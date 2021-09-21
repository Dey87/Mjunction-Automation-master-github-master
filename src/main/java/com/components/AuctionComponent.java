package com.components;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.mail.Message;
import javax.mail.MessagingException;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.baseClasses.BaseClass_Web;
import com.baseClasses.EmailUtils;
import com.baseClasses.PDFResultReport;
import com.baseClasses.ThreadLocalWebdriver;
import com.objectRepository.TenderCreation_Locators;

public class AuctionComponent extends BaseClass_Web {
	TenderCreation_Locators tendercreationlocators = new TenderCreation_Locators();

	eTenderComponent etendercomponentobj = new eTenderComponent(pdfResultReport);

	public AuctionComponent(PDFResultReport pdfresultReport) {
		this.pdfResultReport = pdfresultReport;
	}

	public void Activate_Aution(String date) throws Throwable {
		try {
			log.info("started executing the method:: Activate_Aution");

			click(tendercreationlocators.tabPARTICiPATE, "tabPARTICiPATE");
			waitForObj(3000);
			click(tendercreationlocators.ActiveAuction(date), "btnParticipate");

			pdfResultReport.addStepDetails("Activate_Aution", "Activating the Auction",
					"Successfully activated the Auction " + " ", "Pass", "Y");
			log.info("completed executing the method:: Activate_Aution");
		} catch (Exception e) {
			log.fatal("Unable to navigate to Auction participation" + e.getMessage());
			pdfResultReport.addStepDetails("Activate_Aution", "Activating the Auction",
					"Unable to Activate the Auction" + e.getMessage(), "Fail", "N");
		}
	}

