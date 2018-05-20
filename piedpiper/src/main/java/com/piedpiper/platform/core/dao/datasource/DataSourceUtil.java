 package com.piedpiper.platform.core.dao.datasource;
 
 import java.sql.Connection;
 import java.sql.DatabaseMetaData;
 import java.sql.SQLException;
 import org.springframework.dao.DataAccessException;
 import org.springframework.jdbc.core.ConnectionCallback;
 import org.springframework.jdbc.core.JdbcTemplate;
 import org.springframework.jdbc.datasource.DataSourceUtils;
 
 
 
 
 
 
 
 public class DataSourceUtil
 {
   private static DataSourceUtil _instance = null;
   private static String dbType = null;
   
   public static DataSourceUtil getInstance() {
     if (_instance == null) {
       _instance = DataSourceUtilHolder.holder;
       return _instance;
     }
     
     return _instance;
   }
   
   private static class DataSourceUtilHolder
   {
     private static final DataSourceUtil holder = new DataSourceUtil(null);
   }
   
   public String getDataBaseType(JdbcTemplate jdbcTemplate) {
     if ((dbType != null) && (dbType.trim().length() > 0)) {
       return dbType;
     }
     Connection conn = null;
     conn = (Connection)jdbcTemplate.execute(new ConnectionCallback()
     {
       public Connection doInConnection(Connection paramConnection) throws SQLException, DataAccessException
       {
         return paramConnection;
       }
     });
     try {
       if (conn.getMetaData().getURL().indexOf("sqlserver") != -1) {
         dbType = "sqlserver";
       } else if (conn.getMetaData().getURL().indexOf("db2") != -1) {
         dbType = "db2";
       } else if (conn.getMetaData().getURL().indexOf("mysql") != -1) {
         dbType = "mysql";
       } else {
         dbType = "oracle";
       }
     } catch (SQLException e) {
       e.printStackTrace();
     } finally {
       DataSourceUtils.releaseConnection(conn, jdbcTemplate.getDataSource());
     }
     
     return dbType;
   }
 }


