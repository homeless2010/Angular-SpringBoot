 package com.piedpiper.platform.core.mybatis.shard.strategy.impl;
 
 import com.piedpiper.platform.core.mybatis.shard.strategy.ShardStrategy;
 
 public class BpmVariableShardStrategy
   implements ShardStrategy
 {
   public String getTargetTableName(String baseTableName, Object params, String mapperId)
   {
     int idx = ((Integer)params).intValue();
     String postfix = "_EXT" + idx;
     return baseTableName + postfix;
   }
 }


