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

@PojoRemark(table = "mobile_app_version", object = "MobileAppVersionDTO", name = "mobile_app_version")
public class MobileAppVersionDTO extends BeanDTO implements BaseCacheBean {
	private static final long serialVersionUID = 1L;
	@Id
	@LogField
	@FieldRemark(column = "id", field = "id", name = "Id")
	private String id;
	@FieldRemark(column = "app_id", field = "appId", name = "应用ID，MOBILE_APP外键")
	private String appId;
	@FieldRemark(column = "platform_type", field = "platformType", name = "APP版本类型：ios或者android")
	private String platformType;
	@FieldRemark(column = "app_version", field = "appVersion", name = "应用版本")
	private String appVersion;
	@FieldRemark(column = "app_status", field = "appStatus", name = "版本状态：true正常，false禁用")
	private String appStatus;
	@FieldRemark(column = "download_url", field = "downloadUrl", name = "应用下载地址")
	private String downloadUrl;
	@FieldRemark(column = "descript", field = "descript", name = "应用描述")
	private String descript;
	public static final String MOBILEAPPVERSION = "PLATFORM6_MOBILEAPPVERSION";
	public static final String MOBILEAPPVERSION_PID = "PLATFORM6_MOBILEAPPVERSION_PID_";
	public static final String MOBILEAPPVERSION_PID_PLATFORM_VERSION = "PLATFORM6_MOBILEAPPVERSION_PID_PLATFORM_VERSION_";

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAppId() {
		return this.appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getPlatformType() {
		return this.platformType;
	}

	public void setPlatformType(String platformType) {
		this.platformType = platformType;
	}

	public String getAppVersion() {
		return this.appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getAppStatus() {
		return this.appStatus;
	}

	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}

	public String getDownloadUrl() {
		return this.downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public String getDescript() {
		return this.descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public String getLogFormName() {
		if ((this.logFormName == null) || (this.logFormName.equals(""))) {
			return "mobile_app_version";
		}
		return this.logFormName;
	}

	public String getLogTitle() {
		if ((this.logTitle == null) || (this.logTitle.equals(""))) {
			return "mobile_app_version";
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
		map.put("PLATFORM6_MOBILEAPPVERSION", this.id);
		map.put("PLATFORM6_MOBILEAPPVERSION_PID_" + this.appId, this.id);
		if ((this.appStatus != null) && (this.appStatus.equals("2"))) {
			map.put("PLATFORM6_MOBILEAPPVERSION_PID_PLATFORM_VERSION_" + this.appId + "_" + this.platformType + "_2",
					this.id);
		}
		return map;
	}

	public String prefix() {
		return "PLATFORM6_MOBILEAPPVERSION";
	}

	public String returnAppId() {
		return null;
	}
}
