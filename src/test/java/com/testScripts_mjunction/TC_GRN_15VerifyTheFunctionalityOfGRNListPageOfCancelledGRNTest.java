package com.testScripts_mjunction;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.components.ASN_GRNComponent;
import com.components.PostTenderComponent;
import com.components.PreConditionPo_SanctionCreation;
import com.components.eTenderComponent;
import com.objectRepository.TenderCreation_Locators;

public class TC_GRN_15VerifyTheFunctionalityOfGRNListPageOfCancelledGRNTest extends BaseClass_Web {
	PreConditionPo_SanctionCreation preConditionPo = new PreConditionPo_SanctionCreation(pdfResultReport);
	public eTenderComponent etendercomponentobj = new eTenderComponent(pdfResultReport);
	public TenderCreation_Locators tendercreationobj = new TenderCreation_Locators();
	public PostTenderComponent posttendercomponentobj = new PostTenderComponent(pdfResultReport);
	public ASN_GRNComponent asn_grncomponentobj = new ASN_GRNComponent(pdfResultReport);

	public void initializeRepository() throws Exception {
		reportDetails.put("Test Script Name", this.getClass().getSimpleName());
		reportDetails.put("Test Script MyWorksshop Document ID", "Doc1234567");
		reportDetails.put("Test Script Revision No", "15");
		reportDetails.put("Test Author Name", "venkatesh");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}

	@Parameters("TestcaseNo")
	@Test(description = "TC_GRN_15: VerifyTheFunctionalityOfGRNListPageOfCancelledGRNTest")
	public void VerifyTheFunctionalityOfGRNListPageOfCancelledGRNTest(String no) throws Throwable {
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

		asn_grncomponentobj.coloumnVisibilityIconValidation();

		asn_grncomponentobj
				.clickApprovedAsnListActionMenu(eTenderComponent.getDataFromPropertiesFile("ASNShippingTracking"));

		asn_grncomponentobj.VerifyViewAndCreateGRNIsDisplayedValidation(
				eTenderComponent.getDataFromPropertiesFile("ASNShippingTracking"));

		asn_grncomponentobj.createGrn(eTenderComponent.getDataFromPropertiesFile("ASNShippingTracking"));
		asn_grncomponentobj.verifyAsnQtyValueInGrnCreationPage(1);
		asn_grncomponentobj.enterRejectedQty(1, "2.00");
		asn_grncomponentobj.enterExcessQty(1, "2.00");
		asn_grncomponentobj.enterDamageQty(1, "1.00");

		asn_grncomponentobj.getAcceptedQtyValueAndVerify(1, "14.00");

		asn_grncomponentobj.verifyAsnQtyValueInGrnCreationPage(2);
		asn_grncomponentobj.enterRejectedQty(2, "2.00");
		asn_grncomponentobj.enterExcessQty(2, "2.00");
		asn_grncomponentobj.enterDamageQty(2, "1.00");
		asn_grncomponentobj.getAcceptedQtyValueAndVerify(2, "14.00");

		asn_grncomponentobj.verifyAsnQtyValueInGrnCreationPage(3);
		asn_grncomponentobj.enterRejectedQty(3, "2.00");
		asn_grncomponentobj.enterExcessQty(3, "2.00");
		asn_grncomponentobj.enterDamageQty(3, "1.00");
		asn_grncomponentobj.getAcceptedQtyValueAndVerify(3, "14.00");

		asn_grncomponentobj.saveGrnDetails();

		asn_grncomponentobj.submitGrnDetails();
		asn_grncomponentobj.clickGrnSubmitComfirmationNo();

		asn_grncomponentobj.enterRejectedQty(1, "2.00");

		asn_grncomponentobj.saveGrnDetails();

		asn_grncomponentobj.submitGrnDetails();

		asn_grncomponentobj.clickGrnSubmitComfirmationYes();

		asn_grncomponentobj.navigateToGrnListPage();

		asn_grncomponentobj.clickOnGRNCreatedTab();

		asn_grncomponentobj.enterShippementTrackingNoInGrnListSerachBox();

		asn_grncomponentobj.clickGrnListActionMenu(eTenderComponent.getDataFromPropertiesFile("ASNShippingTracking"));

		asn_grncomponentobj.validateActionMenuElementsInGrnListPage(
				eTenderComponent.getDataFromPropertiesFile("ASNShippingTracking"));

		asn_grncomponentobj
				.clickViewGrnDetailsInActionMenu(eTenderComponent.getDataFromPropertiesFile("ASNShippingTracking"));

		asn_grncomponentobj.verifyViewGrnSavedDetailsInPopUp("Item Code_1", "2.00", "2.00", "0.00", "1.00");

		asn_grncomponentobj.verifyViewGrnSavedDetailsInPopUp("Item Code_2", "2.00", "2.00", "0.00", "1.00");

		asn_grncomponentobj.verifyViewGrnSavedDetailsInPopUp("Item Code_3", "2.00", "2.00", "0.00", "1.00");

		asn_grncomponentobj.closePreviewwGrnDetailsPopUp();

		asn_grncomponentobj.clickGrnListActionMenu(eTenderComponent.getDataFromPropertiesFile("ASNShippingTracking"));

		asn_grncomponentobj.clickViewShipmentDetailsInActionMenu(
				eTenderComponent.getDataFromPropertiesFile("ASNShippingTracking"));

		asn_grncomponentobj.VerifyDetailsInShipmetDetailsPage();

		asn_grncomponentobj.closeAsnModalPreViewDetailsPopUp();

		scrollToTopOfThePage();
		
		waitForObj(5000);
		
		asn_grncomponentobj.clickGrnListActionMenu(eTenderComponent.getDataFromPropertiesFile("ASNShippingTracking"));

		asn_grncomponentobj
				.clickCancelGrnInActonMenu(eTenderComponent.getDataFromPropertiesFile("ASNShippingTracking"));

		asn_grncomponentobj.clickOnGRNCancelTab();

		asn_grncomponentobj.enterShippementTrackingNoInGrnListSerachBox();

		asn_grncomponentobj.clickGrnListActionMenu(eTenderComponent.getDataFromPropertiesFile("ASNShippingTracking"));

		asn_grncomponentobj.validateActionMenuElementsInGrnListPageCancelledTab(
				eTenderComponent.getDataFromPropertiesFile("ASNShippingTracking"));

		asn_grncomponentobj
				.clickViewGrnDetailsInActionMenu(eTenderComponent.getDataFromPropertiesFile("ASNShippingTracking"));

		asn_grncomponentobj.verifyViewGrnSavedDetailsInPopUp("Item Code_1", "2.00", "2.00", "0.00", "1.00");

		asn_grncomponentobj.verifyViewGrnSavedDetailsInPopUp("Item Code_2", "2.00", "2.00", "0.00", "1.00");

		asn_grncomponentobj.verifyViewGrnSavedDetailsInPopUp("Item Code_3", "2.00", "2.00", "0.00", "1.00");

		asn_grncomponentobj.closePreviewwGrnDetailsPopUp();

		asn_grncomponentobj.clickGrnListActionMenu(eTenderComponent.getDataFromPropertiesFile("ASNShippingTracking"));

		asn_grncomponentobj.clickViewShipmentDetailsInActionMenu(
				eTenderComponent.getDataFromPropertiesFile("ASNShippingTracking"));

		asn_grncomponentobj.VerifyDetailsInShipmetDetailsPage();

		asn_grncomponentobj.closeAsnModalPreViewDetailsPopUp();

		scrollToTopOfThePage();

		etendercomponentobj.tenderLogout();

	}

}
