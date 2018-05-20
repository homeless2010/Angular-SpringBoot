package com.piedpiper.platform.core.redisCacheManager;

import java.lang.reflect.Method;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class BaseAspectManager extends BasePointcutsManager {
	private static final Logger log = LoggerFactory.getLogger(BaseAspectManager.class);

	@Autowired
	private BaseCacheManager baseCacheManager;

	@Around(value = "logMessage()&&refreshRegistration()", argNames = "argname")
	public Object aroundAdvice(ProceedingJoinPoint point) throws Throwable {
		log.debug("通知::::::::::开始目标:::::::::::" + point.getTarget().toString());
		String methodName = point.getSignature().getName();
		MethodSignature msig = (MethodSignature) point.getSignature();
		Class<?>[] paramTypes = msig.getParameterTypes();
		Method method = point.getTarget().getClass().getMethod(methodName, paramTypes);
		Object[] args = point.getArgs();
		AvicitCache avicitcache = (AvicitCache) method.getAnnotation(AvicitCache.class);
		return doCache(point, methodName, args, avicitcache, method);
	}

	@Around("BaseCacheService()")
	public Object aroundBaseCacheService(ProceedingJoinPoint point) throws Throwable {
		log.debug("通知::::::::::开始目标:::::::::::" + point.getTarget().toString());
		String methodName = point.getSignature().getName();
		MethodSignature msig = (MethodSignature) point.getSignature();
		Class<?>[] paramTypes = msig.getParameterTypes();
		Method method = point.getTarget().getClass().getDeclaredMethod(methodName, paramTypes);
		Object[] args = point.getArgs();
		BaseCacheService baseCacheService = (BaseCacheService) method.getAnnotation(BaseCacheService.class);
		AvicitCache avicitcache = (AvicitCache) method.getAnnotation(AvicitCache.class);
		return doCache(point, baseCacheService.methodType(), args, avicitcache, method);
	}

	private Object doCache(ProceedingJoinPoint point, String methodName, Object[] args, AvicitCache avicitcache,
			Method method) throws Throwable {
		Object aspect = null;

		if (methodName.startsWith("save")) {
			aspect = point.proceed();
			if (BaseCacheUtil.vadidationArgs(args)) {
				this.baseCacheManager.insertCache((BaseCacheBean) args[0]);
			}
		} else if (methodName.startsWith("update")) {
			aspect = point.proceed();
			if (BaseCacheUtil.vadidationArgs(args)) {
				this.baseCacheManager.updateCache((BaseCacheBean) args[0]);
			}
		} else if (methodName.startsWith("delete")) {

			aspect = point.proceed();
			if (BaseCacheUtil.vadidationArgs(args)) {
				this.baseCacheManager.deleteCache((BaseCacheBean) args[0]);
			} else if ((BaseCacheUtil.vadidationArgsIsString(args)) && (!BaseCacheUtil.isNull(avicitcache))) {
				this.baseCacheManager.deleteCacheById((String) args[0], avicitcache.prefix(), avicitcache.beanClass());
			}
		}

		return aspect;
	}
}
