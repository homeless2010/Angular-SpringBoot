package com.piedpiper.platform.api.syspassword.dto;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Transient;

import com.piedpiper.platform.core.domain.BeanDTO;
import com.piedpiper.platform.core.properties.PlatformConstant;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheBean;

public class SysUserOldPassword extends BeanDTO implements BaseCacheBean, Comparable<SysUserOldPassword> {
	private static final long serialVersionUID = -1387193561692364117L;
	public static final String USEROLDPASSWORD = "PLATFORM6_USEROLDPASSWORD";
	public static final String USEROLDPASSWORD_USER = "PLATFORM6_USEROLDPASSWORD_USER_";
	private String id;
	private String userId;
	private String userPassword;
	private Date userModifyDate;
	private String managerFlag;
	private String attribute01;
	private String attribute02;
	private String attribute03;
	private String attribute04;
	private String attribute05;
	private String attribute06;
	private String attribute07;
	private String attribute08;
	private String attribute09;
	private String attribute10;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Date getUserModifyDate() {
		return this.userModifyDate;
	}

	public void setUserModifyDate(Date userModifyDate) {
		this.userModifyDate = userModifyDate;
	}

	public String getManagerFlag() {
		return this.managerFlag;
	}

	public void setManagerFlag(String managerFlag) {
		this.managerFlag = managerFlag;
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

	public String getAttribute03() {
		return this.attribute03;
	}

	public void setAttribute03(String attribute03) {
		this.attribute03 = attribute03;
	}

	public String getAttribute04() {
		return this.attribute04;
	}

	public void setAttribute04(String attribute04) {
		this.attribute04 = attribute04;
	}

	public String getAttribute05() {
		return this.attribute05;
	}

	public void setAttribute05(String attribute05) {
		this.attribute05 = attribute05;
	}

	public String getAttribute06() {
		return this.attribute06;
	}

	public void setAttribute06(String attribute06) {
		this.attribute06 = attribute06;
	}

	public String getAttribute07() {
		return this.attribute07;
	}

	public void setAttribute07(String attribute07) {
		this.attribute07 = attribute07;
	}

	public String getAttribute08() {
		return this.attribute08;
	}

	public void setAttribute08(String attribute08) {
		this.attribute08 = attribute08;
	}

	public String getAttribute09() {
		return this.attribute09;
	}

	public void setAttribute09(String attribute09) {
		this.attribute09 = attribute09;
	}

	public String getAttribute10() {
		return this.attribute10;
	}

	public void setAttribute10(String attribute10) {
		this.attribute10 = attribute10;
	}

	public String returnId() {
		return this.id;
	}

	public String returnValidFlag() {
		return null;
	}

	public Map<String, ?> returnCacheKey() {
		Map<String, Object> map = new HashMap();
		map.put("PLATFORM6_USEROLDPASSWORD", this.id);
		map.put("PLATFORM6_USEROLDPASSWORD_USER_" + this.userId, this.id);
		return map;
	}

	public String prefix() {
		return "PLATFORM6_USEROLDPASSWORD";
	}

	public String returnAppId() {
		return null;
	}

	@Transient
	public String getLogFormName() {
		if ((this.logFormName == null) || (this.logFormName.equals(""))) {
			return "用户历史密码模块";
		}
		return this.logFormName;
	}

	@Transient
	public String getLogTitle() {
		if ((this.logTitle == null) || (this.logTitle.equals(""))) {
			return "SYSUSEROLDPASSWORD";
		}
		return this.logTitle;
	}

	@Transient
	public PlatformConstant.LogType getLogType() {
		if ((this.logType == null) || (this.logType.equals(""))) {
			return PlatformConstant.LogType.system_manage;
		}
		return this.logType;
	}

	public int compareTo(SysUserOldPassword o) {
		Long tLong;
		if (this.userModifyDate == null) {
			tLong = Long.valueOf(Long.MIN_VALUE);
		} else
			tLong = Long.valueOf(this.userModifyDate.getTime());
		Long oLong;
		if ((o == null) || (o.getUserModifyDate() == null)) {
			oLong = Long.valueOf(Long.MIN_VALUE);
		} else {
			oLong = Long.valueOf(o.getUserModifyDate().getTime());
		}
		return oLong.longValue() - tLong.longValue() > 0L ? 1 : oLong.longValue() - tLong.longValue() == 0L ? 0 : -1;
	}
}
