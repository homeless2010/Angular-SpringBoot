 package com.piedpiper.platform.core.rest.client;
 
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
 
 
 
 public class RestClientConfig
 {
   static Logger logger = LoggerFactory.getLogger(RestClientConfig.class);
   
   private static final String CONFIG_LOCATION = "rest-client.xml";
   private static Map<String, String> restHostMap = null;
   private static Map<String, String> restRemoteMap = null;
   public static String systemid = "";
   public static String username = "";
   public static String password = "";
   
 
 
 
 
 
 
   public static boolean isServer = false;
   
   public static final String main = "main";
   
   public static final String sysuser = "sysuser";
   public static final String syslookup = "syslookup";
   public static final String sysmenu = "sysmenu";
   public static final String sysresource = "sysresource";
   public static final String sysprofile = "sysprofile";
   public static final String syscustomed = "syscustomed";
   public static final String sysapplication = "sysapplication";
   public static final String sysapprole = "sysapprole";
   public static final String syslanguage = "syslanguage";
   public static final String sysmessage = "sysmessage";
   public static final String syshirosecurity = "syshirosecurity";
   public static final String syslog = "syslog";
   public static final String bizlog = "bizlog";
   public static final String session = "session";
   public static final String bpmengine = "bpmengine";
   public static final String bpmadmin = "bpmadmin";
   public static final String bpmclient = "bpmclient";
   public static final String restmanage = "restmanage";
   public static final String search = "search";
   public static final String quartz = "quartz";
   public static final String sanYuan = "sanYuan";
   public static final String sysPortal = "sysPortal";
   public static final String eform = "eform";
   public static final String syscoding = "syscoding";
   public static final String sysattachment = "sysattachment";
   public static final String sync = "sync";
   public static final String mobile = "mobile";
   
   static
   {
     load();
   }
   
 
   public static void load()
   {
     restHostMap = new HashMap();
     restRemoteMap = new HashMap();
     parseXml();
   }
   
   public static void parseXml()
   {
     try
     {
       SAXReader reader = new SAXReader();
       ClassLoader cl = RestClientConfig.class.getClassLoader();
       URL url = cl.getResource("rest-client.xml");
       Document d = reader.read(new File(url.getFile()));
       Element root = d.getRootElement();
       systemid = root.attributeValue("systemid");
       username = root.attributeValue("username");
       password = root.attributeValue("password");
       isServer = Boolean.parseBoolean(root.attributeValue("isServer"));
       if (isServer) {
         logger.info("本系统运行在平台服务器端");
       } else {
         logger.info("本系统运行在平台客户器端");
       }
       List<Element> list = root.elements("resthost");
       for (Element e : list) {
         String hostKey = e.attributeValue("key");
         String restUrl = e.attributeValue("url");
         String remote = e.attributeValue("remote");
         restHostMap.put(hostKey, restUrl);
         restRemoteMap.put(hostKey, remote);
         logger.info("客户端可以访问的Rest服务主机：" + restUrl + ",remote=" + remote);
       }
     } catch (Exception e) {
       logger.error("解析rest-client.xml文件出错：", e);
     }
   }
   
 
 
 
   public static String getRestHost(String key)
   {
     if (restHostMap == null) {
       load();
     }
     if ((key == null) || (key.equals(""))) {
       key = "main";
     }
     return (String)restHostMap.get(key);
   }
   
 
 
 
   public static String getIsRemote(String key)
   {
     if (restHostMap == null) {
       load();
     }
     if ((key == null) || (key.equals(""))) {
       key = "main";
     }
     return (String)restRemoteMap.get(key);
   }
 }


