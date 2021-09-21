package com.testScripts_mjunction;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.components.PostTenderComponent;
import com.components.eTenderComponent;

public class TC_SN_15VerifyRFQIdInCompletedTenderListAfterCreatingTender extends BaseClass_Web{
	public PostTenderComponent postTenderComponent =new PostTenderComponent(pdfResultReport);
	public eTenderComponent etendercomponentobj =new eTenderComponent(pdfResultReport);
	
	public void initializeRepository() throws Exception {		
		reportDetails.put("Test Script Name", this.getClass().getSimpleName());
		reportDetails.put("Test Script MyWorksshop Document ID", "Doc1234567");
		reportDetails.put("Test Script Revision No", "15");
		reportDetails.put("Test Author Name", "Vamshi");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}
	@Parameters("TestcaseNo")
	@Test(description = "Scenario:15 -Verify the tender in completed Tender List Page when Tender is in draft Stage")
  public void f(String no) throws Throwable {
	  System.out.println("Entered in the Test method..................");
	  try {
		pdfResultReport.readTestDataFile(System.getProperty("user.dir").replace("\\", "/")	+ "/Resources/TenderCreation_CreateNewRFQ_TestData_venkateshNew.xls", no);
	} catch (Exception e) {
		System.out.println("Unable to read the data from excel file");
	}
	  	initializeRepository();
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
		postTenderComponent.tenderIdSave_sn();
		//etendercomponentobj.sendForNoApproval_validation();
		postTenderComponent.closeWithoutApproval();
		etendercomponentobj.navigateToTenderListing();
		etendercomponentobj.enterTenderIdInSearch();
		etendercomponentobj.tenderLogout();
	  
	  postTenderComponent.sanction_Creator_Login();
	  postTenderComponent.clickPostTenderProcessLink();
	  postTenderComponent.enterTenderIdInSearch_sanctionNote();
	  postTenderComponent.verifyRFQInCompletedTenderList();
	  etendercomponentobj.tenderLogout();
	}
}