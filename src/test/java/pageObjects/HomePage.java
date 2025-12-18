package pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage {

Logger logger = LogManager.getLogger(HomePage.class);
	
	@FindBy(how=How.XPATH, using ="//ul/li/a[text()='Register']")
	@CacheLookup
	private WebElement registerButton;
	
	@FindBy(xpath = "//i/following::span[text()='My Account']")
	@CacheLookup
	private WebElement myAccountBtn;
	
	@FindBy(how=How.XPATH, using ="//ul/li/a[text()='Login']")
	@CacheLookup
	private WebElement loginButton;
	
public void performAction(Object...args) {
		
		
		String[] fields = args[0].toString().split("=>");
		
		for(String action : fields) {
		try {
		switch (action.replace(" ", "").toUpperCase()) {
		case "MYACCOUNT":
			DriverUtility.flunetWaitForPageLoad(DriverUtility.getDriver(), myAccountBtn);
			myAccountBtn.click();
			logger.info("Clicked on "+action);
			break;
		case "REGISTER":
			registerButton.click();
			logger.info("Clicked on "+ action);
			break;
		case "LOGIN":
			loginButton.click();
			logger.info("Clicked on "+ action);
			break;
			
		default:
			logger.info("Action "+action+ " is not applicable");
			break;
		}
		}catch (Exception e) {
			logger.error("Could not click on "+action+ " "+ e);
			throw e;
		}
		}
	}
}
