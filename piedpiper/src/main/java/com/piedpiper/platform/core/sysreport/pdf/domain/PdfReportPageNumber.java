package com.piedpiper.platform.core.sysreport.pdf.domain;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfReportPageNumber extends PdfPageEventHelper {
	private BaseFont chineseFont;
	PdfTemplate total;

	public PdfReportPageNumber(BaseFont font) {
		this.chineseFont = font;
	}

	public void onOpenDocument(PdfWriter writer, Document document) {
		this.total = writer.getDirectContent().createTemplate(22.0F, 16.0F);
		this.total.setColorFill(new BaseColor(55, 55, 55));
	}

	public void onEndPage(PdfWriter writer, Document document) {
		PdfPTable table = new PdfPTable(3);
		try {
			table.setWidths(new int[] { 40, 5, 10 });
			table.setTotalWidth(100.0F);
			table.getDefaultCell().setBorder(0);
			table.getDefaultCell().setHorizontalAlignment(2);
			Font font = new Font(this.chineseFont, 8.0F);
			font.setColor(new BaseColor(55, 55, 55));
			Paragraph paragraph = new Paragraph("第 " + writer.getPageNumber() + "页 共", font);
			paragraph.setAlignment(2);
			table.addCell(paragraph);
			Image img = Image.getInstance(this.total);
			img.scaleAbsolute(11.0F, 12.0F);
			PdfPCell cell = new PdfPCell(img);
			cell.setBorder(0);
			table.addCell(cell);
			PdfPCell c = new PdfPCell(new Paragraph("页", font));
			c.setHorizontalAlignment(0);
			c.setBorder(0);
			table.addCell(c);
			float center = document.getPageSize().getWidth() / 2.0F - 60.0F;
			table.writeSelectedRows(0, -1, center, 30.0F, writer.getDirectContent());
		} catch (DocumentException de) {
			throw new ExceptionConverter(de);
		}
	}

	public void onCloseDocument(PdfWriter writer, Document document) {
		ColumnText.showTextAligned(this.total, 0, new Phrase(String.valueOf(writer.getPageNumber() - 1)), 2.0F, 2.0F,
				0.0F);
	}
}
