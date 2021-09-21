package com.testScripts_mjunction;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.components.PostTenderComponent;
import com.components.eTenderComponent;

public class TC_EPS_PO_07VerifyAllPagesPurchaseOrderListValidationsInPOCreatorLogin extends BaseClass_Web{
	public PostTenderComponent postTenderComponent =new PostTenderComponent(pdfResultReport);
	public eTenderComponent etendercomponentobj =new eTenderComponent(pdfResultReport);
	
	public void initializeRepository() throws Exception {		
		reportDetails.put("Test Script Name", this.getClass().getSimpleName());
		reportDetails.put("Test Script MyWorksshop Document ID", "Doc1234567");
		reportDetails.put("Test Script Revision No", "9");
		reportDetails.put("Test Author Name", "Vamshi");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}
	@Parameters("TestcaseNo")
	@Test(description = "Scenario:07 -  Verify the Edit Icon in purchased order list after login the application with PO Viewer")
  public void f(String no) throws Throwable {
	  System.out.println("Entered in the Test method..................");
	  try {
		pdfResultReport.readTestDataFile(System.getProperty("user.dir").replace("\\", "/")	+ "/Resources/TenderCreation_CreateNewRFQ_TestData_Vamshi.xls", no);
	} catch (Exception e) {
		System.out.println("Unable to read the data from excel file");
	}
	  initializeRepository();
	  etendercomponentobj.openURL();
	  postTenderComponent.POCreatorLogin();
	  postTenderComponent.navigateToPOListing();
	  postTenderComponent.createPO();
	  postTenderComponent.TempleteGroup_and_Vendor_Selection_TCS();
	  postTenderComponent.Addquantity_Final_Items();
	  
	  postTenderComponent.ProceedtoCreatePO();
	  postTenderComponent.CreatePOReferenceNum();
	  postTenderComponent.PODetailsTAB();
	  postTenderComponent.POAttachmentTAB();
	  postTenderComponent.POITemDetailsTAB();
	  postTenderComponent.POTRermsandConditionTAB();
	  postTenderComponent.POSave();
	  
	  postTenderComponent.navigateToPOListing();
	  postTenderComponent.verifyPOFieldsInPOCreatorLogin();
	  etendercomponentobj.tenderLogout();
	}
}