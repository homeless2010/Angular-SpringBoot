package com.piedpiper.platform.api.sysuser.impl;

import com.piedpiper.platform.api.sysuser.SysGroupAPI;
import com.piedpiper.platform.api.sysuser.dto.SysGroup;
import com.piedpiper.platform.api.sysuser.dto.SysGroupTl;
import com.piedpiper.platform.api.sysuser.dto.SysGroupVo;
import com.piedpiper.platform.core.dao.Paging;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheManager;
import com.piedpiper.platform.core.rest.client.RestClient;
import com.piedpiper.platform.core.rest.client.RestClientConfig;
import com.piedpiper.platform.core.rest.msg.Muti1Bean;
import com.piedpiper.platform.core.rest.msg.Muti2Bean;
import com.piedpiper.platform.core.rest.msg.Muti3Bean;
import com.piedpiper.platform.core.rest.msg.Muti4Bean;
import com.piedpiper.platform.core.rest.msg.ResponseMsg;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.GenericType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

public class SysGroupAPImpl implements SysGroupAPI {
	private static final Logger log = LoggerFactory.getLogger(SysGroupAPImpl.class);

	@Autowired
	private BaseCacheManager baseCacheManager;

	public List<SysGroup> getAllSysGroupList() {
		this.baseCacheManager.getAllFromCache("PLATFORM6_GROUP", new TypeReference() {
		});
	}

	public List<SysGroup> getAllSysGroupListByAppId(String appId) {
		this.baseCacheManager.getAllFromCache("PLATFORM6_GROUP", new TypeReference() {
		}, appId);
	}

	public SysGroupTl getSysGroupTl(String sysGroupId, String sysLanguageCode)
   {
     (SysGroupTl)this.baseCacheManager.getObjectFromCache("PLATFORM6_GROUPTL", sysGroupId + "_" + sysLanguageCode, new TypeReference() {});
   }

	public SysGroup getSysGroupBySysGroupId(String sysGroupId)
   {
     (SysGroup)this.baseCacheManager.getObjectFromCache("PLATFORM6_GROUP", sysGroupId, new TypeReference() {});
   }

	public String getSysGroupNameBySysGroupId(String sysGroupId, String languageCode) {
		if (StringUtils.isEmpty(sysGroupId)) {
			return "";
		}
		SysGroupTl groupTl = getSysGroupTl(sysGroupId, languageCode);
		if (groupTl == null) {
			log.warn("Id为" + sysGroupId + "的群组，没有语言信息！");
			return "群组无效或者不存在";
		}
		return groupTl.getGroupName();
	}

	public String getSysGroupNamesBySysGroupIds(String sysGroupIds, String regex, String languageCode) {
		StringBuffer string = new StringBuffer();
		String[] sysGroupIdArr = sysGroupIds.split(regex);
		for (String sysGroupId : sysGroupIdArr) {
			String sysGroupName = getSysGroupNameBySysGroupId(sysGroupId, languageCode);
			string.append(sysGroupName).append(regex);
		}
		if (string.length() > 0) {
			return string.substring(0, string.length() - regex.length());
		}
		return string.toString();
	}

	public List<SysGroup> getSubSysGroupListBySysOrgId(String sysOrgId) {
		this.baseCacheManager.getAllFromCache("PLATFORM6_ORG_GROUP_" + sysOrgId, new TypeReference() {
		});
	}

	public List<SysGroup> getSubSysGroupListBySysGroupId(String sysGroupId) {
		this.baseCacheManager.getAllFromCache("PLATFORM6_GROUP_GROUP_" + sysGroupId, new TypeReference() {
		});
	}

	public List<SysGroup> getUserGroupListByUserId(String userId) {
		this.baseCacheManager.getAllFromCache("PLATFORM6_CREATEUSER_GROUP_" + userId, new TypeReference() {
		});
	}

	public SysGroup getParentSysGroupBySysGroupId(String sysGroupId) {
		SysGroup sysGroup = getSysGroupBySysGroupId(sysGroupId);
		return getSysGroupBySysGroupId(sysGroup.getGroupParentId());
	}

