 package com.piedpiper.platform.core.mybatis.pagehelper;
 
 import java.util.ArrayList;
 import java.util.List;
 import org.apache.ibatis.session.RowBounds;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class Page<E>
   extends ArrayList<E>
 {
   private static final long serialVersionUID = 1L;
   private static final int NO_SQL_COUNT = -1;
   private static final int SQL_COUNT = 0;
   private int pageNum;
   private int pageSize;
   private int startRow;
   private int endRow;
   private long total;
   private int pages;
   private boolean reasonable;
   
   public Page() {}
   
   public Page(int pageNum, int pageSize)
   {
     this(pageNum, pageSize, 0);
   }
   
   public Page(int pageNum, int pageSize, boolean count) {
     this(pageNum, pageSize, count ? 0 : -1);
   }
   
   public Page(int pageNum, int pageSize, int total) {
     super(pageSize > -1 ? pageSize : 0);
     this.pageNum = pageNum;
     this.pageSize = pageSize;
     this.total = total;
     calculateStartAndEndRow();
   }
   
   public Page(RowBounds rowBounds, boolean count) {
     this(rowBounds, count ? 0 : -1);
   }
   
   public Page(RowBounds rowBounds, int total)
   {
     super(rowBounds.getLimit() > -1 ? rowBounds.getLimit() : 0);
     this.pageSize = rowBounds.getLimit();
     this.startRow = rowBounds.getOffset();
     
     this.total = total;
     this.endRow = (this.startRow + this.pageSize);
   }
   
   public List<E> getResult() {
     return this;
   }
   
   public int getPages() {
     return this.pages;
   }
   
   public int getEndRow() {
     return this.endRow;
   }
   
   public int getPageNum() {
     return this.pageNum;
   }
   
   public void setPageNum(int pageNum)
   {
     this.pageNum = ((this.reasonable) && (pageNum <= 0) ? 1 : pageNum);
   }
   
   public int getPageSize() {
     return this.pageSize;
   }
   
   public void setPageSize(int pageSize) {
     this.pageSize = pageSize;
   }
   
   public int getStartRow() {
     return this.startRow;
   }
   
   public long getTotal() {
     return this.total;
   }
   
   public void setTotal(long total) {
     this.total = total;
     if (this.pageSize > 0) {
       this.pages = ((int)(total / this.pageSize + (total % this.pageSize == 0L ? 0 : 1)));
     } else {
       this.pages = 0;
     }
     
     if ((this.reasonable) && (this.pageNum > this.pages)) {
       this.pageNum = this.pages;
       calculateStartAndEndRow();
     }
   }
   
   public void setReasonable(boolean reasonable) {
     this.reasonable = reasonable;
     
     if ((this.reasonable) && (this.pageNum <= 0)) {
       this.pageNum = 1;
       calculateStartAndEndRow();
     }
   }
   
 
 
   private void calculateStartAndEndRow()
   {
     this.startRow = (this.pageNum > 0 ? (this.pageNum - 1) * this.pageSize : 0);
     this.endRow = (this.startRow + this.pageSize * (this.pageNum > 0 ? 1 : 0));
   }
   
   public boolean isCount() {
     return this.total > -1L;
   }
   
   public String toString()
   {
     return "Page{pageNum=" + this.pageNum + ", pageSize=" + this.pageSize + ", startRow=" + this.startRow + ", endRow=" + this.endRow + ", total=" + this.total + ", pages=" + this.pages + '}';
   }
 }


