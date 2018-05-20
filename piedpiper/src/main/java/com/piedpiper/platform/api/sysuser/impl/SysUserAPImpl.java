package com.piedpiper.platform.api.sysuser.impl;

import com.piedpiper.platform.api.session.SessionHelper;
import com.piedpiper.platform.api.sysshirolog.utils.SecurityUtil;
import com.piedpiper.platform.api.sysuser.SysUserAPI;
import com.piedpiper.platform.api.sysuser.dto.SysUser;
import com.piedpiper.platform.core.dao.Paging;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheManager;
import com.piedpiper.platform.core.rest.client.RestClient;
import com.piedpiper.platform.core.rest.client.RestClientConfig;
import com.piedpiper.platform.core.rest.msg.Muti2Bean;
import com.piedpiper.platform.core.rest.msg.Muti3Bean;
import com.piedpiper.platform.core.rest.msg.Muti4Bean;
import com.piedpiper.platform.core.rest.msg.ResponseMsg;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.GenericType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

public class SysUserAPImpl implements SysUserAPI {
	@Autowired
	private BaseCacheManager baseCacheManager;

	public void reLoad() throws Exception {
		String url = RestClientConfig.getRestHost("sysuser") + "/api/platform6/sysorguser/SysUser/reLoad/v1";
		ResponseMsg<Void> responseMsg = RestClient.doGet(url, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {
			throw new Exception(responseMsg.getErrorDesc());
		}
	}

	private static final Logger log = LoggerFactory.getLogger(SysUserAPImpl.class);

	public List<SysUser> getAllSysUsers() {
		this.baseCacheManager.getAllFromCache("PLATFORM6_USER", new TypeReference() {
		});
	}

	public List<SysUser> getAllSysUsersNoFilter() {
		this.baseCacheManager.getAllFromCacheNoFilter("PLATFORM6_USER", new TypeReference() {
		});
	}

	public SysUser getSysUserById(String id) {
		return (SysUser) this.baseCacheManager.getObjectFromCache("PLATFORM6_USER", id, SysUser.class);
	}

	public SysUser getSysUserByIdNoFilter(String id) {
		return (SysUser) this.baseCacheManager.getObjectFromCacheNoFilter("PLATFORM6_USER", id, SysUser.class);
	}

	public SysUser getSysUserByUnitCode(String unitCode) {
		return (SysUser) this.baseCacheManager.getObjectFromCache("PLATFORM6_USERUNITCODE", unitCode, SysUser.class);
	}

	public SysUser getSysUserByLoginName(String loginName) {
		return (SysUser) this.baseCacheManager.getObjectFromCache("PLATFORM6_USERLOGINNAME",
				SecurityUtil.matchingUppercase(loginName), SysUser.class);
	}

	public SysUser getSysUserByEmail(String email) {
		return (SysUser) this.baseCacheManager.getObjectFromCache("PLATFORM6_USEREMAIL", email, SysUser.class);
	}

	public SysUser getSysUserByLoginString(String loginName) {
		SysUser sysUser = getSysUserByLoginName(loginName);
		if (sysUser == null) {
			return getSysUserByUnitCode(loginName);
		}
		return sysUser;
	}

	public String getSysUserNameById(String id) {
		if (StringUtils.isEmpty(id)) {
			return "";
		}
		SysUser user = getSysUserById(id);
		if (user == null) {
			if (log.isWarnEnabled()) {
				log.warn("id为" + id + "的用户无效或者不存在！");
			}
			return "用户无效或者不存在";
		}
		return user.getName();
	}

	public String getSysUserNameByLoginString(String loginName) {
		SysUser user = getSysUserByLoginString(loginName);
		if (user == null) {
			return "";
		}
		return user.getName();
	}

	public boolean checkUserNo(String no) {
		return this.baseCacheManager.containsKey("PLATFORM6_USERNO", no);
	}

	public boolean checkUserUnitCode(String unitCode) {
		return this.baseCacheManager.containsKey("PLATFORM6_USERUNITCODE", unitCode);
	}

	public boolean checkUserLoginName(String loginName) {
		return this.baseCacheManager.containsKey("PLATFORM6_USERLOGINNAME", loginName);
	}

	public void updateSysUserNotEvent(SysUser sysUser) {
		String url = RestClientConfig.getRestHost("sysuser")
				+ "/api/platform6/sysorguser/SysUser/updateSysUserNotEvent/v1";
		ResponseMsg<Void> responseMsg = RestClient.doPost(url, sysUser, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {

			throw new RuntimeException(responseMsg.getErrorDesc());
		}
	}

	public void updateSysUser(SysUser sysUser) {
		String url = RestClientConfig.getRestHost("sysuser") + "/api/platform6/sysorguser/SysUser/updateSysUser/v1";
		ResponseMsg<Void> responseMsg = RestClient.doPost(url, sysUser, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {

			throw new RuntimeException(responseMsg.getErrorDesc());
		}
	}

	public void deleteSysUser(SysUser sysUser) {
		String url = RestClientConfig.getRestHost("sysuser") + "/api/platform6/sysorguser/SysUser/deleteSysUser/v1";
		ResponseMsg<Void> responseMsg = RestClient.doPost(url, sysUser, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {

			throw new RuntimeException(responseMsg.getErrorDesc());
		}
	}

	public Paging<SysUser> getPageList(Paging<SysUser> page, Map<String, Object> parameter) {
		Muti2Bean<Paging<SysUser>, Map<String, Object>> mb = new Muti2Bean(page, parameter);
		String url = RestClientConfig.getRestHost("sysuser") + "/api/platform6/sysorguser/SysUser/getPageList/v1";
		ResponseMsg<Paging<SysUser>> responseMsg = RestClient.doPost(url, mb, new GenericType() {
		});
		if (responseMsg.getRetCode().equals("200")) {
			return (Paging) responseMsg.getResponseBody();
		}
		log.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
		return null;
	}

	public Muti3Bean<SysUser, byte[], byte[]> getSysUserByIdforupload(String id) {
		String url = RestClientConfig.getRestHost("sysuser")
				+ "/api/platform6/sysorguser/SysUser/getSysUserByIdforupload/v1";
		ResponseMsg<Muti3Bean<SysUser, byte[], byte[]>> responseMsg = RestClient.doPost(url, id, new GenericType() {
		});
		if (responseMsg.getRetCode().equals("200")) {
			return (Muti3Bean) responseMsg.getResponseBody();
		}
		log.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
		return null;
	}

	@Deprecated
	public String changePassword(String oldPassword, String newPassword, SysUser sysUser) {
		Muti4Bean<String, String, SysUser, String> mult4Bean = new Muti4Bean();
		mult4Bean.setDto1(oldPassword);
		mult4Bean.setDto2(newPassword);
		mult4Bean.setDto3(sysUser);
		mult4Bean.setDto4(SessionHelper.getApplicationId());
		String url = RestClientConfig.getRestHost("sysuser") + "/api/platform6/sysorguser/SysUser/changePassword/v1";
		ResponseMsg<String> responseMsg = RestClient.doPost(url, mult4Bean, new GenericType() {
		});
		if (responseMsg.getRetCode().equals("200")) {
			return (String) responseMsg.getResponseBody();
		}
		log.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
		return null;
	}

	public List<SysUser> getSysUserByHQL(String hql, Object[] parameter) {
		Muti2Bean<String, Object[]> bean = new Muti2Bean();
		bean.setDto1(hql);
		bean.setDto2(parameter);
		String url = RestClientConfig.getRestHost("sysuser") + "/api/platform6/sysorguser/SysUser/getSysUserByHQL/v1";
		ResponseMsg<List<SysUser>> responseMsg = RestClient.doPost(url, bean, new GenericType() {
		});
		if (responseMsg.getRetCode().equals("200")) {
			return (List) responseMsg.getResponseBody();
		}
		log.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
		return null;
	}

	public void insertSysUser(SysUser sysUser) {
		Muti2Bean<SysUser, String> args = new Muti2Bean(sysUser, SessionHelper.getApplicationId());
		String url = RestClientConfig.getRestHost("sysuser") + "/api/platform6/sysorguser/SysUser/insertSysUserVo/v1";
		ResponseMsg<Void> responseMsg = RestClient.doPost(url, args, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {

			throw new RuntimeException(responseMsg.getErrorDesc());
		}
	}

	public List<SysUser> find(String hqlUser, String loginName, String unitCode) {
		Muti3Bean<String, String, String> args = new Muti3Bean(hqlUser, loginName, unitCode);
		String url = RestClientConfig.getRestHost("sysuser")
				+ "/api/platform6/sysorguser/SysUser/findByLoginNameAndUnitCode/v1";
		ResponseMsg<List<SysUser>> responseMsg = RestClient.doPost(url, args, new GenericType() {
		});
		if (responseMsg.getRetCode().equals("200")) {
			return (List) responseMsg.getResponseBody();
		}
		throw new RuntimeException(responseMsg.getErrorDesc());
	}

	public void insertSysUserNotEvent(SysUser sysUser) {
		String url = RestClientConfig.getRestHost("sysuser")
				+ "/api/platform6/sysorguser/SysUser/insertSysUserNotEvent/v1";
		ResponseMsg<Void> responseMsg = RestClient.doPost(url, sysUser, new GenericType() {
		});
		if (responseMsg.getRetCode().equals("200")) {
		}
	}

	public List<SysUser> findUserForPopup(String queryWord) {
		String url = RestClientConfig.getRestHost("sysuser") + "/api/platform6/sysorguser/SysUser/findUserForPopup/v1";
		ResponseMsg<List<SysUser>> responseMsg = RestClient.doPost(url, queryWord, new GenericType() {
		});
		if (responseMsg.getRetCode().equals("200")) {
			return (List) responseMsg.getResponseBody();
		}
		throw new RuntimeException(responseMsg.getErrorDesc());
	}
}
