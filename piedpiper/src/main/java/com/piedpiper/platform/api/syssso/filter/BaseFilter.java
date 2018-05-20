 package com.piedpiper.platform.api.syssso.filter;
 
 import java.io.IOException;
 import javax.servlet.Filter;
 import javax.servlet.FilterChain;
 import javax.servlet.FilterConfig;
 import javax.servlet.ServletException;
 import javax.servlet.ServletRequest;
 import javax.servlet.ServletResponse;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.apache.commons.logging.Log;
 
 
 
 
 
 
 
 
 public abstract class BaseFilter
   implements Filter
 {
   private static final String _DEPTHER = "DEPTHER";
   private FilterConfig _filterConfig;
   
   public void init(FilterConfig filterConfig)
   {
     this._filterConfig = filterConfig;
   }
   
 
 
   public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
     throws IOException, ServletException
   {
     Log log = getLog();
     
     if (log.isDebugEnabled()) {
       if (isFilterEnabled()) {
         log.debug(this._filterClass + " is enabled");
       } else {
         log.debug(this._filterClass + " is disabled");
       }
     }
     
     HttpServletRequest request = (HttpServletRequest)servletRequest;
     HttpServletResponse response = (HttpServletResponse)servletResponse;
     
     if (isFilterEnabled()) {
       processFilter(request, response, filterChain);
     } else {
       processFilter(this._filterClass, request, response, filterChain);
     }
   }
   
   public FilterConfig getFilterConfig() {
     return this._filterConfig;
   }
   
 
 
   public void destroy() {}
   
 
 
   protected abstract Log getLog();
   
 
   protected boolean isFilterEnabled()
   {
     return this._filterEnabled;
   }
   
 
 
 
 
 
 
 
 
 
 
   protected abstract void processFilter(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, FilterChain paramFilterChain)
     throws IOException, ServletException;
   
 
 
 
 
 
 
 
 
 
   protected void processFilter(Class<?> filterClass, HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
     throws IOException, ServletException
   {
     long startTime = 0L;
     
     String threadName = null;
     String depther = null;
     String path = null;
     
     Log log = getLog();
     
     if (log.isDebugEnabled()) {
       startTime = System.currentTimeMillis();
       
       threadName = Thread.currentThread().getName();
       
       depther = (String)request.getAttribute("DEPTHER");
       
       if (depther == null) {
         depther = "";
       } else {
         depther = depther + "=";
       }
       
       request.setAttribute("DEPTHER", depther);
       
       path = request.getRequestURI();
       
       log.debug("[" + threadName + "]" + depther + "> " + filterClass.getName() + " " + path);
     }
     
 
 
     filterChain.doFilter(request, response);
     
     if (log.isDebugEnabled()) {
       long endTime = System.currentTimeMillis();
       
       depther = (String)request.getAttribute("DEPTHER");
       
       log.debug("[" + threadName + "]" + depther + "< " + filterClass.getName() + " " + path + " " + (endTime - startTime) + " ms");
       
 
 
 
       if (depther.length() > 0) {
         depther = depther.substring(1);
       }
       
       request.setAttribute("DEPTHER", depther);
     }
   }
   
 
 
 
   private Class<?> _filterClass = getClass();
   private boolean _filterEnabled = true;
 }


