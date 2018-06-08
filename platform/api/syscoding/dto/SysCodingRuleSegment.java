package com.piedpiper.platform.api.syscoding.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.piedpiper.platform.core.domain.BeanDTO;
import com.piedpiper.platform.core.properties.PlatformConstant;

public class SysCodingRuleSegment extends BeanDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String baseId;
	private BigDecimal segmentIndex;
	private String segmentName;
	private String segmentType;
	private BigDecimal segmentLength;
	private String segmentPrefixion;
	private String segmentSuffix;
	private String segmentRelation;
	private String comSegmentId;
	private BigDecimal serialNumberStart;
	private BigDecimal serialNumberEnd;
	private BigDecimal serialStep;
	private String isInputSerial;
	private String format;
	private String isCurrentTime;
	private String func;
	private String sqlExpression;
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

	public String getBaseId() {
		return this.baseId;
	}

	public void setBaseId(String baseId) {
		this.baseId = baseId;
	}

	public BigDecimal getSegmentIndex() {
		return this.segmentIndex;
	}

	public void setSegmentIndex(BigDecimal segmentIndex) {
		this.segmentIndex = segmentIndex;
	}

	public String getSegmentName() {
		return this.segmentName;
	}

	public void setSegmentName(String segmentName) {
		this.segmentName = segmentName;
	}

	public String getSegmentType() {
		return this.segmentType;
	}

	public void setSegmentType(String segmentType) {
		this.segmentType = segmentType;
	}

	public BigDecimal getSegmentLength() {
		return this.segmentLength;
	}

	public void setSegmentLength(BigDecimal segmentLength) {
		this.segmentLength = segmentLength;
	}

	public String getSegmentPrefixion() {
		return this.segmentPrefixion;
	}

	public void setSegmentPrefixion(String segmentPrefixion) {
		this.segmentPrefixion = segmentPrefixion;
	}

	public String getSegmentSuffix() {
		return this.segmentSuffix;
	}

	public void setSegmentSuffix(String segmentSuffix) {
		this.segmentSuffix = segmentSuffix;
	}

	public String getSegmentRelation() {
		return this.segmentRelation;
	}

	public void setSegmentRelation(String segmentRelation) {
		this.segmentRelation = segmentRelation;
	}

	public String getComSegmentId() {
		return this.comSegmentId;
	}

	public void setComSegmentId(String comSegmentId) {
		this.comSegmentId = comSegmentId;
	}

	public BigDecimal getSerialNumberStart() {
		return this.serialNumberStart;
	}

	public void setSerialNumberStart(BigDecimal serialNumberStart) {
		this.serialNumberStart = serialNumberStart;
	}

	public BigDecimal getSerialNumberEnd() {
		return this.serialNumberEnd;
	}

	public void setSerialNumberEnd(BigDecimal serialNumberEnd) {
		this.serialNumberEnd = serialNumberEnd;
	}

	public BigDecimal getSerialStep() {
		return this.serialStep;
	}

	public void setSerialStep(BigDecimal serialStep) {
		this.serialStep = serialStep;
	}

	public String getIsInputSerial() {
		return this.isInputSerial;
	}

	public void setIsInputSerial(String isInputSerial) {
		this.isInputSerial = isInputSerial;
	}

	public String getFormat() {
		return this.format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getIsCurrentTime() {
		return this.isCurrentTime;
	}

	public void setIsCurrentTime(String isCurrentTime) {
		this.isCurrentTime = isCurrentTime;
	}

	public String getFunc() {
		return this.func;
	}

	public void setFunc(String func) {
		this.func = func;
	}

	public String getSqlExpression() {
		return this.sqlExpression;
	}

	public void setSqlExpression(String sqlExpression) {
		this.sqlExpression = sqlExpression;
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
			return "SYS_CODING_RULE_SEGMENT";
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
