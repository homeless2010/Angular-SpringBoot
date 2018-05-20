package com.piedpiper.platform.api.session;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.piedpiper.platform.api.session.dto.SecurityUser;
import com.piedpiper.platform.api.sysuser.dto.SysDept;
import com.piedpiper.platform.api.sysuser.dto.SysDeptTl;
import com.piedpiper.platform.api.sysuser.dto.SysUser;
import com.piedpiper.platform.core.properties.PlatformProperties;
import com.piedpiper.platform.core.rest.client.RestClientConfig;
import com.piedpiper.platform.core.shiroSecurity.contextThread.ContextCommonHolder;

public class SessionHelper {
	public static String SESSION_CURRENT_DEPT = "CURRENT_DEPT";
	public static String SESSION_CURRENT_DEPT_TL = "CURRENT_DEPT_TL";
	public static String SESSION_CURRENT_USER = "CURRENT_LOGINUSER";
	public static String SESSION_CURRENT_LANGUAGE_CODE = "CURRENT_LANGUAGE";
	public static String SESSION_CURRENT_DEPT_ID = "deptId";
	public static String SESSION_CURRENT_USER_ID = "userId";

	private static final String cLanguage = PlatformProperties.getProperty("platform.default.languageCode");

	public static String getClientIp(HttpServletRequest request) {
		if (null == request) {
			return "127.0.0.1";
		}
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

	public static String getLoginName(HttpServletRequest request) {
		SysUser currentuser = (SysUser) request.getSession().getAttribute(SESSION_CURRENT_USER);
		return currentuser.getLoginName();
	}

	public static SysDeptTl getCurrentDeptTl(HttpServletRequest request) {
		SysDeptTl tl = new SysDeptTl();
		String deptName = getCurrentDeptName(request);
		tl.setDeptName(deptName);
		return tl;
	}

	public static String getCurrentDeptName(HttpServletRequest request) {
		return (String) request.getSession().getAttribute("CURRENT_DEPT_TL");
	}

	public static String getApplicationId() {
		if (RestClientConfig.isServer) {
			Object obj = ContextCommonHolder.getAttribute("CURRENT_APPLICATIONIDR");
			if (null == obj) {
				throw new RuntimeException("service层调用api层，请检查调用方法的ApplicationId是否正确传递");
			}
			return obj.toString();
		}
		return RestClientConfig.systemid;
	}

	public static String getCurrentLanguageCode() {
		return cLanguage;
	}

	@Deprecated
	public static String getCurrentLanguageCode(HttpServletRequest request) {
		return getCurrentLanguageCode();
	}

	public static String getSystemDefaultLanguageCode() {
		return "zh_CN";
	}

	public static String getCurrentUserLanguageCode(HttpServletRequest request) {
		if (RestClientConfig.isServer) {
			Object obj = ContextCommonHolder.getAttribute(SESSION_CURRENT_LANGUAGE_CODE);
			if (null == obj) {
				return null;
			}
			return obj.toString();
		}
		Object o = request.getSession().getAttribute(SESSION_CURRENT_LANGUAGE_CODE);
		if (o != null) {
			return o.toString();
		}
		return null;
	}

	@Deprecated
	public static String getSystemDefaultLanguageCode(HttpServletRequest request) {
		return getSystemDefaultLanguageCode();
	}

	public static String getLoginSysUserId(HttpServletRequest request) {
		SysUser currentuser = (SysUser) request.getSession().getAttribute(SESSION_CURRENT_USER);
		if (currentuser == null) {
			return null;
		}
		return currentuser.getId();
	}

	public static String getCurrentOrgId(HttpServletRequest request) {
		SysDept currentdept = (SysDept) request.getSession().getAttribute(SESSION_CURRENT_DEPT);
		if (currentdept == null) {
			return null;
		}
		return currentdept.getOrgId();
	}

	public static String getCurrentDeptId(HttpServletRequest request) {
		Object obj = request.getSession().getAttribute(SESSION_CURRENT_DEPT_ID);
		if (obj == null) {
			return null;
		}
		return obj.toString();
	}

	public static String getAppRealPath(HttpServletRequest request) {
		return request.getSession().getServletContext().getRealPath("/");
	}

	public static SecurityUser getSecurityUser(HttpServletRequest request) {
		SecurityUser newsecurityUse = (SecurityUser) request.getSession().getAttribute("CURRENT_LOGINUSER_SECURITY");
		return newsecurityUse;
	}

	public static Locale getLocaleByUser(HttpServletRequest request) {
		return (Locale) request.getSession().getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
	}

	public static SysUser getLoginSysUser(HttpServletRequest request) {
		SysUser currentuser = (SysUser) request.getSession().getAttribute(SESSION_CURRENT_USER);
		if (currentuser == null) {
			return null;
		}
		return currentuser;
	}

	public static SysDept getCurrentDept(HttpServletRequest request) {
		SysDept currentdept = (SysDept) request.getSession().getAttribute(SESSION_CURRENT_DEPT);
		if (currentdept == null) {
			return null;
		}
		return currentdept;
	}
}
