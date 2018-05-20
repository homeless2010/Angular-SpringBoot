 package com.piedpiper.platform.core.shiroSecurity.passwordencoder;
 
 public abstract class BaseDigestPasswordEncoder extends BasePasswordEncoder
 {
   private boolean encodeHashAsBase64;
   
   public BaseDigestPasswordEncoder()
   {
     this.encodeHashAsBase64 = false;
   }
   
   public boolean getEncodeHashAsBase64()
   {
     return this.encodeHashAsBase64;
   }
   
   public void setEncodeHashAsBase64(boolean encodeHashAsBase64)
   {
     this.encodeHashAsBase64 = encodeHashAsBase64;
   }
 }


