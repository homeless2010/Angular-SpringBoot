package com.piedpiper.platform.core.excel.export.executor;

import com.piedpiper.platform.core.excel.export.IExportComInfo;
import com.piedpiper.platform.core.excel.export.datasource.IExportDataSourceOut;
import com.piedpiper.platform.core.excel.export.entity.DataGridHeader;
import com.piedpiper.platform.core.excel.export.headersource.IExportDataGridHeaderSource;
import com.piedpiper.platform.core.excel.export.inf.IFormatField;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.StringUtils;

public final class ExcelExecutor {
	public static final String EMPTY = "";
	private IExportDataGridHeaderSource headerSource;
	private IExportDataSourceOut dataSource;
	private IExportComInfo info;
	private List<DataGridHeader> dataGridHeader;
	private Map<String, Object>[] data;
	private DataGridHeader numHeader;
	private HSSFWorkbook wb;
	private HSSFSheet sheet;
	private IFormatField formatField;

	public ExcelExecutor() {
		this.numHeader = new DataGridHeader();
		this.numHeader.setField("rowNum");
		this.numHeader.setTitle("序号");
		this.numHeader.setWidth("50");
	}

	public void setHeader(IExportDataGridHeaderSource header) {
		this.headerSource = header;
	}

	public void setData(IExportDataSourceOut data) {
		this.dataSource = data;
	}

	public void setInfo(IExportComInfo info) {
		this.info = info;
	}

	public void setFormatField(IFormatField formatField) {
		this.formatField = formatField;
	}

	public Workbook execut() {
		this.dataGridHeader = this.headerSource.getDataGridHeader();

		dealNumHeader();

		this.data = this.dataSource.getData();
		if (null == this.data) {
			this.data = new HashMap[0];
		}

		this.wb = new HSSFWorkbook();
		this.sheet = this.wb.createSheet(this.info.getSheetName());

		creatHeader();

		createCell();

		return this.wb;
	}

	private void dealNumHeader() {
		if (this.headerSource.getHasRowNum()) {
			this.dataGridHeader.add(0, this.numHeader);
		}
	}

	private void creatHeader() {
		HSSFRow row = this.sheet.createRow(0);
		row.setHeightInPoints(40.0F);
		Font font = this.wb.createFont();
		font.setFontName("宋体");
		font.setFontHeight((short) 220);
		font.setBoldweight((short) 700);
		CellStyle cellStyle = createBorderedStyle(this.wb, true);
		DataFormat format = this.wb.createDataFormat();
		cellStyle.setDataFormat(format.getFormat("@"));

		for (int i = 0; i < this.dataGridHeader.size(); i++) {
			DataGridHeader dataGridField = (DataGridHeader) this.dataGridHeader.get(i);
			cellStyle.setFont(font);

			if (StringUtils.hasLength(dataGridField.getHalign())) {
				if (dataGridField.getHalign().toLowerCase().equals("left")) {
					cellStyle.setAlignment((short) 1);
				}
				if (dataGridField.getHalign().toLowerCase().equals("right")) {
					cellStyle.setAlignment((short) 3);
				} else {
					cellStyle.setAlignment((short) 2);
				}
			} else {
				cellStyle.setAlignment((short) 2);
			}
			cellStyle.setVerticalAlignment((short) 1);
			cellStyle.setWrapText(true);
			HSSFCell cell = row.createCell(i);

			cell.setCellValue(Html2Text(dataGridField.getTitle()));
			if (StringUtils.hasLength(dataGridField.getWidth())) {
				int width = Integer.parseInt(dataGridField.getWidth()) / 6 > 255 ? 65024
						: Integer.parseInt(dataGridField.getWidth()) / 6 * 256;
				this.sheet.setColumnWidth(i, width);
			} else {
				this.sheet.setColumnWidth(i, 5120);
			}
			cell.setCellStyle(cellStyle);
		}
	}

	private void createCell() {
		Map<String, CellStyle> cellStyleMaps = createStyle(true);
		for (int i = 0; i < this.data.length; i++) {
			Map<String, Object> dataMap = this.data[i];
			HSSFRow row = this.sheet.createRow(i + 1);
			row.setHeightInPoints(22.0F);
			for (int j = 0; j < this.dataGridHeader.size(); j++) {
				DataGridHeader dataGridField = (DataGridHeader) this.dataGridHeader.get(j);

				String field = dataGridField.getField();

				HSSFCell cell = row.createCell(j);
				cell.setCellType(1);
				if (field.equals("rowNum")) {
					cell.setCellValue(i + 1);
				} else {
					Object value;
					if (this.formatField != null) {
						value = this.formatField.formatField(dataGridField.clone(), dataMap, field);
					} else {
						value = dataMap.get(field);
					}
					cell.setCellValue(convertNull(value));
				}
				cell.setCellStyle((CellStyle) cellStyleMaps.get(field));
				if (StringUtils.hasLength(dataGridField.getWidth())) {
					int width = Integer.parseInt(dataGridField.getWidth()) / 6 > 255 ? 65024
							: Integer.parseInt(dataGridField.getWidth()) / 6 * 256;
					this.sheet.setColumnWidth(j, width);
				} else {
					this.sheet.setColumnWidth(i, 5120);
				}
			}
		}
	}

	private String convertNull(Object obj) {
		if (obj == null) {
			return "";
		}
		return obj.toString();
	}

	private Map<String, CellStyle> createStyle(boolean showBorder) {
		Map<String, CellStyle> styleMaps = new HashMap();
		for (DataGridHeader header : this.dataGridHeader) {
			CellStyle cellStyle = this.wb.createCellStyle();
			if (showBorder) {
				cellStyle.setBorderRight((short) 1);
				cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
				cellStyle.setBorderBottom((short) 1);
				cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
				cellStyle.setBorderLeft((short) 1);
				cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
				cellStyle.setBorderTop((short) 1);
				cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
			}

			if (StringUtils.hasLength(header.getAlign())) {
				if (header.getAlign().toLowerCase().equals("left")) {
					cellStyle.setAlignment((short) 1);
				} else if (header.getAlign().toLowerCase().equals("right")) {
					cellStyle.setAlignment((short) 3);
				} else {
					cellStyle.setAlignment((short) 2);
				}
			} else {
				cellStyle.setAlignment((short) 2);
			}
			styleMaps.put(header.getField(), cellStyle);
		}
		return styleMaps;
	}

	private static CellStyle createBorderedStyle(Workbook wb, boolean showBorder) {
		CellStyle style = wb.createCellStyle();
		if (showBorder) {
			style.setBorderRight((short) 1);
			style.setRightBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderBottom((short) 1);
			style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderLeft((short) 1);
			style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderTop((short) 1);
			style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		}
		return style;
	}

	private String Html2Text(String inputString) {
		String htmlStr = inputString;
		String textStr = "";

		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
			String regEx_html = "<[^>]+>";
			String regEx_html1 = "<[^>]+";
			Pattern p_script = Pattern.compile(regEx_script, 2);
			Matcher m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll("");

			Pattern p_style = Pattern.compile(regEx_style, 2);
			Matcher m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll("");

			Pattern p_html = Pattern.compile(regEx_html, 2);
			Matcher m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll("");

			Pattern p_html1 = Pattern.compile(regEx_html1, 2);
			Matcher m_html1 = p_html1.matcher(htmlStr);
			htmlStr = m_html1.replaceAll("");

			textStr = htmlStr;
		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}

		return textStr;
	}
}
