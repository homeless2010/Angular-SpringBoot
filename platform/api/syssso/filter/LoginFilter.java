 package com.piedpiper.platform.api.syssso.filter;
 
 import com.piedpiper.platform.api.sysshirolog.filter.LoginFilterFactoryBean;
 import java.io.IOException;
 import java.net.URLEncoder;
 import java.util.Map;
 import java.util.Map.Entry;
 import javax.servlet.FilterChain;
 import javax.servlet.FilterConfig;
 import javax.servlet.ServletException;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import javax.servlet.http.HttpSession;
 import org.apache.commons.logging.Log;
 import org.apache.commons.logging.LogFactory;
 import org.apache.shiro.util.AntPathMatcher;
 import org.apache.shiro.util.CollectionUtils;
 import org.apache.shiro.util.PatternMatcher;
 import org.apache.shiro.web.util.WebUtils;
 import org.springframework.web.context.WebApplicationContext;
 import org.springframework.web.context.support.WebApplicationContextUtils;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class LoginFilter
   extends BaseConfigFilter
 {
   private String loginPage;
   private String formloginPage;
   private String topLoginPage = "/login/toplogin.jsp";
   
 
   private String[] authSessionVars;
   
 
   private String redirectURIVarName;
   
 
   private String httpheaderlogin;
   
 
   private String ssologin;
   
 
   private String[] notFilters;
   
 
   private String redirectQueryStringVarName;
   
   private static Log log = LogFactory.getLog(LoginFilter.class);
   
 
 
 
 
 
   private LoginFilterFactoryBean bean;
   
 
 
 
 
 
   private boolean isHasNotFilters(String str)
   {
     for (String s : this.notFilters) {
       if (str.indexOf(s) != -1) {
         return true;
       }
     }
     
     return false;
   }
   
 
 
 
 
 
 
 
 
 
 
   private boolean sessionIsOK(HttpSession session)
   {
     if (null == session) {
       return false;
     }
     for (String s : this.authSessionVars)
     {
       if (null == session.getAttribute(s)) {
         return false;
       }
     }
     return true;
   }
   
 
 
 
 
 
 
 
 
 
 
   protected void initParam()
     throws ServletException
   {
     FilterConfig config = getFilterConfig();
     this.loginPage = config.getInitParameter("loginPage");
     this.authSessionVars = config.getInitParameter("authSessionVars").split(",");
     this.redirectURIVarName = config.getInitParameter("redirectURIVarName");
     this.notFilters = config.getInitParameter("notFilters").split(",");
     this.redirectQueryStringVarName = config.getInitParameter("redirectQueryStringVarName");
     this.httpheaderlogin = config.getInitParameter("httpheaderlogin");
     this.ssologin = config.getInitParameter("ssologin");
     this.formloginPage = config.getInitParameter("formloginPage");
     this.bean = ((LoginFilterFactoryBean)WebApplicationContextUtils.getWebApplicationContext(config.getServletContext()).getBean(LoginFilterFactoryBean.class));
   }
   
 
   protected void processFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
     throws IOException, ServletException
   {
     initParam();
     
     HttpSession session = request.getSession();
     
     String URI = request.getRequestURI();
     
     String qString = request.getQueryString();
     
     String contextPath = request.getContextPath().toString();
     
     log.debug("----contextpath:" + contextPath + "----- filter begin uri=" + URI);
     
 
     log.debug("--------- Qstring=" + request.getQueryString());
     
     if (((URI.equals(contextPath + this.formloginPage)) || (URI.equals(contextPath + this.topLoginPage))) && 
       ("t".equals(request.getSession().getAttribute("isLogin")))) {
       response.sendRedirect(contextPath + "/" + request.getSession().getAttribute("LOGINSUCCESSNEXTURL_").toString());
       return;
     }
     
 
     if (this.ssologin.equals("true"))
     {
       if ((URI.indexOf(contextPath + "/" + this.loginPage) != -1) || (isHasNotFilters(URI)) || (getLoginFilter(request))) {
         chain.doFilter(request, response);
         return;
       }
       if (!sessionIsOK(session)) {
         String redirectUrl = "";
         if ((qString != null) && (!"".equals(qString))) {
           qString = qString.replaceAll("&", ";;;;");
           redirectUrl = contextPath + "/login/" + this.loginPage + "?" + this.redirectURIVarName + "=" + URI + "&" + this.redirectQueryStringVarName + "=" + URLEncoder.encode(URLEncoder.encode(qString), "UTF-8") + "";
         } else {
           redirectUrl = contextPath + "/login/" + this.loginPage + "?" + this.redirectURIVarName + "=" + URI;
         }
         response.sendRedirect(redirectUrl);
       } else {
         chain.doFilter(request, response);
       }
       
     }
     else if (this.httpheaderlogin.equals("true")) {
       if ((URI.indexOf(contextPath + "/" + this.loginPage) != -1) || (isHasNotFilters(URI)) || (getLoginFilter(request))) {
         chain.doFilter(request, response);
         return;
       }
       if (!sessionIsOK(session)) {
         String redirectUrl = "";
         String username = request.getParameter("username_");
         if ((username != null) && (!"".equals(username))) {
           qString = qString.replaceAll("&", ";;;;");
           redirectUrl = contextPath + "/platform/user/caslogin?username_=" + username + "&" + this.redirectURIVarName + "=" + URI + "&" + this.redirectQueryStringVarName + "=" + URLEncoder.encode(URLEncoder.encode(qString), "UTF-8") + "";
         } else {
           redirectUrl = contextPath + "/login/toplogin.jsp";
         }
         response.sendRedirect(redirectUrl);
       } else {
         chain.doFilter(request, response);
       }
       
 
     }
     else
     {
       chain.doFilter(request, response);
       return;
     }
   }
   
 
   public boolean getLoginFilter(HttpServletRequest request)
   {
     Map<String, String> filterChainDefinitionMap = this.bean.getFilterChainDefinitions();
     String uri;
     if (!CollectionUtils.isEmpty(filterChainDefinitionMap)) {
       uri = WebUtils.getPathWithinApplication(WebUtils.toHttp(request));
       for (Map.Entry<String, String> entry : filterChainDefinitionMap.entrySet()) {
         String path = (String)entry.getKey();
         if ((((String)entry.getValue()).equals("anon")) && (pathMatches(path, uri))) {
           return true;
         }
       }
     }
     return false;
   }
   
   protected boolean pathMatches(String pattern, String path) { PatternMatcher pathMatcher = new AntPathMatcher();
     return pathMatcher.matches(pattern, path);
   }
   
   protected Log getLog() {
     return log;
   }
 }


