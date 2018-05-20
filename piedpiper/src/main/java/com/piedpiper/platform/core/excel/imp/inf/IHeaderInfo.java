package com.piedpiper.platform.core.excel.imp.inf;

import com.piedpiper.platform.core.excel.imp.entity.ExcelHeader;
import java.util.List;

public abstract interface IHeaderInfo
{
  public abstract List<ExcelHeader> getHeaders();
}


