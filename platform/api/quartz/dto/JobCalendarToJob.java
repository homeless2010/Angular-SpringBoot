package com.piedpiper.platform.api.quartz.dto;

import java.io.Serializable;

import com.piedpiper.platform.core.domain.BeanDTO;
import com.piedpiper.platform.core.properties.PlatformConstant;

public class JobCalendarToJob extends BeanDTO implements Serializable {
	private static final long serialVersionUID = -1040179735105984421L;
	private String id = null;
	private String jobId = null;
	private String jobCalendarId = null;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJobId() {
		return this.jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getJobCalendarId() {
		return this.jobCalendarId;
	}

	public void setJobCalendarId(String jobCalendarId) {
		this.jobCalendarId = jobCalendarId;
	}

	public String getLogFormName() {
		if ((this.logFormName == null) || (this.logFormName.equals(""))) {
			return "任务与任务日历的一对多关系表";
		}
		return this.logFormName;
	}

	public String getLogTitle() {
		if ((this.logTitle == null) || (this.logTitle.equals(""))) {
			return "任务与任务日历的一对多关系表";
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
