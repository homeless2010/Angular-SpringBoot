package com.piedpiper.platform.core.mybatis.pagehelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.builder.SqlSourceBuilder;
import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.builder.annotation.ProviderSqlSource;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.scripting.xmltags.DynamicContext;
import org.apache.ibatis.scripting.xmltags.DynamicSqlSource;
import org.apache.ibatis.scripting.xmltags.MixedSqlNode;
import org.apache.ibatis.scripting.xmltags.SqlNode;
import org.apache.ibatis.session.Configuration;

public class SqlUtil {
	private static final List<ResultMapping> EMPTY_RESULTMAPPING = new ArrayList(0);
	private static final String SUFFIX_PAGE = "_PageHelper";
	private static final String SUFFIX_COUNT = "_PageHelper_Count";
	private static final String PAGEPARAMETER_FIRST = "First_PageHelper";
	private static final String PAGEPARAMETER_SECOND = "Second_PageHelper";
	private static final String PROVIDER_OBJECT = "_provider_object";
	private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
	private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
	private Parser sqlParser;

	private static MetaObject forObject(Object object) {
		return MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
	}

	public static enum Dialect {
		mysql, mariadb, sqlite, oracle, hsqldb, postgresql;
	}

	public SqlUtil(String strDialect) {
		if ((strDialect == null) || ("".equals(strDialect))) {
			throw new IllegalArgumentException("Mybatis分页插件无法获取dialect参数!");
		}
		try {
			Dialect dialect = Dialect.valueOf(strDialect);
			String sqlParserClass = getClass().getPackage().getName() + ".SqlParser";
			try {
				Class.forName("net.sf.jsqlparser.statement.select.Select");
				this.sqlParser = ((Parser) Class.forName(sqlParserClass).getConstructor(new Class[] { Dialect.class })
						.newInstance(new Object[] { dialect }));
			} catch (Exception e) {
			}
			if (this.sqlParser == null) {
				this.sqlParser = SimpleParser.newParser(dialect);
			}
		} catch (IllegalArgumentException e) {
			String dialects = null;
			for (Dialect d : Dialect.values()) {
				if (dialects == null) {
					dialects = d.toString();
				} else {
					dialects = dialects + "," + d;
				}
			}
			throw new IllegalArgumentException("Mybatis分页插件dialect参数值错误，可选值为[" + dialects + "]");
		}
	}

	public Map setPageParameter(MappedStatement ms, Object parameterObject, Page page) {
		BoundSql boundSql = ms.getBoundSql(parameterObject);
		return this.sqlParser.setPageParameter(ms, parameterObject, boundSql, page);
	}

	public void processCountMappedStatement(MappedStatement ms, SqlSource sqlSource, Object[] args) {
		args[0] = getMappedStatement(ms, sqlSource, args[1], "_PageHelper_Count");
	}

	public void processPageMappedStatement(MappedStatement ms, SqlSource sqlSource, Page page, Object[] args) {
		args[0] = getMappedStatement(ms, sqlSource, args[1], "_PageHelper");

		args[1] = setPageParameter((MappedStatement) args[0], args[1], page);
	}

	public static abstract interface Parser {
		public abstract void isSupportedSql(String paramString);

		public abstract String getCountSql(String paramString);

		public abstract String getPageSql(String paramString);

		public abstract Map setPageParameter(MappedStatement paramMappedStatement, Object paramObject,
				BoundSql paramBoundSql, Page paramPage);
	}

	public static abstract class SimpleParser implements SqlUtil.Parser {
	public static SqlUtil.Parser newParser(SqlUtil.Dialect dialect)
    {
      SqlUtil.Parser parser = null;
      switch (dialect)
      {
      case mysql: 
        parser = new SqlUtil.MysqlParser();
        break;
      case oracle: 
        parser = new SqlUtil.OracleParser();
        break;
      case hsqldb: 
        parser = new SqlUtil.HsqldbParser();
        break;
      case postgresql: 
    	  parser = new SqlUtil.PostgreSQLParser();
    	  break;
      default: 
        parser = new SqlUtil.PostgreSQLParser();
      }
      return parser;
    }

