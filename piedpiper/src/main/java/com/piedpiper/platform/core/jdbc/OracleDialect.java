package com.piedpiper.platform.core.jdbc;

class OracleDialect extends AbstractDialect {
	public String sequenceSql(String sequenceName) {
		return "select " + sequenceName + ".nextval from dual";
	}

	public String pageSql(String selectSql, int maxResults, int firstResult) {
		if (firstResult <= 0) {
			return "select * from ( " + selectSql + " ) where rownum <= " + maxResults;
		}
		return "select * from ( select row_.*, rownum rownum_var from ( " + selectSql + " ) row_ where rownum <= "
				+ (firstResult + maxResults) + ") where rownum_var > " + firstResult;
	}

	public boolean supportASWithTableAlias() {
		return false;
	}

	public boolean supportOrderInSubquery() {
		return true;
	}
}
