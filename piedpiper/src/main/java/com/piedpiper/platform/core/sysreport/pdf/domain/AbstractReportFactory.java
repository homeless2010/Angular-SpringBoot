 package com.piedpiper.platform.core.sysreport.pdf.domain;
 
 import com.piedpiper.platform.core.sysreport.domain.ReportFormDataModel;
 import com.piedpiper.platform.core.sysreport.domain.ReportTitleModel;
 import com.piedpiper.platform.core.sysreport.domain.TitleStyle;
 import com.itextpdf.text.BaseColor;
 import com.itextpdf.text.Chunk;
 import com.itextpdf.text.Font;
 import com.itextpdf.text.Paragraph;
 import com.itextpdf.text.pdf.BaseFont;
 import com.itextpdf.text.pdf.PdfPCell;
 import com.itextpdf.text.pdf.PdfPTable;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Collection;
 import java.util.Date;
 import java.util.List;
 
 
 
 public abstract class AbstractReportFactory
 {
   private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   protected BaseFont chineseFont;
   
   protected AbstractReportFactory() throws Exception { this.chineseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", false); }
   
   protected Chunk createChunk(TextChunk textChunk) {
     Chunk chunk = new Chunk(textChunk.getText());
     chunk.setFont(createFont(textChunk));
     return chunk;
   }
   
   protected Font createFont(TextChunk textChunk) { Font font = new Font(this.chineseFont);
     if (textChunk.getFontColor() != null) {
       int[] colors = textChunk.getFontColor();
       font.setColor(new BaseColor(colors[0], colors[1], colors[2]));
     }
     if (textChunk.getFontSize() > 0) {
       font.setSize(textChunk.getFontSize());
     }
     font.setStyle(textChunk.getFontStyle());
     return font;
   }
   
   protected Paragraph createParagraph(TextChunk textChunk) { Paragraph paragraph = new Paragraph(textChunk.getText(), createFont(textChunk));
     paragraph.setAlignment(textChunk.getAlign());
     return paragraph;
   }
   
   protected Paragraph createReportTitle(ReportTitleModel reportTitle) {
     Paragraph paragraph = new Paragraph();
     paragraph.setAlignment(1);
     if ((reportTitle != null) && (reportTitle.isShowTitle())) {
       TextChunk titleChunk = new TextChunk();
       titleChunk.setText(reportTitle.getTitle());
       titleChunk.setFontSize(reportTitle.getStyle().getFontSize());
       titleChunk.setFontColor(reportTitle.getStyle().getFontColor());
       paragraph.add(createChunk(titleChunk));
       
       paragraph.add(Chunk.NEWLINE);
       paragraph.add(Chunk.NEWLINE);
     }
     return paragraph;
   }
   
   protected PdfPTable createFormContentTable(Collection<ReportFormDataModel> datas, int columnCount, boolean showBorder) throws Exception {
     PdfPTable table = new PdfPTable(columnCount * 2);
     table.setWidthPercentage(100.0F);
     for (ReportFormDataModel data : datas)
     {
       Object value = data.getData();
       String result = "";
       if ((value instanceof Date)) {
         result = this.simpleDateFormat.format((Date)value);
         if (result.endsWith("00:00:00")) {
           result = result.substring(0, 11);
         }
       } else if (value != null) {
         result = value.toString();
       }
       TextChunk text = new TextChunk();
       text.setText(result);
       text.setAlign(data.getDataAlign());
       text.setFontStyle(data.getDataStyle());
       
       LabelData labelData = new LabelData();
       labelData.setAlign(data.getLabelAlign());
       labelData.setText(data.getLabel());
       
       PdfPCell labelCell = new PdfPCell();
       labelCell.addElement(createParagraph(labelData));
       labelCell.setHorizontalAlignment(labelData.getAlign());
       labelCell.setVerticalAlignment(5);
       
       if (!showBorder) {
         labelCell.setBorderWidth(0.0F);
       }
       table.addCell(labelCell);
       int colSpan = data.getColSpan() * 2;
       PdfPCell valueCell = new PdfPCell();
       valueCell.setColspan(colSpan - 1);
       valueCell.setHorizontalAlignment(data.getDataAlign());
       valueCell.setVerticalAlignment(5);
       valueCell.addElement(createParagraph(text));
       if (!showBorder) {
         valueCell.setBorderWidth(0.0F);
       }
       table.addCell(valueCell);
     }
     return table;
   }
   
   protected PdfPTable createGridTable(ReportDataModel dataModel, boolean isRepeatHeader) throws Exception {
     PdfPTable table = new PdfPTable(calculateGridColumnCount(dataModel.getTopColumnHeaders()));
     table.setWidthPercentage(100.0F);
     Collection<ColumnHeader> topHeaders = dataModel.getTopColumnHeaders();
     List<Integer> widths = new ArrayList();
     generateGridColumnWidths(topHeaders, widths);
     int[] values = new int[widths.size()];
     for (int i = 0; i < widths.size(); i++) {
       values[i] = ((Integer)widths.get(i)).intValue();
     }
     table.setWidths(values);
     int maxHeaderLevel = getGridMaxColumngroup(topHeaders);
     createGridColumnHeader(table, topHeaders, maxHeaderLevel);
     createGridTableDatas(table, dataModel.getReportData());
     if (isRepeatHeader) {
       table.setHeaderRows(maxHeaderLevel);
     }
     return table;
   }
   
   private void createGridColumnHeader(PdfPTable table, Collection<ColumnHeader> topHeaders, int maxHeaderLevel) throws Exception {
     for (int i = 1; i < 50; i++) {
       List<ColumnHeader> result = new ArrayList();
       generateGridHeadersByLevel(topHeaders, i, result);
       for (ColumnHeader header : result) {
         PdfPCell cell = new PdfPCell(createParagraph(header));
         if (header.getBgColor() != null) {
           int[] colors = header.getBgColor();
           cell.setBackgroundColor(new BaseColor(colors[0], colors[1], colors[2]));
         }
         cell.setVerticalAlignment(5);
         cell.setHorizontalAlignment(header.getAlign());
         cell.setColspan(header.getColspan());
         if (header.getColumnHeaders().size() == 0) {
           int rowspan = maxHeaderLevel - (header.getLevel() - 1);
           if (rowspan > 0) {
             cell.setRowspan(rowspan);
           }
         }
         table.addCell(cell);
       }
     }
   }
   
   private void generateGridHeadersByLevel(Collection<ColumnHeader> topHeaders, int level, List<ColumnHeader> result) {
     for (ColumnHeader header : topHeaders) {
       if (header.getLevel() == level) {
         result.add(header);
       }
       generateGridHeadersByLevel(header.getColumnHeaders(), level, result);
     }
   }
   
   private void generateGridColumnWidths(Collection<ColumnHeader> topHeaders, List<Integer> widths)
   {
     for (ColumnHeader header : topHeaders) {
       Collection<ColumnHeader> children = header.getColumnHeaders();
       if (children.size() == 0) {
         widths.add(Integer.valueOf(header.getWidth()));
       }
       generateGridColumnWidths(children, widths);
     }
   }
   
   private void createGridTableDatas(PdfPTable table, Collection<ReportData> datas) {
     for (ReportData data : datas) {
       PdfPCell cell = new PdfPCell(createParagraph(data.getTextChunk()));
       cell.setVerticalAlignment(5);
       int level = calculateIndentationCount(data.getTextChunk().getText());
       if (level == 0) {
         cell.setHorizontalAlignment(data.getAlign());
       } else {
         cell.setIndent(20 * level);
       }
       table.addCell(cell);
     }
   }
   
   private int getGridMaxColumngroup(Collection<ColumnHeader> topHeaders)
   {
     int max = 1;
     for (int i = 1; i < 50; i++) {
       List<ColumnHeader> result = new ArrayList();
       generateGridHeadersByLevel(topHeaders, i, result);
       if (result.size() == 0) {
         max = i - 1;
         break;
       }
     }
     return max;
   }
   
   private int calculateGridColumnCount(Collection<ColumnHeader> topHeaders) {
     int i = 0;
     for (ColumnHeader header : topHeaders) {
       i++;
       i += header.getColspan() - 1;
     }
     return i;
   }
   
   private int calculateIndentationCount(String s) { int count = 0;
     for (int i = 0; i < s.length(); i++) {
       char temp = s.charAt(i);
       if (temp == '\t') {
         count++;
       }
     }
     return count;
   }
 }


