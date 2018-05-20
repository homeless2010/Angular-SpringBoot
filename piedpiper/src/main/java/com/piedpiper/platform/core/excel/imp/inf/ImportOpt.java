package com.piedpiper.platform.core.excel.imp.inf;

import java.util.Map;

public abstract interface ImportOpt
{
  public abstract void import2Db(Map<String, Object> paramMap)
    throws Exception;
}


