package com.testScripts_mjunction;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.baseClasses.ThreadLocalWebdriver;
import com.components.PreConditionPo_SanctionCreation;

public class Pre_conditionTestStepsForPurchaseOrder_SanctionNoteCreationTest extends BaseClass_Web {

	PreConditionPo_SanctionCreation preConditionPo = new PreConditionPo_SanctionCreation(pdfResultReport);

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
	@Test(description = "sanctionNoteCreationWorkFlow")
	public void sanctionNoteCreationWorkFlow(String no) throws Throwable {
		System.out.println("Entered in the Test method..................");
		try {
			pdfResultReport.readTestDataFile(System.getProperty("user.dir").replace("\\", "/")
					+ "/Resources/TenderCreation_CreateNewRFQ_TestData_venkateshNew.xls", no);
		} catch (Exception e) {
			System.out.println("Unable to read the data from excel file");
		}

		WebDriver driver = ThreadLocalWebdriver.getDriver();

		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);

		
		initializeRepository();

		preConditionPo.openURL();

		preConditionPo.deleteBrowserCookies();

		preConditionPo.super_Admin_Login();

		preConditionPo.clickPostTenderProcessLink();

		preConditionPo.enterCompleted_TenderId();

		preConditionPo.clickSN_StageBtn();
		
		preConditionPo.clickSanctionNoteCreateBtn();

		preConditionPo.createsanctionReferenceNumber();

		preConditionPo.checkApproverSuppliersTab("CTS");
		
		preConditionPo.checkApproverSuppliersTab("TCS");
		
		preConditionPo.clickRejectedSupplierTab();
		
		preConditionPo.checkRejectedSuppliersTab("Tech Mahindra");
		
		preConditionPo.clickApproverSupplierTab();
		
		preConditionPo.SanctionsupplierSelection();

		preConditionPo.SanctionItemsAllotment();
		
		preConditionPo.saveSanctionDetails();
		
		preConditionPo.clickSanctionNoteSummaryTab();
		
		preConditionPo.veiwSummaryDetails();
		
		preConditionPo.ScantionComment_recommendationTab();
		
		preConditionPo.clickPrintPreviewTab();
		
		preConditionPo.viewPrintTabDetails();
		
		preConditionPo.clickOnSubmitButton();

		preConditionPo.documentNoSave();

		preConditionPo.sendForApproval();

		preConditionPo.enterDocumentNoInSearch();

		preConditionPo.verifyStageForSanctionNoteCreated();

	}

}
