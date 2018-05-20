 package com.piedpiper.platform.core.mybatis.intercept;
 
 import com.piedpiper.platform.commons.utils.ComUtil;
 import com.piedpiper.platform.commons.utils.reflection.ReflectionUtils;
 import java.sql.ResultSet;
 import java.sql.ResultSetMetaData;
 import java.sql.SQLException;
 import java.sql.Statement;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import java.util.Properties;
 import org.apache.ibatis.executor.resultset.ResultSetHandler;
 import org.apache.ibatis.mapping.MappedStatement;
 import org.apache.ibatis.plugin.Interceptor;
 import org.apache.ibatis.plugin.Intercepts;
 import org.apache.ibatis.plugin.Invocation;
 import org.apache.ibatis.plugin.Plugin;
 import org.springframework.jdbc.support.JdbcUtils;
 
 
 
 
 
 
 @Intercepts({@org.apache.ibatis.plugin.Signature(method="handleResultSets", type=ResultSetHandler.class, args={Statement.class})})
 public class MapInterceptor
   implements Interceptor
 {
   public Object intercept(Invocation invocation)
     throws Throwable
   {
     Object target = invocation.getTarget();
     
     if ((target instanceof ResultSetHandler)) {
       ResultSetHandler resultSetHandler = (ResultSetHandler)target;
       MappedStatement mappedStatement = (MappedStatement)ReflectionUtils.getFieldValue(resultSetHandler, "mappedStatement");
       List<org.apache.ibatis.mapping.ResultMap> list = mappedStatement.getResultMaps();
       if ((list.size() > 0) && (((org.apache.ibatis.mapping.ResultMap)list.get(0)).getType().equals(ResultMap.class))) {
         Statement stmt = (Statement)invocation.getArgs()[0];
         return handleResultSet(stmt.getResultSet()); }
       if ((list.size() > 0) && (((org.apache.ibatis.mapping.ResultMap)list.get(0)).getType().equals(ResultKeyMap.class))) {
         Statement stmt = (Statement)invocation.getArgs()[0];
         return handleResultKeySet(stmt.getResultSet());
       }
     }
     
 
     return invocation.proceed();
   }
   
   private Object handleResultKeySet(ResultSet resultSet) { if (resultSet != null) {
       Map<Object, Object> map = new HashMap();
       
       List<Object> resultList = new ArrayList();
       try
       {
         while (resultSet.next()) {
           ResultSetMetaData rsmd = resultSet.getMetaData();
           int columnCount = rsmd.getColumnCount();
           if (columnCount <= 1) {
             throw new RuntimeException("返回一列数据时，resultType类型不能为" + ResultKeyMap.class.getName());
           }
           String key = JdbcUtils.getResultSetValue(resultSet, 1, String.class).toString();
           
           Map<String, Object> map1 = new HashMap(columnCount);
           for (int index = 1; index <= columnCount; index++) {
             String column = JdbcUtils.lookupColumnName(rsmd, index);
             
             Object value = JdbcUtils.getResultSetValue(resultSet, index, String.class);
             map1.put(ComUtil.getJavaNameFromDBColumnName(column), value);
           }
           map.put(key, map1);
         }
       } catch (SQLException e) {
         throw new RuntimeException(e.getMessage(), e);
       } finally {
         closeResultSet(resultSet);
       }
       resultList.add(map);
       return resultList;
     }
     return null;
   }
   
 
 
 
 
   private Object handleResultSet(ResultSet resultSet)
   {
     if (resultSet != null) {
       Map<Object, Object> map = new HashMap();
       
       List<Object> resultList = new ArrayList();
       try
       {
         while (resultSet.next()) {
           ResultSetMetaData rsmd = resultSet.getMetaData();
           int columnCount = rsmd.getColumnCount();
           if (columnCount <= 1) {
             throw new RuntimeException("返回一列数据时，resultType类型不能为" + org.apache.ibatis.mapping.ResultMap.class.getName());
           }
           columnCount = 2;
           String[] cath = new String[2];
           for (int index = 1; index <= columnCount; index++) {
             cath[(index - 1)] = JdbcUtils.getResultSetValue(resultSet, index, String.class).toString();
           }
           map.put(cath[0], cath[1]);
         }
       } catch (SQLException e) {
         throw new RuntimeException(e.getMessage(), e);
       } finally {
         closeResultSet(resultSet);
       }
       resultList.add(map);
       return resultList;
     }
     return null;
   }
   
 
 
   private void closeResultSet(ResultSet resultSet)
   {
     try
     {
       if (resultSet != null) {
         resultSet.close();
       }
     }
     catch (SQLException e) {}
   }
   
 
 
 
   public Object plugin(Object obj)
   {
     return Plugin.wrap(obj, this);
   }
   
   public void setProperties(Properties props) {}
 }


