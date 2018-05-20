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

@PojoRemark(table = "mobile_service_headers", object = "MobileServiceHeadersDTO", name = "mobile_service_headers")
public class MobileServiceHeadersDTO extends BeanDTO implements BaseCacheBean {
	private static final long serialVersionUID = 1L;
	@Id
	@LogField
	@FieldRemark(column = "id", field = "id", name = "主键")
	private String id;
	@LogField
	@FieldRemark(column = "service_id", field = "serviceId", name = "服务id外键")
	private String serviceId;
	@LogField
	@FieldRemark(column = "header_name", field = "headerName", name = "header键名称")
	private String headerName;
	@LogField
	@FieldRemark(column = "header_value", field = "headerValue", name = "header值")
	private String headerValue;
	public static final String MOBILESERVICEHEADERS = "PLATFORM6_MOBILESERVICEHEADERS";
	public static final String MOBILESERVICEHEADERS_PID = "PLATFORM6_MOBILESERVICEHEADERS_PID_";

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

	public String getHeaderName() {
		return this.headerName;
	}

	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}

	public String getHeaderValue() {
		return this.headerValue;
	}

	public void setHeaderValue(String headerValue) {
		this.headerValue = headerValue;
	}

	public String getLogFormName() {
		if ((this.logFormName == null) || (this.logFormName.equals(""))) {
			return "mobile_service_headers";
		}
		return this.logFormName;
	}

	public String getLogTitle() {
		if ((this.logTitle == null) || (this.logTitle.equals(""))) {
			return "mobile_service_headers";
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
		map.put("PLATFORM6_MOBILESERVICEHEADERS", this.id);
		map.put("PLATFORM6_MOBILESERVICEHEADERS_PID_" + this.serviceId, this.id);
		return map;
	}

	public String prefix() {
		return "PLATFORM6_MOBILESERVICEHEADERS";
	}

	public String returnAppId() {
		return null;
	}
}
