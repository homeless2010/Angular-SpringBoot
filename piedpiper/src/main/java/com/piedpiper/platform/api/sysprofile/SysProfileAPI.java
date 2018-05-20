package com.piedpiper.platform.api.sysprofile;

import java.util.List;

public abstract interface SysProfileAPI
{
  public abstract void reLoad()
    throws Exception;
  
  @Deprecated
  public abstract String getProfileValueByCode(String paramString);
  
  public abstract boolean containsOptionCode(String paramString);
  
  public abstract String getProfileValueByCodeByAppId(String paramString1, String paramString2);
  
  public abstract List<Object> getProfileValueList(String paramString);
  
  public abstract List<Object> getProfileValueListByAppId(String paramString1, String paramString2);
}


