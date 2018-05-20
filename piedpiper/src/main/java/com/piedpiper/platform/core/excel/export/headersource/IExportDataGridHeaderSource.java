package com.piedpiper.platform.core.excel.export.headersource;

import com.piedpiper.platform.core.excel.export.entity.DataGridHeader;
import java.util.List;

public abstract interface IExportDataGridHeaderSource
{
  public abstract List<DataGridHeader> getDataGridHeader();
  
  public abstract boolean getHasRowNum();
}


