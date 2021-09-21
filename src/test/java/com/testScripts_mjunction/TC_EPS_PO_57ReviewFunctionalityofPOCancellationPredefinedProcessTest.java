package com.testScripts_mjunction;


import com.components.PostTenderComponent;
import com.components.eTenderComponent;
import com.objectRepository.TenderCreation_Locators;

import org.openqa.selenium.By;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.components.eTenderComponent;


public class TC_EPS_PO_57ReviewFunctionalityofPOCancellationPredefinedProcessTest extends BaseClass_Web {

	public eTenderComponent etendercomponentobj =new eTenderComponent(pdfResultReport);
	public TenderCreation_Locators tendercreationobj =new TenderCreation_Locators(); 
	public PostTenderComponent posttendercomponentobj =new PostTenderComponent(pdfResultReport);
	
	public void initializeRepository() throws Exception {		
		reportDetails.put("Test Script Name", this.getClass().getSimpleName());
		reportDetails.put("Test Script MyWorksshop Document ID", "Doc1234567");
		reportDetails.put("Test Script Revision No", "57");
		reportDetails.put("Test Author Name", "Pooja");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}
	@Parameters("TestcaseNo")
	@Test(description = "Scenario:57 -Verify the Review Functionality of PO cancellation WF as PO Approver for Predefined Process")
  public void f(String no) throws Throwable {
	  System.out.println("Entered in the Test method..................");
	  try {
		pdfResultReport.readTestDataFile(System.getProperty("user.dir").replace("\\", "/")	+ "/Resources/TenderCreation_CreateNewRFQ_TestData_pooja.xls", no);
	} catch (Exception e) {
		System.out.println("Unable to read the data from excel file");
	}
	  initializeRepository();
	  etendercomponentobj.openURL();
	  posttendercomponentobj.super_Admin_Login();
	  
	  posttendercomponentobj.navigateTo_DOPList("Po_DOp_Joy");
		
	  posttendercomponentobj.verifyDop_ActivationLink("Po_DOp_Joy");
	  
	  posttendercomponentobj.clickPostTenderProcessLink();
	  posttendercomponentobj.enterCompleted_TenderId() ;
	  posttendercomponentobj.IssuePO();
	  posttendercomponentobj.TempleteGroup_and_Vendor_Selection_CTS();
	  posttendercomponentobj.Addquantity_Final_Items();
	  posttendercomponentobj.ProceedtoCreatePO();
	  posttendercomponentobj.CreatePOReferenceNum();
	  posttendercomponentobj.PODetailsTAB();
	  posttendercomponentobj.POAttachmentTAB();
	  posttendercomponentobj.POITemDetailsTAB();
	  posttendercomponentobj.POTRermsandConditionTAB();
	  posttendercomponentobj.POSaveandApproval();
	  posttendercomponentobj.savePoDocNumber();
	  posttendercomponentobj.ApprovalNotRequired();
	  etendercomponentobj.tenderLogout();
	  
	  
	  posttendercomponentobj.super_Admin_Login();
	  posttendercomponentobj.navigateToPurchasrOrderList();
	  posttendercomponentobj.searchThePoRefNoInPoListPage();
	  posttendercomponentobj.POCreatorCancelsAcceptedPO();
	  posttendercomponentobj.sendForApprovalPreDefinedForCancelPO();
	  posttendercomponentobj.poCancellationSucessesMessageValidationForApprover();
	  etendercomponentobj.tenderLogout();
  
	  posttendercomponentobj.poApprover1Login();
	  posttendercomponentobj.navigateToApprovalPendingPage();
	  posttendercomponentobj.clickOnCancelPurchaseOrderTab();
	  posttendercomponentobj.navigateToPurchaseOrderApproval();
	  posttendercomponentobj.provide_PO_ApproverComment();
	  
	  
	  posttendercomponentobj.cancelPurchaseOrderReview();
	  etendercomponentobj.tenderLogout();
	  
	  posttendercomponentobj.super_Admin_Login();
	  
	  posttendercomponentobj.navigateTo_DOPList("Po_DOp_Joy");
		
	  posttendercomponentobj.verify_Dop_InactiveLink("Po_DOp_Joy");
	  
	  posttendercomponentobj.closeBrowser();
	  
	}

}
