 package com.piedpiper.platform.api.sysshirolog.utils;
 
 public class NotApplictionIdException
   extends RuntimeException
 {
   private static final long serialVersionUID = -8419334306393820051L;
   
   public NotApplictionIdException(String msg)
   {
     super(msg);
   }
   
   public NotApplictionIdException(String msg, Throwable t)
   {
     super(msg, t);
   }
 }


