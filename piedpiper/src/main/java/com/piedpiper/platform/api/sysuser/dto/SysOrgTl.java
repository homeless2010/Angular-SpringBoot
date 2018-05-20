package com.piedpiper.platform.api.sysuser.dto;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.piedpiper.platform.core.domain.BeanDTO;
import com.piedpiper.platform.core.properties.PlatformConstant;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheBean;

public class SysOrgTl extends BeanDTO implements BaseCacheBean {
	public static final String ORGTLID = "PLATFORM6_ORGTLID";
	public static final String ORGTL = "PLATFORM6_ORGTL";
	private static final long serialVersionUID = 1L;
	private String id;
	private String sysOrgId;
	private String sysLanguageCode;
	private String orgName;
	private String orgDesc;
	private String orgPlace;
	private static final String pattern = "[id=%s],[组织名称=%s],[组织描述=%s],[组织id=%s],[语言代码=%s]";

	@Id
	@Column(name = "ID", nullable = false, length = 50)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "SYS_ORG_ID", nullable = false, length = 50)
	public String getSysOrgId() {
		return this.sysOrgId;
	}

	public void setSysOrgId(String sysOrgId) {
		this.sysOrgId = sysOrgId;
	}

	@Column(name = "SYS_LANGUAGE_CODE", nullable = false, length = 50)
	public String getSysLanguageCode() {
		return this.sysLanguageCode;
	}

	public void setSysLanguageCode(String sysLanguageCode) {
		this.sysLanguageCode = sysLanguageCode;
	}

	@Column(name = "ORG_NAME", nullable = false, length = 100)
	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@Column(name = "ORG_DESC")
	public String getOrgDesc() {
		return this.orgDesc;
	}

	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}

	@Column(name = "ORG_PLACE")
	public String getOrgPlace() {
		return this.orgPlace;
	}

	public void setOrgPlace(String orgPlace) {
		this.orgPlace = orgPlace;
	}

	@Transient
	public String getLogFormName() {
		if ((this.logFormName == null) || (this.logFormName.equals(""))) {
			return "测试模块";
		}
		return this.logFormName;
	}

	@Transient
	public String getLogTitle() {
		if ((this.logTitle == null) || (this.logTitle.equals(""))) {
			return "SYSORGTL";
		}
		return this.logTitle;
	}

	@Transient
	public PlatformConstant.LogType getLogType() {
		if ((this.logType == null) || (this.logType.equals(""))) {
			return PlatformConstant.LogType.system_manage;
		}
		return this.logType;
	}

	public Map<String, ?> returnCacheKey() {
		Map<String, Object> map = new HashMap();
		map.put("PLATFORM6_ORGTLID", this.id);
		map.put("PLATFORM6_ORGTL", this.sysOrgId + "_" + this.sysLanguageCode);
		return map;
	}

	public String prefix() {
		return "PLATFORM6_ORGTLID";
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
		return String.format("[id=%s],[组织名称=%s],[组织描述=%s],[组织id=%s],[语言代码=%s]",
				new Object[] { this.id, this.orgName, this.orgDesc, this.sysOrgId, this.sysLanguageCode });
	}

	public String returnKey() {
		return this.id;
	}
}
