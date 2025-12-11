package pageObjects;

import java.util.LinkedHashMap;
import java.util.Map;

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
	
	@FindBy(xpath = "//div/descendant::input[@name='email']")
	@CacheLookup
	private WebElement emailFiElement;
	
	@FindBy(xpath= "//div/descendant::input[@name='password']")
	@CacheLookup
	private WebElement passwordFieldElement;
	
	@FindBy(xpath = "//input[@value='Login']")
	@CacheLookup
	private WebElement loginBtnElement;
	
	@FindBy(xpath = "//ul/li/a[text()='Logout']")
	@CacheLookup
	private WebElement logoffBtnElement;
	
public void performAction(Object...args) {
		
		String[] fields = args[0].toString().split("=>");
		
		for(String action : fields) {
		try {
		switch (action.replace(" ", "").toUpperCase().trim()) {
		case "LOGIN":
			loginBtnElement.click();
			logger.info("Clicked on "+action+ " button");
			break;
		case "MYACCOUNT":
			myAccountBtn.click();
			logger.info("Clicked on "+action);
			break;
		case "LOGOUT":
			DriverUtility.flunetWaitForPageLoad(DriverUtility.getDriver(), loginBtnElement);
			logoffBtnElement.clear();
			logger.info("Clicked on "+action);
			break;
		default:
			logger.info("Action "+action+ " is not applicable");
			break;	
		}
		}catch (Exception e) {
			logger.error("Could not click on "+action+ " "+ e);
		}
		}
	}
public Login enterDetails(Object...args) {
	
	String[] fieldsArrStrings = args[0].toString().split("=>");
	
	Map<String, String> fieldsMap = new LinkedHashMap<>();
	if(args[0] instanceof Map) {
		fieldsMap = (Map<String, String>) args[0];
	}else {
		for(String fieldsString : fieldsArrStrings ) {
			String[] fieldString = fieldsString.split("=");
			fieldsMap.put(fieldString[0], fieldString[1]);
	}
	}
	
	for(Map.Entry<String, String> entry : fieldsMap.entrySet()) {
		try {
			switch (entry.getKey().toUpperCase().trim().toUpperCase()) {
			case "EMAIL":
				emailFiElement.sendKeys(entry.getValue().trim());
				logger.info("Entered value"+entry.getValue().trim()+ " in field "+entry.getKey());
				break;
			case "PASSWORD":
				passwordFieldElement.sendKeys(entry.getValue().trim());
				logger.info("Entered value"+entry.getValue().trim()+ " in field "+entry.getKey());
				break;
			
			default:
				logger.info("Field "+entry.getKey()+ " is not applicable");
				throw new Exception();
			}
		
	}catch (Exception e) {
		logger.error("Could not enter into field "+e);
		System.out.println("Could not enter into field "+e);
	}	
}
	return this;
}
}
