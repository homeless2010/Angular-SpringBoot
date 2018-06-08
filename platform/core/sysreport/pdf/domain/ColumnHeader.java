 package com.piedpiper.platform.core.sysreport.pdf.domain;
 
 import java.io.Serializable;
 import java.util.ArrayList;
 import java.util.List;
 
 
 
 
 
 
 
 public class ColumnHeader
   extends TextChunk
   implements Serializable
 {
   private static final long serialVersionUID = 4545085711049920810L;
   private String name;
   private int width;
   private int[] bgColor;
   private int level;
   private List<ColumnHeader> columnHeaders = new ArrayList();
   
   public ColumnHeader(int level) { this.level = level; }
   
   public String getName() {
     return this.name;
   }
   
   public int getColspan() {
     if (this.columnHeaders.size() == 0) {
       return 1;
     }
     return calculateColspan(0, this.columnHeaders);
   }
   
   private int calculateColspan(int start, List<ColumnHeader> columnHeaders) { if (start == 0) start += columnHeaders.size();
     for (ColumnHeader header : columnHeaders) {
       if (header.getColumnHeaders().size() > 0) {
         start += header.getColumnHeaders().size() - 1;
         start = calculateColspan(start, header.getColumnHeaders());
       }
     }
     return start;
   }
   
   public void setName(String name)
   {
     this.name = name;
   }
   
   public List<ColumnHeader> getColumnHeaders() {
     return this.columnHeaders;
   }
   
   public void addColumnHeader(ColumnHeader columnHeader) {
     this.columnHeaders.add(columnHeader);
   }
   
   public int getWidth() {
     return this.width;
   }
   
   public void setWidth(int width) { this.width = width; }
   
 
   public int[] getBgColor() { return this.bgColor; }
   
   public void setBgColor(int[] bgColor) {
     if (bgColor.length != 3) {
       throw new IllegalArgumentException("设置颜色的RBG值不合法!");
     }
     this.bgColor = bgColor;
   }
   
   public int getLevel() {
     return this.level;
   }
   
   public void setLevel(int level) {
     this.level = level;
   }
 }


