package com.testScripts_mjunction;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.components.PostTenderComponent;
import com.components.eTenderComponent;

public class TC_EPSPO_54_TC_EPSPOVerifyApproveFunctionalityOfPOCancellation extends BaseClass_Web {

	public PostTenderComponent posttendercomponentobj = new PostTenderComponent(pdfResultReport);

	public eTenderComponent etendercomponentobj = new eTenderComponent(pdfResultReport);

	public void initializeRepository() throws Exception {
		reportDetails.put("Test Script Name", this.getClass().getSimpleName());
		reportDetails.put("Test Script MyWorksshop Document ID", "Doc1234567");
		reportDetails.put("Test Script Revision No", "45");
		reportDetails.put("Test Author Name", "Vamshi");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}

	@Parameters("TestcaseNo")
	@Test(description = "Verify the approval functionality of PO cancellation WF for Predefined, User Defined and Not required radio  buttons")
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
		posttendercomponentobj.super_Admin_Login();

		posttendercomponentobj.navigateTo_DOPList("Po_DOp_Joy");

		posttendercomponentobj.verifyDop_ActivationLink("Po_DOp_Joy");
		etendercomponentobj.tenderLogout();

		posttendercomponentobj.POCreatorLogin();
		posttendercomponentobj.navigateToPOListing();
		posttendercomponentobj.sanctionNoteSelectionFunctionality();
		posttendercomponentobj.SearchScantionID();
		posttendercomponentobj.clickOnCreatePOLink();

		posttendercomponentobj.TempleteGroup_and_Vendor_Selection_TCS();
		posttendercomponentobj.Addquantity_Final_Items();

		posttendercomponentobj.ProceedtoCreatePO();
		posttendercomponentobj.CreatePOReferenceNum();
		// edit
		posttendercomponentobj.EditPO();

		posttendercomponentobj.PODetailsTAB();
		posttendercomponentobj.POAttachmentTAB();
		posttendercomponentobj.POITemDetailsTAB();

		posttendercomponentobj.POTRermsandConditionTAB();
		posttendercomponentobj.POSaveandApproval();
		posttendercomponentobj.savePoDocNumber();
		// add all po types
		posttendercomponentobj.SelectallApproval();
		posttendercomponentobj.ApprovalNotRequired();

		posttendercomponentobj.CancelPOUserDefined();
		etendercomponentobj.tenderLogout();

		// Approval1
		posttendercomponentobj.poApprover1Login();
		posttendercomponentobj.navigateToApprovalPendingPage();
		posttendercomponentobj.navigateToPurchaseOrderApproval2();
		posttendercomponentobj.provide_PO_ApproverComment();
		posttendercomponentobj.purchaseOrderApproval();
		etendercomponentobj.tenderLogout();

		// Approval2
		posttendercomponentobj.poApprover2Login();
		posttendercomponentobj.navigateToApprovalPendingPage();
		posttendercomponentobj.navigateToPurchaseOrderApproval2();
		posttendercomponentobj.provide_PO_ApproverComment();
		posttendercomponentobj.purchaseOrderApproval();
		etendercomponentobj.tenderLogout();

		posttendercomponentobj.super_Admin_Login();
		posttendercomponentobj.navigateToPOListing();
		posttendercomponentobj.statusOfPOInCancelledPOTab1();
		etendercomponentobj.tenderLogout();

		posttendercomponentobj.super_Admin_Login();
		posttendercomponentobj.navigateTo_DOPList("Po_DOp_Joy");
		posttendercomponentobj.verify_Dop_InactiveLink("Po_DOp_Joy");
		etendercomponentobj.tenderLogout();
	}
}