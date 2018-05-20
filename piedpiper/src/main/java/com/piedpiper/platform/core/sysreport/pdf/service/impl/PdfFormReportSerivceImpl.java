 package com.piedpiper.platform.core.sysreport.pdf.service.impl;
 
 import com.piedpiper.platform.core.sysreport.domain.ReportFormModel;
 import com.piedpiper.platform.core.sysreport.domain.ReportTitleModel;
 import com.piedpiper.platform.core.sysreport.pdf.domain.AbstractReportFactory;
 import com.piedpiper.platform.core.sysreport.pdf.domain.PdfReportPageNumber;
 import com.piedpiper.platform.core.sysreport.pdf.service.PdfFormReportService;
 import com.itextpdf.text.Chunk;
 import com.itextpdf.text.Document;
 import com.itextpdf.text.pdf.PdfWriter;
 import java.io.OutputStream;
 import java.util.Collection;
 import org.springframework.stereotype.Component;
 
 
 
 
 
 
 
 @Component
 public class PdfFormReportSerivceImpl
   extends AbstractReportFactory
   implements PdfFormReportService
 {
   public PdfFormReportSerivceImpl()
     throws Exception
   {}
   
   public void generateFormReport(ReportTitleModel reportTitle, Collection<ReportFormModel> reportFormModels, OutputStream out)
     throws Exception
   {
     Document doc = new Document();
     PdfWriter writer = PdfWriter.getInstance(doc, out);
     if (reportTitle.isShowPageNo()) {
       PdfReportPageNumber event = new PdfReportPageNumber(this.chineseFont);
       writer.setPageEvent(event);
     }
     doc.open();
     doc.add(createReportTitle(reportTitle));
     for (ReportFormModel dataModel : reportFormModels) {
       doc.add(createFormContentTable(dataModel.getListReportFormDataModel(), dataModel.getColumnCount(), reportTitle.isShowBorder()));
       doc.add(Chunk.NEWLINE);
     }
     doc.close();
   }
 }


