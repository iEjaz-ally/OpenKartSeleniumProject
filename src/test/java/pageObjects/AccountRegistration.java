package pageObjects;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;


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
	
	@FindAll({@FindBy(xpath="//input[@value='Continue']"),
			@FindBy(xpath = "//div/a[text()='Continue']")})
	@CacheLookup
	private WebElement continueBtnElement;
	
	@FindBy(xpath = "//div/a/following-sibling::input[1]")
	@CacheLookup
	private WebElement privacyPolicyCheckBoxElement;
	
	@FindBy(xpath="//div/h1/following-sibling::p")
	@CacheLookup
	private List<WebElement> messageList;

	public void performAction(Object...args) {
		
		
		String[] fields = args[0].toString().split("=>");
		
		for(String action : fields) {
		try {
		switch (action.replace(" ", "").toUpperCase()) {
		case "CONTINUE":
			try {
				continueBtnElement.click();
			logger.info("Clicked on "+ action);
			}catch (Exception e) {
				// TODO: handle exception
			//	DriverUtility.fluentWait(DriverUtility.getDriver(), "//div/a[text()='Continue']");
				DriverUtility.getDriver().findElement(By.xpath("//div/a[text()='Continue']")).click();
				
			}
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
	public void verifymessages(Object...args) {
		String messageTypeString= args[0].toString().trim().toUpperCase();
		int i=0;
		try {
			switch (messageTypeString.replace(" ", "")) {
			case "SUCCESSFULLREGISTRATION":
				ArrayList<String> messArrayList = new ArrayList<>();
				messArrayList.add("Congratulations! Your new account has been successfully created!");
				messArrayList.add("You can now take advantage of member privileges to enhance your online shopping experience with us.");
				messArrayList.add("If you have ANY questions about the operation of this online shop, please e-mail the store owner.");
				messArrayList.add("A confirmation has been sent to the provided e-mail address. If you have not received it within the hour, please contact us.");
				Assert.assertEquals("Your Account Has Been Created!",
						DriverUtility.getDriver().findElement(By.xpath("//div/h1[contains(text(),'Your Account')]")).getText());
				for(WebElement element : messageList) {
					Assert.assertEquals(messArrayList.get(i++),
							element.getText());
					
				}
				break;
			case "DUPLICATEREGISTRATION":
				Assert.assertEquals("Warning: E-Mail Address is already registered!",
						DriverUtility.getDriver().findElement(By.xpath("//div[contains(text(),'Warning')]")).getText(), " Messages don't match");
				break;

			default:
				logger.info("Message type "+messageTypeString+ " not applicable");
				break;
			}
		}catch (Exception e) {
			logger.error("Assert failed due to following Exception "+e);
		}
	}

}
