 package com.piedpiper.platform.api.sysuserlimitip.dto;
 
 import com.piedpiper.platform.core.domain.BeanDTO;
 import com.piedpiper.platform.core.properties.PlatformConstant.LogType;
 import com.piedpiper.platform.core.redisCacheManager.BaseCacheBean;
 import java.util.HashMap;
 import java.util.Map;
 
 
 
 
 
 
 
 
 
 
 
 
 public class SysUserLimitIp
   extends BeanDTO
   implements BaseCacheBean
 {
   public static final String USERLIMITIP = "PLATFORM6_USERLIMITIP";
   public static final String USERLIMITIP_USERID_USERTYPE_IPTYPE = "PLATFORM6_USERLIMITIP_USERID_USERTYPE_IPTYPE_";
   private static final long serialVersionUID = 1L;
   private String id;
   private String userLimitUserId;
   private String limitTypeIpType;
   private String limitUserType;
   private String userLimitIpFrom;
   private String userLimitIpEnd;
   private String attribute01;
   private String attribute02;
   private String attribute03;
   private String attribute04;
   private String attribute05;
   private String attribute06;
   private String attribute07;
   private String attribute08;
   private String attribute09;
   private String attribute10;
   private String userLimitUserName;
   
   public String getId()
   {
     return this.id;
   }
   
   public void setId(String id) {
     this.id = id;
   }
   
   public String getUserLimitUserId()
   {
     return this.userLimitUserId;
   }
   
   public void setUserLimitUserId(String userLimitUserId) {
     this.userLimitUserId = userLimitUserId;
   }
   
   public String getLimitTypeIpType()
   {
     return this.limitTypeIpType;
   }
   
   public void setLimitTypeIpType(String limitTypeIpType) {
     this.limitTypeIpType = limitTypeIpType;
   }
   
   public String getLimitUserType()
   {
     return this.limitUserType;
   }
   
   public void setLimitUserType(String limitUserType) {
     this.limitUserType = limitUserType;
   }
   
   public String getUserLimitIpFrom()
   {
     return this.userLimitIpFrom;
   }
   
   public void setUserLimitIpFrom(String userLimitIpFrom) {
     this.userLimitIpFrom = userLimitIpFrom;
   }
   
   public String getUserLimitIpEnd()
   {
     return this.userLimitIpEnd;
   }
   
   public void setUserLimitIpEnd(String userLimitIpEnd) {
     this.userLimitIpEnd = userLimitIpEnd;
   }
   
   public String getAttribute01()
   {
     return this.attribute01;
   }
   
   public void setAttribute01(String attribute01) {
     this.attribute01 = attribute01;
   }
   
   public String getAttribute02()
   {
     return this.attribute02;
   }
   
   public void setAttribute02(String attribute02) {
     this.attribute02 = attribute02;
   }
   
   public String getAttribute03()
   {
     return this.attribute03;
   }
   
   public void setAttribute03(String attribute03) {
     this.attribute03 = attribute03;
   }
   
   public String getAttribute04()
   {
     return this.attribute04;
   }
   
   public void setAttribute04(String attribute04) {
     this.attribute04 = attribute04;
   }
   
   public String getAttribute05()
   {
     return this.attribute05;
   }
   
   public void setAttribute05(String attribute05) {
     this.attribute05 = attribute05;
   }
   
   public String getAttribute06()
   {
     return this.attribute06;
   }
   
   public void setAttribute06(String attribute06) {
     this.attribute06 = attribute06;
   }
   
   public String getAttribute07()
   {
     return this.attribute07;
   }
   
   public void setAttribute07(String attribute07) {
     this.attribute07 = attribute07;
   }
   
   public String getAttribute08()
   {
     return this.attribute08;
   }
   
   public void setAttribute08(String attribute08) {
     this.attribute08 = attribute08;
   }
   
   public String getAttribute09()
   {
     return this.attribute09;
   }
   
   public void setAttribute09(String attribute09) {
     this.attribute09 = attribute09;
   }
   
   public String getAttribute10()
   {
     return this.attribute10;
   }
   
   public void setAttribute10(String attribute10) {
     this.attribute10 = attribute10;
   }
   
   public String getLogFormName()
   {
     return null;
   }
   
   public String getLogTitle()
   {
     return null;
   }
   
   public PlatformConstant.LogType getLogType()
   {
     return null;
   }
   
   public String getUserLimitUserName()
   {
     return this.userLimitUserName;
   }
   
   public void setUserLimitUserName(String userLimitUserName) {
     this.userLimitUserName = userLimitUserName;
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
     map.put("PLATFORM6_USERLIMITIP", this.id);
     map.put("PLATFORM6_USERLIMITIP_USERID_USERTYPE_IPTYPE_" + this.userLimitUserId + "_" + this.limitUserType + "_" + this.limitTypeIpType, this.id);
     return map;
   }
   
   public String prefix()
   {
     return "PLATFORM6_USERLIMITIP";
   }
   
   public String returnAppId()
   {
     return null;
   }
 }