	public String getParentSysGroupIdBySysGroupId(String sysGroupId) {
		return getParentSysGroupBySysGroupId(sysGroupId).getId();
	}

	public List<SysGroup> getAllSubSysGroupListBySysGroupId(String sysGroupId) {
		List<SysGroup> list = new ArrayList();
		List<SysGroup> subList = getSubSysGroupListBySysGroupId(sysGroupId);
		if (subList != null) {
			list.addAll(subList);
			for (SysGroup sysGroup : subList) {
				list.addAll(getAllSubSysGroupListBySysGroupId(sysGroup.getId()));
			}
		}
		return list;
	}

	public String getAllSubSysGroupIdBySysGroupId(String sysGroupId, String regex) {
		StringBuffer string = new StringBuffer();
		List<SysGroup> list = getAllSubSysGroupListBySysGroupId(sysGroupId);
		for (SysGroup sysGroup : list) {
			string.append(sysGroup.getId()).append(regex);
		}
		if (string.length() > 0) {
			return string.substring(0, string.length() - regex.length());
		}
		return string.toString();
	}

	public List<SysGroup> getAllParentSysGroupListBySysGroupId(String sysGroupId) {
		List<SysGroup> list = new ArrayList();
		SysGroup parentSysGroup = getParentSysGroupBySysGroupId(sysGroupId);
		if (parentSysGroup != null) {
			list.add(parentSysGroup);
			list.addAll(getAllParentSysGroupListBySysGroupId(parentSysGroup.getId()));
		}
		return list;
	}

	public String getAllParentSysGroupIdBySysGroupId(String sysGroupId, String regex) {
		StringBuffer string = new StringBuffer();
		List<SysGroup> list = getAllParentSysGroupListBySysGroupId(sysGroupId);
		for (SysGroup sysGroup : list) {
			string.append(sysGroup.getId()).append(regex);
		}
		if (string.length() > 0) {
			return string.substring(0, string.length() - regex.length());
		}
		return string.toString();
	}

	public SysGroupTl getSysGroupTlWithoutLanguageCode(String sysGroupId) {
		return getSysGroupTl(sysGroupId, "zh_CN");
	}

