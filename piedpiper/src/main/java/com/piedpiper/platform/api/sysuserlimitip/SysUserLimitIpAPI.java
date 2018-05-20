package com.piedpiper.platform.api.sysuserlimitip;

import com.piedpiper.platform.api.sysuserlimitip.dto.SysUserLimitIp;
import java.util.List;
import java.util.Map;

public abstract interface SysUserLimitIpAPI
{
  public abstract void reLoad()
    throws Exception;
  
  public abstract List<String> findSecretLevel(String paramString);
  
  public abstract List<String> finduserLimitUserId(String paramString1, String paramString2, String paramString3);
  
  public abstract List<Object> findlimitTypeIpType(String paramString1, String paramString2);
  
  public abstract List<Object> finduserLimitUserIdObject(String paramString1, String paramString2, String paramString3);
  
  public abstract boolean checkCurrentBetweenIpSectionList(List<Object> paramList, String paramString);
  
  public abstract Map<String, List<SysUserLimitIp>> finduserLimitByUserId(String paramString);
}


