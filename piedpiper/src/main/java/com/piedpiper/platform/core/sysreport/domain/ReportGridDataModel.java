 package com.piedpiper.platform.core.sysreport.domain;
 
 import java.io.Serializable;
 import java.util.List;
 import java.util.Map;
 
 public class ReportGridDataModel
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   private int[] contentBgColor;
   private int contentFontAlign;
   private int contentFontSize;
   private int[] contentFontColor;
   private List<Map<String, Object>> datas;
   private String treeColumn;
   
   public int[] getContentBgColor()
   {
     return this.contentBgColor;
   }
   
   public void setContentBgColor(int[] contentBgColor) { this.contentBgColor = contentBgColor; }
   
   public int getContentFontAlign() {
     return this.contentFontAlign;
   }
   
   public void setContentFontAlign(int contentFontAlign) { this.contentFontAlign = contentFontAlign; }
   
   public int getContentFontSize() {
     return this.contentFontSize;
   }
   
   public void setContentFontSize(int contentFontSize) { this.contentFontSize = contentFontSize; }
   
   public int[] getContentFontColor() {
     return this.contentFontColor;
   }
   
   public void setContentFontColor(int[] contentFontColor) { this.contentFontColor = contentFontColor; }
   
   public List<Map<String, Object>> getDatas() {
     return this.datas;
   }
   
   public void setDatas(List<Map<String, Object>> datas) { this.datas = datas; }
   
   public String getTreeColumn() {
     return this.treeColumn;
   }
   
   public void setTreeColumn(String treeColumn) { this.treeColumn = treeColumn; }
 }


