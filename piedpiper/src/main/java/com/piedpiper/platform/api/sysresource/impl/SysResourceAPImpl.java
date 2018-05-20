package com.piedpiper.platform.api.sysresource.impl;

import com.piedpiper.platform.api.sysresource.SysResourceAPI;
import com.piedpiper.platform.api.sysresource.dto.SysResource;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheManager;
import com.piedpiper.platform.core.rest.client.RestClient;
import com.piedpiper.platform.core.rest.client.RestClientConfig;
import com.piedpiper.platform.core.rest.msg.ResponseMsg;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import javax.ws.rs.core.GenericType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class SysResourceAPImpl implements SysResourceAPI {
	private static final Logger log = LoggerFactory.getLogger(SysResourceAPImpl.class);
	@Autowired
	private BaseCacheManager baseCacheManager;

	public void reLoad() throws Exception {
		String url = RestClientConfig.getRestHost("sysresource")
				+ "/api/platform6/centralizedauthorization/SysResource/reLoad/v1";
		ResponseMsg<Void> responseMsg = RestClient.doGet(url, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {
			throw new Exception(responseMsg.getErrorDesc());
		}
	}

	public SysResource getSysResourceById(String id) {
		return (SysResource) this.baseCacheManager.getObjectFromCache("PLATFORM6_SYSRESOURCE", id, new TypeReference() {
		});
	}

	public SysResource getSysResourceByValue(String value, String appId) {
		return (SysResource) this.baseCacheManager.getObjectFromCache("PLATFORM6_SYSRESOURCE_APPID_" + appId, value,
				new TypeReference() {
				});
	}

	public List<SysResource> getAllList() {
		return this.baseCacheManager.getAllFromCache("PLATFORM6_SYSRESOURCE", new TypeReference() {
		});
	}

	public void insertSysResource(SysResource SysResource) {
		String url = RestClientConfig.getRestHost("sysresource")
				+ "/api/platform6/centralizedauthorization/SysResource/insert/v1";
		ResponseMsg<SysResource> responseMsg = RestClient.doPost(url, SysResource, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {
			log.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
		}
	}

	public void insertSysResource(List<SysResource> sysResources) {
		String url = RestClientConfig.getRestHost("sysresource")
				+ "/api/platform6/centralizedauthorization/SysResource/insertByList/v1";
		ResponseMsg<List<SysResource>> responseMsg = RestClient.doPost(url, sysResources, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {
			log.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
		}
	}

	public void updateSysResource(SysResource sysResource) {
		String url = RestClientConfig.getRestHost("sysresource")
				+ "/api/platform6/centralizedauthorization/SysResource/update/v1";
		ResponseMsg<SysResource> responseMsg = RestClient.doPost(url, sysResource, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {
			log.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
		}
	}

	public void deleteSysResource(SysResource SysResource) {
		String url = RestClientConfig.getRestHost("sysresource")
				+ "/api/platform6/centralizedauthorization/SysResource/delete/v1";
		ResponseMsg<SysResource> responseMsg = RestClient.doPost(url, SysResource, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {
			log.error("url:" + url + ",arg:" + SysResource + ",error:" + responseMsg.getErrorDesc());
		}
	}

	public void deleteSysResourceById(String id) {
		String url = RestClientConfig.getRestHost("sysresource")
				+ "/api/platform6/centralizedauthorization/SysResource/deleteById/v1";
		ResponseMsg<String> responseMsg = RestClient.doPost(url, id, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {
			log.error("url:" + url + ",arg:" + id + ",error:" + responseMsg.getErrorDesc());
		}
	}

	public List<SysResource> getSysResourceByParentId(String parentId) {
		String url = RestClientConfig.getRestHost("sysresource")
				+ "/api/platform6/centralizedauthorization/SysResource/getSysResourceByParentId/v1";
		ResponseMsg<List<SysResource>> responseMsg = RestClient.doPost(url, parentId, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {
			log.error("url:" + url + ",arg:" + parentId + ",error:" + responseMsg.getErrorDesc());
		} else {
			return (List) responseMsg.getResponseBody();
		}
		return null;
	}
}
