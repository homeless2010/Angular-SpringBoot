 package com.piedpiper.platform.core.sysreport.pdf.service.impl;
 
 import com.piedpiper.platform.core.sysreport.domain.ReportTitleModel;
 import com.piedpiper.platform.core.sysreport.pdf.domain.AbstractReportFactory;
 import com.piedpiper.platform.core.sysreport.pdf.domain.PdfReportPageNumber;
 import com.piedpiper.platform.core.sysreport.pdf.domain.ReportDataModel;
 import com.piedpiper.platform.core.sysreport.pdf.service.PdfGridReportService;
 import com.itextpdf.text.Chunk;
 import com.itextpdf.text.Document;
 import com.itextpdf.text.Paragraph;
 import com.itextpdf.text.pdf.PdfWriter;
 import java.io.OutputStream;
 import java.util.Collection;
 import org.springframework.stereotype.Component;
 
 
 
 
 
 
 
 
 
 
 
 
 
 @Component
 public class PdfGridReportServiceImpl
   extends AbstractReportFactory
   implements PdfGridReportService
 {
   public PdfGridReportServiceImpl()
     throws Exception
   {}
   
   public void generateGridReport(ReportTitleModel reportTitle, Collection<ReportDataModel> reportDataModels, OutputStream out)
     throws Exception
   {
     Document doc = new Document();
     PdfWriter writer = PdfWriter.getInstance(doc, out);
     if (reportTitle.isShowPageNo()) {
       PdfReportPageNumber event = new PdfReportPageNumber(this.chineseFont);
       writer.setPageEvent(event);
     }
     doc.open();
     Paragraph paragraph = createReportTitle(reportTitle);
     
     for (ReportDataModel dataModel : reportDataModels) {
       paragraph.add(createGridTable(dataModel, reportTitle.isRepeatHeader()));
       paragraph.add(Chunk.NEWLINE);
     }
     doc.add(paragraph);
     doc.close();
   }
 }


