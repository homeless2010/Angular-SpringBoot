 package com.piedpiper.platform.api.eform.impl;
 
 import com.piedpiper.platform.api.eform.EformAPI;
 import com.piedpiper.platform.api.syspermissionresource.dto.SysPermissionResource;
 import com.piedpiper.platform.api.sysresource.dto.SysResource;
 import com.piedpiper.platform.core.rest.client.RestClient;
 import com.piedpiper.platform.core.rest.client.RestClientConfig;
 import com.piedpiper.platform.core.rest.msg.ResponseMsg;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import javax.ws.rs.core.GenericType;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 
 
 
 
 public class EformAPImpl
   implements EformAPI
 {
   static Logger logger = LoggerFactory.getLogger(EformAPImpl.class);
   
   public List<SysResource> getEformResource(String tableName, String applicationId)
   {
     Map<String, String> entity = new HashMap();
     entity.put("tableName", tableName);
     entity.put("applicationId", applicationId);
     
     String restHost = RestClientConfig.getRestHost("eform");
     String restURL = restHost + "/api/platform6/eform/resource/listAll/v1";
     ResponseMsg<List<SysResource>> responseMsg = RestClient.doPost(restURL, entity, new GenericType() {});
     if (responseMsg.getRetCode().equals("200")) {
       logger.debug("调用rest服务成功：" + restURL);
     } else {
       logger.info("调用rest服务出错：:" + restURL + "," + responseMsg.getRetCode() + "," + responseMsg.getErrorDesc());
     }
     return (List)responseMsg.getResponseBody();
   }
   
 
   public List<SysPermissionResource> getEformDataResource(String tableName, String menuId)
   {
     Map<String, String> entity = new HashMap();
     entity.put("tableName", tableName);
     entity.put("menuId", menuId);
     
     String restHost = RestClientConfig.getRestHost("eform");
     String restURL = restHost + "/api/platform6/eform/resource/listDataResource/v1";
     ResponseMsg<List<SysPermissionResource>> responseMsg = RestClient.doPost(restURL, entity, new GenericType() {});
     if (responseMsg.getRetCode().equals("200")) {
       logger.debug("调用rest服务成功：" + restURL);
     } else {
       logger.info("调用rest服务出错：:" + restURL + "," + responseMsg.getRetCode() + "," + responseMsg.getErrorDesc());
     }
     return (List)responseMsg.getResponseBody();
   }
   
 
   public Map<String, String> getEformElements(String tableName)
   {
     Map<String, String> entity = new HashMap();
     entity.put("tableName", tableName);
     String restHost = RestClientConfig.getRestHost("eform");
     String restURL = restHost + "/api/platform6/eform/resource/listElements/v1";
     ResponseMsg<Map<String, String>> responseMsg = RestClient.doPost(restURL, entity, new GenericType() {});
     if (responseMsg.getRetCode().equals("200")) {
       logger.debug("调用rest服务成功：" + restURL);
     } else {
       logger.info("调用rest服务出错：:" + restURL + "," + responseMsg.getRetCode() + "," + responseMsg.getErrorDesc());
     }
     return (Map)responseMsg.getResponseBody();
   }
 }


