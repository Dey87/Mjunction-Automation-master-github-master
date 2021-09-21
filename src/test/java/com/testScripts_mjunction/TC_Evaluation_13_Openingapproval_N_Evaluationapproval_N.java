package com.testScripts_mjunction;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.baseClasses.ThreadLocalWebdriver;
import com.components.eTenderComponent;

public class TC_Evaluation_13_Openingapproval_N_Evaluationapproval_N extends BaseClass_Web{
	
	public eTenderComponent etendercomponentobj = new eTenderComponent(pdfResultReport);
	

	/**
	 * TestScript Environment Details
	 * 
	 * @throws Exception
	 */
	public void initializeRepository() throws Exception {
		reportDetails.put("Test Script Name", this.getClass().getSimpleName());
		reportDetails.put("Test Script MyWorksshop Document ID", "Doc1234567");
		reportDetails.put("Test Script Revision No", "1");
		reportDetails.put("Test Author Name", "Venkatesh Jujjuru");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}
	
	
	@Parameters("TestcaseNo")
	@Test(description = "Tender_Evaluation_with_OpeningApproval_N_Evaluationapproval_N")
	public void Tender_Evaluation_with_OpeningApproval_N_Evaluationapproval_N(String no) throws Throwable {
		System.out.println("Entered in the Test method..................");
		try {
			pdfResultReport.readTestDataFile(System.getProperty("user.dir").replace("\\", "/")
					+ "/Resources/Testdata_etender_module_static_scripts.xls", no);
		} catch (Exception e) {
			System.out.println("Unable to read the data from excel file");
		}

		WebDriver driver = ThreadLocalWebdriver.getDriver();
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);	
		initializeRepository();
	//Creating a new Open tender with approval type Not Required
		etendercomponentobj.openURL();
		etendercomponentobj.tendercreatorLogin();
		etendercomponentobj.createTenderWithNewRfq();
		etendercomponentobj.Tender_WithOptionalItemsAndQtyEditableGeneralInfoWithThreeBidders("With Optional Items & Qty Editable by Supplier_V2");
		etendercomponentobj.Tender_WithOptionalItemsAndQtyEditableOtherAttachments();
		etendercomponentobj.Tender_WithOptionalItemsAndQtyEditableProjectTab();
		etendercomponentobj.Tender_WithOptionalItemsAndQtyEditableAttachmentsTab();
		etendercomponentobj.Tender_WithOptionalItemsAndQtyEditableDateSchedule(9,28,29);
		etendercomponentobj.Tender_WithOptionalItemsAndQtyEditableBOQOptional();
		etendercomponentobj.Tender_WithOptionalItemsAndQtyEditableTermsAndCondition(3);
		etendercomponentobj.Tender_WithOptionalItemsAndQtyEditableTechnical(3);
		etendercomponentobj.Tender_WithOptionalItemsAndQtyEditableBOQMandatortTab(3, 1, 3, "CCM");
		etendercomponentobj.clickSubmitBtn();
		etendercomponentobj.tenderIdSave();
		etendercomponentobj.sendForNoApproval_validation();
		etendercomponentobj.enterTenderIdInSearch();
		etendercomponentobj.checkTenderStatusAndTenderStage();
		etendercomponentobj.tenderLogout();
			
