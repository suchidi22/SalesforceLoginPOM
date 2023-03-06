package com.jan23.utility;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.jan23.base.BaseTest;
import com.jan23.pages.base.BasePage;

public class TestEventListenersUtility  implements ITestListener{
	protected static ExtentReportsUtility extentreport = null;
	protected WebDriver driver;
	
	@Override
	public void onTestStart(ITestResult result) {//before each test
		
		extentreport.startSingleTestReport(result.getMethod().getMethodName());//return the name of the method
	}
	@Override
	public void onTestSuccess(ITestResult result) {//every test method sucessfully execute
		// TODO Auto-generated method stub
		extentreport.logTestpassed(result.getMethod().getMethodName());
	}
	

	@Override
	public void onStart(ITestContext context) {//before all test
		// TODO Auto-generated method stub
		extentreport =new ExtentReportsUtility();
		extentreport.startExtentReport();
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		extentreport.logTestFailed(result.getMethod().getMethodName());
		BasePage ob=new BasePage(driver);
		driver=ob.returnDriverInstance1();
		
			String path = ob.getScreenshotBase64(driver);
	
			// TODO Auto-generated catch block
		
		extentreport.logTestScreenshot(path);
		
		
	}


	@Override
	public void onFinish(ITestContext context) {
		extentreport.endReport();
		
	}


	
	

}
