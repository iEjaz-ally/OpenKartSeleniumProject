package pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Login {
	Logger logger = LogManager.getLogger(getClass());

	@FindBy(how=How.XPATH, using ="//ul/li/a[text()='Login']")
	@CacheLookup
	private WebElement loginButton;
	
	@FindBy(xpath = "//i/following::span[text()='My Account']")
	@CacheLookup
	private WebElement myAccountBtn;
	
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
		case "LOGIN":
			loginButton.click();
			logger.info("Clicked on "+ action);
			break;
		
		}
		}catch (Exception e) {
			logger.error("Could not click on "+action+ " "+ e);
		}
		}
	}

}
