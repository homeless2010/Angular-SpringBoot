package com.piedpiper.platform.core.spring;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.support.XmlWebApplicationContext;

public class SpringFactory implements ApplicationContextAware, DisposableBean {
	private static ApplicationContext applicationContext = null;

	private static Logger logger = LoggerFactory.getLogger(SpringFactory.class);

	public void setApplicationContext(ApplicationContext applicationContext) {
		logger.info("注入ApplicationContext到SpringFactory:" + applicationContext);
		if (applicationContext != null) {
			logger.warn("SpringFactory中的ApplicationContext被覆盖, 原有ApplicationContext为:" + applicationContext);
		}

		applicationContext = applicationContext;
	}

	public void destroy() throws Exception {
	}

	public static ApplicationContext getApplicationContext() {
		assertContextInjected();
		return applicationContext;
	}

	public static <T> T getBean(String name) {
		assertContextInjected();
		return (T) applicationContext.getBean(name);
	}

	public static <T> T getBean(Class<T> requiredType) {
		assertContextInjected();
		return (T) applicationContext.getBean(requiredType);
	}

	private static void clear() {
		logger.debug("清除SpringFactory中的ApplicationContext:" + applicationContext);
		applicationContext = null;
	}

	private static void assertContextInjected() {
		if (applicationContext == null) {

			try {

				while (applicationContext == null) {
					Thread.sleep(5000L);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (applicationContext == null) {
				throw new IllegalStateException("applicaitonContext未注入,请在applicationContext.xml中定义SpringFactory");
			}
		}
	}

	public static Map<String, BeanDefinition> getBeanDefinitions() {
		ApplicationContext springAppContext = getApplicationContext();

		XmlWebApplicationContext webContext = (XmlWebApplicationContext) springAppContext;

		DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) webContext.getBeanFactory();

		String[] beanNames = beanFactory.getBeanDefinitionNames();
		Map<String, BeanDefinition> beanMap = new HashMap();

		for (int i = 0; i < beanNames.length; i++) {
			beanMap.put(beanNames[i], beanFactory.getBeanDefinition(beanNames[i]));
		}
		webContext.close();
		return beanMap;
	}
}
