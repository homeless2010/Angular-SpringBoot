 package com.piedpiper.platform.core.rest.auth;
 
 import org.springframework.http.HttpStatus;
 
 
 
 
 public class RestException
   extends RuntimeException
 {
   private static final long serialVersionUID = -318174704351305461L;
   public HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
   
   public RestException() {}
   
   public RestException(HttpStatus status)
   {
     this.status = status;
   }
   
   public RestException(String message) {
     super(message);
   }
   
   public RestException(HttpStatus status, String message) {
     super(message);
     this.status = status;
   }
 }


