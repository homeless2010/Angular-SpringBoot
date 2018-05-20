package com.piedpiper.platform.core.dao.hibernate;

import com.piedpiper.platform.commons.utils.PojoUtil;
import com.piedpiper.platform.commons.utils.reflection.ReflectionUtils;
import com.piedpiper.platform.core.properties.PlatformConstant.OpType;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public class SimpleHibernateDao<T, PK extends Serializable> {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected SessionFactory sessionFactory;

	protected Class<T> entityClass;

	public SimpleHibernateDao() {
		this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
	}

	public SimpleHibernateDao(SessionFactory sessionFactory, Class<T> entityClass) {
		this.sessionFactory = sessionFactory;
		this.entityClass = entityClass;
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public void save(T entity) {
		Assert.notNull(entity, "entity不能为空");
		try {
			PojoUtil.setSysProperties(entity, PlatformConstant.OpType.insert);
		} catch (Exception e) {
			this.logger.error("没有继承BeanBase:" + entity.getClass());
		} finally {
			getSession().save(entity);
		}
		this.logger.debug("save entity: {}", entity);
	}

	public void update(T entity) {
		Assert.notNull(entity, "entity不能为空");
		try {
			PojoUtil.setSysProperties(entity, PlatformConstant.OpType.update);
		} catch (Exception e) {
			this.logger.error("没有继承BeanBase:" + entity.getClass());
		} finally {
			getSession().update(entity);
		}
		this.logger.debug("update entity: {}", entity);
	}

	public void saveOrUpdate(T entity) {
		Assert.notNull(entity, "entity不能为空");
		try {
			PojoUtil.setSysProperties(entity, PlatformConstant.OpType.insert);
		} catch (Exception e) {
			this.logger.error("没有继承BeanBase:" + entity.getClass());
		} finally {
			getSession().saveOrUpdate(entity);
		}
		this.logger.debug("save entity: {}", entity);
	}

	public void delete(T entity) {
		Assert.notNull(entity, "entity不能为空");
		getSession().delete(entity);
		this.logger.debug("delete entity: {}", entity);
	}

	public void delete(PK id) {
		Assert.notNull(id, "id不能为空");
		delete(get(id));
		this.logger.debug("delete entity {},id is {}", this.entityClass.getSimpleName(), id);
	}

	public T get(PK id) {
		Assert.notNull(id, "id不能为空");

		return (T) getSession().get(this.entityClass, id);
	}

	public List<T> get(Collection<PK> ids) {
		return find(new Criterion[] { Restrictions.in(getIdName(), ids) });
	}

	public List<T> getAll() {
		return find(new Criterion[0]);
	}

	public List<T> getAll(String orderByProperty, boolean isAsc) {
		Criteria c = createCriteria(new Criterion[0]);
		if (isAsc) {
			c.addOrder(Order.asc(orderByProperty));
		} else {
			c.addOrder(Order.desc(orderByProperty));
		}
		return c.list();
	}

	public List<T> findBy(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName不能为空");
		Criterion criterion = Restrictions.eq(propertyName, value);
		return find(new Criterion[] { criterion });
	}

	public T findUniqueBy(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName不能为空");
		Criterion criterion = Restrictions.eq(propertyName, value);
		return (T) createCriteria(new Criterion[] { criterion }).uniqueResult();
	}

	public <X> List<X> find(String hql, Object... values) {
		return createQuery(hql, values).list();
	}

	public <X> List<X> find(String hql, Map<String, ?> values) {
		return createQuery(hql, values).list();
	}

	public <X> X findUnique(String hql, Object... values) {
		return (X) createQuery(hql, values).uniqueResult();
	}

	public <X> X findUnique(String hql, Map<String, ?> values) {
		return (X) createQuery(hql, values).uniqueResult();
	}

	public int batchExecute(String hql, Object... values) {
		return createQuery(hql, values).executeUpdate();
	}

	public int batchExecute(String hql, Map<String, ?> values) {
		return createQuery(hql, values).executeUpdate();
	}

	public Query createQuery(String queryString, Object... values) {
		Assert.hasText(queryString, "queryString不能为空");
		Query query = getSession().createQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}

	public Query createQuery(String queryString, Map<String, ?> values) {
		Assert.hasText(queryString, "queryString不能为空");
		Query query = getSession().createQuery(queryString);
		if (values != null) {
			query.setProperties(values);
		}
		return query;
	}

	public List<T> find(Criterion... criterions) {
		return createCriteria(criterions).list();
	}

	public T findUnique(Criterion... criterions) {
		return (T) createCriteria(criterions).uniqueResult();
	}

	public Criteria createCriteria(Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(this.entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}

	public void initProxyObject(Object proxy) {
		Hibernate.initialize(proxy);
	}

	public void flush() {
		getSession().flush();
	}

	public void evict(Object entity) {
		getSession().evict(entity);
	}

	public Query distinct(Query query) {
		query.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return query;
	}

	public Criteria distinct(Criteria criteria) {
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return criteria;
	}

	public String getIdName() {
		ClassMetadata meta = getSessionFactory().getClassMetadata(this.entityClass);
		return meta.getIdentifierPropertyName();
	}

	public boolean isPropertyUnique(String propertyName, Object newValue, Object oldValue) {
		if ((newValue == null) || (newValue.equals(oldValue))) {
			return true;
		}
		Object object = findUniqueBy(propertyName, newValue);
		return object == null;
	}
}
