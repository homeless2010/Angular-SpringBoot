package com.piedpiper.platform.core.mybatis.shard.strategy;

public abstract interface ShardStrategy
{
  public abstract String getTargetTableName(String paramString1, Object paramObject, String paramString2);
}


