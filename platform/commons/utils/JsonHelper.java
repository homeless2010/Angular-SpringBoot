package com.piedpiper.platform.commons.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.Serializable;
import java.text.DateFormat;

public class JsonHelper extends ObjectMapper implements Serializable {
	private static final long serialVersionUID = 1L;

	public static JsonHelper getInstance() {
		return _class.instance;
	}

	public static JsonHelper getBaseInstance() {
		return _class.baseInstance;
	}

	public String writeValueAsString(Object value) {
		try {
			return super.writeValueAsString(value);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public <T> T readValue(String content, TypeReference valueTypeRef) {
		try {
			return super.readValue(content, valueTypeRef);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public <T> T readValue(String content, Class<T> calss) {
		try {
			return super.readValue(content, calss);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public <T> T readValue(String content, DateFormat dateFormat, TypeReference valueTypeRef) {
		try {
			super.setDateFormat(dateFormat);

			return super.readValue(content, valueTypeRef);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public <T> T transformDto(Object object, TypeReference<?> typeReference) {
		String json = writeValueAsString(object);
		return readValue(json, typeReference);
	}

	static {
		_class.instance.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		_class.baseInstance.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	private static class _class {
		private static JsonHelper instance = new JsonHelper();
		private static JsonHelper baseInstance = new JsonHelper();
	}
}