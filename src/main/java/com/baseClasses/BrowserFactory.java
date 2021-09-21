package com.baseClasses;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserFactory {
	public static String browser;
	
/*	BrowserFactory(String name){
		browser=name;
	}*/
	
	 @SuppressWarnings("deprecation")
	static WebDriver openBrowser(String browserName) {
	        WebDriver driver = null;
	        if (browserName.toLowerCase().contains("firefox")) {
	            driver = new FirefoxDriver();
	            browser="Firefox";
	            return driver;
	        }
	        else if (browserName.toLowerCase().contains("internet")) {
	            driver = new InternetExplorerDriver();
	            browser="IE";
	            return driver;
	        }
	        /*else if (browserName.toLowerCase().contains("windowschrome")) {
	        
	        	System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir").replace("\\", "/")	+"/drivers/chromedriver.exe");
	            driver = new ChromeDriver();	
	            browser="Google Chrome";
	            System.out.println("browser   :::::::"+browser);
		        driver.manage().window().maximize();
		        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	            return driver;
	        }*/
	        
	        else if (browserName.toLowerCase().contains("windowschrome")) {
	        	String currentUsersHomeDir = System.getProperty("user.dir");
	            String otherFolder = currentUsersHomeDir + File.separator + "Resources" + File.separator + "rfqExcel";
	            HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
	            chromePrefs.put("profile.default_content_settings.popups", 0);
	            chromePrefs.put("download.default_directory", otherFolder);
	           
	            ChromeOptions options = new ChromeOptions();
	            options.setExperimentalOption("prefs", chromePrefs);
	            DesiredCapabilities cap = DesiredCapabilities.chrome();
	            cap.setCapability(ChromeOptions.CAPABILITY, options);
	            //
	            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir").replace("\\", "/") +"/drivers/chromedriver.exe");
	               driver = new ChromeDriver(cap);
	               browser="Google Chrome";
	               System.out.println("browser   :::::::"+browser);
	           driver.manage().window().maximize();
	           driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	               return driver;
	        }
	        else if (browserName.toLowerCase().contains("linuxchrome")) {
		        	System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir").replace("\\", "/")	+"/linux/chromedriver");
		            driver = new ChromeDriver();	
		            browser="Google Chrome";
		            System.out.println("browser   :::::::"+browser);
			        driver.manage().window().maximize();
			        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		            return driver;
		        }
	        else if (browserName.toLowerCase().contains("headless")) {
	        	System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir").replace("\\", "/")	+"/linux/chromedriver");
	        	ChromeOptions options = new ChromeOptions();
	          //  options.setHeadless(true);
	            options.addArguments("--headless");
	        	driver = new ChromeDriver(options);	
	          
	            System.out.println("Headless mode   :::::::"+browserName);
		        driver.manage().window().maximize();
		        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	            return driver;
	        }
	        return driver;
	    }
	}
