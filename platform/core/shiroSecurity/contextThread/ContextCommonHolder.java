package com.piedpiper.platform.core.shiroSecurity.contextThread;

import com.piedpiper.platform.commons.utils.SerializeUtil;
import com.piedpiper.platform.core.redis.JedisSentinelPool;
import com.piedpiper.platform.core.shiroSecurity.shiroUtil.PropertiesConfigurationLoader;
import com.piedpiper.platform.core.spring.SpringFactory;
import java.util.Map;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import redis.clients.jedis.ShardedJedis;

public class ContextCommonHolder extends AbstractContextHoderThread {
	private static PropertiesConfigurationLoader propertiesConfiguration;
	private static String AVICIT_TEMP_DIR;

	public static String getPlatformTempFileStorePath() {
		return AVICIT_TEMP_DIR;
	}

	private static final ThreadLocal<String> midCookeidThreadLocal = new ThreadLocal();

	private static final ThreadLocal<HttpServletResponse> responseThreadLocal = new ThreadLocal();

	private static final ThreadLocal<HttpServletRequest> requestThreadLocal = new ThreadLocal();

	private static final ThreadLocal<String> currentDataSourceNameThreadLocal = new ThreadLocal();

	public ContextCommonHolder() {
		INSTANCE = this;
	}

	public static final void setCurrentDataSourceName(String dataSourceName) {
		currentDataSourceNameThreadLocal.set(dataSourceName);
	}

	public static final String getCurrentDataSourceName() {
		return (String) currentDataSourceNameThreadLocal.get();
	}

	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) requestThreadLocal.get();
	}

	public static HttpServletResponse getResponse() {
		return (HttpServletResponse) responseThreadLocal.get();
	}

	public static void setRequestAttribute(String key, Object obj) {
		((HttpServletRequest) requestThreadLocal.get()).setAttribute(key, obj);
	}

	public static Object getRequestAttribute(String key) {
		return ((HttpServletRequest) requestThreadLocal.get()).getAttribute(key);
	}

	public static String getRequestParameter(String key) {
		return ((HttpServletRequest) requestThreadLocal.get()).getParameter(key);
	}

	public static String getCookeMid() {
		return (String) midCookeidThreadLocal.get();
	}

	public static void setCookeMid(String mid) {
		midCookeidThreadLocal.set(mid);
	}

	public static void removeCurrentDataSourceName() {
		currentDataSourceNameThreadLocal.remove();
	}

	public static void clearContext() {
		responseThreadLocal.remove();
		midCookeidThreadLocal.remove();
		requestThreadLocal.remove();
		currentDataSourceNameThreadLocal.remove();
	}

	public static String getPlatformProperty(String key) {
		return propertiesConfiguration.getProperties().getProperty(key);
	}

	public static Properties getPropertiesConfiguration() {
		return propertiesConfiguration.getProperties();
	}

	private static ContextCommonHolder INSTANCE = null;

	public static void setHttpRequestResponseHolder(HttpServletRequest request, HttpServletResponse response) {
		responseThreadLocal.set(response);
		requestThreadLocal.set(request);
		setCookeMid(request.getSession().getId());
	}

	public static Object getAttribute(String arg0) {
		JedisSentinelPool pool = (JedisSentinelPool) SpringFactory.getBean("jedisSentinelPool");
		ShardedJedis jedis = (ShardedJedis) pool.getResource();
		Map session = (Map) SerializeUtil.unserialize(jedis.get(getCookeMid().getBytes()));
		pool.returnResource(jedis);
		Object obj = session.get(arg0);
		return obj;
	}
}
