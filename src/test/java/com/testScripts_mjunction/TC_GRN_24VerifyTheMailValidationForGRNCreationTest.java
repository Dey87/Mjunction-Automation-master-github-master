package com.testScripts_mjunction;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.baseClasses.EmailUtils;
import com.components.ASN_GRNComponent;
import com.components.PostTenderComponent;
import com.components.PreConditionPo_SanctionCreation;
import com.components.eTenderComponent;
import com.objectRepository.TenderCreation_Locators;

public class TC_GRN_24VerifyTheMailValidationForGRNCreationTest extends BaseClass_Web {
	PreConditionPo_SanctionCreation preConditionPo = new PreConditionPo_SanctionCreation(pdfResultReport);
	public eTenderComponent etendercomponentobj = new eTenderComponent(pdfResultReport);
	public TenderCreation_Locators tendercreationobj = new TenderCreation_Locators();
	public PostTenderComponent posttendercomponentobj = new PostTenderComponent(pdfResultReport);
	public ASN_GRNComponent asn_grncomponentobj = new ASN_GRNComponent(pdfResultReport);
	public EmailUtils emailyUtils = new EmailUtils();

	public void initializeRepository() throws Exception {
		reportDetails.put("Test Script Name", this.getClass().getSimpleName());
		reportDetails.put("Test Script MyWorksshop Document ID", "Doc1234567");
		reportDetails.put("Test Script Revision No", "24");
		reportDetails.put("Test Author Name", "venkatesh");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}

	@Parameters("TestcaseNo")
	@Test(description = "TC_GRN_24: VerifyTheMailValidationForGRNCreationTest")
	public void verifyGRNCreationNotificationMail(String no) throws Throwable {
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
		
		asn_grncomponentobj.verifyMailSubjectAndMailBody("TEST_AUTOMATION e-PROC SYSTEM: GRN ID.", numberOfMessages, "This is to inform you that content", "e-PROC SYSTEM TEAM",eTenderComponent.getDataFromPropertiesFile("GRNID"),eTenderComponent.getDataFromPropertiesFile("ASNShippingTracking"));
		
		//asn_grncomponentobj.verifyMailSubjectAndMailBody("TEST_AUTOMATION e-PROC SYSTEM: GRN ID.", 10, "This is to inform you that content", "e-PROC SYSTEM TEAM", "TEST_AUTOMATION e-PROC SYSTEM: GRN ID."+eTenderComponent.getDataFromPropertiesFile("GRNID")+" "+" has been created",eTenderComponent.getDataFromPropertiesFile("ASNShippingTracking") );
	}

}