	//Bid submission process for bidder1	
		etendercomponentobj.waitTillBidstartDateReached();
		etendercomponentobj.bidder_01_Login();
		etendercomponentobj.enterTenderIdInSearch_bidsubmission();
		etendercomponentobj.navigateToActionDropdown_bidsubmission();
		etendercomponentobj.newTermsAndServicesCheckBox_bidsubmission();
		etendercomponentobj.generalInformationPageValidation_bidsubmission();
		etendercomponentobj.quotationReferenceCode_bidsubmission();
		etendercomponentobj.mandatoryFieldValidation_technicalTab_bidsubmission();
		etendercomponentobj.mandatoryFieldValidation_termsAndConditionTab_bidsubmission();
		etendercomponentobj.mandatoryFieldValidation_boqOptionalTab("900", "400");
		etendercomponentobj.mandatoryFieldValidation_attachmentsTab();
		etendercomponentobj.mandatoryFieldValidation_commercialTab_bidsubmission();
		etendercomponentobj.mandatoryFieldValidation_BoqmandatoryTab(eTenderComponent.getDataFromPropertiesFile("Bidder1SupplierQty"),eTenderComponent.getDataFromPropertiesFile("Bidder1BudgetRate"));
		etendercomponentobj.mandatoryFieldValidation_submitButton_bidsubmission();
		etendercomponentobj.validateAllInPrevieAllSubmitbidPage();
		etendercomponentobj.submitBid_link_in_previewAllPage();
		etendercomponentobj.navigate_to_bidList_page();
		etendercomponentobj.enterTenderIdInSearch_bidsubmission();
		etendercomponentobj.submittedBid_Tab_Validation();
		//etendercomponentobj.validateTenderpreviewInBidListPage();
		//etendercomponentobj.validateBidPreview_BidListPage();
		etendercomponentobj.tenderLogout();
		
	//Bid submission process for bidder2		
		etendercomponentobj.bidder_02_Login();
		etendercomponentobj.enterTenderIdInSearch_bidsubmission();
		etendercomponentobj.navigateToActionDropdown_bidsubmission();
		etendercomponentobj.newTermsAndServicesCheckBox_bidsubmission();
		etendercomponentobj.generalInformationPageValidation_bidsubmission();
		etendercomponentobj.quotationReferenceCode_bidsubmission();
		etendercomponentobj.mandatoryFieldValidation_technicalTab_bidsubmission();
		etendercomponentobj.mandatoryFieldValidation_termsAndConditionTab_bidsubmission();
		etendercomponentobj.mandatoryFieldValidation_boqOptionalTab("800", "400");
		etendercomponentobj.mandatoryFieldValidation_attachmentsTab();
		etendercomponentobj.mandatoryFieldValidation_commercialTab_bidsubmission();
		etendercomponentobj.mandatoryFieldValidation_BoqmandatoryTab(eTenderComponent.getDataFromPropertiesFile("Bidder2SupplierQty"),eTenderComponent.getDataFromPropertiesFile("Bidder2BudgetRate"));
		etendercomponentobj.mandatoryFieldValidation_submitButton_bidsubmission();
		etendercomponentobj.validateAllInPrevieAllSubmitbidPage();
		etendercomponentobj.submitBid_link_in_previewAllPage();
		etendercomponentobj.navigate_to_bidList_page();
		etendercomponentobj.enterTenderIdInSearch_bidsubmission();
		etendercomponentobj.submittedBid_Tab_Validation();
		//etendercomponentobj.validateTenderpreviewInBidListPage();
		//etendercomponentobj.validateBidPreview_BidListPage();
		etendercomponentobj.tenderLogout();
		
	//Bid submission process for bidder3		
		etendercomponentobj.bidder_03_Login();
		etendercomponentobj.enterTenderIdInSearch_bidsubmission();
		etendercomponentobj.navigateToActionDropdown_bidsubmission();
		etendercomponentobj.newTermsAndServicesCheckBox_bidsubmission();
		etendercomponentobj.generalInformationPageValidation_bidsubmission();
		etendercomponentobj.quotationReferenceCode_bidsubmission();
		etendercomponentobj.mandatoryFieldValidation_technicalTab_bidsubmission();
		etendercomponentobj.mandatoryFieldValidation_termsAndConditionTab_bidsubmission();
		etendercomponentobj.mandatoryFieldValidation_boqOptionalTab("800", "600");
		etendercomponentobj.mandatoryFieldValidation_attachmentsTab();
		etendercomponentobj.mandatoryFieldValidation_commercialTab_bidsubmission();
		etendercomponentobj.mandatoryFieldValidation_BoqmandatoryTab(eTenderComponent.getDataFromPropertiesFile("Bidder3SupplierQty"),eTenderComponent.getDataFromPropertiesFile("Bidder3BudgetRate"));
		etendercomponentobj.mandatoryFieldValidation_submitButton_bidsubmission();
		etendercomponentobj.validateAllInPrevieAllSubmitbidPage();
		etendercomponentobj.submitBid_link_in_previewAllPage();
		etendercomponentobj.navigate_to_bidList_page();
		etendercomponentobj.enterTenderIdInSearch_bidsubmission();
		etendercomponentobj.submittedBid_Tab_Validation();
		//etendercomponentobj.validateTenderpreviewInBidListPage();
		//etendercomponentobj.validateBidPreview_BidListPage();
		etendercomponentobj.tenderLogout();

