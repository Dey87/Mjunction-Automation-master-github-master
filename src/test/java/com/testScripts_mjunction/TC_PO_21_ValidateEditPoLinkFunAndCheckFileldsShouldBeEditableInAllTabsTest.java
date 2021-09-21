package com.testScripts_mjunction;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.components.PostTenderComponent;
import com.components.eTenderComponent;

public class TC_PO_21_ValidateEditPoLinkFunAndCheckFileldsShouldBeEditableInAllTabsTest extends BaseClass_Web {
	public eTenderComponent etendercomponentobj = new eTenderComponent(pdfResultReport);

	public PostTenderComponent posttendercomponentobj = new PostTenderComponent(pdfResultReport);

	public void initializeRepository() throws Exception {
		reportDetails.put("Test Script Name", this.getClass().getSimpleName());
		reportDetails.put("Test Script MyWorksshop Document ID", "Doc1234567");
		reportDetails.put("Test Script Revision No", "21");
		reportDetails.put("Test Author Name", "venkatesh jujjuru");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}

	@Parameters("TestcaseNo")
	@Test(description = "User should be able to edit the information in these pages except PO No, PO creator name")
	public void validateEditPoLinkAndFileldsShouldBeEditable(String no) throws Throwable {
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
		posttendercomponentobj.POTermsandConditionTAB();
		posttendercomponentobj.POSaveandApproval();
		posttendercomponentobj.savePoDocNumber();
		posttendercomponentobj.closeWithoutApproval();

		posttendercomponentobj.navigateToPurchasrOrderList();
		posttendercomponentobj.searchThePoRefNoInPoListPage();
		posttendercomponentobj.clickEditPOLink();
		posttendercomponentobj.checkPoCreatorNameAndPoNoFieldsNonEditable();
		posttendercomponentobj.verifyPoDetailsTab();
		posttendercomponentobj.verifyBidderDetailsUiType();
		posttendercomponentobj.editItemDetails();
		posttendercomponentobj.SavePoDetails();

		posttendercomponentobj.closeBrowser();

	}
}
