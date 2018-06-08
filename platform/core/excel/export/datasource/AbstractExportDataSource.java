 package com.piedpiper.platform.core.excel.export.datasource;
 
 import java.util.Collection;
 import java.util.Enumeration;
 import java.util.Map;
 
 
 
 
 
 
 
 public abstract class AbstractExportDataSource
   implements IExportDataSourceIn, IExportDataSourceOut
 {
   protected Map<String, Object>[] dates;
   
   public void setData(Collection<Map<String, Object>> collection)
   {
     throw new UnsupportedOperationException("没有实现此方法");
   }
   
   public void setData(Enumeration<Map<String, Object>> enumeration) {
     throw new UnsupportedOperationException("没有实现此方法");
   }
   
   public void setData(Map<String, Object>[] array) { throw new UnsupportedOperationException("没有实现此方法"); }
   
   public void setData(String dataJson) {
     throw new UnsupportedOperationException("没有实现此方法");
   }
 }


