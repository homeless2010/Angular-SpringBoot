package com.piedpiper.platform.core.sysreport.pdf.service;

import com.piedpiper.platform.core.sysreport.domain.ReportFormModel;
import com.piedpiper.platform.core.sysreport.domain.ReportTitleModel;
import com.piedpiper.platform.core.sysreport.pdf.domain.ReportDataModel;
import java.io.OutputStream;
import java.util.Collection;
import java.util.List;

public abstract interface PdfFormGridReportService
{
  public static final String SERVICE_ID = "bdf.pdfFormGridReportService";
  
  public abstract void generateFormGridReport(ReportTitleModel paramReportTitleModel, Collection<ReportFormModel> paramCollection, List<ReportDataModel> paramList, OutputStream paramOutputStream)
    throws Exception;
}


