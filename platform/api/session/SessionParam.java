package com.piedpiper.platform.api.session;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.piedpiper.platform.api.session.dto.SessionBean;

public class SessionParam {
	public static final String clientIp = "clientIp";
	public static final String loginName = "loginName";
	public static final String applicationId = "applicationId";
	public static final String currentLanguageCode = "currentLanguageCode";
	public static final String systemDefaultLanguageCode = "systemDefaultLanguageCode";
	public static final String currentUserLanguageCode = "currentUserLanguageCode";
	public static final String loginSysUserId = "loginSysUserId";
	public static final String currentOrgId = "currentOrgId";
	public static final String currentDeptId = "currentDeptId";
	public static final String sanyuanLogin = "sanyuanLogin";
	public static final String appRealPath = "appRealPath";
	public static final String securityUser = "securityUser";
	public static final String localeByUser = "localeByUser";
	public static final String loginSysUser = "loginSysUser";
	public static final String currentDept = "currentDept";

	public static Map<String, Object> makeSessionParam(HttpServletRequest request, String[] keys) {
		Map<String, Object> sessionParam = new HashMap();
		if ((keys == null) || (keys.length == 0)) {
			return sessionParam;
		}
		for (int i = 0; i < keys.length; i++) {
			if (keys[i].equals("clientIp")) {
				sessionParam.put("clientIp", SessionHelper.getClientIp(request));
			}
			if (keys[i].equals("loginName")) {
				sessionParam.put("loginName", SessionHelper.getLoginName(request));
			}
			if (keys[i].equals("applicationId")) {
				sessionParam.put("applicationId", SessionHelper.getApplicationId());
			}
			if (keys[i].equals("currentLanguageCode")) {
				sessionParam.put("currentLanguageCode", SessionHelper.getCurrentLanguageCode());
			}
			if (keys[i].equals("systemDefaultLanguageCode")) {
				sessionParam.put("systemDefaultLanguageCode", SessionHelper.getSystemDefaultLanguageCode());
			}
			if (keys[i].equals("currentUserLanguageCode")) {
				sessionParam.put("currentUserLanguageCode", SessionHelper.getCurrentUserLanguageCode(request));
			}
			if (keys[i].equals("loginSysUserId")) {
				sessionParam.put("loginSysUserId", SessionHelper.getLoginSysUser(request));
			}
			if (keys[i].equals("currentOrgId")) {
				sessionParam.put("currentOrgId", SessionHelper.getCurrentOrgId(request));
			}
			if (keys[i].equals("currentDeptId")) {
				sessionParam.put("currentDeptId", SessionHelper.getCurrentDeptId(request));
			}
			if (keys[i].equals("appRealPath")) {
				sessionParam.put("appRealPath", SessionHelper.getAppRealPath(request));
			}
			if (keys[i].equals("securityUser")) {
				sessionParam.put("securityUser", SessionHelper.getSecurityUser(request));
			}
			if (keys[i].equals("localeByUser")) {
				sessionParam.put("localeByUser", SessionHelper.getLocaleByUser(request));
			}
			if (keys[i].equals("loginSysUser")) {
				sessionParam.put("loginSysUser", SessionHelper.getLoginSysUser(request));
			}
			if (keys[i].equals("currentDept")) {
				sessionParam.put("currentDept", SessionHelper.getCurrentDept(request));
			}
		}
		return sessionParam;
	}

	public static SessionBean makeSessionBean(HttpServletRequest request, String[] keys) {
		SessionBean sessionParam = new SessionBean();
		for (int i = 0; i < keys.length; i++) {
			if ((keys == null) || (keys[i].equals("clientIp"))) {
				sessionParam.setClientIp(SessionHelper.getClientIp(request));
			}
			if ((keys == null) || (keys[i].equals("loginName"))) {
				sessionParam.setLoginName(SessionHelper.getLoginName(request));
			}
			if ((keys == null) || (keys[i].equals("applicationId"))) {
				sessionParam.setApplicationId(SessionHelper.getApplicationId());
			}
			if ((keys == null) || (keys[i].equals("currentLanguageCode"))) {
				sessionParam.setCurrentLanguageCode(SessionHelper.getCurrentLanguageCode());
			}
			if ((keys == null) || (keys[i].equals("systemDefaultLanguageCode"))) {
				sessionParam.setSystemDefaultLanguageCode(SessionHelper.getSystemDefaultLanguageCode());
			}
			if ((keys == null) || (keys[i].equals("currentUserLanguageCode"))) {
				sessionParam.setCurrentUserLanguageCode(SessionHelper.getCurrentUserLanguageCode(request));
			}
			if ((keys == null) || (keys[i].equals("loginSysUserId"))) {
				sessionParam.setLoginSysUser(SessionHelper.getLoginSysUser(request));
			}
			if ((keys == null) || (keys[i].equals("currentOrgId"))) {
				sessionParam.setCurrentOrgId(SessionHelper.getCurrentOrgId(request));
			}
			if ((keys == null) || (keys[i].equals("currentDeptId"))) {
				sessionParam.setCurrentDeptId(SessionHelper.getCurrentDeptId(request));
			}

			if ((keys == null) || (keys[i].equals("appRealPath"))) {
				sessionParam.setAppRealPath(SessionHelper.getAppRealPath(request));
			}
			if ((keys == null) || (keys[i].equals("securityUser"))) {
				sessionParam.setSecurityUser(SessionHelper.getSecurityUser(request));
			}
			if ((keys == null) || (keys[i].equals("localeByUser"))) {
				sessionParam.setLocaleByUser(SessionHelper.getLocaleByUser(request));
			}
			if ((keys == null) || (keys[i].equals("loginSysUser"))) {
				sessionParam.setLoginSysUser(SessionHelper.getLoginSysUser(request));
			}
			if ((keys == null) || (keys[i].equals("currentDept"))) {
				sessionParam.setCurrentDept(SessionHelper.getCurrentDept(request));
			}
		}
		return sessionParam;
	}
}
