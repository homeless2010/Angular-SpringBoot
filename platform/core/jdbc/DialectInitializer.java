package com.piedpiper.platform.core.jdbc;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Map;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

public class DialectInitializer implements ApplicationContextAware {
	private ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
		initialize();
	}

	private void initialize() {
		Map<String, JdbcAvicit> daoMap = this.applicationContext.getBeansOfType(JdbcAvicit.class);
		for (JdbcAvicit dao : daoMap.values()) {
			doInitialize(dao);
		}
	}

	private void doInitialize(JdbcAvicit dao) {
		if (dao.getDialect() == null) {
			PropertyDescriptor propDesc = BeanUtils.getPropertyDescriptor(dao.getClass(), "dialect");
			if (propDesc != null) {
				Method m = propDesc.getWriteMethod();
				if (m != null) {
					IDialect dialect = createDialect(dao);
					if (dialect != null) {
						try {
							m.invoke(dao, new Object[] { dialect });
						} catch (Exception e) {
							throw new RuntimeException(e);
						}
					}
				}
			}
		}
	}

	protected IDialect createDialect(JdbcAvicit dao) {
		IDialect d = null;
		d = (IDialect) dao.getOperations().getJdbcOperations().execute(new ConnectionCallback() {
			public IDialect doInConnection(Connection con) throws SQLException, DataAccessException {
				DatabaseMetaData meta = con.getMetaData();
				String dbName = meta.getDatabaseProductName();
				if ("Microsoft SQL Server".equals(dbName)) {
					return new MssqlDialect();
				}
				if ("Oracle".equals(dbName)) {
					return new OracleDialect();
				}
				if ("MySQL".equals(dbName)) {
					return new MySqlDialect();
				}
				if ("DM DBMS".equals(dbName)) {
					return new DmDialect();
				}
				if ((dbName != null) && (dbName.startsWith("DB2"))) {
					return new Db2Dialect();
				}
				return new OracleDialect();
			}

		});
		return d;
	}
}
