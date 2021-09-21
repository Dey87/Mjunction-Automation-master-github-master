package com.testScripts_mjunction;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.components.PostTenderComponent;
import com.components.eTenderComponent;
import com.objectRepository.TenderCreation_Locators;

public class TC_SN_22VerifySanctionCreatorAfterReviewing extends BaseClass_Web{
	public eTenderComponent etendercomponentobj =new eTenderComponent(pdfResultReport);
	public TenderCreation_Locators tendercreationobj =new TenderCreation_Locators(); 
	public PostTenderComponent posttendercomponentobj =new PostTenderComponent(pdfResultReport);
	
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
	@Test(description = "Scenario:23 - Create a sanction note with approver and verify the sanction stage after the approver reviews it")
  public void f(String no) throws Throwable {
	  System.out.println("Entered in the Test method..................");
	  try {
		pdfResultReport.readTestDataFile(System.getProperty("user.dir").replace("\\", "/")	+ "/Resources/TenderCreation_CreateNewRFQ_TestData_Vamshi.xls", no);
	} catch (Exception e) {
		System.out.println("Unable to read the data from excel file");
	}
	  initializeRepository();
	  etendercomponentobj.openURL();
	  posttendercomponentobj.sanction_Creator_Login();
	  posttendercomponentobj.clickPostTenderProcessLink();
	  posttendercomponentobj.enterCompleted_TenderId() ;
	  posttendercomponentobj.createSanctionNote();
	  posttendercomponentobj.sanctionReferenceNumber();
	  posttendercomponentobj.SanctionsupplierSelection();
	  posttendercomponentobj.SanctionItemsAllotment();
	  posttendercomponentobj.ScantionComment_recommendationTab();
	  posttendercomponentobj.clickOnSubmitButton();
	  posttendercomponentobj.documentNoSave();
	  posttendercomponentobj.sendForApprovalUserDefinedSequential_Coordinator();
	  posttendercomponentobj.enterDocumentNoInSearch();
	  posttendercomponentobj.viewDetailsValidation();
	  etendercomponentobj.tenderLogout();
	  posttendercomponentobj.sanctionNoteApprover1Login();
	  posttendercomponentobj.navigateToApprovalPendingPage();
	  posttendercomponentobj.enterDocumentNoInSearchSanctionApprover();
	  posttendercomponentobj.sanctionNoteEvaluationValidation();
	  posttendercomponentobj.provideApproverComment();
	  posttendercomponentobj.approverStatusAfterReview();
	  etendercomponentobj.tenderLogout();
	  posttendercomponentobj.sanction_Creator_Login();
	  posttendercomponentobj.clickPostTenderProcessLink();
	  posttendercomponentobj.enterCompleted_TenderId();
	  posttendercomponentobj.clickOnCreateSanctionNote();
	  posttendercomponentobj.verifyDraftStage();
	}
}