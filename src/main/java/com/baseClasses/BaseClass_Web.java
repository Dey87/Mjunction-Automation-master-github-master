	package com.baseClasses;
	
	import java.awt.Robot;
	import java.awt.event.KeyEvent;
	import java.io.File;
	import java.io.IOException;
	import java.lang.reflect.Method;
	import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
	import java.util.Arrays;
	import java.util.Calendar;
	import java.util.Date;
	import java.util.HashMap;
	import java.util.Iterator;
	import java.util.List;
	import java.util.Map;
	import java.util.Properties;
	import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
	import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
	import org.openqa.selenium.By;
	import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
	import org.openqa.selenium.interactions.Actions;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.Select;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.ITestContext;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.AfterSuite;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.BeforeSuite;
	import org.testng.annotations.Test;
	
	public class BaseClass_Web {
		
	Sendmail sendmail = new Sendmail();
	static boolean b = true;
	public String TestReportspath;
	public String suiteName;
	public String TestName = null;
	public String TestFullName = null;
	public final SimpleDateFormat DATE_FORMAT_NOW = new SimpleDateFormat("yyyyMMddHHmmss");
	public  String winHandleBefore = null;
	private String errorMsg;
	public List<String> desc = new ArrayList<String>();
	int r;
	String baseURL,nodeURL;
	public Map<String, String> reportDetails = new HashMap();
	public Logger log=Logger.getLogger(BaseClass_Web.class);
	public PDFResultReport pdfResultReport=new PDFResultReport();
	public HtmlReport htmlrep = new HtmlReport();
	
	public void launchapp(String url) throws Exception {
		try {
			ThreadLocalWebdriver.getDriver().get(url);
			log.info("Opened the URL ::"+url);
		} catch (RuntimeException localRuntimeException) {
			log.fatal("Unable to open the url");
			pdfResultReport.addStepDetails("Launch Application", "Application should be launched", "Error in launching the application:" + localRuntimeException.getMessage(), "FAIL","N");
			throw new AutomationException("Error in launching the application:" + localRuntimeException.getMessage());
		}
	
	}
	
	public void click(By locator,String locatordisplayname) throws Exception {
		try{
			ThreadLocalWebdriver.getDriver().findElement(locator).click();
			waitForObj(3000);
			log.info("Clicked on the field :"+locatordisplayname);
		} catch (RuntimeException localRuntimeException) {
			log.fatal("Error in clicking the field:" + localRuntimeException.getMessage());
		System.out.println("Error in clicking the field:" + localRuntimeException.getMessage() + "Fail");
		pdfResultReport.addStepDetails("click Method", "Click on the field :", "Unable to click on the field" + localRuntimeException.getMessage(), "FAIL","N");
		throw new AutomationException("Unable to click on the field : " + localRuntimeException.getMessage());
	}
	}
	
	public void set(By locator, String data,String locatordisplayname) throws Exception {
		try {
			
			ThreadLocalWebdriver.getDriver().findElement(locator).sendKeys(data);
			waitForObj(2000);
			log.info("Entered the value in the text box :"+locatordisplayname);
		} catch (RuntimeException localRuntimeException) {
			log.fatal("Unable to Enter the value in the text box :"+locatordisplayname);
			System.out.println("Unable to Enter the value in the text box :" + localRuntimeException.getMessage() + "Fail");
			pdfResultReport.addStepDetails("set Method", "Enter the value "+data+" in the textbox : "+locatordisplayname, "Unable to enter the value "+data+" in the textbox : "+locatordisplayname +"\n"+ localRuntimeException.getMessage(), "FAIL","N");
			throw new AutomationException("Error in entering the text in element: " + localRuntimeException.getMessage());
		}
	}
	
	public void clear(By locator,String locatordisplayname) throws Exception {
		try {
			ThreadLocalWebdriver.getDriver().findElement(locator).clear();
			
			log.info("cleared the value in the text box :"+locatordisplayname);
		} catch (RuntimeException localRuntimeException) {
			log.fatal("Unable to Enter the value in the text box :"+locatordisplayname);
			System.out.println("Unable to clear the value in the text box :" + localRuntimeException.getMessage() + "Fail");
			pdfResultReport.addStepDetails("clear Method", "cleared value in text box: "+locatordisplayname, "Unable to clear in the textbox : "+locatordisplayname +"\n"+ localRuntimeException.getMessage(), "FAIL","N");
			throw new AutomationException("Error in clearing the text in element: " + localRuntimeException.getMessage());
		}
	}
	public void submit(By locator) throws Exception {
		try {
			ThreadLocalWebdriver.getDriver().findElement(locator).submit();
			log.info("Entered the value in the text box :"+locator);
		} catch (RuntimeException localRuntimeException) {
			log.fatal("Unable to Enter the value in the text box :"+locator);
			System.out.println("Unable to Enter the value in the text box :" + localRuntimeException.getMessage() + "Fail");
			throw new AutomationException("Error in entering the text in element: " + localRuntimeException.getMessage());
		}
	}
	
	public void select(By locator, String data) throws Exception {
		try {
			Select dropdown = new Select(ThreadLocalWebdriver.getDriver().findElement(locator));
			dropdown.selectByVisibleText(data);
			waitForObj(2000);
			log.info("Selected the Value from the dropdown :"+locator);
		} catch (RuntimeException localRuntimeException) {
			log.fatal("Unable to select the value from the dropdown :"+locator);
			System.out.println("Error in selecting value from dropdown:" + localRuntimeException.getMessage() + "Fail");
			pdfResultReport.addStepDetails("Select list item", "List item should be selected", "Error in selecting value from dropdown: " + localRuntimeException.getMessage(), "FAIL","N");
			throw new AutomationException("Error in selecting value from dropdown: " + localRuntimeException.getMessage());
		}
	
	}
	
	public void selectbyvalue(By locator, String data) throws Exception {
		try {
		Select dropdown = new Select(ThreadLocalWebdriver.getDriver().findElement(locator));
		dropdown.selectByValue(data);
		log.info("Selected the value from the dropdown:"+locator);
		} catch (RuntimeException localRuntimeException) {
		log.fatal("Unable to select the value from the dropdown :"+locator);
		System.out.println("Error in selecting value from dropdown:" + localRuntimeException.getMessage() + "Fail");
		pdfResultReport.addStepDetails("Select list item", "List item should be selected", "Error in selecting value from dropdown: " + localRuntimeException.getMessage(), "FAIL","N");
		}
		}
	
	public void selectbyIndex(By locator, int no) throws Exception {
		try {
		Select dropdown = new Select(ThreadLocalWebdriver.getDriver().findElement(locator));
		dropdown.selectByIndex(no);
		log.info("Selected the value from the dropdown by using index:"+locator);
		} catch (RuntimeException localRuntimeException) {
		log.fatal("Unable to select the value from the dropdown by using index:"+locator);
		System.out.println("Error in selecting value from dropdown:" + localRuntimeException.getMessage() + "Fail");
		pdfResultReport.addStepDetails("Select list item", "List item should be selected", "Error in selecting value from dropdown: " + localRuntimeException.getMessage(), "FAIL","N");
		}
		}
	
	public void clickwithLinkText(String str) throws Exception {
		try {
			ThreadLocalWebdriver.getDriver().findElement(By.linkText(str)).click();
			log.info("Clicked on the linked text :"+str);
		} catch (RuntimeException localRuntimeException) {
			log.fatal("Unable to clicked on the linked text :"+str);
			System.out.println("Error in clicking the element:" + localRuntimeException.getMessage() + "Fail");
			pdfResultReport.addStepDetails("Click on Element", "Click operation should be done", "Error in clicking the element: " + localRuntimeException.getMessage(), "FAIL","N");
			throw new AutomationException("Error in clicking the element: " + localRuntimeException.getMessage());
		}
	}
	
	public void switchframe(WebElement elem) throws Exception {
		try {
			ThreadLocalWebdriver.getDriver().switchTo().frame(elem);
			log.info("Successfully navigated to frame :"+elem);
		} catch (RuntimeException localRuntimeException) {
			log.info("Unable to navigate to the frame :"+elem);
			System.out.println("Error in Switching the Frame:" + localRuntimeException.getMessage() + "Fail");
			pdfResultReport.addStepDetails("Switch to Frame", "Frame should be available", "Error in Switching to the Frame: " + localRuntimeException.getMessage(), "FAIL","N");
			throw new AutomationException("Error in Switching to the Frame: " + localRuntimeException.getMessage());
		}
	
	}
	public void closeBrowser() throws Exception {
		try {
			ThreadLocalWebdriver.getDriver().close();
			log.info("Successfully closed browser ");
		} catch (RuntimeException localRuntimeException) {
			log.info("Unable close browser");
				}
	
	}
	
	public void switchToDefaultFrame() throws Exception {
		try {
			ThreadLocalWebdriver.getDriver().switchTo().defaultContent();
		} catch (RuntimeException localRuntimeException) {
			System.out.println("Error in Switching the Frame:" + localRuntimeException.getMessage() + "Fail");
			pdfResultReport.addStepDetails("Switch to Default Frame", "Default Frame should be available", "Error in Switching to the Default Frame: " + localRuntimeException.getMessage(), "FAIL","N");
			throw new AutomationException("Error in Switching to the Default Frame: " + localRuntimeException.getMessage());
		}
	
	}

	public  boolean js_type(By by,String Text, String LocatorName) throws Throwable{
		boolean flag=true;
		try {
			WebElement location=ThreadLocalWebdriver.getDriver().findElement(by);
			((JavascriptExecutor)ThreadLocalWebdriver.getDriver()).executeScript("arguments[0].value='"+Text+"'", location);
			
			return true;
		} catch (Exception e) {
			flag=false;
			return false;
		}
		finally{
			if (flag) {
				System.out.println("Type Data into "+LocatorName+ " : Able to Type Data into :" + LocatorName);
			}
			else
			{
				System.out.println("Type Data into "+LocatorName+ " : Unable to Type Data into :" + LocatorName);
			}
		}
	}
	
	public  boolean JSClick(By locator, String locatorName)	throws Throwable {
		boolean flag = false;
		try {
			WebElement element = ThreadLocalWebdriver.getDriver().findElement(locator);
			JavascriptExecutor executor = (JavascriptExecutor)ThreadLocalWebdriver.getDriver();
			executor.executeScript("arguments[0].click();", element);
			flag = true;
		}
		catch (RuntimeException localRuntimeException) {
			System.out.println("Error in clicking the element :" + localRuntimeException.getMessage() + "Fail");
			pdfResultReport.addStepDetails("Click on the element", "Element should be clickable ", "Error in clicking the element : " + localRuntimeException.getMessage(), "FAIL","N");
			throw new AutomationException("Error in clicking the element : " + localRuntimeException.getMessage());
		} finally {
			if (!flag) {
				System.out.println("MouseOver "+
						" MouseOver action is not perform on " + locatorName);
				return flag;
			} else if (b && flag) {
				System.out.println("MouseOver "+
						" MouserOver Action is Done on " + locatorName);
				return flag;
			}
	
		}
		return flag;
	}

	public  boolean waitForInVisibilityOfElement(By by, String locator)	throws Throwable {
		boolean flag = false;
		WebDriverWait wait = new WebDriverWait(ThreadLocalWebdriver.getDriver(), 30);
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
			flag = true;
			return true;
		} catch (Exception e) {
			
			return false;
		} finally {
			if (!flag) {
				System.out.println("WaitForInVisibilityOfElement "+
						" Element  " + locator + " is  visible");
	
			} else if (b && flag) {
				System.out.println("WaitForInVisibilityOfElement "+
						" Element  " + locator + " is not visible");
	
			}
		}
	
	}
	
	public  void highlight(By locator) throws Exception 
	{
		try{
	
			WebElement elem = ThreadLocalWebdriver.getDriver().findElement(locator);
			JavascriptExecutor je=(JavascriptExecutor)ThreadLocalWebdriver.getDriver();
			je.executeScript("arguments[0].style.border='3px solid blue'", elem);
	
		} catch (RuntimeException localRuntimeException)
		{
			System.out.println("Error in Highlighting the element :" + localRuntimeException.getMessage() + "Fail");
			pdfResultReport.addStepDetails("highlight the element", "Element should be highlighted ", "Error in Highlighting the element : " + localRuntimeException.getMessage(), "FAIL","N");
			throw new AutomationException("Error in Highlighting the element : " + localRuntimeException.getMessage());
		}
	}
	
	public  void highlight(WebElement elem) throws Exception 
	{
		try{
	
			JavascriptExecutor je=(JavascriptExecutor)ThreadLocalWebdriver.getDriver();
			je.executeScript("arguments[0].style.border='3px solid blue'", elem);
	
		} catch (RuntimeException localRuntimeException)
		{
			System.out.println("Error in Highlighting the element :" + localRuntimeException.getMessage() + "Fail");
			pdfResultReport.addStepDetails("highlight the element", "Element should be highlighted ", "Error in Highlighting the element : " + localRuntimeException.getMessage(), "FAIL","N");
			throw new AutomationException("Error in Highlighting the element : " + localRuntimeException.getMessage());
		}
	}
	
	public  void refresh() throws Exception 
	{
		try{
			Actions a = new Actions(ThreadLocalWebdriver.getDriver());
			a.sendKeys(Keys.F5).build().perform();
			Thread.sleep(3000);
		} catch (RuntimeException localRuntimeException)
		{
			System.out.println("Unable to regresh the page..");
		}
	}
	public  void pagedown() throws Exception 
	{
		try{
			Robot r=new Robot();
			r.keyPress(KeyEvent.VK_PAGE_DOWN);
			r.keyRelease(KeyEvent.VK_PAGE_DOWN);
			Thread.sleep(1000);
		} catch (RuntimeException localRuntimeException)
		{
			System.out.println("Unable to regresh the page..");
		}
	}
	public void switchwindow(int index) throws Exception {
		try {
			Set<String> childHandl = ThreadLocalWebdriver.getDriver().getWindowHandles();
			System.out.println(childHandl);
			System.out.println(childHandl.size());
			Object[] windows=childHandl.toArray();
			/*for (int i = 0; i < windows.length; i++) {
				System.out.println("Window name::::::"+windows[index].toString());
				ThreadLocalWebdriver.getDriver().switchTo().window(windows[index].toString());
			}*/
			System.out.println("Window name::::::"+windows[index].toString());
			ThreadLocalWebdriver.getDriver().switchTo().window(windows[index].toString());
		//	ThreadLocalWebdriver.getDriver().manage().window().maximize();
		} catch (RuntimeException localRuntimeException) {
			System.out.println("Error in Switching the window:" + index + "Fail");
			pdfResultReport.addStepDetails("Switch to Window", "Switch to window should be done", "Error in Switching to the Window: " + localRuntimeException.getMessage(), "FAIL","N");
			throw new AutomationException("Error in Switching to the Window: " + localRuntimeException.getMessage());
		}
	
	}
	
	public void waitForElement(By locator, int timer) throws Exception{
		try{
			for (int i = 0; i < timer; i++) {
				try{
					ThreadLocalWebdriver.getDriver().findElement(locator).isDisplayed();
					System.out.println("Element is available :"+locator);
					break;
				}catch (RuntimeException localRuntimeException) { 
					Thread.sleep(1000);
					System.out.println("Waiting for........"+locator);
				} 
			}
		}catch (RuntimeException localRuntimeException) {
			System.out.println("Error in performing Wait:" + localRuntimeException.getMessage() + "Fail");
			pdfResultReport.addStepDetails("Error in performing Wait:", "It should wait for the element", "Error in performing Wait:: " + localRuntimeException.getMessage(), "FAIL","N");
			throw new AutomationException("Time Out Exception: " + localRuntimeException.getMessage());
		}
	}
	
	public void IsElementPresent(By locator) throws Exception {
		try {
			ThreadLocalWebdriver.getDriver().findElement(locator).isDisplayed();
			log.info("Element is available : "+locator);
		} catch (RuntimeException localRuntimeException) {
			log.error("Element is not available : "+locator);
			System.out.println("Error in verification of presense of element: " + localRuntimeException.getMessage() + "Fail");
			pdfResultReport.addStepDetails("Error during verifying the presence of Element :", "Error in element presence verification::", "Error during the verification of element in DOM: " + localRuntimeException.getMessage(), "FAIL","N");
			throw new AutomationException("Error during verifying the presence of Element: " + localRuntimeException.getMessage());
		}
	}
	
	public int totalitemsdropdownlist(WebElement elem) throws AutomationException {
		List<WebElement> dropdown_values = null;
		try {
			Select dropdownfield = new Select(elem);
			dropdown_values = dropdownfield.getOptions();
		} catch (RuntimeException localRuntimeException) {
			System.out.println("Error in finding total no. of elements in dropdown: " + localRuntimeException.getMessage() + "Fail");
			// PDFResultReport.addStepDetails("List box size", "Get the no of items available in the dropdown", "Error in finding total no. of elements in dropdown: " + localRuntimeException.getMessage(), "FAIL","N");
			throw new AutomationException("Error in finding total no. of elements in dropdown: " + localRuntimeException.getMessage());
		}
		return dropdown_values.size();
	}
	
	public static boolean ischeckboxcheckedbbydefault(WebElement elem) {
		if (elem.getAttribute("checked") != null) {
			return true;
		} else {
			return false;
		}
	}
	
	
	
	public static void verifyElementIsEnabled(WebElement elem, boolean paramBoolean) throws AutomationException
	{
		try
		{
			boolean bool = elem.isEnabled();
			if (bool == paramBoolean)
				System.out.println("Element is present in expected state"+elem+"Pass");
			else
				System.out.println("Element is not present in expected state"+elem+"Fail");
		}
		catch (RuntimeException localRuntimeException)
		{
			System.out.println("Element not found:"+elem+"Fail");
			//    PDFResultReport.addStepDetails("Verify Element", "Element should be present", "Element not found: " + localRuntimeException.getMessage(), "FAIL","N");
			throw new AutomationException("Element not found: " + localRuntimeException.getMessage());
	
		}
	}
	//created on 03/02/2021
	public boolean IsEnabled(By templateGroupdropdown) throws Exception
	{
		try
		{
			boolean bool = ThreadLocalWebdriver.getDriver().findElement(templateGroupdropdown).isEnabled();
			if(bool==true)
			{
				return true;
			}else
			{
				return false;
			}
		}
		catch (RuntimeException localRuntimeException)
		{
			System.out.println("Element not found:"+templateGroupdropdown+"Fail");
			pdfResultReport.addStepDetails("Verify element enablement", "Element should be enabled","Element not found", "FAIL","N");
			throw new AutomationException("Error in entering the text in element: " + localRuntimeException.getMessage());
		}
	}
	public static boolean isAlertPresent() throws AutomationException{
		boolean foundAlert = false;
		try{
			WebDriverWait wait = new WebDriverWait(ThreadLocalWebdriver.getDriver(), 60L);
			wait.until(ExpectedConditions.alertIsPresent());
			foundAlert = true;
		}
		catch(RuntimeException localRuntimeException)
		{
			System.out.println("Error in finding Alert Is Present:Fail");
			// PDFResultReport.addStepDetails("Verify Alert", "Alert should be present", "Alert not found: " + localRuntimeException.getMessage(), "FAIL","N");
			throw new AutomationException("Alert not found: " + localRuntimeException.getMessage());
		} 
		return foundAlert;
	}
	public static void handleConfirmation(String paramString) throws AutomationException
	{
		Alert localAlert = ThreadLocalWebdriver.getDriver().switchTo().alert();
		if (localAlert != null)
		{
			if (paramString.trim().equalsIgnoreCase("Y"))
			{
				System.out.println("Alert accepted!!!");
				localAlert.accept();
			}
			else if (paramString.trim().equalsIgnoreCase("N"))
			{
				System.out.println("Alert Rejected!!!");
				localAlert.dismiss();
			}
		}
		else
		{
			System.out.println("Error in finding Alert:Fail");
			//   PDFResultReport.addStepDetails("Verify Alert", "Alert should be present", "Error in finding Alert: ", "FAIL","N");
			throw new AutomationException("Error in finding Alert: ");
		}
	}
	public static boolean verifyPopupMessage(String paramString) throws AutomationException
	{
		try{
			Alert localAlert = ThreadLocalWebdriver.getDriver().switchTo().alert();
			String str1 = localAlert.getText();
			System.out.println(str1);
			String str2 = paramString;
			System.out.println(str2);
			if (str1.startsWith(str2)){
				//PDFResultReport.addStepDetails("Verify Popup message", "Popup message should be available", "Expected : "+paramString+" and Actual : "+str1+" both are matching", "PASS","N");
			}else {
				//     PDFResultReport.addStepDetails("Verify Popup message", "Popup message should be available", "Expected : "+paramString+" and Actual : "+str1+" both are not matching" , "FAIL","N");
			}
		}
		catch(RuntimeException localRuntimeException){
			System.out.println("Error in Verifying pop up message: "+paramString+"Fail");
			// PDFResultReport.addStepDetails("Verify Popup Message", "Popup message should be available", "Error in Verifying pop up message: "+localRuntimeException.getMessage(), "FAIL","N");
			throw new AutomationException("Error in Verifying pop up message: "+localRuntimeException.getMessage());
		}
		return true;
	}
	public static String getPopupMessage()
	{
		String str1 = null;
		try{
			Alert localAlert = ThreadLocalWebdriver.getDriver().switchTo().alert();
			str1 = localAlert.getText();
	
			return str1;
		}catch(Exception e){
			// PDFResultReport.addStepDetails("Verify Popup message", "Popup message should be available", "Alert is not present", "FAIL","Y");
		}
		return str1;
	}
	public void switchBackToOriginalBrowser() throws AutomationException
	{
		try{
			ThreadLocalWebdriver.getDriver().switchTo().window(winHandleBefore);
		}
		catch(RuntimeException localRuntimeException)
		{
			System.out.println("Error in switching to original Browser");
			// PDFResultReport.addStepDetails("Switch back to Original Browser", "Default Browser should be present", "Error in switching to Original browser: "+localRuntimeException.getMessage(), "FAIL","N");
			throw new AutomationException("Error in switching to Original browser: "+localRuntimeException.getMessage());
		}
	
	}
	public static void sleep(float paramFloat)
	{
		try{
			long l1 = (long)(paramFloat * 1000.0F);
			long l2 = System.currentTimeMillis();
			while (l2 + l1 >= System.currentTimeMillis());
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	
	}

	public static int getTableRowCount(String tableid) throws AutomationException
	{ 
		//By locator = By.xpath(String.format(new CreatePartPages(driver).webTableLocator, tableid);
		//driver.findElement(locator).
		int iRowCount=0;
		try
		{
			List<WebElement> iRows = ThreadLocalWebdriver.getDriver().findElements(By.xpath("//table[@id='"+tableid+"']/tbody/tr"));
			iRowCount = iRows.size();
		}catch(RuntimeException localRuntimeException){
			System.out.println("Error in fetching no. of rows:"+tableid+"Fail");
			// PDFResultReport.addStepDetails("Verify table row count", "Table should be present", "Error in fetching no of rows in a table: "+localRuntimeException.getMessage(), "FAIL","N");
			throw new AutomationException("Error in fetching no of rows in a table: "+localRuntimeException.getMessage());
		}
		return iRowCount;
	}
	
	public static int getColHeaderNumber(String tableid, String colExpct) throws AutomationException
	{
		boolean blnStatus = false;
		int getColNum = 0;
		try
		{
			List<WebElement> iColumns = ThreadLocalWebdriver.getDriver().findElements(By.xpath("//table[@id='"+tableid+"']/tbody/tr[1]/th"));
			for (int j=1;j<=iColumns.size();j++){
				getColNum++;
				WebElement getdata = ThreadLocalWebdriver.getDriver().findElement(By.xpath("//table[@id='"+tableid+"']/tbody/tr[1]/th["+j+"]"));
				if (getdata.getText().trim().toString().contains(colExpct)){
					blnStatus = true;
					break;
				}
			}
			if (blnStatus)
				return getColNum;
			else
				return 0;
		}catch (RuntimeException localRuntimeException){
			System.out.println("Error in fatching the column header:"+colExpct+"in table:"+tableid+"Fail");
			//  PDFResultReport.addStepDetails("Verify table column header", "Table should be present", "Error in fetching the column header: "+localRuntimeException.getMessage(), "FAIL","N");
			throw new AutomationException("Error in fetching the column header: "+localRuntimeException.getMessage());
		}
	}
	
	public static int getRowNumber(String tableid, String colExpct,String rowExpct) throws AutomationException
	{ 
		boolean blnStatus = false;
		int intRowNumber = 0;
		try
		{ 
			int iRowCount = getTableRowCount(tableid);
			int getColNumber = getColHeaderNumber(tableid, colExpct);
			for (int i=1;i<=iRowCount;i++){
				intRowNumber++;
				WebElement getdata = ThreadLocalWebdriver.getDriver().findElement(By.xpath("//table[@id='"+tableid+"']/tbody/tr["+i+"]/td["+getColNumber+"]"));
				if (getdata.getText().trim().toString().contains(rowExpct.trim().toString())){
					System.out.println("Expected data:"+rowExpct+" is present in row no."+intRowNumber+"in column no."+colExpct+"inside table:"+tableid+"Pass");
					blnStatus = true;
					break;
				}
			}
		}catch (RuntimeException localRuntimeException){
			System.out.println("Unable to find row"+rowExpct+"in webtable:"+tableid+"Fail");
		}
		if (blnStatus)
			return intRowNumber;
		else
			return 0;
	}
	public  static void pressEnterKey()
	{
		try
		{
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public  static void tab()
	{
		try
		{
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyRelease(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyRelease(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyRelease(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public void swtichToChildTab() {
		try {
			winHandleBefore = ThreadLocalWebdriver.getDriver().getWindowHandle();
			System.out.println(winHandleBefore);
			String childHandl = (String) ThreadLocalWebdriver.getDriver().getWindowHandles().toArray()[1];
			ThreadLocalWebdriver.getDriver().switchTo().window(childHandl);
			ThreadLocalWebdriver.getDriver().manage().window().maximize();
			System.out.println("Switched backed to child tab"+"Pass");
		} catch (Exception e) {
			System.out.println("Error in Switching back to child tab"+"fail");
		}
	
	}
	public void VerifyText(WebElement elem, String paramString2)
	{
		try
		{
			String selectedOption = new Select(elem).getFirstSelectedOption().getText();
			if (selectedOption.trim().equalsIgnoreCase(paramString2))
			{
				System.out.println("Text was found :"+paramString2+"Pass");
				//  PDFResultReport.addStepDetails("Verify Element", "Element "+paramString2+" should be available", "Element "+paramString2+" was found in DOM", "PASS","N");
			}
			else
			{
				System.out.println("Text was not found :"+paramString2+"Fail");
				//  PDFResultReport.addStepDetails("Verify Element", "Element "+paramString2+" should be available", "Element "+paramString2+" is not found", "FAIL","N");
			}
		}
		catch (RuntimeException localRuntimeException)
		{
			System.out.println("Element was not found :"+elem+"Fail");
			//   PDFResultReport.addStepDetails("Verify Element", "Element "+paramString2+" should be available", "Error in finding the element", "FAIL","N");
			throw new AutomationException("Error in finding the Element: " + localRuntimeException.getMessage());
		}
	}
	
	public String getToolTipText(WebElement elem,String paramString1) throws AutomationException{
		String tooltip = null;
		try{
			if (elem != null)
			{
				tooltip = elem.getAttribute(paramString1);  
			}
		}
		catch (RuntimeException localRuntimeException) {
			System.out.println("Error in Getting tool tip text:"+localRuntimeException.getMessage()+"Fail");
			throw new AutomationException("Error in Getting tool tip text:"+localRuntimeException.getMessage()+"Fail");
		}
		return tooltip;
	}
	public static void verifyListItems(WebElement elem){
		try{
			Select listBox = new Select(elem);
			List<WebElement> allItems = listBox.getOptions();
			for (WebElement item:allItems){
				System.out.println("Item is available in list:"+item);
			}
		}catch (Exception e){
			System.out.println("Issue While Selecting Value in Drop Down Object :"+elem);
		}
	}
	public static void verifyValuesInDropdown(WebElement elem, String[] value){
		try
		{
			int n=0;
			String optionValue="";
			List<String> optionList=new ArrayList<String>();
			Select sel=new Select(elem);
			List<WebElement> localWebElement = sel.getOptions();
			for (WebElement option : localWebElement) {
				optionValue = option.getText();
				optionList.add(optionValue);
			}
			List<String> items = Arrays.asList(value); 
			for(int i=0;i<items.size();i++)
			{
				if(items.get(i).trim().equals(optionList.get(i).trim())){
				}else{
					n++;
				}
			} 
			if (n == 0){
				System.out.println("The values are present in element"+elem+"Pass" );
				//PDFResultReport.addStepDetails("Verify if the vlaues are present in Elemment", "All expected values should be present in : "+elem, "All values are present in element : "+elem, "PASS","N");
			}else
				System.out.println("The values are not present in element"+elem+"Fail" );
			//PDFResultReport.addStepDetails("Verify if the vlaues are present in Elemment", "All expected values should be present in : "+elem, "Error in finding the values in "+elem, "FAIL","N");
		}
		catch (RuntimeException localRuntimeException)
		{
			System.out.println("Element not found"+elem+"Fail");
			// PDFResultReport.addStepDetails("Verify the Element", "Element should be present : "+elem, "Error in finding the Element : "+elem, "FAIL","N");
			throw new AutomationException("Element not found : " + localRuntimeException.getMessage());
		}
	}
	public static By getLocators(String paramString1, String paramString2)
	{
		if (paramString1.trim().equalsIgnoreCase("xpath"))
			return By.xpath(paramString2);
		if (paramString1.trim().equalsIgnoreCase("cssselector"))
			return By.cssSelector(paramString2);
		if (paramString1.trim().equalsIgnoreCase("tagname"))
			return By.tagName(paramString2);
		if (paramString1.trim().equalsIgnoreCase("id"))
			return By.id(paramString2);
		if (paramString1.trim().equalsIgnoreCase("name"))
			return By.name(paramString2);
		if (paramString1.trim().equalsIgnoreCase("linktext"))
			return By.linkText(paramString2);
		return null;
	}
	public static String defaultdropdownselecteditem(WebElement elem) {
	
		Select dropdownfield = new Select(elem);
		String text = dropdownfield.getFirstSelectedOption().getText();
		System.out.println(text.trim());
		return dropdownfield.getFirstSelectedOption().getText().trim();
	}
	
	public String alldropdownlistvalues(WebElement elem) {
		Select dropdownfield = new Select(elem);
		List<WebElement> dropdownfield_values = dropdownfield.getOptions();
	
		String allvalues = "";
		for (int i = 0; i < dropdownfield_values.size(); i++) {
			String currentvalue = dropdownfield_values.get(i).getText();
			String concatvalue = allvalues + currentvalue + ",";
			allvalues = concatvalue;
	
		}
	
		return allvalues.substring(0, allvalues.length() - 1);
	}
	
	public String getdate(int period, String format) {
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		currentDate.add(Calendar.DAY_OF_MONTH, period);
		String date = formatter.format(currentDate.getTime());
		return date;
	}
	
	public static String getattributevalue(WebElement elem, String requiredattribute) throws Exception {
		String attribute = null;
		try{
			attribute = elem.getAttribute(requiredattribute);
		}catch(RuntimeException localRuntimeException){
			// PDFResultReport.addStepDetails("Get Element Attribute", "Element attribute should able to get", "Error in getting the Element attribute" + elem, "FAIL","N");
		}
		return attribute;
	}
	
	public static void alertaction(String action) {
	
		try {
			if (action.equals("accept")) {
				ThreadLocalWebdriver.getDriver().switchTo().alert().accept();
			} else if (action.equals("dismiss")) {
				ThreadLocalWebdriver.getDriver().switchTo().alert().dismiss();
			}
		} catch (Exception e) {
			System.out.println("Error in performing action on Alert box:" + action + "Fail");
		}
	
	}
	
	public  String text(By locator) {
		String text= ThreadLocalWebdriver.getDriver().findElement(locator).getText();
		System.out.println("The text is :"+text);
		return text;
	}
	
	public int totallinnks(WebElement elem) {
		return elem.findElements(By.tagName("a")).size();
	}
	
	public static void capturesnapshot(String imagename) throws Exception {
		try {
			File srcFile = ((TakesScreenshot) ThreadLocalWebdriver.getDriver()).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, new File(getabsoluteimagepath(imagename)));
		}
	
		catch (Exception e) {
			System.out.println("Error in Capturing Screenshot:Fail");
		}
	
	}
	
	public void dragAndDrop(By question, By target){
		WebElement e1=ThreadLocalWebdriver.getDriver().findElement(question);
		WebElement e2=ThreadLocalWebdriver.getDriver().findElement(target);
		Actions a=new Actions(ThreadLocalWebdriver.getDriver());
		a.dragAndDrop(e1, e2).build().perform();
	}
	
	public static String getabsoluteimagepath(String imagename){
		File classpathRoot = new File(System.getProperty("user.dir"));
		File picturesDir = new File(classpathRoot, "/src/TestResults");
		File picture = new File(picturesDir, imagename);
		return picture.getAbsolutePath(); 
	}
	public static boolean verifyElementExist(WebElement elem)
	{
		boolean blnStatus = false;
		WebDriverWait localWebDriverWait = new WebDriverWait(ThreadLocalWebdriver.getDriver(), 60L);
		try
		{
			localWebDriverWait.until(ExpectedConditions.presenceOfElementLocated((By) elem));
			System.out.println("Element is available:"+elem+"Pass");
			blnStatus = true;
	
		}
		catch (RuntimeException localRuntimeException)
		{
			System.out.println("Error in finding Element:"+localRuntimeException.getMessage() +"Pass");
		}
		return blnStatus;
	}
	
	public static void VerifyTableElement(String[] value1,String[] value2){
		int n=0;
		List<WebElement> iRows = ThreadLocalWebdriver.getDriver().findElements(By.xpath("//form[@name='frmFormView']/table/tbody/tr"));
		int iRowCount = iRows.size();
		for(int i=0;i<=iRowCount;i++){
			WebElement getdata1 = ThreadLocalWebdriver.getDriver().findElement(By.xpath("//form[@name='frmFormView']/table/tbody/tr["+i+"]/td[1]"));
			WebElement getdata2 = ThreadLocalWebdriver.getDriver().findElement(By.xpath("//form[@name='frmFormView']/table/tbody/tr["+i+"]/td[2]"));
			if((getdata1.getText().trim().toString().equalsIgnoreCase(value1[i])) && (getdata2.getText().trim().toString().equalsIgnoreCase(value2[i]))){
	
			}else{
				n++; 
			}
		}
		if(n == 0)
			System.out.println("PASS");
		else
			System.out.println("FAIL");
	}
	public static void mouseOver(By loc)
	{
		try
		{
			Actions action = new Actions(ThreadLocalWebdriver.getDriver());
			action.moveToElement(ThreadLocalWebdriver.getDriver().findElement(loc)).build().perform();
		}
		catch (RuntimeException localRuntimeException)
		{
			System.out.println("Error in Hover on element"+localRuntimeException.getMessage()+"Pass");
	
		}
	}
	
	public static void selectListItem(WebElement elem, String paramString)
	{
		int i = 0;
		try
		{
			List localList = elem.findElements(By.tagName("option"));
			Iterator localIterator = localList.iterator();
			while (localIterator.hasNext())
			{
				WebElement localWebElement2 = (WebElement)localIterator.next();
				if (paramString.trim().equalsIgnoreCase(localWebElement2.getText().trim()))
				{
					i = 1;
					localWebElement2.click();
					break;
				}
			}
			System.out.println("Selected option:"+paramString+"Successfully"+"Pass");
			if (i == 0)
			{
				System.out.println("Selected option:"+paramString+"is not present"+"Fail");
			}
		}
		catch (RuntimeException localRuntimeException)
		{
			System.out.println("Issue while Selected value:"+localRuntimeException.getMessage()+"is not present"+"Fail");
		}
	}
	
	public static void waitForObj(long ms)
	{
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());;
		}
	}
	//Added for mjunction project
		public static String getCurrentDateTime()
		{
	       LocalDateTime localdatetime =LocalDateTime.now();
			
			String currentDateTime = localdatetime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a"));
			return currentDateTime;
		}
		
		
	
		//*******************END********************************Added for mjunction project
				public void switchToBrowser(String paramString)
	{
		try{
			winHandleBefore = ThreadLocalWebdriver.getDriver().getWindowHandle();
			String str1 = paramString;
			int i = 0;
			Iterator localIterator = ThreadLocalWebdriver.getDriver().getWindowHandles().iterator();
			while (localIterator.hasNext())
			{
				String str2 = (String)localIterator.next();
				if (ThreadLocalWebdriver.getDriver().switchTo().window(str2).getTitle().equalsIgnoreCase(str1.trim()))
				{
					i = 1;
					ThreadLocalWebdriver.getDriver().switchTo().window(str2);
				}
				else
				{
					ThreadLocalWebdriver.getDriver().switchTo().window(winHandleBefore);
				}
			}
			if (i == 0)
				System.out.println("The Browser Window with title : " + str1 + " is not found");
		}
		catch(Exception e)
		{
			System.out.println("Error in switching to browser"+e.getMessage());
		}
	}
	
	public static void handleAlert()
	{  
		try{
			WebDriverWait localWebDriverWait = new WebDriverWait(ThreadLocalWebdriver.getDriver(), 60L);
			localWebDriverWait.ignoring(NoAlertPresentException.class).until(ExpectedConditions.alertIsPresent());
			if(localWebDriverWait.until(ExpectedConditions.alertIsPresent()) != null){
				if(isAlertPresent()){
					Alert localAlert = ThreadLocalWebdriver.getDriver().switchTo().alert();
					if (localAlert != null) 
					{
						localAlert.accept();
					}
					else
					{
						System.out.println("Alert Not Present Here."+"Fail");
	
					}
				}
			}
		}
		catch (Exception e) {
			System.out.println("Error in handling alert"+e.getMessage()+"Fail");
		}
	}
	public void AutomationException(String paramString)
	{
		System.out.println("Error Message : " + paramString);
		this.errorMsg = paramString;
		if (ThreadLocalWebdriver.getDriver() != null){
			ThreadLocalWebdriver.getDriver().quit();
			//ThreadLocalWebdriver.getDriver() = null;
		}
	}
	
	public void switchbacktodefaultframe()
	{
		ThreadLocalWebdriver.getDriver().switchTo().defaultContent();
	}
	
	@BeforeSuite
	public void setUpSuiteDetails(ITestContext ctx) throws Exception{
		//pdfResultReport=new PDFResultReport();
		pdfResultReport.suiteName = ctx.getCurrentXmlTest().getSuite().getName();
		System.out.println("pdfResultReport.suiteName "+pdfResultReport.suiteName);
		pdfResultReport.generateSuiteResultFolder();
		TestReportspath = System.getProperty("user.dir") + "/TestReports/" + 
				pdfResultReport.suiteName + "_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		System.out.println("TestReportspath  ::"+TestReportspath);
		pdfResultReport.generateSuiteResultFolder();
		pdfResultReport.DefaultReport("SummaryReport");
	}
	@BeforeMethod
	public void captureDesc(Method method){
		Test test = method.getAnnotation(Test.class);
		if(!test.description().toString().equals("")){
			desc.add(test.description());
		}
	} 
	@AfterSuite
	public void afterSuite() throws Exception{
		try{
			pdfResultReport.createTestRunReport(reportDetails.get("Test Script Name"),reportDetails.get("Test Author Name"),false);
			pdfResultReport.generatePDFSummaryReport2(TestName);	
			File path=pdfResultReport.suiteFolder;
			System.out.println("Result path ::::::::::::::"+path);
			htmlrep.generateHtmlReport(TestName);
		//	Sendmail.ComposeGmail("gvenkataraghavend", "raghavendrasai.goli@cigniti.com", path);
			System.out.println("Mail sent successfully.......");
			System.out.println("Execution completed......................................");	
			} catch (Exception ex){
			System.out.println("Result Suite file is not being generated : "+ex.getMessage());
		}
	}

	@BeforeClass
	public void BeforeClass() throws Exception
	{ 
		Thread.sleep(1000);
		TestName = this.getClass().getSimpleName();
		TestFullName = this.getClass().getName();
		System.out.println("TestName ::::::::::::::::"+TestName);
		pdfResultReport.captureTestCaseStartTime();
		Thread.sleep(2000);
		pdfResultReport.createPDFfile(TestName);
	}

	@AfterClass 
	public void afterClass() throws Exception
	{
		pdfResultReport.createTestRunReport(reportDetails.get("Test Script Name"),reportDetails.get("Test Author Name"),true);
		pdfResultReport.addSummaryReport(TestName,desc.get(r), TestFullName);
		r++;
		pdfResultReport.captureTestCaseEndTime();
		pdfResultReport.generatePDFSummaryReport(TestName);
		pdfResultReport.writeChartToPDF(pdfResultReport.docWriter);
		pdfResultReport.generatePDFStepdetails(TestName);
		//flush();
	} 
	public void scrollToElement(By locator) {

		try {
		WebDriver driver = ThreadLocalWebdriver.getDriver();

		JavascriptExecutor je = (JavascriptExecutor)driver;

		WebElement element = driver.findElement(locator);

		je.executeScript("arguments[0].scrollIntoView(true);",element);
		} catch (Exception e) {

		e.printStackTrace();
		}
		}
	public void waitForElementToBeVisible(By locator)
	{
		WebDriverWait wait= new WebDriverWait(ThreadLocalWebdriver.getDriver(), 100);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	
	public void waitForElementToBeInvisible(By locator)
	{
		WebDriverWait wait= (WebDriverWait) new WebDriverWait(ThreadLocalWebdriver.getDriver(),100).pollingEvery(5, TimeUnit.SECONDS).ignoring(Exception.class);
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	
	
	public void waitForElementToBeClickable(By locator)
	{
		WebDriverWait wait= new WebDriverWait(ThreadLocalWebdriver.getDriver(), 100);
		
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	
	public void waitForElementTextToBeTillInVisible(By locator,String text)
	{
		WebDriverWait wait= new WebDriverWait(ThreadLocalWebdriver.getDriver(), 100);
		
		wait.until(ExpectedConditions.invisibilityOfElementWithText(locator, text));
	}
	
	public void textToBePresentInElement(By locator,String value)
	{
		WebDriverWait wait= new WebDriverWait(ThreadLocalWebdriver.getDriver(), 100);
		
		wait.until(ExpectedConditions.textToBe(locator, value));
	}
	public String checkPageIsReady() {

		JavascriptExecutor js = (JavascriptExecutor) ThreadLocalWebdriver.getDriver();

		while(true)
		{
			if (js.executeScript("return document.readyState").toString().equals("complete")) {
				System.out.println("Page Is loaded.");
				return "page loaded Completely";
			}
	  }
	}
	public String bidStartDate_24Hrs="";
	public String getBidStartDate(int minutes)
	{
		  LocalDateTime localdatetime =LocalDateTime.now().plusMinutes(minutes);
		  
		  bidStartDate_24Hrs = localdatetime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
		  
		  String bidStartDate_12Hrs = localdatetime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a")).toLowerCase();
		
		  return bidStartDate_12Hrs;	
	}
	
	public String bidDueDate_24Hrs="";
	public String getBidDueDate(int minutes)
	{
		 LocalDateTime localdatetime =LocalDateTime.now().plusMinutes(minutes);
		 
		 bidDueDate_24Hrs= localdatetime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
		 
		  String bidDueDateTime = localdatetime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a")).toLowerCase();
		
		  return bidDueDateTime;	
	}
	
	public String bidOpenDate_24Hrs="";
	public String getBidOpenDate(int minutes)
	{
		 LocalDateTime localdatetime =LocalDateTime.now().plusMinutes(minutes);
		 
		 bidOpenDate_24Hrs = localdatetime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
			
		 String bidOpenDateTime_12Hrs = localdatetime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a")).toLowerCase();
		
		 return bidOpenDateTime_12Hrs;	
		
	}
	
	public void deleteBrowserCookies() {

		try {
			WebDriver driver = ThreadLocalWebdriver.getDriver();
			driver.manage().deleteAllCookies();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void scrollToTopOfThePage() {
		try {
			WebDriver driver = ThreadLocalWebdriver.getDriver();
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -document.body.scrollHeight)");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	}
