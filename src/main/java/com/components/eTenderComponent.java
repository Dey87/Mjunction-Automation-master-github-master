package com.components;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.baseClasses.AutomationException;
import com.baseClasses.BaseClass_Web;
import com.baseClasses.PDFResultReport;
import com.baseClasses.ThreadLocalWebdriver;
import com.objectRepository.TenderCreation_Locators;
import java.awt.Robot;
import java.io.Closeable;
import java.io.File;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import com.objectRepository.TenderCreation_Locators;
//import com.sun.glass.events.KeyEvent;

public class eTenderComponent extends BaseClass_Web {
	String tenderReferenceNoLocatorText = null;
	//String tenderReferenceNoLocatorText = "1124";
	TenderCreation_Locators tendercreationlocators = new TenderCreation_Locators();
	String BidStartDate = null;
	String BidDueDate = null;
	String BidOpenDate = null;
	LocalDateTime localdatetimeForBid = null;
	String BidnoLocatorText = null;
	String DraftBidnoLocatorText = null;
	
	public eTenderComponent(PDFResultReport pdfresultReport) {
		this.pdfResultReport = pdfresultReport;
	}

	public void verifyPOFieldsInPOCreatorLogin() throws Throwable {
		try {
			log.info("started executing the method::  verifyPOFieldsInPOCreatorLogin");
			waitForObj(5000);
			WebElement myPOTab = ThreadLocalWebdriver.getDriver().findElement(
					By.xpath("//li[@class='uib-tab nav-item ng-scope ng-isolate-scope active'][@heading='My PO']"));
			if (myPOTab.isDisplayed()) {
				System.out.println("PO Creator by default entered in MY PO tab.");
			}
			IsElementPresent(tendercreationlocators.poNoRefNo1);
			click(tendercreationlocators.othersPO, "othersPO");
			waitForElementToBeVisible(tendercreationlocators.statusFilter);
			IsElementPresent(tendercreationlocators.poNoRefNo2);
			click(tendercreationlocators.cancelledPO, "cancelledPO");
			waitForElementToBeVisible(tendercreationlocators.statusFilter);
			IsElementPresent(tendercreationlocators.poNoRefNo3);
			click(tendercreationlocators.myPO, "myPO");
			waitForElementToBeVisible(tendercreationlocators.statusFilter);
			select(tendercreationlocators.statusFilter, pdfResultReport.testData.get("statusFilter"));
			String halfActions27 = "//table[@id='usrmntbLst']/tbody/tr[";
			String otherAction27 = "]/td[10]/span";
			List<WebElement> rows = ThreadLocalWebdriver.getDriver()
					.findElements(By.xpath("//table[@id='usrmntbLst']/tbody/tr"));
			int rowsCount = rows.size();
			int i = 2;
			while (i <= rowsCount) {
				String actions27 = halfActions27 + i + otherAction27;
				WebElement actionXpath = ThreadLocalWebdriver.getDriver().findElement(By.xpath(actions27));
				String actionsText = actionXpath.getText();
				if (actionsText.equals("Not Defined")) {
					System.out.println("Validity is shown as Not Defined");
					i++;
				} else if (actionsText.equals("Expired")) {
					System.out.println("Validity is shown as Expired");
					i++;
				} else {
					String halfactionDropdown = "//table[@id='usrmntbLst']/tbody/tr[", otherHalf = "]/td[14]";
					String actionDropDown = halfactionDropdown + i + otherHalf;

					By actionX = By.xpath(actionDropDown);
					click(actionX, "actionX");
					List<WebElement> actionList = ThreadLocalWebdriver.getDriver()
							.findElements(By.xpath("//table[@id='usrmntbLst']/tbody/tr[" + i + "]/td[14]//ul"));
					for (int j = 0; j < actionList.size(); j++) {
						WebElement individualList = actionList.get(j);
						System.out.println(individualList.getText());
					}
					By releasePODropDown = By.xpath(
							"//table[@id='usrmntbLst']/tbody/tr[" + i + "]/td[14]//ul//span[text()='Release PO']");
					click(releasePODropDown, "releasePODropDown");
					waitForElement(tendercreationlocators.releasePOButton, 5000);
					click(tendercreationlocators.releasePOButton, "releasePOButton");
					click(tendercreationlocators.sendForApprovalNotRequired, "sendForApprovalNotRequired");
					click(tendercreationlocators.sendForApprovalSubmit, "sendForApprovalSubmit");
					waitForElementToBeVisible(tendercreationlocators.statusFilter);
					break;
				}
			}
			pdfResultReport.addStepDetails("verifyPOFieldsInPOCreatorLogin",
					"All the fields in PO List must be validated sucessfully",
					"Successfully validated all the fields " + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyPOFieldsInPOCreatorLogin");
		} catch (Exception e) {
			log.fatal("Unable to validate the fields" + e.getMessage());
			pdfResultReport.addStepDetails("verifyPOFieldsInPOCreatorLogin",
					"All the fields in PO List must be validated sucessfully",
					"Unable to validate the fields" + e.getMessage(), "Fail", "N");
		}
	}

	public void openURL() throws Exception {
		try {
			launchapp(pdfResultReport.testDataValue.get("AppURL"));
			waitForObj(3000);

			pdfResultReport.addStepDetails("openURL", "Application should open the url",
					"Successfully opened the URL" + " ", "Pass", "Y");
		} catch (Exception e) {
			log.fatal("Unable to open the URL" + e.getMessage());
			pdfResultReport.addStepDetails("openURL", "Application should open the url",
					"Unable to open the URL" + e.getMessage(), "Fail", "N");
		}

	}

	public void tendercreatorLogin() throws Throwable {
		try {
			log.info("started executing the method:: tendercreatorLogin");
			click(tendercreationlocators.login, "login");
			set(tendercreationlocators.userName, pdfResultReport.testData.get("TenderCreatorUserName"), "userName");
			waitForObj(5000);
			set(tendercreationlocators.password, pdfResultReport.testData.get("AppPassword"), "password");
			//Handle fixed Captcha (06/11/2020)
			set(tendercreationlocators.Captcha_Login, "1234", "Login_Captcha");
			//Wait statement (Added to handle Captcha temporarily in AWS QA (19/10/2020))
			//waitForObj(50000);
			click(tendercreationlocators.okButton, "okButton");
			waitForElement(tendercreationlocators.dashboardIcon, 5000);
			pdfResultReport.addStepDetails("Tender creator login", "Tender creator must be sucessfully logged in",
					"Successfully logged in as tender creator" + " ", "Pass", "Y");
			log.info("completed executing the method:: tendercreatorLogin");

		} catch (Exception e) {
			log.fatal("Unable to open the URL" + e.getMessage());
			pdfResultReport.addStepDetails("Tender creator login", "Tender creator is not logged in",
					"Unable to login as tender creator" + e.getMessage(), "Fail", "N");
		}
	}

	public void navigateToTenderList() throws Throwable {
		try {
			log.info("started executing the method:: navigateToTenderList");
			// mouseOver(tendercreationlocators.tendersIcon);
			click(tendercreationlocators.tendersIcon, "tendersIcon");
			waitForObj(2000);
			JSClick(tendercreationlocators.tenderCreation, "tenderCreation");
			waitForObj(2000);
			JSClick(tendercreationlocators.createNewRfq, "createNewRfq");

			pdfResultReport.addStepDetails("Navigate to tender List", "Tender list must be navigated successfully",
					"Successfully navigated to tender list" + " ", "Pass", "Y");
			log.info("completed executing the method:: navigateToTenderList");

		} catch (Exception e) {
			log.fatal("Unable to navigate to the tender list" + e.getMessage());
			pdfResultReport.addStepDetails("Navigate to tender List", "Not able to navigate to the tender list",
					"Unable to navigate to the tender list" + e.getMessage(), "Fail", "N");
		}
	}

	public void createTender_WithOptionalItemsAndQtyEditable() throws Throwable {
		try {
			log.info("started executing the method:: createTender_WithOptionalItemsAndQtyEditable");
			click(tendercreationlocators.tendercreationPlusicon, "tendercreationPlusicon");
			waitForElement(tendercreationlocators.templateGroupdropdown, 5000);
			select(tendercreationlocators.templateGroupdropdown, "With Optional Items & Qty Editable by Supplier_V2");
			select(tendercreationlocators.bitPartdropdown, pdfResultReport.testData.get("BidParts"));
			click(tendercreationlocators.bitPartRadio, "bitPartRadio");
			set(tendercreationlocators.tenderReferenceNumber, pdfResultReport.testData.get("TenderReferencNumber"),
					"tenderReferenceNumber");
			select(tendercreationlocators.procurementCategory, "Default_cat");
			select(tendercreationlocators.tenderCurrency, pdfResultReport.testData.get("TenderCurrency"));
			set(tendercreationlocators.probabaleAmountContract, pdfResultReport.testData.get("PAC"),
					"probabaleAmountContract");
			select(tendercreationlocators.biddingCurrency, pdfResultReport.testData.get("BiddingCurrency"));
			select(tendercreationlocators.tenderType, pdfResultReport.testData.get("TenderType"));
			set(tendercreationlocators.detailedDescription,
					pdfResultReport.testData.get("CreateTender-DetailedDescription"), "detailedDescription");
			click(tendercreationlocators.savebutton, "savebutton");
			waitForElement(tendercreationlocators.editTenderTitle, 10000);
			click(tendercreationlocators.otherAttachements, "otherAttachements");
			waitForObj(5000);
			click(tendercreationlocators.addtenderattachmenticon, "addtenderattachmenticon");
			set(tendercreationlocators.supportingdocument, pdfResultReport.testData.get("OtherAttachments"),
					"supportingdocument");
			click(tendercreationlocators.buyerattachemntOK, "buyerattachemntOK");
			click(tendercreationlocators.savebutton, "savebutton");
			click(tendercreationlocators.projectDetailsTab, "projectDetailsTab");
			waitForElement(tendercreationlocators.projectName, 10000);
			set(tendercreationlocators.projectName, pdfResultReport.testData.get("ProjectName"), "projectName");
			set(tendercreationlocators.projectLocation, pdfResultReport.testData.get("ProjectLocation"),
					"projectLocation");
			set(tendercreationlocators.product, pdfResultReport.testData.get("Product"), "product");
			set(tendercreationlocators.contactPerson, pdfResultReport.testData.get("ContactPerson"), "contactPerson");
			click(tendercreationlocators.savebutton, "savebutton");
			click(tendercreationlocators.attachementsTab, "attachementsTab");
			click(tendercreationlocators.openattachementsTab, "openattachementsTab");
			set(tendercreationlocators.label, pdfResultReport.testData.get("Attachments-Label"), "label");
			set(tendercreationlocators.fileName, System.getProperty("user.dir") + "\\MediaFiles\\rfqCreation.xlsx",
					"fileName");
			select(tendercreationlocators.VisibleTo, pdfResultReport.testData.get("Attachments-VisibleTo"));
			click(tendercreationlocators.attachmentOKbutton, "attachmentOKbutton");
			click(tendercreationlocators.savebutton, "savebutton");
			click(tendercreationlocators.dateschedule, "dateschedule");
			waitForElement(tendercreationlocators.bidsubmissionStartDate, 5000);
			set(tendercreationlocators.bidsubmissionStartDate, pdfResultReport.testData.get("BidSubmissionStartDate"),
					"bidsubmissionStartDate");
			set(tendercreationlocators.bidsubmissionDueDate, pdfResultReport.testData.get("BidSubmissionDueDate"),
					"bidsubmissionDueDate");
			set(tendercreationlocators.bidsubmissionOpenDate, pdfResultReport.testData.get("BidOpenDate"),
					"bidsubmissionOpenDate");
			click(tendercreationlocators.savebutton, "savebutton");
			click(tendercreationlocators.BOQOptionalTab, "BOQOptionalTab");
			click(tendercreationlocators.addNonSORItem, "addNonSORItem");
			set(tendercreationlocators.BOQOptionItemCode, pdfResultReport.testData.get("BOQ(Optional)-ItemCode"),
					"BOQOptionItemCode");
			set(tendercreationlocators.BOQOptionItemDescription,
					pdfResultReport.testData.get("BOQ(Optional)-ItemDescription"), "BOQOptionItemDescription");
			select(tendercreationlocators.BOQOptionUOM, pdfResultReport.testData.get("UOM"));
			set(tendercreationlocators.projectquantity, pdfResultReport.testData.get("ProjectQuantity"),
					"projectquantity");
			set(tendercreationlocators.preferedmake, pdfResultReport.testData.get("PreferedMaker"), "preferedmake");
			set(tendercreationlocators.budgetedrate, pdfResultReport.testData.get("BudgetedRate"), "budgetedrate");
			set(tendercreationlocators.remarks, pdfResultReport.testData.get("Remarks"), "remarks");
			click(tendercreationlocators.savebutton, "savebutton");
			click(tendercreationlocators.termsConditionsTab, "termsConditionsTab");
			click(tendercreationlocators.termsconditionsAdd, "termsconditionsAdd");
			set(tendercreationlocators.termsconditionClause, pdfResultReport.testData.get("TermsClause"),
					"termsconditionClause");
			set(tendercreationlocators.termsconditionDetails_WithOptionalItemsAndQtyEditable,
					pdfResultReport.testData.get("TermsDetails"),
					"termsconditionDetails_WithOptionalItemsAndQtyEditable");
			click(tendercreationlocators.savebutton, "savebutton");
			click(tendercreationlocators.technical, "technical");
			click(tendercreationlocators.technicalAdd_WithOptionalItemsAndQtyEditable,
					"technicalAdd_WithOptionalItemsAndQtyEditable");
			set(tendercreationlocators.technicalclause, pdfResultReport.testData.get("Technical-Clause"),
					"technicalclause");
			click(tendercreationlocators.savebutton, "savebutton");
			click(tendercreationlocators.BOQmandatoryTab, "BOQmandatoryTab");
			click(tendercreationlocators.addnonSORBOQMandatoryTab, "addnonSORBOQMandatoryTab");
			set(tendercreationlocators.BOQMadatoryItemCode, pdfResultReport.testData.get("BOQ(Mandatory)-ItemCode"),
					"BOQMadatoryItemCode");
			set(tendercreationlocators.BOQMadatoryItemdescription,
					pdfResultReport.testData.get("BOQ(Mandatory)-ItemDescription"), "BOQMadatoryItemdescription");
			click(tendercreationlocators.savebutton, "savebutton");
			click(tendercreationlocators.submitbutton, "submitbutton");
			pdfResultReport.addStepDetails("Successfully Created Tender",
					"Tender must be created successfully using WithOptionalItemsAndQtyEditable as template group ",
					"Tender is created successfully using WithOptionalItemsAndQtyEditable as template group" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: createTender_WithOptionalItemsAndQtyEditable");

		} catch (Exception e) {
			log.fatal("Not able to create tender" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to create tender",
					"Not able to create tender using WithOptionalItemsAndQtyEditable as template group",
					"Unable to create tender using WithOptionalItemsAndQtyEditable as template group" + e.getMessage(),
					"Fail", "N");
		}
	}

	public void createTender_UVoltasDEMO_generalInformation() throws Throwable {
		try {
			log.info("started executing the method:: createTender_UVoltasDEMO_generalInformation");
			waitForElement(tendercreationlocators.templateGroupdropdown, 5000);
			waitForSpinnerToDisappear();
			select(tendercreationlocators.templateGroupdropdown, "UVoltas DEMO");
			select(tendercreationlocators.bitPartdropdown, pdfResultReport.testData.get("BidParts"));
			click(tendercreationlocators.bitPartRadio, "bitPartRadio");
			set(tendercreationlocators.tenderReferenceNumber, pdfResultReport.testData.get("TenderReferencNumber"),
					"tenderReferenceNumber");
			select(tendercreationlocators.procurementCategory, "Default_cat");
			select(tendercreationlocators.tenderCurrency, pdfResultReport.testData.get("TenderCurrency"));
			set(tendercreationlocators.probabaleAmountContract, pdfResultReport.testData.get("PAC"),
					"probabaleAmountContract");
			select(tendercreationlocators.biddingCurrency, pdfResultReport.testData.get("BiddingCurrency"));
			select(tendercreationlocators.tenderType, pdfResultReport.testData.get("TenderType"));
			set(tendercreationlocators.detailedDescription,
					pdfResultReport.testData.get("CreateTender-DetailedDescription"), "detailedDescription");
			JSClick(tendercreationlocators.savebutton, "savebutton");
			waitForSpinnerToDisappear();
			pdfResultReport.addStepDetails("Successfully Created Tender",
					"Tender must be created successfully using createTender_UVoltasDEMO_generalInformation as template group ",
					"Tender is created successfully using createTender_UVoltasDEMO_generalInformation as template group"
							+ " ",
					"Pass", "Y");
			log.info("completed executing the method:: createTender_UVoltasDEMO_generalInformation");
		} catch (Exception e) {
			log.fatal("Not able to create tender" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to create tender",
					"Not able to create tender using createTender_UVoltasDEMO_generalInformation as template group",
					"Unable to create tender using createTender_UVoltasDEMO_generalInformation as template group"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void createTender_UVoltasDEMO_otherAttachments() throws Throwable {
		try {
			log.info("started executing the method:: createTender_UVoltasDEMO_otherAttachments");
			click(tendercreationlocators.otherAttachements, "otherAttachements");
			waitForSpinnerToDisappear();
			click(tendercreationlocators.addtenderattachmenticon, "addtenderattachmenticon");
			set(tendercreationlocators.supportingdocument, pdfResultReport.testData.get("OtherAttachments"),
					"supportingdocument");
			click(tendercreationlocators.buyerattachemntOK, "buyerattachemntOK");
			JSClick(tendercreationlocators.savebutton, "savebutton");

			waitForSpinnerToDisappear();
			pdfResultReport.addStepDetails("Successfully Created Tender",
					"Tender must be created successfully using createTender_UVoltasDEMO_otherAttachments as template group ",
					"Tender is created successfully using createTender_UVoltasDEMO_otherAttachments as template group"
							+ " ",
					"Pass", "Y");
			log.info("completed executing the method:: createTender_UVoltasDEMO_otherAttachments");

		} catch (Exception e) {
			log.fatal("Not able to create tender" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to create tender",
					"Not able to create tender using createTender_UVoltasDEMO_otherAttachments as template group",
					"Unable to create tender using createTender_UVoltasDEMO_otherAttachments as template group"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void createTender_UVoltasDEMO_attachments() throws Throwable {
		try {
			log.info("started executing the method:: createTender_UVoltasDEMO_attachments");
			click(tendercreationlocators.attachementsTab, "attachementsTab");
			waitForSpinnerToDisappear();
			click(tendercreationlocators.openattachementsTab, "openattachementsTab");
			set(tendercreationlocators.label, pdfResultReport.testData.get("Attachments-Label"), "label");
			set(tendercreationlocators.fileName, System.getProperty("user.dir") + "\\MediaFiles\\rfqCreation.xlsx",
					"fileName");
			select(tendercreationlocators.VisibleTo, pdfResultReport.testData.get("Attachments-VisibleTo"));
			click(tendercreationlocators.attachmentOKbutton, "attachmentOKbutton");
			JSClick(tendercreationlocators.savebutton, "savebutton");

			waitForSpinnerToDisappear();
			pdfResultReport.addStepDetails("Successfully Created Tender",
					"Tender must be created successfully using createTender_UVoltasDEMO_attachments as template group ",
					"Tender is created successfully using createTender_UVoltasDEMO_attachments as template group" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: createTender_UVoltasDEMO_attachments");
		} catch (Exception e) {
			log.fatal("Not able to create tender" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to create tender",
					"Not able to create tender using createTender_UVoltasDEMO_attachments as template group",
					"Unable to create tender using createTender_UVoltasDEMO_attachments as template group"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void createTender_UVoltasDEMO_dateSchedule(int startdatelag, int enddatelag, int opendatelag)
			throws Throwable {
		try {
			log.info("started executing the method:: createTender_UVoltasDEMO_dateSchedule");
			click(tendercreationlocators.dateschedule, "dateschedule");

			waitForSpinnerToDisappear();
			waitForElement(tendercreationlocators.bidsubmissionStartDate, 5000);

			// set(tendercreationlocators.bidsubmissionStartDate,
			// pdfResultReport.testData.get("BidSubmissionStartDate"),"bidsubmissionStartDate");
			set(tendercreationlocators.bidsubmissionStartDate, getBidStartDate(startdatelag), "bidsubmissionStartDate");
			set(tendercreationlocators.bidsubmissionDueDate, getBidDueDate(enddatelag), "bidsubmissionDueDate");
			set(tendercreationlocators.bidsubmissionOpenDate, getBidOpenDate(opendatelag), "bidsubmissionOpenDate");
			click(tendercreationlocators.savebutton, "savebutton");

			waitForSpinnerToDisappear();
			pdfResultReport.addStepDetails("Successfully Created Tender",
					"Tender must be created successfully using createTender_UVoltasDEMO_dateSchedule as template group ",
					"Tender is created successfully using createTender_UVoltasDEMO_dateSchedule as template group"
							+ " ",
					"Pass", "Y");
			log.info("completed executing the method:: createTender_UVoltasDEMO_dateSchedule");
		} catch (Exception e) {
			log.fatal("Not able to create tender" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to create tender",
					"Not able to create tender using createTender_UVoltasDEMO_dateSchedule as template group",
					"Unable to create tender using createTender_UVoltasDEMO_dateSchedule as template group"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void createTender_UVoltasDEMO_dateSchedule() throws Throwable {
		try {
			log.info("started executing the method:: createTender_UVoltasDEMO_dateSchedule");
			click(tendercreationlocators.dateschedule, "dateschedule");
			waitForElement(tendercreationlocators.bidsubmissionStartDate, 5000);
			set(tendercreationlocators.bidsubmissionStartDate, pdfResultReport.testData.get("BidSubmissionStartDate"),
					"bidsubmissionStartDate");
			set(tendercreationlocators.bidsubmissionDueDate, pdfResultReport.testData.get("BidSubmissionDueDate"),
					"bidsubmissionDueDate");
			set(tendercreationlocators.bidsubmissionOpenDate, pdfResultReport.testData.get("BidOpenDate"),
					"bidsubmissionOpenDate");
			click(tendercreationlocators.savebutton, "savebutton");
			pdfResultReport.addStepDetails("Successfully Created Tender",
					"Tender must be created successfully using createTender_UVoltasDEMO_dateSchedule as template group ",
					"Tender is created successfully using createTender_UVoltasDEMO_dateSchedule as template group"
							+ " ",
					"Pass", "Y");
			log.info("completed executing the method:: createTender_UVoltasDEMO_dateSchedule");
		} catch (Exception e) {
			log.fatal("Not able to create tender" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to create tender",
					"Not able to create tender using createTender_UVoltasDEMO_dateSchedule as template group",
					"Unable to create tender using createTender_UVoltasDEMO_dateSchedule as template group"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void createTender_UVoltasDEMO_termsAndCondition() throws Throwable {
		try {
			log.info("started executing the method:: createTender_UVoltasDEMO_termsAndCondition");
			click(tendercreationlocators.termsConditionsTab, "termsConditionsTab");
			waitForSpinnerToDisappear();

			click(tendercreationlocators.termsconditionsAdd, "termsconditionsAdd");
			set(tendercreationlocators.termsconditionClause, pdfResultReport.testData.get("TermsClause"),
					"termsconditionClause");
			// set(tendercreationlocators.termsconditionDetails,
			// pdfResultReport.testData.get("TermsDetails"),"termsconditionDetails");
			JSClick(tendercreationlocators.savebutton, "savebutton");

			waitForSpinnerToDisappear();

			pdfResultReport.addStepDetails("Successfully Created Tender",
					"Tender must be created successfully using createTender_UVoltasDEMO_termsAndCondition as template group ",
					"Tender is created successfully using createTender_UVoltasDEMO_termsAndCondition as template group"
							+ " ",
					"Pass", "Y");
			log.info("completed executing the method:: createTender_UVoltasDEMO_termsAndCondition");
		} catch (Exception e) {
			log.fatal("Not able to create tender" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to create tender",
					"Not able to create tender using createTender_UVoltasDEMO_termsAndCondition as template group",
					"Unable to create tender using createTender_UVoltasDEMO_termsaAndCondition as template group"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void createTender_UVoltasDEMO_technical() throws Throwable {
		try {
			log.info("started executing the method:: createTender_UVoltasDEMO_technical");
			click(tendercreationlocators.technical, "technical");
			waitForSpinnerToDisappear();
			click(tendercreationlocators.technicalAdd_WithOptionalItemsAndQtyEditable, "technicalAdd");
			set(tendercreationlocators.technicalclause, pdfResultReport.testData.get("Technical-Clause"),
					"technicalclause");
			JSClick(tendercreationlocators.savebutton, "savebutton");

			waitForSpinnerToDisappear();

			click(tendercreationlocators.next, "next");
			pdfResultReport.addStepDetails("Successfully Created Tender",
					"Tender must be created successfully using createTender_UVoltasDEMO_technical as template group ",
					"Tender is created successfully using createTender_UVoltasDEMO_technical as template group" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: createTender_UVoltasDEMO_technical");
		} catch (Exception e) {
			log.fatal("Not able to create tender" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to create tender",
					"Not able to create tender using createTender_UVoltasDEMO_technical as template group",
					"Unable to create tender using createTender_UVoltasDEMO_technical as template group"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void createTender_UVoltasDEMO_boqSummary() throws Throwable {
		try {
			log.info("started executing the method:: createTender_UVoltasDEMO_boqSummary");
			click(tendercreationlocators.boqSummary, "boqSummary");
			waitForSpinnerToDisappear();
			click(tendercreationlocators.addNonSORItem, "addNonSORItem");
			set(tendercreationlocators.boqSummaryItemCode, pdfResultReport.testData.get("ItemCode"),
					"boqSummaryItemCode");
			set(tendercreationlocators.boqSummaryItemDescription, pdfResultReport.testData.get("ItemDescription"),
					"boqSummaryItemDescription");
			set(tendercreationlocators.boqSummaryQuantity, pdfResultReport.testData.get("Quantity"),
					"boqSummaryQuantity");
			select(tendercreationlocators.boqSummaryUom, pdfResultReport.testData.get("UOM"));
			set(tendercreationlocators.boqSummarySorRate, pdfResultReport.testData.get("SORRate"), "boqSummarySorRate");
			select(tendercreationlocators.boqSummaryMandatoryItem, pdfResultReport.testData.get("MandatoryItem"));
			JSClick(tendercreationlocators.savebutton, "savebutton");

			waitForSpinnerToDisappear();
			pdfResultReport.addStepDetails("Successfully Created Tender",
					"Tender must be created successfully using createTender_UVoltasDEMO_boqSummary as template group ",
					"Tender is created successfully using createTender_UVoltasDEMO_boqSummary as template group" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: createTender_UVoltasDEMO_boqSummary");
		} catch (Exception e) {
			log.fatal("Not able to create tender" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to create tender",
					"Not able to create tender using createTender_UVoltasDEMO_boqSummary as template group",
					"Unable to create tender using createTender_UVoltasDEMO_boqSummary as template group"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void createTender_TenderTwoCreationByDutta_GeneralInformation() throws Throwable {
		try {
			log.info("started executing the method:: createTender_TenderTwoCreationByDutta");
			select(tendercreationlocators.templateGroupdropdown, "Tender 2 Creation by dutta");
			select(tendercreationlocators.bitPartdropdown, pdfResultReport.testData.get("BidParts"));
			click(tendercreationlocators.bitPartRadio, "bitPartRadio");
			set(tendercreationlocators.tenderReferenceNumber, pdfResultReport.testData.get("TenderReferencNumber"),
					"tenderReferenceNumber");
			select(tendercreationlocators.procurementCategory, "Default_cat");
			select(tendercreationlocators.tenderCurrency, pdfResultReport.testData.get("TenderCurrency"));
			set(tendercreationlocators.probabaleAmountContract, pdfResultReport.testData.get("PAC"),
					"probabaleAmountContract");
			select(tendercreationlocators.biddingCurrency, pdfResultReport.testData.get("BiddingCurrency"));
			select(tendercreationlocators.tenderType, pdfResultReport.testData.get("TenderType2"));
			click(tendercreationlocators.addBidders, "addBidders");
			click(tendercreationlocators.bidderCompany_TechMahindra, "bidderCompany_TechMahindra");
			click(tendercreationlocators.addBidderCompany, "addBidderCompany");
			select(tendercreationlocators.previousTenderNo, "1000 (Cigniti-Sample Tender)");
			set(tendercreationlocators.detailedDescription,
					pdfResultReport.testData.get("CreateTender-DetailedDescription"), "detailedDescription");
			click(tendercreationlocators.savebutton, "savebutton");

			pdfResultReport.addStepDetails("GeneralInformation tab should be saved Successfully",
					"GeneralInformation tab should be saved Successfully using createTender_TenderTwoCreationByDutta_GeneralInformation",
					"GeneralInformation tab save is successfully using createTender_TenderTwoCreationByDutta_GeneralInformation"
							+ " ",
					"Pass", "Y");
			log.info("completed executing the method:: createTender_TenderTwoCreationByDutta_GeneralInformation");
		} catch (Exception e) {
			log.fatal("Not able save GeneralInformation tab" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to create tender",
					"Not able to create tender using createTender_TenderTwoCreationByDutta_GeneralInformation as template group",
					"Unable to create tender using createTender_TenderTwoCreationByDutta_GeneralInformation as template group"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void createTender_TenderTwoCreationByDutta_DateSchedule() throws Throwable {
		try {
			log.info("started executing the method:: createTender_TenderTwoCreationByDutta_DateSchedule");
			click(tendercreationlocators.dateschedule, "dateschedule");
			set(tendercreationlocators.bidsubmissionStartDate, pdfResultReport.testData.get("BidSubmissionStartDate"),
					"bidsubmissionStartDate");
			set(tendercreationlocators.bidsubmissionDueDate, pdfResultReport.testData.get("BidSubmissionDueDate"),
					"bidsubmissionDueDate");
			set(tendercreationlocators.bidsubmissionOpenDate, pdfResultReport.testData.get("BidOpenDate"),
					"bidsubmissionOpenDate");
			click(tendercreationlocators.savebutton, "savebutton");

			pdfResultReport.addStepDetails("Successfully Created Tender",
					"Tender must be created successfully using createTender_TenderTwoCreationByDutta_DateSchedule as template group ",
					"Tender is created successfully using createTender_TenderTwoCreationByDutta_DateSchedule as template group"
							+ " ",
					"Pass", "Y");
			log.info("completed executing the method:: createTender_TenderTwoCreationByDutta_DateSchedule");
		} catch (Exception e) {
			log.fatal("Not able to create tender" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to create tender",
					"Not able to create tender using createTender_TenderTwoCreationByDutta_DateSchedule as template group",
					"Unable to create tender using createTender_TenderTwoCreationByDutta_DateSchedule as template group"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void createTender_TenderTwoCreationByDutta_ProjectDetails() throws Throwable {
		try {
			log.info("started executing the method:: createTender_TenderTwoCreationByDutta_ProjectDetails");
			click(tendercreationlocators.projectDetailsTab, "projectDetailsTab");
			set(tendercreationlocators.projectName, pdfResultReport.testData.get("ProjectName"), "projectName");
			set(tendercreationlocators.projectLocation, pdfResultReport.testData.get("ProjectLocation"),
					"projectLocation");
			set(tendercreationlocators.product, pdfResultReport.testData.get("Product"), "product");
			set(tendercreationlocators.preferredCountryofOrigin,
					pdfResultReport.testData.get("PreferredCountryofOrigin"), "preferredCountryofOrigin");
			set(tendercreationlocators.projectBrief, pdfResultReport.testData.get("ProjectBrief"), "projectBrief");
			set(tendercreationlocators.instructionToTheSuppliers,
					pdfResultReport.testData.get("InstructionToTheSuppliers"), "instructionToTheSuppliers");
			set(tendercreationlocators.contactPerson, pdfResultReport.testData.get("ContactPerson"), "contactPerson");
			click(tendercreationlocators.savebutton, "savebutton");

			pdfResultReport.addStepDetails("Successfully Created Tender",
					"Tender must be created successfully using createTender_TenderTwoCreationByDutta_ProjectDetails as template group ",
					"Tender is created successfully using createTender_TenderTwoCreationByDutta_ProjectDetails as template group"
							+ " ",
					"Pass", "Y");
			log.info("completed executing the method:: createTender_TenderTwoCreationByDutta_ProjectDetails");
		} catch (Exception e) {
			log.fatal("Not able to create tender" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to create tender",
					"Not able to create tender using createTender_TenderTwoCreationByDutta_ProjectDetails as template group",
					"Unable to create tender using createTender_TenderTwoCreationByDutta_ProjectDetails as template group"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void createTender_TenderTwoCreationByDutta_TermsAndConditions() throws Throwable {
		try {
			log.info("started executing the method:: createTender_TenderTwoCreationByDutta_TermsAndConditions");
			click(tendercreationlocators.termsConditionsTab, "termsConditionsTab");
			click(tendercreationlocators.termsconditionsAdd, "termsconditionsAdd");
			set(tendercreationlocators.termsconditionClause, pdfResultReport.testData.get("TermsClause"),
					"termsconditionClause");
			set(tendercreationlocators.termsconditionDetails, pdfResultReport.testData.get("TermsDetails"),
					"termsconditionDetails");
			click(tendercreationlocators.savebutton, "savebutton");
			pdfResultReport.addStepDetails("Successfully Created Tender",
					"Tender must be created successfully using createTender_TenderTwoCreationByDutta_TermsAndConditions as template group ",
					"Tender is created successfully using createTender_TenderTwoCreationByDutta_TermsAndConditions as template group"
							+ " ",
					"Pass", "Y");
			log.info("completed executing the method:: createTender_TenderTwoCreationByDutta_TermsAndConditions");
		} catch (Exception e) {
			log.fatal("Not able to create tender" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to create tender",
					"Not able to create tender using createTender_TenderTwoCreationByDutta_TermsAndConditions as template group",
					"Unable to create tender using createTender_TenderTwoCreationByDutta_TermsAndConditions as template group"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void createTender_TenderTwoCreationByDutta_Technical() throws Throwable {
		try {
			log.info("started executing the method:: createTender_TenderTwoCreationByDutta_Technical");
			click(tendercreationlocators.technical, "technical");
			click(tendercreationlocators.technicalAdd, "technicalAdd");
			set(tendercreationlocators.technicalclause_TenderTwoCreationByDutta,
					pdfResultReport.testData.get("Technical-Clause"), "technicalclause_TenderTwoCreationByDutta");
			set(tendercreationlocators.technicaldetails, pdfResultReport.testData.get("Technical-Details"),
					"technicaldetails");
			click(tendercreationlocators.savebutton, "savebutton");
			pdfResultReport.addStepDetails("Successfully Created Tender",
					"Tender must be created successfully using createTender_TenderTwoCreationByDutta_Technical as template group ",
					"Tender is created successfully using createTender_TenderTwoCreationByDutta_Technical as template group"
							+ " ",
					"Pass", "Y");
			log.info("completed executing the method:: createTender_TenderTwoCreationByDutta_Technical");
		} catch (Exception e) {
			log.fatal("Not able to create tender" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to create tender",
					"Not able to create tender using createTender_TenderTwoCreationByDutta_Technical as template group",
					"Unable to create tender using createTender_TenderTwoCreationByDutta_Technical as template group"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void createTender_TenderTwoCreationByDutta_BOQMandatory() throws Throwable {
		try {
			log.info("started executing the method:: createTender_TenderTwoCreationByDutta_BOQMandatory");
			click(tendercreationlocators.BOQmandatoryTab, "BOQmandatoryTab");
			click(tendercreationlocators.addNonSORItem, "addnonSORBOQMandatoryTab");
			set(tendercreationlocators.BOQMadatoryItemCode_tender2creationbydutta,
					pdfResultReport.testData.get("BOQ(Mandatory)-ItemCode"),
					"BOQMadatoryItemCode_tender2creationbydutta");
			set(tendercreationlocators.BOQMadatoryItemdescription_tender2creationbydutta,
					pdfResultReport.testData.get("BOQ(Mandatory)-ItemDescription"),
					"BOQMadatoryItemdescription_tender2creationbydutta");
			click(tendercreationlocators.savebutton, "savebutton");
			click(tendercreationlocators.submitbutton, "submitbutton");
			waitForObj(10000);
			pdfResultReport.addStepDetails("Successfully Created Tender",
					"Tender must be created successfully using createTender_TenderTwoCreationByDutta_BOQMandatory as template group ",
					"Tender is created successfully using createTender_TenderTwoCreationByDutta_BOQMandatory as template group"
							+ " ",
					"Pass", "Y");
			log.info("completed executing the method:: createTender_TenderTwoCreationByDutta_BOQMandatory");

		} catch (Exception e) {
			log.fatal("Not able to create tender" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to create tender",
					"Not able to create tender using createTender_TenderTwoCreationByDutta_BOQMandatory as template group",
					"Unable to create tender using createTender_TenderTwoCreationByDutta_BOQMandatory as template group"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public String tenderIdSave() throws Throwable {
		log.info("started executing the method:: tenderIdSave");
		tenderReferenceNoLocatorText = text(tendercreationlocators.tenderReferenceNoLocator).trim();
		System.out.println(tenderReferenceNoLocatorText);
		return tenderReferenceNoLocatorText;
	}

	public void createTender_TenderTwoCreationByDutta_SendForApproval() throws Throwable {
		try {
			log.info("started executing the method:: createTender_TenderTwoCreationByDutta_SendForApproval");
			click(tendercreationlocators.Userdefined, "Userdefined");
			click(tendercreationlocators.sectionWiseComments, "sectionWiseComments");
			set(tendercreationlocators.comments, pdfResultReport.testData.get("UserDefinedApprover-Comments"),
					"comments");
			click(tendercreationlocators.sendForApproval, "sendForApproval");
			WebDriverWait wait = new WebDriverWait(ThreadLocalWebdriver.getDriver(), 100);
			wait.until(ExpectedConditions.visibilityOfElementLocated(tendercreationlocators.defaultCatBy));
			pdfResultReport.addStepDetails("Successfully Created Tender",
					"Tender must be created successfully using createTender_TenderTwoCreationByDutta_SendForApproval as template group ",
					"Tender is created successfully using createTender_TenderTwoCreationByDutta_SendForApproval as template group"
							+ " ",
					"Pass", "Y");
			log.info("completed executing the method:: createTender_TenderTwoCreationByDutta_SendForApproval");
		} catch (Exception e) {
			log.fatal("Not able to create tender" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to create tender",
					"Not able to create tender using createTender_TenderTwoCreationByDutta_SendForApproval as template group",
					"Unable to create tender using createTender_TenderTwoCreationByDutta_SendForApproval as template group"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void tenderApproval1_Approval1PageValidation() throws Throwable {
		try {
			log.info("started executing the method:: tenderApproval_Approval1PageValidation");
			tenderLogout();
			tenderApproverLogin();
			click(tendercreationlocators.workFlow, "workFlow");
			click(tendercreationlocators.pending, "pending");
			waitForObj(5000);
			set(tendercreationlocators.search, tenderReferenceNoLocatorText, "search");
			IsElementPresent(tendercreationlocators.workFlowStatus);
			pdfResultReport.addStepDetails("Successfully able to locate created tender ",
					"Tender must be located successfully using tenderApproval1_Approval1PageValidation",
					"Tender is located successfully using tenderApproval1_Approval1PageValidation" + " ", "Pass", "Y");
			log.info("completed executing the method:: tenderApproval1_Approval1PageValidation");
		} catch (Exception e) {
			log.fatal("Not able to Approve Tender" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to locate created tender",
					"Not able to locate created using tenderApproval1_Approval1PageValidation",
					"Unable to locate created Tender using tenderApproval1_Approval1PageValidation" + e.getMessage(),
					"Fail", "N");
		}
	}

	public void tenderApproval1_CommentSectionApproval() throws Throwable {
		try {
			log.info("started executing the method:: tenderApproval_CommentSectionApproval");
			click(tendercreationlocators.details, "details");
			IsElementPresent(tendercreationlocators.approverComment);
			click(tendercreationlocators.dateScheduleTab, "dateScheduleTab");
			IsElementPresent(tendercreationlocators.approverComment);
			click(tendercreationlocators.projectDetailsTemplate, "projectDetailsTemplate");
			IsElementPresent(tendercreationlocators.approverComment);
			click(tendercreationlocators.termsAndConditionsTemplate, "termsAndConditionsTemplate");
			IsElementPresent(tendercreationlocators.approverComment);
			click(tendercreationlocators.technicalTab, "technicalTab");
			IsElementPresent(tendercreationlocators.approverComment);
			click(tendercreationlocators.boqMandatoryTab, "boqMandatoryTab");
			IsElementPresent(tendercreationlocators.approverComment);
			pdfResultReport.addStepDetails("Comment section is present",
					"Comment section is present is verified successfully using tenderApproval1_CommentSectionApproval",
					"Comment section is present is verified using tenderApproval1_CommentSectionApproval as template group"
							+ " ",
					"Pass", "Y");
			log.info("completed executing the method:: tenderApproval1_CommentSectionApproval");
		} catch (Exception e) {
			log.fatal("Not able to create tender" + e.getMessage());
			pdfResultReport.addStepDetails("Not able locate comment",
					"Not able to locate comment using tenderApproval1_CommentSectionApproval as template group",
					"Unable to locate comment using tenderApproval1_CommentSectionApproval as template group"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void tenderApproval1_ProvidingComment() throws Throwable {
		try {
			log.info("started executing the method:: tenderApproval_ProvidingComment");
			// BOQ Mandatory
			set(tendercreationlocators.commentBoqMandatory,
					pdfResultReport.testData.get("TenderApproval-BOQMandatoryCommentTab"), "commentBoqMandatory");
			click(tendercreationlocators.saveTabComment_BoqMandatory, "saveTabComment_BoqMandatory");
			IsElementPresent(tendercreationlocators.alertTab);
			click(tendercreationlocators.alertTabOk, "alertTabOk");
			click(tendercreationlocators.technicalTab, "technicalTab");
			waitForObj(5000);
			// Technical
			set(tendercreationlocators.commentTechnical,
					pdfResultReport.testData.get("TenderApproval-TechnicalCommentTab"), "commentTechnical");
			click(tendercreationlocators.saveTabComment_Technical, "saveTabComment_Technical");
			IsElementPresent(tendercreationlocators.alertTab);
			click(tendercreationlocators.alertTabOk, "alertTabOk");
			click(tendercreationlocators.termsAndConditionsTemplate, "termsAndConditionsTemplate");
			waitForObj(5000);
			// Terms and Conditions
			set(tendercreationlocators.commentTermsAndConditions,
					pdfResultReport.testData.get("TenderApproval-TermsAndConditionsCommentTab"),
					"commentTermsAndConditions");
			click(tendercreationlocators.saveTabComment_TermsAndConditions, "saveTabComment_TermsAndConditions");
			IsElementPresent(tendercreationlocators.alertTab);
			click(tendercreationlocators.alertTabOk, "alertTabOk");
			click(tendercreationlocators.projectDetailsTemplate, "projectDetailsTemplate");
			waitForObj(5000);
			// Project Details
			set(tendercreationlocators.commentProjectDetails,
					pdfResultReport.testData.get("TenderApproval-ProjectDetailsCommentTab"), "commentProjectDetails");
			click(tendercreationlocators.saveTabComment_ProjectDetails, "saveTabComment_ProjectDetails");
			IsElementPresent(tendercreationlocators.alertTab);
			click(tendercreationlocators.alertTabOk, "alertTabOk");
			click(tendercreationlocators.dateScheduleTab, "dateScheduleTab");
			waitForObj(5000);
			// Date Schedule
			set(tendercreationlocators.commentDateSchedule,
					pdfResultReport.testData.get("TenderApproval-DateScheduleCommentTab"), "commentDateSchedule");
			click(tendercreationlocators.saveTabComment_DateSchedule, "saveTabComment_DateSchedule");
			IsElementPresent(tendercreationlocators.alertTab);
			click(tendercreationlocators.alertTabOk, "alertTabOk");
			click(tendercreationlocators.generalInformationTab, "generalInformationTab");
			waitForObj(5000);
			// General Information
			pdfResultReport.addStepDetails("Successfully provided comments",
					"Created Tender must be provided with comments successfully using tenderApproval1_ProvidingComment as template group ",
					"Tender is created successfully using tenderApproval1_ProvidingComment as template group" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: tenderApproval1_ProvidingComment");
		} catch (Exception e) {
			log.fatal("Not able to provide comments" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to provide comments",
					"Not able to provide comments using tenderApproval1_ProvidingComment as template group",
					"Unable to provide comments using tenderApproval1_ProvidingComment as template group"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void tenderApproval1_ProvidingOverallApproverComment() throws Throwable {
		try {
			log.info("started executing the method:: tenderApproval_ProvidingOverallApproverComment");
			click(tendercreationlocators.approverComment, "approverComment");
			set(tendercreationlocators.comment, pdfResultReport.testData.get("TenderApproval-DateScheduleCommentTab"),
					"comment");
			click(tendercreationlocators.approve, "approve");
			// waitForObj(5000);
			waitForSpinnerToDisappear();

			pdfResultReport.addStepDetails("Successfully provided overall approval",
					"Created Tender must be provided overall approval using tenderApproval1_ProvidingOverallApproverComment as template group ",
					"Tender overall approval successfully using tenderApproval1_ProvidingOverallApproverComment as template group"
							+ " ",
					"Pass", "Y");
			log.info("completed executing the method:: tenderApproval1_ProvidingOverallApproverComment");
		} catch (Exception e) {
			log.fatal("Not able provided overall approval comment " + e.getMessage());
			pdfResultReport.addStepDetails("Not able provided overall approval",
					"Not able provided overall approval using tenderApproval1_ProvidingOverallApproverComment as template group",
					"Unable approve created tender using tenderApproval1_ProvidingOverallApproverComment as template group"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void tenderApproval2_ApprovalPageValidation() throws Throwable {
		try {
			log.info("started executing the method:: tenderApproval2_ApprovalPageValidation");
			tenderLogout();
			tenderApprover2Login();
			click(tendercreationlocators.workFlow, "workFlow");
			click(tendercreationlocators.pending, "pending");
			waitForObj(5000);
			set(tendercreationlocators.search, tenderReferenceNoLocatorText, "search");
			pdfResultReport.addStepDetails("Not able to locate created tender",
					"Not able to locate created using tenderApproval2_ApprovalPageValidation",
					"Unable to locate created Tender using tenderApproval2_ApprovalPageValidation", "Pass", "Y");
			log.info("completed executing the method:: tenderApproval2_ApprovalPageValidation");
		} catch (Exception e) {
			log.fatal("Not able to Approve Tender" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to locate created tender",
					"Not able to locate created using tenderApproval2_ApprovalPageValidation",
					"Unable to locate created Tender using tenderApproval2_ApprovalPageValidation" + e.getMessage(),
					"Fail", "N");
		}
	}

	public void tenderApproval2_CommentSectionApproval() throws Throwable {
		try {
			log.info("started executing the method:: tenderApproval_CommentSectionApproval");
			click(tendercreationlocators.details, "details");
			IsElementPresent(tendercreationlocators.approverComment);
			click(tendercreationlocators.dateScheduleTab, "dateScheduleTab");
			IsElementPresent(tendercreationlocators.approverComment);
			click(tendercreationlocators.projectDetailsTemplate, "projectDetailsTemplate");
			IsElementPresent(tendercreationlocators.approverComment);
			click(tendercreationlocators.termsAndConditionsTemplate, "termsAndConditionsTemplate");
			IsElementPresent(tendercreationlocators.approverComment);
			click(tendercreationlocators.technicalTab, "technicalTab");
			IsElementPresent(tendercreationlocators.approverComment);
			click(tendercreationlocators.boqMandatoryTab, "boqMandatoryTab");
			IsElementPresent(tendercreationlocators.approverComment);
			pdfResultReport.addStepDetails("Comment section is present",
					"Comment section is present is verified successfully using tenderApproval2_CommentSectionApproval",
					"Comment section is present is verified using tenderApproval2_CommentSectionApproval as template group"
							+ " ",
					"Pass", "Y");
			log.info("completed executing the method:: tenderApproval2_CommentSectionApproval");
		} catch (Exception e) {
			log.fatal("Not able to create tender" + e.getMessage());
			pdfResultReport.addStepDetails("Not able locate comment",
					"Not able to locate comment using tenderApproval2_CommentSectionApproval as template group",
					"Unable to locate comment using tenderApproval2_CommentSectionApproval as template group"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void tenderApproval2_ProvidingComment() throws Throwable {
		try {
			log.info("started executing the method:: tenderApproval2_ProvidingComment");
			// BOQ Mandatory
			set(tendercreationlocators.commentBoqMandatory,
					pdfResultReport.testData.get("TenderApproval-BOQMandatoryCommentTab"), "commentBoqMandatory");
			click(tendercreationlocators.saveTabComment_BoqMandatory, "saveTabComment_BoqMandatory");
			IsElementPresent(tendercreationlocators.alertTab);
			click(tendercreationlocators.alertTabOk, "alertTabOk");
			click(tendercreationlocators.technicalTab, "technicalTab");
			waitForObj(5000);
			// Technical
			set(tendercreationlocators.commentTechnical,
					pdfResultReport.testData.get("TenderApproval-TechnicalCommentTab"), "commentTechnical");
			click(tendercreationlocators.saveTabComment_Technical, "saveTabComment_Technical");
			IsElementPresent(tendercreationlocators.alertTab);
			click(tendercreationlocators.alertTabOk, "alertTabOk");
			click(tendercreationlocators.termsAndConditionsTemplate, "termsAndConditionsTemplate");
			waitForObj(5000);
			// Terms and Conditions
			set(tendercreationlocators.commentTermsAndConditions,
					pdfResultReport.testData.get("TenderApproval-TermsAndConditionsCommentTab"),
					"commentTermsAndConditions");
			click(tendercreationlocators.saveTabComment_TermsAndConditions, "saveTabComment_TermsAndConditions");
			IsElementPresent(tendercreationlocators.alertTab);
			click(tendercreationlocators.alertTabOk, "alertTabOk");
			click(tendercreationlocators.projectDetailsTemplate, "projectDetailsTemplate");
			waitForObj(5000);
			// Project Details
			set(tendercreationlocators.commentProjectDetails,
					pdfResultReport.testData.get("TenderApproval-ProjectDetailsCommentTab"), "commentProjectDetails");
			click(tendercreationlocators.saveTabComment_ProjectDetails, "saveTabComment_ProjectDetails");
			IsElementPresent(tendercreationlocators.alertTab);
			click(tendercreationlocators.alertTabOk, "alertTabOk");
			click(tendercreationlocators.dateScheduleTab, "dateScheduleTab");
			waitForObj(5000);
			// Date Schedule
			set(tendercreationlocators.commentDateSchedule,
					pdfResultReport.testData.get("TenderApproval-DateScheduleCommentTab"), "commentDateSchedule");
			click(tendercreationlocators.saveTabComment_DateSchedule, "saveTabComment_DateSchedule");
			IsElementPresent(tendercreationlocators.alertTab);
			click(tendercreationlocators.alertTabOk, "alertTabOk");
			click(tendercreationlocators.generalInformationTab, "generalInformationTab");
			waitForObj(5000);
			// General Information
			pdfResultReport.addStepDetails("Successfully provided comments",
					"Created Tender must be provided with comments successfully using tenderApproval2_ProvidingComment as template group ",
					"Tender is created successfully using tenderApproval2_ProvidingComment as template group" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: tenderApproval2_ProvidingComment");
		} catch (Exception e) {
			log.fatal("Not able to provide comments" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to provide comments",
					"Not able to provide comments using tenderApproval2_ProvidingComment as template group",
					"Unable to provide comments using tenderApproval2_ProvidingComment as template group"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void tenderApproval2_ProvidingOverall2ApproverComment() throws Throwable {
		try {
			log.info("started executing the method:: tenderApproval2_ProvidingOverall2ApproverComment");
			click(tendercreationlocators.approverComment, "approverComment");
			set(tendercreationlocators.comment, pdfResultReport.testData.get("TenderApproval-DateScheduleCommentTab"),
					"comment");
			click(tendercreationlocators.approve, "approve");
			// waitForObj(10000);
			waitForSpinnerToDisappear();
			pdfResultReport.addStepDetails("Successfully provided overall approval",
					"Created Tender must be provided overall approval using tenderApproval2_ProvidingOverall2ApproverComment as template group ",
					"Tender overall approval successfully using tenderApproval2_ProvidingOverall2ApproverComment as template group"
							+ " ",
					"Pass", "Y");
			log.info("completed executing the method:: tenderApproval2_ProvidingOverall2ApproverComment");
		} catch (Exception e) {
			log.fatal("Not able provided overall approval" + e.getMessage());
			pdfResultReport.addStepDetails("Not able provided overall approval",
					"Not able provided overall approval using tenderApproval2_ProvidingOverall2ApproverComment as template group",
					"Unable approve created tender using tenderApproval2_ProvidingOverall2ApproverComment as template group"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void navigateToTenderListing() throws Throwable {
		try {
			log.info("started executing the method:: navigateToTenderList");
			// mouseOver(tendercreationlocators.tendersIcon);
			JSClick(tendercreationlocators.tendersIcon, "tendersIcon");
			JSClick(tendercreationlocators.tenderList, "tenderList");
			WebDriver driver = ThreadLocalWebdriver.getDriver();
			checkPageIsReady();
			waitForElementToBeVisible(tendercreationlocators.defaultCatBy);
			waitTillSpinnerDisable(driver, tendercreationlocators.LoadingBy);
			waitForObj(3000);

			pdfResultReport.addStepDetails("Navigate to tender List", "Tender list must be navigated successfully",
					"Successfully navigated to tender list" + " ", "Pass", "Y");
			log.info("completed executing the method:: navigateToTenderList");

		} catch (Exception e) {
			log.fatal("Unable to navigate to the tender list" + e.getMessage());
			pdfResultReport.addStepDetails("Navigate to tender List", "Not able to navigate to the tender list",
					"Unable to navigate to the tender list" + e.getMessage(), "Fail", "N");
		}
	}

	public void tenderStatusAndTenderStage() throws Throwable {
		try {
			waitForObj(5000);
			tenderLogout();
			tendercreatorLogin();
			waitForElement(tendercreationlocators.dashboardIcon, 5000);
			navigateToTenderListing();
			enterTenderIdInSearch();
			IsElementPresent(tendercreationlocators.tenderCreatedStatus);
			pdfResultReport.addStepDetails("Successfully shown Tender status as published",
					"Tender status must be shown as published using tenderStatusAndTenderStage",
					"Tender status must be shown as published using tenderStatusAndTenderStage" + " ", "Pass", "Y");
			log.info("completed executing the method:: tenderStatusAndTenderStage");
		} catch (Exception e) {
			log.fatal("Unable to show the status" + e.getMessage());
			pdfResultReport.addStepDetails("Unable to show the status",
					"Not able to show the tender status using tenderStatusAndTenderStage",
					"Unable to show the status using tenderStatusAndTenderStage" + e.getMessage(), "Fail", "N");
		}
	}

	public void createTender_UVoltasDEMO_previewAll() throws Throwable {
		try {
			log.info("started executing the method:: createTender_UVoltasDEMO_previewAll");
			waitForObj(3000);
			click(tendercreationlocators.previewButton, "previewButton");

			waitForSpinnerToDisappear();
			pdfResultReport.addStepDetails("Successfully Created Tender",
					"Tender must be created successfully using UVoltasDEMO previewAll as template group ",
					"Tender is created successfully using UVoltasDEMO previewAll as template group" + " ", "Pass", "Y");
			log.info("completed executing the method:: createTender_UVoltasDEMO_previewAll");
		} catch (Exception e) {
			log.fatal("Not able to create tender" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to create tender",
					"Not able to create tender using createTender_UVoltasDEMO_previewAll as template group",
					"Unable to create tender using createTender_UVoltasDEMO_previewAll as template group"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void tenderApproverLogin() throws Throwable {
		try {
			log.info("started executing the method:: tenderApproverLogin");
			click(tendercreationlocators.login, "login");
			set(tendercreationlocators.userName, pdfResultReport.testData.get("TenderApproverUserName"), "userName");
			waitForObj(5000);
			set(tendercreationlocators.password, pdfResultReport.testData.get("AppPassword"), "password");
			//Handle fixed Captcha (06/11/2020)
			set(tendercreationlocators.Captcha_Login, "1234", "Login_Captcha");
			click(tendercreationlocators.okButton, "okButton");
			waitForObj(10000);
			pdfResultReport.addStepDetails("Tender creator login", "Tender approver must be sucessfully logged in",
					"Successfully logged in as tender approver" + " ", "Pass", "Y");
			log.info("completed executing the method:: tenderApproverLogin");

		} catch (Exception e) {
			log.fatal("Unable to open the URL" + e.getMessage());
			pdfResultReport.addStepDetails("Tender Approver login", "Tender Approver is not logged in",
					"Unable to login as tender Approver" + e.getMessage(), "Fail", "N");
		}
	}

	public void tenderApprover2Login() throws Throwable {
		try {
			log.info("started executing the method:: tenderApproverLogin");
			click(tendercreationlocators.login, "login");
			set(tendercreationlocators.userName, pdfResultReport.testData.get("TenderApprover2UserName"), "userName");
			waitForObj(2000);
			set(tendercreationlocators.password, pdfResultReport.testData.get("AppPassword"), "password");
			//Handle fixed Captcha (06/11/2020)
			set(tendercreationlocators.Captcha_Login, "1234", "Login_Captcha");
			click(tendercreationlocators.okButton, "okButton");
			waitForElement(tendercreationlocators.dashboardIcon, 5000);
			pdfResultReport.addStepDetails("Tender Approver2 login", "Tender approver2 must be sucessfully logged in",
					"Successfully logged in as tender approver2" + " ", "Pass", "Y");
			log.info("completed executing the method:: tenderApprover2Login");

		} catch (Exception e) {
			log.fatal("Unable to open the URL" + e.getMessage());
			pdfResultReport.addStepDetails("Tender Approver login", "Tender Approver is not logged in",
					"Unable to login as tender Approver" + e.getMessage(), "Fail", "N");
		}
	}

	public void tenderLogout() throws Throwable {
		try {
			log.info("started executing the method:: tendercreatorLogout");
			waitForObj(5000);

			JSClick(tendercreationlocators.logoutOption, "logoutOption");
			click(tendercreationlocators.logout, "logout");
			click(tendercreationlocators.logoutConfirmation, "logoutConfirmation");
			ThreadLocalWebdriver.getDriver().navigate().to("https://epsnewprodaws.mjunction.in/EPSV2Web/");
			waitForObj(10000);
			pdfResultReport.addStepDetails("Tender creator logout", "Tender creator must be sucessfully logout",
					"Successfully logout" + " ", "Pass", "Y");
			log.info("completed executing the method:: tendercreatorLogout");

		} catch (Exception e) {
			log.fatal("Unable to logout as tender creator" + e.getMessage());
			pdfResultReport.addStepDetails("Tender creator logout", "Tender creator is not logged out",
					"Unable to logout as tender creator" + e.getMessage(), "Fail", "N");
		}
	}

	public void tenderApprovalStatus_Review() throws Throwable {
		try {
			log.info("started executing the method:: tenderStatus_Review");
			openURL();
			tendercreatorLogin();
			navigateToTenderList();
			createTender_TenderTwoCreationByDutta_GeneralInformation();
			createTender_TenderTwoCreationByDutta_DateSchedule();
			createTender_TenderTwoCreationByDutta_ProjectDetails();
			createTender_TenderTwoCreationByDutta_TermsAndConditions();
			createTender_TenderTwoCreationByDutta_Technical();
			createTender_TenderTwoCreationByDutta_BOQMandatory();
			tenderIdSave();
			createTender_TenderTwoCreationByDutta_SendForApproval();
			tenderApproval1_Approval1PageValidation();
			tenderApproval1_CommentSectionApproval();
			tenderApproval1_ProvidingComment();
			click(tendercreationlocators.approverComment, "approverComment");
			set(tendercreationlocators.comment, pdfResultReport.testData.get("TenderApproval-DateScheduleCommentTab"),
					"comment");
			click(tendercreationlocators.approveCommentReview, "approveCommentReview");
			waitForObj(5000);
			tenderLogout();
			tendercreatorLogin();
			navigateToTenderListing();
			enterTenderIdInSearch();
			IsElementPresent(tendercreationlocators.tenderCreatedStatus);
			tenderLogout();
			pdfResultReport.addStepDetails("Tender creator logout", "Tender creator must be sucessfully logout",
					"Successfully logout" + " ", "Pass", "Y");
			log.info("completed executing the method:: tendercreatorLogout");

		} catch (Exception e) {
			log.fatal("Unable to open the URL" + e.getMessage());
			pdfResultReport.addStepDetails("Tender creator login", "Tender creator is not logged in",
					"Unable to login as tender creator" + e.getMessage(), "Fail", "N");
		}
	}

	public void tenderApproveApproval1ReviewApproval2() throws Throwable {
		try {
			log.info("started executing the method:: tenderApproveApproval1ReviewApproval2");
			openURL();
			tendercreatorLogin();
			navigateToTenderList();
			createTender_TenderTwoCreationByDutta_GeneralInformation();
			createTender_TenderTwoCreationByDutta_DateSchedule();
			createTender_TenderTwoCreationByDutta_ProjectDetails();
			createTender_TenderTwoCreationByDutta_TermsAndConditions();
			createTender_TenderTwoCreationByDutta_Technical();
			createTender_TenderTwoCreationByDutta_BOQMandatory();
			tenderIdSave();
			// createTender_TenderTwoCreationByDutta_SendForApproval();
			createTender_TenderTwoCreationByDutta_SendForApprovalSequential2Approvers();
			tenderApproval1_Approval1PageValidation();
			tenderApproval1_CommentSectionApproval();
			tenderApproval1_ProvidingComment();
			tenderApproval1_ProvidingOverallApproverComment();
			waitForObj(8000);
			tenderApproval2_ApprovalPageValidation();
			tenderApproval2_CommentSectionApproval();
			tenderApproval2_ProvidingComment();
			click(tendercreationlocators.approverComment, "approverComment");
			set(tendercreationlocators.comment, pdfResultReport.testData.get("TenderApproval-DateScheduleCommentTab"),
					"comment");
			click(tendercreationlocators.approveCommentReview, "approveCommentReview");
			click(tendercreationlocators.reverseBackToApprover, "reverseBackToApprover");
			waitForObj(5000);
			tenderApproval1_Approval1PageValidation();

			pdfResultReport.addStepDetails("Tender creator logout", "Tender creator must be sucessfully logout",
					"Successfully logout" + " ", "Pass", "Y");
			log.info("completed executing the method:: tendercreatorLogout");

		} catch (Exception e) {
			log.fatal("Unable to open the URL" + e.getMessage());
			pdfResultReport.addStepDetails("Tender creator login", "Tender creator is not logged in",
					"Unable to login as tender creator" + e.getMessage(), "Fail", "N");
		}
	}

	/**
	 * create Tender With NewRfq
	 * 
	 * @author E005672
	 * @throws Throwable
	 */
	public void createTenderWithNewRfq() throws Throwable {
		try {
			log.info("started executing the method:: createTenderWithNewRfq");
			// mouseOver(tendercreationlocators.tendersIcon);
			click(tendercreationlocators.tendersIcon, "tendersIcon");
			click(tendercreationlocators.tenderCreation, "tenderCreation");
			click(tendercreationlocators.createNewRfq, "createNewRfq");

			pdfResultReport.addStepDetails("Navigate to tender List", "Tender list must be navigated successfully",
					"Successfully navigated to tender list" + " ", "Pass", "Y");
			log.info("completed executing the method:: createTenderWithNewRfq");

		} catch (Exception e) {
			log.fatal("Unable to navigate to the tender list" + e.getMessage());
			pdfResultReport.addStepDetails("Navigate to tender List", "Not able to navigate to the tender list",
					"Unable to navigate to the tender list" + e.getMessage(), "Fail", "N");
		}
	}

	/**
	 * @Re usable method Check is the elememt is checkBox or not
	 * @author E005672=venkatesh jujjuru
	 * @param ele
	 * @return
	 */
	public static boolean isCheckBox(WebElement ele) {
		if (ele.getAttribute("type").equals("text")) {
			return false;

		} else {
			return true;
		}

	}

	/**
	 * @Re usable method
	 * 
	 *     Select the checkBox Item and parallely enter the Item Quantity no
	 * 
	 * @author E005672=venkatesh Jujjuru
	 * 
	 * @param qtyNo
	 * @param j
	 * @throws Throwable
	 */
	public void seclectSorItemsCheckBoxAndAddQuantity(String qtyNo, int j) throws Throwable {

		try {
			log.info("started executing the method:: seclectSorItemsCheckBoxAndAddQuantity");
			ThreadLocalWebdriver.getDriver().findElements(tendercreationlocators.newBy).stream().limit(j)
					.collect(Collectors.toList()).forEach((ele) -> {
						if (isCheckBox(ele)) {
							ele.click();
						} else {
							ele.sendKeys(qtyNo);
						}

					});

			pdfResultReport.addStepDetails("seclectSorItemsCheckBoxAndAddQuantity",
					"Items shoud be slected and qty should be entered ", "Sucessly added the Item Qty" + " ", "Pass",
					"Y");
			log.info("completed executing the method:: seclectSorItemsCheckBoxAndAddQuantity");

		} catch (Exception e) {

			log.fatal("Unable to " + e.getMessage());
			pdfResultReport.addStepDetails("seclectSorItemsCheckBoxAndAddQuantity",
					"Not able to slected items and not able to item qty",
					"Unable to slected items and not able to item qty" + e.getMessage(), "Fail", "N");
		}
	}

	/**
	 * Choose Option Items and select as NO in Dropdown
	 * 
	 * @author E005672=venkatesh jujjuru
	 * @param j
	 * @throws Throwable
	 */
	public void chooseItemsForOptional(int j) throws Throwable {
		try {
			log.info("started executing the method:: chooseItemsForOptional");

			List<WebElement> ListItems = ThreadLocalWebdriver.getDriver()
					.findElements(tendercreationlocators.mandotatoryItemSelectBoxBy);
			int i = 0;
			for (WebElement Items : ListItems) {
				i++;
				Items.click();
				Items.sendKeys("NO");
				Items.sendKeys(Keys.ENTER);
				waitForObj(2000);
				click(tendercreationlocators.confirmationOkBy, "confirm Ok for Optional item");
				waitForObj(4000);
				if (i == j)
					break;
			}
			pdfResultReport.addStepDetails("chooseItemsForOptional", "Item need to selected for optional",
					"Sucessfully selected items for Optional " + " ", "Pass", "Y");
			log.info("completed executing the method:: chooseItemsForOptional");

		} catch (Exception e) {

			log.fatal("Unable to " + e.getMessage());
			pdfResultReport.addStepDetails("chooseItemsForOptional", "Not able to select itemsfor optional ",
					"Unable to select itemsfor optional " + e.getMessage(), "Fail", "N");
		}
	}

	/**
	 *
	 * createTender for template UVoltasDEMO by addding SorItems
	 * 
	 * @author E005672=venkatesh jujjuru
	 * @throws Throwable
	 */
	public void createTender_UVoltasDEMO_boqSummaryWithSorItems() throws Throwable {
		try {
			log.info("started executing the method:: createTender_UVoltasDEMO_boqSummaryWithSorItems");
			click(tendercreationlocators.boqSummary, "BoqSummary");
			waitForSpinnerToDisappear();
			click(tendercreationlocators.addSorItemBy, "Click Add SorItems");
			waitForObj(3000);
			select(tendercreationlocators.selectSorItemMaterialBy, pdfResultReport.testData.get("SORItemMaterial"));
			waitForObj(2000);
			select(tendercreationlocators.selectSorItemLocationBy, pdfResultReport.testData.get("SORItemLocation"));
			waitForObj(2000);
			select(tendercreationlocators.selectSorItemMaterialWeightBy,
					pdfResultReport.testData.get("ItemMaterialWeight"));

			click(tendercreationlocators.sorItemSearchBy, "Click Sor Items search Button");
			waitForObj(3000);
			seclectSorItemsCheckBoxAndAddQuantity(pdfResultReport.testData.get("SORItemQuantity"), 8);
			click(tendercreationlocators.sorItemSaveBy, "Save the Item List Selected");
			waitForObj(3000);
			String noOfRecordsText = text(tendercreationlocators.noOfRecordsBy);
			verifyNoOfRecordsAdded(noOfRecordsText);
			chooseItemsForOptional(2);
			JSClick(tendercreationlocators.savebutton, "savebutton");
			waitForSpinnerToDisappear();
			pdfResultReport.addStepDetails("createTender_UVoltasDEMO_boqSummaryWithSorItems",
					"Should select SOR items ", "Sucessfully slected SOR Items" + " ", "Pass", "Y");
			log.info("completed executing the method::createTender_UVoltasDEMO_boqSummaryWithSorItems");

		} catch (Exception e) {

			log.fatal("Unable to " + e.getMessage());
			pdfResultReport.addStepDetails("createTender_UVoltasDEMO_boqSummaryWithSorItems",
					"Not able to select SOR items", "Unable to select SOR items" + e.getMessage(), "Fail", "N");
		}
	}

	/**
	 * verify the no of items records are added
	 */
	String ActualText = "Showing 4 of 4 Records";

	public boolean verifyNoOfRecordsAdded(String ExpectedText) {
		if (ExpectedText.equals(ActualText))
			return true;
		else
			return false;

	}

	/**
	 * Submit the Tender without taking Approval
	 * 
	 * @author E005672=venkatesh jujjuru
	 * @throws Throwable
	 */
	public void submitTheTenderWithNoApprovalRequired() throws Throwable {
		try {
			log.info("started executing the method:: submitTheTenderWithNoApprovalRequired");

			click(tendercreationlocators.submitbutton, "submitbutton");
			waitForObj(3000);
			click(tendercreationlocators.sendForApprovalNotRequired, "sendForApprovalNotRequired");
			tenderIdSave();
			click(tendercreationlocators.sendForApprovalSubmit, "sendForApprovalSubmit");
			WebDriverWait wait = new WebDriverWait(ThreadLocalWebdriver.getDriver(), 100);
			wait.until(ExpectedConditions.visibilityOfElementLocated(tendercreationlocators.defaultCatBy));
			IsElementPresent(tendercreationlocators.tenderList);
			pdfResultReport.addStepDetails("submitTheTenderWithNoApprovalRequired",
					"Should Submit with  Approval not required",
					"Sucessfully submitted with Approval not required" + " ", "Pass", "Y");
			log.info("completed executing the method:: submitTheTenderWithNoApprovalRequired");

		} catch (Exception e) {

			log.fatal("Unable to " + e.getMessage());
			pdfResultReport.addStepDetails("submitTheTenderWithNoApprovalRequired",
					"Not able to Submit with  Approval not required ",
					"Unable to Submit with  Approval not required " + e.getMessage(), "Fail", "N");
		}

	}

	/**
	 * Select Non sor items and enter the all mandatory fields and save
	 * 
	 * @author E005672-venkatesh jujjuru
	 * 
	 * @param j
	 */
	public void selectNonSorItems(int j) {
		List<WebElement> itemCode = ThreadLocalWebdriver.getDriver()
				.findElements(tendercreationlocators.boqSummaryItemCode);

		List<WebElement> itemDescription = ThreadLocalWebdriver.getDriver()
				.findElements(tendercreationlocators.boqSummaryItemDescription);

		List<WebElement> quantity = ThreadLocalWebdriver.getDriver()
				.findElements(tendercreationlocators.boqSummaryQuantity);

		List<WebElement> uom = ThreadLocalWebdriver.getDriver().findElements(tendercreationlocators.boqSummaryUom);

		List<WebElement> scheduleRate = ThreadLocalWebdriver.getDriver()
				.findElements(tendercreationlocators.boqSummarySorRate);

		List<WebElement> mandatoryItem = ThreadLocalWebdriver.getDriver()
				.findElements(tendercreationlocators.boqSummaryMandatoryItem);

		Random random = new Random();

		for (int i = 0; i < j; i++) {

			itemCode.get(i).sendKeys(String.valueOf(getrandomInterger(1000, 10000)));
			waitForObj(2000);

			itemDescription.get(i).sendKeys(pdfResultReport.testData.get("ItemDescription"));
			waitForObj(2000);
			quantity.get(i).sendKeys(pdfResultReport.testData.get("Quantity"));
			waitForObj(2000);
			Select select = new Select(uom.get(i));
			select.selectByVisibleText(pdfResultReport.testData.get("UOM"));
			waitForObj(2000);
			scheduleRate.get(i).sendKeys(pdfResultReport.testData.get("SORRate"));
			waitForObj(2000);

		}

	}

	/**
	 * createTender UVoltasDEMO boqSummary WithNonSorItems
	 * 
	 * @author E005672-venkatesh jujjuru
	 * @throws Throwable
	 */
	public void createTender_UVoltasDEMO_boqSummaryWithNonSorItems() throws Throwable {
		try {
			log.info("started executing the method:: createTender_UVoltasDEMO_boqSummaryWithNonSorItems");
			click(tendercreationlocators.boqSummary, "boqSummary");

			waitForSpinnerToDisappear();

			for (int i = 0; i < 4; i++) {
				click(tendercreationlocators.addNonSORItem, "addNonSORItem");
				waitForObj(2000);
			}

			waitForObj(3000);

			selectNonSorItems(4);

			JSClick(tendercreationlocators.savebutton, "savebutton");

			waitForObj(4000);

			chooseItemsForOptional(2);

			JSClick(tendercreationlocators.savebutton, "savebutton");

			waitForSpinnerToDisappear();

			pdfResultReport.addStepDetails("createTender_UVoltasDEMO_boqSummaryWithNonSorItems",
					"Tender must be created successfully WithNonSorItems",
					"Tender is created successfully with adding NonSoritems" + " ", "Pass", "Y");
			log.info("completed executing the method:: createTender_UVoltasDEMO_boqSummaryWithNonSorItems");
		} catch (Exception e) {
			log.fatal("Not able to create tender" + e.getMessage());
			pdfResultReport.addStepDetails("createTender_UVoltasDEMO_boqSummaryWithNonSorItems",
					"Not able to create tender using WithNonSorItems",
					"Unable to create tender using WithNonSorItems" + e.getMessage(), "Fail", "N");
		}
	}

	public void tenderApproveApprovalTypeParallel() throws Throwable {
		try {
			log.info("started executing the method:: tenderApproveApproval1ReviewApproval2");
			openURL();
			tendercreatorLogin();
			navigateToTenderList();
			createTender_TenderTwoCreationByDutta_GeneralInformation();
			createTender_TenderTwoCreationByDutta_DateSchedule();
			createTender_TenderTwoCreationByDutta_ProjectDetails();
			createTender_TenderTwoCreationByDutta_TermsAndConditions();
			createTender_TenderTwoCreationByDutta_Technical();
			createTender_TenderTwoCreationByDutta_BOQMandatory();
			tenderIdSave();
			createTender_TenderTwoCreationByDutta_SendForApprovalParallel2Approvers();
			set(tendercreationlocators.search, tenderReferenceNoLocatorText, "search");
			IsElementPresent(tendercreationlocators.tenderListStatus);
			tenderApproval2_ApprovalPageValidation();
			tenderApproval2_CommentSectionApproval();
			tenderApproval2_ProvidingComment();
			tenderApproval2_ProvidingOverall2ApproverComment();
			tenderApproval1_Approval1PageValidation();
			tenderApproval1_CommentSectionApproval();
			tenderApproval1_ProvidingComment();
			tenderApproval1_ProvidingOverallApproverComment();
			tenderLogout();
			tendercreatorLogin();
			navigateToTenderListing();
			set(tendercreationlocators.tenderListSearch, tenderReferenceNoLocatorText, "tenderListSearch");
			IsElementPresent(tendercreationlocators.tenderListStatus);
			// tenderApproval1_Approval1PageValidation();
			pdfResultReport.addStepDetails("Tender creator logout", "Tender creator must be sucessfully logout",
					"Successfully logout" + " ", "Pass", "Y");
			log.info("completed executing the method:: tendercreatorLogout");

		} catch (Exception e) {
			log.fatal("Unable to open the URL" + e.getMessage());
			pdfResultReport.addStepDetails("Tender creator login", "Tender creator is not logged in",
					"Unable to login as tender creator" + e.getMessage(), "Fail", "N");
		}
	}

	// Vamshi
	public void createTender_TenderTwoCreationByDutta_SendForApprovalParallel2Approvers() throws Throwable {
		try {
			log.info(
					"started executing the method:: createTender_TenderTwoCreationByDutta_SendForApprovalParallel2Approvers");
			click(tendercreationlocators.Userdefined, "Userdefined");
			click(tendercreationlocators.sectionWiseComments, "sectionWiseComments");
			waitForObj(2000);
			click(tendercreationlocators.cancelUser1, "cancelUser1");
			waitForObj(2000);
			click(tendercreationlocators.cancelUser1, "cancelUser1");
			waitForObj(2000);
			click(tendercreationlocators.userAdd, "userAdd");
			waitForObj(2000);
			set(tendercreationlocators.user1, pdfResultReport.testData.get("UserTenderApprover1"), "user1");
			select(tendercreationlocators.approverType1_p, pdfResultReport.testData.get("ApprovalType1"));
			waitForObj(2000);
			click(tendercreationlocators.userAdd, "userAdd");
			waitForObj(2000);
			set(tendercreationlocators.user2, pdfResultReport.testData.get("UserTenderApprover2"), "user2");
			select(tendercreationlocators.approverType2_p, pdfResultReport.testData.get("ApprovalType1"));

			set(tendercreationlocators.minApprover1, pdfResultReport.testData.get("MinApprover"), "minApprover1");
			click(tendercreationlocators.coordinatorFlag, "coordinatorFlag");
			waitForObj(2000);
			// set(tendercreationlocators.minApprover2,pdfResultReport.testData.get("MinApprover"),
			// "minApprover2");
			set(tendercreationlocators.comments, pdfResultReport.testData.get("UserDefinedApprover-Comments"),
					"comments");
			click(tendercreationlocators.sendForApproval, "sendForApproval");
			pdfResultReport.addStepDetails("Successfully Created Tender",
					"Tender must be created successfully using createTender_TenderTwoCreationByDutta_SendForApproval as template group ",
					"Tender is created successfully using createTender_TenderTwoCreationByDutta_SendForApproval as template group"
							+ " ",
					"Pass", "Y");
			// waitForObj(10000);

			WebDriverWait wait = new WebDriverWait(ThreadLocalWebdriver.getDriver(), 60);

			wait.until(ExpectedConditions.visibilityOfElementLocated(tendercreationlocators.defaultCatBy));

			pdfResultReport.addStepDetails("Successfully Created Tender",
					"Tender must be created successfully using createTender_TenderTwoCreationByDutta_SendForApproval as template group ",
					"Tender is created successfully using createTender_TenderTwoCreationByDutta_SendForApproval as template group"
							+ " ",
					"Pass", "Y");
		} catch (Exception e) {
			log.fatal("Not able to create tender" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to create tender",
					"Not able to create tender using createTender_TenderTwoCreationByDutta_SendForApprovalSequential2Approvers as template group",
					"Unable to create tender using createTender_TenderTwoCreationByDutta_SendForApprovalSequential2Approvers as template group"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void createTender_TenderTwoCreationByDutta_SendForApprovalSequential2Approvers() throws Throwable {
		try {
			log.info(
					"started executing the method:: createTender_TenderTwoCreationByDutta_SendForApprovalSequential2Approvers");
			click(tendercreationlocators.Userdefined, "Userdefined");
			click(tendercreationlocators.sectionWiseComments, "sectionWiseComments");
			click(tendercreationlocators.cancelUser1, "cancelUser1");
			click(tendercreationlocators.cancelUser1, "cancelUser1");
			click(tendercreationlocators.userAdd, "userAdd");
			set(tendercreationlocators.user1, pdfResultReport.testData.get("UserTenderApprover1"), "user1");
			waitForObj(2000);
			select(tendercreationlocators.approverType1_p, pdfResultReport.testData.get("ApprovalType2"));
			click(tendercreationlocators.userAdd, "userAdd");

			set(tendercreationlocators.user2, pdfResultReport.testData.get("UserTenderApprover2"), "user2");
			waitForObj(2000);
			select(tendercreationlocators.approverType2_p, pdfResultReport.testData.get("ApprovalType2"));
			waitForObj(2000);
			set(tendercreationlocators.comments, pdfResultReport.testData.get("UserDefinedApprover-Comments"),
					"comments");
			waitForObj(2000);

			click(tendercreationlocators.sendForApproval, "sendForApproval");
			pdfResultReport.addStepDetails("Successfully Created Tender",
					"Tender must be created successfully using createTender_TenderTwoCreationByDutta_SendForApprovalSequential2Approvers as template group ",
					"Tender is created successfully using createTender_TenderTwoCreationByDutta_SendForApprovalSequential2Approvers as template group"
							+ " ",
					"Pass", "Y");
			WebDriverWait wait = new WebDriverWait(ThreadLocalWebdriver.getDriver(), 100);

			wait.until(ExpectedConditions.visibilityOfElementLocated(tendercreationlocators.defaultCatBy));

			pdfResultReport.addStepDetails("Successfully Created Tender",
					"Tender must be created successfully using createTender_TenderTwoCreationByDutta_SendForApprovalSequential2Approvers as template group ",
					"Tender is created successfully using createTender_TenderTwoCreationByDutta_SendForApprovalSequential2Approvers as template group"
							+ " ",
					"Pass", "Y");
			log.info(
					"completed executing the method:: createTender_TenderTwoCreationByDutta_SendForApprovalSequential2Approvers");
		} catch (Exception e) {
			log.fatal("Not able to create tender" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to create tender",
					"Not able to create tender using createTender_TenderTwoCreationByDutta_SendForApprovalSequential2Approvers as template group",
					"Unable to create tender using createTender_TenderTwoCreationByDutta_SendForApprovalSequential2Approvers as template group"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void createTender_UVoltasDEMO_boqSummarySorItemTC_13() throws Throwable {
		try {
			log.info("started executing the method:: createTender_UVoltasDEMO_boqSummarySorItemTC_13");
			click(tendercreationlocators.boqSummary, "boqSummary");
			click(tendercreationlocators.addSORItem, "addSORItem");

			select(tendercreationlocators.selectSORItemMaterialBy, pdfResultReport.testData.get("SORItemMaterial"));
			select(tendercreationlocators.selectSORItemLocationBy, pdfResultReport.testData.get("SORItemLocation"));
			select(tendercreationlocators.selectSORItemMaterialWeightBy,
					pdfResultReport.testData.get("ItemMaterialWeight"));
			click(tendercreationlocators.addSORItemSearch, "addSORItemSearch");
			waitForObj(3000);
			click(tendercreationlocators.addSORItemCodeCheckBoxBy, "addSORItemCodeCheckBoxBy");
			set(tendercreationlocators.addSORItemQuantity, pdfResultReport.testData.get("SORItemQuantity"),
					"addSORItemQuantity");
			click(tendercreationlocators.addSORItemSave, "addSORItemSave");
			click(tendercreationlocators.savebutton, "savebutton");
			pdfResultReport.addStepDetails("Successfully Created Tender",
					"Tender must be created successfully using createTender_UVoltasDEMO_boqSummarySorItemTC_13 as template group ",
					"Tender is created successfully using createTender_UVoltasDEMO_boqSummarySorItemTC_13 as template group"
							+ " ",
					"Pass", "Y");
			log.info("completed executing the method:: createTender_UVoltasDEMO_boqSummarySorItemTC_13");
		} catch (Exception e) {
			log.fatal("Not able to create tender" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to create tender",
					"Not able to create tender using createTender_UVoltasDEMO_boqSummarySorItemTC_13 as template group",
					"Unable to create tender using createTender_UVoltasDEMO_boqSummarySorItemTC_13 as template group"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void datecorrigendum() throws Throwable {
		try {
			log.info("started executing the method:: datecorrigendum");
			click(tendercreationlocators.action, "action");
			click(tendercreationlocators.corrigendumLink, "corrigendumLink");
			waitForObj(3000);
			set(tendercreationlocators.corrigendumReferenceNumber,
					pdfResultReport.testData.get("CorrigendumReferenceNumber"), "corrigendumReferenceNumber");
			set(tendercreationlocators.corrigendumDescription, pdfResultReport.testData.get("CorrigendumDescription"),
					"corrigendumDescription");
			click(tendercreationlocators.corrigendumSelect, "corrigendumSelect");
			click(tendercreationlocators.corrigendumSelect_DateSchedule, "corrigendumSelect_DateSchedule");
			pdfResultReport.addStepDetails("Successfully Create datecorrigendum",
					"datecorrigendum must be created successfully", "Successfully Created datecorrigendum" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: datecorrigendum");
		} catch (Exception e) {
			log.fatal("Not able to create datecorrigendum" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to create datecorrigendum", "Not able to create datecorrigendum",
					"Unable to create  datecorrigendum " + e.getMessage(), "Fail", "N");
		}
	}

	public void corrigendumXButton() throws Throwable {
		try {
			log.info("started executing the method:: corrigendumXButton");
			click(tendercreationlocators.corrigendumXButton, "corrigendumXButton");

			WebDriverWait wait = new WebDriverWait(ThreadLocalWebdriver.getDriver(), 60);

			wait.until(ExpectedConditions.visibilityOfElementLocated(tendercreationlocators.defaultCatBy));

			pdfResultReport.addStepDetails("Successfully click on corrigendumXButton",
					"Able to click on corrigendumXButton", "Successfully clicked on corrigendumXButton" + " ", "Pass",
					"Y");
			log.info("completed executing the method:: corrigendumXButton");
		} catch (Exception e) {
			log.fatal("Not able to click on corrigendumXButton" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to click on corrigendumXButton",
					"Not able to click on corrigendumXButton",
					"Unable to click on datecorrigendumXButton " + e.getMessage(), "Fail", "N");
		}
	}

	public void corrigendumDiscardButton() throws Throwable {
		try {
			log.info("started executing the method:: corrigendumDiscardButton");
			click(tendercreationlocators.corrigendumDiscardButton, "corrigendumDiscardButton");
			click(tendercreationlocators.corrigendumAlertTabOk, "corrigendumAlertTabOk ");

			WebDriverWait wait = new WebDriverWait(ThreadLocalWebdriver.getDriver(), 60);

			wait.until(ExpectedConditions.visibilityOfElementLocated(tendercreationlocators.defaultCatBy));
			pdfResultReport.addStepDetails("Successfully click on corrigendumDiscardButton",
					"Able to click on corrigendumDiscardButton",
					"Successfully clicked on corrigendumDiscardButton" + " ", "Pass", "Y");
			log.info("completed executing the method:: corrigendumDiscardButton");
		} catch (Exception e) {
			log.fatal("Not able to click on corrigendumDiscardButton" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to click on corrigendumDiscardButton",
					"Not able to click on corrigendumDiscardButton",
					"Unable to click on corrigendumDiscardButton " + e.getMessage(), "Fail", "N");
		}
	}

	public void corrigendumSaveButton() throws Throwable {
		try {
			log.info("started executing the method:: corrigendumSaveButton");
			click(tendercreationlocators.savebutton, "savebutton");

			waitForSpinnerToDisappear();
			waitForObj(4000);
			pdfResultReport.addStepDetails("Successfully click on corrigendumSaveButton",
					"Able to click on corrigendumSaveButton", "Successfully clicked on corrigendumSaveButton" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: corrigendumSaveButton");
		} catch (Exception e) {
			log.fatal("Not able to click on corrigendumSaveButton" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to click on corrigendumSaveButton",
					"Not able to click on corrigendumSaveButton",
					"Unable to click on corrigendumSaveButton " + e.getMessage(), "Fail", "N");
		}
	}

	public void modifyDateScheduleTemplate() throws Throwable {
		try {
			log.info("started executing the method:: modifyDateScheduleTemplate");
			clear(tendercreationlocators.bidsubmissionStartDate, "bidsubmissionStartDate");
			set(tendercreationlocators.bidsubmissionStartDate,
					pdfResultReport.testData.get("CorrigendumBidSubmissionStartDate"),
					"CorrigendumBidSubmissionStartDate");
			clear(tendercreationlocators.bidsubmissionDueDate, "bidsubmissionDueDate");
			set(tendercreationlocators.bidsubmissionDueDate,
					pdfResultReport.testData.get("CorrigendumBidSubmissionDueDate"), "CorrigendumBidSubmissionDueDate");
			clear(tendercreationlocators.bidsubmissionOpenDate, "bidsubmissionOpenDate");
			set(tendercreationlocators.bidsubmissionOpenDate, pdfResultReport.testData.get("CorrigendumBidOpenDate"),
					"CorrigendumBidOpenDate");
			click(tendercreationlocators.savebutton, "savebutton");
			click(tendercreationlocators.submitbutton, "submitbutton");
			pdfResultReport.addStepDetails("Successfully Modify Date Schedule Template",
					"Date Schedule Template must be modify successfully",
					"Successfully Modified Date Schedule Template" + " ", "Pass", "Y");
			log.info("completed executing the method:: modifyDateScheduleTemplate");
		} catch (Exception e) {
			log.fatal("Not able to Modify DateSchedule Template" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to modifyDateScheduleTemplate",
					"Not able to modifyDateScheduleTemplate",
					"Unable to Modify Date Schedule Template " + e.getMessage(), "Fail", "N");
		}
	}

	public void tenderApproval2_ApprovalPageValidation_CorrigendumTab() throws Throwable {
		try {
			log.info("started executing the method:: tenderApproval2_ApprovalPageValidation_CorrigendumTab");
			tenderLogout();
			tenderApprover2Login();
			JSClick(tendercreationlocators.workFlow, "workFlow");
			JSClick(tendercreationlocators.pending, "pending");

			waitForSpinnerToDisappear();
			click(tendercreationlocators.corrigendumTab, "corrigendumTab");
			waitForSpinnerToDisappear();
			enterTenderIdInSearch();
			// set(tendercreationlocators.search,tenderReferenceNoLocatorText,"search");
			pdfResultReport.addStepDetails("Successfully able to locate created tender",
					"Tender must be located successfully using tenderApproval2_ApprovalPageValidation_CorrigendumTab",
					"Tender is located successfully using using tenderApproval2_ApprovalPageValidation_CorrigendumTab",
					"Pass", "Y");
			log.info("completed executing the method:: tenderApproval2_ApprovalPageValidation_CorrigendumTab");
		} catch (Exception e) {
			log.fatal("Not able to Approve Tender" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to locate created tender",
					"Not able to locate created using tenderApproval2_ApprovalPageValidation_CorrigendumTab",
					"Unable to locate created Tender using tenderApproval2_ApprovalPageValidation_CorrigendumTab"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void tenderApproval1_Approval1PageValidation_CorrigendumTab() throws Throwable {
		try {
			log.info("started executing the method:: tenderApproval1_Approval1PageValidation_CorrigendumTab");
			tenderLogout();
			tenderApproverLogin();
			JSClick(tendercreationlocators.workFlow, "workFlow");
			JSClick(tendercreationlocators.pending, "pending");
			waitForSpinnerToDisappear();
			click(tendercreationlocators.corrigendumTab, "corrigendumTab");
			waitForSpinnerToDisappear();
			enterTenderIdInSearch();
			IsElementPresent(tendercreationlocators.workFlowStatus);
			pdfResultReport.addStepDetails("Successfully able to locate created tender ",
					"Tender must be located successfully using tenderApproval1_Approval1PageValidation_CorrigendumTab",
					"Tender is located successfully using tenderApproval1_Approval1PageValidation_CorrigendumTab" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: tenderApproval1_Approval1PageValidation_CorrigendumTab");
		} catch (Exception e) {
			log.fatal("Not able to Approve Tender" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to locate created tender",
					"Not able to locate created using tenderApproval1_Approval1PageValidation_CorrigendumTab",
					"Unable to locate created Tender using tenderApproval1_Approval1PageValidation_CorrigendumTab"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void tenderApproval1_CommentSectionApproval_CorrigendumTab() throws Throwable {
		try {
			log.info("started executing the method:: tenderApproval1_CommentSectionApproval_CorrigendumTab");
			click(tendercreationlocators.details, "details");
			waitForSpinnerToDisappear();
			IsElementPresent(tendercreationlocators.approverComment);
			set(tendercreationlocators.dateScheduleComment,
					pdfResultReport.testData.get("TenderApproval-DateScheduleCommentTab"), "dateScheduleComment");
			click(tendercreationlocators.saveTabComment_DateSchedule, "saveTabComment_DateSchedule");
			IsElementPresent(tendercreationlocators.alertTab);
			click(tendercreationlocators.alertTabOk, "alertTabOk");

			pdfResultReport.addStepDetails("Comment section is present",
					"Comment section is present is verified successfully using tenderApproval1_CommentSectionApproval_CorrigendumTab",
					"Comment section is present is verified using tenderApproval1_CommentSectionApproval_CorrigendumTab as template group"
							+ " ",
					"Pass", "Y");
			log.info("completed executing the method:: tenderApproval1_CommentSectionApproval_CorrigendumTab");
		} catch (Exception e) {
			log.fatal("Not able to create tender" + e.getMessage());
			pdfResultReport.addStepDetails("Not able locate comment",
					"Not able to locate comment using tenderApproval1_CommentSectionApproval_CorrigendumTab as template group",
					"Unable to locate comment using tenderApproval1_CommentSectionApproval_CorrigendumTab as template group"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void tenderApproval2_CommentSectionApproval_CorrigendumTab() throws Throwable {
		try {
			log.info("started executing the method:: tenderApproval2_CommentSectionApproval_CorrigendumTab");
			click(tendercreationlocators.details, "details");
			waitForSpinnerToDisappear();
			IsElementPresent(tendercreationlocators.approverComment);
			set(tendercreationlocators.dateScheduleComment,
					pdfResultReport.testData.get("TenderApproval-DateScheduleCommentTab"), "dateScheduleComment");
			click(tendercreationlocators.saveTabComment_DateSchedule, "saveTabComment_DateSchedule");
			IsElementPresent(tendercreationlocators.alertTab);
			click(tendercreationlocators.alertTabOk, "alertTabOk");

			pdfResultReport.addStepDetails("Comment section is present",
					"Comment section is present is verified successfully using tenderApproval2_CommentSectionApproval_CorrigendumTab",
					"Comment section is present is verified using tenderApproval2_CommentSectionApproval_CorrigendumTab as template group"
							+ " ",
					"Pass", "Y");
			log.info("completed executing the method:: tenderApproval2_CommentSectionApproval_CorrigendumTab");
		} catch (Exception e) {
			log.fatal("Not able to locate comment section" + e.getMessage());
			pdfResultReport.addStepDetails("Not able locate comment",
					"Not able to locate comment using tenderApproval2_CommentSectionApproval_CorrigendumTab as template group",
					"Unable to locate comment using tenderApproval2_CommentSectionApproval_CorrigendumTab as template group"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void corrigendumStatus() throws Throwable {
		try {
			log.info("started executing the method:: corrigendumStatus");
			IsElementPresent(tendercreationlocators.corrigendumStatus);
			pdfResultReport.addStepDetails("Successfully shown corrigendumStatus as Yes",
					"Corrigendum status must be shown as Yes using corrigendumStatus",
					"Corrigendum Status must be shown as Yes using corrigendumStatus" + " ", "Pass", "Y");
			log.info("completed executing the method:: corrigendumStatus");
		} catch (Exception e) {
			log.fatal("Unable to show the status" + e.getMessage());
			pdfResultReport.addStepDetails("Unable to show the status",
					"Not able to show the corrigendumStatus using corrigendumStatus",
					"Unable to show the status using corrigendumStatus" + e.getMessage(), "Fail", "N");
		}
	}
	public void Verifying_corrigendumStatus_No() throws Throwable {
		try {
			log.info("started executing the method:: Verifying_corrigendumStatus_No");
			IsElementPresent(tendercreationlocators.corrigendumStatusNo);
			pdfResultReport.addStepDetails("Successfully shown corrigendumStatus as No",
					"Corrigendum status must be shown as No using corrigendumStatus",
					"Corrigendum Status must be shown as No using corrigendumStatus" + " ", "Pass", "Y");
			log.info("completed executing the method:: Verifying_corrigendumStatus_No");
		} catch (Exception e) {
			log.fatal("Unable to show the status" + e.getMessage());
			pdfResultReport.addStepDetails("Unable to show the status",
					"Not able to show the corrigendumStatus using Verifying_corrigendumStatus_No",
					"Unable to show the status using Verifying_corrigendumStatus_No" + e.getMessage(), "Fail", "N");
		}
	}

	public void corrigendumStatus_Yes_Hyperlink_Validation() throws Throwable {
		try {
			log.info("started executing the method:: corrigendumStatus_Yes_Hyperlink_Validation");
			click(tendercreationlocators.corrigendumStatus, "corrigendumStatus");
			waitForObj(10000);
			IsElementPresent(tendercreationlocators.corrigendumStatusViewAll);
			IsElementPresent(tendercreationlocators.corrigendumNumber);
			IsElementPresent(tendercreationlocators.corrigendumHistory);
			pdfResultReport.addStepDetails("ViewAll is present",
					"ViewAll is present is verified successfully using corrigendumStatus_Yes_Hyperlink_Validation",
					"ViewAll is present is verified using corrigendumStatus_Yes_Hyperlink_Validation" + " ", "Pass",
					"Y");
			log.info("completed executing the method:: corrigendumStatus_Yes_Hyperlink_Validation");
		} catch (Exception e) {
			log.fatal("Not able to see ViewAll" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to see ViewAll",
					"Not able to see ViewAll using corrigendumStatus_Yes_Hyperlink_Validation",
					"Unable to see ViewAll using corrigendumStatus_Yes_Hyperlink_Validation" + e.getMessage(), "Fail",
					"N");
		}
	}

	public void corrigendumNumber_Hyperlink_Validation() throws Throwable {
		try {
			log.info("started executing the method:: corrigendumStatus_Yes_Hyperlink_Validation");
			click(tendercreationlocators.corrigendumNumber, "corrigendumNumber");
			waitForObj(10000);

			IsElementPresent(tendercreationlocators.corrigendumNewDateSchedule);
			IsElementPresent(tendercreationlocators.corrigendumOldDateSchedule);

			pdfResultReport.addStepDetails("CorrigendumNumber is present",
					"CorrigendumNumber is present is verified successfully using CorrigendumNumber_Hyperlink_Validation",
					"CorrigendumNumber is present is verified using CorrigendumNumber_Hyperlink_Validation" + " ",
					"Pass", "Y");
			click(tendercreationlocators.previewAllOkButton_p, "previewAllOkButton_p");
			waitForObj(3000);
			log.info("completed executing the method:: CorrigendumNumber_Hyperlink_Validation");
		} catch (Exception e) {
			log.fatal("Not able to see CorrigendumNumber" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to see CorrigendumNumber",
					"Not able to see CorrigendumNumber using CorrigendumNumber_Hyperlink_Validation",
					"Unable to see CorrigendumNumber using CorrigendumNumber_Hyperlink_Validation" + e.getMessage(),
					"Fail", "N");
		}
	}

	public void tenderApproveReviewTypeParallel() throws Throwable {
		try {
			log.info("started executing the method:: tenderApproveReviewTypeParallel");
			openURL();
			tendercreatorLogin();
			navigateToTenderList();
			createTender_TenderTwoCreationByDutta_GeneralInformation();
			createTender_TenderTwoCreationByDutta_DateSchedule();
			createTender_TenderTwoCreationByDutta_ProjectDetails();
			createTender_TenderTwoCreationByDutta_TermsAndConditions();
			createTender_TenderTwoCreationByDutta_Technical();
			createTender_TenderTwoCreationByDutta_BOQMandatory();
			tenderIdSave();
			createTender_TenderTwoCreationByDutta_SendForApprovalParallel2ApproversWithCoordinatorFlag2();// changed
			set(tendercreationlocators.search, tenderReferenceNoLocatorText, "search");
			IsElementPresent(tendercreationlocators.tenderListStatus);
			waitForObj(5000);
			tenderApproval2_ApprovalPageValidation();
			tenderApproval2_CommentSectionApproval();
			tenderApproval2_ProvidingComment();
			click(tendercreationlocators.approverComment, "approverComment");
			set(tendercreationlocators.comment, pdfResultReport.testData.get("TenderApproval-DateScheduleCommentTab"),
					"comment");
			click(tendercreationlocators.approveCommentReview, "approveCommentReview");
			waitForObj(5000);
			tenderLogout();
			tendercreatorLogin();
			navigateToTenderListing();
			set(tendercreationlocators.tenderListSearch, tenderReferenceNoLocatorText, "tenderListSearch");
			IsElementPresent(tendercreationlocators.tenderListStatus);
			// tenderApproval1_Approval1PageValidation();
			pdfResultReport.addStepDetails("Tender creator logout", "Tender creator must be sucessfully logout",
					"Successfully logout" + " ", "Pass", "Y");
			log.info("completed executing the method:: tendercreatorLogout");

		} catch (Exception e) {
			log.fatal("Unable to review by the approver" + e.getMessage());
			pdfResultReport.addStepDetails("Tender creator login", "Tender creator is not logged in",
					"Unable to login as tender creator" + e.getMessage(), "Fail", "N");
		}
	}

	public void createTender_TenderTwoCreationByDutta_SendForApprovalParallel2ApproversWithCoordinatorFlag2()
			throws Throwable {
		try {
			log.info("started executing the method:: createTender_TenderTwoCreationByDutta_SendForApproval");
			click(tendercreationlocators.Userdefined, "Userdefined");
			click(tendercreationlocators.sectionWiseComments, "sectionWiseComments");
			click(tendercreationlocators.cancelUser1, "cancelUser1");
			click(tendercreationlocators.cancelUser1, "cancelUser1");
			click(tendercreationlocators.userAdd, "userAdd");
			set(tendercreationlocators.user1, pdfResultReport.testData.get("UserTenderApprover1"), "user1");
			select(tendercreationlocators.approverType1_p, pdfResultReport.testData.get("ApprovalType1"));

			click(tendercreationlocators.userAdd, "userAdd");
			set(tendercreationlocators.user2, pdfResultReport.testData.get("UserTenderApprover2"), "user2");
			select(tendercreationlocators.approverType2_p, pdfResultReport.testData.get("ApprovalType1"));

			set(tendercreationlocators.minApprover1, pdfResultReport.testData.get("MinApprover"), "minApprover1");
			click(tendercreationlocators.coordinatorFlag2, "coordinatorFlag2");
			// set(tendercreationlocators.minApprover2,pdfResultReport.testData.get("MinApprover"),
			// "minApprover2");
			set(tendercreationlocators.comments, pdfResultReport.testData.get("UserDefinedApprover-Comments"),
					"comments");
			click(tendercreationlocators.sendForApproval, "sendForApproval");
			pdfResultReport.addStepDetails("Successfully Created Tender",
					"Tender must be created successfully using createTender_TenderTwoCreationByDutta_SendForApproval as template group ",
					"Tender is created successfully using createTender_TenderTwoCreationByDutta_SendForApproval as template group"
							+ " ",
					"Pass", "Y");
			WebDriverWait wait = new WebDriverWait(ThreadLocalWebdriver.getDriver(), 100);
			wait.until(ExpectedConditions.visibilityOfElementLocated(tendercreationlocators.defaultCatBy));

			log.info("completed executing the method:: createTender_TenderTwoCreationByDutta_SendForApproval");
		} catch (Exception e) {
			log.fatal("Not able to create tender" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to create tender",
					"Not able to create tender using createTender_TenderTwoCreationByDutta_SendForApproval as template group",
					"Unable to create tender using createTender_TenderTwoCreationByDutta_SendForApproval as template group"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void tenderApproval2_ProvidingOverall2ReviewComment() throws Throwable {
		try {
			log.info("started executing the method:: tenderApproval_ProvidingOverallApproverComment");
			click(tendercreationlocators.approverComment, "approverComment");
			set(tendercreationlocators.comment, pdfResultReport.testData.get("TenderApproval-DateScheduleCommentTab"),
					"comment");
			click(tendercreationlocators.approveCommentReview, "approveCommentReview");
			click(tendercreationlocators.reverseBackToApprover, "reverseBackToApprover");
			waitForObj(10000);
			pdfResultReport.addStepDetails("Successfully provided overall approval",
					"Created Tender must be provided overall approval using tenderApproval2_ProvidingOverall2ApproverComment as template group ",
					"Tender overall approval successfully using tenderApproval2_ProvidingOverall2ApproverComment as template group"
							+ " ",
					"Pass", "Y");
			log.info("completed executing the method:: tenderApproval2_ProvidingOverall2ApproverComment");
		} catch (Exception e) {
			log.fatal("Not able provided overall approval" + e.getMessage());
			pdfResultReport.addStepDetails("Not able provided overall approval",
					"Not able provided overall approval using tenderApproval2_ProvidingOverall2ApproverComment as template group",
					"Unable approve created tender using tenderApproval2_ProvidingOverall2ApproverComment as template group"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void tenderApproval1_ProvidingOverall1ReviewComment() throws Throwable {
		try {
			log.info("started executing the method:: tenderApproval1_ProvidingOverall1ReviewComment");
			click(tendercreationlocators.approverComment, "approverComment");
			set(tendercreationlocators.comment, pdfResultReport.testData.get("TenderApproval-DateScheduleCommentTab"),
					"comment");
			click(tendercreationlocators.approveCommentReview, "approveCommentReview");
			waitForObj(10000);
			pdfResultReport.addStepDetails("Successfully provided overall review",
					"Created Tender must be provided overall review using tenderApproval1_ProvidingOverall1ReviewComment",
					"Tender overall review successfully using tenderApproval1_ProvidingOverall1ReviewComment" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: tenderApproval1_ProvidingOverall1ReviewComment");
		} catch (Exception e) {
			log.fatal("Not able provided overall review" + e.getMessage());
			pdfResultReport.addStepDetails("Not able provided overall approval",
					"Not able provided overall review using tenderApproval1_ProvidingOverall1ReviewComment",
					"Unable approve created tender using tenderApproval1_ProvidingOverall1ReviewComment as template group"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void corrigendumStatus_No() throws Throwable {
		try {
			log.info("started executing the method:: corrigendumStatus_No");
			waitForObj(5000);
			tenderLogout();
			tendercreatorLogin();
			waitForElement(tendercreationlocators.dashboardIcon, 5000);
			navigateToTenderListing();
			enterTenderIdInSearch();
			IsElementPresent(tendercreationlocators.corrigendumStatus_No);
			pdfResultReport.addStepDetails("Successfully shown corrigendumStatus as No",
					"Corrigendum status must be shown as No using corrigendumStatus_No",
					"Corrigendum Status must be shown as No using corrigendumStatus_No" + " ", "Pass", "Y");
			log.info("completed executing the method:: corrigendumStatus_No");
		} catch (Exception e) {
			log.fatal("Unable to show the status" + e.getMessage());
			pdfResultReport.addStepDetails("Unable to show the status",
					"Not able to show the corrigendumStatus using corrigendumStatus_No",
					"Unable to show the status using corrigendumStatus_No" + e.getMessage(), "Fail", "N");
		}
	}

	public void modifyBOQMandatoryTemplate() throws Throwable {
		try {
			log.info("started executing the method:: modifyBOQMandatoryTemplate");
			click(tendercreationlocators.addNonSORItem, "addNonSORItem");
			set(tendercreationlocators.BOQMadatoryItemCode2_tender2creationbydutta,
					pdfResultReport.testData.get("BOQ(Mandatory)-ItemCode2"), "BOQ(Mandatory)-ItemCode2");
			set(tendercreationlocators.BOQMadatoryItemdescription2_tender2creationbydutta,
					pdfResultReport.testData.get("BOQ(Mandatory)-ItemDescription2"), "BOQ(Mandatory)-ItemDescription2");

			click(tendercreationlocators.savebutton, "savebutton");
			click(tendercreationlocators.submitbutton, "submitbutton");
			pdfResultReport.addStepDetails("Successfully Modify Date BOQMandatoryTemplate",
					"BOQMandatoryTemplate must be modify successfully",
					"Successfully Modified BOQMandatoryTemplate" + " ", "Pass", "Y");
			log.info("completed executing the method:: modifyDateScheduleTemplate");
		} catch (Exception e) {
			log.fatal("Not able to Modify BOQMandatoryTemplate" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to modifyDateScheduleTemplate",
					"Not able to modify BOQMandatoryTemplate",
					"Unable to Modify BOQMandatoryTemplate " + e.getMessage(), "Fail", "N");
		}
	}

	public void corrigendumNumberBOQMandatory_Hyperlink_Validation() throws Throwable {
		try {
			log.info("started executing the method:: corrigendumStatus_Yes_Hyperlink_Validation");
			waitForObj(10000);
			click(tendercreationlocators.corrigendumNumber, "corrigendumNumber");

			IsElementPresent(tendercreationlocators.corrigendumNewDateSchedule);
			IsElementPresent(tendercreationlocators.corrigendumOldDateSchedule);
			click(tendercreationlocators.previewAllOkButton_p, "previewAllOkButton_p");
			pdfResultReport.addStepDetails("CorrigendumNumber is present",
					"CorrigendumNumber is present is verified successfully using CorrigendumNumber_Hyperlink_Validation",
					"CorrigendumNumber is present is verified using CorrigendumNumber_Hyperlink_Validation" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: CorrigendumNumber_Hyperlink_Validation");
		} catch (Exception e) {
			log.fatal("Not able to see CorrigendumNumber" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to see ViewAll",
					"Not able to see CorrigendumNumberusing CorrigendumNumber_Hyperlink_Validation",
					"Unable to see CorrigendumNumber using CorrigendumNumber_Hyperlink_Validation" + e.getMessage(),
					"Fail", "N");
		}
	}

	public void corrigendumNumber_Hyperlink_ValidationForBOQMandatory() throws Throwable {
		try {
			log.info("started executing the method:: corrigendumNumber_Hyperlink_ValidationForBOQMandatory");
			waitForObj(10000);
			click(tendercreationlocators.corrigendumNumber, "corrigendumNumber");
			IsElementPresent(tendercreationlocators.corrigendumNewboqMandatory);
			IsElementPresent(tendercreationlocators.corrigendumOldboqMandatory);
			click(tendercreationlocators.previewAllOkButton_p, "previewAllOkButton_p");
			pdfResultReport.addStepDetails("CorrigendumNumber is present",
					"CorrigendumNumber is present is verified successfully using corrigendumNumber_Hyperlink_ValidationForBOQMandatory",
					"CorrigendumNumber is present is verified using corrigendumNumber_Hyperlink_ValidationForBOQMandatory"
							+ " ",
					"Pass", "Y");
			log.info("completed executing the method:: CorrigendumNumber_Hyperlink_Validation");
		} catch (Exception e) {
			log.fatal("Not able to see CorrigendumNumber" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to see ViewAll",
					"Not able to see CorrigendumNumberusing corrigendumNumber_Hyperlink_ValidationForBOQMandatory",
					"Unable to see CorrigendumNumber using corrigendumNumber_Hyperlink_ValidationForBOQMandatory"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void itemCorrigendum() throws Throwable {
		try {
			log.info("started executing the method:: itemCorrigendum");
			click(tendercreationlocators.action, "action");
			click(tendercreationlocators.corrigendumLink, "corrigendumLink");
			waitForObj(50000);
			set(tendercreationlocators.corrigendumReferenceNumber,
					pdfResultReport.testData.get("CorrigendumReferenceNumber"), "corrigendumReferenceNumber");
			set(tendercreationlocators.corrigendumDescription, pdfResultReport.testData.get("CorrigendumDescription"),
					"corrigendumDescription");
			click(tendercreationlocators.corrigendumSelect, "corrigendumSelect");
			click(tendercreationlocators.corrigendumSelect_NonSORItem, "corrigendumSelect_NonSORItem");
			// select(tendercreationlocators.corrigendumSelect,pdfResultReport.testData.get("CorrigendumSelect"));
			pdfResultReport.addStepDetails("Successfully Create itemCorrigendum",
					"itemCorrigendum must be created successfully", "Successfully Created itemCorrigendum" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: itemCorrigendum");
		} catch (Exception e) {
			log.fatal("Not able to create itemCorrigendum" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to create tender", "Not able to create itemCorrigendum",
					"Unable to create  itemCorrigendum " + e.getMessage(), "Fail", "N");
		}
	}

	public void attachmentsCorrigendum1() throws Throwable {
		try {
			log.info("started executing the method:: itemCorrigendum");
			click(tendercreationlocators.action, "action");
			click(tendercreationlocators.corrigendumLink, "corrigendumLink");
			waitForObj(3000);
			set(tendercreationlocators.corrigendumReferenceNumber,
					pdfResultReport.testData.get("CorrigendumReferenceNumber"), "corrigendumReferenceNumber");
			set(tendercreationlocators.corrigendumDescription, pdfResultReport.testData.get("CorrigendumDescription"),
					"corrigendumDescription");
			click(tendercreationlocators.corrigendumSelect, "corrigendumSelect");
			click(tendercreationlocators.corrigendumSelectAttachments, "corrigendumSelectAttachments");
			pdfResultReport.addStepDetails("Successfully Create itemCorrigendum",
					"itemCorrigendum must be created successfully", "Successfully Created itemCorrigendum" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: itemCorrigendum");
		} catch (Exception e) {
			log.fatal("Not able to create itemCorrigendum" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to create tender", "Not able to create itemCorrigendum",
					"Unable to create  itemCorrigendum " + e.getMessage(), "Fail", "N");
		}
	}

	public void attachmentsCorrigendum2() throws Throwable {
		try {
			log.info("started executing the method:: attachmentsCorrigendum2");
			waitForObj(3000);
			click(tendercreationlocators.openattachementsTab, "openattachementsTab");
			waitForSpinnerToDisappear();
			set(tendercreationlocators.label, pdfResultReport.testData.get("Attachments-Label1"), "label");
			set(tendercreationlocators.fileName, System.getProperty("user.dir") + "\\MediaFiles\\rfqCreation1.xlsx",
					"fileName");
			// select(tendercreationlocators.VisibleTo2,pdfResultReport.testData.get("Attachments-VisibleTo"));
			click(tendercreationlocators.attachmentOKbutton, "attachmentOKbutton");
			click(tendercreationlocators.savebutton, "savebutton");
			waitForSpinnerToDisappear();
			pdfResultReport.addStepDetails("Successfully Create itemCorrigendum",
					"attachmentsCorrigendum2 must be created successfully",
					"Successfully Created attachmentsCorrigendum2" + " ", "Pass", "Y");
			log.info("completed executing the method:: attachmentsCorrigendum2");
		} catch (Exception e) {
			log.fatal("Not able to create itemCorrigendum" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to create tender", "Not able to create attachmentsCorrigendum2",
					"Unable to create  attachmentsCorrigendum2 " + e.getMessage(), "Fail", "N");
		}
	}

	public void corrigendumNumber_Hyperlink_ValidationForAttachments() throws Throwable {
		try {
			log.info("started executing the method:: corrigendumNumber_Hyperlink_ValidationForBOQMandatory");
			waitForObj(10000);
			click(tendercreationlocators.corrigendumNumber, "corrigendumNumber");
			IsElementPresent(tendercreationlocators.corrigendumNewAttachments);
			IsElementPresent(tendercreationlocators.corrigendumOldAttachments);
			click(tendercreationlocators.previewAllOkButton_p, "previewAllOkButton_p");
			pdfResultReport.addStepDetails("CorrigendumNumber is present",
					"CorrigendumNumber is present is verified successfully using corrigendumNumber_Hyperlink_ValidationForBOQMandatory",
					"CorrigendumNumber is present is verified using corrigendumNumber_Hyperlink_ValidationForBOQMandatory"
							+ " ",
					"Pass", "Y");
			log.info("completed executing the method:: CorrigendumNumber_Hyperlink_Validation");
		} catch (Exception e) {
			log.fatal("Not able to see CorrigendumNumber" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to see ViewAll",
					"Not able to see CorrigendumNumberusing corrigendumNumber_Hyperlink_ValidationForBOQMandatory",
					"Unable to see CorrigendumNumber using corrigendumNumber_Hyperlink_ValidationForBOQMandatory"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	/**
	 * @author E005672 venkatesh
	 * @param min
	 * @param max
	 * @return
	 */
	public static int getrandomInterger(int min, int max) {

		return ((int) (Math.random() * (max - min))) + min;

	}

	/**
	 * @author E005672 venkatesh Enter the mandatory fields of
	 *         OptionalItemsAndQtyEditable template GeneralInfo Tab
	 * @throws Exception
	 */
	public void Tender_WithOptionalItemsAndQtyEditableGeneralInfo() throws Exception {
		try {
			log.info("started executing the method:: Tender_WithOptionalItemsAndQtyEditableGeneralInfo()");
			waitForElement(tendercreationlocators.templateGroupdropdown, 5000);
			select(tendercreationlocators.templateGroupdropdown, "With Optional Items & Qty Editable by Supplier_V2");
			select(tendercreationlocators.bitPartdropdown, pdfResultReport.testData.get("BidParts"));
			click(tendercreationlocators.bitPartRadio, "bitPartRadio");
			set(tendercreationlocators.tenderReferenceNumber, pdfResultReport.testData.get("TenderReferencNumber"),
					"tenderReferenceNumber");
			select(tendercreationlocators.procurementCategory, "Default_cat");
			select(tendercreationlocators.tenderCurrency, pdfResultReport.testData.get("TenderCurrency"));
			set(tendercreationlocators.probabaleAmountContract, pdfResultReport.testData.get("PAC"),
					"probabaleAmountContract");
			select(tendercreationlocators.biddingCurrency, pdfResultReport.testData.get("BiddingCurrency"));
			select(tendercreationlocators.tenderType, pdfResultReport.testData.get("TenderType"));

			click(tendercreationlocators.addBidders, "addBidders");
			click(tendercreationlocators.checkBoxTechMahindraBy, "bidderCompany_TechMahindra");

			click(tendercreationlocators.addBidderCompany, "addBidderCompany");

			set(tendercreationlocators.detailedDescription,
					pdfResultReport.testData.get("CreateTender-DetailedDescription"), "detailedDescription");
			click(tendercreationlocators.savebutton, "savebutton");

			pdfResultReport.addStepDetails("Tender_WithOptionalItemsAndQtyEditableGeneralInfo",
					"Should save generalInfo tab fields", "Sucessfully saved generalInfo tab fields " + " ", "Pass",
					"Y");
			log.info("completed executing the method:: Tender_WithOptionalItemsAndQtyEditableGeneralInfo");

		} catch (Exception e) {

			log.fatal("Unable to " + e.getMessage());
			pdfResultReport.addStepDetails("Tender_WithOptionalItemsAndQtyEditableGeneralInfo",
					"Not able to save generalInfo tab fields", "Unable to save generalInfo tab fields" + e.getMessage(),
					"Fail", "N");
		}
	}

	/**
	 * Enter the mandatory fields of OptionalItemsAndQtyEditable template
	 * OtherAttachments tab
	 * 
	 * @throws Exception
	 */

	/**
	 * @author E005672 venkatesh jujjuru Enter project tab fields for
	 *         OptionalItemsAndQtyEditable template in ProjectTab
	 * @throws Exception
	 */

	/**
	 * @author E005672 venkatesh Enter the DateSchudle Tab fields for
	 *         WithOptionalItemsAndQtyEditable template in DateSchedule Tab
	 * @throws Exception
	 */
	public void Tender_WithOptionalItemsAndQtyEditableDateSchedule() throws Exception {
		try {
			log.info("started executing the method:: Tender_WithOptionalItemsAndQtyEditableDateSchedule");
			click(tendercreationlocators.dateschedule, "dateschedule");
			waitForElement(tendercreationlocators.bidsubmissionStartDate, 5000);
			set(tendercreationlocators.bidsubmissionStartDate, pdfResultReport.testData.get("BidSubmissionStartDate"),
					"bidsubmissionStartDate");
			set(tendercreationlocators.bidsubmissionDueDate, pdfResultReport.testData.get("BidSubmissionDueDate"),
					"bidsubmissionDueDate");
			set(tendercreationlocators.bidsubmissionOpenDate, pdfResultReport.testData.get("BidOpenDate"),
					"bidsubmissionOpenDate");
			click(tendercreationlocators.savebutton, "savebutton");
			pdfResultReport.addStepDetails("Tender_WithOptionalItemsAndQtyEditableDateSchedule",
					"Should save the Date scheduled fields ", "Sucessfully saved the Date Schedule fields " + " ",
					"Pass", "Y");
			log.info("completed executing the method:: Tender_WithOptionalItemsAndQtyEditableDateSchedule");

		} catch (Exception e) {

			log.fatal("Unable to " + e.getMessage());
			pdfResultReport.addStepDetails("Tender_WithOptionalItemsAndQtyEditableDateSchedule",
					"Not able to save the Date scheduled fields",
					"Unable to save the Date Schedule fields" + e.getMessage(), "Fail", "N");
		}

	}

	/**
	 * @author E005672 venkatesh jujjuru Enter the Terms and condition Tab
	 *         fields for OptionalItemsAndQtyEditable template in Terms and
	 *         condition Tab
	 * @throws Exception
	 */
	public void Tender_WithOptionalItemsAndQtyEditableTermsAndCondition() throws Exception {
		try {
			log.info("started executing the method::Tender_WithOptionalItemsAndQtyEditableTermsAndCondition");

			click(tendercreationlocators.termsConditionsTab, "termsConditionsTab");
			click(tendercreationlocators.termsconditionsAdd, "termsconditionsAdd");
			set(tendercreationlocators.termsconditionClause, pdfResultReport.testData.get("TermsClause"),
					"termsconditionClause");
			set(tendercreationlocators.termsconditionDetails_WithOptionalItemsAndQtyEditable,
					pdfResultReport.testData.get("TermsDetails"),
					"termsconditionDetails_WithOptionalItemsAndQtyEditable");
			click(tendercreationlocators.savebutton, "savebutton");
			pdfResultReport.addStepDetails("Tender_WithOptionalItemsAndQtyEditableTermsAndCondition",
					"Should save the Terms And Conditions fields",
					"Sucessfully saved the Terms And Conditions fields" + " ", "Pass", "Y");
			log.info("completed executing the method:: Tender_WithOptionalItemsAndQtyEditableTermsAndCondition");

		} catch (Exception e) {

			log.fatal("Unable to " + e.getMessage());
			pdfResultReport.addStepDetails("Tender_WithOptionalItemsAndQtyEditableTermsAndCondition",
					"Not able to save the Terms And Conditions fields",
					"Unable to saved the Terms And Conditions fields" + e.getMessage(), "Fail", "N");
		}

	}

	/**
	 * @author E005672 venkatesh jujjuru enter the Technical fields for
	 *         OptionalItemsAndQtyEditable in Technical Tab
	 * @throws Exception
	 */
	public void Tender_WithOptionalItemsAndQtyEditableTechnical() throws Exception {
		try {
			log.info("started executing the method:: Tender_WithOptionalItemsAndQtyEditableTechnical");
			click(tendercreationlocators.technical, "technical");
			click(tendercreationlocators.technicalAdd_WithOptionalItemsAndQtyEditable,
					"technicalAdd_WithOptionalItemsAndQtyEditable");
			set(tendercreationlocators.technicalclause, pdfResultReport.testData.get("Technical-Clause"),
					"technicalclause");
			click(tendercreationlocators.savebutton, "savebutton");
			click(tendercreationlocators.next, "next");
			pdfResultReport.addStepDetails("Tender_WithOptionalItemsAndQtyEditableTechnical",
					"should save the Technical Tab fileds", "Sucessfully saved the Technical tab fields " + " ", "Pass",
					"Y");
			log.info("completed executing the method:: Tender_WithOptionalItemsAndQtyEditableTechnical");

		} catch (Exception e) {

			log.fatal("Unable to " + e.getMessage());
			pdfResultReport.addStepDetails("Tender_WithOptionalItemsAndQtyEditableTechnical",
					"Not able to save the Technical Tab fileds",
					"Unable to save the Technical tab fields" + e.getMessage(), "Fail", "N");
		}

	}

	/**
	 * @author E005672 venkatesh jujjuru BOQ Mandatory tab WithNonSoItems
	 *         ParentChildRelation
	 * @throws Throwable
	 */
	public void Tender_WithOptionalItemsAndQtyEditableBOQWithNonSoItemsParentChildRelation() throws Throwable {
		try {
			log.info(
					"started executing the method:: Tender_WithOptionalItemsAndQtyEditableBOQWithNonSoItemsParentChildRelation");

			JavascriptExecutor je = (JavascriptExecutor) ThreadLocalWebdriver.getDriver();

			click(tendercreationlocators.BOQmandatoryTab, "BOQmandatoryTab");

			waitForSpinnerToDisappear();

			click(tendercreationlocators.addnonSORBOQMandatoryTab, "addnonSORBOQMandatoryTab");

			set(tendercreationlocators.BOQMadatoryItemCode, pdfResultReport.testData.get("BOQ(Mandatory)-ItemCode"),
					"BOQMadatoryItemCode");
			set(tendercreationlocators.BOQMadatoryItemdescription,
					pdfResultReport.testData.get("BOQ(Mandatory)-ItemDescription"), "BOQMadatoryItemdescription");

			select(By.xpath("//div/table/tbody/tr[2]/td[position()=4]//following::select[@name='uom']"),
					pdfResultReport.testData.get("UOM"));

			set(tendercreationlocators.boqProjectQuantityBy, pdfResultReport.testData.get("ProjectQuantity"),
					"projectquantity");
			set(tendercreationlocators.boqpreferedMakeBy, pdfResultReport.testData.get("PreferedMaker"),
					"preferedmake");
			set(tendercreationlocators.boqBudgetrateBy, pdfResultReport.testData.get("BudgetedRate"), "budgetedrate");
			set(tendercreationlocators.boqRemarksBy, pdfResultReport.testData.get("Remarks"), "remarks");

			// enter the Three child Non -sor items from Parent

			for (int i = 0; i < 3; i++) {
				click(tendercreationlocators.boqTableActionmenuBy, "action_sec dropdownBy");
				waitForElement(tendercreationlocators.childNonSORItemfirstBy, 2000);
				click(tendercreationlocators.childNonSORItemfirstBy, "Non-sorItems");
				waitForObj(2000);
			}

			// Add Item data to Child non Sor Items for the rows 2 to 4

			selectNonSorItemsparent(2, 5);

			waitForObj(2000);

			// click add non sor tab
			click(tendercreationlocators.addnonSORBOQMandatoryTab, "addnonSORBOQMandatoryTab");

			List<WebElement> itemCode = ThreadLocalWebdriver.getDriver()
					.findElements(tendercreationlocators.boqSummaryItemCode);

			List<WebElement> itemDescription = ThreadLocalWebdriver.getDriver()
					.findElements(tendercreationlocators.boqSummaryItemDescription);

			List<WebElement> quantity = ThreadLocalWebdriver.getDriver()
					.findElements(By.xpath("//*[@name='projectquantity']"));

			List<WebElement> uom = ThreadLocalWebdriver.getDriver().findElements(tendercreationlocators.boqSummaryUom);

			List<WebElement> scheduleRate = ThreadLocalWebdriver.getDriver()
					.findElements(tendercreationlocators.boqSummarySorRate);

			List<WebElement> mandatoryItem = ThreadLocalWebdriver.getDriver()
					.findElements(tendercreationlocators.boqSummaryMandatoryItem);

			// enter the non sor items data in second parent in fifth row

			itemCode.get(5).sendKeys(String.valueOf(getrandomInterger(1000, 10000)));
			waitForObj(2000);

			itemDescription.get(5).sendKeys("Item desc");
			waitForObj(2000);
			quantity.get(5).sendKeys("100");
			waitForObj(2000);
			Select select = new Select(uom.get(5));
			select.selectByVisibleText("KG");
			waitForObj(2000);
			scheduleRate.get(5).sendKeys("1000");
			waitForObj(2000);
			mandatoryItem.get(5).click();
			mandatoryItem.get(5).sendKeys("Yes");
			mandatoryItem.get(5).sendKeys(Keys.ENTER);
			waitForObj(2000);

			// select the three child items from second parent from row 5

			for (int i = 0; i < 3; i++) {

				click(By.xpath("//div[@id='rfqItemtabl2-ri_boq']/table/tbody/tr[5]/td[13]"), "action_sec dropdownBy");

				JSClick(By.xpath("(//li/a[contains(@ng-click,'addChildNonSORItem')])[6]"), "Non-sorItems");
				waitForObj(2000);
			}

			waitForObj(2000);

			// enter the child non-sor items for row 6 t 8
			selectNonSorItemsparent(6, 9);

			// get the total estimated value for parent1
			String sparent1 = (String) je
					.executeScript("return document.getElementById('ri_boq.totalestvalue.0').value;");
			;

			int nonsorparent1 = Integer.parseInt(sparent1);

			String sparent2 = (String) je
					.executeScript("return document.getElementById('ri_boq.totalestvalue.0').value;");
			;

			int nonsorparent2 = Integer.parseInt(sparent2);

			int TotalAggergatevalue = nonsorparent1 + nonsorparent2;

			System.out.println(
					TotalAggergatevalue == 600000 ? "parent1 and parent2 sum estimatated values equal to total sum"
							: "parent1 and parent2 sum estimatated values not equal to total sum");

			Thread.sleep(5000);
			// scroll to horizontal right and scroll up
			je.executeScript("window.scrollBy(300,0)");
			je.executeScript("window.scrollBy(0,-500)");

			// select parent1 items as optional by selecting No in Dropdown
			click(tendercreationlocators.mandatoryitemBy, "mandatoryitemBy");

			select(tendercreationlocators.mandatoryitemBy, "No");

			click(tendercreationlocators.confirmationOkBy, "confirm Ok for Optional item");

			click(tendercreationlocators.savebutton, "savebutton");

			waitForSpinnerToDisappear();

			pdfResultReport.addStepDetails("Tender_WithOptionalItemsAndQtyEditableBOQWithNonSoItemsParentChildRelation",
					"Should get aggregate sum equal to two parents sum ", "Sucessfully equal to two parents sum " + " ",
					"Pass", "Y");
			log.info(
					"completed executing the method:: Tender_WithOptionalItemsAndQtyEditableBOQWithNonSoItemsParentChildRelation");

		} catch (Exception e) {

			log.fatal("Unable to " + e.getMessage());
			pdfResultReport.addStepDetails("Tender_WithOptionalItemsAndQtyEditableBOQWithNonSoItemsParentChildRelation",
					"Not able to get aggregate sum equal to two parents sum",
					"Unable to equal to two parents sum" + e.getMessage(), "Fail", "N");
		}

	}

	/**
	 * enter the child non -sor items for the respective mentioned Rows(int
	 * j,int k )
	 * 
	 * @param j
	 * @param k
	 */
	public void selectNonSorItemsparent(int j, int k) {

		List<WebElement> itemCode = ThreadLocalWebdriver.getDriver()
				.findElements(tendercreationlocators.boqSummaryItemCode);

		List<WebElement> itemDescription = ThreadLocalWebdriver.getDriver()
				.findElements(tendercreationlocators.boqSummaryItemDescription);

		List<WebElement> quantity = ThreadLocalWebdriver.getDriver()
				.findElements(tendercreationlocators.projectQtysItemsBy);

		List<WebElement> uom = ThreadLocalWebdriver.getDriver().findElements(tendercreationlocators.boqSummaryUom);

		List<WebElement> scheduleRate = ThreadLocalWebdriver.getDriver()
				.findElements(tendercreationlocators.boqSummarySorRate);

		List<WebElement> mandatoryItem = ThreadLocalWebdriver.getDriver()
				.findElements(tendercreationlocators.boqSummaryMandatoryItem);

		for (int i = j; i < k; i++) {

			itemCode.get(i).sendKeys(String.valueOf(getrandomInterger(500, 900)));
			waitForObj(2000);

			itemDescription.get(i).sendKeys("Item decs");
			waitForObj(2000);
			quantity.get(i).sendKeys("100");
			waitForObj(2000);
			Select select = new Select(uom.get(i));
			select.selectByVisibleText("KG");
			waitForObj(2000);
			scheduleRate.get(i).sendKeys("1000");
			waitForObj(2000);
			mandatoryItem.get(i).click();
			mandatoryItem.get(i).sendKeys("Yes");
			mandatoryItem.get(i).sendKeys(Keys.ENTER);
			waitForObj(2000);

		}

	}

	public void BOQParentItemMandatoryAndChildOptionForNonSoritem() throws Exception {
		try {
			log.info("started executing the method::  BOQParentItemMandatoryAndChildOptionForNonSoritem");

			click(tendercreationlocators.BOQmandatoryTab, "BOQmandatoryTab");
			click(tendercreationlocators.addnonSORBOQMandatoryTab, "addnonSORBOQMandatoryTab");

			set(tendercreationlocators.BOQMadatoryItemCode, pdfResultReport.testData.get("BOQ(Mandatory)-ItemCode"),
					"BOQMadatoryItemCode");
			set(tendercreationlocators.BOQMadatoryItemdescription,
					pdfResultReport.testData.get("BOQ(Mandatory)-ItemDescription"), "BOQMadatoryItemdescription");

			select(tendercreationlocators.boqUimBy, pdfResultReport.testData.get("UOM"));

			set(tendercreationlocators.boqProjectQuantityBy, pdfResultReport.testData.get("ProjectQuantity"),
					"projectquantity");
			set(tendercreationlocators.boqpreferedMakeBy, pdfResultReport.testData.get("PreferedMaker"),
					"preferedmake");
			set(tendercreationlocators.boqBudgetrateBy, pdfResultReport.testData.get("BudgetedRate"), "budgetedrate");
			set(tendercreationlocators.boqRemarksBy, pdfResultReport.testData.get("Remarks"), "remarks");

			// enter the child Non -sor items from Parent

			click(tendercreationlocators.boqTableActionmenuBy, "action_sec dropdownBy");
			waitForElement(tendercreationlocators.childNonSORItemfirstBy, 2000);
			click(tendercreationlocators.childNonSORItemfirstBy, "Non-sorItems");
			waitForObj(2000);

			// Add Item data to Child non Sor Items for the rows 2 to 4

			selectNonSorItemsparent(2, 3);

			click(tendercreationlocators.mandatoryitem1By, "mandatoryitemBy");

			select(tendercreationlocators.mandatoryitem1By, "No");

			waitForObj(2000);

			click(tendercreationlocators.confirmationOkBy, "confirm Ok for Optional item");

			click(tendercreationlocators.savebutton, "savebutton");

			String expected = "Parent item is mandatory, please select at least one child item mandatory in row 2";

			boolean popupdispalyed = ThreadLocalWebdriver.getDriver()
					.findElement(tendercreationlocators.confirmationOkBy).isDisplayed();

			System.out.println("Is error popup displayed " + expected + " " + popupdispalyed);

			org.testng.Assert.assertEquals(popupdispalyed, true, expected);

			pdfResultReport.addStepDetails("BOQParentItemMandatoryAndChildOptionForNonSoritem",
					"Error Pop should Display", "Succesfully displayed  --" + expected + " ", "Pass", "Y");

			click(tendercreationlocators.confirmationOkBy, "confirm Ok for Optional item");

			pdfResultReport.addStepDetails("BOQParentItemMandatoryAndChildOptionForNonSoritem",
					"Should Save BOQ Items data Sucessfully", "Succesfully Saved BOQ Items" + " ", "Pass", "Y");
			log.info("completed executing the method::  BOQParentItemMandatoryAndChildOptionForNonSoritem");

		} catch (Exception e) {

			log.fatal("Unable to " + e.getMessage());
			pdfResultReport.addStepDetails("BOQParentItemMandatoryAndChildOptionForNonSoritem",
					"Should save BOQ Items data  Sucessfully ", "Unable to save the BOQ Items data " + e.getMessage(),
					"Fail", "N");
		}
	}

	public void verifychoosenTenderTypeIsAbleToselectSingleBidderOnly(String tenderType) throws Exception {
		try {
			log.info("started executing the method:: verifychoosenTenderTypeIsAbleToselectSingleBidderOnly");

			waitForElement(tendercreationlocators.templateGroupdropdown, 5000);
			select(tendercreationlocators.templateGroupdropdown, "UVoltas DEMO");
			select(tendercreationlocators.bitPartdropdown, pdfResultReport.testData.get("BidParts"));
			click(tendercreationlocators.bitPartRadio, "bitPartRadio");
			set(tendercreationlocators.tenderReferenceNumber, pdfResultReport.testData.get("TenderReferencNumber"),
					"tenderReferenceNumber");
			select(tendercreationlocators.procurementCategory, "Default_cat");
			select(tendercreationlocators.tenderCurrency, pdfResultReport.testData.get("TenderCurrency"));
			set(tendercreationlocators.probabaleAmountContract, pdfResultReport.testData.get("PAC"),
					"probabaleAmountContract");
			select(tendercreationlocators.biddingCurrency, pdfResultReport.testData.get("BiddingCurrency"));

			select(tendercreationlocators.tenderType, tenderType);

			click(tendercreationlocators.addBidders, "addBidders");

			waitForObj(3000);

			click(tendercreationlocators.checkBoxTechMahindraBy, "click Techmehindra checkbox");

			click(tendercreationlocators.checkBoxTcsBy, "click Tcs checkbox");

			String actualErrorMsg = "You can not add more than one bidder for selected tender type";

			String expectedErrorMsg = ThreadLocalWebdriver.getDriver()
					.findElement(By.xpath("//div[@ng-hide='infoMessageDisplay']")).getText();

			if (actualErrorMsg.equalsIgnoreCase(expectedErrorMsg)) {

				click(tendercreationlocators.checkBoxTcsBy, " click Tcs checkbox");
			}

			click(tendercreationlocators.addBidderCompany, "addBidderCompany");

			set(tendercreationlocators.detailedDescription,
					pdfResultReport.testData.get("CreateTender-DetailedDescription"), "detailedDescription");
			click(tendercreationlocators.savebutton, "savebutton");

			pdfResultReport.addStepDetails("verifychoosenTenderTypeIsAbleToselectSingleBidderOnly",
					"Should select only single Bidder", "Sucessfully selecting Single Bidder " + " ", "Pass", "Y");
			log.info("completed executing the method:: verifychoosenTenderTypeIsAbleToselectSingleBidderOnly");

		} catch (Exception e) {

			log.fatal("Unable to " + e.getMessage());
			pdfResultReport.addStepDetails("verifychoosenTenderTypeIsAbleToselectSingleBidderOnly",
					"Should not selclet Multiple Bidders ", "Unable to Slect only single Bidder " + e.getMessage(),
					"Fail", "N");
		}
	}

	public void draftVerification() {
		if (text(tendercreationlocators.tenderListStatus_Draft).equals(tendercreationlocators.tenderListStatus)) {
			System.out.println("Status of Tender Creation is in Draft" + tendercreationlocators.tenderListStatus);
		} else {
			System.out.println("Status of Tender Creation is not in Draft" + tendercreationlocators.tenderListStatus);
		}
	}

	public void createTender_TenderTwoCreationByDutta_SendForApprovalParallel2ApproversCoordinatorFlag2()
			throws Throwable {
		try {
			log.info("started executing the method:: createTender_TenderTwoCreationByDutta_SendForApproval");
			click(tendercreationlocators.Userdefined, "Userdefined");
			click(tendercreationlocators.sectionWiseComments, "sectionWiseComments");
			click(tendercreationlocators.cancelUser1, "cancelUser1");
			click(tendercreationlocators.cancelUser1, "cancelUser1");
			click(tendercreationlocators.userAdd, "userAdd");
			set(tendercreationlocators.user1, pdfResultReport.testData.get("UserTenderApprover1"), "user1");
			select(tendercreationlocators.approverType1_p, pdfResultReport.testData.get("ApprovalType1"));

			click(tendercreationlocators.userAdd, "userAdd");
			set(tendercreationlocators.user2, pdfResultReport.testData.get("UserTenderApprover2"), "user2");
			select(tendercreationlocators.approverType2_p, pdfResultReport.testData.get("ApprovalType1"));

			set(tendercreationlocators.minApprover1, pdfResultReport.testData.get("MinApprover"), "minApprover1");
			click(tendercreationlocators.coordinatorFlag2, "coordinatorFlag2");
			// set(tendercreationlocators.minApprover2,pdfResultReport.testData.get("MinApprover"),
			// "minApprover2");
			set(tendercreationlocators.comments, pdfResultReport.testData.get("UserDefinedApprover-Comments"),
					"comments");
			click(tendercreationlocators.sendForApproval, "sendForApproval");
			WebDriverWait wait = new WebDriverWait(ThreadLocalWebdriver.getDriver(), 100);
			wait.until(ExpectedConditions.visibilityOfElementLocated(tendercreationlocators.defaultCatBy));
			pdfResultReport.addStepDetails("Successfully Created Tender",
					"Tender must be created successfully using createTender_TenderTwoCreationByDutta_SendForApproval as template group ",
					"Tender is created successfully using createTender_TenderTwoCreationByDutta_SendForApproval as template group"
							+ " ",
					"Pass", "Y");
			log.info("completed executing the method:: createTender_TenderTwoCreationByDutta_SendForApproval");
		} catch (Exception e) {
			log.fatal("Not able to create tender" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to create tender",
					"Not able to create tender using createTender_TenderTwoCreationByDutta_SendForApproval as template group",
					"Unable to create tender using createTender_TenderTwoCreationByDutta_SendForApproval as template group"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	// bidsubmission
	// Bidder1 login
	public void bidder_01_Login() throws Throwable {
		try {
			log.info("started executing the method:: bidder_01_Login");
			click(tendercreationlocators.login, "login");
			set(tendercreationlocators.userName, pdfResultReport.testData.get("Bidder1UserName"), "userName");
			waitForObj(5000);
			set(tendercreationlocators.password, pdfResultReport.testData.get("AppPassword"), "password");
			//Handle fixed Captcha (06/11/2020)
			set(tendercreationlocators.Captcha_Login, "1234", "Login_Captcha");
			//Wait statement (Added to handle Captcha temporarily in AWS QA (19/10/2020))
			//waitForObj(50000);
			click(tendercreationlocators.okButton, "okButton");
			waitForElement(tendercreationlocators.dashboardIcon, 5000);
			pdfResultReport.addStepDetails("bidder_01_Login", "bidder_01_Login must be sucessfully logged in",
					"Successfully logged in as bidder_01_Login" + " ", "Pass", "Y");
			log.info("completed executing the method:: bidder_01_Login");

		} catch (Exception e) {
			log.fatal("Unable to open the URL" + e.getMessage());
			pdfResultReport.addStepDetails("bidder_01_Login", "bidder_01_Login is not logged in",
					"Unable to login as bidder_01_Login" + e.getMessage(), "Fail", "N");
		}
	}

	// Bidder2 login
	public void bidder_02_Login() throws Throwable {
		try {
			log.info("started executing the method:: bidder_02_Login");
			click(tendercreationlocators.login, "login");
			set(tendercreationlocators.userName, pdfResultReport.testData.get("Bidder2UserName"), "userName");
			waitForObj(5000);
			set(tendercreationlocators.password, pdfResultReport.testData.get("AppPassword"), "password");
			//Handle fixed Captcha (06/11/2020)
			set(tendercreationlocators.Captcha_Login, "1234", "Login_Captcha");
			//Wait statement (Added to handle Captcha temporarily in AWS QA (19/10/2020))
			//waitForObj(50000);
			click(tendercreationlocators.okButton, "okButton");
			waitForElement(tendercreationlocators.dashboardIcon, 5000);
			pdfResultReport.addStepDetails("bidder_02_Login", "bidder_02_Login must be sucessfully logged in",
					"Successfully logged in as bidder_02_Login" + " ", "Pass", "Y");
			log.info("completed executing the method:: bidder_02_Login");

		} catch (Exception e) {
			log.fatal("Unable to open the URL" + e.getMessage());
			pdfResultReport.addStepDetails("bidder_02_Login", "bidder_02_Login is not logged in",
					"Unable to login as bidder_02_Login" + e.getMessage(), "Fail", "N");
		}
	}

	// Bidder3 login
	public void bidder_03_Login() throws Throwable {
		try {
			log.info("started executing the method:: bidder_03_Login");
			click(tendercreationlocators.login, "login");
			set(tendercreationlocators.userName, pdfResultReport.testData.get("Bidder3UserName"), "userName");
			waitForObj(5000);
			set(tendercreationlocators.password, pdfResultReport.testData.get("AppPassword"), "password");
			//Handle fixed Captcha (06/11/2020)
			set(tendercreationlocators.Captcha_Login, "1234", "Login_Captcha");
			//Wait statement (Added to handle Captcha temporarily in AWS QA (19/10/2020))
			//waitForObj(50000);
			click(tendercreationlocators.okButton, "okButton");
			waitForElement(tendercreationlocators.dashboardIcon, 5000);
			pdfResultReport.addStepDetails("bidder_03_Login", "bidder_03_Login must be sucessfully logged in",
					"Successfully logged in as bidder_03_Login" + " ", "Pass", "Y");
			log.info("completed executing the method:: bidder_03_Login");

		} catch (Exception e) {
			log.fatal("Unable to open the URL" + e.getMessage());
			pdfResultReport.addStepDetails("bidder_03_Login", "bidder_03_Login is not logged in",
					"Unable to login as bidder_03_Login" + e.getMessage(), "Fail", "N");
		}
	}

	public void bidder_04_Login() throws Throwable {
		try {
			log.info("started executing the method:: bidder_04_Login");
			click(tendercreationlocators.login, "login");
			set(tendercreationlocators.userName, pdfResultReport.testData.get("Bidder4UserName"), "userName");
			waitForObj(5000);
			set(tendercreationlocators.password, pdfResultReport.testData.get("AppPassword"), "password");
			click(tendercreationlocators.okButton, "okButton");
			waitForElement(tendercreationlocators.dashboardIcon, 5000);
			pdfResultReport.addStepDetails("bidder_04_Login", "bidder_04_Login must be sucessfully logged in",
					"Successfully logged in as bidder_04_Login" + " ", "Pass", "Y");
			log.info("completed executing the method:: bidder_04_Login");

		} catch (Exception e) {
			log.fatal("Unable to open the URL" + e.getMessage());
			pdfResultReport.addStepDetails("bidder_04_Login", "bidder_04_Login is not logged in",
					"Unable to login as bidder_04_Login" + e.getMessage(), "Fail", "N");
		}
	}

	public void previewAll_Validation_bidsubmission() throws Throwable {
		try {
			log.info("started executing the method:: previewAll_Validation_bidsubmission");
			IsElementPresent(tendercreationlocators.previewAllTittle);
			IsElementPresent(tendercreationlocators.technical_priviewAll);
			IsElementPresent(tendercreationlocators.termsAndCondition_priviewAll);
			IsElementPresent(tendercreationlocators.generalInformation_priviewAll);
			IsElementPresent(tendercreationlocators.quotationAttachments_priviewAll);
			IsElementPresent(tendercreationlocators.bidderSpecificAttachments_priviewAll);
			IsElementPresent(tendercreationlocators.commercial_priviewAll);
			IsElementPresent(tendercreationlocators.quotationSummary_priviewAll);
			pdfResultReport.addStepDetails("Successfully validate previewAll details",
					" previewAll details must be validated successfully",
					"Successfully validated previewAll details" + " ", "Pass", "Y");
			log.info("completed executing the method:: previewAll_Validation_bidsubmission");
		} catch (Exception e) {
			log.fatal("Not able to validate previewAll details" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to validate previewAll details",
					"Not able to validate previewAll details", "Unable to validate previewAll details" + e.getMessage(),
					"Fail", "N");
		}
	}

	public void tenderPreview_bidListPage() throws Throwable {
		try {
			log.info("started executing the method:: tenderPreview_bidListPage");
			click(tendercreationlocators.action, "action");
			click(tendercreationlocators.tenderPreview, "tenderPreview");
			// waitForObj(5000);
			waitForSpinnerToDisappear();
			IsElementPresent(tendercreationlocators.generalInformation_tenderPreview);
			IsElementPresent(tendercreationlocators.otherAttachments_tenderPreview);
			IsElementPresent(tendercreationlocators.dateSchedule_tenderPreview);
			IsElementPresent(tendercreationlocators.termsAndConditions_tenderPreview);
			IsElementPresent(tendercreationlocators.technical_tenderPreview);
			IsElementPresent(tendercreationlocators.boqSummary_tenderPreview);
			click(tendercreationlocators.previewAllOkButton, "previewAllOkButton");
			pdfResultReport.addStepDetails("Successfully validate tenderPreview details",
					"tenderPreview details must be validate successfully",
					"Successfully validated tenderPreview details" + " ", "Pass", "Y");
			log.info("completed executing the method:: tenderPreview_bidListPage");
		} catch (Exception e) {
			log.fatal("Not able to validate tenderPreview details" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to validate tenderPreview details",
					"Not able to validate tenderPreview details",
					"Unable to validate tenderPreview details" + e.getMessage(), "Fail", "N");
		}
	}

	public void bidPreview_bidListPage() throws Throwable {
		try {
			log.info("started executing the method:: bidPreview_bidListPage");
			click(tendercreationlocators.action, "action");
			click(tendercreationlocators.bidPreview, "bidSubmission_Bid");
			// waitForObj(5000);
			waitForSpinnerToDisappear();
			IsElementPresent(tendercreationlocators.technical_priviewAll);
			IsElementPresent(tendercreationlocators.termsAndCondition_priviewAll);
			IsElementPresent(tendercreationlocators.generalInformation_priviewAll);
			IsElementPresent(tendercreationlocators.bidderSpecificAttachments_priviewAll);
			IsElementPresent(tendercreationlocators.commercial_priviewAll);
			IsElementPresent(tendercreationlocators.quotationSummary_priviewAll);
			click(tendercreationlocators.previewAll_close, "previewAll_close");
			pdfResultReport.addStepDetails("Successfully validate bidPreview details",
					"bidPreview details must be validate successfully",
					"Successfully validated bidPreview details" + " ", "Pass", "Y");
			log.info("completed executing the method:: bidPreview_bidListPage");
		} catch (Exception e) {
			log.fatal("Not able to validate bidPreview details" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to validate bidPreview details",
					"Not able to validate bidPreview details", "Unable to validate bidPreview details" + e.getMessage(),
					"Fail", "N");
		}
	}

	// sendfor approval
	public void sendForApproval_validation() throws Throwable {
		try {
			log.info("started executing the method:: sendForApproval_validation");
			click(tendercreationlocators.sendForApprovalNotRequired, "sendForApprovalNotRequired");
			JSClick(tendercreationlocators.sendForApprovalSubmit, "sendForApprovalSubmit");//

			WebDriverWait wait = new WebDriverWait(ThreadLocalWebdriver.getDriver(), 100);

			wait.until(ExpectedConditions.visibilityOfElementLocated(tendercreationlocators.defaultCatBy));

			pdfResultReport.addStepDetails("Successfully validate sendForApproval",
					"sendForApproval must be validate successfully", "Successfully validated sendForApproval" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: sendForApproval_validation");
		} catch (Exception e) {
			log.fatal("Not able to validate sendForApproval" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to validate sendForApproval",
					"Not able to validate sendForApproval", "Unable to validate sendForApproval" + e.getMessage(),
					"Fail", "N");
		}
	}

	public void previewAll_validation() throws Throwable {
		try {
			log.info("started executing the method:: sendForApproval_validation");
			IsElementPresent(tendercreationlocators.previewoteOpenTender);
			click(tendercreationlocators.previewAllOkButton, "previewAllOkButton");
			click(tendercreationlocators.submitbutton, "submitbutton");
			waitForSpinnerToDisappear();
			waitForObj(3000);
			pdfResultReport.addStepDetails("Successfully validate previewAll details",
					"previewAll details must be validate successfully",
					"Successfully validated previewAll details" + " ", "Pass", "Y");
			log.info("completed executing the method:: previewAll_validation");
		} catch (Exception e) {
			log.fatal("Not able to validate previewAll details" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to validate previewAll details",
					"Not able to validate previewAll details", "Unable to validate previewAll details" + e.getMessage(),
					"Fail", "N");
		}
	}

	public void withdrawbid_bidListPage() throws Throwable {
		try {
			log.info("started executing the method:: withdrawbid_bidListPage");
			click(tendercreationlocators.action, "action");
			click(tendercreationlocators.withdraw_bidlist, "withdraw_bidlist");
			waitForObj(3000);
			IsElementPresent(tendercreationlocators.acceptOrDecline_withdraw);
			IsElementPresent(tendercreationlocators.popUpMessage_withdraw);
			click(tendercreationlocators.newTermsAndServices_CheckBox_withdraw,
					"newTermsAndServices_CheckBox_withdraw");
			click(tendercreationlocators.newTermsAndServices_Accept_withdraw, "newTermsAndServices_Accept_withdraw");
			IsElementPresent(tendercreationlocators.confirmation_withdraw_bidlist);
			click(tendercreationlocators.ok_confirmation_withdraw_bidlist, "ok_confirmation_withdraw_bidlist");
			waitForObj(3000);
			IsElementPresent(tendercreationlocators.successPopUp_withdraw_bidlist);
			click(tendercreationlocators.successPopUpOk_withdraw_bidlist, "successPopUpOk_withdraw_bidlist");

			pdfResultReport.addStepDetails("withdrawbid_bidListPage", "Bid  must be withdrawn successfully",
					"Successfully withdraw bid" + " ", "Pass", "Y");
			log.info("completed executing the method:: withdrawbid_bidListPage");
		} catch (Exception e) {
			log.fatal("Not able to withdraw bid" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to withdraw bid", "Not able to withdraw bid",
					"Unable to withdraw bid" + e.getMessage(), "Fail", "N");
		}
	}

	public void withdrawnBid_Tab_Validation() throws Throwable {
		try {
			log.info("started executing the method:: withdrawnBid_Tab_Validation");
			click(tendercreationlocators.withdrawBidTab, "withdrawBidTab");
			// waitForObj(1000);
			waitForSpinnerToDisappear();
			IsElementPresent(tendercreationlocators.tenderNo_withdrawnOn);

			pdfResultReport.addStepDetails("Navigate to withdrawnBid tab",
					"Tender must be listed under withdrawnBid tab successfully",
					"Successfully listed Tender under withdrawnBid tab " + " ", "Pass", "Y");
			log.info("completed executing the method:: withdrawnBid_Tab_Validation");

		} catch (Exception e) {
			log.fatal("Unable to see Tender under withdrawnBid tab" + e.getMessage());
			pdfResultReport.addStepDetails("Navigate to withdrawnBid tab",
					"Not able to see Tender under withdrawnBid tab",
					"Unable to to see Tender under withdrawnBid tab" + e.getMessage(), "Fail", "N");
		}
	}

	public void modifyDateScheduleTemplate1(int enddatelag, int opendatelag) throws Throwable {
		try {
			log.info("started executing the method:: modifyDateScheduleTemplate");

			clear(tendercreationlocators.bidsubmissionDueDate, "bidsubmissionDueDate");
			set(tendercreationlocators.bidsubmissionDueDate, getBidDueDate(enddatelag), "bidsubmissionDueDate");
			clear(tendercreationlocators.bidsubmissionOpenDate, "bidsubmissionOpenDate");
			set(tendercreationlocators.bidsubmissionOpenDate, getBidOpenDate(opendatelag), "bidsubmissionOpenDate");

			click(tendercreationlocators.savebutton, "savebutton");

			waitForSpinnerToDisappear();

			waitForObj(3000);

			click(tendercreationlocators.submitbutton, "submitbutton");

			waitForSpinnerToDisappear();
			waitForObj(1000);
			pdfResultReport.addStepDetails("Successfully Modify Date Schedule Template",
					"Date Schedule Template must be modify successfully",
					"Successfully Modified Date Schedule Template" + " ", "Pass", "Y");
			log.info("completed executing the method:: modifyDateScheduleTemplate");
		} catch (Exception e) {
			log.fatal("Not able to Modify DateSchedule Template" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to modifyDateScheduleTemplate",
					"Not able to modifyDateScheduleTemplate",
					"Unable to Modify Date Schedule Template " + e.getMessage(), "Fail", "N");
		}
	}

	public void corrigendumHistory_Hyperlink_Validation() throws Throwable {

		try {
			log.info("started executing the method:: corrigendumHistory_Hyperlink_Validation");
			click(tendercreationlocators.corrigendumHistory, "corrigendumHistory");
			waitForObj(10000);
			IsElementPresent(tendercreationlocators.corrigendumHistoryCorrigendumNumber);
			IsElementPresent(tendercreationlocators.corrigendumHistoryTenderNo);
			IsElementPresent(tendercreationlocators.corrigendumHistoryApprovalSection);

			pdfResultReport.addStepDetails("corrigendumHistory is present",
					"corrigendumHistory is present is verified successfully using corrigendumHistory_Hyperlink_Validation",
					"corrigendumHistory is present is verified using corrigendumHistory_Hyperlink_Validation" + " ",
					"Pass", "Y");
			click(tendercreationlocators.close_history, "close_history");
			click(tendercreationlocators.closeButton, "closeButton");
			waitForObj(3000);
			log.info("completed executing the method:: corrigendumHistory_Hyperlink_Validation");
		} catch (Exception e) {
			log.fatal("Not able to see corrigendumHistory" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to see corrigendumHistory",
					"Not able to see corrigendumHistory corrigendumHistory_Hyperlink_Validation",

					"Unable to see corrigendumHistory using corrigendumHistory_Hyperlink_Validation" + e.getMessage(),
					"Fail", "N");
		}
	}

	public void createTender_UVoltasDEMO_boqSummarySorItem() throws Throwable {
		try {
			log.info("started executing the method:: createTender_UVoltasDEMO_boqSummarySorItemTC_13");
			waitForObj(3000);
			click(tendercreationlocators.boqSummary, "boqSummary");

			waitForSpinnerToDisappear();

			click(tendercreationlocators.addSORItem, "addSORItem");

			select(tendercreationlocators.selectSORItemMaterialBy, pdfResultReport.testData.get("SORItemMaterial"));
			select(tendercreationlocators.selectSORItemLocationBy, pdfResultReport.testData.get("SORItemLocation"));
			select(tendercreationlocators.selectSORItemMaterialWeightBy,
					pdfResultReport.testData.get("ItemMaterialWeight"));
			click(tendercreationlocators.addSORItemSearch, "addSORItemSearch");
			waitForObj(3000);

			click(tendercreationlocators.addSORItemCodeCheckBoxBy, "addSORItemCodeCheckBoxBy");
			set(tendercreationlocators.addSORItemQuantity, pdfResultReport.testData.get("SORItemQuantity"),
					"addSORItemQuantity");
			click(tendercreationlocators.addSORItemSave, "addSORItemSave");
			click(tendercreationlocators.savebutton, "savebutton");

			waitForSpinnerToDisappear();

			pdfResultReport.addStepDetails("Successfully Created Tender",
					"Tender must be created successfully using createTender_UVoltasDEMO_boqSummarySorItemTC_13 as template group ",
					"Tender is created successfully using createTender_UVoltasDEMO_boqSummarySorItemTC_13 as template group"
							+ " ",
					"Pass", "Y");
			log.info("completed executing the method:: createTender_UVoltasDEMO_boqSummarySorItemTC_13");
		} catch (Exception e) {
			log.fatal("Not able to create tender" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to create tender",
					"Not able to create tender using createTender_UVoltasDEMO_boqSummarySorItemTC_13 as template group",
					"Unable to create tender using createTender_UVoltasDEMO_boqSummarySorItemTC_13 as template group"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void verifyingTenderInTenderListing() throws Throwable {
		try {
			log.info("started executing the method:: verifyingTenderInTenderListing");
			// waitForObj(10000);
			JSClick(tendercreationlocators.tendersIcon, "tendersIcon");
			JSClick(tendercreationlocators.tenderList, "tenderList");

			WebDriverWait wait = new WebDriverWait(ThreadLocalWebdriver.getDriver(), 60);

			wait.until(ExpectedConditions.visibilityOfElementLocated(tendercreationlocators.defaultCatBy));

			set(tendercreationlocators.tenderListKeyword, tenderReferenceNoLocatorText, "tenderListSearch");
			IsElementPresent(tendercreationlocators.tenderListStatus);

			pdfResultReport.addStepDetails("Navigate to tender List", "Tender list must be verified successfully",
					"Successfully verified the tender list" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyingTenderInTenderListing");

		} catch (Exception e) {
			log.fatal("Unable to locate tender in tender list" + e.getMessage());
			pdfResultReport.addStepDetails("Navigate to tender List", "Not able to locate tender in tender list",
					"Unable to locate tender in tender list" + e.getMessage(), "Fail", "N");
		}
	}

	public void CorrigendumSendForApprovalNotRequired() throws Throwable {
		try {
			log.info("started executing the method:: CorrigendumSendForApprovalNotRequired");
			waitForObj(3000);
			click(tendercreationlocators.corrigendumSendForApprovalNotRequired,
					"corrigendumSendForApprovalNotRequired");
			JSClick(tendercreationlocators.sendForApprovalSubmit, "sendForApprovalSubmit");
			// waitForObj(20000);
			WebDriverWait wait = new WebDriverWait(ThreadLocalWebdriver.getDriver(), 100);

			wait.until(ExpectedConditions.visibilityOfElementLocated(tendercreationlocators.defaultCatBy));
			pdfResultReport.addStepDetails("Navigate to tender List", "Corrigundem must be successfully submitted",
					"Successfully verified the Corrigundem" + " ", "Pass", "Y");
			log.info("completed executing the method:: CorrigendumSendForApprovalNotRequired");

		} catch (Exception e) {
			log.fatal("Unable to verify Corrigendum" + e.getMessage());
			pdfResultReport.addStepDetails("Navigate to tender List", "Not able to submit Corrigundem",
					"Unable to verify Corrigendum" + e.getMessage(), "Fail", "N");
		}
	}

	public void itemCorrigendumBOQSummary() throws Throwable {
		try {
			log.info("started executing the method:: itemCorrigendumBOQSummary");
			click(tendercreationlocators.action, "action");
			click(tendercreationlocators.corrigendumLink, "corrigendumLink");
			waitForObj(3000);
			set(tendercreationlocators.corrigendumReferenceNumber,
					pdfResultReport.testData.get("CorrigendumReferenceNumber"), "corrigendumReferenceNumber");
			set(tendercreationlocators.corrigendumDescription, pdfResultReport.testData.get("CorrigendumDescription"),
					"corrigendumDescription");
			click(tendercreationlocators.corrigendumSelect, "corrigendumSelect");
			click(tendercreationlocators.corrigendumSelect_NonSORItemSummary, "corrigendumSelect_NonSORItemSummary");
			// select(tendercreationlocators.corrigendumSelect,pdfResultReport.testData.get("CorrigendumSelect"));
			pdfResultReport.addStepDetails("Successfully Create itemCorrigendumBOQSummary",
					"itemCorrigendum must be created successfully", "Successfully Created itemCorrigendum" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: itemCorrigendumBOQSummary");
		} catch (Exception e) {
			log.fatal("Not able to create itemCorrigendumBOQSummary" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to create tender", "Not able to create itemCorrigendumBOQSummary",
					"Unable to create  itemCorrigendumBOQSummary " + e.getMessage(), "Fail", "N");
		}
	}

	public void addItemInUVoltasDEMO_boqSummary() throws Throwable {
		try {
			log.info("started executing the method:: addItemInUVoltasDEMO_boqSummary");
			waitForObj(5000);
			click(tendercreationlocators.addNonSORItem, "addNonSORItem");
			set(tendercreationlocators.BOQSummaryItemCode2_tender2creationbydutta,
					pdfResultReport.testData.get("BOQ(Mandatory)-ItemCode2"),
					"BOQSummaryItemCode2_tender2creationbydutta");
			set(tendercreationlocators.BOQSummaryItemdescription2_tender2creationbydutta,
					pdfResultReport.testData.get("BOQ(Mandatory)-ItemDescription2"),
					"BOQSummaryItemdescription2_tender2creationbydutta");
			set(tendercreationlocators.boqSummaryQuantity2, pdfResultReport.testData.get("Quantity"),
					"boqSummaryQuantity2");
			// select(tendercreationlocators.boqSummaryUom,pdfResultReport.testData.get("UOM"));
			set(tendercreationlocators.boqSummarySorRate2, pdfResultReport.testData.get("SORRate"),
					"boqSummarySorRate2");
			// select(tendercreationlocators.boqSummaryMandatoryItem,pdfResultReport.testData.get("MandatoryItem"));
			click(tendercreationlocators.savebutton, "savebutton");
			click(tendercreationlocators.submitbutton, "submitbutton");
			pdfResultReport.addStepDetails("Successfully Created Tender",
					"Tender must be created successfully using createTender_UVoltasDEMO_boqSummary as template group ",
					"Tender is created successfully using createTender_UVoltasDEMO_boqSummary as template group" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: createTender_UVoltasDEMO_boqSummary");
		} catch (Exception e) {
			log.fatal("Not able to create tender" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to create tender",
					"Not able to create tender using createTender_UVoltasDEMO_boqSummary as template group",
					"Unable to create tender using createTender_UVoltasDEMO_boqSummary as template group"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void corrigendumNumber_Hyperlink_ValidationForBOQSummary() throws Throwable {
		try {
			log.info("started executing the method:: corrigendumNumber_Hyperlink_ValidationForBOQMandatory");
			waitForObj(10000);
			click(tendercreationlocators.corrigendumNumber, "corrigendumNumber");
			IsElementPresent(tendercreationlocators.corrigendumNewboqSummary);
			IsElementPresent(tendercreationlocators.corrigendumOldboqSummary);
			click(tendercreationlocators.previewAllOkButton_p, "previewAllOkButton_p");
			pdfResultReport.addStepDetails("CorrigendumNumber is present",
					"CorrigendumNumber is present is verified successfully using corrigendumNumber_Hyperlink_ValidationForBOQSummary",
					"CorrigendumNumber is present is verified using corrigendumNumber_Hyperlink_ValidationForBOQSummary"
							+ " ",
					"Pass", "Y");
			log.info("completed executing the method:: corrigendumNumber_Hyperlink_ValidationForBOQSummary");
		} catch (Exception e) {
			log.fatal("Not able to see CorrigendumNumber" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to see ViewAll",
					"Not able to see CorrigendumNumberusing corrigendumNumber_Hyperlink_ValidationForBOQSummary",
					"Unable to see CorrigendumNumber using corrigendumNumber_Hyperlink_ValidationForBOQSummary"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void sendForApproval_validationCorrigendum() throws Throwable {
		try {

			log.info("started executing the method:: sendForApproval_validation");

			waitForObj(3000);
			click(tendercreationlocators.corrigendumSendForApprovalNotRequired,
					"corrigendumSendForApprovalNotRequired");
			JSClick(tendercreationlocators.sendForApprovalSubmit, "sendForApprovalSubmit");

			WebDriverWait wait = new WebDriverWait(ThreadLocalWebdriver.getDriver(), 100);

			wait.until(ExpectedConditions.visibilityOfElementLocated(tendercreationlocators.defaultCatBy));

			pdfResultReport.addStepDetails("Successfully validate sendForApproval",
					"sendForApproval_validationCorrigendum must be validate successfully",
					"Successfully validated sendForApproval_validationCorrigendum" + " ", "Pass", "Y");
			log.info("completed executing the method:: sendForApproval_validationCorrigendum");
		} catch (Exception e) {
			log.fatal("Not able to validate sendForApproval in Corrigendum" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to validate sendForApproval in Corrigendum",
					"Not able to validate sendForApproval in Corrigendum",
					"Unable to validate sendForApproval_validationCorrigendum" + e.getMessage(), "Fail", "N");
		}
	}

	public void publishedVerification() {
		if (text(tendercreationlocators.tenderListStatus_Published).equals(tendercreationlocators.tenderListStatus)) {
			System.out.println(
					"Status of Tender Creation is in Published" + tendercreationlocators.tenderListStatus + "status");
		} else {
			System.out.println("Status of Tender Creation is not in Draft, it is in "
					+ tendercreationlocators.tenderListStatus + "status");
		}
		waitForObj(5000);
	}

	public void createTender_UVoltasDEMO_generalInformationLimitedTender() throws Throwable {
		try {
			log.info("started executing the method:: createTender_UVoltasDEMO_generalInformationLimitedTender");
			// waitForElement(tendercreationlocators.templateGroupdropdown,
			// 5000);
			waitForSpinnerToDisappear();
			select(tendercreationlocators.templateGroupdropdown, "UVoltas DEMO");
			select(tendercreationlocators.bitPartdropdown, pdfResultReport.testData.get("BidParts"));
			click(tendercreationlocators.bitPartRadio, "bitPartRadio");
			set(tendercreationlocators.tenderReferenceNumber, pdfResultReport.testData.get("TenderReferencNumber"),
					"tenderReferenceNumber");
			select(tendercreationlocators.procurementCategory, "Default_cat");
			select(tendercreationlocators.tenderCurrency, pdfResultReport.testData.get("TenderCurrency"));
			set(tendercreationlocators.probabaleAmountContract, pdfResultReport.testData.get("PAC"),
					"probabaleAmountContract");
			select(tendercreationlocators.biddingCurrency, pdfResultReport.testData.get("BiddingCurrency"));
			select(tendercreationlocators.tenderType, pdfResultReport.testData.get("TenderType2"));
			click(tendercreationlocators.addBidders, "addBidders");
			click(tendercreationlocators.bidderCompany_CTS, "bidderCompany_CTS");
			click(tendercreationlocators.addBidderCompany, "addBidderCompany");
			set(tendercreationlocators.detailedDescription,
					pdfResultReport.testData.get("CreateTender-DetailedDescription"), "detailedDescription");
			click(tendercreationlocators.savebutton, "savebutton");
			pdfResultReport.addStepDetails("Successfully Created Tender",
					"Tender must be created successfully using createTender_UVoltasDEMO_generalInformation as template group ",
					"Tender is created successfully using createTender_UVoltasDEMO_generalInformationLimitedTender as template group"
							+ " ",
					"Pass", "Y");
			log.info("completed executing the method:: createTender_UVoltasDEMO_generalInformationLimitedTender");
		} catch (Exception e) {
			log.fatal("Not able to create tender" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to create tender",
					"Not able to create tender using createTender_UVoltasDEMO_generalInformationLimitedTender as template group",
					"Unable to create tender using createTender_UVoltasDEMO_generalInformationLimitedTender as template group"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void previewAll_validationLimitedTender() throws Throwable {
		try {
			log.info("started executing the method:: previewAll_validationLimitedTender");
			IsElementPresent(tendercreationlocators.previewoteLimitedTender);
			click(tendercreationlocators.previewAllOkButton, "previewAllOkButton");
			click(tendercreationlocators.submitbutton, "submitbutton");
			// waitForObj(3000);
			waitForSpinnerToDisappear();
			pdfResultReport.addStepDetails("Successfully validate previewAll details",
					"previewAll details must be validate successfully",
					"Successfully validated previewAll details" + " ", "Pass", "Y");
			log.info("completed executing the method:: previewAll_validationLimitedTender");
		} catch (Exception e) {
			log.fatal("Not able to validate previewAll_validationLimitedTender details" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to validate previewAll_validationLimitedTender details",
					"Not able to validate previewAll_validationLimitedTender details",
					"Unable to validate previewAll_validationLimitedTender details" + e.getMessage(), "Fail", "N");
		}
	}

	public void tenderApproval1_CommentSectionApproval_UVoltasDEMO() throws Throwable {
		try {
			log.info("started executing the method:: tenderApproval1_CommentSectionApproval_UVoltasDEMO");
			click(tendercreationlocators.details, "details");
			waitForObj(5000);
			IsElementPresent(tendercreationlocators.approverComment);
			click(tendercreationlocators.otherAttachmentsTab, "otherAttachmentsTab");
			waitForObj(5000);
			IsElementPresent(tendercreationlocators.approverComment);
			click(tendercreationlocators.attachmentsTab, "attachmentsTab");
			waitForObj(5000);
			IsElementPresent(tendercreationlocators.approverComment);
			click(tendercreationlocators.dateScheduleTab, "dateScheduleTab");
			waitForObj(5000);
			IsElementPresent(tendercreationlocators.approverComment);
			click(tendercreationlocators.termsAndConditionsTemplate, "termsAndConditionsTemplate");
			waitForObj(5000);
			IsElementPresent(tendercreationlocators.approverComment);
			click(tendercreationlocators.technicalTab, "technicalTab");
			waitForObj(5000);
			IsElementPresent(tendercreationlocators.approverComment);
			click(tendercreationlocators.next, "next");
			click(tendercreationlocators.boqSummaryTab, "boqSummaryTab");
			waitForObj(5000);
			IsElementPresent(tendercreationlocators.approverComment);
			pdfResultReport.addStepDetails("Comment section is present",
					"Comment section is present is verified successfully using tenderApproval1_CommentSectionApproval_UVoltasDEMO",
					"Comment section is present is verified using tenderApproval1_CommentSectionApproval_UVoltasDEMO as template group"
							+ " ",
					"Pass", "Y");
			log.info("completed executing the method:: tenderApproval1_CommentSectionApproval_UVoltasDEMO");
		} catch (Exception e) {
			log.fatal("Not able to create tender" + e.getMessage());
			pdfResultReport.addStepDetails("Not able locate comment",
					"Not able to locate comment using tenderApproval1_CommentSectionApproval_UVoltasDEMO as template group",
					"Unable to locate comment using tenderApproval1_CommentSectionApproval_UVoltasDEMO as template group"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void tenderApproval1_ProvidingComment_UVoltasDEMO() throws Throwable {
		try {
			log.info("started executing the method:: tenderApproval1_ProvidingComment_UVoltasDEMO");
			// BOQ Summary
			set(tendercreationlocators.boqSummaryTabComment, pdfResultReport.testData.get("TenderApproval-CommentTab"),
					"boqSummaryTabComment");
			JSClick(tendercreationlocators.saveTabComment_BoqMandatory, "saveTabComment_BoqMandatory");
			waitForObj(4000);
			IsElementPresent(tendercreationlocators.alertTab);
			click(tendercreationlocators.alertTabOk, "alertTabOk");
			click(tendercreationlocators.technicalTab, "technicalTab");
			waitForObj(5000);
			// Technical
			set(tendercreationlocators.commentTechnical1, pdfResultReport.testData.get("TenderApproval-CommentTab"),
					"commentTechnical1");
			JSClick(tendercreationlocators.saveTabComment_Technical1, "saveTabComment_Technical1");
			waitForObj(4000);
			IsElementPresent(tendercreationlocators.alertTab);
			click(tendercreationlocators.alertTabOk, "alertTabOk");
			click(tendercreationlocators.termsAndConditionsTemplate, "termsAndConditionsTemplate");
			waitForObj(5000);
			// Terms and Conditions
			set(tendercreationlocators.commentTermsAndConditions1,
					pdfResultReport.testData.get("TenderApproval-CommentTab"), "commentTermsAndConditions1");
			JSClick(tendercreationlocators.saveTabComment_TermsAndConditions1, "saveTabComment_TermsAndConditions1");
			waitForObj(4000);
			IsElementPresent(tendercreationlocators.alertTab);
			click(tendercreationlocators.alertTabOk, "alertTabOk");
			click(tendercreationlocators.dateScheduleTemplate, "dateScheduleTemplate");
			waitForObj(5000);
			// Date Schedule
			set(tendercreationlocators.commentDateSchedule, pdfResultReport.testData.get("TenderApproval-CommentTab"),
					"commentDateSchedule");
			JSClick(tendercreationlocators.saveTabComment_DateSchedule, "saveTabComment_DateSchedule");
			waitForObj(4000);
			IsElementPresent(tendercreationlocators.alertTab);
			click(tendercreationlocators.alertTabOk, "alertTabOk");

			click(tendercreationlocators.previousBy, "previousBy");

			click(tendercreationlocators.attachmentsTab, "attachmentsTab");
			waitForObj(5000);
			// Attachments
			set(tendercreationlocators.commentAttachments, pdfResultReport.testData.get("TenderApproval-CommentTab"),
					"commentAttachments");
			JSClick(tendercreationlocators.saveTabComment_Attachments, "saveTabComment_Attachments");
			waitForObj(4000);
			IsElementPresent(tendercreationlocators.alertTab);
			click(tendercreationlocators.alertTabOk, "alertTabOk");
			click(tendercreationlocators.otherAttachmentsTab, "otherAttachmentsTab");
			waitForObj(5000);
			// Other Attachments
			set(tendercreationlocators.commentOtherAttachments,
					pdfResultReport.testData.get("TenderApproval-CommentTab"), "commentOtherAttachments");
			JSClick(tendercreationlocators.saveTabComment_OtherAttachments, "saveTabComment_OtherAttachments");
			waitForObj(4000);
			IsElementPresent(tendercreationlocators.alertTab);
			click(tendercreationlocators.alertTabOk, "alertTabOk");
			waitForObj(5000);
			// click(tendercreationlocators.prev,"prev");
			click(tendercreationlocators.generalInformationTab, "generalInformationTab");
			pdfResultReport.addStepDetails("Successfully provided comments",
					"Created Tender must be provided with comments successfully using tenderApproval1_ProvidingComment_UVoltasDEMO as template group ",
					"Tender is created successfully using tenderApproval1_ProvidingComment_UVoltasDEMO as template group"
							+ " ",
					"Pass", "Y");
			log.info("completed executing the method:: tenderApproval1_ProvidingComment_UVoltasDEMO");
		} catch (Exception e) {
			log.fatal("Not able to provide comments" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to provide comments",
					"Not able to provide comments using tenderApproval1_ProvidingComment_UVoltasDEMO as template group",
					"Unable to provide comments using tenderApproval1_ProvidingComment_UVoltasDEMO as template group"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void createTender_TenderTwoCreationByDutta_GeneralInformationWithOpenTender() throws Throwable {
		try {
			log.info("started executing the method:: createTender_TenderTwoCreationByDutta_GeneralInformation");
			select(tendercreationlocators.templateGroupdropdown, "Tender 2 Creation by dutta");
			select(tendercreationlocators.bitPartdropdown, pdfResultReport.testData.get("BidParts"));
			click(tendercreationlocators.bitPartRadio, "bitPartRadio");
			set(tendercreationlocators.tenderReferenceNumber, pdfResultReport.testData.get("TenderReferencNumber"),
					"tenderReferenceNumber");
			select(tendercreationlocators.procurementCategory, "Default_cat");
			select(tendercreationlocators.tenderCurrency, pdfResultReport.testData.get("TenderCurrency"));
			set(tendercreationlocators.probabaleAmountContract, pdfResultReport.testData.get("PAC"),
					"probabaleAmountContract");
			select(tendercreationlocators.biddingCurrency, pdfResultReport.testData.get("BiddingCurrency"));
			select(tendercreationlocators.tenderType, pdfResultReport.testData.get("TenderType"));
			// click(tendercreationlocators.addBidders,"addBidders");
			// click(tendercreationlocators.bidderCompany_TechMahindra,"bidderCompany_TechMahindra");
			// click(tendercreationlocators.addBidderCompany,"addBidderCompany");
			select(tendercreationlocators.previousTenderNo, "1000 (Cigniti-Sample Tender)");
			set(tendercreationlocators.detailedDescription,
					pdfResultReport.testData.get("CreateTender-DetailedDescription"), "detailedDescription");
			click(tendercreationlocators.savebutton, "savebutton");

			pdfResultReport.addStepDetails("GeneralInformation tab should be saved Successfully",
					"GeneralInformation tab should be saved Successfully using createTender_TenderTwoCreationByDutta_GeneralInformation",
					"GeneralInformation tab save is successfully using createTender_TenderTwoCreationByDutta_GeneralInformation"
							+ " ",
					"Pass", "Y");
			log.info("completed executing the method:: createTender_TenderTwoCreationByDutta_GeneralInformation");
		} catch (Exception e) {
			log.fatal("Not able save GeneralInformation tab" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to create tender",
					"Not able to create tender using createTender_TenderTwoCreationByDutta_GeneralInformation as template group",
					"Unable to create tender using createTender_TenderTwoCreationByDutta_GeneralInformation as template group"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void corrigendumSubmitButton() throws Throwable {
		try {
			log.info("started executing the method:: corrigendumSubmitButton");
			waitForObj(3000);
			click(tendercreationlocators.submitbutton, "submitbutton");
			waitForSpinnerToDisappear();

			pdfResultReport.addStepDetails("Successfully clicked submit button",
					"Submit button should be clicked successfully using corrigendumSubmitButton as template group ",
					"Successfully clicked on submit button corrigendumSubmitButton as template group" + " ", "Pass",
					"Y");
			log.info("completed executing the method:: corrigendumSubmitButton");
		} catch (Exception e) {
			log.fatal("Not able to click on submit button" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to click on submit button",
					"Not able to click on submit button using corrigendumSubmitButton as template group",
					"Unable to click on submit button using corrigendumSubmitButton as template group" + e.getMessage(),
					"Fail", "N");
		}
	}

	/**
	 * @author E005672 add the Sor Items codes in a list.
	 */
	public List<String> nonMandatoryItems = new ArrayList<String>();
	public List<String> mandatoryItems = new ArrayList<String>();
	List<WebElement> itemsList;

	public void addSorItemCodesFromItemMenu() {
		itemsList = ThreadLocalWebdriver.getDriver().findElements(tendercreationlocators.itemNamesInItemMenutableBy);
		nonMandatoryItems.add(itemsList.get(0).getText());
		nonMandatoryItems.add(itemsList.get(1).getText());
		mandatoryItems.add(itemsList.get(2).getText());
		mandatoryItems.add(itemsList.get(3).getText());
	}

	private String actualDefaultRecords = "Showing 2 of 2 Records";

	private List<String> bidMandotaryListItems;

	/**
	 * check Mandatory sor and Non-sor Items In
	 * QuotationSummaryTab_bidsubmission
	 */
	public boolean checkMandatorySorOrNonSorItems() {
		boolean flag = false;

		List<WebElement> bidMandatoryItemslist = ThreadLocalWebdriver.getDriver().findElements(
				By.xpath("//span[text()='Item Code']/following::table[1]/tbody/tr/td[1]/child::div/child::span"));

		bidMandotaryListItems = new ArrayList();

		bidMandotaryListItems.add(bidMandatoryItemslist.get(2).getText());
		bidMandotaryListItems.add(bidMandatoryItemslist.get(3).getText());

		String expectedDefaultRecords = text(tendercreationlocators.noOfRecordsBy);
		if (bidMandotaryListItems.containsAll(mandatoryItems)
				&& expectedDefaultRecords.equalsIgnoreCase(actualDefaultRecords))
			flag = true;
		mandatoryItems.clear();
		return flag;

	}

	/**
	 * Check nonmandatory sor And non-sor items in addOptional Table popup
	 * 
	 * @author E005672 venkatesh jujjuru
	 */
	public Boolean checkNonMandatorySorOrNonSorItems() {
		boolean flag = false;

		List<WebElement> BidnonMandatoryList = ThreadLocalWebdriver.getDriver()
				.findElements(By.xpath("//table/thead/tr/th[text()='Item Name']//following::tbody/tr/td[2]/label"));
		List<String> bidNonMandotaryListItems = new ArrayList();

		bidNonMandotaryListItems.add(BidnonMandatoryList.get(0).getText());
		bidNonMandotaryListItems.add(BidnonMandatoryList.get(1).getText());

		if (bidNonMandotaryListItems.containsAll(nonMandatoryItems))
			flag = true;
		nonMandatoryItems.clear();
		return flag;

	}

	/**
	 * validate Mandatory NonMandatory Sor and Non Sor items in
	 * QuotationSummaryTab bidsubmission
	 * 
	 * @author E005672 venkatesh jujjuru
	 */

	private String recordsafteradded = "Showing 4 of 4 Records";

	public void validate_Mandatory_NonMandatory_QuotationSummaryTab_bidsubmission_() throws Throwable {
		try {
			log.info(
					"started executing the method:: validate_Mandatory_NonMandatory_QuotationSummaryTab_bidsubmission_");
			click(tendercreationlocators.quotationSummary_bidSubmission, "quotationSummary_bidSubmission");

			waitForSpinnerToDisappearInBidSubmission();

			boolean checkMandatorySorItems = checkMandatorySorOrNonSorItems();

			Assert.assertTrue(checkMandatorySorItems, "It is Diaplaying only Mandatory items");
			log.info("It is Diaplaying only Mandatory items");

			click(tendercreationlocators.addOptionalBy, "addOptionalBy");
			IsElementPresent(tendercreationlocators.addOptionalPopUpBy);

			waitForObj(2000);

			Boolean checknonMandatorySorItems = checkNonMandatorySorOrNonSorItems();

			Assert.assertTrue(checknonMandatorySorItems, "It is Diaplaying only Non Mandatory items");
			log.info("It is Diaplaying only Non Mandatory items");

			waitForObj(2000);

			click(tendercreationlocators.allOptionalItemsCheckedBy, "allOptionalItemsCheckedBy");

			JSClick(tendercreationlocators.allOptionalItemsOkBy, "okBy");

			waitForObj(4000);

			String afterAddingOptionalItemsRecords = text(tendercreationlocators.noOfRecordsBy);

			if (afterAddingOptionalItemsRecords.equalsIgnoreCase(recordsafteradded)) {
				System.out.println(
						"It is displaying correctly both mandatory and Nonmandatory items records in menu menu Item box list");
			}

			JSClick(tendercreationlocators.savebutton, "savebutton");

			waitForSpinnerToDisappearInBidSubmission();

			waitForObj(2000);

			IsElementPresent(tendercreationlocators.alertPopUp_QRC_bidSubmission);
			IsElementPresent(tendercreationlocators.quotationSummaryAlertTab_bidSubmission);
			click(tendercreationlocators.alertClose_QRC_bidSubmission, "alertClose_QRC_bidSubmission");

			pdfResultReport.addStepDetails("Successfully validate quotationSummaryTab",
					"quotationSummaryTab must be validate successfully",
					"Successfully validate quotationSummaryTab" + " ", "Pass", "Y");
			log.info(
					"completed executing the method:: validate_Mandatory_NonMandatory_QuotationSummaryTab_bidsubmission_");
		} catch (Exception e) {
			log.fatal("Not able to validate quotationSummaryTab" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to validate quotationSummaryTab",
					"Not able to validate quotationSummaryTab",
					"Unable to validate quotationSummaryTab" + e.getMessage(), "Fail", "N");
		}
	}

	public void reBid_bidListPage() throws Throwable {
		try {
			log.info("started executing the method:: reBid_bidListPage");
			click(tendercreationlocators.action, "action");
			click(tendercreationlocators.reBid, "reBid");
			waitForObj(5000);

			pdfResultReport.addStepDetails("Successfully reBid ", "reBid must be done successfully",
					"Successfully done reBid" + " ", "Pass", "Y");
			log.info("completed executing the method:: reBid_bidListPage");
		} catch (Exception e) {
			log.fatal("Not able to reBid" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to reBid", "Not able to reBid", "Unable to reBid" + e.getMessage(),
					"Fail", "N");
		}
	}

	public void mandatoryFieldValidation_attachmentsTab_reBid() throws Throwable {
		try {
			log.info("started executing the method:: mandatoryFieldValidation_attachmentsTab_reBid");
			click(tendercreationlocators.attachementsTab, "attachementsTab");
			waitForObj(1000);
			click(tendercreationlocators.addtenderattachmenticon, "addtenderattachmenticon");
			set(tendercreationlocators.upload, System.getProperty("user.dir") + "\\MediaFiles\\rfqCreation1.xlsx",
					"fileName");
			click(tendercreationlocators.savebutton, "savebutton");
			IsElementPresent(tendercreationlocators.alertPopUp_QRC_bidSubmission);
			IsElementPresent(tendercreationlocators.attachmentsAlertTab_bidSubmission);
			click(tendercreationlocators.alertClose_QRC_bidSubmission, "alertClose_QRC_bidSubmission");
			pdfResultReport.addStepDetails("Successfully validate attachmentsTab ",
					"attachmentsTab must be validate successfully", "Successfully validated attachmentsTab" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: mandatoryFieldValidation_attachmentsTab_reBid");
		} catch (Exception e) {
			log.fatal("Not able to validate attachmentsTab" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to validate attachmentsTab ",
					"Not able to validate attachmentsTab", "Unable to validate" + e.getMessage(), "Fail", "N");
		}
	}

	public void draftBid_Tab_Validation() throws Throwable {
		try {
			log.info("started executing the method:: draftBid_Tab_Validation");
			//click(tendercreationlocators.draftBidTab, "draftBidTab");
			waitForObj(1000);
			IsElementPresent(tendercreationlocators.tenderNo_draftBid);

			pdfResultReport.addStepDetails("Navigate to draftBid tab",
					"Tender must be listed under draftBid tab successfully",
					"Successfully listed Tender under draftBid tab " + " ", "Pass", "Y");
			log.info("completed executing the method:: draftBid_Tab_Validation");

		} catch (Exception e) {
			log.fatal("Unable to see Tender under withdrawnBid tab" + e.getMessage());
			pdfResultReport.addStepDetails("Navigate to draftBid tab", "Not able to see Tender under draftBid tab",
					"Unable to to see Tender under  draftBid tab" + e.getMessage(), "Fail", "N");
		}
	}

	public void editBid_bidListPage() throws Throwable {
		try {
			log.info("started executing the method:: Editbid_bidListPage");
			click(tendercreationlocators.action, "action");
			click(tendercreationlocators.editBid, "editBid");
			waitForObj(3000);
			pdfResultReport.addStepDetails("Editbid_bidListPage",
					"Editbid link should be clicked successfully",
					"Successfully clicked edit bid link" + " ", "Pass", "Y");
			log.info("completed executing the method:: Editbid_bidListPage");
		} catch (Exception e) {
			log.fatal("Not able to validate Editbid_bidListPage details" + e.getMessage());
			pdfResultReport.addStepDetails("Editbid_bidListPage",
					"Not able to click editbid link",
					"Unable to click editbid link" + e.getMessage(), "Fail", "N");
		}
	}

	public void addNonSorItemCodesFromItemMenu() {
		JavascriptExecutor je = (JavascriptExecutor) ThreadLocalWebdriver.getDriver();

		String itemcode1 = (String) je
				.executeScript("return document.getElementById('uvoltasdemori.item_code.0').value;");

		String itemcode2 = (String) je
				.executeScript("return document.getElementById('uvoltasdemori.item_code.1').value;");

		nonMandatoryItems.add(itemcode1);
		nonMandatoryItems.add(itemcode2);

		String itemcode3 = (String) je
				.executeScript("return document.getElementById('uvoltasdemori.item_code.2').value;");

		String itemcode4 = (String) je
				.executeScript("return document.getElementById('uvoltasdemori.item_code.3').value;");

		mandatoryItems.add(itemcode3);
		mandatoryItems.add(itemcode4);

	}

	public void validate_Mandatory_NonMandatory_Items_QuotationSummaryTab_bidsubmission_() throws Throwable {
		try {
			log.info(
					"started executing the method:: validate_Mandatory_NonMandatory_QuotationSummaryTab_bidsubmission_");
			click(tendercreationlocators.quotationSummary_bidSubmission, "quotationSummary_bidSubmission");
			waitForObj(10000);

			boolean checkMandatorySorItems = checkMandatorySorOrNonSorItems();

			Assert.assertTrue(checkMandatorySorItems, "It is Diaplaying only Mandatory items");
			log.info("It is Diaplaying only Mandatory items");

			click(tendercreationlocators.addOptionalBy, "addOptionalBy");
			IsElementPresent(tendercreationlocators.addOptionalPopUpBy);

			waitForObj(2000);

			Boolean checknonMandatorySorItems = checkNonMandatorySorOrNonSorItems();

			Assert.assertTrue(checknonMandatorySorItems, "It is Diaplaying only Non Mandatory items");
			log.info("It is Diaplaying only Non Mandatory items");

			waitForObj(2000);

			click(tendercreationlocators.allOptionalItemsCheckedBy, "allOptionalItemsCheckedBy");

			JSClick(tendercreationlocators.allOptionalItemsOkBy, "okBy");

			waitForObj(4000);

			String afterAddingOptionalItemsRecords = text(tendercreationlocators.noOfRecordsBy);

			if (afterAddingOptionalItemsRecords.equalsIgnoreCase(recordsafteradded)) {
				System.out.println(
						"It is displaying correctly both mandatory and Nonmandatory items records in menu menu Item box list");
			}

			JSClick(tendercreationlocators.savebutton, "savebutton");

			waitForObj(4000);

			IsElementPresent(tendercreationlocators.alertPopUp_QRC_bidSubmission);
			IsElementPresent(tendercreationlocators.quotationSummaryAlertTab_bidSubmission);
			click(tendercreationlocators.alertClose_QRC_bidSubmission, "alertClose_QRC_bidSubmission");

			waitForObj(3000);

			pdfResultReport.addStepDetails("Successfully validate quotationSummaryTab",
					"quotationSummaryTab must be validate successfully",
					"Successfully validate quotationSummaryTab" + " ", "Pass", "Y");
			log.info(
					"completed executing the method:: validate_Mandatory_NonMandatory_QuotationSummaryTab_bidsubmission_");
		} catch (Exception e) {
			log.fatal("Not able to validate quotationSummaryTab" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to validate quotationSummaryTab",
					"Not able to validate quotationSummaryTab",
					"Unable to validate quotationSummaryTab" + e.getMessage(), "Fail", "N");
		}
	}

	/**
	 * @author E005672 venkatesh Jujjuru
	 * @throws Exception
	 */
	public void clickPreviewAll() throws Exception {
		try {
			log.info("started executing the method:: clickPreviewAll");
			waitForObj(3000);
			click(tendercreationlocators.previewButton, "previewButton");

			waitForSpinnerToDisappear();
			pdfResultReport.addStepDetails("clickPreviewAll", "Should clickpreviewAll button And popup should appear",
					"SucessFully clicked Preview All button And PopUp is displaying" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickPreviewAll");
		} catch (Exception e) {
			log.fatal("Not able to create tender" + e.getMessage());
			pdfResultReport.addStepDetails("clickPreviewAll",
					"Not able to clickpreviewAll button And popup is not appearing",
					"Unable to click previewAll button And popup is not appearing" + e.getMessage(), "Fail", "N");
		}
	}

	/**
	 * @author E005672 venkatesh Jujjuru
	 * @throws Exception
	 */
	public void clickPreviewOkBtn() throws Exception {
		try {
			log.info("started executing the method:: clickPreviewOkBy");
			click(tendercreationlocators.previewAllOkButton, "previewAllOkButton");
			pdfResultReport.addStepDetails("clickPreviewOkBy", "Prview Pop should close",
					"Prview Pop is  closed suceesfully " + " ", "Pass", "Y");
			log.info("completed executing the method:: Method nmae");

		} catch (Exception e) {

			log.fatal("Unable to " + e.getMessage());
			pdfResultReport.addStepDetails("clickPreviewOkBy", "Not able to close preview popup",
					"Unable to close Preview Pop " + e.getMessage(), "Fail", "N");
		}

	}

	/**
	 * @author E005672
	 * @throws Exception
	 * 
	 */
	public void checkTenderStatusAndTenderStage() throws Exception {
		try {

			WebElement Tenderstatus = ThreadLocalWebdriver.getDriver()
					.findElement(tendercreationlocators.tenderCreatedStatus);
			WebDriverWait wait = new WebDriverWait(ThreadLocalWebdriver.getDriver(), 60);
			wait.until(ExpectedConditions.textToBePresentInElement(Tenderstatus, Tenderstatus.getText()));
			String tenderStatus = text(tendercreationlocators.tenderCreatedStatus);

			if (tenderStatus.trim().equalsIgnoreCase("Published"))
				System.out.println("Tender Status is in published mode");

			WebElement bidpublishStage = ThreadLocalWebdriver.getDriver()
					.findElement(tendercreationlocators.bidpublishStage);

			if (bidpublishStage.getAttribute("class").trim().contains("grnBtn")) {
				System.out.println("stage mode is in Published mode" + bidpublishStage.getAttribute("class").trim());
			} else {
				System.out.println(
						"stage mode is in not in Published mode" + bidpublishStage.getAttribute("class").trim());
			}
			pdfResultReport.addStepDetails("Successfully shown Tender status as published",
					"Tender status must be shown as published using tenderStatusAndTenderStage",
					"Tender status must be shown as published using tenderStatusAndTenderStage" + " ", "Pass", "Y");
			log.info("completed executing the method:: tenderStatusAndTenderStage");
		} catch (Exception e) {
			log.fatal("Unable to show the status" + e.getMessage());
			pdfResultReport.addStepDetails("Unable to show the status",
					"Not able to show the tender status using tenderStatusAndTenderStage",
					"Unable to show the status using tenderStatusAndTenderStage" + e.getMessage(), "Fail", "N");
		}
	}

	public void checktenderStatusAndTenderStage() throws Throwable {
		try {
			log.info("started executing the method:: checktenderStatusAndTenderStage");
			IsElementPresent(tendercreationlocators.evaluationTenderStatus_Evalutation);
			waitForObj(3000);
			// IsElementPresent(tendercreationlocators.evaluationTenderStage);
			// waitForObj(10000);
			pdfResultReport.addStepDetails("checktenderStatusAndTenderStage",
					"Tender status must be shown as Evaluation", "Successfully shown Tender status as Evaluation" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: checktenderStatusAndTenderStage");
		} catch (Exception e) {
			log.fatal("Unable to show the status as completed" + e.getMessage());
			pdfResultReport.addStepDetails("checktenderStatusIsIncompleteddState",
					"Tender status must be shown as Evaluation",
					"Unable to show tender status as Evaluation" + e.getMessage(), "Fail", "N");
		}
	}

	/**
	 * @author E005672
	 * @throws Throwable
	 */
	public void mandatoryFieldValidation_boqOptional_bidsubmission() throws Throwable {
		try {
			log.info("started executing the method:: mandatoryFieldValidation_boqOptional_bidsubmission");
			click(tendercreationlocators.BOQOptionalTab, "BOQOptionalTab");
			waitForSpinnerToDisappearInBidSubmission();

			WebDriverWait wait = new WebDriverWait(ThreadLocalWebdriver.getDriver(), 10);
			wait.until(ExpectedConditions.presenceOfElementLocated(tendercreationlocators.boqaddOptionItemButtonBy));

			WebElement element = ThreadLocalWebdriver.getDriver()
					.findElement(tendercreationlocators.excludingVATFieldBy);

			Coordinates coordinates = ((Locatable) element).getCoordinates();
			coordinates.onPage();
			coordinates.inViewPort();
			set(tendercreationlocators.excludingVATFieldBy, "10", "excludingVATFieldBy");
			click(tendercreationlocators.savebutton, "savebutton");

			waitForSpinnerToDisappearInBidSubmission();

			IsElementPresent(tendercreationlocators.alertPopUp_QRC_bidSubmission);
			// IsElementPresent(tendercreationlocators.boqOptionalAlertTab_BidSubmissionBy);
			click(tendercreationlocators.alertClose_QRC_bidSubmission, "alertClose_QRC_bidSubmission");

			pdfResultReport.addStepDetails("Successfully validate boqOptional tab",
					"boq optional tab must be validate successfully", "Successfully validated boqOptional tab" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: mandatoryFieldValidation_boqOptional_bidsubmission");
		} catch (Exception e) {
			log.fatal("Not able to validate boq optional tab" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to validate boqoptional tab",
					"Not able to validate boqotional tab", "Unable to validate boqotional  tab" + e.getMessage(),
					"Fail", "N");
		}
	}

	/**
	 * @author e005672
	 * @throws Throwable
	 */
	public void mandatoryFieldValidation_BoqmandatoryTab_bidsubmission() throws Throwable {

		String actualRecords = "Showing 8 of 8 Records";
		try {
			log.info("started executing the method:: mandatoryFieldValidation_BoqmandatoryTab_bidsubmission");
			click(tendercreationlocators.BOQmandatoryTab, "BOQmandatoryTab");

			waitForSpinnerToDisappearInBidSubmission();

			WebDriverWait wait = new WebDriverWait(ThreadLocalWebdriver.getDriver(), 10);
			wait.until(ExpectedConditions.presenceOfElementLocated(tendercreationlocators.boqaddOptionItemButtonBy));

			click(tendercreationlocators.boqaddOptionItemButtonBy, "addOptionalBy");
			IsElementPresent(tendercreationlocators.boqaddOptionItemPopupBy);
			waitForObj(2000);
			click(tendercreationlocators.boqallOptionalItemsCheckedBy, "allOptionalItemsCheckedBy");
			JSClick(tendercreationlocators.boqallOptionalItemsPopOkBy, "okBy");
			waitForObj(2000);
			String afterAddingOptionalItemsRecords = text(tendercreationlocators.boqShowrecordsBy);

			if (afterAddingOptionalItemsRecords.equalsIgnoreCase(actualRecords)) {
				System.out.println(
						"It is displaying correctly both mandatory and Nonmandatory items records in menu menu Item box list");
			}

			entermandatoryFieldsInBoqmandatoryItemsBidSubmit();

			String parent1 = ThreadLocalWebdriver.getDriver()
					.findElement(By
							.xpath("//*[@id='target']/following::div/table/tbody/tr[1]/td[11]/div //span/child::span//child::span/child::span"))
					.getAttribute("title");

			String parent2 = ThreadLocalWebdriver.getDriver()
					.findElement(By
							.xpath("//*[@id='target']/following::div/table/tbody/tr[5]/td[11]/div //span/child::span//child::span/child::span"))
					.getAttribute("title");

			String TotalSum = ThreadLocalWebdriver.getDriver()
					.findElement(By.xpath("//*[@id='target']//following::div/table/tbody/tr[9]/td[11]/div/input"))
					.getAttribute("value");

			int p1 = (int) Float.parseFloat(parent1);
			int p2 = (int) Float.parseFloat(parent2);
			int parentTotal = p1 + p2;
			int total = (int) Float.parseFloat(TotalSum);

			if (parentTotal == total) {
				System.out.println("Parents aggregate equals to Totalsum");
			}

			click(tendercreationlocators.savebutton, "savebutton");

			waitForSpinnerToDisappearInBidSubmission();
			waitForObj(3000);
			IsElementPresent(tendercreationlocators.alertPopUp_QRC_bidSubmission);

			click(tendercreationlocators.alertClose_QRC_bidSubmission, "alertClose_QRC_bidSubmission");
			pdfResultReport.addStepDetails("mandatoryFieldValidation_BoqmandatoryTab_bidsubmission",
					"BoqmandatoryTab_bidsubmission must be validate successfully",
					"Successfully validate qBoqmandatoryTab_bidsubmission" + " ", "Pass", "Y");
			log.info("completed executing the method:: mandatoryFieldValidation_BoqmandatoryTab_bidsubmission");
		} catch (Exception e) {
			log.fatal("Not able to validate quotationSummaryTab" + e.getMessage());
			pdfResultReport.addStepDetails("mandatoryFieldValidation_BoqmandatoryTab_bidsubmission",
					"Not able to validate BoqmandatoryTab_bidsubmission",
					"Unable to validate BoqmandatoryTab_bidsubmission" + e.getMessage(), "Fail", "N");
		}
	}

	/**
	 * Enter Boq mandatory fields in Bid Submit
	 */
	public void entermandatoryFieldsInBoqmandatoryItemsBidSubmit() {

		int proposeMake = ThreadLocalWebdriver.getDriver().findElements(tendercreationlocators.boqproposedMakeBy)
				.size();

		for (int i = 0; i < proposeMake; i++) {
			String s = "//*[@id='qi_boq.proposed_make.{0}']";
			ThreadLocalWebdriver.getDriver().findElement(By.xpath(s.replace("{0}", +i + ""))).sendKeys("proposedmake");

			waitForObj(1000);
		}

		int vatvalue = ThreadLocalWebdriver.getDriver().findElements(tendercreationlocators.boqBasicUnitVatBy).size();

		for (int i = 0; i < vatvalue; i++) {
			String s = "//*[@id='qi_boq.basicunit.{0}']";
			// ThreadLocalWebdriver.getDriver().findElement(By.xpath("//*[@id='qi_boq.basicunit.7']"));
			WebElement element = ThreadLocalWebdriver.getDriver().findElement(By.xpath(s.replace("{0}", +i + "")));
			Coordinates coordinates = ((Locatable) element).getCoordinates();
			coordinates.onPage();
			coordinates.inViewPort();
			element.sendKeys("10");

			waitForObj(1000);
		}

	}

	/**
	 * @author E005672
	 * @throws Exception
	 * 
	 */
	public void validateTenderpreviewInBidListPage() throws Exception {
		try {
			log.info("started executing the method:: tenderPreview_bidListPage");
			click(tendercreationlocators.action, "action");
			click(tendercreationlocators.tenderPreview, "tenderPreview");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForObj(5000);
			IsElementPresent(tendercreationlocators.generalInformation_tenderPreview);
			IsElementPresent(tendercreationlocators.otherAttachments_tenderPreview);
			IsElementPresent(tendercreationlocators.dateSchedule_tenderPreview);
			IsElementPresent(tendercreationlocators.termsAndConditions_tenderPreview);
			IsElementPresent(tendercreationlocators.technical_tenderPreview);
			IsElementPresent(tendercreationlocators.TenderPreviewBoQOptionalBy);
			IsElementPresent(tendercreationlocators.TenderPreviewBidBoQmandatoryBy);
			click(tendercreationlocators.previewAllOkButton, "previewAllOkButton");

			pdfResultReport.addStepDetails("Successfully validate tenderPreview details",
					"tenderPreview details must be validate successfully",
					"Successfully validated tenderPreview details" + " ", "Pass", "Y");
			log.info("completed executing the method:: tenderPreview_bidListPage");
		} catch (Exception e) {
			log.fatal("Not able to validate tenderPreview details" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to validate tenderPreview details",
					"Not able to validate tenderPreview details",
					"Unable to validate tenderPreview details" + e.getMessage(), "Fail", "N");
		}
	}
	
	public void TG1_validateTenderpreviewInBidListPage() throws Exception {
		try {
			log.info("started executing the method:: TG1_validateTenderpreviewInBidListPage");
			click(tendercreationlocators.action, "action");
			click(tendercreationlocators.tenderPreview, "tenderPreview");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForObj(5000);
			
			IsElementPresent(tendercreationlocators.PreviewAll_TenderPreviewBidListPage_TG1);
			IsElementPresent(tendercreationlocators.GeneralInfo_TenderPreviewBidListPage_TG1);

			scrollToElement(tendercreationlocators.Terms_Conditions_TenderPreviewBidListPage_TG1);
			IsElementPresent(tendercreationlocators.Terms_Conditions_TenderPreviewBidListPage_TG1);

			scrollToElement(tendercreationlocators.GeneralReq_TenderPreviewBidListPage_TG1);
			IsElementPresent(tendercreationlocators.GeneralReq_TenderPreviewBidListPage_TG1);

			scrollToElement(tendercreationlocators.GeneralInfoClauses_TenderPreviewBidListPage_TG1);
			IsElementPresent(tendercreationlocators.GeneralInfoClauses_TenderPreviewBidListPage_TG1);

			scrollToElement(tendercreationlocators.Attachments_TenderPreviewBidListPage_TG1);
			IsElementPresent(tendercreationlocators.Attachments_TenderPreviewBidListPage_TG1);

			scrollToElement(tendercreationlocators.ReqAttachment_TenderPreviewBidListPage_TG1);
			IsElementPresent(tendercreationlocators.ReqAttachment_TenderPreviewBidListPage_TG1);

			scrollToElement(tendercreationlocators.Payment_TenderPreviewBidListPage_TG1);
			IsElementPresent(tendercreationlocators.Payment_TenderPreviewBidListPage_TG1);

			scrollToElement(tendercreationlocators.RFQItem_TenderPreviewBidListPage_TG1);
			IsElementPresent(tendercreationlocators.RFQItem_TenderPreviewBidListPage_TG1);
			
			scrollToElement(tendercreationlocators.BOQMandatory_TenderPreviewBidListPage_TG1);
			IsElementPresent(tendercreationlocators.BOQMandatory_TenderPreviewBidListPage_TG1);
			
			scrollToElement(tendercreationlocators.DateSchedule_TenderPreviewBidListPage_TG1);
			IsElementPresent(tendercreationlocators.DateSchedule_TenderPreviewBidListPage_TG1);
			
			click(tendercreationlocators.previewAllOkButton, "previewAllOkButton");

			pdfResultReport.addStepDetails("Successfully validate tenderPreview details",
					"tenderPreview details must be validate successfully",
					"Successfully validated tenderPreview details" + " ", "Pass", "Y");
			log.info("completed executing the method:: TG1_validateTenderpreviewInBidListPage");
		} catch (Exception e) {
			log.fatal("Not able to validate tenderPreview details" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to validate tenderPreview details",
					"Not able to validate tenderPreview details",
					"Unable to validate tenderPreview details" + e.getMessage(), "Fail", "N");
		}
	}
//Validate tender preview in bid list page for RFQ TG3 (date: 18/03/2021)
	public void TG3_validateTenderpreviewInBidListPage() throws Exception {
		try {
			log.info("started executing the method:: TG3_validateTenderpreviewInBidListPage");
			click(tendercreationlocators.action, "action");
			click(tendercreationlocators.tenderPreview, "tenderPreview");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForObj(5000);
			
			IsElementPresent(tendercreationlocators.PreviewAll_TenderPreviewBidListPage_TG3);
			IsElementPresent(tendercreationlocators.GeneralInfo_TenderPreviewBidListPage_TG3);

			scrollToElement(tendercreationlocators.Terms_Conditions_TenderPreviewBidListPage_TG3);
			IsElementPresent(tendercreationlocators.Terms_Conditions_TenderPreviewBidListPage_TG3);

			scrollToElement(tendercreationlocators.CommercialTermsCond_TenderPreviewBidListPage_TG3);
			IsElementPresent(tendercreationlocators.CommercialTermsCond_TenderPreviewBidListPage_TG3);

			scrollToElement(tendercreationlocators.Attachments_TenderPreviewBidListPage_TG3);
			IsElementPresent(tendercreationlocators.Attachments_TenderPreviewBidListPage_TG3);

			scrollToElement(tendercreationlocators.PriceFormat_TenderPreviewBidListPage_TG3);
			IsElementPresent(tendercreationlocators.PriceFormat_TenderPreviewBidListPage_TG3);

			scrollToElement(tendercreationlocators.DateSchedule_TenderPreviewBidListPage_TG3);
			IsElementPresent(tendercreationlocators.DateSchedule_TenderPreviewBidListPage_TG3);

			scrollToElement(tendercreationlocators.PrebidDiscussion_TenderPreviewBidListPage_TG3);
			IsElementPresent(tendercreationlocators.PrebidDiscussion_TenderPreviewBidListPage_TG3);
			
			click(tendercreationlocators.previewAllOkButton, "previewAllOkButton");

			pdfResultReport.addStepDetails("Successfully validate tenderPreview details",
					"tenderPreview details must be validate successfully",
					"Successfully validated tenderPreview details" + " ", "Pass", "Y");
			log.info("completed executing the method:: TG3_validateTenderpreviewInBidListPage");
		} catch (Exception e) {
			log.fatal("Not able to validate tenderPreview details" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to validate tenderPreview details",
					"Not able to validate tenderPreview details",
					"Unable to validate tenderPreview details" + e.getMessage(), "Fail", "N");
		}
	}
	/**
	 * @author E005672
	 * @throws Throwable
	 */

	public void validateBidPreview_BidListPage() throws Throwable {
		try {
			log.info("started executing the method:: bidPreview_bidListPage");
			click(tendercreationlocators.action, "action");
			click(tendercreationlocators.bidPreview, "bidSubmission_Bid");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForObj(5000);
			IsElementPresent(tendercreationlocators.technical_priviewAll);
			IsElementPresent(tendercreationlocators.termsAndCondition_priviewAll);
			IsElementPresent(tendercreationlocators.generalInformation_priviewAll);
			IsElementPresent(tendercreationlocators.bidderSpecificAttachments_priviewAll);
			IsElementPresent(tendercreationlocators.commercial_priviewAll);
			IsElementPresent(tendercreationlocators.boqoptionalpreviewAllpageBy);
			IsElementPresent(tendercreationlocators.boqMandatorypreviewAllpageBy);
			click(tendercreationlocators.previewAll_close, "previewAll_close");
			pdfResultReport.addStepDetails("Successfully validate bidPreview details",
					"bidPreview details must be validate successfully",
					"Successfully validated bidPreview details" + " ", "Pass", "Y");
			log.info("completed executing the method:: bidPreview_bidListPage");
		} catch (Exception e) {
			log.fatal("Not able to validate bidPreview details" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to validate bidPreview details",
					"Not able to validate bidPreview details", "Unable to validate bidPreview details" + e.getMessage(),
					"Fail", "N");
		}
	}
	public void TG1_validateBidPreview_BidListPage() throws Throwable {
		try {
			log.info("started executing the method:: TG1_validateBidPreview_BidListPage");
			click(tendercreationlocators.action, "action");
			click(tendercreationlocators.bidPreview, "bidSubmission_Bid");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForObj(5000);
			TG1_validateAllInPrevieAllSubmitbidPage();
			click(tendercreationlocators.previewAll_close, "previewAll_close");
			pdfResultReport.addStepDetails("Successfully validate bidPreview details",
					"bidPreview details must be validate successfully",
					"Successfully validated bidPreview details" + " ", "Pass", "Y");
			log.info("completed executing the method:: TG1_validateBidPreview_BidListPage");
		} catch (Exception e) {
			log.fatal("Not able to validate bidPreview details" + e.getMessage());
			pdfResultReport.addStepDetails("TG1_validateBidPreview_BidListPage",
					"Not able to validate bidPreview details", "Unable to validate bidPreview details" + e.getMessage(),
					"Fail", "N");
		}
	}
	//Validate bid preview in bid list page (date: 18/03/2021)
	public void TG3_validateBidPreview_BidListPage() throws Throwable {
		try {
			log.info("started executing the method:: TG3_validateBidPreview_BidListPage");
			click(tendercreationlocators.action, "action");
			click(tendercreationlocators.bidPreview, "bidSubmission_Bid");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForObj(5000);
			TG3_validateAllInPrevieAllSubmitbidPage();
			click(tendercreationlocators.previewAll_close, "previewAll_close");
			pdfResultReport.addStepDetails("Successfully validate bidPreview details",
					"bidPreview details must be validate successfully",
					"Successfully validated bidPreview details" + " ", "Pass", "Y");
			log.info("completed executing the method:: TG3_validateBidPreview_BidListPage");
		} catch (Exception e) {
			log.fatal("Not able to validate bidPreview details" + e.getMessage());
			pdfResultReport.addStepDetails("TG3_validateBidPreview_BidListPage",
					"Not able to validate bidPreview details", "Unable to validate bidPreview details" + e.getMessage(),
					"Fail", "N");
		}
	}

	public void mandatoryFieldValidation_technicalTab_bidsubmission2() throws Throwable {
		try {
			log.info("started executing the method:: mandatoryFieldValidation_technicalTab_bidsubmission");
			// click(tendercreationlocators.technical,"technical");
			waitForObj(10000);
			click(tendercreationlocators.savebutton1, "savebutton1");
			waitForObj(10000);
			IsElementPresent(tendercreationlocators.alertPopUp_QRC_bidSubmission1);
			waitForObj(10000);
			IsElementPresent(tendercreationlocators.technicalAlertTab_bidSubmission2);
			waitForObj(10000);
			click(tendercreationlocators.alertClose_QRC_bidSubmission, "alertClose_QRC_bidSubmission");
			pdfResultReport.addStepDetails("Successfully validate Technical tab",
					"Technical tab must be validate successfully", "Successfully validated technical tab" + " ", "Pass",
					"Y");
			log.info("completed executing the method:: mandatoryFieldValidation_technicalTab_bidsubmission");
		} catch (Exception e) {
			log.fatal("Not able to validate technical tab" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to validate technical tab", "Not able to validate technical tab",
					"Unable to validate technical tab" + e.getMessage(), "Fail", "N");
		}
	}

	public void selectTenderTypeInTenderList() throws Exception {
		try {
			log.info("started executing the method:: selectTenderTypeInTenderList");

			WebDriverWait wait = new WebDriverWait(ThreadLocalWebdriver.getDriver(), 5);
			wait.until(ExpectedConditions.visibilityOfElementLocated(tendercreationlocators.selectTenderTypeBy));

			select(tendercreationlocators.selectTenderTypeBy, "Limited Tender");

			pdfResultReport.addStepDetails("selectTenderTypeInTenderList", "Should select tender type",
					"sucessfully selected tender type" + " ", "Pass", "Y");
			log.info("completed executing the method:: selectTenderTypeInTenderList");
		} catch (Exception e) {
			log.fatal("Not able to selectTenderTypeInTenderList" + e.getMessage());
			pdfResultReport.addStepDetails("selectTenderTypeInTenderList", "Should select tender type",
					"Unable to validate select tender type" + e.getMessage(), "Fail", "N");
		}

	}

	/**
	 * @author E005672
	 * @throws Throwable
	 */
	public void validateBidsubmitDetailsFromTenderListActionMenu() throws Throwable {
		try {
			log.info("started executing the method:: validateBidsubmitDetailsFromTenderListActionMenu");

			click(tendercreationlocators.action, "action");

			WebDriverWait wait = new WebDriverWait(ThreadLocalWebdriver.getDriver(), 5);
			wait.until(ExpectedConditions.visibilityOfElementLocated(tendercreationlocators.totalSubmittedBidsBy));

			String totalsubmittedbids = text(tendercreationlocators.totalSubmittedBidsBy);
			System.out.println("no.of Bids submited" + totalsubmittedbids);

			click(tendercreationlocators.totalSubmittedBidsBy, "totalSubmittedBidsBy");

			wait.until(ExpectedConditions.visibilityOfElementLocated(tendercreationlocators.bidDetailsPopUpBy));

			String bidstatus = text(tendercreationlocators.bidDetailsStatusBy);

			if (bidstatus.trim().equalsIgnoreCase("Bid submitted")) {
				System.out.println("The bid is  submitted");

				click(tendercreationlocators.bidPreviewLinkBy, "bidPreviewLinkBy");

				WebElement previewall = ThreadLocalWebdriver.getDriver().findElement(
						By.xpath("//div[@id='myModalprev_bid']//following::h3[contains(text(),'Preview All')]"));

				wait.until(ExpectedConditions.visibilityOf(previewall));
				System.out.println("bid preview is displayed");

				IsElementPresent(tendercreationlocators.technical_priviewAll);
				IsElementPresent(tendercreationlocators.termsAndCondition_priviewAll);
				IsElementPresent(tendercreationlocators.generalInformation_priviewAll);
				IsElementPresent(tendercreationlocators.bidderSpecificAttachments_priviewAll);
				IsElementPresent(tendercreationlocators.commercial_priviewAll);
				IsElementPresent(tendercreationlocators.quotationSummary_priviewAll);

				click(tendercreationlocators.previewAll_close, "previewAll_close");

			} else {
				System.out.println("Bid not submitted or bid is withdrawn");
			}

			pdfResultReport.addStepDetails("validateBidsubmitDetailsFromTenderListActionMenu",
					"Should validate bidsubmit details and status",
					"Sucessfully validated bidsubmit details and status" + " ", "Pass", "Y");
			log.info("completed executing the method:: validateBidsubmitDetailsFromTenderListActionMenu");
		} catch (Exception e) {
			log.fatal("validateBidsubmitDetailsFromTenderListActionMenu" + e.getMessage());
			pdfResultReport.addStepDetails("validateBidsubmitDetailsFromTenderListActionMenu",
					"Should validate bidsubmit details and status",
					"Unable to validate bidsubmit details and status" + e.getMessage(), "Fail", "N");
		}
	}

	public void evaluationSettingPageValidation() throws Throwable {
		try {
			log.info("started executing the method:: evaluationSettingPageValidation");

			IsElementPresent(tendercreationlocators.evaluation_tenderId);
			IsElementPresent(tendercreationlocators.evaluation_noOfLiveBids);
			IsElementPresent(tendercreationlocators.evaluation_openAndEvaluationBid);
			IsElementPresent(tendercreationlocators.evaluation_scheduleOpening);
			IsElementPresent(tendercreationlocators.evaluation_approverOrEvaluatorRequired);
			waitForObj(2000);
			pdfResultReport.addStepDetails("evaluationSettingPageValidation",
					"evaluation settings page must be validate successfully",
					"Successfully validated evaluation setting page" + " ", "Pass", "Y");
			log.info("completed executing the method:: evaluationSettingPageValidation");
		} catch (Exception e) {
			log.fatal("Not able to validate evaluation setting page" + e.getMessage());
			pdfResultReport.addStepDetails("evaluationSettingPageValidation",
					"evaluation settings page must be validate successfully",
					"Unable to validate evaluation setting page " + e.getMessage(), "Fail", "N");
		}
	}

	public void evaluationSettingPageValidationforBidPart1() throws Throwable {
		try {
			log.info("started executing the method:: evaluationSettingPageValidation");
			IsElementPresent(tendercreationlocators.evaluationStatus);
			IsElementPresent(tendercreationlocators.tenderId);
			IsElementPresent(tendercreationlocators.numberOfLiveBids);
			IsElementPresent(tendercreationlocators.openAndEvaluateBid);
			IsElementPresent(tendercreationlocators.scheduleOpening);
			IsElementPresent(tendercreationlocators.approverOrEvaluatorRequired);
			waitForObj(2000);
			pdfResultReport.addStepDetails("evaluationSettingPageValidation",
					"evaluation settings page must be validate successfully",
					"Successfully validated evaluation setting page" + " ", "Pass", "Y");
			log.info("completed executing the method:: evaluationSettingPageValidation");
		} catch (Exception e) {
			log.fatal("Not able to validate evaluation setting page" + e.getMessage());
			pdfResultReport.addStepDetails("evaluationSettingPageValidation",
					"evaluation settings page must be validate successfully",
					"Unable to validate evaluation setting page " + e.getMessage(), "Fail", "N");
		}
	}

	public void evaluationSettingPageValidationforBidPart2() throws Throwable {
		try {
			log.info("started executing the method:: evaluationSettingPageValidation");
			IsElementPresent(tendercreationlocators.evaluationStatus2);
			IsElementPresent(tendercreationlocators.tenderId);
			IsElementPresent(tendercreationlocators.numberOfLiveBids);
			IsElementPresent(tendercreationlocators.openAndEvaluateBid);
			IsElementPresent(tendercreationlocators.scheduleOpening);
			IsElementPresent(tendercreationlocators.approverOrEvaluatorRequired);
			waitForObj(2000);
			pdfResultReport.addStepDetails("evaluationSettingPageValidation",
					"evaluation settings page must be validate successfully",
					"Successfully validated evaluation setting page" + " ", "Pass", "Y");
			log.info("completed executing the method:: evaluationSettingPageValidation");
		} catch (Exception e) {
			log.fatal("Not able to validate evaluation setting page" + e.getMessage());
			pdfResultReport.addStepDetails("evaluationSettingPageValidation",
					"evaluation settings page must be validate successfully",
					"Unable to validate evaluation setting page " + e.getMessage(), "Fail", "N");
		}
	}

	public void checktenderStageIsInPendingforEvaluationApprovalCover1Stage() throws Throwable {
		try {
			log.info("started executing the method:: checktenderStageIsInPendingforEvaluationApprovalCover1Stage");

			WebDriverWait wait = new WebDriverWait(ThreadLocalWebdriver.getDriver(), 7000);
			wait.until(
					ExpectedConditions.visibilityOfElementLocated(tendercreationlocators.pendingForEvaluationApproval));

			IsElementPresent(tendercreationlocators.pendingForEvaluationApproval);

			pdfResultReport.addStepDetails("checktenderStageIsInPendingforEvaluationApprovalCover1Stage",
					"Tender stage must be shown as PendingforEvaluationApprovalCover1",
					"Successfully shown Tender stage as PendingforEvaluationApprovalCover1" + " ", "Pass", "Y");
			log.info("completed executing the method:: tenderStage_PendingforEvaluationApprovalCover1");
		} catch (Exception e) {
			log.fatal("Unable to show the stage as PendingforEvaluationApprovalCover1" + e.getMessage());
			pdfResultReport.addStepDetails("checktenderStageIsInPendingforEvaluationApprovalCover1Stage",
					"Tender stage must be shown as PendingforEvaluationApprovalCover1",
					"Unable to show tender stage as PendingforEvaluationApprovalCover1" + e.getMessage(), "Fail", "N");
		}
	}

	public void pendingForEvaluationApprovalStage() throws Throwable {
		try {
			log.info("started executing the method:: pendingForEvaluationApprovalStage");
			click(tendercreationlocators.pendingForEvaluationApproval, "pendingForEvaluationApproval");
			waitForObj(5000);
			pdfResultReport.addStepDetails("pendingForEvaluationApprovalStage",
					"pendingForEvaluationApprovalStage must be clicked successfully",
					"Successfully clicked on pendingForEvaluationApprovalStage" + " ", "Pass", "Y");
			log.info("completed executing the method:: pendingForEvaluationApprovalStage");
		} catch (Exception e) {
			log.fatal("Not able to click on pendingForEvaluationApprovalStage" + e.getMessage());
			pdfResultReport.addStepDetails("pendingForEvaluationApprovalStage",
					"pendingForEvaluationApprovalStage must be clicked successfully",
					"Unable to click on pendingForEvaluationApprovalStage " + e.getMessage(), "Fail", "N");
		}
	}

	public void bidDetailsPageValidation() throws Throwable {
		try {
			log.info("started executing the method:: bidDetailsPageValidation");
			waitForObj(5000);
			IsElementPresent(tendercreationlocators.bidDetailstenderId);
			IsElementPresent(tendercreationlocators.bidDetailstenderStatus);
			IsElementPresent(tendercreationlocators.TotalNoOfSubmittedBid);
			IsElementPresent(tendercreationlocators.noOfEligibleBid);
			pdfResultReport.addStepDetails("bidDetailsPageValidation", "bid details page must be validate successfully",
					"Successfully validated bid details page" + " ", "Pass", "Y");
			log.info("completed executing the method:: bidDetailsPageValidation");
		} catch (Exception e) {
			log.fatal("Not able validate bid details page" + e.getMessage());
			pdfResultReport.addStepDetails("bidDetailsPageValidation", "bid details page must be validate successfully",
					"Unable to validate bid details page " + e.getMessage(), "Fail", "N");
		}
	}

	public void bidderDetailsValidation() throws Throwable {
		try {
			log.info("started executing the method:: bidderDetailsValidation");
			IsElementPresent(tendercreationlocators.bidderName);
			IsElementPresent(tendercreationlocators.bidderCompany);
			IsElementPresent(tendercreationlocators.bidder_bidSubmissionDate);
			IsElementPresent(tendercreationlocators.bidder_decryptionStatus);
			IsElementPresent(tendercreationlocators.bidder_status);
			IsElementPresent(tendercreationlocators.bidder_approveOrReject);
			IsElementPresent(tendercreationlocators.bidder_overallComment);
			IsElementPresent(tendercreationlocators.bidder_attachment);
			IsElementPresent(tendercreationlocators.bidder_actiondropdown);
			pdfResultReport.addStepDetails("bidderDetailsValidation", "bidder details must be validate successfully",
					"Successfully validated bidder details" + " ", "Pass", "Y");
			log.info("completed executing the method:: bidderDetailsValidation");
		} catch (Exception e) {
			log.fatal("Not able validate bidder details" + e.getMessage());
			pdfResultReport.addStepDetails("bidderDetailsValidation", "bidder details must be validate successfully",
					"Unable to validate bidder details" + e.getMessage(), "Fail", "N");
		}
	}

	public void providingOverallComment() throws Throwable {
		try {
			log.info("started executing the method:: providingOverallComment");
			set(tendercreationlocators.overallComment_currentPart, pdfResultReport.testData.get("overAllComment"),
					"overallComment_currentPart");
			pdfResultReport.addStepDetails("providingOverallComment", "OverallComment must be entered successfully",
					"Successfully entered OverallComment" + " ", "Pass", "Y");
			log.info("completed executing the method:: providingOverallComment");
		} catch (Exception e) {
			log.fatal("Not able to enter OverallComment" + e.getMessage());
			pdfResultReport.addStepDetails("providingOverallComment", "OverallComment must be entered successfully",
					"Unable to enter OverallComment" + e.getMessage(), "Fail", "N");
		}
	}

	public void navigateToBidDetailsSection() throws Throwable {
		try {
			log.info("started executing the method:: navigateToBidDetailsSection");
			click(tendercreationlocators.bidder_actiondropdown, "bidder_actiondropdown");
			click(tendercreationlocators.bidDetails, "bidDetails");
			waitForObj(5000);
			pdfResultReport.addStepDetails("navigateToBidDetailsSection",
					"BidDetails Section must be navigated successfully",
					"Successfully navigated to BidDetails Section" + " ", "Pass", "Y");
			log.info("completed executing the method:: navigateToBidDetailsSection");
		} catch (Exception e) {
			log.fatal("Not able to navigate To BidDetails Section" + e.getMessage());
			pdfResultReport.addStepDetails("navigateToBidDetailsSection",
					"BidDetails Section must be navigated successfully",
					"Unable to navigate To BidDetails Section" + e.getMessage(), "Fail", "N");
		}
	}

	public void navigateToBidDetailsEachAndEveryTab() throws Throwable {
		try {
			log.info("started executing the method:: navigateToBidDetailsEachAndEveryTab");
			click(tendercreationlocators.technical, "technical");
			waitForObj(5000);
			click(tendercreationlocators.termsAndCondition_bidDetails, "termsAndCondition_bidDetails");
			waitForObj(5000);
			click(tendercreationlocators.generalInformation_bidDetails, "generalInformation_bidDetails");
			waitForObj(5000);
			click(tendercreationlocators.attachments_bidDetails, "attachments_bidDetails");
			waitForObj(5000);
			click(tendercreationlocators.next, "next");
			waitForObj(5000);
			click(tendercreationlocators.commercial_bidDetails, "commercial_bidDetails");
			waitForObj(5000);
			click(tendercreationlocators.quotationSummary_bidDetails, "quotationSummary_bidDetails");
			waitForObj(5000);
			click(tendercreationlocators.close_bidDetails, "close_bidDetails");
			waitForObj(5000);
			pdfResultReport.addStepDetails("navigateToBidDetailsEachAndEveryTab",
					"BidDetails each and every tab must be navigated successfully",
					"Successfully navigated to BidDetails each and every tab" + " ", "Pass", "Y");
			log.info("completed executing the method:: navigateToBidDetailsEachAndEveryTab");
		} catch (Exception e) {
			log.fatal("Not able to navigate To BidDetails each and every tab" + e.getMessage());
			pdfResultReport.addStepDetails("navigateToBidDetailsEachAndEveryTab",
					"BidDetails each and every tab must be navigated successfully",
					"Unable to navigate To BidDetails each and every tab" + e.getMessage(), "Fail", "N");
		}
	}

	public void approveOrRejectValidations() throws Throwable {
		try {
			log.info("started executing the method:: approveOrRejectValidations");
			WebElement saveButton = ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.save);
			boolean paramBoolean = true;
			click(tendercreationlocators.approveOrRejectEditableLink, "approveOrRejectEditableLink");
			click(tendercreationlocators.rejectRadioButton, "rejectRadioButton");
			verifyElementIsEnabled(saveButton, paramBoolean);
			set(tendercreationlocators.commentText, pdfResultReport.testData.get("overAllComment"), "commentText");
			verifyElementIsEnabled(saveButton, paramBoolean);
			click(tendercreationlocators.approveRadioButton, "approveRadioButton");
			set(tendercreationlocators.upload_editable,
					System.getProperty("user.dir") + "\\MediaFiles\\rfqCreation1.xlsx", "fileName");
			set(tendercreationlocators.commentText, pdfResultReport.testData.get("overAllComment"), "commentText");
			click(tendercreationlocators.save, "save");
			waitForObj(5000);
			IsElementPresent(tendercreationlocators.alertPopUp);
			click(tendercreationlocators.alertCloseButton, "alertCloseButton");
			waitForObj(5000);
			IsElementPresent(tendercreationlocators.bidderStatusApprove);
			IsElementPresent(tendercreationlocators.bidderOverAllComment);
			// IsElementPresent(tendercreationlocators.bidderAttachment);
			click(tendercreationlocators.submitbutton1, "submitbutton1");
			waitForObj(10000);

		} catch (Exception e) {
			log.fatal("Not able to verify the bid details" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to verify the bid details",
					"Not able to verify the bid details using approveOrRejectValidations",
					"Unable to verify the bid details using approveOrRejectValidations" + e.getMessage(), "Fail", "N");
		}
	}

	public void bidDetailsectionForEditableTab() throws Throwable {
		try {
			log.info("started executing the method:: bidDetailsectionForEditableTab");
			click(tendercreationlocators.technical, "technical");
			waitForObj(5000);

			IsElementPresent(tendercreationlocators.technical);
			IsElementPresent(tendercreationlocators.technical_Clause);
			IsElementPresent(tendercreationlocators.technical_Details);
			IsElementPresent(tendercreationlocators.technical_Attachment);
			IsElementPresent(tendercreationlocators.technical_Complaince);
			IsElementPresent(tendercreationlocators.technical_AttachFile);
			IsElementPresent(tendercreationlocators.technical_Remarks);
			IsElementPresent(tendercreationlocators.technical_EvaluatorRemarks);
			click(tendercreationlocators.termsNConditions, "termsNConditions");
			waitForObj(5000);
			IsElementPresent(tendercreationlocators.tnc_Clause);
			IsElementPresent(tendercreationlocators.tnc_Details);
			// IsElementPresent(tendercreationlocators.tnc_Attachment);
			IsElementPresent(tendercreationlocators.tnc_Complaince);
			// IsElementPresent(tendercreationlocators.tnc_AttachFile);
			IsElementPresent(tendercreationlocators.tnc_Remarks);
			IsElementPresent(tendercreationlocators.tnc_EvaluatorRemarks);
			click(tendercreationlocators.generalInformationTab, "generalInformationTab");
			waitForObj(5000);
			IsElementPresent(tendercreationlocators.tenderIdBidDetails);
			IsElementPresent(tendercreationlocators.tenderDescription);
			IsElementPresent(tendercreationlocators.proableAmountOfContract);
			IsElementPresent(tendercreationlocators.tenderCategory);
			IsElementPresent(tendercreationlocators.referenceCode);
			IsElementPresent(tendercreationlocators.quotationCurrency);
			click(tendercreationlocators.attachementsTab, "attachementsTab");
			waitForObj(5000);
			IsElementPresent(tendercreationlocators.bidderAttachment1);
			IsElementPresent(tendercreationlocators.bidSpecificAttachment);
			IsElementPresent(tendercreationlocators.tenderAttachment);
			click(tendercreationlocators.close, "close");
			waitForObj(5000);
			pdfResultReport.addStepDetails("bidDetailsectionForEditableTab",
					"Must verify the Each and every tab in Bid detail section",
					"Sucessfully verified bid details section each and every tab" + " ", "Pass", "Y");
			log.info("completed executing the method:: bidDetailsectionForEditableTab");
		} catch (Exception e) {
			log.fatal("Not able to verify the bid details" + e.getMessage());
			pdfResultReport.addStepDetails("bidDetailsectionForEditableTab",
					"Must verify the Each and every tab in Bid detail section",
					"Unable to verified bid details section each and every tab" + e.getMessage(), "Fail", "N");
		}
	}

	public void checktenderStatusIsIncompletedState() throws Throwable {
		try {
			log.info("started executing the method:: checktenderStatusIsIncompletedState");
			
			click(tendercreationlocators.completedtablnk, "completedtablnk");
			waitForObj(3000);
			waitForElementToBeVisible(tendercreationlocators.tenderStatus_completed);
			IsElementPresent(tendercreationlocators.tenderStatus_completed);
			pdfResultReport.addStepDetails("checktenderStatusIsIncompltedState",
					"Tender status must be shown as completed", "Successfully shown Tender status as completed" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: checktenderStatusIsIncompletedState");
		} catch (Exception e) {
			log.fatal("Unable to show the status as completed" + e.getMessage());
			pdfResultReport.addStepDetails("checktenderStatusIsIncompleteddState",
					"Tender status must be shown as completed",
					"Unable to show tender status as completed" + e.getMessage(), "Fail", "N");
		}
	}

	public void checktenderStageIsIncompletedStage() throws Throwable {
		try {
			log.info("started executing the method:: checktenderStatusIsIncompletedState");

			WebDriverWait wait = new WebDriverWait(ThreadLocalWebdriver.getDriver(), 7000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(tendercreationlocators.tenderStage_completed));

			IsElementPresent(tendercreationlocators.tenderStage_completed);

			pdfResultReport.addStepDetails("checktenderStageIsIncompltedState",
					"Tender stage must be shown as completed", "Successfully shown Tender stage as completed" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: checktenderStatusIsIncompletedState");
		} catch (Exception e) {
			log.fatal("Unable to show the stage as completed" + e.getMessage());
			pdfResultReport.addStepDetails("checktenderStageIsIncompleteddState",
					"Tender stage must be shown as completed",
					"Unable to show tender stage as completed" + e.getMessage(), "Fail", "N");
		}
	}

	public static String getDataFromPropertiesFile() throws IOException {
		FileInputStream fileReader = null;
		Properties properties = null;
		String propertyValue;
		try {
			final String filePath = System.getProperty("user.dir")
					+ "//src//main//java//com//DataProperties//GeneralInfo.properties";

			fileReader = new FileInputStream(filePath);

			properties = new Properties();
			properties.load(fileReader);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		propertyValue = properties.getProperty("tenderId");

		fileReader.close();

		return propertyValue;

	}

	public void updateDataIntoPropertyFile(String value) throws IOException {
		FileOutputStream fileWriter = null;

		Properties properties = null;

		try {
			final String filePath = System.getProperty("user.dir")
					+ "//src//main//java//com//DataProperties//GeneralInfo.properties";

			properties = new Properties();

			FileInputStream fis = new FileInputStream(filePath);

			properties.load(fis);

			fis.close();

			fileWriter = new FileOutputStream(filePath);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// properties.replace("tenderId", value);
		properties.replace("tenderId", properties.get("tenderId"), value);

		try {
			properties.store(fileWriter, null);
		} catch (IOException e) {
			e.printStackTrace();
		}

		fileWriter.close();
	}

	public static String getDataFromPropertiesFile(String value) throws IOException {
		FileInputStream fileReader = null;
		Properties properties = null;
		String propertyValue;
		try {
			final String filePath = System.getProperty("user.dir")
					+ "//src//main//java//com//DataProperties//GeneralInfo.properties";

			fileReader = new FileInputStream(filePath);

			properties = new Properties();
			properties.load(fileReader);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		propertyValue = properties.getProperty(value);

		fileReader.close();

		return propertyValue;

	}

	public static String updateDataIntoPropertyFile(String key, String value) throws IOException {
		FileOutputStream fileWriter = null;

		Properties properties = null;

		try {
			final String filePath = System.getProperty("user.dir")
					+ "//src//main//java//com//DataProperties//GeneralInfo.properties";

			properties = new Properties();

			FileInputStream fis = new FileInputStream(filePath);

			properties.load(fis);

			fis.close();

			fileWriter = new FileOutputStream(filePath);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// properties.replace("tenderId", value);
		properties.replace(key, properties.get(key), value);

		try {
			properties.store(fileWriter, null);
		} catch (IOException e) {
			e.printStackTrace();
		}

		fileWriter.close();
		return value;
	}

	public void checktenderStatusAndTenderStageAfterCompletion() throws Throwable {
		try {
			log.info("started executing the method:: checktenderStatusAndTenderStageAfterCompletion");
			IsElementPresent(tendercreationlocators.evaluationTenderStatus_Completd);
			waitForObj(3000);
			IsElementPresent(tendercreationlocators.evaluationTenderStage_completd);
			waitForObj(10000);
			pdfResultReport.addStepDetails("checktenderStatusAndTenderStageAfterCompletion",
					"Tender status must be shown as Evaluation", "Successfully shown Tender status as Evaluation" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: checktenderStatusAndTenderStageAfterCompletion");
		} catch (Exception e) {
			log.fatal("Unable to show the status as completed" + e.getMessage());
			pdfResultReport.addStepDetails("checktenderStatusAndTenderStageAfterCompletion",
					"Tender status must be shown as Evaluation",
					"Unable to show tender status as Evaluation" + e.getMessage(), "Fail", "N");
		}
	}

	/**
	 * @author venkatesh jujjuru
	 * @throws Throwable
	 */
	public void checktenderStatusIsInclosedState() throws Throwable {
		try {
			log.info("started executing the method:: checktenderStatusIsInclosedState");

			String tenderstatusclosed = ThreadLocalWebdriver.getDriver()
					.findElement(By.xpath("//*[@id='myTablebyrTl00']/tbody/tr/td[7]/span")).getText();

			while (true) {
				if (!tenderstatusclosed.equalsIgnoreCase("Closed")) {

					/*
					 * ThreadLocalWebdriver.getDriver().navigate().refresh();
					 * 
					 * ThreadLocalWebdriver.getDriver().manage().timeouts().
					 * pageLoadTimeout(15, TimeUnit.SECONDS);
					 */

					navigateToTenderListing();

					enterTenderIdInSearch();

					tenderstatusclosed = ThreadLocalWebdriver.getDriver()
							.findElement(By.xpath("//*[@id='myTablebyrTl00']/tbody/tr/td[7]/span")).getText();

					System.out.println(" wait for tender status to be closed state");

				} else {

					IsElementPresent(tendercreationlocators.tenderStatus_closed);

					System.out.println("tender status is in closed state");

					pdfResultReport.addStepDetails("checktenderStatusIsInclosedState",
							"Tender status must be shown as closed", "Successfully shown Tender status as closed" + " ",
							"Pass", "Y");
					log.info("completed executing the method:: tenderStatus_closed");

					break;
				}
			}

		} catch (Exception e) {
			log.fatal("Unable to show the status as closed" + e.getMessage());
			pdfResultReport.addStepDetails("checktenderStatusIsInclosedState", "Tender status must be shown as closed",
					"Unable to show tender status as closed" + e.getMessage(), "Fail", "N");
		}
	}

	/**
	 * @author venkatesh jujjuru
	 * @throws Throwable
	 */

	public void ValidateEvaluationSettingPageFieldElements() throws Throwable {
		try {
			log.info("started executing the method:: ValidateEvaluationSettingPageFieldElements");

			IsElementPresent(tendercreationlocators.EvaluationStatus_Evaluation);

			IsElementPresent(tendercreationlocators.tenderId_Evaluation);
			IsElementPresent(tendercreationlocators.noOfLiveBids_Evaluation);
			IsElementPresent(tendercreationlocators.openAndEvaluateBid_Evaluation);
			IsElementPresent(tendercreationlocators.scheduleOpening_Evaluation);
			pdfResultReport.addStepDetails("ValidateEvaluationSettingPageFieldElements",
					"evaluation settings page must be validate successfully",
					"Successfully validated evaluation setting page" + " ", "Pass", "Y");
			log.info("completed executing the method:: evaluationSettingPageValidation");
		} catch (Exception e) {
			log.fatal("Not able validate evaluation setting page" + e.getMessage());
			pdfResultReport.addStepDetails("ValidateEvaluationSettingPageFieldElements",
					"evaluation settings page must be validate successfully",
					"Unable to validate evaluation setting page " + e.getMessage(), "Fail", "N");
		}
	}

	public void ValidateEvaluationSettingPageFieldElements(String cover__text, String liveBids) throws Throwable {
		try {
			log.info("started executing the method:: ValidateEvaluationSettingPageFieldElements");

			waitForObj(7000);

			String opening_Approval = text(By.xpath("(//div[@class='alert alert-info text-center']//div//strong)[1]"))
					.trim();

			String covernumber = opening_Approval.substring(opening_Approval.lastIndexOf('(') + 1,
					opening_Approval.lastIndexOf(')'));

			Assert.assertTrue(covernumber.trim().equalsIgnoreCase(cover__text),
					"PENDING FOR OPENING APPROVAL" + cover__text + " is verified");

			String Tender_Id = text(By.xpath("(//div[@class='alert alert-info text-center']//div//strong)[2]")).trim();

			Assert.assertTrue(Tender_Id.split(":")[1].trim().equals(tenderReferenceNoLocatorText),
					"tender id :" + tenderReferenceNoLocatorText + " is verified");

			String No_of_Live_Bids = text(By.xpath("(//div[@class='alert alert-info text-center']//div//strong)[3]"))
					.trim();

			Assert.assertTrue(No_of_Live_Bids.split(":")[1].trim().equals(liveBids),
					"No_of_Live_Bids :" + liveBids + "is  verified");

			IsElementPresent(tendercreationlocators.evaluation_openAndEvaluationBid);

			IsElementPresent(tendercreationlocators.evaluation_scheduleOpening);

			IsElementPresent(tendercreationlocators.evaluation_approverOrEvaluatorRequired);

			pdfResultReport.addStepDetails("ValidateEvaluationSettingPageFieldElements",
					"evaluation settings page must be validate successfully",
					"Successfully validated evaluation setting page" + " ", "Pass", "Y");
			log.info("completed executing the method:: evaluationSettingPageValidation");
		} catch (Exception e) {
			log.fatal("Not able validate evaluation setting page" + e.getMessage());
			pdfResultReport.addStepDetails("ValidateEvaluationSettingPageFieldElements",
					"evaluation settings page must be validate successfully",
					"Unable to validate evaluation setting page " + e.getMessage(), "Fail", "N");
		}
	}

	/**
	 * @author venkatesh jujjuru
	 * @throws Exception
	 */
	public void selectYesForApprovalAndEvaluationRequired() throws Exception {
		try {
			log.info("started executing the method:: selectYesForApprovalAndEvaluationRequired");

			click(tendercreationlocators.ApproverSelectYES_Evaluation, "ApproverSelectYES_Evaluation");

			pdfResultReport.addStepDetails("selectYesForApprovalAndEvaluationRequired",
					"should select Yes for approval and evaluation required ",
					"Successfully selected Yes for approval and evaluation required " + " ", "Pass", "Y");
			log.info("completed executing the method:: selectYesForApprovalAndEvaluationRequired");
		} catch (Exception e) {
			log.fatal("Unable to show Tender status as evaluation" + e.getMessage());
			pdfResultReport.addStepDetails("selectYesForApprovalAndEvaluationRequired",
					"should select Yes for approval and evaluation required",
					"Unable to selected Yes for approval and evaluation required" + e.getMessage(), "Fail", "N");
		}
	}

	/**
	 * @author venkatesh jujjuru
	 * @throws Exception
	 */
	public void selectBidOpeningAndProvideCommentsForBidOpeningApproval() throws Exception {
		try {
			log.info("started executing the method:: selectBidOpeningAndProvideCommentsForBidOpeningApproval");

			click(tendercreationlocators.bidOpenBy, "bidOpenBy");
			waitForObj(5000);
			click(tendercreationlocators.Workflowtype_Evaluation, "Workflowtype_Evaluation");
			waitForElementToBeVisible(tendercreationlocators.AddApprover_Evaluation);
			click(tendercreationlocators.AddApprover_Evaluation, "AddApprover_Evaluation");
			waitForObj(1000);
			set(tendercreationlocators.OpeningUser1_Evaluation, pdfResultReport.testData.get("TenderOpening_Approver1_name"), "OpeningUser1_Evaluation");
			waitForObj(2000);
			select(tendercreationlocators.OpeningApprovalType1_Evaluation, pdfResultReport.testData.get("TenderOpening_ApprovalType1"));
			set(tendercreationlocators.commentSectionBy, "Sending for approval to Approval user", "commentSectionBy");

			pdfResultReport.addStepDetails("selectBidOpeningAndProvideCommentsForBidOpeningApproval",
					"should select BidOpening And Provide Comments For BidOpening Approval",
					"Successfully selected BidOpening And Provide Comments For BidOpening Approval" + " ", "Pass", "Y");
			log.info("completed executing the method:: selectYesForApprovalAndEvaluationRequired");
		} catch (Exception e) {
			log.fatal("Unable to select BidOpening And Provide Comments For BidOpening Approval" + e.getMessage());
			pdfResultReport.addStepDetails("selectBidOpeningAndProvideCommentsForBidOpeningApproval",
					"should select BidOpening And Provide Comments For BidOpenin gApproval",
					"Unable to select BidOpening And Provide Comments For BidOpening Approval" + e.getMessage(), "Fail",
					"N");
		}

	}
	public void selectBidOpeningAndProvideCommentsForBidOpeningApproval_cover2() throws Exception {
		try {
			log.info("started executing the method:: selectBidOpeningAndProvideCommentsForBidOpeningApproval_cover2");

			waitForObj(2000);
			set(tendercreationlocators.commentSectionBy, "Sending for approval to Approval user", "commentSectionBy");

			pdfResultReport.addStepDetails("selectBidOpeningAndProvideCommentsForBidOpeningApproval_cover2",
					"should select BidOpening And Provide Comments For BidOpening Approval",
					"Successfully selected BidOpening And Provide Comments For BidOpening Approval" + " ", "Pass", "Y");
			log.info("completed executing the method:: selectBidOpeningAndProvideCommentsForBidOpeningApproval_cover2");
		} catch (Exception e) {
			log.fatal("Unable to select BidOpening And Provide Comments For BidOpening Approval" + e.getMessage());
			pdfResultReport.addStepDetails("selectBidOpeningAndProvideCommentsForBidOpeningApproval_cover2",
					"should select BidOpening And Provide Comments For BidOpenin gApproval",
					"Unable to select BidOpening And Provide Comments For BidOpening Approval" + e.getMessage(), "Fail",
					"N");
		}

	}
	public void selectBidEvaluationAndProvideCommentsForBidEvaluationApproval_cover2() throws Exception {
		try {
			log.info("started executing the method:: selectBidEvaluationAndProvideCommentsForBidEvaluationApproval_cover2");

			waitForObj(2000);
			set(tendercreationlocators.EvalcommentSection_Evaluation, "Sending for approval to Approval user", "EvalcommentSection_Evaluation");

			pdfResultReport.addStepDetails("selectBidEvaluationAndProvideCommentsForBidEvaluationApproval_cover2",
					"should select BidEvaluation And Provide Comments For BidEvaluation Approval",
					"Successfully selected BidEvaluation And Provide Comments For BidEvaluation Approval" + " ", "Pass", "Y");
			log.info("completed executing the method:: selectBidEvaluationAndProvideCommentsForBidEvaluationApproval_cover2");
		} catch (Exception e) {
			log.fatal("Unable to select BidEvaluation And Provide Comments For BidEvaluation Approval" + e.getMessage());
			pdfResultReport.addStepDetails("selectBidEvaluationAndProvideCommentsForBidEvaluationApproval_cover2",
					"should select BidEvaluation And Provide Comments For BidEvaluation Approval",
					"Unable to select BidEvaluation And Provide Comments For BidEvaluation Approval" + e.getMessage(), "Fail",
					"N");
		}

	}
	/**
	 * @author venkatesh jujjuru
	 * @throws Exception
	 */
	public void selectEvaluationAndProvideCommentsForBidOpeningApproval() throws Exception {
		try {
			log.info("started executing the method:: selectEvaluationAndProvideCommentsForBidOpeningApproval");
			
			//scrollToElement(tendercreationlocators.bidValBy);
			scrollToTopOfThePage();
			click(tendercreationlocators.bidValBy, "bidValBy");
			waitForObj(5000);
			click(tendercreationlocators.BidEvaluationtab_Evaluation, "BidEvaluationtab_Evaluation");
			click(tendercreationlocators.WorkflowtypeEvalapp_Evaluation, "WorkflowtypeEvalapp_Evaluation");
			waitForElementToBeVisible(tendercreationlocators.AddApproverEvalapp_Evaluation);
			click(tendercreationlocators.AddApproverEvalapp_Evaluation, "AddApproverEvalapp_Evaluation");
			waitForObj(1000);
			set(tendercreationlocators.EvalUser1_Evaluation, pdfResultReport.testData.get("TenderEvaluation_Approver1_name"), "EvalUser1_Evaluation");
			waitForObj(2000);
			select(tendercreationlocators.EvalApprovalType1_Evaluation, pdfResultReport.testData.get("TenderEvaluation_ApprovalType1"));
			set(tendercreationlocators.EvalcommentSection_Evaluation, "Sending for approval to Approval user", "EvalcommentSection_Evaluation");

			pdfResultReport.addStepDetails("selectEvaluationAndProvideCommentsForBidOpeningApproval",
					"should select BidEvaluation And Provide Comments For BidEvaluation Approval",
					"Successfully selected BidEvaluation And Provide Comments For BidEvaluationApproval" + " ", "Pass",
					"Y");
			log.info("completed executing the method:: selectEvaluationAndProvideCommentsForBidOpeningApproval");
		} catch (Exception e) {
			log.fatal("Unable to select BidEvaluation And Provide Comments For BidOpening Approval" + e.getMessage());
			pdfResultReport.addStepDetails("selectEvaluationAndProvideCommentsForBidOpeningApproval",
					"should select BidEvaluation And Provide Comments For BidEvaluation Approval",
					"Unable to select BidEvaluation And Provide Comments For BidEvaluation Approval" + e.getMessage(),
					"Fail", "N");
		}

	}

	/**
	 * @author venkatesh jujjuru Bid opening approval page
	 * @throws Throwable
	 */
	public void validateTenderOpeningTab_ForBidOpeningUser() throws Throwable {

		String str = "//td[text()='Pending for opening Approval (Cover 1)']/preceding-sibling::td[position()=1 and text()='{0}']";
		try {
			log.info("started executing the method:: validateTenderOpeningTabDetails_ForBidOpeningUser");

			click(tendercreationlocators.workFlow, "workFlow");
			click(tendercreationlocators.pending, "pending");
			waitForObj(4000);
			set(tendercreationlocators.search, tenderReferenceNoLocatorText, "search");

			click(tendercreationlocators.tenderopeningTabBy, "tenderopeningTabBy");
			IsElementPresent(tendercreationlocators.documentId_Evaluation);

			//IsElementPresent(tendercreationlocators.OpeningApprovalWFType_Evaluation(coverNo));

			IsElementPresent(tendercreationlocators.OpeningApprovalWFStatus_Evaluation);

			pdfResultReport.addStepDetails("validateTenderOpeningTabDetails_ForBidOpeningUser",
					"Tender OpeningTab Details must be validate For BidOpening User",
					"SucessFully validated TenderOpening TabDetails ForBidOpeningUser" + " ", "Pass", "Y");
			log.info("completed executing the method:: validateTenderOpeningTabDetails_ForBidOpeningUser");
		} catch (Exception e) {
			log.fatal("Not able to validate Tender OpeningTab Details For BidOpening User" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to locate created tender",
					"Tender OpeningTab Details must be validate For BidOpening User",
					"Unable to validate Tender OpeningTab Details For BidOpening User" + e.getMessage(), "Fail", "N");
		}
	}

	/**
	 * 
	 * @throws Exception
	 */
	public void clickDetailsLink() throws Exception {
		try {
			log.info("started executing the method:: clickDetailsLink");
			click(tendercreationlocators.tenderOpeningTabDetailsLinkBy, "tenderOpeningTabDetailsLinkBy");
			waitForObj(10000);
			pdfResultReport.addStepDetails("clickDetailsLink", "Detail link must be clicked sucessfully",
					"SucessFully clicked Detail link" + " ", "Pass", "Y");
			log.info("completed executing the method:: validateTenderOpeningTabDetails_ForBidOpeningUser");
		} catch (Exception e) {
			log.fatal("Not able to clicked Detail link" + e.getMessage());
			pdfResultReport.addStepDetails("clickDetailsLink", "Detail link must be clicked sucessfully",
					"Unable to clicked Detail link" + e.getMessage(), "Fail", "N");
		}
	}

	/**
	 * Bid opening Approval page
	 * 
	 * @author venkatesh jujjuru
	 * @param cover1
	 * @param no_ofBids
	 * @param no_of_eligible_bids
	 * @throws Exception
	 */
	public void validateBidOpeningApprovalpage(String cover1, String no_ofBids, String no_of_eligible_bids)
			throws Exception {
		try {

			log.info("started executing the method:: validateBidOpeningApprovalpage");
			String Tenderidtext = text(By.xpath("(//div[@class='alert alert-info text-center']/strong)[1]")).trim();
			System.out.println("Tender id Text:" + Tenderidtext);
			//Assert.assertTrue(Tenderidtext.split(":")[1].trim().equals(tenderReferenceNoLocatorText),
			//"tender id is verified");
			if(Tenderidtext.split(":")[1].trim().equals(tenderReferenceNoLocatorText))
			{
				System.out.println("Tender id is verified");
			}
			else
			{
				pdfResultReport.addStepDetails("validateBidOpeningApprovalpage","validate BidOpening Approvalpage : Tender id should be verified",
						"Tender id not matched in the bid opening approval page","Fail", "N");
			}
			

			String CoverApprovalText = text(By.xpath("(//div[@class='alert alert-info text-center']/strong)[2]"));
			System.out.println("CoverApprovalText:" + CoverApprovalText);
			String cover = CoverApprovalText
					.substring(CoverApprovalText.lastIndexOf('(') + 1, CoverApprovalText.lastIndexOf(')')).trim();
			System.out.println("Cover:" + cover);
			//Assert.assertTrue(cover.trim().equalsIgnoreCase(cover1), "PENDING FOR OPENING APPROVAL is verified");
			
			if(cover.trim().equalsIgnoreCase(cover1))
			{
				System.out.println("Cover No is verified");
			}
			else
			{
				pdfResultReport.addStepDetails("validateBidOpeningApprovalpage","validate BidOpening Approvalpage : Cover No should be verified",
						"Cover No not matched in the bid opening approval page","Fail", "N");
			}

			String TotalNo_OfBids = text(By.xpath("//*[contains(text(), 'Total No of Submitted Bid :')] /a"));
			System.out.println("TotalNo_OfBids:" + TotalNo_OfBids);
			//Assert.assertTrue(TotalNo_OfBids.trim().equals(no_ofBids), "Total No of Submitted Bid : verified");
			if(TotalNo_OfBids.trim().equals(no_ofBids))
			{
				System.out.println("Total No of bid is verified");
			}
			else
			{
				pdfResultReport.addStepDetails("validateBidOpeningApprovalpage","validate BidOpening Approvalpage : Total No of bid should be verified",
						"Total No of bid not matched in the bid opening approval page","Fail", "N");
			}

			String Eligible_Bid = text(By
					.xpath("//*[contains(text(), 'Total No of Submitted Bid :')]/parent::div/parent::div/parent::div/parent::div/h3"))
							.trim();
			System.out.println("Eligible_Bid:" + Eligible_Bid);
			//Assert.assertTrue(Eligible_Bid.split(":")[1].trim().equals(no_of_eligible_bids), "nof eligible bids");
			if(Eligible_Bid.split(":")[1].trim().equals(no_of_eligible_bids))
			{
				System.out.println("Total No of Eligible bid is verified");
			}
			else
			{
				pdfResultReport.addStepDetails("validateBidOpeningApprovalpage","validate BidOpening Approvalpage : Total No of eligible bid should be verified",
						"Total No of eligible bid not matched in the bid opening approval page","Fail", "N");
			}

			//Assert.assertFalse(ThreadLocalWebdriver.getDriver()
					//.findElement(By
							//.xpath("//label[normalize-space(text())='Initiator Comments']/following-sibling::textarea"))
					//.isEnabled(), "Initiator Comments Is in disabled mode");

			String approvalComment = ThreadLocalWebdriver.getDriver()
					.findElement(By.xpath("//label[text()='Approver Comment']//following-sibling::textarea"))
					.getAttribute("class").trim();
			//Assert.assertTrue(approvalComment.contains("ng-empty"), "approval comment is editable");
			if(approvalComment.contains("ng-empty"))
			{
				System.out.println("approval comment text area is editable");
			}
			else
			{
				pdfResultReport.addStepDetails("validateBidOpeningApprovalpage","validate BidOpening Approvalpage : approval comment text area should be editable",
						"approval comment text area is editable","Fail", "N");
			}

			IsElementPresent(By.xpath("//button[@data-original-title='Review']"));

			pdfResultReport.addStepDetails("validateBidOpeningApprovalpage",
					"validate BidOpening Approvalpage Should be done sucessfully",
					"SucessFully validated BidOpening Approvalpage" + " ", "Pass", "Y");
			log.info("completed executing the method:: validateBidOpeningApprovalpage");

		} catch (Exception e) {

			log.fatal("Not able to validate BidOpening Approvalpage sucessfully" + e.getMessage());
			pdfResultReport.addStepDetails("validateBidOpeningApprovalpage",
					"validate BidOpening Approvalpage Should be done sucessfully",
					"Unable to validate BidOpening Approvalpage sucessfully" + e.getMessage(), "Fail", "N");
		}

	}

	/**
	 * 
	 * @throws Exception
	 */
	public void provideApproverCommentInCommentsection() throws Exception {

		try {

			log.info("started executing the method:: provideApproverCommentInCommentsection");

			set(tendercreationlocators.OpeningApprovalComment_Evaluation, "approvalRequest",
					"ApprovalcommentSection");

			pdfResultReport.addStepDetails("provideApproverCommentInCommentsection",
					"provide ApproverCommentInCommentsection must be done",
					"SucessFully provided ApproverCommentInCommentsection" + " ", "Pass", "Y");
			log.info("completed executing the method:: provideApproverCommentInCommentsection");
		} catch (Exception e) {

			log.fatal("Not able to  provided ApproverCommentInCommentsection" + e.getMessage());
			pdfResultReport.addStepDetails("validateBidOpeningApprovalpage",
					"provide ApproverCommentInCommentsection must be done",
					"Unable to  provided ApproverCommentInCommentsection" + e.getMessage(), "Fail", "N");
		}

	}

	/**
	 * 
	 * @throws Throwable
	 */
	public void clickApproveBtnAndCheckTenderIsDisplayingOrNotInTenderOpeningTab() throws Throwable {
		try {

			log.info("started executing the method:: clickApproveBtnAndCheckTenderIsDisplayingOrNotInTenderOpeningTab");

			JSClick(tendercreationlocators.approveBtn, "approveBtn");
			IsElementPresent(tendercreationlocators.OpeningApprovalAlert_Evaluation);
			click(tendercreationlocators.OpeningApprovalAlertConfirmbtn_Evaluation, "OpeningApprovalAlertConfirmbtn_Evaluation");
			waitForObj(1000);
			waitForElementToBeVisible(tendercreationlocators.tenderopeningTabBy);
			click(tendercreationlocators.tenderopeningTabBy, "tenderopeningTabBy");
			waitForObj(1000);
			set(tendercreationlocators.search, tenderReferenceNoLocatorText, "search");
			waitForObj(1000);
			WebDriver driver = ThreadLocalWebdriver.getDriver();
			int size = driver.findElements(tendercreationlocators.OpeningApprovalDetailsbtn_Evaluation).size();
			waitForObj(1000);
			if(size >=1)
			{
				System.out.println("**********************************************************************");
				System.out.println("Tender is present in Opening Appproval pending List page the size is --->"+size);
				pdfResultReport.addStepDetails("clickApproveBtnAndCheckTenderIsDisplayingOrNotInTenderOpeningTab",
					"Tender should not be displayed in the Tender Opening tab after approval",
					"Tender is displayed in the Tender Opening tab after approval", "Fail", "N");
				log.info("completed executing the method:: clickApproveBtnAndCheckTenderIsDisplayingOrNotInTenderOpeningTab");
				System.out.println("**********************************************************************");
			}
			else if(size == 0)
			{
				System.out.println("**********************************************************************");
				System.out.println("The tender is Not there in Approval pending List page the size is --->"+size);
				pdfResultReport.addStepDetails("clickApproveBtnAndCheckTenderIsDisplayingOrNotInTenderOpeningTab",
					"Tender should not be displayed in the Tender Opening tab after approval",
					"Tender is not displayed in the Tender Opening tab after approval", "Pass", "Y");
				log.info("completed executing the method:: clickApproveBtnAndCheckTenderIsDisplayingOrNotInTenderOpeningTab");
				
			}
		} catch (Exception e) {
			log.fatal("Not able to clicked ApproveBtn" + e.getMessage());
			pdfResultReport.addStepDetails("clickApproveBtnAndCheckTenderIsDisplayingOrNotInTenderOpeningTab",
					"click ApproveBtnAndCheckTender InTenderOpeningTab must be done",
					"Unable to  clicked ApproveBtn " + e.getMessage(), "Fail", "N");
		}
	}

	public void validateTenderEvaluationTabDetails_WithEvaluatorUser() throws Throwable {
		try {
			log.info("started executing the method:: validateTenderEvaluationTabDetails_WithEvaluatorUser");

			click(tendercreationlocators.workFlow, "workFlow");
			click(tendercreationlocators.pending, "pending");
			waitForElementToBeVisible(tendercreationlocators.TenEvalTab_Evaluation);
			waitForObj(1000);
			set(tendercreationlocators.search, tenderReferenceNoLocatorText, "search");
			IsElementPresent(tendercreationlocators.documentId_Evaluation);
			IsElementPresent(tendercreationlocators.workFlowType_Evaluation);
			IsElementPresent(tendercreationlocators.OpeningApprovalWFStatus_Evaluation);

			pdfResultReport.addStepDetails("validateTenderEvaluationTabDetails_WithEvaluatorUser",
					"Tender Evaluation tab Details must be validated",
					"SucessFully validated Tender Evaluation TabDetails" + " ", "Pass", "Y");
			log.info("completed executing the method:: validateTenderEvaluationTabDetails_WithEvaluatorUser");
		} catch (Exception e) {
			log.fatal("Not able to Tender Evaluation TabDetails" + e.getMessage());
			pdfResultReport.addStepDetails("validateTenderEvaluationTabDetails_WithEvaluatorUser",
					"Tender Evaluation tab Details must be validated",
					"Unable to validate Tender Evaluation TabDetails" + e.getMessage(), "Fail", "N");
		}
	}

	public void validateEvaluationDetailsPage(String coverno, String no_ofBids, String liveBids, String bidderUserName,
			String bidderName) throws Exception {
		try {

			log.info("started executing the method:: validateEvaluationDetailsPage");

			String CoverApprovalText = text(By.xpath("//div[@class='alert alert-info text-center']/strong"));

			String cover = CoverApprovalText
					.substring(CoverApprovalText.lastIndexOf('(') + 1, CoverApprovalText.lastIndexOf(')')).trim();

			Assert.assertTrue(cover.trim().equalsIgnoreCase(coverno),
					"PENDING FOR APPROVAL FOR FINAL EVALUATION" + coverno + "is verified");

			String TotalBids = text(By.xpath("(//*[@id='main-content-nw']//div//h3/span)[1]"));

			Assert.assertTrue(TotalBids.split(":")[1].trim().equals("1"), "Total Bids : verified");

			String liveBidsActual = text(By.xpath("(//*[@id='main-content-nw']//div//h3/span)[2]"));

			Assert.assertTrue(liveBidsActual.split(":")[1].trim().equals("1"), "nof live bids");

			Assert.assertTrue(ThreadLocalWebdriver.getDriver()
					.findElement(
							By.xpath("//*[contains(normalize-space(text()), '" + bidderUserName.toUpperCase() + "')]"))
					.isDisplayed(), "bidderUserName is present");

			Assert.assertTrue(ThreadLocalWebdriver.getDriver()
					.findElement(By
							.xpath("//span/strong[normalize-space(text())='Pending']/parent::span/parent::td/preceding-sibling::td[text()='"
									+ bidderName.toUpperCase() + "']"))
					.isDisplayed(), "bidderName is present");

			IsElementPresent(By.xpath("//span/strong[normalize-space(text())='Pending']"));

			IsElementPresent(By.xpath(
					"//span/strong[normalize-space(text())='Pending']/../following-sibling::button[starts-with(@ng-click,'editBidderStatus')]"));

			Assert.assertTrue(
					ThreadLocalWebdriver.getDriver()
							.findElement(By.xpath("//textarea[@ng-model='comment.overallComments']"))
							.getAttribute("class").trim().contains("ng-empty"),
					"Overall comment : will be blank by default");

			Assert.assertTrue(ThreadLocalWebdriver.getDriver()
					.findElements(By.xpath("//*[text()='Note:']/../../../../preceding-sibling::table/tbody/tr"))
					.size() == 0, "Attachment : Will be blank by default");

			waitForObj(7000);

			click(By.xpath("//button[@data-toggle='dropdown']"), "");

			String[] str = { "Bid Details", "Bid Preview", "Raise Evaluation Clarification" };

			List<String> asList = Arrays.asList(str);

			ArrayList<String> al = new ArrayList<>();
			List<WebElement> listelements = ThreadLocalWebdriver.getDriver()
					.findElements(By.xpath("//button[@data-toggle='dropdown']//following-sibling::ul/li/a"));
			al.add(listelements.get(0).getText());

			al.add(listelements.get(1).getText());

			al.add(listelements.get(2).getText());

			Assert.assertTrue(asList.equals(al), "DropLists values are present");
			;

			pdfResultReport.addStepDetails("validateEvaluationDetailsPage",
					"Evaluation Details page must be validated sucessfully",
					"SucessFully validatedEvaluation Details page" + " ", "Pass", "Y");
			log.info("completed executing the method:: validateTenderOpeningTabDetails_ForBidOpeningUser");

		} catch (com.baseClasses.AutomationException e) {
			log.fatal("Not able to validatedEvaluation Details page" + e.getMessage());
			pdfResultReport.addStepDetails("validateTenderEvaluationTabDetails_WithEvaluatorUser",
					"Evaluation Details page must be validated sucessfully",
					"Unable to validatedEvaluation Details page" + e.getMessage(), "Fail", "N");
		}
	}

	/**
	 * @author venkatesh jujjuru
	 * @throws Throwable
	 */
	public void checktenderStatusIsInevaluationState() throws Throwable {
		try {
			log.info("started executing the method:: checktenderStatusIsInevaluationState");

			waitForObj(1000);
			IsElementPresent(tendercreationlocators.tenderStatus_evaluation);

			pdfResultReport.addStepDetails("checktenderStatusIsInevaluationState",
					"Tender status must be shown as evaluation", "Successfully shown Tender status as evaluation" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: tenderStatus_evaluation");
		} catch (Exception e) {
			log.fatal("Unable to show the status as evaluation" + e.getMessage());
			pdfResultReport.addStepDetails("checktenderStatusIsInevaluationState",
					"Tender status must be shown as evaluation",
					"Unable to show tender status as evaluation" + e.getMessage(), "Fail", "N");
		}
	}

	/**
	 * @author venkatesh
	 * @throws Throwable
	 */
	public void checktenderStageIsInevaluationStage() throws Throwable {
		try {
			log.info("started executing the method:: checktenderStageIsInevaluationStage");

			waitForObj(500);
			IsElementPresent(tendercreationlocators.pendingForEvaluationApproval);

			pdfResultReport.addStepDetails("checktenderStageIsInevaluationState",
					"Tender stage must be shown as evaluation", "Successfully shown Tender stage as evaluation" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: tenderStage_evaluation");
		} catch (Exception e) {
			log.fatal("Unable to show the stage as evaluation" + e.getMessage());
			pdfResultReport.addStepDetails("checktenderStageIsInevaluationState",
					"Tender stage must be shown as evaluation",
					"Unable to show tender stage as evaluation" + e.getMessage(), "Fail", "N");
		}
	}

	/**
	 * 
	 * @throws Throwable
	 */
	public void bidderDetailsValidationInBidDetailsPage(String Bidderuser, String biddercompany) throws Throwable {
		try {
			log.info("started executing the method:: bidderDetailsValidation");

			IsElementPresent(By.xpath("//*[contains(normalize-space(text()), '" + Bidderuser.toUpperCase() + "')]"));
			IsElementPresent(By.xpath("//*[normalize-space(text())='Pending']/../../child::td[contains(text(),'"
					+ biddercompany.toUpperCase() + "')]"));

			IsElementPresent(By.xpath("//*[normalize-space(text())='Pending']"));
			IsElementPresent(By.xpath("//button[@data-toggle='dropdown']"));
			IsElementPresent(tendercreationlocators.bidder_status);

			pdfResultReport.addStepDetails("bidderDetailsValidation", "bidder details must be validate successfully",
					"Successfully validated bidder details" + " ", "Pass", "Y");
			log.info("completed executing the method:: bidderDetailsValidation");
		} catch (Exception e) {
			log.fatal("Not able validate bidder details" + e.getMessage());
			pdfResultReport.addStepDetails("bidderDetailsValidation", "bidder details must be validate successfully",
					"Unable to validate bidder details" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyTenderStageIsInFinalApprovalCover_1() throws Exception {

		try {
			log.info("started executing the method:: verifyTenderStageIsInFinalApprovalCover_1");

			// button[@data-original-title='Pending for Approval for Final
			// Evaluation (Cover
			// 1)']

			WebDriver driver = ThreadLocalWebdriver.getDriver();
			WebDriverWait wait = new WebDriverWait(driver, 10);

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//button[@class='btn btn-primary btn-circle btn-lg btn-lg-sub2 grnBtn ng-scope ng-isolate-scope']")));

			IsElementPresent(By.xpath(
					"//button[@class='btn btn-primary btn-circle btn-lg btn-lg-sub2 grnBtn ng-scope ng-isolate-scope']"));

			pdfResultReport.addStepDetails("verifyTenderStageIsInFinalApprovalCover_1",
					"verifyTenderStageIsInFinalApprovalCover_1 must be done successfully",
					"Successfully verified TenderStageIsInFinalApprovalCover_1" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyTenderStageIsInFinalApprovalCover_1");
		} catch (Exception e) {
			log.fatal("Not able toverified TenderStageIsInFinalApprovalCover_1" + e.getMessage());
			pdfResultReport.addStepDetails("verifyTenderStageIsInFinalApprovalCover_1",
					"verifyTenderStageIsInFinalApprovalCover_1 must be done successfully",
					"Unable to verified TenderStageIsInFinalApprovalCover_1" + e.getMessage(), "Fail", "N");
		}
	}

	/**
	 * 
	 * @throws Throwable
	 */
	public void evaluator1Login() throws Throwable {
		try {
			log.info("started executing the method:: evaluator1Login");
			click(tendercreationlocators.login, "login");
			set(tendercreationlocators.userName, pdfResultReport.testData.get("EvaluatorUserName"), "userName");
			waitForObj(5000);
			set(tendercreationlocators.password, pdfResultReport.testData.get("AppPassword"), "password");
			//Handle fixed Captcha (06/11/2020)
			set(tendercreationlocators.Captcha_Login, "1234", "Login_Captcha");
			click(tendercreationlocators.okButton, "okButton");
			waitForElement(tendercreationlocators.dashboardIcon, 5000);
			pdfResultReport.addStepDetails("Tender creator login", "evaluator1Login  must be sucessfully ",
					"Successfully logged in as evaluator1" + " ", "Pass", "Y");
			log.info("completed executing the method:: tenderApproverLogin");

		} catch (Exception e) {
			log.fatal("Unable to do evaluator1Login" + e.getMessage());
			pdfResultReport.addStepDetails("evaluator1Login", "Tender evaluator1Login is not logged in",
					"Unable to do evaluator1Login" + e.getMessage(), "Fail", "N");
		}
	}

	// String tenderno = "//td[text()='Pending for Approval for Final Evaluation
	// (Cover 1)']/preceding-sibling::td[position()=1 and text()='{0}']";
	/**
	 * @author venkatesh jujjuru
	 * @throws Throwable
	 */

	/**
	 * 
	 * @throws Throwable
	 */
	public void clickBidDetailsSection() throws Throwable {
		try {
			log.info("started executing the method:: navigateToBidDetailsSection");

			JSClick(By.xpath("//button[@data-toggle='dropdown']"), "bidder_actiondropdown");

			JSClick(By.xpath("//button[@data-toggle='dropdown']//following-sibling::ul/li[1]/a"), "bidDetails");

			waitForObj(5000);
			pdfResultReport.addStepDetails("navigateToBidDetailsSection",
					"BidDetails Section must be navigated successfully",
					"Successfully navigated to BidDetails Section" + " ", "Pass", "Y");
			log.info("completed executing the method:: navigateToBidDetailsSection");
		} catch (Exception e) {
			log.fatal("Not able to navigate To BidDetails Section" + e.getMessage());
			pdfResultReport.addStepDetails("navigateToBidDetailsSection",
					"BidDetails Section must be navigated successfully",
					"Unable to navigate To BidDetails Section" + e.getMessage(), "Fail", "N");
		}
	}

	/**
	 * @author venkatesh
	 * @throws Throwable
	 */
	public void validateBidDetailsEachAndEveryTab() throws Throwable {
		try {
			log.info("started executing the method:: navigateToBidDetailsEachAndEveryTab");
			click(tendercreationlocators.technical, "technical");
			click(tendercreationlocators.termsAndCondition_bidDetails, "termsAndCondition_bidDetails");
			waitForObj(5000);
			click(tendercreationlocators.generalInformation_bidDetails, "generalInformation_bidDetails");
			waitForObj(5000);
			click(tendercreationlocators.attachments_bidDetails, "attachments_bidDetails");
			waitForObj(5000);
			click(tendercreationlocators.next, "next");
			click(tendercreationlocators.commercial_bidDetails, "commercial_bidDetails");
			waitForObj(5000);
			click(tendercreationlocators.quotationSummary_bidDetails, "quotationSummary_bidDetails");
			waitForObj(5000);
			click(By.xpath("(//i[contains(@class,'remove')])[1]"), "close_bidDetails");
			waitForObj(5000);
			pdfResultReport.addStepDetails("navigateToBidDetailsEachAndEveryTab",
					"BidDetails each and every tab must be navigated successfully",
					"Successfully navigated to BidDetails each and every tab" + " ", "Pass", "Y");
			log.info("completed executing the method:: navigateToBidDetailsEachAndEveryTab");
		} catch (Exception e) {
			log.fatal("Not able to navigate To BidDetails each and every tab" + e.getMessage());
			pdfResultReport.addStepDetails("navigateToBidDetailsEachAndEveryTab",
					"BidDetails each and every tab must be navigated successfully",
					"Unable to navigate To BidDetails each and every tab" + e.getMessage(), "Fail", "N");
		}
	}

	public void editPencilIconAndClickApproveAndSave() throws Throwable {
		try {
			log.info("started executing the method:: editPencilIconAndClickApproveAndSave");

			click(tendercreationlocators.approveOrRejectEditableLink, "approveOrRejectEditableLink");
			click(tendercreationlocators.rejectRadioButton, "rejectRadioButton");

			WebElement saveButton = ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.save);
			Assert.assertFalse(saveButton.isEnabled(), "save button is disabled");
			click(tendercreationlocators.approveRadioButton, "approveRadioButton");

			boolean paramBoolean = true;
			verifyElementIsEnabled(saveButton, paramBoolean);

			set(tendercreationlocators.upload_editable,
					System.getProperty("user.dir") + "\\MediaFiles\\rfqCreation1.xlsx", "fileName");
			waitForObj(8000);
			set(tendercreationlocators.commentText, "Approving all", "commentText");

			waitForObj(2000);
			click(tendercreationlocators.save, "save");

			IsElementPresent(By.xpath("//strong[text()='CTS test (CTS_AUTO_01) is successfully Approved']"));
			click(tendercreationlocators.alertCloseButton, "alertCloseButton");

			IsElementPresent(By.xpath("//table/tbody/tr[2]/td/span/strong[normalize-space(text())='Approved']"));
			// overallcomment
			IsElementPresent(By.xpath(
					"//table/tbody/tr[2]/td/span/strong[normalize-space(text())='Approved']/../../following-sibling::td[1]"));
			// attachment
			IsElementPresent(By.xpath(
					"//table/tbody/tr[2]/td/span/strong[normalize-space(text())='Approved']/../../following-sibling::td[2]"));

			pdfResultReport.addStepDetails("editPencilIconAndClickApproveAndSave",
					"editPencilIconAndClickApproveAndSave must be validated successfully",
					"Successfully edited PencilIcon And Clicked ApproveAndSave" + " ", "Pass", "Y");
			log.info("completed executing the method:: editPencilIconAndClickApproveAndSave");
		} catch (Exception e) {
			log.fatal("Not able to verify the bid details" + e.getMessage());
			pdfResultReport.addStepDetails("Not able toeditPencilIconAndClickApproveAndSave",
					"Not able to verify the bid details using approveOrRejectValidations",
					"Unable to editPencilIconAndClickApproveAndSave" + e.getMessage(), "Fail", "N");
		}
	}

	/**
	 * 
	 * @throws Throwable
	 */
	public void checktenderStatusIsInOpening() throws Throwable {
		try {
			log.info("started executing the method:: checktenderStatusisInOpening");

			IsElementPresent(tendercreationlocators.tenderStatusInOpeningBy);

			pdfResultReport.addStepDetails("checktenderStatusisInOpening", "Tender status must be shown as Opening",
					"Successfully shown Tender status as Opening" + " ", "Pass", "Y");
			log.info("completed executing the method:: checktenderStatusisInOpening");
		} catch (Exception e) {
			log.fatal("Unable to show Tender status as Opening" + e.getMessage());
			pdfResultReport.addStepDetails("checktenderStatusisInOpening", "Tender status must be shown as Opening",
					"Unable to show Tender status as Opening" + e.getMessage(), "Fail", "N");
		}
	}

	/**
	 * @author venkatesh jujjuru
	 * @throws Throwable
	 */

	public void checktenderStageIsInPendingForOpeningApprovalCover1(String CoverNo) throws Throwable {
		try {
			Thread.sleep(5000);
			log.info("started executing the method:: checktenderStageIsInPendingForOpeningApprovalCover1");

			IsElementPresent(tendercreationlocators.openingApprovalCover1);

			pdfResultReport.addStepDetails("checktenderStageIsInPendingForOpeningApprovalCover1",
					"Tender stage must be Pending for opening Approval ("+CoverNo+")",
					"Tender stage successfully shown Pending for opening Approval ("+CoverNo+")" + " ", "Pass", "Y");
	
			log.info("completed executing the method:: checktenderStageIsInPendingForOpeningApprovalCover1");
		} catch (Exception e) {
				log.fatal("Unable to Pending for opening Approval ("+CoverNo+")" + e.getMessage());
				pdfResultReport.addStepDetails("checktenderStageIsInPendingForOpeningApprovalCover1",
						"Tender stage must be Pending for opening Approval ("+CoverNo+")",
						"Unable to show tender stage as Pending for opening Approval ("+CoverNo+")" + e.getMessage(), "Fail", "N");
		}
	}

	public void validateTenderOpeningTabDetails_ForBidOpeningUser(String openingApprovalCover) throws Throwable {

		String str = "//td[text()='" + openingApprovalCover.trim()
				+ "']/preceding-sibling::td[position()=1 and text()='{0}']";
		try {
			log.info("started executing the method:: validateTenderOpeningTabDetails_ForBidOpeningUser");

			click(tendercreationlocators.workFlow, "workFlow");
			Thread.sleep(5000);
			click(tendercreationlocators.pending, "pending");
			Thread.sleep(5000);
			set(tendercreationlocators.search, tenderReferenceNoLocatorText, "search");

			click(tendercreationlocators.tenderopeningTabBy, "tenderopeningTabBy");

			Thread.sleep(5000);

			IsElementPresent(By.xpath(str.replace("{0}", tenderReferenceNoLocatorText)));
			Assert.assertTrue(
					ThreadLocalWebdriver.getDriver()
							.findElement(By.xpath(str.replace("{0}", tenderReferenceNoLocatorText))).getText().trim()
							.equals(tenderReferenceNoLocatorText),
					"Tender id " + tenderReferenceNoLocatorText + "is verified");

			IsElementPresent(By.xpath("//td[text()='" + openingApprovalCover.trim() + "']"));

			IsElementPresent(tendercreationlocators.workFlowStatusPendingBy);

			IsElementPresent(tendercreationlocators.tenderOpeningTabDetailsLinkBy);

			WebDriver driver = ThreadLocalWebdriver.getDriver();

			WebDriverWait wait = new WebDriverWait(driver, 10);

			wait.until(ExpectedConditions.elementToBeClickable(tendercreationlocators.tenderOpeningTabDetailsLinkBy));

			pdfResultReport.addStepDetails("validateTenderOpeningTabDetails_ForBidOpeningUser",
					"Tender OpeningTab Details must be validate For BidOpening User",
					"SucessFully validated TenderOpening TabDetails ForBidOpeningUser" + " ", "Pass", "Y");
			log.info("completed executing the method:: validateTenderOpeningTabDetails_ForBidOpeningUser");
		} catch (Exception e) {
			log.fatal("Not able to validate Tender OpeningTab Details For BidOpening User" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to locate created tender",
					"Tender OpeningTab Details must be validate For BidOpening User",
					"Unable to validate Tender OpeningTab Details For BidOpening User" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyTenderStageIsInFinalApprovalCover_1Or_Cover2() throws Exception {

		try {
			log.info("started executing the method:: verifyTenderStageIsInFinalApprovalCover_1Or_Cover2");

			By finalApprovalStageBy = By.xpath("//button[@id='evaluation123']");
			waitForElementToBeVisible(finalApprovalStageBy);
			IsElementPresent(finalApprovalStageBy);

			pdfResultReport.addStepDetails("verifyTenderStageIsInFinalApprovalCover_1Or_Cover2",
					"TenderStage Is  In Final ApprovalCover_1Or_Cover2 verification must be done successfully",
					"Successfully verified TenderStageIsInFinalApprovalCover_1Or_Cover2" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyTenderStageIsInFinalApprovalCover_1Or_Cover2");
		} catch (Exception e) {
			log.fatal("Not able to verified TenderStageIsInFinalApprovalCover_1Or_Cover2" + e.getMessage());
			pdfResultReport.addStepDetails("verifyTenderStageIsInFinalApprovalCover_1Or_Cover2",
					"TenderStage Is In Final ApprovalCover_1Or_Cover2 verification must be done successfully",
					"Unable to verified TenderStageIsInFinalApprovalCover_1Or_Cover2" + e.getMessage(), "Fail", "N");
		}
	}

	public void validateBidDetailsEachAndEveryTabForBidPart2() throws Throwable {
		try {
			log.info("started executing the method:: navigateToBidDetailsEachAndEveryTab");
			click(tendercreationlocators.bidopeningcommentTabBy, "bidopeningcommentTabBy");
			waitForObj(5000);
			click(tendercreationlocators.bidEvaluationCommentTabBy, "bidEvaluationCommentTabBy");
			waitForObj(5000);
			click(tendercreationlocators.technical, "technical");
			waitForObj(5000);
			click(tendercreationlocators.termsAndCondition_bidDetails, "termsAndCondition_bidDetails");
			waitForObj(5000);
			click(tendercreationlocators.generalInformation_bidDetails, "generalInformation_bidDetails");
			waitForObj(5000);
			click(tendercreationlocators.attachments_bidDetails, "attachments_bidDetails");
			waitForObj(5000);
			click(tendercreationlocators.next, "next");
			click(tendercreationlocators.commercial_bidDetails, "commercial_bidDetails");
			waitForObj(5000);
			click(tendercreationlocators.quotationSummary_bidDetails, "quotationSummary_bidDetails");
			waitForObj(5000);
			click(By.xpath("(//i[contains(@class,'remove')])[1]"), "close_bidDetails");
			waitForObj(5000);
			pdfResultReport.addStepDetails("navigateToBidDetailsEachAndEveryTab",
					"BidDetails each and every tab must be navigated successfully",
					"Successfully navigated to BidDetails each and every tab" + " ", "Pass", "Y");
			log.info("completed executing the method:: navigateToBidDetailsEachAndEveryTab");
		} catch (Exception e) {
			log.fatal("Not able to navigate To BidDetails each and every tab" + e.getMessage());
			pdfResultReport.addStepDetails("navigateToBidDetailsEachAndEveryTab",
					"BidDetails each and every tab must be navigated successfully",
					"Unable to navigate To BidDetails each and every tab" + e.getMessage(), "Fail", "N");
		}
	}

	public void provideCommentsForBidOpeningBidpart2() throws Throwable {
		try {
			log.info("started executing the method:: provideCommentsForBidOpeningBidpart2");

			set(By.xpath("(//label[contains(text(),'Comments')]/following::textarea)[1]"),
					"Sending for approval to Approval user", "commentSectionBy");

			pdfResultReport.addStepDetails("provideCommentsForBidOpeningBidpart2",
					"comment must be done successfull in BidOpening tab",
					"Successfully provided comment for  BidOpening tab" + " ", "Pass", "Y");
			log.info("completed executing the method:: provideCommentsForBidOpeningBidpart2");
		} catch (Exception e) {
			log.fatal("Not ableprovided comment for bid" + e.getMessage());
			pdfResultReport.addStepDetails("provideCommentsForBidOpeningBidpart2",
					"comment must be done successfully  BidOpening tab",
					"Unable to provided comment for  BidOpening tab " + e.getMessage(), "Fail", "N");
		}
	}

	public void provideCommentsForBidEvaluationBidpart2() throws Throwable {
		try {
			log.info("started executing the method:: provideCommentsForBidEvaluationBidpart2");

			click(tendercreationlocators.bidEvaluationTabBy, "bidEvaluationTabBy");

			waitForObj(2000);

			set(tendercreationlocators.evaluationSettingspageEvaluationCommentBy,
					"Sending for approval to eavluator user",
					"tendercreationlocators.evaluationSettingspageEvaluationCommentBy");

			pdfResultReport.addStepDetails("provideCommentsForBidOpeningBidpart2",
					"comment must be done successfully Bid evaluation tab",
					"Successfully provided comment for Bid evaluation tab" + " ", "Pass", "Y");
			log.info("completed executing the method:: provideCommentsForBidEvaluationBidpart2");
		} catch (Exception e) {
			log.fatal("Not ableprovided comment for bid" + e.getMessage());
			pdfResultReport.addStepDetails("provideCommentsForBidEvaluationBidpart2",
					"comment must be done successfully Bid evaluation tab",
					"Unable to provided comment for Bid evaluation tab" + e.getMessage(), "Fail", "N");
		}
	}

	public void checktenderStageIsInPendingForOpeningApprovalCover2() throws Throwable {
		try {
			log.info("started executing the method:: checktenderStageIsInPendingForOpeningApprovalCover2");

			IsElementPresent(tendercreationlocators.tenderStageApprovalCover2By);

			pdfResultReport.addStepDetails("checktenderStageIsInPendingForOpeningApprovalCover2",
					"Tender status must be Pending for opening Approval (Cover 2)",
					"Successfully shown Pending for opening Approval (Cover 2)" + " ", "Pass", "Y");
			log.info("completed executing the method:: checktenderStageIsInPendingForOpeningApprovalCover2");
		} catch (Exception e) {
			log.fatal("Unable to Pending for opening Approval (Cover 2)" + e.getMessage());
			pdfResultReport.addStepDetails("checktenderStageIsInPendingForOpeningApprovalCover2",
					"Tender status must be Pending for opening Approval (Cover 2)",
					"Unable to show Pending for opening Approval (Cover 2)" + e.getMessage(), "Fail", "N");
		}
	}

	public void checktenderStageIsInCover1Evaluated() throws Throwable {
		try {
			log.info("started executing the method:: checktenderStageIsInCover1Evaluated");

			IsElementPresent(tendercreationlocators.tenderStageCover1Evaluated);

			pdfResultReport.addStepDetails("checktenderStageIsInCover1Evaluated",
					"Tender stage must be Cover1 Evaluated",
					"Successfully shown tender stage as Cover1 Evaluated" + " ", "Pass", "Y");
			log.info("completed executing the method:: checktenderStageIsInCover1Evaluated");
		} catch (Exception e) {
			log.fatal("Unable to show tender stage as Cover1 Evaluated" + e.getMessage());
			pdfResultReport.addStepDetails("checktenderStageIsInCover1Evaluated",
					"Tender stage must be Cover1 Evaluated",
					"Unable to show tender stage as Cover1 Evaluated" + e.getMessage(), "Fail", "N");
		}
	}

	public void approveOrRejectValidations_CTS_AUTO_01() throws Throwable {
		try {
			log.info("started executing the method:: approveOrRejectValidations");
			WebElement saveButton = ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.save);
			boolean paramBoolean = true;
			click(tendercreationlocators.approveOrRejectEditableLink, "approveOrRejectEditableLink");
			click(tendercreationlocators.rejectRadioButton, "rejectRadioButton");
			verifyElementIsEnabled(saveButton, paramBoolean);
			set(tendercreationlocators.commentText, pdfResultReport.testData.get("overAllComment"), "commentText");
			verifyElementIsEnabled(saveButton, paramBoolean);
			click(tendercreationlocators.approveRadioButton, "approveRadioButton");
			set(tendercreationlocators.upload_editable,
					System.getProperty("user.dir") + "\\MediaFiles\\rfqCreation1.xlsx", "fileName");
			set(tendercreationlocators.commentText, pdfResultReport.testData.get("overAllComment"), "commentText");
			click(tendercreationlocators.save, "save");
			waitForObj(5000);
			IsElementPresent(tendercreationlocators.alertPopUp_CTS_AUTO_01);
			click(tendercreationlocators.alertCloseButton, "alertCloseButton");
			waitForObj(5000);
			IsElementPresent(tendercreationlocators.bidderStatusApprove);
			IsElementPresent(tendercreationlocators.bidderOverAllComment);
			// IsElementPresent(tendercreationlocators.bidderAttachment);
			click(tendercreationlocators.submitbutton1, "submitbutton1");
			waitForObj(20000);
		} catch (Exception e) {
			log.fatal("Not able to verify the bid details" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to verify the bid details",
					"Not able to verify the bid details using approveOrRejectValidations",
					"Unable to verify the bid details using approveOrRejectValidations" + e.getMessage(), "Fail", "N");
		}
	}

	public void ProvideCommentsForBidOpeningApproval() throws Exception {
		try {
			log.info("started executing the method:: ProvideCommentsForBidOpeningApproval");
			set(tendercreationlocators.commentSectionBy, "Sending for approval to Approval user", "commentSectionBy");

			pdfResultReport.addStepDetails("ProvideCommentsForBidOpeningApproval",
					"Provide Comments For BidOpening Approval",
					"Successfully Provided Comments For BidOpening Approval" + " ", "Pass", "Y");
			log.info("completed executing the method:: ProvideCommentsForBidOpeningApproval");
		} catch (Exception e) {
			log.fatal("Unable to show Tender status as evaluation" + e.getMessage());
			pdfResultReport.addStepDetails("ProvideCommentsForBidOpeningApproval",
					"Provide Comments For BidOpenin gApproval",
					"Unable to Provide Comments For BidOpening Approval" + e.getMessage(), "Fail", "N");
		}

	}

	public void checktenderStageIsInPendingforEvaluationApprovalCover2Stage() throws Throwable {
		try {
			log.info("started executing the method:: checktenderStageIsInPendingforEvaluationApprovalCover2Stage");

			WebDriverWait wait = new WebDriverWait(ThreadLocalWebdriver.getDriver(), 7000);
			wait.until(
					ExpectedConditions.visibilityOfElementLocated(tendercreationlocators.pendingForEvaluationApproval));

			IsElementPresent(tendercreationlocators.pendingForEvaluationApproval);

			pdfResultReport.addStepDetails("checktenderStageIsInPendingforEvaluationApprovalCover2Stage",
					"Tender stage must be shown as PendingforEvaluationApprovalCover2",
					"Successfully shown Tender stage as PendingforEvaluationApprovalCover2" + " ", "Pass", "Y");
			log.info("completed executing the method:: tenderStage_PendingforEvaluationApprovalCover2");
		} catch (Exception e) {
			log.fatal("Unable to show the stage as PendingforEvaluationApprovalCover2" + e.getMessage());
			pdfResultReport.addStepDetails("checktenderStageIsInPendingforEvaluationApprovalCover2Stage",
					"Tender stage must be shown as PendingforEvaluationApprovalCover2",
					"Unable to show tender stage as PendingforEvaluationApprovalCover2" + e.getMessage(), "Fail", "N");
		}
	}

	public void checktenderStageIsInPendingforOpeningApprovalCover2Stage() throws Throwable {
		try {
			log.info("started executing the method:: checktenderStageIsInPendingforOpeningApprovalCover2Stage");

			By openingapprovalCover2Stage = By.xpath(
					"//button[@class='btn btn-primary btn-circle btn-lg btn-lg-sub2 grnBtn ng-scope ng-isolate-scope']");

			WebDriverWait wait = new WebDriverWait(ThreadLocalWebdriver.getDriver(), 7);
			wait.until(ExpectedConditions.visibilityOfElementLocated(openingapprovalCover2Stage));

			IsElementPresent(openingapprovalCover2Stage);

			pdfResultReport.addStepDetails("checktenderStageIsInPendingforOpeningApprovalCover2Stage",
					"Tender stage must be shown as PendingforOpeningApprovalCover2",
					"Successfully shown Tender stage as PendingforOpeningApprovalCover2" + " ", "Pass", "Y");
			log.info("completed executing the method:: checktenderStageIsInPendingforOpeningApprovalCover2Stage");
		} catch (Exception e) {
			log.fatal("Unable to show the stage as PendingforEvaluationApprovalCover1" + e.getMessage());
			pdfResultReport.addStepDetails("checktenderStageIsInPendingforOpeningApprovalCover2Stage",
					"Tender stage must be shown as PendingforOpeningApprovalCover2",
					"Unable to show tender stage as PendingforOpeningApprovalCover2" + e.getMessage(), "Fail", "N");
		}
	}

	public void validateBidDetailsEachAndEveryTabForBidPart2_Cover1() throws Throwable {
		try {
			log.info("started executing the method:: navigateToBidDetailsEachAndEveryTab");

			click(tendercreationlocators.bidopeningcommentTabBy, "bidEvaluationCommentTabBy");
			waitForObj(5000);
			click(tendercreationlocators.bidEvaluationCommentTabBy, "bidEvaluationCommentTabBy");
			waitForObj(5000);
			click(tendercreationlocators.technical, "technical");
			waitForObj(5000);
			click(tendercreationlocators.termsAndCondition_bidDetails, "termsAndCondition_bidDetails");
			waitForObj(5000);
			click(tendercreationlocators.generalInformation_bidDetails, "generalInformation_bidDetails");
			waitForObj(5000);
			click(tendercreationlocators.attachments_bidDetails, "attachments_bidDetails");
			waitForObj(5000);
			click(By.xpath("(//i[contains(@class,'remove')])[1]"), "close_bidDetails");
			waitForObj(10000);
			pdfResultReport.addStepDetails("navigateToBidDetailsEachAndEveryTab",
					"BidDetails each and every tab must be navigated successfully",
					"Successfully navigated to BidDetails each and every tab" + " ", "Pass", "Y");
			log.info("completed executing the method:: navigateToBidDetailsEachAndEveryTab");
		} catch (Exception e) {
			log.fatal("Not able to navigate To BidDetails each and every tab" + e.getMessage());
			pdfResultReport.addStepDetails("navigateToBidDetailsEachAndEveryTab",
					"BidDetails each and every tab must be navigated successfully",
					"Unable to navigate To BidDetails each and every tab" + e.getMessage(), "Fail", "N");
		}
	}

	public void validateBidDetailsEachAndEveryTabForBidPart2_bidEvaluationApproval() throws Throwable {
		try {
			log.info(
					"started executing the method:: validateBidDetailsEachAndEveryTabForBidPart2_bidEvaluationApproval");
			click(tendercreationlocators.bidEvaluationCommentTabBy, "bidEvaluationCommentTabBy");
			waitForObj(2000);
			click(tendercreationlocators.technical, "technical");
			waitForObj(2000);
			click(tendercreationlocators.termsAndCondition_bidDetails, "termsAndCondition_bidDetails");
			waitForObj(2000);
			click(tendercreationlocators.generalInformation_bidDetails, "generalInformation_bidDetails");
			waitForObj(2000);
			click(tendercreationlocators.attachments_bidDetails, "attachments_bidDetails");
			waitForObj(2000);
			click(By.xpath("(//i[contains(@class,'remove')])[1]"), "close_bidDetails");
			waitForObj(5000);
			pdfResultReport.addStepDetails("validateBidDetailsEachAndEveryTabForBidPart2_bidEvaluationApproval",
					"BidDetails each and every tab must be navigated successfully",
					"Successfully navigated to BidDetails each and every tab" + " ", "Pass", "Y");
			log.info(
					"completed executing the method:: validateBidDetailsEachAndEveryTabForBidPart2_bidEvaluationApproval");
		} catch (Exception e) {
			log.fatal("Not able to navigate To BidDetails each and every tab" + e.getMessage());
			pdfResultReport.addStepDetails("validateBidDetailsEachAndEveryTabForBidPart2_bidEvaluationApproval",
					"BidDetails each and every tab must be navigated successfully",
					"Unable to navigate To BidDetails each and every tab" + e.getMessage(), "Fail", "N");
		}
	}

	public void approveOrRejectValidations_CTS_AUTO_01_BidEvaluationApproval() throws Throwable {
		try {
			log.info("started executing the method:: approveOrRejectValidations_CTS_AUTO_01_BidEvaluationApproval");
			WebElement saveButton = ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.save);
			boolean paramBoolean = true;
			click(tendercreationlocators.approveOrRejectEditableLink, "approveOrRejectEditableLink");
			click(tendercreationlocators.rejectRadioButton, "rejectRadioButton");
			verifyElementIsEnabled(saveButton, paramBoolean);
			set(tendercreationlocators.commentText, pdfResultReport.testData.get("overAllComment"), "commentText");
			verifyElementIsEnabled(saveButton, paramBoolean);
			click(tendercreationlocators.approveRadioButton, "approveRadioButton");
			set(tendercreationlocators.upload_editable,
					System.getProperty("user.dir") + "\\MediaFiles\\rfqCreation1.xlsx", "fileName");
			set(tendercreationlocators.commentText, pdfResultReport.testData.get("overAllComment"), "commentText");
			click(tendercreationlocators.save, "save");
			waitForObj(5000);
			IsElementPresent(tendercreationlocators.alertPopUp_CTS_AUTO_01);
			click(tendercreationlocators.alertCloseButton, "alertCloseButton");
			waitForObj(5000);
			IsElementPresent(tendercreationlocators.bidderStatusApprove);
			IsElementPresent(tendercreationlocators.bidderOverAllComment);
			waitForObj(10000);
		} catch (Exception e) {
			log.fatal("Not able to verify the bid details" + e.getMessage());
			pdfResultReport.addStepDetails("approveOrRejectValidations_CTS_AUTO_01_BidEvaluationApproval",
					"approve the bid details using approveOrRejectValidations",
					"Unable to approve the bid details using approveOrRejectValidations" + e.getMessage(), "Fail", "N");
		}
	}

	public void navigateToEachAndEveryTab_BidEvaluationApproval() throws Throwable {
		try {
			log.info("started executing the method:: navigateToEachAndEveryTab_BidEvaluationApproval");
			click(tendercreationlocators.technical, "technical");
			waitForObj(5000);
			IsElementPresent(tendercreationlocators.technical);
			IsElementPresent(tendercreationlocators.technical_Clause);
			IsElementPresent(tendercreationlocators.technical_Details);
			IsElementPresent(tendercreationlocators.technical_Attachment);
			IsElementPresent(tendercreationlocators.technical_Complaince);
			IsElementPresent(tendercreationlocators.technical_AttachFile);
			IsElementPresent(tendercreationlocators.technical_Remarks);
			IsElementPresent(tendercreationlocators.technical_EvaluatorRemarks);
			click(tendercreationlocators.termsNConditions, "termsNConditions");
			waitForObj(5000);
			IsElementPresent(tendercreationlocators.tnc_Clause);
			IsElementPresent(tendercreationlocators.tnc_Details);
			// IsElementPresent(tendercreationlocators.tnc_Attachment);
			IsElementPresent(tendercreationlocators.tnc_Complaince);
			// IsElementPresent(tendercreationlocators.tnc_AttachFile);
			IsElementPresent(tendercreationlocators.tnc_Remarks);
			IsElementPresent(tendercreationlocators.tnc_EvaluatorRemarks);
			click(tendercreationlocators.generalInformationTab, "generalInformationTab");
			waitForObj(5000);
			IsElementPresent(tendercreationlocators.tenderIdBidDetails);
			IsElementPresent(tendercreationlocators.tenderDescription);
			IsElementPresent(tendercreationlocators.proableAmountOfContract);
			IsElementPresent(tendercreationlocators.tenderCategory);
			IsElementPresent(tendercreationlocators.referenceCode);
			IsElementPresent(tendercreationlocators.quotationCurrency);
			click(tendercreationlocators.attachementsTab, "attachementsTab");
			waitForObj(5000);
			IsElementPresent(tendercreationlocators.bidderAttachment1);
			IsElementPresent(tendercreationlocators.bidSpecificAttachment);
			IsElementPresent(tendercreationlocators.tenderAttachment);
			click(tendercreationlocators.closeXButton, "closeXButton");
			waitForObj(5000);
			pdfResultReport.addStepDetails("navigateToEachAndEveryTab_BidEvaluationApproval",
					"navigate ToEachAndEveryTab_BidEvaluationApproval",
					"Successfully navigated ToEachAndEveryTab_BidEvaluationApproval" + " ", "Pass", "Y");
			log.info("completed executing the method:: navigateToEachAndEveryTab_BidEvaluationApproval");
		} catch (Exception e) {
			log.fatal("Not able to verify the bid details" + e.getMessage());
			pdfResultReport.addStepDetails("navigateToEachAndEveryTab_BidEvaluationApproval",
					"navigate ToEachAndEveryTab_BidEvaluationApproval",
					"Unable to navigate ToEachAndEveryTab_BidEvaluationApproval" + e.getMessage(), "Fail", "N");
		}
	}

	public void selectEvaluationAndProvideCommentsForBidEvaluationApproval() throws Exception {
		try {
			log.info("started executing the method:: selectEvaluationAndProvideCommentsForBidEvaluationApproval");

			click(tendercreationlocators.bidValBy, "bidValBy");

			click(tendercreationlocators.bidEvaluationTabBy, "bidEvaluationTabBy");

			waitForObj(2000);

			click(By.xpath("(//span[text()='Nothing selected'])[1]"), "dropDownNothingselectedBy");

			click(tendercreationlocators.evaluator_01By, "evaluator_01By");

			click(tendercreationlocators.evaluationSettingspageEvaluationCommentBy,
					"evaluationSettingspageEvaluationCommentBy");

			set(tendercreationlocators.evaluationSettingspageEvaluationCommentBy,
					"Sending for approval to eavluator user",
					"tendercreationlocators.evaluationSettingspageEvaluationCommentBy");

			pdfResultReport.addStepDetails("selectEvaluationAndProvideCommentsForBidEvaluationApproval",
					"should select BidEvaluation And Provide Comments For BidEvaluation Approval",
					"Successfully selected BidEvaluation And Provide Comments For BidEvaluationApproval" + " ", "Pass",
					"Y");
			log.info("completed executing the method:: selectEvaluationAndProvideCommentsForBidEvaluationApproval");
		} catch (Exception e) {
			log.fatal(
					"Unable to select BidEvaluation And Provide Comments For BidEvaluation Approval" + e.getMessage());
			pdfResultReport.addStepDetails("selectEvaluationAndProvideCommentsForBidOpeningApproval",
					"should select BidEvaluation And Provide Comments For BidEvaluation gApproval",
					"Unable to select BidEvaluation And Provide Comments For BidEvaluation Approval" + e.getMessage(),
					"Fail", "N");
		}

	}

	public void navigateToBidDetailsEachAndEveryTab_BidEvaluationApproval2() throws Throwable {
		try {
			log.info("started executing the method:: navigateToBidDetailsEachAndEveryTab_BidEvaluationApproval2");
			click(tendercreationlocators.technical, "technical");
			click(tendercreationlocators.termsAndCondition_bidDetails, "termsAndCondition_bidDetails");
			waitForObj(2000);
			click(tendercreationlocators.generalInformation_bidDetails, "generalInformation_bidDetails");
			waitForObj(2000);
			click(tendercreationlocators.attachments_bidDetails, "attachments_bidDetails");
			waitForObj(2000);
			click(tendercreationlocators.next, "next");
			click(tendercreationlocators.commercial_bidDetails, "commercial_bidDetails");
			waitForObj(2000);
			click(tendercreationlocators.quotationSummary_bidDetails, "quotationSummary_bidDetails");
			waitForObj(2000);
			click(tendercreationlocators.closeXButton, "closeXButton");
			waitForObj(5000);
			pdfResultReport.addStepDetails("navigateToBidDetailsEachAndEveryTab_BidEvaluationApproval2",
					"BidDetails each and every tab must be navigated successfully",
					"Successfully navigated to BidDetails each and every tab" + " ", "Pass", "Y");
			log.info("completed executing the method:: navigateToBidDetailsEachAndEveryTab_BidEvaluationApproval2");
		} catch (Exception e) {
			log.fatal("Not able to navigate To BidDetails each and every tab" + e.getMessage());
			pdfResultReport.addStepDetails("navigateToBidDetailsEachAndEveryTab_BidEvaluationApproval2",
					"BidDetails each and every tab must be navigated successfully",
					"Unable to navigate To BidDetails each and every tab" + e.getMessage(), "Fail", "N");
		}
	}

	public void selectNoForApprovalAndEvaluationRequired() throws Exception {
		try {
			log.info("started executing the method:: selectYesForApprovalAndEvaluationRequired");

			click(tendercreationlocators.selectNoBy, "selectNoBy");

			pdfResultReport.addStepDetails("selectYesForApprovalAndEvaluationRequired",
					"should select Yes for approval and evaluation required ",
					"Successfully selected Yes for approval and evaluation required " + " ", "Pass", "Y");
			log.info("completed executing the method:: selectYesForApprovalAndEvaluationRequired");
		} catch (Exception e) {
			log.fatal("Unable to show Tender status as evaluation" + e.getMessage());
			pdfResultReport.addStepDetails("selectYesForApprovalAndEvaluationRequired",
					"should select Yes for approval and evaluation required",
					"Unable to selected Yes for approval and evaluation required" + e.getMessage(), "Fail", "N");
		}
	}

	public void validateBidDetailsEachAndEveryTab2() throws Throwable {
		try {
			log.info("started executing the method:: navigateToBidDetailsEachAndEveryTab");
			click(tendercreationlocators.technical, "technical");
			click(tendercreationlocators.termsAndCondition_bidDetails, "termsAndCondition_bidDetails");
			waitForObj(2000);
			click(tendercreationlocators.generalInformation_bidDetails, "generalInformation_bidDetails");
			waitForObj(2000);
			click(tendercreationlocators.attachments_bidDetails, "attachments_bidDetails");
			waitForObj(2000);
			click(tendercreationlocators.next, "next");
			// click(tendercreationlocators.commercial_bidDetails,
			// "commercial_bidDetails");
			waitForObj(2000);
			// click(tendercreationlocators.quotationSummary_bidDetails,
			// "quotationSummary_bidDetails");
			waitForObj(2000);
			click(By.xpath("(//i[contains(@class,'remove')])[1]"), "close_bidDetails");
			waitForObj(5000);
			pdfResultReport.addStepDetails("navigateToBidDetailsEachAndEveryTab",
					"BidDetails each and every tab must be navigated successfully",
					"Successfully navigated to BidDetails each and every tab" + " ", "Pass", "Y");
			log.info("completed executing the method:: navigateToBidDetailsEachAndEveryTab");
		} catch (Exception e) {
			log.fatal("Not able to navigate To BidDetails each and every tab" + e.getMessage());
			pdfResultReport.addStepDetails("navigateToBidDetailsEachAndEveryTab",
					"BidDetails each and every tab must be navigated successfully",
					"Unable to navigate To BidDetails each and every tab" + e.getMessage(), "Fail", "N");
		}
	}

	public void validateTenderEvaluationTabDetails_WithEvaluatorUserCover2(String evaluationCoverNo) throws Throwable {
		try {
			log.info("started executing the method:: validateTenderEvaluationTabDetails_WithEvaluatorUser");

			click(tendercreationlocators.workFlow, "workFlow");
			click(tendercreationlocators.pending, "pending");
			set(tendercreationlocators.search, tenderReferenceNoLocatorText, "search");

			waitForElement(tendercreationlocators.workFlowStatusPendingBy, 5);

			String tenderno = "//td[text()='" + evaluationCoverNo
					+ "']/preceding-sibling::td[position()=1 and text()='{0}']";
			IsElementPresent(By.xpath(tenderno.replace("{0}", tenderReferenceNoLocatorText)));

			IsElementPresent(tendercreationlocators.workFlowTypeFinalApprovalCover2By);

			IsElementPresent(tendercreationlocators.workFlowStatusPendingBy);

			IsElementPresent(tendercreationlocators.tenderOpeningTabDetailsLinkBy);

			WebDriver driver = ThreadLocalWebdriver.getDriver();

			WebDriverWait wait = new WebDriverWait(driver, 10);

			wait.until(ExpectedConditions.elementToBeClickable(tendercreationlocators.tenderOpeningTabDetailsLinkBy));

			pdfResultReport.addStepDetails("validateTenderEvaluationTabDetails_WithEvaluatorUser",
					"Tender Evaluation tab Details must be validated",
					"SucessFully validated Tender Evaluation TabDetails" + " ", "Pass", "Y");
			log.info("completed executing the method:: validateTenderOpeningTabDetails_ForBidOpeningUser");
		} catch (Exception e) {
			log.fatal("Not able to Tender Evaluation TabDetails" + e.getMessage());
			pdfResultReport.addStepDetails("validateTenderEvaluationTabDetails_WithEvaluatorUser",
					"Tender Evaluation tab Details must be validated",
					"Unable to validate Tender Evaluation TabDetails" + e.getMessage(), "Fail", "N");
		}
	}

	public void approveOrRejectValidationsCTSBidder() throws Throwable {
		try {
			log.info("started executing the method:: approveOrRejectValidations");
			WebElement saveButton = ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.save);
			boolean paramBoolean = true;
			click(tendercreationlocators.approveOrRejectEditableLink, "approveOrRejectEditableLink");
			click(tendercreationlocators.rejectRadioButton, "rejectRadioButton");
			verifyElementIsEnabled(saveButton, paramBoolean);
			set(tendercreationlocators.commentText, pdfResultReport.testData.get("overAllComment"), "commentText");
			verifyElementIsEnabled(saveButton, paramBoolean);
			click(tendercreationlocators.approveRadioButton, "approveRadioButton");
			set(tendercreationlocators.upload_editable,
					System.getProperty("user.dir") + "\\MediaFiles\\rfqCreation1.xlsx", "fileName");
			set(tendercreationlocators.commentText, pdfResultReport.testData.get("overAllComment"), "commentText");
			click(tendercreationlocators.save, "save");
			waitForObj(5000);
			IsElementPresent(tendercreationlocators.alertPopUp_CTS);
			click(tendercreationlocators.alertCloseButton, "alertCloseButton");
			waitForObj(5000);
			IsElementPresent(tendercreationlocators.bidderStatusApprove);
			IsElementPresent(tendercreationlocators.bidderOverAllComment);
			// IsElementPresent(tendercreationlocators.bidderAttachment);
			click(tendercreationlocators.submitbutton1, "submitbutton1");
			waitForObj(10000);
		} catch (Exception e) {
			log.fatal("Not able to verify the bid details" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to verify the bid details",
					"Not able to verify the bid details using approveOrRejectValidations",
					"Unable to verify the bid details using approveOrRejectValidations" + e.getMessage(), "Fail", "N");
		}
	}

	public void validationMessage_WithoutViewingBidDetailsSection_WithoutApproving() throws Throwable {
		try {
			log.info(
					"started executing the method:: validationMessage_WithoutViewingBidDetailsSection_WithoutApproving");

			IsElementPresent(tendercreationlocators.approve0rReject_AlertMessage);
			IsElementPresent(tendercreationlocators.viewDetailsOfBidder_AlertMessage);

			pdfResultReport.addStepDetails("validationMessage_WithoutViewingBidDetailsSection_WithoutApproving",
					"Alert message must be validate successfully", "Successfully validated Alert message" + " ", "Pass",
					"Y");
			log.info(
					"completed executing the method:: validationMessage_WithoutViewingBidDetailsSection_WithoutApproving");
		} catch (Exception e) {
			log.fatal("Not able to validate Alert message" + e.getMessage());
			pdfResultReport.addStepDetails("validationMessage_WithoutViewingBidDetailsSection_WithoutApproving",
					"Alert message must be validate successfully", "Unable to validate Alert message" + e.getMessage(),
					"Fail", "N");
		}
	}

	public void validationMessage__WithoutApproving() throws Throwable {
		try {
			log.info("started executing the method:: validationMessage_WithoutApproving");

			IsElementPresent(tendercreationlocators.approve0rReject_AlertMessage);

			pdfResultReport.addStepDetails("validationMessage_WithoutApproving",
					"Alert message must be validate successfully", "Successfully validated Alert message" + " ", "Pass",
					"Y");
			log.info("completed executing the method:: validationMessage_WithoutApproving");
		} catch (Exception e) {
			log.fatal("Not able to validate Alert message" + e.getMessage());
			pdfResultReport.addStepDetails("validationMessage_WithoutApproving",
					"Alert message must be validate successfully", "Unable to validate Alert message" + e.getMessage(),
					"Fail", "N");
		}
	}

	public static void waitForSpinnerToDisappear() {
		WebDriver driver = ThreadLocalWebdriver.getDriver();
		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@id='spinnerholder']"))));
	}

	public static void waitForSpinnerToDisappearInBidSubmission() {
		WebDriver driver = ThreadLocalWebdriver.getDriver();
		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions
				.invisibilityOf(driver.findElement(By.xpath("//*[@id='container']//div[@id='spinnerholder']"))));
	}

	/**
	 * 
	 * @throws Throwable
	 */
	public void bidDetailsValidationInBidDetailsPageFormultipleBiddersForCover1(String Bidderuser, String biddercompany,
			int i) throws Throwable {
		try {
			log.info("started executing the method:: bidDetailsValidationInBidDetailsPageFormultipleBiddersForCover1");

			String decryptpending = "(//*[normalize-space(text())='Pending'])[{yyy}]";
			String dropdown = "(//button[@data-toggle='dropdown'])[{xxx}]";
			String Pendingstatus = "(//*[normalize-space(text())='PENDING'])[{zzz}]";

			IsElementPresent(By.xpath("//*[contains(normalize-space(text()), '" + Bidderuser.toUpperCase() + "')]"));
			IsElementPresent(By.xpath("//*[normalize-space(text())='Pending']/../../child::td[contains(text(),'"
					+ biddercompany + "')]"));

			IsElementPresent(By.xpath(decryptpending.replace("{yyy}", +i + "")));

			IsElementPresent(By.xpath(dropdown.replace("{xxx}", +i + "")));

			IsElementPresent(By.xpath(Pendingstatus.replace("{zzz}", +i + "")));

			pdfResultReport.addStepDetails("bidDetailsValidationInBidDetailsPageFormultipleBiddersForCover1",
					"bidder details must be validate successfully", "Successfully validated bidder details" + " ",
					"Pass", "Y");
			log.info(
					"completed executing the method:: bidDetailsValidationInBidDetailsPageFormultipleBiddersForCover1");
		} catch (Exception e) {
			log.fatal("Not able validate bidder details" + e.getMessage());
			pdfResultReport.addStepDetails("bidDetailsValidationInBidDetailsPageFormultipleBiddersForCover1",
					"bidder details must be validate successfully",
					"Unable to validate bidder details" + e.getMessage(), "Fail", "N");
		}
	}

	public void validateEvaluationDetailsPageAllBidderDetails(String coverno, String no_ofBids, String liveBids,
			String bidderUserName, String bidderName) throws Exception {
		try {

			log.info("started executing the method:: validateEvaluationDetailsPageAllBidderDetails");

			String CoverApprovalText = text(By.xpath("//div[@class='alert alert-info text-center']/strong"));

			String cover = CoverApprovalText
					.substring(CoverApprovalText.lastIndexOf('(') + 1, CoverApprovalText.lastIndexOf(')')).trim();

			Assert.assertTrue(cover.trim().equalsIgnoreCase(coverno),
					"PENDING FOR APPROVAL FOR FINAL EVALUATION" + coverno + "is verified");

			String TotalBids = text(By.xpath("(//*[@id='main-content-nw']//div//h3/span)[1]"));

			Assert.assertTrue(TotalBids.split(":")[1].trim().equals(no_ofBids), "Total Bids : verified");

			String liveBidsActual = text(By.xpath("(//*[@id='main-content-nw']//div//h3/span)[2]"));

			Assert.assertTrue(liveBidsActual.split(":")[1].trim().equals(liveBids), "nof live bids");

			Assert.assertTrue(ThreadLocalWebdriver.getDriver()
					.findElement(
							By.xpath("//*[contains(normalize-space(text()), '" + bidderUserName.toUpperCase() + "')]"))
					.isDisplayed(), "bidderUserName is present");

			Assert.assertTrue(ThreadLocalWebdriver.getDriver()
					.findElement(By
							.xpath("//span/strong[normalize-space(text())='Pending']/parent::span/parent::td/preceding-sibling::td[text()='"
									+ bidderName + "']"))
					.isDisplayed(), "bidderName is present");

			pdfResultReport.addStepDetails("validateEvaluationDetailsPageAllBidderDetails",
					"Evaluation Details page must be validated sucessfully",
					"SucessFully validatedEvaluation Details page" + " ", "Pass", "Y");
			log.info("completed executing the method:: validateEvaluationDetailsPageAllBidderDetails");

		} catch (com.baseClasses.AutomationException e) {
			log.fatal("Not able to validatedEvaluation Details page" + e.getMessage());
			pdfResultReport.addStepDetails("validateEvaluationDetailsPageAllBidderDetails",
					"Evaluation Details page must be validated sucessfully",
					"Unable to validatedEvaluation Details page" + e.getMessage(), "Fail", "N");
		}
	}

	/**
	 * 
	 * @throws Throwable
	 */
	public void bidDetailsValidationInBidDetailsPageFormultipleBiddersForCover2(String Bidderuser, String biddercompany)
			throws Throwable {
		try {
			log.info("started executing the method:: bidDetailsValidationInBidDetailsPageFormultipleBiddersForCover2");

			IsElementPresent(By.xpath("//*[contains(normalize-space(text()), '" + Bidderuser.toUpperCase() + "')]"));

			IsElementPresent(By.xpath("//*[normalize-space(text())='Pending']/../../child::td[contains(text(),'"
					+ biddercompany + "')]"));

			pdfResultReport.addStepDetails("bidDetailsValidationInBidDetailsPageFormultipleBiddersForCover2",
					"bidder details must be validate successfully", "Successfully validated bidder details" + " ",
					"Pass", "Y");
			log.info(
					"completed executing the method:: bidDetailsValidationInBidDetailsPageFormultipleBiddersForCover2");
		} catch (Exception e) {
			log.fatal("Not able validate bidder details" + e.getMessage());
			pdfResultReport.addStepDetails("bidDetailsValidationInBidDetailsPageFormultipleBiddersForCover2",
					"bidder details must be validate successfully",
					"Unable to validate bidder details" + e.getMessage(), "Fail", "N");
		}
	}

	/**
	 * @author venkatesh jujjuru
	 * @throws Throwable
	 */

	//TC Submit the decrypted bid (12/01/2020)
	public void submitBidDetailPage() throws Throwable {
		try {
			log.info("started executing the method:: submitBidDetailPage");
			click(tendercreationlocators.submitbutton1, "submitbutton1");
			waitForElementToBeVisible(tendercreationlocators.defaultCatBy);
			waitForObj(3000);
			pdfResultReport.addStepDetails("submitBidDetailPage", "bidder must be submit decrypted bid successfully",
					"Successfully submit decrypted bid" + " ", "Pass", "Y");
			log.info("completed executing the method:: submitBidDetailPage");
		} catch (Exception e) {
			log.fatal("Not able to submit decrypted bid" + e.getMessage());
			pdfResultReport.addStepDetails("submitBidDetailPage", "bidder must be submit decrypted bid successfully",
					"Unable to submit decrypted bid" + e.getMessage(), "Fail", "N");
		}
	}

	public void checkTheStatusOfTheBidder(String Bidderuser) throws Throwable {
		try {
			log.info("started executing the method:: checkTheStatusOfTheBiddercheckTheStatusOfTheBidder");

			String status = "//*[contains(normalize-space(text()), '{0}')]//following-sibling::td[4]";

			String text = text(By.xpath(status.replace("{0}", Bidderuser)));

			if (text.equalsIgnoreCase("Qualified in bid part 1"))
				System.out.println(Bidderuser + "Status is Qualified in bid part 1 ");
			else
				System.out.println(Bidderuser + "Status is disQualified in bid part 1");

			pdfResultReport.addStepDetails("checkTheStatusOfTheBidder", "bidder status should  checked sucessfully",
					"Successfully validated bidder status" + " ", "Pass", "Y");
			log.info("completed executing the method:: checkTheStatusOfTheBidder");
		} catch (Exception e) {
			log.fatal("Not able to get the status of bidder" + e.getMessage());
			pdfResultReport.addStepDetails("checkTheStatusOfTheBidder", "bidder status must be validate successfully",
					"Unable to  check bidder status " + e.getMessage(), "Fail", "N");
		}
	}

	public void approveOrRejectValidations1() throws Throwable {
		try {
			log.info("started executing the method:: approveOrRejectValidations");
			WebElement saveButton = ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.save);
			boolean paramBoolean = true;
			click(tendercreationlocators.approveOrRejectEditableLink, "approveOrRejectEditableLink");
			click(tendercreationlocators.rejectRadioButton, "rejectRadioButton");
			verifyElementIsEnabled(saveButton, paramBoolean);
			set(tendercreationlocators.commentText, pdfResultReport.testData.get("overAllComment"), "commentText");
			verifyElementIsEnabled(saveButton, paramBoolean);
			// click(tendercreationlocators.approveRadioButton,
			// "approveRadioButton");
			set(tendercreationlocators.upload_editable,
					System.getProperty("user.dir") + "\\MediaFiles\\rfqCreation1.xlsx", "fileName");
			set(tendercreationlocators.commentText, pdfResultReport.testData.get("overAllComment"), "commentText");
			click(tendercreationlocators.save, "save");
			waitForObj(5000);
			IsElementPresent(tendercreationlocators.alertPopUp_CTSreject);
			click(tendercreationlocators.alertCloseButton, "alertCloseButton");
			waitForObj(5000);
			// IsElementPresent(tendercreationlocators.bidderStatusApprove);
			// IsElementPresent(tendercreationlocators.bidderOverAllComment);
			// IsElementPresent(tendercreationlocators.bidderAttachment);
			// click(tendercreationlocators.submitbutton1, "submitbutton1");
			waitForObj(10000);
		} catch (Exception e) {
			log.fatal("Not able to verify the bid details" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to verify the bid details",
					"Not able to verify the bid details using approveOrRejectValidations",
					"Unable to verify the bid details using approveOrRejectValidations" + e.getMessage(), "Fail", "N");
		}
	}

	public void validateBidDetailsEachAndEveryTab3() throws Throwable {
		try {
			log.info("started executing the method:: navigateToBidDetailsEachAndEveryTab");
			click(tendercreationlocators.bidEvaluationCommentTabBy, "bidEvaluationCommentTabBy");
			waitForObj(3000);
			click(tendercreationlocators.technical, "technical");
			waitForObj(3000);
			click(tendercreationlocators.termsAndCondition_bidDetails, "termsAndCondition_bidDetails");
			waitForObj(2000);
			click(tendercreationlocators.generalInformation_bidDetails, "generalInformation_bidDetails");
			waitForObj(2000);
			click(tendercreationlocators.attachments_bidDetails, "attachments_bidDetails");
			waitForObj(2000);
			click(tendercreationlocators.next, "next");
			// click(tendercreationlocators.commercial_bidDetails,
			// "commercial_bidDetails");
			waitForObj(2000);
			// click(tendercreationlocators.quotationSummary_bidDetails,
			// "quotationSummary_bidDetails");
			waitForObj(2000);
			click(By.xpath("(//i[contains(@class,'remove')])[1]"), "close_bidDetails");
			waitForObj(5000);
			pdfResultReport.addStepDetails("navigateToBidDetailsEachAndEveryTab",
					"BidDetails each and every tab must be navigated successfully",
					"Successfully navigated to BidDetails each and every tab" + " ", "Pass", "Y");
			log.info("completed executing the method:: navigateToBidDetailsEachAndEveryTab");
		} catch (Exception e) {
			log.fatal("Not able to navigate To BidDetails each and every tab" + e.getMessage());
			pdfResultReport.addStepDetails("navigateToBidDetailsEachAndEveryTab",
					"BidDetails each and every tab must be navigated successfully",
					"Unable to navigate To BidDetails each and every tab" + e.getMessage(), "Fail", "N");
		}
	}

	public void approveOrRejectValidations_CTS_AUTO_02() throws Throwable {
		try {
			log.info("started executing the method:: approveOrRejectValidations");
			WebElement saveButton = ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.save);
			boolean paramBoolean = true;
			click(tendercreationlocators.approveOrRejectEditableLink, "approveOrRejectEditableLink");
			click(tendercreationlocators.rejectRadioButton, "rejectRadioButton");
			verifyElementIsEnabled(saveButton, paramBoolean);
			set(tendercreationlocators.commentText, pdfResultReport.testData.get("overAllComment"), "commentText");
			verifyElementIsEnabled(saveButton, paramBoolean);
			// click(tendercreationlocators.approveRadioButton,"approveRadioButton");
			set(tendercreationlocators.upload_editable,
					System.getProperty("user.dir") + "\\MediaFiles\\rfqCreation1.xlsx", "fileName");
			set(tendercreationlocators.commentText, pdfResultReport.testData.get("overAllComment"), "commentText");
			click(tendercreationlocators.save, "save");
			waitForObj(5000);
			IsElementPresent(tendercreationlocators.alertPopUp_CTSreject);
			click(tendercreationlocators.alertCloseButton, "alertCloseButton");
			waitForObj(5000);
			IsElementPresent(tendercreationlocators.bidderStatusRejected);
			IsElementPresent(tendercreationlocators.bidderOverAllComment2);
			waitForObj(5000);
			IsElementPresent(tendercreationlocators.bidderAttachment2);
			// click(tendercreationlocators.submitbutton,"submitbutton");
			waitForObj(20000);
		} catch (Exception e) {
			log.fatal("Not able to verify the bid details" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to verify the bid details",
					"Not able to verify the bid details using approveOrRejectValidations",
					"Unable to verify the bid details using approveOrRejectValidations" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickBidDetailsSection1() throws Throwable {
		try {
			log.info("started executing the method:: navigateToBidDetailsSection");

			click(By.xpath("//button[@data-toggle='dropdown']//following-sibling::ul/li[1]/a"), "bidDetails");
			waitForObj(5000);
			pdfResultReport.addStepDetails("navigateToBidDetailsSection",
					"BidDetails Section must be navigated successfully",
					"Successfully navigated to BidDetails Section" + " ", "Pass", "Y");
			log.info("completed executing the method:: navigateToBidDetailsSection");
		} catch (Exception e) {
			log.fatal("Not able to navigate To BidDetails Section" + e.getMessage());
			pdfResultReport.addStepDetails("navigateToBidDetailsSection",
					"BidDetails Section must be navigated successfully",
					"Unable to navigate To BidDetails Section" + e.getMessage(), "Fail", "N");
		}
	}

	public void approveOrRejectValidations_CTS_AUTO_01WithApproveAll() throws Throwable {
		try {
			log.info("started executing the method:: approveOrRejectValidations_CTS_AUTO_01WithApproveAll");
			WebElement saveButton = ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.save);
			boolean paramBoolean = true;
			click(tendercreationlocators.approveOrRejectEditableLink, "approveOrRejectEditableLink");
			click(tendercreationlocators.rejectRadioButton, "rejectRadioButton");
			verifyElementIsEnabled(saveButton, paramBoolean);
			set(tendercreationlocators.commentText, pdfResultReport.testData.get("overAllComment"), "commentText");
			verifyElementIsEnabled(saveButton, paramBoolean);
			click(tendercreationlocators.approveRadioButton, "approveRadioButton");
			set(tendercreationlocators.upload_editable,
					System.getProperty("user.dir") + "\\MediaFiles\\rfqCreation1.xlsx", "fileName");
			set(tendercreationlocators.commentText, pdfResultReport.testData.get("overAllComment"), "commentText");
			click(tendercreationlocators.save, "save");
			waitForObj(5000);
			IsElementPresent(tendercreationlocators.alertPopUp_CTS_AUTO_01);
			click(tendercreationlocators.alertCloseButton, "alertCloseButton");
			waitForObj(5000);
			// IsElementPresent(tendercreationlocators.bidderStatusApprove);
			IsElementPresent(tendercreationlocators.bidderOverAllComment2);
			click(tendercreationlocators.approveAll, "approveAll");
			waitForObj(5000);
			click(tendercreationlocators.approveAllOkButton, "approveAllOkButton");
			waitForObj(20000);
		} catch (Exception e) {
			log.fatal("Not able to verify the bid details" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to verify the bid details",
					"Not able to verify the bid details using approveOrRejectValidations",
					"Unable to verify the bid details using approveOrRejectValidations" + e.getMessage(), "Fail", "N");
		}
	}

	public void validateBidDetailsEachAndEveryTab4() throws Throwable {
		try {
			log.info("started executing the method:: navigateToBidDetailsEachAndEveryTab");
			click(tendercreationlocators.bidEvaluationCommentTabBy, "bidEvaluationCommentTabBy");
			waitForObj(3000);
			click(tendercreationlocators.technical, "technical");
			waitForObj(3000);
			click(tendercreationlocators.termsAndCondition_bidDetails, "termsAndCondition_bidDetails");
			waitForObj(2000);
			click(tendercreationlocators.generalInformation_bidDetails, "generalInformation_bidDetails");
			waitForObj(2000);
			click(tendercreationlocators.attachments_bidDetails, "attachments_bidDetails");
			waitForObj(2000);
			click(tendercreationlocators.next, "next");
			click(tendercreationlocators.commercial_bidDetails, "commercial_bidDetails");
			waitForObj(2000);
			click(tendercreationlocators.quotationSummary_bidDetails, "quotationSummary_bidDetails");
			waitForObj(2000);
			click(By.xpath("(//i[contains(@class,'remove')])[1]"), "close_bidDetails");
			waitForObj(5000);
			pdfResultReport.addStepDetails("navigateToBidDetailsEachAndEveryTab",
					"BidDetails each and every tab must be navigated successfully",
					"Successfully navigated to BidDetails each and every tab" + " ", "Pass", "Y");
			log.info("completed executing the method:: navigateToBidDetailsEachAndEveryTab");
		} catch (Exception e) {
			log.fatal("Not able to navigate To BidDetails each and every tab" + e.getMessage());
			pdfResultReport.addStepDetails("navigateToBidDetailsEachAndEveryTab",
					"BidDetails each and every tab must be navigated successfully",
					"Unable to navigate To BidDetails each and every tab" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickRejectBtnAndCheckTenderIsDisplayingOrNotInTenderOpeningTab() throws Exception {
		try {

			log.info("started executing the method:: clickRejectBtnAndCheckTenderIsDisplayingOrNotInTenderOpeningTab");

			click(tendercreationlocators.rejectBtn, "rejectBtn");

			waitForElement(tendercreationlocators.tenderopeningTabBy, 20);

			click(tendercreationlocators.tenderopeningTabBy, "tenderopeningTabBy");

			set(tendercreationlocators.search, tenderReferenceNoLocatorText, "search");

			Assert.assertTrue(
					ThreadLocalWebdriver.getDriver().findElements(By.xpath("//*[@id='myTable']/tbody/tr")).size() == 0,
					"no tender is not  displayingin tender open tab pending list");
			pdfResultReport.addStepDetails("clickApproveBtnAndCheckTenderIsDisplayingOrNotInTenderOpeningTab",
					"click ApproveBtnAndCheckTender InTenderOpeningTab must be done",
					"SucessFully clicked ApproveBtn And TenderIs Not displaying  In TenderOpeningTab" + " ", "Pass",
					"Y");
			log.info(
					"completed executing the method:: clickRejectBtnAndCheckTenderIsDisplayingOrNotInTenderOpeningTab");

		} catch (Exception e) {

			log.fatal("Not able to clicked ApproveBtn" + e.getMessage());
			pdfResultReport.addStepDetails("validateBidOpeningApprovalpage",
					"click ApproveBtnAndCheckTender InTenderOpeningTab must be done",
					"Unable to  clicked ApproveBtn " + e.getMessage(), "Fail", "N");
		}

	}

	public void createTender_UVoltasDEMO_generalInformationWithBidPart1() throws Throwable {
		try {
			log.info("started executing the method:: createTender_UVoltasDEMO_generalInformation");
			waitForElement(tendercreationlocators.templateGroupdropdown, 5000);
			select(tendercreationlocators.templateGroupdropdown, "UVoltas DEMO");
			select(tendercreationlocators.bitPartdropdown, pdfResultReport.testData.get("BidParts1"));
			click(tendercreationlocators.bitPartRadio, "bitPartRadio");
			set(tendercreationlocators.tenderReferenceNumber, pdfResultReport.testData.get("TenderReferencNumber"),
					"tenderReferenceNumber");
			select(tendercreationlocators.procurementCategory, "Default_cat");
			select(tendercreationlocators.tenderCurrency, pdfResultReport.testData.get("TenderCurrency"));
			set(tendercreationlocators.probabaleAmountContract, pdfResultReport.testData.get("PAC"),
					"probabaleAmountContract");
			select(tendercreationlocators.biddingCurrency, pdfResultReport.testData.get("BiddingCurrency"));
			select(tendercreationlocators.tenderType, pdfResultReport.testData.get("TenderType"));
			set(tendercreationlocators.detailedDescription,
					pdfResultReport.testData.get("CreateTender-DetailedDescription"), "detailedDescription");
			click(tendercreationlocators.savebutton, "savebutton");
			pdfResultReport.addStepDetails("Successfully Created Tender",
					"Tender must be created successfully using createTender_UVoltasDEMO_generalInformation as template group ",
					"Tender is created successfully using createTender_UVoltasDEMO_generalInformation as template group"
							+ " ",
					"Pass", "Y");
			log.info("completed executing the method:: createTender_UVoltasDEMO_generalInformation");
		} catch (Exception e) {
			log.fatal("Not able to create tender" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to create tender",
					"Not able to create tender using createTender_UVoltasDEMO_generalInformation as template group",
					"Unable to create tender using createTender_UVoltasDEMO_generalInformation as template group"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void providingOverallCommentAndSubmit() throws Throwable {
		try {
			log.info("started executing the method:: providingOverallComment");
			set(tendercreationlocators.overallComment_currentPart, pdfResultReport.testData.get("overAllComment"),
					"overallComment_currentPart");
			waitForObj(3000);
			click(tendercreationlocators.submitbutton, "submitbutton");
			waitForObj(10000);
			pdfResultReport.addStepDetails("providingOverallComment", "OverallComment must be entered successfully",
					"Successfully entered OverallComment" + " ", "Pass", "Y");
			log.info("completed executing the method:: providingOverallComment");
		} catch (Exception e) {
			log.fatal("Not able to enter OverallComment" + e.getMessage());
			pdfResultReport.addStepDetails("providingOverallComment", "OverallComment must be entered successfully",
					"Unable to enter OverallComment" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickEditableLinkPendingStatusAndSelectApproveForTheBidder1_CTS() throws Throwable {
		try {
			log.info("started executing the method:: clickEditableLinkPendingStatusAndSelectApproveForTheBidder1_CTS");
			click(tendercreationlocators.approveOrRejectEditableLink1, "approveOrRejectEditableLink1");
			click(tendercreationlocators.approveRadioButton, "approveRadioButton");
			set(tendercreationlocators.upload_editable,
					System.getProperty("user.dir") + "\\MediaFiles\\rfqCreation1.xlsx", "fileName");
			set(tendercreationlocators.commentText, pdfResultReport.testData.get("overAllComment"), "commentText");
			click(tendercreationlocators.save, "save");
			waitForObj(5000);
			IsElementPresent(tendercreationlocators.alertPopUp_CTS_AUTO_01);
			click(tendercreationlocators.alertCloseButton, "alertCloseButton");
			waitForObj(5000);
			IsElementPresent(tendercreationlocators.bidderStatusApprove);
			IsElementPresent(tendercreationlocators.bidderOverAllComment);
			pdfResultReport.addStepDetails("clickEditableLinkPendingStatusAndSelectApproveForTheBidder1_CTS",
					"editPencilIconAndClickApproveAndSave must be validated successfully",
					"Successfully edited PencilIcon And Clicked ApproveAndSave" + " ", "Pass", "Y");
			log.info(
					"completed executing the method:: clickEditableLinkPendingStatusAndSelectApproveForTheBidder1_CTS");
		} catch (Exception e) {
			log.fatal("Not able to verify the bid details" + e.getMessage());
			pdfResultReport.addStepDetails("clickEditableLinkPendingStatusAndSelectApproveForTheBidder1_CTS",
					"editPencilIconAndClickApproveAndSave must be validated successfully",
					"Unable to editPencilIconAndClickApproveAndSave" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickEditableLinkPendingStatusAndSelectRejectForTheBidder2_TCS() throws Throwable {
		try {
			log.info("started executing the method:: clickEditableLinkPendingStatusAndSelectApproveForTheBidder1_CTS");
			click(tendercreationlocators.approveOrRejectEditableLink2, "approveOrRejectEditableLink2");
			click(tendercreationlocators.rejectRadioButton, "rejectRadioButton");
			set(tendercreationlocators.upload_editable,
					System.getProperty("user.dir") + "\\MediaFiles\\rfqCreation1.xlsx", "fileName");
			set(tendercreationlocators.commentText, pdfResultReport.testData.get("overAllComment"), "commentText");
			click(tendercreationlocators.save, "save");
			waitForObj(5000);
			IsElementPresent(tendercreationlocators.alertPopUp_TCS_AUTO_01);
			click(tendercreationlocators.alertCloseButton, "alertCloseButton");
			waitForObj(5000);
			IsElementPresent(tendercreationlocators.bidderStatusApprove);
			IsElementPresent(tendercreationlocators.bidderOverAllComment);
			pdfResultReport.addStepDetails("clickEditableLinkPendingStatusAndSelectApproveForTheBidder1_CTS",
					"editPencilIconAndClickApproveAndSave must be validated successfully",
					"Successfully edited PencilIcon And Clicked ApproveAndSave" + " ", "Pass", "Y");
			log.info(
					"completed executing the method:: clickEditableLinkPendingStatusAndSelectApproveForTheBidder1_CTS");
		} catch (Exception e) {
			log.fatal("Not able to verify the bid details" + e.getMessage());
			pdfResultReport.addStepDetails("clickEditableLinkPendingStatusAndSelectApproveForTheBidder1_CTS",
					"editPencilIconAndClickApproveAndSave must be validated successfully",
					"Unable to editPencilIconAndClickApproveAndSave" + e.getMessage(), "Fail", "N");
		}
	}

	public void providingOverallComment_currentpart() throws Throwable {
		try {
			log.info("started executing the method:: providingOverallComment_currentpart");
			set(tendercreationlocators.overallComment_currentPart, pdfResultReport.testData.get("overAllComment"),
					"overallComment_currentPart");
			click(tendercreationlocators.submitbutton1, "submitbutton1");
			waitForObj(10000);
			pdfResultReport.addStepDetails("providingOverallComment_currentpart",
					"OverallComment must be entered successfully", "Successfully entered OverallComment" + " ", "Pass",
					"Y");
			log.info("completed executing the method:: providingOverallComment_currentpart");
		} catch (Exception e) {
			log.fatal("Not able to enter OverallComment" + e.getMessage());
			pdfResultReport.addStepDetails("providingOverallComment_currentpart",
					"OverallComment must be entered successfully", "Unable to enter OverallComment" + e.getMessage(),
					"Fail", "N");
		}
	}

	public void verifyDownloadDecryptedDataBtn() throws Throwable {
		try {
			log.info("started executing the method:: verifyDownloadDecryptedDataBtn");

			String expectedUrl = "https://epsnewprod.mjunction.in/EPSV2Web/report/getDecryptedDataforCSSReport/{xxx}.action";

			WebDriver driver = ThreadLocalWebdriver.getDriver();

			boolean enabled = driver.findElement(tendercreationlocators.downloadDecryptBtnBy).isEnabled();

			Assert.assertTrue(enabled, "Download Decrypted Data btn is enabled");

			click(tendercreationlocators.downloadDecryptBtnBy, "downloadDecryptBtnBy");

			List<WebElement> dropdownValues = driver.findElements(tendercreationlocators.downloadDecryptLinkDropdownBy);

			// Before clicking parent window id
			String parentwindow = driver.getWindowHandle();

			int i = 1;
			for (WebElement dropdownvalue : dropdownValues) {

				dropdownvalue.click();

				// after clicking dropdown value all windows id
				Set<String> windowHandles = driver.getWindowHandles();

				Object[] array = windowHandles.toArray();

				driver.switchTo().window(array[1].toString());

				String currentUrl = driver.switchTo().window(array[1].toString()).getCurrentUrl();

				System.out.println(currentUrl);

				Assert.assertEquals(currentUrl, expectedUrl.replace("{xxx}", String.valueOf(i)));

				driver.close();

				waitForObj(3000);

				driver.switchTo().window(parentwindow);

				i++;

				if (i == 5)
					break;

				click(tendercreationlocators.downloadDecryptBtnBy, "downloadDecryptBtnBy");

			}

			pdfResultReport.addStepDetails("verifyDownloadDecryptedDataBtn",
					"DownloadDecrypted Data Btn must be validated  successfully",
					"Successfully validated Download Decrypted Data Btn" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyDownloadDecryptedDataBtn");
		} catch (Exception e) {
			log.fatal("Not able to decrypting the bidder" + e.getMessage());
			pdfResultReport.addStepDetails("verifyDownloadDecryptedDataBtn",
					"DownloadDecrypted Data Btn must be validated  successfully",
					"Unable to validate Download Decrypted Data Btn" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickActiondropDownAndVerifyCsReportLinkBtn(String formatFile) throws Throwable {
		try {
			log.info("started executing the method:: clickActiondropDownAndVerifyCsReportLinkBtn");

			By csReportlinkBy = By.xpath("//*[@ng-click='csReport(tender.tenderid)']");

			click(tendercreationlocators.action, "action");

			WebDriver driver = ThreadLocalWebdriver.getDriver();

			WebDriverWait wait = new WebDriverWait(driver, 60);

			wait.until(ExpectedConditions.visibilityOfElementLocated(csReportlinkBy));

			click(csReportlinkBy, "csReportlinkBy");

			waitForObj(2000);

			select(By.xpath("//select[@ng-model='checkedBidParts.type']"), formatFile);

			click(By.xpath(
					"//select[@ng-model='checkedBidParts.type']//following-sibling::table/tbody/tr/td//input[@type='checkbox']"),
					"select check box");

			String parentwindow = driver.getWindowHandle();

			click(By.xpath("//*[@id='myModalCSReport']//button[text()='Submit']"), "submit");

			Set<String> windowHandles = driver.getWindowHandles();

			Object[] array = windowHandles.toArray();

			driver.switchTo().window(array[1].toString());

			String currentUrl = driver.switchTo().window(array[1].toString()).getCurrentUrl();

			System.out.println(currentUrl);
			String expectedUrl = "https://epsnewprod.mjunction.in/EPSV2Web/report/getGeneratedCSSReport.action";
			Assert.assertEquals(currentUrl.trim(), expectedUrl.trim());

			waitForObj(3000);

			driver.close();

			waitForObj(3000);

			driver.switchTo().window(parentwindow);

			pdfResultReport.addStepDetails("clickActiondropDownAndVerifyCsReportLinkBtn",
					"verify in tender list page of specific tender cs report must be valiadted sucessfully",
					"Successfully validated in tender list page of specific tender cs report" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickActiondropDownAndVerifyCsReportLinkBtn");
		} catch (Exception e) {
			log.fatal("Not able to specific tender cs report link" + e.getMessage());
			pdfResultReport.addStepDetails("clickActiondropDownAndVerifyCsReportLinkBtn",
					"verify in tender list page of specific tender cs report must be valiadted sucessfully",
					"Unable to validate specific tender cs report link" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickInitiateNegotiationLink() throws Throwable {
		try {
			log.info("started executing the method:: clickInitiateNegotiationLink");
			click(tendercreationlocators.action, "action");
			waitForObj(5000);
			click(tendercreationlocators.initiateNegotiation, "initiateNegotiation");
			waitForElementToBeVisible(tendercreationlocators.Negotiation_detail);	
			waitForObj(1000);
			pdfResultReport.addStepDetails("clickInitiateNegotiationLink",
					"Initiate Negotiation Link must be clicked successfully",
					"Successfully clicked on Initiate Negotiation Link" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickInitiateNegotiationLink");
		} catch (Exception e) {
			log.fatal("Not able to click on Initiate Negotiation Link" + e.getMessage());
			pdfResultReport.addStepDetails("clickInitiateNegotationLink",
					"Initiate Negotiation Link must be clicked successfully",
					"Unable to click on Initiate Negotiation Link " + e.getMessage(), "Fail", "N");
		}
	}

	public void negotiationDetailPageValidation() throws Throwable {
		try {
			log.info("started executing the method:: negotiationDetailPageValidation");
			IsElementPresent(tendercreationlocators.negotiationBidSubmissionStartDate);
			IsElementPresent(tendercreationlocators.negotiationBidSubmissionEndDate);
			IsElementPresent(tendercreationlocators.tenderID_Negotiation);
			IsElementPresent(tendercreationlocators.evaluatorName);
			IsElementPresent(tendercreationlocators.bidPartOrCoverNumber);
			IsElementPresent(tendercreationlocators.subProcessName);
			IsElementPresent(tendercreationlocators.bidderCode);
			IsElementPresent(tendercreationlocators.bidderCode_CTS);
			IsElementPresent(tendercreationlocators.bidderCode_TCS);
			IsElementPresent(tendercreationlocators.bidderCode_TECH);
			IsElementPresent(tendercreationlocators.bidder_Name);
			IsElementPresent(tendercreationlocators.bidder_Name_CTS);
			IsElementPresent(tendercreationlocators.bidder_Name_TCS);
			IsElementPresent(tendercreationlocators.bidder_Name_TECH);
			IsElementPresent(tendercreationlocators.company);
			IsElementPresent(tendercreationlocators.company_CTS);
			IsElementPresent(tendercreationlocators.company_TCS);
			IsElementPresent(tendercreationlocators.company_TECH);
			IsElementPresent(tendercreationlocators.biddercomment);
			IsElementPresent(tendercreationlocators.status);
			scrollToTopOfThePage();
			//IsElementPresent(tendercreationlocators.status_Rejected);
			//IsElementPresent(tendercreationlocators.status_Approved);
			//IsElementPresent(tendercreationlocators.status_Negotiation);
			pdfResultReport.addStepDetails("negotiationDetailPageValidation",
					"negotiation Detail Page must be validated successfully",
					"Successfully validated negotiation Detail Page" + " ", "Pass", "Y");
			log.info("completed executing the method:: negotiationDetailPageValidation");
		} catch (Exception e) {
			log.fatal("Not able to validate negotiation Detail Page" + e.getMessage());
			pdfResultReport.addStepDetails("negotiationDetailPageValidation",
					"negotiation Detail Page must be validated successfully",
					"Unable to validate negotiation Detail Page " + e.getMessage(), "Fail", "N");
		}
	}

	public void negotiation_dateSchedule(int startdatelag, int enddatelag) throws Throwable {
		try {
			log.info("started executing the method:: negotiation_dateSchedule");

			set(tendercreationlocators.negotiationBidSubmissionStartDate, getBidStartDate(startdatelag),
					"negotiationBidSubmissionStartDate");
			set(tendercreationlocators.negotiationBidSubmissionEndDate, getBidDueDate(enddatelag),
					"negotiationBidSubmissionEndDate");
			click(tendercreationlocators.submitbutton, "submitbutton");
			waitForElementToBeVisible(tendercreationlocators.defaultCatBy);
			waitForObj(5000);
			pdfResultReport.addStepDetails("negotiation_dateSchedule",
					"Negotiation bid submission start date and end date must be entered successfully",
					"Successfully entered Negotiation bid submission start date and end date " + " ", "Pass", "Y");
			log.info("completed executing the method:: createTender_UVoltasDEMO_dateSchedule");
		} catch (Exception e) {
			log.fatal("Not able to enter Negotiation bid submission start date and end date" + e.getMessage());
			pdfResultReport.addStepDetails("negotiation_dateSchedule",
					"Negotiation bid submission start date and end date must be entered successfully",
					"Unable to enter Negotiation bid submission start date and end date" + e.getMessage(), "Fail", "N");
		}
	}

	public void negotiation_dateSchedule2(int enddatelag) throws Throwable {
		try {
			log.info("started executing the method:: negotiation_dateSchedule");

			// set(tendercreationlocators.negotiationBidSubmissionStartDate,
			// getBidStartDate(startdatelag),
			// "negotiationBidSubmissionStartDate");
			set(tendercreationlocators.negotiationBidSubmissionEndDate, getBidDueDate(enddatelag),
					"negotiationBidSubmissionEndDate");
			click(tendercreationlocators.submitbutton, "submitbutton");
			waitForObj(5000);
			pdfResultReport.addStepDetails("negotiation_dateSchedule",
					"Negotiation bid submission start date and end date must be entered successfully",
					"Successfully entered Negotiation bid submission start date and end date " + " ", "Pass", "Y");
			log.info("completed executing the method:: createTender_UVoltasDEMO_dateSchedule");
		} catch (Exception e) {
			log.fatal("Not able to enter Negotiation bid submission start date and end date" + e.getMessage());
			pdfResultReport.addStepDetails("negotiation_dateSchedule",
					"Negotiation bid submission start date and end date must be entered successfully",
					"Unable to enter Negotiation bid submission start date and end date" + e.getMessage(), "Fail", "N");
		}
	}

	public void negotiationTenderStatusAndTenderStage() throws Throwable {
		try {
			log.info("started executing the method:: negotiationTenderStatusAndTenderStage");
			IsElementPresent(tendercreationlocators.tenderStatus);
			IsElementPresent(tendercreationlocators.cover1Negotiation);
			waitForObj(500);
			pdfResultReport.addStepDetails("negotiationTenderStatusAndTenderStage",
					"Tender status must be shown as Evaluation and tender stage as Cover1 Negotiation",
					"Successfully shown Tender status as Evaluation and tender stage as Cover1 Negotiation" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: negotiationTenderStatusAndTenderStage");
		} catch (Exception e) {
			log.fatal("Unable to show the status as Evaluation and stage as Cover1 Negotiation" + e.getMessage());
			pdfResultReport.addStepDetails("negotiationTenderStatusAndTenderStage",
					"Tender status must be shown as Evaluation and tender stage as Cover1 Negotiation",
					"Unable to show tender status as Evaluation and tender stage as Cover1 Negotiation"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void negotiatedBidderSubmission() throws Throwable {
		try {
			log.info("started executing the method:: negotiatedBidderSubmission");
			click(tendercreationlocators.action, "action");
			click(tendercreationlocators.negotiation, "negotiation");
			//Viewing Technical tab	
			waitForObj(2000);
			waitForSpinnerToDisappearInBidSubmission();
			waitForElementToBeVisible(tendercreationlocators.technicalTabFileuploadEleBy);
		//clicking on Terms and conditions tab	
			click(tendercreationlocators.termsAndCondition_bidSubmission, "termsAndCondition_bidSubmission");
			waitForObj(2000);
			waitForSpinnerToDisappearInBidSubmission();
			waitForElementToBeVisible(tendercreationlocators.termsAndConditionsClauseBy);
		//Clicking on General info tab and modify Quot ref code	
			click(tendercreationlocators.Geninfotab_bidsubmission, "Geninfotab_bidsubmission");
			waitForObj(2000);
			waitForElementToBeVisible(tendercreationlocators.tenderId_generalInformation_bidSubmission);
			set(tendercreationlocators.quotationReferenceCodeInput_generalInformation_bidSubmission,
					"EditedQuotRefCode",
					"quotationReferenceCodeInput_generalInformation_RebidSubmission");
		//Clicking on BOQOptional tab	
			click(tendercreationlocators.BOQOptionalTab, "BOQOptionalTab");
			waitForObj(2000);
			waitForSpinnerToDisappearInBidSubmission();
			waitForElementToBeVisible(tendercreationlocators.boqOptionalAddOptionalBy);			
		//Clicking on Attachment tab
			click(tendercreationlocators.attachementsTab, "attachementsTab");
			waitForObj(2000);
			waitForSpinnerToDisappearInBidSubmission();	
			click(tendercreationlocators.attachment_subtab, "Attachment_subtab");
			waitForElementToBeVisible(tendercreationlocators.addtenderattachmenticon);			
		//Clicking on Commercial tab	
			click(tendercreationlocators.commercial_bidSubmission, "commercial_bidSubmission");
			waitForObj(2000);
			waitForSpinnerToDisappear();
			waitForElementToBeVisible(tendercreationlocators.documentChargeFieldEleBy);	
		//Clicking on BOQ Mandatory tab	
			click(tendercreationlocators.BOQmandatoryTab, "BOQmandatoryTab");
			waitForObj(2000);
			waitForSpinnerToDisappearInBidSubmission();
			waitForElementToBeVisible(tendercreationlocators.boqaddOptionItemButtonBy);
		//Clicking on save button and alert verification
			click(tendercreationlocators.savebutton, "savebutton");
			waitForObj(2000);
			waitForSpinnerToDisappearInBidSubmission();
			waitForElementToBeVisible(tendercreationlocators.alertPopUp_QRC_bidSubmission);
			IsElementPresent(tendercreationlocators.alertPopUp_QRC_bidSubmission);
			IsElementPresent(tendercreationlocators.boqOptionalAlertTab_BidSubmissionBy);
			IsElementPresent(tendercreationlocators.technicalAlertTab_bidSubmission);
			IsElementPresent(tendercreationlocators.termsAndConditionAlertTab_bidSubmission);
			IsElementPresent(tendercreationlocators.commercialAlertTab_bidSubmission);
			waitForObj(2000);
			click(tendercreationlocators.alertClose_QRC_bidSubmission, "alertClose_QRC_bidSubmission");
		//Clicking on Submit button	
			waitForObj(2000);
			click(tendercreationlocators.submitbutton, "submitbutton");
			waitForObj(2000);
			waitForSpinnerToDisappearInBidSubmission();
			waitForElementToBeVisible(tendercreationlocators.previewAllTittle);
			waitForObj(2000);
		//Preview all page validation
			validateAllInPrevieAllSubmitbidPage();
		//Submit bid and verify success message
			submitBid_link_in_previewAllPage();
			pdfResultReport.addStepDetails("negotiatedBidderSubmission", "BidSubmission must be done successfully",
					"Successfully done BidSubmission" + " ", "Pass", "Y");
			log.info("completed executing the method:: negotiatedBidderSubmission");
		} catch (Exception e) {
			log.fatal("Not able to Bid Submission" + e.getMessage());
			pdfResultReport.addStepDetails("negotiatedBidderSubmission", "BidSubmission must be done successfully",
					"Unable to do Bid Submission" + e.getMessage(), "Fail", "N");
		}
	}
	//Negotiated bid submission for TG1(date: 24/02/2020)
	public void TG1_negotiatedBidderSubmission() throws Throwable {
		try {
			log.info("started executing the method:: TG1_negotiatedBidderSubmission");
			click(tendercreationlocators.action, "action");
			click(tendercreationlocators.negotiation, "negotiation");
			waitForObj(5000);
			waitForSpinnerToDisappearInBidSubmission();
			waitForElementToBeVisible(tendercreationlocators.TermsandConditionstabLnk_BidSubmission_TG1);
			//Clicking on Next link to get the remaining tab
			click(tendercreationlocators.NextLnk_Tender_TG1, "NextLnk_Tender_TG1");
			waitForElementToBeVisible(tendercreationlocators.CommercialComptabLnk_BidSubmission_TG1);
			//Clicking on Next link to get the remaining tab
			click(tendercreationlocators.NextLnk_Tender_TG1, "NextLnk_Tender_TG1");
		//Verifying General Requirement Equipment details tab++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			waitForElementToBeClickable(tendercreationlocators.GeneralReqEqiptabLnk_BidSubmission_TG1);
			click(tendercreationlocators.GeneralReqEqiptabLnk_BidSubmission_TG1, "GeneralReqEqiptabLnk_BidSubmission_TG1");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForElementToBeVisible(tendercreationlocators.Address_GeneralReqEqiptab_BidSubmission_TG1);
			waitForObj(500);
		//Verifying Payment tab+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			waitForElementToBeClickable(tendercreationlocators.PaymenttabLnk_BidSubmission_TG1);
			click(tendercreationlocators.PaymenttabLnk_BidSubmission_TG1, "PaymenttabLnk_BidSubmission_TG1");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForElementToBeVisible(tendercreationlocators.Paymentbtn_Paymenttab_BidSubmission_TG1);
			waitForObj(500);
		//Verifying RFQ Item tab+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			waitForElementToBeClickable(tendercreationlocators.RFQItemtabLnk_BidSubmission_TG1);
			click(tendercreationlocators.RFQItemtabLnk_BidSubmission_TG1, "RFQItemtabLnk_BidSubmission_TG1");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForElementToBeVisible(tendercreationlocators.UnitRateTxt_RFQItemtab_BidSubmission_TG1);
			waitForObj(500);
			clear(tendercreationlocators.UnitRateTxt_RFQItemtab_BidSubmission_TG1,"UnitRateTxt_RFQItemtab_BidSubmission_TG1");
			set(tendercreationlocators.UnitRateTxt_RFQItemtab_BidSubmission_TG1, pdfResultReport.testData.get("UnitRateTxt_RFQItemtab_NegoBidSubmission_TG1"), "UnitRateTxt_RFQItemtab_BidSubmission_TG1");
		//Verifying BOQ Mandatory tab+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			waitForElementToBeClickable(tendercreationlocators.BOQMandatorytabLnk_BidSubmission_TG1);
			click(tendercreationlocators.BOQMandatorytabLnk_BidSubmission_TG1, "BOQMandatorytabLnk_BidSubmission_TG1");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForElementToBeVisible(tendercreationlocators.UnitRateTxt_BOQMandatorytab_BidSubmission_TG1);
			waitForObj(500);
			clear(tendercreationlocators.UnitRateTxt_BOQMandatorytab_BidSubmission_TG1,"UnitRateTxt_BOQMandatorytab_BidSubmission_TG1");
			set(tendercreationlocators.UnitRateTxt_BOQMandatorytab_BidSubmission_TG1, pdfResultReport.testData.get("UnitRateTxt_BOQMandatorytab_NegoBidSubmission_TG1"), "UnitRateTxt_BOQMandatorytab_BidSubmission_TG1");
			//Clicking on Submit button	
			click(tendercreationlocators.submitbutton, "submitbutton");
			waitForObj(4000);
			waitForSpinnerToDisappearInBidSubmission();
			waitForElementToBeVisible(tendercreationlocators.previewAllTittle);
			waitForObj(2000);
		//Preview all page validation
			TG1_validateAllInPrevieAllSubmitbidPage();
		//Submit bid and verify success message
			submitBid_link_in_previewAllPage();

			pdfResultReport.addStepDetails("TG1_negotiatedBidderSubmission", "Negotiated BidSubmission must be done successfully",
					"Successfully done negotiated BidSubmission" + " ", "Pass", "Y");
			log.info("completed executing the method:: TG1_negotiatedBidderSubmission");
		} catch (Exception e) {
			log.fatal("Not able to Bid Submission" + e.getMessage());
			pdfResultReport.addStepDetails("TG1_negotiatedBidderSubmission", "Negotiated BidSubmission must be done successfully",
					"Unable to do negotiated Bid Submission" + e.getMessage(), "Fail", "N");
		}
	}
	//Negotiated bid submission for TG3(date: 19/03/2020)
	public void TG3_negotiatedBidderSubmission() throws Throwable {
		try {
			log.info("started executing the method:: TG3_negotiatedBidderSubmission");
			click(tendercreationlocators.action, "action");
			click(tendercreationlocators.negotiation, "negotiation");
			waitForObj(5000);
			waitForSpinnerToDisappearInBidSubmission();
		//Verifying Commercial Terms and Conditions tab++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForElementToBeVisible(tendercreationlocators.CommercialTermsConditionstabLnk_BidSubmission_TG3);
			waitForElementToBeVisible(tendercreationlocators.ClauseTxt_CommercialTermsConditionstab_BidSubmission_TG3);
			waitForObj(500);
		//Verifying Terms and Conditions tab+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			waitForElementToBeClickable(tendercreationlocators.TermsConditionstabLnk_BidSubmission_TG3);
			click(tendercreationlocators.TermsConditionstabLnk_BidSubmission_TG3, "TermsConditionstabLnk_BidSubmission_TG3");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForElementToBeVisible(tendercreationlocators.ClauseTxt_TermsConditionstab_BidSubmission_TG3);
			waitForObj(500);
		//Verifying Price Format tab+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			waitForElementToBeClickable(tendercreationlocators.PriceFormattabLnk_BidSubmission_TG3);
			click(tendercreationlocators.PriceFormattabLnk_BidSubmission_TG3, "PriceFormattabLnk_BidSubmission_TG3");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForElementToBeVisible(tendercreationlocators.Mandatoryitemlbl_PriceFormattab_BidSubmission_TG3);
			waitForObj(500);
			scrollToElement(tendercreationlocators.OverallDiscountTxt_PriceFormattab_BidSubmission_TG3);
			clear(tendercreationlocators.OverallDiscountTxt_PriceFormattab_BidSubmission_TG3,"OverallDiscountTxt_PriceFormattab_BidSubmission_TG3");
			set(tendercreationlocators.OverallDiscountTxt_PriceFormattab_BidSubmission_TG3, pdfResultReport.testData.get("OverallDiscountTxt_PriceFormattab_NegoBidSubmission_TG3"), "OverallDiscountTxt_PriceFormattab_BidSubmission_TG3");
			scrollToTopOfThePage();
		//Verifying Price Summary tab+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			waitForElementToBeClickable(tendercreationlocators.PriceSummarytabLnk_BidSubmission_TG3);
			click(tendercreationlocators.PriceSummarytabLnk_BidSubmission_TG3, "PriceSummarytabLnk_BidSubmission_TG3");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForElementToBeVisible(tendercreationlocators.IncotermsSelect_PriceSummarytab_BidSubmission_TG3);
			waitForObj(500);
		//Clicking on Submit button	
			click(tendercreationlocators.submitbutton, "submitbutton");
			waitForObj(4000);
			waitForSpinnerToDisappearInBidSubmission();
			waitForElementToBeVisible(tendercreationlocators.previewAllTittle);
			waitForObj(2000);
		//Preview all page validation
			TG3_validateAllInPrevieAllSubmitbidPage();
		//Submit bid and verify success message
			submitBid_link_in_previewAllPage();

			pdfResultReport.addStepDetails("TG3_negotiatedBidderSubmission", "Negotiated BidSubmission must be done successfully",
					"Successfully done negotiated BidSubmission" + " ", "Pass", "Y");
			log.info("completed executing the method:: TG3_negotiatedBidderSubmission");
		} catch (Exception e) {
			log.fatal("Not able to Bid Submission" + e.getMessage());
			pdfResultReport.addStepDetails("TG3_negotiatedBidderSubmission", "Negotiated BidSubmission must be done successfully",
					"Unable to do negotiated Bid Submission" + e.getMessage(), "Fail", "N");
		}
	}

	public void provideEvaluationCommentsAndConfirmationPopUpValidation() throws Throwable {
		try {
			log.info("started executing the method:: provideEvaluationCommentsAndConfirmationPopUpValidation");
			waitForObj(2000);
			set(tendercreationlocators.EvalcommentSection_Evaluation, "Sending for approval to Approval user", "EvalcommentSection_Evaluation");
			JSClick(tendercreationlocators.evaluation_sendForApproval, "evaluation_sendForApproval");
			waitForElementToBeVisible(tendercreationlocators.confirmationPopUp_YES);	
			IsElementPresent(tendercreationlocators.confirmationPopUp);
			click(tendercreationlocators.confirmationPopUp_YES, "confirmationPopUp_YES");
			waitForObj(2000);
			waitForElementToBeVisible(tendercreationlocators.defaultCatBy);
			pdfResultReport.addStepDetails("provideEvaluationCommentsAndConfirmationPopUpValidation",
					"Evaluation comment must be enter and Confirmation PopUp must be validated successfully",
					"Successfully entered evaluation comment and validated Confirmation PopUp" + " ", "Pass", "Y");
			log.info("completed executing the method:: provideEvaluationCommentsAndConfirmationPopUpValidation");
		} catch (Exception e) {
			log.fatal("Not able enter evaluation comment and validate Confirmation PopUp" + e.getMessage());
			pdfResultReport.addStepDetails("provideEvaluationCommentsAndConfirmationPopUpValidation",
					"Evaluation comment must be enter and Confirmation PopUp must be validated successfully",
					"Unable to enter evaluation comment and validated Confirmation PopUp" + e.getMessage(), "Fail", "N");
		}
	}
	//submit evaluation settings page during evaluation after negotiation bid submission(15/01/2021)
	public void Submitevalsettings_ConfirmationPopUpValidation() throws Throwable {
		try {
			log.info("started executing the method:: provideEvaluationCommentsAndConfirmationPopUpValidation");
			waitForObj(2000);
			JSClick(tendercreationlocators.evaluation_sendForApproval, "evaluation_sendForApproval");
			waitForElementToBeVisible(tendercreationlocators.confirmationPopUp_YES);	
			IsElementPresent(tendercreationlocators.confirmationPopUp);
			click(tendercreationlocators.confirmationPopUp_YES, "confirmationPopUp_YES");
			waitForObj(2000);
			waitForElementToBeVisible(tendercreationlocators.defaultCatBy);
			pdfResultReport.addStepDetails("provideEvaluationCommentsAndConfirmationPopUpValidation",
					"Evaluation comment must be enter and Confirmation PopUp must be validated successfully",
					"Successfully entered evaluation comment and validated Confirmation PopUp" + " ", "Pass", "Y");
			log.info("completed executing the method:: provideEvaluationCommentsAndConfirmationPopUpValidation");
		} catch (Exception e) {
			log.fatal("Not able enter evaluation comment and validate Confirmation PopUp" + e.getMessage());
			pdfResultReport.addStepDetails("provideEvaluationCommentsAndConfirmationPopUpValidation",
					"Evaluation comment must be enter and Confirmation PopUp must be validated successfully",
					"Unable to enter evaluation comment and validated Confirmation PopUp" + e.getMessage(), "Fail", "N");
		}
	}

	// Vamshi
	public void TCSApproveValidations() throws Throwable {
		try {
			log.info("started executing the method:: TCSApproveValidations");
			WebElement saveButton = ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.save);
			boolean paramBoolean = true;
			click(tendercreationlocators.approveOrRejectEditableLink_TCS, "approveOrRejectEditableLink_TCS");
			click(tendercreationlocators.rejectRadioButton, "rejectRadioButton");
			verifyElementIsEnabled(saveButton, paramBoolean);
			set(tendercreationlocators.commentText, pdfResultReport.testData.get("overAllComment"), "commentText");
			verifyElementIsEnabled(saveButton, paramBoolean);
			click(tendercreationlocators.approveRadioButton, "approveRadioButton");
			set(tendercreationlocators.upload_editable,
					System.getProperty("user.dir") + "\\MediaFiles\\rfqCreation1.xlsx", "fileName");
			set(tendercreationlocators.commentText, pdfResultReport.testData.get("overAllComment"), "commentText");
			click(tendercreationlocators.save, "save");
			waitForObj(5000);
			IsElementPresent(tendercreationlocators.alertPopUp);
			click(tendercreationlocators.alertCloseButton, "alertCloseButton");
			IsElementPresent(tendercreationlocators.bidderStatus_Approved);
			IsElementPresent(tendercreationlocators.OverAllComment_TCS);
			IsElementPresent(tendercreationlocators.attachment_TCS);

			waitForObj(5000);
		} catch (Exception e) {
			log.fatal("Not able to approve TCS validations" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to verify the bid details",
					"Not able to approve TCS validations using TCSApproveValidations",
					"Unable to approve TCS validations using TCSApproveValidations" + e.getMessage(), "Fail", "N");
		}
	}

	public void CTSRejectValidations() throws Throwable {
		try {
			log.info("started executing the method:: CTSRejectValidations");
			WebElement saveButton = ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.save);
			boolean paramBoolean = true;
			click(tendercreationlocators.approveOrRejectEditableLink_CTS, "approveOrRejectEditableLink_CTS");
			click(tendercreationlocators.rejectRadioButton, "rejectRadioButton");
			verifyElementIsEnabled(saveButton, paramBoolean);
			set(tendercreationlocators.commentText, pdfResultReport.testData.get("overAllComment"), "commentText");
			verifyElementIsEnabled(saveButton, paramBoolean);
			// click(tendercreationlocators.approveRadioButton,
			// "approveRadioButton");
			set(tendercreationlocators.upload_editable,
					System.getProperty("user.dir") + "\\MediaFiles\\rfqCreation1.xlsx", "fileName");
			// set(tendercreationlocators.commentText,
			// pdfResultReport.testData.get("overAllComment"), "commentText");
			click(tendercreationlocators.save, "save");
			waitForObj(5000);
			IsElementPresent(tendercreationlocators.alertPopUp_CTS_AUTO_01_Reject);
			click(tendercreationlocators.alertCloseButton, "alertCloseButton");
			waitForObj(5000);
			/*
			 * IsElementPresent(tendercreationlocators.bidderStatusApprove);
			 * IsElementPresent(tendercreationlocators.bidderOverAllComment);
			 * IsElementPresent(tendercreationlocators.bidderAttachment);
			 */
			IsElementPresent(tendercreationlocators.bidderStatus_Rejected);
			IsElementPresent(tendercreationlocators.OverAllComment_CTS);
			IsElementPresent(tendercreationlocators.attachment_CTS);

			waitForObj(10000);
		} catch (Exception e) {
			log.fatal("Not able to verify the bid details" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to reject CTS bidder",
					"Not able to reject CTS bidder using CTSRejectValidations",
					"Unable to reject CTS bidder using CTSRejectValidations" + e.getMessage(), "Fail", "N");
		}
	}

	public void TechMahindraNegotiateOptionalValidations() throws Throwable {
		try {
			log.info("started executing the method:: TechMahindraNegotiateOptionalValidations");
			WebElement saveButton = ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.save);
			boolean paramBoolean = true;
			click(tendercreationlocators.approveOrRejectEditableLink_TechMahindra,
					"approveOrRejectEditableLink_TechMahindra");
			click(tendercreationlocators.rejectRadioButton, "rejectRadioButton");
			verifyElementIsEnabled(saveButton, paramBoolean);
			set(tendercreationlocators.commentText, pdfResultReport.testData.get("overAllComment"), "commentText");
			verifyElementIsEnabled(saveButton, paramBoolean);
			click(tendercreationlocators.negotiateRadioButton, "negotiateRadioButton");
			set(tendercreationlocators.upload_editable,
					System.getProperty("user.dir") + "\\MediaFiles\\rfqCreation1.xlsx", "fileName");
			set(tendercreationlocators.commentText, pdfResultReport.testData.get("overAllComment"), "commentText");
			click(tendercreationlocators.save, "save");
			waitForObj(5000);
			IsElementPresent(tendercreationlocators.alertPopUp_Tech_AUTO_01);
			click(tendercreationlocators.alertCloseButton, "alertCloseButton");
			waitForObj(5000);
			IsElementPresent(tendercreationlocators.bidderStatus_Negotiated);
			IsElementPresent(tendercreationlocators.OverAllComment_TECH);
			IsElementPresent(tendercreationlocators.attachment_TECH);
			waitForObj(10000);
		} catch (Exception e) {
			log.fatal("Not able to Negotiate Tech validations" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to Negotiate Tech validations",
					"Not able to Negotiate Tech validations using TechMahindraNegotiateOptionalValidations",
					"Unable to Negotiate Tech validations using TechMahindraNegotiateOptionalValidations"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void TechMahindraNegotiateMandatoryValidations() throws Throwable {
		try {
			log.info("started executing the method:: TechMahindraNegotiateOptionalValidations");
			WebElement saveButton = ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.save);
			boolean paramBoolean = true;
			click(tendercreationlocators.approveOrRejectEditableLink_TechMahindra,
					"approveOrRejectEditableLink_TechMahindra");
			click(tendercreationlocators.rejectRadioButton, "rejectRadioButton");
			verifyElementIsEnabled(saveButton, paramBoolean);
			set(tendercreationlocators.commentText, pdfResultReport.testData.get("overAllComment"), "commentText");
			verifyElementIsEnabled(saveButton, paramBoolean);
			click(tendercreationlocators.negotiateRadioButton, "negotiateRadioButton");
			waitForObj(2000);
			click(tendercreationlocators.negotiateMandatoryRadioButton, "negotiateMandatoryRadioButton");
			set(tendercreationlocators.upload_editable,
					System.getProperty("user.dir") + "\\MediaFiles\\rfqCreation1.xlsx", "fileName");
			set(tendercreationlocators.commentText, pdfResultReport.testData.get("overAllComment"), "commentText");
			click(tendercreationlocators.save, "save");
			waitForObj(5000);
			IsElementPresent(tendercreationlocators.alertPopUp_Tech_AUTO_01);
			click(tendercreationlocators.alertCloseButton, "alertCloseButton");
			waitForObj(5000);
			IsElementPresent(tendercreationlocators.bidderStatus_Negotiated);
			IsElementPresent(tendercreationlocators.OverAllComment_TECH);
			IsElementPresent(tendercreationlocators.attachment_TECH);
			waitForObj(10000);
		} catch (Exception e) {
			log.fatal("Not able to Negotiate Tech validations" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to Negotiate Tech validations",
					"Not able to Negotiate Tech validations using TechMahindraNegotiateMandatoryValidations",
					"Unable to Negotiate Tech validations using TechMahindraNegotiateMandatoryValidations"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void TechMahindraApproveValidations() throws Throwable {
		try {
			log.info("started executing the method:: TechMahindraApproveValidations");
			WebElement saveButton = ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.save);
			boolean paramBoolean = true;
			click(tendercreationlocators.approveOrRejectEditableLink_TechMahindra,
					"approveOrRejectEditableLink_TechMahindra");
			click(tendercreationlocators.rejectRadioButton, "rejectRadioButton");
			verifyElementIsEnabled(saveButton, paramBoolean);
			set(tendercreationlocators.commentText, pdfResultReport.testData.get("overAllComment"), "commentText");
			verifyElementIsEnabled(saveButton, paramBoolean);
			click(tendercreationlocators.approveRadioButton, "approveRadioButton");
			set(tendercreationlocators.upload_editable,
					System.getProperty("user.dir") + "\\MediaFiles\\rfqCreation1.xlsx", "fileName");
			set(tendercreationlocators.commentText, pdfResultReport.testData.get("overAllComment"), "commentText");
			click(tendercreationlocators.save, "save");
			waitForObj(5000);
			IsElementPresent(tendercreationlocators.alertPopUp_Tech_AUTO_01_Approved);
			click(tendercreationlocators.alertCloseButton, "alertCloseButton");
			waitForObj(5000);
			IsElementPresent(tendercreationlocators.bidderStatus_Approved_TECH);
			IsElementPresent(tendercreationlocators.OverAllComment_TECH);
			IsElementPresent(tendercreationlocators.attachment_TECH);
			waitForObj(10000);
		} catch (Exception e) {
			log.fatal("Not able to Approve Tech Mahindra validations" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to Approve Tech Mahindra",
					"Not able to Approve Tech Mahindra validations using TechMahindraApproveValidations",
					"Unable to Approve Tech Mahindra validations using TechMahindraApproveValidations" + e.getMessage(),
					"Fail", "N");
		}
	}

	public void decryptingAllTheBidder() throws Throwable {
		try {
			log.info("started executing the method:: decryptingAllTheBidder");
			click(tendercreationlocators.bidder_Allcheckbox, "bidder_Allcheckbox");
			click(tendercreationlocators.decryptSelected, "decryptSelected");
			waitForObj(5000);
			pdfResultReport.addStepDetails("decryptingTheBidder", "bidder must be decrypted successfully",
					"Successfully decrypted the bidder" + " ", "Pass", "Y");
			log.info("completed executing the method:: decryptingTheBidder");
		} catch (Exception e) {
			log.fatal("Not able to decrypting the bidder" + e.getMessage());
			pdfResultReport.addStepDetails("decryptingTheBidder", "bidder must be decrypted successfully",
					"Unable to decrypting the bidder" + e.getMessage(), "Fail", "N");
		}
	}

	public void negotiation_dateSchedule_pastTime(int enddatelag) throws Throwable {
		try {
			log.info("started executing the method:: negotiation_dateSchedule_pastTime");
			set(tendercreationlocators.negotiationBidSubmissionStartDate,
					pdfResultReport.testData.get("negotiationBidSubmissionStartDate"),
					"negotiationBidSubmissionStartDate");
			set(tendercreationlocators.negotiationBidSubmissionEndDate, getBidDueDate(enddatelag),
					"negotiationBidSubmissionEndDate");
			click(tendercreationlocators.submitbutton, "submitbutton");
			pdfResultReport.addStepDetails("negotiation_dateSchedule_pastTime",
					"Negotiation bid submission start date and end date must be entered successfully",
					"Successfully entered Negotiation bid submission start date and end date " + " ", "Pass", "Y");
			log.info("completed executing the method:: negotiation_dateSchedule_pastTime");
		} catch (Exception e) {
			log.fatal("Not able to enter Negotiation bid submission start date and end date" + e.getMessage());
			pdfResultReport.addStepDetails("negotiation_dateSchedule_pastTime",
					"Negotiation bid submission start date and end date must be entered successfully",
					"Unable to enter Negotiation bid submission start date and end date" + e.getMessage(), "Fail", "N");
		}
	}

	public void negotiateBidderByDefaultInRejectedStatus() throws Throwable {
		try {
			log.info("started executing the method:: negotiateBidderByDefaultInRejectedStatus");
			IsElementPresent(tendercreationlocators.bidderStatus_Rejected_TECH);
			pdfResultReport.addStepDetails("negotiateBidderByDefaultInRejectedStatus",
					"negotiate Bidder status must be shown as Rejected",
					"Successfully shown negotiate Bidder status as rejected" + " ", "Pass", "Y");
			log.info("completed executing the method:: negotiateBidderByDefaultInRejectedStatus");
		} catch (Exception e) {
			log.fatal("Unable to show the status as Evaluation and stage as Cover1 Negotiation" + e.getMessage());
			pdfResultReport.addStepDetails("negotiateBidderByDefaultInRejectedStatus",
					"negotiate Bidder status must be shown as Rejected",
					"Unable to show negotiate Bidder status as rejected" + e.getMessage(), "Fail", "N");
		}
	}

	public void submit_BidDetailPage() throws Throwable {

		try {
			log.info("started executing the method:: submit_BidDetailPage");
			click(tendercreationlocators.submit_biddetails, "submit_biddetails");
			waitForObj(5000);
			pdfResultReport.addStepDetails("submit_BidDetailPage", "must click submit in Bid detail page",
					"Successfully clicked submit in Bid detail page" + " ", "Pass", "Y");
			log.info("completed executing the method:: submit_BidDetailPage");
		} catch (Exception e) {
			log.fatal("Not able to click submit in Bid detail page" + e.getMessage());
			pdfResultReport.addStepDetails("submit_BidDetailPage", "must click submit in Bid detail page",
					"Unable to click submit in Bid detail page " + e.getMessage(), "Fail", "N");
		}
	}

	public void approveBidderSubmission2() throws Throwable {
		try {
			log.info("started executing the method:: approveBidderSubmission2");
			click(tendercreationlocators.action, "action");
			click(tendercreationlocators.negotiation, "negotiation");
			waitForObj(5000);

			click(tendercreationlocators.technical, "technical");
			click(tendercreationlocators.savebutton, "savebutton");
			IsElementPresent(tendercreationlocators.alertPopUp_QRC_bidSubmission);
			IsElementPresent(tendercreationlocators.technicalAlertTab_bidSubmission);
			click(tendercreationlocators.alertClose_QRC_bidSubmission, "alertClose_QRC_bidSubmission");

			click(tendercreationlocators.termsAndCondition_bidSubmission, "termsAndCondition_bidSubmission");
			waitForObj(4000);
			click(tendercreationlocators.savebutton, "savebutton");
			IsElementPresent(tendercreationlocators.alertPopUp_QRC_bidSubmission);
			IsElementPresent(tendercreationlocators.termsAndConditionAlertTab_bidSubmission);
			click(tendercreationlocators.alertClose_QRC_bidSubmission, "alertClose_QRC_bidSubmission");

			click(tendercreationlocators.generalInformation_Tab, "generalInformation_Tab");
			waitForObj(4000);
			click(tendercreationlocators.savebutton, "savebutton");

			click(tendercreationlocators.attachementsTab, "attachementsTab");
			waitForObj(1000);
			click(tendercreationlocators.addtenderattachmenticon, "addtenderattachmenticon");
			set(tendercreationlocators.upload, System.getProperty("user.dir") + "\\MediaFiles\\rfqCreation2.xlsx",
					"fileName");
			click(tendercreationlocators.savebutton, "savebutton");
			IsElementPresent(tendercreationlocators.alertPopUp_QRC_bidSubmission);
			IsElementPresent(tendercreationlocators.attachmentsAlertTab_bidSubmission);
			click(tendercreationlocators.alertClose_QRC_bidSubmission, "alertClose_QRC_bidSubmission");

			click(tendercreationlocators.submitbutton, "submitbutton");
			waitForObj(2000);
			click(tendercreationlocators.submitbid, "submitbid");
			waitForObj(5000);
			IsElementPresent(tendercreationlocators.bidSuccessfulMessage);
			click(tendercreationlocators.bidSuccessfulMessage_Ok, "bidSuccessfulMessage_Ok");
			waitForObj(3000);
			pdfResultReport.addStepDetails("approveBidderSubmission2", "BidSubmission must be done successfully",
					"Successfully done BidSubmission" + " ", "Pass", "Y");
			log.info("completed executing the method:: approveBidderSubmission2");
		} catch (Exception e) {
			log.fatal("Not able to Bid Submission" + e.getMessage());
			pdfResultReport.addStepDetails("negotiatedBidderSubmission", "BidSubmission must be done successfully",
					"Unable to do Bid Submission" + e.getMessage(), "Fail", "N");
		}
	}

	public void negotiationTimeOverMessageValidation() throws Throwable {
		try {
			log.info("started executing the method:: negotiationTimeOverMessageValidation");
			click(tendercreationlocators.action, "action");
			click(tendercreationlocators.negotiation, "negotiation");
			waitForObj(5000);
			IsElementPresent(tendercreationlocators.negotiationTimeOverPopup);
			pdfResultReport.addStepDetails("negotiationTimeOverMessageValidation",
					"negotiation Time Over Message must be validate successfully",
					"Successfully validated negotiation Time Over Message" + " ", "Pass", "Y");
			log.info("completed executing the method:: negotiationTimeOverMessageValidation");
		} catch (Exception e) {
			log.fatal("Not able to validate negotiation Time Over Message" + e.getMessage());
			pdfResultReport.addStepDetails("negotiationTimeOverMessageValidation",
					"negotiation Time Over Message must be validate successfully",
					"Unable to validate negotiation Time Over Message" + e.getMessage(), "Fail", "N");
		}
	}

	public void createTender_UVoltasDEMO_generalInformation_excel(String oldpath) throws Throwable {
		try {
			log.info("started executing the method:: createTender_UVoltasDEMO_generalInformation");
			waitForElement(tendercreationlocators.templateGroupdropdown, 5000);
			waitForSpinnerToDisappear();
			select(tendercreationlocators.templateGroupdropdown, "UVoltas DEMO");
			select(tendercreationlocators.bitPartdropdown, pdfResultReport.testData.get("BidParts"));
			click(tendercreationlocators.bitPartRadio, "bitPartRadio");
			set(tendercreationlocators.tenderReferenceNumber, pdfResultReport.testData.get("TenderReferencNumber"),
					"tenderReferenceNumber");
			select(tendercreationlocators.procurementCategory, "Default_cat");
			select(tendercreationlocators.tenderCurrency, pdfResultReport.testData.get("TenderCurrency"));
			set(tendercreationlocators.probabaleAmountContract, pdfResultReport.testData.get("PAC"),
					"probabaleAmountContract");
			select(tendercreationlocators.biddingCurrency, pdfResultReport.testData.get("BiddingCurrency"));
			select(tendercreationlocators.tenderType, pdfResultReport.testData.get("TenderType"));
			if (pdfResultReport.testData.get("TenderType").equalsIgnoreCase("Limited Tender")) {
				click(tendercreationlocators.addBidders, "addBidders");
				click(tendercreationlocators.biddingcomp, "biddingcompany");
				click(tendercreationlocators.addbtnvendor, "addbtnvendor");
			} else {
				System.out.println("Tender type is not linmited");
			}
			set(tendercreationlocators.detailedDescription,
					pdfResultReport.testData.get("CreateTender-DetailedDescription"), "detailedDescription");
			JSClick(tendercreationlocators.savebutton, "savebutton");
			waitForObj(30000);
			JSClick(tendercreationlocators.sectionsdownloadinExcel, "sectionsdownloadinExcel");
			waitForObj(5000);
			List<WebElement> ele = ThreadLocalWebdriver.getDriver()
					.findElements(By.xpath("(//ul[@class='dropdown-menu inner'])[2]//span[@class='text']"));
			for (WebElement elem : ele) {
				elem.click();
			}
			// JSClick(tendercreationlocators.savebutton, "savebutton");
			waitForSpinnerToDisappear();
			waitForObj(20000);
			JSClick(tendercreationlocators.DownloadExcelFormat, "DownloadExcelFormat");
			waitForObj(60000);
			writedataDateSchedule();
			waitForObj(5000);
			ExcelTermsandCondition();
			waitForObj(5000);
			ExcelTechnical();
			waitForObj(5000);
			ExcelBOQSummary();
			waitForObj(5000);
			set(tendercreationlocators.UploadExcelSheet, System.getProperty("user.dir") + oldpath, "UploadExcelSheet");
			waitForObj(25000);
			JSClick(tendercreationlocators.Okbtn, "successs btn of excel upload");
			waitForObj(5000);
			JSClick(tendercreationlocators.savebutton, "savebutton");
			pdfResultReport.addStepDetails("Successfully Created Tender",
					"Tender must be created successfully using createTender_UVoltasDEMO_generalInformation as template group ",
					"Tender is created successfully using createTender_UVoltasDEMO_generalInformation as template group"
							+ " ",
					"Pass", "Y");
			log.info("completed executing the method:: createTender_UVoltasDEMO_generalInformation");
		} catch (Exception e) {
			log.fatal("Not able to create tender" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to create tender",
					"Not able to create tender using createTender_UVoltasDEMO_generalInformation as template group",
					"Unable to create tender using createTender_UVoltasDEMO_generalInformation as template group"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public static int fn_GetCellNumberByColumName(XSSFSheet WSheetObj, String ColumnName, int rownum)
			throws IOException {

		XSSFRow FstRowObj = WSheetObj.getRow(rownum);
		int columnCount = FstRowObj.getLastCellNum();
		int columnNumber = 0;
		for (int i = 0; i <= columnCount - 1; i++) {
			XSSFCell cellObj = FstRowObj.getCell(i, XSSFRow.CREATE_NULL_AS_BLANK);
			String xl_ColumnName = cellObj.getStringCellValue();
			xl_ColumnName = xl_ColumnName.trim();
			ColumnName = ColumnName.trim();
			if (ColumnName.equalsIgnoreCase(xl_ColumnName) == true) {
				columnNumber = i;
				break;
			}
		}
		return columnNumber;
	}

	public FileInputStream fnOpenExcelFile(String path) {
		FileInputStream fis = null;
		try {
			File file = new File(System.getProperty("user.dir") + path);
			fis = new FileInputStream(file);
		} catch (Exception e) {
		}
		return fis;
	}

	public XSSFWorkbook fnOpenWorkBook(FileInputStream fis, String sheetname) {
		XSSFWorkbook Workbook = null;
		try {
			Workbook = new XSSFWorkbook(fis);
		} catch (Exception e) {
		}
		return Workbook;
	}

	public XSSFSheet fnOpenSheet(XSSFWorkbook Workbook, String sheetname) {
		XSSFSheet sheet = Workbook.getSheet(sheetname);
		try {
			sheet = Workbook.getSheet(sheetname);
		} catch (Exception e) {
		}
		return sheet;
	}

	public void fnSaveExcel(String path, FileInputStream fis, XSSFWorkbook Workbook) {
		try {
			File file = new File(System.getProperty("user.dir") + path);
			FileOutputStream fos = new FileOutputStream(file);
			Workbook.write(fos);
			fis.close();
			Thread.sleep(10000);
		} catch (Exception e) {
		}
	}

	public void ExcelWite(XSSFSheet sheet, String colname, int rownum, int rowtowrite, String data) {
		try {
			int columnNumber = fn_GetCellNumberByColumName(sheet, colname, rownum);
			XSSFRow row1 = sheet.getRow(rowtowrite);
			Cell cell = row1.getCell(columnNumber);
			if (data.startsWith("'")) {
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				data = data.replace("'", "");
				cell.setCellValue(Integer.parseInt(data));
			} else {
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(data);
			}
		} catch (Exception e) {
		}
	}

	public void ExcelWiteWithColumnNo(XSSFSheet sheet, int columnNumber, int rowtowrite, String data) {
		try {
			// int columnNumber=
			// fn_GetCellNumberByColumName(sheet,colname,rownum);
			XSSFRow row1 = sheet.getRow(rowtowrite);
			Cell cell = row1.getCell(columnNumber);
			if (data.startsWith("'")) {
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				data = data.replace("'", "");
				cell.setCellValue(Integer.parseInt(data));
			} else {
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void writeexcel(String path, String sheetname, String colname, int rownum, int rowtowrite, String data) {
		try {
			File file = new File(System.getProperty("user.dir") + path);
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook Workbook = null;
			Workbook = new XSSFWorkbook(fis);

			XSSFSheet sheet = Workbook.getSheet(sheetname);

			int columnNumber = fn_GetCellNumberByColumName(sheet, colname, rownum);

			XSSFRow row1 = sheet.getRow(rowtowrite);
			Cell cell = row1.getCell(columnNumber);
			if (data.startsWith("'")) {
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				data = data.replace("'", "");
				cell.setCellValue(Integer.parseInt(data));
			} else {
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(data);
			}

			FileOutputStream fos = new FileOutputStream(file);
			Workbook.write(fos);

			fis.close();
			fos.close();
			Thread.sleep(10000);
		} catch (Exception e) {

			log.fatal("Not able to write" + e.getCause().getClass());
		}

	}

	public void createTender_UVoltasDEMO_dateSchedule_ExcelUpload(String oldpath, String newpath) throws Throwable {
		try {
			log.info("started executing the method:: createTender_UVoltasDEMO_dateSchedule");
			click(tendercreationlocators.dateschedule, "dateschedule");
			waitForSpinnerToDisappear();
			waitForElement(tendercreationlocators.bidsubmissionStartDate, 5000);
			click(tendercreationlocators.downloadbtndateschedule, "download excel");
			waitForObj(20000);
			writedataDateSchedule();
			waitForObj(15000);
			set(tendercreationlocators.uploadbtn, System.getProperty("user.dir") + oldpath, "upload date schedule");
			waitForObj(15000);
			click(tendercreationlocators.Okbtn, "successs btn of excel upload");
			click(tendercreationlocators.savebutton, "savebutton");
			waitForObj(5000);
			File f = new File(System.getProperty("user.dir") + oldpath);
			if (f.exists()) {
				movefile(oldpath, newpath);
				System.out.println("File moved successfully");
			} else {
				System.out.println("Unable to move File ");
			}
			waitForSpinnerToDisappear();
			pdfResultReport.addStepDetails("Successfully Created Tender",
					"Tender must be created successfully using createTender_UVoltasDEMO_dateSchedule as template group ",
					"Tender is created successfully using createTender_UVoltasDEMO_dateSchedule as template group"
							+ " ",
					"Pass", "Y");
			log.info("completed executing the method:: createTender_UVoltasDEMO_dateSchedule");
		} catch (Exception e) {
			log.fatal("Not able to create tender" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to create tender",
					"Not able to create tender using createTender_UVoltasDEMO_dateSchedule as template group",
					"Unable to create tender using createTender_UVoltasDEMO_dateSchedule as template group"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void writedataDateSchedule() throws Throwable {

		String path = "\\Resources\\rfqExcel\\rfqCreation.xlsx";
		String ColName_StartDate = "Bid Submission Start Date *";
		String ColName_DueDate = "Bid Submission Due Date *";
		String ColName_OpenDate = "Bid Open Date *";
		/*
		 * String start_date="25-04-2020 08:20 pm"; String Due_date=
		 * "26-04-2020 11:21 pm"; String Sub_date="28-04-2020 09:21 pm";
		 */
		String start_date = getBidStartDate(5);
		localdatetimeForBid = LocalDateTime.now().plusMinutes(6);
		;
		String Due_date = getBidDueDate(1440);
		String Sub_date = getBidOpenDate(2880);
		BidStartDate = start_date;
		BidDueDate = Due_date;
		BidOpenDate = Sub_date;
		String Sheet = "Date Schedule";
		int colrow = 6;
		int rowtowrite = 7;

		try {
			FileInputStream fis = fnOpenExcelFile(path);
			XSSFWorkbook wb = fnOpenWorkBook(fis, Sheet);
			XSSFSheet sheet = fnOpenSheet(wb, Sheet);
			ExcelWite(sheet, ColName_StartDate, colrow, rowtowrite, start_date);
			ExcelWite(sheet, ColName_DueDate, colrow, rowtowrite, Due_date);
			ExcelWite(sheet, ColName_OpenDate, colrow, rowtowrite, Sub_date);
			fnSaveExcel(path, fis, wb);

			/*
			 * writeexcel(path,Sheet,ColName_StartDate,colrow,rowtowrite,
			 * start_date);
			 * writeexcel(path,Sheet,ColName_DueDate,colrow,rowtowrite,Due_date)
			 * ;
			 * writeexcel(path,Sheet,ColName_OpenDate,colrow,rowtowrite,Sub_date
			 * );
			 */
		} catch (Exception e) {

			log.fatal("Not able to write" + e.getCause().getClass());
		}

	}

	public void ExcelTermsandCondition() throws Throwable {
		String path = "\\Resources\\rfqExcel\\rfqCreation.xlsx";
		String ColName_Clause = "Clause *";
		String ColName_Details = "Details";

		String Clause_Val = "CLAUSE006";
		String Details_Val = "Details";

		String Sheet = "Terms and Conditions";
		int colrow = 7;
		int rowtowrite = 8;
		int rowcount = 6;

		try {
			FileInputStream fis = fnOpenExcelFile(path);
			XSSFWorkbook wb = fnOpenWorkBook(fis, Sheet);
			XSSFSheet sheet = fnOpenSheet(wb, Sheet);
			ExcelWite(sheet, ColName_Clause, colrow, rowtowrite, Clause_Val);
			ExcelWite(sheet, ColName_Details, colrow, rowtowrite, Details_Val);
			ExcelWite(sheet, ColName_Clause, colrow, rowcount, "'1");
			fnSaveExcel(path, fis, wb);
			/*
			 * writeexcel(path,Sheet,ColName_Clause,colrow,rowtowrite,Clause_Val
			 * ); writeexcel(path,Sheet,ColName_Details,colrow,rowtowrite,
			 * Details_Val);
			 * writeexcel(path,Sheet,ColName_Clause,colrow,rowcount,"'1");
			 */
		} catch (Exception e) {

			log.fatal("Not able to write" + e.getCause().getClass());
		}

	}

	public void ExcelTechnical() throws Throwable {
		String path = "\\Resources\\rfqExcel\\rfqCreation.xlsx";
		String ColName_Clause = "Clause *";
		String ColName_Details = "Details";

		String Clause_Val = "CLAUSE006";
		String Details_Val = "Details";

		String Sheet = "Technical";
		int colrow = 7;
		int rowtowrite = 8;
		int rowcount = 6;
		try {
			FileInputStream fis = fnOpenExcelFile(path);
			XSSFWorkbook wb = fnOpenWorkBook(fis, Sheet);
			XSSFSheet sheet = fnOpenSheet(wb, Sheet);
			ExcelWite(sheet, ColName_Clause, colrow, rowtowrite, Clause_Val);
			ExcelWite(sheet, ColName_Details, colrow, rowtowrite, Details_Val);
			ExcelWite(sheet, ColName_Clause, colrow, rowcount, "'1");
			fnSaveExcel(path, fis, wb);
			/*
			 * writeexcel(path,Sheet,ColName_Clause,colrow,rowtowrite,Clause_Val
			 * ); writeexcel(path,Sheet,ColName_Details,colrow,rowtowrite,
			 * Details_Val);
			 * writeexcel(path,Sheet,ColName_Clause,colrow,rowcount,"'1");
			 */
		} catch (Exception e) {

			log.fatal("Not able to write" + e.getCause().getClass());
		}

	}

	public void ExcelBOQSummary() throws Throwable {
		String path = "\\Resources\\rfqExcel\\rfqCreation.xlsx";
		String ColName_ItemCode = "Item Code *";
		String ColName_ItemDescription = "Item Description *";
		String ColName_Quantity = "Quantity *";
		String ColName_UOM = "UOM *";
		String ColName_SORRate = "SOR Rate *";
		String ColName_MandatoryItem = "Mandatory Item *";
		String ColName_SOR = "SOR (Y/N) *";

		String ItemCode = "CLAUSE006";
		String ItemDescription = "Details";
		String Quantity = "15";
		String UOM = "156";
		String SORRate = "15";
		String MandatoryItem = "Yes";
		String SOR = pdfResultReport.testData.get("SORSTATUS");
		int colrow = 7;
		int rowtowrite = 8;
		int rowcount = 6;
		String Sheet = "BOQ Summary";

		try {
			FileInputStream fis = fnOpenExcelFile(path);
			XSSFWorkbook wb = fnOpenWorkBook(fis, Sheet);
			XSSFSheet sheet = fnOpenSheet(wb, Sheet);
			ExcelWite(sheet, ColName_ItemCode, colrow, rowtowrite, ItemCode);
			ExcelWite(sheet, ColName_ItemDescription, colrow, rowtowrite, ItemDescription);
			ExcelWite(sheet, ColName_Quantity, colrow, rowtowrite, Quantity);
			ExcelWite(sheet, ColName_UOM, colrow, rowtowrite, UOM);
			ExcelWite(sheet, ColName_SORRate, colrow, rowtowrite, SORRate);
			ExcelWite(sheet, ColName_MandatoryItem, colrow, rowtowrite, MandatoryItem);
			ExcelWite(sheet, ColName_SOR, colrow, rowtowrite, SOR);
			ExcelWite(sheet, ColName_ItemCode, colrow, rowcount, "'1");
			fnSaveExcel(path, fis, wb);
			/*
			 * 
			 * 
			 * writeexcel(path,Sheet,ColName_ItemCode,colrow,rowtowrite,ItemCode
			 * );
			 * writeexcel(path,Sheet,ColName_ItemDescription,colrow,rowtowrite,
			 * ItemDescription);
			 * writeexcel(path,Sheet,ColName_Quantity,colrow,rowtowrite,Quantity
			 * ); writeexcel(path,Sheet,ColName_UOM,colrow,rowtowrite,UOM);
			 * writeexcel(path,Sheet,ColName_SORRate,colrow,rowtowrite,SORRate);
			 * writeexcel(path,Sheet,ColName_MandatoryItem,colrow,rowtowrite,
			 * MandatoryItem);
			 * writeexcel(path,Sheet,ColName_SOR,colrow,rowtowrite,SOR);
			 * writeexcel(path,Sheet,ColName_ItemCode,colrow,rowcount,"'1");
			 */
		} catch (Exception e) {

			log.fatal("Not able to write" + e.getCause().getClass());
		}

	}

	public String GetCurrentDateandStamp() {
		String date = new SimpleDateFormat("yyyyMMddHHmm'.xlsx'").format(new Date());
		;
		return date;
	}

	public void movefile(String oldpath, String newpath) throws IOException {
		File fileToMove = new File(System.getProperty("user.dir") + oldpath);
		boolean isMoved = fileToMove
				.renameTo(new File(System.getProperty("user.dir") + newpath + "\\" + GetCurrentDateandStamp()));
		if (!isMoved) {
			throw new FileSystemException(System.getProperty("user.dir") + newpath + "\\" + GetCurrentDateandStamp());
		}

	}

	public void createTender_UVoltasDEMO_termsAndCondition_ExcelUpload(String oldpath, String newpath)
			throws Throwable {
		try {
			log.info("started executing the method:: createTender_UVoltasDEMO_termsAndCondition");
			click(tendercreationlocators.termsConditionsTab, "termsConditionsTab");
			waitForSpinnerToDisappear();

			click(tendercreationlocators.downloadbtntermsandcond, "downloadbtntermsandcond");
			waitForObj(20000);
			ExcelTermsandCondition();
			waitForObj(15000);
			set(tendercreationlocators.uploadbtntermsandcond, System.getProperty("user.dir") + oldpath,
					"upload date schedule");
			waitForObj(15000);
			click(tendercreationlocators.Okbtn, "successs btn of excel upload");
			// writedata(path,ColName_StartDate,start_date);
			JSClick(tendercreationlocators.savebutton, "savebutton");

			waitForObj(5000);

			File f = new File(System.getProperty("user.dir") + oldpath);
			if (f.exists()) {
				movefile(oldpath, newpath);
				System.out.println("File moved successfully");
			} else {
				System.out.println("Unable to move File ");
			}
			waitForSpinnerToDisappear();

			pdfResultReport.addStepDetails("Successfully Created Tender",
					"Tender must be created successfully using createTender_UVoltasDEMO_termsAndCondition as template group ",
					"Tender is created successfully using createTender_UVoltasDEMO_termsAndCondition as template group"
							+ " ",
					"Pass", "Y");
			log.info("completed executing the method:: createTender_UVoltasDEMO_termsAndCondition");
		} catch (Exception e) {
			log.fatal("Not able to create tender" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to create tender",
					"Not able to create tender using createTender_UVoltasDEMO_termsAndCondition as template group",
					"Unable to create tender using createTender_UVoltasDEMO_termsaAndCondition as template group"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void createTender_UVoltasDEMO_boqSummary_excelUpload(String oldpath, String newpath) throws Throwable {
		try {
			log.info("started executing the method:: createTender_UVoltasDEMO_boqSummary");
			click(tendercreationlocators.boqSummary, "boqSummary");
			waitForSpinnerToDisappear();
			click(tendercreationlocators.downloadbtnboqsummary, "downloadbtnboqsummary");
			waitForObj(20000);
			ExcelBOQSummary();
			waitForObj(15000);
			JSClick(tendercreationlocators.savebutton, "savebutton");
			waitForObj(5000);
			set(tendercreationlocators.uploadbtnboqsummary, System.getProperty("user.dir") + oldpath,
					"upload boq summary");
			waitForSpinnerToDisappear();
			waitForObj(15000);
			click(tendercreationlocators.Okbtn, "successs btn of excel upload");
			JSClick(tendercreationlocators.savebutton, "savebutton");
			waitForObj(5000);
			File f = new File(System.getProperty("user.dir") + oldpath);
			if (f.exists()) {
				movefile(oldpath, newpath);
				System.out.println("File moved successfully");
			} else {
				System.out.println("Unable to move File ");
			}
			pdfResultReport.addStepDetails("Successfully Created Tender",
					"Tender must be created successfully using createTender_UVoltasDEMO_boqSummary as template group ",
					"Tender is created successfully using createTender_UVoltasDEMO_boqSummary as template group" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: createTender_UVoltasDEMO_boqSummary");
		} catch (Exception e) {
			log.fatal("Not able to create tender" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to create tender",
					"Not able to create tender using createTender_UVoltasDEMO_boqSummary as template group",
					"Unable to create tender using createTender_UVoltasDEMO_boqSummary as template group"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void createTender_UVoltasDEMO_technicalExcel_upload(String oldpath, String newpath) throws Throwable {
		try {
			log.info("started executing the method:: createTender_UVoltasDEMO_technical");
			click(tendercreationlocators.technical, "technical");
			waitForSpinnerToDisappear();

			click(tendercreationlocators.downloadbtntechnical, "downloadbtntechnical");
			waitForObj(20000);
			ExcelTechnical();
			waitForObj(15000);
			set(tendercreationlocators.uploadbtntechnical, System.getProperty("user.dir") + oldpath,
					"upload technical");

			waitForObj(15000);
			click(tendercreationlocators.Okbtn, "successs btn of excel upload");
			// writedata(path,ColName_StartDate,start_date);
			JSClick(tendercreationlocators.savebutton, "savebutton");

			waitForObj(5000);

			File f = new File(System.getProperty("user.dir") + oldpath);
			if (f.exists()) {
				movefile(oldpath, newpath);
				System.out.println("File moved successfully");
			} else {
				System.out.println("Unable to move File ");
			}
			waitForSpinnerToDisappear();

			click(tendercreationlocators.next, "next");
			pdfResultReport.addStepDetails("Successfully Created Tender",
					"Tender must be created successfully using createTender_UVoltasDEMO_technical as template group ",
					"Tender is created successfully using createTender_UVoltasDEMO_technical as template group" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: createTender_UVoltasDEMO_technical");
		} catch (Exception e) {
			log.fatal("Not able to create tender" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to create tender",
					"Not able to create tender using createTender_UVoltasDEMO_technical as template group",
					"Unable to create tender using createTender_UVoltasDEMO_technical as template group"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void generalInformation_ExcelDownload() throws Throwable {
		try {
			String oldpath = "\\Resources\\rfqExcel\\QuotationExcel.xlsx";
			String newpath = "\\Resources\\rfqExcel_old";
			File folder = new File(System.getProperty("user.dir") + "\\Resources\\rfqExcel");
			FileUtils.cleanDirectory(folder);
			log.info("started executing the method:: generalInformation_ExcelDownload");
			waitForSpinnerToDisappearInBidSubmission();
			waitForObj(30000);
			JSClick(tendercreationlocators.generalInformation_Tab, "general information");
			waitForSpinnerToDisappearInBidSubmission();
			waitForObj(15000);
			click(tendercreationlocators.excelBidding, "excelBidding");
			waitForObj(5000);
			List<WebElement> ele = ThreadLocalWebdriver.getDriver()
					.findElements(By.xpath("(//ul[@class='dropdown-menu inner'])[1]/li"));
			ele.get(0).click();
			for (WebElement elem : ele) {
				elem.click();
			}
			click(tendercreationlocators.biddownloadinExcel, "sectionsdownloadinExcel");
			waitForObj(60000);
			ExcelBidSubmissionTechnical();
			ExcelBidSubmissionTermsAndConditions();
			ExcelBidSubmissionQuotatonSummary();
			ExcelBidSubmissionCommercial();
			// Upload
			set(tendercreationlocators.bidSubmissionUpload, System.getProperty("user.dir") + oldpath,
					"Uploaded Bid Submission Commercial");
			waitForObj(50000);
			click(tendercreationlocators.Okbtn, "successs btn of excel upload");
			JSClick(tendercreationlocators.savebutton, "savebutton");
			waitForObj(50000);
			mandatoryFieldValidation_attachmentsTab_bidsubmission();
			waitForObj(50000);
			JSClick(tendercreationlocators.submitbutton, "submitbutton");
			waitForObj(50000);

			pdfResultReport.addStepDetails("Successfully identify general information elements",
					"general information elements must be identified successfully",
					"Successfully identified general information elements" + " ", "Pass", "Y");
			log.info("completed executing the method:: generalInformationPageValidation_bidsubmission");
		} catch (Exception e) {
			log.fatal("Not able to identify general information elements" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to identify general information elements",
					"Not able to identify general information elements",
					"Unable to identify general information elements" + e.getMessage(), "Fail", "N");
		}
	}

	public void generalInformation_ExcelDownloadIndividual() throws Throwable {
		try {
			String oldpath = "\\Resources\\rfqExcel\\QuotationExcel.xlsx";
			String newpath = "\\Resources\\rfqExcel_old";
			File folder = new File(System.getProperty("user.dir") + "\\Resources\\rfqExcel");
			FileUtils.cleanDirectory(folder);
			waitForObj(30000);
			log.info("started executing the method:: generalInformation_ExcelDownload");
			waitForSpinnerToDisappearInBidSubmission();
			waitForObj(20000);
			JSClick(tendercreationlocators.generalInformation_Tab, "general information");
			waitForSpinnerToDisappearInBidSubmission();
			waitForObj(15000);
			click(tendercreationlocators.excelBidding, "excelBidding");
			waitForObj(5000);
			List<WebElement> ele = ThreadLocalWebdriver.getDriver()
					.findElements(By.xpath("(//ul[@class='dropdown-menu inner'])[1]/li"));
			ele.get(0).click();
			// Commercial
			ele.get(0).click();
			FileUtils.cleanDirectory(folder);
			click(tendercreationlocators.biddownloadinExcel, "sectionsdownloadinExcel");
			waitForObj(60000);
			ExcelBidSubmissionCommercial();
			// Upload
			set(tendercreationlocators.bidSubmissionUpload, System.getProperty("user.dir") + oldpath,
					"Uploaded Bid Submission Commercial");
			waitForObj(15000);
			click(tendercreationlocators.Okbtn, "successs btn of excel upload");
			// Technical
			click(tendercreationlocators.excelBidding, "excelBidding");
			waitForObj(5000);
			List<WebElement> eleTec = ThreadLocalWebdriver.getDriver()
					.findElements(By.xpath("(//ul[@class='dropdown-menu inner'])[1]/li"));
			eleTec.get(0).click();
			eleTec.get(1).click();
			FileUtils.cleanDirectory(folder);
			click(tendercreationlocators.biddownloadinExcel, "sectionsdownloadinExcel");
			waitForObj(60000);
			ExcelBidSubmissionTechnical();
			set(tendercreationlocators.bidSubmissionUpload, System.getProperty("user.dir") + oldpath,
					"Uploaded Bid Submission Technical");
			waitForObj(15000);
			click(tendercreationlocators.Okbtn, "successs btn of excel upload");
			// Terms & Conditions
			click(tendercreationlocators.excelBidding, "excelBidding");
			waitForObj(5000);
			List<WebElement> eleTerms = ThreadLocalWebdriver.getDriver()
					.findElements(By.xpath("(//ul[@class='dropdown-menu inner'])[1]/li"));
			eleTerms.get(1).click();
			eleTerms.get(2).click();
			FileUtils.cleanDirectory(folder);
			click(tendercreationlocators.biddownloadinExcel, "sectionsdownloadinExcel");
			waitForObj(60000);
			ExcelBidSubmissionTermsAndConditions();
			set(tendercreationlocators.bidSubmissionUpload, System.getProperty("user.dir") + oldpath,
					"Uploaded Bid Submission Terms And Conditions");
			waitForObj(15000);
			click(tendercreationlocators.Okbtn, "successs btn of excel upload");
			// Quotation Summary
			click(tendercreationlocators.excelBidding, "excelBidding");
			waitForObj(5000);
			List<WebElement> eleQuo = ThreadLocalWebdriver.getDriver()
					.findElements(By.xpath("(//ul[@class='dropdown-menu inner'])[1]/li"));
			eleQuo.get(2).click();
			eleQuo.get(3).click();
			FileUtils.cleanDirectory(folder);
			click(tendercreationlocators.biddownloadinExcel, "sectionsdownloadinExcel");
			waitForObj(60000);
			ExcelBidSubmissionQuotatonSummary();
			set(tendercreationlocators.bidSubmissionUpload, System.getProperty("user.dir") + oldpath,
					"Uploaded Bid Submission Quotaton Summary");
			waitForObj(50000);
			click(tendercreationlocators.Okbtn, "successs btn of excel upload");
			waitForObj(50000);
			mandatoryFieldValidation_attachmentsTab_bidsubmission();
			waitForObj(50000);
			JSClick(tendercreationlocators.submitbutton, "submitbutton");
			waitForObj(50000);
			pdfResultReport.addStepDetails("Successfully identify general information elements",
					"general information elements must be identified successfully",
					"Successfully identified general information elements" + " ", "Pass", "Y");
			log.info("completed executing the method:: generalInformationPageValidation_bidsubmission");
		} catch (Exception e) {
			log.fatal("Not able to identify general information elements" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to identify general information elements",
					"Not able to identify general information elements",
					"Unable to identify general information elements" + e.getMessage(), "Fail", "N");
		}
	}

	public void ExcelBidSubmissionTechnical() throws Throwable {
		String path = "\\Resources\\rfqExcel\\QuotationExcel.xlsx";
		String ColName_Clause = "Clause *";
		String ColName_Details = "Details";
		String Remarks_Details = "Remarks";
		String Remarks_Val = "Technical Description";
		String Clause_Val = "CLAUSE007";
		String Details_Val = "Details07";
		String Sheet = "Technical";
		int colrow = 8;
		int rowtowrite = 9;
		int rowcount = 6;

		try {
			// Open Excel
			// Send data
			// Save Excel File
			FileInputStream fis = fnOpenExcelFile(path);
			XSSFWorkbook wb = fnOpenWorkBook(fis, Sheet);
			XSSFSheet sheet = fnOpenSheet(wb, Sheet);
			ExcelWite(sheet, ColName_Details, colrow, rowtowrite, Details_Val);
			ExcelWite(sheet, Remarks_Details, colrow, rowtowrite, Remarks_Val);
			// ExcelWite(sheet,ColName_Clause,colrow,rowcount,"'1");
			fnSaveExcel(path, fis, wb);
		} catch (Exception e) {
			log.fatal("Not able to write" + e.getCause().getClass());
		}
	}

	public void ExcelBidSubmissionTermsAndConditions() throws Throwable {
		String path = "\\Resources\\rfqExcel\\QuotationExcel.xlsx";
		String ColName_Clause = "Clause *";
		String ColName_Details = "Details";
		String Remarks_Details = "Remarks";
		String ColName_Compliance = "Compliance *";
		String Remarks_Val = "TermsAndConditions Description";
		String Clause_Val = "CLAUSE007";
		String Details_Val = "Details07";
		String ColName_Compliance_Val = "Yes";
		String Sheet = "Terms & Conditions";
		int colrow = 8;
		int rowtowrite = 9;
		int rowcount = 6;

		try {
			FileInputStream fis = fnOpenExcelFile(path);
			XSSFWorkbook wb = fnOpenWorkBook(fis, Sheet);
			XSSFSheet sheet = fnOpenSheet(wb, Sheet);
			ExcelWite(sheet, ColName_Details, colrow, rowtowrite, Details_Val);
			ExcelWite(sheet, ColName_Compliance, colrow, rowtowrite, ColName_Compliance_Val);
			ExcelWite(sheet, Remarks_Details, colrow, rowtowrite, Remarks_Val);
			// ExcelWite(sheet,ColName_Clause,colrow,rowcount,"'1");
			fnSaveExcel(path, fis, wb);
		} catch (Exception e) {
			log.fatal("Not able to write" + e.getCause().getClass());
		}
	}

	public void ExcelBidSubmissionQuotatonSummary() throws Throwable {
		String path = "\\Resources\\rfqExcel\\QuotationExcel.xlsx";
		String ColName_Clause = "Clause *";
		String ColName_Details = "Item Description *";
		String Remarks_Details = "Remarks";
		String Remarks_Val = "QuotatonSummary Description";
		String Details_Val = "Details07";
		String Sheet = "Quotation Summary";
		int colrow = 8;
		int rowtowrite = 9;
		int rowcount = 6;

		try {
			FileInputStream fis = fnOpenExcelFile(path);
			XSSFWorkbook wb = fnOpenWorkBook(fis, Sheet);
			XSSFSheet sheet = fnOpenSheet(wb, Sheet);
			ExcelWite(sheet, ColName_Details, colrow, rowtowrite, Details_Val);
			ExcelWite(sheet, Remarks_Details, colrow, rowtowrite, Remarks_Val);
			// ExcelWite(sheet,ColName_Clause,colrow,rowcount,"'1");
			fnSaveExcel(path, fis, wb);
		} catch (Exception e) {
			log.fatal("Not able to write" + e.getCause().getClass());
		}
	}

	public void ExcelBidSubmissionCommercial() throws Throwable {
		String path = "\\Resources\\rfqExcel\\QuotationExcel.xlsx";
		String Col_PKg_Doc = "Packing & Documentation Charge *";
		String Col_PKg_Doc_Val = "INCLUDED";
		String Col_FR_INS = "Freight & Insurance *";
		String Col_FR_INS_Val = "INCLUDED";
		String Col_Custom_Duty = "Custom Duty *";
		String Col_Custom_Duty_Val = "INCLUDED";
		String Col_Spare_Part = "Spare Parts *";
		String Col_Spare_Part_Val = "INCLUDED";
		String Col_Testing_Comm = "Testing & Commissioning *";
		String Col_Testing_Comm_Val = "INCLUDED";
		String Sheet = "Commercial";
		int colrow = 7;
		int rowtowrite = 8;
		int rowcount = 6;
		try {
			FileInputStream fis = fnOpenExcelFile(path);
			XSSFWorkbook wb = fnOpenWorkBook(fis, Sheet);
			XSSFSheet sheet = fnOpenSheet(wb, Sheet);
			ExcelWite(sheet, Col_PKg_Doc, colrow, rowtowrite, Col_PKg_Doc_Val);
			ExcelWite(sheet, Col_FR_INS, colrow, rowtowrite, Col_FR_INS_Val);
			ExcelWite(sheet, Col_Custom_Duty, colrow, rowtowrite, Col_Custom_Duty_Val);
			ExcelWite(sheet, Col_Spare_Part, colrow, rowtowrite, Col_Spare_Part_Val);
			ExcelWite(sheet, Col_Testing_Comm, colrow, rowtowrite, Col_Testing_Comm_Val);
			fnSaveExcel(path, fis, wb);
		} catch (Exception e) {
			log.fatal("Not able to write" + e.getCause().getClass());
		}
	}

	public void previewAll_validationBidSumbit() throws Throwable {
		try {
			log.info("started executing the method:: previewAll_validationBidSumbit");
			IsElementPresent(tendercreationlocators.previewAllTittle);
			click(tendercreationlocators.submitbid, "previewAllSubmitBid");
			waitForObj(30000);
			click(tendercreationlocators.bidSuccessfulMessage_Ok, "okbutton");
			waitForSpinnerToDisappear();
			waitForObj(30000);
			pdfResultReport.addStepDetails("Successfully validate previewAll details",
					"previewAll details must be validate successfully",
					"Successfully validated previewAll details" + " ", "Pass", "Y");
			log.info("completed executing the method:: previewAll_validation");
		} catch (Exception e) {
			log.fatal("Not able to validate previewAll details" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to validate previewAll details",
					"Not able to validate previewAll details", "Unable to validate previewAll details" + e.getMessage(),
					"Fail", "N");
		}
	}

	public void bid_validation() throws Throwable {
		try {
			log.info("started executing the method:: bid_validation");
			waitForObj(30000);
			String strAppBidStartDate = text(tendercreationlocators.valBidStartDate);
			String strAppBidDueDate = text(tendercreationlocators.valBidDueDate);
			if (strAppBidStartDate.equalsIgnoreCase(BidStartDate) && strAppBidDueDate.equalsIgnoreCase(BidDueDate)) {
				pdfResultReport.addStepDetails("Bid Validation", "Bid Date to be validate successfully",
						"Successfully validated Bid Validation details" + " ", "Pass", "Y");
			} else {
				pdfResultReport.addStepDetails("Bid Validation", "Bid Date to be validate successfully",
						"Unable to validate Bid Validation details" + " ", "FAIL", "N");
			}
			log.info("completed executing the method:: previewAll_validation");
		} catch (Exception e) {
			log.fatal("Not able to validate previewAll details" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to validate previewAll details",
					"Not able to validate previewAll details", "Unable to validate previewAll details" + e.getMessage(),
					"Fail", "N");
		}
	}

	public void createTender_UVoltasDEMO_generalInformation_AllBidder() throws Throwable {
		try {
			String oldpath = "\\Resources\\rfqExcel\\rfqCreation.xlsx";
			String newpath = "\\Resources\\rfqExcel_old";
			log.info("started executing the method:: createTender_UVoltasDEMO_generalInformation_AllBidder");
			waitForElement(tendercreationlocators.templateGroupdropdown, 5000);
			waitForSpinnerToDisappear();
			select(tendercreationlocators.templateGroupdropdown, "UVoltas DEMO");
			select(tendercreationlocators.bitPartdropdown, pdfResultReport.testData.get("BidParts"));
			click(tendercreationlocators.bitPartRadio, "bitPartRadio");
			set(tendercreationlocators.tenderReferenceNumber, pdfResultReport.testData.get("TenderReferencNumber"),
					"tenderReferenceNumber");
			select(tendercreationlocators.procurementCategory, "Default_cat");
			select(tendercreationlocators.tenderCurrency, pdfResultReport.testData.get("TenderCurrency"));
			set(tendercreationlocators.probabaleAmountContract, pdfResultReport.testData.get("PAC"),
					"probabaleAmountContract");
			select(tendercreationlocators.biddingCurrency, pdfResultReport.testData.get("BiddingCurrency"));
			select(tendercreationlocators.tenderType, pdfResultReport.testData.get("TenderType"));
			if (pdfResultReport.testData.get("TenderType").equalsIgnoreCase("Limited Tender")) {
				click(tendercreationlocators.addBidders, "addBidders");
				click(tendercreationlocators.selectallBidder, "selectallBidder");
				click(tendercreationlocators.addbtnvendor, "addbtnvendor");
			} else {
				System.out.println("Tender type is not linmited");
			}
			set(tendercreationlocators.detailedDescription,
					pdfResultReport.testData.get("CreateTender-DetailedDescription"), "detailedDescription");
			JSClick(tendercreationlocators.savebutton, "savebutton");
			waitForObj(60000);
			JSClick(tendercreationlocators.sectionsdownloadinExcel, "sectionsdownloadinExcel");
			waitForObj(5000);
			List<WebElement> ele = ThreadLocalWebdriver.getDriver()
					.findElements(By.xpath("(//ul[@class='dropdown-menu inner'])[2]//span[@class='text']"));
			for (WebElement elem : ele) {
				elem.click();
			}
			// JSClick(tendercreationlocators.savebutton, "savebutton");
			waitForSpinnerToDisappear();
			waitForObj(20000);
			JSClick(tendercreationlocators.DownloadExcelFormat, "DownloadExcelFormat");
			waitForObj(60000);
			writedataDateSchedule();
			waitForObj(5000);
			ExcelTermsandCondition();
			waitForObj(5000);
			ExcelTechnical();
			waitForObj(5000);
			ExcelBOQSummary();
			waitForObj(5000);
			set(tendercreationlocators.UploadExcelSheet, System.getProperty("user.dir") + oldpath, "UploadExcelSheet");
			waitForObj(25000);
			click(tendercreationlocators.Okbtn, "successs btn of excel upload");
			waitForObj(5000);
			JSClick(tendercreationlocators.savebutton, "savebutton");
			pdfResultReport.addStepDetails("Successfully Created Tender",
					"Tender must be created successfully using createTender_UVoltasDEMO_generalInformation_AllBidder as template group ",
					"Tender is created successfully using createTender_UVoltasDEMO_generalInformation_AllBidder as template group"
							+ " ",
					"Pass", "Y");
			log.info("completed executing the method:: createTender_UVoltasDEMO_generalInformation_AllBidder");
		} catch (Exception e) {
			log.fatal("Not able to create tender" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to create tender",
					"Not able to create tender using createTender_UVoltasDEMO_generalInformation_AllBidder as template group",
					"Unable to create tender using createTender_UVoltasDEMO_generalInformation_AllBidder as template group"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void waitforBidSubmissionDueDateTime() {
		try {
			Timestamp ts1 = Timestamp.valueOf(localdatetimeForBid);
			for (int i = 0; i < 20; i++) {
				LocalDateTime localdatetime1 = LocalDateTime.now();
				System.out.println("Bid Start Time ==> " + localdatetimeForBid);
				System.out.println("Now Time ==> " + localdatetime1);
				Timestamp ts2 = Timestamp.valueOf(localdatetime1);
				int diff = ts2.compareTo(ts1);
				System.out.println(diff);
				if (diff < 0) {
					System.out.println("Waiting for Bid Start Date");
					Thread.sleep(30000);
					System.out.println("App is Active : " + ThreadLocalWebdriver.getDriver().getTitle());
				} else {
					break;
				}
			}
		} catch (Exception e) {
		}

	}

	public void mandatoryFieldValidation_attachmentsTab_bidsubmission() throws Throwable {
		try {
			log.info("started executing the method:: mandatoryFieldValidation_attachmentsTab_bidsubmission");
			click(tendercreationlocators.attachementsTab, "attachementsTab");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			click(tendercreationlocators.addtenderattachmenticon, "addtenderattachmenticon");
			set(tendercreationlocators.upload, System.getProperty("user.dir") + "\\MediaFiles\\rfqCreation.xlsx",
					"fileName");
			click(tendercreationlocators.savebutton, "savebutton");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			IsElementPresent(tendercreationlocators.alertPopUp_QRC_bidSubmission);
			click(tendercreationlocators.alertClose_QRC_bidSubmission, "alertClose_QRC_bidSubmission");
			waitForObj(5000);
			pdfResultReport.addStepDetails("Successfully validate attachmentsTab ",
					"attachmentsTab must be validate successfully", "Successfully validated attachmentsTab" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: mandatoryFieldValidation_attachmentsTab_bidsubmission");
		} catch (Exception e) {
			log.fatal("Not able to validate attachmentsTab" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to validate attachmentsTab ",
					"Not able to validate attachmentsTab", "Unable to validate" + e.getMessage(), "Fail", "N");
		}
	}

	public void mandatoryFieldValidation_quotationSummaryTab_bidsubmission() throws Throwable {
		try {
			log.info("started executing the method:: mandatoryFieldValidation_quotationSummaryTab_bidsubmission");
			click(tendercreationlocators.quotationSummary_bidSubmission, "quotationSummary_bidSubmission");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			click(tendercreationlocators.savebutton, "savebutton");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.alertPopUp_QRC_bidSubmission);
			IsElementPresent(tendercreationlocators.quotationSummaryAlertTab_bidSubmission);
			click(tendercreationlocators.alertClose_QRC_bidSubmission, "alertClose_QRC_bidSubmission");
			waitForObj(5000);
			pdfResultReport.addStepDetails("Successfully validate quotationSummaryTab",
					"quotationSummaryTab must be validate successfully",
					"Successfully validate quotationSummaryTab" + " ", "Pass", "Y");
			log.info("completed executing the method:: mandatoryFieldValidation_quotationSummaryTab_bidsubmission");
		} catch (Exception e) {
			log.fatal("Not able to validate quotationSummaryTab" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to validate quotationSummaryTab",
					"Not able to validate quotationSummaryTab",
					"Unable to validate quotationSummaryTab" + e.getMessage(), "Fail", "N");
		}
	}

	public void evaluationSendForApproval() throws Throwable {
		try {
			log.info("started executing the method:: evaluationSendForApproval");
			waitForObj(5000);
			JSClick(tendercreationlocators.evaluation_sendForApproval, "evaluation_sendForApproval");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForObj(10000);
			pdfResultReport.addStepDetails("evaluationSendForApproval", "send for approval must be done successfully",
					"Successfully done send for approval" + " ", "Pass", "Y");
			log.info("completed executing the method:: evaluationSendForApproval");
		} catch (Exception e) {
			log.fatal("Not able to send for approval" + e.getMessage());
			pdfResultReport.addStepDetails("evaluationSendForApproval", "send for approval must be done successfully",
					"Unable to send for approval " + e.getMessage(), "Fail", "N");
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

	String tenderRef = null;

	public void Tender_WithOptionalItemsAndQtyEditableGeneralInfoWithThreeBidders(String TemplateGroup)
			throws Exception {
		try {
			log.info(
					"started executing the method:: Tender_WithOptionalItemsAndQtyEditableGeneralInfoWithThreeBidders()");
			waitForElement(tendercreationlocators.templateGroupdropdown, 5000);
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			select(tendercreationlocators.templateGroupdropdown, TemplateGroup);
			select(tendercreationlocators.bitPartdropdown, pdfResultReport.testData.get("BidParts"));
			click(tendercreationlocators.bitPartRadio, "bitPartRadio");

			tenderRef = "TendRef_";

			int getrandomInterger = getrandomInterger(10000, 1000000000);

			tenderRef = tenderRef.concat(String.valueOf(getrandomInterger));

			updateDataIntoPropertyFile("TenderRefNumber", tenderRef);

			set(tendercreationlocators.tenderReferenceNumber, tenderRef, "tenderReferenceNumber");
			select(tendercreationlocators.procurementCategory, "Default_cat");
			select(tendercreationlocators.tenderCurrency, pdfResultReport.testData.get("TenderCurrency"));
			set(tendercreationlocators.probabaleAmountContract, getDataFromPropertiesFile("ProbableAmountOfContract"),
					"probabaleAmountContract");
			select(tendercreationlocators.biddingCurrency, pdfResultReport.testData.get("BiddingCurrency"));
			select(tendercreationlocators.tenderType, pdfResultReport.testData.get("TenderType"));
			//added on 02/11/2020 in AWS QA env
			set(tendercreationlocators.Min_bid_no, pdfResultReport.testData.get("Minimum_no_of_Bids"), "MinimumNoOfBids");
			set(tendercreationlocators.detailedDescription,
					pdfResultReport.testData.get("CreateTender-DetailedDescription"), "detailedDescription");

			click(tendercreationlocators.savebutton, "savebutton");
			waitForObj(3000);
			pdfResultReport.addStepDetails("General Info tab filling for the TG: '"+TemplateGroup+"'",
					"Should save generalInfo tab fields", "Sucessfully saved generalInfo tab fields " + " ", "Pass",
					"Y");
			log.info(
					"completed executing the method:: Tender_WithOptionalItemsAndQtyEditableGeneralInfoWithThreeBidders");

		} catch (Exception e) {

			log.fatal("Not able to save generalInfo tab fields" + e.getMessage());
			pdfResultReport.addStepDetails("General Info tab filling for the TG: '"+TemplateGroup+"'",
					"Should save generalInfo tab fields", "Unable to save generalInfo tab fields" + e.getMessage(),
					"Fail", "N");
		}
	}

	public void Tender_WithOptionalItemsAndQtyEditableOtherAttachments() throws Exception {
		try {
			log.info("started executing the method:: Tender_WithOptionalItemsAndQtyEditableOtherAttachments");
			waitForElement(tendercreationlocators.editTenderTitle, 10000);
			WebDriver driver = ThreadLocalWebdriver.getDriver();

			waitTillSpinnerDisable(driver, tendercreationlocators.LoadingBy);

			click(tendercreationlocators.otherAttachements, "otherAttachements");
			waitForObj(5000);

			waitTillSpinnerDisable(driver, tendercreationlocators.LoadingBy);

			click(tendercreationlocators.addtenderattachmenticon, "addtenderattachmenticon");

			waitForElementToBeVisible(tendercreationlocators.supportingdocument);

			set(tendercreationlocators.supportingdocument, pdfResultReport.testData.get("OtherAttachments"),
					"supportingdocument");
			click(tendercreationlocators.buyerattachemntOK, "buyerattachemntOK");

			click(tendercreationlocators.savebutton, "savebutton");

			waitForObj(3000);
			pdfResultReport.addStepDetails("Tender_WithOptionalItemsAndQtyEditableOtherAttachments",
					"should save the other attactments tab field",
					"Sucessfully save other attachements tab fields " + " ", "Pass", "Y");
			log.info("completed executing the method:: Tender_WithOptionalItemsAndQtyEditableOtherAttachments");

		} catch (Exception e) {

			log.fatal("Unable to save other attachements tab fields" + e.getMessage());
			pdfResultReport.addStepDetails("Tender_WithOptionalItemsAndQtyEditableOtherAttachments",
					"should save the other attactments tab field",
					"Unable to save other attachements tab fields" + e.getMessage(), "Fail", "N");
		}
	}

	public void Tender_WithOptionalItemsAndQtyEditableProjectTab() throws Exception {
		try {
			log.info("started executing the method:: Tender_WithOptionalItemsAndQtyEditableProjectTab");

			WebDriver driver = ThreadLocalWebdriver.getDriver();

			waitTillSpinnerDisable(driver, tendercreationlocators.LoadingBy);

			click(tendercreationlocators.projectDetailsTab, "projectDetailsTab");

			waitForElementToBeVisible(tendercreationlocators.projectName);

			waitTillSpinnerDisable(driver, tendercreationlocators.LoadingBy);

			set(tendercreationlocators.projectName, pdfResultReport.testData.get("ProjectName"), "projectName");
			set(tendercreationlocators.projectLocation, pdfResultReport.testData.get("ProjectLocation"),
					"projectLocation");
			set(tendercreationlocators.product, pdfResultReport.testData.get("Product"), "product");
			set(tendercreationlocators.contactPerson, pdfResultReport.testData.get("ContactPerson"), "contactPerson");
			click(tendercreationlocators.savebutton, "savebutton");

			waitForObj(3000);

			pdfResultReport.addStepDetails("Tender_WithOptionalItemsAndQtyEditableProjectTab",
					"Should save with project tab fields", "Sucessfully saved with project tab fields " + " ", "Pass",
					"Y");
			log.info("completed executing the method:: Tender_WithOptionalItemsAndQtyEditableProjectTab");

		} catch (Exception e) {

			log.fatal("Unable to save with project tab fields" + e.getMessage());
			pdfResultReport.addStepDetails("Tender_WithOptionalItemsAndQtyEditableProjectTab",
					"Should save with project tab fields", "Unable to save with project tab fields" + e.getMessage(),
					"Fail", "N");
		}
	}

	public void Tender_WithOptionalItemsAndQtyEditableAttachmentsTab() throws Exception {
		try {
			log.info("started executing the method:: Tender_WithOptionalItemsAndQtyEditableAttachmentsTab");
			WebDriver driver = ThreadLocalWebdriver.getDriver();
			waitTillSpinnerDisable(driver, tendercreationlocators.LoadingBy);
			click(tendercreationlocators.attachementsTab, "attachementsTab");

			waitTillSpinnerDisable(driver, tendercreationlocators.LoadingBy);

			click(tendercreationlocators.openattachementsTab, "openattachementsTab");

			waitForElementToBeVisible(tendercreationlocators.label);

			set(tendercreationlocators.label, pdfResultReport.testData.get("Attachments-Label"), "label");
			set(tendercreationlocators.fileName, System.getProperty("user.dir") + "\\MediaFiles\\rfqCreation.xlsx",
					"fileName");

			waitTillSpinnerDisable(driver, tendercreationlocators.LoadingBy);

			select(tendercreationlocators.VisibleTo, pdfResultReport.testData.get("Attachments-VisibleTo"));

			click(tendercreationlocators.attachmentOKbutton, "attachmentOKbutton");
			click(tendercreationlocators.savebutton, "savebutton");

			waitForObj(3000);

			waitTillSpinnerDisable(driver, tendercreationlocators.LoadingBy);

			click(tendercreationlocators.next, "next");
			pdfResultReport.addStepDetails("Tender_WithOptionalItemsAndQtyEditableAttachmentsTab",
					"Should save the Attachment tabs with fields",
					"Sucessfully save dthe Attachments tab fields " + " ", "Pass", "Y");
			log.info("completed executing the method:: Tender_WithOptionalItemsAndQtyEditableAttachmentsTab");

		} catch (Exception e) {

			log.fatal("Unable to save dthe Attachments tab fields" + e.getMessage());
			pdfResultReport.addStepDetails("Tender_WithOptionalItemsAndQtyEditableAttachmentsTab",
					"Should save the Attachment tabs with fields",
					"Unable to save dthe Attachments tab fields " + e.getMessage(), "Fail", "N");
		}

	}

	public void Tender_WithOptionalItemsAndQtyEditableDateSchedule(int startdatelag, int enddatelag, int opendatelag)
			throws Exception {
		try {
			log.info("started executing the method:: Tender_WithOptionalItemsAndQtyEditableDateSchedule");
			click(tendercreationlocators.dateschedule, "dateschedule");

			waitForElementToBeVisible(tendercreationlocators.bidsubmissionStartDate);

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			set(tendercreationlocators.bidsubmissionStartDate, getBidStartDate(startdatelag), "bidsubmissionStartDate");
			set(tendercreationlocators.bidsubmissionDueDate, getBidDueDate(enddatelag), "bidsubmissionDueDate");
			set(tendercreationlocators.bidsubmissionOpenDate, getBidOpenDate(opendatelag), "bidsubmissionOpenDate");
	
			click(tendercreationlocators.savebutton, "savebutton");

			waitForObj(3000);

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			pdfResultReport.addStepDetails("Tender_WithOptionalItemsAndQtyEditableDateSchedule",
					"Should save the Date scheduled fields ", "Sucessfully saved the Date Schedule fields " + " ",
					"Pass", "Y");
			log.info("completed executing the method:: Tender_WithOptionalItemsAndQtyEditableDateSchedule");

		} catch (Exception e) {

			log.fatal("Unable to save the Date Schedule fields" + e.getMessage());
			pdfResultReport.addStepDetails("Tender_WithOptionalItemsAndQtyEditableDateSchedule",
					"Should save the Date scheduled fields", "Unable to save the Date Schedule fields" + e.getMessage(),
					"Fail", "N");
		}

	}

	public void Tender_WithOptionalItemsAndQtyEditableBOQOptional() throws Exception {
		try {
			log.info("started executing the method:: Tender_WithOptionalItemsAndQtyEditableBOQOptional");

			WebDriver driver = ThreadLocalWebdriver.getDriver();

			waitForObj(2000);
			click(tendercreationlocators.BOQOptionalTab, "BOQOptionalTab");

			waitForElementToBeVisible(tendercreationlocators.addNonSORItem);
			waitTillSpinnerDisable(driver, tendercreationlocators.LoadingBy);

			click(tendercreationlocators.addNonSORItem, "addNonSORItem");

			set(tendercreationlocators.BOQOptionItemCode, pdfResultReport.testData.get("BOQ(Optional)-ItemCode"),
					"BOQOptionItemCode");
			set(tendercreationlocators.BOQOptionItemDescription,
					pdfResultReport.testData.get("BOQ(Optional)-ItemDescription"), "BOQOptionItemDescription");
			select(tendercreationlocators.BOQOptionUOM, pdfResultReport.testData.get("UOM"));
			set(tendercreationlocators.projectquantity, pdfResultReport.testData.get("ProjectQuantity"),
					"projectquantity");
			set(tendercreationlocators.preferedmake, pdfResultReport.testData.get("PreferedMaker"), "preferedmake");
			set(tendercreationlocators.budgetedrate, pdfResultReport.testData.get("BudgetedRate"), "budgetedrate");
			set(tendercreationlocators.remarks, pdfResultReport.testData.get("Remarks"), "remarks");

			click(tendercreationlocators.savebutton, "savebutton");

			waitForObj(2000);

			waitTillSpinnerDisable(driver, tendercreationlocators.LoadingBy);

			pdfResultReport.addStepDetails("Tender_WithOptionalItemsAndQtyEditableBOQOptional",
					"Should save the BOQ optional fields", "Sucessfully saved the  BOQ optional fields " + " ", "Pass",
					"Y");
			log.info("completed executing the method:: Tender_WithOptionalItemsAndQtyEditableBOQOptional");

		} catch (Exception e) {

			log.fatal("Unable to saved the  BOQ optional fields" + e.getMessage());
			pdfResultReport.addStepDetails("Tender_WithOptionalItemsAndQtyEditableBOQOptional",
					"Should save the BOQ optional fields", "Unable to saved the  BOQ optional fields" + e.getMessage(),
					"Fail", "N");
		}
	}

	public void Tender_WithOptionalItemsAndQtyEditableTermsAndCondition(int j) throws Exception {
		try {
			log.info("started executing the method::Tender_WithOptionalItemsAndQtyEditableTermsAndCondition");

			WebDriver driver = ThreadLocalWebdriver.getDriver();

			waitForObj(2000);

			click(tendercreationlocators.termsConditionsTab, "termsConditionsTab");

			waitForElementToBeVisible(tendercreationlocators.termsconditionsAdd);

			int l = 1;
			while (l <= j) {
				click(tendercreationlocators.termsconditionsAdd, "termsconditionsAdd");
				l++;
			}

			List<WebElement> clauseRows = driver.findElements(By.xpath("//input[@placeholder='Clause']"));
			String clauseNo = pdfResultReport.testData.get("TermsClause");

			for (int i = 1; i <= clauseRows.size(); i++) {

				String clause = clauseNo.concat(String.valueOf(i));

				set(tendercreationlocators.TenderClauseField(i), clause, "termsconditionClause");

				set(tendercreationlocators.TenderClauseDetailsField(i), pdfResultReport.testData.get("TermsDetails"),
						"termsconditionDetails_WithOptionalItemsAndQtyEditable");

			}

			click(tendercreationlocators.savebutton, "savebutton");

			waitForObj(2000);

			waitTillSpinnerDisable(driver, tendercreationlocators.LoadingBy);

			pdfResultReport.addStepDetails("Tender_WithOptionalItemsAndQtyEditableTermsAndCondition",
					"Should save the Terms And Conditions fields",
					"Sucessfully saved the Terms And Conditions fields" + " ", "Pass", "Y");
			log.info("completed executing the method:: Tender_WithOptionalItemsAndQtyEditableTermsAndCondition");

		} catch (Exception e) {

			log.fatal("Unable to saved the Terms And Conditions fields" + e.getMessage());
			pdfResultReport.addStepDetails("Tender_WithOptionalItemsAndQtyEditableTermsAndCondition",
					"Should save the Terms And Conditions fields",
					"Unable to saved the Terms And Conditions fields" + e.getMessage(), "Fail", "N");
		}
	}

	public void Tender_WithOptionalItemsAndQtyEditableTechnical(int j) throws Exception {
		try {
			log.info("started executing the method:: Tender_WithOptionalItemsAndQtyEditableTechnical");

			WebDriver driver = ThreadLocalWebdriver.getDriver();

			waitForObj(2000);

			click(tendercreationlocators.technical, "technical");

			waitTillSpinnerDisable(driver, tendercreationlocators.LoadingBy);

			int l = 1;
			while (l <= j) {
				click(tendercreationlocators.technicalAdd_WithOptionalItemsAndQtyEditable,
						"technicalAdd_WithOptionalItemsAndQtyEditable");
				l++;
			}

			List<WebElement> clauseRows = driver.findElements(By.xpath("//*[starts-with(@id, 'rh_techcompl.clause')]"));
			String clauseNo = pdfResultReport.testData.get("Technical-Clause");

			for (int i = 1; i <= clauseRows.size(); i++) {

				String clause = clauseNo.concat(String.valueOf(i));

				set(tendercreationlocators.TenderTechnicalClauseField(i), clause, "technicalclause");

				set(tendercreationlocators.TenderTechnicalDetailsField(i), "Technical Details", "technicalclause");
			}

			click(tendercreationlocators.savebutton, "savebutton");

			waitForObj(3000);

			waitTillSpinnerDisable(driver, tendercreationlocators.LoadingBy);

			click(tendercreationlocators.next, "next");

			pdfResultReport.addStepDetails("Tender_WithOptionalItemsAndQtyEditableTechnical",
					"should save the Technical Tab fileds", "Sucessfully saved the Technical tab fields " + " ", "Pass",
					"Y");
			log.info("completed executing the method:: Tender_WithOptionalItemsAndQtyEditableTechnical");

		} catch (Exception e) {

			log.fatal("Unable to save the Technical tab fields" + e.getMessage());
			pdfResultReport.addStepDetails("Tender_WithOptionalItemsAndQtyEditableTechnical",
					"should save the Technical Tab fileds", "Unable to save the Technical tab fields" + e.getMessage(),
					"Fail", "N");
		}

	}

	public void Tender_WithOptionalItemsAndQtyEditableBOQMandatortTab(int noofaddbtnclicks, int startingrow,
			int endingrow, String UnitMeasurement) throws Exception {
		try {
			log.info("started executing the method::  BOQParentItemMandatoryAndChildOptionForNonSoritem");

			WebDriver driver = ThreadLocalWebdriver.getDriver();

			waitForObj(2000);

			click(tendercreationlocators.BOQmandatoryTab, "BOQmandatoryTab");

			waitForElementToBeVisible(tendercreationlocators.addnonSORBOQMandatoryTab);

			waitTillSpinnerDisable(driver, tendercreationlocators.LoadingBy);

			int l = 1;
			while (l <= noofaddbtnclicks) {
				click(tendercreationlocators.addnonSORBOQMandatoryTab, "addnonSORBOQMandatoryTab");

				l++;

			}

			selectNonSorItems(startingrow, endingrow, UnitMeasurement);

			waitForObj(3000);

			click(tendercreationlocators.savebutton, "savebutton");

			waitForObj(3000);

			waitTillSpinnerDisable(driver, tendercreationlocators.LoadingBy);

			pdfResultReport.addStepDetails("BOQParentItemMandatoryAndChildOptionForNonSoritem",
					"Should Save BOQ Items data Sucessfully", "Succesfully Saved BOQ Items" + " ", "Pass", "Y");
			log.info("completed executing the method::  BOQParentItemMandatoryAndChildOptionForNonSoritem");

		} catch (Exception e) {

			log.fatal("Unable to " + e.getMessage());
			pdfResultReport.addStepDetails("BOQParentItemMandatoryAndChildOptionForNonSoritem",
					"Should save BOQ Items data  Sucessfully ", "Unable to save the BOQ Items data " + e.getMessage(),
					"Fail", "N");
		}
	}

	public void selectNonSorItems(int j, int k, String KG) throws IOException {

		List<WebElement> itemCode = ThreadLocalWebdriver.getDriver()
				.findElements(tendercreationlocators.boqSummaryItemCode);

		List<WebElement> itemDescription = ThreadLocalWebdriver.getDriver()
				.findElements(tendercreationlocators.boqSummaryItemDescription);

		List<WebElement> quantity = ThreadLocalWebdriver.getDriver()
				.findElements(tendercreationlocators.projectQtysItemsBy);

		List<WebElement> uom = ThreadLocalWebdriver.getDriver().findElements(tendercreationlocators.boqSummaryUom);

		List<WebElement> scheduleRate = ThreadLocalWebdriver.getDriver()
				.findElements(tendercreationlocators.boqSummarySorRate);

		List<WebElement> PreferedMake = ThreadLocalWebdriver.getDriver()
				.findElements(tendercreationlocators.preferedmake);

		List<WebElement> Remarks = ThreadLocalWebdriver.getDriver().findElements(tendercreationlocators.remarks);

		for (int i = j; i <= k; ++i) {

			itemCode.get(i).sendKeys(pdfResultReport.testData.get("BOQ(Mandatory)-ItemCode").concat(String.valueOf(i)));

			waitForObj(2000);
			itemDescription.get(i)
					.sendKeys(pdfResultReport.testData.get("BOQ(Mandatory)-ItemDescription").concat(String.valueOf(i)));
			waitForObj(2000);
			quantity.get(i).sendKeys(getDataFromPropertiesFile("TenderItemQty"));
			waitForObj(2000);
			Select select = new Select(uom.get(i));
			select.selectByIndex(i + 13);
			waitForObj(2000);
			scheduleRate.get(i).sendKeys(getDataFromPropertiesFile("TenderBudgetRate"));
			waitForObj(2000);
			PreferedMake.get(i).sendKeys(pdfResultReport.testData.get("PreferedMaker").concat(String.valueOf(i)));
			waitForObj(2000);
			Remarks.get(i).sendKeys(pdfResultReport.testData.get("Remarks").concat(String.valueOf(i)));
			waitForObj(2000);
		}

	}

	public void clickSubmitBtn() throws Exception {
		try {
			log.info("started executing the method:: clickSubmitBtn");

			click(tendercreationlocators.submitbutton, "submitbutton");

			waitForSpinnerToDisappear();

			waitForElementToBeVisible(tendercreationlocators.sendForApprovalNotRequired);

			pdfResultReport.addStepDetails("clickSubmitBtn",
					"Should click Submit Btn and  Send For Approval Pop Up should Appear",
					"Successfully clicked Submit Btn and  Send For Approval Pop Up is displayed " + " ", "Pass", "Y");
			log.info("completed executing the method::clickSubmitBtn");

		} catch (Exception e) {

			log.fatal("Unable to click Subtmin Btn Send For Approval Pop Up is not Appear" + e.getMessage());
			pdfResultReport.addStepDetails("clickSubmitBtn",
					"Should click Submit Btn and  Send For Approval Pop Up should Appear",
					"Unable to click Subtmin Btn Send For Approval Pop Up is not Appeared" + e.getMessage(), "Fail",
					"N");
		}

	}

	public void sendForNoApproval_validation() throws Throwable {
		try {
			log.info("started executing the method:: sendForNoApproval_validation");

			WebDriver driver = ThreadLocalWebdriver.getDriver();

			click(tendercreationlocators.sendForApprovalNotRequired, "sendForApprovalNotRequired");

			JSClick(tendercreationlocators.sendForApprovalSubmit, "sendForApprovalSubmit");

			WebDriverWait wait = new WebDriverWait(driver, 100);

			wait.until(ExpectedConditions.visibilityOfElementLocated(tendercreationlocators.defaultCatBy));

			waitTillSpinnerDisable(driver, tendercreationlocators.LoadingBy);

			pdfResultReport.addStepDetails("sendForNoApproval_validation",
					"Should click SendForApproval and Navigate to Tender List Page",
					"Successfully clicked SendForApproval btn and Navigated to Tender List Page" + " ", "Pass", "Y");
			log.info("completed executing the method:: sendForApproval_validation");
		} catch (Exception e) {
			log.fatal("Unable to click SendForApprovalbtn  and not  Navigated to Tender List Page" + e.getMessage());
			pdfResultReport.addStepDetails("sendForNoApproval_validation",
					"Should click SendForApproval and Navigate to Tender List Page",
					"Unable to click SendForApprovalbtn  and not  Navigated to Tender List Page" + e.getMessage(),
					"Fail", "N");
		}
	}

	public void enterTenderIdInSearch() throws Throwable {
		try {
			log.info("started executing the method:: enterTenderIdInSearch");

			clear(tendercreationlocators.tenderListKeyword, "tenderListKeyword");
			set(tendercreationlocators.tenderListKeyword, tenderReferenceNoLocatorText, "tenderListSearch");
			//waitForElementToBeVisible(tendercreationlocators.defaultCatBy);
			waitForObj(7000);
			
			pdfResultReport.addStepDetails("Successfully Saved",
					"Tender Id must be entered successfully in search field",
					"Tender Id is successfully entered in search field" + " ", "Pass", "Y");
			log.info("completed executing the method:: tenderIdSave");
		} catch (Exception e) {
			log.fatal("Not able to create tender" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to create tender", "Not able to enter Tender Id in search field",
					"Unable to enter Tender Id in search field" + e.getMessage(), "Fail", "N");
		}
	}

	public void navigateToLiveTenderTab_bidsubmission() throws Throwable {
		try {
			log.info("started executing the method:: navigateToLiveTenderTab_bidsubmission");
			waitForObj(3000);

			WebDriver driver = ThreadLocalWebdriver.getDriver();

			JSClick(tendercreationlocators.bidSubmissionTransaction, "bidSubmissionTransaction");

			JSClick(tendercreationlocators.bidSubmissionTenderListing, "bidSubmissionTenderListing");

			waitForElementToBeVisible(tendercreationlocators.SpinnerHolderBy);

			checkPageIsReady();

			waitForElementToBeVisible(tendercreationlocators.LiveTenderRecordsBy);

			waitTillSpinnerDisable(driver, tendercreationlocators.LoadingBy);

			waitForObj(5000);

			pdfResultReport.addStepDetails("navigateToLiveTenderTab_bidsubmission",
					"Live Tender Tab must be navigated successfully", "Successfully navigated to Live Tender Tab" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: navigateToLiveTenderTab_bidsubmission");

		} catch (Exception e) {
			log.fatal("Unable to navigate to the Live Tender Tab" + e.getMessage());
			pdfResultReport.addStepDetails("navigateToLiveTenderTab_bidsubmission",
					"Live Tender Tab must be navigated successfully",
					"Unable to navigate to the Live Tender Tab" + e.getMessage(), "Fail", "N");
		}
	}

	public void navigateToActionDropdown_bidsubmission() throws Throwable {
		try {
			log.info("started executing the method:: navigateToActionDropdown_bidsubmission");
			click(tendercreationlocators.action, "action");

			click(tendercreationlocators.bidSubmission_Bid, "bidSubmission_Bid");

			waitForObj(3000);
			WebDriver driver = ThreadLocalWebdriver.getDriver();
			waitTillSpinnerDisable(driver, tendercreationlocators.LoadingBy);

			pdfResultReport.addStepDetails("navigateToActionDropdown_bidsubmission",
					"navigate to action dropdown must be done successfully and should Navigated to accept or decline page",
					"Successfully clicked action dropdown and Navigated to accept or decline page" + " ", "Pass", "Y");
			log.info("completed executing the method:: navigateToActionDropdown_bidsubmission");
		} catch (Exception e) {
			log.fatal("Not able to Navigated to accept or decline page" + e.getMessage());
			pdfResultReport.addStepDetails("navigateToActionDropdown_bidsubmission",
					"navigate to action dropdown must be done successfully and should Navigated to accept or decline page",
					"Not able to Navigated to accept or decline page" + e.getMessage(), "Fail", "N");
		}
	}

	public void newTermsAndServicesCheckBox_bidsubmission() throws Throwable {
		try {
			log.info("started executing the method:: newTermsAndServicesCheckBox_bidsubmission");

			waitForElementToBeVisible(tendercreationlocators.acceptOrDeclineTitle);

			WebDriver driver = ThreadLocalWebdriver.getDriver();
			waitTillSpinnerDisable(driver, tendercreationlocators.LoadingBy);

			waitForObj(3000);

			JSClick(tendercreationlocators.newTermsAndServices_CheckBox, "newTermsAndServices_CheckBox");
			JSClick(tendercreationlocators.newTermsAndServices_Accept, "newTermsAndServices_Accept");

			pdfResultReport.addStepDetails("newTermsAndServicesCheckBox_bidsubmission",
					"accept button must be click successfully and should display general info page",
					"Successfully clicked on accept button and displaying general info page" + " ", "Pass", "Y");
			log.info("completed executing the method:: newTermsAndServicesCheckBox_bidsubmission");
		} catch (Exception e) {
			log.fatal("Not able to navigate to generalInfo page " + e.getMessage());
			pdfResultReport.addStepDetails("newTermsAndServicesCheckBox_bidsubmission",
					"accept button must be click successfully and should display general info page",
					"Not able to navigate to generalInfo page" + e.getMessage(), "Fail", "N");
		}
	}

	public void generalInformationPageValidation_bidsubmission() throws Throwable {
		try {
			log.info("started executing the method:: generalInformationPageValidation_bidsubmission");

			waitForElementToBeVisible(tendercreationlocators.tenderId_generalInformation_bidSubmission);

			IsElementPresent(tendercreationlocators.tenderId_generalInformation_bidSubmission);
			IsElementPresent(tendercreationlocators.tenderDescription_generalInformation_bidSubmission);
			IsElementPresent(tendercreationlocators.tenderCategory_generalInformation_bidSubmission);
			IsElementPresent(tendercreationlocators.quotationReferenceCode_generalInformation_bidSubmission);
			IsElementPresent(tendercreationlocators.quotationCurrency_generalInformation_bidSubmission);

			pdfResultReport.addStepDetails("generalInformationPageValidation_bidsubmission",
					"general information elements must be valiadted successfully",
					"Successfully validated general information elements" + " ", "Pass", "Y");
			log.info("completed executing the method:: generalInformationPageValidation_bidsubmission");
		} catch (Exception e) {
			log.fatal("Failed  due to validation  general information elements" + e.getMessage());
			pdfResultReport.addStepDetails("generalInformationPageValidation_bidsubmission",
					"general information elements must be valiadted successfully",
					"Failed due to validation  general information elements" + e.getMessage(), "Fail", "N");
		}
	}
	//specific to TG1 ("Indigenous Tender (Supply & Service Both) V-1.0") templategroup (16/02/2021)
	public void TG1_generalInformationPageValidation_bidsubmission() throws Throwable {
		try {
			log.info("started executing the method:: TG1_generalInformationPageValidation_bidsubmission");

			waitForElementToBeVisible(tendercreationlocators.tenderId_generalInformation_bidSubmission);

			IsElementPresent(tendercreationlocators.tenderId_generalInformation_bidSubmission);
			IsElementPresent(tendercreationlocators.tenderDescription_generalInformation_bidSubmission);
			//IsElementPresent(tendercreationlocators.tenderCategory_generalInformation_bidSubmission);
			IsElementPresent(tendercreationlocators.quotationReferenceCode_generalInformation_bidSubmission);
			IsElementPresent(tendercreationlocators.quotationCurrency_generalInformation_bidSubmission);

			pdfResultReport.addStepDetails("TG1_generalInformationPageValidation_bidsubmission",
					"general information elements must be valiadted successfully",
					"Successfully validated general information elements" + " ", "Pass", "Y");
			log.info("completed executing the method:: TG1_generalInformationPageValidation_bidsubmission");
		} catch (Exception e) {
			log.fatal("Failed  due to validation  general information elements" + e.getMessage());
			pdfResultReport.addStepDetails("TG1_generalInformationPageValidation_bidsubmission",
					"general information elements must be valiadted successfully",
					"Failed due to validation  general information elements" + e.getMessage(), "Fail", "N");
		}
	}

	public void quotationReferenceCode_bidsubmission() throws Throwable {
		try {
			log.info("started executing the method:: quotationReferenceCode_bidsubmission");

			JSClick(tendercreationlocators.savebutton, "savebutton");

			waitForElementToBeVisible(tendercreationlocators.errorMessage_QRC_bidSubmission);

			waitForObj(2000);

			IsElementPresent(tendercreationlocators.alertPopUp_QRC_bidSubmission);
			IsElementPresent(tendercreationlocators.errorMessage_QRC_bidSubmission);
			JSClick(tendercreationlocators.alertClose_QRC_bidSubmission, "alertClose_QRC_bidSubmission");
			waitForObj(2000);
			set(tendercreationlocators.quotationReferenceCodeInput_generalInformation_bidSubmission,
					pdfResultReport.testData.get("QuotationReferenceCode"),
					"quotationReferenceCodeInput_generalInformation_bidSubmission");

			JSClick(tendercreationlocators.savebutton, "savebutton");
			waitForObj(5000);

			//waitForElementToBeVisible(tendercreationlocators.technical);

			WebDriver driver = ThreadLocalWebdriver.getDriver();
			waitTillSpinnerDisable(driver, tendercreationlocators.LoadingBy);

			pdfResultReport.addStepDetails("quotationReferenceCode_bidsubmission", "Must save general Info Details",
					"Successfully saved generfal info details " + " ", "Pass", "Y");
			log.info("completed executing the method:: quotationReferenceCode_bidsubmission");
		} catch (Exception e) {
			log.fatal("Unable to Save general Info details" + e.getMessage());
			pdfResultReport.addStepDetails("quotationReferenceCode_bidsubmission", "Must save general Info Details",
					"Unable to Save general Info details" + e.getMessage(), "Fail", "N");
		}
	}

	public void mandatoryFieldValidation_technicalTab_bidsubmission() throws Throwable {
		try {
			log.info("started executing the method:: mandatoryFieldValidation_technicalTab_bidsubmission");
			waitForObj(5000);
			click(tendercreationlocators.technical, "technical");
			waitForSpinnerToDisappearInBidSubmission();
			waitForElementToBeVisible(tendercreationlocators.technicalTabFileuploadEleBy);

			/*
			 * click(tendercreationlocators.savebutton, "savebutton");
			 * waitForObj(3000); waitForSpinnerToDisappearInBidSubmission();
			 * waitForElementToBeVisible(tendercreationlocators.
			 * alertPopUp_QRC_bidSubmission);
			 * IsElementPresent(tendercreationlocators.
			 * alertPopUp_QRC_bidSubmission);
			 * IsElementPresent(tendercreationlocators.
			 * technicalAlertTab_bidSubmission); waitForObj(2000);
			 * click(tendercreationlocators.alertClose_QRC_bidSubmission,
			 * "alertClose_QRC_bidSubmission");
			 */
			waitForObj(2000);
			pdfResultReport.addStepDetails("mandatoryFieldValidation_technicalTab_bidsubmission",
					"Technical tab must be validate successfully", "Successfully validated technical tab" + " ", "Pass",
					"Y");
			log.info("completed executing the method:: mandatoryFieldValidation_technicalTab_bidsubmission");
		} catch (Exception e) {
			log.fatal("failed due to validating technical tab details" + e.getMessage());
			pdfResultReport.addStepDetails("mandatoryFieldValidation_technicalTab_bidsubmission",
					"Technical tab must be validate successfully",
					"failed due to validating technical tab details" + e.getMessage(), "Fail", "N");
		}
	}

	public void mandatoryFieldValidation_termsAndConditionTab_bidsubmission() throws Throwable {
		try {
			log.info("started executing the method:: mandatoryFieldValidation_termsAndConditionTab_bidsubmission");

			waitForObj(2000);

			click(tendercreationlocators.termsAndCondition_bidSubmission, "termsAndCondition_bidSubmission");

			waitForObj(2000);

			waitForSpinnerToDisappearInBidSubmission();

			waitForElementToBeVisible(tendercreationlocators.termsAndConditionsClauseBy);

			/*
			 * JSClick(tendercreationlocators.savebutton, "savebutton");
			 * 
			 * waitForObj(2000);
			 * 
			 * waitForSpinnerToDisappearInBidSubmission();
			 * 
			 * waitForElementToBeVisible(tendercreationlocators.
			 * termsAndConditionAlertTab_bidSubmission);
			 * 
			 * IsElementPresent(tendercreationlocators.
			 * alertPopUp_QRC_bidSubmission);
			 * 
			 * IsElementPresent(tendercreationlocators.
			 * termsAndConditionAlertTab_bidSubmission);
			 * 
			 * waitForObj(2000);
			 * 
			 * click(tendercreationlocators.alertClose_QRC_bidSubmission,
			 * "alertClose_QRC_bidSubmission");
			 */

			waitForObj(2000);

			pdfResultReport.addStepDetails("mandatoryFieldValidation_termsAndConditionTab_bidsubmission",
					"Terms And Condition Tab must be validate successfully",
					"Successfully validated terms And Condition Tab " + " ", "Pass", "Y");
			log.info("completed executing the method:: mandatoryFieldValidation_termsAndConditionTab_bidsubmission");
		} catch (Exception e) {
			log.fatal("Not able to validate terms And Condition Tab" + e.getMessage());
			pdfResultReport.addStepDetails("mandatoryFieldValidation_termsAndConditionTab_bidsubmission",
					"Terms And Condition Tab must be validate successfully",
					"Unable to validate terms And Condition Tab" + e.getMessage(), "Fail", "N");
		}
	}

	public void mandatoryFieldValidation_boqOptionalTab(String SupplierQuotedtQty, String exculdingVatValue)
			throws Throwable {
		try {
			log.info("started executing the method:: mandatoryFieldValidation_boqOptionalTab");
			waitForObj(2000);

			click(tendercreationlocators.BOQOptionalTab, "BOQOptionalTab");

			waitForObj(2000);

			waitForSpinnerToDisappearInBidSubmission();

			waitForElementToBeVisible(tendercreationlocators.boqOptionalAddOptionalBy);

			WebDriver driver = ThreadLocalWebdriver.getDriver();

			WebElement suppQty = driver.findElement(tendercreationlocators.boqOptionalSupQuoteQuantBy);

			Coordinates coordinates = ((Locatable) suppQty).getCoordinates();
			coordinates.onPage();
			coordinates.inViewPort();
			suppQty.clear();
			suppQty.sendKeys(SupplierQuotedtQty);

			waitForObj(2000);

			WebElement vatvalue = driver.findElement(tendercreationlocators.excludingVATFieldBy);

			Coordinates coordinates1 = ((Locatable) vatvalue).getCoordinates();
			coordinates1.onPage();
			coordinates1.inViewPort();
			vatvalue.sendKeys(exculdingVatValue);

			/*
			 * js_type(tendercreationlocators.boqOptionalSupQuoteQuantBy,
			 * SupplierQuotedtQty, "boqOptionalSupQuoteQuantBy");
			 * 
			 * waitForObj(2000);
			 * 
			 * js_type(tendercreationlocators.excludingVATFieldBy,
			 * exculdingVatValue, "excludingVATFieldBy");
			 */
			/*
			 * click(tendercreationlocators.savebutton, "savebutton");
			 * 
			 * waitForObj(3000);
			 * 
			 * waitForSpinnerToDisappearInBidSubmission();
			 * 
			 * IsElementPresent(tendercreationlocators.
			 * alertPopUp_QRC_bidSubmission);
			 * IsElementPresent(tendercreationlocators.
			 * boqOptionalAlertTab_BidSubmissionBy);
			 * 
			 * waitForObj(2000);
			 * click(tendercreationlocators.alertClose_QRC_bidSubmission,
			 * "alertClose_QRC_bidSubmission");
			 */

			waitForObj(2000);

			pdfResultReport.addStepDetails("mandatoryFieldValidation_boqOptionalTab",
					"Boq optional tab must be validate And saved successfully",
					"Successfully validated  and saved boqOptional tab details" + " ", "Pass", "Y");
			log.info("completed executing the method:: mandatoryFieldValidation_boqOptionalTab");
		} catch (Exception e) {
			log.fatal("Not able to save  boq optional tab details" + e.getMessage());
			pdfResultReport.addStepDetails("mandatoryFieldValidation_boqOptionalTab",
					"Boq optional tab must be validate And saved successfully",
					"Not able to save  boq optional tab details" + e.getMessage(), "Fail", "N");
		}
	}

	public void mandatoryFieldValidation_attachmentsTab() throws Throwable {
		try {
			log.info("started executing the method:: mandatoryFieldValidation_attachmentsTab");

			waitForObj(2000);

			click(tendercreationlocators.attachementsTab, "attachementsTab");

			waitForObj(2000);
			waitForSpinnerToDisappearInBidSubmission();
			click(tendercreationlocators.attachment_subtab, "Attachment_subtab");
			waitForElementToBeVisible(tendercreationlocators.addtenderattachmenticon);
			click(tendercreationlocators.addtenderattachmenticon, "addtenderattachmenticon");

			set(tendercreationlocators.attachmentLabelFieldBy, "Label1", "attachmentLabelFieldBy");

			set(tendercreationlocators.upload, System.getProperty("user.dir") + "\\MediaFiles\\rfqCreation.xlsx",
					"fileName");
			waitForObj(2000);

			click(tendercreationlocators.bidderSpecAttachmentBy, "bidderSpecAttachmentBy");

			waitForObj(2000);

			click(tendercreationlocators.actionMenuDropDownBy, "actionMenuDropDownBy");

			waitForElementToBeVisible(tendercreationlocators.bidderSpecAttachmentUploadBy);

			// set(tendercreationlocators.bidderSpecAttachmentUploadBy,
			// System.getProperty("user.dir") +
			// "\\MediaFiles\\rfqCreation.xlsx",
			// "bidderSpecAttachmentUploadBy");

			waitForObj(2000);

			click(tendercreationlocators.tenderAttachmentBy, "tenderAttachmentBy");

			waitForObj(2000);

			/*
			 * click(tendercreationlocators.savebutton, "savebutton");
			 * 
			 * waitForObj(3000);
			 * 
			 * waitForSpinnerToDisappearInBidSubmission();
			 * 
			 * IsElementPresent(tendercreationlocators.
			 * alertPopUp_QRC_bidSubmission);
			 * 
			 * waitForObj(2000);
			 * click(tendercreationlocators.alertClose_QRC_bidSubmission,
			 * "alertClose_QRC_bidSubmission");
			 */

			waitForObj(2000);

			pdfResultReport.addStepDetails("mandatoryFieldValidation_attachmentsTab",
					"attachmentsTab must be validate successfully", "Successfully validated attachmentsTab" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: mandatoryFieldValidation_attachmentsTab");
		} catch (Exception e) {
			log.fatal("Not able to validate attachmentsTab" + e.getMessage());
			pdfResultReport.addStepDetails("mandatoryFieldValidation_attachmentsTab ",
					"Not able to validate attachmentsTab", "Unable to validate" + e.getMessage(), "Fail", "N");
		}
	}

	public void mandatoryFieldValidation_commercialTab_bidsubmission() throws Throwable {
		try {
			log.info("started executing the method:: mandatoryFieldValidation_commercialTab_bidsubmission");
			waitForObj(2000);
			click(tendercreationlocators.commercial_bidSubmission, "commercial_bidSubmission");

			waitForObj(2000);

			waitForSpinnerToDisappear();

			waitForElementToBeVisible(tendercreationlocators.documentChargeFieldEleBy);

			/*
			 * click(tendercreationlocators.savebutton, "savebutton");
			 * waitForObj(2000); waitForSpinnerToDisappearInBidSubmission();
			 * waitForElementToBeVisible(tendercreationlocators.
			 * commercialAlertTab_bidSubmission);
			 * IsElementPresent(tendercreationlocators.
			 * alertPopUp_QRC_bidSubmission);
			 * IsElementPresent(tendercreationlocators.
			 * commercialAlertTab_bidSubmission); waitForObj(2000);
			 * click(tendercreationlocators.alertClose_QRC_bidSubmission,
			 * "alertClose_QRC_bidSubmission");
			 */
			waitForObj(2000);
			pdfResultReport.addStepDetails("Successfully validate commercialTab",
					"commercialTab must be validate successfully", "Successfully validate commercialTab" + " ", "Pass",
					"Y");
			log.info("completed executing the method:: mandatoryFieldValidation_commercialTab_bidsubmission");
		} catch (Exception e) {
			log.fatal("Not able to validate commercialTab" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to validate commercialTab", "Not able to validate commercialTab",
					"Unable to validate commercialTab" + e.getMessage(), "Fail", "N");
		}
	}

	public void mandatoryFieldValidation_BoqmandatoryTab(String suppQuoteQty, String vatValue) throws Throwable {

		try {
			log.info("started executing the method:: mandatoryFieldValidation_BoqmandatoryTab_bidsubmission");

			waitForObj(2000);
			click(tendercreationlocators.BOQmandatoryTab, "BOQmandatoryTab");
			waitForObj(2000);
			waitForSpinnerToDisappearInBidSubmission();

			waitForElementToBeVisible(tendercreationlocators.boqaddOptionItemButtonBy);

			int proposeMake = ThreadLocalWebdriver.getDriver().findElements(tendercreationlocators.boqproposedMakeBy)
					.size();

			for (int i = 0; i < proposeMake; i++) {
				String s = "//*[@id='qi_boq.proposed_make.{0}']";
				ThreadLocalWebdriver.getDriver().findElement(By.xpath(s.replace("{0}", +i + "")))
						.sendKeys("Proposedmake_" + i + "");

				waitForObj(1000);
			}

			int boqSuppQuoteQtySize = ThreadLocalWebdriver.getDriver()
					.findElements(tendercreationlocators.boqSuppQuoteQtyBy).size();

			for (int i = 0; i < boqSuppQuoteQtySize; i++) {

				String s = "//*[@id='qi_boq.supquotequant.{0}']";

				WebElement element = ThreadLocalWebdriver.getDriver().findElement(By.xpath(s.replace("{0}", +i + "")));
				Coordinates coordinates = ((Locatable) element).getCoordinates();
				coordinates.onPage();
				coordinates.inViewPort();
				element.clear();
				element.sendKeys(suppQuoteQty);
				waitForObj(1000);
			}

			int vatvalue = ThreadLocalWebdriver.getDriver().findElements(tendercreationlocators.boqBasicUnitVatBy)
					.size();

			for (int i = 0; i < vatvalue; i++) {
				String s = "//*[@id='qi_boq.basicunit.{0}']";

				WebElement element = ThreadLocalWebdriver.getDriver().findElement(By.xpath(s.replace("{0}", +i + "")));
				Coordinates coordinates = ((Locatable) element).getCoordinates();
				coordinates.onPage();
				coordinates.inViewPort();
				element.sendKeys(vatValue);
				waitForObj(1000);
			}

			click(tendercreationlocators.savebutton, "savebutton");
			waitForObj(2000);
			waitForSpinnerToDisappearInBidSubmission();

			waitForElementToBeVisible(tendercreationlocators.alertPopUp_QRC_bidSubmission);

			IsElementPresent(tendercreationlocators.alertPopUp_QRC_bidSubmission);

			IsElementPresent(tendercreationlocators.boqOptionalAlertTab_BidSubmissionBy);

			IsElementPresent(tendercreationlocators.technicalAlertTab_bidSubmission);

			IsElementPresent(tendercreationlocators.termsAndConditionAlertTab_bidSubmission);

			IsElementPresent(tendercreationlocators.commercialAlertTab_bidSubmission);

			waitForObj(2000);

			click(tendercreationlocators.alertClose_QRC_bidSubmission, "alertClose_QRC_bidSubmission");

			waitForObj(2000);

			pdfResultReport.addStepDetails("mandatoryFieldValidation_BoqmandatoryTab_bidsubmission",
					"BoqmandatoryTab_bidsubmission must be validate successfully",
					"Successfully validate qBoqmandatoryTab_bidsubmission" + " ", "Pass", "Y");
			log.info("completed executing the method:: mandatoryFieldValidation_BoqmandatoryTab_bidsubmission");
		} catch (Exception e) {
			log.fatal("Not able to validate quotationSummaryTab" + e.getMessage());
			pdfResultReport.addStepDetails("mandatoryFieldValidation_BoqmandatoryTab_bidsubmission",
					"Not able to validate BoqmandatoryTab_bidsubmission",
					"Unable to validate BoqmandatoryTab_bidsubmission" + e.getMessage(), "Fail", "N");
		}
	}

	public void mandatoryFieldValidation_submitButton_bidsubmission() throws Throwable {
		try {
			log.info("started executing the method:: mandatoryFieldValidation_quotationSummaryTab_bidsubmission");
			waitForObj(2000);
			click(tendercreationlocators.submitbutton, "submitbutton");
			waitForObj(2000);
			waitForSpinnerToDisappearInBidSubmission();
			waitForElementToBeVisible(tendercreationlocators.previewAllpage);

			pdfResultReport.addStepDetails("Successfully click on submitbutton",
					"submitbutton must be clicked successfully", "Successfully clicked on submitbutton" + " ", "Pass",
					"Y");
			log.info("completed executing the method:: mandatoryFieldValidation_quotationSummaryTab_bidsubmission");
		} catch (Exception e) {
			log.fatal("Not able to click on submitbutton" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to click submitbutton", "Not able to click on submitbutton",
					"Unable to click on submitbutton" + e.getMessage(), "Fail", "N");
		}
	}

	public void validateAllInPrevieAllSubmitbidPage() throws Throwable {
		try {
			log.info("started executing the method:: previewAll_Validation_bidsubmission");
			IsElementPresent(tendercreationlocators.previewAllTittle);

			IsElementPresent(tendercreationlocators.technical_priviewAll);

			scrollToElement(tendercreationlocators.termsAndCondition_priviewAll);
			IsElementPresent(tendercreationlocators.termsAndCondition_priviewAll);

			scrollToElement(tendercreationlocators.generalInformation_priviewAll);
			IsElementPresent(tendercreationlocators.generalInformation_priviewAll);
			//Commneted in AWS QA as it is not displaying(04/11/2020)
			//scrollToElement(tendercreationlocators.quotationAttachments_priviewAll);
			//IsElementPresent(tendercreationlocators.quotationAttachments_priviewAll);

			scrollToElement(tendercreationlocators.bidderSpecificAttachments_priviewAll);
			IsElementPresent(tendercreationlocators.bidderSpecificAttachments_priviewAll);

			scrollToElement(tendercreationlocators.commercial_priviewAll);
			IsElementPresent(tendercreationlocators.commercial_priviewAll);

			scrollToElement(tendercreationlocators.boqoptionalpreviewAllpageBy);
			IsElementPresent(tendercreationlocators.boqoptionalpreviewAllpageBy);

			scrollToElement(tendercreationlocators.boqMandatorypreviewAllpageBy);
			IsElementPresent(tendercreationlocators.boqMandatorypreviewAllpageBy);

			pdfResultReport.addStepDetails("Successfully validate previewAll details",
					" previewAll details must be validated successfully",
					"Successfully validated previewAll details" + " ", "Pass", "Y");
			log.info("completed executing the method:: previewAll_Validation_bidsubmission");
		} catch (Exception e) {
			log.fatal("Not able to validate previewAll details" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to validate previewAll details",
					"Not able to validate previewAll details", "Unable to validate previewAll details" + e.getMessage(),
					"Fail", "N");
		}
	}
	//Validate Preview All page after submiting the bid specific to TG1 template group("Indigenous Tender (Supply & Service Both) V-1.0")
	public void TG1_validateAllInPrevieAllSubmitbidPage() throws Throwable {
		try {
			log.info("started executing the method:: TG1_validateAllInPrevieAllSubmitbidPage");
			IsElementPresent(tendercreationlocators.previewAllpage);
			IsElementPresent(tendercreationlocators.GeneralInfo_PreviwAllPage_TG1);

			scrollToElement(tendercreationlocators.Terms_Conditions_PreviwAllPage_TG1);
			IsElementPresent(tendercreationlocators.Terms_Conditions_PreviwAllPage_TG1);

			scrollToElement(tendercreationlocators.Technical_ComplianceTable_PreviwAllPage_TG1);
			IsElementPresent(tendercreationlocators.Technical_ComplianceTable_PreviwAllPage_TG1);

			scrollToElement(tendercreationlocators.Tender_Attachment_PreviwAllPage_TG1);
			IsElementPresent(tendercreationlocators.Tender_Attachment_PreviwAllPage_TG1);

			scrollToElement(tendercreationlocators.BidderSpecAttachment_PreviwAllPage_TG1);
			IsElementPresent(tendercreationlocators.BidderSpecAttachment_PreviwAllPage_TG1);

			scrollToElement(tendercreationlocators.Technical_PreviwAllPage_TG1);
			IsElementPresent(tendercreationlocators.Technical_PreviwAllPage_TG1);

			scrollToElement(tendercreationlocators.CommercialParam_PreviwAllPage_TG1);
			IsElementPresent(tendercreationlocators.CommercialParam_PreviwAllPage_TG1);

			scrollToElement(tendercreationlocators.GeneralReq_PreviwAllPage_TG1);
			IsElementPresent(tendercreationlocators.GeneralReq_PreviwAllPage_TG1);
			
			scrollToElement(tendercreationlocators.OtherClauses_PreviwAllPage_TG1);
			IsElementPresent(tendercreationlocators.OtherClauses_PreviwAllPage_TG1);
			
			scrollToElement(tendercreationlocators.PaymentDetails_PreviwAllPage_TG1);
			IsElementPresent(tendercreationlocators.PaymentDetails_PreviwAllPage_TG1);
			
			scrollToElement(tendercreationlocators.RFQItem_PreviwAllPage_TG1);
			IsElementPresent(tendercreationlocators.RFQItem_PreviwAllPage_TG1);
			
			scrollToElement(tendercreationlocators.BOQMan_PreviwAllPage_TG1);
			IsElementPresent(tendercreationlocators.BOQMan_PreviwAllPage_TG1);
			
			
			pdfResultReport.addStepDetails("TG1_validateAllInPrevieAllSubmitbidPage",
					"PreviewAll details must be validated successfully",
					"Successfully validated previewAll details" + " ", "Pass", "Y");
			log.info("completed executing the method:: TG1_validateAllInPrevieAllSubmitbidPage");
		} catch (Exception e) {
			log.fatal("Not able to validate previewAll details" + e.getMessage());
			pdfResultReport.addStepDetails("TG1_validateAllInPrevieAllSubmitbidPage",
					"Validating Preview All page", "Unable to validate previewAll details" + e.getMessage(),
					"Fail", "N");
		}
	}
	//Validate Preview All page after submitting the bid specific to T31 template group("MRO_V1.1")
	public void TG3_validateAllInPrevieAllSubmitbidPage() throws Throwable {
		try {
			log.info("started executing the method:: TG3_validateAllInPrevieAllSubmitbidPage");
			IsElementPresent(tendercreationlocators.previewAllpage);
			IsElementPresent(tendercreationlocators.GeneralInfo_PreviwAllPage_TG1);

			scrollToElement(tendercreationlocators.QuotationAttachment_PreviwAllPage_TG3);
			IsElementPresent(tendercreationlocators.QuotationAttachment_PreviwAllPage_TG3);

			scrollToElement(tendercreationlocators.TenderAttachment_PreviwAllPage_TG3);
			IsElementPresent(tendercreationlocators.TenderAttachment_PreviwAllPage_TG3);

			scrollToElement(tendercreationlocators.CommercialTermsCond_PreviwAllPage_TG3);
			IsElementPresent(tendercreationlocators.CommercialTermsCond_PreviwAllPage_TG3);

			scrollToElement(tendercreationlocators.TermsCond_PreviwAllPage_TG3);
			IsElementPresent(tendercreationlocators.TermsCond_PreviwAllPage_TG3);

			scrollToElement(tendercreationlocators.GeneralInfoClauses_PreviwAllPage_TG3);
			IsElementPresent(tendercreationlocators.GeneralInfoClauses_PreviwAllPage_TG3);

			scrollToElement(tendercreationlocators.PriceFormat_PreviwAllPage_TG3);
			IsElementPresent(tendercreationlocators.PriceFormat_PreviwAllPage_TG3);

			scrollToElement(tendercreationlocators.PriceSummary_PreviwAllPage_TG3);
			IsElementPresent(tendercreationlocators.PriceSummary_PreviwAllPage_TG3);
			
			pdfResultReport.addStepDetails("TG3_validateAllInPrevieAllSubmitbidPage",
					"PreviewAll details must be validated successfully",
					"Successfully validated previewAll details" + " ", "Pass", "Y");
			log.info("completed executing the method:: TG3_validateAllInPrevieAllSubmitbidPage");
		} catch (Exception e) {
			log.fatal("Not able to validate previewAll details" + e.getMessage());
			pdfResultReport.addStepDetails("TG3_validateAllInPrevieAllSubmitbidPage",
					"Validating Preview All page", "Unable to validate previewAll details" + e.getMessage(),
					"Fail", "N");
		}
	}

	public void submitBid_link_in_previewAllPage() throws Throwable {
		try {
			log.info("started executing the method:: submitBid_link_in_previewAllPage");
			JSClick(tendercreationlocators.submitbid, "submitbid");
			waitForObj(3000);
			waitForElementToBeVisible(tendercreationlocators.bidSuccessfulMessage);
			IsElementPresent(tendercreationlocators.bidSuccessfulMessage);
			click(tendercreationlocators.bidSuccessfulMessage_Ok, "bidSuccessfulMessage_Ok");
			waitForObj(3000);
			waitForElementToBeVisible(tendercreationlocators.LiveTenderRecordsBy);
			WebDriver driver = ThreadLocalWebdriver.getDriver();

			waitTillSpinnerDisable(driver, tendercreationlocators.LoadingBy);

			pdfResultReport.addStepDetails("Successfully submit bid", "Bid must be submitted successfully",
					"Successfully submitted Bid" + " ", "Pass", "Y");
			log.info("completed executing the method:: submitBid_link_in_previewAllPage");
		} catch (Exception e) {
			log.fatal("Not able to submit Bid" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to submit Bid", "Not able to submit Bid",
					"Unable to submit Bid" + e.getMessage(), "Fail", "N");
		}
	}

	public void navigate_to_bidList_page() throws Throwable {
		try {
			log.info("started executing the method:: navigate_to_bidList_page");

			JSClick(tendercreationlocators.bidSubmissionTransaction, "bidSubmissionTransaction");
			JSClick(tendercreationlocators.bidListing, "bidListing");

			// waitForElementToBeVisible(tendercreationlocators.SpinnerHolderBy);

			checkPageIsReady();

			waitForSpinnerToDisappear();
			IsElementPresent(tendercreationlocators.draftBidTab);
			IsElementPresent(tendercreationlocators.submittedBidTab);
			IsElementPresent(tendercreationlocators.withdrawBidTab);
			pdfResultReport.addStepDetails("Navigate to Bid List page", "Bid List page must be navigated successfully",
					"Successfully navigated Bid List page" + " ", "Pass", "Y");
			log.info("completed executing the method:: navigate_to_bidList_page");

		} catch (Exception e) {
			log.fatal("Unable to navigate to the Bid List Page" + e.getMessage());
			pdfResultReport.addStepDetails("Navigate to Bid List Page", "Not able to navigate to Bid List Page",
					"Unable to navigate to the Bid List Page" + e.getMessage(), "Fail", "N");
		}
	}

	public void enterTenderIdInSearch_bidsubmission() throws Throwable {
		try {
			log.info("started executing the method:: enterTenderIdInSearch_bidsubmission");
			set(tendercreationlocators.bidsubmissionSearchByKeyword, tenderReferenceNoLocatorText, "tenderListSearch");
			waitForObj(5000);
			WebDriver driver = ThreadLocalWebdriver.getDriver();
			waitTillSpinnerDisable(driver, tendercreationlocators.LoadingBy);
			pdfResultReport.addStepDetails("enterTenderIdInSearch_bidsubmission",
					"Tender Id must be entered successfully in search field",
					"Tender Id is successfully entered in search field" + " ", "Pass", "Y");
			log.info("completed executing the method:: enterTenderIdInSearch_bidsubmission");
		} catch (Exception e) {
			log.fatal("Not able to create tender" + e.getMessage());
			pdfResultReport.addStepDetails("enterTenderIdInSearch_bidsubmission",
					"Tender Id must be entered successfully in search field",
					"Unable to enter Tender Id in search field" + e.getMessage(), "Fail", "N");
		}
	}

	public void submittedBid_Tab_Validation() throws Throwable {
		try {
			log.info("started executing the method:: submittedBid_Tab_Validation");
			click(tendercreationlocators.submittedBidTab, "submittedBid");

			waitForSpinnerToDisappear();
			waitForElementToBeVisible(tendercreationlocators.submittedOn_bidList);
			IsElementPresent(tendercreationlocators.submittedOn_bidList);

			pdfResultReport.addStepDetails("Navigate to submittedbid tab",
					"Tender must be listed under submittedbid tab successfully",
					"Successfully listed Tender under submittedbid tab " + " ", "Pass", "Y");
			log.info("completed executing the method:: submittedBid_Tab_Validation");

		} catch (Exception e) {
			log.fatal("Unable to see Tender under submittedbid tab" + e.getMessage());
			pdfResultReport.addStepDetails("Navigate to submittedbid tab",
					"Not able to see Tender under submittedbid tab",
					"Unable to to see Tender under submittedbid tab" + e.getMessage(), "Fail", "N");
		}
	}

	public void waitTillBidstartDateReached() throws Throwable {
		try {

			while (true) {
				LocalDateTime localdatetime = LocalDateTime.now();

				String currentdateTime = localdatetime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
				System.out.println(bidStartDate_24Hrs);
				if (currentdateTime.compareTo(bidStartDate_24Hrs) > 0) {

					waitForObj(5000);

					System.out.println("The Given Bid Start Time Is Reached --->" + bidStartDate_24Hrs + " "
							+ "The Bidder Can Start Bidding");
					break;
				} else {
					System.out.println("The Given Bid Start Time Is  --->" + bidStartDate_24Hrs + " "
							+ "Wait till bid start date time reached " + " "
							+ currentdateTime.compareTo(bidStartDate_24Hrs));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void waitTillBidDuetDateReached() throws Throwable {
		try {

			while (true) {
				LocalDateTime localdatetime = LocalDateTime.now();

				String currentdateTime = localdatetime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

				if (currentdateTime.compareTo(bidDueDate_24Hrs) > 0) {

					waitForObj(5000);
					System.out.println("The Given Bid Due Time Is Reached --->" + bidDueDate_24Hrs + " "
							+ "The Bidder Should wait for BidOpen date");
					break;
				} else {
					System.out.println("he Given Bid Due Time Is  --->" + bidDueDate_24Hrs + " "
							+ "Wait till the due date time reached " + "  "
							+ currentdateTime.compareTo(bidDueDate_24Hrs));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void waitTillBidOpentDateReached() throws Throwable {
		try {

			while (true) {
				LocalDateTime localdatetime = LocalDateTime.now();

				String currentdateTime = localdatetime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

				if (currentdateTime.compareTo(bidOpenDate_24Hrs) > 0) {

					waitForObj(5000);

					break;
				} else {
					System.out.println(
							"wait till the bid open date time reached " + currentdateTime.compareTo(bidDueDate_24Hrs));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void clickEvaluationSettingsLink() throws Throwable {
		try {
			log.info("started executing the method:: clickEvaluationSettingsLink");

			click(tendercreationlocators.action, "action");

			waitForElementToBeVisible(tendercreationlocators.evaluationSettings);

			System.out.println("Evalution settings is visible");

			scrollToElement(tendercreationlocators.evaluationSettings);

			JSClick(tendercreationlocators.evaluationSettings, "evaluationSettings");

			waitForElementToBeVisible(tendercreationlocators.openAndEvaluateBid_Evaluation);

			waitForObj(3000);

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			waitForObj(2000);

			pdfResultReport.addStepDetails("clickEvaluationSettingsLink",
					"Evaluation settings link must be clicked successfully And Navigated to Evaluation Settings page",
					"Successfully clicked on evaluation settings link" + " ", "Pass", "Y");
			log.info("completed executing the method:: evaluationSettingsLink");
		} catch (Exception e) {
			log.fatal("Unable to Navigated to Evaluation Settings page" + e.getMessage());
			pdfResultReport.addStepDetails("clickEvaluationSettingsLink",
					"Evaluation settings link must be clicked successfully And Navigated to Evaluation Settings page",
					"Unable to Navigated to Evaluation Settings page" + e.getMessage(), "Fail", "N");
		}
	}

	public void SendForApprovalInEvaluationsetting() throws Throwable {
		try {
			log.info("started executing the method:: SendForApprovalInEvaluationsetting");

			JSClick(tendercreationlocators.evaluation_sendForApproval, "evaluation_sendForApproval");
			waitForObj(2000);
			waitForElementToBeVisible(tendercreationlocators.defaultCatBy);

			pdfResultReport.addStepDetails("Successfully send for approval",
					"send for approval must be done successfully InEvaluationsetting",
					"Successfully done send for approval InEvaluationsetting" + " ", "Pass", "Y");
			log.info("completed executing the method:: SendForApprovalInEvaluationsetting");
		} catch (Exception e) {
			log.fatal("Not able send for approval InEvaluationsetting" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to send for approval",
					"send for approval must be done successfully InEvaluationsetting",
					"Unable to send for approval InEvaluationsetting page" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickPendingForEvaluationApprovalStage() throws Throwable {
		try {
			log.info("started executing the method:: pendingForEvaluationApprovalStage");

			if (JSClick(tendercreationlocators.pendingForEvaluationApproval, "pendingForEvaluationApproval")) {
				System.out.println("pendingForEvaluationApprovalstage is clicked");
				waitForElementToBeVisible(tendercreationlocators.LblBiddername_Evaluation);
				waitForObj(2000);

			} else {

				ThreadLocalWebdriver.getDriver()
						.findElement(By
								.xpath("//*[@class='btn btn-primary btn-circle btn-lg btn-lg-sub2 grnBtn ng-scope ng-isolate-scope']"))
						.click();
				waitForElementToBeVisible(tendercreationlocators.LblBiddername_Evaluation);
				waitForObj(2000);

			}

			pdfResultReport.addStepDetails("pendingForEvaluationApprovalStage",
					"PendingForEvaluationApprovalStage must be clicked and Should navigate to Bid DetailPage",
					"Successfully clicked And navigated to Bid DetailPage" + " ", "Pass", "Y");
			log.info("completed executing the method:: pendingForEvaluationApprovalStage");

		} catch (Exception e) {
			log.fatal("Not able to navigated to Bid DetailPage" + e.getMessage());
			pdfResultReport.addStepDetails("pendingForEvaluationApprovalStage",
					"PendingForEvaluationApprovalStage must be clicked  and Should navigate to Bid DetailPage",
					"Not able to navigated to Bid DetailPage " + e.getMessage(), "Fail", "N");
		}
	}

	public void decryptingTheBidder() throws Throwable {
		try {
			log.info("started executing the method:: decryptingTheBidder");
			click(tendercreationlocators.bidder_checkbox, "bidder_checkbox");
			click(tendercreationlocators.decryptSelected, "decryptSelected");
			waitForElementToBeVisible(tendercreationlocators.disabledDecryptBidderDataBtnBy);
			waitForObj(5000);
			pdfResultReport.addStepDetails("decryptingTheBidder", "bidder must be decrypted successfully",
					"Successfully decrypted the bidder" + " ", "Pass", "Y");
			log.info("completed executing the method:: decryptingTheBidder");
		} catch (Exception e) {
			log.fatal("Not able to decrypting the bidder" + e.getMessage());
			pdfResultReport.addStepDetails("decryptingTheBidder", "bidder must be decrypted successfully",
					"Unable to decrypting the bidder" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickBidDetailsSectionForchoosenBidder(String BidderName) throws Throwable {
		try {
			log.info("started executing the method:: clickBidDetailsSectionForchoosenBidder");

			click(By.xpath("//*[contains(normalize-space(text()),'" + BidderName.toUpperCase()
					+ "')]//following-sibling::td//button[@data-toggle='dropdown']"), "click Action Menu dropdown");

			waitForObj(2000);

			click(By.xpath("//*[contains(normalize-space(text()),'" + BidderName.toUpperCase()
					+ "')]//following-sibling::td//span[contains(text(),'Bid Details')]//parent::a[contains(@ng-click,'previewBid')]"),
					"click bidDetails section");

			//waitForElementToBeVisible(tendercreationlocators.technical);
			waitForObj(7000);

			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");

			pdfResultReport.addStepDetails("clickBidDetailsSectionForchoosenBidder",
					"BidDetails Section must be clciked successfully", "Successfully clicked BidDetails Section" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: clickBidDetailsSectionForchoosenBidder");
		} catch (Exception e) {
			log.fatal("Not able to navigate To BidDetails Section" + e.getMessage());
			pdfResultReport.addStepDetails("clickBidDetailsSectionForchoosenBidder",
					"BidDetails Section must be clicked successfully",
					"Unable to click BidDetails Section" + e.getMessage(), "Fail", "N");
		}
	}

	public void validateBidDetailsForCover1_ForNoApprovalType() throws Throwable {
		try {
			log.info("started executing the method:: validateBidDetailsForCover1_ForNoApprovalType");

			click(tendercreationlocators.technical, "technical");
			waitForElementToBeVisible(tendercreationlocators.technicalTabFileuploadEleBy);
			waitForObj(2000);
			//Commented as CR#83575 implemented (no need to navigate each and every tab)
			/*click(tendercreationlocators.termsAndCondition_bidDetails, "termsAndCondition_bidDetails");
			waitForObj(2000);
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");
			waitForElementToBeVisible(tendercreationlocators.termsAndConditionsClauseBy);
			waitForObj(2000);
			click(tendercreationlocators.generalInformation_bidDetails, "generalInformation_bidDetails");
			waitForObj(2000);
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");
			waitForElementToBeVisible(tendercreationlocators.excelBiddingFieldEleBy);
			waitForObj(2000);
			click(tendercreationlocators.attachments_bidDetails, "attachments_bidDetails");
			waitForObj(2000);
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");
			waitForElementToBeVisible(tendercreationlocators.bidSpecificAttachment);
			waitForObj(2000);
			click(tendercreationlocators.next, "Click next");
			waitForObj(2000);
			click(tendercreationlocators.commercial_biddetails, "Commercial_bidDetails");*/
			waitForObj(2000);
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");
			waitForObj(2000);
			click(By.xpath("(//i[contains(@class,'remove')])[1]"), "close_bidDetails");

			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");

			waitForObj(2000);

			pdfResultReport.addStepDetails("validateBidDetailsForCover1_ForNoApprovalType",
					"BidDetails For cover 1 should be validated", "Successfully Validated Bid details for cover1" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: validateBidDetailsForCover1_ForNoApprovalType");
		} catch (Exception e) {
			log.fatal("Unable to validate BidDetails for cover 1" + e.getMessage());
			pdfResultReport.addStepDetails("navigateToBidDetailsEachAndEveryTab",
					"BidDetails For cover 1 should be validated",
					"Unable to validate BidDetails for cover 1" + e.getMessage(), "Fail", "N");
		}
	}

	//Validate bid details for cover1 (TG1 template group)
	public void TG1_validateBidDetailsForCover1() throws Throwable {
		try {
			log.info("started executing the method:: TG1_validateBidDetailsForCover1");

			waitForElementToBeVisible(tendercreationlocators.TG1_generalInformation_bidDetails);
			click(tendercreationlocators.TG1_generalInformation_bidDetails, "TG1_generalInformation_bidDetails");
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,"Please Wait...");
			waitForObj(2000);
			//Commented as CR#83575 implemented (no need to navigate each and every tab)
			/*click(tendercreationlocators.TG1_generalInformation_bidDetails, "TG1_generalInformation_bidDetails");
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,"Please Wait...");
			waitForObj(2000);
			click(tendercreationlocators.TG1_TermsAndConditions_bidDetails, "TG1_TermsAndConditions_bidDetails");
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");
			waitForObj(2000);
			click(tendercreationlocators.TG1_TechnicalComp_bidDetails, "TG1_TechnicalComp_bidDetails");
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");
			waitForObj(2000);
			click(tendercreationlocators.TG1_Attachments_bidDetails, "TG1_Attachments_bidDetails");
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");
			waitForObj(2000);
			click(tendercreationlocators.next, "Click next");
			waitForObj(2000);
			click(tendercreationlocators.TG1_Technical_bidDetails, "TG1_Technical_bidDetails");
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");
			waitForObj(2000);
			click(tendercreationlocators.TG1_SpecificationAndTechnicalReq_bidDetails, "TG1_SpecificationAndTechnicalReq_bidDetails");
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");
			waitForObj(2000);
			click(tendercreationlocators.next, "Click next");
			waitForObj(2000);
			click(tendercreationlocators.TG1_OtherClauses_bidDetails, "TG1_OtherClauses_bidDetails");*/
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");
			waitForObj(2000);
			click(By.xpath("(//i[contains(@class,'remove')])[1]"), "close_bidDetails");
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");
			waitForObj(2000);

			pdfResultReport.addStepDetails("TG1_validateBidDetailsForCover1",
					"BidDetails For cover 1 should be validated", "Successfully Validated Bid details for cover1" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: TG1_validateBidDetailsForCover1");
		} catch (Exception e) {
			log.fatal("Unable to validate BidDetails for cover 1" + e.getMessage());
			pdfResultReport.addStepDetails("TG1_validateBidDetailsForCover1",
					"BidDetails For cover 1 should be validated",
					"Unable to validate BidDetails for cover 1" + e.getMessage(), "Fail", "N");
		}
	}
	//Validate bid details for cover1 (TG3 template group)
	public void TG3_validateBidDetailsForCover1() throws Throwable {
		try {
			log.info("started executing the method:: TG3_validateBidDetailsForCover1");

			waitForElementToBeVisible(tendercreationlocators.TG3_generalInformation_bidDetails);
			//Commented as CR#83575 implemented (no need to navigate each and every tab) 
			/*click(tendercreationlocators.TG3_generalInformation_bidDetails, "TG3_generalInformation_bidDetails");
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,"Please Wait...");
			waitForObj(2000);
			click(tendercreationlocators.TG3_DateSchedule_bidDetails, "TG3_DateSchedule_bidDetails");
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");
			waitForObj(2000);
			click(tendercreationlocators.next, "Click next");
			waitForObj(2000);
			click(tendercreationlocators.TG3_GeneralInfoClauses_bidDetails, "TG3_GeneralInfoClauses_bidDetails");*/
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");
			waitForObj(2000);
			click(By.xpath("(//i[contains(@class,'remove')])[1]"), "close_bidDetails");
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");
			waitForObj(2000);

			pdfResultReport.addStepDetails("TG3_validateBidDetailsForCover1",
					"BidDetails For cover 1 should be validated", "Successfully Validated Bid details for cover1" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: TG3_validateBidDetailsForCover1");
		} catch (Exception e) {
			log.fatal("Unable to validate BidDetails for cover 1" + e.getMessage());
			pdfResultReport.addStepDetails("TG3_validateBidDetailsForCover1",
					"BidDetails For cover 1 should be validated",
					"Unable to validate BidDetails for cover 1" + e.getMessage(), "Fail", "N");
		}
	}
	//Validate bid details for cover2 (TG1 template group)
	public void TG1_validateBidDetailsForCover2() throws Throwable {
		try {
			log.info("started executing the method:: TG1_validateBidDetailsForCover2");

			waitForElementToBeVisible(tendercreationlocators.TG1_generalInformation_bidDetails);
			waitForObj(2000);
			//Commented as CR#83575 implemented (no need to navigate each and every tab)
		/*	click(tendercreationlocators.next, "Click next");
			waitForObj(2000);
			click(tendercreationlocators.next, "Click next");
			waitForObj(2000);
			click(tendercreationlocators.TG1_CommercialParam_bidDetails, "TG1_CommercialParam_bidDetails");
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");
			waitForObj(2000);
			click(tendercreationlocators.TG1_GeneralReqEquip_bidDetails, "TG1_GeneralReqEquip_bidDetails");
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");
			waitForObj(2000);
			click(tendercreationlocators.TG1_Payment_bidDetails, "TG1_Payment_bidDetails");
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");
			waitForObj(2000);
			click(tendercreationlocators.TG1_RFQItem_bidDetails, "TG1_RFQItem_bidDetails");
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");
			waitForObj(2000);
			click(tendercreationlocators.TG1_BOQMandatory_bidDetails, "TG1_BOQMandatory_bidDetails");*/
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");
			waitForObj(2000);
			click(By.xpath("(//i[contains(@class,'remove')])[1]"), "close_bidDetails");
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");
			waitForObj(2000);

			pdfResultReport.addStepDetails("TG1_validateBidDetailsForCover2",
					"BidDetails For cover 2 should be validated", "Successfully Validated Bid details for cover2" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: TG1_validateBidDetailsForCover2");
		} catch (Exception e) {
			log.fatal("Unable to validate BidDetails for cover 2" + e.getMessage());
			pdfResultReport.addStepDetails("TG1_validateBidDetailsForCover2",
					"BidDetails For cover 2 should be validated",
					"Unable to validate BidDetails for cover 2" + e.getMessage(), "Fail", "N");
		}
	}
	//Validate bid details for cover2 (TG3 template group)
	public void TG3_validateBidDetailsForCover2() throws Throwable {
		try {
			log.info("started executing the method:: TG3_validateBidDetailsForCover2");
			
			waitForElementToBeVisible(tendercreationlocators.TG3_generalInformation_bidDetails);
			click(tendercreationlocators.TG3_generalInformation_bidDetails, "TG3_generalInformation_bidDetails");
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,"Please Wait...");
			waitForObj(2000);
			click(tendercreationlocators.TG3_DateSchedule_bidDetails, "TG3_DateSchedule_bidDetails");
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");
			waitForObj(2000);
			click(tendercreationlocators.TG3_CommercialTermsAndCond_bidDetails, "TG3_CommercialTermsAndCond_bidDetails");
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");
			waitForObj(2000);
			click(tendercreationlocators.TG3_TermsAndCond_bidDetails, "TG3_TermsAndCond_bidDetails");
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");
			waitForObj(2000);
			click(tendercreationlocators.next, "Click next");
			waitForObj(2000);
			click(tendercreationlocators.TG3_GeneralInfoClauses_bidDetails, "TG3_GeneralInfoClauses_bidDetails");
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");
			waitForObj(2000);
			click(tendercreationlocators.TG3_PriceFormat_bidDetails, "TG3_PriceFormat_bidDetails");
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");
			waitForObj(2000);
			click(tendercreationlocators.TG3_PriceSummary_bidDetails, "TG3_PriceSummary_bidDetails");
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");
			waitForObj(2000);
			//Save bid details page of evaluation (added 23/3/2021)
			waitForElementToBeVisible(tendercreationlocators.Savebtn_BidDetailspage_Evaluation);
			click(tendercreationlocators.Savebtn_BidDetailspage_Evaluation, "Savebtn_BidDetailspage_Evaluation");
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,"Please Wait...");
			waitForObj(2000);
			waitForElementToBeVisible(tendercreationlocators.AlertMsgmodal_BidDetailspage_Evaluation);
			click(tendercreationlocators.ClosebtnAlertMsgmodal_BidDetailspage_Evaluation, "ClosebtnAlertMsgmodal_BidDetailspage_Evaluation");
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,"Please Wait...");
			waitForObj(2000);
			
			click(By.xpath("(//i[contains(@class,'remove')])[1]"), "close_bidDetails");
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");
			waitForObj(2000);

			pdfResultReport.addStepDetails("TG3_validateBidDetailsForCover2",
					"BidDetails For cover 2 should be validated", "Successfully Validated Bid details for cover2" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: TG3_validateBidDetailsForCover2");
		} catch (Exception e) {
			log.fatal("Unable to validate BidDetails for cover 2" + e.getMessage());
			pdfResultReport.addStepDetails("TG3_validateBidDetailsForCover2",
					"BidDetails For cover 2 should be validated",
					"Unable to validate BidDetails for cover 2" + e.getMessage(), "Fail", "N");
		}
	}
	//Validate bid details for cover2 for TC user(TG3 template group)
	public void TG3_validateBidDetailsForCover2_TCUser() throws Throwable {
		try {
			log.info("started executing the method:: TG3_validateBidDetailsForCover2_TCUser");
			
			waitForElementToBeVisible(tendercreationlocators.TG3_generalInformation_bidDetails);
			click(tendercreationlocators.TG3_generalInformation_bidDetails, "TG3_generalInformation_bidDetails");
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,"Please Wait...");
			waitForObj(2000);
			click(tendercreationlocators.TG3_DateSchedule_bidDetails, "TG3_DateSchedule_bidDetails");
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");
			waitForObj(2000);
			click(tendercreationlocators.TG3_CommercialTermsAndCond_bidDetails, "TG3_CommercialTermsAndCond_bidDetails");
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");
			waitForObj(2000);
			click(tendercreationlocators.TG3_TermsAndCond_bidDetails, "TG3_TermsAndCond_bidDetails");
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");
			waitForObj(2000);
			click(tendercreationlocators.next, "Click next");
			waitForObj(2000);
			click(tendercreationlocators.TG3_GeneralInfoClauses_bidDetails, "TG3_GeneralInfoClauses_bidDetails");
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");
			waitForObj(2000);
			click(tendercreationlocators.TG3_PriceFormat_bidDetails, "TG3_PriceFormat_bidDetails");
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");
			waitForObj(2000);
			click(tendercreationlocators.TG3_PriceSummary_bidDetails, "TG3_PriceSummary_bidDetails");
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");
			waitForObj(2000);
			//Save bid details page of evaluation (added 23/3/2021)
			waitForElementToBeVisible(tendercreationlocators.Savebtn_BidDetailspage_TCUser_Evaluation);
			click(tendercreationlocators.Savebtn_BidDetailspage_TCUser_Evaluation, "Savebtn_BidDetailspage_TCUser_Evaluation");
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,"Please Wait...");
			waitForObj(2000);
			waitForElementToBeVisible(tendercreationlocators.AlertMsgmodal_BidDetailspage_Evaluation);
			click(tendercreationlocators.ClosebtnAlertMsgmodal_BidDetailspage_Evaluation, "ClosebtnAlertMsgmodal_BidDetailspage_Evaluation");
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,"Please Wait...");
			waitForObj(2000);
			
			click(By.xpath("(//i[contains(@class,'remove')])[1]"), "close_bidDetails");
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");
			waitForObj(2000);

			pdfResultReport.addStepDetails("TG3_validateBidDetailsForCover2_TCUser",
					"BidDetails For cover 2 should be validated", "Successfully Validated Bid details for cover2" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: TG3_validateBidDetailsForCover2_TCUser");
		} catch (Exception e) {
			log.fatal("Unable to validate BidDetails for cover 2" + e.getMessage());
			pdfResultReport.addStepDetails("TG3_validateBidDetailsForCover2_TCUser",
					"BidDetails For cover 2 should be validated",
					"Unable to validate BidDetails for cover 2" + e.getMessage(), "Fail", "N");
		}
	}
	//Validate bid details for cover2 after negotiated bid submission (TG1 template group)
	public void TG1_validateBidDetailsForCover2_afterNegotiation() throws Throwable {
		try {
			log.info("started executing the method:: TG1_validateBidDetailsForCover2_afterNegotiation");

			waitForElementToBeVisible(tendercreationlocators.TG1_generalInformation_bidDetails);
			click(tendercreationlocators.TG1_generalInformation_bidDetails, "TG1_generalInformation_bidDetails");
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,"Please Wait...");
			waitForObj(2000);
			//Commented as CR#83575 implemented (no need to navigate each and every tab)
			/*click(tendercreationlocators.TG1_TermsAndConditions_bidDetails, "TG1_TermsAndConditions_bidDetails");
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");
			waitForObj(2000);
			click(tendercreationlocators.TG1_TechnicalComp_bidDetails, "TG1_TechnicalComp_bidDetails");
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");
			waitForObj(2000);
			click(tendercreationlocators.TG1_Attachments_bidDetails, "TG1_Attachments_bidDetails");
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");
			waitForObj(2000);
			click(tendercreationlocators.next, "Click next");
			waitForObj(2000);
			click(tendercreationlocators.TG1_Technical_bidDetails, "TG1_Technical_bidDetails");
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");
			waitForObj(2000);
			click(tendercreationlocators.TG1_SpecificationAndTechnicalReq_bidDetails, "TG1_SpecificationAndTechnicalReq_bidDetails");
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");
			waitForObj(2000);
			click(tendercreationlocators.next, "Click next");
			waitForObj(2000);
			click(tendercreationlocators.TG1_CommercialParam_bidDetails, "TG1_CommercialParam_bidDetails");
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");
			waitForObj(2000);
			click(tendercreationlocators.TG1_GeneralReqEquip_bidDetails, "TG1_GeneralReqEquip_bidDetails");
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");
			waitForObj(2000);
			click(tendercreationlocators.TG1_OtherClauses_bidDetails, "TG1_OtherClauses_bidDetails");
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");
			waitForObj(2000);
			click(tendercreationlocators.TG1_Payment_bidDetails, "TG1_Payment_bidDetails");
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");
			waitForObj(2000);
			click(tendercreationlocators.TG1_RFQItem_bidDetails, "TG1_RFQItem_bidDetails");
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");
			waitForObj(2000);
			click(tendercreationlocators.TG1_BOQMandatory_bidDetails, "TG1_BOQMandatory_bidDetails");*/
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");
			waitForObj(2000);
			click(By.xpath("(//i[contains(@class,'remove')])[1]"), "close_bidDetails");
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");
			waitForObj(2000);

			pdfResultReport.addStepDetails("TG1_validateBidDetailsForCover2_afterNegotiation",
					"BidDetails For cover 2 should be validated", "Successfully Validated Bid details for cover2" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: TG1_validateBidDetailsForCover2_afterNegotiation");
		} catch (Exception e) {
			log.fatal("Unable to validate BidDetails for cover 2" + e.getMessage());
			pdfResultReport.addStepDetails("TG1_validateBidDetailsForCover2_afterNegotiation",
					"BidDetails For cover 2 should be validated",
					"Unable to validate BidDetails for cover 2" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickEditableLinkPendingStatusAndSelectApproveForTheBidder(String bidderName, String sucessAlretMsg)
			throws Throwable {
		try {
			log.info("started executing the method:: editPencilIconAndClickApproveAndSave");

			WebDriver driver = ThreadLocalWebdriver.getDriver();

			driver.findElement(By
					.xpath(tendercreationlocators.editpencilIconEleBy.replace("{0}", bidderName.trim().toUpperCase())))
					.click();
			;

			waitForElementToBeClickable(tendercreationlocators.approveRadioButton);

			click(tendercreationlocators.approveRadioButton, "approveRadioButton");

			set(tendercreationlocators.upload_editable,
					System.getProperty("user.dir") + "\\MediaFiles\\rfqCreation1.xlsx", "fileName");

			waitForObj(5000);

			click(tendercreationlocators.save, "save");
			waitForObj(1000);
			waitForElementToBeVisible(By.xpath("//strong[text()='" + sucessAlretMsg.trim() + "']"));

			IsElementPresent(By.xpath("//strong[text()='" + sucessAlretMsg.trim() + "']"));

			click(tendercreationlocators.alertCloseButton, "alertCloseButton");

			IsElementPresent(
					By.xpath(tendercreationlocators.approvedStatus.replace("{0}", bidderName.trim().toUpperCase())));

			pdfResultReport.addStepDetails("editPencilIconAndClickApproveAndSave",
					"Bidder Should be Approved Sucessfully", "Successfully Approved Bidder " + " ", "Pass", "Y");
			log.info("completed executing the method:: editPencilIconAndClickApproveAndSave");
		} catch (Exception e) {
			log.fatal("Unable to approve the Bidder" + e.getMessage());
			pdfResultReport.addStepDetails("editPencilIconAndClickApproveAndSave",
					"Bidder Should be Approved Sucessfully", "Unable to approve the Bidder " + e.getMessage(), "Fail",
					"N");
		}
	}

	public void enterOverallComment() throws Throwable {
		try {
			log.info("started executing the method:: enterOverallComment");

			Thread.sleep(10000);
			JSClick(By.xpath("//*[@aria-label='Bold']"), "overallComment_currentPart");
			WebDriver driver = ThreadLocalWebdriver.getDriver();
			driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
			set(tendercreationlocators.body_overallcomment, "Provide OverallComment", "overallComment_currentPart");
			driver.switchTo().defaultContent();
			Thread.sleep(5000);
			click(tendercreationlocators.submitbutton1, "submitbutton1");
			//WebDriverWait wait = new WebDriverWait(driver, 200);
			//wait.until(ExpectedConditions.visibilityOfElementLocated(tendercreationlocators.defaultCatBy));
			waitTillSpinnerDisable(driver, tendercreationlocators.LoadingBy);
			waitForObj(7000);

			pdfResultReport.addStepDetails("enterOverallComment",
					"OverallComment must be entered successfully And Should Submit",
					"Successfully entered OverallComment And submitted" + " ", "Pass", "Y");
			log.info("completed executing the method:: enterOverallComment");
		} catch (Exception e) {
			log.fatal("Unable to Submit" + e.getMessage());
			pdfResultReport.addStepDetails("enterOverallComment",
					"OverallComment must be entered successfully And Should Submit",
					"Unable to Submit" + e.getMessage(), "Fail", "N");
		}
	}
	//Provide overall comment in evaluator user(12/01/2021)
	public void enterOverallComment_EvaluatorUser() throws Throwable {
		try {
			log.info("started executing the method:: enterOverallComment_EvaluatorUser");

			Thread.sleep(10000);
			JSClick(By.xpath("//*[@aria-label='Bold']"), "overallComment_currentPart");
			WebDriver driver = ThreadLocalWebdriver.getDriver();
			driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
			set(tendercreationlocators.body_overallcomment, "Provide OverallComment", "overallComment_currentPart");
			driver.switchTo().defaultContent();
			Thread.sleep(5000);
			click(tendercreationlocators.submitbutton_EvalUser, "submitbutton_EvalUser");
			waitForElementToBeVisible(tendercreationlocators.Alert_Nobtn_EvalUser);
			click(tendercreationlocators.Alert_Nobtn_EvalUser, "Alert_Nobtn_EvalUser");
			WebDriverWait wait = new WebDriverWait(driver, 200);
			wait.until(ExpectedConditions.visibilityOfElementLocated(tendercreationlocators.TenEvalTab_Evaluation));
			waitTillSpinnerDisable(driver, tendercreationlocators.LoadingBy);
			waitForObj(3000);

			pdfResultReport.addStepDetails("enterOverallComment_EvaluatorUser",
					"OverallComment must be entered successfully And Should Submit",
					"Successfully entered OverallComment And submitted" + " ", "Pass", "Y");
			log.info("completed executing the method:: enterOverallComment_EvaluatorUser");
		} catch (Exception e) {
			log.fatal("Unable to Submit" + e.getMessage());
			pdfResultReport.addStepDetails("enterOverallComment_EvaluatorUser",
					"OverallComment must be entered successfully And Should Submit",
					"Unable to Submit" + e.getMessage(), "Fail", "N");
		}
	}

	public void validateBidDetailsForCover2_ForNoApprovalType() throws Throwable {
		try {
			log.info("started executing the method:: validateBidDetailsEachAndEveryTabForCover2_ForNoApprovalType");

			click(tendercreationlocators.technical, "technical");
			waitForElementToBeVisible(tendercreationlocators.technicalTabFileuploadEleBy);
			waitForObj(2000);
			
			/*click(tendercreationlocators.next, "Click next");

			click(tendercreationlocators.BOQOptionalTab, "BOQOptionalTab");
			waitForObj(2000);
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");
			waitForElementToBeVisible(tendercreationlocators.itemCodeElement(1));
			waitForObj(2000);

			click(tendercreationlocators.commercial_bidDetails, "commercial_bidDetails");
			waitForObj(2000);
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");
			waitForElementToBeVisible(tendercreationlocators.documentChargeFieldEleBy);
			waitForObj(2000);

			click(tendercreationlocators.BOQmandatoryTab, "BOQmandatoryTab");
			waitForObj(2000);
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");
			waitForElementToBeVisible(tendercreationlocators.itemCodeElement(2));*/
			waitForObj(2000);

			click(By.xpath("(//i[contains(@class,'remove')])[1]"), "close_bidDetails");

			waitForObj(2000);

			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");

			waitForObj(2000);

			pdfResultReport.addStepDetails("validateBidDetailsEachAndEveryTabForCover2_ForNoApprovalType",
					"BidDetails Cover2 validation Should be done", "Successfully Validated cover 2 bid details" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: validateBidDetailsEachAndEveryTabForCover2_ForNoApprovalType");
		} catch (Exception e) {
			log.fatal("Unable to validate cover 2 bid details" + e.getMessage());
			pdfResultReport.addStepDetails("navigateToBidDetailsEachAndEveryTab",
					"BidDetails Cover2 validation Should be done",
					"Unable to validate cover 2 bid details" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickEditableLinkPendingStatusAndSelectRejectForTheBidder(String bidderName, String sucessAlretMsg)
			throws Throwable {
		try {
			log.info("started executing the method:: editPencilIconAndClickApproveAndSave");

			WebDriver driver = ThreadLocalWebdriver.getDriver();
			driver.findElement(By
					.xpath(tendercreationlocators.editpencilIconEleBy.replace("{0}", bidderName.trim().toUpperCase())))
					.click();
			;

			waitForElementToBeVisible(tendercreationlocators.rejectRadioButton);

			click(tendercreationlocators.rejectRadioButton, "rejectRadioButton");

			set(tendercreationlocators.commentText, "rejected" + bidderName, "commentText");

			set(tendercreationlocators.upload_editable,
					System.getProperty("user.dir") + "\\MediaFiles\\rfqCreation1.xlsx", "fileName");

			waitForObj(5000);

			click(tendercreationlocators.save, "save");

			waitForElementToBeVisible(By.xpath("//strong[text()='" + sucessAlretMsg.trim() + "']"));

			IsElementPresent(By.xpath("//strong[text()='" + sucessAlretMsg.trim() + "']"));

			click(tendercreationlocators.alertCloseButton, "alertCloseButton");

			IsElementPresent(
					By.xpath(tendercreationlocators.rejectStatus.replace("{0}", bidderName.trim().toUpperCase())));

			pdfResultReport.addStepDetails("editPencilIconAndClickApproveAndSave",
					"editPencilIconAndClickApproveAndSave must be validated successfully",
					"Successfully edited PencilIcon And Clicked ApproveAndSave" + " ", "Pass", "Y");
			log.info("completed executing the method:: editPencilIconAndClickApproveAndSave");
		} catch (Exception e) {
			log.fatal("Not able to verify the bid details" + e.getMessage());
			pdfResultReport.addStepDetails("Not able toeditPencilIconAndClickApproveAndSave",
					"Not able to verify the bid details using approveOrRejectValidations",
					"Unable to editPencilIconAndClickApproveAndSave" + e.getMessage(), "Fail", "N");
		}
	}

	public void UpdateCompletedTenderIdInPropertyFile() throws Throwable {
		try {
			log.info("started executing the method:: UpdateCompletedTenderIdInPropertyFile");

			WebDriverWait wait = new WebDriverWait(ThreadLocalWebdriver.getDriver(), 7000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(tendercreationlocators.tenderStatus_completed));

			IsElementPresent(tendercreationlocators.tenderStatus_completed);

			String tenderStatus = text(tendercreationlocators.tenderCreatedStatus);

			if (tenderStatus.trim().equalsIgnoreCase("Completed")) {
				System.out.println("Tender Status is in Complete State");

				updateDataIntoPropertyFile(tenderReferenceNoLocatorText);

			}

			pdfResultReport.addStepDetails("UpdateCompletedTenderIdInPropertyFile",
					"Completed Tender Must be updated in property file",
					"Successfully updated completed Tender in property file" + " ", "Pass", "Y");
			log.info("completed executing the method:: UpdateCompletedTenderIdInPropertyFile");
		} catch (Exception e) {
			log.fatal("Unable to updated Completed tender in property file" + e.getMessage());
			pdfResultReport.addStepDetails("UpdateCompletedTenderIdInPropertyFile",
					"Completed Tender Must be updated in property file",
					"Unable to updated Completed tender in property file" + e.getMessage(), "Fail", "N");
		}
	}

	public void UpdateCompletedTenderIdInPropertyFile(int i) throws Throwable {
		try {
			log.info("started executing the method:: UpdateCompletedTenderIdInPropertyFile");

			WebDriverWait wait = new WebDriverWait(ThreadLocalWebdriver.getDriver(), 7000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(tendercreationlocators.tenderStatus_completed));

			IsElementPresent(tendercreationlocators.tenderStatus_completed);

			String tenderStatus = text(tendercreationlocators.tenderCreatedStatus);

			if (tenderStatus.trim().equalsIgnoreCase("Completed")) {
				System.out.println("Tender Status is in Complete State");

				updateDataIntoPropertyFile(tenderReferenceNoLocatorText);

				String path = "/Resources/TenderCreationList.xlsx";
				String Sheetname = "Tender";
				String time = GetCurrentDateandStamp();
				String times = time.substring(0, time.indexOf("."));
				writeexcel(path, Sheetname, "TenderID", 0, i + 1, tenderReferenceNoLocatorText);
				writeexcel(path, Sheetname, "Timestamp", 0, i + 1, times);
				writeexcel(path, Sheetname, "TenderRef", 0, i + 1, tenderRef);
			}

			pdfResultReport.addStepDetails("UpdateCompletedTenderIdInPropertyFile",
					"Completed Tender Must be updated in property file",
					"Successfully updated completed Tender in property file" + " ", "Pass", "Y");
			log.info("completed executing the method:: UpdateCompletedTenderIdInPropertyFile");
		} catch (Exception e) {
			log.fatal("Unable to updated Completed tender in property file" + e.getMessage());
			pdfResultReport.addStepDetails("UpdateCompletedTenderIdInPropertyFile",
					"Completed Tender Must be updated in property file",
					"Unable to updated Completed tender in property file" + e.getMessage(), "Fail", "N");
		}
	}

	public void enterTenderIdInCompletedTenderList() throws Throwable {
		try {
			log.info("started executing the method:: enterTenderIdInCompletedTenderList");
			clear(tendercreationlocators.tenderListKeyword, "tenderListKeyword");
			set(tendercreationlocators.tenderListKeyword, tenderReferenceNoLocatorText, "tenderListSearch");
			waitForObj(5000);
			pdfResultReport.addStepDetails("enterTenderIdInCompletedTenderList",
					"Tender Id must be entered successfully in search field",
					"Tender Id is successfully entered in search field" + " ", "Pass", "Y");

			log.info("completed executing the method:: enterTenderIdInCompletedTenderList");
		} catch (Exception e) {
			log.fatal("Not able to eneter Tender Id in search field" + e.getMessage());
			pdfResultReport.addStepDetails("enterTenderIdInCompletedTenderList",
					"Not able to enter Tender Id in search field",
					"Unable to enter Tender Id in search field" + e.getMessage(), "Fail", "N");
		}
	}

	public void mandatoryFieldValidationQuotationSummaryTab_bidsubmission() throws Throwable {
		try {
			log.info("started executing the method:: mandatoryFieldValidationQuotationSummaryTab_bidsubmission");
			waitForObj(5000);
			click(tendercreationlocators.quotationSummary_bidSubmission, "quotationSummary_bidSubmission");
			waitForSpinnerToDisappearInBidSubmission();
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForObj(2000);
			click(tendercreationlocators.savebutton, "savebutton");
			waitForObj(2000);
			waitForSpinnerToDisappearInBidSubmission();

			waitForElementToBeVisible(tendercreationlocators.alertPopUp_QRC_bidSubmission);
			IsElementPresent(tendercreationlocators.technicalAlertTab_bidSubmission);
			IsElementPresent(tendercreationlocators.termsAndConditionAlertTab_bidSubmission);
			IsElementPresent(tendercreationlocators.commercialAlertTab_bidSubmission);
			IsElementPresent(tendercreationlocators.quotationSummaryAlertTab_bidSubmission);
			waitForObj(4000);
			click(tendercreationlocators.alertClose_QRC_bidSubmission, "alertClose_QRC_bidSubmission");
			waitForObj(3000);
			pdfResultReport.addStepDetails("mandatoryFieldValidationQuotationSummaryTab_bidsubmission",
					"quotationSummaryTab must be validate successfully",
					"Successfully validate quotationSummaryTab" + " ", "Pass", "Y");
			log.info("completed executing the method:: mandatoryFieldValidationQuotationSummaryTab_bidsubmission");
		} catch (Exception e) {
			log.fatal("Not able to validate quotationSummaryTab" + e.getMessage());
			pdfResultReport.addStepDetails("mandatoryFieldValidationQuotationSummaryTab_bidsubmission",
					"Not able to validate quotationSummaryTab",
					"Unable to validate quotationSummaryTab" + e.getMessage(), "Fail", "N");
		}
	}

	public void mandatoryFieldValidationAttachmentsTab_bidsubmission() throws Throwable {
		try {
			log.info("started executing the method:: mandatoryFieldValidationAttachmentsTab_bidsubmission");
			waitForObj(5000);
			click(tendercreationlocators.attachementsTab, "attachementsTab");
			waitForSpinnerToDisappearInBidSubmission();
			waitForElementToBeVisible(tendercreationlocators.addtenderattachmenticon);
			waitForObj(2000);
			click(tendercreationlocators.addtenderattachmenticon, "addtenderattachmenticon");
			waitForObj(2000);
			set(tendercreationlocators.upload, System.getProperty("user.dir") + "\\MediaFiles\\rfqCreation.xlsx",
					"fileName");
			waitForSpinnerToDisappearInBidSubmission();
			waitForObj(3000);
			pdfResultReport.addStepDetails("mandatoryFieldValidationAttachmentsTab_bidsubmission",
					"attachmentsTab must be validate successfully", "Successfully validated attachmentsTab" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: mandatoryFieldValidationAttachmentsTab_bidsubmission");
		} catch (Exception e) {
			log.fatal("Not able to validate attachmentsTab" + e.getMessage());
			pdfResultReport.addStepDetails("mandatoryFieldValidationAttachmentsTab_bidsubmission ",
					"Not able to validate attachmentsTab", "Unable to validate" + e.getMessage(), "Fail", "N");
		}
	}
	//Userdefined tender workflow / corrigendum workflow send for approval page (21/12/2020)
	public void AddTwoUsersForSequentialApproval() throws Throwable {
		try {
			log.info(
					"started executing the method:: AddTwoUsersForSequentialApproval");
			click(tendercreationlocators.Userdefined_tender, "Userdefined");
			click(tendercreationlocators.sectionWiseComments_tender, "sectionWiseComments");
			//need loop if the the user not added previously
			
			waitForObj(3000);
			while(ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.cancelUser1_tender).isDisplayed())
			{
				click(tendercreationlocators.cancelUser1_tender, "cancelUser1");
				waitForObj(2000);
			}
			
			/*
			 * waitForObj(3000); click(tendercreationlocators.cancelUser1_tender,
			 * "cancelUser1"); waitForObj(2000);
			 * click(tendercreationlocators.cancelUser1_tender, "cancelUser1");
			 */
			waitForObj(2000);
			click(tendercreationlocators.userAdd_tender, "userAdd");
			waitForObj(2000);
			set(tendercreationlocators.user1_tender, pdfResultReport.testData.get("UserTenderApprover1"), "user1");
			waitForObj(2000);
			select(tendercreationlocators.approverType1_p_tender, pdfResultReport.testData.get("ApprovalType2"));
			waitForObj(2000);
			click(tendercreationlocators.userAdd_tender, "userAdd");
			waitForObj(2000);
			set(tendercreationlocators.user2_tender, pdfResultReport.testData.get("UserTenderApprover2"), "user2");
			waitForObj(2000);
			select(tendercreationlocators.approverType2_p_tender, pdfResultReport.testData.get("ApprovalType2"));
			waitForObj(2000);
			set(tendercreationlocators.comments_tender, pdfResultReport.testData.get("UserDefinedApprover-Comments"),
					"comments");
			pdfResultReport.addStepDetails("AddTwoUsersForSequentialApproval",
					"Sequential Approval Must Be selected",
					"Sequential Approval selected Sucessfully"
							+ " ",
					"Pass", "Y");
			waitForObj(2000);
			click(tendercreationlocators.sendForApproval_tender, "sendForApproval");
			
			waitForElementToBeVisible(tendercreationlocators.defaultCatBy);
			
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			
			waitForObj(2000);
			pdfResultReport.addStepDetails("AddTwoUsersForSequentialApproval",
					"Must Submit the Tender With Seq Flow ",
					"Sucessfully Submitted Tender With Seq Flow "
							+ " ",
					"Pass", "Y");
			log.info(
					"completed executing the method:: AddTwoUsersForSequentialApproval");
		} catch (Exception e) {
			log.fatal("Not able to Submit tender" + e.getMessage());
			pdfResultReport.addStepDetails("AddTwoUsersForSequentialApproval",
					"Must Submit the Tender With Seq Flow ",
					"Not able to Submit tender"
							+ e.getMessage(),
					"Fail", "N");
                       Assert.fail("Failed Due to " + e.getMessage());
		}
	}
	//verifying pending tender in 2nd approver during tender sequential approval (21/12/2020)
	public void Verifying_Pendingtender_sequentialWF() throws Throwable {
		try {
			log.info("started executing the method:: Verifying_Pendingtender_sequentialWF");
			
			JSClick(tendercreationlocators.workFlow, "workFlow");
			waitForObj(2000);
			JSClick(tendercreationlocators.pending, "pending");
		
		
			waitForElementToBeVisible(tendercreationlocators.Tenderlink_approver_tender);
			
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			
			waitForObj(2000);
			
			set(tendercreationlocators.search, tenderReferenceNoLocatorText, "search");
			
			WebDriver driver = ThreadLocalWebdriver.getDriver();
			
			int size = driver.findElements(tendercreationlocators.details).size();
			waitForObj(3000);
			if(size >=1)
			{
				System.out.println("**********************************************************************");
				System.out.println("Tender is present in Appproval pending List page the size is --->"+size);
				pdfResultReport.addStepDetails("Verifying_Pendingtender_sequentialWF",
					"Pending tender should not displayed in the pending tender tab for 2nd approver in sequential approval work flow until it is approved by 1st approver",
					"Pending tender displayed in 2nd approver in sequential approval WF before approved by 1st approver", "Fail", "N");
				log.info("completed executing the method:: Verifying_Pendingtender_sequentialWF");
				System.out.println("**********************************************************************");
			}
			else if(size == 0)
			{
				System.out.println("**********************************************************************");
				System.out.println("The tender is Not there in Approval pending List page the size is --->"+size);
				pdfResultReport.addStepDetails("Verifying_Pendingtender_sequentialWF",
					"Pending tender not displayed in the pending tender tab for 2nd approver in sequential approval work flow until it is approved by 1st approver",
					"Successfully verified the pending tender tab in approval work flow", "Pass", "Y");
				log.info("completed executing the method:: Verifying_Pendingtender_sequentialWF");
				
			}

		} catch (Exception e) {
			log.fatal("Unable to display the pending tender in approval work flow" + e.getMessage());
			pdfResultReport.addStepDetails("Verifying_Pendingtender_sequentialWF",
					"Should display the pending tender in approval work flow","Unable to display the pending tender in approval work flow" + e.getMessage(),
					"Fail", "N");
                    Assert.fail("Failed Due to " + e.getMessage());
		}
	}
	//Navigating pending tender list during tender approval in tender approver login (21/12/2020)
	public void GoToApprovalworkFlowPendingTendersAndSearchTheTender() throws Throwable {
		try {
			log.info("started executing the method:: GoToApprovalworkFlowPendingTendersAndSearchTheTender");
			
			JSClick(tendercreationlocators.workFlow, "workFlow");
			waitForObj(2000);
			JSClick(tendercreationlocators.pending, "pending");
		
			waitForElementToBeVisible(tendercreationlocators.Tenderlink_approver_tender);
			
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			
			waitForObj(2000);
			
			set(tendercreationlocators.search, tenderReferenceNoLocatorText, "search");
			
			WebDriver driver = ThreadLocalWebdriver.getDriver();
			
			int size = driver.findElements(tendercreationlocators.details).size();
			waitForObj(3000);
			if(size >=1)
			{
				System.out.println("**********************************************************************");
				System.out.println("Tender is present in Appproval pending List page the size is --->"+size);
				pdfResultReport.addStepDetails("GoToApprovalworkFlowPendingTendersAndSearchTheTender",
					"Should display the pending tender in approval work flow",
					"Sucessfully displayed the pending tender in approval work flow", "Pass", "Y");
				log.info("completed executing the method:: GoToApprovalworkFlowPendingTendersAndSearchTheTender");
				System.out.println("**********************************************************************");
			}
			else if(size == 0)
			{
				System.out.println("**********************************************************************");
				System.out.println("The tender is Not there in Approval pending List page the size is --->"+size);
				pdfResultReport.addStepDetails("GoToApprovalworkFlowPendingTendersAndSearchTheTender",
					"Should display the pending tender in approval work flow","Unable to display the pending tender in approval work flow",
					"Fail", "N");
				
			}

		} catch (Exception e) {
			log.fatal("Unable to display the pending tender in approval work flow" + e.getMessage());
			pdfResultReport.addStepDetails("GoToApprovalworkFlowPendingTendersAndSearchTheTender",
					"Should display the pending tender in approval work flow","Unable to display the pending tender in approval work flow" + e.getMessage(),
					"Fail", "N");
                    Assert.fail("Failed Due to " + e.getMessage());
		}
	}
	//clicking on details link during tender approval in approver login (21/12/2020)
	public void clickDetailLinkInApprovalListPage() throws Throwable {
		try {
			log.info("started executing the method:: clickDetailLinkInApprovalListPage");
			
			click(tendercreationlocators.ActionButton_approver_tender, "ActionButton_approver_tender");
			click(tendercreationlocators.details, "details");
			waitForElementToBeVisible(tendercreationlocators.approverComment);
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForObj(5000);
			pdfResultReport.addStepDetails("clickDetailLinkInApprovalListPage",
					"Should Naviagte to Approver section comments page",
					"Sucessfully  Naviagte to Approver section comments page", "Pass", "Y");

			log.info("completed executing the method:: clickDetailLinkInApprovalListPage");
		} catch (Exception e) {
			log.fatal("Unable to Naviagte to Approver section comments page" + e.getMessage());
			pdfResultReport.addStepDetails("clickDetailLinkInApprovalListPage",
					"Should Naviagte to Approver section comments page",
					"Unable to Naviagte to Approver section comments page" + e.getMessage(), "Fail", "N");

                     Assert.fail("Failed Due to " + e.getMessage());
		}
	}
	//providing sectionwise comment in each tab during tender approval (21/12/2020)
	public void tenderApprover_dynamicity() throws Exception {
		try {

			WebDriver driver = ThreadLocalWebdriver.getDriver();
			List<WebElement> TotalTabs = driver
					.findElements(By.xpath("//div[@id='rfq_headermain']//li//a[@class='ng-binding']"));

			Thread.sleep(3000);
			int i;
			for (i = 1; i <= TotalTabs.size(); i++) {
				String xpath = "(//div[@id='rfq_headermain']//li//a[@class='ng-binding'])[" + i + "]";

				JavascriptExecutor javas = (JavascriptExecutor) driver;

				WebElement tabElemnt = driver.findElement(By.xpath(xpath));

				javas.executeScript("arguments[0].click();", tabElemnt);

				waitTillSpinnerDisable(driver, tendercreationlocators.LoadingBy);

				waitForObj(3000);

				driver.findElement(By.xpath("(//textarea[@ng-model='template.tabWiseApproverComment'])["+i+"]"))
						.sendKeys("done verification for the tab section");

				waitForObj(2000);

				driver.findElement(By.xpath("(//button[contains(text(),'Save Tab Comment')])["+ i +"]")).click();

				waitTillSpinnerDisable(driver, tendercreationlocators.LoadingBy);

				waitForObj(3000);

				waitForElementToBeVisible(tendercreationlocators.alertTabOk);

				click(tendercreationlocators.alertTabOk, "alertTabOk");
				Thread.sleep(3000);

				if (i % 4 == 0) {
					driver.findElement(By.xpath("//a[@class='bx-next']")).click();
				}

				pdfResultReport.addStepDetails("tenderapprover", "Should give Comment for each Section",
						"Successfully given Comment" + " ", "Pass", "Y");

			}
			pdfResultReport.addStepDetails("tenderapprover", "Should give Comments for all Section",
					"Successfully given Comments for all tabs" + " ", "Pass", "Y");
		} catch (Exception e) {
			pdfResultReport.addStepDetails("tenderapprover", "Should give Comments for each Section",
					"unable to  give Comments for each Section" + " ", "Fail", "Y");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}
	//Tender approval with overall comment in approver login(21/12/2020)
	public void ApproverOverAllComentWithTenderHasBeenApproved() throws Throwable {
		try {
			log.info("started executing the method:: ApproverOverAllComentWithTenderHasBeenApproved");
			String comment = "Tender Process Is Approved";
			click(tendercreationlocators.approverComment, "approverComment");
			waitForObj(1000);
			set(tendercreationlocators.Approvercomment_tender, comment,"comment");
			click(tendercreationlocators.Approvebutton_tender, "approve");
			waitForObj(500);
			//For 2nd approver in a 2 approver sequential WF
			WebDriver driver = ThreadLocalWebdriver.getDriver();
			int size = driver.findElements(tendercreationlocators.CloseWF_tender).size();
			if(size>=1)
			{
				click(tendercreationlocators.CloseWF_tender, "Close_Workflow_button");
				click(tendercreationlocators.Conf_YesbuttonWF_tender, "Confirmation_Workflow_yesbutton");
				click(tendercreationlocators.Conf_YesbuttonWF_tender, "Confirmation_Workflow_yesbutton");
				//click(tendercreationlocators.Conf_YesbuttonWF_tender, "Confirmation_Workflow_yesbutton");
			}else
			{
				//click(tendercreationlocators.Conf_YesbuttonWF_tender, "Confirmation_Workflow_yesbutton");
			}

			waitForElementToBeVisible(tendercreationlocators.Tenderlink_approver_tender);

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			waitForObj(5000);

			pdfResultReport.addStepDetails("ApproverOverAllComentWithTenderHasBeenApproved",
					"Should Provide OverAll Comment ",
					"SucessFully Provided Over All Comment As --->  " + comment + " ", "Pass", "Y");
			log.info("completed executing the method:: ApproverOverAllComentWithTenderHasBeenApproved");
		} catch (Exception e) {
			log.fatal("Unable to Provide OverAll Comment " + e.getMessage());
			pdfResultReport.addStepDetails("ApproverOverAllComentWithTenderHasBeenApproved",
					"Should Provide OverAll Comment ",
					"Unable to Provide OverAll Comment"
							+ e.getMessage(),
					"Fail", "N");
                    Assert.fail("Failed Due to " + e.getMessage());
		}
	}
	
	//Review tender with overall comment in tender approver login (22/12/2020)
	public void ReviewTender_withoverall_comment() throws Throwable {
		try {
			log.info("started executing the method:: ReviewTender_withoverall_comment");
			String comment = "Tender Process Is Reviewed";
			click(tendercreationlocators.approverComment, "approverComment");
			waitForObj(1000);
			set(tendercreationlocators.Approvercomment_tender, comment,"comment");
			click(tendercreationlocators.Reviewbutton_tender, "Review");
			waitForObj(500);
			click(tendercreationlocators.Conf_Ok_button, "Conf_Ok_button");
			waitForElementToBeVisible(tendercreationlocators.Tenderlink_approver_tender);
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForObj(5000);
			pdfResultReport.addStepDetails("ReviewTender_withoverall_comment",
					"Should Provide OverAll Comment ",
					"SucessFully Provided Over All Comment As --->  " + comment + " and reviewed the tender ", "Pass", "Y");
			log.info("completed executing the method:: ReviewTender_withoverall_comment");
		} catch (Exception e) {
			log.fatal("Unable to Provide OverAll Comment " + e.getMessage());
			pdfResultReport.addStepDetails("ReviewTender_withoverall_comment",
					"Should Provide OverAll Comment ",
					"Unable to Provide OverAll Comment"
							+ e.getMessage(),
					"Fail", "N");
                    Assert.fail("Failed Due to " + e.getMessage());
		}
	}
	//Checking Draft status after review of tender (22/12/2020)
	public void checkDraft_tenderstatus_after_review() throws Exception {
		try {

			WebElement Tenderstatus = ThreadLocalWebdriver.getDriver()
					.findElement(tendercreationlocators.tenderCreatedStatus);
			WebDriverWait wait = new WebDriverWait(ThreadLocalWebdriver.getDriver(), 60);
			wait.until(ExpectedConditions.textToBePresentInElement(Tenderstatus, Tenderstatus.getText()));
			String tenderStatus = text(tendercreationlocators.tenderCreatedStatus);

			if (tenderStatus.trim().equalsIgnoreCase("Draft"))
				System.out.println("Tender Status is in Draft mode");

			WebElement bidpublishStage = ThreadLocalWebdriver.getDriver()
					.findElement(tendercreationlocators.bidpublishStage);

			if (bidpublishStage.getAttribute("class").trim().contains("grnBtn")) {
				System.out.println("stage mode is in Published mode" + bidpublishStage.getAttribute("class").trim());
			} else {
				System.out.println(
						"stage mode is in not in Published mode" + bidpublishStage.getAttribute("class").trim());
			}
			pdfResultReport.addStepDetails("Successfully shown Tender status as Draft",
					"Tender status must be shown as Draft using checkDraft_tenderstatus_after_review",
					"Tender status shown as Draft using checkDraft_tenderstatus_after_review" + " ", "Pass", "Y");
			log.info("completed executing the method:: checkDraft_tenderstatus_after_review");
		} catch (Exception e) {
			log.fatal("Unable to show the status" + e.getMessage());
			pdfResultReport.addStepDetails("Unable to show the status",
					"Not able to show the tender status using checkDraft_tenderstatus_after_review",
					"Unable to show the status using checkDraft_tenderstatus_after_review" + e.getMessage(), "Fail", "N");
		}
	}
	//Checking Live status after date corrigendum of tender (01/01/2021)
	public void checkLive_tenderstatus_after_corrigendum() throws Exception {
		try {

			WebElement Tenderstatus = ThreadLocalWebdriver.getDriver()
					.findElement(tendercreationlocators.tenderCreatedStatus);
			WebDriverWait wait = new WebDriverWait(ThreadLocalWebdriver.getDriver(), 60);
			wait.until(ExpectedConditions.textToBePresentInElement(Tenderstatus, Tenderstatus.getText()));
			String tenderStatus = text(tendercreationlocators.tenderCreatedStatus);

			if (tenderStatus.trim().equalsIgnoreCase("Live"))
			{
				System.out.println("Tender Status is in Live mode");
				pdfResultReport.addStepDetails("Successfully shown Tender status as Live",
						"Tender status must be shown as Live using checkLive_tenderstatus_after_corrigendum",
						"Tender status shown as Live using checkLive_tenderstatus_after_corrigendum" + " ", "Pass", "Y");
			}
			else
			{
				pdfResultReport.addStepDetails("Verifying tender status",
						"Tender status must be shown as Live using checkLive_tenderstatus_after_corrigendum",
						"Tender status not shown as Live using checkLive_tenderstatus_after_corrigendum", "Fail", "N");
			}

			WebElement bidpublishStage = ThreadLocalWebdriver.getDriver()
					.findElement(tendercreationlocators.bidpublishStage);

			if (bidpublishStage.getAttribute("class").trim().contains("grnBtn")) {
				System.out.println("stage mode is in Published mode" + bidpublishStage.getAttribute("class").trim());
			} else {
				System.out.println(
						"stage mode is in not in Published mode" + bidpublishStage.getAttribute("class").trim());
			}
			log.info("completed executing the method:: checkLive_tenderstatus_after_corrigendum");
		} catch (Exception e) {
			log.fatal("Unable to show the status" + e.getMessage());
			pdfResultReport.addStepDetails("Unable to show the status",
					"Not able to show the tender status using checkLive_tenderstatus_after_corrigendum",
					"Unable to show the status using checkLive_tenderstatus_after_corrigendum" + e.getMessage(), "Fail", "N");
		}
	}
	//verifying pending tender in corrigendum in 2nd approver during tender sequential approval (24/12/2020)
	public void Verifying_Pendingtender_Corrigendumtab_sequentialWF() throws Throwable {
		try {
			log.info("started executing the method:: Verifying_Pendingtender_Corrigendumtab_sequentialWF");
			
			JSClick(tendercreationlocators.workFlow, "workFlow");
			waitForObj(2000);
			JSClick(tendercreationlocators.pending, "pending");
			waitForElementToBeVisible(tendercreationlocators.Tenderlink_approver_tender);
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForObj(2000);
			click(tendercreationlocators.corrigendumTab, "corrigendumTab");
            waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForObj(2000); 
			set(tendercreationlocators.search, tenderReferenceNoLocatorText, "search");
			WebDriver driver = ThreadLocalWebdriver.getDriver();
			int size = driver.findElements(tendercreationlocators.details).size();
			waitForObj(3000);
			if(size >=1)
			{
				System.out.println("**********************************************************************");
				System.out.println("Tender is present in Appproval pending List page the size is --->"+size);
				pdfResultReport.addStepDetails("Verifying_Pendingtender_Corrigendumtab_sequentialWF",
					"Pending tender should not displayed in the Corrigendum tab for 2nd approver in sequential approval work flow until it is approved by 1st approver",
					"Pending tender displayed in 2nd approver in sequential approval WF before approved by 1st approver", "Fail", "N");
				log.info("completed executing the method:: Verifying_Pendingtender_Corrigendumtab_sequentialWF");
				System.out.println("**********************************************************************");
			}
			else if(size == 0)
			{
				System.out.println("**********************************************************************");
				System.out.println("The tender is Not there in Approval pending List page the size is --->"+size);
				pdfResultReport.addStepDetails("Verifying_Pendingtender_Corrigendumtab_sequentialWF",
					"Pending tender not displayed in the corrigendum tender tab for 2nd approver in sequential approval work flow until it is approved by 1st approver",
					"Successfully verified the corrigendum tab in approval work flow", "Pass", "Y");
				log.info("completed executing the method:: Verifying_Pendingtender_Corrigendumtab_sequentialWF");
				
			}

		} catch (Exception e) {
			log.fatal("Unable to display the pending tender in approval work flow" + e.getMessage());
			pdfResultReport.addStepDetails("Verifying_Pendingtender_Corrigendumtab_sequentialWF",
					"Should display the corrigendum tender in approval work flow","Unable to display the corrigendum tender in approval work flow" + e.getMessage(),
					"Fail", "N");
                    Assert.fail("Failed Due to " + e.getMessage());
		}
	}
	// Verifying pending tender under Corrigendum tab in 1st corrigendum approver login (24/12/2020)
	public void clickCorrigendumTabAndSearchThePendingListTenderNo() throws Throwable {
		try {
			log.info("started executing the method:: clickCorrigendumTabAndSearchThePendingListTenderNo");
			
			JSClick(tendercreationlocators.workFlow, "workFlow");
			
			waitForObj(2000);
			
			JSClick(tendercreationlocators.pending, "pending");
		
			waitForElementToBeVisible(tendercreationlocators.Tenderlink_approver_tender);
			
			waitForObj(2000);
			
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			
			waitForObj(2000);
			
			click(tendercreationlocators.corrigendumTab, "corrigendumTab");
			
            waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			
			waitForObj(2000);   
			
			set(tendercreationlocators.search, tenderReferenceNoLocatorText, "search");
			
			WebDriver driver = ThreadLocalWebdriver.getDriver();
			
			int size = driver.findElements(tendercreationlocators.details).size();
			
			if(size >=1)
			{
				System.out.println("**********************************************************************");
				System.out.println("Tender is present in Appproval pending List page the size is --->"+size);
			pdfResultReport.addStepDetails("clickCorrigendumTabAndSearchThePendingListTenderNo",
				"Should display the pending tender under Corrigendum tab in approval work flow",
				"Successfully displayed the pending tender under corrigendum tab in approval work flow", "Pass", "Y");
			log.info("completed executing the method:: clickCorrigendumTabAndSearchThePendingListTenderNo");
			System.out.println("**********************************************************************");
			}
			else if(size == 0)
			{
				System.out.println("**********************************************************************");
				System.out.println("The tender is Not there in Approval pending List page the size is --->"+size);
			pdfResultReport.addStepDetails("clickCorrigendumTabAndSearchThePendingListTenderNo",
				"Should display the pending tender under Corrigendum tab in approval work flow","Unable to display the pending tender under Corrigendum tab in approval work flow",
				"Fail", "N");
				
			}
			waitForObj(3000);
			
		} catch (Exception e) {
			log.fatal("Unable to display the pending tender in approval work flow" + e.getMessage());
			pdfResultReport.addStepDetails("clickCorrigendumTabAndSearchThePendingListTenderNo",
					"Should display the pending tender under Corrigendum tab in approval work flow",
					"Unable to display the pending tender under Corrigendum tab in approval work flow" + e.getMessage(),
					"Fail", "N");
			Assert.fail("Failed due to -->" + e.getMessage());
		}
	}
	//clicking on details link during Corrigendum approval in approver login (24/12/2020)
	public void clickDetailLinkInApprovalListPage_CorrigendumApproval() throws Throwable {
		try {
			log.info("started executing the method:: clickDetailLinkInApprovalListPage_CorrigendumApproval");
			click(tendercreationlocators.details, "details");
			waitForElementToBeVisible(tendercreationlocators.approverComment);
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForObj(5000);
			pdfResultReport.addStepDetails("clickDetailLinkInApprovalListPage_CorrigendumApproval",
					"Should Naviagte to Approver section comments page",
					"Sucessfully  Naviagte to Approver section comments page", "Pass", "Y");

			log.info("completed executing the method:: clickDetailLinkInApprovalListPage_CorrigendumApproval");
		} catch (Exception e) {
			log.fatal("Unable to Naviagte to Approver section comments page" + e.getMessage());
			pdfResultReport.addStepDetails("clickDetailLinkInApprovalListPage_CorrigendumApproval",
					"Should Naviagte to Approver section comments page",
					"Unable to Naviagte to Approver section comments page" + e.getMessage(), "Fail", "N");

                     Assert.fail("Failed Due to " + e.getMessage());
		}
	}
	// Providing approver comments for date schedule tab during date corrigendum (24/12/2020)	
	public void provideApproverCommentsForDateScheduleTab() throws Throwable {
		try {
			log.info("started executing the method:: provideApproverCommentsForDateScheduleTab");
			waitForObj(5000);
			set(tendercreationlocators.dateScheduleComment, "Done Verification for the dateSchedule Tab section",
					"dateScheduleComment");
			click(tendercreationlocators.saveTabComment_DateSchedule, "saveTabComment_DateSchedule");
			waitForElementToBeVisible(tendercreationlocators.alertTab);
			IsElementPresent(tendercreationlocators.alertTab);
			pdfResultReport.addStepDetails("provideApproverCommentsForDateScheduleTab",
					"Should Provide Comment and save", "Sucessfully Provided Comment and saved" + " ", "Pass", "Y");
			click(tendercreationlocators.alertTabOk, "alertTabOk");
			log.info("completed executing the method:: provideApproverCommentsForDateScheduleTab");
		} catch (Exception e) {
			log.fatal("Not able to Provide Comment and save" + e.getMessage());
			pdfResultReport.addStepDetails("provideApproverCommentsForDateScheduleTab",
					"Should Provide Comment and save", "Not able to Provide Comment and save" + e.getMessage(), "Fail",
					"N");
			Assert.fail("Failed due to -->" + e.getMessage());
		}
	}
	//Corrigendum approval with overall comment in approver login(24/12/2020)
	public void ApproverOverAllComentWithCorrigendumHasBeenApproved() throws Throwable {
		try {
			log.info("started executing the method:: ApproverOverAllComentWithCorrigendumHasBeenApproved");
			String comment = "Tender Process Is Approved";
			click(tendercreationlocators.approverComment, "approverComment");
			waitForObj(1000);
			set(tendercreationlocators.Approvercomment_tender, comment,"comment");
			click(tendercreationlocators.Approvebutton_tender, "approve");
			waitForObj(500);
			//For 2nd approver in a 2 approver sequential WF
			WebDriver driver = ThreadLocalWebdriver.getDriver();
			int size = driver.findElements(tendercreationlocators.CloseWF_tender).size();
			if(size>=1)
			{
				click(tendercreationlocators.CloseWF_tender, "Close_Workflow_button");
				click(tendercreationlocators.Conf_YesbuttonWF_tender, "Confirmation_Workflow_yesbutton");
				click(tendercreationlocators.Conf_YesbuttonWF_tender, "Confirmation_Workflow_yesbutton");
			}
			click(tendercreationlocators.Conf_YesbuttonWF_tender, "Confirmation_Workflow_yesbutton");
			waitForElementToBeVisible(tendercreationlocators.Tenderlink_approver_tender);
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForObj(5000);
			pdfResultReport.addStepDetails("ApproverOverAllComentWithCorrigendumHasBeenApproved",
					"Should Provide OverAll Comment ",
					"SucessFully Provided Over All Comment As --->  " + comment + " ", "Pass", "Y");
			log.info("completed executing the method:: ApproverOverAllComentWithCorrigendumHasBeenApproved");
		} catch (Exception e) {
			log.fatal("Unable to Provide OverAll Comment " + e.getMessage());
			pdfResultReport.addStepDetails("ApproverOverAllComentWithCorrigendumHasBeenApproved",
					"Should Provide OverAll Comment ",
					"Unable to Provide OverAll Comment"
							+ e.getMessage(),
					"Fail", "N");
                    Assert.fail("Failed Due to " + e.getMessage());
		}
	}
	//Reject corrigendum with overall comment in approver login(01/01/2021)
	public void Reject_Corrigendum_with_overallcomment() throws Throwable {
		try {
			log.info("started executing the method:: Reject_Corrigendum_with_overallcomment");
			String comment = "Corrigendum is Reviewed";
			click(tendercreationlocators.approverComment, "approverComment");
			waitForObj(1000);
			set(tendercreationlocators.Approvercomment_tender, comment,"comment");
			click(tendercreationlocators.Reviewbutton_tender, "Review");
			waitForObj(500);
			waitForElementToBeVisible(tendercreationlocators.Tenderlink_approver_tender);
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForObj(5000);
			pdfResultReport.addStepDetails("Reject_Corrigendum_with_overallcomment",
					"Should Provide OverAll Comment ",
					"SucessFully Provided Over All Comment As --->  " + comment + " ", "Pass", "Y");
			log.info("completed executing the method:: Reject_Corrigendum_with_overallcomment");
		} catch (Exception e) {
			log.fatal("Unable to Provide OverAll Comment " + e.getMessage());
			pdfResultReport.addStepDetails("Reject_Corrigendum_with_overallcomment",
					"Should Provide OverAll Comment ",
					"Unable to Provide OverAll Comment"
							+ e.getMessage(),
					"Fail", "N");
                    Assert.fail("Failed Due to " + e.getMessage());
		}
	}
	//Saving submitted bid no (04/01/2021)
	public String BidNoSave() throws Throwable {
		log.info("started executing the method:: BidNoSave");
		BidnoLocatorText = text(tendercreationlocators.BidNo_bidsubmission).trim();
		System.out.println(BidnoLocatorText);
		return BidnoLocatorText;
	}
	//Saving Draft bid no (05/01/2021)
	public String DraftBidNoSave() throws Throwable {
		log.info("started executing the method:: DraftBidNoSave");
		DraftBidnoLocatorText = text(tendercreationlocators.BidNo_Draftbid_bidsubmission).trim();
		System.out.println(DraftBidnoLocatorText);
		return DraftBidnoLocatorText;
	}
	//Bid submission during rebid(05/01/2021)
	public void Bid_submission_during_Rebid() throws Throwable {
		try {
			log.info("started executing the method:: Bid_submission_during_Rebid");
		//Viewing Technical tab	
			waitForObj(2000);
			waitForSpinnerToDisappearInBidSubmission();
			waitForElementToBeVisible(tendercreationlocators.technicalTabFileuploadEleBy);
		//clicking on Terms and conditions tab	
			click(tendercreationlocators.termsAndCondition_bidSubmission, "termsAndCondition_bidSubmission");
			waitForObj(2000);
			waitForSpinnerToDisappearInBidSubmission();
			waitForElementToBeVisible(tendercreationlocators.termsAndConditionsClauseBy);
		//Clicking on General info tab and modify Quot ref code	
			click(tendercreationlocators.Geninfotab_bidsubmission, "Geninfotab_bidsubmission");
			waitForObj(2000);
			waitForElementToBeVisible(tendercreationlocators.tenderId_generalInformation_bidSubmission);
			set(tendercreationlocators.quotationReferenceCodeInput_generalInformation_bidSubmission,
					"EditedQuotRefCode",
					"quotationReferenceCodeInput_generalInformation_RebidSubmission");
		//Clicking on BOQOptional tab	
			click(tendercreationlocators.BOQOptionalTab, "BOQOptionalTab");
			waitForObj(2000);
			waitForSpinnerToDisappearInBidSubmission();
			waitForElementToBeVisible(tendercreationlocators.boqOptionalAddOptionalBy);			
		//Clicking on Attachment tab
			click(tendercreationlocators.attachementsTab, "attachementsTab");
			waitForObj(2000);
			waitForSpinnerToDisappearInBidSubmission();	
			click(tendercreationlocators.attachment_subtab, "Attachment_subtab");
			waitForElementToBeVisible(tendercreationlocators.addtenderattachmenticon);			
		//Clicking on Commercial tab	
			click(tendercreationlocators.commercial_bidSubmission, "commercial_bidSubmission");
			waitForObj(2000);
			waitForSpinnerToDisappear();
			waitForElementToBeVisible(tendercreationlocators.documentChargeFieldEleBy);	
		//Clicking on BOQ Mandatory tab	
			click(tendercreationlocators.BOQmandatoryTab, "BOQmandatoryTab");
			waitForObj(2000);
			waitForSpinnerToDisappearInBidSubmission();
			waitForElementToBeVisible(tendercreationlocators.boqaddOptionItemButtonBy);
		//Clicking on save button and alert verification
			click(tendercreationlocators.savebutton, "savebutton");
			waitForObj(2000);
			waitForSpinnerToDisappearInBidSubmission();
			waitForElementToBeVisible(tendercreationlocators.alertPopUp_QRC_bidSubmission);
			IsElementPresent(tendercreationlocators.alertPopUp_QRC_bidSubmission);
			IsElementPresent(tendercreationlocators.boqOptionalAlertTab_BidSubmissionBy);
			IsElementPresent(tendercreationlocators.technicalAlertTab_bidSubmission);
			IsElementPresent(tendercreationlocators.termsAndConditionAlertTab_bidSubmission);
			IsElementPresent(tendercreationlocators.commercialAlertTab_bidSubmission);
			waitForObj(2000);
			click(tendercreationlocators.alertClose_QRC_bidSubmission, "alertClose_QRC_bidSubmission");
		//Clicking on Submit button	
			waitForObj(2000);
			click(tendercreationlocators.submitbutton, "submitbutton");
			waitForObj(2000);
			waitForSpinnerToDisappearInBidSubmission();
			waitForElementToBeVisible(tendercreationlocators.previewAllTittle);
			waitForObj(2000);
		//Preview all page validation
			validateAllInPrevieAllSubmitbidPage();
		//Submit bid and verify success message
			submitBid_link_in_previewAllPage();
			pdfResultReport.addStepDetails("Bid_submission_during_Rebid",
					"Bid submisison during rebid should be successful ",
					"Successfully bid submitted during Rebid", "Pass", "Y");
			log.info("completed executing the method:: Bid_submission_during_Rebid");
		} catch (Exception e) {
			log.fatal("Unable to Provide OverAll Comment " + e.getMessage());
			pdfResultReport.addStepDetails("Bid_submission_during_Rebid",
					"Should be able to submit bid during rebid",
					"Unable to submit bid during rebid"
							+ e.getMessage(),
					"Fail", "N");
                    Assert.fail("Failed Due to " + e.getMessage());
		}
	}
	//Bid submission during rebid for TG1(22/02/2021)
	public void TG1_Bid_submission_during_Rebid() throws Throwable {
		try {
			log.info("started executing the method:: TG1_Bid_submission_during_Rebid");

		//Viewing on General info tab and modify Quot ref code	
			waitForSpinnerToDisappearInBidSubmission();
			waitForObj(2000);
			waitForElementToBeVisible(tendercreationlocators.tenderId_generalInformation_bidSubmission);
			clear(tendercreationlocators.quotationReferenceCodeInput_generalInformation_bidSubmission,"quotationReferenceCodeInput_generalInformation_RebidSubmission");
			set(tendercreationlocators.quotationReferenceCodeInput_generalInformation_bidSubmission,
					"EditedQuotRefCode",
					"quotationReferenceCodeInput_generalInformation_RebidSubmission");

			pdfResultReport.addStepDetails("TG1_Bid_submission_during_Rebid ",
					"Verify General info tab and modify quot ref code text", "Verification of General info tab and modification of quot ref code text is successful", "Pass",
					"Y");
			
		//Verifying Terms & Conditions tab++++++++++++++++++++++++++++++++++++++++++++	
			waitForElementToBeClickable(tendercreationlocators.TermsandConditionstabLnk_BidSubmission_TG1);
			click(tendercreationlocators.TermsandConditionstabLnk_BidSubmission_TG1, "TermsandConditionstabLnk_BidSubmission_TG1");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForElementToBeVisible(tendercreationlocators.ClauseTxtTermsandConditionstab_BidSubmission_TG1);
			waitForObj(200);
		//Verifying Technical Compliance Table tab++++++++++++++++++++++++++++++++++++++++++++	
			waitForElementToBeClickable(tendercreationlocators.TechnicalCompliancetabLnk_BidSubmission_TG1);
			click(tendercreationlocators.TechnicalCompliancetabLnk_BidSubmission_TG1, "TechnicalCompliancetabLnk_BidSubmission_TG1");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForElementToBeVisible(tendercreationlocators.ClauseTxtTechnicalCompliancetab_BidSubmission_TG1);
			waitForObj(200);
		//Verifying Attachments tab++++++++++++++++++++++++++++++++++++++++++++		
			waitForElementToBeClickable(tendercreationlocators.AttachmentstabLnk_BidSubmission_TG1);
			click(tendercreationlocators.AttachmentstabLnk_BidSubmission_TG1, "AttachmentstabLnk_BidSubmission_TG1");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForElementToBeVisible(tendercreationlocators.Attachment_subtabLnk_BidSubmission_TG1);
			waitForObj(200);
		//Verifying Technical tab++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			waitForElementToBeClickable(tendercreationlocators.TechnicaltabLnk_BidSubmission_TG1);
			click(tendercreationlocators.TechnicaltabLnk_BidSubmission_TG1, "TechnicaltabLnk_BidSubmission_TG1");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForObj(500);	
		//Clicking on Next link to get the remaining tab
			click(tendercreationlocators.NextLnk_Tender_TG1, "NextLnk_Tender_TG1");
		//Verifying Specifications and Technical Requirements Compliance tab++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			waitForElementToBeClickable(tendercreationlocators.TechnicalReqComptabLnk_BidSubmission_TG1);
			click(tendercreationlocators.TechnicalReqComptabLnk_BidSubmission_TG1, "TechnicalReqComptabLnk_BidSubmission_TG1");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForObj(500);
		//Verifying Commercial Parameters Compliance tab++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			waitForElementToBeClickable(tendercreationlocators.CommercialComptabLnk_BidSubmission_TG1);
			click(tendercreationlocators.CommercialComptabLnk_BidSubmission_TG1, "CommercialComptabLnk_BidSubmission_TG1");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForObj(1000);
		//Clicking on Next link to get the remaining tab
			click(tendercreationlocators.NextLnk_Tender_TG1, "NextLnk_Tender_TG1");
		//Verifying General Requirement Equipment details tab++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			waitForElementToBeClickable(tendercreationlocators.GeneralReqEqiptabLnk_BidSubmission_TG1);
			click(tendercreationlocators.GeneralReqEqiptabLnk_BidSubmission_TG1, "GeneralReqEqiptabLnk_BidSubmission_TG1");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForElementToBeVisible(tendercreationlocators.Address_GeneralReqEqiptab_BidSubmission_TG1);
			waitForObj(500);
		//Verifying Other Clauses tab++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			waitForElementToBeClickable(tendercreationlocators.OtherClausestabLnk_BidSubmission_TG1);
			click(tendercreationlocators.OtherClausestabLnk_BidSubmission_TG1, "OtherClausestabLnk_BidSubmission_TG1");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForElementToBeVisible(tendercreationlocators.VendorCode_OtherClausestab_BidSubmission_TG1);
			waitForObj(500);
		//Verifying Payment tab+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			waitForElementToBeClickable(tendercreationlocators.PaymenttabLnk_BidSubmission_TG1);
			click(tendercreationlocators.PaymenttabLnk_BidSubmission_TG1, "PaymenttabLnk_BidSubmission_TG1");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForElementToBeVisible(tendercreationlocators.Paymentbtn_Paymenttab_BidSubmission_TG1);
			waitForObj(500);
		//Verifying RFQ Item tab+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			waitForElementToBeClickable(tendercreationlocators.RFQItemtabLnk_BidSubmission_TG1);
			click(tendercreationlocators.RFQItemtabLnk_BidSubmission_TG1, "RFQItemtabLnk_BidSubmission_TG1");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForElementToBeVisible(tendercreationlocators.UnitRateTxt_RFQItemtab_BidSubmission_TG1);
			waitForObj(500);
		//Verifying BOQ Mandatory tab+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			waitForElementToBeClickable(tendercreationlocators.BOQMandatorytabLnk_BidSubmission_TG1);
			click(tendercreationlocators.BOQMandatorytabLnk_BidSubmission_TG1, "BOQMandatorytabLnk_BidSubmission_TG1");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForElementToBeVisible(tendercreationlocators.UnitRateTxt_BOQMandatorytab_BidSubmission_TG1);
			waitForObj(500);
			
		//Clicking on Submit button	
			click(tendercreationlocators.submitbutton, "submitbutton");
			waitForObj(4000);
			waitForSpinnerToDisappearInBidSubmission();
			waitForElementToBeVisible(tendercreationlocators.previewAllTittle);
			waitForObj(2000);
		//Preview all page validation
			TG1_validateAllInPrevieAllSubmitbidPage();
		//Submit bid and verify success message
			submitBid_link_in_previewAllPage();
			pdfResultReport.addStepDetails("TG1_Bid_submission_during_Rebid",
					"Bid submisison during rebid/edit bid should be successful ",
					"Successfully bid submitted during rebid/edit bid", "Pass", "Y");
			log.info("completed executing the method:: Bid_submission_during_Rebid");
		} catch (Exception e) {
			log.fatal("Unable to Provide OverAll Comment " + e.getMessage());
			pdfResultReport.addStepDetails("TG1_Bid_submission_during_Rebid",
					"Should be able to submit bid during rebid/edit bid",
					"Unable to submit bid during rebid/edit bid"
							+ e.getMessage(),
					"Fail", "N");
                    Assert.fail("Failed Due to " + e.getMessage());
		}
	}
	//Bid submission during rebid for TG3(19/03/2021)
	public void TG3_Bid_submission_during_Rebid() throws Throwable {
		try {
			log.info("started executing the method:: TG3_Bid_submission_during_Rebid");

		//Viewing on General info tab and modify Quot ref code	
			waitForSpinnerToDisappearInBidSubmission();
			waitForObj(2000);
			waitForElementToBeVisible(tendercreationlocators.tenderId_generalInformation_bidSubmission);
			clear(tendercreationlocators.quotationReferenceCodeInput_generalInformation_bidSubmission,"quotationReferenceCodeInput_generalInformation_RebidSubmission");
			set(tendercreationlocators.quotationReferenceCodeInput_generalInformation_bidSubmission,
					"EditedQuotRefCode",
					"quotationReferenceCodeInput_generalInformation_RebidSubmission");

			pdfResultReport.addStepDetails("TG3_Bid_submission_during_Rebid ",
					"Verify General info tab and modify quot ref code text", "Verification of General info tab and modification of quot ref code text is successful", "Pass",
					"Y");
			
		//Verifying Date Schedule tab++++++++++++++++++++++++++++++++++++++++++++	
			waitForElementToBeClickable(tendercreationlocators.DateScheduletabLnk_BidSubmission_TG3);
			click(tendercreationlocators.DateScheduletabLnk_BidSubmission_TG3, "DateScheduletabLnk_BidSubmission_TG3");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForElementToBeVisible(tendercreationlocators.AttachmentSubtabLnk_BidSubmission_TG3);
			waitForObj(200);
		//Verifying Commercial Terms and Conditions tab++++++++++++++++++++++++++++++++++++++++++++	
			waitForElementToBeClickable(tendercreationlocators.CommercialTermsConditionstabLnk_BidSubmission_TG3);
			click(tendercreationlocators.CommercialTermsConditionstabLnk_BidSubmission_TG3, "CommercialTermsConditionstabLnk_BidSubmission_TG3");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForElementToBeVisible(tendercreationlocators.ClauseTxt_CommercialTermsConditionstab_BidSubmission_TG3);
			waitForObj(200);
		//Verifying Terms and Conditions tab++++++++++++++++++++++++++++++++++++++++++++		
			waitForElementToBeClickable(tendercreationlocators.TermsConditionstabLnk_BidSubmission_TG3);
			click(tendercreationlocators.TermsConditionstabLnk_BidSubmission_TG3, "TermsConditionstabLnk_BidSubmission_TG3");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForElementToBeVisible(tendercreationlocators.ClauseTxt_TermsConditionstab_BidSubmission_TG3);
			waitForObj(200);
		//Verifying General Information Clauses tab++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			waitForElementToBeClickable(tendercreationlocators.GeneralinfoClausestabLnk_BidSubmission_TG3);
			click(tendercreationlocators.GeneralinfoClausestabLnk_BidSubmission_TG3, "GeneralinfoClausestabLnk_BidSubmission_TG3");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForElementToBeVisible(tendercreationlocators.Mandatoryitemlbl_GeneralinfoClausestab_BidSubmission_TG3);
			waitForObj(500);	
		//Clicking on Next link to get the remaining tab
			click(tendercreationlocators.NextLnk_Tender_TG1, "NextLnk_Tender_TG1");
		//Verifying Price Format tab++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			waitForElementToBeClickable(tendercreationlocators.PriceFormattabLnk_BidSubmission_TG3);
			click(tendercreationlocators.PriceFormattabLnk_BidSubmission_TG3, "PriceFormattabLnk_BidSubmission_TG3");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForElementToBeVisible(tendercreationlocators.Mandatoryitemlbl_PriceFormattab_BidSubmission_TG3);
			waitForObj(500);
		//Verifying Price Summary tab++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			waitForElementToBeClickable(tendercreationlocators.PriceSummarytabLnk_BidSubmission_TG3);
			click(tendercreationlocators.PriceSummarytabLnk_BidSubmission_TG3, "PriceSummarytabLnk_BidSubmission_TG3");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForElementToBeVisible(tendercreationlocators.IncotermsSelect_PriceSummarytab_BidSubmission_TG3);
			waitForObj(1000);
		//Clicking on Submit button	
			click(tendercreationlocators.submitbutton, "submitbutton");
			waitForObj(4000);
			waitForSpinnerToDisappearInBidSubmission();
			waitForElementToBeVisible(tendercreationlocators.previewAllTittle);
			waitForObj(2000);
		//Preview all page validation
			TG3_validateAllInPrevieAllSubmitbidPage();
		//Submit bid and verify success message
			submitBid_link_in_previewAllPage();
			pdfResultReport.addStepDetails("TG3_Bid_submission_during_Rebid",
					"Bid submisison during rebid/edit bid should be successful ",
					"Successfully bid submitted during rebid/edit bid", "Pass", "Y");
			log.info("completed executing the method:: TG3_Bid_submission_during_Rebid");
		} catch (Exception e) {
			log.fatal("Unable to Provide OverAll Comment " + e.getMessage());
			pdfResultReport.addStepDetails("TG3_Bid_submission_during_Rebid",
					"Should be able to submit bid during rebid/edit bid",
					"Unable to submit bid during rebid/edit bid"
							+ e.getMessage(),
					"Fail", "N");
                    Assert.fail("Failed Due to " + e.getMessage());
		}
	}
	
	//Bid no verification after Rebid (05/01/2021)
	public void VerifyBidno_after_rebid() throws Throwable {
		try {	
		log.info("started executing the method:: VerifyBidno_after_rebid");
		String BidnoLocatorText_afterRebid = text(tendercreationlocators.BidNo_bidsubmission).trim();
		if(BidnoLocatorText.equals(BidnoLocatorText_afterRebid))
		{
			pdfResultReport.addStepDetails("VerifyBidno_after_rebid",
					"Submitted bid no after Rebid should be different than original bid no ",
					"Submitted bid no after Rebid '"+BidnoLocatorText_afterRebid+ "' is not different than original bid no '"+BidnoLocatorText+"'",
					"Fail", "N");
		}
		else
		{
			pdfResultReport.addStepDetails("VerifyBidno_after_rebid",
					"Submitted bid no after Rebid should be different than original bid no ",
					"Submitted bid no after Rebid '"+BidnoLocatorText_afterRebid+ "' is different than original bid no '"+BidnoLocatorText+"'", "Pass", "Y");
		}
		log.info("Completed executing the method:: VerifyBidno_after_rebid");
		}catch(Exception e)
		{
			log.fatal("Unable to verify bid no after rebid " + e.getMessage());
			pdfResultReport.addStepDetails("VerifyBidno_after_rebid",
				"Should be able to verify bid no after rebid",
				"Unable to verify bid no after rebid"
						+ e.getMessage(),
				"Fail", "N");
	            Assert.fail("Failed Due to " + e.getMessage());
		}
	}
	//Validate Bid preview from bid list page (05-01-2021)
	public void validateBidPreview_BidListPage_afterRebid() throws Throwable {
		try {
			log.info("started executing the method:: validateBidPreview_BidListPage");
			click(tendercreationlocators.action, "action");
			click(tendercreationlocators.bidPreview, "bidSubmission_Bid");
			waitForObj(5000);
			IsElementPresent(tendercreationlocators.technical_priviewAll);
			IsElementPresent(tendercreationlocators.termsAndCondition_priviewAll);
			IsElementPresent(tendercreationlocators.generalInformation_priviewAll);
			IsElementPresent(tendercreationlocators.Quot_ref_code_afterRebidPreview);
			IsElementPresent(tendercreationlocators.bidderSpecificAttachments_priviewAll);
			IsElementPresent(tendercreationlocators.commercial_priviewAll);
			IsElementPresent(tendercreationlocators.boqoptionalpreviewAllpageBy);
			IsElementPresent(tendercreationlocators.boqMandatorypreviewAllpageBy);
			click(tendercreationlocators.previewAll_close, "previewAll_close");
			pdfResultReport.addStepDetails("validateBidPreview_BidListPage",
					"bidPreview details must be validate successfully",
					"Successfully validated bidPreview details" + " ", "Pass", "Y");
			log.info("completed executing the method:: validateBidPreview_BidListPage");
		} catch (Exception e) {
			log.fatal("Not able to validate bidPreview details" + e.getMessage());
			pdfResultReport.addStepDetails("validateBidPreview_BidListPage",
					"Not able to validate bidPreview details", "Unable to validate bidPreview details" + e.getMessage(),
					"Fail", "N");
		}
	}
	//Bid submission from draft bid(05/01/2021)
	public void Bid_submission_from_draftbid() throws Throwable {
		try {
			log.info("started executing the method:: Bid_submission_from_draftbid");
		//Viewing Technical tab	
			waitForObj(2000);
			waitForSpinnerToDisappearInBidSubmission();
			waitForElementToBeVisible(tendercreationlocators.technicalTabFileuploadEleBy);
		//clicking on Terms and conditions tab	
			click(tendercreationlocators.termsAndCondition_bidSubmission, "termsAndCondition_bidSubmission");
			waitForObj(2000);
			waitForSpinnerToDisappearInBidSubmission();
			waitForElementToBeVisible(tendercreationlocators.termsAndConditionsClauseBy);
		//Clicking on General info tab and modify Quot ref code	
			click(tendercreationlocators.Geninfotab_bidsubmission, "Geninfotab_bidsubmission");
			waitForObj(2000);
			waitForElementToBeVisible(tendercreationlocators.tenderId_generalInformation_bidSubmission);
			clear(tendercreationlocators.quotationReferenceCodeInput_generalInformation_bidSubmission,"quotationReferenceCodeInput_generalInformation_DraftbidSubmission");
			set(tendercreationlocators.quotationReferenceCodeInput_generalInformation_bidSubmission,
					"EditedQuotRefCode",
					"quotationReferenceCodeInput_generalInformation_DraftbidSubmission");
		//Clicking on BOQOptional tab	
			click(tendercreationlocators.BOQOptionalTab, "BOQOptionalTab");
			waitForObj(2000);
			waitForSpinnerToDisappearInBidSubmission();
			waitForElementToBeVisible(tendercreationlocators.boqOptionalAddOptionalBy);			
		//Clicking on Attachment tab
			click(tendercreationlocators.attachementsTab, "attachementsTab");
			waitForObj(2000);
			waitForSpinnerToDisappearInBidSubmission();	
			click(tendercreationlocators.attachment_subtab, "Attachment_subtab");
			waitForElementToBeVisible(tendercreationlocators.addtenderattachmenticon);			
		//Clicking on Commercial tab	
			click(tendercreationlocators.commercial_bidSubmission, "commercial_bidSubmission");
			waitForObj(2000);
			waitForSpinnerToDisappear();
			waitForElementToBeVisible(tendercreationlocators.documentChargeFieldEleBy);	
		//Clicking on BOQ Mandatory tab	
			click(tendercreationlocators.BOQmandatoryTab, "BOQmandatoryTab");
			waitForObj(2000);
			waitForSpinnerToDisappearInBidSubmission();
			waitForElementToBeVisible(tendercreationlocators.boqaddOptionItemButtonBy);
		//Clicking on save button and alert verification
			click(tendercreationlocators.savebutton, "savebutton");
			waitForObj(2000);
			waitForSpinnerToDisappearInBidSubmission();
			waitForElementToBeVisible(tendercreationlocators.alertPopUp_QRC_bidSubmission);
			IsElementPresent(tendercreationlocators.alertPopUp_QRC_bidSubmission);
			IsElementPresent(tendercreationlocators.boqOptionalAlertTab_BidSubmissionBy);
			IsElementPresent(tendercreationlocators.technicalAlertTab_bidSubmission);
			IsElementPresent(tendercreationlocators.termsAndConditionAlertTab_bidSubmission);
			IsElementPresent(tendercreationlocators.commercialAlertTab_bidSubmission);
			waitForObj(2000);
			click(tendercreationlocators.alertClose_QRC_bidSubmission, "alertClose_QRC_bidSubmission");
		//Clicking on Submit button	
			waitForObj(2000);
			click(tendercreationlocators.submitbutton, "submitbutton");
			waitForObj(2000);
			waitForSpinnerToDisappearInBidSubmission();
			waitForElementToBeVisible(tendercreationlocators.previewAllTittle);
			waitForObj(2000);
		//Preview all page validation
			validateAllInPrevieAllSubmitbidPage();
		//Submit bid and verify success message
			submitBid_link_in_previewAllPage();
			pdfResultReport.addStepDetails("Bid_submission_from_draftbid",
					"Bid submisison from draft bid should be successful ",
					"Successfully bid submitted from draft bid", "Pass", "Y");
			log.info("completed executing the method:: Bid_submission_from_draftbid");
		} catch (Exception e) {
			log.fatal("Unable to submit bid from draft bid" + e.getMessage());
			pdfResultReport.addStepDetails("Bid_submission_from_draftbid",
					"Should be able to submit bid from draft bid",
					"Unable to submit bid from draft bid"
							+ e.getMessage(),
					"Fail", "N");
                    Assert.fail("Failed Due to " + e.getMessage());
		}
	}
	//Bid no verification from draftbid (05/01/2021)
	public void VerifyBidno_from_draftbid() throws Throwable {
		try {	
		log.info("started executing the method:: VerifyBidno_from_draftbid");
		String BidnoLocatorText_afterdrfatbid= text(tendercreationlocators.BidNo_bidsubmission).trim();
		if(DraftBidnoLocatorText.equals(BidnoLocatorText_afterdrfatbid))
		{
			pdfResultReport.addStepDetails("VerifyBidno_from_draftbid",
					"Submitted bid no from draftbid should be same as drafbid no",
					"Submitted bid no from draftbid '"+BidnoLocatorText_afterdrfatbid+ "' is same as the draftbid no '"+DraftBidnoLocatorText+"'",
					"Pass", "Y");
		}
		else
		{
			pdfResultReport.addStepDetails("VerifyBidno_from_draftbid",
					"Submitted bid no from draftbid should be same as drafbid no",
					"Submitted bid no from draftbid '"+BidnoLocatorText_afterdrfatbid+ "' is different than the draftbid no '"+DraftBidnoLocatorText+"'", "Fail", "N");
		}
		log.info("Completed executing the method:: VerifyBidno_from_draftbid");
		}catch(Exception e)
		{
			log.fatal("Unable to verify bid no from draft bid " + e.getMessage());
			pdfResultReport.addStepDetails("VerifyBidno_from_draftbid",
				"Should be able to verify bid no from draftbid",
				"Unable to verify bid no from draftbid"
						+ e.getMessage(),
				"Fail", "N");
	            Assert.fail("Failed Due to " + e.getMessage());
		}
	}
	//Bid no verification for Withdraw bid(06/01/2021)
	public void VerifyBidno_withdrawbid() throws Throwable {
		try {	
		log.info("started executing the method:: VerifyBidno_withdrawbid");
		String BidnoLocatorText_withdrawbid = text(tendercreationlocators.BidNo_Withdrawbid_bidsubmission).trim();
		if(BidnoLocatorText.equals(BidnoLocatorText_withdrawbid))
		{
			pdfResultReport.addStepDetails("VerifyBidno_withdrawbid",
					"Withdrawn bid no after Withdraw should be same as submitted bid no ",
					"Withdrawn bid no after Withdraw '"+BidnoLocatorText_withdrawbid+ "' is same as submitted bid no '"+BidnoLocatorText+"'",
					"Pass", "Y");
		}
		else
		{
			pdfResultReport.addStepDetails("VerifyBidno_withdrawbid",
					"Withdrawn bid no after Withdraw should be same as submitted bid no ",
					"Withdrawn bid no after Withdraw '"+BidnoLocatorText_withdrawbid+ "' is not same as submitted bid no '"+BidnoLocatorText+"'", "Fail", "N");
		}
		log.info("Completed executing the method:: VerifyBidno_withdrawbid");
		}catch(Exception e)
		{
			log.fatal("Unable to verify bid no for withdrawn bid" + e.getMessage());
			pdfResultReport.addStepDetails("VerifyBidno_withdrawbid",
				"Should be able to verify bid no for withdrawn bid",
				"Unable to verify bid no for withdrawn bid"
						+ e.getMessage(),
				"Fail", "N");
	            Assert.fail("Failed Due to " + e.getMessage());
		}
	}
	//Verify bid submission after withdraw bid (06/01/2021)
	public void VerifyBidsubmission_after_WithdrawBid() throws Throwable {
		try {	
		log.info("started executing the method:: VerifyBidsubmission_after_WithdrawBid");
		JSClick(tendercreationlocators.bidSubmissionTransaction, "bidSubmissionTransaction");
		JSClick(tendercreationlocators.bidSubmissionTenderListing, "bidSubmissionTenderListing");
		waitForObj(5000);
		waitForElementToBeVisible(tendercreationlocators.Livetab_bidsubmission);
		set(tendercreationlocators.bidsubmissionSearchByKeyword, tenderReferenceNoLocatorText, "tenderListSearch");
		waitForObj(2000);
		click(tendercreationlocators.action, "action");
		click(tendercreationlocators.bidSubmission_Bid, "bidSubmission_Bid");
		waitForObj(3000);
		WebDriver driver = ThreadLocalWebdriver.getDriver();
		waitTillSpinnerDisable(driver, tendercreationlocators.LoadingBy);
		IsElementPresent(tendercreationlocators.Alertmsg_bidsubmission);
		pdfResultReport.addStepDetails("VerifyBidsubmission_after_WithdrawBid","Bidder should not be able to provide bid from the tender whose bid has been withdrawn once and alert message should be displaying",
				"Alert message displaying and unable to submit bid","Pass", "Y");
		click(tendercreationlocators.Alertmsg_OK_btn_bidsubmission, "Alertmsg_OK_btn_bidsubmission");
		log.info("Completed executing the method:: VerifyBidsubmission_after_WithdrawBid");
		}catch(Exception e)
		{
			log.fatal("Unable to verify bidsubmission after withdraw bid" + e.getMessage());
			pdfResultReport.addStepDetails("VerifyBidsubmission_after_WithdrawBid",
				"Should be able to verify bid submisison after withdraw bid",
				"Unable to verify bidsubmisison after withdraw bid"
						+ e.getMessage(),
				"Fail", "N");
	            Assert.fail("Failed Due to " + e.getMessage());
		}
	}
	//Click bid details section for choosen bidder in evaluator user(12/01/2020)
	public void clickBidDetailsSectionForchoosenBidder_EvaluatorUser(String BidderName) throws Throwable {
		try {
			log.info("started executing the method:: clickBidDetailsSectionForchoosenBidder_EvaluatorUser");

			click(By.xpath("//*[contains(normalize-space(text()),'" + BidderName.toUpperCase()
					+ "')]//following-sibling::td//button[@data-toggle='dropdown']"), "click Action Menu dropdown");
			waitForObj(2000);
			click(By.xpath("//*[contains(normalize-space(text()),'" + BidderName.toUpperCase()
					+ "')]//following-sibling::td//a[contains(@ng-click,'previewBidderSpecificData')]"),
					"click bidDetails section");
			//waitForElementToBeVisible(tendercreationlocators.technical);
			waitForElementTextToBeTillInVisible(tendercreationlocators.SpinnerHolderContainerIdTextBy,
					"Please Wait...");
			waitForObj(5000);
			pdfResultReport.addStepDetails("clickBidDetailsSectionForchoosenBidder",
					"BidDetails Section must be clciked successfully", "Successfully clicked BidDetails Section" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: clickBidDetailsSectionForchoosenBidder");
		} catch (Exception e) {
			log.fatal("Not able to navigate To BidDetails Section" + e.getMessage());
			pdfResultReport.addStepDetails("clickBidDetailsSectionForchoosenBidder",
					"BidDetails Section must be clicked successfully",
					"Unable to click BidDetails Section" + e.getMessage(), "Fail", "N");
		}
	}
	//Select bid opening, bid evaluation in cover2
	public void selectBidOpening_BidEval_Provide_Comments_cover2() throws Exception {
		try {
			log.info("started executing the method:: selectBidOpening_BidEval_Provide_Comments_cover2");
			
			//scrollToElement(tendercreationlocators.OpeningcommentSection_Evaluation);
			
			set(tendercreationlocators.OpeningcommentSection_Evaluation, "Sending for approval to Approval user", "OpeningcommentSection_Evaluation");
			//scrollToElement(tendercreationlocators.BidEvaluationtab_Evaluation);
			scrollToTopOfThePage();
			click(tendercreationlocators.BidEvaluationtab_Evaluation, "BidEvaluationtab_Evaluation");
			set(tendercreationlocators.EvalcommentSection_Evaluation, "Sending for approval to Approval user", "EvalcommentSection_Evaluation");

			pdfResultReport.addStepDetails("selectBidOpening_BidEval_Provide_Comments_cover2",
					"should select BidOpening,Bid evaluation and Provide Comments For both the Approval",
					"Successfully selected BidOpening,Bid evaluation and Provide Comments For both the Approval" + " ", "Pass", "Y");
			log.info("completed executing the method:: selectBidOpening_BidEval_Provide_Comments_cover2");
		} catch (Exception e) {
			log.fatal("Unable to select BidOpening,Bid evaluation and Provide Comments For both the Approval" + e.getMessage());
			pdfResultReport.addStepDetails("selectBidOpening_BidEval_Provide_Comments_cover2",
					"should select BidOpening,Bid evaluation and Provide Comments For both the Approval",
					"Unable to select BidOpening,Bid evaluation and Provide Comments For both the Approval" + e.getMessage(), "Fail",
					"N");
		}

	}
	//Negotiate the bidder (14/01/2021)
	public void clickEditableLinkPendingStatusAndSelectNegotiateForTheBidder(String bidderName, String sucessAlretMsg, 
			String negotiationType) throws Throwable {
		try {
			log.info("started executing the method:: clickEditableLinkPendingStatusAndSelectNegotiateForTheBidder");

			WebDriver driver = ThreadLocalWebdriver.getDriver();
			driver.findElement(By
					.xpath(tendercreationlocators.editpencilIconEleBy.replace("{0}", bidderName.trim().toUpperCase())))
					.click();
			waitForElementToBeClickable(tendercreationlocators.negotiateRadioButton);
			click(tendercreationlocators.negotiateRadioButton,"NegotiateBtn");
			waitForObj(2000);
			set(tendercreationlocators.upload_editable,
					System.getProperty("user.dir") + "\\MediaFiles\\rfqCreation1.xlsx", "fileName");
			waitForElementToBeClickable(tendercreationlocators.save);
			waitForObj(5000);
			click(tendercreationlocators.negotiateMandatoryRadioButton,"negotiateMandatoryRadioButton");
			set(tendercreationlocators.commentText, pdfResultReport.testData.get("overAllComment"), "commentText");
			click(tendercreationlocators.save, "save");
			waitForObj(1000);
			waitForElementToBeVisible(By.xpath("//strong[text()='" + sucessAlretMsg.trim() + "']"));
			IsElementPresent(By.xpath("//strong[text()='" + sucessAlretMsg.trim() + "']"));
			click(tendercreationlocators.alertCloseButton, "alertCloseButton");
			IsElementPresent(
				By.xpath(tendercreationlocators.NegotiatedStatus_Negotiation.replace("{0}", bidderName.trim().toUpperCase())));

			pdfResultReport.addStepDetails("clickEditableLinkPendingStatusAndSelectNegotiateForTheBidder",
				"Bidder Should be Negotiated Successfully", "Successfully Negotiated Bidder '" +bidderName+ "' ", "Pass", "Y");
			log.info("completed executing the method:: clickEditableLinkPendingStatusAndSelectNegotiateForTheBidder");
		} catch (Exception e) {
			log.fatal("Not able to Negotiate the bidder" + e.getMessage());
			pdfResultReport.addStepDetails("clickEditableLinkPendingStatusAndSelectNegotiateForTheBidder",
					"Negotiation should be successful",
					"Unable to Negotiate the bidder : '"+ bidderName + "' "
							+ e.getMessage(),
					"Fail", "N");
		}
	}
	//Bid no verification after Negotiatedbid (14/01/2021)
	public void VerifyBidno_after_Negotiatedbid() throws Throwable {
		try {	
		log.info("started executing the method:: VerifyBidno_after_Negotiatedbid");
		String BidnoLocatorText_afterNegotiation = text(tendercreationlocators.BidNo_bidsubmission).trim();
		if(BidnoLocatorText.equals(BidnoLocatorText_afterNegotiation))
		{
			pdfResultReport.addStepDetails("VerifyBidno_after_Negotiatedbid",
					"Submitted bid no after Negotiation should be different than original bid no ",
					"Submitted bid no after Negotiation '"+BidnoLocatorText_afterNegotiation+ "' is not different than original bid no '"+BidnoLocatorText+"'",
					"Fail", "N");
		}
		else
		{
			pdfResultReport.addStepDetails("VerifyBidno_after_Negotiatedbid",
					"Submitted bid no after Negotiation should be different than original bid no ",
					"Submitted bid no after Negotiation '"+BidnoLocatorText_afterNegotiation+ "' is different than original bid no '"+BidnoLocatorText+"'", "Pass", "Y");
		}
		log.info("Completed executing the method:: VerifyBidno_after_Negotiatedbid");
		}catch(Exception e)
		{
			log.fatal("Unable to verify bid no after rebid " + e.getMessage());
			pdfResultReport.addStepDetails("VerifyBidno_after_Negotiatedbid",
				"Should be able to verify bid no after Negotiated bid",
				"Unable to verify bid no after Negotiated bid"
						+ e.getMessage(),
				"Fail", "N");
	            Assert.fail("Failed Due to " + e.getMessage());
		}
	}
	//Terms and Conditions tab filling for TG1 (08/02/2021)
	public void Tender_Terms_Conditions_tabFilling_TG1() throws Throwable {
		try {			
		waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
		waitForObj(1000);
		waitForElementToBeClickable(tendercreationlocators.TermsandConditionstabLnk_Tender_TG1);
		click(tendercreationlocators.TermsandConditionstabLnk_Tender_TG1, "TermsandConditionstabLnk_Tender_TG1");
		waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
		waitForElementToBeVisible(tendercreationlocators.AddBtnTermsandConditionstab_Tender_TG1);
		click(tendercreationlocators.AddBtnTermsandConditionstab_Tender_TG1, "AddBtnTermsandConditionstab_Tender_TG1");
		waitForObj(100);
		set(tendercreationlocators.ClauseTxtTermsandConditionstab_Tender_TG1, pdfResultReport.testData.get("ClauseNo_TermsAndConditions_TG1"), "ClauseTxtTermsandConditionstab_Tender_TG1");
		set(tendercreationlocators.ClauseHeaderTxtTermsandConditionstab_Tender_TG1, pdfResultReport.testData.get("ClauseHeader_TermsAndConditions_TG1"), "ClauseHeaderTxtTermsandConditionstab_Tender_TG1");
		click(tendercreationlocators.savebutton, "savebutton");
		waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
		waitForObj(500);
		pdfResultReport.addStepDetails("Tender_Terms_Conditions_tabFilling_TG1",
				"Verify Terms & Conditions tab", "Terms & Conditions tab verified successfully", "Pass",
				"Y");
		log.info("Completed executing the method:: Tender_Terms_Conditions_tabFilling_TG1");
		}catch(Exception e)
		{
			log.fatal("Unable to verify terms and conditions tab" + e.getMessage());
			pdfResultReport.addStepDetails("Tender_Terms_Conditions_tabFilling_TG1",
				"Verify Terms & Conditions tab",
				"Unable to verify Terms & Conditions tab"
						+ e.getMessage(),
				"Fail", "N");
	            Assert.fail("Failed Due to " + e.getMessage());
		}
	}
	//providing sectionwise comment in each tab during tender approval for TG1 (12/02/2021)
	//Creating separate function specific to TG1 as tab name and tab body not in sync(proper order)
	public void TG1_tenderApprover_dynamicity() throws Exception {
		try {
			WebDriver driver = ThreadLocalWebdriver.getDriver();
			List<WebElement> TotalTabs = driver
					.findElements(By.xpath("//div[@id='rfq_headermain']//li//a[@class='ng-binding']"));

			Thread.sleep(3000);
			int i;
			for (i = 1; i <= TotalTabs.size(); i++) {
				String xpath = "(//div[@id='rfq_headermain']//li//a[@class='ng-binding'])[" + i + "]";

				JavascriptExecutor javas = (JavascriptExecutor) driver;

				WebElement tabElemnt = driver.findElement(By.xpath(xpath));

				javas.executeScript("arguments[0].click();", tabElemnt);

				waitTillSpinnerDisable(driver, tendercreationlocators.LoadingBy);

				waitForObj(3000);
				//If tab name is Payment then below code should be executed
				String text= ThreadLocalWebdriver.getDriver().findElement(By.xpath(xpath)).getText();
				if(text.trim().equalsIgnoreCase("PAYMENT"))
				{
					driver.findElement(By.xpath("//div[@id='rfqpayment']//textarea[contains(@ng-model,'template.tabWiseApproverComment')]"))
					.sendKeys("done verification for the tab section");
					waitForObj(2000);
					driver.findElement(By.xpath("//div[@id='rfqpayment']//button[contains(text(),'Save Tab Comment')]")).click();
				}else if(text.trim().equalsIgnoreCase("Project Details"))
				{
					driver.findElement(By.xpath("//div[@id='rdcis_rfq_estimtion_sheet']//textarea[contains(@ng-model,'template.tabWiseApproverComment')]"))
					.sendKeys("done verification for the tab section");
					waitForObj(2000);
					driver.findElement(By.xpath("//div[@id='rdcis_rfq_estimtion_sheet']//button[contains(text(),'Save Tab Comment')]")).click();
				}else if(text.trim().equalsIgnoreCase("Technical"))
				{
					driver.findElement(By.xpath("//div[@id='rdcis_rfq_cmpl_tech_speci']//textarea[contains(@ng-model,'template.tabWiseApproverComment')]"))
					.sendKeys("done verification for the tab section");
					waitForObj(2000);
					driver.findElement(By.xpath("//div[@id='rdcis_rfq_cmpl_tech_speci']//button[contains(text(),'Save Tab Comment')]")).click();
				}else if(text.trim().equalsIgnoreCase("RFQ Item"))
				{
					driver.findElement(By.xpath("//div[@id='rdcis_rfq_bom_supply']//textarea[contains(@ng-model,'template.tabWiseApproverComment')]"))
					.sendKeys("done verification for the tab section");
					waitForObj(2000);
					driver.findElement(By.xpath("//div[@id='rdcis_rfq_bom_supply']//button[contains(text(),'Save Tab Comment')]")).click();
				}else if(text.trim().equalsIgnoreCase("BOQ (Mandatory)"))
				{
					driver.findElement(By.xpath("//div[@id='rdcis_rfq_bom_services']//textarea[contains(@ng-model,'template.tabWiseApproverComment')]"))
					.sendKeys("done verification for the tab section");
					waitForObj(2000);
					driver.findElement(By.xpath("//div[@id='rdcis_rfq_bom_services']//button[contains(text(),'Save Tab Comment')]")).click();
				}else if(text.trim().equalsIgnoreCase("Date Schedule"))
				{
					driver.findElement(By.xpath("//div[@id='rfqdate']//textarea[contains(@ng-model,'template.tabWiseApproverComment')]"))
					.sendKeys("done verification for the tab section");
					waitForObj(2000);
					driver.findElement(By.xpath("//div[@id='rfqdate']//button[contains(text(),'Save Tab Comment')]")).click();
				}
				else
				{
					driver.findElement(By.xpath("(//textarea[@ng-model='template.tabWiseApproverComment'])["+i+"]"))
					.sendKeys("done verification for the tab section");	
					waitForObj(2000);
					driver.findElement(By.xpath("(//button[contains(text(),'Save Tab Comment')])["+ i +"]")).click();
				}
				waitTillSpinnerDisable(driver, tendercreationlocators.LoadingBy);

				waitForObj(3000);

				waitForElementToBeVisible(tendercreationlocators.alertTabOk);

				click(tendercreationlocators.alertTabOk, "alertTabOk");
				Thread.sleep(3000);

				if (i % 4 == 0) {
					driver.findElement(By.xpath("//a[@class='bx-next']")).click();
				}

				pdfResultReport.addStepDetails("TG1_tenderApprover_dynamicity", "Should give Comment for each Section",
						"Successfully given Comment" + " ", "Pass", "Y");

			}
			pdfResultReport.addStepDetails("TG1_tenderApprover_dynamicity", "Should give Comments for all Section",
					"Successfully given Comments for all tabs" + " ", "Pass", "Y");
		} catch (Exception e) {
			pdfResultReport.addStepDetails("TG1_tenderApprover_dynamicity", "Should give Comments for each Section",
					"unable to  give Comments for each Section" + " ", "Fail", "Y");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}
	//Verifying Terms and Conditions tab during RFQ publish of TG3(date: 04/03/2021)
	public void TG3_RFQ_TermsAndConditionsTab() throws Exception {
		try {
			log.info("started executing the method:: TG3_RFQ_TermsAndConditionsTab");
			
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForElementToBeClickable(tendercreationlocators.TermsandConditionstabLnk_Tender_TG3);
			click(tendercreationlocators.TermsandConditionstabLnk_Tender_TG3, "TermsandConditionstabLnk_Tender_TG3");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForElementToBeVisible(tendercreationlocators.AddBtnTermsandConditionstab_Tender_TG3);
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			click(tendercreationlocators.AddBtnTermsandConditionstab_Tender_TG3, "AddBtnTermsandConditionstab_Tender_TG3");
			set(tendercreationlocators.ClauseNoTxtTermsandConditionstab_Tender_TG3, pdfResultReport.testData.get("ClauseNoTxtTermsandConditionstab_Tender_TG3"), "ClauseNoTxtTermsandConditionstab_Tender_TG3");
			set(tendercreationlocators.ClauseTxtTermsandConditionstab_Tender_TG3, pdfResultReport.testData.get("ClauseTxtTermsandConditionstab_Tender_TG3"),
					"ClauseTxtTermsandConditionstab_Tender_TG3");
			set(tendercreationlocators.DetailsTxtTermsandConditionstab_Tender_TG3, pdfResultReport.testData.get("DetailsTxtTermsandConditionstab_Tender_TG3"), "DetailsTxtTermsandConditionstab_Tender_TG3");
			click(tendercreationlocators.savebutton, "savebutton");
			waitForObj(3000);

			pdfResultReport.addStepDetails("TG3_RFQ_TermsAndConditionsTab",
					"Should save with Terms and Conditions tab fields", "Sucessfully saved with Terms and Conditions tab fields " + " ", "Pass",
					"Y");
			log.info("completed executing the method:: TG3_RFQ_TermsAndConditionsTab");

		} catch (Exception e) {

			log.fatal("Unable to save with Terms and Conditions tab fields" + e.getMessage());
			pdfResultReport.addStepDetails("TG3_RFQ_TermsAndConditionsTab",
					"Should save with Terms and Conditions tab fields", "Unable to save with Terms and Conditions tab fields" + e.getMessage(),
					"Fail", "N");
		}
	}
	//Verifying Commercial Terms and Conditions tab during RFQ publish of TG3(date: 04/03/2021)
	public void TG3_RFQ_CommercialTermsAndConditionsTab() throws Exception {
		try {
			log.info("started executing the method:: TG3_RFQ_CommercialTermsAndConditionsTab");
			
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForElementToBeClickable(tendercreationlocators.CommercialTermsandConditionstabLnk_Tender_TG3);
			click(tendercreationlocators.CommercialTermsandConditionstabLnk_Tender_TG3, "CommercialTermsandConditionstabLnk_Tender_TG3");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForElementToBeVisible(tendercreationlocators.AddBtnCommercialTermsandConditionstab_Tender_TG3);
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			click(tendercreationlocators.AddBtnCommercialTermsandConditionstab_Tender_TG3, "AddBtnCommercialTermsandConditionstab_Tender_TG3");
			set(tendercreationlocators.ClauseNoTxtCommercialTermsandConditionstab_Tender_TG3, pdfResultReport.testData.get("ClauseNoTxtCommercialTermsandConditionstab_Tender_TG3"), "ClauseNoTxtCommercialTermsandConditionstab_Tender_TG3");
			set(tendercreationlocators.ClauseTxtCommercialTermsandConditionstab_Tender_TG3, pdfResultReport.testData.get("ClauseTxtCommercialTermsandConditionstab_Tender_TG3"),
					"ClauseTxtCommercialTermsandConditionstab_Tender_TG3");
			set(tendercreationlocators.DetailsTxtCommercialTermsandConditionstab_Tender_TG3, pdfResultReport.testData.get("DetailsTxtCommercialTermsandConditionstab_Tender_TG3"), "DetailsTxtCommercialTermsandConditionstab_Tender_TG3");
			click(tendercreationlocators.savebutton, "savebutton");
			waitForObj(3000);

			pdfResultReport.addStepDetails("TG3_RFQ_TermsAndConditionsTab",
					"Should save with Commercial Terms and Conditions tab fields", "Sucessfully saved with Commercial Terms and Conditions tab fields " + " ", "Pass",
					"Y");
			log.info("completed executing the method:: TG3_RFQ_CommercialTermsAndConditionsTab");

		} catch (Exception e) {

			log.fatal("Unable to save with Commercial Terms and Conditions tab fields" + e.getMessage());
			pdfResultReport.addStepDetails("TG3_RFQ_CommercialTermsAndConditionsTab",
					"Should save with Commercial Terms and Conditions tab fields", "Unable to save with Commercial Terms and Conditions tab fields" + e.getMessage(),
					"Fail", "N");
		}
	}
	//Verifying attachments tab during RFQ publish of TG3(Date: 04/03/2021)
	public void TG3_RFQ_attachmentsTab_Verifying() throws Exception {
		try {
			log.info("started executing the method:: TG3_RFQ_attachmentsTab_Verifying");

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForElementToBeClickable(tendercreationlocators.AttachmentstabLnk_Tender_TG3);
			click(tendercreationlocators.AttachmentstabLnk_Tender_TG3, "AttachmentstabLnk_Tender_TG3");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForElementToBeVisible(tendercreationlocators.AddBtnAttachmenttab_Tender_TG3);
			click(tendercreationlocators.AddBtnAttachmenttab_Tender_TG3, "AddBtnAttachmenttab_Tender_TG3");
			waitForElementToBeVisible(tendercreationlocators.AddAttachmentLbl_Attachments_Tender_TG3);
			set(tendercreationlocators.LblTxtAttachmenttab_Tender_TG3, pdfResultReport.testData.get("LblTxtAttachmenttab_Tender_TG3"), "label");
			set(tendercreationlocators.FileNameinputAttachmenttab_Tender_TG3, System.getProperty("user.dir") + "\\MediaFiles\\rfqCreation.xlsx",
					"FileNameinputAttachmenttab_Tender_TG3");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			click(tendercreationlocators.OKBtnAttachmenttab_Tender_TG3, "OKBtnAttachmenttab_Tender_TG3");
			click(tendercreationlocators.savebutton, "savebutton");
			waitForObj(3000);
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			pdfResultReport.addStepDetails("TG3_RFQ_attachmentsTab_Verifying",
					"Should save the Attachment tabs with fields",
					"Sucessfully save dthe Attachments tab fields " + " ", "Pass", "Y");
			log.info("completed executing the method:: TG3_RFQ_attachmentsTab_Verifying");

		} catch (Exception e) {

			log.fatal("Unable to save the Attachments tab fields" + e.getMessage());
			pdfResultReport.addStepDetails("TG3_RFQ_attachmentsTab_Verifying",
					"Should save the Attachment tabs with fields",
					"Unable to save dthe Attachments tab fields " + e.getMessage(), "Fail", "N");
		}

	}
	//Verifying Pre Bid Discussion tab during RFQ publish of TG3(Date: 04/03/2021)
	public void TG3_RFQ_PrebidDiscussionTab_Verifying() throws Exception {
		try {
			log.info("started executing the method:: TG3_RFQ_PrebidDiscussionTab_Verifying");

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForElementToBeClickable(tendercreationlocators.PreBidDiscussiontabLnk_Tender_TG3);
			click(tendercreationlocators.PreBidDiscussiontabLnk_Tender_TG3, "PreBidDiscussiontabLnk_Tender_TG3");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForElementToBeVisible(tendercreationlocators.Type_PreBidDiscussiontab_Tender_TG3);
			click(tendercreationlocators.savebutton, "savebutton");
			waitForObj(3000);
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			pdfResultReport.addStepDetails("TG3_RFQ_PrebidDiscussionTab_Verifying",
					"Should save the Pre Bid Discussion tab with fields",
					"Sucessfully save the Pre Bid Discussion tab fields " + " ", "Pass", "Y");
			log.info("completed executing the method:: TG3_RFQ_PrebidDiscussionTab_Verifying");

		} catch (Exception e) {

			log.fatal("Unable to save the Pre Bid Discussion tab fields" + e.getMessage());
			pdfResultReport.addStepDetails("TG3_RFQ_PrebidDiscussionTab_Verifying",
					"Should save the Pre Bid Discussion tab with fields",
					"Unable to save dthe Pre Bid Discussion tab fields " + e.getMessage(), "Fail", "N");
		}

	}
	//
	public void TG3_RFQ_PriceFormatTab_Verifying() throws Throwable {
		try {
			log.info(
					"Started executing the method:: TG3_RFQ_PriceFormatTab_Verifying");

			waitForElementToBeClickable(tendercreationlocators.PriceFormattabLnk_Tender_TG3);
			click(tendercreationlocators.PriceFormattabLnk_Tender_TG3, "PriceFormattabLnk_Tender_TG3");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForElementToBeVisible(tendercreationlocators.AddBtnPriceFormattab_Tender_TG3);
			waitForObj(500);
			click(tendercreationlocators.AddBtnPriceFormattab_Tender_TG3, "AddBtnPriceFormattab_Tender_TG3");
			waitForElementToBeVisible(tendercreationlocators.PRnoTxtPriceFormattab_Tender_TG3);
			set(tendercreationlocators.PRnoTxtPriceFormattab_Tender_TG3, pdfResultReport.testData.get("PRnoTxtPriceFormattab_Tender_TG3"), "PRnoTxtPriceFormattab_Tender_TG3");
			set(tendercreationlocators.PRDatePriceFormattab_Tender_TG3, getdate(10, "dd-MM-yyyy"), "PRDatePriceFormattab_Tender_TG3");
			set(tendercreationlocators.PRLineitemPriceFormattab_Tender_TG3, pdfResultReport.testData.get("PRLineitemPriceFormattab_Tender_TG3"), "PRLineitemPriceFormattab_Tender_TG3");
			set(tendercreationlocators.EPSMatCodePriceFormattab_Tender_TG3, pdfResultReport.testData.get("EPSMatCodePriceFormattab_Tender_TG3"), "EPSMatCodePriceFormattab_Tender_TG3");
			set(tendercreationlocators.SAPMatCodePriceFormattab_Tender_TG3, pdfResultReport.testData.get("SAPMatCodePriceFormattab_Tender_TG3"), "SAPMatCodePriceFormattab_Tender_TG3");
			set(tendercreationlocators.ShortDescPriceFormattab_Tender_TG3, pdfResultReport.testData.get("ShortDescPriceFormattab_Tender_TG3"), "ShortDescPriceFormattab_Tender_TG3");
			scrollToElement(tendercreationlocators.QtyPriceFormattab_Tender_TG3);
			set(tendercreationlocators.QtyPriceFormattab_Tender_TG3, pdfResultReport.testData.get("QtyPriceFormattab_Tender_TG3"), "QtyPriceFormattab_Tender_TG3");
			scrollToElement(tendercreationlocators.UOMPriceFormattab_Tender_TG3);
			select(tendercreationlocators.UOMPriceFormattab_Tender_TG3, pdfResultReport.testData.get("UOMPriceFormattab_Tender_TG3"));
			scrollToElement(tendercreationlocators.PRValPriceFormattab_Tender_TG3);
			set(tendercreationlocators.PRValPriceFormattab_Tender_TG3, pdfResultReport.testData.get("PRValPriceFormattab_Tender_TG3"), "PRValPriceFormattab_Tender_TG3");
			scrollToElement(tendercreationlocators.DeliveryDatePriceFormattab_Tender_TG3);
			set(tendercreationlocators.DeliveryDatePriceFormattab_Tender_TG3, getdate(10, "dd-MM-yyyy"), "DeliveryDatePriceFormattab_Tender_TG3");
			scrollToElement(tendercreationlocators.OrganizationPriceFormattab_Tender_TG3);
			set(tendercreationlocators.OrganizationPriceFormattab_Tender_TG3, pdfResultReport.testData.get("OrganizationPriceFormattab_Tender_TG3"), "OrganizationPriceFormattab_Tender_TG3");
			scrollToElement(tendercreationlocators.CompanyNamePriceFormattab_Tender_TG3);
			set(tendercreationlocators.CompanyNamePriceFormattab_Tender_TG3, pdfResultReport.testData.get("CompanyNamePriceFormattab_Tender_TG3"), "CompanyNamePriceFormattab_Tender_TG3");
			scrollToElement(tendercreationlocators.PlantCodePriceFormattab_Tender_TG3);
			set(tendercreationlocators.PlantCodePriceFormattab_Tender_TG3, pdfResultReport.testData.get("PlantCodePriceFormattab_Tender_TG3"), "PlantCodePriceFormattab_Tender_TG3");
			scrollToElement(tendercreationlocators.PlantNamePriceFormattab_Tender_TG3);
			set(tendercreationlocators.PlantNamePriceFormattab_Tender_TG3, pdfResultReport.testData.get("PlantNamePriceFormattab_Tender_TG3"), "PlantNamePriceFormattab_Tender_TG3");
			scrollToElement(tendercreationlocators.PurchaseGroupPriceFormattab_Tender_TG3);
			set(tendercreationlocators.PurchaseGroupPriceFormattab_Tender_TG3, pdfResultReport.testData.get("PurchaseGroupPriceFormattab_Tender_TG3"), "PurchaseGroupPriceFormattab_Tender_TG3");
			scrollToElement(tendercreationlocators.UserEmailPriceFormattab_Tender_TG3);
			set(tendercreationlocators.UserEmailPriceFormattab_Tender_TG3, pdfResultReport.testData.get("UserEmailPriceFormattab_Tender_TG3"), "UserEmailPriceFormattab_Tender_TG3");
			scrollToTopOfThePage();
			click(tendercreationlocators.UserEmailPriceFormattab_Tender_TG3, "UserEmailPriceFormattab_Tender_TG3");
			
			pdfResultReport.addStepDetails("TG3_RFQ_PriceFormatTab_Verifying ",
					"Verify RFQ Price Format tab", "RFQ Price Format tab verified successfully", "Pass",
					"Y");

			log.info("Completed executing the method:: TG3_RFQ_PriceFormatTab_Verifying");

		} catch (Exception e) {
			log.fatal("Not able to verify RFQ Price Format tab" + e.getMessage());
			pdfResultReport.addStepDetails("TG3_RFQ_PriceFormatTab_Verifying",
					"Should verify RFQ Price Format tab", "Unable to verify RFQ Price Format tab" + e.getMessage(),
					"Fail", "N");
		}
	}
	//Verifying Date Schedule (Attachments) tab during bid submission of TG3(Date: 18/03/2021)
	public void TG3_BidSubmission_DateSchedule_attachmentsTab_Verifying() throws Exception {
		try {
			log.info("started executing the method:: TG3_BidSubmission_DateSchedule_attachmentsTab_Verifying");

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForElementToBeClickable(tendercreationlocators.DateScheduletabLnk_BidSubmission_TG3);
			click(tendercreationlocators.DateScheduletabLnk_BidSubmission_TG3, "DateScheduletabLnk_BidSubmission_TG3");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForElementToBeVisible(tendercreationlocators.AttachmentSubtabLnk_BidSubmission_TG3);
			click(tendercreationlocators.AttachmentSubtabLnk_BidSubmission_TG3, "AttachmentSubtabLnk_BidSubmission_TG3");
			waitForElementToBeVisible(tendercreationlocators.AddAttachmentBtn_BidSubmission_TG3);
			click(tendercreationlocators.AddAttachmentBtn_BidSubmission_TG3, "AddAttachmentBtn_BidSubmission_TG3");
			set(tendercreationlocators.LabelTxt_AttachmentSubtab_BidSubmission_TG3, pdfResultReport.testData.get("LabelTxt_AttachmentSubtab_BidSubmission_TG3"), "LabelTxt_AttachmentSubtab_BidSubmission_TG3");
			click(tendercreationlocators.Actionbtn_AttachmentSubtab_BidSubmission_TG3, "Actionbtn_AttachmentSubtab_BidSubmission_TG3");
			set(tendercreationlocators.FileInput_AttachmentSubtab_BidSubmission_TG3, System.getProperty("user.dir") + "\\MediaFiles\\rfqCreation.xlsx",
					"FileInput_AttachmentSubtab_BidSubmission_TG3");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForElementToBeClickable(tendercreationlocators.TenderattachmentSubtabLnk_BidSubmission_TG3);
			click(tendercreationlocators.TenderattachmentSubtabLnk_BidSubmission_TG3, "TenderattachmentSubtabLnk_BidSubmission_TG3");
			waitForObj(500);
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			pdfResultReport.addStepDetails("TG3_BidSubmission_DateSchedule_attachmentsTab_Verifying",
					"Should verify Date Schedule (Attachments) tab successfully",
					"Sucessfully verified Date Schedule (Attachments) tab" + " ", "Pass", "Y");
			log.info("completed executing the method:: TG3_BidSubmission_DateSchedule_attachmentsTab_Verifying");

		} catch (Exception e) {

			log.fatal("Unable to verify the date schedule (Attachments) tab" + e.getMessage());
			pdfResultReport.addStepDetails("TG3_BidSubmission_DateSchedule_attachmentsTab_Verifying",
					"Should verify Date Schedule (Attachments) tab successfully",
					"Unable to verify Date Schedule (Attachments) tab" + e.getMessage(), "Fail", "N");
		}
	}
	//Verifying Commercial Terms and Conditions tab during bid submission of TG3(Date: 18/03/2021)
	public void TG3_BidSubmission_CommercialTermsConditionsTab_Verifying() throws Exception {
		try {
			log.info("started executing the method:: TG3_BidSubmission_CommercialTermsConditionsTab_Verifying");

			waitForElementToBeClickable(tendercreationlocators.CommercialTermsConditionstabLnk_BidSubmission_TG3);
			click(tendercreationlocators.CommercialTermsConditionstabLnk_BidSubmission_TG3, "CommercialTermsConditionstabLnk_BidSubmission_TG3");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForElementToBeVisible(tendercreationlocators.ClauseTxt_CommercialTermsConditionstab_BidSubmission_TG3);

			pdfResultReport.addStepDetails("TG3_BidSubmission_CommercialTermsConditionsTab_Verifying",
					"Should verify Commercial Terms and Conditions tab successfully",
					"Sucessfully verified Commercial Terms and Conditions tab" + " ", "Pass", "Y");
			log.info("completed executing the method:: TG3_BidSubmission_CommercialTermsConditionsTab_Verifying");

		} catch (Exception e) {

			log.fatal("Unable to verify the Commercial Terms and Conditions tab" + e.getMessage());
			pdfResultReport.addStepDetails("TG3_BidSubmission_CommercialTermsConditionsTab_Verifying",
					"Should verify Commercial Terms and Conditions tab successfully",
					"Unable to verify Commercial Terms and Conditions tab" + e.getMessage(), "Fail", "N");
		}
	}
	//Verifying Terms and Conditions tab during bid submission of TG3(Date: 18/03/2021)
	public void TG3_BidSubmission_TermsConditionsTab_Verifying() throws Exception {
		try {
			log.info("started executing the method:: TG3_BidSubmission_TermsConditionsTab_Verifying");

			waitForElementToBeClickable(tendercreationlocators.TermsConditionstabLnk_BidSubmission_TG3);
			click(tendercreationlocators.TermsConditionstabLnk_BidSubmission_TG3, "TermsConditionstabLnk_BidSubmission_TG3");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForElementToBeVisible(tendercreationlocators.ClauseTxt_TermsConditionstab_BidSubmission_TG3);

			pdfResultReport.addStepDetails("TG3_BidSubmission_TermsConditionsTab_Verifying",
					"Should verify Terms and Conditions tab successfully",
					"Sucessfully verified Terms and Conditions tab" + " ", "Pass", "Y");
			log.info("completed executing the method:: TG3_BidSubmission_TermsConditionsTab_Verifying");

		} catch (Exception e) {

			log.fatal("Unable to verify the Terms and Conditions tab" + e.getMessage());
			pdfResultReport.addStepDetails("TG3_BidSubmission_TermsConditionsTab_Verifying",
					"Should verify Terms and Conditions tab successfully",
					"Unable to verify Terms and Conditions tab" + e.getMessage(), "Fail", "N");
		}
	}
	//Verifying General Information Clauses tab during bid submission of TG3(Date: 18/03/2021)
	public void TG3_BidSubmission_GeneralInfoClausesTab_Verifying() throws Exception {
		try {
			log.info("started executing the method:: TG3_BidSubmission_GeneralInfoClausesTab_Verifying");

			waitForElementToBeClickable(tendercreationlocators.GeneralinfoClausestabLnk_BidSubmission_TG3);
			click(tendercreationlocators.GeneralinfoClausestabLnk_BidSubmission_TG3, "GeneralinfoClausestabLnk_BidSubmission_TG3");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForElementToBeVisible(tendercreationlocators.Mandatoryitemlbl_GeneralinfoClausestab_BidSubmission_TG3);
			scrollToElement(tendercreationlocators.SupplirOfferedTxt_GeneralinfoClausestab_BidSubmission_TG3);
			set(tendercreationlocators.SupplirOfferedTxt_GeneralinfoClausestab_BidSubmission_TG3, pdfResultReport.testData.get("SupplirOfferedTxt_GeneralinfoClausestab_BidSubmission_TG3"), "SupplirOfferedTxt_GeneralinfoClausestab_BidSubmission_TG3");
			scrollToElement(tendercreationlocators.HSNCodeTxt_GeneralinfoClausestab_BidSubmission_TG3);
			set(tendercreationlocators.HSNCodeTxt_GeneralinfoClausestab_BidSubmission_TG3, pdfResultReport.testData.get("HSNCodeTxt_GeneralinfoClausestab_BidSubmission_TG3"), "HSNCodeTxt_GeneralinfoClausestab_BidSubmission_TG3");
			scrollToElement(tendercreationlocators.OfferValidityTxt_GeneralinfoClausestab_BidSubmission_TG3);
			set(tendercreationlocators.OfferValidityTxt_GeneralinfoClausestab_BidSubmission_TG3, getdate(10, "dd-MM-yyyy"), "OfferValidityTxt_GeneralinfoClausestab_BidSubmission_TG3");
			scrollToElement(tendercreationlocators.OfferDeliverPeriodTxt_GeneralinfoClausestab_BidSubmission_TG3);
			set(tendercreationlocators.OfferDeliverPeriodTxt_GeneralinfoClausestab_BidSubmission_TG3, pdfResultReport.testData.get("OfferDeliverPeriodTxt_GeneralinfoClausestab_BidSubmission_TG3"), "OfferDeliverPeriodTxt_GeneralinfoClausestab_BidSubmission_TG3");
			scrollToTopOfThePage();
			pdfResultReport.addStepDetails("TG3_BidSubmission_GeneralInfoClausesTab_Verifying",
					"Should verify General Information Clauses tab successfully",
					"Sucessfully verified General Information Clauses tab" + " ", "Pass", "Y");
			log.info("completed executing the method:: TG3_BidSubmission_GeneralInfoClausesTab_Verifying");

		} catch (Exception e) {

			log.fatal("Unable to verify the General Information Clauses tab" + e.getMessage());
			pdfResultReport.addStepDetails("TG3_BidSubmission_GeneralInfoClausesTab_Verifying",
					"Should verify General Information Clauses tab successfully",
					"Unable to verify General Information Clauses tab" + e.getMessage(), "Fail", "N");
		}
	}
	//Verifying Price Format tab during bid submission of TG3(Date: 18/03/2021)
	public void TG3_BidSubmission_PriceFormatTab_Verifying() throws Exception {
		try {
			log.info("started executing the method:: TG3_BidSubmission_PriceFormatTab_Verifying");
			
			click(tendercreationlocators.Nexttab_BidSubmission_TG3, "Nexttab_BidSubmission_TG3");
			waitForElementToBeClickable(tendercreationlocators.PriceFormattabLnk_BidSubmission_TG3);
			click(tendercreationlocators.PriceFormattabLnk_BidSubmission_TG3, "PriceFormattabLnk_BidSubmission_TG3");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForElementToBeVisible(tendercreationlocators.Mandatoryitemlbl_PriceFormattab_BidSubmission_TG3);
			scrollToElement(tendercreationlocators.QuotedPriceTxt_PriceFormattab_BidSubmission_TG3);
			set(tendercreationlocators.QuotedPriceTxt_PriceFormattab_BidSubmission_TG3, pdfResultReport.testData.get("QuotedPriceTxt_PriceFormattab_BidSubmission_TG3"), "QuotedPriceTxt_PriceFormattab_BidSubmission_TG3");
			scrollToElement(tendercreationlocators.OverallDiscountTxt_PriceFormattab_BidSubmission_TG3);
			set(tendercreationlocators.OverallDiscountTxt_PriceFormattab_BidSubmission_TG3, pdfResultReport.testData.get("OverallDiscountTxt_PriceFormattab_BidSubmission_TG3"), "OverallDiscountTxt_PriceFormattab_BidSubmission_TG3");
			scrollToElement(tendercreationlocators.GSTPercentageTxt_PriceFormattab_BidSubmission_TG3);
			set(tendercreationlocators.GSTPercentageTxt_PriceFormattab_BidSubmission_TG3, pdfResultReport.testData.get("GSTPercentageTxt_PriceFormattab_BidSubmission_TG3"), "GSTPercentageTxt_PriceFormattab_BidSubmission_TG3");
			scrollToTopOfThePage();
			
			pdfResultReport.addStepDetails("TG3_BidSubmission_PriceFormatTab_Verifying",
					"Should verify Price Format tab successfully",
					"Sucessfully verified Price Format tab" + " ", "Pass", "Y");
			log.info("completed executing the method:: TG3_BidSubmission_PriceFormatTab_Verifying");

		} catch (Exception e) {

			log.fatal("Unable to verify the Price Format tab" + e.getMessage());
			pdfResultReport.addStepDetails("TG3_BidSubmission_PriceFormatTab_Verifying",
					"Should verify Price Format tab successfully",
					"Unable to verify Price Format tab" + e.getMessage(), "Fail", "N");
		}
	}
	//Verifying Price Summary tab during bid submission of TG3(Date: 18/03/2021)
	public void TG3_BidSubmission_PriceSummaryTab_Verifying() throws Exception {
		try {
			log.info("started executing the method:: TG3_BidSubmission_PriceSummaryTab_Verifying");

			waitForElementToBeClickable(tendercreationlocators.PriceSummarytabLnk_BidSubmission_TG3);
			click(tendercreationlocators.PriceSummarytabLnk_BidSubmission_TG3, "PriceSummarytabLnk_BidSubmission_TG3");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForElementToBeVisible(tendercreationlocators.IncotermsSelect_PriceSummarytab_BidSubmission_TG3);

			click(tendercreationlocators.savebutton, "savebutton");
			waitForObj(2000);
			waitForSpinnerToDisappearInBidSubmission();
			waitForElementToBeVisible(tendercreationlocators.alertPopUp_QRC_bidSubmission);
			IsElementPresent(tendercreationlocators.alertPopUp_QRC_bidSubmission);
			waitForObj(2000);
			click(tendercreationlocators.alertClose_QRC_bidSubmission, "alertClose_QRC_bidSubmission");
			waitForObj(2000);
			
			pdfResultReport.addStepDetails("TG3_BidSubmission_PriceSummaryTab_Verifying",
					"Should verify Price Summary tab successfully",
					"Sucessfully verified Price Summary tab" + " ", "Pass", "Y");
			log.info("completed executing the method:: TG3_BidSubmission_PriceSummaryTab_Verifying");

		} catch (Exception e) {

			log.fatal("Unable to verify the Price Summary tab" + e.getMessage());
			pdfResultReport.addStepDetails("TG3_BidSubmission_PriceSummaryTab_Verifying",
					"Should verify Price Summary tab successfully",
					"Unable to verify Price Summary tab" + e.getMessage(), "Fail", "N");
		}
	}
}
