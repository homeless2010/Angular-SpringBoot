package com.piedpiper.platform.api.sysuser.dto;

import java.util.HashMap;
import java.util.Map;

import com.piedpiper.platform.core.domain.BeanDTO;
import com.piedpiper.platform.core.properties.PlatformConstant;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheBean;

public class SysGroupTl extends BeanDTO implements BaseCacheBean {
	public static final String GROUPTLID = "PLATFORM6_GROUPTLID";
	public static final String GROUPTL = "PLATFORM6_GROUPTL";
	private static final long serialVersionUID = 1L;
	private String id;
	private String sysGroupId;
	private String sysLanguageCode;
	private String groupName;
	private String groupDesc;
	private static final String pattern = "[id=%s],[群组名称=%s],[群组描述=%s],[群组id=%s],[语言代码=%s]";

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSysGroupId() {
		return this.sysGroupId;
	}

	public void setSysGroupId(String sysGroupId) {
		this.sysGroupId = sysGroupId;
	}

	public String getSysLanguageCode() {
		return this.sysLanguageCode;
	}

	public void setSysLanguageCode(String sysLanguageCode) {
		this.sysLanguageCode = sysLanguageCode;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupDesc() {
		return this.groupDesc;
	}

	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
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
		map.put("PLATFORM6_GROUPTLID", this.id);
		map.put("PLATFORM6_GROUPTL", this.sysGroupId + "_" + this.sysLanguageCode);
		return map;
	}

	public String prefix() {
		return "PLATFORM6_GROUPTLID";
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
		return String.format("[id=%s],[群组名称=%s],[群组描述=%s],[群组id=%s],[语言代码=%s]",
				new Object[] { this.id, this.groupName, this.groupDesc, this.sysGroupId, this.sysLanguageCode });
	}

	public String returnKey() {
		return this.id;
	}
}
