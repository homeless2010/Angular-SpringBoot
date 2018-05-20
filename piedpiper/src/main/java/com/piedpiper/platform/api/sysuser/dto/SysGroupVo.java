package com.piedpiper.platform.api.sysuser.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class SysGroupVo implements Serializable {
	private static final long serialVersionUID = -4508148531491342373L;
	private String id;
	private String groupParentId;
	private String orgId;
	private BigDecimal orderBy;
	private String type;
	private String belongTo;
	private String validFlag;
	private Long version;
	private String applicationId;
	private String applicationName;
	private String sysGroupTlId;
	private String sysGroupId;
	private String sysLanguageCode;
	private String groupName;
	private String groupDesc;
	private Long groupTlVersion;
	private String r;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGroupParentId() {
		return this.groupParentId;
	}

	public void setGroupParentId(String groupParentId) {
		this.groupParentId = groupParentId;
	}

	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public BigDecimal getOrderBy() {
		return this.orderBy;
	}

	public void setOrderBy(BigDecimal orderBy) {
		this.orderBy = orderBy;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBelongTo() {
		return this.belongTo;
	}

	public void setBelongTo(String belongTo) {
		this.belongTo = belongTo;
	}

	public String getValidFlag() {
		return this.validFlag;
	}

	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
	}

	public String getSysGroupTlId() {
		return this.sysGroupTlId;
	}

	public void setSysGroupTlId(String sysGroupTlId) {
		this.sysGroupTlId = sysGroupTlId;
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

	public Long getVersion() {
		return this.version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Long getGroupTlVersion() {
		return this.groupTlVersion;
	}

	public void setGroupTlVersion(Long groupTlVersion) {
		this.groupTlVersion = groupTlVersion;
	}

	public String getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getApplicationName() {
		return this.applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getR() {
		return this.r;
	}

	public void setR(String r) {
		this.r = r;
	}
}
