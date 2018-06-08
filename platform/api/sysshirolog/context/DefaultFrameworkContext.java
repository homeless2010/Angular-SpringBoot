 package com.piedpiper.platform.api.sysshirolog.context;
 
 import com.piedpiper.platform.api.session.dto.SecurityUser;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class DefaultFrameworkContext
   extends FrameworkContext
 {
   private static final long serialVersionUID = 5825531044361146552L;
   private String loginFormUrl;
   
   public SecurityUser getLoginUser()
   {
     if (this.loginUser == null) {
       throw new RuntimeException("");
     }
     return this.loginUser;
   }
   
   public String getLoginUsername()
   {
     if (this.loginUsername == null) {
       throw new RuntimeException("");
     }
     return this.loginUsername;
   }
   
   public String getLoginFormUrl()
   {
     return this.loginFormUrl;
   }
   
   public void setLoginFormUrl(String loginFormUrl) {
     this.loginFormUrl = loginFormUrl;
   }
 }


