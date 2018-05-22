package com.piedpiper.platform.core.threadPool;

import com.piedpiper.platform.core.dao.hibernate.CommonHibernateDao2;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheBean;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheManager;
import java.util.List;
import java.util.concurrent.Callable;

public class FastLoader implements Callable<String> {
	private CommonHibernateDao2 dao;
	private BaseCacheManager m;
	private Class<?> c;

	public FastLoader(BaseCacheManager m, CommonHibernateDao2 dao, Class<?> c) {
		this.dao = dao;
		this.m = m;
		this.c = c;
	}

	public String call() throws Exception {
		List<?> list = this.dao.loadAll(this.c);
		for (Object object : list) {
			this.m.insertCache((BaseCacheBean) object);
		}
		return "1";
	}
}
