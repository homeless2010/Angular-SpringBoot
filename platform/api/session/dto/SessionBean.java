 package com.piedpiper.platform.api.session.dto;
 
 import com.piedpiper.platform.api.sysuser.dto.SysDept;
 import com.piedpiper.platform.api.sysuser.dto.SysUser;
 import java.io.Serializable;
 import java.util.Locale;
 
 
 
 
 public class SessionBean
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   public String clientIp;
   public String loginName;
   public String applicationId;
   public String currentLanguageCode;
   public String systemDefaultLanguageCode;
   public String currentUserLanguageCode;
   public String loginSysUserId;
   public String currentOrgId;
   public String currentDeptId;
   public boolean sanyuanLogin;
   public String appRealPath;
   public SecurityUser securityUser;
   public Locale localeByUser;
   public SysUser loginSysUser;
   public SysDept currentDept;
   
   public String getClientIp()
   {
     return this.clientIp;
   }
   
   public void setClientIp(String clientIp) { this.clientIp = clientIp; }
   
   public String getLoginName() {
     return this.loginName;
   }
   
   public void setLoginName(String loginName) { this.loginName = loginName; }
   
   public String getApplicationId() {
     return this.applicationId;
   }
   
   public void setApplicationId(String applicationId) { this.applicationId = applicationId; }
   
   public String getCurrentLanguageCode() {
     return this.currentLanguageCode;
   }
   
   public void setCurrentLanguageCode(String currentLanguageCode) { this.currentLanguageCode = currentLanguageCode; }
   
   public String getSystemDefaultLanguageCode() {
     return this.systemDefaultLanguageCode;
   }
   
   public void setSystemDefaultLanguageCode(String systemDefaultLanguageCode) { this.systemDefaultLanguageCode = systemDefaultLanguageCode; }
   
   public String getCurrentUserLanguageCode() {
     return this.currentUserLanguageCode;
   }
   
   public void setCurrentUserLanguageCode(String currentUserLanguageCode) { this.currentUserLanguageCode = currentUserLanguageCode; }
   
   public String getLoginSysUserId() {
     return this.loginSysUserId;
   }
   
   public void setLoginSysUserId(String loginSysUserId) { this.loginSysUserId = loginSysUserId; }
   
   public String getCurrentOrgId() {
     return this.currentOrgId;
   }
   
   public void setCurrentOrgId(String currentOrgId) { this.currentOrgId = currentOrgId; }
   
   public String getCurrentDeptId() {
     return this.currentDeptId;
   }
   
   public void setCurrentDeptId(String currentDeptId) { this.currentDeptId = currentDeptId; }
   
   public String getAppRealPath()
   {
     return this.appRealPath;
   }
   
   public void setAppRealPath(String appRealPath) { this.appRealPath = appRealPath; }
   
 
 
 
   public SysUser getLoginSysUser()
   {
     return this.loginSysUser;
   }
   
   public void setLoginSysUser(SysUser loginSysUser) { this.loginSysUser = loginSysUser; }
   
   public boolean isSanyuanLogin()
   {
     return this.sanyuanLogin;
   }
   
   public void setSanyuanLogin(boolean sanyuanLogin) { this.sanyuanLogin = sanyuanLogin; }
   
   public SecurityUser getSecurityUser() {
     return this.securityUser;
   }
   
   public void setSecurityUser(SecurityUser securityUser) { this.securityUser = securityUser; }
   
   public Locale getLocaleByUser() {
     return this.localeByUser;
   }
   
   public void setLocaleByUser(Locale localeByUser) { this.localeByUser = localeByUser; }
   
   public SysDept getCurrentDept() {
     return this.currentDept;
   }
   
   public void setCurrentDept(SysDept currentDept) { this.currentDept = currentDept; }
 }


