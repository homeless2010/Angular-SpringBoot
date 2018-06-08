 package com.piedpiper.platform.core.rest.msg;
 
 import java.io.Serializable;
 
 public class ResponseMsg<T> implements Serializable { private static final long serialVersionUID = 1L;
   private ResponseMsg<T>.Header header;
   private T responseBody;
   
   public ResponseMsg() { this.header = new Header(); }
   
 
 
   public T getResponseBody()
   {
     return (T)this.responseBody;
   }
   
   public void setResponseBody(T responseBody) {
     this.responseBody = responseBody;
   }
   
   public String getRetCode() {
     return this.header.retCode;
   }
   
   public void setRetCode(String retCode) {
     this.header.retCode = retCode;
   }
   
   public String getErrorDesc() {
     return this.header.errorDesc;
   }
   
   public void setErrorDesc(String errorDesc) {
     this.header.errorDesc = errorDesc;
   }
   
   private final class Header
   {
     String retCode = "200";
     
     String errorDesc = null;
     
     private Header() {}
   }
   
   public String toString() {
     StringBuilder builder = new StringBuilder();
     builder.append("Header [retCode=");
     builder.append(getRetCode());
     builder.append(", errorDesc=");
     builder.append(getErrorDesc());
     builder.append("]");
     builder.append("   Body [");
     if (this.responseBody != null) {
       builder.append(this.responseBody.toString());
     }
     builder.append("]");
     return builder.toString();
   }
 }


