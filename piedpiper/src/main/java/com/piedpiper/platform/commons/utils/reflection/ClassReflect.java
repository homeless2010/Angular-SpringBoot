 package com.piedpiper.platform.commons.utils.reflection;
 
 import java.lang.reflect.Constructor;
 import java.lang.reflect.InvocationTargetException;
 
 public class ClassReflect
 {
   public static Object getInstance(String className, Class[] parameterTypes, Object[] initargs) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, InstantiationException, IllegalAccessException
   {
     return getInstance(getConstructor(className, parameterTypes), initargs);
   }
   
   public static Object getInstance(Constructor cons, Object[] initargs) throws IllegalArgumentException, InvocationTargetException, InstantiationException, IllegalAccessException {
     Object obj = null;
     obj = cons.newInstance(initargs);
     return obj;
   }
   
   public static Object getInstance(Class reflectClass, String factoryMethod, Class[] parameterTypes, Object[] initargs) throws ClassNotFoundException, IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException
   {
     return reflectClass.getMethod(factoryMethod, parameterTypes).invoke(null, initargs);
   }
   
   public static Constructor getConstructor(String className, Class[] parameterTypes) throws ClassNotFoundException, NoSuchMethodException, SecurityException {
     Constructor con = null;
     Class reflectClass = Class.forName(className);
     con = reflectClass.getConstructor(parameterTypes);
     return con;
   }
 }


