 package com.piedpiper.platform.core.rest.auth;
 
 import java.io.File;
 import java.net.URL;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import org.dom4j.Document;
 import org.dom4j.Element;
 import org.dom4j.io.SAXReader;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 
 
 
 public class RestWhiteListConfig
 {
   static Logger logger = LoggerFactory.getLogger(RestWhiteListConfig.class);
   private static final String CONFIG_LOCATION = "rest-auth.xml";
   private static Map<String, String> ipMap = null;
   
 
 
   public static void load()
   {
     ipMap = new HashMap();
     parseXml();
   }
   
   public static void parseXml()
   {
     try
     {
       SAXReader reader = new SAXReader();
       ClassLoader cl = RestWhiteListConfig.class.getClassLoader();
       URL url = cl.getResource("rest-auth.xml");
       Document d = reader.read(new File(url.getFile()));
       Element root = d.getRootElement();
       List<Element> list = root.elements("ip");
       for (Element e : list) {
         String ip = e.getText();
         ipMap.put(ip, ip);
         logger.info("Rest服务白名单：" + ip);
       }
     } catch (Exception e) {
       logger.error("解析rest-white-list.xml文件出错：", e);
     }
   }
   
 
 
 
   public static boolean check(String ip)
   {
     if (ipMap == null) {
       load();
     }
     String ipValue = (String)ipMap.get(ip);
     if ((ipValue != null) && (!ipValue.equals(""))) {
       return true;
     }
     return false;
   }
 }


