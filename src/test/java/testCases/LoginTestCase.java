package testCases;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.DriverUtility;
import pageObjects.Login;
import utilities.DataProviders;

public class LoginTestCase {

	
	@BeforeClass
	public void setUp() throws IOException {
		DriverUtility.getData();
		DriverUtility.setBrowser();
	}
	//@Test
	public void loginTest() {
		pageObjects.Login obj;
		
		pageObjects.HomePage homePage;
		try {
			obj = DriverUtility.getpage(pageObjects.Login.class);
			homePage = DriverUtility.getpage(pageObjects.HomePage.class);
			
			obj = DriverUtility.getpage(pageObjects.Login.class);
			homePage.performAction("my account => login");
			
			obj.enterDetails("Email = johnny99009@abc.com => Password = !@#POa");
			
			obj.performAction("login => my account => logout");
	}catch (Exception e) {	
		e.printStackTrace();
	}
	}
	@Test(dataProvider = "DataProviderForLogin", dataProviderClass = DataProviders.class)
	public void LoginTest1(HashMap<String, String> dataMap) {
		pageObjects.Login obj;
		pageObjects.HomePage homePage;
		try {
			obj = DriverUtility.getpage(pageObjects.Login.class);
			homePage = DriverUtility.getpage(pageObjects.HomePage.class);
			
			HashMap<String, Object> map = new HashMap<>();
			
			map.put("email", dataMap.get("Email"));
			map.put("Password", dataMap.get("Password"));			
			
			homePage.performAction("my account => login");
			
			obj.enterDetails(map);
			obj.performAction("login");
			
			String expectedString = dataMap.get("Expected").toString();
				if(expectedString.equalsIgnoreCase("Valid")) {
					obj.verifyMessages(expectedString);
					obj.performAction("my account => logout");
				}else {
					obj.verifyMessages(expectedString);
				}
		}catch (Exception e) {
			
		}
	}
	@AfterClass
	public void  breakDown() {
		DriverUtility.getDriver().quit();
	}
}