		public void isSupportedSql(String sql) {
			if (sql.trim().toUpperCase().endsWith("FOR UPDATE")) {
				throw new RuntimeException("分页插件不支持包含for update的sql");
			}
		}

		public String getCountSql(String sql) {
			isSupportedSql(sql);
			StringBuilder stringBuilder = new StringBuilder(sql.length() + 40);
			stringBuilder.append("select count(*) from (");
			stringBuilder.append(sql);
			stringBuilder.append(") tmp_count");
			return stringBuilder.toString();
		}

		public abstract String getPageSql(String paramString);

		public Map setPageParameter(MappedStatement ms, Object parameterObject, BoundSql boundSql, Page page) {
			Map paramMap = null;
			boolean hasTypeHandler;
			MetaObject metaObject;
			if (parameterObject == null) {
				paramMap = new HashMap();
			} else if ((parameterObject instanceof Map)) {
				paramMap = (Map) parameterObject;
			} else {
				paramMap = new HashMap();

				hasTypeHandler = ms.getConfiguration().getTypeHandlerRegistry()
						.hasTypeHandler(parameterObject.getClass());
				metaObject = SqlUtil.forObject(parameterObject);
				if ((ms.getSqlSource() instanceof SqlUtil.MyProviderSqlSource)) {
					paramMap.put("_provider_object", parameterObject);
				}
				if (!hasTypeHandler) {
					for (String name : metaObject.getGetterNames()) {
						paramMap.put(name, metaObject.getValue(name));
					}
				}
				if ((boundSql.getParameterMappings() != null) && (boundSql.getParameterMappings().size() > 0)) {
					for (ParameterMapping parameterMapping : boundSql.getParameterMappings()) {
						String name = parameterMapping.getProperty();
						if ((!name.equals("First_PageHelper")) && (!name.equals("Second_PageHelper"))
								&& (paramMap.get(name) == null)) {
							if ((hasTypeHandler)
									|| (parameterMapping.getJavaType().equals(parameterObject.getClass()))) {
								paramMap.put(name, parameterObject);
							} else {
								paramMap.put(name, metaObject.getValue(name));
							}
						}
					}
				}
			}
			return paramMap;
		}
	}

	private static class MysqlParser extends SqlUtil.SimpleParser {
		public String getPageSql(String sql) {
			StringBuilder sqlBuilder = new StringBuilder(sql.length() + 14);
			sqlBuilder.append(sql);
			sqlBuilder.append(" limit ?,?");
			return sqlBuilder.toString();
		}

		public Map setPageParameter(MappedStatement ms, Object parameterObject, BoundSql boundSql, Page page) {
			Map paramMap = super.setPageParameter(ms, parameterObject, boundSql, page);
			paramMap.put("First_PageHelper", Integer.valueOf(page.getStartRow()));
			paramMap.put("Second_PageHelper", Integer.valueOf(page.getPageSize()));
			return paramMap;
		}
	}

	private static class OracleParser extends SqlUtil.SimpleParser {
		public String getPageSql(String sql) {
			StringBuilder sqlBuilder = new StringBuilder(sql.length() + 120);
			sqlBuilder.append("select * from ( select tmp_page.*, rownum row_id from ( ");
			sqlBuilder.append(sql);
			sqlBuilder.append(" ) tmp_page where rownum <= ? ) where row_id > ?");
			return sqlBuilder.toString();
		}

		public Map setPageParameter(MappedStatement ms, Object parameterObject, BoundSql boundSql, Page page) {
			Map paramMap = super.setPageParameter(ms, parameterObject, boundSql, page);
			paramMap.put("First_PageHelper", Integer.valueOf(page.getEndRow()));
			paramMap.put("Second_PageHelper", Integer.valueOf(page.getStartRow()));
			return paramMap;
		}
	}

	private static class HsqldbParser extends SqlUtil.SimpleParser {
		public String getPageSql(String sql) {
			StringBuilder sqlBuilder = new StringBuilder(sql.length() + 20);
			sqlBuilder.append(sql);
			sqlBuilder.append(" limit ? offset ?");
			return sqlBuilder.toString();
		}

