 package com.piedpiper.platform.api.syspermissionresource.scantags.entity;
 
 import java.util.List;
 
 
 public class DataGridDefinitions
 {
   private String dataGridId;
   private String url;
   private String dataPermission;
   private String opts;
   private List<DataGridField> fieldList;
   
   public String getOpts()
   {
     return this.opts;
   }
   
 
   public void setOpts(String opts)
   {
     this.opts = opts;
   }
   
 
   public String getDataGridId()
   {
     return this.dataGridId;
   }
   
 
   public void setDataGridId(String dataGridId)
   {
     this.dataGridId = dataGridId;
   }
   
 
   public String getUrl()
   {
     return this.url;
   }
   
 
   public void setUrl(String url)
   {
     this.url = url;
   }
   
 
   public String getDataPermission()
   {
     return this.dataPermission;
   }
   
 
   public void setDataPermission(String dataPermission)
   {
     this.dataPermission = dataPermission;
   }
   
 
   public List<DataGridField> getFieldList()
   {
     return this.fieldList;
   }
   
 
   public void setFieldList(List<DataGridField> fieldList)
   {
     this.fieldList = fieldList;
   }
   
   public static class DataGridField
   {
     private String filedName;
     private String type;
     private String des;
     
     public String getDes()
     {
       return this.des;
     }
     
     public void setDes(String des) { this.des = des; }
     
     public String getFiledName() {
       return this.filedName;
     }
     
     public void setFiledName(String filedName) { this.filedName = filedName; }
     
     public String getType() {
       return this.type;
     }
     
     public void setType(String type) { this.type = type; }
   }
 }


