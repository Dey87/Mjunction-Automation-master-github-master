package com.testScripts_mjunction;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.components.eTenderComponent;

public class TenderCreationTest extends BaseClass_Web{
	
	public eTenderComponent etendercomponentobj =new eTenderComponent(pdfResultReport); 
	public void initializeRepository() throws Exception {		
		reportDetails.put("Test Script Name", this.getClass().getSimpleName());
		reportDetails.put("Test Script MyWorksshop Document ID", "Doc1234567");
		reportDetails.put("Test Script Revision No", "1");
		reportDetails.put("Test Author Name", "Venkat");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}
	
	@Parameters("TestcaseNo")
	@Test(description = "Scenario:1 - Test the Tender Creation Using With Optional Items & Qty Editable by Supplier_V2 as Template Group")
  public void f(String no) throws Throwable {
	  System.out.println("Entered in the Test method..................");
	  try {
		pdfResultReport.readTestDataFile(System.getProperty("user.dir").replace("\\", "/")	+ "/Resources/TenderCreation_CreateNewRFQ_TestData.xls", no);
	} catch (Exception e) {
		System.out.println("Unable to read the data from excel file");
	}
	  initializeRepository();
	  
	  etendercomponentobj.openURL();
	  etendercomponentobj.tendercreatorLogin();
	  etendercomponentobj.navigateToTenderList();
	  etendercomponentobj.createTender_WithOptionalItemsAndQtyEditable();
	  
	}
}
