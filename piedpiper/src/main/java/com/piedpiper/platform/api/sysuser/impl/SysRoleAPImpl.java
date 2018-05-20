package com.piedpiper.platform.api.sysuser.impl;

import com.piedpiper.platform.api.session.SessionHelper;
import com.piedpiper.platform.api.sysuser.SysRoleAPI;
import com.piedpiper.platform.api.sysuser.SysUserAPI;
import com.piedpiper.platform.api.sysuser.dto.SysRole;
import com.piedpiper.platform.api.sysuser.dto.SysUser;
import com.piedpiper.platform.core.dao.Paging;
import com.piedpiper.platform.core.dao.PropertyFilter;
import com.piedpiper.platform.core.properties.PlatformConstant.FixedRole;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheManager;
import com.piedpiper.platform.core.rest.client.RestClient;
import com.piedpiper.platform.core.rest.client.RestClientConfig;
import com.piedpiper.platform.core.rest.msg.Muti3Bean;
import com.piedpiper.platform.core.rest.msg.ResponseMsg;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import javax.ws.rs.core.GenericType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

public class SysRoleAPImpl implements SysRoleAPI {
	@Autowired
	private SysUserAPI sysUserAPI;
	@Autowired
	private BaseCacheManager baseCacheManager;

	public List<SysRole> getAllSysRoles() {
		this.baseCacheManager.getAllFromCache("PLATFORM6_ROLE", new TypeReference() {
		});
	}

	public SysRole getSysRoleById(String id)
   {
     (SysRole)this.baseCacheManager.getObjectFromCache("PLATFORM6_ROLE", id, new TypeReference() {});
   }

	public String getSysRoleNameById(String id) {
		if (StringUtils.isEmpty(id)) {
			return "";
		}
		SysRole role = getSysRoleById(id);
		if (role != null) {
			return getSysRoleById(id).getRoleName();
		}
		return "角色无效或者不存在";
	}

	public SysRole getSysRoleByRoleCode(String roleCode)
   {
     (SysRole)this.baseCacheManager.getObjectFromCache("PLATFORM6_ROLECODE", roleCode, new TypeReference() {}, SessionHelper.getApplicationId());
   }

	public SysRole getSysRoleByRoleCodeAndAppId(String roleCode, String appId)
   {
     (SysRole)this.baseCacheManager.getObjectFromCache("PLATFORM6_ROLECODE", roleCode, new TypeReference() {}, appId);
   }

	public List<SysRole> getAllSysRolesByAppId(String appId) {
		this.baseCacheManager.getAllFromCache("PLATFORM6_ROLE", new TypeReference() {
		}, appId);
	}

	public boolean checkRoleCode(String roleCode) {
		return this.baseCacheManager.containsKey("PLATFORM6_ROLECODE", roleCode);
	}

	public boolean isAdministrator(String username) {
		SysUser user = this.sysUserAPI.getSysUserByLoginString(username);
		return isAdmin(user.getId());
	}

	public boolean isAdministratorByUserId(String userId) {
		return isAdmin(userId);
	}

	private boolean isAdmin(String userId) {
		List<SysRole> list = this.baseCacheManager.getAllFromCache("PLATFORM6_USER_ROLE_" + userId,
				new TypeReference() {
				});
		for (int i = 0; i < list.size(); i++) {
			SysRole sysRole = (SysRole) list.get(i);
			if (PlatformConstant.FixedRole.platform_manager.getCode().equals(sysRole.getRoleCode())) {
				return true;
			}
		}
		return false;
	}

	public List<SysRole> getRolesByUserId(String userId, String appId) {
		this.baseCacheManager.getAllFromCache("PLATFORM6_USER_ROLE_" + userId, new TypeReference() {
		}, appId);
	}

	public void insertSysRole(SysRole sysRole) {
		String url = RestClientConfig.getRestHost("sysuser") + "/api/platform6/sysorguser/SysRole/insertSysRole/v1";
		ResponseMsg<Void> responseMsg = RestClient.doPost(url, sysRole, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {

			throw new RuntimeException(responseMsg.getErrorDesc());
		}
	}

	public void updateSysRole(SysRole sysRole) {
		String url = RestClientConfig.getRestHost("sysuser") + "/api/platform6/sysorguser/SysRole/updateSysRole/v1";
		ResponseMsg<Void> responseMsg = RestClient.doPost(url, sysRole, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {

			throw new RuntimeException(responseMsg.getErrorDesc());
		}
	}

	public void deleteSysRole(SysRole sysRole) {
		String url = RestClientConfig.getRestHost("sysuser") + "/api/platform6/sysorguser/SysRole/deleteSysRole/v1";
		ResponseMsg<Void> responseMsg = RestClient.doPost(url, sysRole, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {

			throw new RuntimeException(responseMsg.getErrorDesc());
		}
	}

	public List<SysRole> find(List<PropertyFilter> filter) {
		String url = RestClientConfig.getRestHost("sysuser") + "/api/platform6/sysorguser/SysRole/findSysRole/v1";
		ResponseMsg<List<SysRole>> responseMsg = RestClient.doPost(url, filter, new GenericType() {
		});
		if (responseMsg.getRetCode().equals("200")) {
			return (List) responseMsg.getResponseBody();
		}
		throw new RuntimeException(responseMsg.getErrorDesc());
	}

	public Paging<SysRole> findPage(Paging<SysRole> page, String hql, String value) {
		String url = RestClientConfig.getRestHost("sysuser") + "/api/platform6/sysorguser/SysRole/findSysRole/v1";
		Muti3Bean<Paging<SysRole>, String, String> mb = new Muti3Bean(page, hql, value);
		ResponseMsg<Paging<SysRole>> responseMsg = RestClient.doPost(url, mb, new GenericType() {
		});
		if (responseMsg.getRetCode().equals("200")) {
			return (Paging) responseMsg.getResponseBody();
		}
		throw new RuntimeException(responseMsg.getErrorDesc());
	}
}
