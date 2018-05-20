package com.piedpiper.platform.core.dao;

import com.piedpiper.platform.commons.utils.reflection.ConvertUtils;
import com.piedpiper.platform.commons.utils.web.ServletUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

public class PropertyFilter {
	public static final String OR_SEPARATOR = "_OR_";

	public static enum MatchType {
		EQ, LIKE, LT, GT, BT, LE, GE, ISNULL, ISNOTNULL;

		private MatchType() {
		}
	}

	public static enum PropertyType {
		S(String.class), I(Integer.class), L(Long.class), N(Double.class), D(Date.class), B(Boolean.class);

		private Class<?> clazz;

		private PropertyType(Class<?> clazz) {
			this.clazz = clazz;
		}

		public Class<?> getValue() {
			return this.clazz;
		}
	}

	private MatchType matchType = null;
	private Object matchValue = null;

	private Class<?> propertyClass = null;
	private String[] propertyNames = null;

	public void setPropertyNames(String[] propertyNames) {
		this.propertyNames = propertyNames;
	}

	public PropertyFilter() {
	}

	public PropertyFilter(String filterName, String value) {
		String firstPart = StringUtils.substringBefore(filterName, "_");
		String matchTypeCode = StringUtils.substring(firstPart, 0, firstPart.length() - 1);
		String propertyTypeCode = StringUtils.substring(firstPart, firstPart.length() - 1, firstPart.length());
		try {
			this.matchType = ((MatchType) Enum.valueOf(MatchType.class, matchTypeCode));
		} catch (RuntimeException e) {
			throw new IllegalArgumentException("filter名称" + filterName + "没有按规则编写,无法得到属性比较类型.", e);
		}
		try {
			this.propertyClass = ((PropertyType) Enum.valueOf(PropertyType.class, propertyTypeCode)).getValue();
		} catch (RuntimeException e) {
			throw new IllegalArgumentException("filter名称" + filterName + "没有按规则编写,无法得到属性值类型.", e);
		}

		String propertyNameStr = StringUtils.substringAfter(filterName, "_");
		Assert.isTrue(StringUtils.isNotBlank(propertyNameStr), "filter名称" + filterName + "没有按规则编写,无法得到属性名称.");
		this.propertyNames = StringUtils.splitByWholeSeparator(propertyNameStr, "_OR_");

		this.matchValue = ConvertUtils.convertStringToObject(value, this.propertyClass);
	}

	public PropertyFilter(String filterName, Object value) {
		String firstPart = StringUtils.substringBefore(filterName, "_");
		String matchTypeCode = firstPart;

		try {
			this.matchType = ((MatchType) Enum.valueOf(MatchType.class, matchTypeCode));
		} catch (RuntimeException e) {
			throw new IllegalArgumentException("filter名称" + filterName + "没有按规则编写,无法得到属性比较类型.", e);
		}
		try {
			this.propertyClass = value.getClass();
		} catch (RuntimeException e) {
			throw new IllegalArgumentException("filter名称" + filterName + "没有按规则编写,无法得到属性值类型.", e);
		}

		String propertyNameStr = StringUtils.substringAfter(filterName, "_");
		Assert.isTrue(StringUtils.isNotBlank(propertyNameStr), "filter名称" + filterName + "没有按规则编写,无法得到属性名称.");
		this.propertyNames = StringUtils.splitByWholeSeparator(propertyNameStr, "_OR_");

		this.matchValue = value;
	}

	public static List<PropertyFilter> buildFromHttpRequest(HttpServletRequest request) {
		return buildFromHttpRequest(request, "filter");
	}

	public static List<PropertyFilter> buildFromHttpRequest(HttpServletRequest request, String filterPrefix) {
		List<PropertyFilter> filterList = new ArrayList();

		Map<String, Object> filterParamMap = ServletUtils.getParametersStartingWith(request, filterPrefix + "_");

		for (Map.Entry<String, Object> entry : filterParamMap.entrySet()) {
			String filterName = (String) entry.getKey();
			String value = (String) entry.getValue();

			if (StringUtils.isNotBlank(value)) {
				PropertyFilter filter = new PropertyFilter(filterName, value);
				filterList.add(filter);
			}
		}

		return filterList;
	}

	public static List<PropertyFilter> buildFromParameterMap(Map<String, Object> parameter) {
		return buildFromParameterMap(parameter, "filter");
	}

	public static List<PropertyFilter> buildFromParameterMap(Map<String, Object> parameter, String filterPrefix) {
		if (parameter == null) {
			return null;
		}
		List<PropertyFilter> filterList = new ArrayList();

		Map<String, Object> filterParamMap = getParametersStartingWith(parameter, filterPrefix + "_");

		for (Map.Entry<String, Object> entry : filterParamMap.entrySet()) {
			String filterName = (String) entry.getKey();
			Object value = entry.getValue();

			if (value != null) {
				PropertyFilter filter = new PropertyFilter(filterName, value);
				filterList.add(filter);
			}
		}

		return filterList;
	}

	public static Map<String, Object> getParametersStartingWith(Map<String, Object> parameter, String prefix) {
		Assert.notNull(parameter, "parameter must not be null");
		Map<String, Object> params = new TreeMap();
		if (prefix == null) {
			prefix = "";
		}
		Set<String> key = parameter.keySet();
		for (Iterator<?> it = key.iterator(); it.hasNext();) {
			String paramName = (String) it.next();
			if (("".equals(prefix)) || (paramName.startsWith(prefix))) {
				String unprefixed = paramName.substring(prefix.length());
				Object values = parameter.get(paramName);
				if (values != null) {

					params.put(unprefixed, values);
				}
			}
		}
		return params;
	}

	public Class<?> getPropertyClass() {
		return this.propertyClass;
	}

	public MatchType getMatchType() {
		return this.matchType;
	}

	public Object getMatchValue() {
		return this.matchValue;
	}

	public String[] getPropertyNames() {
		return this.propertyNames;
	}

	public String getPropertyName() {
		return this.propertyNames[0];
	}

	public boolean hasMultiProperties() {
		return this.propertyNames.length > 1;
	}
}
