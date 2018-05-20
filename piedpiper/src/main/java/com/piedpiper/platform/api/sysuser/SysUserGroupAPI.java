package com.piedpiper.platform.api.sysuser;

import com.piedpiper.platform.api.sysuser.dto.SysGroup;
import com.piedpiper.platform.api.sysuser.dto.SysUser;
import java.util.List;

public abstract interface SysUserGroupAPI
{
  public abstract List<SysUser> getSysUserListBySysGroupId(String paramString);
  
  public abstract List<SysUser> getSysUserListBySysGroupId(String paramString, int paramInt);
  
  public abstract List<SysGroup> getSysGroupListBySysUserId(String paramString);
}


