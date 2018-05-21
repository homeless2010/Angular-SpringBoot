package com.piedpiper.platform.api.sysuser.impl;

import com.piedpiper.platform.api.sysuser.SysUserGroupAPI;
import com.piedpiper.platform.api.sysuser.dto.SysGroup;
import com.piedpiper.platform.api.sysuser.dto.SysUser;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheManager;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class SysUserGroupAPImpl implements SysUserGroupAPI {
	@Autowired
	private BaseCacheManager baseCacheManager;

	public List<SysUser> getSysUserListBySysGroupId(String sysGroupId) {
		return this.baseCacheManager.getAllFromCache("PLATFORM6_GROUP_USER_" + sysGroupId, new TypeReference() {
		});
	}

	public List<SysUser> getSysUserListBySysGroupId(String sysGroupId, int secretLevel) {
		List<SysUser> list = getSysUserListBySysGroupId(sysGroupId);
		List<SysUser> result = new ArrayList();
		for (SysUser user : list) {
			if (user.getSecretLevel().equals(Integer.valueOf(secretLevel))) {
				result.add(user);
			}
		}
		return result;
	}

	public List<SysGroup> getSysGroupListBySysUserId(String sysUserId) {
		return this.baseCacheManager.getAllFromCache("PLATFORM6_USER_GROUP_" + sysUserId, new TypeReference() {
		});
	}
}
