package com.piedpiper.platform.api.sysuser.dto;

public class SysOrgDeptVo {
	private String id;
	private String code;
	private String orgId;
	private int orderBy;
	private String validFlag;
	private String fax;
	private String email;
	private String workCalendarId;
	private String responsibleDeptId;
	private String responsibleDeptName;
	private String parentId;
	private String tlId;
	private String sysLanguageCode;
	private String name;
	private String desc;
	private String place;
	private String post;
	private String tel;
	private String deptAlias;
	private String deptType;
	private String type;

	public String getDeptAlias() {
		return this.deptAlias;
	}

	public void setDeptAlias(String deptAlias) {
		this.deptAlias = deptAlias;
	}

	public String getDeptType() {
		return this.deptType;
	}

	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getValidFlag() {
		return this.validFlag;
	}

	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getTlId() {
		return this.tlId;
	}

	public void setTlId(String tlId) {
		this.tlId = tlId;
	}

	public String getSysLanguageCode() {
		return this.sysLanguageCode;
	}

	public void setSysLanguageCode(String sysLanguageCode) {
		this.sysLanguageCode = sysLanguageCode;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getPlace() {
		return this.place;
	}

	public void setPlace(String place) {
		this.place = place;
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
