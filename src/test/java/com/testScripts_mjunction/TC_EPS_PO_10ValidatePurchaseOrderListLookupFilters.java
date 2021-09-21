package com.testScripts_mjunction;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.components.PostTenderComponent;
import com.components.eTenderComponent;

public class TC_EPS_PO_10ValidatePurchaseOrderListLookupFilters extends BaseClass_Web{
	public PostTenderComponent postTenderComponent =new PostTenderComponent(pdfResultReport);
	public eTenderComponent etendercomponentobj =new eTenderComponent(pdfResultReport);
	
	public void initializeRepository() throws Exception {		
		reportDetails.put("Test Script Name", this.getClass().getSimpleName());
		reportDetails.put("Test Script MyWorksshop Document ID", "Doc1234567");
		reportDetails.put("Test Script Revision No", "10");
		reportDetails.put("Test Author Name", "Prasad");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}
	@Parameters("TestcaseNo")
	@Test(description = "Scenario:10 -  Filters of Status, PO Category, Source (RFQ/SN/PR/legacy/legacy(discarded)/legacy(amended)), PO Type, Used in Catalogue should be present on PO listing page")
  public void f(String no) throws Throwable {
	  System.out.println("Entered in the Test method..................");
	  try {
		pdfResultReport.readTestDataFile(System.getProperty("user.dir").replace("\\", "/")	+ "/Resources/TenderCreation_CreateNewRFQ_TestData_Prasad.xls", no);
	} catch (Exception e) {
		System.out.println("Unable to read the data from excel file");
	}
	  initializeRepository();
	  etendercomponentobj.openURL();
	  postTenderComponent.POCreatorLogin();
	  postTenderComponent.navigateToPurchasrOrderList();
	  postTenderComponent.sanctionNoteSelectionFunctionality();
	  postTenderComponent.enterSanctionRefNumber();
	  postTenderComponent.clickOnCreatePOLink();
	  postTenderComponent.TempleteGroup_and_Vendor_Selection_TCS();
	  postTenderComponent.Addquantity_Final_Items();
	  postTenderComponent.ProceedtoCreatePO();
	  postTenderComponent.CreatePOReferenceNum();
	  postTenderComponent.PODetailsTAB();
	  postTenderComponent.POAttachmentTAB();
	  postTenderComponent.POITemDetailsTAB();
	  postTenderComponent.POTRermsandConditionTAB();
	  postTenderComponent.POSaveandApproval();
	  postTenderComponent.savePoDocNumber();
	  postTenderComponent.ApprovalNotRequired();
	  etendercomponentobj.tenderLogout();
	  
	  postTenderComponent.POCreatorLogin();
	  postTenderComponent.navigateToPurchasrOrderList();
	  postTenderComponent.verifyPOStatusDropdownList();
	  postTenderComponent.verifyPOinDraftStatus();
	  postTenderComponent.verifyPOinPendingForApprovalStatus();
	  postTenderComponent.verifyPOinPendingForAcceptanceStatus();
	  postTenderComponent.verifyPOinAcceptedStatus();
	  
	  postTenderComponent.navigateToPurchasrOrderList();
	  postTenderComponent.verifyPOCategoryFilter();
	  
	  postTenderComponent.navigateToPurchasrOrderList();
	  postTenderComponent.verifyPOSourceFilter();
	  
	  postTenderComponent.navigateToPurchasrOrderList();
	  postTenderComponent.verifyPOTypeFilter();
	  
	  postTenderComponent.navigateToPurchasrOrderList();
	  postTenderComponent.usedCatalogueFilter();
	  
	  postTenderComponent.navigateToPurchasrOrderList();
	  postTenderComponent.verifySearchFiledInPoListPage();
	  etendercomponentobj.tenderLogout();
	  
	}
}
