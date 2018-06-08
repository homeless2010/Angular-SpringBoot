package com.piedpiper.platform.api.sysuser.dto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.piedpiper.platform.core.domain.BeanDTO;
import com.piedpiper.platform.core.properties.PlatformConstant;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheBean;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheConf;

public class SysUserGroup extends BeanDTO implements BaseCacheBean {
	public static final String USERGROUPID = "PLATFORM6_USERGROUPID";
	public static final String USER_GROUP = "PLATFORM6_USER_GROUP_";
	public static final String GROUP_USER = "PLATFORM6_GROUP_USER_";
	private static final long serialVersionUID = 1L;
	private String id;
	private String groupId;
	private String userId;
	private BigDecimal orderBy;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGroupId() {
		return this.groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public BigDecimal getOrderBy() {
		return this.orderBy;
	}

	public void setOrderBy(BigDecimal orderBy) {
		this.orderBy = orderBy;
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
		map.put("PLATFORM6_USERGROUPID", this.id);
		map.put("PLATFORM6_USER_GROUP_" + this.userId, new BaseCacheConf("PLATFORM6_GROUP", this.groupId));
		map.put("PLATFORM6_GROUP_USER_" + this.groupId, new BaseCacheConf("PLATFORM6_USER", this.userId));
		return map;
	}

	public String prefix() {
		return "PLATFORM6_USERGROUPID";
	}

	public String returnValidFlag() {
		return null;
	}

	public String returnId() {
		return this.id;
	}

	public String returnAppId() {
		return null;
	}
}
