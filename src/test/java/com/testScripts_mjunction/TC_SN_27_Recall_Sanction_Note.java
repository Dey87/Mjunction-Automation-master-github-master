package com.testScripts_mjunction;

import com.components.PostTenderComponent;
import com.components.eTenderComponent;
import com.objectRepository.TenderCreation_Locators;

import org.openqa.selenium.By;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.components.eTenderComponent;

public class TC_SN_27_Recall_Sanction_Note extends BaseClass_Web {

	public eTenderComponent etendercomponentobj =new eTenderComponent(pdfResultReport);
	public TenderCreation_Locators tendercreationobj =new TenderCreation_Locators(); 
	public PostTenderComponent posttendercomponentobj =new PostTenderComponent(pdfResultReport);
	
	public void initializeRepository() throws Exception {		
		reportDetails.put("Test Script Name", this.getClass().getSimpleName());
		reportDetails.put("Test Script MyWorksshop Document ID", "Doc1234567");
		reportDetails.put("Test Script Revision No", "27");
		reportDetails.put("Test Author Name", "jit");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}
	@Parameters("TestcaseNo")
	@Test(description = "Scenario:27 - Recall Sanction Note – SN can be recalled by Initiator if Action has not been initiated by Approver")
  public void f(String no) throws Throwable {
	  System.out.println("Entered in the Test method..................");
	  try {
		pdfResultReport.readTestDataFile(System.getProperty("user.dir").replace("\\", "/")	+ "/Resources/TenderCreation_CreateNewRFQ_TestData_jit.xls", no);
	} catch (Exception e) {
		System.out.println("Unable to read the data from excel file");
	}
	  
	  initializeRepository();
	  int cvolumnum=4;
	  String path="/Resources/TenderCreation_CreateNewRFQ_TestData_jit.xls";
		String sheetname="data";
		String colnumname="DocumentID";
		
	  etendercomponentobj.openURL();
	  posttendercomponentobj.super_Admin_Login();
	  posttendercomponentobj.clickPostTenderProcessLink();
	  posttendercomponentobj.enterCompleted_TenderId() ;
	  posttendercomponentobj.createSanctionNote();
	  posttendercomponentobj.sanctionReferenceNumber();
	  posttendercomponentobj.SanctionsupplierSelection();
	  posttendercomponentobj.SanctionItemsAllotment();
	 
	  posttendercomponentobj.ScantionComment_recommendationTab();
	  posttendercomponentobj.clickOnSubmitButton();
	  posttendercomponentobj.documentNoSaveandwrite();
	  posttendercomponentobj.sendForApprovalUserDefinedSequential_Coordinator();
	  posttendercomponentobj.ApproverrecallButtonValidation();
	  posttendercomponentobj.writeexcel(path, sheetname, colnumname, 0, cvolumnum, posttendercomponentobj.id);
	}
}
