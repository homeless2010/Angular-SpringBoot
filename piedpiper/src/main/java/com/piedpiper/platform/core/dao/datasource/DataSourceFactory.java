 package com.piedpiper.platform.core.dao.datasource;
 
 import com.piedpiper.platform.core.dao.jdbc.JdbcDao;
 import java.util.HashMap;
 import java.util.Map;
 import javax.sql.DataSource;
 import org.springframework.beans.BeansException;
 import org.springframework.beans.factory.DisposableBean;
 import org.springframework.beans.factory.InitializingBean;
 import org.springframework.context.ApplicationContext;
 import org.springframework.context.ApplicationContextAware;
 import org.springframework.util.StringUtils;
 
 
 
 
 
 
 
 @Deprecated
 public class DataSourceFactory
   implements InitializingBean, DisposableBean, ApplicationContextAware
 {
   private static String defaultDataSourceName;
   private ApplicationContext applicationContext;
   private static Map<String, JdbcDao> jdbcDaoMap = new HashMap();
   private static Map<String, DataSource> dataSources = new HashMap();
   private static JdbcDao defaultJdbcDao;
   
   public static JdbcDao getDefaultJdbcDao() {
     return defaultJdbcDao;
   }
   
   public static String getDefaultDataSourceName() {
     return defaultDataSourceName;
   }
   
   public static DataSource getDefaultDataSource() {
     return getDataSource(getDefaultDataSourceName());
   }
   
   public Map<String, DataSource> getDataSources() {
     return dataSources;
   }
   
   public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
   {
     this.applicationContext = applicationContext;
   }
   
 
   public void afterPropertiesSet()
     throws Exception
   {
     Map<String, DataSourceRegister> dataSourceRegisters = this.applicationContext.getBeansOfType(DataSourceRegister.class);
     for (DataSourceRegister dataSourceRegister : dataSourceRegisters.values()) {
       dataSources.put(dataSourceRegister.getName(), dataSourceRegister.getDataSource());
       JdbcDao jdbcDao = new JdbcDao() {};
       jdbcDao.setDataSource(dataSourceRegister.getDataSource());
       jdbcDaoMap.put(dataSourceRegister.getName(), jdbcDao);
       if (dataSourceRegister.isAsDefault()) {
         defaultDataSourceName = dataSourceRegister.getName();
         defaultJdbcDao = jdbcDao;
       }
     }
   }
   
 
 
 
 
   public static DataSource getDataSource(String dataSourceName)
   {
     if (StringUtils.hasText(dataSourceName)) {
       return (DataSource)dataSources.get(dataSourceName);
     }
     return (DataSource)dataSources.get(defaultDataSourceName);
   }
   
 
 
 
 
 
   public static JdbcDao getJdbcDao(String dataSourceName)
   {
     if (StringUtils.hasText(dataSourceName)) {
       return (JdbcDao)jdbcDaoMap.get(dataSourceName);
     }
     return (JdbcDao)jdbcDaoMap.get(defaultDataSourceName);
   }
   
   public void destroy()
     throws Exception
   {}
 }


