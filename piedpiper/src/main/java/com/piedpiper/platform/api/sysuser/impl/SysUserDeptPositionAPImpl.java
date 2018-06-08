package com.piedpiper.platform.api.sysuser.impl;

import com.piedpiper.platform.api.sysuser.SysUserDeptPositionAPI;
import com.piedpiper.platform.api.sysuser.dto.SysDept;
import com.piedpiper.platform.api.sysuser.dto.SysPosition;
import com.piedpiper.platform.api.sysuser.dto.SysUser;
import com.piedpiper.platform.api.sysuser.dto.SysUserDeptPosition;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheManager;
import com.piedpiper.platform.core.rest.client.RestClient;
import com.piedpiper.platform.core.rest.client.RestClientConfig;
import com.piedpiper.platform.core.rest.msg.ResponseMsg;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.GenericType;
import org.springframework.beans.factory.annotation.Autowired;

public class SysUserDeptPositionAPImpl implements SysUserDeptPositionAPI {
	@Autowired
	private BaseCacheManager baseCacheManager;

	public List<List<Object>> getDeptAndPositionListBySysUserId(String sysUserId) {
		List<List<Object>> result = new ArrayList();
		List<SysUserDeptPosition> list = this.baseCacheManager
				.getAllFromCache("PLATFORM6_USER_USERDEPTPOSITION_" + sysUserId, new TypeReference() {
				});
		for (SysUserDeptPosition sysUserDeptPosition : list) {
			List<Object> childList = new ArrayList();
			SysDept dept = (SysDept) this.baseCacheManager.getObjectFromCache("PLATFORM6_DEPT",
					sysUserDeptPosition.getDeptId(), SysDept.class);
			SysPosition position = (SysPosition) this.baseCacheManager.getObjectFromCache("PLATFORM6_POSITION",
					sysUserDeptPosition.getPositionId(), SysPosition.class);
			childList.add(dept);
			childList.add(position);
			childList.add(sysUserDeptPosition);
			result.add(childList);
		}
		return result;
	}

	public SysUserDeptPosition getChiefUDPRelationBySysUserId(String sysUserId) {
		List<SysUserDeptPosition> list = this.baseCacheManager
				.getAllFromCache("PLATFORM6_USER_USERDEPTPOSITION_" + sysUserId, new TypeReference() {
				});
		for (SysUserDeptPosition sysUserDeptPosition : list) {
			if (sysUserDeptPosition.getIsChiefDept().equals("1")) {
				return sysUserDeptPosition;
			}
		}
		return null;
	}

	public List<Object> getChiefDeptAndChiefPositionBySysUserId(String sysUserId) {
		SysUserDeptPosition sysUserDeptPosition = getChiefUDPRelationBySysUserId(sysUserId);
		List<Object> result = new ArrayList();
		result.add((SysDept) this.baseCacheManager.getObjectFromCache("PLATFORM6_DEPT", sysUserDeptPosition.getDeptId(),
				SysDept.class));
		result.add((SysPosition) this.baseCacheManager.getObjectFromCache("PLATFORM6_POSITION",
				sysUserDeptPosition.getPositionId(), SysPosition.class));
		return result;
	}

	public SysPosition getChiefPositionBySysUserId(String sysUserId) {
		SysUserDeptPosition sysUserDeptPosition = getChiefUDPRelationBySysUserId(sysUserId);
		return (SysPosition) this.baseCacheManager.getObjectFromCache("PLATFORM6_POSITION",
				sysUserDeptPosition.getPositionId(), SysPosition.class);
	}

	public String getChiefPositionCodeBySysUserId(String sysUserId) {
		SysPosition sysPosition = getChiefPositionBySysUserId(sysUserId);
		return sysPosition.getPositionCode();
	}

	public SysDept getChiefDeptBySysUserId(String sysUserId) {
		SysUserDeptPosition sysUserDeptPosition = getChiefUDPRelationBySysUserId(sysUserId);
		if (sysUserDeptPosition != null) {
			return (SysDept) this.baseCacheManager.getObjectFromCache("PLATFORM6_DEPT", sysUserDeptPosition.getDeptId(),
					SysDept.class);
		}
		return null;
	}

	public String getChiefDeptIdBySysUserId(String sysUserId) {
		SysDept sysDept = getChiefDeptBySysUserId(sysUserId);
		return sysDept.getId();
	}

	public String getCurrentDeptManagerStr(String sysDeptId, String regex) {
		StringBuffer string = new StringBuffer();
		List<SysUserDeptPosition> list = this.baseCacheManager
				.getAllFromCache("PLATFORM6_DEPT_USERDEPTPOSITION_" + sysDeptId, new TypeReference() {
				});
		for (SysUserDeptPosition sysUserDeptPosition : list) {
			if (sysUserDeptPosition.getIsManager().equals("1")) {
				string.append(sysUserDeptPosition.getUserId()).append(regex);
			}
		}
		if (string.length() > 0) {
			return string.substring(0, string.length() - regex.length());
		}
		return string.toString();
	}

