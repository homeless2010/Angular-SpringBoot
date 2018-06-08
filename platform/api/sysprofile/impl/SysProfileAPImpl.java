 package com.piedpiper.platform.api.sysprofile.impl;
 
 import com.piedpiper.platform.api.session.SessionHelper;
 import com.piedpiper.platform.api.sysprofile.SysProfileAPI;
 import com.piedpiper.platform.api.sysprofile.dto.SysProfileOption;
 import com.piedpiper.platform.api.sysprofile.dto.SysProfileOptionValue;
 import com.piedpiper.platform.core.redisCacheManager.BaseCacheManager;
 import com.piedpiper.platform.core.rest.client.RestClient;
 import com.piedpiper.platform.core.rest.client.RestClientConfig;
 import com.piedpiper.platform.core.rest.msg.ResponseMsg;
 import com.fasterxml.jackson.core.type.TypeReference;
 import java.util.ArrayList;
 import java.util.List;
 import javax.ws.rs.core.GenericType;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.beans.factory.annotation.Autowired;
 
 
 
 
 
 public class SysProfileAPImpl
   implements SysProfileAPI
 {
   private Logger log = LoggerFactory.getLogger(SysProfileAPImpl.class);
   @Autowired
   private BaseCacheManager baseCacheManager;
   
   public void reLoad() throws Exception
   {
     String url = RestClientConfig.getRestHost("sysprofile") + "/api/platform6/sysprofile/SysProfile/reLoad/v1";
     ResponseMsg<Void> responseMsg = RestClient.doGet(url, new GenericType() {});
     if (!responseMsg.getRetCode().equals("200"))
     {
       throw new Exception(responseMsg.getErrorDesc());
     }
   }
   
   public boolean containsOptionCode(String strProfileOptionCode)
   {
     return this.baseCacheManager.exists("PLATFORM6_PROFILEOPTION_CODE_" + strProfileOptionCode);
   }
   
   public String getProfileValueByCode(String strProfileOptionCode) {
     List<Object> resultList = new ArrayList();
     List<SysProfileOption> profileOptions = this.baseCacheManager.getAllFromCache("PLATFORM6_PROFILEOPTION_CODE_" + strProfileOptionCode, new TypeReference() {}, SessionHelper.getApplicationId());
     if ((profileOptions != null) && (profileOptions.size() > 0)) {
       List<SysProfileOptionValue> list = this.baseCacheManager.getAllFromCache("PLATFORM6_PROFILEOPTIONSID_" + ((SysProfileOption)profileOptions.get(0)).getId(), new TypeReference() {});
       for (SysProfileOptionValue value : list) {
         resultList.add(value.getProfileOptionValue());
       }
     }
     if ((resultList != null) && (resultList.size() > 0)) {
       return (String)resultList.get(0);
     }
     if (!containsOptionCode(strProfileOptionCode)) {
       this.log.error("系统参数配置代码【" + strProfileOptionCode + "】没有对应值！");
     }
     
     return null;
   }
   
   public String getProfileValueByCodeByAppId(String strProfileOptionCode, String appId)
   {
     List<Object> resultList = new ArrayList();
     List<SysProfileOption> profileOptions = this.baseCacheManager.getAllFromCache("PLATFORM6_PROFILEOPTION_CODE_" + strProfileOptionCode, new TypeReference() {}, appId);
     if ((profileOptions != null) && (profileOptions.size() > 0)) {
       List<SysProfileOptionValue> list = this.baseCacheManager.getAllFromCache("PLATFORM6_PROFILEOPTIONSID_" + ((SysProfileOption)profileOptions.get(0)).getId(), new TypeReference() {});
       for (SysProfileOptionValue value : list) {
         resultList.add(value.getProfileOptionValue());
       }
     }
     if ((resultList != null) && (resultList.size() > 0)) {
       return (String)resultList.get(0);
     }
     if (!containsOptionCode(strProfileOptionCode)) {
       this.log.error("系统参数配置代码【" + strProfileOptionCode + "】在应用" + appId + "中没有对应值！");
     }
     return null;
   }
   
   public List<Object> getProfileValueList(String strProfileOptionCode)
   {
     List<Object> resultList = new ArrayList();
     List<SysProfileOption> profileOptions = this.baseCacheManager.getAllFromCache("PLATFORM6_PROFILEOPTION_CODE_" + strProfileOptionCode, new TypeReference() {}, SessionHelper.getApplicationId());
     if ((profileOptions != null) && (profileOptions.size() > 0)) {
       List<SysProfileOptionValue> list = this.baseCacheManager.getAllFromCache("PLATFORM6_PROFILEOPTIONSID_" + ((SysProfileOption)profileOptions.get(0)).getId(), new TypeReference() {});
       for (SysProfileOptionValue value : list) {
         resultList.add(value.getProfileOptionValue());
       }
     }
     if ((resultList == null) || (resultList.size() < 1)) {
       this.log.error("系统参数配置代码【" + strProfileOptionCode + "】没有对应值！");
     }
     return resultList;
   }
   
   public List<Object> getProfileValueListByAppId(String strProfileOptionCode, String appId)
   {
     List<Object> resultList = new ArrayList();
     List<SysProfileOption> profileOptions = this.baseCacheManager.getAllFromCache("PLATFORM6_PROFILEOPTION_CODE_" + strProfileOptionCode, new TypeReference() {}, appId);
     if ((profileOptions != null) && (profileOptions.size() > 0)) {
       List<SysProfileOptionValue> list = this.baseCacheManager.getAllFromCache("PLATFORM6_PROFILEOPTIONSID_" + ((SysProfileOption)profileOptions.get(0)).getId(), new TypeReference() {});
       for (SysProfileOptionValue value : list) {
         resultList.add(value.getProfileOptionValue());
       }
     }
     if ((resultList == null) || (resultList.size() < 1)) {
       this.log.error("系统参数配置代码【" + strProfileOptionCode + "】在应用" + appId + "中没有对应值！");
     }
     return resultList;
   }
 }


