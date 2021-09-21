package com.testScripts_mjunction;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.components.PostTenderComponent;
import com.components.eTenderComponent;

public class TC_EPS_PO_55_ReviewFunctionalityForPOCancellationWithUserDefinedWfTest extends BaseClass_Web {

	public eTenderComponent etendercomponentobj = new eTenderComponent(pdfResultReport);

	public PostTenderComponent posttendercomponentobj = new PostTenderComponent(pdfResultReport);

	public void initializeRepository() throws Exception {
		reportDetails.put("Test Script Name", this.getClass().getSimpleName());
		reportDetails.put("Test Script MyWorksshop Document ID", "Doc1234567");
		reportDetails.put("Test Script Revision No", "55");
		reportDetails.put("Test Author Name", "venkatesh jujjuru");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}

	@Parameters("TestcaseNo")
	@Test(description = "Verify the Review Functionality for  PO Cancellation With UserDefined  Seq WF")
	public void validateReviewFunctionalityPOCancellationWfwWithUSerDefinedSeqProcess(String no) throws Throwable {
		System.out.println("Entered in the Test method..................");
		try {
			pdfResultReport.readTestDataFile(System.getProperty("user.dir").replace("\\", "/")
					+ "/Resources/TenderCreation_CreateNewRFQ_TestData_venkateshNew.xls", no);
		} catch (Exception e) {
			System.out.println("Unable to read the data from excel file");
		}

		initializeRepository();

		etendercomponentobj.openURL();

		/*
		 * posttendercomponentobj.super_Admin_Login();
		 * posttendercomponentobj.clickPostTenderProcessLink();
		 * posttendercomponentobj.enterCompleted_TenderId();
		 * posttendercomponentobj.createSanctionNote();
		 * posttendercomponentobj.sanctionReferenceNumber();
		 * posttendercomponentobj.supplierSelection();
		 * posttendercomponentobj.itemAllotment_TCS();
		 * posttendercomponentobj.itemAllotment_CTS();
		 * posttendercomponentobj.provideComment_recommendationTab();
		 * posttendercomponentobj.clickOnSubmitButton();
		 * posttendercomponentobj.documentNoSave();
		 * posttendercomponentobj.sendForApproval(); etendercomponentobj.tenderLogout();
		 */

		posttendercomponentobj.po_Creator_Login();
		posttendercomponentobj.navigateToPurchasrOrderList();
		posttendercomponentobj.sanctionNoteSelectionFunctionality();
		posttendercomponentobj.enterSanctionRefNumber();
		posttendercomponentobj.clickOnCreatePOLink();
		posttendercomponentobj.TempleteGroup_and_Vendor_Selection_CTS();
		posttendercomponentobj.Addquantity_Final_Items();
		//posttendercomponentobj.Edit_Final_Items();
		posttendercomponentobj.ProceedtoCreatePO();
		posttendercomponentobj.CreatePOReferenceNum();
		posttendercomponentobj.PODetailsTAB();
		posttendercomponentobj.POAttachmentTAB();
		posttendercomponentobj.POITemDetailsTAB();
		posttendercomponentobj.POTermsandConditionTAB();
		posttendercomponentobj.POSaveandApproval();
		posttendercomponentobj.savePoDocNumber();
		posttendercomponentobj.ApprovalNotRequired();

		posttendercomponentobj.navigateToPurchasrOrderList();
		posttendercomponentobj.searchThePoRefNoInPoListPage();
		posttendercomponentobj.verifyPOStatusIsInPendingForAcceptance();

		posttendercomponentobj.clickCancelPOInActionMenu();

		posttendercomponentobj.selcectUserDefinedAndApprovalTypeSeq();
		
		posttendercomponentobj.AddUserDefinedApproverForAnyApprovaltype(1,
				pdfResultReport.testData.get("UserTenderApprover1"));
		
		posttendercomponentobj.AddUserDefinedApproverForAnyApprovaltype(2,
				pdfResultReport.testData.get("UserPoApprover2"));

		posttendercomponentobj.clickSendForApprovalInPo();

		posttendercomponentobj.navigateToPurchasrOrderList();

		posttendercomponentobj.searchThePoRefNoInPoListPage();

		posttendercomponentobj.verifyPOStatusIsPendingForCancellationApproval();

		etendercomponentobj.tenderLogout();

		// Approval1

		posttendercomponentobj.poApprover1Login();
		posttendercomponentobj.navigateToApprovalPendingPage();
		posttendercomponentobj.clickOnCancelPurchaseOrderTab();
		posttendercomponentobj.navigateToPurchaseOrderApproval();
		posttendercomponentobj.provide_PO_ApproverComment();
		posttendercomponentobj.cancelPurchaseOrderReview();
		etendercomponentobj.tenderLogout();

		posttendercomponentobj.po_Creator_Login();
		posttendercomponentobj.navigateToPurchasrOrderList();
		posttendercomponentobj.searchThePoRefNoInPoListPage();
		posttendercomponentobj.verifyPOStatusIsInPendingForAcceptance();
		
		posttendercomponentobj.clickCancelPOInActionMenu();
		
		posttendercomponentobj.selcectUserDefinedAndApprovalTypeSeq();
		
		posttendercomponentobj.AddUserDefinedApproverForAnyApprovaltype(1,
				pdfResultReport.testData.get("UserTenderApprover2"));
		
		posttendercomponentobj.AddUserDefinedApproverForAnyApprovaltype(2,
				pdfResultReport.testData.get("UserPoApprover3"));

		posttendercomponentobj.clickSendForApprovalInPo();

		posttendercomponentobj.navigateToPurchasrOrderList();

		posttendercomponentobj.searchThePoRefNoInPoListPage();

		posttendercomponentobj.verifyPOStatusIsPendingForCancellationApproval();

		etendercomponentobj.tenderLogout();
		
		posttendercomponentobj.closeBrowser();

	}

}
