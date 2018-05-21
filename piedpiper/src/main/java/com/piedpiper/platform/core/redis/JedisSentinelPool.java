package com.piedpiper.platform.core.redis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.util.Hashing;
import redis.clients.util.Pool;

public class JedisSentinelPool extends Pool<ShardedJedis> {
	public static final int MAX_RETRY_SENTINEL = 10;
	protected final Logger log;
	protected GenericObjectPoolConfig poolConfig;
	protected int timeout;
	private int sentinelRetry;
	protected String password;
	protected int database;
	protected Set<MasterListener> masterListeners;
	private volatile List<HostAndPort> currentHostMasters;

	public JedisSentinelPool(List<String> masters, Set<String> sentinels) {
		this(masters, sentinels, new GenericObjectPoolConfig(), 2000, null, 0);
	}

	public JedisSentinelPool(List<String> masters, Set<String> sentinels, String password) {
		this(masters, sentinels, new GenericObjectPoolConfig(), 2000, password);
	}

	public JedisSentinelPool(GenericObjectPoolConfig poolConfig, List<String> masters, Set<String> sentinels) {
		this(masters, sentinels, poolConfig, 2000, null, 0);
	}

	public JedisSentinelPool(List<String> masters, Set<String> sentinels, GenericObjectPoolConfig poolConfig,
			int timeout, String password) {
		this(masters, sentinels, poolConfig, timeout, password, 0);
	}

	public JedisSentinelPool(List<String> masters, Set<String> sentinels, GenericObjectPoolConfig poolConfig,
			int timeout) {
		this(masters, sentinels, poolConfig, timeout, null, 0);
	}

	public JedisSentinelPool(List<String> masters, Set<String> sentinels, GenericObjectPoolConfig poolConfig,
			String password) {
		this(masters, sentinels, poolConfig, 2000, password);
	}

	public JedisSentinelPool(List<String> masters, Set<String> sentinels, GenericObjectPoolConfig poolConfig,
			int timeout, String password, int database) {
		this.log = LoggerFactory.getLogger(super.getClass().getName());

		this.timeout = 2000;

		this.sentinelRetry = 0;

		this.database = 0;

		this.masterListeners = new HashSet();

		this.poolConfig = poolConfig;
		this.timeout = timeout;
		this.password = password;
		this.database = database;
		if ((masters != null) && (masters.size() > 0)) {
			List masterList = initSentinels(sentinels, masters);
			initPool(masterList);
		} else {
			List redisServer = new ArrayList();
			for (String sentinel : sentinels) {
				HostAndPort hap = toHostAndPort(Arrays.asList(sentinel.split(":")));
				redisServer.add(hap);
			}
			initPool(redisServer);
		}
	}

	public void destroy() {
		for (MasterListener m : this.masterListeners) {
			if (m != null) {
				m.shutdown();
			}
		}

		super.destroy();
	}

	public List<HostAndPort> getCurrentHostMaster() {
		return this.currentHostMasters;
	}

	private void initPool(List<HostAndPort> masters) {
		if (!(equals(this.currentHostMasters, masters))) {
			StringBuffer sb = new StringBuffer();
			for (HostAndPort master : masters) {
				sb.append(master.toString());
				sb.append(" ");
			}
			this.log.info("Created ShardedJedisPool to master at [" + sb.toString() + "]");
			List shardMasters = makeShardInfoList(masters);
			initPool(this.poolConfig, new ShardedJedisFactory(shardMasters, Hashing.MURMUR_HASH, null));
			this.currentHostMasters = masters;
		}
	}

	private boolean equals(List<HostAndPort> currentShardMasters, List<HostAndPort> shardMasters) {
		if ((currentShardMasters != null) && (shardMasters != null)
				&& (currentShardMasters.size() == shardMasters.size())) {
			for (int i = 0; i < currentShardMasters.size(); ++i) {
				if (!(((HostAndPort) currentShardMasters.get(i)).equals(shardMasters.get(i))))
					return false;
			}
			return true;
		}

		return false;
	}

	private List<JedisShardInfo> makeShardInfoList(List<HostAndPort> masters) {
		List shardMasters = new ArrayList();
		for (HostAndPort master : masters) {
			JedisShardInfo jedisShardInfo = new JedisShardInfo(master.getHost(), master.getPort(), this.timeout);
			jedisShardInfo.setPassword(this.password);

			shardMasters.add(jedisShardInfo);
		}
		return shardMasters;
	}

