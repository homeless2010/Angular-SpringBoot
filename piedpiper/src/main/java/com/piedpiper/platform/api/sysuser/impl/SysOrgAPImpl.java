package com.piedpiper.platform.api.sysuser.impl;

import com.piedpiper.platform.api.sysuser.SysOrgAPI;
import com.piedpiper.platform.api.sysuser.dto.SysOrg;
import com.piedpiper.platform.api.sysuser.dto.SysOrgTl;
import com.piedpiper.platform.api.sysuser.dto.SysOrgVo;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheManager;
import com.piedpiper.platform.core.rest.client.RestClient;
import com.piedpiper.platform.core.rest.client.RestClientConfig;
import com.piedpiper.platform.core.rest.msg.Muti2Bean;
import com.piedpiper.platform.core.rest.msg.ResponseMsg;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.GenericType;
import org.springframework.beans.factory.annotation.Autowired;

public class SysOrgAPImpl implements SysOrgAPI {
	@Autowired
	private BaseCacheManager baseCacheManager;

	public List<SysOrg> getAllSysOrgList() {
		this.baseCacheManager.getAllFromCache("PLATFORM6_ORG", new TypeReference() {
		});
	}

	public SysOrgTl getSysOrgTl(String sysOrgId, String sysLanguageCode)
   {
     (SysOrgTl)this.baseCacheManager.getObjectFromCache("PLATFORM6_ORGTL", sysOrgId + "_" + sysLanguageCode, new TypeReference() {});
   }

	public SysOrg getSysOrgBySysOrgId(String sysOrgId)
   {
     (SysOrg)this.baseCacheManager.getObjectFromCache("PLATFORM6_ORG", sysOrgId, new TypeReference() {});
   }

	public String getSysOrgNameBySysOrgId(String sysOrgId, String languageCode) {
		return getSysOrgTl(sysOrgId, languageCode).getOrgName();
	}

	public String getSysOrgNamesBySysOrgIds(String sysOrgIds, String regex, String languageCode) {
		StringBuffer string = new StringBuffer();
		String[] sysOrgIdArr = sysOrgIds.split(regex);
		for (String sysOrgId : sysOrgIdArr) {
			String sysOrgName = getSysOrgNameBySysOrgId(sysOrgId, languageCode);
			string.append(sysOrgName).append(regex);
		}
		if (string.length() > 0) {
			return string.substring(0, string.length() - regex.length());
		}
		return string.toString();
	}

	public List<SysOrg> getSubSysOrgListBySysOrgId(String sysOrgId) {
		this.baseCacheManager.getAllFromCache("PLATFORM6_ORG_ORG_" + sysOrgId, new TypeReference() {
		});
	}

	public SysOrg getParentSysOrgBySysOrgId(String sysOrgId) {
		SysOrg sysOrg = getSysOrgBySysOrgId(sysOrgId);
		return getSysOrgBySysOrgId(sysOrg.getParentOrgId());
	}

	public String getParentSysOrgIdBySysOrgId(String sysOrgId) {
		return getParentSysOrgBySysOrgId(sysOrgId).getId();
	}

	public List<SysOrg> getAllSubSysOrgListBySysOrgId(String sysOrgId) {
		List<SysOrg> list = new ArrayList();
		List<SysOrg> subList = getSubSysOrgListBySysOrgId(sysOrgId);
		if (subList != null) {
			list.addAll(subList);
			for (SysOrg sysOrg : subList) {
				list.addAll(getAllSubSysOrgListBySysOrgId(sysOrg.getId()));
			}
		}
		return list;
	}

	public String getAllSubSysOrgIdBySysOrgId(String sysOrgId, String regex) {
		StringBuffer string = new StringBuffer();
		List<SysOrg> list = getAllSubSysOrgListBySysOrgId(sysOrgId);
		for (SysOrg sysOrg : list) {
			string.append(sysOrg.getId()).append(regex);
		}

		if ((!string.equals("")) && (string.length() >= 1)) {
			string.substring(0, string.length() - regex.length());
		}
		if (string.length() > 0) {
			return string.substring(0, string.length() - regex.length());
		}

		return string.toString();
	}

	public List<SysOrg> getAllParentSysOrgListBySysOrgId(String sysOrgId) {
		List<SysOrg> list = new ArrayList();
		SysOrg parentSysOrg = getParentSysOrgBySysOrgId(sysOrgId);
		if (parentSysOrg != null) {
			list.add(parentSysOrg);
			list.addAll(getAllParentSysOrgListBySysOrgId(parentSysOrg.getId()));
		}
		return list;
	}

	public String getAllParentSysOrgIdBySysOrgId(String sysOrgId, String regex) {
		StringBuffer string = new StringBuffer();
		List<SysOrg> list = getAllParentSysOrgListBySysOrgId(sysOrgId);
		for (SysOrg sysOrg : list) {
			string.append(sysOrg.getId()).append(regex);
		}
		if (string.length() > 0) {
			return string.substring(0, string.length() - regex.length());
		}
		return string.toString();
	}

	public boolean checkOrgCode(String orgCode) {
		return this.baseCacheManager.containsKey("PLATFORM6_ORGCODE", orgCode);
	}

	public boolean existsChilds(String orgId) {
		return this.baseCacheManager.exists("PLATFORM6_ORG_ORG_" + orgId);
	}

	public String getSysOrgIdByCode(String orgCode)
   {
     (String)this.baseCacheManager.getObjectFromCache("PLATFORM6_ORGCODE", orgCode, new TypeReference() {});
   }

	public void insertSysOrg(SysOrgVo sysOrg) {
		Muti2Bean<SysOrgVo, Map<String, Object>> args = new Muti2Bean();
		args.setDto1(sysOrg);
		Map<String, Object> map = new HashMap();
		map.put("currentLanguageCode", "zh_CN");
		args.setDto2(map);
		String url = RestClientConfig.getRestHost("sysuser") + "/api/platform6/sysorguser/SysOrgContrl/insert/v1";
		ResponseMsg<Map<String, Object>> responseMsg = RestClient.doPost(url, args, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {

			throw new RuntimeException(responseMsg.getErrorDesc());
		}
	}

	public void updateSysOrg(SysOrgVo sysOrg) {
		Muti2Bean<SysOrgVo, Map<String, Object>> args = new Muti2Bean();
		args.setDto1(sysOrg);
		Map<String, Object> map = new HashMap();
		map.put("currentLanguageCode", "zh_CN");
		args.setDto2(map);
		String url = RestClientConfig.getRestHost("sysuser") + "/api/platform6/sysorguser/SysOrgContrl/update/v1";
		ResponseMsg<Map<String, Object>> responseMsg = RestClient.doPost(url, args, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {

			throw new RuntimeException(responseMsg.getErrorDesc());
		}
	}

	public void deleteSysOrg(SysOrgVo sysOrg) {
		Muti2Bean<SysOrgVo, Map<String, Object>> args = new Muti2Bean();
		args.setDto1(sysOrg);
		Map<String, Object> map = new HashMap();
		map.put("currentLanguageCode", "zh_CN");
		args.setDto2(map);
		String url = RestClientConfig.getRestHost("sysuser") + "/api/platform6/sysorguser/SysOrgContrl/delete/v1";
		ResponseMsg<Void> responseMsg = RestClient.doPost(url, args, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {

			throw new RuntimeException(responseMsg.getErrorDesc());
		}
	}
}