		public Map setPageParameter(MappedStatement ms, Object parameterObject, BoundSql boundSql, Page page) {
			Map paramMap = super.setPageParameter(ms, parameterObject, boundSql, page);
			paramMap.put("First_PageHelper", Integer.valueOf(page.getPageSize()));
			paramMap.put("Second_PageHelper", Integer.valueOf(page.getStartRow()));
			return paramMap;
		}
	}

	private static class PostgreSQLParser extends SqlUtil.SimpleParser {
		public String getPageSql(String sql) {
			StringBuilder sqlBuilder = new StringBuilder(sql.length() + 14);
			sqlBuilder.append(sql);
			sqlBuilder.append(" limit ? offset ?");
			return sqlBuilder.toString();
		}

		public Map setPageParameter(MappedStatement ms, Object parameterObject, BoundSql boundSql, Page page) {
			Map paramMap = super.setPageParameter(ms, parameterObject, boundSql, page);
			paramMap.put("First_PageHelper", Integer.valueOf(page.getPageSize()));
			paramMap.put("Second_PageHelper", Integer.valueOf(page.getStartRow()));
			return paramMap;
		}
	}

	private class MyDynamicSqlSource implements SqlSource {
		private Configuration configuration;
		private SqlNode rootSqlNode;
		private Boolean count;

		public MyDynamicSqlSource(Configuration configuration, SqlNode rootSqlNode, Boolean count) {
			this.configuration = configuration;
			this.rootSqlNode = rootSqlNode;
			this.count = count;
		}

		public BoundSql getBoundSql(Object parameterObject) {
			DynamicContext context = new DynamicContext(this.configuration, parameterObject);
			this.rootSqlNode.apply(context);
			SqlSourceBuilder sqlSourceParser = new SqlSourceBuilder(this.configuration);
			Class<?> parameterType = parameterObject == null ? Object.class : parameterObject.getClass();
			SqlSource sqlSource = sqlSourceParser.parse(context.getSql(), parameterType, context.getBindings());
			if (this.count.booleanValue()) {
				sqlSource = SqlUtil.this.getCountSqlSource(this.configuration, sqlSource, parameterObject);
			} else {
				sqlSource = SqlUtil.this.getPageSqlSource(this.configuration, sqlSource, parameterObject);
			}
			BoundSql boundSql = sqlSource.getBoundSql(parameterObject);
			for (Map.Entry<String, Object> entry : context.getBindings().entrySet()) {
				boundSql.setAdditionalParameter((String) entry.getKey(), entry.getValue());
			}
			return boundSql;
		}
	}

	private class MyProviderSqlSource implements SqlSource {
		private Configuration configuration;
		private ProviderSqlSource providerSqlSource;
		private Boolean count;

		private MyProviderSqlSource(Configuration configuration, ProviderSqlSource providerSqlSource, Boolean count) {
			this.configuration = configuration;
			this.providerSqlSource = providerSqlSource;
			this.count = count;
		}

		public BoundSql getBoundSql(Object parameterObject) {
			BoundSql boundSql = null;
			if (((parameterObject instanceof Map)) && (((Map) parameterObject).containsKey("_provider_object"))) {
				boundSql = this.providerSqlSource.getBoundSql(((Map) parameterObject).get("_provider_object"));
			} else {
				boundSql = this.providerSqlSource.getBoundSql(parameterObject);
			}
			if (this.count.booleanValue()) {
				return new BoundSql(this.configuration, SqlUtil.this.sqlParser.getCountSql(boundSql.getSql()),
						boundSql.getParameterMappings(), parameterObject);
			}
			return new BoundSql(this.configuration, SqlUtil.this.sqlParser.getPageSql(boundSql.getSql()),
					SqlUtil.this.getPageParameterMapping(this.configuration, boundSql), parameterObject);
		}
	}

	private MappedStatement getMappedStatement(MappedStatement ms, SqlSource sqlSource, Object parameterObject,
			String suffix) {
		MappedStatement qs = null;
		try {
			qs = ms.getConfiguration().getMappedStatement(ms.getId() + suffix);
		} catch (Exception e) {
		}
		if (qs == null) {
			qs = newMappedStatement(ms, getsqlSource(ms, sqlSource, parameterObject, suffix), suffix);
			try {
				ms.getConfiguration().addMappedStatement(qs);
			} catch (Exception e) {
			}
		}
		return qs;
	}

