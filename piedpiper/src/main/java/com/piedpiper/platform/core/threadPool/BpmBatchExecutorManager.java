 package com.piedpiper.platform.core.threadPool;
 
 import java.util.concurrent.ExecutorService;
 import java.util.concurrent.Semaphore;
 
 
 
 
 
 
 public class BpmBatchExecutorManager
 {
   private static ExecutorService service = null;
   
   public static ExecutorService getExecutorService() { if (null == service)
     {
       service = new MyFixedThreadPool(2000); }
     return service;
   }
   
   public static Semaphore getSemaphore() {
     return ((MyFixedThreadPool)service).getSemaphore();
   }
 }


