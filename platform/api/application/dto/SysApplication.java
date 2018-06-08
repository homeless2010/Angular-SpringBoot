package com.piedpiper.platform.api.application.dto;

import com.piedpiper.platform.core.domain.BeanDTO;
import com.piedpiper.platform.core.properties.PlatformConstant;
import com.piedpiper.platform.core.properties.PlatformConstant.LogType;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheBean;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SysApplication extends BeanDTO implements Serializable, BaseCacheBean, Comparable<SysApplication> {
	public static final String APPLICATION = "PLATFORM6_APPLICATION";
	private static final long serialVersionUID = 1L;
	private String id;
	private String applicationName;
	private String applicationCode;
	private String basepath;
	private String runState;
	private String description;
	private long orderBy;
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

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getApplicationName() {
		return this.applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getApplicationCode() {
		return this.applicationCode;
	}

	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}

	public String getBasepath() {
		return this.basepath;
	}

	public void setBasepath(String basepath) {
		this.basepath = basepath;
	}

	public long getOrderBy() {
		return this.orderBy;
	}

	public void setOrderBy(long orderBy) {
		this.orderBy = orderBy;
	}

	public String getRunState() {
		return this.runState;
	}

	public void setRunState(String runState) {
		this.runState = runState;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getLogFormName() {
		return null;
	}

	public String getLogTitle() {
		return null;
	}

	public PlatformConstant.LogType getLogType() {
		return null;
	}

	public Map<String, ?> returnCacheKey() {
		Map<String, Object> map = new HashMap();
		map.put("PLATFORM6_APPLICATION", this.id);
		return map;
	}

	public String prefix() {
		return "PLATFORM6_APPLICATION";
	}

	public String returnValidFlag() {
		return this.runState;
	}

	public String returnId() {
		return this.id;
	}

	public String returnAppId() {
		return null;
	}

	public int compareTo(SysApplication o) {
		Long t = Long.valueOf(this.orderBy);
		return t.compareTo(Long.valueOf(o.getOrderBy()));
	}
}
