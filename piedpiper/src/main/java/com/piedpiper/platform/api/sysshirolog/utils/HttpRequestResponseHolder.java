 package com.piedpiper.platform.api.sysshirolog.utils;
 
 import javax.servlet.http.HttpServletResponse;
 
 public class HttpRequestResponseHolder
 {
   private javax.servlet.http.HttpServletRequest request;
   private HttpServletResponse response;
   
   public HttpRequestResponseHolder(javax.servlet.http.HttpServletRequest request, HttpServletResponse response)
   {
     this.request = request;
     this.response = response;
   }
   
   public javax.servlet.http.HttpServletRequest getRequest()
   {
     return this.request;
   }
   
   public void setRequest(javax.servlet.http.HttpServletRequest request)
   {
     this.request = request;
   }
   
   public HttpServletResponse getResponse()
   {
     return this.response;
   }
   
   public void setResponse(HttpServletResponse response)
   {
     this.response = response;
   }
 }


