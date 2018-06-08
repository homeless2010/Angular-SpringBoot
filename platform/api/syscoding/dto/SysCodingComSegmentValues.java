package com.piedpiper.platform.api.syscoding.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.piedpiper.platform.core.domain.BeanDTO;
import com.piedpiper.platform.core.properties.PlatformConstant;

public class SysCodingComSegmentValues extends BeanDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String segmentId;
	private String segmentName;
	private String segmentValue;
	private BigDecimal orderBy;
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

	public String getSegmentId() {
		return this.segmentId;
	}

	public void setSegmentId(String segmentId) {
		this.segmentId = segmentId;
	}

	public String getSegmentName() {
		return this.segmentName;
	}

	public void setSegmentName(String segmentName) {
		this.segmentName = segmentName;
	}

	public String getSegmentValue() {
		return this.segmentValue;
	}

	public void setSegmentValue(String segmentValue) {
		this.segmentValue = segmentValue;
	}

	public BigDecimal getOrderBy() {
		return this.orderBy;
	}

	public void setOrderBy(BigDecimal orderBy) {
		this.orderBy = orderBy;
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
		if ((this.logFormName == null) || (this.logFormName.equals(""))) {
			return "XX模块";
		}
		return this.logFormName;
	}

	public String getLogTitle() {
		if ((this.logTitle == null) || (this.logTitle.equals(""))) {
			return "SYS_CODING_COM_SEGMENT_VALUES";
		}
		return this.logTitle;
	}

	public PlatformConstant.LogType getLogType() {
		if ((this.logType == null) || (this.logType.equals(""))) {
			return PlatformConstant.LogType.system_manage;
		}
		return this.logType;
	}
}
