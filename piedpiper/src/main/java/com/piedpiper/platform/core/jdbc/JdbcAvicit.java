 package com.piedpiper.platform.core.jdbc;
 
 import java.sql.ResultSet;
 import java.sql.ResultSetMetaData;
 import java.sql.SQLException;
 import java.util.ArrayList;
 import java.util.Collection;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import java.util.Map.Entry;
 import org.apache.commons.lang.StringUtils;
 import org.slf4j.Logger;
 import org.springframework.jdbc.core.BeanPropertyRowMapper;
 import org.springframework.jdbc.core.JdbcTemplate;
 import org.springframework.jdbc.core.RowMapper;
 import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
 import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
 import org.springframework.jdbc.support.JdbcUtils;
 
 public class JdbcAvicit
 {
   private IDialect dialect;
   private Logger logger;
   private NamedParameterJdbcOperations namedParameterJdbcOperations;
   private JdbcTemplate jdbcTemplate;
   
   public JdbcAvicit()
   {
     this.logger = org.slf4j.LoggerFactory.getLogger(getClass());
   }
   
 
 
   public IDialect getDialect()
   {
     return this.dialect;
   }
   
   public void setDialect(IDialect dialect) {
     this.dialect = dialect;
   }
   
   public JdbcTemplate getJdbcTemplate() {
     return this.jdbcTemplate;
   }
   
   public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
     this.jdbcTemplate = jdbcTemplate;
   }
   
   public NamedParameterJdbcOperations getNamedParameterJdbcOperations() {
     if (this.namedParameterJdbcOperations == null) {
       this.namedParameterJdbcOperations = new org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate(this.jdbcTemplate);
     }
     return this.namedParameterJdbcOperations;
   }
   
   public void setNamedParameterJdbcOperations(NamedParameterJdbcOperations namedParameterJdbcOperations) {
     this.namedParameterJdbcOperations = namedParameterJdbcOperations;
   }
   
   public NamedParameterJdbcOperations getOperations() {
     return getNamedParameterJdbcOperations();
   }
   
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
   public <T> void getDatesByPageForMap(Page<T> page, String sql, Map<String, Object> param, String tableName)
   {
     getDatesByPageForMap(page, sql, param, tableName, null);
   }
   
   public <T> void getDatesByPageForMap(Page<T> page, String sql, Map<String, Object> param, String tableName, DecorativeSQL ds) {
     MapSqlParameterSource mapSqlParam = new MapSqlParameterSource();
     if (param == null) {
       String sqll = this.dialect.pageSql(sql, page.getRows(), page.getFirstEntityIndex());
       this.logger.debug("COUNT-SQL: select count(1) from (" + sql + ")");
       long count = ((Long)getNamedParameterJdbcOperations().queryForObject("select count(1) from (" + sql + ") t_avicit", mapSqlParam, Long.class)).longValue();
       this.logger.debug("PAGE-SQL: " + sqll);
       
       Collection<T> result = this.namedParameterJdbcOperations.query(sqll, mapSqlParam, new ResultMap(null));
       page.setEntities(result);
       page.setEntityCount(count);
       return;
     }
     
     String whereSql = " WHERE ";
     List<String> condition = buildeQueryParam(param, tableName, mapSqlParam, null);
     if (condition.size() > 0) {
       sql = sql + whereSql + StringUtils.join(condition, " AND ");
     }
     if (ds != null) {
       sql = ds.decorativeSQL(sql, tableName);
     }
     String sqll = this.dialect.pageSql(sql, page.getRows(), page.getFirstEntityIndex());
     
     long count = ((Long)this.namedParameterJdbcOperations.queryForObject("select count(1) from (" + sql + ") t_avicit", mapSqlParam, Long.class)).longValue();
     Collection<T> ddd = this.namedParameterJdbcOperations.query(sqll, mapSqlParam, new ResultMap(null));
     page.setEntities(ddd);
     page.setEntityCount(count);
   }
   
 
 
 
 
 
 
   public <T> void getDatesByPageForMapJava(Page<T> page, String sql, Map<String, Object> param, String tableName)
   {
     getDatesByPageForMapJava(page, sql, param, tableName, null);
   }
   
 
 
 
 
 
 
   public <T> void getDatesByPageForMapJava(Page<T> page, String sql, Map<String, Object> param, String tableName, DecorativeSQL ds)
   {
     MapSqlParameterSource mapSqlParam = new MapSqlParameterSource();
     if (param != null) {
       String whereSql = " WHERE ";
       List<String> condition = buildeQueryParam(param, tableName, mapSqlParam, null);
       if (condition.size() > 0) {
         sql = sql + whereSql + StringUtils.join(condition, " AND ");
       }
     }
     if (ds != null) {
       sql = ds.decorativeSQL(sql, tableName);
     }
     String sqll = this.dialect.pageSql(sql, page.getRows(), page.getFirstEntityIndex());
     this.logger.debug("COUNT-SQL: select count(1) from (" + sql + ")");
     long count = ((Long)this.namedParameterJdbcOperations.queryForObject("select count(1) from (" + sql + ") t_avicit", mapSqlParam, Long.class)).longValue();
     this.logger.debug("PAGE-SQL: " + sqll);
     Collection<T> ddd = this.namedParameterJdbcOperations.query(sqll, mapSqlParam, new ColumnPropertyRowMapper());
     page.setEntities(ddd);
     page.setEntityCount(count);
   }
   
 
 
 
 
 
 
   public <T> void getDatasByPage(Page<T> page, String sql, Map<String, Object> param, Class<T> clazz, String tableName, DecorativeSQL ds)
   {
     MapSqlParameterSource mapSqlParam = new MapSqlParameterSource();
     if (param != null) {
       String whereSql = " WHERE ";
       List<String> condition = buildeQueryParam(param, tableName, mapSqlParam, null);
       if (condition.size() > 0) {
         sql = sql + whereSql + StringUtils.join(condition, " AND ");
       }
     }
     if (ds != null) {
       sql = ds.decorativeSQL(sql, tableName);
     }
     String sqll = this.dialect.pageSql(sql, page.getRows(), page.getFirstEntityIndex());
     this.logger.debug("COUNT-SQL: select count(1) from (" + sql + ")");
     long count = ((Long)this.namedParameterJdbcOperations.queryForObject("select count(1) from (" + sql + ") t_avicit", mapSqlParam, Long.class)).longValue();
     this.logger.debug("PAGE-SQL: " + sqll);
     List<T> result = getNamedParameterJdbcOperations().query(sqll, mapSqlParam, new BeanPropertyRowMapper(clazz));
     page.setEntityCount(count);
     page.setEntities(result);
   }
   
 
 
 
 
 
   public <T> void getDatasByPage(Page<T> page, Map<String, Object> sqlMap, Class<T> clazz)
   {
     MapSqlParameterSource vv = (MapSqlParameterSource)sqlMap.get("mapSqlParameterSource");
     String sql = (String)sqlMap.get("sqlResult");
     String countSql = "";
     if (sql.indexOf("from") != -1) {
       countSql = sql.substring(sql.indexOf("from"), sql.length());
     } else {
       countSql = sql.substring(sql.indexOf("FROM"), sql.length());
     }
     this.logger.debug("COUNT-SQL: select count(1) " + countSql + "");
     long count = ((Long)getNamedParameterJdbcOperations().queryForObject("select count(1) " + countSql + "", vv, Long.class)).longValue();
     String sqlResult = this.dialect.pageSql(sql, page.getRows(), page.getFirstEntityIndex());
     this.logger.debug("PAGE-SQL: " + sqlResult);
     List<T> result = getNamedParameterJdbcOperations().query(sqlResult, vv, new BeanPropertyRowMapper(clazz));
     page.setEntityCount(count);
     page.setEntities(result);
   }
   
 
 
 
 
 
 
 
   public Map<String, Object> buildDynamicSql(String sql, Map<String, Object> conditionsValues, String tableName)
   {
     Map<String, Object> map = new HashMap();
     MapSqlParameterSource msps = new MapSqlParameterSource();
     if ((conditionsValues == null) || (conditionsValues.size() == 0)) {
       map.put("sqlResult", sql);
       return map;
     }
     
     Map<String, String> queryMap = new HashMap();
     List<String> condition = buildeQueryParam(conditionsValues, tableName, msps, queryMap);
     String whereSql = condition.size() == 0 ? "" : " WHERE ";
     if ((sql.contains("WHERE")) || (sql.contains("where")))
     {
       if (condition.size() > 0) {
         sql = sql + " AND " + StringUtils.join(condition, " AND ");
       }
     } else {
       sql = sql + whereSql + StringUtils.join(condition, " AND ");
     }
     map.put("sqlResult", sql);
     map.put("mapSqlParameterSource", msps);
     map.put("queryMap", queryMap);
     return map;
   }
   
 
 
 
 
 
   public List<Map<String, Object>> queryForMap(String sql, Map<String, String> map)
   {
     return query(sql, map, new StringRowMapper());
   }
   
 
 
 
 
 
   public List<Map<String, Object>> queryForMapJava(String sql, Map<String, String> map)
   {
     return query(sql, map, new ColumnPropertyRowMapper());
   }
   
 
 
 
 
 
 
   public List<Map<String, Object>> queryForMapJavaWithParam(String sql, Map<String, Object> param, String tableName, DecorativeSQL ds)
   {
     Map<String, Object> paramBulid = buildDynamicSql(sql, param, tableName);
     String sqlBuild = paramBulid.get("sqlResult").toString();
     if (ds != null) {
       sqlBuild = ds.decorativeSQL(sqlBuild, tableName);
     }
     return query(sqlBuild, (Map)paramBulid.get("queryMap"), new ColumnPropertyRowMapper());
   }
   
 
 
 
 
 
 
   public List<Map<String, Object>> queryForMap(String sql, Map<String, String> map, StringRowMapper stringRowMapper)
   {
     return query(sql, map, stringRowMapper);
   }
   
 
 
 
 
 
 
   public List<Map<String, Object>> queryForMap(String sql, Map<String, String> map, ColumnPropertyRowMapper stringRowMapper)
   {
     return query(sql, map, stringRowMapper);
   }
   
 
 
 
 
 
 
   public <T> List<T> query(String sql, Map<String, String> map, RowMapper<T> rowMapper)
   {
     return getNamedParameterJdbcOperations().query(sql, map, rowMapper);
   }
   
 
 
 
 
 
 
 
   public <T> void page(Page<T> page, String sql, Map<String, String> map, RowMapper<T> rowMapper)
   {
     String sqll = this.dialect.pageSql(sql, page.getRows(), page.getFirstEntityIndex());
     this.logger.debug("COUNT-SQL: select count(1) from (" + sql + ")");
     long count = ((Long)getNamedParameterJdbcOperations().queryForObject("select count(1) from (" + sql + ") t_avicit", map, Long.class)).longValue();
     this.logger.debug("PAGE-SQL: " + sqll);
     List<T> result = this.namedParameterJdbcOperations.query(sqll, map, rowMapper);
     page.setEntities(result);
     page.setEntityCount(count);
   }
   
 
 
 
 
 
 
   public void pageForMap(Page<Map<String, Object>> page, String sql, Map<String, String> map)
   {
     page(page, sql, map, new StringRowMapper());
   }
   
 
 
 
 
 
   public void pageForMapJava(Page<Map<String, Object>> page, String sql, Map<String, String> map)
   {
     page(page, sql, map, new ColumnPropertyRowMapper());
   }
   
 
 
 
 
 
   public void pageForMap(Page<Map<String, Object>> page, String sql, Map<String, String> map, StringRowMapper stringRowMapper)
   {
     page(page, sql, map, stringRowMapper);
   }
   
 
 
 
 
 
 
   public void pageForMap(Page<Map<String, Object>> page, String sql, Map<String, String> map, ColumnPropertyRowMapper columnPropertyRowMapper)
   {
     page(page, sql, map, columnPropertyRowMapper);
   }
   
 
 
 
 
 
 
 
   private List<String> buildeQueryParam(Map<String, Object> param, String tableName, MapSqlParameterSource mapSqlParam, Map<String, String> map)
   {
     List<String> condition = new ArrayList(param.size());
     String[] token = null;
     if (map == null) map = new HashMap();
     for (Map.Entry<String, Object> entry : param.entrySet()) {
       String temp = "";
       String tableNameTemp = "";
       token = ((String)entry.getKey()).split("-");
       if (("".equals(entry.getValue())) || (null == entry.getValue())) {
         this.logger.debug("查询参数key为空或者为null");
 
       }
       else if (token.length < 3) {
         this.logger.debug("查询参数" + (String)entry.getKey() + "不符合查询参数标直接添加到sql查询参数中。");
         map.put(entry.getKey(), entry.getValue().toString());
         mapSqlParam.addValue((String)entry.getKey(), entry.getValue(), 12);
       }
       else {
         if ((tableName != null) && (!tableName.equals(""))) {
           tableNameTemp = tableName + "." + token[2];
         } else {
           tableNameTemp = token[2];
         }
         if (StringUtils.equalsIgnoreCase("LIKE", token[1]))
         {
           temp = tableNameTemp + " LIKE " + ":" + token[2] + "";
           mapSqlParam.addValue(token[2], "%" + entry.getValue() + "%", 12);
           map.put(token[2], "%" + entry.getValue() + "%");
           condition.add(temp);
         } else if (StringUtils.equalsIgnoreCase("EQ", token[1]))
         {
           temp = tableNameTemp + " = " + ":" + token[2] + "";
           mapSqlParam.addValue(token[2], entry.getValue(), 12);
           map.put(token[2], entry.getValue().toString());
           condition.add(temp);
         } else if (StringUtils.equalsIgnoreCase("LE", token[1]))
         {
           temp = tableNameTemp + " LIKE " + ":" + token[2] + "";
           mapSqlParam.addValue(token[2], entry.getValue(), 12);
           map.put(token[2], entry.getValue().toString());
           condition.add(temp);
         } else if (StringUtils.equalsIgnoreCase("LT", token[1]))
         {
           temp = tableNameTemp + " LIKE " + ":" + token[2] + "";
           mapSqlParam.addValue(token[2], entry.getValue(), 12);
           map.put(token[2], entry.getValue().toString());
           condition.add(temp);
         } else if (StringUtils.equalsIgnoreCase("GE", token[1]))
         {
           temp = tableNameTemp + " LIKE " + ":" + token[2] + "";
           mapSqlParam.addValue(token[2], entry.getValue(), 12);
           map.put(token[2], entry.getValue().toString());
           condition.add(temp);
         } else if (StringUtils.equalsIgnoreCase("GT", token[1]))
         {
           temp = tableNameTemp + " LIKE " + ":" + token[2] + "";
           mapSqlParam.addValue(token[2], entry.getValue(), 12);
           map.put(token[2], entry.getValue().toString());
           condition.add(temp);
         } else if (StringUtils.equalsIgnoreCase("GEDATE", token[1]))
         {
           temp = tableNameTemp + " LIKE " + ":" + token[2] + "";
           mapSqlParam.addValue(token[2], entry.getValue(), 91);
           map.put(token[2], entry.getValue().toString());
           condition.add(temp);
         } else if (StringUtils.equalsIgnoreCase("LEDATE", token[1]))
         {
           temp = tableNameTemp + " LIKE " + ":" + token[2] + "";
           mapSqlParam.addValue(token[2], entry.getValue(), 91);
           map.put(token[2], entry.getValue().toString());
           condition.add(temp);
         }
       }
     }
     return condition;
   }
   
   private class ResultMap implements RowMapper<Map<String, Object>>
   {
     private ResultMap() {}
     
     public Map<String, Object> mapRow(ResultSet paramResultSet, int paramInt) throws SQLException
     {
       ResultSetMetaData rsmd = paramResultSet.getMetaData();
       int columnCount = rsmd.getColumnCount();
       Map<String, Object> map = new HashMap(columnCount);
       for (int index = 1; index <= columnCount; index++) {
         String column = JdbcUtils.lookupColumnName(rsmd, index);
         
 
         Object value = JdbcUtils.getResultSetValue(paramResultSet, index, String.class);
         map.put(column, value);
       }
       return map;
     }
   }
 }


