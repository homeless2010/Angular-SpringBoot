package com.piedpiper.platform.api.sysuser.impl;

import com.piedpiper.platform.api.sysuser.SysDeptAPI;
import com.piedpiper.platform.api.sysuser.dto.SysDept;
import com.piedpiper.platform.api.sysuser.dto.SysDeptTl;
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
import org.springframework.util.StringUtils;

public class SysDeptAPImpl implements SysDeptAPI {
	@Autowired
	private BaseCacheManager baseCacheManager;

	public List<SysDept> getAllSysDeptList() {
		return this.baseCacheManager.getAllFromCache("PLATFORM6_DEPT", new TypeReference() {
		});
	}

	public SysDept getSysDeptBySysDeptId(String sysDeptId) {
		return (SysDept) this.baseCacheManager.getObjectFromCache("PLATFORM6_DEPT", sysDeptId, new TypeReference() {
		});
	}

	public SysDeptTl getSysDeptTl(String sysDeptId, String sysLanguageCode) {
		return (SysDeptTl) this.baseCacheManager.getObjectFromCache("PLATFORM6_DEPTTL",
				sysDeptId + "_" + sysLanguageCode, new TypeReference() {
				});
	}

	public List<SysDept> getSubSysDeptsByOrgId(String orgId) {
		List<SysDept> resultList = new ArrayList();
		List<SysDept> list = this.baseCacheManager.getAllFromCache("PLATFORM6_ORG_DEPT_" + orgId, new TypeReference() {
		});
		for (SysDept sysDept : list) {
			if (sysDept.getParentDeptId().equals("-1")) {
				resultList.add(sysDept);
			}
		}
		return resultList;
	}

	public List<SysDept> getSysDeptsByOrgId(String orgId) {
		return this.baseCacheManager.getAllFromCache("PLATFORM6_ORG_DEPT_" + orgId, new TypeReference() {
		});
	}

	public <T> List<T> getSysDeptsByOrgId(String orgId, TypeReference<T> typeReference) {
		return this.baseCacheManager.getAllFromCache("PLATFORM6_ORG_DEPT_" + orgId, typeReference);
	}

	public String getSysDeptNameBySysDeptId(String sysDeptId, String languageCode) {
		if (StringUtils.isEmpty(sysDeptId)) {
			return "";
		}
		SysDeptTl tl = getSysDeptTl(sysDeptId, languageCode);
		if (tl != null) {
			return tl.getDeptName();
		}
		return "部门无效或者不存在";
	}

	public String getSysDeptNamesBySysDeptIds(String sysDeptIds, String regex, String sysLanguageCode) {
		StringBuffer string = new StringBuffer();
		String[] sysDeptIdArr = sysDeptIds.split(regex);
		for (String sysDeptId : sysDeptIdArr) {
			String sysDeptName = getSysDeptNameBySysDeptId(sysDeptId, sysLanguageCode);
			string.append(sysDeptName).append(regex);
		}
		if (string.length() > 0) {
			return string.substring(0, string.length() - regex.length());
		}
		return string.toString();
	}

	public List<SysDept> getSubSysDeptListBySysDeptId(String sysDeptId) {
		return this.baseCacheManager.getAllFromCache("PLATFORM6_DEPT_DEPT_" + sysDeptId, new TypeReference() {
		});
	}

	public <T> List<T> getSubSysDeptListBySysDeptId(String sysDeptId, TypeReference<T> typeReference) {
		return this.baseCacheManager.getAllFromCache("PLATFORM6_DEPT_DEPT_" + sysDeptId, typeReference);
	}

	public SysDept getParentSysDeptBySysDeptId(String sysDeptId) {
		SysDept sysDept = getSysDeptBySysDeptId(sysDeptId);
		return getSysDeptBySysDeptId(sysDept.getParentDeptId());
	}

	public String getParentSysDeptIdBySysDeptId(String sysDeptId) {
		return getSysDeptBySysDeptId(sysDeptId).getParentDeptId();
	}

	public List<SysDept> getAllSubSysDeptListBySysDeptId(String sysDeptId) {
		List<SysDept> list = new ArrayList();
		List<SysDept> subList = getSubSysDeptListBySysDeptId(sysDeptId);
		list.addAll(subList);
		for (SysDept sysDept : subList) {
			list.addAll(getAllSubSysDeptListBySysDeptId(sysDept.getId()));
		}
		return list;
	}

