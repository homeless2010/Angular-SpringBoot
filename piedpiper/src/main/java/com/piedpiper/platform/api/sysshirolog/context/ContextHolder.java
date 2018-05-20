 package com.piedpiper.platform.api.sysshirolog.context;
 
 import com.piedpiper.platform.api.sysshirolog.utils.HttpRequestResponseHolder;
 import com.piedpiper.platform.core.shiroSecurity.shiroUtil.PropertiesConfigurationLoader;
 import java.util.Properties;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class ContextHolder
 {
   private static PropertiesConfigurationLoader propertiesConfiguration;
   private static String AVICIT_TEMP_DIR;
   
   public static String getPlatformTempFileStorePath()
   {
     return AVICIT_TEMP_DIR;
   }
   
 
 
 
   private static final ThreadLocal<FrameworkContext> frameworkContextThreadLocal = new ThreadLocal();
   
 
 
   private static final ThreadLocal<String> midThreadLocal = new ThreadLocal();
   
 
 
   private static final ThreadLocal<HttpServletResponse> responseThreadLocal = new ThreadLocal();
   
 
 
   private static final ThreadLocal<HttpServletRequest> requestThreadLocal = new ThreadLocal();
   
 
 
 
   private static final ThreadLocal<String> currentDataSourceNameThreadLocal = new ThreadLocal();
   
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
   public static final void setCurrentDataSourceName(String dataSourceName)
   {
     currentDataSourceNameThreadLocal.set(dataSourceName);
   }
   
 
 
 
 
   public static final String getCurrentDataSourceName()
   {
     return (String)currentDataSourceNameThreadLocal.get();
   }
   
   public static HttpServletRequest getRequest() {
     return (HttpServletRequest)requestThreadLocal.get();
   }
   
   public static FrameworkContext getContext() {
     return (FrameworkContext)frameworkContextThreadLocal.get();
   }
   
   public static void setContext(FrameworkContext context) {
     frameworkContextThreadLocal.set(context);
   }
   
 
 
 
 
   public static HttpServletResponse getResponse()
   {
     return (HttpServletResponse)responseThreadLocal.get();
   }
   
 
 
 
 
 
 
 
   public static void setRequestAttribute(String key, Object obj)
   {
     ((HttpServletRequest)requestThreadLocal.get()).setAttribute(key, obj);
   }
   
 
 
 
 
 
 
   public static Object getRequestAttribute(String key)
   {
     return ((HttpServletRequest)requestThreadLocal.get()).getAttribute(key);
   }
   
 
 
 
 
 
 
   public static String getRequestParameter(String key)
   {
     return ((HttpServletRequest)requestThreadLocal.get()).getParameter(key);
   }
   
 
 
 
 
   public static String getMid()
   {
     return (String)midThreadLocal.get();
   }
   
 
 
 
 
 
   public static void setMid(String mid)
   {
     midThreadLocal.set(mid);
   }
   
   public static void removeCurrentDataSourceName() {
     currentDataSourceNameThreadLocal.remove();
   }
   
   public static void clearContext() {
     frameworkContextThreadLocal.remove();
     responseThreadLocal.remove();
     midThreadLocal.remove();
     requestThreadLocal.remove();
     currentDataSourceNameThreadLocal.remove();
   }
   
 
 
 
 
 
   public static void setHttpRequestResponseHolder(HttpRequestResponseHolder requestResponseHolder)
   {
     responseThreadLocal.set(requestResponseHolder.getResponse());
     requestThreadLocal.set(requestResponseHolder.getRequest());
   }
   
   public static String getPlatformProperty(String key) {
     return propertiesConfiguration.getProperties().getProperty(key);
   }
   
   public static Properties getPropertiesConfiguration() {
     return propertiesConfiguration.getProperties();
   }
 }


