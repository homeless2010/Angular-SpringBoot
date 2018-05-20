 package com.piedpiper.platform.core.sysreport.domain;
 
 import java.io.Serializable;
 import java.util.List;
 
 public class ReportFormModel
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   private int columnCount;
   private boolean showBorder;
   private List<ReportFormDataModel> listReportFormData;
   
   public int getColumnCount()
   {
     return this.columnCount;
   }
   
   public boolean isShowBorder() { return this.showBorder; }
   
   public List<ReportFormDataModel> getListReportFormDataModel() {
     return this.listReportFormData;
   }
   
   public void setColumnCount(int columnCount) { this.columnCount = columnCount; }
   
   public void setShowBorder(boolean showBorder) {
     this.showBorder = showBorder;
   }
   
   public void setListReportFormDataModel(List<ReportFormDataModel> listReportFormData) { this.listReportFormData = listReportFormData; }
 }


