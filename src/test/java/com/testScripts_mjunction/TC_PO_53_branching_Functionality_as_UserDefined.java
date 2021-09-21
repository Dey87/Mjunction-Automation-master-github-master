package com.testScripts_mjunction;

import com.components.PostTenderComponent;
import com.components.eTenderComponent;
import com.objectRepository.TenderCreation_Locators;

import org.openqa.selenium.By;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.components.eTenderComponent;

public class TC_PO_53_branching_Functionality_as_UserDefined extends BaseClass_Web {

	public eTenderComponent etendercomponentobj = new eTenderComponent(pdfResultReport);
	public TenderCreation_Locators tendercreationobj = new TenderCreation_Locators();
	public PostTenderComponent posttendercomponentobj = new PostTenderComponent(pdfResultReport);

	public void initializeRepository() throws Exception {
		reportDetails.put("Test Script Name", this.getClass().getSimpleName());
		reportDetails.put("Test Script MyWorksshop Document ID", "Doc1234567");
		reportDetails.put("Test Script Revision No", "2");
		reportDetails.put("Test Author Name", "jit");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}

	@Parameters("TestcaseNo")
	@Test(description = "Scenario:2 - Verify the functionality of End to End workflow of 'PO from Sanction note' with approval type 'User defined'")
	public void f(String no) throws Throwable {
		System.out.println("Entered in the Test method..................");
		try {
			pdfResultReport.readTestDataFile(System.getProperty("user.dir").replace("\\", "/")
					+ "/Resources/TenderCreation_CreateNewRFQ_TestData_jit.xls", no);
		} catch (Exception e) {
			System.out.println("Unable to read the data from excel file");
		}
		initializeRepository();
		etendercomponentobj.openURL();

		posttendercomponentobj.super_Admin_Login();
		posttendercomponentobj.clickPostTenderProcessLink();
		posttendercomponentobj.enterCompleted_TenderId();
		posttendercomponentobj.IssuePO();
		posttendercomponentobj.TempleteGroup_and_Vendor_Selection_TCS();
		posttendercomponentobj.Addquantity_Final_Items();
		posttendercomponentobj.ProceedtoCreatePO();
		posttendercomponentobj.CreatePOReferenceNum();
		posttendercomponentobj.PODetailsTAB();
		posttendercomponentobj.POAttachmentTAB();
		posttendercomponentobj.POITemDetailsTAB();
		posttendercomponentobj.POTRermsandConditionTAB();
		posttendercomponentobj.POSaveandApproval();
		posttendercomponentobj.savePoDocNumber();
		posttendercomponentobj.sendForApprovalUserDefinedSequential_pocreator();
		etendercomponentobj.tenderLogout();

		posttendercomponentobj.poApprover1Login();
		posttendercomponentobj.navigateToApprovalPendingPage();
		posttendercomponentobj.navigateToPurchaseOrderApproval();
		posttendercomponentobj.provide_PO_ApproverComment();
		posttendercomponentobj.BranchFunctionality();
		etendercomponentobj.tenderLogout();

	}

}
