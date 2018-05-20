package com.piedpiper.platform.api.portal.dto;

import java.io.Serializable;
import java.util.ArrayList;

import com.piedpiper.platform.core.domain.BeanDTO;
import com.piedpiper.platform.core.properties.PlatformConstant;

public class ResultModel extends BeanDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String width;
	private ArrayList<String> portletIds;

	public String getWidth() {
		return this.width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public ArrayList<String> getPortletIds() {
		return this.portletIds;
	}

	public void setPortletIds(ArrayList<String> portletIds) {
		this.portletIds = portletIds;
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
