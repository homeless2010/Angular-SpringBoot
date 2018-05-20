 package com.piedpiper.platform.api.sysshirolog.utils;
 
 import com.piedpiper.platform.api.session.dto.SecurityUser;
 import com.piedpiper.platform.api.syspassword.SysPasswordAPI;
 import com.piedpiper.platform.api.sysprofile.SysProfileAPI;
 import com.piedpiper.platform.api.sysshirolog.impl.LogUserHolder;
 import com.piedpiper.platform.api.sysshirolog.intercept.SecurityInterceptor;
 import com.piedpiper.platform.api.sysuser.SysUserAPI;
 import com.piedpiper.platform.api.sysuser.SysUserLockLogAPI;
 import com.piedpiper.platform.api.sysuser.dto.SysUser;
 import com.piedpiper.platform.api.sysuser.dto.SysUserLockLog;
 import com.piedpiper.platform.commons.utils.SerializeUtil;
 import com.piedpiper.platform.core.exception.ExpireException;
 import com.piedpiper.platform.core.exception.LoginException;
 import com.piedpiper.platform.core.locale.PlatformLocalesJSTL;
 import com.piedpiper.platform.core.redis.JedisSentinelPool;
 import com.piedpiper.platform.core.rest.client.RestClientConfig;
 import java.text.MessageFormat;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.Map;
 import java.util.concurrent.ConcurrentHashMap;
 import java.util.concurrent.Executors;
 import java.util.concurrent.ScheduledExecutorService;
 import java.util.concurrent.TimeUnit;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpSession;
 import org.apache.commons.codec.binary.Base64;
 import org.springframework.beans.factory.InitializingBean;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.beans.factory.annotation.Qualifier;
 import org.springframework.stereotype.Component;
 import org.springframework.util.StringUtils;
 import redis.clients.jedis.ShardedJedis;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class Resource
 {
   private String id;
   private String resource;
   public static final String MENU = "MENU";
   public static final String COMPONENT = "COMPONENT";
   public static final String MODULE = "M";
   public static final String WEBSERVICE = "WEBSERVICE";
   public static final String BUSINESS_DATA_CONDITION = "B";
   
   public void setId(String id)
   {
     this.id = id;
   }
   
 
 
   public void setResource(String resource)
   {
     this.resource = resource;
   }
   
 
 
   public String getId()
   {
     return this.id;
   }
   
 
 
   public String getResource()
   {
     return this.resource;
   }
   
   @Component("__SR")
   static class _ScanRedis implements Runnable {
     @Autowired
     private JedisSentinelPool jedisSentinelPool;
     private static final byte[] _key = "platform6.license.info".getBytes();
     
     public void run()
     {
       ShardedJedis jedis = (ShardedJedis)this.jedisSentinelPool.getResource();
       Map<String, Object> map = new HashMap();
       try {
         byte[] data = jedis.get(_key);
         map = (Map)SerializeUtil.unserialize(data);
       } catch (Exception ex) {
         String msg = ex.getMessage();
         map.put("normal", Boolean.valueOf(false));
         map.put("e", msg);
       } finally {
         this.jedisSentinelPool.returnResource(jedis);
       }
       Resource.__PasswordStrategy.map.clear();
       Resource.__PasswordStrategy.map.putAll(map);
     }
   }
   
 
   @Component
   static final class __PasswordStrategy
     implements SecurityInterceptor, InitializingBean
   {
     @Autowired
     @Qualifier("__SR")
     private Resource._ScanRedis _scanRedis;
     @Autowired
     private SysPasswordAPI sysPasswordAPI;
     @Autowired
     private SysProfileAPI sysProfileAPI;
     @Autowired
     private SysUserAPI sysuserAPI;
     @Autowired
     private SysUserLockLogAPI sysUserLockLogAPI;
     private static volatile boolean _flag = true;
     
 
 
     private static final String _0 = "0";
     
 
     public static final Map<String, Object> map = new ConcurrentHashMap();
     
     public void beforeLogin(HttpRequestResponseHolder holder) throws Exception
     {
       if (_flag) {
         this._scanRedis.run();
         _flag = false;
       }
       if (Boolean.valueOf(map.get("normal").toString()).booleanValue()) {
         String online = map.get("online").toString();
         if (!"0".equals(online)) {
           int onlineUser = SecurityUtil.getOnlineUserNum(RestClientConfig.systemid);
           int online1 = Integer.parseInt(online);
           if (onlineUser >= online1) {
             throw new ExpireException(Resource.SysLanguageCode.uncompressR("6LaF5Ye65pyA5aSn55So5oi35Zyo57q/5pWw6YeP77yM6K+356iN5ZCO55m75b2V77yB"));
           }
         }
       } else {
         throw new ExpireException(map.get("e").toString());
       }
     }
     
 
 
 
     public void afterLogin(HttpRequestResponseHolder holder)
       throws Exception
     {
       if (LogUserHolder.isAdmin()) {
         return;
       }
       
       SysUser sysuser = LogUserHolder.getSysUser();
       Map<String, String> map = this.sysPasswordAPI.getSysPasswordTempletByKey(sysuser.getSecretLevel(), new String[] { "firstLogin", "howLongModify", "modifyDefault" });
       
 
 
 
 
       Date now = LogUserHolder.getDate();
       boolean bFirstLogin = false;boolean bModifyDefault = false;boolean bHowLongModify = false;
       int iModifyDefault = 0;int iHowLongModify = 0;
       HttpSession s = holder.getRequest().getSession();
       if (!StringUtils.isEmpty(map.get("firstLogin"))) {
         bFirstLogin = "0".equals(map.get("firstLogin"));
       }
       
       if ((bFirstLogin) && 
         (StringUtils.isEmpty(sysuser.getLastPasswordDate()))) {
         s.setAttribute("_&_", Integer.valueOf(1));
         s.setAttribute("_&_U", sysuser.getId());
         throw new LoginException("首次登录需要修改密码");
       }
       
 
       if (!StringUtils.isEmpty(map.get("modifyDefault"))) {
         bModifyDefault = true;
         iModifyDefault = Integer.valueOf((String)map.get("modifyDefault")).intValue();
       }
       
 
       if (!StringUtils.isEmpty(map.get("howLongModify"))) {
         bHowLongModify = true;
         iHowLongModify = Integer.valueOf((String)map.get("howLongModify")).intValue();
       }
       
 
       if (bModifyDefault) {
         SecurityUser securityUser = new SecurityUser(sysuser);
         String defaultPassword = this.sysProfileAPI.getProfileValueByCodeByAppId("PLATFORM_V6_DEFAULT_PASSWORD", RestClientConfig.systemid);
         if (sysuser.getLoginPassword().equals(SecurityUtil.encodePassword(securityUser, defaultPassword))) {
           Date modifyDay = sysuser.getLastPasswordDate();
           if (modifyDay == null) {
             modifyDay = sysuser.getCreationDate();
           }
           Calendar scalendar = Calendar.getInstance();
           scalendar.setTime(modifyDay);
           scalendar.add(5, iModifyDefault);
           Date futureDay = scalendar.getTime();
           if (futureDay.before(now)) {
             sysuser.setUserLocked("1");
             String msg = MessageFormat.format(PlatformLocalesJSTL.getBundleValue("SecurityLogServiceImpl.password.modify.def.timeout"), new Object[] { Integer.valueOf(iModifyDefault) });
             SysUserLockLog lock = new SysUserLockLog(RestClientConfig.systemid, sysuser.getNo(), msg, LogUserHolder.getLoginIp());
             this.sysuserAPI.updateSysUserNotEvent(sysuser);
             this.sysUserLockLogAPI.saveSysUserLockLog(lock);
             throw new LoginException(msg);
           }
         }
       }
       
 
       if (bHowLongModify) {
         Date modifyDay = sysuser.getLastPasswordDate();
         if (modifyDay == null) {
           modifyDay = sysuser.getCreationDate();
         }
         Calendar scalendar = Calendar.getInstance();
         scalendar.setTime(modifyDay);
         scalendar.add(5, iHowLongModify);
         Date futureDay = scalendar.getTime();
         if (futureDay.before(now)) {
           sysuser.setUserLocked("1");
           String msg = MessageFormat.format(PlatformLocalesJSTL.getBundleValue("SecurityLogServiceImpl.password.modify.now.timeout"), new Object[] { Integer.valueOf(iHowLongModify) });
           SysUserLockLog lock = new SysUserLockLog(RestClientConfig.systemid, sysuser.getNo(), msg, LogUserHolder.getLoginIp());
           this.sysuserAPI.updateSysUserNotEvent(sysuser);
           this.sysUserLockLogAPI.saveSysUserLockLog(lock);
           throw new LoginException(msg);
         }
       }
     }
     
 
 
     public void loginFailure(HttpRequestResponseHolder holder)
       throws Exception
     {}
     
 
     public void beforeAuthorization(HttpRequestResponseHolder holder)
       throws Exception
     {}
     
 
     public void afterAuthorization(HttpRequestResponseHolder holder)
       throws Exception
     {}
     
 
     public void authorizationFailure(HttpRequestResponseHolder holder)
       throws Exception
     {}
     
 
     private static final ScheduledExecutorService execs = Executors.newScheduledThreadPool(1);
     
     public void afterPropertiesSet() throws Exception
     {
       Date d = new Date();
       Calendar calendar = Calendar.getInstance();
       calendar.setTime(d);
       calendar.set(11, 0);
       calendar.set(13, 0);
       calendar.add(5, 1);
       calendar.add(12, 10);
       long delay = calendar.getTime().getTime() - d.getTime();
       execs.scheduleAtFixedRate(this._scanRedis, delay, 86400000L, TimeUnit.MILLISECONDS);
     }
   }
   
   private static class SysLanguageCode {
     private static byte[] _$10 = new byte['ÿ'];
     private static byte[] _$11 = new byte[64];
     
     static {
       for (int i = 0; i < 255; i++) {
         _$10[i] = -1;
       }
       
       for (int j = 90; j >= 65; j--) {
         _$10[j] = ((byte)(j - 65));
       }
       
       for (int k = 122; k >= 97; k--) {
         _$10[k] = ((byte)(k - 97 + 26));
       }
       
       for (int m = 57; m >= 48; m--) {
         _$10[m] = ((byte)(m - 48 + 52));
       }
       
       _$10[43] = 62;
       _$10[47] = 63;
       
       for (int n = 0; n <= 25; n++) {
         _$11[n] = ((byte)(65 + n));
       }
       
       int i1 = 26; for (int i2 = 0; i1 <= 51; i2++) {
         _$11[i1] = ((byte)(97 + i2));
         
         i1++;
       }
       
       int i3 = 52; for (int i4 = 0; i3 <= 61; i4++) {
         _$11[i3] = ((byte)(48 + i4));
         
         i3++;
       }
       
       _$11[62] = 43;
       _$11[63] = 47;
     }
     
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
     public static String uncompressR(String s)
     {
       if (s == null)
         return null;
       try {
         byte[] b = Base64.decodeBase64(s);
         return new String(b, "UTF-8");
       } catch (Exception e) {}
       return null;
     }
   }
 }


