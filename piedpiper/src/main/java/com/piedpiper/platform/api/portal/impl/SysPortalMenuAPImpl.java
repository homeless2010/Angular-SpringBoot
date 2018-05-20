package com.piedpiper.platform.api.portal.impl;

import java.util.List;

import javax.ws.rs.core.GenericType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.type.TypeReference;
import com.piedpiper.platform.api.portal.SysPortalMenuAPI;
import com.piedpiper.platform.api.portal.dto.SysPortalMenu;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheManager;
import com.piedpiper.platform.core.rest.client.RestClient;
import com.piedpiper.platform.core.rest.client.RestClientConfig;
import com.piedpiper.platform.core.rest.msg.ResponseMsg;

public class SysPortalMenuAPImpl implements SysPortalMenuAPI {
	private static final Logger log = LoggerFactory.getLogger(SysPortalMenuAPImpl.class);

	@Autowired
	private BaseCacheManager baseCacheManager;

	public void deleltePortalMenuByMenuId(String menuId) {
		String urlU = RestClientConfig.getRestHost("sysPortal")
				+ "/api/platform6/sysportal/Sysportal/deletePortletMenuByMenuId/" + menuId + "/v1";
		ResponseMsg<Void> responseMsgU = RestClient.doPost(urlU, Integer.valueOf(1), new GenericType() {
		});
		if (!"200".equals(responseMsgU.getRetCode())) {
			log.error("url:" + urlU + ",error:" + responseMsgU.getErrorDesc());
		}
	}

	public List<SysPortalMenu> getPortalMenusByConfigId(String configId) {
		return this.baseCacheManager.getAllFromCache("PLATFORM6_PORTLETMENU_CONFIG_" + configId, new TypeReference() {
		});
	}

	public SysPortalMenu getPortalMenuById(String id) {
		return (SysPortalMenu) this.baseCacheManager.getObjectFromCache("PLATFORM6_PORTLETMENU", id,
				new TypeReference() {
				});
	}

	public List<SysPortalMenu> getPortalMenusByConfigIdAndPid(String configId, String parendtId) {
		return this.baseCacheManager.getAllFromCache(
				"PLATFORM6_PORTLETMENU_CONFIG_PARENT_" + configId + "_" + parendtId, new TypeReference() {
				});
	}
}
