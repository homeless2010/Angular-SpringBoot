package com.piedpiper.platform.api.portal.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.piedpiper.platform.core.domain.BeanDTO;
import com.piedpiper.platform.core.properties.PlatformConstant;

public class SysPortletConfig extends BeanDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String userid;
	private String userName;
	private String layout;
	private String name;
	private String layoutExtends;
	private String content;
	private int orderBy;
	private String roleId;
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
	private String sysApplicationId;
	private String f;
	private String usageModifier;
	private String orgIdentity;
	private String isUse;
	private String indexType;
	private String indexUrl;

	@JsonIgnore
	public String getOrgIdentity() {
		return this.orgIdentity;
	}

	public void setOrgIdentity(String orgIdentity) {
		this.orgIdentity = orgIdentity;
	}

	public String getF() {
		return this.f;
	}

	public String getIsUse() {
		return this.isUse;
	}

	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}

	public String getIndexType() {
		return this.indexType;
	}

	public void setIndexType(String indexType) {
		this.indexType = indexType;
	}

	public String getIndexUrl() {
		return this.indexUrl;
	}

	public void setIndexUrl(String indexUrl) {
		this.indexUrl = indexUrl;
	}

	public void setF(String f) {
		this.f = f;
	}

	public String getUsageModifier() {
		return this.usageModifier;
	}

	public void setUsageModifier(String usageModifier) {
		this.usageModifier = usageModifier;
	}

	public int getOrderBy() {
		return this.orderBy;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLayout() {
		return this.layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLayoutExtends() {
		return this.layoutExtends;
	}

	public void setLayoutExtends(String layoutExtends) {
		this.layoutExtends = layoutExtends;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLogFormName() {
		if ((this.logFormName == null) || (this.logFormName.equals(""))) {
			return "门户管理";
		}
		return this.logFormName;
	}

	public String getLogTitle() {
		if ((this.logTitle == null) || (this.logTitle.equals(""))) {
			return "SYS_PORTLET_CONFIG";
		}
		return this.logTitle;
	}

	public PlatformConstant.LogType getLogType() {
		if ((this.logType == null) || (this.logType.equals(""))) {
			return PlatformConstant.LogType.system_manage;
		}
		return this.logType;
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

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getSysApplicationId() {
		return this.sysApplicationId;
	}

	public void setSysApplicationId(String sysApplicationId) {
		this.sysApplicationId = sysApplicationId;
	}

	public String returnLogAppId() {
		return this.sysApplicationId;
	}
}
