package com.piedpiper.platform.api.sysuser;

import com.piedpiper.platform.api.sysuser.dto.SysRole;
import com.piedpiper.platform.core.dao.Paging;
import com.piedpiper.platform.core.dao.PropertyFilter;
import java.util.List;

public abstract interface SysRoleAPI {
	public abstract List<SysRole> getAllSysRoles();

	public abstract SysRole getSysRoleById(String paramString);

	public abstract String getSysRoleNameById(String paramString);

	@Deprecated
	public abstract SysRole getSysRoleByRoleCode(String paramString);

	public abstract SysRole getSysRoleByRoleCodeAndAppId(String paramString1, String paramString2);

	public abstract List<SysRole> getAllSysRolesByAppId(String paramString);

	public abstract boolean checkRoleCode(String paramString);

	public abstract boolean isAdministrator(String paramString);

	public abstract boolean isAdministratorByUserId(String paramString);

	public abstract List<SysRole> getRolesByUserId(String paramString1, String paramString2);

	public abstract void insertSysRole(SysRole paramSysRole);

	public abstract void updateSysRole(SysRole paramSysRole);

	public abstract void deleteSysRole(SysRole paramSysRole);

	public abstract List<SysRole> find(List<PropertyFilter> paramList);

	public abstract Paging<SysRole> findPage(Paging<SysRole> paramPaging, String paramString1, String paramString2);
}
