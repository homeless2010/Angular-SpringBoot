 package com.piedpiper.platform.core.redisCacheManager;
 
 import java.lang.reflect.Field;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Map;
 
 
 
 
 
 
 
 
 
 
 public class BaseCacheUtil
 {
   public static boolean isNull(Object string)
   {
     if ((string == null) || (string.equals(""))) {
       return true;
     }
     return false;
   }
   
 
 
 
 
 
   public static boolean vadidationArgs(Object[] args)
   {
     if ((args.length != 1) || (!(args[0] instanceof BaseCacheBean)))
       return false;
     return true;
   }
   
 
 
 
 
   public static boolean vadidationArgsIsString(Object[] args)
   {
     if ((args.length != 1) || (!(args[0] instanceof String)))
       return false;
     return true;
   }
   
 
 
 
 
   public static Map<String, ?> getKey(BaseCacheBean object)
   {
     return object.returnCacheKey();
   }
   
 
 
 
 
   public static String getId(BaseCacheBean object)
   {
     return object.returnId();
   }
   
 
 
 
 
   public static String getPrefix(BaseCacheBean object)
   {
     return object.prefix();
   }
   
 
 
 
 
 
   public static boolean isValid(BaseCacheBean baseBean)
   {
     if ((baseBean.returnValidFlag() == null) || ("1".equals(baseBean.returnValidFlag()))) {
       return true;
     }
     return false;
   }
   
 
 
 
 
 
 
   public static boolean isAppId(BaseCacheBean baseBean, String appId)
   {
     if ((baseBean.returnAppId() == null) || ("".equals(baseBean.returnAppId())) || (appId.equals(baseBean.returnAppId()))) {
       return true;
     }
     return false;
   }
   
 
 
 
 
 
   public static List<String> getFieldStringList(Class<?> c)
     throws Exception
   {
     List<String> resultList = new ArrayList();
     Field[] fields = c.getFields();
     for (int i = 0; i < fields.length; i++) {
       resultList.add(fields[i].get(c).toString());
     }
     return resultList;
   }
 }


