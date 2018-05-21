package com.piedpiper.platform.api.sysmenu.impl;

import com.piedpiper.platform.api.portal.dto.LayoutModel;
import com.piedpiper.platform.api.session.SessionHelper;
import com.piedpiper.platform.api.session.dto.SecurityMenu;
import com.piedpiper.platform.api.session.dto.SecurityUser;
import com.piedpiper.platform.api.sysaccesscontrol.dto.SysAccesscontrol;
import com.piedpiper.platform.api.syslookup.SysLookupAPI;
import com.piedpiper.platform.api.sysmenu.SysMenuAPI;
import com.piedpiper.platform.api.sysmenu.dto.SysMenu;
import com.piedpiper.platform.api.sysmenu.dto.SysMenuTl;
import com.piedpiper.platform.api.sysmenu.dto.SysMenuVo;
import com.piedpiper.platform.api.sysprofile.SysProfileAPI;
import com.piedpiper.platform.api.sysresource.dto.SysResource;
import com.piedpiper.platform.core.locale.PlatformLocalesJSTL;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheManager;
import com.piedpiper.platform.core.rest.client.RestClient;
import com.piedpiper.platform.core.rest.client.RestClientConfig;
import com.piedpiper.platform.core.rest.msg.Muti2Bean;
import com.piedpiper.platform.core.rest.msg.Muti3Bean;
import com.piedpiper.platform.core.rest.msg.Muti6Bean;
import com.piedpiper.platform.core.rest.msg.ResponseMsg;
import com.fasterxml.jackson.core.type.TypeReference;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.ws.rs.core.GenericType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class SysMenuAPImpl implements SysMenuAPI {
	private static final Logger log = LoggerFactory.getLogger(SysMenuAPImpl.class);
	private static final int MENU_LENGTH = 16;

	@Autowired
	private BaseCacheManager baseCacheManager;

	@Autowired
	private SysProfileAPI sysProfileAPI;

	@Autowired
	private SysLookupAPI sysLookupAPI;
	private static final String FIRST_HAS_PATTERN = "<li class='dc-mega-li'><a class='dc-mega' href='javascript:void(0)' style='display:block;' onclick='return false;'>%s</a>%s";
	private static final String FIRST_NO_PATTERN = "<li class='dc-mega-li'><a class='dc-mega' href='javascript:void(0)' style='display:block;'onclick=\"addTab('%s','%s','%s','%s','%s');return false;\">%s</a></li>";
	private static final String SECOND_HEAD1 = "<ul><table border ='0' height='100%' cellpadding='0' cellspacing='0'><tr>";
	private static final String SECOND_HEAD2_1 = "<td valign='top' class='iconItemLeftWithoutPadding' style='border-right:solid 1px;border-right-color:#d0d0d0;'>";
	private static final String SECOND_HEAD2_2 = "<td valign='top' class='iconItemLeftWithoutPadding' style='border-left:solid 1px;border-left-color:#ffffff;'>";
	private static final String SECOND_HEAD2_3 = "<td valign='top' class='iconItemLeft' style='border-right:solid 1px;border-right-color:#d0d0d0;border-left:solid 1px;border-left-color:#ffffff;'>";
	private static final String SECOND_HEAD2_4 = "<td valign='top' class='iconItemLeftWithoutPadding'>";
	private static final String SECOND_HEAD3 = "<table cellpadding='0' cellspacing='0' height='100%' width='100%'>";
	private static final String SECOND_HEAD4 = "<tr><td valign='top' height='20' width=100%>";
	private static final String SECOND_END1 = "<tr></tr></table></td>";
	private static final String SECOND_END2 = "</tr></table></ul></li>";
	private static final String SECOND_PATTERN = "<li class='dc-mega-li'><a class='pop-dc-mega' href='javascript:void(0)' style='display:block;' onclick=\"addTab('%s','%s','%s','%s','%s');return false;\"%s>%s</a></li></td></tr>";
	private static final String THIRD_PATTERN = "<tr><td valign='top' height='20' width=100%%><li><ul><a class ='subMenuStyle' href='javascript:void(0)' onclick=\"addTab('%s','%s','%s','%s','%s');return false;\"%s>%s</a></ul></li></td></tr>";
	private static final String PX0 = "0px 0px";
	private static final String TOOLTIP_PATTERN = "title='%s'";
	private static final String url_1 = "<span class='icon' style='background-image:url(%s);background-position:%s;background-repeat:no-repeat no-repeat;display:block;float:left;with:16px;height:16px;'></span>";
	private static final String url_2 = "<span style='*float:left;line-height:16px;cursor:pointer;'>%s</span>";
	private static final String url_3 = "<span style='*float:left;line-height:16px;'>%s</span>";
	private static final Map<String, String> VALUE_BYCODE = new ConcurrentHashMap();

	public void reLoad() throws Exception {
		VALUE_BYCODE.clear();
		String url = new StringBuilder().append(RestClientConfig.getRestHost("sysmenu"))
				.append("/api/platform6/sysmenu/SysMenu/reLoad/v1").toString();
		ResponseMsg responseMsg = RestClient.doGet(url, new GenericType() {
		});
		if (responseMsg.getRetCode().equals("200"))
			return;
		throw new Exception(responseMsg.getErrorDesc());
	}

	public List<SysMenu> getAllSysMenus() {
		return this.baseCacheManager.getAllFromCache("PLATFORM6_MENU", new TypeReference() {
		});
	}

	public List<SysMenu> getAllSysMenus(String appId) {
		return this.baseCacheManager.getAllFromCache("PLATFORM6_MENU", new TypeReference() {
		}, appId);
	}

	public <T> List<T> getAllSysMenus(String appId, TypeReference<T> typeReference) {
		return this.baseCacheManager.getAllFromCache("PLATFORM6_MENU", typeReference, appId);
	}

	public List<SysMenu> getSubSysMenus(SysMenu parent) {
		return getSubSysMenus(parent.getId());
	}

	public List<SysMenu> getSubSysMenus(String parentId) {
		return this.baseCacheManager.getAllFromCache(
				new StringBuilder().append("PLATFORM6_MENU_MENU_").append(parentId).toString(), new TypeReference() {
				}, SessionHelper.getApplicationId());
	}

	public List<SysMenu> getTopSysMenus() {
		return getSubSysMenus("-1");
	}

	public SysMenuTl getSysMenusTl(SysMenu sysMenu, String languageCode) {
		return getSysMenusTlById(sysMenu.getId(), languageCode);
	}

	public SysMenuTl getSysMenusTlById(String sysMenuId, String languageCode) {
		return ((SysMenuTl) this.baseCacheManager.getObjectFromCache("PLATFORM6_MENUTLUNITID",
				new StringBuilder().append(sysMenuId).append("_").append(languageCode).toString(), new TypeReference() {
				}));
	}

	public <T> T getSysMenusTlById(String sysMenuId, String languageCode, TypeReference<T> typeReference) {
		return this.baseCacheManager.getObjectFromCache("PLATFORM6_MENUTLUNITID",
				new StringBuilder().append(sysMenuId).append("_").append(languageCode).toString(), typeReference);
	}

	public List<LayoutModel> getLayoutModelList(String layoutId) {
		return ((List) this.baseCacheManager.getObjectFromCache("PLATFORM6_PORTLETFILE", layoutId, new TypeReference() {
		}));
	}

	public List<List<LayoutModel>> getAllLayoutModelList() {
		return this.baseCacheManager.getAllFromCacheByOrder("PLATFORM6_PORTLETFILE", new TypeReference() {
		}, new Comparator() {
			public int compare(List<LayoutModel> o1, List<LayoutModel> o2) {
				int int1 = Integer.parseInt(((LayoutModel) o1.get(0)).getLayoutId().substring(6));
				int int2 = Integer.parseInt(((LayoutModel) o2.get(0)).getLayoutId().substring(6));
				return (int1 - int2);
			}
		});
	}

	public SysMenu getSysMenuById(String menuId) {
		return ((SysMenu) this.baseCacheManager.getObjectFromCache("PLATFORM6_MENU", menuId, SysMenu.class));
	}

	public List<SysMenu> getSubSysMenus(String parentId, String appId) {
		return this.baseCacheManager.getAllFromCache(
				new StringBuilder().append("PLATFORM6_MENU_MENU_").append(parentId).toString(), new TypeReference() {
				}, appId);
	}

	public int getSubSysMenusSize(String parentId) {
		return this.baseCacheManager
				.countSizeByKey(new StringBuilder().append("PLATFORM6_MENU_MENU_").append(parentId).toString());
	}

	public String getSysMenuIdByCode(String code, String appId) {
		SysMenu sysMenu = (SysMenu) this.baseCacheManager.getObjectFromCache("PLATFORM6_MENU_CODE", code,
				new TypeReference() {
				}, appId);
		if (sysMenu != null) {
			return sysMenu.getId();
		}
		return null;
	}

	public SysMenu getSysMenuByCode(String code, String appId) {
		return ((SysMenu) this.baseCacheManager.getObjectFromCache("PLATFORM6_MENU_CODE", code, new TypeReference() {
		}, appId));
	}

	public List<Map<String, Object>> recurgetSysMenuInfoByParentId_old(String languageCode, String applicationId,
			String parentId, int level, String targetType, String targetId) {
		Map parameter = new HashMap();
		parameter.put("languageCode", (languageCode == null) ? "" : languageCode);
		parameter.put("applicationId", (applicationId == null) ? "" : applicationId);
		parameter.put("parentId", (parentId == null) ? "" : parentId);
		parameter.put("level", Integer.valueOf(level));
		parameter.put("targetType", (targetType == null) ? "" : targetType);
		parameter.put("targetId", (targetId == null) ? "" : targetId);
		String url = new StringBuilder().append(RestClientConfig.getRestHost("sysmenu"))
				.append("/api/platform6/sysmenu/SysMenu/recurgetSysMenuInfoByParentId/v1").toString();
		ResponseMsg responseMsg = RestClient.doPost(url, parameter, new GenericType() {
		});
		if (responseMsg.getRetCode().equals("200")) {
			return ((List) responseMsg.getResponseBody());
		}
		log.error(new StringBuilder().append("url:").append(url).append(",error:").append(responseMsg.getErrorDesc())
				.toString());
		return null;
	}

	public List<Map<String, Object>> recurgetSysMenuInfoByParentId(String languageCode, String applicationId,
			String parentId, int level, String targetType, String targetId) {
		Map parameter = new HashMap();
		parameter.put("languageCode", (languageCode == null) ? "zh_CN" : languageCode);
		parameter.put("applicationId", (applicationId == null) ? "1" : applicationId);
		parameter.put("parentId", (parentId == null) ? "" : parentId);
		parameter.put("level", Integer.valueOf(level));
		parameter.put("targetType", (targetType == null) ? "" : targetType);
		parameter.put("targetId", (targetId == null) ? "" : targetId);

		if (null == parentId) {
			parentId = "-1";
		}

		List menuAccessList = null;
		if ((StringUtils.isNotBlank(targetType)) && (StringUtils.isNotBlank(targetId))) {
			menuAccessList = getMenusAccesscontrol(targetType, targetId);
		}

		--level;

		List listMap = getSubSysMenus(parentId, applicationId);
		List resultListMap = new ArrayList();

		if ((null == listMap) || (0 == listMap.size()))
			return null;

		for (SysMenu sysmenu : listMap) {
			Map map = new HashMap();
			if (!("-1".equals(parentId))) {
				map.put("_parentId", parentId);
			}
			String id = sysmenu.getId();
			if (StringUtils.isBlank(id)) {
				continue;
			}
			map.put("ID", id);
			SysMenuTl sysMenuTl = null;
			sysMenuTl = getSysMenusTlById(id, languageCode);
			String menuName = "";
			if (sysMenuTl == null) {
				menuName = new StringBuilder().append(sysmenu.getCode()).append("_c").toString();
				map.put("COMMENTS", "");
				map.put("SYSMENUTL_ID", "");
			} else {
				menuName = sysMenuTl.getName();
				map.put("COMMENTS", sysMenuTl.getComments());
				map.put("SYSMENUTL_ID", sysMenuTl.getId());
			}

			map.put("NAME", menuName);
			map.put("REMARK", "");
			map.put("LANGUAGE_CODE", languageCode);
			if ((null != menuAccessList) && (menuAccessList.size() > 0)) {
				for (SysAccesscontrol menuAccess : menuAccessList) {
					if (id.equals(menuAccess.getResoureId())) {
						map.put("REMARK", menuAccess.getAccessibility());
					}
				}
			}
			BigDecimal childCount = new BigDecimal(getSubSysMenusSize(id));
			if ((null == childCount) || (childCount.longValue() <= 0L)) {
				map.put("state", "open");
				resultListMap.add(map);
			}

			if (level > 0) {
				List cnodes = recurgetSysMenuInfoByParentId(languageCode, applicationId, (String) map.get("ID"), level,
						targetType, targetId);
				resultListMap.addAll(cnodes);
				map.put("state", "open");
			} else {
				map.put("state", "closed");
			}

			resultListMap.add(map);
		}

		return resultListMap;
	}

	public List<SysAccesscontrol> getMenusAccesscontrol(String targetType, String targetId) {
		List list = this.baseCacheManager.getAllFromCache(new StringBuilder().append("PLATFORM6_ACCESSCONTROL_TYPE_ID_")
				.append(targetType).append("_").append(targetId).toString(), new TypeReference() {
				});
		for (SysAccesscontrol sysAccesscontrol : list) {
			SysResource sysResource = (SysResource) this.baseCacheManager.getObjectFromCache("PLATFORM6_SYSRESOURCE",
					sysAccesscontrol.getResoureId(), new TypeReference() {
					});
			if (sysResource != null) {
				sysAccesscontrol.setTargetType(sysResource.getType());
			}
		}
		return list;
	}

	public List<SysMenuTl> searchMenu(String languageCode, Map<String, Object> params) {
		return null;
	}

	public List<Map<String, Object>> getAllParents(List<Map<String, Object>> nodes, String sysLanguageCode) {
		return null;
	}

	public List<String> getAllocationPortalMenuIds(SecurityUser user) {
		String url = new StringBuilder().append(RestClientConfig.getRestHost("sysmenu"))
				.append("/api/platform6/sysmenu/SysMenu/getAllocationPortalMenuIds/v1").toString();
		ResponseMsg responseMsg = RestClient.doPost(url, user, new GenericType() {
		});
		if (responseMsg.getRetCode().equals("200")) {
			return ((List) responseMsg.getResponseBody());
		}
		log.error(new StringBuilder().append("url:").append(url).append(",error:").append(responseMsg.getErrorDesc())
				.toString());
		return null;
	}

	public String getIconImageUrl(SysMenuVo sysMenuvo) {
		return getIconImageUrlMore(sysMenuvo, "", "", "", "", "");
	}

	public String getIconImageUrlMore(SysMenuVo sysMenuvo, String defaultIconImage, String showTabIcon,
			String showDivIcon, String firstDivIcon, String secondDivIcon) {
		boolean hasUrlFlag = true;
		if ((sysMenuvo.getUrl() == null) || ("".equals(sysMenuvo.getUrl()))) {
			hasUrlFlag = false;
		}
		StringBuilder sBuffer = new StringBuilder();
		try {
			if ((showDivIcon != null) && (Boolean.valueOf(showDivIcon).booleanValue())) {
				String[] icons = getSystemmIconPara(sysMenuvo.getImage(), defaultIconImage, showTabIcon);
				sBuffer.append(new StringBuilder().append("<span class='icon' style='background-image:url(")
						.append(icons[0]).append(");background-position:").append(icons[1])
						.append(";background-repeat:no-repeat no-repeat;display:block;float:left;with:16px;height:16px;'></span>")
						.toString());
				if (hasUrlFlag)
					sBuffer.append("<span style='*float:left;line-height:16px;cursor:pointer;'>")
							.append(formatIconName(sysMenuvo.getName())).append("</span>");
				else
					sBuffer.append("<span style='*float:left;line-height:16px;'>")
							.append(formatIconName(sysMenuvo.getName())).append("</span>");
			} else {
				if ((sysMenuvo.getHasChild() != null) && (sysMenuvo.getHasChild().booleanValue()))
					sBuffer.append(new StringBuilder().append("<img src='").append(firstDivIcon)
							.append("' border=0 style='margin-right:3px;'>").toString());
				else {
					sBuffer.append(new StringBuilder().append("<img src='").append(secondDivIcon)
							.append("' border=0  style='margin-right:3px;'>").toString());
				}
				if (hasUrlFlag)
					sBuffer.append("<span style='line-height:20px;cursor:pointer;'>")
							.append(formatIconName(sysMenuvo.getName())).append("</span>");
				else
					sBuffer.append("<span style='line-height:20px;'>").append(formatIconName(sysMenuvo.getName()))
							.append("</span>");
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return sBuffer.toString();
	}

	private String[] getSystemmIconPara(String iconImage, String defaultIconImage, String showTabIcon) {
		String[] paras = new String[2];
		if ((showTabIcon != null) && (Boolean.valueOf(showTabIcon).booleanValue())) {
			if (iconImage == null) {
				paras[0] = defaultIconImage;
				paras[1] = "0px 0px";
			} else {
				String[] imageUrl = StringUtils.split(iconImage, ' ');
				if (imageUrl.length == 3) {
					paras[0] = imageUrl[0];
					paras[1] = new StringBuilder().append(imageUrl[1]).append(" ").append(imageUrl[2]).toString();
				} else {
					paras[0] = defaultIconImage;
					paras[1] = "0px 0px";
				}
			}
		} else {
			paras[0] = "";
			paras[1] = "0px 0px";
		}
		return paras;
	}

	public String formatIconName(String iconName) {
		String finalIconName = "";
		try {
			finalIconName = getSubStringForMenu(iconName, 16);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return iconName;
		}

		return finalIconName;
	}

	public String getSubStringForMenu(String value, int length) throws Exception {
		if ((value == null) || (value.equals(""))) {
			return "";
		}
		byte[] bytes = value.getBytes("Unicode");
		int n = 0;
		int i = 2;
		for (; (i < bytes.length) && (n < length); ++i) {
			if (i % 2 == 1) {
				++n;
			} else if (bytes[i] != 0) {
				++n;
			}

		}

		if (i % 2 == 1) {
			i += 1;
		}
		String tempValue = new String(bytes, 0, i, "Unicode");
		if (value.length() > tempValue.length()) {
			return new StringBuilder().append(tempValue).append("...").toString();
		}
		return tempValue;
	}

	private Object getEachColElementForPortal(String defaultImage, String parentId, String showTabIcon,
			String iconTipsFlag, String showDivIcon, String currentLanguagecode, String appId, SecurityMenu menu) {
		List list = menu.getChildColumnMenu(parentId);
		StringBuilder sBuffer = new StringBuilder();
		sBuffer.append("<ul><table border ='0' height='100%' cellpadding='0' cellspacing='0'><tr>");
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); ++i) {
				if (list.size() > 1) {
					if (i == 0) {
						sBuffer.append(
								"<td valign='top' class='iconItemLeftWithoutPadding' style='border-right:solid 1px;border-right-color:#d0d0d0;'>");
					} else if (i == list.size() - 1)
						sBuffer.append(
								"<td valign='top' class='iconItemLeftWithoutPadding' style='border-left:solid 1px;border-left-color:#ffffff;'>");
					else {
						sBuffer.append(
								"<td valign='top' class='iconItemLeft' style='border-right:solid 1px;border-right-color:#d0d0d0;border-left:solid 1px;border-left-color:#ffffff;'>");
					}
				} else {
					sBuffer.append("<td valign='top' class='iconItemLeftWithoutPadding'>");
				}

				sBuffer.append("<table cellpadding='0' cellspacing='0' height='100%' width='100%'>");

				List sysMenuVoListNew = (List) list.get(i);

				for (SysMenuVo sysMenuVo : sysMenuVoListNew) {
					if ("1".equals(sysMenuVo.getType())) {
						sBuffer.append("<tr><td valign='top' height='20' width=100%>");
						if ((sysMenuVo.getHasChild() != null) && (sysMenuVo.getHasChild().booleanValue())) {
							String[] icons1 = getSystemmIconPara(sysMenuVo.getImage(), defaultImage, showTabIcon);
							sBuffer.append(String.format(
									"<li class='dc-mega-li'><a class='pop-dc-mega' href='javascript:void(0)' style='display:block;' onclick=\"addTab('%s','%s','%s','%s','%s');return false;\"%s>%s</a></li></td></tr>",
									new Object[]{sysMenuVo.getName(),
											(sysMenuVo.getUrl() == null) ? "" : sysMenuVo.getUrl(), icons1[0],
											(sysMenuVo.getCode() == null) ? "" : sysMenuVo.getCode(), icons1[1],
											setIconTitleTips(sysMenuVo, iconTipsFlag, appId), getIconImageUrl(sysMenuVo,
													defaultImage, showTabIcon, showDivIcon, appId)}));

							Collection sysMenuVoListChildSecond = menu.getMenusByParentId(sysMenuVo.getId());
							if (sysMenuVoListChildSecond != null) {
								for (SysMenuVo sysMenuVoChildSecond : sysMenuVoListChildSecond) {
									if ("1".equals(sysMenuVoChildSecond.getType())) {
										String[] icons = getSystemmIconPara(sysMenuVoChildSecond.getImage(),
												defaultImage, showTabIcon);
										sBuffer.append(String.format(
												"<tr><td valign='top' height='20' width=100%%><li><ul><a class ='subMenuStyle' href='javascript:void(0)' onclick=\"addTab('%s','%s','%s','%s','%s');return false;\"%s>%s</a></ul></li></td></tr>",
												new Object[]{sysMenuVoChildSecond.getName(),
														(sysMenuVoChildSecond.getUrl() == null)
																? ""
																: sysMenuVoChildSecond.getUrl(),
														icons[0],
														(sysMenuVoChildSecond.getCode() == null)
																? ""
																: sysMenuVoChildSecond.getCode(),
														icons[1],
														setIconTitleTips(sysMenuVoChildSecond, iconTipsFlag, appId),
														getIconImageUrl(sysMenuVoChildSecond, defaultImage, showTabIcon,
																showDivIcon, appId)}));
									}

								}

							}

						} else {
							String[] icons2 = getSystemmIconPara(sysMenuVo.getImage(), defaultImage, showTabIcon);
							sBuffer.append(String.format(
									"<li class='dc-mega-li'><a class='pop-dc-mega' href='javascript:void(0)' style='display:block;' onclick=\"addTab('%s','%s','%s','%s','%s');return false;\"%s>%s</a></li></td></tr>",
									new Object[]{sysMenuVo.getName(),
											(sysMenuVo.getUrl() == null) ? "" : sysMenuVo.getUrl(), icons2[0],
											(sysMenuVo.getCode() == null) ? "" : sysMenuVo.getCode(), icons2[1],
											setIconTitleTips(sysMenuVo, iconTipsFlag, appId), getIconImageUrl(sysMenuVo,
													defaultImage, showTabIcon, showDivIcon, appId)}));
						}

					}

				}

				sBuffer.append("<tr></tr></table></td>");
			}
		}

		sBuffer.append("</tr></table></ul></li>");
		return sBuffer.toString();
	}

	private String getIndexElementForPortal(String currentLanguagecode, SecurityMenu menu, String appId) {
		Collection sysMenuVoList = menu.getMenusByParentId("-1");
		String showTabIcon = getProfileValueByCode("PLATFORM_V6_DISPLAY_TAB_ICON", appId);
		String iconTipsFlag = getProfileValueByCode("PLATFORM_V6_DISPLAY_ICON_TIPS", appId);
		String showDivIcon = getProfileValueByCode("PLATFORM_V6_DISPLAY_DIV_ICON", appId);

		String defaultImage = getProfileValueByCode("PLATFORM_V6_DEFAULT_ICON_IMAGE", appId);
		StringBuilder sBuffer = new StringBuilder();

		for (SysMenuVo sysMenuVo : sysMenuVoList) {
			if (sysMenuVo.getHasChild().booleanValue()) {
				sBuffer.append(String.format(
						"<li class='dc-mega-li'><a class='dc-mega' href='javascript:void(0)' style='display:block;' onclick='return false;'>%s</a>%s",
						new Object[]{sysMenuVo.getName(), getEachColElementForPortal(defaultImage, sysMenuVo.getId(),
								showTabIcon, iconTipsFlag, showDivIcon, currentLanguagecode, appId, menu)}));
			} else {
				String[] icons = getSystemmIconPara(sysMenuVo.getImage(), defaultImage, showTabIcon);
				sBuffer.append(String.format(
						"<li class='dc-mega-li'><a class='dc-mega' href='javascript:void(0)' style='display:block;'onclick=\"addTab('%s','%s','%s','%s','%s');return false;\">%s</a></li>",
						new Object[]{sysMenuVo.getName(), (sysMenuVo.getUrl() == null) ? "" : sysMenuVo.getUrl(),
								icons[0], (sysMenuVo.getCode() == null) ? "" : sysMenuVo.getCode(), icons[1],
								formatIconName(sysMenuVo.getName())}));
			}

		}

		return sBuffer.toString();
	}

	public String getIndexElementVlign(String currentLanguagecode, SecurityUser loginUser, SecurityMenu menu,
			String appId) {
		if (!(StringUtils.isEmpty(menu.getPortletConfigId()))) {
			menu.convertToMenuVo(currentLanguagecode);
			return getIndexElementForPortal(currentLanguagecode, menu, appId);
		}

		String showTabIcon = getProfileValueByCode("PLATFORM_V6_DISPLAY_TAB_ICON", appId);
		String iconTipsFlag = getProfileValueByCode("PLATFORM_V6_DISPLAY_ICON_TIPS", appId);
		String showDivIcon = getProfileValueByCode("PLATFORM_V6_DISPLAY_DIV_ICON", appId);

		String defaultImage = getProfileValueByCode("PLATFORM_V6_DEFAULT_ICON_IMAGE", appId);

		List rootList = getSubSysMenus("-1", appId);
		Collection sysMenuVoList = new ArrayList();
		if (rootList.size() > 0) {
			sysMenuVoList = menu.getMenusByParentId(((SysMenu) rootList.get(0)).getId());
		} else {
			log.error("根菜单id丢失");
			return "";
		}

		StringBuilder sBuffer = new StringBuilder();

		for (SysMenuVo sysMenuVo : sysMenuVoList) {
			if ("1".equals(sysMenuVo.getType())) {
				if (sysMenuVo.getHasChild().booleanValue()) {
					sBuffer.append(String
							.format("<li class='dc-mega-li'><a class='dc-mega' href='javascript:void(0)' style='display:block;' onclick='return false;'>%s</a>%s",
									new Object[]{sysMenuVo.getName(),
											getEachColElement(defaultImage, sysMenuVo.getId(), showTabIcon,
													iconTipsFlag, showDivIcon, currentLanguagecode, loginUser, appId,
													menu)}));
				} else {
					String[] icons = getSystemmIconPara(sysMenuVo.getImage(), defaultImage, showTabIcon);
					sBuffer.append(String.format(
							"<li class='dc-mega-li'><a class='dc-mega' href='javascript:void(0)' style='display:block;'onclick=\"addTab('%s','%s','%s','%s','%s');return false;\">%s</a></li>",
							new Object[]{sysMenuVo.getName(), (sysMenuVo.getUrl() == null) ? "" : sysMenuVo.getUrl(),
									icons[0], (sysMenuVo.getCode() == null) ? "" : sysMenuVo.getCode(), icons[1],
									formatIconName(sysMenuVo.getName())}));
				}

			}

		}

		return sBuffer.toString();
	}

	private String getEachColElement(String defaultImage, String parentId, String showTabIcon, String iconTipsFlag,
			String showDivIcon, String currentlanguagecode, SecurityUser security1User, String appId,
			SecurityMenu menu) {
		List list = menu.getChildColumnMenu(parentId);
		StringBuilder sBuffer = new StringBuilder();
		sBuffer.append("<ul><table border ='0' height='100%' cellpadding='0' cellspacing='0'><tr>");
		if ((list != null) && (list.size() > 0)) {
			for (int i = 0; i < list.size(); ++i) {
				if (list.size() > 1) {
					if (i == 0) {
						sBuffer.append(
								"<td valign='top' class='iconItemLeftWithoutPadding' style='border-right:solid 1px;border-right-color:#d0d0d0;'>");
					} else if (i == list.size() - 1)
						sBuffer.append(
								"<td valign='top' class='iconItemLeftWithoutPadding' style='border-left:solid 1px;border-left-color:#ffffff;'>");
					else {
						sBuffer.append(
								"<td valign='top' class='iconItemLeft' style='border-right:solid 1px;border-right-color:#d0d0d0;border-left:solid 1px;border-left-color:#ffffff;'>");
					}
				} else {
					sBuffer.append("<td valign='top' class='iconItemLeftWithoutPadding'>");
				}

				sBuffer.append("<table cellpadding='0' cellspacing='0' height='100%' width='100%'>");

				List sysMenuVoListNew = (List) list.get(i);

				for (SysMenuVo sysMenuVo : sysMenuVoListNew) {
					if ("1".equals(sysMenuVo.getType())) {
						sBuffer.append("<tr><td valign='top' height='20' width=100%>");
						if ((sysMenuVo.getHasChild() != null) && (sysMenuVo.getHasChild().booleanValue())) {
							String[] icons1 = getSystemmIconPara(sysMenuVo.getImage(), defaultImage, showTabIcon);
							sBuffer.append(String.format(
									"<li class='dc-mega-li'><a class='pop-dc-mega' href='javascript:void(0)' style='display:block;' onclick=\"addTab('%s','%s','%s','%s','%s');return false;\"%s>%s</a></li></td></tr>",
									new Object[]{sysMenuVo.getName(),
											(sysMenuVo.getUrl() == null) ? "" : sysMenuVo.getUrl(), icons1[0],
											(sysMenuVo.getCode() == null) ? "" : sysMenuVo.getCode(), icons1[1],
											setIconTitleTips(sysMenuVo, iconTipsFlag, appId), getIconImageUrl(sysMenuVo,
													defaultImage, showTabIcon, showDivIcon, appId)}));

							Collection sysMenuVoListChildSecond = menu.getMenusByParentId(sysMenuVo.getId());
							if (sysMenuVoListChildSecond != null) {
								for (SysMenuVo sysMenuVoChildSecond : sysMenuVoListChildSecond) {
									if ("1".equals(sysMenuVoChildSecond.getType())) {
										String[] icons = getSystemmIconPara(sysMenuVoChildSecond.getImage(),
												defaultImage, showTabIcon);
										sBuffer.append(String.format(
												"<tr><td valign='top' height='20' width=100%%><li><ul><a class ='subMenuStyle' href='javascript:void(0)' onclick=\"addTab('%s','%s','%s','%s','%s');return false;\"%s>%s</a></ul></li></td></tr>",
												new Object[]{sysMenuVoChildSecond.getName(),
														(sysMenuVoChildSecond.getUrl() == null)
																? ""
																: sysMenuVoChildSecond.getUrl(),
														icons[0],
														(sysMenuVoChildSecond.getCode() == null)
																? ""
																: sysMenuVoChildSecond.getCode(),
														icons[1],
														setIconTitleTips(sysMenuVoChildSecond, iconTipsFlag, appId),
														getIconImageUrl(sysMenuVoChildSecond, defaultImage, showTabIcon,
																showDivIcon, appId)}));
									}

								}

							}

						} else {
							String[] icons2 = getSystemmIconPara(sysMenuVo.getImage(), defaultImage, showTabIcon);
							sBuffer.append(String.format(
									"<li class='dc-mega-li'><a class='pop-dc-mega' href='javascript:void(0)' style='display:block;' onclick=\"addTab('%s','%s','%s','%s','%s');return false;\"%s>%s</a></li></td></tr>",
									new Object[]{sysMenuVo.getName(),
											(sysMenuVo.getUrl() == null) ? "" : sysMenuVo.getUrl(), icons2[0],
											(sysMenuVo.getCode() == null) ? "" : sysMenuVo.getCode(), icons2[1],
											setIconTitleTips(sysMenuVo, iconTipsFlag, appId), getIconImageUrl(sysMenuVo,
													defaultImage, showTabIcon, showDivIcon, appId)}));
						}

					}

				}

				sBuffer.append("<tr></tr></table></td>");
			}
		}

		sBuffer.append("</tr></table></ul></li>");
		return sBuffer.toString();
	}

	private String setIconTitleTips(SysMenuVo sysMenuVo, String iconTipsFlag, String appId) {
		if ((iconTipsFlag != null) && (Boolean.valueOf(iconTipsFlag).booleanValue())) {
			return String.format("title='%s'",
					new Object[]{setHrefTitleTips(sysMenuVo.getName(), sysMenuVo.getComments(), appId)});
		}
		return "";
	}

	public String setHrefTitleTips(String iconName, String iconDesc, String appId) {
		StringBuilder finalTitleName = new StringBuilder();
		finalTitleName.append(new StringBuilder().append("名称:").append(iconName).toString());

		if ((Boolean.valueOf(getProfileValueByCode("PLATFORM_V6_DISPLAY_ICON_DESC", appId)).booleanValue())
				&& (iconDesc != null)) {
			finalTitleName.append(new StringBuilder().append("&#13;描述:").append(iconDesc).toString());
		}
		return finalTitleName.toString();
	}

	public String getIconImageUrl(SysMenuVo sysMenuvo, String defaultIconImage, String showTabIcon, String showDivIcon,
			String appid) {
		boolean hasUrlFlag = true;
		if ((sysMenuvo.getUrl() == null) || ("".equals(sysMenuvo.getUrl()))) {
			hasUrlFlag = false;
		}
		StringBuilder sBuffer = new StringBuilder();

		if ((showDivIcon != null) && (Boolean.valueOf(showDivIcon).booleanValue())) {
			String[] icons = getSystemmIconPara(sysMenuvo.getImage(), defaultIconImage, showTabIcon);
			sBuffer.append(String.format(
					"<span class='icon' style='background-image:url(%s);background-position:%s;background-repeat:no-repeat no-repeat;display:block;float:left;with:16px;height:16px;'></span>",
					new Object[]{icons[0], icons[1]}));
			if (hasUrlFlag)
				sBuffer.append(String.format("<span style='*float:left;line-height:16px;cursor:pointer;'>%s</span>",
						new Object[]{formatIconName(sysMenuvo.getName())}));
			else
				sBuffer.append(String.format("<span style='*float:left;line-height:16px;'>%s</span>",
						new Object[]{formatIconName(sysMenuvo.getName())}));
		} else {
			if ((sysMenuvo.getHasChild() != null) && (sysMenuvo.getHasChild().booleanValue()))
				sBuffer.append(new StringBuilder().append("<img src='")
						.append(getProfileValueByCode("PLATFORM_V6_PMO_FIRST_TITLE_ICON", appid))
						.append("' border=0 style='margin-right: 3px;'>").toString());
			else {
				sBuffer.append(new StringBuilder().append("<img src='")
						.append(getProfileValueByCode("PLATFORM_V6_PMO_SECONDE_TITLE_ICON", appid))
						.append("' border=0 style='margin-right: 3px;'>").toString());
			}
			if (hasUrlFlag)
				sBuffer.append("<span style='line-height:20px;cursor:pointer;'>")
						.append(formatIconName(sysMenuvo.getName())).append("</span>");
			else {
				sBuffer.append("<span style='line-height:20px;'>").append(formatIconName(sysMenuvo.getName()))
						.append("</span>");
			}
		}
		return sBuffer.toString();
	}

	private String getProfileValueByCode(String code, String appId) {
		String value = (String) VALUE_BYCODE.get(code);

		if (value == null) {
			value = this.sysProfileAPI.getProfileValueByCodeByAppId(code, appId);
			VALUE_BYCODE.put(code, value);
		}
		return value;
	}

	public String getDefaultSysFunction(String applicationId, String currentLanguage) {
		String url = new StringBuilder().append(RestClientConfig.getRestHost("sysmenu"))
				.append("/api/platform6/sysmenu/SysMenu/getDefaultSysFunction/v1").toString();
		Muti2Bean bean = new Muti2Bean(applicationId, currentLanguage);
		ResponseMsg responseMsg = RestClient.doPost(url, bean, new GenericType() {
		});
		if (responseMsg.getRetCode().equals("200")) {
			return ((String) responseMsg.getResponseBody());
		}
		log.error(new StringBuilder().append("url:").append(url).append(",error:").append(responseMsg.getErrorDesc())
				.toString());
		return null;
	}

	public String createTab4DefaultSysFunction(String sysMenuCode, String applicationId, String currentLanguageCode) {
		String url = new StringBuilder().append(RestClientConfig.getRestHost("sysmenu"))
				.append("/api/platform6/sysmenu/SysMenu/createTab4DefaultSysFunction/v1").toString();
		Muti3Bean bean = new Muti3Bean(sysMenuCode, applicationId, currentLanguageCode);
		ResponseMsg responseMsg = RestClient.doPost(url, bean, new GenericType() {
		});
		if (responseMsg.getRetCode().equals("200")) {
			return ((String) responseMsg.getResponseBody());
		}
		log.error(new StringBuilder().append("url:").append(url).append(",error:").append(responseMsg.getErrorDesc())
				.toString());
		return null;
	}

	public String getMessagerUrl() {
		return "addTab('消息','platform/sysmessage/sysMessageController/toSysMessage','static/images/platform/sysmenu/icons.gif','messageMgr','-220px -80px');return false;";
	}

	public String getQuickSearch(Locale locale) {
		StringBuilder sBuffer = new StringBuilder();
		String showSearchDialog = "";
		try {
			showSearchDialog = getProfileValueByCode("PLATFORM_V6_DISPLAY_SEARCH", SessionHelper.getApplicationId());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		if ((showSearchDialog != null) && (Boolean.valueOf(showSearchDialog).booleanValue())) {
			sBuffer.append("<div id='smartSearch' class=''>").append("")
					.append(new StringBuilder()
							.append("<input type='text' class='search_subject' id='subjectSearch' name='subjectSearch' onfocus='onFocusEvent(this)' onblur='onBlurEvent(this)' value='")
							.append(PlatformLocalesJSTL.getBundleValue("platform.search.onFocus.tip", locale))
							.append("' style='color:rgb(160,160,160);'>").toString())
					.append("<span class='icon_search' id='searchIcon'></span></div>");
		}

		return sBuffer.toString();
	}

	public void reloadHomePageMenuCache() {
		VALUE_BYCODE.clear();
		String url = new StringBuilder().append(RestClientConfig.getRestHost("sysmenu"))
				.append("/api/platform6/sysmenu/SysMenu/reloadHomePageMenuCache/v1").toString();
		ResponseMsg responseMsg = RestClient.doGet(url, new GenericType() {
		});
		if (responseMsg.getRetCode().equals("200"))
			log.info("首页菜单缓存刷新成功");
		else
			log.error(new StringBuilder().append("url:").append(url).append(",error:")
					.append(responseMsg.getErrorDesc()).toString());
	}

	public List<SysMenuVo> getAllSubObjectInPermisson(String appId, String loginName, String userid,
			String lauguageCode, boolean flag, Set<String> permissions) {
		Muti6Bean args = new Muti6Bean(appId, loginName, userid, lauguageCode, Boolean.valueOf(flag), permissions);
		String url = new StringBuilder().append(RestClientConfig.getRestHost("sysmenu"))
				.append("/api/platform6/sysmenu/SysMenu/getAllSubObjectInPermisson/v1").toString();
		ResponseMsg responseMsg = RestClient.doPost(url, args, new GenericType() {
		});
		if (responseMsg.getRetCode().equals("200")) {
			return ((List) responseMsg.getResponseBody());
		}
		log.error(new StringBuilder().append("url:").append(url).append(",error:").append(responseMsg.getErrorDesc())
				.toString());
		return null;
	}

	public int checkSysmeueCode(String code, String menuId) {
		String checkCode = "sysmenu/api/platform6/sysmenu/SysMenu/checkmenucode/v1";
		Muti3Bean checkparam = new Muti3Bean();
		checkparam.setDto1(RestClientConfig.systemid);
		checkparam.setDto2(code);
		checkparam.setDto3(menuId);
		ResponseMsg response = RestClient.doPost(checkCode, checkparam, new GenericType() {
		});
		if (response.getRetCode().equals("200")) {
			return ((Integer) response.getResponseBody()).intValue();
		}
		log.error(new StringBuilder().append("url:").append(checkCode).append(",error:").append(response.getErrorDesc())
				.toString());
		return -3;
	}

	public String insertMenu(SysMenu sysMenu) {
		String url = new StringBuilder().append(RestClientConfig.getRestHost("sysmenu"))
				.append("/api/platform6/sysmenu/SysMenu/insertmenu/zh_CN/v1").toString();
		ResponseMsg responseMsg = RestClient.doPost(url, sysMenu, new GenericType() {
		});
		if (responseMsg.getRetCode().equals("200")) {
			return ((String) responseMsg.getResponseBody());
		}
		throw new RuntimeException(responseMsg.getErrorDesc());
	}

	public Integer insertMenus(List<SysMenu> sysMenu) {
		String url = new StringBuilder().append(RestClientConfig.getRestHost("sysmenu"))
				.append("/api/platform6/sysmenu/SysMenu/insertmenus/zh_CN/v1").toString();
		ResponseMsg responseMsg = RestClient.doPost(url, sysMenu, new GenericType() {
		});
		if (responseMsg.getRetCode().equals("200")) {
			return ((Integer) responseMsg.getResponseBody());
		}
		throw new RuntimeException(responseMsg.getErrorDesc());
	}

	public void updateMenu(SysMenu sysMenu) {
		String url = new StringBuilder().append(RestClientConfig.getRestHost("sysmenu"))
				.append("/api/platform6/sysmenu/SysMenu/updatemenu/zh_CN/v1").toString();
		ResponseMsg responseMsg = RestClient.doPost(url, sysMenu, new GenericType() {
		});
		if (responseMsg.getRetCode().equals("200")) {
			return;
		}
		throw new RuntimeException(responseMsg.getErrorDesc());
	}

	public void deleteMenu(String id) {
		String url = new StringBuilder().append(RestClientConfig.getRestHost("sysmenu"))
				.append("/api/platform6/sysmenu/SysMenu/deletemenu/v1").toString();
		ResponseMsg responseMsg = RestClient.doPost(url, new String[]{id}, new GenericType() {
		});
		if (responseMsg.getRetCode().equals("200")) {
			return;
		}
		throw new RuntimeException(responseMsg.getErrorDesc());
	}

	private String getTraditionalIndexElementVlignPortal(String languageCode, SecurityMenu menu, String appId) {
		String showTabIcon = getProfileValueByCode("PLATFORM_V6_DISPLAY_TAB_ICON", appId);
		String iconTipsFlag = getProfileValueByCode("PLATFORM_V6_DISPLAY_ICON_TIPS", appId);
		String showDivIcon = getProfileValueByCode("PLATFORM_V6_DISPLAY_DIV_ICON", appId);

		String defaultImage = getProfileValueByCode("PLATFORM_V6_DEFAULT_ICON_IMAGE", appId);

		Collection sysMenuVoList = menu.getMenusByParentId("-1");

		StringBuilder sBuffer = new StringBuilder();

		String beginClickEvent = "onclick=\"addTab(";
		String upDonote = "'";
		String seperator = ",";
		String afterClickEvent = ");return false;\"";
		String returnValue = ">";
		if (sysMenuVoList != null) {
			for (SysMenuVo sysMenuVo : sysMenuVoList) {
				if ("1".equals(sysMenuVo.getType())) {
					if (sysMenuVo.getHasChild().booleanValue()) {
						sBuffer.append(
								"<li class='firstmenu' onmouseenter='displaySubMenu(this)' onmouseleave='hideSubMenu(this)'>")
								.append("<a href='javascript:void(0);' onclick='return false;'").append(returnValue)
								.append(sysMenuVo.getName()).append("</a>");

						sBuffer.append(getChildrenElementVlign(sysMenuVo.getId(), appId, menu, beginClickEvent,
								upDonote, seperator, afterClickEvent, returnValue, defaultImage, showTabIcon,
								iconTipsFlag, showDivIcon));

						sBuffer.append("</li>");
					} else {
						sBuffer.append("<li class='firstmenu navinbg'>").append("<a href='javascript:void(0)'")
								.append(beginClickEvent).append(upDonote).append(sysMenuVo.getName()).append(upDonote)
								.append(seperator).append(upDonote)
								.append((sysMenuVo.getUrl() == null) ? "" : sysMenuVo.getUrl()).append(upDonote)
								.append(seperator).append(upDonote)
								.append(getSystemmIconPara(sysMenuVo.getImage(), defaultImage, showTabIcon)[0])
								.append(upDonote).append(seperator).append(upDonote)
								.append((sysMenuVo.getCode() == null) ? "" : sysMenuVo.getCode()).append(upDonote)
								.append(seperator).append(upDonote)
								.append(getSystemmIconPara(sysMenuVo.getImage(), defaultImage, showTabIcon)[1])
								.append(upDonote).append(afterClickEvent).append(returnValue)
								.append(formatIconName(sysMenuVo.getName())).append("</a>").append("</li>");
					}

				}

			}

		}

		return sBuffer.toString();
	}

	public String getTraditionalIndexElementVlign(String languageCode, SecurityUser sysUser, SecurityMenu menu,
			String appId) {
		if (!(StringUtils.isEmpty(menu.getPortletConfigId()))) {
			menu.convertToMenuVo(languageCode);
			return getTraditionalIndexElementVlignPortal(languageCode, menu, appId);
		}

		String showTabIcon = getProfileValueByCode("PLATFORM_V6_DISPLAY_TAB_ICON", appId);
		String iconTipsFlag = getProfileValueByCode("PLATFORM_V6_DISPLAY_ICON_TIPS", appId);
		String showDivIcon = getProfileValueByCode("PLATFORM_V6_DISPLAY_DIV_ICON", appId);

		String defaultImage = getProfileValueByCode("PLATFORM_V6_DEFAULT_ICON_IMAGE", appId);

		List rootList = getSubSysMenus("-1", appId);
		Collection sysMenuVoList = menu.getMenusByParentId(((SysMenu) rootList.get(0)).getId());

		StringBuilder sBuffer = new StringBuilder();

		String beginClickEvent = "onclick=\"addTab(";
		String upDonote = "'";
		String seperator = ",";
		String afterClickEvent = ");return false;\"";
		String returnValue = ">";
		if (sysMenuVoList != null) {
			for (SysMenuVo sysMenuVo : sysMenuVoList) {
				if ("1".equals(sysMenuVo.getType())) {
					if (sysMenuVo.getHasChild().booleanValue()) {
						sBuffer.append(
								"<li class='firstmenu' onmouseenter='displaySubMenu(this)' onmouseleave='hideSubMenu(this)'>")
								.append("<a href='javascript:void(0);' onclick='return false;'").append(returnValue)
								.append(sysMenuVo.getName()).append("</a>");

						sBuffer.append(getChildrenElementVlign(sysMenuVo.getId(), appId, menu, beginClickEvent,
								upDonote, seperator, afterClickEvent, returnValue, defaultImage, showTabIcon,
								iconTipsFlag, showDivIcon));

						sBuffer.append("</li>");
					} else {
						sBuffer.append("<li class='firstmenu navinbg'>").append("<a href='javascript:void(0)'")
								.append(beginClickEvent).append(upDonote).append(sysMenuVo.getName()).append(upDonote)
								.append(seperator).append(upDonote)
								.append((sysMenuVo.getUrl() == null) ? "" : sysMenuVo.getUrl()).append(upDonote)
								.append(seperator).append(upDonote)
								.append(getSystemmIconPara(sysMenuVo.getImage(), defaultImage, showTabIcon)[0])
								.append(upDonote).append(seperator).append(upDonote)
								.append((sysMenuVo.getCode() == null) ? "" : sysMenuVo.getCode()).append(upDonote)
								.append(seperator).append(upDonote)
								.append(getSystemmIconPara(sysMenuVo.getImage(), defaultImage, showTabIcon)[1])
								.append(upDonote).append(afterClickEvent).append(returnValue)
								.append(formatIconName(sysMenuVo.getName())).append("</a>").append("</li>");
					}

				}

			}

		}

		return sBuffer.toString();
	}

	private String getChildrenElementVlign(String parentId, String appId, SecurityMenu menu, String beginClickEvent,
			String upDonote, String seperator, String afterClickEvent, String returnValue, String defaultImage,
			String showTabIcon, String iconTipsFlag, String showDivIcon) {
		List list = menu.getMenusByParentId(parentId);

		StringBuilder sBuffer = new StringBuilder();
		if (!(list.isEmpty())) {
			sBuffer.append("<ul class='sub'><iframe class='platform6BackgroundIframe'></iframe>");
			for (int i = 0; i < list.size(); ++i) {
				SysMenuVo sysMenuVo = (SysMenuVo) list.get(i);

				if (!("1".equals(sysMenuVo.getType())))
					continue;
				if (sysMenuVo.getHasChild().booleanValue()) {
					sBuffer.append("<li onmouseenter='displaySubMenu(this)' onmouseleave='hideSubMenu(this)'>")
							.append("<a href='javascript:void(0);' class='navsanji'").append(beginClickEvent)
							.append(upDonote).append(sysMenuVo.getName()).append(upDonote).append(seperator)
							.append(upDonote).append((sysMenuVo.getUrl() == null) ? "" : sysMenuVo.getUrl())
							.append(upDonote).append(seperator).append(upDonote)
							.append(getSystemmIconPara(sysMenuVo.getImage(), defaultImage, showTabIcon)[0])
							.append(upDonote).append(seperator).append(upDonote)
							.append((sysMenuVo.getCode() == null) ? "" : sysMenuVo.getCode()).append(upDonote)
							.append(seperator).append(upDonote)
							.append(getSystemmIconPara(sysMenuVo.getImage(), defaultImage, showTabIcon)[1])
							.append(upDonote).append(afterClickEvent).append(returnValue)
							.append(getIconImageUrl(sysMenuVo, defaultImage, showTabIcon, showDivIcon, appId))
							.append("</a>");

					sBuffer.append(getChildrenElementVlign(sysMenuVo.getId(), appId, menu, beginClickEvent, upDonote,
							seperator, afterClickEvent, returnValue, defaultImage, showTabIcon, iconTipsFlag,
							showDivIcon));

					sBuffer.append("</li>");
				} else {
					sBuffer.append("<li><a href='javascript:void(0);'").append(beginClickEvent).append(upDonote)
							.append(sysMenuVo.getName()).append(upDonote).append(seperator).append(upDonote)
							.append((sysMenuVo.getUrl() == null) ? "" : sysMenuVo.getUrl()).append(upDonote)
							.append(seperator).append(upDonote)
							.append(getSystemmIconPara(sysMenuVo.getImage(), defaultImage, showTabIcon)[0])
							.append(upDonote).append(seperator).append(upDonote)
							.append((sysMenuVo.getCode() == null) ? "" : sysMenuVo.getCode()).append(upDonote)
							.append(seperator).append(upDonote)
							.append(getSystemmIconPara(sysMenuVo.getImage(), defaultImage, showTabIcon)[1])
							.append(upDonote).append(afterClickEvent).append(returnValue)
							.append(getIconImageUrl(sysMenuVo, defaultImage, showTabIcon, showDivIcon, appId))
							.append("</a>").append("</li>");
				}

			}

			sBuffer.append("</ul>");
		}
		return sBuffer.toString();
	}

	public boolean checkSysMenuUrl(String url) {
		String resturl = new StringBuilder().append(RestClientConfig.getRestHost("sysmenu"))
				.append("/api/platform6/sysmenu/SysMenu/checkSysMenuUrl/v1").toString();
		ResponseMsg responseMsg = RestClient.doPost(resturl, url, new GenericType() {
		});
		if (responseMsg.getRetCode().equals("200")) {
			return ((Boolean) responseMsg.getResponseBody()).booleanValue();
		}
		log.error(new StringBuilder().append("url:").append(resturl).append(",error:")
				.append(responseMsg.getErrorDesc()).toString());
		return true;
	}

	private String getLeftTreeMenusForPortal(String languageCode, SecurityMenu menu, String appId) {
		String showTabIcon = getProfileValueByCode("PLATFORM_V6_DISPLAY_TAB_ICON", appId);
		String iconTipsFlag = getProfileValueByCode("PLATFORM_V6_DISPLAY_ICON_TIPS", appId);
		String showDivIcon = getProfileValueByCode("PLATFORM_V6_DISPLAY_DIV_ICON", appId);

		String defaultImage = getProfileValueByCode("PLATFORM_V6_DEFAULT_ICON_IMAGE", appId);

		Collection sysMenuVoList = menu.getMenusByParentId("-1");

		StringBuilder sBuffer = new StringBuilder();

		String beginClickEvent = "onclick=\"addTab(";
		String upDonote = "'";
		String seperator = ",";
		String afterClickEvent = ");return false;\"";
		String returnValue = ">";

		if (sysMenuVoList != null) {
			for (SysMenuVo sysMenuVo : sysMenuVoList) {
				if (sysMenuVo.getHasChild().booleanValue()) {
					sBuffer.append("<li class='firstmenu'>").append("<div onclick='showSubMenu(this)'>")
							.append("<a href='javascript:void(0);' onclick='return false;'").append(returnValue)
							.append(getLeftTreeIconImageUrl(sysMenuVo, defaultImage, showTabIcon, showDivIcon, appId))
							.append("</a>").append("</div>");

					sBuffer.append(getLeftTreeMenuChildrens(sysMenuVo.getId(), appId, menu, beginClickEvent, upDonote,
							seperator, afterClickEvent, returnValue, defaultImage, showTabIcon, iconTipsFlag,
							showDivIcon, 0));

					sBuffer.append("</li>");
				} else {
					sBuffer.append("<li class='firstmenu navinbg'>").append("<div>")
							.append("<a href='javascript:void(0)'").append(beginClickEvent).append(upDonote)
							.append(sysMenuVo.getName()).append(upDonote).append(seperator).append(upDonote)
							.append((sysMenuVo.getUrl() == null) ? "" : sysMenuVo.getUrl()).append(upDonote)
							.append(seperator).append(upDonote)
							.append(getSystemmIconPara(sysMenuVo.getImage(), defaultImage, showTabIcon)[0])
							.append(upDonote).append(seperator).append(upDonote)
							.append((sysMenuVo.getCode() == null) ? "" : sysMenuVo.getCode()).append(upDonote)
							.append(seperator).append(upDonote)
							.append(getSystemmIconPara(sysMenuVo.getImage(), defaultImage, showTabIcon)[1])
							.append(upDonote).append(afterClickEvent).append(returnValue)
							.append(getLeftTreeIconImageUrl(sysMenuVo, defaultImage, showTabIcon, showDivIcon, appId))
							.append("</a></div></li>");
				}

			}

		}

		return sBuffer.toString();
	}

	public String getLeftTreeMenus(String languageCode, SecurityUser sysUser, SecurityMenu menu, String appId) {
		if (!(StringUtils.isEmpty(menu.getPortletConfigId()))) {
			menu.convertToMenuVo(languageCode);
			return getLeftTreeMenusForPortal(languageCode, menu, appId);
		}

		String showTabIcon = getProfileValueByCode("PLATFORM_V6_DISPLAY_TAB_ICON", appId);
		String iconTipsFlag = getProfileValueByCode("PLATFORM_V6_DISPLAY_ICON_TIPS", appId);
		String showDivIcon = getProfileValueByCode("PLATFORM_V6_DISPLAY_DIV_ICON", appId);

		String defaultImage = getProfileValueByCode("PLATFORM_V6_DEFAULT_ICON_IMAGE", appId);

		List rootList = getSubSysMenus("-1", appId);
		Collection sysMenuVoList = menu.getMenusByParentId(((SysMenu) rootList.get(0)).getId());

		StringBuilder sBuffer = new StringBuilder();

		String beginClickEvent = "onclick=\"addTab(";
		String upDonote = "'";
		String seperator = ",";
		String afterClickEvent = ");return false;\"";
		String returnValue = ">";

		if (sysMenuVoList != null) {
			for (SysMenuVo sysMenuVo : sysMenuVoList) {
				if ("1".equals(sysMenuVo.getType())) {
					if (sysMenuVo.getHasChild().booleanValue()) {
						sBuffer.append("<li class='firstmenu'>").append("<div onclick='showSubMenu(this)'>")
								.append("<a href='javascript:void(0);' onclick='return false;'").append(returnValue)
								.append(getLeftTreeIconImageUrl(sysMenuVo, defaultImage, showTabIcon, showDivIcon,
										appId))
								.append("</a>").append("</div>");

						sBuffer.append(getLeftTreeMenuChildrens(sysMenuVo.getId(), appId, menu, beginClickEvent,
								upDonote, seperator, afterClickEvent, returnValue, defaultImage, showTabIcon,
								iconTipsFlag, showDivIcon, 0));

						sBuffer.append("</li>");
					} else {
						sBuffer.append("<li class='firstmenu navinbg'>").append("<div>")
								.append("<a href='javascript:void(0)'").append(beginClickEvent).append(upDonote)
								.append(sysMenuVo.getName()).append(upDonote).append(seperator).append(upDonote)
								.append((sysMenuVo.getUrl() == null) ? "" : sysMenuVo.getUrl()).append(upDonote)
								.append(seperator).append(upDonote)
								.append(getSystemmIconPara(sysMenuVo.getImage(), defaultImage, showTabIcon)[0])
								.append(upDonote).append(seperator).append(upDonote)
								.append((sysMenuVo.getCode() == null) ? "" : sysMenuVo.getCode()).append(upDonote)
								.append(seperator).append(upDonote)
								.append(getSystemmIconPara(sysMenuVo.getImage(), defaultImage, showTabIcon)[1])
								.append(upDonote).append(afterClickEvent).append(returnValue)
								.append(getLeftTreeIconImageUrl(sysMenuVo, defaultImage, showTabIcon, showDivIcon,
										appId))
								.append("</a></div></li>");
					}

				}

			}

		}

		return sBuffer.toString();
	}

	private String getLeftTreeMenuChildrens(String parentId, String appId, SecurityMenu menu, String beginClickEvent,
			String upDonote, String seperator, String afterClickEvent, String returnValue, String defaultImage,
			String showTabIcon, String iconTipsFlag, String showDivIcon, int index) {
		List list = menu.getMenusByParentId(parentId);

		StringBuilder sBuffer = new StringBuilder();
		if (!(list.isEmpty())) {
			sBuffer.append("<ul>");
			for (int i = 0; i < list.size(); ++i) {
				SysMenuVo sysMenuVo = (SysMenuVo) list.get(i);

				if (!("1".equals(sysMenuVo.getType())))
					continue;
				StringBuilder span = new StringBuilder();
				for (int k = 0; k < index; ++k) {
					span.append("<span class='menu-indent'></span>");
				}

				if (sysMenuVo.getHasChild().booleanValue()) {
					sBuffer.append("<li>")
							.append("<span onclick='showSubMenu(this)' onmouseover='selectMenu(this)' onmouseout='unSelectMenu(this)'>")
							.append(span).append("<a href='javascript:void(0);' class='navsanji' ")
							.append(beginClickEvent).append(upDonote).append(sysMenuVo.getName()).append(upDonote)
							.append(seperator).append(upDonote)
							.append((sysMenuVo.getUrl() == null) ? "" : sysMenuVo.getUrl()).append(upDonote)
							.append(seperator).append(upDonote)
							.append(getSystemmIconPara(sysMenuVo.getImage(), defaultImage, showTabIcon)[0])
							.append(upDonote).append(seperator).append(upDonote)
							.append((sysMenuVo.getCode() == null) ? "" : sysMenuVo.getCode()).append(upDonote)
							.append(seperator).append(upDonote)
							.append(getSystemmIconPara(sysMenuVo.getImage(), defaultImage, showTabIcon)[1])
							.append(upDonote).append(afterClickEvent).append(returnValue)
							.append(getLeftTreeIconImageUrl(sysMenuVo, defaultImage, showTabIcon, showDivIcon, appId))
							.append("</a></span>");

					sBuffer.append(getLeftTreeMenuChildrens(sysMenuVo.getId(), appId, menu, beginClickEvent, upDonote,
							seperator, afterClickEvent, returnValue, defaultImage, showTabIcon, iconTipsFlag,
							showDivIcon, index + 1));

					sBuffer.append("</li>");
				} else {
					sBuffer.append("<li>")
							.append("<span onclick='showSubMenu(this)' onmouseover='selectMenu(this)' onmouseout='unSelectMenu(this)'>")
							.append(span).append("<a href='javascript:void(0);' ").append(beginClickEvent)
							.append(upDonote).append(sysMenuVo.getName()).append(upDonote).append(seperator)
							.append(upDonote).append((sysMenuVo.getUrl() == null) ? "" : sysMenuVo.getUrl())
							.append(upDonote).append(seperator).append(upDonote)
							.append(getSystemmIconPara(sysMenuVo.getImage(), defaultImage, showTabIcon)[0])
							.append(upDonote).append(seperator).append(upDonote)
							.append((sysMenuVo.getCode() == null) ? "" : sysMenuVo.getCode()).append(upDonote)
							.append(seperator).append(upDonote)
							.append(getSystemmIconPara(sysMenuVo.getImage(), defaultImage, showTabIcon)[1])
							.append(upDonote).append(afterClickEvent).append(returnValue)
							.append(getLeftTreeIconImageUrl(sysMenuVo, defaultImage, showTabIcon, showDivIcon, appId))
							.append("</a></span></li>");
				}

			}

			sBuffer.append("</ul>");
		}
		return sBuffer.toString();
	}

	public String getLeftTreeIconImageUrl(SysMenuVo sysMenuvo, String defaultIconImage, String showTabIcon,
			String showDivIcon, String appId) {
		boolean hasUrlFlag = true;

		if ((sysMenuvo.getUrl() == null) || ("".equals(sysMenuvo.getUrl()))) {
			hasUrlFlag = false;
		}
		StringBuilder sBuffer = new StringBuilder();
		try {
			if ((showDivIcon != null) && (Boolean.valueOf(showDivIcon).booleanValue())) {
				sBuffer.append(new StringBuilder().append("<span class='icon' style='background-position: ")
						.append(getLeftTreeIconPara(sysMenuvo.getImage(), defaultIconImage, showTabIcon))
						.append(";'></span>").toString());
				if (hasUrlFlag)
					sBuffer.append("<span>").append(formatIconName(sysMenuvo.getName())).append("</span>");
				else
					sBuffer.append("<span>").append(formatIconName(sysMenuvo.getName())).append("</span>");
			} else {
				if ((sysMenuvo.getHasChild() != null) && (sysMenuvo.getHasChild().booleanValue()))
					sBuffer.append(new StringBuilder().append("<img src='")
							.append(getProfileValueByCode("PLATFORM_V6_PMO_FIRST_TITLE_ICON", appId))
							.append("' border=0 style='margin-right:3px;'>").toString());
				else {
					sBuffer.append(new StringBuilder().append("<img src='")
							.append(getProfileValueByCode("PLATFORM_V6_PMO_SECONDE_TITLE_ICON", appId))
							.append("' border=0 style='margin-right:3px;'>").toString());
				}
				if (hasUrlFlag)
					sBuffer.append("<span style='line-height:20px;cursor:pointer;'>")
							.append(formatIconName(sysMenuvo.getName())).append("</span>");
				else
					sBuffer.append("<span style='line-height:20px;'>").append(formatIconName(sysMenuvo.getName()))
							.append("</span>");
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return sBuffer.toString();
	}

	private String getLeftTreeIconPara(String iconImage, String defaultIconImage, String showTabIcon) {
		String paras = "";
		try {
			if ((showTabIcon != null) && (Boolean.valueOf(showTabIcon).booleanValue())) {
				if (iconImage == null) {
					paras = "-160px -80px";
				} else {
					String[] imageUrl = StringUtils.split(iconImage, ' ');
					if (imageUrl.length == 3)
						paras = new StringBuilder().append(imageUrl[1]).append(" ").append(imageUrl[2]).toString();
					else
						paras = "-160px -80px";
				}
			} else
				paras = "-160px -80px";
		} catch (Exception e) {
		}
		return paras;
	}

	public String getSysMenuNameById(String sysMenuId, String languageCode) {
		SysMenuTl tl = getSysMenusTlById(sysMenuId, languageCode);
		if (tl != null) {
			return tl.getName();
		}
		return "";
	}
}