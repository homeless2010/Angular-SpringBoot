package com.piedpiper.platform.api.syspassword.dto;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Transient;

import com.piedpiper.platform.core.domain.BeanDTO;
import com.piedpiper.platform.core.properties.PlatformConstant;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheBean;

public class SysPasswordConfigure extends BeanDTO implements BaseCacheBean {
	public static final String PASSWORDCONFIGURE = "PLATFORM6_PASSWORDCONFIGURE";
	private static final long serialVersionUID = 1L;
	private String id;
	private String sysApplicationId;
	private String configureKey;
	private String configureName;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSysApplicationId() {
		return this.sysApplicationId;
	}

	public void setSysApplicationId(String sysApplicationId) {
		this.sysApplicationId = sysApplicationId;
	}

	public String getConfigureKey() {
		return this.configureKey;
	}

	public void setConfigureKey(String configureKey) {
		this.configureKey = configureKey;
	}

	public String getConfigureName() {
		return this.configureName;
	}

	public void setConfigureName(String configureName) {
		this.configureName = configureName;
	}

	@Transient
	public String getLogFormName() {
		if ((this.logFormName == null) || (this.logFormName.equals(""))) {
			return "系统密码模板模块";
		}
		return this.logFormName;
	}

	@Transient
	public String getLogTitle() {
		if ((this.logTitle == null) || (this.logTitle.equals(""))) {
			return "SYSPASSWORDCONFIGURE";
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

	public String returnId() {
		return this.id;
	}

	public String returnValidFlag() {
		return null;
	}

	public Map<String, ?> returnCacheKey() {
		Map<String, Object> map = new HashMap();
		map.put("PLATFORM6_PASSWORDCONFIGURE", this.id);
		return map;
	}

	public String prefix() {
		return "PLATFORM6_PASSWORDCONFIGURE";
	}

	public String returnAppId() {
		return this.sysApplicationId;
	}
}
