 package com.piedpiper.platform.core.excel.export.datasource;
 
 import java.util.Collection;
 import java.util.Collections;
 import java.util.Enumeration;
 import java.util.List;
 import java.util.Map;
 
 
 
 
 
 
 
 
 
 
 public class MapDataExportDataSource
   extends AbstractExportDataSource
 {
   public Map<String, Object>[] getData()
   {
     return this.dates;
   }
   
 
 
 
   public void setData(Map<String, Object>[] mapData)
   {
     if (mapData == null) {
       this.dates = null;
       return;
     }
     this.dates = mapData;
   }
   
 
 
 
   public void setData(Collection<Map<String, Object>> collection)
   {
     if (collection == null) {
       this.dates = null;
       return;
     }
     this.dates = ((Map[])collection.toArray(new Map[collection.size()]));
   }
   
 
 
   public void setData(Enumeration<Map<String, Object>> enumeration)
   {
     if (enumeration == null) {
       this.dates = null;
       return;
     }
     List<Map<String, Object>> list = Collections.list(enumeration);
     this.dates = ((Map[])list.toArray(new Map[list.size()]));
   }
 }


