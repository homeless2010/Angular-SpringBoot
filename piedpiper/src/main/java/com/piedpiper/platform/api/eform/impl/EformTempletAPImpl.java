 package com.piedpiper.platform.api.eform.impl;
 
 import com.piedpiper.platform.api.eform.EformTempletAPI;
 import com.piedpiper.platform.api.eform.dto.EformTempletField;
 import com.piedpiper.platform.core.rest.client.RestClient;
 import com.piedpiper.platform.core.rest.client.RestClientConfig;
 import com.piedpiper.platform.core.rest.msg.ResponseMsg;
 import javax.ws.rs.core.GenericType;
 
 
 
 public class EformTempletAPImpl
   implements EformTempletAPI
 {
   public String insertEformTempletField(EformTempletField eformTempletField)
   {
     String restUrl = RestClientConfig.getRestHost("eform") + "/api/platform6/eformTempletField/saveField/v1";
     
 
     ResponseMsg<String> responseMsg = RestClient.doPost(restUrl, eformTempletField, new GenericType() {});
     if (responseMsg.getRetCode().equals("200")) {
       return (String)responseMsg.getResponseBody();
     }
     throw new RuntimeException("插入对象EformTempletField错误，描述：" + responseMsg.getErrorDesc());
   }
 }


