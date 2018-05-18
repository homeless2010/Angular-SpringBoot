package com.piedpiper.platform.api.shiro.util;

import avicit.platform6.api.session.dto.SecurityUser;
import avicit.platform6.api.sysmenu.dto.SysMenu;
import avicit.platform6.api.sysprofile.SysProfileAPI;
import avicit.platform6.api.sysresource.SysResourceAPI;
import avicit.platform6.api.sysresource.dto.SysResource;
import avicit.platform6.api.sysshirolog.context.ContextHolder;
import avicit.platform6.api.sysuser.SysRoleAPI;
import avicit.platform6.commons.utils.SerializeUtil;
import avicit.platform6.commons.utils.ViewUtil;
import avicit.platform6.core.properties.PlatformProperties;
import avicit.platform6.core.redis.JedisSentinelPool;
import avicit.platform6.core.redisCacheManager.BaseCacheManager;
import avicit.platform6.core.rest.client.RestClientConfig;
import avicit.platform6.core.shiroSecurity.cas.util.StringUtil;
import avicit.platform6.core.shiroSecurity.passwordencoder.PasswordEncoder;
import avicit.platform6.core.shiroSecurity.shiroCache.ShiroCacheManager;
import avicit.platform6.core.spring.SpringFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.AntPathMatcher;
import org.apache.shiro.util.PatternMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;

public class SecurityUtil {
	private static Logger log = LoggerFactory.getLogger(SecurityUtil.class);

	private static SysProfileAPI sysProfile = (SysProfileAPI) SpringFactory.getBean(SysProfileAPI.class);

	private static SysResourceAPI sysResourceAPI = (SysResourceAPI) SpringFactory.getBean(SysResourceAPI.class);

	private static SysRoleAPI sysRoleAPI = (SysRoleAPI) SpringFactory.getBean(SysRoleAPI.class);

	private static ConcurrentHashMap<String, Boolean> fixFlagCache = new ConcurrentHashMap();
	public static final String DEFAULT_MENU_RESOURCES_COLLECTION = "avicit.menu.default";
	public static List<SysMenu> defaultMenus = null;
	public static int refushInt;
	public static int staticInt;

	public static String buildSimpleUrl(String oUrl) {
		String url = oUrl;
		url = parseUrl(url);
		if (url.startsWith("/")) {
			url = url.substring(1, url.length());
		}
		return url;
	}

	public static String buildKeyByUrl(String oUrl) {
		return buildSimpleUrl(oUrl);
	}

	public static String parseUrl(String oUrl) {
		if (oUrl == null)
			return null;
		String url = oUrl;
		String tmpLast = null;
		int n = url.indexOf("?");
		if (n != -1) {
			tmpLast = url.substring(n, url.length());
			url = url.substring(0, n);
		}
		if ((url.endsWith(".c")) || (url.endsWith(".d")) || (url.endsWith(".d7"))) {
			int p = url.lastIndexOf(".");
			if (p > -1) {
				String tmp = url.substring(p, url.length());
				url = url.substring(0, url.lastIndexOf("."));
				url = url.replace(".", "/");
				url = url + tmp;
			}
		}
		if (n != -1) {
			url = url + tmpLast;
		}

		return url;
	}

	public static String buildRequestUrlWithoutParameter(HttpServletRequest request) {
		String url = request.getRequestURI();
		if (url.indexOf("?") != -1) {
			url = url.substring(0, url.indexOf("?"));
		}
		return url;
	}

	public static String encodePassword(SecurityUser user, String presentedPassword) {
		PasswordEncoder passwordEncoder = (PasswordEncoder) SpringFactory.getBean("avicit.shiro.passwordEncoder");
		ReflectionSaltSource saltSource = (ReflectionSaltSource) SpringFactory.getBean("avicit.shiro.saltSource");
		Object salt = null;
		salt = saltSource.getSalt(user);
		if (defaultSaltPassword()) {
			return passwordEncoder.encodePassword(presentedPassword, null);
		}
		return passwordEncoder.encodePassword(presentedPassword, salt);
	}

