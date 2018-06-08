 package com.piedpiper.platform.core.sfn.intercept;
 
 import java.util.Map;
 
 class SelfDefinedQueryTrigger {
   public static final ThreadLocal<Map<String, Object>> SelfDefinedQueryParam = new ThreadLocal();
 }


