package com.testScripts_mjunction;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.components.PostTenderComponent;
import com.components.eTenderComponent;
import com.objectRepository.TenderCreation_Locators;

public class TC_EPS_PO_41_VerifyTheMailContentOfPOReviewedToPreviousApproversTest extends BaseClass_Web {
	public eTenderComponent etendercomponentobj =new eTenderComponent(pdfResultReport);
	public TenderCreation_Locators tendercreationobj =new TenderCreation_Locators(); 
	public PostTenderComponent posttendercomponentobj =new PostTenderComponent(pdfResultReport);
	
	public void initializeRepository() throws Exception {		
		reportDetails.put("Test Script Name", this.getClass().getSimpleName());
		reportDetails.put("Test Script MyWorksshop Document ID", "Doc1234567");
		reportDetails.put("Test Script Revision No", "40");
		reportDetails.put("Test Author Name", "Venkatesh Jujjuru");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}
	@Parameters("TestcaseNo")
	@Test(description = "TC_EPS_PO_40: VerifyTheMailContentOfPOReviewedToCreatorTest")
  public void validateTheMailContentOfPOReviewedToCreator(String no) throws Throwable {
	  System.out.println("Entered in the Test method..................");
	  try {
		pdfResultReport.readTestDataFile(System.getProperty("user.dir").replace("\\", "/")	+ "/Resources/TenderCreation_CreateNewRFQ_TestData_venkateshNew.xls", no);
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
	  posttendercomponentobj.sendForApprovalUserDefinedSequential2User_pocreator();
	  etendercomponentobj.tenderLogout();
	  
	  
	  posttendercomponentobj.poApprover1Login();
	  posttendercomponentobj.navigateToApprovalPendingPage();
	  posttendercomponentobj.navigateToPurchaseOrderApproval();
	  posttendercomponentobj.provide_PO_ApproverComment();
	  posttendercomponentobj.purchaseOrderApproval();
	  etendercomponentobj.tenderLogout();
	  
	  posttendercomponentobj.poApprover2Login();
	  posttendercomponentobj.navigateToApprovalPendingPage();
	  posttendercomponentobj. navigateToPurchaseOrderApprovalPage();
	  posttendercomponentobj.clickCommentTab();
	  posttendercomponentobj.provideCommentInCommentSection();
	  posttendercomponentobj.clickPoReviewBtnAndConfirmBySecondApprover();
	  etendercomponentobj.tenderLogout();
	 
	  posttendercomponentobj.verifyMailSubjectAndMailBody("TEST_AUTOMATION IS TO BE REVIEWED.", 10,"This is to inform you that","e-PROC SYSTEM TEAM.");
	  
	 
	  etendercomponentobj.closeBrowser();
	    
	}
	

}
