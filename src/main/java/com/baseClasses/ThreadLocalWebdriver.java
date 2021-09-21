package com.baseClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;

public class ThreadLocalWebdriver {
	 private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
	 
	    public static WebDriver getDriver() {
	        return webDriver.get();
	    }
	 
		static void setWebDriver(WebDriver driver) {
	        webDriver.set(driver);
	    }
}
