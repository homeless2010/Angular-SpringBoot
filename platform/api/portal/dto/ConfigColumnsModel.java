package com.piedpiper.platform.api.portal.dto;

import com.piedpiper.platform.core.domain.BeanDTO;
import com.piedpiper.platform.core.properties.PlatformConstant;
import com.piedpiper.platform.core.properties.PlatformConstant.LogType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfigColumnsModel extends BeanDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<ConfigColumnModel> columns;

	public ArrayList<ConfigColumnModel> getColumns() {
		return this.columns;
	}

	public void setColumns(ArrayList<ConfigColumnModel> columns) {
		this.columns = columns;
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