	private MappedStatement newMappedStatement(MappedStatement ms, SqlSource sqlSource, String suffix) {
		String id = ms.getId() + suffix;
		MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), id, sqlSource,
				ms.getSqlCommandType());
		builder.resource(ms.getResource());
		builder.fetchSize(ms.getFetchSize());
		builder.statementType(ms.getStatementType());
		builder.keyGenerator(ms.getKeyGenerator());
		if ((ms.getKeyProperties() != null) && (ms.getKeyProperties().length != 0)) {
			StringBuilder keyProperties = new StringBuilder();
			for (String keyProperty : ms.getKeyProperties()) {
				keyProperties.append(keyProperty).append(",");
			}
			keyProperties.delete(keyProperties.length() - 1, keyProperties.length());
			builder.keyProperty(keyProperties.toString());
		}
		builder.timeout(ms.getTimeout());
		builder.parameterMap(ms.getParameterMap());
		if (suffix == "_PageHelper") {
			builder.resultMaps(ms.getResultMaps());
		} else {
			List<ResultMap> resultMaps = new ArrayList();
			ResultMap resultMap = new ResultMap.Builder(ms.getConfiguration(), id, Integer.TYPE, EMPTY_RESULTMAPPING)
					.build();
			resultMaps.add(resultMap);
			builder.resultMaps(resultMaps);
		}
		builder.resultSetType(ms.getResultSetType());
		builder.cache(ms.getCache());
		builder.flushCacheRequired(ms.isFlushCacheRequired());
		builder.useCache(ms.isUseCache());

		return builder.build();
	}

	public boolean isDynamic(MappedStatement ms) {
		return ms.getSqlSource() instanceof DynamicSqlSource;
	}

	private SqlSource getsqlSource(MappedStatement ms, SqlSource sqlSource, Object parameterObject, String suffix) {
		if (isDynamic(ms)) {
			MetaObject msObject = forObject(ms);
			SqlNode sqlNode = (SqlNode) msObject.getValue("sqlSource.rootSqlNode");
			MixedSqlNode mixedSqlNode = null;
			if ((sqlNode instanceof MixedSqlNode)) {
				mixedSqlNode = (MixedSqlNode) sqlNode;
			} else {
				List<SqlNode> contents = new ArrayList(1);
				contents.add(sqlNode);
				mixedSqlNode = new MixedSqlNode(contents);
			}
			return new MyDynamicSqlSource(ms.getConfiguration(), mixedSqlNode,
					Boolean.valueOf(suffix == "_PageHelper_Count"));
		}
		if ((sqlSource instanceof ProviderSqlSource)) {
			return new MyProviderSqlSource(ms.getConfiguration(), (ProviderSqlSource) sqlSource,
					Boolean.valueOf(suffix == "_PageHelper_Count"));
		}
		if (suffix == "_PageHelper") {
			return getPageSqlSource(ms.getConfiguration(), sqlSource, parameterObject);
		}
		return getCountSqlSource(ms.getConfiguration(), sqlSource, parameterObject);
	}

	private List<ParameterMapping> getPageParameterMapping(Configuration configuration, BoundSql boundSql) {
		List<ParameterMapping> newParameterMappings = new ArrayList();
		newParameterMappings.addAll(boundSql.getParameterMappings());
		newParameterMappings
				.add(new ParameterMapping.Builder(configuration, "First_PageHelper", Integer.class).build());
		newParameterMappings
				.add(new ParameterMapping.Builder(configuration, "Second_PageHelper", Integer.class).build());
		return newParameterMappings;
	}

	private SqlSource getPageSqlSource(Configuration configuration, SqlSource sqlSource, Object parameterObject) {
		BoundSql boundSql = sqlSource.getBoundSql(parameterObject);
		return new StaticSqlSource(configuration, this.sqlParser.getPageSql(boundSql.getSql()),
				getPageParameterMapping(configuration, boundSql));
	}

	private SqlSource getCountSqlSource(Configuration configuration, SqlSource sqlSource, Object parameterObject) {
		BoundSql boundSql = sqlSource.getBoundSql(parameterObject);
		return new StaticSqlSource(configuration, this.sqlParser.getCountSql(boundSql.getSql()),
				boundSql.getParameterMappings());
	}
}
