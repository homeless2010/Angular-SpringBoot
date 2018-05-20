 package com.piedpiper.platform.core.sfn.condition;
 
 class AndRelation implements RelationalCondition
 {
   private static String relationalOpt = "AND";
   
   public String getStructuredCondition()
   {
     return relationalOpt;
   }
 }


