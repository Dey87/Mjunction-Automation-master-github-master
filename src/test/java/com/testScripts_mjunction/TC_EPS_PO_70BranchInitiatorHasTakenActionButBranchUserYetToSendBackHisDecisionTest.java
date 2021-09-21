package com.testScripts_mjunction;


import com.components.PostTenderComponent;
import com.components.eTenderComponent;
import com.objectRepository.TenderCreation_Locators;

import org.openqa.selenium.By;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.components.eTenderComponent;


public class TC_EPS_PO_70BranchInitiatorHasTakenActionButBranchUserYetToSendBackHisDecisionTest extends BaseClass_Web {

	public eTenderComponent etendercomponentobj =new eTenderComponent(pdfResultReport);
	public TenderCreation_Locators tendercreationobj =new TenderCreation_Locators(); 
	public PostTenderComponent posttendercomponentobj =new PostTenderComponent(pdfResultReport);
	
	public void initializeRepository() throws Exception {		
		reportDetails.put("Test Script Name", this.getClass().getSimpleName());
		reportDetails.put("Test Script MyWorksshop Document ID", "Doc1234567");
		reportDetails.put("Test Script Revision No", "70");
		reportDetails.put("Test Author Name", "Pooja");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}
	@Parameters("TestcaseNo")
	@Test(description = "Scenario 70: - Branch initiator has taken the action but branch user is yet to send back his decision on PO document to the Branch initiator. ")
  public void f(String no) throws Throwable {
	  System.out.println("Entered in the Test method..................");
	  try {
		pdfResultReport.readTestDataFile(System.getProperty("user.dir").replace("\\", "/")	+ "/Resources/TenderCreation_CreateNewRFQ_TestData_pooja.xls", no);
	} catch (Exception e) {
		System.out.println("Unable to read the data from excel file");
	}
	  initializeRepository();
	  etendercomponentobj.openURL();
	  posttendercomponentobj.POCreatorLogin();
	  posttendercomponentobj.navigateToPurchasrOrderList();
	  posttendercomponentobj.sanctionNoteSelectionFunctionality();
	  posttendercomponentobj.enterSanctionRefNumber();
	  posttendercomponentobj.clickOnCreatePOLink();
	  posttendercomponentobj.TempleteGroup_and_Vendor_Selection_CTS();
	  posttendercomponentobj.Addquantity_Final_Items();
	  posttendercomponentobj.ProceedtoCreatePO();
	  posttendercomponentobj.CreatePOReferenceNum();
	  posttendercomponentobj.PODetailsTAB();
	  posttendercomponentobj.POAttachmentTAB();
	  posttendercomponentobj.POITemDetailsTAB();
	  posttendercomponentobj.POTermsandConditionTAB();
	  posttendercomponentobj.POSaveandApproval();
	  posttendercomponentobj.savePoDocNumber();
	  posttendercomponentobj.clickOnCloseInSendForApproval();
	  etendercomponentobj.tenderLogout();  
	  
	  
	  posttendercomponentobj.POCreatorLogin();
	  posttendercomponentobj.navigateToPurchasrOrderList();
	  posttendercomponentobj.searchThePoRefNoInPoListPage();
	  posttendercomponentobj.clickOnEditPOUnderActionDropdown();
	  posttendercomponentobj.PODetailsTAB();
	  posttendercomponentobj.POAttachmentTAB();
	  posttendercomponentobj.POITemDetailsTAB();
	  posttendercomponentobj.POTermsandConditionTAB();
	  posttendercomponentobj.POSaveandApproval();
	  posttendercomponentobj.savePoDocNumber();
	  posttendercomponentobj.sendForApprovalUserDefinedSequential2User_pocreator();
	  posttendercomponentobj.searchThePoRefNoInPoListPage();
	  etendercomponentobj.tenderLogout();
	  
	  posttendercomponentobj.poApprover1Login();
	  posttendercomponentobj.navigateToApprovalPendingPage();
	  posttendercomponentobj.navigateTo_PurchaseOrder_Approval();
	  posttendercomponentobj.provide_PO_ApproverComment();
	  posttendercomponentobj.clickOnApproverCommentSection();
	  posttendercomponentobj.clickOnBranchIcon();
	  posttendercomponentobj.BranchFunctionalityWithAttachment();
	  etendercomponentobj.tenderLogout();
	  
	  posttendercomponentobj.poApprover3Login();
	  posttendercomponentobj.navigateToApprovalPendingPage();
	  posttendercomponentobj.navigateTo_PurchaseOrder_Approval();
	  posttendercomponentobj.provide_PO_ApproverComment();
	  posttendercomponentobj.clickOnApproverCommentSection();
	  etendercomponentobj.tenderLogout();
	  
	  posttendercomponentobj.poApprover1Login();
	  posttendercomponentobj.navigateToApprovalPendingPage();
	  posttendercomponentobj.navigateTo_PurchaseOrder_Approval();
	  posttendercomponentobj.provide_PO_ApproverComment();
	  posttendercomponentobj.clickOnApproverCommentSection();
	  posttendercomponentobj.clickOnBranch_Section_Approver_Comment();
	  posttendercomponentobj.purchaseOrderApproval();
	  etendercomponentobj.tenderLogout();
	  
	  posttendercomponentobj.poApprover3Login();
	  posttendercomponentobj.navigateToApprovalPendingPage();
	  posttendercomponentobj.searchThePoDocNoInBranchUserPage();
	
	 
	}

}
