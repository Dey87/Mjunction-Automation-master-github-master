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

public class TC_Invoice_14_VerifyPartPaymentPossibleOrNot extends BaseClass_Web {

	public eTenderComponent etendercomponentobj =new eTenderComponent(pdfResultReport);
	public PostTenderComponent posttendercomponentobj =new PostTenderComponent(pdfResultReport);
	 public InvoiceComponent invoicecomponentobj=new InvoiceComponent(pdfResultReport);
	 public ASN_GRNComponent asn_grncomponentobj = new ASN_GRNComponent(pdfResultReport);
	/**
	 * TestScript Environment Details
	 * 
	 * @throws Exception
	 */
	public void initializeRepository() throws Exception {
		reportDetails.put("Test Script Name", this.getClass().getSimpleName());
		reportDetails.put("Test Script MyWorksshop Document ID", "Doc1234567");
		reportDetails.put("Test Script Revision No", "01");
		reportDetails.put("Test Author Name", "jit");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}

	@Parameters("TestcaseNo")
	@Test(description = "TC_Invoice_14_VerifyPartPaymentPossibleOrNot")
      public void VerifyPartPaymentPossibleOrNot(String no) throws Throwable {
	  System.out.println("Entered in the Test method..................");
	  try {
		pdfResultReport.readTestDataFile(System.getProperty("user.dir").replace("\\", "/")	+ "/Resources/TenderCreation_CreateNewRFQ_TestData_jit.xls", no);
	       } catch (Exception e) {
	      	System.out.println("Unable to read the data from excel file");
	      }
		initializeRepository();
		etendercomponentobj.openURL();
		invoicecomponentobj.inVoice_Approver_Login();
		invoicecomponentobj.navigateToInVoiceProcessingListPage();
	    invoicecomponentobj.enterSellerInVoiceNo(eTenderComponent.getDataFromPropertiesFile("SellerInvoiceNumber"));
	    invoicecomponentobj.isPoNumberWithHeaderDispalying(eTenderComponent.getDataFromPropertiesFile("poDocNum"));
	    invoicecomponentobj.clickOnPoNumberHeaderBar(eTenderComponent.getDataFromPropertiesFile("poDocNum"));
	    invoicecomponentobj.saveInVoiceNumber();
	    invoicecomponentobj.clickOnInVoiceNumberHeaderBar();
	    invoicecomponentobj.clickOnViewASNInInvoiceProcessingListPage();
	    asn_grncomponentobj.closeAsnModalPreViewDetailsPopUp();
	    scrollToTopOfThePage();
	    invoicecomponentobj.clickOnInVoiceApproveBtn(eTenderComponent.getDataFromPropertiesFile("SellerInvoiceNumber"));
	    invoicecomponentobj.clickInVoiceAcceptBtn();
	    invoicecomponentobj.isInVoiceProcessingListDisplayed();
		etendercomponentobj.tenderLogout();
	
		invoicecomponentobj.InVoice_Payer_Login();
		invoicecomponentobj.NavigateTOApprovedInvoicePage();
		invoicecomponentobj.SearchWithInvoiceNum();
		invoicecomponentobj.VerifyDataPopulatedInInvoiceListPage();
		invoicecomponentobj.NavigateToPaymentPage();
		invoicecomponentobj.VerifyAmounts();
		invoicecomponentobj.VerifyPaymentOptions();
		invoicecomponentobj.SearchWithInvoiceNum();
		invoicecomponentobj.VerifyDataPopulatedInInvoiceListPageAfterPartialPayment();
		invoicecomponentobj.VerifyShowList();
		etendercomponentobj.tenderLogout();
		
	}
}
