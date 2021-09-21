package com.testScripts_mjunction;

import java.io.File;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.components.AuctionComponent;
import com.components.PostTenderComponent;
import com.components.eTenderComponent;
import com.objectRepository.TenderCreation_Locators;

public class TC_Auction_028_E2E_FlowWithAuctionRuleDutch extends BaseClass_Web {

	public eTenderComponent etendercomponentobj = new eTenderComponent(pdfResultReport);
	public TenderCreation_Locators tendercreationobj = new TenderCreation_Locators();
	public PostTenderComponent postTenderComponentobj = new PostTenderComponent(pdfResultReport);
	public AuctionComponent auctioncomponentobj = new AuctionComponent(pdfResultReport);

	public void initializeRepository() throws Exception {
		reportDetails.put("Test Script Name", this.getClass().getSimpleName());
		reportDetails.put("Test Script MyWorksshop Document ID", "Doc1234567");
		reportDetails.put("Test Script Revision No", "28");
		reportDetails.put("Test Author Name", "venkatesh");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}

	@Parameters("TestcaseNo")
	@Test(description = "TC_Auction_028: E2E_FlowWithAuctionRuleDutchAndBiddingTypeNormalTest")
	public void validateWorkFlowWithAuctionRuleDuctAndNormalBiddingType(String no) throws Throwable {
		System.out.println("Entered in the Test method..................");
		try {
			pdfResultReport.readTestDataFile(System.getProperty("user.dir").replace("\\", "/")
					+ "/Resources/TenderCreation_CreateNewRFQ_TestData_venkateshNew.xls", no);
		} catch (Exception e) {
			System.out.println("Unable to read the data from excel file");
		}
		initializeRepository();

		String currentUsersHomeDir = System.getProperty("user.dir");
		String otherFolder = currentUsersHomeDir + File.separator + "Resources" + File.separator + "rfqExcel";
		String decvalue = "400000";
		auctioncomponentobj.DeleteFolder(otherFolder);

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
		auctioncomponentobj.validateStartPriceShouldBeLessThanReservePriceForDutchRule("4600000", "4100000",
				"Start Price must be less than 4500000");
		auctioncomponentobj.validateReservePriceShouldBeLessThanL1PriceForDutchRule("4800000", "4500000",
				"Reserve price must be > 4100000 and <= 4500000");
		auctioncomponentobj.validateTwoMidderAreSelectedByDefault();
		auctioncomponentobj.clickSaveAndSchedule();
		auctioncomponentobj.selectRespectiveEventRowAndPublishTheEvent(1);
		auctioncomponentobj.ScheduleTheEvent(1, 2, 5);
		auctioncomponentobj.clickSaveAndActivateBtn();
		auctioncomponentobj.logoutTheAuction();
		String startTime = etendercomponentobj.getDataFromPropertiesFile("AuctionStartDate");
		auctioncomponentobj.WaititngForBiDTimeMatching(startTime);
		System.out.println("===============Auction bid Time Started=====================");

		etendercomponentobj.openURL();
		String AuctionDate = eTenderComponent.getDataFromPropertiesFile("AuctionStartDate");

		etendercomponentobj.bidder_01_Login();
		auctioncomponentobj.NavigaterToAuctionParcipitation();
		auctioncomponentobj.Activate_Aution(AuctionDate);
		
		auctioncomponentobj.clickOnLiveAuction();
		
		auctioncomponentobj.ParticipateInLiveAuctionAndSubmitBid(decvalue);

		String endTime = etendercomponentobj.getDataFromPropertiesFile("AuctionEndDate");
		auctioncomponentobj.WaititngForBiDTimeMatching(endTime);
		System.out.println("===============Auction Bid Time Ended=====================");
		etendercomponentobj.tenderLogout();
		etendercomponentobj.tendercreatorLogin();
		auctioncomponentobj.navigateToAuctionPlatform();
		auctioncomponentobj.clickOnPostAuction();
		auctioncomponentobj.searchTheTenderRefNumber();
		auctioncomponentobj.clickOnReleaseLinkUnderActionDropdown();
		auctioncomponentobj.clickOnReleasedAuction();
		auctioncomponentobj.searchTheTenderRefNumber();
		auctioncomponentobj.downloadAuctionSummaryReportPDF();
		auctioncomponentobj.logoutTheAuction();

	}

}
