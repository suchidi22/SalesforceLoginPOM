package com.jan23.pages.Test;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.jan23.base.BaseTest;
import com.jan23.pages.Home.HomePage;
import com.jan23.pages.Login.LoginPage;
import com.jan23.pages.base.BasePage;
import com.jan23.utility.*;


@Listeners(com.jan23.utility.TestEventListenersUtility.class)
public class TestPage extends BaseTest
{

	//public TestPage(WebDriver driver) {
		//super(driver);
		
	//}

	@Test

	public void Login_Script() throws InterruptedException  {
		logger.info("inside loginToSalesForce method");
		extentreport.logTestInfo("inside loginToSalesforse method");
		PropertiesUtility propertiesUtility =new PropertiesUtility();
		//Properties propertyFile=  propertiesUtility.loadFile("testDataProperties");
		Properties propertyFile=  propertiesUtility.loadFile("applicationDataProperties");
		String url=propertiesUtility.getPropertyValue("url");
		String username=propertiesUtility.getPropertyValue("login.valid.userid");
		String password=propertiesUtility.getPropertyValue("login.valid.password");
		
		
		String expected ="Salesforce Home Page";
		GetDriverInstance("chrome");
		goToUrl(url);
		//String expected ="suchi23 sen";
		LoginPage loginPage =PageFactory.initElements(driver,LoginPage.class);
		//LoginPage loginPage = new LoginPage(driver);
		loginPage.enterUserName("suchi@tekarch.com");
		loginPage.enterPassword ("allow@123");
		loginPage.ClickOnRememberMe();
		driver =loginPage.clickLogin();
		Thread.sleep(4000);
		loginPage.clickonusermenue();
Thread.sleep(4000);
		HomePage homepage = new HomePage(driver);
		String actual = homepage.getTextFromHomePage();
		Assert.assertEquals(actual,expected);
		extentreport.logTestInfo("method ended");
		driver.close();
}
	
	@Test 
	public void Logout() throws InterruptedException {
	
		
		LoginPage loginPage =PageFactory.initElements(driver,LoginPage.class);
		
		loginPage.enterUserName("suchi@tekarch.com");
		loginPage.enterPassword ("allow@123");
		
		driver =loginPage.clickLogin();
		loginPage.clickonusermenue();
		Thread.sleep(5000);
		loginPage.clickonLogout();
		
		
		Thread.sleep(4000);
		driver.close();
	
}
	@Test 
	public void Forgotpassword() throws InterruptedException {
	LoginPage loginPage =PageFactory.initElements(driver,LoginPage.class);
	
	loginPage.enterUserName("suchi@tekarch.com");
	loginPage.enterPassword ("");
	driver =loginPage.clickLogin();
	loginPage.clickonForgotpassword();
	Thread.sleep(4000);
	driver.close();
	

}
}