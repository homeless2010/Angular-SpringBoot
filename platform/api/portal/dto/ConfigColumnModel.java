package com.piedpiper.platform.api.portal.dto;

import com.piedpiper.platform.core.domain.BeanDTO;
import com.piedpiper.platform.core.properties.PlatformConstant;
import com.piedpiper.platform.core.properties.PlatformConstant.LogType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfigColumnModel extends BeanDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private ArrayList<ConfigPortletModel> column;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<ConfigPortletModel> getColumn() {
		return this.column;
	}

	public void setColumn(ArrayList<ConfigPortletModel> column) {
		this.column = column;
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
