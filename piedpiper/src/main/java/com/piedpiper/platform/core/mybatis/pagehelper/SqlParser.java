 package com.piedpiper.platform.core.mybatis.pagehelper;
 
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Map;
 import java.util.concurrent.ConcurrentHashMap;
 import net.sf.jsqlparser.JSQLParserException;
 import net.sf.jsqlparser.expression.Alias;
 import net.sf.jsqlparser.parser.CCJSqlParserUtil;
 import net.sf.jsqlparser.statement.Statement;
 import net.sf.jsqlparser.statement.select.FromItem;
 import net.sf.jsqlparser.statement.select.Join;
 import net.sf.jsqlparser.statement.select.LateralSubSelect;
 import net.sf.jsqlparser.statement.select.OrderByElement;
 import net.sf.jsqlparser.statement.select.PlainSelect;
 import net.sf.jsqlparser.statement.select.Select;
 import net.sf.jsqlparser.statement.select.SelectBody;
 import net.sf.jsqlparser.statement.select.SelectItem;
 import net.sf.jsqlparser.statement.select.SetOperationList;
 import net.sf.jsqlparser.statement.select.SubJoin;
 import net.sf.jsqlparser.statement.select.SubSelect;
 import net.sf.jsqlparser.statement.select.ValuesList;
 import net.sf.jsqlparser.statement.select.WithItem;
 import org.apache.ibatis.mapping.BoundSql;
 import org.apache.ibatis.mapping.MappedStatement;
 
 public class SqlParser implements SqlUtil.Parser
 {
   private static final List<SelectItem> COUNT_ITEM = new ArrayList();
   static { COUNT_ITEM.add(new net.sf.jsqlparser.statement.select.SelectExpressionItem(new net.sf.jsqlparser.schema.Column("count(1)")));
     
    
   
   private static final Alias TABLE_ALIAS;
   private SqlUtil.Parser simpleParser;
   private Map<String, String> CACHE = new ConcurrentHashMap();
   
   public SqlParser(SqlUtil.Dialect dialect) {
     this.simpleParser = SqlUtil.SimpleParser.newParser(dialect);
   }
   
   public void isSupportedSql(String sql) {
     this.simpleParser.isSupportedSql(sql);
   }
   
   public String getCountSql(String sql)
   {
     isSupportedSql(sql);
     return parse(sql);
   }
   
   public String getPageSql(String sql) {
     return this.simpleParser.getPageSql(sql);
   }
   
   public Map setPageParameter(MappedStatement ms, Object parameterObject, BoundSql boundSql, Page page) {
     return this.simpleParser.setPageParameter(ms, parameterObject, boundSql, page);
   }
   
   public String parse(String sql) {
     if (this.CACHE.get(sql) != null) {
       return (String)this.CACHE.get(sql);
     }
     Statement stmt = null;
     try {
       stmt = CCJSqlParserUtil.parse(sql);
     }
     catch (JSQLParserException e) {
       String countSql = this.simpleParser.getCountSql(sql);
       this.CACHE.put(sql, countSql);
       return countSql;
     }
     Select select = (Select)stmt;
     SelectBody selectBody = select.getSelectBody();
     
     processSelectBody(selectBody);
     
     processWithItemsList(select.getWithItemsList());
     
     sqlToCount(select);
     String result = select.toString();
     this.CACHE.put(sql, result);
     return result;
   }
   
 
 
 
 
   public void sqlToCount(Select select)
   {
     SelectBody selectBody = select.getSelectBody();
     
 
     if (((selectBody instanceof PlainSelect)) && (!selectItemsHashParameters(((PlainSelect)selectBody).getSelectItems())) && (((PlainSelect)selectBody).getGroupByColumnReferences() == null))
     {
 
       ((PlainSelect)selectBody).setSelectItems(COUNT_ITEM);
     } else {
       PlainSelect plainSelect = new PlainSelect();
       SubSelect subSelect = new SubSelect();
       subSelect.setSelectBody(selectBody);
       subSelect.setAlias(TABLE_ALIAS);
       plainSelect.setFromItem(subSelect);
       plainSelect.setSelectItems(COUNT_ITEM);
       select.setSelectBody(plainSelect);
     }
   }
   
 
 
 
 
   public void processSelectBody(SelectBody selectBody)
   {
     if ((selectBody instanceof PlainSelect)) {
       processPlainSelect((PlainSelect)selectBody);
     } else if ((selectBody instanceof WithItem)) {
       WithItem withItem = (WithItem)selectBody;
       if (withItem.getSelectBody() != null) {
         processSelectBody(withItem.getSelectBody());
       }
     } else {
       SetOperationList operationList = (SetOperationList)selectBody;
       if ((operationList.getPlainSelects() != null) && (operationList.getPlainSelects().size() > 0)) {
         List<PlainSelect> plainSelects = operationList.getPlainSelects();
         for (PlainSelect plainSelect : plainSelects) {
           processPlainSelect(plainSelect);
         }
       }
       if (!orderByHashParameters(operationList.getOrderByElements())) {
         operationList.setOrderByElements(null);
       }
     }
   }
   
 
 
 
 
   public void processPlainSelect(PlainSelect plainSelect)
   {
     if (!orderByHashParameters(plainSelect.getOrderByElements())) {
       plainSelect.setOrderByElements(null);
     }
     if (plainSelect.getFromItem() != null) {
       processFromItem(plainSelect.getFromItem());
     }
     if ((plainSelect.getJoins() != null) && (plainSelect.getJoins().size() > 0)) {
       List<Join> joins = plainSelect.getJoins();
       for (Join join : joins) {
         if (join.getRightItem() != null) {
           processFromItem(join.getRightItem());
         }
       }
     }
   }
   
 
 
 
 
   public void processWithItemsList(List<WithItem> withItemsList)
   {
     if ((withItemsList != null) && (withItemsList.size() > 0)) {
       for (WithItem item : withItemsList) {
         processSelectBody(item.getSelectBody());
       }
     }
   }
   
 
 
 
 
   public void processFromItem(FromItem fromItem)
   {
     if ((fromItem instanceof SubJoin)) {
       SubJoin subJoin = (SubJoin)fromItem;
       if ((subJoin.getJoin() != null) && 
         (subJoin.getJoin().getRightItem() != null)) {
         processFromItem(subJoin.getJoin().getRightItem());
       }
       
       if (subJoin.getLeft() != null) {
         processFromItem(subJoin.getLeft());
       }
     } else if ((fromItem instanceof SubSelect)) {
       SubSelect subSelect = (SubSelect)fromItem;
       if (subSelect.getSelectBody() != null) {
         processSelectBody(subSelect.getSelectBody());
       }
     } else if (!(fromItem instanceof ValuesList))
     {
       if ((fromItem instanceof LateralSubSelect)) {
         LateralSubSelect lateralSubSelect = (LateralSubSelect)fromItem;
         if (lateralSubSelect.getSubSelect() != null) {
           SubSelect subSelect = lateralSubSelect.getSubSelect();
           if (subSelect.getSelectBody() != null) {
             processSelectBody(subSelect.getSelectBody());
           }
         }
       }
     }
   }
   
 
 
 
 
 
   public boolean orderByHashParameters(List<OrderByElement> orderByElements)
   {
     if (orderByElements == null) {
       return false;
     }
     for (OrderByElement orderByElement : orderByElements) {
       if (orderByElement.toString().contains("?")) {
         return true;
       }
     }
     return false;
   }
   
 
 
 
 
 
   public boolean selectItemsHashParameters(List<SelectItem> selectItems)
   {
     if (selectItems == null) {
       return false;
     }
     for (SelectItem selectItem : selectItems) {
       if (selectItem.toString().contains("?")) {
         return true;
       }
     }
     return false;
   }
 }


