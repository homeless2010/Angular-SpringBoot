 package com.piedpiper.platform.core.sysreport.domain;
 
 
 public class TitleStyle
 {
   private int[] bgColor;
   private int[] fontColor;
   private int fontSize;
   
   public int[] getBgColor()
   {
     return this.bgColor;
   }
   
   public int[] getFontColor() { return this.fontColor; }
   
   public int getFontSize() {
     return this.fontSize;
   }
   
   public void setBgColor(int[] bgColor) { this.bgColor = bgColor; }
   
   public void setFontColor(int[] fontColor) {
     this.fontColor = fontColor;
   }
   
   public void setFontSize(int fontSize) { this.fontSize = fontSize; }
 }


