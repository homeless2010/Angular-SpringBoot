package com.piedpiper.platform.core.mybatis.shard.converter;

import net.sf.jsqlparser.statement.Statement;

public abstract interface SqlConverter
{
  public abstract String convert(Statement paramStatement, Object paramObject, String paramString);
}


