 package com.piedpiper.platform.core.shiroSecurity.shiroCache;
 
 import com.piedpiper.platform.commons.utils.JsonHelper;
 import com.piedpiper.platform.core.redis.JedisSentinelPool;
 import com.piedpiper.platform.core.rest.client.RestClientConfig;
 import java.util.Collection;
 import java.util.Set;
 import org.apache.shiro.authz.SimpleAuthorizationInfo;
 import org.apache.shiro.cache.Cache;
 import org.apache.shiro.cache.CacheException;
 import redis.clients.jedis.ShardedJedis;
 import redis.clients.jedis.exceptions.JedisConnectionException;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class ShiroCache<K, V>
   implements Cache
 {
   private JedisSentinelPool jedisSentinelPool;
   
   public ShiroCache(JedisSentinelPool jedisSentinelPool)
   {
     this.jedisSentinelPool = jedisSentinelPool;
   }
   
   public void clear()
     throws CacheException
   {}
   
   public Object get(Object key)
     throws CacheException
   {
     String value = null;
     ShardedJedis cache = null;
     try {
       cache = (ShardedJedis)this.jedisSentinelPool.getResource();
     } catch (JedisConnectionException e) {
       this.jedisSentinelPool.returnBrokenResource(cache);
     }
     if (cache != null) {
       try {
         value = cache.get(key.toString() + "_" + RestClientConfig.systemid);
       } catch (Exception e) {
         this.jedisSentinelPool.returnBrokenResource(cache);
       } finally {
         this.jedisSentinelPool.returnResource(cache);
       }
     }
     if (value == null) return null;
     return JsonHelper.getInstance().readValue(value, SimpleAuthorizationInfo.class);
   }
   
   public Set keys()
   {
     return null;
   }
   
   public Object put(Object key, Object value) throws CacheException
   {
     ShardedJedis cache = null;
     try {
       cache = (ShardedJedis)this.jedisSentinelPool.getResource();
     } catch (JedisConnectionException e) {
       this.jedisSentinelPool.returnBrokenResource(cache);
     }
     if (cache != null) {
       try {
         cache.set(key.toString() + "_" + RestClientConfig.systemid, JsonHelper.getInstance().writeValueAsString(value));
       } catch (Exception e) {
         this.jedisSentinelPool.returnBrokenResource(cache);
       } finally {
         this.jedisSentinelPool.returnResource(cache);
       }
     }
     return value;
   }
   
   public Object remove(Object key) throws CacheException
   {
     boolean flag = true;
     ShardedJedis cache = null;
     try {
       cache = (ShardedJedis)this.jedisSentinelPool.getResource();
     } catch (JedisConnectionException e) {
       this.jedisSentinelPool.returnBrokenResource(cache);
     }
     if (cache != null) {
       try {
         cache.del(key.toString() + "_" + RestClientConfig.systemid);
       } catch (Exception e) {
         this.jedisSentinelPool.returnBrokenResource(cache);
         flag = false;
         e.printStackTrace();
       } finally {
         this.jedisSentinelPool.returnResource(cache);
       }
     }
     return null;
   }
   
   public int size()
   {
     return 0;
   }
   
   public Collection values()
   {
     return null;
   }
 }


