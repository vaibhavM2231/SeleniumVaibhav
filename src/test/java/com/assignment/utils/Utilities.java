package com.assignment.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Utilities {

	public static final int IMPLICIT_WAIT_TIME = 10;
	
	public static final int GLOBAL_WAIT_TIME = 10;

	public static final int PAGE_LOAD_TIMEOUT = 5;

	static String filePath = System.getProperty("user.dir")+"/src/test/resources/testData/TestData.xlsx";
	public static Object[] [] getDataFromExcel(String sheetName) {
			
		XSSFWorkbook wb = null;
		try {
			wb = new XSSFWorkbook(new FileInputStream(new File(filePath)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XSSFSheet sheet = wb.getSheet(sheetName);
		int rows = sheet.getLastRowNum();
		int columns = sheet.getRow(0).getLastCellNum();

		Object[] [] data = new Object[rows][columns];
		for(int i=0; i<rows;i++) {
			XSSFRow row = sheet.getRow(i+1);
			for(int j=0;j<row.getLastCellNum();j++) {

				XSSFCell cell = row.getCell(j);
				int cellType = cell.getCellType();

				switch(cellType) {
				case 0:
					data[i][j] = Integer.toString((int) cell.getNumericCellValue());
					break;
				case 1:
					data[i][j] =  cell.getStringCellValue();
					break;
				case 3:
					data [i][j] = "";
					break;
				}		
			}	
		}
		return data;

	}

}
