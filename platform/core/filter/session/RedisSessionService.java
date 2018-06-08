package com.piedpiper.platform.core.filter.session;

import com.piedpiper.platform.commons.utils.SerializeUtil;
import com.piedpiper.platform.core.redis.JedisSentinelPool;
import com.piedpiper.platform.core.spring.SpringFactory;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class RedisSessionService {
	private static RedisSessionService instance = null;

	private static JedisSentinelPool pool = null;

	public static RedisSessionService getInstance() {
		if (instance == null) {
			synchronized (RedisSessionService.class) {
				instance = new RedisSessionService();
			}
		}
		return instance;
	}

	private RedisSessionService() {
		try {
			if (pool == null) {
				pool = (JedisSentinelPool) SpringFactory.getBean("jedisSentinelPool");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Map getSession(String key, int sessionTimeout) {
		ShardedJedis jedis = null;
		Map session = null;
		try {
			jedis = (ShardedJedis) pool.getResource();
		} catch (JedisConnectionException e) {
			pool.returnBrokenResource(jedis);
		}
		if (jedis != null) {
			try {
				if (jedis.get(key.getBytes()) == null) {
					session = new ConcurrentHashMap();
					jedis.set(key.getBytes(), SerializeUtil.serialize(session), "nx".getBytes(), "ex".getBytes(),
							sessionTimeout);
				} else {
					session = (Map) SerializeUtil.unserialize(jedis.get(key.getBytes()));
				}
			} catch (Exception e) {
				pool.returnBrokenResource(jedis);
				e.printStackTrace();
			} finally {
				if (jedis != null)
					pool.returnResource(jedis);
			}
		}
		return session;
	}

	public void saveSession(String key, Map session, int sessionTimeout) {
		ShardedJedis jedis = null;
		try {
			jedis = (ShardedJedis) pool.getResource();
		} catch (JedisConnectionException e) {
			pool.returnBrokenResource(jedis);
		}
		if (jedis != null) {
			try {
				jedis.set(key.getBytes(), SerializeUtil.serialize(session), "xx".getBytes(), "ex".getBytes(),
						sessionTimeout);
			} catch (Exception e) {
				pool.returnBrokenResource(jedis);
				e.printStackTrace();
			} finally {
				if (jedis != null)
					pool.returnResource(jedis);
			}
		}
	}

	public void removeSession(String key) {
		ShardedJedis jedis = null;
		try {
			jedis = (ShardedJedis) pool.getResource();
		} catch (JedisConnectionException e) {
			pool.returnBrokenResource(jedis);
		}
		if (jedis != null) {
			try {
				jedis.del(key.getBytes());
			} catch (Exception e) {
				pool.returnBrokenResource(jedis);
				e.printStackTrace();
			} finally {
				if (jedis != null) {
					pool.returnResource(jedis);
				}
			}
		}
	}

	public Map getSessionById(String sessionId) {
		Map session = null;
		ShardedJedis jedis = (ShardedJedis) pool.getResource();
		try {
			session = (Map) SerializeUtil.unserialize(jedis.get(sessionId.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null)
				pool.returnResource(jedis);
		}
		return session;
	}
}
