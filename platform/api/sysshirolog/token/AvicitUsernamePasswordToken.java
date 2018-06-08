 package com.piedpiper.platform.api.sysshirolog.token;
 
 import com.piedpiper.platform.core.rest.msg.LogBase;
 import org.apache.shiro.authc.UsernamePasswordToken;
 
 public class AvicitUsernamePasswordToken
   extends UsernamePasswordToken
 {
   private static final long serialVersionUID = 7886357106366350864L;
   private String username;
   private char[] password;
   private LogBase logbase;
   private boolean casFlag;
   private boolean rememberMe = false;
   private String host;
   
   public AvicitUsernamePasswordToken() {}
   
   public AvicitUsernamePasswordToken(String username, char[] password)
   {
     this(username, password, false, null);
   }
   
   public AvicitUsernamePasswordToken(String username, String password) {
     this(username, password != null ? password.toCharArray() : null, false, null);
   }
   
 
   public AvicitUsernamePasswordToken(String username, char[] password, String host)
   {
     this(username, password, false, host);
   }
   
   public AvicitUsernamePasswordToken(String username, String password, String host)
   {
     this(username, password != null ? password.toCharArray() : null, false, host);
   }
   
 
   public AvicitUsernamePasswordToken(String username, char[] password, boolean rememberMe)
   {
     this(username, password, rememberMe, null);
   }
   
   public AvicitUsernamePasswordToken(String username, String password, boolean rememberMe)
   {
     this(username, password != null ? password.toCharArray() : null, rememberMe, null);
   }
   
   public AvicitUsernamePasswordToken(String username, String password, boolean casFlag, LogBase logbase)
   {
     this.username = username;
     this.password = (password != null ? password.toCharArray() : null);
     this.casFlag = casFlag;
     this.logbase = logbase;
   }
   
   public AvicitUsernamePasswordToken(String username, char[] password, boolean rememberMe, String host)
   {
     this.username = username;
     this.password = password;
     this.rememberMe = rememberMe;
     this.host = host;
   }
   
   public AvicitUsernamePasswordToken(String username, String password, boolean rememberMe, String host)
   {
     this(username, password != null ? password.toCharArray() : null, rememberMe, host);
   }
   
 
   public AvicitUsernamePasswordToken(String username, String password, LogBase logbase)
   {
     this.username = username;
     this.password = (password != null ? password.toCharArray() : null);
     this.logbase = logbase;
   }
   
   public String getUsername()
   {
     return this.username;
   }
   
   public void setUsername(String username) {
     this.username = username;
   }
   
   public char[] getPassword() {
     return this.password;
   }
   
   public void setPassword(char[] password) {
     this.password = password;
   }
   
   public Object getPrincipal() {
     return getUsername();
   }
   
   public Object getCredentials() {
     return getPassword();
   }
   
   public String getHost() {
     return this.host;
   }
   
   public void setHost(String host) {
     this.host = host;
   }
   
   public boolean isRememberMe() {
     return this.rememberMe;
   }
   
   public void setRememberMe(boolean rememberMe) {
     this.rememberMe = rememberMe;
   }
   
   public void clear() {
     this.username = null;
     this.host = null;
     this.rememberMe = false;
     
     if (this.password != null) {
       for (int i = 0; i < this.password.length; i++) {
         this.password[i] = '\000';
       }
       this.password = null;
     }
   }
   
   public LogBase getLogbase() {
     return this.logbase;
   }
   
   public void setLogbase(LogBase logbase) {
     this.logbase = logbase;
   }
   
   public boolean getCasFlag()
   {
     return this.casFlag;
   }
   
   public void setCasFlag(boolean casFlag) {
     this.casFlag = casFlag;
   }
   
   public String toString() {
     StringBuilder sb = new StringBuilder();
     sb.append(getClass().getName());
     sb.append(" - ");
     sb.append(this.username);
     sb.append(", rememberMe=").append(this.rememberMe);
     if (this.host != null) {
       sb.append(" (").append(this.host).append(")");
     }
     return sb.toString();
   }
 }


