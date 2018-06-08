package com.piedpiper.platform.api.mobile.impl;

import com.piedpiper.platform.api.mobile.MobileAPI;
import com.piedpiper.platform.api.mobile.dto.MobileAppDTO;
import com.piedpiper.platform.api.mobile.dto.MobileAppVersionDTO;
import com.piedpiper.platform.api.mobile.dto.MobileCommandHeadersDTO;
import com.piedpiper.platform.api.mobile.dto.MobileCommandInfoDTO;
import com.piedpiper.platform.api.mobile.dto.MobileDeviceDTO;
import com.piedpiper.platform.api.mobile.dto.MobileServiceHeadersDTO;
import com.piedpiper.platform.api.mobile.dto.MobileServiceInfoDTO;
import com.piedpiper.platform.api.mobile.dto.MobileUpdateSmartDTO;
import com.piedpiper.platform.api.mobile.dto.MobileUseDeviceBindDTO;
import com.piedpiper.platform.api.mobile.dto.MobileUserLogDTO;
import com.piedpiper.platform.core.redisCacheManager.BaseCacheManager;
import com.piedpiper.platform.core.redisCacheManager.CacheHelper;
import com.piedpiper.platform.core.rest.client.RestClient;
import com.piedpiper.platform.core.rest.client.RestClientConfig;
import com.piedpiper.platform.core.rest.msg.ResponseMsg;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.GenericType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class MobileAPImpl implements MobileAPI {
	static Logger logger = LoggerFactory.getLogger(MobileAPImpl.class);
	private static final String SAVE_USERLOG = "/api/platform6/msecure/mobileuserlog/MobileUserLogRest/save/v1";
	private static final String SAVE_BIND = "/api/platform6/msecure/mobiledevice/MobileDeviceRest/saveBind/v1";

	@Autowired
	private BaseCacheManager baseCacheManager;

	public MobileDeviceDTO getMobileDeviceByImei(String imei) {
		return ((MobileDeviceDTO) this.baseCacheManager.getObjectFromCache("PLATFORM6_MOBILEDEVICEIMEI", imei,
				new TypeReference() {
				}));
	}

	public List<MobileUseDeviceBindDTO> getMobileUseDeviceBinds(String userId, String deviceId) {
		return this.baseCacheManager.getAllFromCache(
				"PLATFORM6_MOBILEUSERDEVICEBIND_USER_DEVICE_" + userId + "_" + deviceId, new TypeReference() {
				});
	}

	public MobileCommandInfoDTO getMobileCommandInfoByMethodName(String methodName) {
		return ((MobileCommandInfoDTO) this.baseCacheManager.getObjectFromCache("PLATFORM6_MOBILECOMMANDINFOMETHOD",
				methodName, new TypeReference() {
				}));
	}

	public MobileServiceInfoDTO getMobileServiceInfoById(String id) {
		return ((MobileServiceInfoDTO) this.baseCacheManager.getObjectFromCache("PLATFORM6_MOBILESERVICEINFO", id,
				new TypeReference() {
				}));
	}

	public List<MobileServiceHeadersDTO> getMobileServiceHeadersById(String serviceId) {
		return this.baseCacheManager.getAllFromCache("PLATFORM6_MOBILESERVICEHEADERS_PID_" + serviceId,
				new TypeReference() {
				});
	}

	public List<MobileCommandHeadersDTO> getMobileCommandHeadersById(String commandId) {
		return this.baseCacheManager.getAllFromCache("PLATFORM6_MOBILECOMMANDHEADERS_PID_" + commandId,
				new TypeReference() {
				});
	}

	public List<MobileAppDTO> getAllMobileApps() {
		return this.baseCacheManager.getAllFromCache("PLATFORM6_MOBILEAPP", new TypeReference() {
		});
	}

	public MobileAppDTO getMobileAppById(String id) {
		return ((MobileAppDTO) this.baseCacheManager.getObjectFromCache("PLATFORM6_MOBILEAPP", id, new TypeReference() {
		}));
	}

	public void saveMobileUserLog(MobileUserLogDTO json) {
		logger.debug("insert   mobile   log   ================");
		String restHost = RestClientConfig.getRestHost("mobile");
		String restURL = restHost + "/api/platform6/msecure/mobileuserlog/MobileUserLogRest/save/v1";

		ResponseMsg responseMsg = RestClient.doPost(restURL, json, new GenericType() {
		});
		if (responseMsg.getRetCode().equals("200"))
			logger.debug("调用rest服务成功：" + restURL);
		else
			logger.info("调用rest服务出错：:" + restURL + "," + responseMsg.getRetCode() + "," + responseMsg.getErrorDesc());
	}

	public List<MobileUpdateSmartDTO> smartUpdate(String appName, String appVersion, String smartVersion) {
		List rs = null;
		String restHost = RestClientConfig.getRestHost("mobile");
		String restURL = restHost + "/api/platform6/msecure/mobileapp/MobileAppRest" + "/smartUpdate/" + appName + "/"
				+ appVersion + "/" + smartVersion + "/v1";

		ResponseMsg responseMsg = RestClient.doGet(restURL, new GenericType() {
		});
		if (responseMsg.getRetCode().equals("200")) {
			rs = (List) responseMsg.getResponseBody();
			logger.debug("调用rest服务成功：" + restURL);
		} else {
			logger.info("调用rest服务出错：:" + restURL + "," + responseMsg.getRetCode() + "," + responseMsg.getErrorDesc());
		}
		return rs;
	}

	public void saveMobileUseDeviceBind(MobileUseDeviceBindDTO json) {
		logger.debug("insert   mobile   bind   ================");
		String restHost = RestClientConfig.getRestHost("mobile");
		String restURL = restHost + "/api/platform6/msecure/mobiledevice/MobileDeviceRest/saveBind/v1";

		ResponseMsg responseMsg = RestClient.doPost(restURL, json, new GenericType() {
		});
		if (responseMsg.getRetCode().equals("200"))
			logger.debug("调用rest服务成功：" + restURL);
		else
			logger.info("调用rest服务出错：:" + restURL + "," + responseMsg.getRetCode() + "," + responseMsg.getErrorDesc());
	}

	public void addToken(String appcode, String key) {
		String newkey = "PLATFORM6_MOBILE_LOGIN_" + appcode + "_" + key;
		CacheHelper.getInstance().set(newkey.getBytes(), key.getBytes(), "nx".getBytes(), "ex".getBytes(), 86400L);
	}

	public MobileAppDTO getMobileAppByAppName(String appName) {
		return ((MobileAppDTO) this.baseCacheManager.getObjectFromCache("PLATFORM6_MOBILEAPPNAME", appName,
				new TypeReference() {
				}));
	}

	public List<MobileAppVersionDTO> getMobileAppVersionListByAppId(String appId) {
		return this.baseCacheManager.getAllFromCache("PLATFORM6_MOBILEAPPVERSION_PID_" + appId, new TypeReference() {
		});
	}

	public MobileAppVersionDTO getNewMobileAppVersionByAppIdAndPlatform(String appId, String platform) {
		List list = this.baseCacheManager.getAllFromCache(
				"PLATFORM6_MOBILEAPPVERSION_PID_PLATFORM_VERSION_" + appId + "_" + platform + "_2",
				new TypeReference() {
				});
		if ((list != null) && (list.size() > 0)) {
			return ((MobileAppVersionDTO) list.get(0));
		}
		return null;
	}

	public boolean validateDevice(Map sec) {
		MobileDeviceDTO mdd = getMobileDeviceByImei((String) sec.get("uuid"));

		return ((mdd == null) || (!(mdd.getDeviceStatus().equals("0"))));
	}

	public String validateUserBind(Map sec, String userId) {
		String code = "2";
		MobileDeviceDTO mdd = getMobileDeviceByImei((String) sec.get("uuid"));

		MobileAppDTO mad = getMobileAppByAppName((String) sec.get("appname"));
		if (mad.getAppBindChose().equals("0")) {
			code = "1";
			return code;
		}
		if (mdd == null) {
			return code;
		}
		List<MobileUseDeviceBindDTO> list = getMobileUseDeviceBinds(userId, mdd.getId());
		for (MobileUseDeviceBindDTO entity : list) {
			if (entity.getAppId().equals(mdd.getId())) {
				if (entity.getBindStatus().equals("0")) {
					code = "0";
					break;
				}
				code = "1";

				break;
			}
		}
		return code;
	}

	public boolean validateAppBind(Map sec) {
		MobileAppDTO mad = getMobileAppByAppName((String) sec.get("appname"));

		return ((mad != null) && (!(mad.getAppBindChose().equals("0"))));
	}

	public String validateAppVersion(Map sec) {
		String code = "0";
		String selfversion = (String) sec.get("selfversion");
		MobileAppDTO mad = getMobileAppByAppName((String) sec.get("appname"));
		MobileAppVersionDTO mavd = getNewMobileAppVersionByAppIdAndPlatform(mad.getId(), (String) sec.get("platform"));
		List<MobileAppVersionDTO> list = getMobileAppVersionListByAppId(mad.getId());
		for (MobileAppVersionDTO entity : list) {
			if ((entity.getAppVersion().equals(selfversion)) && (!(entity.getAppStatus().equals("0")))) {
				code = "1";
				if (mavd.getAppVersion().equals(selfversion))
					break;
				code = "2";
				break;
			}

		}

		return code;
	}

	public boolean validateApp(Map sec) {
		MobileAppDTO mdd = getMobileAppByAppName((String) sec.get("appname"));

		return ((mdd != null) && (!(mdd.getAppStatus().equals("0"))));
	}

	public boolean validateToken(Map sec) {
		String key = (String) sec.get("accesstoken");
		String appcode = (String) sec.get("appcode");
		String token = "PLATFORM6_MOBILE_LOGIN_" + appcode + "_" + key;
		return CacheHelper.getInstance().contains(token);
	}
}