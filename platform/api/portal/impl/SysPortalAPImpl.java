package com.piedpiper.platform.api.portal.impl;

import com.piedpiper.platform.api.portal.SysPortalAPI;
import com.piedpiper.platform.api.portal.dto.SysPortal;
import com.piedpiper.platform.api.portal.dto.SysPortletConfig;
import com.piedpiper.platform.api.session.SessionHelper;
import com.piedpiper.platform.core.rest.client.RestClient;
import com.piedpiper.platform.core.rest.client.RestClientConfig;
import com.piedpiper.platform.core.rest.msg.Muti1Bean;
import com.piedpiper.platform.core.rest.msg.ResponseMsg;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.GenericType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SysPortalAPImpl implements SysPortalAPI {
	private static final Logger log = LoggerFactory.getLogger(SysPortalAPImpl.class);

	public SysPortal getSysPortalBySysUserId(String userId, String appid) {
		SysPortal sysPortal = null;
		String url = RestClientConfig.getRestHost("sysPortal")
				+ "/api/platform6/sysportal/Sysportal/getSysPortalBySysUserId/" + userId + "/" + appid + "/v1";
		ResponseMsg<SysPortal> responseMsg = RestClient.doGet(url, new GenericType() {
		});
		if (responseMsg.getRetCode().equals("200")) {
			sysPortal = (SysPortal) responseMsg.getResponseBody();
		} else {
			log.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
		}
		return sysPortal;
	}

	public List<SysPortletConfig> getList(String appid) {
		List<SysPortletConfig> portletConfigList = null;
		String urlList = RestClientConfig.getRestHost("sysPortal") + "/api/platform6/sysportal/Sysportal/getList/"
				+ appid + "/v1";
		ResponseMsg<List<SysPortletConfig>> responseMsgList = RestClient.doGet(urlList, new GenericType() {
		});
		if (responseMsgList.getRetCode().equals("200")) {
			portletConfigList = (List) responseMsgList.getResponseBody();
		} else {
			log.error("url:" + urlList + ",error:" + responseMsgList.getErrorDesc());
		}
		return portletConfigList;
	}

	public SysPortletConfig getSysPortletConfigById(String id) {
		SysPortletConfig sysPortletConfig = null;
		String url = RestClientConfig.getRestHost("sysPortal")
				+ "/api/platform6/sysportal/Sysportal/getSysPortletConfigById/" + id + "/v1";
		ResponseMsg<SysPortletConfig> responseMsg = RestClient.doGet(url, new GenericType() {
		});
		if (responseMsg.getRetCode().equals("200")) {
			sysPortletConfig = (SysPortletConfig) responseMsg.getResponseBody();
		} else {
			log.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
		}
		return sysPortletConfig;
	}

	public void insertSysPortal(SysPortal sysPortal) {
		String url = RestClientConfig.getRestHost("sysPortal")
				+ "/api/platform6/sysportal/Sysportal/insertSysPortal/v1";
		ResponseMsg<String> responseMsgU = RestClient.doPost(url, sysPortal, new GenericType() {
		});
		if (!responseMsgU.getRetCode().equals("200")) {
			log.error("url:" + url + ",error:" + responseMsgU.getErrorDesc());
		}
	}

	public void updateSysPortal(SysPortal sysPortal) {
		String urlU = RestClientConfig.getRestHost("sysPortal")
				+ "/api/platform6/sysportal/Sysportal/updateSysPortal/v1";
		ResponseMsg<String> responseMsgU = RestClient.doPost(urlU, sysPortal, new GenericType() {
		});
		if (!responseMsgU.getRetCode().equals("200")) {
			log.error("url:" + urlU + ",error:" + responseMsgU.getErrorDesc());
		}
	}

	public void updateSysPortletConfig(SysPortletConfig sysPortletConfig) {
		String urlU = RestClientConfig.getRestHost("sysPortal")
				+ "/api/platform6/sysportal/Sysportal/updateSysPortletConfig/v1";
		ResponseMsg<String> responseMsgU = RestClient.doPost(urlU, sysPortletConfig, new GenericType() {
		});
		if (!responseMsgU.getRetCode().equals("200")) {
			log.error("url:" + urlU + ",error:" + responseMsgU.getErrorDesc());
		}
	}

	public void updateSysPortletConfigForIndex(String id, String templet, String portlet) {
		Map<String, String> map = new HashMap();
		if (id != null) {
			map.put("id", id);
		}
		if (templet != null) {
			map.put("templet", templet);
		}
		if (portlet != null) {
			map.put("portlet", portlet);
		}
		String url = RestClientConfig.getRestHost("sysPortal")
				+ "/api/platform6/sysportal/Sysportal/updateSysPortletConfigForIndex/v1";
		ResponseMsg<String> responseMsgU = RestClient.doPost(url, map, new GenericType() {
		});
		if (!responseMsgU.getRetCode().equals("200")) {
			log.error("url:" + url + ",error:" + responseMsgU.getErrorDesc());
		}
	}

	public void deleteSysPortalById(String id) {
		String url = RestClientConfig.getRestHost("sysPortal")
				+ "/api/platform6/sysportal/Sysportal/deleteSysPortalById/v1";
		ResponseMsg<String> responseMsg = RestClient.doPost(url, id, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {
			log.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
		}
	}

	public void insertOrUpdateSysPortletConfig(SysPortletConfig[] sysPortletConfig) {
		String urlU = RestClientConfig.getRestHost("sysPortal")
				+ "/api/platform6/sysportal/Sysportal/insertOrUpdateSysPortletConfig/"
				+ SessionHelper.getApplicationId() + "/v1";
		ResponseMsg<String> responseMsgU = RestClient.doPost(urlU, sysPortletConfig, new GenericType() {
		});
		if (!responseMsgU.getRetCode().equals("200")) {
			log.error("url:" + urlU + ",error:" + responseMsgU.getErrorDesc());
		}
	}

	public void deleteSysPortletConfigByIds(String[] ids) {
		Muti1Bean<String[]> bean = new Muti1Bean(ids);
		String urlU = RestClientConfig.getRestHost("sysPortal")
				+ "/api/platform6/sysportal/Sysportal/deleteSysPortletConfigByIds/v1";
		ResponseMsg<String> responseMsgU = RestClient.doPost(urlU, bean, new GenericType() {
		});
		if (!responseMsgU.getRetCode().equals("200")) {
			log.error("url:" + urlU + ",error:" + responseMsgU.getErrorDesc());
		}
	}
}
