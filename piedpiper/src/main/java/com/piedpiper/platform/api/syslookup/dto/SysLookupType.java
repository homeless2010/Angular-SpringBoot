package com.piedpiper.platform.api.syslookup.dto;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.piedpiper.platform.core.domain.BeanDTO;
import com.piedpiper.platform.core.properties.PlatformConstant;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheBean;

public class SysLookupType extends BeanDTO implements BaseCacheBean {
	private static final long serialVersionUID = 1L;
	public static final String LOOKUPTYPE = "PLATFORM6_LOOKUPTYPE";
	public static final String LOOKUPTYPE_TYPE = "PLATFORM6_LOOKUPTYPE_TYPE_";
	private String id;
	private String sysApplicationId;
	private String lookupType;
	private String systemFlag;
	private String validFlag;
	private String belongModule;
	private String sid;
	private String description;
	private String lookupTypeName;
	private String usageModifier;
	private Collection<SysLookupTypeTl> sysLookupTypeTl;
	private Collection<SysLookup> sysLookup;
	private static final String pattern = "[id=%s],[类型=%s],[使用级别=%s],[应用id=%s],[状态=%s]";

	public String getUsageModifier() {
		return this.usageModifier;
	}

	public void setUsageModifier(String usageModifier) {
		this.usageModifier = usageModifier;
	}

	public String getSid() {
		return this.sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLookupTypeName() {
		return this.lookupTypeName;
	}

	public void setLookupTypeName(String lookupTypeName) {
		this.lookupTypeName = lookupTypeName;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Collection<SysLookupTypeTl> getSysLookupTypeTl() {
		return this.sysLookupTypeTl;
	}

	public void setSysLookupTypeTl(Collection<SysLookupTypeTl> sysLookupTypeTl) {
		this.sysLookupTypeTl = sysLookupTypeTl;
	}

	public Collection<SysLookup> getSysLookup() {
		return this.sysLookup;
	}

	public void setSysLookup(Collection<SysLookup> sysLookup) {
		this.sysLookup = sysLookup;
	}

	public String getSysApplicationId() {
		return this.sysApplicationId;
	}

	public void setSysApplicationId(String sysApplicationId) {
		this.sysApplicationId = sysApplicationId;
	}

	public String getBelongModule() {
		return this.belongModule;
	}

	public void setBelongModule(String belongModule) {
		this.belongModule = belongModule;
	}

	public String getLookupType() {
		return this.lookupType;
	}

	public void setLookupType(String lookupType) {
		this.lookupType = lookupType;
	}

	public String getSystemFlag() {
		return this.systemFlag;
	}

	public void setSystemFlag(String systemFlag) {
		this.systemFlag = systemFlag;
	}

	public String getValidFlag() {
		return this.validFlag;
	}

	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
	}

	public String getLogFormName() {
		if ((this.logFormName == null) || (this.logFormName.equals(""))) {
			return "通用代码管理";
		}
		return this.logFormName;
	}

	public String getLogTitle() {
		if ((this.logTitle == null) || (this.logTitle.equals(""))) {
			return "SYSLOOKUPTYPE";
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
		map.put("PLATFORM6_LOOKUPTYPE", this.id);
		map.put("PLATFORM6_LOOKUPTYPE_TYPE_" + this.lookupType, this.id);
		return map;
	}

	public String prefix() {
		return "PLATFORM6_LOOKUPTYPE";
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

	public String toString() {
		return String.format("[id=%s],[类型=%s],[使用级别=%s],[应用id=%s],[状态=%s]",
				new Object[] { this.id, this.lookupType, this.usageModifier, this.sysApplicationId, this.validFlag });
	}

	public String returnKey() {
		return this.id;
	}
}
