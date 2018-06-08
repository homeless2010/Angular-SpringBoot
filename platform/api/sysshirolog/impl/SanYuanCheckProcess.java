 package com.piedpiper.platform.api.sysshirolog.impl;
 
 import com.piedpiper.platform.api.sysprofile.SysProfileAPI;
 import com.piedpiper.platform.api.sysshirolog.utils.HttpRequestResponseHolder;
 import com.piedpiper.platform.api.sysuser.dto.SysUser;
 import com.piedpiper.platform.api.sysuserlimitip.SysUserLimitIpAPI;
 import com.piedpiper.platform.api.sysuserlimitip.dto.SysUserLimitIp;
 import com.piedpiper.platform.commons.utils.IpUtil;
 import com.piedpiper.platform.core.exception.LoginException;
 import com.piedpiper.platform.core.locale.PlatformLocalesJSTL;
 import com.piedpiper.platform.core.rest.client.RestClientConfig;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 @Service
 public class SanYuanCheckProcess
 {
   @Autowired(required=true)
   private SysUserLimitIpAPI sysUserLimitIpAPI;
   @Autowired
   private SysProfileAPI sysProfile;
   
   public void beforeLogin(HttpRequestResponseHolder holder, String userName)
     throws Exception
   {
     String checkFlag = this.sysProfile.getProfileValueByCodeByAppId("PLATFORM_V6_LOGINCHECK_CONTROL", RestClientConfig.systemid);
     
     if ((!Boolean.valueOf(checkFlag).booleanValue()) || (LogUserHolder.isAdmin())) {
       return;
     }
     
     SysUser sysUser = LogUserHolder.getSysUser();
     String currentIp = LogUserHolder.getLoginIp();
     
     if (IpUtil.isValidIp4(currentIp)) {
       Map<String, List<SysUserLimitIp>> map = this.sysUserLimitIpAPI.finduserLimitByUserId(sysUser.getId());
       
       List<SysUserLimitIp> unList = (List)map.get("1");
       for (SysUserLimitIp sysUserList : unList) {
         if ("0".equals(sysUserList.getLimitTypeIpType())) {
           if (currentIp.equals(sysUserList.getUserLimitIpFrom())) {
             throw new LoginException(PlatformLocalesJSTL.getBundleValue("SanYuanCheckProcess.currentIp.forbidden.visit"));
           }
         } else if (("1".equals(sysUserList.getLimitTypeIpType())) && 
           (IpUtil.ipIsValid(sysUserList.getUserLimitIpFrom(), sysUserList.getUserLimitIpEnd(), currentIp))) {
           throw new LoginException(PlatformLocalesJSTL.getBundleValue("SanYuanCheckProcess.currentIp.forbidden.visit"));
         }
       }
       
 
       List<SysUserLimitIp> inList = (List)map.get("0");
       for (SysUserLimitIp sysUserList : inList) {
         if ("0".equals(sysUserList.getLimitTypeIpType())) {
           if (!currentIp.equals(sysUserList.getUserLimitIpFrom())) {
             throw new LoginException(PlatformLocalesJSTL.getBundleValue("SanYuanCheckProcess.currentIp.outside.limitedIp"));
           }
         } else if (("1".equals(sysUserList.getLimitTypeIpType())) && 
           (!IpUtil.ipIsValid(sysUserList.getUserLimitIpFrom(), sysUserList.getUserLimitIpEnd(), currentIp))) {
           throw new LoginException(PlatformLocalesJSTL.getBundleValue("SanYuanCheckProcess.currentIp.outside.limitedIp"));
         }
       }
     }
   }
   
   public void afterLogin(HttpRequestResponseHolder holder)
     throws Exception
   {}
   
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
 }


