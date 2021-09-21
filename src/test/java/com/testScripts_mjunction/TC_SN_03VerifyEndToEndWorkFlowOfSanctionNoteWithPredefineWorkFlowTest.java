package com.testScripts_mjunction;

import com.components.PostTenderComponent;
import com.components.eTenderComponent;
import com.objectRepository.TenderCreation_Locators;

import org.openqa.selenium.By;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.components.eTenderComponent;

public class TC_SN_03VerifyEndToEndWorkFlowOfSanctionNoteWithPredefineWorkFlowTest extends BaseClass_Web {

	public eTenderComponent etendercomponentobj = new eTenderComponent(pdfResultReport);
	public TenderCreation_Locators tendercreationobj = new TenderCreation_Locators();
	public PostTenderComponent posttendercomponentobj = new PostTenderComponent(pdfResultReport);

	public void initializeRepository() throws Exception {
		reportDetails.put("Test Script Name", this.getClass().getSimpleName());
		reportDetails.put("Test Script MyWorksshop Document ID", "Doc1234567");
		reportDetails.put("Test Script Revision No", "3");
		reportDetails.put("Test Author Name", "Pooja");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}

	@Parameters("TestcaseNo")
	@Test(description = "Scenario:3 - Verify the functionality of End to End workflow of Sanction Note with Predefine workflow")
	public void f(String no) throws Throwable {
		System.out.println("Entered in the Test method..................");
		try {
			pdfResultReport.readTestDataFile(System.getProperty("user.dir").replace("\\", "/")
					+ "/Resources/TenderCreation_CreateNewRFQ_TestData_pooja.xls", no);
		} catch (Exception e) {
			System.out.println("Unable to read the data from excel file");
		}
		initializeRepository();

		deleteBrowserCookies();

		etendercomponentobj.openURL();
		posttendercomponentobj.super_Admin_Login();

		posttendercomponentobj.navigateTo_DOPListPage();

		posttendercomponentobj.filterDopWithModuleAndStatus("Sanction", "Draft");

		posttendercomponentobj.filterDopWithModuleAndStatus("Sanction", "Active");

		posttendercomponentobj.navigateTo_DOPList("DopName_San_2819");

		posttendercomponentobj.verifyDopActivationLink();

		posttendercomponentobj.clickPostTenderProcessLink();
		posttendercomponentobj.enterCompleted_TenderId();
		posttendercomponentobj.createSanctionNote();
		posttendercomponentobj.sanctionReferenceNumber();
		posttendercomponentobj.SanctionsupplierSelection();
		posttendercomponentobj.SanctionItemsAllotment();
		posttendercomponentobj.ScantionComment_recommendationTab();
		posttendercomponentobj.clickOnSubmitButton();
		posttendercomponentobj.documentNoSave();
		posttendercomponentobj.sendForApprovalPredefineWorkFlow();
		posttendercomponentobj.enterDocumentNoInSearch();
		posttendercomponentobj.viewDetailsValidation();
		posttendercomponentobj.SN_ApprovalDetailsValidation();
		posttendercomponentobj.clickOnDownloadSanctionReport();
		etendercomponentobj.tenderLogout();
		// approver1
		posttendercomponentobj.sanctionNoteApprover1Login();
		posttendercomponentobj.navigateToApprovalPendingPage();
		posttendercomponentobj.enterDocumentNoInSearchSanctionApprover();
		posttendercomponentobj.sanctionNoteEvaluationValidation();
		posttendercomponentobj.provideApproverComment();
		posttendercomponentobj.sanctionNoteEvaluationApprove();
		posttendercomponentobj.enterDocumentNoInSearchSanctionApprover();
		etendercomponentobj.tenderLogout();

		posttendercomponentobj.super_Admin_Login();
		posttendercomponentobj.clickPostTenderProcessLink();
		posttendercomponentobj.enterCompleted_TenderId();
		posttendercomponentobj.navigateToCompletedTenderDetailsPage();
		posttendercomponentobj.enterDocumentNoInSearch();
		posttendercomponentobj.issuePObuttonValidation();

		posttendercomponentobj.navigateTo_DOPList("DopName_San_2819");
		posttendercomponentobj.verifyDopInactiveLink();
		etendercomponentobj.tenderLogout();
	}

}
