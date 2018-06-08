//package com.piedpiper.configuration;
//
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//import org.apache.shiro.mgt.SecurityManager;
//import org.apache.shiro.session.mgt.DefaultSessionManager;
//import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
//import org.apache.shiro.spring.LifecycleBeanPostProcessor;
//import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.DependsOn;
//
//import com.piedpiper.platform.api.sysshirolog.filter.LoginFilterFactoryBean;
//import com.piedpiper.platform.api.sysshirolog.filter.ShiroRolesOrFilterAPI;
//import com.piedpiper.platform.api.sysshirolog.impl.ShiroAuthAPIServiceImpl;
//import com.piedpiper.platform.api.sysshirolog.impl.ShiroSecurityAPIDbAuthRealm;
//import com.piedpiper.platform.core.shiroSecurity.passwordencoder.ShaPasswordEncoder;
//import com.piedpiper.platform.core.shiroSecurity.shiroCache.ShiroCacheManager;
//
///**
// * @author homeless2010
// * 
// */
////@Configuration
//public class ShiroConfiguration {
//	/**
//	 * 初始化ShiroFilterFactoryBean的时候需要注入:SecurityManager
//	 * FilterChain定义说明:1.一个URL可以配置多个Filter,使用逗号分隔 2.当设置多个过滤器时,全部验证通过,才视为通过
//	 * 3.部分过滤器可指定参数,如perms,roles
//	 *
//	 */
//	@Bean
//	@DependsOn("piedpiper.shirorolefilter")
//	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
//		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//
//		// 必须设置 SecurityManager
//		shiroFilterFactoryBean.setSecurityManager(securityManager);
//
//		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
//		shiroFilterFactoryBean.setLoginUrl("/web/user/login");
//		// 登录成功后要跳转的链接
//		shiroFilterFactoryBean.setSuccessUrl("/index");
//		// 未授权界面;
//		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
//		shiroFilterFactoryBean.setFilterChainDefinitions("#{shiroAuthServiceApi.loadFilterChainDefinitions()}");
//
//		// 拦截器.
//		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
//		// 配置不会被拦截的链接 顺序判断
////		filterChainDefinitionMap.put("/static/**", "anon");
////		filterChainDefinitionMap.put("/web/user/ajaxLogin", "anon");
//
//		// 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
////		filterChainDefinitionMap.put("/logout", "logout");
////
////		filterChainDefinitionMap.put("/add", "perms[权限添加]");
//
//		// <!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->
//		// <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
////		filterChainDefinitionMap.put("/**", "authc");
//
//		filterChainDefinitionMap.put("piedpiper", "piedpiper.shirorolefilter");
//
//		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//		System.out.println("Shiro拦截器工厂类注入成功");
//		return shiroFilterFactoryBean;
//	}
//
//	@Bean
//	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
//		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
//		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
//		return authorizationAttributeSourceAdvisor;
//	}
//
//	@Bean
//	public DefaultSessionManager shirosessionManager() {
//		EnterpriseCacheSessionDAO shirosessionDAO = new EnterpriseCacheSessionDAO();
//		shirosessionDAO.setCacheManager(cacheManager());
//		DefaultSessionManager shirosessionManager = new DefaultSessionManager();
//		shirosessionManager.setSessionDAO(shirosessionDAO);
//		return shirosessionManager;
//	}
//
//	@Bean
//	public ShiroCacheManager cacheManager() {
//		ShiroCacheManager cacheManager = new ShiroCacheManager();
//		cacheManager.setCacheName("SecurityShiroCache");
//		return new ShiroCacheManager();
//	}
//
//	@Bean
//	public SecurityManager securityManager() {
//		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//		// 设置realm
//		securityManager.setRealm(shiroSecurityAuthRealm());
//		securityManager.setCacheManager(cacheManager());
//		return securityManager;
//	}
//
//	/**
//	 * 身份认证realm
//	 * 
//	 * @return
//	 */
//	@Bean
//	public ShiroSecurityAPIDbAuthRealm shiroSecurityAuthRealm() {
//		ShiroSecurityAPIDbAuthRealm shiroSecurityAuthRealm = new ShiroSecurityAPIDbAuthRealm();
//		return shiroSecurityAuthRealm;
//	}
//
//	@Bean(name = "piedpiper.shirorolefilter")
//	public ShiroRolesOrFilterAPI shiroRoleFilter() {
//		return new ShiroRolesOrFilterAPI();
//	}
//
//	@Bean(name = "piedpiper.shiro.passwordEncoder")
//	public ShaPasswordEncoder passwordEncoder() {
//		return new ShaPasswordEncoder();
//	}
//
//	@Bean(name = "shiroAuthServiceApi")
//	public ShiroAuthAPIServiceImpl shiroAuthServiceApi() {
//		return new ShiroAuthAPIServiceImpl();
//	}
//
//	@Bean
//	public LoginFilterFactoryBean loginFilter() {
//		LoginFilterFactoryBean loginFilter = new LoginFilterFactoryBean();
//		loginFilter.setFilterChainDefinitions("#{shiroAuthServiceApi.loadFilterChainDefinitions()}");
//		return loginFilter;
//	}
//
//	/* shrio */
//	@Bean
//	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
//		return new LifecycleBeanPostProcessor();
//	}
//}