 package com.piedpiper.platform.core.sfn.condition;
 
 
 
 class StringCondition
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
   
   public StringCondition(String tableAlias) {
     super(tableAlias);
   }
   
   public Object doGetValue()
   {
     if (this.contentValue == null) {
       return "";
     }
     return this.contentValue;
   }
 }


