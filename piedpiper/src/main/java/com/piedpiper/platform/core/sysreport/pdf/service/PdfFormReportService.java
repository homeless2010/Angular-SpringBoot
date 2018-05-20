package com.piedpiper.platform.core.sysreport.pdf.service;

import com.piedpiper.platform.core.sysreport.domain.ReportFormModel;
import com.piedpiper.platform.core.sysreport.domain.ReportTitleModel;
import java.io.OutputStream;
import java.util.Collection;

public abstract interface PdfFormReportService
{
  public static final String SERVICE_ID = "bdf.pdfFormReportService";
  
  public abstract void generateFormReport(ReportTitleModel paramReportTitleModel, Collection<ReportFormModel> paramCollection, OutputStream paramOutputStream)
    throws Exception;
}


