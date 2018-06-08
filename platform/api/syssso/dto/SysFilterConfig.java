 package com.piedpiper.platform.api.syssso.dto;
 
 import java.io.Serializable;
 import java.util.HashMap;
 import java.util.Map;
 
 
 
 
 
 
 
 public class SysFilterConfig
   implements Serializable
 {
   private static final long serialVersionUID = 7101149377630205059L;
   private String id;
   private Long version;
   private String filterClassName;
   private String filterEnableFlg;
   private Map<String, Object> extFields = new HashMap();
   
 
 
 
 
   public SysFilterConfig() {}
   
 
 
 
 
   public SysFilterConfig(String filterClassName, String filterEnableFlg)
   {
     this.filterClassName = filterClassName;
     this.filterEnableFlg = filterEnableFlg;
   }
   
   public String getId() {
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
   
 
   public String getFilterClassName()
   {
     return this.filterClassName;
   }
   
   public void setFilterClassName(String filterClassName) {
     this.filterClassName = filterClassName;
   }
   
   public String getFilterEnableFlg() {
     return this.filterEnableFlg;
   }
   
   public void setFilterEnableFlg(String filterEnableFlg) {
     this.filterEnableFlg = filterEnableFlg;
   }
   
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
 }


