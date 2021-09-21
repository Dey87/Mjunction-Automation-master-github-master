package com.testScripts_mjunction;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.components.ASN_GRNComponent;
import com.components.AuctionComponent;
import com.components.PostTenderComponent;
import com.components.eTenderComponent;
import com.objectRepository.TenderCreation_Locators;

public class TC_ASN_15CreatedASNShouldGoForApprovalToTheIntendedGRNUser extends BaseClass_Web {

	public eTenderComponent etendercomponentobj = new eTenderComponent(pdfResultReport);
	public TenderCreation_Locators tendercreationobj = new TenderCreation_Locators();
	public PostTenderComponent posttendercomponentobj = new PostTenderComponent(pdfResultReport);
	public AuctionComponent auctioncomponentobj = new AuctionComponent(pdfResultReport);
	public ASN_GRNComponent asngrnComponent = new ASN_GRNComponent(pdfResultReport);

	public void initializeRepository() throws Exception {
		reportDetails.put("Test Script Name", this.getClass().getSimpleName());
		reportDetails.put("Test Script MyWorksshop Document ID", "Doc1234567");
		reportDetails.put("Test Script Revision No", "015");
		reportDetails.put("Test Author Name", "Vamshi");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}

	@Parameters("TestcaseNo")
	@Test(description = "TC_ASN_015:User upon selecting the ASN the created ASN should go for approval to the intended GRN user ")
	public void verifyCreatedASNShouldGoForApprovalToTheIntendedGRNUser(String no) throws Throwable {
		System.out.println("Entered in the Test method..................");
		try {
			pdfResultReport.readTestDataFile(System.getProperty("user.dir").replace("\\", "/")
					+ "/Resources/TenderCreation_CreateNewRFQ_TestData_Vamshi.xls", no);
		} catch (Exception e) {
			System.out.println("Unable to read the data from excel file");
		}
		initializeRepository();
		etendercomponentobj.openURL();

		etendercomponentobj.bidder_02_Login();
		asngrnComponent.navigateToASNListInBidderLogin();
		asngrnComponent.CreateASN();
		asngrnComponent.SaveASN();
		asngrnComponent.ASNNumberValidation();
		asngrnComponent.ASNNumber();
		asngrnComponent.TabMyInformation();
		asngrnComponent.TabShipmentInformation("Patna");
		asngrnComponent.TabWhatIamShippingWithBoxesOnly("20", "80", "AU");
		asngrnComponent.TabDeliveryChallenChecklist();
		asngrnComponent.TabInvoice("Invoice");
		asngrnComponent.SaveASN();
		asngrnComponent.SendForApproval();
		asngrnComponent.navigateToASNListInBidderLogin();
		asngrnComponent.verifyPreparedStatus("Prepared");
		etendercomponentobj.tenderLogout();

		// ASN Approver Login
		asngrnComponent.ASNApproverLogin();
		asngrnComponent.navigateToASNList();
		asngrnComponent.verifyPreparedStatus("Prepared");
		asngrnComponent.acceptOrRejectValidationButton();

		// Rejected status
		asngrnComponent.verifyRejectionValidation();
		etendercomponentobj.tenderLogout();
		etendercomponentobj.bidder_02_Login();
		asngrnComponent.navigateToASNListInBidderLogin();
		asngrnComponent.verifyRejectedStatus("Rejected");
		asngrnComponent.editASN();
		asngrnComponent.SendForApproval();
		asngrnComponent.verifyPreparedStatus("Prepared");
		etendercomponentobj.tenderLogout();

		// ASN in Hold status
		asngrnComponent.ASNApproverLogin();
		asngrnComponent.navigateToASNList();
		asngrnComponent.acceptOrRejectValidationButton();
		asngrnComponent.verifyHoldValidation();
		asngrnComponent.verifyHoldStatus("Hold");
		asngrnComponent.navigateToASNList();
		asngrnComponent.verifyApproveValidation();
		etendercomponentobj.tenderLogout();

		// ASN in Completed status
		etendercomponentobj.bidder_02_Login();
		asngrnComponent.navigateToASNListInBidderLogin();
		asngrnComponent.verifyCompletedStatus("Completed");
		etendercomponentobj.tenderLogout();

		// ASN in completed after Inspection, create new PO
		posttendercomponentobj.POCreatorLogin();
		posttendercomponentobj.navigateToPurchasrOrderList();
		posttendercomponentobj.sanctionNoteSelectionFunctionality();
		posttendercomponentobj.enterSanctionRefNumber();
		posttendercomponentobj.clickOnCreatePOLink();
		posttendercomponentobj.TempleteGroup_and_Vendor_Selection_TCS();
		posttendercomponentobj.Addquantity_Final_Items();
		posttendercomponentobj.ProceedtoCreatePO();
		posttendercomponentobj.CreatePOReferenceNum();
		posttendercomponentobj.PODetailsTAB();
		posttendercomponentobj.POAttachmentTAB();
		posttendercomponentobj.POITemDetailsTAB();
		posttendercomponentobj.POTRermsandConditionTAB();
		posttendercomponentobj.POSaveandApproval();
		posttendercomponentobj.savePoDocNumber();
		posttendercomponentobj.ApprovalNotRequired();
		etendercomponentobj.tenderLogout();

		etendercomponentobj.bidder_02_Login();
		posttendercomponentobj.navigateToPoListingWithBidderUser();
		posttendercomponentobj.searchPoRefNoInPoListPage();
		posttendercomponentobj.clickAcceptPoInDropDown();
		posttendercomponentobj.verifySummaryTabAndEnterComment();
		posttendercomponentobj.clickAccepPotBtn();
		posttendercomponentobj.verifyPOStatusIsAccepted();
		etendercomponentobj.tenderLogout();

		// Create new ASN for new PO
		etendercomponentobj.bidder_02_Login();
		asngrnComponent.navigateToASNListInBidderLogin();
		asngrnComponent.CreateASN();
		asngrnComponent.SaveASN();
		asngrnComponent.ASNNumberValidation();
		asngrnComponent.ASNNumber();
		asngrnComponent.TabMyInformation();
		asngrnComponent.TabShipmentInformation("Patna");
		asngrnComponent.TabWhatIamShippingWithBoxesOnly("20", "80", "AU");
		asngrnComponent.TabDeliveryChallenChecklist();
		asngrnComponent.TabInvoice("Invoice");
		asngrnComponent.SaveASN();
		asngrnComponent.SendForApproval();
		// asngrnComponent.navigateToASNListInBidderLogin();
		asngrnComponent.verifyPreparedStatus("Prepared");
		etendercomponentobj.tenderLogout();

		// Inspection status
		asngrnComponent.ASNApproverLogin();
		asngrnComponent.navigateToASNList();
		asngrnComponent.verifyPreparedStatus("Prepared");
		asngrnComponent.acceptOrRejectValidationButton();
		asngrnComponent.verifyInspectValidation();
		etendercomponentobj.tenderLogout();

		asngrnComponent.ASNInspectorLogin();
		asngrnComponent.navigateToASNInspectionList();
		asngrnComponent.validateInpectionInInspectionLogin();
		etendercomponentobj.tenderLogout();

		etendercomponentobj.bidder_02_Login();
		asngrnComponent.navigateToASNListInBidderLogin();
		asngrnComponent.verifyCompletedStatus("Completed");
		etendercomponentobj.tenderLogout();
	}

}