	public void insertUserGroupByIDs(String sysUserId, String sysGroupId) {
		Muti2Bean<String, String> bean = new Muti2Bean(sysUserId, sysGroupId);
		String url = RestClientConfig.getRestHost("sysuser")
				+ "/api/platform6/sysorguser/SysGroup/insertUserGroupByIDs/v1";
		ResponseMsg<Void> responseMsg = RestClient.doPost(url, bean, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {
			log.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
			throw new RuntimeException(responseMsg.getErrorDesc());
		}
	}

	public void insertUserGroupByIDs(List<String> sysUserId, String sysGroupId) {
		Muti2Bean<List<String>, String> bean = new Muti2Bean(sysUserId, sysGroupId);
		String url = RestClientConfig.getRestHost("sysuser")
				+ "/api/platform6/sysorguser/SysGroup/insertUserGroupByIDs/batch/v1";
		ResponseMsg<Void> responseMsg = RestClient.doPost(url, bean, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {
			log.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
			throw new RuntimeException(responseMsg.getErrorDesc());
		}
	}

	public void insertSysGroup(SysGroup sysGroup) {
		Muti1Bean<SysGroup> bean = new Muti1Bean(sysGroup);
		String url = RestClientConfig.getRestHost("sysuser") + "/api/platform6/sysorguser/SysGroup/insertSysGroup/v1";
		ResponseMsg<Void> responseMsg = RestClient.doPost(url, bean, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {
			log.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
			throw new RuntimeException(responseMsg.getErrorDesc());
		}
	}

	public void insertSysGroupTl(SysGroupTl sysGroupTl) {
		Muti1Bean<SysGroupTl> bean = new Muti1Bean(sysGroupTl);
		String url = RestClientConfig.getRestHost("sysuser") + "/api/platform6/sysorguser/SysGroup/insertSysGroupTl/v1";
		ResponseMsg<String> responseMsg = RestClient.doPost(url, bean, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {
			log.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
			throw new RuntimeException(responseMsg.getErrorDesc());
		}
	}

	public void updateSysGroupTl(SysGroupTl sysGroupTl) {
		String url = RestClientConfig.getRestHost("sysuser") + "/api/platform6/sysorguser/SysGroup/updateSysGroupTl/v1";
		ResponseMsg<String> responseMsg = RestClient.doPost(url, sysGroupTl, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {
			log.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
			throw new RuntimeException(responseMsg.getErrorDesc());
		}
	}

	public void deleteUserGroupByPersonalGroupId(String sysGroupId, String sysUserId) {
		Muti2Bean<String, String> bean = new Muti2Bean(sysGroupId, sysUserId);
		String url = RestClientConfig.getRestHost("sysuser")
				+ "/api/platform6/sysorguser/SysGroup/deleteUserGroupByPersonalGroupId/v1";
		ResponseMsg<Void> responseMsg = RestClient.doPost(url, bean, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {
			log.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
			throw new RuntimeException(responseMsg.getErrorDesc());
		}
	}

	public void deleteById(String id) {
		Muti1Bean<String> bean = new Muti1Bean(id);
		String url = RestClientConfig.getRestHost("sysuser") + "/api/platform6/sysorguser/SysGroup/deleteById/v1";
		ResponseMsg<Void> responseMsg = RestClient.doPost(url, bean, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {
			log.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
			throw new RuntimeException(responseMsg.getErrorDesc());
		}
	}

	public void deleteTlById(String id) {
		String url = RestClientConfig.getRestHost("sysuser") + "/api/platform6/sysorguser/SysGroup/deleteTlById/v1";
		ResponseMsg<Void> responseMsg = RestClient.doPost(url, id, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {
			log.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
			throw new RuntimeException(responseMsg.getErrorDesc());
		}
	}

	public Long getTotalCountRecord(String countSql, HashMap<String, Object> propertyValueMap,
			HashMap<String, String> aliaClassMap) {
		String url = RestClientConfig.getRestHost("sysuser")
				+ "/api/platform6/sysorguser/SysGroup/getTotalCountRecord/v1";
		Muti3Bean<String, HashMap<String, Object>, HashMap<String, String>> bean = new Muti3Bean(countSql,
				propertyValueMap, aliaClassMap);
		ResponseMsg<Long> responseMsg = RestClient.doPost(url, bean, new GenericType() {
		});
		if (responseMsg.getRetCode().equals("200")) {
			return (Long) responseMsg.getResponseBody();
		}
		log.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
		throw new RuntimeException(responseMsg.getErrorDesc());
	}

	public String buildSqlByCondition(String sql, Map<String, Object> parameter,
			HashMap<String, Object> propertyValueMap) {
		String url = RestClientConfig.getRestHost("sysuser")
				+ "/api/platform6/sysorguser/SysGroup/buildSqlByCondition/v1";
		Muti3Bean<String, Map<String, Object>, HashMap<String, Object>> bean = new Muti3Bean(sql, parameter,
				propertyValueMap);
		ResponseMsg<String> responseMsg = RestClient.doPost(url, bean, new GenericType() {
		});
		if (responseMsg.getRetCode().equals("200")) {
			return (String) responseMsg.getResponseBody();
		}
		log.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
		throw new RuntimeException(responseMsg.getErrorDesc());
	}

	public List<Object[]> getGroupInfoBySQL(Paging<SysGroupVo> pageGroup, String sql,
			HashMap<String, Object> propertyValueMap, HashMap<String, Class> aliaClassMap) {
		String url = RestClientConfig.getRestHost("sysuser")
				+ "/api/platform6/sysorguser/SysGroup/getGroupInfoBySQL/v1";
		Muti4Bean<Paging<SysGroupVo>, String, HashMap<String, Object>, HashMap<String, Class>> bean = new Muti4Bean(
				pageGroup, sql, propertyValueMap, aliaClassMap);
		ResponseMsg<List<Object[]>> responseMsg = RestClient.doPost(url, bean, new GenericType() {
		});
		if (responseMsg.getRetCode().equals("200")) {
			return (List) responseMsg.getResponseBody();
		}
		log.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
		throw new RuntimeException(responseMsg.getErrorDesc());
	}
}
