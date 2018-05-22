package com.piedpiper.platform.core.spring;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheSpringFactory {
	private static final Map<Class<?>, Object> map = new ConcurrentHashMap();

	private static class _class {
		private static CacheSpringFactory instance = new CacheSpringFactory();
	}

	public static CacheSpringFactory getInstance() {
		return _class.instance;
	}

	public <T> T getBean(Class<T> requiredType) {
		if (map.get(requiredType) == null) {
			T t = SpringFactory.getBean(requiredType);
			map.put(requiredType, t);
			return t;
		}
		return (T) map.get(requiredType);
	}
}
