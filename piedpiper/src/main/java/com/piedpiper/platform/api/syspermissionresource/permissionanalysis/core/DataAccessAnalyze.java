 package com.piedpiper.platform.api.syspermissionresource.permissionanalysis.core;
 
 import com.piedpiper.platform.api.syspermissionresource.dto.SysPermissionResource;
 import com.piedpiper.platform.api.syspermissionresource.permissionanalysis.annotation.CustomBean;
 import com.piedpiper.platform.api.syspermissionresource.permissionanalysis.annotation.DataPermission;
 import com.piedpiper.platform.api.syspermissionresource.permissionanalysis.annotation.SqlParam;
 import com.piedpiper.platform.api.syspermissionresource.permissionanalysis.core.support.AbstractDataCondition;
 import com.piedpiper.platform.api.syspermissionresource.permissionanalysis.core.support.CompositeLoaderI;
 import com.piedpiper.platform.api.syspermissionresource.permissionanalysis.core.support.DataCondition;
 import com.piedpiper.platform.core.spring.SpringFactory;
 import java.lang.annotation.Annotation;
 import java.lang.reflect.Method;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import org.apache.commons.lang.StringUtils;
 import org.aspectj.lang.ProceedingJoinPoint;
 import org.aspectj.lang.Signature;
 import org.aspectj.lang.annotation.Around;
 import org.aspectj.lang.annotation.Aspect;
 import org.aspectj.lang.annotation.Pointcut;
 import org.aspectj.lang.reflect.MethodSignature;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.beans.factory.annotation.Qualifier;
 import org.springframework.stereotype.Component;
 
 
 
 
 @Aspect
 @Component
 public class DataAccessAnalyze
 {
   @Pointcut("@annotation(com.piedpiper.platform.api.syspermissionresource.permissionanalysis.annotation.DataPermission)")
   private void pointcut() {}
   
   private Logger logger = LoggerFactory.getLogger(DataAccessAnalyze.class);
   public static final String SQL_EXISITS = "EXISTS({sql})";
   @Autowired(required=false)
   private List<AccessAnalyze> accessAnalyzeList;
   @Autowired(required=true)
   @Qualifier("avicit.globleDataAccess")
   private AbstractDataCondition dataAccess;
   
   private static final ThreadLocal<DataCondition> threadLocal = new ThreadLocal();
   
 
 
   private CompositeLoaderI compositeLoader = (CompositeLoaderI)SpringFactory.getBean("CompositeLoader");
   
   @Around("pointcut()")
   public Object doDataPermission(ProceedingJoinPoint pjp) throws Throwable
   {
     this.logger.debug("开始权限解析,对象为：" + pjp.getTarget().getClass().getName() + "方法为：" + pjp.getSignature().getName());
     
 
 
 
 
 
 
     String condition = "";
     List<String> conditions = new ArrayList();
     
     String methodName = pjp.getSignature().getName();
     
     Object target = pjp.getTarget();
     
     MethodSignature msig = (MethodSignature)pjp.getSignature();
     
     Class<?>[] paramTypes = msig.getParameterTypes();
     Method method = target.getClass().getDeclaredMethod(methodName, paramTypes);
     
     DataPermission dataPerm = (DataPermission)method.getAnnotation(DataPermission.class);
     
     SqlParam dataCondts = null;
     
     Annotation[][] paramTypesAnots = method.getParameterAnnotations();
     
     int index = -1;
     for (int i = 0; (i < paramTypesAnots.length) && (dataCondts == null); i++) {
       Annotation[] annotation = paramTypesAnots[i];
       for (int j = 0; j < annotation.length; j++) {
         if ((annotation[j] instanceof SqlParam)) {
           dataCondts = (SqlParam)annotation[j];
           index = i;
           break;
         }
       }
     }
     if (dataCondts == null) {
       this.logger.info("数据权限过滤方法【" + methodName + "】参数没有注解");
       throw new RuntimeException("数据权限过滤方法参数没有注解");
     }
     
     Object arg = pjp.getArgs()[index];
     if (arg == null) {
       arg = new HashMap();
       pjp.getArgs()[index] = arg;
     }
     
     if (!(arg instanceof Map)) {
       this.logger.info("数据权限过滤方法参数【" + arg.getClass().getName() + "】不为Map对象及其子对象");
       throw new RuntimeException("数据权限过滤方法注解形参不为Map对象及其子对象");
     }
     
 
     DataPermission rightDataPerm = checkAnnotation(dataPerm);
     
 
     SysPermissionResource sysPermRes = this.compositeLoader.getSysPermissionResourceByMetaData(rightDataPerm.ModelName());
     
 
     if ((this.accessAnalyzeList != null) && (sysPermRes != null)) {
       for (AccessAnalyze accessAnalyze : this.accessAnalyzeList) {
         String str = accessAnalyze.generateSqlCondition(sysPermRes, rightDataPerm);
         if (!"".equals(str)) {
           conditions.add(str);
         }
       }
     }
     
     if ((sysPermRes != null) && (sysPermRes.getSql() != null) && (!"".equals(sysPermRes.getSql()))) {
       this.logger.debug("权限前置SQL=" + sysPermRes.getSql());
       conditions.add(StringUtils.replace("EXISTS({sql})", "{sql}", sysPermRes.getSql()));
     }
     
 
     DataCondition dataCondition = (DataCondition)threadLocal.get();
     if (dataCondition != null) {
       this.logger.debug("调用用户自定义接口实现Sql");
       conditions.add(dataCondition.generateSqlCondition(sysPermRes, rightDataPerm));
     }
     
     if (rightDataPerm.BeanInfo().IsDefault())
     {
       conditions.add(this.dataAccess.generateSqlCondition(sysPermRes, rightDataPerm));
     }
     
     condition = StringUtils.join(conditions, " AND ");
     this.logger.debug("权限解析完成，解析的完整Sql为：" + condition);
     ((Map)arg).put(dataCondts.Key(), condition);
     
     threadLocal.remove();
     return pjp.proceed(pjp.getArgs());
   }
   
 
 
 
 
 
 
   private DataPermission checkAnnotation(DataPermission dataPerm)
   {
     String modelName = dataPerm.ModelName();
     if ("".equals(modelName)) {
       this.logger.info("数据权限过滤ModelName不能为空");
       throw new RuntimeException("数据权限过滤model不能为空");
     }
     
     String tableName = dataPerm.TableName();
     if ("".equals(tableName)) {
       this.logger.info("数据权限过滤表的别名不能为空");
       throw new RuntimeException("数据权限过滤表的别名不能为空");
     }
     CustomBean custonBean = dataPerm.BeanInfo();
     String beanName = custonBean.BeanName();
     if (!"avicit".equals(beanName)) {
       Object bean = SpringFactory.getBean(beanName);
       if (bean == null) {
         this.logger.info("不存在名为：" + beanName + "的bean");
         throw new RuntimeException("不存在名为：" + beanName + "的bean");
       }
       if (!(bean instanceof DataCondition)) {
         this.logger.info("名为：" + beanName + "的bean没有实现接口DataCondition");
         throw new RuntimeException("名为：" + beanName + "的bean没有实现接口DataCondition");
       }
       threadLocal.set((DataCondition)bean);
     }
     return dataPerm;
   }
 }


