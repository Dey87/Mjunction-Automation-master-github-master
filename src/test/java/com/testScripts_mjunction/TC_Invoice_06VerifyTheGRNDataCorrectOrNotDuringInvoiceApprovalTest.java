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

public class TC_Invoice_06VerifyTheGRNDataCorrectOrNotDuringInvoiceApprovalTest extends BaseClass_Web {

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
		reportDetails.put("Test Script Revision No", "06");
		reportDetails.put("Test Author Name", "venkatesh");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}

	@Parameters("TestcaseNo")
	@Test(description = "TC_Invoice_06: VerifyTheGRNDataCorrectOrNotDuringInvoiceApprovalTest")
	public void VerifyTheGRNDataCorrectOrNotDuringInvoiceApprovalTest(String no) throws Throwable {
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
	    
	    invoicecomponentobj.verifyEyeIconToViewGRN();
	    
	    invoicecomponentobj.clickOnViewGrnIcon();
	    
	    invoicecomponentobj.verifyGrnDataIsCorrectOrNot("Item Code_3", "0.00", "0.00", "0.00", "0.00", "15", "15", "15");
	    
	    invoicecomponentobj.verifyGrnDataIsCorrectOrNot("Item Code_1", "0.00", "0.00", "0.00", "0.00", "15", "15", "15");
	    
	    invoicecomponentobj.verifyGrnDataIsCorrectOrNot("Item Code_2", "0.00", "0.00", "0.00", "0.00", "15", "15", "15");
	    
	    invoicecomponentobj.closePreviewwGrnDetailsPopUp();
	    
	    scrollToTopOfThePage();
	   
		etendercomponentobj.tenderLogout();

	}

}
