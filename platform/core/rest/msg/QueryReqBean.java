 package com.piedpiper.platform.core.rest.msg;
 
 import java.io.Serializable;
 
 public class QueryReqBean<T>
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   private PageParameter pageParameter = null;
   
 
   private T searchParams = null;
   
   private String sortExp = null;
   
   public PageParameter getPageParameter()
   {
     if (this.pageParameter == null) {
       this.pageParameter = new PageParameter();
     }
     return this.pageParameter;
   }
   
   public void setPageParameter(PageParameter pageParameter) { this.pageParameter = pageParameter; }
   
   public T getSearchParams() {
     return (T)this.searchParams;
   }
   
   public void setSearchParams(T searchParams) { this.searchParams = searchParams; }
   
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
   public String getSortExp()
   {
     return this.sortExp;
   }
   
   public void setSortExp(String sortExp) { this.sortExp = sortExp; }
   
   public String toString()
   {
     StringBuilder builder = new StringBuilder();
     builder.append("QueryReqBean [pageParameter=");
     builder.append(this.pageParameter);
     builder.append(", searchParams=");
     builder.append(this.searchParams);
     builder.append(", sortExp=");
     builder.append(this.sortExp);
     builder.append("]");
     return builder.toString();
   }
 }


