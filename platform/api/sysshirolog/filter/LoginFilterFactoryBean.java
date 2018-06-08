 package com.piedpiper.platform.api.sysshirolog.filter;
 
 import java.util.Map;
 import java.util.concurrent.ConcurrentHashMap;
 import org.apache.shiro.config.Ini;
 import org.apache.shiro.config.Ini.Section;
 import org.apache.shiro.util.CollectionUtils;
 
 public class LoginFilterFactoryBean
 {
   private Map<String, String> filterChainDefinitionsMap;
   
   public Map<String, String> getFilterChainDefinitions()
   {
     return this.filterChainDefinitionsMap;
   }
   
   public void setFilterChainDefinitions(String definitions)
   {
     Ini ini = new Ini();
     ini.load(definitions);
     Ini.Section section = ini.getSection("urls");
     if (CollectionUtils.isEmpty(section)) {
       section = ini.getSection("");
     }
     this.filterChainDefinitionsMap = new ConcurrentHashMap(section);
   }
 }


