 package com.piedpiper.platform.api.sysshirolog.impl;
 
 import com.piedpiper.platform.api.sysuser.dto.SysUser;
 import java.util.Date;
 
 
 public class LogUserHolder
 {
   private static final ThreadLocal<SysUser> ThreadLocal_user = new ThreadLocal();
   private static final ThreadLocal<String> ThreadLocal_ip = new ThreadLocal();
   private static final ThreadLocal<Boolean> ThreadLocal_admin = new ThreadLocal();
   private static final ThreadLocal<Date> ThreadLocal_date = new ThreadLocal();
   private static final ThreadLocal<String> ThreadLocal_code = new ThreadLocal();
   
   public static void addDate(Date date) { ThreadLocal_date.set(date); }
   
   public static void removeDate(Date ip)
   {
     ThreadLocal_date.remove();
   }
   
   public static Date getDate() {
     return (Date)ThreadLocal_date.get();
   }
   
 
   public static void addLoginIp(String ip)
   {
     ThreadLocal_ip.set(ip);
   }
   
   public static void removeLoginIp(String ip) {
     ThreadLocal_ip.remove();
   }
   
   public static String getLoginIp() {
     return (String)ThreadLocal_ip.get();
   }
   
   public static void setAdmin(boolean ip) {
     ThreadLocal_admin.set(Boolean.valueOf(ip));
   }
   
   public static void removeAdmin(boolean ip) {
     ThreadLocal_admin.remove();
   }
   
   public static boolean isAdmin() {
     return ((Boolean)ThreadLocal_admin.get()).booleanValue();
   }
   
 
 
   public static void addSysUser(SysUser user)
   {
     ThreadLocal_user.set(user);
   }
   
   public static void removeSysUser(SysUser user) {
     ThreadLocal_user.remove();
   }
   
   public static SysUser getSysUser() {
     return (SysUser)ThreadLocal_user.get();
   }
   
   public static void clearAll() {
     ThreadLocal_user.remove();
     ThreadLocal_ip.remove();
     ThreadLocal_admin.remove();
     ThreadLocal_date.remove();
     ThreadLocal_code.remove();
   }
   
   public static void addLanguageCode(String user) {
     ThreadLocal_code.set(user);
   }
   
   public static void removeLanguageCode(String user) {
     ThreadLocal_code.remove();
   }
   
   public static String getLanguageCode() {
     return (String)ThreadLocal_code.get();
   }
 }


