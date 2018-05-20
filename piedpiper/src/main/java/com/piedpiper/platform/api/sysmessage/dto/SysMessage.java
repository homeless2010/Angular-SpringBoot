package com.piedpiper.platform.api.sysmessage.dto;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.piedpiper.platform.core.domain.BeanDTO;
import com.piedpiper.platform.core.properties.PlatformConstant;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheBean;

public class SysMessage extends BeanDTO implements BaseCacheBean {
	public static final String MESSAGE = "PLATFORM6_MESSAGE";
	public static final String MESSAGE_USER_READ = "PLATFORM6_MESSAGE_USER_READ_";
	private static final long serialVersionUID = 1L;
	private String id;
	private String title;
	private String content;
	private String urlAddress;
	private String urlType;
	private String sendUser;
	private Date sendDate;
	private String recvUser;
	private Date recvDate;
	private String isReaded;
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
	private String sendUserName;
	private String recvDeptName;
	private String recvDeptCode;
	private String sendDeptName;
	private String sendDeptId;
	private String recvUserName;
	private String recvUserSex;
	private String autoDisappear;
	private Date disappearDate;
	private String sysApplicationId;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrlAddress() {
		return this.urlAddress;
	}

	public void setUrlAddress(String urlAddress) {
		this.urlAddress = urlAddress;
	}

	public String getSendUser() {
		return this.sendUser;
	}

	public void setSendUser(String sendUser) {
		this.sendUser = sendUser;
	}

	public Date getSendDate() {
		return this.sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public String getRecvUser() {
		return this.recvUser;
	}

	public void setRecvUser(String recvUser) {
		this.recvUser = recvUser;
	}

	public Date getRecvDate() {
		return this.recvDate;
	}

	public void setRecvDate(Date recvDate) {
		this.recvDate = recvDate;
	}

	public String getIsReaded() {
		return this.isReaded;
	}

	public void setIsReaded(String isReaded) {
		this.isReaded = isReaded;
	}

	public String getSendUserName() {
		return this.sendUserName;
	}

	public void setSendUserName(String sendUserName) {
		this.sendUserName = sendUserName;
	}

	public String getRecvUserName() {
		return this.recvUserName;
	}

	public void setRecvUserName(String recvUserName) {
		this.recvUserName = recvUserName;
	}

	public String getRecvUserSex() {
		return this.recvUserSex;
	}

	public void setRecvUserSex(String recvUserSex) {
		this.recvUserSex = recvUserSex;
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

	public String getSysApplicationId() {
		return this.sysApplicationId;
	}

	public void setSysApplicationId(String sysApplicationId) {
		this.sysApplicationId = sysApplicationId;
	}

	public String getLogFormName() {
		if ((this.logFormName == null) || (this.logFormName.equals(""))) {
			return "消息管理模块";
		}
		return this.logFormName;
	}

	public String getLogTitle() {
		if ((this.logTitle == null) || (this.logTitle.equals(""))) {
			return "SYSMESSAGE";
		}
		return this.logTitle;
	}

	public PlatformConstant.LogType getLogType() {
		if ((this.logType == null) || (this.logType.equals(""))) {
			return PlatformConstant.LogType.system_manage;
		}
		return this.logType;
	}

	public String getUrlType() {
		return this.urlType;
	}

	public void setUrlType(String urlType) {
		this.urlType = urlType;
	}

	public String getAutoDisappear() {
		return this.autoDisappear;
	}

	public void setAutoDisappear(String autoDisappear) {
		this.autoDisappear = autoDisappear;
	}

	public Date getDisappearDate() {
		return this.disappearDate;
	}

	public void setDisappearDate(Date disappearDate) {
		this.disappearDate = disappearDate;
	}

	public String getSendDeptId() {
		return this.sendDeptId;
	}

	public void setSendDeptId(String sendDeptId) {
		this.sendDeptId = sendDeptId;
	}

	public String getRecvDeptName() {
		return this.recvDeptName;
	}

	public void setRecvDeptName(String recvDeptName) {
		this.recvDeptName = recvDeptName;
	}

	public String getRecvDeptCode() {
		return this.recvDeptCode;
	}

	public void setRecvDeptCode(String recvDeptCode) {
		this.recvDeptCode = recvDeptCode;
	}

	public String getSendDeptName() {
		return this.sendDeptName;
	}

	public void setSendDeptName(String sendDeptName) {
		this.sendDeptName = sendDeptName;
	}

	public String returnId() {
		return this.id;
	}

	public String returnValidFlag() {
		return null;
	}

	public Map<String, ?> returnCacheKey() {
		Map<String, Object> map = new HashMap();
		map.put("PLATFORM6_MESSAGE", this.id);
		map.put("PLATFORM6_MESSAGE_USER_READ_" + this.recvUser + "_" + this.isReaded + "_" + this.sysApplicationId,
				this.id);
		return map;
	}

	public String prefix() {
		return "PLATFORM6_MESSAGE";
	}

	public String returnAppId() {
		return null;
	}
}
