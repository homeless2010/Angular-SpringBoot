package com.piedpiper.platform.api.sysapprole;

import com.piedpiper.platform.api.sysapprole.dto.SysAppRole;
import java.util.List;

public abstract interface SysAppRoleAPI {
	public abstract List<SysAppRole> getAllSysAppRoles();

	public abstract List<String> getAllowedRoles(String paramString);

	public abstract int deleteByAppId(String paramString);

	public abstract int deleteByRoleId(String paramString);

	public abstract int updateRoleNameById(String paramString1, String paramString2);

	public abstract boolean isExistRoleId(String paramString);
}
