 package com.piedpiper.platform.api.syssso.impl;
 
 import com.piedpiper.platform.api.syssso.ISsoPropsInitService;
 import com.piedpiper.platform.api.syssso.dto.SysFilterConfig;
 import com.piedpiper.platform.api.syssso.dto.SysSsoConfig;
 import com.piedpiper.platform.api.syssso.loder.SsoPropsLoader;
 import com.piedpiper.platform.commons.utils.SerializeUtil;
 import com.piedpiper.platform.core.redis.JedisSentinelPool;
 import com.piedpiper.platform.core.rest.client.RestClient;
 import com.piedpiper.platform.core.rest.client.RestClientConfig;
 import com.piedpiper.platform.core.rest.msg.Muti3Bean;
 import com.piedpiper.platform.core.rest.msg.ResponseMsg;
 import com.piedpiper.platform.core.shiroSecurity.cas.util.XMLUtil;
 import com.piedpiper.platform.core.spring.SpringFactory;
 import java.util.List;
 import java.util.Properties;
 import java.util.concurrent.ConcurrentHashMap;
 import javax.ws.rs.core.GenericType;
 import org.apache.commons.logging.Log;
 import org.apache.commons.logging.LogFactory;
 import org.w3c.dom.Document;
 import org.w3c.dom.Element;
 import org.w3c.dom.NodeList;
 import redis.clients.jedis.ShardedJedis;
 import redis.clients.jedis.exceptions.JedisConnectionException;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class SsoPropsInitServiceAPI
   implements ISsoPropsInitService
 {
   private static Log log = LogFactory.getLog(SsoPropsInitServiceAPI.class);
   
   private JedisSentinelPool pool = null;
   
   public void initSsoProps() throws Exception
   {
     this.pool = ((JedisSentinelPool)SpringFactory.getBean("jedisSentinelPool"));
     initFillerConfigProps();
     initSsoMap();
     initccSsoMap();
   }
   
   public void initFillerConfigProps() throws Exception { log.info("init fillerConfigProps--------");
     List<SysFilterConfig> flst = getFillerConfigList();
     for (SysFilterConfig sfc : flst) {
       String key = sfc.getFilterClassName();
       String v = sfc.getFilterEnableFlg();
       SsoPropsLoader.fillerConfigProps.put(key, v);
       
       log.info("key=" + key + ",v=" + v);
     }
   }
   
   public void initSsoMap() throws Exception {
     List<SysSsoConfig> slst = getSsoConfigList();
     log.info("init ssoMap--------");
     for (SysSsoConfig sfc : slst) {
       String xmlconfig = sfc.getSsoConfig();
       Document doc = XMLUtil.loadXmlString(xmlconfig);
       NodeList l = doc.getDocumentElement().getElementsByTagName("ssoParam");
       Properties ssoMapItem = null;
       if (l != null) {
         ssoMapItem = new Properties();
         for (int i = 0; i < l.getLength(); i++)
         {
           Element ep = (Element)l.item(i);
           NodeList epKeyList = ep.getElementsByTagName("key");
           Element epKey = (Element)epKeyList.item(0);
           String key = epKey.getTextContent().trim();
           
           NodeList epVList = ep.getElementsByTagName("value");
           Element epV = (Element)epVList.item(0);
           String v = epV.getTextContent().trim();
           ssoMapItem.put(key, v);
           log.info("key=" + key + ",v=" + v);
         }
       }
       if (ssoMapItem != null) {
         SsoPropsLoader.ccssoMap.put(sfc.getSsoType().trim(), ssoMapItem);
       }
     }
   }
   
 
 
   public void initccSsoMap()
     throws Exception
   {
     ShardedJedis jedis = null;
     try {
       jedis = (ShardedJedis)this.pool.getResource();
     } catch (JedisConnectionException e) {
       this.pool.returnBrokenResource(jedis);
     }
     
     List<SysSsoConfig> slst = getSsoConfigList();
     log.info("init ssoMap--------");
     try {
       for (SysSsoConfig sfc : slst) {
         String xmlconfig = sfc.getSsoConfig();
         Document doc = XMLUtil.loadXmlString(xmlconfig);
         NodeList l = doc.getDocumentElement().getElementsByTagName("ssoParam");
         Properties ssoMapItem = null;
         if (l != null) {
           ssoMapItem = new Properties();
           for (int i = 0; i < l.getLength(); i++)
           {
             Element ep = (Element)l.item(i);
             NodeList epKeyList = ep.getElementsByTagName("key");
             Element epKey = (Element)epKeyList.item(0);
             String key = epKey.getTextContent().trim();
             
             NodeList epVList = ep.getElementsByTagName("value");
             Element epV = (Element)epVList.item(0);
             String v = epV.getTextContent().trim();
             ssoMapItem.put(key, v);
             log.info("key=" + key + ",v=" + v);
           }
         }
         if (ssoMapItem != null)
         {
           if (jedis != null)
           {
             jedis.set((sfc.getSsoType().trim() + "_" + sfc.getSysapplicationId()).getBytes(), SerializeUtil.serialize(ssoMapItem));
           }
         }
       }
     }
     catch (Exception e)
     {
       this.pool.returnBrokenResource(jedis);
       e.printStackTrace();
     } finally {
       if (jedis != null) { this.pool.returnResource(jedis);
       }
     }
   }
   
 
 
 
 
   private List<SysFilterConfig> getFillerConfigList()
     throws Exception
   {
     String url = RestClientConfig.getRestHost("sysuser") + "/api/platform6/syssso/SysUser/getFillerConfigList/v1";
     ResponseMsg<List<SysFilterConfig>> responseMsg = RestClient.doGet(url, new GenericType() {});
     if (responseMsg.getRetCode().equals("200")) {
       return (List)responseMsg.getResponseBody();
     }
     log.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
     return null;
   }
   
 
 
 
 
 
   private List<SysSsoConfig> getSsoConfigList()
     throws Exception
   {
     String url = RestClientConfig.getRestHost("sysuser") + "/api/platform6/syssso/SysUser/getSsoConfigList/v1";
     ResponseMsg<List<SysSsoConfig>> responseMsg = RestClient.doGet(url, new GenericType() {});
     if (responseMsg.getRetCode().equals("200")) {
       return (List)responseMsg.getResponseBody();
     }
     log.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
     return null;
   }
   
 
 
   public void ssoProps2DB(Properties filterConfig, String ssoType, Properties ssoConfig)
     throws Exception
   {
     Muti3Bean<Properties, String, Properties> parameter = new Muti3Bean(filterConfig, ssoType, ssoConfig);
     String url = RestClientConfig.getRestHost("sysuser") + "/api/platform6/syssso/SysUser/ssoProps2DB/v1";
     ResponseMsg<List<SysSsoConfig>> responseMsg = RestClient.doPost(url, parameter, new GenericType() {});
     if (!responseMsg.getRetCode().equals("200"))
     {
       log.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
     }
   }
   
   public void ccssoProps2DB(Properties filterConfig, String ssoType, Properties ssoConfig) throws Exception
   {
     Muti3Bean<Properties, String, Properties> parameter = new Muti3Bean(filterConfig, ssoType, ssoConfig);
     String url = RestClientConfig.getRestHost("sysuser") + "/api/platform6/syssso/SysUser/ccssoProps2DB/v1";
     ResponseMsg<List<SysSsoConfig>> responseMsg = RestClient.doPost(url, parameter, new GenericType() {});
     if (!responseMsg.getRetCode().equals("200"))
     {
       log.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
     }
   }
 }


