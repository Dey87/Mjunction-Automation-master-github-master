package com.testScripts_mjunction;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.baseClasses.ThreadLocalWebdriver;
import com.components.ASN_GRNComponent;
import com.components.InvoiceComponent;
import com.components.PostTenderComponent;
import com.components.PreConditionPo_SanctionCreation;
import com.components.eTenderComponent;

public class TC_Invoice_26BidderCreateInvoiceFromGRNInOtherInvoice extends BaseClass_Web {

	public eTenderComponent etendercomponentobj =new eTenderComponent(pdfResultReport);
	public PostTenderComponent posttendercomponentobj =new PostTenderComponent(pdfResultReport);
	 public InvoiceComponent invoicecomponentobj=new InvoiceComponent(pdfResultReport);
	 public ASN_GRNComponent asn_grncomponentobj=new ASN_GRNComponent(pdfResultReport);
	/**
	 * TestScript Environment Details
	 * 
	 * @throws Exception
	 */
	public void initializeRepository() throws Exception {
		reportDetails.put("Test Script Name", this.getClass().getSimpleName());
		reportDetails.put("Test Script MyWorksshop Document ID", "Doc1234567");
		reportDetails.put("Test Script Revision No", "26");
		reportDetails.put("Test Author Name", "pooja");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}

