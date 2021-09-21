package com.testScripts_mjunction;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.components.PostTenderComponent;
import com.components.eTenderComponent;

public class TC_SN_44_SelectWFApprovalProcessPredefinedandCheckDOPInActiveTest extends BaseClass_Web
{
	public eTenderComponent etendercomponentobj = new eTenderComponent(pdfResultReport);
	public PostTenderComponent posttendercomponentobj = new PostTenderComponent(pdfResultReport);

	public void initializeRepository() throws Exception {
		reportDetails.put("Test Script Name", this.getClass().getSimpleName());
		reportDetails.put("Test Script MyWorksshop Document ID", "Doc1234567");
		reportDetails.put("Test Script Revision No", "44");
		reportDetails.put("Test Author Name", "Venkatesh Jujjuru");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}

	@Parameters("TestcaseNo")
	@Test(description = "Check whether predefined users created for Sanction note Dop are showing or not during Sanction note creation.")
	public void verifyPredefinedWorkFlowHavingInActiveDOP(String no) throws Throwable {
		System.out.println("Entered in the Test method..................");
		try {
			pdfResultReport.readTestDataFile(System.getProperty("user.dir").replace("\\", "/")
					+ "/Resources/TenderCreation_CreateNewRFQ_TestData_venkateshNew.xls", no);
		} catch (Exception e) {
			System.out.println("Unable to read the data from excel file");
		}
		initializeRepository();
		deleteBrowserCookies();
		
		etendercomponentobj.openURL();
		posttendercomponentobj.super_Admin_Login();
		
		posttendercomponentobj.navigateTo_DOPListPage();

		posttendercomponentobj.filterDopWithModuleAndStatus("Sanction", "Draft");
		posttendercomponentobj.filterDopWithModuleAndStatus("Sanction", "Active");
		
        posttendercomponentobj.createDopForSanction_Module();
		posttendercomponentobj.addUserForApprovalForCreatedDop("SEQUENTIAL");
		posttendercomponentobj.navigateTo_DOPList();
		posttendercomponentobj.verifyDopActivationLink();
		posttendercomponentobj.navigateTo_DOPList();
		posttendercomponentobj.verifyDopInactiveLink();
		
		posttendercomponentobj.clickPostTenderProcessLink();
		posttendercomponentobj.enterCompleted_TenderId();
		posttendercomponentobj.CreateSanctionNoteWithPredefinedApprovalWithInactiveDopUsers();
		
		posttendercomponentobj.closeBrowser();
	
	}
}
