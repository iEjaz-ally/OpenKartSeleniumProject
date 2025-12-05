package testCases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import pageObjects.DriverUtility;

public class AccountRegistrationTestCase {
	
	@Test
	public void registeringUser() {
		
		pageObjects.AccountRegistration obj = DriverUtility.getpage(pageObjects.AccountRegistration.class);
		
		obj.performAction("My Account => Register");
		
		obj.enterDetails("FIrst Name = Johnny => Last Name = Bravo => Email = johnny0802@abc.com => Telephone = 9876563");
		
		obj.enterDetails("password = !@#POa => password confirm = !@#POa => Subscribe = Yes");
		
		obj.performAction("Privacy policy => Continue");
		
		obj.verifymessages("successfull registration");
		
		obj.performAction("Continue");
	}
	///@Test(dependsOnMethods = "registeringUser")
	public void registeringUserDuplicateuser() {
		
		pageObjects.AccountRegistration obj = DriverUtility.getpage(pageObjects.AccountRegistration.class);
		
		obj.performAction("My Account => Register");
		
		obj.enterDetails("FIrst Name = Johnny => Last Name = Bravo => Email = johnny345@abc.com => Telephone = 9876563");
		
		obj.enterDetails("password = !@#POa => password confirm = !@#POa => Subscribe = Yes");
		
		obj.performAction("Privacy policy => Continue");
		
		obj.verifymessages("Duplicate Registration");
	}
	@AfterMethod
	public void  breakDown() {
		//DriverUtility.getDriver().close();
		//DriverUtility.getDriver().quit();
	}
}
 