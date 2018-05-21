package com.piedpiper.platform.core.dao.hibernate;

import com.piedpiper.platform.commons.utils.reflection.ReflectionUtils;
import com.piedpiper.platform.core.dao.Paging;
import com.piedpiper.platform.core.dao.PropertyFilter;
import com.piedpiper.platform.core.dao.PropertyFilter.MatchType;
import com.piedpiper.platform.core.exception.DaoException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Filter;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.ReplicationMode;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.impl.CriteriaImpl.OrderEntry;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.util.Assert;

public class CommonHibernateDao2 {
	private HibernateTemplate hibernateTemplate;
	static Logger logger = LoggerFactory.getLogger(CommonHibernateDao2.class);

	public int bulkUpdate(String queryString) throws DataAccessException {
		return getHibernateTemplate().bulkUpdate(queryString);
	}

	public int bulkUpdate(String queryString, Object[] values) throws DataAccessException {
		return getHibernateTemplate().bulkUpdate(queryString, values);
	}

	public int bulkUpdate(String queryString, Object value) throws DataAccessException {
		return getHibernateTemplate().bulkUpdate(queryString, value);
	}

	public void clear() throws DataAccessException {
		getHibernateTemplate().clear();
	}

	public void closeIterator(Iterator<?> it) throws DataAccessException {
		getHibernateTemplate().closeIterator(it);
	}

	public boolean contains(Object entity) throws DataAccessException {
		return getHibernateTemplate().contains(entity);
	}

	public long countCriteriaResult(Criteria c) {
		CriteriaImpl impl = (CriteriaImpl) c;

		Projection projection = impl.getProjection();
		ResultTransformer transformer = impl.getResultTransformer();

		List orderEntries = null;
		try {
			orderEntries = (List) ReflectionUtils.getFieldValue(impl, "orderEntries");
			ReflectionUtils.setFieldValue(impl, "orderEntries", new ArrayList());
		} catch (Exception e) {
			logger.error("不可能抛出的异常:{}", e);
		}

		Long totalCountObject = (Long) c.setProjection(Projections.rowCount()).uniqueResult();
		long totalCount = (totalCountObject != null) ? totalCountObject.longValue() : 0L;

		c.setProjection(projection);

		if (projection == null) {
			c.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		}
		if (transformer != null)
			c.setResultTransformer(transformer);
		try {
			ReflectionUtils.setFieldValue(impl, "orderEntries", orderEntries);
		} catch (Exception e) {
			logger.error("不可能抛出的异常:{}", e);
		}

		return totalCount;
	}

	public long countHqlResult(String hql, Object[] values) {
		String countHql = prepareCountHql(hql);
		try {
			Long count = (Long) findUnique(countHql, values);
			return count.longValue();
		} catch (Exception e) {
			throw new RuntimeException("hql can't be auto count, hql is:" + countHql, e);
		}
	}

	protected long countHqlResult1(String hql, Map<String, ?> values) {
		String countHql = prepareCountHql(hql);
		try {
			Long count = (Long) findUnique1(countHql, values);
			return count.longValue();
		} catch (Exception e) {
			throw new RuntimeException("hql can't be auto count, hql is:" + countHql, e);
		}
	}

