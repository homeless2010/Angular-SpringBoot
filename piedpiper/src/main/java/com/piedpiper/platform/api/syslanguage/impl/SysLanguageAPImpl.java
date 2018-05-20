package com.piedpiper.platform.api.syslanguage.impl;

import com.piedpiper.platform.api.syscustomed.SysCustomedAPI;
import com.piedpiper.platform.api.syslanguage.SysLanguageAPI;
import com.piedpiper.platform.api.syslanguage.dto.SysLanguage;
import com.piedpiper.platform.api.sysuser.SysUserAPI;
import com.piedpiper.platform.api.sysuser.dto.SysUser;
import com.piedpiper.platform.core.properties.PlatformProperties;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheManager;
import com.piedpiper.platform.core.rest.client.RestClient;
import com.piedpiper.platform.core.rest.client.RestClientConfig;
import com.piedpiper.platform.core.rest.msg.ResponseMsg;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import javax.ws.rs.core.GenericType;
import org.springframework.beans.factory.annotation.Autowired;

public class SysLanguageAPImpl implements SysLanguageAPI {
	@Autowired
	private BaseCacheManager baseCacheManager;
	@Autowired
	private SysCustomedAPI sysCustomed;
	@Autowired
	private SysUserAPI userAPI;

	public void reLoad() throws Exception {
		String url = RestClientConfig.getRestHost("syslanguage") + "/api/platform6/syslanguage/SysLanguage/reLoad/v1";
		ResponseMsg<Void> responseMsg = RestClient.doGet(url, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {
			throw new Exception(responseMsg.getErrorDesc());
		}
	}

	public List<SysLanguage> getAllSysLanguages() {
		return this.baseCacheManager.getAllFromCache("PLATFORM6_SYSLANGUAGE", new TypeReference() {
		});
	}

	public SysLanguage getSysDefaultLanguage() {
		return getSysLanguageByCode("zh_CN");
	}

	public SysLanguage getSysLanguageById(String languageId) {
		return (SysLanguage) this.baseCacheManager.getObjectFromCache("PLATFORM6_SYSLANGUAGE", languageId,
				new TypeReference() {
				});
	}

	public SysLanguage getSysLanguageByCode(String languageCode) {
		return (SysLanguage) this.baseCacheManager.getObjectFromCache("PLATFORM6_SYSLANGUAGECODE", languageCode,
				new TypeReference() {
				});
	}

	public String getCurrentLanguageCode() {
		return PlatformProperties.getProperty("platform.default.languageCode");
	}

	public String getSystemDefaultLanguageCode() {
		return "zh_CN";
	}

	public String getCurrentUserLanguageCode(String userId) {
		return getCurrentUserLanguageCode(userId, RestClientConfig.systemid);
	}

	public String getCurrentUserLanguageCode(String userId, String appId) {
		String code = this.sysCustomed.getCustomedValueByKey("PLATFORM_USER_SKIN", userId, appId);
		if (code == null) {
			return this.userAPI.getSysUserById(userId).getLanguageCode();
		}
		return code;
	}
}
