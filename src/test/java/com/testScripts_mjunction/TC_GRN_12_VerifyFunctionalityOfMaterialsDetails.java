package com.testScripts_mjunction;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;

import com.components.PostTenderComponent;
import com.components.PreConditionPo_SanctionCreation;
import com.components.eTenderComponent;
import com.components.ASN_GRNComponent;

public class TC_GRN_12_VerifyFunctionalityOfMaterialsDetails extends BaseClass_Web {

	PreConditionPo_SanctionCreation preConditionPo = new PreConditionPo_SanctionCreation(pdfResultReport);
	public eTenderComponent etendercomponentobj = new eTenderComponent(pdfResultReport);
	public PostTenderComponent posttendercomponentobj = new PostTenderComponent(pdfResultReport);
	public ASN_GRNComponent ASN_GRNComponent = new ASN_GRNComponent(pdfResultReport);

	/**
	 * TestScript Environment Details
	 * 
	 * @throws Exception
	 */
	public void initializeRepository() throws Exception {
		reportDetails.put("Test Script Name", this.getClass().getSimpleName());
		reportDetails.put("Test Script MyWorksshop Document ID", "Doc1234567");
		reportDetails.put("Test Script Revision No", "1");
		reportDetails.put("Test Author Name", "Bhowmick");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}

	@Parameters("TestcaseNo")
	@Test(description = "TC_GRN_12_VerifyFunctionalityOfMaterialsDetails")
	public void VerifyFunctionalityOfMaterialsDetails(String no) throws Throwable {
		System.out.println("Entered in the Test method..................");
		try {
			pdfResultReport.readTestDataFile(System.getProperty("user.dir").replace("\\", "/")
					+ "/Resources/TenderCreation_CreateNewRFQ_TestData_jit.xls", no);
		} catch (Exception e) {
			System.out.println("Unable to read the data from excel file");
		}

		String ShipmentTrackingNum=eTenderComponent.getDataFromPropertiesFile("ASNShippingTracking");
		
		etendercomponentobj.openURL();

		ASN_GRNComponent.GRN_Creator_Login();

		ASN_GRNComponent.navigateToApprovedASNListPage();
		
		ASN_GRNComponent.enterASNShipmentAndSelectVendorName("TCS",ShipmentTrackingNum);
		
		ASN_GRNComponent.clickApprovedAsnListActionMenu(ShipmentTrackingNum);
		
		ASN_GRNComponent.createGrn(ShipmentTrackingNum);
		
		ASN_GRNComponent.verifyAsnQtyValueInGrnCreationPage(1);
		
		ASN_GRNComponent.enterRejectedQty(1,"1.00");
		
		ASN_GRNComponent.enterExcessQty(1,"5.00");
		
		ASN_GRNComponent.enterDamageQty(1,"1.00");
		
		ASN_GRNComponent.getAcceptedQtyValueAndVerify(1,"18.00");

		ASN_GRNComponent.saveGrnDetails();
		
		ASN_GRNComponent.submitGrnDetails();
		
		ASN_GRNComponent.clickGrnSubmitComfirmationYes();
		
		ASN_GRNComponent.SelectTabCreated();
		
		ASN_GRNComponent.enterShippementTrackingNoInGrnListSerachBox();
		
		ASN_GRNComponent.clickGrnListActionMenu(ShipmentTrackingNum);
		
		ASN_GRNComponent.ViewGRN(ShipmentTrackingNum);
		
		ASN_GRNComponent.VerifyMaterialsDetailsGRN();
		
		etendercomponentobj.tenderLogout();
		

	}

}
