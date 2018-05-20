package com.piedpiper.platform.api.syspermissionresource.scantags;

import com.piedpiper.platform.api.syspermissionresource.scantags.factory.RefreshPermComRepositoryFactory;
import com.piedpiper.platform.api.syspermissionresource.scantags.permcominterface.PermComRepositoryI;

public abstract class PermissionComponentUtil {
	private PermissionComponentUtil() {
		throw new Error("工具类禁止实例化");
	}

	private static class Holder {
		private static final RefreshPermComRepositoryFactory holder = new RefreshPermComRepositoryFactory();
	}

	public static PermComRepositoryI getAllComponentOfPage(String pageUrl) {
		return getFactory().createPermComRepositoryByUrl(pageUrl).refreshPermComRepository();
	}

	private static RefreshPermComRepositoryFactory getFactory() {
		return Holder.holder;
	}
}
