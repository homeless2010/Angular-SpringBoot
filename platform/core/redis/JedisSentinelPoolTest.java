package com.piedpiper.platform.core.redis;

import com.piedpiper.platform.commons.utils.JsonHelper;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class JedisSentinelPoolTest {
	public void testX() throws Exception {
		GenericObjectPoolConfig config = new GenericObjectPoolConfig();

		List<String> masters = new ArrayList();

		Set<String> sentinels = new HashSet();
		sentinels.add("127.0.0.1:6379");

		JedisSentinelPool pool = new JedisSentinelPool(masters, sentinels, config, 60000);

		ShardedJedis jedis = null;
		for (int i = 0; i < 100; i++) {
			try {
				jedis = (ShardedJedis) pool.getResource();
				jedis.set("key" + i, "" + i);
				System.out.print(i);
				System.out.print(" ");
				Thread.sleep(500L);
				pool.returnResource(jedis);
			} catch (JedisConnectionException e) {
				System.out.println("可能failover,等待......");
				i--;
				Thread.sleep(1000L);
			}
		}

		System.out.println("");

		for (int i = 0; i < 100; i++) {
			try {
				jedis = (ShardedJedis) pool.getResource();
				System.out.print(jedis.get("key" + i));
				System.out.print(" ");
				Thread.sleep(500L);
				pool.returnResource(jedis);
			} catch (JedisConnectionException e) {
				System.out.println("可能failover,等待......");
				i--;
				Thread.sleep(1000L);
			}
		}

		pool.destroy();
	}

	public void testJson() throws Exception {
		GenericObjectPoolConfig config = new GenericObjectPoolConfig();

		List<String> masters = new ArrayList();

		Set<String> sentinels = new HashSet();
		sentinels.add("127.0.0.1:6379");

		JedisSentinelPool pool = new JedisSentinelPool(masters, sentinels, config, 60000);

		ShardedJedis jedis = null;
		for (int i = 0; i < 10; i++) {
			try {
				jedis = (ShardedJedis) pool.getResource();
				Student student = new Student();
				student.setAge(i);
				student.setName("学生" + i);

				jedis.set("student" + i, JsonHelper.getInstance().writeValueAsString(student));
				System.out.print(i);
				System.out.print(" ");
				Thread.sleep(500L);
				pool.returnResource(jedis);
			} catch (JedisConnectionException e) {
				System.out.println("可能failover,等待......");
				i--;
				Thread.sleep(1000L);
			}
		}

		System.out.println("");

		for (int i = 0; i < 10; i++) {
			try {
				jedis = (ShardedJedis) pool.getResource();
				System.out.print(jedis.get("student" + i));
				System.out.print(" ");
				Thread.sleep(500L);
				pool.returnResource(jedis);
			} catch (JedisConnectionException e) {
				System.out.println("可能failover,等待......");
				i--;
				Thread.sleep(1000L);
			}
		}

		pool.destroy();
	}

	class Student {
		private String name;
		private int age;

		Student() {
		}

		public String getName() {
			return this.name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return this.age;
		}

		public void setAge(int age) {
			this.age = age;
		}
	}

	public static void main(String[] args) {
		JedisSentinelPoolTest t = new JedisSentinelPoolTest();
		try {
			t.testJson();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
