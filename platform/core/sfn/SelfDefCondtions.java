package com.piedpiper.platform.core.sfn;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class SelfDefCondtions implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String text;
	private String state;
	private String iconCls;
	private List<SelfDefCondtions> children;
	private Map<String, Object> attributes;
	private String _pid;
	private Object target;
	private String myk;
	private String dataType;
	private String domId;
	private boolean checked;
	private String ruleOpt;
	private String ruleName;
	private Object textValue;
	private String alias;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIconCls() {
		return this.iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public List<SelfDefCondtions> getChildren() {
		return this.children;
	}

	public void setChildren(List<SelfDefCondtions> children) {
		this.children = children;
	}

	public Map<String, Object> getAttributes() {
		return this.attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public String get_pid() {
		return this._pid;
	}

	public void set_pid(String _pid) {
		this._pid = _pid;
	}

	public Object getTarget() {
		return this.target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

	public String getMyk() {
		return this.myk;
	}

	public void setMyk(String myk) {
		this.myk = myk;
	}

	public String getDataType() {
		return this.dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getDomId() {
		return this.domId;
	}

	public void setDomId(String domId) {
		this.domId = domId;
	}

	public boolean isChecked() {
		return this.checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getRuleOpt() {
		return this.ruleOpt;
	}

	public void setRuleOpt(String ruleOpt) {
		this.ruleOpt = ruleOpt;
	}

	public String getRuleName() {
		return this.ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public Object getTextValue() {
		return this.textValue;
	}

	public void setTextValue(Object textValue) {
		this.textValue = textValue;
	}

	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
}
