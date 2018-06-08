package com.piedpiper.platform.core.excel.imp;

import com.piedpiper.platform.core.excel.imp.entity.ExcelImportResult;
import com.piedpiper.platform.core.excel.imp.executor.AbstractExcutor;
import com.piedpiper.platform.core.excel.imp.inf.IExcelImport;
import com.piedpiper.platform.core.excel.imp.inf.IcommInfo;
import java.io.File;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;

public abstract class AbastractExcelImport implements IcommInfo, IExcelImport {
	protected AbstractExcutor excutor;
	private final ExcelImportResult result;
	private NumberFormat n = new DecimalFormat("#.#");
	private static final String msg_ = "共{0}条记录，成功导入:{1}条,失败:{2}条.";

	static {
		File f = new File(ExcelImportConstans.EXCEL_IMPORT_ERROR_PATH);
		if (!f.exists())
			f.mkdir();
	}

	protected String resultFile = "";

	private String message;

	protected int successRows = 0;

	protected int failRows = 0;

	protected int rowTotal = -1;

	public AbastractExcelImport() {
		this.result = new ExcelImportResult();
	}

	public void setExcutor(AbstractExcutor excutor) {
		this.excutor = excutor;
		System.out.println("");
	}

	public static void main(String[] args) throws Exception {
		String dd = "1.5901092749E10";
		NumberFormat n = new DecimalFormat("#.#");
		Number ddd = n.parse(dd);

		System.out.println(ddd);
	}

	protected Object getContentFromExcelByPOI(Cell cell) throws Exception {
		if (cell == null) {
			return "";
		}
		int ctype = cell.getCellType();
		if (ctype == 0) {
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				return cell.getDateCellValue();
			}
			double number = cell.getNumericCellValue();
			double intNumber = (int) Math.floor(number);
			if (number - intNumber == 0.0D) {
				return Integer.valueOf((int) number);
			}
			return this.n.parse(String.valueOf(number));
		}

		if (ctype == 1)
			return cell.getStringCellValue();
		if (ctype == 4)
			return Boolean.valueOf(cell.getBooleanCellValue());
		if (ctype == 2)
			return cell.getCellFormula();
		if (ctype == 5) {
			return Byte.valueOf(cell.getErrorCellValue());
		}
		return "";
	}

	protected void setErrorExcelForPOI(HSSFCell cellNew, Cell cell) {
		if (cell != null) {
			int ctype = cell.getCellType();
			if (ctype == 0) {
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					cellNew.setCellValue(cell.getDateCellValue());
				} else {
					double number = cell.getNumericCellValue();
					double intNumber = (int) Math.floor(number);
					if (number - intNumber == 0.0D) {
						cellNew.setCellValue((int) number);
					} else {
						cellNew.setCellValue(number);
					}
				}
			} else if (ctype == 1) {
				cellNew.setCellValue(cell.getStringCellValue());
			} else if (ctype == 4) {
				cellNew.setCellValue(cell.getBooleanCellValue());
			} else if (ctype == 2) {
				cellNew.setCellValue(cell.getCellFormula());
			} else if (ctype == 5) {
				cellNew.setCellValue(cell.getErrorCellValue());
			}
		}
	}

	public void importExcel() throws Exception {
		try {
			doPretreatment();

			doValidateExcelTemplet();

			doImportByExcutor();

			if (this.successRows != this.rowTotal) {
				doGenerateErrorExcel();
			}
			this.message = MessageFormat.format("共{0}条记录，成功导入:{1}条,失败:{2}条.",
					new Object[] { Integer.valueOf(this.rowTotal), Integer.valueOf(this.successRows),
							Integer.valueOf(this.failRows) });
		} catch (Exception e) {
			this.message = e.getLocalizedMessage();
			this.result.setException(e);
		} finally {
			this.result.setMessage(this.message);
			this.result.setErrorFile(this.resultFile);
			this.result.setSuccessRecord(this.successRows);
			this.result.setFailRecord(this.failRows);
			this.result.setTotalRecord(this.rowTotal);
			this.result.setResult(this.successRows == this.rowTotal);
			this.result.setNameImport(this.excutor.getName());
		}
	}

	public ExcelImportResult getResult() {
		return this.result;
	}

	protected abstract void doPretreatment() throws Exception;

	protected abstract void doValidateExcelTemplet() throws Exception;

	protected abstract void doImportByExcutor();

	protected abstract void doGenerateErrorExcel() throws Exception;
}
