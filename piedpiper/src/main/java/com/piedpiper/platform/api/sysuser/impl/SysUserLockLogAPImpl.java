package com.piedpiper.platform.api.sysuser.impl;

import com.piedpiper.platform.api.sysuser.SysUserLockLogAPI;
import com.piedpiper.platform.api.sysuser.dto.SysUserLockLog;
import com.piedpiper.platform.core.rest.client.RestClient;
import com.piedpiper.platform.core.rest.client.RestClientConfig;
import com.piedpiper.platform.core.rest.msg.ResponseMsg;
import javax.ws.rs.core.GenericType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SysUserLockLogAPImpl implements SysUserLockLogAPI {
	private static final Logger log = LoggerFactory.getLogger(SysUserLockLogAPImpl.class);

	public void saveSysUserLockLog(SysUserLockLog sysUserLockLog) {
		String url = RestClientConfig.getRestHost("sysuser")
				+ "/api/platform6/sysorguser/SysUser/sysuserlocklog/saveSysUserLockLog/v1";
		ResponseMsg<Void> responseMsg = RestClient.doPost(url, sysUserLockLog, new GenericType() {
		});
		if (!responseMsg.getRetCode().equals("200")) {
			log.error("url:" + url + ",arg:" + sysUserLockLog + ",error:" + responseMsg.getErrorDesc());
		}
	}
}
