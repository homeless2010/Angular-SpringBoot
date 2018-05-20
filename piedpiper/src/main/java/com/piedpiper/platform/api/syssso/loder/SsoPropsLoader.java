 package com.piedpiper.platform.api.syssso.loder;
 
 import com.piedpiper.platform.api.syssso.ISsoPropsInitService;
 import com.piedpiper.platform.commons.utils.SerializeUtil;
 import com.piedpiper.platform.core.redis.JedisSentinelPool;
 import com.piedpiper.platform.core.spring.SpringFactory;
 import java.util.Iterator;
 import java.util.Properties;
 import java.util.Set;
 import java.util.concurrent.ConcurrentHashMap;
 import org.apache.commons.logging.Log;
 import org.apache.commons.logging.LogFactory;
 import org.dom4j.Document;
 import org.dom4j.DocumentHelper;
 import org.dom4j.Element;
 import redis.clients.jedis.ShardedJedis;
 import redis.clients.jedis.exceptions.JedisConnectionException;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class SsoPropsLoader
 {
   private static Log log = LogFactory.getLog(SsoPropsLoader.class);
   
 
 
 
 
   public static ConcurrentHashMap<String, Properties> ccssoMap = new ConcurrentHashMap();
   
 
 
   public static Properties fillerConfigProps = new Properties();
   
   private static ISsoPropsInitService ssoPropsInitService = (ISsoPropsInitService)SpringFactory.getBean(ISsoPropsInitService.class);
   
 
 
 
 
 
 
 
 
 
 
 
 
 
 
   public static void load()
     throws Exception
   {
     log.info("==加载sso conifg==");
     
     if (ccssoMap != null) {
       ccssoMap.clear();
     }
     if (fillerConfigProps != null) {
       fillerConfigProps.clear();
     }
     
     fillerConfigProps = new Properties();
     ssoPropsInitService.initSsoProps();
   }
   
 
 
 
 
 
 
 
 
 
 
 
 
 
   public static String ssoConfig2XmlString(Properties ssoConfig)
   {
     Document document = DocumentHelper.createDocument();
     Element root = document.addElement("sso-params");
     Set<Object> fsset = ssoConfig.keySet();
     Iterator<Object> fcitr = fsset.iterator();
     while (fcitr.hasNext()) {
       String key = (String)fcitr.next();
       String v = ssoConfig.getProperty(key);
       Element param = root.addElement("ssoParam");
       Element ek = param.addElement("key");
       ek.setText(key);
       Element ev = param.addElement("value");
       ev.setText(v);
     }
     
 
     return root.asXML();
   }
   
   public static Properties getSSoProperties(String key)
   {
     JedisSentinelPool pool = pool = (JedisSentinelPool)SpringFactory.getBean("jedisSentinelPool");
     Properties ssoMapItem = null;
     ShardedJedis jedis = null;
     try {
       jedis = (ShardedJedis)pool.getResource();
     } catch (JedisConnectionException e) {
       pool.returnBrokenResource(jedis);
     }
     if (jedis != null) {
       try
       {
         ssoMapItem = (Properties)SerializeUtil.unserialize(jedis.get(key.getBytes()));
       } catch (Exception e) {
         pool.returnBrokenResource(jedis);
         e.printStackTrace();
       } finally {
         if (jedis != null) pool.returnResource(jedis);
       }
     }
     return ssoMapItem;
   }
 }


