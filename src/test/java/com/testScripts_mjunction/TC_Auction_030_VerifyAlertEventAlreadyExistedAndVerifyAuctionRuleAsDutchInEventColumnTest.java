package com.testScripts_mjunction;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.components.AuctionComponent;
import com.components.PostTenderComponent;
import com.components.eTenderComponent;
import com.objectRepository.TenderCreation_Locators;

public class TC_Auction_030_VerifyAlertEventAlreadyExistedAndVerifyAuctionRuleAsDutchInEventColumnTest
		extends BaseClass_Web {

	public eTenderComponent etendercomponentobj = new eTenderComponent(pdfResultReport);
	public TenderCreation_Locators tendercreationobj = new TenderCreation_Locators();
	public PostTenderComponent postTenderComponentobj = new PostTenderComponent(pdfResultReport);
	public AuctionComponent auctioncomponentobj = new AuctionComponent(pdfResultReport);

	public void initializeRepository() throws Exception {
		reportDetails.put("Test Script Name", this.getClass().getSimpleName());
		reportDetails.put("Test Script MyWorksshop Document ID", "Doc1234567");
		reportDetails.put("Test Script Revision No", "30");
		reportDetails.put("Test Author Name", "venkatesh");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}

	@Parameters("TestcaseNo")
	@Test(description = "TC_Auction_030: VerifyAlertEventAlreadyExistedAndVerifyAuctionRuleAsDutchInEventColumnTest")
	public void validateEventAlreadyExistedAndValidateAuctionRuleIsShowingInEventColumn(String no) throws Throwable {
		System.out.println("Entered in the Test method..................");
		try {
			pdfResultReport.readTestDataFile(System.getProperty("user.dir").replace("\\", "/")
					+ "/Resources/TenderCreation_CreateNewRFQ_TestData_venkateshNew.xls", no);
		} catch (Exception e) {
			System.out.println("Unable to read the data from excel file");
		}
		initializeRepository();

		String decvalue = "400000";

		etendercomponentobj.openURL();
		etendercomponentobj.tendercreatorLogin();

		auctioncomponentobj.navigateToTenderListInitiateAuctionPage();
		postTenderComponentobj.enterCompleted_TenderId();
		auctioncomponentobj.clickinitiateAutionFromActionMenu();
		auctioncomponentobj.SelectAuctionType("REVERSE AUCTION");
		auctioncomponentobj.SelectAuctionBiddingType("Normal");
		auctioncomponentobj.selectTemplateName("BOQ (Mandatory)");
		auctioncomponentobj.selectItemCode("Item Code");
		auctioncomponentobj.selectItemCodeDescription("Item Description");
		auctioncomponentobj.selectUOM("U.O.M");
		auctioncomponentobj.selectQuantity("Supplier Quoted Quantity");
		auctioncomponentobj.selectStartBidPrice("Negotiated Total (Exclusive of Tax)(AED)");
		auctioncomponentobj.clickInitiateAuctionFromPopUp();
		auctioncomponentobj.clickViewEvent();
		auctioncomponentobj.clickSetUpAuction(1);
		auctioncomponentobj.enterMandatoryFieldsInEventDetailPageForDutchAuctioRule("4500000", decvalue,
				"DUTCH AUCTION", "4100000", 8, 10, 15);

		auctioncomponentobj.clickBackToEPS();

		auctioncomponentobj.navigateToTenderListInitiateAuctionPage();
		postTenderComponentobj.enterCompleted_TenderId();
		auctioncomponentobj.clickinitiateAutionFromActionMenu();
		auctioncomponentobj.clickInitiateAuctionFromPopUp();
		auctioncomponentobj.verifyEventAlreadyExist();
		auctioncomponentobj.clickViewEvent();
		auctioncomponentobj.verifyAuctionRuleInEventColumn("DUTCH AUCTION");

	}

}
