 package com.piedpiper.platform.core.rest.msg;
 
 import com.piedpiper.platform.core.mybatis.pagehelper.Page;
 import java.io.Serializable;
 import java.util.Collection;
 
 
 public class QueryRespBean<T>
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   private PageParameter pageParameter = null;
   
   private Page<T> result = null;
   
 
   public QueryRespBean() {}
   
 
   public QueryRespBean(PageParameter pageParameter, Page<T> result)
   {
     this.pageParameter = pageParameter;
     this.result = result;
   }
   
   public PageParameter getPageParameter()
   {
     if (this.pageParameter == null) {
       this.pageParameter = new PageParameter();
     }
     return this.pageParameter;
   }
   
   public void setPageParameter(PageParameter pageParameter) { this.pageParameter = pageParameter; }
   
 
 
 
   public Page<T> getResult() { return this.result; }
   
   public void setResult(Page<T> result) {
     if ((result != null) && (this.pageParameter == null)) {
       this.pageParameter = new PageParameter();
       this.pageParameter.setPage(result.getPageNum());
       this.pageParameter.setRows(result.getPageSize());
       this.pageParameter.setTotalCount(result.getTotal());
       this.pageParameter.setTotalPage(result.getPages());
     }
     this.result = result;
   }
   
 
 
 
 
   public void setResult(Collection<T> result, String jvm7Flag)
   {
     this.result = ((Page)result);
   }
   
   public String toString()
   {
     StringBuilder builder = new StringBuilder();
     builder.append("QueryRespBean [pageParameter=");
     builder.append(this.pageParameter);
     builder.append(", result=");
     builder.append(this.result);
     builder.append("]");
     return builder.toString();
   }
 }


