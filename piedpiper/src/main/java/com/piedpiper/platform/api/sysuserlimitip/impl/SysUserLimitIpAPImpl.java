 package com.piedpiper.platform.api.sysuserlimitip.impl;
 
 import com.piedpiper.platform.api.syspassword.dto.SysPasswordTemplet;
 import com.piedpiper.platform.api.syspassword.dto.SysPasswordTempletLevel;
 import com.piedpiper.platform.api.sysuserlimitip.SysUserLimitIpAPI;
 import com.piedpiper.platform.api.sysuserlimitip.dto.SysUserLimitIp;
 import com.piedpiper.platform.commons.utils.IpUtil;
 import com.piedpiper.platform.core.redisCacheManager.BaseCacheManager;
 import com.piedpiper.platform.core.rest.client.RestClient;
 import com.piedpiper.platform.core.rest.client.RestClientConfig;
 import com.piedpiper.platform.core.rest.msg.ResponseMsg;
 import com.fasterxml.jackson.core.type.TypeReference;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Map;
 import javax.ws.rs.core.GenericType;
 import org.springframework.beans.factory.annotation.Autowired;
 
 
 
 
 public class SysUserLimitIpAPImpl
   implements SysUserLimitIpAPI
 {
   @Autowired
   private BaseCacheManager baseCacheManager;
   
   public void reLoad()
     throws Exception
   {
     String url = RestClientConfig.getRestHost("sysuser") + "/api/platform6/sysuserlimitip/SysUserLimitIp/reLoad/v1";
     ResponseMsg<Void> responseMsg = RestClient.doGet(url, new GenericType() {});
     if (!responseMsg.getRetCode().equals("200"))
     {
       throw new Exception(responseMsg.getErrorDesc());
     }
   }
   
   public List<String> findSecretLevel(String secretLevel)
   {
     List<SysPasswordTempletLevel> sysPasswordTempletLevelList = this.baseCacheManager.getAllFromCache("PLATFORM6_PASSWORDTEMPLETLEVEL_CODE_" + secretLevel, new TypeReference() {});
     if (sysPasswordTempletLevelList.size() > 0) {
       List<SysPasswordTemplet> list = this.baseCacheManager.getAllFromCache("PLATFORM6_PASSWORDTEMPLET_TYPE_KEY_" + ((SysPasswordTempletLevel)sysPasswordTempletLevelList.get(0)).getId() + "_howLongLimitToLock", new TypeReference() {});
       List<String> resultList = new ArrayList();
       for (SysPasswordTemplet e : list) {
         resultList.add(e.getTempletValue());
       }
       return resultList;
     }
     return null;
   }
   
 
   public List<String> finduserLimitUserId(String id, String string, String string2)
   {
     List<SysUserLimitIp> SysUserLimitIpList = this.baseCacheManager.getAllFromCache("PLATFORM6_USERLIMITIP_USERID_USERTYPE_IPTYPE_" + id + "_" + string + "_" + string2, new TypeReference() {});
     List<String> resultList = new ArrayList();
     for (SysUserLimitIp e : SysUserLimitIpList) {
       resultList.add(e.getUserLimitIpFrom());
     }
     return resultList;
   }
   
   public List<Object> findlimitTypeIpType(String string, String string2)
   {
     List<SysUserLimitIp> SysUserLimitIpList = this.baseCacheManager.getAllFromCache("PLATFORM6_USERLIMITIP_USERID_USERTYPE_IPTYPE_" + null + "_" + string + "_" + string2, new TypeReference() {});
     List<Object> resultList = new ArrayList();
     for (SysUserLimitIp e : SysUserLimitIpList) {
       List<String> l = new ArrayList();
       l.add(e.getUserLimitIpFrom());
       l.add(e.getUserLimitIpEnd());
       resultList.add(l);
     }
     return resultList;
   }
   
   public List<Object> finduserLimitUserIdObject(String id, String string, String string2)
   {
     List<SysUserLimitIp> SysUserLimitIpList = this.baseCacheManager.getAllFromCache("PLATFORM6_USERLIMITIP_USERID_USERTYPE_IPTYPE_" + id + "_" + string + "_" + string2, new TypeReference() {});
     List<Object> resultList = new ArrayList();
     for (SysUserLimitIp e : SysUserLimitIpList) {
       List<String> l = new ArrayList();
       l.add(e.getUserLimitIpFrom());
       l.add(e.getUserLimitIpEnd());
       resultList.add(l);
     }
     return resultList;
   }
   
   public Map<String, List<SysUserLimitIp>> finduserLimitByUserId(String userId) {
     String url = RestClientConfig.getRestHost("sysuser") + "/api/platform6/sysuserlimitip/SysUserLimitIp/finduserLimitByUserId/" + userId + "/v1";
     ResponseMsg<Map<String, List<SysUserLimitIp>>> responseMsg = RestClient.doGet(url, new GenericType() {});
     if (!responseMsg.getRetCode().equals("200"))
     {
       throw new RuntimeException(responseMsg.getErrorDesc());
     }
     return (Map)responseMsg.getResponseBody();
   }
   
 
 
 
 
 
   public boolean checkCurrentBetweenIpSectionList(List<Object> list, String currentIp)
   {
     boolean flag = false;
     if ((list != null) && (list.size() > 0)) {
       for (int i = 0; i < list.size(); i++) {
         List listtemp = (List)list.get(i);
         Object[] ipSection = listtemp.toArray();
         flag = IpUtil.ipIsValid(ipSection[0].toString(), ipSection[1].toString(), currentIp);
         if (flag) {
           break;
         }
       }
     }
     return flag;
   }
 }


