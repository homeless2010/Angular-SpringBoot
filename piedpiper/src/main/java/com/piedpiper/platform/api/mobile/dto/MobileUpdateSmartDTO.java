package com.piedpiper.platform.api.mobile.dto;

import com.piedpiper.platform.core.annotation.log.FieldRemark;
import com.piedpiper.platform.core.annotation.log.LogField;
import com.piedpiper.platform.core.annotation.log.PojoRemark;
import com.piedpiper.platform.core.domain.BeanDTO;
import com.piedpiper.platform.core.properties.PlatformConstant;
import com.piedpiper.platform.core.properties.PlatformConstant.LogType;
import javax.persistence.Id;

@PojoRemark(table = "mobile_update_smart", object = "MobileUpdateSmartDTO", name = "MOBILE_UPDATE_SMART")
public class MobileUpdateSmartDTO extends BeanDTO {
	private static final long serialVersionUID = 1L;
	@Id
	@LogField
	@FieldRemark(column = "id", field = "id", name = "ID")
	private String id;
	@FieldRemark(column = "app_version_id", field = "appVersionId", name = "APP_VERSION_ID")
	private String appVersionId;
	@FieldRemark(column = "smart_version", field = "smartVersion", name = "SMART_VERSION")
	private String smartVersion;
	@FieldRemark(column = "public_status", field = "publicStatus", name = "PUBLIC_STATUS")
	private String publicStatus;
	@FieldRemark(column = "descript", field = "descript", name = "DESCRIPT")
	private String descript;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAppVersionId() {
		return this.appVersionId;
	}

	public void setAppVersionId(String appVersionId) {
		this.appVersionId = appVersionId;
	}

	public String getSmartVersion() {
		return this.smartVersion;
	}

	public void setSmartVersion(String smartVersion) {
		this.smartVersion = smartVersion;
	}

	public String getPublicStatus() {
		return this.publicStatus;
	}

	public void setPublicStatus(String publicStatus) {
		this.publicStatus = publicStatus;
	}

	public String getDescript() {
		return this.descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public String getLogFormName() {
		if ((this.logFormName == null) || (this.logFormName.equals(""))) {
			return "MOBILE_UPDATE_SMART";
		}
		return this.logFormName;
	}

	public String getLogTitle() {
		if ((this.logTitle == null) || (this.logTitle.equals(""))) {
			return "MOBILE_UPDATE_SMART";
		}
		return this.logTitle;
	}

	public PlatformConstant.LogType getLogType() {
		if ((this.logType == null) || (this.logType.equals(""))) {
			return PlatformConstant.LogType.module_operate;
		}
		return this.logType;
	}
}
