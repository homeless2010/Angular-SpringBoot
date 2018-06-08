package com.piedpiper.platform.core.spring;

import java.util.Map;

public abstract interface PlatformPerformancePersistenceInterface {
	public abstract void saveMethodPerformanceToDB(Map<String, Object> paramMap);

	public abstract void saveExceptionInfoToDB(Map<String, Object> paramMap);
}
