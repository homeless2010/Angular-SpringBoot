package com.piedpiper.platform.core.excel.imp.inf;

public abstract interface Validate
{
  public abstract String getField();
  
  public abstract boolean validate(Object paramObject);
  
  public abstract String getErrorMag();
}


