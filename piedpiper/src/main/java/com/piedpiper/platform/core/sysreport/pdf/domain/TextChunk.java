 package com.piedpiper.platform.core.sysreport.pdf.domain;
 
 import java.io.Serializable;
 
 
 
 
 
 public class TextChunk
   implements Serializable
 {
   private static final long serialVersionUID = -958134552108683L;
   private String text;
   private int[] fontColor = { 0, 0, 0 };
   private int fontSize = 9;
   private int fontStyle = 0;
   private int align = 1;
   
   public String getText() { return this.text; }
   
   public void setText(String text) {
     this.text = text;
   }
   
   public int[] getFontColor() { return this.fontColor; }
   
   public void setFontColor(int[] fontColor) {
     if (fontColor.length != 3) {
       throw new IllegalArgumentException("设置颜色的RBG值不合法!");
     }
     this.fontColor = fontColor;
   }
   
   public int getFontSize() { return this.fontSize; }
   
   public void setFontSize(int fontSize) {
     this.fontSize = fontSize;
   }
   
   public int getFontStyle() { return this.fontStyle; }
   
   public void setFontStyle(int fontStyle) {
     this.fontStyle = fontStyle;
   }
   
   public int getAlign() { return this.align; }
   
   public void setAlign(int align) {
     this.align = align;
   }
 }


