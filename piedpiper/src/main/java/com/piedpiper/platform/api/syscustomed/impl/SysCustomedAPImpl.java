package com.piedpiper.platform.api.syscustomed.impl;

import java.util.List;

import javax.ws.rs.core.GenericType;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.type.TypeReference;
import com.piedpiper.platform.api.session.SessionHelper;
import com.piedpiper.platform.api.syscustomed.SysCustomedAPI;
import com.piedpiper.platform.api.syscustomed.dto.SysCustomed;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheManager;
import com.piedpiper.platform.core.rest.client.RestClient;
import com.piedpiper.platform.core.rest.client.RestClientConfig;
import com.piedpiper.platform.core.rest.client.RestException;
import com.piedpiper.platform.core.rest.msg.Muti3Bean;
import com.piedpiper.platform.core.rest.msg.ResponseMsg;

public class SysCustomedAPImpl implements SysCustomedAPI {
	@Autowired
	private BaseCacheManager baseCacheManager;

	public void reLoad() throws Exception {
		String url = RestClientConfig.getRestHost("syscustomed") + "/api/platform6/syscustomed/SysCustomed/reLoad/v1";
		ResponseMsg<Void> responseMsg = RestClient.doGet(url, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {
			throw new Exception(responseMsg.getErrorDesc());
		}
	}

	public List<SysCustomed> getSysCustomedListByUserIdAndKey(String userId, String key, String isMulti, String appId) {
		return this.baseCacheManager.getAllFromCache(
				"PLATFORM6_SYS_CUSTOMED_ORGID_USERID_KEY_ISMULTI" + userId + "_" + key + "_" + isMulti,
				new TypeReference() {
				}, appId);
	}

	public String getCustomesSkin(String userId, String appId) {
		return getCustomedValueByKey("PLATFORM_USER_SKIN", userId, appId);
	}

	public String getCustomedValueByKey(String key, String userId, String appId) {
		String value = null;
		SysCustomed sysCustomed = (SysCustomed) this.baseCacheManager
				.getObjectFromCache("PLATFORM6_SYS_CUSTOMED_ORGID_USERID_KEY" + userId, key, new TypeReference() {
				}, appId);
		if (null != sysCustomed) {
			value = sysCustomed.getValue();
		}
		return value;
	}

	public SysCustomed getSysCustomedByUserIdAndKey(String id, String key) {
		return getSysCustomedByUserIdAndKey(id, key, SessionHelper.getApplicationId());
	}

	public SysCustomed getSysCustomedByUserIdAndKey(String id, String key, String appId) {
		List<SysCustomed> list = this.baseCacheManager.getAllFromCache(
				"PLATFORM6_SYS_CUSTOMED_ORGID_USERID_KEY_ISMULTI" + id + "_" + key + "_0", new TypeReference() {
				}, appId);
		if (list.size() == 1)
			return (SysCustomed) list.get(0);
		if (list.size() > 1) {
			throw new RuntimeException("用户" + id + "，key【" + key + "】,应用id" + appId + "记录多余一条。");
		}
		return null;
	}

	public List<SysCustomed> getCustomedMenuStyle(String userId, String key, String value) {
		return getCustomedByKeyVal(userId, key, value, SessionHelper.getApplicationId());
	}

	public List<SysCustomed> getCustomedByKeyVal(String userId, String key, String value, String appId) {
		return this.baseCacheManager.getAllFromCache(
				"PLATFORM6_SYS_CUSTOMED_ORGID_USERID_KEY_VALUE" + userId + "_" + key + "_" + value,
				new TypeReference() {
				}, appId);
	}

	public void insertSysCustomed(SysCustomed sysCustomed) throws RestException {
		String url = RestClientConfig.getRestHost("syscustomed")
				+ "/api/platform6/syscustomed/SysCustomed/insertSysCustomed/v1";
		ResponseMsg<Void> responseMsg = RestClient.doPost(url, sysCustomed, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {
			throw new RestException(responseMsg.getErrorDesc());
		}
	}

	public void saveCustomesSkin(String skin, String currentOrgId, String loginSysUserId, String appId)
			throws Exception {
		saveCustomedKeyValueForDefault("PLATFORM_USER_SKIN", skin, currentOrgId, loginSysUserId, appId);
	}

	public void saveCustomedKeyValueForDefault(String key, String value, String currentOrgId, String loginSysUserId,
			String appId) throws Exception {
		saveCustomedKeyValue(key, value, currentOrgId, loginSysUserId, appId, "1");
	}

	public void saveCustomedKeyValueForNotDefault(String key, String value, String currentOrgId, String loginSysUserId,
			String appId) throws Exception {
		saveCustomedKeyValue(key, value, currentOrgId, loginSysUserId, appId, "0");
	}

	public void saveCustomedKeyValue(String key, String value, String currentOrgId, String loginSysUserId, String appId,
			String isDefault) throws Exception {
		Muti3Bean<String, String, String> bean = new Muti3Bean();
		bean.setDto1(currentOrgId);
		bean.setDto2(loginSysUserId);
		bean.setDto3(value);
		String url = RestClientConfig.getRestHost("syscustomed")
				+ "/api/platform6/syscustomed/SysCustomed/saveCustomedKeyValue/" + key + "/" + appId + "/" + isDefault
				+ "/v1";
		ResponseMsg<Void> response = RestClient.doPost(url, bean, new GenericType() {
		});
		if (!"200".equals(response.getRetCode())) {
			throw new RestException(response.getErrorDesc());
		}
	}

	public String deleteSysCustomedByPslGrp(String groupId) throws RestException {
		String url = RestClientConfig.getRestHost("syscustomed")
				+ "/api/platform6/syscustomed/SysCustomed/deleteSysCustomedByPslGrp/v1";
		ResponseMsg<String> responseMsg = RestClient.doPost(url, groupId, new GenericType() {
		});
		if (responseMsg.getRetCode().equals("200")) {
			return (String) responseMsg.getResponseBody();
		}
		throw new RestException(responseMsg.getErrorDesc());
	}
}
