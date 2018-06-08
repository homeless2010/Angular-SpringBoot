package com.piedpiper.platform.api.syspermissionresource.dto;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.piedpiper.platform.core.domain.BeanDTO;
import com.piedpiper.platform.core.properties.PlatformConstant;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheBean;

public class SysPermissionResource extends BeanDTO implements BaseCacheBean {
	private static final long serialVersionUID = -6094684362477970236L;
	public static final String PERMISSIONRESOURCE = "PLATFORM6_PERMISSIONRESOURCE";
	public static final String PERMISSIONRESOURCE_PARENT = "PLATFORM6_PERMISSIONRESOURCE_PARENT_";
	private String id;
	private String parentId;
	private String datagrid;
	private String dataset;
	private String datatype;
	private String metadata;
	private String sql;
	private String status;
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
	private String isSecret;
	private String haveSecret;
	private String menuname;
	private String orgIdentity;
	private Collection<SysPermissionAccess> sysPermissionAccess;

	@JsonIgnore
	public String getOrgIdentity() {
		return this.orgIdentity;
	}

	public void setOrgIdentity(String orgIdentity) {
		this.orgIdentity = orgIdentity;
	}

	public String getMenuname() {
		return this.menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public Collection<SysPermissionAccess> getSysPermissionAccess() {
		return this.sysPermissionAccess;
	}

	public void setSysPermissionAccess(Collection<SysPermissionAccess> sysPermissionResourceChild) {
		this.sysPermissionAccess = sysPermissionResourceChild;
	}

	public String getIsSecret() {
		return this.isSecret;
	}

	public void setIsSecret(String isSecret) {
		this.isSecret = isSecret;
	}

	public String getHaveSecret() {
		return this.haveSecret;
	}

	public void setHaveSecret(String haveSecret) {
		this.haveSecret = haveSecret;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDatagrid() {
		return this.datagrid;
	}

	public void setDatagrid(String datagrid) {
		this.datagrid = datagrid;
	}

	public String getDataset() {
		return this.dataset;
	}

	public void setDataset(String dataset) {
		this.dataset = dataset;
	}

	public String getDatatype() {
		return this.datatype;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	public String getMetadata() {
		return this.metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public String getSql() {
		return this.sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
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

	public SysPermissionAccess getPermAcesByType(PERMISSION perm) {
		for (SysPermissionAccess sysPermissionAccess : getSysPermissionAccess()) {
			if (sysPermissionAccess.getType() == perm) {
				return sysPermissionAccess;
			}
		}
		return null;
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
		map.put("PLATFORM6_PERMISSIONRESOURCE", this.id);
		map.put("PLATFORM6_PERMISSIONRESOURCE_PARENT_" + this.parentId, this.id);
		return map;
	}

	public String prefix() {
		return "PLATFORM6_PERMISSIONRESOURCE";
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
