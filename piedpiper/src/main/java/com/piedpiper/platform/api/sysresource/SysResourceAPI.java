package com.piedpiper.platform.api.sysresource;

import com.piedpiper.platform.api.sysresource.dto.SysResource;
import java.util.List;

public abstract interface SysResourceAPI {
	public abstract void reLoad() throws Exception;

	public abstract SysResource getSysResourceById(String paramString);

	public abstract SysResource getSysResourceByValue(String paramString1, String paramString2);

	public abstract List<SysResource> getAllList();

	public abstract void insertSysResource(SysResource paramSysResource);

	public abstract void insertSysResource(List<SysResource> paramList);

	public abstract void updateSysResource(SysResource paramSysResource);

	public abstract void deleteSysResource(SysResource paramSysResource);

	public abstract void deleteSysResourceById(String paramString);

	public abstract List<SysResource> getSysResourceByParentId(String paramString);
}
