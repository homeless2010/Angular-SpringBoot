package com.piedpiper.platform.commons.utils;

import com.piedpiper.platform.commons.utils.id.UUIDHexGenerator;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ComUtil {
	static Logger log = LoggerFactory.getLogger(ComUtil.class);

	private static final String DATE_STYLE = "yyyy-MM-dd";
	private static final String TIME_STYLE = "yyyy-MM-dd HH:mm:ss";
	public static String EMPTY = "";
	public static String SEMICOLON = ";";

	private static final String EXT = "_ext";

	private static final String SUFFIX = ".jsp";

	public static String getId() {
		UUIDHexGenerator uuid = new UUIDHexGenerator();
		return uuid.generate();
	}

	public static String replaceNull2Space(String s) {
		if (s == null)
			return "";
		if (s.trim().toUpperCase().equals("NULL"))
			return "";
		return s.trim();
	}

	public static String Date2String(Date dt) {
		if ((dt == null) || (dt.equals("")))
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.format(dt);
		} catch (Exception ex) {
			log.error("==ComUtil:Date2String==：" + ex);
		}
		return "";
	}

	public static String Time2String(Date dt) {
		if (dt == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.format(dt);
		} catch (Exception ex) {
			log.error("==ComUtil:Time2String==：" + ex);
		}
		return "";
	}

	public static Date String2Time(String time) {
		if ("".equals(replaceNull2Space(time))) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = null;
		if ((time != null) && (!time.equals(""))) {
			try {
				d = format.parse(time);
			} catch (Exception ex) {
				log.error("==ComUtil:String2Time==：" + ex);
			}
		}
		return d;
	}

	public static Date String2Date(String date) {
		if ("".equals(replaceNull2Space(date))) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		if ((date != null) && (!date.equals(""))) {
			try {
				d = format.parse(date);
			} catch (Exception ex) {
				log.error("==ComUtil:String2Date==：" + ex);
			}
		}
		return d;
	}

	public static String formatDateString(String oldDate) {
		String newDate = "";
		if ((oldDate.length() >= 8) && (oldDate.length() <= 10)) {
			String s = "";
			String st = "";
			int nos = 0;
			char c = ' ';
			try {
				for (int i = 0; i < oldDate.length(); i++) {
					if (!Character.isDigit(oldDate.charAt(i))) {
						c = oldDate.charAt(i);
						s = oldDate.substring(i, i + 1);
						if ("".equals(st)) {
							st = s;
							nos++;
						} else if (st.equals(s)) {
							nos++;
						} else {
							s = "";
							nos = 0;
							break;
						}
					}
				}

				if ((!"".equals(s)) && (nos == 2)) {
					String new_yyyy = "";
					String new_mm = "";
					String new_dd = "";
					String[] new_date = new String[6];
					String[] mm = { "JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST",
							"SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER" };

					if ((oldDate.split("/").length == 3) && (oldDate.split("/")[2].length() == 4)
							&& (Integer.parseInt(oldDate.split("/")[1].toString()) < 13)
							&& (Integer.parseInt(oldDate.split("/")[0].toString()) < 32) && (oldDate.length() == 10)) {

						String[] tmp = oldDate.split("/");
						new_yyyy = tmp[2];
						new_mm = tmp[1];
						new_dd = tmp[0];
						Date x = new Date();
						String[] datestr = x.toString().split("\\s");
						for (int i = 0; i < datestr.length; i++) {
							if (datestr[i].length() == 8) {
								datestr[i] = "00:00:00";
							}
							new_date[i] = datestr[i];
						}
					} else {
						oldDate = oldDate.replace(c, '-');
						String[] xxx = oldDate.split("-");
						if (xxx[0].length() == 1) {
							xxx[0] = ("0" + xxx[0]);
						} else if ((xxx[0].length() == 2) || (xxx[0].length() == 4)) {
							xxx[0] = xxx[0];
						} else {
							newDate = "errorDate";
						}
						if (xxx[1].length() == 1) {
							xxx[1] = ("0" + xxx[1]);
						} else if (xxx[1].length() == 2) {
							xxx[1] = xxx[1];
						} else {
							newDate = "errorDate";
						}
						if (xxx[2].length() == 1) {
							xxx[2] = ("0" + xxx[2]);
						} else if ((xxx[2].length() == 2) || (xxx[2].length() == 4)) {
							xxx[2] = xxx[2];
						} else {
							newDate = "errorDate";
						}
						if ("".equals(newDate)) {
							oldDate = xxx[0] + "-" + xxx[1] + "-" + xxx[2];
							Date x = new Date();
							String[] datestr = x.toString().split("\\s");
							for (int i = 0; i < datestr.length; i++) {
								if (datestr[i].length() == 8) {
									datestr[i] = "00:00:00";
								}
								new_date[i] = datestr[i];
							}
							String[] str = oldDate.split("-");
							if ((str.length == 3) && (str[0].length() == 4)) {
								new_yyyy = str[0];
								new_mm = str[1];
								new_dd = str[2];
							} else if ((str.length == 3) && (str[2].length() == 4)) {
								new_yyyy = str[2];
								new_mm = str[0];
								new_dd = str[1];
								Date dat = String2Date(new_yyyy + "-" + new_mm + "-" + new_dd);
								String change = Date2String(dat);
								int resy = oldDate.indexOf("-" + change.substring(0, 4));

								int resm = oldDate.indexOf(change.substring(5, 8));

								int resd = oldDate.indexOf(change.substring(7, 10) + "-");

								if ((resy == -1) || (resm == -1) || (resd == -1) || (Integer.parseInt(new_mm) > 12)) {
									new_mm = str[1];
									new_dd = str[0];
								}
								if ((change.substring(5, 7).equals(new_mm)) || (change.substring(8, 10).equals(new_dd))
										|| (change.substring(8, 10).equals(new_mm))
										|| (change.substring(5, 7).equals(new_mm))) {

									String chk = Date2String(dat);
									if (!chk.equals(new_yyyy + "-" + new_mm + "-" + new_dd)) {
										newDate = "errorDate";
									}
								} else {
									newDate = "errorDate";
								}
							} else {
								newDate = "errorDate";
							}
						}
					}
					if ("".equals(newDate)) {
						try {
							new_mm = mm[(Integer.parseInt(new_mm) - 1)].substring(0, new_date[1].length());

							String oldstr = new_date[0] + " " + new_mm + " " + Integer.parseInt(new_dd) + "" + " "
									+ new_date[3] + " " + new_date[4] + " " + new_yyyy;

							Date date = new Date(oldstr);
							SimpleDateFormat formater = new SimpleDateFormat();
							formater.applyPattern("yyyy-MM-dd");
							newDate = formater.format(date);
						} catch (Exception ex) {
							newDate = "errorDate";
						}
					}
				} else if ((oldDate.length() == 8) && ("".equals(s))) {
					int digitnum = 0;
					for (int i = 0; i < oldDate.length(); i++) {
						if (Character.isDigit(oldDate.charAt(i))) {
							digitnum++;
						}
					}
					if (digitnum == oldDate.length()) {
						String new_yyyy = oldDate.substring(0, 4);
						String new_mm = oldDate.substring(4, 6);
						String new_dd = oldDate.substring(6, 8);
						newDate = new_yyyy + "-" + new_mm + "-" + new_dd;
					} else {
						newDate = "errorDate";
					}
				} else {
					newDate = "errorDate";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			newDate = "errorDate";
		}
		return newDate;
	}

	public static String str2HexAscii(String s) {
		String str = "";
		if ((s == null) || (s.trim().equals("")))
			return str;
		for (int i = 0; i < s.length(); i++) {

			byte[] bytes = String.valueOf(s.charAt(i)).getBytes();
			String s4;
			if (bytes.length == 1) {
				s4 = String.valueOf(s.charAt(i));
			} else {
				int ch = s.charAt(i);
				s4 = "\\u" + Integer.toHexString(ch);
			}
			str = str + s4;
		}
		return str;
	}

	public static String getRequestPath(HttpServletRequest request) {
		return ViewUtil.getRequestPath(request);
	}

	public static String getSqlEscapeStr(String src) {
		String result = "";
		if ((src != null) && (!src.equals(""))) {
			result = StringEscapeUtils.escapeSql(src);
		}
		return result;
	}

	public static String getHtmlEscapeStr(String src) {
		String result = "";
		if ((src != null) && (!src.equals(""))) {
			result = StringEscapeUtils.escapeHtml(src);
		}
		return result;
	}

	public static String getJavaScriptEscapeStr(String src) {
		String result = "";
		if ((src != null) && (!src.equals(""))) {
			result = StringEscapeUtils.escapeJavaScript(src);
		}
		return result;
	}

	public static int getPageSize(String pageSize) {
		return Integer.parseInt((pageSize == null) || (pageSize == "0") ? "20" : pageSize);
	}

	public static int getPageNo(String pageNo) {
		return Integer.parseInt((pageNo == null) || (pageNo == "0") ? "1" : pageNo);
	}

	public static ByteArrayOutputStream byte2OutputStream(byte[] bytes) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			ByteArrayInputStream in = new ByteArrayInputStream(bytes);
			int BUFFER_SIZE = 1024;
			byte[] data = new byte[BUFFER_SIZE];
			int count = -1;
			while ((count = in.read(data, 0, BUFFER_SIZE)) != -1) {
				out.write(data, 0, count);
			}
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
			log.error("==ComUtil:byte2OutputStream==：" + ex);
		}
		return out;
	}

	public static String getExtView(String viewName) {
		String filePath = viewName + "_ext";
		String temp = System.getProperty("avicit_platform.root");
		if (!temp.endsWith(File.separator)) {
			temp = temp + File.separator;
		}
		String filer = temp + filePath.replaceAll("/", "\\\\") + ".jsp";
		File file = new File(filer);
		if (file.exists()) {
			return filePath;
		}
		return viewName;
	}

	public static Object convertMap(Class type, Map map)
			throws IntrospectionException, IllegalAccessException, InstantiationException, InvocationTargetException {
		BeanInfo beanInfo = Introspector.getBeanInfo(type);
		Object obj = type.newInstance();

		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();

			if (map.containsKey(propertyName)) {
				Object value = map.get(propertyName);

				Object[] args = new Object[1];
				args[0] = value;

				descriptor.getWriteMethod().invoke(obj, args);
			}
		}
		return obj;
	}

	public static byte[] blobToBytes(Blob blob) {
		BufferedInputStream is = null;
		try {
			is = new BufferedInputStream(blob.getBinaryStream());
			byte[] bytes = new byte[(int) blob.length()];
			int len = bytes.length;
			int offset = 0;
			int read = 0;
			while ((offset < len) && ((read = is.read(bytes, offset, len - offset)) >= 0)) {
				offset += read;
			}
			return bytes;
		} catch (Exception e) {
			int len;
			return null;
		} finally {
			try {
				is.close();
				is = null;
			} catch (Exception e) {
				return null;
			}
		}
	}

	public static String getJavaNameFromDBColumnName(String s) {
		String string = toUpperCamelCase(s);
		return Introspector.decapitalize(string);
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

	public static int getHashInt(String id, int modNum) {
		int i = 0;
		i = id.hashCode();
		i = i < 0 ? -i : i;
		i %= modNum;
		i++;
		return i;
	}
}
