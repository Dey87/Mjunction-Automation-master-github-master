package com.testScripts_mjunction;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.baseClasses.ThreadLocalWebdriver;
import com.components.eTenderComponent;

public class TG3_TC_TenderCreation_01_tender_publish_with_tender_approval extends BaseClass_Web{
	
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
	@Test(description = "Tenderpublished_after_tender_approval")
	public void Tenderpublished_after_tender_approval_TG3(String no) throws Throwable {
		System.out.println("Entered in the Test method..................");
		try {
			pdfResultReport.readTestDataFile(System.getProperty("user.dir").replace("\\", "/")
					+ "/Resources/TG3_Testdata_static_scripts.xls", no);
		} catch (Exception e) {
			System.out.println("Unable to read the data from excel file");
		}

		WebDriver driver = ThreadLocalWebdriver.getDriver();
		
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);	
		
		initializeRepository();
//Creating a new tender with sequential 2 approver userdefined WF
		etendercomponentobj.openURL();
		etendercomponentobj.tendercreatorLogin();
		etendercomponentobj.createTenderWithNewRfq();
		etendercomponentobj.Tender_WithOptionalItemsAndQtyEditableGeneralInfoWithThreeBidders("MRO_Automation");
		etendercomponentobj.TG3_RFQ_TermsAndConditionsTab();
		etendercomponentobj.TG3_RFQ_CommercialTermsAndConditionsTab();
		etendercomponentobj.TG3_RFQ_attachmentsTab_Verifying();
		etendercomponentobj.TG3_RFQ_PriceFormatTab_Verifying();
		etendercomponentobj.Tender_WithOptionalItemsAndQtyEditableDateSchedule(25,40,42);
		etendercomponentobj.TG3_RFQ_PrebidDiscussionTab_Verifying();
		etendercomponentobj.clickSubmitBtn();
		etendercomponentobj.tenderIdSave();
		etendercomponentobj.AddTwoUsersForSequentialApproval();
		etendercomponentobj.enterTenderIdInSearch();
		etendercomponentobj.tenderLogout();
		
	//verifying pending tender in 2nd approver login	
		etendercomponentobj.tenderApprover2Login();
		etendercomponentobj.Verifying_Pendingtender_sequentialWF();
		etendercomponentobj.tenderLogout();
		
	//approving pending tender in 1st approver login	
		etendercomponentobj.tenderApproverLogin();
		etendercomponentobj.GoToApprovalworkFlowPendingTendersAndSearchTheTender();
		etendercomponentobj.clickDetailLinkInApprovalListPage();
		etendercomponentobj.tenderApprover_dynamicity();
		etendercomponentobj.ApproverOverAllComentWithTenderHasBeenApproved();
		etendercomponentobj.tenderLogout();
		
	//approving pending tender in 2nd approver login	
		etendercomponentobj.tenderApprover2Login();
		etendercomponentobj.GoToApprovalworkFlowPendingTendersAndSearchTheTender();
		etendercomponentobj.clickDetailLinkInApprovalListPage();
		etendercomponentobj.tenderApprover_dynamicity();
		etendercomponentobj.ApproverOverAllComentWithTenderHasBeenApproved();
		etendercomponentobj.tenderLogout();
		
	//verifying Published tender status 
		etendercomponentobj.tendercreatorLogin();
		etendercomponentobj.navigateToTenderListing();
		etendercomponentobj.enterTenderIdInSearch();
		etendercomponentobj.checkTenderStatusAndTenderStage();
		etendercomponentobj.tenderLogout();
		
	}

}
