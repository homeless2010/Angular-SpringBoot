package com.piedpiper.platform.api.syscoding;

import com.piedpiper.platform.api.syscoding.dto.SysCodingComSegment;
import com.piedpiper.platform.api.syscoding.dto.SysCodingComSegmentValues;
import com.piedpiper.platform.api.syscoding.dto.SysCodingRuleBase;
import com.piedpiper.platform.api.syscoding.dto.SysCodingRuleSegment;
import java.util.List;

public abstract interface SysCodingAPI {
	public abstract String createSelectCoding(String paramString1, String paramString2, String paramString3,
			String paramString4, String paramString5) throws Exception;

	public abstract String createSelectCoding(String paramString1, String paramString2, String paramString3,
			String paramString4, String paramString5, boolean paramBoolean) throws Exception;

	public abstract List<SysCodingRuleBase> getCodingRuleList(String paramString);

	public abstract String getCodingRuleListJson(String paramString, boolean paramBoolean);

	public abstract SysCodingRuleBase getCodingRuleBaseById(String paramString);

	public abstract SysCodingRuleBase getCodingRuleBaseByCode(String paramString1, String paramString2);

	public abstract boolean deleteCodingUsedInfoByFormId(String paramString);

	public abstract List<SysCodingRuleSegment> findRuleSegment(String paramString);

	public abstract List<SysCodingComSegment> findComSegmentList(String paramString);

	public abstract SysCodingComSegment getComSegmentById(String paramString);

	public abstract List<SysCodingComSegmentValues> findComSegmentValues(String paramString);
}
