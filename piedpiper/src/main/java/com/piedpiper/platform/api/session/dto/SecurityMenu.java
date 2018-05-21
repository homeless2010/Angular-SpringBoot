package com.piedpiper.platform.api.session.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.GenericType;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.springframework.util.Assert;

import com.piedpiper.platform.api.portal.SysPortalMenuAPI;
import com.piedpiper.platform.api.portal.dto.SysPortalMenu;
import com.piedpiper.platform.api.session.SessionHelper;
import com.piedpiper.platform.api.sysmenu.SysMenuAPI;
import com.piedpiper.platform.api.sysmenu.dto.SysMenu;
import com.piedpiper.platform.api.sysmenu.dto.SysMenuVo;
import com.piedpiper.platform.api.sysshirolog.context.ContextHolder;
import com.piedpiper.platform.api.sysshirolog.utils.SecurityUtil;
import com.piedpiper.platform.core.properties.PlatformProperties;
import com.piedpiper.platform.core.rest.client.RestClient;
import com.piedpiper.platform.core.rest.client.RestClientConfig;
import com.piedpiper.platform.core.rest.msg.ResponseMsg;
import com.piedpiper.platform.core.shiroSecurity.shiroCache.ShiroCacheManager;
import com.piedpiper.platform.core.spring.CacheSpringFactory;
import com.piedpiper.platform.core.spring.SpringFactory;

public class SecurityMenu implements Serializable {
	private static final long serialVersionUID = -897167350520519473L;
	public static final String DEFAULT_MENU_RESOURCES_COLLECTION = "avicit.menu.default";
	private Collection<SysMenuVo> menus = new ArrayList();

	public List<SysMenuVo> getMenusByParentId(String pid) {
		List<SysMenuVo> tm = new ArrayList();
		if (StringUtils.isEmpty(this.portletConfigId)) {
			if (this.menus == null) {
				return tm;
			}
			for (SysMenuVo s_menu : this.menus) {
				if ((s_menu != null) && (pid.equals(s_menu.getParentId()))) {
					tm.add(s_menu);
				}
			}
			Collections.sort(tm, new Comparator() {
				public int compare(SysMenuVo arg0, SysMenuVo arg1) {
					return arg0.compareTo(arg1);
				}
			});
			return tm;
		}
		for (SysMenuVo menu : this.portalMenuVos) {
			if (pid.equals(menu.getParentId())) {
				tm.add(menu);
			}
		}
		return tm;
	}

	public final Collection<SysMenuVo> getMenus() {
		return this.menus;
	}

	public int getChildMenuNumByParentId(String pid) {
		int num = 0;
		if (this.menus == null) {
			return num;
		}
		for (SysMenuVo s_menu : this.menus) {
			if ((s_menu != null) && (pid.equals(s_menu.getParentId()))) {
				num++;
			}
			int c_num = getChildMenuNumByParentId(s_menu.getId());
			num += c_num;
		}
		return num;
	}

	public List<List<SysMenuVo>> getChildColumnMenu(String pid) {
		List<List<SysMenuVo>> sms = new ArrayList();

		if (!StringUtils.isEmpty(this.portletConfigId)) {
			List<SysMenuVo> mnvs = new ArrayList();
			for (SysMenuVo menu : this.portalMenuVos) {
				if (pid.equals(menu.getParentId())) {
					mnvs.add(menu);
				}
			}
			sms.add(mnvs);
		} else {
			LinkedHashMap<String, List<SysMenuVo>> lhm = new LinkedHashMap();
			for (SysMenuVo s_menu : this.menus) {
				if ((s_menu != null) && (pid.equals(s_menu.getParentId())) && ("1".equals(s_menu.getType()))
						&& ("1".equals(s_menu.getStatus()))) {
					String colIdx = s_menu.getMenuGroup() == null ? "100000" : s_menu.getMenuGroup();
					if (lhm.get(colIdx) == null) {
						lhm.put(colIdx, new ArrayList());
					}
					((List) lhm.get(colIdx)).add(s_menu);
				}
			}

			Comparator<SysMenuVo> com = new Comparator() {
				public int compare(SysMenuVo arg0, SysMenuVo arg1) {
					return arg0.compareTo(arg1);
				}
			};
			Iterator<List<SysMenuVo>> itr = lhm.values().iterator();
			while (itr.hasNext()) {
				List<SysMenuVo> lx = (List) itr.next();
				Collections.sort(lx, com);
				sms.add(lx);
			}
		}

		return sms;
	}

	public void setMenus(Collection<SysMenuVo> menus) {
		this.menus = menus;
	}

	public void reflashMenu(String appId) {
		SecurityUser shiroUser = (SecurityUser) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
		if (shiroUser != null) {
			SimpleAuthorizationInfo info = (SimpleAuthorizationInfo) ShiroCacheManager.instance.getCache(null)
					.get(shiroUser);
			if (info == null) {
				refalshShiroCaceh();
				info = (SimpleAuthorizationInfo) ShiroCacheManager.instance.getCache(null).get(shiroUser);
			}
			if (info != null) {
				this.menus = getAllSubObjectInPermisson(appId, info.getStringPermissions());
			} else {
				this.menus = getAllSubObjectInPermisson(appId, null);
			}
		}
	}

