 package com.piedpiper.platform.core.annotation;
 
 import com.piedpiper.platform.core.annotation.log.FieldRemark;
 import com.piedpiper.platform.core.annotation.log.PojoRemark;
 import java.lang.annotation.Annotation;
 import java.lang.reflect.Field;
 import java.lang.reflect.Method;
 import java.util.ArrayList;
 import java.util.List;
 import javax.persistence.Table;
 import org.apache.commons.beanutils.PropertyUtils;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class AnnotationUtil
 {
   static Logger log = LoggerFactory.getLogger(AnnotationUtil.class);
   
 
 
 
 
   public static String getTableName(Object obj)
   {
     String tableName = "";
     Class<?> o = getClass(obj);
     if (o.isAnnotationPresent(Table.class)) {
       Table tabAnn = (Table)o.getAnnotation(Table.class);
       tableName = tabAnn.name();
     }
     return tableName;
   }
   
 
 
 
 
 
 
   public static List<String> getFieldName(Object obj, Class<? extends Annotation> annotationClass)
   {
     List<String> list = new ArrayList();
     Class<?> o = getClass(obj);
     Field[] fields = o.getDeclaredFields();
     for (int i = 0; i < fields.length; i++) {
       if (fields[i].isAnnotationPresent(annotationClass)) {
         String fieldName = fields[i].getName();
         list.add(fieldName);
       }
     }
     return list;
   }
   
 
 
 
 
 
 
   public static List<String> getMethodName(Object obj, Class<? extends Annotation> annotationClass)
   {
     List<String> list = new ArrayList();
     Class<?> o = getClass(obj);
     Method[] methods = o.getDeclaredMethods();
     log.trace(" 查找已添加注解的方法   ");
     for (int i = 0; i < methods.length; i++) {
       if (methods[i].isAnnotationPresent(annotationClass)) {
         String methodName = methods[i].getName();
         list.add(methodName);
       }
     }
     return list;
   }
   
 
 
 
 
 
   public static List<String> getFieldNameByMethodAnnotation(Object obj, Class<? extends Annotation> annotationClass)
   {
     List<String> list = new ArrayList();
     List<String> methods = getMethodName(obj, annotationClass);
     for (String method : methods) {
       String field = method.substring(3, 4).toLowerCase() + method.substring(4);
       list.add(field);
     }
     return list;
   }
   
 
 
 
 
 
   public static String getFieldAnnotationValue(Object obj, Class<? extends Annotation> AnnotationClass)
   {
     String fieldValue = "";
     List<String> list = getFieldName(obj, AnnotationClass);
     if ((list != null) && (list.size() > 0)) {
       try {
         Object value = PropertyUtils.getProperty(obj, (String)list.get(0));
         if ((value instanceof String)) {
           fieldValue = (String)value;
         }
       }
       catch (Exception e) {
         e.printStackTrace();
       }
     }
     return fieldValue;
   }
   
 
 
 
 
 
   public static String getMethodAnnotationValue(Object obj, Class<? extends Annotation> AnnotationClass)
   {
     String fieldValue = "";
     List<String> list = getMethodName(obj, AnnotationClass);
     if ((list != null) && (list.size() > 0)) {
       try {
         String method = (String)list.get(0);
         String field = method.substring(3, 4).toLowerCase() + method.substring(4);
         Object value = PropertyUtils.getProperty(obj, field);
         if ((value instanceof String)) {
           fieldValue = (String)value;
         }
       }
       catch (Exception e) {
         e.printStackTrace();
       }
     }
     return fieldValue;
   }
   
 
 
 
 
 
 
 
 
   private static Class<?> getClass(Object obj)
   {
     return obj.getClass();
   }
   
 
   public static PojoRemark getPojoRemark(Object obj)
   {
     Class<?> o = getClass(obj);
     if (o.isAnnotationPresent(PojoRemark.class)) {
       return (PojoRemark)o.getAnnotation(PojoRemark.class);
     }
     return null;
   }
   
   public static List<FieldRemark> getFieldRemark(Object obj) {
     List<FieldRemark> list = new ArrayList();
     Class<?> o = getClass(obj);
     Field[] fields = o.getDeclaredFields();
     for (int i = 0; i < fields.length; i++) {
       if (fields[i].isAnnotationPresent(FieldRemark.class)) {
         list.add(fields[i].getAnnotation(FieldRemark.class));
       }
     }
     return list;
   }
 }


