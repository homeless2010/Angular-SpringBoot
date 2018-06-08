 package com.piedpiper.platform.core.excel.export;
 
 import com.piedpiper.platform.core.excel.export.datasource.AbstractExportDataSource;
 import com.piedpiper.platform.core.excel.export.datasource.MapDataExportDataSource;
 import java.util.Collection;
 import java.util.Enumeration;
 import java.util.Map;
 
 
 
 
 
 
 
 
 
 public class ServerExcelExport
   extends AbstraceExcelExport
 {
   private AbstractExportDataSource defaultExportDataSource = new MapDataExportDataSource();
   
 
 
   public void setExportDataSource(AbstractExportDataSource e)
   {
     this.defaultExportDataSource = e;
   }
   
 
 
 
   public void setData(Collection<Map<String, Object>> collection)
   {
     this.defaultExportDataSource.setData(collection);
   }
   
 
 
 
   public void setData(Enumeration<Map<String, Object>> enumeration)
   {
     this.defaultExportDataSource.setData(enumeration);
   }
   
 
 
 
   public void setData(Map<String, Object>[] array)
   {
     this.defaultExportDataSource.setData(array);
   }
   
   protected Map<String, Object>[] doData()
   {
     return this.defaultExportDataSource.getData();
   }
 }


