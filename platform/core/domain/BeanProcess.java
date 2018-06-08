package com.piedpiper.platform.core.domain;

import java.io.Serializable;

public class BeanProcess implements Serializable {
	private static final long serialVersionUID = -3997975889063396777L;
	public String formId;
	public String processInstanceId;
	public String taskUrl = "";

	public String getFormId() {
		return this.formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getProcessInstanceId() {
		return this.processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getTaskUrl() {
		return this.taskUrl;
	}

	public void setTaskUrl(String taskUrl) {
		this.taskUrl = taskUrl;
	}
}
