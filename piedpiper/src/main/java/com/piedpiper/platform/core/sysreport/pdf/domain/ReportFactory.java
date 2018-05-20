package com.piedpiper.platform.core.sysreport.pdf.domain;

import com.piedpiper.platform.core.sysreport.domain.ReportFormModel;
import com.piedpiper.platform.core.sysreport.domain.ReportTitleModel;
import java.io.OutputStream;
import java.util.Collection;
import java.util.List;

public abstract interface ReportFactory
{
  public abstract void generateReport(ReportDataModel paramReportDataModel, OutputStream paramOutputStream)
    throws Exception;
  
  public abstract void generateFormReport(ReportTitleModel paramReportTitleModel, Collection<ReportFormModel> paramCollection, OutputStream paramOutputStream)
    throws Exception;
  
  public abstract void generateGridReport(ReportTitleModel paramReportTitleModel, Collection<ReportDataModel> paramCollection, OutputStream paramOutputStream)
    throws Exception;
  
  public abstract void generateFormGridReport(ReportTitleModel paramReportTitleModel, Collection<ReportFormModel> paramCollection, List<ReportDataModel> paramList, OutputStream paramOutputStream)
    throws Exception;
}


