package com.piedpiper.platform.api.quartz.dto;

import java.io.Serializable;

import javax.persistence.Transient;

import com.piedpiper.platform.core.domain.BeanBase;
import com.piedpiper.platform.core.properties.PlatformConstant;

public class Variable extends BeanBase implements Serializable {
	public static final String MODULE_JBPM4 = "jbpm4";
	public static final String MODULE_QUARTZ = "quartz";
	public static final String MODULE_REPORT = "report";
	private static final long serialVersionUID = -104437947798132014L;
	private String id;
	private String masterId;
	private String module;
	private String type;
	private String name;
	private String dataType;
	private String value;
	private String meta1;
	private String meta2;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMasterId() {
		return this.masterId;
	}

	public String getModule() {
		return this.module;
	}

	public String getType() {
		return this.type;
	}

	public String getName() {
		return this.name;
	}

	public String getDataType() {
		return this.dataType;
	}

	public String getValue() {
		return this.value;
	}

	public String getMeta1() {
		return this.meta1;
	}

	public String getMeta2() {
		return this.meta2;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setMeta1(String meta1) {
		this.meta1 = meta1;
	}

	public void setMeta2(String meta2) {
		this.meta2 = meta2;
	}

	@Transient
	public String getLogFormName() {
		return "系统变量管理";
	}

	@Transient
	public String getLogTitle() {
		return this.masterId;
	}

	@Transient
	public PlatformConstant.LogType getLogType() {
		return PlatformConstant.LogType.system_manage;
	}
}
