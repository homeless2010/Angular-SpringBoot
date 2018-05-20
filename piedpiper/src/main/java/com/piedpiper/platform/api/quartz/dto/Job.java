package com.piedpiper.platform.api.quartz.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

import com.piedpiper.platform.core.properties.PlatformConstant;

public class Job extends BaseBean implements Serializable {
	private static final long serialVersionUID = 4897346371237381804L;
	private String name = null;
	private String group = "avicit.job.defaultGroup";
	private String program = null;
	private String type = null;
	private String cron = null;
	private String status = null;
	private String description = null;
	private String lastState = null;
	private Map<String, Object> jobVariables = null;
	private Collection<Variable> variables = null;
	public static final String DEFAULT_GROUP = "avicit.job.defaultGroup";

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroup() {
		return this.group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getProgram() {
		return this.program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCron() {
		return this.cron;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLastState() {
		return this.lastState;
	}

	public void setLastState(String lastState) {
		this.lastState = lastState;
	}

	public Map<String, Object> getJobVariables() {
		return this.jobVariables;
	}

	public void setJobVariables(Map<String, Object> jobVariables) {
		this.jobVariables = jobVariables;
	}

	public Collection<Variable> getVariables() {
		return this.variables;
	}

	public void setVariables(Collection<Variable> variables) {
		this.variables = variables;
	}

	public static final String DEFAULT_SYSTEM_JOB_GROUP = "avicit_default_system_job_group";

	public String getLogFormName() {
		if ((this.logFormName == null) || (this.logFormName.equals(""))) {
			return "定时任务任务定义表";
		}
		return this.logFormName;
	}

	public String getLogTitle() {
		if ((this.logTitle == null) || (this.logTitle.equals(""))) {
			return "定时任务任务定义表";
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
