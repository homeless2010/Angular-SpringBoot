 package com.piedpiper.platform.core.excel.export.entity;
 
 import java.util.List;
 import java.util.Map;
 
 
 
 
 
 public abstract class ExcelExportConstant
 {
   public static final String DEFAULT_EXCEL_SERVICE_NAME = "commonExcelServiceImpl";
   public static final String DEFAULT_METHOD_EXPORT_NAME = "doExportExcel";
   public static final String DEFAULT_METHOD_EXPORT_PARA1 = "doExportExcel";
   public static final String DEFAULT_METHOD_EXPORT_PARA2 = "doExportExcel";
   public static final String DEFAULT_METHOD_IMPORT_NAME = "doImportExcel";
   public static final Class<?> DEFAULT_METHOD_IMPORT_PARA1 = List.class;
   
   public static final Class<?> DEFAULT_METHOD_IMPORT_PARA2 = Map.class;
   
   public static final String DEFAULT_METHOD_EXP_ERRDATA = "doExportErrorData";
   
   public static final Class<?> DEFAULT_METHOD_EXP_ERRDATA_PARA1 = Map.class;
   public static final int DEFAULT_CELL_WIDTH = 120;
   public static final int DEFAULT_HEADER_ROW_HEIGHT = 40;
   public static final int DEFAULT_OTHER_ROW_HEIGHT = 22;
   public static final String DEFAULT_ERR_COLUMN_NAME = "错误信息";
   public static final String DEFAULT_FONT_NAME = "宋体";
   public static final boolean HAS_ROWNUM = true;
   public static final String ALIGN_LEFT = "left";
   public static final String H_ALIGN_LEFT = "left";
   public static final String ALIGN_RIGHT = "right";
   public static final String H_ALIGN_RIGHT = "right";
   public static final String ALIGN_CENTER = "center";
   public static final String H_ALIGN_CENTER = "center";
 }


