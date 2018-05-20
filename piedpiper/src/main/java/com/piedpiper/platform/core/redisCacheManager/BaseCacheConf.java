 package com.piedpiper.platform.core.redisCacheManager;
 
 import java.io.Serializable;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class BaseCacheConf
   implements Serializable
 {
   private static final long serialVersionUID = 5577568419199151305L;
   public static final String flg = "6fd2e0ba-e28a-49ad-88f1-6a70727e6dfb";
   private String prefixString;
   private String idString;
   
   public BaseCacheConf() {}
   
   public BaseCacheConf(String prefixString, String idString)
   {
     this.prefixString = prefixString;
     this.idString = idString;
   }
   
   public String getPrefixString() { return this.prefixString; }
   
   public void setPrefixString(String prefixString) {
     this.prefixString = prefixString;
   }
   
   public String getIdString() { return this.idString; }
   
   public void setIdString(String idString) {
     this.idString = idString;
   }
   
   public String getFlg() { return "6fd2e0ba-e28a-49ad-88f1-6a70727e6dfb"; }
   
   public void setFlg(String flg) {}
 }


