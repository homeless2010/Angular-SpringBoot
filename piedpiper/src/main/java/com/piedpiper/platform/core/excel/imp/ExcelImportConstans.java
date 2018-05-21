package com.piedpiper.platform.core.excel.imp;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class ExcelImportConstans {
	public static final Map<String, String> UPLOAD_FILE = new ConcurrentHashMap();

	private static String ROOT = System.getProperty("avicit_platform.root");

	static {
		if (!ROOT.endsWith(File.separator))
			ROOT += File.separator;
	}

	public static final String EXCEL_TEMPLATE_PATH = ROOT + "static" + File.separator + "sysexceltemplate";

	public static final String EXCEL_UPLOAD_PATH = ROOT + "uploadExcel";

	public static final String EXCEL_IMPORT_ERROR_PATH = ROOT + "static" + File.separator + "ExcelErrorFile"
			+ File.separator;
}
