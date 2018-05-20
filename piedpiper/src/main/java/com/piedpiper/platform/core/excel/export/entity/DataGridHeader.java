 package com.piedpiper.platform.core.excel.export.entity;
 
 import java.io.Serializable;
 
 
 
 
 
 
 
 
 public final class DataGridHeader
   implements Serializable, Cloneable
 {
   private static final long serialVersionUID = 1L;
   private String index;
   private String title;
   private String field;
   private String width;
   private String rowspan;
   private String colspan;
   private String align;
   private String halign;
   private String hidden;
   private String checkbox;
   private String errorColumn = "false";
   
   public DataGridHeader clone()
   {
     try {
       return (DataGridHeader)super.clone();
     } catch (CloneNotSupportedException e) {}
     return null;
   }
   
   public String getIndex() {
     return this.index;
   }
   
   public void setIndex(String index) {
     this.index = index;
   }
   
   public String getTitle() {
     return this.title;
   }
   
   public void setTitle(String title) {
     this.title = title;
   }
   
   public String getField() {
     return this.field;
   }
   
   public void setField(String field) {
     this.field = field;
   }
   
   public String getWidth() {
     return this.width;
   }
   
   public void setWidth(String width) {
     this.width = width;
   }
   
   public String getRowspan() {
     return this.rowspan;
   }
   
   public void setRowspan(String rowspan) {
     this.rowspan = rowspan;
   }
   
   public String getColspan() {
     return this.colspan;
   }
   
   public void setColspan(String colspan) {
     this.colspan = colspan;
   }
   
   public String getAlign() {
     return this.align;
   }
   
   public void setAlign(String align) {
     this.align = align;
   }
   
   public String getHalign() {
     return this.halign;
   }
   
   public void setHalign(String halign) {
     this.halign = halign;
   }
   
   public String getHidden() {
     return this.hidden;
   }
   
   public void setHidden(String hidden) {
     this.hidden = hidden;
   }
   
   public String getCheckbox() {
     return this.checkbox;
   }
   
   public void setCheckbox(String checkbox) {
     this.checkbox = checkbox;
   }
   
   public String getErrorColumn() {
     return this.errorColumn;
   }
   
   public void setErrorColumn(String errorColumn) {
     this.errorColumn = errorColumn;
   }
 }


