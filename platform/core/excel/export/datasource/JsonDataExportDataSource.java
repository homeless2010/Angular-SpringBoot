 package com.piedpiper.platform.core.excel.export.datasource;
 
 import com.piedpiper.platform.commons.utils.JsonHelper;
 import java.util.Map;
 
 
 
 
 
 
 
 
 
 
 
 public class JsonDataExportDataSource
   extends AbstractExportDataSource
 {
   public Map<String, Object>[] getData()
   {
     return this.dates;
   }
   
   public void setData(String jsonData)
   {
     this.dates = ((Map[])JsonHelper.getInstance().readValue(jsonData, Map[].class));
   }
 }


