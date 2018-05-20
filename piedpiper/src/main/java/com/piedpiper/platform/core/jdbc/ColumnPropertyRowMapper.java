 package com.piedpiper.platform.core.jdbc;
 
 import com.piedpiper.platform.commons.utils.ComUtil;
 import java.sql.ResultSet;
 import java.sql.ResultSetMetaData;
 import java.sql.SQLException;
 import java.util.HashMap;
 import java.util.Map;
 import org.springframework.jdbc.core.RowMapper;
 import org.springframework.jdbc.support.JdbcUtils;
 
 public class ColumnPropertyRowMapper implements RowMapper<Map<String, Object>>
 {
   private Map<String, Class<?>> mappedFields;
   
   public ColumnPropertyRowMapper()
   {
     this.mappedFields = new HashMap();
   }
   
   public ColumnPropertyRowMapper(Map<String, Class<?>> map) {
     this.mappedFields = new HashMap(map.size());
     this.mappedFields.putAll(map);
   }
   
   public Map<String, Object> mapRow(ResultSet paramResultSet, int paramInt)
     throws SQLException
   {
     ResultSetMetaData rsmd = paramResultSet.getMetaData();
     int columnCount = rsmd.getColumnCount();
     Map<String, Object> map = new HashMap(columnCount);
     for (int index = 1; index <= columnCount; index++) {
       String column = JdbcUtils.lookupColumnName(rsmd, index);
       
       Object value = null;
       Class<?> clazz = (Class)this.mappedFields.get(column.replaceAll(" ", "").toLowerCase());
       if (clazz != null) {
         value = JdbcUtils.getResultSetValue(paramResultSet, index, clazz);
       }
       else {
         value = JdbcUtils.getResultSetValue(paramResultSet, index, String.class);
       }
       map.put(ComUtil.getJavaNameFromDBColumnName(column), value);
     }
     return map;
   }
 }


