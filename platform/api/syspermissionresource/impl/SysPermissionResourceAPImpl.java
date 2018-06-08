 package com.piedpiper.platform.api.syspermissionresource.impl;
 
 import com.piedpiper.platform.api.syspermissionresource.SysPermissionResourceAPI;
 import com.piedpiper.platform.api.syspermissionresource.dto.SysPermissionAccess;
 import com.piedpiper.platform.api.syspermissionresource.dto.SysPermissionLeaddept;
 import com.piedpiper.platform.api.syspermissionresource.dto.SysPermissionResource;
 import com.piedpiper.platform.api.syspermissionresource.permissionanalysis.annotation.CustomBean;
 import com.piedpiper.platform.api.syspermissionresource.permissionanalysis.annotation.DataPermission;
 import com.piedpiper.platform.api.syspermissionresource.permissionanalysis.core.support.AbstractDataCondition;
 import com.piedpiper.platform.api.syspermissionresource.permissionanalysis.core.support.CompositeLoaderI;
 import com.piedpiper.platform.core.exception.DaoException;
 import com.piedpiper.platform.core.redisCacheManager.BaseCacheManager;
 import com.piedpiper.platform.core.rest.client.RestClient;
 import com.piedpiper.platform.core.rest.client.RestClientConfig;
 import com.piedpiper.platform.core.rest.msg.ResponseMsg;
 import com.piedpiper.platform.core.spring.SpringFactory;
 import com.fasterxml.jackson.core.type.TypeReference;
 import java.lang.annotation.Annotation;
 import java.util.ArrayList;
 import java.util.List;
 import javax.ws.rs.core.GenericType;
 import org.apache.commons.lang.StringUtils;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.beans.factory.annotation.Autowired;
 
 
 
 
 
 
 public class SysPermissionResourceAPImpl
   implements SysPermissionResourceAPI
 {
   private Logger logger = LoggerFactory.getLogger(SysPermissionResourceAPImpl.class);
   @Autowired
   private BaseCacheManager baseCacheManager;
   
   public void reLoad()
     throws Exception
   {
     String url = RestClientConfig.getRestHost("sysresource") + "/api/platform6/syspermission/SysPermission/reLoad/v1";
     ResponseMsg<Void> responseMsg = RestClient.doGet(url, new GenericType() {});
     if (!responseMsg.getRetCode().equals("200"))
     {
       throw new Exception(responseMsg.getErrorDesc());
     }
   }
   
   public List<String> getPermissionResourceByParentId(String parentId) throws DaoException
   {
     List<String> resultList = new ArrayList();
     List<SysPermissionResource> list = this.baseCacheManager.getAllFromCache("PLATFORM6_PERMISSIONRESOURCE_PARENT_" + parentId, new TypeReference() {});
     for (SysPermissionResource sysPermissionResource : list) {
       resultList.add(sysPermissionResource.getId());
     }
     return resultList;
   }
   
   public List<SysPermissionResource> getAllSysPermissionRes()
   {
     List<SysPermissionResource> list = this.baseCacheManager.getAllFromCache("PLATFORM6_PERMISSIONRESOURCE", new TypeReference() {});
     for (SysPermissionResource sysPermissionResource : list) {
       List<SysPermissionAccess> accessList = this.baseCacheManager.getAllFromCache("PLATFORM6_PERMISSIONACCESS_RESOURCE_" + sysPermissionResource.getId(), new TypeReference() {});
       sysPermissionResource.setSysPermissionAccess(accessList);
     }
     return list;
   }
   
   public SysPermissionResource getSysPermissionResourceByMetaData(String metaData)
   {
     List<SysPermissionResource> list = getAllSysPermissionRes();
     for (SysPermissionResource sysPermissionResource : list) {
       if (metaData.equals(sysPermissionResource.getMetadata())) {
         return sysPermissionResource;
       }
     }
     return null;
   }
   
   public String getCrossDeptByUserId(String userId)
   {
     SysPermissionLeaddept sysPermissionLeaddept = (SysPermissionLeaddept)this.baseCacheManager.getObjectFromCache("PLATFORM6_SYSPERMISSIONLEADDEPT_USER_", userId, SysPermissionLeaddept.class);
     if (sysPermissionLeaddept != null) {
       return sysPermissionLeaddept.getDeptId();
     }
     return null;
   }
   
 
 
   public void deleteSysPermissionResource(String id)
   {
     String url = RestClientConfig.getRestHost("sysresource") + "/api/platform6/syspermission/SysPermission/deleteSysPermissionResource/v1";
     ResponseMsg<Void> responseMsg = RestClient.doPost(url, id, new GenericType() {});
     if (responseMsg.getRetCode().equals("200")) {}
   }
   
 
 
 
 
   public String analyzePermissionSql(final String tableName, final String modelNane)
   {
     this.logger.debug("开始权限解析,对象为：" + modelNane);
     List<String> conditions = new ArrayList();
     
 
     CompositeLoaderI compositeLoader = (CompositeLoaderI)SpringFactory.getBean("CompositeLoader");
     
     SysPermissionResource sysPermRes = compositeLoader.getSysPermissionResourceByMetaData(modelNane);
     
 
 
     if ((sysPermRes != null) && (sysPermRes.getSql() != null) && (!"".equals(sysPermRes.getSql()))) {
       this.logger.debug("权限前置SQL=" + sysPermRes.getSql());
       conditions.add(StringUtils.replace("EXISTS({sql})", "{sql}", sysPermRes.getSql()));
     }
     DataPermission dd = new DataPermission()
     {
       public Class<? extends Annotation> annotationType()
       {
         return null;
       }
       
       public String TableName()
       {
         return tableName;
       }
       
       public String ModelName()
       {
         return modelNane;
       }
       
       public CustomBean BeanInfo()
       {
         return null;
       }
       
     };
     AbstractDataCondition dataAccess = (AbstractDataCondition)SpringFactory.getBean("avicit.globleDataAccess");
     conditions.add(dataAccess.generateSqlCondition(sysPermRes, dd));
     
     return StringUtils.join(conditions, " AND ");
   }
 }


