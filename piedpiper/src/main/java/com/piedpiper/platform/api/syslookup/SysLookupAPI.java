package com.piedpiper.platform.api.syslookup;

import com.piedpiper.platform.api.syslookup.dto.SysLookupSimpleVo;
import com.piedpiper.platform.api.syslookup.dto.SysSecretRelation;
import java.util.Collection;
import java.util.List;

public abstract interface SysLookupAPI {
	public abstract void reLoad() throws Exception;

	public abstract boolean containsLookupType(String paramString);

	@Deprecated
	public abstract Collection<SysLookupSimpleVo> getLookUpListByType(String paramString);

	public abstract Collection<SysLookupSimpleVo> getLookUpListByTypeByAppId(String paramString1, String paramString2);

	public abstract String getNameByLooupTypeCodeAndLooupCode(String paramString1, String paramString2);

	public abstract String getNameByLooupTypeCodeAndLooupCodeByAppId(String paramString1, String paramString2,
			String paramString3);

	public abstract Collection<SysLookupSimpleVo> enhanceLookupcode(Collection<SysLookupSimpleVo> paramCollection);

	public abstract boolean isRelation(String paramString1, String paramString2);

	public abstract SysSecretRelation getSecretRelation(String paramString1, String paramString2);

	public abstract List<String> getSecretWordList(String paramString);

	public abstract Collection<SysLookupSimpleVo> getLookUpListByTypeByAppIdWithLg(String paramString1,
			String paramString2, String paramString3);

	public abstract Collection<SysLookupSimpleVo> getLookUpListByType(String paramString1, String paramString2,
			String paramString3);

	public abstract String getNameByLooupTypeCodeAndLooupCodeByAppId(String paramString1, String paramString2,
			String paramString3, String paramString4);
}
