package com.testScripts_mjunction;

import java.io.File;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.components.ASN_GRNComponent;
import com.components.AuctionComponent;
import com.components.PostTenderComponent;
import com.components.eTenderComponent;
import com.objectRepository.TenderCreation_Locators;

public class TC_ASN_01ASNListingPageShouldBeDisplayed extends BaseClass_Web {

	public eTenderComponent etendercomponentobj = new eTenderComponent(pdfResultReport);
	public TenderCreation_Locators tendercreationobj = new TenderCreation_Locators();
	public PostTenderComponent posttendercomponentobj = new PostTenderComponent(pdfResultReport);
	public AuctionComponent auctioncomponentobj = new AuctionComponent(pdfResultReport);
	public ASN_GRNComponent asngrnComponent = new ASN_GRNComponent(pdfResultReport);

	public void initializeRepository() throws Exception {
		reportDetails.put("Test Script Name", this.getClass().getSimpleName());
		reportDetails.put("Test Script MyWorksshop Document ID", "Doc1234567");
		reportDetails.put("Test Script Revision No", "01");
		reportDetails.put("Test Author Name", "Vamshi");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}

	@Parameters("TestcaseNo")
	@Test(description = "TC_ASN_01:Click on the create ASN page, listing page should display")
  public void validateAlertEventAlreadyExistedAndAuctionRuleInEventColumnTest(String no) throws Throwable {
	  System.out.println("Entered in the Test method..................");
	  try {
		pdfResultReport.readTestDataFile(System.getProperty("user.dir").replace("\\", "/")	+ "/Resources/TenderCreation_CreateNewRFQ_TestData_Vamshi.xls", no);
	} catch (Exception e) {
		System.out.println("Unable to read the data from excel file");
	}
	  initializeRepository();
	  etendercomponentobj.openURL();
	  //etendercomponentobj.tendercreatorLogin();
	  etendercomponentobj.bidder_02_Login();
	  asngrnComponent.navigateToASNListInBidderLogin();
	  etendercomponentobj.tenderLogout();
	}

}
