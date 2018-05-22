package com.piedpiper.platform.api.sysuser;

import com.piedpiper.platform.api.sysuser.dto.SysOrg;
import com.piedpiper.platform.api.sysuser.dto.SysOrgTl;
import com.piedpiper.platform.api.sysuser.dto.SysOrgVo;
import java.util.List;

public abstract interface SysOrgAPI {
	public abstract List<SysOrg> getAllSysOrgList();

	public abstract SysOrgTl getSysOrgTl(String paramString1, String paramString2);

	public abstract SysOrg getSysOrgBySysOrgId(String paramString);

	public abstract String getSysOrgNameBySysOrgId(String paramString1, String paramString2);

	public abstract String getSysOrgNamesBySysOrgIds(String paramString1, String paramString2, String paramString3);

	public abstract List<SysOrg> getSubSysOrgListBySysOrgId(String paramString);

	public abstract SysOrg getParentSysOrgBySysOrgId(String paramString);

	public abstract String getParentSysOrgIdBySysOrgId(String paramString);

	public abstract List<SysOrg> getAllSubSysOrgListBySysOrgId(String paramString);

	public abstract String getAllSubSysOrgIdBySysOrgId(String paramString1, String paramString2);

	public abstract List<SysOrg> getAllParentSysOrgListBySysOrgId(String paramString);

	public abstract String getAllParentSysOrgIdBySysOrgId(String paramString1, String paramString2);

	public abstract String getSysOrgIdByCode(String paramString);

	public abstract boolean checkOrgCode(String paramString);

	public abstract boolean existsChilds(String paramString);

	public abstract void insertSysOrg(SysOrgVo paramSysOrgVo);

	public abstract void updateSysOrg(SysOrgVo paramSysOrgVo);

	public abstract void deleteSysOrg(SysOrgVo paramSysOrgVo);
}
