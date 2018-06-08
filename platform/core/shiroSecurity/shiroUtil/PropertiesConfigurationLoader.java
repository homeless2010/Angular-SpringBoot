 package com.piedpiper.platform.core.shiroSecurity.shiroUtil;
 
 import java.io.IOException;
 import java.io.InputStream;
 import java.util.Properties;
 import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
 import org.springframework.core.io.Resource;
 import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
 import org.springframework.core.io.support.ResourcePatternResolver;
 import org.springframework.util.CollectionUtils;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class PropertiesConfigurationLoader
   extends PropertyPlaceholderConfigurer
 {
   protected void loadProperties(Properties properties)
     throws IOException
   {
     this.properties = properties;
     ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
     Resource[] resources = resourceResolver.getResources("classpath*:default.avicit.security.properties");
     
     for (Resource res : resources) {
       Properties prop = new Properties();
       InputStream inputStream = res.getInputStream();
       prop.load(inputStream);
       CollectionUtils.mergePropertiesIntoMap(prop, properties);
       inputStream.close();
     }
     
     resources = resourceResolver.getResources("classpath*:*.properties");
     
     for (Resource res : resources) {
       Properties prop = new Properties();
       InputStream inputStream = res.getInputStream();
       prop.load(inputStream);
       CollectionUtils.mergePropertiesIntoMap(prop, properties);
       inputStream.close();
     }
   }
   
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
   private Properties properties = null;
   
 
 
 
 
   public Properties getProperties()
   {
     return this.properties;
   }
 }


