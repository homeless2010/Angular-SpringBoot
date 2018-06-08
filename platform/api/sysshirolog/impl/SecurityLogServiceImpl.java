 package com.piedpiper.platform.api.sysshirolog.impl;
 
 import com.piedpiper.platform.api.session.SessionHelper;
 import com.piedpiper.platform.api.syscustomed.SysCustomedAPI;
 import com.piedpiper.platform.api.syslanguage.SysLanguageAPI;
 import com.piedpiper.platform.api.syspassword.SysPasswordAPI;
 import com.piedpiper.platform.api.sysprofile.SysProfileAPI;
 import com.piedpiper.platform.api.sysshirolog.utils.HttpRequestResponseHolder;
 import com.piedpiper.platform.api.sysshirolog.utils.SecurityUtil;
 import com.piedpiper.platform.api.sysuser.SysUserAPI;
 import com.piedpiper.platform.api.sysuser.dto.SysUser;
 import com.piedpiper.platform.core.exception.LoginException;
 import com.piedpiper.platform.core.locale.PlatformLocalesJSTL;
 import com.piedpiper.platform.core.rest.client.RestClient;
 import com.piedpiper.platform.core.rest.client.RestClientConfig;
 import com.piedpiper.platform.core.rest.msg.LogBase;
 import com.piedpiper.platform.core.rest.msg.Muti2Bean;
 import com.piedpiper.platform.core.rest.msg.Muti5Bean;
 import com.piedpiper.platform.core.rest.msg.ResponseMsg;
 import com.piedpiper.platform.core.shiroSecurity.contextThread.ContextCommonHolder;
 import java.text.Format;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.Locale;
 import java.util.Map;
 import java.util.concurrent.ExecutorService;
 import java.util.concurrent.Executors;
 import javax.annotation.PreDestroy;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpSession;
 import javax.ws.rs.core.GenericType;
 import org.apache.commons.lang3.time.FastDateFormat;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 @Service
 public class SecurityLogServiceImpl
 {
   private Logger logger;
   
   public SecurityLogServiceImpl() { this.logger = LoggerFactory.getLogger(getClass()); }
   
   private static final Format df = FastDateFormat.getInstance("yyyy/MM/dd HH:mm:ss");
   
   private static final ExecutorService execs = Executors.newFixedThreadPool(5);
   
   @Autowired(required=false)
   private SysProfileAPI sysProfileAPI;
   @Autowired(required=true)
   private SysUserAPI sysUserAPI;
   @Autowired(required=false)
   private SysPasswordAPI sysPasswordAPI;
   @Autowired
   private SysCustomedAPI sysCustomedAPI;
   @Autowired
   private SysLanguageAPI sysLanguageAPI;
   
   @PreDestroy
   public void destroy()
   {
     execs.shutdownNow();
   }
   
 
 
 
   public void afterLogin(HttpRequestResponseHolder holder, boolean isCas)
     throws Exception
   {
     SysUser user = LogUserHolder.getSysUser();
     LogBase lb = new LogBase(RestClientConfig.systemid, user.getId(), user.getName(), LogUserHolder.getLoginIp());
     lb.setCas(isCas);
     _ansyUpdate u = new _ansyUpdate(user, lb, ContextCommonHolder.getCookeMid());
     
     execs.execute(u);
   }
   
   private class _ansyUpdate implements Runnable
   {
     private final SysUser _user;
     private final LogBase lb;
     private final String sid;
     
     public _ansyUpdate(SysUser u, LogBase l, String sid) {
       this._user = u;
       this.lb = l;
       this.sid = sid;
     }
     
 
     public void run()
     {
       Muti2Bean<SysUser, LogBase> args = new Muti2Bean(this._user, this.lb);
       ContextCommonHolder.setCookeMid(this.sid);
       String url = RestClientConfig.getRestHost("syshirosecurity") + "/api/platform6/shiroSecurity/permisstion/afterLogin/v1";
       ResponseMsg<Void> responseMsg = RestClient.doPost(url, args, new GenericType() {});
       if (!"200".equals(responseMsg.getRetCode())) {
         SecurityLogServiceImpl.this.logger.info("url:" + url + ",error:" + responseMsg.getErrorDesc());
       }
     }
   }
   
 
 
 
 
 
 
 
   public void beforeLogin(HttpRequestResponseHolder holder, String userName)
     throws Exception
   {
     this.logger.debug("开始beforeLogin检验@@@@@@@@@@@@@@@@@@@@@@@@@@@登陆名称为" + userName);
     
     SysUser sysUser = this.sysUserAPI.getSysUserByLoginString(userName);
     
 
     if (sysUser == null) {
       throw new LoginException("用户不存在！");
     }
     
     if ("3".equals(sysUser.getStatus())) {
       throw new LoginException("用户" + userName + "是无效用户");
     }
     
     LogUserHolder.addSysUser(sysUser);
     
     String currentIp = SessionHelper.getClientIp(holder.getRequest());
     LogUserHolder.addLoginIp(currentIp);
     
 
     boolean padmi = SecurityUtil.isPlatformAdministratorByUserId(sysUser.getId());
     LogUserHolder.setAdmin(padmi);
     
     Date now = new Date();
     LogUserHolder.addDate(now);
     sysUser.setLastLoginDate(now);
     String languageCode = getUserLanguageCode(sysUser);
     LogUserHolder.addLanguageCode(languageCode);
     
     if (padmi) {
       return;
     }
     Locale locale = org.springframework.util.StringUtils.parseLocaleString(languageCode);
     
     String registerFlag = this.sysProfileAPI.getProfileValueByCodeByAppId("PLATFORM_V6_REGISTER_ENABLED", RestClientConfig.systemid);
     if (Boolean.valueOf(registerFlag).booleanValue())
     {
       if ("9".equals(sysUser.getUserLocked()))
         throw new LoginException(PlatformLocalesJSTL.getBundleValue("SecurityLogServiceImpl.register.unsuccess", locale));
       if ("8".equals(sysUser.getUserLocked())) {
         throw new LoginException(PlatformLocalesJSTL.getBundleValue("SecurityLogServiceImpl.register.failed", locale));
       }
     }
     
 
     String userLock = sysUser.getUserLocked();
     if (!"0".equals(userLock)) {
       Map<String, String> map = this.sysPasswordAPI.getSysPasswordTempletByKey(sysUser.getSecretLevel(), new String[] { "howTimeLock", "howLongLimitToLock" });
       boolean bHowtimelock = false;
       boolean bHowLongLimitToLock = false;
       int iHowLongLimitToLock = 0;
       if (map.get("howTimeLock") != null) {
         bHowtimelock = true;
       }
       
       if (map.get("howLongLimitToLock") != null) {
         iHowLongLimitToLock = Integer.valueOf((String)map.get("howLongLimitToLock")).intValue();
         bHowLongLimitToLock = iHowLongLimitToLock > 0;
       }
       if (("2".equals(userLock)) && (bHowLongLimitToLock))
       {
         Calendar scalendar = Calendar.getInstance();
         scalendar.setTime(sysUser.getLastLoginDate());
         scalendar.add(11, iHowLongLimitToLock);
         Date future = scalendar.getTime();
         
         if (future.after(now)) {
           String msag = PlatformLocalesJSTL.getBundleValue("SanYuanCheckProcess.wrong.password.begin", locale) + df.format(future) + PlatformLocalesJSTL.getBundleValue("SanYuanCheckProcess.wrong.password.end", locale);
           throw new LoginException(msag);
         }
         return;
       }
       
       if (("3".equals(userLock)) && (bHowtimelock)) {
         throw new LoginException(PlatformLocalesJSTL.getBundleValue("SecurityLogServiceImpl.failOperate.error", locale));
       }
       
       if ("1".equals(userLock)) {
         throw new LoginException(PlatformLocalesJSTL.getBundleValue("SecurityLogServiceImpl.password.modify.lock", locale));
       }
       
       throw new LoginException(PlatformLocalesJSTL.getBundleValue("login.error.userlock.tip", locale));
     }
   }
   
   public void loginFailure(HttpRequestResponseHolder holder) throws Exception
   {
     SysUser user = LogUserHolder.getSysUser();
     String appId = RestClientConfig.systemid;
     String clientIp = LogUserHolder.getLoginIp();
     String url = RestClientConfig.getRestHost("syshirosecurity") + "/api/platform6/shiroSecurity/permisstion/loginFailure/v1";
     Muti5Bean<SysUser, String, String, Boolean, String> muti = new Muti5Bean();
     muti.setDto1(user);
     muti.setDto2(appId);
     muti.setDto3(clientIp);
     muti.setDto4(Boolean.valueOf(LogUserHolder.isAdmin()));
     muti.setDto5(LogUserHolder.getLanguageCode());
     ResponseMsg<Map<String, String>> responseMsg = RestClient.doPost(url, muti, new GenericType() {});
     if (!responseMsg.getRetCode().equals("200"))
     {
       this.logger.error(user.getLoginName() + responseMsg.getErrorDesc());
       holder.getRequest().getSession().setAttribute("exception_msg_", responseMsg.getErrorDesc());
     }
   }
   
 
 
 
   private String getUserLanguageCode(SysUser sysUser)
   {
     String lan_code = "";
     
     lan_code = this.sysCustomedAPI.getCustomedValueByKey("PLATFORM_USER_LANGUAGE", sysUser.getId(), RestClientConfig.systemid);
     if (!org.apache.commons.lang3.StringUtils.isEmpty(lan_code)) {
       return lan_code;
     }
     
 
 
     if (org.apache.commons.lang3.StringUtils.isEmpty(sysUser.getLanguageCode())) {
       lan_code = this.sysLanguageAPI.getSystemDefaultLanguageCode();
       sysUser.setLanguageCode(lan_code);
     } else {
       lan_code = sysUser.getLanguageCode();
     }
     return lan_code;
   }
 }


