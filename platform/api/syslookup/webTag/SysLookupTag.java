package com.piedpiper.platform.api.syslookup.webTag;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import com.piedpiper.platform.api.syslookup.SysLookupAPI;
import com.piedpiper.platform.api.syslookup.dto.SysLookupSimpleVo;
import com.piedpiper.platform.core.rest.client.RestClientConfig;
import com.piedpiper.platform.core.spring.SpringFactory;

public class SysLookupTag extends TagSupport {
	private static final long serialVersionUID = 4492351355873697481L;
	private PageContext pageContext;
	private String name;

	private SysLookupAPI getSysLookupAPI() {
		return _c._lookupApi;
	}

	private static class _c {
		private static SysLookupAPI _lookupApi = (SysLookupAPI) SpringFactory.getBean(SysLookupAPI.class);
	}

	private String dataOptions = "width:180,editable:false,panelHeight:'auto',onShowPanel:comboboxOnShowPanel";
	private String lookupCode;
	private String defaultValue;
	private String classStyle = "easyui-combobox";

	private boolean isNull;
	private String title;
	private String sid;

	public String getSid() {
		return this.sid;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	private String formatSelect = "<select id=\"%s\" name=\"%s\" class=\"%s\" title= \"%s\" data-options=\"%s\">%s</select>";
	private String formatOption = "<option value=\"%s\" %s>%s</option>";
	private String selected = "SELECTED";
	private String empty = "";

	public int doStartTag() throws JspException {
		JspWriter out = this.pageContext.getOut();
		ServletRequest request = this.pageContext.getRequest();
		String languageCode = "zh_CN";
		if ((request instanceof HttpServletRequest)) {
			Cookie[] cookies = ((HttpServletRequest) request).getCookies();
			if ((cookies != null) && (cookies.length > 0)) {
				for (int i = 0; i < cookies.length; i++) {
					Cookie sCookie = cookies[i];
					if ("P_L_CODE".equals(sCookie.getName())) {
						languageCode = sCookie.getValue();
						break;
					}
				}
			}
		} else {
			Object obj = this.pageContext.getSession().getAttribute("CURRENT_LANGUAGE");
			if (obj != null) {
				languageCode = obj.toString();
			}
		}
		try {
			Collection<SysLookupSimpleVo> values = getSysLookupAPI().getLookUpListByTypeByAppIdWithLg(getLookupCode(),
					RestClientConfig.systemid, languageCode);
			if (getIsNull()) {
				getSysLookupAPI().enhanceLookupcode(values);
			}
			StringBuffer sb = new StringBuffer();
			for (SysLookupSimpleVo vo : values) {
				if (vo.getLookupCode().equals(getDefaultValue())) {
					sb.append(String.format(this.formatOption,
							new Object[] { vo.getLookupCode(), this.selected, vo.getLookupName() }));
				} else
					sb.append(String.format(this.formatOption,
							new Object[] { vo.getLookupCode(), this.empty, vo.getLookupName() }));
			}
			String mid;
			if (getId() == null) {
				mid = getName();
			} else {
				mid = getId();
			}
			if (getTitle() == null) {
				setTitle(getName());
			}
			out.print(String.format(this.formatSelect,
					new Object[] { mid, getName(), getClassStyle(), getTitle(), getDataOptions(), sb.toString() }));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return super.doStartTag();
	}

	public PageContext getPageContext() {
		return this.pageContext;
	}

	public void setPageContext(PageContext pageContext) {
		this.pageContext = pageContext;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDataOptions() {
		return this.dataOptions;
	}

	public void setDataOptions(String dataOptions) {
		this.dataOptions = dataOptions;
	}

	public String getLookupCode() {
		return this.lookupCode;
	}

	public void setLookupCode(String lookupCode) {
		this.lookupCode = lookupCode;
	}

	public String getDefaultValue() {
		return this.defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getClassStyle() {
		return this.classStyle;
	}

	public void setClassStyle(String classStyle) {
		this.classStyle = classStyle;
	}

	public boolean getIsNull() {
		return this.isNull;
	}

	public void setIsNull(boolean isNull) {
		this.isNull = isNull;
	}
}
