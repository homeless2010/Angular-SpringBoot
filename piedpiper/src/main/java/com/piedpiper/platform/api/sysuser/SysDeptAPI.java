package com.piedpiper.platform.api.sysuser;

import com.piedpiper.platform.api.sysuser.dto.SysDept;
import com.piedpiper.platform.api.sysuser.dto.SysDeptTl;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import java.util.Map;

public abstract interface SysDeptAPI {
	public abstract List<SysDept> getAllSysDeptList();

	public abstract SysDept getSysDeptBySysDeptId(String paramString);

	public abstract SysDeptTl getSysDeptTl(String paramString1, String paramString2);

	public abstract List<SysDept> getSubSysDeptsByOrgId(String paramString);

	public abstract List<SysDept> getSysDeptsByOrgId(String paramString);

	public abstract <T> List<T> getSysDeptsByOrgId(String paramString, TypeReference<T> paramTypeReference);

	public abstract String getSysDeptNameBySysDeptId(String paramString1, String paramString2);

	public abstract String getSysDeptNamesBySysDeptIds(String paramString1, String paramString2, String paramString3);

	public abstract List<SysDept> getSubSysDeptListBySysDeptId(String paramString);

	public abstract <T> List<T> getSubSysDeptListBySysDeptId(String paramString, TypeReference<T> paramTypeReference);

	public abstract SysDept getParentSysDeptBySysDeptId(String paramString);

	public abstract String getParentSysDeptIdBySysDeptId(String paramString);

	public abstract List<SysDept> getAllSubSysDeptListBySysDeptId(String paramString);

	public abstract String getAllSubSysDeptIdBySysDeptId(String paramString1, String paramString2);

	public abstract List<String> getAllSubSysDeptIdListBySysDeptId(String paramString);

	public abstract List<SysDept> getAllParentSysDeptListBySysDeptId(String paramString, List<SysDept> paramList);

	public abstract String getAllParentSysDeptIdBySysDeptId(String paramString1, String paramString2);

	public abstract SysDept getParentSysDeptBySysDeptIdAndSysDeptLevel(String paramString, long paramLong);

	public abstract SysDept getTopSysDeptBySysDeptId(String paramString);

	public abstract String getTopSysDeptIdBySysDeptId(String paramString);

	public abstract String getTopSysDeptNameBySysDeptId(String paramString1, String paramString2);

	public abstract String getSysDeptURLNameBySysDeptIDAndParentSysDeptLevel(String paramString1, long paramLong,
			String paramString2);

	public abstract String getSysDeptURLNameToTopSysDeptBySysDeptId(String paramString1, String paramString2);

	public abstract SysDeptTl getSysDeptTlWithoutLanguageCode(String paramString);

	public abstract String getSysDeptIdByDeptCode(String paramString);

	public abstract Map<String, SysDeptTl> getSysDeptTlByDeptList(List<SysDept> paramList);

	public abstract boolean existsChilds(String paramString);

	public abstract boolean existsDeptChilds(String paramString);

	public abstract boolean checkDeptCode(String paramString);

	public abstract long countDeptLevel(String paramString);

	public abstract SysDept getSysDeptBySysDeptName(String paramString1, String paramString2);

	public abstract void insertSysDept(SysDept paramSysDept);

	public abstract void updateSysDept(SysDept paramSysDept);

	public abstract void deleteSysDept(SysDept paramSysDept);

	public abstract void insertSysDeptTl(SysDeptTl paramSysDeptTl);

	public abstract void updateSysDeptTl(SysDeptTl paramSysDeptTl);

	public abstract void deleteSysDeptTl(SysDeptTl paramSysDeptTl);

	public abstract List<SysDept> find(String paramString, Object[] paramArrayOfObject);

	public abstract void setValidFlag(Map<String, String> paramMap);
}
