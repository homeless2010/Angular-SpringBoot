package com.piedpiper.platform.api.syspermissionresource.permissionanalysis.core;

import com.piedpiper.platform.api.syspermissionresource.dto.SysPermissionResource;
import com.piedpiper.platform.api.syspermissionresource.permissionanalysis.annotation.DataPermission;

abstract interface AccessAnalyze
{
  public abstract String generateSqlCondition(SysPermissionResource paramSysPermissionResource, DataPermission paramDataPermission);
}


