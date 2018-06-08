 package com.piedpiper.platform.api.syscoding.impl;
 
 import com.piedpiper.platform.api.syscoding.SysCodingAPI;
 import com.piedpiper.platform.api.syscoding.dto.SysCodingComSegment;
 import com.piedpiper.platform.api.syscoding.dto.SysCodingComSegmentValues;
 import com.piedpiper.platform.api.syscoding.dto.SysCodingRuleBase;
 import com.piedpiper.platform.api.syscoding.dto.SysCodingRuleSegment;
 import com.piedpiper.platform.commons.utils.JsonHelper;
 import com.piedpiper.platform.core.rest.client.RestClient;
 import com.piedpiper.platform.core.rest.client.RestClientConfig;
 import com.piedpiper.platform.core.rest.msg.Muti2Bean;
 import com.piedpiper.platform.core.rest.msg.Muti6Bean;
 import com.piedpiper.platform.core.rest.msg.ResponseMsg;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import javax.ws.rs.core.GenericType;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 
 
 
 
 public class SysCodingAPImpl
   implements SysCodingAPI
 {
   Logger log = LoggerFactory.getLogger(SysCodingAPImpl.class);
   
 
 
   public static final String SELECT_REST_URL = "/api/platform6/syscoding/sysCodingSelect";
   
 
 
   public static final String RULE_REST_URL = "/api/platform6/syscoding/sysCodingRule";
   
 
 
   public static final String COM_REST_URL = "/api/platform6/syscoding/sysCodingComSegment";
   
 
 
 
   public String createSelectCoding(String codingId, String codingValue, String lengths, String moduleName, String formId)
     throws Exception
   {
     return createSelectCoding(codingId, codingValue, lengths, moduleName, formId, false);
   }
   
 
 
 
 
 
 
 
 
 
 
 
   public String createSelectCoding(String codingId, String codingValue, String lengths, String moduleName, String formId, boolean isMustUnused)
     throws Exception
   {
     String newCodingValue = "";
     
     Muti6Bean<String, String, String, String, String, Boolean> mb = new Muti6Bean();
     
     mb.setDto1(codingId);
     mb.setDto2(codingValue);
     mb.setDto3(lengths);
     mb.setDto4(moduleName);
     mb.setDto5(formId);
     mb.setDto6(Boolean.valueOf(isMustUnused));
     
     String url = RestClientConfig.getRestHost("syscoding") + "/api/platform6/syscoding/sysCodingSelect" + "/createSelectCoding/v1";
     ResponseMsg<String> responseMsg = RestClient.doPost(url, mb, new GenericType() {});
     if (responseMsg.getRetCode().equals("200")) {
       newCodingValue = (String)responseMsg.getResponseBody();
     } else {
       this.log.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
       throw new Exception(responseMsg.getErrorDesc());
     }
     return newCodingValue;
   }
   
 
 
 
 
   public List<SysCodingRuleBase> getCodingRuleList(String appId)
   {
     List<SysCodingRuleBase> list = null;
     String url = RestClientConfig.getRestHost("syscoding") + "/api/platform6/syscoding/sysCodingSelect" + "/getCodingRuleList/v1";
     ResponseMsg<List<SysCodingRuleBase>> responseMsg = RestClient.doPost(url, appId, new GenericType() {});
     if (responseMsg.getRetCode().equals("200")) {
       list = (List)responseMsg.getResponseBody();
     } else {
       this.log.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
       list = new ArrayList();
     }
     return list;
   }
   
 
 
 
 
 
   public String getCodingRuleListJson(String appId, boolean isDisplaySelect)
   {
     List<SysCodingRuleBase> list = getCodingRuleList(appId);
     List<Map<String, Object>> jsonList = new ArrayList();
     if (isDisplaySelect) {
       Map<String, Object> map = new HashMap();
       map.put("id", "");
       map.put("code", "");
       map.put("name", "--请选择--");
       jsonList.add(map);
     }
     
     for (int i = 0; i < list.size(); i++) {
       SysCodingRuleBase ruleBase = (SysCodingRuleBase)list.get(i);
       String id = ruleBase.getId();
       String codingCode = ruleBase.getCodingCode();
       String codingName = ruleBase.getCodingName();
       
       Map<String, Object> map = new HashMap();
       map.put("id", id);
       map.put("code", codingCode);
       map.put("name", codingName);
       jsonList.add(map);
     }
     
     String data = JsonHelper.getInstance().writeValueAsString(jsonList);
     return data;
   }
   
 
 
 
 
   public SysCodingRuleBase getCodingRuleBaseById(String codingId)
   {
     SysCodingRuleBase sysCodingRuleBase = null;
     String url = RestClientConfig.getRestHost("syscoding") + "/api/platform6/syscoding/sysCodingRule" + "/getRuleBase/v1";
     ResponseMsg<SysCodingRuleBase> responseMsg = RestClient.doPost(url, codingId, new GenericType() {});
     if (responseMsg.getRetCode().equals("200")) {
       sysCodingRuleBase = (SysCodingRuleBase)responseMsg.getResponseBody();
     } else {
       this.log.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
     }
     return sysCodingRuleBase;
   }
   
 
 
 
 
 
   public SysCodingRuleBase getCodingRuleBaseByCode(String appId, String codingCode)
   {
     Muti2Bean<String, String> mb = new Muti2Bean();
     mb.setDto1(appId);
     mb.setDto2(codingCode);
     
     SysCodingRuleBase sysCodingRuleBase = null;
     String url = RestClientConfig.getRestHost("syscoding") + "/api/platform6/syscoding/sysCodingSelect" + "/getCodingRuleBaseByCode/v1";
     ResponseMsg<SysCodingRuleBase> responseMsg = RestClient.doPost(url, mb, new GenericType() {});
     if (responseMsg.getRetCode().equals("200")) {
       sysCodingRuleBase = (SysCodingRuleBase)responseMsg.getResponseBody();
     } else {
       this.log.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
     }
     return sysCodingRuleBase;
   }
   
 
 
 
 
   public boolean deleteCodingUsedInfoByFormId(String formId)
   {
     boolean isok = false;
     String url = RestClientConfig.getRestHost("syscoding") + "/api/platform6/syscoding/sysCodingSelect" + "/deleteCodingUsedInfoByFormId/v1";
     ResponseMsg<Boolean> responseMsg = RestClient.doPost(url, formId, new GenericType() {});
     if (responseMsg.getRetCode().equals("200")) {
       isok = ((Boolean)responseMsg.getResponseBody()).booleanValue();
     } else {
       this.log.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
     }
     return isok;
   }
   
 
 
 
 
   public List<SysCodingRuleSegment> findRuleSegment(String codingId)
   {
     List<SysCodingRuleSegment> list = null;
     String url = RestClientConfig.getRestHost("syscoding") + "/api/platform6/syscoding/sysCodingRule" + "/findRuleSegment/v1";
     ResponseMsg<List<SysCodingRuleSegment>> responseMsg = RestClient.doPost(url, codingId, new GenericType() {});
     if (responseMsg.getRetCode().equals("200")) {
       list = (List)responseMsg.getResponseBody();
     } else {
       this.log.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
       list = new ArrayList();
     }
     return list;
   }
   
 
 
 
 
   public List<SysCodingComSegment> findComSegmentList(String appId)
   {
     List<SysCodingComSegment> list = null;
     String url = RestClientConfig.getRestHost("syscoding") + "/api/platform6/syscoding/sysCodingComSegment" + "/findComSegmentList/v1";
     ResponseMsg<List<SysCodingComSegment>> responseMsg = RestClient.doPost(url, appId, new GenericType() {});
     if (responseMsg.getRetCode().equals("200")) {
       list = (List)responseMsg.getResponseBody();
     } else {
       this.log.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
       list = new ArrayList();
     }
     return list;
   }
   
 
 
 
 
   public SysCodingComSegment getComSegmentById(String id)
   {
     SysCodingComSegment sysCodingComSegment = null;
     String url = RestClientConfig.getRestHost("syscoding") + "/api/platform6/syscoding/sysCodingComSegment" + "/getComSegment/v1";
     ResponseMsg<SysCodingComSegment> responseMsg = RestClient.doPost(url, id, new GenericType() {});
     if (responseMsg.getRetCode().equals("200")) {
       sysCodingComSegment = (SysCodingComSegment)responseMsg.getResponseBody();
     } else {
       this.log.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
     }
     return sysCodingComSegment;
   }
   
 
 
 
 
   public List<SysCodingComSegmentValues> findComSegmentValues(String segmentId)
   {
     List<SysCodingComSegmentValues> list = null;
     String url = RestClientConfig.getRestHost("syscoding") + "/api/platform6/syscoding/sysCodingComSegment" + "/findComSegmentValues/v1";
     ResponseMsg<List<SysCodingComSegmentValues>> responseMsg = RestClient.doPost(url, segmentId, new GenericType() {});
     if (responseMsg.getRetCode().equals("200")) {
       list = (List)responseMsg.getResponseBody();
     } else {
       this.log.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
       list = new ArrayList();
     }
     return list;
   }
 }


