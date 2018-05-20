 package com.piedpiper.platform.core.spring;
 
 import java.io.PrintStream;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.core.NamedThreadLocal;
 import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
 
 
 
 
 
 
 public class WatchHandlerInterceptor
   extends HandlerInterceptorAdapter
 {
   private static Logger logger = LoggerFactory.getLogger(WatchHandlerInterceptor.class);
   
   private int warnSlowTime;
   private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal("StopWatch-StartTime");
   
   public int getWarnSlowTime() {
     return this.warnSlowTime;
   }
   
   public void setWarnSlowTime(int warnSlowTime) {
     this.warnSlowTime = warnSlowTime;
   }
   
   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
     long beginTime = System.currentTimeMillis();
     this.startTimeThreadLocal.set(Long.valueOf(beginTime));
     System.out.println("Current Request Context Path : [" + request.getRequestURI() + "]");
     
     return true;
   }
   
   public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
     long endTime = System.currentTimeMillis();
     long beginTime = ((Long)this.startTimeThreadLocal.get()).longValue();
     long consumeTime = endTime - beginTime;
     if (consumeTime > this.warnSlowTime)
     {
       logger.warn(String.format("%s consume %d millis", new Object[] { request.getRequestURI(), Long.valueOf(consumeTime) }));
     }
   }
 }


