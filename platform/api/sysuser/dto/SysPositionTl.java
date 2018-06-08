package com.piedpiper.platform.api.sysuser.dto;

import java.util.HashMap;
import java.util.Map;

import com.piedpiper.platform.core.domain.BeanDTO;
import com.piedpiper.platform.core.properties.PlatformConstant;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheBean;

public class SysPositionTl extends BeanDTO implements BaseCacheBean {
	public static final String POSITIONTLID = "PLATFORM6_POSITIONTLID";
	public static final String POSITIONTL = "PLATFORM6_POSITIONTL";
	private static final long serialVersionUID = 1L;
	private String id;
	private String positionDesc;
	private String positionName;
	private String sysPositionId;
	private String sysLanguageCode;
	private static final String pattern = "[id=%s],[岗位名称=%s],[岗位描述=%s],[岗位id=%s],[语言代码=%s]";

	public String getId() {
		return this.id;
	}

	public String getPositionDesc() {
		return this.positionDesc;
	}

	public String getPositionName() {
		return this.positionName;
	}

	public String getSysLanguageCode() {
		return this.sysLanguageCode;
	}

	public String getSysPositionId() {
		return this.sysPositionId;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPositionDesc(String positionDesc) {
		this.positionDesc = positionDesc;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public void setSysLanguageCode(String sysLanguageCode) {
		this.sysLanguageCode = sysLanguageCode;
	}

	public void setSysPositionId(String sysPositionId) {
		this.sysPositionId = sysPositionId;
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
		map.put("PLATFORM6_POSITIONTLID", this.id);
		map.put("PLATFORM6_POSITIONTL", this.sysPositionId + "_" + this.sysLanguageCode);
		return map;
	}

	public String prefix() {
		return "PLATFORM6_POSITIONTLID";
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

	public String toString() {
		return String.format("[id=%s],[岗位名称=%s],[岗位描述=%s],[岗位id=%s],[语言代码=%s]", new Object[] { this.id,
				this.positionName, this.positionName, this.sysPositionId, this.sysLanguageCode });
	}

	public String returnKey() {
		return this.id;
	}
}
