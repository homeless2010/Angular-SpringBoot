package com.piedpiper.platform.api.portal.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.piedpiper.platform.core.domain.BeanDTO;
import com.piedpiper.platform.core.properties.PlatformConstant;

public class LayoutModel extends BeanDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String layoutId;
	private String rowId;
	private List<String> columWidth;
	private String layoutName;
	private String fileName;
	private ArrayList<HashMap<String, String>> layouts;

	public String getLayoutId() {
		return this.layoutId;
	}

	public void setLayoutId(String layoutId) {
		this.layoutId = layoutId;
	}

	public String getRowId() {
		return this.rowId;
	}

	public void setRowId(String rowId) {
		this.rowId = rowId;
	}

	public List<String> getColumWidth() {
		return this.columWidth;
	}

	public void setColumWidth(List<String> columWidth) {
		this.columWidth = columWidth;
	}

	public String getLayoutName() {
		return this.layoutName;
	}

	public void setLayoutName(String layoutName) {
		this.layoutName = layoutName;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public ArrayList<HashMap<String, String>> getLayouts() {
		return this.layouts;
	}

	public void setLayouts(ArrayList<HashMap<String, String>> layouts) {
		this.layouts = layouts;
	}

	public String getLogFormName() {
		return null;
	}

	public String getLogTitle() {
		return null;
	}

	public PlatformConstant.LogType getLogType() {
		return null;
	}
}
