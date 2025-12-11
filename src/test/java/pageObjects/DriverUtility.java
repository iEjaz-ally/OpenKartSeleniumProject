package pageObjects;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ExcelUtilities;

public class DriverUtility {
	
	static ThreadLocal<WebDriver> driver= new ThreadLocal<>();
	
	static ExcelUtilities utilities;
	
	static String browserName;
	
	static String urlString;

	
	public static void getData() throws IOException
	{
utilities = new ExcelUtilities(System.getProperty("user.dir")+ File.separator + "testData" + File.separator + "testData.xlsx");
		
		browserName = utilities.getCellData(utilities.getSheetName(0), 1, 1);
		
		urlString = utilities.getCellData(utilities.getSheetName(0), 0,1);
		utilities.closeWorkBook();
	}
	/*public static void initializeBrowser() throws IOException {
		
		utilities = new ExcelUtilities(System.getProperty("user.dir")+ File.separator + "testData" + File.separator + "testData.xlsx");
		
		String browserName = utilities.getCellData(utilities.getSheetName(0), 1, 1);
		
		String urlString = utilities.getCellData(utilities.getSheetName(0), 0,1);
		System.out.println(browserName+ " this is browser name");
		try{
			switch(browserName.replace(" ", "").toUpperCase()) {
	
			case "CHROME":
			    ChromeOptions options = new ChromeOptions();
			    options.addArguments("--remote-allow-origins=*");
			    options.addArguments("--disable-blink-features=AutomationControlled");
			    options.addArguments("--disable-infobars");
			    options.addArguments("--disable-dev-shm-usage");
			    options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

			    System.setProperty("webdriver.http.factory", "jdk-http-client");
			    System.setProperty("selenium.disable.devtools", "true"); // *** key fix ***

			    WebDriverManager.chromedriver().setup();
			    driver.set(new ChromeDriver(options));

			    getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
			    getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			    getDriver().get(urlString);
			    getDriver().manage().deleteAllCookies();
			    getDriver().manage().window().maximize();
			    break;

		case "EDGE":
			WebDriverManager.edgedriver().setup();
			driver.set(new EdgeDriver());
			
		break;
		default:
			throw new Exception("Browser couldn't be initilized");
			} 

		}catch (Exception e) {
			System.out.println(e.getMessage());
			
		}
	//	getDriver().manage().deleteAllCookies();
		
	}*/
	
public static WebDriver initializeBrowser() throws IOException {
		
	WebDriver driver = null;
		try{
			switch(browserName.replace(" ", "").toUpperCase()) {
	
			case "CHROME":
			    ChromeOptions options = new ChromeOptions();
			    options.addArguments("--remote-allow-origins=*");
			    options.addArguments("--disable-blink-features=AutomationControlled");
			    options.addArguments("--disable-infobars");
			    options.addArguments("--disable-dev-shm-usage");
			    options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

			    System.setProperty("webdriver.http.factory", "jdk-http-client");
			    System.setProperty("selenium.disable.devtools", "true");

			    WebDriverManager.chromedriver().setup();
			   driver = new ChromeDriver(options);
			 /*   driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
			    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			    driver.get(urlString);
			    driver.manage().deleteAllCookies();
			    driver.manage().window().maximize();*/

			    break;

		case "EDGE":
			WebDriverManager.edgedriver().setup();	
		break;
		default:
			throw new Exception("Browser couldn't be initilized");
			} 

		}catch (Exception e) {
			System.out.println(e.getMessage());
			
		}
		return driver;
		
	}

public static void setBrowser() {
		
			try {
				driver.set(initializeBrowser());
			} catch (IOException e) {
				e.printStackTrace();
			}
			  getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
			    getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			    getDriver().get(urlString);
			    getDriver().manage().deleteAllCookies();
			    getDriver().manage().window().maximize();
		}
	
	public static WebDriver getDriver() {
		return driver.get();
	}
	
	public static <T> T getpage(Class<T> class1) throws IOException  {
	
		return PageFactory.initElements(getDriver(), class1);
		
	}
	public static void flunetWaitForPageLoad(WebDriver driver, WebElement ele) {
	Wait<WebDriver> wait = new FluentWait<>(driver)
				.withTimeout(Duration.ofSeconds(300))
				.pollingEvery(Duration.ofSeconds(5))
				.ignoring(Exception.class);
	
	wait.until(ExpectedConditions.visibilityOf(ele));
	}
	public static <T> void fluentWait( WebDriver driver, String xpath){
		FluentWait wait = new FluentWait(driver);
		wait.withTimeout(Duration.ofSeconds(300));
		wait.pollingEvery(Duration.ofSeconds(5));
		wait.ignoring(StaleElementReferenceException.class);
		
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath))));
		
	}
}
