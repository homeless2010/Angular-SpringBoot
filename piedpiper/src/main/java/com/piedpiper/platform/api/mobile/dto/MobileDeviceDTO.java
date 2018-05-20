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

@PojoRemark(table = "mobile_device", object = "MobileDeviceDTO", name = "mobile_device")
public class MobileDeviceDTO extends BeanDTO implements BaseCacheBean {
	private static final long serialVersionUID = 1L;
	@Id
	@LogField
	@FieldRemark(column = "id", field = "id", name = "Id")
	private String id;
	@FieldRemark(column = "imei", field = "imei", name = "设备IMEI")
	private String imei;
	@FieldRemark(column = "imsi", field = "imsi", name = "设备IMSI")
	private String imsi;
	@FieldRemark(column = "screen", field = "screen", name = "屏幕分辨率")
	private String screen;
	@FieldRemark(column = "device_name", field = "deviceName", name = "设备名称")
	private String deviceName;
	@FieldRemark(column = "platform", field = "platform", name = "设备平台：ios或者android")
	private String platform;
	@FieldRemark(column = "platform_version", field = "platformVersion", name = "平台版本")
	private String platformVersion;
	@FieldRemark(column = "device_status", field = "deviceStatus", name = "设备状态：true正常，false禁止")
	private String deviceStatus;
	public static final String MOBILEDEVICE = "PLATFORM6_MOBILEDEVICE";
	public static final String MOBILEDEVICEIMEI = "PLATFORM6_MOBILEDEVICEIMEI";

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImei() {
		return this.imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getImsi() {
		return this.imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getScreen() {
		return this.screen;
	}

	public void setScreen(String screen) {
		this.screen = screen;
	}

	public String getDeviceName() {
		return this.deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getPlatform() {
		return this.platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getPlatformVersion() {
		return this.platformVersion;
	}

	public void setPlatformVersion(String platformVersion) {
		this.platformVersion = platformVersion;
	}

	public String getDeviceStatus() {
		return this.deviceStatus;
	}

	public void setDeviceStatus(String deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

	public String getLogFormName() {
		if ((this.logFormName == null) || (this.logFormName.equals(""))) {
			return "mobile_device";
		}
		return this.logFormName;
	}

	public String getLogTitle() {
		if ((this.logTitle == null) || (this.logTitle.equals(""))) {
			return "mobile_device";
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
		map.put("PLATFORM6_MOBILEDEVICE", this.id);
		map.put("PLATFORM6_MOBILEDEVICEIMEI", this.imei);
		return map;
	}

	public String prefix() {
		return "PLATFORM6_MOBILEDEVICE";
	}

	public String returnAppId() {
		return null;
	}
}
