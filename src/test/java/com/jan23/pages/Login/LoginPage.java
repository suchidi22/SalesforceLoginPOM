package com.jan23.pages.Login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.jan23.pages.base.BasePage;

public class LoginPage extends BasePage {
	// WebDriver driver;


	//using FindBy for locating element

	 
	@FindBy(id ="username")WebElement username;
	@FindBy(id ="password") WebElement password;
	@FindBy(name="Login") WebElement Loginbutton;
	@FindBy(xpath ="//input[@id='rememberUn']") WebElement rememberme;
	@FindBy(xpath ="//div[@id='userNav-arrow']")WebElement usermenue;
	@FindBy(xpath = "//a[contains(text(),'Logout')]")WebElement Logout;
	@FindBy(xpath ="//a[@id='forgot_password_link']")WebElement forgotpassword;
	
	public LoginPage(WebDriver driver) {//call the base page  constructor by using super class keyword
	super(driver);
	}
	//create reusable method
	
	public  void enterUserName(String data) {
		WaitUntilElementIsVisible(username,"username element");//just wait before any entering text is good approach
		enterText(username,data,"username element");
		
	}
	public  void enterPassword (String data) {
		
		enterText(password,data,"password element");
	}
	
	public  WebDriver clickLogin() {
		clickElement(Loginbutton,"login button element");
		return driver;
	
	}
	
	public void ClickOnRememberMe() {
		clickElement(rememberme, "remember me element");
		
	}
	public void clickonusermenue() {
		clickElement(usermenue,"usermenue element");
	}
	
	public void clickonLogout() {
		clickElement(Logout,"logout element");
	}
	public void clickonForgotpassword(){
		clickElement(forgotpassword,"forgotpassword element");
	}
	
	
}

