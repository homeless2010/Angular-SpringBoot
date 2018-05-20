package com.piedpiper.platform.api.syslanguage.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.piedpiper.platform.core.domain.BeanDTO;
import com.piedpiper.platform.core.properties.PlatformConstant;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheBean;

public class SysLanguage extends BeanDTO implements Serializable, BaseCacheBean {
	private static final long serialVersionUID = 1L;
	public static final String SYSLANGUAGE = "PLATFORM6_SYSLANGUAGE";
	public static final String SYSLANGUAGECODE = "PLATFORM6_SYSLANGUAGECODE";
	private String id;
	private String languageCode;
	private String languageName;
	private BigDecimal orderBy;
	private Long isSystemDefault;

	@Id
	@Column(name = "ID")
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "LANGUAGE_CODE")
	@NotNull
	public String getLanguageCode() {
		return this.languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	@Column(name = "LANGUAGE_NAME")
	@NotNull
	public String getLanguageName() {
		return this.languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	@Column(name = "ORDER_BY")
	public BigDecimal getOrderBy() {
		return this.orderBy;
	}

	public void setOrderBy(BigDecimal orderBy) {
		this.orderBy = orderBy;
	}

	@Column(name = "IS_SYSTEM_DEFAULT")
	public Long getIsSystemDefault() {
		return this.isSystemDefault;
	}

	public void setIsSystemDefault(Long isSystemDefault) {
		this.isSystemDefault = isSystemDefault;
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
		map.put("PLATFORM6_SYSLANGUAGE", getId());
		map.put("PLATFORM6_SYSLANGUAGECODE", getLanguageCode());
		return map;
	}

	public String prefix() {
		return "PLATFORM6_SYSLANGUAGE";
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
