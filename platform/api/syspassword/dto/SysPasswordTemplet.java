package com.piedpiper.platform.api.syspassword.dto;

import java.util.HashMap;
import java.util.Map;

import com.piedpiper.platform.core.domain.BeanDTO;
import com.piedpiper.platform.core.properties.PlatformConstant;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheBean;

public class SysPasswordTemplet extends BeanDTO implements BaseCacheBean {
	public static final String PASSWORDTEMPLET = "PLATFORM6_PASSWORDTEMPLET";
	public static final String PASSWORDTEMPLET_TYPE_KEY = "PLATFORM6_PASSWORDTEMPLET_TYPE_KEY_";
	public static final String PASSWORDTEMPLET_TYPE = "PLATFORM6_PASSWORDTEMPLET_TYPE_";
	private static final long serialVersionUID = 1L;
	private String id;
	private String templetKey;
	private String templetValue;
	private String templetState;
	private String templetType;
	private String templetKeyDesc;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTempletKey() {
		return this.templetKey;
	}

	public void setTempletKey(String templetKey) {
		this.templetKey = templetKey;
	}

	public String getTempletValue() {
		return this.templetValue;
	}

	public void setTempletValue(String templetValue) {
		this.templetValue = templetValue;
	}

	public String getTempletState() {
		return this.templetState;
	}

	public void setTempletState(String templetState) {
		this.templetState = templetState;
	}

	public String getTempletType() {
		return this.templetType;
	}

	public void setTempletType(String templetType) {
		this.templetType = templetType;
	}

	public String getTempletKeyDesc() {
		return this.templetKeyDesc;
	}

	public void setTempletKeyDesc(String templetKeyDesc) {
		this.templetKeyDesc = templetKeyDesc;
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
		return this.templetState;
	}

	public Map<String, ?> returnCacheKey() {
		Map<String, Object> map = new HashMap();
		map.put("PLATFORM6_PASSWORDTEMPLET", this.id);
		map.put("PLATFORM6_PASSWORDTEMPLET_TYPE_KEY_" + this.templetType + "_" + this.templetKey, this.id);
		map.put("PLATFORM6_PASSWORDTEMPLET_TYPE_" + this.templetType, this.id);
		return map;
	}

	public String prefix() {
		return "PLATFORM6_PASSWORDTEMPLET";
	}

	public String returnAppId() {
		return null;
	}
}
