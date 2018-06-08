package com.piedpiper.platform.core.redisCacheManager;

import com.piedpiper.platform.core.redis.JedisSentinelPool;
import com.piedpiper.platform.core.spring.SpringFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.ShardedJedis;

public class CacheHelper {
	private static final Logger log = LoggerFactory.getLogger(CacheHelper.class);

	private static CacheHelper instance = null;

	private JedisSentinelPool pool = null;

	public static CacheHelper getInstance() {
		if (instance == null) {
			instance = new CacheHelper();
		}
		return instance;
	}

	private CacheHelper() {
		this.pool = ((JedisSentinelPool) SpringFactory.getBean("jedisSentinelPool"));
	}

	private ShardedJedis getShardedJedis() {
		ShardedJedis jedis = null;
		try {
			jedis = (ShardedJedis) this.pool.getResource();
		} catch (Exception e) {
			this.pool.returnBrokenResource(jedis);
		}
		if (jedis == null) {
			log.error("==========获取jedis为null");
		}
		return jedis;
	}

	public void set(String key, String value) {
		ShardedJedis jedis = getShardedJedis();
		try {
			jedis.set(key, value);
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			this.pool.returnResource(jedis);
		}
	}

	public String get(String key) {
		String result = null;
		ShardedJedis jedis = getShardedJedis();
		try {
			result = jedis.get(key);
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			this.pool.returnResource(jedis);
		}
		return result;
	}

	public boolean contains(String key) {
		boolean result = false;
		ShardedJedis jedis = getShardedJedis();
		try {
			result = jedis.exists(key).booleanValue();
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			this.pool.returnResource(jedis);
		}
		return result;
	}

	public void del(String key) {
		ShardedJedis jedis = getShardedJedis();
		try {
			jedis.del(key);
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			this.pool.returnResource(jedis);
		}
	}

	public void set(byte[] key, byte[] value, byte[] nxxx, byte[] expx, long time) {
		ShardedJedis jedis = getShardedJedis();
		try {
			jedis.set(key, value, nxxx, expx, time);
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			this.pool.returnResource(jedis);
		}
	}
}
