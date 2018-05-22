package com.piedpiper.platform.api.sysuser;

import com.piedpiper.platform.api.sysuser.dto.SysUserLockLog;

public abstract interface SysUserLockLogAPI {
	public abstract void saveSysUserLockLog(SysUserLockLog paramSysUserLockLog);
}
