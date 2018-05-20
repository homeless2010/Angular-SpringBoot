package com.piedpiper.platform.api.syscustomed;

import java.util.List;

import com.piedpiper.platform.api.syscustomed.dto.SysCustomed;

public abstract interface SysCustomedAPI {
	public abstract void reLoad() throws Exception;

	@Deprecated
	public abstract SysCustomed getSysCustomedByUserIdAndKey(String paramString1, String paramString2);

	public abstract SysCustomed getSysCustomedByUserIdAndKey(String paramString1, String paramString2,
			String paramString3);

	public abstract String getCustomesSkin(String paramString1, String paramString2);

	public abstract List<SysCustomed> getSysCustomedListByUserIdAndKey(String paramString1, String paramString2,
			String paramString3, String paramString4);

	@Deprecated
	public abstract List<SysCustomed> getCustomedMenuStyle(String paramString1, String paramString2,
			String paramString3);

	public abstract List<SysCustomed> getCustomedByKeyVal(String paramString1, String paramString2, String paramString3,
			String paramString4);

	public abstract void saveCustomesSkin(String paramString1, String paramString2, String paramString3,
			String paramString4) throws Exception;

	public abstract void insertSysCustomed(SysCustomed paramSysCustomed);

	public abstract String deleteSysCustomedByPslGrp(String paramString);

	public abstract void saveCustomedKeyValueForDefault(String paramString1, String paramString2, String paramString3,
			String paramString4, String paramString5) throws Exception;

	public abstract void saveCustomedKeyValueForNotDefault(String paramString1, String paramString2,
			String paramString3, String paramString4, String paramString5) throws Exception;

	public abstract void saveCustomedKeyValue(String paramString1, String paramString2, String paramString3,
			String paramString4, String paramString5, String paramString6) throws Exception;

	public abstract String getCustomedValueByKey(String paramString1, String paramString2, String paramString3);
}
