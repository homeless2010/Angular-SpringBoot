package com.piedpiper.platform.api.sysuser.dto;

import java.io.Serializable;
import java.util.Date;

public class SysUserRelationVo implements Serializable {
	private static final long serialVersionUID = 8984589993400641950L;
	private String id;
	private String user1Id;
	private String user1No;
	private String user1Name;
	private String relation;
	private String orgId;
	private String orgCode;
	private String user2Id;
	private String user2No;
	private String user2Name;
	private String validFlag;
	private String createdBy;
	private Date creationDate;
	private String lastUpdatedBy;
	private Date lastUpdateDate;
	private String lastUpdateIp;
	private Long version;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser1Id() {
		return this.user1Id;
	}

	public void setUser1Id(String user1Id) {
		this.user1Id = user1Id;
	}

	public String getUser1No() {
		return this.user1No;
	}

	public void setUser1No(String user1No) {
		this.user1No = user1No;
	}

	public String getUser1Name() {
		return this.user1Name;
	}

	public void setUser1Name(String user1Name) {
		this.user1Name = user1Name;
	}

	public String getRelation() {
		return this.relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getUser2Id() {
		return this.user2Id;
	}

	public void setUser2Id(String user2Id) {
		this.user2Id = user2Id;
	}

	public String getUser2No() {
		return this.user2No;
	}

	public void setUser2No(String user2No) {
		this.user2No = user2No;
	}

	public String getUser2Name() {
		return this.user2Name;
	}

	public void setUser2Name(String user2Name) {
		this.user2Name = user2Name;
	}

	public String getValidFlag() {
		return this.validFlag;
	}

	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getLastUpdatedBy() {
		return this.lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Date getLastUpdateDate() {
		return this.lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getLastUpdateIp() {
		return this.lastUpdateIp;
	}

	public void setLastUpdateIp(String lastUpdateIp) {
		this.lastUpdateIp = lastUpdateIp;
	}

	public Long getVersion() {
		return this.version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
}
