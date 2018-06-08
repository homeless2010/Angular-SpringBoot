package com.piedpiper.platform.api.mobile.dto;

import com.piedpiper.platform.core.annotation.log.FieldRemark;
import com.piedpiper.platform.core.annotation.log.LogField;
import com.piedpiper.platform.core.annotation.log.PojoRemark;
import com.piedpiper.platform.core.domain.BeanDTO;
import com.piedpiper.platform.core.properties.PlatformConstant;
import com.piedpiper.platform.core.properties.PlatformConstant.LogType;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheBean;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Id;

@PojoRemark(table = "mobile_app", object = "MobileAppDTO", name = "mobile_app")
public class MobileAppDTO extends BeanDTO implements BaseCacheBean {
	private static final long serialVersionUID = 1L;
	@Id
	@LogField
	@FieldRemark(column = "id", field = "id", name = "Id")
	private String id;
	@FieldRemark(column = "app_name", field = "appName", name = "应用名称")
	private String appName;
	@FieldRemark(column = "app_status", field = "appStatus", name = "应用状态：true正常，false禁用")
	private String appStatus;
	@FieldRemark(column = "descript", field = "descript", name = "应用描述")
	private String descript;
	@FieldRemark(column = "app_bind_chose", field = "appBindChose", name = "应用是否需要绑定：true需要，false不需要")
	private String appBindChose;
	public static final String MOBILEAPP = "PLATFORM6_MOBILEAPP";
	public static final String MOBILEAPPNAME = "PLATFORM6_MOBILEAPPNAME";

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAppName() {
		return this.appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppStatus() {
		return this.appStatus;
	}

	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}

	public String getDescript() {
		return this.descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public String getAppBindChose() {
		return this.appBindChose;
	}

	public void setAppBindChose(String appBindChose) {
		this.appBindChose = appBindChose;
	}

	public String getLogFormName() {
		if ((this.logFormName == null) || (this.logFormName.equals(""))) {
			return "mobile_app";
		}
		return this.logFormName;
	}

	public String getLogTitle() {
		if ((this.logTitle == null) || (this.logTitle.equals(""))) {
			return "mobile_app";
		}
		return this.logTitle;
	}

	public PlatformConstant.LogType getLogType() {
		if ((this.logType == null) || (this.logType.equals(""))) {
			return PlatformConstant.LogType.module_operate;
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
		Map<String, String> map = new HashMap();
		map.put("PLATFORM6_MOBILEAPP", this.id);
		map.put("PLATFORM6_MOBILEAPPNAME", this.appName);
		return map;
	}

	public String prefix() {
		return "PLATFORM6_MOBILEAPP";
	}

	public String returnAppId() {
		return null;
	}
}
