 package com.piedpiper.platform.core.jdbc;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class Db2Dialect
   extends AbstractDialect
 {
   public String sequenceSql(String sequenceName)
   {
     return "values nextval for " + sequenceName;
   }
   
   public String pageSql(String selectSql, int maxResults, int firstResult)
   {
     if (firstResult <= 0) {
       return selectSql + " fetch first " + maxResults + " rows only";
     }
     int startOfSelect = selectSql.toLowerCase().indexOf("select");
     
     StringBuffer pagingSelect = new StringBuffer(selectSql.length() + 100).append(selectSql.substring(0, startOfSelect)).append("select * from ( select ").append(getRowNumberSql(selectSql));
     
 
 
 
     if ((hasUnion(selectSql)) || (hasDistinct(selectSql))) {
       pagingSelect.append(" row_.* from ( ").append(selectSql.substring(startOfSelect)).append(" ) as row_");
     }
     else
     {
       pagingSelect.append(selectSql.substring(startOfSelect + 6));
     }
     
     pagingSelect.append(" ) as temp_ where rownum_var_ ");
     pagingSelect.append("between " + (firstResult + 1) + " and " + (firstResult + maxResults));
     return pagingSelect.toString();
   }
   
   private boolean hasDistinct(String sql)
   {
     int selectindex = sql.toLowerCase().indexOf("select");
     if (selectindex >= 0) {
       String tsql = sql.trim().substring(6).trim();
       if ((tsql.substring(0, 1).equals("*")) || (tsql.substring(0, 8).equalsIgnoreCase("distinct")))
       {
         return true;
       }
     }
     return false;
   }
   
   private String getRowNumberSql(String sql) {
     StringBuffer rownumber = new StringBuffer(50).append("rownumber() over(");
     
     int orderByIndex = sql.toLowerCase().indexOf("order by");
     if ((orderByIndex > 0) && (!hasUnion(sql)) && (!hasDistinct(sql))) {
       rownumber.append(sql.substring(orderByIndex));
     }
     rownumber.append(") as rownum_var_,");
     return rownumber.toString();
   }
   
   private boolean hasUnion(String sql) {
     int selectindex = sql.toLowerCase().indexOf("select");
     if (selectindex >= 0) {
       return sql.toLowerCase().indexOf("union") >= 0;
     }
     return false;
   }
   
   public boolean supportASWithTableAlias()
   {
     return true;
   }
   
   public boolean supportOrderInSubquery()
   {
     return true;
   }
   
 
   public String countSql(String selectSql)
   {
     return selectSql;
   }
 }


