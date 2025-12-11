package utilities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="DataProviderForLogin")
	public Iterator<Object[]> getDataForPets() throws IOException{
		String filePathString = System.getProperty("user.dir")+ File.separator + "testData" + File.separator + "testData.xlsx";
			ExcelUtilities utilities = new ExcelUtilities(filePathString);
			int rowCount =  utilities.getRowCount(utilities.getSheetName(1));
			int cellCount = utilities.getCellCount(utilities.getSheetName(1), 1);
			ArrayList<Object[]> list = new ArrayList<>();
		
		for(int i=1; i<=rowCount;i++) {
			HashMap<String, String> returnData = new HashMap<>();
			for(int j=0;j<cellCount;j++) {
				returnData.putAll(utilities.readDataFromExcelForLogin(utilities.getSheetName(1), i, j));
			
			}
			list.add(new Object[] {returnData});
		}
		return list.iterator();
	}
}
