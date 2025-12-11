package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {

	
	private static ThreadLocal<XSSFWorkbook> workBook = new ThreadLocal<>();
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell;
	String filePathString;
	
	public ExcelUtilities(String filePath) throws FileNotFoundException {
		this.filePathString = filePath;
		
	}
	public void setWorkBook() throws IOException {
		FileInputStream	inputStream = new FileInputStream(filePathString);
		try {
			inputStream = new FileInputStream(filePathString);
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			
			workBook.set(workbook);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		inputStream.close();
		
	}
	
	public XSSFWorkbook getXSSFWorkbook() {
		return workBook.get();
	}

	public String getSheetName(int sheetNo) throws IOException {
		setWorkBook();
		return getXSSFWorkbook().getSheetName(sheetNo);
		}
	public int getRowCount(String sheetName) throws IOException {
		setWorkBook();
		sheet = getXSSFWorkbook().getSheet(sheetName);
		
		return sheet.getLastRowNum()-sheet.getFirstRowNum();
	}
	public int getCellCount(String sheetName, int rowNo) throws IOException {
		
		setWorkBook();
		sheet = getXSSFWorkbook().getSheet(sheetName);
		row = sheet.getRow(rowNo);
		int cellCount = row.getLastCellNum();
		return  cellCount;
	}
	public String getCellData(String sheetName, int rowNo, int cellNo) {
		String dataString = "";
	try {
		setWorkBook();
		 
		sheet= getXSSFWorkbook().getSheet(sheetName);
		row = sheet.getRow(rowNo);
		cell = row.getCell(cellNo);
		
		DataFormatter formatter = new DataFormatter();
		
		 dataString = formatter.formatCellValue(cell);
		
	}catch (Exception e) {
		dataString = " NullValue";
	}
	return dataString;
	}

public Map<String,String> readDataFromExcelForLogin(String sheetName, int rowNum, int cellNum){
	 DataFormatter formatter = new DataFormatter();
	 HashMap<String, String> data = new HashMap<>();
	try{
		setWorkBook();
		sheet= getXSSFWorkbook().getSheet(sheetName);
		XSSFRow headerRow = sheet.getRow(0);
		XSSFRow row = sheet.getRow(rowNum);
		XSSFCell headerCell = headerRow.getCell(cellNum);
		XSSFCell celldataCell = row.getCell(cellNum);

		data.put(headerCell.getStringCellValue(), celldataCell!=null? formatter.formatCellValue(celldataCell) : " "); 
	}catch (Exception e) {
		e.printStackTrace();
	}
	return data;
}
	
	public void closeWorkBook() throws IOException {
		getXSSFWorkbook().close();
		workBook.remove();
	}
}

