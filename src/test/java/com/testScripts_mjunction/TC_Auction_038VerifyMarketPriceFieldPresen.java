package com.testScripts_mjunction;

import java.io.File;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.components.AuctionComponent;
import com.components.PostTenderComponent;
import com.components.eTenderComponent;
import com.objectRepository.TenderCreation_Locators;

public class TC_Auction_038VerifyMarketPriceFieldPresen extends BaseClass_Web {

	public eTenderComponent etendercomponentobj = new eTenderComponent(pdfResultReport);
	public TenderCreation_Locators tendercreationobj = new TenderCreation_Locators();
	public PostTenderComponent posttendercomponentobj = new PostTenderComponent(pdfResultReport);
	public AuctionComponent auctioncomponentobj = new AuctionComponent(pdfResultReport);

	public void initializeRepository() throws Exception {
		reportDetails.put("Test Script Name", this.getClass().getSimpleName());
		reportDetails.put("Test Script MyWorksshop Document ID", "Doc1234567");
		reportDetails.put("Test Script Revision No", "38");
		reportDetails.put("Test Author Name", "Pooja");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}

	@Parameters("TestcaseNo")
	@Test(description = "TC_Auction_038:Verify Market Price field is present after or before bid submission for Auction Controller for Rank bidding")
  public void validateAlertEventAlreadyExistedAndAuctionRuleInEventColumnTest(String no) throws Throwable {
	  System.out.println("Entered in the Test method..................");
	  try {
		pdfResultReport.readTestDataFile(System.getProperty("user.dir").replace("\\", "/")	+ "/Resources/TenderCreation_CreateNewRFQ_TestData_pooja.xls", no);
	} catch (Exception e) {
		System.out.println("Unable to read the data from excel file");
	}
	  initializeRepository();
	  String currentUsersHomeDir = System.getProperty("user.dir");
      String otherFolder = currentUsersHomeDir + File.separator + "Resources" + File.separator + "rfqExcel";
	  
	 	  
	  String decvalue="1000";
	  auctioncomponentobj.DeleteFolder(otherFolder);
	  etendercomponentobj.openURL();
	  etendercomponentobj.tendercreatorLogin();
	  
	  auctioncomponentobj.navigateToTenderListInitiateAuctionPage();
	  posttendercomponentobj.enterCompleted_TenderId();
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
	  auctioncomponentobj.enterMandatoryFieldsInEventDetailPage("2000000", decvalue, "RANK BIDDING-SAME SBP");
	  auctioncomponentobj.clickSaveAndSchedule();
	  auctioncomponentobj.selectRespectiveEventRowAndPublishTheEvent(1);
	  auctioncomponentobj.ScheduleTheEvent(1, 2, 12);
	  auctioncomponentobj.clickSavescheduleactivateBtn();
	  auctioncomponentobj.logoutTheAuction();
	  String startTime=etendercomponentobj.getDataFromPropertiesFile("AuctionStartDate");
	  auctioncomponentobj.WaititngForBiDTimeMatching(startTime);
	  System.out.println("===============passed Tender Creation=====================");
	  
	  
	  etendercomponentobj.openURL();
	  String AuctionDate =eTenderComponent.getDataFromPropertiesFile("AuctionStartDate");
	  
	  etendercomponentobj.tendercreatorLogin();
	  auctioncomponentobj.navigateToAuctionPlatform();
	  auctioncomponentobj.clickOnAuctionListForController();
	  auctioncomponentobj.VerifyMarketPriceFieldIsPresentBeforeBidSubmission(decvalue);
	  auctioncomponentobj.logoutTheAuction();
	  
	  etendercomponentobj.bidder_01_Login();
      auctioncomponentobj.NavigaterToAuctionParcipitation();
	  auctioncomponentobj.Activate_Aution(AuctionDate);
	  auctioncomponentobj.ParticipateInLiveAuction(decvalue, 1);
	  etendercomponentobj.tenderLogout();
	  
	  etendercomponentobj.bidder_02_Login();
	  auctioncomponentobj.NavigaterToAuctionParcipitation();
	  auctioncomponentobj.Activate_Aution(AuctionDate);
	  auctioncomponentobj.ParticipateInLiveAuction(decvalue, 3);
	  etendercomponentobj.tenderLogout();
	  
	  etendercomponentobj.bidder_01_Login();
	  auctioncomponentobj.NavigaterToAuctionParcipitation();
	  auctioncomponentobj.ParticipateInLiveAuction(decvalue, 5);
	  etendercomponentobj.tenderLogout();
	  
	  etendercomponentobj.tendercreatorLogin();
	  auctioncomponentobj.navigateToAuctionPlatform();
	  auctioncomponentobj.clickOnAuctionListForController();
	  auctioncomponentobj.VerifyMarketPriceFieldIsPresentAfterBidSubmission(decvalue);
	  auctioncomponentobj.logoutTheAuction();
	  
	  String endTime = etendercomponentobj.getDataFromPropertiesFile("AuctionEndDate");	
	  auctioncomponentobj.WaititngForBiDTimeMatching(endTime);
	  System.out.println("===============passed Bidding=====================");
	  
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
