 package com.piedpiper.platform.api.sysshirolog.filter;
 
 import com.piedpiper.platform.api.session.SessionHelper;
 import com.piedpiper.platform.api.session.dto.SecurityUser;
 import com.piedpiper.platform.api.sysmenu.dto.SysMenu;
 import com.piedpiper.platform.api.sysshirolog.utils.SecurityUtil;
 import com.piedpiper.platform.api.sysuser.dto.SysUser;
 import com.piedpiper.platform.core.properties.PlatformProperties;
 import com.piedpiper.platform.core.redisCacheManager.BaseCacheManager;
 import com.piedpiper.platform.core.spring.SpringFactory;
 import com.fasterxml.jackson.core.type.TypeReference;
 import java.io.IOException;
 import java.util.List;
 import javax.servlet.ServletRequest;
 import javax.servlet.ServletResponse;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.shiro.subject.PrincipalCollection;
 import org.apache.shiro.subject.Subject;
 import org.apache.shiro.web.filter.authz.AuthorizationFilter;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class ShiroRolesOrFilterAPI
   extends AuthorizationFilter
 {
   public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
     throws IOException
   {
     HttpServletRequest httpservlet = (HttpServletRequest)request;
     SecurityUser shiroUser = (SecurityUser)getSubject(request, response).getPrincipals().getPrimaryPrincipal();
     String url = casUrlFormateforplat(httpservlet);
     boolean isAdmin = SecurityUtil.isPlatformAdministratorByUserId(shiroUser.getUser().getId());
     
     if (isAdmin)
       return true;
     if (!SecurityUtil.checkUrlInPermiss(url))
       return true;
     if (PlatformProperties.defaultPageAuthIsGranted()) {
       List<SysMenu> defaultMenus = getAllSysMenusNotInPermisson(SessionHelper.getApplicationId());
       for (SysMenu tmenu : defaultMenus) {
         if ((tmenu != null) && (tmenu.getUrl() != null) && (tmenu.getUrl().equalsIgnoreCase(url))) {
           return true;
         }
       }
     }
     
     try
     {
       if ((url.indexOf("index.jsp") < 0) && (url.indexOf("logout") < 0) && (url.indexOf("login") < 0))
         return SecurityUtil.checkUrl(url);
     } catch (Exception e) {
       logger.error(e.getMessage(), e);
       return false;
     }
     return true;
   }
   
 
   private String casUrlFormateforplat(HttpServletRequest request)
   {
     String url = request.getRequestURI();
     request.getContextPath();
     url = url.substring(request.getContextPath().length() + 1);
     
     return url;
   }
   
   private List<SysMenu> getAllSysMenusNotInPermisson(String appid)
   {
     BaseCacheManager baseCacheManager = (BaseCacheManager)SpringFactory.getBean("baseCacheManager");
     List<SysMenu> list = baseCacheManager.getAllFromCache("avicit.menu.default_" + appid, new TypeReference() {});
     return list;
   }
   
 
 
 
 
 
   private static final Logger logger = LoggerFactory.getLogger(ShiroRolesOrFilterAPI.class);
   public static final String DEFAULT_MENU_RESOURCES_COLLECTION = "avicit.menu.default";
 }


