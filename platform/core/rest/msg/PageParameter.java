 package com.piedpiper.platform.core.rest.msg;
 
 
 public class PageParameter
 {
   public static final int DEFAULT_PAGE_SIZE = 20;
   
   private int rows;
   
   private int page;
   
   private long totalPage;
   
   private long totalCount;
   
 
   public PageParameter()
   {
     this.page = 1;
     this.rows = 20;
   }
   
 
 
 
 
   public PageParameter(int currentPage, int pageSize)
   {
     this.page = currentPage;
     this.rows = pageSize;
   }
   
   public int getPage() {
     return this.page;
   }
   
   public void setPage(int currentPage) {
     this.page = currentPage;
   }
   
   public int getRows() {
     return this.rows;
   }
   
   public void setRows(int pageSize) {
     this.rows = pageSize;
   }
   
   public long getTotalPage() {
     return this.totalPage;
   }
   
   public void setTotalPage(long totalPage) {
     this.totalPage = totalPage;
   }
   
   public long getTotalCount() {
     return this.totalCount;
   }
   
   public void setTotalCount(long totalCount) {
     this.totalCount = totalCount;
   }
   
   public String toString()
   {
     StringBuilder builder = new StringBuilder();
     builder.append("PageParameter [pageSize=");
     builder.append(this.rows);
     builder.append(", currentPage=");
     builder.append(this.page);
     builder.append(", totalPage=");
     builder.append(this.totalPage);
     builder.append(", totalCount=");
     builder.append(this.totalCount);
     builder.append("]");
     return builder.toString();
   }
 }


