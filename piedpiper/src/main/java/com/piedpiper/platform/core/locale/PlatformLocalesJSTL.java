 package com.piedpiper.platform.core.locale;
 
 import java.util.Locale;
 import java.util.ResourceBundle;
 
 
 
 public class PlatformLocalesJSTL
 {
   public static String getBundleValue(String key, Locale locale)
   {
     try
     {
       ResourceBundle bundle = ResourceBundle.getBundle("i18n.platform", locale);
       return bundle.getString(key) == null ? key : bundle.getString(key);
     } catch (Exception ex) {}
     return key;
   }
   
   public static String getBundleValue(String key)
   {
     try {
       ResourceBundle bundle = ResourceBundle.getBundle("i18n.platform", new Locale("zh", "CN"));
       return bundle.getString(key) == null ? key : bundle.getString(key);
     } catch (Exception ex) {
       ex.printStackTrace(); }
     return key;
   }
   
 
 
 
   public static String getBundleValueforSearch(String key, Locale locale)
   {
     try
     {
       ResourceBundle bundle = ResourceBundle.getBundle("i18n.search", locale);
       return bundle.getString(key) == null ? key : bundle.getString(key);
     } catch (Exception ex) {}
     return key;
   }
   
 
 
 
 
 
   public static String getBundleValue(String key, String resource, Locale locale)
   {
     try
     {
       ResourceBundle bundle = ResourceBundle.getBundle(resource, locale);
       return bundle.getString(key) == null ? key : bundle.getString(key);
     } catch (Exception ex) {}
     return key;
   }
 }


