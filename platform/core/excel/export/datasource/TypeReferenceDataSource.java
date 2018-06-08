 package com.piedpiper.platform.core.excel.export.datasource;
 
 import java.lang.reflect.Type;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 
 
 
 
 
 
 
 
 public abstract class TypeReferenceDataSource<T>
   extends AbstractExportDataSource
 {
   protected final Type _type;
   private final List<Map<String, Object>> _data = new ArrayList();
   
   protected TypeReferenceDataSource() {
     Type superClass = getClass().getGenericSuperclass();
     if ((superClass instanceof Class)) {
       throw new IllegalArgumentException("Internal error: TypeReference constructed without actual type information");
     }
     this._type = ((java.lang.reflect.ParameterizedType)superClass).getActualTypeArguments()[0];
   }
   
   public Type getType() {
     return this._type;
   }
   
 
 
   public void setData(List<T> list)
   {
     for (T t : list) {
       this._data.add(convert2Map(t));
     }
     this.dates = ((Map[])this._data.toArray(new HashMap[this._data.size()]));
   }
   
 
 
 
 
   public Map<String, Object>[] getData()
   {
     return this.dates;
   }
   
   public abstract Map<String, Object> convert2Map(T paramT);
 }


