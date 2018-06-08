package com.piedpiper.platform.api.sysaccesscontrol;

import com.piedpiper.platform.api.sysaccesscontrol.dto.SysAccesscontrol;
import com.piedpiper.platform.core.dao.PropertyFilter;
import com.piedpiper.platform.core.dao.PropertyFilter.MatchType;
import java.util.List;

public abstract interface SysAccesscontrolAPI {
	public abstract List<SysAccesscontrol> getSysAccesscontrol4AuthObject(String paramString1, String paramString2);

	public abstract void deleteSysAccesscontrolById(String paramString);

	public abstract void deleteSysAccesscontrol(SysAccesscontrol paramSysAccesscontrol);

	public abstract List<SysAccesscontrol> getSysAccesscontrolListByHQL(String paramString1, String paramString2,
			PropertyFilter.MatchType paramMatchType);

	public abstract Integer insertSysAccesscontrol(List<SysAccesscontrol> paramList);
}
