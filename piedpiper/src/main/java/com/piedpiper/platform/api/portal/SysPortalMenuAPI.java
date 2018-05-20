package com.piedpiper.platform.api.portal;

import java.util.List;

import com.piedpiper.platform.api.portal.dto.SysPortalMenu;

public abstract interface SysPortalMenuAPI {
	public abstract void deleltePortalMenuByMenuId(String paramString);

	public abstract List<SysPortalMenu> getPortalMenusByConfigId(String paramString);

	public abstract List<SysPortalMenu> getPortalMenusByConfigIdAndPid(String paramString1, String paramString2);

	public abstract SysPortalMenu getPortalMenuById(String paramString);
}
