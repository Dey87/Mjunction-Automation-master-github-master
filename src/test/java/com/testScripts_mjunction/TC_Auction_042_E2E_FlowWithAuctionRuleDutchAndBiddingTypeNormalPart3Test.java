package com.testScripts_mjunction;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.components.AuctionComponent;
import com.components.PostTenderComponent;
import com.components.eTenderComponent;
import com.objectRepository.TenderCreation_Locators;

public class TC_Auction_042_E2E_FlowWithAuctionRuleDutchAndBiddingTypeNormalPart3Test extends BaseClass_Web {

	public eTenderComponent etendercomponentobj = new eTenderComponent(pdfResultReport);
	public TenderCreation_Locators tendercreationobj = new TenderCreation_Locators();
	public PostTenderComponent postTenderComponentobj = new PostTenderComponent(pdfResultReport);
	public AuctionComponent auctioncomponentobj = new AuctionComponent(pdfResultReport);

	public void initializeRepository() throws Exception {
		reportDetails.put("Test Script Name", this.getClass().getSimpleName());
		reportDetails.put("Test Script MyWorksshop Document ID", "Doc1234567");
		reportDetails.put("Test Script Revision No", "42");
		reportDetails.put("Test Author Name", "venkatesh");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}

	@Parameters("TestcaseNo")
	@Test(description = "TC_Auction_042_E2E_FlowWithAuctionRuleDutchAndBiddingTypeNormalPart3Test")
	public void VerifyAuctionBidSubmissionTimeExtensionProcess(String no) throws Throwable {
		System.out.println("Entered in the Test method..................");
		try {
			pdfResultReport.readTestDataFile(System.getProperty("user.dir").replace("\\", "/")
					+ "/Resources/TenderCreation_CreateNewRFQ_TestData_venkateshNew.xls", no);
		} catch (Exception e) {
			System.out.println("Unable to read the data from excel file");
		}
		initializeRepository();

		String decvalue = "4000";

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
				"DUTCH AUCTION", "4100000", 9, 10, 11);
		
		auctioncomponentobj.selectAllBidField("Yes");
		
		auctioncomponentobj.enterhardStopTime(30);
		
		auctioncomponentobj.validateTwoMidderAreSelectedByDefault();
		
		auctioncomponentobj.clickSaveAndSchedule();
		
		auctioncomponentobj.selectRespectiveEventRowAndPublishTheEvent(1);
		auctioncomponentobj.ScheduleTheEvent(1, 2, 6);
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
		
		auctioncomponentobj.returnbackToEPSFromAuctionBid();
		
		etendercomponentobj.tenderLogout();
		
		
		etendercomponentobj.bidder_02_Login();
		
		auctioncomponentobj.NavigaterToAuctionParcipitation();

		auctioncomponentobj.Activate_Aution(AuctionDate);

		auctioncomponentobj.clickOnLiveAuction();
		
		
		String endTime = etendercomponentobj.getDataFromPropertiesFile("AuctionEndDate");
		
		auctioncomponentobj.WaititngForBiDTimeMatching(endTime);

		System.out.println("===============Auction Bid Time Ended=====================");

		auctioncomponentobj.WaititngForAuctionPauseTimeMatching(
				etendercomponentobj.getDataFromPropertiesFile("DucthAuctionPauseTime"), decvalue);
		
		System.out.println("===============Auction Pause Time Ended=====================");
		
		auctioncomponentobj.WaititngForAuctionResumeTimeMatching(
				etendercomponentobj.getDataFromPropertiesFile("DucthAuctionResumeTime"), decvalue);
		
		System.out.println("===============Auction Resume Time Ended=====================");

		auctioncomponentobj.WaititngForAuctionExtendedTimeMatching(
				etendercomponentobj.getDataFromPropertiesFile("DucthAuctionExtendedTime"), decvalue);

		System.out.println("===============Auction Extended Time Ended=====================");
		
		
	   auctioncomponentobj.automaticallyNewBidValueWillBeIncrmentingWithPriceChangeInterval(decvalue,"4108000");
	   
		
       auctioncomponentobj.returnbackToEPSFromAuctionBid();
		
	   etendercomponentobj.tenderLogout();
	   
		
	    etendercomponentobj.bidder_01_Login();
	    
		auctioncomponentobj.NavigaterToAuctionParcipitation();

		auctioncomponentobj.clickOnLiveAuction();
		 
		auctioncomponentobj.automaticallyNewBidValueWillBeIncrmentingWithPriceChangeInterval(decvalue,"4132000");
		 
		auctioncomponentobj.ParticipateInLiveAuctionAndSubmitBid(decvalue);
		
		
		etendercomponentobj.tenderLogout();
		
		   
		
	}

}
