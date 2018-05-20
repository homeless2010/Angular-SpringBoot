package com.piedpiper.platform.api.application.impl;

import com.piedpiper.platform.api.application.SysApplicationAPI;
import com.piedpiper.platform.api.application.dto.SysApplication;
import com.piedpiper.platform.api.sysapprole.SysAppRoleAPI;
import com.piedpiper.platform.api.sysuser.SysRoleAPI;
import com.piedpiper.platform.api.sysuser.SysUserRoleAPI;
import com.piedpiper.platform.api.sysuser.dto.SysRole;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheManager;
import com.piedpiper.platform.core.rest.client.RestClient;
import com.piedpiper.platform.core.rest.client.RestClientConfig;
import com.piedpiper.platform.core.rest.msg.ResponseMsg;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.GenericType;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.springframework.beans.factory.annotation.Autowired;

public class SysApplicationAPImpl implements SysApplicationAPI {
	@Autowired
	private BaseCacheManager baseCacheManager;
	@Autowired
	private SysRoleAPI sysRoleAPI;
	@Autowired
	private SysAppRoleAPI sysAppRoleAPI;
	@Autowired
	private SysUserRoleAPI sysUserRoleAPI;

	public void reLoad() throws Exception {
		String url = RestClientConfig.getRestHost("sysapplication")
				+ "/api/platform6/sysapplication/SysApplication/reLoad/v1";
		ResponseMsg<Void> responseMsg = RestClient.doGet(url, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {
			throw new Exception(responseMsg.getErrorDesc());
		}
	}

	public SysApplication getCurrentApplication() {
		return getSysApplication(RestClientConfig.systemid);
	}

	public List<SysApplication> getAllSysApplicationList() {
		return this.baseCacheManager.getAllFromCache("PLATFORM6_APPLICATION", new TypeReference() {
		});
	}

	public String getAllSysApplicationNameById(String applicationId) {
		SysApplication sysApplication = getSysApplication(applicationId);
		return sysApplication.getApplicationName();
	}

	public SysApplication getSysApplication(String id) {
		return (SysApplication) this.baseCacheManager.getObjectFromCache("PLATFORM6_APPLICATION", id,
				SysApplication.class);
	}

	public List<SysApplication> getAllowedSysApplicationByUserId(String userId) {
		if (this.sysRoleAPI.isAdministratorByUserId(userId)) {
			return getAllSysApplicationList();
		}
		List<SysApplication> result = new ArrayList();
		List<SysRole> userRoles = this.sysUserRoleAPI.getSysRoleListBySysUserIdNoAppId(userId);
		for (SysApplication app : getAllSysApplicationList()) {
			final List<String> allRoles = this.sysAppRoleAPI.getAllowedRoles(app.getId());
			if (CollectionUtils.exists(userRoles, new Predicate() {
				public boolean evaluate(Object object) {
					return allRoles.contains(((SysRole) object).getId());
				}
			})) {

				result.add(app);
			}
		}

		return result;
	}

	public String getCurrentAppId() {
		return RestClientConfig.systemid;
	}

	public String getCurrentAppCode() {
		return getCurrentApplication().getApplicationCode();
	}
}
