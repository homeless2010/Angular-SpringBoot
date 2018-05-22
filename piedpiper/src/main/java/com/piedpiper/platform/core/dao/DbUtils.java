package com.piedpiper.platform.core.dao;

import com.piedpiper.platform.core.properties.PlatformProperties;

public class DbUtils {
	private static final String dbType = PlatformProperties.getProperty("db.type");

	public static String getDbType() {
		return dbType;
	}

	public static boolean isMySql() {
		return "mysql".equals(dbType);
	}

	public static boolean isOracle() {
		return "oracle".equals(dbType);
	}
}
