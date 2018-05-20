package com.piedpiper.platform.api.sysuser.dto;

import java.util.Date;

import com.piedpiper.platform.core.domain.BeanDTO;
import com.piedpiper.platform.core.properties.PlatformConstant;

public class SysUserLockLog extends BeanDTO {
	private static final long serialVersionUID = 1L;
	private String id;
	private String sysUserName;
	private String lockIp;
	private Date lockDate;
	private String lockContent;
	private String attribute01;
	private String attribute02;
	private String sysApplicationId;

	public SysUserLockLog(String appid, String username, String lockContent, String lockIp) {
		this.sysApplicationId = appid;
		this.sysUserName = username;
		this.lockContent = lockContent;
		this.lockIp = lockIp;
	}

	public SysUserLockLog() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSysUserName() {
		return this.sysUserName;
	}

	public void setSysUserName(String sysUserName) {
		this.sysUserName = sysUserName;
	}

	public String getLockIp() {
		return this.lockIp;
	}

	public void setLockIp(String lockIp) {
		this.lockIp = lockIp;
	}

	public Date getLockDate() {
		return this.lockDate;
	}

	public void setLockDate(Date lockDate) {
		this.lockDate = lockDate;
	}

	public String getLockContent() {
		return this.lockContent;
	}

	public void setLockContent(String lockContent) {
		this.lockContent = lockContent;
	}

	public String getAttribute01() {
		return this.attribute01;
	}

	public void setAttribute01(String attribute01) {
		this.attribute01 = attribute01;
	}

	public String getAttribute02() {
		return this.attribute02;
	}

	public void setAttribute02(String attribute02) {
		this.attribute02 = attribute02;
	}

	public String getSysApplicationId() {
		return this.sysApplicationId;
	}

	public void setSysApplicationId(String sysApplicationId) {
		this.sysApplicationId = sysApplicationId;
	}

	public String getLogFormName() {
		return null;
	}

	public String getLogTitle() {
		return null;
	}

	public PlatformConstant.LogType getLogType() {
		return null;
	}
}
