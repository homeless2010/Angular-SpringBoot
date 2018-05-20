 package com.piedpiper.platform.core.sysreport.domain;
 
 import java.util.List;
 
 
 
 
 public class ReportGridModel
 {
   private List<ReportGridHeaderModel> gridHeaderModelList;
   private ReportGridDataModel gridDataModel;
   private String fileName;
   private int columnCount;
   private int maxHeaderLevel;
   private boolean showBorder = true;
   private boolean showHeader = true;
   
   public List<ReportGridHeaderModel> getGridHeaderModelList() { return this.gridHeaderModelList; }
   
   public ReportGridDataModel getGridDataModel() {
     return this.gridDataModel;
   }
   
   public String getFileName() { return this.fileName; }
   
   public int getColumnCount() {
     return this.columnCount;
   }
   
   public int getMaxHeaderLevel() { return this.maxHeaderLevel; }
   
   public boolean isShowBorder() {
     return this.showBorder;
   }
   
   public boolean isShowHeader() { return this.showHeader; }
   
   public void setGridHeaderModelList(List<ReportGridHeaderModel> gridHeaderModelList)
   {
     this.gridHeaderModelList = gridHeaderModelList;
   }
   
   public void setGridDataModel(ReportGridDataModel gridDataModel) { this.gridDataModel = gridDataModel; }
   
   public void setFileName(String fileName) {
     this.fileName = fileName;
   }
   
   public void setColumnCount(int columnCount) { this.columnCount = columnCount; }
   
   public void setMaxHeaderLevel(int maxHeaderLevel) {
     this.maxHeaderLevel = maxHeaderLevel;
   }
   
   public void setShowBorder(boolean showBorder) { this.showBorder = showBorder; }
   
   public void setShowHeader(boolean showHeader) {
     this.showHeader = showHeader;
   }
 }


