package com.piedpiper.platform.api.sysuser;

import com.piedpiper.platform.api.sysuser.dto.SysPosition;
import com.piedpiper.platform.api.sysuser.dto.SysPositionTl;
import java.util.List;

public abstract interface SysPositionAPI {
	public abstract List<SysPosition> getAllSysPositionList();

	public abstract List<SysPosition> getAllSysPositionListByOrgId(String paramString);

	public abstract SysPositionTl getSysPositionTl(String paramString1, String paramString2);

	public abstract SysPosition getSysPositionBySysPositionId(String paramString);

	public abstract String getSysPositionNameBySysPositionId(String paramString1, String paramString2);

	public abstract String getSysPositionCodeBySysPositionId(String paramString);

	public abstract String getSysPositionIdBySysPositionCode(String paramString);

	public abstract String getSysPositionNamesBySysPositionCodes(String paramString1, String paramString2,
			String paramString3);

	public abstract String getSysPositionNamesBySysPositionIds(String paramString1, String paramString2,
			String paramString3);

	public abstract SysPositionTl getSysPositionTlWithoutLanguageCode(String paramString);

	public abstract boolean checkPositionCode(String paramString);

	public abstract void insertSysPosition(SysPosition paramSysPosition, String paramString);

	public abstract void updateSysPosition(SysPosition paramSysPosition);

	public abstract void deleteSysPosition(SysPosition paramSysPosition);

	public abstract void insertSysPositionTl(SysPositionTl paramSysPositionTl);

	public abstract void updateSysPositionTl(SysPositionTl paramSysPositionTl);

	public abstract void deleteSysPositionTl(SysPositionTl paramSysPositionTl);
}
