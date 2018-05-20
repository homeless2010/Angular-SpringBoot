 package com.piedpiper.platform.core.sfn.condition;
 
 import java.util.Map;
 
 public class StructuredQueryParam
 {
   private String sql;
   private Map<String, Object> param;
   
   public String getSql()
   {
     return this.sql;
   }
   
   public void setSql(String sql) {
     this.sql = sql;
   }
   
   public Map<String, Object> getParam() {
     return this.param;
   }
   
   public void setParam(Map<String, Object> param) {
     this.param = param;
   }
 }


