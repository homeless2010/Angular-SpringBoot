package com.piedpiper.platform.core.jdbc;

public abstract interface IDialect
{
  public abstract String sequenceSql(String paramString);
  
  public abstract String pageSql(String paramString, int paramInt1, int paramInt2);
  
  public abstract String countSql(String paramString);
  
  public abstract boolean supportASWithTableAlias();
  
  public abstract boolean supportOrderInSubquery();
}


