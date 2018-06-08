 package com.piedpiper.platform.api.sysshirolog.impl;
 
 import com.piedpiper.platform.api.application.SysApplicationAPI;
 import com.piedpiper.platform.api.application.dto.SysApplication;
 import com.piedpiper.platform.api.portal.SysPortalAPI;
 import com.piedpiper.platform.api.portal.dto.SysPortletConfig;
 import com.piedpiper.platform.api.portal.utils.SysPortletUtil;
 import com.piedpiper.platform.api.session.dto.SecurityUser;
 import com.piedpiper.platform.api.syscustomed.SysCustomedAPI;
 import com.piedpiper.platform.api.syscustomed.dto.SysCustomed;
 import com.piedpiper.platform.api.syslanguage.SysLanguageAPI;
 import com.piedpiper.platform.api.sysprofile.SysProfileAPI;
 import com.piedpiper.platform.api.sysshirolog.context.ContextHolder;
 import com.piedpiper.platform.api.sysshirolog.context.FrameworkContext;
 import com.piedpiper.platform.api.sysshirolog.utils.HttpRequestResponseHolder;
 import com.piedpiper.platform.api.sysuser.SysUserDeptPositionAPI;
 import com.piedpiper.platform.api.sysuser.SysUserRoleAPI;
 import com.piedpiper.platform.api.sysuser.dto.SysDept;
 import com.piedpiper.platform.api.sysuser.dto.SysRole;
 import com.piedpiper.platform.api.sysuser.dto.SysUser;
 import com.piedpiper.platform.api.sysuser.impl.SysDeptAPImpl;
 import com.piedpiper.platform.core.exception.LoginException;
 import com.piedpiper.platform.core.filter.session.LoginSessionInfo;
 import com.piedpiper.platform.core.locale.PlatformLocalesJSTL;
 import com.piedpiper.platform.core.rest.client.RestClientConfig;
 import java.text.MessageFormat;
 import java.util.List;
 import java.util.Locale;
 import javax.servlet.http.Cookie;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import javax.servlet.http.HttpSession;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import org.springframework.web.servlet.i18n.SessionLocaleResolver;
 
 
 
 
 
 
 
 
 
 
 
 
 
 @Service
 public class AfterLoginSessionProcess
 {
   public static final String SESSION_CURRENT_DEPT = "CURRENT_DEPT";
   public static final String SESSION_CURRENT_DEPT_TL = "CURRENT_DEPT_TL";
   public static final String SESSION_CURRENT_USER = "CURRENT_LOGINUSER";
   public static final String SESSION_APPLICATIONID = "CURRENT_APPLICATIONIDR";
   public static final String SESSION_IP = "CURRENT_IP";
   public static final String SESSION_CURRENT_LANGUAGE_CODE = "CURRENT_LANGUAGE";
   public static final String SESSION_CURRENT_DEPT_ID = "deptId";
   public static final String SESSION_CURRENT_USER_ID = "userId";
   public static final String SESSION_CURRENT_USER_SKIN = "userSkin";
   public static final String SESSION_CURRENT_SKIN_SWITCH = "userSkinSwitch";
   public static final String SESSION_CURRENT_LOGINUSER_SECURITY = "CURRENT_LOGINUSER_SECURITY";
   public static final String LOGIN_SUCCESS_NEXT_URL = "LOGINSUCCESSNEXTURL_";
   public static final String SESSION_PORTLET_MENU = "PORTLET_MENU";
   public static final String SESSION_IS_ADMIN = "USER_ADMIN";
   private static final String _k = "k";
   @Autowired
   private SysUserDeptPositionAPI sysUserDeptPositionLoader;
   @Autowired
   private SysLanguageAPI sysLanguageLoader;
   @Autowired
   private SysProfileAPI sysProfileAPI;
   @Autowired
   private SysCustomedAPI sysCustomedAPI;
   @Autowired
   private SysApplicationAPI sysAppAPI;
   @Autowired(required=true)
   private SysDeptAPImpl sysDeptAPI;
   @Autowired
   private SysUserRoleAPI sysUserRoleAPI;
   @Autowired
   private SysPortalAPI sysPortalAPI;
   
   public void afterLogin(HttpRequestResponseHolder holder)
     throws Exception
   {
     HttpServletRequest request = holder.getRequest();
     String appId = RestClientConfig.systemid;
     
     SecurityUser securityUser = ContextHolder.getContext().getLoginUser();
     
     SysUser sysUser = securityUser.getUser();
     
     String ip = LogUserHolder.getLoginIp();
     securityUser.setCurrentLoginIp(ip);
     securityUser.setApplicationId(appId);
     
     String lan_code = LogUserHolder.getLanguageCode();
     Locale locale = org.springframework.util.StringUtils.parseLocaleString(lan_code);
     
     SysDept sysDept = null;
     
 
 
 
     sysDept = this.sysUserDeptPositionLoader.getChiefDeptBySysUserId(sysUser.getId());
     
     if (sysDept == null) {
       throw new LoginException(MessageFormat.format(PlatformLocalesJSTL.getBundleValue("SecurityLogServiceImpl.login.dept.unvalid", locale), new Object[] { sysUser.getLoginName() }));
     }
     
 
 
 
     sysUser.setDeptId(sysDept.getId());
     sysUser.setDeptCode(sysDept.getDeptCode());
     String deptName = this.sysDeptAPI.getSysDeptNameBySysDeptId(sysDept.getId(), this.sysLanguageLoader.getSystemDefaultLanguageCode());
     sysUser.setDeptName(deptName);
     sysUser.setLastLoginDeptId(sysDept.getId());
     
 
 
     LoginSessionInfo info = new LoginSessionInfo();
     info.put("CURRENT_LANGUAGE", lan_code);
     info.put("userId", sysUser.getId());
     info.put("CURRENT_LOGINUSER", sysUser);
     info.put("CURRENT_LOGINUSER_SECURITY", securityUser);
     info.put("CURRENT_APPLICATIONIDR", appId);
     info.put("CURRENT_IP", ip);
     info.put("deptId", sysDept.getId());
     info.put("CURRENT_DEPT", sysDept);
     info.put("CURRENT_DEPT_TL", deptName);
     info.put(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
     
     info.put("USER_ADMIN", Boolean.valueOf(LogUserHolder.isAdmin()));
     
     String defaultSkin = this.sysProfileAPI.getProfileValueByCodeByAppId("PLATFORM_V6_DEFAULT_SKIN", appId);
     
     String defaultSkinSwitch = this.sysProfileAPI.getProfileValueByCodeByAppId("PLATFORM_V6_DEFAULT_SKIN_SWITCH", appId);
     
 
     if (Boolean.valueOf(defaultSkinSwitch).booleanValue())
     {
       String userSkin = this.sysCustomedAPI.getCustomesSkin(sysUser.getId(), appId);
       
       if (org.apache.commons.lang3.StringUtils.isEmpty(userSkin)) {
         userSkin = defaultSkin;
       }
       info.put("userSkin", userSkin);
       info.put("userSkinSwitch", Boolean.TRUE.toString());
     } else {
       info.put("userSkin", defaultSkin);
       info.put("userSkinSwitch", Boolean.FALSE.toString());
     }
     
     SysCustomed sysCustomed = this.sysCustomedAPI.getSysCustomedByUserIdAndKey(sysUser.getId(), "PLATFORM_MENU_STYLE", appId);
     if ((sysCustomed != null) && (!org.apache.commons.lang3.StringUtils.isEmpty(sysCustomed.getValue()))) {
       request.setAttribute("LOGINSUCCESSNEXTURL_", sysCustomed.getValue());
       info.put("LOGINSUCCESSNEXTURL_", sysCustomed.getValue());
     } else {
       List<SysRole> currentUserSysRoles = this.sysUserRoleAPI.getSysRoleListBySysUserId(sysUser.getId(), RestClientConfig.systemid);
       List<SysPortletConfig> portletConfigList = this.sysPortalAPI.getList(RestClientConfig.systemid);
       SysPortletConfig myRoleConfig = SysPortletUtil.getInstance().getSysPortletConfig(portletConfigList, currentUserSysRoles);
       if ((myRoleConfig == null) || (myRoleConfig.getIndexUrl() == null)) {
         String defalutUrlCode = this.sysAppAPI.getCurrentApplication().getApplicationCode() + "_DEFALUT_URL";
         if (!this.sysProfileAPI.containsOptionCode(defalutUrlCode)) {
           defalutUrlCode = "PLATFORM_V6_DEFALUT_URL";
         }
         String defaultUrl = this.sysProfileAPI.getProfileValueByCodeByAppId(defalutUrlCode, appId);
         if (!org.apache.commons.lang3.StringUtils.isEmpty(defaultUrl)) {
           request.setAttribute("LOGINSUCCESSNEXTURL_", defaultUrl);
           info.put("LOGINSUCCESSNEXTURL_", defaultUrl);
         } else {
           throw new LoginException("系统默认首页路径丢失，请在系统参数配置添加【PLATFORM_V6_DEFALUT_URL】的值！");
         }
       } else {
         request.setAttribute("LOGINSUCCESSNEXTURL_", myRoleConfig.getIndexUrl());
         info.put("LOGINSUCCESSNEXTURL_", myRoleConfig.getIndexUrl());
         if ("1".equals(myRoleConfig.getIsUse())) {
           info.put("PORTLET_MENU", myRoleConfig.getId());
         }
       }
     }
     HttpSession session = holder.getRequest().getSession();
     info.put("isLogin", "t");
     session.setAttribute("k", info);
     Cookie cookie = new Cookie("P_L_CODE", lan_code);
     cookie.setMaxAge(-1);
     cookie.setPath("/");
     holder.getResponse().addCookie(cookie);
   }
 }


