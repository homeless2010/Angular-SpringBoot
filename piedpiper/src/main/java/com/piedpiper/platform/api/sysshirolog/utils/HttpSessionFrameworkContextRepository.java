 package com.piedpiper.platform.api.sysshirolog.utils;
 
 import com.piedpiper.platform.api.sysshirolog.context.FrameworkContext;
 import javax.servlet.ServletContext;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import javax.servlet.http.HttpSession;
 import org.springframework.web.context.WebApplicationContext;
 import org.springframework.web.context.support.WebApplicationContextUtils;
 
 
 
 
 
 
 
 
 
 public class HttpSessionFrameworkContextRepository
   implements FrameworkContextRepository
 {
   public static final String FRAMEWORK_CONTEXT_KEY = "__FRAMEWORK_CONTEXT_KEY";
   private String frameContextContextBeanId = "avicit.core.defaultFrameWorkContext";
   
   private WebApplicationContext applicationContext;
   
   public FrameworkContext loadFrameworkContext(HttpRequestResponseHolder requestResponseHolder)
   {
     HttpServletRequest request = requestResponseHolder.getRequest();
     HttpSession httpSession = request.getSession(false);
     FrameworkContext frameContext = readFrameworkContextFromSession(httpSession);
     if (frameContext == null)
     {
       frameContext = (FrameworkContext)this.applicationContext.getBean(this.frameContextContextBeanId);
     }
     frameContext.resetStatus();
     return frameContext;
   }
   
 
 
   public void saveFrameworkContext(FrameworkContext context, HttpServletRequest request, HttpServletResponse response)
   {
     HttpSession httpSession = request.getSession(false);
     if (httpSession == null) {
       return;
     }
     if (httpSession.getAttribute("__FRAMEWORK_CONTEXT_KEY") == null) {
       httpSession.setAttribute("__FRAMEWORK_CONTEXT_KEY", context);
       return;
     }
     if ((context != null) && (context.isNeedSave())) {
       httpSession.setAttribute("__FRAMEWORK_CONTEXT_KEY", context);
     }
   }
   
 
 
 
 
   private FrameworkContext readFrameworkContextFromSession(HttpSession httpSession)
   {
     if (httpSession == null) return null;
     Object contextFromSession = httpSession.getAttribute("__FRAMEWORK_CONTEXT_KEY");
     if (contextFromSession == null) {
       return null;
     }
     return (FrameworkContext)contextFromSession;
   }
   
   public void setServletContext(ServletContext servletContext)
   {
     this.applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
   }
 }