	public static String encodePassword(String loginName, String presentedPassword) {
		PasswordEncoder passwordEncoder = (PasswordEncoder) SpringFactory.getBean("avicit.shiro.passwordEncoder");
		ReflectionSaltSource saltSource = (ReflectionSaltSource) SpringFactory.getBean("avicit.shiro.saltSource");
		Object salt = loginName;
		return passwordEncoder.encodePassword(presentedPassword, salt);
	}

	public static boolean checkUrl(String url) throws Exception {
		try {
			if (url == null)
				return true;
			String cassFull = PlatformProperties.getProperty("avicit.security.permissCassFullAuth");
			if (Boolean.valueOf(cassFull).booleanValue()) {
				SecurityUser shiroUser = (SecurityUser) SecurityUtils.getSubject().getPrincipals()
						.getPrimaryPrincipal();
				SimpleAuthorizationInfo info = (SimpleAuthorizationInfo) ShiroCacheManager.instance.getCache(null)
						.get(shiroUser);
				if (info == null) {
					refalshShiroCaceh();
					info = (SimpleAuthorizationInfo) ShiroCacheManager.instance.getCache(null).get(shiroUser);
				}
				Iterator iterator;
				if (info != null) {
					Set Permissions = info.getStringPermissions();
					for (iterator = Permissions.iterator(); iterator.hasNext();) {
						String path = (String) iterator.next();
						if (pathMatches(path, url)) {
							return true;
						}
					}
				}
				return false;
			}
			SecurityUtils.getSubject().checkPermissions(new String[]{url});
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	private static void refalshShiroCaceh() {
		SecurityUtils.getSubject().isPermitted("shuaxinquanxia");
	}

	protected static boolean pathMatches(String pattern, String path) {
		PatternMatcher pathMatcher = new AntPathMatcher();
		return pathMatcher.matches(pattern, path);
	}

	public static boolean checkPortletMenu(String url, String appid) {
		try {
			if (url == null) {
				return true;
			}

			if (defaultMenus != null) {
				if (staticInt < refushInt) {
					staticInt = refushInt;
					defaultMenus = ((BaseCacheManager) SpringFactory.getBean("baseCacheManager"))
							.getAllFromCache("avicit.menu.default_" + RestClientConfig.systemid, new TypeReference() {
							});
				}
				for (SysMenu tmenu : defaultMenus) {
					if ((tmenu != null) && (tmenu.getUrl() != null)) {
						if (fixFlagCache.get(tmenu.getUrl()) != null) {
							return ((Boolean) fixFlagCache.get(tmenu.getUrl())).booleanValue();
						}
						if (tmenu.getUrl().equalsIgnoreCase(url)) {
							fixFlagCache.put(tmenu.getUrl(), Boolean.valueOf(true));
						}

						return ((Boolean) fixFlagCache.get(tmenu.getUrl())).booleanValue();
					}
				}
			}

			SecurityUtils.getSubject().checkPermissions(new String[]{permissCassFullAuthIsGranted(url)});
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	public static int getOnlineUserNum(String applictionId) {
		if (!(StringUtils.hasText(applictionId))) {
			throw new NotApplictionIdException("no applictionId");
		}
		JedisSentinelPool jedisSentinelPool = (JedisSentinelPool) SpringFactory.getBean("jedisSentinelPool");
		ShardedJedis jedis = (ShardedJedis) jedisSentinelPool.getResource();
		Set keys = ((Jedis) jedis.getAllShards().iterator().next()).keys("*" + applictionId + "_SESSION");
		int userNum = 0;
		for (String key : keys) {
			Map session = (Map) SerializeUtil.unserialize(jedis.get(key.getBytes()));
			if (session != null)
				;
			SecurityUser secuser = (SecurityUser) session.get("CURRENT_LOGINUSER_SECURITY");
			if (secuser != null)
				++userNum;
		}
		jedisSentinelPool.returnResource(jedis);
		return userNum;
	}

	public static boolean isAdministrator(String userName) {
		return sysRoleAPI.isAdministrator(userName);
	}

	public static boolean isPlatformAdministratorByUserId(String userId) {
		return sysRoleAPI.isAdministratorByUserId(userId);
	}

	public static void checkModule(String simpleName) {
		String checkFlag = sysProfile.getProfileValueByCode("PLATFORM_V6_LOGINCHECK_CONTROL");
		if (null == checkFlag) {
			checkFlag = "false";

			log.warn("三员开关出现异常{是否配置开关选项，开关是否有值}！！！");
		}

		if ((!("true".equals(checkFlag.toLowerCase())))
				|| (ContextHolder.getRequest().getSession().getAttribute("IS_SUPER_MANAGE") != null))
			return;
		try {
			ContextHolder.getRequest().getSession().setAttribute("SANYUANLOGIN_REDIRECT", simpleName);
			ContextHolder.getResponse().sendRedirect(ViewUtil.getRequestPath(ContextHolder.getRequest())
					+ "avicit/platform6/modules/system/sanyuanlogin/sanyuanlogin.jsp");
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}

	public static void deleteCache(String key) {
		JedisSentinelPool jedisSentinelPool = (JedisSentinelPool) SpringFactory.getBean("jedisSentinelPool");
		ShardedJedis jedis = (ShardedJedis) jedisSentinelPool.getResource();
		Set keys = ((Jedis) jedis.getAllShards().iterator().next()).keys("*" + key + "*");

		for (String defaultKey : keys) {
			jedis.del(defaultKey);
		}
		jedisSentinelPool.returnResource(jedis);
	}

	public static boolean checkUrlByPermiss(String url) {
		return false;
	}

	public static boolean checkUrlInPermiss(String url) {
		SysResource listaccesscontrol = sysResourceAPI.getSysResourceByValue(url, SessionHelper.getApplicationId());
		if (listaccesscontrol != null)
			return true;
		String cassFull = PlatformProperties.getProperty("avicit.security.permissCassFullAuth");

		if (Boolean.valueOf(cassFull).booleanValue()) {
			List listResource = sysResourceAPI.getAllList();

			for (SysResource r : listResource) {
				String path = r.getValue();
				if ((path != null) && (pathMatches(path, url))) {
					return true;
				}

			}

			return false;
		}

		return false;
	}

	public static boolean checkID(String id) {
		try {
			if (id == null)
				return true;
			SecurityUtils.getSubject().checkPermissions(new String[]{id});
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	public static boolean checkauthUrl(String url) throws Exception {
		try {
			if (url == null)
				return true;
			SecurityUtils.getSubject().checkPermissions(new String[]{url});
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	public static boolean defaultSaltPassword() {
		String defaultSaltPassword = PlatformProperties.getProperty("salt_password_flag");
		return Boolean.valueOf(defaultSaltPassword).booleanValue();
	}

	public static String permissCassFullAuthIsGranted(String url) {
		String cassFull = PlatformProperties.getProperty("avicit.security.permissCassFullAuth");
		if ((Boolean.valueOf(cassFull).booleanValue()) && (url.indexOf("?") != -1)) {
			return StringUtil.extractFirst(url, "?");
		}
		return url;
	}

	public static String matchingUppercase(String loginName) {
		String matchUpperCase = PlatformProperties.getProperty("avicit.security.matchUpperCase");
		if (Boolean.valueOf(matchUpperCase).booleanValue()) {
			loginName = loginName.toUpperCase();
		}
		return loginName;
	}

	static {
		if (PlatformProperties.defaultPageAuthIsGranted()) {
			defaultMenus = ((BaseCacheManager) SpringFactory.getBean("baseCacheManager"))
					.getAllFromCache("avicit.menu.default_" + RestClientConfig.systemid, new TypeReference() {
					});
		}

		refushInt = 0;

		staticInt = 0;
	}
}