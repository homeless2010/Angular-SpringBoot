package com.piedpiper.platform.core.excel.export.headersource;

public abstract interface IDefaultDataGridHeader
  extends IExportDataGridHeaderSource
{
  public abstract String[] getUnexportColumn();
  
  public abstract boolean exportHidden();
}