	public String getAllSubSysDeptIdBySysDeptId(String sysDeptId, String regex) {
		StringBuffer string = new StringBuffer();
		List<SysDept> list = getAllSubSysDeptListBySysDeptId(sysDeptId);
		for (SysDept sysDept : list) {
			string.append(sysDept.getId()).append(regex);
		}
		if (string.length() > 0) {
			return string.substring(0, string.length() - regex.length());
		}
		return string.toString();
	}

	public List<String> getAllSubSysDeptIdListBySysDeptId(String sysDeptId) {
		List<String> result = new ArrayList();
		List<SysDept> list = getAllSubSysDeptListBySysDeptId(sysDeptId);
		for (SysDept sysDept : list) {
			result.add(sysDept.getId());
		}
		return result;
	}

	public List<SysDept> getAllParentSysDeptListBySysDeptId(String sysDeptId, List<SysDept> list) {
		SysDept parentSysDept = getParentSysDeptBySysDeptId(sysDeptId);
		if (parentSysDept != null) {
			list.add(parentSysDept);
			getAllParentSysDeptListBySysDeptId(parentSysDept.getId(), list);
		}
		return list;
	}

	public String getAllParentSysDeptIdBySysDeptId(String sysDeptId, String regex) {
		StringBuffer string = new StringBuffer();
		List<SysDept> list = new ArrayList();
		getAllParentSysDeptListBySysDeptId(sysDeptId, list);
		for (SysDept sysDept : list) {
			string.append(sysDept.getId()).append(regex);
		}
		if (string.length() > 0) {
			return string.substring(0, string.length() - regex.length());
		}
		return string.toString();
	}

	public SysDept getParentSysDeptBySysDeptIdAndSysDeptLevel(String sysDeptId, long parentSysDeptLevel) {
		List<SysDept> list = new ArrayList();
		getAllParentSysDeptListBySysDeptId(sysDeptId, list);
		for (SysDept sysDept : list) {
			if (sysDept.getDeptLevel().longValue() == parentSysDeptLevel) {
				return sysDept;
			}
		}
		return null;
	}

	public SysDept getTopSysDeptBySysDeptId(String sysDeptId) {
		return getParentSysDeptBySysDeptIdAndSysDeptLevel(sysDeptId, 1L);
	}

	public String getTopSysDeptIdBySysDeptId(String sysDeptId) {
		return getTopSysDeptBySysDeptId(sysDeptId).getId();
	}

	public String getTopSysDeptNameBySysDeptId(String sysDeptId, String languageCode) {
		String topDeptId = getTopSysDeptBySysDeptId(sysDeptId).getId();
		return getSysDeptNameBySysDeptId(topDeptId, languageCode);
	}

	public String getSysDeptURLNameBySysDeptIDAndParentSysDeptLevel(String sysDeptId, long parentSysDeptLevel,
			String languageCode) {
		List<SysDept> list = new ArrayList();
		getAllParentSysDeptListBySysDeptId(sysDeptId, list);
		String sysDeptURLName = "";
		for (SysDept sysDept : list) {
			if (sysDept.getDeptLevel().longValue() < parentSysDeptLevel) {
				break;
			}
			String sysDeptName = getSysDeptNameBySysDeptId(sysDept.getId(), languageCode);
			sysDeptURLName = sysDeptName + "/" + sysDeptURLName;
		}
		return sysDeptURLName;
	}

	public String getSysDeptURLNameToTopSysDeptBySysDeptId(String sysDeptId, String languageCode) {
		return getSysDeptURLNameBySysDeptIDAndParentSysDeptLevel(sysDeptId, 1L, languageCode);
	}

	public SysDeptTl getSysDeptTlWithoutLanguageCode(String sysDeptId) {
		return getSysDeptTl(sysDeptId, "zh_CN");
	}

	public String getSysDeptIdByDeptCode(String code) {
		SysDept sysDept = (SysDept) this.baseCacheManager.getObjectFromCache("PLATFORM6_DEPTCODE", code,
				new TypeReference() {
				});
		if (sysDept != null) {
			return sysDept.getId();
		}
		return null;
	}

	public Map<String, SysDeptTl> getSysDeptTlByDeptList(List<SysDept> deptList) {
		Map<String, SysDeptTl> resultMap = new HashMap();
		for (SysDept dept : deptList) {
			SysDeptTl tl = getSysDeptTl(dept.getId(), "zh_CN");
			resultMap.put(dept.getId(), tl);
		}
		return resultMap;
	}

	public boolean existsChilds(String orgId) {
		return this.baseCacheManager.exists("PLATFORM6_ORG_DEPT_" + orgId);
	}

	public boolean existsDeptChilds(String deptId) {
		return this.baseCacheManager.exists("PLATFORM6_DEPT_DEPT_" + deptId);
	}

