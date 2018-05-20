package com.piedpiper.platform.api.sysuser;

import com.piedpiper.platform.api.sysuser.dto.SysGroup;
import com.piedpiper.platform.api.sysuser.dto.SysGroupTl;
import com.piedpiper.platform.api.sysuser.dto.SysGroupVo;
import com.piedpiper.platform.core.dao.Paging;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract interface SysGroupAPI
{
  public static final String LONG = "L";
  public static final String INTEGER = "I";
  public static final String STRING = "S";
  public static final String DATE = "D";
  
  public abstract List<SysGroup> getAllSysGroupList();
  
  @Deprecated
  public abstract List<SysGroup> getAllSysGroupListByAppId(String paramString);
  
  public abstract SysGroupTl getSysGroupTl(String paramString1, String paramString2);
  
  public abstract SysGroup getSysGroupBySysGroupId(String paramString);
  
  public abstract String getSysGroupNameBySysGroupId(String paramString1, String paramString2);
  
  public abstract String getSysGroupNamesBySysGroupIds(String paramString1, String paramString2, String paramString3);
  
  public abstract List<SysGroup> getSubSysGroupListBySysOrgId(String paramString);
  
  public abstract List<SysGroup> getSubSysGroupListBySysGroupId(String paramString);
  
  public abstract List<SysGroup> getUserGroupListByUserId(String paramString);
  
  public abstract SysGroup getParentSysGroupBySysGroupId(String paramString);
  
  public abstract String getParentSysGroupIdBySysGroupId(String paramString);
  
  public abstract List<SysGroup> getAllSubSysGroupListBySysGroupId(String paramString);
  
  public abstract String getAllSubSysGroupIdBySysGroupId(String paramString1, String paramString2);
  
  public abstract List<SysGroup> getAllParentSysGroupListBySysGroupId(String paramString);
  
  public abstract String getAllParentSysGroupIdBySysGroupId(String paramString1, String paramString2);
  
  public abstract SysGroupTl getSysGroupTlWithoutLanguageCode(String paramString);
  
  public abstract void insertUserGroupByIDs(String paramString1, String paramString2);
  
  public abstract void insertUserGroupByIDs(List<String> paramList, String paramString);
  
  public abstract void insertSysGroup(SysGroup paramSysGroup);
  
  public abstract void insertSysGroupTl(SysGroupTl paramSysGroupTl);
  
  public abstract void updateSysGroupTl(SysGroupTl paramSysGroupTl);
  
  public abstract void deleteUserGroupByPersonalGroupId(String paramString1, String paramString2);
  
  public abstract void deleteById(String paramString);
  
  public abstract void deleteTlById(String paramString);
  
  public abstract Long getTotalCountRecord(String paramString, HashMap<String, Object> paramHashMap, HashMap<String, String> paramHashMap1);
  
  public abstract List<Object[]> getGroupInfoBySQL(Paging<SysGroupVo> paramPaging, String paramString, HashMap<String, Object> paramHashMap, HashMap<String, Class> paramHashMap1);
  
  public abstract String buildSqlByCondition(String paramString, Map<String, Object> paramMap, HashMap<String, Object> paramHashMap);
}


