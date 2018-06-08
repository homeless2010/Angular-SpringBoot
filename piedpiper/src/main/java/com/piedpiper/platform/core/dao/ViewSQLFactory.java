package com.piedpiper.platform.core.dao;

import com.piedpiper.platform.core.dao.datasource.DataSourceUtil;
import org.springframework.jdbc.core.JdbcTemplate;

public class ViewSQLFactory {
	public static String getViewSQL(JdbcTemplate jdbcTemplate, String statment, int start, int lineNumber) {
		return getViewSQL(jdbcTemplate, statment, start, lineNumber, 0);
	}

	public static String getViewSQL(JdbcTemplate jdbcTemplate, String statment, int start, int lineNumber,
			int lineCount) {
		String dbSupply = DataSourceUtil.getInstance().getDataBaseType(jdbcTemplate);

		if (dbSupply.toLowerCase().equals("oracle")) {
			return "select * from (select rownum r,t1.* from (" + statment + ") t1 ) t2 where t2.r>=" + start
					+ " and t2.r<=" + (start - 1 + lineNumber);
		}
		if (dbSupply.toLowerCase().equals("sqlserver")) {
			start = start == 1 ? 0 : start;

			String statmentTmp = statment;
			String orderby = "";
			if (statment.toUpperCase().indexOf("ORDER BY") > 0) {
				statmentTmp = statment.substring(0, statment.toUpperCase().indexOf("ORDER BY")).trim();
				orderby = statment.substring(statment.toUpperCase().indexOf("ORDER BY"));
			}
			lineCount = lineCount > 0 ? lineCount
					: jdbcTemplate.queryForRowSet("select count(*) as c from (" + statmentTmp + ") tmpTable").getRow();

			if (start + lineNumber > lineCount) {
				return "select * from ( select ROW_NUMBER() OVER(" + orderby + ") as rownum ,"
						+ statmentTmp.substring(statmentTmp.toLowerCase().indexOf("select") + 6)
						+ ") as t where rownum between " + start + " and " + lineCount;
			}
			return "select * from ( select ROW_NUMBER() OVER(" + orderby + ") as rownum ,"
					+ statmentTmp.substring(statmentTmp.toLowerCase().indexOf("select") + 6)
					+ ") as t where rownum between " + start + " and "
					+ (start > 0 ? start + lineNumber - 1 : start + lineNumber);
		}

		if (dbSupply.toLowerCase().equals("mysql")) {

			start = start == 1 ? 0 : start;
			return "select * from (" + statment + ") tmpTab limit " + (start > 0 ? start - 1 : start) + ","
					+ lineNumber;
		}
		if (dbSupply.toLowerCase().equals("db2")) {

			start = start == 1 ? 0 : start;
			String sql = "SELECT * FROM (Select rownumber() over() as row,t.* from (" + statment
					+ ") as t) AS t1 WHERE t1.row BETWEEN " + start + " AND " + (start - 1 + lineNumber);
			return sql;
		}
		if (dbSupply.toLowerCase().equals("oscar")) {

			start = start == 1 ? 0 : start;
			return "select * from (" + statment + ") limit " + lineNumber + " OFFSET " + (start - 1);
		}
		return statment;
	}

	public static String getSqlOfISNULL(JdbcTemplate jdbcTemplate, boolean isNull) {
		String dbSupply = DataSourceUtil.getInstance().getDataBaseType(jdbcTemplate);
		if (dbSupply.toLowerCase().equals("oracle"))
			return isNull ? "is null" : "is not null";
		if (dbSupply.toLowerCase().equals("sqlserver"))
			return isNull ? "=''" : "<>''";
		if (dbSupply.toLowerCase().equals("mysql"))
			return isNull ? "=''" : "<>''";
		if (dbSupply.toLowerCase().equals("db2"))
			return isNull ? "is null" : "is not null";
		if (dbSupply.toLowerCase().equals("oscar")) {
			return isNull ? "is null" : "is not null";
		}
		return "";
	}

	public static String convertDateField(JdbcTemplate jdbcTemplate, String fieldName) {
		String dateField = fieldName;
		String dbSupply = DataSourceUtil.getInstance().getDataBaseType(jdbcTemplate);
		if (dbSupply.toLowerCase().equals("oracle")) {
			dateField = "trunc(" + fieldName + ")";
		} else if (!dbSupply.equalsIgnoreCase("access")) {
			if (dbSupply.toLowerCase().equals("sqlserver")) {
				dateField = "CONVERT(CHAR(10), " + fieldName + ", 120)";
			} else if (dbSupply.toLowerCase().equals("mysql")) {
				dateField = "DATE(" + fieldName + ")";
			} else if (!dbSupply.toLowerCase().equals("db2")) {
				if (!dbSupply.toLowerCase().equals("oscar")) {
				}
			}
		}
		return dateField;
	}

	public static String getDateDefaultValue(JdbcTemplate jdbcTemplate) {
		String dbSupply = DataSourceUtil.getInstance().getDataBaseType(jdbcTemplate);
		if (dbSupply.toLowerCase().equals("oracle"))
			return " sysdate ";
		if (dbSupply.toLowerCase().equals("mysql"))
			return " now() ";
		if (dbSupply.toLowerCase().equals("sqlserver"))
			return " getdate() ";
		if (dbSupply.toLowerCase().equals("db2")) {
			return " current date ";
		}
		return "''";
	}
}
