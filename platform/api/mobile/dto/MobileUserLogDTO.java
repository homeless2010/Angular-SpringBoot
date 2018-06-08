package com.piedpiper.platform.api.mobile.dto;

import com.piedpiper.platform.core.annotation.log.FieldRemark;
import com.piedpiper.platform.core.annotation.log.LogField;
import com.piedpiper.platform.core.annotation.log.PojoRemark;
import com.piedpiper.platform.core.domain.BeanDTO;
import com.piedpiper.platform.core.properties.PlatformConstant;
import com.piedpiper.platform.core.properties.PlatformConstant.LogType;
import java.util.Date;
import javax.persistence.Id;

@PojoRemark(table = "mobile_user_log", object = "MobileUserLogDTO", name = "mobile_user_log")
public class MobileUserLogDTO extends BeanDTO {
	private static final long serialVersionUID = 1L;
	@Id
	@LogField
	@FieldRemark(column = "id", field = "id", name = "Id")
	private String id;
	@FieldRemark(column = "user_id", field = "userId", name = "用户ID")
	private String userId;
	@FieldRemark(column = "device_id", field = "deviceId", name = "设备ID")
	private String deviceId;
	@FieldRemark(column = "app_version_id", field = "appVersionId", name = "版本ID")
	private String appVersionId;
	@FieldRemark(column = "type", field = "type", name = "日志类型")
	private String type;
	@FieldRemark(column = "op_content", field = "opContent", name = "日志内容")
	private String opContent;
	@FieldRemark(column = "is_archive", field = "isArchive", name = "是否归档：归档，未归档")
	private String isArchive;
	@FieldRemark(column = "creation_date1", field = "creationDate1", name = "起始时间")
	private Date creationDate1;
	@FieldRemark(column = "creation_date2", field = "creationDate2", name = "结束时间")
	private Date creationDate2;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getAppVersionId() {
		return this.appVersionId;
	}

	public void setAppVersionId(String appVersionId) {
		this.appVersionId = appVersionId;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOpContent() {
		return this.opContent;
	}

	public void setOpContent(String opContent) {
		this.opContent = opContent;
	}

	public String getIsArchive() {
		return this.isArchive;
	}

	public void setIsArchive(String isArchive) {
		this.isArchive = isArchive;
	}

	public void setCreationDate1(Date creationDate1) {
		this.creationDate1 = creationDate1;
	}

	public Date getCreationDate1() {
		return this.creationDate1;
	}

	public void setCreationDate2(Date creationDate2) {
		this.creationDate2 = creationDate2;
	}

	public Date getCreationDate2() {
		return this.creationDate2;
	}

	public String getLogFormName() {
		if ((this.logFormName == null) || (this.logFormName.equals(""))) {
			return "mobile_user_log";
		}
		return this.logFormName;
	}

	public String getLogTitle() {
		if ((this.logTitle == null) || (this.logTitle.equals(""))) {
			return "mobile_user_log";
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
