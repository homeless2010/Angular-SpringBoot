package com.piedpiper.platform.api.sysresource.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.piedpiper.platform.core.domain.BeanDTO;
import com.piedpiper.platform.core.properties.PlatformConstant;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheBean;

public class SysResource extends BeanDTO implements Serializable, BaseCacheBean {
	public static final String SYSRESOURCE = "PLATFORM6_SYSRESOURCE";
	public static final String SYSRESOURCE_APPID = "PLATFORM6_SYSRESOURCE_APPID_";
	private static final long serialVersionUID = 6913676953456951429L;
	private String id;
	private String key;
	private String value;
	private String type;
	private String desc;
	private String parentId;
	private String sysApplicationId;
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

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
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

	public String returnId() {
		return this.id;
	}

	public String returnValidFlag() {
		return null;
	}

	public Map<String, ?> returnCacheKey() {
		Map<String, Object> map = new HashMap();
		map.put("PLATFORM6_SYSRESOURCE", this.id);
		map.put("PLATFORM6_SYSRESOURCE_APPID_" + this.sysApplicationId, this.value);
		return map;
	}

	public String prefix() {
		return "PLATFORM6_SYSRESOURCE";
	}

	public String returnAppId() {
		return this.sysApplicationId;
	}
}
