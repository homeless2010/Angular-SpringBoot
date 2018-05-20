package com.piedpiper.platform.api.quartz.dto;

import java.io.Serializable;
import java.util.Date;

import com.piedpiper.platform.core.domain.BeanDTO;
import com.piedpiper.platform.core.properties.PlatformConstant;

public class JobCalendarDate extends BeanDTO implements Serializable {
	private static final long serialVersionUID = 4196391112946683378L;
	private String id = null;
	private String jobCalendarId = null;
	private Date excludedDate = null;
	private String description = null;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJobCalendarId() {
		return this.jobCalendarId;
	}

	public void setJobCalendarId(String jobCalendarId) {
		this.jobCalendarId = jobCalendarId;
	}

	public Date getExcludedDate() {
		return this.excludedDate;
	}

	public void setExcludedDate(Date excludedDate) {
		this.excludedDate = excludedDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLogFormName() {
		if ((this.logFormName == null) || (this.logFormName.equals(""))) {
			return "任务日历排除日期表";
		}
		return this.logFormName;
	}

	public String getLogTitle() {
		if ((this.logTitle == null) || (this.logTitle.equals(""))) {
			return "任务日历排除日期表";
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
