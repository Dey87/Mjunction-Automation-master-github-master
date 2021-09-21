package com.testScripts_mjunction;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.components.PostTenderComponent;
import com.components.eTenderComponent;

public class TC_EPSPO_46_TC_EPSPOCheckCancellationReason extends BaseClass_Web {

	public PostTenderComponent posttendercomponentobj = new PostTenderComponent(pdfResultReport);

	public eTenderComponent etendercomponentobj = new eTenderComponent(pdfResultReport);
	public void initializeRepository() throws Exception {
		reportDetails.put("Test Script Name", this.getClass().getSimpleName());
		reportDetails.put("Test Script MyWorksshop Document ID", "Doc1234567");
		reportDetails.put("Test Script Revision No", "46");
		reportDetails.put("Test Author Name", "Pooja");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}
	@Parameters("TestcaseNo")
	@Test(description ="Scenario 46- Check cancellation reason is mandatory during send for approval")
	public void validate_DopCreationFunctionality(String no) throws Throwable {
		System.out.println("Entered in the Test method..................");
		try {
			pdfResultReport.readTestDataFile(System.getProperty("user.dir").replace("\\", "/")
					+ "/Resources/TenderCreation_CreateNewRFQ_TestData_pooja.xls", no);
		} catch (Exception e) {
			System.out.println("Unable to read the data from excel file");
		}

		  initializeRepository();
		  etendercomponentobj.openURL();
          posttendercomponentobj.super_Admin_Login();
	      posttendercomponentobj.navigateTo_DOPList("Po_DOp_Joy");
		  posttendercomponentobj.verifyDop_ActivationLink("Po_DOp_Joy");
		  etendercomponentobj.tenderLogout();

		  //predefine
		  posttendercomponentobj.po_Creator_Login();
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
		  posttendercomponentobj.ApprovalNotRequired();
		  etendercomponentobj.tenderLogout();
		  
		  posttendercomponentobj.po_Creator_Login();
		  posttendercomponentobj.navigateToPurchasrOrderList();
		  posttendercomponentobj.searchThePoRefNoInPoListPage();
		  posttendercomponentobj.CancellationReasonValidationForPredefinedWorkflow();
		  posttendercomponentobj.poCancellation_Sucesses_MessageValidation();
		  posttendercomponentobj.searchThePoRefNoInPoListPage();
		  etendercomponentobj.tenderLogout();
		  
		  posttendercomponentobj.poApprover1Login();
		  posttendercomponentobj.navigateToApprovalPendingPage();
		  posttendercomponentobj.searchThePoDocNoInPoApproverPage();
		  etendercomponentobj.tenderLogout();
		
		  //userdefine
		  posttendercomponentobj.po_Creator_Login();
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
		  posttendercomponentobj.ApprovalNotRequired();
		  etendercomponentobj.tenderLogout();
		  
		  posttendercomponentobj.po_Creator_Login();
		  posttendercomponentobj.navigateToPurchasrOrderList();
		  posttendercomponentobj.searchThePoRefNoInPoListPage();
		  posttendercomponentobj.CancellationReasonValidationForUserdefinedWorkflow();
		  posttendercomponentobj.poCancellation_Sucesses_MessageValidation();
		  posttendercomponentobj.searchThePoRefNoInPoListPage();
		  etendercomponentobj.tenderLogout();
		  
		  posttendercomponentobj.poApprover1Login();
		  posttendercomponentobj.navigateToApprovalPendingPage();
		  posttendercomponentobj.searchThePoDocNoInPoApproverPage();
		  etendercomponentobj.tenderLogout();
		
		  //notrequired
		  posttendercomponentobj.po_Creator_Login();
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
		  posttendercomponentobj.ApprovalNotRequired();
		  etendercomponentobj.tenderLogout();
		  
		  posttendercomponentobj.po_Creator_Login();
		  posttendercomponentobj.navigateToPurchasrOrderList();
		  posttendercomponentobj.searchThePoRefNoInPoListPage();
		  posttendercomponentobj.CancellationReasonValidationForNotRequiredWorkflow();
		  posttendercomponentobj.poCancellationSucessesMessageValidation();
		  posttendercomponentobj.clickOnCancelledPOTab();
		  posttendercomponentobj.searchThePoRefNoInPoListPage();
		  etendercomponentobj.tenderLogout();
		
		  
		  posttendercomponentobj.super_Admin_Login();
		  posttendercomponentobj.navigateTo_DOPList("Po_DOp_Joy");
		  posttendercomponentobj.verify_Dop_InactiveLink("Po_DOp_Joy");
          posttendercomponentobj.closeBrowser();

	}
}
		