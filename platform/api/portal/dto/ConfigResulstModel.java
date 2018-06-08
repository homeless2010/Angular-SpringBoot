package com.piedpiper.platform.api.portal.dto;

import com.piedpiper.platform.core.domain.BeanDTO;
import com.piedpiper.platform.core.properties.PlatformConstant;
import com.piedpiper.platform.core.properties.PlatformConstant.LogType;
import java.io.Serializable;
import java.util.ArrayList;

public class ConfigResulstModel extends BeanDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<ConfigColumnsModel> configResulst;

	public ArrayList<ConfigColumnsModel> getConfigResulst() {
		return this.configResulst;
	}

	public void setConfigResulst(ArrayList<ConfigColumnsModel> configResulst) {
		this.configResulst = configResulst;
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
