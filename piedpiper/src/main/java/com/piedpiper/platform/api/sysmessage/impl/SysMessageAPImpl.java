package com.piedpiper.platform.api.sysmessage.impl;

import com.piedpiper.platform.api.session.SessionHelper;
import com.piedpiper.platform.api.sysmessage.SysMessageAPI;
import com.piedpiper.platform.api.sysmessage.dto.SysMessage;
import com.piedpiper.platform.api.sysuser.dto.SysRole;
import com.piedpiper.platform.core.dao.Paging;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheManager;
import com.piedpiper.platform.core.rest.client.RestClient;
import com.piedpiper.platform.core.rest.client.RestClientConfig;
import com.piedpiper.platform.core.rest.msg.Muti3Bean;
import com.piedpiper.platform.core.rest.msg.ResponseMsg;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.Collection;
import java.util.List;
import javax.ws.rs.core.GenericType;
import org.springframework.beans.factory.annotation.Autowired;

public class SysMessageAPImpl implements SysMessageAPI {
	@Autowired
	private BaseCacheManager baseCacheManager;

	public void reLoad() throws Exception {
		String url = RestClientConfig.getRestHost("sysmessage") + "/api/platform6/sysmessage/SysMessage/reLoad/v1";
		ResponseMsg<Void> responseMsg = RestClient.doGet(url, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {
			throw new Exception(responseMsg.getErrorDesc());
		}
	}

	public int getSysMessageCount(String userId) {
		return this.baseCacheManager.countSizeByKey(
				"PLATFORM6_MESSAGE_USER_READ_" + userId + "_0" + "_" + SessionHelper.getApplicationId());
	}

	public List<SysMessage> getNoReadSysMessageByUserId(String userId) {
		this.baseCacheManager.getAllFromCache(
				"PLATFORM6_MESSAGE_USER_READ_" + userId + "_0" + "_" + SessionHelper.getApplicationId(),
				new TypeReference() {
				});
	}

	public SysMessage getSysMessageById(String sysMessageId)
   {
     (SysMessage)this.baseCacheManager.getObjectFromCache("PLATFORM6_MESSAGE", sysMessageId, new TypeReference() {});
   }

	public void insertOrUpdateSysMessagesJSP(List<SysMessage> sysMessages, String userId, String deptId) {
		String url = RestClientConfig.getRestHost("sysmessage")
				+ "/api/platform6/sysmessage/SysMessage/insertOrUpdateSysMessagesJSP/v1";
		Muti3Bean<Collection<SysMessage>, String, String> args = new Muti3Bean(sysMessages, userId, deptId);
		ResponseMsg<Paging<SysRole>> responseMsg = RestClient.doPost(url, args, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {

			throw new RuntimeException(responseMsg.getErrorDesc());
		}
	}
}
