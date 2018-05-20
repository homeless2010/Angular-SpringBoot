 package com.piedpiper.platform.core.exception;
 
 
 
 
 
 
 
 
 
 
 public class AuthenticationServiceException
   extends RuntimeException
 {
   private static final long serialVersionUID = -8419334306393820051L;
   
 
 
 
 
 
 
 
 
 
   public AuthenticationServiceException(String msg)
   {
     super(msg);
   }
   
   public AuthenticationServiceException(String msg, Throwable t)
   {
     super(msg, t);
   }
 }


