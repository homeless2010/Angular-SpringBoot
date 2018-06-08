package com.piedpiper.platform.api.sysuser.dto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.piedpiper.platform.core.domain.BeanDTO;
import com.piedpiper.platform.core.properties.PlatformConstant;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheBean;

public class SysPosition extends BeanDTO implements BaseCacheBean, Comparable<SysPosition> {
	public static final String POSITION = "PLATFORM6_POSITION";
	public static final String POSITIONCODE = "PLATFORM6_POSITIONCODE";
	public static final String ORG_POSITION = "PLATFORM6_POSITION_ORG_";
	private static final long serialVersionUID = 1L;
	private String id;
	private String orgId;
	private String positionCode;
	private BigDecimal orderBy;
	private String positionDesc;
	private String positionName;
	private String sysLanguageCode;
	private String sysPositionId;
	private String tlId;
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
	private String orgIdentity;
	private static final String pattern = "[id=%s],[组织id=%s],[岗位编码=%s],[排序=%s]";

	@JsonIgnore
	public String getOrgIdentity() {
		return this.orgIdentity;
	}

	public void setOrgIdentity(String orgIdentity) {
		this.orgIdentity = orgIdentity;
	}

	public String getPositionDesc() {
		return this.positionDesc;
	}

	public void setPositionDesc(String positionDesc) {
		this.positionDesc = positionDesc;
	}

	public String getPositionName() {
		return this.positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getSysLanguageCode() {
		return this.sysLanguageCode;
	}

	public void setSysLanguageCode(String sysLanguageCode) {
		this.sysLanguageCode = sysLanguageCode;
	}

	public String getSysPositionId() {
		return this.sysPositionId;
	}

	public void setSysPositionId(String sysPositionId) {
		this.sysPositionId = sysPositionId;
	}

	public String getTlId() {
		return this.tlId;
	}

	public void setTlId(String tlId) {
		this.tlId = tlId;
	}

	@Id
	@Column(name = "ID", nullable = false, length = 50)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "ORG_ID", nullable = false, length = 50)
	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Column(name = "POSITION_CODE", nullable = false, length = 50)
	public String getPositionCode() {
		return this.positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	@Column(name = "ORDER_BY", nullable = false, length = 50)
	public BigDecimal getOrderBy() {
		return this.orderBy;
	}

	public void setOrderBy(BigDecimal orderBy) {
		this.orderBy = orderBy;
	}

	@Column(name = "ATTRIBUTE_01", length = 255)
	public String getAttribute01() {
		return this.attribute01;
	}

	public void setAttribute01(String attribute01) {
		this.attribute01 = attribute01;
	}

	@Column(name = "ATTRIBUTE_02", length = 255)
	public String getAttribute02() {
		return this.attribute02;
	}

	public void setAttribute02(String attribute02) {
		this.attribute02 = attribute02;
	}

	@Column(name = "ATTRIBUTE_03", length = 255)
	public String getAttribute03() {
		return this.attribute03;
	}

	public void setAttribute03(String attribute03) {
		this.attribute03 = attribute03;
	}

	@Column(name = "ATTRIBUTE_04", length = 255)
	public String getAttribute04() {
		return this.attribute04;
	}

	public void setAttribute04(String attribute04) {
		this.attribute04 = attribute04;
	}

	@Column(name = "ATTRIBUTE_05", length = 255)
	public String getAttribute05() {
		return this.attribute05;
	}

	public void setAttribute05(String attribute05) {
		this.attribute05 = attribute05;
	}

	@Column(name = "ATTRIBUTE_06", length = 255)
	public String getAttribute06() {
		return this.attribute06;
	}

	public void setAttribute06(String attribute06) {
		this.attribute06 = attribute06;
	}

	@Column(name = "ATTRIBUTE_07", length = 255)
	public String getAttribute07() {
		return this.attribute07;
	}

	public void setAttribute07(String attribute07) {
		this.attribute07 = attribute07;
	}

	@Column(name = "ATTRIBUTE_08", length = 255)
	public String getAttribute08() {
		return this.attribute08;
	}

	public void setAttribute08(String attribute08) {
		this.attribute08 = attribute08;
	}

	@Column(name = "ATTRIBUTE_09", length = 255)
	public String getAttribute09() {
		return this.attribute09;
	}

	public void setAttribute09(String attribute09) {
		this.attribute09 = attribute09;
	}

	@Column(name = "ATTRIBUTE_10", length = 255)
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
		Map<String, String> map = new HashMap();
		map.put("PLATFORM6_POSITION", this.id);
		map.put("PLATFORM6_POSITIONCODE", this.positionCode);
		map.put("PLATFORM6_POSITION_ORG_" + this.orgId, this.id);
		return map;
	}

	public String prefix() {
		return "PLATFORM6_POSITION";
	}

	public String returnValidFlag() {
		return null;
	}

	public String returnId() {
		return this.id;
	}

	public String returnAppId() {
		return null;
	}

	public int compareTo(SysPosition o) {
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

	public String toString() {
		return String.format("[id=%s],[组织id=%s],[岗位编码=%s],[排序=%s]",
				new Object[] { this.id, this.orgId, this.positionCode, this.orderBy });
	}

	public String returnKey() {
		return this.id;
	}
}
