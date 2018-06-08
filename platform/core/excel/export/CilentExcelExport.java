 package com.piedpiper.platform.core.excel.export;
 
 import com.piedpiper.platform.core.excel.export.datasource.JsonDataExportDataSource;
 import java.util.Map;
 
 
 
 
 
 
 
 
 
 public class CilentExcelExport
   extends AbstraceExcelExport
 {
   private JsonDataExportDataSource defaultExportDataSource;
   
   public CilentExcelExport()
   {
     this.defaultExportDataSource = new JsonDataExportDataSource();
   }
   
 
 
   public void setData(String dataJson)
   {
     this.defaultExportDataSource.setData(dataJson);
   }
   
 
   protected Map<String, Object>[] doData()
   {
     return this.defaultExportDataSource.getData();
   }
 }


