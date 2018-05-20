package com.piedpiper.platform.api.sysuser.dto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.piedpiper.platform.core.domain.BeanDTO;
import com.piedpiper.platform.core.properties.PlatformConstant;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheBean;

public class SysGroup extends BeanDTO implements BaseCacheBean, Comparable<SysGroup> {
	public static final String GROUP = "PLATFORM6_GROUP";
	public static final String GROUP_GROUP = "PLATFORM6_GROUP_GROUP_";
	public static final String ORG_GROUP = "PLATFORM6_ORG_GROUP_";
	public static final String USER_GROUP = "PLATFORM6_CREATEUSER_GROUP_";
	public static final String SYS_GROUP = "1";
	public static final String PERSONAL_GROUP = "2";
	private static final long serialVersionUID = 1L;
	private String id;
	private String groupParentId;
	private String orgId;
	private BigDecimal orderBy;
	private String type;
	private String belongTo;
	private String validFlag;
	private Long version;
	private String applicationId;
	private String applicationName;
	private String sysGroupTlId;
	private String sysGroupId;
	private String sysLanguageCode;
	private String groupName;
	private String groupDesc;
	private Long groupTlVersion;
	private static final String pattern = "[id=%s],[组织id=%s],[群组类型=%s],[群组归属=%s],[群组状态=%s],[排序=%s]";

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGroupParentId() {
		return this.groupParentId;
	}

	public void setGroupParentId(String groupParentId) {
		this.groupParentId = groupParentId;
	}

	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public BigDecimal getOrderBy() {
		return this.orderBy;
	}

	public void setOrderBy(BigDecimal orderBy) {
		this.orderBy = orderBy;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBelongTo() {
		return this.belongTo;
	}

	public void setBelongTo(String belongTo) {
		this.belongTo = belongTo;
	}

	public String getValidFlag() {
		return this.validFlag;
	}

	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
	}

	public String getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public Long getVersion() {
		return this.version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getApplicationName() {
		return this.applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getSysGroupTlId() {
		return this.sysGroupTlId;
	}

	public void setSysGroupTlId(String sysGroupTlId) {
		this.sysGroupTlId = sysGroupTlId;
	}

	public String getSysGroupId() {
		return this.sysGroupId;
	}

	public void setSysGroupId(String sysGroupId) {
		this.sysGroupId = sysGroupId;
	}

	public String getSysLanguageCode() {
		return this.sysLanguageCode;
	}

	public void setSysLanguageCode(String sysLanguageCode) {
		this.sysLanguageCode = sysLanguageCode;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupDesc() {
		return this.groupDesc;
	}

	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}

	public Long getGroupTlVersion() {
		return this.groupTlVersion;
	}

	public void setGroupTlVersion(Long groupTlVersion) {
		this.groupTlVersion = groupTlVersion;
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
		map.put("PLATFORM6_GROUP", this.id);
		map.put("PLATFORM6_GROUP_GROUP_" + this.groupParentId, this.id);
		map.put("PLATFORM6_ORG_GROUP_" + this.orgId, this.id);
		map.put("PLATFORM6_CREATEUSER_GROUP_" + getCreatedBy(), this.id);
		return map;
	}

	public String prefix() {
		return "PLATFORM6_GROUP";
	}

	public String returnValidFlag() {
		return this.validFlag;
	}

	public String returnId() {
		return this.id;
	}

	public String returnAppId() {
		return null;
	}

	public int compareTo(SysGroup o) {
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

	public String toString() {
		return String.format("[id=%s],[组织id=%s],[群组类型=%s],[群组归属=%s],[群组状态=%s],[排序=%s]",
				new Object[] { this.id, this.orgId, this.type, this.belongTo, this.validFlag, this.orderBy });
	}

	public String returnKey() {
		return this.id;
	}
}
