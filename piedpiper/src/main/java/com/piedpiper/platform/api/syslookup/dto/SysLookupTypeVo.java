package com.piedpiper.platform.api.syslookup.dto;

@Deprecated
public class SysLookupTypeVo {
	private String id;
	private String sysApplicationId;
	private String lookupType;
	private String systemFlag;
	private String validFlag;
	private String sysLookupTypeTlId;
	private String sysLookupTypeId;
	private String sysLanguageCode;
	private String lookupTypeName;
	private String description;

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

	public String getLookupType() {
		return this.lookupType;
	}

	public void setLookupType(String lookupType) {
		this.lookupType = lookupType;
	}

	public String getSystemFlag() {
		return this.systemFlag;
	}

	public void setSystemFlag(String systemFlag) {
		this.systemFlag = systemFlag;
	}

	public String getValidFlag() {
		return this.validFlag;
	}

	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
	}

	public String getSysLookupTypeTlId() {
		return this.sysLookupTypeTlId;
	}

	public void setSysLookupTypeTlId(String sysLookupTypeTlId) {
		this.sysLookupTypeTlId = sysLookupTypeTlId;
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
}