	@Parameters("TestcaseNo")
	@Test(description = "TC_Invoice_26:BidderShouldBeAbleToCreateInvoiceFromGRNPopulatedOnOtherInvoice")
      public void validateAlertEventAlreadyExistedAndAuctionRuleInEventColumnTest(String no) throws Throwable {
	  System.out.println("Entered in the Test method..................");
	  try {
		pdfResultReport.readTestDataFile(System.getProperty("user.dir").replace("\\", "/")	+ "/Resources/TenderCreation_CreateNewRFQ_TestData_pooja.xls", no);
	       } catch (Exception e) {
	      	System.out.println("Unable to read the data from excel file");
	      }
		initializeRepository();
		etendercomponentobj.openURL();
	    etendercomponentobj.bidder_02_Login();
	    invoicecomponentobj.clickOnGRListToRaiseInvoice();
	    invoicecomponentobj.searchPONoInOtherInvoiceListPage();
	    invoicecomponentobj.clickOnPONumberHeaderPlusIcon(eTenderComponent.getDataFromPropertiesFile("ASNShippingTracking"));
	    invoicecomponentobj.clickOnRaiseInvoiceIcon();
	    invoicecomponentobj.invoiceTabValidation();
	    invoicecomponentobj.itemDetailsTabValidation();
	    invoicecomponentobj.clickOnSubmitInRaiseInvoicePage();
	    etendercomponentobj.tenderLogout();
	    
	    //TC--8
		invoicecomponentobj.inVoice_Approver_Login();
		invoicecomponentobj.navigateToInVoiceProcessingListPage();
	    invoicecomponentobj.enterSellerInVoiceNo(eTenderComponent.getDataFromPropertiesFile("SellerInvoiceNumber"));
	    invoicecomponentobj.isPoNumberWithHeaderDispalying(eTenderComponent.getDataFromPropertiesFile("poDocNum"));
	    invoicecomponentobj.clickOnPoNumberHeaderBar(eTenderComponent.getDataFromPropertiesFile("poDocNum"));
	    invoicecomponentobj.saveInVoiceNumber();
	    invoicecomponentobj.clickOnInVoiceNumberHeaderBar();
	    invoicecomponentobj.verifyEyeIconToViewASN();
	    invoicecomponentobj.clickOnViewASNInInvoiceProcessingListPage();
	    asn_grncomponentobj.closeAsnModalPreViewDetailsPopUp();
	    scrollToTopOfThePage();
	    invoicecomponentobj.clickOnInVoiceApproveBtn(eTenderComponent.getDataFromPropertiesFile("SellerInvoiceNumber"));
	    invoicecomponentobj.enterApprovedAmount("43015.00");
	    invoicecomponentobj.enterpenaltyDeductionAmount("2500.00");
	    invoicecomponentobj.clickInVoiceAcceptBtn();
	    invoicecomponentobj.validateApproved_PenaltyAmountCantMoreThanTotalAmount("Approved Amount and Penalty Deduction Amount can't be more than total amount.");
	    invoicecomponentobj.clickinVoiceErrorMsgOkBtn();
	    scrollToTopOfThePage();
		etendercomponentobj.tenderLogout();
		
		//TC-09
		invoicecomponentobj.inVoice_Approver_Login();
		invoicecomponentobj.navigateToInVoiceProcessingListPage();
	    invoicecomponentobj.enterSellerInVoiceNo(eTenderComponent.getDataFromPropertiesFile("SellerInvoiceNumber"));
	    invoicecomponentobj.isPoNumberWithHeaderDispalying(eTenderComponent.getDataFromPropertiesFile("poDocNum"));
	    invoicecomponentobj.clickOnPoNumberHeaderBar(eTenderComponent.getDataFromPropertiesFile("poDocNum"));
	    invoicecomponentobj.saveInVoiceNumber();
	    invoicecomponentobj.clickOnInVoiceNumberHeaderBar();
	    invoicecomponentobj.clickOnInVoiceApproveBtn(eTenderComponent.getDataFromPropertiesFile("SellerInvoiceNumber"));
	    invoicecomponentobj.clickInVoiceAcceptBtn();
	    invoicecomponentobj.isInVoiceProcessingListDisplayed();
		etendercomponentobj.tenderLogout();
		
		invoicecomponentobj.InVoice_Payer_Login();
		invoicecomponentobj.NavigateTOApprovedInvoicePage();
		invoicecomponentobj.SearchWithInvoiceNum();
		etendercomponentobj.tenderLogout();
		
		//TC-10
		invoicecomponentobj.InVoice_Payer_Login();
		invoicecomponentobj.NavigateTOApprovedInvoicePage();
		invoicecomponentobj.SearchWithInvoiceNum();
		invoicecomponentobj.verifyPOInInvoiceListForPayment();
		etendercomponentobj.tenderLogout();
		
		//TC-11
		invoicecomponentobj.InVoice_Payer_Login();
		invoicecomponentobj.NavigateTOApprovedInvoicePage();
		invoicecomponentobj.SearchWithInvoiceNum();
		invoicecomponentobj.VerifyASNinInvoicePage();
		etendercomponentobj.tenderLogout();
		
		//TC-12
		invoicecomponentobj.InVoice_Payer_Login();
		invoicecomponentobj.NavigateTOApprovedInvoicePage();
		invoicecomponentobj.SearchWithInvoiceNum();
		invoicecomponentobj.verifyGRNInInvoiceListForPayment();
		etendercomponentobj.tenderLogout();
		
		//TC-13
		invoicecomponentobj.InVoice_Payer_Login();
		invoicecomponentobj.NavigateTOApprovedInvoicePage();
		invoicecomponentobj.SearchWithInvoiceNum();
		invoicecomponentobj.VerifyDataPopulatedInInvoiceListPage();
		etendercomponentobj.tenderLogout();
		
		//TC-14
		invoicecomponentobj.InVoice_Payer_Login();
		invoicecomponentobj.NavigateTOApprovedInvoicePage();
		invoicecomponentobj.SearchWithInvoiceNum();
		invoicecomponentobj.VerifyDataPopulatedInInvoiceListPage();
		invoicecomponentobj.NavigateToPaymentPage();
		invoicecomponentobj.VerifyAmountsValidation();
		invoicecomponentobj.VerifyPaymentOptions();
		invoicecomponentobj.SearchWithInvoiceNum();
		invoicecomponentobj.VerifyDataPopulatedInInvoiceListPageAfterPartialPaymentVal();
		invoicecomponentobj.VerifyShowList();
		
		//TC-15
		invoicecomponentobj.NavigateToPaymentPage();
		invoicecomponentobj.VerifyAmountsFullPay();
		invoicecomponentobj.VerifyPaymentOptions();
		etendercomponentobj.tenderLogout();
		
		
		
	}
}
