 package com.piedpiper.platform.core.sfn.condition;
 
 
 
 class NumberCondition
   extends AbstractParamedConditon
 {
   private Object contentValue;
   
 
 
   public Object getContentValue()
   {
     return this.contentValue;
   }
   
   public void setContentValue(Object contentValue) {
     this.contentValue = contentValue;
   }
   
   public NumberCondition(String tableAlias) {
     super(tableAlias);
   }
   
   public Object doGetValue()
   {
     if (this.contentValue == null) {
       return Integer.valueOf(0);
     }
     return this.contentValue;
   }
 }


