 package com.piedpiper.platform.core.shiroSecurity.shiroUtil;
 
 import java.util.Collections;
 import java.util.Enumeration;
 import java.util.LinkedHashSet;
 import java.util.Properties;
 import java.util.Set;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class ShiroOrderedProperties
   extends Properties
 {
   private static final long serialVersionUID = -4827607240846121968L;
   private final LinkedHashSet<Object> keys = new LinkedHashSet();
   
   public Enumeration<Object> keys() {
     return Collections.enumeration(this.keys);
   }
   
   public Object put(Object key, Object value) {
     this.keys.add(key);
     return super.put(key, value);
   }
   
   public Set<Object> keySet() {
     return this.keys;
   }
   
   public Set<String> stringPropertyNames() {
     Set<String> set = new LinkedHashSet();
     
     for (Object key : this.keys) {
       set.add((String)key);
     }
     
     return set;
   }
 }


