package com.piedpiper.platform.api.sysapprole.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.GenericType;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.type.TypeReference;
import com.piedpiper.platform.api.sysapprole.SysAppRoleAPI;
import com.piedpiper.platform.api.sysapprole.dto.SysAppRole;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheManager;
import com.piedpiper.platform.core.rest.client.RestClient;
import com.piedpiper.platform.core.rest.client.RestClientConfig;
import com.piedpiper.platform.core.rest.msg.ResponseMsg;

public class SysAppRoleAPImpl implements SysAppRoleAPI {
	@Autowired
	private BaseCacheManager baseCacheManager;

	public List<SysAppRole> getAllSysAppRoles() {
		return this.baseCacheManager.getAllFromCache("PLATFORM6_SYSAPPROLE", new TypeReference() {
		});
	}

	public List<String> getAllowedRoles(String appId) {
		List<String> result = new ArrayList();
		for (SysAppRole approle : getAllSysAppRoles()) {
			if (appId.equals(approle.getSysappId())) {
				result.add(approle.getSysroleId());
			}
		}
		return result;
	}

	public int deleteByAppId(String appId) {
		String url = RestClientConfig.getRestHost("sysapprole") + "/api/platform6/sysapprole/SysAppRole/deleteByAppid/"
				+ appId + "/v1";
		ResponseMsg<Integer> responseMsg = RestClient.doPost(url, "", new GenericType() {
		});
		if (responseMsg.getRetCode().equals("200")) {
			return ((Integer) responseMsg.getResponseBody()).intValue();
		}
		throw new RuntimeException("删除应用id" + appId + "关联数据错误，描述：" + responseMsg.getErrorDesc());
	}

	public int deleteByRoleId(String roleId) {
		String url = RestClientConfig.getRestHost("sysapprole") + "/api/platform6/sysapprole/SysAppRole/deleteByRoleid/"
				+ roleId + "/v1";
		ResponseMsg<Integer> responseMsg = RestClient.doPost(url, "", new GenericType() {
		});
		if (responseMsg.getRetCode().equals("200")) {
			return ((Integer) responseMsg.getResponseBody()).intValue();
		}
		throw new RuntimeException("删除应用id" + roleId + "关联数据错误，描述：" + responseMsg.getErrorDesc());
	}

	public int updateRoleNameById(String name, String id) {
		String data = name + "__&__" + id;
		String url = RestClientConfig.getRestHost("sysapprole")
				+ "/api/platform6/sysapprole/SysAppRole/updateRoleNameById/v1";
		ResponseMsg<Integer> responseMsg = RestClient.doPost(url, data, new GenericType() {
		});
		if (responseMsg.getRetCode().equals("200")) {
			return ((Integer) responseMsg.getResponseBody()).intValue();
		}
		throw new RuntimeException("更新角色名称" + name + "关联数据错误，描述：" + responseMsg.getErrorDesc());
	}

	public boolean isExistRoleId(final String roleId) {
		return CollectionUtils.exists(getAllSysAppRoles(), new Predicate() {
			public boolean evaluate(Object object) {
				return ((SysAppRole) object).getSysroleId().equals(roleId);
			}
		});
	}
}
