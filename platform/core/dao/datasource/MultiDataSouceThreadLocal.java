 package com.piedpiper.platform.core.dao.datasource;
 
 
 
 
 
 
 
 
 public class MultiDataSouceThreadLocal
 {
   private static ThreadLocal<String> threadLocal = new ThreadLocal();
   
   public static void setCurrentDataSource(String dataSourceName) {
     threadLocal.set(dataSourceName);
   }
   
   public static String getCurrentDataSource() {
     return (String)threadLocal.get();
   }
 }


