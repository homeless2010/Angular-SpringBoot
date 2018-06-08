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

//import org.mockito.internal.util.Platform;

@PojoRemark(table = "mobile_command_headers", object = "MobileCommandHeadersDTO", name = "mobile_command_headers")
public class MobileCommandHeadersDTO extends BeanDTO implements BaseCacheBean {
	private static final long serialVersionUID = 1L;
	@Id
	@LogField
	@FieldRemark(column = "id", field = "id", name = "主键")
	private String id;
	@LogField
	@FieldRemark(column = "command_id", field = "commandId", name = "方法id外键")
	private String commandId;
	@LogField
	@FieldRemark(column = "header_name", field = "headerName", name = "header键名称")
	private String headerName;
	@LogField
	@FieldRemark(column = "header_value", field = "headerValue", name = "header值")
	private String headerValue;
	public static final String MOBILECOMMANDHEADERS = "PLATFORM6_MOBILECOMMANDHEADERS";
	public static final String MOBILECOMMANDHEADERS_PID = "PLATFORM6_MOBILECOMMANDHEADERS_PID_";

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCommandId() {
		return this.commandId;
	}

	public void setCommandId(String commandId) {
		this.commandId = commandId;
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
			return "mobile_command_headers";
		}
		return this.logFormName;
	}

	public String getLogTitle() {
		if ((this.logTitle == null) || (this.logTitle.equals(""))) {
			return "mobile_command_headers";
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
		map.put("PLATFORM6_MOBILECOMMANDHEADERS", this.id);
		map.put("PLATFORM6_MOBILECOMMANDHEADERS_PID_" + this.commandId, this.id);
		return map;
	}

	public String prefix() {
		return "PLATFORM6_MOBILECOMMANDHEADERS";
	}

	public String returnAppId() {
		return null;
	}
}
