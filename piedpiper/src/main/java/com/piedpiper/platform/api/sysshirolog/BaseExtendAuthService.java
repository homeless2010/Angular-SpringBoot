package com.piedpiper.platform.api.sysshirolog;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract interface BaseExtendAuthService {
	public static final String PROPERTY_ROW_READ = "PropertyRowRead";
	public static final String PROPERTY_ROW_WRITE = "PropertyRowWrite";

	public abstract String getServiceId();

	public abstract String getServiceTitle();

	public abstract LinkedHashMap<String, Map<String, Object>> getAuthType(int paramInt);

	public abstract boolean[] getTypeAuthorizeInfo();

	public abstract LinkedHashMap<String, Map<String, Object>> getAuthResource(String paramString1,
			String paramString2);

	public abstract boolean[] getAuthAuthrizeInfo();
}
