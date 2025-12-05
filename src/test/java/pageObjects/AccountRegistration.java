package pageObjects;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


public class AccountRegistration {
	Logger logger = LogManager.getLogger(AccountRegistration.class);
	
	@FindBy(how=How.XPATH, using ="//ul/li/a[text()='Register']")
	@CacheLookup
	private WebElement registerButton;
	
	@FindBy(xpath = "//div/input[@name='firstname']")
	@CacheLookup
	private WebElement firstNameFieldElement;
	
	@FindBy(xpath = "//i/following::span[text()='My Account']")
	@CacheLookup
	private WebElement myAccountBtn;
	
	@FindBy(xpath = "//div/input[@name='lastname']")
	@CacheLookup
	private WebElement lastNamFieldElement;
	
	@FindBy(xpath = "//div/input[@name='email']")
	@CacheLookup
	private WebElement emailFiElement;
	
	@FindBy(xpath = "//div/input[@name='telephone']")
	@CacheLookup
	private WebElement telePhoneFieldElement;
	
	@FindBy(xpath = "//div/input[@name='password']")
	@CacheLookup
	private WebElement passwordFieldElement;
	
	@FindBy(xpath = "//div/input[@name='confirm']")
	@CacheLookup
	private WebElement confirmPasswordFieldElement;
	
	@FindBy(xpath="//input[@value='Continue']")
	@CacheLookup
	private WebElement continueBtnElement;
	
	@FindBy(xpath = "//div/a/following-sibling::input[1]")
	@CacheLookup
	private WebElement privacyPolicyCheckBoxElement;
	public void performAction(Object...args) {
		
		if(args[0] instanceof Set<?>) {
			
		}
		
		String[] fields = args[0].toString().split("=>");
		
		//String action = args[0].toString().toUpperCase().trim();
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
		case "CONTINUE":
			continueBtnElement.click();
			logger.info("Clicked on "+ action);
			break;
		case "PRIVACYPOLICY":
			privacyPolicyCheckBoxElement.click();
			logger.info("Clicked on "+ action);
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
	public void enterDetails(Object...args) {
		String[] fieldsArrStrings = args[0].toString().split("=>");
		
		Map<String, String> fieldsMap = new LinkedHashMap<>();
		
		for(String fieldsString : fieldsArrStrings ) {
			String[] fieldString = fieldsString.split("=");
			fieldsMap.put(fieldString[0], fieldString[1]);
		}
		
		for(Map.Entry<String, String> entry : fieldsMap.entrySet()) {
		try {
			switch (entry.getKey().replace(" ", "").trim().toUpperCase()) {
			case "FIRSTNAME":
				firstNameFieldElement.sendKeys(entry.getValue().trim());
				logger.info("Entered value"+entry.getValue().trim()+ " in field "+entry.getKey());
				break;
			case "LASTNAME":
				lastNamFieldElement.sendKeys(entry.getValue().trim());
				logger.info("Entered value"+entry.getValue().trim()+ " in field "+entry.getKey());
				break;

			case "EMAIL": 
				emailFiElement.sendKeys(entry.getValue().trim());
				logger.info("Entered value"+entry.getValue().trim()+ " in field "+entry.getKey());
				break;
				
			case "TELEPHONE" :
				telePhoneFieldElement.sendKeys(entry.getValue().trim());
				logger.info("Entered value"+entry.getValue().trim()+ " in field "+entry.getKey());
				break;
			case "PASSWORD":
				passwordFieldElement.sendKeys(entry.getValue().trim());
				logger.info("Entered value"+entry.getValue().trim()+ " in field "+entry.getKey());
				break;
				
			case "PASSWORDCONFIRM":
				confirmPasswordFieldElement.sendKeys(entry.getValue().trim());
				logger.info("Entered value"+entry.getValue().trim()+ " in field "+entry.getKey());
				break;
			case "SUBSCRIBE":
				DriverUtility.getDriver().
				findElement(By.xpath("//fieldset/descendant::label[@class='radio-inline' and text()='"+entry.getValue().trim()+"']")).click();
				logger.info("Selected "+entry.getValue().trim()+ " for "+entry.getKey());
				break;
				
			default:
				logger.info("Field "+entry.getKey()+ " is not applicable");
				System.out.println("Field "+entry.getKey()+ " is not applicable");
				break;
			}
			
		}catch (Exception e) {
			logger.error("Could not enter into field "+e);
			System.out.println("Could not enter into field "+e);
		}
		}
	}

}
