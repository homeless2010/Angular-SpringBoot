package com.piedpiper.platform.core.excel.export.datasource;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;

public abstract interface IExportDataSourceIn
{
  public abstract void setData(Collection<Map<String, Object>> paramCollection);
  
  public abstract void setData(Enumeration<Map<String, Object>> paramEnumeration);
  
  public abstract void setData(Map<String, Object>[] paramArrayOfMap);
  
  public abstract void setData(String paramString);
}


