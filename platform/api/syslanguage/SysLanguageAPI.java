package com.piedpiper.platform.api.syslanguage;

import com.piedpiper.platform.api.syslanguage.dto.SysLanguage;
import java.util.List;

public abstract interface SysLanguageAPI {
	public abstract void reLoad() throws Exception;

	public abstract List<SysLanguage> getAllSysLanguages();

	public abstract SysLanguage getSysDefaultLanguage();

	public abstract String getCurrentLanguageCode();

	public abstract String getSystemDefaultLanguageCode();

	public abstract String getCurrentUserLanguageCode(String paramString);

	public abstract String getCurrentUserLanguageCode(String paramString1, String paramString2);

	public abstract SysLanguage getSysLanguageById(String paramString);

	public abstract SysLanguage getSysLanguageByCode(String paramString);
}
