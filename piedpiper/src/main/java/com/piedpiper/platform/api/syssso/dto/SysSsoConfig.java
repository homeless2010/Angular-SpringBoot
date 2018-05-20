 package com.piedpiper.platform.api.syssso.dto;
 
 import java.io.Serializable;
 import java.util.HashMap;
 import java.util.Map;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class SysSsoConfig
   implements Serializable
 {
   private static final long serialVersionUID = -4556622125743477391L;
   private String id;
   private Long version;
   private String ssoType;
   private String ssoConfig;
   private String ssoEnableFlg;
   private String sysapplicationId;
   private Map<String, Object> extFields = new HashMap();
   
 
 
 
 
   public SysSsoConfig() {}
   
 
 
 
 
   public SysSsoConfig(String ssoType, String ssoConfig, String ssoEnableFlg)
   {
     this.ssoType = ssoType;
     this.ssoConfig = ssoConfig;
     this.ssoEnableFlg = ssoEnableFlg;
   }
   
   public String getId()
   {
     return this.id;
   }
   
   public void setId(String id) {
     this.id = id;
   }
   
   public Long getVersion() {
     return this.version;
   }
   
   public void setVersion(Long version) {
     this.version = version;
   }
   
   public String getSsoType()
   {
     return this.ssoType;
   }
   
   public void setSsoType(String ssoType) {
     this.ssoType = ssoType;
   }
   
   public String getSsoConfig()
   {
     return this.ssoConfig;
   }
   
   public void setSsoConfig(String ssoConfig) {
     this.ssoConfig = ssoConfig;
   }
   
   public String getSsoEnableFlg()
   {
     return this.ssoEnableFlg;
   }
   
 
 
   public void setSsoEnableFlg(String ssoEnableFlg) { this.ssoEnableFlg = ssoEnableFlg; }
   
   public Object getExtField(String name) {
     synchronized (this) {
       if (this.extFields == null) {
         this.extFields = new HashMap();
         return null;
       }
     }
     return this.extFields.get(name);
   }
   
   public void setExtField(String name, Object value) {
     synchronized (this) {
       if (this.extFields == null) {
         this.extFields = new HashMap();
       }
     }
     this.extFields.put(name, value);
   }
   
   public void setExtFields(Map<String, Object> extFields) { this.extFields = extFields; }
   
   public Map<String, Object> getExtFields()
   {
     return this.extFields;
   }
   
   public String getSysapplicationId() {
     return this.sysapplicationId;
   }
   
   public void setSysapplicationId(String sysapplicationId) {
     this.sysapplicationId = sysapplicationId;
   }
 }


