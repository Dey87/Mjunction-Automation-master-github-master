package com.testScripts_mjunction;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseClasses.BaseClass_Web;
import com.components.PostTenderComponent;
import com.components.eTenderComponent;

public class TC_Sanction_37_ValidateDOPListPageElementsTest extends BaseClass_Web {
	public PostTenderComponent posttendercomponentobj = new PostTenderComponent(pdfResultReport);

	public eTenderComponent etendercomponentobj = new eTenderComponent(pdfResultReport);

	/**
	 * TestScript Environment Details
	 * 
	 * @throws Exception
	 */
	public void initializeRepository() throws Exception {
		reportDetails.put("Test Script Name", this.getClass().getSimpleName());
		reportDetails.put("Test Script MyWorksshop Document ID", "Doc1234567");
		reportDetails.put("Test Script Revision No", "37");
		reportDetails.put("Test Author Name", "Venkatesh Jujjuru");
		reportDetails.put("Test Script Type", "Automated Testing");
		reportDetails.put("Requirement Document ID of System", "Doc1234567");
		reportDetails.put("Requirement ID", "US2202");
	}

	/**
	 * 
	 * @author E005672
	 * @param no
	 * @throws Throwable
	 */
	@Parameters("TestcaseNo")
	@Test(description = "Validate DOPListPage Elements")
	public void validateDOpListPageColumnsNames(String no) throws Throwable {
		System.out.println("Entered in the Test method..................");
		try {
			pdfResultReport.readTestDataFile(System.getProperty("user.dir").replace("\\", "/")
					+ "/Resources/TenderCreation_CreateNewRFQ_TestData_venkateshNew.xls", no);
		} catch (Exception e) {
			System.out.println("Unable to read the data from excel file");
		}

		initializeRepository();

		deleteBrowserCookies();

		etendercomponentobj.openURL();

		posttendercomponentobj.super_Admin_Login();
		
		posttendercomponentobj.navigateTo_DOPList("DopName_San_2819");

		posttendercomponentobj.verifyDopListpageColumnNames();

		etendercomponentobj.tenderLogout();

		posttendercomponentobj.closeBrowser();

	}
}
