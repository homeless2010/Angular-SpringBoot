 package com.piedpiper.platform.core.excel.imp;
 
 import com.piedpiper.platform.core.excel.imp.entity.ExcelHeader;
 import com.piedpiper.platform.core.excel.imp.executor.AbstractExcutor;
 import com.piedpiper.platform.core.excel.imp.inf.ImportOpt;
 import com.piedpiper.platform.core.excel.imp.inf.Validate;
 import java.io.BufferedInputStream;
 import java.io.BufferedOutputStream;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.FileOutputStream;
 import java.io.InputStream;
 import java.io.OutputStream;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import java.util.Map.Entry;
 import org.apache.commons.lang3.ArrayUtils;
 import org.apache.poi.hssf.usermodel.HSSFCell;
 import org.apache.poi.hssf.usermodel.HSSFCellStyle;
 import org.apache.poi.hssf.usermodel.HSSFFont;
 import org.apache.poi.hssf.usermodel.HSSFRow;
 import org.apache.poi.hssf.usermodel.HSSFSheet;
 import org.apache.poi.hssf.usermodel.HSSFWorkbook;
 import org.apache.poi.ss.usermodel.Cell;
 import org.apache.poi.ss.usermodel.Row;
 import org.apache.poi.ss.usermodel.Sheet;
 import org.apache.poi.ss.usermodel.Workbook;
 import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
 
 
 
 
 
 public class ExcelImport
   extends AbastractExcelImport
 {
   private static final String ID_KEY = "id";
   private static final int version2003 = 2003;
   private static final int version2007 = 2007;
   private static int version = 2003;
   
   private static Workbook wb = null;
   
   private Map<Integer, ExcelHeader> map = new HashMap();
   
 
   private static Sheet rs;
   
 
   private Map<String, String> param;
   
 
   private int importTypeBeginRow;
   
   private int columnsPerRow = 0;
   
 
   private String[] excelTitleArray = null;
   
   private Map<Integer, String> errorMsg = new HashMap();
   
 
 
 
   public void setInfo(Map<String, String> param)
   {
     this.param = param;
   }
   
   protected void doPretreatment() throws Exception
   {
     if (!this.param.containsKey("id")) {
       throw new RuntimeException("参数不正确");
     }
     String path = (String)ExcelImportConstans.UPLOAD_FILE.get(this.param.get("id"));
     
     this.importTypeBeginRow = 0;
     
     File excelFile = new File(path);
     if (!excelFile.exists()) {
       throw new RuntimeException("上传文件不存在，请重新上传！");
     }
     if ((!path.endsWith(".xls")) && (!path.endsWith(".xlsx"))) {
       throw new RuntimeException("请上传Excel类型的文件！");
     }
     
     InputStream stream = new BufferedInputStream(new FileInputStream(path));
     if (path.endsWith(".xls")) {
       version = 2003;
       wb = new HSSFWorkbook(stream);
     } else {
       version = 2007;
       wb = new XSSFWorkbook(stream);
     }
     stream.close();
   }
   
   protected void doGenerateErrorExcel()
     throws Exception
   {
     HSSFWorkbook wb = new HSSFWorkbook();
     
     HSSFSheet sheet = wb.createSheet("ErrorData Sheet");
     
     sheet.setColumnWidth(1, 3500);
     
     HSSFFont errorInfofont = wb.createFont();
     HSSFCellStyle errorInfotStyle = wb.createCellStyle();
     errorInfofont.setColor((short)10);
     errorInfotStyle.setWrapText(false);
     errorInfotStyle.setFont(errorInfofont);
     
     HSSFCellStyle contentStyle = wb.createCellStyle();
     HSSFFont contentFont = wb.createFont();
     contentStyle.setFont(contentFont);
     contentStyle.setAlignment((short)2);
     contentStyle.setVerticalAlignment((short)1);
     contentStyle.setWrapText(true);
     HSSFRow row = sheet.createRow(0);
     for (int col = 0; col < this.excelTitleArray.length; col++) {
       HSSFCell cell = row.createCell(col);
       cell.setCellStyle(contentStyle);
       cell.setCellValue(this.excelTitleArray[col]);
     }
     int i = 0;
     for (Map.Entry<Integer, String> entry : this.errorMsg.entrySet()) {
       HSSFRow rowCreat = sheet.createRow(i + 1);
       Row rowOld = rs.getRow(((Integer)entry.getKey()).intValue());
       int firstCell = rowOld.getFirstCellNum();
       int lastCell = rowOld.getLastCellNum();
       for (int j = firstCell; j <= lastCell; j++) {
         HSSFCell newCell = rowCreat.createCell(j);
         Cell cellOld = rowOld.getCell(j);
         setErrorExcelForPOI(newCell, cellOld);
       }
       HSSFCell cellContent = rowCreat.createCell(this.excelTitleArray.length - 1);
       cellContent.setCellValue((String)entry.getValue());
       cellContent.setCellStyle(errorInfotStyle);
       sheet.setColumnWidth(lastCell, 8000);
       i++;
     }
     
     String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "error" + ".xls";
     this.resultFile = (this.excutor.getName() + fileName);
     File f = new File(ExcelImportConstans.EXCEL_IMPORT_ERROR_PATH + this.excutor.getName() + fileName);
     OutputStream os = new BufferedOutputStream(new FileOutputStream(f));
     
     wb.write(os);
     
     os.close();
   }
   
   protected void doValidateExcelTemplet()
     throws Exception
   {
     rs = wb.getSheetAt(0);
     this.rowTotal = rs.getLastRowNum();
     if (this.rowTotal < this.importTypeBeginRow + 1) {
       throw new RuntimeException("指定的导入开始行:(" + (this.importTypeBeginRow + 1) + ")大于导入Excel中所含有的行数(" + this.rowTotal + ")行!");
     }
     
 
 
     Row rowTitle = rs.getRow(this.importTypeBeginRow);
     
     if (rowTitle == null) {
       throw new RuntimeException("上传文件的Excel标题头不能为空!");
     }
     this.columnsPerRow = rowTitle.getLastCellNum();
     this.excelTitleArray = new String[this.columnsPerRow + 1];
     for (int j = 0; j < this.columnsPerRow; j++) {
       Cell cell = rowTitle.getCell(j);
       if (cell != null) {
         this.excelTitleArray[j] = cell.getStringCellValue();
       } else {
         throw new RuntimeException("第" + (this.importTypeBeginRow + 1) + "行中，存在标题为空!");
       }
     }
     this.excelTitleArray[this.columnsPerRow] = "错误信息";
     
     if (this.excelTitleArray.length == 0) {
       throw new RuntimeException("上传文件的Excel标题头不能为空!");
     }
     if (this.rowTotal == this.importTypeBeginRow) {
       throw new RuntimeException("空文件");
     }
     List<ExcelHeader> headers = this.excutor.getHeaders();
     String value = "";
     for (ExcelHeader header : headers) {
       value = header.getValue();
       if (!ArrayUtils.contains(this.excelTitleArray, value)) {
         throw new RuntimeException("导入Excel和模版Excel不相符，缺少表头：" + header.toString());
       }
       this.map.put(Integer.valueOf(ArrayUtils.indexOf(this.excelTitleArray, value)), header);
     }
   }
   
 
 
   protected void doImportByExcutor()
   {
     Map<String, Object> data = null;
     
 
 
 
     for (int row = 1; row <= this.rowTotal; row++) {
       data = new HashMap(this.columnsPerRow);
       Row r = rs.getRow(row);
       boolean flag = false;
       
       for (Map.Entry<Integer, ExcelHeader> entry : this.map.entrySet()) {
         Cell cell = r.getCell(((Integer)entry.getKey()).intValue());
         Object obj;
         try
         {
           obj = getContentFromExcelByPOI(cell);
         }
         catch (Exception e)
         {
           this.errorMsg.put(Integer.valueOf(row), e.getMessage());
           flag = true;
           break;
         }
         data.put(((ExcelHeader)entry.getValue()).getKey(), obj);
         
         Validate v = (Validate)this.excutor.getValidators().get(((ExcelHeader)entry.getValue()).getKey());
         
         if ((v != null) && (v.validate(obj))) {
           this.errorMsg.put(Integer.valueOf(row), v.getErrorMag());
           flag = true;
           break;
         }
       }
       if (flag) {
         this.failRows += 1;
       }
       else {
         try {
           this.excutor.getImportOpt().import2Db(data);
         } catch (Exception e) {
           this.errorMsg.put(Integer.valueOf(row), e.getLocalizedMessage());
           this.failRows += 1;
           continue;
         }
         this.successRows += 1;
       }
     }
   }
   
   public int getVersion() {
     return version;
   }
 }


