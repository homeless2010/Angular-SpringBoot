package com.piedpiper.platform.api.sysmenu.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Id;

import com.piedpiper.platform.core.annotation.log.LogField;
import com.piedpiper.platform.core.domain.BeanDTO;
import com.piedpiper.platform.core.properties.PlatformConstant;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheBean;

public class SysMenuTl extends BeanDTO implements Serializable, BaseCacheBean {
	private static final long serialVersionUID = 1L;
	public static final String MENUTL = "PLATFORM6_MENUTL";
	public static final String MENUTLUNITID = "PLATFORM6_MENUTLUNITID";
	private String id;
	private String sysMenuId;
	private String sysLanguageCode;
	private String sysLanguageName;
	private String name;
	private String comments;

	@Id
	@Column(name = "ID", nullable = false, length = 50)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@LogField
	@Column(name = "SYS_MENU_ID", nullable = false, length = 50)
	public String getSysMenuId() {
		return this.sysMenuId;
	}

	public void setSysMenuId(String sysMenuId) {
		this.sysMenuId = sysMenuId;
	}

	@LogField
	@Column(name = "SYS_LANGUAGE_CODE", nullable = false, length = 50)
	public String getSysLanguageCode() {
		return this.sysLanguageCode;
	}

	public void setSysLanguageCode(String sysLanguageCode) {
		this.sysLanguageCode = sysLanguageCode;
	}

	@LogField
	@Column(name = "NAME", nullable = false, length = 100)
	public String getName() {
		if (this.name == null) {
			return "";
		}
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "COMMENTS", nullable = true, length = 2000)
	public String getComments() {
		return this.comments;
	}

	public void setComments(String Comments) {
		this.comments = Comments;
	}

	public String getSysLanguageName() {
		return this.sysLanguageName;
	}

	public void setSysLanguageName(String sysLanguageName) {
		this.sysLanguageName = sysLanguageName;
	}

	public String getLogFormName() {
		return null;
	}

	public String getLogTitle() {
		return null;
	}

	public PlatformConstant.LogType getLogType() {
		return null;
	}

	public Map<String, ?> returnCacheKey() {
		Map<String, Object> map = new HashMap();
		map.put("PLATFORM6_MENUTL", this.id);
		map.put("PLATFORM6_MENUTLUNITID", this.sysMenuId + "_" + this.sysLanguageCode);
		return map;
	}

	public String prefix() {
		return "PLATFORM6_MENUTL";
	}

	public String returnValidFlag() {
		return null;
	}

	public String returnId() {
		return this.id;
	}

	public String returnAppId() {
		return null;
	}
}
