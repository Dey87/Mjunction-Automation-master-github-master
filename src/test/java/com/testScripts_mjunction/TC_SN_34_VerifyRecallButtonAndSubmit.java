package com.testScripts_mjunction;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.components.PostTenderComponent;
import com.components.eTenderComponent;

public class TC_SN_34_VerifyRecallButtonAndSubmit extends BaseClass_Web {

	public PostTenderComponent postTenderComponent = new PostTenderComponent(pdfResultReport);

	public eTenderComponent etendercomponentobj = new eTenderComponent(pdfResultReport);
	
	public void initializeRepository() throws Exception {
		reportDetails.put("Test Script Name", this.getClass().getSimpleName());
		reportDetails.put("Test Script MyWorksshop Document ID", "Doc1234567");
		reportDetails.put("Test Script Revision No", "35");
		reportDetails.put("Test Author Name", "Vamshi");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}

	/**
	 * 
	 * @author E005672
	 * @param no
	 * @throws Throwable
	 */
	@Parameters("TestcaseNo")
	@Test(description = "verify Dop creation functionality in sanction module")
	public void validate_DopCreationFunctionality(String no) throws Throwable {
		System.out.println("Entered in the Test method..................");
		try {
			pdfResultReport.readTestDataFile(System.getProperty("user.dir").replace("\\", "/")
					+ "/Resources/TenderCreation_CreateNewRFQ_TestData_Vamshi.xls", no);
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
		//postTenderComponent.verifyPendingForApprovalSanctionStageAndApprovalDetails();
		postTenderComponent.initiateRecallForSanctionCreator();
		etendercomponentobj.tenderLogout();
	}

}