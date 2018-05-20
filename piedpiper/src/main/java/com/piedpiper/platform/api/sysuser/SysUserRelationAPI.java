package com.piedpiper.platform.api.sysuser;

import com.piedpiper.platform.api.sysuser.dto.SysUser;
import com.piedpiper.platform.api.sysuser.dto.SysUserRelation;
import java.util.List;

public abstract interface SysUserRelationAPI
{
  public abstract List<SysUser> getSysUser2ListByUser1IdAndRelation(String paramString1, String paramString2, String paramString3);
  
  public abstract List<SysUser> getSysUser1ListByUser2IdAndRelation(String paramString1, String paramString2, String paramString3);
  
  public abstract SysUserRelation getSysUserRelationByUser1IdAndUser2Id(String paramString1, String paramString2, String paramString3);
  
  public abstract SysUserRelation getSysUserRelationById(String paramString);
}


