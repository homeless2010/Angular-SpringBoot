 package com.piedpiper.platform.core.excel.export;
 
 import com.piedpiper.platform.core.excel.export.datasource.IExportDataSourceOut;
 import com.piedpiper.platform.core.excel.export.executor.ExcelExecutor;
 import com.piedpiper.platform.core.excel.export.headersource.DefaultExportDataGridHeaderSource;
 import com.piedpiper.platform.core.excel.export.headersource.IExportDataGridHeaderSource;
 import com.piedpiper.platform.core.excel.export.inf.IFormatField;
 import java.util.Map;
 import org.apache.poi.ss.usermodel.Workbook;
 
 
 
 public abstract class AbstraceExcelExport
   implements IExportComInfo, IExport, IExportDataSourceOut
 {
   private String fileName;
   private String sheetName = "sheet1";
   
   private final DefaultExportDataGridHeaderSource defaultDataGridHeader;
   
   private IExportDataGridHeaderSource userExportDataGridHeader = null;
   
   private IFormatField formateField = null;
   
   private ExcelExecutor executor = new ExcelExecutor();
   
   public AbstraceExcelExport() {
     this.defaultDataGridHeader = new DefaultExportDataGridHeaderSource();
   }
   
 
 
 
 
   public void setUserDefinedGridHeader(IExportDataGridHeaderSource source)
   {
     this.userExportDataGridHeader = source;
   }
   
   public String getFileName()
   {
     return this.fileName;
   }
   
   public void setFileName(String fileName) {
     this.fileName = fileName;
   }
   
   public String getSheetName()
   {
     return this.sheetName;
   }
   
   public void setSheetName(String sheetName) {
     this.sheetName = sheetName;
   }
   
 
 
 
   public void setUnexportColumn(String[] unexportColumn)
   {
     this.defaultDataGridHeader.setUnexportColumn(unexportColumn);
   }
   
   public void setUnexportColumn(String string, char split) {
     this.defaultDataGridHeader.setUnexportColumn(string, split);
   }
   
   public void setUnexportColumn(String string) {
     setUnexportColumn(string, ',');
   }
   
   public void setHasRowNum(boolean hasRowNum) {
     this.defaultDataGridHeader.setHasRowNum(hasRowNum);
   }
   
   public void setDataGridHeaders(String dataGridHeadersJson) {
     this.defaultDataGridHeader.setDataGridHeaders(dataGridHeadersJson);
   }
   
   public void setHidden(boolean isHidden) {
     this.defaultDataGridHeader.setHidden(isHidden);
   }
   
   public void setFormatField(IFormatField formateField) {
     this.formateField = formateField;
   }
   
   public Map<String, Object>[] getData()
   {
     return doData();
   }
   
 
 
   protected abstract Map<String, Object>[] doData();
   
 
   public final Workbook exportData()
     throws Exception
   {
     preparedExecutor(this.executor);
     
     this.executor.setFormatField(this.formateField);
     
     return this.executor.execut();
   }
   
 
 
 
 
 
 
   private void preparedExecutor(ExcelExecutor exec)
   {
     if (this.userExportDataGridHeader == null) {
       exec.setHeader(this.defaultDataGridHeader);
     } else {
       exec.setHeader(this.userExportDataGridHeader);
     }
     exec.setData(this);
     exec.setInfo(this);
   }
 }


