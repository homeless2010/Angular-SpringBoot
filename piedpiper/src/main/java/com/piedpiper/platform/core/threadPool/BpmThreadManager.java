 package com.piedpiper.platform.core.threadPool;
 
 import java.util.concurrent.ExecutorService;
 import java.util.concurrent.Executors;
 
 public class BpmThreadManager
 {
   private static ExecutorService service = null;
   
   public static ExecutorService getExecutorService() { if (null == service)
       service = Executors.newFixedThreadPool(5);
     return service;
   }
 }


