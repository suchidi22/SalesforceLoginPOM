package com.jan23.pages.Home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.jan23.pages.base.BasePage;

public class HomePage extends BasePage{
	//WebDriver driver;
	
	@FindBy(xpath="//span[@id='userNavLabel']")WebElement clickOnUsermenue;
	
	
	
	//argument constructor
	public HomePage(WebDriver driver) {
		super(driver);
		}
	
public String  ClickOnusermenue() {
	//return clickOnUsermenue.getText();
	//reusable method
	return getTextFromWebElement(clickOnUsermenue,"from test element");
	
}

public String getTextFromHomePage() {
	return clickOnUsermenue.getText();
}

}

