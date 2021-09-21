package com.testScripts_mjunction;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.components.PostTenderComponent;
import com.components.eTenderComponent;

public class TC_EPS_PO_49_ValidateCancelPoWFWithPreDefinedWFProcessTest extends BaseClass_Web {

	public eTenderComponent etendercomponentobj = new eTenderComponent(pdfResultReport);

	public PostTenderComponent posttendercomponentobj = new PostTenderComponent(pdfResultReport);

	public void initializeRepository() throws Exception {
		reportDetails.put("Test Script Name", this.getClass().getSimpleName());
		reportDetails.put("Test Script MyWorksshop Document ID", "Doc1234567");
		reportDetails.put("Test Script Revision No", "48");
		reportDetails.put("Test Author Name", "venkatesh jujjuru");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}

	@Parameters("TestcaseNo")
	@Test(description = "Verify the Functionality of User Defined Parallel Process  PO Cancellation WF")
	public void validateCancelPoWorkFlowWithUSerDefinedParallelProcess(String no) throws Throwable {
		System.out.println("Entered in the Test method..................");
		try {
			pdfResultReport.readTestDataFile(System.getProperty("user.dir").replace("\\", "/")
					+ "/Resources/TenderCreation_CreateNewRFQ_TestData_venkateshNew.xls", no);
		} catch (Exception e) {
			System.out.println("Unable to read the data from excel file");
		}

		initializeRepository();

		etendercomponentobj.openURL();
		posttendercomponentobj.super_Admin_Login();
	
		posttendercomponentobj.navigateTo_DOPList("Po_DOp_Joy");
		
		posttendercomponentobj.verifyDop_ActivationLink("Po_DOp_Joy");
		
		etendercomponentobj.tenderLogout();

		posttendercomponentobj.po_Creator_Login();
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
		posttendercomponentobj.ApprovalNotRequired();

		posttendercomponentobj.navigateToPurchasrOrderList();
		posttendercomponentobj.searchThePoRefNoInPoListPage();
		posttendercomponentobj.verifyPOStatusIsInPendingForAcceptance();

		posttendercomponentobj.clickCancelPOInActionMenu();

		posttendercomponentobj.sendForApprovalPreDefinedForCancelPO();

		posttendercomponentobj.poCancellationSucessesMessageValidationForApprover();

		posttendercomponentobj.navigateToPurchasrOrderList();

		posttendercomponentobj.searchThePoRefNoInPoListPage();

		posttendercomponentobj.verifyPOStatusIsPendingForCancellationApproval();

		etendercomponentobj.tenderLogout();

		// Approval1
		posttendercomponentobj.poApprover1Login();
		posttendercomponentobj.navigateToApprovalPendingPage();
		posttendercomponentobj.clickCancelPurchaseOrderTab();
		posttendercomponentobj.navigateToPurchaseOrderApproval();
		posttendercomponentobj.provide_PO_ApproverComment();
		posttendercomponentobj.purchaseOrderApproval();
		etendercomponentobj.tenderLogout();

		// Approval2
		posttendercomponentobj.poApprover2Login();
		posttendercomponentobj.navigateToApprovalPendingPage();
		posttendercomponentobj.clickCancelPurchaseOrderTab();
		posttendercomponentobj.navigateToPurchaseOrderApproval();
		posttendercomponentobj.provide_PO_ApproverComment();
		posttendercomponentobj.purchaseOrderApproval();
		etendercomponentobj.tenderLogout();
		
		posttendercomponentobj.poApprover3Login();
		posttendercomponentobj.navigateToApprovalPendingPage();
		posttendercomponentobj.clickOnCancelPurchaseOrderTab();
		posttendercomponentobj.navigateToPurchaseOrderApproval();
		posttendercomponentobj.provide_PO_ApproverComment();
		posttendercomponentobj.purchaseOrderApproval();
		etendercomponentobj.tenderLogout();

		posttendercomponentobj.po_Creator_Login();
		posttendercomponentobj.navigateToPurchasrOrderList();
		posttendercomponentobj.clickCancelledPoTab();
		posttendercomponentobj.searchThePoRefNoInPoListPage();
		posttendercomponentobj.verifyPoRefNumberInPoListPage();
		etendercomponentobj.tenderLogout();

		etendercomponentobj.bidder_01_Login();
		posttendercomponentobj.navigateToPoListingWithBidderUser();
		posttendercomponentobj.verifyCancelledPoIsPresentOrNotInCancelledPOTab("Cancelled");
		posttendercomponentobj.searchThePoRefNoInPoListPage();
		posttendercomponentobj.verifyCancelledPOActionDropdownListInBidderUserPoList();
		etendercomponentobj.tenderLogout();

		posttendercomponentobj.super_Admin_Login();
		
		posttendercomponentobj.navigateTo_DOPList("Po_DOp_Joy");
		
		posttendercomponentobj.verify_Dop_InactiveLink("Po_DOp_Joy");

		posttendercomponentobj.closeBrowser();


	}

}
