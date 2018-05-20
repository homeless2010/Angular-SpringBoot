package com.piedpiper.platform.core.sfn.intercept;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;

@Intercepts({
		@org.apache.ibatis.plugin.Signature(type = org.apache.ibatis.executor.Executor.class, method = "query", args = {
				org.apache.ibatis.mapping.MappedStatement.class, Object.class,
				org.apache.ibatis.session.RowBounds.class, org.apache.ibatis.session.ResultHandler.class }) })
public class SelfDefinedQryInterceptor implements Interceptor {
	public Object intercept(Invocation invocation) throws Throwable {
		Map<String, Object> local = (Map) SelfDefinedQueryTrigger.SelfDefinedQueryParam.get();
		if (local != null) {
			Object[] args = invocation.getArgs();
			Map<String, Object> paramMap = null;
			Object parameterObject = args[1];
			if (parameterObject == null) {
				paramMap = new HashMap();
			} else if ((parameterObject instanceof Map)) {
				paramMap = (Map) parameterObject;
				paramMap.putAll(local);
				SelfDefinedQueryTrigger.SelfDefinedQueryParam.remove();
			} else {
				paramMap = new HashMap();
			}
		}

		return invocation.proceed();
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
	}
}
