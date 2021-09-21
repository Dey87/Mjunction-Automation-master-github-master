package com.testScripts_mjunction;

import java.util.Arrays;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.components.PostTenderComponent;
import com.components.eTenderComponent;

public class TC_EPS_PO_73verifyApproverCommentSectionForParallelApprovalAfterPORelease extends BaseClass_Web {

	public eTenderComponent etendercomponentobj = new eTenderComponent(pdfResultReport);

	public PostTenderComponent posttendercomponentobj = new PostTenderComponent(pdfResultReport);

	public void initializeRepository() throws Exception {
		reportDetails.put("Test Script Name", this.getClass().getSimpleName());
		reportDetails.put("Test Script MyWorksshop Document ID", "Doc1234567");
		reportDetails.put("Test Script Revision No", "72");
		reportDetails.put("Test Author Name", "Vamshi");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}

	@Parameters("TestcaseNo")
	@Test(description = "Verify the sequence in approver comment section for user defined parallel approval after PO release")
	public void validateEditPoLinkAndFileldsShouldBeEditable(String no) throws Throwable {
		System.out.println("Entered in the Test method..................");
		try {
			pdfResultReport.readTestDataFile(System.getProperty("user.dir").replace("\\", "/")
					+ "/Resources/TenderCreation_CreateNewRFQ_TestData_Vamshi.xls", no);
		} catch (Exception e) {
			System.out.println("Unable to read the data from excel file");
		}

		initializeRepository();

		deleteBrowserCookies();

		etendercomponentobj.openURL();
		posttendercomponentobj.POCreatorLogin();
		posttendercomponentobj.navigateToPOListing();
		posttendercomponentobj.createPO();
		posttendercomponentobj.TempleteGroup_and_Vendor_Selection_TCS();
		posttendercomponentobj.Addquantity_Final_Items();
		  
		posttendercomponentobj.ProceedtoCreatePO();
		posttendercomponentobj.CreatePOReferenceNum();
		posttendercomponentobj.PODetailsTAB();
		posttendercomponentobj.POAttachmentTAB();
		posttendercomponentobj.POITemDetailsTAB();
		posttendercomponentobj.POTermsandConditionTAB();
		posttendercomponentobj.POSave();
		posttendercomponentobj.navigateToPOListing();
		posttendercomponentobj.verifyPOinDraftStatus();
		
		//Edit PO
		posttendercomponentobj.EditPO1();
		posttendercomponentobj.POSaveandApproval();
		posttendercomponentobj.savePoDocNumber();
		posttendercomponentobj.sendForApprovalUserDefinedParallelUser_pocreator();
		posttendercomponentobj.enterPOInSearch();
		posttendercomponentobj.verifyPOinPendingForApprovalStatus1();
		etendercomponentobj.tenderLogout();
		
		//Approval1
		posttendercomponentobj.poApprover1Login();
		posttendercomponentobj.navigateToApprovalPendingPage();
		posttendercomponentobj.navigateToPurchaseOrderApprovalPage();

		posttendercomponentobj.verifyPoApproverSummaryTabDetails("TCS");
		posttendercomponentobj.clickCommentTab();

		posttendercomponentobj.clickApprovalHistoryBtn();
		posttendercomponentobj.verifyUserNameColumnSeqInApprovalHistory(
				Arrays.asList("PO Approver1 test", "Self", "PO Creator test"));

		posttendercomponentobj
				.verifyUserNameStatusColumnSeqInApprovalHistory(Arrays.asList("WIP", "WIP", "Initiated"));

		posttendercomponentobj.provide_PO_ApproverComment();
		posttendercomponentobj.purchaseOrderApproval();
		etendercomponentobj.tenderLogout();

		// Approval2
		posttendercomponentobj.poApprover2Login();
		posttendercomponentobj.navigateToApprovalPendingPage();
		posttendercomponentobj.navigateToPurchaseOrderApprovalPage();
		posttendercomponentobj.verifyPoApproverSummaryTabDetails("TCS");

		posttendercomponentobj.clickCommentTab();

		posttendercomponentobj.clickApprovalHistoryBtn();

		posttendercomponentobj
				.verifyUserNameColumnSeqInApprovalHistory(Arrays.asList("PO Approver test", "Self", "PO Creator test"));

		posttendercomponentobj.verifyCommentColumnSeqInApprovalHistory1();

		posttendercomponentobj
				.verifyUserNameStatusColumnSeqInApprovalHistory(Arrays.asList("Approved", "WIP", "Initiated"));
		posttendercomponentobj.navigateToApprovalPendingPage();
		posttendercomponentobj.navigateToPurchaseOrderApproval();
		posttendercomponentobj.provide_PO_ApproverComment();
		posttendercomponentobj.purchaseOrderApproval();
		etendercomponentobj.tenderLogout();
		
		//PO Creator Login
		posttendercomponentobj.POCreatorLogin();
		posttendercomponentobj.navigateToPOListing();
		posttendercomponentobj.selectApprovalHistoryInPendingForAccepetedPO();
		etendercomponentobj.tenderLogout();
	}
}