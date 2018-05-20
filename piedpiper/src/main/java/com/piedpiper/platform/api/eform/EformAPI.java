package com.piedpiper.platform.api.eform;

import com.piedpiper.platform.api.syspermissionresource.dto.SysPermissionResource;
import com.piedpiper.platform.api.sysresource.dto.SysResource;
import java.util.List;
import java.util.Map;

public abstract interface EformAPI
{
  public abstract List<SysResource> getEformResource(String paramString1, String paramString2);
  
  public abstract List<SysPermissionResource> getEformDataResource(String paramString1, String paramString2);
  
  public abstract Map<String, String> getEformElements(String paramString);
}


