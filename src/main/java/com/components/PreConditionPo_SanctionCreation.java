package com.components;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.baseClasses.BaseClass_Web;
import com.baseClasses.PDFResultReport;
import com.baseClasses.ThreadLocalWebdriver;
import com.objectRepository.TenderCreation_Locators;

public class PreConditionPo_SanctionCreation extends BaseClass_Web {

	TenderCreation_Locators tendercreationlocators = new TenderCreation_Locators();

	public PreConditionPo_SanctionCreation(PDFResultReport pdfresultReport) {
		this.pdfResultReport = pdfresultReport;
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

	public void super_Admin_Login() throws Throwable {
		try {
			log.info("started executing the method:: super_Admin_Login");
			click(tendercreationlocators.login, "login");
			set(tendercreationlocators.userName, pdfResultReport.testData.get("SuperAdminUserName"), "userName");
			waitForObj(2000);
			set(tendercreationlocators.password, pdfResultReport.testData.get("AppPassword"), "password");
			//Handle fixed Captcha (06/11/2020)
			set(tendercreationlocators.Captcha_Login, "1234", "Login_Captcha");
			click(tendercreationlocators.okButton, "okButton");

			waitForElement(tendercreationlocators.dashboardIcon, 5000);

			pdfResultReport.addStepDetails("super_Admin_Login", "Super admin user must be login sucessfully",
					"Successfully logged in as Super admin user" + " ", "Pass", "Y");
			log.info("completed executing the method:: super_Admin_Login");
		} catch (Exception e) {
			log.fatal("Unable to login as Super admin user" + e.getMessage());
			pdfResultReport.addStepDetails("super_Admin_Login", "Super admin user must be login sucessfully",
					"Unable to login as Super admin user" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickPostTenderProcessLink() throws Throwable {
		try {
			log.info("started executing the method:: clickPostTenderProcessLink");

			JSClick(tendercreationlocators.tendersIcon, "tendersIcon");
			waitForObj(2000);

			JSClick(tendercreationlocators.postTenderProcessBy, "postTenderProcessBy");

			checkPageIsReady();

			waitForElementToBeVisible(By.xpath("//*[@id='myTablebyrTl00']/tbody/tr[1]"));

			waitForObj(5000);

			pdfResultReport.addStepDetails("clickPostTenderProcessLink", "Should navigate to Complete Tender List page",
					"SucessFully navigated to Complete Tender List page" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickPostTenderProcessLink");
		} catch (Exception e) {
			log.fatal("Unable to navigate to Complete Tender List page" + e.getMessage());
			pdfResultReport.addStepDetails("clickPostTenderProcessLink", "Should navigate to Complete Tender List page",
					"Unable to navigate to Complete Tender List page" + e.getMessage(), "Fail", "N");
		}

	}

	public void enterCompleted_TenderId() throws Exception {
		try {
			log.info("started executing the method::  enterCompletedTenderId");

			clear(tendercreationlocators.tenderListKeyword, "tenderListKeyword");

			set(tendercreationlocators.tenderListKeyword, eTenderComponent.getDataFromPropertiesFile(),
					"enterCompleted_TenderId");

			waitForObj(5000);

			pdfResultReport.addStepDetails("enterCompletedTenderId",
					"Tender id should be entered in completed tender list page",
					"Sucessfully entered tender id in completed tender list page" + " ", "Pass", "Y");
			log.info("completed executing the method:: enterCompletedTenderId");
		} catch (Exception e) {
			log.fatal("Unable to entered tender id in completed tender list page" + e.getMessage());
			pdfResultReport.addStepDetails("enterCompletedTenderId",
					"Tender id should be entered in completed tender list page",
					"Unable to entered tender id in completed tender list page" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickSN_StageBtn() throws Throwable {
		try {
			log.info("started executing the method:: clickSN_StageBtn");
			String id = eTenderComponent.getDataFromPropertiesFile();
			JSClick(tendercreationlocators.SNstage(id), "SN_stage");

			checkPageIsReady();

			waitForElementToBeVisible(By.xpath("//*[@id='myTabContent']/div[2]/div/table/tbody/tr[2]"));

			waitForObj(3000);

			pdfResultReport.addStepDetails("clickSN_StageBtn",
					"Should Dispaly SanctionNote Tab in Completed Tender Details Page",
					"Sucessfully Navigate to Completed Tender Details Page" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickSN_StageBtn");
		} catch (Exception e) {
			log.fatal("Unable to Navigate to SanctionNote Tab in Completed Tender Details  Page" + e.getMessage());
			pdfResultReport.addStepDetails("clickSN_StageBtn",
					"Should Dispaly SanctionNote Tab in Completed Tender Details  Page",
					"Unable to Navigate to SanctionNote Tab in Completed Tender Details  Page" + e.getMessage(), "Fail",
					"N");
		}
	}

	public void clickSanctionNoteCreateBtn() throws Throwable {
		try {
			log.info("started executing the method:: createSanctionNote");

			JSClick(tendercreationlocators.createSanctionNote, "clickSanctionNoteCreateBtn");

			waitForElementToBeVisible(tendercreationlocators.sanctionReferenceNumber);

			waitForObj(3000);

			pdfResultReport.addStepDetails("clickSanctionNoteCreateBtn",
					"Should Dispaly SanRefNumber POP Up For Entering Ref No",
					"Sucessfully displaying SanRefNumber POP Up For Entering Ref No" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickSanctionNoteCreateBtn");
		} catch (Exception e) {
			log.fatal("Unable to Dispaly SanRefNumber POP Up For Entering Ref No" + e.getMessage());
			pdfResultReport.addStepDetails("clickSanctionNoteCreateBtn",
					"Should Dispaly SanRefNumber POP Up For Entering Ref No",
					"Unable to Dispaly SanRefNumber POP Up For Entering Ref No" + e.getMessage(), "Fail", "N");
		}
	}

	public void createsanctionReferenceNumber() throws Throwable {
		try {
			log.info("started executing the method:: sanctionReferenceNumber");
			String SN = "SN_RefNo_";

			LocalDateTime localdatetime = LocalDateTime.now();

			String currentDateTime = localdatetime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm"));

			String sanctionReference = SN.concat(currentDateTime);

			waitForObj(2000);

			set(tendercreationlocators.sanctionReferenceNumber, sanctionReference, "sanctionReferenceNumber");

			updateDataIntoPropertyFile("sanctionReferenceNumber", sanctionReference);

			click(tendercreationlocators.sanctionRefNo_Submit, "sanctionRefNo_Submit");

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			waitForElementToBeVisible(tendercreationlocators.l1RankEleBy);

			waitForObj(4000);

			pdfResultReport.addStepDetails("sanctionReferenceNumber", "Should navigate to sanctionnote creation page",
					"Succesfully navigate to sanctionnote creation page" + " ", "Pass", "Y");

			log.info("completed executing the method:: sanctionReferenceNumber");
		} catch (Exception e) {
			log.fatal("Unable to navigate to sanctionnote creation page" + e.getMessage());
			pdfResultReport.addStepDetails("sanctionReferenceNumber", "Should navigate to sanctionnote creation page",
					"Unable to navigate to sanctionnote creation page" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickApproverSupplierTab() throws Exception {
		try {
			log.info("started executing the method:: clickApproverSupplierTab");

			click(tendercreationlocators.ApproversupplierTabBy, "ApproversupplierTabBy");

			waitForElementToBeVisible(tendercreationlocators.l1RankEleBy);

			waitForObj(4000);

			pdfResultReport.addStepDetails("clickApproverSupplierTab", "Approved Bidder Must be Present",
					"Successfully dispalying Approved Bidder" + " ", "Pass", "Y");

			log.info("completed executing the method:: clickApproverSupplierTab");
		} catch (Exception e) {
			log.fatal("Unable to dispaly Approved Bidder " + e.getMessage());
			pdfResultReport.addStepDetails("clickApproverSupplierTab", "Approved Bidder Must be Present",
					"Unable to dispaly Approved Bidder " + e.getMessage(), "Fail", "N");
		}
	}

	public void clickRejectedSupplierTab() throws Exception {
		try {
			log.info("started executing the method:: clickRejectedSupplierTab");

			click(tendercreationlocators.rejectedsupplierTabBy, "rejectedsupplierTabBy");

			waitForElementToBeVisible(tendercreationlocators.rejectedsupplierBy);

			waitForObj(2000);

			pdfResultReport.addStepDetails("clickRejectedSupplierTab", "Should click reject tab",
					"Sucessfully clicked rejected tab" + " ", "Pass", "Y");

			log.info("completed executing the method:: clickRejectedSupplierTab");
		} catch (Exception e) {
			log.fatal("unable to click rejected tab" + e.getMessage());
			pdfResultReport.addStepDetails("clickRejectedSupplierTab", "Should click reject tab",
					"unable to click rejected tab" + e.getMessage(), "Fail", "N");
		}
	}

	public void checkApproverSuppliersTab(String BidderName) throws Exception {
		try {
			log.info("started executing the method:: checkApproverSuppliersTab");

			String approvedBidder = "//*[@id='approvedSupplier']/div//table/tbody/tr/td[contains(text(),'{0}')]";
			waitForObj(2000);

			IsElementPresent(By.xpath(approvedBidder.replace("{0}", BidderName)));

			pdfResultReport.addStepDetails("checkApproverSuppliersTab",
					"Approved Bidder Must be Present in ApproverSupplier Tab",
					"Successfully displaying Approved Bidder in ApproverSupplier Tab" + " ", "Pass", "Y");

			log.info("completed executing the method:: checkApproverSuppliersTab");
		} catch (Exception e) {
			log.fatal("Unable to display Approved Bidder in ApproverSupplier Tab" + e.getMessage());
			pdfResultReport.addStepDetails("checkApproverSuppliersTab",
					"Approved Bidder Must be Present in ApproverSupplier Tab",
					"Unable to display Approved Bidder in ApproverSupplier Tab" + e.getMessage(), "Fail", "N");
		}

	}

	public void checkRejectedSuppliersTab(String BidderName) throws Exception {
		try {
			log.info("started executing the method:: checkRejectedSuppliersTab");

			String approvedBidder = "//*[@id='rejectedSupplier']/div//table/tbody/tr/td[contains(text(),'{0}')]";
			waitForObj(2000);

			IsElementPresent(By.xpath(approvedBidder.replace("{0}", BidderName)));

			pdfResultReport.addStepDetails("checkRejectedSuppliersTab",
					"Rejected Bidder Must be Present in rejected tab\"",
					"Successfully displaying rejected Bidder" + " ", "Pass", "Y");

			log.info("completed executing the method:: checkRejectedSuppliersTab");
		} catch (Exception e) {
			log.fatal("Unable to display Rejected  Bidder in rejected tab " + e.getMessage());
			pdfResultReport.addStepDetails("checkRejectedSuppliersTab",
					"Rejected Bidder Must be Present in rejected tab",
					"Unable to display Rejected  Bidder in rejected tab" + e.getMessage(), "Fail", "N");
		}

	}

	public void SanctionsupplierSelection() throws Throwable {
		try {
			log.info("started executing the method:: SanctionsupplierSelection");

			JSClick(tendercreationlocators.ALLsupplierSelectionCheckBox, "supplierSelectionCheckBox");

			waitForElementToBeVisible(tendercreationlocators.Biddername);

			waitForObj(2000);

			pdfResultReport.addStepDetails("SanctionsupplierSelection", "Should Select all the Approved Suppliers",
					"Successfully selected all the Approved Suppliers  " + " ", "Pass", "Y");
			log.info("completed executing the method:: SanctionsupplierSelection");
		} catch (Exception e) {
			log.fatal("Unable to select all the Approved Suppliers  " + e.getMessage());
			pdfResultReport.addStepDetails("SanctionsupplierSelection", "Should Select all the Approved Suppliers",
					"Unable to select all the Approved Suppliers " + e.getMessage(), "Fail", "N");
		}
	}

	public void SanctionItemsAllotment() throws Throwable {
		try {
			log.info("started executing the method:: SanctionItemsAllotment");
			waitForObj(2000);
			String bidder = text(tendercreationlocators.Biddername).trim();
			if (bidder.equalsIgnoreCase("TCS")) {
				ScantionitemAllotment_TCS();
				waitForObj(3000);
				ScantionitemAllotment_CTS();
			} else if (bidder.equalsIgnoreCase("CTS")) {

				ScantionitemAllotment_CTS();
				waitForObj(3000);
				ScantionitemAllotment_TCS();
			}

			waitForObj(5000);
			pdfResultReport.addStepDetails("SanctionItemsAllotment", "supplier must be selected sucessfully",
					"Successfully selected supplier " + " ", "Pass", "Y");
			log.info("completed executing the method:: SanctionItemsAllotment");
		} catch (Exception e) {
			log.fatal("Unable to select supplier " + e.getMessage());
			pdfResultReport.addStepDetails("SanctionItemsAllotment", "supplier must be selected sucessfully",
					"Unable to select supplierr " + e.getMessage(), "Fail", "N");
		}
	}

	public void ScantionitemAllotment_TCS() throws Throwable {
		try {
			log.info("started executing the method:: ScantionitemAllotment_TCS");
			click(tendercreationlocators.plusIcon_TCS, "plusIcon_TCS");
			waitForObj(3000);
			click(tendercreationlocators.termsAndConditioncheckBox_TCS, "termsAndConditioncheckBox_TCS");
			waitForObj(1000);
			click(tendercreationlocators.boqCheckbox_TCS, "boqCheckbox_TCS");
			waitForObj(1000);
			clear(tendercreationlocators.supplierQuotedQuantity1_TCS, "supplierQuotedQuantity1_TCS");
			waitForObj(2000);
			set(tendercreationlocators.supplierQuotedQuantity1_TCS,
					getDataFromPropertiesFile("supplierQuotedQuantity1"), "supplierQuotedQuantity1_TCS");

			waitForObj(2000);
			clear(tendercreationlocators.supplierQuotedQuantity2_TCS, "supplierQuotedQuantity2_TCS");
			waitForObj(2000);
			set(tendercreationlocators.supplierQuotedQuantity2_TCS,
					getDataFromPropertiesFile("supplierQuotedQuantity2"), "supplierQuotedQuantity2_TCS");

			waitForObj(2000);
			clear(tendercreationlocators.supplierQuotedQuantity3_TCS, "supplierQuotedQuantity3_TCS");
			waitForObj(2000);
			set(tendercreationlocators.supplierQuotedQuantity3_TCS,
					getDataFromPropertiesFile("supplierQuotedQuantity3"), "supplierQuotedQuantity3_TCS");

			waitForObj(2000);

			pdfResultReport.addStepDetails("ScantionitemAllotment_TCS",
					"Should select the T&C And BOQ Mandotory and Update",
					"Successfully Selected and Updated T&C And BOQ Mandotory " + " ", "Pass", "Y");

			log.info("completed executing the method:: ScantionitemAllotment_TCS");

		} catch (Exception e) {
			log.fatal("Unable to Select and  not Updated T&C And BOQ Mandotory" + e.getMessage());
			pdfResultReport.addStepDetails("ScantionitemAllotment_TCS",
					"Should select the T&C And BOQ Mandotory and Update",
					"Unable to Select and not Updated T&C And BOQ Mandotory" + e.getMessage(), "Fail", "N");
		}
	}

	public void ScantionitemAllotment_CTS() throws Throwable {
		try {
			log.info("started executing the method:: ScantionitemAllotment_CTS");
			waitForObj(3000);
			click(tendercreationlocators.plusIcon_CTS, "plusIcon_CTS");
			waitForObj(1000);
			click(tendercreationlocators.termsAndConditioncheckBox_CTS, "termsAndConditioncheckBox_CTS");
			waitForObj(1000);
			click(tendercreationlocators.boqCheckbox_CTS, "boqCheckbox_CTS");
			waitForObj(1000);
			clear(tendercreationlocators.supplierQuotedQuantity1_CTS, "supplierQuotedQuantity1_CTS");
			waitForObj(1000);
			set(tendercreationlocators.supplierQuotedQuantity1_CTS,
					getDataFromPropertiesFile("supplierQuotedQuantity1"), "supplierQuotedQuantity1_CTS");
			waitForObj(2000);
			clear(tendercreationlocators.supplierQuotedQuantity2_CTS, "supplierQuotedQuantity2_CTS");
			waitForObj(1000);
			set(tendercreationlocators.supplierQuotedQuantity2_CTS,
					getDataFromPropertiesFile("supplierQuotedQuantity2"), "supplierQuotedQuantity2_CTS");
			waitForObj(2000);
			clear(tendercreationlocators.supplierQuotedQuantity3_CTS, "supplierQuotedQuantity3_CTS");
			waitForObj(1000);
			set(tendercreationlocators.supplierQuotedQuantity3_CTS,
					getDataFromPropertiesFile("supplierQuotedQuantity3"), "supplierQuotedQuantity3_CTS");
			waitForObj(2000);

			pdfResultReport.addStepDetails("ScantionitemAllotment_CTS",
					"Should select the T&C And BOQ Mandotory and Update",
					"Successfully selected the T&C And BOQ Mandotory and Update" + " ", "Pass", "Y");
			log.info("completed executing the method:: ScantionitemAllotment_CTS");
		} catch (Exception e) {
			log.fatal("Unable to select the T&C And BOQ Mandotory and not Updated" + e.getMessage());
			pdfResultReport.addStepDetails("ScantionitemAllotment_CTS",
					"Should select the T&C And BOQ Mandotory and Update",
					"Unable to select the T&C And BOQ Mandotory and Updated" + e.getMessage(), "Fail", "N");
		}
	}

	public static String getDataFromPropertiesFile(String Key) throws IOException {
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

		propertyValue = properties.getProperty(Key);

		fileReader.close();

		return propertyValue;

	}

	public void ScantionComment_recommendationTab() throws Throwable {
		try {
			log.info("started executing the method:: ScantionComment_recommendationTab");

			JSClick(tendercreationlocators.recommendationTab, "recommendationTab");

			JSClick(By.xpath("//*[@aria-label='Bold']"), "overallComment");

			WebElement iframele = ThreadLocalWebdriver.getDriver().findElement(By.xpath("//iframe[@id='txtArea_ifr']"));
			switchframe(iframele);

			set(tendercreationlocators.recommendationComment, "Provide Comment", "recommendationComment");

			switchToDefaultFrame();

			click(tendercreationlocators.saveSanction, "saveSanction");

			Thread.sleep(3000);

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			Thread.sleep(3000);

			pdfResultReport.addStepDetails("ScantionComment_recommendationTab",
					"Comment must be pass under recommendation tab successfully",
					"Successfully passed comment under recommendation tab" + " ", "Pass", "Y");
			log.info("completed executing the method:: ScantionComment_recommendationTab");
		} catch (Exception e) {
			log.fatal("Not able to pass comment under recommendation tab" + e.getMessage());
			pdfResultReport.addStepDetails("ScantionComment_recommendationTab",
					"Comment must be pass under recommendation tab successfully",
					"Unable to pass comment under recommendation tab" + e.getMessage(), "Fail", "N");
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

	public void clickOnSubmitButton() throws Throwable {
		try {
			log.info("started executing the method:: clickOnSubmitButton");
			JSClick(tendercreationlocators.submit, "submit");

			waitForElementToBeVisible(tendercreationlocators.sendForApprovalNotRequired_SN);

			waitForObj(3000);

			pdfResultReport.addStepDetails("clickOnSubmitButton", "should display Send for approval pop up",
					"Successfully displaying Send for approval pop up" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickOnSubmitButton");
		} catch (Exception e) {
			log.fatal("Unable to display Send for approval pop up" + e.getMessage());
			pdfResultReport.addStepDetails("clickOnSubmitButton", "should display Send for approval pop up",
					"Unable to display Send for approval pop up" + e.getMessage(), "Fail", "N");
		}
	}

	String documentNumberText = null;

	public String documentNoSave() throws Throwable {
		log.info("started executing the method:: documentNoSave");
		documentNumberText = text(tendercreationlocators.documentNumber).trim();

		System.out.println(documentNumberText);

		updateDataIntoPropertyFile("sanctionoteDocnum", documentNumberText);

		return documentNumberText;
	}

	public void sendForApproval() throws Throwable {
		try {
			log.info("started executing the method:: sendForApproval");

			JSClick(tendercreationlocators.sendForApprovalNotRequired_SN, "sendForApprovalNotRequired");

			waitForObj(2000);

			JSClick(tendercreationlocators.sendForApprovalSubmit, "sendForApprovalSubmit");

			WebDriverWait wait = new WebDriverWait(ThreadLocalWebdriver.getDriver(), 200);
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//*[@id='myTabContent']/child::div/child::div/table/tbody/tr[2]")));

			waitForObj(5000);

			pdfResultReport.addStepDetails("sendForApproval", "Should navigate to Completed Tender Details Page",
					"Successfully navigated to Completed Tender Details Page" + " ", "Pass", "Y");
			log.info("completed executing the method:: sendForApproval");
		} catch (Exception e) {
			log.fatal("Unable to  navigate to Completed Tender Details Page" + e.getMessage());
			pdfResultReport.addStepDetails("sendForApproval", "Should navigate to Completed Tender Details Page",
					"Unable to  navigate to Completed Tender Details Page" + e.getMessage(), "Fail", "N");
		}
	}

	public void enterDocumentNoInSearch() throws Throwable {
		try {
			log.info("started executing the method:: enterDocumentNoInSearch");
			waitForElement(tendercreationlocators.typeAnyKeyword, 30);
			waitForObj(5000);
			clear(tendercreationlocators.typeAnyKeyword, "typeAnyKeyword");
			set(tendercreationlocators.typeAnyKeyword, eTenderComponent.getDataFromPropertiesFile("sanctionoteDocnum"),
					"typeAnyKeyword");
			waitForObj(5000);
			pdfResultReport.addStepDetails("enterDocumentNoInSearch", "Document No must be enter successfully",
					"Successfully entered document No" + " ", "Pass", "Y");
			log.info("completed executing the method:: enterDocumentNoInSearch");
		} catch (Exception e) {
			log.fatal("Not able to enter document No" + e.getMessage());
			pdfResultReport.addStepDetails("enterDocumentNoInSearch", "Document No must be enter successfully",
					"Unable to eneter document No" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickSanctionNoteSummaryTab() throws Throwable {
		try {
			log.info("started executing the method:: clickSanctionNoteSummaryTab");

			click(tendercreationlocators.sanctionNoteSummaryTabBy, "sanctionNoteSummaryTabBy");

			waitForElementToBeVisible(tendercreationlocators.SummaryTabCtsBy);
			waitForObj(3000);

			pdfResultReport.addStepDetails("clickSanctionNoteSummaryTab", "Should click the Summary tab ",
					"Successfully  clicked the Summary tab" + " ", "Pass", "Y");

			log.info("completed executing the method:: clickSanctionNoteSummaryTab");
		} catch (Exception e) {
			log.fatal("Unable to  click the Summary tab" + e.getMessage());
			pdfResultReport.addStepDetails("clickSanctionNoteSummaryTab", "Should click the Summary tab",
					"Unable to  click the Summary tab" + e.getMessage(), "Fail", "N");
		}
	}

	public void veiwSummaryDetails() throws Exception {
		try {
			log.info("started executing the method:: veiwSummaryDetails");

			click(tendercreationlocators.SummaryTabCtsBy, "SummaryTabCtsBy");

			waitForElementToBeVisible(By.xpath("(//*[@id='collapse_0']//div[text()='Terms and Conditions'])[2]"));

			scrollToElement(By.xpath("(//*[@id='collapse_0']//div[text()='Terms and Conditions'])[2]"));

			waitForObj(2000);
			scrollToElement(By.xpath("(//*[@id='collapse_0']//div[text()='BOQ (Mandatory)'])[2]"));

			waitForObj(2000);

			click(tendercreationlocators.SummaryTabTcsBy, "SummaryTabCtsBy");

			scrollToElement(By.xpath("(//*[@id='collapse_1']//div[text()='Terms and Conditions'])[2]"));

			waitForObj(2000);

			scrollToElement(By.xpath("(//*[@id='collapse_1']//div[text()='BOQ (Mandatory)'])[2]"));

			waitForObj(2000);

			scrollToTopOfThePage();

			waitForObj(5000);

			pdfResultReport.addStepDetails("veiwSummaryDetails", "Should display the details of Supplier Bidders ",
					"Successfully displaying the details of Supplier Bidders" + " ", "Pass", "Y");

			log.info("completed executing the method:: veiwSummaryDetails");
		} catch (Exception e) {
			log.fatal("Unable to display the details of Supplier Bidders" + e.getMessage());
			pdfResultReport.addStepDetails("veiwSummaryDetails", "Should dispaly the details of Supplier Bidders",
					"Unable to display the details of Supplier Bidders" + e.getMessage(), "Fail", "N");
		}

	}

	public void saveSanctionDetails() throws Exception {
		try {
			log.info("started executing the method:: saveSanctionDetails");

			click(tendercreationlocators.saveSanction, "saveSanction");

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			Thread.sleep(5000);

			pdfResultReport.addStepDetails("saveSanctionDetails", "Should save the updated details",
					"Successfully saved the updated details" + " ", "Pass", "Y");

			log.info("completed executing the method:: saveSanctionDetails");
		} catch (Exception e) {
			log.fatal("Unable tod save the updated details" + e.getMessage());
			pdfResultReport.addStepDetails("saveSanctionDetails", "Should save the updated details",
					"Unable tod save the updated details" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickPrintPreviewTab() throws Exception {
		try {
			log.info("started executing the method:: clickPrintPreviewTab");

			click(tendercreationlocators.sanctionNotePrintViewTabBy, "sanctionNotePrintViewTabBy");

			Thread.sleep(3000);

			pdfResultReport.addStepDetails("clickSanctionNoteSummaryTab", "Should click print view tab",
					"Successfully clicked print view tab" + " ", "Pass", "Y");

			log.info("completed executing the method:: clickPrintPreviewTab");
		} catch (Exception e) {
			log.fatal("Unable to click print view tab" + e.getMessage());
			pdfResultReport.addStepDetails("clickPrintPreviewTab", "Should click print view tab",
					"Unable to click print view tab" + e.getMessage(), "Fail", "N");
		}

	}

	public void viewPrintTabDetails() throws Exception {
		try {
			log.info("started executing the method:: viewPrintTabDetails");

			waitForElementToBeVisible(By.xpath("(//*[@id='collapse_0']//div[text()='Terms and Conditions'])[3]"));

			scrollToElement(By.xpath("(//*[@id='collapse_0']//div[text()='Terms and Conditions'])[3]"));

			waitForObj(2000);
			scrollToElement(By.xpath("(//*[@id='collapse_0']//div[text()='BOQ (Mandatory)'])[3]"));

			waitForObj(2000);

			scrollToElement(By.xpath("(//*[@id='collapse_1']//div[text()='Terms and Conditions'])[3]"));

			waitForObj(2000);

			scrollToElement(By.xpath("(//*[@id='collapse_1']//div[text()='BOQ (Mandatory)'])[3]"));

			waitForObj(2000);

			scrollToTopOfThePage();

			waitForObj(5000);

			pdfResultReport.addStepDetails("viewPrintTabDetails",
					"Should display in print preview mode all the Bidder details",
					"Successfully displaying in print preview mode all the Bidder details" + " ", "Pass", "Y");

			log.info("completed executing the method:: viewPrintTabDetails");
		} catch (Exception e) {
			log.fatal("Unable to display in print preview mode all the Bidder details" + e.getMessage());
			pdfResultReport.addStepDetails("viewPrintTabDetails",
					"Should display in print preview mode all the Bidder details",
					"Unable to display in print preview mode all the Bidder details" + e.getMessage(), "Fail", "N");
		}
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

	public void verifyStageForSanctionNoteCreated() throws Exception {
		try {
			log.info("started executing the method:: verifyStageForSanctionNoteCreated");

			IsElementPresent(tendercreationlocators.sanctionAcceptedStageEleBy);

			pdfResultReport.addStepDetails("verifyStageForSanctionNoteCreated", "Should be in completed/Accepted Stage",
					"Successfully displaying completed/Accepted Stage" + " ", "Pass", "Y");

			log.info("completed executing the method:: verifyStageForSanctionNoteCreated");
		} catch (Exception e) {
			log.fatal("Unable to display completed/Accepted Stage" + e.getMessage());
			pdfResultReport.addStepDetails("verifyStageForSanctionNoteCreated", "Should be in completed/Accepted Stage",
					"Unable to display completed/Accepted Stage" + e.getMessage(), "Fail", "N");
		}
	}
}
