package com.piedpiper.platform.api.portal.dto;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Id;

import com.piedpiper.platform.core.annotation.log.FieldRemark;
import com.piedpiper.platform.core.annotation.log.LogField;
import com.piedpiper.platform.core.annotation.log.PojoRemark;
import com.piedpiper.platform.core.domain.BeanDTO;
import com.piedpiper.platform.core.properties.PlatformConstant;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheBean;

@PojoRemark(table = "sys_portlet_menu", object = "SysPortletMenuDTO", name = "12")
public class SysPortalMenu extends BeanDTO implements BaseCacheBean {
	private static final long serialVersionUID = -2807281216526675808L;
	@Id
	@LogField
	@FieldRemark(column = "id", field = "id", name = "ID")
	private String id;
	@LogField
	@FieldRemark(column = "portlet_config_id", field = "portletConfigId", name = "PORTLET_CONFIG_ID")
	private String portletConfigId;
	@LogField
	@FieldRemark(column = "menu_id", field = "menuId", name = "菜单id")
	private String menuId;
	@LogField
	@FieldRemark(column = "PARENT_ID", field = "parentId", name = "父id")
	private String parentId;
	@FieldRemark(column = "TEXT", field = "text", name = "菜单名称")
	private String text;
	@LogField
	@FieldRemark(column = "BEFORE_ID", field = "beforeId", name = "上级id")
	private String beforeId;
	@LogField
	@FieldRemark(column = "AFTER_ID", field = "afterId", name = "下级id")
	private String afterId;
	public static final String PORTLETMENU = "PLATFORM6_PORTLETMENU";
	public static final String PORTLETMENU_CONFIG = "PLATFORM6_PORTLETMENU_CONFIG_";
	public static final String PORTLETMENU_CONFIG_PARENT = "PLATFORM6_PORTLETMENU_CONFIG_PARENT_";

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getPortletConfigId() {
		return this.portletConfigId;
	}

	public void setPortletConfigId(String portletConfigId) {
		this.portletConfigId = portletConfigId;
	}

	public String getMenuId() {
		return this.menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getBeforeId() {
		return this.beforeId;
	}

	public void setBeforeId(String beforeId) {
		this.beforeId = beforeId;
	}

	public String getAfterId() {
		return this.afterId;
	}

	public void setAfterId(String afterId) {
		this.afterId = afterId;
	}

	public String getLogFormName() {
		if ((this.logFormName == null) || (this.logFormName.equals(""))) {
			return "12";
		}
		return this.logFormName;
	}

	public String getLogTitle() {
		if ((this.logTitle == null) || (this.logTitle.equals(""))) {
			return "12";
		}
		return this.logTitle;
	}

	public PlatformConstant.LogType getLogType() {
		if ((this.logType == null) || (this.logType.equals(""))) {
			return PlatformConstant.LogType.module_operate;
		}
		return this.logType;
	}

	public String returnId() {
		return this.id;
	}

	public String returnValidFlag() {
		return null;
	}

	public Map<String, ?> returnCacheKey() {
		Map<String, Object> map = new HashMap();
		map.put("PLATFORM6_PORTLETMENU", this.id);
		map.put("PLATFORM6_PORTLETMENU_CONFIG_" + getPortletConfigId(), this.id);
		map.put("PLATFORM6_PORTLETMENU_CONFIG_PARENT_" + getPortletConfigId() + "_" + getParentId(), this.id);
		return map;
	}

	public String prefix() {
		return "PLATFORM6_PORTLETMENU";
	}

	public String returnAppId() {
		return null;
	}
}