	private List<HostAndPort> initSentinels(Set<String> sentinels, List<String> masters)
  {
    Map masterMap = new HashMap();
    List shardMasters = new ArrayList();

    this.log.info("Trying to find all master from available Sentinels...");

    for (String masterName : masters) {
      HostAndPort master = null;
      boolean fetched = false;

      while ((!(fetched)) && (this.sentinelRetry < 10)) {
        for (String sentinel : sentinels) {
          HostAndPort hap = toHostAndPort(Arrays.asList(sentinel.split(":")));

          this.log.info("Connecting to Sentinel " + hap);
          Jedis jedis = null;
          try {
            jedis = new Jedis(hap.getHost(), hap.getPort());
            master = (HostAndPort)masterMap.get(masterName);
            if (master == null) {
              List hostAndPort = jedis.sentinelGetMasterAddrByName(masterName);
              if ((hostAndPort != null) && (hostAndPort.size() > 0)) {
                master = toHostAndPort(hostAndPort);
                this.log.info("Found Redis master at " + master);
                shardMasters.add(master);
                masterMap.put(masterName, master);
                fetched = true;
                jedis.disconnect();

                if (jedis == null) break label376;
                jedis.close(); break label376;
              }
            }
          }
          catch (JedisConnectionException e)
          {
            this.log.warn("Cannot connect to sentinel running @ " + hap + ". Trying next one.");
          } finally {
            if (jedis != null) {
              jedis.close();
            }
          }

        }

        if (null != master) continue;
        try {
          this.log.error("All sentinels down, cannot determine where is " + masterName + " master is running... sleeping 1000ms, Will try again.");

          Thread.sleep(1000L);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        fetched = false;
        this.sentinelRetry += 1;
      }

      if ((!(fetched)) && (this.sentinelRetry >= 10)) {
        this.log.error("All sentinels down and try 10 times, Abort.");
        throw new JedisConnectionException("Cannot connect all sentinels, Abort.");
      }

    }

    if ((masters.size() != 0) && (masters.size() == shardMasters.size()))
    {
      label376: this.log.info("Starting Sentinel listeners...");
      for (String sentinel : sentinels) {
        HostAndPort hap = toHostAndPort(Arrays.asList(sentinel.split(":")));
        MasterListener masterListener = new MasterListener(masters, hap.getHost(), hap.getPort());
        this.masterListeners.add(masterListener);
        masterListener.start();
      }
    }

    return shardMasters;
  }

	private HostAndPort toHostAndPort(List<String> getMasterAddrByNameResult) {
		String host = (String) getMasterAddrByNameResult.get(0);
		int port = Integer.parseInt((String) getMasterAddrByNameResult.get(1));

		return new HostAndPort(host, port);
	}

	protected class MasterListener extends Thread {
		protected List<String> masters;
		protected String host;
		protected int port;
		protected long subscribeRetryWaitTimeMillis;
		protected Jedis jedis;
		protected AtomicBoolean running;

		protected MasterListener() {
			this.subscribeRetryWaitTimeMillis = 5000L;

			this.running = new AtomicBoolean(false);
		}

		public MasterListener(String masters, int host) {
			this.subscribeRetryWaitTimeMillis = 5000L;

			this.running = new AtomicBoolean(false);

			this.masters = masters;
			this.host = host;
			this.port = port;
		}

	public MasterListener(String masters, int host, long port)
    {
      this(???, masters, host, port);
      this.subscribeRetryWaitTimeMillis = subscribeRetryWaitTimeMillis;
    }

		public void run() {
			this.running.set(true);

			while (this.running.get()) {
				this.jedis = new Jedis(this.host, this.port);
				try {
					this.jedis.subscribe(new JedisSentinelPool.JedisPubSubAdapter() {
						public void onMessage(String channel, String message) {
							JedisSentinelPool.MasterListener.this.this$0.log.info("Sentinel "
									+ JedisSentinelPool.MasterListener.this.host + ":"
									+ JedisSentinelPool.MasterListener.this.port + " published: " + message + ".");

							String[] switchMasterMsg = message.split(" ");

							if (switchMasterMsg.length > 3) {
								int index = JedisSentinelPool.MasterListener.this.masters.indexOf(switchMasterMsg[0]);
								if (index >= 0) {
									HostAndPort newHostMaster = JedisSentinelPool.MasterListener.this.this$0
											.toHostAndPort(Arrays
													.asList(new String[] { switchMasterMsg[3], switchMasterMsg[4] }));
									List newHostMasters = new ArrayList();
									for (int i = 0; i < JedisSentinelPool.MasterListener.this.masters.size(); ++i) {
										newHostMasters.add(null);
									}
									Collections.copy(newHostMasters,
											JedisSentinelPool.MasterListener.this.this$0.currentHostMasters);
									newHostMasters.set(index, newHostMaster);

									JedisSentinelPool.MasterListener.this.this$0.initPool(newHostMasters);
								} else {
									StringBuffer sb = new StringBuffer();
									for (String masterName : JedisSentinelPool.MasterListener.this.masters) {
										sb.append(masterName);
										sb.append(",");
									}
									JedisSentinelPool.MasterListener.this.this$0.log.info(
											"Ignoring message on +switch-master for master name " + switchMasterMsg[0]
													+ ", our monitor master name are [" + sb + "]");
								}

							} else {
								JedisSentinelPool.MasterListener.this.this$0.log
										.error("Invalid message received on Sentinel "
												+ JedisSentinelPool.MasterListener.this.host + ":"
												+ JedisSentinelPool.MasterListener.this.port
												+ " on channel +switch-master: " + message);
							}
						}
					}, new String[] { "+switch-master" });
				} catch (JedisConnectionException e) {
					if (this.running.get()) {
						this.this$0.log.error("Lost connection to Sentinel at " + this.host + ":" + this.port
								+ ". Sleeping 5000ms and retrying.");
						try {
							Thread.sleep(this.subscribeRetryWaitTimeMillis);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
					} else {
						this.this$0.log.info("Unsubscribing from Sentinel at " + this.host + ":" + this.port);
					}
				}
			}
		}

		public void shutdown() {
			try {
				this.this$0.log.info("Shutting down listener on " + this.host + ":" + this.port);
				this.running.set(false);

				this.jedis.disconnect();
			} catch (Exception e) {
				this.this$0.log.error("Caught exception while shutting down: " + e.getMessage());
			}
		}
	}

	protected class JedisPubSubAdapter extends JedisPubSub {
		public void onMessage(String channel, String message) {
		}

		public void onPMessage(String pattern, String channel, String message) {
		}

		public void onPSubscribe(String pattern, int subscribedChannels) {
		}

		public void onPUnsubscribe(String pattern, int subscribedChannels) {
		}

		public void onSubscribe(String channel, int subscribedChannels) {
		}

		public void onUnsubscribe(String channel, int subscribedChannels) {
		}
	}

	protected static class ShardedJedisFactory implements PooledObjectFactory<ShardedJedis> {
		private List<JedisShardInfo> shards;
		private Hashing algo;
		private Pattern keyTagPattern;

		public ShardedJedisFactory(List<JedisShardInfo> shards, Hashing algo, Pattern keyTagPattern) {
			this.shards = shards;
			this.algo = algo;
			this.keyTagPattern = keyTagPattern;
		}

		public PooledObject<ShardedJedis> makeObject() throws Exception {
			ShardedJedis jedis = new ShardedJedis(this.shards, this.algo, this.keyTagPattern);
			return new DefaultPooledObject(jedis);
		}

		public void destroyObject(PooledObject<ShardedJedis> pooledShardedJedis) throws Exception {
			ShardedJedis shardedJedis = (ShardedJedis) pooledShardedJedis.getObject();
			for (Jedis jedis : shardedJedis.getAllShards())
				try {
					try {
						jedis.quit();
					} catch (Exception e) {
					}
					jedis.disconnect();
				} catch (Exception e) {
				}
		}

		public boolean validateObject(PooledObject<ShardedJedis> pooledShardedJedis) {
			try {
				ShardedJedis jedis = (ShardedJedis) pooledShardedJedis.getObject();
				for (Jedis shard : jedis.getAllShards()) {
					if (!(shard.ping().equals("PONG"))) {
						return false;
					}
				}
				return true;
			} catch (Exception ex) {
			}
			return false;
		}

		public void activateObject(PooledObject<ShardedJedis> p) throws Exception {
		}

		public void passivateObject(PooledObject<ShardedJedis> p) throws Exception {
		}
	}
}