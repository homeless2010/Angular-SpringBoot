 package com.piedpiper.platform.api.sysshirolog.impl;
 
 import com.piedpiper.platform.api.sysshirolog.SanyuanServiceAPI;
 import com.piedpiper.platform.core.rest.client.RestClient;
 import com.piedpiper.platform.core.rest.client.RestClientConfig;
 import com.piedpiper.platform.core.rest.msg.ResponseMsg;
 import java.util.HashMap;
 import java.util.Map;
 import javax.ws.rs.core.GenericType;
 
 
 
 
 public class SanyuanService
   implements SanyuanServiceAPI
 {
   public String sanyuanLogin(String name1, String pwd1, String name2, String pwd2, String name3, String pwd3)
   {
     Map<String, String> parameter = new HashMap();
     parameter.put("name1", name1);
     parameter.put("pwd1", pwd1);
     parameter.put("name2", name2);
     parameter.put("pwd2", pwd2);
     parameter.put("name3", name3);
     parameter.put("pwd3", pwd3);
     String url = RestClientConfig.getRestHost("sysuser") + "/api/platform6/shiroSecurity/permisstion/sanyuanlogin/v1";
     ResponseMsg<String> responseMsg = RestClient.doPost(url, parameter, new GenericType() {});
     if (responseMsg.getRetCode().equals("200")) {
       return (String)responseMsg.getResponseBody();
     }
     return null;
   }
 }


