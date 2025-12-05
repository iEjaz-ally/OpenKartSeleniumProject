package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {

	
	static FileInputStream inputStream;
	XSSFWorkbook workBook;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell;
	String filePathString;
	
	public ExcelUtilities(String filePath) throws FileNotFoundException {
		this.filePathString = filePath;
		
	}

	public String getSheetName(int sheetNo) throws IOException {
		inputStream = new FileInputStream(filePathString);
		workBook = new XSSFWorkbook(inputStream);
		workBook.close();
		inputStream.close();

		return workBook.getSheetName(sheetNo);
	}
	public int getRowCount(String sheetName) throws IOException {
		inputStream = new FileInputStream(filePathString);
		workBook = new XSSFWorkbook(inputStream);
		sheet = workBook.getSheet(sheetName);
		int rowCount = sheet.getPhysicalNumberOfRows();
		workBook.close();
		inputStream.close();
		return rowCount;
	}
	public int getCellCount(String sheetName, int rowNo) throws IOException {
		
		workBook = new XSSFWorkbook(inputStream);
		sheet = workBook.getSheet(sheetName);
		row = sheet.getRow(rowNo);
		int cellCount = row.getLastCellNum();
		workBook.close();
		inputStream.close();
		return  cellCount;
	}
	public String getCellData(String sheetName, int rowNo, int cellNo) {
		String dataString = "";
	try {
		inputStream = new FileInputStream(filePathString);
		workBook = new XSSFWorkbook(inputStream);
	
		sheet = workBook.getSheet(sheetName);
		row = sheet.getRow(rowNo);
		cell = row.getCell(cellNo);
		
		DataFormatter formatter = new DataFormatter();
		
		 dataString = formatter.formatCellValue(cell);
		
	}catch (Exception e) {
		dataString = " NullValue";
	}
	return dataString;
	}
}

