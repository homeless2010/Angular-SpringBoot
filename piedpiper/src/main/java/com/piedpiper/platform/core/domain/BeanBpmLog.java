package com.piedpiper.platform.core.domain;

import javax.persistence.Transient;

public abstract class BeanBpmLog {
	@Transient
	protected String logFormName = null;
	@Transient
	protected String logTitle = null;
	@Transient
	protected com.piedpiper.platform.core.properties.PlatformConstant.LogType logType = null;

	@Transient
	public abstract String getLogFormName();

	@Transient
	public abstract String getLogTitle();

	@Transient
	public abstract com.piedpiper.platform.core.properties.PlatformConstant.LogType getLogType();
}
