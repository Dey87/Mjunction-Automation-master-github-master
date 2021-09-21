package com.testScripts_mjunction;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.baseClasses.ThreadLocalWebdriver;
import com.components.RfqFromIndentComponent;
import com.components.eTenderComponent;

public class TG1_TC_TenderCreation_01_tender_publish_with_tender_approval extends BaseClass_Web{
	
	public eTenderComponent etendercomponentobj = new eTenderComponent(pdfResultReport);
	public RfqFromIndentComponent rfqfromintendcomponentobj = new RfqFromIndentComponent(pdfResultReport);

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
	@Test(description = "Tenderpublished_after_tender_approval_tender_from_indent")
	public void Tenderpublished_after_tender_approval_TG1(String no) throws Throwable {
		System.out.println("Entered in the Test method..................");
		try {
			pdfResultReport.readTestDataFile(System.getProperty("user.dir").replace("\\", "/")
					+ "/Resources/TG1_Testdata_static_scripts.xls", no);
		} catch (Exception e) {
			System.out.println("Unable to read the data from excel file");
		}
		WebDriver driver = ThreadLocalWebdriver.getDriver();
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);	
		initializeRepository();
	//Creating a new Indent with No approval
		etendercomponentobj.openURL();
		rfqfromintendcomponentobj.IndentcreatorLogin();
		rfqfromintendcomponentobj.navigateToIndentCreation();
		rfqfromintendcomponentobj.IndentTG1_General_Info_tabvalidation("Indigenous Indent (Supply & Service Both) V-004");
		rfqfromintendcomponentobj.IndentTG1_Indent_Details_tabvalidation();
		rfqfromintendcomponentobj.IndentTG1_Other_Information_tabvalidation();
		rfqfromintendcomponentobj.IndentTG1_BOM_Item_tabvalidation();
		rfqfromintendcomponentobj.IndentTG1_BOM_Services_tabvalidation();
		rfqfromintendcomponentobj.IndentTG1_EstimationSheet_tabvalidation();
		rfqfromintendcomponentobj.IndentTG1_technical_Specification_tabvalidation();
		rfqfromintendcomponentobj.IndentTG1_Annexures_tabvalidation();
		rfqfromintendcomponentobj.IndentTG1_Submit();
		rfqfromintendcomponentobj.SystemIndentNoSave();
		rfqfromintendcomponentobj.NoApproval_IndentWF();
		rfqfromintendcomponentobj.navigateToIndentListing();
		rfqfromintendcomponentobj.enterIndentNoInSearch();
		rfqfromintendcomponentobj.VerifyIndentStatus("Completed");

	//Mark the created indent 'Marked for RFQ'
		rfqfromintendcomponentobj.Indent_Mark_for_RFQ_functionality();
		rfqfromintendcomponentobj.enterIndentNoInSearch();
		rfqfromintendcomponentobj.VerifyIndentStatus("Marked For RFQ");
		etendercomponentobj.tenderLogout();
		
	//Indent assignment Process (self claim)
		etendercomponentobj.tendercreatorLogin();
		rfqfromintendcomponentobj.navigateToIndentAssignment();
		rfqfromintendcomponentobj.enterIndentNoInSearch();
		rfqfromintendcomponentobj.Verify_Indent_Assignment_self_Claim();
		rfqfromintendcomponentobj.enterIndentNoInSearch();
		rfqfromintendcomponentobj.VerifyIndentStatus_AssignmentListPage("Assigned");
		rfqfromintendcomponentobj.navigateToCreateRFQFromIndentPage();
		rfqfromintendcomponentobj.enterIndentNoInSearch_RFQfromIndentPage();
		
	//Create and publish RFQ from indent
		rfqfromintendcomponentobj.Create_RFQ_From_Indent("Indigenous Tender (Supply & Service Both) V-1.0");
		rfqfromintendcomponentobj.PublishTender_from_indent_withRFQ_TG1("Indigenous Tender (Supply & Service Both) V-1.0",22,30,40);
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
		etendercomponentobj.TG1_tenderApprover_dynamicity();
		etendercomponentobj.ApproverOverAllComentWithTenderHasBeenApproved();
		etendercomponentobj.tenderLogout();
		
	//approving pending tender in 2nd approver login	
		etendercomponentobj.tenderApprover2Login();
		etendercomponentobj.GoToApprovalworkFlowPendingTendersAndSearchTheTender();
		etendercomponentobj.clickDetailLinkInApprovalListPage();
		etendercomponentobj.TG1_tenderApprover_dynamicity();
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
