 package com.piedpiper.platform.core.exception;
 
 public class UtilsException
   extends RuntimeException
 {
   private static final long serialVersionUID = 7215415753519104121L;
   
   public UtilsException(String msg, Throwable cause)
   {
     super(msg, cause);
   }
   
   public UtilsException(String s) { super(s); }
 }


