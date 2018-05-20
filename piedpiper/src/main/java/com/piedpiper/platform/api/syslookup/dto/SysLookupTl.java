package com.piedpiper.platform.api.syslookup.dto;

import java.util.HashMap;
import java.util.Map;

import com.piedpiper.platform.core.domain.BeanDTO;
import com.piedpiper.platform.core.properties.PlatformConstant;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheBean;

public class SysLookupTl extends BeanDTO implements BaseCacheBean {
	private static final long serialVersionUID = 1L;
	public static final String LOOKUPTL = "PLATFORM6_LOOKUPTL";
	public static final String LOOKUPTl_TYPE = "PLATFORM6_LOOKUPTl_TYPE_";
	private String id;
	private String sysLookupId;
	private String sysLanguageCode;
	private String lookupName;
	private String description;
	private String sysLanguageName;
	private SysLookup sysLookup;
	private static final String pattern = "[id=%s],[代码id=%s],[名称=%s],[描述=%s],[语言代码=%s]";

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSysLanguageName() {
		return this.sysLanguageName;
	}

	public void setSysLanguageName(String sysLanguageName) {
		this.sysLanguageName = sysLanguageName;
	}

	public String getSysLookupId() {
		return this.sysLookupId;
	}

	public void setSysLookupId(String sysLookupId) {
		this.sysLookupId = sysLookupId;
	}

	public SysLookup getSysLookup() {
		return this.sysLookup;
	}

	public void setSysLookup(SysLookup sysLookup) {
		this.sysLookup = sysLookup;
	}

	public String getSysLanguageCode() {
		return this.sysLanguageCode;
	}

	public void setSysLanguageCode(String sysLanguageCode) {
		this.sysLanguageCode = sysLanguageCode;
	}

	public String getLookupName() {
		return this.lookupName;
	}

	public void setLookupName(String lookupName) {
		this.lookupName = lookupName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLogFormName() {
		if ((this.logFormName == null) || (this.logFormName.equals(""))) {
			return "通用代码管理";
		}
		return this.logFormName;
	}

	public String getLogTitle() {
		if ((this.logTitle == null) || (this.logTitle.equals(""))) {
			return "SYSLOOKUPTL";
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
		map.put("PLATFORM6_LOOKUPTL", this.id);
		map.put("PLATFORM6_LOOKUPTl_TYPE_" + this.sysLookupId, this.id);
		return map;
	}

	public String prefix() {
		return "PLATFORM6_LOOKUPTL";
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
		return String.format("[id=%s],[代码id=%s],[名称=%s],[描述=%s],[语言代码=%s]",
				new Object[] { this.id, this.sysLookupId, this.lookupName, this.description, this.sysLanguageCode });
	}

	public String returnKey() {
		return this.id;
	}
}
