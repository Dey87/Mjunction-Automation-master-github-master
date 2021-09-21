package com.testScripts_mjunction;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.baseClasses.ThreadLocalWebdriver;
import com.components.ASN_GRNComponent;
import com.components.PostTenderComponent;
import com.components.PreConditionPo_SanctionCreation;
import com.components.eTenderComponent;

public  class Pre_CondtionScriptForInvoiceASNGRNQtySame extends BaseClass_Web {

	PreConditionPo_SanctionCreation preConditionPo = new PreConditionPo_SanctionCreation(pdfResultReport);
	public eTenderComponent etendercomponentobj =new eTenderComponent(pdfResultReport);
	public PostTenderComponent posttendercomponentobj =new PostTenderComponent(pdfResultReport);
	 public ASN_GRNComponent asn_grncomponentobj=new ASN_GRNComponent(pdfResultReport);
	/**
	 * TestScript Environment Details
	 * 
	 * @throws Exception
	 */
	public void initializeRepository() throws Exception {
		reportDetails.put("Test Script Name", this.getClass().getSimpleName());
		reportDetails.put("Test Script MyWorksshop Document ID", "Doc1234567");
		reportDetails.put("Test Script Revision No", "1");
		reportDetails.put("Test Author Name", "pooja");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}

	@Parameters("TestcaseNo")
	@Test(description = "Pre_CondtionScriptForInvoiceASNGRNQtySame")
	public void sanctionNoteCreationWorkFlow(String no) throws Throwable {
		System.out.println("Entered in the Test method..................");
		try {
			pdfResultReport.readTestDataFile(System.getProperty("user.dir").replace("\\", "/")
					+ "/Resources/TenderCreation_CreateNewRFQ_TestData_pooja.xls", no);
		} catch (Exception e) {
			System.out.println("Unable to read the data from excel file");
		}

		WebDriver driver = ThreadLocalWebdriver.getDriver();

		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);

		
		initializeRepository();

		etendercomponentobj.openURL();
		posttendercomponentobj.POCreatorLogin();
		posttendercomponentobj.navigateToPurchasrOrderList();
		posttendercomponentobj.sanctionNoteSelectionFunctionality();
		posttendercomponentobj.enterSanctionRefNumber();
		posttendercomponentobj.clickOnCreatePOLink();
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
		posttendercomponentobj.ApprovalNotRequired();
		etendercomponentobj.tenderLogout();

		etendercomponentobj.bidder_02_Login();
		posttendercomponentobj.navigateToPoListingWithBidderUser();
		posttendercomponentobj.searchPoRefNoInPoListPage();
		posttendercomponentobj.clickAcceptPoInDropDown();
		posttendercomponentobj.verifySummaryTabAndEnterComment();
		posttendercomponentobj.clickAccepPotBtn();
		posttendercomponentobj.verifyPOStatusIsAccepted();
		etendercomponentobj.tenderLogout();
		
		 etendercomponentobj.openURL();
		 etendercomponentobj.bidder_02_Login();
		 asn_grncomponentobj.SelectASNModule();
		 asn_grncomponentobj.CreateASN();
		 asn_grncomponentobj.SaveASN();
		 asn_grncomponentobj.TabMyInformation();
		 asn_grncomponentobj.TabShipmentInformation("Durgapur");
		 asn_grncomponentobj.TabWhatIamShippingWithBoxesOnly(eTenderComponent.getDataFromPropertiesFile("AsnQTy"),"50","µM");
         asn_grncomponentobj.TabDeliveryChallenChecklist();
         asn_grncomponentobj.TabInvoice("Invoice");
		 asn_grncomponentobj.SaveASN();
		 asn_grncomponentobj.SendForApproval();
		 asn_grncomponentobj.SearchPoInASNList();
		 etendercomponentobj.tenderLogout();
		 
        asn_grncomponentobj.ASNApproverLogin();
        asn_grncomponentobj.navigateToASNList();
		asn_grncomponentobj.searchThePODocNumber();
		asn_grncomponentobj.clickOnApproveOrRejectUnderActionDropdown();
		asn_grncomponentobj.navigatToEachTabInApproveAdvancedShipmentNoticePage();
		asn_grncomponentobj.approvingTheASN();
		etendercomponentobj.tenderLogout();

	    
	 	asn_grncomponentobj.GRN_Creator_Login();
	 	asn_grncomponentobj.navigateToApprovedASNListPage();
	 	asn_grncomponentobj.enterASNShipmentAndSelectVendorName("TCS",eTenderComponent.getDataFromPropertiesFile("ASNShippingTracking"));
	 	asn_grncomponentobj.clickApprovedAsnListActionMenu(eTenderComponent.getDataFromPropertiesFile("ASNShippingTracking"));
	 	asn_grncomponentobj.createGrn(eTenderComponent.getDataFromPropertiesFile("ASNShippingTracking"));
	 	asn_grncomponentobj.verifyAsnQtyValueInGrnCreationPage(1);

	    asn_grncomponentobj.getAcceptedQtyValueAndVerify(1, "15.00");
	        
	    asn_grncomponentobj.verifyAsnQtyValueInGrnCreationPage(2);

		asn_grncomponentobj.getAcceptedQtyValueAndVerify(2, "15.00");

		asn_grncomponentobj.verifyAsnQtyValueInGrnCreationPage(3);

		asn_grncomponentobj.getAcceptedQtyValueAndVerify(3, "15.00");

		asn_grncomponentobj.saveGrnDetails();
			
	    asn_grncomponentobj.submitGrnDetails();
	    
	    asn_grncomponentobj.saveGRNId();

		asn_grncomponentobj.clickGrnSubmitComfirmationYes();

		asn_grncomponentobj.navigateToGrnListPage();

		asn_grncomponentobj.clickOnGRNCreatedTab();

		asn_grncomponentobj.enterShippementTrackingNoInGrnListSerachBox();
		
		etendercomponentobj.tenderLogout();
	}

}
