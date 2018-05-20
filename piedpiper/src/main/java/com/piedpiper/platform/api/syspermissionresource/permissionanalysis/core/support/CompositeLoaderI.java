package com.piedpiper.platform.api.syspermissionresource.permissionanalysis.core.support;

import com.piedpiper.platform.api.syspermissionresource.dto.SysPermissionResource;
import java.util.List;

public abstract interface CompositeLoaderI
  extends LoaderConstant
{
  public abstract SysPermissionResource getSysPermissionResourceByMetaData(String paramString);
  
  public abstract List<String> getOwnRolesByUserId(String paramString);
  
  public abstract List<String> getOwnDeptsByUserId(String paramString);
  
  public abstract List<String> getOwnGroupsByUserId(String paramString);
  
  public abstract List<String> getOwnPositionsByUserId(String paramString);
  
  public abstract String getChiefDeptByUserId(String paramString);
  
  public abstract String getSubDeptByDeptId(String paramString);
  
  public abstract String getCrossDeptByUserId(String paramString);
}


