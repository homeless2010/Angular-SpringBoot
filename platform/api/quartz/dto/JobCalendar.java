package com.piedpiper.platform.api.quartz.dto;

import java.io.Serializable;
import java.util.Collection;

import com.piedpiper.platform.core.properties.PlatformConstant;

public class JobCalendar extends BaseBean implements Serializable {
	private static final long serialVersionUID = -2155716156660089733L;
	private String name = null;
	private String description = null;
	private Collection<JobCalendarDate> jobCalendarDates = null;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Collection<JobCalendarDate> getJobCalendarDates() {
		return this.jobCalendarDates;
	}

	public void setJobCalendarDates(Collection<JobCalendarDate> jobCalendarDates) {
		this.jobCalendarDates = jobCalendarDates;
	}

	public String getLogFormName() {
		if ((this.logFormName == null) || (this.logFormName.equals(""))) {
			return "任务日历表";
		}
		return this.logFormName;
	}

	public String getLogTitle() {
		if ((this.logTitle == null) || (this.logTitle.equals(""))) {
			return "任务日历表";
		}
		return this.logTitle;
	}

	public PlatformConstant.LogType getLogType() {
		if ((this.logType == null) || (this.logType.equals(""))) {
			return PlatformConstant.LogType.system_manage;
		}
		return this.logType;
	}
}
