package com.piedpiper.platform.api.syspermissionresource.permissionanalysis.core.support;

import com.piedpiper.platform.api.syspermissionresource.dto.SysPermissionResource;
import java.util.List;

public abstract interface SysPermisLoaderI
{
  public abstract List<SysPermissionResource> getAllSysPermissionRes();
  
  public abstract SysPermissionResource getSysPermissionResourceByMetaData(String paramString);
  
  public abstract String getCrossDeptByUserId(String paramString);
}


