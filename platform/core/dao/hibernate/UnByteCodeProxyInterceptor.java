 package com.piedpiper.platform.core.dao.hibernate;
 
 import org.hibernate.EmptyInterceptor;
 
 
 
 
 
 
 
 
 public class UnByteCodeProxyInterceptor
   extends EmptyInterceptor
 {
   private static final long serialVersionUID = -6422637558312349795L;
   
   public String getEntityName(Object object)
   {
     if (object != null) {
       Class<?> cl = object.getClass();
       
 
 
       return cl.getName();
     }
     
     return null;
   }
 }


