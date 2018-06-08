package com.piedpiper.platform.api.syspassword.dto;

import java.util.HashMap;
import java.util.Map;

import com.piedpiper.platform.core.domain.BeanDTO;
import com.piedpiper.platform.core.properties.PlatformConstant;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheBean;

public class SysPasswordTempletLevel extends BeanDTO implements BaseCacheBean {
	public static final String PASSWORDTEMPLETLEVEL = "PLATFORM6_PASSWORDTEMPLETLEVEL";
	public static final String PASSWORDTEMPLETLEVEL_CODE = "PLATFORM6_PASSWORDTEMPLETLEVEL_CODE_";
	private static final long serialVersionUID = 1L;
	private String id;
	private String sysApplicationId;
	private String key;
	private String code;
	private String userLevelCode;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSysApplicationId() {
		return this.sysApplicationId;
	}

	public void setSysApplicationId(String sysApplicationId) {
		this.sysApplicationId = sysApplicationId;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUserLevelCode() {
		return this.userLevelCode;
	}

	public void setUserLevelCode(String userLevelCode) {
		this.userLevelCode = userLevelCode;
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

	public String returnId() {
		return this.id;
	}

	public String returnValidFlag() {
		return null;
	}

	public Map<String, ?> returnCacheKey() {
		Map<String, Object> map = new HashMap();
		map.put("PLATFORM6_PASSWORDTEMPLETLEVEL", this.id);
		map.put("PLATFORM6_PASSWORDTEMPLETLEVEL_CODE_" + this.userLevelCode, this.id);
		return map;
	}

	public String prefix() {
		return "PLATFORM6_PASSWORDTEMPLETLEVEL";
	}

	public String returnAppId() {
		return this.sysApplicationId;
	}
}
