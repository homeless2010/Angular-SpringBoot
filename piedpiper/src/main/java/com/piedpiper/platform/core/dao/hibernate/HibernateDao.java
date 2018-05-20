 package com.piedpiper.platform.core.dao.hibernate;
 
 import com.piedpiper.platform.commons.utils.reflection.ReflectionUtils;
 import com.piedpiper.platform.core.dao.Paging;
 import com.piedpiper.platform.core.dao.PropertyFilter;
 import com.piedpiper.platform.core.dao.PropertyFilter.MatchType;
 import com.piedpiper.platform.core.exception.DaoException;
 import java.io.Serializable;
 import java.sql.CallableStatement;
 import java.sql.Connection;
 import java.sql.SQLException;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Map;
 import javax.sql.DataSource;
 import org.apache.commons.lang3.StringUtils;
 import org.hibernate.Criteria;
 import org.hibernate.Query;
 import org.hibernate.SessionFactory;
 import org.hibernate.criterion.CriteriaSpecification;
 import org.hibernate.criterion.Criterion;
 import org.hibernate.criterion.Disjunction;
 import org.hibernate.criterion.MatchMode;
 import org.hibernate.criterion.Order;
 import org.hibernate.criterion.Projection;
 import org.hibernate.criterion.Projections;
 import org.hibernate.criterion.Restrictions;
 import org.hibernate.impl.CriteriaImpl;
 import org.hibernate.impl.CriteriaImpl.OrderEntry;
 import org.hibernate.transform.ResultTransformer;
 import org.slf4j.Logger;
 import org.springframework.orm.hibernate3.SessionFactoryUtils;
 import org.springframework.util.Assert;
 
 
 
 
 
 
 
 
 
 
 
 
 public class HibernateDao<T, PK extends Serializable>
   extends SimpleHibernateDao<T, PK>
 {
   private final long MAX_EXECUTE_TIMEOUT = 5000L;
   
 
 
 
 
 
   public HibernateDao() {}
   
 
 
 
 
   public HibernateDao(SessionFactory sessionFactory, Class<T> entityClass)
   {
     super(sessionFactory, entityClass);
   }
   
 
 
 
 
   public Paging<T> getAll(Paging<T> page)
   {
     return findPage(page, new Criterion[0]);
   }
   
 
 
 
 
 
 
 
 
 
 
 
 
   public Paging<T> findPage(Paging<T> page, String hql, Object... values)
   {
     Assert.notNull(page, "page不能为空");
     String OriginalHql = hql;
     Query q = createQuery(hql, values);
     
     if (page.isAutoCount()) {
       long totalCount = countHqlResult(OriginalHql, values);
       page.setTotalCount(totalCount);
     }
     
     setPageParameterToQuery(q, page);
     
     List<T> result = q.list();
     page.setResult(result);
     return page;
   }
   
 
 
 
 
 
 
 
 
 
 
 
 
   public Paging<T> findPage(Paging<T> page, String hql, Map<String, ?> values)
   {
     Assert.notNull(page, "page不能为空");
     
     Query q = createQuery(hql, values);
     
     if (page.isAutoCount()) {
       long totalCount = countHqlResult(hql, values);
       page.setTotalCount(totalCount);
     }
     
     setPageParameterToQuery(q, page);
     page.setResult(q.list());
     return page;
   }
   
 
 
 
 
 
 
 
 
 
 
   public Paging<T> findPage(Paging<T> page, Criterion... criterions)
   {
     Assert.notNull(page, "page不能为空");
     
     Criteria c = createCriteria(criterions);
     
     if (page.isAutoCount()) {
       long totalCount = countCriteriaResult(c);
       page.setTotalCount(totalCount);
     }
     setPageParameterToCriteria(c, page);
     
     List<T> result = c.list();
     page.setResult(result);
     return page;
   }
   
 
 
 
 
 
 
 
 
 
 
   public Paging<T> findPage(Paging<T> page, Criteria criteria)
   {
     Assert.notNull(page, "page不能为空");
     if (page.isAutoCount()) {
       long totalCount = countCriteriaResult(criteria);
       page.setTotalCount(totalCount);
     }
     setPageParameterToCriteria(criteria, page);
     page.setResult(criteria.list());
     return page;
   }
   
 
 
 
   protected Query setPageParameterToQuery(Query q, Paging<T> page)
   {
     Assert.isTrue(page.getPageSize() > 0, "Paging Size must larger than zero");
     
 
     q.setFirstResult(page.getFirst() - 1);
     q.setMaxResults(page.getPageSize());
     return q;
   }
   
 
 
 
   protected Criteria setPageParameterToCriteria(Criteria c, Paging<T> page)
   {
     Assert.isTrue(page.getPageSize() > 0, "Paging Size must larger than zero");
     
 
     c.setFirstResult(page.getFirst() - 1);
     c.setMaxResults(page.getPageSize());
     
     if (page.isOrderBySetted()) {
       String[] orderByArray = StringUtils.split(page.getOrderBy(), ',');
       String[] orderArray = StringUtils.split(page.getOrder(), ',');
       
       Assert.isTrue(orderByArray.length == orderArray.length, "分页多重排序参数中,排序字段与排序方向的个数不相等");
       
       for (int i = 0; i < orderByArray.length; i++) {
         if ("asc".equals(orderArray[i])) {
           c.addOrder(Order.asc(orderByArray[i]));
         } else {
           c.addOrder(Order.desc(orderByArray[i]));
         }
       }
     }
     return c;
   }
   
 
 
 
 
   protected long countHqlResult(String hql, Object... values)
   {
     String countHql = prepareCountHql(hql);
     try
     {
       Long count = (Long)findUnique(countHql, values);
       return count.longValue();
     } catch (Exception e) {
       throw new RuntimeException("hql can't be auto count, hql is:" + countHql, e);
     }
   }
   
 
 
 
 
   protected long countHqlResult(String hql, Map<String, ?> values)
   {
     String countHql = prepareCountHql(hql);
     try
     {
       Long count = (Long)findUnique(countHql, values);
       return count.longValue();
     } catch (Exception e) {
       throw new RuntimeException("hql can't be auto count, hql is:" + countHql, e);
     }
   }
   
   private String prepareCountHql(String orgHql) {
     String fromHql = orgHql;
     
     fromHql = "from " + StringUtils.substringAfter(fromHql, "from");
     fromHql = StringUtils.substringBefore(fromHql, "order by");
     
     String countHql = "select count(*) " + fromHql;
     return countHql;
   }
   
 
 
 
   protected long countCriteriaResult(Criteria c)
   {
     CriteriaImpl impl = (CriteriaImpl)c;
     
 
     Projection projection = impl.getProjection();
     ResultTransformer transformer = impl.getResultTransformer();
     
     List<CriteriaImpl.OrderEntry> orderEntries = null;
     try {
       orderEntries = (List)ReflectionUtils.getFieldValue(impl, "orderEntries");
       ReflectionUtils.setFieldValue(impl, "orderEntries", new ArrayList());
     } catch (Exception e) {
       this.logger.error("不可能抛出的异常:{}", e.getMessage());
     }
     
 
     Long totalCountObject = (Long)c.setProjection(Projections.rowCount()).uniqueResult();
     long totalCount = totalCountObject != null ? totalCountObject.longValue() : 0L;
     
 
     c.setProjection(projection);
     
     if (projection == null) {
       c.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
     }
     if (transformer != null) {
       c.setResultTransformer(transformer);
     }
     try {
       ReflectionUtils.setFieldValue(impl, "orderEntries", orderEntries);
     } catch (Exception e) {
       this.logger.error("不可能抛出的异常:{}", e.getMessage());
     }
     
     return totalCount;
   }
   
 
 
 
 
 
 
 
   public List<T> findBy(String propertyName, Object value, PropertyFilter.MatchType matchType)
   {
     Criterion criterion = buildCriterion(propertyName, value, matchType);
     return find(new Criterion[] { criterion });
   }
   
 
 
   public List<T> find(List<PropertyFilter> filters)
   {
     Criterion[] criterions = buildCriterionByPropertyFilter(filters);
     return find(criterions);
   }
   
 
 
   public Paging<T> findPage(Paging<T> page, List<PropertyFilter> filters)
   {
     if (filters != null) {
       Criterion[] criterions = buildCriterionByPropertyFilter(filters);
       return findPage(page, criterions);
     }
     return findPage(page, new Criterion[0]);
   }
   
 
 
 
   protected Criterion buildCriterion(String propertyName, Object propertyValue, PropertyFilter.MatchType matchType)
   {
     Assert.hasText(propertyName, "propertyName不能为空");
     Criterion criterion = null;
     
     switch (matchType) {
     case EQ: 
       criterion = Restrictions.eq(propertyName, propertyValue);
       break;
     case LIKE: 
       criterion = Restrictions.like(propertyName, (String)propertyValue, MatchMode.ANYWHERE);
       break;
     
     case LE: 
       criterion = Restrictions.le(propertyName, propertyValue);
       break;
     case LT: 
       criterion = Restrictions.lt(propertyName, propertyValue);
       break;
     case GE: 
       criterion = Restrictions.ge(propertyName, propertyValue);
       break;
     case GT: 
       criterion = Restrictions.gt(propertyName, propertyValue);
       break;
     case ISNULL: 
       criterion = Restrictions.isNull(propertyName);
       break;
     case ISNOTNULL: 
       criterion = Restrictions.isNotNull(propertyName);
     }
     
     
     return criterion;
   }
   
 
 
 
   protected Criterion[] buildCriterionByPropertyFilter(List<PropertyFilter> filters)
   {
     List<Criterion> criterionList = new ArrayList();
     if (filters != null) {
       for (PropertyFilter filter : filters) {
         if (!filter.hasMultiProperties()) {
           Criterion criterion = buildCriterion(filter.getPropertyName(), filter.getMatchValue(), filter.getMatchType());
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
     return (Criterion[])criterionList.toArray(new Criterion[criterionList.size()]);
   }
   
 
 
 
 
 
 
 
 
 
 
 
 
   public String buildDynamicHql(List<PropertyFilter> filters, String hql, Map<String, Object> conditionsValues)
   {
     if (null != filters) {
       for (PropertyFilter filter : filters) {
         Object propertyValue = filter.getMatchValue();
         if ((null != propertyValue) && (propertyValue.toString().trim().length() != 0))
         {
 
           if (!hql.contains("where")) {
             hql = hql + " where ";
             hql = buildHqlDynamicCondition(hql, filter, "", conditionsValues);
           }
           else if (hql.trim().endsWith("and")) {
             hql = buildHqlDynamicCondition(hql, filter, "", conditionsValues);
           } else {
             hql = buildHqlDynamicCondition(hql, filter, "and", conditionsValues);
           }
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
     switch (matchType) {
     case EQ: 
       hql = hql + " " + relationChar + " " + propertyName + "=:" + propertyName;
       break;
     case LIKE: 
       hql = hql + " " + relationChar + " " + propertyName + " like :" + propertyName;
       conditionsValues.put(propertyName, "%" + propertyValue.toString().trim() + "%");
       break;
     case LE: 
       hql = hql + " " + relationChar + " " + propertyName + "<=:" + propertyName;
       break;
     case LT: 
       hql = hql + " " + relationChar + " " + propertyName + "<:" + propertyName;
       break;
     case GE: 
       hql = hql + " " + relationChar + " " + propertyName + ">=:" + propertyName;
       break;
     case GT: 
       hql = hql + " " + relationChar + " " + propertyName + ">:" + propertyName;
       break;
     }
     
     
     return hql;
   }
   
 
 
 
 
 
 
 
 
 
 
 
 
   public String callProcedure(String procedure, List parameter)
     throws DaoException, SQLException
   {
     long beginTime = System.currentTimeMillis();
     
     String vCall = "{call " + procedure + "(";
     for (int i = 0; i < parameter.size(); i++) {
       vCall = vCall + "'" + parameter.get(i).toString() + "',";
     }
     vCall = vCall + "?,?)}";
     Connection conn = getConnection();
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
 
 
     }
     catch (SQLException e)
     {
 
       if (4068 == e.getErrorCode()) {
         this.logger.error("检测到包 " + procedure + " 的状态发生变化", e);
         throw new DaoException("检测到数据库发生 ORA-04068 错误，该问题一般不会引起数据丢失，请您重新执行您的操作。");
       }
       
       this.logger.error("调用存储过程时发生错误", e);
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
     if (executeTime > 5000L) {
       this.logger.error("执行 " + procedure + " 代码的时间是 " + executeTime + " 毫秒，执行速度较慢需要优化。");
     }
     return cstmtMessage;
   }
   
 
 
 
 
   public Connection getConnection()
   {
     Connection connection = null;
     try {
       connection = SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();
     } catch (SQLException e) {
       e.printStackTrace();
     }
     return connection;
   }
 }


