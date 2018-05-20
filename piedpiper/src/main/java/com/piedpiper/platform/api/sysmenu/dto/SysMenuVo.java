package com.piedpiper.platform.api.sysmenu.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class SysMenuVo implements Serializable, Comparable<SysMenuVo> {
	private static final long serialVersionUID = 7572600918303365122L;
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
	private Boolean hasChild;
	private String sysMenuTlId;
	private String sysLanguageCode;
	private String name;
	private String comments;
	private String isRobbin;
	private String menuView;
	private String menuGroup;

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
		return this.css;
	}

	public void setCss(String css) {
		this.css = css;
	}

	public String getHeight() {
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

	public String getSysMenuTlId() {
		return this.sysMenuTlId;
	}

	public void setSysMenuTlId(String sysMenuTlId) {
		this.sysMenuTlId = sysMenuTlId;
	}

	public String getSysLanguageCode() {
		return this.sysLanguageCode;
	}

	public void setSysLanguageCode(String sysLanguageCode) {
		this.sysLanguageCode = sysLanguageCode;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Boolean getHasChild() {
		return this.hasChild;
	}

	public void setHasChild(Boolean hasChild) {
		this.hasChild = hasChild;
	}

	public String getMenuGroup() {
		return this.menuGroup;
	}

	public void setMenuGroup(String columnIndex) {
		this.menuGroup = columnIndex;
	}

	public int compareTo(SysMenuVo o) {
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
}
