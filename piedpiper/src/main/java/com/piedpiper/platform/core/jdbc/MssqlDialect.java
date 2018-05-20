 package com.piedpiper.platform.core.jdbc;
 
 
 
 
 
 
 
 
 
 class MssqlDialect
   extends AbstractDialect
 {
   public String sequenceSql(String sequenceName)
   {
     throw new UnsupportedOperationException();
   }
   
   public String pageSql(String selectSql, int maxResults, int firstResult)
   {
     if (firstResult <= 0) {
       String sql2 = selectSql.toLowerCase();
       int selectIndex = sql2.indexOf("select");
       int selectDistinctIndex = sql2.indexOf("select distinct");
       int splitIndex = selectIndex + (selectDistinctIndex == selectIndex ? 15 : 6);
       
       StringBuffer sSql = new StringBuffer(selectSql.length() + 10);
       return sSql.append(selectSql).insert(splitIndex, " top " + maxResults).toString();
     }
     String orderby = "order by current_timestamp";
     StringBuilder b = new StringBuilder(selectSql.toLowerCase());
     int orderByIndex = b.indexOf("order by");
     if (orderByIndex > 0) {
       orderby = b.substring(orderByIndex);
       b.delete(orderByIndex, b.length());
     }
     
     int distinctIndex = b.indexOf("distinct");
     if (distinctIndex > 0) {
       b.delete(distinctIndex, distinctIndex + "distinct".length() + 1);
       String select = b.substring(b.indexOf("select") + "select".length(), b.indexOf("from"));
       String groupFields = select.replaceAll("\\sas[^,]+(,?)", "$1");
       b.append(" group by").append(groupFields);
     }
     
     int selectEndIndex = b.indexOf("select") + "select".length();
     b.insert(selectEndIndex, " row_number() over (" + orderby + ") as rownum_,");
     
     b.insert(0, "with query as (").append(") select * from query ");
     b.append("where rownum_ between " + (firstResult + 1) + " and " + (firstResult + maxResults));
     
     return b.toString();
   }
   
 
   public boolean supportASWithTableAlias()
   {
     return true;
   }
   
   public boolean supportOrderInSubquery()
   {
     return false;
   }
   
 
   public String countSql(String selectSql)
   {
     return null;
   }
 }


