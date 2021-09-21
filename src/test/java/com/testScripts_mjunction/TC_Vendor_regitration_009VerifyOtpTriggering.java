package com.testScripts_mjunction;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.baseClasses.ThreadLocalWebdriver;
import com.components.VendorRegistrationComponent;
import com.components.InvoiceComponent;
import com.components.PostTenderComponent;
import com.components.PreConditionPo_SanctionCreation;
import com.components.eTenderComponent;

public class TC_Vendor_regitration_009VerifyOtpTriggering extends BaseClass_Web {

	public VendorRegistrationComponent VendorRegistrationComponent = new VendorRegistrationComponent(pdfResultReport);
	public eTenderComponent etendercomponentobj = new eTenderComponent(pdfResultReport);
	/**
	 * TestScript Environment Details
	 * 
	 * @throws Exception
	 */
	public void initializeRepository() throws Exception {
		reportDetails.put("Test Script Name", this.getClass().getSimpleName());
		reportDetails.put("Test Script MyWorksshop Document ID", "Doc1234567");
		reportDetails.put("Test Script Revision No", "01");
		reportDetails.put("Test Author Name", "jit");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}

	@Parameters("TestcaseNo")
	@Test(description = "TC_Vendor_regitration_009VerifyOtpTriggering")
      public void VerifySaveButton(String no) throws Throwable {
	  System.out.println("Entered in the Test method..................");
	  try {
		pdfResultReport.readTestDataFile(System.getProperty("user.dir").replace("\\", "/")	+ "/Resources/TenderCreation_CreateNewRFQ_TestData_jit.xls", no);
	       } catch (Exception e) {
	      	System.out.println("Unable to read the data from excel file");
	      }
		initializeRepository();
		
		etendercomponentobj.openURL();
		
		VendorRegistrationComponent.ClickOnBidderRegistration();
		
		VendorRegistrationComponent.SelectFreshRegistration();
		
		VendorRegistrationComponent.SelectCountry();
		
		VendorRegistrationComponent.CompanyDetails();
		
		VendorRegistrationComponent.UserDetails();
		
		VendorRegistrationComponent.GetOTP();
		
		VendorRegistrationComponent.WaitforOTP();
		
		
		
	}
}
