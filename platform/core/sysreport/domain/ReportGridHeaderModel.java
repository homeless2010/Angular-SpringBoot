 package com.piedpiper.platform.core.sysreport.domain;
 
 import java.io.Serializable;
 import java.util.ArrayList;
 import java.util.List;
 
 public class ReportGridHeaderModel
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   private String columnName;
   private String label;
   private int fontSize;
   private int[] bgColor;
   private int[] fontColor;
   private int level;
   private int align;
   private int width;
   private ReportGridHeaderModel parent;
   private List<ReportGridHeaderModel> headers = new ArrayList();
   
   public String getColumnName() { return this.columnName; }
   
   public String getLabel() {
     return this.label;
   }
   
   public int getFontSize() { return this.fontSize; }
   
   public int[] getBgColor() {
     return this.bgColor;
   }
   
   public int[] getFontColor() { return this.fontColor; }
   
   public int getLevel() {
     return this.level;
   }
   
   public int getAlign() { return this.align; }
   
   public int getWidth() {
     return this.width;
   }
   
   public ReportGridHeaderModel getParent() { return this.parent; }
   
   public List<ReportGridHeaderModel> getHeaders() {
     return this.headers;
   }
   
   public void setColumnName(String columnName) { this.columnName = columnName; }
   
   public void setLabel(String label) {
     this.label = label;
   }
   
   public void setFontSize(int fontSize) { this.fontSize = fontSize; }
   
   public void setBgColor(int[] bgColor) {
     this.bgColor = bgColor;
   }
   
   public void setFontColor(int[] fontColor) { this.fontColor = fontColor; }
   
   public void setLevel(int level) {
     this.level = level;
   }
   
   public void setAlign(int align) { this.align = align; }
   
   public void setWidth(int width) {
     this.width = width;
   }
   
   public void setParent(ReportGridHeaderModel parent) { this.parent = parent; }
   
   public void setHeaders(List<ReportGridHeaderModel> headers) {
     this.headers = headers;
   }
   
   public void addGridHeader(ReportGridHeaderModel header) { this.headers.add(header); }
 }


