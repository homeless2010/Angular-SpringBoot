package com.piedpiper.platform.api.portal;

import java.util.List;

import com.piedpiper.platform.api.portal.dto.SysPortal;
import com.piedpiper.platform.api.portal.dto.SysPortletConfig;

public abstract interface SysPortalAPI {
	public abstract SysPortal getSysPortalBySysUserId(String paramString1, String paramString2);

	public abstract void insertSysPortal(SysPortal paramSysPortal);

	public abstract void updateSysPortal(SysPortal paramSysPortal);

	public abstract void deleteSysPortalById(String paramString);

	public abstract List<SysPortletConfig> getList(String paramString);

	public abstract SysPortletConfig getSysPortletConfigById(String paramString);

	public abstract void updateSysPortletConfig(SysPortletConfig paramSysPortletConfig);

	public abstract void updateSysPortletConfigForIndex(String paramString1, String paramString2, String paramString3);

	public abstract void insertOrUpdateSysPortletConfig(SysPortletConfig[] paramArrayOfSysPortletConfig);

	public abstract void deleteSysPortletConfigByIds(String[] paramArrayOfString);
}
