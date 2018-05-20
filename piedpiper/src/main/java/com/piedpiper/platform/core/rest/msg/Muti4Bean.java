 package com.piedpiper.platform.core.rest.msg;
 
 import java.io.Serializable;
 
 
 public class Muti4Bean<D1, D2, D3, D4>
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   private D1 dto1 = null;
   private D2 dto2 = null;
   private D3 dto3 = null;
   private D4 dto4 = null;
   
   public Muti4Bean() {}
   
   public Muti4Bean(D1 dto1, D2 dto2, D3 dto3, D4 dto4)
   {
     this.dto1 = dto1;
     this.dto2 = dto2;
     this.dto3 = dto3;
     this.dto4 = dto4;
   }
   
   public D1 getDto1() {
     return (D1)this.dto1;
   }
   
   public void setDto1(D1 dto1) { this.dto1 = dto1; }
   
   public D2 getDto2() {
     return (D2)this.dto2;
   }
   
   public void setDto2(D2 dto2) { this.dto2 = dto2; }
   
   public D3 getDto3() {
     return (D3)this.dto3;
   }
   
   public void setDto3(D3 dto3) { this.dto3 = dto3; }
   
   public D4 getDto4() {
     return (D4)this.dto4;
   }
   
   public void setDto4(D4 dto4) { this.dto4 = dto4; }
 }