	//Evaluation: Cover 1 work flow
		etendercomponentobj.tendercreatorLogin();
		etendercomponentobj.waitTillBidDuetDateReached();
		etendercomponentobj.waitTillBidOpentDateReached();
		etendercomponentobj.navigateToTenderListing();
		etendercomponentobj.enterTenderIdInSearch();
		etendercomponentobj.clickEvaluationSettingsLink();
		etendercomponentobj.SendForApprovalInEvaluationsetting();
		etendercomponentobj.enterTenderIdInSearch();
		etendercomponentobj.clickPendingForEvaluationApprovalStage();
		etendercomponentobj.decryptingTheBidder();
		etendercomponentobj.clickBidDetailsSectionForchoosenBidder("CTS_AUTO_01");
		etendercomponentobj.validateBidDetailsForCover1_ForNoApprovalType();
		etendercomponentobj.clickEditableLinkPendingStatusAndSelectApproveForTheBidder("CTS_AUTO_01",
				"CTS test (CTS_AUTO_01) is successfully Approved");
		etendercomponentobj.clickBidDetailsSectionForchoosenBidder("TCS_AUTO_01");
		etendercomponentobj.validateBidDetailsForCover1_ForNoApprovalType();
		etendercomponentobj.clickEditableLinkPendingStatusAndSelectApproveForTheBidder("TCS_AUTO_01",
				"TCS test (TCS_AUTO_01) is successfully Approved");
		etendercomponentobj.clickBidDetailsSectionForchoosenBidder("TECH_AUTO_01");
		etendercomponentobj.validateBidDetailsForCover1_ForNoApprovalType();
		etendercomponentobj.clickEditableLinkPendingStatusAndSelectApproveForTheBidder("TECH_AUTO_01",
				"Tech Mahindra test (TECH_AUTO_01) is successfully Approved");
		etendercomponentobj.enterOverallComment();

	//Evaluation: Cover 2 work flow
		etendercomponentobj.enterTenderIdInSearch();
		etendercomponentobj.checktenderStatusAndTenderStage();
		etendercomponentobj.clickEvaluationSettingsLink();
		etendercomponentobj.SendForApprovalInEvaluationsetting();
        etendercomponentobj.enterTenderIdInSearch();
		etendercomponentobj.clickPendingForEvaluationApprovalStage();
		etendercomponentobj.decryptingTheBidder();
		etendercomponentobj.clickBidDetailsSectionForchoosenBidder("CTS_AUTO_01");
		etendercomponentobj.validateBidDetailsForCover2_ForNoApprovalType();
		etendercomponentobj.clickEditableLinkPendingStatusAndSelectApproveForTheBidder("CTS_AUTO_01",
				"CTS test (CTS_AUTO_01) is successfully Approved");
		etendercomponentobj.clickBidDetailsSectionForchoosenBidder("TCS_AUTO_01");
		etendercomponentobj.validateBidDetailsForCover2_ForNoApprovalType();
		etendercomponentobj.clickEditableLinkPendingStatusAndSelectApproveForTheBidder("TCS_AUTO_01",
				"TCS test (TCS_AUTO_01) is successfully Approved");
		etendercomponentobj.clickBidDetailsSectionForchoosenBidder("TECH_AUTO_01");
		etendercomponentobj.validateBidDetailsForCover2_ForNoApprovalType();
		etendercomponentobj.clickEditableLinkPendingStatusAndSelectApproveForTheBidder("TECH_AUTO_01",
				"Tech Mahindra test (TECH_AUTO_01) is successfully Approved");
		//Check tender status Completed
		etendercomponentobj.enterOverallComment();
		etendercomponentobj.enterTenderIdInSearch();
		etendercomponentobj.checktenderStatusIsIncompletedState();
		etendercomponentobj.tenderLogout();
	}
}
