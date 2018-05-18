package com.piedpiper.platform.api.sysuser.dto;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author homeless2010
 *
 */
public class SysUser extends BeanDTO implements BaseCacheBean, Comparable<SysUser> {
	public static final String USER = "PLATFORM_USER";
	public static final String USERLOGINNAME = "PLATFORM_USERLOGINNAME";
	public static final String USERUNITCODE = "PLATFORM_USERUNITCODE";
	public static final String USERNO = "PLATFORM_USERNO";
	public static final String USEREMAIL = "PLATFORM_USEREMAIL";
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String nameEn;
	private String no;
	private String loginName;
	private String loginPassword;
	private String secretLevel;
	private Date birthday;
	private String sex;
	private String nation;
	private String birthAddress;
	private String polity;
	private Date workDate;
	private String title;
	private String degree;
	private String education;
	private String mobile;
	private String officeTel;
	private String fax;
	private String email;
	private String workSpace;
	private String roomNo;
	private String homeAddress;
	private String homeZip;
	private String homeTel;

	@JsonIgnore
	private Blob photo;

	@JsonIgnore
	private Blob sign;
	private BigDecimal orderBy;
	private String type;
	private String status;
	private String remark;
	private String languageCode;
	private String lastLoginDeptId;
	private Date lastPasswordDate;
	private Date lastLoginDate;
	private BigDecimal passwordDays;
	private BigDecimal loginFailTimes;
	private String userLocked;
	private Date loginFailFirstTime;
	private String unitCode;
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
	private String deptName;
	private String deptCode;
	private String deptId;
	private String userDeptPositionId;
	private String userId;
	private String positionId;
	private String isChiefDept;
	private String isManager;
	private BigDecimal userDeptPositionOrderBy;
	private String ruleId;
	private String ruleName;
	private String positionName;
	private String editAc;
	private static final String pattern = "[id=%s],[姓名=%s],[用户编号=%s],[登录名=%s],[状态=%s][锁定状态=%s][集团统一编码=%s]";

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameEn() {
		return this.nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getNo() {
		return this.no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPassword() {
		return this.loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getSecretLevel() {
		return this.secretLevel;
	}

	public void setSecretLevel(String secretLevel) {
		this.secretLevel = secretLevel;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNation() {
		return this.nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getBirthAddress() {
		return this.birthAddress;
	}

	public void setBirthAddress(String birthAddress) {
		this.birthAddress = birthAddress;
	}

	public String getPolity() {
		return this.polity;
	}

	public void setPolity(String polity) {
		this.polity = polity;
	}

	public Date getWorkDate() {
		return this.workDate;
	}

	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDegree() {
		return this.degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getEducation() {
		return this.education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getOfficeTel() {
		return this.officeTel;
	}

	public void setOfficeTel(String officeTel) {
		this.officeTel = officeTel;
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

	public String getWorkSpace() {
		return this.workSpace;
	}

	public void setWorkSpace(String workSpace) {
		this.workSpace = workSpace;
	}

	public String getRoomNo() {
		return this.roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getHomeAddress() {
		return this.homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getHomeZip() {
		return this.homeZip;
	}

	public void setHomeZip(String homeZip) {
		this.homeZip = homeZip;
	}

	public String getHomeTel() {
		return this.homeTel;
	}

	public void setHomeTel(String homeTel) {
		this.homeTel = homeTel;
	}

	public Blob getPhoto() {
		return this.photo;
	}

	public void setPhoto(Blob photo) {
		this.photo = photo;
	}

	public Blob getSign() {
		return this.sign;
	}

	public void setSign(Blob sign) {
		this.sign = sign;
	}

	public BigDecimal getOrderBy() {
		return this.orderBy;
	}

	public void setOrderBy(BigDecimal orderBy) {
		this.orderBy = orderBy;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLanguageCode() {
		return this.languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public String getLastLoginDeptId() {
		return this.lastLoginDeptId;
	}

	public void setLastLoginDeptId(String lastLoginDeptId) {
		this.lastLoginDeptId = lastLoginDeptId;
	}

	public Date getLastPasswordDate() {
		return this.lastPasswordDate;
	}

	public void setLastPasswordDate(Date lastPasswordDate) {
		this.lastPasswordDate = lastPasswordDate;
	}

	public Date getLastLoginDate() {
		return this.lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public BigDecimal getPasswordDays() {
		return this.passwordDays;
	}

	public void setPasswordDays(BigDecimal passwordDays) {
		this.passwordDays = passwordDays;
	}

	public BigDecimal getLoginFailTimes() {
		return this.loginFailTimes;
	}

	public void setLoginFailTimes(BigDecimal loginFailTimes) {
		this.loginFailTimes = loginFailTimes;
	}

	public String getUserLocked() {
		return this.userLocked;
	}

	public void setUserLocked(String userLocked) {
		this.userLocked = userLocked;
	}

	public String getUnitCode() {
		return this.unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
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

	public Date getLoginFailFirstTime() {
		return this.loginFailFirstTime;
	}

	public void setLoginFailFirstTime(Date loginFailFirstTime) {
		this.loginFailFirstTime = loginFailFirstTime;
	}

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptCode() {
		return this.deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public int compareTo(SysUser o) {
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

	public void setLogTitle(String string) {
		this.logTitle = string;
	}

	public String getLogFormName() {
		return null;
	}

	public String getLogTitle() {
		return this.logTitle;
	}

	public PlatformConstant.LogType getLogType() {
		return null;
	}

	public String getUserDeptPositionId() {
		return this.userDeptPositionId;
	}

	public void setUserDeptPositionId(String userDeptPositionId) {
		this.userDeptPositionId = userDeptPositionId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPositionId() {
		return this.positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	public String getIsChiefDept() {
		return this.isChiefDept;
	}

	public void setIsChiefDept(String isChiefDept) {
		this.isChiefDept = isChiefDept;
	}

	public String getIsManager() {
		return this.isManager;
	}

	public void setIsManager(String isManager) {
		this.isManager = isManager;
	}

	public BigDecimal getUserDeptPositionOrderBy() {
		return this.userDeptPositionOrderBy;
	}

	public void setUserDeptPositionOrderBy(BigDecimal userDeptPositionOrderBy) {
		this.userDeptPositionOrderBy = userDeptPositionOrderBy;
	}

	public String getRuleId() {
		return this.ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public String getRuleName() {
		return this.ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getPositionName() {
		return this.positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getEditAc() {
		return this.editAc;
	}

	public void setEditAc(String editAc) {
		this.editAc = editAc;
	}

	public Map<String, ?> returnCacheKey() {
		Map map = new HashMap();
		map.put("PLATFORM_USER", this.id);
		map.put("PLATFORM_USERLOGINNAME", this.loginName);
		map.put("PLATFORM_USERUNITCODE", this.unitCode);
		map.put("PLATFORM_USERNO", new BaseCacheBlank(this.no));
		if ((this.email != null) && (!(this.email.equals("")))) {
			map.put("PLATFORM_USEREMAIL", this.email);
		}
		return map;
	}

	public String prefix() {
		return "PLATFORM_USER";
	}

	public String returnValidFlag() {
		return this.status;
	}

	public String returnId() {
		return this.id;
	}

	public String returnAppId() {
		return null;
	}

	public String toString() {
		return String.format("[id=%s],[姓名=%s],[用户编号=%s],[登录名=%s],[状态=%s][锁定状态=%s][集团统一编码=%s]", new Object[] { this.id,
				this.name, this.no, this.loginName, this.status, this.userLocked, this.unitCode });
	}

	public String returnKey() {
		return this.id;
	}
}
