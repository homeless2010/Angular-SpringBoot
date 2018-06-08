 package com.piedpiper.platform.core.rest.msg;
 
 import java.io.Serializable;
 
 
 public class Muti10Bean<D1, D2, D3, D4, D5, D6, D7, D8, D9, D10>
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   private D1 dto1 = null;
   private D2 dto2 = null;
   private D3 dto3 = null;
   private D4 dto4 = null;
   private D5 dto5 = null;
   private D6 dto6 = null;
   private D7 dto7 = null;
   private D8 dto8 = null;
   private D9 dto9 = null;
   private D10 dto10 = null;
   
 
   public Muti10Bean() {}
   
 
   public Muti10Bean(D1 dto1, D2 dto2, D3 dto3, D4 dto4, D5 dto5, D6 dto6, D7 dto7, D8 dto8, D9 dto9, D10 dto10)
   {
     this.dto1 = dto1;
     this.dto2 = dto2;
     this.dto3 = dto3;
     this.dto4 = dto4;
     this.dto5 = dto5;
     this.dto6 = dto6;
     this.dto7 = dto7;
     this.dto8 = dto8;
     this.dto9 = dto9;
     this.dto10 = dto10;
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
   
   public D3 getDto3() {
     return (D3)this.dto3;
   }
   
   public void setDto3(D3 dto3) { this.dto3 = dto3; }
   
   public D4 getDto4() {
     return (D4)this.dto4;
   }
   
   public void setDto4(D4 dto4) { this.dto4 = dto4; }
   
   public D5 getDto5() {
     return (D5)this.dto5;
   }
   
   public void setDto5(D5 dto5) { this.dto5 = dto5; }
   
   public D6 getDto6() {
     return (D6)this.dto6;
   }
   
   public void setDto6(D6 dto6) { this.dto6 = dto6; }
   
   public D7 getDto7() {
     return (D7)this.dto7;
   }
   
   public void setDto7(D7 dto7) { this.dto7 = dto7; }
   
   public D8 getDto8() {
     return (D8)this.dto8;
   }
   
   public void setDto8(D8 dto8) { this.dto8 = dto8; }
   
   public D9 getDto9() {
     return (D9)this.dto9;
   }
   
   public void setDto9(D9 dto9) { this.dto9 = dto9; }
   
   public D10 getDto10()
   {
     return (D10)this.dto10;
   }
   
   public void setDto10(D10 dto10) {
     this.dto10 = dto10;
   }
 }


