package com.piedpiper.platform.api.sysuser;

import com.piedpiper.platform.api.sysuser.dto.SysRole;
import com.piedpiper.platform.api.sysuser.dto.SysUser;
import com.piedpiper.platform.api.sysuser.dto.SysUserRole;
import com.piedpiper.platform.core.properties.PlatformConstant.FixedRole;
import com.piedpiper.platform.core.properties.PlatformConstant.LogType;
import java.util.List;

public abstract interface SysUserRoleAPI
{
  public abstract List<SysUser> getSysUserListBySysRoleId(String paramString);
  
  public abstract List<SysUser> getSysUserListBySysRoleId(String paramString, int paramInt);
  
  public abstract List<SysUser> getSysUserListBySysRoleCode(String paramString);
  
  public abstract List<SysUser> getSysUserListBySysRoleCode(String paramString, int paramInt);
  
  public abstract List<SysRole> getSysRoleListBySysUserId(String paramString);
  
  public abstract List<SysRole> getSysRoleListBySysUserId(String paramString1, String paramString2);
  
  public abstract List<SysRole> getSysRoleListBySysUserIdNoAppId(String paramString);
  
  public abstract List<SysUserRole> getAllSysUserRole();
  
  public abstract void insertSysUserRole(SysUserRole paramSysUserRole);
  
  public abstract void updateSysUserRole(SysUserRole paramSysUserRole);
  
  public abstract void deleteSysUserRole(SysUserRole paramSysUserRole);
  
  public abstract boolean isHaveFiexedRole(String paramString, PlatformConstant.FixedRole paramFixedRole);
  
  public abstract PlatformConstant.LogType genLogTypeByUserId(String paramString1, String paramString2);
}


