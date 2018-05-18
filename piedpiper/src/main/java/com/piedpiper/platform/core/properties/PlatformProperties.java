package com.piedpiper.platform.core.properties;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.CollectionUtils;
/**
 * 读取平台配置信息
 * @author homeless2010
 *
 */
public class PlatformProperties {
	private static Logger logger = LoggerFactory.getLogger(PlatformProperties.class);

	private static final Properties _PROP = new Properties();
	private static String[] _unReWrites;
	private static String _unReWrite1 = "platform.unableModify.system.sysRole";

	private static void loadProperties() throws Exception {
		ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();

		Resource res = resourceResolver.getResource("classpath:platform.properties");
		logger.info("开始加载系统参数信息");
		Properties prop = new Properties();
		InputStream inputStream = res.getInputStream();
		prop.load(inputStream);
		CollectionUtils.mergePropertiesIntoMap(prop, _PROP);
		printPropertis(prop);
		inputStream.close();
		logger.info("完成加载系统参数信息");

		Resource[] resources = resourceResolver.getResources("classpath*:platform_ext.properties");
		for (Resource res_ext : resources) {
			logger.info("开始加载系统扩展参数信息");
			prop = new Properties();
			InputStream inputStream_ext = res_ext.getInputStream();
			prop.load(inputStream_ext);

			for (String str : _unReWrites) {
				if (prop.containsKey(str)) {
					logger.warn("属性" + str + "不可以重写！");
					prop.remove(str);
				}
			}
			CollectionUtils.mergePropertiesIntoMap(prop, _PROP);
			printPropertis(prop);
			inputStream_ext.close();
			logger.info("完成加载系统扩展参数信息");
		}
	}

	private static void printPropertis(Properties prop) {
		for (Enumeration e = prop.keys(); e.hasMoreElements();) {
			String key = (String) e.nextElement();
			String val = (String) prop.get(key);
			logger.info(key + "=" + val);
		}
	}

	public static String getProperty(String key) {
		String value = null;
		try {
			value = _PROP.getProperty(key).trim();
		} catch (Exception ex) {
			logger.error("从platform配置文件取" + key + "错误:");
			logger.error(ex.getMessage(), ex);
		}
		return value;
	}

	public static boolean defaultPageAuthIsGranted() {
		String administratorUsernames = getProperty("avicit.security.defaultPageAuthIsGranted");
		return Boolean.valueOf(administratorUsernames).booleanValue();
	}

	static {
		try {
			_unReWrites = new String[] { _unReWrite1 };
			loadProperties();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new Error(e);
		}
	}
}