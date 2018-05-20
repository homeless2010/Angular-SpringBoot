 package com.piedpiper.platform.core.rest.auth;
 
 import java.io.IOException;
 import javax.servlet.Filter;
 import javax.servlet.FilterChain;
 import javax.servlet.FilterConfig;
 import javax.servlet.ServletException;
 import javax.servlet.ServletRequest;
 import javax.servlet.ServletResponse;
 import javax.servlet.http.HttpServletRequest;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 
 
 
 
 
 
 public class RestAuthFilter
   implements Filter
 {
   static Logger logger = LoggerFactory.getLogger(RestAuthFilter.class);
   
 
   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
     throws IOException, ServletException
   {
     HttpServletRequest httpRequest = (HttpServletRequest)request;
     String ip = getClientIp(httpRequest);
     if (checkAuth(ip)) {
       chain.doFilter(request, response);
     } else {
       throw new ServletException("该IP=" + ip + "未被授权访问。");
     }
   }
   
   private static String getClientIp(HttpServletRequest request) {
     String ip = request.getHeader("x-forwarded-for");
     if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
       ip = request.getHeader("Proxy-Client-IP");
     }
     if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
       ip = request.getHeader("WL-Proxy-Client-IP");
     }
     if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
       ip = request.getRemoteAddr();
     }
     return ip;
   }
   
 
 
 
 
   private boolean checkAuth(String ip)
   {
     boolean isAuth = false;
     if ((ip == null) || (ip.equals(""))) {
       return false;
     }
     
     if (RestWhiteListConfig.check(ip)) {
       isAuth = true;
     }
     return isAuth;
   }
   
   public void init(FilterConfig config)
     throws ServletException
   {}
   
   public void destroy() {}
 }


