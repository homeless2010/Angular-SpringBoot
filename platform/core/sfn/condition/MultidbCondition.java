package com.piedpiper.platform.core.sfn.condition;

public abstract interface MultidbCondition
  extends Condition
{
  public abstract String getDbType();
}


