package com.piedpiper.platform.core.jdbc;

class MySqlDialect extends AbstractDialect {
	public String sequenceSql(String sequenceName) {
		throw new UnsupportedOperationException();
	}

	public String pageSql(String selectSql, int maxResults, int firstResult) {
		if (firstResult <= 0) {
			return selectSql + " limit " + maxResults;
		}
		return selectSql + " limit " + maxResults + " offset " + firstResult;
	}

	public boolean supportASWithTableAlias() {
		return true;
	}

	public boolean supportOrderInSubquery() {
		return true;
	}
}
