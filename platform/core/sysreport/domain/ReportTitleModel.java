 package com.piedpiper.platform.core.sysreport.domain;
 
 
 
 
 
 public class ReportTitleModel
 {
   private boolean showPageNo = true;
   private boolean repeatHeader = false;
   private boolean showBorder = true;
   private boolean showTitle = false;
   private String title;
   private TitleStyle style;
   
   public boolean isShowPageNo() { return this.showPageNo; }
   
   public boolean isRepeatHeader() {
     return this.repeatHeader;
   }
   
   public boolean isShowBorder() { return this.showBorder; }
   
   public boolean isShowTitle() {
     return this.showTitle;
   }
   
   public TitleStyle getStyle() { return this.style; }
   
   public void setShowPageNo(boolean showPageNo) {
     this.showPageNo = showPageNo;
   }
   
   public void setRepeatHeader(boolean repeatHeader) { this.repeatHeader = repeatHeader; }
   
   public void setShowBorder(boolean showBorder) {
     this.showBorder = showBorder;
   }
   
   public void setShowTitle(boolean showTitle) { this.showTitle = showTitle; }
   
   public void setStyle(TitleStyle style) {
     this.style = style;
   }
   
   public String getTitle() { return this.title; }
   
   public void setTitle(String title) {
     this.title = title;
   }
 }


