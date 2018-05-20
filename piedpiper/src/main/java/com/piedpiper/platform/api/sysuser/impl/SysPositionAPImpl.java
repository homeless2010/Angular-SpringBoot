package com.piedpiper.platform.api.sysuser.impl;

import com.piedpiper.platform.api.sysuser.SysPositionAPI;
import com.piedpiper.platform.api.sysuser.dto.SysPosition;
import com.piedpiper.platform.api.sysuser.dto.SysPositionTl;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheManager;
import com.piedpiper.platform.core.rest.client.RestClient;
import com.piedpiper.platform.core.rest.client.RestClientConfig;
import com.piedpiper.platform.core.rest.msg.Muti3Bean;
import com.piedpiper.platform.core.rest.msg.ResponseMsg;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.GenericType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

public class SysPositionAPImpl implements SysPositionAPI {
	@Autowired
	private BaseCacheManager baseCacheManager;

	public List<SysPosition> getAllSysPositionList() {
		this.baseCacheManager.getAllFromCache("PLATFORM6_POSITION", new TypeReference() {
		});
	}

	public List<SysPosition> getAllSysPositionListByOrgId(String orgId) {
		this.baseCacheManager.getAllFromCache("PLATFORM6_POSITION_ORG_" + orgId, new TypeReference() {
		});
	}

	public SysPositionTl getSysPositionTl(String sysPositionId, String sysLanguageCode)
   {
     (SysPositionTl)this.baseCacheManager.getObjectFromCache("PLATFORM6_POSITIONTL", sysPositionId + "_" + sysLanguageCode, new TypeReference() {});
   }

	public SysPosition getSysPositionBySysPositionId(String sysPositionId)
   {
     (SysPosition)this.baseCacheManager.getObjectFromCache("PLATFORM6_POSITION", sysPositionId, new TypeReference() {});
   }

	public String getSysPositionNameBySysPositionId(String sysPositionId, String languageCode) {
		if (StringUtils.isEmpty(sysPositionId)) {
			return "";
		}
		SysPositionTl positiont = getSysPositionTl(sysPositionId, languageCode);
		if (positiont != null) {
			return getSysPositionTl(sysPositionId, languageCode).getPositionName();
		}
		return "岗位无效或者不存在";
	}

	public String getSysPositionCodeBySysPositionId(String sysPositionId) {
		return getSysPositionBySysPositionId(sysPositionId).getPositionCode();
	}

	public String getSysPositionIdBySysPositionCode(String sysPositionCode) {
		SysPosition sysPosition = (SysPosition) this.baseCacheManager.getObjectFromCache("PLATFORM6_POSITIONCODE",
				sysPositionCode, SysPosition.class);
		return sysPosition.getId();
	}

	public String getSysPositionNamesBySysPositionCodes(String sysPositionCodes, String regex, String languageCode) {
		StringBuffer string = new StringBuffer();
		String[] sysPositionCodeArr = sysPositionCodes.split(regex);
		for (String sysPositionCode : sysPositionCodeArr) {
			String sysPositionId = getSysPositionIdBySysPositionCode(sysPositionCode);
			String sysPositionName = getSysPositionNameBySysPositionId(sysPositionId, languageCode);
			string.append(sysPositionName).append(regex);
		}
		if (string.length() > 0) {
			return string.substring(0, string.length() - regex.length());
		}
		return string.toString();
	}

	public String getSysPositionNamesBySysPositionIds(String sysPositionIds, String regex, String languageCode) {
		StringBuffer string = new StringBuffer();
		String[] sysPositionIdArr = sysPositionIds.split(regex);
		for (String sysPositionId : sysPositionIdArr) {
			String sysPositionName = getSysPositionNameBySysPositionId(sysPositionId, languageCode);
			string.append(sysPositionName).append(regex);
		}
		if (string.length() > 0) {
			return string.substring(0, string.length() - regex.length());
		}
		return string.toString();
	}

	public SysPositionTl getSysPositionTlWithoutLanguageCode(String sysPositionId) {
		return getSysPositionTl(sysPositionId, "zh_CN");
	}

	public boolean checkPositionCode(String positionCode) {
		return this.baseCacheManager.containsKey("PLATFORM6_POSITIONCODE", positionCode);
	}

	public void insertSysPosition(SysPosition sysPosition, String orgId) {
		Muti3Bean<SysPosition, String, Map<String, Object>> args = new Muti3Bean();
		args.setDto1(sysPosition);
		args.setDto2(orgId);
		Map<String, Object> map = new HashMap();
		map.put("currentLanguageCode", "zh_CN");
		args.setDto3(map);
		String url = RestClientConfig.getRestHost("sysuser") + "/api/platform6/sysorguser/SysPosition/insert/v1";
		ResponseMsg<Void> responseMsg = RestClient.doPost(url, args, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {

			throw new RuntimeException(responseMsg.getErrorDesc());
		}
	}

	public void updateSysPosition(SysPosition sysPosition) {
		String url = RestClientConfig.getRestHost("sysuser")
				+ "/api/platform6/sysorguser/SysPosition/updateSysPosition/v1";
		ResponseMsg<Void> responseMsg = RestClient.doPost(url, sysPosition, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {

			throw new RuntimeException(responseMsg.getErrorDesc());
		}
	}

	public void deleteSysPosition(SysPosition sysPosition) {
		String url = RestClientConfig.getRestHost("sysuser")
				+ "/api/platform6/sysorguser/SysPosition/deleteSysPosition/v1";
		ResponseMsg<Void> responseMsg = RestClient.doPost(url, sysPosition, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {

			throw new RuntimeException(responseMsg.getErrorDesc());
		}
	}

	public void insertSysPositionTl(SysPositionTl sysPositionTl) {
		String url = RestClientConfig.getRestHost("sysuser")
				+ "/api/platform6/sysorguser/SysPosition/insertSysPositionTl/v1";
		ResponseMsg<Void> responseMsg = RestClient.doPost(url, sysPositionTl, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {

			throw new RuntimeException(responseMsg.getErrorDesc());
		}
	}

	public void updateSysPositionTl(SysPositionTl sysPositionTl) {
		String url = RestClientConfig.getRestHost("sysuser")
				+ "/api/platform6/sysorguser/SysPosition/updateSysPositionTl/v1";
		ResponseMsg<Void> responseMsg = RestClient.doPost(url, sysPositionTl, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {

			throw new RuntimeException(responseMsg.getErrorDesc());
		}
	}

	public void deleteSysPositionTl(SysPositionTl sysPositionTl) {
		String url = RestClientConfig.getRestHost("sysuser")
				+ "/api/platform6/sysorguser/SysPosition/deleteSysPositionTl/v1";
		ResponseMsg<Void> responseMsg = RestClient.doPost(url, sysPositionTl, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {

			throw new RuntimeException(responseMsg.getErrorDesc());
		}
	}
}
