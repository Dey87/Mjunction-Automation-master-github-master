package com.testScripts_mjunction;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.components.ASN_GRNComponent;
import com.components.PostTenderComponent;
import com.components.PreConditionPo_SanctionCreation;
import com.components.eTenderComponent;

public class TC_ASN_06_CreateASNbyInputingAllData extends BaseClass_Web {

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
		reportDetails.put("Test Script Revision No", "6");
		reportDetails.put("Test Author Name", "Bhowmick");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}

	@Parameters("TestcaseNo")
	@Test(description = "TC_ASN_06: Verify In My Information Tab The Supplier Fileds Will Be Auto Populaed")
	public void ValidateMyInFormationTabFieldsAutoPopulated(String no) throws Throwable {
		System.out.println("Entered in the Test method..................");
		try {
			pdfResultReport.readTestDataFile(System.getProperty("user.dir").replace("\\", "/")
					+ "/Resources/TenderCreation_CreateNewRFQ_TestData_jit.xls", no);
		} catch (Exception e) {
			System.out.println("Unable to read the data from excel file");
		}

		etendercomponentobj.openURL();

		etendercomponentobj.bidder_02_Login();

		ASN_GRNComponent.SelectASNModule();

		ASN_GRNComponent.CreateASN();

		ASN_GRNComponent.SaveASN();

		ASN_GRNComponent.verifyMyInformationTabFieldsAutoPopulated();

		ASN_GRNComponent.ASNListPage();

		ASN_GRNComponent.SearchPoInASNList();

		ASN_GRNComponent.VerifyDraftStatus();

		etendercomponentobj.tenderLogout();

	}

}
