package com.piedpiper.platform.api.syspassword.dto;

import java.io.Serializable;

public class ChangeSelfPasswordVo implements Serializable {
	private static final long serialVersionUID = 5484263934409033208L;
	private String maxlength = "";
	private String intensity = "";
	private String distinctBefore = "";
	private String minlength = "";
	private String difference = "";
	private String modifyDefault = "";
	private String howLongModify = "";

	private String howLongLimitToLock = "";
	private String tipBeforeTime = "";
	private String howTimeLock = "";
	private String oldPassword;
	private String secretLevelName = "";
	private String managerFlag;
	private String firstLogin = "";

	private String defaultPsw;

	private String sysPsw;

	public String getDefaultPsw() {
		return this.defaultPsw;
	}

	public void setDefaultPsw(String defaultPsw) {
		this.defaultPsw = defaultPsw;
	}

	public String getSysPsw() {
		return this.sysPsw;
	}

	public void setSysPsw(String sysPsw) {
		this.sysPsw = sysPsw;
	}

	public String getFirstLogin() {
		return this.firstLogin;
	}

	public void setFirstLogin(String firstLogin) {
		this.firstLogin = firstLogin;
	}

	public String getManagerFlag() {
		return this.managerFlag;
	}

	public void setManagerFlag(String managerFlag) {
		this.managerFlag = managerFlag;
	}

	public String getModifyDefault() {
		return this.modifyDefault;
	}

	public void setModifyDefault(String modifyDefault) {
		this.modifyDefault = modifyDefault;
	}

	public String getHowLongModify() {
		return this.howLongModify;
	}

	public void setHowLongModify(String howLongModify) {
		this.howLongModify = howLongModify;
	}

	public String getSecretLevelName() {
		return this.secretLevelName;
	}

	public void setSecretLevelName(String secretLevelName) {
		this.secretLevelName = secretLevelName;
	}

	public String getOldPassword() {
		return this.oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getMaxlength() {
		return this.maxlength;
	}

	public void setMaxlength(String maxlength) {
		this.maxlength = maxlength;
	}

	public String getIntensity() {
		return this.intensity;
	}

	public void setIntensity(String intensity) {
		this.intensity = intensity;
	}

	public String getDistinctBefore() {
		return this.distinctBefore;
	}

	public void setDistinctBefore(String distinctBefore) {
		this.distinctBefore = distinctBefore;
	}

	public String getMinlength() {
		return this.minlength;
	}

	public void setMinlength(String minlength) {
		this.minlength = minlength;
	}

	public String getDifference() {
		return this.difference;
	}

	public void setDifference(String difference) {
		this.difference = difference;
	}

	public String getHowLongLimitToLock() {
		return this.howLongLimitToLock;
	}

	public void setHowLongLimitToLock(String howLongLimitToLock) {
		this.howLongLimitToLock = howLongLimitToLock;
	}

	public String getTipBeforeTime() {
		return this.tipBeforeTime;
	}

	public void setTipBeforeTime(String tipBeforeTime) {
		this.tipBeforeTime = tipBeforeTime;
	}

	public String getHowTimeLock() {
		return this.howTimeLock;
	}

	public void setHowTimeLock(String howTimeLock) {
		this.howTimeLock = howTimeLock;
	}
}
