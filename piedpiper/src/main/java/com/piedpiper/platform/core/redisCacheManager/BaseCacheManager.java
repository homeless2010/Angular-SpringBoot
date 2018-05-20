 package com.piedpiper.platform.core.redisCacheManager;
 
 import com.piedpiper.platform.commons.utils.JsonHelper;
 import com.piedpiper.platform.core.dao.hibernate.CommonHibernateDao2;
 import com.piedpiper.platform.core.domain.BeanBase;
 import com.piedpiper.platform.core.redis.JedisSentinelPool;
 import com.fasterxml.jackson.core.type.TypeReference;
 import java.util.ArrayList;
 import java.util.Collection;
 import java.util.Collections;
 import java.util.Comparator;
 import java.util.HashSet;
 import java.util.Iterator;
 import java.util.List;
 import java.util.Map;
 import java.util.Map.Entry;
 import java.util.Set;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import redis.clients.jedis.Jedis;
 import redis.clients.jedis.ShardedJedis;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class BaseCacheManager
 {
   private static final String SYSTEM_CACHE_KEY = "_SYSTEM_CACHE_KEY_";
   private static final Logger log = LoggerFactory.getLogger(BaseCacheManager.class);
   
   private JedisSentinelPool jedisSentinelPool;
   private CommonHibernateDao2 commonHibernateDao;
   
   public JedisSentinelPool getJedisSentinelPool()
   {
     return this.jedisSentinelPool;
   }
   
   public void setJedisSentinelPool(JedisSentinelPool jedisSentinelPool) {
     this.jedisSentinelPool = jedisSentinelPool;
   }
   
   public CommonHibernateDao2 getCommonHibernateDao() {
     return this.commonHibernateDao;
   }
   
   public void setCommonHibernateDao(CommonHibernateDao2 commonHibernateDao) {
     this.commonHibernateDao = commonHibernateDao;
   }
   
 
 
 
 
 
 
   public void insertCache(BaseCacheBean info)
   {
     Set<BaseCacheValue> infoSet = getValueSet(info, true);
     ShardedJedis jedis = getShardedJedis();
     try {
       insertCacheBySet(infoSet, info.getClass().getName(), jedis);
     } catch (Exception e) {
       log.error(e.getMessage());
     } finally {
       this.jedisSentinelPool.returnResource(jedis);
     }
   }
   
 
 
 
 
 
   public void updateCache(BaseCacheBean info)
   {
     if ((info instanceof BeanBase)) {
       BeanBase bb = (BeanBase)info;
       if (bb.getVersion() != null) {
         bb.setVersion(Long.valueOf(bb.getVersion().longValue() + 1L));
       }
     }
     
     String id = BaseCacheUtil.getId(info);
     String prefix = BaseCacheUtil.getPrefix(info);
     if ((BaseCacheUtil.isNull(id)) || (BaseCacheUtil.isNull(prefix))) {
       return;
     }
     BaseCacheBean oldObject = (BaseCacheBean)getObjectFromCacheNoFilter(prefix, id, info.getClass());
     if (BaseCacheUtil.isNull(oldObject)) {
       return;
     }
     Set<BaseCacheValue> oldInfo = getValueSet(oldObject, false);
     Set<BaseCacheValue> newInfo = getValueSet(info, true);
     oldInfo.removeAll(newInfo);
     ShardedJedis jedis = getShardedJedis();
     try {
       insertCacheBySet(newInfo, info.getClass().getName(), jedis);
       deleteCacheBySet(oldInfo, jedis);
     } catch (Exception e) {
       log.error(e.getMessage());
     } finally {
       this.jedisSentinelPool.returnResource(jedis);
     }
   }
   
 
 
 
 
   public void deleteCache(BaseCacheBean info)
   {
     Set<BaseCacheValue> infoSet = getValueSet(info, false);
     ShardedJedis jedis = getShardedJedis();
     try {
       deleteCacheBySet(infoSet, jedis);
     } catch (Exception e) {
       log.error(e.getMessage());
     } finally {
       this.jedisSentinelPool.returnResource(jedis);
     }
   }
   
 
 
 
 
 
 
   public void deleteCacheById(String id, String prefix, Class<?> c)
   {
     if ((BaseCacheUtil.isNull(id)) || (BaseCacheUtil.isNull(prefix))) {
       return;
     }
     BaseCacheBean oldObject = (BaseCacheBean)getObjectFromCacheNoFilter(prefix, id, c);
     if (BaseCacheUtil.isNull(oldObject)) {
       return;
     }
     deleteCache(oldObject);
   }
   
 
 
 
 
 
   private void insertCacheBySet(Set<BaseCacheValue> infoSet, String className, ShardedJedis jedis)
     throws Exception
   {
     for (BaseCacheValue value : infoSet) {
       jedis.sadd("_SYSTEM_CACHE_KEY_" + className, new String[] { value.getKey() });
       jedis.hset(value.getKey(), value.getField(), value.getValue());
     }
   }
   
 
 
 
 
 
   private void deleteCacheBySet(Set<BaseCacheValue> infoSet, ShardedJedis jedis)
     throws Exception
   {
     for (BaseCacheValue value : infoSet) {
       jedis.hdel(value.getKey(), new String[] { value.getField() });
     }
   }
   
 
 
 
 
 
   private Set<BaseCacheValue> getValueSet(BaseCacheBean info, boolean needValue)
   {
     Set<BaseCacheValue> resultSet = new HashSet();
     Map<String, ?> keyMap = BaseCacheUtil.getKey(info);
     for (Map.Entry<String, ?> entry : keyMap.entrySet()) {
       if (!BaseCacheUtil.isNull(entry.getValue()))
       {
 
         if ((entry.getValue() instanceof String)) {
           String field = (String)entry.getValue();
           BaseCacheValue bean = new BaseCacheValue((String)entry.getKey(), field);
           if (needValue) {
             bean.setValue(JsonHelper.getBaseInstance().writeValueAsString(info));
           }
           resultSet.add(bean);
         } else if ((entry.getValue() instanceof BaseCacheConf)) {
           BaseCacheConf conf = (BaseCacheConf)entry.getValue();
           if (!BaseCacheUtil.isNull(conf.getIdString()))
           {
 
             BaseCacheValue bean = new BaseCacheValue((String)entry.getKey(), conf.getIdString());
             if (needValue) {
               bean.setValue(JsonHelper.getBaseInstance().writeValueAsString(conf));
             }
             resultSet.add(bean);
           } } else if ((entry.getValue() instanceof BaseCacheBlank)) {
           BaseCacheBlank blank = (BaseCacheBlank)entry.getValue();
           if (blank.getField() != null) {
             BaseCacheValue bean = new BaseCacheValue((String)entry.getKey(), blank.getField());
             if (needValue) {
               bean.setValue(JsonHelper.getBaseInstance().writeValueAsString(info.returnId()));
             }
             resultSet.add(bean);
           }
         } }
     }
     return resultSet;
   }
   
 
 
 
 
   private void insertCacheForLoaderOnThread(BaseCacheBean info, ShardedJedis jedis)
     throws Exception
   {
     Set<BaseCacheValue> infoSet = getValueSet(info, true);
     insertCacheBySet(infoSet, info.getClass().getName(), jedis);
   }
   
 
 
 
 
 
 
 
 
   public List<?> getAllFromCacheNoFilter(String prefix, Class<?> c)
   {
     List<?> resultList = getAllFromCacheBaseNoFilter(prefix, c);
     if ((resultList.size() > 0) && 
       ((resultList.get(0) instanceof Comparable))) {
       Collections.sort(resultList, new Comparator()
       {
         public int compare(Object o1, Object o2)
         {
           Comparable a1 = (Comparable)o1;
           return a1.compareTo(o2);
         }
       });
     }
     
 
     return resultList;
   }
   
 
 
 
 
 
 
   public List<?> getAllFromCache(String prefix, Class<?> c)
   {
     List<?> resultList = getAllFromCacheBase(prefix, c);
     if ((resultList.size() > 0) && 
       ((resultList.get(0) instanceof Comparable))) {
       Collections.sort(resultList, new Comparator()
       {
         public int compare(Object o1, Object o2)
         {
           Comparable a1 = (Comparable)o1;
           return a1.compareTo(o2);
         }
       });
     }
     
 
     return resultList;
   }
   
 
 
 
 
 
 
 
   public List<?> getAllFromCacheNoFilter(String prefix, Class<?> c, String appId)
   {
     if (BaseCacheUtil.isNull(appId)) {
       return null;
     }
     List<?> resultList = getAllFromCacheBaseNoFilter(prefix, c, appId);
     if ((resultList.size() > 0) && 
       ((resultList.get(0) instanceof Comparable))) {
       Collections.sort(resultList, new Comparator()
       {
         public int compare(Object o1, Object o2)
         {
           Comparable a1 = (Comparable)o1;
           return a1.compareTo(o2);
         }
       });
     }
     
 
     return resultList;
   }
   
 
 
 
 
 
 
 
   public List<?> getAllFromCache(String prefix, Class<?> c, String appId)
   {
     if (BaseCacheUtil.isNull(appId)) {
       return null;
     }
     List<?> resultList = getAllFromCacheBase(prefix, c, appId);
     if ((resultList.size() > 0) && 
       ((resultList.get(0) instanceof Comparable))) {
       Collections.sort(resultList, new Comparator()
       {
         public int compare(Object o1, Object o2)
         {
           Comparable a1 = (Comparable)o1;
           return a1.compareTo(o2);
         }
       });
     }
     
 
     return resultList;
   }
   
 
 
 
 
 
 
   public <T> List<T> getAllFromCacheNoFilter(String prefix, TypeReference<T> typeReference)
   {
     List<T> resultList = getAllFromCacheBaseNoFilter(prefix, typeReference);
     if ((resultList.size() > 0) && 
       ((resultList.get(0) instanceof Comparable))) {
       Collections.sort(resultList, new Comparator()
       {
         public int compare(Object o1, Object o2)
         {
           Comparable a1 = (Comparable)o1;
           return a1.compareTo(o2);
         }
       });
     }
     
 
     return resultList;
   }
   
 
 
 
 
 
 
   public <T> List<T> getAllFromCache(String prefix, TypeReference<T> typeReference)
   {
     List<T> resultList = getAllFromCacheBase(prefix, typeReference);
     if ((resultList.size() > 0) && 
       ((resultList.get(0) instanceof Comparable))) {
       Collections.sort(resultList, new Comparator()
       {
         public int compare(Object o1, Object o2)
         {
           Comparable a1 = (Comparable)o1;
           return a1.compareTo(o2);
         }
       });
     }
     
 
     return resultList;
   }
   
 
 
 
 
 
   public <T> List<T> getAllFromCacheByList(String prefix, TypeReference<T> typeReference)
   {
     List<T> resultList = getAllFromCacheBaseByList(prefix, typeReference);
     return resultList;
   }
   
 
 
 
 
 
 
 
   public <T> List<T> getAllFromCacheNoFilter(String prefix, TypeReference<T> typeReference, String appId)
   {
     if (BaseCacheUtil.isNull(appId)) {
       return null;
     }
     List<T> resultList = getAllFromCacheBaseNoFilter(prefix, typeReference, appId);
     if ((resultList.size() > 0) && 
       ((resultList.get(0) instanceof Comparable))) {
       Collections.sort(resultList, new Comparator()
       {
         public int compare(Object o1, Object o2)
         {
           Comparable a1 = (Comparable)o1;
           return a1.compareTo(o2);
         }
       });
     }
     
 
     return resultList;
   }
   
 
 
 
 
 
 
 
   public <T> List<T> getAllFromCache(String prefix, TypeReference<T> typeReference, String appId)
   {
     if (BaseCacheUtil.isNull(appId)) {
       return null;
     }
     List<T> resultList = getAllFromCacheBase(prefix, typeReference, appId);
     if ((resultList.size() > 0) && 
       ((resultList.get(0) instanceof Comparable))) {
       Collections.sort(resultList, new Comparator()
       {
         public int compare(Object o1, Object o2)
         {
           Comparable a1 = (Comparable)o1;
           return a1.compareTo(o2);
         }
       });
     }
     
 
     return resultList;
   }
   
 
 
 
 
 
 
 
   public List<?> getAllFromCacheByOrder(String prefix, Class<?> c, Comparator comparator)
   {
     List<?> resultList = getAllFromCacheBase(prefix, c);
     Collections.sort(resultList, comparator);
     return resultList;
   }
   
 
 
 
 
 
 
 
 
   public List<?> getAllFromCacheByOrder(String prefix, Class<?> c, Comparator comparator, String appId)
   {
     List<?> resultList = getAllFromCacheBase(prefix, c, appId);
     Collections.sort(resultList, comparator);
     return resultList;
   }
   
 
 
 
 
 
 
 
   public <T> List<T> getAllFromCacheByOrder(String prefix, TypeReference<T> typeReference, Comparator comparator)
   {
     List<T> resultList = getAllFromCacheBase(prefix, typeReference);
     Collections.sort(resultList, comparator);
     return resultList;
   }
   
 
 
 
 
 
 
 
 
   public <T> List<T> getAllFromCacheByOrder(String prefix, TypeReference<T> typeReference, Comparator comparator, String appId)
   {
     List<T> resultList = getAllFromCacheBase(prefix, typeReference, appId);
     Collections.sort(resultList, comparator);
     return resultList;
   }
   
 
 
 
 
 
 
 
 
   public Object getObjectFromCacheNoFilter(String prefix, String id, Class<?> c)
   {
     if (BaseCacheUtil.isNull(id)) {
       return null;
     }
     ShardedJedis jedis = getShardedJedis();
     String string = null;
     try {
       string = parseValue(prefix, jedis.hget(prefix, id), jedis);
     } catch (Exception e) {
       log.error(e.getMessage());
     } finally {
       this.jedisSentinelPool.returnResource(jedis);
     }
     if (string != null) {
       return parseObject(string, c, false);
     }
     return null;
   }
   
 
 
 
 
 
 
   public Object getObjectFromCache(String prefix, String id, Class<?> c)
   {
     if (BaseCacheUtil.isNull(id)) {
       return null;
     }
     ShardedJedis jedis = getShardedJedis();
     String string = null;
     try {
       string = parseValue(prefix, jedis.hget(prefix, id), jedis);
     } catch (Exception e) {
       log.error(e.getMessage());
     } finally {
       this.jedisSentinelPool.returnResource(jedis);
     }
     if (string != null) {
       return parseObject(string, c, true);
     }
     return null;
   }
   
 
 
 
 
 
 
 
   public Object getObjectFromCacheNoFilter(String prefix, String id, Class<?> c, String appId)
   {
     if (BaseCacheUtil.isNull(id)) {
       return null;
     }
     ShardedJedis jedis = getShardedJedis();
     String string = null;
     try {
       string = parseValue(prefix, jedis.hget(prefix, id), jedis);
     } catch (Exception e) {
       log.error(e.getMessage());
     } finally {
       this.jedisSentinelPool.returnResource(jedis);
     }
     if (string != null) {
       return parseObject(string, c, appId, false);
     }
     return null;
   }
   
 
 
 
 
 
 
 
   public Object getObjectFromCache(String prefix, String id, Class<?> c, String appId)
   {
     if (BaseCacheUtil.isNull(id)) {
       return null;
     }
     ShardedJedis jedis = getShardedJedis();
     String string = null;
     try {
       string = parseValue(prefix, jedis.hget(prefix, id), jedis);
     } catch (Exception e) {
       log.error(e.getMessage());
     } finally {
       this.jedisSentinelPool.returnResource(jedis);
     }
     if (string != null) {
       return parseObject(string, c, appId, true);
     }
     return null;
   }
   
 
 
 
 
 
 
   public <T> T getObjectFromCacheNoFilter(String prefix, String id, TypeReference<T> typeReference)
   {
     if (BaseCacheUtil.isNull(id)) {
       return null;
     }
     ShardedJedis jedis = getShardedJedis();
     String string = null;
     try {
       string = parseValue(prefix, jedis.hget(prefix, id), jedis);
     } catch (Exception e) {
       log.error(e.getMessage());
     } finally {
       this.jedisSentinelPool.returnResource(jedis);
     }
     if (string != null) {
       return (T)parseT(string, typeReference, false);
     }
     return null;
   }
   
 
 
 
 
 
 
   public <T> T getObjectFromCache(String prefix, String id, TypeReference<T> typeReference)
   {
     if (BaseCacheUtil.isNull(id)) {
       return null;
     }
     ShardedJedis jedis = getShardedJedis();
     String string = null;
     try {
       string = parseValue(prefix, jedis.hget(prefix, id), jedis);
     } catch (Exception e) {
       log.error(e.getMessage());
     } finally {
       this.jedisSentinelPool.returnResource(jedis);
     }
     if (string != null) {
       return (T)parseT(string, typeReference, true);
     }
     return null;
   }
   
 
 
 
 
 
 
 
   public <T> T getObjectFromCache(String prefix, String id, TypeReference<T> typeReference, String appId)
   {
     if (BaseCacheUtil.isNull(id)) {
       return null;
     }
     ShardedJedis jedis = getShardedJedis();
     String string = null;
     try {
       string = parseValue(prefix, jedis.hget(prefix, id), jedis);
     } catch (Exception e) {
       log.error(e.getMessage());
     } finally {
       this.jedisSentinelPool.returnResource(jedis);
     }
     if (string != null) {
       return (T)parseT(string, typeReference, appId, true);
     }
     return null;
   }
   
 
 
 
 
 
 
 
   private List<?> getAllFromCacheBaseNoFilter(String prefix, Class<?> c)
   {
     ShardedJedis jedis = getShardedJedis();
     List<Object> resultList = new ArrayList();
     try {
       Map<String, String> map = jedis.hgetAll(prefix);
       for (Map.Entry<String, String> entry : map.entrySet()) {
         String value = parseValue(prefix, (String)entry.getValue(), jedis);
         if (value != null)
         {
 
           Object object = parseObject(value, c, false);
           if (object != null)
             resultList.add(object);
         }
       }
     } catch (Exception e) {
       log.error(e.getMessage());
     } finally {
       this.jedisSentinelPool.returnResource(jedis);
     }
     return resultList;
   }
   
 
 
 
 
 
   private List<?> getAllFromCacheBase(String prefix, Class<?> c)
   {
     ShardedJedis jedis = getShardedJedis();
     List<Object> resultList = new ArrayList();
     try {
       Map<String, String> map = jedis.hgetAll(prefix);
       for (Map.Entry<String, String> entry : map.entrySet()) {
         String value = parseValue(prefix, (String)entry.getValue(), jedis);
         if (value != null)
         {
 
           Object object = parseObject(value, c, true);
           if (object != null)
             resultList.add(object);
         }
       }
     } catch (Exception e) {
       log.error(e.getMessage());
     } finally {
       this.jedisSentinelPool.returnResource(jedis);
     }
     return resultList;
   }
   
 
 
 
 
 
 
   private List<?> getAllFromCacheBaseNoFilter(String prefix, Class<?> c, String appId)
   {
     ShardedJedis jedis = getShardedJedis();
     List<Object> resultList = new ArrayList();
     try {
       Map<String, String> map = jedis.hgetAll(prefix);
       for (Map.Entry<String, String> entry : map.entrySet()) {
         String value = parseValue(prefix, (String)entry.getValue(), jedis);
         if (value != null)
         {
 
           Object object = parseObject(value, c, appId, false);
           if (object != null)
             resultList.add(object);
         }
       }
     } catch (Exception e) {
       log.error(e.getMessage());
     } finally {
       this.jedisSentinelPool.returnResource(jedis);
     }
     return resultList;
   }
   
 
 
 
 
 
 
   private List<?> getAllFromCacheBase(String prefix, Class<?> c, String appId)
   {
     ShardedJedis jedis = getShardedJedis();
     List<Object> resultList = new ArrayList();
     try {
       Map<String, String> map = jedis.hgetAll(prefix);
       for (Map.Entry<String, String> entry : map.entrySet()) {
         String value = parseValue(prefix, (String)entry.getValue(), jedis);
         if (value != null)
         {
 
           Object object = parseObject(value, c, appId, true);
           if (object != null)
             resultList.add(object);
         }
       }
     } catch (Exception e) {
       log.error(e.getMessage());
     } finally {
       this.jedisSentinelPool.returnResource(jedis);
     }
     return resultList;
   }
   
 
 
 
 
 
   private <T> List<T> getAllFromCacheBaseNoFilter(String prefix, TypeReference<T> typeReference)
   {
     ShardedJedis jedis = getShardedJedis();
     List<T> resultList = new ArrayList();
     try {
       Map<String, String> map = jedis.hgetAll(prefix);
       for (Map.Entry<String, String> entry : map.entrySet()) {
         String value = parseValue(prefix, (String)entry.getValue(), jedis);
         if (value != null)
         {
 
           T t = parseT(value, typeReference, false);
           if (t != null)
             resultList.add(t);
         }
       }
     } catch (Exception e) {
       log.error(e.getMessage());
     } finally {
       this.jedisSentinelPool.returnResource(jedis);
     }
     return resultList;
   }
   
 
 
 
 
 
   private <T> List<T> getAllFromCacheBase(String prefix, TypeReference<T> typeReference)
   {
     ShardedJedis jedis = getShardedJedis();
     List<T> resultList = new ArrayList();
     try {
       Map<String, String> map = jedis.hgetAll(prefix);
       for (Map.Entry<String, String> entry : map.entrySet()) {
         String value = parseValue(prefix, (String)entry.getValue(), jedis);
         if (value != null)
         {
 
           T t = parseT(value, typeReference, true);
           if (t != null)
             resultList.add(t);
         }
       }
     } catch (Exception e) {
       log.error(e.getMessage());
     } finally {
       this.jedisSentinelPool.returnResource(jedis);
     }
     return resultList;
   }
   
 
 
 
 
 
   private <T> List<T> getAllFromCacheBaseByList(String prefix, TypeReference<T> typeReference)
   {
     ShardedJedis jedis = getShardedJedis();
     List<T> resultList = new ArrayList();
     try {
       List<String> list = jedis.lrange(prefix, 0L, -1L);
       for (String value : list) {
         value = parseValue(prefix, value, jedis);
         if (value != null)
         {
 
           T t = parseT(value, typeReference, true);
           if (t != null)
             resultList.add(t);
         }
       }
     } catch (Exception e) {
       log.error(e.getMessage());
     } finally {
       this.jedisSentinelPool.returnResource(jedis);
     }
     return resultList;
   }
   
 
 
 
 
 
 
   private <T> List<T> getAllFromCacheBaseNoFilter(String prefix, TypeReference<T> typeReference, String appId)
   {
     ShardedJedis jedis = getShardedJedis();
     List<T> resultList = new ArrayList();
     try {
       Map<String, String> map = jedis.hgetAll(prefix);
       for (Map.Entry<String, String> entry : map.entrySet()) {
         String value = parseValue(prefix, (String)entry.getValue(), jedis);
         if (value != null)
         {
 
           T t = parseT(value, typeReference, appId, false);
           if (t != null)
             resultList.add(t);
         }
       }
     } catch (Exception e) {
       log.error(e.getMessage());
     } finally {
       this.jedisSentinelPool.returnResource(jedis);
     }
     
     return resultList;
   }
   
 
 
 
 
 
 
   private <T> List<T> getAllFromCacheBase(String prefix, TypeReference<T> typeReference, String appId)
   {
     ShardedJedis jedis = getShardedJedis();
     List<T> resultList = new ArrayList();
     try {
       Map<String, String> map = jedis.hgetAll(prefix);
       for (Map.Entry<String, String> entry : map.entrySet()) {
         String value = parseValue(prefix, (String)entry.getValue(), jedis);
         if (value != null)
         {
 
           T t = parseT(value, typeReference, appId, true);
           if (t != null)
             resultList.add(t);
         }
       }
     } catch (Exception e) {
       log.error(e.getMessage());
     } finally {
       this.jedisSentinelPool.returnResource(jedis);
     }
     
     return resultList;
   }
   
 
 
 
 
 
 
 
   private String parseValue(String prefix, String value, ShardedJedis jedis)
   {
     if ((value != null) && (value.indexOf("6fd2e0ba-e28a-49ad-88f1-6a70727e6dfb") != -1)) {
       BaseCacheConf conf = (BaseCacheConf)JsonHelper.getBaseInstance().readValue(value, new TypeReference() {});
       value = jedis.hget(conf.getPrefixString(), conf.getIdString());
     }
     return value;
   }
   
 
 
 
 
 
 
   private Object parseObject(String string, Class<?> c, boolean isValid)
   {
     Object object = JsonHelper.getBaseInstance().readValue(string, c);
     if ((object instanceof BaseCacheBean)) {
       BaseCacheBean baseBean = (BaseCacheBean)object;
       if (isValid) {
         if (BaseCacheUtil.isValid(baseBean)) {
           return object;
         }
       } else {
         return object;
       }
     } else {
       return object;
     }
     return null;
   }
   
 
 
 
 
 
 
   private Object parseObject(String string, Class<?> c, String appId, boolean isValid)
   {
     Object object = JsonHelper.getBaseInstance().readValue(string, c);
     if ((object instanceof BaseCacheBean)) {
       BaseCacheBean baseBean = (BaseCacheBean)object;
       if (BaseCacheUtil.isAppId(baseBean, appId)) {
         if (isValid) {
           if (BaseCacheUtil.isValid(baseBean)) {
             return object;
           }
         } else {
           return object;
         }
       }
     }
     return null;
   }
   
 
 
 
 
 
   private <T> T parseT(String string, TypeReference<T> typeReference, boolean isValid)
   {
     T t = JsonHelper.getBaseInstance().readValue(string, typeReference);
     if ((t instanceof BaseCacheBean)) {
       BaseCacheBean baseBean = (BaseCacheBean)t;
       if (isValid) {
         if (BaseCacheUtil.isValid(baseBean)) {
           return t;
         }
       } else {
         return t;
       }
     } else {
       return t;
     }
     return null;
   }
   
 
 
 
 
 
 
   private <T> T parseT(String string, TypeReference<T> typeReference, String appId, boolean isValid)
   {
     T t = JsonHelper.getBaseInstance().readValue(string, typeReference);
     if ((t instanceof BaseCacheBean)) {
       BaseCacheBean baseBean = (BaseCacheBean)t;
       if (BaseCacheUtil.isAppId(baseBean, appId)) {
         if (isValid) {
           if (BaseCacheUtil.isValid(baseBean)) {
             return t;
           }
         } else {
           return t;
         }
       }
     }
     return null;
   }
   
 
 
 
 
 
   public boolean exists(String key)
   {
     ShardedJedis jedis = getShardedJedis();
     boolean b = jedis.exists(key).booleanValue();
     this.jedisSentinelPool.returnResource(jedis);
     return b;
   }
   
 
 
 
 
 
   public boolean containsKey(String key, String field)
   {
     ShardedJedis jedis = getShardedJedis();
     boolean b = jedis.hexists(key, field).booleanValue();
     this.jedisSentinelPool.returnResource(jedis);
     return b;
   }
   
 
 
 
 
   public boolean delByPattern(String pattern)
   {
     ShardedJedis jedis = getShardedJedis();
     Set<String> keys = ((Jedis)jedis.getAllShards().iterator().next()).keys("*" + pattern + "*");
     for (String defaultKey : keys) {
       jedis.del(defaultKey);
     }
     this.jedisSentinelPool.returnResource(jedis);
     return true;
   }
   
 
 
 
 
   public boolean delByKey(String key)
   {
     ShardedJedis jedis = getShardedJedis();
     jedis.del(key);
     this.jedisSentinelPool.returnResource(jedis);
     return true;
   }
   
 
 
 
 
   public int countSizeByKey(String key)
   {
     ShardedJedis jedis = getShardedJedis();
     Map<String, String> map = jedis.hgetAll(key);
     this.jedisSentinelPool.returnResource(jedis);
     return map.keySet().size();
   }
   
 
 
 
   public void loaderBeanList(Class<?> c)
   {
     log.info("开始加载目标【" + c.getName() + "】......");
     ShardedJedis jedis = getShardedJedis();
     try
     {
       Set<String> keyList = jedis.smembers("_SYSTEM_CACHE_KEY_" + c.getName());
       log.debug("目标【" + c.getName() + "】原缓存key长度=" + keyList.size());
       for (String keyString : keyList) {
         jedis.del(keyString);
       }
       jedis.del("_SYSTEM_CACHE_KEY_" + c.getName());
       
       List<?> list = this.commonHibernateDao.loadAll(c);
       log.info("目标【" + c.getName() + "】总长度=" + list.size());
       for (Object object : list) {
         insertCacheForLoaderOnThread((BaseCacheBean)object, jedis);
       }
     } catch (Exception e) {
       log.error(e.getMessage());
     } finally {
       this.jedisSentinelPool.returnResource(jedis);
     }
     log.info("目标【" + c.getName() + "】加载完成。");
   }
   
 
 
 
   public void loaderBeanList(Class<?> c, Collection<?> dataList)
   {
     log.info("开始加载目标【" + c.getName() + "】......");
     ShardedJedis jedis = getShardedJedis();
     try
     {
       Set<String> keyList = jedis.smembers("_SYSTEM_CACHE_KEY_" + c.getName());
       log.debug("目标【" + c.getName() + "】原缓存key长度=" + keyList.size());
       for (String keyString : keyList) {
         jedis.del(keyString);
       }
       jedis.del("_SYSTEM_CACHE_KEY_" + c.getName());
       
       log.info("目标【" + c.getName() + "】总长度=" + dataList.size());
       for (Object object : dataList) {
         insertCacheForLoaderOnThread((BaseCacheBean)object, jedis);
       }
     } catch (Exception e) {
       log.error(e.getMessage());
     } finally {
       this.jedisSentinelPool.returnResource(jedis);
     }
     log.info("目标【" + c.getName() + "】加载完成。");
   }
   
 
 
 
 
 
 
   private ShardedJedis getShardedJedis()
   {
     ShardedJedis jedis = null;
     try {
       jedis = (ShardedJedis)this.jedisSentinelPool.getResource();
     } catch (Exception e) {
       this.jedisSentinelPool.returnBrokenResource(jedis);
     }
     if (jedis == null) {
       log.error("==========获取jedis为null");
     }
     return jedis;
   }
   
 
 
 
 
 
 
   public boolean delByKey(byte[] key)
   {
     ShardedJedis jedis = getShardedJedis();
     jedis.del(key);
     this.jedisSentinelPool.returnResource(jedis);
     return true;
   }
   
 
 
 
 
   public boolean delByField(String key, String field)
   {
     ShardedJedis jedis = getShardedJedis();
     jedis.hdel(key, new String[] { field });
     this.jedisSentinelPool.returnResource(jedis);
     return true;
   }
   
 
 
 
 
   public Set<String> getFields(String key)
   {
     ShardedJedis jedis = getShardedJedis();
     Set<String> result = jedis.hkeys(key);
     this.jedisSentinelPool.returnResource(jedis);
     return result == null ? new HashSet() : result;
   }
   
 
 
 
 
 
   public String getFromRedis(String key, String field)
   {
     String result = null;
     ShardedJedis jedis = getShardedJedis();
     try {
       result = jedis.hget(key, field);
     } catch (Exception e) {
       log.error(e.getMessage());
     } finally {
       this.jedisSentinelPool.returnResource(jedis);
     }
     return result;
   }
   
 
 
 
 
 
   public byte[] getFromRedis(byte[] key, byte[] field)
   {
     byte[] result = null;
     ShardedJedis jedis = getShardedJedis();
     try {
       result = jedis.hget(key, field);
     } catch (Exception e) {
       log.error(e.getMessage());
     } finally {
       this.jedisSentinelPool.returnResource(jedis);
     }
     return result;
   }
   
 
 
 
 
 
   public void insert2redis(String key, String field, String value)
   {
     ShardedJedis jedis = getShardedJedis();
     try {
       jedis.hset(key, field, value);
     } catch (Exception e) {
       log.error(e.getMessage());
     } finally {
       this.jedisSentinelPool.returnResource(jedis);
     }
   }
   
 
 
 
 
 
   public void insert2redis(byte[] key, byte[] field, byte[] value)
   {
     ShardedJedis jedis = getShardedJedis();
     try {
       jedis.hset(key, field, value);
     } catch (Exception e) {
       log.error(e.getMessage());
     } finally {
       this.jedisSentinelPool.returnResource(jedis);
     }
   }
 }


