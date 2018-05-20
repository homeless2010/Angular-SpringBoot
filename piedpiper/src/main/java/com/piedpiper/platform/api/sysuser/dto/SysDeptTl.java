package com.piedpiper.platform.api.sysuser.dto;

import java.util.HashMap;
import java.util.Map;

import com.piedpiper.platform.core.domain.BeanDTO;
import com.piedpiper.platform.core.properties.PlatformConstant;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheBean;

public class SysDeptTl extends BeanDTO implements BaseCacheBean {
	public static final String DEPTTLID = "PLATFORM6_DEPTTLID";
	public static final String DEPTTL = "PLATFORM6_DEPTTL";
	public static final String DEPT_DEPTNAME = "PLATFORM6_DEPT_DEPTNAME";
	private static final long serialVersionUID = 1L;
	private String id;
	private String sysDeptId;
	private String sysLanguageCode;
	private String deptName;
	private String deptDesc;
	private String deptPlace;
	private static final String pattern = "[id=%s],[部门名称=%s],[部门描述=%s],[部门id=%s],[语言代码=%s]";

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSysDeptId() {
		return this.sysDeptId;
	}

	public void setSysDeptId(String sysDeptId) {
		this.sysDeptId = sysDeptId;
	}

	public String getSysLanguageCode() {
		return this.sysLanguageCode;
	}

	public void setSysLanguageCode(String sysLanguageCode) {
		this.sysLanguageCode = sysLanguageCode;
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
		map.put("PLATFORM6_DEPTTLID", this.id);
		map.put("PLATFORM6_DEPT_DEPTNAME", this.deptName + "_" + this.sysLanguageCode);
		map.put("PLATFORM6_DEPTTL", this.sysDeptId + "_" + this.sysLanguageCode);
		return map;
	}

	public String prefix() {
		return "PLATFORM6_DEPTTLID";
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

	public String toString() {
		return String.format("[id=%s],[部门名称=%s],[部门描述=%s],[部门id=%s],[语言代码=%s]",
				new Object[] { this.id, this.deptName, this.deptDesc, this.sysDeptId, this.sysLanguageCode });
	}

	public String returnKey() {
		return this.id;
	}
}
