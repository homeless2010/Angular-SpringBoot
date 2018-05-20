package com.piedpiper.platform.api.application;

import com.piedpiper.platform.api.application.dto.SysApplication;
import java.util.List;

public abstract interface SysApplicationAPI
{
  public abstract void reLoad()
    throws Exception;
  
  public abstract SysApplication getCurrentApplication();
  
  public abstract String getCurrentAppId();
  
  public abstract List<SysApplication> getAllSysApplicationList();
  
  public abstract String getAllSysApplicationNameById(String paramString);
  
  public abstract SysApplication getSysApplication(String paramString);
  
  public abstract List<SysApplication> getAllowedSysApplicationByUserId(String paramString);
  
  public abstract String getCurrentAppCode();
}


