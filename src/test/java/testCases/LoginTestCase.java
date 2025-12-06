package testCases;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.DriverUtility;

public class LoginTestCase {

	
	@BeforeMethod(alwaysRun = true)
	public void setUp() throws IOException {
		DriverUtility.getData();
		DriverUtility.setBrowser();
	}
	@Test
	public void loginTest() {
		pageObjects.Login obj;
		try {
			obj = DriverUtility.getpage(pageObjects.Login.class);
			obj.performAction("my account => login");
		
	}catch (Exception e) {
		// TODO: handle exception
	}
	}
}
