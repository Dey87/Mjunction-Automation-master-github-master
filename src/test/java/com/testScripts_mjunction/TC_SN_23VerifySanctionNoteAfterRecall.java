package com.testScripts_mjunction;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.components.PostTenderComponent;
import com.components.eTenderComponent;

public class TC_SN_23VerifySanctionNoteAfterRecall extends BaseClass_Web{
	public PostTenderComponent posttendercomponentobj =new PostTenderComponent(pdfResultReport);
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
	@Test(description = "Scenario:23 - Create a sanction note with approver and verify the sanction stage after recall")
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
	  posttendercomponentobj.RecallForSanctionCreator();
	  posttendercomponentobj.verifyDraftStage();
	  etendercomponentobj.tenderLogout();
	}
}