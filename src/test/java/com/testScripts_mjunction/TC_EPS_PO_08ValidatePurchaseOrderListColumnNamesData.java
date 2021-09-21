package com.testScripts_mjunction;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.components.PostTenderComponent;
import com.components.eTenderComponent;

public class TC_EPS_PO_08ValidatePurchaseOrderListColumnNamesData extends BaseClass_Web{
	public PostTenderComponent postTenderComponent =new PostTenderComponent(pdfResultReport);
	public eTenderComponent etendercomponentobj =new eTenderComponent(pdfResultReport);
	
	public void initializeRepository() throws Exception {		
		reportDetails.put("Test Script Name", this.getClass().getSimpleName());
		reportDetails.put("Test Script MyWorksshop Document ID", "Doc1234567");
		reportDetails.put("Test Script Revision No", "8");
		reportDetails.put("Test Author Name", "Prasad");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}
	@Parameters("TestcaseNo")
	@Test(description = "Scenario:08 -  Click on the PO listing submenu under PO Menu on the left hand of the dashboard -->  MY PO/ Other's PO")
  public void f(String no) throws Throwable {
	  System.out.println("Entered in the Test method..................");
	  try {
		pdfResultReport.readTestDataFile(System.getProperty("user.dir").replace("\\", "/")	+ "/Resources/TenderCreation_CreateNewRFQ_TestData_Prasad.xls", no);
	} catch (Exception e) {
		System.out.println("Unable to read the data from excel file");
	}
	  initializeRepository();
	  etendercomponentobj.openURL();
	  postTenderComponent.POCreatorLogin();
	  postTenderComponent.navigateToPOListing();
	  postTenderComponent.verifyPOFields();
	  postTenderComponent.verifyPOStatusDraft();
	  postTenderComponent.verifyPOStatusAccepted();
	  postTenderComponent.verifyPOStatusPendingForAcceptance();
	  postTenderComponent.verifyPOStatusPendingForApproval();
	  postTenderComponent.navigateToOthersPOTab();
	  postTenderComponent.verifyOthersPOFields();
	  postTenderComponent.verifyPOStatusDraft();
	  postTenderComponent.verifyPOStatusAccepted();
	  postTenderComponent.verifyPOStatusPendingForAcceptance();
	  postTenderComponent.verifyPOStatusPendingForApproval();
	  etendercomponentobj.tenderLogout();
	  
	}
}