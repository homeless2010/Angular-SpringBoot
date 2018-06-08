 package com.piedpiper.platform.api.syspermissionresource.permissionanalysis.core.support;
 
 import com.piedpiper.platform.api.syspermissionresource.dto.SysPermissionResource;
 import com.piedpiper.platform.api.syspermissionresource.permissionanalysis.annotation.DataPermission;
 import com.piedpiper.platform.core.spring.SpringFactory;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 
 
 
 
 
 
 public abstract class AbstractDataCondition
   implements DataCondition
 {
   protected Logger logger = LoggerFactory.getLogger(AbstractDataCondition.class);
   protected AbstractDataCondition nextDataAccess;
   protected CompositeLoaderI compositeLoader = (CompositeLoaderI)SpringFactory.getBean("CompositeLoader");
   
   public AbstractDataCondition getNextDataAccess() {
     return this.nextDataAccess;
   }
   
   public abstract void setNextDataAccess(AbstractDataCondition paramAbstractDataCondition);
   
   public abstract String generateSqlCondition(SysPermissionResource paramSysPermissionResource, DataPermission paramDataPermission);
 }


