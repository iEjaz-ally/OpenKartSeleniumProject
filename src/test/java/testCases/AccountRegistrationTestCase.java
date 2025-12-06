package testCases;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import pageObjects.DriverUtility;

public class AccountRegistrationTestCase {

	@BeforeMethod(alwaysRun = true)
	public void setUp() throws IOException {
		DriverUtility.getData();
		DriverUtility.setBrowser();
	}
	@Test
	public void registeringUser() {
		
		pageObjects.AccountRegistration obj;
		try {
			obj = DriverUtility.getpage(pageObjects.AccountRegistration.class);
			obj.performAction("My Account => Register");
			
			obj.enterDetails("FIrst Name = Johnny => Last Name = Bravo => Email = johnny9902@abc.com => Telephone = 9876563");
			
			obj.enterDetails("password = !@#POa => password confirm = !@#POa => Subscribe = Yes");
			
			obj.performAction("Privacy policy => Continue");
			
			obj.verifymessages("successfull registration");
			
			obj.performAction("Continue");
		} catch (IOException e) {
			e.printStackTrace();
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
	@AfterMethod(alwaysRun = true)
	public void  breakDown() {
		//DriverUtility.getDriver().close();
		DriverUtility.getDriver().quit();
	}
}
 