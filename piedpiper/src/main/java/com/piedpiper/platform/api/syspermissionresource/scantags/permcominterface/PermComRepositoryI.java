package com.piedpiper.platform.api.syspermissionresource.scantags.permcominterface;

import com.piedpiper.platform.api.syspermissionresource.dto.SysPermissionResource;
import java.util.List;

public abstract interface PermComRepositoryI
{
  public abstract List<SysPermissionResource> getPermResourceWithDataGrid();
}


