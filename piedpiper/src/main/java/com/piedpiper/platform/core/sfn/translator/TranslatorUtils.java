 package com.piedpiper.platform.core.sfn.translator;
 
 import com.piedpiper.platform.core.sfn.SelfDefCondtions;
 import com.piedpiper.platform.core.sfn.condition.Condition;
 import com.piedpiper.platform.core.sfn.condition.ConditionFactory;
 import com.piedpiper.platform.core.sfn.condition.ParameterizedCondition;
 import com.piedpiper.platform.core.sfn.condition.StructuredQueryParam;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 
 public abstract class TranslatorUtils
 {
   private static String type = "type";
   private static ConditionFactory factory = new ConditionFactory();
   
   public static StructuredQueryParam translateSelfDefConditions(List<SelfDefCondtions> selfDefCondtions, String tableAlias) {
     List<Condition> conditions = new ArrayList();
     recuret(conditions, selfDefCondtions, tableAlias);
     
     Map<String, Object> paramMap = new HashMap(conditions.size());
     int i = 0;
     StringBuilder buf = new StringBuilder();
     
     boolean flag = false;
     for (Condition condition : conditions) {
       if (flag) {
         buf.append(" ");
       } else {
         flag = true;
       }
       if ((condition instanceof ParameterizedCondition)) {
         ParameterizedCondition pc = (ParameterizedCondition)condition;
         pc.setKey("selfparam" + i);
         paramMap.put("selfparam" + i, pc.getValue());
         i++;
       }
       buf.append(condition.getStructuredCondition());
     }
     StructuredQueryParam sqp = new StructuredQueryParam();
     sqp.setSql(buf.toString());
     sqp.setParam(paramMap);
     return sqp;
   }
   
   private static void recuret(List<Condition> conditions, List<SelfDefCondtions> selfDefCondtions, String tableAlias) {
     Condition first = null;
     
     for (SelfDefCondtions selfDefCondtion : selfDefCondtions) {
       Integer tt = (Integer)selfDefCondtion.getAttributes().get(type);
       if ((tt.intValue() == 3) && (selfDefCondtion.getChildren().size() > 0)) {
         if (first != null) {
           conditions.add(first);
         }
         conditions.add(factory.createLeftBracket());
         recuret(conditions, selfDefCondtion.getChildren(), tableAlias);
         conditions.add(factory.createRightBracket());
         first = factory.createAndCondtion();
       } else if (tt.intValue() == 1) {
         if (first != null) {
           conditions.add(first);
         }
         conditions.add(factory.createFieldCondtion(selfDefCondtion, tableAlias));
         first = factory.createAndCondtion();
       } else if (tt.intValue() == 2) {
         first = factory.createOrConditon();
       }
     }
   }
 }


