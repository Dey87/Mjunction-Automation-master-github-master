package com.testScripts_mjunction;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.baseClasses.ThreadLocalWebdriver;
import com.components.ASN_GRNComponent;
import com.components.AuctionComponent;
import com.components.PostTenderComponent;
import com.components.PreConditionPo_SanctionCreation;
import com.components.eTenderComponent;
import com.objectRepository.TenderCreation_Locators;

public class TC_ASN_19GRNUserShouldBeAbleToViewAllTheSentASNInTheListingPage extends BaseClass_Web{
	PreConditionPo_SanctionCreation preConditionPo = new PreConditionPo_SanctionCreation(pdfResultReport);
	public eTenderComponent etendercomponentobj =new eTenderComponent(pdfResultReport);
	public TenderCreation_Locators tendercreationobj =new TenderCreation_Locators(); 
	public PostTenderComponent posttendercomponentobj =new PostTenderComponent(pdfResultReport);
    public ASN_GRNComponent asn_grncomponentobj=new ASN_GRNComponent(pdfResultReport);
	
	public void initializeRepository() throws Exception {		
		reportDetails.put("Test Script Name", this.getClass().getSimpleName());
		reportDetails.put("Test Script MyWorksshop Document ID", "Doc1234567");
		reportDetails.put("Test Script Revision No", "19");
		reportDetails.put("Test Author Name", "Pooja");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}
	@Parameters("TestcaseNo")
	@Test(description = "TC_ASN_19:GRN user should be able to view all the sent ASN in the listing page")
  public void validateAlertEventAlreadyExistedAndAuctionRuleInEventColumnTest(String no) throws Throwable {
	  System.out.println("Entered in the Test method..................");
	  try {
		pdfResultReport.readTestDataFile(System.getProperty("user.dir").replace("\\", "/")	+ "/Resources/TenderCreation_CreateNewRFQ_TestData_pooja.xls", no);
	} catch (Exception e) {
		System.out.println("Unable to read the data from excel file");
	}
	     initializeRepository();
         etendercomponentobj.openURL();
		 etendercomponentobj.bidder_02_Login();
		 asn_grncomponentobj.SelectASNModule();
		 asn_grncomponentobj.CreateASN();
		 asn_grncomponentobj.SaveASN();
		 asn_grncomponentobj.TabMyInformation();
		 asn_grncomponentobj.TabShipmentInformation("Durgapur");
		 asn_grncomponentobj.TabWhatIamShippingWithBoxesOnly("15","50","µM");
         asn_grncomponentobj.TabDeliveryChallenChecklist();
		 asn_grncomponentobj.TabInvoice("Invoice");
		 asn_grncomponentobj.SaveASN();
		 asn_grncomponentobj.SendForApproval();
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
		asn_grncomponentobj.enterShipmentNumber(eTenderComponent.getDataFromPropertiesFile("ASNShippingTracking"));
	    
	}

}
