package com.piedpiper.platform.api.syslookup.dto;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.piedpiper.platform.core.domain.BeanDTO;
import com.piedpiper.platform.core.properties.PlatformConstant;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheBean;

public class SysLookup extends BeanDTO implements BaseCacheBean, Comparable<SysLookup> {
	private static final long serialVersionUID = 1L;
	public static final String LOOKUP = "PLATFORM6_LOOKUP";
	public static final String LOOKUP_TYPE = "PLATFORM6_LOOKUP_TYPE_";
	private String id;
	private String sysLookupTypeId;
	private BigDecimal displayOrder;
	private String lookupCode;
	private String validFlag;
	private String systemFlag;
	private SysLookupType sysLookupType;
	private Collection<SysLookupTl> sysLookupTl;
	private String sid;
	private String lookupName;
	private String description;
	private static final String pattern = "[id=%s],[通用代码id=%s],[编码=%s],[排序=%s],[状态=%s]";

	public String getSid() {
		return this.sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getLookupName() {
		return this.lookupName;
	}

	public void setLookupName(String lookupName) {
		this.lookupName = lookupName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SysLookupType getSysLookupType() {
		return this.sysLookupType;
	}

	public void setSysLookupType(SysLookupType sysLookupType) {
		this.sysLookupType = sysLookupType;
	}

	public Collection<SysLookupTl> getSysLookupTl() {
		return this.sysLookupTl;
	}

	public void setSysLookupTl(Collection<SysLookupTl> sysLookupTl) {
		this.sysLookupTl = sysLookupTl;
	}

	public String getSysLookupTypeId() {
		return this.sysLookupTypeId;
	}

	public void setSysLookupTypeId(String sysLookupTypeId) {
		this.sysLookupTypeId = sysLookupTypeId;
	}

	public BigDecimal getDisplayOrder() {
		return this.displayOrder;
	}

	public void setDisplayOrder(BigDecimal displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getLookupCode() {
		return this.lookupCode;
	}

	public void setLookupCode(String lookupCode) {
		this.lookupCode = lookupCode;
	}

	public String getValidFlag() {
		return this.validFlag;
	}

	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
	}

	public String getSystemFlag() {
		return this.systemFlag;
	}

	public void setSystemFlag(String systemFlag) {
		this.systemFlag = systemFlag;
	}

	public String getLogFormName() {
		if ((this.logFormName == null) || (this.logFormName.equals(""))) {
			return "通用代码管理";
		}
		return this.logFormName;
	}

	public String getLogTitle() {
		if ((this.logTitle == null) || (this.logTitle.equals(""))) {
			return "SYSLOOKUP";
		}
		return this.logTitle;
	}

	public PlatformConstant.LogType getLogType() {
		if ((this.logType == null) || (this.logType.equals(""))) {
			return PlatformConstant.LogType.system_manage;
		}
		return this.logType;
	}

	public Map<String, ?> returnCacheKey() {
		Map<String, Object> map = new HashMap();
		map.put("PLATFORM6_LOOKUP", this.id);
		map.put("PLATFORM6_LOOKUP_TYPE_" + this.sysLookupTypeId, this.id);
		return map;
	}

	public String prefix() {
		return "PLATFORM6_LOOKUP";
	}

	public int compareTo(SysLookup o) {
		if (getDisplayOrder() == null) {
			return -1;
		}
		if (o.getDisplayOrder() == null) {
			return -1;
		}

		return getDisplayOrder().compareTo(o.getDisplayOrder());
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

	public String toString() {
		return String.format("[id=%s],[通用代码id=%s],[编码=%s],[排序=%s],[状态=%s]",
				new Object[] { this.id, this.sysLookupTypeId, this.lookupCode, this.displayOrder, this.validFlag });
	}

	public String returnKey() {
		return this.id;
	}
}
