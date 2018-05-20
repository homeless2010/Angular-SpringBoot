package com.piedpiper.platform.api.syssso;

import java.util.Properties;

public abstract interface ISsoPropsInitService
{
  public abstract void initSsoProps()
    throws Exception;
  
  public abstract void initFillerConfigProps()
    throws Exception;
  
  public abstract void initSsoMap()
    throws Exception;
  
  public abstract void initccSsoMap()
    throws Exception;
  
  public abstract void ssoProps2DB(Properties paramProperties1, String paramString, Properties paramProperties2)
    throws Exception;
  
  public abstract void ccssoProps2DB(Properties paramProperties1, String paramString, Properties paramProperties2)
    throws Exception;
}


