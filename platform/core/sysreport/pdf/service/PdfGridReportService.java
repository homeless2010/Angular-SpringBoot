package com.piedpiper.platform.core.sysreport.pdf.service;

import com.piedpiper.platform.core.sysreport.domain.ReportTitleModel;
import com.piedpiper.platform.core.sysreport.pdf.domain.ReportDataModel;
import java.io.OutputStream;
import java.util.Collection;

public abstract interface PdfGridReportService
{
  public static final String SERVICE_ID = "bdf.pdfGridReportService";
  
  public abstract void generateGridReport(ReportTitleModel paramReportTitleModel, Collection<ReportDataModel> paramCollection, OutputStream paramOutputStream)
    throws Exception;
}


