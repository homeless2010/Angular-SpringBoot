package com.piedpiper.platform.api.sysmenu;

import com.piedpiper.platform.api.portal.dto.LayoutModel;
import com.piedpiper.platform.api.session.dto.SecurityMenu;
import com.piedpiper.platform.api.session.dto.SecurityUser;
import com.piedpiper.platform.api.sysaccesscontrol.dto.SysAccesscontrol;
import com.piedpiper.platform.api.sysmenu.dto.SysMenu;
import com.piedpiper.platform.api.sysmenu.dto.SysMenuTl;
import com.piedpiper.platform.api.sysmenu.dto.SysMenuVo;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public abstract interface SysMenuAPI {
	public static final String PORTLETFILE = "PLATFORM6_PORTLETFILE";

	public abstract void reLoad() throws Exception;

	public abstract List<SysMenu> getAllSysMenus();

	public abstract List<SysMenu> getAllSysMenus(String paramString);

	public abstract <T> List<T> getAllSysMenus(String paramString, TypeReference<T> paramTypeReference);

	public abstract List<SysMenu> getSubSysMenus(SysMenu paramSysMenu);

	public abstract List<SysMenu> getSubSysMenus(String paramString);

	public abstract List<SysMenu> getSubSysMenus(String paramString1, String paramString2);

	public abstract int getSubSysMenusSize(String paramString);

	public abstract List<SysMenu> getTopSysMenus();

	public abstract SysMenuTl getSysMenusTl(SysMenu paramSysMenu, String paramString);

	public abstract SysMenuTl getSysMenusTlById(String paramString1, String paramString2);

	public abstract <T> T getSysMenusTlById(String paramString1, String paramString2,
			TypeReference<T> paramTypeReference);

	public abstract List<LayoutModel> getLayoutModelList(String paramString);

	public abstract List<List<LayoutModel>> getAllLayoutModelList();

	public abstract SysMenu getSysMenuById(String paramString);

	public abstract String getSysMenuIdByCode(String paramString1, String paramString2);

	public abstract SysMenu getSysMenuByCode(String paramString1, String paramString2);

	public abstract List<Map<String, Object>> recurgetSysMenuInfoByParentId(String paramString1, String paramString2,
			String paramString3, int paramInt, String paramString4, String paramString5);

	public abstract List<SysAccesscontrol> getMenusAccesscontrol(String paramString1, String paramString2);

	@Deprecated
	public abstract List<SysMenuTl> searchMenu(String paramString, Map<String, Object> paramMap);

	@Deprecated
	public abstract List<Map<String, Object>> getAllParents(List<Map<String, Object>> paramList, String paramString);

	public abstract List<SysMenuVo> getAllSubObjectInPermisson(String paramString1, String paramString2,
			String paramString3, String paramString4, boolean paramBoolean, Set<String> paramSet);

	public abstract List<String> getAllocationPortalMenuIds(SecurityUser paramSecurityUser);

	public abstract String getIconImageUrl(SysMenuVo paramSysMenuVo);

	public abstract String getIndexElementVlign(String paramString1, SecurityUser paramSecurityUser,
			SecurityMenu paramSecurityMenu, String paramString2);

	@Deprecated
	public abstract String getDefaultSysFunction(String paramString1, String paramString2);

	@Deprecated
	public abstract String createTab4DefaultSysFunction(String paramString1, String paramString2, String paramString3);

	public abstract String getMessagerUrl();

	public abstract String getQuickSearch(Locale paramLocale);

	public abstract void reloadHomePageMenuCache();

	public abstract String insertMenu(SysMenu paramSysMenu);

	public abstract void updateMenu(SysMenu paramSysMenu);

	public abstract void deleteMenu(String paramString);

	public abstract String getTraditionalIndexElementVlign(String paramString1, SecurityUser paramSecurityUser,
			SecurityMenu paramSecurityMenu, String paramString2);

	public abstract Integer insertMenus(List<SysMenu> paramList);

	public abstract int checkSysmeueCode(String paramString1, String paramString2);

	public abstract boolean checkSysMenuUrl(String paramString);

	public abstract String getLeftTreeMenus(String paramString1, SecurityUser paramSecurityUser,
			SecurityMenu paramSecurityMenu, String paramString2);

	public abstract String getSysMenuNameById(String paramString1, String paramString2);
}
