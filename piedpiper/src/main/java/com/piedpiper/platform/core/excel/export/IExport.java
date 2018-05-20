package com.piedpiper.platform.core.excel.export;

import org.apache.poi.ss.usermodel.Workbook;

public abstract interface IExport
{
  public abstract Workbook exportData()
    throws Exception;
  
  public abstract String getFileName();
}


