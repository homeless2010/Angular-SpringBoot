package com.piedpiper.platform.api.portal.utils;

import com.piedpiper.platform.api.portal.dto.LayoutModel;
import com.piedpiper.platform.api.portal.dto.ResultModel;
import com.piedpiper.platform.api.portal.dto.SysPortletConfig;
import com.piedpiper.platform.api.sysuser.dto.SysRole;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import com.piedpiper.platform.api.portal.dto.LayoutModel;
import com.piedpiper.platform.api.portal.dto.ResultModel;
import com.piedpiper.platform.api.portal.dto.SysPortletConfig;
import com.piedpiper.platform.api.sysuser.dto.SysRole;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class SysPortletUtil {
	public static SysPortletUtil getInstance() {
		return _class.instance;
	}

	public String getLayoutContent(HashMap<String, String> layoutContents) {
		Iterator iterator = layoutContents.entrySet().iterator();
		String content = "";
		if (iterator.hasNext()) {
			Map.Entry entry = (Map.Entry) iterator.next();
			String key = (String) entry.getKey();
			content = (String) layoutContents.get(key);
			layoutContents.remove(key);
			return content;
		}
		return "";
	}

	public List<ResultModel> getLayoutTemplateForString(String xmlStr) {
		Document doc = readForString(xmlStr);
		List columns = new ArrayList();
		if ((doc != null) && (doc.getRootElement() != null)) {
			Iterator columnsIt = doc.getRootElement().elementIterator();
			while (columnsIt.hasNext()) {
				Element columnsElement = (Element) columnsIt.next();
				if (columnsElement.getName().equals("layout-columns")) {
					Iterator columnIt = columnsElement.elementIterator();
					while (columnIt.hasNext()) {
						Element columnElement = (Element) columnIt.next();
						if (columnElement.getName().equals("layout-column")) {
							ResultModel resultModel = new ResultModel();
							resultModel.setWidth(columnElement.attribute("width").getValue());
							Iterator portletsIt = columnElement.elementIterator();
							ArrayList portlets = new ArrayList();
							while (portletsIt.hasNext()) {
								Element portletElement = (Element) portletsIt.next();
								if (portletElement.getName().equals("portlet")) {
									portlets.add(portletElement.getText());
								}
							}
							resultModel.setPortletIds(portlets);
							columns.add(resultModel);
						}
					}
				}
			}
		}
		return columns;
	}

	public ArrayList<HashMap<String, String>> getLayoutTemplateForFile(String layoutTemlateName) throws Exception {
		return getLayoutTemplateForFile(layoutTemlateName, "");
	}

	public ArrayList<HashMap<String, String>> getLayoutTemplateForFile(String layoutTemplateName, String flag)
			throws Exception {
		Document doc = readForFile(layoutTemplateName);
		return getLayoutTemplateList(doc, flag);
	}

	public String getLayoutTemplateAttributeNameForTemplatePath(String layoutTemplatePath) throws Exception {
		Document doc = readForFile(layoutTemplatePath);
		if ((doc != null) && (doc.getRootElement() != null)) {
			return doc.getRootElement().attributeValue("name");
		}
		return "";
	}

	public String getLayoutTemplateAttributeEnNameForTemplatePath(String layoutTemplatePath) throws Exception {
		Document doc = readForFile(layoutTemplatePath);
		if ((doc != null) && (doc.getRootElement() != null)) {
			return doc.getRootElement().attributeValue("enName");
		}
		return "";
	}

	public String getLayoutTemplateAttributeNameForStringXml(String strXml) throws Exception {
		Document doc = readForString(strXml);
		if ((doc != null) && (doc.getRootElement() != null)) {
			return doc.getRootElement().attributeValue("name");
		}
		return "";
	}

	private ArrayList<HashMap<String, String>> getLayoutTemplateList(Document doc, String flag) {
		ArrayList columnsMap = new ArrayList();
		if ((doc != null) && (doc.getRootElement() != null)) {
			Iterator it = doc.getRootElement().elementIterator();
			while (it.hasNext()) {
				Element element = (Element) it.next();
				if (element.getName().equals("layout-columns")) {
					HashMap columnMap = new HashMap();
					Iterator itt = element.elementIterator();
					while (itt.hasNext()) {
						Element elementitt = (Element) itt.next();
						if (elementitt.getName().equals("layout-column")) {
							Attribute attributeWidth = elementitt.attribute("width");
							Attribute attributeId = elementitt.attribute("id");
							if (attributeId != null) {
								columnMap.put(attributeId.getValue(), attributeWidth.getValue());
							}
						}
					}
					columnsMap.add(columnMap);
				}
			}
		}

		if (flag.trim().length() > 0) {
			for (int i = 0; i < 20; ++i) {
				columnsMap.add(columnsMap.get(columnsMap.size() - 1));
			}
		}
		return columnsMap;
	}

	public String getLayoutTemplateXmlContent(String layoutTemplateName) throws Exception {
		Document doc = readForFile(layoutTemplateName);
		return doc.asXML();
	}

	public Document readForFile(String layoutTemlateName) throws Exception {
		SAXReader saxreader = new SAXReader();
		try {
			return saxreader.read(layoutTemlateName);
		} catch (Exception e) {
			throw new RuntimeException("环境路径不能有空格或中文" + layoutTemlateName + e.getMessage());
		}
	}

	private Document readForString(String xmlStr) {
		try {
			return DocumentHelper.parseText(xmlStr);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}

	public SysPortletConfig getSysPortletConfig(List<SysPortletConfig> portletConfigs,
			List<SysRole> currentUserSysRoles) {
		SysPortletConfig result = null;
		int flag = 2147483647;

		for (Iterator i$ = currentUserSysRoles.iterator(); i$.hasNext();) {
			SysRole sysRole;
			sysRole = (SysRole) i$.next();
			for (SysPortletConfig sysPortletConfig : portletConfigs)
				if ((sysPortletConfig != null) && (StringUtils
						.containsIgnoreCase(sysPortletConfig.getLayoutExtends().trim(), sysRole.getRoleName().trim()))
						&& (sysPortletConfig.getOrderBy() < flag)) {
					flag = sysPortletConfig.getOrderBy();
					result = sysPortletConfig;
				}
		}
		return result;
	}

	public List<LayoutModel> getPortalLayout(String layoutTemplateName) throws Exception {
		Document doc = readForFile(layoutTemplateName);
		List plList = new ArrayList();
		LayoutModel lm = null;
		if ((doc != null) && (doc.getRootElement() != null)) {
			String layoutId = doc.getRootElement().attributeValue("enName");
			String layoutName = doc.getRootElement().attributeValue("name");
			Iterator columnsIt = doc.getRootElement().elementIterator();
			while (columnsIt.hasNext()) {
				Element columnsElement = (Element) columnsIt.next();
				if (columnsElement.getName().equals("layout-columns")) {
					String rowId = columnsElement.attribute("id").getValue() + "";
					lm = new LayoutModel();
					lm.setLayoutId(layoutId);
					lm.setRowId(rowId);
					lm.setLayoutName(layoutName);
					lm.setFileName(layoutId + ".xml");
					List columWidthList = new ArrayList();
					ArrayList layouts = getLayoutTemplateList(doc, "");
					Iterator columnIt = columnsElement.elementIterator();
					while (columnIt.hasNext()) {
						Element columnElement = (Element) columnIt.next();
						if (columnElement.getName().equals("layout-column")) {
							String width = columnElement.attribute("width").getValue() + "";
							columWidthList.add(width);
						}
					}
					lm.setColumWidth(columWidthList);
					lm.setLayouts(layouts);
					plList.add(lm);
				}
			}
		}
		return plList;
	}

	private static class _class {
		private static SysPortletUtil instance = new SysPortletUtil();
	}
}