package com.components;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.mail.Message;
import javax.mail.MessagingException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import com.baseClasses.BaseClass_Web;
import com.baseClasses.PDFResultReport;
import com.baseClasses.ThreadLocalWebdriver;
import com.objectRepository.TenderCreation_Locators;
import com.baseClasses.EmailUtils;



public class ASN_GRNComponent extends BaseClass_Web {

	TenderCreation_Locators tendercreationlocators = new TenderCreation_Locators();

	eTenderComponent etendercomponentobj = new eTenderComponent(pdfResultReport);

	public ASN_GRNComponent(PDFResultReport pdfresultReport) {
		this.pdfResultReport = pdfresultReport;
	}

	public void SelectASNModule() throws Throwable {
		try {
			log.info("started executing the method:: SelectASNModule");

			JSClick(tendercreationlocators.ASN, "SN_stage");

			pdfResultReport.addStepDetails("SelectASNModule", "ASN Module should be selected successfully",
					"Successfully clicked ASN Module " + " ", "Pass", "Y");
			log.info("completed executing the method:: SelectASNModule");
		} catch (Exception e) {
			log.fatal("Unable to click on ASN Module" + e.getMessage());
			pdfResultReport.addStepDetails("SelectASNModule", "ASN Module should be selected successfully",
					"Unable to click on ASN Module" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}

	}

	LocalDateTime localdatetime = LocalDateTime.now();

	public void SaveASN() throws Throwable {
		try {
			log.info("started executing the method:: SaveASN");

			click(tendercreationlocators.ASNSave, "ASNSave");
			etendercomponentobj.waitForSpinnerToDisappear();
			waitForObj(3000);

			pdfResultReport.addStepDetails("SaveASN", "ASN Module should be saved successfully",
					"Successfully saved ASN Module " + " ", "Pass", "Y");
			log.info("completed executing the method:: SaveASN");
		} catch (Exception e) {
			log.fatal("Unable to saved ASN Module" + e.getMessage());
			pdfResultReport.addStepDetails("SaveASN", "ASN Module should be saved successfully",
					"Unable to saved ASN Module" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}

	}


	public void TabShipmentInformation(String warehouse) throws Throwable {
		try {
			log.info("started executing the method:: TabShipmentInformation");
			// Durgapur
			// Patna

			click(tendercreationlocators.TabShipmentInformation, "TabShipmentInformation");
			waitForObj(5000);
			select(tendercreationlocators.SelctWareHouse, warehouse);

			String BolNum = "BolNum_".concat(eTenderComponent.getDataFromPropertiesFile("poDocNum"));
			click(tendercreationlocators.BolNumber, "BolNumber");
			set(tendercreationlocators.BolNumber, BolNum, "BolNumber");

			click(tendercreationlocators.DeliverToContactName, "DeliverToContactName");
			set(tendercreationlocators.DeliverToContactName, "Rakesh", "DeliverToContactName");
			waitForObj(3000);

			pdfResultReport.addStepDetails("TabShipmentInformation",
					"All fileds of Shipment Information tab should be entered successfully",
					"Successfully saved Shipment Information Tab Details " + " ", "Pass", "Y");
			log.info("completed executing the method:: TabShipmentInformation");
		} catch (Exception e) {
			log.fatal("Unable to save My Information Tab" + e.getMessage());
			pdfResultReport.addStepDetails("TabShipmentInformation",
					"All fileds ofS hipment Information tab should be entered successfully",
					"Unable to save Shipment Information Tab" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}

	}

	public void TabWhatIamShippingWithBoxesOnly(String ASNQuantity, String Weight, String UOM_Weight) throws Throwable {
		try {
			log.info("started executing the method:: TabWhatIamShipping");

			click(tendercreationlocators.TabWhatIamShipping, "TabWhatIamShipping");
			waitForObj(2000);
			etendercomponentobj.waitForSpinnerToDisappear();
			click(tendercreationlocators.BoxesOnly, "BoxesOnly");
			waitForObj(2000);
			click(tendercreationlocators.AddBoxe, "AddBoxe");

			// box1
			click(tendercreationlocators.Boxe1, "Boxe1");

			set(tendercreationlocators.Boxe1, "Box_1", "BoxesOnly");

			click(tendercreationlocators.ItemDescription1, "ItemDescription1");

			click(tendercreationlocators.radiobtnItemDescription1, "radiobtnItemDescription1");

			click(tendercreationlocators.BtnOk, "BtnOk");

			clear(tendercreationlocators.ASNQuantity1, "ASNQuantity1");

			set(tendercreationlocators.ASNQuantity1, ASNQuantity, "ASNQuantity1");

			set(tendercreationlocators.Weight1, Weight, "Weight1");

			select(tendercreationlocators.UOMWeight1, UOM_Weight);// 然
			waitForObj(2000);

			// box2
			JSClick(tendercreationlocators.AddBoxe, "AddBoxe");
			JSClick(tendercreationlocators.Boxe1, "Boxe1");

			set(tendercreationlocators.Boxe2, "Box_2", "BoxesOnly");

			click(tendercreationlocators.ItemDescription2, "ItemDescription2");

			click(tendercreationlocators.radiobtnItemDescription2, "radiobtnItemDescription2");

			click(tendercreationlocators.BtnOk, "BtnOk");

			clear(tendercreationlocators.ASNQuantity2, "ASNQuantity2");

			set(tendercreationlocators.ASNQuantity2, ASNQuantity, "ASNQuantity2");

			set(tendercreationlocators.Weight2, Weight, "Weight2");

			select(tendercreationlocators.UOMWeight2, UOM_Weight);// 然
			waitForObj(2000);

			// box3
			scrollToElement(tendercreationlocators.AddBoxe);
			JSClick(tendercreationlocators.AddBoxe, "AddBoxe");
			scrollToElement(tendercreationlocators.Boxe3);
			JSClick(tendercreationlocators.Boxe3, "Boxe1");

			set(tendercreationlocators.Boxe3, "Box_3", "BoxesOnly");

			click(tendercreationlocators.ItemDescription3, "ItemDescription3");

			click(tendercreationlocators.radiobtnItemDescription3, "radiobtnItemDescription3");

			click(tendercreationlocators.BtnOk, "BtnOk");

			clear(tendercreationlocators.ASNQuantity3, "ASNQuantity3");

			set(tendercreationlocators.ASNQuantity3, ASNQuantity, "ASNQuantity3");

			set(tendercreationlocators.Weight3, Weight, "Weight3");

			select(tendercreationlocators.UOMWeight3, UOM_Weight);// 然

			pdfResultReport.addStepDetails("TabWhatIamShipping",
					"All fileds of What I am Shipping tab should be entered successfully",
					"Successfully saved What I am Shipping Tab Details " + " ", "Pass", "Y");
			log.info("completed executing the method:: TabWhatIamShipping");
		} catch (Exception e) {
			log.fatal("Unable to save My Information Tab" + e.getMessage());
			pdfResultReport.addStepDetails("TabWhatIamShipping",
					"All fileds of What I am Shipping tab should be entered successfully",
					"Unable to save What I am Shipping Tab" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}

	}

	public void TabDeliveryChallenChecklist() throws Throwable {
		try {
			log.info("started executing the method:: TabDeliveryChallenChecklist");

			JSClick(tendercreationlocators.TabDeliveryChallanChecklist, "TabDeliveryChallanChecklist");
			etendercomponentobj.waitForSpinnerToDisappear();
			scrollToElement(tendercreationlocators.DocumentName);

			JavascriptExecutor js = (JavascriptExecutor) ThreadLocalWebdriver.getDriver();
			js.executeScript("window.scrollBy(0,-350)", "");
			waitForObj(2000);
			JSClick(tendercreationlocators.DocumentName, "DocumentName");

			set(tendercreationlocators.DocumentName, "Confidentials_Doc", "DocumentName");

			etendercomponentobj.waitForSpinnerToDisappear();

			set(tendercreationlocators.UploadDocument,
					System.getProperty("user.dir") + "\\MediaFiles\\rfqCreation.xlsx", "UploadDocument");

			etendercomponentobj.waitForSpinnerToDisappear();

			pdfResultReport.addStepDetails("TabDeliveryChallenChecklist",
					"All fileds of Delivery Challen Check list tab should be entered successfully",
					"Successfully saved Delivery Challen Check list Tab Details " + " ", "Pass", "Y");
			log.info("completed executing the method:: TabDeliveryChallenChecklist");
		} catch (Exception e) {
			log.fatal("Unable to save My Information Tab" + e.getMessage());
			pdfResultReport.addStepDetails("TabDeliveryChallenChecklist",
					"All fileds of Delivery Challen Check listtab should be entered successfully",
					"Unable to save Delivery Challen Check list Tab" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}

	}

	public void TabInvoice(String InvoiceType) throws Throwable {
		try {
			log.info("started executing the method:: TabInvoice");
			// Invoice
			// Proforma invoice
			// Delivery challan

			JSClick(tendercreationlocators.TabInvoice, "TabInvoice");
			JavascriptExecutor js = (JavascriptExecutor) ThreadLocalWebdriver.getDriver();
			js.executeScript("window.scrollBy(0,-350)", "");

			waitForObj(3000);

			select(tendercreationlocators.InvoiceType, InvoiceType);
			clear(tendercreationlocators.SellerInvoiceNum, "SellerInvoiceNum");
			waitForObj(3000);
			
			String SellerInvoiceNum = "SellerInvoice_".concat(eTenderComponent.getDataFromPropertiesFile("poDocNum"));
			set(tendercreationlocators.SellerInvoiceNum, SellerInvoiceNum, "TabShipmentInformation");
			eTenderComponent.updateDataIntoPropertyFile("SellerInvoiceNumber", SellerInvoiceNum);

			String InvoiceDate = localdatetime.format(DateTimeFormatter.ofPattern("dd-MM-[yy]"));
			JSClick(tendercreationlocators.InvoiceDate, "InvoiceDate");
			clear(tendercreationlocators.InvoiceDate, "InvoiceDate");
			set(tendercreationlocators.InvoiceDate, InvoiceDate, "InvoiceDate");

			JSClick(tendercreationlocators.Tax, "Tax");
			clear(tendercreationlocators.Tax, "Tax");
			set(tendercreationlocators.Tax, "5", "Tax");

			JSClick(tendercreationlocators.Freight, "Freight");
			clear(tendercreationlocators.Freight, "Freight");
			set(tendercreationlocators.Freight, "10", "Freight");
			etendercomponentobj.waitForSpinnerToDisappear();
			waitForObj(2000);

			set(tendercreationlocators.InvoiceAttachment, System.getProperty("user.dir") + "\\MediaFiles\\report.pdf",
					"InvoiceAttachment");
			waitForObj(3000);

			set(tendercreationlocators.BankAccountNumber, "1558540", "BankAccountNumber");
			waitForObj(2000);
			set(tendercreationlocators.BillingAddress, "Kolkata", "BillingAddress");
			waitForObj(2000);

			if (InvoiceType.equals("Invoice")) {
				click(tendercreationlocators.cashDiscount, "cashDiscount");

				click(tendercreationlocators.cashPercentage, "cashPercentage");

				clear(tendercreationlocators.cashPercentage, "cashPercentage");
				set(tendercreationlocators.cashPercentage, "5", "cashPercentage");

				click(tendercreationlocators.NumberOfDays, "NumberOfDays");

				clear(tendercreationlocators.NumberOfDays, "NumberOfDays");
				set(tendercreationlocators.NumberOfDays, "6", "NumberOfDays");

			} else {
				System.out.println("Invoice Type is not Invoice");
			}

			pdfResultReport.addStepDetails("TabInvoice", "All fileds of Invoice tab should be entered successfully",
					"Successfully saved Invoice Tab Details " + " ", "Pass", "Y");
			log.info("completed executing the method:: TabInvoice");
		} catch (Exception e) {
			log.fatal("Unable to save My Information Tab" + e.getMessage());
			pdfResultReport.addStepDetails("TabInvoice", "All fileds of Invoice should be entered successfully",
					"Unable to save Invoice Tab" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}

	}

	public void VerifyDraftStatus() throws Throwable {
		try {
			log.info("started executing the method:: VerifyDraftStatus");

			String ponum = eTenderComponent.getDataFromPropertiesFile("poDocNum");

			waitForObj(3000);
			WebElement ele = ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.Status(ponum));
			highlight(tendercreationlocators.Status(ponum));
			String colour = getattributevalue(ele, "class");
			if (colour.contains("grnBtn")) {
				pdfResultReport.addStepDetails("VerifyDraftStatus", "ASN status to be verified",
						"Successfully verified ASN Status as Draft" + " ", "Pass", "Y");
			} else {
				pdfResultReport.addStepDetails("VerifyDraftStatus", "ASN status to be verified",
						"Unable to verify ASN Status as Draft", "Fail", "N");
			}
			log.info("completed executing the method:: VerifyDraftStatus");
		} catch (Exception e) {
			log.fatal("Not able to search ASN list with PO number" + e.getMessage());
			Assert.fail("Failed Due to " + e.getMessage());

		}
	}

	public void clickOnASNList() throws Throwable {
		try {
			log.info("started executing the method:: clickOnASNList");
			JSClick(tendercreationlocators.ASN, "ASN");
			waitForObj(3000);
			JSClick(tendercreationlocators.ASNList, "ASNList");
			waitForElementToBeVisible(By.xpath("(//section[@id='main-content-nw']//table//tbody//tr[2])[1]"));
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			pdfResultReport.addStepDetails("clickOnASNList", "ASN List page must be naviagte successfully",
					"Successfully navigated to ASN List page" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickOnASNList");
		} catch (Exception e) {
			log.fatal("Not able to navigate to ASN List page" + e.getMessage());
			pdfResultReport.addStepDetails("clickOnASNList", "ASN List page must be naviagte successfully",
					"Unable to navigate to ASN List page" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void searchForTheCancelledPOInASNListingPage() throws Throwable {
		try {
			log.info("started executing the method:: searchForTheCancelledPOInASNListingPage");
			clear(tendercreationlocators.search, "Clear the TenderRefNumber");
			set(tendercreationlocators.search, eTenderComponent.getDataFromPropertiesFile("poDocNum"),
					"search The PO Doc Number");
			waitForObj(5000);
			pdfResultReport.addStepDetails("searchForTheCancelledPOInASNListingPage",
					"Cancelled PO should not be display In ASN Listing Page sucessfully",
					"Sucessfully not displayed Cancelled PO In ASN Listing Page" + " ", "Pass", "Y");
			log.info("completed executing the method:: searchForTheCancelledPOInASNListingPage");
		} catch (Exception e) {
			log.fatal("Able to display Cancelled PO In ASN Listing Page" + e.getMessage());
			pdfResultReport.addStepDetails("searchForTheCancelledPOInASNListingPage",
					"Cancelled PO should not be display In ASN Listing Page sucessfully",
					"Able to display Cancelled PO In ASN Listing Page" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void searchForTheRejectedPOInASNListingPage() throws Throwable {
		try {
			log.info("started executing the method:: searchForTheRejectedPOInASNListingPage");
			clear(tendercreationlocators.search, "Clear the TenderRefNumber");
			set(tendercreationlocators.search, eTenderComponent.getDataFromPropertiesFile("poDocNum"),
					"search The PO Doc Number");
			waitForObj(5000);
			pdfResultReport.addStepDetails("searchForTheRejectedPOInASNListingPage",
					"Rejected PO should not be display In ASN Listing Page sucessfully",
					"Sucessfully not displayed Rejected PO In ASN Listing Page" + " ", "Pass", "Y");
			log.info("completed executing the method:: searchForTheRejectedPOInASNListingPage");
		} catch (Exception e) {
			log.fatal("Able to display Rejected PO In ASN Listing Page" + e.getMessage());
			pdfResultReport.addStepDetails("searchForTheRejectedPOInASNListingPage",
					"Rejected PO should not be display In ASN Listing Page sucessfully",
					"Able to display Rejected PO In ASN Listing Page" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void searchForThePONotAcceptedByBidderInASNListingPage() throws Throwable {
		try {
			log.info("started executing the method:: searchForThePONotAcceptedByBidderInASNListingPage");
			clear(tendercreationlocators.search, "Clear the TenderRefNumber");
			set(tendercreationlocators.search, eTenderComponent.getDataFromPropertiesFile("poDocNum"),
					"search The PO Doc Number");
			waitForObj(5000);
			pdfResultReport.addStepDetails("searchForThePONotAcceptedByBidderInASNListingPage",
					"PO should not be displayed In ASN Listing Page until it is accepted by the bidder sucessfully",
					"Sucessfully not displayed PO In ASN Listing Page until it is accepted by the bidder " + " ",
					"Pass", "Y");
			log.info("completed executing the method:: searchForThePONotAcceptedByBidderInASNListingPage");
		} catch (Exception e) {
			log.fatal("Able to display PO before accepted by the bidder In ASN Listing Page" + e.getMessage());
			pdfResultReport.addStepDetails("searchForThePONotAcceptedByBidderInASNListingPage",
					"PO should not be displayed In ASN Listing Page until it is accepted by the bidder sucessfully",
					"Able to display PO before accepted by the bidder In ASN Listing Page" + e.getMessage(), "Fail",
					"N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void GRN_Creator_Login() throws Throwable {
		try {
			log.info("started executing the method:: GRN_Creator_Login");
			click(tendercreationlocators.login, "login");
			waitForElement(tendercreationlocators.userName, 3000);
			set(tendercreationlocators.userName, pdfResultReport.testData.get("GRNCreatorUserName"), "userName");
			set(tendercreationlocators.password, pdfResultReport.testData.get("AppPassword"), "password");
			click(tendercreationlocators.okButton, "okButton");
			waitForElement(tendercreationlocators.dashboardIcon, 5000);
			waitForObj(5000);
			pdfResultReport.addStepDetails("GRN_Creator_Login", "Dashboard Page will be displayed",
					"Successfully Dashboard Page is displaying" + " ", "Pass", "Y");
			log.info("completed executing the method:: GRN_Creator_Login");
		} catch (Exception e) {
			log.fatal("Unable to login GRN_Creator_Login" + e.getMessage());
			pdfResultReport.addStepDetails("GRN_Creator_Login", "Dashboard Page will be displayed",
					"Unable to login GRN_Creator_Login" + e.getMessage(), "Fail", "N");

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

	public void ASNListPage() throws Throwable {
		try {
			log.info("started executing the method:: navigateToASNList");
			JSClick(tendercreationlocators.ASN, "ASN");
			waitForObj(3000);
			JSClick(tendercreationlocators.ASNList, "ASNList");
			waitForObj(3000);
			etendercomponentobj.waitForSpinnerToDisappear();
			pdfResultReport.addStepDetails("navigateToASNList", "ASN List page must be naviagte successfully",
					"Successfully navigated to ASN List page" + " ", "Pass", "Y");
			log.info("completed executing the method:: navigateToASNList");
		} catch (Exception e) {
			log.fatal("Not able to navigate to ASN List page" + e.getMessage());
			pdfResultReport.addStepDetails("navigateToASNList", "ASN List page must be naviagte successfully",
					"Unable to navigate to ASN List page" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void navigateToApprovedASNListPage() throws Throwable {
		try {
			log.info("started executing the method:: navigateToApprovedASNListPage");

			JSClick(tendercreationlocators.GRNMenuLinkBy, "GRNMenuLinkBy");

			waitForObj(2000);

			JSClick(tendercreationlocators.ApprovedASNLinkBy, "ApprovedASNLinkBy");

			waitForElementToBeVisible(tendercreationlocators.AsnListShipmentThBy);

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			waitForObj(5000);

			pdfResultReport.addStepDetails("navigateToApprovedASNListPage", "Should navigate to Approved ASN List Page",
					"Successfully navigated to Approved ASN List Page" + " ", "Pass", "Y");
			log.info("completed executing the method:: navigateToApprovedASNListPage");
		} catch (Exception e) {
			log.fatal("Unable to navigate to Approved ASN List Page" + e.getMessage());
			pdfResultReport.addStepDetails("navigateToApprovedASNListPage", "Should navigate to Approved ASN List Page",
					"Unable to navigate to Approved ASN List Page" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void enterASNShipmentAndSelectVendorName(String vendorName, String AsnShipmentNo) throws Throwable {
		try {
			log.info("started executing the method:: enterASNShipmentAndSelectVendorName");
			// TCS
			// ShippingTracking_2104

			WebDriver driver = ThreadLocalWebdriver.getDriver();
			String TCSVendorBy = "//*[contains(@id,'typeahead')]/li/a/strong[text()='{0}']";

			String AsnShipmentNoBy = "//*[contains(@id,'typeahead')]/li/a/strong[text()='{0}']";

			waitForObj(3000);

			set(tendercreationlocators.vendorNameFieldBy, vendorName, "vendorNameFieldBy");

			driver.findElement(By.xpath(TCSVendorBy.replace("{0}", vendorName))).click();

			waitForObj(2000);

			clear(tendercreationlocators.entershipmentFieldBy, "Clear shipmentFieldBy");
			set(tendercreationlocators.entershipmentFieldBy, AsnShipmentNo, "AsnShipmentNo");

			driver.findElement(By.xpath(AsnShipmentNoBy.replace("{0}", AsnShipmentNo))).click();

			waitForObj(2000);

			pdfResultReport.addStepDetails("enterASNShipmentAndSelectVendorName", "Should display ASN Data in Table",
					"Successfully  displaying ASN Data in Table" + " ", "Pass", "Y");
			log.info("completed executing the method:: enterASNShipmentAndSelectVendorName");
		} catch (Exception e) {
			log.fatal("Unable to display ASN Data in Table" + e.getMessage());
			pdfResultReport.addStepDetails("enterASNShipmentAndSelectVendorName", "Should display ASN Data in Table",
					"Unable to display ASN Data in Table" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());

		}
	}

	public void enterShipmentNumber() throws Throwable {
		try {
			log.info("started executing the method:: enterShipmentNumber");
			clear(tendercreationlocators.enterShipmentNumber, "Clear the TenderRefNumber");
			set(tendercreationlocators.enterShipmentNumber,
					eTenderComponent.getDataFromPropertiesFile("ASNShippingTracking"), "search Shipment Number");
			ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.enterShipmentNumber)
					.sendKeys(Keys.RETURN);
			pdfResultReport.addStepDetails("enterShipmentNumber",
					"Shipping Tracking Number must be display sucessfully",
					"Sucessfully displayed Shipping Tracking Number" + " ", "Pass", "Y");
			log.info("completed executing the method:: enterShipmentNumber");
		} catch (Exception e) {
			log.fatal("Not Able to display Shipping Tracking Number" + e.getMessage());
			pdfResultReport.addStepDetails("enterShipmentNumber",
					"Shipping Tracking Number must be display sucessfully",
					"Unable to display Shipping Tracking Number" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	/*public void clickApprovedAsnListActionMenu(String AsnShipmentNo) throws Throwable {
		try {
			log.info("started executing the method:: clickApprovedAsnListActionMenu");

			WebDriver driver = ThreadLocalWebdriver.getDriver();

			String actionDropDown = "//*[contains(text(),'{0}')]/../following-sibling::td//child::button[@data-toggle='dropdown']";

			driver.findElement(By.xpath(actionDropDown.replace("{0}", AsnShipmentNo))).click();

			waitForObj(3000);

			pdfResultReport.addStepDetails("clickApprovedAsnListActionMenu", "Should click ActionMenu",
					"Successfully  Should click ActionMenu" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickApprovedAsnListActionMenu");
		} catch (Exception e) {
			log.fatal("Unable to click ActionMenu" + e.getMessage());
			pdfResultReport.addStepDetails("clickApprovedAsnListActionMenu", "Should click ActionMenu",
					"Unable to click ActionMenu" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}*/

	public void createGrn(String AsnShipmentNo) throws Throwable {
		try {
			log.info("started executing the method:: createGrn");

			WebDriver driver = ThreadLocalWebdriver.getDriver();

			String createGrn = "//*[contains(text(),'{0}')]/../following-sibling::td//child::button[@data-toggle='dropdown']/..//ul/li/a[contains(text(),'Create GRN')]";

			driver.findElement(By.xpath(createGrn.replace("{0}", AsnShipmentNo))).click();

			waitForElementToBeVisible(tendercreationlocators.MaterialDetailsBy);

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			waitForObj(3000);

			pdfResultReport.addStepDetails("createGrn", "Should Navigate to GRN creation Page",
					"Successfully Navigated to GRN creation Page" + " ", "Pass", "Y");
			log.info("completed executing the method:: createGrn");
		} catch (Exception e) {
			log.fatal("Unable to Navigate to GRN creation Page" + e.getMessage());
			pdfResultReport.addStepDetails("createGrn", "Should Navigate to GRN creation Page",
					"Unable to Navigate to GRN creation Page" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());

		}
	}

	public void saveGrnDetails() throws Throwable {
		try {
			log.info("started executing the method:: saveGrnDetails");

			waitForObj(3000);

			click(tendercreationlocators.SaveGrnDetailsBy, "SaveGrnDetails");

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			waitForObj(3000);

			pdfResultReport.addStepDetails("saveGrnDetails", "Should Save GRN Details",
					"Successfully Save GRN Details" + " ", "Pass", "Y");
			log.info("completed executing the method:: saveGrnDetails");
		} catch (Exception e) {
			log.fatal("Unable to Save GRN Details" + e.getMessage());
			pdfResultReport.addStepDetails("saveGrnDetails", "Should Save GRN Details",
					"Unable to  Save GRN Details" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());

		}
	}

	public void submitGrnDetails() throws Throwable {
		try {
			log.info("started executing the method:: submitGrnDetails");

			waitForObj(3000);

			click(tendercreationlocators.GrnSubmitBy, "GrnSubmitBy");

			waitForElementToBeVisible(tendercreationlocators.GrnSubmitConfirmPopUpMsgBy);

			waitForObj(3000);

			pdfResultReport.addStepDetails("submitGrnDetails", "Should Show Confirmation Pop Up Yes or No",
					"Successfully Showing Confirmation Pop Up Yes or No" + " ", "Pass", "Y");
			log.info("completed executing the method:: submitGrnDetails");
		} catch (Exception e) {
			log.fatal("Unable to Show Confirmation Pop Up Yes or No" + e.getMessage());
			pdfResultReport.addStepDetails("submitGrnDetails", "Should Show Confirmation Pop Up Yes or No",
					"Unable to  Show Confirmation Pop Up Yes or No" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());

		}
	}

	public void clickGrnSubmitComfirmationNo() throws Throwable {
		try {
			log.info("started executing the method:: clickGrnSubmitComfirmationNo");

			waitForObj(3000);

			click(tendercreationlocators.GrnSubmitConfirmNoBy, "GrnSubmitBy");

			waitForObj(5000);

			pdfResultReport.addStepDetails("clickGrnSubmitComfirmationNo", "Should be in Same Page Grn Creation Page",
					"Successfully clicked No Button and is in Same Creation page " + " ", "Pass", "Y");
			log.info("completed executing the method:: clickGrnSubmitComfirmationNo");
		} catch (Exception e) {
			log.fatal("Unable to click No Button" + e.getMessage());
			pdfResultReport.addStepDetails("clickGrnSubmitComfirmationNo", "Should be in Same Page Grn Creation Page",
					"Unable to click No Button " + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());

		}
	}

	/*
	 * public void enterRejectedQty(int ItemCodeRow, String QtyNo) throws
	 * Throwable { try { log.info(
	 * "started executing the method:: enterRejectedQty");
	 * 
	 * clear(tendercreationlocators.EnterRejectedQtyBy(ItemCodeRow),
	 * "Clear RejectedQtyBy");
	 * 
	 * set(tendercreationlocators.EnterRejectedQtyBy(ItemCodeRow), QtyNo,
	 * "EnterRejectedQtyBy");
	 * 
	 * waitForObj(2000);
	 * 
	 * pdfResultReport.addStepDetails("enterRejectedQty",
	 * "Should enter Rejected Quantity", "Successfully enter Rejected Quantity"
	 * + " ", "Pass", "Y"); log.info(
	 * "completed executing the method:: enterRejectedQty"); } catch (Exception
	 * e) { log.fatal("Unable to enter Rejected Qty" + e.getMessage());
	 * pdfResultReport.addStepDetails("enterRejectedQty",
	 * "Should enter Rejected Quantity", "Unable to enter Rejected Qty " +
	 * e.getMessage(), "Fail", "N");
	 * 
	 * Assert.fail("Failed Due to " + e.getMessage());
	 * 
	 * } }
	 */
	public void enterRejectedQty(int ItemCodeRow, String QtyNo) throws Throwable {
		try {
			log.info("started executing the method:: enterRejectedQty");

			click(tendercreationlocators.EnterRejectedQtyBy(ItemCodeRow), "Clear RejectedQtyBy");

			ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.EnterRejectedQtyBy(ItemCodeRow))
					.sendKeys(Keys.DELETE);
			ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.EnterRejectedQtyBy(ItemCodeRow))
					.sendKeys(Keys.ARROW_LEFT);
			ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.EnterRejectedQtyBy(ItemCodeRow))
					.sendKeys(Keys.ARROW_LEFT);
			set(tendercreationlocators.EnterRejectedQtyBy(ItemCodeRow), QtyNo, "EnterRejectedQtyBy");

			waitForObj(2000);

			pdfResultReport.addStepDetails("enterRejectedQty", "Should enter Rejected Quantity",
					"Successfully enter Rejected Quantity" + " ", "Pass", "Y");
			log.info("completed executing the method:: enterRejectedQty");
		} catch (Exception e) {
			log.fatal("Unable to enter Rejected Qty" + e.getMessage());
			pdfResultReport.addStepDetails("enterRejectedQty", "Should enter Rejected Quantity",
					"Unable to enter Rejected Qty " + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());

		}
	}

	/*
	 * public void enterShortQty(int ItemCodeRow, String QtyNo) throws Throwable
	 * { try { log.info("started executing the method:: enterShortQty");
	 * 
	 * clear(tendercreationlocators.EnterShortQtyBy(ItemCodeRow),
	 * "clear ShortQty");
	 * 
	 * set(tendercreationlocators.EnterShortQtyBy(ItemCodeRow), QtyNo,
	 * "EnterShortQtyBy");
	 * 
	 * waitForObj(2000);
	 * 
	 * pdfResultReport.addStepDetails("enterShortQty",
	 * "Should enter Short Quantity", "Successfully enter Short  Quantity" + " "
	 * , "Pass", "Y"); log.info("completed executing the method:: enterShortQty"
	 * ); } catch (Exception e) { log.fatal("Unable to enter Short  Qty" +
	 * e.getMessage()); pdfResultReport.addStepDetails("enterShortQty",
	 * "Should enter Short  Quantity", "Unable to enter Short  Qty " +
	 * e.getMessage(), "Fail", "N");
	 * 
	 * Assert.fail("Failed Due to " + e.getMessage()); } }
	 */
	public void enterShortQty(int ItemCodeRow, String QtyNo) throws Throwable {
		try {
			log.info("started executing the method:: enterShortQty");

			click(tendercreationlocators.EnterShortQtyBy(ItemCodeRow), "clear ShortQty");
			ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.EnterShortQtyBy(ItemCodeRow))
					.sendKeys(Keys.DELETE);
			ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.EnterShortQtyBy(ItemCodeRow))
					.sendKeys(Keys.ARROW_LEFT);
			ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.EnterShortQtyBy(ItemCodeRow))
					.sendKeys(Keys.ARROW_LEFT);
			set(tendercreationlocators.EnterShortQtyBy(ItemCodeRow), QtyNo, "EnterShortQtyBy");

			waitForObj(2000);

			pdfResultReport.addStepDetails("enterShortQty", "Should enter Short Quantity",
					"Successfully enter Short  Quantity" + " ", "Pass", "Y");
			log.info("completed executing the method:: enterShortQty");
		} catch (Exception e) {
			log.fatal("Unable to enter Short  Qty" + e.getMessage());
			pdfResultReport.addStepDetails("enterShortQty", "Should enter Short  Quantity",
					"Unable to enter Short  Qty " + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	/*
	 * public void enterExcessQty(int ItemCodeRow, String QtyNo) throws
	 * Throwable { try { log.info(
	 * "started executing the method:: enterExcessQty");
	 * 
	 * clear(tendercreationlocators.EnterExcessQtyBy(ItemCodeRow),
	 * " clear ExcessQtyBy");
	 * 
	 * set(tendercreationlocators.EnterExcessQtyBy(ItemCodeRow), QtyNo,
	 * "EnterExcessQtyBy");
	 * 
	 * waitForObj(2000);
	 * 
	 * pdfResultReport.addStepDetails("enterExcessQty",
	 * "Should enter Excess Quantity", "Successfully enter Excess  Quantity" +
	 * " ", "Pass", "Y"); log.info(
	 * "completed executing the method:: enterExcessQty"); } catch (Exception e)
	 * { log.fatal("Unable to enter Excess  Qty" + e.getMessage());
	 * pdfResultReport.addStepDetails("enterExcessQty",
	 * "Should enter Excess  Quantity", "Unable to enter Excess  Qty " +
	 * e.getMessage(), "Fail", "N");
	 * 
	 * Assert.fail("Failed Due to " + e.getMessage());
	 * 
	 * } }
	 */
	public void enterExcessQty(int ItemCodeRow, String QtyNo) throws Throwable {
		try {
			log.info("started executing the method:: enterExcessQty");

			click(tendercreationlocators.EnterExcessQtyBy(ItemCodeRow), " clear ExcessQtyBy");
			ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.EnterExcessQtyBy(ItemCodeRow))
					.sendKeys(Keys.DELETE);
			ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.EnterExcessQtyBy(ItemCodeRow))
					.sendKeys(Keys.ARROW_LEFT);
			ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.EnterExcessQtyBy(ItemCodeRow))
					.sendKeys(Keys.ARROW_LEFT);
			set(tendercreationlocators.EnterExcessQtyBy(ItemCodeRow), QtyNo, "EnterExcessQtyBy");

			waitForObj(2000);

			pdfResultReport.addStepDetails("enterExcessQty", "Should enter Excess Quantity",
					"Successfully enter Excess  Quantity" + " ", "Pass", "Y");
			log.info("completed executing the method:: enterExcessQty");
		} catch (Exception e) {
			log.fatal("Unable to enter Excess  Qty" + e.getMessage());
			pdfResultReport.addStepDetails("enterExcessQty", "Should enter Excess  Quantity",
					"Unable to enter Excess  Qty " + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());

		}
	}

	/*
	 * public void enterDamageQty(int ItemCodeRow, String QtyNo) throws
	 * Throwable { try { log.info(
	 * "started executing the method:: enterDamageQty");
	 * 
	 * clear(tendercreationlocators.EnterDamageQtyBy(ItemCodeRow),
	 * " clear DamageQty");
	 * 
	 * set(tendercreationlocators.EnterDamageQtyBy(ItemCodeRow), QtyNo,
	 * "EnterDamageQtyBy");
	 * 
	 * waitForObj(2000);
	 * 
	 * pdfResultReport.addStepDetails("enterDamageQty",
	 * "Should enter Damage Quantity", "Successfully enter Damage Quantity" +
	 * " ", "Pass", "Y"); log.info(
	 * "completed executing the method:: enterDamageQty"); } catch (Exception e)
	 * { log.fatal("Unable to enter Excess  Qty" + e.getMessage());
	 * pdfResultReport.addStepDetails("enterDamageQty",
	 * "Should enter Damage  Quantity", "Unable to enter Damage  Qty " +
	 * e.getMessage(), "Fail", "N"); Assert.fail("Failed Due to " +
	 * e.getMessage()); } }
	 */
	public void enterDamageQty(int ItemCodeRow, String QtyNo) throws Throwable {
		try {
			log.info("started executing the method:: enterDamageQty");

			click(tendercreationlocators.EnterDamageQtyBy(ItemCodeRow), " clear DamageQty");
			ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.EnterDamageQtyBy(ItemCodeRow))
					.sendKeys(Keys.DELETE);
			ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.EnterDamageQtyBy(ItemCodeRow))
					.sendKeys(Keys.ARROW_LEFT);
			ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.EnterDamageQtyBy(ItemCodeRow))
					.sendKeys(Keys.ARROW_LEFT);
			set(tendercreationlocators.EnterDamageQtyBy(ItemCodeRow), QtyNo, "EnterDamageQtyBy");

			waitForObj(2000);

			pdfResultReport.addStepDetails("enterDamageQty", "Should enter Damage Quantity",
					"Successfully enter Damage Quantity" + " ", "Pass", "Y");
			log.info("completed executing the method:: enterDamageQty");
		} catch (Exception e) {
			log.fatal("Unable to enter Excess  Qty" + e.getMessage());
			pdfResultReport.addStepDetails("enterDamageQty", "Should enter Damage  Quantity",
					"Unable to enter Damage  Qty " + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void ViewShipmentDetailsPageForCancelledGRN(String ShipTrackno) throws Throwable {
		try {
			log.info("started executing the method:: ViewShipmentDetailsPageForCancelledGRN");
			JSClick(tendercreationlocators.CancelGRN(ShipTrackno), "CancelGRN");
			waitForObj(5000);
			JSClick(tendercreationlocators.confirm, "CancelGRN");
			etendercomponentobj.waitForSpinnerToDisappear();
			waitForObj(8000);
			JSClick(tendercreationlocators.TabCancel, "TabCancel");
			etendercomponentobj.waitForSpinnerToDisappear();
			waitForObj(5000);
			JSClick(tendercreationlocators.ViewShipmentDetailsPage(ShipTrackno), "ViewShipmentDetailsPage");
			etendercomponentobj.waitForSpinnerToDisappear();
			waitForObj(5000);

			pdfResultReport.addStepDetails("ViewShipmentDetailsPageForCancelledGRN",
					"View Shipment Details Page should be navigated successfully",
					"Successfully navigated to View Shipment Details Page" + " ", "Pass", "Y");
			log.info("completed executing the method:: ViewShipmentDetailsPageForCancelledGRN");
		} catch (Exception e) {
			log.fatal("Unable to navigate to View Shipment Details Page" + e.getMessage());
			pdfResultReport.addStepDetails("ViewShipmentDetailsPageForCancelledGRN",
					"View Shipment Details Page should be navigated successfully",
					"Unable to navigate to View Shipment Details Page" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}


	public void VerifyDetailsInShipmetDetailsPage() throws Throwable {
		try {
			log.info("started executing the method:: VerifyDetailsInShipmetDetailsPage");
			etendercomponentobj.waitForSpinnerToDisappear();
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
										pdfResultReport.addStepDetails("VerifyDetailsInShipmetDetailsPage",
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
										pdfResultReport.addStepDetails("VerifyDetailsInShipmetDetailsPage",
												"Should verify all validations",
												"Successfully verified all validations" + " ", "Pass", "Y");
									} else {
										pdfResultReport.addStepDetails("VerifyDetailsInShipmetDetailsPage",
												"Should verify ASN_Shipment_Num value",
												"Unable to  verify ASN_Shipment_Num Value" + " ", "Fail", "N");
									}
								} else {
									pdfResultReport.addStepDetails("VerifyDetailsInShipmetDetailsPage",
											"Should verify ASN_carrier value",
											"Unable to  verify ASN_carrier Value" + " ", "Fail", "N");
								}
							} else {
								pdfResultReport.addStepDetails("VerifyDetailsInShipmetDetailsPage",
										"Should verify ASNAdditionalNote value",
										"Unable to  verify ASNAdditionalNote Value" + " ", "Fail", "N");
							}

						} else {
							pdfResultReport.addStepDetails("VerifyDetailsInShipmetDetailsPage",
									"Should verify Shipmetmethod value", "Unable to  verify Shipmetmethod Value" + " ",
									"Fail", "N");
						}
					} else {
						pdfResultReport.addStepDetails("VerifyDetailsInShipmetDetailsPage",
								"Should verify Reference value", "Unable to  verify Reference Value" + " ", "Fail",
								"N");
					}

				} else {
					pdfResultReport.addStepDetails("VerifyDetailsInShipmetDetailsPage", "Should verify ASN value",
							"Unable to  verify ASN Value" + " ", "Fail", "N");
				}
			} else {
				pdfResultReport.addStepDetails("VerifyDetailsInShipmetDetailsPage", "Should verify PO value",
						"Unable to  verify PO Value" + " ", "Fail", "N");
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

	public void getAcceptedQtyValueAndVerify(int ItemCodeRow, String ExpectedAcceptedValue) throws Throwable {
		try {
			log.info("started executing the method:: enterDamageQty");

			String ActualAcceptedValue = text(tendercreationlocators.AcceptedQtyBy(ItemCodeRow));

			Assert.assertTrue(ActualAcceptedValue.trim().equals(ExpectedAcceptedValue),
					"Acceted value is showing correctly");

			waitForObj(2000);

			pdfResultReport.addStepDetails("enterDamageQty", "Should enter Damage Quantity",
					"Successfully enter Damage Quantity" + " ", "Pass", "Y");
			log.info("completed executing the method:: enterDamageQty");
		} catch (Exception e) {
			log.fatal("Unable to enter Excess  Qty" + e.getMessage());
			pdfResultReport.addStepDetails("enterDamageQty", "Should enter Damage  Quantity",
					"Unable to enter Damage  Qty " + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void clickViewGrnDetailsInActionMenu(String AsnShipmentNo) throws Throwable {
		try {
			log.info("started executing the method:: clickViewGrnDetails");

			WebDriver driver = ThreadLocalWebdriver.getDriver();

			String viewGrn = "//*[contains(text(),'{0}')]/following-sibling::td//child::button[@data-toggle='dropdown']/following-sibling::ul/li/a[contains(text(),'View GRN')]";

			driver.findElement(By.xpath(viewGrn.replace("{0}", AsnShipmentNo))).click();

			waitForElementToBeVisible(tendercreationlocators.grnIDBy);

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			waitForObj(3000);

			pdfResultReport.addStepDetails("clickViewGrnDetails", "Should Display Popup With GRN Preview Details",
					"Successfully Displaying popup With GRN Preview Details" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickViewGrnDetails");
		} catch (Exception e) {
			log.fatal("Unable to  Display popup With GRN Preview Details" + e.getMessage());
			pdfResultReport.addStepDetails("clickViewGrnDetails", "Should Display popup With GRN Preview Details",
					"Unable to  Display popup With GRN Preview Details" + e.getMessage(), "Fail", "N");

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

	public void closeAsnModalPreViewDetailsPopUp() throws Throwable {
		try {
			log.info("started executing the method:: closePreviewwGrnDetailsPopUp");

			click(tendercreationlocators.AsnPreviewCloseBtn, "AsnPreviewCloseBtn");

			waitForObj(3000);

			pdfResultReport.addStepDetails("closeAsnModalPreViewDetailsPopUp", "Should close ASNPreview Pop Up",
					"Successfully closed  ASN  Preview Pop Up" + " ", "Pass", "Y");
			log.info("completed executing the method:: closeAsnModalPreViewDetailsPopUp");
		} catch (Exception e) {
			log.fatal("Unable to  Display popup With GRN Preview Details" + e.getMessage());
			pdfResultReport.addStepDetails("closeAsnModalPreViewDetailsPopUp", "Should close  ASN Preview Pop Up",
					"Unable to close  ASN Preview Pop Up" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}

	}

	public void clickViewShipmentDetailsInActionMenu(String AsnShipmentNo) throws Throwable {
		try {
			log.info("started executing the method:: clickViewShipmentDetails");

			WebDriver driver = ThreadLocalWebdriver.getDriver();

			String viewshipment = "//*[contains(text(),'{0}')]/following-sibling::td//child::button[@data-toggle='dropdown']/following-sibling::ul/li/a[contains(text(),'View Shipment Details')]";

			driver.findElement(By.xpath(viewshipment.replace("{0}", AsnShipmentNo))).click();

			waitForElementToBeVisible(tendercreationlocators.asnNoBy);

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			waitForObj(3000);

			pdfResultReport.addStepDetails("clickViewShipmentDetails", "Should Display Popup With ASN Preview Details",
					"Successfully Displaying popup With ASN Preview Details" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickViewShipmentDetails");
		} catch (Exception e) {
			log.fatal("Unable to  Display popup With ASN Preview Details" + e.getMessage());
			pdfResultReport.addStepDetails("clickViewShipmentDetails", "Should Display popup With ASN Preview Details",
					"Unable to  Display popup With ASN Preview Details" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void clickOnGRNCancelTab() throws Throwable {
		try {
			log.info("started executing the method:: clickOnGRNCancelTab");

			click(tendercreationlocators.GrnCancelTabBy, "GrnCancelTabBy");

			waitForElementToBeVisible(tendercreationlocators.GrnCancelTabRecBy);

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			waitForObj(3000);

			pdfResultReport.addStepDetails("clickOnGRNCancelTab", "Should Display GRN Cancelled Records",
					"Successfully Displaying  GRN Cancelled Records" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickOnGRNCancelTab");
		} catch (Exception e) {
			log.fatal("Unable to  Display GRN Cancelled Records" + e.getMessage());
			pdfResultReport.addStepDetails("clickOnGRNCancelTab", "Should Display GRN Cancelled Records",
					"Unable to  Display GRN Cancelled Records" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void clickGrnListActionMenu(String AsnShipmentNo) throws Throwable {
		try {
			log.info("started executing the method:: clickGrnListActionMenu");

			WebDriver driver = ThreadLocalWebdriver.getDriver();

			String actionDropDown = "//*[contains(text(),'{0}')]/following-sibling::td//child::button[@data-toggle='dropdown']";

			driver.findElement(By.xpath(actionDropDown.replace("{0}", AsnShipmentNo))).click();

			waitForObj(3000);

			pdfResultReport.addStepDetails("clickGrnListActionMenu", "Should click ActionMenu",
					"Successfully  Should click ActionMenu" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickGrnListActionMenu");
		} catch (Exception e) {
			log.fatal("Unable to click ActionMenu" + e.getMessage());
			pdfResultReport.addStepDetails("clickGrnListActionMenu", "Should click ActionMenu",
					"Unable to click ActionMenu" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void VerifyViewAndCreateGRNIsDisplayed(String AsnShipmentNo) throws Throwable {
		try {
			log.info("started executing the method:: VerifyViewAndCreateGRNIsDisplayed");

			WebDriver driver = ThreadLocalWebdriver.getDriver();

			String createGrn = "//*[contains(text(),'{0}')]/../following-sibling::td//child::button[@data-toggle='dropdown']/..//ul/li/a[contains(text(),'Create GRN')]";

			boolean displayed = driver.findElement(By.xpath(createGrn.replace("{0}", AsnShipmentNo))).isDisplayed();

			String view = "//*[contains(text(),'{0}')]/../following-sibling::td//child::button[@data-toggle='dropdown']/..//ul/li/a[contains(text(),'View')]";

			boolean displayed1 = driver.findElement(By.xpath(view.replace("{0}", AsnShipmentNo))).isDisplayed();

			Assert.assertTrue(displayed, "Create GRN is displayed");
			Assert.assertTrue(displayed1, "View is displayed");
			pdfResultReport.addStepDetails("VerifyViewAndCreateGRNIsDisplayed",
					"View and Create GRN must be display sucessfully",
					"Successfully displayed View and Create GRN" + " ", "Pass", "Y");

			driver.findElement(By.xpath(view.replace("{0}", AsnShipmentNo))).click();
			waitForObj(5000);

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			pdfResultReport.addStepDetails("VerifyViewAndCreateGRNIsDisplayed",
					"Preview ASN page must be display sucessfully", "Successfully displayed preview ASN page" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: VerifyViewAndCreateGRNIsDisplayed");
		} catch (Exception e) {
			log.fatal("Unable to display View and Create GRN" + e.getMessage());
			pdfResultReport.addStepDetails("VerifyViewAndCreateGRNIsDisplayed",
					"View and Create GRN must be display sucessfully",
					"Unable to display View and Create GRN" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void CreateASN() throws Throwable {
		try {
			log.info("started executing the method:: CreateASN");

			// String number = String.valueOf(getrandomInterger(1000, 1000000));
			JSClick(tendercreationlocators.CreateASN, "CreateASN");
			etendercomponentobj.waitForSpinnerToDisappear();
			waitForObj(5000);
			select(tendercreationlocators.SelectPONum, eTenderComponent.getDataFromPropertiesFile("poDocNum"));

			String ASNDate = localdatetime.format(DateTimeFormatter.ofPattern("dd-MM-[yy]"));
			click(tendercreationlocators.ASNDate, "ASNDate");
			clear(tendercreationlocators.ASNDate, "ASNDate");
			set(tendercreationlocators.ASNDate, ASNDate, "ASNDate");

			String ASNREF = "REF_".concat(eTenderComponent.getDataFromPropertiesFile("poDocNum"));
			click(tendercreationlocators.ASNReference, "ASNReference");
			set(tendercreationlocators.ASNReference, ASNREF, "ASNReference");

			String ASNCarrier = "CARRIER_".concat(eTenderComponent.getDataFromPropertiesFile("poDocNum"));
			click(tendercreationlocators.ASNCarrier, "ASNCarrier");
			set(tendercreationlocators.ASNCarrier, ASNCarrier, "ASNCarrier");

			String ASNShippingMethod = "ShippingMethod_".concat(eTenderComponent.getDataFromPropertiesFile("poDocNum"));
			click(tendercreationlocators.ASNShippingMethod, "ASNShippingMethod");
			set(tendercreationlocators.ASNShippingMethod, ASNShippingMethod, "ASNShippingMethod");

			String ASNShippingTracking1 = "ShippingTracking_"
					.concat(eTenderComponent.getDataFromPropertiesFile("poDocNum"));
			click(tendercreationlocators.ASNShippertracking, "ASNShippertracking");
			set(tendercreationlocators.ASNShippertracking, ASNShippingTracking1, "ASNShippertracking");
			eTenderComponent.updateDataIntoPropertyFile("ASNShippingTracking", ASNShippingTracking1);

			LocalDateTime localdatetime1 = LocalDateTime.now().plusDays(25);
			String EstimatedDeliveryDate = localdatetime1.format(DateTimeFormatter.ofPattern("dd-MM-[yy]"));
			click(tendercreationlocators.ASNEstimatedDeliveryTime, "ASNEstimatedDeliveryTime");
			clear(tendercreationlocators.ASNEstimatedDeliveryTime, "ASNEstimatedDeliveryTime");
			waitForObj(3000);
			set(tendercreationlocators.ASNEstimatedDeliveryTime, EstimatedDeliveryDate, "ASNEstimatedDeliveryTime");

			click(tendercreationlocators.ASNAdditionalNote, "ASNAdditionalNote");
			set(tendercreationlocators.ASNAdditionalNote, "Shipment Additional Note", "ASNAdditionalNote");

			String ASNShipmentnumber = "ShippingTracking_"
					.concat(eTenderComponent.getDataFromPropertiesFile("poDocNum"));
			click(tendercreationlocators.ASNShipmentnumber, "ASNShipmentnumber");
			set(tendercreationlocators.ASNShipmentnumber, ASNShipmentnumber, "ASNShipmentnumber");
			waitForObj(3000);

			pdfResultReport.addStepDetails("SelectASNModule", "ASN Module should be created successfully",
					"Successfully clicked CreateASN " + " ", "Pass", "Y");
			log.info("completed executing the method:: CreateASN");
		} catch (Exception e) {
			log.fatal("Unable to click on ASN Module" + e.getMessage());
			pdfResultReport.addStepDetails("SelectASNModule", "ASN Module should be created successfully",
					"Unable to click on CreateASN" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}

	}
	public void ASNApproverLogin() throws Throwable {
		try {
			log.info("started executing the method:: ASNApproverLogin");
			click(tendercreationlocators.login, "login");
			waitForElement(tendercreationlocators.userName, 3000);
			set(tendercreationlocators.userName, pdfResultReport.testData.get("ASNApproverUserName"), "userName");
			set(tendercreationlocators.password, pdfResultReport.testData.get("AppPassword"), "password");
			click(tendercreationlocators.okButton, "okButton");
			waitForElement(tendercreationlocators.dashboardIcon, 5000);
			pdfResultReport.addStepDetails("ASNApproverLogin", "ASN Approver must be sucessfully logged in",
					"Successfully logged in as ASN approver" + " ", "Pass", "Y");
			log.info("completed executing the method:: ASNApproverLogin");
		} catch (Exception e) {
			log.fatal("Unable to login in as PO approver" + e.getMessage());
			pdfResultReport.addStepDetails("ASNApproverLogin", "ASN Approver must be sucessfully logged in",
					"Unable to login in as ASN approver" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void navigateToASNList() throws Throwable {
		try {
			log.info("started executing the method:: navigateToASNList");
			JSClick(tendercreationlocators.ASN, "ASN");
			waitForObj(3000);
			JSClick(tendercreationlocators.approveASN, "approveASN");
			waitForElementToBeVisible(By.xpath("(//section[@id='main-content-nw']//table//tbody//tr[2])[1]"));
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			pdfResultReport.addStepDetails("navigateToASNList", "ASN List page must be naviagte successfully",
					"Successfully navigated to ASN List page" + " ", "Pass", "Y");
			log.info("completed executing the method:: navigateToASNList");
		} catch (Exception e) {
			log.fatal("Not able to navigate to ASN List page" + e.getMessage());
			pdfResultReport.addStepDetails("navigateToASNList", "ASN List page must be naviagte successfully",
					"Unable to navigate to ASN List page" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void searchThePODocNumber() throws Throwable {
		try {
			log.info("started executing the method:: searchThePODocNumber");
			clear(tendercreationlocators.search, "Clear the TenderRefNumber");
			set(tendercreationlocators.search, eTenderComponent.getDataFromPropertiesFile("poDocNum"),
					"search The PO Doc Number");
			pdfResultReport.addStepDetails("searchThePODocNumber", "PO Number must be display sucessfully",
					"Sucessfully displayed PO Number" + " ", "Pass", "Y");
			log.info("completed executing the method:: searchThePODocNumber");
		} catch (Exception e) {
			log.fatal("Unable to display PO Number" + e.getMessage());
			pdfResultReport.addStepDetails("searchThePODocNumber", "PO Number must be display sucessfully",
					"Unable to display PO Number" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void clickOnApproveOrRejectUnderActionDropdown() throws Throwable {
		try {
			log.info("started executing the method:: clickOnApproveOrRejectUnderActionDropdown");
			click(tendercreationlocators.actionDropdownASN, "actionDropdownASN");
			waitForElementToBeVisible(tendercreationlocators.approveOrReject);
			highlight(tendercreationlocators.approveOrReject);
			pdfResultReport.addStepDetails("clickOnApproveOrRejectUnderActionDropdown",
					"ApproveOrReject link must be display successfully",
					"Successfully displayed approveOrReject link" + " ", "Pass", "Y");
			click(tendercreationlocators.approveOrReject, "approveOrReject");
			waitForElementToBeVisible(tendercreationlocators.selectPONo);
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForObj(3000);
			pdfResultReport.addStepDetails("clickOnApproveOrRejectUnderActionDropdown",
					"Approve Advanced Shipment Notice page must be display successfully",
					"Successfully displayed Approve Advanced Shipment Notice" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickOnApproveOrRejectUnderActionDropdown");
		} catch (Exception e) {
			log.fatal("Not able to display Approve Advanced Shipment Notice page" + e.getMessage());
			pdfResultReport.addStepDetails("clickOnApproveOrRejectUnderActionDropdown",
					"Approve Advanced Shipment Notice page must be display successfully",
					"Unable to display Approve Advanced Shipment Notice page" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void ADDorDeleteBoxes(String ASNQuantity, String Weight, String UOM_Weight) throws Throwable {
		try {
			log.info("started executing the method:: TabWhatIamShipping");

			click(tendercreationlocators.TabWhatIamShipping, "TabWhatIamShipping");
			waitForObj(2000);
			etendercomponentobj.waitForSpinnerToDisappear();
			click(tendercreationlocators.BoxesOnly, "BoxesOnly");
			waitForObj(2000);
			click(tendercreationlocators.AddBoxe, "AddBoxe");

			// box1
			click(tendercreationlocators.Boxe1, "Boxe1");

			set(tendercreationlocators.Boxe1, "Box_1", "BoxesOnly");

			click(tendercreationlocators.ItemDescription1, "ItemDescription1");

			click(tendercreationlocators.radiobtnItemDescription1, "radiobtnItemDescription1");

			click(tendercreationlocators.BtnOk, "BtnOk");

			clear(tendercreationlocators.ASNQuantity1, "ASNQuantity1");

			set(tendercreationlocators.ASNQuantity1, ASNQuantity, "ASNQuantity1");

			set(tendercreationlocators.Weight1, Weight, "Weight1");

			select(tendercreationlocators.UOMWeight1, UOM_Weight);// 然
			waitForObj(2000);

			pdfResultReport.addStepDetails("ADDorDeleteBoxes", "Boxes to be added successfully",
					"Successfully added  Boxes " + " ", "Pass", "Y");
			log.info("completed executing the method:: ADDorDeleteBoxes");

			JSClick(tendercreationlocators.DeleteBox, "DeleteBox");
			waitForObj(3000);
			JSClick(tendercreationlocators.BoXDeleteConfirm, "BoXDeleteConfirm");

			pdfResultReport.addStepDetails("ADDorDeleteBoxes", "Boxes to be  deleted successfully",
					"Successfully  deleted Boxes " + " ", "Pass", "Y");
			log.info("completed executing the method:: ADDorDeleteBoxes");
		} catch (Exception e) {
			log.fatal("Unable to add or delete boxes" + e.getMessage());
			pdfResultReport.addStepDetails("ADDorDeleteBoxes", "Boxes to be added and deleted successfully",
					"Unable to add or delete boxes" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}

	}

	public void navigateToASNListInBidderLogin() throws Throwable {
		try {
			log.info("started executing the method:: navigateToASNListInBidderLogin");
			JSClick(tendercreationlocators.ASN, "ASN");
			waitForObj(3000);
			JSClick(tendercreationlocators.ASNListInBidderLogin, "ASNListInBidderLogin");
			waitForElementToBeVisible(By.xpath("(//section[@id='main-content-nw']//table//tbody//tr[2])[1]"));
			String ASNPageTitle = text(tendercreationlocators.ASNListPage);
			if (ASNPageTitle.contains("ASN List")) {
				System.out.println("Title is displayed");
			}

			pdfResultReport.addStepDetails("navigateToASNListInBidderLogin",
					"ASN List page must be naviagte successfully", "Successfully navigated to ASN List page" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: navigateToASNListInBidderLogin");
		} catch (Exception e) {
			log.fatal("Not able to navigate to ASN List page" + e.getMessage());
			pdfResultReport.addStepDetails("navigateToASNListInBidderLogin",
					"ASN List page must be naviagte successfully",
					"Unable to navigate to ASN List page" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void validationsInASNListPage() throws Throwable {
		try {
			log.info("started executing the method:: validationsInASNListPage");

			pdfResultReport.addStepDetails("validationsInASNListPage", "All the elements are validated successfully",
					"Successfully validated all the elements in ASN List page " + " ", "Pass", "Y");
			log.info("completed executing the method:: validationsInASNListPage");
		} catch (Exception e) {
			log.fatal("Unable validated elements in ASN list page" + e.getMessage());
			pdfResultReport.addStepDetails("validationsInASNListPage", "All the elements are validated successfully",
					"Unable validated elements in ASN list page" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}

	}

	public void ASNNumberValidation() throws Throwable {
		try {
			log.info("started executing the method:: ASNNumberValidation");

			waitForElementToBeVisible(tendercreationlocators.ASNNo);
			IsElementPresent(tendercreationlocators.ASNNo);
			highlight(tendercreationlocators.ASNNo);
			waitForObj(2000);
			WebElement ele = ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.ASNNo);
			String asnNo = getattributevalue(ele, "title");
			System.out.println(asnNo);

			pdfResultReport.addStepDetails("ASNNumberValidation", "ASN No should be generated",
					"Successfully generated ASN no " + " ", "Pass", "Y");
			log.info("completed executing the method:: ASNNumberValidation");
		} catch (Exception e) {
			log.fatal("Unable generate ASN no." + e.getMessage());
			pdfResultReport.addStepDetails("ASNNumberValidation", "ASN No should be generated successfully",
					"Unable generate ASN no." + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}

	}

	public void verifyDraftStatus() throws Throwable {
		try {
			log.info("started executing the method:: verifyDraftStatus");
			clear(tendercreationlocators.search, "Clear the TenderRefNumber");
			set(tendercreationlocators.search, eTenderComponent.getDataFromPropertiesFile("poDocNum"),
					"search The PO Doc Number");
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.draftStatus);
			waitForObj(2000);
			pdfResultReport.addStepDetails("verifyDraftStatus", "Successfully verified in draft status",
					"Successfully verified in draft status" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyDraftStatus");
		} catch (Exception e) {
			log.fatal("Unable to verify the draft status" + e.getMessage());
			pdfResultReport.addStepDetails("verifyDraftStatus", "Successfully verified in draft status",
					"Unable to verify the draft status" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void dataValidationForInvoiceTab() throws Throwable {
		try {
			log.info("started executing the method:: dataValidationForInvoiceTab");
			click(tendercreationlocators.alertTabOk, "alertTabOk");
			waitForElementToBeVisible(tendercreationlocators.TabInvoice);
			waitForObj(3000);

			pdfResultReport.addStepDetails("ASNNumberValidation", "ASN No should be generated",
					"Successfully generated ASN no " + " ", "Pass", "Y");
			log.info("completed executing the method:: ASNNumberValidation");
		} catch (Exception e) {
			log.fatal("Unable generate ASN no." + e.getMessage());
			pdfResultReport.addStepDetails("ASNNumberValidation", "ASN No should be generated successfully",
					"Unable generate ASN no." + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void verifyPOnumberIsDisplayed() throws Throwable {
		try {
			log.info("started executing the method:: verifyPOnumberIsDisplayed");
			searchThePODocNumber();
			waitForObj(2000);
			String records = ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.records).getText();
			if (records.equals("0")) {
				System.out.println("No records are available");
			}
			pdfResultReport.addStepDetails("verifyPOnumberIsDisplayed", "No records should be displayed",
					"Successfully no records are present " + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyPOnumberIsDisplayed");
		} catch (Exception e) {
			log.fatal("Unable to verify records" + e.getMessage());
			pdfResultReport.addStepDetails("verifyPOnumberIsDisplayed", "No records should be displayed successfully",
					"Unable to verify records" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void verifyStatus() throws Throwable {
		try {
			log.info("started executing the method:: verifyStatus");
			clear(tendercreationlocators.search, "Clear the TenderRefNumber");
			set(tendercreationlocators.search, "1494", "search The PO Doc Number");
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.draftStatus);
			waitForObj(2000);
			pdfResultReport.addStepDetails("verifyStatus", "ASN No should be generated",
					"Successfully generated ASN no " + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyStatus");
		} catch (Exception e) {
			log.fatal("Unable generate ASN no." + e.getMessage());
			pdfResultReport.addStepDetails("verifyStatus", "ASN No should be generated successfully",
					"Unable generate ASN no." + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void verifyColoumnVisibilityAndCreateASNButton() throws Throwable {
		try {
			log.info("started executing the method:: verifyColoumnVisibilityAndCreateASNButton");
			waitForElementToBeVisible(tendercreationlocators.columnVisibility);
			IsElementPresent(tendercreationlocators.columnVisibility);
			IsElementPresent(tendercreationlocators.createASNButton);
			pdfResultReport.addStepDetails("verifyColoumnVisibilityAndCreateASNButton", "Validation is successfully",
					"Successfully validated Coloumn Visibility And Create ASN Buttons " + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyStatus");
		} catch (Exception e) {
			log.fatal("Unable to validate buttons" + e.getMessage());
			pdfResultReport.addStepDetails("verifyColoumnVisibilityAndCreateASNButton", "Validation is successfully",
					"Unable to validate buttons" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void enterShipmentNumber(String AsnShipmentNo) throws Throwable {
		try {
			log.info("started executing the method:: enterShipmentNumber");
			String AsnShipmentNoBy = "//*[contains(@id,'typeahead')]/li/a/strong[text()='{0}']";
			clear(tendercreationlocators.enterShipmentNumber, "Clear the TenderRefNumber");
			waitForObj(2000);
			set(tendercreationlocators.entershipmentFieldBy, AsnShipmentNo, "AsnShipmentNo");
			ThreadLocalWebdriver.getDriver().findElement(By.xpath(AsnShipmentNoBy.replace("{0}", AsnShipmentNo)))
					.click();

			waitForObj(2000);
			pdfResultReport.addStepDetails("enterShipmentNumber",
					"Shipping Tracking Number must be display sucessfully",
					"Sucessfully displayed Shipping Tracking Number" + " ", "Pass", "Y");
			log.info("completed executing the method:: enterShipmentNumber");
		} catch (Exception e) {
			log.fatal("Not Able to display Shipping Tracking Number" + e.getMessage());
			pdfResultReport.addStepDetails("enterShipmentNumber",
					"Shipping Tracking Number must be display sucessfully",
					"Unable to display Shipping Tracking Number" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}



	public void verifyActionDropDownListElements(List<String> list) throws Throwable {
		try {
			log.info("started executing the method:: verifyActionDropDownListElements");
			waitForElementToBeVisible(By.xpath("//table[contains(@class,'asn-list-table')]/tbody/tr[2]"));
			clear(tendercreationlocators.search, "search");
			set(tendercreationlocators.search, asnNo, "search with ASN no.");
			waitForObj(3000);
			click(tendercreationlocators.action, "action");
			waitForElementToBeVisible(By.xpath("//ul[@class='dropdown-menu extended logout big_actv']"));
			List<WebElement> actionList = ThreadLocalWebdriver.getDriver().findElements(
					By.xpath("//td[text()='" + asnNo + "']/../td//button[@id='menu1']/following-sibling::ul/li/a"));
			ArrayList<String> ListText = new ArrayList<String>();
			for (WebElement actionListText : actionList) {

				ListText.add(actionListText.getText());
			}
			System.out.println(ListText);
			boolean b = ListText.containsAll(list);
			Assert.assertTrue(b);
			pdfResultReport.addStepDetails("verifyActionDropDownListElements", "Validation is successfully",
					"Successfully verified elements " + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyActionDropDownListElements");
		} catch (Exception e) {
			log.fatal("Unable to validate buttons" + e.getMessage());
			pdfResultReport.addStepDetails("verifyActionDropDownListElements", "Validation is successfully",
					"Unable to verify elements" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void verifyView() throws Throwable {
		try {
			log.info("started executing the method:: verifyView");
			waitForElementToBeVisible(By.xpath("//table[contains(@class,'asn-list-table')]/tbody/tr[2]"));
			clear(tendercreationlocators.search, "search");
			set(tendercreationlocators.search, asnNo, "search with ASN no.");
			waitForObj(3000);
			click(tendercreationlocators.action, "action");
			waitForElementToBeVisible(By.xpath("//ul[@class='dropdown-menu extended logout big_actv']"));
			click(tendercreationlocators.viewASN, "viewASN");
			waitForElementToBeVisible(tendercreationlocators.previewASN);
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.ASNNo(asnNo));
			highlight(tendercreationlocators.ASNNo(asnNo));
			IsElementPresent(tendercreationlocators.asnInformation);
			IsElementPresent(tendercreationlocators.myInformation);
			IsElementPresent(tendercreationlocators.shipmentInfo);
			IsElementPresent(tendercreationlocators.shipping);
			IsElementPresent(tendercreationlocators.deliveryChallan);
			IsElementPresent(tendercreationlocators.invoiceCheck);
			waitForObj(2000);
			click(tendercreationlocators.previewAll_close, "previewAll_close");
			waitForElementToBeVisible(By.xpath("//table[contains(@class,'asn-list-table')]/tbody/tr[2]"));
			waitForObj(2000);
			pdfResultReport.addStepDetails("verifyActionDropDownListElements", "Validation is successfully",
					"Successfully verified elements " + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyActionDropDownListElements");
		} catch (Exception e) {
			log.fatal("Unable to validate buttons" + e.getMessage());
			pdfResultReport.addStepDetails("verifyActionDropDownListElements", "Validation is successfully",
					"Unable to verify elements" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void verifyPreparedStatus() throws Throwable {
		try {
			log.info("started executing the method:: verifyPreparedStatus");
			clear(tendercreationlocators.search, "Clear the TenderRefNumber");
			set(tendercreationlocators.search, asnNo, "search The PO Doc Number");
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.preparedStatus);
			waitForObj(2000);
			pdfResultReport.addStepDetails("verifyPreparedStatus", "Successfully verified in prepared status",
					"Successfully verified in prepared status" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyPreparedStatus");
		} catch (Exception e) {
			log.fatal("Unable to verify the draft status" + e.getMessage());
			pdfResultReport.addStepDetails("verifyPreparedStatus", "Successfully verified in prepared status",
					"Unable to verify the prepared status" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void CreateASNWithInspectorRole() throws Throwable {
		try {
			log.info("started executing the method:: CreateASN");

			// String number = String.valueOf(getrandomInterger(1000, 1000000));
			JSClick(tendercreationlocators.CreateASN, "CreateASN");
			etendercomponentobj.waitForSpinnerToDisappear();
			waitForObj(5000);
			select(tendercreationlocators.SelectPONum, eTenderComponent.getDataFromPropertiesFile("poDocNum"));

			click(tendercreationlocators.ChkbxInspectionReq, "ChkbxInspectionReq");
			waitForObj(3000);
			click(tendercreationlocators.ASNInspectionDetails, "ASNInspectionDetails");
			set(tendercreationlocators.ASNInspectionDetails, "To be Inspected", "ASNInspectionDetails");

			String ASNDate = localdatetime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
			click(tendercreationlocators.ASNDate, "ASNDate");
			clear(tendercreationlocators.ASNDate, "ASNDate");
			set(tendercreationlocators.ASNDate, ASNDate, "ASNDate");

			String ASNREF = "REF_".concat(eTenderComponent.getDataFromPropertiesFile("poDocNum"));
			click(tendercreationlocators.ASNReference, "ASNReference");
			set(tendercreationlocators.ASNReference, ASNREF, "ASNReference");

			String ASNCarrier = "CARRIER_".concat(eTenderComponent.getDataFromPropertiesFile("poDocNum"));
			click(tendercreationlocators.ASNCarrier, "ASNCarrier");
			set(tendercreationlocators.ASNCarrier, ASNCarrier, "ASNCarrier");

			String ASNShippingMethod = "ShippingMethod_".concat(eTenderComponent.getDataFromPropertiesFile("poDocNum"));
			click(tendercreationlocators.ASNShippingMethod, "ASNShippingMethod");
			set(tendercreationlocators.ASNShippingMethod, ASNShippingMethod, "ASNShippingMethod");

			String ASNShippingTracking = "ShippingTracking_"
					.concat(eTenderComponent.getDataFromPropertiesFile("poDocNum"));
			click(tendercreationlocators.ASNShippertracking, "ASNShippertracking");
			set(tendercreationlocators.ASNShippertracking, ASNShippingTracking, "ASNShippertracking");

			LocalDateTime localdatetime1 = LocalDateTime.now().plusDays(25);
			String EstimatedDeliveryDate = localdatetime1.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
			click(tendercreationlocators.ASNEstimatedDeliveryTime, "ASNEstimatedDeliveryTime");
			clear(tendercreationlocators.ASNEstimatedDeliveryTime, "ASNEstimatedDeliveryTime");
			set(tendercreationlocators.ASNEstimatedDeliveryTime, EstimatedDeliveryDate, "ASNEstimatedDeliveryTime");

			click(tendercreationlocators.ASNAdditionalNote, "ASNAdditionalNote");
			set(tendercreationlocators.ASNAdditionalNote, "Shipment Additional Note", "ASNAdditionalNote");

			String ASNShipmentnumber = "ShippingTracking_"
					.concat(eTenderComponent.getDataFromPropertiesFile("poDocNum"));
			click(tendercreationlocators.ASNShipmentnumber, "ASNShipmentnumber");
			set(tendercreationlocators.ASNShipmentnumber, ASNShipmentnumber, "ASNShipmentnumber");
			waitForObj(3000);

			pdfResultReport.addStepDetails("SelectASNModule", "ASN Module should be created successfully",
					"Successfully clicked CreateASN " + " ", "Pass", "Y");
			log.info("completed executing the method:: CreateASN");
		} catch (Exception e) {
			log.fatal("Unable to click on ASN Module" + e.getMessage());
			pdfResultReport.addStepDetails("SelectASNModule", "ASN Module should be created successfully",
					"Unable to click on CreateASN" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}

	}

	public void ASNInspectorLogin() throws Throwable {
		try {
			log.info("started executing the method:: ASNInspectorLogin");
			click(tendercreationlocators.login, "login");
			waitForElement(tendercreationlocators.userName, 3000);
			set(tendercreationlocators.userName, pdfResultReport.testData.get("ASNInspectorUserName"), "userName");
			set(tendercreationlocators.password, pdfResultReport.testData.get("AppPassword"), "password");
			click(tendercreationlocators.okButton, "okButton");
			waitForElement(tendercreationlocators.dashboardIcon, 5000);
			pdfResultReport.addStepDetails("ASNInspectorLogin", "ASN Inspector must be sucessfully logged in",
					"Successfully logged in as ASN Inspector" + " ", "Pass", "Y");
			log.info("completed executing the method:: ASNInspectorLogin");
		} catch (Exception e) {
			log.fatal("Unable to login in as ASN Inspector" + e.getMessage());
			pdfResultReport.addStepDetails("ASNInspectorLogin", "ASN Inspector must be sucessfully logged in",
					"Unable to login in as ASN approver" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void navigateToASNInspectionList() throws Throwable {
		try {
			log.info("started executing the method:: navigateToASNInspectionList");
			JSClick(tendercreationlocators.ASN, "ASN");
			waitForObj(3000);
			JSClick(tendercreationlocators.Inspection, "Inspection");
			waitForElementToBeVisible(By.xpath("(//section[@id='main-content-nw']//table//tbody//tr[2])[1]"));
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			pdfResultReport.addStepDetails("navigateToASNList", "ASN List page must be naviagte successfully",
					"Successfully navigated to ASN List page" + " ", "Pass", "Y");
			log.info("completed executing the method:: navigateToASNInspectionList");
		} catch (Exception e) {
			log.fatal("Not able to navigate to ASN List page" + e.getMessage());
			pdfResultReport.addStepDetails("navigateToASNInspectionList", "ASN List page must be naviagte successfully",
					"Unable to navigate to ASN List page" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void VerifyASNforInspection() throws Throwable {
		try {
			log.info("started executing the method:: VerifyASNforInspection");
			String ASNNum = eTenderComponent.getDataFromPropertiesFile("ASNNum");
			JSClick(tendercreationlocators.Pending, "Pending");
			etendercomponentobj.waitForSpinnerToDisappear();
			JSClick(tendercreationlocators.InspectionActionBtn(ASNNum), "InspectionActionBtn");
			waitForObj(3000);
			highlight(tendercreationlocators.Claim(ASNNum));
			highlight(tendercreationlocators.ViewASN(ASNNum));

			pdfResultReport.addStepDetails("navigateToASNList", "ASN List  must be verified successfully",
					"Successfully verified  ASN List " + " ", "Pass", "Y");
			log.info("completed executing the method:: VerifyASNforInspection");
		} catch (Exception e) {
			log.fatal("Not able to verify ASN List " + e.getMessage());
			pdfResultReport.addStepDetails("VerifyASNforInspection", "ASN List  must be verified successfully",
					"Unable to verify ASN List " + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void ClaimASN() throws Throwable {
		try {
			log.info("started executing the method:: ClaimASN");
			String ASNNum = eTenderComponent.getDataFromPropertiesFile("ASNNum");
			JSClick(tendercreationlocators.Claim(ASNNum), "Claimbtn");
			etendercomponentobj.waitForSpinnerToDisappear();
			String status = text(tendercreationlocators.claimAssigned(ASNNum));
			waitForObj(3000);
			if (status.equals("Assigned")) {
				System.out.println("CLaim status of ASN is Assigned");
				pdfResultReport.addStepDetails("ClaimASN", "Claim status should be changed successfully",
						"Successfully changed claim status to Assigned " + " ", "Pass", "Y");
			} else {
				pdfResultReport.addStepDetails("ClaimASN", "ASN should be claimed successfully",
						"Unable to change claim Status", "Fail", "N");
			}

			log.info("completed executing the method:: ClaimASN");
		} catch (Exception e) {
			log.fatal("Not able to claim ASN  " + e.getMessage());
			pdfResultReport.addStepDetails("ClaimASN", "ASN should be claimed successfully",
					"Unable to claim ASN  " + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void clickOnApproveOrRejectUnderActionDropdownInspection() throws Throwable {
		try {
			log.info("started executing the method:: clickOnApproveOrRejectUnderActionDropdownInspection");
			String ASNNum = eTenderComponent.getDataFromPropertiesFile("ASNNum");
			JSClick(tendercreationlocators.InspectionActionBtn(ASNNum), "InspectionActionBtn");
			waitForObj(3000);
			highlight(tendercreationlocators.ApproveOrReview(ASNNum));
			JSClick(tendercreationlocators.ApproveOrReview(ASNNum), "ApproveOrReview btn");
			etendercomponentobj.waitForSpinnerToDisappear();
			pdfResultReport.addStepDetails("clickOnApproveOrRejectUnderActionDropdownInspection",
					"Approve or Rejected button must be clicked successfully",
					"Successfully displayed Approve Advanced Shipment Notice" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickOnApproveOrRejectUnderActionDropdown");
		} catch (Exception e) {
			log.fatal("Not able to click on ApproveOrReject button" + e.getMessage());
			pdfResultReport.addStepDetails("clickOnApproveOrRejectUnderActionDropdownInspection",
					"Approve or Rejected button must be clicked successfully",
					"Not able to click on ApproveOrReject button" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void VerifyOnApproveOrRejectUnderActionDropdownInspection() throws Throwable {
		try {
			log.info("started executing the method:: clickOnApproveOrRejectUnderActionDropdownInspection");
			String ASNNum = eTenderComponent.getDataFromPropertiesFile("ASNNum");
			JSClick(tendercreationlocators.InspectionActionBtn(ASNNum), "InspectionActionBtn");
			waitForObj(3000);
			highlight(tendercreationlocators.ApproveOrReview(ASNNum));
			highlight(tendercreationlocators.ViewASN(ASNNum));
			pdfResultReport.addStepDetails("clickOnApproveOrRejectUnderActionDropdownInspection",
					"Approve or Rejected button must be clicked successfully",
					"Successfully displayed Approve Advanced Shipment Notice" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickOnApproveOrRejectUnderActionDropdown");
		} catch (Exception e) {
			log.fatal("Not able to click on ApproveOrReject button" + e.getMessage());
			pdfResultReport.addStepDetails("clickOnApproveOrRejectUnderActionDropdownInspection",
					"Approve or Rejected button must be clicked successfully",
					"Not able to click on ApproveOrReject button" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void navigatToEachTabInApproveAdvancedShipmentNoticePage() throws Throwable {
		try {
			log.info("started executing the method:: navigatToEachTabInApproveAdvancedShipmentNoticePage");
			etendercomponentobj.waitForSpinnerToDisappear();
			click(tendercreationlocators.supplierInformation, "supplierInformation");
			waitForElement(tendercreationlocators.superLegancyCode, 5000);
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			click(tendercreationlocators.shipmentInformation, "shipmentInformation");
			waitForElement(tendercreationlocators.bolNumber, 5000);
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			click(tendercreationlocators.whatTheyAreShippingToUs, "whatTheyAreShippingToUs");
			waitForElement(tendercreationlocators.boxNumber, 5000);
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			click(tendercreationlocators.ASNDeliveryChallan, "ASNDeliveryChallan");
			waitForElement(tendercreationlocators.buyerAcceptance, 5000);
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			select(tendercreationlocators.buyerAcceptance, "Accepted");

			click(tendercreationlocators.invoice, "invoice");
			waitForElement(tendercreationlocators.poNumber, 5000);
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			click(tendercreationlocators.ASNApproverSave, "ASNApproverSave");
			waitForObj(5000);
			pdfResultReport.addStepDetails("navigatToEachTabInApproveAdvancedShipmentNoticePage",
					"Each and every Tab must be navigate successfully",
					"Successfully navigated each and every tab" + " ", "Pass", "Y");
			log.info("completed executing the method:: navigatToEachTabInApproveAdvancedShipmentNoticePage");
		} catch (Exception e) {
			log.fatal("Not able to navigate to each and every tab" + e.getMessage());
			pdfResultReport.addStepDetails("navigatToEachTabInApproveAdvancedShipmentNoticePage",
					"Each and every Tab must be navigate successfully",
					"Unable to navigate to each and every tab" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void approvingTheASN() throws Throwable {
		try {
			log.info("started executing the method:: approvingTheASN");
			etendercomponentobj.waitForSpinnerToDisappear();
			click(tendercreationlocators.approveOrHold, "approveOrHold");
			waitForElementToBeVisible(tendercreationlocators.approverCommentSection);
			set(tendercreationlocators.approverCommentSection, "Approve", "approverCommentSection");
			waitForObj(3000);
			pdfResultReport.addStepDetails("approvingTheASN", "ApproverComment must be enter successfully",
					"Successfully entered approver comment" + " ", "Pass", "Y");
			click(tendercreationlocators.approveButton, "approveButton");
			waitForElementToBeVisible(tendercreationlocators.search);
			etendercomponentobj.waitForSpinnerToDisappear();
			pdfResultReport.addStepDetails("approvingTheASN", "ASN must be approve successfully",
					"Sucessfully approved ASN" + " ", "Pass", "Y");
			log.info("completed executing the method:: approvingTheASN");
		} catch (Exception e) {
			log.fatal("Not able to approve ASN" + e.getMessage());
			pdfResultReport.addStepDetails("approvingTheASN", "ASN must be approve successfully",
					"Unable to approve ASN" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void clickOnEditUnderActionDropdown() throws Throwable {
		try {
			log.info("started executing the method:: clickOnEditUnderActionDropdown");
			String ponum = eTenderComponent.getDataFromPropertiesFile("poDocNum");
			click(tendercreationlocators.ActionBtnBidder(ponum), "ActionBtnBidder");
			waitForElementToBeVisible(tendercreationlocators.EditASN(ponum));
			highlight(tendercreationlocators.EditASN(ponum));
			JSClick(tendercreationlocators.EditASN(ponum), "EditASN");

			pdfResultReport.addStepDetails("clickOnEditUnderActionDropdown",
					"ApproveOrReject link must be display successfully",
					"Successfully displayed approveOrReject link" + " ", "Pass", "Y");

			pdfResultReport.addStepDetails("clickOnEditUnderActionDropdown",
					"Edit button under action dropdown must be clicked successfully",
					"Successfully clicked on EDIT button" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickOnEditUnderActionDropdown");
		} catch (Exception e) {
			log.fatal("Unable to click on EDIT button" + e.getMessage());
			pdfResultReport.addStepDetails("clickOnEditUnderActionDropdown",
					"Edit button under action dropdown must be clicked successfully",
					"Unable to click on EDIT button" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	String PQuantity = null;

	public void VerifyPOItemRemainingTabInWhatIAmShipingPage() throws Throwable {
		try {
			log.info("started executing the method:: navigatToEachTabInApproveAdvancedShipmentNoticePage");
			etendercomponentobj.waitForSpinnerToDisappear();
			click(tendercreationlocators.shipmentInformation, "shipmentInformation");
			waitForElement(tendercreationlocators.bolNumber, 5000);
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			click(tendercreationlocators.TabWhatIamShipping, "TabWhatIamShipping");

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			System.out.println("Quantity was:" + PQuantity);
			String PQuantityEdit = text(tendercreationlocators.POItemRemaining);
			System.out.println("Item ");
			if (PQuantityEdit.equals(PQuantity)) {
				highlight(tendercreationlocators.POItemRemaining);
				waitForObj(5000);
				System.out.println("PO Item remaining is verified");
				pdfResultReport.addStepDetails("VerifyPOItemRemainingTabInWhatIAmShipingPage",
						" Verify PO Iterm Remaining Left is matched ", "PO Iterm Remaining Left is matched" + " ",
						"Pass", "Y");
			} else {
				System.out.println("PO Item Remaining is not matched");
				pdfResultReport.addStepDetails("VerifyPOItemRemainingTabInWhatIAmShipingPage",
						" Verify PO Iterm Remaining Left is matched ", "Unable to match the item remaining" + " ",
						"Fail", "N");
			}

			pdfResultReport.addStepDetails("VerifyPOItemRemainingTabInWhatIAmShipingPage",
					" Verify PO Iterm Remaining field is displayed ",
					"PO Iterm Remaining field is displayed successfully" + " ", "Pass", "Y");

			log.info("completed executing the method:: VerifyPOItemRemainingTabInWhatIAmShipingPage");
		} catch (Exception e) {
			log.fatal("Not able todisplay POItemRemainig Tab" + e.getMessage());
			pdfResultReport.addStepDetails("VerifyPOItemRemainingTabInWhatIAmShipingPage",
					" Verify PO Iterm Remaining field is displayed ",
					"Unable to display POItemRemainig Tab" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void ASNInformationTab_validation() throws Throwable {
		try {
			log.info("started executing the method:: ASNInformationTab_validation");
			JSClick(tendercreationlocators.CreateASN, "CreateASN");
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			waitForObj(5000);
			select(tendercreationlocators.SelectPONum, eTenderComponent.getDataFromPropertiesFile("poDocNum"));
			pdfResultReport.addStepDetails("ASNInformationTab_validation",
					"PO Number must be displayed under Select PO Number drop down field sucessfully",
					"Successfully displayed PO Number under Select PO Number drop down field " + " ", "Pass", "Y");

			WebDriver driver = ThreadLocalWebdriver.getDriver();
			String ASNDate = localdatetime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
			boolean displayed = driver.findElement(By.xpath("//input[@id='0.asn_date.0']")).isDisplayed();
			Assert.assertTrue(displayed, "Current date is displayed");
			pdfResultReport.addStepDetails("ASNInformationTab_validation",
					"ASN Date field must be auto populate current date sucessfully",
					"Successfully auto populated current date in ASN Date field " + " ", "Pass", "Y");

			click(tendercreationlocators.ASNSave, "ASNSave");
			waitForElementToBeVisible(tendercreationlocators.ASNInformation);
			pdfResultReport.addStepDetails("ASNInformationTab_validation",
					"Error message must be populate if mandatory field is not fill up sucessfully",
					"Successfully populated error message when mandatory field is not filled up" + " ", "Pass", "Y");
			click(tendercreationlocators.confirmationOkBy, "confirmationOkBy");

			String ASNREF = "REF_".concat(eTenderComponent.getDataFromPropertiesFile("poDocNum"));
			click(tendercreationlocators.ASNReference, "ASNReference");
			set(tendercreationlocators.ASNReference, ASNREF, "ASNReference");

			String ASNCarrier = "CARRIER_".concat(eTenderComponent.getDataFromPropertiesFile("poDocNum"));
			click(tendercreationlocators.ASNCarrier, "ASNCarrier");
			set(tendercreationlocators.ASNCarrier, ASNCarrier, "ASNCarrier");

			String ASNShippingMethod = "ShippingMethod_".concat(eTenderComponent.getDataFromPropertiesFile("poDocNum"));
			click(tendercreationlocators.ASNShippingMethod, "ASNShippingMethod");
			set(tendercreationlocators.ASNShippingMethod, ASNShippingMethod, "ASNShippingMethod");

			String ASNShippingTracking1 = "ShippingTracking_"
					.concat(eTenderComponent.getDataFromPropertiesFile("poDocNum"));
			click(tendercreationlocators.ASNShippertracking, "ASNShippertracking");
			set(tendercreationlocators.ASNShippertracking, ASNShippingTracking1, "ASNShippertracking");
			eTenderComponent.updateDataIntoPropertyFile("ASNShippingTracking", ASNShippingTracking1);

			LocalDateTime localdatetime1 = LocalDateTime.now().plusDays(25);
			String EstimatedDeliveryDate = localdatetime1.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
			click(tendercreationlocators.ASNEstimatedDeliveryTime, "ASNEstimatedDeliveryTime");
			clear(tendercreationlocators.ASNEstimatedDeliveryTime, "ASNEstimatedDeliveryTime");
			set(tendercreationlocators.ASNEstimatedDeliveryTime, EstimatedDeliveryDate, "ASNEstimatedDeliveryTime");

			click(tendercreationlocators.ASNAdditionalNote, "ASNAdditionalNote");
			set(tendercreationlocators.ASNAdditionalNote, "Shipment Additional Note", "ASNAdditionalNote");

			String ASNShipmentnumber = "ShippingTracking_"
					.concat(eTenderComponent.getDataFromPropertiesFile("poDocNum"));
			click(tendercreationlocators.ASNShipmentnumber, "ASNShipmentnumber");
			set(tendercreationlocators.ASNShipmentnumber, ASNShipmentnumber, "ASNShipmentnumber");
			waitForObj(3000);

			pdfResultReport.addStepDetails("ASNInformationTab_validation",
					"Mandatory details should be fill in ASN Information Tab successfully",
					"Successfully filled Mandatory details in ASN Information Tab" + " ", "Pass", "Y");
			log.info("completed executing the method:: ASNInformationTab_validation");
		} catch (Exception e) {
			log.fatal("Unable to fill Mandatory details in ASN Information Tab" + e.getMessage());
			pdfResultReport.addStepDetails("ASNInformationTab_validation",
					"Mandatory details should be fill in ASN Information Tab successfully",
					"Unable to fill Mandatory details in ASN Information Tab" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}

	}

	public void TabWhatIamShippingWithBoxesOnlyPOQuantityVerify(String ASNQuantity, String Weight, String UOM_Weight)
			throws Throwable {
		try {
			log.info("started executing the method:: TabWhatIamShipping");

			JSClick(tendercreationlocators.TabWhatIamShipping, "TabWhatIamShipping");
			waitForObj(2000);
			etendercomponentobj.waitForSpinnerToDisappear();
			JSClick(tendercreationlocators.BoxesOnly, "BoxesOnly");
			waitForObj(2000);
			JSClick(tendercreationlocators.AddBoxe, "AddBoxe");

			// box1
			JSClick(tendercreationlocators.Boxe1, "Boxe1");

			set(tendercreationlocators.Boxe1, "Box_1", "BoxesOnly");

			JSClick(tendercreationlocators.ItemDescription1, "ItemDescription1");

			JSClick(tendercreationlocators.radiobtnItemDescription1, "radiobtnItemDescription1");

			JSClick(tendercreationlocators.BtnOk, "BtnOk");

			clear(tendercreationlocators.ASNQuantity1, "ASNQuantity1");

			set(tendercreationlocators.ASNQuantity1, ASNQuantity, "ASNQuantity1");

			set(tendercreationlocators.Weight1, Weight, "Weight1");

			select(tendercreationlocators.UOMWeight1, UOM_Weight);// 然
			waitForObj(2000);

			PQuantity = text(tendercreationlocators.POItemRemaining);

			pdfResultReport.addStepDetails("TabWhatIamShipping",
					"All fileds of What I am Shipping tab should be entered successfully",
					"Successfully saved What I am Shipping Tab Details " + " ", "Pass", "Y");
			log.info("completed executing the method:: TabWhatIamShipping");
		} catch (Exception e) {
			log.fatal("Unable to save My Information Tab" + e.getMessage());
			pdfResultReport.addStepDetails("TabWhatIamShipping",
					"All fileds of What I am Shipping tab should be entered successfully",
					"Unable to save What I am Shipping Tab" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}

	}

	public void DeleteASN() throws Throwable {
		try {
			log.info("started executing the method:: DeleteASN");
			String ponum = eTenderComponent.getDataFromPropertiesFile("poDocNum");
			JSClick(tendercreationlocators.ActionBtnBidder(ponum), "ActionBtnBidder");
			waitForElementToBeVisible(tendercreationlocators.DeleteASN(ponum));
			highlight(tendercreationlocators.DeleteASN(ponum));
			JSClick(tendercreationlocators.DeleteASN(ponum), "DeleteASN");
			waitForObj(3000);
			JSClick(tendercreationlocators.BtnYes, "BtnYes");
			etendercomponentobj.waitForSpinnerToDisappear();
			pdfResultReport.addStepDetails("DeleteASN", "ASN must be deleted successfully",
					"Successfully deleted ASN" + " ", "Pass", "Y");
			log.info("completed executing the method:: DeleteASN");
		} catch (Exception e) {
			log.fatal("Unable to Delete ASN" + e.getMessage());
			pdfResultReport.addStepDetails("DeleteASN", "ASN must be deleted successfully",
					"Unable to Delete ASN" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void SearchDeletedPoInASNList() throws Throwable {
		try {
			log.info("started executing the method:: SearchPo");
			etendercomponentobj.waitForSpinnerToDisappear();
			JSClick(tendercreationlocators.SearchPOINASN, "SearchPOINASN");
			waitForObj(3000);
			String ponum = eTenderComponent.getDataFromPropertiesFile("poDocNum");
			clear(tendercreationlocators.SearchPOINASN, "SearchPOINASN");
			set(tendercreationlocators.SearchPOINASN, ponum, "SearchPOINASN");

			waitForObj(3000);
			pdfResultReport.addStepDetails("SearchPo", "ASN list with PO number is to searched",
					"Successfully searched ASN list with PO number which is deleted" + " ", "Pass", "Y");
			log.info("completed executing the method:: SearchPo");
		} catch (Exception e) {
			log.fatal("Not able to search ASN list with PO number" + e.getMessage());
			pdfResultReport.addStepDetails("SearchPo", "ASN list with PO number is to searched",
					"Unable to search ASN list with PO number" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void SendForApproval() throws Throwable {
		try {
			log.info("started executing the method:: SendForApproval");

			JSClick(tendercreationlocators.btnSendforApproval, "btnSendforApproval");
			waitForObj(2000);
			JSClick(tendercreationlocators.BtnYes, "BtnYes");
			etendercomponentobj.waitForSpinnerToDisappear();
			pdfResultReport.addStepDetails("SendForApproval", "Send For Approval should be clicked successfully",
					"Successfully clicked on Send For Approval Button" + " ", "Pass", "Y");
			log.info("completed executing the method:: SendForApproval");
		} catch (Exception e) {
			log.fatal("Unable to click on Send For Approval Button" + e.getMessage());
			pdfResultReport.addStepDetails("SendForApproval", "Send For Approval should be clicked successfully",
					"Unable to click on Send For Approval Button" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}

	}

	public void SearchPoInASNList() throws Throwable {
		try {
			log.info("started executing the method:: SearchPo");
			etendercomponentobj.waitForSpinnerToDisappear();
			JSClick(tendercreationlocators.SearchPOINASN, "SearchPOINASN");
			waitForObj(3000);
			String ponum = eTenderComponent.getDataFromPropertiesFile("poDocNum");
			clear(tendercreationlocators.SearchPOINASN, "SearchPOINASN");
			set(tendercreationlocators.SearchPOINASN, ponum, "SearchPOINASN");
			String ASNNum = text(tendercreationlocators.ASNNum(ponum));
			eTenderComponent.updateDataIntoPropertyFile("ASNNum", ASNNum);
			waitForObj(3000);
			pdfResultReport.addStepDetails("SearchPo", "ASN list with PO number is to searched",
					"Successfully searched ASN list with PO number" + " ", "Pass", "Y");
			log.info("completed executing the method:: SearchPo");
		} catch (Exception e) {
			log.fatal("Not able to search ASN list with PO number" + e.getMessage());
			pdfResultReport.addStepDetails("SearchPo", "ASN list with PO number is to searched",
					"Unable to search ASN list with PO number" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void VerifyTheDeletedASN() throws Throwable {
		try {
			log.info("started executing the method:: VerifyTheDeletedASN");
			boolean b = ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.actionDropdownASN)
					.isDisplayed();
			if (b != true) {
				pdfResultReport.addStepDetails("VerifyTheDeletedASN", "Deleted ASN Should not be displayed",
						"Deleted ASN is not displayed" + " ", "Pass", "Y");

			} else {
				pdfResultReport.addStepDetails("VerifyTheDeletedASN", "Deleted ASN Should not be displayed",
						"Unable to verify the Deleted ASN" + " ", "Fail", "N");
			}
			log.info("completed executing the method:: VerifyTheDeletedASN");
		} catch (Exception e) {
			log.fatal("Unable to verify the Deleted ASN" + e.getMessage());
			pdfResultReport.addStepDetails("VerifyTheDeletedASN", "Deleted ASN Should not be displayed",
					"Unable to verify the Deleted ASN" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}

	}

	public void TabShipmentInformationWareHouseChange(String warehouse) throws Throwable {
		try {
			log.info("started executing the method:: TabShipmentInformationWareHouseChange");
			// Durgapur
			// Patna
			etendercomponentobj.waitForSpinnerToDisappear();
			JSClick(tendercreationlocators.TabMyInformation, "TabMyInformation");
			etendercomponentobj.waitForSpinnerToDisappear();
			waitForObj(5000);
			JSClick(tendercreationlocators.TabShipmentInformation, "TabShipmentInformation");
			etendercomponentobj.waitForSpinnerToDisappear();
			waitForObj(5000);
			select(tendercreationlocators.SelctWareHouse, warehouse);
			waitForObj(5000);
			pdfResultReport.addStepDetails("TabShipmentInformationWareHouseChange",
					"WareHouse name should be changed successfully", "Successfully changed WareHouse Name " + " ",
					"Pass", "Y");
			log.info("completed executing the method:: TabShipmentInformationWareHouseChange");
		} catch (Exception e) {
			log.fatal("Unable to change WareHouse Name" + e.getMessage());
			pdfResultReport.addStepDetails("TabShipmentInformationWareHouseChange",
					"WareHouse name should be changed successfully", "Unable to change WareHouse Name" + e.getMessage(),
					"Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}

	}

	public void VerifyBidderWareHouse() throws Throwable {
		try {
			log.info("started executing the method:: VerifyBidderWareHouse");
			waitForObj(3000);
			String ponum = eTenderComponent.getDataFromPropertiesFile("poDocNum");
			highlight(tendercreationlocators.BidderWarehouse(ponum));
			waitForObj(5000);
			String text = text(tendercreationlocators.BidderWarehouse(ponum));
			if (text.equals("Patna")) {
				pdfResultReport.addStepDetails("VerifyBidderWareHouse", "WareHouse should be change successfully",
						"Successfully verified the changed Warehouse" + " ", "Pass", "Y");
			} else {
				pdfResultReport.addStepDetails("VerifyBidderWareHouse", "WareHouse should be change successfully",
						"Unable to verify changed Warehouse " + " ", "Fail", "N");
			}
			log.info("completed executing the method:: VerifyBidderWareHouse");
		} catch (Exception e) {
			log.fatal("Unable to verify changed Warehouse" + e.getMessage());
			pdfResultReport.addStepDetails("VerifyBidderWareHouse", "WareHouse should be change successfully",
					"Unable to verify changed Warehouse " + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void VerifyApproverWareHouse() throws Throwable {
		try {
			log.info("started executing the method:: VerifyBidderWareHouse");
			waitForObj(3000);
			String ASNNum = eTenderComponent.getDataFromPropertiesFile("ASNNum");
			highlight(tendercreationlocators.ApproverWarehouse(ASNNum));
			waitForObj(5000);
			String text = text(tendercreationlocators.ApproverWarehouse(ASNNum));
			if (text.equals("Patna")) {
				pdfResultReport.addStepDetails("VerifyApproverWareHouse", "WareHouse should be change successfully",
						"Successfully verified the changed Warehouse" + " ", "Pass", "Y");
			} else {
				pdfResultReport.addStepDetails("VerifyApproverWareHouse", "WareHouse should be change successfully",
						"Unable to verify changed Warehouse " + " ", "Fail", "N");
			}
			log.info("completed executing the method:: VerifyApproverWareHouse");
		} catch (Exception e) {
			log.fatal("Unable to verify changed Warehouse" + e.getMessage());
			pdfResultReport.addStepDetails("VerifyApproverWareHouse", "WareHouse should be change successfully",
					"Unable to verify changed Warehouse " + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void SaveASNWithconfirmation() throws Throwable {
		try {
			log.info("started executing the method:: SaveASN");

			JSClick(tendercreationlocators.ASNSave, "ASNSave");
			waitForObj(3000);
			JSClick(tendercreationlocators.BtnYes, "BtnYes");
			etendercomponentobj.waitForSpinnerToDisappear();
			waitForObj(3000);

			pdfResultReport.addStepDetails("SaveASN", "ASN Module should be saved successfully",
					"Successfully saved ASN Module " + " ", "Pass", "Y");
			log.info("completed executing the method:: SaveASN");
		} catch (Exception e) {
			log.fatal("Unable to saved ASN Module" + e.getMessage());
			pdfResultReport.addStepDetails("SaveASN", "ASN Module should be saved successfully",
					"Unable to saved ASN Module" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}

	}

	public void VerifyPendingForInspectionStatus() throws Throwable {
		try {
			log.info("started executing the method:: VerifyPendingForInspectionStatus");

			String ponum = eTenderComponent.getDataFromPropertiesFile("poDocNum");

			waitForObj(3000);
			WebElement ele = ThreadLocalWebdriver.getDriver()
					.findElement(tendercreationlocators.StatusPendingforInspection(ponum));
			highlight(tendercreationlocators.StatusPendingforInspection(ponum));
			String colour = getattributevalue(ele, "class");
			if (colour.contains("grnBtn")) {
				pdfResultReport.addStepDetails("VerifyDraftStatus", "ASN Pending For Inspection status to be verified",
						"Successfully verified ASN Status as Pending For Inspection" + " ", "Pass", "Y");
			} else {
				pdfResultReport.addStepDetails("VerifyPendingForInspectionStatus",
						"ASN Pending For Inspection status to be verified",
						"Unable to verify ASN Status as Pending For Inspection", "Fail", "N");
			}
			log.info("completed executing the method:: VerifyPendingForInspectionStatus");
		} catch (Exception e) {
			log.fatal("Not able to search ASN list with PO number" + e.getMessage());
			Assert.fail("Failed Due to " + e.getMessage());

		}
	}


	public void acceptOrRejectValidationButton() throws Throwable {
		try {
			log.info("started executing the method:: acceptOrRejectValidationButton");
			clear(tendercreationlocators.search, "Clear the TenderRefNumber");
			set(tendercreationlocators.search, asnNo, "search The PO Doc Number");
			waitForObj(2000);
			click(tendercreationlocators.action, "action");
			waitForElementToBeVisible(By.xpath("//ul[@class='dropdown-menu extended logout big_actv']"));
			IsElementPresent(tendercreationlocators.approveOrReject(asnNo));
			highlight(tendercreationlocators.approveOrReject(asnNo));
			waitForObj(2000);
			pdfResultReport.addStepDetails("acceptOrRejectValidationButton",
					"Successfully validated accept or reject button",
					"Successfully validated accept or reject button" + " ", "Pass", "Y");
			log.info("completed executing the method:: acceptOrRejectValidationButton");
		} catch (Exception e) {
			log.fatal("Unable to validated accept or reject button" + e.getMessage());
			pdfResultReport.addStepDetails("acceptOrRejectValidationButton",
					"Successfully validated accept or reject button",
					"Unable to validated accept or reject button" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	
	public void editASN() throws Throwable {
		try {
			log.info("started executing the method:: editASN");
			clear(tendercreationlocators.search, "Clear the TenderRefNumber");
			set(tendercreationlocators.search, asnNo, "search The PO Doc Number");
			waitForObj(2000);
			click(tendercreationlocators.action, "action");
			waitForElementToBeVisible(By.xpath("//ul[@class='dropdown-menu extended logout big_actv']"));
			click(tendercreationlocators.actionDropDowns(asnNo, "Edit"), "edit");
			waitForElementToBeVisible(tendercreationlocators.ASNDate);
			// SendForApproval();
			waitForObj(2000);
			pdfResultReport.addStepDetails("editASN", "Successfully edited ASN", "Successfully edited ASN" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: editASN");
		} catch (Exception e) {
			log.fatal("Unable edit ASN" + e.getMessage());
			pdfResultReport.addStepDetails("editASN", "Successfully edited ASN ", "Unable to edit ASN" + e.getMessage(),
					"Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}



	public void verifyVariousStatus(String currentStatus) throws Throwable {
		try {
			log.info("started executing the method:: verifyPreparedStatus");
			clear(tendercreationlocators.search, "Clear the TenderRefNumber");
			set(tendercreationlocators.search, asnNo, "search The PO Doc Number");
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.ASNStatus(currentStatus));
			waitForObj(2000);
			pdfResultReport.addStepDetails("verifyPreparedStatus", "Successfully verified in prepared status",
					"Successfully verified in prepared status" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyPreparedStatus");
		} catch (Exception e) {
			log.fatal("Unable to verify the draft status" + e.getMessage());
			pdfResultReport.addStepDetails("verifyPreparedStatus", "Successfully verified in prepared status",
					"Unable to verify the prepared status" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void coloumnVisibilityIconValidation() throws Throwable {
		try {
			log.info("started executing the method:: coloumnVisibilityIconValidation");
			highlight(tendercreationlocators.columnVisibility);
			pdfResultReport.addStepDetails("coloumnVisibilityIconValidation",
					"Coloumn Visibility Icon must be display successfully",
					"Successfully displayed Coloumn Visibility Icon " + " ", "Pass", "Y");
			click(tendercreationlocators.columnVisibility, "columnVisibility");
			waitForElementToBeVisible(tendercreationlocators.poValueCV);
			click(tendercreationlocators.poValueCV, "poValueCV");
			pdfResultReport.addStepDetails("coloumnVisibilityIconValidation",
					"User should be able to modify the column names successfully",
					"Successfully user modified the column names" + " ", "Pass", "Y");
			click(tendercreationlocators.netASNValueCV, "netASNValueCV");
			pdfResultReport.addStepDetails("coloumnVisibilityIconValidation",
					"User should be able to modify the column names successfully",
					"Successfully user modified the column names" + " ", "Pass", "Y");
			click(tendercreationlocators.closeFilter, "closeFilter");
			log.info("completed executing the method:: coloumnVisibilityIconValidation");
		} catch (Exception e) {
			log.fatal("Unable to modified the column names" + e.getMessage());
			pdfResultReport.addStepDetails("coloumnVisibilityIconValidation",
					"User should be able to modify the column names successfully",
					"Unable to  modified the column names" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void VerifyViewAndCreateGRNIsDisplayedValidation(String AsnShipmentNo) throws Throwable {
		try {
			log.info("started executing the method:: VerifyViewAndCreateGRNIsDisplayedValidation");

			WebDriver driver = ThreadLocalWebdriver.getDriver();

			String createGrn = "//*[contains(text(),'{0}')]/../following-sibling::td//child::button[@data-toggle='dropdown']/..//ul/li/a[contains(text(),'Create GRN')]";

			boolean displayed = driver.findElement(By.xpath(createGrn.replace("{0}", AsnShipmentNo))).isDisplayed();

			String view = "//*[contains(text(),'{0}')]/../following-sibling::td//child::button[@data-toggle='dropdown']/..//ul/li/a[contains(text(),'View')]";

			boolean displayed1 = driver.findElement(By.xpath(view.replace("{0}", AsnShipmentNo))).isDisplayed();

			Assert.assertTrue(displayed, "Create GRN is displayed");
			Assert.assertTrue(displayed1, "View is displayed");
			pdfResultReport.addStepDetails("VerifyViewAndCreateGRNIsDisplayedValidation",
					"View and Create GRN must be display sucessfully",
					"Successfully displayed View and Create GRN" + " ", "Pass", "Y");
			log.info("completed executing the method:: VerifyViewAndCreateGRNIsDisplayedValidation");
		} catch (Exception e) {
			log.fatal("Unable to display View and Create GRN" + e.getMessage());
			pdfResultReport.addStepDetails("VerifyViewAndCreateGRNIsDisplayedValidation",
					"View and Create GRN must be display sucessfully",
					"Unable to display View and Create GRN" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void calculationOfDamageQtyValidation() throws Throwable {
		try {
			log.info("started executing the method:: calculationOfDamageQtyValidation");
			waitForElementToBeVisible(tendercreationlocators.acceptedQtyAlert);
			pdfResultReport.addStepDetails("calculationOfDamageQtyValidation",
					"Accepted Quantity can not be less than 0 popup must be display sucessfully",
					"Successfully displayed Accepted Quantity can not be less than 0 popup" + " ", "Pass", "Y");
			click(tendercreationlocators.alertTabOk, "alertTabOk");
			pdfResultReport.addStepDetails("calculationOfDamageQtyValidation",
					"System should not allow GRN creator to save or submit the GRN",
					"Successfully system not allowed to save or submit GRN" + " ", "Pass", "Y");
			log.info("completed executing the method:: calculationOfDamageQtyValidation");
		} catch (Exception e) {
			log.fatal("Able to save GRN" + e.getMessage());
			pdfResultReport.addStepDetails("calculationOfDamageQtyValidation",
					"System should not allow GRN creator to save or submit the GRN",
					"Able to save GRN" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());

		}
	}

	public void verifyinvalidQtyInGrnCreationPage(int ItemCoderow) throws Throwable {
		try {
			log.info("started executing the method:: verifyinvalidQtyInGrnCreationPage");
			WebDriver driver = ThreadLocalWebdriver.getDriver();
			boolean invalidQty = driver.findElement(tendercreationlocators.invalidQuantity(ItemCoderow)).isDisplayed();
			Assert.assertTrue(invalidQty, "Invalid Qty is displayed");
			pdfResultReport.addStepDetails("verifyinvalidQtyInGrnCreationPage",
					"Invalid quantity must be display in Accepted quantity sucessfully",
					"Sucessfully displayed Invalid quantity in Accepted quantity" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyinvalidQtyInGrnCreationPage");
		} catch (Exception e) {
			log.fatal("Unable to display Invalid quantity in Accepted quantity" + e.getMessage());
			pdfResultReport.addStepDetails("verifyinvalidQtyInGrnCreationPage",
					"Invalid quantity must be display in Accepted quantity sucessfully",
					"Unable to display Invalid quantity in Accepted quantity " + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void verifyAsnQtyValueInGrnCreationPage(int ItemCoderow) throws Throwable {
		try {
			log.info("started executing the method:: verifyAsnQtyValueInGrnCreationPage");

			waitForObj(3000);

			String AsnActualValue = text(tendercreationlocators.asnQtyFieldvalue(ItemCoderow));

			String AsnExpectedValue = eTenderComponent.getDataFromPropertiesFile("AsnQTy");

			Assert.assertTrue(AsnActualValue.trim().contains(AsnExpectedValue.trim()),
					"verified AsnQty Value In Grn CreationPage");

			pdfResultReport.addStepDetails("verifyAsnQtyValueInGrnCreationPage",
					"Asn Qty Data Value  should be  validated ", "Asn Qty Data Value  is validated Sucessfully" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: verifyAsnQtyValueInGrnCreationPage");
		} catch (Exception e) {
			log.fatal("Unable to validate Asn Qty Data Value" + e.getMessage());
			pdfResultReport.addStepDetails("verifyAsnQtyValueInGrnCreationPage",
					"Asn Qty Data Value  should be  validated",
					"Unable to validate Asn Qty Data Value  " + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void navigateToGrnListPage() throws Throwable {
		try {
			log.info("started executing the method:: navigateToGrnListPage");

			JSClick(tendercreationlocators.GRNMenuLinkBy, "GRNMenuLinkBy");

			waitForObj(2000);

			JSClick(tendercreationlocators.GrnListMenuLinkBy, "GrnListMenuLinkBy");

			waitForElementToBeVisible(tendercreationlocators.GrnListDraftEleBy);

			waitForElementToBeVisible(tendercreationlocators.GrnDraftRecBy);

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			waitForObj(5000);

			pdfResultReport.addStepDetails("navigateToGrnListPage", "Should navigate to Grn List page",
					"Successfully navigated to Grn List page " + " ", "Pass", "Y");
			log.info("completed executing the method:: navigateToGrnListPage");
		} catch (Exception e) {
			log.fatal("Unable to navigate to Grn List page" + e.getMessage());
			pdfResultReport.addStepDetails("navigateToGrnListPage", "Should navigate to Grn List page",
					"Unable to navigate to Grn List page " + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());

		}
	}

	public void clickOnGRNCreatedTab() throws Throwable {
		try {
			log.info("started executing the method:: clickOnGRNCreatedTab");

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			waitForObj(5000);

			click(tendercreationlocators.GrnCreatedTabBy, "GrnCreatedTabBy");

			waitForElementToBeVisible(tendercreationlocators.GrnCreatedTabRecBy);

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			waitForObj(3000);

			pdfResultReport.addStepDetails("clickOnGRNCreatedTab", "Should Display GRN Submitted Records",
					"Successfully Displaying GRN Submitted Records" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickViewShipmentDetails");
		} catch (Exception e) {
			log.fatal("Unable to  Display GRN Submitted Records" + e.getMessage());
			pdfResultReport.addStepDetails("clickOnGRNCreatedTab", "Should Display GRN Submitted Records",
					"Unable to  Display GRN Submitted Records" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void enterShippementTrackingNoInGrnListSerachBox() throws Throwable {
		try {
			log.info("started executing the method:: enterShippementTrackingNoInGrnListSerachBox");

			clear(tendercreationlocators.search, "clear the search field");

			set(tendercreationlocators.search, eTenderComponent.getDataFromPropertiesFile("ASNShippingTracking"),
					"ASNShippingTracking");

			waitForObj(5000);

			pdfResultReport.addStepDetails("enterShippementTrackingNoInGrnListSerachBox", "Should Display GRN  Records",
					"Successfully Displaying  GRN  Records" + " ", "Pass", "Y");
			log.info("completed executing the method:: enterShippementTrackingNoInGrnListSerachBox");
		} catch (Exception e) {
			log.fatal("Unable to  Display GRN  Records" + e.getMessage());
			pdfResultReport.addStepDetails("enterShippementTrackingNoInGrnListSerachBox", "Should Display GRN  Records",
					"Unable to  Display GRN  Records" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void validateActionMenuElementsInGrnListPage(String AsnShipmentNo) throws Throwable {
		try {
			log.info("started executing the method:: validateActionMenuElementsInGrnListPage");

			WebDriver driver = ThreadLocalWebdriver.getDriver();

			String viewGrn = "//*[contains(text(),'{0}')]/following-sibling::td//child::button[@data-toggle='dropdown']/following-sibling::ul/li/a[contains(text(),'View GRN')]";

			String viewshipment = "//*[contains(text(),'{0}')]/following-sibling::td//child::button[@data-toggle='dropdown']/following-sibling::ul/li/a[contains(text(),'View Shipment Details')]";

			String cancelGrn = "//*[contains(text(),'{0}')]/following-sibling::td//child::button[@data-toggle='dropdown']/following-sibling::ul/li/a[contains(text(),'Cancel GRN')]";

			Assert.assertTrue(driver.findElement(By.xpath(viewGrn.replace("{0}", AsnShipmentNo))).isDisplayed());

			Assert.assertTrue(driver.findElement(By.xpath(viewshipment.replace("{0}", AsnShipmentNo))).isDisplayed());

			Assert.assertTrue(driver.findElement(By.xpath(cancelGrn.replace("{0}", AsnShipmentNo))).isDisplayed());

			pdfResultReport.addStepDetails("validateActionMenuElementsInGrnListPage",
					"Should Contains view Grn ,cancel Grn , viewShipment in action Menu",
					"sucessfully  displaying  view Grn ,cancel Grn , viewShipment in action Menu" + " ", "Pass", "Y");
			log.info("completed executing the method:: validateActionMenuElementsInGrnListPage");
		} catch (Exception e) {
			log.fatal("Unable to Show view Grn ,cancel Grn , viewShipment in action Menu" + e.getMessage());
			pdfResultReport.addStepDetails("validateActionMenuElementsInGrnListPage",
					"Should Contains view Grn ,cancel Grn , viewShipment in action Menu",
					"Unable to Show view Grn ,cancel Grn , viewShipment in action Menu" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void verifyViewGrnSavedDetailsInPopUp(String ItemCode, String rejvalue, String excesvalue, String shortvalue,
			String damagevalue) throws Throwable {
		try {
			log.info("started executing the method:: verifyViewGrnSavedDetailsInPopUp");

			waitForObj(3000);

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

			Assert.assertTrue(shortQtyvalue.trim().contains(shortvalue), "Rejected Value is Correct");

			waitForObj(1000);

			scrollToElement(tendercreationlocators.DamageQtyvalue(ItemCode.trim()));

			highlight(tendercreationlocators.DamageQtyvalue(ItemCode.trim()));

			String damageQtyvalue = text(tendercreationlocators.DamageQtyvalue(ItemCode.trim()));

			Assert.assertTrue(damageQtyvalue.trim().contains(damagevalue), "Rejected Value is Correct");

			waitForObj(3000);

			pdfResultReport.addStepDetails("verifyViewGrnSavedDetailsInPopUp",
					"GRN details Saved Should be populated Correctly In Pop up",
					"Sucessfully checked Grn details In Pop Up" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyViewGrnSavedDetailsInPopUp");
		} catch (Exception e) {
			log.fatal("failed to check Grn details In Pop Up " + e.getMessage());
			pdfResultReport.addStepDetails("verifyViewGrnSavedDetailsInPopUp",
					"GRN details Saved Should be populated Correctly In Pop up",
					"failed to check Grn details In Pop Up " + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void clickCancelGrnInActonMenu(String AsnShipmentNo) throws Throwable {
		try {
			log.info("started executing the method:: clickCancelGrnInActonMenu");

			WebDriver driver = ThreadLocalWebdriver.getDriver();

			String cancelGrn = "//*[contains(text(),'{0}')]/following-sibling::td//child::button[@data-toggle='dropdown']/following-sibling::ul/li/a[contains(text(),'Cancel GRN')]";

			driver.findElement(By.xpath(cancelGrn.replace("{0}", AsnShipmentNo))).click();

			waitForElementToBeVisible(tendercreationlocators.GrnCancelAlertMsgBy);

			waitForObj(2000);

			click(tendercreationlocators.GrnCancelConfirmBtnBy, "GrnCancelConfirmBtnBy");

			pdfResultReport.addStepDetails("clickCancelGrnInActonMenu", "Should Cancel the GRN",
					"Successfully Cancelled the GRN" + " ", "Pass", "Y");

			waitForObj(5000);

			log.info("completed executing the method:: clickViewShipmentDetails");
		} catch (Exception e) {
			log.fatal("Unable to  Cancel the GRN" + e.getMessage());
			pdfResultReport.addStepDetails("clickCancelGrnInActonMenu", "Should Cancel the GRN",
					"Unable to  Cancel the GRN" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void clickGrnSubmitComfirmationYes() throws Throwable {
		try {
			log.info("started executing the method:: clickGrnSubmitComfirmationYes");

			waitForObj(3000);

			click(tendercreationlocators.GrnSubmitConfirmYesBy, "GrnSubmitBy");

			waitForElementToBeVisible(tendercreationlocators.GrnListDraftEleBy);

			waitForObj(3000);

			waitForElementToBeVisible(tendercreationlocators.GrnDraftRecBy);

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			waitForObj(5000);

			pdfResultReport.addStepDetails("clickGrnSubmitComfirmationYes", "Should Navigate to GRN List Page",
					"Successfully Should Navigated to GRN List Page" + " ", "Pass", "Y");
			log.info("completed executing the method:: submitGrnDetails");
		} catch (Exception e) {
			log.fatal("Unable to Should Navigate to GRN List Page" + e.getMessage());
			pdfResultReport.addStepDetails("submitGrnDetails", "Should Navigate to GRN List Page",
					"Unable to Should Navigate to GRN List Page" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());

		}
	}

	public void validateActionMenuElementsInGrnListPageCancelledTab(String AsnShipmentNo) throws Throwable {
		try {
			log.info("started executing the method:: validateActionMenuElementsInGrnListPageCancelledTab");

			WebDriver driver = ThreadLocalWebdriver.getDriver();

			String viewGrn = "//*[contains(text(),'{0}')]/following-sibling::td//child::button[@data-toggle='dropdown']/following-sibling::ul/li/a[contains(text(),'View GRN')]";

			String viewshipment = "//*[contains(text(),'{0}')]/following-sibling::td//child::button[@data-toggle='dropdown']/following-sibling::ul/li/a[contains(text(),'View Shipment Details')]";

			Assert.assertTrue(driver.findElement(By.xpath(viewGrn.replace("{0}", AsnShipmentNo))).isDisplayed());

			Assert.assertTrue(driver.findElement(By.xpath(viewshipment.replace("{0}", AsnShipmentNo))).isDisplayed());

			pdfResultReport.addStepDetails("validateActionMenuElementsInGrnListPageCancelledTab",
					"Should Contains view Grn  , viewShipment in action Menu",
					"sucessfully  displaying  view Grn , viewShipment in action Menu" + " ", "Pass", "Y");
			log.info("completed executing the method:: validateActionMenuElementsInGrnListPageCancelledTab");
		} catch (Exception e) {
			log.fatal("Unable to Show view Grn , viewShipment in action Menu" + e.getMessage());
			pdfResultReport.addStepDetails("validateActionMenuElementsInGrnListPageCancelledTab",
					"Should Contains view Grn , viewShipment in action Menu",
					"Unable to Show view Grn , viewShipment in action Menu" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void clickApprovedAsnListActionMenu(String AsnShipmentNo) throws Throwable {
		try {
			log.info("started executing the method:: clickApprovedAsnListActionMenu");

			WebDriver driver = ThreadLocalWebdriver.getDriver();

			String actionDropDown = "//*[contains(text(),'{0}')]/../following-sibling::td//child::button[@data-toggle='dropdown']";

			driver.findElement(By.xpath(actionDropDown.replace("{0}", AsnShipmentNo))).click();

			verifyGRNValidationInActionButton(eTenderComponent.getDataFromPropertiesFile("ASNShippingTracking"),
					Arrays.asList("Create GRN", "View"));

			waitForObj(3000);

			pdfResultReport.addStepDetails("clickApprovedAsnListActionMenu", "Should click ActionMenu",
					"Successfully  Should click ActionMenu" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickApprovedAsnListActionMenu");
		} catch (Exception e) {
			log.fatal("Unable to click ActionMenu" + e.getMessage());
			pdfResultReport.addStepDetails("clickApprovedAsnListActionMenu", "Should click ActionMenu",
					"Unable to click ActionMenu" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void verifyGRNValidationInActionButton(String shippingNo, List<String> list) throws Throwable {
		try {
			log.info("started executing the method:: verifyGRNValidationInActionButton");
			waitForElementToBeVisible(By
					.xpath("//td/b[text()='" + shippingNo + "']/../../td//button[@id='menu1']/following-sibling::ul"));
			waitForObj(2000);
			List<WebElement> actionList = ThreadLocalWebdriver.getDriver().findElements(By.xpath(
					"//td/b[text()='" + shippingNo + "']/../../td//button[@id='menu1']/following-sibling::ul/li/a"));
			ArrayList<String> ListText = new ArrayList<String>();
			for (WebElement actionListText : actionList) {

				ListText.add(actionListText.getText());
			}
			System.out.println(ListText);
			boolean b = ListText.containsAll(list);
			Assert.assertTrue(b);
			waitForObj(3000);
			IsElementPresent(tendercreationlocators.columnVisibility);
			pdfResultReport.addStepDetails("verifyPreparedStatus", "Successfully verified action dropdown validation",
					"Successfully verified action dropdown validation" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyGRNValidationInActionButton");
		} catch (Exception e) {
			log.fatal("Unable to verify the action dropdown validation" + e.getMessage());
			pdfResultReport.addStepDetails("verifyGRNValidationInActionButton",
					"Successfully verified action dropdown validation",
					"Unable to verify the action dropdown validation" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void submitGrnDetails1() throws Throwable {
		try {
			log.info("started executing the method:: submitGrnDetails");

			waitForObj(3000);

			click(tendercreationlocators.GrnSubmitBy, "GrnSubmitBy");

			waitForElementToBeVisible(tendercreationlocators.GrnSubmitConfirmPopUpMsgBy);
			IsElementPresent(tendercreationlocators.GrnSubmitConfirmPopUpMsgBy);

			waitForObj(3000);

			pdfResultReport.addStepDetails("submitGrnDetails", "Should Show Confirmation Pop Up Yes or No",
					"Successfully Showing Confirmation Pop Up Yes or No" + " ", "Pass", "Y");
			click(tendercreationlocators.yesOrNoButton("No"), "No Button");
			waitForElementToBeVisible(tendercreationlocators.EnterExcessQtyBy(1));
			enterExcessQty(1, "3.00");
			click(tendercreationlocators.GrnSubmitBy, "GrnSubmitBy");
			waitForElementToBeVisible(tendercreationlocators.GrnSubmitConfirmPopUpMsgBy);
			IsElementPresent(tendercreationlocators.GrnSubmitConfirmPopUpMsgBy);
			click(tendercreationlocators.yesOrNoButton("Yes"), "Yes Button");
			waitForElementToBeVisible(tendercreationlocators.draft);
			waitForObj(2000);
			log.info("completed executing the method:: submitGrnDetails");
		} catch (Exception e) {
			log.fatal("Unable to Show Confirmation Pop Up Yes or No" + e.getMessage());
			pdfResultReport.addStepDetails("submitGrnDetails", "Should Show Confirmation Pop Up Yes or No",
					"Unable to  Show Confirmation Pop Up Yes or No" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());

		}
	}

	public void shortQtyValidation(int ItemCodeRow, String QtyNo) throws Throwable {
		try {
			log.info("started executing the method:: shortQtyValidation");

			click(tendercreationlocators.EnterShortQtyBy(ItemCodeRow), "clear ShortQty");
			ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.EnterShortQtyBy(ItemCodeRow))
					.sendKeys(Keys.DELETE);
			waitForElementToBeVisible(tendercreationlocators.shortQtyPopUp);
			IsElementPresent(tendercreationlocators.popUpText);
			highlight(tendercreationlocators.popUpText);
			pdfResultReport.addStepDetails("shortQtyValidation", "Should validate Short Quantity",
					"Successfully validated Short  Quantity" + " ", "Pass", "Y");
			click(tendercreationlocators.confirmationPopUp_YES, "YesButton");
			waitForElementToBeVisible(tendercreationlocators.EnterShortQtyBy(ItemCodeRow));
			ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.EnterShortQtyBy(ItemCodeRow))
					.sendKeys(Keys.ARROW_LEFT);
			ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.EnterShortQtyBy(ItemCodeRow))
					.sendKeys(Keys.ARROW_LEFT);
			set(tendercreationlocators.EnterShortQtyBy(ItemCodeRow), QtyNo, "EnterShortQtyBy");

			waitForObj(2000);

			pdfResultReport.addStepDetails("shortQtyValidation", "Should validate Short Quantity",
					"Successfully validated Short  Quantity" + " ", "Pass", "Y");
			log.info("completed executing the method:: shortQtyValidation");
		} catch (Exception e) {
			log.fatal("Unable to validate Short  Qty" + e.getMessage());
			pdfResultReport.addStepDetails("shortQtyValidation", "Should validate Short  Quantity",
					"Unable to validate Short  Qty " + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	/*public void verifyAllDropDownsInActionButton(String shipmentNo) throws Throwable {
		try {
			log.info("started executing the method:: verifyAllDropDownsInActionButton");

			waitForObj(3000);
			click(tendercreationlocators.GrnCreatedTabBy, "GrnCreatedTabBy");
			clear(tendercreationlocators.search, "Clear the TenderRefNumber");
			enterShippementTrackingNoInGrnListSerachBox();

			waitForElementToBeVisible(tendercreationlocators.action);
			click(tendercreationlocators.action, "action");
			waitForObj(2000);
			pdfResultReport.addStepDetails("verifyAllDropDownsInActionButton",
					"Successfully verified in prepared status", "Successfully verified in prepared status" + " ",
					"Pass", "Y");
			// verifyAllActionDropDownInGRNCreatedTab(Arrays.asList("Cancel
			// GRN","View GRN","View Shipment
			// Details"),eTenderComponent.getDataFromPropertiesFile("ASNShippingTracking")
			// );
			IsElementPresent(tendercreationlocators.viewGRN);
			IsElementPresent(tendercreationlocators.viewShipmentDetails);
			IsElementPresent(tendercreationlocators.cancelGRN);
			click(tendercreationlocators.viewGRN, "viewGRN");
			waitForElementToBeVisible(tendercreationlocators.previewGRN);
			waitForObj(2000);
			pdfResultReport.addStepDetails("verifyAllDropDownsInActionButton",
					"Successfully verified in prepared status", "Successfully verified in prepared status" + " ",
					"Pass", "Y");
			click(tendercreationlocators.previewAll_close, "Close Btn");
			waitForElementToBeVisible(tendercreationlocators.action);
			click(tendercreationlocators.action, "action");
			waitForElementToBeVisible(tendercreationlocators.viewShipmentDetails);
			click(tendercreationlocators.viewShipmentDetails, "viewShipmentDetails");
			waitForElementToBeVisible(tendercreationlocators.previewASN);
			waitForObj(2000);
			pdfResultReport.addStepDetails("verifyAllDropDownsInActionButton",
					"Successfully verified in prepared status", "Successfully verified in prepared status" + " ",
					"Pass", "Y");
			click(tendercreationlocators.previewAll_close1, "Close Btn");
			waitForElementToBeVisible(tendercreationlocators.action);
			click(tendercreationlocators.action, "action");
			waitForElementToBeVisible(tendercreationlocators.cancelGRN);
			click(tendercreationlocators.cancelGRN, "cancelGRN");
			waitForElementToBeVisible(tendercreationlocators.corrigendumAlertTabOk);
			click(tendercreationlocators.corrigendumAlertTabOk, "confirm Btn");
			waitForElementToBeVisible(tendercreationlocators.GrnCreatedTabBy);
			click(tendercreationlocators.GrnCancelTabBy, "Cancel Tab");

			pdfResultReport.addStepDetails("verifyAllDropDownsInActionButton",
					"Successfully verified in prepared status", "Successfully verified in prepared status" + " ",
					"Pass", "Y");
			// click(tendercreationlocators.previewAll_close,"Close Btn");
			clear(tendercreationlocators.search, "Clear the TenderRefNumber");
			enterShippementTrackingNoInGrnListSerachBox();
			waitForObj(3000);

			pdfResultReport.addStepDetails("verifyAllDropDownsInActionButton",
					"Successfully verified in prepared status", "Successfully verified in prepared status" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: verifyAllDropDownsInActionButton");
		} catch (Exception e) {
			log.fatal("Unable to verify the draft status" + e.getMessage());
			pdfResultReport.addStepDetails("verifyAllDropDownsInActionButton",
					"Successfully verified in prepared status", "Unable to verify the prepared status" + e.getMessage(),
					"Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}*/
	public void verifyAllDropDownsInActionButton(String shipmentNo) throws Throwable {
		try {
			log.info("started executing the method:: verifyAllDropDownsInActionButton");

			waitForObj(3000);
			click(tendercreationlocators.GrnCreatedTabBy, "GrnCreatedTabBy");
			clear(tendercreationlocators.search, "Clear the TenderRefNumber");
			enterShippementTrackingNoInGrnListSerachBox();

			waitForElementToBeVisible(tendercreationlocators.action);
			click(tendercreationlocators.action, "action");
			waitForObj(2000);
			pdfResultReport.addStepDetails("verifyAllDropDownsInActionButton",
					"Successfully verified in prepared status", "Successfully verified in prepared status" + " ",
					"Pass", "Y");
			// verifyAllActionDropDownInGRNCreatedTab(Arrays.asList("Cancel
			// GRN","View GRN","View Shipment
			// Details"),eTenderComponent.getDataFromPropertiesFile("ASNShippingTracking")
			// );
			IsElementPresent(tendercreationlocators.viewGRN);
			IsElementPresent(tendercreationlocators.viewShipmentDetails);
			IsElementPresent(tendercreationlocators.cancelGRN);
			click(tendercreationlocators.viewGRN, "viewGRN");
			waitForElementToBeVisible(tendercreationlocators.previewGRN);
			waitForObj(2000);
			pdfResultReport.addStepDetails("verifyAllDropDownsInActionButton",
					"Successfully verified in prepared status", "Successfully verified in prepared status" + " ",
					"Pass", "Y");
			click(tendercreationlocators.previewAll_close, "Close Btn");
			waitForElementToBeVisible(tendercreationlocators.action);
			click(tendercreationlocators.action, "action");
			
			waitForElementToBeVisible(tendercreationlocators.viewShipmentDetails);
			click(tendercreationlocators.viewShipmentDetails, "viewShipmentDetails");
			By shipmentxpath = By.xpath("//table[@id]//b[contains(text(),'"+shipmentNo+"')]");
			WebElement shipmentNumber = ThreadLocalWebdriver.getDriver().
					findElement(shipmentxpath);
			etendercomponentobj.waitForSpinnerToDisappear();
			waitForElementToBeVisible(shipmentxpath);
			highlight(shipmentNumber);
			waitForObj(3000);
			click(tendercreationlocators.previewAll_close1, "Close Btn");
			waitForElementToBeVisible(tendercreationlocators.action);
			click(tendercreationlocators.action, "action");
			waitForElementToBeVisible(tendercreationlocators.cancelGRN);
			waitForObj(3000);
			click(tendercreationlocators.cancelGRN, "cancelGRN");
			waitForElementToBeVisible(tendercreationlocators.corrigendumAlertTabOk);
			click(tendercreationlocators.corrigendumAlertTabOk, "confirm Btn");
			waitForElementToBeVisible(tendercreationlocators.GrnCreatedTabBy);
			waitForObj(3000);
			click(tendercreationlocators.GrnCancelTabBy, "Cancel Tab");

			pdfResultReport.addStepDetails("verifyAllDropDownsInActionButton",
					"Successfully verified in prepared status", "Successfully verified in prepared status" + " ",
					"Pass", "Y");
			// click(tendercreationlocators.previewAll_close,"Close Btn");
			clear(tendercreationlocators.search, "Clear the TenderRefNumber");
			enterShippementTrackingNoInGrnListSerachBox();
			waitForObj(3000);

			pdfResultReport.addStepDetails("verifyAllDropDownsInActionButton",
					"Successfully verified in prepared status", "Successfully verified in prepared status" + " ",
					"Pass", "Y");
			log.info("completed executing the method:: verifyAllDropDownsInActionButton");
		} catch (Exception e) {
			log.fatal("Unable to verify the draft status" + e.getMessage());
			pdfResultReport.addStepDetails("verifyAllDropDownsInActionButton",
					"Successfully verified in prepared status", "Unable to verify the prepared status" + e.getMessage(),
					"Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void ViewGRN(String ShipTrackno) throws Throwable {
		try {
			log.info("started executing the method:: ViewGRN");
			JSClick(tendercreationlocators.ViewlGRN(ShipTrackno), "ViewlGRN");
			etendercomponentobj.waitForSpinnerToDisappear();
			waitForObj(5000);

			pdfResultReport.addStepDetails("ViewGRN", "View GRN Page should be navigated successfully",
					"Successfully navigated to View GRN Details Page" + " ", "Pass", "Y");
			log.info("completed executing the method:: ViewGRN");
		} catch (Exception e) {
			log.fatal("Unable to navigate to View GRN Details Page" + e.getMessage());
			pdfResultReport.addStepDetails("ViewGRN", "View GRN Page should be navigated successfully",
					"Unable to navigate to View GRN Details Page" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void VerifyMaterialsDetails() throws Throwable {
		try {
			log.info("started executing the method:: VerifyMaterialsDetails");
			etendercomponentobj.waitForSpinnerToDisappear();
			waitForObj(5000);
			String ItemCodeUI = text(tendercreationlocators.ItemCodeUi);
			if (ItemCodeUI.equals("Item Code_1")) {
				highlight(tendercreationlocators.ItemCodeUi);
				String ItemDescriptionUI = text(tendercreationlocators.ItemDescriptionUi);
				if (ItemDescriptionUI.equals("ItemDescription_1")) {
					highlight(tendercreationlocators.ItemDescriptionUi);
					String UOMUI = text(tendercreationlocators.UOMUi);
					if (UOMUI.equals("CAR")) {

						highlight(tendercreationlocators.ItemDescriptionUi);
						String POQuantityUiUI = text(tendercreationlocators.POQuantityUi);
						if (POQuantityUiUI.equals("PO Quantity: 10.00")) {
							highlight(tendercreationlocators.POQuantityUi);
							String ASNQuantityUiUI = text(tendercreationlocators.ASNQuantityUi);
							if (ASNQuantityUiUI.equals("ASN Quantity: 15.00")) {
								highlight(tendercreationlocators.ASNQuantityUi);
								String RejectedQuantityUI = text(tendercreationlocators.RejectedQuantityUi);
								if (RejectedQuantityUI.equals("Rejected Quantity : 1.00")) {
									highlight(tendercreationlocators.RejectedQuantityUi);
									String ExcessQuantityUI = text(tendercreationlocators.ExcessQuantityUi);
									if (ExcessQuantityUI.equals("Excess Quantity: 5.00")) {
										highlight(tendercreationlocators.ExcessQuantityUi);
										String DamageQuantityUI = text(tendercreationlocators.DamageQuantity);
										if (DamageQuantityUI.equals("Damage Quantity: 1.00")) {
											highlight(tendercreationlocators.DamageQuantity);
											String AcceptedQuantityUI = text(tendercreationlocators.AcceptedQuantityUi);
											if (AcceptedQuantityUI.equals("18.00")) {
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
	public void saveGRNId() throws Throwable {
		try {
			log.info("started executing the method:: saveGRNId");

			waitForObj(3000);

			String grnId = text(tendercreationlocators.GrnIDBy).trim();

			String grnidUpdated = eTenderComponent.updateDataIntoPropertyFile("GRNID", grnId);

			System.out.println("GRN ID was updated in property file GRNID: --->" + grnidUpdated);

			waitForObj(3000);

			pdfResultReport.addStepDetails("saveGRNId", "Should save the GRN ID in property file",
					"Successfully  saved GRN Id in property file" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickGrnListActionMenu");
		} catch (Exception e) {
			log.fatal("Unable to save GRN Id" + e.getMessage());
			pdfResultReport.addStepDetails("clickGrnListActionMenu", "Should save the GRN ID in property file",
					"Unable to save GRN Id" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public void verifyGrnCreatedStatus(String grnId) throws Throwable {
		try {
			log.info("started executing the method:: verifyGrnCreatedStatus");

			waitForObj(3000);

			String GrnStatus = "//div[@class='row' and @ng-show='CURRENT_USER_ROLE == GRN_CREATOR_CONSTANT']//*[contains(text(),'{0}')]/following-sibling::td//*//*[@class='btn btn-primary btn-circle btn-lg btn-lg-sub2 grnBtn']";

			WebDriver driver = ThreadLocalWebdriver.getDriver();

			boolean displayed = driver.findElement(By.xpath(GrnStatus.replace("{0}", grnId))).isDisplayed();

			pdfResultReport.addStepDetails("verifyGrnCreatedStatus", "Should show GRN created Status",
					"Successfully showing GRN created Status" + " ", "Pass", "Y");

			Assert.assertTrue(displayed, "GRN Staus is Created is Displaying");

			log.info("completed executing the method:: clickGrnListActionMenu");
		} catch (Exception e) {
			log.fatal("Unable to show GRN created Status" + e.getMessage());
			pdfResultReport.addStepDetails("clickGrnListActionMenu", "Should show GRN created Status",
					"Unable to  show GRN created Status" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

	public EmailUtils emailyUtils = new EmailUtils();

	public void verifyMailSubjectAndMailBody(String subject, int max, String subContent1, String subContent2,
			String GRNId, String ShippingTrackingNo) throws Exception {
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

					if (message.getSubject().contains(GRNId)) {
						System.out.println("The Email Subject is :---->   " + message.getSubject());

						eTenderComponent.updateDataIntoPropertyFile("ExpectedMailSubject", message.getSubject());

						System.out.println("Updated latest Triggerd mail Subject in property File");

						System.out.println("--------------------------------------------------------------------");

						pdfResultReport.addStepDetails("verifyMailSubjectAndMailBody",
								"Mail Subject Must Be Validated Sucessfully",
								"Successfully Validated Mail Subject as Expected ---->" + message.getSubject() + " "
										+ " ",
								"Pass", "Y");

						if (emailyUtils.getSpecificMailBodyContent(message, subContent1, subContent2).contains(GRNId)
								&& emailyUtils.getSpecificMailBodyContent(message, subContent1, subContent2)
										.contains(ShippingTrackingNo)) {

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
									+ "\n expected Body content should contains the Grn id as  :" + GRNId);
							System.out.println(
									"----------------------------------------------------------------------------");
							System.out.println("check the next mail with Expected Body Content");
							System.out.println(
									"----------------------------------------------------------------------------");
						}
					} else {
						System.out.println("Email is not matching with excpted Subject \n" + "Actual Subject is :"
								+ message.getSubject()
								+ "\n expected Subjected Content should contains the Grn id as  : " + GRNId + "  ");
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
						+ GRNId + "  ");

				pdfResultReport.addStepDetails("verifyMailSubjectAndMailBody",
						"Mail Was Not Triggred waited for one minute", "Mail Was Not Triggred waited for one minute-->",

						"Fail", "N");

				throw new AssertionError(
						"Error Message : " + "The Mail was not triggered Unable to validate mail with given Subject"
								+ subject + GRNId + "  ");
			}

			log.info("completed executing the method:: verifyMailSubjectAndMailBody");

		} catch (Exception e) {

			e.printStackTrace();

			Assert.fail(e.getMessage());

		}
	}
	public void ViewShipmentDetailsPage(String ShipTrackno) throws Throwable {
		try {
			
			JSClick(tendercreationlocators.ViewShipmentDetailsPage(ShipTrackno), "ViewShipmentDetailsPage");
			etendercomponentobj.waitForSpinnerToDisappear();
			waitForObj(5000);

			pdfResultReport.addStepDetails("ViewShipmentDetailsPage",
					"View Shipment Details Page should be navigated successfully",
					"Successfully navigated to View Shipment Details Page" + " ", "Pass", "Y");
			log.info("completed executing the method:: ViewShipmentDetailsPage");
		} catch (Exception e) {
			log.fatal("Unable to navigate to View Shipment Details Page" + e.getMessage());
			pdfResultReport.addStepDetails("ViewShipmentDetailsPage",
					"View Shipment Details Page should be navigated successfully",
					"Unable to navigate to View Shipment Details Page" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}
	public void clickViewGrnDetailsOFCancelledInActionMenu(String AsnShipmentNo) throws Throwable {
		try {
			log.info("started executing the method:: clickViewGrnDetails");
			
			JSClick(tendercreationlocators.CancelGRN(AsnShipmentNo), "CancelGRN");
			waitForObj(5000);
			JSClick(tendercreationlocators.confirm, "CancelGRN");
			etendercomponentobj.waitForSpinnerToDisappear();
			waitForObj(8000);
			JSClick(tendercreationlocators.TabCancel, "TabCancel");
			etendercomponentobj.waitForSpinnerToDisappear();
			waitForObj(5000);
			clickGrnListActionMenu( AsnShipmentNo);
			WebDriver driver = ThreadLocalWebdriver.getDriver();

			String viewGrn = "//*[contains(text(),'{0}')]/following-sibling::td//child::button[@data-toggle='dropdown']/following-sibling::ul/li/a[contains(text(),'View GRN')]";

			driver.findElement(By.xpath(viewGrn.replace("{0}", AsnShipmentNo))).click();

			waitForElementToBeVisible(tendercreationlocators.grnIDBy);

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			waitForObj(3000);

			pdfResultReport.addStepDetails("clickViewGrnDetails", "Should Display Popup With GRN Preview Details",
					"Successfully Displaying popup With GRN Preview Details" + " ", "Pass", "Y");
			log.info("completed executing the method:: clickViewGrnDetails");
		} catch (Exception e) {
			log.fatal("Unable to  Display popup With GRN Preview Details" + e.getMessage());
			pdfResultReport.addStepDetails("clickViewGrnDetails", "Should Display popup With GRN Preview Details",
					"Unable to  Display popup With GRN Preview Details" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}
	public void SelectTabCreated() throws Throwable {
		try {
			log.info("started executing the method:: SelectTabCreated");

			waitForObj(5000);
			JSClick(tendercreationlocators.TabCreated, "TabCreated");
			etendercomponentobj.waitForSpinnerToDisappear();
			waitForObj(5000);

			pdfResultReport.addStepDetails("SelectTabCreated", "Should Display Created GRN Records",
					"Successfully Displaying Created GRN  Records" + " ", "Pass", "Y");
			log.info("completed executing the method:: SelectTabCreated");
		} catch (Exception e) {
			log.fatal("Unable to  Display GRN  Records" + e.getMessage());
			pdfResultReport.addStepDetails("SelectTabCreated", "Should Display Created GRN Records",
					"Unable to  Display GRN  Records" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}
	public void enterShippementTrackingNoInGrnListSerachBoxAndVerifyCreatedGRN() throws Throwable {
		try {
			log.info("started executing the method:: enterShippementTrackingNoInGrnListSerachBox");

			clear(tendercreationlocators.search, "clear the search field");

			set(tendercreationlocators.search, eTenderComponent.getDataFromPropertiesFile("ASNShippingTracking"),
					"ASNShippingTracking");

			waitForObj(2000);
			
			IsElementPresent(tendercreationlocators.grnActionBtn(eTenderComponent.getDataFromPropertiesFile("ASNShippingTracking")));
			
			highlight(tendercreationlocators.grnActionBtn(eTenderComponent.getDataFromPropertiesFile("ASNShippingTracking")));
			
			waitForObj(3000);

			pdfResultReport.addStepDetails("enterShippementTrackingNoInGrnListSerachBox", "Should Display GRN  Records",
					"Successfully Displaying  GRN  Records" + " ", "Pass", "Y");
			log.info("completed executing the method:: enterShippementTrackingNoInGrnListSerachBox");
		} catch (Exception e) {
			log.fatal("Unable to  Display GRN  Records" + e.getMessage());
			pdfResultReport.addStepDetails("enterShippementTrackingNoInGrnListSerachBox", "Should Display GRN  Records",
					"Unable to  Display GRN  Records" + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());
		}
	}
	public void enterDamageQty1(int ItemCodeRow, String QtyNo) throws Throwable {
		try {
			log.info("started executing the method:: enterDamageQty");
			
			click(tendercreationlocators.EnterDamageQtyBy(ItemCodeRow), " clear DamageQty");
			ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.EnterDamageQtyBy(ItemCodeRow))
					.sendKeys(Keys.DELETE);
			ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.EnterDamageQtyBy(ItemCodeRow))
					.sendKeys(Keys.ARROW_LEFT);
			ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.EnterDamageQtyBy(ItemCodeRow))
					.sendKeys(Keys.ARROW_LEFT);
			set(tendercreationlocators.EnterDamageQtyBy(ItemCodeRow), QtyNo, "EnterDamageQtyBy");
			String itemCode_3AcceptedQuantity="";
			String itemCode_3AcceptedQuantityText="";

			String itemCode_2AcceptedQuantity="";
			String itemCode_2AcceptedQuantityText="";

			String itemCode_1AcceptedQuantity="";
			String itemCode_1AcceptedQuantityText="";
			itemCode_3AcceptedQuantity = "//span[text()='Item Code_3']/../../../following-sibling::div//h2/b";
			itemCode_3AcceptedQuantityText = ThreadLocalWebdriver.getDriver().
					findElement(By.xpath(itemCode_3AcceptedQuantity)).getText().trim();
			System.out.println(itemCode_3AcceptedQuantityText);
			eTenderComponent.updateDataIntoPropertyFile("itemCode_3AcceptedQuantityValue", itemCode_3AcceptedQuantityText);
			//eTenderComponent.updateDataIntoPropertyFile(key, value)
			
			itemCode_2AcceptedQuantity = "//span[text()='Item Code_2']/../../../following-sibling::div//h2/b";
			itemCode_2AcceptedQuantityText = ThreadLocalWebdriver.getDriver().
					findElement(By.xpath(itemCode_2AcceptedQuantity)).getText().trim();
			System.out.println(itemCode_2AcceptedQuantityText);
			eTenderComponent.updateDataIntoPropertyFile("itemCode_2AcceptedQuantityValue", itemCode_2AcceptedQuantityText);
			
			itemCode_1AcceptedQuantity = "//span[text()='Item Code_1']/../../../following-sibling::div//h2/b";
			itemCode_1AcceptedQuantityText = ThreadLocalWebdriver.getDriver().
					findElement(By.xpath(itemCode_1AcceptedQuantity)).getText().trim();
			System.out.println(itemCode_1AcceptedQuantityText);
			eTenderComponent.updateDataIntoPropertyFile("itemCode_1AcceptedQuantityValue", itemCode_1AcceptedQuantityText);

			waitForObj(2000);

			pdfResultReport.addStepDetails("enterDamageQty", "Should enter Damage Quantity",
					"Successfully enter Damage Quantity" + " ", "Pass", "Y");
			log.info("completed executing the method:: enterDamageQty");
		} catch (Exception e) {
			log.fatal("Unable to enter Excess  Qty" + e.getMessage());
			pdfResultReport.addStepDetails("enterDamageQty", "Should enter Damage  Quantity",
					"Unable to enter Damage  Qty " + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}
	public void TabMyInformation() throws Throwable {
		try {
			log.info("started executing the method:: TabMyInformation");

			click(tendercreationlocators.TabMyInformation, "TabMyInformation");
			
			waitForObj(3000);
			
			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
			
			clear(tendercreationlocators.SupplierOrgName, "SupplierOrgName");
			
			set(tendercreationlocators.SupplierOrgName, "TCS", "SupplierOrgName");

			clear(tendercreationlocators.purchaserAccount, "purchaserAccount");
			
			set(tendercreationlocators.purchaserAccount, "128751", "purchaserAccount");

			clear(tendercreationlocators.SupplierState, "SupplierState");
			
			set(tendercreationlocators.SupplierState, "West Bengal", "SupplierState");

			clear(tendercreationlocators.SupplierZip, "SupplierZip");
			
			set(tendercreationlocators.SupplierZip, "712246", "SupplierZip");

			clear(tendercreationlocators.SupplierContact, "SupplierContact");
			
			set(tendercreationlocators.SupplierContact, "9051445500", "SupplierContact");
			
			waitForObj(2000);

			pdfResultReport.addStepDetails("TabMyInformation",
					"All fileds of MY Information tab should be entered successfully",
					"Successfully saved My Infromation Tab Details " + " ", "Pass", "Y");
			log.info("completed executing the method:: TabMyInformation");
		} catch (Exception e) {
			log.fatal("Unable to save My Information Tab" + e.getMessage());
			pdfResultReport.addStepDetails("TabMyInformation",
					"All fileds of MY Information tab should be entered successfully",
					"Unable to save My Information Tab" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}


public void verifyMyInformationTabFieldsAutoPopulated() throws Throwable {
		try {
			log.info("started executing the method:: verifyMyInformationTabFieldsAutoPopulated");

			String non_Empty= "ng-not-empty";
			
			waitForObj(3000);

			WebDriver driver = ThreadLocalWebdriver.getDriver();

			click(tendercreationlocators.TabMyInformation, "TabMyInformation");

			waitForObj(3000);

			waitTillSpinnerDisable(driver, tendercreationlocators.LoadingBy);
			
			WebElement supplierOrg = driver.findElement(tendercreationlocators.SupplierOrgName);

			if (supplierOrg.getAttribute("class").trim().contains(non_Empty.trim())) {
				
				highlight(supplierOrg);
				
				waitForObj(2000);
				
				System.out.println("Supplier Org   ---->" + supplierOrg.getAttribute("title"));
			} else {
				System.out.println("The Supplier Org Field is Not Auto Populated");

				throw new AssertionError("The Supplier Org Field is Not Auto Populated");
			}

			WebElement supplierCountry = driver.findElement(By.xpath("//*[@placeholder='Supplier Country']"));

			if (supplierCountry.getAttribute("class").trim().contains(non_Empty.trim())) {
				
				highlight(supplierCountry);
				
				waitForObj(2000);
				
				System.out.println("Supplier country   is auto  populated ---->" + supplierCountry.getAttribute("title"));
			} else {
				System.out.println("The Supplier country Field is Not Auto Populated");

				throw new AssertionError("The Supplier country Field is Not Auto Populated");
			}

			WebElement supplierAddress = driver.findElement(By.xpath("//*[@placeholder='Supplier Address']"));

			if (supplierAddress.getAttribute("class").trim().contains(non_Empty.trim())) {
				
				highlight(supplierAddress);
				
				waitForObj(2000);
				
				System.out.println("Supplier address  is auto  populated  ---->" + supplierAddress.getAttribute("title"));
			} else {
				System.out.println("The Supplier adderss Field is Not Auto Populated");

				throw new AssertionError("The Supplier address Field is Not Auto Populated");
			}

			WebElement supplierMail = driver.findElement(By.xpath("//*[@placeholder='Supplier Email']"));

			if (supplierMail.getAttribute("class").trim().contains(non_Empty.trim())) {
				
				highlight(supplierMail);
				
				waitForObj(2000);
				
				System.out.println("Supplier mail  is auto  populated  ---->" + supplierMail.getAttribute("title"));
			} else {
				System.out.println("The Supplier Mail Field is Not Auto Populated");

				throw new AssertionError("The Supplier Mail Field is Not Auto Populated");
			}
			
			WebElement supplierState = driver.findElement(By.xpath("//*[@placeholder='Supplier State']"));

			if (supplierState.getAttribute("class").trim().contains(non_Empty.trim())) {
				
				highlight(supplierState);
				
				waitForObj(2000);
				
				System.out.println("Supplier state  is auto  populated  ---->" + supplierState.getAttribute("title"));
			} else {
				System.out.println("The Supplier state Field is Not Auto Populated");

				throw new AssertionError("The Supplier State Field is Not Auto Populated");
			}
			
			WebElement supplierZip= driver.findElement(By.xpath("//*[@placeholder='Supplier zip']"));

			if (supplierZip.getAttribute("class").trim().contains(non_Empty.trim())) {
				
				highlight(supplierZip);
				
				waitForObj(2000);
				
				System.out.println("Supplier zip  is auto  populated  ---->" + supplierZip.getAttribute("title"));
			} else {
				System.out.println("The Supplier zip Field is Not Auto Populated");

				throw new AssertionError("The Supplier zip Field is Not Auto Populated");
			}

			WebElement supplierContact = driver.findElement(By.xpath("//*[@placeholder='Supplier Contact']"));

			if (supplierContact.getAttribute("class").trim().contains(non_Empty.trim())){
				
				highlight(supplierContact);
				
				waitForObj(2000);
				
				System.out.println("Supplier Contact  is auto  populated  ---->" + supplierContact.getAttribute("title"));
			} else {
				System.out.println("The Supplier Contact Field is Not Auto Populated");

				throw new AssertionError("The Supplier Contact Field is Not Auto Populated");
			}

			waitForObj(5000);
			
			pdfResultReport.addStepDetails("verifyMyInformationTabFieldsAutoPopulated",
					"My Information Tab Fields Should be Auto Populated",
					"Successfully validated the My information tab Fields auto populated " + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyMyInformationTabFieldsAutoPopulated");
		} catch (Exception e) {
			log.fatal("Failed To validate the My information tab Fields are auto populated" + e.getMessage());
			pdfResultReport.addStepDetails("TabMyInformation",
					"My Information Tab Fields Should be Auto Populated",
					"Failed To validate the My information tab Fields are auto populated" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}
	

String asnNo = "";

	public String ASNNumber() throws Throwable {
		waitForObj(2000);
		WebElement ele = ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.ASNNo);
		asnNo = getattributevalue(ele, "title").trim();
		System.out.println(asnNo);
		
		eTenderComponent.updateDataIntoPropertyFile("ASNNum", asnNo);
		
		return asnNo;
	}


public void navigateToCreateAsnPage() throws Throwable {
		try {
			log.info("started executing the method:: navigateToCreateAsnPage");

			waitForElementToBeVisible(tendercreationlocators.LiveTenderRecordsBy);

			waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);

			waitForObj(3000);
			
			JSClick(tendercreationlocators.ASN, "SN_stage");
			
			waitForObj(2000);

			JSClick(tendercreationlocators.CreateASN, "CreateASN");
			
            waitForElementToBeVisible(tendercreationlocators.SelectPONum);
			
            waitTillSpinnerDisable(ThreadLocalWebdriver.getDriver(), tendercreationlocators.LoadingBy);
            
            waitForObj(4000);
            
			pdfResultReport.addStepDetails("navigateToCreateAsnPage", "Should Navigate to Asn Creation Page",
					"Successfully Navigated to Asn Creation Page " + " ", "Pass", "Y");
			log.info("completed executing the method:: navigateToCreateAsnPage");
		} catch (Exception e) {
			log.fatal("Unable to Navigate to Asn Creation Page" + e.getMessage());
			pdfResultReport.addStepDetails("navigateToCreateAsnPage", "Should Navigate to Asn Creation Page",
					"Unable to Navigate to Asn Creation Page" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}

	}
	
	private List<String> PoNumbersFromdropdown=null;
	
	public void getAllPoNumbersFromDropDown() throws Throwable {
		try {
			log.info("started executing the method:: getAllPoNumbersFromDropDown");

			highlight(tendercreationlocators.SelectPONum);
			
			click(tendercreationlocators.SelectPONum,"SelectPONum");
			
			WebElement poDropDownBy = ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.SelectPONum);
			
			Select dropdownfield = new Select(poDropDownBy);
			
			List<WebElement> dropdown_values = dropdownfield.getOptions();
			
		   PoNumbersFromdropdown = dropdown_values.stream().map(povalues-> povalues.getText().trim()).collect(Collectors.toList());
			
			System.out.println("***************************************************************************************************");
			
			System.out.println("All Po Numbers From Drop Down List----------------------------------------->"+PoNumbersFromdropdown);
			
			System.out.println("***************************************************************************************************");
			
			
			waitForObj(5000);
			
			pdfResultReport.addStepDetails("getAllPoNumbersFromDropDown", "Should get all po numbers From dropdown list",
					"Successfully got all po numbers From dropdown list" +PoNumbersFromdropdown+" \n \n ---->And My Po number To Check Is This "+eTenderComponent.getDataFromPropertiesFile("poDocNum"), "Pass", "Y");
			log.info("completed executing the method:: getAllPoNumbersFromDropDown");
		} catch (Exception e) {
			log.fatal("Unable to get all po numbers From dropdown list" + e.getMessage());
			pdfResultReport.addStepDetails("getAllPoNumbersFromDropDown", "Should get all po numbers From dropdown list",
					"Unable to get all po numbers From dropdown list" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}

	}
	
	public void verifyPoFromTheDropDownList(String poNumber,String PoStatus) throws Throwable {
		try {
			log.info("started executing the method:: verifyPoFromTheDropDownList");

			boolean contains = PoNumbersFromdropdown.contains(poNumber);
		
			if(!contains)
			{
				System.out.println("***********************************************************************");
				
				System.out.println("The Po Number is Not present in Dropdown List---->"+!contains);
				
				
				System.out.println("***********************************************************************");
				
				
				pdfResultReport.addStepDetails("verifyPoFromTheDropDownList", "Po Number Will Not be present for"+ PoStatus+"  ",
						"Succesfully Verified The Po Number"+ poNumber +"It is not present in Dropdown for  "+ PoStatus+" ", "Pass", "Y");
			}
			else {
				
				System.out.println("***********************************************************************");
				
				System.out.println("The Po Number is present in Dropdown List ----->"+contains);
				
				System.out.println("***********************************************************************");
				
				pdfResultReport.addStepDetails("verifyPoFromTheDropDownList", "Po Number Will Not be present for\"+ PoStatus+\" Status \"",
						"Failed to verify the po in dropdown list", "Fail", "N");
			}
			
			waitForObj(2000);
			
			log.info("completed executing the method:: verifyPoFromTheDropDownList");
		} catch (Exception e) {
			log.fatal("Failed to verify the po in dropdown list" + e.getMessage());
			pdfResultReport.addStepDetails("verifyPoFromTheDropDownList", "Po Number Will Not be present for\"+ PoStatus+\" Status \"",
					"Failed to verify the po in dropdown list" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}

	}
	public void VerifyMaterialsDetailsGRN() throws Throwable {
		try {
			log.info("started executing the method:: VerifyMaterialsDetails");
			etendercomponentobj.waitForSpinnerToDisappear();
			waitForObj(5000);
			String ItemCodeUI = text(tendercreationlocators.ItemCodeUi);
			
				highlight(tendercreationlocators.ItemCodeUi);
				String ItemDescriptionUI = text(tendercreationlocators.ItemDescriptionUi);
				
					highlight(tendercreationlocators.ItemDescriptionUi);
					String UOMUI = text(tendercreationlocators.UOMUi);
					
						highlight(tendercreationlocators.ItemDescriptionUi);
						String POQuantityUiUI = text(tendercreationlocators.POQuantityUi);
						if (POQuantityUiUI.equals("PO Quantity: 15.00")) {
							highlight(tendercreationlocators.POQuantityUi);
							String ASNQuantityUiUI = text(tendercreationlocators.ASNQuantityUi);
							if (ASNQuantityUiUI.equals("ASN Quantity: 15.00")) {
								highlight(tendercreationlocators.ASNQuantityUi);
								String RejectedQuantityUI = text(tendercreationlocators.RejectedQuantityUi);
								if (RejectedQuantityUI.equals("Rejected Quantity : 1.00")) {
									highlight(tendercreationlocators.RejectedQuantityUi);
									String ExcessQuantityUI = text(tendercreationlocators.ExcessQuantityUi);
									if (ExcessQuantityUI.equals("Excess Quantity: 5.00")) {
										highlight(tendercreationlocators.ExcessQuantityUi);
										String DamageQuantityUI = text(tendercreationlocators.DamageQuantity);
										if (DamageQuantityUI.equals("Damage Quantity: 1.00")) {
											highlight(tendercreationlocators.DamageQuantity);
											String AcceptedQuantityUI = text(tendercreationlocators.AcceptedQuantityUi);
											if (AcceptedQuantityUI.equals("18.00")) {
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
	public void verifyRejectionValidation() throws Throwable {
		try {
			log.info("started executing the method:: verifyRejectionValidation");
			click(tendercreationlocators.search, "Click the TenderRefNumber");
			clear(tendercreationlocators.search, "Clear the TenderRefNumber");
			set(tendercreationlocators.search, asnNo, "search The PO Doc Number");
			waitForObj(2000);
			click(tendercreationlocators.action, "action");
			waitForElementToBeVisible(By.xpath("//ul[@class='dropdown-menu extended logout big_actv']"));
			IsElementPresent(tendercreationlocators.approveOrReject(asnNo));
			click(tendercreationlocators.approveOrReject(asnNo), "approve or reject");
			etendercomponentobj.waitForSpinnerToDisappear();
			waitForElementToBeVisible(By.xpath("//*[@name='org_asn_id']"));
			waitForObj(3000);
			waitForElementToBeVisible(tendercreationlocators.TabDeliveryChallanChecklist);
			click(tendercreationlocators.TabDeliveryChallanChecklist, "TabDeliveryChallanChecklist");
			// etendercomponentobj.waitForSpinnerToDisappear();
			waitForElementToBeVisible(tendercreationlocators.buyerAcceptence);
			waitForObj(2000);			
			select(tendercreationlocators.buyerAcceptence, "Rejected");
			pdfResultReport.addStepDetails("verifyRejectionValidation","Rejected Comment must be select in Buyer acceptance field ",
					"Successfully selected rejected comment in Buyer acceptance field " + " ", "Pass", "Y");
			SaveASN();
			click(tendercreationlocators.approveOrHold, "approveOrHoldButton");
			waitForElementToBeVisible(tendercreationlocators.approverCommentSection);
			set(tendercreationlocators.approverCommentSection, "Reject", "Reject Comment");
			pdfResultReport.addStepDetails("verifyRejectionValidation","Reject Comment must be eneter sucessfully",
					"Successfully entered reject comment" + " ", "Pass", "Y");
			waitForObj(2000);
			click(tendercreationlocators.revert, "Revert");
			waitForElementToBeVisible(By.xpath("//table[contains(@class,'asn-list-table')]/tbody/tr[2]"));
			pdfResultReport.addStepDetails("verifyRejectionValidation","ASN Should be send back to ASN creator",
					"Successfully sent ASN back to ASN creator" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyRejectionValidation");
		} catch (Exception e) {
			log.fatal("Unable to send ASN back to ASN creator" + e.getMessage());
			pdfResultReport.addStepDetails("verifyRejectionValidation","ASN Should be send back to ASN creator",
					"Unable to send ASN back to ASN creator" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

public void validateInpectionInInspectionLogin() throws Throwable {
		try {
			log.info("started executing the method:: validateInpectionInInspectionLogin");
			waitForElementToBeVisible(tendercreationlocators.inspectionASNDocNo(asnNo));
			click(tendercreationlocators.actionBtn(asnNo), "action");
			IsElementPresent(tendercreationlocators.actionDropDowns(asnNo, "Claim"));
			click(tendercreationlocators.actionDropDowns(asnNo, "Claim"), "actionDropDown");
			etendercomponentobj.waitForSpinnerToDisappear();
			// waitForElementToBeVisible(By.xpath("(//section[@id='main-content-nw']//table//tbody//tr[1])[1]"));
			waitForObj(3000);
			click(tendercreationlocators.actionBtn(asnNo), "action");
			waitForElementToBeVisible(tendercreationlocators.approveOrReview(asnNo));
			IsElementPresent(tendercreationlocators.approveOrReview(asnNo));
			click(tendercreationlocators.approveOrReview(asnNo), "approve or review");
			etendercomponentobj.waitForSpinnerToDisappear();
			waitForElementToBeVisible(By.xpath("//*[@name='org_asn_id']"));
			waitForObj(3000);
			waitForElementToBeVisible(tendercreationlocators.TabDeliveryChallanChecklist);
			click(tendercreationlocators.TabDeliveryChallanChecklist, "TabDeliveryChallanChecklist");
			// etendercomponentobj.waitForSpinnerToDisappear();
			waitForElementToBeVisible(tendercreationlocators.buyerAcceptence);
			waitForObj(2000);
			select(tendercreationlocators.buyerAcceptence, "Provisional");
			SaveASN();
			click(tendercreationlocators.approveOrHold, "approveOrHoldButton");
			waitForElementToBeVisible(tendercreationlocators.approverCommentSection);
			set(tendercreationlocators.approverCommentSection, "Approved", "Approve Comment");
			pdfResultReport.addStepDetails("validateInpectionInInspectionLogin", "Approved comment must be enter sucessfully",
					"Successfully entered approved comment" + " ", "Pass", "Y");
			waitForObj(2000);
			click(tendercreationlocators.approveButton, "approveButton");
			waitForElementToBeVisible(By.xpath("//table[contains(@class,'asn-list-table')]/tbody/tr[2]"));
			pdfResultReport.addStepDetails("validateInpectionInInspectionLogin", "Successfully approved inspection",
					"Successfully approved inspection" + " ", "Pass", "Y");
			log.info("completed executing the method:: validateInpectionInInspectionLogin");
		} catch (Exception e) {
			log.fatal("Unable to approve inspection" + e.getMessage());
			pdfResultReport.addStepDetails("validateInpectionInInspectionLogin", "Successfully accepted inspection",
					"Unable to approve inspection" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

public void verifyInspectValidation() throws Throwable {
		try {
			log.info("started executing the method:: verifyInspectValidation");
			click(tendercreationlocators.search, "Click the TenderRefNumber");
			clear(tendercreationlocators.search, "Clear the TenderRefNumber");
			set(tendercreationlocators.search, asnNo, "search The PO Doc Number");
			waitForObj(2000);
			click(tendercreationlocators.action, "action");
			waitForElementToBeVisible(By.xpath("//ul[@class='dropdown-menu extended logout big_actv']"));
			IsElementPresent(tendercreationlocators.approveOrReject(asnNo));
			click(tendercreationlocators.approveOrReject(asnNo), "approve or reject");
			etendercomponentobj.waitForSpinnerToDisappear();
			waitForElementToBeVisible(By.xpath("//*[@name='org_asn_id']"));
			waitForObj(3000);
			waitForElementToBeVisible(tendercreationlocators.TabDeliveryChallanChecklist);
			click(tendercreationlocators.TabDeliveryChallanChecklist, "TabDeliveryChallanChecklist");
			// etendercomponentobj.waitForSpinnerToDisappear();
			waitForElementToBeVisible(tendercreationlocators.buyerAcceptence);
			waitForObj(2000);
			select(tendercreationlocators.buyerAcceptence, "Accepted");
			SaveASN();
			click(tendercreationlocators.inspectionButton, "inspectionButton");

			waitForElementToBeVisible(tendercreationlocators.approverCommentSection);
			set(tendercreationlocators.approverCommentSection, "Inspect", "Approve Comment");
			pdfResultReport.addStepDetails("verifyInspectValidation", "Inspect comment must be enter sucessfully",
					"Successfully entered Inspect comment" + " ", "Pass", "Y");
			waitForObj(2000);
			click(tendercreationlocators.requestForInspectionButton, "requestForInspectionButton");
			waitForElementToBeVisible(By.xpath("//table[contains(@class,'asn-list-table')]/tbody/tr[2]"));
			pdfResultReport.addStepDetails("verifyInspectValidation", "Successfully validated accept or reject button",
					"Successfully validated accept or reject button" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyInspectValidation");
		} catch (Exception e) {
			log.fatal("Unable to validated accept or reject button" + e.getMessage());
			pdfResultReport.addStepDetails("verifyInspectValidation", "Successfully validated accept or reject button",
					"Unable to validated accept or reject button" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

public void verifyApproveValidation() throws Throwable {
		try {
			log.info("started executing the method:: verifyApproveValidation");
			click(tendercreationlocators.search, "Click the TenderRefNumber");
			clear(tendercreationlocators.search, "Clear the TenderRefNumber");
			set(tendercreationlocators.search, asnNo, "search The PO Doc Number");
			waitForObj(2000);
			click(tendercreationlocators.action, "action");
			waitForElementToBeVisible(By.xpath("//ul[@class='dropdown-menu extended logout big_actv']"));
			IsElementPresent(tendercreationlocators.approveOrReject(asnNo));
			click(tendercreationlocators.approveOrReject(asnNo), "approve or reject");
			etendercomponentobj.waitForSpinnerToDisappear();
			waitForElementToBeVisible(By.xpath("//*[@name='org_asn_id']"));

			waitForElementToBeVisible(tendercreationlocators.TabDeliveryChallanChecklist);
			click(tendercreationlocators.TabDeliveryChallanChecklist, "TabDeliveryChallanChecklist");
			etendercomponentobj.waitForSpinnerToDisappear();
			waitForElementToBeVisible(tendercreationlocators.buyerAcceptence);
			waitForObj(2000);
			select(tendercreationlocators.buyerAcceptence, "Accepted");
			pdfResultReport.addStepDetails("verifyApproveValidation","Accepted Comment must be select in Buyer acceptance field ",
					"Successfully selected accepted comment in Buyer acceptance field " + " ", "Pass", "Y");
			SaveASN();
			click(tendercreationlocators.approveOrHold, "approveOrHoldButton");
			waitForElementToBeVisible(tendercreationlocators.approverCommentSection);
			set(tendercreationlocators.approverCommentSection, "Approve", "Approve Comment");
			pdfResultReport.addStepDetails("verifyApproveValidation", "Approve Comment must be enter sucessfully",
					"Successfully entered approve comment" + " ", "Pass", "Y");
			waitForObj(2000);
			click(tendercreationlocators.approveButton, "approveButton");
			waitForElementToBeVisible(By.xpath("//table[contains(@class,'asn-list-table')]/tbody/tr[2]"));
			pdfResultReport.addStepDetails("verifyApproveValidation", "ASN must be approve sucessfully",
					"Successfully approved ASN" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyApproveValidation");
		} catch (Exception e) {
			log.fatal("Unable to validated accept or reject button" + e.getMessage());
			pdfResultReport.addStepDetails("verifyApproveValidation", "Successfully validated accept or reject button",
					"Unable to validated accept or reject button" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}

public void verifyHoldValidation() throws Throwable {
		try {
			log.info("started executing the method:: verifyHoldValidation");
			click(tendercreationlocators.search, "Click the TenderRefNumber");
			clear(tendercreationlocators.search, "Clear the TenderRefNumber");
			set(tendercreationlocators.search, asnNo, "search The PO Doc Number");
			waitForObj(2000);
			click(tendercreationlocators.action, "action");
			waitForElementToBeVisible(By.xpath("//ul[@class='dropdown-menu extended logout big_actv']"));
			IsElementPresent(tendercreationlocators.approveOrReject(asnNo));
			click(tendercreationlocators.approveOrReject(asnNo), "approve or reject");
			etendercomponentobj.waitForSpinnerToDisappear();
			waitForElementToBeVisible(By.xpath("//*[@name='org_asn_id']"));
			waitForObj(3000);
			waitForElementToBeVisible(tendercreationlocators.TabDeliveryChallanChecklist);
			click(tendercreationlocators.TabDeliveryChallanChecklist, "TabDeliveryChallanChecklist");
			// etendercomponentobj.waitForSpinnerToDisappear();
			waitForElementToBeVisible(tendercreationlocators.buyerAcceptence);
			waitForObj(2000);
			select(tendercreationlocators.buyerAcceptence, "Rejected");
			SaveASN();
			click(tendercreationlocators.approveOrHold, "approveOrHoldButton");
			waitForElementToBeVisible(tendercreationlocators.approverCommentSection);
			set(tendercreationlocators.approverCommentSection, "Hold", "Hold Comment");
			pdfResultReport.addStepDetails("verifyHoldValidation", "Hold comment must be enter sucessfully",
					"Successfully entered hold comment" + " ", "Pass", "Y");
			waitForObj(2000);
			click(tendercreationlocators.hold, "Hold");
			waitForElementToBeVisible(By.xpath("//table[contains(@class,'asn-list-table')]/tbody/tr[2]"));
			pdfResultReport.addStepDetails("verifyHoldValidation", "ASN should be hold",
					"Successfully holded ASN" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyHoldValidation");
		} catch (Exception e) {
			log.fatal("Unable to hold ASN" + e.getMessage());
			pdfResultReport.addStepDetails("verifyHoldValidation", "ASN should be hold",
					"Unable to hold ASN" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
	}
public void verifyPreparedStatus(String currentStatus) throws Throwable {
	try {
		log.info("started executing the method:: verifyPreparedStatus");
		clear(tendercreationlocators.search, "Clear the TenderRefNumber");
		set(tendercreationlocators.search, asnNo, "search The PO Doc Number");
		waitForObj(2000);
		IsElementPresent(tendercreationlocators.ASNStatus(currentStatus));
		waitForObj(2000);
		pdfResultReport.addStepDetails("verifyPreparedStatus", "Prepared status must be verify sucessfully",
				"Successfully verified in prepared status" + " ", "Pass", "Y");
		log.info("completed executing the method:: verifyPreparedStatus");
	} catch (Exception e) {
		log.fatal("Unable to verify the prepared status" + e.getMessage());
		pdfResultReport.addStepDetails("verifyPreparedStatus", "Prepared status must be verify sucessfully",
				"Unable to verify the prepared status" + e.getMessage(), "Fail", "N");
		Assert.fail("Failed Due to " + e.getMessage());
	}
}
	public void verifyRejectedStatus(String currentStatus) throws Throwable {
		try {
			log.info("started executing the method:: verifyRejectedStatus");
			clear(tendercreationlocators.search, "Clear the TenderRefNumber");
			set(tendercreationlocators.search, asnNo, "search The PO Doc Number");
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.ASNStatus(currentStatus));
			waitForObj(2000);
			pdfResultReport.addStepDetails("verifyRejectedStatus", "Rejected status must be verify sucessfully",
					"Successfully verified in rejected status" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyRejectedStatus");
		} catch (Exception e) {
			log.fatal("Unable to verify the rejected status" + e.getMessage());
			pdfResultReport.addStepDetails("verifyRejectedStatus", "Rejected status must be verify sucessfully",
					"Unable to verify the rejected status" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
}
	public void verifyHoldStatus(String currentStatus) throws Throwable {
		try {
			log.info("started executing the method:: verifyHoldStatus");
			clear(tendercreationlocators.search, "Clear the TenderRefNumber");
			set(tendercreationlocators.search, asnNo, "search The PO Doc Number");
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.ASNStatus(currentStatus));
			waitForObj(2000);
			pdfResultReport.addStepDetails("verifyHoldStatus", "Hold status must be verify sucessfully",
					"Successfully verified in hold status" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyHoldStatus");
		} catch (Exception e) {
			log.fatal("Unable to verify the hold status" + e.getMessage());
			pdfResultReport.addStepDetails("verifyHoldStatus", "Hold status must be verify sucessfully",
					"Unable to verify the hold status" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
}
	public void verifyCompletedStatus(String currentStatus) throws Throwable {
		try {
			log.info("started executing the method:: verifyCompletedStatus");
			clear(tendercreationlocators.search, "Clear the TenderRefNumber");
			set(tendercreationlocators.search, asnNo, "search The PO Doc Number");
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.ASNStatus(currentStatus));
			waitForObj(2000);
			pdfResultReport.addStepDetails("verifyCompletedStatus", "Completed status must be verify sucessfully",
					"Successfully verified in completed status" + " ", "Pass", "Y");
			log.info("completed executing the method:: verifyCompletedStatus");
		} catch (Exception e) {
			log.fatal("Unable to verify the completed status" + e.getMessage());
			pdfResultReport.addStepDetails("verifyCompletedStatus", "Completed status must be verify sucessfully",
					"Unable to verify the completed status" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
}
	public void enterExcessQty1(int ItemCodeRow, String QtyNo) throws Throwable {
		try {
			log.info("started executing the method:: enterExcessQty");

			click(tendercreationlocators.EnterExcessQtyBy(ItemCodeRow), " clear ExcessQtyBy");
			ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.EnterExcessQtyBy(ItemCodeRow))
					.sendKeys(Keys.DELETE);
			ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.EnterExcessQtyBy(ItemCodeRow))
					.sendKeys(Keys.ARROW_LEFT);
			ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.EnterExcessQtyBy(ItemCodeRow))
					.sendKeys(Keys.ARROW_LEFT);
			set(tendercreationlocators.EnterExcessQtyBy(ItemCodeRow), QtyNo, "EnterExcessQtyBy");
			String itemCode_3AcceptedQuantity="";
			String itemCode_3AcceptedQuantityText="";

			String itemCode_2AcceptedQuantity="";
			String itemCode_2AcceptedQuantityText="";

			String itemCode_1AcceptedQuantity="";
			String itemCode_1AcceptedQuantityText="";
			itemCode_3AcceptedQuantity = "//span[text()='Item Code_3']/../../../following-sibling::div//h2/b";
			itemCode_3AcceptedQuantityText = ThreadLocalWebdriver.getDriver().
					findElement(By.xpath(itemCode_3AcceptedQuantity)).getText().trim();
			System.out.println(itemCode_3AcceptedQuantityText);
			eTenderComponent.updateDataIntoPropertyFile("itemCode_3AcceptedQuantityValue", itemCode_3AcceptedQuantityText);
			//eTenderComponent.updateDataIntoPropertyFile(key, value)
			
			itemCode_2AcceptedQuantity = "//span[text()='Item Code_2']/../../../following-sibling::div//h2/b";
			itemCode_2AcceptedQuantityText = ThreadLocalWebdriver.getDriver().
					findElement(By.xpath(itemCode_2AcceptedQuantity)).getText().trim();
			System.out.println(itemCode_2AcceptedQuantityText);
			eTenderComponent.updateDataIntoPropertyFile("itemCode_2AcceptedQuantityValue", itemCode_2AcceptedQuantityText);
			
			itemCode_1AcceptedQuantity = "//span[text()='Item Code_1']/../../../following-sibling::div//h2/b";
			itemCode_1AcceptedQuantityText = ThreadLocalWebdriver.getDriver().
					findElement(By.xpath(itemCode_1AcceptedQuantity)).getText().trim();
			System.out.println(itemCode_1AcceptedQuantityText);
			eTenderComponent.updateDataIntoPropertyFile("itemCode_1AcceptedQuantityValue", itemCode_1AcceptedQuantityText);

			waitForObj(2000);



			waitForObj(2000);

			pdfResultReport.addStepDetails("enterExcessQty", "Should enter Excess Quantity",
					"Successfully enter Excess  Quantity" + " ", "Pass", "Y");
			log.info("completed executing the method:: enterExcessQty");
		} catch (Exception e) {
			log.fatal("Unable to enter Excess  Qty" + e.getMessage());
			pdfResultReport.addStepDetails("enterExcessQty", "Should enter Excess  Quantity",
					"Unable to enter Excess  Qty " + e.getMessage(), "Fail", "N");

			Assert.fail("Failed Due to " + e.getMessage());

		}
	}
	
	
}
