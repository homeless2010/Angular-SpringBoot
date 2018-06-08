package com.piedpiper.platform.api.sysshirolog.impl;

import com.piedpiper.platform.api.sysresource.dto.SysResource;
import java.util.List;

public abstract interface BaseCompExtendService
{
  public abstract List<SysResource> getAuthResource(String paramString1, String paramString2);
  
  public abstract boolean matchUrl(String paramString);
}


