 package com.piedpiper.platform.api.syscustomed.dto;
 
 
 public class SysCustomedVo
 {
   private String id;
   
   private String menuStyleUrl;
   private String menuStyleName;
   private String lookupType;
   private String isDefault;
   private String sysUserName;
   private String sysUserId;
   private String sysGroupName;
   private String sysGroupId;
   private String orgId;
   private String isMulti;
   private String userId;
   private String key;
   private String value;
   private String menuImageUrl;
   private String sysAppId;
   private String validFlag;
   
   public String getValidFlag()
   {
     return this.validFlag;
   }
   
   public void setValidFlag(String validFlag) { this.validFlag = validFlag; }
   
   public String getValue() {
     return this.value;
   }
   
   public void setValue(String value) { this.value = value; }
   
   public String getMenuStyleUrl() {
     return this.menuStyleUrl;
   }
   
   public void setMenuStyleUrl(String menuStyleUrl) { this.menuStyleUrl = menuStyleUrl; }
   
   public String getMenuStyleName() {
     return this.menuStyleName;
   }
   
   public void setMenuStyleName(String menuStyleName) { this.menuStyleName = menuStyleName; }
   
   public String getLookupType() {
     return this.lookupType;
   }
   
   public void setLookupType(String lookupType) { this.lookupType = lookupType; }
   
   public String getIsDefault() {
     return this.isDefault;
   }
   
   public void setIsDefault(String isDefault) { this.isDefault = isDefault; }
   
   public String getId() {
     return this.id;
   }
   
   public void setId(String id) { this.id = id; }
   
   public String getSysUserName() {
     return this.sysUserName;
   }
   
   public void setSysUserName(String sysUserName) { this.sysUserName = sysUserName; }
   
   public String getSysUserId() {
     return this.sysUserId;
   }
   
   public void setSysUserId(String sysUserId) { this.sysUserId = sysUserId; }
   
   public String getSysGroupName() {
     return this.sysGroupName;
   }
   
   public void setSysGroupName(String sysGroupName) { this.sysGroupName = sysGroupName; }
   
   public String getSysGroupId() {
     return this.sysGroupId;
   }
   
   public void setSysGroupId(String sysGroupId) { this.sysGroupId = sysGroupId; }
   
   public String getOrgId() {
     return this.orgId;
   }
   
   public void setOrgId(String orgId) { this.orgId = orgId; }
   
   public String getIsMulti() {
     return this.isMulti;
   }
   
   public void setIsMulti(String isMulti) { this.isMulti = isMulti; }
   
   public String getUserId() {
     return this.userId;
   }
   
   public void setUserId(String userId) { this.userId = userId; }
   
   public String getKey() {
     return this.key;
   }
   
   public void setKey(String key) { this.key = key; }
   
   public String getMenuImageUrl() {
     return this.menuImageUrl;
   }
   
   public void setMenuImageUrl(String menuImageUrl) { this.menuImageUrl = menuImageUrl; }
   
   public String getSysAppId() {
     return this.sysAppId;
   }
   
   public void setSysAppId(String sysAppId) { this.sysAppId = sysAppId; }
 }


