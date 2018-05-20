 package com.piedpiper.platform.core.jdbc;
 
 
 
 
 
 
 
 
 public abstract class AbstractDialect
   implements IDialect
 {
   public String countSql(String selectSql)
   {
     return selectSql;
   }
 }


