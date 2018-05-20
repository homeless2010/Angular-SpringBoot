package com.piedpiper.platform.core.excel.export.inf;

import com.piedpiper.platform.core.excel.export.entity.DataGridHeader;
import java.util.Map;

public abstract interface IFormatField
{
  public abstract Object formatField(DataGridHeader paramDataGridHeader, Map<String, Object> paramMap, String paramString);
}


