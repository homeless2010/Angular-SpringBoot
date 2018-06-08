package com.piedpiper.platform.api.sysuser.dto;

import java.math.BigDecimal;

public class SysPositionVo {
	private String id;
	private String orgId;
	private String positionCode;
	private BigDecimal orderBy;
	private String positionDesc;
	private String positionName;
	private String sysLanguageCode;
	private String sysPositionId;
	private String tlId;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getPositionCode() {
		return this.positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	public BigDecimal getOrderBy() {
		return this.orderBy;
	}

	public void setOrderBy(BigDecimal orderBy) {
		this.orderBy = orderBy;
	}

	public String getTlId() {
		return this.tlId;
	}

	public void setTlId(String tlId) {
		this.tlId = tlId;
	}

	public String getPositionDesc() {
		return this.positionDesc;
	}

	public void setPositionDesc(String positionDesc) {
		this.positionDesc = positionDesc;
	}

	public String getPositionName() {
		return this.positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getSysLanguageCode() {
		return this.sysLanguageCode;
	}

	public void setSysLanguageCode(String sysLanguageCode) {
		this.sysLanguageCode = sysLanguageCode;
	}

	public String getSysPositionId() {
		return this.sysPositionId;
	}

	public void setSysPositionId(String sysPositionId) {
		this.sysPositionId = sysPositionId;
	}
}
