package com.piedpiper.platform.core.sfn.condition;

public abstract interface ParameterizedCondition
  extends Condition
{
  public abstract void setKey(String paramString);
  
  public abstract String getKey();
  
  public abstract Object getValue();
}


