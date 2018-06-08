package com.piedpiper.platform.api.sysmenu.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.piedpiper.platform.core.domain.BeanDTO;
import com.piedpiper.platform.core.properties.PlatformConstant;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheBean;

public class SysMenu extends BeanDTO implements Serializable, BaseCacheBean, Comparable<SysMenu> {
	private static final long serialVersionUID = 204609595498037684L;
	public static final String MENU = "PLATFORM6_MENU";
	public static final String MENU_MENU = "PLATFORM6_MENU_MENU_";
	public static final String MENU_CODE = "PLATFORM6_MENU_CODE";
	public static final String MENU_ROOT_PID = "-1";
	private String id;
	private String sysApplicationId;
	private String code;
	private String image;
	private String url;
	private String parentId;
	private BigDecimal orderBy;
	private String type;
	private String css;
	private String height;
	private String scroll;
	private String refresh;
	private String isclose;
	private String status;
	private String menuGroup;
	private String attribute01;
	private String attribute02;
	private String attribute03;
	private String attribute04;
	private String attribute05;
	private String attribute06;
	private String attribute07;
	private String attribute08;
	private String attribute09;
	private String attribute10;
	private String menuName;
	private String sysMenuTlId;
	private String languageCode;
	private String comments;
	private String searchContext;
	private String moreurl;
	private Boolean hasChild;
	private String isShowTitle;
	private String isDrag;
	private String isMenu;
	private String orgIdentity;
	private String tlId;
	private String name;
	private String isRobbin;
	private String menuView;

	@JsonIgnore
	public String getOrgIdentity() {
		return this.orgIdentity;
	}

	public void setOrgIdentity(String orgIdentity) {
		this.orgIdentity = orgIdentity;
	}

	public String getIsMenu() {
		return this.isMenu;
	}

	public void setIsMenu(String isMenu) {
		this.isMenu = isMenu;
	}

	public String getMenuView() {
		return this.menuView;
	}

	public void setMenuView(String menuView) {
		this.menuView = menuView;
	}

	public String getIsRobbin() {
		return this.isRobbin;
	}

	public void setIsRobbin(String isRobbin) {
		this.isRobbin = isRobbin;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTlId() {
		return this.tlId;
	}

	public void setTlId(String tlId) {
		this.tlId = tlId;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSysApplicationId() {
		return this.sysApplicationId;
	}

	public void setSysApplicationId(String sysApplicationId) {
		this.sysApplicationId = sysApplicationId;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public BigDecimal getOrderBy() {
		return this.orderBy;
	}

	public void setOrderBy(BigDecimal orderBy) {
		this.orderBy = orderBy;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCss() {
		this.css = (this.css == null ? "" : this.css);
		return this.css;
	}

	public void setCss(String css) {
		this.css = css;
	}

	public String getHeight() {
		this.height = (this.height == null ? "" : this.height);
		return this.height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getScroll() {
		return this.scroll;
	}

	public void setScroll(String scroll) {
		this.scroll = scroll;
	}

	public String getRefresh() {
		this.refresh = (this.refresh == null ? "" : this.refresh);
		return this.refresh;
	}

	public void setRefresh(String refresh) {
		this.refresh = refresh;
	}

	public String getIsclose() {
		return this.isclose;
	}

	public void setIsclose(String isclose) {
		this.isclose = isclose;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMenuGroup() {
		return this.menuGroup;
	}

	public void setMenuGroup(String menuGroup) {
		this.menuGroup = menuGroup;
	}

	public String getAttribute01() {
		return this.attribute01;
	}

	public void setAttribute01(String attribute01) {
		this.attribute01 = attribute01;
	}

	public String getAttribute02() {
		return this.attribute02;
	}

	public void setAttribute02(String attribute02) {
		this.attribute02 = attribute02;
	}

	public String getAttribute03() {
		return this.attribute03;
	}

	public void setAttribute03(String attribute03) {
		this.attribute03 = attribute03;
	}

	public String getAttribute04() {
		return this.attribute04;
	}

	public void setAttribute04(String attribute04) {
		this.attribute04 = attribute04;
	}

	public String getAttribute05() {
		return this.attribute05;
	}

	public void setAttribute05(String attribute05) {
		this.attribute05 = attribute05;
	}

	public String getAttribute06() {
		return this.attribute06;
	}

	public void setAttribute06(String attribute06) {
		this.attribute06 = attribute06;
	}

	public String getAttribute07() {
		return this.attribute07;
	}

	public void setAttribute07(String attribute07) {
		this.attribute07 = attribute07;
	}

	public String getAttribute08() {
		return this.attribute08;
	}

	public void setAttribute08(String attribute08) {
		this.attribute08 = attribute08;
	}

	public String getAttribute09() {
		return this.attribute09;
	}

	public void setAttribute09(String attribute09) {
		this.attribute09 = attribute09;
	}

	public String getAttribute10() {
		return this.attribute10;
	}

	public void setAttribute10(String attribute10) {
		this.attribute10 = attribute10;
	}

	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getSysMenuTlId() {
		return this.sysMenuTlId;
	}

	public void setSysMenuTlId(String sysMenuTlId) {
		this.sysMenuTlId = sysMenuTlId;
	}

	public String getLanguageCode() {
		return this.languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String Comments) {
		this.comments = Comments;
	}

	public String getSearchContext() {
		return this.searchContext;
	}

	public void setSearchContext(String searchContext) {
		this.searchContext = searchContext;
	}

	public String getIsShowTitle() {
		if (this.isShowTitle == null) {
			this.isShowTitle = "1";
		}
		return this.isShowTitle;
	}

	public void setIsShowTitle(String isShowTitle) {
		this.isShowTitle = isShowTitle;
	}

	public String getIsDrag() {
		if (this.isDrag == null) {
			this.isDrag = "1";
		}
		return this.isDrag;
	}

	public void setIsDrag(String isDrag) {
		this.isDrag = isDrag;
	}

	public String getMoreurl() {
		return this.moreurl;
	}

	public void setMoreurl(String moreurl) {
		this.moreurl = moreurl;
	}

	public String getLogFormName() {
		return null;
	}

	public String getLogTitle() {
		return null;
	}

	public PlatformConstant.LogType getLogType() {
		return null;
	}

	public String returnId() {
		return this.id;
	}

	public String returnValidFlag() {
		return null;
	}

	public Map<String, ?> returnCacheKey() {
		Map<String, Object> map = new HashMap();
		map.put("PLATFORM6_MENU", this.id);
		map.put("PLATFORM6_MENU_MENU_" + this.parentId, this.id);
		map.put("PLATFORM6_MENU_CODE", this.code);
		return map;
	}

	public String prefix() {
		return "PLATFORM6_MENU";
	}

	public String returnAppId() {
		return this.sysApplicationId;
	}

	public int compareTo(SysMenu o) {
		try {
			if (this.orderBy == null) {
				this.orderBy = BigDecimal.valueOf(2147483647L);
			}
			if ((o != null) && (o.getOrderBy() == null)) {
				o.setOrderBy(BigDecimal.valueOf(2147483647L));
			}
			return this.orderBy.subtract(o.getOrderBy()).intValue();
		} catch (Exception e) {
		}
		return 0;
	}

	public Boolean getHasChild() {
		return this.hasChild;
	}

	public void setHasChild(Boolean hasChild) {
		this.hasChild = hasChild;
	}

	public String returnLogAppId() {
		return this.sysApplicationId;
	}
}
