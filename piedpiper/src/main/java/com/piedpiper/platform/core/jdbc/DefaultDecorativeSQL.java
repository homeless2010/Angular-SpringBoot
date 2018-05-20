 package com.piedpiper.platform.core.jdbc;
 
 
 
 
 
 
 public class DefaultDecorativeSQL
   implements DecorativeSQL
 {
   public String decorativeSQL(String sql, String tableName)
   {
     return sql;
   }
 }


