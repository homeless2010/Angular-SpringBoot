package com.piedpiper.platform.api.portal.dto;

import com.piedpiper.platform.core.domain.BeanDTO;
import com.piedpiper.platform.core.properties.PlatformConstant;
import com.piedpiper.platform.core.properties.PlatformConstant.LogType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfigPortletModel extends BeanDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String portlet;

	public String getPortlet() {
		return this.portlet;
	}

	public void setPortlet(String portlet) {
		this.portlet = portlet;
	}

	public String getLogFormName() {
		return null;
	}

	public String getLogTitle() {
		return null;
	}

	public PlatformConstant.LogType getLogType() {
		return null;
	}
}