	public boolean isCurrentDeptManager(String sysDeptId, String sysUserId) {
		List<SysUserDeptPosition> list = this.baseCacheManager
				.getAllFromCache("PLATFORM6_USER_USERDEPTPOSITION_" + sysUserId, new TypeReference() {
				});
		for (SysUserDeptPosition sysUserDeptPosition : list) {
			if ((sysUserDeptPosition.getDeptId().equals(sysDeptId))
					&& (sysUserDeptPosition.getIsManager().equals("1"))) {
				return true;
			}
		}
		return false;
	}

	public List<SysUser> getSysUserListBySysDeptId(String sysDeptId, boolean isSubDept) {
		List<SysUser> list = new ArrayList();
		List<SysUser> subList = this.baseCacheManager.getAllFromCache("PLATFORM6_DEPT_USER_" + sysDeptId,
				new TypeReference() {
				});
		list.addAll(subList);
		if (isSubDept) {
			List<SysDept> childDeptList = this.baseCacheManager.getAllFromCache("PLATFORM6_DEPT_DEPT_" + sysDeptId,
					new TypeReference() {
					});
			for (SysDept sysDept : childDeptList) {
				list.addAll(getSysUserListBySysDeptId(sysDept.getId(), isSubDept));
			}
		}
		return list;
	}

	public List<SysUser> getSysUserListBySysDeptId(String sysDeptId, boolean isSubDept, int secretLevel) {
		List<SysUser> resultList = new ArrayList();
		List<SysUser> list = getSysUserListBySysDeptId(sysDeptId, isSubDept);
		for (SysUser sysUser : list) {
			if (Integer.parseInt(sysUser.getSecretLevel()) >= secretLevel) {
				resultList.add(sysUser);
			}
		}
		return resultList;
	}

	public List<SysUser> getSysUserListBySysDeptIdAndSysPositionCode(String sysDeptId, String positionCode) {
		SysPosition sysPosition = (SysPosition) this.baseCacheManager.getObjectFromCache("PLATFORM6_POSITIONCODE",
				positionCode, SysPosition.class);
		return getSysUserListBySysDeptIdAndSysPositionId(sysDeptId, sysPosition.getId());
	}

	public List<SysUser> getSysUserListBySysDeptIdAndSysPositionCode(String sysDeptId, String positionCode,
			int secretLevel) {
		List<SysUser> resultList = new ArrayList();
		List<SysUser> list = getSysUserListBySysDeptIdAndSysPositionCode(sysDeptId, positionCode);
		for (SysUser sysUser : list) {
			if (Integer.parseInt(sysUser.getSecretLevel()) >= secretLevel) {
				resultList.add(sysUser);
			}
		}
		return resultList;
	}

	public List<SysUser> getSysUserListBySysDeptIdAndSysPositionId(String sysDeptId, String positionId) {
		return this.baseCacheManager.getAllFromCache("PLATFORM6_DEPT_POSITION_" + sysDeptId + "_" + positionId,
				new TypeReference() {
				});
	}

	public List<SysUser> getSysUserListBySysDeptIdAndSysPositionId(String sysDeptId, String positionId,
			int secretLevel) {
		List<SysUser> resultList = new ArrayList();
		List<SysUser> list = getSysUserListBySysDeptIdAndSysPositionId(sysDeptId, positionId);
		for (SysUser sysUser : list) {
			if (Integer.parseInt(sysUser.getSecretLevel()) >= secretLevel) {
				resultList.add(sysUser);
			}
		}
		return resultList;
	}

	public List<SysPosition> getSysPositionListBySysUserId(String sysUserId) {
		return this.baseCacheManager.getAllFromCache("PLATFORM6_USER_POSITION_" + sysUserId, new TypeReference() {
		});
	}

	public List<SysDept> getSysDeptListBySysUserId(String sysUserId) {
		return this.baseCacheManager.getAllFromCache("PLATFORM6_USER_DEPT_" + sysUserId, new TypeReference() {
		});
	}

	public boolean isHaveThisPosition(String sysUserId, String sysPositionCode) {
		List<SysPosition> list = getSysPositionListBySysUserId(sysUserId);
		for (SysPosition sysPosition : list) {
			if (sysPosition.getPositionCode().equals(sysPositionCode)) {
				return true;
			}
		}
		return false;
	}

