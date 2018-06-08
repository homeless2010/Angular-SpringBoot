package com.piedpiper.platform.api.sysuser.dto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.piedpiper.platform.core.domain.BeanDTO;
import com.piedpiper.platform.core.properties.PlatformConstant;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheBean;

public class SysRole extends BeanDTO implements BaseCacheBean, Comparable<SysRole> {
	public static final String ROLE = "PLATFORM6_ROLE";
	public static final String ROLECODE = "PLATFORM6_ROLECODE";
	private static final long serialVersionUID = 1L;
	private String id;
	private String roleName;
	private String roleCode;
	private String roleGroup;
	private String roleType;
	private String sysApplicationId;
	private String sysApplicationCode;
	private String sysApplicationName;
	private String desc;
	private BigDecimal orderBy;
	private String validFlag;
	private String sysDeptId;
	private String sysDeptName;
	private String unableModify;
	private String orgIdentity;
	private String usageModifier;
	private static final String pattern = "[id=%s],[角色姓名=%s],[角色编码=%s],[应用范围=%s],[状态=%s][排序=%s]";

	@JsonIgnore
	public String getOrgIdentity() {
		return this.orgIdentity;
	}

	public void setOrgIdentity(String orgIdentity) {
		this.orgIdentity = orgIdentity;
	}

	public String getUsageModifier() {
		return this.usageModifier;
	}

	public void setUsageModifier(String usageModifier) {
		this.usageModifier = usageModifier;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleCode() {
		return this.roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleGroup() {
		return this.roleGroup;
	}

	public void setRoleGroup(String roleGroup) {
		this.roleGroup = roleGroup;
	}

	public String getRoleType() {
		return this.roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getSysApplicationId() {
		return this.sysApplicationId;
	}

	public void setSysApplicationId(String sysApplicationId) {
		this.sysApplicationId = sysApplicationId;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public BigDecimal getOrderBy() {
		return this.orderBy;
	}

	public void setOrderBy(BigDecimal orderBy) {
		this.orderBy = orderBy;
	}

	public String getValidFlag() {
		return this.validFlag;
	}

	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
	}

	public String getSysDeptId() {
		return this.sysDeptId;
	}

	public void setSysDeptId(String sysDeptId) {
		this.sysDeptId = sysDeptId;
	}

	public String getSysDeptName() {
		return this.sysDeptName;
	}

	public void setSysDeptName(String sysDeptName) {
		this.sysDeptName = sysDeptName;
	}

	public String getSysApplicationCode() {
		return this.sysApplicationCode;
	}

	public void setSysApplicationCode(String sysApplicationCode) {
		this.sysApplicationCode = sysApplicationCode;
	}

	public String getSysApplicationName() {
		return this.sysApplicationName;
	}

	public void setSysApplicationName(String sysApplicationName) {
		this.sysApplicationName = sysApplicationName;
	}

	public String getUnableModify() {
		return this.unableModify;
	}

	public void setUnableModify(String unableModify) {
		this.unableModify = unableModify;
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
		Map<String, String> map = new HashMap();
		map.put("PLATFORM6_ROLE", this.id);
		map.put("PLATFORM6_ROLECODE", this.roleCode);
		return map;
	}

	public String prefix() {
		return "PLATFORM6_ROLE";
	}

	public String returnValidFlag() {
		return this.validFlag;
	}

	public String returnId() {
		return this.id;
	}

	public String returnAppId() {
		if ("0".equals(this.usageModifier)) {
			return null;
		}
		return this.sysApplicationId;
	}

	public int compareTo(SysRole o) {
		try {
			if (this.orderBy == null) {
				this.orderBy = BigDecimal.valueOf(2147483647L);
			}
			if ((o != null) && (o.getOrderBy() == null)) {
				o.setOrderBy(BigDecimal.valueOf(2147483647L));
			}
			return this.orderBy.subtract(o.getOrderBy()).intValue();
		} catch (Exception e) {
		}
		return 0;
	}

	public String returnLogAppId() {
		return this.sysApplicationId;
	}

	public String toString() {
		return String.format("[id=%s],[角色姓名=%s],[角色编码=%s],[应用范围=%s],[状态=%s][排序=%s]", new Object[] { this.id,
				this.roleName, this.roleCode, this.usageModifier, this.validFlag, this.orderBy });
	}

	public String returnKey() {
		return this.id;
	}
}
