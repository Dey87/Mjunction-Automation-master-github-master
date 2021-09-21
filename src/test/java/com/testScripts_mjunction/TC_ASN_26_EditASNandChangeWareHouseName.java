package com.testScripts_mjunction;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;

import com.components.PostTenderComponent;
import com.components.PreConditionPo_SanctionCreation;
import com.components.eTenderComponent;
import com.components.ASN_GRNComponent;

public class TC_ASN_26_EditASNandChangeWareHouseName extends BaseClass_Web {

	PreConditionPo_SanctionCreation preConditionPo = new PreConditionPo_SanctionCreation(pdfResultReport);
	public eTenderComponent etendercomponentobj = new eTenderComponent(pdfResultReport);
	public PostTenderComponent posttendercomponentobj = new PostTenderComponent(pdfResultReport);
	public ASN_GRNComponent ASN_GRNComponent = new ASN_GRNComponent(pdfResultReport);

	/**
	 * TestScript Environment Details
	 * 
	 * @throws Exception
	 */
	public void initializeRepository() throws Exception {
		reportDetails.put("Test Script Name", this.getClass().getSimpleName());
		reportDetails.put("Test Script MyWorksshop Document ID", "Doc1234567");
		reportDetails.put("Test Script Revision No", "1");
		reportDetails.put("Test Author Name", "Bhowmick");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}

	@Parameters("TestcaseNo")
	@Test(description = "TC_ASN_26_EditASNandChangeWareHouseName")
	public void sanctionNoteCreationWorkFlow(String no) throws Throwable {
		System.out.println("Entered in the Test method..................");
		try {
			pdfResultReport.readTestDataFile(System.getProperty("user.dir").replace("\\", "/")
					+ "/Resources/TenderCreation_CreateNewRFQ_TestData_jit.xls", no);
		} catch (Exception e) {
			System.out.println("Unable to read the data from excel file");
		}

		etendercomponentobj.openURL();

		etendercomponentobj.bidder_02_Login();

		ASN_GRNComponent.SelectASNModule();

		ASN_GRNComponent.CreateASN();

		ASN_GRNComponent.SaveASN();

		ASN_GRNComponent.TabMyInformation();

		ASN_GRNComponent.TabShipmentInformation("Durgapur");

		ASN_GRNComponent.TabWhatIamShippingWithBoxesOnlyPOQuantityVerify("15", "50", "�M");

		ASN_GRNComponent.TabDeliveryChallenChecklist();

		ASN_GRNComponent.TabInvoice("Invoice");

		ASN_GRNComponent.SaveASN();

		ASN_GRNComponent.SendForApproval();

		ASN_GRNComponent.SearchPoInASNList();

		etendercomponentobj.tenderLogout();

		ASN_GRNComponent.ASNApproverLogin();

		ASN_GRNComponent.navigateToASNList();

		ASN_GRNComponent.searchThePODocNumber();

		etendercomponentobj.tenderLogout();
		
		etendercomponentobj.bidder_02_Login();

		ASN_GRNComponent.SelectASNModule();
		
        ASN_GRNComponent.ASNListPage();	
		  
		ASN_GRNComponent.SearchPoInASNList();
		
		ASN_GRNComponent.clickOnEditUnderActionDropdown();

		ASN_GRNComponent.TabShipmentInformationWareHouseChange("Patna");
		
		ASN_GRNComponent.SaveASNWithconfirmation();
		
		ASN_GRNComponent.ASNListPage();
		
		ASN_GRNComponent.SearchPoInASNList();
		
		ASN_GRNComponent.VerifyBidderWareHouse();
		
		etendercomponentobj.tenderLogout();
		
		ASN_GRNComponent.ASNApproverLogin();

		ASN_GRNComponent.navigateToASNList();

		ASN_GRNComponent.searchThePODocNumber();
		
		ASN_GRNComponent.VerifyApproverWareHouse();
		
		etendercomponentobj.tenderLogout();

	}

}
