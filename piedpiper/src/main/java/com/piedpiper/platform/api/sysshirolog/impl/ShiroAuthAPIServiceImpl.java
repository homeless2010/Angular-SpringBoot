package com.piedpiper.platform.api.sysshirolog.impl;

import com.piedpiper.platform.api.session.SessionHelper;
import com.piedpiper.platform.api.sysshirolog.IAuthServiceAPI;
import com.piedpiper.platform.api.sysuser.dto.SysRole;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheManager;
import com.piedpiper.platform.core.shiroSecurity.shiroUtil.ShiroOrderedProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

public class ShiroAuthAPIServiceImpl implements IAuthServiceAPI {
	private static final Logger log = LoggerFactory.getLogger(ShiroAuthAPIServiceImpl.class);

	private static final String CRLF = "\r\n";

	private static final String LAST_AUTH_STR = "   

	@Autowired
	private ShiroFilterFactoryBean shiroFilterFactoryBean;

	@Autowired
	private BaseCacheManager baseCacheManager;

	public String loadFilterChainDefinitions()
   {
     StringBuffer sb = new StringBuffer("");
     
     sb.append(getFixedAuthRule()).append("     
     return sb.toString();
   }

	private String getFixedAuthRule() {
		StringBuffer sb = new StringBuffer("");

		ClassPathResource cp = new ClassPathResource("shiro.properties");
		Properties properties = new ShiroOrderedProperties();
		try {
			properties.load(cp.getInputStream());
		} catch (IOException e) {
			log.error("auth-shiro.properties error!", e);
			throw new RuntimeException("load auth-shiro.properties error!");
		}
		for (Iterator<Object> its = properties.keySet().iterator(); its.hasNext();) {
			String key = (String) its.next();
			sb.append(key).append(" = ").append(properties.getProperty(key).trim()).append("\r\n");
		}

		return sb.toString();
	}

	public synchronized void reCreateFilterChains() {
		AbstractShiroFilter shiroFilter = null;
		try {
			shiroFilter = (AbstractShiroFilter) this.shiroFilterFactoryBean.getObject();
		} catch (Exception e) {
			log.error("getShiroFilter from shiroFilterFactoryBean error!", e);
			throw new RuntimeException("get ShiroFilter from shiroFilterFactoryBean error!");
		}
		PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter
				.getFilterChainResolver();
		DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();
		manager.getFilterChains().clear();
		this.shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
		this.shiroFilterFactoryBean.setFilterChainDefinitions(loadFilterChainDefinitions());
		Map<String, String> chains = this.shiroFilterFactoryBean.getFilterChainDefinitionMap();
		for (Map.Entry<String, String> entry : chains.entrySet()) {
			String url = (String) entry.getKey();
			String chainDefinition = ((String) entry.getValue()).trim().replace(" ", "");
			manager.createChain(url, chainDefinition);
		}
	}

	public Set<String> findRoles(String userId) {
		return findRolesByUserId(userId);
	}

	public Set<String> findPermissions(String userId) {
		return findRolesByUserId(userId);
	}

	private Set<String> findRolesByUserId(String userId) {
		List<SysRole> rolelist = this.baseCacheManager.getAllFromCache("PLATFORM6_USER_ROLE_" + userId,
				new TypeReference() {
				}, SessionHelper.getApplicationId());

		Set<String> set = new HashSet(rolelist.size());
		for (SysRole role : rolelist) {
			if ((role != null) && (role.getRoleName() != null) && (!role.getRoleName().equals(""))) {

				set.add(role.getRoleName());
			}
		}
		return set;
	}
}