	public List<SysUser> getSysUserListBySysPositionCode(String sysPositionCode) {
		SysPosition sysPosition = (SysPosition) this.baseCacheManager.getObjectFromCache("PLATFORM6_POSITIONCODE",
				sysPositionCode, SysPosition.class);
		return getSysUserListBySysPositionId(sysPosition.getId());
	}

	public List<SysUser> getSysUserListBySysPositionCode(String sysPositionCode, int secretLevel) {
		List<SysUser> resultList = new ArrayList();
		List<SysUser> list = getSysUserListBySysPositionCode(sysPositionCode);
		for (SysUser sysUser : list) {
			if (Integer.parseInt(sysUser.getSecretLevel()) >= secretLevel) {
				resultList.add(sysUser);
			}
		}
		return resultList;
	}

	public List<SysUser> getSysUserListBySysPositionId(String sysPositionId) {
		return this.baseCacheManager.getAllFromCache("PLATFORM6_POSITION_USER_" + sysPositionId, new TypeReference() {
		});
	}

	public List<SysUser> getSysUserListBySysPositionId(String sysPositionId, int secretLevel) {
		List<SysUser> resultList = new ArrayList();
		List<SysUser> list = getSysUserListBySysPositionId(sysPositionId);
		for (SysUser sysUser : list) {
			if (Integer.parseInt(sysUser.getSecretLevel()) >= secretLevel) {
				resultList.add(sysUser);
			}
		}
		return resultList;
	}

	public String getSysUserStrBySysPositionCode(String sysPositionCode, String regex) {
		List<SysUser> list = getSysUserListBySysPositionCode(sysPositionCode);
		StringBuffer string = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			SysUser user = (SysUser) list.get(i);
			string.append(user.getId());
			if (i < list.size() - 1) {
				string.append(regex);
			}
		}
		return string.toString();
	}

	public SysUserDeptPosition getModelByUserIdAndDeptId(String userId, String deptId) {
		List<SysUserDeptPosition> list = this.baseCacheManager
				.getAllFromCache("PLATFORM6_USER_USERDEPTPOSITION_" + userId, new TypeReference() {
				});
		for (SysUserDeptPosition sysUserDeptPosition : list) {
			if (sysUserDeptPosition.getDeptId().equals(deptId)) {
				return sysUserDeptPosition;
			}
		}
		return null;
	}

	public SysUserDeptPosition getSysUserDeptPositionById(String id) {
		return (SysUserDeptPosition) this.baseCacheManager.getObjectFromCache("PLATFORM6_USERDEPTPOSITION", id,
				SysUserDeptPosition.class);
	}

	public SysPosition getSysPositionBySysUserIdAndSysDeptId(String sysUserId, String sysDeptId) {
		SysUserDeptPosition sysUserDeptPosition = getModelByUserIdAndDeptId(sysUserId, sysDeptId);
		return (SysPosition) this.baseCacheManager.getObjectFromCache("PLATFORM6_POSITION",
				sysUserDeptPosition.getPositionId(), SysPosition.class);
	}

	public Map<String, List<SysUser>> getAllSysUserByDeptList(List<String> deptIds) {
		Map<String, List<SysUser>> resultMap = new HashMap();
		for (String sysDeptId : deptIds) {
			List<SysUser> sysUserList = getSysUserListBySysDeptId(sysDeptId, false);
			resultMap.put(sysDeptId, sysUserList);
		}
		return resultMap;
	}

	public void insertSysUserDeptPosition(SysUserDeptPosition sysUserDeptPosition) {
		String url = RestClientConfig.getRestHost("sysuser")
				+ "/api/platform6/sysorguser/SysUserDeptPosition/insertSysUserDeptPosition/v1";
		ResponseMsg<Void> responseMsg = RestClient.doPost(url, sysUserDeptPosition, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {

			throw new RuntimeException(responseMsg.getErrorDesc());
		}
	}

	public void updateSysUserDeptPosition(SysUserDeptPosition sysUserDeptPosition) {
		String url = RestClientConfig.getRestHost("sysuser")
				+ "/api/platform6/sysorguser/SysUserDeptPosition/updateSysUserDeptPosition/v1";
		ResponseMsg<Void> responseMsg = RestClient.doPost(url, sysUserDeptPosition, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {

			throw new RuntimeException(responseMsg.getErrorDesc());
		}
	}

	public void deleteSysUserDeptPosition(SysUserDeptPosition sysUserDeptPosition) {
		String url = RestClientConfig.getRestHost("sysuser")
				+ "/api/platform6/sysorguser/SysUserDeptPosition/deleteSysUserDeptPositionByObject/v1";
		ResponseMsg<Void> responseMsg = RestClient.doPost(url, sysUserDeptPosition, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {

			throw new RuntimeException(responseMsg.getErrorDesc());
		}
	}
}
