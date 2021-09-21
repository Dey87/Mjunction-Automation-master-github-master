package com.testScripts_mjunction;

import java.io.File;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.components.AuctionComponent;
import com.components.PostTenderComponent;
import com.components.eTenderComponent;
import com.objectRepository.TenderCreation_Locators;

public class TC_Auction_002E2E_FLowAuctionRuleEnglishNoTiesAndBidddingLOTPrice extends BaseClass_Web {

	public eTenderComponent etendercomponentobj = new eTenderComponent(pdfResultReport);
	public TenderCreation_Locators tendercreationobj = new TenderCreation_Locators();
	public PostTenderComponent posttendercomponentobj = new PostTenderComponent(pdfResultReport);
	public AuctionComponent auctioncomponentobj = new AuctionComponent(pdfResultReport);

	public void initializeRepository() throws Exception {
		reportDetails.put("Test Script Name", this.getClass().getSimpleName());
		reportDetails.put("Test Script MyWorksshop Document ID", "Doc1234567");
		reportDetails.put("Test Script Revision No", "02");
		reportDetails.put("Test Author Name", "Pooja");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}

	@Parameters("TestcaseNo")
	@Test(description = "TC_Auction_002:Verify End to End flow with Auction Rule as 'English-No Ties' and bidding type 'LOT_Price'")
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
	  
	 	  
	  String decvalue="2000";
	  auctioncomponentobj.DeleteFolder(otherFolder);
	  etendercomponentobj.openURL();
	 etendercomponentobj.tendercreatorLogin();
	  
	  auctioncomponentobj.navigateToTenderListInitiateAuctionPage();
	  posttendercomponentobj.enterCompleted_TenderId();
	  auctioncomponentobj.clickinitiateAutionFromActionMenu();
	  auctioncomponentobj.SelectAuctionType("REVERSE AUCTION");
	  auctioncomponentobj.SelectAuctionBiddingType("LOT-Itemwise (Price X Qty)");
	  auctioncomponentobj.selectTemplateName("BOQ (Mandatory)");
	  auctioncomponentobj.selectItemCode("Item Code");
	  auctioncomponentobj.selectItemCodeDescription("Item Description");
	  auctioncomponentobj.selectUOM("U.O.M");
	  auctioncomponentobj.selectQuantity("Supplier Quoted Quantity");
	  auctioncomponentobj.selectStartBidPrice("Negotiated Total (Exclusive of Tax)(AED)");
	  auctioncomponentobj.clickInitiateAuctionFromPopUp();
	  auctioncomponentobj.clickViewEvent();
	  auctioncomponentobj.clickSetUpAuction(1);
	  auctioncomponentobj.enterMandatoryFieldsInEventDetailPage("2000000", decvalue, "ENGLISH NO-TIES");
	  auctioncomponentobj.clickSaveAndSchedule();
	  auctioncomponentobj.selectRespectiveEventRowAndPublishTheEvent(1);
	  auctioncomponentobj.ScheduleTheEvent(1, 2, 10);
	  auctioncomponentobj.clickSavescheduleactivateBtn();
	  auctioncomponentobj.logoutTheAuction();
	  String startTime=etendercomponentobj.getDataFromPropertiesFile("AuctionStartDate");
	  auctioncomponentobj.WaititngForBiDTimeMatching(startTime);
	  System.out.println("===============passed Tender Creation=====================");
	  
	  
	  etendercomponentobj.openURL();
	  String AuctionDate =eTenderComponent.getDataFromPropertiesFile("AuctionStartDate");
	 etendercomponentobj.bidder_01_Login();
      auctioncomponentobj.NavigaterToAuctionParcipitation();
	  auctioncomponentobj.Activate_Aution(AuctionDate);
	  auctioncomponentobj.clickOnDetailsButton(decvalue);
	  auctioncomponentobj.settingNewBidAsRateWise("4500000","4500000","4500000");
	  etendercomponentobj.tenderLogout();
	  
	  etendercomponentobj.bidder_02_Login();
	  auctioncomponentobj.NavigaterToAuctionParcipitation();
	  auctioncomponentobj.Activate_Aution(AuctionDate);
	  auctioncomponentobj.clickOnDetailsButton(decvalue);
	  auctioncomponentobj.settingNewBidAsRateWise("4500000","4500000","4498000");
	  etendercomponentobj.tenderLogout();
	  
	  etendercomponentobj.bidder_01_Login();
	  auctioncomponentobj.NavigaterToAuctionParcipitation();
	 
	  auctioncomponentobj.clickOnDetailsButton(decvalue);
	  auctioncomponentobj.settingNewBidAsRateWise("4500000","4498000","4498000");
	  etendercomponentobj.tenderLogout();
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
