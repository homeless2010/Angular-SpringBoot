 package com.piedpiper.platform.api.sysshirolog.filter;
 
 import com.piedpiper.platform.api.sysshirolog.context.ContextHolder;
 import com.piedpiper.platform.api.sysshirolog.context.FrameworkContext;
 import com.piedpiper.platform.api.sysshirolog.utils.FrameworkContextRepository;
 import com.piedpiper.platform.api.sysshirolog.utils.HttpRequestResponseHolder;
 import com.piedpiper.platform.api.sysshirolog.utils.HttpSessionFrameworkContextRepository;
 import com.piedpiper.platform.core.shiroSecurity.contextThread.ContextCommonHolder;
 import java.io.IOException;
 import javax.servlet.Filter;
 import javax.servlet.FilterChain;
 import javax.servlet.FilterConfig;
 import javax.servlet.ServletContext;
 import javax.servlet.ServletException;
 import javax.servlet.ServletRequest;
 import javax.servlet.ServletResponse;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.util.StringUtils;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class ShiroContextPersistenceFilter
   implements Filter
 {
   public void init(FilterConfig config)
     throws ServletException
   {
     if (this.logger.isDebugEnabled())
     {
       this.logger.debug("shiro 安全过滤器  start....." + ShiroContextPersistenceFilter.class.getSimpleName());
     }
     
 
     servletContext = config.getServletContext();
     
 
     String repo = config.getInitParameter("frameworkContextRepository");
     
 
     if (StringUtils.hasText(repo))
     {
       try
       {
         this.frameworkContextRepository = ((FrameworkContextRepository)Class.forName(repo).newInstance());
         
         this.frameworkContextRepository.setServletContext(config.getServletContext());
       }
       catch (Exception e) {
         this.logger.error(e.getMessage());
         throw new ServletException("名为" + repo + "的FrameworkContextRepository实现类不合法！");
       }
     }
   }
   
 
 
 
 
 
 
 
 
 
 
 
 
   public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
     throws IOException, ServletException
   {
     HttpServletRequest request = (HttpServletRequest)req;
     HttpServletResponse response = (HttpServletResponse)res;
     
     HttpRequestResponseHolder holder = new HttpRequestResponseHolder(request, response);
     FrameworkContext frameworkContext = this.frameworkContextRepository.loadFrameworkContext(holder);
     ContextHolder.setHttpRequestResponseHolder(holder);
     ContextCommonHolder.setHttpRequestResponseHolder(request, response);
     try
     {
       ContextHolder.setContext(frameworkContext);
       chain.doFilter(req, res);
     }
     catch (Exception ex) {
       if (!"org.apache.catalina.connector.ClientAbortException".equals(ex.getClass().getName())) {
         this.logger.error(ex.getMessage());
         throw new ServletException(ex);
       }
     } finally {
       this.frameworkContextRepository.saveFrameworkContext(ContextHolder.getContext(), request, response);
       ContextHolder.clearContext();
     }
   }
   
 
 
   public void destroy() {}
   
 
   public static final ServletContext getServletContext()
   {
     return servletContext;
   }
   
   private static ServletContext servletContext = null;
   
   private Logger logger = LoggerFactory.getLogger(getClass());
   
 
 
 
   private FrameworkContextRepository frameworkContextRepository = new HttpSessionFrameworkContextRepository();
 }


