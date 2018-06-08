 package com.piedpiper.platform.api.sysapprole.dto;
 
 import java.util.HashMap;
import java.util.Map;

import javax.persistence.Id;

import com.piedpiper.platform.core.annotation.log.FieldRemark;
import com.piedpiper.platform.core.annotation.log.PojoRemark;
import com.piedpiper.platform.core.domain.BeanDTO;
import com.piedpiper.platform.core.properties.PlatformConstant;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheBean;
 
 @PojoRemark(table="sys_app_role", object="SysAppRole", name="应用角色权限关系")
 public class SysAppRole
   extends BeanDTO
   implements BaseCacheBean
 {
   public static final String SYSAPPROLE = "PLATFORM6_SYSAPPROLE";
   @Id
   @FieldRemark(column="ID", field="id", name="主键")
   private String id;
   @FieldRemark(column="SYSAPP_ID", field="sysappId", name="应用id")
   private String sysappId;
   @FieldRemark(column="SYSROLE_ID", field="sysroleId", name="角色id")
   private String sysroleId;
   @FieldRemark(column="SYSAPP_NAME", field="sysappName", name="应用名称")
   private String sysappName;
   @FieldRemark(column="SYSROLE_NAME", field="sysroleName", name="角色名称")
   private String sysroleName;
   private static final long serialVersionUID = -986497173056657557L;
   
   public String getId()
   {
     return this.id;
   }
   
   public void setId(String id) {
     this.id = id;
   }
   
   public String getSysappId() {
     return this.sysappId;
   }
   
   public void setSysappId(String sysappId) {
     this.sysappId = sysappId;
   }
   
   public String getSysroleId() {
     return this.sysroleId;
   }
   
   public void setSysroleId(String sysroleId) {
     this.sysroleId = sysroleId;
   }
   
   public String getSysappName() {
     return this.sysappName;
   }
   
   public void setSysappName(String sysappName) {
     this.sysappName = sysappName;
   }
   
   public String getSysroleName() {
     return this.sysroleName;
   }
   
   public void setSysroleName(String sysroleName) {
     this.sysroleName = sysroleName;
   }
   
 
 
 
 
 
   public String getLogFormName()
   {
     if ((this.logFormName == null) || (this.logFormName.equals(""))) {
       return "应用角色授权";
     }
     return this.logFormName;
   }
   
 
   public String getLogTitle()
   {
     if ((this.logTitle == null) || (this.logTitle.equals(""))) {
       return "SYS_APP_ROLE";
     }
     return this.logTitle;
   }
   
 
   public PlatformConstant.LogType getLogType()
   {
     return PlatformConstant.LogType.safety_manage;
   }
   
   public String returnId()
   {
     return this.sysappId + "_" + this.sysroleId;
   }
   
   public String returnValidFlag()
   {
     return null;
   }
   
   public Map<String, ?> returnCacheKey()
   {
     Map<String, Object> map = new HashMap();
     map.put("PLATFORM6_SYSAPPROLE", returnId());
     return map;
   }
   
   public String prefix()
   {
     return "PLATFORM6_SYSAPPROLE";
   }
   
   public String returnAppId()
   {
     return null;
   }
 }


