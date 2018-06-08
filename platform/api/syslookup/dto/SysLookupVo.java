package com.piedpiper.platform.api.syslookup.dto;

import java.math.BigDecimal;

@Deprecated
public class SysLookupVo {
	private String id;
	private String sysLookupTypeId;
	private BigDecimal displayOrder;
	private String lookupCode;
	private String validFlag;
	private String systemFlag;
	private String sysLooupTlId;
	private String sysLookupId;
	private String sysLanguageCode;
	private String lookupName;
	private String description;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSysLookupTypeId() {
		return this.sysLookupTypeId;
	}

	public void setSysLookupTypeId(String sysLookupTypeId) {
		this.sysLookupTypeId = sysLookupTypeId;
	}

	public BigDecimal getDisplayOrder() {
		return this.displayOrder;
	}

	public void setDisplayOrder(BigDecimal displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getLookupCode() {
		return this.lookupCode;
	}

	public void setLookupCode(String lookupCode) {
		this.lookupCode = lookupCode;
	}

	public String getValidFlag() {
		return this.validFlag;
	}

	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
	}

	public String getSystemFlag() {
		return this.systemFlag;
	}

	public void setSystemFlag(String systemFlag) {
		this.systemFlag = systemFlag;
	}

	public String getSysLooupTlId() {
		return this.sysLooupTlId;
	}

	public void setSysLooupTlId(String sysLooupTlId) {
		this.sysLooupTlId = sysLooupTlId;
	}

	public String getSysLookupId() {
		return this.sysLookupId;
	}

	public void setSysLookupId(String sysLookupId) {
		this.sysLookupId = sysLookupId;
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
}
