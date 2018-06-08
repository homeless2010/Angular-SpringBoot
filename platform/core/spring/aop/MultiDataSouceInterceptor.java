package com.piedpiper.platform.core.spring.aop;

import com.piedpiper.platform.core.dao.datasource.MultiDataSouceThreadLocal;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Properties;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class MultiDataSouceInterceptor implements MethodInterceptor {
	private Logger logger;
	private Properties properties;
	private Resource configFileLocation;

	public MultiDataSouceInterceptor() {
		this.logger = LoggerFactory.getLogger(super.getClass());
		this.properties = null;
		this.configFileLocation = new ClassPathResource("configuration/jdbc/jdbc.properties");
	}

	public Object invoke(MethodInvocation mi) throws Throwable {
		Method m = mi.getMethod();
		String className = m.getDeclaringClass().getName();

		String dataSourceName = getDataSourceNameByClassName(className);
		String threadName = Thread.currentThread().getName();

		this.logger.trace(
				"根据[" + className + "." + m.getName() + "]名称属性将当前线程[" + threadName + "]的数据源设置为：" + dataSourceName);

		MultiDataSouceThreadLocal.setCurrentDataSource(dataSourceName);

		return mi.proceed();
	}

	private String getDataSourceNameByClassName(String className) {
		Enumeration e;
		try {
			if (this.properties == null) {
				Resource res = getConfigFileLocation();
				this.properties = new Properties();
				this.properties.load(res.getInputStream());
			}

			for (e = this.properties.keys(); e.hasMoreElements();) {
				String key = (String) e.nextElement();
				String value = this.properties.getProperty(key);
				if (className.contains(key))
					return value;
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return "defaultDataSource";
	}

	public Resource getConfigFileLocation() {
		return this.configFileLocation;
	}

	public void setConfigFileLocation(Resource configFileLocation) {
		this.configFileLocation = configFileLocation;
	}
}