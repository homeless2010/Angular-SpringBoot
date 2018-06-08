 package com.piedpiper.platform.core.rest.msg;
 
 import java.io.Serializable;
 
 
 public class Muti1Bean<D1>
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   private D1 dto1 = null;
   
 
   public Muti1Bean() {}
   
   public Muti1Bean(D1 dto1)
   {
     this.dto1 = dto1;
   }
   
   public D1 getDto1()
   {
     return (D1)this.dto1;
   }
   
   public void setDto1(D1 dto1) { this.dto1 = dto1; }
 }


