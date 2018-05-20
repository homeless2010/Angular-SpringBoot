package com.piedpiper.platform.core.excel.export.datasource;

import java.util.Map;

public abstract interface IExportDataSourceOut
{
  public abstract Map<String, Object>[] getData();
}


