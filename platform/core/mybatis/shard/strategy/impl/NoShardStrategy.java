 package com.piedpiper.platform.core.mybatis.shard.strategy.impl;
 
 import com.piedpiper.platform.core.mybatis.shard.strategy.ShardStrategy;
 
 
 
 
 public class NoShardStrategy
   implements ShardStrategy
 {
   public String getTargetTableName(String baseTableName, Object params, String mapperId)
   {
     return baseTableName;
   }
 }


