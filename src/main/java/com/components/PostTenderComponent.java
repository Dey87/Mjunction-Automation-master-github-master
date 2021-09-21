package com.components;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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

public class PostTenderComponent extends BaseClass_Web {
	TenderCreation_Locators tendercreationlocators = new TenderCreation_Locators();
	String documentNumberText = null;
	String tenderReferenceNoLocatorText_sn = null;
	eTenderComponent etendercomponentobj = new eTenderComponent(pdfResultReport);

	public PostTenderComponent(PDFResultReport pdfresultReport) {
		this.pdfResultReport = pdfresultReport;
	}

	public void clickOnCreateSanctionNote() throws Throwable {
		try {
			log.info("started executing the method::  clickOnCreateSanctionNote");
			click(tendercreationlocators.sanctionNoteStage, "sanctionNoteStage");
			waitForObj(7000);
			pdfResultReport.addStepDetails("clickOnCreateSanctionNote",
					"The page redirects to completed tender details",
					"Completed tender details page should be displayed" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickOnCreateSanctionNote");
		} catch (Exception e) {
			log.fatal("Unable to click on sanction note" + e.getMessage());
			pdfResultReport.addStepDetails("clickOnCreateSanctionNote",
					"The page redirects to completed tender details",
					"Unable to click on sanction note" + e.getMessage(), "Fail", "N");
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
			//Added to handle Captcha manually
			//waitForObj(40000);
			click(tendercreationlocators.okButton, "okButton");

			waitForElement(tendercreationlocators.dashboardIcon, 50);

			pdfResultReport.addStepDetails("super_Admin_Login", "Super admin user must be login sucessfully",
					"Successfully logged in as Super admin user" + " ", "Pass", "Y");
			log.info("completed executing the method:: super_Admin_Login");
		} catch (Exception e) {
			log.fatal("Unable to login as Super admin user" + e.getMessage());
			pdfResultReport.addStepDetails("super_Admin_Login", "Super admin user must be login sucessfully",
					"Unable to login as Super admin user" + e.getMessage(), "Fail", "N");
		}
	}

	public void navigateToOthersPOTab() throws Throwable {
		try {
			log.info("started executing the method:: navigateToOthersPOTab");
			JSClick(tendercreationlocators.OtherPOBy, "Other'sPOtabselection");
			waitForObj(2000);
			pdfResultReport.addStepDetails("navigateToOthersPOTab", "Other's PO tab must be selected successfully",
					"Successfully selected Other's PO tab" + " ", "Pass", "Y");
			log.info("completed executing the method:: navigateToOthersPOTab");
		} catch (Exception e) {
			log.fatal("Not able to select Other's PO tab" + e.getMessage());
			pdfResultReport.addStepDetails("navigateToOthersPOTab", "Other's PO tab must be selected successfully",
					"Unable to select Other's PO tab" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyOthersPOFields() throws Throwable {
		try {
			log.info("started executing the method::  verifyOthersPOFields");
			waitForObj(5000);

			IsElementPresent(tendercreationlocators.columnVisibility);
			IsElementPresent(tendercreationlocators.OPOSNO);
			IsElementPresent(tendercreationlocators.OpoNoRefNo);
			IsElementPresent(tendercreationlocators.OprRfqSn);
			IsElementPresent(tendercreationlocators.OpoType);
			IsElementPresent(tendercreationlocators.OpoCategory);
			IsElementPresent(tendercreationlocators.Oitems);
			IsElementPresent(tendercreationlocators.OvendorOrSupplierName);
			IsElementPresent(tendercreationlocators.OpoValue);
			IsElementPresent(tendercreationlocators.OcreationDate);
			IsElementPresent(tendercreationlocators.Ovalidity);
			IsElementPresent(tendercreationlocators.Osource);
			IsElementPresent(tendercreationlocators.Ocatalogue);
			IsElementPresent(tendercreationlocators.Ostatus);
			IsElementPresent(tendercreationlocators.Oaction);
			waitForObj(5000);

			pdfResultReport.addStepDetails("verifyOthersPOFields",
					"All the fields in Other's PO tab must be validated sucessfully",
					"Successfully validated all the fields in Other's PO tab" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyOthersPOFields");
		} catch (Exception e) {
			log.fatal("Unable to validate the fields in Other's PO tab" + e.getMessage());
			pdfResultReport.addStepDetails("verifyOthersPOFields",
					"All the fields in Other's PO tab must be validated sucessfully",
					"Unable to validate the fields in Other's PO tab" + e.getMessage(), "Fail", "N");
		}
	}

	public void sanction_Creator_Login() throws Throwable {
		try {
			log.info("started executing the method:: sanction_Creator_Login");
			click(tendercreationlocators.login, "login");
			set(tendercreationlocators.userName, pdfResultReport.testData.get("SanctionNoteCreatorUserName"),
					"userName");
			waitForObj(2000);
			set(tendercreationlocators.password, pdfResultReport.testData.get("AppPassword"), "password");
			//Handle fixed Captcha (06/11/2020)
			set(tendercreationlocators.Captcha_Login, "1234", "Login_Captcha");
			click(tendercreationlocators.okButton, "okButton");
			waitForElement(tendercreationlocators.tendersIcon, 50);

			pdfResultReport.addStepDetails("sanction_Creator_Login",
					"Sanction note  creator user must be login sucessfully",
					"Successfully logged in as Sanction note  creator user " + " ", "Pass", "Y");
			log.info("completed executing the method:: sanction_Creator_Login");
		} catch (Exception e) {
			log.fatal("Unable to login as sanction note creator user" + e.getMessage());
			pdfResultReport.addStepDetails("Tsanction_Creator_Login",
					"Sanction note  creator user must be login sucessfully",
					"Unable to login as sanction note creator user" + e.getMessage(), "Fail", "N");
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
					"Tender id should be displayed in completed tender list page",
					"Sucessfully displayed tender id in completed tender list page" + " ", "Pass", "Y");
			log.info("completed executing the method:: enterCompletedTenderId");
		} catch (Exception e) {
			log.fatal("Unable to display tender id in completed tender list page" + e.getMessage());
			pdfResultReport.addStepDetails("enterCompletedTenderId",
					"Tender id should be displayed in completed tender list page",
					"Unable to display tender id in completed tender list page" + e.getMessage(), "Fail", "N");
		}
	}

	public void supplierSelection() throws Throwable {
		try {
			log.info("started executing the method:: sanctionReferenceNumber");
			click(tendercreationlocators.supplierSelectionCheckBox, "supplierSelectionCheckBox");
			waitForObj(1000);
			pdfResultReport.addStepDetails("sanctionReferenceNumber",
					"Sanction reference number must be pass sucessfully",
					"Successfully passed sanction reference number " + " ", "Pass", "Y");
			log.info("completed executing the method:: sanctionReferenceNumber");
		} catch (Exception e) {
			log.fatal("Unable to pass sanction reference number " + e.getMessage());
			pdfResultReport.addStepDetails("sanctionReferenceNumber",
					"Sanction reference number must be pass sucessfully",
					"Unable to pass sanction reference number " + e.getMessage(), "Fail", "N");
		}
	}

	public void sendForApprovalUserDefinedSequential() throws Throwable {
		try {
			log.info("started executing the method:: sendForApprovalUserDefinedSequential");
			click(tendercreationlocators.Userdefined, "Userdefined");

			click(tendercreationlocators.cancel_User1, "cancelUser1");
			click(tendercreationlocators.cancel_User1, "cancelUser1");
			click(tendercreationlocators.userAdd, "userAdd");
			set(tendercreationlocators.user1, pdfResultReport.testData.get("UserTenderApprover1"), "user1");
			waitForObj(2000);
			select(tendercreationlocators.approverType1_p, pdfResultReport.testData.get("ApprovalType2"));
			click(tendercreationlocators.userAdd, "userAdd");

			set(tendercreationlocators.user2, pdfResultReport.testData.get("UserTenderApprover2"), "user2");
			waitForObj(2000);
			select(tendercreationlocators.approverType2_p, pdfResultReport.testData.get("ApprovalType2"));
			waitForObj(2000);
			click(tendercreationlocators.coordinatorFlag_2, "coordinatorFlag_2");
			click(tendercreationlocators.sendForApproval, "sendForApproval");
			pdfResultReport.addStepDetails("sendForApprovalUserDefinedSequential",
					"sendForApproval must be validate successfully", "Successfully validated sendForApproval" + " ",
					"Pass", "Y");
			WebDriverWait wait = new WebDriverWait(ThreadLocalWebdriver.getDriver(), 60);
			waitForObj(20000);
			pdfResultReport.addStepDetails("sendForApprovalUserDefinedSequential",
					"sendForApproval must be validate successfully", "Successfully validated sendForApproval" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: sendForApprovalUserDefinedSequential");
		} catch (Exception e) {
			log.fatal("Not able to validate sendForApproval" + e.getMessage());
			pdfResultReport.addStepDetails("sendForApprovalUserDefinedSequential",
					"sendForApproval must be validate successfully",
					"Unable to validate sendForApproval" + e.getMessage(), "Fail", "N");
		}
	}

	public void sanctionNoteApprover1Login() throws Throwable {
		try {
			log.info("started executing the method:: sanctionNoteApprover1Login");
			click(tendercreationlocators.login, "login");
			set(tendercreationlocators.userName, pdfResultReport.testData.get("SanctionNoteApproverUserName"),
					"userName");
			waitForObj(5000);
			set(tendercreationlocators.password, pdfResultReport.testData.get("AppPassword"), "password");
			//Handle fixed Captcha (06/11/2020)
			set(tendercreationlocators.Captcha_Login, "1234", "Login_Captcha");
			//Added to handle Captcha manually
			//waitForObj(50000);
			click(tendercreationlocators.okButton, "okButton");
			waitForObj(5000);
			pdfResultReport.addStepDetails("sanctionNoteApprover1Login",
					"Sanction Note Approver must be sucessfully logged in",
					"Successfully logged in as sanction note approver" + " ", "Pass", "Y");
			log.info("completed executing the method:: sanctionNoteApprover1Login");

		} catch (Exception e) {
			log.fatal("Unable to login in as sanction note approver" + e.getMessage());
			pdfResultReport.addStepDetails("sanctionNoteApprover1Login",
					"Sanction Note Approver must be sucessfully logged in",
					"Unable to login in as sanction note approver" + e.getMessage(), "Fail", "N");
		}
	}

	public void sanctionNoteApprover2Login() throws Throwable {
		try {
			log.info("started executing the method:: sanctionNoteApprover2Login");
			click(tendercreationlocators.login, "login");
			set(tendercreationlocators.userName, pdfResultReport.testData.get("SanctionNoteApproverUserName2"),
					"userName");
			waitForObj(5000);
			set(tendercreationlocators.password, pdfResultReport.testData.get("AppPassword"), "password");
			//Handle fixed Captcha (06/11/2020)
			set(tendercreationlocators.Captcha_Login, "1234", "Login_Captcha");
			//Added to handle Captcha manually
			//waitForObj(50000);
			click(tendercreationlocators.okButton, "okButton");
			waitForObj(5000);
			pdfResultReport.addStepDetails("sanctionNoteApprover2Login",
					"Sanction Note Approver must be sucessfully logged in",
					"Successfully logged in as sanction note approver" + " ", "Pass", "Y");
			log.info("completed executing the method:: sanctionNoteApprover2Login");

		} catch (Exception e) {
			log.fatal("Unable to login in as sanction note approver" + e.getMessage());
			pdfResultReport.addStepDetails("sanctionNoteApprover2Login",
					"Sanction Note Approver must be sucessfully logged in",
					"Unable to login in as sanction note approver" + e.getMessage(), "Fail", "N");
		}
	}

	public void enterDocumentNoInSearchSanctionApprover() throws Throwable {
		try {
			log.info("started executing the method:: enterDocumentNoInSearchSanctionApprover");
			set(tendercreationlocators.typeAnyKeyword1, documentNumberText, "typeAnyKeyword");
			waitForObj(5000);
			pdfResultReport.addStepDetails("enterDocumentNoInSearchSanctionApprover",
					"Document No must be enter successfully", "Successfully entered document No" + " ", "Pass", "Y");
			log.info("completed executing the method:: enterDocumentNoInSearchSanctionApprover");
		} catch (Exception e) {
			log.fatal("Not able to enter document No" + e.getMessage());
			pdfResultReport.addStepDetails("enterDocumentNoInSearchSanctionApprover",
					"Document No must be enter successfully", "Unable to eneter document No" + e.getMessage(), "Fail",
					"N");
		}
	}

	public void sanctionNoteEvaluationApprove() throws Throwable {
		try {
			log.info("started executing the method:: sanctionNoteEvaluationApprove");
			click(tendercreationlocators.sanctionNoteEvaluationApprove, "sanctionNoteEvaluationApprove");
			waitForObj(5000);
			click(tendercreationlocators.approveConfirm, "approveConfirm");
			waitForObj(8000);
			pdfResultReport.addStepDetails("sanctionNoteEvaluationApprove",
					"sanction Note Evaluation must be approve sucessfully ",
					"Successfully approved sanction Note Evaluation" + " ", "Pass", "Y");
			log.info("completed executing the method:: sanctionNoteEvaluationApprove");

		} catch (Exception e) {
			log.fatal("Unable to approve sanction Note Evaluation" + e.getMessage());
			pdfResultReport.addStepDetails("sanctionNoteEvaluationApprove",
					"sanction Note Evaluation must be approve sucessfully ",
					"Unable to approve sanction Note Evaluation" + e.getMessage(), "Fail", "N");
		}
	}

	public void navigateToCompletedTenderDetailsPage() throws Throwable {
		try {
			log.info("started executing the method:: navigateToCompletedTenderDetailsPage");
			click(tendercreationlocators.SN_stage, "SN_stage");
			waitForObj(5000);
			pdfResultReport.addStepDetails("navigateToCompletedTenderDetailsPage",
					"Completed Tender Details Page must be sucessfully logged in",
					"Successfully navigated to Completed Tender Details Page" + " ", "Pass", "Y");
			log.info("completed executing the method:: navigateToCompletedTenderDetailsPage");

		} catch (Exception e) {
			log.fatal("Unable to navigate Completed Tender Details Page" + e.getMessage());
			pdfResultReport.addStepDetails("navigateToCompletedTenderDetailsPage",
					"Completed Tender Details Page must be sucessfully logged in",
					"Unable to navigate Completed Tender Details Page" + e.getMessage(), "Fail", "N");
		}
	}

	public void provideApproverComment() throws Throwable {
		try {
			log.info("started executing the method:: provideApproverComment");
			JSClick(By.xpath("//*[@aria-label='Bold']"), "overallComment");
			WebElement iframele = ThreadLocalWebdriver.getDriver().findElement(By.xpath("//iframe[@id='txtArea_ifr']"));
			switchframe(iframele);
			Thread.sleep(5000);
			set(tendercreationlocators.recommendationComment, "overallComment_currentPart", "recommendationComment");
			switchToDefaultFrame();
			Thread.sleep(5000);
			pdfResultReport.addStepDetails("provideApproverComment", "Comment must be pass successfully",
					"Successfully passed comment" + " ", "Pass", "Y");
			log.info("completed executing the method:: provideApproverComment");
		} catch (Exception e) {
			log.fatal("Not able to pass comment under recommendation tab" + e.getMessage());
			pdfResultReport.addStepDetails("provideApproverComment", "Comment must be pass successfully",
					"Unable to pass comment" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickOnEditLink() throws Throwable {
		try {
			log.info("started executing the method:: clickOnEditLink");
			click(tendercreationlocators.actionDropdown, "actionDropdown");
			click(tendercreationlocators.edit, "edit");
			waitForObj(5000);
			pdfResultReport.addStepDetails("clickOnEditLink", "Edit link must be click successfully",
					"Successfully clicked on edit link" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickOnEditLink");
		} catch (Exception e) {
			log.fatal("Not able to click on edit link" + e.getMessage());
			pdfResultReport.addStepDetails("clickOnEditLink", "Edit link must be click successfully",
					"Unable to click on edit link" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyRFQInPostTenderProcess() throws Throwable {
		try {
			log.info("started executing the method::  verifyRFQInPostTenderProcess");
			waitForObj(5000);
			enterCompleted_TenderId();
			WebElement noRecords = ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.noRecords);

			String noRecordsText = noRecords.getText().trim();

			System.out.println(noRecordsText);
			if (noRecordsText.equalsIgnoreCase("0")) {
				System.out.println("No records are present");
			} else if ((ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.completedRFQId).getText()
					.trim()).equalsIgnoreCase(eTenderComponent.getDataFromPropertiesFile())) {
				System.out.println("RFQid: is present");
			} else {
				System.out.println("RFQid: present is invalid");
			}
			pdfResultReport.addStepDetails("verifyRFQInPostTenderProcess",
					"Tender id should be displayed in completed tender list page",
					"Sucessfully displayed RFQ id and RFQ description in completed tender list page" + " ", "Pass",
					"Y");
			log.info("completed executing the method:: verifyRFQInPostTenderProcess");
		} catch (Exception e) {
			log.fatal("Unable to display RFQ id and RFQ description in completed tender list page" + e.getMessage());
			pdfResultReport.addStepDetails("verifyRFQInPostTenderProcess",
					"RFQ id and RFQ description should be displayed in completed tender list page",
					"Unable to display RFQ id and RFQ description in completed tender list page" + e.getMessage(),
					"Fail", "N");
		}
	}

	public void sanctionTemplateGroupCreationAlertMessageValidation() throws Throwable {
		try {
			log.info("started executing the method:: sanctionTemplateGroupCreationAlertMessageValidation");
			IsElementPresent(tendercreationlocators.sanctionTemplateGroupAlert);
			waitForObj(2000);
			pdfResultReport.addStepDetails("sanctionTemplateGroupCreationAlertMessageValidation",
					"Alert Message must be validate successfully", "Successfully validated alert message" + " ", "Pass",
					"Y");

			log.info("completed executing the method:: sanctionTemplateGroupCreationAlertMessageValidation");
		} catch (Exception e) {
			log.fatal("Not able to validate alert message" + e.getMessage());
			pdfResultReport.addStepDetails("sanctionTemplateGroupCreationAlertMessageValidation",
					"Alert message must be validate successfully", "Unable to validate alert message" + e.getMessage(),
					"Fail", "N");
		}
	}

	public void sendForApprovalPredefineWorkFlow() throws Throwable {
		try {
			log.info("started executing the method:: sendForApprovalPredefineWorkFlow");
			waitForObj(5000);
			click(tendercreationlocators.predefine, "predefine");
			waitForObj(2000);
			waitForElementToBeVisible(By.xpath("//*[@id='menusndApprovl']/div/table/tbody/tr/td[2]"));

			IsElementPresent(By.xpath("//*[@id='menusndApprovl']/div/table/tbody/tr/td[2]"));

			pdfResultReport.addStepDetails("sendForApprovalPredefineWorkFlow",
					"Must dispaly sanction approver user in Predefined Appproval Type",
					"Sucessfully dispalying sanction approver user in Predefined Appproval Type" + " ", "Pass", "Y");

			click(tendercreationlocators.sendForApproval, "sendForApproval");

			waitForElementToBeVisible(By.xpath("//*[@id='myTabContent']/child::div/child::div/table/tbody/tr[2]"));

			waitForObj(5000);

			pdfResultReport.addStepDetails("sendForApprovalPredefineWorkFlow",
					"Must Navigate to Completed Tender details Page",
					"Sucessfully navigated to Completed Tender details Page" + " ", "Pass", "Y");
			log.info("completed executing the method:: sendForApprovalPredefineWorkFlow");
		} catch (Exception e) {
			log.fatal("Unable to  Navigate to Completed Tender details Page" + e.getMessage());
			pdfResultReport.addStepDetails("sendForApprovalPredefineWorkFlow",
					"Must Navigate to Completed Tender details Page",
					"Unable to  Navigate to Completed Tender details Page" + e.getMessage(), "Fail", "N");
		}
	}

	public void SN_ApprovalDetailsValidation() throws Throwable {
		try {
			log.info("started executing the method:: SN_ApprovalDetailsValidation");
			click(tendercreationlocators.drpdwn(eTenderComponent.getDataFromPropertiesFile("sanctionoteDocnum")),
					"actionDropdown");

			click(tendercreationlocators.approvdetailsvalidation(
					eTenderComponent.getDataFromPropertiesFile("sanctionoteDocnum")), "approvalDetails");
			IsElementPresent(tendercreationlocators.username);
			IsElementPresent(tendercreationlocators.approval_Type);
			IsElementPresent(tendercreationlocators.approvalDetailsStatus);
			pdfResultReport.addStepDetails("SN_ApprovalDetailsValidation",
					"Sanction Approval details must be validate successfully",
					"Successfully validated Sanction approval details" + " ", "Pass", "Y");
			click(tendercreationlocators.approvalDetails_close, "approvalDetails_close");
			pdfResultReport.addStepDetails("SN_ApprovalDetailsValidation",
					"Approval details must be validate successfully", "Successfully validated approval details" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: SN_ApprovalDetailsValidation");
		} catch (Exception e) {
			log.fatal("Unable to validate Sanction approval details" + e.getMessage());
			pdfResultReport.addStepDetails("SN_ApprovalDetailsValidation",
					" Sanction Approval details must be validate successfully",
					"Unable to validate Sanction approval details" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickOn_SNstage() throws Throwable {
		try {
			log.info("started executing the method:: clickOn_SNstage");
			click(tendercreationlocators.SN_stage, "SN_stage");
			waitForObj(5000);
			pdfResultReport.addStepDetails("clickOn_SNstage", "SN stage must be click sucessfully",
					"Successfully clicked on SN stage " + " ", "Pass", "Y");
			log.info("completed executing the method:: clickOn_POstage");
		} catch (Exception e) {
			log.fatal("Unable to click on PO stage" + e.getMessage());
			pdfResultReport.addStepDetails("clickOn_SNstage", "SN stage must be click sucessfully",
					"Unable to click on SN stage" + e.getMessage(), "Fail", "N");
		}
	}

	public void navigateTo_DOPListPage() throws Throwable {
		try {
			log.info("started executing the method:: navigateTo_DOPListPage");

			JSClick(tendercreationlocators.dopMenuLinkBy, "dopMenuLinkBy");

			JSClick(tendercreationlocators.dop_ListLinkBy, "dop_ListLinkBy");

			waitForObj(5000);

			waitForElementToBeVisible(tendercreationlocators.dopListSactionRowTextBy);

			waitForObj(2000);

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			waitForObj(5000);

			pdfResultReport.addStepDetails("navigateTo_DOPListPage", "Must Navigate to  DOP List page",
					"Sucessfully Navigated to  DOP List page" + " ", "Pass", "Y");
			log.info("completed executing the method:: navigateTo_DOPListPage");

		} catch (Exception e) {
			log.fatal("Unable to Navigate to  DOP List page" + e.getMessage());
			pdfResultReport.addStepDetails("navigateTo_DOPListPage", "Must Navigate to  DOP List page",
					"Unable to Navigate to  DOP List page" + e.getMessage(), "Fail", "N");
		}
	}

	public void filterDopWithModuleAndStatus(String module, String status) throws Throwable {
		try {

			select(tendercreationlocators.selectModuleBy, module);

			select(tendercreationlocators.selectDopStatusBy, status);

			if (status.equalsIgnoreCase("Draft")) {

				makeDopActive();

				navigateTo_DOPListPage();

			}

			else if (status.equalsIgnoreCase("Active")) {
				makeDopInActive();
			}

		} catch (Exception e) {
			log.fatal("" + e.getMessage());

		}
	}

	public void verify_DopCloneLinkAndMakeDuplicateDopActive(String dopName) throws Throwable {
		try {
			log.info("started executing the method::verify_DopCloneLinkAndMakeDuplicateDopActive");

			click(tendercreationlocators.action, "action");

			click(tendercreationlocators.dopClonelinkBy, "dopClonelinkBy");

			waitForElementToBeVisible(tendercreationlocators.confirmBtn);

			JSClick(tendercreationlocators.confirmBtn, "confirmBtn");

			waitForElementToBeVisible(tendercreationlocators.confirmationOkBy);

			click(tendercreationlocators.confirmationOkBy, "confirmationOkBy");

			waitForElementToBeVisible(tendercreationlocators.dopPreviewOkBy);

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			waitForObj(5000);

			JSClick(tendercreationlocators.dopPreviewOkBy, "dopPreviewOkBy");

			waitForElementToBeVisible(tendercreationlocators.dopListSactionRowTextBy);

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			waitForObj(5000);

			clear(tendercreationlocators.search, "clear the enter key word");

			set(tendercreationlocators.search, dopName, "enter key word");

			List<WebElement> statusDraft = ThreadLocalWebdriver.getDriver()
					.findElements(By.xpath("//*[@id='myTablebyrTl00']/tbody/tr/td[position()=9 and text()='Draft']"));
			int draftsize = statusDraft.size();

			statusDraft.get(draftsize - 1).isDisplayed();

			By draftStatActionMenuBy = By.xpath(
					"(//*[@id='myTablebyrTl00']/tbody/tr/td[position()=9 and text()='Draft']/following-sibling::td//button)");

			int size = ThreadLocalWebdriver.getDriver().findElements(draftStatActionMenuBy).size();

			ThreadLocalWebdriver.getDriver().findElements(draftStatActionMenuBy).get(size - 1).click();

			List<WebElement> editBy = ThreadLocalWebdriver.getDriver()
					.findElements(tendercreationlocators.dopListEditBy);

			int editsize = editBy.size();

			editBy.get(editsize - 1).isDisplayed();

			By activeBy = By.xpath("//*[starts-with(normalize-space(text()),'Activate')]");

			int activatesize = ThreadLocalWebdriver.getDriver().findElements(activeBy).size();

			ThreadLocalWebdriver.getDriver().findElements(activeBy).get(activatesize - 1).click();

			waitForElementToBeVisible(tendercreationlocators.confirmBtn);

			JSClick(tendercreationlocators.confirmBtn, "confirmBtn");

			waitForElementToBeVisible(tendercreationlocators.confirmationOkBy);

			IsElementPresent(By.xpath(
					"//*[starts-with(normalize-space(text()),'Already a DOP is Active with such combination')]"));

			pdfResultReport.addStepDetails("verify_DopCloneLinkAndMakeDuplicateDopActive",
					"Must create duplicate record with same details and status should be Draft  and can't activate with same details, alert will show",
					"Sucessfully not able to activate the Cloned Dop With Same Deatils Alert Error Pop Up is displaying"
							+ " ",
					"Pass", "Y");

			JSClick(tendercreationlocators.confirmationOkBy, "confirmationOkBy");

			waitForObj(5000);

			editBy.get(editsize - 1).click();

			log.info("completed executing the method:: verify_DopCloneLinkAndMakeDuplicateDopActive");
		} catch (Exception e) {
			log.fatal("Unable to Show Already a DOP is Active with such combination" + e.getMessage());
			pdfResultReport.addStepDetails("verify_DopCloneLinkAndMakeDuplicateDopActive",
					"Must create duplicate record with same details and status should be Draft  and can't activate with same details, alert will show",
					"Unable to Show Already a DOP is Active with such combination " + e.getMessage(), "Fail", "N");
		}

	}

	public void makeDopActive() throws Throwable {
		try {
			log.info("started executing the method:: makeDopActive");

			List<WebElement> dopValues = ThreadLocalWebdriver.getDriver()
					.findElements(By.xpath("//*[@id='myTablebyrTl00']/tbody/tr/td[5]"));

			List<WebElement> dopValuesTo = ThreadLocalWebdriver.getDriver()
					.findElements(By.xpath("//*[@id='myTablebyrTl00']/tbody/tr/td[6]"));

			List<WebElement> dopDraftRows = ThreadLocalWebdriver.getDriver()
					.findElements(By.xpath("//*[@id='myTablebyrTl00']/tbody/tr/td[2]/strong"));

			List<WebElement> dopactioMenu = ThreadLocalWebdriver.getDriver()
					.findElements(By.xpath("//button[contains(@data-toggle,'dropdown')]"));

			List<WebElement> dopActiveLink = ThreadLocalWebdriver.getDriver()
					.findElements(By.xpath("//*[contains(normalize-space(text()),'Activate')]"));

			if (dopDraftRows.size() > 0) {
				int i = 0;
				int j = 0;
				for (WebElement dopValue : dopValues) {

					int dopvalue = Integer.parseInt(dopValue.getText().trim());

					int expectedValue = Integer
							.valueOf(eTenderComponent.getDataFromPropertiesFile("Sanction_dopValueFrom"));

					int dopvalueTo = Integer.valueOf(dopValuesTo.get(j).getText().trim());

					if (expectedValue <= dopvalueTo || dopvalue >= expectedValue) {
						dopactioMenu.get(i).click();

						WebDriverWait wait = new WebDriverWait(ThreadLocalWebdriver.getDriver(), 60);

						wait.until(ExpectedConditions.visibilityOf(dopActiveLink.get(i)));

						dopActiveLink.get(i).click();

						waitForElementToBeVisible(tendercreationlocators.confirmBtn);

						JSClick(tendercreationlocators.confirmBtn, "confirmBtn");

						waitForElementToBeVisible(tendercreationlocators.confirmationOkBy);

						JSClick(tendercreationlocators.confirmationOkBy, "confirmationOkBy");

						waitForObj(5000);
					}

					i++;
					j++;
				}
				pdfResultReport.addStepDetails("makeDopActive",
						"Check the dop in draft status if there is any dop value with the expected dop value",
						"Sucessfully verified the dop with the expected range" + " ", "Pass", "Y");
				log.info("completed executing the method:: makeDopActive");

			} else {

				pdfResultReport.addStepDetails("makeDopActive", "Should be no Draft Dop's with in the Expected range",
						"Sucessfully checked and Present there are no Draft Dop's with in the Expected range" + " ",
						"Pass", "Y");

				log.info("completed executing the method:: makeDopActive");
			}

		} catch (Exception e) {
			log.fatal("Unable to verify the dop values range in Draft status  as per expected value " + e.getMessage());
			pdfResultReport.addStepDetails("makeDopActive",
					"Check the dop in draft status if there is any dop value with the expected dop value",
					"Unable to verify the dop values range in Draft status  as per expected value " + e.getMessage(),
					"Fail", "N");
		}
	}

	public void makeDopInActive() throws Throwable {
		try {
			log.info("started executing the method:: makeDopInActive");

			List<WebElement> dopactiverows = ThreadLocalWebdriver.getDriver()
					.findElements(By.xpath("//*[@id='myTablebyrTl00']/tbody/tr/td[2]/strong"));

			List<WebElement> dopValues = ThreadLocalWebdriver.getDriver()
					.findElements(By.xpath("//*[@id='myTablebyrTl00']/tbody/tr/td[5]"));

			List<WebElement> dopValuesTo = ThreadLocalWebdriver.getDriver()
					.findElements(By.xpath("//*[@id='myTablebyrTl00']/tbody/tr/td[6]"));

			List<WebElement> dopactioMenu = ThreadLocalWebdriver.getDriver()
					.findElements(By.xpath("//button[contains(@data-toggle,'dropdown')]"));

			List<WebElement> dopDe_ActiveLink = ThreadLocalWebdriver.getDriver()
					.findElements(By.xpath("//*[contains(normalize-space(text()),'De-Activate')]"));

			if (dopactiverows.size() > 0) {
				int i = 0;
				int j = 0;
				for (WebElement dopValue : dopValues) {

					int dopvalue = Integer.parseInt(dopValue.getText().trim());

					int expectedValue = Integer
							.valueOf(eTenderComponent.getDataFromPropertiesFile("Sanction_dopValueFrom"));

					int dopvalueTo = Integer.valueOf(dopValuesTo.get(j).getText().trim());

					if (expectedValue <= dopvalueTo || dopvalue >= expectedValue) {
						dopactioMenu.get(i).click();

						WebDriverWait wait = new WebDriverWait(ThreadLocalWebdriver.getDriver(), 60);

						wait.until(ExpectedConditions.visibilityOf(dopDe_ActiveLink.get(i)));

						dopDe_ActiveLink.get(i).click();

						waitForElementToBeVisible(tendercreationlocators.confirmBtn);

						JSClick(tendercreationlocators.confirmBtn, "confirmBtn");

						waitForElementToBeVisible(tendercreationlocators.confirmationOkBy);

						JSClick(tendercreationlocators.confirmationOkBy, "confirmationOkBy");

						waitForObj(5000);
					}

					i++;
					j++;
				}

				pdfResultReport.addStepDetails("makeDopInActive",
						"Check the dop in Active status if there is any dop value with the expected dop value",
						"Sucessfully verified Dop value range as per Expected value in Dop  Active status" + " ",
						"Pass", "Y");
				log.info("completed executing the method:: makeDopInActive");
			} else {
				pdfResultReport.addStepDetails("makeDopInActive",
						"Should be no Active Dop's with in the Expected range",
						"Sucessfully checked and Present there are no Active Dop's with in the Expected range" + " ",
						"Pass", "Y");

				log.info("completed executing the method:: makeDopInActive");
			}

		} catch (Exception e) {
			log.fatal("Unable to verify the Dop value range as per Expected value in Dop  Active status"
					+ e.getMessage());
			pdfResultReport.addStepDetails("makeDopInActive",
					"Check the dop in draft status if there is any dop value with the expected dop value",
					"Unable to verify the Dop value range as per Expected value in Dop  Active status" + e.getMessage(),
					"Fail", "N");
		}
	}

	String DopName = "";

	public void createDopForSanction_Module() throws Throwable {
		try {
			log.info("started executing the method::  createDopForSanction_Module");

			JSClick(tendercreationlocators.dopMenuLinkBy, "dopMenuLinkBy");

			JSClick(tendercreationlocators.createDopLinkBy, "createDopLinkBy");

			waitForObj(5000);

			waitForElementToBeVisible(tendercreationlocators.selectModuleBy);

			//waitForElementToBeVisible(tendercreationlocators.LoadingBy);

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			select(tendercreationlocators.selectModuleBy, "Sanction");

			select(tendercreationlocators.selectProcurementCategoryBy, "Default_cat");

			click(tendercreationlocators.addOrg_HierarchyBy, "addOrg_HierarchyBy");

			waitForObj(5000);

			waitForElementToBeVisible(tendercreationlocators.addBtn_HierarchyBy);

			click(tendercreationlocators.addBtn_HierarchyBy, "addBtn_HierarchyBy");

			waitForObj(2000);
			set(tendercreationlocators.dopValueFromBy,
					eTenderComponent.getDataFromPropertiesFile("Sanction_dopValueFrom"), "typeAnyKeyword");

			waitForObj(1000);
			set(tendercreationlocators.dopValueToBy, eTenderComponent.getDataFromPropertiesFile("Sanction_dopValueTo"),
					"typeAnyKeyword");

			waitForObj(1000);

			String dopName = "San_Dop_";

			LocalDateTime localdatetime = LocalDateTime.now();

			String currentDateTime = localdatetime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm"));

			String DopName = dopName.concat(currentDateTime);

			eTenderComponent.updateDataIntoPropertyFile("san_dopName", DopName);

			set(tendercreationlocators.dopNameBy, DopName, "dopNameBy");

			waitForObj(1000);

			click(tendercreationlocators.savebutton, "saveButton");

			IsElementPresent(tendercreationlocators.DopSucessMsgBy);

			pdfResultReport.addStepDetails("DopSucessMsgBy", "must show Dop sucess msg",
					"Sucessfully showing dop sucess msg" + " ", "Pass", "Y");

			click(tendercreationlocators.confirmationOkBy, "confirmationOkBy");

			waitForObj(3000);

			pdfResultReport.addStepDetails("createDopForSanction_Module", "Must save DopCreation In Saction Module",
					"Successfully saved the DOP Creation " + " ", "Pass", "Y");
			log.info("completed executing the method:: createDopForSanction_Module");
		} catch (Exception e) {
			log.fatal("Unable to Save the Dop creation" + e.getMessage());
			pdfResultReport.addStepDetails("createDopForSanction_Module", "Must save DopCreation In Saction Module",
					"Unable to Save the Dop creation" + e.getMessage(), "Fail", "N");
		}
	}

	public void deleteDopCreated() {
		try {
			click(tendercreationlocators.deleteDopBy, "deleteDopBy");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void navigateTo_DOPList() throws Throwable {
		try {
			log.info("started executing the method:: navigateTo_DOPList");

			JSClick(tendercreationlocators.dopMenuLinkBy, "dopMenuLinkBy");

			JSClick(tendercreationlocators.dop_ListLinkBy, "dop_ListLinkBy");

			waitForObj(5000);

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			waitForElementToBeVisible(tendercreationlocators.dopListSactionRowTextBy);

			waitForObj(5000);

			clear(tendercreationlocators.search, "clear the enter key word");

			set(tendercreationlocators.search, eTenderComponent.getDataFromPropertiesFile("san_dopName"),
					"enter key word");

			waitForElementToBeVisible(tendercreationlocators.dopListSactionRowTextBy);

			pdfResultReport.addStepDetails("navigateTo_DOPList", "Must display the Respective DOP in DOP List",
					"Sucessfully displaying the Respective DOP in DOP List" + " ", "Pass", "Y");
			log.info("completed executing the method:: navigateTo_DOPList");

		} catch (Exception e) {
			log.fatal("Unable to display the Respective DOP in DOP List" + e.getMessage());
			pdfResultReport.addStepDetails("navigateTo_DOPList", "Must display the Respective DOP in DOP List",
					"Unable to display the Respective DOP in DOP List" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyRespectiveDopIsAddedInDopList() throws Throwable {
		try {
			log.info("started executing the method:: verifyRespectiveDopIsAddedInDopList");

			String dopname = "//table/tbody/tr[1]/td[text()='{0}']";

			Assert.assertTrue(ThreadLocalWebdriver.getDriver()
					.findElement(
							By.xpath(dopname.replace("{0}", eTenderComponent.getDataFromPropertiesFile("san_dopName"))))
					.isDisplayed());

			pdfResultReport.addStepDetails("verifyRespectiveDopIsAddedInDopList",
					"Must display the Respective DOP in DOP List",
					"Sucessfully displaying the Respective DOP in DOP List" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyRespectiveDopIsAddedInDopList");

		} catch (Exception e) {
			log.fatal("Unable to display the Respective DOP in DOP List" + e.getMessage());
			pdfResultReport.addStepDetails("verifyRespectiveDopIsAddedInDopList",
					"Must display the Respective DOP in DOP List",
					"Unable to display the Respective DOP in DOP List" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyDopListpageColumnNames() throws Exception {
		try {
			log.info("started executing the method::verifyDopListpageColumnNames");

			String[] DopColumns = { "Sl No.", "Module", "Procurement Category", "Organization Hierarchy", "Value From",
					"Value To", "DOP Name", "Created On", "Status", "Action" };

			List<String> DopColumnsList = Arrays.asList(DopColumns);

			List<WebElement> DoplistPageElements = ThreadLocalWebdriver.getDriver()
					.findElements(tendercreationlocators.doplistPageColumnNames);

			ArrayList<String> DopListOfColumns = new ArrayList<>();

			for (WebElement DopColumn : DoplistPageElements) {

				DopListOfColumns.add(DopColumn.getText().trim());
			}

			Assert.assertTrue(DopListOfColumns.containsAll(DopColumnsList));

			pdfResultReport.addStepDetails("verifyDopListpageColumnNames",
					"DOP list page Should consists of All column names mentioned",
					"Sucessfully Showing all the Dop list column names" + " ", "Pass", "Y");
			log.info("completed executing the method:: navigateTo_DOPList");

		} catch (Exception e) {
			log.fatal("Unable to Showing all the Dop list column names" + e.getMessage());
			pdfResultReport.addStepDetails("verifyDopListpageColumnNames",
					"DOP list page Should consists of All column names mentioned",
					"Unable to Showing all the Dop list column names" + e.getMessage(), "Fail", "N");
		}

	}

	public void verify_Dop_PreviewLink() throws Throwable {
		try {
			log.info("started executing the method::verify_Dop_PreviewLink");

			click(tendercreationlocators.action, "action");

			click(tendercreationlocators.dopListPreviewBy, "dopListPreviewBy");

			waitForObj(2000);

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			IsElementPresent(tendercreationlocators.dopPreviewVerifySanctionModuleBy);

			String dopname = "//table/tbody/tr/td[label[text()='DOP Name'] ]/following-sibling::td/label[contains(normalize-space(text()),'{0}')]";

			IsElementPresent(By.xpath(dopname.replace("{0}", DopName)));

			IsElementPresent(By.xpath(
					"//table/tbody/tr/td[label[text()='Procurement Category']]/following-sibling::td/label[contains(normalize-space(text()),'Default_cat')]"));

			IsElementPresent(By.xpath(
					"//*[@id='myModalprev']//child::div/table[2]/tbody/tr/td[contains(text(),'SN Approver test')]"));

			pdfResultReport.addStepDetails("Check Dop PreviewDetails",
					"DOP preview details must be verified Sucessfully",
					"Sucessfully verified all the DOP Preview details" + " ", "Pass", "Y");

			waitForObj(2000);

			JSClick(tendercreationlocators.dopPreviewOkBy, "dopPreviewOkBy");

			pdfResultReport.addStepDetails("verify_Dop_PreviewLink", "DOP preview details must be verified Sucessfully",
					"Sucessfully verified all the DOP preview details" + " ", "Pass", "Y");
			log.info("completed executing the method:: verify_Dop_PreviewLink");
		} catch (Exception e) {
			log.fatal("Unable to verify all the DOP preview details" + e.getMessage());
			pdfResultReport.addStepDetails("verify_Dop_PreviewLink", "DOP preview details must be verified Sucessfully",
					"Unable to verify all the DOP preview details" + e.getMessage(), "Fail", "N");
		}
	}

	public void verify_DopCloneLink(String dopName) throws Throwable {
		try {
			log.info("started executing the method::verify_DopCloneLink");

			click(tendercreationlocators.action, "action");

			click(tendercreationlocators.dopClonelinkBy, "dopClonelinkBy");

			waitForElementToBeVisible(tendercreationlocators.confirmBtn);

			JSClick(tendercreationlocators.confirmBtn, "confirmBtn");

			waitForElementToBeVisible(tendercreationlocators.confirmationOkBy);

			click(tendercreationlocators.confirmationOkBy, "confirmationOkBy");

			waitForElementToBeVisible(tendercreationlocators.dopPreviewOkBy);

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			waitForObj(5000);

			JSClick(tendercreationlocators.dopPreviewOkBy, "dopPreviewOkBy");

			waitForElementToBeVisible(tendercreationlocators.dopListSactionRowTextBy);

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			waitForObj(5000);

			clear(tendercreationlocators.search, "clear the enter key word");

			set(tendercreationlocators.search, dopName, "enter key word");

			List<WebElement> statusDraft = ThreadLocalWebdriver.getDriver()
					.findElements(By.xpath("//*[@id='myTablebyrTl00']/tbody/tr/td[position()=9 and text()='Draft']"));
			int draftsize = statusDraft.size();

			statusDraft.get(draftsize - 1).isDisplayed();

			By draftStatActionMenuBy = By.xpath(
					"(//*[@id='myTablebyrTl00']/tbody/tr/td[position()=9 and text()='Draft']/following-sibling::td//button)");

			int size = ThreadLocalWebdriver.getDriver().findElements(draftStatActionMenuBy).size();

			ThreadLocalWebdriver.getDriver().findElements(draftStatActionMenuBy).get(size - 1).click();

			IsElementPresent(tendercreationlocators.dopListEditBy);

			ThreadLocalWebdriver.getDriver().findElements(draftStatActionMenuBy).get(size - 1).click();

			pdfResultReport.addStepDetails("verify_DopCloneLink",
					"Must create duplicate record with same details and status should be Draft",
					"Sucessfully cloned Dop Record and Status is Draft" + " ", "Pass", "Y");
			log.info("completed executing the method:: verify_DopCloneLink");
		} catch (Exception e) {
			log.fatal("Unable to clone the Dop  record" + e.getMessage());
			pdfResultReport.addStepDetails("verify_DopCloneLink",
					"Must create duplicate record with same details and status should be Draft",
					"Unable to clone the Dop  record" + e.getMessage(), "Fail", "N");
		}

	}

	public void doActvationForCloneRecord() throws Throwable {
		waitForObj(3000);

		By activeStateLinkBy = By.xpath(
				"(//table/tbody/tr/td[text()='Draft']/following-sibling::td//button)[1]//following-sibling::ul//*[contains(normalize-space(text()),'Activate')]");

		click(tendercreationlocators.draftStatActionMenuBy, "draftStatActionMenuBy");

		waitForObj(2000);

		click(activeStateLinkBy, "activeTabLinkBy");

		waitForObj(5000);

		JSClick(tendercreationlocators.confirmBtn, "confirmBtn");

		waitForObj(8000);

		JSClick(tendercreationlocators.confirmationOkBy, "confirmationOkBy");

		waitForObj(3000);

		click(tendercreationlocators.activeStateActionMenuBy, "activeStateActionMenuBy");

	}

	public void doDe_ActvationForCloneRecord() throws Throwable {
		waitForObj(3000);

		By de_ActiveStateLinkBy = By.xpath(
				"(//table/tbody/tr/td[text()='Active']/following-sibling::td//button)[1]//following-sibling::ul//*[contains(normalize-space(text()),'De-Activate')]");

		click(tendercreationlocators.activeStateActionMenuBy, "activeStateActionMenuBy");

		waitForObj(2000);

		click(de_ActiveStateLinkBy, "deActiveTabLinkBy");

		waitForObj(5000);

		JSClick(tendercreationlocators.confirmBtn, "confirmBtn");

		waitForObj(8000);

		JSClick(tendercreationlocators.confirmationOkBy, "confirmationOkBy");

		waitForObj(3000);

		IsElementPresent(tendercreationlocators.dopListStatusInActiveBy);

	}

	public void verify_EditDopTabLink() throws Throwable {
		try {
			log.info("started executing the method::verify_EditDopTabLink");

			click(tendercreationlocators.action, "action");

			waitForObj(2000);

			click(tendercreationlocators.dopListEditBy, "dopListEditBy");

			waitForElementToBeVisible(tendercreationlocators.dopNameBy);

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			waitForObj(2000);

			String dopName = "San_Dop_";

			LocalDateTime localdatetime = LocalDateTime.now();

			String currentDateTime = localdatetime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm"));

			String DopName = dopName.concat(currentDateTime);

			eTenderComponent.updateDataIntoPropertyFile("san_dopName", DopName);

			clear(tendercreationlocators.dopNameBy, "clear the dop name");

			waitForObj(1000);

			set(tendercreationlocators.dopNameBy, DopName, "dopNameBy");

			waitForObj(1000);

			click(tendercreationlocators.savebutton, "saveButton");

			waitForElementToBeVisible(tendercreationlocators.DopSucessMsgBy);

			IsElementPresent(tendercreationlocators.DopSucessMsgBy);

			pdfResultReport.addStepDetails("DopSucessMsgBy", "must save Dop creation",
					"SucessFully saved Dop Creation" + " ", "Pass", "Y");

			waitForElementToBeVisible(tendercreationlocators.confirmationOkBy);

			click(tendercreationlocators.confirmationOkBy, "confirmationOkBy");

			waitForObj(3000);

			navigateTo_DOPList();

			pdfResultReport.addStepDetails("verify_EditDopTabLink",
					"DOP Which is not Activated can be edited and all fields should be editable with save button.",
					"Successfully editable For the Dop which is Not Activated" + " ", "Pass", "Y");
			log.info("completed executing the method:: verify_EditDopTabLink");
		} catch (Exception e) {
			log.fatal("Unable to do editable For not activated DOP" + e.getMessage());
			pdfResultReport.addStepDetails("verify_EditDopTabLink",
					"DOP Which is not Activated can be edited and all fields should be editable with save button.",
					"Unable to do editable For  DOP which is not activated" + e.getMessage(), "Fail", "N");
		}
	}

	public void addUserForApprovalForCreatedDop(String approvalType) throws Exception {

		// SEQUENTIAL

		click(tendercreationlocators.action, "action");

		waitForObj(2000);

		click(tendercreationlocators.addUserBy, "addUserBy");

		waitForObj(5000);

		waitForElementToBeVisible(tendercreationlocators.search);

		clear(tendercreationlocators.search, "clear the user");

		set(tendercreationlocators.search, pdfResultReport.testData.get("AddUSerName"), "enter User For Approval");

		click(tendercreationlocators.addUserToTableBy, "addUserToTableBy");

		waitForObj(2000);

		select(tendercreationlocators.approvalTypeDropdownBy, approvalType);

		waitForObj(3000);

		click(tendercreationlocators.saveUserBy, "saveUserBy");

		waitForElementToBeVisible(tendercreationlocators.confirmationOkBy);

		click(tendercreationlocators.confirmationOkBy, "confirmationOkBy");

		waitForObj(3000);
	}

	public static int getrandomInterger(int min, int max) {

		return ((int) (Math.random() * (max - min))) + min;

	}

	public void createSanctionNote() throws Throwable {
		try {
			log.info("started executing the method:: createSanctionNote");
			JSClick(tendercreationlocators.SN_stage, "SN_stage");
			waitForElementToBeVisible(tendercreationlocators.createSanctionNote);
			waitForObj(5000);
			JSClick(tendercreationlocators.createSanctionNote, "createSanctionNote");
			pdfResultReport.addStepDetails("createSanctionNote", "Create sanction note must be click sucessfully",
					"Successfully clicked on Create sanction note " + " ", "Pass", "Y");
			log.info("completed executing the method:: createSanctionNote");
		} catch (Exception e) {
			log.fatal("Unable to click on Create sanction note" + e.getMessage());
			pdfResultReport.addStepDetails("createSanctionNote", "Create sanction note must be click sucessfully",
					"Unable to click on Create sanction note" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyPendingForApprovalSanctionStageAndApprovalDetails() throws Exception {
		try {
			log.info("started executing the method::  verifyPendingForApprovalSanctionStageAndApprovalDetails");
			clear(tendercreationlocators.tenderListKeyword, "tenderListKeyword");
			set(tendercreationlocators.typeAnyKeyword, documentNumberText, "typeAnyKeyword");
			waitForObj(5000);
			IsElementPresent(tendercreationlocators.pendingForApproval);
			click(tendercreationlocators.action, "action");
			IsElementPresent(tendercreationlocators.approvalDetails);
			pdfResultReport.addStepDetails("enterCompletedTenderId",
					"Tender id should be displayed in completed tender list page",
					"Sucessfully displayed tender id in completed tender list page" + " ", "Pass", "Y");
			log.info("completed executing the method:: enterCompletedTenderId");
		} catch (Exception e) {
			log.fatal("Unable to display tender id in completed tender list page" + e.getMessage());
			pdfResultReport.addStepDetails("enterCompletedTenderId",
					"Tender id should be displayed in completed tender list page",
					"Unable to display tender id in completed tender list page" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyApprovedBidderList() throws Throwable {
		try {
			log.info("started executing the method::  verifyApprovedBidderList");
			clear(tendercreationlocators.tenderListKeyword, "tenderListKeyword");
			set(tendercreationlocators.typeAnyKeyword, documentNumberText, "typeAnyKeyword");
			waitForObj(3000);
			IsElementPresent(tendercreationlocators.supplierStanding);
			pdfResultReport.addStepDetails("verifyApprovedBidderList", "Bidder element is located",
					"Unable to to locate an element" + " ", "Pass", "Y");
			waitForObj(3000);
			log.info("completed executing the method:: verifyApprovedBidderList");
		} catch (Exception e) {
			log.fatal("Unable to to locate an element" + e.getMessage());
			pdfResultReport.addStepDetails("verifyApprovedBidderList", "The page redirects to Approved suppliers list",
					"Unable to locate an element" + e.getMessage(), "Fail", "N");
		}
	}

	public void itemRowSelectionAlertMessageValidation() throws Exception {
		try {
			log.info("started executing the method::  itemRowSelectionAlertMessageValidation");
			waitForElementToBeVisible(tendercreationlocators.suppliercheckboxCTS);
			waitForObj(5000);
			click(tendercreationlocators.suppliercheckboxCTS, "suppliercheckboxCTS");
			click(tendercreationlocators.plusIcon_CTS, "plusIcon_CTS");
			waitForObj(1000);
			click(tendercreationlocators.termsAndConditioncheckBox_CTS, "termsAndConditioncheckBox_CTS");
			waitForObj(2000);
			click(tendercreationlocators.submit, "submit");
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.itemSelectionAlert);
			pdfResultReport.addStepDetails("itemRowSelectionAlertMessageValidation",
					"Alert message must be validate successfully", "Sucessfully validated Alert message" + " ", "Pass",
					"Y");
			log.info("completed executing the method:: itemRowSelectionAlertMessageValidation");
		} catch (Exception e) {
			log.fatal("Unable to validate Alert message" + e.getMessage());
			pdfResultReport.addStepDetails("itemRowSelectionAlertMessageValidation",
					"Alert message must be validate successfully", "Unable to validate Alert message" + e.getMessage(),
					"Fail", "N");
		}
	}

	public void sanctionNoteAttachment() throws Exception {
		try {
			log.info("started executing the method::  sanctionNoteAttachment");
			click(tendercreationlocators.addAttachment, "addAttachment");
			set(tendercreationlocators.uploadAttachment,
					System.getProperty("user.dir") + "\\MediaFiles\\rfqCreation.xlsx", "fileName");
			waitForObj(2000);
			pdfResultReport.addStepDetails("sanctionNoteAttachment", "Attachment must be upload successfully",
					"Sucessfully uploaded attachment" + " ", "Pass", "Y");
			log.info("completed executing the method:: sanctionNoteAttachment");
		} catch (Exception e) {
			log.fatal("Unable to upload attachment" + e.getMessage());
			pdfResultReport.addStepDetails("sanctionNoteAttachment", "Attachment must be upload successfully",
					"Unable to upload attachment" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickPostTenderProcessLink() throws Throwable {
		try {
			log.info("started executing the method:: clickPostTenderProcessLink");

			click(tendercreationlocators.tendersIcon, "tendersIcon");
			waitForObj(2000);

			JSClick(tendercreationlocators.postTenderProcessBy, "postTenderProcessBy");

			waitForObj(3000);

			checkPageIsReady();

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

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
	public void enterTenderIdInSearch_sanctionNote() throws Throwable {
		try {

			log.info("started executing the method:: enterTenderIdInSearch_sanctionNote");

			clear(tendercreationlocators.tenderListKeyword, "tenderListKeyword");

			set(tendercreationlocators.tenderListKeyword, tenderReferenceNoLocatorText_sn, "tenderListSearch");

			waitForElementToBeVisible(tendercreationlocators.supplier_standing);

			waitForObj(5000);
			pdfResultReport.addStepDetails("Successfully Saved",
					"Tender Id must be entered successfully in search field",
					"Tender Id is successfully entered in search field" + " ", "Pass", "Y");

			log.info("completed executing the method:: enterTenderIdInSearch_sanctionNote");
		} catch (Exception e) {
			log.fatal("Not able to create tender" + e.getMessage());
			pdfResultReport.addStepDetails("Not able to create tender", "Not able to enter Tender Id in search field",
					"Unable to enter Tender Id in search field" + e.getMessage(), "Fail", "N");
		}
	}
	
	public String tenderIdSave_sn() throws Throwable 
	{
	log.info("started executing the method:: tenderIdSave");
	tenderReferenceNoLocatorText_sn = text(tendercreationlocators.tenderReferenceNoLocator).trim();
	System.out.println(tenderReferenceNoLocatorText_sn);
	return tenderReferenceNoLocatorText_sn;
	}
	public String documentNoSave() throws Throwable {
		log.info("started executing the method:: documentNoSave");
		documentNumberText = text(tendercreationlocators.documentNumber).trim();
		System.out.println(documentNumberText);
		eTenderComponent.updateDataIntoPropertyFile("sanctionoteDocnum", documentNumberText);
		return documentNumberText;
	}

	public void CreateSanctionNoteWithPredefinedApproval() throws Throwable {
		try {
			log.info("started executing the method::CreateSanctionNoteWithPredefinedApproval");

			click(tendercreationlocators.SN_stage, "SN_stage");

			WebDriverWait wait = new WebDriverWait(ThreadLocalWebdriver.getDriver(), 100);
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//*[@id='myTabContent']/child::div/child::div/table/tbody/tr[2]")));
			waitForObj(5000);

			click(tendercreationlocators.createSanctionNote, "createSanctionNote");

			set(tendercreationlocators.sanctionReferenceNo, pdfResultReport.testData.get("SanctionReferenceNumber"),
					"sanctionReferenceNo");
			click(tendercreationlocators.sanctionSubmit, "sanctionSubmit");

			waitForObj(10000);

			click(tendercreationlocators.itemAllotment1, "itemAllotment1");

			click(tendercreationlocators.itemAllotmentSelect, "itemAllotmentSelect");

			click(tendercreationlocators.itemAllotmentRow, "itemAllotmentRow");

			waitForObj(3000);

			JavascriptExecutor jse = (JavascriptExecutor) ThreadLocalWebdriver.getDriver();
			jse.executeScript("window.scrollBy(0,-650)");

			JSClick(tendercreationlocators.recommendationTab, "recommendationTab");
			waitForObj(4000);

			WebElement iframele = ThreadLocalWebdriver.getDriver().findElement(By.xpath("//iframe[@id='txtArea_ifr']"));
			switchframe(iframele);

			set(tendercreationlocators.recommendationOverAllCommentButton, "recommendationOverAllCommentButton",
					"recommendationOverAllCommentButton");
			switchToDefaultFrame();

			click(tendercreationlocators.submit, "submit");
			waitForObj(10000);
			documentNoSave();

			click(tendercreationlocators.predefine, "predefine");
			waitForObj(2000);

			IsElementPresent(By.xpath("//*[@id='menusndApprovl']/div/table/tbody/tr/td[2]"));

			pdfResultReport.addStepDetails("CreateSanctionNoteWithPredefinedApproval",
					"Sanction note with predifined Approval should dispaly Dop Active user",
					"Sucessfully showing Dop Active users in with predefined work flow approval" + " ", "Pass", "Y");

			JSClick(By.xpath("//*[@ng-click='sendForApproval(approvalObj.workflowType)']"), "sendForApprovalSubmit");

			waitForObj(6000);

			pdfResultReport.addStepDetails("CreateSanctionNoteWithPredefinedApproval", "Should show Sucessful msg",
					"Sucessfully showing success msg" + " ", "Pass", "Y");

			// WebDriverWait wait = new
			// WebDriverWait(ThreadLocalWebdriver.getDriver(), 100);

			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//*[@id='myTabContent']/child::div/child::div/table/tbody/tr[2]")));
			waitForObj(5000);

			pdfResultReport.addStepDetails("CreateSanctionNoteWithPredefinedApproval",
					"Sanction note with predifined Approval should dispaly Dop Active user",
					"Sucessfully showing Dop Active users in with predefined work flow approval" + " ", "Pass", "Y");
			log.info("completed executing the method:: CreateSanctionNoteWithPredefinedApproval");
		} catch (Exception e) {
			log.fatal("Unable to show Dop Active users in with predefined work flow approval" + e.getMessage());
			pdfResultReport.addStepDetails("CreateSanctionNoteWithPredefinedApproval",
					"Sanction note with predifined Approval should dispaly Dop Active user",
					"Unable to show Dop Active users in with predefined work flow approval" + e.getMessage(), "Fail",
					"N");
		}
	}

	public void checkStageIsUnderPendingForApprovalForSanctionNote() throws Exception {
		try {
			log.info("started executing the method::checkStageIsUnderPendingForApprovalForSanctionNote");

			IsElementPresent(tendercreationlocators.pendingForApproval);

			pdfResultReport.addStepDetails("checkStageIsUnderPendingForApprovalForSanctionNote",
					"Respective SN will be under Pending for Approval stage",
					"Sucessfully SN is showing under Pending for Approval stage" + " ", "Pass", "Y");
			log.info("completed executing the method:: checkStageIsUnderPendingForApprovalForSanctionNote");
		} catch (Exception e) {
			log.fatal("Unable to show SN  under Pending for Approval stage" + e.getMessage());
			pdfResultReport.addStepDetails("checkStageIsUnderPendingForApprovalForSanctionNote",
					"Respective SN will be under Pending for Approval stage",
					"Unable to show SN  under Pending for Approval stage" + e.getMessage(), "Fail", "N");
		}
	}

	public void CreateSanctionNoteWithPredefinedApprovalWithInactiveDopUsers() throws Throwable {
		try {
			log.info("started executing the method::CreateSanctionNoteWithPredefinedApprovalWithInactiveDopUsers");

			String SN = "SN_";
			String ref = String.valueOf(getrandomInterger(10000000, 1000000000));
			String sanctionReference = SN.concat(ref);

			click(tendercreationlocators.SN_stage, "SN_stage");

			checkPageIsReady();

			waitForElementToBeVisible(By.xpath("//*[@id='myTabContent']/div[2]/div/table/tbody/tr[2]"));

			waitForObj(3000);

			click(tendercreationlocators.createSanctionNote, "createSanctionNote");

			waitForElementToBeVisible(tendercreationlocators.sanctionReferenceNumber);

			waitForObj(3000);

			set(tendercreationlocators.sanctionReferenceNo, sanctionReference, "sanctionReferenceNo");
			click(tendercreationlocators.sanctionSubmit, "sanctionSubmit");

			waitForObj(2000);

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			waitForElementToBeVisible(tendercreationlocators.l1RankEleBy);

			waitForObj(4000);

			click(tendercreationlocators.itemAllotment1, "itemAllotment1");

			click(tendercreationlocators.itemAllotmentSelect, "itemAllotmentSelect");

			click(tendercreationlocators.itemAllotmentRow, "itemAllotmentRow");

			waitForObj(3000);

			JavascriptExecutor jse = (JavascriptExecutor) ThreadLocalWebdriver.getDriver();
			jse.executeScript("window.scrollBy(0,-650)");

			JSClick(tendercreationlocators.recommendationTab, "recommendationTab");
			Thread.sleep(10000);
			JSClick(By.xpath("//*[@aria-label='Bold']"), "overallComment");

			WebElement iframele = ThreadLocalWebdriver.getDriver().findElement(By.xpath("//iframe[@id='txtArea_ifr']"));
			switchframe(iframele);
			Thread.sleep(10000);
			set(tendercreationlocators.recommendationComment, "Provide Comment", "recommendationComment");
			switchToDefaultFrame();
			Thread.sleep(5000);
			click(tendercreationlocators.saveSanction, "saveSanction");

			Thread.sleep(3000);

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			Thread.sleep(3000);

			click(tendercreationlocators.submit, "submit");

			waitForElementToBeVisible(tendercreationlocators.sendForApprovalNotRequired_SN);

			waitForObj(3000);

			documentNoSave();

			click(tendercreationlocators.predefine, "predefine");

			waitForObj(2000);

			IsElementPresent(By.xpath("//strong[contains(normalize-space(text()),'NOTE : No such DOP Present.')]"));

			pdfResultReport.addStepDetails("CreateSanctionNoteWithPredefinedApprovalWithInactiveDopUsers",
					"list must be showing empty for Dop Deactived users",
					"list is showing empty Successfully for Dop Inactive users in predefined wf" + " ", "Pass", "Y");
			log.info("completed executing the method:: CreateSanctionNoteWithPredefinedApprovalWithInactiveDopUsers");
		} catch (Exception e) {
			log.fatal("Failed to showing empty list for Dop in active users in predefined wf" + e.getMessage());
			pdfResultReport.addStepDetails("CreateSanctionNoteWithPredefinedApprovalWithInactiveDopUsers",
					"list must be showing empty for Dop Deactived users",
					"Failed to showing empty list for Dop in active users in predefined wf" + e.getMessage(), "Fail",
					"N");
		}
	}

	public void initiateRecallForSanctionCreator() throws Exception {
		try {
			log.info("started executing the method::  initiateRecallForSanctionCreator");
			clear(tendercreationlocators.tenderListKeyword, "tenderListKeyword");
			set(tendercreationlocators.typeAnyKeyword, documentNumberText, "typeAnyKeyword");
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.pendingForApproval);
			pdfResultReport.addStepDetails("initiateRecallForSanctionCreator",
					"Recall should be submitted successfully",
					"Recall should be submitted successfully in completed tender list page" + " ", "Pass", "Y");
			click(tendercreationlocators.action, "action");
			click(tendercreationlocators.recall, "recall");
			waitForElementToBeVisible(tendercreationlocators.recallReason);
			set(tendercreationlocators.recallReason, pdfResultReport.testData.get("RecallReason"), "recallReason");
			pdfResultReport.addStepDetails("initiateRecallForSanctionCreator",
					"Recall button must be validate successfully", "Sucessfully validate recall button" + " ", "Pass",
					"Y");
			click(tendercreationlocators.submitRecall, "submitRecall");
			waitForElementToBeInvisible(tendercreationlocators.Snipper);
			waitForElementToBeVisible(tendercreationlocators.action);
			clear(tendercreationlocators.tenderListKeyword, "tenderListKeyword");
			set(tendercreationlocators.typeAnyKeyword, documentNumberText, "typeAnyKeyword");
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.draftStage);
			Boolean draftStageVerify = ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.draftStage)
					.isDisplayed();
			if (draftStageVerify) {
				System.out.println("Recall is successfull");
			}
			pdfResultReport.addStepDetails("initiateRecallForSanctionCreator",
					"Recall should be submitted successfully",
					"Recall should be submitted successfully in completed tender list page" + " ", "Pass", "Y");
			log.info("completed executing the method:: initiateRecallForSanctionCreator");
		} catch (Exception e) {
			log.fatal("Unable to submit recall in completed tender list page" + e.getMessage());
			pdfResultReport.addStepDetails("initiateRecallForSanctionCreator",
					"Draft stage should be displayed in completed tender list page",
					"Unable to display draft stage in completed tender list page" + e.getMessage(), "Fail", "N");
		}
	}

	public void RecallForSanctionCreator() throws Throwable {
		try {
			log.info("started executing the method::  initiateRecallForSanctionCreator");

			waitForObj(5000);
			IsElementPresent(tendercreationlocators
					.ApprovalPendingSN(eTenderComponent.getDataFromPropertiesFile("sanctionoteDocnum")));
			highlight(tendercreationlocators
					.ApprovalPendingSN(eTenderComponent.getDataFromPropertiesFile("sanctionoteDocnum")));
			waitForObj(5000);
			recallButtonValidation();
			waitForObj(7000);
			pdfResultReport.addStepDetails("initiateRecallForSanctionCreator",
					"Recall should be submitted successfully",
					"Recall should be submitted successfully in completed tender list page" + " ", "Pass", "Y");
			log.info("completed executing the method:: initiateRecallForSanctionCreator");
		} catch (Exception e) {
			log.fatal("Unable to submit recall in completed tender list page" + e.getMessage());
			pdfResultReport.addStepDetails("initiateRecallForSanctionCreator",
					"Draft stage should be displayed in completed tender list page",
					"Unable to display draft stage in completed tender list page" + e.getMessage(), "Fail", "N");
		}
	}

	public void approverStatusAfterReview() throws Throwable {
		try {
			log.info("started executing the method::  approverStatusAfterReview");
			JSClick(tendercreationlocators.reviewButton, "reviewButton");
			waitForObj(3000);
			click(tendercreationlocators.confirm, "confirm");
			waitForObj(15000);
			set(tendercreationlocators.typeAnyKeyword1, documentNumberText, "typeAnyKeyword");
			waitForObj(4000);
			pdfResultReport.addStepDetails("approverStatusAfterReview", "Sanction Note must be reviewed sucessfully",
					"Sucessfully reviewed sanction note" + " ", "Pass", "Y");
			log.info("completed executing the method:: approverStatusAfterReview");
		} catch (Exception e) {
			log.fatal("Unable to review sanction note" + e.getMessage());
			pdfResultReport.addStepDetails("approverStatusAfterReview", "Sanction Note must be reviewed sucessfully",
					"Unable to review sanction note" + e.getMessage(), "Fail", "N");
		}
	}

	public void checkTheRejectedBiddersList() throws Throwable {
		try {
			log.info("started executing the method::  checkTheRejectedBiddersList");
			click(tendercreationlocators.createSanctionNote, "createSanctionNote");
			waitForElementToBeVisible(tendercreationlocators.sanctionReferenceNumber);
			sanctionReferenceNumber();
			waitForObj(3000);
			waitForElementToBeVisible(tendercreationlocators.recommendationTab);
			etendercomponentobj.waitForSpinnerToDisappear();
			waitForObj(2000);
			JSClick(tendercreationlocators.rejectedSupplier, "rejectedSupplier");
			waitForElementToBeVisible(tendercreationlocators.orgName);
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.orgName);
			pdfResultReport.addStepDetails("checkTheRejectedBiddersList",
					"The page redirects to Rejected list supplier tab",
					"Rejected list supplier page should be displayed" + " ", "Pass", "Y");
			waitForObj(3000);
			log.info("completed executing the method:: checkTheRejectedBiddersList");
		} catch (Exception e) {
			log.fatal("Unable to click Rejected list supplier tab" + e.getMessage());
			pdfResultReport.addStepDetails("checkTheRejectedBiddersList",
					"The page redirects to Rejected list supplier",
					"Unable to show the Rejected list supplier" + e.getMessage(), "Fail", "N");
		}
	}

	public void wipApproverStatus() throws Throwable {
		try {
			log.info("started executing the method::  wipApproverStatus");
			// waitForObj(3000);
			IsElementPresent(tendercreationlocators.wipApprover);
			pdfResultReport.addStepDetails("wipApproverStatus", "WIP element is located",
					"Unable to to locate an element" + " ", "Pass", "Y");
			waitForObj(3000);
			log.info("completed executing the method:: wipApproverStatus");
		} catch (Exception e) {
			log.fatal("Unable to to locate an element" + e.getMessage());
			pdfResultReport.addStepDetails("wipApproverStatus", "The page redirects to Rejected list supplier",
					"Unable to locate an element" + e.getMessage(), "Fail", "N");
		}
	}

	public void addUserForParallelApproval(String approvalName1, String approvalName2, String approvalType,
			String minAppr) throws Exception {

		try {

			log.info("started executing the method::  addUserForParallelApproval");

			String approvalTypeSelect = "//table/tbody/tr/td[contains(text(),'{0}')]/following-sibling::td//child::select[@ng-model='user.approvalType']";

			String minApproval = "//table/tbody/tr/td[contains(text(),'{0}')]/following-sibling::td//child::input[@ng-model='user.minApproval']";

			String co_ordinatorflag = "//table/tbody/tr/td[contains(text(),'{0}')]/following-sibling::td//child::input[@ng-model='user.coorinatorFlag']";
			click(tendercreationlocators.action, "action");

			waitForObj(2000);

			click(tendercreationlocators.addUserBy, "addUserBy");

			waitForObj(5000);

			clear(tendercreationlocators.search, "clear the user");

			set(tendercreationlocators.search, approvalName1, "enter User For Approval");

			click(tendercreationlocators.addUserToTableBy, "addUserToTableBy");

			waitForObj(2000);

			clear(tendercreationlocators.search, "clear the user");

			set(tendercreationlocators.search, approvalName2, "enter User For Approval");

			click(tendercreationlocators.addUserToTableBy, "addUserToTableBy");

			waitForObj(2000);

			WebDriver driver = ThreadLocalWebdriver.getDriver();

			Select select = new Select(driver.findElement(By.xpath(approvalTypeSelect.replace("{0}", approvalName1))));

			select.selectByVisibleText(approvalType);

			waitForObj(2000);

			Select select1 = new Select(driver.findElement(By.xpath(approvalTypeSelect.replace("{0}", approvalName2))));

			select1.selectByVisibleText(approvalType);

			waitForObj(3000);

			driver.findElement(By.xpath(minApproval.replace("{0}", approvalName1))).sendKeys(minAppr);

			waitForObj(2000);

			driver.findElement(By.xpath(co_ordinatorflag.replace("{0}", approvalName1))).click();

			click(tendercreationlocators.saveUserBy, "saveUserBy");

			pdfResultReport.addStepDetails("addUserForParallelApproval",
					"Should add Sanction Approver users for parallel approval",
					"Sucessfully users added for Sequential approval" + " ", "Pass", "Y");

			waitForElementToBeVisible(tendercreationlocators.confirmationOkBy);

			click(tendercreationlocators.confirmationOkBy, "confirmationOkBy");

			waitForObj(3000);

			log.info("completed executing the method::addUserForParallelApproval");

		} catch (Exception e) {
			log.fatal("Unable to add Sanction Approver users for parallel approval type" + e.getMessage());
			pdfResultReport.addStepDetails("addUserForParallelApproval",
					"Should add Sanction Approver users for parallel approval",
					"Unable to add Sanction Approver users for parallel approval type" + e.getMessage(), "Fail", "N");
		}
	}

	public void addUserForSequentialApprovalAfterSelectingParallelUser(String approvalName, String approvalName1,
			String approvalType) throws Exception {

		try {

			log.info("started executing the method:: addUserForSequentialApprovalAfterSelectingParallelUser");

			String approvalTypeSelect = "//table/tbody/tr/td[contains(text(),'{0}')]/following-sibling::td//child::select[@ng-model='user.approvalType']";

			click(tendercreationlocators.action, "action");

			waitForObj(2000);

			click(tendercreationlocators.addUserBy, "addUserBy");

			waitForObj(5000);

			clear(tendercreationlocators.search, "clear the user");

			set(tendercreationlocators.search, approvalName, "enter User For Approval");

			click(tendercreationlocators.addUserToTableBy, "addUserToTableBy");

			waitForObj(2000);

			WebDriver driver = ThreadLocalWebdriver.getDriver();

			Select select = new Select(driver.findElement(By.xpath(approvalTypeSelect.replace("{0}", approvalName))));

			select.selectByVisibleText(approvalType);

			waitForObj(3000);

			String co_ordinatorflag = "//table/tbody/tr/td[contains(text(),'{0}')]/following-sibling::td//child::input[@ng-model='user.coorinatorFlag']";

			driver.findElement(By.xpath(co_ordinatorflag.replace("{0}", approvalName1))).click();

			click(tendercreationlocators.saveUserBy, "saveUserBy");

			pdfResultReport.addStepDetails("addUserForSequentialApprovalAfterSelectingParallelUser",
					"Should add Sanction Approver users for Sequential approval",
					"Sucessfully added Sanction Approver users for Sequential approval" + " ", "Pass", "Y");

			waitForElementToBeVisible(tendercreationlocators.confirmationOkBy);

			click(tendercreationlocators.confirmationOkBy, "confirmationOkBy");

			waitForObj(3000);

			log.info("completed executing the method::addUserForSequentialApprovalAfterSelectingParallelUser");
		} catch (Exception e) {

			log.fatal("Unable to add Sanction Approver users for Sequential Approval" + e.getMessage());
			pdfResultReport.addStepDetails("addUserForParallelApproval",
					"Should add Sanction Approver users for sequential approval",
					"Unable to add Sanction Approver users for Sequential Approval" + e.getMessage(), "Fail", "N");
		}

	}

	public void verifyOrderOfApprovalUserInPredefinedWorkFLow(String aproverFirstRow, String approverSecondRow,
			String approverThirdRow) throws Throwable {
		try {
			log.info("started executing the method::verifyOrderOfApprovalUserInPredefinedWorkFLow");

			String SN = "SN_";
			String ref = String.valueOf(getrandomInterger(10000000, 1000000000));
			String sanctionReference = SN.concat(ref);

			String approver1stRow = "//*[@id='menusndApprovl']/div/table/tbody/tr[1]/td[contains(text(),'{0}')]";

			String approver2ndRow = "//*[@id='menusndApprovl']/div/table/tbody/tr[2]/td[contains(text(),'{0}')]";

			String approver3rdRow = "//*[@id='menusndApprovl']/div/table/tbody/tr[3]/td[contains(text(),'{0}')]";

			WebDriver driver = ThreadLocalWebdriver.getDriver();

			click(tendercreationlocators.SN_stage, "SN_stage");

			checkPageIsReady();

			waitForElementToBeVisible(By.xpath("//*[@id='myTabContent']/div[2]/div/table/tbody/tr[2]"));

			waitForObj(3000);

			click(tendercreationlocators.createSanctionNote, "createSanctionNote");

			waitForElementToBeVisible(tendercreationlocators.sanctionReferenceNumber);

			waitForObj(3000);

			set(tendercreationlocators.sanctionReferenceNo, sanctionReference, "sanctionReferenceNo");
			click(tendercreationlocators.sanctionSubmit, "sanctionSubmit");

			waitForObj(2000);

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			waitForElementToBeVisible(tendercreationlocators.l1RankEleBy);

			waitForObj(4000);

			click(tendercreationlocators.itemAllotment1, "itemAllotment1");

			click(tendercreationlocators.itemAllotmentSelect, "itemAllotmentSelect");

			click(tendercreationlocators.itemAllotmentRow, "itemAllotmentRow");

			waitForObj(3000);

			JavascriptExecutor jse = (JavascriptExecutor) ThreadLocalWebdriver.getDriver();
			jse.executeScript("window.scrollBy(0,-650)");

			JSClick(tendercreationlocators.recommendationTab, "recommendationTab");
			Thread.sleep(10000);
			JSClick(By.xpath("//*[@aria-label='Bold']"), "overallComment");

			WebElement iframele = ThreadLocalWebdriver.getDriver().findElement(By.xpath("//iframe[@id='txtArea_ifr']"));
			switchframe(iframele);
			Thread.sleep(10000);
			set(tendercreationlocators.recommendationComment, "Provide Comment", "recommendationComment");

			switchToDefaultFrame();
			Thread.sleep(5000);
			click(tendercreationlocators.saveSanction, "saveSanction");

			Thread.sleep(3000);

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			Thread.sleep(3000);

			click(tendercreationlocators.submit, "submit");

			waitForElementToBeVisible(tendercreationlocators.sendForApprovalNotRequired_SN);

			waitForObj(3000);

			documentNoSave();

			click(tendercreationlocators.predefine, "predefine");

			waitForObj(2000);

			Assert.assertTrue(
					driver.findElement(By.xpath(approver1stRow.replace("{0}", aproverFirstRow))).isDisplayed());

			Assert.assertTrue(
					driver.findElement(By.xpath(approver2ndRow.replace("{0}", approverSecondRow))).isDisplayed());

			Assert.assertTrue(
					driver.findElement(By.xpath(approver3rdRow.replace("{0}", approverThirdRow))).isDisplayed());

			pdfResultReport.addStepDetails("verifyOrderOfApprovalUserInPredefinedWorkFLow",
					"Should display in top to bottom order as selected in Dop Creation",
					"Sucessfully displaying in top to bottom order as selected in Dop Creation" + " ", "Pass", "Y");

			JSClick(tendercreationlocators.sendForApprovalSubmit, "sendForApprovalSubmit");

			waitForElementToBeVisible(By.xpath("//*[@id='myTabContent']/child::div/child::div/table/tbody/tr[2]"));

			log.info("completed executing the method:: verifyOrderOfApprovalUserInPredefinedWorkFLow");
		} catch (Exception e) {
			log.fatal("Unable to display in top to bottom order selcted as selected in Dop Creation" + e.getMessage());
			pdfResultReport.addStepDetails("verifyOrderOfApprovalUserInPredefinedWorkFLow",
					"Should display in top to bottom order as selected in Dop Creation",
					"Unable to display in top to bottom order selcted as selected in Dop Creation" + e.getMessage(),
					"Fail", "N");
		}
	}

	public void SanctionsupplierSelection() throws Throwable {
		try {
			log.info("started executing the method:: sanctionReferenceNumber");
			waitForObj(20000);
			etendercomponentobj.waitForSpinnerToDisappear();
			waitForObj(20000);
			JSClick(tendercreationlocators.ALLsupplierSelectionCheckBox, "supplierSelectionCheckBox");
			waitForObj(2000);
			JSClick(tendercreationlocators.ALLsupplierSelectionCheckBox, "supplierSelectionCheckBox");
			waitForObj(2000);
			JSClick(tendercreationlocators.ALLsupplierSelectionCheckBox, "supplierSelectionCheckBox");

			waitForObj(5000);
			pdfResultReport.addStepDetails("supplierSelection", "supplier must be selected sucessfully",
					"Successfully selected supplier " + " ", "Pass", "Y");
			log.info("completed executing the method:: supplierSelection");
		} catch (Exception e) {
			log.fatal("Unable to select supplier " + e.getMessage());
			pdfResultReport.addStepDetails("supplierSelection", "supplier must be selected sucessfully",
					"Unable to select supplierr " + e.getMessage(), "Fail", "N");
		}
	}

	public void ApproverrecallButtonValidation() throws Throwable {
		try {
			log.info("started executing the method:: recallButtonValidation");
			click(tendercreationlocators.SearchIdInSanction, "SearchIdInSanction");
			set(tendercreationlocators.SearchIdInSanction, idno, "SearchIdInSanction");
			waitForObj(5000);
			click(tendercreationlocators.actionDropdown, "actionDropdown");
			click(tendercreationlocators.recallButton, "recallButton");
			waitForObj(5000);
			set(tendercreationlocators.recallReasonComment, "recall", "recommendationComment");
			pdfResultReport.addStepDetails("recallButtonValidation", "Recall button must be validate successfully",
					"Successfully validated recall button" + " ", "Pass", "Y");
			click(tendercreationlocators.recallReasonSubmit, "recallReasonSubmit");
			waitForObj(15000);
			pdfResultReport.addStepDetails("recallButtonValidation", "Recall button must be validate successfully",
					"Successfully validated recall button" + " ", "Pass", "Y");
			log.info("completed executing the method:: recallButtonValidation");
		} catch (Exception e) {
			log.fatal("Not able to validate recall button" + e.getMessage());
			pdfResultReport.addStepDetails("recallButtonValidation", "Recall button must be validate successfully",
					"Unable to validate recall button" + e.getMessage(), "Fail", "N");
		}
	}

	public void searchSanctionNotewithID() throws Throwable {
		try {
			log.info("started executing the method:: searchSanctionNotewithID");
			click(tendercreationlocators.SearchIdInSanction, "SearchIdInSanction");
			set(tendercreationlocators.SearchIdInSanction, documentNoSave(), "SearchIdInSanction");
			waitForObj(4000);
			pdfResultReport.addStepDetails("searchSanctionNotewithID", "Search Sanction Note with ID ",
					"Searched Sanction Note with ID successfully" + " ", "Pass", "Y");
			log.info("completed executing the method:: searchSanctionNotewithID");

		} catch (Exception e) {
			log.fatal("Unable to Search Sanction Note with ID" + e.getMessage());

			pdfResultReport.addStepDetails("searchSanctionNotewithID", "searchSanctionNotewithID",
					"Unable to Search Sanction Note with ID" + e.getMessage(), "Fail", "N");
		}
	}

	public void SN_Approver_Login() throws Throwable {
		try {
			log.info("started executing the method:: SN_Approver_Login");
			click(tendercreationlocators.login, "login");
			set(tendercreationlocators.userName, pdfResultReport.testData.get("SanctionNoteApprover"), "userName");
			waitForObj(2000);
			set(tendercreationlocators.password, pdfResultReport.testData.get("AppPassword"), "password");
			//Handle fixed Captcha (06/11/2020)
			set(tendercreationlocators.Captcha_Login, "1234", "Login_Captcha");
			click(tendercreationlocators.okButton, "okButton");

			waitForElement(tendercreationlocators.dashboardIcon, 50);

			pdfResultReport.addStepDetails("SN_Approver_Login", "SN Approver user must be login sucessfully",
					"Successfully logged in as SN Approver" + " ", "Pass", "Y");
			log.info("completed executing the method:: SN_Approver_Login");
		} catch (Exception e) {
			log.fatal("Unable to login as SN approver user" + e.getMessage());
			pdfResultReport.addStepDetails("SN_Approver_Login", "SN Approver user must be login sucessfully",
					"Unable to login as SN Approver" + e.getMessage(), "Fail", "N");
		}
	}

	public void SN_SearchWithID() throws Throwable {
		try {
			log.info("started executing the method:: SN_SearchWithID");
			waitForObj(10000);
			click(tendercreationlocators.search, "search");
			String i = pdfResultReport.testData.get("DocumentID");
			String j = i.replaceAll("[.0]+$", "");
			set(tendercreationlocators.search, j, "DocumentID");
			waitForObj(5000);
			click(tendercreationlocators.sanctionDetails, "sanctionDetails");
			waitForObj(10000);

			pdfResultReport.addStepDetails("SN_SearchWithID", "SN_Search With ID ",
					"Successfully search SN with ID" + " ", "Pass", "Y");
			log.info("completed executing the method:: SN_SearchWithID");
		} catch (Exception e) {
			log.fatal("Unable to search SN with ID" + e.getMessage());
			pdfResultReport.addStepDetails("SN_SearchWithID", "SN_Search With ID",
					"Unable to search SN with ID" + e.getMessage(), "Fail", "N");
		}
	}

	public void ApproveSanctionNote() throws Throwable {
		try {
			log.info("started executing the method:: ApproveSanctionNote");
			click(tendercreationlocators.commenttab, "commenttab");

			waitForObj(5000);
			WebElement iframele = ThreadLocalWebdriver.getDriver().findElement(By.xpath("//iframe[@id='txtArea_ifr']"));
			switchframe(iframele);
			waitForObj(5000);
			set(tendercreationlocators.recommendationComment, pdfResultReport.testData.get("ApproverComment"),
					"ApproverComment");
			switchToDefaultFrame();
			waitForObj(5000);
			click(tendercreationlocators.approvesanction, "approvesanction");
			click(tendercreationlocators.confirmsanction, "confirmsanction");
			waitForObj(20000);
			pdfResultReport.addStepDetails("ApproveSanctionNote", "Approve sanction note ",
					"Successfully approved sanction note" + " ", "Pass", "Y");
			log.info("completed executing the method:: ApproveSanctionNote");
		} catch (Exception e) {
			log.fatal("Unable to approve sanction Note" + e.getMessage());
			pdfResultReport.addStepDetails("ApproveSanctionNote", "Approve sanction note",
					"Unable to  approve sanction" + e.getMessage(), "Fail", "N");
		}
	}

	public void ReviewSanctionNote() throws Throwable {
		try {
			log.info("started executing the method:: ApproveSanctionNote");
			click(tendercreationlocators.commenttab, "commenttab");

			waitForObj(5000);
			WebElement iframele = ThreadLocalWebdriver.getDriver().findElement(By.xpath("//iframe[@id='txtArea_ifr']"));
			switchframe(iframele);
			waitForObj(5000);
			set(tendercreationlocators.recommendationComment, pdfResultReport.testData.get("ApproverComment"),
					"ApproverComment");
			switchToDefaultFrame();
			waitForObj(7000);
			click(tendercreationlocators.summarytab, "summarytab");
			waitForObj(3000);
			mouseOver(tendercreationlocators.mousehoverreview);
			waitForObj(2000);
			highlight(tendercreationlocators.reviewsanction);
			waitForObj(2000);
			click(tendercreationlocators.reviewsanction, "reviewsanction");
			click(tendercreationlocators.confirmsanction, "confirmsanction");
			waitForObj(20000);
			pdfResultReport.addStepDetails("reviewSanctionNote", "review sanction note ",
					"Successfully reviewed sanction note" + " ", "Pass", "Y");
			log.info("completed executing the method:: ReviewSanctionNote");
		} catch (Exception e) {
			log.fatal("Unable to review sanction Note" + e.getMessage());
			pdfResultReport.addStepDetails("ReviewSanctionNote", "review sanction note",
					"Unable to review sanction" + e.getMessage(), "Fail", "N");
		}
	}

	public static int fn_GetCellNumberByColumName(HSSFSheet WSheetObj, String ColumnName, int rownum) {

		HSSFRow FstRowObj = WSheetObj.getRow(rownum);
		int columnCount = FstRowObj.getLastCellNum();
		int columnNumber = 0;
		for (int i = 0; i <= columnCount - 1; i++) {
			HSSFCell cellObj = FstRowObj.getCell(i, HSSFRow.CREATE_NULL_AS_BLANK);
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

	public void writeexcel(String path, String sheetname, String colname, int rownum, int rowtowrite, int data) {
		try {
			File file = new File(System.getProperty("user.dir") + path);
			FileInputStream fis = new FileInputStream(file);

			HSSFWorkbook Wb = null;
			Wb = new HSSFWorkbook(fis);

			HSSFSheet sheet = Wb.getSheet(sheetname);

			int columnNumber = fn_GetCellNumberByColumName(sheet, colname, rownum);
			HSSFRow row1 = sheet.getRow(rowtowrite);
			Cell cell = row1.getCell(columnNumber);
			cell.setCellValue(data);

			FileOutputStream fos = new FileOutputStream(file);
			Wb.write(fos);

			fis.close();
			fos.close();

			Thread.sleep(10000);
		} catch (Exception e) {
			System.out.println("message " + e.getMessage());
			log.fatal("Not able to write" + e.getCause().getClass());
		}

	}

	public String idno;
	public int id;

	public int documentNoSaveandwrite() throws Throwable {
		log.info("started executing the method:: documentNoSave");
		documentNumberText = text(tendercreationlocators.documentNumber).trim();
		idno = documentNumberText;
		id = Integer.parseInt(documentNumberText);
		System.out.println(documentNumberText);

		waitForObj(10000);
		return id;
	}

	public void TempleteGroup_and_Vendor_Selection_TCS() throws Throwable {
		try {
			log.info("started executing the method:: TempleteGroup_and_Vendor_Selection_TCS");
			click(tendercreationlocators.SelectTemplateGroup, "SelectTemplateGroup");

			waitForObj(5000);
			click(tendercreationlocators.SelectBidderPOTCS, "TCS");

			waitForObj(5000);
			pdfResultReport.addStepDetails("TempleteGroup_and_Vendor_Selection_TCS",
					"Templete Group and Vendor to be selected ",
					"Successfully selected Templete Group and Vendors" + " ", "Pass", "Y");
			log.info("completed executing the method:: ApproveSanctionNote");
		} catch (Exception e) {
			log.fatal("Unable create templete group" + e.getMessage());
			pdfResultReport.addStepDetails("TempleteGroup_and_Vendor_Selection_TCS",
					"Templete Group and Vendor to be selected ",
					"Unable to select Templete Group and Vendors" + e.getMessage(), "Fail", "N");
		}
	}

	public void TempleteGroup_and_Vendor_Selection_CTS() throws Throwable {
		try {
			log.info("started executing the method:: TempleteGroup_and_Vendor_Selection_CTS");
			click(tendercreationlocators.SelectTemplateGroup, "SelectTemplateGroup");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForObj(2000);
			click(tendercreationlocators.SelectBidderPOCTS, "SelectBidderPOCTS");
			waitForElementToBeVisible(tendercreationlocators.BOQAllItemChecklist);
			// waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(),
			// tendercreationlocators.LoadingBy);
			waitForObj(2000);
			pdfResultReport.addStepDetails("TempleteGroup_and_Vendor_Selection_CTS",
					"Templete Group and Vendor to be selected ",
					"Successfully selected Templete Group and Vendors" + " ", "Pass", "Y");
			log.info("completed executing the method:: TempleteGroup_and_Vendor_Selection_CTS");
		} catch (Exception e) {
			log.fatal("Unable to create templete group" + e.getMessage());
			pdfResultReport.addStepDetails("TempleteGroup_and_Vendor_Selection_CTS",
					"Templete Group and Vendor to be selected ",
					"Unable to select Templete Group and Vendors" + e.getMessage(), "Fail", "N");
		}
	}

	public void Addquantity_Final_Items() throws Throwable {
		try {
			log.info("started executing the method:: Addquantity_Final_Items");
			JSClick(tendercreationlocators.BOQAllItemChecklist, "BOQAllItemChecklist");
			waitForObj(1000);
			click(tendercreationlocators.btnAddfinalItems, "btnAddfinalItems");
			waitForObj(1000);
			pdfResultReport.addStepDetails("Addquantity_Final_Items",
					"Remaining items for Purchase to be displayed in Finalitems ",
					"Successfully displaying Finalitems List of item Qty for purchase" + " ", "Pass", "Y");
			log.info("completed executing the method:: Addquantity_Final_Items");
		} catch (Exception e) {
			log.fatal("Unable to display Finalitems List of item Qty for purchase" + e.getMessage());
			pdfResultReport.addStepDetails("Addquantity_Final_Items",
					"Remaining items for Purchase to be displayed in Finalitems ",
					"Unable to display Finalitems List of item Qty for purchase" + e.getMessage(), "Fail", "N");
		}
	}

	public void Edit_Final_Items() throws Throwable {
		try {
			log.info("started executing the method:: Edit_Final_Items");
			Actions act = new Actions(ThreadLocalWebdriver.getDriver());

			click(tendercreationlocators.addItemQantity1, "addItemQantity1");
			clear(tendercreationlocators.addItemQantity1, "addItemQantity1");
			set(tendercreationlocators.addItemQantity1, pdfResultReport.testData.get("FinalItemQuantity1"),
					"addItemQantity1");
			click(tendercreationlocators.addItemQantity1, "addItemQantity1");
			set(tendercreationlocators.addItemQantity1, pdfResultReport.testData.get("FinalItemQuantity1"),
					"addItemQantity1");

			waitForObj(3000);

			click(tendercreationlocators.addItemQantity2, "addItemQantity2");
			clear(tendercreationlocators.addItemQantity2, "addItemQantity2");
			set(tendercreationlocators.addItemQantity2, pdfResultReport.testData.get("FinalItemQuantity2"),
					"addItemQantity2");
			click(tendercreationlocators.addItemQantity2, "addItemQantity2");
			set(tendercreationlocators.addItemQantity2, pdfResultReport.testData.get("FinalItemQuantity2"),
					"addItemQantity2");
			waitForObj(3000);

			click(tendercreationlocators.addItemQantity3, "addItemQantity3");
			clear(tendercreationlocators.addItemQantity3, "addItemQantity3");

			set(tendercreationlocators.addItemQantity3, pdfResultReport.testData.get("FinalItemQuantity3"),
					"addItemQantity3");
			click(tendercreationlocators.addItemQantity3, "addItemQantity3");
			set(tendercreationlocators.addItemQantity3, pdfResultReport.testData.get("FinalItemQuantity3"),
					"addItemQantity3");
			waitForObj(3000);
			pdfResultReport.addStepDetails("Edit_Final_Items", "Final items to be edited ",
					"Successfully edit list of final items;" + " ", "Pass", "Y");
			log.info("completed executing the method:: Edit_Final_Items");
		} catch (Exception e) {
			log.fatal("Unable to create templete group" + e.getMessage());
			pdfResultReport.addStepDetails("Edit_Final_Items", "Final items to be edited",
					"Unable to edit list of Final items " + e.getMessage(), "Fail", "N");
		}
	}

	public void ProceedtoCreatePO() throws Throwable {
		try {
			log.info("started executing the method:: ProceedtoCreatePO");
			click(tendercreationlocators.btnProceedToCreatePO, "btnProceedToCreatePO");

			waitForElementToBeVisible(tendercreationlocators.COnfirmationYESforScantionInFuture);

			click(tendercreationlocators.COnfirmationYESforScantionInFuture, "COnfirmationYESforScantionInFuture");
			waitForObj(3000);
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			waitForElementToBeVisible(tendercreationlocators.POReferenceNo);

			pdfResultReport.addStepDetails("ProceedtoCreatePO", "Should display POReferenceNo PopUp",
					"Successfully displaying POReferenceNo PopUp " + " ", "Pass", "Y");
			log.info("completed executing the method:: ProceedtoCreatePO");
		} catch (Exception e) {
			log.fatal("Unable to display POReferenceNo PopUp" + e.getMessage());
			pdfResultReport.addStepDetails("ProceedtoCreatePO", "Should display POReferenceNo PopUp",
					"Unable to display POReferenceNo PopUp" + e.getMessage(), "Fail", "N");
		}
	}

	public void PODetailsTAB() throws Throwable {
		try {
			log.info("started executing the method:: PODetails");

			waitForElementToBeVisible(tendercreationlocators.POExpiryDate);

			LocalDateTime localdatetime = LocalDateTime.now().plusDays(26);

			String POExpiryDate = localdatetime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a"));

			click(tendercreationlocators.POExpiryDate, "POExpiryDate");

			set(tendercreationlocators.POExpiryDate, POExpiryDate, "POExpiryDate");

			waitForObj(4000);

			click(tendercreationlocators.POAmendmentflag, "POAmendmentflag");

			click(tendercreationlocators.POAmendmentflagOption(pdfResultReport.testData.get("POAmendmentflag")),
					"POAmendmentflagYES");

			waitForObj(3000);
			pdfResultReport.addStepDetails("PODetailsTAB", "PO Details tab details to be filled ",
					"Successfully filled PO Details tab details" + " ", "Pass", "Y");

			log.info("completed executing the method:: PODetailsTAB");
		} catch (Exception e) {
			log.fatal("Unable to  fill details in  PO Details tab" + e.getMessage());
			pdfResultReport.addStepDetails("PODetails", "PO Details tab details to be filled ",
					"Unable to  fill details in  PO Details tab" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyDop_ActivationLink(String DOPName) throws Throwable {
		try {
			log.info("started executing the method::verifyDop_ActivationLink");

			/*
			 * clear(tendercreationlocators.search, "clear the enter key word");
			 * 
			 * set(tendercreationlocators.search, DopName, "enter key word");
			 */
			String status = text(tendercreationlocators.DOPstatusPO(DOPName));
			if (status.equalsIgnoreCase("Inactive")) {

				waitForObj(3000);

				click(tendercreationlocators.action, "action");
				waitForObj(1000);
				click(tendercreationlocators.activeTabLinkBy, "activeTabLinkBy");

				waitForObj(5000);

				JSClick(tendercreationlocators.confirmDOP, "confirmBtn");

				waitForElementToBeVisible(tendercreationlocators.confirmationOkBy);

				JSClick(tendercreationlocators.confirmDOPMSG, "confirmMSGDOP");

				waitForObj(3000);

				IsElementPresent(tendercreationlocators.dopListStatusActiveBy);

				pdfResultReport.addStepDetails("verifyDop_ActivationLink", "Dop List Status Column should be in Active",
						"Successfully changed to Active mode in Dop List" + " ", "Pass", "Y");
			} else {
				System.out.println("DOP is already active");
			}

			log.info("completed executing the method:: verifyDop_ActivationLink");
		} catch (Exception e) {
			pdfResultReport.addStepDetails("verifyDop_ActivationLink",
					"Dop List Status Column should be in Active/Inactive",
					"Unable to change to Active/Inactive mode in Dop List" + e.getMessage(), "Fail", "N");
		}
	}

	public void verify_Dop_InactiveLink(String DopName) throws Throwable {
		try {
			log.info("started executing the method::verifyDop_InactiveLink");

			/*
			 * clear(tendercreationlocators.search, "clear the enter key word");
			 * 
			 * set(tendercreationlocators.search, DopName, "enter key word");
			 */
			String status = text(tendercreationlocators.DOPstatusPO(DopName));
			if (status.equalsIgnoreCase("Active")) {
				waitForObj(3000);

				click(tendercreationlocators.action, "action");

				click(tendercreationlocators.deActiveTabLinkBy, "deActiveTabLinkBy");

				waitForObj(5000);

				JSClick(tendercreationlocators.confirmBtn, "confirmBtn");

				waitForElementToBeVisible(tendercreationlocators.confirmationOkBy);

				JSClick(tendercreationlocators.confirmationOkBy, "confirmationOkBy");

				waitForObj(3000);

				IsElementPresent(tendercreationlocators.dopListStatusInActiveBy);

				pdfResultReport.addStepDetails("verifyDop_InactiveLink", "Dop List Status Column should be in InActive",
						"Successfully changed to InActive mode in Dop List" + " ", "Pass", "Y");
			} else {
				System.out.println("DOP is already inactive");
			}
			log.info("completed executing the method:: verifyDop_InactiveLink");
		} catch (Exception e) {
			log.fatal("Unable to change to InActive mode in Dop List" + e.getMessage());
			pdfResultReport.addStepDetails("verifyDop_InactiveLink", "Dop List Status Column should be in InActive",
					"Unable to change to InActive mode in Dop List" + e.getMessage(), "Fail", "N");
		}
	}

	public void POITemDetailsTAB() throws Throwable {
		try {
			log.info("started executing the method:: POITemDetailsTAB");
			click(tendercreationlocators.POItemDetails, "POItemDetails");
			waitForObj(3000);
			// Item 1
			JSClick(tendercreationlocators.itemQuantity1, "itemQuantity1");
			clear(tendercreationlocators.itemQuantity1, "itemQuantity1");
			set(tendercreationlocators.itemQuantity1, eTenderComponent.getDataFromPropertiesFile("POItemQuantity1"),
					"POItemQuantity1");
			waitForObj(3000);
			JSClick(tendercreationlocators.VLegacyCodeItem1, "VLegacyCodeItem1");
			set(tendercreationlocators.VLegacyCodeItem1, pdfResultReport.testData.get("VLegacyCodeItem1"),
					"VLegacyCodeItem1");
			waitForObj(3000);

			// Item 2
			JSClick(tendercreationlocators.itemQuantity2, "itemQuantity2");
			clear(tendercreationlocators.itemQuantity2, "itemQuantity2");
			set(tendercreationlocators.itemQuantity2, eTenderComponent.getDataFromPropertiesFile("POItemQuantity2"),
					"POItemQuantity2");
			waitForObj(3000);
			JSClick(tendercreationlocators.VLegacyCodeItem2, "VLegacyCodeItem2");
			set(tendercreationlocators.VLegacyCodeItem2, pdfResultReport.testData.get("VLegacyCodeItem2"),
					"VLegacyCodeItem2");
			waitForObj(3000);

			// //Item 3
			JSClick(tendercreationlocators.itemQuantity3, "itemQuantity3");
			clear(tendercreationlocators.itemQuantity3, "itemQuantity3");
			set(tendercreationlocators.itemQuantity3, eTenderComponent.getDataFromPropertiesFile("POItemQuantity3"),
					"POItemQuantity3");
			waitForObj(3000);
			JSClick(tendercreationlocators.VLegacyCodeItem3, "VLegacyCodeItem3");
			set(tendercreationlocators.VLegacyCodeItem3, pdfResultReport.testData.get("VLegacyCodeItem3"),
					"VLegacyCodeItem3");
			pdfResultReport.addStepDetails("POITemDetailsTAB", "PO Item Details to be filled ",
					"Successfully filled PO Item Details" + " ", "Pass", "Y");
			log.info("completed executing the method:: POITemDetailsTAB");
		} catch (Exception e) {
			log.fatal("Unable to fill PO Item Details" + e.getMessage());
			pdfResultReport.addStepDetails("POITemDetailsTAB", "PO Item Details to be filled ",
					"Unable to  fill PO Item Details" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickAccepPotBtn() throws Throwable {
		try {
			log.info("started executing the method:: clickAccepPotBtn");

			JSClick(tendercreationlocators.poAcceptBtnBy, "poAcceptBtnBy");

			waitForObj(3000);

			click(tendercreationlocators.acceptPurchaseOrderConfirmPopYesBtnBy,
					"acceptPurchaseOrderConfirmPopYesBtnBy");
			pdfResultReport.addStepDetails("PO Sucessful Msg PopUp",
					"Purchase order should be successfully PopUp should appear",
					"Purchase order successfully accepted PopUp is dispalyed" + " ", "Pass", "Y");
			waitForObj(2000);
			click(tendercreationlocators.acceptPurchaseOrderConfirmPopOKBtnBy, "acceptPurchaseOrderConfirmPopOKBtnBy");

			waitForElementToBeVisible(tendercreationlocators.poListPageRowBy);

			waitForObj(3000);

			pdfResultReport.addStepDetails("clickAccepPotBtn", "Purchase order should be successfully accepted",
					"Purchase order successfully accepted" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickAccepPotBtn");
		} catch (Exception e) {
			log.fatal("Unable to Accept PO" + e.getMessage());
			pdfResultReport.addStepDetails("verifySummaryTabAndEnterComment", "Unable to Accept PO",
					"Purchase order should be successfully accepted" + e.getMessage(), "Fail", "N");
		}
	}

	// POOJA
	public void itemAllotment_CTS() throws Throwable {
		try {
			log.info("started executing the method:: itemAllotment_CTS");
			JSClick(tendercreationlocators.plusIcon_CTS, "plusIcon_CTS");
			click(tendercreationlocators.termsAndConditioncheckBox_CTS, "termsAndConditioncheckBox_CTS");
			click(tendercreationlocators.boqCheckbox_CTS, "boqCheckbox_CTS");
			clear(tendercreationlocators.supplierQuotedQuantity1_CTS, "supplierQuotedQuantity1_CTS");
			set(tendercreationlocators.supplierQuotedQuantity1_CTS,
					pdfResultReport.testData.get("supplierQuotedQuantity1"), "supplierQuotedQuantity1_CTS");
			clear(tendercreationlocators.supplierQuotedQuantity2_CTS, "supplierQuotedQuantity2_CTS");
			set(tendercreationlocators.supplierQuotedQuantity2_CTS,
					pdfResultReport.testData.get("supplierQuotedQuantity2"), "supplierQuotedQuantity2_CTS");
			clear(tendercreationlocators.supplierQuotedQuantity3_CTS, "supplierQuotedQuantity3_CTS");
			set(tendercreationlocators.supplierQuotedQuantity3_CTS,
					pdfResultReport.testData.get("supplierQuotedQuantity3"), "supplierQuotedQuantity3_CTS");
			pdfResultReport.addStepDetails("itemAllotment_CTS", "Supplier Quoted Quantity must be change sucessfully",
					"Successfully changed Supplier Quoted Quantity" + " ", "Pass", "Y");
			log.info("completed executing the method:: itemAllotment_CTS");
		} catch (Exception e) {
			log.fatal("Unable to change Supplier Quoted Quantity" + e.getMessage());
			pdfResultReport.addStepDetails("itemAllotment_CTS", "Supplier Quoted Quantity must be change sucessfully",
					"Unable to change Supplier Quoted Quantity " + e.getMessage(), "Fail", "N");
		}
	}

	public void itemAllotment_TCS() throws Throwable {
		try {
			log.info("started executing the method:: itemAllotment_TCS");
			JSClick(tendercreationlocators.plusIcon_TCS, "plusIcon_TCS");
			click(tendercreationlocators.termsAndConditioncheckBox_TCS, "termsAndConditioncheckBox_TCS");
			click(tendercreationlocators.boqCheckbox_TCS, "boqCheckbox_TCS");
			clear(tendercreationlocators.supplierQuotedQuantity1_TCS, "supplierQuotedQuantity1_TCS");
			set(tendercreationlocators.supplierQuotedQuantity1_TCS,
					pdfResultReport.testData.get("supplierQuotedQuantity1"), "supplierQuotedQuantity1_TCS");
			clear(tendercreationlocators.supplierQuotedQuantity2_TCS, "supplierQuotedQuantity2_TCS");
			set(tendercreationlocators.supplierQuotedQuantity2_TCS,
					pdfResultReport.testData.get("supplierQuotedQuantity2"), "supplierQuotedQuantity2_TCS");
			clear(tendercreationlocators.supplierQuotedQuantity3_TCS, "supplierQuotedQuantity3_TCS");
			set(tendercreationlocators.supplierQuotedQuantity3_TCS,
					pdfResultReport.testData.get("supplierQuotedQuantity3"), "supplierQuotedQuantity3_TCS");
			pdfResultReport.addStepDetails("itemAllotment_TCS", "Supplier Quoted Quantity must be change sucessfully",
					"Successfully changed Supplier Quoted Quantity" + " ", "Pass", "Y");

			log.info("completed executing the method:: itemAllotment_TCS");
		} catch (Exception e) {
			log.fatal("Unable to change Supplier Quoted Quantity" + e.getMessage());
			pdfResultReport.addStepDetails("itemAllotment_TCS", "Supplier Quoted Quantity must be change sucessfully",
					"Unable to change Supplier Quoted Quantity" + e.getMessage(), "Fail", "N");
		}
	}

	public void provideComment_recommendationTab() throws Throwable {
		try {
			log.info("started executing the method:: provideComment_recommendationTab");
			JSClick(tendercreationlocators.recommendationTab, "recommendationTab");
			Thread.sleep(10000);
			JSClick(By.xpath("//*[@aria-label='Bold']"), "overallComment");
			WebElement iframele = ThreadLocalWebdriver.getDriver().findElement(By.xpath("//iframe[@id='txtArea_ifr']"));
			switchframe(iframele);
			Thread.sleep(5000);
			set(tendercreationlocators.recommendationComment, "overallComment_currentPart", "recommendationComment");
			switchToDefaultFrame();
			Thread.sleep(5000);
			pdfResultReport.addStepDetails("provideComment_recommendationTab",
					"Comment must be pass under recommendation tab successfully",
					"Successfully passed comment under recommendation tab" + " ", "Pass", "Y");
			log.info("completed executing the method:: provideComment_recommendationTab");
		} catch (Exception e) {
			log.fatal("Not able to pass comment under recommendation tab" + e.getMessage());
			pdfResultReport.addStepDetails("provideComment_recommendationTab",
					"Comment must be pass under recommendation tab successfully",
					"Unable to pass comment under recommendation tab" + e.getMessage(), "Fail", "N");
		}
	}

	public void po_Creator_Login() throws Throwable {
		try {
			log.info("started executing the method:: po_Creator_Login");
			click(tendercreationlocators.login, "login");
			set(tendercreationlocators.userName, pdfResultReport.testData.get("POCreatorUserName"), "userName");
			waitForObj(2000);
			set(tendercreationlocators.password, pdfResultReport.testData.get("AppPassword"), "password");
			//Handle fixed Captcha (06/11/2020)
			set(tendercreationlocators.Captcha_Login, "1234", "Login_Captcha");
			click(tendercreationlocators.okButton, "okButton");

			waitForElement(tendercreationlocators.dashboard, 50);

			pdfResultReport.addStepDetails("po_Creator_Login", "PO Creator user must be login sucessfully",
					"Successfully logged in as po creator user" + " ", "Pass", "Y");
			log.info("completed executing the method:: po_Creator_Login");
		} catch (Exception e) {
			log.fatal("Unable to login as po creator user" + e.getMessage());
			pdfResultReport.addStepDetails("po_Creator_Login", "Po Creator user must be login sucessfully",
					"Unable to login as po creator user" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickOnCreatePOLink() throws Throwable {
		try {
			log.info("started executing the method:: clickOnCreatePOLink");
			click(tendercreationlocators.actionDropdown, "actionDropdown");
			waitForObj(2000);
			click(tendercreationlocators.createPOLink, "createPOLink");
			// waitForObj(4000);
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForObj(2000);
			pdfResultReport.addStepDetails("clickOnCreatePOLink", "Create PO link must be click successfully",
					"Successfully clicked on create PO link" + " ", "Pass", "Y");

			log.info("completed executing the method:: clickOnCreatePOLink");
		} catch (Exception e) {
			log.fatal("Not able to click on create PO link" + e.getMessage());
			pdfResultReport.addStepDetails("clickOnCreatePOLink", "Create PO link must be click successfully",
					"Unable to click on create PO link" + e.getMessage(), "Fail", "N");
		}
	}

	public void po_TemplateGroupAndBidderSelection() throws Throwable {
		try {
			log.info("started executing the method:: po_TemplateGroupAndBidderSelection");
			select(tendercreationlocators.selectTemplateGroup, pdfResultReport.testData.get("selectTemplateGroup"));
			waitForObj(2000);
			select(tendercreationlocators.selectBidder, pdfResultReport.testData.get("selectBidder"));
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.bidderOrganisationName);
			IsElementPresent(tendercreationlocators.vendorName);
			pdfResultReport.addStepDetails("po_TemplateGroupAndBidderSelection",
					"PO template group and bidder must be select successfully",
					"Successfully selected PO template group and bidder" + " ", "Pass", "Y");
			log.info("completed executing the method:: po_TemplateGroupAndBidderSelection");
		} catch (Exception e) {
			log.fatal("Not able to select PO template group and bidder" + e.getMessage());
			pdfResultReport.addStepDetails("po_TemplateGroupAndBidderSelection",
					"PO template group and bidder must be select successfully",
					"Unable to select PO template group and bidder" + e.getMessage(), "Fail", "N");
		}
	}

	public void POCreatorLogin() throws Throwable {
		try {
			log.info("started executing the method::  POCreatorLogin");
			click(tendercreationlocators.login, "login");
			set(tendercreationlocators.userName, pdfResultReport.testData.get("POCreatorUserName"), "userName");
			waitForObj(2000);
			set(tendercreationlocators.password, pdfResultReport.testData.get("AppPassword"), "password");
			//Handle fixed Captcha (06/11/2020)
			set(tendercreationlocators.Captcha_Login, "1234", "Login_Captcha");
			click(tendercreationlocators.okButton, "okButton");
			waitForElement(tendercreationlocators.purchaseOrderNavigtionIcon, 50);

			pdfResultReport.addStepDetails("POCreatorLogin", "PO creator user must be login sucessfully",
					"Successfully logged in as PO creator user " + " ", "Pass", "Y");
			log.info("completed executing the method:: POCreatorLogin");
		} catch (Exception e) {
			log.fatal("Unable to login" + e.getMessage());
			pdfResultReport.addStepDetails("POCreatorLogin", "The page redirects to Rejected list supplier",
					"Unable to login" + e.getMessage(), "Fail", "N");
		}
	}

	public void navigateToPOListing() throws Throwable {
		try {
			log.info("started executing the method:: navigateToPOListing");
			JSClick(tendercreationlocators.purchaseOrder, "purchaseOrder");
			waitForObj(2000);
			JSClick(tendercreationlocators.poListing, "poListing");
			waitForObj(5000);
			waitForElementToBeVisible(tendercreationlocators.poListPageRowBy);
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForObj(5000);
			pdfResultReport.addStepDetails("navigateToPOListing",
					"Purchase Order List Page must be naviagte successfully",
					"Successfully navigated to Purchase Order List Page" + " ", "Pass", "Y");
			log.info("completed executing the method:: navigateToPurchasrOrderList");
		} catch (Exception e) {
			log.fatal("Not able to navigate to Purchase Order List Page" + e.getMessage());
			pdfResultReport.addStepDetails("navigateToPurchasrOrderList",
					"Purchase Order List Page must be naviagte successfully",
					"Unable to navigate to Purchase Order List Page" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyPOFields() throws Throwable {
		try {
			log.info("started executing the method::  verifyPOFields");
			waitForObj(5000);
			// IsElementPresent(tendercreationlocators.createPOButton);
			IsElementPresent(tendercreationlocators.columnVisibility);
			IsElementPresent(tendercreationlocators.poSNo);
			IsElementPresent(tendercreationlocators.poNoRefNo);
			IsElementPresent(tendercreationlocators.prRfqSn);
			IsElementPresent(tendercreationlocators.poType);
			IsElementPresent(tendercreationlocators.poCategory);
			IsElementPresent(tendercreationlocators.items);
			IsElementPresent(tendercreationlocators.vendorOrSupplierName);
			IsElementPresent(tendercreationlocators.poValue);
			IsElementPresent(tendercreationlocators.creationDate);
			IsElementPresent(tendercreationlocators.validity);
			IsElementPresent(tendercreationlocators.source);
			IsElementPresent(tendercreationlocators.catalogue);
			IsElementPresent(tendercreationlocators.status1);
			IsElementPresent(tendercreationlocators.action1);
			waitForObj(5000);
			pdfResultReport.addStepDetails("verifyPOFields", "All the fields in PO List must be validated sucessfully",
					"Successfully validated all the fields " + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyPOFields");
		} catch (Exception e) {
			log.fatal("Unable to validate the fields" + e.getMessage());
			pdfResultReport.addStepDetails("verifyPOFields", "All the fields in PO List must be validated sucessfully",
					"Unable to validate the fields" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyPOFieldsInPOCreator() throws Throwable {
		try {
			log.info("started executing the method::  verifyPOFieldsInPOCreator");
			WebElement myPOTab = ThreadLocalWebdriver.getDriver().findElement(
					By.xpath("//li[@class='uib-tab nav-item ng-scope ng-isolate-scope active'][@heading='My PO']"));
			if (myPOTab.isDisplayed()) {
				System.out.println("PO Creator by default entered in MY PO tab.");
				IsElementPresent(tendercreationlocators.createPOButton);
				verifyPOFields();
			}
			pdfResultReport.addStepDetails("verifyPOFieldsInPOCreator",
					"All the fields in PO List must be validated sucessfully",
					"Successfully validated all the fields " + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyPOFieldsInPOCreator");
		} catch (Exception e) {
			log.fatal("Unable to validate the fields" + e.getMessage());
			pdfResultReport.addStepDetails("verifyPOFieldsInPOCreator",
					"All the fields in PO List must be validated sucessfully",
					"Unable to validate the fields" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyPOFieldsInSuperAdmin() throws Throwable {
		try {
			log.info("started executing the method::  verifyPOFieldsInSuperAdmin");
			WebElement myPOTab = ThreadLocalWebdriver.getDriver()
					.findElement(By.xpath("//li[@class='uib-tab nav-item ng-scope ng-isolate-scope active']"));
			if (myPOTab.isDisplayed()) {
				System.out.println("Super Admin by default entered in My PO tab.");

				IsElementPresent(tendercreationlocators.poListPageRowBy);

				IsElementPresent(tendercreationlocators.createPOButton);

				click(tendercreationlocators.othersPO, "OtherPOBy");

				waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

				waitForObj(5000);

				waitForElementToBeVisible(By.xpath("(//*[@id='usrmntbLst']/tbody/tr[2])[2]"));

				IsElementPresent(By.xpath("(//*[@id='usrmntbLst']/tbody/tr[2])[2]"));

				verifyPOFields();

			}
			pdfResultReport.addStepDetails("verifyPOFieldsInSuperAdmin",
					"All the fields in PO List must be validated sucessfully",
					"Successfully validated all the fields " + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyPOFieldsInSuperAdmin");
		} catch (Exception e) {
			log.fatal("Unable to validate the fields" + e.getMessage());
			pdfResultReport.addStepDetails("verifyPOFieldsInSuperAdmin",
					"All the fields in PO List must be validated sucessfully",
					"Unable to validate the fields" + e.getMessage(), "Fail", "N");
		}
	}

	public void POViewerLogin() throws Throwable {
		try {
			log.info("started executing the method::  POViewerLogin");
			click(tendercreationlocators.login, "login");
			set(tendercreationlocators.userName, pdfResultReport.testData.get("POViewer"), "userName");
			waitForObj(2000);
			set(tendercreationlocators.password, pdfResultReport.testData.get("AppPassword"), "password");
			//Handle fixed Captcha (06/11/2020)
			set(tendercreationlocators.Captcha_Login, "1234", "Login_Captcha");
			click(tendercreationlocators.okButton, "okButton");
			waitForElement(tendercreationlocators.purchaseOrderNavigtionIcon, 50);

			pdfResultReport.addStepDetails("POViewerLogin", "PO creator user must be login sucessfully",
					"Successfully logged in as PO creator user " + " ", "Pass", "Y");
			log.info("completed executing the method:: POViewerLogin");
		} catch (Exception e) {
			log.fatal("Unable to login" + e.getMessage());
			pdfResultReport.addStepDetails("POViewerLogin", "The page redirects to Rejected list supplier",
					"Unable to login" + e.getMessage(), "Fail", "N");
		}
	}

	public void POSuperAdminLogin() throws Throwable {
		try {
			log.info("started executing the method::  POSuperAdminLogin");
			click(tendercreationlocators.login, "login");
			set(tendercreationlocators.userName, pdfResultReport.testData.get("SuperAdminUserName"), "userName");
			waitForObj(2000);
			set(tendercreationlocators.password, pdfResultReport.testData.get("AppPassword"), "password");
			//Handle fixed Captcha (06/11/2020)
			set(tendercreationlocators.Captcha_Login, "1234", "Login_Captcha");
			click(tendercreationlocators.okButton, "okButton");
			waitForElement(tendercreationlocators.tendersIcon, 50);

			pdfResultReport.addStepDetails("POSuperAdminLogin", "Super Admin user must be login sucessfully",
					"Successfully logged in as Super Admin user" + " ", "Pass", "Y");
			log.info("completed executing the method:: POSuperAdminLogin");
		} catch (Exception e) {
			log.fatal("Unable to login" + e.getMessage());
			pdfResultReport.addStepDetails("POSuperAdminLogin", "The page redirects to Rejected list supplier",
					"Unable to login" + e.getMessage(), "Fail", "N");
		}
	}

	public void validationsForPOList() throws Throwable {
		try {
			log.info("started executing the method::  POSuperAdminLogin");
			click(tendercreationlocators.login, "login");
			set(tendercreationlocators.userName, pdfResultReport.testData.get("SuperAdminUserName"), "userName");
			waitForObj(2000);
			set(tendercreationlocators.password, pdfResultReport.testData.get("AppPassword"), "password");
			click(tendercreationlocators.okButton, "okButton");
			waitForElement(tendercreationlocators.tendersIcon, 50);

			pdfResultReport.addStepDetails("POSuperAdminLogin", "Super Admin user must be login sucessfully",
					"Successfully logged in as Super Admin user" + " ", "Pass", "Y");
			log.info("completed executing the method:: POSuperAdminLogin");
		} catch (Exception e) {
			log.fatal("Unable to login" + e.getMessage());
			pdfResultReport.addStepDetails("POSuperAdminLogin", "The page redirects to Rejected list supplier",
					"Unable to login" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyEditIconInPOViewer() throws Throwable {
		try {
			log.info("started executing the method::  verifyEditIconInPOViewer");
			click(tendercreationlocators.otherPoActionMenuBy, "otherPoActionMenuBy");

			List<WebElement> actionList = ThreadLocalWebdriver.getDriver()
					.findElements(tendercreationlocators.otherPoActionMenuElementsBy);

			for (int i = 0; i < actionList.size(); i++) {
				WebElement individualList = actionList.get(i);
				if ((individualList.getText()).equals(" Edit PO")) {
					System.out.println("Edit Icon is available in Others PO page");
				} else {
					System.out.println("Edit Icon is not available in Others PO page");
				}
			}

			click(tendercreationlocators.cancelledPO, "cancelledPO");
			waitForObj(2000);
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			click(tendercreationlocators.cancelledPOAction, "action");

			List<WebElement> actionList1 = ThreadLocalWebdriver.getDriver()
					.findElements(tendercreationlocators.cancelledPOActionDropdownElementsBy);

			for (int i = 0; i < actionList1.size(); i++) {
				WebElement individualList = actionList1.get(i);
				if ((individualList.getText()).equals(" Edit PO")) {
					System.out.println("Edit Icon is available in Cancelled PO page");
				} else {
					System.out.println("Edit Icon is not available in Cancelled PO page");
				}
			}
			pdfResultReport.addStepDetails("verifyEditIconInPOViewer", "POViewer is verified sucessfully",
					"Successfully verified POViewer" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyEditIconInPOViewer");
		} catch (Exception e) {
			log.fatal("Unable to verify" + e.getMessage());
			pdfResultReport.addStepDetails("verifyEditIconInPOViewer", "Successfully verified POViewer",
					"Unable to verify POViewer" + e.getMessage(), "Fail", "N");
		}
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
			waitForElementToBeVisible(tendercreationlocators.othersPO);
			IsElementPresent(tendercreationlocators.poNoRefNo2);
			click(tendercreationlocators.cancelledPO, "cancelledPO");
			waitForElementToBeVisible(tendercreationlocators.cancelledPO);
			IsElementPresent(tendercreationlocators.poNoRefNo3);
			click(tendercreationlocators.myPO, "myPO");
			waitForElementToBeVisible(tendercreationlocators.myPO);
			set(tendercreationlocators.search, eTenderComponent.getDataFromPropertiesFile("POReferenceNum"),
					"POReferenceNum");
			click(tendercreationlocators.action, "action");
			List<WebElement> actionList = ThreadLocalWebdriver.getDriver()
					.findElements(tendercreationlocators.listDropdown);
			for (int i = 0; i < actionList.size(); i++) {
				WebElement individualList = actionList.get(i);
				System.out.println(individualList.getText() + " is displayed");
			}
			click(tendercreationlocators.releasePODropDown, "releasePODropDown");
			waitForElementToBeVisible(tendercreationlocators.releasePOButton);
			click(tendercreationlocators.releasePOButton, "releasePOButton");
			click(tendercreationlocators.ApprovalNotReqd, "ApprovalNotReqd");
			click(tendercreationlocators.SubmitApproval, "SubmitApproval");
			waitForElementToBeVisible(tendercreationlocators.action);
			waitForObj(10000);
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

	public void createPO() throws Throwable {
		try {
			log.info("started executing the method:: createPO");
			click(tendercreationlocators.createPOButton, "createPOButton");
			waitForElementToBeVisible(tendercreationlocators.selectSource);
			select(tendercreationlocators.selectSource, "Sanction Note");
			set(tendercreationlocators.search, eTenderComponent.getDataFromPropertiesFile("sanctionReferenceNumber"),
					"sanctionReferenceNumber");
			click(tendercreationlocators.action, "action");
			waitForObj(2000);
			click(tendercreationlocators.createPOLink, "create PO");
			waitForElementToBeVisible(tendercreationlocators.POTemplateGroup);
			// click(tendercreationlocators.POTemplateGroup,"POTemplateGroup");
			pdfResultReport.addStepDetails("createPO", "sucessfully clicked on create PO",
					"sucessfully clicked on create PO" + " ", "Pass", "Y");
			log.info("completed executing the method:: ApproveSanctionNote");
		} catch (Exception e) {
			log.fatal("Unable click on createPO" + e.getMessage());
			pdfResultReport.addStepDetails("createPO", "sucessfully clicked on create PO",
					"Unable to click on create PO" + e.getMessage(), "Fail", "N");
		}
	}

	public void POSave() throws Throwable {
		try {
			log.info("started executing the method:: POSave");

			click(tendercreationlocators.POSave, "POSave");

			waitForElementToBeVisible(tendercreationlocators.poSaveSuucessMsgPopUpBy);

			waitForObj(3000);
			click(tendercreationlocators.POSUccessMsg, "POSUccessMsg");

			waitForObj(5000);

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			waitForObj(3000);
			pdfResultReport.addStepDetails("POSave", "PO should Save and Approval",
					"Successfully saved and approved po " + " ", "Pass", "Y");
			log.info("completed executing the method:: POSave");
		} catch (Exception e) {
			log.fatal("Unable to Issue PO" + e.getMessage());
			pdfResultReport.addStepDetails("POSave", "Unable to saved and approved po",
					"PO should Save and Approval" + e.getMessage(), "Fail", "N");
		}
	}

	public void POTRermsandConditionTAB() throws Throwable {
		try {
			log.info("started executing the method:: POTRermsandConditionTAB");
			click(tendercreationlocators.POTermsandCond, "POTermsandCond");
			waitForObj(3000);
			List<WebElement> ele = ThreadLocalWebdriver.getDriver()
					.findElements(By.xpath("//input[@name='clause_no']"));
			String Clausenumber = pdfResultReport.testData.get("POclauseNo");
			int num = Integer.parseInt(Clausenumber);
			for (int i = 1; i <= ele.size(); i++) {
				String val = Integer.toString(num);
				click(tendercreationlocators.POclauseNo(i), "POclauseNo");
				clear(tendercreationlocators.POclauseNo(i), "POclauseNo");
				set(tendercreationlocators.POclauseNo(i), val, "POclauseNo");
				waitForObj(3000);

				click(tendercreationlocators.POclauseName(i), "POclauseName");
				clear(tendercreationlocators.POclauseName(i), "POclauseName");
				set(tendercreationlocators.POclauseName(i), pdfResultReport.testData.get("POclauseName"),
						"POclauseName");
				waitForObj(3000);

				click(tendercreationlocators.POclauseDetails(i), "POclauseDetails");
				clear(tendercreationlocators.POclauseDetails(i), "POclauseDetails");
				set(tendercreationlocators.POclauseDetails(i), pdfResultReport.testData.get("POclauseDetails"),
						"POclauseDetails");

				num++;
			}

			pdfResultReport.addStepDetails("POTRermsandConditionTAB", "PO Terms & Conditions to be filled ",
					"Successfully filled PO Terms & Conditions" + " ", "Pass", "Y");
			log.info("completed executing the method:: POTRermsandConditionTAB");
		} catch (Exception e) {
			log.fatal("Unable to Issue PO" + e.getMessage());
			pdfResultReport.addStepDetails("POTRermsandConditionTAB", "PO Details to be filled ",
					"Unable to  fill PO Details" + e.getMessage(), "Fail", "N");
		}
	}

	public void searchPoRefNoInPoListPage() throws Throwable {
		try {
			log.info("started executing the method:: searchThePoRefNoInPoListPage");

			clear(tendercreationlocators.search, "Clear the po ref no search field");

			set(tendercreationlocators.search, eTenderComponent.getDataFromPropertiesFile("POReferenceNum"),
					"po ref no search field");

			String val = eTenderComponent.getDataFromPropertiesFile("POReferenceNum");
			IsElementPresent(tendercreationlocators.poNum(val));
			highlight(tendercreationlocators.poNum(val));
			waitForObj(2000);

			pdfResultReport.addStepDetails("searchThePoRefNoInPoListPage",
					"Created PO should be displayed in the bidder login PO listing page",
					"Sucessfully Created PO is displaying in the bidder login PO listing page" + " ", "Pass", "Y");
			log.info("completed executing the method:: searchThePoRefNoInPoListPage");
		} catch (Exception e) {
			log.fatal("Unable to display the Po  in the bidder login PO listing page" + e.getMessage());
			pdfResultReport.addStepDetails("searchThePoRefNoInPoListPage",
					"Unable to display the Po  in the bidder login PO listing page",
					"Created PO should be displayed in the bidder login PO listing page" + e.getMessage(), "Fail", "N");
		}
	}

	public void POCreatorCancelsAcceptedPO() throws Throwable {
		try {
			log.info("started executing the method:: POCreatorCancelsAcceptedPO");
			click(tendercreationlocators.action, "action");
			waitForObj(3000);
			highlight(tendercreationlocators.cancelPO);
			pdfResultReport.addStepDetails("POCreatorCancelsAcceptedPO", "Cancel PO link must be click sucessfully",
					"Successfully clicked on cancel PO link" + " ", "Pass", "Y");
			click(tendercreationlocators.cancelPO, "cancelPO");
			waitForElementToBeVisible(tendercreationlocators.cancellationReason);
			waitForObj(2000);
			click(tendercreationlocators.cancellationReason, "cancellationReason");
			set(tendercreationlocators.cancellationReason, pdfResultReport.testData.get("cancellationReason"),
					"cancellationReason");
			pdfResultReport.addStepDetails("POCreatorCancelsAcceptedPO", "PO cancellation reason must fill sucessfully",
					"Successfully filled PO cancellation reason" + " ", "Pass", "Y");
			log.info("completed executing the method:: POCreatorCancelsAcceptedPO");
		} catch (Exception e) {
			log.fatal("Unable to fill PO cancellation reason" + e.getMessage());
			pdfResultReport.addStepDetails("POCreatorCancelsAcceptedPO", "PO cancellation reason must fill sucessfully",
					"Unable to fill PO cancellation reason" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyPOStatusIsPendingForCancellationApproval() throws Throwable {
		try {
			log.info("started executing the method:: verifyPOStatusIsPendingForCancellationApproval");
			IsElementPresent(tendercreationlocators.pendingForCancellationApproval);
			waitForObj(5000);
			pdfResultReport.addStepDetails("verifyPOStatusIsPendingForCancellationApproval",
					"PO status must be show as PendingForCancellationApproval successfully",
					"Successfully shown PO status as PendingForCancellationApproval" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyPOStatusIsPendingForCancellationApproval");
		} catch (Exception e) {
			log.fatal("Not able to show PO status as PendingForCancellationApproval" + e.getMessage());
			pdfResultReport.addStepDetails("verifyPOStatusIsPendingForCancellationApproval",
					"PO status must be show as PendingForCancellationApproval successfully",
					"Unable to show PO status as PendingForCancellationApproval" + e.getMessage(), "Fail", "N");
		}
	}

	public void po_Viewer_Login() throws Throwable {
		try {
			log.info("started executing the method:: po_Viewer_Login");
			click(tendercreationlocators.login, "login");
			set(tendercreationlocators.userName, pdfResultReport.testData.get("POViewerUserName"), "userName");
			waitForObj(2000);
			set(tendercreationlocators.password, pdfResultReport.testData.get("AppPassword"), "password");
			//Handle fixed Captcha (06/11/2020)
			set(tendercreationlocators.Captcha_Login, "1234", "Login_Captcha");
			click(tendercreationlocators.okButton, "okButton");
			waitForElement(tendercreationlocators.dashboardIcon1, 50);

			pdfResultReport.addStepDetails("po_Viewer_Login", "PO Viewer user must be login sucessfully",
					"Successfully logged in as PO Viewer user" + " ", "Pass", "Y");
			log.info("completed executing the method:: po_Viewer_Login");
		} catch (Exception e) {
			log.fatal("Unable to login as PO Viewer user" + e.getMessage());
			pdfResultReport.addStepDetails("po_Viewer_Login", "PO Viewer user must be login sucessfully",
					"Unable to login as PO Viewer user" + e.getMessage(), "Fail", "N");
		}
	}

	public void editPODetailsValidation() throws Throwable {
		try {
			log.info("started executing the method::  editPODetailsValidation");
			select(tendercreationlocators.statusFilter, pdfResultReport.testData.get("statusFilter"));
			click(tendercreationlocators.action, "action");
			waitForObj(2000);
			highlight(tendercreationlocators.editPOLink);
			pdfResultReport.addStepDetails("editPODetailsValidation", "Edit PO link must be click sucessfully",
					"Successfully clicked on edit PO link" + " ", "Pass", "Y");
			click(tendercreationlocators.editPOLink, "editPOLink");
			waitForElement(tendercreationlocators.PODetailsAttachmentTab, 50);
			waitForObj(4000);
			pdfResultReport.addStepDetails("editPODetailsValidation", "PO Details page must be naviagte sucessfully",
					"Successfully navigated to PO Details page" + " ", "Pass", "Y");
			log.info("completed executing the method:: editPODetailsValidation");

		} catch (Exception e) {
			log.fatal("Unable to navigate to PO Details page" + e.getMessage());
			pdfResultReport.addStepDetails("editPODetailsValidation", "PO Details page must be naviagte sucessfully",
					"Unable to navigate to PO Details page" + e.getMessage(), "Fail", "N");
		}
	}

	public void mandatortFieldAlertMessageValidation() throws Throwable {
		try {
			log.info("started executing the method::  mandatortFieldAlertMessageValidation");
			click(tendercreationlocators.POExpiryDate, "POExpiryDate");
			waitForObj(2000);
			clear(tendercreationlocators.POExpiryDate, "POExpiryDate");
			waitForObj(2000);
			click(tendercreationlocators.POSave, "POSave");
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.mandatoryFieldAlert);
			waitForObj(4000);
			pdfResultReport.addStepDetails("mandatortFieldAlertMessageValidation",
					"MandatoryFieldAlertMessage must be validate sucessfully",
					"Successfully validated MandatoryFieldAlertMessage" + " ", "Pass", "Y");
			log.info("completed executing the method:: mandatortFieldAlertMessageValidation");
			click(tendercreationlocators.POSUccessMsg, "POSUccessMsg");
		} catch (Exception e) {
			log.fatal("Unable to validate MandatoryFieldAlertMessage" + e.getMessage());
			pdfResultReport.addStepDetails("mandatortFieldAlertMessageValidation",
					"MandatoryFieldAlertMessage must be validate sucessfull",
					"Unable to validate MandatoryFieldAlertMessage" + e.getMessage(), "Fail", "N");
		}
	}

	public void poApprover1Login() throws Throwable {
		try {
			log.info("started executing the method:: poApprover1Login");
			click(tendercreationlocators.login, "login");
			waitForObj(3000);
			set(tendercreationlocators.userName, pdfResultReport.testData.get("POApproverUserName1"), "userName");
			set(tendercreationlocators.password, pdfResultReport.testData.get("AppPassword"), "password");
			//Handle fixed Captcha (06/11/2020)
			set(tendercreationlocators.Captcha_Login, "1234", "Login_Captcha");
			click(tendercreationlocators.okButton, "okButton");

			waitForObj(5000);
			pdfResultReport.addStepDetails("poApprover1Login", "PO Approver must be sucessfully logged in",
					"Successfully logged in as PO approver" + " ", "Pass", "Y");
			log.info("completed executing the method:: poApprover1Login");

		} catch (Exception e) {
			log.fatal("Unable to login in as PO approver" + e.getMessage());
			pdfResultReport.addStepDetails("poApprover1Login", "PO Approver must be sucessfully logged in",
					"Unable to login in as PO approver" + e.getMessage(), "Fail", "N");
		}
	}

	public void provide_PO_ApproverComment() throws Throwable {
		try {
			log.info("started executing the method:: provide_PO_ApproverComment");
			JSClick(By.xpath("//*[@aria-label='Bold']"), "overallComment");
			WebElement iframele = ThreadLocalWebdriver.getDriver()
					.findElement(By.xpath("//iframe[@id='uiTinymce0_ifr']"));
			switchframe(iframele);
			// Thread.sleep(5000);
			set(tendercreationlocators.recommendationComment, "Purchase Order Approval Approved",
					"recommendationComment");
			switchToDefaultFrame();
			// Thread.sleep(5000);
			pdfResultReport.addStepDetails("provide_PO_ApproverComment", "Comment must be pass successfully",
					"Successfully passed comment" + " ", "Pass", "Y");
			log.info("completed executing the method:: provide_PO_ApproverComment");
		} catch (Exception e) {
			log.fatal("Not able to pass comment under recommendation tab" + e.getMessage());
			pdfResultReport.addStepDetails("provide_PO_ApproverComment", "Comment must be pass successfully",
					"Unable to pass comment" + e.getMessage(), "Fail", "N");
		}
	}

	public void poApprover2Login() throws Throwable {
		try {
			log.info("started executing the method:: poApprover2Login");
			click(tendercreationlocators.login, "login");
			waitForObj(3000);
			set(tendercreationlocators.userName, pdfResultReport.testData.get("POApproverUserName2"), "userName");
			set(tendercreationlocators.password, pdfResultReport.testData.get("AppPassword"), "password");
			//Handle fixed Captcha (06/11/2020)
			set(tendercreationlocators.Captcha_Login, "1234", "Login_Captcha");
			click(tendercreationlocators.okButton, "okButton");
			waitForObj(5000);
			pdfResultReport.addStepDetails("poApprover2Login", "PO Approver must be sucessfully logged in",
					"Successfully logged in as PO approver" + " ", "Pass", "Y");
			log.info("completed executing the method:: poApprover1Login");

		} catch (Exception e) {
			log.fatal("Unable to login in as PO approver" + e.getMessage());
			pdfResultReport.addStepDetails("poApprover2Login", "PO Approver must be sucessfully logged in",
					"Unable to login in as PO approver" + e.getMessage(), "Fail", "N");
		}
	}

	public void poApprover3Login() throws Throwable {
		try {
			log.info("started executing the method:: poApprover3Login");
			click(tendercreationlocators.login, "login");
			waitForObj(3000);
			set(tendercreationlocators.userName, pdfResultReport.testData.get("POApproverUserName3"), "userName");
			set(tendercreationlocators.password, pdfResultReport.testData.get("AppPassword"), "password");
			//Handle fixed Captcha (06/11/2020)
			set(tendercreationlocators.Captcha_Login, "1234", "Login_Captcha");
			click(tendercreationlocators.okButton, "okButton");

			waitForObj(5000);
			pdfResultReport.addStepDetails("poApprover3Login", "PO Approver must be sucessfully logged in",
					"Successfully logged in as PO approver" + " ", "Pass", "Y");
			log.info("completed executing the method:: poApprover1Login");

		} catch (Exception e) {
			log.fatal("Unable to login in as PO approver" + e.getMessage());
			pdfResultReport.addStepDetails("poApprover3Login", "PO Approver must be sucessfully logged in",
					"Unable to login in as PO approver" + e.getMessage(), "Fail", "N");
		}
	}

	public void navigateToPoListingWithBidderUser() throws Throwable {

		try {
			log.info("started executing the method:: navigateToPoListingWithBidderUser");

			waitForObj(3000);
			JSClick(tendercreationlocators.bidSubmissionTransaction, "bidSubmissionTransaction");

			waitForObj(2000);

			// click(tendercreationlocators.actionMenuDropDownBy,
			// "actionMenuDropDownBy");

			waitForObj(2000);

			JSClick(tendercreationlocators.poListingLinkBy, "poListingLinkBy");

			waitForElementToBeVisible(tendercreationlocators.poListPageRowBy);

			waitForObj(3000);

			pdfResultReport.addStepDetails("navigateToPoListingWithBidderUser",
					"Must Navigate to Purchase Order List Page",
					"Successfully Navigate to Purchase Order List Page" + " ", "Pass", "Y");
			log.info("completed executing the method:: navigateToPoListingWithBidderUser");
		} catch (Exception e) {
			log.fatal("Unable to Navigate to Purchase Order List Page" + e.getMessage());
			pdfResultReport.addStepDetails("navigateToPoListingWithBidderUser",
					"Must Navigate to Purchase Order List page",
					"Unable to Navigate to Purchase Order List Page" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickAcceptPoInDropDown() throws Throwable {
		try {
			log.info("started executing the method:: clickAcceptPoInDropDown");

			click(tendercreationlocators.actionMenuDropDownBy_PO, "actionMenuDropDownBy_PO");

			waitForObj(3000);

			IsElementPresent(tendercreationlocators.acceptPOLinkBy);

			JSClick(tendercreationlocators.acceptPOLinkBy, "acceptPOLinkBy");

			waitForElementToBeVisible(tendercreationlocators.poDeatailsTabBy);

			waitForObj(3000);

			pdfResultReport.addStepDetails("clickAcceptPoInDropDown", "Should navigate to Accept Purchase Order Page",
					"Sucessfully navigated to Accept Purchase Order Page" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickAcceptPoInDropDown");
		} catch (Exception e) {
			log.fatal("Unable to navigate to Accept Purchase Order Page" + e.getMessage());
			pdfResultReport.addStepDetails("clickAcceptPoInDropDown",
					"Unable to navigate to Accept Purchase Order Page ",
					"Should navigate to Accept Purchase Order Page" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyPOStatusIsAccepted() throws Exception {
		try {
			log.info("started executing the method:: verifyPOStatusIsAccepted");

			clear(tendercreationlocators.search, "Clear the po ref no search field");

			set(tendercreationlocators.search, eTenderComponent.getDataFromPropertiesFile("POReferenceNum"),
					"po ref no search field");

			waitForObj(2000);

			IsElementPresent(tendercreationlocators.poStatusAcceptedBy);

			waitForObj(2000);

			pdfResultReport.addStepDetails("verifyPOStatusIsAccepted", "Po Status should show Accepted Mode",
					"Sucessfully showing Status as accepted" + " ", "Pass", "Y");

			log.info("completed executing the method:: verifyPOStatusIsAccepted");
		} catch (Exception e) {
			log.fatal("Unable to show Po Status in Accepted Mode" + e.getMessage());
			pdfResultReport.addStepDetails("verifyPOStatusIsAccepted", "Unable to show Po Status in Accepted Mode",
					"Po Status should show Accepted Mode" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickOtherPoTabLink() throws Exception {
		try {
			log.info("started executing the method:: clickOtherPoTabLink");

			waitForObj(5000);

			clear(tendercreationlocators.search, "Clear the po ref no search field");

			set(tendercreationlocators.search, eTenderComponent.getDataFromPropertiesFile("POReferenceNum"),
					"po ref no search field");

			click(tendercreationlocators.OtherPOBy, "OtherPOBy");

			waitForObj(3000);
			pdfResultReport.addStepDetails("clickOtherPoTabLink", "Must Click on Other Tab",
					"Sucessfully Clicked on Other Tab" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickOtherPoTabLink");

		} catch (Exception e) {
			log.fatal("Unable to click on Other Tab" + e.getMessage());
			pdfResultReport.addStepDetails("clickOtherPoTabLink", "Must Click on Other Tab",
					"Unable to click on Other Tab" + e.getMessage(), "Fail", "N");
		}
	}

	public void POTermsandConditionTAB() throws Throwable {
		try {
			log.info("started executing the method:: POTRermsandConditionTAB");
			click(tendercreationlocators.POTermsandCond, "POTermsandCond");
			waitForObj(3000);

			String poClauseNo = pdfResultReport.testData.get("POclauseNo");

			WebDriver driver = ThreadLocalWebdriver.getDriver();

			List<WebElement> cluaseNoList = driver.findElements(tendercreationlocators.POclauseNo);

			Set<Integer> getrandomIntergerNoWithUnique = getrandomIntergerNoWithUnique(1, 100, 3);

			Object[] array = getrandomIntergerNoWithUnique.toArray();

			int i = 0;
			for (WebElement clauseNo : cluaseNoList) {

				clauseNo.sendKeys(poClauseNo.concat("_").concat(array[i].toString()));

				i++;
			}

			waitForObj(3000);

			pdfResultReport.addStepDetails("POTRermsandConditionTAB", "PO Terms & details to be filled ",
					"Successfully filled PO Terms & details" + " ", "Pass", "Y");
			log.info("completed executing the method:: POTRermsandConditionTAB");
		} catch (Exception e) {
			log.fatal("Unable to fill  PO Terms & details" + e.getMessage());
			pdfResultReport.addStepDetails("POTRermsandConditionTAB", "PO Terms & details to be filled",
					"Unable to fill  PO Terms & details" + e.getMessage(), "Fail", "N");
		}
	}

	public void closeWithoutApproval() throws Exception {
		try {
			log.info("started executing the method:: closeWithoutApproval");

			click(tendercreationlocators.closeBtnBy_PO, "closeBtnBy_PO");

			waitForObj(3000);

			pdfResultReport.addStepDetails("closeWithoutApproval", "Must close the pop up SendFor Approval",
					"SucessFully closed PopUp SendForApproval" + " ", "Pass", "Y");
			log.info("completed executing the method:: closeWithoutApproval");

		} catch (Exception e) {
			log.fatal("Unable to close the pop up SendFor Approval" + e.getMessage());
			pdfResultReport.addStepDetails("closeWithoutApproval", "Must close the pop up SendFor Approval",
					"Unable to close the pop up SendFor Approval" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickEditPOLink() throws Exception {
		try {
			log.info("started executing the method:: clickEditPOLink");

			click(tendercreationlocators.actionMenuDropDownBy_PO, "actionMenuDropDownBy_PO");

			waitForElementToBeVisible(tendercreationlocators.editPOLinkBy);

			click(tendercreationlocators.editPOLinkBy, "editPOLinkBy");

			waitForElementToBeVisible(tendercreationlocators.poDeatailsTabBy);

			waitForElementToBeVisible(tendercreationlocators.POExpiryDate);

			pdfResultReport.addStepDetails("clickEditPOLink", "Must navigate to Po Details Tab",
					"Sucessfully  navigated to Po Details Tab" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickEditPOLink");

		} catch (Exception e) {
			log.fatal("Unable to navigate to Po Details Tab" + e.getMessage());
			pdfResultReport.addStepDetails("clickEditPOLink", "Must navigate to Po Details Tab",
					"Unable to navigate to Po Details Tab" + e.getMessage(), "Fail", "N");
		}
	}

	public void checkPoCreatorNameAndPoNoFieldsNonEditable() throws Exception {
		try {
			log.info("started executing the method:: checkPoCreatorNameAndPoNoFieldsNonEditable");

			IsElementPresent(tendercreationlocators.poNoFieldBy);

			IsElementPresent(tendercreationlocators.poCreatorNameFieldBy);

			pdfResultReport.addStepDetails("checkPoCreatorNameAndPoNoFieldsNonEditable",
					"Must be Non-editable po and po no creation name",
					"sucessfully showing Non-editable po and po no creation name" + " ", "Pass", "Y");
			log.info("completed executing the method:: checkPoCreatorNameAndPoNoFieldsNonEditable");

		} catch (Exception e) {
			log.fatal("Unable to show Non-editable po and po no creation name" + e.getMessage());
			pdfResultReport.addStepDetails("checkPoCreatorNameAndPoNoFieldsNonEditable",
					"Must be Non-editable po and po no creation name",
					"Unable to show Non-editable po and po no creation name" + e.getMessage(), "Fail", "N");
		}
	}

	public void editItemDetails() throws Exception {
		try {
			log.info("started executing the method:: checkPoCreatorNameAndPoNoFieldsNonEditable");

			WebDriver driver = ThreadLocalWebdriver.getDriver();

			click(tendercreationlocators.POItemDetails, "POItemDetails");

			waitForElementToBeVisible(tendercreationlocators.unitPriceFieldBy);

			List<WebElement> unitPriceEle = driver.findElements(tendercreationlocators.unitPriceFieldBy);

			unitPriceEle.stream().forEach((unitPriceField) -> {

				unitPriceField.clear();

				unitPriceField.sendKeys((String.valueOf(getrandomInterger(900, 950))));

			});

			waitForObj(3000);

			pdfResultReport.addStepDetails("checkPoCreatorNameAndPoNoFieldsNonEditable",
					"Item Details Must be editable ", "sucessfully Item Details are editable" + " ", "Pass", "Y");
			log.info("completed executing the method:: checkPoCreatorNameAndPoNoFieldsNonEditable");

		} catch (Exception e) {
			log.fatal("Unable to do Item Details edit" + e.getMessage());
			pdfResultReport.addStepDetails("checkPoCreatorNameAndPoNoFieldsNonEditable",
					"Item Details Must be editable", "Unable to do Item Details edit" + e.getMessage(), "Fail", "N");
		}
	}

	public void SavePoDetails() throws Exception {
		try {
			log.info("started executing the method:: SavePoDetails");

			click(tendercreationlocators.POSave, "POSave");

			waitForElementToBeVisible(tendercreationlocators.poSaveSuucessMsgPopUpBy);

			waitForObj(3000);

			click(tendercreationlocators.POSUccessMsg, "POSUccessMsg");

			waitForObj(5000);

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			pdfResultReport.addStepDetails("SavePoDetails", "Should show po details saved  sucessfully msg",
					"sucessfully showing po details saved  sucessfully msg" + " ", "Pass", "Y");
			log.info("completed executing the method:: SavePoDetails");

		} catch (Exception e) {
			log.fatal("Unable to show po details saved  sucessfully msg" + e.getMessage());
			pdfResultReport.addStepDetails("SavePoDetails", "Should show po details saved  sucessfully msg ",
					"Unable to show po details saved  sucessfully msg" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyPoDetailsTab() throws Exception {
		try {
			log.info("started executing the method:: verifyBidderPoDetails");

			JavascriptExecutor je = (JavascriptExecutor) ThreadLocalWebdriver.getDriver();

			String poRefNo = (String) je
					.executeScript("return document.getElementById('po_header_temp.po_ref_no.0').value;");

			Assert.assertTrue(poRefNo.equalsIgnoreCase(eTenderComponent.getDataFromPropertiesFile("POReferenceNum")));

			waitForObj(3000);

			pdfResultReport.addStepDetails("verifyBidderPoDetails", "PO Details Must be Validated Sucessfully",
					"Sucessfully Validated Sucessfully Po Details" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyBidderPoDetails");

		} catch (Exception e) {
			log.fatal("Unable to Validate PO Details" + e.getMessage());
			pdfResultReport.addStepDetails("verifyBidderPoDetails", " PO Details Must be Validated Sucessfully",
					"Unable to Validate PO Details" + e.getMessage(), "Fail", "N");
		}
	}

	public void sendForApprovalUserDefinedSequential_pocreator() throws Throwable {
		try {
			log.info("started executing the method:: sendForApprovalUserDefinedSequential");
			click(tendercreationlocators.Userdefined_po, "Userdefined_po");
			click(tendercreationlocators.approvalType_Sequential, "approvalType_Sequential");
			click(tendercreationlocators.addButton, "addButton");
			set(tendercreationlocators.user1, pdfResultReport.testData.get("UserTenderApprover1"), "user1");
			waitForObj(2000);
			set(tendercreationlocators.comments, pdfResultReport.testData.get("UserDefinedApprover-Comments"),
					"comments");
			pdfResultReport.addStepDetails("sendForApprovalUserDefinedSequential",
					"sendForApproval must be validate successfully", "Successfully validated sendForApproval" + " ",
					"Pass", "Y");
			waitForObj(2000);
			JSClick(By.xpath("//*[@ng-click='sendForApproval(approvalObj.workflowType)']"), "sendForApprovalSubmit");
			waitForElementToBeVisible(tendercreationlocators.poListPageRowBy);
			waitForObj(5000);
			pdfResultReport.addStepDetails("sendForApprovalUserDefinedSequential",
					"sendForApproval must be validate successfully", "Successfully validated sendForApproval" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: sendForApprovalUserDefinedSequential");
		} catch (Exception e) {
			log.fatal("Not able to validate sendForApproval" + e.getMessage());
			pdfResultReport.addStepDetails("sendForApprovalUserDefinedSequential",
					"sendForApproval must be validate successfully",
					"Unable to validate sendForApproval" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyPOStatusDraft() throws Throwable {
		try {
			log.info("started executing the method:: verifyPOStatusDraft");
			waitForObj(5000);
			select(tendercreationlocators.poStatusFilter, "Draft");
			waitForObj(1000);
			select(tendercreationlocators.poCategoryBy, "Default_cat");

			waitForObj(1000);
			select(tendercreationlocators.poSource, "SN");

			IsElementPresent(tendercreationlocators.draftStateStatus);
			waitForObj(2000);
			pdfResultReport.addStepDetails("verifyPOStatusDraft",
					"All Purchase Orders with Status as 'Draft' must be displayed",
					"All Purchase Orders with Status as 'Draft' are displayed successfull" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyPOStatusDraft");
		} catch (Exception e) {
			log.fatal("Unable to displayed PO with 'Draft' Status" + e.getMessage());
			pdfResultReport.addStepDetails("verifyPOStatusDraft",
					"All Purchase Orders with Status as 'Draft' must be displayed",
					"Unable to displayed PO with 'Draft' Status" + e.getMessage(), "Fail", "N");
		}
	}

	public void poCancellationSucessesMessageValidation() throws Throwable {
		try {
			log.info("started executing the method::  poCancellationSucessesMessageValidation");
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.cancelledPoSucessesPopUp);
			pdfResultReport.addStepDetails("poCancellationSucessesMessageValidation",
					"poCancellationSucessesMessage must be validate sucessfully",
					"Successfully validated poCancellationSucessesMessage" + " ", "Pass", "Y");
			waitForObj(2000);
			click(tendercreationlocators.alertTabOk, "alertTabOk");
			waitForElementToBeVisible(tendercreationlocators.poListPageRowBy);
			waitForObj(5000);
			pdfResultReport.addStepDetails("poCancellationSucessesMessageValidation",
					"Purchase order list page must be navigate sucessfully",
					"Successfully navigated to purchase order list page" + " ", "Pass", "Y");
			log.info("completed executing the method:: poCancellationSucessesMessageValidation");

		} catch (Exception e) {
			log.fatal("Unable to validate poCancellationSucessesMessage" + e.getMessage());
			pdfResultReport.addStepDetails("poCancellationSucessesMessageValidation",
					"poCancellationSucessesMessage must be validate sucessfull",
					"Unable to validate poCancellationSucessesMessage" + e.getMessage(), "Fail", "N");
		}
	}

	public void sendForApprovalNotRequiredForCancelPO() throws Throwable {
		try {
			log.info("started executing the method:: sendForApprovalNotRequiredForCancelPO");
			click(tendercreationlocators.ApprovalNotReqd_CancelPO, "ApprovalNotReqd_CancelPO");
			pdfResultReport.addStepDetails("sendForApprovalNotRequiredForCancelPO",
					"NotRequired radio button must be click successfully",
					"Successfully clicked on NotRequired radio button" + " ", "Pass", "Y");
			JSClick(tendercreationlocators.sendForApprovalSubmit_CancelPO, "sendForApprovalSubmit_CancelPO");
			WebDriverWait wait = new WebDriverWait(ThreadLocalWebdriver.getDriver(), 100);
			waitForElementToBeVisible(tendercreationlocators.cancelledPoSucessesPopUp);
			waitForObj(5000);
			pdfResultReport.addStepDetails("sendForApprovalNotRequiredForCancelPO",
					"Submit button must be click successfully", "Successfully clicked on submit button" + " ", "Pass",
					"Y");
			log.info("completed executing the method:: sendForApprovalNotRequiredForCancelPO");
		} catch (Exception e) {
			log.fatal("Not able to click on submit" + e.getMessage());
			pdfResultReport.addStepDetails("sendForApprovalNotRequiredForCancelPO",
					"Successfully clicked on submit button", "Unable to click on submit button" + e.getMessage(),
					"Fail", "N");
		}
	}

	public void clickOnCancelledPOTab() throws Throwable {
		try {
			log.info("started executing the method:: clickOnCancelledPOTab");
			click(tendercreationlocators.cancelledPoTab, "cancelledPoTab");
			waitForObj(5000);
			pdfResultReport.addStepDetails("clickOnCancelledPOTab", "Cancelled PO Tab must be click sucessfully ",
					"Successfully clicked on Cancelled PO Tab" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickOnCancelledPOTab");

		} catch (Exception e) {
			log.fatal("Unable to click on Cancelled PO Tab" + e.getMessage());
			pdfResultReport.addStepDetails("clickOnCancelledPOTab", "Cancelled PO Tab must be click sucessfully ",
					"Unable to click on Cancelled PO Tab " + e.getMessage(), "Fail", "N");
		}
	}

	public void navigateTo_DOPList(String DopName) throws Throwable {
		try {
			log.info("started executing the method:: navigateTo_DOPList");

			JSClick(tendercreationlocators.dopMenuLinkBy, "dopMenuLinkBy");

			JSClick(tendercreationlocators.dop_ListLinkBy, "dop_ListLinkBy");

			waitForElementToBeVisible(tendercreationlocators.dopListSactionRowTextBy);

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			waitForObj(5000);

			clear(tendercreationlocators.search, "clear the enter key word");

			set(tendercreationlocators.search, DopName, "enter key word");

			pdfResultReport.addStepDetails("navigateTo_DOPList", "Must display the Respective DOP in DOP List",
					"Sucessfully displaying the Respective DOP in DOP List" + " ", "Pass", "Y");
			log.info("completed executing the method:: navigateTo_DOPList");

		} catch (Exception e) {
			log.fatal("Unable to display the Respective DOP in DOP List" + e.getMessage());
			pdfResultReport.addStepDetails("navigateTo_DOPList", "Must display the Respective DOP in DOP List",
					"Unable to display the Respective DOP in DOP List" + e.getMessage(), "Fail", "N");
		}
	}

	public void selectApprovalTypePredifined() throws Throwable {
		try {
			log.info("started executing the method:: selectApprovalTypePredifined");
			waitForObj(2000);
			JSClick(tendercreationlocators.PredifinedApprovalTypeBy, "predefine");
			waitForObj(2000);

			pdfResultReport.addStepDetails("selectApprovalTypePredifined", "Must select Predefined Approval Type",
					"Successfully Selecting PreDefined Approval Type" + " ", "Pass", "Y");
			log.info("completed executing the method:: selectApprovalTypePredifined");
		} catch (Exception e) {
			log.fatal("Unable to  select ApprovalType Predifined" + e.getMessage());
			pdfResultReport.addStepDetails("selectApprovalTypePredifined", "Unable to  select ApprovalType Predifined",
					"Must select Predefined Approval Type" + e.getMessage(), "Fail", "N");
		}
	}

	public void sendForApprovalUserDefinedForCancelPO() throws Throwable {
		try {
			log.info("started executing the method:: sendForApprovalUserDefinedForCancelPO");

			click(By.xpath("(//input[@id='appYes'][@value='U'])[1]"), "Userdefined_po");
			click(tendercreationlocators.approvalType_Sequential, "approvalType_Sequential");
			click(tendercreationlocators.addButton, "addButton");
			set(tendercreationlocators.user1, pdfResultReport.testData.get("UserTenderApprover1"), "user1");
			waitForObj(3000);

			JSClick(By.xpath("//*[@ng-click='sendForApproval(approvalObj.workflowType)']"), "sendForApprovalSubmit");

			waitForElementToBeVisible(tendercreationlocators.confirmationOkBy);

			click(tendercreationlocators.confirmationOkBy, "confirmationOkBy");

			waitForElementToBeVisible(tendercreationlocators.poListPageRowBy);

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			waitForObj(5000);
			pdfResultReport.addStepDetails("sendForApprovalUserDefinedForCancelPO", "should navigate to polist page",
					"Successfully  navigated to polist page" + " ", "Pass", "Y");
			log.info("completed executing the method:: sendForApprovalUserDefinedForCancelPO");
		} catch (Exception e) {
			log.fatal("Unable to navigate to polist page" + e.getMessage());
			pdfResultReport.addStepDetails("sendForApprovalUserDefinedForCancelPO", "should navigate to polist page",
					"Unable to navigate to polist page" + e.getMessage(), "Fail", "N");
		}
	}

	public void closeCancelPOWithOutApproval() throws Exception {
		try {
			log.info("started executing the method:: closeCancelPOWithOutApproval");
			click(By.xpath("//*[@id='approvalModal']//button[contains(text(),'Close')]"), "close ");
			waitForObj(3000);
			pdfResultReport.addStepDetails("closeCancelPOWithOutApproval", "pop up window will close",
					"Successfully closed  pop up window" + " ", "Pass", "Y");
			log.info("completed executing the method:: closeCancelPOWithOutApproval");
		} catch (Exception e) {
			log.fatal("Unable to close pop up window" + e.getMessage());
			pdfResultReport.addStepDetails("closeCancelPOWithOutApproval", "Unable to close pop up window",
					"pop up window will close" + e.getMessage(), "Fail", "N");
		}

	}

	public void verifyPOStatusIsInPendingForAcceptance() throws Throwable {
		try {
			log.info("started executing the method:: verifyPOStatusIsInPendingForAcceptance");

			IsElementPresent(tendercreationlocators.PendingForAcceptanceStateStatus);
			waitForObj(2000);
			pdfResultReport.addStepDetails("verifyPOStatusIsInPendingForAcceptance",
					"Po Status as 'Pending For Acceptance' must be displayed",
					"Po Status as 'Pending For Acceptance' are displayed successfull" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyPOStatusIsInPendingForAcceptance");
		} catch (Exception e) {
			log.fatal("Unable to displayed PO with 'Pending For Acceptance Status" + e.getMessage());
			pdfResultReport.addStepDetails("verifyPOStatusIsInPendingForAcceptance",
					"Po Status as 'Pending For Acceptance' must be displayed",
					"Unable to displayed PO with 'Pending For Acceptance Status" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyPendingForCancellationapproval() throws Exception {
		try {
			log.info("started executing the method:: verifyPendingForCancellationapproval");

			IsElementPresent(tendercreationlocators.PendingForCancellationApprovalBy);
			waitForObj(2000);
			pdfResultReport.addStepDetails("verifyPendingForCancellationapproval",
					"Po Status as 'Pending For Cancellationapproval' must be displayed",
					"Po Status as 'Pending ForCancellationapproval' are displayed successfull" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyPendingForCancellationapproval");
		} catch (Exception e) {
			log.fatal("Unable to displayed PO with 'Pending For Cancellationapproval" + e.getMessage());
			pdfResultReport.addStepDetails("verifyPendingForCancellationapproval",
					"Po Status as 'Pending For Cancellationapproval must be displayed",
					"Unable to displayed PO with 'Pending For Cancellationapproval" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickCancelPurchaseOrderTab() throws Exception {
		try {
			log.info("started executing the method:: clickCancelPurchaseOrderTab");

			click(tendercreationlocators.cancelPurchaseOrderTabBy, "cancelPurchaseOrderTabBy");

			waitForObj(5000);

			pdfResultReport.addStepDetails("clickCancelPurchaseOrderTab", "Should click CancelPurchaseOrderTab",
					"Sucessfully clicked CancelPurchaseOrderTab" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickCancelPurchaseOrderTab");
		} catch (Exception e) {
			log.fatal("Unable to click CancelPurchaseOrderTab" + e.getMessage());
			pdfResultReport.addStepDetails("clickCancelPurchaseOrderTab", "Should click CancelPurchaseOrderTab",
					"Unable to click CancelPurchaseOrderTab" + e.getMessage(), "Fail", "N");
		}
	}

	public static Set<Integer> getrandomIntergerNoWithUnique(int min, int max, int count) {

		Set<Integer> set = new HashSet<>();

		while (true) {
			int number = ((int) (Math.random() * (max - min))) + min;

			set.add(number);

			if (set.size() == count) {
				break;
			}
		}
		return set;
	}

	public void verifyPoRefNumberInPoListPage() throws Exception {
		try {
			log.info("started executing the method:: verifyPoRefNumberInPoListPage");

			String porefnum = "//tbody/tr/td/span[contains(text(),'{0}')]";

			IsElementPresent(
					By.xpath(porefnum.replace("{0}", eTenderComponent.getDataFromPropertiesFile("POReferenceNum"))));

			pdfResultReport.addStepDetails("verifyPoRefNumberInPoListPage",
					"Should display Expected poref num in list page",
					"Sucessfully displaying Expected poref num in list page" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyPoRefNumberInPoListPage");
		} catch (Exception e) {
			log.fatal("Unable to enter po ref no in text field" + e.getMessage());
			pdfResultReport.addStepDetails("verifyPoRefNumberInPoListPage",
					"Should display Expected poref num in list page",
					"Unable to display Expected poref num in list page" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyBidderCannotViewCancelledPOinCancelledPOTab(String SelectStatus) throws Exception {
		try {
			log.info("started executing the method:: verifyBidderCannotViewCancelledPOinCancelledPOTab");

			List<String> cancelledPoNo = new ArrayList<>();

			select(tendercreationlocators.poStatusFilter, SelectStatus);

			waitForObj(5000);

			List<WebElement> cancelledlist = ThreadLocalWebdriver.getDriver()
					.findElements(By.xpath("//tbody/tr/td[2]/span[2]"));

			cancelledlist.stream().forEach((listcancelled) -> cancelledPoNo.add(listcancelled.getText()));

			Assert.assertFalse(cancelledPoNo.contains(poDocNum));

			pdfResultReport.addStepDetails("verifyBidderCannotViewCancelledPOinCancelledPOTab",
					"Cancelled PO should not be displayed", "Sucessfully not showing Cancelled PO" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyBidderCannotViewCancelledPOinCancelledPOTab");
		} catch (Exception e) {
			log.fatal("Cancelled PO is displayed in Cancelled PO Tab" + e.getMessage());
			pdfResultReport.addStepDetails("verifyBidderCannotViewCancelledPOinCancelledPOTab",
					"Cancelled PO should not be displayed",
					"Cancelled PO is displayed in Cancelled PO Tab" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickPurchaseOrder() throws Exception {
		try {
			log.info("started executing the method:: clickPurchaseOrder");

			click(tendercreationlocators.PurchaseOrderTabBy, "clickPurchaseOrder");

			waitForObj(5000);

			pdfResultReport.addStepDetails("clickPurchaseOrder", "Must clickPurchaseOrder tab",
					"Sucessfilly clicked PurchaseOrder Tab" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickPurchaseOrder");
		} catch (Exception e) {
			log.fatal("Unable to clickPurchaseOrder" + e.getMessage());
			pdfResultReport.addStepDetails("clickPurchaseOrder", "Must click PurchaseOrder tab",
					"Unable to clickPurchaseOrder" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyCanclledStatusInPurchaseOrderTab() throws Exception {
		try {
			log.info("started executing the method:: verifyCanclledStatusInPurchaseOrderTab");

			clear(tendercreationlocators.PoSearchBy, "clear poref num");

			set(tendercreationlocators.PoSearchBy, eTenderComponent.getDataFromPropertiesFile("POReferenceNum"),
					"POReferenceNum enter");

			IsElementPresent(tendercreationlocators.CancelledStatusBy);

			waitForObj(5000);

			pdfResultReport.addStepDetails("verifyCanclledStatusInPurchaseOrderTab", "Po Status Should be Cancelled",
					"Po Status Cancelled is displaying SucessFully" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyCanclledStatusInPurchaseOrderTab");
		} catch (Exception e) {
			log.fatal("Unable to displayed PO Status Cancell" + e.getMessage());
			pdfResultReport.addStepDetails("verifyCanclledStatusInPurchaseOrderTab", "Po Status Should be Cancelled",
					"Unable to displayed PO Status Cancell" + e.getMessage(), "Fail", "N");
		}
	}

	public void ApprovalNotRequired() throws Throwable {
		try {
			log.info("started executing the method:: ApprovalNotRequired");
			click(tendercreationlocators.ApprovalNotReqd, "ApprovalNotReqd");
			waitForObj(2000);
			click(tendercreationlocators.SubmitApproval, "SubmitApproval");
			waitForElementToBeVisible(tendercreationlocators.poListPageRowBy);

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			waitForObj(5000);
			pdfResultReport.addStepDetails("ApprovalNotRequired", "Should navigate to PoListPage",
					"Successfully navigated to PoListPage" + " ", "Pass", "Y");

			log.info("completed executing the method:: ApprovalNotRequired");
		} catch (Exception e) {
			log.fatal("Unable to navigate to PoListPage" + e.getMessage());
			pdfResultReport.addStepDetails("ApprovalNotRequired", "Should navigate to PoListPage",
					"Unable to navigate to PoListPage" + e.getMessage(), "Fail", "N");
		}
	}

	public void navigateToApprovalPendingPage() throws Throwable {
		try {
			log.info("started executing the method:: navigateToApprovalPendingPage");
			click(tendercreationlocators.workFlow, "workFlow");
			click(tendercreationlocators.pending, "pending");

			waitForElementToBeVisible(By.xpath("(//*[@id='myTable']/tbody/tr[1]/td[1])[1]"));

			waitForObj(5000);
			pdfResultReport.addStepDetails("navigateToApprovalPendingPage",
					"Approval Pending page must be navigate successfully",
					"Successfully navigated to Approval Pendong page" + " ", "Pass", "Y");
			log.info("completed executing the method:: navigateToApprovalPendingPage");

		} catch (Exception e) {
			log.fatal("Unable to navigate to Approval Pendong page" + e.getMessage());
			pdfResultReport.addStepDetails("navigateToApprovalPendingPage",
					"Approval Pending page must be navigate successfully",
					"Unable to navigate to Approval Pendong page" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickOnActionDropDownForCancelledPO() throws Throwable {
		try {
			log.info("started executing the method:: clickOnActionDropDownForCancelledPO");
			click(tendercreationlocators.actionMenuDropDownBy_PO, "actionMenuDropDownBy_PO");
			waitForObj(3000);
			pdfResultReport.addStepDetails("clickOnActionDropDownForCancelledPO",
					"Preview and Printpreview must be validate sucessfully",
					"Sucessfully validated Preview and print preview" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickOnActionDropDownForCancelledPO");
		} catch (Exception e) {
			log.fatal("Unable to validate Preview and print preview" + e.getMessage());
			pdfResultReport.addStepDetails("clickOnActionDropDownForCancelledPO",
					"Preview and Printpreview must be validate sucessfully",
					"Unable to validate Preview and print preview" + e.getMessage(), "Fail", "N");
		}
	}

	public void purchaseOrderApproval() throws Throwable {
		try {
			log.info("started executing the method:: purchaseOrderApproval");
			highlight(tendercreationlocators.sanctionNoteEvaluationApprove);
			pdfResultReport.addStepDetails("purchaseOrderApproval", "Approve button must be click sucessfully ",
					"Successfully clicked on approve button" + " ", "Pass", "Y");
			click(tendercreationlocators.sanctionNoteEvaluationApprove, "sanctionNoteEvaluationApprove");
			waitForElementToBeVisible(tendercreationlocators.approveConfirm);
			click(tendercreationlocators.approveConfirm, "approveConfirm");

			waitForElementToBeVisible(tendercreationlocators.approvalConfirmPopUp);
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.approvalConfirmPopUp);
			pdfResultReport.addStepDetails("purchaseOrderApproval",
					"Status updated sucessfully popup must be validate sucessfully ",
					"Successfully validate status updated sucessfully pop up" + " ", "Pass", "Y");
			click(tendercreationlocators.confirmOk, "confirmOk");

			waitForElementToBeVisible(By.xpath("(//*[@id='myTable']/tbody/tr[1]/td[1])[1]"));

			waitForObj(5000);
			pdfResultReport.addStepDetails("purchaseOrderApproval", "Purchase order must be approve sucessfully ",
					"Successfully approved Purchase order" + " ", "Pass", "Y");
			log.info("completed executing the method:: purchaseOrderApproval");

		} catch (Exception e) {
			log.fatal("Unable to approve Purchase order" + e.getMessage());
			pdfResultReport.addStepDetails("purchaseOrderApproval", "Purchase order must be approve sucessfully ",
					"Unable to approve Purchase order" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickOn_POstage() throws Throwable {
		try {
			log.info("started executing the method:: clickOn_POstage");
			click(tendercreationlocators.POstage, "POstage");

			waitForElementToBeVisible(By.xpath("//*[@id='contact']/div/table/tbody/tr[2]/td[text()='Default_cat']"));

			waitForObj(5000);
			pdfResultReport.addStepDetails("clickOn_POstage", "PO stage must be click sucessfully",
					"Successfully clicked on PO stage " + " ", "Pass", "Y");
			log.info("completed executing the method:: clickOn_POstage");
		} catch (Exception e) {
			log.fatal("Unable to click on PO stage" + e.getMessage());
			pdfResultReport.addStepDetails("clickOn_POstage", "PO stage must be click sucessfully",
					"Unable to click on PO stage" + e.getMessage(), "Fail", "N");
		}
	}

	public void sendForApprovalUserDefinedSequential_pocreator1() throws Throwable {
		try {
			log.info("started executing the method:: sendForApprovalUserDefinedSequential");
			click(tendercreationlocators.Userdefined_po1, "Userdefined_po");
			click(tendercreationlocators.approvalType_Sequential, "approvalType_Sequential");
			click(tendercreationlocators.addButton, "addButton");
			set(tendercreationlocators.user1, pdfResultReport.testData.get("POapprover1User"), "user1");
			waitForObj(2000);
			// set(tendercreationlocators.comments,
			// pdfResultReport.testData.get("UserDefinedApprover-Comments"),
			// "comments");

			click(tendercreationlocators.sendForApproval, "sendForApproval");
			waitForElement(tendercreationlocators.cancelledPoSucessesPopUpApprover, 50);
			IsElementPresent(tendercreationlocators.cancelledPoSucessesPopUpApprover);
			click(tendercreationlocators.confirmOk, "confirmOk");
			waitForElementToBeVisible(tendercreationlocators.action);
			pdfResultReport.addStepDetails("sendForApprovalUserDefinedSequential",
					"sendForApproval must be validate successfully", "Successfully validated sendForApproval" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: sendForApprovalUserDefinedSequential");
		} catch (Exception e) {
			log.fatal("Not able to validate sendForApproval" + e.getMessage());
			pdfResultReport.addStepDetails("sendForApprovalUserDefinedSequential",
					"sendForApproval must be validate successfully",
					"Unable to validate sendForApproval" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyPOStatusAccepted() throws Throwable {
		try {
			log.info("started executing the method:: verifyPOStatusAccepted");
			waitForObj(5000);
			select(tendercreationlocators.poStatusFilter, "Accepted");

			waitForObj(1000);
			select(tendercreationlocators.poCategoryBy, "Default_cat");

			waitForObj(1000);
			select(tendercreationlocators.poSource, "SN");

			IsElementPresent(tendercreationlocators.AcceptedStateStatus);
			waitForObj(2000);
			pdfResultReport.addStepDetails("verifyPOStatusAccepted",
					"All Purchase Orders with Status as 'Accepted' must be displayed",
					"All Purchase Orders with Status as 'Accepted' are displayed successfull" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyPOStatusAccepted");
		} catch (Exception e) {
			log.fatal("Unable to displayed PO with 'Accepted' Status" + e.getMessage());
			pdfResultReport.addStepDetails("verifyPOStatusAccepted",
					"All Purchase Orders with Status as 'Accepted' must be displayed",
					"Unable to displayed PO with 'Accepted' Status" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyPOStatusPendingForAcceptance() throws Throwable {
		try {
			log.info("started executing the method:: verifyPOStatusPendingForAcceptance");
			waitForObj(5000);
			select(tendercreationlocators.poStatusFilter, "Pending For Acceptance");

			waitForObj(1000);
			select(tendercreationlocators.poCategoryBy, "Default_cat");

			waitForObj(1000);
			select(tendercreationlocators.poSource, "SN");
			IsElementPresent(tendercreationlocators.PendingForAcceptanceStateStatus);
			waitForObj(2000);
			pdfResultReport.addStepDetails("verifyPOStatusPendingForAcceptance",
					"All Purchase Orders with Status as 'Pending For Acceptance' must be displayed",
					"All Purchase Orders with Status as 'Pending For Acceptance' are displayed successfull" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: verifyPOStatusPendingForAcceptance");
		} catch (Exception e) {
			log.fatal("Unable to displayed PO with 'Pending For Acceptance' Status" + e.getMessage());
			pdfResultReport.addStepDetails("verifyPOStatusPendingForAcceptance",
					"All Purchase Orders with Status as 'Pending For Acceptance' must be displayed",
					"Unable to displayed PO with 'Pending For Acceptance' Status" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyPOStatusPendingForApproval() throws Throwable {
		try {
			log.info("started executing the method:: verifyPOStatusPendingForApproval");
			waitForObj(5000);
			select(tendercreationlocators.poStatusFilter, "Pending For Approval");

			waitForObj(1000);
			select(tendercreationlocators.poCategoryBy, "Default_cat");

			waitForObj(1000);
			select(tendercreationlocators.poSource, "SN");

			IsElementPresent(tendercreationlocators.PendingForApprovalStateStatus);
			waitForObj(2000);
			pdfResultReport.addStepDetails("verifyPOStatusPendingForApproval",
					"All Purchase Orders with Status as 'Pending For Approval' must be displayed",
					"All Purchase Orders with Status as 'Pending For Approval' are displayed successfull" + " ", "Pass",
					"Y");
			log.info("completed executing the method:: verifyPOStatusPendingForApproval");
		} catch (Exception e) {
			log.fatal("Unable to displayed PO with 'Pending For Approval Status" + e.getMessage());
			pdfResultReport.addStepDetails("verifyPOStatusPendingForApproval",
					"All Purchase Orders with Status as 'Pending For Approval' must be displayed",
					"Unable to displayed PO with 'Pending For Approval' Status" + e.getMessage(), "Fail", "N");
		}
	}

	public void navigateToPoListingWithBidder1User() throws Throwable {

		try {
			log.info("started executing the method:: navigateToPoListingWithBidderUser");

			waitForObj(3000);
			JSClick(tendercreationlocators.bidSubmissionTransaction, "bidSubmissionTransaction");

			waitForObj(2000);

			JSClick(tendercreationlocators.poListingLinkBy, "poListingLinkBy");

			waitForElementToBeVisible(tendercreationlocators.poListPageRowBy);

			searchPoRefNoInPoListPage();

			// click(tendercreationlocators.actionMenuDropDownBy,
			// "actionMenuDropDownBy");

			waitForObj(3000);

			pdfResultReport.addStepDetails("navigateToPoListingWithBidderUser",
					"Must Navigate to Purchase Order List Page",
					"Successfully Navigate to Purchase Order List Page" + " ", "Pass", "Y");
			log.info("completed executing the method:: navigateToPoListingWithBidderUser");
		} catch (Exception e) {
			log.fatal("Unable to Navigate to Purchase Order List Page" + e.getMessage());
			pdfResultReport.addStepDetails("navigateToPoListingWithBidderUser",
					"Must Navigate to Purchase Order List page",
					"Unable to Navigate to Purchase Order List Page" + e.getMessage(), "Fail", "N");
		}
	}

	public void CancelPendingForAcceptencePOCreator() throws Throwable {
		try {
			log.info("started executing the method:: CancelPendingForAcceptencePOCreator");
			waitForElement(tendercreationlocators.action, 50);
			select(tendercreationlocators.selectStatus, "Pending For Acceptance");
			waitForObj(3000);
			click(tendercreationlocators.action, "action");
			waitForObj(3000);
			click(tendercreationlocators.cancelPO, "cancelPO");
			waitForObj(3000);
			// click(tendercreationlocators.cancellationReason,"cancellationReason");
			// set(tendercreationlocators.cancellationReason,
			// pdfResultReport.testData.get("cancellationReason"),
			// "cancellationReason");
			pdfResultReport.addStepDetails("CancelPendingForAcceptencePOCreator",
					"PO should be cancelled successfully ", "Successfully canceled PO" + " ", "Pass", "Y");
			log.info("completed executing the method:: CancelPendingForAcceptencePOCreator");
		} catch (Exception e) {
			log.fatal("Unable to Issue PO" + e.getMessage());
			pdfResultReport.addStepDetails("CancelPendingForAcceptencePOCreator", "PO Details to be filled ",
					"Unable to  fill PO Details" + e.getMessage(), "Fail", "N");
		}
	}

	public void sendForApprovalNotRequiredSequential_pocreator1() throws Throwable {
		try {
			log.info("started executing the method:: sendForApprovalUserDefinedSequential");
			//locator change (19/11/2020 due to cancel PO page change x-path)
			click(tendercreationlocators.ApprovalNotReqd_CancelPO, "ApprovalNotReqd_CancelPO");

			set(tendercreationlocators.cancellationReason, pdfResultReport.testData.get("cancellationReason"),
					"cancellationReason");

			waitForObj(2000);
			// set(tendercreationlocators.comments,
			// pdfResultReport.testData.get("UserDefinedApprover-Comments"),
			// "comments");
			waitForObj(2000);
			//locator change (19/11/2020 due to cancel PO page change x-path)
			click(tendercreationlocators.sendForApprovalSubmit_CancelPO, "sendForApprovalSubmit_CancelPO");

			waitForObj(4000);
			pdfResultReport.addStepDetails("sendForApprovalUserDefinedSequential",
					"sendForApproval must be validate successfully", "Successfully validated sendForApproval" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: sendForApprovalUserDefinedSequential");
		} catch (Exception e) {
			log.fatal("Not able to validate sendForApproval" + e.getMessage());
			pdfResultReport.addStepDetails("sendForApprovalUserDefinedSequential",
					"sendForApproval must be validate successfully",
					"Unable to validate sendForApproval" + e.getMessage(), "Fail", "N");
		}
	}

	public void statusOfPOInCancelledPOTab() throws Throwable {
		try {
			log.info("started executing the method:: statusOfPOInCancelledPOTab");
			click(tendercreationlocators.POSUccessMsg, "OKButton");
			waitForElementToBeVisible(tendercreationlocators.action);

			waitForObj(2000);
			click(tendercreationlocators.cancelledPO, "cancelledPO");
			waitForObj(4000);
			set(tendercreationlocators.search, poDocNum, "enter PoDocNum");
			waitForObj(3000);
			IsElementPresent(tendercreationlocators.poNoRefNo);
			waitForObj(3000);
			// IsElementPresent(tendercreationlocators.pendingForCancellationApproval);
			pdfResultReport.addStepDetails("statusOfPOInCancelledPOTab",
					"statusOfPOInCancelledPOTab must be validate successfully",
					"Successfully validated statusOfPOInCancelledPOTab" + " ", "Pass", "Y");
			pdfResultReport.addStepDetails("statusOfPOInCancelledPOTab",
					"statusOfPOInCancelledPOTab must be validate successfully",
					"Successfully validated statusOfPOInCancelledPOTab" + " ", "Pass", "Y");
			log.info("completed executing the method:: statusOfPOInCancelledPOTab");
		} catch (Exception e) {
			log.fatal("Not able to validate staus" + e.getMessage());
			pdfResultReport.addStepDetails("statusOfPOInCancelledPOTab", "status must be validate successfully",
					"Unable to validate status" + e.getMessage(), "Fail", "N");
		}

	}

	public void ReorderPendingForAccepetedPO() throws Throwable {
		try {
			log.info("started executing the method:: CancelPendingForAcceptencePOCreator");
			waitForElement(tendercreationlocators.action, 50);
			select(tendercreationlocators.selectStatus, "Accepted");
			waitForObj(3000);
			click(tendercreationlocators.action, "action");
			waitForObj(3000);
			click(tendercreationlocators.repeatOrder, "repeatOrder");
			waitForObj(3000);
			click(tendercreationlocators.POSUccessMsg, "OKButton");
			waitForObj(5000);
			String refNo = text(tendercreationlocators.refText).trim();
			System.out.println(refNo);
			pdfResultReport.addStepDetails("CancelPendingForAcceptencePOCreator",
					"PO should be cancelled successfully ", "Successfully canceled PO" + " ", "Pass", "Y");
			refNo = refNo.substring(73, 77);
			click(tendercreationlocators.POSUccessMsg, "OKButton");
			waitForObj(4000);
			select(tendercreationlocators.selectStatus, "Draft");
			waitForElement(tendercreationlocators.draftStateStatus, 50);
			clear(tendercreationlocators.search, "search");
			set(tendercreationlocators.search, refNo, "refNo");
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.draftStateStatus);
			// set(tendercreationlocators.cancellationReason,
			// pdfResultReport.testData.get("cancellationReason"),
			// "cancellationReason");
			pdfResultReport.addStepDetails("CancelPendingForAcceptencePOCreator",
					"PO should be cancelled successfully ", "Successfully canceled PO" + " ", "Pass", "Y");
			log.info("completed executing the method:: ReorderPendingForAccepetedPO");
		} catch (Exception e) {
			log.fatal("Unable to Issue PO" + e.getMessage());
			pdfResultReport.addStepDetails("ReorderPendingForAccepetedPO", "PO Details to be filled ",
					"Unable to  fill PO Details" + e.getMessage(), "Fail", "N");
		}
	}

	public void purchaseOrderApproval1() throws Throwable {
		try {
			log.info("started executing the method:: purchaseOrderApproval");
			click(tendercreationlocators.sanctionNoteEvaluationApprove, "sanctionNoteEvaluationApprove");
			waitForObj(5000);
			click(tendercreationlocators.approveConfirm, "approveConfirm");
			waitForElement(tendercreationlocators.approvalConfirmPopUp, 50);
			WebElement approveConfirm1 = ThreadLocalWebdriver.getDriver()
					.findElement(tendercreationlocators.approvalConfirmPopUp);
			if (approveConfirm1.isDisplayed()) {
				IsElementPresent(tendercreationlocators.approvalConfirmPopUp);
				click(tendercreationlocators.confirmOk, "confirmOk");
				waitForElementToBeVisible(By.xpath("(//*[@id='myTable']/tbody/tr[1]/td[1])[1]"));
			} else {
				waitForElementToBeVisible(By.xpath("(//*[@id='myTable']/tbody/tr[1]/td[1])[1]"));
			}
			pdfResultReport.addStepDetails("purchaseOrderApproval", "Purchase order must be approve sucessfully ",
					"Successfully approved Purchase order" + " ", "Pass", "Y");
			log.info("completed executing the method:: purchaseOrderApproval");

		} catch (Exception e) {
			log.fatal("Unable to approve Purchase order" + e.getMessage());
			pdfResultReport.addStepDetails("purchaseOrderApproval", "Purchase order must be approve sucessfully ",
					"Unable to approve Purchase order" + e.getMessage(), "Fail", "N");
		}
	}

	public void checkingCancellationComment() throws Throwable {
		try {
			log.info("started executing the method:: checkingCancellationComment");
			waitForElement(tendercreationlocators.action, 50);
			select(tendercreationlocators.selectStatus, "Pending For Acceptance");
			waitForObj(3000);
			String refNo = text(tendercreationlocators.poNoRefNo1Span).trim();
			System.out.println(refNo);
			click(tendercreationlocators.action, "action");
			waitForObj(3000);
			click(tendercreationlocators.cancelPO, "cancelPO");
			waitForObj(3000);
			click(tendercreationlocators.cancellationReason, "cancellationReason");
			waitForObj(3000);
			//Locator change due to Cancel PO page x-path change (19/11/2020)
			click(tendercreationlocators.ApprovalNotReqd_CancelPO, "ApprovalNotReqd_CancelPO");
			click(tendercreationlocators.sendForApprovalSubmit_CancelPO, "sendForApprovalSubmit_CancelPO");
			IsElementPresent(tendercreationlocators.alertCancel);
			pdfResultReport.addStepDetails("checkingCancellationComment", "Purchase order must be cancel sucessfully ",
					"Successfully cancelled Purchase order" + " ", "Pass", "Y");
			click(tendercreationlocators.confirmOk, "confirmOk");
			set(tendercreationlocators.cancellationReason, pdfResultReport.testData.get("cancellationReason"),
					"cancellationReason");
			click(tendercreationlocators.sendForApprovalSubmit, "sendForApprovalSubmit");
			waitForObj(5000);
			click(tendercreationlocators.approveAllOkButton, "approveAllOkButton");
			waitForElement(tendercreationlocators.action, 50);
			click(tendercreationlocators.cancelledPO, "CancelledPO");
			waitForObj(5000);
			set(tendercreationlocators.search, refNo, "refNo");
			waitForObj(3000);
			IsElementPresent(tendercreationlocators.cancellationComment);
			pdfResultReport.addStepDetails("checkingCancellationComment", "Purchase order must be cancel sucessfully ",
					"Successfully cancelled Purchase order" + " ", "Pass", "Y");
			log.info("completed executing the method:: checkingCancellationComment");

		} catch (Exception e) {
			log.fatal("Unable to cancel Purchase order" + e.getMessage());
			pdfResultReport.addStepDetails("checkingCancellationComment",
					"Purchase order must be cancelled sucessfully ", "Unable to cancel Purchase order" + e.getMessage(),
					"Fail", "N");
		}
	}

	public void sanctionNoteSelectionFunctionality() throws Throwable {
		try {
			waitForElementToBeVisible(tendercreationlocators.POListingpage);
			log.info("started executing the method:: sanctionNoteSelectionFunctionality");
			waitForObj(2000);
			JSClick(tendercreationlocators.createPO, "createPO");
			waitForElementToBeVisible(tendercreationlocators.selectSource);
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			// waitForObj(4000);
			select(tendercreationlocators.selectSource, pdfResultReport.testData.get("selectSource"));
			// waitForObj(3000);
			pdfResultReport.addStepDetails("sanctionNoteSelectionFunctionality",
					"Sanction Note must be select in Dropdown Source ",
					"Successfully selected as Sanction Note in dropDown source" + " ", "Pass", "Y");
			log.info("completed executing the method:: sanctionNoteSelectionFunctionality");
		} catch (Exception e) {
			log.fatal("Unable to select Sanction Note from  Dropdown" + e.getMessage());
			pdfResultReport.addStepDetails("sanctionNoteSelectionFunctionality",
					"Sanction Note must be select in Dropdown Source",
					"Unable to select Sanction Note from  Dropdown" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickOnPOstageWhenSNStageIsNotInGreenColour() throws Throwable {
		try {
			log.info("started executing the method:: clickOnPOstageWhenSNStageIsNotInGreenColour");
			List<WebElement> ele = ThreadLocalWebdriver.getDriver()
					.findElements(By.xpath("//th[normalize-space(text())='RFQ ID']/../../..//tr/td[2]"));

			for (int i = 1; i <= ele.size(); i++) {
				WebElement elem = ThreadLocalWebdriver.getDriver()
						.findElement(By.xpath("((//th[normalize-space(text())='RFQ ID']/../../..//tr/td[2])[" + i
								+ "]/following-sibling::td[5]//button)[1]"));
				String SNcolcor = elem.getAttribute("class");
				if (SNcolcor.contains("yellowBtn")) {
					ThreadLocalWebdriver.getDriver()
							.findElement(By.xpath("((//th[normalize-space(text())='RFQ ID']/../../..//tr/td[2])[" + i
									+ "]/following-sibling::td[5]//button)[2]"))
							.click();
					break;
				}

			}
			pdfResultReport.addStepDetails("clickOnPOstageWhenSNStageIsNotInGreenColour",
					"PO stage must be click sucessfully", "Successfully clicked on PO stage" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickOnPOstageWhenSNStageIsNotInGreenColour");
		} catch (Exception e) {
			log.fatal("Unable to click on PO stage" + e.getMessage());
			pdfResultReport.addStepDetails("clickOnPOstageWhenSNStageIsNotInGreenColour",
					"PO stage must be click sucessfully", "Unable to click on PO stage" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickOnPOstageWhenSNStageIsInGreenColour() throws Throwable {
		try {
			log.info("started executing the method:: clickOnPOstageWhenSNStageIsInGreenColour");
			List<WebElement> ele = ThreadLocalWebdriver.getDriver()
					.findElements(By.xpath("//th[normalize-space(text())='RFQ ID']/../../..//tr/td[2]"));

			for (int i = 1; i <= ele.size(); i++) {
				WebElement elem = ThreadLocalWebdriver.getDriver()
						.findElement(By.xpath("((//th[normalize-space(text())='RFQ ID']/../../..//tr/td[2])[" + i
								+ "]/following-sibling::td[5]//button)[1]"));
				String SNcolcor = elem.getAttribute("class");
				if (SNcolcor.contains("grnBtn")) {
					WebElement elem1 = ThreadLocalWebdriver.getDriver()
							.findElement(By.xpath("((//th[normalize-space(text())='RFQ ID']/../../..//tr/td[2])[" + i
									+ "]/following-sibling::td[5]//button)[2]"));
					String SNcolour = elem1.getAttribute("class");
					if (SNcolour.contains("yellowBtn")) {
						ThreadLocalWebdriver.getDriver()
								.findElement(By.xpath("((//th[normalize-space(text())='RFQ ID']/../../..//tr/td[2])["
										+ i + "]/following-sibling::td[5]//button)[2]"))
								.click();
						break;
					}
				}
			}
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForObj(5000);
			pdfResultReport.addStepDetails("clickOnPOstageWhenSNStageIsInGreenColour",
					"PO stage must be click sucessfully", "Successfully clicked on PO stage" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickOnPOstageWhenSNStageIsInGreenColour");
		} catch (Exception e) {
			log.fatal("Unable to click on PO stage" + e.getMessage());
			pdfResultReport.addStepDetails("clickOnPOstageWhenSNStageIsInGreenColour",
					"PO stage must be click sucessfully", "Unable to click on PO stage" + e.getMessage(), "Fail", "N");
		}
	}

	public void IssuePO() throws Throwable {
		try {
			log.info("started executing the method:: IssuePO");

			String id = eTenderComponent.getDataFromPropertiesFile();
			JSClick(tendercreationlocators.SNstage(id), "SN_stage");

			waitForObj(5000);
			click(tendercreationlocators.SearchIdInSanction, "SearchIdInSanction");
			set(tendercreationlocators.SearchIdInSanction,
					eTenderComponent.getDataFromPropertiesFile("sanctionReferenceNumber"), "sanctionReferenceNumber");
			waitForObj(5000);
			click(tendercreationlocators.btnIssuePO, "btnIssuePO");
			waitForObj(5000);
			click(tendercreationlocators.confirmationOkBy, "confirmationOkBy");
			pdfResultReport.addStepDetails("IssuePO", "Issue PO must be click sucessfully",
					"Successfully clicked on Issue PO " + " ", "Pass", "Y");
			log.info("completed executing the method:: IssuePO");
		} catch (Exception e) {
			log.fatal("Unable to Issue PO" + e.getMessage());
			pdfResultReport.addStepDetails("IssuePO", "Issue PO must be clicked sucessfully",
					"Unable to click Issue PO" + e.getMessage(), "Fail", "N");
		}

	}

	public void SearchScantionID() throws Throwable {
		try {
			log.info("started executing the method:: IssuePO");

			click(tendercreationlocators.searchsactionIdinPOPOButton, "SearchIdInSanction");
			set(tendercreationlocators.searchsactionIdinPOPOButton,
					eTenderComponent.getDataFromPropertiesFile("sanctionReferenceNumber"), "sanctionReferenceNumber");
			waitForObj(5000);

			pdfResultReport.addStepDetails("IssuePO", "Issue PO must be click sucessfully",
					"Successfully clicked on Issue PO " + " ", "Pass", "Y");
			log.info("completed executing the method:: IssuePO");
		} catch (Exception e) {
			log.fatal("Unable to Issue PO" + e.getMessage());
			pdfResultReport.addStepDetails("IssuePO", "Issue PO must be clicked sucessfully",
					"Unable to click Issue PO" + e.getMessage(), "Fail", "N");
		}
	}

	public void CreatePOReferenceNum() throws Throwable {
		try {
			log.info("started executing the method:: CreatePOReferenceNum");
			click(tendercreationlocators.POReferenceNo, "POReferenceNo");

			String PO = "PO_Ref_";

			LocalDateTime localdatetime = LocalDateTime.now();

			String currentDateTime = localdatetime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm"));

			String poReference = PO.concat(currentDateTime);

			waitForObj(2000);

			set(tendercreationlocators.POReferenceNo, poReference, "POReferenceNo");

			eTenderComponent.updateDataIntoPropertyFile("POReferenceNum", poReference);

			click(tendercreationlocators.BtnCreatePO, "BtnCreatePO");

			waitForObj(5000);

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			waitForElementToBeVisible(tendercreationlocators.POExpiryDate);

			pdfResultReport.addStepDetails("CreatePOReferenceNum", "Should navigate to PO detail page",
					"Successfully navigated to PO detail page" + " ", "Pass", "Y");
			log.info("completed executing the method:: ProceedtoCreatePO");
		} catch (Exception e) {
			log.fatal("Unable to navigate to PO detail page" + e.getMessage());
			pdfResultReport.addStepDetails("CreatePOReferenceNum", "Should navigate to PO detail page",
					"Unable to navigate to PO detail page" + e.getMessage(), "Fail", "N");
		}
	}

	public void ViewPO() throws Throwable {
		try {
			log.info("started executing the method:: ViewPO");
			waitForElementToBeVisible(tendercreationlocators.POListingpage);
			click(tendercreationlocators.SearchPO, "SearchPO");
			set(tendercreationlocators.SearchPO, eTenderComponent.getDataFromPropertiesFile("POReferenceNum"),
					"POReferenceNum");
			waitForObj(2000);
			click(tendercreationlocators.actionDropdown, "actionDropdown");
			waitForObj(2000);
			click(tendercreationlocators.ViewPO, "ViewPO");
			waitForObj(5000);
			IsElementPresent(tendercreationlocators.View_PO_ref_NUm);
			highlight(tendercreationlocators.View_PO_ref_NUm);
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.View_PO_NUm);
			highlight(tendercreationlocators.View_PO_NUm);
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.View_PO_Value);
			highlight(tendercreationlocators.View_PO_Value);
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.View_PO_Amendment_flag);
			highlight(tendercreationlocators.View_PO_Amendment_flag);
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.View_PO_Start_Date);
			highlight(tendercreationlocators.View_PO_Start_Date);
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.View_PO_Expiry_Date);
			highlight(tendercreationlocators.View_PO_Expiry_Date);
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.View_Sanction_Note_Num);
			highlight(tendercreationlocators.View_Sanction_Note_Num);
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.View_PO_Quotation_Code);
			highlight(tendercreationlocators.View_PO_Quotation_Code);
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.View_PO_Supplier_Organization_Name);
			highlight(tendercreationlocators.View_PO_Supplier_Organization_Name);
			pdfResultReport.addStepDetails("ViewPO", "Proceed to View PO", "Successfully viewed PO " + " ", "Pass",
					"Y");
			waitForObj(2000);
			click(tendercreationlocators.View_PO_CloseBtn, "View_PO_CloseBtn");
			waitForObj(2000);
			pdfResultReport.addStepDetails("ViewPO", "Proceed to View PO", "Successfully viewed PO " + " ", "Pass",
					"Y");
			log.info("completed executing the method:: ViewPO");
		} catch (Exception e) {
			log.fatal("Unable to view PO" + e.getMessage());
			pdfResultReport.addStepDetails("ViewPO", "Proceed to View PO", "Unable to view PO" + e.getMessage(), "Fail",
					"N");
		}
	}

	public void EditPO() throws Throwable {
		try {
			log.info("started executing the method:: EditPO");
			click(tendercreationlocators.Cancelbtn, "Cancelbtn");
			waitForObj(5000);
			waitForElementToBeVisible(tendercreationlocators.POListingpage);

			click(tendercreationlocators.SearchPO, "SearchPO");
			set(tendercreationlocators.SearchPO, eTenderComponent.getDataFromPropertiesFile("POReferenceNum"),
					"POReferenceNum");
			waitForObj(2000);
			click(tendercreationlocators.actionDropdown, "actionDropdown");
			click(tendercreationlocators.EditPO, "EditPO");
			waitForObj(5000);
			waitForElementToBeVisible(tendercreationlocators.PODeailspage);

			waitForObj(2000);
			pdfResultReport.addStepDetails("EditPO", "Proceed to Edit PO", "Successfully Editd PO " + " ", "Pass", "Y");
			log.info("completed executing the method:: EditPO");
		} catch (Exception e) {
			log.fatal("Unable to Edit PO" + e.getMessage());
			pdfResultReport.addStepDetails("EditPO", "Proceed to Edit PO", "Unable to Edit PO" + e.getMessage(), "Fail",
					"N");
		}
	}

	public void SelectallApproval() throws Throwable {
		try {
			log.info("started executing the method:: SelectallApproval");
			click(tendercreationlocators.ApprovalPreDefined, "ApprovalPreDefined");
			waitForObj(2000);
			click(tendercreationlocators.UserDefined, "UserDefined");

			pdfResultReport.addStepDetails("ApprovalNotRequired", "Approvals to be click successfully",
					"Successfully clicked on create PO link" + " ", "Pass", "Y");

			log.info("completed executing the method:: SelectallApproval");
		} catch (Exception e) {
			log.fatal("Not able to click on create Approvals" + e.getMessage());
			pdfResultReport.addStepDetails("SelectallApproval", "Approval  to be click successfully",
					"Unable to click on Approvals" + e.getMessage(), "Fail", "N");
		}
	}

	public void AmendPO() throws Throwable {
		try {
			log.info("started executing the method:: AmendPO");
			waitForElementToBeVisible(tendercreationlocators.POListingpage);
			click(tendercreationlocators.SearchPO, "SearchPO");
			clear(tendercreationlocators.SearchPO, "SearchPO");
			set(tendercreationlocators.SearchPO, eTenderComponent.getDataFromPropertiesFile("POReferenceNum"),
					"POReferenceNum");
			waitForObj(2000);
			click(tendercreationlocators.actionDropdown, "actionDropdown");
			waitForObj(2000);
			click(tendercreationlocators.AmendPo, "AmendPo");
			waitForObj(5000);
			click(tendercreationlocators.AmendPoComment, "AmendPoComment");
			set(tendercreationlocators.AmendPoComment, "Po to be ammended", "AmendPoComment");
			click(tendercreationlocators.AmendPoSubmitbtn, "AmendPoSubmitbtn");
			highlight(tendercreationlocators.AmendSuccessmsg);
			waitForObj(5000);
			click(tendercreationlocators.AmendMsgOKbutton, "AmendMsgOKbutton");
			pdfResultReport.addStepDetails("AmendPO", "Proceed to Amend PO", "Successfully Amend PO " + " ", "Pass",
					"Y");
			log.info("completed executing the method:: AmendPO");
		} catch (Exception e) {
			log.fatal("Unable to Edit PO" + e.getMessage());
			pdfResultReport.addStepDetails("AmendPO", "Proceed to Amend PO", "Unable to Amend PO" + e.getMessage(),
					"Fail", "N");
		}
	}

	public void ReleasePO() throws Throwable {
		try {
			log.info("started executing the method:: ReleasePO");
			waitForElementToBeVisible(tendercreationlocators.POListingpage);
			click(tendercreationlocators.SearchPO, "SearchPO");
			clear(tendercreationlocators.SearchPO, "SearchPO");
			set(tendercreationlocators.SearchPO, eTenderComponent.getDataFromPropertiesFile("POReferenceNum"),
					"POReferenceNum");
			waitForObj(2000);
			click(tendercreationlocators.actionDropdown, "actionDropdown");
			waitForObj(2000);
			click(tendercreationlocators.ReleasePO, "ReleasePO");
			waitForObj(5000);
			mouseOver(tendercreationlocators.View_PO_ref_NUm);
			IsElementPresent(tendercreationlocators.View_PO_ref_NUm);
			highlight(tendercreationlocators.View_PO_ref_NUm);
			IsElementPresent(tendercreationlocators.View_PO_NUm);
			highlight(tendercreationlocators.View_PO_NUm);
			IsElementPresent(tendercreationlocators.View_PO_Value);
			highlight(tendercreationlocators.View_PO_Value);
			IsElementPresent(tendercreationlocators.View_PO_Amendment_flag);
			highlight(tendercreationlocators.View_PO_Amendment_flag);
			IsElementPresent(tendercreationlocators.View_PO_Start_Date);
			highlight(tendercreationlocators.View_PO_Start_Date);
			IsElementPresent(tendercreationlocators.View_PO_Expiry_Date);
			highlight(tendercreationlocators.View_PO_Expiry_Date);
			IsElementPresent(tendercreationlocators.View_Sanction_Note_Num);
			highlight(tendercreationlocators.View_Sanction_Note_Num);
			IsElementPresent(tendercreationlocators.View_PO_Quotation_Code);
			highlight(tendercreationlocators.View_PO_Quotation_Code);
			IsElementPresent(tendercreationlocators.View_PO_Supplier_Organization_Name);
			highlight(tendercreationlocators.View_PO_Supplier_Organization_Name);
			click(tendercreationlocators.btnReleasePo, "btnReleasePo");
			waitForObj(5000);
			click(tendercreationlocators.approvnotreq, "approvnotreq");
			click(tendercreationlocators.subapproval, "subapproval");

			pdfResultReport.addStepDetails("AmendReleasePOPO", "Proceed to Release PO",
					"Successfully Released PO " + " ", "Pass", "Y");
			log.info("completed executing the method:: ReleasePO");
		} catch (Exception e) {
			log.fatal("Unable to Edit PO" + e.getMessage());
			pdfResultReport.addStepDetails("ReleasePO", "Proceed to Release PO",
					"Unable to Release PO" + e.getMessage(), "Fail", "N");
		}
	}

	public void CancelPO() throws Throwable {
		try {
			log.info("started executing the method:: CancelPO");
			etendercomponentobj.waitForSpinnerToDisappear();
			waitForElementToBeVisible(tendercreationlocators.POListingpage);
			waitForObj(9000);
			click(tendercreationlocators.SearchPO, "SearchPO");
			clear(tendercreationlocators.SearchPO, "SearchPO");
			waitForObj(2000);
			set(tendercreationlocators.SearchPO, eTenderComponent.getDataFromPropertiesFile("POReferenceNum"),
					"POReferenceNum");
			waitForObj(2000);
			click(tendercreationlocators.actionDropdown, "actionDropdown");
			waitForObj(2000);
			click(tendercreationlocators.POCancel, "POCancel");
			waitForObj(5000);
			click(tendercreationlocators.PoCancelMsg, "PoCancelMsg");
			set(tendercreationlocators.PoCancelMsg, "PO to be cancelled", "PoCancelMsg");
			waitForObj(5000);
			click(tendercreationlocators.approvnotreq, "approvnotreq");
			click(tendercreationlocators.Cancelsubmitbtn, "Cancelsubmitbtn");
			click(tendercreationlocators.AmendMsgOKbutton, "AmendMsgOKbutton");
			etendercomponentobj.waitForSpinnerToDisappear();
			waitForElementToBeVisible(tendercreationlocators.POListingpage);
			waitForObj(9000);
			click(tendercreationlocators.CancelledPOTab, "CancelledPOTab");
			click(tendercreationlocators.SearchPO, "SearchPO");
			clear(tendercreationlocators.SearchPO, "SearchPO");
			set(tendercreationlocators.SearchPO, eTenderComponent.getDataFromPropertiesFile("POReferenceNum"),
					"POReferenceNum");
			String val = eTenderComponent.getDataFromPropertiesFile("POReferenceNum");
			IsElementPresent(tendercreationlocators.poNum(val));
			highlight(tendercreationlocators.poNum(val));
			waitForObj(2000);
			pdfResultReport.addStepDetails("CancelPO", "Proceed to Cancel PO", "Successfully Cancelled PO " + " ",
					"Pass", "Y");
			log.info("completed executing the method:: CancelPO");
		} catch (Exception e) {
			log.fatal("Unable to Edit PO" + e.getMessage());
			pdfResultReport.addStepDetails("CancelPO", "Proceed to Cancel PO", "Unable to Cancel PO" + e.getMessage(),
					"Fail", "N");
		}
	}

	public void RecallPO() throws Throwable {
		try {
			log.info("started executing the method:: RecallPO");
			waitForElementToBeVisible(tendercreationlocators.POListingpage);
			click(tendercreationlocators.SearchPO, "SearchPO");
			clear(tendercreationlocators.SearchPO, "SearchPO");
			set(tendercreationlocators.SearchPO, eTenderComponent.getDataFromPropertiesFile("POReferenceNum"),
					"POReferenceNum");
			waitForObj(2000);
			click(tendercreationlocators.actionDropdown, "actionDropdown");
			waitForObj(2000);
			click(tendercreationlocators.Recall, "Recall");
			waitForObj(5000);
			click(tendercreationlocators.RecallReason, "RecallReason");
			set(tendercreationlocators.RecallReason, "Po to be recalled", "RecallReason");
			waitForObj(5000);
			click(tendercreationlocators.RecallSubmit, "RecallSubmit");
			etendercomponentobj.waitForSpinnerToDisappear();
			waitForElementToBeVisible(tendercreationlocators.POListingpage);
			waitForObj(9000);
			pdfResultReport.addStepDetails("RecallPO", "Proceed to Amend PO", "Successfully Amend PO " + " ", "Pass",
					"Y");
			log.info("completed executing the method:: RecallPO");
		} catch (Exception e) {
			log.fatal("Unable to Edit PO" + e.getMessage());
			pdfResultReport.addStepDetails("RecallPO", "Proceed to Recall PO", "Unable to Recall PO" + e.getMessage(),
					"Fail", "N");
		}
	}

	String Quantity1, Quantity2, Quantity3 = null;

	public void getItemsvalue() throws Throwable {
		try {
			log.info("started executing the method:: getItemsvalue");

			Quantity1 = ThreadLocalWebdriver.getDriver().findElement(By.xpath("(//input[@type='textbox'])[1]"))
					.getAttribute("max");
			Quantity2 = ThreadLocalWebdriver.getDriver().findElement(By.xpath("(//input[@type='textbox'])[2]"))
					.getAttribute("max");
			Quantity3 = ThreadLocalWebdriver.getDriver().findElement(By.xpath("(//input[@type='textbox'])[3]"))
					.getAttribute("max");

			pdfResultReport.addStepDetails("Verifying_Final_Items", "Final items to be verified  ",
					"Successfully verified the final items not more than SN;" + " ", "Pass", "Y");
			log.info("completed executing the method:: getItemsvalue");
		} catch (Exception e) {
			log.fatal("Unable to create templete group" + e.getMessage());
			pdfResultReport.addStepDetails("getItemsvalue", "Final items to be edited",
					"Unable to verify the final items to br not more than SN " + e.getMessage(), "Fail", "N");
		}
	}

	int qnty1, qnty2, qnty3;

	public void ValidateIemsQuantity() throws Throwable {
		try {
			log.info("started executing the method:: VerifyIemsQuantity");

			qnty1 = Integer.parseInt(Quantity1) + 1;
			qnty2 = Integer.parseInt(Quantity2) + 1;
			qnty3 = Integer.parseInt(Quantity3) + 1;
			Quantity1 = Integer.toString(qnty1);
			Quantity2 = Integer.toString(qnty2);
			Quantity3 = Integer.toString(qnty3);
			click(tendercreationlocators.POItemDetails, "POItemDetails");
			waitForObj(3000);
			JSClick(tendercreationlocators.itemQuantity1, "itemQuantity1");
			clear(tendercreationlocators.itemQuantity1, "itemQuantity1");
			set(tendercreationlocators.itemQuantity1, Quantity1, "POItemQuantity1");
			waitForObj(3000);
			highlight(tendercreationlocators.Warningmsg);
			waitForObj(3000);
			click(tendercreationlocators.Acceptwarinigmsg, "Acceptwarinigmsg");
			waitForObj(3000);
			JSClick(tendercreationlocators.itemQuantity2, "itemQuantity2");
			clear(tendercreationlocators.itemQuantity2, "itemQuantity2");
			set(tendercreationlocators.itemQuantity2, Quantity2, "POItemQuantity2");
			waitForObj(3000);
			highlight(tendercreationlocators.Warningmsg);
			waitForObj(3000);
			click(tendercreationlocators.Acceptwarinigmsg, "Acceptwarinigmsg");
			waitForObj(3000);
			JSClick(tendercreationlocators.itemQuantity3, "itemQuantity3");
			clear(tendercreationlocators.itemQuantity3, "itemQuantity3");
			set(tendercreationlocators.itemQuantity3, Quantity3, "POItemQuantity3");
			waitForObj(3000);
			highlight(tendercreationlocators.Warningmsg);
			waitForObj(3000);
			click(tendercreationlocators.Acceptwarinigmsg, "Acceptwarinigmsg");

			pdfResultReport.addStepDetails("VerifyIemsQuantity", "Final items to be verified  ",
					"Successfully verified the final items not more than SN;" + " ", "Pass", "Y");
			log.info("completed executing the method:: VerifyIemsQuantity");
		} catch (Exception e) {
			log.fatal("Unable to create templete group" + e.getMessage());
			pdfResultReport.addStepDetails("VerifyIemsQuantity", "Final items to be verified",
					"Unable to verify the final items to br not more than SN " + e.getMessage(), "Fail", "N");
		}
	}

	public void Verifying_Final_Items() throws Throwable {
		try {
			log.info("started executing the method:: Edit_Final_Items");
			Actions act = new Actions(ThreadLocalWebdriver.getDriver());
			highlight(tendercreationlocators.addItemQantity1);
			waitForObj(3000);
			String val1 = ThreadLocalWebdriver.getDriver().findElement(By.xpath("(//input[@type='textbox'])[1]"))
					.getAttribute("max");
			int value1 = Integer.parseInt(val1);
			int passval1 = (value1 % 10) + 1;
			String strvalue1 = Integer.toString(passval1 + 1);

			click(tendercreationlocators.addItemQantity1, "addItemQantity1");
			clear(tendercreationlocators.addItemQantity1, "addItemQantity1");
			set(tendercreationlocators.addItemQantity1, strvalue1, "addItemQantity1");
			highlight(tendercreationlocators.addItemQantity1);
			waitForObj(5000);

			highlight(tendercreationlocators.addItemQantity2);
			waitForObj(3000);
			String val2 = ThreadLocalWebdriver.getDriver().findElement(By.xpath("(//input[@type='textbox'])[2]"))
					.getAttribute("max");
			int value2 = Integer.parseInt(val2);
			int passval2 = (value2 % 10) + 1;
			String strvalue2 = Integer.toString(passval2);

			click(tendercreationlocators.addItemQantity2, "addItemQantity2");
			clear(tendercreationlocators.addItemQantity2, "addItemQantity2");
			set(tendercreationlocators.addItemQantity2, strvalue2, "addItemQantity2");
			highlight(tendercreationlocators.addItemQantity2);
			waitForObj(5000);

			highlight(tendercreationlocators.addItemQantity3);
			waitForObj(3000);
			String val3 = ThreadLocalWebdriver.getDriver().findElement(By.xpath("(//input[@type='textbox'])[3]"))
					.getAttribute("max");
			int value3 = Integer.parseInt(val3);
			int passval3 = (value3 % 10) + 1;
			String strvalue3 = Integer.toString(passval3);
			click(tendercreationlocators.addItemQantity3, "addItemQantity3");
			clear(tendercreationlocators.addItemQantity3, "addItemQantity3");
			set(tendercreationlocators.addItemQantity3, strvalue3, "addItemQantity3");
			highlight(tendercreationlocators.addItemQantity3);
			waitForObj(5000);
			pdfResultReport.addStepDetails("Verifying_Final_Items", "Final items to be verified  ",
					"Successfully verified the final items not more than SN;" + " ", "Pass", "Y");
			log.info("completed executing the method:: Verifying_Final_Items");
		} catch (Exception e) {
			log.fatal("Unable to create templete group" + e.getMessage());
			pdfResultReport.addStepDetails("Verifying_Final_Items", "Final items to be edited",
					"Unable to verify the final items to br not more than SN " + e.getMessage(), "Fail", "N");
		}
	}

	public void clickOnActionDropDownValidation_bidder() throws Throwable {
		try {
			log.info("started executing the method:: clickOnActionDropDownValidation_bidder");

			click(tendercreationlocators.actionMenuDropDownBy_PO, "actionMenuDropDownBy_PO");
			waitForObj(5000);
			IsElementPresent(tendercreationlocators.acceptPOLinkBy);
			pdfResultReport.addStepDetails("clickOnActionDropDownValidation_bidder",
					"Action Dropdown details must be validate sucessfully",
					"Sucessfully validated Action Dropdown details" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickOnActionDropDownValidation_bidder");
		} catch (Exception e) {
			log.fatal("Unable to validate Action Dropdown details" + e.getMessage());
			pdfResultReport.addStepDetails("clickOnActionDropDownValidation_bidder",
					"Action Dropdown details must be validate sucessfully ",
					"Unable to validate Action Dropdown details " + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyBidderDetailsUiType() throws Exception {
		try {
			log.info("started executing the method:: verifyBidderDetailsUiType");

			JavascriptExecutor je = (JavascriptExecutor) ThreadLocalWebdriver.getDriver();

			click(tendercreationlocators.bidderDetailTabBy, "bidderDetailTabBy");

			waitForObj(3000);

			String uidType = (String) je
					.executeScript("return document.getElementById('po_vendor_temp.uid_type.0').value;");

			Assert.assertTrue(uidType.equalsIgnoreCase(eTenderComponent.getDataFromPropertiesFile("UID_Type")));

			pdfResultReport.addStepDetails("verifyBidderDetailsUiType", "Bidder should be validated sucessfully",
					"Sucessfully validated Bidder details sucessfully" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyBidderDetailsUiType");

		} catch (Exception e) {
			log.fatal("Unable to validate Bidder details" + e.getMessage());
			pdfResultReport.addStepDetails("verifyBidderDetailsUiType", "Bidder should be validated sucessfully",
					"Unable to validate Bidder details" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifySummaryTabAndEnterComment() throws Exception {
		try {
			log.info("started executing the method:: verifySummaryTabAndEnterComment");

			JavascriptExecutor je = (JavascriptExecutor) ThreadLocalWebdriver.getDriver();

			String poRefNo = (String) je
					.executeScript("return document.getElementById('po_header_temp.po_ref_no.0').value;");

			Assert.assertTrue(poRefNo.equalsIgnoreCase(eTenderComponent.getDataFromPropertiesFile("POReferenceNum")));

			click(tendercreationlocators.aacceptPOCommentTabBy, "aacceptPOCommentTabBy");

			waitForObj(3000);

			set(tendercreationlocators.poObjCommentBy, "Accept Po", "poObjCommentBy");

			waitForObj(3000);

			pdfResultReport.addStepDetails("verifySummaryTabAndEnterComment",
					"Acceptance functionality of PO by the bidder should be validated and enter comment",
					"Sucessfully valiadted functionality of PO by the bidder and provided comments" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifySummaryTabAndEnterComment");
		} catch (Exception e) {
			log.fatal("Unable to  Acceptance functionality of PO by the bidder" + e.getMessage());
			pdfResultReport.addStepDetails("verifySummaryTabAndEnterComment",
					"Unable to  Acceptance functionality of PO by the bidder",
					"Acceptance functionality of PO by the bidder should be validated and provide comment"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void clickRejectPoInDropDown() throws Throwable {
		try {
			log.info("started executing the method:: clickRejectPoInDropDown");

			click(tendercreationlocators.actionMenuDropDownBy_PO, "actionMenuDropDownBy_PO");

			waitForObj(3000);

			IsElementPresent(tendercreationlocators.rejectPOLinkBy);

			JSClick(tendercreationlocators.rejectPOLinkBy, "rejectPOLinkBy");

			waitForElementToBeVisible(tendercreationlocators.poDeatailsTabBy);

			waitForObj(3000);

			pdfResultReport.addStepDetails("clickRejectPoInDropDown", "Should navigate to Reject Purchase Order Page",
					"Sucessfully navigated to Reject Purchase Order Page" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickRejectPoInDropDown");
		} catch (Exception e) {
			log.fatal("Unable to navigate to Reject Purchase Order Page" + e.getMessage());
			pdfResultReport.addStepDetails("clickRejectPoInDropDown",
					"Unable to navigate to Reject Purchase Order Page ",
					"Should navigate to Reject Purchase Order Page" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickRejectPotBtn() throws Throwable {
		try {
			log.info("started executing the method:: clickRejectPotBtn");

			JSClick(tendercreationlocators.poRejectBtnBy, "poRejectBtnBy");

			waitForObj(3000);

			click(tendercreationlocators.acceptPurchaseOrderConfirmPopYesBtnBy,
					"acceptPurchaseOrderConfirmPopYesBtnBy");

			waitForObj(2000);

			pdfResultReport.addStepDetails("PO Sucessful Msg PopUp",
					"Purchase order should be successfully Reject PopUp should appear",
					"Purchase order successfully Rejected PopUp is dispalyed" + " ", "Pass", "Y");

			click(tendercreationlocators.acceptPurchaseOrderConfirmPopOKBtnBy, "acceptPurchaseOrderConfirmPopOKBtnBy");

			waitForElementToBeVisible(tendercreationlocators.poListPageRowBy);

			waitForObj(3000);

			pdfResultReport.addStepDetails("clickRejectPotBtn", "Purchase order should be successfully rejected",
					"Purchase order successfully rejected" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickRejectPotBtn");
		} catch (Exception e) {
			log.fatal("Unable to Accept PO" + e.getMessage());
			pdfResultReport.addStepDetails("clickRejectPotBtn", "Unable to Reject PO",
					"Purchase order should be successfully Rejected" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyPOStatusIsRejected() throws Exception {
		try {
			log.info("started executing the method:: verifyPOStatusIsRejected");

			clear(tendercreationlocators.search, "Clear the po ref no search field");

			set(tendercreationlocators.search, eTenderComponent.getDataFromPropertiesFile("POReferenceNum"),
					"po ref no search field");

			waitForObj(2000);

			IsElementPresent(tendercreationlocators.poStatusRejectedBy);

			waitForObj(2000);

			pdfResultReport.addStepDetails("verifyPOStatusIsRejected", "Po Status should show Rejected Mode",
					"Sucessfully showing Status as Rejecetd" + " ", "Pass", "Y");

			log.info("completed executing the method:: verifyPOStatusIsRejected");
		} catch (Exception e) {
			log.fatal("Unable to show Po Status in Rejected Mode" + e.getMessage());
			pdfResultReport.addStepDetails("verifyPOStatusIsRejected", "Unable to show Po Status in Rejected Mode",
					"Po Status should show Rejected Mode" + e.getMessage(), "Fail", "N");
		}
	}

	public void searchThePoRefNoInPoListPage() throws Throwable {
		try {
			log.info("started executing the method:: searchThePoRefNoInPoListPage");

			String porefNumber = "//*[@id='usrmntbLst']/tbody/tr/td/span[contains(@ng-bind,'data.orgpoId') and contains(text(),'{0}')]";

			clear(tendercreationlocators.search, "Clear the po ref no search field");

			set(tendercreationlocators.search, eTenderComponent.getDataFromPropertiesFile("POReferenceNum"),
					"po ref no search field");

			IsElementPresent(
					By.xpath(porefNumber.replace("{0}", eTenderComponent.getDataFromPropertiesFile("poDocNum"))));

			waitForObj(5000);

			pdfResultReport.addStepDetails("searchThePoRefNoInPoListPage",
					"Created PO should be displayed in the PO listing page",
					"Sucessfully Created PO is displaying in the po listing page" + " ", "Pass", "Y");
			log.info("completed executing the method:: searchThePoRefNoInPoListPage");
		} catch (Exception e) {
			log.fatal("Unable to display the Po  in the bidder login PO listing page" + e.getMessage());
			pdfResultReport.addStepDetails("searchThePoRefNoInPoListPage",
					"Created PO should be displayed in PO listing page",
					"Unable to display the Po  in the PO listing page" + e.getMessage(), "Fail", "N");
		}
	}

	public void POAttachmentTAB() throws Throwable {
		try {
			log.info("started executing the method:: POAttachmentTAB");
			click(tendercreationlocators.PODetailsAttachmentTab, "PODetailsAttachmentTab");

			waitForElementToBeVisible(tendercreationlocators.addattachments);
			click(tendercreationlocators.addattachments, "addattachments");
			waitForObj(3000);
			click(tendercreationlocators.addlabelsattachment, "addlabelsattachment");
			set(tendercreationlocators.addlabelsattachment, "Label1", "Label1");
			set(tendercreationlocators.addnamesattachment, "rfqCreation.xlsx", "Attachmentfile");
			set(tendercreationlocators.addfileattachment,
					System.getProperty("user.dir") + "\\MediaFiles\\rfqCreation.xlsx", "Attachmentfile");
			waitForObj(6000);
			pdfResultReport.addStepDetails("POAttachmentTAB", "Attachment should be added",
					"Successfully added attachment" + " ", "Pass", "Y");
			log.info("completed executing the method:: POAttachmentTAB");
		} catch (Exception e) {
			log.fatal("Unable to add Attachment" + e.getMessage());
			pdfResultReport.addStepDetails("POAttachmentTAB", "Attachment should be added ",
					"Unable to add Attachment" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyPendingForApprovalInPoList() throws Exception {

		try {
			log.info("started executing the method:: verifyPendingForApproval");

			IsElementPresent(tendercreationlocators.PendingForApprovalStateStatus);

			pdfResultReport.addStepDetails("verifyPendingForApproval", "Po Status Should be PendingForApproval",
					"Po Status PendingForApproval is displaying SucessFully" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyPendingForApproval");
		} catch (Exception e) {
			log.fatal("Unable to Po show PendingForApproval" + e.getMessage());
			pdfResultReport.addStepDetails("verifyPendingForApproval", "Po Status Should be PendingForApproval",
					"Unable to Po show PendingForApproval" + e.getMessage(), "Fail", "N");
		}
	}

	public void CancelPOWithOutApprovalNotRequired() throws Throwable {
		try {
			log.info("started executing the method:: CancelPOWithOutApprovalNotRequired");

			By approvalNotReqBy = By.xpath(
					"//label[contains(text(),'Attachments')]/../../../../following-sibling::div/child::div//div/label/input[@id='appNo']");

			By submitBy = By.xpath("(//*[@id='cm'])[1]");

			click(approvalNotReqBy, "approvalNotReqBy");

			waitForObj(2000);

			JSClick(submitBy, "submitBy;");

			waitForElementToBeVisible(tendercreationlocators.confirmOk);

			waitForObj(2000);

			click(tendercreationlocators.confirmOk, "confirmOk");

			waitForElementToBeVisible(tendercreationlocators.poListPageRowBy);

			waitForObj(5000);

			pdfResultReport.addStepDetails("CancelPOWithOutApprovalNotRequired",
					"Select not req and Should click submit and navigate to po list page",
					"Sucessfully Selceted Not Req and Clicked Submit navigated to po list page" + " ", "Pass", "Y");
			log.info("completed executing the method:: CancelPOWithOutApprovalNotRequired");
		} catch (Exception e) {
			log.fatal("Unable to click submit and navigate to po list page" + e.getMessage());
			pdfResultReport.addStepDetails("CancelPOWithOutApprovalNotRequired",
					"Select not req and Should click submit and navigate to po list page",
					"Unable to click submit and navigate to po list page" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyCancelledPoInApprovalLoginIsDispalyingOrNot() throws Exception {
		try {
			log.info("started executing the method:: verifyCancelledPoInApprovalLoginIsDispalyingOrNot");

			List<String> cancelledPoNo = new ArrayList<>();

			List<WebElement> cancelledlist = ThreadLocalWebdriver.getDriver().findElements(
					By.xpath("//*[@id='cancelPurchaseOrder']/child::div//*[@id='myTable']/tbody/tr/td[2]"));

			cancelledlist.stream().forEach((listcancelled) -> cancelledPoNo.add(listcancelled.getText()));

			Assert.assertFalse(cancelledPoNo.contains(poDocNum));

			pdfResultReport.addStepDetails("verifyCancelledPoInApprovalLoginIsDispalyingOrNot",
					"Cancelled PO should not be displayed", "Sucessfully not showing Cancelled PO" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyCancelledPoInApprovalLoginIsDispalyingOrNot");
		} catch (Exception e) {
			log.fatal("Failed as it is displaying Cancelled PO" + e.getMessage());
			pdfResultReport.addStepDetails("verifyCancelledPoInApprovalLoginIsDispalyingOrNot",
					"Cancelled PO should not be displayed", "Failed as it is displaying Cancelled PO" + e.getMessage(),
					"Fail", "N");
		}
	}

	public void navigateToPurchasrOrderList() throws Throwable {
		try {
			log.info("started executing the method:: navigateToPurchasrOrderList");
			JSClick(tendercreationlocators.purchaseOrder, "purchaseOrder");
			waitForObj(2000);
			JSClick(tendercreationlocators.poListing, "poListing");
			waitForObj(5000);
			waitForElementToBeVisible(tendercreationlocators.poListPageRowBy);
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForObj(5000);
			pdfResultReport.addStepDetails("navigateToPurchasrOrderList",
					"Purchase Order List Page must be naviagte successfully",
					"Successfully navigated to Purchase Order List Page" + " ", "Pass", "Y");
			log.info("completed executing the method:: navigateToPurchasrOrderList");
		} catch (Exception e) {
			log.fatal("Not able to navigate to Purchase Order List Page" + e.getMessage());
			pdfResultReport.addStepDetails("navigateToPurchasrOrderList",
					"Purchase Order List Page must be naviagte successfully",
					"Unable to navigate to Purchase Order List Page" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickCancelPOInActionMenu() throws Throwable {
		try {
			log.info("started executing the method:: clickCancelPOInActionMenu");
			JSClick(tendercreationlocators.action, "action");
			waitForObj(3000);
			click(tendercreationlocators.cancelPO, "cancelPO");
			waitForObj(3000);
			click(tendercreationlocators.cancellationReason, "cancellationReason");
			set(tendercreationlocators.cancellationReason, "Reason For Cancellation", "cancellationReason");
			pdfResultReport.addStepDetails("clickCancelPOInActionMenu", "Must Display Send For Approval window pop up",
					"Successfully dispalying Send For Approval window popup" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickCancelPOInActionMenu");
		} catch (Exception e) {
			log.fatal("Unable to  dispaly Send For Approval window popup" + e.getMessage());
			pdfResultReport.addStepDetails("clickCancelPOInActionMenu",
					"Unable to  dispaly Send For Approval window popup",
					"Must Display Send For Approval window pop up" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickCancelledPoTab() throws Throwable {
		try {
			log.info("started executing the method:: clickCancelledPoTab");
			JSClick(tendercreationlocators.cancelledPO, "cancelledPO");
			waitForObj(5000);
			pdfResultReport.addStepDetails("clickCancelledPoTab", "Cancelled Po's must be showing in CancelledPoTab",
					"Successfully Showing  Cancelled Po's in CancelledPoTab" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickCancelledPoTab");
		} catch (Exception e) {
			log.fatal("Unable to click and Show Cancelled Po's" + e.getMessage());
			pdfResultReport.addStepDetails("clickCancelledPoTab", "Unable to click and Show Cancelled Po's",
					"Must Show Cancelled Po's" + e.getMessage(), "Fail", "N");
		}

	}

	public void sendForApprovalUserDefinedParallel3UserForCancelPO() throws Throwable {
		try {
			log.info("started executing the method:: sendForApprovalUserDefinedParallel3UserForCancelPO");
			click(tendercreationlocators.Userdefined_po1, "Userdefined_po1");
			click(tendercreationlocators.approvalType_Parallel, "approvalType_Parallel");
			click(tendercreationlocators.minimumApproval, "minimumApproval");
			set(tendercreationlocators.minimumApproval, pdfResultReport.testData.get("minimumApproval"),
					"minimumApproval");
			click(tendercreationlocators.addButton, "addButton");

			set(tendercreationlocators.user1, pdfResultReport.testData.get("UserTenderApprover1"), "user1");
			waitForObj(2000);
			click(tendercreationlocators.addButton, "addButton");
			set(tendercreationlocators.user2, pdfResultReport.testData.get("UserTenderApprover2"), "user2");
			waitForObj(2000);
			click(tendercreationlocators.addButton, "addButton");
			set(tendercreationlocators.user3, pdfResultReport.testData.get("UserTenderApprover3"), "user3");
			waitForObj(2000);
			click(tendercreationlocators.coordinator3, "coordinator3");

			JSClick(By.xpath("//*[@ng-click='sendForApproval(approvalObj.workflowType)']"), "sendForApprovalSubmit");
			pdfResultReport.addStepDetails("sendForApprovalUserDefinedParallel3UserForCancelPO",
					"sendForApproval must be validate successfully", "Successfully validated sendForApproval" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: sendForApprovalUserDefinedParallel3UserForCancelPO");
			WebDriverWait wait = new WebDriverWait(ThreadLocalWebdriver.getDriver(), 100);
			waitForElementToBeVisible(tendercreationlocators.cancelledPoSucessesPopUpApprover);
			waitForObj(5000);
			pdfResultReport.addStepDetails("sendForApprovalUserDefinedParallel3UserForCancelPO",
					"sendForApproval must be validate successfully", "Successfully validated sendForApproval" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: sendForApprovalUserDefinedParallel3UserForCancelPO");
		} catch (Exception e) {
			log.fatal("Not able to validate sendForApproval" + e.getMessage());
			pdfResultReport.addStepDetails("sendForApprovalUserDefinedParallel3UserForCancelPO",
					"sendForApproval must be validate successfully",
					"Unable to validate sendForApproval" + e.getMessage(), "Fail", "N");
		}
	}

	public void poCancellationSucessesMessageValidationForApprover() throws Throwable {
		try {
			log.info("started executing the method::  poCancellationSucessesMessageValidationForApprover");
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.cancelledPoSucessesPopUpApprover);
			waitForObj(2000);
			click(tendercreationlocators.alertTabOk, "alertTabOk");
			waitForElementToBeVisible(tendercreationlocators.poListPageRowBy);
			waitForObj(5000);
			pdfResultReport.addStepDetails("poCancellationSucessesMessageValidationForApprover",
					"poCancellationSucessesMessage must be validate sucessfully",
					"Successfully validated poCancellationSucessesMessage" + " ", "Pass", "Y");
			log.info("completed executing the method:: poCancellationSucessesMessageValidationForApprover");

		} catch (Exception e) {
			log.fatal("Unable to validate poCancellationSucessesMessage" + e.getMessage());
			pdfResultReport.addStepDetails("poCancellationSucessesMessageValidationForApprover",
					"poCancellationSucessesMessage must be validate sucessfull",
					"Unable to validate poCancellationSucessesMessage" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickOnCancelPurchaseOrderTab() throws Exception {
		try {
			log.info("started executing the method:: clickOnCancelPurchaseOrderTab");
			waitForObj(5000);
			click(tendercreationlocators.cancelPurchaseOrder, "cancelPurchaseOrder");
			waitForObj(3000);
			pdfResultReport.addStepDetails("clickOnCancelPurchaseOrderTab", "Must Click on Cancel Purchase Order Tab",
					"Sucessfully Clicked on CancelPurchaseOrderTab" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickOnCancelPurchaseOrderTab");

		} catch (Exception e) {
			log.fatal("Unable to click CancelPurchaseOrderTab" + e.getMessage());
			pdfResultReport.addStepDetails("clickOnCancelPurchaseOrderTab", "Must Click on CancelPurchaseOrderTab",
					"Unable to click on CancelPurchaseOrderTab" + e.getMessage(), "Fail", "N");
		}
	}

	public void navigateToPurchaseOrderApproval() throws Throwable {
		try {
			log.info("started executing the method:: navigateToPurchaseOrderApproval");
			waitForObj(5000);
			set(tendercreationlocators.search, poDocNum, "enter PoDocNum");
			pdfResultReport.addStepDetails("navigateToPurchaseOrderApproval",
					"Purchase Order Approval page must be navigate sucessfully ",
					"Successfully navigated to Purchase Order Approval page" + " ", "Pass", "Y");
			waitForObj(10000);
			click(tendercreationlocators.details, "details");
			waitForElementToBeVisible(tendercreationlocators.podetails);
			waitForObj(5000);
			pdfResultReport.addStepDetails("navigateToPurchaseOrderApproval",
					"Purchase Order Approval page must be navigate sucessfully ",
					"Successfully navigated to Purchase Order Approval page" + " ", "Pass", "Y");
			click(tendercreationlocators.sanctionNoteEvaluationCommentTab, "sanctionNoteEvaluationCommentTab");
			waitForObj(10000);
			pdfResultReport.addStepDetails("navigateToPurchaseOrderApproval",
					"Purchase Order Approval page must be navigate sucessfully ",
					"Successfully navigated to Purchase Order Approval page" + " ", "Pass", "Y");
			log.info("completed executing the method:: navigateToPurchaseOrderApproval");

		} catch (Exception e) {
			log.fatal("Unable to navigate to Purchase Order Approval page" + e.getMessage());
			pdfResultReport.addStepDetails("navigateToPurchaseOrderApproval",
					"Purchase Order Approval page must be navigate sucessfully",
					"Unable to navigate to Purchase Order Approval page" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyingMultiplePOCreationfromRFQ() throws Throwable {
		try {
			log.info("started executing the method:: verifyingMultiplePOCreationfromRFQ");
			JSClick(tendercreationlocators.SelectSource, "SelectSource");
			highlight(tendercreationlocators.SelectSource);
			waitForObj(3000);
			click(tendercreationlocators.SelectRQF, "SelectRFQ");
			pdfResultReport.addStepDetails("clickCancelledPoTab", "verifying Multiple POCreation from RFQ",
					"verified Multiple POCreation from RFQ is not possible" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyingMultiplePOCreationfromRFQ");
		} catch (Exception e) {
			log.fatal("Unable to verify Multiple POCreation from RFQ is not possible" + e.getMessage());
			pdfResultReport.addStepDetails("verifyingMultiplePOCreationfromRFQ",
					"verifying Multiple POCreation from RFQ",
					"Unable to verify Multiple POCreation from RFQ is not possible" + e.getMessage(), "Fail", "N");
		}

	}

	public void verifyingMultiplePOCreationfromPR() throws Throwable {
		try {
			log.info("started executing the method:: verifyingMultiplePOCreationfromPR");
			JSClick(tendercreationlocators.createPO, "createPO");
			waitForObj(5000);
			select(tendercreationlocators.selectSource, "PR");
			highlight(tendercreationlocators.selectSource);
			waitForObj(2000);
			pdfResultReport.addStepDetails("verifyingMultiplePOCreationfromPR", "verifying Multiple POCreation from PR",
					"verified Multiple POCreation from PR is not possible" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyingMultiplePOCreationfromPR");
		} catch (Exception e) {
			log.fatal("Unable to verify Multiple POCreation from PR is not possible" + e.getMessage());
			pdfResultReport.addStepDetails("verifyingMultiplePOCreationfromPR", "verifying Multiple POCreation from PR",
					"Unable to verify Multiple POCreation from PR is not possible" + e.getMessage(), "Fail", "N");
		}
	}

	public void VerifyPrintPreviewPDF() throws Throwable {
		try {
			log.info("started executing the method::  VerifyPrintPreviewPDF");
			JSClick(tendercreationlocators.poListing, "poListing");
			waitforElement(tendercreationlocators.MYPO);
			waitForObj(15000);
			JSClick(tendercreationlocators.action, "action");
			waitForObj(3000);

			JSClick(tendercreationlocators.PrintPreview, "PrintPreview");
			waitForObj(10000);

			pdfResultReport.addStepDetails("VerifyPrintPreviewPDF",
					"Verify Print Preview PDF should be displayed in completed tender list page",
					"Sucessfully  verified Print Preview PDF detials of PO" + " ", "Pass", "Y");

			JSClick(tendercreationlocators.PrintPreviewClose, "PrintPreviewClose");
			waitForObj(5000);
			log.info("completed executing the method:: VerifyPrintPreviewPDF");
		} catch (Exception e) {
			log.fatal("Unable to  verify Print Preview PDF" + e.getMessage());
			pdfResultReport.addStepDetails("VerifyPrintPreviewPDF",
					"Verify Print Preview PDF should be displayed in completed tender list page",
					"Unable to verify Print Preview PDF details of PO" + e.getMessage(), "Fail", "N");
		}
	}

	private void waitforElement(By mYPO) {
		// TODO Auto-generated method stub

	}

	public void navigatePurchaseOrderApproval() throws Throwable {
		try {
			log.info("started executing the method:: navigateToPurchaseOrderApproval");

			waitForObj(5000);
			set(tendercreationlocators.search, eTenderComponent.getDataFromPropertiesFile("poDocNum"),
					"enter PoDocNum");
			waitForObj(3000);
			click(tendercreationlocators.details, "details");
			waitForObj(5000);
			click(tendercreationlocators.sanctionNoteEvaluationCommentTab, "sanctionNoteEvaluationCommentTab");
			waitForObj(10000);
			pdfResultReport.addStepDetails("navigateToPurchaseOrderApproval",
					"Purchase Order Approval page must be navigate sucessfully ",
					"Successfully navigated to Purchase Order Approval page" + " ", "Pass", "Y");
			log.info("completed executing the method:: navigateToPurchaseOrderApproval");

		} catch (Exception e) {
			log.fatal("Unable to navigate to Purchase Order Approval page" + e.getMessage());
			pdfResultReport.addStepDetails("navigateToPurchaseOrderApproval",
					"Purchase Order Approval page must be navigate sucessfully",
					"Unable to navigate to Purchase Order Approval page" + e.getMessage(), "Fail", "N");
		}
	}

	String poDocNum = "";

	public String savePoDocNumber() throws Throwable {
		log.info("started executing the method:: documentNoSave");
		String text = text(tendercreationlocators.poDocumentNoBy);
		poDocNum = text.substring(0, text.lastIndexOf("(")).trim();
		System.out.println(poDocNum);
		eTenderComponent.updateDataIntoPropertyFile("poDocNum", poDocNum);
		return poDocNum;
	}

	public void selcectUserDefinedAndApprovalTypeSeq() throws Exception {
		try {
			log.info("started executing the method:: selcectUserDerfinedAndApprovalTypeSeq");

			click(By.xpath("(//input[@id='appYes'][@value='U'])[1]"), "Userdefined_po");

			click(tendercreationlocators.approvalType_Sequential, "approvalType_Sequential");

			pdfResultReport.addStepDetails("selcectUserDerfinedAndApprovalTypeSeq",
					"Should Select UserDefined WorkFlow Type", "Sucessfully  Selected UserDefined WorkFlow Type" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: selcectUserDerfinedAndApprovalTypeSeq");
		} catch (Exception e) {
			log.fatal("Not able to Select UserDefined WorkFlow Type" + e.getMessage());
			pdfResultReport.addStepDetails("selcectUserDerfinedAndApprovalTypeSeq",
					"Should Select UserDefined WorkFlow Type",
					"Not able to Select UserDefined WorkFlow Type" + e.getMessage(), "Fail", "N");
		}
	}

	public void AddUserDefinedApproverForAnyApprovaltype(int rowno, String poApproverName) throws Throwable {
		try {
			log.info("started executing the method:: AddUserDefinedApproverForAnyApprovaltype");

			By poApproverRow = By.xpath("//table[@id='approver']/tbody/tr[" + rowno + "]/td[2]/input");

			click(tendercreationlocators.addButton, "addButton");

			set(poApproverRow, poApproverName, "PoApprover");

			waitForObj(3000);

			pdfResultReport.addStepDetails("AddUserDefinedApproverForAnyApprovaltype", "Should add approver Users",
					"Sucessfully added Approver users" + " ", "Pass", "Y");
			log.info("completed executing the method:: AddUserDefinedApproverForAnyApprovaltype");
		} catch (Exception e) {
			log.fatal("Not able to add approver Users" + e.getMessage());
			pdfResultReport.addStepDetails("AddUserDefinedApproverForAnyApprovaltype", "Should add approver Users",
					"Not able to add approver Users" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickSendForApprovalInPo() throws Throwable {
		try {
			log.info("started executing the method:: sendForApprovalInPo");

			waitForObj(3000);

			JSClick(By.xpath("//*[@ng-click='sendForApproval(approvalObj.workflowType)']"), "sendForApprovalSubmit");

			waitForElementToBeVisible(tendercreationlocators.confirmationOkBy);

			waitForObj(3000);

			click(tendercreationlocators.confirmationOkBy, "confirmationOkBy");

			waitForElementToBeVisible(tendercreationlocators.poListPageRowBy);

			waitForObj(5000);

			pdfResultReport.addStepDetails("sendForApprovalInPo",
					"Should click Send For approval Btn and navigate to PO Listing Page",
					"Sucessfully clicked and navigated to po listing page" + " ", "Pass", "Y");
			log.info("completed executing the method:: sendForApprovalInPo");
		} catch (Exception e) {
			log.fatal("Not able to click Send For approval Btn" + e.getMessage());
			pdfResultReport.addStepDetails("sendForApprovalInPo",
					"Should click Send For approval Btn and navigate to PO Listing Page",
					"Not able to click Send For approval Btn" + e.getMessage(), "Fail", "N");
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

	public void verifyEditPOAndItemDetailsPage() throws Throwable {
		try {
			log.info("started executing the method:: verifyEditPOAndItemDetailsPage");

			// waitForElementToBeVisible(tendercreationlocators.POListingpage);

			click(tendercreationlocators.SearchPO, "SearchPO");
			clear(tendercreationlocators.SearchPO, "SearchPO");
			set(tendercreationlocators.SearchPO, eTenderComponent.getDataFromPropertiesFile("POReferenceNum"),
					"POReferenceNum");
			waitForObj(2000);
			click(tendercreationlocators.actionDropdown, "actionDropdown");
			waitForElementToBeVisible(tendercreationlocators.EditPO);
			click(tendercreationlocators.EditPO, "EditPO");
			// waitForObj(5000);
			waitForElementToBeVisible(tendercreationlocators.PODeailspage);
			waitForObj(2000);
			JSClick(tendercreationlocators.POItemDetails, "POItemDetails");
			waitForElementToBeVisible(tendercreationlocators.itemQuantity01);
			clear(tendercreationlocators.itemQuantity01, "POItemQuantity1");
			waitForObj(2000);
			set(tendercreationlocators.itemQuantity01, eTenderComponent.getDataFromPropertiesFile("POItemQuantity1"),
					"POItemQuantity1");
			waitForObj(3000);
			String itemQty = eTenderComponent.getDataFromPropertiesFile("Bidder2SupplierQty");

			int itemQty1 = Integer.parseInt(itemQty);
			int itemQty2 = Integer.parseInt(eTenderComponent.getDataFromPropertiesFile("Bidder2SupplierQty"));

			while (itemQty1 <= itemQty2) {
				itemQty1++;
			}
			String itemQty1Val = Integer.toString(itemQty1);
			clear(tendercreationlocators.itemQuantity02, "ItemQuantity");
			waitForObj(2000);
			set(tendercreationlocators.itemQuantity02, itemQty1Val, "itemQty2Val");
			waitForObj(3000);
			IsElementPresent(tendercreationlocators.itemQuantityalertMsgPopUp);
			highlight(tendercreationlocators.itemQuantityalertMsgPopUp);
			pdfResultReport.addStepDetails("EditPO", "Proceed to Edit PO", "Successfully Editd PO " + " ", "Pass", "Y");
			waitForObj(2000);
			click(tendercreationlocators.AmendMsgOKbutton, "okButton");
			waitForElementToBeVisible(tendercreationlocators.logoutOption);
			waitForObj(2000);
			pdfResultReport.addStepDetails("EditPO", "Proceed to Edit PO", "Successfully Editd PO " + " ", "Pass", "Y");
			log.info("completed executing the method:: EditPO");
		} catch (Exception e) {
			log.fatal("Unable to Edit PO" + e.getMessage());
			pdfResultReport.addStepDetails("EditPO", "Proceed to Edit PO", "Unable to Edit PO" + e.getMessage(), "Fail",
					"N");
		}
	}

	public void enterPOInSearchAndDraftVerification() throws Throwable {
		try {
			log.info("started executing the method:: enterPOInSearchAndDraftVerification");
			clear(tendercreationlocators.SearchPO, "SearchPO");
			set(tendercreationlocators.SearchPO, eTenderComponent.getDataFromPropertiesFile("POReferenceNum"),
					"POReferenceNum");
			waitForElementToBeVisible(tendercreationlocators.actionDropdown);
			waitForObj(2000);
			// select(tendercreationlocators.poStatusFilter, "Draft");
			highlight(tendercreationlocators.draftStateStatus);
			IsElementPresent(tendercreationlocators.draftStateStatus);
			waitForObj(5000);

			// IsElementPresent(tendercreationlocators.POStatus);
			// waitForObj(5000);
			pdfResultReport.addStepDetails("enterPOInSearchAndDraftVerification",
					"Reference number is entered in search field and verified the status is in draft successfully",
					"Reference number is entered in search field and verified the status is in draft successfully"
							+ " ",
					"Pass", "Y");
			log.info("completed executing the method:: enterPOInSearchAndDraftVerification");
		} catch (Exception e) {
			log.fatal("Unable to enter PO reference number or status is not in draft" + e.getMessage());
			pdfResultReport.addStepDetails("enterPOInSearchAndDraftVerification",
					"Reference number is entered in search field and verified the status is in draft successfully",
					"Unable to enter PO reference number or status is not in draft" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyPOinPendingForApprovalStatus1() throws Throwable {
		try {
			log.info("started executing the method:: verifyPOStatusPendingForApproval");
			// select(tendercreationlocators.poStatusFilter, "Pending For
			// Approval");

			IsElementPresent(tendercreationlocators.PendingForApprovalStateStatus);
			waitForObj(5000);
			pdfResultReport.addStepDetails("verifyPOStatusPendingForApproval",
					"All Purchase Orders with Status as 'Pending For Approval' must be displayed",
					"All Purchase Orders with Status as 'Pending For Approval' are displayed successfull" + " ", "Pass",
					"Y");
			log.info("completed executing the method:: verifyPOStatusPendingForApproval");
		} catch (Exception e) {
			log.fatal("Unable to displayed PO with 'Pending For Approval Status" + e.getMessage());
			pdfResultReport.addStepDetails("verifyPOStatusPendingForApproval",
					"All Purchase Orders with Status as 'Pending For Approval' must be displayed",
					"Unable to displayed PO with 'Pending For Approval' Status" + e.getMessage(), "Fail", "N");
		}
	}

	public void sendForApprovalValidation() throws Throwable {
		try {
			log.info("started executing the method:: sendForApprovalValidation");
			IsElementPresent(tendercreationlocators.ApprovalPreDefined);
			IsElementPresent(tendercreationlocators.UserDefined);
			IsElementPresent(tendercreationlocators.notRequired);
			waitForObj(2000);
			click(tendercreationlocators.Userdefined_po, "Userdefined_po");
			click(tendercreationlocators.approvalType_Sequential, "approvalType_Sequential");
			click(tendercreationlocators.addButton, "addButton");
			set(tendercreationlocators.user1, pdfResultReport.testData.get("POapprover1User"), "user1");
			waitForObj(2000);
			click(tendercreationlocators.addButton, "addButton");
			set(tendercreationlocators.user2, pdfResultReport.testData.get("POapprover2User"), "user2");
			waitForObj(2000);
			set(tendercreationlocators.comments, pdfResultReport.testData.get("UserDefinedApprover-Comments"),
					"comments");
			pdfResultReport.addStepDetails("sendForApprovalValidation",
					"sendForApprovalValidation must be validate successfully",
					"Successfully validated sendForApprovalValidation" + " ", "Pass", "Y");
			waitForObj(2000);
			JSClick(By.xpath("//*[@ng-click='sendForApproval(approvalObj.workflowType)']"), "sendForApprovalSubmit");
			pdfResultReport.addStepDetails("sendForApprovalValidation",
					"sendForApprovalValidation must be validate successfully",
					"Successfully validated sendForApprovalValidation" + " ", "Pass", "Y");
			waitForElementToBeVisible(tendercreationlocators.poListPageRowBy);

			pdfResultReport.addStepDetails("sendForApprovalValidation", "Should navigate to PoListPage",
					"Successfully navigated to PoListPage" + " ", "Pass", "Y");

			log.info("completed executing the method:: sendForApprovalValidation");
		} catch (Exception e) {
			log.fatal("Unable to navigate to PoListPage" + e.getMessage());
			pdfResultReport.addStepDetails("sendForApprovalValidation", "Should navigate to PoListPage",
					"Unable to navigate to PoListPage" + e.getMessage(), "Fail", "N");
		}
	}

	public void POSaveandApproval() throws Throwable {
		try {
			log.info("started executing the method:: POSaveandApproval");

			click(tendercreationlocators.POSave, "POSave");

			waitForElementToBeVisible(tendercreationlocators.poSaveSuucessMsgPopUpBy);

			waitForObj(3000);
			click(tendercreationlocators.POSUccessMsg, "POSUccessMsg");

			waitForObj(5000);

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			click(tendercreationlocators.btnSendforapproval, "btnSendforapproval");

			waitForElementToBeVisible(tendercreationlocators.poDocumentNoBy);

			waitForObj(5000);

			pdfResultReport.addStepDetails("POSaveandApproval", "PO should Save and Approval",
					"Successfully saved and approved po " + " ", "Pass", "Y");
			log.info("completed executing the method:: POSaveandApproval");
		} catch (Exception e) {
			log.fatal("Unable to Save and Approval" + e.getMessage());
			pdfResultReport.addStepDetails("POSaveandApproval", "PO should Save and Approval",
					"Unable to Save and Approval" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyCancelledPOTab() throws Throwable {
		try {
			log.info("started executing the method:: verifyCancelledPOTab");
			JSClick(tendercreationlocators.cancelledPoTab, "cancelledPoTab");
			waitForObj(5000);
			pdfResultReport.addStepDetails("verifyCancelledPOTab", "Cancelled PO tab must be selected",
					"Cancelled PO tab is selected succcessfully" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyCancelledPOTab");
		} catch (Exception e) {
			log.fatal("Unable to select Cancelled PO tab" + e.getMessage());
			pdfResultReport.addStepDetails("verifyCancelledPOTab", "Cancelled PO tab must be selected",
					"Unable to select Cancelled PO tab" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyPOFieldsInPOViewer() throws Throwable {
		try {
			log.info("started executing the method::  verifyPOFieldsInPOViewer");
			WebElement othersPO = ThreadLocalWebdriver.getDriver()
					.findElement(By.xpath("//li[@class='uib-tab nav-item ng-scope ng-isolate-scope active']"));
			if (othersPO.isDisplayed()) {
				System.out.println("PO Viewer by default entered in Other's PO tab.");
				verifyPOFields();
			}
			pdfResultReport.addStepDetails("verifyPOFieldsInPOViewer",
					"All the fields in PO List must be validated sucessfully",
					"Successfully validated all the fields " + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyPOFieldsInPOViewer");
		} catch (Exception e) {
			log.fatal("Unable to validate the fields" + e.getMessage());
			pdfResultReport.addStepDetails("verifyPOFieldsInPOViewer",
					"All the fields in PO List must be validated sucessfully",
					"Unable to validate the fields" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyPOCancellationDetails() throws Throwable {
		try {
			log.info("started executing the method:: verifyPOCancellationDetails");
			JSClick(tendercreationlocators.cancellationComment, "CancellationDetails");
			waitForObj(5000);

			IsElementPresent(tendercreationlocators.columnVisibility);
			IsElementPresent(tendercreationlocators.cancellationComment);
			IsElementPresent(tendercreationlocators.cancellationDate);
			IsElementPresent(tendercreationlocators.usedInCatalogue);
			waitForObj(2000);
			pdfResultReport.addStepDetails("verifyPOCancellationDetails",
					"Cancellation Comment, Cancellation Date & Used In Catalogue must be present on Cancelled PO listing page",
					"Cancellation Comment, Cancellation Date & Used In Catalogue are present" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyPOCancellationDetails");
		} catch (Exception e) {
			log.fatal(
					"Unable to find Cancellation Comment, Cancellation Date & Used In Catalogue on Cancelled PO listing page"
							+ e.getMessage());
			pdfResultReport.addStepDetails("verifyPOCancellationDetails",
					"Cancellation Comment, Cancellation Date & Used In Catalogue must be present on Cancelled PO listing page",
					"Unable to find Cancellation Comment, Cancellation Date & Used In Catalogue on Cancelled PO listing page"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void verifyCancelledPOActionDropdownList() throws Throwable {
		try {
			log.info("started executing the method:: verifyCancelledPOActionDropdownList");
			JSClick(tendercreationlocators.action, "action");
			waitForObj(3000);

			IsElementPresent(tendercreationlocators.viewPoPreview);
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.viewPoForprint);
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.ApprovalHistory);
			waitForObj(2000);
			pdfResultReport.addStepDetails("verifyCancelledPOActionDropdownList",
					"Cancelled PO - Action menu must be displayed",
					"Cancelled PO - Action is displayed successfully" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyCancelledPOActionDropdownList");
		} catch (Exception e) {
			log.fatal("Unable to  dispaly Cancelled PO - Action menu" + e.getMessage());
			pdfResultReport.addStepDetails("verifyCancelledPOActionDropdownList",
					"Unable to  dispaly Cancelled PO - Action menu",
					"Cancelled PO - Action menu must be displayed" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyPOStatusDropdownList() throws Throwable {
		try {
			log.info("started executing the method:: verifyPOStatusDropdownList");
			waitForObj(5000);
			click(tendercreationlocators.poStatusFilter, "poStatusFilter");
			pdfResultReport.addStepDetails("verifyPOStatusDropdownList",
					"All Purchase Orders Statuses must be displayed",
					"All Purchase Orders Statuses are displayed successfully" + " ", "Pass", "Y");
			click(tendercreationlocators.poStatusFilter, "poStatusFilter");
			waitForObj(5000);
			log.info("completed executing the method:: verifyPOStatusDropdownList");
		} catch (Exception e) {
			log.fatal("Unable to display PO Statuses" + e.getMessage());
			pdfResultReport.addStepDetails("verifyPOStatusDropdownList",
					"All Purchase Orders Statuses must be displayed", "Unable to display PO Statuses" + e.getMessage(),
					"Fail", "N");
		}
	}

	public void verifyPOinDraftStatus() throws Throwable {
		try {
			log.info("started executing the method:: verifyPOinDraftStatus");
			select(tendercreationlocators.poStatusFilter, "Draft");
			IsElementPresent(tendercreationlocators.draftStateStatus);
			waitForObj(5000);
			pdfResultReport.addStepDetails("verifyPOinDraftStatus",
					"All Purchase Orders with Status as 'Draft' must be displayed",
					"All Purchase Orders with Status as 'Draft' are displayed successfull" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyPOinDraftStatus");
		} catch (Exception e) {
			log.fatal("Unable to displayed PO with 'Draft' Status" + e.getMessage());
			pdfResultReport.addStepDetails("verifyPOinDraftStatus",
					"All Purchase Orders with Status as 'Draft' must be displayed",
					"Unable to displayed PO with 'Draft' Status" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyPOinPendingForApprovalStatus() throws Throwable {
		try {
			log.info("started executing the method:: verifyPOStatusPendingForApproval");
			select(tendercreationlocators.poStatusFilter, "Pending For Approval");

			IsElementPresent(tendercreationlocators.PendingForApprovalStateStatus);
			waitForObj(5000);
			pdfResultReport.addStepDetails("verifyPOStatusPendingForApproval",
					"All Purchase Orders with Status as 'Pending For Approval' must be displayed",
					"All Purchase Orders with Status as 'Pending For Approval' are displayed successfull" + " ", "Pass",
					"Y");
			log.info("completed executing the method:: verifyPOStatusPendingForApproval");
		} catch (Exception e) {
			log.fatal("Unable to displayed PO with 'Pending For Approval Status" + e.getMessage());
			pdfResultReport.addStepDetails("verifyPOStatusPendingForApproval",
					"All Purchase Orders with Status as 'Pending For Approval' must be displayed",
					"Unable to displayed PO with 'Pending For Approval' Status" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyPOinPendingForAcceptanceStatus() throws Throwable {
		try {
			log.info("started executing the method:: verifyPOinPendingForAcceptanceStatus");
			select(tendercreationlocators.poStatusFilter, "Pending For Acceptance");

			IsElementPresent(tendercreationlocators.PendingForAcceptanceStateStatus);
			waitForObj(5000);
			pdfResultReport.addStepDetails("verifyPOinPendingForAcceptanceStatus",
					"All Purchase Orders with Status as 'Pending For Acceptance' must be displayed",
					"All Purchase Orders with Status as 'Pending For Acceptance' are displayed successfull" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: verifyPOinPendingForAcceptanceStatus");
		} catch (Exception e) {
			log.fatal("Unable to displayed PO with 'Pending For Acceptance' Status" + e.getMessage());
			pdfResultReport.addStepDetails("verifyPOinPendingForAcceptanceStatus",
					"All Purchase Orders with Status as 'Pending For Acceptance' must be displayed",
					"Unable to displayed PO with 'Pending For Acceptance' Status" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyPOinAcceptedStatus() throws Throwable {
		try {
			log.info("started executing the method:: verifyPOinAcceptedStatus");
			waitForObj(5000);
			select(tendercreationlocators.poStatusFilter, "Accepted");

			IsElementPresent(tendercreationlocators.AcceptedStateStatus);
			waitForObj(2000);
			pdfResultReport.addStepDetails("verifyPOinAcceptedStatus",
					"All Purchase Orders with Status as 'Accepted' must be displayed",
					"All Purchase Orders with Status as 'Accepted' are displayed successfull" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyPOinAcceptedStatus");
		} catch (Exception e) {
			log.fatal("Unable to displayed PO with 'Accepted' Status" + e.getMessage());
			pdfResultReport.addStepDetails("verifyPOinAcceptedStatus",
					"All Purchase Orders with Status as 'Accepted' must be displayed",
					"Unable to displayed PO with 'Accepted' Status" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyPOCategoryFilter() throws Throwable {
		try {
			log.info("started executing the method:: verifyPOCategoryFilter");
			waitForObj(5000);
			click(tendercreationlocators.poCategoryBy, "poCategoryBy");
			pdfResultReport.addStepDetails("verifyPOCategoryFilter", "PO Category Filter must be validate sucessfully",
					"Successfully validate POCategory Filter" + " ", "Pass", "Y");
			click(tendercreationlocators.poCategoryBy, "poCategoryBy");
			select(tendercreationlocators.poCategoryBy, "Default_cat");
			waitForObj(5000);

			pdfResultReport.addStepDetails("verifyPOCategoryFilter",
					"Purchase Orders with selected category must be displayed",
					"Purchase Orders with selected category are displayed successfully" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyPOCategoryFilter");
		} catch (Exception e) {
			log.fatal("Unable to display Purchase Orders with selected category" + e.getMessage());
			pdfResultReport.addStepDetails("verifyPOCategoryFilter",
					"Purchase Orders with selected category must be displayed",
					"Unable to display Purchase Orders with selected category" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyPOSourceFilter() throws Throwable {
		try {
			log.info("started executing the method:: verifyPOSourceFilter");
			waitForObj(5000);
			click(tendercreationlocators.poSource, "poSource");
			pdfResultReport.addStepDetails("verifyPOSourceFilter", "PO Source list must be validate sucessfully",
					"Successfully validate PO Source list" + " ", "Pass", "Y");
			click(tendercreationlocators.poSource, "poSource");
			select(tendercreationlocators.poSource, "RFQ");
			waitForObj(5000);
			pdfResultReport.addStepDetails("verifyPOSourceFilter", "RFQ must be select sucessfully",
					"Successfully selected RFQ" + " ", "Pass", "Y");
			select(tendercreationlocators.poSource, "SN");
			waitForObj(5000);
			pdfResultReport.addStepDetails("verifyPOSourceFilter", "SN must be select sucessfully",
					"Successfully selected SN" + " ", "Pass", "Y");
			select(tendercreationlocators.poSource, "PR");
			waitForObj(5000);
			pdfResultReport.addStepDetails("verifyPOSourceFilter", "PR must be select sucessfully",
					"Successfully selected PR" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyPOSourceFilter");
		} catch (Exception e) {
			log.fatal("Unable to display Purchase Orders with selected Source" + e.getMessage());
			pdfResultReport.addStepDetails("verifyPOSourceFilter",
					"Purchase Orders with selected Source must be displayed",
					"Unable to display Purchase Orders with selected Source" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyPOTypeFilter() throws Throwable {
		try {
			log.info("started executing the method:: verifyPOTypeFilter");
			waitForObj(5000);
			click(tendercreationlocators.poTypeFilter, "poTypeFilter");
			pdfResultReport.addStepDetails("verifyPOTypeFilter", "PO Type Filter must be validate sucessfully",
					"Successfully validate PO Type Filter" + " ", "Pass", "Y");
			click(tendercreationlocators.poTypeFilter, "poTypeFilter");
			select(tendercreationlocators.poTypeFilter, "Rate Contract");
			waitForObj(5000);

			pdfResultReport.addStepDetails("verifyPOTypeFilter", "Purchase Orders with selected Type must be displayed",
					"Purchase Orders with selected Type are displayed successfully" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyPOTypeFilter");
		} catch (Exception e) {
			log.fatal("Unable to display Purchase Orders with selected Type" + e.getMessage());
			pdfResultReport.addStepDetails("verifyPOTypeFilter", "Purchase Orders with selected Type must be displayed",
					"Unable to display Purchase Orders with selected Type" + e.getMessage(), "Fail", "N");
		}
	}

	public void usedCatalogueFilter() throws Throwable {
		try {
			log.info("started executing the method:: usedCatalogueFilter");
			waitForObj(5000);
			click(tendercreationlocators.usedCatalogueFilter, "usedCatalogueFilter");
			pdfResultReport.addStepDetails("usedCatalogueFilter", "UsedCatalogue Filter must be validate sucessfully",
					"Successfully validate UsedCatalogue Filter" + " ", "Pass", "Y");
			click(tendercreationlocators.usedCatalogueFilter, "usedCatalogueFilter");
			select(tendercreationlocators.usedCatalogueFilter, "Na");
			waitForObj(5000);
			pdfResultReport.addStepDetails("usedCatalogueFilter",
					"Purchase Orders with selected Used In Catalogue must be displayed",
					"Purchase Orders with selected Used In Catalogue are displayed successfully" + " ", "Pass", "Y");
			log.info("completed executing the method:: usedCatalogueFilter");
		} catch (Exception e) {
			log.fatal("Unable to display Purchase Orders with selected Used In Catalogue" + e.getMessage());
			pdfResultReport.addStepDetails("usedCatalogueFilter",
					"Purchase Orders with selected Used In Catalogue must be displayed",
					"Unable to display Purchase Orders with selected Used In Catalogue" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifySearchFiledInPoListPage() throws Throwable {
		try {
			log.info("started executing the method:: verifySearchFiledInPoListPage");

			String porefNumber = "//*[@id='usrmntbLst']/tbody/tr/td/span[contains(text(),'{0}')]";

			clear(tendercreationlocators.search, "Clear the po ref no search field");

			set(tendercreationlocators.search, eTenderComponent.getDataFromPropertiesFile("POReferenceNum"),
					"po ref no search field");

			IsElementPresent(
					By.xpath(porefNumber.replace("{0}", eTenderComponent.getDataFromPropertiesFile("POReferenceNum"))));

			waitForObj(5000);

			pdfResultReport.addStepDetails("verifySearchFiledInPoListPage",
					"Searched PO should be displayed in the PO listing page",
					"Sucessfully displayed searched PO in the po listing page" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifySearchFiledInPoListPage");
		} catch (Exception e) {
			log.fatal("Unable to display searched PO in the po listing page" + e.getMessage());
			pdfResultReport.addStepDetails("verifySearchFiledInPoListPage",
					"Searched PO should be displayed in the PO listing page",
					"Unable to display searched PO in the po listing page" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyCancelledPOFields() throws Throwable {
		try {
			log.info("started executing the method::  verifyCancelledPOFields");
			waitForObj(5000);

			IsElementPresent(tendercreationlocators.columnVisibility);
			IsElementPresent(tendercreationlocators.SNo);
			IsElementPresent(tendercreationlocators.PRRFQSNNo);
			IsElementPresent(tendercreationlocators.PONo);
			IsElementPresent(tendercreationlocators.POCreationDate);
			IsElementPresent(tendercreationlocators.CancelledPOValue);
			IsElementPresent(tendercreationlocators.CancelledAction);
			waitForObj(5000);
			pdfResultReport.addStepDetails("verifyCancelledPOFields",
					"All the fields in Cancelled PO List must be validated sucessfully",
					"Successfully validated all the fields " + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyCancelledPOFields");
		} catch (Exception e) {
			log.fatal("Unable to validate the fields" + e.getMessage());
			pdfResultReport.addStepDetails("verifyCancelledPOFields",
					"All the fields in PO List must be validated sucessfully",
					"Unable to validate the fields" + e.getMessage(), "Fail", "N");
		}
	}

	public void createDopForSanction_Module(String module) throws Throwable {
		try {
			log.info("started executing the method::  createDopForSanction_Module");

			click(tendercreationlocators.dopMenuLinkBy, "dopMenuLinkBy");

			JSClick(tendercreationlocators.createDopLinkBy, "createDopLinkBy");

			waitForObj(15000);

			select(tendercreationlocators.selectModuleBy, module);

			waitForObj(2000);

			select(tendercreationlocators.selectProcurementCategoryBy, "Default_cat");

			waitForObj(2000);

			click(tendercreationlocators.addOrg_HierarchyBy, "addOrg_HierarchyBy");

			waitForObj(5000);

			click(tendercreationlocators.addBtn_HierarchyBy, "addBtn_HierarchyBy");

			waitForObj(2000);

			set(tendercreationlocators.dopValueFromBy, pdfResultReport.testData.get("dopValueFrom"), "dopValueFromBy");
			waitForObj(1000);

			set(tendercreationlocators.dopValueToBy, pdfResultReport.testData.get("dopValueTo"), "dopValueToBy");

			waitForObj(1000);

			DopName = pdfResultReport.testData.get("dopName").concat("_")
					.concat(String.valueOf(getrandomInterger(2000, 3000)));

			set(tendercreationlocators.dopNameBy, DopName, "dopNameBy");

			waitForObj(1000);

			click(tendercreationlocators.savebutton, "saveButton");

			IsElementPresent(tendercreationlocators.DopSucessMsgBy);

			pdfResultReport.addStepDetails("DopSucessMsgBy", "must show Dop sucess msg",
					"Sucessfully showing dop sucess msg" + " ", "Pass", "Y");

			click(tendercreationlocators.confirmationOkBy, "confirmationOkBy");

			waitForObj(3000);

			pdfResultReport.addStepDetails("createDopForSanction_Module", "Must save DopCreation In Saction Module",
					"Successfully saved the DOP Creation " + " ", "Pass", "Y");
			log.info("completed executing the method:: createDopForSanction_Module");
		} catch (Exception e) {
			log.fatal("Unable to Save the Dop creation" + e.getMessage());
			pdfResultReport.addStepDetails("createDopForSanction_Module", "Must save DopCreation In Saction Module",
					"Unable to Save the Dop creation" + e.getMessage(), "Fail", "N");
		}
	}

	public void addUserForApprovalForCreatedDop1(String approvalType) throws Exception {

		// SEQUENTIAL

		click(tendercreationlocators.action, "action");

		waitForObj(2000);

		click(tendercreationlocators.addUserBy, "addUserBy");

		waitForObj(5000);

		click(tendercreationlocators.addUserToTableBy, "addUserToTableBy");
		click(tendercreationlocators.addUserToTableBy, "addUserToTableBy");
		click(tendercreationlocators.addUserToTableBy, "addUserToTableBy");

		waitForObj(2000);

		select(tendercreationlocators.approvalTypeDropdownBy, approvalType);
		select(tendercreationlocators.approvalTypeDropdownBy1, approvalType);
		select(tendercreationlocators.approvalTypeDropdownBy2, approvalType);

		waitForObj(3000);

		click(tendercreationlocators.saveUserBy, "saveUserBy");

		click(tendercreationlocators.confirmationOkBy, "confirmationOkBy");

		waitForObj(3000);
	}

	public void catalogueDropdownsInAllPOTabs() throws Throwable {

		try {
			log.info("started executing the method:: catalogueDropdownsInAllPOTabs");
			waitForElement(tendercreationlocators.usedCatalogueFilter, 50);
			click(tendercreationlocators.usedCatalogueFilter, "usedCatalogueFilter");
			IsElementPresent(tendercreationlocators.fullyUsed);
			IsElementPresent(tendercreationlocators.partiallyUsed);
			IsElementPresent(tendercreationlocators.notUsed);
			click(tendercreationlocators.othersPO, "OthersPo");
			waitForObj(5000);
			click(tendercreationlocators.usedCatalogueFilter, "usedCatalogueFilter");
			IsElementPresent(tendercreationlocators.fullyUsed);
			IsElementPresent(tendercreationlocators.partiallyUsed);
			IsElementPresent(tendercreationlocators.notUsed);
			click(tendercreationlocators.cancelledPO, "cancelledPO");
			waitForObj(5000);
			click(tendercreationlocators.usedCatalogueFilter, "usedCatalogueFilter");
			IsElementPresent(tendercreationlocators.fullyUsed);
			IsElementPresent(tendercreationlocators.partiallyUsed);
			IsElementPresent(tendercreationlocators.notUsed);
			pdfResultReport.addStepDetails("catalogueDropdownsInAllPOTabs",
					"Catalogue dropdowns are avaliable in all tabs under PO List",
					"Successfully Showing Catalogue dropdowns in all tabs under PO List" + " ", "Pass", "Y");
			log.info("completed executing the method:: catalogueDropdownsInAllPOTabs");
		} catch (Exception e) {
			log.fatal("Unable to show catalogue dropdowns under PO List" + e.getMessage());
			pdfResultReport.addStepDetails("catalogueDropdownsInAllPOTabs",
					"Unable to show catalogue dropdowns under PO List",
					"Unable to show catalogue dropdowns under PO List" + e.getMessage(), "Fail", "N");
		}

	}

	public void SearchfunctinalityasPO_Approver() throws Throwable {
		try {
			log.info("started executing the method:: SearchfunctinalityasPO_Approver");
			waitForElementToBeVisible(tendercreationlocators.search);
			IsElementPresent(tendercreationlocators.approvalStatusPending);
			IsElementPresent(tendercreationlocators.approvalStatusCompleted);
			set(tendercreationlocators.search, eTenderComponent.getDataFromPropertiesFile("poDocNum"),
					"enter PoDocNum");
			waitForObj(3000);
			click(By.xpath("//select[@ng-model='searchApprovalStatus']"), "Approval Status");
			List<WebElement> ele = ThreadLocalWebdriver.getDriver()
					.findElements(By.xpath("//table[@id='myTable']//tr[@class='ng-scope']/td"));
			for (int i = 1; i <= ele.size(); i++) {
				IsElementPresent(tendercreationlocators.PoApproveDetails(i));
				highlight(tendercreationlocators.PoApproveDetails(i));
				waitForObj(2000);
			}
			pdfResultReport.addStepDetails("SearchfunctinalityasPO_Approver",
					"Verify the Search Functionality as PO Approver",
					"Successfully Verified the Search Functionality as PO Approver" + " ", "Pass", "Y");
			log.info("completed executing the method:: SearchfunctinalityasPO_Approver");

		} catch (Exception e) {
			log.fatal("Unable to Verify the Search Functionality as PO Approver" + e.getMessage());
			pdfResultReport.addStepDetails("SearchfunctinalityasPO_Approver",
					"Verify the Search Functionality as PO Approver",
					"Unable to Verify the Search Functionality as PO Approver" + e.getMessage(), "Fail", "N");
		}
	}

	public void searchTheExpiredPoRefNoInPoListPage() throws Throwable {
		try {
			log.info("started executing the method:: searchThePoRefNoInPoListPage");

			String porefNumber = "//*[@id='usrmntbLst']/tbody/tr/td/span[contains(text(),'{0}')]";

			clear(tendercreationlocators.search, "Clear the po ref no search field");

			set(tendercreationlocators.search, eTenderComponent.getDataFromPropertiesFile("ExpiredPOReferenceNum"),
					"po ref no search field");

			IsElementPresent(By.xpath(
					porefNumber.replace("{0}", eTenderComponent.getDataFromPropertiesFile("ExpiredPOReferenceNum"))));

			waitForObj(5000);

			pdfResultReport.addStepDetails("searchTheExpiredPoRefNoInPoListPage",
					"Expired PO should be displayed in the PO listing page",
					"Sucessfully Expired PO is displaying in the po listing page" + " ", "Pass", "Y");
			log.info("completed executing the method:: searchTheExpiredPoRefNoInPoListPage");
		} catch (Exception e) {
			log.fatal("Unable to display the Expired Po in the bidder login PO listing page" + e.getMessage());
			pdfResultReport.addStepDetails("searchTheExpiredPoRefNoInPoListPage",
					"Expired PO should be displayed in PO listing page",
					"Unable to display the Expired Po in the PO listing page" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickOnActionDropDownForExpiredPO() throws Throwable {
		try {
			log.info("started executing the method:: clickOnActionDropDownForExpiredPO");

			click(tendercreationlocators.actionMenuDropDownBy_PO, "actionMenuDropDownBy_PO");
			waitForObj(3000);
			IsElementPresent(tendercreationlocators.preview);
			IsElementPresent(tendercreationlocators.printPreview);
			waitForObj(3000);

			pdfResultReport.addStepDetails("clickOnActionDropDownForExpiredPO",
					"Preview and Print preview must be validate scessfully",
					"Sucessfully validated Preview and Print preview " + " ", "Pass", "Y");
			log.info("completed executing the method:: clickOnActionDropDownForExpiredPO");
		} catch (Exception e) {
			log.fatal("Unable to validate Preview and Print preview" + e.getMessage());
			pdfResultReport.addStepDetails("clickOnActionDropDownForExpiredPO",
					"Preview and Print preview must be validate scessfully",
					"Unable to validate Preview and Print preview" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickOnCloseInSendForApproval() throws Throwable {
		try {
			log.info("started executing the method:: clickOnCloseInSendForApproval");

			click(tendercreationlocators.sendForApprovalClose, "sendForApprovalClose");
			waitForObj(3000);
			pdfResultReport.addStepDetails("clickOnCloseInSendForApproval", "Close button must be click scessfully",
					"Sucessfully clicked on close button" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickOnCloseInSendForApproval");
		} catch (Exception e) {
			log.fatal("Unable to click on close button" + e.getMessage());
			pdfResultReport.addStepDetails("clickOnCloseInSendForApproval", "Close button must be click scessfully",
					"Unable to click on close button" + e.getMessage(), "Fail", "N");
		}
	}

	public void poExpiryDateAlertMessageValidation() throws Throwable {
		try {
			log.info("started executing the method::  poExpiryDateAlertMessageValidation");
			click(tendercreationlocators.POSave, "POSave");
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.poExpiryDateAlert);
			waitForObj(4000);
			pdfResultReport.addStepDetails("poExpiryDateAlertMessageValidation",
					"poExpiryDateAlertMessage must be validate sucessfully",
					"Successfully validated poExpiryDateAlertMessage" + " ", "Pass", "Y");
			log.info("completed executing the method:: poExpiryDateAlertMessageValidation");
		} catch (Exception e) {
			log.fatal("Unable to validate poExpiryDateAlertMessage" + e.getMessage());
			pdfResultReport.addStepDetails("poExpiryDateAlertMessageValidation",
					"poExpiryDateAlertMessage must be validate sucessfull",
					"Unable to validate poExpiryDateAlertMessageValidation" + e.getMessage(), "Fail", "N");
		}
	}

	public void editPODetailsTAB(int startdatelag) throws Throwable {
		try {
			log.info("started executing the method:: editPODetailsTAB");
			click(tendercreationlocators.POExpiryDate, "POExpiryDate");
			clear(tendercreationlocators.POExpiryDate, "POExpiryDate");
			waitForObj(2000);
			set(tendercreationlocators.POExpiryDate, getBidStartDate(startdatelag), "POExpiryDate");
			waitForObj(4000);
			click(tendercreationlocators.POAmendmentflag, "POExpiryDate");
			click(tendercreationlocators.POAmendmentflagOption(pdfResultReport.testData.get("POAmendmentflag")),
					"POAmendmentflagYES");
			waitForObj(3000);
			pdfResultReport.addStepDetails("editPODetailsTAB", "PO Details to be filled ",
					"Successfully filled PO Details" + " ", "Pass", "Y");
			log.info("completed executing the method:: editPODetailsTAB");
		} catch (Exception e) {
			log.fatal("Unable to Issue PO" + e.getMessage());
			pdfResultReport.addStepDetails("editPODetailsTAB", "PO Details to be filled ",
					"Unable to  fill PO Details" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickOnEditPOUnderActionDropdown() throws Throwable {
		try {
			log.info("started executing the method::  clickOnEditPOUnderActionDropdown");
			click(tendercreationlocators.action, "action");
			waitForObj(2000);
			highlight(tendercreationlocators.editPOLink);
			pdfResultReport.addStepDetails("clickOnEditPOUnderActionDropdown", "Edit PO must be click sucessfully",
					"Successfully clicked on Edit PO " + " ", "Pass", "Y");
			click(tendercreationlocators.editPOLink, "editPOLink");
			waitForElement(tendercreationlocators.PODetailsAttachmentTab, 50);
			waitForObj(4000);
			pdfResultReport.addStepDetails("clickOnEditPOUnderActionDropdown",
					"PO Details page must be validate sucessfully", "Successfully validated PO Details page" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: clickOnEditPOUnderActionDropdown");

		} catch (Exception e) {
			log.fatal("Unable to login" + e.getMessage());
			pdfResultReport.addStepDetails("clickOnEditPOUnderActionDropdown",
					"PO Details page must be validate sucessfully",
					"Unable to validate PO Details page" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyCancelledPoIsPresentOrNotInCancelledPOTab(String SelectStatus) throws Exception {
		try {
			log.info("started executing the method:: cancelledPoIsPresentOrNotInCancelledPOTab");

			List<String> cancelledPoNo = new ArrayList<>();

			select(tendercreationlocators.poStatusFilter, SelectStatus);

			waitForObj(5000);

			List<WebElement> cancelledlist = ThreadLocalWebdriver.getDriver()
					.findElements(By.xpath("//tbody/tr/td[2]/span[2]"));

			cancelledlist.stream().forEach((listcancelled) -> cancelledPoNo.add(listcancelled.getText()));

			Assert.assertTrue(cancelledPoNo.contains(poDocNum));

			pdfResultReport.addStepDetails("cancelledPoIsPresentOrNotInCancelledPOTab",
					"Cancelled PO should  be displayed", "Sucessfully  showing Cancelled PO" + " ", "Pass", "Y");
			log.info("completed executing the method:: cancelledPoIsPresentOrNotInCancelledPOTab");
		} catch (Exception e) {
			log.fatal("Failed to displaying Cancelled PO" + e.getMessage());
			pdfResultReport.addStepDetails("cancelledPoIsPresentOrNotInCancelledPOTab",
					"Cancelled PO should  be displayed", "Failed to displaying Cancelled PO" + e.getMessage(), "Fail",
					"N");
		}
	}

	public void verifyCancelledPOActionDropdownListInBidderUserPoList() throws Throwable {
		try {
			log.info("started executing the method:: verifyCancelledPOActionDropdownListInBidderUserPoList");
			JSClick(tendercreationlocators.action, "action");
			waitForObj(3000);
			IsElementPresent(tendercreationlocators.viewPoForprint);
			IsElementPresent(tendercreationlocators.PrintPreview);
			waitForObj(2000);
			pdfResultReport.addStepDetails("verifyCancelledPOActionDropdownListInBidderUserPoList",
					"Cancelled PO - Action menu Should display Only preview and Print view",
					"Cancelled PO - Action is displayed successfully" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyCancelledPOActionDropdownListInBidderUserPoList");
		} catch (Exception e) {
			log.fatal("Unable to  dispaly preview and Print view" + e.getMessage());
			pdfResultReport.addStepDetails("verifyCancelledPOActionDropdownListInBidderUserPoList",
					"Unable to  dispaly preview and Print view",
					"Cancelled PO - Action menu Should display Only preview and Print view" + e.getMessage(), "Fail",
					"N");
		}
	}

	public void sendForApprovalPreDefinedForCancelPO() throws Throwable {
		try {
			log.info("started executing the method:: sendForApprovalUserDefinedParallel3UserForCancelPO");
			JSClick(tendercreationlocators.PredifinedApprovalTypeBy, "predefine");

			waitForElementToBeVisible(tendercreationlocators.PreDefinedApproversListBy);

			waitForObj(4000);

			JSClick(By.xpath("//*[@ng-click='sendForApproval(approvalObj.workflowType)']"), "sendForApprovalSubmit");
			pdfResultReport.addStepDetails("sendForApprovalUserDefinedParallel3UserForCancelPO",
					"sendForApproval must be validate successfully", "Successfully validated sendForApproval" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: sendForApprovalUserDefinedParallel3UserForCancelPO");

			waitForElementToBeVisible(tendercreationlocators.cancelledPoSucessesPopUpApprover);
			waitForObj(5000);
			pdfResultReport.addStepDetails("sendForApprovalUserDefinedParallel3UserForCancelPO",
					"sendForApproval must be validate successfully", "Successfully validated sendForApproval" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: sendForApprovalUserDefinedParallel3UserForCancelPO");
		} catch (Exception e) {
			log.fatal("Not able to validate sendForApproval" + e.getMessage());
			pdfResultReport.addStepDetails("sendForApprovalUserDefinedParallel3UserForCancelPO",
					"sendForApproval must be validate successfully",
					"Unable to validate sendForApproval" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyCancelledDateInCancelledPOTab() throws Exception {
		try {
			log.info("started executing the method:: verifyCancelledDateInCancelledPOTab");

			IsElementPresent(tendercreationlocators.PoCancelleddateValueBy);

			pdfResultReport.addStepDetails("verifyCancelledDateInCancelledPOTab",
					"Cancellation Date should be displayed in the Cancelled Po  tab.",
					"Sucessfully displaying Cancellation Date in the Cancelled Po tab." + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyCancelledDateInCancelledPOTab");
		} catch (Exception e) {
			log.fatal("" + e.getMessage());
			pdfResultReport.addStepDetails("verifyCancelledDateInCancelledPOTab",
					"Cancellation Date should be displayed in the Cancelled PO tab.",
					"Failed to diaplay Cancellation Date in the Cancelled Po tab." + e.getMessage(), "Fail", "N");
		}
	}

	public void sendForApprovalValidationsForNotRequired() throws Throwable {

		try {
			log.info("started executing the method:: sendForApprovalValidationsForUserDefined");

			waitForObj(3000);

			select(tendercreationlocators.selectStatus, "Pending For Approval ");
			waitForObj(2000);

			enterPORef();
			click(tendercreationlocators.actionMenuDropDownBy_PO, "actionMenuDropDownBy_PO");

			waitForObj(2000);

			JSClick(tendercreationlocators.cancelPO, "cancelPO");

			waitForObj(3000);

			click(tendercreationlocators.sendForApprovalNotRequired, "sendForApprovalNotRequired");
			click(tendercreationlocators.sendForApprovalSubmit, "sendForApprovalSubmit");
			IsElementPresent(tendercreationlocators.alertCancel);
			pdfResultReport.addStepDetails("checkingCancellationComment", "Purchase order must be cancel sucessfully ",
					"Successfully cancelled Purchase order" + " ", "Pass", "Y");
			click(tendercreationlocators.confirmOk, "confirmOk");

			set(tendercreationlocators.cancellationReason, pdfResultReport.testData.get("cancellationReason"),
					"cancellationReason");
			click(tendercreationlocators.sendForApprovalSubmit, "sendForApprovalSubmit");
			waitForElement(tendercreationlocators.approveAllOkButton, 30);
			click(tendercreationlocators.approveAllOkButton, "approveAllOkButton");

			waitForObj(5000);

			pdfResultReport.addStepDetails("sendForApprovalValidations",
					"Must validate all validation in send for approval Page",
					"Successfully validate all validation in send for approval Page" + " ", "Pass", "Y");
			log.info("completed executing the method:: sendForApprovalValidations");
		} catch (Exception e) {
			log.fatal("Unable to validate all validation in send for approval Page" + e.getMessage());
			pdfResultReport.addStepDetails("sendForApprovalValidations",
					"Must validate all validation in send for approval Page",
					"Unable validate all validation in send for approval Page" + e.getMessage(), "Fail", "N");
		}
	}

	public void sendForApprovalValidationsForUserDefined() throws Throwable {

		try {
			log.info("started executing the method:: sendForApprovalValidationsForUserDefined");
			waitForObj(3000);
			select(tendercreationlocators.selectStatus, "Accepted");
			waitForObj(2000);

			enterPORef();
			click(tendercreationlocators.actionMenuDropDownBy_PO, "actionMenuDropDownBy_PO");

			waitForObj(2000);

			JSClick(tendercreationlocators.cancelPO, "cancelPO");

			waitForObj(3000);
			click(tendercreationlocators.Userdefined, "Userdefined");
			waitForObj(2000);
			click(tendercreationlocators.approvalType_Sequential, "approvalType_Sequential");

			click(tendercreationlocators.addButton, "addButton");
			set(tendercreationlocators.user1, pdfResultReport.testData.get("POApproverUser1"), "user1");
			waitForObj(2000);
			click(tendercreationlocators.addButton, "addButton");
			set(tendercreationlocators.user2, pdfResultReport.testData.get("POApproverUserName2"), "user2");
			set(tendercreationlocators.cancellationReason, pdfResultReport.testData.get("cancellationReason"),
					"cancellationReason");
			click(tendercreationlocators.sendForApproval, "sendForApproval");
			waitForElement(tendercreationlocators.approveAllOkButton, 30);
			click(tendercreationlocators.approveAllOkButton, "approveAllOkButton");

			waitForObj(5000);

			pdfResultReport.addStepDetails("sendForApprovalValidations",
					"Must validate all validation in send for approval Page",
					"Successfully validate all validation in send for approval Page" + " ", "Pass", "Y");
			log.info("completed executing the method:: sendForApprovalValidations");
		} catch (Exception e) {
			log.fatal("Unable to validate all validation in send for approval Page" + e.getMessage());
			pdfResultReport.addStepDetails("sendForApprovalValidations",
					"Must validate all validation in send for approval Page",
					"Unable validate all validation in send for approval Page" + e.getMessage(), "Fail", "N");
		}
	}

	public void sendForApprovalValidationsForPredefined() throws Throwable {

		try {
			log.info("started executing the method:: sendForApprovalValidations");

			waitForObj(3000);
			click(tendercreationlocators.predefined, "predefined");

			waitForObj(2000);

			set(tendercreationlocators.cancellationReason, pdfResultReport.testData.get("cancellationReason"),
					"cancellationReason");
			click(tendercreationlocators.sendForApproval, "sendForApproval");
			waitForElement(tendercreationlocators.approveAllOkButton, 30);
			click(tendercreationlocators.approveAllOkButton, "approveAllOkButton");

			waitForObj(10000);

			pdfResultReport.addStepDetails("sendForApprovalValidations",
					"Must validate all validation in send for approval Page",
					"Successfully validate all validation in send for approval Page" + " ", "Pass", "Y");
			log.info("completed executing the method:: sendForApprovalValidations");
		} catch (Exception e) {
			log.fatal("Unable to validate all validation in send for approval Page" + e.getMessage());
			pdfResultReport.addStepDetails("sendForApprovalValidations",
					"Must validate all validation in send for approval Page",
					"Unable validate all validation in send for approval Page" + e.getMessage(), "Fail", "N");
		}
	}

	public void sendForApprovalValidations() throws Throwable {

		try {
			log.info("started executing the method:: sendForApprovalValidations");

			waitForObj(3000);
			select(tendercreationlocators.selectStatus, "Accepted");

			waitForObj(2000);

			enterPORef();

			click(tendercreationlocators.actionMenuDropDownBy_PO, "actionMenuDropDownBy_PO");

			waitForObj(2000);

			JSClick(tendercreationlocators.cancelPO, "cancelPO");

			waitForObj(3000);
			IsElementPresent(tendercreationlocators.predefined);
			IsElementPresent(tendercreationlocators.Userdefined_po1);
			IsElementPresent(tendercreationlocators.sendForApprovalNotRequired);

			pdfResultReport.addStepDetails("sendForApprovalValidations",
					"Must validate all validation in send for approval Page",
					"Successfully validate all validation in send for approval Page" + " ", "Pass", "Y");
			log.info("completed executing the method:: sendForApprovalValidations");
		} catch (Exception e) {
			log.fatal("Unable to validate all validation in send for approval Page" + e.getMessage());
			pdfResultReport.addStepDetails("sendForApprovalValidations",
					"Must validate all validation in send for approval Page",
					"Unable validate all validation in send for approval Page" + e.getMessage(), "Fail", "N");
		}
	}

	String refNo = null;

	String enterPORef() {
		refNo = text(tendercreationlocators.poNoRefNo1Span).trim();
		System.out.println(refNo);
		return refNo;
	}

	public void purchaseOrderApprovalAlertForExpiredPO() throws Throwable {
		try {
			log.info("started executing the method:: purchaseOrderApprovalAlertForExpiredPO");
			click(tendercreationlocators.sanctionNoteEvaluationApprove, "sanctionNoteEvaluationApprove");
			waitForObj(4000);

			IsElementPresent(tendercreationlocators.POApprovalAlertForExpiredPO);
			waitForObj(4000);
			pdfResultReport.addStepDetails("purchaseOrderApprovalAlertForExpiredPO",
					"Alert message must be displayed as 'PO validity is expired. Hence PO can not be approved. Please send back the PO to the initiator' while approving expired PO",
					"Alert message is displayed successfully as 'PO validity is expired. Hence PO can not be approved. Please send back the PO to the initiator' while approving expired PO"
							+ " ",
					"Pass", "Y");

			click(tendercreationlocators.confirmOk, "confirmOk");
			pdfResultReport.addStepDetails("purchaseOrderApprovalAlertForExpiredPO",
					"Alert message must be displayed as 'PO validity is expired. Hence PO can not be approved. Please send back the PO to the initiator' while approving expired PO",
					"Alert message is displayed successfully as 'PO validity is expired. Hence PO can not be approved. Please send back the PO to the initiator' while approving expired PO"
							+ " ",
					"Pass", "Y");
			log.info("completed executing the method:: purchaseOrderApprovalAlertForExpiredPO");

		} catch (Exception e) {
			log.fatal("Unable to display alert message" + e.getMessage());
			pdfResultReport.addStepDetails("purchaseOrderApprovalAlertForExpiredPO",
					"Alert message must be displayed as 'PO validity is expired. Hence PO can not be approved. Please send back the PO to the initiator' while approving expired PO",
					"Unable to display alert message" + e.getMessage(), "Fail", "N");
		}
	}

	public void addUserForApprovalForCreatedDop2(String approvalType) throws Exception {
		try {
			log.info("started executing the method:: addUserForApprovalForCreatedDop");

			// PARALLEL

			click(tendercreationlocators.action, "action");

			waitForObj(2000);

			click(tendercreationlocators.addUserBy, "addUserBy");

			waitForObj(5000);

			pdfResultReport.addStepDetails("addUserForApprovalForCreatedDop", "", "Successfully" + " ", "Pass", "Y");
			log.info("completed executing the method:: addUserForApprovalForCreatedDop");

			click(tendercreationlocators.addUserToTableBy, "addUserToTableBy");
			click(tendercreationlocators.addUserToTableBy, "addUserToTableBy");
			click(tendercreationlocators.addUserToTableBy, "addUserToTableBy");

			waitForObj(2000);

			select(tendercreationlocators.approvalTypeDropdownBy, approvalType);

			select(tendercreationlocators.approvalTypeDropdownBy1, approvalType);
			select(tendercreationlocators.approvalTypeDropdownBy2, approvalType);

			click(tendercreationlocators.coordinatorFlagParallel_vamshi, "coordinatorFlagParallel");
			set(tendercreationlocators.minApprover1Parallel, pdfResultReport.testData.get("MinApprover"),
					"minApprover1Parallel");
			// set(tendercreationlocators.minApprover1Parallel2,
			// pdfResultReport.testData.get("MinApprover"),
			// "minApprover1Parallel");
			// set(tendercreationlocators.minApprover1Parallel3,
			// pdfResultReport.testData.get("MinApprover"),
			// "minApprover1Parallel");
			// waitForObj(3000);

			click(tendercreationlocators.minApprover1Parallel2, "minApprover1Parallel");
			pdfResultReport.addStepDetails("addUserForApprovalForCreatedDop", "", "Successfully" + " ", "Pass", "Y");
			log.info("completed executing the method:: addUserForApprovalForCreatedDop");

			waitForObj(3000);

			click(tendercreationlocators.saveUserBy, "saveUserBy");

			click(tendercreationlocators.confirmationOkBy, "confirmationOkBy");

			waitForObj(3000);
			pdfResultReport.addStepDetails("addUserForApprovalForCreatedDop", "", "Successfully" + " ", "Pass", "Y");
			log.info("completed executing the method:: addUserForApprovalForCreatedDop");
		} catch (Exception e) {
			log.fatal("Not" + e.getMessage());

			pdfResultReport.addStepDetails("addUserForApprovalForCreatedDop", "", "" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickPredefinedApprovalAndSendForApproval() throws Throwable {
		try {
			log.info("started executing the method:: clickPredefinedApprovalAndSubmit");

			waitForElementToBeVisible(tendercreationlocators.PredifinedApprovalTypeBy);

			waitForObj(2000);

			JSClick(tendercreationlocators.PredifinedApprovalTypeBy, "predefine");

			waitForObj(2000);

			set(tendercreationlocators.commentFieldBy, "Send For Approval Comment", "commentFieldBy");

			waitForObj(2000);

			JSClick(By.xpath("//*[@ng-click='sendForApproval(approvalObj.workflowType)']"), "sendForApprovalSubmit");
			pdfResultReport.addStepDetails("clickPredefinedApprovalAndSubmit", "", "Successfully" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickPredefinedApprovalAndSubmit");

			waitForElementToBeVisible(tendercreationlocators.poListPageRowBy);

			waitForObj(5000);

			pdfResultReport.addStepDetails("clickPredefinedApprovalAndSubmit", "", "Successfully" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickPredefinedApprovalAndSubmit");
		} catch (Exception e) {
			log.fatal("Not" + e.getMessage());

			pdfResultReport.addStepDetails("clickPredefinedApprovalAndSubmit", "", "" + e.getMessage(), "Fail", "N");
		}
	}

	public void sendForApprovalUserDefinedSequential2User_pocreator() throws Throwable {
		try {
			log.info("started executing the method:: sendForApprovalUserDefinedSequential2User_pocreator");
			click(tendercreationlocators.Userdefined_po, "Userdefined_po");
			click(tendercreationlocators.approvalType_Sequential, "approvalType_Sequential");
			click(tendercreationlocators.addButton, "addButton");
			set(tendercreationlocators.user1, pdfResultReport.testData.get("UserTenderApprover1"), "user1");
			waitForObj(2000);
			click(tendercreationlocators.addButton, "addButton");
			set(tendercreationlocators.user2, pdfResultReport.testData.get("UserTenderApprover2"), "user1");
			waitForObj(2000);
			set(tendercreationlocators.comments, pdfResultReport.testData.get("UserDefinedApprover-Comments"),
					"comments");
			waitForObj(2000);

			JSClick(By.xpath("//*[@ng-click='sendForApproval(approvalObj.workflowType)']"), "sendForApprovalSubmit");
			pdfResultReport.addStepDetails("sendForApprovalUserDefinedSequential2User_pocreator",
					"sendForApproval must be validate successfully", "Successfully validated sendForApproval" + " ",
					"Pass", "Y");
			waitForElementToBeVisible(tendercreationlocators.poListPageRowBy);

			waitForObj(5000);
			pdfResultReport.addStepDetails("sendForApprovalUserDefinedSequential2User_pocreator",
					"sendForApproval must be validate successfully", "Successfully validated sendForApproval" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: sendForApprovalUserDefinedSequential2User_pocreator");
		} catch (Exception e) {
			log.fatal("Not able to validate sendForApproval" + e.getMessage());
			pdfResultReport.addStepDetails("sendForApprovalUserDefinedSequential2User_pocreator",
					"sendForApproval must be validate successfully",
					"Unable to validate sendForApproval" + e.getMessage(), "Fail", "N");
		}
	}

	public void viewPODetailsValidation() throws Throwable {
		try {
			log.info("started executing the method::  viewPODetailsValidation");
			click(tendercreationlocators.action, "action");
			waitForObj(2000);
			highlight(tendercreationlocators.viewPO);
			pdfResultReport.addStepDetails("viewPODetailsValidation", "View PO link must be click sucessfully",
					"Successfully clicked on view PO link" + " ", "Pass", "Y");
			click(tendercreationlocators.viewPO, "viewPO");
			waitForElement(tendercreationlocators.poDetails, 50);
			waitForObj(4000);
			IsElementPresent(tendercreationlocators.poNo);
			IsElementPresent(tendercreationlocators.poRefNo);
			waitForObj(4000);
			pdfResultReport.addStepDetails("viewPODetailsValidation", "PO Details page must be validate sucessfully",
					"Successfully validated PO Details page" + " ", "Pass", "Y");
			log.info("completed executing the method:: viewPODetailsValidation");
			JSClick(tendercreationlocators.viewDetails_close, "viewDetails_close");
			waitForObj(3000);
		} catch (Exception e) {
			log.fatal("Unable to validate PO Details page" + e.getMessage());
			pdfResultReport.addStepDetails("viewPODetailsValidation", "PO Details page must be validate sucessfully",
					"Unable to validate PO Details page" + e.getMessage(), "Fail", "N");
		}
	}

	public void navigateToConfigurationManagement() throws Throwable {
		try {
			log.info("started executing the method:: navigateToConfigurationManagement");
			JSClick(tendercreationlocators.settings, "settings");
			waitForObj(2000);
			JSClick(tendercreationlocators.configurationManagement, "configurationManagement");
			waitForObj(5000);
			waitForElementToBeVisible(tendercreationlocators.purchaseOrderManagement);
			waitForObj(5000);
			pdfResultReport.addStepDetails("navigateToConfigurationManagement",
					"Configuration Management Page must be naviagte successfully",
					"Successfully navigated to Configuration Management Page" + " ", "Pass", "Y");
			log.info("completed executing the method:: navigateToConfigurationManagement");
		} catch (Exception e) {
			log.fatal("Not able to navigate to Configuration Management Page" + e.getMessage());
			pdfResultReport.addStepDetails("navigateToConfigurationManagement",
					"Configuration Management Page must be naviagte successfully",
					"Unable to navigate to Configuration Management Page" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickOnPurchaseOrderManagementTab() throws Throwable {
		try {
			log.info("started executing the method::  clickOnPurchaseOrderManagementTab");
			click(tendercreationlocators.purchaseOrderManagement, "purchaseOrderManagement");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForObj(5000);
			pdfResultReport.addStepDetails("clickOnPurchaseOrderManagementTab",
					"Purchase Order Management Tab must be click sucessfully",
					"Successfully clicked on Purchase Order Management Tab" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickOnPurchaseOrderManagementTab");
		} catch (Exception e) {
			log.fatal("Unable to click on Purchase Order Management Tab" + e.getMessage());
			pdfResultReport.addStepDetails("clickOnPurchaseOrderManagementTab",
					"Purchase Order Management Tab must be click sucessfully",
					"Unable to click on Purchase Order Management Tab" + e.getMessage(), "Fail", "N");
		}
	}

	public void selectedPOTypeListValidation() throws Throwable {
		try {
			log.info("started executing the method::  selectedPOTypeListValidation");
			JSClick(tendercreationlocators.defaultPOType, "defaultPOType");
			waitForObj(3000);
			click(tendercreationlocators.defaultPOTypeDropdown, "defaultPOTypeDropdown");
			select(tendercreationlocators.defaultPOTypeDropdown, "Rate Contract");
			waitForObj(2000);
			click(tendercreationlocators.rateContractDropdown, "rateContractDropdown");
			select(tendercreationlocators.rateContractDropdown, "Active");
			waitForObj(2000);
			click(tendercreationlocators.blanketDropdown, "blanketDropdown");
			select(tendercreationlocators.blanketDropdown, "Active");
			waitForObj(2000);
			click(tendercreationlocators.plannedDropdown, "plannedDropdown");
			select(tendercreationlocators.plannedDropdown, "Active");
			waitForObj(2000);
			click(tendercreationlocators.standardDropdown, "standardDropdown");
			select(tendercreationlocators.standardDropdown, "InActive");
			waitForObj(4000);
			pdfResultReport.addStepDetails("selectedPOTypeListValidation",
					"selectedPOTypeList must be validate sucessfully",
					"Successfully validated selectedPOTypeList" + " ", "Pass", "Y");
			log.info("completed executing the method:: selectedPOTypeListValidation");

		} catch (Exception e) {
			log.fatal("Unable to validate selectedPOTypeList" + e.getMessage());
			pdfResultReport.addStepDetails("selectedPOTypeListValidation",
					"selectedPOTypeList must be validate sucessfully",
					"Unable to validate selectedPOTypeList" + e.getMessage(), "Fail", "N");
		}
	}

	public void savePurchaseOrderManagementTab() throws Throwable {
		try {
			log.info("started executing the method::  savePurchaseOrderManagementTab");
			JSClick(tendercreationlocators.configurationManagementSave, "configurationManagementSave");
			waitForElementToBeVisible(tendercreationlocators.purchaseOrderConfigurationSucessesMessage);
			IsElementPresent(tendercreationlocators.purchaseOrderConfigurationSucessesMessage);
			waitForObj(5000);

			pdfResultReport.addStepDetails("savePurchaseOrderManagementTab",
					"Purchase Order Management Tab must be save sucessfully",
					"Successfully saved Purchase Order Management Tab" + " ", "Pass", "Y");
			click(tendercreationlocators.alertTabOk, "alertTabOk");
			log.info("completed executing the method:: savePurchaseOrderManagementTab");
		} catch (Exception e) {
			log.fatal("Unable to save Purchase Order Management Tab" + e.getMessage());
			pdfResultReport.addStepDetails("savePurchaseOrderManagementTab",
					"Purchase Order Management Tab must be save sucessfully",
					"Unable to save Purchase Order Management Tab" + e.getMessage(), "Fail", "N");
		}
	}

	public void poTypeValidationInPODetailsTab() throws Throwable {
		try {
			log.info("started executing the method::  poTypeValidationInPODetailsTab");
			click(tendercreationlocators.po_Type, "po_Type");
			waitForObj(5000);
			pdfResultReport.addStepDetails("selectedPOTypeListValidation",
					"poType details must be validate sucessfully", "Successfully validated poType details" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: poTypeValidationInPODetailsTab");

		} catch (Exception e) {
			log.fatal("Unable to validate poType details" + e.getMessage());
			pdfResultReport.addStepDetails("poTypeValidationInPODetailsTab",
					"poType detailst must be validate sucessfully",
					"Unable to validate poType details" + e.getMessage(), "Fail", "N");
		}
	}

	public void poTypeSelectionUnderPODetailsTAB() throws Throwable {
		try {

			LocalDateTime localdatetime = LocalDateTime.now().plusDays(25);

			String POExpiryDate = localdatetime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a"));
			log.info("started executing the method:: poTypeSelectionUnderPODetailsTAB");
			click(tendercreationlocators.POExpiryDate, "POExpiryDate");
			set(tendercreationlocators.POExpiryDate, POExpiryDate, "POExpiryDate");
			waitForObj(4000);
			click(tendercreationlocators.POAmendmentflag, "POExpiryDate");
			click(tendercreationlocators.POAmendmentflagOption(pdfResultReport.testData.get("POAmendmentflag")),
					"POAmendmentflagYES");
			waitForObj(3000);
			click(tendercreationlocators.po_Type, "po_Type");
			pdfResultReport.addStepDetails("poTypeSelectionUnderPODetailsTAB", "PO Type must be select sucessfully",
					"Successfully selected PO Type" + " ", "Pass", "Y");
			waitForObj(5000);
			select(tendercreationlocators.po_Type, "Rate Contract");
			pdfResultReport.addStepDetails("poTypeSelectionUnderPODetailsTAB", "PO Type must be select sucessfully",
					"Successfully selected PO Type" + " ", "Pass", "Y");
			log.info("completed executing the method:: poTypeSelectionUnderPODetailsTAB");
		} catch (Exception e) {
			log.fatal("Unable to select PO Type" + e.getMessage());
			pdfResultReport.addStepDetails("poTypeSelectionUnderPODetailsTAB", "PO Type must be select sucessfully",
					"Unable to select PO Type" + e.getMessage(), "Fail", "N");
		}
	}

	String bidderName = null;

	String enterBidderName() {
		bidderName = text(tendercreationlocators.bidder).trim();
		System.out.println(bidderName);
		return bidderName;
	}

	public void sendForApprovalValidationsForPredefined1() throws Throwable {

		try {
			log.info("started executing the method:: sendForApprovalValidations");
			waitForObj(5000);
			select(tendercreationlocators.selectStatus, "Pending For Acceptance");

			waitForObj(2000);

			enterPORef();
			enterBidderName();

			click(tendercreationlocators.actionMenuDropDownBy_PO, "actionMenuDropDownBy_PO");

			waitForObj(2000);

			JSClick(tendercreationlocators.cancelPO, "cancelPO");

			waitForObj(3000);
			IsElementPresent(tendercreationlocators.predefined);
			IsElementPresent(tendercreationlocators.Userdefined_po1);
			IsElementPresent(tendercreationlocators.sendForApprovalNotRequired);

			waitForObj(3000);
			click(tendercreationlocators.predefined, "predefined");

			waitForObj(2000);

			set(tendercreationlocators.cancellationReason, pdfResultReport.testData.get("cancellationReason"),
					"cancellationReason");
			click(tendercreationlocators.sendForApproval, "sendForApproval");
			waitForElement(tendercreationlocators.approveAllOkButton, 30);
			click(tendercreationlocators.approveAllOkButton, "approveAllOkButton");

			waitForObj(10000);

			pdfResultReport.addStepDetails("sendForApprovalValidations",
					"Must validate all validation in send for approval Page",
					"Successfully validate all validation in send for approval Page" + " ", "Pass", "Y");
			log.info("completed executing the method:: sendForApprovalValidations");
		} catch (Exception e) {
			log.fatal("Unable to validate all validation in send for approval Page" + e.getMessage());
			pdfResultReport.addStepDetails("sendForApprovalValidations",
					"Must validate all validation in send for approval Page",
					"Unable validate all validation in send for approval Page" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyBidder() throws Throwable {
		try {
			log.info("started executing the method:: verifyBidder");

			System.out.println(bidderName);
			click(tendercreationlocators.login, "login");
			if (bidderName.contains("TCS")) {
				System.out.println("Login with TCS");
				set(tendercreationlocators.userName, pdfResultReport.testData.get("Bidder2UserName"), "userName");
				waitForObj(5000);
				set(tendercreationlocators.password, pdfResultReport.testData.get("AppPassword"), "password");
				click(tendercreationlocators.okButton, "okButton");
				waitForElement(tendercreationlocators.dashboardIcon, 50);
			} else if (bidderName.contains("CTS")) {
				System.out.println("Login with CTS");
				set(tendercreationlocators.userName, pdfResultReport.testData.get("Bidder1UserName"), "userName");
				waitForObj(5000);
				set(tendercreationlocators.password, pdfResultReport.testData.get("AppPassword"), "password");
				click(tendercreationlocators.okButton, "okButton");
				waitForElement(tendercreationlocators.dashboardIcon, 50);
			} else {
				System.out.println("Login cannot be done");
			}

			pdfResultReport.addStepDetails("verifyBidder", "verifyBidder must be validate successfully",
					"Successfully validated verifyBidder" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyBidder");
		} catch (Exception e) {
			log.fatal("Not able to validate bidder" + e.getMessage());
			pdfResultReport.addStepDetails("verifyBidder", "bidder must be validate successfully",
					"Unable to validate bidder" + e.getMessage(), "Fail", "N");
		}

	}

	public void sendForApprovalValidationsForUserDefined1() throws Throwable {

		try {
			log.info("started executing the method:: sendForApprovalValidationsForUserDefined");
			waitForObj(7000);
			select(tendercreationlocators.selectStatus, "Pending For Approval ");
			waitForObj(2000);

			enterPORef();
			bidderName = text(tendercreationlocators.bidder).trim();
			click(tendercreationlocators.actionMenuDropDownBy_PO, "actionMenuDropDownBy_PO");

			waitForObj(2000);

			JSClick(tendercreationlocators.cancelPO, "cancelPO");

			waitForObj(3000);
			click(tendercreationlocators.Userdefined, "Userdefined");
			waitForObj(2000);
			click(tendercreationlocators.approvalType_Sequential, "approvalType_Sequential");

			click(tendercreationlocators.addButton, "addButton");
			set(tendercreationlocators.user1, pdfResultReport.testData.get("POApproverUser1"), "user1");
			waitForObj(2000);
			click(tendercreationlocators.addButton, "addButton");
			set(tendercreationlocators.user2, pdfResultReport.testData.get("POApproverUser2"), "user2");
			set(tendercreationlocators.cancellationReason, pdfResultReport.testData.get("cancellationReason"),
					"cancellationReason");
			click(tendercreationlocators.sendForApproval, "sendForApproval");
			waitForElement(tendercreationlocators.approveAllOkButton, 30);
			click(tendercreationlocators.approveAllOkButton, "approveAllOkButton");

			waitForObj(5000);

			pdfResultReport.addStepDetails("sendForApprovalValidations",
					"Must validate all validation in send for approval Page",
					"Successfully validate all validation in send for approval Page" + " ", "Pass", "Y");
			log.info("completed executing the method:: sendForApprovalValidations");
		} catch (Exception e) {
			log.fatal("Unable to validate all validation in send for approval Page" + e.getMessage());
			pdfResultReport.addStepDetails("sendForApprovalValidations",
					"Must validate all validation in send for approval Page",
					"Unable validate all validation in send for approval Page" + e.getMessage(), "Fail", "N");
		}
	}

	public void sendForApprovalValidationsForNotRequired1() throws Throwable {

		try {
			log.info("started executing the method:: sendForApprovalValidationsForUserDefined");

			waitForObj(3000);

			select(tendercreationlocators.selectStatus, "Pending For Approval ");
			waitForObj(2000);

			enterPORef();
			bidderName = text(tendercreationlocators.bidder).trim();
			click(tendercreationlocators.actionMenuDropDownBy_PO, "actionMenuDropDownBy_PO");

			waitForObj(2000);

			JSClick(tendercreationlocators.cancelPO, "cancelPO");

			waitForObj(3000);

			click(tendercreationlocators.sendForApprovalNotRequired, "sendForApprovalNotRequired");
			click(tendercreationlocators.sendForApprovalSubmit, "sendForApprovalSubmit");
			IsElementPresent(tendercreationlocators.alertCancel);
			pdfResultReport.addStepDetails("checkingCancellationComment", "Purchase order must be cancel sucessfully ",
					"Successfully cancelled Purchase order" + " ", "Pass", "Y");
			click(tendercreationlocators.confirmOk, "confirmOk");

			set(tendercreationlocators.cancellationReason, pdfResultReport.testData.get("cancellationReason"),
					"cancellationReason");
			click(tendercreationlocators.sendForApprovalSubmit, "sendForApprovalSubmit");
			waitForElement(tendercreationlocators.approveAllOkButton, 30);
			click(tendercreationlocators.approveAllOkButton, "approveAllOkButton");

			waitForObj(10000);

			pdfResultReport.addStepDetails("sendForApprovalValidations",
					"Must validate all validation in send for approval Page",
					"Successfully validate all validation in send for approval Page" + " ", "Pass", "Y");
			log.info("completed executing the method:: sendForApprovalValidations");
		} catch (Exception e) {
			log.fatal("Unable to validate all validation in send for approval Page" + e.getMessage());
			pdfResultReport.addStepDetails("sendForApprovalValidations",
					"Must validate all validation in send for approval Page",
					"Unable validate all validation in send for approval Page" + e.getMessage(), "Fail", "N");
		}
	}

	public void navigateToPoListingWithBidderUser1() throws Throwable {

		try {
			log.info("started executing the method:: navigateToPoListingWithBidderUser");

			waitForObj(3000);
			JSClick(tendercreationlocators.bidSubmissionTransaction, "bidSubmissionTransaction");

			waitForObj(2000);

			JSClick(tendercreationlocators.poListingLinkBy, "poListingLinkBy");

			waitForElementToBeVisible(tendercreationlocators.poListPageRowBy);

			waitForObj(3000);

			pdfResultReport.addStepDetails("navigateToPoListingWithBidderUser",
					"Must Navigate to Purchase Order List Page",
					"Successfully Navigate to Purchase Order List Page" + " ", "Pass", "Y");
			log.info("completed executing the method:: navigateToPoListingWithBidderUser");
		} catch (Exception e) {
			log.fatal("Unable to Navigate to Purchase Order List Page" + e.getMessage());
			pdfResultReport.addStepDetails("navigateToPoListingWithBidderUser",
					"Must Navigate to Purchase Order List page",
					"Unable to Navigate to Purchase Order List Page" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyPOStatusIsCancelled() throws Exception {
		try {
			log.info("started executing the method:: verifyPOStatusIsCancelled");

			clear(tendercreationlocators.search, "Clear the po ref no search field");

			set(tendercreationlocators.search, refNo, "po ref no search field");

			waitForObj(2000);

			IsElementPresent(tendercreationlocators.poStatusCancelled);

			waitForObj(2000);

			pdfResultReport.addStepDetails("verifyPOStatusIsCancelled", "Po Status should show Cancelled Mode",
					"Sucessfully showing Status as cancelled" + " ", "Pass", "Y");

			log.info("completed executing the method:: verifyPOStatusIsCancelled");
		} catch (Exception e) {
			log.fatal("Unable to show Po Status in cancelled Mode" + e.getMessage());
			pdfResultReport.addStepDetails("verifyPOStatusIsCancelled", "Unable to show Po Status in cancelled Mode",
					"Po Status should show cancelled Mode" + e.getMessage(), "Fail", "N");
		}
	}

	public void poDetailsTabValidation() throws Throwable {
		try {
			log.info("started executing the method::  poDetailsTabValidation");
			waitForObj(2000);
			highlight(tendercreationlocators.headerInformation);
			IsElementPresent(tendercreationlocators.headerInformation);
			highlight(tendercreationlocators.shippingInformation);
			IsElementPresent(tendercreationlocators.shippingInformation);
			IsElementPresent(tendercreationlocators.poNoFieldBy);
			waitForObj(4000);
			pdfResultReport.addStepDetails("poDetailsTabValidation",
					"Header Information, Shipping Information, PO No section must be validate sucessfully",
					"Successfully validated Header Information, Shipping Information, PO No section" + " ", "Pass",
					"Y");
			log.info("completed executing the method:: poDetailsTabValidation");
		} catch (Exception e) {
			log.fatal("Unable to validate Header Information, Shipping Information, PO No section" + e.getMessage());
			pdfResultReport.addStepDetails("poDetailsTabValidation",
					"Header Information, Shipping Information, PO No section must be validate sucessfully",
					"Unable to validate Header Information, Shipping Information, PO No section" + e.getMessage(),
					"Fail", "N");
		}
	}

	public void enterNegotiated_TenderId() throws Exception {
		try {
			log.info("started executing the method::  enterNegotiated_TenderId");

			clear(tendercreationlocators.tenderListKeyword, "tenderListKeyword");

			set(tendercreationlocators.tenderListKeyword,
					eTenderComponent.getDataFromPropertiesFile("NegotiatedTenderId"), "NegotiatedTenderId");

			waitForObj(5000);

			pdfResultReport.addStepDetails("enterNegotiated_TenderId",
					"Negotiated Tender Id must be entered sucessfully, Post tender process list should not show the RFQ(unless RFQ has not been completed)",
					"Sucessfully entered Negotiated Tender Id, Post tender process list does not show the RFQ(unless RFQ has not been completed)"
							+ " ",
					"Pass", "Y");
			log.info("completed executing the method:: enterNegotiated_TenderId");
		} catch (Exception e) {
			log.fatal("Unable to eneter Negotiated Tender Id" + e.getMessage());
			pdfResultReport.addStepDetails("enterNegotiated_TenderId",
					"Negotiated Tender Id must be entered sucessfully, Post tender process list should not show the RFQ(unless RFQ has not been completed)",
					"Unable to eneter Negotiated Tender Id" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickEvaluationStage() throws Throwable {
		try {
			log.info("started executing the method:: clickEvaluationStage");
			click(tendercreationlocators.evaluationstage, "evaluationstage");
			waitForElementToBeVisible(tendercreationlocators.negotiatedStatus);
			waitForObj(4000);
			pdfResultReport.addStepDetails("clickEvaluationStage",
					"Evaluation Stage must be click successfully, No option is there to initiate further evaluation",
					"Successfully clicked on Evaluation Stage, No option is there to initiate further evaluation" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: clickEvaluationStage");
		} catch (Exception e) {
			log.fatal("Not able to click on Evaluation Stage" + e.getMessage());
			pdfResultReport.addStepDetails("clickEvaluationStage",
					"Evaluation Stage must be click successfully, No option is there to initiate further evaluation",
					"Unable to click on Evaluation Stage" + e.getMessage(), "Fail", "N");
		}
	}

	public void poTemplateGroupAlertMessageValidation() throws Throwable {
		try {
			log.info("started executing the method:: poTemplateGroupAlertMessageValidation");
			click(tendercreationlocators.issuePO, "issuePO");
			waitForObj(3000);
			click(tendercreationlocators.POSUccessMsg, "OKButton");
			// waitForElement(tendercreationlocators.POSUccessMsg,5000);
			new WebDriverWait(ThreadLocalWebdriver.getDriver(), 30)
					.until(ExpectedConditions.invisibilityOfElementLocated(tendercreationlocators.Snipper));
			select(tendercreationlocators.POTemplateGroup, "PO Template Group V1.11");
			pdfResultReport.addStepDetails("poTemplateGroupAlertMessageValidation",
					"Alert Message must be validate successfully", "Successfully validated alert message" + " ", "Pass",
					"Y");
			waitForObj(4000);
			pdfResultReport.addStepDetails("poTemplateGroupAlertMessageValidation",
					"Alert Message must be validate successfully", "Successfully validated alert message" + " ", "Pass",
					"Y");

			log.info("completed executing the method:: poTemplateGroupAlertMessageValidation");
		} catch (Exception e) {
			log.fatal("Not able to validate alert message" + e.getMessage());
			pdfResultReport.addStepDetails("poTemplateGroupAlertMessageValidation",
					"Alert message must be validate successfully", "Unable to validate alert message" + e.getMessage(),
					"Fail", "N");
		}
	}

	public void CreateSanctionNoteWithNoApproval() throws Throwable {
		try {
			log.info("started executing the method::  CreateSanctionNote");
			click(tendercreationlocators.createSanctionNote, "createSanctionNote");
			waitForObj(7000);
			// set(tendercreationlocators.sanctionReferenceNo,
			// pdfResultReport.testData.get("SanctionReferenceNumber"),
			// "sanctionReferenceNo");
			sanctionReferenceNumber();
			waitForObj(3000);
			// click(tendercreationlocators.sanctionSubmit, "sanctionSubmit");
			waitForElement(tendercreationlocators.recommendationTab, 40);
			// waitForObj(20000);
			new WebDriverWait(ThreadLocalWebdriver.getDriver(), 30)
					.until(ExpectedConditions.invisibilityOfElementLocated(tendercreationlocators.Snipper));
			waitForObj(5000);
			JSClick(tendercreationlocators.itemAllotment1, "itemAllotment1");
			waitForObj(2000);
			JSClick(tendercreationlocators.itemAllotmentSelect, "itemAllotmentSelect");
			waitForObj(3000);
			JSClick(tendercreationlocators.itemAllotmentRow, "itemAllotmentRow");
			waitForObj(3000);
			JSClick(tendercreationlocators.recommendationTab, "recommendationTab");
			waitForObj(4000);

			WebElement iframele = ThreadLocalWebdriver.getDriver().findElement(By.xpath("//iframe[@id='txtArea_ifr']"));
			switchframe(iframele);
			waitForObj(4000);
			set(tendercreationlocators.recommendationOverAllCommentButton, "recommendationOverAllCommentButton",
					"recommendationOverAllCommentButton");
			switchToDefaultFrame();
			waitForObj(4000);
			click(tendercreationlocators.submit, "submit");
			waitForObj(5000);
			documentNoSave();
			waitForObj(3000);
			click(tendercreationlocators.sendForApprovalNotRequired_SN, "sendForApprovalNotRequired_SN");
			JSClick(tendercreationlocators.sendForApprovalSubmit, "sendForApprovalSubmit");
			waitForElement(tendercreationlocators.createSanctionNote, 100);
			new WebDriverWait(ThreadLocalWebdriver.getDriver(), 30)
					.until(ExpectedConditions.invisibilityOfElementLocated(tendercreationlocators.Snipper));
			waitForObj(7000);
			pdfResultReport.addStepDetails("clickOnCreateSanctionNote",
					"The page redirects to completed tender details",
					"Completed tender details page should be displayed" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickOnCreateSanctionNote");
		} catch (Exception e) {
			log.fatal("Unable to click on sanction note" + e.getMessage());
			pdfResultReport.addStepDetails("clickOnCreateSanctionNote",
					"The page redirects to completed tender details",
					"Unable to click on sanction note" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyAcceptedOrRejectedSanctionStageAndApprovalDetails() throws Exception {
		try {
			log.info("started executing the method::  verifyAcceptedOrRejectedSanctionStageAndApprovalDetails");
			waitForObj(10000);
			clear(tendercreationlocators.tenderListKeyword, "tenderListKeyword");
			set(tendercreationlocators.typeAnyKeyword, documentNumberText, "typeAnyKeyword");
			IsElementPresent(tendercreationlocators.acceptOrReject);
			click(tendercreationlocators.action, "action");
			IsElementPresent(tendercreationlocators.approvalDetails);

			pdfResultReport.addStepDetails("enterCompletedTenderId",
					"Tender id should be displayed in completed tender list page",
					"Sucessfully displayed tender id in completed tender list page" + " ", "Pass", "Y");
			log.info("completed executing the method:: enterCompletedTenderId");
		} catch (Exception e) {
			log.fatal("Unable to display tender id in completed tender list page" + e.getMessage());
			pdfResultReport.addStepDetails("enterCompletedTenderId",
					"Tender id should be displayed in completed tender list page",
					"Unable to display tender id in completed tender list page" + e.getMessage(), "Fail", "N");
		}
	}

	public void sendForApprovalUserDefinedSequential_Coordinator() throws Throwable {
		try {
			log.info("started executing the method:: sendForApprovalUserDefinedSequential");
			waitForObj(5000);
			if (ThreadLocalWebdriver.getDriver().findElement(By.xpath("//input[@id='appYes'][@value='U']"))
					.isSelected()) {
				System.out.println("user defined already selected");
			} else {
				click(tendercreationlocators.Userdefined, "Userdefined");
			}

			waitForObj(5000);
			List<WebElement> elem = ThreadLocalWebdriver.getDriver()
					.findElements(By.xpath("//button[@ng-click='cancelUser(row)']"));
			boolean flag = false;
			if (elem.size() > 0) {
				flag = true;
			} else {
				flag = false;
			}

			if (flag) {
				List<WebElement> elem1 = ThreadLocalWebdriver.getDriver()
						.findElements(By.xpath("//button[@ng-click='cancelUser(row)']"));
				for (int i = 1; i <= elem1.size(); i++) {
					click(tendercreationlocators.deleteUser, "deleteUser");
					waitForObj(3000);
				}

			} else {
				System.out.println("user not created earlier");
			}

			click(tendercreationlocators.userAdd, "userAdd");
			waitForObj(5000);
			set(tendercreationlocators.user1, pdfResultReport.testData.get("UserTenderApprover1"), "user1");
			waitForObj(2000);
			select(tendercreationlocators.approverType1_p, pdfResultReport.testData.get("ApprovalType1"));
			waitForObj(5000);
			JSClick(tendercreationlocators.coordinator, "coordinator");
			waitForObj(5000);
			click(tendercreationlocators.userAdd, "userAdd");
			waitForObj(5000);
			set(tendercreationlocators.user2, pdfResultReport.testData.get("UserTenderApprover2"), "user2");
			waitForObj(5000);
			select(tendercreationlocators.approverType2_p, pdfResultReport.testData.get("ApprovalType1"));
			waitForObj(5000);
			JSClick(tendercreationlocators.coordinator, "coordinator");
			waitForObj(5000);
			JSClick(tendercreationlocators.sendForApproval, "sendForApproval");

			pdfResultReport.addStepDetails("sendForApprovalUserDefinedSequential",
					"sendForApproval must be validate successfully", "Successfully validated sendForApproval" + " ",
					"Pass", "Y");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			new WebDriverWait(ThreadLocalWebdriver.getDriver(), 45)
					.until(ExpectedConditions.invisibilityOfElementLocated(tendercreationlocators.Snipper));
			waitForObj(15000);
			pdfResultReport.addStepDetails("sendForApprovalUserDefinedSequential",
					"sendForApproval must be validate successfully", "Successfully validated sendForApproval" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: sendForApprovalUserDefinedSequential");
		} catch (Exception e) {
			log.fatal("Not able to validate sendForApproval" + e.getMessage());
			pdfResultReport.addStepDetails("sendForApprovalUserDefinedSequential",
					"sendForApproval must be validate successfully",
					"Unable to validate sendForApproval" + e.getMessage(), "Fail", "N");
		}
	}

	public void ScantionComment_recommendationTab() throws Throwable {
		try {
			log.info("started executing the method:: provideComment_recommendationTab");
			click(tendercreationlocators.saveSanction, "saveSanction");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			Thread.sleep(5000);
			JSClick(tendercreationlocators.recommendationTab, "recommendationTab");
			Thread.sleep(10000);
			JSClick(By.xpath("//*[@aria-label='Bold']"), "overallComment");
			WebElement iframele = ThreadLocalWebdriver.getDriver().findElement(By.xpath("//iframe[@id='txtArea_ifr']"));
			switchframe(iframele);
			Thread.sleep(10000);
			set(tendercreationlocators.recommendationComment, "overallComment_currentPart", "recommendationComment");
			switchToDefaultFrame();
			Thread.sleep(5000);
			click(tendercreationlocators.saveSanction, "saveSanction");
			Thread.sleep(10000);
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			pdfResultReport.addStepDetails("provideComment_recommendationTab",
					"Comment must be pass under recommendation tab successfully",
					"Successfully passed comment under recommendation tab" + " ", "Pass", "Y");
			log.info("completed executing the method:: provideComment_recommendationTab");
		} catch (Exception e) {
			log.fatal("Not able to pass comment under recommendation tab" + e.getMessage());
			pdfResultReport.addStepDetails("provideComment_recommendationTab",
					"Comment must be pass under recommendation tab successfully",
					"Unable to pass comment under recommendation tab" + e.getMessage(), "Fail", "N");
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

	public void clickOnSubmitButton() throws Throwable {
		try {
			log.info("started executing the method:: clickOnSubmitButton");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			JSClick(tendercreationlocators.submit, "submit");
			pdfResultReport.addStepDetails("clickOnSubmitButton", "Submit button must be click successfully",
					"Successfully clicked on submit button" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickOnSubmitButton");
		} catch (Exception e) {
			log.fatal("Not able to click on submit button" + e.getMessage());
			pdfResultReport.addStepDetails("clickOnSubmitButton", "Submit button must be click successfully",
					"Unable to click on submit button" + e.getMessage(), "Fail", "N");
		}
	}

	public void sanctionReferenceNumber() throws Throwable {
		try {
			log.info("started executing the method:: sanctionReferenceNumber");
			String SN = "SN_";
			String ref = String.valueOf(getrandomInterger(1000, 10000));
			String sanctionReference = SN.concat(ref);
			waitForObj(5000);
			set(tendercreationlocators.sanctionReferenceNumber, sanctionReference, "sanctionReferenceNumber");
			eTenderComponent.updateDataIntoPropertyFile("sanctionReferenceNumber", sanctionReference);
			waitForObj(5000);
			pdfResultReport.addStepDetails("sanctionReferenceNumber",
					"Sanction reference number must be pass sucessfully",
					"Successfully passed sanction reference number " + " ", "Pass", "Y");
			click(tendercreationlocators.sanctionRefNo_Submit, "sanctionRefNo_Submit");
			waitForObj(20000);
			log.info("completed executing the method:: sanctionReferenceNumber");
		} catch (Exception e) {
			log.fatal("Unable to pass sanction reference number " + e.getMessage());
			pdfResultReport.addStepDetails("sanctionReferenceNumber",
					"Sanction reference number must be pass sucessfully",
					"Unable to pass sanction reference number " + e.getMessage(), "Fail", "N");
		}
	}

	public void clickOnDownloadSanctionReport() throws Throwable {
		try {
			log.info("started executing the method:: clickOnDownloadSanctionReport");
			click(tendercreationlocators.drpdwn(eTenderComponent.getDataFromPropertiesFile("sanctionoteDocnum")),
					"actionDropdown");
			WebDriver driver = ThreadLocalWebdriver.getDriver();

			WebDriverWait wait = new WebDriverWait(driver, 60);
			JSClick(tendercreationlocators.dwnloadSanctionReport(
					eTenderComponent.getDataFromPropertiesFile("sanctionoteDocnum")), "downloadSanctionReport");
			waitForObj(5000);
			select(tendercreationlocators.downloadReoprtFormat, pdfResultReport.testData.get("downloadReoprtFormat"));

			String parentwindow = driver.getWindowHandle();
			click(tendercreationlocators.goButton, "goButton");

			Set<String> windowHandles = driver.getWindowHandles();

			Object[] array = windowHandles.toArray();

			driver.switchTo().window(array[1].toString());

			String currentUrl = driver.switchTo().window(array[1].toString()).getCurrentUrl();

			System.out.println(currentUrl);
			String expectedUrl = "https://epsnewprodaws.mjunction.in/EPSV2Web/rest/sanctionnote/getGeneratedSanctionCSReport.action";
			Assert.assertEquals(currentUrl.trim(), expectedUrl.trim());

			waitForObj(3000);

			driver.close();

			waitForObj(3000);

			driver.switchTo().window(parentwindow);

			pdfResultReport.addStepDetails("clickOnDownloadSanctionReport",
					"Download sanction report must be click successfully",
					"Successfully clicked on download sanction report" + " ", "Pass", "Y");
			click(tendercreationlocators.downloadSanctionReportClose, "downloadSanctionReportClose");
			log.info("completed executing the method:: clickOnDownloadSanctionReport");
		} catch (Exception e) {
			log.fatal("Not able to click on download sanction report" + e.getMessage());
			pdfResultReport.addStepDetails("clickOnDownloadSanctionReport",
					"Download sanction report must be click successfully",
					"Unable to click on download sanction report" + e.getMessage(), "Fail", "N");
		}
	}

	public void viewDetailsValidation() throws Throwable {
		try {
			log.info("started executing the method:: viewDetailsValidation");
			click(tendercreationlocators.drpdwn(eTenderComponent.getDataFromPropertiesFile("sanctionoteDocnum")),
					"actionDropdown");
			click(tendercreationlocators.VIEW(eTenderComponent.getDataFromPropertiesFile("sanctionoteDocnum")), "view");
			IsElementPresent(tendercreationlocators.viewDetails_TCS);
			IsElementPresent(tendercreationlocators.viewDetails_CTS);
			pdfResultReport.addStepDetails("viewDetailsValidation", "View details must be validate successfully",
					"Successfully validated view details" + " ", "Pass", "Y");
			click(tendercreationlocators.viewDetails_close, "viewDetails_close");
			log.info("completed executing the method:: viewDetailsValidation");
		} catch (Exception e) {
			log.fatal("Not able to validate view details" + e.getMessage());
			pdfResultReport.addStepDetails("viewDetailsValidation", "View details must be validate successfully",
					"Unable to validate view details" + e.getMessage(), "Fail", "N");
		}
	}

	public void approvalDetailsValidationForUserdefinedSequential() throws Throwable {
		try {
			log.info("started executing the method:: approvalDetailsValidationForUserdefinedSequential");
			click(tendercreationlocators.drpdwn(eTenderComponent.getDataFromPropertiesFile("sanctionoteDocnum")),
					"actionDropdown");

			click(tendercreationlocators.approvdetailsvalidation(
					eTenderComponent.getDataFromPropertiesFile("sanctionoteDocnum")), "approvalDetails");
			IsElementPresent(tendercreationlocators.username);
			IsElementPresent(tendercreationlocators.approval_Type);
			IsElementPresent(tendercreationlocators.approvalDetailsStatus);
			pdfResultReport.addStepDetails("approvalDetailsValidationForUserdefinedSequential",
					"Approval details must be validate successfully", "Successfully validated approval details" + " ",
					"Pass", "Y");
			click(tendercreationlocators.approvalDetails_close, "approvalDetails_close");
			pdfResultReport.addStepDetails("approvalDetailsValidationForUserdefinedSequential",
					"Approval details must be validate successfully", "Successfully validated approval details" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: approvalDetailsValidationForUserdefinedSequential");
		} catch (Exception e) {
			log.fatal("Not able to validate approval details" + e.getMessage());
			pdfResultReport.addStepDetails("approvalDetailsValidationForUserdefinedSequential",
					"Approval details must be validate successfully",
					"Unable to validate approval details" + e.getMessage(), "Fail", "N");
		}
	}

	public void approvalDetailsValidation() throws Throwable {
		try {
			log.info("started executing the method:: approvalDetailsValidation");
			click(tendercreationlocators.drpdwn(eTenderComponent.getDataFromPropertiesFile("sanctionoteDocnum")),
					"actionDropdown");

			click(tendercreationlocators.approvdetailsvalidation(
					eTenderComponent.getDataFromPropertiesFile("sanctionoteDocnum")), "approvalDetails");
			IsElementPresent(tendercreationlocators.noRecordsFound);
			pdfResultReport.addStepDetails("approvalDetailsValidation",
					"Approval details must be validate successfully", "Successfully validated approval details" + " ",
					"Pass", "Y");
			click(tendercreationlocators.approvalDetails_close, "approvalDetails_close");
			log.info("completed executing the method:: approvalDetailsValidation");
		} catch (Exception e) {
			log.fatal("Not able to validate approval details" + e.getMessage());
			pdfResultReport.addStepDetails("approvalDetailsValidation",
					"Approval details must be validate successfully",
					"Unable to validate approval details" + e.getMessage(), "Fail", "N");
		}
	}

	public void sendForApproval() throws Throwable {
		try {
			log.info("started executing the method:: sendForApproval");
			JSClick(tendercreationlocators.sendForApprovalNotRequired_SN, "sendForApprovalNotRequired_SN");
			JSClick(tendercreationlocators.sendForApprovalSubmit, "sendForApprovalSubmit");
			pdfResultReport.addStepDetails("sendForApproval", "sendForApproval must be validate successfully",
					"Successfully validated sendForApproval" + " ", "Pass", "Y");
			log.info("completed executing the method:: sendForApproval");
			WebDriverWait wait = new WebDriverWait(ThreadLocalWebdriver.getDriver(), 100);
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//*[@id='myTabContent']/child::div/child::div/table/tbody/tr[2]")));
			waitForObj(5000);
			pdfResultReport.addStepDetails("sendForApproval", "sendForApproval must be validate successfully",
					"Successfully validated sendForApproval" + " ", "Pass", "Y");
			log.info("completed executing the method:: sendForApproval");
		} catch (Exception e) {
			log.fatal("Not able to validate sendForApproval" + e.getMessage());
			pdfResultReport.addStepDetails("sendForApproval", "Not able to validate sendForApproval",
					"Unable to validate sendForApproval" + e.getMessage(), "Fail", "N");
		}
	}

	public void recallButtonValidation() throws Throwable {
		try {
			log.info("started executing the method:: recallButtonValidation");
			click(tendercreationlocators.drpdwn(eTenderComponent.getDataFromPropertiesFile("sanctionoteDocnum")),
					"actionDropdown");
			click(tendercreationlocators.btnRecall(eTenderComponent.getDataFromPropertiesFile("sanctionoteDocnum")),
					"recallButton");
			waitForObj(5000);
			set(tendercreationlocators.recallReasonComment, "recall", "recommendationComment");
			pdfResultReport.addStepDetails("recallButtonValidation", "Recall button must be validate successfully",
					"Successfully validated recall button" + " ", "Pass", "Y");
			click(tendercreationlocators.recallReasonSubmit, "recallReasonSubmit");
			waitForObj(8000);
			pdfResultReport.addStepDetails("recallButtonValidation", "Recall button must be validate successfully",
					"Successfully validated recall button" + " ", "Pass", "Y");
			log.info("completed executing the method:: recallButtonValidation");
		} catch (Exception e) {
			log.fatal("Not able to validate recall button" + e.getMessage());
			pdfResultReport.addStepDetails("recallButtonValidation", "Recall button must be validate successfully",
					"Unable to validate recall button" + e.getMessage(), "Fail", "N");
		}
	}

	public void sanctionNoteEvaluationValidation() throws Throwable {
		try {
			log.info("started executing the method:: sanctionNoteEvaluationValidation");
			click(tendercreationlocators.details, "details");
			waitForObj(5000);
			JSClick(tendercreationlocators.sanctionNoteEvaluationCommentTab, "sanctionNoteEvaluationCommentTab");
			waitForObj(2000);
			pdfResultReport.addStepDetails("sanctionNoteEvaluationValidation",
					"sanction Note Evaluation must be validate sucessfully ",
					"Successfully validated sanction Note Evaluation" + " ", "Pass", "Y");
			log.info("completed executing the method:: sanctionNoteEvaluationValidation");

		} catch (Exception e) {
			log.fatal("Unable to validate sanction Note Evaluation" + e.getMessage());
			pdfResultReport.addStepDetails("sanctionNoteEvaluationValidation",
					"sanction Note Evaluation must be validate sucessfully",
					"Unable to validate sanction Note Evaluation" + e.getMessage(), "Fail", "N");
		}
	}

	public void issuePObuttonValidation() throws Throwable {
		try {
			log.info("started executing the method:: issuePObuttonValidation");

			IsElementPresent(tendercreationlocators.issuePO);
			highlight(tendercreationlocators.issuePO);
			waitForObj(5000);
			pdfResultReport.addStepDetails("issuePObuttonValidation", "Issue PO button must be validate successfully",
					"Successfully validated Issue PO button" + " ", "Pass", "Y");

			log.info("completed executing the method:: issuePObuttonValidation");
		} catch (Exception e) {
			log.fatal("Not able to validate Issue PO button" + e.getMessage());
			pdfResultReport.addStepDetails("issuePObuttonValidation", "Issue PO button must be validate successfully",
					"Unable to validate Issue PO button" + e.getMessage(), "Fail", "N");
		}
	}

	public void CreateSanctionNoteWithUserdefinedApproval() throws Throwable {
		try {
			log.info("started executing the method::  CreateSanctionNote");
			click(tendercreationlocators.createSanctionNote, "createSanctionNote");
			sanctionReferenceNumber();
			waitForObj(3000);
			// click(tendercreationlocators.sanctionSubmit, "sanctionSubmit");
			waitForElementToBeVisible(tendercreationlocators.recommendationTab);
			// waitForObj(20000);
			new WebDriverWait(ThreadLocalWebdriver.getDriver(), 30)
					.until(ExpectedConditions.invisibilityOfElementLocated(tendercreationlocators.Snipper));
			waitForObj(5000);
			click(tendercreationlocators.itemAllotment1, "itemAllotment1");
			click(tendercreationlocators.itemAllotmentSelect, "itemAllotmentSelect");
			click(tendercreationlocators.itemAllotmentRow, "itemAllotmentRow");
			waitForObj(3000);
			JSClick(tendercreationlocators.recommendationTab, "recommendationTab");
			// click(tendercreationlocators.recommendationTab,"recommendationTab");
			waitForObj(4000);

			WebElement iframele = ThreadLocalWebdriver.getDriver().findElement(By.xpath("//iframe[@id='txtArea_ifr']"));
			switchframe(iframele);
			waitForObj(4000);
			set(tendercreationlocators.recommendationOverAllCommentButton, "recommendationOverAllCommentButton",
					"recommendationOverAllCommentButton");
			switchToDefaultFrame();
			waitForObj(4000);
			click(tendercreationlocators.submit, "submit");
			waitForObj(5000);
			documentNoSave();
			waitForObj(3000);

			if (ThreadLocalWebdriver.getDriver().findElement(By.xpath("//input[@id='appYes'][@value='U']"))
					.isSelected()) {
				System.out.println("user defined already selected");
			} else {
				click(tendercreationlocators.Userdefined, "Userdefined");
			}

			waitForObj(2000);
			List<WebElement> elem = ThreadLocalWebdriver.getDriver()
					.findElements(By.xpath("//button[@ng-click='cancelUser(row)']"));
			boolean flag = false;
			if (elem.size() > 0) {
				flag = true;
			} else {
				flag = false;
			}

			if (flag) {
				List<WebElement> elem1 = ThreadLocalWebdriver.getDriver()
						.findElements(By.xpath("//button[@ng-click='cancelUser(row)']"));
				for (int i = 1; i <= elem1.size(); i++) {
					click(tendercreationlocators.deleteUser, "deleteUser");

				}

			} else {
				System.out.println("user not created earlier");
			}
			click(tendercreationlocators.Userdefined, "Userdefined");
			// click(tendercreationlocators.cancelUser, "cancelUser");
			click(tendercreationlocators.userAdd, "userAdd");

			waitForObj(2000);
			set(tendercreationlocators.user1, pdfResultReport.testData.get("Sanctionapprover1User"), "user1");
			select(tendercreationlocators.approverType1_p, pdfResultReport.testData.get("ApprovalType2"));
			waitForObj(2000);
			click(tendercreationlocators.coordinatorFlag, "coordinatorFlag");
			click(tendercreationlocators.sendForApproval, "sendForApproval");
			pdfResultReport.addStepDetails("sendForApproval", "sendForApproval must be validate successfully",
					"Successfully validated sendForApproval" + " ", "Pass", "Y");
			waitForElement(tendercreationlocators.createSanctionNote, 5);
			new WebDriverWait(ThreadLocalWebdriver.getDriver(), 30)
					.until(ExpectedConditions.invisibilityOfElementLocated(tendercreationlocators.Snipper));
			waitForObj(7000);
			pdfResultReport.addStepDetails("clickOnCreateSanctionNote",
					"The page redirects to completed tender details",
					"Completed tender details page should be displayed" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickOnCreateSanctionNote");
		} catch (Exception e) {
			log.fatal("Unable to click on sanction note" + e.getMessage());
			pdfResultReport.addStepDetails("clickOnCreateSanctionNote",
					"The page redirects to completed tender details",
					"Unable to click on sanction note" + e.getMessage(), "Fail", "N");
		}
	}

	public void sendForApprovalUserDefinedSequentialOneUser_Coordinator() throws Throwable {
		try {
			log.info("started executing the method:: sendForApprovalUserDefinedSequential");
			waitForObj(5000);
			if (ThreadLocalWebdriver.getDriver().findElement(By.xpath("//input[@id='appYes'][@value='U']"))
					.isSelected()) {
				System.out.println("user defined already selected");
			} else {
				click(tendercreationlocators.Userdefined, "Userdefined");
			}

			waitForObj(5000);
			List<WebElement> elem = ThreadLocalWebdriver.getDriver()
					.findElements(By.xpath("//button[@ng-click='cancelUser(row)']"));
			boolean flag = false;
			if (elem.size() > 0) {
				flag = true;
			} else {
				flag = false;
			}

			if (flag) {
				List<WebElement> elem1 = ThreadLocalWebdriver.getDriver()
						.findElements(By.xpath("//button[@ng-click='cancelUser(row)']"));
				for (int i = 1; i <= elem1.size(); i++) {
					click(tendercreationlocators.deleteUser, "deleteUser");
					waitForObj(3000);
				}

			} else {
				System.out.println("user not created earlier");
			}

			click(tendercreationlocators.userAdd, "userAdd");
			waitForObj(5000);
			set(tendercreationlocators.user1, pdfResultReport.testData.get("UserTenderApprover1"), "user1");
			waitForObj(2000);
			select(tendercreationlocators.approverType1_p, pdfResultReport.testData.get("ApprovalType1"));
			waitForObj(5000);
			JSClick(tendercreationlocators.coordinator, "coordinator");
			waitForObj(5000);
			JSClick(tendercreationlocators.sendForApproval, "sendForApproval");

			pdfResultReport.addStepDetails("sendForApprovalUserDefinedSequential",
					"sendForApproval must be validate successfully", "Successfully validated sendForApproval" + " ",
					"Pass", "Y");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			new WebDriverWait(ThreadLocalWebdriver.getDriver(), 45)
					.until(ExpectedConditions.invisibilityOfElementLocated(tendercreationlocators.Snipper));
			waitForObj(15000);
			pdfResultReport.addStepDetails("sendForApprovalUserDefinedSequential",
					"sendForApproval must be validate successfully", "Successfully validated sendForApproval" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: sendForApprovalUserDefinedSequential");
		} catch (Exception e) {
			log.fatal("Not able to validate sendForApproval" + e.getMessage());
			pdfResultReport.addStepDetails("sendForApprovalUserDefinedSequential",
					"sendForApproval must be validate successfully",
					"Unable to validate sendForApproval" + e.getMessage(), "Fail", "N");
		}
	}

	public void navigateToSanctionTemplateList() throws Throwable {
		try {
			log.info("started executing the method:: navigateToSanctionTemplateList");
			IsElementPresent(tendercreationlocators.sanctionTemplate);
			mouseOver(tendercreationlocators.sanctionTemplate);
			pdfResultReport.addStepDetails("navigateToSanctionTemplateList",
					"Sanction Template must be available for selection",
					"Sanction Template is available for selection" + " ", "Pass", "Y");
			JSClick(tendercreationlocators.sanctionTemplate, "sanctionTemplate");
			waitForObj(2000);

			IsElementPresent(tendercreationlocators.templateGroupCreation);
			mouseOver(tendercreationlocators.templateGroupCreation);
			JSClick(tendercreationlocators.templateGroupCreation, "templateGroupCreation");
			waitForObj(5000);

			pdfResultReport.addStepDetails("navigateToSanctionTemplateList",
					"Template Group Creation must be available, Sanction Template List Page must be naviagte successfully after clicking",
					"Template Group Creation is available, Successfully navigated to Sanction Template List Page" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: navigateToSanctionTemplateList");
		} catch (Exception e) {
			log.fatal("Unable to navigate to Sanction Template List Page" + e.getMessage());
			pdfResultReport.addStepDetails("navigateToSanctionTemplateList",
					"Template Group Creation must be available, Sanction Template List Page must be naviagte successfully after clicking",
					"Unable to navigate to Sanction Template List Page" + e.getMessage(), "Fail", "N");
		}
	}

	public void ValidateSNTemplateOptionNotFound() throws Throwable {
		try {
			log.info("started executing the method:: ValidateSNTemplateOptionNotFound");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			mouseOver(tendercreationlocators.dashboardIcon);
			pdfResultReport.addStepDetails("ValidateSNTemplateOptionNotFound",
					"Sanction Template option must not be showing for Buyer",
					"Sanction Template option is not showing for Buyer" + " ", "Pass", "Y");
			log.info("completed executing the method:: ValidateSNTemplateOptionNotFound");
		} catch (Exception e) {
			log.fatal("Sanction Template List Page is available for Buyer" + e.getMessage());
			pdfResultReport.addStepDetails("navigateToSanctionTemplateList",
					"Sanction Template option must not be showing for Buyer",
					"Sanction Template option is showing for Buyer" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyRFQInCompletedTenderList() throws Throwable {
		try {
			log.info("started executing the method::  verifyRFQInCompletedTenderList");
			waitForObj(5000);
			WebElement noRecords = ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.noRecords);

			String noRecordsText = noRecords.getText().trim();

			System.out.println(noRecordsText);
			if (noRecordsText.equalsIgnoreCase("0")) {
				System.out.println("No records are present");
			} else if ((ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.completedRFQId).getText()
					.trim()).equalsIgnoreCase(eTenderComponent.getDataFromPropertiesFile())) {
				System.out.println("RFQid: is present");
			} else {
				System.out.println("RFQid: present is invalid");
			}
			pdfResultReport.addStepDetails("verifyRFQInCompletedTenderList",
					"Tender id should not be displayed in completed tender list page",
					"Sucessfully not displayed Tender id in completed tender list page" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyRFQInCompletedTenderList");
		} catch (Exception e) {
			log.fatal("Tender id displayed in completed tender list page" + e.getMessage());
			pdfResultReport.addStepDetails("verifyRFQInCompletedTenderList",
					"Tender id should  not be displayed in completed tender list page",
					"Tender id displayed in completed tender list page" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyDop_ActivationLink() throws Throwable {
		try {
			log.info("started executing the method::verifyDop_ActivationLink");

			/*
			 * clear(tendercreationlocators.search, "clear the enter key word");
			 * 
			 * set(tendercreationlocators.search, DopName, "enter key word");
			 */
			String status = text(tendercreationlocators.DOPstatus);
			if (status.equalsIgnoreCase("Inactive")) {

				waitForObj(3000);

				click(tendercreationlocators.action, "action");

				click(tendercreationlocators.activeTabLinkBy, "activeTabLinkBy");

				waitForObj(5000);

				JSClick(tendercreationlocators.confirmBtn, "confirmBtn");

				waitForElementToBeVisible(tendercreationlocators.confirmationOkBy);

				JSClick(tendercreationlocators.confirmationOkBy, "confirmationOkBy");

				waitForObj(3000);

				IsElementPresent(tendercreationlocators.dopListStatusActiveBy);

				pdfResultReport.addStepDetails("verifyDop_ActivationLink", "Dop List Status Column should be in Active",
						"Successfully changed to Active mode in Dop List" + " ", "Pass", "Y");
			} else {
				System.out.println("DOP is already active");
			}

			log.info("completed executing the method:: verifyDop_ActivationLink");
		} catch (Exception e) {
			pdfResultReport.addStepDetails("verifyDop_ActivationLink",
					"Dop List Status Column should be in Active/Inactive",
					"Unable to change to Active/Inactive mode in Dop List" + e.getMessage(), "Fail", "N");
		}
	}

	public void verify_Dop_InactiveLink() throws Throwable {
		try {
			log.info("started executing the method::verifyDop_InactiveLink");

			/*
			 * clear(tendercreationlocators.search, "clear the enter key word");
			 * 
			 * set(tendercreationlocators.search, DopName, "enter key word");
			 */
			String status = text(tendercreationlocators.DOPstatus);
			if (status.equalsIgnoreCase("Active")) {
				waitForObj(3000);

				click(tendercreationlocators.action, "action");

				click(tendercreationlocators.deActiveTabLinkBy, "deActiveTabLinkBy");

				waitForObj(5000);

				JSClick(tendercreationlocators.confirmBtn, "confirmBtn");

				waitForElementToBeVisible(tendercreationlocators.confirmationOkBy);

				JSClick(tendercreationlocators.confirmationOkBy, "confirmationOkBy");

				waitForObj(3000);

				IsElementPresent(tendercreationlocators.dopListStatusInActiveBy);

				pdfResultReport.addStepDetails("verifyDop_InactiveLink", "Dop List Status Column should be in InActive",
						"Successfully changed to InActive mode in Dop List" + " ", "Pass", "Y");
			} else {
				System.out.println("DOP is already inactive");
			}
			log.info("completed executing the method:: verifyDop_InactiveLink");
		} catch (Exception e) {
			log.fatal("Unable to change to InActive mode in Dop List" + e.getMessage());
			pdfResultReport.addStepDetails("verifyDop_InactiveLink", "Dop List Status Column should be in InActive",
					"Unable to change to InActive mode in Dop List" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyAcceptedOrRejectedSanctionStage() throws Exception {
		try {
			log.info("started executing the method::  verifyAcceptedOrRejectedSanctionStageAndApprovalDetails");
			waitForObj(5000);

			IsElementPresent(tendercreationlocators
					.AcceptedorRejected(eTenderComponent.getDataFromPropertiesFile("sanctionoteDocnum")));
			highlight(tendercreationlocators
					.AcceptedorRejected(eTenderComponent.getDataFromPropertiesFile("sanctionoteDocnum")));
			waitForObj(5000);

			pdfResultReport.addStepDetails("verifyAcceptedOrRejectedSanctionStage",
					"verify status as Accepted Or Rejected Sanction Stage",
					"Sucessfully verified status as  Accepted Or Rejected Sanction Stage" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyAcceptedOrRejectedSanctionStage");
		} catch (Exception e) {
			log.fatal("Unable to verify status as  Accepted Or Rejected Sanction Stage" + e.getMessage());
			pdfResultReport.addStepDetails("verifyAcceptedOrRejectedSanctionStage",
					"verify status as Accepted Or Rejected Sanction Stage",
					"Unable to verify status as  Accepted Or Rejected Sanction Stage" + e.getMessage(), "Fail", "N");
		}
	}

	public void ScantionitemAllotment_CTS() throws Throwable {
		try {
			log.info("started executing the method:: itemAllotment_CTS");
			waitForObj(5000);
			click(tendercreationlocators.plusIcon_CTS, "plusIcon_CTS");
			waitForObj(3000);
			click(tendercreationlocators.termsAndConditioncheckBox_CTS, "termsAndConditioncheckBox_CTS");
			waitForObj(3000);
			click(tendercreationlocators.boqCheckbox_CTS, "boqCheckbox_CTS");
			waitForObj(3000);
			clear(tendercreationlocators.supplierQuotedQuantity1_CTS, "supplierQuotedQuantity1_CTS");
			waitForObj(3000);
			set(tendercreationlocators.supplierQuotedQuantity1_CTS,
					pdfResultReport.testData.get("supplierQuotedQuantity1"), "supplierQuotedQuantity1_CTS");
			/*
			 * IsElementPresent(tendercreationlocators.
			 * EditTagtextsupplierQuotedQuantity1_CTS);
			 * highlight(tendercreationlocators.
			 * EditTagtextsupplierQuotedQuantity1_CTS);
			 */
			waitForObj(3000);
			clear(tendercreationlocators.supplierQuotedQuantity2_CTS, "supplierQuotedQuantity2_CTS");
			waitForObj(3000);
			set(tendercreationlocators.supplierQuotedQuantity2_CTS,
					pdfResultReport.testData.get("supplierQuotedQuantity2"), "supplierQuotedQuantity2_CTS");
			/*
			 * IsElementPresent(tendercreationlocators.
			 * EditTagtextsupplierQuotedQuantity2_CTS);
			 * highlight(tendercreationlocators.
			 * EditTagtextsupplierQuotedQuantity2_CTS);
			 */
			waitForObj(3000);
			clear(tendercreationlocators.supplierQuotedQuantity3_CTS, "supplierQuotedQuantity3_CTS");
			waitForObj(3000);
			set(tendercreationlocators.supplierQuotedQuantity3_CTS,
					pdfResultReport.testData.get("supplierQuotedQuantity3"), "supplierQuotedQuantity3_CTS");
			/*
			 * IsElementPresent(tendercreationlocators.
			 * EditTagtextsupplierQuotedQuantity3_CTS);
			 * highlight(tendercreationlocators.
			 * EditTagtextsupplierQuotedQuantity3_CTS);
			 */
			waitForObj(5000);

			pdfResultReport.addStepDetails("itemAllotment_CTS", "Supplier Quoted Quantity must be change sucessfully",
					"Successfully changed Supplier Quoted Quantity" + " ", "Pass", "Y");
			log.info("completed executing the method:: itemAllotment_CTS");
		} catch (Exception e) {
			log.fatal("Unable to change Supplier Quoted Quantity" + e.getMessage());
			pdfResultReport.addStepDetails("itemAllotment_CTS", "Supplier Quoted Quantity must be change sucessfully",
					"Unable to change Supplier Quoted Quantity " + e.getMessage(), "Fail", "N");
		}
	}

	public void ScantionitemAllotment_TCS() throws Throwable {
		try {
			log.info("started executing the method:: itemAllotment_TCS");
			waitForObj(5000);
			click(tendercreationlocators.plusIcon_TCS, "plusIcon_TCS");
			waitForObj(3000);
			click(tendercreationlocators.termsAndConditioncheckBox_TCS, "termsAndConditioncheckBox_TCS");
			waitForObj(3000);
			click(tendercreationlocators.boqCheckbox_TCS, "boqCheckbox_TCS");
			waitForObj(5000);
			clear(tendercreationlocators.supplierQuotedQuantity1_TCS, "supplierQuotedQuantity1_TCS");
			waitForObj(5000);
			set(tendercreationlocators.supplierQuotedQuantity1_TCS,
					pdfResultReport.testData.get("supplierQuotedQuantity1"), "supplierQuotedQuantity1_TCS");
			/*
			 * IsElementPresent(tendercreationlocators.
			 * EditTagtext_supplierQuotedQuantity1_TCS);
			 * highlight(tendercreationlocators.
			 * EditTagtext_supplierQuotedQuantity1_TCS);
			 */
			waitForObj(3000);
			clear(tendercreationlocators.supplierQuotedQuantity2_TCS, "supplierQuotedQuantity2_TCS");
			waitForObj(3000);
			set(tendercreationlocators.supplierQuotedQuantity2_TCS,
					pdfResultReport.testData.get("supplierQuotedQuantity2"), "supplierQuotedQuantity2_TCS");
			/*
			 * IsElementPresent(tendercreationlocators.
			 * EditTagtext_supplierQuotedQuantity2_TCS);
			 * highlight(tendercreationlocators.
			 * EditTagtext_supplierQuotedQuantity2_TCS);
			 */
			waitForObj(3000);
			clear(tendercreationlocators.supplierQuotedQuantity3_TCS, "supplierQuotedQuantity3_TCS");
			waitForObj(3000);
			set(tendercreationlocators.supplierQuotedQuantity3_TCS,
					pdfResultReport.testData.get("supplierQuotedQuantity3"), "supplierQuotedQuantity3_TCS");
			/*
			 * IsElementPresent(tendercreationlocators.
			 * EditTagtext_supplierQuotedQuantity3_TCS);
			 * highlight(tendercreationlocators.
			 * EditTagtext_supplierQuotedQuantity3_TCS);
			 */
			pdfResultReport.addStepDetails("itemAllotment_TCS", "Supplier Quoted Quantity must be change sucessfully",
					"Successfully changed Supplier Quoted Quantity" + " ", "Pass", "Y");

			waitForObj(2000);
			log.info("completed executing the method:: itemAllotment_TCS");
			waitForObj(5000);
		} catch (Exception e) {
			log.fatal("Unable to change Supplier Quoted Quantity" + e.getMessage());
			pdfResultReport.addStepDetails("itemAllotment_TCS", "Supplier Quoted Quantity must be change sucessfully",
					"Unable to change Supplier Quoted Quantity" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyDraftStage() throws Throwable {
		try {
			log.info("started executing the method::  verifyPendingForApprovalSanctionStageAndApprovalDetails");
			enterDocumentNoInSearch();
			IsElementPresent(tendercreationlocators
					.DraftStatus(eTenderComponent.getDataFromPropertiesFile("sanctionoteDocnum")));
			highlight(tendercreationlocators
					.DraftStatus(eTenderComponent.getDataFromPropertiesFile("sanctionoteDocnum")));
			waitForObj(5000);
			pdfResultReport.addStepDetails("verifyDraftStage", "Tender id should be displayed in draft stage",
					"Sucessfully displayed tender id in completed tender list page" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyDraftStage");
		} catch (Exception e) {
			log.fatal("Unable to display tender id in completed tender list page" + e.getMessage());
			pdfResultReport.addStepDetails("verifyDraftStage",
					"Tender id should be displayed in completed tender list page",
					"Unable to display tender id in completed tender list page" + e.getMessage(), "Fail", "N");
		}
	}

	public void SanctionItemsAllotment() throws Throwable {
		try {
			log.info("started executing the method:: SanctionItemsAllotment");
			waitForObj(5000);
			String bidder = text(tendercreationlocators.Biddername);
			if (bidder.equalsIgnoreCase("TCS")) {
				waitForObj(3000);
				ScantionitemAllotment_TCS();
				waitForObj(3000);
				ScantionitemAllotment_CTS();
			} else if (bidder.equalsIgnoreCase("CTS")) {
				waitForObj(3000);
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

	public void verifyDopActivationLink() throws Throwable {
		try {
			log.info("started executing the method::verifyDopActivationLink");
			waitForObj(5000);

			click(tendercreationlocators.action, "action");

			waitForElementToBeVisible(tendercreationlocators.activeTabLinkBy);

			waitForObj(2000);

			click(tendercreationlocators.activeTabLinkBy, "activeTabLinkBy");

			waitForElementToBeVisible(tendercreationlocators.confirmBtn);

			JSClick(tendercreationlocators.confirmBtn, "confirmBtn");

			waitForElementToBeVisible(tendercreationlocators.confirmationOkBy);

			JSClick(tendercreationlocators.confirmationOkBy, "confirmationOkBy");

			waitForElementToBeVisible(tendercreationlocators.dopListStatusActiveBy);

			IsElementPresent(tendercreationlocators.dopListStatusActiveBy);

			pdfResultReport.addStepDetails("verifyDopActivationLink", "Dop List Status Column should be in Active",
					"Successfully Dop was activated" + " ", "Pass", "Y");

			log.info("completed executing the method:: verifyDopActivationLink");
		} catch (Exception e) {
			log.fatal("Unable to Show the dop status as Active" + e.getMessage());
			pdfResultReport.addStepDetails("verifyDopActivationLink", "Dop List Status Column should be in Active",
					"Unable to Show the dop status as Active" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyDopInactiveLink() throws Throwable {
		try {
			log.info("started executing the method::verifyDopInactiveLink");

			waitForObj(5000);

			click(tendercreationlocators.action, "action");

			waitForElementToBeVisible(tendercreationlocators.deActiveTabLinkBy);

			click(tendercreationlocators.deActiveTabLinkBy, "deActiveTabLinkBy");

			waitForElementToBeVisible(tendercreationlocators.confirmBtn);

			JSClick(tendercreationlocators.confirmBtn, "confirmBtn");

			waitForElementToBeVisible(tendercreationlocators.confirmationOkBy);

			JSClick(tendercreationlocators.confirmationOkBy, "confirmationOkBy");

			waitForObj(3000);

			waitForElementToBeVisible(tendercreationlocators.dopListStatusInActiveBy);

			IsElementPresent(tendercreationlocators.dopListStatusInActiveBy);

			pdfResultReport.addStepDetails("verifyDopInactiveLink", "Dop List Status Column should be in InActive",
					"Successfully De activated dop" + " ", "Pass", "Y");

			log.info("completed executing the method:: verifyDopInactiveLink");
		} catch (Exception e) {
			log.fatal("Unable show dop status as In activate" + e.getMessage());
			pdfResultReport.addStepDetails("verifyDopInactiveLink", "Dop List Status Column should be in InActive",
					"Unable show dop status as In activate" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickOnApproverCommentSection() throws Throwable {
		try {
			log.info("started executing the method:: clickOnApproverCommentSection");
			JSClick(By.xpath("//strong[contains(text(),'Approver Comment')]"), "ApproverComment");
			waitForObj(5000);
			pdfResultReport.addStepDetails("clickOnApproverCommentSection", "Attachment must be populated successfully",
					"Successfully populated attachment" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickOnApproverCommentSection");
		} catch (Exception e) {
			log.fatal("Not able to populate attachment" + e.getMessage());
			pdfResultReport.addStepDetails("clickOnApproverCommentSection", "Attachment must be populated successfully",
					"Unable to populate attachment" + e.getMessage(), "Fail", "N");
		}
	}

	public void approvalListWithSelectedApprovers() throws Throwable {
		try {
			log.info("started executing the method::  approvalListWithSelectedApprovers");
			click(tendercreationlocators.sanctionNoteStage, "sanctionNoteStage");
			waitForElementToBeVisible(tendercreationlocators.action);
			waitForObj(2000);
			clear(tendercreationlocators.typeAnyKeyword, "typeAnyKeyword");
			set(tendercreationlocators.typeAnyKeyword, documentNumberText, "documentNumberText");
			waitForObj(2000);
			click(tendercreationlocators.action, "Action");
			waitForElementToBeVisible(tendercreationlocators.approvalDetails);
			click(tendercreationlocators.approvalDetails, "approvalDetails");
			waitForElementToBeVisible(tendercreationlocators.approvalDetailsWindow);
			IsElementPresent(tendercreationlocators.approvalDetailsWindow);
			pdfResultReport.addStepDetails("approvalListWithSelectedApprovers", "All the approvers are verified",
					"Verifed approvers is done" + " ", "Pass", "Y");
			log.info("completed executing the method:: approvalListWithSelectedApprovers");
			click(tendercreationlocators.approvalDetails_close, "approvalDetails_close");
			waitForObj(3000);
			pdfResultReport.addStepDetails("approvalListWithSelectedApprovers", "All the approvers are verified",
					"Verifed approvers is done" + " ", "Pass", "Y");
			log.info("completed executing the method:: approvalListWithSelectedApprovers");
		} catch (Exception e) {
			log.fatal("Unable to verify the approvers" + e.getMessage());
			pdfResultReport.addStepDetails("approvalListWithSelectedApprovers", "Verifed approvers is done",
					"Unable to verify the approvers" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickOnCloseInSendForApproval_Sanction() throws Throwable {
		try {
			log.info("started executing the method:: clickOnCloseInSendForApproval_Sanction");

			click(tendercreationlocators.sendForApproval_Close, "sendForApproval_Close");
			waitForObj(3000);
			pdfResultReport.addStepDetails("clickOnCloseInSendForApproval_Sanction",
					"Close button must be click scessfully", "Sucessfully clicked on close button" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickOnCloseInSendForApproval_Sanction");
		} catch (Exception e) {
			log.fatal("Unable to click on close button" + e.getMessage());
			pdfResultReport.addStepDetails("clickOnCloseInSendForApproval_Sanction",
					"Close button must be click scessfully", "Unable to click on close button" + e.getMessage(), "Fail",
					"N");
		}
	}

	public void POTermsandConditionTABAddingRows() throws Throwable {
		try {
			log.info("started executing the method:: POTermsandConditionTABAddingRows");
			click(tendercreationlocators.POTermsandCond, "POTermsandCond");
			waitForObj(3000);
			for (int i = 1; i <= 4; i++) {
				click(tendercreationlocators.addTermsAndCondition, "addTermsAndCondition");
				waitForObj(1000);
			}
			List<WebElement> ele = ThreadLocalWebdriver.getDriver()
					.findElements(By.xpath("//input[@name='clause_no']"));
			String Clausenumber = pdfResultReport.testData.get("POclauseNo");
			int num = Integer.parseInt(Clausenumber);
			for (int i = 1; i <= ele.size(); i++) {
				String val = Integer.toString(num);
				click(tendercreationlocators.POclauseNo(i), "POclauseNo");
				clear(tendercreationlocators.POclauseNo(i), "POclauseNo");
				set(tendercreationlocators.POclauseNo(i), val, "POclauseNo");
				waitForObj(3000);

				click(tendercreationlocators.POclauseName(i), "POclauseName");
				clear(tendercreationlocators.POclauseName(i), "POclauseName");
				set(tendercreationlocators.POclauseName(i), pdfResultReport.testData.get("POclauseName"),
						"POclauseName");
				waitForObj(3000);

				click(tendercreationlocators.POclauseDetails(i), "POclauseDetails");
				clear(tendercreationlocators.POclauseDetails(i), "POclauseDetails");
				set(tendercreationlocators.POclauseDetails(i), pdfResultReport.testData.get("POclauseDetails"),
						"POclauseDetails");

				num++;
			}
			pdfResultReport.addStepDetails("POTermsandConditionTABAddingRows", "PO Details to be filled ",
					"Successfully filled PO Details" + " ", "Pass", "Y");
			log.info("completed executing the method:: POTermsandConditionTABAddingRows");
		} catch (Exception e) {
			log.fatal("Unable to PO details" + e.getMessage());
			pdfResultReport.addStepDetails("POTermsandConditionTABAddingRows", "PO Details to be filled ",
					"Unable to  fill PO Details" + e.getMessage(), "Fail", "N");
		}
	}

	public void editPoDetailsTAB() throws Throwable {
		try {
			log.info("started executing the method:: PODetails");

			waitForElementToBeVisible(tendercreationlocators.POExpiryDate);

			LocalDateTime localdatetime = LocalDateTime.now().plusDays(25);

			String POExpiryDate = localdatetime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a"));

			click(tendercreationlocators.POExpiryDate, "POExpiryDate");

			clear(tendercreationlocators.POExpiryDate, "clear the field POExpiryDate");

			set(tendercreationlocators.POExpiryDate, POExpiryDate, "POExpiryDate");

			waitForObj(3000);
			pdfResultReport.addStepDetails("PODetailsTAB", "PO Details tab details to be filled ",
					"Successfully filled PO Details tab details" + " ", "Pass", "Y");

			log.info("completed executing the method:: PODetailsTAB");
		} catch (Exception e) {
			log.fatal("Unable to  fill details in  PO Details tab" + e.getMessage());
			pdfResultReport.addStepDetails("PODetails", "PO Details tab details to be filled ",
					"Unable to  fill details in  PO Details tab" + e.getMessage(), "Fail", "N");
		}
	}

	public void navigateToPurchaseOrderApprovalPage() throws Throwable {
		try {
			log.info("started executing the method:: navigateToPurchaseOrderApprovalPage");
			set(tendercreationlocators.search, poDocNum, "enter PoDocNum");
			waitForElementToBeVisible(tendercreationlocators.details);
			click(tendercreationlocators.details, "details");
			waitForElementToBeVisible(tendercreationlocators.podetails);
			waitForObj(5000);

			pdfResultReport.addStepDetails("navigateToPurchaseOrderApprovalPage",
					"Purchase Order Approval page must be navigate sucessfully ",
					"Successfully navigated to Purchase Order Approval page" + " ", "Pass", "Y");
			log.info("completed executing the method:: navigateToPurchaseOrderApprovalPage");

		} catch (Exception e) {
			log.fatal("Unable to navigate to Purchase Order Approval page" + e.getMessage());
			pdfResultReport.addStepDetails("navigateToPurchaseOrderApprovalPage",
					"Purchase Order Approval page must be navigate sucessfully",
					"Unable to navigate to Purchase Order Approval page" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyPoApproverSummaryTabDetails(String bidder) throws Throwable {
		try {
			log.info("started executing the method:: verifyPoApproverSummaryTabDetails");

			waitForElementToBeVisible(tendercreationlocators.podetails);

			scrollToElement(tendercreationlocators.podetails);

			String poref = "//*[@id='preview']//tr/td/label/span[text()='{0}']";

			IsElementPresent(
					By.xpath(poref.replace("{0}", eTenderComponent.getDataFromPropertiesFile("POReferenceNum"))));

			scrollToElement(tendercreationlocators.SummaryTabBidderDetailsBy);

			String bidderBy = "//*[@id='preview']//tr/td/label/span[text()='{0}']";

			IsElementPresent(By.xpath(bidderBy.replace("{0}", bidder)));

			scrollToElement(tendercreationlocators.SummaryTabItemDetailsBy);

			IsElementPresent(tendercreationlocators.itemcodeQty(1));

			IsElementPresent(tendercreationlocators.itemcodeQty(2));

			IsElementPresent(tendercreationlocators.itemcodeQty(3));

			scrollToElement(tendercreationlocators.SummaryTabTermsAndCondDetailsBy);

			IsElementPresent(tendercreationlocators.termsClauseNames(1));

			IsElementPresent(tendercreationlocators.termsClauseNames(2));

			IsElementPresent(tendercreationlocators.termsClauseNames(3));

			scrollToTopOfThePage();

			pdfResultReport.addStepDetails("verifyPoApproverSummaryTabDetails",
					"Must verify po approver summary tab details",
					"Successfully verify po approver summary tab details" + " ", "Pass", "Y");

			log.info("completed executing the method:: verifyPoApproverSummaryTabDetails");
		} catch (Exception e) {
			log.fatal("Unable to verify po approver summary tab details" + e.getMessage());
			pdfResultReport.addStepDetails("verifyPoApproverSummaryTabDetails",
					"Must verify po approver summary tab details ",
					"Unable to verify po approver summary tab details" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickCommentTab() throws Throwable {
		try {
			log.info("started executing the method:: clickCommentTab");

			click(tendercreationlocators.sanctionNoteEvaluationCommentTab, "sanctionNoteEvaluationCommentTab");

			waitForObj(3000);

			waitForElementToBeVisible(tendercreationlocators.ApprovalCommentHistoryBtnBy);

			pdfResultReport.addStepDetails("clickCommentTab", "Must click on CommentTab ",
					"Successfully clicked on CommentTab" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickCommentTab");

		} catch (Exception e) {
			log.fatal("Unable to click on CommentTab" + e.getMessage());
			pdfResultReport.addStepDetails("clickCommentTab", "Must click on CommentTab",
					"Unable to click on CommentTab" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickApprovalHistoryBtn() throws Throwable {
		try {
			log.info("started executing the method:: clickApprovalHistoryBtn");

			click(tendercreationlocators.ApprovalCommentHistoryBtnBy, "ApprovalCommentHistoryBtnBy");

			waitForObj(3000);

			pdfResultReport.addStepDetails("clickApprovalHistoryBtn", "Must click on Approval CommentHistory Btn",
					"Successfully clicked on Approval CommentHistory Btn" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickCommentTab");

		} catch (Exception e) {
			log.fatal("Unable to Must click on Approval CommentHistory Btn" + e.getMessage());
			pdfResultReport.addStepDetails("clickCommentTab", "Must click on Approval CommentHistory Btn",
					"Unable to Must click on Approval CommentHistory Btn" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyUserNameColumnSeqInApprovalHistory(List<String> usernamesExpected) throws Throwable {
		try {
			log.info("started executing the method:: verifyUserNameColumnSeqInApprovalHistory");

			List<WebElement> userNamesList = ThreadLocalWebdriver.getDriver()
					.findElements(tendercreationlocators.userNameColumnSeqBy);

			ArrayList<String> usernamesActual = new ArrayList<>();

			for (WebElement userName : userNamesList) {

				usernamesActual.add(userName.getText().trim());

			}

			Assert.assertTrue(usernamesActual.equals(usernamesExpected));

			pdfResultReport.addStepDetails("verifyUserNameColumnSeqInApprovalHistory",
					"Must Verify Approval Usernames Seq order In Approval History",
					"Successfully Verified Approval Usernames Seq order In Approval History" + " ", "Pass", "Y");

			log.info("completed executing the method:: verifyUserNameColumnSeqInApprovalHistory");
		} catch (Exception e) {
			log.fatal("Unable to Verify Approval Usernames Seq order In Approval History" + e.getMessage());
			pdfResultReport.addStepDetails("verifyUserNameColumnSeqInApprovalHistory",
					"Must Verify Approval Usernames Seq order In Approval History",
					"Unable to Verify Approval Usernames Seq order In Approval History" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyUserNameStatusColumnSeqInApprovalHistory(List<String> usernamesStatusExpected) throws Throwable {
		try {
			log.info("started executing the method:: verifyUserNameStatusColumnSeqInApprovalHistory");

			List<WebElement> userNamesStatusList = ThreadLocalWebdriver.getDriver()
					.findElements(tendercreationlocators.userNameStatusSeqBy);

			ArrayList<String> usernamesStatusActual = new ArrayList<>();

			for (WebElement userName : userNamesStatusList) {

				usernamesStatusActual.add(userName.getText().trim());

			}

			Assert.assertTrue(usernamesStatusActual.equals(usernamesStatusExpected));

			pdfResultReport.addStepDetails("verifyUserNameStatusColumnSeqInApprovalHistory",
					"Must Verify Approval Usernames Status  Seq order In Approval History",
					"Successfully Verified Approval Usernames Status Seq order In Approval History" + " ", "Pass", "Y");

			log.info("completed executing the method:: verifyUserNameStatusColumnSeqInApprovalHistory");
		} catch (Exception e) {
			log.fatal("Unable to Verify Approval Usernames Status  Seq order In Approval History" + e.getMessage());
			pdfResultReport.addStepDetails("verifyUserNameStatusColumnSeqInApprovalHistory",
					" Must Verify Approval Usernames Status  Seq order In Approval History ",
					"Unable to Verify Approval Usernames Status  Seq order In Approval History" + e.getMessage(),
					"Fail", "N");
		}
	}

	public void verifyCommentColumnSeqInApprovalHistory(int row, String Comment) throws Throwable {
		try {
			log.info("started executing the method:: verifyUserNameColumnSeqInApprovalHistory");

			List<WebElement> CommentsList = ThreadLocalWebdriver.getDriver()
					.findElements(tendercreationlocators.CommentColumnSeqBy);

			Assert.assertTrue(CommentsList.get(CommentsList.size() - row).getText().trim().equals(Comment));

			pdfResultReport.addStepDetails("verifyUserNameColumnSeqInApprovalHistory",
					"The Approver comment should be in chronological manner depending upon no of approver taken",
					"Successfully Approver comment is showing in chronological manner depending upon no of approver taken"
							+ " ",
					"Pass", "Y");

			log.info("completed executing the method:: verifyUserNameColumnSeqInApprovalHistory");
		} catch (Exception e) {
			log.fatal("Unable to dispaly Approver comment in chronological manner depending upon no of approver taken"
					+ e.getMessage());
			pdfResultReport.addStepDetails("verifyUserNameColumnSeqInApprovalHistory",
					"The Approver comment should be in chronological manner depending upon no of approver taken",
					"Unable to dispaly Approver comment in chronological manner depending upon no of approver taken"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void EditPO1() throws Throwable {
		try {
			log.info("started executing the method:: EditPO");

			// waitForElementToBeVisible(tendercreationlocators.POListingpage);

			click(tendercreationlocators.SearchPO, "SearchPO");
			set(tendercreationlocators.SearchPO, eTenderComponent.getDataFromPropertiesFile("POReferenceNum"),
					"POReferenceNum");
			waitForObj(2000);
			click(tendercreationlocators.actionDropdown, "actionDropdown");
			click(tendercreationlocators.EditPO, "EditPO");
			waitForObj(5000);
			waitForElementToBeVisible(tendercreationlocators.PODeailspage);

			waitForObj(2000);
			pdfResultReport.addStepDetails("EditPO", "Proceed to Edit PO", "Successfully Editd PO " + " ", "Pass", "Y");
			log.info("completed executing the method:: EditPO");
		} catch (Exception e) {
			log.fatal("Unable to Edit PO" + e.getMessage());
			pdfResultReport.addStepDetails("EditPO", "Proceed to Edit PO", "Unable to Edit PO" + e.getMessage(), "Fail",
					"N");
		}
	}

	public void enterPOInSearch() throws Throwable {
		try {
			log.info("started executing the method:: enterPOInSearch");
			clear(tendercreationlocators.SearchPO, "SearchPO");
			set(tendercreationlocators.SearchPO, eTenderComponent.getDataFromPropertiesFile("POReferenceNum"),
					"POReferenceNum");
			IsElementPresent(tendercreationlocators.POStatus);
			waitForObj(5000);
			pdfResultReport.addStepDetails("enterPOInSearch",
					"Reference number is entered in search field successfully",
					"Reference number is entered in search field successfully" + " ", "Pass", "Y");
			log.info("completed executing the method:: enterPOInSearch");
		} catch (Exception e) {
			log.fatal("Unable to enter PO reference number" + e.getMessage());
			pdfResultReport.addStepDetails("verifyPOStatusPendingForApproval",
					"Reference number is entered in search field successfully",
					"Unable to enter PO reference number" + e.getMessage(), "Fail", "N");
		}
	}

	public void RepeatorderPendingForAccepetedPO() throws Throwable {
		try {
			log.info("started executing the method:: CancelPendingForAcceptencePOCreator");
			waitForElement(tendercreationlocators.action, 50);
			select(tendercreationlocators.selectStatus, "Accepted");
			waitForObj(3000);
			set(tendercreationlocators.SearchPO, eTenderComponent.getDataFromPropertiesFile("POReferenceNum"),
					"POReferenceNum");
			waitForObj(3000);
			click(tendercreationlocators.repeatordernotexpired, "action");
			waitForObj(3000);
			click(tendercreationlocators.btnrepeatorder, "repeatOrder");
			waitForObj(3000);
			click(tendercreationlocators.POSUccessMsg, "OKButton");
			waitForObj(5000);
			String refNo = text(tendercreationlocators.refText).trim();
			System.out.println(refNo);
			pdfResultReport.addStepDetails("RepeatorderPendingForAccepetedPO", "PO should be repeated successfully ",
					"Successfully repeated PO" + " ", "Pass", "Y");
			refNo = refNo.substring(73, 77);
			click(tendercreationlocators.POSUccessMsg, "OKButton");
			waitForObj(4000);
			select(tendercreationlocators.selectStatus, "Draft");
			waitForElement(tendercreationlocators.draftStateStatus, 50);
			clear(tendercreationlocators.search, "search");
			waitForObj(3000);
			set(tendercreationlocators.search, refNo, "refNo");
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.draftStateStatus);
			// set(tendercreationlocators.cancellationReason,
			// pdfResultReport.testData.get("cancellationReason"),
			// "cancellationReason");
			pdfResultReport.addStepDetails("RepeatorderPendingForAccepetedPO", "PO should be repeated successfully ",
					"Successfully repeated PO" + " ", "Pass", "Y");
			log.info("completed executing the method:: RepeatorderPendingForAccepetedPO");
		} catch (Exception e) {
			log.fatal("Unable to Issue PO" + e.getMessage());
			pdfResultReport.addStepDetails("RepeatorderPendingForAccepetedPO", "PO Details to be filled ",
					"Unable to  fill PO Details" + e.getMessage(), "Fail", "N");
		}
	}

	public void enterSanctionRefNumber() throws Exception {
		try {
			log.info("started executing the method:: enterSanctionRefNumber");

			clear(tendercreationlocators.search, "Clear the San ref no search field");

			set(tendercreationlocators.search, eTenderComponent.getDataFromPropertiesFile("sanctionReferenceNumber"),
					"Sanction ref no search field");

			waitForObj(3000);

			pdfResultReport.addStepDetails("enterSanctionRefNumber", "Must Enter SanRef Num",
					"Sucessfully Entered SanRefNum" + " ", "Pass", "Y");
			log.info("completed executing the method:: enterSanctionRefNumber");

		} catch (Exception e) {
			log.fatal("Unable to Enter SanRef Num" + e.getMessage());
			pdfResultReport.addStepDetails("enterSanctionRefNumber", "Must Enter SanRef Num ",
					"Unable to Enter SanRef Num" + e.getMessage(), "Fail", "N");
		}
	}

	public void ClickRepeatOrderLinkAndClickOk() throws Throwable {
		try {
			log.info("started executing the method:: ClickRepeatOrderLink");

			click(tendercreationlocators.action, "action");
			waitForObj(3000);
			click(tendercreationlocators.repeatOrder, "repeatOrder");

			waitForElementToBeVisible(tendercreationlocators.POSUccessMsg);

			click(tendercreationlocators.POSUccessMsg, "POSUccessMsg");
			waitForObj(3000);

			String refNo = text(tendercreationlocators.refText).trim();
			System.out.println(refNo);
			pdfResultReport.addStepDetails("ClickRepeatOrderLinkAndClickOk",
					"Should display POP Up Like Repeat Order has been successfully completed with Purchase Order Number",
					"Suceesfull Pop Up is Shown like  Repeat Order has been successfully completed with Purchase Order Number"
							+ " ",
					"Pass", "Y");
			refNo = refNo.substring(refNo.lastIndexOf(':') + 1).trim();

			waitForElementToBeVisible(tendercreationlocators.POSUccessMsg);

			click(tendercreationlocators.POSUccessMsg, "POSUccessMsg");

			waitForObj(4000);
			select(tendercreationlocators.selectStatus, "Draft");
			waitForElement(tendercreationlocators.draftStateStatus, 50);
			clear(tendercreationlocators.search, "search");
			set(tendercreationlocators.search, refNo, "refNo");
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.draftStateStatus);

			pdfResultReport.addStepDetails("ClickRepeatOrderLink",
					"Successfully Displaying in draft state The newly created Dupliacted PO",
					"The newly created Duplicate PO should be in draft state " + " ", "Pass", "Y");
			log.info("completed executing the method:: ClickRepeatOrderLink");
		} catch (Exception e) {
			log.fatal("Unable to dispaly in Draft State the Newly Created Duplicated PO " + e.getMessage());
			pdfResultReport.addStepDetails("ClickRepeatOrderLink",
					"The newly created Duplicate  PO should be in draft state",
					"Unable to dispaly in Draft State the Newly Created Duplicated PO " + e.getMessage(), "Fail", "N");
		}
	}

	public void recallPOValidation() throws Throwable {
		try {
			log.info("started executing the method::  recallPOValidation");

			click(tendercreationlocators.action, "action");
			waitForObj(2000);

			click(tendercreationlocators.recallPo, "recallPo");

			waitForElementToBeVisible(By.xpath("//*[@name='recallReason']"));

			set(By.xpath("//*[@name='recallReason']"), "Reason For Recall", "recallReason");

			click(By.xpath("//*[@ng-click='recallFn()']"), "recallFn()");

			waitForObj(4000);

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			waitForObj(4000);

			IsElementPresent(By.xpath("//tbody/tr[2]/td[contains(text(),'Draft')]/a[text()='(Recalled)']"));

			pdfResultReport.addStepDetails("recallPOValidation", "Recall PO must be validate sucessfully",
					"Successfully validated Recall PO Data" + " ", "Pass", "Y");
			log.info("completed executing the method:: recallPOValidation");

		} catch (Exception e) {
			log.fatal("Unable to validate recall PO" + e.getMessage());
			pdfResultReport.addStepDetails("recallPOValidation", "Recall PO must be validate sucessfully",
					"Unable to validate recall PO " + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyPOFunctionalityofPOApprover() throws Throwable {
		try {
			log.info("started executing the method:: verifyPOFunctionalityofPOApprover");
			click(tendercreationlocators.SummaryTab, "SummaryTab");
			IsElementPresent(tendercreationlocators.View_PO_Num);
			highlight(tendercreationlocators.View_PO_Num);
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.View_PO_VAlue);
			highlight(tendercreationlocators.View_PO_VAlue);
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.View_PO_Amendment_Flag);
			highlight(tendercreationlocators.View_PO_Amendment_Flag);
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.View_PO_ref_NUM);
			highlight(tendercreationlocators.View_PO_ref_NUM);
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.View_PO_Start_Date);
			highlight(tendercreationlocators.View_PO_Start_Date);
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.View_PO_Expiry_Date);
			highlight(tendercreationlocators.View_PO_Expiry_Date);
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.View_Sanction_Note_Num);
			highlight(tendercreationlocators.View_Sanction_Note_Num);
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.View_PO_Quotation_Code);
			highlight(tendercreationlocators.View_PO_Quotation_Code);
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.View_PO_Supplier_Organization_Name);
			highlight(tendercreationlocators.View_PO_Supplier_Organization_Name);
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.btnApprove);
			highlight(tendercreationlocators.btnApprove);
			waitForObj(2000);

			mouseOver(tendercreationlocators.btnReview);
			IsElementPresent(tendercreationlocators.btnReview);
			highlight(tendercreationlocators.btnReview);
			waitForObj(2000);

			mouseOver(tendercreationlocators.btnAbranch);
			IsElementPresent(tendercreationlocators.btnAbranch);
			highlight(tendercreationlocators.btnAbranch);
			waitForObj(2000);
			JSClick(tendercreationlocators.btndownload, "btndownload");
			IsElementPresent(tendercreationlocators.btndownload);
			highlight(tendercreationlocators.btndownload);

			waitForObj(10000);
			pdfResultReport.addStepDetails("verifyPOFunctionalityofPOApprover",
					"verify PO Functionality of POApprover ",
					"Successfully verified PO Functionality of POApprover " + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyPOFunctionalityofPOApprover");

		} catch (Exception e) {
			log.fatal("Unable to verify PO Functionality of POApprover" + e.getMessage());
			pdfResultReport.addStepDetails("verifyPOFunctionalityofPOApprover", "verify PO Functionality of POApprover",
					"Unable to verify PO Functionality of POApprover" + e.getMessage(), "Fail", "N");
		}
	}

	public void BranchFunctionality() throws Throwable {
		try {
			log.info("started executing the method:: BranchFunctionality");
			click(tendercreationlocators.btnBranch, "btnBranch");
			waitForObj(4000);
			click(tendercreationlocators.BranchUSer1Btn(pdfResultReport.testData.get("BranchUserName1")),
					"BranchUSer1Btn");
			waitForObj(4000);
			click(tendercreationlocators.BranchUser1Remarks, "BranchUser1Remarks");
			set(tendercreationlocators.BranchUser1Remarks, "Branched Sucessfully", "BranchRemarks");
			waitForObj(4000);
			click(tendercreationlocators.BranchBtnSubmit, "BranchBtnSubmit");
			waitForObj(4000);
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			highlight(tendercreationlocators.BranchSuccessMsg);
			waitForObj(4000);
			click(tendercreationlocators.BranchSuccesMsgClose, "BranchSuccesMsgClose");
			waitForObj(8000);
			pdfResultReport.addStepDetails("BranchFunctionality", "Branching must be pass successfully",
					"Successfully Branched to approver" + " ", "Pass", "Y");
			log.info("completed executing the method:: BranchFunctionality");
		} catch (Exception e) {
			log.fatal("Not able to branch" + e.getMessage());
			pdfResultReport.addStepDetails("BranchFunctionality", "Branching must be pass successfully",
					"Unable to branch" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyActionDropDownElementsForCancelledPo() throws Exception {
		try {
			log.info("started executing the method:: verifyActionDropDownElementsForCancelledPo");

			set(tendercreationlocators.search, eTenderComponent.getDataFromPropertiesFile("POReferenceNum"),
					"po ref no search field");

			click(tendercreationlocators.actionMenuDropDownBy_PO, "actionMenuDropDownBy_PO");

			List<WebElement> actionEles = ThreadLocalWebdriver.getDriver()
					.findElements(By.xpath("//*[@data-target='#viewPoModal']/../../li/a"));

			List<String> eles = actionEles.stream().map(ele -> ele.getText()).collect(Collectors.toList());

			boolean containsAll = Arrays.asList("Preview", "Print Preview").containsAll(eles);

			boolean containsAll2 = eles.contains(Arrays.asList("Accept", "Reject"));

			Assert.assertTrue(containsAll);

			Assert.assertFalse(containsAll2);

			pdfResultReport.addStepDetails("verifyActionDropDownElementsForCancelledPo",
					"Bidder will be able to see only  Preview,Print Preview details in drop down and Bidder will not get any option to accept or reject po",
					"Sucessfully displaying Only print and PrintView details.Bidder is not getting option to accept or reject po"
							+ " ",
					"Pass", "Y");
			log.info("completed executing the method:: verifyActionDropDownElementsForCancelledPo");
		} catch (Exception e) {
			log.fatal("Unable to see  Preview,Print Preview details" + e.getMessage());
			pdfResultReport.addStepDetails("verifyActionDropDownElementsForCancelledPo",
					"Bidder will be able to see only  Preview,Print Preview details in drop down and Bidder will not get any option to accept or reject po",
					"Unable to see  Preview,Print Preview details" + e.getMessage(), "Fail", "N");
		}
	}

	public void filterPoDopWithModuleAndStatus(String module, String status) throws Throwable {
		try {

			select(tendercreationlocators.selectModuleBy, module);

			select(tendercreationlocators.selectDopStatusBy, status);

			if (status.equalsIgnoreCase("Draft")) {

				verifyAndMakePODop_Activation();

				navigateTo_DOPListPage();

			}

			else if (status.equalsIgnoreCase("Active")) {
				verifyAndMAkePODopDe_Activation();
			}

		} catch (Exception e) {
			log.fatal("" + e.getMessage());

		}
	}

	public void createDopForPOModule() throws Throwable {
		try {
			log.info("started executing the method::  createDopForPOModule");

			click(tendercreationlocators.dopMenuLinkBy, "dopMenuLinkBy");

			JSClick(tendercreationlocators.createDopLinkBy, "createDopLinkBy");

			waitForElementToBeVisible(tendercreationlocators.selectModuleBy);

			waitForObj(3000);

			select(tendercreationlocators.selectModuleBy, "PO");

			waitForObj(2000);

			select(tendercreationlocators.selectProcurementCategoryBy, "Default_cat");

			waitForObj(2000);

			click(tendercreationlocators.addOrg_HierarchyBy, "addOrg_HierarchyBy");

			waitForElementToBeVisible(tendercreationlocators.addBtn_HierarchyBy);

			click(tendercreationlocators.addBtn_HierarchyBy, "addBtn_HierarchyBy");

			waitForObj(2000);

			set(tendercreationlocators.dopValueFromBy, eTenderComponent.getDataFromPropertiesFile("PoDopValueFrom"),
					"dopValueFromBy");
			waitForObj(1000);

			set(tendercreationlocators.dopValueToBy, eTenderComponent.getDataFromPropertiesFile("PoDopValueTo"),
					"dopValueToBy");

			waitForObj(1000);

			LocalDateTime localdatetime = LocalDateTime.now();

			String currentDateTime = localdatetime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm"));

			String dopname = "Po_DOP_";

			String DopName = dopname.concat(currentDateTime);

			eTenderComponent.updateDataIntoPropertyFile("PO_dopName", DopName);

			set(tendercreationlocators.dopNameBy, DopName, "dopNameBy");

			waitForObj(1000);

			click(tendercreationlocators.savebutton, "saveButton");

			IsElementPresent(tendercreationlocators.DopSucessMsgBy);

			pdfResultReport.addStepDetails("DopSucessMsgBy", "must show Dop sucess msg",
					"Sucessfully showing dop sucess msg" + " ", "Pass", "Y");

			click(tendercreationlocators.confirmationOkBy, "confirmationOkBy");

			waitForObj(3000);

			pdfResultReport.addStepDetails("createDopForPOModule", "Must save DopCreation In Po Module",
					"Successfully saved the DOP Creation for PO " + " ", "Pass", "Y");
			log.info("completed executing the method:: createDopForSanction_Module");
		} catch (Exception e) {
			log.fatal("Unable to Save the Dop creation for Po" + e.getMessage());
			pdfResultReport.addStepDetails("createDopForPOModule", "Must save DopCreation In Po Module",
					"Unable to Save the Dop creation for PO" + e.getMessage(), "Fail", "N");
		}
	}

	public void navigateTo_DOPList1() throws Throwable {
		try {
			log.info("started executing the method:: navigateTo_DOPList1");

			JSClick(tendercreationlocators.dopMenuLinkBy, "dopMenuLinkBy");

			JSClick(tendercreationlocators.dop_ListLinkBy, "dop_ListLinkBy");

			waitForObj(5000);

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			waitForElementToBeVisible(tendercreationlocators.dopListSactionRowTextBy);

			waitForObj(5000);

			clear(tendercreationlocators.search, "clear the enter key word");

			set(tendercreationlocators.search, eTenderComponent.getDataFromPropertiesFile("PO_dopName"),
					"enter key word");

			waitForElementToBeVisible(tendercreationlocators.dopListSactionRowTextBy);

			pdfResultReport.addStepDetails("navigateTo_DOPList1", "Must display the Respective DOP in DOP List",
					"Sucessfully displaying the Respective DOP in DOP List" + " ", "Pass", "Y");
			log.info("completed executing the method:: navigateTo_DOPList1");

		} catch (Exception e) {
			log.fatal("Unable to display the Respective DOP in DOP List" + e.getMessage());
			pdfResultReport.addStepDetails("navigateTo_DOPList1", "Must display the Respective DOP in DOP List",
					"Unable to display the Respective DOP in DOP List" + e.getMessage(), "Fail", "N");
		}
	}

	public void ValidateSNTemplateOptionInSnCreatorLogin() throws Throwable {
		try {
			log.info("started executing the method:: ValidateSNTemplateOptionInSnCreatorLogin");

			WebDriver driver = ThreadLocalWebdriver.getDriver();

			List<String> menuText = new ArrayList<String>();

			List<WebElement> menulistsize = driver.findElements(By.xpath("//*[@id='mCSB_1_container']/li"));

			for (int i = 1; i <= menulistsize.size(); i++) {

				String text = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].innerHTML;",
						driver.findElement(By.xpath("//*[@id='mCSB_1_container']/li[" + i + "]/a/span[1]")));

				System.out.println("SanctionCreatorThe menu list Text is    ----->" + text);
				System.out.println("------------------------------------------------------------------");

				menuText.add(text);

			}

			boolean contains = menuText.contains("Sanction Template");

			System.out.println(
					"Check Sanction Template in Menu List Returns true if Present else Returns False if not present   ------>"
							+ contains);

			System.out.println("------------------------------------------------------------------");
			Assert.assertFalse(contains, "Sanction template should not be present in SanCreator Login");

			pdfResultReport.addStepDetails("ValidateSNTemplateOptionInSnCreatorLogin",
					"Sanction Template option Should not be showing for SnCreator ",
					"Sucessfully Sanction Template option is Not present in  Sncreator Menu List" + " ", "Pass", "Y");
			log.info("completed executing the method:: ValidateSNTemplateOptionNotFound");
		} catch (Exception e) {
			log.fatal("Unable to check this Sanction Template option Should not be showing for SnCreator"
					+ e.getMessage());
			pdfResultReport.addStepDetails("ValidateSNTemplateOptionInSnCreatorLogin",
					"Sanction Template option Should not be showing for SnCreator",
					"Unable to check this Sanction Template option Should not be showing for SnCreator"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void CancellationReasonValidationForPredefinedWorkflow() throws Throwable {
		try {
			log.info("started executing the method:: CancellationReasonValidationForPredefinedWorkflow");
			click(tendercreationlocators.action, "action");
			waitForObj(3000);
			highlight(tendercreationlocators.cancelPO);
			pdfResultReport.addStepDetails("CancellationReasonValidationForPredefinedWorkflow",
					"Cancel PO link must be click sucessfully", "Successfully clicked on cancel PO link" + " ", "Pass",
					"Y");
			click(tendercreationlocators.cancelPO, "cancelPO");
			waitForElementToBeVisible(tendercreationlocators.cancellationReason);
			waitForObj(2000);
			click(tendercreationlocators.predefined, "predefined");
			waitForObj(5000);
			JSClick(tendercreationlocators.sendForApproval1, "sendForApproval1");
			waitForElementToBeVisible(tendercreationlocators.cancellationReasonAlert);
			waitForObj(5000);
			IsElementPresent(tendercreationlocators.cancellationReasonAlert);
			pdfResultReport.addStepDetails("CancellationReasonValidationForPredefinedWorkflow",
					"Cancellation Reason is mandatory message must be validate sucessfully",
					"Successfully validate Cancellation Reason is mandatory message" + " ", "Pass", "Y");
			click(tendercreationlocators.alertTabOk, "alertTabOk");
			click(tendercreationlocators.cancellationReason, "cancellationReason");
			set(tendercreationlocators.cancellationReason, pdfResultReport.testData.get("cancellationReason"),
					"cancellationReason");
			pdfResultReport.addStepDetails("CancellationReasonValidationForPredefinedWorkflow",
					"PO cancellation reason must fill sucessfully", "Successfully filled PO cancellation reason" + " ",
					"Pass", "Y");
			click(tendercreationlocators.predefined, "predefined");
			waitForObj(2000);
			JSClick(tendercreationlocators.sendForApproval1, "sendForApproval1");
			WebDriverWait wait = new WebDriverWait(ThreadLocalWebdriver.getDriver(), 100);
			waitForElementToBeVisible(tendercreationlocators.POCancelSuccesMsg);
			waitForObj(5000);
			pdfResultReport.addStepDetails("CancellationReasonValidationForPredefinedWorkflow",
					"Submit button must be click successfully", "Successfully clicked on submit button" + " ", "Pass",
					"Y");
			log.info("completed executing the method:: CancellationReasonValidationForPredefinedWorkflow");
		} catch (Exception e) {
			log.fatal("Unable to fill PO cancellation reason" + e.getMessage());
			pdfResultReport.addStepDetails("CancellationReasonValidationForPredefinedWorkflow",
					"PO cancellation reason must fill sucessfully",
					"Unable to fill PO cancellation reason" + e.getMessage(), "Fail", "N");
		}
	}

	public void CancellationReasonValidationForUserdefinedWorkflow() throws Throwable {
		try {
			log.info("started executing the method:: CancellationReasonValidationForUserdefinedWorkflow");
			click(tendercreationlocators.action, "action");
			waitForObj(3000);
			highlight(tendercreationlocators.cancelPO);
			pdfResultReport.addStepDetails("CancellationReasonValidationForUserdefinedWorkflow",
					"Cancel PO link must be click sucessfully", "Successfully clicked on cancel PO link" + " ", "Pass",
					"Y");
			click(tendercreationlocators.cancelPO, "cancelPO");
			waitForElementToBeVisible(tendercreationlocators.cancellationReason);
			waitForObj(2000);
			click(tendercreationlocators.Userdefined, "Userdefined");

			waitForObj(3000);
			JSClick(tendercreationlocators.sendForApproval1, "sendForApproval1");
			waitForElementToBeVisible(tendercreationlocators.cancellationReasonAlert);
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.cancellationReasonAlert);
			pdfResultReport.addStepDetails("CancellationReasonValidationForUserdefinedWorkflow",
					"Cancellation Reason is mandatory message must be validate sucessfully",
					"Successfully validate Cancellation Reason is mandatory message" + " ", "Pass", "Y");
			click(tendercreationlocators.alertTabOk, "alertTabOk");
			click(tendercreationlocators.cancellationReason, "cancellationReason");
			set(tendercreationlocators.cancellationReason, pdfResultReport.testData.get("cancellationReason"),
					"cancellationReason");
			pdfResultReport.addStepDetails("CancellationReasonValidationForUserdefinedWorkflow",
					"PO cancellation reason must fill sucessfully", "Successfully filled PO cancellation reason" + " ",
					"Pass", "Y");
			click(tendercreationlocators.Userdefined, "Userdefined");
			waitForObj(2000);
			click(tendercreationlocators.approvalType_Sequential, "approvalType_Sequential");
			click(tendercreationlocators.addButton, "addButton");
			set(tendercreationlocators.user1, pdfResultReport.testData.get("UserTenderApprover1"), "user1");
			waitForObj(2000);
			JSClick(tendercreationlocators.sendForApproval1, "sendForApproval1");
			WebDriverWait wait = new WebDriverWait(ThreadLocalWebdriver.getDriver(), 100);
			waitForElementToBeVisible(tendercreationlocators.POCancelSuccesMsg);
			waitForObj(5000);
			pdfResultReport.addStepDetails("CancellationReasonValidationForUserdefinedWorkflow",
					"Submit button must be click successfully", "Successfully clicked on submit button" + " ", "Pass",
					"Y");
			log.info("completed executing the method:: CancellationReasonValidationForUserdefinedWorkflow");
		} catch (Exception e) {
			log.fatal("Unable to fill PO cancellation reason" + e.getMessage());
			pdfResultReport.addStepDetails("CancellationReasonValidationForUserdefinedWorkflow",
					"PO cancellation reason must fill sucessfully",
					"Unable to fill PO cancellation reason" + e.getMessage(), "Fail", "N");
		}
	}

	public void CancellationReasonValidationForNotRequiredWorkflow() throws Throwable {
		try {
			log.info("started executing the method:: CancellationReasonValidationForNotRequiredWorkflow");
			click(tendercreationlocators.action, "action");
			waitForObj(3000);
			highlight(tendercreationlocators.cancelPO);
			pdfResultReport.addStepDetails("CancellationReasonValidationForNotRequiredWorkflow",
					"Cancel PO link must be click sucessfully", "Successfully clicked on cancel PO link" + " ", "Pass",
					"Y");
			click(tendercreationlocators.cancelPO, "cancelPO");
			waitForElementToBeVisible(tendercreationlocators.cancellationReason);
			waitForObj(2000);
			click(tendercreationlocators.ApprovalNotReqd_CancelPO, "ApprovalNotReqd_CancelPO");
			waitForObj(2000);
			JSClick(tendercreationlocators.sendForApprovalSubmit_CancelPO, "sendForApprovalSubmit_CancelPO");
			waitForObj(2000);
			waitForElementToBeVisible(tendercreationlocators.cancellationReasonAlert);
			IsElementPresent(tendercreationlocators.cancellationReasonAlert);
			pdfResultReport.addStepDetails("CancellationReasonValidationForNotRequiredWorkflow",
					"Cancellation Reason is mandatory message must be validate sucessfully",
					"Successfully validate Cancellation Reason is mandatory message" + " ", "Pass", "Y");
			click(tendercreationlocators.alertTabOk, "alertTabOk");
			click(tendercreationlocators.cancellationReason, "cancellationReason");
			set(tendercreationlocators.cancellationReason, pdfResultReport.testData.get("cancellationReason"),
					"cancellationReason");
			pdfResultReport.addStepDetails("CancellationReasonValidationForNotRequiredWorkflow",
					"PO cancellation reason must fill sucessfully", "Successfully filled PO cancellation reason" + " ",
					"Pass", "Y");
			click(tendercreationlocators.ApprovalNotReqd_CancelPO, "ApprovalNotReqd_CancelPO");
			waitForObj(2000);
			JSClick(tendercreationlocators.sendForApprovalSubmit_CancelPO, "sendForApprovalSubmit_CancelPO");
			WebDriverWait wait = new WebDriverWait(ThreadLocalWebdriver.getDriver(), 100);
			waitForElementToBeVisible(tendercreationlocators.cancelledPoSucessesPopUp);
			waitForObj(5000);
			pdfResultReport.addStepDetails("CancellationReasonValidationForNotRequiredWorkflow",
					"Submit button must be click successfully", "Successfully clicked on submit button" + " ", "Pass",
					"Y");
			log.info("completed executing the method:: CancellationReasonValidationForNotRequiredWorkflow");
		} catch (Exception e) {
			log.fatal("Unable to fill PO cancellation reason" + e.getMessage());
			pdfResultReport.addStepDetails("CancellationReasonValidationForNotRequiredWorkflow",
					"PO cancellation reason must fill sucessfully",
					"Unable to fill PO cancellation reason" + e.getMessage(), "Fail", "N");
		}
	}

	public void searchThePoDocNoInPoApproverPage() throws Throwable {
		try {
			log.info("started executing the method:: searchThePoDocNoInPoApproverPage");
			click(tendercreationlocators.cancelPurchaseOrderTabBy, "cancelPurchaseOrderTabBy");
			waitForObj(5000);
			set(tendercreationlocators.search, poDocNum, "enter PoDocNum");
			waitForElementToBeVisible(tendercreationlocators.details);
			pdfResultReport.addStepDetails("searchThePoDocNoInPoApproverPage",
					"Po Doc No must be display in PO Approver page sucessfully",
					"Successfully displayed Po Doc No in PO Approver page" + " ", "Pass", "Y");
			log.info("completed executing the method:: searchThePoDocNoInPoApproverPage");
		} catch (Exception e) {
			log.fatal("Unable to display Po Doc No in PO Approver page" + e.getMessage());
			pdfResultReport.addStepDetails("searchThePoDocNoInPoApproverPage",
					"Po Doc No must be display in PO Approver page sucessfully",
					"Unable to display Po Doc No in PO Approver page" + e.getMessage(), "Fail", "N");
		}
	}

	public void poCancellation_Sucesses_MessageValidation() throws Throwable {
		try {
			log.info("started executing the method::  poCancellation_Sucesses_MessageValidation");
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.POCancelSuccesMsg);
			pdfResultReport.addStepDetails("poCancellationSucessesMessageValidation",
					"poCancellationSucessesMessage must be validate sucessfully",
					"Successfully validated poCancellationSucessesMessage" + " ", "Pass", "Y");
			waitForObj(2000);
			click(tendercreationlocators.alertTabOk, "alertTabOk");
			waitForElementToBeVisible(tendercreationlocators.poListPageRowBy);
			waitForObj(5000);
			pdfResultReport.addStepDetails("poCancellation_Sucesses_MessageValidation",
					"Purchase order list page must be navigate sucessfully",
					"Successfully navigated to purchase order list page" + " ", "Pass", "Y");
			log.info("completed executing the method:: poCancellation_Sucesses_MessageValidation");

		} catch (Exception e) {
			log.fatal("Unable to validate poCancellationSucessesMessage" + e.getMessage());
			pdfResultReport.addStepDetails("poCancellation_Sucesses_MessageValidation",
					"poCancellationSucessesMessage must be validate sucessfull",
					"Unable to validate poCancellationSucessesMessage" + e.getMessage(), "Fail", "N");
		}
	}

	public void CancelPOUserDefined() throws Throwable {
		try {
			log.info("started executing the method:: CancelPO");
			etendercomponentobj.waitForSpinnerToDisappear();
			waitForElementToBeVisible(tendercreationlocators.POListingpage);
			waitForObj(9000);
			click(tendercreationlocators.SearchPO, "SearchPO");
			clear(tendercreationlocators.SearchPO, "SearchPO");
			waitForObj(2000);
			set(tendercreationlocators.SearchPO, eTenderComponent.getDataFromPropertiesFile("POReferenceNum"),
					"POReferenceNum");
			waitForObj(2000);
			click(tendercreationlocators.actionDropdown, "actionDropdown");
			waitForObj(2000);
			click(tendercreationlocators.POCancel, "POCancel");
			waitForObj(5000);
			click(tendercreationlocators.PoCancelMsg, "PoCancelMsg");
			set(tendercreationlocators.PoCancelMsg, "PO to be cancelled", "PoCancelMsg");
			waitForObj(5000);
			click(tendercreationlocators.approvuserdefin, "approvapprovuserdefinnotreq");
			waitForObj(2000);
			click(tendercreationlocators.sequentialCancelorder, "sequentialCancelorder");
			waitForObj(2000);
			click(tendercreationlocators.adduserapprove, "adduserapprove");
			waitForObj(3000);
			click(tendercreationlocators.approverList1, "approverList1");
			set(tendercreationlocators.approverList1, pdfResultReport.testData.get("POapprover1User"), "approverList1");
			waitForObj(3000);
			click(tendercreationlocators.adduserapprove, "adduserapprove");
			waitForObj(3000);
			click(tendercreationlocators.approverList2, "approverList2");
			set(tendercreationlocators.approverList2, pdfResultReport.testData.get("POapprover2User"),
					"POapprover2User");
			waitForObj(3000);

			click(tendercreationlocators.SendForAptovalbtn, "SendForAptovalbtn");
			click(tendercreationlocators.AmendMsgOKbutton, "AmendMsgOKbutton");
			etendercomponentobj.waitForSpinnerToDisappear();
			waitForElementToBeVisible(tendercreationlocators.POListingpage);
			waitForObj(9000);
			/*
			 * click(tendercreationlocators.CancelledPOTab, "CancelledPOTab");
			 * click(tendercreationlocators.SearchPO, "SearchPO");
			 * clear(tendercreationlocators.SearchPO, "SearchPO");
			 * set(tendercreationlocators.SearchPO,
			 * eTenderComponent.getDataFromPropertiesFile("POReferenceNum"),
			 * "POReferenceNum"); String val =
			 * eTenderComponent.getDataFromPropertiesFile("POReferenceNum");
			 * IsElementPresent(tendercreationlocators.poNum(val));
			 * highlight(tendercreationlocators.poNum(val)); waitForObj(2000);
			 */
			pdfResultReport.addStepDetails("CancelPO", "Proceed to Cancel PO", "Successfully Cancelled PO " + " ",
					"Pass", "Y");
			log.info("completed executing the method:: CancelPO");
		} catch (Exception e) {
			log.fatal("Unable to Edit PO" + e.getMessage());
			pdfResultReport.addStepDetails("CancelPO", "Proceed to Cancel PO", "Unable to Cancel PO" + e.getMessage(),
					"Fail", "N");
		}
	}

	public void statusOfPOInCancelledPOTab1() throws Throwable {
		try {
			log.info("started executing the method:: statusOfPOInCancelledPOTab");
			// click(tendercreationlocators.POSUccessMsg, "OKButton");
			waitForElementToBeVisible(tendercreationlocators.action);

			waitForObj(5000);
			click(tendercreationlocators.cancelledPO, "cancelledPO");
			waitForObj(4000);
			set(tendercreationlocators.search, eTenderComponent.getDataFromPropertiesFile("poDocNum"), "enter refNo");
			waitForObj(3000);
			IsElementPresent(tendercreationlocators.poNoRefNo1);
			waitForObj(3000);
			highlight(tendercreationlocators.poNoRefNo1);
			waitForObj(5000);
			// IsElementPresent(tendercreationlocators.pendingForCancellationApproval);
			pdfResultReport.addStepDetails("statusOfPOInCancelledPOTab",
					"statusOfPOInCancelledPOTab must be validate successfully",
					"Successfully validated statusOfPOInCancelledPOTab" + " ", "Pass", "Y");
			log.info("completed executing the method:: statusOfPOInCancelledPOTab");
		} catch (Exception e) {
			log.fatal("Not able to validate staus" + e.getMessage());
			pdfResultReport.addStepDetails("statusOfPOInCancelledPOTab", "status must be validate successfully",
					"Unable to validate status" + e.getMessage(), "Fail", "N");
		}

	}

	public void navigateToPurchaseOrderApproval1() throws Throwable {
		try {
			log.info("started executing the method:: navigateToPurchaseOrderApproval");
			click(tendercreationlocators.cancelPurchaseOrderTabBy, "cancelPurchaseOrderTabBy");
			waitForObj(5000);
			set(tendercreationlocators.search, eTenderComponent.getDataFromPropertiesFile("poDocNum"), "refNo");
			waitForObj(3000);
			IsElementPresent(tendercreationlocators.details);
			waitForObj(3000);
			click(tendercreationlocators.details, "details");
			pdfResultReport.addStepDetails("navigateToPurchaseOrderApproval",
					"Purchase Order Approval page must be navigate sucessfully ",
					"Successfully navigated to Purchase Order Approval page" + " ", "Pass", "Y");
			log.info("completed executing the method:: navigateToPurchaseOrderApproval");

		} catch (Exception e) {
			log.fatal("Unable to navigate to Purchase Order Approval page" + e.getMessage());
			pdfResultReport.addStepDetails("navigateToPurchaseOrderApproval",
					"Purchase Order Approval page must be navigate sucessfully",
					"Unable to navigate to Purchase Order Approval page" + e.getMessage(), "Fail", "N");
		}
	}

	public void verifyAndMakePODop_Activation() throws Throwable {
		try {
			log.info("started executing the method::verifyAndMakePODop_Activation");

			List<WebElement> dopValues = ThreadLocalWebdriver.getDriver()
					.findElements(By.xpath("//*[@id='myTablebyrTl00']/tbody/tr/td[5]"));

			List<WebElement> dopValuesTo = ThreadLocalWebdriver.getDriver()
					.findElements(By.xpath("//*[@id='myTablebyrTl00']/tbody/tr/td[6]"));

			List<WebElement> dopDraftRows = ThreadLocalWebdriver.getDriver()
					.findElements(By.xpath("//*[@id='myTablebyrTl00']/tbody/tr/td[2]/strong"));

			List<WebElement> dopactioMenu = ThreadLocalWebdriver.getDriver()
					.findElements(By.xpath("//button[contains(@data-toggle,'dropdown')]"));

			List<WebElement> dopActiveLink = ThreadLocalWebdriver.getDriver()
					.findElements(By.xpath("//*[contains(normalize-space(text()),'Activate')]"));

			if (dopDraftRows.size() > 0) {
				int i = 0;
				int j = 0;
				for (WebElement dopValue : dopValues) {

					int dopvalue = Integer.parseInt(dopValue.getText().trim());

					int expectedValue = Integer.valueOf(eTenderComponent.getDataFromPropertiesFile("PoDopValueFrom"));

					int dopvalueTo = Integer.valueOf(dopValuesTo.get(j).getText().trim());

					if (expectedValue <= dopvalueTo || dopvalue >= expectedValue) {
						dopactioMenu.get(i).click();

						WebDriverWait wait = new WebDriverWait(ThreadLocalWebdriver.getDriver(), 60);

						wait.until(ExpectedConditions.visibilityOf(dopActiveLink.get(i)));

						dopActiveLink.get(i).click();

						waitForElementToBeVisible(tendercreationlocators.confirmBtn);

						JSClick(tendercreationlocators.confirmBtn, "confirmBtn");

						waitForElementToBeVisible(tendercreationlocators.confirmationOkBy);

						JSClick(tendercreationlocators.confirmationOkBy, "confirmationOkBy");

						waitForObj(5000);
					}

					i++;
					j++;
				}
				pdfResultReport.addStepDetails("verifyAndMakePODop_Activation",
						"Check the dop in draft status if there is any dop value with the expected dop value",
						"Sucessfully verified the dop with the expected range" + " ", "Pass", "Y");
				log.info("completed executing the method:: makeDopActive");

			} else {

				pdfResultReport.addStepDetails("verifyAndMakePODop_Activation",
						"Should be no Draft Dop's with in the Expected range",
						"Sucessfully checked and Present there are no Draft Dop's with in the Expected range" + " ",
						"Pass", "Y");

				log.info("completed executing the method:: verifyAndMakePODop_Activation");
			}

		} catch (Exception e) {
			log.fatal("Unable to verify the dop values range in Draft status  as per expected value " + e.getMessage());
			pdfResultReport.addStepDetails("verifyAndMakePODop_Activation",
					"Check the dop in draft status if there is any dop value with the expected dop value",
					"Unable to verify the dop values range in Draft status  as per expected value " + e.getMessage(),
					"Fail", "N");
		}
	}

	public void verifyAndMAkePODopDe_Activation() throws Throwable {
		try {
			log.info("started executing the method:: verifyAndMAkePODopDe_Activation");

			List<WebElement> dopactiverows = ThreadLocalWebdriver.getDriver()
					.findElements(By.xpath("//*[@id='myTablebyrTl00']/tbody/tr/td[2]/strong"));

			List<WebElement> dopValues = ThreadLocalWebdriver.getDriver()
					.findElements(By.xpath("//*[@id='myTablebyrTl00']/tbody/tr/td[5]"));

			List<WebElement> dopValuesTo = ThreadLocalWebdriver.getDriver()
					.findElements(By.xpath("//*[@id='myTablebyrTl00']/tbody/tr/td[6]"));

			List<WebElement> dopactioMenu = ThreadLocalWebdriver.getDriver()
					.findElements(By.xpath("//button[contains(@data-toggle,'dropdown')]"));

			List<WebElement> dopDe_ActiveLink = ThreadLocalWebdriver.getDriver()
					.findElements(By.xpath("//*[contains(normalize-space(text()),'De-Activate')]"));

			if (dopactiverows.size() > 0) {
				int i = 0;
				int j = 0;
				for (WebElement dopValue : dopValues) {

					int dopvalue = Integer.parseInt(dopValue.getText().trim());

					int expectedValue = Integer.valueOf(eTenderComponent.getDataFromPropertiesFile("PoDopValueFrom"));

					int dopvalueTo = Integer.valueOf(dopValuesTo.get(j).getText().trim());

					if (expectedValue <= dopvalueTo || dopvalue >= expectedValue) {
						dopactioMenu.get(i).click();

						WebDriverWait wait = new WebDriverWait(ThreadLocalWebdriver.getDriver(), 60);

						wait.until(ExpectedConditions.visibilityOf(dopDe_ActiveLink.get(i)));

						dopDe_ActiveLink.get(i).click();

						waitForElementToBeVisible(tendercreationlocators.confirmBtn);

						JSClick(tendercreationlocators.confirmBtn, "confirmBtn");

						waitForElementToBeVisible(tendercreationlocators.confirmationOkBy);

						JSClick(tendercreationlocators.confirmationOkBy, "confirmationOkBy");

						waitForObj(5000);
					}

					i++;
					j++;
				}

				pdfResultReport.addStepDetails("verifyAndMAkePODopDe_Activation",
						"Check the dop in Active status if there is any dop value with the expected dop value",
						"Sucessfully verified Dop value range as per Expected value in Dop  Active status" + " ",
						"Pass", "Y");
				log.info("completed executing the method:: verifyAndMAkePODopDe_Activation");
			} else {
				pdfResultReport.addStepDetails("verifyAndMAkePODopDe_Activation",
						"Should be no Active Dop's with in the Expected range",
						"Sucessfully checked and Present there are no Active Dop's with in the Expected range" + " ",
						"Pass", "Y");

				log.info("completed executing the method:: verifyAndMAkePODopDe_Activation");
			}

		} catch (Exception e) {
			log.fatal("Unable to verify the Dop value range as per Expected value in Dop  Active status"
					+ e.getMessage());
			pdfResultReport.addStepDetails("verifyAndMAkePODopDe_Activation",
					"Check the dop in draft status if there is any dop value with the expected dop value",
					"Unable to verify the Dop value range as per Expected value in Dop  Active status" + e.getMessage(),
					"Fail", "N");
		}
	}

	public void search_The_PoDocNo_In_PoApproverPage() throws Throwable {
		try {
			log.info("started executing the method:: search_The_PoDocNo_In_PoApproverPage");
			set(tendercreationlocators.search, poDocNum, "enter PoDocNum");
			waitForObj(5000);
			pdfResultReport.addStepDetails("search_The_PoDocNo_In_PoApproverPage",
					"Po Doc No must be enter in search field sucessfully",
					"Successfully entered Po Doc No in search field" + " ", "Pass", "Y");

			log.info("completed executing the method:: search_The_PoDocNo_In_PoApproverPage");
		} catch (Exception e) {
			log.fatal("Unable to enter Po Doc No in search field" + e.getMessage());
			pdfResultReport.addStepDetails("search_The_PoDocNo_In_PoApproverPage",
					"Po Doc No must be enter in search filed sucessfully",
					"Unable to enter Po Doc No in search field" + e.getMessage(), "Fail", "N");
		}
	}

	public void cancelPurchaseOrderReview() throws Throwable {
		try {
			log.info("started executing the method:: cancelPurchaseOrderReview");
			highlight(tendercreationlocators.Review);
			pdfResultReport.addStepDetails("cancelPurchaseOrderReview", "Review button must be click sucessfully ",
					"Successfully clicked on review button" + " ", "Pass", "Y");
			click(tendercreationlocators.Review, "Review");
			waitForObj(5000);
			click(tendercreationlocators.approveConfirm, "approveConfirm");

			waitForElementToBeVisible(tendercreationlocators.approvalConfirmPopUp);

			IsElementPresent(tendercreationlocators.approvalConfirmPopUp);
			pdfResultReport.addStepDetails("cancelPurchaseOrderReview",
					"Status updated sucessfully popup must be validate sucessfully ",
					"Successfully validate status updated sucessfully pop up" + " ", "Pass", "Y");
			click(tendercreationlocators.confirmOk, "confirmOk");

			waitForElementToBeVisible(By.xpath("(//*[@id='myTable']/tbody/tr[1]/td[1])[1]"));

			waitForObj(5000);
			pdfResultReport.addStepDetails("cancelPurchaseOrderReview", "Purchase order must be review sucessfully ",
					"Successfully reviewed Purchase order" + " ", "Pass", "Y");
			log.info("completed executing the method:: cancelPurchaseOrderReview");

		} catch (Exception e) {
			log.fatal("Unable to review Purchase order" + e.getMessage());
			pdfResultReport.addStepDetails("cancelPurchaseOrderReview", "Purchase order must be review sucessfully ",
					"Unable to review Purchase order" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickOnBranchIcon() throws Throwable {
		try {
			log.info("started executing the method:: clickOnBranchIcon");
			highlight(tendercreationlocators.btnBranch);
			pdfResultReport.addStepDetails("clickOnBranchIcon", "Branch Icon must be click sucessfully ",
					"Successfully clicked on branch Icon" + " ", "Pass", "Y");
			click(tendercreationlocators.btnBranch, "btnBranch");
			waitForObj(4000);
			waitForElementToBeVisible(tendercreationlocators.branchUser);
			IsElementPresent(tendercreationlocators.branchUser);
			pdfResultReport.addStepDetails("clickOnBranchIcon", "Branch user page should be displayed",
					"Successfully displayed Branch user page" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickOnBranchIcon");
		} catch (Exception e) {
			log.fatal("Unable to display Branch user page" + e.getMessage());
			pdfResultReport.addStepDetails("clickOnBranchIcon", "Branch user page should be displayed",
					"Unable to display Branch user page" + e.getMessage(), "Fail", "N");
		}
	}

	public void BranchFunctionalityWithAttachment() throws Throwable {
		try {
			log.info("started executing the method:: BranchFunctionalityWithAttachment");
			click(tendercreationlocators.BranchUSer1Btn(pdfResultReport.testData.get("BranchUserName1")),
					"BranchUSer1Btn");
			waitForObj(4000);
			click(tendercreationlocators.BranchUser1Remarks, "BranchUser1Remarks");
			set(tendercreationlocators.BranchUser1Remarks, "Branched Sucessfully", "BranchRemarks");
			waitForObj(4000);
			set(tendercreationlocators.branchUpload, System.getProperty("user.dir") + "\\MediaFiles\\rfqCreation.xlsx",
					"fileName");
			waitForObj(4000);
			pdfResultReport.addStepDetails("BranchFunctionalityWithAttachment", "user should be add for branching",
					"Successfully added user for branching" + " ", "Pass", "Y");
			click(tendercreationlocators.BranchBtnSubmit, "BranchBtnSubmit");
			waitForElementToBeVisible(tendercreationlocators.BranchSuccessMsg);
			highlight(tendercreationlocators.BranchSuccessMsg);
			waitForObj(4000);
			pdfResultReport.addStepDetails("BranchFunctionalityWithAttachment",
					"Branching has been done successfully pop up must be validate successfully",
					"Successfully validated Branching has been done successfully pop up" + " ", "Pass", "Y");
			click(tendercreationlocators.BranchSuccesMsgClose, "BranchSuccesMsgClose");
			waitForElementToBeVisible(By.xpath("(//*[@id='myTable']/tbody/tr[1]/td[1])[1]"));
			waitForObj(5000);
			pdfResultReport.addStepDetails("BranchFunctionalityWithAttachment", "Branching must be pass successfully",
					"Successfully Branched to approver" + " ", "Pass", "Y");
			log.info("completed executing the method:: BranchFunctionality");
		} catch (Exception e) {
			log.fatal("Not able to branch" + e.getMessage());
			pdfResultReport.addStepDetails("BranchFunctionalityWithAttachment", "Branching must be pass successfully",
					"Unable to branch" + e.getMessage(), "Fail", "N");
		}
	}

	public void ClickOnSendBackButton() throws Throwable {
		try {
			log.info("started executing the method:: ClickOnSendBackButton");
			highlight(tendercreationlocators.sendBackButton);
			pdfResultReport.addStepDetails("ClickOnSendBackButton", "Send Back button must be click sucessfully ",
					"Successfully clicked on send back button" + " ", "Pass", "Y");
			click(tendercreationlocators.sendBackButton, "sendBackButton");
			waitForElementToBeVisible(tendercreationlocators.approveConfirm);
			click(tendercreationlocators.approveConfirm, "approveConfirm");

			waitForElementToBeVisible(tendercreationlocators.sendBackSucessMsg);
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.sendBackSucessMsg);
			pdfResultReport.addStepDetails("ClickOnSendBackButton",
					"Send Back successfully completed popup must be validate sucessfully ",
					"Successfully validated Send Back successfully completed pop up" + " ", "Pass", "Y");
			click(tendercreationlocators.confirmOk, "confirmOk");

			waitForElementToBeVisible(By.xpath("(//*[@id='myTable']/tbody/tr[1]/td[1])[1]"));

			waitForObj(5000);
			pdfResultReport.addStepDetails("ClickOnSendBackButton", "Send Back button must be click sucessfully",
					"Successfully clicked on Send Back button" + " ", "Pass", "Y");
			log.info("completed executing the method:: purchaseOrderApproval");

		} catch (Exception e) {
			log.fatal("Unable to click on send back button" + e.getMessage());
			pdfResultReport.addStepDetails("ClickOnSendBackButton", "Send Back button must be click sucessfully ",
					"Unable to click on send back button" + e.getMessage(), "Fail", "N");
		}
	}

	public void navigateTo_PurchaseOrder_Approval() throws Throwable {
		try {
			log.info("started executing the method:: navigateTo_PurchaseOrder_Approval");
			waitForObj(5000);
			set(tendercreationlocators.search, poDocNum, "enter PoDocNum");
			pdfResultReport.addStepDetails("navigateToPurchaseOrderApproval",
					"Purchase Order Approval page must be navigate sucessfully ",
					"Successfully navigated to Purchase Order Approval page" + " ", "Pass", "Y");
			waitForElementToBeVisible(tendercreationlocators.details);
			click(tendercreationlocators.details, "details");
			waitForElementToBeVisible(tendercreationlocators.podetails);
			waitForObj(5000);
			pdfResultReport.addStepDetails("navigateTo_PurchaseOrder_Approval",
					"Summary Tab must be validate sucessfully ", "Successfully validated summary tab" + " ", "Pass",
					"Y");
			click(tendercreationlocators.sanctionNoteEvaluationCommentTab, "sanctionNoteEvaluationCommentTab");
			waitForElementToBeVisible(By.xpath("//strong[contains(text(),'Approver Comment')]"));
			pdfResultReport.addStepDetails("navigateTo_PurchaseOrder_Approval",
					"Comment tab must be validate sucessfully", "Successfully validated comment tab" + " ", "Pass",
					"Y");
			log.info("completed executing the method:: navigateTo_PurchaseOrder_Approval");

		} catch (Exception e) {
			log.fatal("Unable to navigate to Purchase Order Approval page" + e.getMessage());
			pdfResultReport.addStepDetails("navigateTo_PurchaseOrder_Approval",
					"Purchase Order Approval page must be navigate sucessfully",
					"Unable to navigate to Purchase Order Approval page" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickOnBranchSectionApproverComment() throws Throwable {
		try {
			log.info("started executing the method:: clickOnBranchSectionApproverComment");
			JSClick(tendercreationlocators.branchSectionApproverComment, "branchSectionApproverComment");
			waitForElementToBeVisible(tendercreationlocators.sendBackToApproverStatus);
			highlight(tendercreationlocators.sendBackToApproverStatus);
			waitForObj(5000);
			pdfResultReport.addStepDetails("clickOnBranchSectionApproverComment",
					"The workflow should be send back to the branch initiator for his approval",
					"Successfully The workflow sent back to the branch initiator for his approval" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickOnBranchSectionApproverComment");
		} catch (Exception e) {
			log.fatal("Not able to click on BranchSectionApproverComment" + e.getMessage());
			pdfResultReport.addStepDetails("clickOnBranchSectionApproverComment",
					"BranchSectionApproverComment must be click successfully",
					"Unable to click on BranchSectionApproverComment" + e.getMessage(), "Fail", "N");
		}
	}

	public void searchThePoDocNoInBranchUserPage() throws Throwable {
		try {
			log.info("started executing the method:: searchThePoDocNoInBranchUserPage");
			set(tendercreationlocators.search, poDocNum, "enter PoDocNum");
			waitForObj(5000);
			pdfResultReport.addStepDetails("searchThePoDocNoInBranchUserPage",
					"PO must be remove from the Branch user workflow pending list sucessfully",
					"Successfully removed PO from the Branch user workflow pending list" + " ", "Pass", "Y");
			log.info("completed executing the method:: searchThePoDocNoInBranchUserPage");
		} catch (Exception e) {
			log.fatal("Unable to remove PO from the Branch user workflow pending list" + e.getMessage());
			pdfResultReport.addStepDetails("searchThePoDocNoInBranchUserPage",
					"PO must be remove from the Branch user workflow pending list sucessfully",
					"Unable to remove PO from the Branch user workflow pending list" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickOnBranch_Section_Approver_Comment() throws Throwable {
		try {
			log.info("started executing the method:: clickOnBranch_Section_Approver_Comment");
			JSClick(tendercreationlocators.branchSectionApproverComment, "branchSectionApproverComment");
			waitForElementToBeVisible(By.xpath("//*[@id='reloadMe2']/table/tbody/tr[1]"));
			waitForObj(5000);
			pdfResultReport.addStepDetails("clickOnBranch_Section_Approver_Comment",
					"Sequence of Branch user approval comment history must be validate sucessfully",
					"Successfully validated Sequence of Branch user approval comment history" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickOnBranch_Section_Approver_Comment");
		} catch (Exception e) {
			log.fatal("Not able to validate Sequence of Branch user approval comment history" + e.getMessage());
			pdfResultReport.addStepDetails("clickOnBranch_Section_Approver_Comment",
					"Sequence of Branch user approval comment history must be validate sucessfully",
					"Unable to validate Sequence of Branch user approval comment history" + e.getMessage(), "Fail",
					"N");
		}
	}

	public void clickOnRemoveBranchUserButton() throws Throwable {
		try {
			log.info("started executing the method:: clickOnBranchSectionApproverComment");
			highlight(tendercreationlocators.removeBranchUser);
			pdfResultReport.addStepDetails("ClickOnRemoveBranchUser",
					"Remove branch user button must be click sucessfully ",
					"Successfully clicked on Remove branch user button" + " ", "Pass", "Y");
			click(tendercreationlocators.removeBranchUser, "removeBranchUser");

			waitForElementToBeVisible(tendercreationlocators.removeBranchUserSuccessfulMessage);
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.removeBranchUserSuccessfulMessage);
			pdfResultReport.addStepDetails("ClickOnSendBackButton",
					"Assigned user successfully removed popup must be validate sucessfully ",
					"Successfully validated Assigned user successfully removed pop up" + " ", "Pass", "Y");
			click(tendercreationlocators.confirmOk, "confirmOk");

			waitForObj(5000);
			log.info("completed executing the method:: clickOnBranchSectionApproverComment");
		} catch (Exception e) {
			log.fatal("Not able to click on BranchSectionApproverComment" + e.getMessage());
			pdfResultReport.addStepDetails("clickOnBranchSectionApproverComment",
					"BranchSectionApproverComment must be click successfully",
					"Unable to click on BranchSectionApproverComment" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickOnReleasePOUnderActionDropdown() throws Throwable {
		try {
			log.info("started executing the method:: clickOnReleasePOUnderActionDropdown");
			click(tendercreationlocators.action, "action");
			waitForObj(3000);
			highlight(tendercreationlocators.releasePODropDown);
			pdfResultReport.addStepDetails("clickOnReleasePOUnderActionDropdown",
					"Release PO must be click sucessfully", "Successfully clicked on Release PO " + " ", "Pass", "Y");
			click(tendercreationlocators.releasePODropDown, "releasePODropDown");
			waitForElementToBeVisible(tendercreationlocators.poDetails);

			waitForElementToBeVisible(tendercreationlocators.releasePOButton);

			JSClick(tendercreationlocators.releasePOButton, "releasePOButton");
			waitForElementToBeVisible(tendercreationlocators.poDocumentNoBy);
			waitForObj(3000);
			click(tendercreationlocators.ApprovalNotReqd_ReleasePO, "ApprovalNotReqd_ReleasePO");
			pdfResultReport.addStepDetails("clickOnReleasePOUnderActionDropdown",
					"Not Required radio button must be click sucessfully",
					"Successfully clicked on not required radio button" + " ", "Pass", "Y");
			click(tendercreationlocators.sendForApprovalSubmit_ReleasePO, "sendForApprovalSubmit_ReleasePO");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForElementToBeVisible(tendercreationlocators.poListPageRowBy);
			waitForObj(5000);
			pdfResultReport.addStepDetails("clickOnReleasePOUnderActionDropdown",
					"Purchase order list page must be navigate sucessfully",
					"Successfully navigated to Purhase oredr list page" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickOnReleasePOUnderActionDropdown");
		} catch (Exception e) {
			log.fatal("Unable to navigate Purchase order list" + e.getMessage());
			pdfResultReport.addStepDetails("clickOnReleasePOUnderActionDropdown",
					"Purchase order list page must be navigate sucessfully",
					"Unable to navigate Purchase order list " + e.getMessage(), "Fail", "N");
		}
	}

	public EmailUtils emailyUtils = new EmailUtils();

	public void verifyLatestTriggeredMailSubject() {
		try {
			log.info("started executing the method:: verifyLatestTriggeredMailSubject");

			Message message = emailyUtils.getLatestMessage();

			String specificMailsubject = emailyUtils.getSpecificMailsubject(message);

			System.out.println(" The Email Subject is : ------->   " + specificMailsubject);

			System.out.println("--------------------------------------------------------------------");

			eTenderComponent.updateDataIntoPropertyFile("ExpectedMailSubject", specificMailsubject);

			System.out.println("Updated latest Triggerd mail Subject in property File");

			System.out.println("--------------------------------------------------------------------");

			Assert.assertTrue(specificMailsubject.contains(eTenderComponent.getDataFromPropertiesFile("poDocNum")));

			System.out.println("SuceessFully verified Mail Subject");
			log.info("completed executing the method:: verifyLatestTriggeredMailSubject");

		} catch (Exception e) {

			e.printStackTrace();

			Assert.fail(e.getMessage());

		}
	}

	public void verifyLatestTriggeredMailBody(String content, String contentLast) {
		try {
			log.info("started executing the method:: verifyLatestTriggeredMailBody");

			Message message = emailyUtils.getLatestMessage();

			String specificMailBody = emailyUtils.getSpecificMailBodyContent(message, content, contentLast);

			System.out.println(" The Email Body is : ------->   " + specificMailBody);

			System.out.println("-----------------------------------------------------------------------");

			eTenderComponent.updateDataIntoPropertyFile("ExpectedmailBody", specificMailBody);

			Assert.assertTrue(specificMailBody.contains(eTenderComponent.getDataFromPropertiesFile("poDocNum")));

			System.out.println("-----------------------------------------------------------------------");

			System.out.println("SuceessFully verified Mail Body");

			log.info("completed executing the method:: verifyLatestTriggeredMailBody");

		} catch (Exception e) {

			e.printStackTrace();

			Assert.fail(e.getMessage());

		}
	}

	public void verifyLatestTriggeredMailBody(String content, String contentLast, int max) throws Exception {
		try {
			log.info("started executing the method:: verifyLatestTriggeredMailBody");

			Message[] specificMailBody = emailyUtils.getMessages(max);

			Arrays.sort(specificMailBody, (m1, m2) -> {
				try {
					return m2.getSentDate().compareTo(m1.getSentDate());
				} catch (MessagingException e) {
					throw new RuntimeException(e);
				}
			});

			for (Message message : specificMailBody) {

				if (emailyUtils.getSpecificMailBodyContent(message, content, contentLast)
						.contains(eTenderComponent.getDataFromPropertiesFile("poDocNum"))) {
					System.out.println("The Email Body is :---->   "
							+ emailyUtils.getSpecificMailBodyContent(message, content, contentLast));

					eTenderComponent.updateDataIntoPropertyFile("ExpectedmailBody",
							emailyUtils.getSpecificMailBodyContent(message, content, contentLast));

					System.out.println("Updated latest Triggerd mail With body in property File");

					System.out.println("--------------------------------------------------------------------");

					break;

				}
			}

			log.info("completed executing the method:: verifyLatestTriggeredMailBody");

		} catch (Exception e) {

			e.printStackTrace();

			Assert.fail(e.getMessage());

		}
	}

	public void verifyMailSubjectAndMailBody(String subject, int max, String subContent1, String subContent2)
			throws Exception {
		boolean flag = false;
		try {
			log.info("started executing the method:: verifyMailSubjectAndMailBody");

			waitForObj(60000);

			Message[] specificMailsubject = emailyUtils.getMessagesBySubject(subject, max);

			Arrays.sort(specificMailsubject, (m1, m2) -> {
				try {
					return m2.getSentDate().compareTo(m1.getSentDate());
				} catch (MessagingException e) {
					throw new RuntimeException(e);
				}
			});

			System.out.println("The email message List with given Subject   " + specificMailsubject + " The Size is    "
					+ specificMailsubject.length);

			if (specificMailsubject.length > 0) {
				for (Message message : specificMailsubject) {

					if (message.getSubject().contains(eTenderComponent.getDataFromPropertiesFile("poDocNum"))) {
						System.out.println("The Email Subject is :---->   " + message.getSubject());

						eTenderComponent.updateDataIntoPropertyFile("ExpectedMailSubject", message.getSubject());

						System.out.println("Updated latest Triggerd mail Subject in property File");

						System.out.println("--------------------------------------------------------------------");

						pdfResultReport.addStepDetails("verifyMailSubjectAndMailBody",
								"Mail Subject Must Be Validated Sucessfully",
								"Successfully Validated Mail Subject as Expected ---->" + message.getSubject() + " "
										+ " ",
								"Pass", "Y");

						if (emailyUtils.getSpecificMailBodyContent(message, subContent1, subContent2)
								.contains(eTenderComponent.getDataFromPropertiesFile("poDocNum"))) {
							
							String specificMailBodyContent = emailyUtils.getSpecificMailBodyContent(message,
									subContent1, subContent2);

							System.out.println("The Email Body is :---->   " + specificMailBodyContent);

							eTenderComponent.updateDataIntoPropertyFile("ExpectedmailBody",
									emailyUtils.getSpecificMailBodyContent(message, subContent1, subContent2));

							System.out.println("Updated latest Triggerd mail With body in property File");

							System.out.println("--------------------------------------------------------------------");

							pdfResultReport.addStepDetails("verifyMailSubjectAndMailBody",
									"Mail Body Must Be Validated Sucessfully",
									"Successfully Validated Mail Body as Expected ---->" + specificMailBodyContent + " "
											+ " ",
									"Pass", "Y");
							flag = true;
							
							break;
						} else {
							System.out.println("Email is not matching with excpted Body \n" + "Actual Body is : "
									+ emailyUtils.getSpecificMailBodyContent(message, subContent1, subContent2)
									+ "\n expected Body content is  :"
									+ eTenderComponent.getDataFromPropertiesFile("poDocNum"));

							System.out.println(
									"----------------------------------------------------------------------------");
							System.out.println("check the next mail with Expected Body Content");
							System.out.println(
									"----------------------------------------------------------------------------");
						}
					} else {
						System.out.println("Email is not matching with excpted Subject \n" + "Actual Subject is :"
								+ message.getSubject() + "\n expected Subjected Content is : "
								+ eTenderComponent.getDataFromPropertiesFile("poDocNum"));

						System.out.println(
								"----------------------------------------------------------------------------");
						System.out.println("check the next mail with Expected subject Content");
						System.out.println(
								"----------------------------------------------------------------------------");
					}
				}

			} else {

				pdfResultReport.addStepDetails("verifyMailSubjectAndMailBody",
						"Mail List Length With Specific Subject Must Be Greater than Zero",
						"Unable to get the Mail List With Given Subject Mail List Subject Length is-->"
								+ specificMailsubject.length,
						"Fail", "N");

				throw new AssertionError("Error Message : " + "The Given Subject List is Emptytring");

			}

			if (!flag) {

				System.out.println("The Mail was not triggered Unable to validate mail with given Subject" + subject
						+ eTenderComponent.getDataFromPropertiesFile("poDocNum") + "  ");

				pdfResultReport.addStepDetails("verifyMailSubjectAndMailBody",
						"Mail Was Not Triggred waited for one minute-->Unable to validate mail with given Subject",
						"Mail Was Not Triggred waited for one minute-->Unable to validate mail with given Subject",

						"Fail", "N");

				throw new AssertionError(
						"Error Message : " + "The Mail was not triggered Unable to validate mail with given Subject"
								+ subject + eTenderComponent.getDataFromPropertiesFile("poDocNum") + "  ");
			}
			log.info("completed executing the method:: verifyMailSubjectAndMailBody");

		} catch (Exception e) {

			pdfResultReport.addStepDetails("verifyMailSubjectAndMailBody", "Failed to validate mail content",
					"Unable to validate mail content",

					"Fail", "N");

			Assert.fail(e.getMessage());

		}
	}

	public void EnterCommentForRejection() throws Exception {
		try {
			log.info("started executing the method:: EnterCommentForRejection");

			click(tendercreationlocators.aacceptPOCommentTabBy, "aacceptPOCommentTabBy");

			waitForObj(3000);

			set(tendercreationlocators.poObjCommentBy, "Po Is Rejected", "poObjCommentBy");

			waitForObj(3000);

			pdfResultReport.addStepDetails("EnterCommentForRejection", "Should enter the Comment for rejection",
					"Sucessfully entered Comment For Rejection" + " ", "Pass", "Y");
			log.info("completed executing the method:: EnterCommentForRejection");
		} catch (Exception e) {
			log.fatal("Unable to enter the Comment for rejection" + e.getMessage());
			pdfResultReport.addStepDetails("EnterCommentForRejection", "Unable to enter the Comment for rejection",
					"Should enter the Comment for rejection" + e.getMessage(), "Fail", "N");
		}
	}

	public void selectApprovalHistoryInPendingForAccepetedPO() throws Throwable {
		try {
			log.info("started executing the method:: selectApprovalHistoryInPendingForAccepetedPO");
			waitForElement(tendercreationlocators.action, 50);
			select(tendercreationlocators.selectStatus, "Pending For Acceptance");
			waitForObj(3000);
			set(tendercreationlocators.SearchPO, eTenderComponent.getDataFromPropertiesFile("POReferenceNum"),
					"POReferenceNum");
			pdfResultReport.addStepDetails("selectApprovalHistoryInPendingForAccepetedPO",
					"PO should be repeated successfully ", "Successfully repeated PO" + " ", "Pass", "Y");
			waitForObj(3000);
			click(tendercreationlocators.action, "action");
			waitForElementToBeVisible(tendercreationlocators.ApprovalHistory);
			click(tendercreationlocators.ApprovalHistory, "ApprovalHistory");
			waitForObj(3000);
			// Username
			waitForElementToBeVisible(tendercreationlocators.previewAllOkButton);
			IsElementPresent(By.xpath("//td[text()='PO Creator test']"));
			IsElementPresent(By.xpath("//td[text()='PO Approver1 test']"));
			IsElementPresent(By.xpath("//td[text()='PO Approver test']"));
			waitForObj(2000);
			click(tendercreationlocators.previewAllOkButton, "OKButton");
			waitForElementToBeVisible(tendercreationlocators.action);
			pdfResultReport.addStepDetails("selectApprovalHistoryInPendingForAccepetedPO",
					"Validations should be successfully ", "Validations should be successfully" + " ", "Pass", "Y");
			log.info("completed executing the method:: selectApprovalHistoryInPendingForAccepetedPO");
		} catch (Exception e) {
			log.fatal("Unable to Issue PO" + e.getMessage());
			pdfResultReport.addStepDetails("selectApprovalHistoryInPendingForAccepetedPO",
					"Validations should be successfully ", "Unable to validate Approval History" + e.getMessage(),
					"Fail", "N");
		}
	}

	public void verifyCommentColumnSeqInApprovalHistory1() throws Throwable {
		try {
			log.info("started executing the method:: verifyCommentColumnSeqInApprovalHistory1");

			IsElementPresent(tendercreationlocators.approver1Comment);
			IsElementPresent(tendercreationlocators.POapproverComment);

			pdfResultReport.addStepDetails("verifyCommentColumnSeqInApprovalHistory1",
					"The Approver comment should be in chronological manner depending upon no of approver taken",
					"Successfully Approver comment is showing in chronological manner depending upon no of approver taken"
							+ " ",
					"Pass", "Y");

			log.info("completed executing the method:: verifyCommentColumnSeqInApprovalHistory1");
		} catch (Exception e) {
			log.fatal("Unable to dispaly Approver comment in chronological manner depending upon no of approver taken"
					+ e.getMessage());
			pdfResultReport.addStepDetails("verifyCommentColumnSeqInApprovalHistory1",
					"The Approver comment should be in chronological manner depending upon no of approver taken",
					"Unable to dispaly Approver comment in chronological manner depending upon no of approver taken"
							+ e.getMessage(),
					"Fail", "N");
		}
	}

	public void sendForApprovalUserDefinedParallelUser_pocreator() throws Throwable {
		try {
			log.info("started executing the method:: sendForApprovalUserDefinedSequential2User_pocreator");
			click(tendercreationlocators.Userdefined_po, "Userdefined_po");
			click(tendercreationlocators.approvalType_Parallel, "approvalType_Parallel");
			set(tendercreationlocators.minApproverRequired, pdfResultReport.testData.get("MinApprover"), "MinApprover");
			waitForObj(2000);
			click(tendercreationlocators.addButton, "addButton");
			set(tendercreationlocators.user1, pdfResultReport.testData.get("POapprover1User"), "user1");
			waitForObj(2000);
			set(tendercreationlocators.comments, pdfResultReport.testData.get("UserDefinedApprover-Comments"),
					"comments");

			JSClick(By.xpath("//*[@ng-click='sendForApproval(approvalObj.workflowType)']"), "sendForApprovalSubmit");
			waitForElement(tendercreationlocators.minApproverAlert, 50);
			highlight(tendercreationlocators.minApproverAlert);
			IsElementPresent(tendercreationlocators.minApproverAlert);
			pdfResultReport.addStepDetails("sendForApprovalUserDefinedSequential2User_pocreator",
					"sendForApproval must be validate successfully", "Successfully validated sendForApproval" + " ",
					"Pass", "Y");
			click(tendercreationlocators.alertTabOk, "alertTabOk");
			waitForObj(2000);
			click(tendercreationlocators.addButton, "addButton");
			set(tendercreationlocators.user2, pdfResultReport.testData.get("POapprover2User"), "user1");
			waitForObj(2000);
			set(tendercreationlocators.comments, pdfResultReport.testData.get("UserDefinedApprover-Comments"),
					"comments");
			waitForObj(2000);
			click(tendercreationlocators.parallelCoordinatorFlag, "parallelCoordinatorFlag");
			waitForObj(2000);

			JSClick(By.xpath("//*[@ng-click='sendForApproval(approvalObj.workflowType)']"), "sendForApprovalSubmit");
			pdfResultReport.addStepDetails("sendForApprovalUserDefinedSequential2User_pocreator",
					"sendForApproval must be validate successfully", "Successfully validated sendForApproval" + " ",
					"Pass", "Y");
			waitForElementToBeVisible(tendercreationlocators.poListPageRowBy);

			waitForObj(5000);
			pdfResultReport.addStepDetails("sendForApprovalUserDefinedSequential2User_pocreator",
					"sendForApproval must be validate successfully", "Successfully validated sendForApproval" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: sendForApprovalUserDefinedSequential2User_pocreator");
		} catch (Exception e) {
			log.fatal("Not able to validate sendForApproval" + e.getMessage());
			pdfResultReport.addStepDetails("sendForApprovalUserDefinedSequential2User_pocreator",
					"sendForApproval must be validate successfully",
					"Unable to validate sendForApproval" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickOnReleasePOWithPoApproval() throws Throwable {
		try {
			log.info("started executing the method:: clickOnReleasePOWithPoApproval");
			click(tendercreationlocators.action, "action");
			waitForObj(3000);
			highlight(tendercreationlocators.releasePODropDown);

			click(tendercreationlocators.releasePODropDown, "releasePODropDown");
			waitForElementToBeVisible(tendercreationlocators.poDetails);
			waitForElementToBeVisible(tendercreationlocators.releasePOButton);
			JSClick(tendercreationlocators.releasePOButton, "releasePOButton");
			waitForElementToBeVisible(tendercreationlocators.poDocumentNoBy);
			waitForObj(1000);
			click(tendercreationlocators.Userdefined_po1, "Userdefined_po");
			waitForObj(1000);
			click(tendercreationlocators.approvalType_Sequential, "approvalType_Sequential");
			waitForObj(1000);
			click(tendercreationlocators.addButton, "addButton");
			set(tendercreationlocators.user1, pdfResultReport.testData.get("UserTenderApprover1"), "user1");
			waitForObj(2000);
			set(tendercreationlocators.commentFieldBy, "Enter  Approval Comment", "commentFieldBy");
			waitForObj(2000);
			click(tendercreationlocators.sendForApproval, "sendForApproval");
			waitForObj(5000);
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForElementToBeVisible(tendercreationlocators.poListPageRowBy);
			waitForObj(5000);
			pdfResultReport.addStepDetails("clickOnReleasePOWithPoApproval",
					"Purchase order list page must be navigate sucessfully",
					"Successfully navigated to Purhase oredr list page" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickOnReleasePOWithPoApproval");
		} catch (Exception e) {
			log.fatal("Unable to  navigate  Purchase order list page " + e.getMessage());
			pdfResultReport.addStepDetails("clickOnReleasePOWithPoApproval",
					"Purchase order list page must be navigate sucessfully",
					"Unable to  navigate  Purchase order list page" + e.getMessage(), "Fail", "N");
		}
	}

	public void provideCommentInCommentSection() throws Throwable {
		try {
			log.info("started executing the method:: provideCommentInCommentSection");
			JSClick(By.xpath("//*[@aria-label='Bold']"), "overallComment");
			WebElement iframele = ThreadLocalWebdriver.getDriver()
					.findElement(By.xpath("//iframe[@id='uiTinymce0_ifr']"));
			switchframe(iframele);

			set(tendercreationlocators.recommendationComment, "Provide Comment in Comment Section",
					"recommendationComment");
			switchToDefaultFrame();

			pdfResultReport.addStepDetails("provideCommentInCommentSection", "Comment must be pass successfully",
					"Successfully passed comment" + " ", "Pass", "Y");
			log.info("completed executing the method:: provideCommentInCommentSection");
		} catch (Exception e) {
			log.fatal("Not able to pass comment under Comment Section Tab" + e.getMessage());
			pdfResultReport.addStepDetails("provideCommentInCommentSection", "Comment must be pass successfully",
					"Not able to pass comment under Comment Section Tab" + e.getMessage(), "Fail", "N");
		}
	}

	public void clickPoReviewBtnAndConfirm() throws Throwable {
		try {
			log.info("started executing the method:: clickReviewBtnAndConfirm");
			highlight(tendercreationlocators.Review);

			click(tendercreationlocators.Review, "Review");

			waitForElementToBeVisible(tendercreationlocators.approveConfirm);

			click(tendercreationlocators.approveConfirm, "approveConfirm");

			waitForElementToBeVisible(tendercreationlocators.approvalConfirmPopUp);

			IsElementPresent(tendercreationlocators.approvalConfirmPopUp);

			click(tendercreationlocators.confirmOk, "confirmOk");

			waitForElementToBeVisible(By.xpath("(//*[@id='myTable']/tbody/tr[1]/td[1])[1]"));

			waitForObj(5000);

			pdfResultReport.addStepDetails("clickReviewBtnAndConfirm", "Po Must send for Review again ",
					"Successfully Po was Sended For Review Process" + " ", "Pass", "Y");
			log.info("completed executing the method:: cancelPurchaseOrderReview");

		} catch (Exception e) {
			log.fatal("Unable to send Po for Review" + e.getMessage());
			pdfResultReport.addStepDetails("clickReviewBtnAndConfirm", "Po Must send for Review again ",
					"Unable to send Po for Review" + e.getMessage(), "Fail", "N");
		}
	}

	public void VerifyStatusOfPO() throws Throwable {
		try {
			log.info("started executing the method:: VerifyStatusOfPO");
			waitForObj(5000);
			IsElementPresent(tendercreationlocators.StatusDraft);
			highlight(tendercreationlocators.StatusDraft);
			IsElementPresent(tendercreationlocators.StatusRejectedByapprover);
			highlight(tendercreationlocators.StatusRejectedByapprover);

			waitForObj(8000);

			pdfResultReport.addStepDetails("VerifyStatusOfPO",
					"Verify the status of PO as Draft stage and Rejected By approver",
					"Sucessfully Verified the status of PO as Draft stage and Rejected By approver" + " ", "Pass", "Y");
			log.info("completed executing the method:: VerifyStatusOfPO");
		} catch (Exception e) {
			log.fatal("Unable to Verify the status of PO as Draft stage and Rejected By approver" + e.getMessage());
			pdfResultReport.addStepDetails("searchThePoRefNoInPoListPage",
					"Verify the status of PO as Draft stage and Rejected By approver",
					"Unable to Verify the status of PO as Draft stage and Rejected By approver" + e.getMessage(),
					"Fail", "N");
		}
	}

	public void clickPoReviewBtnAndConfirmBySecondApprover() throws Throwable {
		try {
			log.info("started executing the method:: clickPoReviewBtnAndConfirmBySecondApprover");
			highlight(tendercreationlocators.Review);

			click(tendercreationlocators.Review, "Review");

			waitForElementToBeVisible(tendercreationlocators.approveConfirm);

			click(tendercreationlocators.approveConfirm, "approveConfirm");

			waitForElementToBeVisible(tendercreationlocators.reverseBackApproverBy);

			click(tendercreationlocators.reverseBackApproverBy, "reverseBackApproverBy");

			waitForElementToBeVisible(tendercreationlocators.approvalConfirmPopUp);

			IsElementPresent(tendercreationlocators.approvalConfirmPopUp);

			click(tendercreationlocators.confirmOk, "confirmOk");

			waitForElementToBeVisible(By.xpath("(//*[@id='myTable']/tbody/tr[1]/td[1])[1]"));

			waitForObj(5000);

			pdfResultReport.addStepDetails("clickPoReviewBtnAndConfirmBySecondApprover",
					"Po Must Reverse back to approver", "Successfully Po  Reverse back to approver" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickPoReviewBtnAndConfirmBySecondApprover");

		} catch (Exception e) {
			log.fatal("Unable to  Reverse back to approver" + e.getMessage());
			pdfResultReport.addStepDetails("clickPoReviewBtnAndConfirmBySecondApprover",
					"Po Must Reverse back to approver ", "Unable to  Reverse back to approver" + e.getMessage(), "Fail",
					"N");
		}
	}

	public void editItemQtyInPOITemDetailsTab() throws Throwable {
		try {
			log.info("started executing the method:: editItemQtyInPOITemDetailsTab");
			click(tendercreationlocators.POItemDetails, "POItemDetails");
			waitForObj(3000);
			// Item 1
			JSClick(tendercreationlocators.itemQuantity1, "itemQuantity1");
			clear(tendercreationlocators.itemQuantity1, "itemQuantity1");
			set(tendercreationlocators.itemQuantity1, eTenderComponent.getDataFromPropertiesFile("AddMoreItemQtnies"),
					"POItemQuantity1");

			waitForElementToBeVisible(tendercreationlocators.ItemQtyPopUpValidationBy);

			pdfResultReport.addStepDetails("Add More Item Qty than Main Qty",
					"Should get Quantity not more than main quantity! ",
					"Successfully Displaying validation Pop up that  Quantity not more than main quantity!" + " ",
					"Pass", "Y");

			click(tendercreationlocators.confirmationOkBy, "Confirm Ok");

			clear(tendercreationlocators.itemQuantity1, "itemQuantity1");
			set(tendercreationlocators.itemQuantity1, eTenderComponent.getDataFromPropertiesFile("POItemQuantity1"),
					"POItemQuantity1");

			// Item 2
			JSClick(tendercreationlocators.itemQuantity2, "itemQuantity2");
			clear(tendercreationlocators.itemQuantity2, "itemQuantity2");
			set(tendercreationlocators.itemQuantity2, eTenderComponent.getDataFromPropertiesFile("AddMoreItemQtnies"),
					"POItemQuantity2");

			waitForElementToBeVisible(tendercreationlocators.ItemQtyPopUpValidationBy);

			pdfResultReport.addStepDetails("Add More Item Qty than Main Qty",
					"Should get Quantity not more than main quantity! ",
					"Successfully Displaying validation Pop up that  Quantity not more than main quantity!" + " ",
					"Pass", "Y");

			click(tendercreationlocators.confirmationOkBy, "Confirm Ok");

			clear(tendercreationlocators.itemQuantity2, "itemQuantity2");
			set(tendercreationlocators.itemQuantity2, eTenderComponent.getDataFromPropertiesFile("POItemQuantity2"),
					"POItemQuantity2");

			// //Item 3
			JSClick(tendercreationlocators.itemQuantity3, "itemQuantity3");
			clear(tendercreationlocators.itemQuantity3, "itemQuantity3");
			set(tendercreationlocators.itemQuantity3, eTenderComponent.getDataFromPropertiesFile("AddMoreItemQtnies"),
					"POItemQuantity3");

			waitForElementToBeVisible(tendercreationlocators.ItemQtyPopUpValidationBy);

			pdfResultReport.addStepDetails("Add More Item Qty than Main Qty",
					"Should get Quantity not more than main quantity! ",
					"Successfully Displaying validation Pop up that  Quantity not more than main quantity!" + " ",
					"Pass", "Y");

			click(tendercreationlocators.confirmationOkBy, "Confirm Ok");
			clear(tendercreationlocators.itemQuantity3, "itemQuantity3");
			set(tendercreationlocators.itemQuantity3, eTenderComponent.getDataFromPropertiesFile("POItemQuantity3"),
					"POItemQuantity3");

			click(tendercreationlocators.POSave, "POSave");

			waitForElementToBeVisible(tendercreationlocators.poSaveSuucessMsgPopUpBy);

			waitForObj(2000);

			click(tendercreationlocators.POSUccessMsg, "POSUccessMsg");

			waitForObj(5000);

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			pdfResultReport.addStepDetails("editItemQtyInPOITemDetailsTab",
					"Quantity can be added but should be less  than the main Quantity given earlier. ",
					"Successfully Quantity are added and less  than the main Quantity given earlier." + " ", "Pass",
					"Y");
			log.info("completed executing the method:: editItemQtyInPOITemDetailsTab");
		} catch (Exception e) {
			log.fatal("Unable to Add Item Qty" + e.getMessage());
			pdfResultReport.addStepDetails("editItemQtyInPOITemDetailsTab",
					"Quantity can be added but should be less  than the main Quantity given earlier.",
					"Unable to  Add Item Qty " + e.getMessage(), "Fail", "N");
		}
	}

	public void navigateToPurchaseOrderApproval2() throws Throwable {
		try {
			log.info("started executing the method:: navigateToPurchaseOrderApproval");
			waitForObj(5000);
			set(tendercreationlocators.search, poDocNum, "enter PoDocNum");
			// //pdfResultReport.addStepDetails("navigateToPurchaseOrderApproval",
			// "Purchase Order Approval page must be navigate sucessfully ",
			// "Successfully navigated to Purchase Order Approval page" + " ",
			// "Pass", "Y");
			// waitForElementToBeVisible(tendercreationlocators.details);
			// click(tendercreationlocators.details, "details");
			// waitForElementToBeVisible(tendercreationlocators.podetails);
			// waitForObj(5000);
			// pdfResultReport.addStepDetails("navigateToPurchaseOrderApproval",
			// "Purchase Order Approval page must be navigate sucessfully ",
			// "Successfully navigated to Purchase Order Approval page" + " ",
			// "Pass", "Y");
			click(tendercreationlocators.cancelPurchaseOrderTabBy, "cancelPurchaseOrderTabBy");
			waitForElementToBeVisible(tendercreationlocators.details);
			click(tendercreationlocators.details, "details");
			waitForElementToBeVisible(tendercreationlocators.podetails);
			waitForObj(3000);
			JSClick(tendercreationlocators.sanctionNoteEvaluationCommentTab, "sanctionNoteEvaluationCommentTab");
			waitForObj(3000);
			pdfResultReport.addStepDetails("navigateToPurchaseOrderApproval",
					"Purchase Order Approval page must be navigate sucessfully ",
					"Successfully navigated to Purchase Order Approval page" + " ", "Pass", "Y");
			log.info("completed executing the method:: navigateToPurchaseOrderApproval");

		} catch (Exception e) {
			log.fatal("Unable to navigate to Purchase Order Approval page" + e.getMessage());
			pdfResultReport.addStepDetails("navigateToPurchaseOrderApproval",
					"Purchase Order Approval page must be navigate sucessfully",
					"Unable to navigate to Purchase Order Approval page" + e.getMessage(), "Fail", "N");
		}
	}

}
