package com.testScripts_mjunction;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.components.PostTenderComponent;
import com.components.eTenderComponent;

public class TC_EPS_PO_74TermsAndConditionsSequenceCheckAfterReviewTest extends BaseClass_Web{
	public PostTenderComponent posttendercomponentobj =new PostTenderComponent(pdfResultReport);
	public eTenderComponent etendercomponentobj =new eTenderComponent(pdfResultReport);
	
	public void initializeRepository() throws Exception {		
		reportDetails.put("Test Script Name", this.getClass().getSimpleName());
		reportDetails.put("Test Script MyWorksshop Document ID", "Doc1234567");
		reportDetails.put("Test Script Revision No", "74");
		reportDetails.put("Test Author Name", "Pooja");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}
	@Parameters("TestcaseNo")
	@Test(description = "Scenario:74 - Terms & conditions sequence check after review")
  public void f(String no) throws Throwable {
	  System.out.println("Entered in the Test method..................");
	  try {
		pdfResultReport.readTestDataFile(System.getProperty("user.dir").replace("\\", "/")	+ "/Resources/TenderCreation_CreateNewRFQ_TestData_pooja.xls", no);
	} catch (Exception e) {
		System.out.println("Unable to read the data from excel file");
	}
	  initializeRepository();
	  etendercomponentobj.openURL();
	  posttendercomponentobj.POCreatorLogin();
	  posttendercomponentobj.navigateToPurchasrOrderList();
	  posttendercomponentobj.sanctionNoteSelectionFunctionality();
	  posttendercomponentobj.enterSanctionRefNumber();
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
	  posttendercomponentobj.savePoDocNumber();
	  posttendercomponentobj.clickOnCloseInSendForApproval();
	  etendercomponentobj.tenderLogout();
	  
	  posttendercomponentobj.POCreatorLogin();
	  posttendercomponentobj.navigateToPurchasrOrderList();
	  posttendercomponentobj.searchThePoRefNoInPoListPage();
	  posttendercomponentobj.editPODetailsValidation();
	  posttendercomponentobj.PODetailsTAB();
	  posttendercomponentobj.POAttachmentTAB();
	  posttendercomponentobj.POITemDetailsTAB();	
	  posttendercomponentobj.POTermsandConditionTABAddingRows();
	  posttendercomponentobj.POSaveandApproval();
	  posttendercomponentobj.savePoDocNumber();
	  posttendercomponentobj.sendForApprovalUserDefinedSequential_pocreator();
	  posttendercomponentobj.searchThePoRefNoInPoListPage();
	  etendercomponentobj.tenderLogout();
	  
	  posttendercomponentobj.poApprover1Login();
	  posttendercomponentobj.navigateToApprovalPendingPage();
	  posttendercomponentobj.navigateToPurchaseOrderApproval();
	  posttendercomponentobj.provide_PO_ApproverComment();
	  posttendercomponentobj.clickOnApproverCommentSection(); 
	  posttendercomponentobj.cancelPurchaseOrderReview();
	  etendercomponentobj.tenderLogout();
	  
	  posttendercomponentobj.POCreatorLogin();
	  posttendercomponentobj.navigateToPurchasrOrderList();
	  posttendercomponentobj.searchThePoRefNoInPoListPage();
	  posttendercomponentobj.editPODetailsValidation();
	  posttendercomponentobj.PODetailsTAB();
	  posttendercomponentobj.POAttachmentTAB();
	  posttendercomponentobj.POITemDetailsTAB();
	  posttendercomponentobj.POTermsandConditionTABAddingRows();
	  
	  etendercomponentobj.tenderLogout();
	  posttendercomponentobj.closeBrowser();
	}
}
