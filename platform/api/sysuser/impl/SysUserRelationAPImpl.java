package com.piedpiper.platform.api.sysuser.impl;

import com.piedpiper.platform.api.sysuser.SysUserRelationAPI;
import com.piedpiper.platform.api.sysuser.dto.SysUser;
import com.piedpiper.platform.api.sysuser.dto.SysUserRelation;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheManager;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class SysUserRelationAPImpl implements SysUserRelationAPI {
	@Autowired
	private BaseCacheManager baseCacheManager;

	public List<SysUser> getSysUser2ListByUser1IdAndRelation(String user1Id, String relation, String currentOrgId) {
		return this.baseCacheManager.getAllFromCache("PLATFORM6_USER1_USER2_" + relation + "_" + user1Id,
				new TypeReference() {
				});
	}

	public List<SysUser> getSysUser1ListByUser2IdAndRelation(String user2Id, String relation, String currentOrgId) {
		return this.baseCacheManager.getAllFromCache("PLATFORM6_USER2_USER1_" + relation + "_" + user2Id,
				new TypeReference() {
				});
	}

	public SysUserRelation getSysUserRelationByUser1IdAndUser2Id(String user1Id, String user2Id, String currentOrgId) {
		return (SysUserRelation) this.baseCacheManager.getObjectFromCache("PLATFORM6_USERRELATIONUNIT",
				user1Id + "_" + user2Id, SysUserRelation.class);
	}

	public SysUserRelation getSysUserRelationById(String id) {
		return (SysUserRelation) this.baseCacheManager.getObjectFromCache("PLATFORM6_USERRELATION", id, SysUser.class);
	}
}
