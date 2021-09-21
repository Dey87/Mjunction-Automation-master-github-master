package com.components;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import javax.mail.Message;
import javax.mail.MessagingException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

import com.baseClasses.BaseClass_Web;
import com.baseClasses.EmailUtils;
import com.baseClasses.PDFResultReport;
import com.baseClasses.ThreadLocalWebdriver;
import com.objectRepository.TenderCreation_Locators;

public class InvoiceComponent extends BaseClass_Web {

	TenderCreation_Locators tendercreationlocators = new TenderCreation_Locators();

	eTenderComponent etendercomponentobj = new eTenderComponent(pdfResultReport);
	ASN_GRNComponent asn_grncomponentobj =new ASN_GRNComponent(pdfResultReport);
	private static String poHeaderBy = "//div[@class='panel-heading']//a[contains(normalize-space(text()),'PO Number : {0}')]";

	public EmailUtils emailyUtils = new EmailUtils();
	int itemCode1IndexValue=0, itemCode2IndexValue=0, itemCode3IndexValue=0, lowestIndex;
	float lowestItemValue;
	String lowestItemValueTxt=null;
	public InvoiceComponent(PDFResultReport pdfresultReport) {

		this.pdfResultReport = pdfresultReport;
	}

	

	public void navigateToInVoiceProcessingListPage() throws Throwable {
		try {
			log.info("started executing the method:: navigateToInVoiceProcessingListPage");

			JSClick(tendercreationlocators.InVoiceMenuLinkBy, "InVoiceMenuLinkBy");

			waitForObj(2000);

			JSClick(tendercreationlocators.InVoiceProcessingLinkBy, "InVoiceProcessingLinkBy");

			waitForElementToBeVisible(tendercreationlocators.invoiceNestedTableRowsBy);

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			waitForObj(5000);

			pdfResultReport.addStepDetails("navigateToInVoiceProcessingListPage",
					"Should navigate to In Voice Processing List Page",
					"Successfully navigated to InVoice Processing List Page" + " ", "Pass", "Y");
			log.info("completed executing the method:: navigateToInVoiceProcessingListPage");
		} catch (Exception e) {
			log.fatal("Unable to navigate to In Voice Processing List Page" + e.getMessage());
			pdfResultReport.addStepDetails("navigateToInVoiceProcessingListPage",
					"Should navigate to In Voice Processing List Page",
					"Unable to navigate to In Voice Processing List Page" + e.getMessage(), "Fail", "N");

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

	public void enterInVoiceOrPoNoSerachBox(String PoOrInVoiceNum) throws Throwable {
		try {
			log.info("started executing the method:: enterInVoiceOrPoNoSerachBox");

			clear(tendercreationlocators.serchInvoiceOrPoNumBy, "clear the search field");

			set(tendercreationlocators.serchInvoiceOrPoNumBy, PoOrInVoiceNum, "PoOrInVoiceNum");

			waitForObj(5000);

			pdfResultReport.addStepDetails("enterInVoiceOrPoNoSerachBox", "Should enter Po No In search box",
					"Successfully   entered Po No In search box" + " ", "Pass", "Y");

			log.info("completed executing the method:: enterShippementTrackingNoInGrnListSerachBox");
		} catch (Exception e) {
			log.fatal("Unable to  enter Po No In search box" + e.getMessage());
			pdfResultReport.addStepDetails("enterShippementTrackingNoInGrnListSerachBox",
					"Should enter Po No In search box", "Unable to  enter Po No In search box" + e.getMessage(), "Fail",
					"N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void isPoNumberWithHeaderDispalying(String PoNum) throws Throwable {
		try {
			log.info("started executing the method:: isPoNumberWithHeaderDispalying");

			WebDriver driver = ThreadLocalWebdriver.getDriver();

			Assert.assertTrue(driver.findElement(By.xpath(poHeaderBy.replace("{0}", PoNum))).isDisplayed(),
					"Po Number With Header is Displaying");
			;
			waitForObj(5000);

			pdfResultReport.addStepDetails("isPoNumberWithHeaderDispalying", "Should Display Po Number In Header panel",
					"Successfully Displaying  Po Number in Header" + " ", "Pass", "Y");
			log.info("completed executing the method:: isPoNumberWithHeaderDispalying");
		} catch (Exception e) {
			log.fatal("Unable to   Display Po Number in Header" + e.getMessage());
			pdfResultReport.addStepDetails("isPoNumberWithHeaderDispalying", "Should Display Po Number In Header panel",
					"Unable to   Display Po Number in Header" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}


	public void clickOnGrnHeaderBar(String shippingTrackingNumber) throws Throwable {
		try {
			log.info("started executing the method:: clickOnGrnHeaderBar");

			click(tendercreationlocators.GRNHeaderBy, "InvoiceSellerHeaderBy");

			waitForElementToBeVisible(tendercreationlocators.GRNHeaderMinusIconBy);

			IsElementPresent(tendercreationlocators.shippingTrackingData(shippingTrackingNumber));

			waitForObj(2000);

			pdfResultReport.addStepDetails("clickOnGrnHeaderBar", "Should display GRN Data",
					"Successfully displaying GRN Data" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickOnGrnHeaderBar");
		} catch (Exception e) {
			log.fatal("Unable to  display GRN Header Bar" + e.getMessage());
			pdfResultReport.addStepDetails("clickOnGrnHeaderBar", "Should display GRN Data",
					"Unable to   display GRN Data" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}
	
	public void searchPONoInOtherInvoiceListPage() throws Throwable {
		try {
			log.info("started executing the method:: searchPONoInOtherInvoiceListPage");
			clear(tendercreationlocators.enterPONumber, "Clear the PO Number");
			set(tendercreationlocators.enterPONumber, eTenderComponent.getDataFromPropertiesFile("poDocNum"),
					"search The PO Doc Number");
			pdfResultReport.addStepDetails("searchPONoInOtherInvoiceListPage", "PO Number must be display sucessfully",
					"Sucessfully displayed PO Number" + " ", "Pass", "Y");
			log.info("completed executing the method:: searchPONoInOtherInvoiceListPage");
		} catch (Exception e) {
			log.fatal("Unable to display PO Number" + e.getMessage());
			pdfResultReport.addStepDetails("searchPONoInOtherInvoiceListPage", "PO Number must be display sucessfully",
					"Unable to display PO Number" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}
	public void clickOnPONumberHeaderPlusIcon() throws Throwable {
		try {
			log.info("started executing the method:: clickOnPONumberHeaderPlusIcon");
			click(tendercreationlocators.poNumberplusIcon, "poNumberplusIcon");
			waitForElementToBeVisible(tendercreationlocators.view_GRN);
			click(tendercreationlocators.view_GRN, "view_GRN");
			waitForElementToBeVisible(tendercreationlocators.GRNId);
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			click(tendercreationlocators.GRNId, "GRNId");
			waitForObj(3000);
			pdfResultReport.addStepDetails("clickOnPONumberHeaderPlusIcon", "PO details and GRN details must be validate sucessfully",
					"Successfully validated PO details and GRN details" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickOnPONumberHeaderPlusIcon");
		} catch (Exception e) {
			log.fatal("Unable to validate PO details and GRN details" + e.getMessage());
			pdfResultReport.addStepDetails("clickOnPONumberHeaderPlusIcon", "PO details and GRN details must be validate sucessfully",
					"Unable to validate PO details and GRN details" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void clickOnSubmitInRaiseInvoicePage() throws Throwable {
		try {
			log.info("started executing the method:: clickOnSubmitInRaiseInvoicePage");
			click(tendercreationlocators.submitbutton, "submitbutton");
			waitForElementToBeVisible(tendercreationlocators.enterPONumber);
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			pdfResultReport.addStepDetails("clickOnSubmitInRaiseInvoicePage", "Should navigate to Other Invoice List Page",
					"Successfully navigated to Other Invoice List Page" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickOnSubmitInRaiseInvoicePage");
		} catch (Exception e) {
			log.fatal("Unable to navigate to Other Invoice List Page" + e.getMessage());
			pdfResultReport.addStepDetails("clickOnSubmitInRaiseInvoicePage", "Should navigate to Other Invoice List Page",
					"Unable to navigate to Other Invoice List Page" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}
	
	
	
	public void InVoice_Payer_Login() throws Throwable {
		try {
			log.info("started executing the method:: InVoice_Payer_Login");
			click(tendercreationlocators.login, "login");
			waitForElementToBeVisible(tendercreationlocators.userName);
			waitForObj(2000);

			set(tendercreationlocators.userName, pdfResultReport.testData.get("Invoice_payer_UserName"), "userName");
			set(tendercreationlocators.password, pdfResultReport.testData.get("AppPassword"), "password");
			click(tendercreationlocators.okButton, "okButton");

			waitForElement(tendercreationlocators.dashboardIcon, 5000);

			waitForObj(5000);
			pdfResultReport.addStepDetails("InVoice_Payer_Login", "InVoice Dashboard Page will be displayed",
					"Successfully InVoice Dashboard Page is displaying" + " ", "Pass", "Y");
			log.info("completed executing the method:: InVoice_Payer_Login");
		} catch (Exception e) {
			log.fatal("Unable to display InVoice Dashboard Page" + e.getMessage());
			pdfResultReport.addStepDetails("InVoice_Payer_Login", "InVoice Dashboard Page will be displayed",
					"Unable to display InVoice Dashboard Page" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}
	public void NavigateTOApprovedInvoicePage() throws Throwable {
		try {
			log.info("started executing the method:: NavigateTOApprovedInvoicePage");
			JSClick(tendercreationlocators.btnPayment, "btnPayment");
			
			JSClick(tendercreationlocators.btnInvoiceListPayment, "btnInvoiceListPayment");
			waitForObj(5000);
			etendercomponentobj.waitForSpinnerToDisappear();
			
			waitForElement(tendercreationlocators.HomeInvoicepage, 5000);

			waitForObj(5000);
			pdfResultReport.addStepDetails("NavigateTOApprovedInvoicePage", "InVoice Home Page will be displayed",
					"Successfully InVoice Home Page is displaying" + " ", "Pass", "Y");
			log.info("completed executing the method:: NavigateTOApprovedInvoicePage");
		} catch (Exception e) {
			log.fatal("Unable to display InVoice Home Page" + e.getMessage());
			pdfResultReport.addStepDetails("NavigateTOApprovedInvoicePage", "InVoice Home Page will be displayed",
					"Unable to display InVoice Home Page" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}
	
	
	public void VerifyASNinInvoicePage() throws Throwable {
		try {
			log.info("started executing the method:: VerifyASNinInvoicePage");
			JSClick(tendercreationlocators.invoiceActiondrpdwn(eTenderComponent.getDataFromPropertiesFile("InVoice_Number")), "invoiceActiondrpdwn");
			waitForObj(2000);
			JSClick(tendercreationlocators.ViewInvoiceASN(eTenderComponent.getDataFromPropertiesFile("InVoice_Number")), "ViewInvoiceASN");
			
			waitForObj(3000);
			etendercomponentobj.waitForSpinnerToDisappear();
			waitForObj(3000);
			asn_grncomponentobj.VerifyDetailsInShipmetDetailsPage();
			waitForObj(3000);
			pdfResultReport.addStepDetails("VerifyASNinInvoicePage", "ASN Details will be displayed",
					"Successfully displayed ASN Details" + " ", "Pass", "Y");
			log.info("completed executing the method:: VerifyASNinInvoicePage");
		} catch (Exception e) {
			log.fatal("Unable to display ASN Details" + e.getMessage());
			pdfResultReport.addStepDetails("VerifyASNinInvoicePage", "ASN Details will be displayed",
					"Unable to display ASN Details" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}
	public void VerifyGRNinInvoicePage() throws Throwable {
		try {
			log.info("started executing the method:: VerifyGRNinInvoicePage");
			JSClick(tendercreationlocators.invoiceActiondrpdwn(eTenderComponent.getDataFromPropertiesFile("InVoice_Number")), "invoiceActiondrpdwn");
			waitForObj(2000);
			JSClick(tendercreationlocators.ViewInvoiceGRN(eTenderComponent.getDataFromPropertiesFile("InVoice_Number")), "ViewInvoiceGRN");
			
			waitForObj(3000);
			etendercomponentobj.waitForSpinnerToDisappear();
			waitForObj(3000);
			
			VerifyMaterialsDetailsInvoice();
			pdfResultReport.addStepDetails("VerifyGRNinInvoicePage", "GRN Details will be displayed",
					"Successfully displayed GRN Details" + " ", "Pass", "Y");
			log.info("completed executing the method:: VerifyGRNinInvoicePage");
		} catch (Exception e) {
			log.fatal("Unable to display GRN Details" + e.getMessage());
			pdfResultReport.addStepDetails("VerifyGRNinInvoicePage", "GRN Details will be displayed",
					"Unable to display GRN Details" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}
		
public void clickOnInVoiceApproveBtn(String sellerInvoiceNo) throws Throwable {
		try {
			log.info("started executing the method:: clickOnInVoiceApproveBtn");
			
			highlight(tendercreationlocators.SellerInvoiceapprove(sellerInvoiceNo));
			
			pdfResultReport.addStepDetails("clickOnInVoiceApproveBtn",
					"Approve Inovice button must be display sucessfully;",
					"Successfully displayed Approve Inovice button" + " ", "Pass", "Y");
			
			JSClick(tendercreationlocators.SellerInvoiceapprove(sellerInvoiceNo), "sellerInvoiceApproveBy");

			waitForElementToBeVisible(tendercreationlocators.poValueNumberBy);

			waitForObj(3000);

			pdfResultReport.addStepDetails("clickOnInVoiceApproveBtn", "Should navigate to InVoice Processing Page",
					"Successfully navigated to InVoice Processing Page" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickOnInVoiceApproveBtn");
		} catch (Exception e) {
			log.fatal("Unable to navigate to InVoice Processing Page" + e.getMessage());
			pdfResultReport.addStepDetails("clickOnInVoiceApproveBtn", "Should navigate to InVoice Processing Page",
					"Unable to navigate to InVoice Processing Page" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}


	public void enterApprovedAmount(String approveAmountvalue) throws Throwable {
		try {
			log.info("started executing the method:: enterApprovedAmount");
			
			clear(tendercreationlocators.approvedAmountBy, "clear value");
			
			set(tendercreationlocators.approvedAmountBy, approveAmountvalue, "approveAmountvalue");
			
			waitForObj(3000);
			
			pdfResultReport.addStepDetails("enterApprovedAmount", "Should enter Approved Amount Value",
					"Successfully entered Approved Amount Valuee" + " ", "Pass", "Y");
			log.info("completed executing the method:: enterApprovedAmount");
		} catch (Exception e) {
			log.fatal("Unable to  enter Approved Amount Value" + e.getMessage());
			pdfResultReport.addStepDetails("enterApprovedAmount", "Should enter Approved Amount Value",
					"Unable to  enter Approved Amount Value" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}
	
	
	public void enterpenaltyDeductionAmount(String penaltyDeductionAmountvalue) throws Throwable {
		try {
			log.info("started executing the method:: enterpenaltyDeductionAmount");
			
			clear(tendercreationlocators.penaltyDeductionAmountBy, "clear value");
			
			set(tendercreationlocators.penaltyDeductionAmountBy, penaltyDeductionAmountvalue, "penaltyDeductionAmountvalue");
			
			waitForObj(3000);
			
			pdfResultReport.addStepDetails("enterpenaltyDeductionAmount", "Should enter penaltyDeduction Amount Value",
					"Successfully entered  penaltyDeduction Amount Value" + " ", "Pass", "Y");
			log.info("completed executing the method:: enterpenaltyDeductionAmount");
		} catch (Exception e) {
			log.fatal("Unable to  enter  penaltyDeduction Amount Value" + e.getMessage());
			pdfResultReport.addStepDetails("enterpenaltyDeductionAmount", "Should enter penaltyDeduction Amount Value",
					"Unable to  enter  penaltyDeduction Amount Value" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}
	
	public void clickInVoiceAcceptBtn() throws Throwable {
		try {
			log.info("started executing the method:: clickInVoiceAcceptBtn");
			
			JSClick(tendercreationlocators.inVoiceAcceptBy, "inVoiceAcceptBy");
			
			waitForObj(3000);
			
			pdfResultReport.addStepDetails("clickInVoiceAcceptBtn", "Should click Invoice Accept Btn",
					"Successfully clicked Invoice Accept Btn" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickInVoiceAcceptBtn");
		} catch (Exception e) {
			log.fatal("Unable to  click Invoice Accept Btn" + e.getMessage());
			pdfResultReport.addStepDetails("clickInVoiceAcceptBtn", "Should click Invoice Accept Btn",
					"Unable to  click Invoice Accept Btn" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}
	
	public void isInVoiceProcessingListDisplayed() throws Throwable {
		try {
			log.info("started executing the method:: isInVoiceProcessingListDisplayed");
			
			waitForElementToBeVisible(tendercreationlocators.invoiceNestedTableRowsBy);
			
			IsElementPresent(tendercreationlocators.invoiceNestedTableRowsBy);
			
			waitForObj(3000);
			
			pdfResultReport.addStepDetails("isInVoiceProcessingListDisplayed", "Should display InVoice Processing List Page",
					"Successfully displaying InVoice Processing List Page" + " ", "Pass", "Y");
			log.info("completed executing the method:: isInVoiceProcessingListDisplayed");
		} catch (Exception e) {
			log.fatal("Unable to display InVoice Processing List Page" + e.getMessage());
			pdfResultReport.addStepDetails("isInVoiceProcessingListDisplayed", "Should display InVoice Processing List Page",
					"Unable to   display InVoice Processing List Page"+ e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}
	

	public void validateApproved_PenaltyAmountCantMoreThanTotalAmount(String errorMsgValidation) throws Throwable {
		try {
			log.info("started executing the method:: validateApproved_PenaltyAmountCantMoreThanTotalAmount");
			
			//Approved Amount and Penalty Deduction Amount can't be more than total amount.
			
			String invoiceValidation = text(tendercreationlocators.inVoiceErrorValidationBy);
			
			Assert.assertTrue(invoiceValidation.trim().contains(errorMsgValidation.trim()), "Approved Amount and Penalty Deduction Amount can't be more than total amount.");
			
			waitForObj(3000);
			
			pdfResultReport.addStepDetails("validateApproved_PenaltyAmountCantMoreThanTotalAmount", "Approved Amount and Penalty Deduction Amount can't be more than total amount.",
					"Successfully validated ErrorMsg As expected  "+errorMsgValidation + " ", "Pass", "Y");
			log.info("completed executing the method:: validateApproved_PenaltyAmountCantMoreThanTotalAmount");
		} catch (Exception e) {
			log.fatal("Unable to  validate ErrorMsg" + e.getMessage());
			pdfResultReport.addStepDetails("validateApproved_PenaltyAmountCantMoreThanTotalAmount", "Approved Amount and Penalty Deduction Amount can't be more than total amount. ",
					"Unable to  validate ErrorMsg "+ e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}
	
	public void clickinVoiceErrorMsgOkBtn() throws Throwable {
		try {
			log.info("started executing the method:: clickinVoiceErrorMsgOkBtn");
			
			click(tendercreationlocators.inVoiceErrorMsgOkBy, "inVoiceErrorMsgOkBy");
			
			waitForObj(3000);
			
			pdfResultReport.addStepDetails("clickinVoiceErrorMsgOkBtn", "Should Click InvoceErrorMsg OK Btn",
					"Successfully cliked on ErrorMSg  Ok Btn"+ " ", "Pass", "Y");
			log.info("completed executing the method:: clickinVoiceErrorMsgOkBtn");
		} catch (Exception e) {
			log.fatal("Unable to click ErrorMsg OK Btn" + e.getMessage());
			pdfResultReport.addStepDetails("clickinVoiceErrorMsgOkBtn", "Should Click InvoceErrorMsg OK Btn",
					"Unable to click ErrorMsg OK Btn "+ e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}
	
	public void saveInVoiceNumber() throws Throwable {
		try {
			log.info("started executing the method:: saveInVoiceNumber");
			
			String invoiceheaderText = text(tendercreationlocators.inVoiceHeaderBarBy);
			
			String invoiceNo = invoiceheaderText.substring(invoiceheaderText.lastIndexOf(':')+1,invoiceheaderText.lastIndexOf('/')).trim();
			
			eTenderComponent.updateDataIntoPropertyFile("InVoice_Number", invoiceNo);
			
			
			pdfResultReport.addStepDetails("saveInVoiceNumber", "Should save InVoice Number",
					"Successfully saved Invoice Number"+ " ", "Pass", "Y");
			log.info("completed executing the method:: saveInVoiceNumber");
		} catch (Exception e) {
			log.fatal("Unable to save InVoice Number" + e.getMessage());
			pdfResultReport.addStepDetails("saveInVoiceNumber", "Should save InVoice Number",
					"Unable to  save InVoice Number "+ e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}
	
	
	
	public void clickOnViewGrnIcon() throws Throwable {
		try {
			log.info("started executing the method:: clickOnViewGrnIcon");
			
			JSClick(tendercreationlocators.viewGrnBy, "viewGrnBy");
			
			waitForElementToBeVisible(tendercreationlocators.grnIDBy);

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			waitForObj(3000);
		
			pdfResultReport.addStepDetails("clickOnViewGrnIcon", "Should dispaly preview Grn data",
					"Successfully dispalyed preview Grn data"+ " ", "Pass", "Y");
			log.info("completed executing the method:: clickOnViewGrnIcon");
		} catch (Exception e) {
			log.fatal("Unable to  dispaly preview Grn data" + e.getMessage());
			pdfResultReport.addStepDetails("clickOnViewGrnIcon", "Should dispaly preview Grn data",
					"Unable to  dispaly preview Grn data "+ e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}
	
	
	
	public void closePreviewwGrnDetailsPopUp() throws Throwable {
		try {
			log.info("started executing the method:: closePreviewwGrnDetailsPopUp");

			click(tendercreationlocators.GrnPreviewCloseBtn, "GrnPreviewCloseBtn");

			waitForObj(3000);

			pdfResultReport.addStepDetails("closePreviewwGrnDetailsPopUp", "Should close GRN Preview Pop Up",
					"Successfully closed  GRN Preview Pop Up" + " ", "Pass", "Y");
			log.info("completed executing the method:: closePreviewwGrnDetailsPopUp");
		} catch (Exception e) {
			log.fatal("Unable to  Display popup With GRN Preview Details" + e.getMessage());
			pdfResultReport.addStepDetails("closePreviewwGrnDetailsPopUp", "Should close  GRN Preview Pop Up",
					"Unable to close  GRN Preview Pop Up" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}


public void inVoice_Approver_Login() throws Throwable {
		try {
			log.info("started executing the method:: inVoice_Approver_Login");
			click(tendercreationlocators.login, "login");
			waitForElementToBeVisible(tendercreationlocators.userName);
			waitForObj(2000);

			set(tendercreationlocators.userName, pdfResultReport.testData.get("InVoice_Approver_UserName"), "userName");
			set(tendercreationlocators.password, pdfResultReport.testData.get("AppPassword"), "password");
			click(tendercreationlocators.okButton, "okButton");

			waitForElement(tendercreationlocators.dashboardIcon, 5000);

			waitForObj(5000);
			pdfResultReport.addStepDetails("inVoice_Approver_Login", "InVoice Dashboard Page will be displayed",
					"Successfully InVoice Dashboard Page is displaying" + " ", "Pass", "Y");
			log.info("completed executing the method:: inVoice_Approver_Login");
		} catch (Exception e) {
			log.fatal("Unable to display InVoice Dashboard Page" + e.getMessage());
			pdfResultReport.addStepDetails("inVoice_Approver_Login", "InVoice Dashboard Page will be displayed",
					"Unable to display InVoice Dashboard Page" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

public void enterSellerInVoiceNo(String sellerVoiceNum) throws Throwable {
		try {
			log.info("started executing the method:: enterSellerInVoiceNo");

			clear(tendercreationlocators.serchInvoiceOrPoNumBy, "clear the search field");

			set(tendercreationlocators.serchInvoiceOrPoNumBy, sellerVoiceNum, "sellerVoiceNum");

			waitForObj(5000);

			pdfResultReport.addStepDetails("enterSellerInVoiceNo", "Should enter sellerVoice No In search box",
					"Successfully  entered sellerVoice No In search box" + " ", "Pass", "Y");

			log.info("completed executing the method:: enterSellerInVoiceNo");
		} catch (Exception e) {
			log.fatal("Unable to  enter sellerVoice No In search box" + e.getMessage());
			pdfResultReport.addStepDetails("enterSellerInVoiceNo",
					"Should enter sellerVoice No In search box", "Unable to  enter sellerVoice No In search box" + e.getMessage(), "Fail",
					"N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

public void clickOnPoNumberHeaderBar(String PoNum) throws Throwable {
		try {
			log.info("started executing the method:: clickOnPoNumberHeader");

			WebDriver driver = ThreadLocalWebdriver.getDriver();

			driver.findElement(By.xpath(poHeaderBy.replace("{0}", PoNum))).click();

			waitForElementToBeVisible(tendercreationlocators.InvoiceSellerHeaderBy);
			
			highlight(tendercreationlocators.InvoiceSellerHeaderBy);

			waitForObj(2000);

			pdfResultReport.addStepDetails("clickOnPoNumberHeader", "Should display Invoice Header Bar",
					"Successfully displaying Invoice Header Bar" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickOnPoNumberHeader");
		} catch (Exception e) {
			log.fatal("Unable to  display Invoice Header Bar" + e.getMessage());
			pdfResultReport.addStepDetails("clickOnPoNumberHeader", "Should display Invoice Header Bar",
					"Unable to  display Invoice Header Bar" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void clickOnInVoiceNumberHeaderBar() throws Throwable {
		try {
			log.info("started executing the method:: clickOnInVoiceNumberHeaderBar");

			click(tendercreationlocators.InvoiceSellerHeaderBy, "InvoiceSellerHeaderBy");

			waitForElementToBeVisible(tendercreationlocators.GRNHeaderBy);

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			
			highlight(tendercreationlocators.GRNHeaderBy);
			
			waitForObj(2000);

			pdfResultReport.addStepDetails("clickOnInVoiceNumberHeaderBar", "Should display GRN Header Bar",
					"Successfully displaying GRN Header Bar" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickOnInVoiceNumberHeaderBar");
		} catch (Exception e) {
			log.fatal("Unable to  display GRN Header Bar" + e.getMessage());
			pdfResultReport.addStepDetails("clickOnInVoiceNumberHeaderBar", "Should display GRN Header Bar",
					"Unable to  display GRN Header Bar" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void clickOnGRListToRaiseInvoice() throws Throwable {
		try {
			log.info("started executing the method:: clickOnGRListToRaiseInvoice");
			JSClick(tendercreationlocators.Invoice, "Invoice");
			waitForObj(2000);
			JSClick(tendercreationlocators.GRListToRaiseInvoice, "GRListToRaiseInvoice");
			waitForElementToBeVisible(tendercreationlocators.enterPONumber);
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			pdfResultReport.addStepDetails("clickOnGRListToRaiseInvoice", "Should navigate to Other Invoice List Page",
					"Successfully navigated to Other Invoice List Page" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickOnGRListToRaiseInvoice");
		} catch (Exception e) {
			log.fatal("Unable to navigate to Other Invoice List Page" + e.getMessage());
			pdfResultReport.addStepDetails("clickOnGRListToRaiseInvoice", "Should navigate to Other Invoice List Page",
					"Unable to navigate to Other Invoice List Page" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}
	public void clickOnPONumberHeaderPlusIcon(String AsnShipmentNo) throws Throwable {
		try {
			log.info("started executing the method:: clickOnPONumberHeaderPlusIcon");
			click(tendercreationlocators.poNumberplusIcon, "poNumberplusIcon");
			waitForElementToBeVisible(tendercreationlocators.view_GRN);
			click(tendercreationlocators.view_GRN, "view_GRN");
			waitForElementToBeVisible(tendercreationlocators.GRNId);
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			click(tendercreationlocators.GRNId, "GRNId");
			waitForObj(3000);
			WebDriver driver = ThreadLocalWebdriver.getDriver();
			String shipmentNumber = "//*[contains(text(),'{0}')]";
			boolean displayed = driver.findElement(By.xpath(shipmentNumber.replace("{0}", AsnShipmentNo))).isDisplayed();
			Assert.assertTrue(displayed, "Shipment Number is displayed");
			pdfResultReport.addStepDetails("clickOnPONumberHeaderPlusIcon", "PO details and GRN details must be validate sucessfully",
					"Successfully validated PO details and GRN details" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickOnPONumberHeaderPlusIcon");
		} catch (Exception e) {
			log.fatal("Unable to validate PO details and GRN details" + e.getMessage());
			pdfResultReport.addStepDetails("clickOnPONumberHeaderPlusIcon", "PO details and GRN details must be validate sucessfully",
					"Unable to validate PO details and GRN details" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void clickOnViewASNInInvoiceProcessingListPage() throws Throwable {
		try {
			log.info("started executing the method:: clickOnViewASNInInvoiceProcessingListPage");
			JSClick(tendercreationlocators.viewASN_InoviceNumber, "viewASN_InoviceNumber");
			waitForElementToBeVisible(tendercreationlocators.previewASN);
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForObj(5000);
			String ponum = eTenderComponent.getDataFromPropertiesFile("poDocNum");
			String UIPonum = text(tendercreationlocators.ASNPONum);
			if (ponum.equals(UIPonum)) {
				highlight(tendercreationlocators.ASNPONum);
				String ASN = eTenderComponent.getDataFromPropertiesFile("ASNNum");
				String ASNUI = text(tendercreationlocators.ASNVal);
				if (ASN.equals(ASNUI)) {
					highlight(tendercreationlocators.ASNVal);
					String Ref = "REF_".concat(ponum);
					String RefUI = text(tendercreationlocators.ASN_reference);
					if (Ref.equals(RefUI)) {

						highlight(tendercreationlocators.ASN_reference);
						String Shipmetmethod = "ShippingMethod_".concat(ponum);
						String ShipmetmethodUI = text(tendercreationlocators.ASN_Shippingmethod);
						if (Shipmetmethod.equals(ShipmetmethodUI)) {
							highlight(tendercreationlocators.ASN_Shippingmethod);
							String ASNAdditionalNote = "Shipment Additional Note";
							String ASNAdditionalNoteUI = text(tendercreationlocators.ASNAdditionalNoteVal);
							if (ASNAdditionalNote.equals(ASNAdditionalNoteUI)) {
								highlight(tendercreationlocators.ASNAdditionalNoteVal);
								String ASN_carrier = "CARRIER_".concat(ponum);
								String ASN_carrierUI = text(tendercreationlocators.ASN_carrier);
								if (ASN_carrier.equals(ASN_carrierUI)) {

									highlight(tendercreationlocators.ASN_carrier);
									String ASN_Shipment_Num = "ShippingTracking_".concat(ponum);
									String ASN_Shipment_NumUI = text(tendercreationlocators.ASN_Shipment_Num);
									if (ASN_Shipment_Num.equals(ASN_Shipment_NumUI)) {

										highlight(tendercreationlocators.ASN_Shipment_Num);
										waitForObj(2000);
										highlight(tendercreationlocators.SellerORGName);
										waitForObj(2000);
										highlight(tendercreationlocators.budgetHead);
										waitForObj(2000);
										highlight(tendercreationlocators.SupplierAddress);
										waitForObj(2000);
										highlight(tendercreationlocators.Suppliercountry);
										waitForObj(2000);
										highlight(tendercreationlocators.Supplierzip);
										waitForObj(2000);
										highlight(tendercreationlocators.Suppiercontact);
										waitForObj(2000);
										pdfResultReport.addStepDetails("clickOnViewASNInInvoiceProcessingListPage",
												"Should verify all validations",
												"Successfully verified all validations" + " ", "Pass", "Y");
										JavascriptExecutor js = (JavascriptExecutor) ThreadLocalWebdriver.getDriver();
										js.executeScript("window.scrollBy(0,350)", "");
										scrollToElement(tendercreationlocators.SellerInvoiceNumber);
										waitForObj(5000);
										highlight(tendercreationlocators.SellerInvoiceNumber);
										waitForObj(2000);
										highlight(tendercreationlocators.Billingaddress);
										waitForObj(2000);
										highlight(tendercreationlocators.DiscountbeforNoofDAYS);
										waitForObj(2000);
										highlight(tendercreationlocators.InvoiceTypeVal);
										pdfResultReport.addStepDetails("clickOnViewASNInInvoiceProcessingListPage",
												"Should verify all validations",
												"Successfully verified all validations" + " ", "Pass", "Y");
									} else {
										pdfResultReport.addStepDetails("clickOnViewASNInInvoiceProcessingListPage",
												"Should verify ASN_Shipment_Num value",
												"Unable to  verify ASN_Shipment_Num Value" + " ", "Fail", "N");
									}
								} else {
									pdfResultReport.addStepDetails("clickOnViewASNInInvoiceProcessingListPage",
											"Should verify ASN_carrier value",
											"Unable to  verify ASN_carrier Value" + " ", "Fail", "N");
								}
							} else {
								pdfResultReport.addStepDetails("clickOnViewASNInInvoiceProcessingListPage",
										"Should verify ASNAdditionalNote value",
										"Unable to  verify ASNAdditionalNote Value" + " ", "Fail", "N");
							}

						} else {
							pdfResultReport.addStepDetails("clickOnViewASNInInvoiceProcessingListPage",
									"Should verify Shipmetmethod value", "Unable to  verify Shipmetmethod Value" + " ",
									"Fail", "N");
						}
					} else {
						pdfResultReport.addStepDetails("clickOnViewASNInInvoiceProcessingListPage",
								"Should verify Reference value", "Unable to  verify Reference Value" + " ", "Fail",
								"N");
					}

				} else {
					pdfResultReport.addStepDetails("clickOnViewASNInInvoiceProcessingListPage", "Should verify ASN value",
							"Unable to  verify ASN Value" + " ", "Fail", "N");
				}
			} else {
				pdfResultReport.addStepDetails("clickOnViewASNInInvoiceProcessingListPage", "Should verify PO value",
						"Unable to  verify PO Value" + " ", "Fail", "N");
			}

			pdfResultReport.addStepDetails("clickOnViewASNInInvoiceProcessingListPage", "Should verify all validations",
					"Successfully verified all validations" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickOnViewASNInInvoiceProcessingListPage");
		} catch (Exception e) {
			log.fatal("Unable to  Display GRN  Records" + e.getMessage());
			pdfResultReport.addStepDetails("clickOnViewASNInInvoiceProcessingListPage", "Should verify all validations",
					"Unable to  verify all validations" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}
	public void verifyEyeIconToViewASN() throws Throwable {
		try {
			log.info("started executing the method:: verifyEyeIconToViewASN");
			highlight(tendercreationlocators.viewASN_InoviceNumber);	
			IsElementPresent(tendercreationlocators.viewASN_InoviceNumber);
			pdfResultReport.addStepDetails("verifyEyeIconToViewASN", "Row should be displayed with an eye icon to view ASN",
					"Successfully dispalyed row with an eye icon to view ASN "+ " ", "Pass", "Y");
			log.info("completed executing the method:: verifyEyeIconToViewASN");
		} catch (Exception e) {
			log.fatal("Unable to dispaly row with an eye icon to view ASN" + e.getMessage());
			pdfResultReport.addStepDetails("verifyEyeIconToViewASN", "Row should be displayed with an eye icon to view ASN",
					"Unable to dispaly row with an eye icon to view ASN"+ e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}
	public void verifyEyeIconToViewGRN() throws Throwable {
		try {
			log.info("started executing the method:: clickOnViewGrnIcon");
			highlight(tendercreationlocators.viewGrnBy);	
			IsElementPresent(tendercreationlocators.viewGrnBy);
			pdfResultReport.addStepDetails("verifyEyeIconToViewGRN", "Row should be displayed with an eye icon to view GRN",
					"Successfully dispalyed row with an eye icon to view GRN "+ " ", "Pass", "Y");
			log.info("completed executing the method:: verifyEyeIconToViewGRN");
		} catch (Exception e) {
			log.fatal("Unable to dispaly row with an eye icon to view GRN" + e.getMessage());
			pdfResultReport.addStepDetails("verifyEyeIconToViewGRN", "Row should be displayed with an eye icon to view GRN",
					"Unable to dispaly row with an eye icon to view GRN "+ e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}
	public void verifyEyeIconToViewPO() throws Throwable {
		try {
			log.info("started executing the method:: verifyEyeIconToViewPO");
			highlight(tendercreationlocators.view_PO);	
			IsElementPresent(tendercreationlocators.view_PO);
			pdfResultReport.addStepDetails("verifyEyeIconToViewPO", "Row should be displayed with an eye icon to view PO",
					"Successfully dispalyed row with an eye icon to view PO"+ " ", "Pass", "Y");
			log.info("completed executing the method:: verifyEyeIconToViewPO");
		} catch (Exception e) {
			log.fatal("Unable to dispaly row with an eye icon to view PO" + e.getMessage());
			pdfResultReport.addStepDetails("verifyEyeIconToViewPO", "Row should be displayed with an eye icon to view PO",
					"Unable to dispaly row with an eye icon to view PO"+ e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}
	public void clickOnViewPOInInvoiceProcessingListPage() throws Throwable {
		try {
			log.info("started executing the method:: clickOnViewPOInInvoiceProcessingListPage");
			JSClick(tendercreationlocators.view_PO, "view_PO");
			waitForElementToBeVisible(tendercreationlocators.View_PO_NUm);
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.View_PO_NUm);
			highlight(tendercreationlocators.View_PO_NUm);
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.View_PO_ref_NUm);
			highlight(tendercreationlocators.View_PO_ref_NUm);
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
			pdfResultReport.addStepDetails("clickOnViewPOInInvoiceProcessingListPage", "PO details must be validate sucessfully",
					"Successfully validated PO details " + " ", "Pass","Y");
			IsElementPresent(tendercreationlocators.View_PO_Supplier_Organization_Name);
			highlight(tendercreationlocators.View_PO_Supplier_Organization_Name);
			pdfResultReport.addStepDetails("clickOnViewPOInInvoiceProcessingListPage", "PO details must be validate sucessfully",
					"Successfully validated PO details " + " ", "Pass","Y");
			log.info("completed executing the method:: clickOnViewPOInInvoiceProcessingListPage");
		} catch (Exception e) {
			log.fatal("Unable to validate PO details" + e.getMessage());
			pdfResultReport.addStepDetails("clickOnViewPOInInvoiceProcessingListPage", "PO details must be validate sucessfully",
					"Unable to validate PO details" + e.getMessage(), "Fail","N");
                        Assert.fail("Failed Due to " + e.getMessage());
		}
	}
	public void closePODetailsPopUp() throws Throwable {
		try {
			log.info("started executing the method:: closePODetailsPopUp");
			click(tendercreationlocators.View_PO_CloseBtn, "View_PO_CloseBtn");
			waitForObj(2000);
			pdfResultReport.addStepDetails("closePODetailsPopUp", "Should close PO Details Pop Up",
					"Successfully closed PO details Pop Up" + " ", "Pass", "Y");
			log.info("completed executing the method:: closePODetailsPopUp");
		} catch (Exception e) {
			log.fatal("Unable to close Po Details pop up" + e.getMessage());
			pdfResultReport.addStepDetails("closePODetailsPopUp", "Should close PO Details Pop Up",
					"Unable to close PO Details Pop Up" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}

	}
	public void invoiceNumberDataValidation() throws Throwable {
		try {
			log.info("started executing the method:: invoiceNumberDataValidation");
			IsElementPresent(tendercreationlocators.invoice_Date);
			highlight(tendercreationlocators.invoice_Date);
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.dueDate);
			highlight(tendercreationlocators.dueDate);
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.invoiceAmount);
			highlight(tendercreationlocators.invoiceAmount);
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.paidAmount);
			highlight(tendercreationlocators.paidAmount);
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.overDueAmount);
			highlight(tendercreationlocators.overDueAmount);
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.invoiceStatus);
			highlight(tendercreationlocators.invoiceStatus);
			waitForObj(2000);
			pdfResultReport.addStepDetails("invoiceNumberDataValidation", "Should verify all validations",
					"Successfully verified all validation" + " ", "Pass", "Y");
			log.info("completed executing the method:: invoiceNumberDataValidation");
		} catch (Exception e) {
			log.fatal("Unable to verify all validation" + e.getMessage());
			pdfResultReport.addStepDetails("invoiceNumberDataValidation", "Should verify all validations",
					"Unable to verify all validation" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}

	}
	public void verifyGrnDataIsCorrectOrNot(String ItemCode, String rejvalue, String excesvalue, String shortvalue,
			String damagevalue,String poQuantity,String AsnQty,String GrnAcceptedQty) throws Throwable {
		try {
			log.info("started executing the method:: verifyGrnDataIsCorrectOrNot");

			waitForObj(2000);
			
			scrollToElement(tendercreationlocators.poQuantityValue(ItemCode.trim()));
			
			highlight(tendercreationlocators.poQuantityValue(ItemCode.trim()));

			String poQuantityvalue = text(tendercreationlocators.poQuantityValue(ItemCode.trim()));

			Assert.assertTrue(poQuantityvalue.trim().contains(poQuantity), "POQty Value is Correct");

			waitForObj(1000);
			
			highlight(tendercreationlocators.AsnQuantityValue(ItemCode.trim()));

			String asnQuantityvalue = text(tendercreationlocators.AsnQuantityValue(ItemCode.trim()));

			Assert.assertTrue(asnQuantityvalue.trim().contains(AsnQty), "AsnQuantityValue is Correct");

			waitForObj(1000);
			
			highlight(tendercreationlocators.RejectedQtyvalue(ItemCode.trim()));

			String rejectedvalue = text(tendercreationlocators.RejectedQtyvalue(ItemCode.trim()));

			Assert.assertTrue(rejectedvalue.trim().contains(rejvalue), "Rejected Value is Correct");

			waitForObj(1000);

			scrollToElement(tendercreationlocators.ExcessQtyvalue(ItemCode.trim()));

			highlight(tendercreationlocators.ExcessQtyvalue(ItemCode.trim()));

			String excessQtyvalue = text(tendercreationlocators.ExcessQtyvalue(ItemCode.trim()));

			Assert.assertTrue(excessQtyvalue.trim().contains(excesvalue), "excess Value is Correct");

			waitForObj(1000);

			scrollToElement(tendercreationlocators.ShortQtyvalue(ItemCode.trim()));

			highlight(tendercreationlocators.ShortQtyvalue(ItemCode.trim()));

			String shortQtyvalue = text(tendercreationlocators.ShortQtyvalue(ItemCode.trim()));

			Assert.assertTrue(shortQtyvalue.trim().contains(shortvalue), "Short Value is Correct");

			waitForObj(1000);

			scrollToElement(tendercreationlocators.DamageQtyvalue(ItemCode.trim()));

			highlight(tendercreationlocators.DamageQtyvalue(ItemCode.trim()));

			String damageQtyvalue = text(tendercreationlocators.DamageQtyvalue(ItemCode.trim()));

			Assert.assertTrue(damageQtyvalue.trim().contains(damagevalue), "damage Value is Correct");

			waitForObj(1000);
			
			scrollToElement(tendercreationlocators.AcceptedGrnQuantity(ItemCode.trim()));
			
			highlight(tendercreationlocators.AcceptedGrnQuantity(ItemCode.trim()));

			String acceptedGrnQuantityValue = text(tendercreationlocators.AcceptedGrnQuantity(ItemCode.trim()));

			Assert.assertTrue(acceptedGrnQuantityValue.trim().contains(GrnAcceptedQty), "Grn Accepted Value is Correct");

			
			waitForObj(3000);

			pdfResultReport.addStepDetails("verifyGrnDataIsCorrectOrNot",
					"Grn data Should be Validated",
					"Sucessfully checked Grn details In Pop Up" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyViewGrnSavedDetailsInPopUp");
		} catch (Exception e) {
			log.fatal("failed to check Grn details In Pop Up " + e.getMessage());
			pdfResultReport.addStepDetails("verifyViewGrnSavedDetailsInPopUp",
					"Grn data Should be Validated",
					"failed to check Grn details In Pop Up " + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void VerifyPOinInvoicePage() throws Throwable {
		try {
			log.info("started executing the method:: VerifyPOinInvoicePage");
			JSClick(tendercreationlocators.invoiceActiondrpdwn(eTenderComponent.getDataFromPropertiesFile("InVoice_Number")), "invoiceActiondrpdwn");
			waitForObj(2000);
			JSClick(tendercreationlocators.ViewInvoicePO(eTenderComponent.getDataFromPropertiesFile("InVoice_Number")), "ViewInvoicePO");
			
			waitForObj(3000);
			etendercomponentobj.waitForSpinnerToDisappear();
			String PonumRef=eTenderComponent.getDataFromPropertiesFile("POReferenceNum");
			String POnumRefUi = text(tendercreationlocators.View_PO_ref_NUm);
			if(PonumRef.equals(POnumRefUi))
			{
				highlight(tendercreationlocators.View_PO_ref_NUm);
				String PoNum = eTenderComponent.getDataFromPropertiesFile("poDocNum");
				String PoNumUi =text(tendercreationlocators.View_PO_NUm);
				if(PoNum.equals(PoNumUi))
				{
					highlight(tendercreationlocators.View_PO_NUm);
					highlight(tendercreationlocators.View_PO_Value);
					String PoAmendentFagUi =text(tendercreationlocators.View_PO_Amendment_flag);
					if(PoAmendentFagUi.equals("Yes"))
					{
						highlight(tendercreationlocators.View_PO_Amendment_flag);
						waitForObj(2000);
						highlight(tendercreationlocators.View_PO_Start_Date);
						waitForObj(2000);
						highlight(tendercreationlocators.View_PO_Expiry_Date);
						String sanctionNumber = eTenderComponent.getDataFromPropertiesFile("sanctionoteDocnum");
						String sanctionNumberUi =text(tendercreationlocators.View_Sanction_Note_Num);
						
						if(sanctionNumber.equals(sanctionNumberUi))
						{
							highlight(tendercreationlocators.View_Sanction_Note_Num);
							scrollToElement(tendercreationlocators.SupplierCode);
							String SupplierCodeUi =text(tendercreationlocators.SupplierCode);
							String SupplierorgNameUi =text(tendercreationlocators.SupplierorgName);
							if(SupplierCodeUi.equals("TCS_AUTO_01")&&SupplierorgNameUi.equals("TCS"))
							{
								highlight(tendercreationlocators.SupplierCode);
								waitForObj(2000);
								highlight(tendercreationlocators.SupplierorgName);
								highlight(tendercreationlocators.Address);
								highlight(tendercreationlocators.Country);
								
							}
							else
							{
								pdfResultReport.addStepDetails("VerifyPOinInvoicePage",
										"Should verify SupplierCode and SupplierorgName " ,
										"Unable to  verify  SupplierCode and SupplierorgName" + " ", "Fail", "N");
							}
						}else
						{
							pdfResultReport.addStepDetails("VerifyPOinInvoicePage",
									"Should verify Sanction Note Number" ,
									"Unable to  verify  Sanction Note Number" + " ", "Fail", "N");
						}
					}
					else
					{
						pdfResultReport.addStepDetails("VerifyPOinInvoicePage",
								"Should verify PO Amendent Flag" ,
								"Unable to  verify  PO Amendent Flag" + " ", "Fail", "N");
					}
				}
				else
				{
					pdfResultReport.addStepDetails("VerifyPOinInvoicePage",
							"Should verify PO  value",
							"Unable to  verify PO  Value" + " ", "Fail", "N");
				}
			}
			else
			{
				pdfResultReport.addStepDetails("VerifyPOinInvoicePage",
						"Should verify PO Ref value",
						"Unable to  verify PO Ref Value" + " ", "Fail", "N");
			}
			
			pdfResultReport.addStepDetails("VerifyPOinInvoicePage", "PO Details will be displayed",
					"Successfully displayed PO Details" + " ", "Pass", "Y");
			log.info("completed executing the method:: VerifyPOinInvoicePage");
			JSClick(tendercreationlocators.ClosePoDetails,"ClosePoDetails");
			waitForObj(5000);
		} catch (Exception e) {
			log.fatal("Unable to display PO Details" + e.getMessage());
			pdfResultReport.addStepDetails("VerifyPOinInvoicePage", "PO Details will be displayed",
					"Unable to display PO Details" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}
	public void SearchWithInvoiceNum() throws Throwable {
		try {
			log.info("started executing the method:: SearchWithInvoiceNum");
			JSClick(tendercreationlocators.search, "search");
			waitForObj(2000);
			set(tendercreationlocators.search,eTenderComponent.getDataFromPropertiesFile("InVoice_Number"), "btnInvoiceListPayment");
			waitForObj(5000);
			JSClick(tendercreationlocators.chkboxinvoice(eTenderComponent.getDataFromPropertiesFile("InVoice_Number")), "chkboxinvoice");
			
			waitForObj(3000);
			pdfResultReport.addStepDetails("SearchWithInvoiceNum", "Specific Invoice Number will be displayed",
					"Successfully searched specific invoice number" + " ", "Pass", "Y");
			log.info("completed executing the method:: SearchWithInvoiceNum");
		} catch (Exception e) {
			log.fatal("Unable to display specific invoice number" + e.getMessage());
			pdfResultReport.addStepDetails("SearchWithInvoiceNum", "Specific Invoice Number will be displayed",
					"Unable to display specific invoice number" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}
	
		public void VerifyMaterialsDetailsInvoice() throws Throwable {
			try {
				log.info("started executing the method:: VerifyMaterialsDetails");
				etendercomponentobj.waitForSpinnerToDisappear();
				waitForObj(5000);
				String GRNIDUi=text(tendercreationlocators.GRNidUi);
				String GrnId =eTenderComponent.getDataFromPropertiesFile("GRNID");
				if(GRNIDUi.equals(GrnId))
				{
					highlight(tendercreationlocators.GRNidUi);			
				   String ItemCodeUI = text(tendercreationlocators.ItemCodeUi);
				if (ItemCodeUI.equals("Item Code_1")) {
					highlight(tendercreationlocators.ItemCodeUi);
					String ItemDescriptionUI = text(tendercreationlocators.ItemDescriptionUi);
					if (ItemDescriptionUI.equals("ItemDescription_1")) {
						highlight(tendercreationlocators.ItemDescriptionUi);
						String UOMUI = text(tendercreationlocators.UOMUi);
						if (UOMUI.equals("CAR")) {

							highlight(tendercreationlocators.UOMUi);
							String POQuantityUiUI = text(tendercreationlocators.POQuantityUi);
							if (POQuantityUiUI.equals("PO Quantity: 15.00")) {
								highlight(tendercreationlocators.POQuantityUi);
								String ASNQuantityUiUI = text(tendercreationlocators.ASNQuantityUi);
								if (ASNQuantityUiUI.equals("ASN Quantity: 15.00")) {
									highlight(tendercreationlocators.ASNQuantityUi);
									String RejectedQuantityUI = text(tendercreationlocators.RejectedQuantityUi);
									if (RejectedQuantityUI.equals("Rejected Quantity : 0.00")) {
										highlight(tendercreationlocators.RejectedQuantityUi);
										String ExcessQuantityUI = text(tendercreationlocators.ExcessQuantityUi);
										if (ExcessQuantityUI.equals("Excess Quantity: 0.00")) {
											highlight(tendercreationlocators.ExcessQuantityUi);
											String DamageQuantityUI = text(tendercreationlocators.DamageQuantity);
											if (DamageQuantityUI.equals("Damage Quantity: 0.00")) {
												highlight(tendercreationlocators.DamageQuantity);
												String AcceptedQuantityUI = text(tendercreationlocators.AcceptedQuantityUi);
												if (AcceptedQuantityUI.equals("15.00")) {
													highlight(tendercreationlocators.AcceptedQuantityUi);
												} else {
													pdfResultReport.addStepDetails("VerifyMaterialsDetails",
															"Should verify Accepted Quantity value",
															"Unable to  verify Accepted Quantity Value" + " ", "Fail", "N");
												}

											} else {
												pdfResultReport.addStepDetails("VerifyMaterialsDetails",
														"Should verify Damage Quantity value",
														"Unable to  verify Damage Quantity Value" + " ", "Fail", "N");
											}
										} else {
											pdfResultReport.addStepDetails("VerifyMaterialsDetails",
													"Should verify Excess Quantity value",
													"Unable to  verify Excess Quantity Value" + " ", "Fail", "N");
										}
									} else {
										pdfResultReport.addStepDetails("VerifyMaterialsDetails",
												"Should verify Rejected Quantity value",
												"Unable to  verify Rejected Quantity Value" + " ", "Fail", "N");
									}
								} else {
									pdfResultReport.addStepDetails("VerifyMaterialsDetails",
											"Should verify ASN Quantity value",
											"Unable to  verify ASN Quantity Value" + " ", "Fail", "N");
								}

							} else {
								pdfResultReport.addStepDetails("VerifyMaterialsDetails", "Should verify PO Quantity value",
										"Unable to  verify PO Quantity Value" + " ", "Fail", "N");
							}
						} else {
							pdfResultReport.addStepDetails("VerifyMaterialsDetails", "Should verify UOM value",
									"Unable to  verify UOM Value" + " ", "Fail", "N");
						}

					} else {
						pdfResultReport.addStepDetails("VerifyMaterialsDetails", "Should verify Item Description value",
								"Unable to  verify Item Description Value" + " ", "Fail", "N");
					}
				} else {
					pdfResultReport.addStepDetails("VerifyMaterialsDetails", "Should verify Item Code value",
							"Unable to  verify Item Code Value" + " ", "Fail", "N");
				}
				}
				else{
					pdfResultReport.addStepDetails("VerifyMaterialsDetails", "Should verify GRN ID value",
							"Unable to  verify  GRN ID Value" + " ", "Fail", "N");
				}

				pdfResultReport.addStepDetails("VerifyDetailsInShipmetDetailsPage", "Should verify all validations",
						"Successfully verified all validations" + " ", "Pass", "Y");
				log.info("completed executing the method:: VerifyDetailsInShipmetDetailsPage");
			} catch (Exception e) {
				log.fatal("Unable to  Display GRN  Records" + e.getMessage());
				pdfResultReport.addStepDetails("VerifyDetailsInShipmetDetailsPage", "Should verify all validations",
						"Unable to  verify all validations" + e.getMessage(), "Fail", "N");

				Assert.fail("Failed Due to " + e.getMessage());
			}
		}
		public void navigatingToDisputedInvoiceList() throws Throwable {
			try {
				log.info("started executing the method:: navigatingToDisputedInvoiceList");

				//waitForElementToBeVisible(tendercreationlocators.InVoiceMenuLinkBy);
				JSClick(tendercreationlocators.InVoiceMenuLinkBy, "Invoice Button");
				waitForElementToBeVisible(tendercreationlocators.disputeInvoiceList);
				waitForObj(3000);
				JSClick(tendercreationlocators.disputeInvoiceList,"disputeInvoiceList");
				waitForObj(5000);
				etendercomponentobj.waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
				waitForObj(2000);
				pdfResultReport.addStepDetails("navigatingToDisputedInvoiceList", "Navigated succesfully to disputed Invoice Payment List page",
						"Successfully navigated to disputed Invoice Payment List page" + " ", "Pass", "Y");
				log.info("completed executing the method:: navigatingToDisputedInvoiceList");
			} catch (Exception e) {
				log.fatal("Unable to navigate to disputed Invoice Payment List page" + e.getMessage());
				pdfResultReport.addStepDetails("navigatingToDisputedInvoiceList", "Should navigate to disputed Invoice Payment List page",
						"Unable to navigate to disputed Invoice Payment List page" + e.getMessage(), "Fail", "N");

				Assert.fail("Failed Due to " + e.getMessage());
			}
		}
	public void verifyPONumber() throws Throwable {
			try {
				log.info("started executing the method:: verifyPONumber");
				//waitForElementToBeVisible(tendercreationlocators.enterPONumber);
				clear(tendercreationlocators.enterPONumber,"enterPONumber");
				set(tendercreationlocators.enterPONumber, eTenderComponent.getDataFromPropertiesFile("SellerInvoiceNumber"),
						"search The PO Doc Number");
				waitForObj(2000);
				pdfResultReport.addStepDetails("verifyPONumber", "PO Number is verified Successfully",
						"Successfully verified PO number" + " ", "Pass", "Y");
				click(tendercreationlocators.poNumberHeader,"poNumberHeader");
				saveInVoiceNumber();
				waitForObj(2000);
				pdfResultReport.addStepDetails("verifyPONumber", "PO Number is verified Successfully",
						"Successfully verified PO number" + " ", "Pass", "Y");
				log.info("completed executing the method:: verifyPONumber");
			} catch (Exception e) {
				log.fatal("Unable to verify PO number" + e.getMessage());
				pdfResultReport.addStepDetails("verifyPONumber", "Should verify PO number",
						"Unable verify PO number" + e.getMessage(), "Fail", "N");

				Assert.fail("Failed Due to " + e.getMessage());
			}
		}
	
	public void clickItemDetailsAndValiate() throws Throwable {
			try {
				log.info("started executing the method:: clickItemDetailsAndValiate");
				
				click(tendercreationlocators.itemDetailsTab,"ItemDetailsTab");
				waitForObj(2000);
				String itemCode1Actual = "", itemCode2Actual = "", itemCode3Actual = "",
						itemCode1ActualValue = "", itemCode2ActualValue = "", itemCode3ActualValue = "";
				
				String itemCodeTxt = null;
				String itemCodeValue = null;
				for(int i = 1; i<=3; i++) {
					itemCodeTxt = ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.itemCodeList(i)).getText().trim();
					itemCodeValue = ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.acceptedQuantity(i)).getText().trim();
					if(itemCodeTxt.equals("Item Code_1")) {
						itemCode1Actual = itemCodeTxt;
						itemCode1ActualValue = itemCodeValue;
						
					}else if(itemCodeTxt.equals("Item Code_2")) {
						itemCode2Actual = itemCodeTxt;
						itemCode2ActualValue = itemCodeValue;
						
					}else if(itemCodeTxt.equals("Item Code_3")){
						itemCode3Actual = itemCodeTxt;
						itemCode3ActualValue = itemCodeValue;
					}
						
				}
				System.out.println("ItemCode1Text is : "+itemCode1Actual+" and value is : " +itemCode1ActualValue+"\n"+
				"ItemCode2Text is : "+itemCode2Actual+" and value is : "+itemCode2ActualValue+"\n"+
				"ItemCode3Text is : "+itemCode3Actual+" and value is : "+itemCode3ActualValue);
				
				Assert.assertEquals(itemCode1ActualValue, etendercomponentobj.getDataFromPropertiesFile("itemCode_1AcceptedQuantityValue"));
				Assert.assertEquals(itemCode2ActualValue, etendercomponentobj.getDataFromPropertiesFile("itemCode_2AcceptedQuantityValue"));
				Assert.assertEquals(itemCode3ActualValue, etendercomponentobj.getDataFromPropertiesFile("itemCode_3AcceptedQuantityValue"));
				
				pdfResultReport.addStepDetails("clickItemDetailsAndValiate", "PO Number is verified Successfully",
						"Successfully verified PO number" + " ", "Pass", "Y");
				log.info("completed executing the method:: clickItemDetailsAndValiate");
			} catch (Exception e) {
				log.fatal("Unable to verify PO number" + e.getMessage());
				pdfResultReport.addStepDetails("clickItemDetailsAndValiate", "Should verify PO number",
						"Unable verify PO number" + e.getMessage(), "Fail", "N");

				Assert.fail("Failed Due to " + e.getMessage());
			}
		}
	public void quantityLessThanGRNQuantity() throws Throwable {
			try {
				log.info("started executing the method:: quantityLessThanGRNQuantity");
				
				click(tendercreationlocators.invoiceQuantityToBeRaised(1),"Invoice Quantity");
				clear(tendercreationlocators.invoiceQuantityToBeRaised(1),"TextBox");
				set(tendercreationlocators.invoiceQuantityToBeRaised(1),"15","Invoice Quantity");
				pdfResultReport.addStepDetails("quantityLessThanGRNQuantity", "PO Number is verified Successfully",
						"Successfully verified PO number" + " ", "Pass", "Y");
				waitForObj(2000);
				clear(tendercreationlocators.invoiceQuantityToBeRaised(1),"TextBox");
				set(tendercreationlocators.invoiceQuantityToBeRaised(1),"10","Invoice Quantity");
				pdfResultReport.addStepDetails("quantityLessThanGRNQuantity", "PO Number is verified Successfully",
						"Successfully verified PO number" + " ", "Pass", "Y");
				waitForObj(2000);
				
				click(tendercreationlocators.submitbutton,"SubmitButton");
				waitForElementToBeVisible(tendercreationlocators.disputedInvoicePaymentListTitle);
				waitForObj(2000);
				
				
				pdfResultReport.addStepDetails("quantityLessThanGRNQuantity", "PO Number is verified Successfully",
						"Successfully verified PO number" + " ", "Pass", "Y");
				log.info("completed executing the method:: quantityLessThanGRNQuantity");
			} catch (Exception e) {
				log.fatal("Unable to verify PO number" + e.getMessage());
				pdfResultReport.addStepDetails("quantityLessThanGRNQuantity", "Should verify PO number",
						"Unable verify PO number" + e.getMessage(), "Fail", "N");

				Assert.fail("Failed Due to " + e.getMessage());
			}
		}
	public void verifyUniqueInvoiceNumber() throws Throwable {
			try {
				log.info("started executing the method:: verifyUniqueInvoiceNumber");
				
				click(tendercreationlocators.poNumberHeader,"poNumberHeader");
				
				waitForObj(3000);
				
				String invoiceheaderText = text(tendercreationlocators.inVoiceHeaderBarBy);
				
				String invoiceNo = invoiceheaderText.substring(invoiceheaderText.lastIndexOf(':')+1,invoiceheaderText.lastIndexOf('/')).trim();
				
				Assert.assertFalse(invoiceNo.equals(etendercomponentobj.getDataFromPropertiesFile("InVoice_Number")), "Unique invoice number is displayed");
				
				pdfResultReport.addStepDetails("verifyUniqueInvoiceNumber", "invoice Number is verified Successfully",
						"Successfully verified invoice number" + " ", "Pass", "Y");
				log.info("completed executing the method:: verifyUniqueInvoiceNumber");
			} catch (Exception e) {
				log.fatal("Unable to verify invoice number" + e.getMessage());
				pdfResultReport.addStepDetails("verifyUniqueInvoiceNumber", "Should verify invoice number",
						"Unable verify invoice number" + e.getMessage(), "Fail", "N");

				Assert.fail("Failed Due to " + e.getMessage());
			}
		}
	public void clickOnSendBackToSupplier() throws Throwable {
		try {
			log.info("started executing the method:: clickOnSendBackToSupplier");
			
			JSClick(tendercreationlocators.sendBackToSupplierBy, "sendBackToSupplierBy");
			
			waitForObj(5000);
			
			pdfResultReport.addStepDetails("clickOnSendBackToSupplier", "Should click on sendback supplier Btn",
					"Successfully  clicked on sendback supplier Btn" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickOnSendBackToSupplier");
		} catch (Exception e) {
			log.fatal("Unable to  click on sendback supplier Btn" + e.getMessage());
			pdfResultReport.addStepDetails("clickOnSendBackToSupplier", "Should click on sendback supplier Btn ",
					"Unable to  click on sendback supplier Btn "+ e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}



public void verifyDisputedStatusIsPresent() throws Throwable {
		try {
			log.info("started executing the method:: verifyDisputedStatusIsPresent");
			
			highlight(tendercreationlocators.disputedStatusBy);
			
			IsElementPresent(tendercreationlocators.disputedStatusBy);
			
			waitForObj(2000);
			
			
			pdfResultReport.addStepDetails("verifyDisputedStatusIsPresent", "Should Verify Disputed Status is Present or not",
					"Successfully Verified Disputed Status" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyDisputedStatusIsPresent");
		} catch (Exception e) {
			log.fatal("Unable to Verify Disputed Status" + e.getMessage());
			pdfResultReport.addStepDetails("verifyDisputedStatusIsPresent", "Should Verify Disputed Status is Present or not",
					"Unable to Verify Disputed Status" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}



public void clickItemDetailsTab() throws Throwable {
		try {
			log.info("started executing the method:: clickItemDetailsTab");
			
			click(tendercreationlocators.itemDetailsTab,"ItemDetailsTab");
			
			waitForObj(2000);
			
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			
			waitForElementToBeVisible(tendercreationlocators.itemCodeList(1));
			
			waitForObj(2000);
			
			pdfResultReport.addStepDetails("clickItemDetailsTab", "Should click itemdetails tab",
					"Successfully clicked itemdetails tab" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickItemDetailsTab");
		} catch (Exception e) {
			log.fatal("Unable to click itemdetails tab" + e.getMessage());
			pdfResultReport.addStepDetails("clickItemDetailsTab", "Should click itemdetails tab",
					"Unable to click itemdetails tab" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

public void submitTheDisputedInVoice() throws Throwable {
		try {
			log.info("started executing the method:: submitTheDisputedInVoice");
			
			waitForObj(2000);
			
			click(tendercreationlocators.submitbutton,"SubmitButton");
			
			pdfResultReport.addStepDetails("submitTheDisputedInVoice", "Should Submit Disputed Invoice",
					"Successfully Submitted Disputed Invoicer" + " ", "Pass", "Y");
			
			
			log.info("completed executing the method:: submitTheDisputedInVoice");
		} catch (Exception e) {
			log.fatal("Unable to Submit Disputed Invoice" + e.getMessage());
			pdfResultReport.addStepDetails("submitTheDisputedInVoice", "Should Submit Disputed Invoice",
					"Unable to Submit Disputed Invoice" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

public void isDisputedInVoicePaymentListPageDisplayed() throws Throwable {
		try {
			log.info("started executing the method:: isDisputedInVoicePaymentListPageDisplayed");
			
			waitForElementToBeVisible(tendercreationlocators.invoiceNestedTableRowsBy);
			
			IsElementPresent(tendercreationlocators.invoiceNestedTableRowsBy);
			
			waitForObj(3000);
			
			pdfResultReport.addStepDetails("isDisputedInVoicePaymentListPageDisplayed", "Should display InVoice Payment List Page",
					"Successfully displaying InVoice Payment List Page" + " ", "Pass", "Y");
			log.info("completed executing the method:: isDisputedInVoicePaymentListPageDisplayed");
		} catch (Exception e) {
			log.fatal("Unable to display InVoice Payment List Page" + e.getMessage());
			pdfResultReport.addStepDetails("isDisputedInVoicePaymentListPageDisplayed", "Should display InVoice Payment List Page",
					"Unable to   display InVoice Payment List Page"+ e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

public void verifyMailSubjectAndMailBody(String subject, int max, String subContent1, String subContent2,
			String sellerInvoiceNum, String readyPayment) throws Exception {
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

			System.out.println("----------------------------------------------------------------------------------------------------");
			System.out.println("The email message List with given Subject   " + specificMailsubject + " The Size is    "
					+ specificMailsubject.length);
			System.out.println("-----------------------------------------------------------------------------------------------------");
			
			if (specificMailsubject.length > 0) {
				for (Message message : specificMailsubject) {

					if (message.getSubject().contains(sellerInvoiceNum)) {
						
						System.out.println("------------------------------------------------------------------------------------------");
						
						System.out.println("The Email Subject is :---->   " + message.getSubject());

						System.out.println("------------------------------------------------------------------------------------------");
						
						
						eTenderComponent.updateDataIntoPropertyFile("ExpectedMailSubject", message.getSubject());

						System.out.println("------------------------------------------------------------------------------------------");
						
						System.out.println("Updated latest Triggerd mail Subject in property File");

						System.out.println("------------------------------------------------------------------------------------------");

						pdfResultReport.addStepDetails("verifyMailSubjectAndMailBody",
								"Mail Subject Must Be Validated Sucessfully",
								"Successfully Validated Mail Subject as Expected ---->" + message.getSubject() + " "
										+ " ",
								"Pass", "Y");

						if (emailyUtils.getSpecificMailBodyContent(message, subContent1, subContent2)
								.contains(sellerInvoiceNum)
								&& emailyUtils.getSpecificMailBodyContent(message, subContent1, subContent2).replaceAll("\\s{2,}"," ").trim()
										.contains(readyPayment)) {

							String specificMailBodyContent = emailyUtils.getSpecificMailBodyContent(message,
									subContent1, subContent2).replaceAll("\\s{2,}"," ").trim();

							System.out.println("-------------------------------------------------------------------------------------------------");
							
							
							System.out.println("The Email Body is :---->   " + specificMailBodyContent);

							System.out.println("------------------------------------------------------------------------------------------------");
							
							
							eTenderComponent.updateDataIntoPropertyFile("ExpectedmailBody",
									specificMailBodyContent);
							
							System.out.println("------------------------------------------------------------------------------------------------");

							System.out.println("Updated latest Triggerd mail With body in property File");

							System.out.println("-------------------------------------------------------------------------------------------------");

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
									+ "\n expected Body content should contains the seller invoice number   :"
									+ sellerInvoiceNum);

							System.out.println(
									"----------------------------------------------------------------------------");
							System.out.println("Failed to validate mail body ");
							System.out.println(
									"----------------------------------------------------------------------------");
						}
					} else {
						System.out.println("Email is not matching with excpted Subject \n" + "Actual Subject is :"
								+ message.getSubject()
								+ "\n expected Subjected Content should contains the seller invoice number  : "
								+ sellerInvoiceNum + "  ");
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

				throw new AssertionError("Error Message : " + "The Given Subject List is Empty Size");
			}

			if (!flag) {

				System.out.println("The Mail was not triggered Unable to validate mail with given Subject" + subject
						+ sellerInvoiceNum + "  ");

				pdfResultReport.addStepDetails("verifyMailSubjectAndMailBody",
						"Mail Was Not Triggred waited for one minute", "Mail Was Not Triggred waited for one minute-->",

						"Fail", "N");

				throw new AssertionError(
						"Error Message : " + "The Mail was not triggered Unable to validate mail with given Subject"
								+ subject + sellerInvoiceNum + "  ");
			}

			log.info("completed executing the method:: verifyMailSubjectAndMailBody");

		} catch (Exception e) {

			pdfResultReport.addStepDetails("verifyMailSubjectAndMailBody", "Failed to validate mail Content",
					"Failed to validate mail Content",

					"Fail", "N");

			e.printStackTrace();

			Assert.fail(e.getMessage());

		}
	}
public void clickOnViewASNInDisputedInvoicePage() throws Throwable {
	try {
		log.info("started executing the method:: clickOnViewASNInDisputedInvoicePage");
		click(tendercreationlocators.itemCodeActionBtn(itemCode1IndexValue),"Action Btn");
		waitForElementToBeVisible(tendercreationlocators.itemCodeViewASN(itemCode1IndexValue));
		click(tendercreationlocators.itemCodeViewASN(itemCode1IndexValue),"itemCodeViewASN");
		waitForElementToBeVisible(tendercreationlocators.previewASN);
		waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
		waitForObj(3000);
		pdfResultReport.addStepDetails("clickOnViewASNInDisputedInvoicePage", "Preview ASN details popup must be display successfully",
				"Successfully displayed preview ASN details popup " + " ", "Pass", "Y");
		log.info("completed executing the method:: clickOnViewASNInDisputedInvoicePage");
	} catch (Exception e) {
		log.fatal("Unable to display preview ASN details popup" + e.getMessage());
		pdfResultReport.addStepDetails("clickOnViewASNInDisputedInvoicePage", "Preview ASN details popup must be display successfully",
				"Unable to display preview ASN details popup" + e.getMessage(), "Fail", "N");

		Assert.fail("Failed Due to " + e.getMessage());
	}
}
public void verifyASNDataIsCorrectOrNot() throws Throwable {
	try {
		log.info("started executing the method:: verifyASNDataIsCorrectOrNot");
		waitForElementToBeVisible(tendercreationlocators.previewASN);
		String ponum = eTenderComponent.getDataFromPropertiesFile("poDocNum");
		String UIPonum = text(tendercreationlocators.ASNPONum);
		if (ponum.equals(UIPonum)) {
			highlight(tendercreationlocators.ASNPONum);
			String ASN = eTenderComponent.getDataFromPropertiesFile("ASNNum");
			String ASNUI = text(tendercreationlocators.ASNVal);
			if (ASN.equals(ASNUI)) {
				highlight(tendercreationlocators.ASNVal);
				String Ref = "REF_".concat(ponum);
				String RefUI = text(tendercreationlocators.ASN_reference);
				if (Ref.equals(RefUI)) {

					highlight(tendercreationlocators.ASN_reference);
					String Shipmetmethod = "ShippingMethod_".concat(ponum);
					String ShipmetmethodUI = text(tendercreationlocators.ASN_Shippingmethod);
					if (Shipmetmethod.equals(ShipmetmethodUI)) {
						highlight(tendercreationlocators.ASN_Shippingmethod);
						String ASNAdditionalNote = "Shipment Additional Note";
						String ASNAdditionalNoteUI = text(tendercreationlocators.ASNAdditionalNoteVal);
						if (ASNAdditionalNote.equals(ASNAdditionalNoteUI)) {
							highlight(tendercreationlocators.ASNAdditionalNoteVal);
							String ASN_carrier = "CARRIER_".concat(ponum);
							String ASN_carrierUI = text(tendercreationlocators.ASN_carrier);
							if (ASN_carrier.equals(ASN_carrierUI)) {

								highlight(tendercreationlocators.ASN_carrier);
								String ASN_Shipment_Num = "ShippingTracking_".concat(ponum);
								String ASN_Shipment_NumUI = text(tendercreationlocators.ASN_Shipment_Num);
								if (ASN_Shipment_Num.equals(ASN_Shipment_NumUI)) {

									highlight(tendercreationlocators.ASN_Shipment_Num);
									waitForObj(2000);
									highlight(tendercreationlocators.SellerORGName);
									waitForObj(2000);
									highlight(tendercreationlocators.budgetHead);
									waitForObj(2000);
									highlight(tendercreationlocators.SupplierAddress);
									waitForObj(2000);
									highlight(tendercreationlocators.Suppliercountry);
									waitForObj(2000);
									highlight(tendercreationlocators.Supplierzip);
									waitForObj(2000);
									highlight(tendercreationlocators.Suppiercontact);
									waitForObj(2000);
									pdfResultReport.addStepDetails("verifyASNDataIsCorrectOrNot",
											"Should verify all validations",
											"Successfully verified all validations" + " ", "Pass", "Y");
									JavascriptExecutor js = (JavascriptExecutor) ThreadLocalWebdriver.getDriver();
									js.executeScript("window.scrollBy(0,350)", "");
									scrollToElement(tendercreationlocators.SellerInvoiceNumber);
									waitForObj(5000);
									highlight(tendercreationlocators.SellerInvoiceNumber);
									waitForObj(2000);
									highlight(tendercreationlocators.Billingaddress);
									waitForObj(2000);
									highlight(tendercreationlocators.DiscountbeforNoofDAYS);
									waitForObj(2000);
									highlight(tendercreationlocators.InvoiceTypeVal);
									pdfResultReport.addStepDetails("verifyASNDataIsCorrectOrNot",
											"Should verify all validations",
											"Successfully verified all validations" + " ", "Pass", "Y");
								} else {
									pdfResultReport.addStepDetails("verifyASNDataIsCorrectOrNot",
											"Should verify ASN_Shipment_Num value",
											"Unable to  verify ASN_Shipment_Num Value" + " ", "Fail", "N");
								}
							} else {
								pdfResultReport.addStepDetails("verifyASNDataIsCorrectOrNot",
										"Should verify ASN_carrier value",
										"Unable to  verify ASN_carrier Value" + " ", "Fail", "N");
							}
						} else {
							pdfResultReport.addStepDetails("verifyASNDataIsCorrectOrNot",
									"Should verify ASNAdditionalNote value",
									"Unable to  verify ASNAdditionalNote Value" + " ", "Fail", "N");
						}

					} else {
						pdfResultReport.addStepDetails("verifyASNDataIsCorrectOrNot",
								"Should verify Shipmetmethod value", "Unable to  verify Shipmetmethod Value" + " ",
								"Fail", "N");
					}
				} else {
					pdfResultReport.addStepDetails("verifyASNDataIsCorrectOrNot",
							"Should verify Reference value", "Unable to  verify Reference Value" + " ", "Fail",
							"N");
				}

			} else {
				pdfResultReport.addStepDetails("verifyASNDataIsCorrectOrNot", "Should verify ASN value",
						"Unable to  verify ASN Value" + " ", "Fail", "N");
			}
		} else {
			pdfResultReport.addStepDetails("verifyASNDataIsCorrectOrNot", "Should verify PO value",
					"Unable to  verify PO Value" + " ", "Fail", "N");
		}

		pdfResultReport.addStepDetails("verifyASNDataIsCorrectOrNot", "Should verify all validations",
				"Successfully verified all validations" + " ", "Pass", "Y");
		log.info("completed executing the method:: verifyASNDataIsCorrectOrNot");
	} catch (Exception e) {
		log.fatal("Unable to  Display ASNN  Records" + e.getMessage());
		pdfResultReport.addStepDetails("verifyASNDataIsCorrectOrNot", "Should verify all validations",
				"Unable to  verify all validations" + e.getMessage(), "Fail", "N");

		Assert.fail("Failed Due to " + e.getMessage());
	}
}

public void closePreViewASNDetailsPopUp() throws Throwable {
	try {
		log.info("started executing the method:: closePreViewASNDetailsPopUp");
		click(tendercreationlocators.closePreviewASN, "closePreviewASN");
		waitForObj(3000);
		pdfResultReport.addStepDetails("closePreViewASNDetailsPopUp", "Should close ASNPreview Pop Up",
				"Successfully closed  ASN  Preview Pop Up" + " ", "Pass", "Y");
		log.info("completed executing the method:: closePreViewASNDetailsPopUp");
	} catch (Exception e) {
		log.fatal("Unable to close  ASN Preview Pop Up" + e.getMessage());
		pdfResultReport.addStepDetails("closePreViewASNDetailsPopUp", "Should close  ASN Preview Pop Up",
				"Unable to close  ASN Preview Pop Up" + e.getMessage(), "Fail", "N");
		Assert.fail("Failed Due to " + e.getMessage());
	}
}
public void clickOnViewGRNInDisputedInvoicePage() throws Throwable {
	try {
		log.info("started executing the method:: clickOnViewGRNInDisputedInvoicePage");
		click(tendercreationlocators.itemCodeActionBtn(itemCode1IndexValue),"Action Btn");
		waitForElementToBeVisible(tendercreationlocators.itemCodeViewGRN(itemCode1IndexValue));
		click(tendercreationlocators.itemCodeViewGRN(itemCode1IndexValue),"itemCodeViewGRN");
		waitForElementToBeVisible(tendercreationlocators.grnIDBy);
		waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
		waitForObj(3000);
		pdfResultReport.addStepDetails("clickOnViewGRNInDisputedInvoicePage", "Preview GRN details popup must be display successfully",
				"Successfully displayed preview GRN details popup " + " ", "Pass", "Y");
		log.info("completed executing the method:: clickOnViewGRNInDisputedInvoicePage");
	} catch (Exception e) {
		log.fatal("Unable to display preview GRN details popup" + e.getMessage());
		pdfResultReport.addStepDetails("clickOnViewGRNInDisputedInvoicePage", "Preview GRN details popup must be display successfully",
				"Unable to display preview GRN details popup" + e.getMessage(), "Fail", "N");

		Assert.fail("Failed Due to " + e.getMessage());
	}
}
public void closePreViewGrnDetailsPopUp() throws Throwable {
	try {
		log.info("started executing the method:: closePreViewGrnDetailsPopUp");
		click(tendercreationlocators.closePreviewGRN, "closePreviewGRN");
		waitForObj(3000);
		pdfResultReport.addStepDetails("closePreViewGrnDetailsPopUp", "Should close GRN Preview Pop Up",
				"Successfully closed  GRN Preview Pop Up" + " ", "Pass", "Y");
		log.info("completed executing the method:: closePreViewGrnDetailsPopUp");
	} catch (Exception e) {
		log.fatal("Unable to  Display popup With GRN Preview Details" + e.getMessage());
		pdfResultReport.addStepDetails("closePreViewGrnDetailsPopUp", "Should close  GRN Preview Pop Up",
				"Unable to close  GRN Preview Pop Up" + e.getMessage(), "Fail", "N");

		Assert.fail("Failed Due to " + e.getMessage());
	}
}
public void VerifyDataPopulatedInInvoiceListPage() throws Throwable {
	try {
		log.info("started executing the method:: VerifyDataPopulatedInInvoiceListPage");
		String InvoiceNo =eTenderComponent.getDataFromPropertiesFile("InVoice_Number");
		String InvoiceNoUI=text(tendercreationlocators.InvoiceNuUI(InvoiceNo));
		LocalDateTime localdatetime = LocalDateTime.now();

		String currentDateTime = localdatetime.format(DateTimeFormatter.ofPattern("dd-MM-yy"));
		String Amt="45,015.00";
		String Status="Due";
		if(InvoiceNo.equals(InvoiceNoUI))
		{
			highlight(tendercreationlocators.InvoiceNuUI(InvoiceNo));
			waitForObj(2000);
			String SupplierUI=text(tendercreationlocators.SupplierUI);
			if(SupplierUI.equals("TCS"))
			{
				highlight(tendercreationlocators.SupplierUI);
				waitForObj(2000);
				String InvoicedDateUI=text(tendercreationlocators.InvoiceDateUI(currentDateTime));
				if(currentDateTime.equals(InvoicedDateUI))
				{
					highlight(tendercreationlocators.InvoiceDateUI(currentDateTime));
					waitForObj(2000);
					String InvoiceAmt=text(tendercreationlocators.InvoiceAmtUI(Amt));
					String BalanceAmt=text(tendercreationlocators.BalanceInvoiceAmtUI(Amt));
					
					if(InvoiceAmt.equals(Amt) && BalanceAmt.equals(Amt))
					{
						highlight(tendercreationlocators.InvoiceAmtUI(Amt));
						waitForObj(2000);
						highlight(tendercreationlocators.BalanceInvoiceAmtUI(Amt));
						waitForObj(2000);
						String StatusUI=text(tendercreationlocators.InvoiceStatusUI(Status));
						if(StatusUI.equals(Status))
						{
							highlight(tendercreationlocators.InvoiceStatusUI(Status));
							waitForObj(2000);
						}
						else
						{
							pdfResultReport.addStepDetails("VerifyDataPopulatedInInvoiceListPage", "All data of the Invoice will be validated",
									"Unable to validate Status" +" ", "Fail", "N");
						}
					}
					else
					{
						pdfResultReport.addStepDetails("VerifyDataPopulatedInInvoiceListPage", "All data of the Invoice will be validated",
								"Unable to validate Invoice and Balance Amount" +" ", "Fail", "N");
					}
				}
				else
				{
					pdfResultReport.addStepDetails("VerifyDataPopulatedInInvoiceListPage", "All data of the Invoice will be validated",
							"Unable to validate Invoice Date" +" ", "Fail", "N");
				}
			}
			else
			{
				pdfResultReport.addStepDetails("VerifyDataPopulatedInInvoiceListPage", "All data of the Invoice will be validated",
						"Unable to validate Supplier" +" ", "Fail", "N");
			}
		}
		else
		{
			pdfResultReport.addStepDetails("VerifyDataPopulatedInInvoiceListPage", "All data of the Invoice will be validated",
					"Unable to validate Invoice Number" +" ", "Fail", "N");
		}
		
		pdfResultReport.addStepDetails("VerifyDataPopulatedInInvoiceListPage", "All data of the Invoice will be validated",
				"Successfully validated all Invoice Data" + " ", "Pass", "Y");
		log.info("completed executing the method:: VerifyDataPopulatedInInvoiceListPage");
	} catch (Exception e) {
		log.fatal("Unable to validate Invoice Data" + e.getMessage());
		pdfResultReport.addStepDetails("VerifyDataPopulatedInInvoiceListPage", "All data of the Invoice will be validated",
				"Unable to validate Invoice Data" + e.getMessage(), "Fail", "N");

		Assert.fail("Failed Due to " + e.getMessage());
	}
}
public void NavigateToPaymentPage() throws Throwable {
	try {
		log.info("started executing the method:: NavigateToPaymentPage");
		JSClick(tendercreationlocators.btnPay, "btnPay");
		
		etendercomponentobj.waitForSpinnerToDisappear();
		waitForObj(5000);
		pdfResultReport.addStepDetails("NavigateToPaymentPage", "Should navigate to payment Page",
				"Successfully navigated to payment Page" + " ", "Pass", "Y");
		log.info("completed executing the method:: NavigateToPaymentPage");
	} catch (Exception e) {
		log.fatal("Unable to navigate to payment Page" + e.getMessage());
		pdfResultReport.addStepDetails("NavigateToPaymentPage", "Should navigate to payment Paged",
				"Unable to navigate to payment Page" + e.getMessage(), "Fail", "N");

		Assert.fail("Failed Due to " + e.getMessage());
	}
}

public void VerifyAmounts() throws Throwable {
	try {
		log.info("started executing the method:: VerifyAmounts");
		click(tendercreationlocators.AmtToBePaid, "AmtToBePaid");
		ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.AmtToBePaid).sendKeys(Keys.DELETE);
        String BalanceUI=text(tendercreationlocators.Balance);
        String TotalUI=text(tendercreationlocators.Total);
        if(BalanceUI.equals("40,000.00") && TotalUI.equals("2,764.25"))
        {
        	highlight(tendercreationlocators.Balance);
        	waitForObj(2000);
        	highlight(tendercreationlocators.Total);
        }
        else
        {
        	pdfResultReport.addStepDetails("VerifyAmounts", "Should verify Balance and Total",
					"Successfully verified Balance and Total" + " ", "Fail", "Y");
        	ThreadLocalWebdriver.getDriver().close();
        }
        
		waitForObj(2000);
		pdfResultReport.addStepDetails("VerifyAmounts", "Should verify the Amount fields",
				"Successfully verified Amount fields" + " ", "Pass", "Y");
		log.info("completed executing the method:: VerifyAmounts");
	} catch (Exception e) {
		log.fatal("Unable to verify all the Payment Options" + e.getMessage());
		pdfResultReport.addStepDetails("VerifyAmounts", "Should verify all the Payment Options",
				"Unable to verify all the Payment Options" + e.getMessage(), "Fail", "N");

		Assert.fail("Failed Due to " + e.getMessage());
	}
}
public void VerifyAmountsFullPay() throws Throwable {
	try {
		log.info("started executing the method:: VerifyAmounts");
		click(tendercreationlocators.AmtToBePaid, "AmtToBePaid");
		ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.AmtToBePaid).sendKeys(Keys.DELETE);
		ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.AmtToBePaid).sendKeys("4");
        String BalanceUI=text(tendercreationlocators.Balance);
        String TotalUI=text(tendercreationlocators.Total);
        if(BalanceUI.equals("0.00") && TotalUI.equals("40,000.00"))
        {
        	highlight(tendercreationlocators.Balance);
        	waitForObj(2000);
        	highlight(tendercreationlocators.Total);
        }
        else
        {
        	pdfResultReport.addStepDetails("VerifyAmounts", "Should verify Balance and Total",
					"Successfully verified Balance and Total" + " ", "Fail", "Y");
        	ThreadLocalWebdriver.getDriver().close();
        }
        
		waitForObj(2000);
		pdfResultReport.addStepDetails("VerifyAmounts", "Should verify the Amount fields",
				"Successfully verified Amount fields" + " ", "Pass", "Y");
		log.info("completed executing the method:: VerifyAmounts");
	} catch (Exception e) {
		log.fatal("Unable to verify all the Payment Options" + e.getMessage());
		pdfResultReport.addStepDetails("VerifyAmounts", "Should verify all the Payment Options",
				"Unable to verify all the Payment Options" + e.getMessage(), "Fail", "N");

		Assert.fail("Failed Due to " + e.getMessage());
	}
}

public void VerifyPaymentOptions() throws Throwable {
	try {
		log.info("started executing the method:: VerifyPaymentOptions");
		JSClick(tendercreationlocators.btnPay, "btnPay");
		etendercomponentobj.waitForSpinnerToDisappear();
		
		String BeneficiaryName=pdfResultReport.testDataValue.get("BeneficiaryName");
		String BeneficiaryAccountNumber=pdfResultReport.testDataValue.get("BeneficiaryAccountNumber");
		String IFSCCode=pdfResultReport.testDataValue.get("IFSCCode");
		String TransactionNumber=pdfResultReport.testDataValue.get("TransactionNumber");
		
		JSClick(tendercreationlocators.BeneficiaryName, "BeneficiaryName");
		set(tendercreationlocators.BeneficiaryName, BeneficiaryName, "BeneficiaryName");
		waitForObj(2000);
		JSClick(tendercreationlocators.BeneficiaryAccountNumber, "BeneficiaryAccountNumber");
		set(tendercreationlocators.BeneficiaryAccountNumber, BeneficiaryAccountNumber, "BeneficiaryAccountNumber");
		waitForObj(2000);
		JSClick(tendercreationlocators.IFSCCode, "IFSCCode");
		set(tendercreationlocators.IFSCCode, IFSCCode, "IFSCCode");
		waitForObj(2000);
		JSClick(tendercreationlocators.TransactionNumber, "TransactionNumber");
		set(tendercreationlocators.TransactionNumber, TransactionNumber, "TransactionNumber");
		waitForObj(2000);
		highlight(tendercreationlocators.Amount);
		waitForObj(2000);
		
		
		
		String chqAccount=pdfResultReport.testDataValue.get("chqAccount");
		String ChqNumber=pdfResultReport.testDataValue.get("ChqNumber");
		LocalDateTime localdatetime = LocalDateTime.now();
		String ChqDate = localdatetime.format(DateTimeFormatter.ofPattern("dd-MM-yy"));
		String PayeeNameOnCheque=pdfResultReport.testDataValue.get("PayeeNameOnCheque");
	
		JSClick(tendercreationlocators.radiobtnChallan, "radiobtnChallan");
		waitForObj(5000);
		
		String VoucherNumber=pdfResultReport.testDataValue.get("VoucherNumber");
		String ProcessedBy=pdfResultReport.testDataValue.get("ProcessedBy");
		
		JSClick(tendercreationlocators.VoucherNumber, "VoucherNumber");
		set(tendercreationlocators.VoucherNumber, VoucherNumber, "VoucherNumber");
		waitForObj(2000);
		JSClick(tendercreationlocators.ProcessedBy, "ProcessedBy");
		set(tendercreationlocators.ProcessedBy, ProcessedBy, "ProcessedBy");
		waitForObj(2000);
		JSClick(tendercreationlocators.ChqDate, "ChqDate");
		set(tendercreationlocators.ChqDate, ChqDate, "ChqDate");
		
		JSClick(tendercreationlocators.radiobtnCheque, "radiobtnCheque");
		waitForObj(5000);		
		
		JSClick(tendercreationlocators.chqAccount, "chqAccount");
		set(tendercreationlocators.chqAccount, chqAccount, "chqAccount");
		waitForObj(2000);
		JSClick(tendercreationlocators.ChqNumber, "ChqNumber");
		set(tendercreationlocators.ChqNumber, ChqNumber, "ChqNumber");
		waitForObj(2000);
		JSClick(tendercreationlocators.ChqDate, "ChqDate");
		set(tendercreationlocators.ChqDate, ChqDate, "ChqDate");
		waitForObj(2000);
		JSClick(tendercreationlocators.PayeeNameOnCheque, "PayeeNameOnCheque");
		set(tendercreationlocators.PayeeNameOnCheque, PayeeNameOnCheque, "PayeeNameOnCheque");
		waitForObj(2000);
		highlight(tendercreationlocators.Amount);
		waitForObj(2000);
		JSClick(tendercreationlocators.Submit, "Submit");
		etendercomponentobj.waitForSpinnerToDisappear();
		waitForObj(5000);
		
		pdfResultReport.addStepDetails("NavigateToPaymentPage", "Should verify all the Payment Options",
				"Successfully verified all the Payment Options" + " ", "Pass", "Y");
		log.info("completed executing the method:: VerifyPaymentOptions");
	} catch (Exception e) {
		log.fatal("Unable to verify all the Payment Options" + e.getMessage());
		pdfResultReport.addStepDetails("VerifyPaymentOptions", "Should verify all the Payment Options",
				"Unable to verify all the Payment Options" + e.getMessage(), "Fail", "N");

		Assert.fail("Failed Due to " + e.getMessage());
	}
}
public void VerifyDataPopulatedInInvoiceListPageAfterPartialPayment() throws Throwable {
	try {
		log.info("started executing the method:: VerifyDataPopulatedInInvoiceListPageAfterPartialPayment");
		String InvoiceNo =eTenderComponent.getDataFromPropertiesFile("InVoice_Number");
		String InvoiceNoUI=text(tendercreationlocators.InvoiceNuUI(InvoiceNo));
		LocalDateTime localdatetime = LocalDateTime.now();

		String currentDateTime = localdatetime.format(DateTimeFormatter.ofPattern("dd-MM-yy"));
		String Amt="45,015.00";
		String BalanceAmt="42,250.75";
		String PAidAMt="2,764.25";
		String Status="Due";
		if(InvoiceNo.equals(InvoiceNoUI))
		{
			highlight(tendercreationlocators.InvoiceNuUI(InvoiceNo));
			waitForObj(2000);
			String SupplierUI=text(tendercreationlocators.SupplierUI);
			if(SupplierUI.equals("TCS"))
			{
				highlight(tendercreationlocators.SupplierUI);
				waitForObj(2000);
				String InvoicedDateUI=text(tendercreationlocators.InvoiceDateUI(currentDateTime));
				if(currentDateTime.equals(InvoicedDateUI))
				{
					highlight(tendercreationlocators.InvoiceDateUI(currentDateTime));
					waitForObj(2000);
					String InvoiceAmtUI=text(tendercreationlocators.InvoiceAmtUI(Amt));
					String BalanceAmtUI=text(tendercreationlocators.BalanceInvoiceAmtUI(BalanceAmt));
					String PaidAmtUI=text(tendercreationlocators.PaidAmt(Amt)).trim();
					
					if(InvoiceAmtUI.equals(Amt) && BalanceAmtUI.equals(BalanceAmt) && PaidAmtUI.equals(PAidAMt) )
					{
						highlight(tendercreationlocators.InvoiceAmtUI(Amt));
						waitForObj(2000);
						highlight(tendercreationlocators.BalanceInvoiceAmtUI(BalanceAmt));
						waitForObj(2000);
						highlight(tendercreationlocators.PaidAmt(Amt));
						waitForObj(2000);
						String StatusUI=text(tendercreationlocators.InvoiceStatusUI(Status));
						if(StatusUI.equals(Status))
						{
							highlight(tendercreationlocators.InvoiceStatusUI(Status));
							waitForObj(2000);
						}
						else
						{
							pdfResultReport.addStepDetails("VerifyDataPopulatedInInvoiceListPage", "All data of the Invoice will be validated",
									"Unable to validate Status" +" ", "Fail", "N");
						}
					}
					else
					{
						pdfResultReport.addStepDetails("VerifyDataPopulatedInInvoiceListPage", "All data of the Invoice will be validated",
								"Unable to validate Invoice and Balance Amount" +" ", "Fail", "N");
					}
				}
				else
				{
					pdfResultReport.addStepDetails("VerifyDataPopulatedInInvoiceListPage", "All data of the Invoice will be validated",
							"Unable to validate Invoice Date" +" ", "Fail", "N");
				}
			}
			else
			{
				pdfResultReport.addStepDetails("VerifyDataPopulatedInInvoiceListPage", "All data of the Invoice will be validated",
						"Unable to validate Supplier" +" ", "Fail", "N");
			}
		}
		else
		{
			pdfResultReport.addStepDetails("VerifyDataPopulatedInInvoiceListPage", "All data of the Invoice will be validated",
					"Unable to validate Invoice Number" +" ", "Fail", "N");
		}
		
		pdfResultReport.addStepDetails("VerifyDataPopulatedInInvoiceListPageAfterPartialPayment", "All data of the Invoice will be validated",
				"Successfully validated all Invoice Data" + " ", "Pass", "Y");
		log.info("completed executing the method:: VerifyDataPopulatedInInvoiceListPageAfterPartialPayment");
	} catch (Exception e) {
		log.fatal("Unable to validate Invoice Data" + e.getMessage());
		pdfResultReport.addStepDetails("VerifyDataPopulatedInInvoiceListPage", "All data of the Invoice will be validated",
				"Unable to validate Invoice Data" + e.getMessage(), "Fail", "N");

		Assert.fail("Failed Due to " + e.getMessage());
	}
}
public void VerifyShowList() throws Throwable {
	try {
		log.info("started executing the method:: VerifyShowList");
		String Amt="45,015.00";
		JSClick(tendercreationlocators.ShowList(Amt), "ShowList");
		etendercomponentobj.waitForSpinnerToDisappear();
		waitForObj(5000);
		List<WebElement> elem =ThreadLocalWebdriver.getDriver().findElements(By.xpath("//td[@class='ng-binding']"));
		for(WebElement ele: elem)
		{
			JavascriptExecutor je=(JavascriptExecutor)ThreadLocalWebdriver.getDriver();
			je.executeScript("arguments[0].style.border='3px solid blue'", ele);
			waitForObj(2000);
		}
		pdfResultReport.addStepDetails("ShowList", "Should verify the Showlist fields",
				"Successfully verified Shwo List fields" + " ", "Pass", "Y");
		JSClick(tendercreationlocators.btnOk, "btnOk");
		etendercomponentobj.waitForSpinnerToDisappear();
		
		log.info("completed executing the method:: VerifyShowList");
	} catch (Exception e) {
		log.fatal("Unable to verify ShowList fields" + e.getMessage());
		pdfResultReport.addStepDetails("VerifyShowList", "Should verify the Showlist fields",
				"Unable to verify ShowList fields" + e.getMessage(), "Fail", "N");

		Assert.fail("Failed Due to " + e.getMessage());
	}
}
public void clickItemDetailsAndValiateItemCodes() throws Throwable {
	try {
		log.info("started executing the method:: clickItemDetailsAndValiateItemCodes");
		
		click(tendercreationlocators.itemDetailsTab,"ItemDetailsTab");
		waitForObj(2000);
		String itemCode1Actual = "", itemCode2Actual = "", itemCode3Actual = "",
				itemCode1ActualValue = "", itemCode2ActualValue = "", itemCode3ActualValue = "";
		
		String itemCodeTxt = null;
		String itemCodeValue = null;
		int i = 1;
		for( ;i<=3; i++) {
			itemCodeTxt = ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.itemCodeList(i)).getText().trim();
			itemCodeValue = ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.acceptedQuantity(i)).getText().trim();
			if(itemCodeTxt.equals("Item Code_1")) { 
				itemCode1Actual = itemCodeTxt;
				itemCode1ActualValue = itemCodeValue;
				itemCode1IndexValue = i;
				
			}else if(itemCodeTxt.equals("Item Code_2")) {
				itemCode2Actual = itemCodeTxt;
				itemCode2ActualValue = itemCodeValue;
				itemCode2IndexValue = i;
				
			}else if(itemCodeTxt.equals("Item Code_3")){
				itemCode3Actual = itemCodeTxt;
				itemCode3ActualValue = itemCodeValue;
				itemCode3IndexValue = i;
			}
		}
		System.out.println("ItemCode1Text is : "+itemCode1Actual+" and value is : " +itemCode1ActualValue+"\n"+
		"ItemCode2Text is : "+itemCode2Actual+" and value is : "+itemCode2ActualValue+"\n"+
		"ItemCode3Text is : "+itemCode3Actual+" and value is : "+itemCode3ActualValue);
			
		Assert.assertEquals(itemCode1ActualValue, etendercomponentobj.getDataFromPropertiesFile("itemCode_1AcceptedQuantityValue"));
		Assert.assertEquals(itemCode2ActualValue, etendercomponentobj.getDataFromPropertiesFile("itemCode_2AcceptedQuantityValue"));
		Assert.assertEquals(itemCode3ActualValue, etendercomponentobj.getDataFromPropertiesFile("itemCode_3AcceptedQuantityValue"));
		
		String itemCode1Value = eTenderComponent.getDataFromPropertiesFile("itemCode_1AcceptedQuantityValue");
		String itemCode2Value = eTenderComponent.getDataFromPropertiesFile("itemCode_2AcceptedQuantityValue");
		String itemCode3Value = eTenderComponent.getDataFromPropertiesFile("itemCode_3AcceptedQuantityValue");
		float itemCode1Valuef = Float.parseFloat(itemCode1Value);
		float itemCode2Valuef = Float.parseFloat(itemCode2Value);
		float itemCode3Valuef = Float.parseFloat(itemCode3Value);
		lowestItemValue=0;
		lowestIndex=0;
		if((itemCode1Valuef < itemCode2Valuef) && (itemCode1Valuef < itemCode3Valuef)) {
			lowestItemValue = itemCode1Valuef;
			lowestIndex = itemCode1IndexValue;
			
		} else if((itemCode2Valuef < itemCode1Valuef) && (itemCode2Valuef < itemCode3Valuef)) {
			lowestItemValue = itemCode2Valuef;
			lowestIndex = itemCode2IndexValue;
			
		} else if((itemCode3Valuef < itemCode1Valuef) && (itemCode3Valuef < itemCode2Valuef)) {
			lowestItemValue = itemCode3Valuef;
			lowestIndex = itemCode3IndexValue;
		}
		
		//float highestItemValue = lowestItemValue+3, leastItemValue = lowestItemValue-2;
		String highestItemValue = Float.toString(lowestItemValue+3),
				leastItemValue = Float.toString(lowestItemValue-2);
		lowestItemValueTxt = Float.toString(lowestItemValue);
		click(tendercreationlocators.invoiceQuantityToBeRaised(lowestIndex),"Invoice Quantity");
		clear(tendercreationlocators.invoiceQuantityToBeRaised(lowestIndex),"TextBox");
		set(tendercreationlocators.invoiceQuantityToBeRaised(lowestIndex),highestItemValue,"Invoice Quantity");
		pdfResultReport.addStepDetails("clickItemDetailsAndValiateItemCodes", "Itemcode details are validated successfully",
				"Successfully validated Itemcode details are validated" + " ", "Pass", "Y");
		waitForObj(2000);
		clear(tendercreationlocators.invoiceQuantityToBeRaised(lowestIndex),"TextBox");
		set(tendercreationlocators.invoiceQuantityToBeRaised(lowestIndex),leastItemValue,"Invoice Quantity");
		pdfResultReport.addStepDetails("clickItemDetailsAndValiateItemCodes", "Itemcode details are validated successfully",
				"Successfully validated Itemcode details are validated" + " ", "Pass", "Y");
		waitForObj(2000);
		pdfResultReport.addStepDetails("clickItemDetailsAndValiateItemCodes", "Itemcode details are validated successfully",
				"Successfully validated Itemcode details are validated" + " ", "Pass", "Y");
	log.info("completed executing the method:: clickItemDetailsAndValiateItemCodes");
} catch (Exception e) {
	log.fatal("Unable validated Itemcode details" + e.getMessage());
	pdfResultReport.addStepDetails("clickItemDetailsAndValiateItemCodes", "Itemcode details are validated successfully",
			"Unable validated Itemcode details" + e.getMessage(), "Fail", "N");

	Assert.fail("Failed Due to " + e.getMessage());
}

}
public void validateViewPODetailsInDisputeInvoiceItemCode() throws Throwable {
	try {
		log.info("started executing the method:: validateViewPODetailsInDisputeInvoiceItemCode");
		click(tendercreationlocators.itemCodeActionBtn(lowestIndex),"Action Btn");
		waitForElementToBeVisible(tendercreationlocators.itemCodeViewPO);
		click(tendercreationlocators.itemCodeViewPO,"View Po");
		waitForElementToBeVisible(tendercreationlocators.PODetail);
		waitForObj(2000);
		
		String poNumText =  ThreadLocalWebdriver.getDriver().
				findElement(By.xpath("//b[text()='PO No']/../../following-sibling::td//span")).getText();
		Assert.assertEquals(poNumText,eTenderComponent.getDataFromPropertiesFile("poDocNum"));
		highlight(ThreadLocalWebdriver.getDriver().
				findElement(By.xpath("//b[text()='PO No']/../../following-sibling::td//span")));
		
		String poRefNo =  ThreadLocalWebdriver.getDriver().
				findElement(By.xpath("//b[text()='PO Ref No']/../../following-sibling::td//span")).getText();
		Assert.assertEquals(poRefNo,eTenderComponent.getDataFromPropertiesFile("POReferenceNum"));
		highlight(ThreadLocalWebdriver.getDriver().
				findElement(By.xpath("//b[text()='PO Ref No']/../../following-sibling::td//span")));
		
		waitForObj(2000);
		click(tendercreationlocators.closePOView,"close btn");
		waitForElementToBeVisible(tendercreationlocators.itemCodeActionBtn(itemCode1IndexValue));
		
		
		pdfResultReport.addStepDetails("validateViewPODetailsInDisputeInvoiceItemCode", "View Po details are validated successfully",
				"Successfully validated View Po details are validated" + " ", "Pass", "Y");
		log.info("completed executing the method:: validateViewPODetailsInDisputeInvoiceItemCode");
	} catch (Exception e) {
		log.fatal("Unable validated View Po details" + e.getMessage());
		pdfResultReport.addStepDetails("validateViewPODetailsInDisputeInvoiceItemCode", "View Po details are validated successfully",
				"Unable validated View Po details" + e.getMessage(), "Fail", "N");

		Assert.fail("Failed Due to " + e.getMessage());
	}
}
public void verifyGrnDataIsCorrectOrNot() throws Throwable {
	try {
		log.info("started executing the method:: verifyGrnDataIsCorrectOrNot");

		waitForObj(2000);
		
		
		WebElement poNumElement = ThreadLocalWebdriver.getDriver().
				findElement(By.xpath("//th[text()='PO Number(s)']/../td//a"));
		String poNum = poNumElement.getText().trim();
		Assert.assertEquals(poNum, eTenderComponent.getDataFromPropertiesFile("poDocNum"));
		highlight(poNumElement);
		
		WebElement vendorOrg = ThreadLocalWebdriver.getDriver().
				findElement(By.xpath("//th[text()='Vendor org name']/following-sibling::td"));
		String vendorOrgName = vendorOrg.getText().trim();
		Assert.assertEquals(vendorOrgName, eTenderComponent.getDataFromPropertiesFile("Bidder2"));
		highlight(vendorOrg);
		
		WebElement ShipmentNo = ThreadLocalWebdriver.getDriver().
				findElement(By.xpath("(//th[text()='Shipment No.']/following-sibling::td)[1]"));
		String ShipmentNoText = ShipmentNo.getText().trim();
		Assert.assertEquals(ShipmentNoText, eTenderComponent.getDataFromPropertiesFile("ASNShippingTracking"));
		highlight(ShipmentNo);
		
		int i=lowestIndex;
		
		WebElement asnNo = ThreadLocalWebdriver.getDriver().
				findElement(By.xpath("(//th[text()='ASN No.']/following-sibling::td)[1]"));
		String asnNoText = asnNo.getText().trim();
		Assert.assertEquals(asnNoText, eTenderComponent.getDataFromPropertiesFile("ASNNum"));
		highlight(asnNo);
		
		
		waitForObj(2000);

		pdfResultReport.addStepDetails("verifyGrnDataIsCorrectOrNot",
				"Grn data Should be Validated",
				"Sucessfully checked Grn details In Pop Up" + " ", "Pass", "Y");
		log.info("completed executing the method:: verifyViewGrnSavedDetailsInPopUp");
	} catch (Exception e) {
		log.fatal("failed to check Grn details In Pop Up " + e.getMessage());
		pdfResultReport.addStepDetails("verifyViewGrnSavedDetailsInPopUp",
				"Grn data Should be Validated",
				"failed to check Grn details In Pop Up " + e.getMessage(), "Fail", "N");
		Assert.fail("Failed Due to " + e.getMessage());
	}
}
public void submitInTheDisputedInVoice() throws Throwable {
	try {
	log.info("started executing the method:: submitTheDisputedInVoice");

	waitForObj(2000);

	click(tendercreationlocators.submitbutton,"SubmitButton");
	waitForElementToBeVisible(tendercreationlocators.disputedInvoicePaymentListTitle);
	waitForObj(2000);

	pdfResultReport.addStepDetails("submitTheDisputedInVoice", "Should Submit Disputed Invoice",
	"Successfully Submitted Disputed Invoicer" + " ", "Pass", "Y");


	log.info("completed executing the method:: submitTheDisputedInVoice");
	} catch (Exception e) {
	log.fatal("Unable to Submit Disputed Invoice" + e.getMessage());
	pdfResultReport.addStepDetails("submitTheDisputedInVoice", "Should Submit Disputed Invoice",
	"Unable to Submit Disputed Invoice" + e.getMessage(), "Fail", "N");

	Assert.fail("Failed Due to " + e.getMessage());
	}
	}
public void VerifyDueandOVerDueFilters() throws Throwable {
	try {
		log.info("started executing the method:: VerifyDueandOVerDueFilters");
		select(tendercreationlocators.StatusFilter, "Due");
		waitForObj(5000);
		pdfResultReport.addStepDetails("VerifyDueandOVerDueFilters", "Invoices with Status as Due should be displayed",
				"Successfully displayed Invoices with status as Due" + " ", "Pass", "Y");
		select(tendercreationlocators.StatusFilter, "Overdue");
		waitForObj(5000);
		pdfResultReport.addStepDetails("VerifyDueandOVerDueFilters", "Invoices with Status as OverDue should be displayed",
				"Successfully displayed Invoices with status as OverDue" + " ", "Pass", "Y");
		
		log.info("completed executing the method:: VerifyDueandOVerDueFilters");
	} catch (Exception e) {
		log.fatal("Unable to display InVoice Home Page" + e.getMessage());
		pdfResultReport.addStepDetails("VerifyDueandOVerDueFilters", "InVoice ith Status as Due and OverDue should be displayed",
				"Unable to display Due and OverDue Status" + e.getMessage(), "Fail", "N");

		Assert.fail("Failed Due to " + e.getMessage());
	}
}
public void clickOnRaiseInvoiceIcon() throws Throwable {
	try {
		log.info("started executing the method:: clickOnRaiseInvoiceIcon");
		click(tendercreationlocators.raiseInovice, "raiseInovice");
		waitForElementToBeVisible(tendercreationlocators.itemDetailsTab);
		waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
		pdfResultReport.addStepDetails("clickOnRaiseInvoiceIcon", "Should navigate to Raise Invoice page",
				"Successfully navigated to Raise Invoice page" + " ", "Pass", "Y");
		log.info("completed executing the method:: clickOnRaiseInvoiceIcon");
	} catch (Exception e) {
		log.fatal("Unable to navigate to Raise Invoice page" + e.getMessage());
		pdfResultReport.addStepDetails("clickOnRaiseInvoiceIcon", "Should navigate to Raise Invoice page",
				"Unable to navigate to Raise Invoice page" + e.getMessage(), "Fail", "N");
		Assert.fail("Failed Due to " + e.getMessage());
	}	}
public void invoiceTabValidation() throws Throwable {
	try {
		log.info("started executing the method:: invoiceTabValidation");
		waitForElementToBeVisible(By.xpath("//input[@id='.legacy_po_no.']"));
		String PONumber = "//input[@id='.legacy_po_no.']";
		WebElement PoNo = ThreadLocalWebdriver.getDriver().findElement(By.xpath(PONumber));
		String ActualPO = PoNo.getAttribute("title").trim();
		Assert.assertTrue(ActualPO.equals(eTenderComponent.getDataFromPropertiesFile("poDocNum")), "Po's are same");
		highlight(By.xpath(PONumber));
		
		LocalDateTime localdatetime =LocalDateTime.now();
		String currentDate = localdatetime.format(DateTimeFormatter.ofPattern("dd-MM-yy"));
		
		String SellerInvoiceNo = "//input[@id='.invoice_no.']";
		WebElement SellerInvNo = ThreadLocalWebdriver.getDriver().findElement(By.xpath(SellerInvoiceNo));
		String actualSellerInvoiceNo = SellerInvNo.getAttribute("title").trim();
		Assert.assertTrue(actualSellerInvoiceNo.equals(eTenderComponent.getDataFromPropertiesFile("SellerInvoiceNumber")), "seller invoice numbers are same");
		highlight(By.xpath(SellerInvoiceNo));
		waitForObj(2000);
		
		LocalDateTime localdatetime1 = LocalDateTime.now();
		String InvoiceDate = localdatetime1.format(DateTimeFormatter.ofPattern("dd-MM-[yy]"));
		click(tendercreationlocators.invoiceDate, "invoiceDate");
		clear(tendercreationlocators.invoiceDate, "invoiceDate");
		set(tendercreationlocators.invoiceDate, InvoiceDate, "invoiceDate");
		pdfResultReport.addStepDetails("invoiceTabValidation", "Inovice Tab must be validate sucessfully",
				"Successfully validated Invoice Tab" + " ", "Pass", "Y");
		log.info("completed executing the method:: invoiceTabValidation");
	} catch (Exception e) {
		log.fatal("Unable to validate Invoice Tab" + e.getMessage());
		pdfResultReport.addStepDetails("invoiceTabValidation", "Invoice Tab must be validate sucessfully",
				"Unable to validate Invoice Tab" + e.getMessage(), "Fail", "N");
		Assert.fail("Failed Due to " + e.getMessage());
	}	}
public void itemDetailsTabValidation() throws Throwable {
	try {
		log.info("started executing the method:: itemDetailsTabValidation");
		click(tendercreationlocators.itemDetailsTab, "itemDetailsTab");
		waitForObj(5000);
		String itemCode1Actual = "", itemCode2Actual = "", itemCode3Actual = "",
				itemCode1ActualValue = "", itemCode2ActualValue = "", itemCode3ActualValue = "";
		
		String itemCodeTxt = null;
		String itemCodeValue = null;
		int i = 1;
		for( ;i<=3; i++) {
			itemCodeTxt = ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.itemCodeList(i)).getText().trim();
			itemCodeValue = ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.acceptedQuantity(i)).getText().trim();
			if(itemCodeTxt.equals("Item Code_1")) { 
				itemCode1Actual = itemCodeTxt;
				itemCode1ActualValue = itemCodeValue;
				itemCode1IndexValue = i;
				
			}else if(itemCodeTxt.equals("Item Code_2")) {
				itemCode2Actual = itemCodeTxt;
				itemCode2ActualValue = itemCodeValue;
				itemCode2IndexValue = i;
				
			}else if(itemCodeTxt.equals("Item Code_3")){
				itemCode3Actual = itemCodeTxt;
				itemCode3ActualValue = itemCodeValue;
				itemCode3IndexValue = i;
			}
		}
		System.out.println("ItemCode1Text is : "+itemCode1Actual+" and value is : " +itemCode1ActualValue+"\n"+
		"ItemCode2Text is : "+itemCode2Actual+" and value is : "+itemCode2ActualValue+"\n"+
		"ItemCode3Text is : "+itemCode3Actual+" and value is : "+itemCode3ActualValue);
		pdfResultReport.addStepDetails("itemDetailsTabValidation", "Item details Tab must be validate sucessfully",
				"Successfully validated Item details Tab" + " ", "Pass", "Y");
		log.info("completed executing the method:: itemDetailsTabValidation");
	} catch (Exception e) {
		log.fatal("Unable to validate Item details Tab" + e.getMessage());
		pdfResultReport.addStepDetails("itemDetailsTabValidation", "Item details Tab must be validate sucessfully",
				"Unable to validate Item details Tab" + e.getMessage(), "Fail", "N");
		Assert.fail("Failed Due to " + e.getMessage());
	}	
	}
public void verifyGRNInInvoiceListForPayment() throws Throwable {
	try {
		log.info("started executing the method:: verifyGRNInInvoiceListForPayment");
		JSClick(tendercreationlocators.invoiceActiondrpdwn(eTenderComponent.getDataFromPropertiesFile("InVoice_Number")), "invoiceActiondrpdwn");
		waitForObj(2000);
		JSClick(tendercreationlocators.ViewInvoiceGRN(eTenderComponent.getDataFromPropertiesFile("InVoice_Number")), "ViewInvoiceGRN");
		waitForElementToBeVisible(By.xpath("//th[text()='PO Number(s)']/../td//a"));
		etendercomponentobj.waitForSpinnerToDisappear();
		waitForObj(3000);
		WebElement poNumElement = ThreadLocalWebdriver.getDriver().
				findElement(By.xpath("//th[text()='PO Number(s)']/../td//a"));
		String poNum = poNumElement.getText().trim();
		Assert.assertEquals(poNum, eTenderComponent.getDataFromPropertiesFile("poDocNum"));
		highlight(poNumElement);
		
		WebElement vendorOrg = ThreadLocalWebdriver.getDriver().
				findElement(By.xpath("//th[text()='Vendor org name']/following-sibling::td"));
		String vendorOrgName = vendorOrg.getText().trim();
		Assert.assertEquals(vendorOrgName, eTenderComponent.getDataFromPropertiesFile("Bidder2"));
		highlight(vendorOrg);
		
		WebElement ShipmentNo = ThreadLocalWebdriver.getDriver().
				findElement(By.xpath("(//th[text()='Shipment No.']/following-sibling::td)[1]"));
		String ShipmentNoText = ShipmentNo.getText().trim();
		Assert.assertEquals(ShipmentNoText, eTenderComponent.getDataFromPropertiesFile("ASNShippingTracking"));
		highlight(ShipmentNo);
		int i=lowestIndex;
		WebElement asnNo = ThreadLocalWebdriver.getDriver().
				findElement(By.xpath("(//th[text()='ASN No.']/following-sibling::td)[1]"));
		String asnNoText = asnNo.getText().trim();
		Assert.assertEquals(asnNoText, eTenderComponent.getDataFromPropertiesFile("ASNNum"));
		highlight(asnNo);
		waitForObj(2000);
		pdfResultReport.addStepDetails("verifyGRNInInvoiceListForPayment",
				"Grn data Should be Validated",
				"Sucessfully checked Grn details In Pop Up" + " ", "Pass", "Y");
		log.info("completed executing the method:: verifyGRNInInvoiceListForPayment");
	} catch (Exception e) {
		log.fatal("failed to check Grn details In Pop Up " + e.getMessage());
		pdfResultReport.addStepDetails("verifyGRNInInvoiceListForPayment",
				"Grn data Should be Validated",
				"failed to check Grn details In Pop Up " + e.getMessage(), "Fail", "N");
		Assert.fail("Failed Due to " + e.getMessage());
	}
}
public void verifyPOInInvoiceListForPayment() throws Throwable {
	try {
		log.info("started executing the method:: verifyPOInInvoiceListForPayment");
		JSClick(tendercreationlocators.invoiceActiondrpdwn(eTenderComponent.getDataFromPropertiesFile("InVoice_Number")), "invoiceActiondrpdwn");
		waitForObj(2000);
		JSClick(tendercreationlocators.ViewInvoicePO(eTenderComponent.getDataFromPropertiesFile("InVoice_Number")), "ViewInvoicePO");
		waitForElementToBeVisible(tendercreationlocators.PODetail);
		etendercomponentobj.waitForSpinnerToDisappear();
		waitForObj(2000);
		String poNumText =  ThreadLocalWebdriver.getDriver().
				findElement(By.xpath("//b[text()='PO No']/../../following-sibling::td//span")).getText();
		Assert.assertEquals(poNumText,eTenderComponent.getDataFromPropertiesFile("poDocNum"));
		highlight(ThreadLocalWebdriver.getDriver().
				findElement(By.xpath("//b[text()='PO No']/../../following-sibling::td//span")));
		
		String poRefNo =  ThreadLocalWebdriver.getDriver().
				findElement(By.xpath("//b[text()='PO Ref No']/../../following-sibling::td//span")).getText();
		Assert.assertEquals(poRefNo,eTenderComponent.getDataFromPropertiesFile("POReferenceNum"));
		highlight(ThreadLocalWebdriver.getDriver().
				findElement(By.xpath("//b[text()='PO Ref No']/../../following-sibling::td//span")));
		waitForObj(3000);
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
		pdfResultReport.addStepDetails("verifyPOInInvoiceListForPayment", "PO Details will be displayed",
				"Successfully displayed PO Details" + " ", "Pass", "Y");
		log.info("completed executing the method:: verifyPOInInvoiceListForPayment");
		JSClick(tendercreationlocators.ClosePoDetails,"ClosePoDetails");
		waitForObj(5000);
	} catch (Exception e) {
		log.fatal("Unable to display PO Details" + e.getMessage());
		pdfResultReport.addStepDetails("verifyPOInInvoiceListForPayment", "PO Details will be displayed",
				"Unable to display PO Details" + e.getMessage(), "Fail", "N");
		Assert.fail("Failed Due to " + e.getMessage());
	}
}


public void VerifyAmountsValidation() throws Throwable {
	try {
		log.info("started executing the method:: VerifyAmountsValidation");
		click(tendercreationlocators.AmtToBePaid, "AmtToBePaid");
		ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.AmtToBePaid).sendKeys(Keys.DELETE);
        String BalanceUI=text(tendercreationlocators.Balance);
        String TotalUI=text(tendercreationlocators.Total);
        if(BalanceUI.equals("40,000.00") && TotalUI.equals("5,015.00"))
        {
        	highlight(tendercreationlocators.Balance);
        	waitForObj(2000);
        	highlight(tendercreationlocators.Total);
        }
        else
        {
        	pdfResultReport.addStepDetails("VerifyAmounts", "Should verify Balance and Total",
					"Successfully verified Balance and Total" + " ", "Fail", "Y");
        	ThreadLocalWebdriver.getDriver().close();
        }
        
		waitForObj(2000);
		pdfResultReport.addStepDetails("VerifyAmountsValidation", "Should verify the Amount fields",
				"Successfully verified Amount fields" + " ", "Pass", "Y");
		log.info("completed executing the method:: VerifyAmountsValidation");
	} catch (Exception e) {
		log.fatal("Unable to verify all the Payment Options" + e.getMessage());
		pdfResultReport.addStepDetails("VerifyAmountsValidation", "Should verify all the Payment Options",
				"Unable to verify all the Payment Options" + e.getMessage(), "Fail", "N");

		Assert.fail("Failed Due to " + e.getMessage());
	}
}
public void VerifyDataPopulatedInInvoiceListPageAfterPartialPaymentVal() throws Throwable {
	try {
		log.info("started executing the method:: VerifyDataPopulatedInInvoiceListPageAfterPartialPaymentVal");
		String InvoiceNo =eTenderComponent.getDataFromPropertiesFile("InVoice_Number");
		String InvoiceNoUI=text(tendercreationlocators.InvoiceNuUI(InvoiceNo));
		LocalDateTime localdatetime = LocalDateTime.now();

		String currentDateTime = localdatetime.format(DateTimeFormatter.ofPattern("dd-MM-yy"));
		String Amt="45,015.00";
		String BalanceAmt="40,000.00";
		String PAidAMt="5,015.00";
		String Status="Due";
		if(InvoiceNo.equals(InvoiceNoUI))
		{
			highlight(tendercreationlocators.InvoiceNuUI(InvoiceNo));
			waitForObj(2000);
			String SupplierUI=text(tendercreationlocators.SupplierUI);
			if(SupplierUI.equals("TCS"))
			{
				highlight(tendercreationlocators.SupplierUI);
				waitForObj(2000);
				String InvoicedDateUI=text(tendercreationlocators.InvoiceDateUI(currentDateTime));
				if(currentDateTime.equals(InvoicedDateUI))
				{
					highlight(tendercreationlocators.InvoiceDateUI(currentDateTime));
					waitForObj(2000);
					String InvoiceAmtUI=text(tendercreationlocators.InvoiceAmtUI(Amt));
					String BalanceAmtUI=text(tendercreationlocators.BalanceInvoiceAmtUI(BalanceAmt));
					String PaidAmtUI=text(tendercreationlocators.PaidAmt(Amt)).trim();
					
					if(InvoiceAmtUI.equals(Amt) && BalanceAmtUI.equals(BalanceAmt) && PaidAmtUI.equals(PAidAMt) )
					{
						highlight(tendercreationlocators.InvoiceAmtUI(Amt));
						waitForObj(2000);
						highlight(tendercreationlocators.BalanceInvoiceAmtUI(BalanceAmt));
						waitForObj(2000);
						highlight(tendercreationlocators.PaidAmt(Amt));
						waitForObj(2000);
						String StatusUI=text(tendercreationlocators.InvoiceStatusUI(Status));
						if(StatusUI.equals(Status))
						{
							highlight(tendercreationlocators.InvoiceStatusUI(Status));
							waitForObj(2000);
						}
						else
						{
							pdfResultReport.addStepDetails("VerifyDataPopulatedInInvoiceListPage", "All data of the Invoice will be validated",
									"Unable to validate Status" +" ", "Fail", "N");
						}
					}
					else
					{
						pdfResultReport.addStepDetails("VerifyDataPopulatedInInvoiceListPage", "All data of the Invoice will be validated",
								"Unable to validate Invoice and Balance Amount" +" ", "Fail", "N");
					}
				}
				else
				{
					pdfResultReport.addStepDetails("VerifyDataPopulatedInInvoiceListPage", "All data of the Invoice will be validated",
							"Unable to validate Invoice Date" +" ", "Fail", "N");
				}
			}
			else
			{
				pdfResultReport.addStepDetails("VerifyDataPopulatedInInvoiceListPage", "All data of the Invoice will be validated",
						"Unable to validate Supplier" +" ", "Fail", "N");
			}
		}
		else
		{
			pdfResultReport.addStepDetails("VerifyDataPopulatedInInvoiceListPage", "All data of the Invoice will be validated",
					"Unable to validate Invoice Number" +" ", "Fail", "N");
		}
		
		pdfResultReport.addStepDetails("VerifyDataPopulatedInInvoiceListPageAfterPartialPaymentVal", "All data of the Invoice will be validated",
				"Successfully validated all Invoice Data" + " ", "Pass", "Y");
		log.info("completed executing the method:: VerifyDataPopulatedInInvoiceListPageAfterPartialPaymentVal");
	} catch (Exception e) {
		log.fatal("Unable to validate Invoice Data" + e.getMessage());
		pdfResultReport.addStepDetails("VerifyDataPopulatedInInvoiceListPageAfterPartialPaymentVal", "All data of the Invoice will be validated",
				"Unable to validate Invoice Data" + e.getMessage(), "Fail", "N");

		Assert.fail("Failed Due to " + e.getMessage());
	}
}

public void VerifyDataPopulatedInInvoiceListPage1() throws Throwable {
	try {
		log.info("started executing the method:: VerifyDataPopulatedInInvoiceListPage");
		String InvoiceNo =eTenderComponent.getDataFromPropertiesFile("InVoice_Number");
		String InvoiceNoUI=text(tendercreationlocators.InvoiceNuUI(InvoiceNo));
		LocalDateTime localdatetime = LocalDateTime.now();

		String currentDateTime = localdatetime.format(DateTimeFormatter.ofPattern("dd-MM-yy"));
		String Amt=totalInvoiceAmt;
		String Status="Due";
		if(InvoiceNo.equals(InvoiceNoUI))
		{
			highlight(tendercreationlocators.InvoiceNuUI(InvoiceNo));
			waitForObj(2000);
			String SupplierUI=text(tendercreationlocators.SupplierUI);
			if(SupplierUI.equals("TCS"))
			{
				highlight(tendercreationlocators.SupplierUI);
				waitForObj(2000);
				String InvoicedDateUI=text(tendercreationlocators.InvoiceDateUI(currentDateTime));
				if(currentDateTime.equals(InvoicedDateUI))
				{
					highlight(tendercreationlocators.InvoiceDateUI(currentDateTime));
					waitForObj(2000);
					String InvoiceAmt=text(tendercreationlocators.InvoiceAmtUI(Amt));
					String BalanceAmt=text(tendercreationlocators.BalanceInvoiceAmtUI(Amt));
					
					if(InvoiceAmt.equals(Amt) && BalanceAmt.equals(Amt))
					{
						highlight(tendercreationlocators.InvoiceAmtUI(Amt));
						waitForObj(2000);
						highlight(tendercreationlocators.BalanceInvoiceAmtUI(Amt));
						waitForObj(2000);
						String StatusUI=text(tendercreationlocators.InvoiceStatusUI(Status));
						if(StatusUI.equals(Status))
						{
							highlight(tendercreationlocators.InvoiceStatusUI(Status));
							waitForObj(2000);
						}
						else
						{
							pdfResultReport.addStepDetails("VerifyDataPopulatedInInvoiceListPage", "All data of the Invoice will be validated",
									"Unable to validate Status" +" ", "Fail", "N");
						}
					}
					else
					{
						pdfResultReport.addStepDetails("VerifyDataPopulatedInInvoiceListPage", "All data of the Invoice will be validated",
								"Unable to validate Invoice and Balance Amount" +" ", "Fail", "N");
					}
				}
				else
				{
					pdfResultReport.addStepDetails("VerifyDataPopulatedInInvoiceListPage", "All data of the Invoice will be validated",
							"Unable to validate Invoice Date" +" ", "Fail", "N");
				}
			}
			else
			{
				pdfResultReport.addStepDetails("VerifyDataPopulatedInInvoiceListPage", "All data of the Invoice will be validated",
						"Unable to validate Supplier" +" ", "Fail", "N");
			}
		}
		else
		{
			pdfResultReport.addStepDetails("VerifyDataPopulatedInInvoiceListPage", "All data of the Invoice will be validated",
					"Unable to validate Invoice Number" +" ", "Fail", "N");
		}
		
		pdfResultReport.addStepDetails("VerifyDataPopulatedInInvoiceListPage", "All data of the Invoice will be validated",
				"Successfully validated all Invoice Data" + " ", "Pass", "Y");
		log.info("completed executing the method:: VerifyDataPopulatedInInvoiceListPage");
	} catch (Exception e) {
		log.fatal("Unable to validate Invoice Data" + e.getMessage());
		pdfResultReport.addStepDetails("VerifyDataPopulatedInInvoiceListPage", "All data of the Invoice will be validated",
				"Unable to validate Invoice Data" + e.getMessage(), "Fail", "N");

		Assert.fail("Failed Due to " + e.getMessage());
	}
}
int itemCode1IndexValueE=0, itemCode2IndexValueE=0, itemCode3IndexValueE=0, highestIndex;
float highestItemValue;
String highestItemValueTxt=null;
public void clickItemDetailsAndValiateItemCodesForExcessQuantity() throws Throwable {
try {
	log.info("started executing the method:: clickItemDetailsAndValiateItemCodesForExcessQuantity");
	
	click(tendercreationlocators.itemDetailsTab,"ItemDetailsTab");
	waitForObj(2000);
	String itemCode1Actual = "", itemCode2Actual = "", itemCode3Actual = "",
			itemCode1ActualValue = "", itemCode2ActualValue = "", itemCode3ActualValue = "";
	
	String itemCodeTxt = null;
	String itemCodeValue = null;
	int i = 1;
	for( ;i<=3; i++) {
		itemCodeTxt = ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.itemCodeList(i)).getText().trim();
		itemCodeValue = ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.acceptedQuantity(i)).getText().trim();
		if(itemCodeTxt.equals("Item Code_1")) { 
			itemCode1Actual = itemCodeTxt;
			itemCode1ActualValue = itemCodeValue;
			itemCode1IndexValue = i;
			
		}else if(itemCodeTxt.equals("Item Code_2")) {
			itemCode2Actual = itemCodeTxt;
			itemCode2ActualValue = itemCodeValue;
			itemCode2IndexValue = i;
			
		}else if(itemCodeTxt.equals("Item Code_3")){
			itemCode3Actual = itemCodeTxt;
			itemCode3ActualValue = itemCodeValue;
			itemCode3IndexValue = i;
		}
	}
	System.out.println("ItemCode1Text is : "+itemCode1Actual+" and value is : " +itemCode1ActualValue+"\n"+
	"ItemCode2Text is : "+itemCode2Actual+" and value is : "+itemCode2ActualValue+"\n"+
	"ItemCode3Text is : "+itemCode3Actual+" and value is : "+itemCode3ActualValue);
		
	Assert.assertEquals(itemCode1ActualValue, etendercomponentobj.getDataFromPropertiesFile("itemCode_1AcceptedQuantityValue"));
	Assert.assertEquals(itemCode2ActualValue, etendercomponentobj.getDataFromPropertiesFile("itemCode_2AcceptedQuantityValue"));
	Assert.assertEquals(itemCode3ActualValue, etendercomponentobj.getDataFromPropertiesFile("itemCode_3AcceptedQuantityValue"));
	
	String itemCode1Value = eTenderComponent.getDataFromPropertiesFile("itemCode_1AcceptedQuantityValue");
	String itemCode2Value = eTenderComponent.getDataFromPropertiesFile("itemCode_2AcceptedQuantityValue");
	String itemCode3Value = eTenderComponent.getDataFromPropertiesFile("itemCode_3AcceptedQuantityValue");
	float itemCode1Valuef = Float.parseFloat(itemCode1Value);
	float itemCode2Valuef = Float.parseFloat(itemCode2Value);
	float itemCode3Valuef = Float.parseFloat(itemCode3Value);
	highestItemValue=0;
	highestIndex=0;
	if((itemCode1Valuef > itemCode2Valuef) && (itemCode1Valuef > itemCode3Valuef)) {
		highestItemValue = itemCode1Valuef;
		highestIndex = itemCode1IndexValue;
		
	} else if((itemCode2Valuef > itemCode1Valuef) && (itemCode2Valuef > itemCode3Valuef)) {
		highestItemValue = itemCode2Valuef;
		highestIndex = itemCode2IndexValue;
		
	} else if((itemCode3Valuef > itemCode1Valuef) && (itemCode3Valuef > itemCode2Valuef)) {
		highestItemValue = itemCode3Valuef;
		highestIndex = itemCode3IndexValue;
	}
	
	waitForObj(2000);
	pdfResultReport.addStepDetails("clickItemDetailsAndValiateItemCodesForExcessQuantity", "Itemcode details are validated successfully",
			"Successfully validated Itemcode details are validated" + " ", "Pass", "Y");
log.info("completed executing the method:: clickItemDetailsAndValiateItemCodesForExcessQuantity");
} catch (Exception e) {
log.fatal("Unable validated Itemcode details" + e.getMessage());
pdfResultReport.addStepDetails("clickItemDetailsAndValiateItemCodesForExcessQuantity", "Itemcode details are validated successfully",
		"Unable validated Itemcode details" + e.getMessage(), "Fail", "N");

Assert.fail("Failed Due to " + e.getMessage());
}

}
public void ZeroValueItemValidation() throws Throwable {
try {
	log.info("started executing the method:: ZeroValueItemValidation");
	
	click(tendercreationlocators.itemCodeActionBtn(highestIndex),"Action Btn");
	waitForElementToBeVisible(tendercreationlocators.zeroValueItem);
	IsElementPresent(tendercreationlocators.zeroValueItem);
	highlight(tendercreationlocators.zeroValueItem);
	pdfResultReport.addStepDetails("ZeroValueItemValidation", "Should validate all the zero value item validations",
			"Successfully validated all the zero value item validations" + " ", "Pass", "Y");
	waitForObj(2000);
	click(tendercreationlocators.zeroValueItem,"Zero Value Item");
	waitForElementToBeVisible(tendercreationlocators.declarationOfFreeItem);
	waitForObj(2000);
	IsElementPresent(tendercreationlocators.whyExtra);
	highlight(tendercreationlocators.whyExtra);
	waitForObj(2000);
	IsElementPresent(tendercreationlocators.quantity);
	highlight(tendercreationlocators.quantity);
	waitForObj(2000);
	IsElementPresent(tendercreationlocators.previewAll_close);
	highlight(tendercreationlocators.previewAll_close);
	waitForObj(2000);
	IsElementPresent(tendercreationlocators.addZeroValueItem);
	waitForObj(2000);
	click(tendercreationlocators.previewAll_close,"close btn");
	waitForElementToBeVisible(tendercreationlocators.itemCodeActionBtn(highestIndex));
	waitForObj(2000);
	
	pdfResultReport.addStepDetails("ZeroValueItemValidation", "Should validate all the zero value item validations",
			"Successfully validated all the zero value item validations" + " ", "Pass", "Y");
	log.info("completed executing the method:: ZeroValueItemValidation");
} catch (Exception e) {
	log.fatal("Unable to validate all the zero value item validations" + e.getMessage());
	pdfResultReport.addStepDetails("ZeroValueItemValidation", "Should validate all the zero value item validations",
			"Unable to validate all the zero value item validations" + e.getMessage(), "Fail", "N");

	Assert.fail("Failed Due to " + e.getMessage());
}
}

public void addingNewRowToValidateZeroValueItemOption() throws Throwable {
try {
	log.info("started executing the method:: addingNewRowToValidateZeroValueItemOption");
	//before adding new row verify no. of rows
	List<WebElement> al = ThreadLocalWebdriver.getDriver().
			findElements(By.xpath("//button[@class='btn btn-primary dropdown-toggle']"));
	int rows = al.size();
	System.out.println(rows);
	pdfResultReport.addStepDetails("addingNewRowToValidateZeroValueItemOption", "Should validate all the zero value item validations",
			"Successfully validated all the zero value item validations" + " ", "Pass", "Y");
	
	//add new row without entering reason
	click(tendercreationlocators.itemCodeActionBtn(highestIndex),"Action Btn");
	waitForElementToBeVisible(tendercreationlocators.zeroValueItem);
	waitForObj(2000);
	click(tendercreationlocators.zeroValueItem,"Zero Value Item");
	waitForElementToBeVisible(tendercreationlocators.declarationOfFreeItem);
	click(tendercreationlocators.addZeroValueItem,"addZeroValueItem");
	String extraQuantity = ThreadLocalWebdriver.getDriver().
			findElement(By.xpath("//h4[@class='ng-binding']")).getText().trim();
	
	IsElementPresent(tendercreationlocators.addErrorMessage);
	highlight(tendercreationlocators.addErrorMessage);
	pdfResultReport.addStepDetails("addingNewRowToValidateZeroValueItemOption", "Should validate all the zero value item validations",
			"Successfully validated all the zero value item validations" + " ", "Pass", "Y");
	waitForObj(2000);
	
	//close the preview all page and verify no. of rows
	click(tendercreationlocators.previewAll_close,"close btn");
	waitForElementToBeVisible(tendercreationlocators.itemCodeActionBtn(highestIndex));
	al = ThreadLocalWebdriver.getDriver().
			findElements(By.xpath("//button[@class='btn btn-primary dropdown-toggle']"));
	int rowsClosed = al.size();
	System.out.println(rowsClosed);
	Assert.assertTrue(rows==rowsClosed);
	pdfResultReport.addStepDetails("addingNewRowToValidateZeroValueItemOption", "Should validate all the zero value item validations",
			"Successfully validated all the zero value item validations" + " ", "Pass", "Y");
	waitForObj(2000);
	
	//add a new row in item details tab
	click(tendercreationlocators.itemCodeActionBtn(highestIndex),"Action Btn");
	waitForElementToBeVisible(tendercreationlocators.zeroValueItem);
	waitForObj(2000);
	click(tendercreationlocators.zeroValueItem,"Zero Value Item");
	waitForElementToBeVisible(tendercreationlocators.declarationOfFreeItem);
	clear(tendercreationlocators.whyExtraTextBox,"TextBox");
	set(tendercreationlocators.whyExtraTextBox,"Gift","whyExtraTextBox");
	
	clear(tendercreationlocators.quantityTextBox,"TextBox");
	set(tendercreationlocators.quantityTextBox,extraQuantity,"TextBox");
	waitForObj(2000);
	click(tendercreationlocators.addZeroValueItem,"addZeroValueItem");
	waitForElementToBeVisible(tendercreationlocators.itemCodeActionBtn(highestIndex));
	waitForObj(2000);
	al = ThreadLocalWebdriver.getDriver().
			findElements(By.xpath("//button[@class='btn btn-primary dropdown-toggle']"));
	Assert.assertTrue(al.size()>rows);
	String excessQuantity ="";
	int indexValue=0;
	for(int i=1;i<=al.size();i++) {
		excessQuantity = 
				ThreadLocalWebdriver.getDriver().
				findElement(By.xpath("(//div[@class='ui-grid-cell ng-scope ui-grid-coluiGrid-000C text-right']/div[@title])["+i+"]")).getText().trim();
		double excessQuantityD = Double.parseDouble(excessQuantity);
		if(excessQuantityD>0.00) {
			indexValue = i;
		}
	}
	highlight(tendercreationlocators.excessQuantity(indexValue));
	highlight(tendercreationlocators.excessQuantity(indexValue+1));
	pdfResultReport.addStepDetails("addingNewRowToValidateZeroValueItemOption", "Should validate all the zero value item validations",
			"Successfully validated all the zero value item validations" + " ", "Pass", "Y");
	log.info("completed executing the method:: addingNewRowToValidateZeroValueItemOption");
} catch (Exception e) {
	log.fatal("Unable to validate all the zero value item validations" + e.getMessage());
	pdfResultReport.addStepDetails("addingNewRowToValidateZeroValueItemOption", "Should validate all the zero value item validations",
			"Unable to validate all the zero value item validations" + e.getMessage(), "Fail", "N");

	Assert.fail("Failed Due to " + e.getMessage());
}
}
String paidAmountS = "", balanceWithoutDiscount = "";
public void VerifyAmounts1() throws Throwable {
try {
	log.info("started executing the method:: VerifyAmounts");
	paidAmountS = "10000.00";
	String acutalInvoiceAmountS = invoiceAmount;
	DecimalFormat df = new DecimalFormat("##,###.00");
	double paidAmountF = Double.parseDouble(paidAmountS);
	
	click(tendercreationlocators.AmtToBePaid, "AmtToBePaid");
	clear(tendercreationlocators.AmtToBePaid, "AmtToBePaid");
	set(tendercreationlocators.AmtToBePaid,paidAmountS,"Amount to be paid");
	Double actualInvoiceAmount = Double.parseDouble(acutalInvoiceAmountS);
	Double discountAmount = ((5/100d)*actualInvoiceAmount);
	Double balance = (actualInvoiceAmount-discountAmount)-paidAmountF,
			balanceAmount = (actualInvoiceAmount-paidAmountF);
	balanceWithoutDiscount = Double.toString(balanceAmount);
	String expectedBalance = df.format(balance);
			//Double.toString(balance);
	String actualBalance = ThreadLocalWebdriver.getDriver().
			findElement(By.xpath("//th[text()='Balance']/../../following-sibling::tbody/tr/td[7]")).getText().trim();
	String paidAmount = ThreadLocalWebdriver.getDriver().
			findElement(By.xpath("//button[text()='Total']/../following-sibling::div/button")).getText().trim();
	Assert.assertEquals(expectedBalance, actualBalance);
	String paidAmountSExpected = df.format(paidAmountF);
	Assert.assertEquals(paidAmountSExpected, paidAmount);
	waitForObj(2000);
	pdfResultReport.addStepDetails("VerifyAmounts", "Should verify the Amount fields",
			"Successfully verified Amount fields" + " ", "Pass", "Y");
	log.info("completed executing the method:: VerifyAmounts");
} catch (Exception e) {
	log.fatal("Unable to verify all the Payment Options" + e.getMessage());
	pdfResultReport.addStepDetails("VerifyAmounts", "Should verify all the Payment Options",
			"Unable to verify all the Payment Options" + e.getMessage(), "Fail", "N");

	Assert.fail("Failed Due to " + e.getMessage());
}
}
public void verifyPaymentDetails() throws Throwable {
try {
	log.info("started executing the method:: verifyPaymentDetails");
	
	waitForElementToBeVisible(tendercreationlocators.showList);
	click(tendercreationlocators.showList,"showList");
	waitForElementToBeVisible(By.xpath("//h3[contains(text(),'Payment Details')]/../following-sibling::div//tr[2]"));
	
	String invoiceNum = ThreadLocalWebdriver.getDriver().
			findElement(By.xpath("//h3[contains(text(),'Payment Details')]/../following-sibling::div//tr[2]/td[1]")).getText().trim();
	Assert.assertTrue(invoiceNum.equals(eTenderComponent.getDataFromPropertiesFile("InVoice_Number")));
	
	String sellerInvoiceNum = ThreadLocalWebdriver.getDriver().
			findElement(By.xpath("//h3[contains(text(),'Payment Details')]/../following-sibling::div//tr[2]/td[2]")).getText().trim();
	Assert.assertTrue(sellerInvoiceNum.equals(eTenderComponent.getDataFromPropertiesFile("SellerInvoiceNumber")));
	
	String currentPaidAmount = ThreadLocalWebdriver.getDriver().
			findElement(By.xpath("//h3[contains(text(),'Payment Details')]/../following-sibling::div//tr[2]/td[5]")).getText().trim();
	String paidAmount = paidAmountS;
	double d = Double.parseDouble(paidAmount);
	DecimalFormat df = new DecimalFormat("##,###.00");
	paidAmount = df.format(d);
	Assert.assertTrue(currentPaidAmount.equals(paidAmount));
	pdfResultReport.addStepDetails("verifyPaymentDetails", "All data of the Invoice will be validated",
			"Successfully validated all Invoice Data" + " ", "Pass", "Y");
	
	click(tendercreationlocators.BtnOk,"OkButton");
	etendercomponentobj.waitForSpinnerToDisappear();
	waitForObj(3000);
	
	String balance = balanceWithoutDiscount;
	double balanceD = Double.parseDouble(balance);
	balance = df.format(balanceD);
	
	String balanceAmount = ThreadLocalWebdriver.getDriver().
			findElement(By.xpath("//th[text()='Balance']/../../following-sibling::tbody/tr/td[8]")).getText().trim();
	Assert.assertTrue(balanceAmount.equals(balance));
	
	pdfResultReport.addStepDetails("verifyPaymentDetails", "All data of the Invoice will be validated",
			"Successfully validated all Invoice Data" + " ", "Pass", "Y");
	log.info("completed executing the method:: verifyPaymentDetails");
} catch (Exception e) {
	log.fatal("Unable to validate Invoice Data" + e.getMessage());
	pdfResultReport.addStepDetails("verifyPaymentDetails", "All data of the Invoice will be validated",
			"Unable to validate Invoice Data" + e.getMessage(), "Fail", "N");

	Assert.fail("Failed Due to " + e.getMessage());
}
}
String invoiceAmount = "";
public String invoiceAmountValue(){
	invoiceAmount = ThreadLocalWebdriver.getDriver().
		findElement(By.xpath("//label[contains(text(),'Total Invoice Amount')]/following-sibling::div//input")).
		getAttribute("title").trim();
//invoiceAmount = invoiceAmount.substring(0, 5);
System.out.println(invoiceAmount);

return invoiceAmount;
}
String totalInvoiceAmt = "";
public void clickAlterInvoiceTabAndValiate() throws Throwable {
try {
	log.info("started executing the method:: clickAlterInvoiceTab");
	waitForElementToBeVisible(tendercreationlocators.alterInvoiceIcon);
	click(tendercreationlocators.alterInvoiceIcon,"InvoiceButton");
	etendercomponentobj.waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
	waitForElementToBeVisible(By.xpath("//input[@id='.legacy_po_no.']"));
	IsElementPresent(tendercreationlocators.alterDisputedInvoice);
	String PONumber = "//input[@id='.legacy_po_no.']";
	WebElement PoNo = ThreadLocalWebdriver.getDriver().findElement(By.xpath(PONumber));
	String ActualPO = PoNo.getAttribute("title").trim();
	Assert.assertTrue(ActualPO.equals(eTenderComponent.getDataFromPropertiesFile("poDocNum")), "Po's are same");
	highlight(By.xpath(PONumber));
	
	LocalDateTime localdatetime =LocalDateTime.now();
	String currentDate = localdatetime.format(DateTimeFormatter.ofPattern("dd-MM-yy"));
	
	
	String SellerInvoiceNo = "//input[@id='.invoice_no.']";
	WebElement SellerInvNo = ThreadLocalWebdriver.getDriver().findElement(By.xpath(SellerInvoiceNo));
	String actualSellerInvoiceNo = SellerInvNo.getAttribute("title").trim();
	Assert.assertTrue(actualSellerInvoiceNo.equals(eTenderComponent.getDataFromPropertiesFile("SellerInvoiceNumber")), "seller invoice numbers are same");
	highlight(By.xpath(SellerInvoiceNo));
	waitForObj(2000);
	set(tendercreationlocators.invoiceDate,"currentDate","Invoice Date");
	pdfResultReport.addStepDetails("clickAlterInvoiceTab", "Navigated succesfully to Alter/Modify Disputed Invoice\n" + 
			"",
			"Successfully navigated to Alter/Modify Disputed Invoice\n" + 
			"" + " ", "Pass", "Y");
	log.info("completed executing the method:: clickAlterInvoiceTab");
} catch (Exception e) {
	log.fatal("Unable to navigate to Alter/Modify Disputed Invoice page" + e.getMessage());
	pdfResultReport.addStepDetails("clickAlterInvoiceTab", "Should navigate to Alter/Modify Disputed Invoice page",
			"Unable to navigate to Alter/Modify Disputed Invoice page" + e.getMessage(), "Fail", "N");

	Assert.fail("Failed Due to " + e.getMessage());
}
}
public void clickAlterInvoiceTabAndValiate1() throws Throwable {
try {
	log.info("started executing the method:: clickAlterInvoiceTab");
	waitForElementToBeVisible(tendercreationlocators.alterInvoiceIcon);
	click(tendercreationlocators.alterInvoiceIcon,"InvoiceButton");
	etendercomponentobj.waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
	waitForElementToBeVisible(By.xpath("//input[@id='.legacy_po_no.']"));
	IsElementPresent(tendercreationlocators.alterDisputedInvoice);
	String PONumber = "//input[@id='.legacy_po_no.']";
	WebElement PoNo = ThreadLocalWebdriver.getDriver().findElement(By.xpath(PONumber));
	String ActualPO = PoNo.getAttribute("title").trim();
	Assert.assertTrue(ActualPO.equals(eTenderComponent.getDataFromPropertiesFile("poDocNum")), "Po's are same");
	highlight(By.xpath(PONumber));
	
	LocalDateTime localdatetime =LocalDateTime.now();
	String currentDate = localdatetime.format(DateTimeFormatter.ofPattern("dd-MM-yy"));
	
	totalInvoiceAmt = ThreadLocalWebdriver.getDriver().
			findElement(By.xpath("//h2[contains(@ng-if,'totalInvoiceAmount')]/b"))
			.getText().trim();
	totalInvoiceAmt = totalInvoiceAmt.substring(0,totalInvoiceAmt.length()-2);
	
	String SellerInvoiceNo = "//input[@id='.invoice_no.']";
	WebElement SellerInvNo = ThreadLocalWebdriver.getDriver().findElement(By.xpath(SellerInvoiceNo));
	String actualSellerInvoiceNo = SellerInvNo.getAttribute("title").trim();
	Assert.assertTrue(actualSellerInvoiceNo.equals(eTenderComponent.getDataFromPropertiesFile("SellerInvoiceNumber")), "seller invoice numbers are same");
	highlight(By.xpath(SellerInvoiceNo));
	waitForObj(2000);
	set(tendercreationlocators.invoiceDate,"currentDate","Invoice Date");
	pdfResultReport.addStepDetails("clickAlterInvoiceTab", "Navigated succesfully to Alter/Modify Disputed Invoice\n" + 
			"",
			"Successfully navigated to Alter/Modify Disputed Invoice\n" + 
			"" + " ", "Pass", "Y");
	log.info("completed executing the method:: clickAlterInvoiceTab");
} catch (Exception e) {
	log.fatal("Unable to navigate to Alter/Modify Disputed Invoice page" + e.getMessage());
	pdfResultReport.addStepDetails("clickAlterInvoiceTab", "Should navigate to Alter/Modify Disputed Invoice page",
			"Unable to navigate to Alter/Modify Disputed Invoice page" + e.getMessage(), "Fail", "N");

	Assert.fail("Failed Due to " + e.getMessage());
}
}
public void VerifyDataPopulatedInInvoiceListPage2() throws Throwable {
	try {
		log.info("started executing the method:: VerifyDataPopulatedInInvoiceListPage");
		String InvoiceNo =eTenderComponent.getDataFromPropertiesFile("InVoice_Number");
		String InvoiceNoUI=text(tendercreationlocators.InvoiceNuUI(InvoiceNo));
		LocalDateTime localdatetime = LocalDateTime.now();

		String currentDateTime = localdatetime.format(DateTimeFormatter.ofPattern("dd-MM-yy"));
		DecimalFormat df = new DecimalFormat("##,###.00");
		String invoiceAmountS = invoiceAmount;
		double invoiceAmountD = Double.parseDouble(invoiceAmountS);
		String invoiceAmountExpected = df.format(invoiceAmountD);
		String Amt=invoiceAmountExpected;
		String Status="Due";
		if(InvoiceNo.equals(InvoiceNoUI))
		{
			highlight(tendercreationlocators.InvoiceNuUI(InvoiceNo));
			waitForObj(2000);
			String SupplierUI=text(tendercreationlocators.SupplierUI);
			if(SupplierUI.equals("TCS"))
			{
				highlight(tendercreationlocators.SupplierUI);
				waitForObj(2000);
				String InvoicedDateUI=text(tendercreationlocators.InvoiceDateUI(currentDateTime));
				if(currentDateTime.equals(InvoicedDateUI))
				{
					highlight(tendercreationlocators.InvoiceDateUI(currentDateTime));
					waitForObj(2000);
					String InvoiceAmt=text(tendercreationlocators.InvoiceAmtUI(Amt));
					String BalanceAmt=text(tendercreationlocators.BalanceInvoiceAmtUI(Amt));
					
					if(InvoiceAmt.equals(Amt) && BalanceAmt.equals(Amt))
					{
						highlight(tendercreationlocators.InvoiceAmtUI(Amt));
						waitForObj(2000);
						highlight(tendercreationlocators.BalanceInvoiceAmtUI(Amt));
						waitForObj(2000);
						String StatusUI=text(tendercreationlocators.InvoiceStatusUI(Status));
						if(StatusUI.equals(Status))
						{
							highlight(tendercreationlocators.InvoiceStatusUI(Status));
							waitForObj(2000);
						}
						else
						{
							pdfResultReport.addStepDetails("VerifyDataPopulatedInInvoiceListPage", "All data of the Invoice will be validated",
									"Unable to validate Status" +" ", "Fail", "N");
						}
					}
					else
					{
						pdfResultReport.addStepDetails("VerifyDataPopulatedInInvoiceListPage", "All data of the Invoice will be validated",
								"Unable to validate Invoice and Balance Amount" +" ", "Fail", "N");
					}
				}
				else
				{
					pdfResultReport.addStepDetails("VerifyDataPopulatedInInvoiceListPage", "All data of the Invoice will be validated",
							"Unable to validate Invoice Date" +" ", "Fail", "N");
				}
			}
			else
			{
				pdfResultReport.addStepDetails("VerifyDataPopulatedInInvoiceListPage", "All data of the Invoice will be validated",
						"Unable to validate Supplier" +" ", "Fail", "N");
			}
		}
		else
		{
			pdfResultReport.addStepDetails("VerifyDataPopulatedInInvoiceListPage", "All data of the Invoice will be validated",
					"Unable to validate Invoice Number" +" ", "Fail", "N");
		}
		
		pdfResultReport.addStepDetails("VerifyDataPopulatedInInvoiceListPage", "All data of the Invoice will be validated",
				"Successfully validated all Invoice Data" + " ", "Pass", "Y");
		log.info("completed executing the method:: VerifyDataPopulatedInInvoiceListPage");
	} catch (Exception e) {
		log.fatal("Unable to validate Invoice Data" + e.getMessage());
		pdfResultReport.addStepDetails("VerifyDataPopulatedInInvoiceListPage", "All data of the Invoice will be validated",
				"Unable to validate Invoice Data" + e.getMessage(), "Fail", "N");

		Assert.fail("Failed Due to " + e.getMessage());
	}
}
public void penalityDeductionsValidations() throws Throwable {
	try {
		log.info("started executing the method:: penalityDeductionsValidations");
		double invoiceApprovedAmount=0;
		String invoiceApprovedAmountVal = invoiceAmount;
		invoiceApprovedAmount = Double.parseDouble(invoiceApprovedAmountVal);
		invoiceApprovedAmount = invoiceApprovedAmount+100;
		String invoiceApprovedAmountS = Double.toString(invoiceApprovedAmount);
		click(tendercreationlocators.penalityDeduction,"penalityDeduction");
		clear(tendercreationlocators.penalityDeduction,"penalityDeduction");
		set(tendercreationlocators.penalityDeduction,invoiceApprovedAmountS,"penalityDeduction");
		
		JSClick(tendercreationlocators.inVoiceAcceptBy, "inVoiceAcceptBy");
		waitForElementToBeVisible(tendercreationlocators.invoiceValidationMessage);
		IsElementPresent(tendercreationlocators.invoiceValidationMessage);
		highlight(tendercreationlocators.invoiceValidationMessage);
		pdfResultReport.addStepDetails("penalityDeductionsValidations", "Should validate all penality deductions",
				"Successfully validated all penality deductions" + " ", "Pass", "Y");
		
		click(tendercreationlocators.confirmationOkBy,"OK Button");
		waitForElementToBeVisible(tendercreationlocators.penalityDeduction);
		waitForObj(2000);
		
		click(tendercreationlocators.approvedAmount,"approvedAmount");
		clear(tendercreationlocators.approvedAmount,"approvedAmount");
		set(tendercreationlocators.approvedAmount,"0","approvedAmount");
		clear(tendercreationlocators.penalityDeduction,"penalityDeduction");
		set(tendercreationlocators.penalityDeduction,invoiceApprovedAmountS,"penalityDeduction");
		JSClick(tendercreationlocators.inVoiceAcceptBy, "inVoiceAcceptBy");
		waitForElementToBeVisible(tendercreationlocators.invoiceValidationMessage);
		IsElementPresent(tendercreationlocators.invoiceValidationMessage);
		highlight(tendercreationlocators.invoiceValidationMessage);
		pdfResultReport.addStepDetails("penalityDeductionsValidations", "Should validate all penality deductions",
				"Successfully validated all penality deductions" + " ", "Pass", "Y");
		
		click(tendercreationlocators.confirmationOkBy,"OK Button");
		waitForElementToBeVisible(tendercreationlocators.penalityDeduction);
		waitForObj(2000);
		
		clear(tendercreationlocators.approvedAmount,"approvedAmount");
		set(tendercreationlocators.approvedAmount,invoiceApprovedAmountVal,"approvedAmount");
		clear(tendercreationlocators.penalityDeduction,"penalityDeduction");
		set(tendercreationlocators.penalityDeduction,"0","penalityDeduction");
		
		waitForElementToBeVisible(tendercreationlocators.inVoiceAcceptBy);
		
		waitForObj(2000);
		
		JSClick(tendercreationlocators.inVoiceAcceptBy, "inVoiceAcceptBy");
		
		
		pdfResultReport.addStepDetails("penalityDeductionsValidations", "Should validate all penality deductions",
				"Successfully validated all penality deductions" + " ", "Pass", "Y");
		log.info("completed executing the method:: penalityDeductionsValidations");
	} catch (Exception e) {
		log.fatal("Unable to verify all the penality deductions" + e.getMessage());
		pdfResultReport.addStepDetails("penalityDeductionsValidations", "Should verify all the penality deductions",
				"Unable to verify all the penality deductions" + e.getMessage(), "Fail", "N");

		Assert.fail("Failed Due to " + e.getMessage());
	}
}
public void searchPoRefNoInPoListPage1() throws Throwable {
		try {
			log.info("started executing the method:: searchThePoRefNoInPoListPage");

			clear(tendercreationlocators.search, "Clear the po ref no search field");

			set(tendercreationlocators.search, eTenderComponent.getDataFromPropertiesFile("POReferenceNum"),
					"po ref no search field");
			
			//String str = eTenderComponent.getDataFromPropertiesFile(poDocNum);

			
			IsElementPresent(tendercreationlocators.actionPONum(eTenderComponent.getDataFromPropertiesFile("poDocNum")));
			highlight(tendercreationlocators.actionPONum(eTenderComponent.getDataFromPropertiesFile("poDocNum")));
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
public void clickAcceptPoInDropDown1() throws Throwable {
		try {
			log.info("started executing the method:: clickAcceptPoInDropDown");

			click(tendercreationlocators.actionPONum(eTenderComponent.getDataFromPropertiesFile("poDocNum")), "actionMenuDropDownBy");

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
public void verifyGrnDataIsCorrectOrNot(String asnNo, String shipmentNo, String poNo, String vendorName) throws Throwable {
	try {
		log.info("started executing the method:: verifyGrnDataIsCorrectOrNot");

		waitForObj(2000);
		
		String POno = text(By.xpath("//th[text()='PO Number(s)']/../td//a")).trim();
		Assert.assertTrue(POno.equals(poNo));
		highlight(By.xpath("//th[text()='PO Number(s)']/../td//a"));
		
		String ShipmentTrackingNo = text(By.xpath("//th[text()='Shipment No.']/following-sibling::td")).trim();
		Assert.assertTrue(ShipmentTrackingNo.equals(shipmentNo));
		highlight(By.xpath("//th[text()='Shipment No.']/following-sibling::td"));
		
		String VendorName = text(By.xpath("//th[text()='Vendor org name']/following-sibling::td")).trim();
		Assert.assertTrue(VendorName.equals(vendorName));
		highlight(By.xpath("//th[text()='Vendor org name']/following-sibling::td"));
		
		String ASNno = text(By.xpath("//th[text()='ASN No.']/following-sibling::td")).trim();
		Assert.assertTrue(ASNno.equals(asnNo));
		highlight(By.xpath("//th[text()='ASN No.']/following-sibling::td"));
		
		
		String itemCode1AcceptedQuantity = text(tendercreationlocators.viewAcceptedQuantity(itemCode1IndexValue)).trim();
		Assert.assertTrue(itemCode1AcceptedQuantity.equals(etendercomponentobj.getDataFromPropertiesFile("itemCode_1AcceptedQuantityValue")));
		highlight(tendercreationlocators.viewAcceptedQuantity(itemCode1IndexValue));
		
		String itemCode2AcceptedQuantity = text(tendercreationlocators.viewAcceptedQuantity(itemCode2IndexValue)).trim();
		Assert.assertTrue(itemCode2AcceptedQuantity.equals(etendercomponentobj.getDataFromPropertiesFile("itemCode_2AcceptedQuantityValue")));
		highlight(tendercreationlocators.viewAcceptedQuantity(itemCode2IndexValue));
		
		String itemCode3AcceptedQuantity = text(tendercreationlocators.viewAcceptedQuantity(itemCode3IndexValue)).trim();
		Assert.assertTrue(itemCode2AcceptedQuantity.equals(etendercomponentobj.getDataFromPropertiesFile("itemCode_3AcceptedQuantityValue")));
		highlight(tendercreationlocators.viewAcceptedQuantity(itemCode3IndexValue));
		
		waitForObj(3000);

		pdfResultReport.addStepDetails("verifyGrnDataIsCorrectOrNot",
				"Grn data Should be Validated",
				"Sucessfully checked Grn details In Pop Up" + " ", "Pass", "Y");
		log.info("completed executing the method:: verifyViewGrnSavedDetailsInPopUp");
	} catch (Exception e) {
		log.fatal("failed to check Grn details In Pop Up " + e.getMessage());
		pdfResultReport.addStepDetails("verifyViewGrnSavedDetailsInPopUp",
				"Grn data Should be Validated",
				"failed to check Grn details In Pop Up " + e.getMessage(), "Fail", "N");
		Assert.fail("Failed Due to " + e.getMessage());
	}
}
public void clickOnViewGRNInDisputedInvoicePage1() throws Throwable {
	try {
		log.info("started executing the method:: clickOnViewGRNInDisputedInvoicePage");
		click(tendercreationlocators.itemCodeActionBtn(1),"Action Btn");
		waitForElementToBeVisible(tendercreationlocators.itemCodeViewGRN(1));
		click(tendercreationlocators.itemCodeViewGRN(1),"itemCodeViewGRN");
		waitForElementToBeVisible(tendercreationlocators.grnIDBy);
		waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
		waitForObj(3000);
		pdfResultReport.addStepDetails("clickOnViewGRNInDisputedInvoicePage", "Preview GRN details popup must be display successfully",
				"Successfully displayed preview GRN details popup " + " ", "Pass", "Y");
		log.info("completed executing the method:: clickOnViewGRNInDisputedInvoicePage");
	} catch (Exception e) {
		log.fatal("Unable to display preview GRN details popup" + e.getMessage());
		pdfResultReport.addStepDetails("clickOnViewGRNInDisputedInvoicePage", "Preview GRN details popup must be display successfully",
				"Unable to display preview GRN details popup" + e.getMessage(), "Fail", "N");

		Assert.fail("Failed Due to " + e.getMessage());
	}
}
public void clickOnViewASNInDisputedInvoicePage1() throws Throwable {
	try {
		log.info("started executing the method:: clickOnViewASNInDisputedInvoicePage");
		click(tendercreationlocators.itemCodeActionBtn(1),"Action Btn");
		waitForElementToBeVisible(tendercreationlocators.itemCodeViewASN(1));
		click(tendercreationlocators.itemCodeViewASN(1),"itemCodeViewASN");
		waitForElementToBeVisible(tendercreationlocators.previewASN);
		waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
		waitForObj(3000);
		pdfResultReport.addStepDetails("clickOnViewASNInDisputedInvoicePage", "Preview ASN details popup must be display successfully",
				"Successfully displayed preview ASN details popup " + " ", "Pass", "Y");
		log.info("completed executing the method:: clickOnViewASNInDisputedInvoicePage");
	} catch (Exception e) {
		log.fatal("Unable to display preview ASN details popup" + e.getMessage());
		pdfResultReport.addStepDetails("clickOnViewASNInDisputedInvoicePage", "Preview ASN details popup must be display successfully",
				"Unable to display preview ASN details popup" + e.getMessage(), "Fail", "N");

		Assert.fail("Failed Due to " + e.getMessage());
	}
}
public void validateViewPODetailsInDisputeInvoiceItemCode1() throws Throwable {
	try {
		log.info("started executing the method:: validateViewPODetailsInDisputeInvoiceItemCode");
		click(tendercreationlocators.itemCodeActionBtn(1),"Action Btn");
		waitForElementToBeVisible(tendercreationlocators.itemCodeViewPO);
		click(tendercreationlocators.itemCodeViewPO,"View Po");
		waitForElementToBeVisible(tendercreationlocators.PODetail);
		waitForObj(2000);
		
		String poNumText =  ThreadLocalWebdriver.getDriver().
				findElement(By.xpath("//b[text()='PO No']/../../following-sibling::td//span")).getText();
		Assert.assertEquals(poNumText,eTenderComponent.getDataFromPropertiesFile("poDocNum"));
		highlight(ThreadLocalWebdriver.getDriver().
				findElement(By.xpath("//b[text()='PO No']/../../following-sibling::td//span")));
		
		String poRefNo =  ThreadLocalWebdriver.getDriver().
				findElement(By.xpath("//b[text()='PO Ref No']/../../following-sibling::td//span")).getText();
		Assert.assertEquals(poRefNo,eTenderComponent.getDataFromPropertiesFile("POReferenceNum"));
		highlight(ThreadLocalWebdriver.getDriver().
				findElement(By.xpath("//b[text()='PO Ref No']/../../following-sibling::td//span")));
		
		waitForObj(2000);
		click(tendercreationlocators.closePOView,"close btn");
		waitForElementToBeVisible(tendercreationlocators.itemCodeActionBtn(itemCode1IndexValue));
		
		
		pdfResultReport.addStepDetails("validateViewPODetailsInDisputeInvoiceItemCode", "View Po details are validated successfully",
				"Successfully validated View Po details are validated" + " ", "Pass", "Y");
		log.info("completed executing the method:: validateViewPODetailsInDisputeInvoiceItemCode");
	} catch (Exception e) {
		log.fatal("Unable validated View Po details" + e.getMessage());
		pdfResultReport.addStepDetails("validateViewPODetailsInDisputeInvoiceItemCode", "View Po details are validated successfully",
				"Unable validated View Po details" + e.getMessage(), "Fail", "N");

		Assert.fail("Failed Due to " + e.getMessage());
	}
}
}