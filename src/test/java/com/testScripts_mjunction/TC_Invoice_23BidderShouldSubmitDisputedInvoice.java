package com.testScripts_mjunction;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.components.ASN_GRNComponent;
import com.components.InvoiceComponent;
import com.components.PostTenderComponent;
import com.components.PreConditionPo_SanctionCreation;
import com.components.eTenderComponent;
import com.objectRepository.TenderCreation_Locators;

public class TC_Invoice_23BidderShouldSubmitDisputedInvoice extends BaseClass_Web{
	PreConditionPo_SanctionCreation preConditionPo = new PreConditionPo_SanctionCreation(pdfResultReport);
	public eTenderComponent etendercomponentobj =new eTenderComponent(pdfResultReport);
	public TenderCreation_Locators tendercreationobj =new TenderCreation_Locators(); 
	public PostTenderComponent posttendercomponentobj =new PostTenderComponent(pdfResultReport);
    public ASN_GRNComponent asn_grncomponentobj=new ASN_GRNComponent(pdfResultReport);
    public InvoiceComponent invoicecomponentobj=new InvoiceComponent(pdfResultReport);
	
	public void initializeRepository() throws Exception {		
		reportDetails.put("Test Script Name", this.getClass().getSimpleName());
		reportDetails.put("Test Script MyWorksshop Document ID", "Doc1234567");
		reportDetails.put("Test Script Revision No", "23");
		reportDetails.put("Test Author Name", "Vamshi");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}
	@Parameters("TestcaseNo")
	@Test(description = "TC_Invoice_23: After altering Invoice, Bidder should be able to submit this Disputed Invoice")
  public void validateAlertEventAlreadyExistedAndAuctionRuleInEventColumnTest(String no) throws Throwable {
	  System.out.println("Entered in the Test method..................");
	  try {
		pdfResultReport.readTestDataFile(System.getProperty("user.dir").replace("\\", "/")	+ "/Resources/TenderCreation_CreateNewRFQ_TestData_Vamshi.xls", no);
	} catch (Exception e) {
		System.out.println("Unable to read the data from excel file");
	}
	     initializeRepository();
	     //create GRN
	     
	     etendercomponentobj.openURL();
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
			invoicecomponentobj.searchPoRefNoInPoListPage1();
			invoicecomponentobj.clickAcceptPoInDropDown1();
			posttendercomponentobj.verifySummaryTabAndEnterComment();
			posttendercomponentobj.clickAccepPotBtn();
			posttendercomponentobj.verifyPOStatusIsAccepted();
			etendercomponentobj.tenderLogout();
			
			 etendercomponentobj.openURL();
			 etendercomponentobj.bidder_02_Login();
			 asn_grncomponentobj.SelectASNModule();
			 asn_grncomponentobj.CreateASN();
			 asn_grncomponentobj.SaveASN();
			 asn_grncomponentobj.TabMyInformation();
			 asn_grncomponentobj.TabShipmentInformation("Durgapur");
			 asn_grncomponentobj.TabWhatIamShippingWithBoxesOnly(eTenderComponent.getDataFromPropertiesFile("AsnQTy"),"50","µM");
	         asn_grncomponentobj.TabDeliveryChallenChecklist();
			 asn_grncomponentobj.TabInvoice("Invoice");
			 asn_grncomponentobj.SaveASN();
			 asn_grncomponentobj.SendForApproval();
			 asn_grncomponentobj.SearchPoInASNList();
			 etendercomponentobj.tenderLogout();
			 
	         
	        asn_grncomponentobj.ASNApproverLogin();
	        asn_grncomponentobj.navigateToASNList();
			asn_grncomponentobj.searchThePODocNumber();
			asn_grncomponentobj.clickOnApproveOrRejectUnderActionDropdown();
			asn_grncomponentobj.navigatToEachTabInApproveAdvancedShipmentNoticePage();
			asn_grncomponentobj.approvingTheASN();
			etendercomponentobj.tenderLogout();
			
			asn_grncomponentobj.GRN_Creator_Login();
		 	asn_grncomponentobj.navigateToApprovedASNListPage();
		 	asn_grncomponentobj.enterASNShipmentAndSelectVendorName("TCS",eTenderComponent.getDataFromPropertiesFile("ASNShippingTracking"));
		 	asn_grncomponentobj.clickApprovedAsnListActionMenu(eTenderComponent.getDataFromPropertiesFile("ASNShippingTracking"));
		 	asn_grncomponentobj.createGrn(eTenderComponent.getDataFromPropertiesFile("ASNShippingTracking"));
		 	
		 	asn_grncomponentobj.enterDamageQty1(1,"3.00");
		 	
		 	asn_grncomponentobj.enterExcessQty1(2,"3.00");

			asn_grncomponentobj.saveGrnDetails();
				
		    asn_grncomponentobj.submitGrnDetails();

			asn_grncomponentobj.clickGrnSubmitComfirmationYes();

			asn_grncomponentobj.navigateToGrnListPage();

			asn_grncomponentobj.clickOnGRNCreatedTab();

			asn_grncomponentobj.enterShippementTrackingNoInGrnListSerachBoxAndVerifyCreatedGRN();
			etendercomponentobj.tenderLogout();
	     
	     
	     //etendercomponentobj.openURL();
	     etendercomponentobj.bidder_02_Login();
	     invoicecomponentobj.navigatingToDisputedInvoiceList();
	     invoicecomponentobj.verifyPONumber();
	     invoicecomponentobj.clickAlterInvoiceTabAndValiate();
	     invoicecomponentobj.clickItemDetailsAndValiateItemCodes();
	     invoicecomponentobj.clickItemDetailsAndValiateItemCodesForExcessQuantity();
	     //invoicecomponentobj.validateViewPODetailsInDisputeInvoiceItemCode();
	     invoicecomponentobj.ZeroValueItemValidation();
	     invoicecomponentobj.addingNewRowToValidateZeroValueItemOption();
	     
	     invoicecomponentobj.validateViewPODetailsInDisputeInvoiceItemCode();
	     invoicecomponentobj.clickOnViewASNInDisputedInvoicePage();
	     invoicecomponentobj.verifyASNDataIsCorrectOrNot();
	     invoicecomponentobj.closePreViewASNDetailsPopUp();
	     scrollToTopOfThePage();

	     invoicecomponentobj.clickOnViewGRNInDisputedInvoicePage1();
	     invoicecomponentobj.verifyGrnDataIsCorrectOrNot();
	     invoicecomponentobj.closePreViewGrnDetailsPopUp();
	     invoicecomponentobj.submitInTheDisputedInVoice();
	     
	     etendercomponentobj.tenderLogout();
	     invoicecomponentobj.inVoice_Approver_Login();
//	     invoicecomponentobj.navigateToInVoiceProcessingListPage();
//	     invoicecomponentobj.enterSellerInVoiceNo(eTenderComponent.getDataFromPropertiesFile("SellerInvoiceNumber"));
//	     invoicecomponentobj.verifyUniqueInvoiceNumber();
//	     etendercomponentobj.tenderLogout();
	     invoicecomponentobj.navigateToInVoiceProcessingListPage();
	     invoicecomponentobj.enterSellerInVoiceNo(eTenderComponent.getDataFromPropertiesFile("SellerInvoiceNumber"));
	     invoicecomponentobj.verifyUniqueInvoiceNumber();
	     invoicecomponentobj.saveInVoiceNumber();
	     invoicecomponentobj.clickOnInVoiceApproveBtn(eTenderComponent.getDataFromPropertiesFile("SellerInvoiceNumber"));
	     invoicecomponentobj.invoiceAmountValue();
		 invoicecomponentobj.clickInVoiceAcceptBtn();
		 invoicecomponentobj.isInVoiceProcessingListDisplayed();
	     etendercomponentobj.tenderLogout();
	     
	     invoicecomponentobj.InVoice_Payer_Login();
	     invoicecomponentobj.NavigateTOApprovedInvoicePage();
	     invoicecomponentobj.SearchWithInvoiceNum();
	     invoicecomponentobj.VerifyDataPopulatedInInvoiceListPage2();
	     invoicecomponentobj.NavigateToPaymentPage();
	     invoicecomponentobj.VerifyAmounts1();
		 invoicecomponentobj.VerifyPaymentOptions();
		 invoicecomponentobj.SearchWithInvoiceNum();
		 invoicecomponentobj.verifyPaymentDetails();
		 invoicecomponentobj.NavigateToPaymentPage();
		 invoicecomponentobj.VerifyPaymentOptions();
		 etendercomponentobj.tenderLogout();
	     
	}
}