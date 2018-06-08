package com.piedpiper.platform.api.sysshirolog;

import java.util.Set;

public abstract interface IAuthServiceAPI {
	public abstract String loadFilterChainDefinitions();

	public abstract void reCreateFilterChains();

	public abstract Set<String> findRoles(String paramString);

	public abstract Set<String> findPermissions(String paramString);
}
