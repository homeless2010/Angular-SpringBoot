 package com.piedpiper.platform.core.rest.msg;
 
 import java.io.Serializable;
 
 
 public class Muti2Bean<D1, D2>
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   private D1 dto1 = null;
   private D2 dto2 = null;
   
   public Muti2Bean() {}
   
   public Muti2Bean(D1 dto1, D2 dto2)
   {
     this.dto1 = dto1;
     this.dto2 = dto2;
   }
   
   public D1 getDto1()
   {
     return (D1)this.dto1;
   }
   
   public void setDto1(D1 dto1) { this.dto1 = dto1; }
   
   public D2 getDto2() {
     return (D2)this.dto2;
   }
   
   public void setDto2(D2 dto2) { this.dto2 = dto2; }
 }


