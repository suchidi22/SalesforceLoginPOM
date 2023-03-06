package com.jan23.pages.base;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.jan23.utility.ExtentReportsUtility;



public class BasePage  { //created c;lass 

		protected static  WebDriver driver =null ;//declare and initialize the webdriver
		protected static WebDriverWait wait=null;
		protected static  Logger logger =LogManager.getLogger(BasePage.class.getName());
		
		protected static ExtentReportsUtility extentreport = ExtentReportsUtility.getInstance();
		
		public BasePage(WebDriver driver) {
			
			this.driver=driver;
			PageFactory.initElements(driver,this);
			
		}
		public static void enterText(WebElement element, String text, String name) {
			if (element.isDisplayed()) {
				clearElement(element, name);
				element.sendKeys(text);
				logger.info("text entered in " + name + " field");
				extentreport.logTestInfo("text entered in " + name + " field");
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
	
		public static void clickElement(WebElement element, String objName) {
			if (element.isDisplayed()) {
				element.click();
				logger.info("pass:" + objName + " element clicked");
				//extentreport.logTestInfo("pass:"+ objName+"element clicked");
			} else {
				logger.info("fail:" + objName + "  element not displayed");

			}
	}
		public static String getTextFromWebElement(WebElement element, String name) {
			if (element.isDisplayed()) {
				return element.getText();
			} else {
				System.out.println(name + " web element is not displayed");
				return null;
			}

		}
		public static void moveToElementAction(WebElement ele, String objName) {
			Actions action = new Actions(driver);
			action.moveToElement(ele).build().perform();
			System.out.println("right click performed on webElement"+objName);
		}
		public static void ContextClickAction(WebElement ele, String objName) {
			Actions action = new Actions(driver);
			action.moveToElement(ele).build().perform();
			System.out.println("right click performed on webElement"+objName);
		}
		public static void WaitUntilElementIsVisible(WebElement ele, String objName) {
			
			System.out.println("waiting for an web element"+objName+"for its visibility");
			wait =new WebDriverWait(driver,30);
			wait.until(ExpectedConditions.visibilityOf(ele));
		}
		public static void waitUntilAlertIsPresent (){
			System.out.println("waiting for alert to be present");
			wait =new WebDriverWait(driver,30);
			wait.until(ExpectedConditions.alertIsPresent());
		}
		public static void WaitUntilElementToBeClickable(By locator,String objName) {
			System.out.println("waiting for an web element"+objName+"to be clickable");
			wait =new WebDriverWait(driver,30);
			wait.until(ExpectedConditions.elementToBeClickable(locator));
		}
		public static void waitFluentForVisibility(WebElement ele, String objName) {
			Wait<WebDriver> wait =  new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
					.ignoring(NoSuchElementException.class);
	wait.until(ExpectedConditions.visibilityOf(ele));
					
		}
		public static  Alert switchToAlert() {
			waitUntilAlertIsPresent();
			Alert alert =driver.switchTo().alert();
			System.out.println( "switched to alert");
			return alert;
			
		}
		public static void AcceptAlert(Alert alert) {
			System.out.println("alert Accepted");
			alert.accept();
		}
		public static String getAlertText(Alert alert) {
			System.out.println("etracting text in the alert");
			return alert.getText();
		}
		public static void dismisAlert() {
			waitUntilAlertIsPresent();
			Alert alert = switchToAlert();
			alert.dismiss();
			System.out.println("alert dismissed");
		}
		
		public static void selectByTextData(WebElement element, String text, String objName) {
			Select selectCity = new Select(element);
			selectCity.selectByVisibleText(text);
			System.out.println(objName + "  selected  " + text );
		}
		
		public static void selectByIndexData(WebElement element, int index, String objName) {
			Select selectCity = new Select(element);
			selectCity.selectByIndex(index);
			System.out.println(objName + "  selected  ");
		}
		public static void selectByValueData(WebElement element, String text, String objName) {
			Select selectCity = new Select(element);
			selectCity.selectByVisibleText(text);
			System.out.println(objName + "  selected  " );
		}
		public static void SwitchWindowOpened(String mainWindowHandle) {
			Set<String> allWindowHandles = driver.getWindowHandles();
			for (String handle : allWindowHandles) {
				if(!mainWindowHandle.equalsIgnoreCase(handle))
					driver.switchTo().window(handle);
		}
			System.out.println("switched to new window");
		}
			
		public static WebElement selectFromList(List<WebElement> list, String text) {
			WebElement country=null;
			for(WebElement i : list) {
				if (i.getText().equalsIgnoreCase(text)) {
					System.out.println("selected=  "+ i.getText());
					country=i;
					break;
				}
			}
			return country;
		}
		/*public String getScreenshotOfThePage()  {
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
		}*/
		public String getScreenshotBase64(WebDriver driver) {
			String date = new SimpleDateFormat("yyyy__MM__dd__hh_mm_ss").format(new Date(0));
			String curDir = System.getProperty("user.dir");
			TakesScreenshot screenShot=(TakesScreenshot)driver;
			String img=screenShot.getScreenshotAs(OutputType.BASE64);
			return img;
		}
		//return destFile.getAbsolutePath();
		
		public WebDriver returnDriverInstance1() {
			// TODO Auto-generated method stub
			return null;
		}

	}

