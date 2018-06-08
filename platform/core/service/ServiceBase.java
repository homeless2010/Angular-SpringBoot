package com.piedpiper.platform.core.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.Assert;

import com.piedpiper.platform.core.dao.Paging;
import com.piedpiper.platform.core.dao.hibernate.CommonHibernateDao2;
import com.piedpiper.platform.core.domain.BeanBase;
import com.piedpiper.platform.core.exception.DaoException;
import com.piedpiper.platform.core.jdbc.JdbcAvicit;
import com.piedpiper.platform.core.properties.PlatformConstant;
import com.piedpiper.platform.core.rest.msg.LogBase;

public abstract class ServiceBase {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	protected CommonHibernateDao2 hibernateDao;

	@Autowired(required = true)
	@Qualifier("jdbcAvicit")
	protected JdbcAvicit jdbc;

	public <T> Paging<T> findForPage(Paging<T> paging, String hql) {
		Assert.notNull(paging);
		Assert.notNull(hql);
		return this.hibernateDao.findByHQLForPage(paging, hql);
	}

	public <T> Paging<T> findForPage(Paging<T> paging, String hql, Map<String, Object> params) {
		Assert.notNull(paging);
		Assert.notNull(hql);
		Assert.notNull(params);
		Object[] params_array = params.values().toArray();

		return this.hibernateDao.findByHQLForPage(paging, hql, params_array);
	}

	public <T> Paging<T> findAllForPage(Paging<T> page, Class<T> clazz) {
		Assert.notNull(page);
		Assert.notNull(clazz);

		return this.hibernateDao.findAllForPage(page, clazz);
	}

	public <T> void doOperations(Map<T, PlatformConstant.OpType> dataMap, LogBase logBase) {
		Set<Map.Entry<T, PlatformConstant.OpType>> set = dataMap.entrySet();
		for (Iterator<Map.Entry<T, PlatformConstant.OpType>> it = set.iterator(); it.hasNext();) {
			Map.Entry<T, PlatformConstant.OpType> entry = (Map.Entry) it.next();
			PlatformConstant.OpType type = (PlatformConstant.OpType) entry.getValue();
			switch (type) {
			case delete:
				delete((BeanBase) entry.getKey());
				break;
			case update:
				update((BeanBase) entry.getKey());
				break;
			case insert:
				insert((BeanBase) entry.getKey());
			}

		}
	}

	public abstract void insert(BeanBase paramBeanBase) throws DaoException;

	public abstract void delete(BeanBase paramBeanBase) throws DaoException;

	public abstract void update(BeanBase paramBeanBase) throws DaoException;

	protected <T extends EventBase> void BeforeInsertEvent(List<T> eventBaseList, BeanBase beanBase) throws Exception {
		if (null != eventBaseList) {
			for (EventBase beforeInsertEvent : eventBaseList) {
				beforeInsertEvent.beforeInsert(beanBase);
			}
		}
	}

	protected <T extends EventBase> void AfterInsertEvent(List<T> eventBaseList, BeanBase beanBase) {
		if (null != eventBaseList) {
			for (EventBase afterInsertEvent : eventBaseList) {
				afterInsertEvent.afterInsert(beanBase);
			}
		}
	}

	protected <T extends EventBase> void BeforeUpdateEvent(List<T> eventBaseList, BeanBase beanBase) throws Exception {
		if (null != eventBaseList) {
			for (EventBase beforeUpdateEvent : eventBaseList) {
				beforeUpdateEvent.beforeUpdate(beanBase);
			}
		}
	}

	protected <T extends EventBase> void AfterUpdateEvent(List<T> eventBaseList, BeanBase beanBase) {
		if (null != eventBaseList) {
			for (EventBase afterUpdateEvent : eventBaseList) {
				afterUpdateEvent.afterUpdate(beanBase);
			}
		}
	}

	protected <T extends EventBase> void BeforeDeleteEvent(List<T> eventBaseList, BeanBase beanBase) throws Exception {
		if (null != eventBaseList) {
			for (EventBase beforeDeleteEvent : eventBaseList) {
				beforeDeleteEvent.beforeDelete(beanBase);
			}
		}
	}

	protected <T extends EventBase> void AfterDeleteEvent(List<T> eventBaseList, BeanBase beanBase) {
		if (null != eventBaseList) {
			for (EventBase afterDeleteEvent : eventBaseList) {
				afterDeleteEvent.afterDelete(beanBase);
			}
		}
	}
}
