 package com.piedpiper.platform.core.dao.datasource;
 
 import com.piedpiper.platform.core.dao.datasource.annotation.DataSource;
 import java.lang.reflect.Method;
 import java.util.List;
 import java.util.Random;
 import org.aspectj.lang.JoinPoint;
 import org.aspectj.lang.Signature;
 import org.aspectj.lang.reflect.MethodSignature;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 
 public class DataSourceAspect
 {
   private List<String> masterDataSourceList;
   private List<String> slaveDataSourceList;
   
   public void setMasterDataSourceList(List<String> masterDataSourceList)
   {
     this.masterDataSourceList = masterDataSourceList;
   }
   
   public void setSlaveDataSourceList(List<String> slaveDataSourceList) {
     this.slaveDataSourceList = slaveDataSourceList;
   }
   
   private static final Logger logger = LoggerFactory.getLogger(DataSourceAspect.class);
   
   public void before(JoinPoint point) {
     String method = point.getSignature().getName();
     Class<?>[] parameterTypes = ((MethodSignature)point.getSignature()).getMethod().getParameterTypes();
     
     String classType = point.getTarget().getClass().getName();
     try {
       Class<?> clazz = Class.forName(classType);
       Method m = clazz.getMethod(method, parameterTypes);
       if ((m != null) && (m.isAnnotationPresent(DataSource.class))) {
         DataSource data = (DataSource)m.getAnnotation(DataSource.class);
         if (data.value().equals("master")) {
           int rm = new Random().nextInt(this.masterDataSourceList.size());
           MultiDataSouceThreadLocal.setCurrentDataSource((String)this.masterDataSourceList.get(rm));
           logger.info("current datasource is : masterDataSourceList  " + rm);
         }
         if (data.value().equals("slave")) {
           int rm = new Random().nextInt(this.slaveDataSourceList.size());
           MultiDataSouceThreadLocal.setCurrentDataSource((String)this.slaveDataSourceList.get(rm));
           logger.info("current datasource is : slaveDataSourceList  " + rm);
         }
       }
       else {
         MultiDataSouceThreadLocal.setCurrentDataSource("default");
         logger.info("current datasource is : default");
       }
     }
     catch (Exception e1) {
       e1.printStackTrace();
     }
   }
 }


