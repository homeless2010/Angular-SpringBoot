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

@PojoRemark(table = "mobile_use_device_bind", object = "MobileUseDeviceBindDTO", name = "mobile_use_device_bind")
public class MobileUseDeviceBindDTO extends BeanDTO implements BaseCacheBean {
	private static final long serialVersionUID = 1L;
	@Id
	@LogField
	@FieldRemark(column = "id", field = "id", name = "Id")
	private String id;
	@FieldRemark(column = "user_id", field = "userId", name = "用户ID")
	private String userId;
	private String userIdAlias;
	@FieldRemark(column = "device_id", field = "deviceId", name = "设备ID")
	private String deviceId;
	@FieldRemark(column = "app_id", field = "appId", name = "appId")
	private String appId;
	private String appName;
	@FieldRemark(column = "bind_status", field = "bindStatus", name = "状态")
	private String bindStatus;
	public static final String MOBILEUSERDEVICEBIND = "PLATFORM6_MOBILEUSERDEVICEBIND";
	public static final String MOBILEUSERDEVICEBIND_USER_DEVICE = "PLATFORM6_MOBILEUSERDEVICEBIND_USER_DEVICE_";

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

	public String getUserIdAlias() {
		return this.userIdAlias;
	}

	public void setUserIdAlias(String userIdAlias) {
		this.userIdAlias = userIdAlias;
	}

	public String getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getAppId() {
		return this.appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppName() {
		return this.appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getBindStatus() {
		return this.bindStatus;
	}

	public void setBindStatus(String bindStatus) {
		this.bindStatus = bindStatus;
	}

	public String getLogFormName() {
		if ((this.logFormName == null) || (this.logFormName.equals(""))) {
			return "mobile_use_device_bind";
		}
		return this.logFormName;
	}

	public String getLogTitle() {
		if ((this.logTitle == null) || (this.logTitle.equals(""))) {
			return "mobile_use_device_bind";
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
		Map<String, Object> map = new HashMap();
		map.put("PLATFORM6_MOBILEUSERDEVICEBIND", this.id);
		map.put("PLATFORM6_MOBILEUSERDEVICEBIND_USER_DEVICE_" + this.userId + "_" + this.deviceId, this.id);
		return map;
	}

	public String prefix() {
		return "PLATFORM6_MOBILEUSERDEVICEBIND";
	}

	public String returnAppId() {
		return null;
	}
}
