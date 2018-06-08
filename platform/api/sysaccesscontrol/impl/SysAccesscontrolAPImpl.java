package com.piedpiper.platform.api.sysaccesscontrol.impl;

import com.piedpiper.platform.api.sysaccesscontrol.SysAccesscontrolAPI;
import com.piedpiper.platform.api.sysaccesscontrol.dto.SysAccesscontrol;
import com.piedpiper.platform.api.sysresource.dto.SysResource;
import com.piedpiper.platform.core.dao.PropertyFilter;
import com.piedpiper.platform.core.dao.PropertyFilter.MatchType;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheManager;
import com.piedpiper.platform.core.rest.client.RestClient;
import com.piedpiper.platform.core.rest.client.RestClientConfig;
import com.piedpiper.platform.core.rest.msg.ResponseMsg;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.GenericType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class SysAccesscontrolAPImpl implements SysAccesscontrolAPI {
	private static final Logger log = LoggerFactory.getLogger(SysAccesscontrolAPImpl.class);

	@Autowired
	private BaseCacheManager baseCacheManager;

	public List<SysAccesscontrol> getSysAccesscontrol4AuthObject(String targetType, String targetId) {
		List<SysAccesscontrol> list = this.baseCacheManager
				.getAllFromCache("PLATFORM6_ACCESSCONTROL_TYPE_ID_" + targetType + "_" + targetId, new TypeReference() {
				});
		for (SysAccesscontrol sysAccesscontrol : list) {
			SysResource sysResource = (SysResource) this.baseCacheManager.getObjectFromCache("PLATFORM6_SYSRESOURCE",
					sysAccesscontrol.getResoureId(), new TypeReference() {
					});
			if (sysResource != null) {
				sysAccesscontrol.setTargetType(sysResource.getType());
			}
		}
		return list;
	}

	public Integer insertSysAccesscontrol(List<SysAccesscontrol> list) {
		String url = RestClientConfig.getRestHost("sysresource")
				+ "/api/platform6/centralizedauthorization/SysAccesscontrol/insert4List/v1";
		ResponseMsg<Integer> responseMsg = RestClient.doPost(url, list, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {
			log.error("url:" + url + ",arg:" + list + ",error:" + responseMsg.getErrorDesc());
		}
		return (Integer) responseMsg.getResponseBody();
	}

	public void deleteSysAccesscontrolById(String id) {
		String url = RestClientConfig.getRestHost("sysresource")
				+ "/api/platform6/centralizedauthorization/SysAccesscontrol/deleteById/v1";
		ResponseMsg<String> responseMsg = RestClient.doPost(url, id, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {
			log.error("url:" + url + ",arg:" + id + ",error:" + responseMsg.getErrorDesc());
		}
	}

	public void deleteSysAccesscontrol(SysAccesscontrol sysAccesscontrol) {
		String url = RestClientConfig.getRestHost("sysresource")
				+ "/api/platform6/centralizedauthorization/SysAccesscontrol/delete/v1";
		ResponseMsg<SysAccesscontrol> responseMsg = RestClient.doPost(url, sysAccesscontrol, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {
			log.error("url:" + url + ",arg:" + sysAccesscontrol + ",error:" + responseMsg.getErrorDesc());
		}
	}

	public List<SysAccesscontrol> getSysAccesscontrolListByHQL(String field, String value,
			PropertyFilter.MatchType type) {
		Map<String, Object> queryMap = new HashMap();
		queryMap.put("field", field);
		queryMap.put("value", value);
		queryMap.put("type", type);
		String url = RestClientConfig.getRestHost("sysresource")
				+ "/api/platform6/centralizedauthorization/SysAccesscontrol/getByHQL/v1";
		ResponseMsg<List<SysAccesscontrol>> responseMsg = RestClient.doPost(url, queryMap, new GenericType() {
		});
		if (responseMsg.getRetCode().equals("200")) {
			return (List) responseMsg.getResponseBody();
		}
		log.error("url:" + url + ",arg:" + queryMap + ",error:" + responseMsg.getErrorDesc());
		return null;
	}
}
