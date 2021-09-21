package com.testScripts_mjunction;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.components.InvoiceComponent;
import com.components.PostTenderComponent;
import com.components.RfqFromIndentComponent;
import com.components.eTenderComponent;

public class TC_RFQ_from_Indent_001VerifyTCCanSearchDifferentIndentType extends BaseClass_Web {

	public eTenderComponent etendercomponentobj =new eTenderComponent(pdfResultReport);
	public PostTenderComponent posttendercomponentobj =new PostTenderComponent(pdfResultReport);
	 public InvoiceComponent invoicecomponentobj=new InvoiceComponent(pdfResultReport);
	 public RfqFromIndentComponent rfqfromintendcomponentobj = new RfqFromIndentComponent(pdfResultReport);
	/**
	 * TestScript Environment Details
	 * 
	 * @throws Exception
	 */
	public void initializeRepository() throws Exception {
		reportDetails.put("Test Script Name", this.getClass().getSimpleName());
		reportDetails.put("Test Script MyWorksshop Document ID", "Doc1234567");
		reportDetails.put("Test Script Revision No", "01");
		reportDetails.put("Test Author Name", "pooja");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}

	@Parameters("TestcaseNo")
	@Test(description = "TC_RFQ_from_Indent_001:Verify TC can search different Indent type from Create RFQ From Indent table")
      public void VerifyTCCanSearchDifferentIndentTypeTest(String no) throws Throwable {
	  System.out.println("Entered in the Test method..................");
	  try {
		pdfResultReport.readTestDataFile(System.getProperty("user.dir").replace("\\", "/")	+ "/Resources/TenderCreation_CreateNewRFQ_TestData_pooja.xls", no);
	       } catch (Exception e) {
	      	System.out.println("Unable to read the data from excel file");
	      }
		initializeRepository();
		etendercomponentobj.openURL();
	    etendercomponentobj.tendercreatorLogin();
		rfqfromintendcomponentobj.navigateToCreateRFQFromIndentPage();
		rfqfromintendcomponentobj. filterIndentTypeFromDropdown("Quick");
		rfqfromintendcomponentobj. filterIndentTypeFromDropdown("Normal");
		rfqfromintendcomponentobj. filterIndentTypeFromDropdown("Grouped");
		rfqfromintendcomponentobj. filterIndentTypeFromDropdown("Split");
		etendercomponentobj.tenderLogout();
	}
}
