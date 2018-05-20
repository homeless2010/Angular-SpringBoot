 package com.piedpiper.platform.core.rest.client;
 
 
 
 public class RestException
   extends RuntimeException
 {
   private static final long serialVersionUID = 6940853139061922461L;
   
 
   public RestException() {}
   
 
   public RestException(String msg, Throwable cause)
   {
     super(msg);
     super.initCause(cause);
   }
   
   public RestException(String msg) { super(msg); }
   
 
   public RestException(Throwable cause)
   {
     super.initCause(cause);
   }
 }


