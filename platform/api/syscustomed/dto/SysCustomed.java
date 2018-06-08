 package com.piedpiper.platform.api.syscustomed.dto;
 
 import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.piedpiper.platform.core.domain.BeanDTO;
import com.piedpiper.platform.core.properties.PlatformConstant;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheBean;
 
 public class SysCustomed extends BeanDTO implements BaseCacheBean
 {
   private static final long serialVersionUID = 1L;
   private String id;
   private String orgId;
   private String userId;
   private String key;
   private String value;
   private String isMulti;
   private String isDefault;
   private String sysAppId;
   public static final String SYS_CUSTOMED = "PLATFORM6_SYS_CUSTOMED";
   public static final String SYS_CUSTOMED_USERID_KEY = "PLATFORM6_SYS_CUSTOMED_ORGID_USERID_KEY";
   public static final String SYS_CUSTOMED_USERID_KEY_VALUE = "PLATFORM6_SYS_CUSTOMED_ORGID_USERID_KEY_VALUE";
   public static final String SYS_CUSTOMED_USERID_KEY_ISMULTI = "PLATFORM6_SYS_CUSTOMED_ORGID_USERID_KEY_ISMULTI";
   private String orgIdentity;
   public static final String USER_SKIN_KEY = "PLATFORM_USER_SKIN";
   public static final String USER_LANGUAGE_KEY = "PLATFORM_USER_LANGUAGE";
   public static final String SYSCUSTOMED_PERSONALGROUP = "PLATFORM_PERSONALGROUP";
   public static final String SYSCUSTOMED_APPROVALOPTION = "PLATFORM_APPROVALOPTION";
   public static final String SYSCUSTOME_DEFAULT = "1";
   public static final String SYSCUSTOME_NOT_DEFAULT = "0";
   
   @JsonIgnore
   public String getOrgIdentity()
   {
     return this.orgIdentity;
   }
   
   public void setOrgIdentity(String orgIdentity) {
     this.orgIdentity = orgIdentity;
   }
   
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
   public String getId()
   {
     return this.id;
   }
   
   public void setId(String id) {
     this.id = id;
   }
   
   public String getSysAppId() {
     return this.sysAppId;
   }
   
   public void setSysAppId(String sysAppId) {
     this.sysAppId = sysAppId;
   }
   
   public String getOrgId() {
     return this.orgId;
   }
   
   public void setOrgId(String orgId) {
     this.orgId = orgId;
   }
   
   public String getUserId() {
     return this.userId;
   }
   
   public void setUserId(String userId) {
     this.userId = userId;
   }
   
   public String getKey() { return this.key; }
   
   public void setKey(String key)
   {
     this.key = key;
   }
   
   public String getValue() { return this.value; }
   
   public void setValue(String value)
   {
     this.value = value;
   }
   
   public String getIsMulti() {
     return this.isMulti;
   }
   
   public void setIsMulti(String isMulti) {
     this.isMulti = isMulti;
   }
   
   public String getIsDefault() {
     return this.isDefault;
   }
   
   public void setIsDefault(String isDefault) {
     this.isDefault = isDefault;
   }
   
   public String getLogFormName() {
     if ((this.logFormName == null) || (this.logFormName.equals(""))) {
       return "用户惯用设置模块";
     }
     return this.logFormName;
   }
   
   public String getLogTitle()
   {
     if ((this.logTitle == null) || (this.logTitle.equals(""))) {
       return "SYSCUSTOMED";
     }
     return this.logTitle;
   }
   
   public PlatformConstant.LogType getLogType()
   {
     if ((this.logType == null) || (this.logType.equals(""))) {
       return PlatformConstant.LogType.system_manage;
     }
     return this.logType;
   }
   
 
   public String returnId()
   {
     return this.id;
   }
   
   public String returnValidFlag()
   {
     return null;
   }
   
   public Map<String, ?> returnCacheKey()
   {
     Map<String, Object> map = new HashMap();
     map.put("PLATFORM6_SYS_CUSTOMED", this.id);
     map.put("PLATFORM6_SYS_CUSTOMED_ORGID_USERID_KEY" + this.userId, this.key);
     map.put("PLATFORM6_SYS_CUSTOMED_ORGID_USERID_KEY_VALUE" + this.userId + "_" + this.key + "_" + this.value, this.id);
     map.put("PLATFORM6_SYS_CUSTOMED_ORGID_USERID_KEY_ISMULTI" + this.userId + "_" + this.key + "_" + this.isMulti, this.id);
     return map;
   }
   
   public String prefix()
   {
     return "PLATFORM6_SYS_CUSTOMED";
   }
   
   public String returnAppId()
   {
     return this.sysAppId;
   }
 }


