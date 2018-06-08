 package com.piedpiper.platform.core.sfn.condition;
 
 import com.piedpiper.platform.core.dao.DbUtils;
 import java.text.MessageFormat;
 import java.util.HashMap;
 import java.util.Map;
 
 abstract class AbstractParamedConditon
   implements ParameterizedCondition, MultidbCondition
 {
   private static String dbType;
   
   protected String tableAlias;
   
   protected String filedName;
   
   protected String logicalOperator;
   
   private String key;
   
   private static Map<String, String> logicalMapping = new HashMap();
   
   private static String likeStatment;
   
   private static String tableNameStatment = "{0}.{1} {2}";
   
   private static String noTableNameStatment = "{0} {1}";
   
   private static String ohterStatment = "{0} #'{'{1}'}'";
   
   static {
     logicalMapping.put("eq", "=");
     logicalMapping.put("like", "like");
     logicalMapping.put("gt", ">");
     logicalMapping.put("gteq", ">=");
     logicalMapping.put("lt", "<");
     logicalMapping.put("lteq", "<=");
     logicalMapping.put("uneq", "<>");
     if (DbUtils.isOracle()) {
       likeStatment = "like ''%''||#'{'{0}'}'||''%''";
     } else if (DbUtils.isMySql()) {
       likeStatment = "like concat(concat(''%'',#'{'{0}'}'),''%'')";
     }
   }
   
   public AbstractParamedConditon(String tableAlias) {
     this.tableAlias = tableAlias;
   }
   
   public String getFiledName() {
     return this.filedName;
   }
   
   public void setFiledName(String filedName) {
     this.filedName = filedName;
   }
   
   public void setKey(String key) {
     this.key = key;
   }
   
   public final String getKey()
   {
     return this.key;
   }
   
   private String mapperOpt() {
     if ("like".equals(this.logicalOperator)) {
       return MessageFormat.format(likeStatment, new Object[] { getKey() });
     }
     return MessageFormat.format(ohterStatment, new Object[] { logicalMapping.get(this.logicalOperator), getKey() });
   }
   
   private String mapperTableName()
   {
     if ("".equals(this.tableAlias)) {
       return MessageFormat.format(noTableNameStatment, new Object[] { this.filedName, mapperOpt() });
     }
     return MessageFormat.format(tableNameStatment, new Object[] { this.tableAlias, this.filedName, mapperOpt() });
   }
   
 
   public final String getStructuredCondition()
   {
     return mapperTableName();
   }
   
   public String getLogicalOperator() {
     return this.logicalOperator;
   }
   
   public void setLogicalOperator(String logicalOperator) {
     this.logicalOperator = logicalOperator;
   }
   
   public String getDbType() {
     return dbType;
   }
   
   public final Object getValue() {
     return doGetValue();
   }
   
   public abstract Object doGetValue();
}
