package testCases;

import java.io.IOException;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import pageObjects.AccountRegistration;
import pageObjects.DriverUtility;

public class AccountRegistrationTestCase {
	Logger logger = LogManager.getLogger(AccountRegistrationTestCase.class);
	@BeforeClass
	public void setUp() throws IOException {
		DriverUtility.getData();
		DriverUtility.setBrowser();
	}
	@Test
	public void registeringUser() {
		
		pageObjects.AccountRegistration obj;
		
		pageObjects.HomePage homePage;
		try {
			obj = DriverUtility.getpage(pageObjects.AccountRegistration.class);
			homePage= DriverUtility.getpage(pageObjects.HomePage.class);
			
			homePage.performAction("My Account => Register");
			
			obj.enterDetails("FIrst Name = Johnny => Last Name = Bravo => Email = johnny99009@abc.com => Telephone = 9876563");
			
			obj.enterDetails("password = !@#POa => password confirm = !@#POa => Subscribe = Yes");
			
			obj.performAction("Privacy policy => Continue");
			
			obj.verifymessages("successfull registration");
			
			obj.performAction("Continue");
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		
	}
	///@Test(dependsOnMethods = "registeringUser")
	public void registeringUserDuplicateuser() throws IOException {
		
		pageObjects.AccountRegistration obj = DriverUtility.getpage(pageObjects.AccountRegistration.class);
		
		obj.performAction("My Account => Register");
		
		obj.enterDetails("FIrst Name = Johnny => Last Name = Bravo => Email = johnny345@abc.com => Telephone = 9876563");
		
		obj.enterDetails("password = !@#POa => password confirm = !@#POa => Subscribe = Yes");
		
		obj.performAction("Privacy policy => Continue");
		
		obj.verifymessages("Duplicate Registration");
	}
	@AfterClass(alwaysRun = true)
	public void  breakDown() {
		//DriverUtility.getDriver().close();
		DriverUtility.getDriver().quit();
	}
}
 