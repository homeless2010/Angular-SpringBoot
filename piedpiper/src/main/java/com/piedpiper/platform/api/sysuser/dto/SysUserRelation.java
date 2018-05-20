package com.piedpiper.platform.api.sysuser.dto;

import java.util.HashMap;
import java.util.Map;

import com.piedpiper.platform.core.domain.BeanDTO;
import com.piedpiper.platform.core.properties.PlatformConstant;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheBean;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheConf;

public class SysUserRelation extends BeanDTO implements BaseCacheBean {
	public static final String USERRELATION = "PLATFORM6_USERRELATION";
	public static final String USERRELATIONUNIT = "PLATFORM6_USERRELATIONUNIT";
	public static final String USER1_USER2 = "PLATFORM6_USER1_USER2_";
	public static final String USER2_USER1 = "PLATFORM6_USER2_USER1_";
	private static final long serialVersionUID = 1L;
	private String id;
	private String user1Id;
	private String relation;
	private String orgId;
	private String user2Id;
	private String validFlag;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser1Id() {
		return this.user1Id;
	}

	public void setUser1Id(String user1Id) {
		this.user1Id = user1Id;
	}

	public String getRelation() {
		return this.relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getUser2Id() {
		return this.user2Id;
	}

	public void setUser2Id(String user2Id) {
		this.user2Id = user2Id;
	}

	public String getValidFlag() {
		return this.validFlag;
	}

	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
	}

	public String getLogFormName() {
		return null;
	}

	public String getLogTitle() {
		return null;
	}

	public PlatformConstant.LogType getLogType() {
		return null;
	}

	public Map<String, ?> returnCacheKey() {
		Map<String, Object> map = new HashMap();
		map.put("PLATFORM6_USERRELATION", this.id);
		map.put("PLATFORM6_USERRELATIONUNIT", this.user1Id + "_" + this.user2Id);
		map.put("PLATFORM6_USER1_USER2_" + this.relation + "_" + this.user1Id,
				new BaseCacheConf("PLATFORM6_USER", this.user2Id));
		map.put("PLATFORM6_USER2_USER1_" + this.relation + "_" + this.user2Id,
				new BaseCacheConf("PLATFORM6_USER", this.user1Id));
		return map;
	}

	public String prefix() {
		return "PLATFORM6_USERRELATION";
	}

	public String returnValidFlag() {
		return this.validFlag;
	}

	public String returnId() {
		return this.id;
	}

	public String returnAppId() {
		return null;
	}
}
