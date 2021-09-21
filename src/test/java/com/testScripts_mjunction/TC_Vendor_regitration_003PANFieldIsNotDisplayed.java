package com.testScripts_mjunction;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.components.InvoiceComponent;
import com.components.PostTenderComponent;
import com.components.RfqFromIndentComponent;
import com.components.VendorRegistrationComponent;
import com.components.eTenderComponent;

public class TC_Vendor_regitration_003PANFieldIsNotDisplayed extends BaseClass_Web {

	public eTenderComponent etendercomponentobj =new eTenderComponent(pdfResultReport);
	 public VendorRegistrationComponent vendorregistrationcomponentobj=new VendorRegistrationComponent(pdfResultReport);
	 public RfqFromIndentComponent rfqfromintendcomponentobj = new RfqFromIndentComponent(pdfResultReport);
	/**
	 * TestScript Environment Details
	 * 
	 * @throws Exception
	 */
	public void initializeRepository() throws Exception {
		reportDetails.put("Test Script Name", this.getClass().getSimpleName());
		reportDetails.put("Test Script MyWorksshop Document ID", "Doc1234567");
		reportDetails.put("Test Script Revision No", "03");
		reportDetails.put("Test Author Name", "pooja");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}

	@Parameters("TestcaseNo")
	@Test(description = "TC_Vendor_regitration_003:Choosen County Is Not India PAN Field Is not Displayed")
      public void ChoosenCountyIsIndiaPANFieldIsDisplayedTest(String no) throws Throwable {
	  System.out.println("Entered in the Test method..................");
	  try {
		pdfResultReport.readTestDataFile(System.getProperty("user.dir").replace("\\", "/")	+ "/Resources/TenderCreation_CreateNewRFQ_TestData_pooja.xls", no);
	       } catch (Exception e) {
	      	System.out.println("Unable to read the data from excel file");
	      }
		initializeRepository();
		etendercomponentobj.openURL();
		vendorregistrationcomponentobj.ClickOnBidderRegistration();
		vendorregistrationcomponentobj.SelectFreshRegistration();
		vendorregistrationcomponentobj.SelectCountry();
		vendorregistrationcomponentobj.PANFieldValidationForOtherCountry();
		
	}
}
