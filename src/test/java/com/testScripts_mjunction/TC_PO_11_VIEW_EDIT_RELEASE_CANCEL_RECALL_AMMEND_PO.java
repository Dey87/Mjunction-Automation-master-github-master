package com.testScripts_mjunction;


import com.components.PostTenderComponent;
import com.components.eTenderComponent;
import com.objectRepository.TenderCreation_Locators;

import org.openqa.selenium.By;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.components.eTenderComponent;

public class TC_PO_11_VIEW_EDIT_RELEASE_CANCEL_RECALL_AMMEND_PO extends BaseClass_Web {

	public eTenderComponent etendercomponentobj =new eTenderComponent(pdfResultReport);
	public TenderCreation_Locators tendercreationobj =new TenderCreation_Locators(); 
	public PostTenderComponent posttendercomponentobj =new PostTenderComponent(pdfResultReport);
	
	public void initializeRepository() throws Exception {		
		reportDetails.put("Test Script Name", this.getClass().getSimpleName());
		reportDetails.put("Test Script MyWorksshop Document ID", "Doc1234567");
		reportDetails.put("Test Script Revision No", "1");
		reportDetails.put("Test Author Name", "jit");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}
	@Parameters("TestcaseNo")
	@Test(description = "Scenario:1 - Verify the functionality of End to End workflow of Sanction Note")
  public void f(String no) throws Throwable {
	  System.out.println("Entered in the Test method..................");
	
	  try {
		pdfResultReport.readTestDataFile(System.getProperty("user.dir").replace("\\", "/")	+ "/Resources/TenderCreation_CreateNewRFQ_TestData_jit.xls", no);
	} catch (Exception e) {
		System.out.println("Unable to read the data from excel file");
	}
	  
		
		
	  initializeRepository();
	 
	 
	  etendercomponentobj.openURL();
	  posttendercomponentobj.POCreatorLogin();
	  posttendercomponentobj.navigateToPurchasrOrderList();
	  posttendercomponentobj.sanctionNoteSelectionFunctionality();
	  posttendercomponentobj.SearchScantionID();
	  posttendercomponentobj.clickOnCreatePOLink();
		  
	  posttendercomponentobj.TempleteGroup_and_Vendor_Selection_TCS();
	  posttendercomponentobj.Addquantity_Final_Items();
	 
	  posttendercomponentobj.ProceedtoCreatePO();
	  posttendercomponentobj.CreatePOReferenceNum();
	  //edit
	  posttendercomponentobj.EditPO();
	  
	  
	  posttendercomponentobj.PODetailsTAB();
	  posttendercomponentobj.POAttachmentTAB();
	  posttendercomponentobj.POITemDetailsTAB();
	
	  posttendercomponentobj.POTRermsandConditionTAB();
	  posttendercomponentobj.POSaveandApproval();
	  
	  //add all po types
	  posttendercomponentobj.SelectallApproval();
	  posttendercomponentobj.ApprovalNotRequired();
	 //view PO
	  posttendercomponentobj.ViewPO();
	 
	  //amend PO
	  posttendercomponentobj.AmendPO();
	 
	  //releasePO
	  posttendercomponentobj.ReleasePO(); 
	
	//cancel
	  posttendercomponentobj.CancelPO();
	  
	   //recall new po with approver as userdefined
      posttendercomponentobj.navigateToPurchasrOrderList();
	  posttendercomponentobj.sanctionNoteSelectionFunctionality();
	  posttendercomponentobj.SearchScantionID();
	  posttendercomponentobj.clickOnCreatePOLink();
		  
	  posttendercomponentobj.TempleteGroup_and_Vendor_Selection_CTS();
	  posttendercomponentobj.Addquantity_Final_Items();
	 
	  posttendercomponentobj.ProceedtoCreatePO();
	  posttendercomponentobj.CreatePOReferenceNum();
	  posttendercomponentobj.PODetailsTAB();
	  posttendercomponentobj.POAttachmentTAB();
	  posttendercomponentobj.POITemDetailsTAB();
	
	  posttendercomponentobj.POTRermsandConditionTAB();
	  posttendercomponentobj.POSaveandApproval();
	  posttendercomponentobj.sendForApprovalUserDefinedSequential_pocreator();
	  posttendercomponentobj.RecallPO();
	  
	  etendercomponentobj.tenderLogout();
	  
	}
}
