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

public class TC_Invoice_21CheckASNDataCorrectOrNotDuringAlteringInvoice extends BaseClass_Web {

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
		reportDetails.put("Test Script Revision No", "21");
		reportDetails.put("Test Author Name", "pooja");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}

	@Parameters("TestcaseNo")
	@Test(description = "TC_Invoice_21:Check ASN data are correct or not during altering Invoice")
      public void CheckASNdataCorrectOrNotDuringAlteringInvoiceTest(String no) throws Throwable {
	  System.out.println("Entered in the Test method..................");
	  try {
		pdfResultReport.readTestDataFile(System.getProperty("user.dir").replace("\\", "/")	+ "/Resources/TenderCreation_CreateNewRFQ_TestData_pooja.xls", no);
	       } catch (Exception e) {
	      	System.out.println("Unable to read the data from excel file");
	      }
		initializeRepository();
		 etendercomponentobj.openURL();
	     etendercomponentobj.bidder_02_Login();
	     invoicecomponentobj.navigatingToDisputedInvoiceList();
	     invoicecomponentobj.verifyPONumber();
	     invoicecomponentobj.clickAlterInvoiceTabAndValiate();
	     invoicecomponentobj.clickItemDetailsAndValiateItemCodes();
	     invoicecomponentobj.clickOnViewASNInDisputedInvoicePage();
	     invoicecomponentobj.verifyASNDataIsCorrectOrNot();
	     invoicecomponentobj.closePreViewASNDetailsPopUp();
	     scrollToTopOfThePage();
	     invoicecomponentobj.submitTheDisputedInVoice();
	     invoicecomponentobj.isDisputedInVoicePaymentListPageDisplayed();
	     etendercomponentobj.tenderLogout();
	     
	     invoicecomponentobj.inVoice_Approver_Login();
	     invoicecomponentobj.navigateToInVoiceProcessingListPage();
	     invoicecomponentobj.enterSellerInVoiceNo(eTenderComponent.getDataFromPropertiesFile("SellerInvoiceNumber"));
	     invoicecomponentobj.verifyUniqueInvoiceNumber();
	     etendercomponentobj.tenderLogout();
		
	}
}
