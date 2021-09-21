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

public class TC_Invoice19CheckFunctionalityOfZeroValueItem extends BaseClass_Web{
	PreConditionPo_SanctionCreation preConditionPo = new PreConditionPo_SanctionCreation(pdfResultReport);
	public eTenderComponent etendercomponentobj =new eTenderComponent(pdfResultReport);
	public TenderCreation_Locators tendercreationobj =new TenderCreation_Locators(); 
	public PostTenderComponent posttendercomponentobj =new PostTenderComponent(pdfResultReport);
    public ASN_GRNComponent asn_grncomponentobj=new ASN_GRNComponent(pdfResultReport);
    public InvoiceComponent invoicecomponentobj=new InvoiceComponent(pdfResultReport);
	
	public void initializeRepository() throws Exception {		
		reportDetails.put("Test Script Name", this.getClass().getSimpleName());
		reportDetails.put("Test Script MyWorksshop Document ID", "Doc1234567");
		reportDetails.put("Test Script Revision No", "19");
		reportDetails.put("Test Author Name", "Vamshi");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}
	@Parameters("TestcaseNo")
	@Test(description = "TC_Invoice_19: Check Functionality of Zero value item are working properly or not")
  public void validateAlertEventAlreadyExistedAndAuctionRuleInEventColumnTest(String no) throws Throwable {
	  System.out.println("Entered in the Test method..................");
	  try {
		pdfResultReport.readTestDataFile(System.getProperty("user.dir").replace("\\", "/")	+ "/Resources/TenderCreation_CreateNewRFQ_TestData_Vamshi.xls", no);
	} catch (Exception e) {
		System.out.println("Unable to read the data from excel file");
	}
	     initializeRepository();
	     etendercomponentobj.openURL();
	     etendercomponentobj.bidder_02_Login();
	     invoicecomponentobj.navigatingToDisputedInvoiceList();
	     invoicecomponentobj.verifyPONumber();
	     invoicecomponentobj.clickAlterInvoiceTabAndValiate();
	     invoicecomponentobj.clickItemDetailsAndValiateItemCodesForExcessQuantity();
	     //invoicecomponentobj.validateViewPODetailsInDisputeInvoiceItemCode();
	     invoicecomponentobj.ZeroValueItemValidation();
	     invoicecomponentobj.addingNewRowToValidateZeroValueItemOption();
	     invoicecomponentobj.submitInTheDisputedInVoice();
	     etendercomponentobj.tenderLogout();
	     
	     invoicecomponentobj.inVoice_Approver_Login();
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