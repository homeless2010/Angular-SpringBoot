package com.piedpiper.platform.api.syspermissionresource;

import com.piedpiper.platform.api.syspermissionresource.dto.SysPermissionResource;
import java.util.List;

public abstract interface SysPermissionResourceAPI {
	public abstract void reLoad() throws Exception;

	public abstract List<SysPermissionResource> getAllSysPermissionRes();

	public abstract SysPermissionResource getSysPermissionResourceByMetaData(String paramString);

	public abstract String getCrossDeptByUserId(String paramString);

	public abstract List<String> getPermissionResourceByParentId(String paramString);

	public abstract void deleteSysPermissionResource(String paramString);

	public abstract String analyzePermissionSql(String paramString1, String paramString2);
}
