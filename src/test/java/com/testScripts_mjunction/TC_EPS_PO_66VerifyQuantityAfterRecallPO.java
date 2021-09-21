package com.testScripts_mjunction;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.components.PostTenderComponent;
import com.components.eTenderComponent;

public class TC_EPS_PO_66VerifyQuantityAfterRecallPO extends BaseClass_Web{
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
	@Test(description = "Scenario:66 -  Verify the Quantity after Recall PO, Quantity can be added to PO")
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
	  postTenderComponent.POTermsandConditionTAB();
	  postTenderComponent.POSave();
	  postTenderComponent.navigateToPOListing();
	  
	  //View PO
	  postTenderComponent.ViewPO();
	  
	  postTenderComponent.navigateToPOListing();
	  
	  //Edit PO
	  postTenderComponent.EditPO1();
	  postTenderComponent.POSaveandApproval();
	  postTenderComponent.savePoDocNumber();
	  postTenderComponent.sendForApprovalValidation();
	  postTenderComponent.enterPOInSearch();
	  postTenderComponent.verifyPOinPendingForApprovalStatus1();
	  postTenderComponent.RecallPO();
	  postTenderComponent.enterPOInSearchAndDraftVerification();
	  postTenderComponent.verifyEditPOAndItemDetailsPage();
	  etendercomponentobj.tenderLogout();
	}
}