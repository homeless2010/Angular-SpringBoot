package com.piedpiper.platform.api.sysuser.dto;

public class SysOrgVo {
	private String id;

	private String orgCode;

	private String orgId;

	private String validFlag;

	private int orderBy;

	private String post;

	private String tel;

	private String fax;

	private String email;

	private String workCalendarId;
	private String responsibleDeptId;
	private String responsibleDeptName;
	private String tlId;
	private String sysLanguageCode;
	private String orgName;
	private String orgDesc;
	private String orgPlace;
	private String parentOrgId;
	private String icon;
	private int childrenCount;
	private String nodeType;

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getParentOrgId() {
		return this.parentOrgId;
	}

	public void setParentOrgId(String parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	public String getValidFlag() {
		return this.validFlag;
	}

	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
	}

	public String getNodeType() {
		return this.nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public int getChildrenCount() {
		return this.childrenCount;
	}

	public void setChildrenCount(int childrenCount) {
		this.childrenCount = childrenCount;
	}

	public String getTlId() {
		return this.tlId;
	}

	public void setTlId(String tlId) {
		this.tlId = tlId;
	}

	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public int getOrderBy() {
		return this.orderBy;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}

	public String getPost() {
		return this.post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWorkCalendarId() {
		return this.workCalendarId;
	}

	public void setWorkCalendarId(String workCalendarId) {
		this.workCalendarId = workCalendarId;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSysLanguageCode() {
		return this.sysLanguageCode;
	}

	public void setSysLanguageCode(String sysLanguageCode) {
		this.sysLanguageCode = sysLanguageCode;
	}

	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgDesc() {
		return this.orgDesc;
	}

	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}

	public String getOrgPlace() {
		return this.orgPlace;
	}

	public void setOrgPlace(String orgPlace) {
		this.orgPlace = orgPlace;
	}

	public String getResponsibleDeptId() {
		return this.responsibleDeptId;
	}

	public void setResponsibleDeptId(String responsibleDeptId) {
		this.responsibleDeptId = responsibleDeptId;
	}

	public String getResponsibleDeptName() {
		return this.responsibleDeptName;
	}

	public void setResponsibleDeptName(String responsibleDeptName) {
		this.responsibleDeptName = responsibleDeptName;
	}
}
