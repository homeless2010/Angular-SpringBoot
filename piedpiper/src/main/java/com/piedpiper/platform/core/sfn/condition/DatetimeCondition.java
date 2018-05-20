 package com.piedpiper.platform.core.sfn.condition;
 
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 
 
 
 
 class DatetimeCondition
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
   
   public DatetimeCondition(String tableAlias) {
     super(tableAlias);
   }
   
   public Object doGetValue() {
     if (this.contentValue == null) {
       return new Date();
     }
     if ((this.contentValue instanceof Long))
       return new Date(((Long)this.contentValue).longValue());
     if ((this.contentValue instanceof Integer))
       return new Date(((Integer)this.contentValue).intValue());
     if ((this.contentValue instanceof String)) {
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       try
       {
         return sdf.parse((String)this.contentValue);
       } catch (ParseException e) {
         e.printStackTrace();
         
         return new Date();
       } }
     return new Date();
   }
 }


