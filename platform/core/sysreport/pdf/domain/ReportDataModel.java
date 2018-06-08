 package com.piedpiper.platform.core.sysreport.pdf.domain;
 
 import java.util.Collection;
 
 public class ReportDataModel implements java.io.Serializable {
   private static final long serialVersionUID = -4269131426608999898L;
   private Collection<ColumnHeader> topColumnHeaders;
   private Collection<ReportData> reportData;
   private String fileName;
   private int columnCount;
   
   public ReportDataModel(Collection<ColumnHeader> topColumnHeaders, Collection<ReportData> reportData) {
     this.topColumnHeaders = topColumnHeaders;
     this.reportData = reportData;
   }
   
   public Collection<ColumnHeader> getTopColumnHeaders() { return this.topColumnHeaders; }
   
   public void setTopColumnHeaders(Collection<ColumnHeader> topColumnHeaders) {
     this.topColumnHeaders = topColumnHeaders;
   }
   
   public Collection<ReportData> getReportData() {
     return this.reportData;
   }
   
   public void setReportData(Collection<ReportData> reportData) { this.reportData = reportData; }
   
   public String getFileName() {
     return this.fileName;
   }
   
   public void setFileName(String fileName) { this.fileName = fileName; }
   
   public int getColumnCount() {
     return this.columnCount;
   }
   
   public void setColumnCount(int columnCount) { this.columnCount = columnCount; }
 }


