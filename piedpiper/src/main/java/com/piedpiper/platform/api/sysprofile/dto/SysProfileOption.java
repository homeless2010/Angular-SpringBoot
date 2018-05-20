 package com.piedpiper.platform.api.sysprofile.dto;
 
 import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.piedpiper.platform.core.domain.BeanBase;
import com.piedpiper.platform.core.properties.PlatformConstant;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheBean;
 
 
 
 
 
 
 
 
 public class SysProfileOption
   extends BeanBase
   implements BaseCacheBean
 {
   private static final long serialVersionUID = 1L;
   public static final String PROFILEOPTION = "PLATFORM6_PROFILEOPTION";
   public static final String PROFILEOPTIONCODE = "PLATFORM6_PROFILEOPTION_CODE_";
   private String id;
   private String sysApplicationId;
   private String profileOptionCode;
   private String profileOptionName;
   private String description;
   private String ynMultiValue;
   private String userChangeableFlag;
   private String userVisibleFlag;
   private String siteEnabledFlag;
   private String appEnabledFlag;
   private String roleEnabledFlag;
   private String userEnabledFlag;
   private String deptEnabledFlag;
   private String sqlValidation;
   private String validFlag;
   private String systemFlag;
   private String usageModifier;
   private Collection<SysProfileOptionValue> sysProfileOptionValue;
   private static final String pattern = "[id=%s],[应用id=%s],[配置文件名称=%s],[配置文件code=%s]";
   
   public String getUsageModifier()
   {
     return this.usageModifier;
   }
   
   public void setUsageModifier(String usageModifier) {
     this.usageModifier = usageModifier;
   }
   
 
   public Collection<SysProfileOptionValue> getSysProfileOptionValue()
   {
     return this.sysProfileOptionValue;
   }
   
   public void setSysProfileOptionValue(Collection<SysProfileOptionValue> sysProfileOptionValue)
   {
     this.sysProfileOptionValue = sysProfileOptionValue;
   }
   
   public String getId() {
     return this.id;
   }
   
   public void setId(String id) {
     this.id = id;
   }
   
   public String getSysApplicationId() {
     return this.sysApplicationId;
   }
   
   public void setSysApplicationId(String sysApplicationId) {
     this.sysApplicationId = sysApplicationId;
   }
   
   public String getProfileOptionCode() {
     return this.profileOptionCode;
   }
   
   public void setProfileOptionCode(String profileOptionCode) {
     this.profileOptionCode = profileOptionCode;
   }
   
   public String getProfileOptionName() {
     return this.profileOptionName;
   }
   
   public void setProfileOptionName(String profileOptionName) {
     this.profileOptionName = profileOptionName;
   }
   
   public String getDescription() {
     return this.description;
   }
   
   public void setDescription(String description) {
     this.description = description;
   }
   
   public String getYnMultiValue() {
     return this.ynMultiValue;
   }
   
   public void setYnMultiValue(String ynMultiValue) {
     this.ynMultiValue = ynMultiValue;
   }
   
   public String getUserChangeableFlag() {
     return this.userChangeableFlag;
   }
   
   public void setUserChangeableFlag(String userChangeableFlag) {
     this.userChangeableFlag = userChangeableFlag;
   }
   
   public String getUserVisibleFlag() {
     return this.userVisibleFlag;
   }
   
   public void setUserVisibleFlag(String userVisibleFlag) {
     this.userVisibleFlag = userVisibleFlag;
   }
   
   public String getSiteEnabledFlag() {
     return this.siteEnabledFlag;
   }
   
   public void setSiteEnabledFlag(String siteEnabledFlag) {
     this.siteEnabledFlag = siteEnabledFlag;
   }
   
   public String getAppEnabledFlag() {
     return this.appEnabledFlag;
   }
   
   public void setAppEnabledFlag(String appEnabledFlag) {
     this.appEnabledFlag = appEnabledFlag;
   }
   
   public String getRoleEnabledFlag() {
     return this.roleEnabledFlag;
   }
   
   public void setRoleEnabledFlag(String roleEnabledFlag) {
     this.roleEnabledFlag = roleEnabledFlag;
   }
   
   public String getUserEnabledFlag() {
     return this.userEnabledFlag;
   }
   
   public void setUserEnabledFlag(String userEnabledFlag) {
     this.userEnabledFlag = userEnabledFlag;
   }
   
   public String getDeptEnabledFlag() {
     return this.deptEnabledFlag;
   }
   
   public void setDeptEnabledFlag(String deptEnabledFlag) {
     this.deptEnabledFlag = deptEnabledFlag;
   }
   
   public String getSqlValidation() {
     return this.sqlValidation;
   }
   
   public void setSqlValidation(String sqlValidation) {
     this.sqlValidation = sqlValidation;
   }
   
   public String getValidFlag() {
     return this.validFlag;
   }
   
   public void setValidFlag(String validFlag) {
     this.validFlag = validFlag;
   }
   
   public String getSystemFlag() {
     return this.systemFlag;
   }
   
   public void setSystemFlag(String systemFlag) {
     this.systemFlag = systemFlag;
   }
   
   public String getLogFormName() {
     if ((this.logFormName == null) || (this.logFormName.equals(""))) {
       return "系统参数配置";
     }
     return this.logFormName;
   }
   
   public String getLogTitle()
   {
     if ((this.logTitle == null) || (this.logTitle.equals(""))) {
       return "SYSPROFILEOPTION";
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
   
 
   public Map<String, ?> returnCacheKey()
   {
     Map<String, Object> map = new HashMap();
     map.put("PLATFORM6_PROFILEOPTION", this.id);
     map.put("PLATFORM6_PROFILEOPTION_CODE_" + this.profileOptionCode, this.id);
     return map;
   }
   
   public String prefix()
   {
     return "PLATFORM6_PROFILEOPTION";
   }
   
   public String returnValidFlag()
   {
     return this.validFlag;
   }
   
   public String returnId()
   {
     return this.id;
   }
   
   public String returnAppId()
   {
     if ("0".equals(this.usageModifier)) {
       return null;
     }
     return this.sysApplicationId;
   }
   
 
   public String returnLogAppId()
   {
     return this.sysApplicationId;
   }
   
 
 
 
 
   public String toString()
   {
     return String.format("[id=%s],[应用id=%s],[配置文件名称=%s],[配置文件code=%s]", new Object[] { this.id, this.sysApplicationId, this.profileOptionName, this.profileOptionCode });
   }
   
   public String returnKey() {
     return this.id;
   }
 }


