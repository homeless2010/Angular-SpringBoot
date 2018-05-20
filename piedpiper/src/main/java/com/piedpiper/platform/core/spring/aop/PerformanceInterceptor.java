 package com.piedpiper.platform.core.spring.aop;
 
 import java.lang.reflect.Method;
 import org.aopalliance.intercept.MethodInvocation;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.aop.ThrowsAdvice;
 import org.springframework.aop.interceptor.PerformanceMonitorInterceptor;
 
 
 
 
 
 public class PerformanceInterceptor
   extends PerformanceMonitorInterceptor
   implements ThrowsAdvice
 {
   private static final long serialVersionUID = 7302350984439928367L;
   static Logger logger = LoggerFactory.getLogger(PerformanceInterceptor.class);
   
 
   private Long maxAllowedTimeMillis = Long.valueOf(1000L);
   
 
 
 
 
 
 
   public Object invoke(MethodInvocation invocation)
     throws Throwable
   {
     String name = createInvocationTraceName(invocation);
     long beginTime = System.currentTimeMillis();
     try { long runTime;
       return invocation.proceed();
     } finally {
       long runTime = System.currentTimeMillis() - beginTime;
       
       if (runTime > this.maxAllowedTimeMillis.longValue()) {
         logger.error("方法执行时间是 " + runTime + " ms，存在执行效率问题，请优化:" + name);
         persistencePerformance(runTime, name);
       }
     }
   }
   
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
   public void afterThrowing(Method method, Object[] args, Object obj, RuntimeException throwable) {}
   
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
   private void persistencePerformance(long runTime, String name) {}
   
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
   public Long getMaxAllowedTimeMillis()
   {
     return this.maxAllowedTimeMillis;
   }
   
 
 
 
 
 
   public void setMaxAllowedTimeMillis(Long maxAllowedTimeMillis)
   {
     this.maxAllowedTimeMillis = maxAllowedTimeMillis;
   }
 }


