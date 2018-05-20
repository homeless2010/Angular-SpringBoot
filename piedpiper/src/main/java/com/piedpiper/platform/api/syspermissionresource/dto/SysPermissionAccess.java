package com.piedpiper.platform.api.syspermissionresource.dto;

import java.util.HashMap;
import java.util.Map;

import com.piedpiper.platform.core.properties.PlatformConstant;

public class SysPermissionAccess extends com.piedpiper.platform.core.domain.BeanDTO
		implements com.piedpiper.platform.core.redisCacheManager.BaseCacheBean {
	private static final long serialVersionUID = -4459174283745433724L;
	public static final String PERMISSIONACCESS = "PLATFORM6_PERMISSIONACCESS";
	public static final String PERMISSIONACCESS_RESOURCE = "PLATFORM6_PERMISSIONACCESS_RESOURCE_";
	private SysPermissionResource sysPermissionResource;
	private String id;
	private String resourceId;
	private PERMISSION type;
	private String accessUser;
	private String typeName;
	private String accessRoleName;
	private String accessDeptName;
	private String accessUserName;
	private String accessGroupName;
	private String accessPositionName;
	private String accessRole;

	public SysPermissionResource getSysPermissionResource() {
		return this.sysPermissionResource;
	}

	public void setSysPermissionResource(SysPermissionResource sysPermissionResourceMain) {
		this.sysPermissionResource = sysPermissionResourceMain;
	}

	private String accessDept;

	private String accessGroup;

	private String accessPosition;

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
	private String issql;
	private String presql;
	private String presqlName;

	public String getPresqlName() {
		return this.presqlName;
	}

	public void setPresqlName(String presqlName) {
		this.presqlName = presqlName;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getAccessRoleName() {
		return this.accessRoleName;
	}

	public void setAccessRoleName(String accessRoleName) {
		this.accessRoleName = accessRoleName;
	}

	public String getAccessDeptName() {
		return this.accessDeptName;
	}

	public void setAccessDeptName(String accessDeptName) {
		this.accessDeptName = accessDeptName;
	}

	public String getAccessUserName() {
		return this.accessUserName;
	}

	public void setAccessUserName(String accessUserName) {
		this.accessUserName = accessUserName;
	}

	public String getAccessGroupName() {
		return this.accessGroupName;
	}

	public void setAccessGroupName(String accessGroupName) {
		this.accessGroupName = accessGroupName;
	}

	public String getAccessPositionName() {
		return this.accessPositionName;
	}

	public void setAccessPositionName(String accessPositionName) {
		this.accessPositionName = accessPositionName;
	}

	public static long getSerialversionuid() {
		return -4459174283745433724L;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public PERMISSION getType() {
		return this.type;
	}

	public void setType(PERMISSION type) {
		this.type = type;
	}

	public String getAccessUser() {
		if (this.accessUser == null)
			return "";
		return this.accessUser;
	}

	public void setAccessUser(String accessUser) {
		this.accessUser = accessUser;
	}

	public String getAccessRole() {
		if (this.accessRole == null)
			return "";
		return this.accessRole;
	}

	public void setAccessRole(String accessRole) {
		this.accessRole = accessRole;
	}

	public String getAccessDept() {
		if (this.accessDept == null) {
			return "";
		}
		return this.accessDept;
	}

	public void setAccessDept(String accessDept) {
		this.accessDept = accessDept;
	}

	public String getAccessGroup() {
		if (this.accessGroup == null)
			return "";
		return this.accessGroup;
	}

	public void setAccessGroup(String accessGroup) {
		this.accessGroup = accessGroup;
	}

	public String getAccessPosition() {
		if (this.accessPosition == null)
			return "";
		return this.accessPosition;
	}

	public void setAccessPosition(String accessPosition) {
		this.accessPosition = accessPosition;
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

	public String getIssql() {
		return this.issql;
	}

	public void setIssql(String issql) {
		this.issql = issql;
	}

	public String getPresql() {
		if (this.presql == null)
			return "";
		return this.presql;
	}

	public void setPresql(String presql) {
		this.presql = presql;
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

	public Map<String, ?> returnCacheKey() {
		Map<String, Object> map = new HashMap();
		map.put("PLATFORM6_PERMISSIONACCESS", this.id);
		map.put("PLATFORM6_PERMISSIONACCESS_RESOURCE_" + this.resourceId, this.id);
		return map;
	}

	public String prefix() {
		return "PLATFORM6_PERMISSIONACCESS";
	}

	public String returnValidFlag() {
		return null;
	}

	public String returnId() {
		return this.id;
	}

	public String returnAppId() {
		return null;
	}
}
