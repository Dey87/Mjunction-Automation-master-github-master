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

public class TC_Invoice_33VerifyTheMailValidationForInvoiceapproverAfterGRNCreation extends BaseClass_Web {
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
		reportDetails.put("Test Script Revision No", "33");
		reportDetails.put("Test Author Name", "venkatesh");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}

	@Parameters("TestcaseNo")
	@Test(description = "TC_Invoice_33: VerifyTheMailValidationForInvoiceapproverAfterGRNCreation")
	public void VerifyTheMailValidationForInvoiceapproverAfterGRNCreation(String no) throws Throwable {
		System.out.println("Entered in the Test method..................");
		try {
			pdfResultReport.readTestDataFile(System.getProperty("user.dir").replace("\\", "/")
					+ "/Resources/TenderCreation_CreateNewRFQ_TestData_venkateshNew.xls", no);
		} catch (Exception e) {
			System.out.println("Unable to read the data from excel file");
		}
		initializeRepository();
		
		etendercomponentobj.openURL();
		asn_grncomponentobj.GRN_Creator_Login();
		asn_grncomponentobj.navigateToApprovedASNListPage();
		asn_grncomponentobj.enterASNShipmentAndSelectVendorName("TCS",
				eTenderComponent.getDataFromPropertiesFile("ASNShippingTracking"));
		asn_grncomponentobj
				.clickApprovedAsnListActionMenu(eTenderComponent.getDataFromPropertiesFile("ASNShippingTracking"));

		asn_grncomponentobj.createGrn(eTenderComponent.getDataFromPropertiesFile("ASNShippingTracking"));
		
		asn_grncomponentobj.saveGrnDetails();
		
		asn_grncomponentobj.saveGRNId();

		asn_grncomponentobj.submitGrnDetails();
		
		asn_grncomponentobj.clickGrnSubmitComfirmationYes();

		asn_grncomponentobj.navigateToGrnListPage();
		
		asn_grncomponentobj.clickOnGRNCreatedTab();
		
		asn_grncomponentobj.enterShippementTrackingNoInGrnListSerachBox();

		asn_grncomponentobj.verifyGrnCreatedStatus(eTenderComponent.getDataFromPropertiesFile("GRNID"));
		
		etendercomponentobj.tenderLogout();
		
		int numberOfMessages = emailyUtils.getNumberOfMessages();
		
		System.out.println("number Of mails  count   ----->  "+numberOfMessages);
		
		invoicecomponentobj.verifyMailSubjectAndMailBody("e-PROC SYSTEM: INVOICE NO: ", numberOfMessages, "Dear User", "e-PROC SYSTEM TEAM",eTenderComponent.getDataFromPropertiesFile("SellerInvoiceNumber"),"TEST_AUTOMATION is ready for payment.");
		
		//invoicecomponentobj.verifyMailSubjectAndMailBody("e-PROC SYSTEM: INVOICE NO: ", numberOfMessages, "Dear User", "e-PROC SYSTEM TEAM","2392","TEST_AUTOMATION is ready for payment.");
		
	}

}
