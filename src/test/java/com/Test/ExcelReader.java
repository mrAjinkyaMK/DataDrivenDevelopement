package com.Test;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExcelReader {

	public static XSSFWorkbook workbook;

	public ExcelReader(String path) {
		FileInputStream fis;
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getRowCount(int sheetIndex) {

		return workbook.getSheetAt(sheetIndex).getLastRowNum() + 1;
	}

	public int getColumnCOunt(int sheetIndex) {
		return workbook.getSheetAt(sheetIndex).getRow(0).getLastCellNum();
	}

	public Object getSpecificCellValue(int sheetIndex, int row, int column) {

		XSSFCell cell = workbook.getSheetAt(sheetIndex).getRow(row).getCell(column);

		if (cell == null) {
			return "";
		}
		if (cell != null) {
			if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING)
				return cell.getStringCellValue();
			if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC)
				return cell.getRawValue();
			if (cell.getCellType() == XSSFCell.CELL_TYPE_BOOLEAN)
				return cell.getBooleanCellValue();
			if (cell.getCellType() == XSSFCell.CELL_TYPE_FORMULA)
				return cell.getCellFormula();
		}

		return null;
	}
}
