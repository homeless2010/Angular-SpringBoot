package com.piedpiper.platform.api.syspassword.impl;

import com.piedpiper.platform.api.syspassword.SysPasswordAPI;
import com.piedpiper.platform.api.syspassword.dto.SysPasswordTemplet;
import com.piedpiper.platform.api.syspassword.dto.SysPasswordTempletLevel;
import com.piedpiper.platform.api.syspassword.dto.SysUserOldPassword;
import com.piedpiper.platform.api.sysuser.dto.SysUser;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheManager;
import com.piedpiper.platform.core.rest.client.RestClient;
import com.piedpiper.platform.core.rest.client.RestClientConfig;
import com.piedpiper.platform.core.rest.msg.Muti4Bean;
import com.piedpiper.platform.core.rest.msg.ResponseMsg;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.GenericType;
import org.springframework.beans.factory.annotation.Autowired;

public class SysPasswordAPImpl implements SysPasswordAPI {
	@Autowired
	private BaseCacheManager baseCacheManager;

	public void reLoad() throws Exception {
		String url = RestClientConfig.getRestHost("sysuser") + "/api/platform6/syspassword/SysPassword/reLoad/v1";
		ResponseMsg<Void> responseMsg = RestClient.doGet(url, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {
			throw new Exception(responseMsg.getErrorDesc());
		}
	}

	public List<SysUserOldPassword> getSysUserOldPasswordListByHQL(String string) {
		return this.baseCacheManager.getAllFromCache("PLATFORM6_USEROLDPASSWORD_USER_" + string, new TypeReference() {
		});
	}

	public List<SysPasswordTemplet> getSysPasswordTemplet(String secretLevel) {
		if ((secretLevel == null) || (secretLevel.equals(""))) {
			return null;
		}
		List<SysPasswordTempletLevel> sysPasswordTempletLevelList = this.baseCacheManager
				.getAllFromCache("PLATFORM6_PASSWORDTEMPLETLEVEL_CODE_" + secretLevel, new TypeReference() {
				});
		if (sysPasswordTempletLevelList.size() > 0) {
			this.baseCacheManager.getAllFromCache(
					"PLATFORM6_PASSWORDTEMPLET_TYPE_"
							+ ((SysPasswordTempletLevel) sysPasswordTempletLevelList.get(0)).getId(),
					new TypeReference() {
					});
		}
		return null;
	}

	public Map<String, String> getSysPasswordTempletByKey(String secretLevel, String... keys) {
		List<SysPasswordTemplet> list = getSysPasswordTemplet(secretLevel);
		if (list == null) {
			return new HashMap(0);
		}
		Map<String, String> res = new HashMap(keys.length);
		for (String key : keys) {
			for (SysPasswordTemplet t : list) {
				if (t.getTempletKey().equals(key)) {
					res.put(key, t.getTempletValue());
					break;
				}
				res.put(key, null);
			}
		}
		return res;
	}

	public List<SysUserOldPassword> getSysUserOldPassword(String userid) {
		if ((userid == null) || (userid.equals(""))) {
			return null;
		}
		List<SysUserOldPassword> SysUserOldPasswordList = this.baseCacheManager
				.getAllFromCache("PLATFORM6_USEROLDPASSWORD_USER_" + userid, new TypeReference() {
				});
		return SysUserOldPasswordList;
	}

	public Map<String, String> changePassword(String oldPassword, String newPassword, SysUser sysUser, String appId) {
		Muti4Bean<String, String, SysUser, String> mult4Bean = new Muti4Bean();
		mult4Bean.setDto1(oldPassword);
		mult4Bean.setDto2(newPassword);
		mult4Bean.setDto3(sysUser);
		mult4Bean.setDto4(appId);
		String url = RestClientConfig.getRestHost("sysuser")
				+ "/api/platform6/syspassword/SysPassword/changePassword/v1";
		ResponseMsg<Map<String, String>> responseMsg = RestClient.doPost(url, mult4Bean, new GenericType() {
		});
		if (responseMsg.getRetCode().equals("200")) {
			return (Map) responseMsg.getResponseBody();
		}
		return Collections.singletonMap("f", responseMsg.getErrorDesc());
	}
}
