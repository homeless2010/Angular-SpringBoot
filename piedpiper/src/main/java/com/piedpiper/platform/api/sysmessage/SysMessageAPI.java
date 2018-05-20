package com.piedpiper.platform.api.sysmessage;

import com.piedpiper.platform.api.sysmessage.dto.SysMessage;
import java.util.List;

public abstract interface SysMessageAPI
{
  public abstract void reLoad()
    throws Exception;
  
  public abstract int getSysMessageCount(String paramString);
  
  public abstract List<SysMessage> getNoReadSysMessageByUserId(String paramString);
  
  public abstract SysMessage getSysMessageById(String paramString);
  
  public abstract void insertOrUpdateSysMessagesJSP(List<SysMessage> paramList, String paramString1, String paramString2);
}


