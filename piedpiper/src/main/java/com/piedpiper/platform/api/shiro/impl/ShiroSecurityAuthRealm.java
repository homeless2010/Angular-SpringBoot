package com.piedpiper.platform.api.shiro.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.piedpiper.platform.user.dto.User;

@Service
public class ShiroSecurityAuthRealm extends AuthorizingRealm {
	private static final Logger logger = LoggerFactory.getLogger(ShiroSecurityAuthRealm.class);
	public static ShiroSecurityAuthRealm instance;

	@Autowired
	public JedisSentinelPool jedisSentinelPool;

	@Autowired(required = false)
	private SysUserAPI sysUserAPI;

	public ShiroSecurityAuthRealm() {
		instance = this;

		setAuthenticationTokenClass(UsernamePasswordToken.class);

		setCredentialsMatcher(new CredentialsMatcher() {
			public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
				AvicitUsernamePasswordToken usernamepasswordtoken = (AvicitUsernamePasswordToken) token;
				SimpleAuthenticationInfo simpleSecurityUserInfo = (SimpleAuthenticationInfo) info;
				ShiroSecurityAPIDbAuthRealm.logger.debug(new StringBuilder().append(usernamepasswordtoken.getUsername())
						.append("Authentication start....").toString());
				SysUser user = null;
				SecurityUser securityUser = (SecurityUser) simpleSecurityUserInfo.getPrincipals().getPrimaryPrincipal();
				user = securityUser.getUser();
				if (ShiroSecurityAPIDbAuthRealm.logger.isDebugEnabled()) {
					ShiroSecurityAPIDbAuthRealm.logger.debug("user is" + user.toString());
				}
				SecurityUser securityUserdto = new SecurityUser();
				PojoUtil.copyProperties(securityUserdto, securityUser);
				if (usernamepasswordtoken.getCasFlag()) {
					usernamepasswordtoken.getLogbase().setSyslogUsername(user.getId());
					usernamepasswordtoken.getLogbase().setSyslogUsernameZh(user.getLoginName());
					FrameworkContext fwc = ContextHolder.getContext();
					fwc.setLoginUsername(securityUser.getUser().getLoginName());
					securityUser.setCurrentLoginDateTime(new Date(System.currentTimeMillis()));
					fwc.setLoginUser(securityUser);

					ShiroSecurityAPIDbAuthRealm.logger.debug(new StringBuilder()
							.append(usernamepasswordtoken.getUsername()).append("Authentication end....").toString());
					return true;
				}

				String pwd = new String(usernamepasswordtoken.getPassword());
				if (user.getLoginPassword().equals(SecurityUtil.encodePassword(securityUserdto, pwd))) {
					usernamepasswordtoken.getLogbase().setSyslogUsername(user.getId());
					usernamepasswordtoken.getLogbase().setSyslogUsernameZh(user.getName());

					ShardedJedis jedis = null;
					try {
						jedis = (ShardedJedis) ShiroSecurityAPIDbAuthRealm.this.jedisSentinelPool.getResource();
						jedis.del(securityUser.toString());
					} catch (Exception e) {
						ShiroSecurityAPIDbAuthRealm.this.jedisSentinelPool.returnBrokenResource(jedis);
					} finally {
						ShiroSecurityAPIDbAuthRealm.this.jedisSentinelPool.returnResource(jedis);
					}
					securityUser.setCurrentLoginDateTime(new Date(System.currentTimeMillis()));
					FrameworkContext fwc = ContextHolder.getContext();
					fwc.setLoginUsername(securityUser.getUser().getLoginName());
					fwc.setLoginUser(securityUser);

					return true;
				}
				return false;
			}
		});
	}

	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		SysUser user = LogUserHolder.getSysUser();
		logger.debug("通过登录名称@@@@@@@@@@@@@@@@" + user.getName() + "取出用户成功");
		SecurityUser securityUser = new SecurityUser(user);

		SimpleAuthenticationInfo saInfo = new SimpleAuthenticationInfo(securityUser,
				securityUser.getUser().getLoginPassword(), getName());

		return saInfo;
	}

	public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalsParent) {
		logger.info("authorization  roles  permissions  is start....");

		SecurityUser shiroUser = (SecurityUser) principalsParent.getPrimaryPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermissions(
				doGetAuthorizationInfoPermissions(shiroUser.getUser().getId(), SessionHelper.getApplicationId()));
		if (logger.isDebugEnabled()) {
			logger.debug("roles is::::::::::::" + info.getRoles());
			logger.debug("StringPermissions is :::::::::" + info.getStringPermissions());
		}
		return info;
	}

	private Set<String> doGetAuthorizationInfoPermissions(String userId, String appid) {
		String restHost = RestClientConfig.getRestHost("syshirosecurity");
		Muti2Bean parameter = new Muti2Bean(userId, appid);
		parameter = null;
		String restURL = restHost + "/api/platform6/shiroSecurity/permisstion/doGetAuthorizationInfoPermissions/"
				+ userId + "/" + appid + "/v1";
		ResponseMsg responseMsg = RestClient.doGet(restURL, new GenericType() {
		});
		Set authorizationInfoPermissions = null;
		if (responseMsg.getRetCode().equals("200")) {
			logger.debug("调用rest服务成功：" + restURL);
			authorizationInfoPermissions = (Set) responseMsg.getResponseBody();
		} else {
			logger.info("调用rest服务出错：:" + restURL + "," + responseMsg.getRetCode() + "," + responseMsg.getErrorDesc());
		}
		return authorizationInfoPermissions;
	}

	public void clearCachedAuthorizationInfo(PrincipalCollection pc) {
		SecurityUser shiroUser = (SecurityUser) pc.getPrimaryPrincipal();
		String restHost = RestClientConfig.getRestHost("syshirosecurity");
		String restURL = restHost + "/api/platform6/shiroSecurity/permisstion/clearCachedAuthorizationInfo/v1";
		ResponseMsg responseMsg = RestClient.doPost(restURL, shiroUser, new GenericType() {
		});
		if (!(responseMsg.getRetCode().equals("200"))) {
			throw new RuntimeException(responseMsg.getErrorDesc());
		}
		SimplePrincipalCollection principals = new SimplePrincipalCollection(pc, getName());
		super.clearCachedAuthorizationInfo(principals);
	}

}