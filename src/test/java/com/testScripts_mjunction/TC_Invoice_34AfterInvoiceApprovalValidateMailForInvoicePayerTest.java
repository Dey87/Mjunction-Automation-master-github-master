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

public class TC_Invoice_34AfterInvoiceApprovalValidateMailForInvoicePayerTest extends BaseClass_Web {
	
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
		reportDetails.put("Test Script Revision No", "34");
		reportDetails.put("Test Author Name", "venkatesh");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}

	@Parameters("TestcaseNo")
	@Test(description = "TC_Invoice_34: AfterInvoiceApprovalValidateNotificationMailForInvoicePayerTest")
	public void verifyAfterInvoiceApprovalValidateMailNotificationForInvoicePayer(String no) throws Throwable {
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
		invoicecomponentobj.clickInVoiceAcceptBtn();
		invoicecomponentobj.isInVoiceProcessingListDisplayed();
		etendercomponentobj.tenderLogout();
		
        int numberOfMessages = emailyUtils.getNumberOfMessages();
		
        String InVoiceBodyContent ="TEST_AUTOMATION has been approved by INVOICE PAYER test and now is ready for payment.";
		System.out.println("number Of mails  count   ----->  "+numberOfMessages);
		
		invoicecomponentobj.verifyMailSubjectAndMailBody("e-PROC SYSTEM: INVOICE NO: ", numberOfMessages, "Dear User", "e-PROC SYSTEM TEAM",eTenderComponent.getDataFromPropertiesFile("SellerInvoiceNumber"),InVoiceBodyContent.trim());
		
		//invoicecomponentobj.verifyMailSubjectAndMailBody("e-PROC SYSTEM: INVOICE NO: ", numberOfMessages, "Dear User","e-PROC SYSTEM TEAM","2485", InVoiceBodyContent.trim());
		

	}


}
