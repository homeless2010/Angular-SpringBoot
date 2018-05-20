package com.piedpiper.platform.api.syslookup.dto;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Transient;

import com.piedpiper.platform.core.domain.BeanDTO;
import com.piedpiper.platform.core.properties.PlatformConstant;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheBean;

public class SysSecretRelation extends BeanDTO implements BaseCacheBean {
	private static final long serialVersionUID = 1L;
	public static String SYSSECRETRELATION = "PLATFORM6_SYSSECRETRELATION";
	public static String SYSSECRETRELATION_WORD_USER = "PLATFORM6_SYSSECRETRELATION_WORD_USER";
	private String id;
	private String wordSecret;
	private String userSecret;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWordSecret() {
		return this.wordSecret;
	}

	public void setWordSecret(String wordSecret) {
		this.wordSecret = wordSecret;
	}

	public String getUserSecret() {
		return this.userSecret;
	}

	public void setUserSecret(String userSecret) {
		this.userSecret = userSecret;
	}

	@Transient
	public String getLogFormName() {
		if ((this.logFormName == null) || (this.logFormName.equals(""))) {
			return "人员密级与文档密级关系表";
		}
		return this.logFormName;
	}

	@Transient
	public String getLogTitle() {
		if ((this.logTitle == null) || (this.logTitle.equals(""))) {
			return "SYSSECRETRELATION";
		}
		return this.logTitle;
	}

	@Transient
	public PlatformConstant.LogType getLogType() {
		if ((this.logType == null) || (this.logType.equals(""))) {
			return PlatformConstant.LogType.system_manage;
		}
		return this.logType;
	}

	public Map<String, ?> returnCacheKey() {
		Map<String, Object> map = new HashMap();
		map.put(SYSSECRETRELATION, this.id);
		map.put(SYSSECRETRELATION_WORD_USER, this.wordSecret + "_" + this.userSecret);
		return map;
	}

	public String prefix() {
		return SYSSECRETRELATION;
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
