package com.piedpiper.platform.core.redisCacheManager;

import java.util.Map;

public abstract interface BaseCacheBean
{
  public abstract String returnId();
  
  public abstract String returnValidFlag();
  
  public abstract Map<String, ?> returnCacheKey();
  
  public abstract String prefix();
  
  public abstract String returnAppId();
}


