package com.piedpiper.platform.api.syslookup.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.piedpiper.platform.core.domain.BeanDTO;
import com.piedpiper.platform.core.properties.PlatformConstant;

public class SysLookupSimpleVo extends BeanDTO {
	private static final long serialVersionUID = 5474146773215921275L;
	private String lookupCode;
	private String lookupTypeName;
	private String lookupType;
	private String lookupName;
	private BigDecimal displayOrder;
	private String lookupDes;
	private String sysLookupTlId;
	@JsonIgnore
	protected String createdBy;
	@JsonIgnore
	protected Date creationDate;
	@JsonIgnore
	protected Date lastUpdateDate;
	@JsonIgnore
	protected String lastUpdatedBy;
	@JsonIgnore
	protected String lastUpdateIp;
	@JsonIgnore
	protected Long version;

	public String getLookupCode() {
		return this.lookupCode;
	}

	public void setLookupCode(String lookupCode) {
		this.lookupCode = lookupCode;
	}

	public String getLookupTypeName() {
		return this.lookupTypeName;
	}

	public void setLookupTypeName(String lookupTypeName) {
		this.lookupTypeName = lookupTypeName;
	}

	public String getSysLookupTlId() {
		return this.sysLookupTlId;
	}

	public void setSysLookupTlId(String sysLookupTlId) {
		this.sysLookupTlId = sysLookupTlId;
	}

	public String getLookupType() {
		return this.lookupType;
	}

	public void setLookupType(String lookupType) {
		this.lookupType = lookupType;
	}

	public String getLookupName() {
		return this.lookupName;
	}

	public void setLookupName(String lookupName) {
		this.lookupName = lookupName;
	}

	public String getLookupDes() {
		return this.lookupDes;
	}

	public void setLookupDes(String lookupDes) {
		this.lookupDes = lookupDes;
	}

	public BigDecimal getDisplayOrder() {
		return this.displayOrder;
	}

	public void setDisplayOrder(BigDecimal displayOrder) {
		this.displayOrder = displayOrder;
	}

	@JsonIgnore
	public String getLogFormName() {
		return null;
	}

	@JsonIgnore
	public String getLogTitle() {
		return null;
	}

	@JsonIgnore
	public PlatformConstant.LogType getLogType() {
		return null;
	}
}
