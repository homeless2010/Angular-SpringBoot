package com.piedpiper.platform.api.portal.dto;

import java.io.Serializable;

import com.piedpiper.platform.core.domain.BeanDTO;
import com.piedpiper.platform.core.properties.PlatformConstant;

public class PortletModel extends BeanDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String portletId;
	private String layoutId;
	private String rowId;
	private String xy;
	private String url;
	private String moreUrl;
	private String height;
	private String width;
	private String title;
	private String portletIcon;
	private String isShowTitle;
	private String isDrag;
	private String isDisplayScroll;
	private String isClose;
	private String refreshTime;

	public String getPortletId() {
		return this.portletId;
	}

	public void setPortletId(String portletId) {
		this.portletId = portletId;
	}

	public String getLayoutId() {
		return this.layoutId;
	}

	public void setLayoutId(String layoutId) {
		this.layoutId = layoutId;
	}

	public String getRowId() {
		return this.rowId;
	}

	public void setRowId(String rowId) {
		this.rowId = rowId;
	}

	public String getXy() {
		return this.xy;
	}

	public void setXy(String xy) {
		this.xy = xy;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMoreUrl() {
		return this.moreUrl;
	}

	public void setMoreUrl(String moreUrl) {
		this.moreUrl = moreUrl;
	}

	public String getHeight() {
		return this.height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWidth() {
		return this.width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPortletIcon() {
		return this.portletIcon;
	}

	public void setPortletIcon(String portletIcon) {
		this.portletIcon = portletIcon;
	}

	public String getIsShowTitle() {
		return this.isShowTitle;
	}

	public void setIsShowTitle(String isShowTitle) {
		this.isShowTitle = isShowTitle;
	}

	public String getIsDrag() {
		return this.isDrag;
	}

	public void setIsDrag(String isDrag) {
		this.isDrag = isDrag;
	}

	public String getIsDisplayScroll() {
		return this.isDisplayScroll;
	}

	public void setIsDisplayScroll(String isDisplayScroll) {
		this.isDisplayScroll = isDisplayScroll;
	}

	public String getIsClose() {
		return this.isClose;
	}

	public void setIsClose(String isClose) {
		this.isClose = isClose;
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

	public String getRefreshTime() {
		return this.refreshTime;
	}

	public void setRefreshTime(String refreshTime) {
		this.refreshTime = refreshTime;
	}
}
