package com.piedpiper.platform.commons.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;

public class JsonUtil {
	public static List<Map<String, Object>> parseJSON2List(String jsonStr) {
		if ((jsonStr == null) || (jsonStr.equals("")))
			return null;
		try {
			return ((List) JsonHelper.getInstance().readValue(jsonStr, new TypeReference() {
			}));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Map<String, Object> parseJSON2Map(String jsonStr) {
		if ((jsonStr == null) || (jsonStr.equals("")))
			return null;
		try {
			return ((Map) JsonHelper.getInstance().readValue(jsonStr, new TypeReference() {
			}));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<Map<String, Object>> getListByUrl(String url) {
		try {
			InputStream in = new URL(url).openStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder sb = new StringBuilder();

			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			return parseJSON2List(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Map<String, Object> getMapByUrl(String url) {
		try {
			InputStream in = new URL(url).openStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			return parseJSON2Map(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}