package com.testScripts_mjunction;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.components.RfqFromIndentComponent;
import com.components.eTenderComponent;

public class TC_RFQ_from_Indent_006_CreateRFQFromIndentTest extends BaseClass_Web {

	public RfqFromIndentComponent rfComponentobj = new RfqFromIndentComponent(pdfResultReport);
	public eTenderComponent etendercomponentobj = new eTenderComponent(pdfResultReport);

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
	@Test(description = "TC_RFQ_from_Indent_006_CreateRFQFromIndentTest")
	public void verfiyRFQCreationFromIndent(String no) throws Throwable {
		System.out.println("Entered in the Test method..................");
		try {
			pdfResultReport.readTestDataFile(System.getProperty("user.dir").replace("\\", "/")
					+ "/Resources/TenderCreation_CreateNewRFQ_TestData_venkateshNew.xls", no);
		} catch (Exception e) {
			System.out.println("Unable to read the data from excel file");
		}

		initializeRepository();

		etendercomponentobj.openURL();

		etendercomponentobj.tendercreatorLogin();

		rfComponentobj.navigateToCreateRFQFromIndentPage();

		rfComponentobj.searchTheIntendNo(eTenderComponent.getDataFromPropertiesFile("SystemIndentNo"));

		rfComponentobj.clickCreateRFQBtnInIndentPreviewPopUp();

		rfComponentobj.selectTemplateGroupAndSubmit("With Optional Items & Qty Editable by Supplier_V2");

		rfComponentobj.Tender_WithOptionalItemsAndQtyEditableGeneralInfo();

		etendercomponentobj.Tender_WithOptionalItemsAndQtyEditableOtherAttachments();

		etendercomponentobj.Tender_WithOptionalItemsAndQtyEditableProjectTab();

		etendercomponentobj.Tender_WithOptionalItemsAndQtyEditableAttachmentsTab();

		etendercomponentobj.Tender_WithOptionalItemsAndQtyEditableDateSchedule(9, 15, 20);

		rfComponentobj.Tender_WithOptionalItemsAndQtyEditableBOQOptional(0,1);

		etendercomponentobj.Tender_WithOptionalItemsAndQtyEditableTermsAndCondition(3);

		etendercomponentobj.Tender_WithOptionalItemsAndQtyEditableTechnical(3);

		rfComponentobj.Tender_WithOptionalItemsAndQtyEditableBOQMandatortTab(2, 3);

		etendercomponentobj.clickSubmitBtn();

		etendercomponentobj.tenderIdSave();

		etendercomponentobj.sendForNoApproval_validation();

		etendercomponentobj.enterTenderIdInSearch();

		etendercomponentobj.checkTenderStatusAndTenderStage();

		etendercomponentobj.tenderLogout();
	}

}
