 package com.piedpiper.platform.core.sfn.condition;
 
 import com.piedpiper.platform.core.sfn.SelfDefCondtions;
 
 public class ConditionFactory {
   private Condition and = new AndRelation();
   private Condition or = new OrRelation();
   private Condition left = new LeftBracket();
   private Condition right = new RightBracket();
   
   private final String typeString = "string";
   private final String typeDate = "date";
   private final String typedatetime = "datetime";
   private final String typenumber = "number";
   
   public Condition createAndCondtion() { return this.and; }
   
   public Condition createOrConditon()
   {
     return this.or;
   }
   
   public Condition createLeftBracket() {
     return this.left;
   }
   
   public Condition createRightBracket() {
     return this.right;
   }
   
   public Condition createFieldCondtion(SelfDefCondtions selfDefCondtions, String tableAlias) {
     String type = selfDefCondtions.getDataType();
     if ("string".equals(type)) {
       StringCondition sc = new StringCondition(tableAlias);
       sc.setFiledName(selfDefCondtions.getMyk());
       sc.setLogicalOperator(selfDefCondtions.getRuleOpt());
       sc.setContentValue(selfDefCondtions.getTextValue());
       return sc; }
     if ("date".equals(type)) {
       DateCondition dc = new DateCondition(tableAlias);
       dc.setFiledName(selfDefCondtions.getMyk());
       dc.setLogicalOperator(selfDefCondtions.getRuleOpt());
       dc.setContentValue(selfDefCondtions.getTextValue());
       return dc; }
     if ("datetime".equals(type)) {
       DatetimeCondition dtc = new DatetimeCondition(tableAlias);
       dtc.setFiledName(selfDefCondtions.getMyk());
       dtc.setLogicalOperator(selfDefCondtions.getRuleOpt());
       dtc.setContentValue(selfDefCondtions.getTextValue());
       return dtc; }
     if ("number".endsWith(type)) {
       NumberCondition nc = new NumberCondition(tableAlias);
       nc.setFiledName(selfDefCondtions.getMyk());
       nc.setLogicalOperator(selfDefCondtions.getRuleOpt());
       nc.setContentValue(selfDefCondtions.getTextValue());
       return nc;
     }
     StringCondition sc = new StringCondition(tableAlias);
     sc.setFiledName(selfDefCondtions.getMyk());
     sc.setLogicalOperator(selfDefCondtions.getRuleOpt());
     sc.setContentValue(selfDefCondtions.getTextValue());
     return sc;
   }
 }