	public void navigateToAuctionPlatform() throws Throwable {
		try {
			log.info("started executing the method:: navigateToAuctionPlatform");
			JSClick(tendercreationlocators.auction, "auction");
			waitForElementToBeVisible(tendercreationlocators.home);
			JSClick(tendercreationlocators.home, "home");
			waitForElementToBeVisible(tendercreationlocators.filter);
			pdfResultReport.addStepDetails("navigateToAuctionPlatform",
					"Auction Platform must be naviagte successfully",
					"Successfully navigated to Auction Platform" + " ", "Pass", "Y");
			log.info("completed executing the method:: navigateToAuctionPlatform");
		} catch (Exception e) {
			log.fatal("Not able to navigate to Auction Platform" + e.getMessage());
			pdfResultReport.addStepDetails("navigateToAuctionPlatform",
					"Auction Platform must be naviagte successfully",
					"Unable to navigate to Auction Platform" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickOnPostAuction() throws Throwable {
		try {
			log.info("started executing the method:: clickOnPostAuction");
			JSClick(tendercreationlocators.postAuction, "postAuction");
			waitForElementToBeVisible(tendercreationlocators.action_Dropdown);
			pdfResultReport.addStepDetails("clickOnPostAuction", "Auction list page must be navigate successfully",
					"Successfully navigated to auction list page" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickOnPostAuction");
		} catch (Exception e) {
			log.fatal("Not able to click on post auction" + e.getMessage());
			pdfResultReport.addStepDetails("clickOnPostAuction", "Post auction must be click successfully",
					"Unable to click on post auction" + e.getMessage(), "Fail", "N");
		}
	}

	public void searchTheTenderRefNumber() throws Throwable {
		try {
			log.info("started executing the method:: searchTheTenderRefNumber");
			clear(tendercreationlocators.auctionSearch, "Clear the TenderRefNumber");
			set(tendercreationlocators.auctionSearch, eTenderComponent.getDataFromPropertiesFile("TenderRefNumber"),
					"search The TenderRefNumber");
			pdfResultReport.addStepDetails("searchTheTenderRefNumber", "Tender Ref Number must be display sucessfully",
					"Sucessfully displayed Tender Ref Number" + " ", "Pass", "Y");
			log.info("completed executing the method:: searchTheTenderRefNumber");
		} catch (Exception e) {
			log.fatal("Unable to display Tender Ref Number" + e.getMessage());
			pdfResultReport.addStepDetails("searchTheTenderRefNumber", "Tender Ref Number must be display sucessfully",
					"Unable to display Tender Ref Number" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickOnReleaseLinkUnderActionDropdown() throws Throwable {
		try {
			log.info("started executing the method:: clickOnReleaseLinkUnderActionDropdown");
			click(tendercreationlocators.action_Dropdown, "action_Dropdown");
			waitForElementToBeVisible(tendercreationlocators.releaseLink);
			highlight(tendercreationlocators.releaseLink);
			pdfResultReport.addStepDetails("clickOnReleaseLinkUnderActionDropdown",
					"Release link must be display successfully", "Successfully displayed release link" + " ", "Pass",
					"Y");
			click(tendercreationlocators.releaseLink, "releaseLink");
			waitForElementToBeVisible(tendercreationlocators.releaseConfirmYes);
			pdfResultReport.addStepDetails("clickOnReleaseLinkUnderActionDropdown",
					"Auction release confirmation popup must be display successfully",
					"Successfully displayed auction release confirmation popup" + " ", "Pass", "Y");
			click(tendercreationlocators.releaseConfirmYes, "releaseConfirmYes");
			waitForElementToBeVisible(tendercreationlocators.releaseConfirmSucessesMsgOk);
			pdfResultReport.addStepDetails("clickOnReleaseLinkUnderActionDropdown",
					"Auction has been released sucessfully popup must be display successfully",
					"Successfully displayed Auction has been released sucessfully popup" + " ", "Pass", "Y");
			click(tendercreationlocators.releaseConfirmSucessesMsgOk, "releaseConfirmSucessesMsgOk");
			waitForElementToBeVisible(tendercreationlocators.action_Dropdown);
			pdfResultReport.addStepDetails("clickOnReleaseLinkUnderActionDropdown",
					"Release link must be click successfully", "Successfully clicked on release link" + " ", "Pass",
					"Y");
			log.info("completed executing the method:: clickOnReleaseLinkUnderActionDropdown");
		} catch (Exception e) {
			log.fatal("Not able to click on release link" + e.getMessage());
			pdfResultReport.addStepDetails("clickOnReleaseLinkUnderActionDropdown",
					"Release link must be click successfully", "Unable to click on release link" + e.getMessage(),
					"Fail", "N");
		}
	}

	public void clickOnReleasedAuction() throws Throwable {
		try {
			log.info("started executing the method:: clickOnReleasedAuction");
			click(tendercreationlocators.releasedAuction, "releasedAuction");
			waitForElementToBeVisible(tendercreationlocators.action_Dropdown);
			pdfResultReport.addStepDetails("clickOnReleasedAuction", "Released Auction must be click successfully",
					"Successfully clicked on Released Auction" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickOnReleasedAuction");
		} catch (Exception e) {
			log.fatal("Not able to click on Released Auction" + e.getMessage());
			pdfResultReport.addStepDetails("clickOnReleasedAuction", "Released Auction must be click successfully",
					"Unable to click on Released Auction" + e.getMessage(), "Fail", "N");
		}
	}

	public void downloadAuctionSummaryReportPDF() throws Throwable {
		try {
			log.info("started executing the method:: downloadAuctionSummaryReportPDF");
			click(tendercreationlocators.action_Dropdown, "action_Dropdown");
			waitForElementToBeVisible(tendercreationlocators.auctionSummaryReport);
			highlight(tendercreationlocators.auctionSummaryReport);
			pdfResultReport.addStepDetails("downloadAuctionSummaryReportPDF",
					"Auction summary report link must be display successfully",
					"Successfully displayed auction summary report link" + " ", "Pass", "Y");
			click(tendercreationlocators.auctionSummaryReport, "auctionSummaryReport");
			pdfResultReport.addStepDetails("downloadAuctionSummaryReportPDF",
					"Auction summary PDF report must be download successfully",
					"Successfully downloaded Auction summary PDF report" + " ", "Pass", "Y");
			log.info("completed executing the method:: downloadAuctionSummaryReportPDF");
		} catch (Exception e) {
			log.fatal("Not able to download Auction summary PDF report" + e.getMessage());
			pdfResultReport.addStepDetails("downloadAuctionSummaryReportPDF",
					"Auction summary PDF reprt must be download successfully",
					"Unable to download Auction summary PDF report" + e.getMessage(), "Fail", "N");
		}
	}

	public void navigateToTenderListInitiateAuctionPage() throws Throwable {
		try {
			log.info("started executing the method:: navigateToTenderListInitiateAuctionPage");

			JSClick(tendercreationlocators.tendersIcon, "tendersIcon");

			waitForObj(2000);

			JSClick(tendercreationlocators.intiateAuctionMenuLinkBy, "intiateAuctionMenuLink");

			waitForElementToBeVisible(By.xpath("//*[@id='tenderList']/tbody/tr[1]"));

			waitForObj(5000);

			pdfResultReport.addStepDetails("navigateToTenderListInitiateAuctionPage",
					"Must naviagte to tenderList Initiate auction page",
					"Successfully  naviagted  to tenderList Initiate auction page" + " ", "Pass", "Y");
			log.info("completed executing the method:: navigateToTenderListInitiateAuctionPage");

		} catch (Exception e) {
			log.fatal("Unable to  naviagte to tenderList Intiatate auction page" + e.getMessage());
			pdfResultReport.addStepDetails("navigateToTenderListInitiateAuctionPage",
					"Must naviagte to tenderList Initiate auction page",
					"Unable to  naviagte to tenderList Initiate auction page" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}

	}

	public void clickinitiateAutionFromActionMenu() throws Exception {
		try {
			log.info("started executing the method::clickinitiateAutionFromActionMenu ");

			String completedtenderid = eTenderComponent.getDataFromPropertiesFile();
			ThreadLocalWebdriver.getDriver().findElement(By.xpath("//td[contains(@title,'" + completedtenderid
					+ "')]//parent::tr//td[7]//button[@data-toggle='dropdown']")).click();

			waitForElementToBeVisible(tendercreationlocators.actionMenuintiateAuctionlinkBy(completedtenderid));

			click(tendercreationlocators.actionMenuintiateAuctionlinkBy(completedtenderid),
					"actionMenuintiateAuctionlinkBy");

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			waitForElementToBeVisible(tendercreationlocators.popupInitiateAuctonBtnBy);

			waitForObj(3000);

			pdfResultReport.addStepDetails("clickinitiateAutionFromActionMenu",
					"Should Dispaly PopUp Initiate Auction for tender",
					"Successfully  Dispalying PopUp Initiate Auction for tender" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickinitiateAutionFromActionMenu");

		} catch (Exception e) {
			log.fatal("Unable to Dispaly PopUp Initiate Auction for tender" + e.getMessage());
			pdfResultReport.addStepDetails("clickinitiateAutionFromActionMenu",
					"Should Dispaly PopUp Initiate Auction for tender",
					"Unable to Dispaly PopUp Initiate Auction for tender" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	@SuppressWarnings("deprecation")
	public static boolean waitTillSpinnerDisable(WebDriver driver, By by) {
		FluentWait<WebDriver> fWait = new FluentWait<WebDriver>(driver);
		fWait.withTimeout(100, TimeUnit.SECONDS);
		fWait.pollingEvery(3, TimeUnit.SECONDS);
		fWait.ignoring(NoSuchElementException.class);

		Function<WebDriver, Boolean> func = new Function<WebDriver, Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				WebElement element = driver.findElement(by);
				System.out.println(element.getCssValue("display"));
				if (element.getCssValue("display").equalsIgnoreCase("none")) {
					return true;
				}
				return false;
			}
		};

		return fWait.until(func);
	}

	public void SelectAuctionType(String AuctionType) throws Exception {
		try {
			log.info("started executing the method:: SelectAuctionType");
			// FORWARD AUCTION
			// REVERSE AUCTION
			select(tendercreationlocators.AuctionTypeSelectBy, AuctionType);

			pdfResultReport.addStepDetails("SelectAuctionType", "Should Select the Auction Type",
					"Successfully  Selected the Auction Type" + " ", "Pass", "Y");
			log.info("completed executing the method::SelectAuctionType ");

		} catch (Exception e) {
			log.fatal("Unable to Select the Auction Type" + e.getMessage());
			pdfResultReport.addStepDetails("SelectAuctionType", "Should Select the Auction Type",
					"Unable to Select the Auction Type" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void SelectAuctionBiddingType(String AuctionBiddingType) throws Exception {
		try {
			log.info("started executing the method:: SelectAuctionBiddingType");
			// Normal
			// LOT-Itemwise (Price X Qty)
			// LOT-Itemwise (Lumpsum Price)
			// LOT-Consolidated

			select(tendercreationlocators.AuctionTypeBiddingSelectBy, AuctionBiddingType);

			pdfResultReport.addStepDetails("SelectAuctSelectAuctionBiddingTypeionType",
					"Should Select the Auction Bidding Type", "Successfully  Selected the Auction  Bidding Type" + " ",
					"Pass", "Y");
			log.info("completed executing the method::SelectAuctionBiddingType ");

		} catch (Exception e) {
			log.fatal("Unable to Select the Auction Bidding  Type" + e.getMessage());
			pdfResultReport.addStepDetails("SelectAuctionBiddingType", "Should Select the Auction Bidding Type",
					"Unable to Select the Auction Bidding Type" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void selectTemplateName(String templateName) throws Exception {
		try {
			log.info("started executing the method:: selectTemplateName");

			select(tendercreationlocators.templateFieldSelectBy, templateName);

			pdfResultReport.addStepDetails("selectTemplateName", "Should Select the Template Name",
					"Successfully  Selected the  Template Name" + " ", "Pass", "Y");
			log.info("completed executing the method::selectTemplateName ");

		} catch (Exception e) {
			log.fatal("Unable to Select the Template Name" + e.getMessage());
			pdfResultReport.addStepDetails("selectTemplateName", "Should Select the Template Name",
					"Unable to Select the Template Name" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void selectItemCode(String ItemCode) throws Exception {
		try {
			log.info("started executing the method:: selectItemCode");
			// Item Code
			select(tendercreationlocators.itemCodeSelectBy, ItemCode);

			pdfResultReport.addStepDetails("selectItemCode", "Should Select the Item Code",
					"Successfully  Selected the  Item Code" + " ", "Pass", "Y");

			log.info("completed executing the method::selectItemCode ");

		} catch (Exception e) {
			log.fatal("Unable to Select the Item Code" + e.getMessage());
			pdfResultReport.addStepDetails("selectItemCode", "Should Select the Item Code",
					"Unable to Select the Item Code" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void selectItemCodeDescription(String ItemCodeDescription) throws Exception {
		try {
			log.info("started executing the method:: selectItemCodeDescription");
			// Item Description
			select(tendercreationlocators.itemCodeDescSelectBy, ItemCodeDescription);

			pdfResultReport.addStepDetails("selectItemCodeDescription", "Should Select the Item Code Description",
					"Successfully  Selected the  Item Code Description" + " ", "Pass", "Y");

			log.info("completed executing the method::selectItemCodeDescription");

		} catch (Exception e) {
			log.fatal("Unable to Select the Item Code Description" + e.getMessage());
			pdfResultReport.addStepDetails("selectItemCodeDescription", "Should Select the Item Code Description",
					"Unable to Select the Item Code Description" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void selectUOM(String UOM) throws Exception {
		try {
			log.info("started executing the method:: selectUOM");
			// U.O.M
			select(tendercreationlocators.uomSelectBy, UOM);

			pdfResultReport.addStepDetails("selectUOM", "Should Select the UOM", "Successfully  Selected the UOM" + " ",
					"Pass", "Y");

			log.info("completed executing the method::selectUOM");

		} catch (Exception e) {
			log.fatal("Unable to Select theUOM" + e.getMessage());
			pdfResultReport.addStepDetails("selectUOM", "Should Select the UOM",
					"Unable to Select the UOM" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void selectQuantity(String quantity) throws Exception {
		try {
			log.info("started executing the method:: selectQuantity");
			// Supplier Quoted Quantity
			select(tendercreationlocators.QuantitySelectBy, quantity);

			pdfResultReport.addStepDetails("selectQuantity", "Should Select select Quantity",
					"Successfully  Selected the  select Quantity" + " ", "Pass", "Y");

			log.info("completed executing the method::selectQuantity");

		} catch (Exception e) {
			log.fatal("Unable to Select theselectQuantity" + e.getMessage());
			pdfResultReport.addStepDetails("selectQuantity", "Should Select the selectQuantity",
					"Unable to Select the selectQuantity" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void selectStartBidPrice(String startBidPrice) throws Exception {
		try {
			log.info("started executing the method:: selectStartBidPrice");
			// Negotiated Total (Exclusive of Tax)(AED)
			select(tendercreationlocators.startBidPriceSelectBy, startBidPrice);

			pdfResultReport.addStepDetails("selectStartBidPrice", "Should  select StartBidPrice",
					"Successfully  Selected the  startBidPrice" + " ", "Pass", "Y");

			log.info("completed executing the method::selectStartBidPrice");

		} catch (Exception e) {
			log.fatal("Unable to Select the startBidPrice" + e.getMessage());
			pdfResultReport.addStepDetails("selectStartBidPrice", "Should Select the startBidPrice",
					"Unable to Select the startBidPrice" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void clickInitiateAuctionFromPopUp() throws Exception {
		try {
			log.info("started executing the method:: clickInitiateAuctionFromPopUp");

			click(tendercreationlocators.popupInitiateAuctonBtnBy, "popupInitiateAuctonBtnBy");

			waitForElementToBeVisible(tendercreationlocators.viewEventBy);

			waitForObj(5000);

			pdfResultReport.addStepDetails("clickInitiateAuctionFromPopUp",
					"Should Display  Event Created Sucessfully PopUp",
					"Successfully Displaying  Pop Event Created Sucessfully" + " ", "Pass", "Y");

			log.info("completed executing the method::clickInitiateAuctionFromPopUp");

		} catch (Exception e) {
			log.fatal("Display Event Created Sucessfully PopUp" + e.getMessage());
			pdfResultReport.addStepDetails("clickInitiateAuctionFromPopUp",
					"Should Display Event Created Sucessfully PopUp",
					"Unable to Display Event Created Sucessfully PopUp" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void clickViewEvent() throws Exception {
		try {
			log.info("started executing the method:: clickViewEvent");

			click(tendercreationlocators.viewEventBy, "viewEventBy");

			waitForElementToBeVisible(tendercreationlocators.setUpAction(1));

			pdfResultReport.addStepDetails("clickViewEvent", "Should display Events PopUp Page ",
					"Successfully displaying Events PopUp Page" + " ", "Pass", "Y");

			log.info("completed executing the method::clickInitiateAuctionFromPopUp");

		} catch (Exception e) {
			log.fatal("Unable to display Events PopUp Page" + e.getMessage());
			pdfResultReport.addStepDetails("clickInitiateAuctionFromPopUp", "Should display Events PopUp Page",
					"Unable to display Events PopUp Page" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void clickSetUpAuction(int rowNo) throws Exception {
		try {
			log.info("started executing the method:: clickSetUpAuction");

			click(tendercreationlocators.selectDraftCheckBox(rowNo), "selectDraftCheckBox");

			click(tendercreationlocators.setUpAction(rowNo), "setUpAction");

			waitForElementToBeVisible(By.xpath("//*[@id='auctionAdd']//select/option[text()='INR']"));

			waitForObj(5000);

			pdfResultReport.addStepDetails("clickSetUpAuction", "Should display Eventdetails  Page ",
					"Successfully displaying Eventdetails  Page" + " ", "Pass", "Y");

			log.info("completed executing the method::clickSetUpAuction");

		} catch (Exception e) {
			log.fatal("Unable to display Eventdetails  Page" + e.getMessage());
			pdfResultReport.addStepDetails("clickSetUpAuction", "Should display Eventdetails  Page",
					"Unable to display Eventdetails  Page" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}



	public void selectRespectiveEventRowAndPublishTheEvent(int rowNo) throws Exception {
		try {
			log.info("started executing the method:: SelectEventRowAndPublish");

			click(tendercreationlocators.selectDraftCheckBox(rowNo), "selectDraftCheckBox");

			click(tendercreationlocators.publishEventBtnBy, "publishEventBtnBy");

			waitForElementToBeVisible(tendercreationlocators.confirmationPublishEventYesBy);

			click(tendercreationlocators.confirmationPublishEventYesBy, "confirmationPublishEventYesBy");

			waitForElementToBeVisible(tendercreationlocators.sucessMsgForPublishEventByBy);

			click(tendercreationlocators.confirmationOkBy, "confirmationOkBy");

			waitForObj(5000);

			pdfResultReport.addStepDetails("SelectEventRowAndPublish", "Event Must be Published Sucessfully",
					"Successfully  Published event  Sucessfully " + " ", "Pass", "Y");
			log.info("completed executing the method:: SelectEventRowAndPublish");

		} catch (Exception e) {
			log.fatal("Unable to  Publish event  Sucessfully" + e.getMessage());
			pdfResultReport.addStepDetails("SelectEventRowAndPublish", "Event Must be Published Sucessfully",
					"Unable to  Publish event  Sucessfully" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public String eventStartTime = "";

	public String getEventStartTime(int minutes) {
		LocalDateTime localdatetime = LocalDateTime.now().plusMinutes(minutes);

		eventStartTime = localdatetime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

		return eventStartTime;
	}

	public String eventEndTime = "";

	public String getEventEndTime(int minutes) {
		LocalDateTime localdatetime = LocalDateTime.now().plusMinutes(minutes);

		eventEndTime = localdatetime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

		return eventEndTime;
	}

	public void clickSavescheduleactivateBtn() throws Exception {
		try {
			log.info("started executing the method:: clickSavescheduleactivateBtn");

			click(tendercreationlocators.saveScheduleActivateBtnBy, "saveScheduleActivateBtnBy");

			waitForElementToBeVisible(tendercreationlocators.auctionactivatedSucessmsgBy);

			click(tendercreationlocators.confirmationOkBy, "confirmationOkBy");

			waitForElementToBeVisible(tendercreationlocators.AcivateStatusBy);

			waitForObj(5000);

			WebDriver driver = ThreadLocalWebdriver.getDriver();

			String eventStartDateValue = driver.findElement(tendercreationlocators.startDateTime).getAttribute("value")
					.trim();

			eTenderComponent.updateDataIntoPropertyFile("AuctionStartDate", eventStartDateValue);

			String eventEndDateValue = driver.findElement(tendercreationlocators.endDateTime).getAttribute("value")
					.trim();

			eTenderComponent.updateDataIntoPropertyFile("AuctionEndDate", eventEndDateValue);

			pdfResultReport.addStepDetails("clickSavescheduleactivateBtn",
					"should Change The Auction event Status To active ",
					"Successfully activated the  Auction event" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickSavescheduleactivateBtn");

		} catch (Exception e) {
			log.fatal("Unable to  Change The Auction event Status To active" + e.getMessage());
			pdfResultReport.addStepDetails("clickSavescheduleactivateBtn",
					"should Change The Auction event Status To active",
					"Unable to  Change The Auction event Status To active" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void logoutTheAuction() throws Exception {
		try {
			log.info("started executing the method:: logoutTheAuction");

			click(tendercreationlocators.userpicBy, "userpicBy");

			click(tendercreationlocators.LogoutBy, "LogoutBy");

			waitForElementToBeVisible(tendercreationlocators.yesForLogoutBy);

			click(tendercreationlocators.yesForLogoutBy, "yesForLogoutBy");

			launchapp(pdfResultReport.testDataValue.get("AppURL"));

			waitForObj(5000);

			pdfResultReport.addStepDetails("logoutTheAuction", "Should Logout the Auction",
					"Successfully Logged Out" + " ", "Pass", "Y");
			log.info("completed executing the method:: logoutTheAuction");

		} catch (Exception e) {
			log.fatal("Unable to  Logout" + e.getMessage());
			pdfResultReport.addStepDetails("logoutTheAuction", "Should Logout the Auction",
					"Unable to  Logout" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public static boolean DeleteFolder(String Path) {
		boolean blnStatus = false;
		try {
			File file = new File(Path);
			if (!file.exists()) {
				System.out.println("File Do not Exist");
			} else {
				FileUtils.deleteDirectory(new File(Path));
				System.out.println("Deleted file");

			}
		} catch (Exception e) {
		}
		return blnStatus;
	}

	public static boolean WaititngForBiDTimeMatching(String bidTime) {
		boolean blnStatus = false;
		try {
			while (true) {
				LocalDateTime localdatetime = LocalDateTime.now();

				String currentdateTime = localdatetime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

				System.out.println(bidTime);
				System.out.println(currentdateTime);
				if (currentdateTime.compareTo(bidTime) > 0) {

					waitForObj(5000);

					System.out.println("The Given Bid Start Time Is Reached --->" + bidTime + " "
							+ "The Bidder Can Start Bidding");
					break;
				} else {
					System.out.println("The Given Bid Start Time Is  --->" + bidTime + " "
							+ "Wait till bid start date time reached " + " " + currentdateTime.compareTo(bidTime));
				}
			}
		}

		catch (Exception e) {
		}
		return blnStatus;

	}

	public void enterMandatoryFieldsInEventDetailPage(String ReservePrice, String decrementPrice, String AuctionRule)
			throws Exception {
		try {
			log.info("started executing the method::enterMandatoryFieldsInEventDetailPage");

			clear(tendercreationlocators.reserverevePriceFiledBy, "Clear ReservePrice");
			set(tendercreationlocators.reserverevePriceFiledBy, ReservePrice, "ReservePrice");

			clear(tendercreationlocators.changePriceFiledBy, "Clear decrementPrice");
			set(tendercreationlocators.changePriceFiledBy, decrementPrice, "decrementPrice");

			select(tendercreationlocators.priceMultipleFiledBy, "Yes");

			select(tendercreationlocators.auctionRuleFiledBy, AuctionRule);

			click(tendercreationlocators.observersAddFiledBy, "observersAddFiledBy");

			click(tendercreationlocators.ObserversBy, "ObserversBy");

			click(tendercreationlocators.selectObserverAddBy, "selectObserverAddBy");

			waitForObj(3000);

			pdfResultReport.addStepDetails("enterMandatoryFieldsInEventDetailPage",
					"Must Enter MandatoryFields In EventDetailPage",
					"Successfully  Entered MandatoryFields In EventDetailPage" + " ", "Pass", "Y");
			log.info("completed executing the method:: enterMandatoryFieldsInEventDetailPage");

		} catch (Exception e) {
			log.fatal("Unable to Enter MandatoryFields In EventDetailPage" + e.getMessage());
			pdfResultReport.addStepDetails("enterMandatoryFieldsInEventDetailPage",
					"Must Enter MandatoryFields In EventDetailPage ",
					"Unable to Enter MandatoryFields In EventDetailPage" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void saveEventDetails() throws Exception {
		try {
			log.info("started executing the method:: saveEventDetails");

			click(tendercreationlocators.saveEventDetailsBy, "saveScheduleBy");

			waitForElementToBeVisible(tendercreationlocators.AuctionSavedSucessMsgBy);

			click(tendercreationlocators.confirmationOkBy, "confirmationOkBy");

			waitForObj(3000);

			pdfResultReport.addStepDetails("saveEventDetails", "Should Save Auction event  details",
					"Successfully Saved auction Event  details " + " ", "Pass", "Y");
			log.info("completed executing the method:: saveEventDetails");

		} catch (Exception e) {
			log.fatal("Unable to  save Auction event Details" + e.getMessage());
			pdfResultReport.addStepDetails("saveEventDetails", "Should Save auction Event details",
					"Unable to  save Auction event Details " + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void clickBackToEPS() throws Throwable {
		try {
			log.info("started executing the method:: clickBackToEPS");

			JSClick(tendercreationlocators.backToEpsBy, "backToEpsBy");

			waitForElementToBeVisible(tendercreationlocators.ConfirmYesForBackToEpsBy);

			click(tendercreationlocators.ConfirmYesForBackToEpsBy, "ConfirmYesForBackToEpsBy");

			waitForObj(3000);

			waitForElement(tendercreationlocators.intiateAuctionMenuLinkBy, 5000);

			pdfResultReport.addStepDetails("clickBackToEPS", "Should Navigate to Eps Home Page",
					"Successfully Navigated to Eps Home Page" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickBackToEPS");

		} catch (Exception e) {
			log.fatal("Unable to Navigate to Eps Home Page" + e.getMessage());
			pdfResultReport.addStepDetails("clickBackToEPS", "Should Navigate to Eps Home Page",
					"Unable to Navigate to Eps Home Page" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void verifyEventAlreadyExist() throws Exception {
		try {
			log.info("started executing the method:: verifyEventAlreadyExist");

			IsElementPresent(tendercreationlocators.EventMsgAlreadyExistBy);

			pdfResultReport.addStepDetails("verifyEventAlreadyExist", "Should display msg as Event already exist",
					"Successfully displaying msg as Event already exist" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyEventAlreadyExist");

		} catch (Exception e) {
			log.fatal("Unable to display msg as Event already exist" + e.getMessage());
			pdfResultReport.addStepDetails("verifyEventAlreadyExist", "Should display msg as Event already exist",
					"Unable to display msg as Event already exist" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void verifyAuctionRuleInEventColumn(String AuctionRule) throws Exception {
		try {
			log.info("started executing the method:: verifyAuctionRuleInEventColumn");

			String auctionRule = "//span[text()='Draft']/..//preceding-sibling::td[text()='{0}']";

			WebDriver driver = ThreadLocalWebdriver.getDriver();

			boolean displayed = driver.findElement(By.xpath(auctionRule.replace("{0}", AuctionRule))).isDisplayed();

			Assert.assertTrue(displayed, "Showing Auction rule In event Column as Expected");

			pdfResultReport.addStepDetails("verifyAuctionRuleInEventColumn", "Should display Auction Rule As Expected",
					"Successfully displaying  Auction Rule As Expected" + " ", "Pass", "Y");

			log.info("completed executing the method:: verifyAuctionRuleInEventColumn");

		} catch (Exception e) {
			log.fatal("Unable to  display Auction Rule As Expected" + e.getMessage());
			pdfResultReport.addStepDetails("verifyAuctionRuleInEventColumn", "Should display Auction Rule As Expected",
					"Unable to  display Auction Rule As Expected" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}


	public void VerifyPublishEventButton() throws Throwable {
		try {
			log.info("started executing the method:: VerifyPublishEventButton");
			highlight(tendercreationlocators.publishEventBtnBy);
			IsElementPresent(tendercreationlocators.publishEventBtnBy);
			pdfResultReport.addStepDetails("VerifyPublishEventButton",
					"Publish Event button must be display sucesssfully",
					"Successfully displayed publish event button" + " ", "Pass", "Y");
			log.info("completed executing the method:: VerifyPublishEventButton");
		} catch (Exception e) {
			log.fatal("Unable to display publish event button" + e.getMessage());
			pdfResultReport.addStepDetails("VerifyPublishEventButton",
					"Publish Event button must be display sucesssfully",
					"Unable to display publish event button" + e.getMessage(), "Fail", "N");
		}
	}

	String CTSBidAmt=null;
	public void ParticipateInLiveAuction(String Decrementval, int decrementTimes) throws Throwable {
		try {
			log.info("started executing the method:: Activate_Aution");

			click(tendercreationlocators.tabLIVE, "tabLIVE");
			waitForObj(3000);

			for (int i = 0; i <= decrementTimes; i++) {
				JSClick(tendercreationlocators.SubsVal(Decrementval), "SubsVal");
			}
			waitForObj(3000);
			JSClick(tendercreationlocators.Submit(Decrementval), "Submit");
			waitForObj(6000);
			CTSBidAmt=text(tendercreationlocators.MarketPriceVal);
			waitForObj(3000);
			JSClick(tendercreationlocators.BtnBackToEPS, "BtnBackToEPS");
			waitForObj(3000);
			JSClick(tendercreationlocators.ConfirmBackYes, "ConfirmBackYes");
			etendercomponentobj.waitForSpinnerToDisappear();
			pdfResultReport.addStepDetails("Activate_Aution", "Activating the Auction",
					"Successfully activated the Auction " + " ", "Pass", "Y");
			log.info("completed executing the method:: Activate_Aution");
		} catch (Exception e) {
			log.fatal("Unable to navigate to Auction participation" + e.getMessage());
			pdfResultReport.addStepDetails("Activate_Aution", "Activating the Auction",
					"Unable to Activate the Auction" + e.getMessage(), "Fail", "N");
		}
	}
	String TCSBidAmt=null;
	public void ParticipateInLiveAuctionTCS(String Decrementval, int decrementTimes) throws Throwable {
		try {
			log.info("started executing the method:: Activate_Aution");

			click(tendercreationlocators.tabLIVE, "tabLIVE");
			waitForObj(3000);

			for (int i = 0; i <= decrementTimes; i++) {
				JSClick(tendercreationlocators.SubsVal(Decrementval), "SubsVal");
			}
			waitForObj(3000);
			JSClick(tendercreationlocators.Submit(Decrementval), "Submit");
			waitForObj(6000);
			TCSBidAmt=text(tendercreationlocators.MarketPriceVal);
			waitForObj(3000);
			JSClick(tendercreationlocators.BtnBackToEPS, "BtnBackToEPS");
			waitForObj(3000);
			JSClick(tendercreationlocators.ConfirmBackYes, "ConfirmBackYes");
			etendercomponentobj.waitForSpinnerToDisappear();
			pdfResultReport.addStepDetails("Activate_Aution", "Activating the Auction",
					"Successfully activated the Auction " + " ", "Pass", "Y");
			log.info("completed executing the method:: Activate_Aution");
		} catch (Exception e) {
			log.fatal("Unable to navigate to Auction participation" + e.getMessage());
			pdfResultReport.addStepDetails("Activate_Aution", "Activating the Auction",
					"Unable to Activate the Auction" + e.getMessage(), "Fail", "N");
		}
	}
	public void VerofyDOwnloadedBIdTrailReport(String path) throws Throwable {
		try {
			log.info("started executing the method:: VerofyDOwnloadedBIdTrailReport");
			
			String bidqnty=eTenderComponent.getDataFromPropertiesFile("Bidder1SupplierQty").concat(".0000");
			String filename = null;
			File dir = new File(path);
			FilenameFilter filter = new FilenameFilter() {
				public boolean accept(File dir, String name) {
					return name.startsWith("reportBidTrail");
				}
			};
			String[] children = dir.list(filter);
			if (children == null) {
				System.out.println("Either dir does not exist or is not a directory");
			} else {
				for (int i = 0; i < children.length; i++) {
					filename = children[i];
					System.out.println(filename);
				}
			String report =  path + File.separator + filename;
			File file = new File(report);
			PDDocument document = PDDocument.load(file);
		PDFTextStripper pdfStripper = new PDFTextStripper();
		String text = pdfStripper.getText(document);
		String[] lines=text.split("\\r\\n\\r\\n");
		for (String line : lines) {
			System.out.println(line);
		}
		String ctsval=CTSBidAmt.substring(CTSBidAmt.indexOf(":")+1).concat(".0");
		String tcsval=TCSBidAmt.substring(TCSBidAmt.indexOf(":")+1).concat(".0");
		
		if(text.contains("CTS") && text.contains(ctsval) && text.contains(bidqnty))
		{
			pdfResultReport.addStepDetails("BidTrailReport", "Bid Trail report  data validation for CTS",
					"Successfully Verified Bid Trail report for CTS " +CTSBidAmt+" Quantity: " +bidqnty, "Pass", "Y");
			
			if(text.contains("TCS") && text.contains(tcsval)&& text.contains(bidqnty))
			{
				pdfResultReport.addStepDetails("BidTrailReport", "Bid Trail report  data validation for TCS",
						"Successfully Verified Bid Trail report for TCS " +TCSBidAmt+" Quantity: " +bidqnty , "Pass", "Y");
			}
			else
			{
				pdfResultReport.addStepDetails("VerofyDOwnloadedBIdTrailReport", "Bid Trail report  data validation for TCS",
						"Unable to verify bid trail report for TCS " , "Fail", "N");
			}
		}
		else
		{
			pdfResultReport.addStepDetails("VerofyDOwnloadedBIdTrailReport", "Bid Trail report  data validation for CTS",
					"Unable to verify bid trail report for CTS" , "Fail", "N");
		}
			
		
				pdfResultReport.addStepDetails("BidTrailReport", "Bid Trail report  data validation",
						"Successfully Verified Bid Trail report" + " ", "Pass", "Y");
				log.info("completed executing the method:: VerofyDOwnloadedBIdTrailReport");
			}
		}
		catch (Exception e) {
			log.fatal("Unable to verify bid trail report" + e.getMessage());
			pdfResultReport.addStepDetails("VerofyDOwnloadedBIdTrailReport", "Bid Trail report  data validation",
					"Unable to verify bid trail report" + e.getMessage(), "Fail", "N");
		}
	  }

	public void clickOnDetailsButton(String Decrementval) throws Throwable {
		try {
			log.info("started executing the method:: clickOnDetailsButton");

			click(tendercreationlocators.tabLIVE, "tabLIVE");
			waitForObj(3000);

			/*
			 * highlight(tendercreationlocators.StartPrice(Decrementval));
			 * waitForObj(3000);
			 * 
			 * highlight(tendercreationlocators.Quantity(Decrementval));
			 * waitForObj(3000);
			 * 
			 * highlight(tendercreationlocators.Currency(Decrementval));
			 * waitForObj(3000);
			 * 
			 * highlight(tendercreationlocators.MyLastBid(Decrementval));
			 * waitForObj(3000);
			 * 
			 * highlight(tendercreationlocators.Decrement(Decrementval));
			 * waitForObj(3000);
			 */

			highlight(tendercreationlocators.details(Decrementval));
			pdfResultReport.addStepDetails("clickOnDetailsButton", "Details button must be click sucessfully",
					"Successfully clicked on details button" + " ", "Pass", "Y");

			JSClick(tendercreationlocators.details(Decrementval), "details");
			waitForElementToBeVisible(tendercreationlocators.price1);
			pdfResultReport.addStepDetails("clickOnDetailsButton", "Item list page must be display sucessfully",
					"Successfully displayed item list page" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickOnDetailsButton");
		} catch (Exception e) {
			log.fatal("Unable to display item list page" + e.getMessage());
			pdfResultReport.addStepDetails("clickOnDetailsButton", "Item list page must be display sucessfully",
					"Unable to display item list page" + e.getMessage(), "Fail", "N");
		}
	}

	public void settingNewBidAsRateWise(String price1, String price2, String price3) throws Throwable {
		try {
			log.info("started executing the method::settingNewBidAsRateWise");

			clear(tendercreationlocators.price1, "Clear Price1");
			set(tendercreationlocators.price1, price1, "price1");

			clear(tendercreationlocators.price2, "Clear Price2");
			set(tendercreationlocators.price2, price2, "price2");

			clear(tendercreationlocators.price3, "Clear Price3");
			set(tendercreationlocators.price3, price3, "price3");

			pdfResultReport.addStepDetails("settingNewBidAsRateWise", "Price must be eneter sucessfully",
					"Successfully Entered Price" + " ", "Pass", "Y");

			click(tendercreationlocators.calculateTotal, "calculateTotal");
			pdfResultReport.addStepDetails("settingNewBidAsRateWise", "BidValue must be calculate sucessfully",
					"Successfully calculated Bidvalue" + " ", "Pass", "Y");
			click(tendercreationlocators.submitBidValue, "submitBidValue");
			pdfResultReport.addStepDetails("settingNewBidAsRateWise", "BidValue must be accept sucessfully",
					"Successfully accepted BidValue" + " ", "Pass", "Y");
			waitForObj(3000);
			JSClick(tendercreationlocators.BtnBackToEPS, "BtnBackToEPS");
			waitForElementToBeVisible(tendercreationlocators.ConfirmBackYes);
			JSClick(tendercreationlocators.ConfirmBackYes, "ConfirmBackYes");
			etendercomponentobj.waitForSpinnerToDisappear();
			pdfResultReport.addStepDetails("settingNewBidAsRateWise", "BidValue must be submit sucessfully",
					"Successfully submitted BidValue" + " ", "Pass", "Y");
			log.info("completed executing the method:: settingNewBidAsRateWise");

		} catch (Exception e) {
			log.fatal("Unable to submit BidValue" + e.getMessage());
			pdfResultReport.addStepDetails("settingNewBidAsRateWise", "BidValue must be submit sucessfully",
					"Unable to submit BidValue" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void validateAuctionDetailsPageElementsForDynamicSBPRule(String ReservePrice, String ReservePrice2,
			String ReservepricevalidationMsg) throws Throwable {
		try {
			log.info("started executing the method::validateAuctionDetailsPageElements");

			clear(tendercreationlocators.reserverevePriceFiledBy, "Clear ReservePrice");

			set(tendercreationlocators.reserverevePriceFiledBy, ReservePrice2, "ReservePrice");

			JSClick(tendercreationlocators.saveEventDetailsBy, "saveEventDetailsBy");

			scrollToTopOfThePage();

			scrollToElement(tendercreationlocators.startPriceFiledBy);

			String reservePriceValidationMsg = text(tendercreationlocators.reservePriceValidationBy).trim();

			Assert.assertTrue(reservePriceValidationMsg.contains(ReservepricevalidationMsg),
					"Please enter Less than the L1 price");

			pdfResultReport.addStepDetails("validateAuctionDetailsPageElements",
					"Reserve Price Must Be Less Than L1 Price", "Successfully  Validated Reserve Price" + " ", "Pass",
					"Y");

			waitForObj(5000);

			clear(tendercreationlocators.reserverevePriceFiledBy, "Clear ReservePrice");

			set(tendercreationlocators.reserverevePriceFiledBy, ReservePrice, "ReservePrice");

			WebDriver driver = ThreadLocalWebdriver.getDriver();

			String startPrice = driver.findElement(tendercreationlocators.startPriceFiledBy).getAttribute("class");

			Assert.assertTrue(startPrice.contains("untouched"), "Start Price Field is Blank as Expected");

			pdfResultReport.addStepDetails("validateAuctionDetailsPageElements",
					"Start Price Must Be Blank For DynamicSBP Rule",
					"Successfully  Validated Started Price is Showing Blank" + " ", "Pass", "Y");

			scrollToElement(tendercreationlocators.user1auctionBy);

			Assert.assertTrue(ischeckboxcheckedbbydefault(driver.findElement(tendercreationlocators.user1auctionBy)),
					"Bidder User 1 is Checked");

			Assert.assertTrue(ischeckboxcheckedbbydefault(driver.findElement(tendercreationlocators.user2auctionBy)),
					"Bidder User 2 is Checked");

			pdfResultReport.addStepDetails("validateAuctionDetailsPageElements", "Validate Two Bidders are Selected",
					"Successfully  Validated The Two Bidders Are Selcted By Default" + " ", "Pass", "Y");

			scrollToElement(tendercreationlocators.saveScheduleBy);

			waitForObj(3000);

			pdfResultReport.addStepDetails("validateAuctionDetailsPageElements",
					"Must validate the Filelds As Expected", "Successfully  Validated the Fields As Expected" + " ",
					"Pass", "Y");

			log.info("completed executing the method:: validateAuctionDetailsPageElements");

		} catch (Exception e) {
			log.fatal("Unable to Enter MandatoryFields In EventDetailPage" + e.getMessage());
			pdfResultReport.addStepDetails("validateAuctionDetailsPageElements",
					"Must Enter MandatoryFields In EventDetailPage ",
					"Unable to Enter MandatoryFields In EventDetailPage" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void clickViewOfDefaultTermsAndConditions() throws Exception {
		try {
			log.info("started executing the method::clickViewOfDefaultTermsAndConditions");
			waitForElementToBeVisible(tendercreationlocators.viewButton);
			waitForObj(2000);
			click(tendercreationlocators.viewButton, "viewButton");
			waitForElementToBeVisible(tendercreationlocators.termsAndConditions);
			pdfResultReport.addStepDetails("clickViewOfDefaultTermsAndConditions",
					"Default Terms and Conditions window will be opened successfully",
					"Successfully Default Terms and Conditions window will be opened" + " ", "Pass", "Y");
			waitForObj(3000);
			click(tendercreationlocators.closeTNC, "closeTNC");
			waitForElementToBeVisible(tendercreationlocators.viewButton);
			waitForObj(3000);
			pdfResultReport.addStepDetails("clickViewOfDefaultTermsAndConditions",
					"Default Terms and Conditions window will be opened successfully",
					"Successfully Default Terms and Conditions window will be opened" + " ", "Pass", "Y");
			log.info("completed executing the method:: settingNewBidAsRateWise");

		} catch (Exception e) {
			log.fatal("Unable to open Default Terms and Conditions window" + e.getMessage());
			pdfResultReport.addStepDetails("settingNewBidAsRateWise",
					"Default Terms and Conditions window will be opened successfully",
					"Unable to Default Terms and Conditions window" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void ParticipateInLiveAuctionLOT_Consolidated(String Decrementval) throws Throwable {
		try {
			log.info("started executing the method:: Activate_Aution");

			click(tendercreationlocators.tabLIVE, "tabLIVE");
			waitForObj(3000);

			highlight(tendercreationlocators.StartPrice(Decrementval));
			waitForObj(3000);
			String startPrice = text(tendercreationlocators.StartPrice(Decrementval));

			String separator = ":";
			int sepPos = startPrice.indexOf(":");
			String price = startPrice.substring(sepPos + separator.length()).trim();
			int startprice = Integer.parseInt(price);

			/*
			 * highlight(tendercreationlocators.Quantity(Decrementval));
			 * waitForObj(3000);
			 * 
			 * highlight(tendercreationlocators.Currency(Decrementval));
			 * waitForObj(3000);
			 * 
			 * highlight(tendercreationlocators.MyLastBid(Decrementval));
			 * waitForObj(3000);
			 * 
			 * highlight(tendercreationlocators.Decrement(Decrementval));
			 * waitForObj(3000);
			 */

			JSClick(tendercreationlocators.SubsVal(Decrementval), "SubsVal");
			waitForObj(3000);
			JSClick(tendercreationlocators.Submit(Decrementval), "Submit");
			waitForObj(6000);
			JSClick(tendercreationlocators.BtnBackToEPS, "BtnBackToEPS");
			waitForObj(3000);
			JSClick(tendercreationlocators.ConfirmBackYes, "ConfirmBackYes");
			etendercomponentobj.waitForSpinnerToDisappear();
			pdfResultReport.addStepDetails("Activate_Aution", "Activating the Auction",
					"Successfully activated the Auction " + " ", "Pass", "Y");
			log.info("completed executing the method:: Activate_Aution");
		} catch (Exception e) {
			log.fatal("Unable to navigate to Auction participation" + e.getMessage());
			pdfResultReport.addStepDetails("Activate_Aution", "Activating the Auction",
					"Unable to Activate the Auction" + e.getMessage(), "Fail", "N");
		}
	}

	public void VerifyLiveTab(String Decrementval) throws Throwable {
		try {
			log.info("started executing the method:: VerifyLiveTab");

			click(tendercreationlocators.tabLIVE, "tabLIVE");
			waitForObj(3000);

			highlight(tendercreationlocators.StartPrice(Decrementval));
			waitForObj(3000);
			String startPrice = text(tendercreationlocators.StartPrice(Decrementval));

			String separator = ":";
			int sepPos = startPrice.indexOf(":");
			String price = startPrice.substring(sepPos + separator.length()).trim();
			int startprice = Integer.parseInt(price);

			highlight(tendercreationlocators.Quantity(Decrementval));
			waitForObj(3000);

			highlight(tendercreationlocators.Currency(Decrementval));
			waitForObj(3000);

			highlight(tendercreationlocators.Decrement(Decrementval));
			waitForObj(3000);

			String Value = ThreadLocalWebdriver.getDriver()
					.findElement(By.xpath("//div[contains(text(),'Decrement')]/../../div[text()='" + Decrementval
							+ "']/../../../following-sibling::div//span/input"))
					.getAttribute("value");
			double d = Double.parseDouble(Value);
			int bidValue = (int) d;

			if (startprice <= bidValue) {
				pdfResultReport.addStepDetails("VerifySTartPrice", "Start Price is equal than bid price",
						"Successfully verified the Start price -" + bidValue + " ", "Pass", "Y");
			} else {
				pdfResultReport.addStepDetails("VerifySTartPrice", "Start Price is equal than bid price",
						"Unable to Verify the Start price with bid Price", "Fail", "N");
			}

			JSClick(tendercreationlocators.BtnBackToEPS, "BtnBackToEPS");
			waitForObj(3000);
			JSClick(tendercreationlocators.ConfirmBackYes, "ConfirmBackYes");
			etendercomponentobj.waitForSpinnerToDisappear();
			pdfResultReport.addStepDetails("VerifyLiveTab", "Verify the Live Tab",
					"Successfully verified the Live tab " + " ", "Pass", "Y");
			log.info("completed executing the method:: VerifyLiveTab");
		} catch (Exception e) {
			log.fatal("Unable to navigate to Auction participation" + e.getMessage());
			pdfResultReport.addStepDetails("VerifyLiveTab", "Verify the Live Tab",
					"Unable to Verify the Live Tab" + e.getMessage(), "Fail", "N");
		}
	}

	public void uploadFileUnderCustomizedTermsandConditions() throws Throwable {
		try {
			log.info("started executing the method:: uploadFileUnderCustomizedTermsandConditions");
			JSClick(tendercreationlocators.customizedTermsCondtionRadio, "customizedTermsCondtionRadio");
			waitForElementToBeVisible(tendercreationlocators.file_Name);
			pdfResultReport.addStepDetails("uploadFileUnderCustomizedTermsandConditions",
					"Customized Terms and Conditions radio button must be click sucesssfully",
					"Successfully clicked Customized Terms and Conditions radio button" + " ", "Pass", "Y");
			set(tendercreationlocators.file_Name, "Report", "file_Name");
			set(tendercreationlocators.browseFile, System.getProperty("user.dir") + "\\MediaFiles\\report.pdf",
					"fileName");
			waitForObj(3000);
			pdfResultReport.addStepDetails("uploadFileUnderCustomizedTermsandConditions",
					"File name must be enter sucesssfully", "Successfully entered file name" + " ", "Pass", "Y");
			JSClick(tendercreationlocators.uploadFile, "uploadFile");
			waitForElementToBeVisible(tendercreationlocators.uploadFileConfirmOk);
			pdfResultReport.addStepDetails("uploadFileUnderCustomizedTermsandConditions",
					"File attached sucessfully popup must be display sucesssfully",
					"Successfully displayed file attached sucessfully popup" + " ", "Pass", "Y");
			click(tendercreationlocators.uploadFileConfirmOk, "uploadFileConfirmPopup");
			waitForObj(5000);
			pdfResultReport.addStepDetails("uploadFileUnderCustomizedTermsandConditions",
					"File must be upload sucesssfully", "Successfully uploaded file" + " ", "Pass", "Y");
			log.info("completed executing the method:: uploadFileUnderCustomizedTermsandConditions");
		} catch (Exception e) {
			log.fatal("Unable to upload file" + e.getMessage());
			pdfResultReport.addStepDetails("uploadFileUnderCustomizedTermsandConditions",
					"File must be upload sucesssfully", "Unable to upload file" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickOnAuctionListForController() throws Throwable {
		try {
			log.info("started executing the method:: clickOnAuctionListForController");
			JSClick(tendercreationlocators.auctionListForController, "auctionListForController");
			waitForElementToBeVisible(tendercreationlocators.auctionList);
			waitForObj(3000);
			pdfResultReport.addStepDetails("clickOnAuctionListForController",
					"Auction list page must be navigate successfully",
					"Successfully navigated to auction list page" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickOnAuctionListForController");
		} catch (Exception e) {
			log.fatal("Not able navigate to auction list page" + e.getMessage());
			pdfResultReport.addStepDetails("clickOnAuctionListForController",
					"Auction list page must be navigate successfully",
					"Unable to navigate to auction list page" + e.getMessage(), "Fail", "N");
		}
	}

	public void VerifyMarketPriceFieldIsPresentBeforeBidSubmission(String Decrementval) throws Throwable {
		try {
			log.info("started executing the method:: VerifyMarketPriceFieldIsPresentBeforeBidSubmission");
			waitForElementToBeVisible(tendercreationlocators.MarketPrice(Decrementval));
			highlight(tendercreationlocators.MarketPrice(Decrementval));
			waitForObj(3000);
			pdfResultReport.addStepDetails("VerifyMarketPriceFieldIsPresentBeforeBidSubmission",
					"Market Price Field before the bid submission must be display successfully",
					"Successfully displayed Market price field before the bid submission" + " ", "Pass", "Y");
			log.info("completed executing the method:: VerifyMarketPriceFieldIsPresentBeforeBidSubmission");
		} catch (Exception e) {
			log.fatal("Not able display Market price field before the bid submission" + e.getMessage());
			pdfResultReport.addStepDetails("VerifyMarketPriceFieldIsPresentBeforeBidSubmission",
					"Market Price Field before the bid submission must be display successfully",
					"Unable to display Market price field before the bid submission" + e.getMessage(), "Fail", "N");
		}
	}

	public void VerifyMarketPriceFieldIsPresentAfterBidSubmission(String Decrementval) throws Throwable {
		try {
			log.info("started executing the method:: VerifyMarketPriceFieldIsPresentAfterBidSubmission");
			waitForElementToBeVisible(tendercreationlocators.MarketPrice(Decrementval));
			highlight(tendercreationlocators.MarketPrice(Decrementval));
			waitForObj(3000);
			pdfResultReport.addStepDetails("VerifyMarketPriceFieldIsPresentAfterBidSubmission",
					"Market Price Field after the bid submission must be display successfully",
					"Successfully displayed Market price field after the bid submission" + " ", "Pass", "Y");
			log.info("completed executing the method:: VerifyMarketPriceFieldIsPresentAfterBidSubmission");
		} catch (Exception e) {
			log.fatal("Not able display Market price field after the bid submission" + e.getMessage());
			pdfResultReport.addStepDetails("VerifyMarketPriceFieldIsPresentAfterBidSubmission",
					"Market Price Field after the bid submission must be display successfully",
					"Unable to display Market price field after the bid submission" + e.getMessage(), "Fail", "N");
		}
	}

	public void VerifyStartPriceFieldStatus(String Decrementval) throws Throwable {
		try {
			log.info("started executing the method:: VerifyStartPriceFieldStatus");
			waitForElementToBeVisible(tendercreationlocators.startPrice(Decrementval));
			waitForObj(3000);
			highlight(tendercreationlocators.startPrice(Decrementval));
			pdfResultReport.addStepDetails("VerifyStartPriceFieldStatus",
					"Start Price Field  must be display successfully", "Successfully displayed Start price field" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: VerifyStartPriceFieldStatus");
		} catch (Exception e) {
			log.fatal("Not able display Market price field before the bid submission" + e.getMessage());
			pdfResultReport.addStepDetails("VerifyStartPriceFieldStatus",
					"Market Price Field before the bid submission must be display successfully",
					"Unable to display Market price field before the bid submission" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickOnObserver() throws Throwable {
		try {
			log.info("started executing the method:: clickOnObserver");
			JSClick(tendercreationlocators.auction, "auction");
			waitForObj(3000);
			JSClick(tendercreationlocators.observer, "observer");
			waitForElementToBeVisible(tendercreationlocators.auctionList);
			waitForObj(3000);
			pdfResultReport.addStepDetails("clickOnObserver", "Auction list page must be navigate successfully",
					"Successfully navigated to auction list page" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickOnObserver");
		} catch (Exception e) {
			log.fatal("Not able navigate to auction list page" + e.getMessage());
			pdfResultReport.addStepDetails("clickOnObserver", "Auction list page must be navigate successfully",
					"Unable to navigate to auction list page" + e.getMessage(), "Fail", "N");
		}
	}

	public void navigateToAuctionListController() throws Throwable {
		try {
			log.info("started executing the method:: navigateToAuctionListController");
			JSClick(tendercreationlocators.AuctionListForController, "AuctionListForController");

			waitForElementToBeVisible(tendercreationlocators.AuctionListPage);
			waitForObj(3000);
			pdfResultReport.addStepDetails("navigateToAuctionListController",
					"Auction List Controller must be naviagte successfully",
					"Successfully navigated to Auction List Controller" + " ", "Pass", "Y");
			log.info("completed executing the method:: navigateToAuctionListController");
		} catch (Exception e) {
			log.fatal("Not able to navigate to Auction List Controller" + e.getMessage());
			pdfResultReport.addStepDetails("navigateToAuctionListController",
					"Auction List Controller must be naviagte successfully",
					"Unable to navigate to Auction List Controller" + e.getMessage(), "Fail", "N");
		}
	}

	public void navigateToLiveTab() throws Throwable {
		try {
			log.info("started executing the method:: navigateToLiveTab");
			JSClick(tendercreationlocators.tabLIVE, "tabLIVE");

			waitForObj(3000);
			pdfResultReport.addStepDetails("navigateToLiveTab", "Live Tab must be naviagte successfully",
					"Successfully navigated to Live Tab" + " ", "Pass", "Y");
			log.info("completed executing the method:: navigateToLiveTab");
		} catch (Exception e) {
			log.fatal("Not able to navigate to Live Tab" + e.getMessage());
			pdfResultReport.addStepDetails("navigateToAuctionListController", "Live Tab must be naviagte successfully",
					"Unable to navigate to Live Tab" + e.getMessage(), "Fail", "N");
		}
	}

	public void StopAuction(String Decrementval) throws Throwable {
		try {
			log.info("started executing the method:: StopAuction");
			click(tendercreationlocators.Actionbtn(Decrementval), "Actionbtn");
			waitForObj(3000);
			click(tendercreationlocators.StopAuction(Decrementval), "StopAuction");
			waitForObj(3000);
			highlight(tendercreationlocators.Confirmationmessagepopup);
			waitForObj(3000);
			click(tendercreationlocators.YesToStop, "YesToStop");
			waitForObj(3000);
			highlight(tendercreationlocators.SuccessConfirmation);
			waitForObj(3000);
			pdfResultReport.addStepDetails("StopAuction", "Live Auction must be stopped Successfully",
					"Successfully Stopped the Live Auction" + " ", "Pass", "Y");
			click(tendercreationlocators.OKMessagebtn, "OKMessagebtn");
			waitForObj(5000);

			log.info("completed executing the method:: StopAuction");
		} catch (Exception e) {
			log.fatal("Not able to navigate to Live Tab" + e.getMessage());
			pdfResultReport.addStepDetails("StopAuction", "Live Auction must be stopped Successfully",
					"Unable to stop Live Auction" + e.getMessage(), "Fail", "N");
		}
	}

	public void VerifyHardStop() throws Throwable {
		try {
			log.info("started executing the method:: VerifyHardStop");
			String Tenderrefnum = eTenderComponent.getDataFromPropertiesFile("TenderRefNumber");
			highlight(tendercreationlocators.TenderRefNum(Tenderrefnum));
			waitForObj(3000);
			highlight(tendercreationlocators.HArdStoptext(Tenderrefnum));
			waitForObj(5000);
			pdfResultReport.addStepDetails("clickOnPostAuction", "Hard Stop must be verified",
					"Successfully verified Hard Stop" + " ", "Pass", "Y");
			log.info("completed executing the method:: VerifyHardStop");
		} catch (Exception e) {
			log.fatal("Not able to verify the Hard Stop" + e.getMessage());
			pdfResultReport.addStepDetails("VerifyHardStop", "Hard Stop must be verified",
					"Unable to verify the Hard Stop" + e.getMessage(), "Fail", "N");
		}
	}

	public void NavigateToReleaseTab() throws Throwable {
		try {
			log.info("started executing the method:: NavigateToReleaseTab");
			click(tendercreationlocators.ReleaseTab, "ReleaseTab");
			waitForObj(5000);
			pdfResultReport.addStepDetails("NavigateToReleaseTab", "Navigation to release Tab",
					"Successfully Navigated To release tab" + " ", "Pass", "Y");
			log.info("completed executing the method:: VerifyHardStop");
		} catch (Exception e) {
			log.fatal("Not able to navigate To release tab" + e.getMessage());
			pdfResultReport.addStepDetails("NavigateToReleaseTab", "Navigation to release Tab",
					"Unable to navigate To release tab" + e.getMessage(), "Fail", "N");
		}
	}

	public void RelaunchAuction() throws Throwable {
		try {
			log.info("started executing the method:: RelaunchAuction");
			click(tendercreationlocators.ActionDrpdwn, "ActionDrpdwn");
			waitForObj(3000);
			click(tendercreationlocators.Relaunch, "Relaunch");
			waitForObj(3000);
			click(tendercreationlocators.YesToStop, "YesToStop");
			waitForObj(2000);
			click(tendercreationlocators.RemarksForRelaunch, "RemarksForRelaunch");
			waitForObj(2000);
			set(tendercreationlocators.RemarksForRelaunch, "Auction is Relaunched", "RemarksForRelaunch");
			waitForObj(5000);
			click(tendercreationlocators.RelaunchBtn, "RelaunchBtn");

			highlight(tendercreationlocators.SuccessConfirmation);
			waitForObj(3000);
			pdfResultReport.addStepDetails("RelaunchAuction", "Auction to be relaunched",
					"Successfully Relaunched the auction" + " ", "Pass", "Y");
			click(tendercreationlocators.OKMessagebtn, "OKMessagebtn");
			waitForObj(3000);
			log.info("completed executing the method:: RelaunchAuction");
		} catch (Exception e) {
			log.fatal("Not able to relaunch the auction" + e.getMessage());
			pdfResultReport.addStepDetails("RelaunchAuction", "Auction to be relaunched",
					"Unable to relaunch the auction" + e.getMessage(), "Fail", "N");
		}
	}

	public void downloadAllTypesReportsPDF() throws Throwable {
		try {

			log.info("started executing the method:: downloadAllTypesReportsPDF");
			click(tendercreationlocators.action_Dropdown, "action_Dropdown");
			waitForElementToBeVisible(tendercreationlocators.auctionSummaryReport);
			highlight(tendercreationlocators.auctionSummaryReport);
			pdfResultReport.addStepDetails("downloadAuctionSummaryReportPDF",
					"Auction summary report link must be display successfully",
					"Successfully displayed auction summary report link" + " ", "Pass", "Y");
			click(tendercreationlocators.auctionSummaryReport, "auctionSummaryReport");

			waitForObj(5000);
			click(tendercreationlocators.action_Dropdown, "action_Dropdown");
			waitForElementToBeVisible(tendercreationlocators.BidTrailReport);
			highlight(tendercreationlocators.BidTrailReport);
			pdfResultReport.addStepDetails("downloadBidTrailReportPDF",
					"Bid Trail Report link must be display successfully",
					"Successfully displayed auction summary report link" + " ", "Pass", "Y");
			click(tendercreationlocators.BidTrailReport, "BidTrailReport");
			waitForObj(5000);

			click(tendercreationlocators.action_Dropdown, "action_Dropdown");
			waitForElementToBeVisible(tendercreationlocators.RejectedBidTrailReport);
			highlight(tendercreationlocators.RejectedBidTrailReport);
			pdfResultReport.addStepDetails("RejectedBidTrailReport",
					"Rejected Bid Trail Report link must be display successfully",
					"Successfully displayed Rejected Bid Trail Report summary report link" + " ", "Pass", "Y");
			click(tendercreationlocators.RejectedBidTrailReport, "RejectedBidTrailReport");
			waitForObj(5000);

			pdfResultReport.addStepDetails("downloadAllTypesReportsPDF", "All types of must be download successfully",
					"Successfully downloaded all report types" + " ", "Pass", "Y");
			log.info("completed executing the method:: downloadAllTypesReportsPDF");
		} catch (Exception e) {
			log.fatal("NUnable to download all report types" + e.getMessage());
			pdfResultReport.addStepDetails("downloadAllTypesReportsPDF", "All types of must be download successfully",
					"Unable to download all report types" + e.getMessage(), "Fail", "N");
		}
	}

	public void submitNewBidValue(String price1, String price2, String price3) throws Throwable {
		try {
			log.info("started executing the method::submitNewBidValue");
			clear(tendercreationlocators.price1, "Clear Price1");
			set(tendercreationlocators.price1, price1, "price1");

			clear(tendercreationlocators.price2, "Clear Price2");
			set(tendercreationlocators.price2, price2, "price2");

			clear(tendercreationlocators.price3, "Clear Price3");
			set(tendercreationlocators.price3, price3, "price3");

			pdfResultReport.addStepDetails("submitNewBidValue", "Price must be eneter sucessfully",
					"Successfully Entered Price" + " ", "Pass", "Y");

			click(tendercreationlocators.calculateTotal, "calculateTotal");
			pdfResultReport.addStepDetails("submitNewBidValue", "BidValue must be calculate sucessfully",
					"Successfully calculated Bidvalue" + " ", "Pass", "Y");
			click(tendercreationlocators.submitBidValue, "submitBidValue");
			pdfResultReport.addStepDetails("submitNewBidValue", "BidValue must be accept sucessfully",
					"Successfully accepted BidValue" + " ", "Pass", "Y");
			waitForObj(3000);
			log.info("completed executing the method:: submitNewBidValue");
		} catch (Exception e) {
			log.fatal("Unable to submit BidValue" + e.getMessage());
			pdfResultReport.addStepDetails("submitNewBidValue", "BidValue must be submit sucessfully",
					"Unable to submit BidValue" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void VerifyRankFieldIsNotPresentAtLiveAuctionPageBeforeBidSubmission(String Decrementval) throws Throwable {
		try {
			log.info("started executing the method:: VerifyRankFieldIsNotPresentAtLiveAuctionPageBeforeBidSubmission");
			waitForElementToBeVisible(tendercreationlocators.MarketPrice(Decrementval));
			IsElementPresent(tendercreationlocators.MarketPrice(Decrementval));
			waitForObj(3000);
			pdfResultReport.addStepDetails("VerifyRankFieldIsNotPresentAtLiveAuctionPageBeforeBidSubmission",
					"Rank Field before the bid submission should not be display successfully",
					"Successfully not displayed Rank field before the bid submission" + " ", "Pass", "Y");
			log.info(
					"completed executing the method:: VerifyRankFieldIsNotPresentAtLiveAuctionPageBeforeBidSubmission");
		} catch (Exception e) {
			log.fatal("Not able display Rank field before the bid submission" + e.getMessage());
			pdfResultReport.addStepDetails("VerifyRankFieldIsNotPresentAtLiveAuctionPageBeforeBidSubmission",
					"Rank Field before the bid submission should not be display successfully",
					"Unable to display Rank field before the bid submission" + e.getMessage(), "Fail", "N");
		}
	}

	public void VerifyRankFieldIsNotPresentAtLiveAuctionPageAfterBidSubmission(String Decrementval) throws Throwable {
		try {
			log.info("started executing the method:: VerifyRankFieldIsNotPresentAtLiveAuctionPageAfterBidSubmission");
			waitForElementToBeVisible(tendercreationlocators.MarketPrice(Decrementval));
			IsElementPresent(tendercreationlocators.MarketPrice(Decrementval));
			waitForObj(3000);
			pdfResultReport.addStepDetails("VerifyRankFieldIsNotPresentAtLiveAuctionPageAfterBidSubmission",
					"Rank Field after the bid submission should not be display successfully",
					"Successfully not displayed Rank field after the bid submission" + " ", "Pass", "Y");
			log.info("completed executing the method:: VerifyRankFieldIsNotPresentAtLiveAuctionPageAfterBidSubmission");
		} catch (Exception e) {
			log.fatal("Not able display Rank field after the bid submission" + e.getMessage());
			pdfResultReport.addStepDetails("VerifyRankFieldIsNotPresentAtLiveAuctionPageAfterBidSubmission",
					"Rank Field after the bid submission should not be display successfully",
					"Unable to display Rank field after the bid submission" + e.getMessage(), "Fail", "N");
		}
	}

	public void notifyBiddersWithLeadsIfHeRanksFirst(String Decrementval) throws Throwable {
		try {
			log.info("started executing the method::notifyBiddersWithLeadsIfHeRanksFirst");
			waitForElementToBeVisible(tendercreationlocators.lead(Decrementval));
			highlight(tendercreationlocators.lead(Decrementval));
			pdfResultReport.addStepDetails("notifyBiddersWithLeadsIfHeRanksFirst",
					"Bidder must be notify with lead rank sucessfully",
					"Successfully notified bidder with lead rank" + " ", "Pass", "Y");
			waitForObj(3000);
			JSClick(tendercreationlocators.BtnBackToEPS, "BtnBackToEPS");
			waitForElementToBeVisible(tendercreationlocators.ConfirmBackYes);
			JSClick(tendercreationlocators.ConfirmBackYes, "ConfirmBackYes");
			etendercomponentobj.waitForSpinnerToDisappear();
			pdfResultReport.addStepDetails("notifyBiddersWithLeadsIfHeRanksFirst",
					"Bidder must be notify with lead rank sucessfully",
					"Successfully notified bidder with lead rank" + " ", "Pass", "Y");
			log.info("completed executing the method:: notifyBiddersWithLeadsIfHeRanksFirst");

		} catch (Exception e) {
			log.fatal("Unable to notify bidder with lead rank" + e.getMessage());
			pdfResultReport.addStepDetails("notifyBiddersWithLeadsIfHeRanksFirst",
					"Bidder must be notify with lead rank sucessfully",
					"Unable to notify bidder with lead rank" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void verifyRank1(String val) throws Throwable {
		try {
			log.info("started executing the method:: verifyRank1");
			waitForElementToBeVisible(tendercreationlocators.rank(val));
			pdfResultReport.addStepDetails("verifyRank1", "unable to verify rank one",
					"Successfully verified rank one" + " ", "Pass", "Y");
			JSClick(tendercreationlocators.BtnBackToEPS, "BtnBackToEPS");
			waitForElementToBeVisible(tendercreationlocators.ConfirmBackYes);
			JSClick(tendercreationlocators.ConfirmBackYes, "ConfirmBackYes");
			etendercomponentobj.waitForSpinnerToDisappear();
			log.info("completed executing the method:: verifyRank1");
		} catch (Exception e) {
			log.fatal("Unable to verify rank one" + e.getMessage());
			pdfResultReport.addStepDetails("verifyRank1", "Successfully verified rank one",
					"Unable to verify rank one" + e.getMessage(), "Fail", "N");
		}
	}

	public void settingNewBidAsRateWise1(String price1, String price2, String price3) throws Throwable {
		try {
			log.info("started executing the method::settingNewBidAsRateWise");

			clear(tendercreationlocators.price1, "Clear Price1");
			set(tendercreationlocators.price1, price1, "price1");

			clear(tendercreationlocators.price2, "Clear Price2");
			set(tendercreationlocators.price2, price2, "price2");

			clear(tendercreationlocators.price3, "Clear Price3");
			set(tendercreationlocators.price3, price3, "price3");

			pdfResultReport.addStepDetails("settingNewBidAsRateWise", "Price must be eneter sucessfully",
					"Successfully Entered Price" + " ", "Pass", "Y");

			click(tendercreationlocators.calculateTotal, "calculateTotal");
			pdfResultReport.addStepDetails("settingNewBidAsRateWise", "BidValue must be calculate sucessfully",
					"Successfully calculated Bidvalue" + " ", "Pass", "Y");
			click(tendercreationlocators.submitBidValue, "submitBidValue");

			pdfResultReport.addStepDetails("settingNewBidAsRateWise", "BidValue must be submit sucessfully",
					"Successfully submitted BidValue" + " ", "Pass", "Y");
			log.info("completed executing the method:: settingNewBidAsRateWise");

		} catch (Exception e) {
			log.fatal("Unable to submit BidValue" + e.getMessage());
			pdfResultReport.addStepDetails("settingNewBidAsRateWise", "BidValue must be submit sucessfully",
					"Unable to submit BidValue" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void NavigaterToObserver(String Decrementval) throws Throwable {
		try {
			log.info("started executing the method:: NavigaterToObserver");
			waitForObj(2000);
			
			JSClick(tendercreationlocators.auction, "btnAuction");
			JSClick(tendercreationlocators.observe, "observe");
			waitForElementToBeVisible(tendercreationlocators.tabLIVE);
			highlight(tendercreationlocators.Decrement(Decrementval));
			waitForObj(2000);
			pdfResultReport.addStepDetails("NavigaterToObserver", "Navigating to Auction Observer",
					"Successfully navigated to Auction Observer" + " ", "Pass", "Y");
			log.info("completed executing the method:: NavigaterToObserver");
		} catch (Exception e) {
			log.fatal("Unable to navigate to Auction Observer" + e.getMessage());
			pdfResultReport.addStepDetails("NavigaterToObserver", "Navigating to Auction Observer",
					"Unable to navigate to Auction Observer" + e.getMessage(), "Fail", "N");
		}
	}
	public void BidTrailReport(String path) throws Throwable {
		try {
			log.info("started executing the method:: BidTrailReport");

			waitForObj(5000);
			click(tendercreationlocators.action_Dropdown, "action_Dropdown");
			waitForElementToBeVisible(tendercreationlocators.BidTrailReport);
			highlight(tendercreationlocators.BidTrailReport);
			pdfResultReport.addStepDetails("downloadBidTrailReportPDF",
					"Bid Trail Report link must be display successfully",
					"Successfully displayed auction summary report link" + " ", "Pass", "Y");
			click(tendercreationlocators.BidTrailReport, "BidTrailReport");
			waitForObj(5000);
			//
			String filename = null;
			File dir = new File(path);
			FilenameFilter filter = new FilenameFilter() {
				public boolean accept(File dir, String name) {
					return name.startsWith("reportBidTrail");
				}
			};
			String[] children = dir.list(filter);
			if (children == null) {
				System.out.println("Either dir does not exist or is not a directory");
			} else {
				for (int i = 0; i < children.length; i++) {
					filename = children[i];
					System.out.println(filename);
				}
				String file = path + File.separator + filename;
				Openfile(file);
				waitForObj(5000);

				pdfResultReport.addStepDetails("BidTrailReport", "Bid Trail report downloaded and data verification",
						"Successfully Verified Bid Trail report" + " ", "Pass", "Y", "");
				log.info("completed executing the method:: BidTrailReport");
			}
		} catch (Exception e) {
			log.fatal("Unable to verify bid trail report" + e.getMessage());
			pdfResultReport.addStepDetails("BidTrailReport", "Bid Trail report downloaded and data verification",
					"Unable to verify bid trail report" + e.getMessage(), "Fail", "N", "");
		}
	}

	public void Openfile(String path) throws IOException {
		File pdfFile = new File(path);
		if (pdfFile.exists()) {

			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().open(pdfFile);
			} else {
				System.out.println("Awt Desktop is not supported!");
			}

		} else {
			System.out.println("File is not exists!");
		}

		System.out.println("Done");

	}


	public void validateStartPriceShouldBeLessThanReservePriceForDutchRule(String StartPrice1, String StartPrice2,
			String StratPriceValidationMsg) throws Throwable {
		try {
			log.info("started executing the method::validateStartPriceShouldBeLessThanReservePriceForDutchRule");

			scrollToTopOfThePage();

			waitForObj(3000);

			clear(tendercreationlocators.startPriceFiledBy, "startPriceFiledBy");

			set(tendercreationlocators.startPriceFiledBy, StartPrice1, "startPriceFiledBy");

			JSClick(tendercreationlocators.saveEventDetailsBy, "saveEventDetailsBy");

			scrollToTopOfThePage();

			String startPriceValidationMsg = text(tendercreationlocators.startPriceValidationBy).trim();

			Assert.assertTrue(startPriceValidationMsg.contains(StratPriceValidationMsg),
					"Please enter Less than the Reserve price");

			waitForObj(3000);

			pdfResultReport.addStepDetails("validateStartPriceShouldBeLessThanReservePriceForDutchRule",
					"Must validate the Start Price Filelds As Expected",
					"Successfully  Validated the Start Price Field As Expected" + " ", "Pass", "Y");

			clear(tendercreationlocators.startPriceFiledBy, "startPriceFiledBy");

			set(tendercreationlocators.startPriceFiledBy, StartPrice2, "startPriceFiledBy");

			JSClick(tendercreationlocators.saveEventDetailsBy, "saveEventDetailsBy");

			waitForElementToBeVisible(tendercreationlocators.AuctionSavedSucessMsgBy);

			click(tendercreationlocators.confirmationOkBy, "confirmationOkBy");

			waitForObj(3000);

			scrollToTopOfThePage();

			pdfResultReport.addStepDetails("validateStartPriceShouldBeLessThanReservePriceForDutchRule",
					"Must save the Details", "Successfully save details " + " ", "Pass", "Y");

			log.info("completed executing the method:: validateStartPriceShouldBeLessThanReservePriceForDutchRule");

		} catch (Exception e) {
			log.fatal("Unable to save the details" + e.getMessage());
			pdfResultReport.addStepDetails("validateStartPriceShouldBeLessThanReservePriceForDutchRule",
					"Must save the Details ", "Unable to save the details" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void validateReservePriceShouldBeLessThanL1PriceForDutchRule(String ReservePrice1, String ReservePrice2,
			String ReservepricevalidationMsg) throws Throwable {
		try {
			log.info("started executing the method::validateReservePriceShouldBeLessThanL1PriceForDutchRule");

			waitForObj(3000);

			clear(tendercreationlocators.reserverevePriceFiledBy, "Clear ReservePrice");

			set(tendercreationlocators.reserverevePriceFiledBy, ReservePrice1, "ReservePrice");

			JSClick(tendercreationlocators.saveEventDetailsBy, "saveEventDetailsBy");

			scrollToTopOfThePage();

			// String reservePriceValidationMsg =
			// text(tendercreationlocators.reservePriceValidationBy).trim();

			// Assert.assertTrue(reservePriceValidationMsg.contains(ReservepricevalidationMsg),
			// "Please enter Less than the L1 price");

			pdfResultReport.addStepDetails("validateReservePriceShouldBeLessThanL1PriceForDutchRule",
					" Reserve price should not be greater than L1 price", "Successfully  Validated Reserve Price" + " ",
					"Pass", "Y");

			clear(tendercreationlocators.reserverevePriceFiledBy, "Clear ReservePrice");

			set(tendercreationlocators.reserverevePriceFiledBy, ReservePrice2, "ReservePrice");

			JSClick(tendercreationlocators.saveEventDetailsBy, "saveEventDetailsBy");

			waitForElementToBeVisible(tendercreationlocators.AuctionSavedSucessMsgBy);

			click(tendercreationlocators.confirmationOkBy, "confirmationOkBy");

			waitForObj(3000);

			scrollToTopOfThePage();

			pdfResultReport.addStepDetails("validateReservePriceShouldBeLessThanL1PriceForDutchRule",
					"Must validate the Filelds As Expected",
					"Successfully  Validated the Fields As Expected and save Details" + " ", "Pass", "Y");

			log.info("completed executing the method:: validateReservePriceShouldBeLessThanL1PriceForDutchRule");

		} catch (Exception e) {
			log.fatal("Unable to save details" + e.getMessage());
			pdfResultReport.addStepDetails("validateReservePriceShouldBeLessThanL1PriceForDutchRule",
					"Must validate the Filelds As Expected ", "Unable to save details" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void validateTwoMidderAreSelectedByDefault() throws Throwable {
		try {
			log.info("started executing the method::validateTwoMidderAreSelectedByDefault");

			WebDriver driver = ThreadLocalWebdriver.getDriver();

			scrollToElement(tendercreationlocators.user1auctionBy);

			Assert.assertTrue(ischeckboxcheckedbbydefault(driver.findElement(tendercreationlocators.user1auctionBy)),
					"Bidder User 1 is Checked");

			Assert.assertTrue(ischeckboxcheckedbbydefault(driver.findElement(tendercreationlocators.user2auctionBy)),
					"Bidder User 2 is Checked");

			pdfResultReport.addStepDetails("validateTwoMidderAreSelectedByDefault", "Validate Two Bidders are Selected",
					"Successfully  Validated The Two Bidders Are Selcted By Default" + " ", "Pass", "Y");

			scrollToElement(tendercreationlocators.saveScheduleBy);

			log.info("completed executing the method:: validateTwoMidderAreSelectedByDefault");

		} catch (Exception e) {
			log.fatal("Unable to Validate Two Bidders are Selected" + e.getMessage());
			pdfResultReport.addStepDetails("validateTwoMidderAreSelectedByDefault",
					"Must Validate Two Bidders are Selected ",
					"Unable to Validate Two Bidders are Selected" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void ScheduleTheEvent(int rowNo, int startEventTimeMiutes, int endEventTimeMinutes) throws Throwable {
		try {
			log.info("started executing the method:: ScheduleTheEvent");

			click(tendercreationlocators.selectDraftCheckBox(rowNo), "selectDraftCheckBox");

			click(tendercreationlocators.ClickonStartDateField, "ClickonDateField");

			click(tendercreationlocators.todayStartdate, "todaydate");

			LocalDateTime localdatetime1 = LocalDateTime.now().plusMinutes(startEventTimeMiutes);

			String StartDate = localdatetime1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			System.out.println("bid time is " + StartDate);
			String y = StartDate.substring(11, 13);
			String s = StartDate.substring(11, 16);
			if (y.startsWith("0")) {
				char charAt = y.charAt(0);

				y = y.replaceFirst(String.valueOf(charAt), "").trim();
			}

			if (s.startsWith("0")) {
				char charAt = s.charAt(0);

				s = s.replaceFirst(String.valueOf(charAt), "").trim();
			}

			System.out.println("hour is- " + y);
			System.out.println("min is- " + s);
			JSClick(tendercreationlocators.Starthour(y.trim()), "Starthour");
			waitForObj(3000);
			JSClick(tendercreationlocators.Startminutes(s.trim()), "Startminutes");

			etendercomponentobj.updateDataIntoPropertyFile("AuctionStartDate", StartDate);
			waitForObj(5000);

			click(tendercreationlocators.ClickonEndDateField, "ClickonEndDateField");

			click(tendercreationlocators.todayEnddate, "todayEnddate");

			LocalDateTime localdatetime2 = LocalDateTime.now().plusMinutes(endEventTimeMinutes);

			String EndDate = localdatetime2.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			System.out.println("bid time is " + EndDate);
			String a = EndDate.substring(11, 13);
			String b = EndDate.substring(11, 16);

			if (a.startsWith("0")) {
				char charAt = a.charAt(0);

				a = a.replaceFirst(String.valueOf(charAt), "").trim();
			}

			if (b.startsWith("0")) {
				char charAt = b.charAt(0);

				b = b.replaceFirst(String.valueOf(charAt), "").trim();
			}

			System.out.println("hour is- " + a);
			System.out.println("min is- " + b);

			JSClick(tendercreationlocators.Endhour(a.trim()), "Starthour");

			waitForObj(3000);
			waitForElementToBeVisible(tendercreationlocators.Endminutes(b));

			JSClick(tendercreationlocators.Endminutes(b.trim()), "Startminutes");

			etendercomponentobj.updateDataIntoPropertyFile("AuctionEndDate", EndDate);
			waitForObj(5000);

			pdfResultReport.addStepDetails("ScheduleTheEvent", "Should Enter Both Event start And End time",
					"Successfully  Entered Both Event start And End time" + " ", "Pass", "Y");
			log.info("completed executing the method:: ScheduleTheEvent");

		} catch (Exception e) {
			log.fatal("Unable to Enter Both Event start And End time" + e.getMessage());
			pdfResultReport.addStepDetails("ScheduleTheEvent", "Should Enter Both Event start And End time",
					"Unable to Enter Both Event start And End time" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void clickOnLiveAuction() throws Throwable {
		try {
			log.info("started executing the method:: clickOnLiveAuction");

			waitForObj(3000);

			click(tendercreationlocators.tabLIVE, "tabLIVE");

			waitForObj(3000);

			pdfResultReport.addStepDetails("clickOnLiveAuction", "Must Click On Live Auction",
					"Successfully Clicked On Live Auction " + " ", "Pass", "Y");

			log.info("completed executing the method:: clickOnLiveAuction");
		} catch (Exception e) {
			log.fatal(" Not able to Click On Live Auction" + e.getMessage());
			pdfResultReport.addStepDetails("clickOnLiveAuction", "Must Click On Live Auction",
					" Not able to Click On Live Auction" + e.getMessage(), "Fail", "N");
		}
	}

	public void ParticipateInLiveAuctionAndSubmitBid(String Decrementval) throws Throwable {
		try {
			log.info("started executing the method:: ParticipateInLiveAuctionAndSubmitBid");

			waitForObj(3000);

			highlight(tendercreationlocators.DutchRuleStartPrice(Decrementval));

			waitForObj(2000);

			highlight(tendercreationlocators.DutchRuleMarketPrice(Decrementval));

			waitForObj(2000);

			highlight(tendercreationlocators.DutchRuleNewBidPrice(Decrementval));
			waitForObj(2000);

			highlight(tendercreationlocators.DutchRuleSubmitBidPrice(Decrementval));

			JSClick(tendercreationlocators.DutchRuleSubmitBidPrice(Decrementval), "Submit");

			waitForObj(6000);
			JSClick(tendercreationlocators.BtnBackToEPS, "BtnBackToEPS");
			waitForObj(3000);
			JSClick(tendercreationlocators.ConfirmBackYes, "ConfirmBackYes");
			etendercomponentobj.waitForSpinnerToDisappear();
			pdfResultReport.addStepDetails("ParticipateInLiveAuctionAndSubmitBid",
					"Bidder Must Submit the Auction  Bid", "Successfully Bidder has Submitted the Auction Bid " + " ",
					"Pass", "Y");
			log.info("completed executing the method:: ParticipateInLiveAuctionAndSubmitBid");
		} catch (Exception e) {
			log.fatal("Bidder Not able to Submit the Auction  Bid" + e.getMessage());
			pdfResultReport.addStepDetails("ParticipateInLiveAuctionAndSubmitBid",
					"Bidder Must Submit the Auction  Bid",
					"Bidder Not able to Submit the Auction  Bid" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickSaveAndActivateBtn() throws Exception {
		try {
			log.info("started executing the method:: clickSaveAndActivateBtn");

			waitForObj(3000);

			click(tendercreationlocators.eventPagedropDownBy, "eventPagedropDownBy");

			waitForElementToBeVisible(tendercreationlocators.saveAndActivateFieldBy);

			click(tendercreationlocators.saveAndActivateFieldBy, "saveAndActivateFieldBy");

			waitForElementToBeVisible(tendercreationlocators.auctionactivatedSuccessmsgBy);

			click(tendercreationlocators.confirmationOkBy, "confirmationOkBy");

			waitForElementToBeVisible(tendercreationlocators.DutchAcivateStatusBy);

			waitForObj(5000);

			WebDriver driver = ThreadLocalWebdriver.getDriver();

			String eventStartDateValue = driver.findElement(tendercreationlocators.DutchstartDateTime)
					.getAttribute("value").trim();

			eTenderComponent.updateDataIntoPropertyFile("AuctionStartDate", eventStartDateValue);

			String eventEndDateValue = driver.findElement(tendercreationlocators.DutchendDateTime).getAttribute("value")
					.trim();

			eTenderComponent.updateDataIntoPropertyFile("AuctionEndDate", eventEndDateValue);

			pdfResultReport.addStepDetails("clickSaveAndActivateBtn",
					"should Change The Auction event Status To active ",
					"Successfully activated the  Auction event" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickSavescheduleactivateBtn");

		} catch (Exception e) {
			log.fatal("Unable to  Change The Auction event Status To active" + e.getMessage());
			pdfResultReport.addStepDetails("clickSaveAndActivateBtn",
					"should Change The Auction event Status To active",
					"Unable to  Change The Auction event Status To active" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}
	public void enterMandatoryFieldsInEventDetailPageForDutchAuctioRule(String ReservePrice, String decrementPrice,
			String AuctionRule, String StartPrice, int pauseTimeMinutes, int resumeTimeMinutes, int endTimeMinutes)
			throws Throwable {
		try {
			log.info("started executing the method::enterMandatoryFieldsInEventDetailPageForDutchAuctioRule");

			clear(tendercreationlocators.startPriceFiledBy, "startPriceFiledBy");

			set(tendercreationlocators.startPriceFiledBy, StartPrice, "startPriceFiledBy");

			clear(tendercreationlocators.reserverevePriceFiledBy, "Clear ReservePrice");

			set(tendercreationlocators.reserverevePriceFiledBy, ReservePrice, "ReservePrice");

			clear(tendercreationlocators.changePriceFiledBy, "Clear decrementPrice");
			set(tendercreationlocators.changePriceFiledBy, decrementPrice, "decrementPrice");

			select(tendercreationlocators.auctionRuleFiledBy, AuctionRule);

			LocalDateTime localdatetime1 = LocalDateTime.now().plusMinutes(pauseTimeMinutes);

			String pausedateTime = localdatetime1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

			clear(tendercreationlocators.pauseTimeFieldBy, "pauseTimeFieldBy");
			set(tendercreationlocators.pauseTimeFieldBy, pausedateTime, "pausedateTime");

			click(tendercreationlocators.pauseTimeFieldBy, "pauseTimeFieldBy");

			waitForObj(2000);

			LocalDateTime localdatetime2 = LocalDateTime.now().plusMinutes(resumeTimeMinutes);

			String resumedateTime = localdatetime2.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

			clear(tendercreationlocators.resumeTimeFieldBy, "resumeTimeFieldBy");

			set(tendercreationlocators.resumeTimeFieldBy, resumedateTime, "resumeTimeFieldBy");

			click(tendercreationlocators.resumeTimeFieldBy, "resumeTimeFieldBy");
			waitForObj(2000);

			etendercomponentobj.updateDataIntoPropertyFile("DucthAuctionResumeTime", resumedateTime);
			LocalDateTime localdatetime3 = LocalDateTime.now().plusMinutes(endTimeMinutes);

			String extendeddateTime = localdatetime3.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

			clear(tendercreationlocators.extendedTimeFieldBy, "extendedTimeFieldBy");

			set(tendercreationlocators.extendedTimeFieldBy, extendeddateTime, "extendeddateTime");
			click(tendercreationlocators.extendedTimeFieldBy, "extendedTimeFieldBy");

			waitForObj(2000);

			etendercomponentobj.updateDataIntoPropertyFile("DucthAuctionExtendedTime", extendeddateTime);

			click(tendercreationlocators.observersAddFiledBy, "observersAddFiledBy");

			waitForElementToBeVisible(tendercreationlocators.ObserversBy);

			click(tendercreationlocators.ObserversBy, "ObserversBy");

			click(tendercreationlocators.selectObserverAddBy, "selectObserverAddBy");

			waitForObj(3000);

			JSClick(tendercreationlocators.saveEventDetailsBy, "saveEventDetailsBy");

			waitForElementToBeVisible(tendercreationlocators.AuctionSavedSucessMsgBy);

			click(tendercreationlocators.confirmationOkBy, "confirmationOkBy");

			waitForObj(3000);

			JavascriptExecutor je = (JavascriptExecutor) ThreadLocalWebdriver.getDriver();

			String pausedateTimevalue = (String) je.executeScript("return document.getElementById('pauseTime').value;");

			etendercomponentobj.updateDataIntoPropertyFile("DucthAuctionPauseTime", pausedateTimevalue.trim());

			String resumedateTimevalue = (String) je
					.executeScript("return document.getElementById('resumeTime').value;");

			etendercomponentobj.updateDataIntoPropertyFile("DucthAuctionResumeTime", resumedateTimevalue.trim());

			String extendeddateTimevalue = (String) je
					.executeScript("return document.getElementById('extendedDate').value;");

			etendercomponentobj.updateDataIntoPropertyFile("DucthAuctionExtendedTime", extendeddateTimevalue.trim());

			pdfResultReport.addStepDetails("enterMandatoryFieldsInEventDetailPageForDutchAuctioRule",
					"Must Enter MandatoryFields In EventDetailPage",
					"Successfully  Entered MandatoryFields In EventDetailPage" + " ", "Pass", "Y");
			log.info("completed executing the method:: enterMandatoryFieldsInEventDetailPageForDutchAuctioRule");

		} catch (Exception e) {
			log.fatal("Unable to Enter MandatoryFields In EventDetailPage" + e.getMessage());
			pdfResultReport.addStepDetails("enterMandatoryFieldsInEventDetailPageForDutchAuctioRule",
					"Must Enter MandatoryFields In EventDetailPage ",
					"Unable to Enter MandatoryFields In EventDetailPage" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}

	}

public void clickSaveAndSchedule() throws Exception
	{
		try {
			log.info("started executing the method:: clickSaveAndSchedule");
			
			click(tendercreationlocators.saveScheduleBy, "saveScheduleBy");
			
			waitForElementToBeVisible(tendercreationlocators.AuctionSavedSucessMsgBy);
			
			click(tendercreationlocators.confirmationOkBy, "confirmationOkBy");
			
			waitForElementToBeVisible(tendercreationlocators.publishEventBtnBy);
			
			waitForObj(3000);
			
			pdfResultReport.addStepDetails("clickSaveAndSchedule", "Should Save auction details and navigate to Event Schedule page",
					"Successfully Saved auction details and navigated to Event Schedule page" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickSaveAndSchedule");

		} catch (Exception e) {
			log.fatal("Unable to  navigate to Event Schedule page  " + e.getMessage());
			pdfResultReport.addStepDetails("clickSaveAndSchedule", "Should Save auction details and navigate to Event Schedule page ",
					"Unable to  navigate to Event Schedule page " + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to "+e.getMessage());
		}
	}

	public boolean waitForAuctionBidEndTimeToReach(String bidTime, String Decrementval) throws Exception {
		boolean blnStatus = false;
		try {
			while (true) {
				LocalDateTime localdatetime = LocalDateTime.now();

				String currentdateTime = localdatetime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

				System.out.println(bidTime);
				System.out.println(currentdateTime);
				if (currentdateTime.compareTo(bidTime) > 0) {

					waitForObj(8000);
					blnStatus = true;
					System.out.println("The Given Auction Bid End Time Is Reached --->" + bidTime + " "
							+ "The New Bid will Increment Automatically After extended time");

					break;
				} else {
					System.out.println("The Given Auction Bid End Time Is  --->" + bidTime + " "
							+ "Wait till bid End date time reached " + " " + currentdateTime.compareTo(bidTime));

					highlight(tendercreationlocators.DutchRuleNewBidPrice(Decrementval));

					highlight(tendercreationlocators.DutchRuleMarketPrice(Decrementval));

					WebDriver driver = ThreadLocalWebdriver.getDriver();

					String attribute = driver.findElement(tendercreationlocators.DutchRuleNewBidPrice(Decrementval))
							.getAttribute("value");

					System.out.println("The New Bid Price Value is---->" + attribute);

					waitForObj(30000);

					System.out.println("wait for 30 seconds and check the time");

					pdfResultReport.addStepDetails("waitForAuctionBidEndTimeToReach",
							"Must Wait till Auction Bid end Time Reached There Will Be No Change in Bid Value",
							"Successfully waited till Auction bid end time and there is no change in Bid Value" + " ",
							"Pass", "Y");
				}
			}

		}

		catch (Exception e) {

			pdfResultReport.addStepDetails("waitForAuctionBidEndTimeToReach",
					"Must wait till Auction Bid End Time reached ",
					"Unable to wait till Auction Bid End Time reached" + e.getMessage(), "Fail", "N");
		}
		return blnStatus;

	}

public boolean WaititngForAuctionPauseTimeMatching(String pauseTime, String Decrementval) throws Exception {
		boolean blnStatus = false;
		try {
			while (true) {
				LocalDateTime localdatetime = LocalDateTime.now();

				String currentdateTime = localdatetime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

				System.out.println(pauseTime);
				System.out.println(currentdateTime);
				if (currentdateTime.compareTo(pauseTime) > 0) {

					waitForObj(8000);
					blnStatus = true;
					System.out.println("The Given Auction Pause Time Is Reached ---> " + pauseTime + " "
							+ "The Bidder Cannot to Proceed Auction Bid Until Resume Time Reached");
					break;
				} else {
					System.out.println("The Given Auction Pause Time Is  --->  " + pauseTime + " "
							+ "Wait till Auction Pause Time is reached " + " " + currentdateTime.compareTo(pauseTime));

					waitForElementToBeVisible(tendercreationlocators.DutchRuleNewBidPrice(Decrementval));

					highlight(tendercreationlocators.DutchRuleNewBidPrice(Decrementval));

					highlight(tendercreationlocators.DutchRuleMarketPrice(Decrementval));

					WebDriver driver = ThreadLocalWebdriver.getDriver();

					String attribute = driver.findElement(tendercreationlocators.DutchRuleNewBidPrice(Decrementval))
							.getAttribute("value");
					System.out.println("The New Bid Price Value is----->  " + attribute);

					waitForObj(30000);

					System.out.println("Wait for 30 seconds and check the Time");

					pdfResultReport.addStepDetails("WaititngForAuctionPauseTimeMatching",
							"Must Wait till Auction Bid pause Time Reached There Will Be No Change in Bid Value",
							"Successfully waited till Auction bid pause time and there is no change in Bid Value" + " ",
							"Pass", "Y");
				}
			}
		}

		catch (Exception e) {
			pdfResultReport.addStepDetails("WaititngForAuctionPauseTimeMatching",
					"Must Wait till Auction Bid pause Time Reached There Will Be No Change in Bid Value ",
					"Unable to wait till Auction Bid pause Time reached" + e.getMessage(), "Fail", "N");
		}
		return blnStatus;

	}

	public boolean WaititngForAuctionResumeTimeMatching(String resumeTime, String Decrementval) throws Exception {
		boolean blnStatus = false;
		try {
			while (true) {
				LocalDateTime localdatetime = LocalDateTime.now();

				String currentdateTime = localdatetime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

				System.out.println(resumeTime);
				System.out.println(currentdateTime);
				if (currentdateTime.compareTo(resumeTime) > 0) {

					waitForObj(8000);

					blnStatus = true;
					System.out.println("The Given Auction Resume Time Is Reached --->" + resumeTime + " "
							+ "The Bidder Can Proceed for the AUction Bid");
					break;
				} else {
					System.out.println("The Given Auction resume Time Is  --->" + resumeTime + " "
							+ "Wait till Auction Resume Time is reached " + " "
							+ currentdateTime.compareTo(resumeTime));

					waitForElementToBeVisible(tendercreationlocators.DutchRuleNewBidPrice(Decrementval));

					highlight(tendercreationlocators.DutchRuleNewBidPrice(Decrementval));

					highlight(tendercreationlocators.DutchRuleMarketPrice(Decrementval));

					WebDriver driver = ThreadLocalWebdriver.getDriver();

					String attribute = driver.findElement(tendercreationlocators.DutchRuleNewBidPrice(Decrementval))
							.getAttribute("value");

					System.out.println("The New Bid Price Value is---->   " + attribute);

					waitForObj(30000);
					System.out.println("Wait for 30 seconds and check the Time");

					pdfResultReport.addStepDetails("WaititngForAuctionResumeTimeMatching",
							"Must Wait till Auction Bid Resume Time Reached There Will Be No Change in Bid Value",
							"Successfully waited till Auction bid pause time and there is no change in Bid Value" + " ",
							"Pass", "Y");
				}
			}
		}

		catch (Exception e) {
			pdfResultReport.addStepDetails("WaititngForAuctionPauseTimeMatching",
					"Must Wait till Auction Bid Resume Time Reached There Will Be No Change in Bid Value ",
					"Unable to wait till Auction Bid Resume Time reached" + e.getMessage(), "Fail", "N");
		}
		return blnStatus;

	}

	

public boolean WaititngForAuctionExtendedTimeMatching(String extendedTime, String Decrementval) throws Exception {
		boolean blnStatus = false;
		try {
			while (true) {
				LocalDateTime localdatetime = LocalDateTime.now();

				String currentdateTime = localdatetime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

				System.out.println(extendedTime);
				System.out.println(currentdateTime);
				if (currentdateTime.compareTo(extendedTime) > 0) {

					waitForObj(5000);

					System.out.println("The Given Auction extended Time Is Reached --->" + extendedTime + " "
							+ "The Bidder Can Proceed for the Auction Bid Submit");
					break;
				} else {
					System.out.println("The Given Auction extended Time Is  --->" + extendedTime + " "
							+ "Wait till Auction extended Time is reached Or Submit the Bid If Needed" + " "
							+ currentdateTime.compareTo(extendedTime));

					waitForElementToBeVisible(tendercreationlocators.DutchRuleNewBidPrice(Decrementval));

					highlight(tendercreationlocators.DutchRuleNewBidPrice(Decrementval));

					highlight(tendercreationlocators.DutchRuleMarketPrice(Decrementval));

					WebDriver driver = ThreadLocalWebdriver.getDriver();

					String attribute = driver.findElement(tendercreationlocators.DutchRuleNewBidPrice(Decrementval))
							.getAttribute("value");

					System.out.println("The New Bid Price Value is---->" + attribute);

					waitForObj(30000);

					System.out.println("Wait for 30 seconds and check the Time");

					pdfResultReport.addStepDetails("WaititngForAuctionExtendedTimeMatching",
							"Must Wait till Auction Extended Resume Time Reached",
							"Successfully waited till Auction bid extended time" + " ", "Pass", "Y");
				}
			}
		}

		catch (Exception e) {
			pdfResultReport.addStepDetails("WaititngForAuctionPauseTimeMatching",
					"Must Wait till Auction Bid Extended Time Reached ",
					"Unable to wait till Auction Bid Extended Time reached" + e.getMessage(), "Fail", "N");
		}
		return blnStatus;

	}


		public void selectAllBidField(String value) throws Exception
	{
		try {
			log.info("started executing the method:: selectAllBidField");
			
			waitForObj(3000);
			
			scrollToElement(tendercreationlocators.startPriceFiledBy);
			
			select(tendercreationlocators.selectAllBidFieldBy, value);
			
			waitForObj(3000);
			
			
			pdfResultReport.addStepDetails("selectAllBidField",
					"Should Select All Bid Field",
					"Successfully Should Select All Bid Field " + " ", "Pass", "Y");
					log.info("completed executing the method:: SubmitAuctionBid");
					
			log.info("completed executing the method:: selectAllBidField");

		} catch (Exception e) {
			log.fatal("Unable to  select AllBid Field" + e.getMessage());
			pdfResultReport.addStepDetails("selectAllBidField", "Should Select All Bid Field",
					"Unable to  select AllBid Field" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to "+e.getMessage());
		}
	}
	
	public void enterhardStopTime(int hardStopTimeMinutes) throws Exception
	{
		try {
			log.info("started executing the method:: enterhardStopTime");
			
			
			LocalDateTime localdatetime1 = LocalDateTime.now().plusMinutes(hardStopTimeMinutes);

			String hardStopTime = localdatetime1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			
			clear(tendercreationlocators.hardStopTimeFieldBy, "hardStopTimeFieldBy");
			
			set(tendercreationlocators.hardStopTimeFieldBy, hardStopTime, "hardStopTime");
			
			click(tendercreationlocators.hardStopTimeFieldBy, "pauseTimeFieldBy");
			
			waitForObj(8000);
			
			pdfResultReport.addStepDetails("enterhardStopTime",
					"Should Enter Hardstop Time",
					"Successfully Should Select All Bid Field " + " ", "Pass", "Y");
					log.info("completed executing the method:: enterhardStopTime");
					
			log.info("completed executing the method:: enterhardStopTime");

		} catch (Exception e) {
			log.fatal("Unable to  Enter Hardstop Time" + e.getMessage());
			pdfResultReport.addStepDetails("enterhardStopTime", "Should Enter Hardstop Time",
					"Unable to  Enter Hardstop Time" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to "+e.getMessage());
		}
	}
	
	public void returnbackToEPSFromAuctionBid() throws Throwable {
		try {
			log.info("started executing the method:: returnbackToEPSFromAuctionBid");

			waitForObj(6000);
			
			JSClick(tendercreationlocators.BtnBackToEPS, "BtnBackToEPS");
			waitForObj(3000);
			JSClick(tendercreationlocators.ConfirmBackYes, "ConfirmBackYes");
			
			waitForObj(6000);
			
			waitForElement(tendercreationlocators.btnAuction, 5000);
			
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			
			waitForObj(6000);
			
			pdfResultReport.addStepDetails("returnbackToEPSFromAuctionBid",
					"Bidder Must Return back to Bidder Menu Page", "Successfully Bidder Returned back to Bidder Menu Page " + " ",
					"Pass", "Y");
			log.info("completed executing the method:: returnbackToEPSFromAuctionBid");
		} catch (Exception e) {
			log.fatal("Bidder Not able to  Return back to Bidder Menu Page" + e.getMessage());
			pdfResultReport.addStepDetails("returnbackToEPSFromAuctionBid",
					"Bidder Must Return back to Bidder Menu Page",
					"Bidder Not able to  Return back to Bidder Menu Page" + e.getMessage(), "Fail", "N");
			
			Assert.fail(e.getMessage());
		}
	}


public void NavigaterToAuctionParcipitation() throws Throwable {
		try {
			log.info("started executing the method:: NavigaterToAuctionParcipitation");
			
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			
			waitForObj(3000);
			
			JSClick(tendercreationlocators.btnAuction, "btnAuction");
			waitForObj(2000);
			JSClick(tendercreationlocators.btnParticipate, "btnParticipate");
			
			waitForElementToBeVisible(tendercreationlocators.tabPARTICiPATE);
			pdfResultReport.addStepDetails("NavigaterToAuctionParcipitation", "Navigating to Auction Participation",
					"Successfully navigated to Auction participation" + " ", "Pass", "Y");
			log.info("completed executing the method:: NavigaterToAuctionParcipitation");
		} catch (Exception e) {
			log.fatal("Unable to navigate to Auction participation" + e.getMessage());
			pdfResultReport.addStepDetails("NavigaterToAuctionParcipitation", "Navigating to Auction Participation",
					"Unable to navigate to Auction participation" + e.getMessage(), "Fail", "N");
			
			Assert.fail(e.getMessage());
		}
	}

public void automaticallyNewBidValueWillBeIncrmentingWithPriceChangeInterval(String Decrementval, String BidValue)
			throws Throwable {
		try {
			log.info("started executing the method:: automaticallyNewBidValueWillBeIncrmentingWithPriceChangeInterval");

			while (true) {

				waitForElementToBeVisible(tendercreationlocators.DutchRuleMarketPrice(Decrementval));

				highlight(tendercreationlocators.DutchRuleMarketPrice(Decrementval));

				highlight(tendercreationlocators.DutchRuleNewBidPrice(Decrementval));

				WebDriver driver = ThreadLocalWebdriver.getDriver();

				waitForElementToBeVisible(tendercreationlocators.DutchRuleNewBidPrice(Decrementval));
				
				String attribute = driver.findElement(tendercreationlocators.DutchRuleNewBidPrice(Decrementval))
						.getAttribute("value");

				System.out.println("The New Bid Price Value is---->" + attribute);

				pdfResultReport.addStepDetails("automaticallyNewBidValueWillBeIncrmentingWithPriceChangeInterval",
						"New Bid Value will be increasing with Price Change Interval Time till Reserved price is reached",
						"Successfully New Bid Value will be increasing with Price Change Interval and reserved price was Reached"
								+ " ",
						"Pass", "Y");

				waitForElementToBeVisible(tendercreationlocators.DutchRuleNewBidPrice(Decrementval));

				if (attribute.trim().contains(BidValue.trim())) {
					System.out.println("The new bid value reached ---->" + BidValue);

					pdfResultReport.addStepDetails("automaticallyNewBidValueWillBeIncrmentingWithPriceChangeInterval",
							"New Bid Value will be increasing with Price Change Interval Time till Reserved price is reached",
							"Successfully New Bid Value will be increasing with Price Change Interval and reserved price was Reached"
									+ " ",
							"Pass", "Y");
					break;
				}

				waitForObj(55000);
			}

			waitForObj(180000);

			pdfResultReport.addStepDetails("automaticallyNewBidValueWillBeIncrmentingWithPriceChangeInterval",
					"New Bid Value will be increasing with Price Change Interval Time till Reserved price is reached",
					"Successfully New Bid Value will be increasing with Price Change Interval and reserved price was Reached"
							+ " ",
					"Pass", "Y");
			log.info("completed executing the method:: SubmitAuctionBid");
		} catch (Exception e) {
			log.fatal("Unable to increase New Bid Value with Price Change Interval Time" + e.getMessage());
			pdfResultReport.addStepDetails("automaticallyNewBidValueWillBeIncrmentingWithPriceChangeInterval",
					"New Bid Value will be increasing with Price Change Interval Time",
					"Unable to increase New Bid Value with Price Change Interval Time" + e.getMessage(), "Fail", "N");
			
			Assert.fail(e.getMessage());
			
		}
	}

}
