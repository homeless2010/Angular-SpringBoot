 package com.piedpiper.platform.core.exception;
 
 
 
 public class LoginException
   extends RuntimeException
 {
   private static final long serialVersionUID = -645291003775188572L;
   
 
 
   public LoginException(String msg, Throwable cause)
   {
     super(msg, cause);
   }
   
   public LoginException(String s) { super(s); }
 }


