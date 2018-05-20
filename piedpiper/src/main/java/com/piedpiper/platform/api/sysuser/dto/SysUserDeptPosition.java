package com.piedpiper.platform.api.sysuser.dto;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.piedpiper.platform.core.domain.BeanDTO;
import com.piedpiper.platform.core.properties.PlatformConstant;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheBean;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheConf;

public class SysUserDeptPosition extends BeanDTO implements BaseCacheBean {
	public static final String USERDEPTPOSITION = "PLATFORM6_USERDEPTPOSITION";
	public static final String USER_USERDEPTPOSITION = "PLATFORM6_USER_USERDEPTPOSITION_";
	public static final String DEPT_USERDEPTPOSITION = "PLATFORM6_DEPT_USERDEPTPOSITION_";
	public static final String DEPT_USER = "PLATFORM6_DEPT_USER_";
	public static final String POSITION_USER = "PLATFORM6_POSITION_USER_";
	public static final String DEPT_POSITION = "PLATFORM6_DEPT_POSITION_";
	public static final String USER_POSITION = "PLATFORM6_USER_POSITION_";
	public static final String USER_DEPT = "PLATFORM6_USER_DEPT_";
	private static final long serialVersionUID = 1L;
	private String id;
	private String userId;
	private String deptId;
	private String positionId;
	private String isChiefDept;
	private String isManager;
	private Long orderBy;
	private String orgIdentity;

	@JsonIgnore
	public String getOrgIdentity() {
		return this.orgIdentity;
	}

	public void setOrgIdentity(String orgIdentity) {
		this.orgIdentity = orgIdentity;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getPositionId() {
		return this.positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	public String getIsChiefDept() {
		return this.isChiefDept;
	}

	public void setIsChiefDept(String isChiefDept) {
		this.isChiefDept = isChiefDept;
	}

	public String getIsManager() {
		return this.isManager;
	}

	public void setIsManager(String isManager) {
		this.isManager = isManager;
	}

	public Long getOrderBy() {
		return this.orderBy;
	}

	public void setOrderBy(Long orderBy) {
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
		map.put("PLATFORM6_USERDEPTPOSITION", this.id);
		map.put("PLATFORM6_USER_USERDEPTPOSITION_" + this.userId, this.id);
		map.put("PLATFORM6_DEPT_USERDEPTPOSITION_" + this.deptId, this.id);
		map.put("PLATFORM6_DEPT_USER_" + this.deptId, new BaseCacheConf("PLATFORM6_USER", this.userId));
		map.put("PLATFORM6_POSITION_USER_" + this.positionId, new BaseCacheConf("PLATFORM6_USER", this.userId));
		map.put("PLATFORM6_DEPT_POSITION_" + this.deptId + "_" + this.positionId,
				new BaseCacheConf("PLATFORM6_USER", this.userId));
		map.put("PLATFORM6_USER_POSITION_" + this.userId, new BaseCacheConf("PLATFORM6_POSITION", this.positionId));
		map.put("PLATFORM6_USER_DEPT_" + this.userId, new BaseCacheConf("PLATFORM6_DEPT", this.deptId));
		return map;
	}

	public String prefix() {
		return "PLATFORM6_USERDEPTPOSITION";
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
