 package com.piedpiper.platform.core.excel.export.datasource;
 
 import com.piedpiper.platform.commons.utils.JsonHelper;
 import com.fasterxml.jackson.core.type.TypeReference;
 import java.util.HashMap;
 import java.util.Map;
 
 
 
 
 
 
 
 
 
 
 
 public class DefaultTypeReferenceDataSource<T>
   extends TypeReferenceDataSource<T>
 {
   public Map<String, Object> convert2Map(T obj)
   {
     Map<String, Object> result = (Map)JsonHelper.getInstance().transformDto(obj, new TypeReference() {});
     return result;
   }
 }


