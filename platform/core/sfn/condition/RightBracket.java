 package com.piedpiper.platform.core.sfn.condition;
 
 class RightBracket implements RelationalCondition {
   private static String relationalOpt = ")";
   
   public String getStructuredCondition() {
     return relationalOpt;
   }
 }


