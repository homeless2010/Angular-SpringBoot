package com.piedpiper.platform.core.service;

import com.piedpiper.platform.core.domain.BeanBase;

public abstract interface EventBase {
	public abstract void beforeInsert(BeanBase paramBeanBase) throws Exception;

	public abstract void afterInsert(BeanBase paramBeanBase);

	public abstract void beforeUpdate(BeanBase paramBeanBase) throws Exception;

	public abstract void afterUpdate(BeanBase paramBeanBase);

	public abstract void beforeDelete(BeanBase paramBeanBase) throws Exception;

	public abstract void afterDelete(BeanBase paramBeanBase);
}
