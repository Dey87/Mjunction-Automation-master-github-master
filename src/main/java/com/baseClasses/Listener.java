package com.baseClasses;

import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.internal.IInvoker;

public class Listener implements IInvokedMethodListener{
	
	  public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
	        if (method.isTestMethod()) {
	            String browserName = method.getTestMethod().getXmlTest().getLocalParameters().get("browserName");
	            WebDriver driver = BrowserFactory.openBrowser(browserName);
	            ThreadLocalWebdriver.setWebDriver(driver);
	            
	        }
	    }
	 
	    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
	        if (method.isTestMethod()) {
	            WebDriver driver = ThreadLocalWebdriver.getDriver();
	            if (driver != null) {
	            	try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	              driver.quit();
	            }
	        }
	    }


}
