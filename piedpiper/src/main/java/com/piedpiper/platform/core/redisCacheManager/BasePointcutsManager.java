package com.piedpiper.platform.core.redisCacheManager;

import org.aspectj.lang.annotation.Pointcut;

public class BasePointcutsManager {
	@Pointcut("target(com.piedpiper.platform.core.redisCacheManager.BaseCacheDAO)")
	public void logMessage() {
	}

	@Pointcut("@annotation(com.piedpiper.platform.core.redisCacheManager.BaseCacheService)")
	public void BaseCacheService() {
	}

	@Pointcut("execution(* save**(..)) || execution(* update**(..)) || execution(* delete**(..))")
	protected void refreshRegistration() {
	}
}
