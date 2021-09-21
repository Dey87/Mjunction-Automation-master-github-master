package com.testScripts_mjunction;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.components.PostTenderComponent;
import com.components.eTenderComponent;

public class TC_EPS_PO_61POExpiryCheckByPOApproverDuringPOApprovalPredefinedTest extends BaseClass_Web {
	public PostTenderComponent posttendercomponentobj = new PostTenderComponent(pdfResultReport);
	public eTenderComponent etendercomponentobj = new eTenderComponent(pdfResultReport);

	public void initializeRepository() throws Exception {
		reportDetails.put("Test Script Name", this.getClass().getSimpleName());
		reportDetails.put("Test Script MyWorksshop Document ID", "Doc1234567");
		reportDetails.put("Test Script Revision No", "61");
		reportDetails.put("Test Author Name", "Pooja");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}

	@Parameters("TestcaseNo")
	@Test(description = "Scenario:61 -  PO expiry check by PO approver during PO Approval and Workflow type -Pre-Defined")
	public void f(String no) throws Throwable {
		System.out.println("Entered in the Test method..................");
		try {
			pdfResultReport.readTestDataFile(System.getProperty("user.dir").replace("\\", "/")
					+ "/Resources/TenderCreation_CreateNewRFQ_TestData_pooja.xls", no);
		} catch (Exception e) {
			System.out.println("Unable to read the data from excel file");
		}
		initializeRepository();
		etendercomponentobj.openURL();
		posttendercomponentobj.super_Admin_Login();
		posttendercomponentobj.navigateTo_DOPList("Po_DOp_Joy");

		posttendercomponentobj.verifyDop_ActivationLink("Po_DOp_Joy");
		etendercomponentobj.tenderLogout();

		posttendercomponentobj.POCreatorLogin();
		posttendercomponentobj.navigateToPurchasrOrderList();
		posttendercomponentobj.sanctionNoteSelectionFunctionality();
		posttendercomponentobj.enterSanctionRefNumber();
		posttendercomponentobj.clickOnCreatePOLink();
		posttendercomponentobj.TempleteGroup_and_Vendor_Selection_CTS();
		posttendercomponentobj.Addquantity_Final_Items();
		posttendercomponentobj.Edit_Final_Items();
		posttendercomponentobj.ProceedtoCreatePO();
		posttendercomponentobj.CreatePOReferenceNum();
		posttendercomponentobj.editPODetailsTAB(3);
		posttendercomponentobj.POAttachmentTAB();
		posttendercomponentobj.POITemDetailsTAB();
		posttendercomponentobj.POTermsandConditionTAB();
		posttendercomponentobj.POSaveandApproval();
		posttendercomponentobj.savePoDocNumber();
		posttendercomponentobj.clickOnCloseInSendForApproval();
		etendercomponentobj.tenderLogout();

		posttendercomponentobj.POCreatorLogin();
		posttendercomponentobj.navigateToPurchasrOrderList();
		posttendercomponentobj.searchThePoRefNoInPoListPage();
		posttendercomponentobj.viewPODetailsValidation();

		posttendercomponentobj.navigateToPurchasrOrderList();
		posttendercomponentobj.searchThePoRefNoInPoListPage();
		posttendercomponentobj.clickOnEditPOUnderActionDropdown();
		posttendercomponentobj.editPODetailsTAB(4);
		posttendercomponentobj.POAttachmentTAB();
		posttendercomponentobj.POITemDetailsTAB();
		posttendercomponentobj.POTermsandConditionTAB();
		posttendercomponentobj.POSaveandApproval();
		posttendercomponentobj.savePoDocNumber();
		posttendercomponentobj.clickPredefinedApprovalAndSendForApproval();
		etendercomponentobj.tenderLogout();
        
		posttendercomponentobj.poApprover1Login();
		posttendercomponentobj.navigateToApprovalPendingPage();
		posttendercomponentobj.navigateToPurchaseOrderApproval();
		posttendercomponentobj.provide_PO_ApproverComment();
		posttendercomponentobj.purchaseOrderApprovalAlertForExpiredPO();
		posttendercomponentobj.cancelPurchaseOrderReview();
		etendercomponentobj.tenderLogout();

		posttendercomponentobj.POCreatorLogin();
		posttendercomponentobj.navigateToPurchasrOrderList();
		posttendercomponentobj.searchThePoRefNoInPoListPage();
		etendercomponentobj.tenderLogout();

		posttendercomponentobj.super_Admin_Login();
		posttendercomponentobj.navigateTo_DOPList("Po_DOp_Joy");
		posttendercomponentobj.verify_Dop_InactiveLink("Po_DOp_Joy");
		etendercomponentobj.tenderLogout();

	}
}
