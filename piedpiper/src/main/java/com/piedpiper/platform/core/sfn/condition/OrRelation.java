 package com.piedpiper.platform.core.sfn.condition;
 
 class OrRelation implements RelationalCondition
 {
   private static String relationalOpt = "OR";
   
   public String getStructuredCondition()
   {
     return relationalOpt;
   }
 }


