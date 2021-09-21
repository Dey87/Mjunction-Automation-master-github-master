package com.components;

import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.baseClasses.BaseClass_Web;
import com.baseClasses.PDFResultReport;
import com.baseClasses.ThreadLocalWebdriver;
import com.objectRepository.TenderCreation_Locators;

public class VendorRegistrationComponent extends BaseClass_Web {

	TenderCreation_Locators tendercreationlocators = new TenderCreation_Locators();

	eTenderComponent etendercomponentobj = new eTenderComponent(pdfResultReport);

	public VendorRegistrationComponent(PDFResultReport pdfresultReport) {
		this.pdfResultReport = pdfresultReport;
	}

	public void ClickOnBidderRegistration() throws Throwable {
		try {
			log.info("started executing the method:: ClickOnBidderRegistration");
			JSClick(tendercreationlocators.BidderRegistration, "BidderRegistration");
			etendercomponentobj.waitForSpinnerToDisappear();
			
			pdfResultReport.addStepDetails("clickOnRaiseInvoiceIcon", "Should navigate to Registration Option page",
					"Successfully navigated to Registration Option page" + " ", "Pass", "Y");
			log.info("completed executing the method:: ClickOnBidderRegistration");
		} catch (Exception e) {
			log.fatal("Unable to navigate to Registration Optione page" + e.getMessage());
			pdfResultReport.addStepDetails("ClickOnBidderRegistration", "Should navigate to Registration Option page",
					"Unable to navigate to Registration Option page" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}	}
	public void SelectFreshRegistration() throws Throwable {
		try {
			log.info("started executing the method:: SelectFreshRegistration");
			JSClick(tendercreationlocators.FreshRegistration, "FreshRegistration");
			waitForObj(2000);
			JSClick(tendercreationlocators.btnContinue, "btnContinue");
			waitForObj(2000);
			etendercomponentobj.waitForSpinnerToDisappear();
			
			pdfResultReport.addStepDetails("SelectFreshRegistration", "Should able to select Fresh Registration",
					"Successfully selected Fresh Registration" + " ", "Pass", "Y");
			log.info("completed executing the method:: SelectFreshRegistration");
		} catch (Exception e) {
			log.fatal("Unable to select  Fresh Registration" + e.getMessage());
			pdfResultReport.addStepDetails("SelectFreshRegistration", "Should able to select Fresh Registration",
					"Unable to select  Fresh Registration" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}	}

	public void SelectCountry() throws Throwable {
		try {
			log.info("started executing the method:: SelectCountry");
			//India
			select(tendercreationlocators.SelectCountry, pdfResultReport.testData.get("Country"));
			waitForObj(2000);
			etendercomponentobj.waitForSpinnerToDisappear();
			
			pdfResultReport.addStepDetails("SelectCountry", "Should able to select country",
					"Successfully selected Country" + " ", "Pass", "Y");
			log.info("completed executing the method:: SelectCountry");
		} catch (Exception e) {
			log.fatal("Unable to select country" + e.getMessage());
			pdfResultReport.addStepDetails("SelectCountry", "Should able to select country",
					"Unable to select country" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}	}


	public static int getrandomInterger(int min, int max) {

		return ((int) (Math.random() * (max - min))) + min;

	}
	public void UserDetails() throws Throwable {
		try {
			log.info("started executing the method:: UserDetails");
			scrollToElement(tendercreationlocators.Title);
			waitForObj(2000);
			select(tendercreationlocators.Title, "Mr.");
			waitForObj(2000);
			 String Firstname = "Jit".concat(String.valueOf(getrandomInterger(1000, 1000000)));
			 String LastName = "Bhowmick".concat(String.valueOf(getrandomInterger(1000, 10000)));
			JSClick(tendercreationlocators.FirstName, "FirstName");
			set(tendercreationlocators.FirstName, Firstname,"FirstName");
			waitForObj(2000);
			JSClick(tendercreationlocators.LastName, "LastName");
			set(tendercreationlocators.LastName, LastName,"LastName");
			waitForObj(2000);
			
			pdfResultReport.addStepDetails("UserDetails", "Should able to enter UserDetails",
					"Successfully entered User Details" + " ", "Pass", "Y");
			log.info("completed executing the method:: UserDetails");
		} catch (Exception e) {
			log.fatal("Unable to Enter user Details" + e.getMessage());
			pdfResultReport.addStepDetails("SelectCountry", "Should able to enter UserDetails",
					"Unable to Enter user Details" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}	}
	
	public void WaitforOTP() throws Throwable {
		try {
			String time=etendercomponentobj.getDataFromPropertiesFile("WaitForOTP");
			long t=Long.parseLong(time)*60000;
			waitForObj(t);
		}
		catch (Exception e) {
			log.fatal("Unable to enter OTP" + e.getMessage());
		}
	}
	
	public void VerifyOTPValidity() throws Throwable {
		try {
			log.info("started executing the method:: VerifyOTPValidity");
			String TimeStatus=text(tendercreationlocators.OtpValidity);
			String time=TimeStatus.substring(14, 16);
			int t=Integer.parseInt(time);
			if(t<=20)
			{
			pdfResultReport.addStepDetails("VerifyOTPValidity", "Should able to verify OTP Validity",
					"Successfully verified OTP Validity" + " ", "Pass", "Y");
			}
			else
			{
				pdfResultReport.addStepDetails("EnterOTP", "Should able to verify OTP Validity",
						"Unable to verify OTP Validity", "Fail", "N");	
			}
			log.info("completed executing the method:: VerifyOTPValidity");
		} catch (Exception e) {
			log.fatal("Unable to verify OTP Validity" + e.getMessage());
			pdfResultReport.addStepDetails("EnterOTP", "Should able to verify OTP Validity",
					"Unable to verify OTP Validity" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}	}	
		
	public void VerifySaveButton() throws Throwable {
		try {
			log.info("started executing the method:: VerifySaveButton");
			scrollToElement(tendercreationlocators.savevendor);
			highlight(tendercreationlocators.savevendor);
		boolean val=ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.savevendor).isEnabled();
		if(val==true)
		{
			pdfResultReport.addStepDetails("VerifySaveButton", "Should able to verify Save Button",
					"Successfully verified Save Button" + " ", "Pass", "Y");
		}
		else
		{
			pdfResultReport.addStepDetails("VerifySaveButton", "Should able to verify Save Button",
					"Unable to verify Save Button" , "Fail", "N");
			
		}
			log.info("completed executing the method:: VerifySaveButton");
		} catch (Exception e) {
			log.fatal("Unable to verify Save Button" + e.getMessage());
			pdfResultReport.addStepDetails("VerifySaveButton", "Should able to verify Save Button",
					"Unable to verify Save Button" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}	}
	public void VerifyOtherTabs() throws Throwable {
		try {
			log.info("started executing the method:: VerifyOtherTabs");
			waitForObj(5000);
			IsElementPresent(tendercreationlocators.TabItemCategory);
			highlight(tendercreationlocators.TabItemCategory);
			waitForObj(2000);
			IsElementPresent(tendercreationlocators.TabOrganization);
			highlight(tendercreationlocators.TabOrganization);
			pdfResultReport.addStepDetails("VerifyOtherTabs", "Should able to verify Other Tabs",
					"Successfully verified Other Tabs" + " ", "Pass", "Y");
			log.info("completed executing the method:: VerifyOtherTabs");
		} catch (Exception e) {
			log.fatal("Unable to verify other Tabs" + e.getMessage());
			pdfResultReport.addStepDetails("VerifyOtherTabs", "Should able to verify Other Tabs",
					"Unable to verify other Tabs" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}	}	
	public void GetOTP() throws Throwable {
		try {
			log.info("started executing the method:: VerifyOTP");
			
			JSClick(tendercreationlocators.MobileNum, "MobileNum");
			set(tendercreationlocators.MobileNum,  pdfResultReport.testData.get("Mobile"),"MobileNum");
			waitForObj(2000);
			JSClick(tendercreationlocators.Email, "Email");
			set(tendercreationlocators.Email, pdfResultReport.testData.get("Email"),"Email");
			waitForObj(2000);
			JSClick(tendercreationlocators.btnGetOTP, "btnGetOTP");
			etendercomponentobj.waitForSpinnerToDisappear();
			pdfResultReport.addStepDetails("VerifyOTP", "Should able to get OTP",
					"Successfully received OTP" + " ", "Pass", "Y");
			log.info("completed executing the method:: VerifyOTP");
		} catch (Exception e) {
			log.fatal("Unable to get OTP" + e.getMessage());
			pdfResultReport.addStepDetails("VerifyOTP", "Should able to get OTP",
					"Unable to get OTP" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}	}	
public void bidderRegristrationFormValidation() throws Throwable {
		try {
			log.info("started executing the method:: bidderRegristrationFormValidation");
			IsElementPresent(tendercreationlocators.Companyname);
			IsElementPresent(tendercreationlocators.OfficeAddress);
			IsElementPresent(tendercreationlocators.City);
			IsElementPresent(tendercreationlocators.Postalcode);
			pdfResultReport.addStepDetails("bidderRegristrationFormValidation", "Bidder Regristration Form must be open sucessfully",
					"Successfully opened Bidder Regristration Form" + " ", "Pass", "Y");
			log.info("completed executing the method:: bidderRegristrationFormValidation");
		} catch (Exception e) {
			log.fatal("Unable to open Bidder Regristration Form" + e.getMessage());
			pdfResultReport.addStepDetails("bidderRegristrationFormValidation", "Bidder Regristration Form must be open sucessfully",
					"Unable to open Bidder Regristration Form" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}	}
	
	
	public void PANFieldValidationForIndiaCountry() throws Throwable {
			try {
				log.info("started executing the method:: PANFieldValidationForIndiaCountry");
				 
			List<WebElement> ele=	ThreadLocalWebdriver.getDriver().findElements(tendercreationlocators.Pannum);
				 if (ele.size()>0)
	             {
					 System.out.println("PAN field is displayed");
					 pdfResultReport.addStepDetails("PANFieldValidationForIndiaCountry", "PAN field should be display",
								"Successfully displayed PAN field" + " ", "Pass", "Y");
	             }
				 else
				 {
					 
						pdfResultReport.addStepDetails("PANFieldValidationForIndiaCountry", "PAN field should be display",
								"Uable to display PAN field" , "Fail", "N"); 
				 }
				
					log.info("completed executing the method:: PANFieldValidationForIndiaCountry");
				} catch (Exception e) {
					log.fatal("Uable to display PAN field" + e.getMessage());
					pdfResultReport.addStepDetails("PANFieldValidationForIndiaCountry", "PAN field should be display",
							"Uable to display PAN field" + e.getMessage(), "Fail", "N");
					Assert.fail("Failed Due to " + e.getMessage());
				}	}
	
	public void PANFieldValidationForOtherCountry() throws Throwable {
		try {
			log.info("started executing the method:: PANFieldValidationForOtherCountry");
			List<WebElement> ele=	ThreadLocalWebdriver.getDriver().findElements(tendercreationlocators.Pannum);
			 if (ele.size()>0)
             {
				 pdfResultReport.addStepDetails("PANFieldValidationForOtherCountry", "PAN field should not be display",
							"able to display PAN field" , "Fail", "N"); 
             }
			 else
			 {
				 pdfResultReport.addStepDetails("PANFieldValidationForOtherCountry", "PAN field should not be display",
							"Successfully not displayed PAN field" + " ", "Pass", "Y");
			 }
			pdfResultReport.addStepDetails("PANFieldValidationForOtherCountry", "PAN field should not be display",
					"Successfully not displayed PAN field" + " ", "Pass", "Y");
			log.info("completed executing the method:: PANFieldValidationForOtherCountry");
		} catch (Exception e) {
			log.fatal("able to display PAN field" + e.getMessage());
			pdfResultReport.addStepDetails("PANFieldValidationForOtherCountry", "PAN field should not be display",
					"able to display PAN field" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}
		}
public void mobileNumberFieldValidation() throws Throwable {
		try {
			log.info("started executing the method:: mobileNumberFieldValidation");
			JSClick(tendercreationlocators.MobileNum, "MobileNum");
			set(tendercreationlocators.MobileNum,  pdfResultReport.testData.get("Mobile"),"MobileNum");
			waitForObj(3000);
			IsElementPresent(tendercreationlocators.mobileNoField);
			waitForObj(3000);
			pdfResultReport.addStepDetails("mobileNumberFieldValidation", "Mobile number is invalid! error message must be display",
					"Successfully displayed Mobile number is invalid! error message" + " ", "Pass", "Y");
			log.info("completed executing the method:: mobileNumberFieldValidation");
		} catch (Exception e) {
			log.fatal("Unable to display error message" + e.getMessage());
			pdfResultReport.addStepDetails("mobileNumberFieldValidation", "Mobile number is invalid! popup must be display",
					"Unable to display error message" + e.getMessage(), "Fail", "N");
			Assert.fail("Failed Due to " + e.getMessage());
		}	
}
public void EnterOTP() throws Throwable {
	try {
		log.info("started executing the method:: EnterOTP");
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the OTP");
		String OTP = sc.nextLine();
		etendercomponentobj.updateDataIntoPropertyFile("OTP", OTP);
		waitForObj(2000);
		JSClick(tendercreationlocators.EmailOTP, "EmailOTP");
		set(tendercreationlocators.EmailOTP, etendercomponentobj.getDataFromPropertiesFile("OTP"), "Email");
		JSClick(tendercreationlocators.ValidateSubmit, "ValidateSubmit");
		etendercomponentobj.waitForSpinnerToDisappear();
		JSClick(tendercreationlocators.btnOK, "btnOK");
		waitForObj(5000);
		etendercomponentobj.waitForSpinnerToDisappear();
		pdfResultReport.addStepDetails("EnterOTP", "Should able to enter OTP", "Successfully entered OTP" + " ",
				"Pass", "Y");
		log.info("completed executing the method:: EnterOTP");
	} catch (Exception e) {
		log.fatal("Unable to enter OTP" + e.getMessage());
		pdfResultReport.addStepDetails("EnterOTP", "Should able to enter OTP",
				"Unable to enter OTP" + e.getMessage(), "Fail", "N");
		Assert.fail("Failed Due to " + e.getMessage());
	}
}

public void CompanyDetails() throws Throwable {
	try {
		log.info("started executing the method:: CompanyDetails");
		scrollToElement(tendercreationlocators.Pannum);
		clear(tendercreationlocators.Pannum, "Pannum");
		JSClick(tendercreationlocators.Pannum, "Pannum");
		String PAN = "ABCDE".concat(String.valueOf(getrandomInterger(1000, 10000))).concat("F");
		set(tendercreationlocators.Pannum, PAN, "Pannum");
		waitForObj(2000);
		clear(tendercreationlocators.Companyname, "Companyname");
		JSClick(tendercreationlocators.Companyname, "Companyname");
		set(tendercreationlocators.Companyname, pdfResultReport.testData.get("CompanyName"), "Companyname");
		waitForObj(2000);
		// Public Limited
		select(tendercreationlocators.BuisnessType, pdfResultReport.testData.get("BuisnessType"));
		waitForObj(2000);
		clear(tendercreationlocators.OfficeAddress, "OfficeAddress");
		JSClick(tendercreationlocators.OfficeAddress, "OfficeAddress");
		set(tendercreationlocators.OfficeAddress, pdfResultReport.testData.get("Address"), "OfficeAddress");
		waitForObj(2000);
		clear(tendercreationlocators.City, "City");
		JSClick(tendercreationlocators.City, "City");
		set(tendercreationlocators.City, pdfResultReport.testData.get("City"), "City");
		waitForObj(2000);
		// Telangana
		select(tendercreationlocators.State, pdfResultReport.testData.get("State"));
		waitForObj(2000);
		clear(tendercreationlocators.Postalcode, "Postalcode");
		JSClick(tendercreationlocators.Postalcode, "Postalcode");
		set(tendercreationlocators.Postalcode, pdfResultReport.testData.get("Zip"), "Postalcode");
		waitForObj(2000);
		clear(tendercreationlocators.Phoneno, "Phoneno");
		JSClick(tendercreationlocators.Phoneno, "Phoneno");
		set(tendercreationlocators.Phoneno, pdfResultReport.testData.get("Mobile"), "Phoneno");
		waitForObj(2000);
		clear(tendercreationlocators.Faxno, "Faxno");
		JSClick(tendercreationlocators.Faxno, "Faxno");
		set(tendercreationlocators.Faxno, pdfResultReport.testData.get("Mobile"), "Faxno");
		waitForObj(2000);
		pdfResultReport.addStepDetails("CompanyDetails", "Should able to Enter Company Details",
				"Successfully Entered Company Details" + " ", "Pass", "Y");
		log.info("completed executing the method:: CompanyDetails");
	} catch (Exception e) {
		log.fatal("Unable to Enter Company Details" + e.getMessage());
		pdfResultReport.addStepDetails("CompanyDetails", "Should able to Enter Company Details",
				"Unable to Enter Company Details" + e.getMessage(), "Fail", "N");
		Assert.fail("Failed Due to " + e.getMessage());
	}
}

public void NavigatedOtherTabs() throws Throwable {
	try {
		log.info("started executing the method:: VerifyOtherTabs");
		waitForObj(5000);
		IsElementPresent(tendercreationlocators.TabItemCategory);
		highlight(tendercreationlocators.TabItemCategory);
		JSClick(tendercreationlocators.TabItemCategory, "FaxTabItemCategoryno");
		etendercomponentobj.waitForSpinnerToDisappear();
		waitForObj(2000);
		IsElementPresent(tendercreationlocators.TabOrganization);
		highlight(tendercreationlocators.TabOrganization);
		JSClick(tendercreationlocators.TabItemCategory, "TabItemCategory");
		etendercomponentobj.waitForSpinnerToDisappear();
		pdfResultReport.addStepDetails("NavigatedOtherTabs", "Should able to verify Other Tabs",
				"Successfully verified Other Tabs" + " ", "Pass", "Y");
		log.info("completed executing the method:: NavigatedOtherTabs");
	} catch (Exception e) {
		log.fatal("Unable to verify other Tabs" + e.getMessage());
		pdfResultReport.addStepDetails("NavigatedOtherTabs", "Should able to verify Other Tabs",
				"Unable to verify other Tabs" + e.getMessage(), "Fail", "N");
		Assert.fail("Failed Due to " + e.getMessage());
	}
}

public void VerifyFields() throws Throwable {
	try {
		log.info("started executing the method:: CompanyDetails");
		waitForObj(2000);
		// Public Limited
		select(tendercreationlocators.BuisnessType, pdfResultReport.testData.get("BuisnessType"));
		waitForObj(2000);
		JSClick(tendercreationlocators.OfficeAddress, "OfficeAddress");
		set(tendercreationlocators.OfficeAddress, pdfResultReport.testData.get("Address"), "OfficeAddress");
		waitForObj(2000);
		JSClick(tendercreationlocators.City, "City");
		set(tendercreationlocators.City, pdfResultReport.testData.get("City"), "City");
		waitForObj(2000);
		// Telangana
		select(tendercreationlocators.State, pdfResultReport.testData.get("State"));
		waitForObj(2000);
		JSClick(tendercreationlocators.Postalcode, "Postalcode");
		set(tendercreationlocators.Postalcode, pdfResultReport.testData.get("Zip"), "Postalcode");
		waitForObj(2000);
		JSClick(tendercreationlocators.Phoneno, "Phoneno");
		set(tendercreationlocators.Phoneno, pdfResultReport.testData.get("Mobile"), "Phoneno");
		waitForObj(2000);
		JSClick(tendercreationlocators.Faxno, "Faxno");
		set(tendercreationlocators.Faxno, pdfResultReport.testData.get("Mobile"), "Faxno");
		waitForObj(2000);
		pdfResultReport.addStepDetails("VerifyFields",
				"Should able to Enter Company Details without mandatory Fileds",
				"Successfully Entered Company Details" + " ", "Pass", "Y");
		log.info("completed executing the method:: VerifyFields");
	} catch (Exception e) {
		log.fatal("Unable to Enter Company Details" + e.getMessage());
		pdfResultReport.addStepDetails("VerifyFields",
				"Should able to Enter Company Details without mandatory Fileds",
				"Unable to Enter Company Details" + e.getMessage(), "Fail", "N");
		Assert.fail("Failed Due to " + e.getMessage());
	}
}

public void VerifyMobileandEmailDisabled() throws Throwable {
	try {
		log.info("started executing the method:: VerifyOTP");
		highlight(tendercreationlocators.MobileNum);
		boolean val = ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.MobileNum).isEnabled();
		System.out.println("Mobile field is " + val);
		highlight(tendercreationlocators.Email);
		boolean val1 = ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.Email).isEnabled();
		System.out.println("Email field is " + val1);
		if (val == false && val1 == false) {
			pdfResultReport.addStepDetails("VerifyMobileandEmailDisabled",
					"Mobile and Email field shsould be disabled", "Mobile and Email fields is disabled" + " ",
					"Pass", "Y");
		} else {
			pdfResultReport.addStepDetails("VerifyMobileandEmailDisabled",
					"Mobile and Email field shsould be disabled", "Mobile and Email fields is enabled", "Fail",
					"N");
		}

		log.info("completed executing the method:: VerifyMobileandEmailDisabled");
	} catch (Exception e) {
		log.fatal("Unable to get OTP" + e.getMessage());
		pdfResultReport.addStepDetails("VerifyMobileandEmailDisabled", "Mobile and Email field shsould be disabled",
				"Unable to get OTP" + e.getMessage(), "Fail", "N");
		Assert.fail("Failed Due to " + e.getMessage());
	}
}

public void VerifySubmitwithItemCategoryOnly() throws Throwable {
	try {
		log.info("started executing the method:: VerifyOtherTabs");
		waitForObj(5000);
		IsElementPresent(tendercreationlocators.TabItemCategory);
		highlight(tendercreationlocators.TabItemCategory);
		JSClick(tendercreationlocators.TabItemCategory, "FaxTabItemCategoryno");
		etendercomponentobj.waitForSpinnerToDisappear();
		waitForObj(2000);
		IsElementPresent(tendercreationlocators.TabOrganization);
		highlight(tendercreationlocators.TabOrganization);
		JSClick(tendercreationlocators.TabOrganization, "TabOrganization");
		etendercomponentobj.waitForSpinnerToDisappear();
		JSClick(tendercreationlocators.TabItemCategory, "TabItemCategory");
		etendercomponentobj.waitForSpinnerToDisappear();
		JSClick(tendercreationlocators.Accommodationandfoodserviceactivities,
				"Accommodationandfoodserviceactivities");
		waitForObj(5000);
		boolean val = ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.btnSubmit).isEnabled();
		if (val == false) {
			pdfResultReport.addStepDetails("VerifySubmitwithItemCategoryOnly",
					"Should not able to submit with Category changes",
					"Successfully verified submit not Enabled" + " ", "Pass", "Y");
		} else {
			ThreadLocalWebdriver.getDriver().findElement(tendercreationlocators.btnSubmit).click();
			pdfResultReport.addStepDetails("VerifySubmitwithItemCategoryOnly",
					"Should not able to submit with Category changes", "Unable to verify submit not Enabled ",
					"Fail", "N");
		}
		waitForObj(5000);
		log.info("completed executing the method:: VerifySubmitwithItemCategoryOnly");
	} catch (Exception e) {
		log.fatal("Unable to verify other Tabs" + e.getMessage());
		pdfResultReport.addStepDetails("VerifySubmitwithItemCategoryOnly", "Should able to verify Other Tabs",
				"Unable to verify other Tabs" + e.getMessage(), "Fail", "N");
		Assert.fail("Failed Due to " + e.getMessage());
	}
}

public void Addorganization() throws Throwable {
	try {
		log.info("started executing the method:: Addorganization");
		JSClick(tendercreationlocators.TabItemCategory, "TabItemCategory");
		etendercomponentobj.waitForSpinnerToDisappear();
		JSClick(tendercreationlocators.Accommodationandfoodserviceactivities,
				"Accommodationandfoodserviceactivities");
		waitForObj(5000);
		JSClick(tendercreationlocators.TabOrganization, "TabOrganization");
		etendercomponentobj.waitForSpinnerToDisappear();
		JSClick(tendercreationlocators.MasterOrgList, "MasterOrgList");
		set(tendercreationlocators.MasterOrgList, "Apple", "MasterOrgList");
		waitForObj(3000);
		JSClick(tendercreationlocators.addorg, "addorg");
		waitForObj(5000);
		pdfResultReport.addStepDetails("Addorganization", "Should able to Add organization",
				"Successfully added organization " + " ", "Pass", "Y");
		log.info("completed executing the method:: Addorganization");
	} catch (Exception e) {
		log.fatal("Unable to add organization" + e.getMessage());
		pdfResultReport.addStepDetails("Addorganization", "Should able to Add organization",
				"Unable to add organization" + e.getMessage(), "Fail", "N");
		Assert.fail("Failed Due to " + e.getMessage());
	}
}

public void VerifyDuplicateUserID() throws Throwable {
	try {
		log.info("started executing the method:: EnterDuplicateUserID");
		JSClick(tendercreationlocators.btnSubmit, "btnSubmit");
		etendercomponentobj.waitForSpinnerToDisappear();
		waitForObj(5000);
		JSClick(tendercreationlocators.UserName, "UserName");
		set(tendercreationlocators.UserName, "JHONTY", "UserName");
		waitForObj(3000);
		JSClick(tendercreationlocators.AcceptDisclaimer, "AcceptDisclaimer");
		waitForObj(3000);
		JSClick(tendercreationlocators.SubmitUserName, "SubmitUserName");
		waitForObj(3000);
		List<WebElement> ele = ThreadLocalWebdriver.getDriver()
				.findElements(tendercreationlocators.usercodeunavailable);
		if (ele.size() > 0) {
			pdfResultReport.addStepDetails("VerifyDuplicateUserID",
					"Should able to verify error message of Duplicate username",
					"Successfully verified Duplicate Username error message " + " ", "Pass", "Y");
		} else {
			pdfResultReport.addStepDetails("VerifyDuplicateUserID",
					"Should able to verify error message of Duplicate username",
					"Unable to add verify Error messge of duplicate User name", "Fail", "N");
		}

		pdfResultReport.addStepDetails("VerifyDuplicateUserID",
				"Should able to verify error message of Duplicate username",
				"Successfully verified Duplicate Username error message " + " ", "Pass", "Y");
		log.info("completed executing the method:: VerifyDuplicateUserID");
	} catch (Exception e) {
		log.fatal("Unable to add verify Error messge of duplicate User name" + e.getMessage());
		pdfResultReport.addStepDetails("VerifyDuplicateUserID",
				"Should able to verify error message of Duplicate username",
				"Unable to add verify Error messge of duplicate User name" + e.getMessage(), "Fail", "N");
		Assert.fail("Failed Due to " + e.getMessage());
	}
}

public void VerifyCharlimit() throws Throwable {
	try {
		log.info("started executing the method:: VerifyCharlimit");
		JSClick(tendercreationlocators.btnSubmit, "btnSubmit");
		etendercomponentobj.waitForSpinnerToDisappear();
		waitForObj(5000);
		String UserName = "ABC".concat(String.valueOf(getrandomInterger(1000, 10000)));
		JSClick(tendercreationlocators.UserName, "UserName");
		set(tendercreationlocators.UserName, UserName, "UserName");
		waitForObj(3000);
		JSClick(tendercreationlocators.AcceptDisclaimer, "AcceptDisclaimer");
		waitForObj(3000);
		highlight(tendercreationlocators.CharLimit);
		waitForObj(5000);
		pdfResultReport.addStepDetails("VerifyCharlimit", "Should able to verify char limit text",
				"Successfully verified char limit text " + " ", "Pass", "Y");
		JSClick(tendercreationlocators.SubmitUserName, "SubmitUserName");
		log.info("completed executing the method:: VerifyCharlimit");
	} catch (Exception e) {
		log.fatal("Unable to add verify char limit text" + e.getMessage());
		pdfResultReport.addStepDetails("VerifyCharlimit", "Should able to verify char limit text",
				"Unable to add verify char limit text" + e.getMessage(), "Fail", "N");
		Assert.fail("Failed Due to " + e.getMessage());
	}
}
public void VerifyAfterClickonGetOTPTimerWillOpen() throws Throwable {
	try {
		log.info("started executing the method:: VerifyAfterClickonGetOTPTimerWillOpen");
		String TimeStatus = text(tendercreationlocators.OTPValidity);
		String time = TimeStatus.substring(32, 35).trim();

		int t = Integer.parseInt(time);
		System.out.println("Time now " + t);
		if (t <= 20) {
			pdfResultReport.addStepDetails("VerifyAfterClickonGetOTPTimerWillOpen", "Timer should be verify",
					"Successfully verified timer" + " ", "Pass", "Y");
		} else {
			pdfResultReport.addStepDetails("VerifyAfterClickonGetOTPTimerWillOpen", "Timer should be verify",
					"Unable to verify timer", "Fail", "N");

		}
		pdfResultReport.addStepDetails("VerifyAfterClickonGetOTPTimerWillOpen", "Timer should be verify",
				"Successfully verified timer" + " ", "Pass", "Y");
		log.info("completed executing the method:: VerifyAfterClickonGetOTPTimerWillOpen");

	} catch (Exception e) {
		log.fatal("Unable to verify timer" + e.getMessage());
		pdfResultReport.addStepDetails("VerifyAfterClickonGetOTPTimerWillOpen", "Timer should be verify",
				"Unable to verify timer" + e.getMessage(), "Fail", "N");
		Assert.fail("Failed Due to " + e.getMessage());
	}
}

public void GetOTPButtonValidation() throws Throwable {
	try {
		log.info("started executing the method:: VerifyOTP");
		WebDriver driver = ThreadLocalWebdriver.getDriver();
		List<WebElement> wb = driver.findElements(tendercreationlocators.btnGetOTP);
		for (WebElement we : wb) {
			if (we.isDisplayed() && we.isEnabled()) {
				we.click();
				pdfResultReport.addStepDetails("GetOTPButtonValidation",
						" User should not be able to click on the GET OTP before the timer stop",
						"Able to click on GET OTP before timer stop", "Fail", "N");
			} else {
				pdfResultReport.addStepDetails("GetOTPButtonValidation",
						"User should not be able to click on the GET OTP before the timer stop",
						"Successfully not able to click on GET OTP before timer stop" + " ", "Pass", "Y");
			}
		}

		pdfResultReport.addStepDetails("GetOTPButtonValidation",
				"User should not be able to click on the GET OTP before the timer stop",
				"Successfully not able to click on GET OTP before timer stop" + " ", "Pass", "Y");
		log.info("completed executing the method:: VerifyOTP");
	} catch (Exception e) {
		log.fatal("Able to click on GET OTP before timer stop" + e.getMessage());
		pdfResultReport.addStepDetails("GetOTPButtonValidation",
				"User should not be able to click on the GET OTP before the timer stop",
				"Able to click on GET OTP before timer stop" + e.getMessage(), "Fail", "N");
		Assert.fail("Failed Due to " + e.getMessage());
	}
}

public void SelectContinueWithTRN() throws Throwable {
	try {
		log.info("started executing the method:: SelectContinueWithTRN");
		JSClick(tendercreationlocators.continueWithTRN, "continueWithTRN");
		waitForElementToBeVisible(tendercreationlocators.TRNNumber);
		pdfResultReport.addStepDetails("SelectContinueWithTRN", "Should able to select ContinueWithTRN",
				"Successfully selected ContinueWithTRN" + " ", "Pass", "Y");
		log.info("completed executing the method:: SelectContinueWithTRN");
	} catch (Exception e) {
		log.fatal("Unable to select ContinueWithTRN" + e.getMessage());
		pdfResultReport.addStepDetails("SelectContinueWithTRN", "Should able to select ContinueWithTRN",
				"Unable to select ContinueWithTRN" + e.getMessage(), "Fail", "N");
		Assert.fail("Failed Due to " + e.getMessage());
	}
}

public void EnterTRNnumber() throws Throwable {
	try {
		log.info("started executing the method:: EnterTRNnumber");
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the TRN Number");
		String TRNNumber = sc.nextLine();
		etendercomponentobj.updateDataIntoPropertyFile("TRNNumber", TRNNumber);
		waitForObj(2000);
		JSClick(tendercreationlocators.TRNNumber, "TRNNumber");
		set(tendercreationlocators.TRNNumber, etendercomponentobj.getDataFromPropertiesFile("TRNNumber"),
				"TRNNumber");
		waitForObj(2000);
		JSClick(tendercreationlocators.verifiedEmailAddress, "verifiedEmailAddress");
		set(tendercreationlocators.verifiedEmailAddress, pdfResultReport.testData.get("Email"), "Email");
		waitForObj(2000);
		JSClick(tendercreationlocators.verifiedMobileNumber, "verifiedMobileNumber");
		set(tendercreationlocators.verifiedMobileNumber, pdfResultReport.testData.get("Mobile"), "MobileNum");
		pdfResultReport.addStepDetails("EnterTRNnumber",
				"Should able to enter TRNnumber, verifiedEmailAddress, verifiedMobileNumber",
				"Successfully entered TRNnumber, verifiedEmailAddress, verifiedMobileNumber" + " ", "Pass", "Y");
		waitForObj(2000);
		JSClick(tendercreationlocators.btnContinue, "btnContinue");
		etendercomponentobj.waitForSpinnerToDisappear();
		waitForElementToBeVisible(tendercreationlocators.Pannum);
		pdfResultReport.addStepDetails("EnterTRNnumber", "Bidder Registration form should be displayed",
				"Successfully displayed Bidder Registration form" + " ", "Pass", "Y");
		log.info("completed executing the method:: EnterTRNnumber");
	} catch (Exception e) {
		log.fatal("Unable to display bidder registration form" + e.getMessage());
		pdfResultReport.addStepDetails("EnterTRNnumber", "Bidder Registration form should be displayed",
				"Unable to display bidder registration form" + e.getMessage(), "Fail", "N");
		Assert.fail("Failed Due to " + e.getMessage());
	}
}

public void bidderRegristrationForm() throws Throwable {
	try {
		log.info("started executing the method:: bidderRegristrationForm");
		highlight((By.xpath("//input[@name='pan']/..")));
		IsElementPresent(tendercreationlocators.Pannum);
		waitForObj(2000);
		highlight(By.xpath("//input[@name='companyname']/.."));
		IsElementPresent(tendercreationlocators.Companyname);
		waitForObj(2000);
		highlight(By.xpath("//select[@name='naturecompany']/.."));
		IsElementPresent(tendercreationlocators.BuisnessType);
		waitForObj(2000);
		highlight(By.xpath("//textarea[@ng-model='vendor.officeaddress']/.."));
		IsElementPresent(tendercreationlocators.OfficeAddress);
		pdfResultReport.addStepDetails("bidderRegristrationForm",
				"Bidder Registration form should be displayed with previously saved data",
				"Successfully displayed Bidder Registration form with previously saved data" + " ", "Pass", "Y");
		log.info("completed executing the method:: bidderRegristrationForm");
	} catch (Exception e) {
		log.fatal("Unable to display bidder registration form" + e.getMessage());
		pdfResultReport.addStepDetails("bidderRegristrationForm",
				"Bidder Registration form should be displayed with previously saved data",
				"Unable to display bidder registration form" + e.getMessage(), "Fail", "N");
		Assert.fail("Failed Due to " + e.getMessage());
	}
}
public void SubmitPreferredUserName() throws Throwable {
	try {
	log.info("started executing the method:: SubmitPreferredUserName");
	JSClick(tendercreationlocators.btnSubmit, "btnSubmit");
	etendercomponentobj.waitForSpinnerToDisappear();
	waitForObj(5000);
	String UserName = "ABC".concat(String.valueOf(getrandomInterger(1000, 10000)));
	JSClick(tendercreationlocators.UserName, "UserName");
	set(tendercreationlocators.UserName, UserName, "UserName");
	waitForObj(3000);
	JSClick(tendercreationlocators.AcceptDisclaimer, "AcceptDisclaimer");
	waitForObj(3000);
	JSClick(tendercreationlocators.SubmitUserName, "SubmitUserName");
	waitForElementToBeVisible(tendercreationlocators.bidderRegsuccessMesg);
	highlight(tendercreationlocators.bidderRegsuccessMesg);
	pdfResultReport.addStepDetails("SubmitPreferredUserName", "Should able to submit Preferred UserName",
	"Successfully submitted Preferred UserName " + " ", "Pass", "Y");
	waitForObj(2000);
	JSClick(tendercreationlocators.alertTabOk, "alertTabOk");
	waitForObj(5000);
	log.info("completed executing the method:: SubmitPreferredUserName");
	} catch (Exception e) {
	log.fatal("Unable to submit Preferred UserName" + e.getMessage());
	pdfResultReport.addStepDetails("SubmitPreferredUserName", "Should able to submit Preferred UserName",
	"Unable to submit Preferred UserName" + e.getMessage(), "Fail", "N");
	Assert.fail("Failed Due to " + e.getMessage());
	}
	}
public void passValueToMasterOrganizationList() throws Throwable {
	try {
		log.info("started executing the method:: passValueToMasterOrganizationList");
		JSClick(tendercreationlocators.TabOrganization, "TabOrganization");
		etendercomponentobj.waitForSpinnerToDisappear();
		JSClick(tendercreationlocators.MasterOrgList, "MasterOrgList");
		set(tendercreationlocators.MasterOrgList, "Apple", "MasterOrgList");
		waitForObj(5000);
		pdfResultReport.addStepDetails("passValueToMasterOrganizationList", "Searched result should be display properly",
				"Successfully displayed searched result" + " ", "Pass", "Y");
		log.info("completed executing the method:: passValueToMasterOrganizationList");
	} catch (Exception e) {
		log.fatal("Unable to display searched result" + e.getMessage());
		pdfResultReport.addStepDetails("passValueToMasterOrganizationList", "Searched result should be display properly",
				"Unable to display searched result" + e.getMessage(), "Fail", "N");
		Assert.fail("Failed Due to " + e.getMessage());
	}
}
public void fillUpBidderTemplateGroup() throws Throwable {
	try {
		log.info("started executing the method:: fillUpBidderTemplateGroup");
		JSClick(tendercreationlocators.TabOrganization, "TabOrganization");
		etendercomponentobj.waitForSpinnerToDisappear();
		pdfResultReport.addStepDetails("fillUpBidderTemplateGroup", "Should be navigate to Organization Tab",
				"Successfully navigated to Organization Tab" + " ", "Pass", "Y");
		JSClick(tendercreationlocators.MasterOrgList, "MasterOrgList");
		set(tendercreationlocators.MasterOrgList, "CHIPS", "MasterOrgList");
		waitForObj(3000);
		pdfResultReport.addStepDetails("fillUpBidderTemplateGroup", "Should pass Value to Master Organization List",
				"Successfully passed Value to Master Organization List" + " ", "Pass", "Y");
		JSClick(tendercreationlocators.addorg, "addorg");
		waitForObj(5000);
		pdfResultReport.addStepDetails("fillUpBidderTemplateGroup", "Should be click on apply",
				"Successfully clicked on apply" + " ", "Pass", "Y");
		JSClick(tendercreationlocators.editTemplate, "editTemplate");
		etendercomponentobj.waitForSpinnerToDisappear();
		waitForElementToBeVisible(tendercreationlocators.GSTIN);
		select(tendercreationlocators.GSTIN,"No");
		waitForObj(3000);
		set(tendercreationlocators.GSTINUpload, System.getProperty("user.dir") + "\\MediaFiles\\report.pdf",
				"fileName");
		etendercomponentobj.waitForSpinnerToDisappear();
		pdfResultReport.addStepDetails("fillUpBidderTemplateGroup", "Should be able to fill up bidder template group",
				"Successfully filled up bidder template group" + " ", "Pass", "Y");
		JSClick(tendercreationlocators.OrganizationItemCategory, "OrganizationItemCategory");
		etendercomponentobj.waitForSpinnerToDisappear();
		JSClick(tendercreationlocators.saveOrgTabData, "saveOrgTabData");
		waitForElementToBeVisible(tendercreationlocators.OrgDatasuccessMesg);
		highlight(tendercreationlocators.OrgDatasuccessMesg);
		pdfResultReport.addStepDetails("fillUpBidderTemplateGroup", "Organization Data should be Save Successfully",
		"Successfully saved Organization Data" + " ", "Pass", "Y");
		waitForObj(2000);
		JSClick(tendercreationlocators.alertTabOk, "alertTabOk");
		waitForObj(5000);
		JSClick(tendercreationlocators.closeOrgDetail, "closeOrgDetail");
		waitForObj(5000);
		pdfResultReport.addStepDetails("fillUpBidderTemplateGroup", "Should be able to fill up bidder template group",
				"Successfully filled up bidder template group" + " ", "Pass", "Y");
		log.info("completed executing the method:: fillUpBidderTemplateGroup");
	} catch (Exception e) {
		log.fatal("Unable to fill up bidder template group" + e.getMessage());
		pdfResultReport.addStepDetails("fillUpBidderTemplateGroup", "Should be able to fill up bidder template group",
				"Unable to fill up bidder template group" + e.getMessage(), "Fail", "N");
		Assert.fail("Failed Due to " + e.getMessage());
	}
}

				 
}
