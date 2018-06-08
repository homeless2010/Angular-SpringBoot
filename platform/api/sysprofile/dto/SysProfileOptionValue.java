package com.piedpiper.platform.api.sysprofile.dto;

import java.util.HashMap;
import java.util.Map;

import com.piedpiper.platform.core.domain.BeanBase;
import com.piedpiper.platform.core.properties.PlatformConstant;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheBean;

public class SysProfileOptionValue extends BeanBase implements BaseCacheBean {
	public static final String PROFILEOPTIONVALUE = "PLATFORM6_PROFILEOPTIONVALUE";
	public static final String PROFILEOPTIONSID = "PLATFORM6_PROFILEOPTIONSID_";
	private static final long serialVersionUID = 1L;
	private String id;
	private String sysProfileOptionsId;
	private String profileLevelCode;
	private String levelValue;
	private String profileOptionValue;
	private String levelName;
	private String allocationName;
	private String profileOptionName;
	private SysProfileOption sysProfileOption;
	private static final String pattern = "[id=%s],[配置id=%s],[级别编号=%s],[级别值=%s][值=%s]";

	public SysProfileOption getSysProfileOption() {
		return this.sysProfileOption;
	}

	public void setSysProfileOption(SysProfileOption sysProfileOption) {
		this.sysProfileOption = sysProfileOption;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSysProfileOptionsId() {
		return this.sysProfileOptionsId;
	}

	public void setSysProfileOptionsId(String sysProfileOptionsId) {
		this.sysProfileOptionsId = sysProfileOptionsId;
	}

	public String getProfileLevelCode() {
		return this.profileLevelCode;
	}

	public void setProfileLevelCode(String profileLevelCode) {
		this.profileLevelCode = profileLevelCode;
	}

	public String getLevelValue() {
		return this.levelValue;
	}

	public void setLevelValue(String levelValue) {
		this.levelValue = levelValue;
	}

	public String getProfileOptionValue() {
		return this.profileOptionValue;
	}

	public void setProfileOptionValue(String profileOptionValue) {
		this.profileOptionValue = profileOptionValue;
	}

	public String getLevelName() {
		return this.levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getAllocationName() {
		return this.allocationName;
	}

	public void setAllocationName(String allocationName) {
		this.allocationName = allocationName;
	}

	public String getProfileOptionName() {
		return this.profileOptionName;
	}

	public void setProfileOptionName(String profileOptionName) {
		this.profileOptionName = profileOptionName;
	}

	public String getLogFormName() {
		if ((this.logFormName == null) || (this.logFormName.equals(""))) {
			return "系统参数配置";
		}
		return this.logFormName;
	}

	public String getLogTitle() {
		if ((this.logTitle == null) || (this.logTitle.equals(""))) {
			return "SYSPROFILEOPTIONVALUE";
		}
		return this.logTitle;
	}

	public PlatformConstant.LogType getLogType() {
		if ((this.logType == null) || (this.logType.equals(""))) {
			return PlatformConstant.LogType.system_manage;
		}
		return this.logType;
	}

	public Map<String, ?> returnCacheKey() {
		Map<String, Object> map = new HashMap();
		map.put("PLATFORM6_PROFILEOPTIONVALUE", this.id);
		map.put("PLATFORM6_PROFILEOPTIONSID_" + this.sysProfileOptionsId, this.id);
		return map;
	}

	public String prefix() {
		return "PLATFORM6_PROFILEOPTIONVALUE";
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
		return String.format("[id=%s],[配置id=%s],[级别编号=%s],[级别值=%s][值=%s]", new Object[] { this.id,
				this.sysProfileOptionsId, this.profileLevelCode, this.levelValue, this.profileOptionValue });
	}

	public String returnKey() {
		return this.id;
	}
}
