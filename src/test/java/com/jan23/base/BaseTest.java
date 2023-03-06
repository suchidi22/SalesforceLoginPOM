package com.jan23.base;



	
	import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;

import com.jan23.pages.base.BasePage;
import com.jan23.utility.ExtentReportsUtility;
import com.jan23.utility.PropertiesUtility;

import io.github.bonigarcia.wdm.WebDriverManager;


		//declare the webdriver
		
		public class BaseTest  {
			
			protected static  WebDriver driver =null ;//declare and initialize the webdriver
			protected static WebDriverWait wait=null;
			protected static  Logger logger =null;
			protected static ExtentReportsUtility extentreport = ExtentReportsUtility.getInstance();
		
			/*public BaseTest(WebDriver driver) {
				super();
				}
				*/
	/*@BeforeTest
		public  void initialize () throws InterruptedException {
		
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			Thread.sleep(4000);
			driver.get(("https://login.salesforce.com"));

			}
	@AfterTest
	public void TeardownTest() {
		BasePage.driver.quit();
		
	}*/
	public WebDriver returnDriverInstance() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	@BeforeTest
	public void setUpBeforeTest() {
		//we have to created a logger
		System.out.println("inside @BeforeTest method" );
		logger =LogManager.getLogger(BaseTest.class.getName());
		
		//extentreport =new ExtentReportsUtility();
		//extentreport.startExtentReport();
		//all comaand beacuse all handeling by listners
	}
	//@AfterTest
	//public void teardownAfterTest() {
		//extentreport.endReport();
	//}
	
	@BeforeMethod
	//@Parameters("browsername")
	public void setUpBeforeTestMethod(@Optional("chrome") String browsername,Method method) {
		//extentreport.startSingleTestReport("testcase");
		
		logger.info("started testscript name "+method.getName()); // writing to log4j file
		//System.out.println("started testscript name="+method.getName());
		//extentreport.logTestInfo("started testscript name "+method.getName());// writing to extent report
		
		PropertiesUtility propertiesUtility =new PropertiesUtility();
		propertiesUtility.loadFile("applicationDataProperties");
		String url = propertiesUtility.getPropertyValue("url");
		GetDriverInstance(browsername);
		goToUrl(url);
	}
	
	@AfterMethod
	public void tearDownAfterTestMethod() {
		driver.close();
	}
	
	public static void enterText(WebElement element, String text, String name) {
		if (element.isDisplayed()) {
			clearElement(element, name);
			element.sendKeys(text);
			logger.info("text entered in " + name + " field");
			//extentreport.logTestInfo("text entered in " + name + " field");
		} else {
			logger.info("fail: " + name + " element not displayed");
		}
		driver.getTitle();
	}
	
	public static void clearElement(WebElement element, String objName) {
		if (element.isDisplayed()) {
			element.clear();
			logger.info("pass:" + objName + "  element cleared");

		} else {
			logger.info("fail:" + objName + " element not displayed");
		}
	}
@AfterTest
public void tearDownAfterTest() {
	//extentreport.endReport();
}
	public static void GetDriverInstance(String browserName) {

		switch (browserName) {
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			break;

		case "chrome":
			WebDriverManager.chromedriver().setup();
			//ChromeOptions option=new ChromeOptions();
			//option.addArguments("--headless");
			//option.addArguments("--incognito");
			//option.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			break;

		default:
			System.out.println("not entered proper browsername");
		}
	}
	
	public static void goToUrl(String url) {
		logger.info("going to the url=" +  url);
		driver.get(url);
	}
	public String getScreenshotOfThePage()  {
		//random value +date()+testscreen name-->filename
		String date = new SimpleDateFormat("yyyy__MM__dd__hh_mm_ss").format(new Date(0));
		String curDir = System.getProperty("user.dir");
		TakesScreenshot screenShot=(TakesScreenshot)driver;
		File imgFile =screenShot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(curDir+"/screenshots/" +date+".png");
		try {
		FileHandler.copy(imgFile, destFile);
		}catch (IOException e) {
			e.printStackTrace();
		}
		return curDir;
	}
	}
