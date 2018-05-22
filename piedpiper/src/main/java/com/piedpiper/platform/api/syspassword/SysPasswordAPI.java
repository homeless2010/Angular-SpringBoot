package com.piedpiper.platform.api.syspassword;

import com.piedpiper.platform.api.syspassword.dto.SysPasswordTemplet;
import com.piedpiper.platform.api.syspassword.dto.SysUserOldPassword;
import com.piedpiper.platform.api.sysuser.dto.SysUser;
import java.util.List;
import java.util.Map;

public abstract interface SysPasswordAPI {
	public abstract void reLoad() throws Exception;

	public abstract List<SysUserOldPassword> getSysUserOldPasswordListByHQL(String paramString);

	public abstract List<SysPasswordTemplet> getSysPasswordTemplet(String paramString);

	public abstract List<SysUserOldPassword> getSysUserOldPassword(String paramString);

	public abstract Map<String, String> getSysPasswordTempletByKey(String paramString, String... paramVarArgs);

	public abstract Map<String, String> changePassword(String paramString1, String paramString2, SysUser paramSysUser,
			String paramString3);
}
