 package com.piedpiper.platform.core.rest.msg;
 
 import java.io.Serializable;
 
 
 
 public class LogBase
   implements Serializable
 {
   private static final long serialVersionUID = 1557804288890935198L;
   private String sysApplicationId;
   private String syslogUsername;
   private String syslogUsernameZh;
   private String syslogIp;
   private boolean isCas;
   
   public LogBase()
   {
     this.isCas = false;
   }
   
 
   public LogBase(String sysApplicationId, String syslogUsername, String syslogUsernameZh, String syslogIp)
   {
     this.sysApplicationId = sysApplicationId;
     this.syslogUsername = syslogUsername;
     this.syslogUsernameZh = syslogUsernameZh;
     this.syslogIp = syslogIp;
     this.isCas = false;
   }
   
   public boolean isCas()
   {
     return this.isCas;
   }
   
   public void setCas(boolean isCas)
   {
     this.isCas = isCas;
   }
   
   public String getSysApplicationId()
   {
     return this.sysApplicationId;
   }
   
   public void setSysApplicationId(String sysApplicationId) { this.sysApplicationId = sysApplicationId; }
   
   public String getSyslogUsername() {
     return this.syslogUsername;
   }
   
   public void setSyslogUsername(String syslogUsername) { this.syslogUsername = syslogUsername; }
   
   public String getSyslogIp() {
     return this.syslogIp;
   }
   
   public void setSyslogIp(String syslogIp) { this.syslogIp = syslogIp; }
   
   public String getSyslogUsernameZh() {
     return this.syslogUsernameZh;
   }
   
   public void setSyslogUsernameZh(String syslogUsernameZh) {
     this.syslogUsernameZh = syslogUsernameZh;
   }
 }


