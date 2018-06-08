 package com.piedpiper.platform.core.mybatis.shard.builder;
 
 import com.piedpiper.platform.core.mybatis.shard.strategy.ShardStrategy;
 import java.util.HashMap;
 import java.util.HashSet;
 import java.util.Map;
 import java.util.Set;
 
 
 public class ShardConfigHolder
 {
   private static final ShardConfigHolder instance = new ShardConfigHolder();
   
   public static ShardConfigHolder getInstance() {
     return instance;
   }
   
   private Map<String, ShardStrategy> strategyRegister = new HashMap();
   
   private Set<String> ignoreSet;
   
   private Set<String> parseSet;
   
 
   public void register(String table, ShardStrategy strategy)
   {
     this.strategyRegister.put(table.toLowerCase(), strategy);
   }
   
   public ShardStrategy getStrategy(String table)
   {
     return (ShardStrategy)this.strategyRegister.get(table.toLowerCase());
   }
   
   public synchronized void addIgnoreId(String id)
   {
     if (this.ignoreSet == null) {
       this.ignoreSet = new HashSet();
     }
     this.ignoreSet.add(id);
   }
   
   public synchronized void addParseId(String id)
   {
     if (this.parseSet == null) {
       this.parseSet = new HashSet();
     }
     this.parseSet.add(id);
   }
   
 
 
 
   public boolean isConfigParseId()
   {
     return this.parseSet == null;
   }
   
   public boolean isParseId(String id)
   {
     return (this.parseSet != null) && (this.parseSet.contains(id));
   }
   
   public boolean isIgnoreId(String id)
   {
     return (this.ignoreSet != null) && (this.ignoreSet.contains(id));
   }
 }


