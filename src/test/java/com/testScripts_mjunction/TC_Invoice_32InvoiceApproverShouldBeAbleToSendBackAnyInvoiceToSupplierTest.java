package com.testScripts_mjunction;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.baseClasses.EmailUtils;
import com.components.ASN_GRNComponent;
import com.components.InvoiceComponent;
import com.components.PostTenderComponent;
import com.components.PreConditionPo_SanctionCreation;
import com.components.eTenderComponent;
import com.objectRepository.TenderCreation_Locators;

public class TC_Invoice_32InvoiceApproverShouldBeAbleToSendBackAnyInvoiceToSupplierTest extends BaseClass_Web {

	PreConditionPo_SanctionCreation preConditionPo = new PreConditionPo_SanctionCreation(pdfResultReport);
	public eTenderComponent etendercomponentobj = new eTenderComponent(pdfResultReport);
	public TenderCreation_Locators tendercreationobj = new TenderCreation_Locators();
	public PostTenderComponent posttendercomponentobj = new PostTenderComponent(pdfResultReport);
	public ASN_GRNComponent asn_grncomponentobj = new ASN_GRNComponent(pdfResultReport);

	public InvoiceComponent invoicecomponentobj = new InvoiceComponent(pdfResultReport);

	public EmailUtils emailyUtils = new EmailUtils();

	public void initializeRepository() throws Exception {
		reportDetails.put("Test Script Name", this.getClass().getSimpleName());
		reportDetails.put("Test Script MyWorksshop Document ID", "Doc1234567");
		reportDetails.put("Test Script Revision No", "32");
		reportDetails.put("Test Author Name", "venkatesh");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}

	@Parameters("TestcaseNo")
	@Test(description = "TC_Invoice_32: InvoiceApproverShouldBeAbleToSendBackAnyInvoiceToSupplierTest")
	public void verifyInvoiceApproverShouldBeAbleToSendBackAnyInvoiceToSupplier(String no) throws Throwable {
		System.out.println("Entered in the Test method..................");
		try {
			pdfResultReport.readTestDataFile(System.getProperty("user.dir").replace("\\", "/")
					+ "/Resources/TenderCreation_CreateNewRFQ_TestData_venkateshNew.xls", no);
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
		invoicecomponentobj.clickOnInVoiceApproveBtn(eTenderComponent.getDataFromPropertiesFile("SellerInvoiceNumber"));
		invoicecomponentobj.clickOnSendBackToSupplier();
		invoicecomponentobj.isInVoiceProcessingListDisplayed();
		etendercomponentobj.tenderLogout();

		etendercomponentobj.bidder_02_Login();
		invoicecomponentobj.navigatingToDisputedInvoiceList();
		invoicecomponentobj.verifyPONumber();
		invoicecomponentobj.clickOnInVoiceNumberHeaderBar();
		invoicecomponentobj.verifyDisputedStatusIsPresent();
		invoicecomponentobj.clickAlterInvoiceTabAndValiate();
		invoicecomponentobj.clickItemDetailsTab();
		invoicecomponentobj.submitTheDisputedInVoice();
		invoicecomponentobj.isDisputedInVoicePaymentListPageDisplayed();
		etendercomponentobj.tenderLogout();

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
	}

}
