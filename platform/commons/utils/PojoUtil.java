package com.piedpiper.platform.commons.utils;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.piedpiper.platform.core.domain.BeanBase;
import com.piedpiper.platform.core.domain.BeanDTO;
import com.piedpiper.platform.core.exception.DaoException;
import com.piedpiper.platform.core.exception.UtilsException;
import com.piedpiper.platform.core.filter.session.RedisSessionService;
import com.piedpiper.platform.core.properties.PlatformConstant;
import com.piedpiper.platform.core.shiroSecurity.contextThread.ContextCommonHolder;

public class PojoUtil {
	static Logger logger = LoggerFactory.getLogger(PojoUtil.class);

	private static final long VERSION = 0L;

	public static String getJavaNameFromDBColumnName(String s) {
		String string = toUpperCamelCase(s);
		return Introspector.decapitalize(string);
	}

	public static Map<?, ?> toMap(Object bean)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Assert.notNull(bean, "参数 bean 不能为空");
		return PropertyUtils.describe(bean);
	}

	public static Object Map2Pojo(Map<String, Object> map, Object target) throws UtilsException {
		try {
			for (Map.Entry<String, Object> s : map.entrySet()) {
				if (s.getValue() != null) {
					String objName = getJavaNameFromDBColumnName((String) s.getKey());
					Object objValue = s.getValue();
					PropertyDescriptor propDes = BeanUtils.getPropertyDescriptor(target.getClass(), objName);

					if ((propDes != null) && (propDes.getPropertyType() == BigDecimal.class)) {
						if (objValue != null) {

							BigDecimal big = new BigDecimal(objValue.toString());
							PropertyUtils.setProperty(target, objName, big);
						}
					} else
						PropertyUtils.setProperty(target, objName, objValue);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new UtilsException(e.getMessage(), e);
		}
		return target;
	}

	public static void copyProperties(Object toObj, Object fromObj) throws UtilsException {
		Assert.notNull(toObj, "参数 toObj 不能为空");
		Assert.notNull(fromObj, "参数 fromObj 不能为空");
		try {
			PropertyUtils.copyProperties(toObj, fromObj);
		} catch (Exception e) {
			throw new UtilsException(e.getMessage(), e);
		}
	}

	public static void copyProperties(Object toObj, Object fromObj, boolean skipNull) throws UtilsException {
		Assert.notNull(toObj, "参数 toObj 不能为空");
		Assert.notNull(fromObj, "参数 fromObj 不能为空");
		Object dest = toObj;
		Object orig = fromObj;
		PropertyUtilsBean utilsBean = BeanUtilsBean.getInstance().getPropertyUtils();
		if (dest == null) {
			throw new IllegalArgumentException("No destination bean specified");
		}
		if (orig == null) {
			throw new IllegalArgumentException("No origin bean specified");
		}

		if ((orig instanceof DynaBean)) {
			DynaProperty[] origDescriptors = ((DynaBean) orig).getDynaClass().getDynaProperties();
			for (int i = 0; i < origDescriptors.length; i++) {
				String name = origDescriptors[i].getName();
				if ((utilsBean.isReadable(orig, name)) && (utilsBean.isWriteable(dest, name))) {
					try {
						Object value = ((DynaBean) orig).get(name);
						if ((skipNull != true) || (null != value)) {

							if ((dest instanceof DynaBean)) {
								((DynaBean) dest).set(name, value);
							} else
								utilsBean.setSimpleProperty(dest, name, value);
						}
					} catch (NoSuchMethodException e) {
						if (logger.isDebugEnabled()) {
							logger.debug("Error writing to '" + name + "' on class '" + dest.getClass() + "'", e);
							throw new UtilsException(e.getMessage(), e);
						}
					} catch (IllegalAccessException e) {
						logger.debug("Error writing to '" + name + "' on class '" + dest.getClass() + "'", e);
						throw new UtilsException(e.getMessage(), e);
					} catch (InvocationTargetException e) {
						logger.debug("Error writing to '" + name + "' on class '" + dest.getClass() + "'", e);
						throw new UtilsException(e.getMessage(), e);
					}
				}
			}
		} else if ((orig instanceof Map)) {
			Iterator entries = ((Map) orig).entrySet().iterator();
			while (entries.hasNext()) {
				Map.Entry entry = (Map.Entry) entries.next();
				String name = (String) entry.getKey();
				if (utilsBean.isWriteable(dest, name)) {
					try {
						Object value = entry.getValue();
						if ((skipNull != true) || (null != value)) {

							if ((dest instanceof DynaBean)) {
								((DynaBean) dest).set(name, entry.getValue());
							} else
								utilsBean.setSimpleProperty(dest, name, entry.getValue());
						}
					} catch (NoSuchMethodException e) {
						logger.debug("Error writing to '" + name + "' on class '" + dest.getClass() + "'", e);
						throw new UtilsException(e.getMessage(), e);
					} catch (IllegalAccessException e) {
						logger.debug("Error writing to '" + name + "' on class '" + dest.getClass() + "'", e);
						throw new UtilsException(e.getMessage(), e);
					} catch (InvocationTargetException e) {
						logger.debug("Error writing to '" + name + "' on class '" + dest.getClass() + "'", e);
						throw new UtilsException(e.getMessage(), e);
					}
				}
			}
		} else {
			PropertyDescriptor[] origDescriptors = utilsBean.getPropertyDescriptors(orig);
			for (int i = 0; i < origDescriptors.length; i++) {
				String name = origDescriptors[i].getName();
				if ((utilsBean.isReadable(orig, name)) && (utilsBean.isWriteable(dest, name))) {
					try {
						Object value = utilsBean.getSimpleProperty(orig, name);
						if ((skipNull != true) || (null != value)) {

							if ((dest instanceof DynaBean)) {
								((DynaBean) dest).set(name, value);
							} else
								utilsBean.setSimpleProperty(dest, name, value);
						}
					} catch (NoSuchMethodException e) {
						logger.debug("Error writing to '" + name + "' on class '" + dest.getClass() + "'", e);
						throw new UtilsException(e.getMessage(), e);
					} catch (IllegalAccessException e) {
						logger.debug("Error writing to '" + name + "' on class '" + dest.getClass() + "'", e);
						throw new UtilsException(e.getMessage(), e);
					} catch (InvocationTargetException e) {
						logger.debug("Error writing to '" + name + "' on class '" + dest.getClass() + "'", e);
						throw new UtilsException(e.getMessage(), e);
					}
				}
			}
		}
	}

	public static void setSysProperties(BeanBase beanBase, PlatformConstant.OpType type) {
		Assert.notNull(beanBase);
		String sessionId = ContextCommonHolder.getCookeMid();
		Map session = new HashMap();
		if (StringUtils.hasText(sessionId)) {
			session = RedisSessionService.getInstance().getSessionById(sessionId);
		}
		String userId = "";
		String userIp = "";

		if (session.get("userId") == null) {
			userId = "1";
		} else {
			userId = session.get("userId").toString();
		}
		if (session.get("CURRENT_IP") == null) {
			userIp = "127.0.0.1";
		} else {
			userIp = session.get("CURRENT_IP").toString();
		}
		switch (type) {
		case insert:
			beanBase.setCreatedBy(userId);
			beanBase.setCreationDate(new Date());
			beanBase.setLastUpdateDate(new Date());
			beanBase.setLastUpdatedBy(userId);
			beanBase.setLastUpdateIp(userIp);
			break;
		case update:
			beanBase.setLastUpdateDate(new Date());
			beanBase.setLastUpdatedBy(userId);
			beanBase.setLastUpdateIp(userIp);
			break;
		}

	}

	public static void setSysProperties(BeanDTO beanDto, PlatformConstant.OpType type) {
		Assert.notNull(beanDto);
		String sessionId = ContextCommonHolder.getCookeMid();
		Map session = new HashMap();
		if (StringUtils.hasText(sessionId)) {
			session = RedisSessionService.getInstance().getSessionById(sessionId);
		}
		String userId = "";
		String userIp = "";

		if (session.get("userId") == null) {
			userId = "1";
		} else {
			userId = session.get("userId").toString();
		}
		if (session.get("CURRENT_IP") == null) {
			userIp = "127.0.0.1";
		} else {
			userIp = session.get("CURRENT_IP").toString();
		}
		switch (type) {
		case insert:
			beanDto.setCreatedBy(userId);
			beanDto.setCreationDate(new Date());
			beanDto.setLastUpdateDate(new Date());
			beanDto.setLastUpdatedBy(userId);
			beanDto.setLastUpdateIp(userIp);
			beanDto.setVersion(Long.valueOf(0L));
			break;
		case update:
			beanDto.setLastUpdateDate(new Date());
			beanDto.setLastUpdatedBy(userId);
			beanDto.setLastUpdateIp(userIp);
			break;
		}

	}

	public static <T> void setSysProperties(T t, PlatformConstant.OpType type) throws DaoException {
		Assert.notNull(t);
		if ((t instanceof BeanBase)) {
			BeanBase bean = (BeanBase) t;
			setSysProperties(bean, type);
		} else if ((t instanceof BeanDTO)) {
			BeanDTO bean = (BeanDTO) t;
			setSysProperties(bean, type);
		}
	}

	public static String toUpperCamelCase(String s) {
		if ("".equals(s)) {
			return s;
		}
		StringBuffer result = new StringBuffer();

		boolean capitalize = true;
		boolean lastCapital = false;
		boolean lastDecapitalized = false;
		String p = null;
		for (int i = 0; i < s.length(); i++) {
			String c = s.substring(i, i + 1);
			if (("_".equals(c)) || (" ".equals(c)) || ("-".equals(c))) {
				capitalize = true;
			} else {
				if (c.toUpperCase().equals(c)) {
					if ((lastDecapitalized) && (!lastCapital)) {
						capitalize = true;
					}
					lastCapital = true;
				} else {
					lastCapital = false;
				}

				if (capitalize) {
					if ((p == null) || (!p.equals("_"))) {
						result.append(c.toUpperCase());
						capitalize = false;
						p = c;
					} else {
						result.append(c.toLowerCase());
						capitalize = false;
						p = c;
					}
				} else {
					result.append(c.toLowerCase());
					lastDecapitalized = true;
					p = c;
				}
			}
		}
		String r = result.toString();
		return r;
	}
}
