package com.piedpiper.platform.api.sysuser.dto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.piedpiper.platform.core.domain.BeanDTO;
import com.piedpiper.platform.core.properties.PlatformConstant;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheBean;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheConf;

public class SysUserRole extends BeanDTO implements BaseCacheBean {
	public static final String USERROLE = "PLATFORM6_USERROLE";
	public static final String USER_ROLE = "PLATFORM6_USER_ROLE_";
	public static final String ROLE_USER = "PLATFORM6_ROLE_USER_";
	private static final long serialVersionUID = 1L;
	private String id;
	private String sysRoleId;
	private String sysUserId;
	private BigDecimal orderBy;
	private String sysApplicationId;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSysRoleId() {
		return this.sysRoleId;
	}

	public void setSysRoleId(String sysRoleId) {
		this.sysRoleId = sysRoleId;
	}

	public String getSysUserId() {
		return this.sysUserId;
	}

	public void setSysUserId(String sysUserId) {
		this.sysUserId = sysUserId;
	}

	public BigDecimal getOrderBy() {
		return this.orderBy;
	}

	public void setOrderBy(BigDecimal orderBy) {
		this.orderBy = orderBy;
	}

	public String getSysApplicationId() {
		return this.sysApplicationId;
	}

	public void setSysApplicationId(String sysApplicationId) {
		this.sysApplicationId = sysApplicationId;
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
		map.put("PLATFORM6_USERROLE", this.id);
		map.put("PLATFORM6_USER_ROLE_" + this.sysUserId, new BaseCacheConf("PLATFORM6_ROLE", this.sysRoleId));
		map.put("PLATFORM6_ROLE_USER_" + this.sysRoleId, new BaseCacheConf("PLATFORM6_USER", this.sysUserId));
		return map;
	}

	public String prefix() {
		return "PLATFORM6_USERROLE";
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
