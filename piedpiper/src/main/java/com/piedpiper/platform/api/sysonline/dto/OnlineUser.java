package com.piedpiper.platform.api.sysonline.dto;

public class OnlineUser {
	private int onlineUserNum;

	private int sessionNum;

	private String name;

	private String loginName;

	private String deptId;
	private String deptName;
	private String ip;
	private String loginTime;
	private String onlineTime;
	private String userId;

	public int getOnlineUserNum() {
		return this.onlineUserNum;
	}

	public void setOnlineUserNum(int onlineUserNum) {
		this.onlineUserNum = onlineUserNum;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getLoginTime() {
		return this.loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getOnlineTime() {
		return this.onlineTime;
	}

	public void setOnlineTime(String onlineTime) {
		this.onlineTime = onlineTime;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getSessionNum() {
		return this.sessionNum;
	}

	public void setSessionNum(int sessionNum) {
		this.sessionNum = sessionNum;
	}
}
