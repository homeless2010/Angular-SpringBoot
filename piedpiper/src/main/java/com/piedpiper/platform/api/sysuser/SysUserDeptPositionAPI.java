package com.piedpiper.platform.api.sysuser;

import com.piedpiper.platform.api.sysuser.dto.SysDept;
import com.piedpiper.platform.api.sysuser.dto.SysPosition;
import com.piedpiper.platform.api.sysuser.dto.SysUser;
import com.piedpiper.platform.api.sysuser.dto.SysUserDeptPosition;
import java.util.List;
import java.util.Map;

public abstract interface SysUserDeptPositionAPI
{
  public abstract List<List<Object>> getDeptAndPositionListBySysUserId(String paramString);
  
  public abstract SysUserDeptPosition getChiefUDPRelationBySysUserId(String paramString);
  
  public abstract List<Object> getChiefDeptAndChiefPositionBySysUserId(String paramString);
  
  public abstract SysPosition getChiefPositionBySysUserId(String paramString);
  
  public abstract String getChiefPositionCodeBySysUserId(String paramString);
  
  public abstract SysDept getChiefDeptBySysUserId(String paramString);
  
  public abstract String getChiefDeptIdBySysUserId(String paramString);
  
  public abstract String getCurrentDeptManagerStr(String paramString1, String paramString2);
  
  public abstract boolean isCurrentDeptManager(String paramString1, String paramString2);
  
  public abstract List<SysUser> getSysUserListBySysDeptId(String paramString, boolean paramBoolean);
  
  public abstract List<SysUser> getSysUserListBySysDeptId(String paramString, boolean paramBoolean, int paramInt);
  
  public abstract List<SysUser> getSysUserListBySysDeptIdAndSysPositionCode(String paramString1, String paramString2);
  
  public abstract List<SysUser> getSysUserListBySysDeptIdAndSysPositionCode(String paramString1, String paramString2, int paramInt);
  
  public abstract List<SysUser> getSysUserListBySysDeptIdAndSysPositionId(String paramString1, String paramString2);
  
  public abstract List<SysUser> getSysUserListBySysDeptIdAndSysPositionId(String paramString1, String paramString2, int paramInt);
  
  public abstract List<SysPosition> getSysPositionListBySysUserId(String paramString);
  
  public abstract List<SysDept> getSysDeptListBySysUserId(String paramString);
  
  public abstract boolean isHaveThisPosition(String paramString1, String paramString2);
  
  public abstract List<SysUser> getSysUserListBySysPositionCode(String paramString);
  
  public abstract List<SysUser> getSysUserListBySysPositionCode(String paramString, int paramInt);
  
  public abstract List<SysUser> getSysUserListBySysPositionId(String paramString);
  
  public abstract List<SysUser> getSysUserListBySysPositionId(String paramString, int paramInt);
  
  public abstract String getSysUserStrBySysPositionCode(String paramString1, String paramString2);
  
  public abstract SysUserDeptPosition getModelByUserIdAndDeptId(String paramString1, String paramString2);
  
  public abstract SysUserDeptPosition getSysUserDeptPositionById(String paramString);
  
  public abstract SysPosition getSysPositionBySysUserIdAndSysDeptId(String paramString1, String paramString2);
  
  public abstract Map<String, List<SysUser>> getAllSysUserByDeptList(List<String> paramList);
  
  public abstract void insertSysUserDeptPosition(SysUserDeptPosition paramSysUserDeptPosition);
  
  public abstract void updateSysUserDeptPosition(SysUserDeptPosition paramSysUserDeptPosition);
  
  public abstract void deleteSysUserDeptPosition(SysUserDeptPosition paramSysUserDeptPosition);
}


