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

@PojoRemark(table = "mobile_service_info", object = "MobileServiceInfoDTO", name = "mobile_service_info")
public class MobileServiceInfoDTO extends BeanDTO implements BaseCacheBean {
	private static final long serialVersionUID = 1L;
	@Id
	@LogField
	@FieldRemark(column = "id", field = "id", name = "主键")
	private String id;
	@FieldRemark(column = "service_name", field = "serviceName", name = "服务名称")
	private String serviceName;
	@FieldRemark(column = "service_baseurl", field = "serviceBaseurl", name = "服务基本地址")
	private String serviceBaseurl;
	public static final String MOBILESERVICEINFO = "PLATFORM6_MOBILESERVICEINFO";

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getServiceName() {
		return this.serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceBaseurl() {
		return this.serviceBaseurl;
	}

	public void setServiceBaseurl(String serviceBaseurl) {
		this.serviceBaseurl = serviceBaseurl;
	}

	public String getLogFormName() {
		if ((this.logFormName == null) || (this.logFormName.equals(""))) {
			return "mobile_service_info";
		}
		return this.logFormName;
	}

	public String getLogTitle() {
		if ((this.logTitle == null) || (this.logTitle.equals(""))) {
			return "mobile_service_info";
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
		map.put("PLATFORM6_MOBILESERVICEINFO", this.id);
		return map;
	}

	public String prefix() {
		return "PLATFORM6_MOBILESERVICEINFO";
	}

	public String returnAppId() {
		return null;
	}
}
