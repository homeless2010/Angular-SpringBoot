 package com.piedpiper.platform.core.sfn.intercept;
 
 import com.piedpiper.platform.commons.utils.JsonHelper;
 import com.piedpiper.platform.core.sfn.SelfDefCondtions;
 import com.piedpiper.platform.core.sfn.condition.StructuredQueryParam;
 import com.piedpiper.platform.core.sfn.translator.TranslatorUtils;
 import java.util.HashMap;
 import java.util.List;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 
 
 public class SelfDefinedQuery
 {
   public static Logger __a = LoggerFactory.getLogger(SelfDefinedQuery.class);
   
   private SelfDefCondtions selfDefConditions = null;
   
   private String tableAlias;
   
   private String sqlWhere;
   private StructuredQueryParam sqp = null;
   
   public SelfDefinedQuery(String conditionJson) { this(conditionJson, ""); }
   
 
   public SelfDefinedQuery(String conditionJson, String tableAlias)
   {
     if (!"".equals(conditionJson)) {
       this.selfDefConditions = ((SelfDefCondtions)JsonHelper.getInstance().readValue(conditionJson, SelfDefCondtions.class));
     }
     this.tableAlias = tableAlias;
     
     if ((this.selfDefConditions == null) || (this.selfDefConditions.getChildren().size() == 0)) {
       this.sqlWhere = "";
     } else {
       this.sqp = TranslatorUtils.translateSelfDefConditions(this.selfDefConditions.getChildren(), this.tableAlias);
       this.sqlWhere = this.sqp.getSql();
     }
     if (__a.isDebugEnabled()) {
       __a.debug("自定义查询数据库表达式：" + this.sqlWhere);
     }
   }
   
   public String getTableAlias()
   {
     return this.tableAlias;
   }
   
   public void setTableAlias(String tableAlias) {
     this.tableAlias = tableAlias;
   }
   
   public String getSqlWhere() {
     return this.sqlWhere;
   }
   
   public void setSqlWhere(String sqlWhere) {
     this.sqlWhere = sqlWhere;
   }
   
   public final SelfDefined parseSql() {
     if (this.sqp != null) {
       SelfDefinedQueryTrigger.SelfDefinedQueryParam.set(this.sqp.getParam());
       if (__a.isDebugEnabled()) {
         __a.debug("自定义查询数据库表达式查询条件：" + this.sqp.getParam());
       }
     }
     return new Ree(this.sqlWhere);
   }
   
   private class Ree
     extends HashMap<String, String>
     implements SelfDefined
   {
     private static final long serialVersionUID = -6656319706156109120L;
     private static final String key = "sql";
     
     public Ree(String value)
     {
       super.put("sql", value);
     }
   }
   
   public String toString()
   {
     return this.sqlWhere;
   }
 }


