package com.testScripts_mjunction;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.components.PostTenderComponent;
import com.components.eTenderComponent;

public class TC_SN_26VerifyApproverStatusAfterSelectingUserInSanctionNoteCreator extends BaseClass_Web{
	public PostTenderComponent postTenderComponent =new PostTenderComponent(pdfResultReport);
	public eTenderComponent etendercomponentobj =new eTenderComponent(pdfResultReport);
	
	public void initializeRepository() throws Exception {		
		reportDetails.put("Test Script Name", this.getClass().getSimpleName());
		reportDetails.put("Test Script MyWorksshop Document ID", "Doc1234567");
		reportDetails.put("Test Script Revision No", "26");
		reportDetails.put("Test Author Name", "Vamshi");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}
	@Parameters("TestcaseNo")
	@Test(description = "Scenario:26 - Check the approver status after creating a sanction note with one user")
  public void f(String no) throws Throwable {
	  System.out.println("Entered in the Test method..................");
	  try {
		pdfResultReport.readTestDataFile(System.getProperty("user.dir").replace("\\", "/")	+ "/Resources/TenderCreation_CreateNewRFQ_TestData_Vamshi.xls", no);
	} catch (Exception e) {
		System.out.println("Unable to read the data from excel file");
	}
	  initializeRepository();
	  etendercomponentobj.openURL();
	  postTenderComponent.sanction_Creator_Login();
	  postTenderComponent.clickPostTenderProcessLink();
	  postTenderComponent.enterCompleted_TenderId();
	  postTenderComponent.clickOnCreateSanctionNote();
	  postTenderComponent.CreateSanctionNoteWithUserdefinedApproval();
	  postTenderComponent.verifyPendingForApprovalSanctionStageAndApprovalDetails();
	  etendercomponentobj.tenderLogout();
	  postTenderComponent.sanctionNoteApprover1Login();
	  postTenderComponent.navigateToApprovalPendingPage();
	  postTenderComponent.enterDocumentNoInSearchSanctionApprover();
	  postTenderComponent.wipApproverStatus();
	}
}