	public void convertToMenuVo(String currentLanguagecode) {
		getPortletMenuByPid(this.portletConfigId, "-1", currentLanguagecode, "-1");
	}

	private boolean getPortletMenuByPid(String configId, String pid, String code, String menuPid) {
		SysPortalMenuAPI portletmenu = (SysPortalMenuAPI) CacheSpringFactory.getInstance()
				.getBean(SysPortalMenuAPI.class);
		SysMenuAPI menuAPI = (SysMenuAPI) CacheSpringFactory.getInstance().getBean(SysMenuAPI.class);
		List<SysPortalMenu> portalMenus = portletmenu.getPortalMenusByConfigIdAndPid(configId, pid);
		if (portalMenus.size() == 0) {
			return false;
		}
		SysPortalMenu first = null;
		for (SysPortalMenu menu : portalMenus) {
			if ("first".equals(menu.getBeforeId())) {
				first = menu;
				break;
			}
		}

		while (first != null) {
			SysMenuVo vo = new SysMenuVo();
			SysMenu sysMenu = menuAPI.getSysMenuById(first.getMenuId());
			String chechId = sysMenu.getUrl() == null ? sysMenu.getId() : sysMenu.getUrl();
			if (SecurityUtil.checkPortletMenu(chechId, this.appId)) {
				vo.setCode(sysMenu.getCode());
				vo.setName(menuAPI.getSysMenuNameById(first.getMenuId(), code));
				vo.setUrl(sysMenu.getUrl());
				vo.setId(sysMenu.getId());
				vo.setParentId(menuPid);
				vo.setHasChild(Boolean.valueOf(getPortletMenuByPid(configId, first.getId(), code, first.getMenuId())));
				vo.setType("1");
				this.portalMenuVos.add(vo);
			}

			first = portletmenu.getPortalMenuById(first.getAfterId());
		}

		return true;
	}

	private List<SysMenuVo> portalMenuVos = new ArrayList();

	public List<SysMenuVo> getPortalMenuVos() {
		return this.portalMenuVos;
	}

	public void setPortalMenuVos(List<SysMenuVo> portalMenuVos) {
		this.portalMenuVos = portalMenuVos;
	}

	private String portletConfigId = "";
	private String appId;
	private boolean isAdmin = false;

	public boolean isAdmin() {
		return this.isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getPortletConfigId() {
		return this.portletConfigId;
	}

	public void setPortletConfigId(String portletConfigId) {
		this.portletConfigId = portletConfigId;
	}

	public void reflashMenu(String appId, Object portletId) {
		this.appId = appId;
		if ((portletId != null) && (!this.isAdmin)) {
			this.portletConfigId = portletId.toString();
			return;
		}

		SecurityUser shiroUser = (SecurityUser) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();

		if (shiroUser != null) {
			SimpleAuthorizationInfo info = (SimpleAuthorizationInfo) ShiroCacheManager.instance.getCache(null)
					.get(shiroUser);

			if (info == null) {
				refalshShiroCaceh();
				info = (SimpleAuthorizationInfo) ShiroCacheManager.instance.getCache(null).get(shiroUser);
			}

			if (info != null) {
				this.menus = getAllSubObjectInPermisson(appId, info.getStringPermissions());
			} else {
				this.menus = getAllSubObjectInPermisson(appId, null);
			}
		}
	}

	private Set<String> doGetAuthorizationInfoPermissions(String userId, String appid, String orgId) {
		Assert.notNull(userId);
		Assert.notNull(appid);
		Assert.notNull(orgId);
		String restHost = RestClientConfig.getRestHost("syshirosecurity");
		String restURL = restHost + "/api/platform6/shiroSecurity/permisstion/doGetAuthorizationInfoPermissions/"
				+ userId + "/" + appid + "/v1";
		ResponseMsg<Set<String>> responseMsg = RestClient.doGet(restURL, new GenericType() {
		});
		Set<String> authorizationInfoPermissions = null;
		if (responseMsg.getRetCode().equals("200")) {
			authorizationInfoPermissions = (Set) responseMsg.getResponseBody();
		}

		return authorizationInfoPermissions;
	}

	private List<SysMenuVo> getAllSubObjectInPermisson(String appid, Set<String> permissions) {
		SysMenuAPI sysmenuloader = (SysMenuAPI) SpringFactory.getBean(SysMenuAPI.class);
		HttpServletRequest req = ContextHolder.getRequest();
		List<SysMenuVo> allMenus = sysmenuloader.getAllSubObjectInPermisson(appid, SessionHelper.getLoginName(req),
				SessionHelper.getLoginSysUserId(req), SessionHelper.getCurrentUserLanguageCode(req),
				PlatformProperties.defaultPageAuthIsGranted(), permissions);
		return allMenus;
	}

	private void refalshShiroCaceh() {
		SecurityUtils.getSubject().isPermitted("shuaxinquanxia");
	}
}
