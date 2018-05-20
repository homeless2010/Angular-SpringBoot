 package com.piedpiper.platform.core.dao.datasource;
 
 import javax.sql.DataSource;
 
 
 
 
 
 
 
 
 @Deprecated
 public class DataSourceRegister
 {
   private String name = null;
   private DataSource dataSource = null;
   private boolean asDefault = false;
   
   public String getName() {
     return this.name;
   }
   
   public void setName(String name) {
     this.name = name;
   }
   
   public DataSource getDataSource() {
     return this.dataSource;
   }
   
   public void setDataSource(DataSource dataSource) {
     this.dataSource = dataSource;
   }
   
   public boolean isAsDefault() {
     return this.asDefault;
   }
   
   public void setAsDefault(boolean asDefault) {
     this.asDefault = asDefault;
   }
 }


