package com.piedpiper.platform.api.sysuser.dto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.piedpiper.platform.core.domain.BeanDTO;
import com.piedpiper.platform.core.properties.PlatformConstant;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheBean;

public class SysDept extends BeanDTO implements BaseCacheBean, Comparable<SysDept> {
	public static final String DEPT = "PLATFORM6_DEPT";
	public static final String DEPTCODE = "PLATFORM6_DEPTCODE";
	public static final String ORG_DEPT = "PLATFORM6_ORG_DEPT_";
	public static final String DEPT_DEPT = "PLATFORM6_DEPT_DEPT_";
	private static final long serialVersionUID = 1L;
	private String id;
	private String deptCode;
	private String parentDeptId;
	private String orgId;
	private BigDecimal orderBy;
	private String post;
	private String tel;
	private String fax;
	private String email;
	private String deptAlias;
	private String deptType;
	private Long deptLevel;
	private String deptIndexTreeNo;
	private String workCalendarId;
	private String validFlag;
	private String deptName;
	private String deptDesc;
	private String deptPlace;
	private String currentLanguageCode = "zh_CN";

	private String sysLanguageCode;

	private String parentDeptName;

	private String tlId;
	private String nodeType;
	private String searchContext;
	private String icon;
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
	private List<SysDept> childrens;
	private int childrenCount;
	private static final String pattern = "[id=%s],[组织id=%s],[部门编码=%s],[父部门id=%s],[状态=%s][排序=%s][部门级别=%s]";

	@JsonIgnore
	public String getOrgIdentity() {
		return this.orgIdentity;
	}

	public void setOrgIdentity(String orgIdentity) {
		this.orgIdentity = orgIdentity;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeptCode() {
		return this.deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getParentDeptId() {
		return this.parentDeptId;
	}

	public void setParentDeptId(String parentDeptId) {
		this.parentDeptId = parentDeptId;
	}

	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public BigDecimal getOrderBy() {
		return this.orderBy;
	}

	public void setOrderBy(BigDecimal orderBy) {
		this.orderBy = orderBy;
	}

	public String getPost() {
		return this.post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDeptAlias() {
		return this.deptAlias;
	}

	public void setDeptAlias(String deptAlias) {
		this.deptAlias = deptAlias;
	}

	public String getDeptType() {
		return this.deptType;
	}

	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}

	public Long getDeptLevel() {
		return this.deptLevel;
	}

	public void setDeptLevel(Long deptLevel) {
		this.deptLevel = deptLevel;
	}

	public String getDeptIndexTreeNo() {
		return this.deptIndexTreeNo;
	}

	public void setDeptIndexTreeNo(String deptIndexTreeNo) {
		this.deptIndexTreeNo = deptIndexTreeNo;
	}

	public String getWorkCalendarId() {
		return this.workCalendarId;
	}

	public void setWorkCalendarId(String workCalendarId) {
		this.workCalendarId = workCalendarId;
	}

	public String getValidFlag() {
		return this.validFlag;
	}

	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
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

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptDesc() {
		return this.deptDesc;
	}

	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}

	public String getDeptPlace() {
		return this.deptPlace;
	}

	public void setDeptPlace(String deptPlace) {
		this.deptPlace = deptPlace;
	}

	public String getCurrentLanguageCode() {
		return this.currentLanguageCode;
	}

	public void setCurrentLanguageCode(String currentLanguageCode) {
		this.currentLanguageCode = currentLanguageCode;
	}

	public String getParentDeptName() {
		return this.parentDeptName;
	}

	public void setParentDeptName(String parentDeptName) {
		this.parentDeptName = parentDeptName;
	}

	public String getSysLanguageCode() {
		return this.sysLanguageCode;
	}

	public void setSysLanguageCode(String sysLanguageCode) {
		this.sysLanguageCode = sysLanguageCode;
	}

	public String getTlId() {
		return this.tlId;
	}

	public void setTlId(String tlId) {
		this.tlId = tlId;
	}

	public String getNodeType() {
		return this.nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public String getSearchContext() {
		return this.searchContext;
	}

	public void setSearchContext(String searchContext) {
		this.searchContext = searchContext;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<SysDept> getChildrens() {
		return this.childrens;
	}

	public void setChildrens(List<SysDept> childrens) {
		this.childrens = childrens;
	}

	public int getChildrenCount() {
		return this.childrenCount;
	}

	public void setChildrenCount(int childrenCount) {
		this.childrenCount = childrenCount;
	}

	public Map<String, ?> returnCacheKey() {
		Map<String, String> map = new HashMap();
		map.put("PLATFORM6_DEPT", this.id);
		map.put("PLATFORM6_DEPTCODE", this.deptCode);
		map.put("PLATFORM6_ORG_DEPT_" + this.orgId, this.id);
		map.put("PLATFORM6_DEPT_DEPT_" + this.parentDeptId, this.id);
		return map;
	}

	public String prefix() {
		return "PLATFORM6_DEPT";
	}

	public String returnValidFlag() {
		return this.validFlag;
	}

	public String returnId() {
		return this.id;
	}

	public String returnAppId() {
		return null;
	}

	public int compareTo(SysDept o) {
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
		return String.format("[id=%s],[组织id=%s],[部门编码=%s],[父部门id=%s],[状态=%s][排序=%s][部门级别=%s]", new Object[] { this.id,
				this.orgId, this.deptCode, this.parentDeptId, this.validFlag, this.orderBy, this.deptLevel });
	}

	public String returnKey() {
		return this.id;
	}
}
