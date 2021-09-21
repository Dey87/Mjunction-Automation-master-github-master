package com.testScripts_mjunction;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.components.PostTenderComponent;
import com.components.eTenderComponent;

public class TC_SN_18VerifyRFQIdInCompletedTenderListWithBidSubmissionClosedTenderStage extends BaseClass_Web{
	public PostTenderComponent postTenderComponent =new PostTenderComponent(pdfResultReport);
	public eTenderComponent etendercomponentobj =new eTenderComponent(pdfResultReport);
	
	public void initializeRepository() throws Exception {		
		reportDetails.put("Test Script Name", this.getClass().getSimpleName());
		reportDetails.put("Test Script MyWorksshop Document ID", "Doc1234567");
		reportDetails.put("Test Script Revision No", "18");
		reportDetails.put("Test Author Name", "Vamshi");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}
	@Parameters("TestcaseNo")
	@Test(description = "Scenario:18 -Verify the tender in completed Tender List Page when Tender is in Bid submission Closed stage")
  public void f(String no) throws Throwable {
	  System.out.println("Entered in the Test method..................");
	  try {
		pdfResultReport.readTestDataFile(System.getProperty("user.dir").replace("\\", "/")	+ "/Resources/TenderCreation_CreateNewRFQ_TestData_venkateshNew.xls", no);
	} catch (Exception e) {
		System.out.println("Unable to read the data from excel file");
	}
	  //Tender Creation	
	  	initializeRepository();
	  	etendercomponentobj.openURL();
	  	etendercomponentobj.tendercreatorLogin();
		etendercomponentobj.createTenderWithNewRfq();
		etendercomponentobj.Tender_WithOptionalItemsAndQtyEditableGeneralInfoWithThreeBidders("With Optional Items & Qty Editable by Supplier_V2");
		etendercomponentobj.Tender_WithOptionalItemsAndQtyEditableOtherAttachments();
		etendercomponentobj.Tender_WithOptionalItemsAndQtyEditableProjectTab();
		etendercomponentobj.Tender_WithOptionalItemsAndQtyEditableAttachmentsTab();
		etendercomponentobj.Tender_WithOptionalItemsAndQtyEditableDateSchedule(9,18,19);
		etendercomponentobj.Tender_WithOptionalItemsAndQtyEditableBOQOptional();
		etendercomponentobj.Tender_WithOptionalItemsAndQtyEditableTermsAndCondition(3);
		etendercomponentobj.Tender_WithOptionalItemsAndQtyEditableTechnical(3);
		etendercomponentobj.Tender_WithOptionalItemsAndQtyEditableBOQMandatortTab(3, 1, 3, "CCM");
		etendercomponentobj.clickSubmitBtn();
		etendercomponentobj.tenderIdSave();
		postTenderComponent.tenderIdSave_sn();
		etendercomponentobj.sendForApproval_validation();
		etendercomponentobj.enterTenderIdInSearch();
		etendercomponentobj.tenderLogout();
		etendercomponentobj.waitTillBidstartDateReached();
	//Bid Submission	
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
	//Evaluation	
		etendercomponentobj.waitTillBidDuetDateReached();
		etendercomponentobj.waitTillBidOpentDateReached();
		etendercomponentobj.tendercreatorLogin();
		etendercomponentobj.navigateToTenderListing();
		etendercomponentobj.enterTenderIdInSearch();
		etendercomponentobj.checktenderStatusIsInclosedState();
		etendercomponentobj.tenderLogout();
	//Checking tender in post tender process   
		postTenderComponent.sanction_Creator_Login();
		postTenderComponent.clickPostTenderProcessLink();
		postTenderComponent.enterTenderIdInSearch_sanctionNote();
		postTenderComponent.verifyRFQInCompletedTenderList();
		etendercomponentobj.tenderLogout();
	}
}