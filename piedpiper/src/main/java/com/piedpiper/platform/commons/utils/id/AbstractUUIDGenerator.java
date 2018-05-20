 package com.piedpiper.platform.commons.utils.id;
 
 import java.net.InetAddress;
 
 public abstract class AbstractUUIDGenerator
 {
   private static final int IP;
   
   static {
     int ipadd;
     try {
       ipadd = BytesHelper.toInt(InetAddress.getLocalHost().getAddress());
     }
     catch (Exception e) {
       ipadd = 0;
     }
     IP = ipadd; }
   
   private static short counter = 0;
   private static final int JVM = (int)(System.currentTimeMillis() >>> 8);
   
 
 
 
 
 
 
   protected int getJVM()
   {
     return JVM;
   }
   
 
 
 
   protected short getCount()
   {
     synchronized (AbstractUUIDGenerator.class) {
       if (counter < 0) counter = 0;
       return counter++;
     }
   }
   
 
 
   protected int getIP()
   {
     return IP;
   }
   
 
 
   protected short getHiTime()
   {
     return (short)(int)(System.currentTimeMillis() >>> 32);
   }
   
   protected int getLoTime() { return (int)System.currentTimeMillis(); }
 }


