package com.piedpiper.platform.api.sysaccesscontrol.dto;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.piedpiper.platform.core.domain.BeanDTO;
import com.piedpiper.platform.core.properties.PlatformConstant;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheBean;

public class SysAccesscontrol extends BeanDTO implements BaseCacheBean {
	public static final String ACCESSCONTROL = "PLATFORM6_ACCESSCONTROL";
	public static final String ACCESSCONTROL_TYPE_ID = "PLATFORM6_ACCESSCONTROL_TYPE_ID_";
	private static final long serialVersionUID = -1152662123325805597L;
	private Long accessibility;
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
	private String id;
	private Long operability;
	private String resoureId;
	private String sysApplicationId;
	private String targetId;
	private String targetType;
	private String orgIdentity;

	@JsonIgnore
	public String getOrgIdentity() {
		return this.orgIdentity;
	}

	public void setOrgIdentity(String orgIdentity) {
		this.orgIdentity = orgIdentity;
	}

	public SysAccesscontrol() {
	}

	public SysAccesscontrol(String targetType, String targetId, String resoureId, Long accessibility, Long operability,
			String sysApplicationId, Date lastUpdateDate, String lastUpdatedBy, Date creationDate, String createdBy,
			String lastUpdateIp) {
		this.targetType = targetType;
		this.targetId = targetId;
		this.resoureId = resoureId;
		this.accessibility = accessibility;
		this.operability = operability;
		this.sysApplicationId = sysApplicationId;
	}

	public SysAccesscontrol(String targetType, String targetId, String resoureId, Long accessibility, Long operability,
			String sysApplicationId, Date lastUpdateDate, String lastUpdatedBy, Date creationDate, String createdBy,
			String lastUpdateIp, String attribute01, String attribute02, String attribute03, String attribute04,
			String attribute05, String attribute06, String attribute07, String attribute08, String attribute09,
			String attribute10) {
		this.targetType = targetType;
		this.targetId = targetId;
		this.resoureId = resoureId;
		this.accessibility = accessibility;
		this.operability = operability;
		this.sysApplicationId = sysApplicationId;
		this.attribute01 = attribute01;
		this.attribute02 = attribute02;
		this.attribute03 = attribute03;
		this.attribute04 = attribute04;
		this.attribute05 = attribute05;
		this.attribute06 = attribute06;
		this.attribute07 = attribute07;
		this.attribute08 = attribute08;
		this.attribute09 = attribute09;
		this.attribute10 = attribute10;
	}

	public Long getAccessibility() {
		return this.accessibility;
	}

	public String getAttribute01() {
		return this.attribute01;
	}

	public String getAttribute02() {
		return this.attribute02;
	}

	public String getAttribute03() {
		return this.attribute03;
	}

	public String getAttribute04() {
		return this.attribute04;
	}

	public String getAttribute05() {
		return this.attribute05;
	}

	public String getAttribute06() {
		return this.attribute06;
	}

	public String getAttribute07() {
		return this.attribute07;
	}

	public String getAttribute08() {
		return this.attribute08;
	}

	public String getAttribute09() {
		return this.attribute09;
	}

	public String getAttribute10() {
		return this.attribute10;
	}

	public String getId() {
		return this.id;
	}

	public Long getOperability() {
		return this.operability;
	}

	public String getResoureId() {
		return this.resoureId;
	}

	public String getSysApplicationId() {
		return this.sysApplicationId;
	}

	public String getTargetId() {
		return this.targetId;
	}

	public String getTargetType() {
		return this.targetType;
	}

	public void setAccessibility(Long accessibility) {
		this.accessibility = accessibility;
	}

	public void setAttribute01(String attribute01) {
		this.attribute01 = attribute01;
	}

	public void setAttribute02(String attribute02) {
		this.attribute02 = attribute02;
	}

	public void setAttribute03(String attribute03) {
		this.attribute03 = attribute03;
	}

	public void setAttribute04(String attribute04) {
		this.attribute04 = attribute04;
	}

	public void setAttribute05(String attribute05) {
		this.attribute05 = attribute05;
	}

	public void setAttribute06(String attribute06) {
		this.attribute06 = attribute06;
	}

	public void setAttribute07(String attribute07) {
		this.attribute07 = attribute07;
	}

	public void setAttribute08(String attribute08) {
		this.attribute08 = attribute08;
	}

	public void setAttribute09(String attribute09) {
		this.attribute09 = attribute09;
	}

	public void setAttribute10(String attribute10) {
		this.attribute10 = attribute10;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setOperability(Long operability) {
		this.operability = operability;
	}

	public void setResoureId(String resoureId) {
		this.resoureId = resoureId;
	}

	public void setSysApplicationId(String sysApplicationId) {
		this.sysApplicationId = sysApplicationId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
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
		map.put("PLATFORM6_ACCESSCONTROL", this.id);
		map.put("PLATFORM6_ACCESSCONTROL_TYPE_ID_" + this.targetType + "_" + this.targetId, this.id);
		return map;
	}

	public String prefix() {
		return "PLATFORM6_ACCESSCONTROL";
	}

	public String returnAppId() {
		return null;
	}
}
