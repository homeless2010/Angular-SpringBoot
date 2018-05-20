package com.piedpiper.platform.api.eform.dto;

import com.piedpiper.platform.core.domain.BeanDTO;
import com.piedpiper.platform.core.properties.PlatformConstant;
import com.piedpiper.platform.core.properties.PlatformConstant.LogType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class EformTempletField extends BeanDTO implements Serializable {
	private static final long serialVersionUID = 6611252269282239757L;
	private String id;
	private String templetId;
	private String colName;
	private String colLabel;
	private String colType;
	private BigDecimal colLength;
	private BigDecimal colDecimal;
	private String colIsSys;
	private BigDecimal colOrder;
	private String colIsMust;
	private String colIsVisible;
	private String colIsSearch;
	private String colIsEdit;
	private String colIsTabVisible;
	private String colIsDetail;
	private String colDropdownType;
	private String colGeneMethod;
	private String colRuleName;
	private String colRuleTitle;
	private String customPath;
	private String remark;
	private String orgId;
	private String deptId;
	private String sysId;
	private String secretLevel;
	private String sysApplicationId;
	private Date lastUpdateDate;
	private String lastUpdatedBy;
	private Date creationDate;
	private String createdBy;
	private String lastUpdateIp;
	private Long version;
	private String attribute01;
	private String attribute02;
	private String attribute03;
	private String attribute04;
	private String attribute05;
	private String attribute06;
	private String attribute07;
	private String attribute08;
	private Date attribute09;
	private Date attribute10;
	private BigDecimal attribute11;
	private BigDecimal attribute12;
	private BigDecimal attribute13;
	private BigDecimal attribute14;
	private BigDecimal attribute15;
	private String colIsDisplay;
	private String colGeneMethodRule;
	private String colShowFormat;
	private String isNew;

	public String getIsNew() {
		return this.isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTempletId() {
		return this.templetId;
	}

	public void setTempletId(String templetId) {
		this.templetId = templetId;
	}

	public String getColName() {
		return this.colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public String getColLabel() {
		return this.colLabel;
	}

	public void setColLabel(String colLabel) {
		this.colLabel = colLabel;
	}

	public String getColType() {
		return this.colType;
	}

	public void setColType(String colType) {
		this.colType = colType;
	}

	public BigDecimal getColLength() {
		return this.colLength;
	}

	public void setColLength(BigDecimal colLength) {
		this.colLength = colLength;
	}

	public BigDecimal getColDecimal() {
		return this.colDecimal;
	}

	public void setColDecimal(BigDecimal colDecimal) {
		this.colDecimal = colDecimal;
	}

	public String getColIsSys() {
		return this.colIsSys;
	}

	public void setColIsSys(String colIsSys) {
		this.colIsSys = colIsSys;
	}

	public BigDecimal getColOrder() {
		return this.colOrder;
	}

	public void setColOrder(BigDecimal colOrder) {
		this.colOrder = colOrder;
	}

	public String getColIsMust() {
		return this.colIsMust;
	}

	public void setColIsMust(String colIsMust) {
		this.colIsMust = colIsMust;
	}

	public String getColIsVisible() {
		return this.colIsVisible;
	}

	public void setColIsVisible(String colIsVisible) {
		this.colIsVisible = colIsVisible;
	}

	public String getColIsSearch() {
		return this.colIsSearch;
	}

	public void setColIsSearch(String colIsSearch) {
		this.colIsSearch = colIsSearch;
	}

	public String getColIsEdit() {
		return this.colIsEdit;
	}

	public void setColIsEdit(String colIsEdit) {
		this.colIsEdit = colIsEdit;
	}

	public String getColIsTabVisible() {
		return this.colIsTabVisible;
	}

	public void setColIsTabVisible(String colIsTabVisible) {
		this.colIsTabVisible = colIsTabVisible;
	}

	public String getColIsDetail() {
		return this.colIsDetail;
	}

	public void setColIsDetail(String colIsDetail) {
		this.colIsDetail = colIsDetail;
	}

	public String getColDropdownType() {
		return this.colDropdownType;
	}

	public void setColDropdownType(String colDropdownType) {
		this.colDropdownType = colDropdownType;
	}

	public String getColGeneMethod() {
		return this.colGeneMethod;
	}

	public void setColGeneMethod(String colGeneMethod) {
		this.colGeneMethod = colGeneMethod;
	}

	public String getColRuleName() {
		return this.colRuleName;
	}

	public void setColRuleName(String colRuleName) {
		this.colRuleName = colRuleName;
	}

	public String getColRuleTitle() {
		return this.colRuleTitle;
	}

	public void setColRuleTitle(String colRuleTitle) {
		this.colRuleTitle = colRuleTitle;
	}

	public String getCustomPath() {
		return this.customPath;
	}

	public void setCustomPath(String customPath) {
		this.customPath = customPath;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getSysId() {
		return this.sysId;
	}

	public void setSysId(String sysId) {
		this.sysId = sysId;
	}

	public String getSecretLevel() {
		return this.secretLevel;
	}

	public void setSecretLevel(String secretLevel) {
		this.secretLevel = secretLevel;
	}

	public String getSysApplicationId() {
		return this.sysApplicationId;
	}

	public void setSysApplicationId(String sysApplicationId) {
		this.sysApplicationId = sysApplicationId;
	}

	public Date getLastUpdateDate() {
		return this.lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getLastUpdatedBy() {
		return this.lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getLastUpdateIp() {
		return this.lastUpdateIp;
	}

	public void setLastUpdateIp(String lastUpdateIp) {
		this.lastUpdateIp = lastUpdateIp;
	}

	public Long getVersion() {
		return this.version;
	}

	public void setVersion(Long version) {
		this.version = version;
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

	public Date getAttribute09() {
		return this.attribute09;
	}

	public void setAttribute09(Date attribute09) {
		this.attribute09 = attribute09;
	}

	public Date getAttribute10() {
		return this.attribute10;
	}

	public void setAttribute10(Date attribute10) {
		this.attribute10 = attribute10;
	}

	public BigDecimal getAttribute11() {
		return this.attribute11;
	}

	public void setAttribute11(BigDecimal attribute11) {
		this.attribute11 = attribute11;
	}

	public BigDecimal getAttribute12() {
		return this.attribute12;
	}

	public void setAttribute12(BigDecimal attribute12) {
		this.attribute12 = attribute12;
	}

	public BigDecimal getAttribute13() {
		return this.attribute13;
	}

	public void setAttribute13(BigDecimal attribute13) {
		this.attribute13 = attribute13;
	}

	public BigDecimal getAttribute14() {
		return this.attribute14;
	}

	public void setAttribute14(BigDecimal attribute14) {
		this.attribute14 = attribute14;
	}

	public BigDecimal getAttribute15() {
		return this.attribute15;
	}

	public void setAttribute15(BigDecimal attribute15) {
		this.attribute15 = attribute15;
	}

	public String getColIsDisplay() {
		return this.colIsDisplay;
	}

	public void setColIsDisplay(String colIsDisplay) {
		this.colIsDisplay = colIsDisplay;
	}

	public String getColGeneMethodRule() {
		return this.colGeneMethodRule;
	}

	public void setColGeneMethodRule(String colGeneMethodRule) {
		this.colGeneMethodRule = colGeneMethodRule;
	}

	public String getColShowFormat() {
		return this.colShowFormat;
	}

	public void setColShowFormat(String colShowFormat) {
		this.colShowFormat = colShowFormat;
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
}
