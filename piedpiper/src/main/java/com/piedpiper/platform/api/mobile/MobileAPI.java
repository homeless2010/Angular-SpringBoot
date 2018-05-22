package com.piedpiper.platform.api.mobile;

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
import java.util.List;
import java.util.Map;

public abstract interface MobileAPI {
	public abstract MobileDeviceDTO getMobileDeviceByImei(String paramString);

	public abstract List<MobileUseDeviceBindDTO> getMobileUseDeviceBinds(String paramString1, String paramString2);

	public abstract MobileCommandInfoDTO getMobileCommandInfoByMethodName(String paramString);

	public abstract MobileServiceInfoDTO getMobileServiceInfoById(String paramString);

	public abstract List<MobileServiceHeadersDTO> getMobileServiceHeadersById(String paramString);

	public abstract List<MobileCommandHeadersDTO> getMobileCommandHeadersById(String paramString);

	public abstract List<MobileAppDTO> getAllMobileApps();

	public abstract MobileAppDTO getMobileAppById(String paramString);

	public abstract MobileAppDTO getMobileAppByAppName(String paramString);

	public abstract List<MobileAppVersionDTO> getMobileAppVersionListByAppId(String paramString);

	public abstract MobileAppVersionDTO getNewMobileAppVersionByAppIdAndPlatform(String paramString1,
			String paramString2);

	public abstract void saveMobileUserLog(MobileUserLogDTO paramMobileUserLogDTO);

	public abstract void saveMobileUseDeviceBind(MobileUseDeviceBindDTO paramMobileUseDeviceBindDTO);

	public abstract void addToken(String paramString1, String paramString2);

	public abstract boolean validateDevice(Map paramMap);

	public abstract String validateAppVersion(Map paramMap);

	public abstract boolean validateApp(Map paramMap);

	public abstract boolean validateToken(Map paramMap);

	public abstract String validateUserBind(Map paramMap, String paramString);

	public abstract boolean validateAppBind(Map paramMap);

	public abstract List<MobileUpdateSmartDTO> smartUpdate(String paramString1, String paramString2,
			String paramString3);
}