	public Query createQuery(String queryString, Object[] values) {
		Assert.hasText(queryString, "queryString不能为空");
		Query query = getSession().createQuery(queryString);
		for (int i = 0; i < values.length; ++i) {
			if (values[i] == null)
				query.setParameter(i, null);
			else {
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}

	public void delete(Object entity) throws DataAccessException {
		getHibernateTemplate().delete(entity);
	}

	public void delete(Object entity, LockMode lockMode) throws DataAccessException {
		getHibernateTemplate().delete(entity, lockMode);
	}

	public void delete(String entityName, Object entity) throws DataAccessException {
		getHibernateTemplate().delete(entityName, entity);
	}

	public void delete(String entityName, Object entity, LockMode lockMode) throws DataAccessException {
		getHibernateTemplate().delete(entityName, entity, lockMode);
	}

	public void deleteAll(Collection<?> entities) throws DataAccessException {
		getHibernateTemplate().deleteAll(entities);
	}

	public Filter enableFilter(String filterName) throws IllegalStateException {
		return getHibernateTemplate().enableFilter(filterName);
	}

	public void evict(Object entity) throws DataAccessException {
		getHibernateTemplate().evict(entity);
	}

	public <T> T execute(HibernateCallback<T> action) throws DataAccessException {
		return getHibernateTemplate().execute(action);
	}

	public List executeFind(HibernateCallback<?> action) throws DataAccessException {
		return getHibernateTemplate().executeFind(action);
	}

	public <T> T executeWithNativeSession(HibernateCallback<T> action) {
		return getHibernateTemplate().executeWithNativeSession(action);
	}

	public <T> T executeWithNewSession(HibernateCallback<T> action) {
		return getHibernateTemplate().executeWithNewSession(action);
	}

	public List find(String queryString) throws DataAccessException {
		return getHibernateTemplate().find(queryString);
	}

	public List find(String queryString, Object[] values) throws DataAccessException {
		return getHibernateTemplate().find(queryString, values);
	}

	public List find(String queryString, Object value) throws DataAccessException {
		return getHibernateTemplate().find(queryString, value);
	}

	public <T> Paging<T> findAllForPage(Paging<T> page, Class<T> clazz) {
		Assert.notNull(page, "page参数不能为空");
		Criteria c = getSession().createCriteria(clazz);
		page.setTotalCount(countCriteriaResult(c));
		c.setFirstResult(page.getFirst() - 1);
		c.setMaxResults(page.getPageSize());
		List result = c.list();
		page.setResult(result);
		return page;
	}

	public List findByCriteria(DetachedCriteria criteria) throws DataAccessException {
		return getHibernateTemplate().findByCriteria(criteria);
	}

	public List findByCriteria(DetachedCriteria criteria, int firstResult, int maxResults) throws DataAccessException {
		return getHibernateTemplate().findByCriteria(criteria, firstResult, maxResults);
	}

	public List findByExample(Object exampleEntity) throws DataAccessException {
		return getHibernateTemplate().findByExample(exampleEntity);
	}

	public List findByExample(Object exampleEntity, int firstResult, int maxResults) throws DataAccessException {
		return getHibernateTemplate().findByExample(exampleEntity, firstResult, maxResults);
	}

	public List findByExample(String entityName, Object exampleEntity) throws DataAccessException {
		return getHibernateTemplate().findByExample(entityName, exampleEntity);
	}

	public List findByExample(String entityName, Object exampleEntity, int firstResult, int maxResults)
			throws DataAccessException {
		return getHibernateTemplate().findByExample(entityName, exampleEntity, firstResult, maxResults);
	}

	public <T> Paging<T> findByHQLForPage(Paging<T> page, String hql) {
		Assert.notNull(page, "page不能为空");

		Query q = getSession().createQuery(hql);

		if (page.isAutoCount()) {
			String counthql = prepareCountHql(hql);

			long totalCount = ((Long) getSession().createQuery(counthql).uniqueResult()).longValue();
			page.setTotalCount(totalCount);
		}

		setPageParameterToQuery(q, page);

		List result = q.list();
		page.setResult(result);
		return page;
	}

	public <T> Paging<T> findByHQLForPage(Paging<T> page, String hql, Object[] params) {
		Assert.notNull(page, "page不能为空");

		Query q = getSession().createQuery(hql);
		for (int i = 0; i < params.length; ++i) {
			q.setParameter(i, params[i]);
		}

		if (page.isAutoCount()) {
			long totalCount = countHqlResult(hql, params);
			page.setTotalCount(totalCount);
		}

		setPageParameterToQuery(q, page);

		List result = q.list();
		page.setResult(result);
		return page;
	}

	public List findByNamedParam(String queryString, String paramName, Object value) throws DataAccessException {
		return getHibernateTemplate().findByNamedParam(queryString, paramName, value);
	}

	public List findByNamedParam(String queryString, String[] paramNames, Object[] values) throws DataAccessException {
		return getHibernateTemplate().findByNamedParam(queryString, paramNames, values);
	}

	public List findByNamedQuery(String queryName) throws DataAccessException {
		return getHibernateTemplate().findByNamedQuery(queryName);
	}

	public List findByNamedQuery(String queryName, Object[] values) throws DataAccessException {
		return getHibernateTemplate().findByNamedQuery(queryName, values);
	}

	public List findByNamedQuery(String queryName, Object value) throws DataAccessException {
		return getHibernateTemplate().findByNamedQuery(queryName, value);
	}

	public List findByNamedQueryAndNamedParam(String queryName, String paramName, Object value)
			throws DataAccessException {
		return getHibernateTemplate().findByNamedQueryAndNamedParam(queryName, paramName, value);
	}

	public List findByNamedQueryAndNamedParam(String queryName, String[] paramNames, Object[] values)
			throws DataAccessException {
		return getHibernateTemplate().findByNamedQueryAndNamedParam(queryName, paramNames, values);
	}

	public List findByNamedQueryAndValueBean(String queryName, Object valueBean) throws DataAccessException {
		return getHibernateTemplate().findByNamedQueryAndValueBean(queryName, valueBean);
	}

	public List findBySQL(String sql) {
		SQLQuery sqlquery = getSession().createSQLQuery(sql);
		return sqlquery.list();
	}

	public List findBySQL(String sql, Session session) {
		return session.createSQLQuery(sql).list();
	}

	public List findBySQL(String sql, Object[] params) {
		SQLQuery sqlquery = getSession().createSQLQuery(sql);
		for (int i = 0; i < params.length; ++i) {
			sqlquery.setParameter(i, params[i]);
		}
		return sqlquery.list();
	}

	public List findByValueBean(String queryString, Object valueBean) throws DataAccessException {
		return getHibernateTemplate().findByValueBean(queryString, valueBean);
	}

	private <X> X findUnique(String hql, Object[] values) {
		Object uniqueResult = createQuery(hql, values).uniqueResult();
		return uniqueResult;
	}

	public <X> X findUnique1(String hql, Map<String, ?> values) {
		return createQuery1(hql, values).uniqueResult();
	}

	public void flush() throws DataAccessException {
		getHibernateTemplate().flush();
	}

	public <T> T get(Class<T> entityClass, Serializable id) throws DataAccessException {
		return getHibernateTemplate().get(entityClass, id);
	}

	public <T> T get(Class<T> entityClass, Serializable id, LockMode lockMode) throws DataAccessException {
		return getHibernateTemplate().get(entityClass, id, lockMode);
	}

	public Object get(String entityName, Serializable id) throws DataAccessException {
		return getHibernateTemplate().get(entityName, id);
	}

	public Object get(String entityName, Serializable id, LockMode lockMode) throws DataAccessException {
		return getHibernateTemplate().get(entityName, id, lockMode);
	}

	public int getFetchSize() {
		return getHibernateTemplate().getFetchSize();
	}

	public HibernateTemplate getHibernateTemplate() {
		return this.hibernateTemplate;
	}

	public int getMaxResults() {
		return getHibernateTemplate().getMaxResults();
	}

	public String getQueryCacheRegion() {
		return getHibernateTemplate().getQueryCacheRegion();
	}

	public Session getSession() {
		return getHibernateTemplate().getSessionFactory().getCurrentSession();
	}

	public Session openSession() {
		return getHibernateTemplate().getSessionFactory().openSession();
	}

	public void closeSession(Session session) {
		if (session == null)
			return;
		session.close();
	}

	public void initialize(Object proxy) throws DataAccessException {
		getHibernateTemplate().initialize(proxy);
	}

	public boolean isAllowCreate() {
		return getHibernateTemplate().isAllowCreate();
	}

	public boolean isAlwaysUseNewSession() {
		return getHibernateTemplate().isAlwaysUseNewSession();
	}

	public boolean isCacheQueries() {
		return getHibernateTemplate().isCacheQueries();
	}

	public boolean isCheckWriteOperations() {
		return getHibernateTemplate().isCheckWriteOperations();
	}

	public boolean isExposeNativeSession() {
		return getHibernateTemplate().isExposeNativeSession();
	}

	public Iterator<?> iterate(String queryString) throws DataAccessException {
		return getHibernateTemplate().iterate(queryString);
	}

	public Iterator<?> iterate(String queryString, Object[] values) throws DataAccessException {
		return getHibernateTemplate().iterate(queryString, values);
	}

	public Iterator<?> iterate(String queryString, Object value) throws DataAccessException {
		return getHibernateTemplate().iterate(queryString, value);
	}

	public <T> T load(Class<T> entityClass, Serializable id) throws DataAccessException {
		return getHibernateTemplate().load(entityClass, id);
	}

	public <T> T load(Class<T> entityClass, Serializable id, LockMode lockMode) throws DataAccessException {
		return getHibernateTemplate().load(entityClass, id, lockMode);
	}

	public void load(Object entity, Serializable id) throws DataAccessException {
		getHibernateTemplate().load(entity, id);
	}

	public Object load(String entityName, Serializable id) throws DataAccessException {
		return getHibernateTemplate().load(entityName, id);
	}

	public Object load(String entityName, Serializable id, LockMode lockMode) throws DataAccessException {
		return getHibernateTemplate().load(entityName, id, lockMode);
	}

	public <T> List<T> loadAll(Class<T> entityClass) throws DataAccessException {
		return getHibernateTemplate().loadAll(entityClass);
	}

	public void lock(Object entity, LockMode lockMode) throws DataAccessException {
		getHibernateTemplate().lock(entity, lockMode);
	}

	public void lock(String entityName, Object entity, LockMode lockMode) throws DataAccessException {
		getHibernateTemplate().lock(entityName, entity, lockMode);
	}

	public <T> T merge(String entityName, T entity) throws DataAccessException {
		return getHibernateTemplate().merge(entityName, entity);
	}

	public <T> T merge(T entity) throws DataAccessException {
		return getHibernateTemplate().merge(entity);
	}

	public void persist(Object entity) throws DataAccessException {
		getHibernateTemplate().persist(entity);
	}

	public void persist(String entityName, Object entity) throws DataAccessException {
		getHibernateTemplate().persist(entityName, entity);
	}

	private String prepareCountHql(String orgHql) {
		String fromHql = orgHql;

		fromHql = "from " + StringUtils.substringAfter(fromHql, "from");
		fromHql = StringUtils.substringBefore(fromHql, "order by");

		String countHql = "select count(*) " + fromHql;
		return countHql;
	}

	public void refresh(Object entity) throws DataAccessException {
		getHibernateTemplate().refresh(entity);
	}

	public void refresh(Object entity, LockMode lockMode) throws DataAccessException {
		getHibernateTemplate().refresh(entity, lockMode);
	}

	public void replicate(Object entity, ReplicationMode replicationMode) throws DataAccessException {
		getHibernateTemplate().replicate(entity, replicationMode);
	}

	public void replicate(String entityName, Object entity, ReplicationMode replicationMode)
			throws DataAccessException {
		getHibernateTemplate().replicate(entityName, entity, replicationMode);
	}

	public Serializable save(Object entity) throws DataAccessException {
		return getHibernateTemplate().save(entity);
	}

	public Serializable save(String entityName, Object entity) throws DataAccessException {
		return getHibernateTemplate().save(entityName, entity);
	}

	public void saveOrUpdate(Object entity) throws DataAccessException {
		getHibernateTemplate().saveOrUpdate(entity);
	}

	public void saveOrUpdate(String entityName, Object entity) throws DataAccessException {
		getHibernateTemplate().saveOrUpdate(entityName, entity);
	}

	public void setAllowCreate(boolean allowCreate) {
		getHibernateTemplate().setAllowCreate(allowCreate);
	}

	public void setAlwaysUseNewSession(boolean alwaysUseNewSession) {
		getHibernateTemplate().setAlwaysUseNewSession(alwaysUseNewSession);
	}

	public void setCacheQueries(boolean cacheQueries) {
		getHibernateTemplate().setCacheQueries(cacheQueries);
	}

	public void setCheckWriteOperations(boolean checkWriteOperations) {
		getHibernateTemplate().setCheckWriteOperations(checkWriteOperations);
	}

	public void setExposeNativeSession(boolean exposeNativeSession) {
		getHibernateTemplate().setExposeNativeSession(exposeNativeSession);
	}

	public void setFetchSize(int fetchSize) {
		getHibernateTemplate().setFetchSize(fetchSize);
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void setMaxResults(int maxResults) {
		getHibernateTemplate().setMaxResults(maxResults);
	}

	protected <T> Query setPageParameterToQuery(Query q, Paging<T> page) {
		Assert.isTrue(page.getPageSize() > 0, "Paging Size must larger than zero");

		q.setFirstResult(page.getFirst() - 1);
		q.setMaxResults(page.getPageSize());
		return q;
	}

	public void setQueryCacheRegion(String queryCacheRegion) {
		getHibernateTemplate().setQueryCacheRegion(queryCacheRegion);
	}

	public void update(Object entity) throws DataAccessException {
		getHibernateTemplate().update(entity);
	}

	public void update(Object entity, LockMode lockMode) throws DataAccessException {
		getHibernateTemplate().update(entity, lockMode);
	}

	public void update(String entityName, Object entity) throws DataAccessException {
		getHibernateTemplate().update(entityName, entity);
	}

	public void update(String entityName, Object entity, LockMode lockMode) throws DataAccessException {
		getHibernateTemplate().update(entityName, entity, lockMode);
	}

	protected Criterion buildCriterion(String propertyName, Object propertyValue, PropertyFilter.MatchType matchType)
  {
    Assert.hasText(propertyName, "propertyName不能为空");
    Criterion criterion = null;

    switch (1.$SwitchMap$avicit$platform6$core$dao$PropertyFilter$MatchType[matchType.ordinal()])
    {
    case 1:
      criterion = Restrictions.eq(propertyName, propertyValue);
      break;
    case 2:
      criterion = Restrictions.like(propertyName, (String)propertyValue, MatchMode.ANYWHERE);
      break;
    case 3:
      criterion = Restrictions.le(propertyName, propertyValue);
      break;
    case 4:
      criterion = Restrictions.lt(propertyName, propertyValue);
      break;
    case 5:
      criterion = Restrictions.ge(propertyName, propertyValue);
      break;
    case 6:
      criterion = Restrictions.gt(propertyName, propertyValue);
    }
    return criterion;
  }

	protected Criterion[] buildCriterionByPropertyFilter(List<PropertyFilter> filters) {
		List criterionList = new ArrayList();
		if (filters != null) {
			for (PropertyFilter filter : filters) {
				if (!(filter.hasMultiProperties())) {
					Criterion criterion = buildCriterion(filter.getPropertyName(), filter.getMatchValue(),
							filter.getMatchType());
					criterionList.add(criterion);
				} else {
					Disjunction disjunction = Restrictions.disjunction();
					for (String param : filter.getPropertyNames()) {
						Object propertyValue = filter.getMatchValue();
						if ((null != propertyValue) && (propertyValue.toString().trim().length() > 0)) {
							Criterion criterion = buildCriterion(param, filter.getMatchValue(), filter.getMatchType());
							disjunction.add(criterion);
						}
					}

					criterionList.add(disjunction);
				}
			}
		}
		return ((Criterion[]) criterionList.toArray(new Criterion[criterionList.size()]));
	}

	protected <T> Criteria setPageParameterToCriteria(Criteria c, Paging<T> page) {
		Assert.isTrue(page.getPageSize() > 0, "Paging Size must larger than zero");

		c.setFirstResult(page.getFirst() - 1);
		c.setMaxResults(page.getPageSize());

		if (page.isOrderBySetted()) {
			String[] orderByArray = StringUtils.split(page.getOrderBy(), ',');
			String[] orderArray = StringUtils.split(page.getOrder(), ',');

			Assert.isTrue(orderByArray.length == orderArray.length, "分页多重排序参数中,排序字段与排序方向的个数不相等");

			for (int i = 0; i < orderByArray.length; ++i) {
				if ("asc".equals(orderArray[i]))
					c.addOrder(Order.asc(orderByArray[i]));
				else {
					c.addOrder(Order.desc(orderByArray[i]));
				}
			}
		}
		return c;
	}

	public Object selectByRecordKey(Class<?> record, String key) throws DaoException {
		Object o = getHibernateTemplate().get(record, key);
		return o;
	}

	public String callProcedure(String procedure, List parameter) throws DaoException, SQLException {
		long MAX_EXECUTE_TIMEOUT = 5000L;
		long beginTime = System.currentTimeMillis();

		String vCall = "{call " + procedure + "(";
		for (int i = 0; i < parameter.size(); ++i) {
			vCall = vCall + "'" + parameter.get(i).toString() + "',";
		}
		vCall = vCall + "?,?)}";
		Connection conn = SessionFactoryUtils.getDataSource(getSession().getSessionFactory()).getConnection();
		CallableStatement cstmt = null;
		int cstmtCode = 0;
		String cstmtMessage = null;
		try {
			cstmt = conn.prepareCall(vCall);
			cstmt.registerOutParameter(1, 4);
			cstmt.registerOutParameter(2, 12);
			cstmt.execute();
			cstmtCode = cstmt.getInt(1);
			cstmtMessage = cstmt.getString(2);
		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		} finally {
			if (cstmt != null) {
				cstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		if (cstmtCode == 1) {
			throw new DaoException(cstmtMessage);
		}

		long executeTime = System.currentTimeMillis() - beginTime;
		if (executeTime > MAX_EXECUTE_TIMEOUT) {
			logger.error("执行 " + procedure + " 代码的时间是 " + executeTime + " 毫秒，执行速度较慢需要优化。");
		}
		return cstmtMessage;
	}

	public <T> Paging<T> findPage(Paging<T> page, String hql, Object[] values) {
		Assert.notNull(page, "page不能为空");
		String OriginalHql = hql;
		Query q = createQuery(hql, values);

		if (page.isAutoCount()) {
			long totalCount = countHqlResult(OriginalHql, values);
			page.setTotalCount(totalCount);
		}

		setPageParameterToQuery(q, page);

		List result = q.list();
		page.setResult(result);
		return page;
	}

	public <T> Paging<T> findPage(Paging<T> page, String hql, Map<String, ?> values) {
		Assert.notNull(page, "page不能为空");

		Query q = createQuery1(hql, values);

		if (page.isAutoCount()) {
			long totalCount = countHqlResult1(hql, values);
			page.setTotalCount(totalCount);
		}

		setPageParameterToQuery(q, page);
		page.setResult(q.list());
		return page;
	}

	public Query createQuery1(String queryString, Map<String, ?> values) {
		Assert.hasText(queryString, "queryString不能为空");
		Query query = getSession().createQuery(queryString);
		if (values != null) {
			query.setProperties(values);
		}
		return query;
	}

	public <T> Paging<T> findPage(Class<T> clazz, Paging<T> page, Criterion[] criterions) {
		Assert.notNull(page, "page不能为空");

		Criteria c = createCriteria(clazz, criterions);

		if (page.isAutoCount()) {
			long totalCount = countCriteriaResult(c);
			page.setTotalCount(totalCount);
		}
		setPageParameterToCriteria(c, page);

		List result = c.list();
		page.setResult(result);
		return page;
	}

	public <T> Paging<T> findPage(Paging<T> page, Criteria criteria) {
		Assert.notNull(page, "page不能为空");
		if (page.isAutoCount()) {
			long totalCount = countCriteriaResult(criteria);
			page.setTotalCount(totalCount);
		}
		setPageParameterToCriteria(criteria, page);
		page.setResult(criteria.list());
		return page;
	}

	public <T> List<T> findBy(Class<T> clazz, String propertyName, Object value, PropertyFilter.MatchType matchType) {
		Criterion criterion = buildCriterion(propertyName, value, matchType);
		return find(clazz, new Criterion[] { criterion });
	}

	public <T> List<T> find(Class<T> clazz, Criterion[] criterions) {
		return createCriteria(clazz, criterions).list();
	}

	public <T> List<T> find(Class<T> clazz, List<PropertyFilter> filters) {
		Criterion[] criterions = buildCriterionByPropertyFilter(filters);
		return find(clazz, criterions);
	}

	public <T> Criteria createCriteria(Class<T> clazz, Criterion[] criterions) {
		Criteria criteria = getSession().createCriteria(clazz);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}

	public <T> Paging<T> findPage(Class<T> clazz, Paging<T> page, List<PropertyFilter> filters) {
		if (filters != null) {
			Criterion[] criterions = buildCriterionByPropertyFilter(filters);
			return findPage(clazz, page, criterions);
		}
		return findPage(clazz, page, new Criterion[0]);
	}

	public String buildDynamicHql(List<PropertyFilter> filters, String hql, Map<String, Object> conditionsValues) {
		if (null != filters) {
			for (PropertyFilter filter : filters) {
				Object propertyValue = filter.getMatchValue();
				if (null == propertyValue)
					continue;
				if (propertyValue.toString().trim().length() == 0) {
					continue;
				}
				if (!(hql.contains("where"))) {
					hql = hql + " where ";
					hql = buildHqlDynamicCondition(hql, filter, "", conditionsValues);
				} else if (hql.trim().endsWith("and")) {
					hql = buildHqlDynamicCondition(hql, filter, "", conditionsValues);
				} else {
					hql = buildHqlDynamicCondition(hql, filter, "and", conditionsValues);
				}
			}

		}

		return hql;
	}

	private String buildHqlDynamicCondition(String hql, PropertyFilter filter, String relationChar, Map<String, Object> conditionsValues)
  {
    String propertyName = filter.getPropertyName();
    Object propertyValue = filter.getMatchValue();
    conditionsValues.put(propertyName, propertyValue.toString().trim());
    PropertyFilter.MatchType matchType = filter.getMatchType();
    switch (1.$SwitchMap$avicit$platform6$core$dao$PropertyFilter$MatchType[matchType.ordinal()])
    {
    case 1:
      hql = hql + " " + relationChar + " " + propertyName + "=:" + propertyName;
      break;
    case 2:
      hql = hql + " " + relationChar + " " + propertyName + " like :" + propertyName;
      conditionsValues.put(propertyName, "%" + propertyValue.toString().trim() + "%");
      break;
    case 3:
      hql = hql + " " + relationChar + " " + propertyName + "<=:" + propertyName;
      break;
    case 4:
      hql = hql + " " + relationChar + " " + propertyName + "<:" + propertyName;
      break;
    case 5:
      hql = hql + " " + relationChar + " " + propertyName + ">=:" + propertyName;
      break;
    case 6:
      hql = hql + " " + relationChar + " " + propertyName + ">:" + propertyName;
      break;
    case 7:
      hql = hql + " " + relationChar + " " + propertyName + " between " + ((String)propertyValue);
    }

    return hql;
  }

	public <T> Paging<T> findPageBySQL(Paging<T> page, String pSql, Map<String, Object> parameter) {
		Assert.notNull(page, "page不能为空");
		String querSql = "select * from ( " + pSql + " ) where 1=1 ";

		List filters = PropertyFilter.buildFromParameterMap(parameter);
		HashMap ValueMap = new HashMap();
		if (null != parameter) {
			querSql = buildDynamicSql(filters, querSql, ValueMap);
		}
		String countSql = "select count(1) from ( " + querSql + " ) where 1=1 ";
		if (page.getOrderBy() != null) {
			querSql = querSql + " order by " + page.getOrderBy();
			if (null != page.getOrder())
				querSql = querSql + " " + page.getOrder();
		}
		SQLQuery q = getSession().createSQLQuery(querSql);
		q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		if ((ValueMap != null) && (ValueMap.size() > 0)) {
			for (String key : ValueMap.keySet()) {
				q.setParameter(key, ValueMap.get(key));
			}
		}

		if (page.isAutoCount()) {
			long totalCount = countSqlResult(countSql, ValueMap);
			page.setTotalCount(totalCount);
		}
		setPageParameterToQuery(q, page);
		List result = q.list();
		page.setResult(result);
		return page;
	}

	public String buildDynamicSql(List<PropertyFilter> filters, String pSql, Map<String, Object> conditionsValues) {
		if (null != filters) {
			for (PropertyFilter filter : filters) {
				Object propertyValue = filter.getMatchValue();
				if (null == propertyValue)
					continue;
				if (propertyValue.toString().trim().length() == 0) {
					continue;
				}
				pSql = buildSqlDynamicCondition(pSql, filter, "and", conditionsValues);
			}
		}

		return pSql;
	}

	public long countSqlResult(String pSql, HashMap<String, Object> params) {
		try {
			SQLQuery query = getSession().createSQLQuery(pSql);
			if (null != params) {
				for (String key : params.keySet()) {
					query.setParameter(key, params.get(key));
				}
			}
			BigDecimal bcount = (BigDecimal) query.uniqueResult();
			long totalCount = bcount.longValue();
			return totalCount;
		} catch (Exception e) {
			throw new RuntimeException("hql can't be auto count, hql is:" + pSql, e);
		}
	}

	private String buildSqlDynamicCondition(String hql, PropertyFilter filter, String relationChar, Map<String, Object> conditionsValues) {
    String propertyName = filter.getPropertyName();
    Object propertyValue = filter.getMatchValue();
    conditionsValues.put(propertyName, propertyValue.toString().trim());
    PropertyFilter.MatchType matchType = filter.getMatchType();
    switch (1.$SwitchMap$avicit$platform6$core$dao$PropertyFilter$MatchType[matchType.ordinal()])
    {
    case 1:
      hql = hql + " " + relationChar + " " + propertyName + "=:" + propertyName;
      break;
    case 2:
      hql = hql + " " + relationChar + " " + propertyName + " like :" + propertyName;
      conditionsValues.put(propertyName, "%" + propertyValue.toString().trim() + "%");
      break;
    case 3:
      hql = hql + " " + relationChar + " " + propertyName + "<=:" + propertyName;
      break;
    case 4:
      hql = hql + " " + relationChar + " " + propertyName + "<:" + propertyName;
      break;
    case 5:
      hql = hql + " " + relationChar + " " + propertyName + ">=:" + propertyName;
      break;
    case 6:
      hql = hql + " " + relationChar + " " + propertyName + ">:" + propertyName;
      break;
    case 7:
      hql = hql + " " + relationChar + " " + propertyName + " between " + ((String)propertyValue);
    }

    return hql; }

	public List<Map> findListMapBySQL(String sql) {
		SQLQuery sqlquery = getSession().createSQLQuery(sql);
		sqlquery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return sqlquery.list();
	}
}