 package com.piedpiper.platform.core.sysreport.pdf.domain;
 
 import java.io.Serializable;
 
 
 
 
 
 
 public class ReportData
   implements Serializable
 {
   private static final long serialVersionUID = 4070768286694803327L;
   private TextChunk textChunk;
   private int align;
   private LabelData labelData;
   private int colSpan;
   private int rowSpan;
   
   public ReportData(TextChunk textChunk)
   {
     this.textChunk = textChunk;
   }
   
   public TextChunk getTextChunk() {
     return this.textChunk;
   }
   
   public void setTextChunk(TextChunk textChunk) {
     this.textChunk = textChunk;
   }
   
   public int getAlign() {
     return this.align;
   }
   
   public void setAlign(int align) {
     this.align = align;
   }
   
   public LabelData getLabelData() {
     return this.labelData;
   }
   
   public void setLabelData(LabelData labelData) {
     this.labelData = labelData;
   }
   
   public int getColSpan() {
     return this.colSpan;
   }
   
   public void setColSpan(int colSpan) {
     this.colSpan = colSpan;
   }
   
   public int getRowSpan() {
     return this.rowSpan;
   }
   
   public void setRowSpan(int rowSpan) {
     this.rowSpan = rowSpan;
   }
 }


