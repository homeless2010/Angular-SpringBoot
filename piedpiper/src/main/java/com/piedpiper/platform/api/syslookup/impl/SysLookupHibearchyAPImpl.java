 package com.piedpiper.platform.api.syslookup.impl;
 
 import com.piedpiper.platform.api.session.SessionHelper;
 import com.piedpiper.platform.api.syslookup.SysLookupHibearchyAPI;
 import com.piedpiper.platform.api.syslookup.dto.SysLookupHibearchy;
 import com.piedpiper.platform.commons.utils.web.TreeNode;
 import com.piedpiper.platform.core.redisCacheManager.BaseCacheManager;
 import com.piedpiper.platform.core.rest.client.RestClient;
 import com.piedpiper.platform.core.rest.client.RestClientConfig;
 import com.piedpiper.platform.core.rest.msg.ResponseMsg;
 import com.fasterxml.jackson.core.type.TypeReference;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;
 import javax.ws.rs.core.GenericType;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.beans.factory.annotation.Autowired;
 
 
 
 
 
 public class SysLookupHibearchyAPImpl
   implements SysLookupHibearchyAPI
 {
   private static Logger log = LoggerFactory.getLogger(SysLookupHibearchyAPImpl.class);
   
 
   public static final String BASE_REST_URL = "/api/platform6/syslookup/sysLookupHibearchy";
   
   @Autowired
   private BaseCacheManager baseCacheManager;
   
 
   public void reLoad()
     throws Exception
   {
     String url = RestClientConfig.getRestHost("syslookup") + "/api/platform6/syslookup/sysLookupHibearchy" + "/reLoad/v1";
     ResponseMsg<Void> responseMsg = RestClient.doGet(url, new GenericType() {});
     if (!responseMsg.getRetCode().equals("200"))
     {
       throw new Exception(responseMsg.getErrorDesc());
     }
   }
   
 
 
 
 
 
   public boolean containsLookupType(String lookupType)
   {
     return this.baseCacheManager.exists("PLATFORM6_LOOKUPHIBEARCHY_TYPE_" + lookupType);
   }
   
 
 
 
 
 
   public List<TreeNode> getHibearchyTreeData(String lookupType)
   {
     return getHibearchyTreeData(SessionHelper.getApplicationId(), lookupType);
   }
   
 
 
 
   private String getHibearchyRootId()
   {
     String rootId = "";
     String url = RestClientConfig.getRestHost("syslookup") + "/api/platform6/syslookup/sysLookupHibearchy" + "/getHibearchyRootId/v1";
     ResponseMsg<String> responseMsg = RestClient.doGet(url, new GenericType() {});
     if (responseMsg.getRetCode().equals("200")) {
       rootId = (String)responseMsg.getResponseBody();
     } else {
       log.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
     }
     return rootId;
   }
   
 
 
 
 
 
 
   public List<TreeNode> getHibearchyTreeData(String appId, String lookupType)
   {
     List<TreeNode> treeNodeList = new ArrayList();
     
     List<SysLookupHibearchy> list = getHibearchyList(appId, lookupType);
     HashMap<String, List<SysLookupHibearchy>> map = new HashMap();
     for (SysLookupHibearchy hibearchy : list) {
       List<SysLookupHibearchy> mapList = null;
       String parentId = hibearchy.getParentId();
       if (map.containsKey(parentId)) {
         mapList = (List)map.get(parentId);
         mapList.add(hibearchy);
         map.put(parentId, mapList);
       } else {
         mapList = new ArrayList();
         mapList.add(hibearchy);
         map.put(parentId, mapList);
       }
     }
     
 
     if (map.get("-1") != null) {
       List<SysLookupHibearchy> mapList = (List)map.get("-1");
       for (SysLookupHibearchy hibearchy : mapList) {
         String id = hibearchy.getId();
         TreeNode treeNode = hibearchyToTreeNode(hibearchy, false);
         
         List<TreeNode> childrenList = getChildrenNode(map, id);
         if (!childrenList.isEmpty()) {
           treeNode.setChildren(childrenList);
         }
         treeNodeList.add(treeNode);
       }
     }
     return treeNodeList;
   }
   
 
 
 
 
   public List<SysLookupHibearchy> getHibearchyList(String lookupType)
   {
     return getHibearchyList(SessionHelper.getApplicationId(), lookupType);
   }
   
 
 
 
 
 
   public List<SysLookupHibearchy> getHibearchyList(String appId, String lookupType)
   {
     String prefix = "PLATFORM6_LOOKUPHIBEARCHY_TYPE_" + lookupType;
     TypeReference<SysLookupHibearchy> typeReference = new TypeReference() {};
     List<SysLookupHibearchy> list = this.baseCacheManager.getAllFromCache(prefix, typeReference, appId);
     String rootId = getHibearchyRootId();
     SysLookupHibearchy root = (SysLookupHibearchy)this.baseCacheManager.getObjectFromCache("PLATFORM6_LOOKUPHIBEARCHY", rootId, typeReference);
     list.add(0, root);
     return list;
   }
   
 
 
 
 
 
   private List<TreeNode> getChildrenNode(HashMap<String, List<SysLookupHibearchy>> map, String parentId)
   {
     List<TreeNode> treeNodeList = new ArrayList();
     try {
       if (map.get(parentId) != null) {
         List<SysLookupHibearchy> list = (List)map.get(parentId);
         for (SysLookupHibearchy hibearchy : list) {
           String id = hibearchy.getId();
           TreeNode treeNode = hibearchyToTreeNode(hibearchy, false);
           
           List<TreeNode> childrenList = getChildrenNode(map, id);
           if (!childrenList.isEmpty()) {
             treeNode.setChildren(childrenList);
           }
           treeNodeList.add(treeNode);
         }
       }
     } catch (Exception e) {
       e.printStackTrace();
     }
     return treeNodeList;
   }
   
 
 
 
 
 
   private TreeNode hibearchyToTreeNode(SysLookupHibearchy bean, boolean isLazy)
   {
     TreeNode treeNode = new TreeNode();
     
     String id = bean.getId();
     String text = bean.getTypeValue();
     String parentId = bean.getParentId();
     String lookupType = bean.getLookupType();
     
     treeNode.setId(id);
     treeNode.setText(text);
     treeNode.set_parentId(parentId);
     
     if (isLazy) {
       treeNode.setState("closed");
     }
     if ("-1".equals(parentId)) {
       treeNode.setIconCls("icon-home");
     } else {
       treeNode.setIconCls("icon-dept");
     }
     
     HashMap<String, String> attributes = new HashMap();
     attributes.put("parentId", parentId);
     attributes.put("lookupType", lookupType);
     attributes.put("systemFlag", bean.getSystemFlag());
     attributes.put("validFlag", bean.getValidFlag());
     attributes.put("sysLanguageCode", bean.getSysLanguageCode());
     attributes.put("typeKey", bean.getTypeKey());
     attributes.put("orderBy", String.valueOf(bean.getOrderBy()));
     attributes.put("remark", bean.getRemark());
     treeNode.setAttributes(attributes);
     
     return treeNode;
   }
   
 
 
 
 
 
 
   public String getTypeValue(String lookupType, String typeKey)
   {
     return getTypeValueByAppId(SessionHelper.getApplicationId(), lookupType, typeKey);
   }
   
 
 
 
 
 
 
 
   public String getTypeValueByAppId(String appId, String lookupType, String typeKey)
   {
     String typeValue = "";
     List<SysLookupHibearchy> list = getHibearchyList(appId, lookupType);
     for (SysLookupHibearchy hibearchy : list) {
       if (hibearchy.getTypeKey().equals(typeKey)) {
         typeValue = hibearchy.getTypeValue();
         break;
       }
     }
     if ((typeValue == null) || ("".equals(typeValue))) {
       log.error("【" + lookupType + "】在应用" + appId + "中没有值为" + typeKey + "的多维通用代码值");
     }
     return typeValue;
   }
 }


