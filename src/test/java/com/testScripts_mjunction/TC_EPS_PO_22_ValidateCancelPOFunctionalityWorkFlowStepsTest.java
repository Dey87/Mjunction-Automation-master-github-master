package com.testScripts_mjunction;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.components.PostTenderComponent;
import com.components.eTenderComponent;

public class TC_EPS_PO_22_ValidateCancelPOFunctionalityWorkFlowStepsTest extends BaseClass_Web {

	public eTenderComponent etendercomponentobj = new eTenderComponent(pdfResultReport);

	public PostTenderComponent posttendercomponentobj = new PostTenderComponent(pdfResultReport);

	public void initializeRepository() throws Exception {
		reportDetails.put("Test Script Name", this.getClass().getSimpleName());
		reportDetails.put("Test Script MyWorksshop Document ID", "Doc1234567");
		reportDetails.put("Test Script Revision No", "22");
		reportDetails.put("Test Author Name", "venkatesh jujjuru");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}

	@Parameters("TestcaseNo")
	@Test(description = "Verify the Cancel po functionality workflow of PO")
	public void validateCancelPoWorkFlow(String no) throws Throwable {
		System.out.println("Entered in the Test method..................");
		try {
			pdfResultReport.readTestDataFile(System.getProperty("user.dir").replace("\\", "/")
					+ "/Resources/TenderCreation_CreateNewRFQ_TestData_venkateshNew.xls", no);
		} catch (Exception e) {
			System.out.println("Unable to read the data from excel file");
		}

		initializeRepository();

		deleteBrowserCookies();
		etendercomponentobj.openURL();
		
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
		posttendercomponentobj.clickCancelPOInActionMenu();
		posttendercomponentobj.selectApprovalTypePredifined();
		posttendercomponentobj.closeCancelPOWithOutApproval();
		posttendercomponentobj.verifyPOStatusIsInPendingForAcceptance();
		
		
		posttendercomponentobj.clickCancelPOInActionMenu();
		posttendercomponentobj.sendForApprovalUserDefinedForCancelPO();
		
		posttendercomponentobj.navigateToPurchasrOrderList();
		posttendercomponentobj.searchThePoRefNoInPoListPage();
		
		
		etendercomponentobj.tenderLogout();

		// Approval1
		posttendercomponentobj.poApprover1Login();
		posttendercomponentobj.navigateToApprovalPendingPage();
		posttendercomponentobj.clickCancelPurchaseOrderTab();
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

		posttendercomponentobj.super_Admin_Login();
		posttendercomponentobj.clickPostTenderProcessLink();
	    posttendercomponentobj.enterCompleted_TenderId() ;
		posttendercomponentobj.clickOn_POstage();
		posttendercomponentobj.clickPurchaseOrder();
		posttendercomponentobj.verifyCanclledStatusInPurchaseOrderTab();

		posttendercomponentobj.closeBrowser();

	}
}
