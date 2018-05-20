package com.piedpiper.platform.api.syslookup.dto;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.piedpiper.platform.core.domain.BeanDTO;
import com.piedpiper.platform.core.properties.PlatformConstant;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheBean;

public class SysLookupTypeTl extends BeanDTO implements BaseCacheBean {
	private static final long serialVersionUID = 1L;
	public static final String LOOKUPTYPETL = "PLATFORM6_LOOKUPTYPETL";
	private String id;
	private String sysLookupTypeId;
	private String sysLanguageCode;
	private String lookupTypeName;
	private String description;
	private String sysLanguageName;
	@JsonIgnore
	private SysLookupType sysLookupType;
	private static final String pattern = "[id=%s],[通用代码id=%s],[名称=%s],[描述=%s],[语言代码=%s]";

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

	public SysLookupType getSysLookupType() {
		return this.sysLookupType;
	}

	public void setSysLookupType(SysLookupType sysLookupType) {
		this.sysLookupType = sysLookupType;
	}

	public String getSysLookupTypeId() {
		return this.sysLookupTypeId;
	}

	public void setSysLookupTypeId(String sysLookupTypeId) {
		this.sysLookupTypeId = sysLookupTypeId;
	}

	public String getSysLanguageCode() {
		return this.sysLanguageCode;
	}

	public void setSysLanguageCode(String sysLanguageCode) {
		this.sysLanguageCode = sysLanguageCode;
	}

	public String getLookupTypeName() {
		return this.lookupTypeName;
	}

	public void setLookupTypeName(String lookupTypeName) {
		this.lookupTypeName = lookupTypeName;
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
			return "SYSLOOKUPTYPETL";
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
		map.put("PLATFORM6_LOOKUPTYPETL", this.id);
		return map;
	}

	public String prefix() {
		return "PLATFORM6_LOOKUPTYPETL";
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
		return String.format("[id=%s],[通用代码id=%s],[名称=%s],[描述=%s],[语言代码=%s]", new Object[] { this.id,
				this.sysLookupTypeId, this.lookupTypeName, this.description, this.sysLanguageCode });
	}

	public String returnKey() {
		return this.id;
	}
}
