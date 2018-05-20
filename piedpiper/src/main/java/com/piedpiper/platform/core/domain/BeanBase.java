package com.piedpiper.platform.core.domain;

import com.piedpiper.platform.core.properties.PlatformConstant;
import com.piedpiper.platform.core.properties.PlatformConstant.LogType;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class BeanBase implements Serializable, IAppId, Cloneable {
	private static final long serialVersionUID = -8846053093880926413L;
	@Column(name = "CREATED_BY")
	protected String createdBy;
	@Column(name = "CREATION_DATE")
	protected Date creationDate;
	@Column(name = "LAST_UPDATE_DATE")
	protected Date lastUpdateDate;
	@Column(name = "LAST_UPDATED_BY")
	protected String lastUpdatedBy;
	@Column(name = "LAST_UPDATE_IP")
	protected String lastUpdateIp;
	@Transient
	protected String logFormName = null;
	@Transient
	protected String logTitle = null;
	@Transient
	protected PlatformConstant.LogType logType = null;

	@Column(name = "VERSION")
	@Version
	protected Long version;
	private String logAppId = null;

	public void setLogAppId(String logAppId) {
		this.logAppId = logAppId;
	}

	@Transient
	public String getLogAppId() {
		return this.logAppId;
	}

	@Column(name = "CREATED_BY")
	public String getCreatedBy() {
		return this.createdBy;
	}

	@Column(name = "CREATION_DATE")
	public Date getCreationDate() {
		return this.creationDate;
	}

	@Column(name = "LAST_UPDATE_DATE")
	public Date getLastUpdateDate() {
		return this.lastUpdateDate;
	}

	@Column(name = "LAST_UPDATED_BY")
	public String getLastUpdatedBy() {
		return this.lastUpdatedBy;
	}

	@Column(name = "LAST_UPDATE_IP")
	public String getLastUpdateIp() {
		return this.lastUpdateIp;
	}

	@Transient
	public abstract String getLogFormName();

	@Transient
	public abstract String getLogTitle();

	@Transient
	public PlatformConstant.LogType getLogType() {
		return PlatformConstant.LogType.module_operate;
	}

	@Column(name = "VERSION")
	@Version
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

	public String returnLogAppId() {
		return this.logAppId;
	}

	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}
}
