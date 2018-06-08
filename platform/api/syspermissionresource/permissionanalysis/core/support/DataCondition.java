package com.piedpiper.platform.api.syspermissionresource.permissionanalysis.core.support;

import com.piedpiper.platform.api.syspermissionresource.dto.SysPermissionResource;
import com.piedpiper.platform.api.syspermissionresource.permissionanalysis.annotation.DataPermission;

public abstract interface DataCondition
{
  public abstract String generateSqlCondition(SysPermissionResource paramSysPermissionResource, DataPermission paramDataPermission);
}