	public boolean checkDeptCode(String code) {
		return this.baseCacheManager.containsKey("PLATFORM6_DEPTCODE", code);
	}

	public long countDeptLevel(String parentId) {
		return countDeptLevel(parentId, 1L);
	}

	private long countDeptLevel(String parentId, long level) {
		if ((parentId == null) || (parentId.equals("")) || (parentId.equals("-1"))) {
			return level;
		}
		SysDept sysDept = getSysDeptBySysDeptId(parentId);
		level += 1L;
		return countDeptLevel(sysDept.getParentDeptId(), level);
	}

	public SysDept getSysDeptBySysDeptName(String deptName, String language) {
		SysDeptTl tl = (SysDeptTl) this.baseCacheManager.getObjectFromCache("PLATFORM6_DEPT_DEPTNAME",
				deptName + "_" + language, new TypeReference() {
				});
		if (tl != null) {
			return (SysDept) this.baseCacheManager.getObjectFromCache("PLATFORM6_DEPT", tl.getSysDeptId(),
					new TypeReference() {
					});
		}
		return null;
	}

	public void insertSysDept(SysDept sysDept) {
		if ((sysDept.getOrgId() == null) || ("".equals(sysDept.getOrgId()))) {
			throw new RuntimeException("orgId不能为空！");
		}

		String url = RestClientConfig.getRestHost("sysuser") + "/api/platform6/sysorguser/SysDept/insertDeptInfo/v1";
		ResponseMsg<SysDept> responseMsg = RestClient.doPost(url, sysDept, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {

			throw new RuntimeException(responseMsg.getErrorDesc());
		}
	}

	public void updateSysDept(SysDept sysDept) {
		String url = RestClientConfig.getRestHost("sysuser") + "/api/platform6/sysorguser/SysDept/updateDeptInfo/v1";
		ResponseMsg<SysDept> responseMsg = RestClient.doPost(url, sysDept, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {

			throw new RuntimeException(responseMsg.getErrorDesc());
		}
	}

	public void deleteSysDept(SysDept sysDept) {
		String url = RestClientConfig.getRestHost("sysuser") + "/api/platform6/sysorguser/SysDept/deleteDept/v1";
		ResponseMsg<SysDept> responseMsg = RestClient.doPost(url, sysDept, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {

			throw new RuntimeException(responseMsg.getErrorDesc());
		}
	}

	public void insertSysDeptTl(SysDeptTl sysDeptTl) {
		String url = RestClientConfig.getRestHost("sysuser") + "/api/platform6/sysorguser/SysDept/insertDeptTl/v1";
		ResponseMsg<SysDeptTl> responseMsg = RestClient.doPost(url, sysDeptTl, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {

			throw new RuntimeException(responseMsg.getErrorDesc());
		}
	}

	public void updateSysDeptTl(SysDeptTl sysDeptTl) {
		String url = RestClientConfig.getRestHost("sysuser") + "/api/platform6/sysorguser/SysDept/updateDeptTl/v1";
		ResponseMsg<SysDeptTl> responseMsg = RestClient.doPost(url, sysDeptTl, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {

			throw new RuntimeException(responseMsg.getErrorDesc());
		}
	}

	public void deleteSysDeptTl(SysDeptTl sysDeptTl) {
		String url = RestClientConfig.getRestHost("sysuser") + "/api/platform6/sysorguser/SysDept/deleteDeptTl/v1";
		ResponseMsg<SysDeptTl> responseMsg = RestClient.doPost(url, sysDeptTl, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {

			throw new RuntimeException(responseMsg.getErrorDesc());
		}
	}

	public List<SysDept> find(String hql, Object[] object) {
		Muti2Bean<String, Object[]> args = new Muti2Bean(hql, object);
		String url = RestClientConfig.getRestHost("sysuser") + "/api/platform6/sysorguser/SysDept/findByObject/v1";
		ResponseMsg<List<SysDept>> responseMsg = RestClient.doPost(url, args, new GenericType() {
		});
		if (responseMsg.getRetCode().equals("200")) {
			return (List) responseMsg.getResponseBody();
		}
		throw new RuntimeException(responseMsg.getErrorDesc());
	}

	public void setValidFlag(Map<String, String> parameter) {
		String url = RestClientConfig.getRestHost("sysuser") + "/api/platform6/sysorguser/SysDept/setValidFlag/v1";
		ResponseMsg<String> responseMsg = RestClient.doPost(url, parameter, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {

			throw new RuntimeException(responseMsg.getErrorDesc());
		}
	}
}
