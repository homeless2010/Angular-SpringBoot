package com.piedpiper.platform.core.domain;

import com.piedpiper.platform.core.properties.PlatformConstant;
import com.piedpiper.platform.core.properties.PlatformConstant.LogType;
import java.io.Serializable;
import java.util.Date;

public abstract class BeanDTO implements Serializable, IAppId, Cloneable {
	private static final long serialVersionUID = 1981121902843093325L;
	protected String createdBy;
	protected Date creationDate;
	protected Date lastUpdateDate;
	protected String lastUpdatedBy;
	protected String lastUpdateIp;
	protected String logFormName = null;

	protected String logTitle = null;

	protected PlatformConstant.LogType logType = null;

	protected Long version;

	private String logAppId = null;

	private String key;

	public String returnLogAppId() {
		return this.logAppId;
	}

	public String getLogAppId() {
		return this.logAppId;
	}

	public void setLogAppId(String logAppId) {
		this.logAppId = logAppId;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public Date getLastUpdateDate() {
		return this.lastUpdateDate;
	}

	public String getLastUpdatedBy() {
		return this.lastUpdatedBy;
	}

	public String getLastUpdateIp() {
		return this.lastUpdateIp;
	}

	public abstract String getLogFormName();

	public abstract String getLogTitle();

	public PlatformConstant.LogType getLogType() {
		return PlatformConstant.LogType.module_operate;
	}

	public Long getVersion() {
		return this.version;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public void setLastUpdateIp(String lastUpdateIp) {
		this.lastUpdateIp = lastUpdateIp;
	}

	public void setLogFormName(String logFormName) {
		this.logFormName = logFormName;
	}

	public void setLogTitle(String logTitle) {
		this.logTitle = logTitle;
	}

	public void setLogType(PlatformConstant.LogType logType) {
		this.logType = logType;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}

	public String returnKey() {
		return this.key;
	}
}
