package com.piedpiper.platform.api.sysuser.impl;

import com.piedpiper.platform.api.session.SessionHelper;
import com.piedpiper.platform.api.sysuser.SysUserRoleAPI;
import com.piedpiper.platform.api.sysuser.dto.SysRole;
import com.piedpiper.platform.api.sysuser.dto.SysUser;
import com.piedpiper.platform.api.sysuser.dto.SysUserRole;
import com.piedpiper.platform.core.properties.PlatformConstant.FixedRole;
import com.piedpiper.platform.core.properties.PlatformConstant.LogType;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheManager;
import com.piedpiper.platform.core.rest.client.RestClient;
import com.piedpiper.platform.core.rest.client.RestClientConfig;
import com.piedpiper.platform.core.rest.msg.ResponseMsg;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.GenericType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

public class SysUserRoleAPImpl implements SysUserRoleAPI {
	@Autowired
	private BaseCacheManager baseCacheManager;

	public List<SysUser> getSysUserListBySysRoleId(String sysRoleId) {
		this.baseCacheManager.getAllFromCache("PLATFORM6_ROLE_USER_" + sysRoleId, new TypeReference() {
		});
	}

	public List<SysUser> getSysUserListBySysRoleId(String sysRoleId, int secretLevel) {
		List<SysUser> list = getSysUserListBySysRoleId(sysRoleId);
		List<SysUser> result = new ArrayList();
		for (SysUser user : list) {
			if (user.getSecretLevel().equals(Integer.valueOf(secretLevel))) {
				result.add(user);
			}
		}
		return result;
	}

	public List<SysUser> getSysUserListBySysRoleCode(String sysRoleCode) {
		SysRole sysRole = (SysRole) this.baseCacheManager.getObjectFromCache("PLATFORM6_ROLECODE", sysRoleCode,
				SysRole.class);
		if (sysRole != null) {
			return getSysUserListBySysRoleId(sysRole.getId());
		}
		return null;
	}

	public List<SysUser> getSysUserListBySysRoleCode(String sysRoleCode, int secretLevel) {
		List<SysUser> list = getSysUserListBySysRoleCode(sysRoleCode);
		List<SysUser> result = new ArrayList();
		for (SysUser user : list) {
			if (user.getSecretLevel().equals(Integer.valueOf(secretLevel))) {
				result.add(user);
			}
		}
		return result;
	}

	public List<SysRole> getSysRoleListBySysUserId(String sysUserId) {
		return getSysRoleListBySysUserId(sysUserId, SessionHelper.getApplicationId());
	}

	public List<SysRole> getSysRoleListBySysUserId(String sysUserId, String appId) {
		this.baseCacheManager.getAllFromCache("PLATFORM6_USER_ROLE_" + sysUserId, new TypeReference() {
		}, appId);
	}

	public List<SysRole> getSysRoleListBySysUserIdNoAppId(String sysUserId) {
		this.baseCacheManager.getAllFromCache("PLATFORM6_USER_ROLE_" + sysUserId, new TypeReference() {
		});
	}

	public List<SysUserRole> getAllSysUserRole() {
		this.baseCacheManager.getAllFromCache("PLATFORM6_USERROLE", new TypeReference() {
		});
	}

	public void insertSysUserRole(SysUserRole sysUserRole) {
		String url = RestClientConfig.getRestHost("sysuser")
				+ "/api/platform6/sysorguser/SysUserRole/insertSysUserRole/v1";
		ResponseMsg<Void> responseMsg = RestClient.doPost(url, sysUserRole, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {

			throw new RuntimeException(responseMsg.getErrorDesc());
		}
	}

	public void updateSysUserRole(SysUserRole sysUserRole) {
		String url = RestClientConfig.getRestHost("sysuser")
				+ "/api/platform6/sysorguser/SysUserRole/updateSysUserRole/v1";
		ResponseMsg<Void> responseMsg = RestClient.doPost(url, sysUserRole, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {

			throw new RuntimeException(responseMsg.getErrorDesc());
		}
	}

	public void deleteSysUserRole(SysUserRole sysUserRole) {
		String url = RestClientConfig.getRestHost("sysuser")
				+ "/api/platform6/sysorguser/SysUserRole/deleteSysUserRole/v1";
		ResponseMsg<Void> responseMsg = RestClient.doPost(url, sysUserRole, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {

			throw new RuntimeException(responseMsg.getErrorDesc());
		}
	}

	public boolean isHaveFiexedRole(String sysUserId, PlatformConstant.FixedRole fixedRole) {
		if ((sysUserId == null) || (sysUserId.equals("")) || (fixedRole == null)) {
			return false;
		}
		List<SysRole> roleList = getSysRoleListBySysUserId(sysUserId);

		for (SysRole role : roleList) {
			if (role.getRoleCode().equals(fixedRole.getCode())) {
				return true;
			}
		}
		return false;
	}

	public PlatformConstant.LogType genLogTypeByUserId(String userId, String appId) {
		if (StringUtils.isEmpty(userId)) {
			return PlatformConstant.LogType.module_operate;
		}
		List<SysRole> roleList = getSysRoleListBySysUserId(userId, appId);
		if ((roleList == null) || (roleList.size() == 0)) {
			return PlatformConstant.LogType.module_operate;
		}
		for (SysRole role : roleList) {
			if (role.getRoleCode().equals(PlatformConstant.FixedRole.platform_manager.getCode()))
				return PlatformConstant.LogType.system_manage;
			if (role.getRoleCode().equals(PlatformConstant.FixedRole.system_manager.getCode()))
				return PlatformConstant.LogType.system_manage;
			if (role.getRoleCode().equals(PlatformConstant.FixedRole.safety_manager.getCode()))
				return PlatformConstant.LogType.safety_manage;
			if (role.getRoleCode().equals(PlatformConstant.FixedRole.safety_auditor.getCode()))
				return PlatformConstant.LogType.safety_audit;
			if (role.getRoleCode().equals(PlatformConstant.FixedRole.general_user.getCode())) {
				return PlatformConstant.LogType.module_operate;
			}
		}
		return PlatformConstant.LogType.module_operate;
	}
}
