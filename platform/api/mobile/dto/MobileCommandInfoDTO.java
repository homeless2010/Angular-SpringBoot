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

@PojoRemark(table = "mobile_command_info", object = "MobileCommandInfoDTO", name = "mobile_command_info")
public class MobileCommandInfoDTO extends BeanDTO implements BaseCacheBean {
	private static final long serialVersionUID = 1L;
	@Id
	@LogField
	@FieldRemark(column = "id", field = "id", name = "主键")
	private String id;
	@FieldRemark(column = "service_id", field = "serviceId", name = "服务id外键")
	private String serviceId;
	@FieldRemark(column = "method_url", field = "methodUrl", name = "方法URL地址")
	private String methodUrl;
	@FieldRemark(column = "method_type", field = "methodType", name = "方法类型GET||POST")
	private String methodType;
	@FieldRemark(column = "method_name", field = "methodName", name = "方法名称")
	private String methodName;
	@FieldRemark(column = "method_showname", field = "methodShowName", name = "方法中文名称")
	private String methodShowName;
	public static final String MOBILECOMMANDINFO = "PLATFORM6_MOBILECOMMANDINFO";
	public static final String MOBILECOMMANDINFOMETHOD = "PLATFORM6_MOBILECOMMANDINFOMETHOD";

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getServiceId() {
		return this.serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getMethodUrl() {
		return this.methodUrl;
	}

	public void setMethodUrl(String methodUrl) {
		this.methodUrl = methodUrl;
	}

	public String getMethodType() {
		return this.methodType;
	}

	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}

	public String getMethodName() {
		return this.methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getMethodShowName() {
		return this.methodShowName;
	}

	public void setMethodShowName(String methodShowName) {
		this.methodShowName = methodShowName;
	}

	public String getLogFormName() {
		if ((this.logFormName == null) || (this.logFormName.equals(""))) {
			return "mobile_command_info";
		}
		return this.logFormName;
	}

	public String getLogTitle() {
		if ((this.logTitle == null) || (this.logTitle.equals(""))) {
			return "mobile_command_info";
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
		map.put("PLATFORM6_MOBILECOMMANDINFO", this.id);
		map.put("PLATFORM6_MOBILECOMMANDINFOMETHOD", this.methodName);
		return map;
	}

	public String prefix() {
		return "PLATFORM6_MOBILECOMMANDINFO";
	}

	public String returnAppId() {
		return null;
	}
}
