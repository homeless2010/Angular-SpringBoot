 package com.piedpiper.platform.core.shiroSecurity.shiroCache;
 
 import com.piedpiper.platform.core.redis.JedisSentinelPool;
 import com.piedpiper.platform.core.spring.SpringFactory;
 import org.apache.shiro.ShiroException;
 import org.apache.shiro.cache.Cache;
 import org.apache.shiro.cache.CacheException;
 import org.apache.shiro.cache.CacheManager;
 import org.apache.shiro.util.Destroyable;
 import org.apache.shiro.util.Initializable;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.beans.factory.annotation.Autowired;
 import redis.clients.jedis.ShardedJedis;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class ShiroCacheManager
   implements CacheManager, Initializable, Destroyable
 {
   private static final Logger log = LoggerFactory.getLogger(ShiroCacheManager.class);
   
   @Autowired
   private JedisSentinelPool jedisSentinelPool;
   
   public static ShiroCacheManager instance;
   
   private String cacheName;
   
   public void setCacheName(String cacheName)
   {
     this.cacheName = cacheName;
   }
   
   private boolean cacheManagerImplicitlyCreated = false;
   
   public ShiroCacheManager() {
     instance = this;
   }
   
 
   public void destroy()
     throws Exception
   {}
   
 
   public void init()
     throws ShiroException
   {}
   
   public final <K, V> Cache<K, V> getCache(String name)
     throws CacheException
   {
     if (log.isTraceEnabled()) {
       log.trace("Acquiring EhCache instance named [" + name + "]");
     }
     ShardedJedis jedis = null;
     try {
       if (this.jedisSentinelPool == null) {
         if (log.isDebugEnabled()) {
           log.debug("Using default jedisSentinelPool CacheManager for cache region '" + this.cacheName + "'");
         }
         
         this.jedisSentinelPool = ((JedisSentinelPool)SpringFactory.getBean("jedisSentinelPool"));
       }
       if (this.cacheName == null)
         this.cacheName = "";
       jedis = (ShardedJedis)this.jedisSentinelPool.getResource();
       if (jedis == null) {
         if (log.isInfoEnabled()) {
           log.info("Cache with name '{}' does not yet exist. Creating now.");
         }
         if (log.isInfoEnabled()) {
           log.info("Added EhCache named [" + name + "]");
         }
       }
       else if (log.isInfoEnabled()) {
         log.info("Using existing jedisSentinelPool named [" + this.jedisSentinelPool.getCurrentHostMaster() + "]");
       }
       
 
 
       return new ShiroCache(this.jedisSentinelPool);
     } catch (Exception e) {
       log.error(e.getMessage(), e);
       throw new CacheException("启动失败---->缓存服务器没启动！");
     } finally {
       this.jedisSentinelPool.returnResource(jedis);
     }
   }
 }


