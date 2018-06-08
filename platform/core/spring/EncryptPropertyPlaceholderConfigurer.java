package com.piedpiper.platform.core.spring;

import com.piedpiper.platform.commons.utils.DESUtils;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
	private String[] encryptPropNames = { "bonecp0.password", "redis.password" };

	protected String convertProperty(String propertyName, String propertyValue) {
		if (isEncryptProp(propertyName)) {
			String decryptValue = DESUtils.decryptBasedDes(propertyValue);
			return decryptValue;
		}
		return propertyValue;
	}

	private boolean isEncryptProp(String propertyName) {
		for (String encryptName : this.encryptPropNames) {
			if (encryptName.equals(propertyName)) {
				return true;
			}
		}
		return false;
	}
}
