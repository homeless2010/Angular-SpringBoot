package com.piedpiper.platform.core.spring;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;

import org.springframework.web.util.HtmlUtils;

public class XssObjectMapper extends ObjectMapper {
	private static final long serialVersionUID = -3448961813323784217L;

	public XssObjectMapper() {
		SimpleModule module = new SimpleModule("HTML XSS Serializer",
				new Version(1, 0, 0, "FINAL", "com.avicit", "ep-jsonmodule"));

		module.addSerializer(new JsonHtmlXssSerializer());
		registerModule(module);
	}

	class JsonHtmlXssSerializer extends JsonSerializer<String> {
		public Class<String> handledType() {
			return String.class;
		}

		public void serialize(String value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
				throws IOException, JsonProcessingException {
			if (value != null) {
				String encodedValue = HtmlUtils.htmlEscape(value + "");
				jsonGenerator.writeString(encodedValue);
			}
		}
	}
}
