package com.piedpiper.platform.api.quartz.dto;

import java.io.Serializable;
import java.util.Date;

import com.piedpiper.platform.core.domain.BeanDTO;

public abstract class BaseBean extends BeanDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private Date createDate;
	private Date updateDate;
	private String createUser;
	private String updateUser;

	public BaseBean() {
	}

	public BaseBean(String id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
