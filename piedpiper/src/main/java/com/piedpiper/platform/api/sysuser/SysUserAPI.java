package com.piedpiper.platform.api.sysuser;

import com.piedpiper.platform.api.sysuser.dto.SysUser;
import com.piedpiper.platform.core.dao.Paging;
import com.piedpiper.platform.core.rest.msg.Muti3Bean;
import java.util.List;
import java.util.Map;

public abstract interface SysUserAPI {
	public abstract void reLoad() throws Exception;

	public abstract List<SysUser> getAllSysUsers();

	public abstract List<SysUser> getAllSysUsersNoFilter();

	public abstract SysUser getSysUserById(String paramString);

	public abstract SysUser getSysUserByIdNoFilter(String paramString);

	public abstract SysUser getSysUserByUnitCode(String paramString);

	public abstract SysUser getSysUserByLoginName(String paramString);

	public abstract SysUser getSysUserByEmail(String paramString);

	public abstract SysUser getSysUserByLoginString(String paramString);

	public abstract String getSysUserNameById(String paramString);

	public abstract String getSysUserNameByLoginString(String paramString);

	public abstract boolean checkUserNo(String paramString);

	public abstract boolean checkUserUnitCode(String paramString);

	public abstract boolean checkUserLoginName(String paramString);

	public abstract void insertSysUser(SysUser paramSysUser);

	public abstract void updateSysUserNotEvent(SysUser paramSysUser);

	public abstract void insertSysUserNotEvent(SysUser paramSysUser);

	public abstract void updateSysUser(SysUser paramSysUser);

	public abstract void deleteSysUser(SysUser paramSysUser);

	public abstract Paging<SysUser> getPageList(Paging<SysUser> paramPaging, Map<String, Object> paramMap);

	public abstract Muti3Bean<SysUser, byte[], byte[]> getSysUserByIdforupload(String paramString);

	@Deprecated
	public abstract String changePassword(String paramString1, String paramString2, SysUser paramSysUser);

	public abstract List<SysUser> getSysUserByHQL(String paramString, Object[] paramArrayOfObject);

	public abstract List<SysUser> find(String paramString1, String paramString2, String paramString3);

	public abstract List<SysUser> findUserForPopup(String paramString);
}
