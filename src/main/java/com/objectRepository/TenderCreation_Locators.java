package com.objectRepository;

import org.openqa.selenium.By;

public class TenderCreation_Locators {

	public By login = By.xpath("//img[contains(@src,'icon-login')]");
	public By userName = By.xpath("//input[@name='userCode'][@type='text']");
	public By password = By.xpath("//input[@placeholder='Password']");
	public By Captcha_Login = By.xpath("//input[@name='captcha' and @ng-model = 'login.captcha']");
	public By okButton = By.xpath("//button[@id='login']");
	public By dashboardIcon = By.xpath("//i[@class='fa fa-laptop title_icon']");
	public By tendersIcon = By.xpath("//span[contains(text(),'Tender(s)')]//parent::a//i[@class='fa fa-tasks']");
	public By tenderList = By.xpath("//a[contains(@ng-click,'tender/listTender')]");
	public By tendercreationPlusicon = By.xpath("//button[@data-original-title='Tender Creation']");
	public By templateGroupdropdown = By.xpath("//select[@name='templateGroup']");
	public By bitPartdropdown = By.xpath("//select[@name='bidPartNo']");
	public By bitPartRadio = By.xpath("//input[@name='bidPartRadio']");
	public By tenderReferenceNumber = By.xpath("//input[@name='referenceNo']");
	public By procurementCategory = By.xpath("//select[@name='procat']");
	public By tenderCurrency = By.xpath("//select[@name='currency']");
	public By probabaleAmountContract = By.xpath("//input[@name='pacamt']");
	public By biddingCurrency = By.xpath("//select[@name='bcurrency']");
	public By tenderType = By.xpath("//select[@name='tenderType']");
	public By Min_bid_no = By.xpath("//input[@name = 'minbidno']");
	public By detailedDescription = By.xpath("//textarea[@name='description']");
	public By savebutton = By.xpath("//a/button[@data-original-title='Save']/i");
	public By editTenderTitle = By.xpath("//span[text()=' Edit Tender']");
	public By otherAttachements = By.xpath("//a[text()='Other Attachments']");
	public By addtenderattachmenticon = By.xpath("//button[@data-original-title='Add']");
	public By supportingdocument = By.xpath("//input[@placeholder='Supporting Document']");
	public By buyerattachemntOK = By.xpath("//button[@id='add-author3']");
	public By projectDetailsTab = By.xpath("//a[text()='Project Details']");
	public By projectName = By.xpath("//input[@placeholder='Project Name']");
	public By projectLocation = By.xpath("//input[@placeholder='Project Location']");
	public By product = By.xpath("//input[@placeholder='Product']");
	public By contactPerson = By.xpath("//input[@placeholder='Contact Person']");
	public By attachementsTab = By.xpath("//a[text()='Attachments']");
	public By attachment_subtab = By.xpath("//a[text()='Attachment']");
	public By fileName = By.xpath("//input[@file-model='tenderAttachments']");
	public By openattachementsTab = By
			.xpath("//button[@data-original-title='Add'][@ng-click='openAttachmentModal(templateIndex)']");
	public By label = By.xpath("//input[@placeholder='Label']");
	public By uploadatachementIcon = By.xpath("//input[@file-model='tenderAttachments']//parent::a");
	public By VisibleTo = By.xpath("//select[@name='visible']");
	public By attachmentOKbutton = By.xpath("//button[@id='add-authorRow']");
	public By dateschedule = By.xpath("//a[text()='Date Schedule']");
	public By bidsubmissionStartDate = By.xpath("//input[@id='rfqdate.bid_start_date.0']");
	public By bidsubmissionDueDate = By.xpath("//input[@id='rfqdate.bid_end_date.0']");
	public By bidsubmissionOpenDate = By.xpath("//input[@id='rfqdate.bid_open_date.0']");
	public By BOQOptionalTab = By.xpath("//a[text()='BOQ (Optional)']");
	public By addNonSORItem = By.xpath("//button[@data-original-title='Add NON SOR Item']");
	public By BOQOptionItemCode = By.xpath("//input[@id='ri_boq_optional.item_code.0']");
	public By BOQOptionItemDescription = By.xpath("//input[@id='ri_boq_optional.item_name.0']");
	public By BOQOptionUOM = By.xpath("//select[@id='ri_boq_optional.uom.0']");
	public By projectquantity = By.xpath("//input[@placeholder='Project Quantity']");
	public By preferedmake = By.xpath("//input[@placeholder='Prefered Make']");
	public By budgetedrate = By.xpath("//input[@placeholder='Budgeted Rate']");
	public By remarks = By.xpath("//textarea[@placeholder='Remarks']");
	public By termsConditionsTab = By.xpath("//a[text()='Terms and Conditions']");
	public By termsconditionsAdd = By.xpath("//button[@ng-click='addRowForDynamicTabularTemplate(templateIndex)']");
	public By termsconditionClause = By.xpath("//input[@placeholder='Clause']");
	public By termsconditionDetails_WithOptionalItemsAndQtyEditable = By.xpath("//textarea[@name='details']");

	public By termsconditionDetails = By.xpath("//input[@name='Details']");
	public By technical = By.xpath("//a[text()='Technical']");
	public By technicalAdd = By
			.xpath("//div[@id='tech_sourav']//button[@ng-click='addRowForDynamicTabularTemplate(templateIndex)']");
	public By technicalAdd_WithOptionalItemsAndQtyEditable = By
			.xpath("//div[@id='rh_techcompl']//button[@ng-click='addRowForDynamicTabularTemplate(templateIndex)']");
	public By technicalclause = By.xpath("//input[@id='rh_techcompl.clause.0']");
	public By BOQmandatoryTab = By.xpath("//a[text()='BOQ (Mandatory)']");
	public By addnonSORBOQMandatoryTab = By.xpath(
			"//a[@data-target='#myModalalRfqNonSorItem-ri_boq']//button[@data-original-title='Add NON SOR Item']");
	public By BOQMadatoryItemCode = By.xpath("//input[@id='ri_boq.item_code.0']");
	public By BOQMadatoryItemdescription = By.xpath("//input[@id='ri_boq.item_name.0']");
	public By submitbutton = By.xpath("//button[@data-original-title='Submit']");
	public By technicaldetails = By.xpath("//input[@id='tech_sourav.Details.0']");
	public By boqSummary = By.xpath("//a[text()='BOQ Summary']");
	public By next = By.xpath("//a[text()='Next']");
	public By commercial_biddetails = By.xpath("//a[contains(@href,'qh_commercial_v1')]");
	public By boqSummaryItemCode = By.xpath("//input[@name='item_code']");
	public By boqSummaryItemDescription = By.xpath("//input[@name='item_name']");
	public By boqSummaryQuantity = By.xpath("//input[@name='item_qty']");
	public By boqSummaryUom = By.xpath("//select[@name='uom']");
	public By boqSummarySorRate = By.xpath("//input[@name='sor_rate']");
	public By boqSummaryMandatoryItem = By.xpath("//select[@name='mandatory_item']");
	public By Userdefined = By.xpath("//input[@id='appYes'][@value='U']");
	public By sectionWiseComments = By.xpath("//input[@value='Y'][@required='required']");
	// public By comments = By.xpath("//textarea[@class='form-control marg_tp15
	// ng-pristine ng-untouched ng-empty ng-invalid ng-invalid-required']");
	public By sendForApproval = By.xpath("//button[contains(@ng-click,'sendForApproval(approvalObj.workflowType)')]");
	public By physicalDocSubmissionEndDate = By.xpath("//input[@id='rfqdate.doc_sub_date.0']");
	public By previousTenderNo = By.xpath("//select[@ng-model='generalInfo.parentid']");
	public By preferredCountryofOrigin = By.xpath("//input[@name='Preferred_C_o_O']");
	public By projectBrief = By.xpath("//input[@name='Project_Brief']");
	public By instructionToTheSuppliers = By.xpath("//input[@name='Instruction_to_the_S']");
	public By technicalclause_tender2creationbydutta = By.xpath("//input[@id='tech_sourav.Clause.0']");
	public By BOQMadatoryItemCode_tender2creationbydutta = By.xpath("//input[@id='boq.item_code.0']");
	public By BOQMadatoryItemdescription_tender2creationbydutta = By.xpath("//input[@id='boq.item_name.0']");

	public By previewButton = By.xpath("//button[@data-original-title='Preview']");
	public By previewAllTittle = By.xpath("//*[contains(text(),' Preview All')]	");
	public By previewAllpage = By.xpath("//div[@id='myModalprev_bid' and contains(@class,'fade in')]");
	public By previewoteOpenTender = By.xpath("//label[contains(text(),'OTE-Open Tender')]");
	public By previewAllOkButton = By.xpath("//*[contains(text(),' Ok')]");
	public By corrigendumSendForApprovalNotRequired = By
			.xpath("//input[@id='appNo'][@ng-model='approvalObj.workflowType']");
	public By sendForApprovalSubmit = By.xpath("//button[@id='cm' and contains(text(),'Submit')]");
	public By sendForApprovalSubmit_CancelPO = By.xpath("(//button[@id='cm' and contains(text(),'Submit')])[1]");
	public By sendForApprovalSubmit_ReleasePO = By.xpath("(//button[@id='cm' and contains(text(),'Submit')])[1]");
	public By tenderReferenceNoLocator = By
			.xpath("//div[contains(@id,'tenderApprovalModal')]//form[contains(@class,'ng-valid')]//td[text()='System Tender No.']//parent::tr//td[2]");
	public By tenderCreation = By.xpath("//span[text()='Tender Creation']");
	public By createNewRfq = By.xpath("//a[contains(string(),'Create New RFQ')]");
	public By tenderListSearch = By.xpath("//input[contains(@ng-model,'tenderFilter')]");
	public By tenderListStatus = By.xpath("//table[@id='myTablebyrTl00']/tbody/tr[1]/td[7]/span");

	// Vamshi
	// Send for Approval
	public By Add = By.xpath("(//button[@id='addApprovertc'])[2]");
	public By userselection = By.xpath("(//input[@list='approvaerList'])[2]");
	public By approvalType = By
			.xpath("//table[@id='approver']//tr[2]//select[@id='filterTest']/option[text()='SEQUENTIAL']");
	public By comments = By.xpath(
			"//div[@id='menusndApprovl'][contains(@class,'active')]//following-sibling::div//textarea[@name='approvalComment']");
	// Approval Page Validation
	public By logoutOption = By.xpath("//div[@id='user-profile-img-header']");
	public By logout = By.xpath("//a[@data-target='#logoutConfirm']");
	public By logoutConfirmation = By.xpath("//button[@onclick='logout();']");
	// Tender Approval Validation
	public By workFlow = By.xpath("//i[@class='fa fa-building-o notific']");
	public By pending = By.xpath("//a[contains(text(),'Pending')]");
	public By search = By.xpath("//input[@placeholder='Type any keyword']");
	public By documentId = By.xpath("(//td[@class='ng-binding'])[2]");
	public By workFlowStatus = By.xpath("//table[@id='myTable']/tbody/tr/td[6]");
	public By details = By.xpath("//a[contains(text(),'Details')]");
	// Details page of Tender Approval Validation
	// public By approverComment_GeneralInfromation =
	// By.xpath("//a[contains(text(),'Details')]");
	public By dateScheduleTab = By.xpath("//a[text()='Date Schedule']");
	public By approverComment = By.xpath("//strong[text()='Approver Comment']");
	public By projectDetailsTemplate = By.xpath("//a[text()='Project Details']");
	public By termsAndConditionsTemplate = By.xpath("//a[text()='Terms and Conditions']");
	public By technicalTab = By.xpath("//a[text()='Technical']");
	public By boqMandatoryTab = By.xpath("//a[text()='BOQ (Mandatory)']");
	// tenderApproval_ProvidingComment
	public By commentBoqMandatory = By.xpath("//div[@id='boq']//textarea[@ng-model='template.tabWiseApproverComment']");
	public By saveTabComment_BoqMandatory = By
			.xpath("//button[contains(text(),'Save Tab Comment')][@id='saveComment6']");
	public By alertTab = By.xpath("//div[@class='bootbox-body']");
	public By alertTabOk = By.xpath("//button[@data-bb-handler='ok']");
	public By commentTechnical = By
			.xpath("//div[@id='tech_sourav']//textarea[@ng-model='template.tabWiseApproverComment']");
	public By saveTabComment_Technical = By
			.xpath("//div[@id='tech_sourav']//button[contains(text(),'Save Tab Comment')]");
	public By commentTermsAndConditions = By
			.xpath("//div[@id='tandc']//textarea[@ng-model='template.tabWiseApproverComment']");
	public By saveTabComment_TermsAndConditions = By
			.xpath("//div[@id='tandc']//button[contains(text(),'Save Tab Comment')]");
	public By commentProjectDetails = By
			.xpath("//div[@id='proj_details']//textarea[@ng-model='template.tabWiseApproverComment']");
	public By saveTabComment_ProjectDetails = By
			.xpath("//button[contains(text(),'Save Tab Comment')][@id='saveComment7']");
	public By commentDateSchedule = By
			.xpath("//div[@id='rfqdate']//textarea[@ng-model='template.tabWiseApproverComment']");
	public By saveTabComment_DateSchedule = By
			.xpath("//button[contains(text(),'Save Tab Comment')][@id='saveComment2']");
	public By generalInformationTab = By.xpath("//a[text()='General Information']");
	// public By approverComment = By.xpath("//strong[text()='Approver
	// Comment']");
	public By comment = By.xpath("//div[@id='collapse1']//textarea[@ng-model='approvalComment.approvalComment']");
	public By approve = By.xpath("//button[@id='savePayment1_0']");
	public By tenderListKeyword = By.xpath("//input[@placeholder='Type any keyword']");
	public By tenderCreatedStatus = By.xpath("//table[@id='myTablebyrTl00']/tbody/tr[1]/td[7]");
	public By addBidders = By.xpath("//button[@type='button'][@ng-click='addVendor(generalInfo.tendertypeid)']");
	public By bidderCompany_TechMahindra = By.xpath("//table[@id='vendorLst']/tbody/tr[2]/td[1]");
	public By bidderCompany_TCS = By.xpath("//table[@id='vendorLst']/tbody/tr[3]/td[1]");
	public By bidderCompany_CTS = By.xpath("//table[@id='vendorLst']/tbody/tr[ td[2] and td/span[text()='CTS']]/td[1]");
	public By addBidderCompany = By.xpath("//button[@id='addVendor']");
	public By technicalclause_TenderTwoCreationByDutta = By.xpath("//input[@id='tech_sourav.Clause.0']");

	public By approveCommentReview = By.xpath("//button[contains(text(),'Review')]");
	public By reverseBackToApprover = By.xpath("//button[text()=' Reverse back to approver']");
	public By minApprover1 = By.xpath("//table[@id='approver']/tbody/tr[1]/td[6]//input");
	public By minApprover2 = By.xpath("//table[@id='approver']/tbody/tr[2]/td[6]//input");
	public By coordinatorFlag = By.xpath("//table[@id='approver']/tbody/tr[1]/td[5]//input");
	public By cancelUser1 = By.xpath("//table[@id='approver']/tbody/tr[1]/td[7]/button[@ng-click='cancelUser(row)']");
	// public By cancelUser2 =
	// By.xpath("//table[@id='approver']/tbody/tr[2]/td[7]/button[@ng-click='cancelUser(row)']");
	public By userAdd = By.xpath("//table[@id='approver']/thead/tr[1]/th[7]/div/button[@id='addApprovertc']");
	public By user1 = By.xpath("//table[@id='approver']/tbody/tr[1]/td[2]/input");
	public By user2 = By.xpath("//table[@id='approver']/tbody/tr[2]/td[2]/input");
	public By approverType1 = By.xpath("//table[@id='approver']/tbody/tr[1]/td[3]/label/select[@id='filterTest']");
	public By approverType2 = By.xpath("//table[@id='approver']/tbody/tr[2]/td[3]/label/select[@id='filterTest']");
	// venkatesh

	public By addSorItemBy = By.xpath("//button[@data-original-title='Add SOR Item']");
	public By selectSorItemMaterialBy = By.xpath(
			"//*[@id='collapse1']//child::div[1]/select[@name='itemSelectBox'] [(//select[@name='itemSelectBox'])[1]]");
	public By selectSorItemLocationBy = By.xpath(
			"//*[@id='collapse1']//child::div[2]/select[@name='itemSelectBox'] [(//select[@name='itemSelectBox'])[2]]");
	public By selectSorItemMaterialWeightBy = By.xpath(
			"//*[@id='collapse1']//child::div[3]/select[@name='itemSelectBox'] [(//select[@name='itemSelectBox'])[3]]");
	public By sorItemSearchBy = By.xpath("//button[@data-original-title='Search']");
	public By newBy = By.xpath("//table[@id='rfqpopupTAble']/tbody/tr/td[position()=1 or position ()=6]/child::*[1]");

	public By sorItemCodeCheckBoxBy = By.xpath("//table[@id='rfqpopupTAble']/tbody/tr/td/input[@type='checkbox']");
	public By sorItemQuantityBy = By.xpath("//table[@id='rfqpopupTAble']/tbody/tr/td/input[@ng-model='item.quantity']");
	public By sorItemSaveBy = By.xpath("//button[@id='saveRFQItemToTab']");
	public By noOfRecordsBy = By.xpath("//*[contains(text(),'More...')]/parent::div/child::span");
	public By mandotatoryItemSelectBoxBy = By.xpath("//select[@name='mandatory_item']");
	public By confirmationOkBy = By.xpath("//button[text()='OK']");
	public By previewPopOkBy = By.xpath("//div[@id='myModalprev']//button[contains(text(),'Ok')]");

	public By addSORItem = By.xpath("//button[@data-original-title='Add SOR Item']");
	public By selectSORItemMaterialBy = By.xpath(
			"//*[@id='collapse1']//child::div[1]/select[@name='itemSelectBox'] [(//select[@name='itemSelectBox'])[1]]");
	public By selectSORItemLocationBy = By.xpath(
			"//*[@id='collapse1']//child::div[2]/select[@name='itemSelectBox'] [(//select[@name='itemSelectBox'])[2]]");
	public By selectSORItemMaterialWeightBy = By.xpath(
			"//*[@id='collapse1']//child::div[3]/select[@name='itemSelectBox'] [(//select[@name='itemSelectBox'])[3]]");
	public By addSORItemSearch = By.xpath("//button[@data-original-title='Search']");
	public By addSORItemCodeCheckBoxBy = By.xpath("//table[@id='rfqpopupTAble']/tbody/tr/td/input[@type='checkbox']");
	public By addSORItemQuantity = By
			.xpath("//table[@id='rfqpopupTAble']/tbody/tr/td/input[@ng-model='item.quantity'] ");
	public By addSORItemSave = By.xpath("//button[@id='saveRFQItemToTab']");
	public By action = By.xpath("//button[contains(@data-toggle,'dropdown')]");
	public By corrigendumLink = By.xpath("//a[contains(text(),'Corrigendum')]");
	public By corrigendumReferenceNumber = By.xpath("//input[@name='corriRefNo']");
	public By corrigendumDescription = By.xpath("//textarea[@name='corriDesc']");
	public By corrigendumSelect = By.xpath("//span[text()='Nothing selected']");
	public By corrigendumSelect_DateSchedule = By.xpath("//span[text()='Date Schedule']");
	public By corrigendumXButton = By.xpath("//button[contains(@data-original-title,'Close')]");
	public By corrigendumDiscardButton = By.xpath("//button[contains(@data-original-title,'Discard')]");
	public By corrigendumAlertTabOk = By.xpath("//button[@data-bb-handler='confirm']");
	public By userSelectionDropdown2 = By.xpath("(//table[@id='approver']/tbody/tr[2]/td[2]");
	public By noApproverAdded = By.xpath("//table[@id='approver']/tfoot/tr/td/strong");
	public By closeSelection = By.xpath("//button[@ng-click='cancelUser(row)']");
	public By corrigendumTab = By.xpath("//a[contains(text(),'Corrigendum')]");
	public By dateScheduleComment = By.xpath("//textarea[@ng-model='template.tabWiseApproverComment']");
	public By corrigendumStatus = By
			.xpath("//table[@id='myTablebyrTl00']/tbody/tr[1]/td[6]/a[contains(text(), 'Yes')]");
	public By corrigendumStatusNo = By
			.xpath("//table[@id='myTablebyrTl00']/tbody/tr[1]/td[6]");
	public By corrigendumStatusViewAll = By.xpath("//h3[contains(text(),'View All')]");
	public By corrigendumNumber = By.xpath("//a[@ng-click='corrigendumReport(corrigendum.corrId)']");
	public By corrigendumHistory = By.xpath("//a[@name='deleteWork']");
	public By corrigendumNewDateSchedule = By.xpath("//div[contains(text(),'new Date Schedule')]");
	public By corrigendumOldDateSchedule = By.xpath("//div[contains(text(),'old Date Schedule')]");
	public By corrigendumHistoryApprovalSection = By.xpath(
			"//table[@class='table table-striped table-advance table-bordered']/tbody/tr/th[text()='Approval Section']");
	public By corrigendumHistoryTenderNo = By
			.xpath("//table[@class='table table-advance borderless']/tbody/tr[1]/td[4]");
	public By corrigendumHistoryCorrigendumNumber = By
			.xpath("//table[@class='table table-advance borderless']/tbody/tr[1]/td[2]");

	//added for static test script(21/12/2020)
	public By Userdefined_tender = By.xpath("//div[@id='tenderApprovalModal']//div[contains(@class,'row midSecpop')]//div[2]//input[@id = 'appYes']");
	public By sectionWiseComments_tender = By.xpath("//div[@id='tenderApprovalModal']//div[contains(@class,'row midSecpop')]//input[@id='secCmntYes' and @value = 'Y']");
	public By cancelUser1_tender = By.xpath("//table[@id='approver']/tbody/tr[1]/td[7]/button[@ng-click='cancelUser(row)']");
	public By userAdd_tender = By.xpath("//table[@id='approver']/thead/tr[1]/th[7]/div/button[@id='addApprovertc']");
	public By user1_tender = By.xpath("//table[@id='approver']/tbody/tr[1]/td[2]/input");
	public By user2_tender = By.xpath("//table[@id='approver']/tbody/tr[2]/td[2]/input");
	public By approverType1_p_tender = By.xpath("//tr[contains(@ng-repeat,'row in approval.approvalDetails track by $index')]//select[@id='filterTest']");
	public By approverType2_p_tender = By.xpath("(//tr[contains(@ng-repeat,'row in approval.approvalDetails track by $index')]//select[@id='filterTest'])[2]");
	public By comments_tender = By.xpath("//div[@id='menusndApprovl'][contains(@class,'active')]//following-sibling::div//textarea[@name='approvalComment']");
	public By sendForApproval_tender = By.xpath("//div[@id='tenderApprovalModal']//button[@id='dyantc']");
	public By ActionButton_approver_tender = By.xpath("//button[@id='menu2']");
	public By Approvercomment_tender = By.xpath("//div[@id='collapse1']//textarea[@ng-model='approvalWorkFlow.approvalDetails[0].overallComment']");
	public By Approvebutton_tender = By.xpath("//button[normalize-space()='Approve']");
	public By Tenderlink_approver_tender = By.xpath("//a[normalize-space()='Tender']");
	public By CloseWF_tender = By.xpath("//button[normalize-space()='Close Workflow']");
	public By Conf_YesbuttonWF_tender = By.xpath("//button[@class='btn btn-primary'][normalize-space()='Yes']");
	public By Reviewbutton_tender = By.xpath("//button[normalize-space()='Review']");
	public By Conf_Ok_button = By.xpath("//button[normalize-space()='OK']");
	public By AlertYesbtn_Corrigendum = By.xpath("//button[normalize-space()='Yes']//i[@class='fa fa-check']");
	public By BidNo_bidsubmission = By.xpath("//tr[contains(@ng-repeat,\"bid in submittedBidList\")]/td[4]");
	public By BidNo_Draftbid_bidsubmission = By.xpath("//tr[contains(@ng-repeat,\"bid in draftBidList\")]/td[4]");
	public By BidNo_Withdrawbid_bidsubmission = By.xpath("//tr[contains(@ng-repeat,\"bid in withdrawBidList\")]/td[4]");
	public By Geninfotab_bidsubmission = By.xpath("//a[@id='quot_gen_info']");
	public By Quot_ref_code_afterRebidPreview = By.xpath("(//label[contains(text(),'EditedQuotRefCode')])[1]");
	public By Livetab_bidsubmission = By.xpath("//a[@id = 'liveTab']");
	public By Alertmsg_bidsubmission = By.xpath("//div[@class='bootbox-body']");
	public By Alertmsg_OK_btn_bidsubmission = By.xpath("//button[normalize-space()='OK']");
	public By ApproverSelectYES_Evaluation = By.xpath("(//*[@type='radio' and @value='Y'])[2]");
	public By Workflowtype_Evaluation = By.xpath("//input[@id='appYes' and @value='U']");
	public By AddApprover_Evaluation = By.xpath("//button[@id = 'addApprovertc']");
	public By OpeningUser1_Evaluation = By.xpath("//table[@id='approver']/tbody/tr[1]/td[2]/input");
	public By OpeningApprovalType1_Evaluation = By.xpath("//tr[contains(@ng-repeat,'row in global.evaluationSettings.approval.approvalDetails  track by $index')]//select[@id='filterTest']");
	//public By OpeningApprovalWFType_Evaluation = By.xpath("//td[normalize-space()='Pending for opening Approval (Cover 1)']");
	public By OpeningApprovalWFType_Evaluation(String coverNo) {
		return By.xpath("//td[normalize-space()='Pending for opening Approval ("+coverNo+")']");
	}
	public By OpeningApprovalWFStatus_Evaluation = By.xpath("//td[normalize-space()='WIP']");
	public By OpeningApprovalComment_Evaluation = By.xpath("//label[text()='Approver Comment']//following-sibling::textarea");
	public By OpeningApprovalAlert_Evaluation = By.xpath("//div[@class='bootbox-body']");
	public By OpeningApprovalAlertConfirmbtn_Evaluation = By.xpath("//button[normalize-space()='Confirm']");
	public By OpeningApprovalDetailsbtn_Evaluation = By.xpath("//div[@id='tenderOpening']//a[contains(text(),'Details')]");
	public By BidEvaluationtab_Evaluation = By.xpath("//a[normalize-space()='Bid Evaluation']");
	public By WorkflowtypeEvalapp_Evaluation = By.xpath("//div[@id='menu1Bid']//input[@id='appYes' and @value='U']");
	public By AddApproverEvalapp_Evaluation = By.xpath("//div[@id='menu1Bid']//button[@id = 'addApprovertc']");
	public By EvalUser1_Evaluation = By.xpath("//div[@id='menu1Bid']//table[@id='approver']/tbody/tr[1]/td[2]/input");
	public By EvalApprovalType1_Evaluation = By.xpath("//div[@id='menu1Bid']//select[@id='filterTest']");
	public By EvalcommentSection_Evaluation = By.xpath("//div[@id='menu1Bid']//label[contains(text(),'Comments')]/following::textarea");
	public By OpeningcommentSection_Evaluation = By.xpath("//div[@id='homeBid']//label[contains(text(),'Comments')]/following::textarea[contains(@ng-model,'openingcomment')]");
	public By TenEvalTab_Evaluation = By.xpath("//a[normalize-space()='Tender Evaluation']");
	public By submitbutton_EvalUser = By.xpath("//button[@data-original-title='Submit']");
	public By Alert_Nobtn_EvalUser = By.xpath("//button[@data-bb-handler='no']");
	public By LblBidDetails_Evaluation = By.xpath("//h3[normalize-space()='Bid Details']");
	public By LblBiddername_Evaluation = By.xpath("(//th[contains(text(),'Bidder Name')])[1]");
	public String NegotiatedStatus_Negotiation = "//*[contains(normalize-space(text()),'{0}')]//following-sibling::td//*[normalize-space(text())='Negotiated']";
	public By Negotiation_detail = By.xpath("//h3[normalize-space()='Negotiation Detail']");
	
	// p
	public By approverType1_p = By.xpath("//select[@id='filterTest']");
	// public By approverType2_p =
	// By.xpath("//table[@id='approver']//tr[2]//td[3]//select[@id='filterTest']");
	public By previewAllOkButton_p = By.xpath("//div[@class='modal-footer']//button[text()='Ok']");
	public By close_history = By
			.xpath("(//div[@class='modal-footer']//button[@id='btn-prev']//following-sibling::button)[1]");
	public By corrigendumStatus_No = By.xpath("//table[@id='myTablebyrTl00']/tbody/tr[1]/td[6]");
	public By corrigendumNewboqMandatory = By.xpath("//div[contains(text(),'new BOQ (Mandatory)')]");
	public By corrigendumOldboqMandatory = By.xpath("//div[contains(text(),'old BOQ (Mandatory)')]");
	public By BOQMadatoryItemCode2_tender2creationbydutta = By.xpath("//input[@id='boq.item_code.1']");
	public By BOQMadatoryItemdescription2_tender2creationbydutta = By.xpath("//input[@id='boq.item_name.1']");
	public By corrigendumSelect_NonSORItem = By.xpath("//span[text()='BOQ (Mandatory)']");
	public By corrigendumSelectAttachments = By.xpath("//span[text()='Attachments']");
	public By corrigendumNewAttachments = By.xpath("//div[contains(text(),'new Attachments')]");
	public By corrigendumOldAttachments = By.xpath("//div[contains(text(),'old Attachments')]");
	public By boqProjectQuantityBy = By.xpath(
			"//div[@id='rfqItemtabl2-ri_boq']/table/tbody/tr[1]/td[position()=6]//input[@placeholder='Project Quantity']");

	public By boqpreferedMakeBy = By.xpath(
			"//div[@id='rfqItemtabl2-ri_boq']/table/tbody/tr[1]/td[position()=8]//input[@placeholder='Prefered Make']");

	public By boqBudgetrateBy = By.xpath(
			"//div[@id='rfqItemtabl2-ri_boq']/table/tbody/tr[1]/td[position()=9]//input[@placeholder='Budgeted Rate']");

	public By boqRemarksBy = By.xpath(
			"//div[@id='rfqItemtabl2-ri_boq']/table/tbody/tr[1]/td[position()=11]//textarea[@placeholder='Remarks']");

	public By boqTableActionmenuBy = By.xpath("//div[@id='rfqItemtabl2-ri_boq']/table/tbody/tr[1]/td[13]");
	public By childNonSORItemfirstBy = By.xpath(
			"//div[@id='rfqItemtabl2-ri_boq']/table/tbody/tr[1]/td[position()=13]//li/a[contains(@ng-click,'addChildNonSORItem')]");
	public By mandatoryitemBy = By.xpath("//*[@id=\"ri_boq.mandatory_item.0\"]");
	public By projectQtysItemsBy = By.xpath("//*[@name='projectquantity']");
	public By boqUimBy = By.xpath("//div/table/tbody/tr[2]/td[position()=4]//following::select[@name='uom']");
	public By mandatoryitem1By = By.xpath("//*[@id='ri_boq.mandatory_item.1']");
	public By checkBoxTechMahindraBy = By
			.xpath("//table[@id='vendorLst']/tbody/tr[ td[2] and td/span[text()='Tech Mahindra']]/td[1]");
	public By checkBoxTcsBy = By.xpath("//table[@id='vendorLst']/tbody/tr[ td[2] and td/span[text()='TCS']]/td[1]");

	public By tenderListStatus_Draft = By.xpath("//span[text()='Draft  ']");
	public By coordinatorFlag2 = By.xpath("//table[@id='approver']/tbody/tr[2]/td[5]//input");
	public By label1 = By.xpath("(//input[@placeholder='Label'])[1]");
	public By closeButton = By.xpath("//div[@id='myModaliview']//button[text()='Close']");
	public By bidSubmissionTransaction = By.xpath("//i[@class='fa fa-users']");
	public By bidSubmissionTenderListing = By.xpath("//a[contains(text(),'Tender Listing')]");
	public By bidsubmissionSearchByKeyword = By
			.xpath("//form[contains(@class,'form-horizontal')]//div[@class='row']//input[@placeholder='Search By Keywords']");

	public By bidSubmission_Bid = By.xpath("//a[contains(@id,'interst123') and contains(text(),'Bid')]");
	public By acceptOrDeclineTitle = By
			.xpath("//h3[@class='page-header box_shadow' and contains(text(),' Accept Or Decline')]");
	public By newTermsAndServices_CheckBox = By.xpath("//input[contains(@ng-model,'tenderRes.interestFlag')]");
	public By newTermsAndServices_Accept = By
			.xpath("//button[contains(text(),'Accept')]");
	public By tenderId_generalInformation_bidSubmission = By
			.xpath("//strong[@class='ng-binding' and contains(text(),'Tender ID')]");
	public By tenderDescription_generalInformation_bidSubmission = By
			.xpath("//strong[@class='ng-binding' and contains(text(),'Tender Description')]");
	public By tenderCategory_generalInformation_bidSubmission = By
			.xpath("//strong[@class='ng-binding' and contains(text(),'Tender Category')]");
	public By quotationReferenceCode_generalInformation_bidSubmission = By
			.xpath("//strong[@class='ng-binding' and contains(text(),'Quotation Reference Code')]");
	public By quotationCurrency_generalInformation_bidSubmission = By
			.xpath("//strong[@class='ng-binding' and contains(text(),'Quotation Currency')]");
	public By quotationReferenceCodeInput_generalInformation_bidSubmission = By
			.xpath("//input[@id='quot_gen_info.quote_ref_no.0' or placeholder='Quotation Reference Code' ]");
	public By alertPopUp_QRC_bidSubmission = By.xpath("//h2[@class='modal-title' and contains(text(),'Alert')]");
	public By errorMessage_QRC_bidSubmission = By
			.xpath("//h3[@class='ng-binding' and contains(text(),'Errors in General Information')]");
	public By alertClose_QRC_bidSubmission = By
			.xpath("//div[@class='modal-footer']//button[contains(@ng-click,'clearAlertMessages()')]");
	// mandatory field validation
	public By technicalAlertTab_bidSubmission = By
			.xpath("//strong[@class='ng-binding' and contains(text(),'Technical Tab was saved successfully!')]");
	public By termsAndCondition_bidSubmission = By.xpath("//a[@id='qh_termsnconditons']");
	public By termsAndConditionAlertTab_bidSubmission = By.xpath(
			"//strong[@class='ng-binding' and contains(text(),'Terms and Conditions Tab was saved successfully!')]");

	public By attachmentsAlertTab_bidSubmission = By
			.xpath("//strong[@class='ng-binding' and contains(text(),'Attachments Tab was saved successfully!')]");

	public By commercial_bidSubmission = By.xpath("//a[@id='qh_commercial_v1']");
	public By commercialAlertTab_bidSubmission = By
			.xpath("//strong[@class='ng-binding' and contains(text(),'Commercial Tab was saved successfully!')]");

	public By quotationSummary_bidSubmission = By.xpath("//a[@id='uvoltasdemoqi']");
	public By quotationSummaryAlertTab_bidSubmission = By.xpath(
			"//strong[@class='ng-binding' and contains(text(),'Quotation Summary Tab was saved successfully!')]");

	public By technical_priviewAll = By.xpath("(//table//tr//following::h2[contains(text(),'Technical')])[1]");
	public By termsAndCondition_priviewAll = By
			.xpath("(//table//tr//following::h2[contains(text(),'Terms and Conditions')])[1]");
	public By generalInformation_priviewAll = By
			.xpath("(//table//tr//following::h2[contains(text(),'General Information')])[1]");
	public By quotationAttachments_priviewAll = By
			.xpath("//table//tr//following::h2[contains(text(),'Quotation Attachment')]");
	public By bidderSpecificAttachments_priviewAll = By
			.xpath("(//table//tr//following::h2[contains(text(),'Bidder Specific Attachment')])[1]");
	public By commercial_priviewAll = By.xpath("(//table//tr//following::h2[contains(text(),'Commercial')])[1]");
	public By quotationSummary_priviewAll = By
			.xpath("(//table//tr//following::h2[contains(text(),'Quotation Summary')])[1]");
	public By submitbid = By.xpath("//button[@id='btn-prev_bidsb' and contains(text(),'Submit Bid')]");
	public By bidSuccessfulMessage = By.xpath("//strong[text()='Your bid has been submitted successfully']");
	public By bidSuccessfulMessage_Ok = By.xpath("//button[@class='btn btn-primary closeBtn']");
	public By bidListing = By.xpath("//a[contains(text(),'Bid Listing')]");
	public By draftBidTab = By.xpath("//a[text()='Draft Bid']");
	public By submittedBidTab = By.xpath("//a[@id='submittedBid']");
	public By withdrawBidTab = By.xpath("//a[text()='Withdrawn Bid']");
	public By submittedOn_bidList = By.xpath("//table[@id='myTable']//tbody//tr[1]//td[8]");
	public By tenderPreview = By.xpath("//a[@ng-click='previewTender(bid.tenderId)']");
	public By bidPreview = By.xpath("(//a[@class='dlt_tr'])[2]");
	// tenderpreview
	public By generalInformation_tenderPreview = By.xpath("//div[text()='General Information']");
	public By otherAttachments_tenderPreview = By.xpath("//div[text()='Other Attachments']");
	public By attachments_tenderPreview = By.xpath("//div[text()='Attachments']");
	public By dateSchedule_tenderPreview = By.xpath("//div[text()='Date Schedule']");
	public By termsAndConditions_tenderPreview = By.xpath("//div[text()='Terms and Conditions']");
	public By technical_tenderPreview = By.xpath("//div[text()='Technical']");
	public By boqSummary_tenderPreview = By.xpath("//div[text()='BOQ Summary']");
	public By previewAll_close = By.xpath("(//button[@class='btn btn-primary succes_msgsnd' and text()='Close'])[1]");
	public By upload = By.xpath("//input[@filename-model='row']");
	public By withdraw_bidlist = By.xpath("//a[@data-target='#myModalview']");
	public By acceptOrDecline_withdraw = By.xpath("//h3[text()='Accept Or Decline']");
	public By popUpMessage_withdraw = By.xpath("//p[text()=' Your bid will be withdrawn from the tender ']");
	public By newTermsAndServices_CheckBox_withdraw = By
			.xpath("//input[contains(@ng-model,'withdrawBidRes.acceptFlag')]");
	public By newTermsAndServices_Accept_withdraw = By
			.xpath("(//button[@class='btn btn-icon btn-primary' and contains(text(),'Accept')])[1]");
	public By confirmation_withdraw_bidlist = By.xpath("//h4[text()='Are you sure you want to withdraw the Bid']");
	public By ok_confirmation_withdraw_bidlist = By.xpath("//button[@ng-click='withdrawQuotation()']");
	public By successPopUp_withdraw_bidlist = By.xpath("//div[@class='bootbox-body']");
	public By successPopUpOk_withdraw_bidlist = By.xpath("//button[@data-bb-handler='ok']");
	public By tenderNo_withdrawnOn = By.xpath("//tr[contains(@ng-repeat,'bid in withdrawBidList')]//td[1]");

	public By selectSORItemMaterialBy2(int i) {
		return By.xpath("((//div[@id='rfqpoptab1'])[2]//select[1])[" + i + "]");
	}

	public By addSORItemSearch2 = By.xpath("(//button[@data-original-title='Search'])[2]");
	public By previewoteLimitedTender = By.xpath("//label[contains(text(),'Limited Tender')]");
	public By otherAttachmentsTab = By.xpath("//a[text()='Other Attachments']");
	public By attachmentsTab = By.xpath("//a[text()='Attachments']");
	public By boqSummaryTab = By.xpath("//a[text()='BOQ Summary']");
	public By boqSummaryTabComment = By
			.xpath("//div[@id='uvoltasdemori']//textarea[@ng-model='template.tabWiseApproverComment']");
	public By commentAttachments = By
			.xpath("//div[@id='rfqattachment']//textarea[@ng-model='template.tabWiseApproverComment']");
	public By saveTabComment_Attachments = By
			.xpath("//button[contains(text(),'Save Tab Comment')][@id='saveComment4']");
	public By saveTabComment_OtherAttachments = By
			.xpath("//button[contains(text(),'Save Tab Comment')][@id='saveComment5']");
	public By commentTechnical1 = By
			.xpath("//div[@id='rh_techcompl']//textarea[@ng-model='template.tabWiseApproverComment']");
	public By saveTabComment_Technical1 = By
			.xpath("//div[@id='rh_techcompl']//button[contains(text(),'Save Tab Comment')]");
	public By commentTermsAndConditions1 = By
			.xpath("//div[@id='rh_termsnconditions']//textarea[@ng-model='template.tabWiseApproverComment']");
	public By saveTabComment_TermsAndConditions1 = By
			.xpath("//div[@id='rh_termsnconditions']//button[contains(text(),'Save Tab Comment')]");
	public By dateScheduleTemplate = By.xpath("//a[text()='Date Schedule']");
	public By commentOtherAttachments = By
			.xpath("//div[@id='requiredbidderattachment']//textarea[@ng-model='template.tabWiseApproverComment']");
	public By corrigendumSelect_NonSORItemSummary = By.xpath("//span[text()='BOQ Summary']");
	public By BOQSummaryItemCode2_tender2creationbydutta = By.xpath("//input[@id='uvoltasdemori.item_code.1']");
	public By BOQSummaryItemdescription2_tender2creationbydutta = By.xpath("//input[@id='uvoltasdemori.item_name.1']");
	public By boqSummaryQuantity2 = By.xpath("//input[@id='uvoltasdemori.item_qty.1']");
	public By corrigendumNewboqSummary = By.xpath("//div[contains(text(),'new BOQ Summary')]");
	public By corrigendumOldboqSummary = By.xpath("//div[contains(text(),'old BOQ Summary')]");
	public By sendForApprovalNotRequired_corrigendum = By
			.xpath("//form[contains(@class,'ng-valid')]//input[@id='appNo']/parent::label");
	public By tenderListStatus_Published = By.xpath("//span[text()='Published  ']");
	public By boqSummarySorRate2 = By.xpath("//input[@id='uvoltasdemori.sor_rate.1']");
	public By itemNamesInItemMenutableBy = By
			.xpath("//table/tbody[@id='rfqItemtabl-uvoltasdemori itmtbl-custom']/tr/td[3]/span/child::span");
	public By addOptionalBy = By.xpath(" //button[@data-target='#addOptionalItemModal_uvoltasdemoqi']");
	public By addOptionalPopUpBy = By
			.xpath("//div[@id='addOptionalItemModal_uvoltasdemoqi']//child::*[text()=' Add Optional Item']");
	public By allOptionalItemsCheckedBy = By
			.xpath("//table/thead/tr/th/input[@type='checkbox' and contains(@ng-model ,'allOptionalItemsChecked')]");
	public By allOptionalItemsOkBy = By
			.xpath("//button[@ng-click='calculateVisibleItems()' and contains(text(),'OK')]");
	public By reBid = By.xpath("(//a[@class='dlt_tr'])[3]");
	public By tenderNo_draftBid = By.xpath("//tr[contains(@ng-repeat,'bid in draftBidList')]//td[1]");
	public By editBid = By.xpath("(//a[@class='dlt_tr'])[3]");
	public By previewAllBy = By.xpath("//div[@id='myModalprev']//following::h3[contains(text(),'Preview All')]");

	public By SendforApprovalTextBy = By
			.xpath("//div[@id='tenderApprovalModal']//following::h3[contains(text(),'Send For Approval')]");

	public By actionMenuDropDownBy = By
			.xpath("//button[@class='btn btn-primary dropdown-toggle' and @data-btn-row='BidPart1']");
	public By actionMenuDropDownBy_PO = By
			.xpath("//button[@class='btn btn-primary dropdown-toggle' and @id='menu1']");

	public By bidpublishStage = By.xpath("//button[@id='bidPublish']");

	public By showingRecordsBy = By.xpath("//div[@id='myTender']/div");
	public By excludingVATFieldBy = By.xpath("//input[@id='qi_boqoptional.basicunit.0']");

	public By boqOptionalAlertTab_BidSubmissionBy = By
			.xpath("//strong[@class='ng-binding' and contains(text(),'BOQ (Optional) Tab was saved successfully!')]");

	public By boqaddOptionItemButtonBy = By.xpath("//button[@data-target='#addOptionalItemModal_qi_boq']");

	public By boqaddOptionItemPopupBy = By
			.xpath("//div[@id='addOptionalItemModal_qi_boq']//child::*[text()=' Add Optional Item']");

	public By boqallOptionalItemsCheckedBy = By.xpath(
			"//div[@id='addOptionalItemModal_qi_boq']//table/thead/tr/th/input[@type='checkbox' and contains(@ng-model ,'allOptionalItemsChecked')]");

	public By boqallOptionalItemsPopOkBy = By.xpath(
			"//div[@id='addOptionalItemModal_qi_boq']//button[@ng-click='calculateVisibleItems()' and contains(text(),'OK')]");

	public By boqproposedMakeBy = By.xpath("//*[starts-with(@class,'form-control qi_boq.proposed_make')]");

	public By boqBasicUnitVatBy = By.xpath("//*[starts-with(@class,'form-control qi_boq.basicunit')]");

	public By boqShowrecordsBy = By
			.xpath("//form[@name='forms.form_qi_boq']//*[contains(text(),'More...')]/parent::div/child::span");

	public By boqoptionalpreviewAllpageBy = By.xpath(
			"(//*[@id='myModalprev_bid']//following::div//table//tr/th/h2[contains(text(),'BOQ (Optional)')])[1]");

	public By boqMandatorypreviewAllpageBy = By.xpath(
			"(//*[@id='myModalprev_bid']//following::div//table//tr/th/h2[contains(text(),'BOQ (Mandatory)')])[1]");

	public By TenderPreviewBoQOptionalBy = By
			.xpath("//*[@id='myModalprev']//following::div/child::div[contains(text(),'BOQ (Optional)')]");

	public By TenderPreviewBidBoQmandatoryBy = By
			.xpath("//*[@id='myModalprev']//following::div/child::div[contains(text(),'BOQ (Mandatory)')]");
	public By savebutton1 = By.xpath("//button[@id='bidsave']");
	public By alertPopUp_QRC_bidSubmission1 = By.xpath("//h2[text()='Alert']");
	public By technicalAlertTab_bidSubmission2 = By.xpath("//strong[text()='Technical Tab was saved successfully!']");

	public By selectTenderTypeBy = By.xpath("//select[@ng-model='tenderF.typeId']");

	public By totalSubmittedBidsBy = By.xpath("//li[starts-with(@ng-click,'totalSubmitBidClick')]/a");

	public By bidDetailsPopUpBy = By
			.xpath("//*[@id='totalSubmitBid']//following::div[contains(text(),'Bidder Detail')]");

	public By bidDetailsStatusBy = By.xpath("//*[@id='submitBidTable']/tbody/tr/td[2]");

	public By bidPreviewLinkBy = By.xpath("//*[starts-with(@ng-click,'previewBid')]");

	public By evaluationStatus = By.xpath(
			"//div[@class='col-md-12']//strong[contains(text(),'Evaluation Status : 	 Pending for opening Approval (Cover 1)')]");
	public By evaluation_tenderId = By.xpath("//div[@class='text-right col-md-6']");
	public By evaluation_noOfLiveBids = By.xpath("//div[@class='col-md-6 text-left']");

	public By evaluationStatus2 = By.xpath("//strong[contains(text(),'Evaluation Status : 	 Cover 1 Evaluated')]");
	public By tenderId = By.xpath("//strong[contains(text(),'Tender Id : ')]");
	public By numberOfLiveBids = By.xpath("//strong[contains(text(),' No of Live Bids :')]");
	public By openAndEvaluateBid = By.xpath("//label[contains(text(),' Open & Evaluate Bid  ')]");
	public By scheduleOpening = By.xpath("//label[contains(text(),'Schedule Opening ')]");
	public By approverOrEvaluatorRequired = By.xpath("//label[contains(text(),' Approver/Evaluator required ')]");

	public By evaluationTenderStatus_Evalutation = By.xpath("//span[text()='Evaluation  ']");

	public By noOfEligibleBid = By.xpath("//span[contains(text(),'No of Eligible Bid :')]");

	public By bidderCompany = By.xpath("//table[@id]/tbody/tr[2]/td[3]");

	public By bidderOverAllComment = By.xpath("//table[@id]/tbody/tr[2]/td[8]");
	public By bidderAttachment = By.xpath("//table[@id]/tbody/tr[2]/td[9]");
	public By bidderActionDropDown = By.xpath("//table[@id]/tbody/tr[2]/td[10]//button");
	public By bidderSelection = By.xpath("//table[@id]/tbody/tr[2]/td[1]");
	public By technical_Clause = By.xpath("(//th[text()='Clause'])[1]");
	public By technical_Details = By.xpath("(//th[text()='Clause'])[1]");
	public By technical_Attachment = By.xpath("(//th[text()='Attachment'])[1]");
	public By technical_Complaince = By.xpath("(//th[text()='Technical Compliance'])[1]");
	public By technical_AttachFile = By.xpath("(//th[text()='Attach File (if any)'])[1]");
	public By technical_Remarks = By.xpath("(//th[text()='Remarks'])[1]");
	public By technical_EvaluatorRemarks = By.xpath("(//th[text()='Evaluator Remarks'])[1]");
	public By termsNConditions = By.xpath("//a[text()='Terms and Conditions']");
	public By tnc_EvaluatorRemarks = By.xpath("(//th[text()='Evaluator Remarks'])[2]");
	public By tnc_Clause = By.xpath("(//th[text()='Clause'])[2]");
	public By tnc_Details = By.xpath("(//th[text()='Details'])[2]");
	public By tnc_Attachment = By.xpath("(//th[text()='Attachment'])[2]");
	public By tnc_Complaince = By.xpath("//th[text()='Compliance']");
	public By tnc_AttachFile = By.xpath("(//th[text()='Attach File (if any)'])[2]");
	public By tnc_Remarks = By.xpath("(//th[text()='Remarks'])[2]");
	public By tenderIdBidDetails = By.xpath("//div[@id='_quot_gen_info']//strong[contains(text(),'Tender ID')]");
	public By tenderDescription = By.xpath("//strong[contains(text(),'Tender Description')]");
	public By proableAmountOfContract = By.xpath("//strong[contains(text(),'Probable Amount of the Contract')]");
	public By tenderCategory = By.xpath("//strong[contains(text(),'Tender Category')]");
	public By referenceCode = By.xpath("//strong[contains(text(),'Quotation Reference Code')]");
	public By quotationCurrency = By.xpath("//strong[contains(text(),'Quotation Currency')]");
	public By bidderAttachment1 = By.xpath("//a[contains(text(),'Bidder Attachment')]");
	public By bidSpecificAttachment = By.xpath("//a[contains(text(),'Bid Specific Attachment')]");
	public By tenderAttachment = By.xpath("//a[contains(text(),'Tender Attachment')]");
	public By close = By.xpath("//button[@ng-click='closePreview()']");
	public By approveOrRejectEditableLink = By.xpath("//i[@class='fa fa-pencil']");
	public By approveRadioButton = By.xpath("//input[@type='radio']/following::strong[text()='Approve']");
	public By rejectRadioButton = By.xpath("//input[@type='radio']/following::strong[text()='Reject']");
	public By save = By.xpath("//button[contains(text(),'Save')]");
	public By commentText = By.xpath("//textarea[@ng-model='bidderStatusModel.comment']");
	public By alertPopUp = By.xpath("//strong[text()='TCS test (TCS_AUTO_01) is successfully Approved']");
	public By alertCloseButton = By.xpath("//button[@ng-click='clearAlertMessages()'][normalize-space()='Close']");
	public By bidderStatusApprove = By.xpath("//label[text()='Approved']");
	public By submitbutton1 = By.xpath("//a[@data-original-title='Submit']");

	public By overallComment_currentPart = By.xpath("//iframe[@id='uiTinymce1_ifr']");
	public By upload_editable = By.xpath("//input[@class='ng-isolate-scope']");
	public By pendingForEvaluationApproval = By
			.xpath("//button[@class='btn btn-primary btn-circle btn-lg btn-lg-sub2 grnBtn ng-scope ng-isolate-scope']");
	public By bidDetailstenderId = By.xpath("(//strong[@class='ng-binding'])[1]");
	public By bidDetailstenderStatus = By.xpath("(//strong[@class='ng-binding'])[2]");
	public By TotalNoOfSubmittedBid = By.xpath("(//span[@class='ng-binding'])[2]");
	public By bidderName = By.xpath("//table[@class='table']//tbody//tr[2]//td[2]");
	public By bidder_bidSubmissionDate = By.xpath("//table[@class='table']//tbody//tr[2]//td[4]");
	public By bidder_decryptionStatus = By.xpath("//table[@class='table']//tbody//tr[2]//td[5]");
	public By bidder_status = By.xpath("//table[@class='table']//tbody//tr[2]//td[6]");
	public By bidder_approveOrReject = By.xpath("//table[@class='table']//tbody//tr[2]//td[7]");
	public By bidder_overallComment = By.xpath("//table[@class='table']//tbody//tr[2]//td[8]");
	public By bidder_attachment = By.xpath("//table[@class='table']//tbody//tr[2]//td[9]");
	public By bidder_actiondropdown = By.xpath("//table[@class='table']//tbody//tr[2]//td[10]");
	public By bidder_checkbox = By.xpath("//input[@ng-click='checkAllBidderForDecryption()']");
	public By decryptSelected = By.xpath("//button[@ng-click='decryptBidderData()']");
	public By bidDetails = By.xpath("(//li[@class='eborder-top'])[3]");
	public By termsAndCondition_bidDetails = By.xpath("//a[text()='Terms and Conditions']");
	public By generalInformation_bidDetails = By.xpath("//a[text()='General Information']");
	public By attachments_bidDetails = By.xpath("//a[text()='Attachments']");
	public By commercial_bidDetails = By.xpath("//a[text()='Commercial']");
	public By quotationSummary_bidDetails = By.xpath("//a[text()='Quotation Summary']");
	public By close_bidDetails = By.xpath("//button[@ng-click='closePreview()']");
	public By approveOrReject_editable = By.xpath("//button[@ng-click='editBidderStatus($index)']");
	public By approve_editable = By.xpath("(//label[@class='radio-inline'])[1]");
	public By comment_editable = By.xpath("//textarea[@ng-model='bidderStatusModel.comment']");
	public By save_editable = By.xpath("//button[@ng-click='saveBidderStatus()']");
	public By alertPopUp_editable = By.xpath(
			"//strong[@class='ng-binding' and contains(text(),'CTS test (CTS_AUTO_01) is successfully Approved')]");
	public By submit_biddetails = By.xpath("//button[@ng-click='validateDecryptedData();']");
	public By tenderStatus_completed = By
			.xpath("//span[@class='ng-binding ng-isolate-scope' and contains(text(),'Completed')]");
	public By tenderStage_completed = By.xpath("//button[@data-original-title='Completed']");
	public By completedtablnk = By.xpath("//a[normalize-space()='Completed']");
	public By evaluation_sendForApproval = By.xpath("//button[@data-original-title='Send For Approval']");
	public By tenderStatus_evaluation = By
			.xpath("//span[@class='ng-binding ng-isolate-scope' and contains(text(),'Evaluation')]");

	public By selectYesBy = By.xpath("//*[@type='radio' and @value='Y']");

	public By bidOpenBy = By.xpath("//label[@id='bidOpen']");
	public By bidValBy = By.xpath("//label[@id='bidVal']");

	public By dropDownNothingselectedBy = By.xpath("//span[text()='Nothing selected']");

	public By tender_Approver_01By = By.xpath(
			"//*[@class='dropdown-menu open']//li/a/span[contains(text(),'Tender Approver (TENDER_APPROVER_01)')]");

	public By commentSectionBy = By.xpath("//label[contains(text(),'Comments')]/following::textarea");

	public By bidEvaluationTabBy = By.xpath("//*[@id='menuBidmn1' and text()='Bid Evaluation']");

	public By evaluator_01By = By
			.xpath("//*[@class='dropdown-menu open']//li/a/span[contains(text(),'Evaluator Test (EVALUATOR_01)')]");

	public By tenderStatusInOpeningBy = By
			.xpath("//span[@class='ng-binding ng-isolate-scope' and contains(text(),'Opening')]");

	public By tenderStageApprovalCover1By = By
			.xpath("//*[@data-original-title='Pending for opening Approval (Cover 1)']");

	public By workFlowTypeApprovalCover1By = By.xpath("//td[text()='Pending for opening Approval (Cover 1)']");

	public By workFlowStatusPendingBy = By.xpath("//td[text()='Pending']");

	public By tenderOpeningTabDetailsLinkBy = By
			.xpath("//td[text()='WIP']/following-sibling::td/a[contains(text(),'Details')]");

	public By bidOpeningcommentBy = By.xpath("//*[text()='Bid Opening Comment']");

	public By workFlowTypeFinalApprovalCover1By = By
			.xpath("//td[text()='Pending for Approval for Final Evaluation (Cover 1)']");

	public By sequentialBy = By.xpath("//label[@class='sequanLab']");

	public By evaluationSettingspageEvaluationCommentBy = By.xpath("//*[@name='evaluationcomment']");

	public By defaultCatBy = By.xpath("//table/tbody/tr[1]/td[position()=8 and text()='Default_cat']");
	public By supplier_standing = By.xpath("//section[contains(@class,'fixedHeader-table-section')]//table//th[contains(text(),'Suppliers Standing')]");

	public By tenderopeningTabBy = By.xpath("//*[text()='Tender Opening']");

	public By approveBtn = By.xpath("//button[@data-original-title='Approve']");
	public By evaluation_openAndEvaluationBid = By.xpath("//label[contains(text(),'Open & Evaluate Bid')]");
	public By evaluation_approverOrEvaluatorRequired = By
			.xpath("//label[contains(text(),'Approver/Evaluator required')]");
	public By evaluation_scheduleOpening = By.xpath("//label[contains(text(),'Schedule Opening')]");
	public By evaluationSettings = By.xpath("//a[contains(text(),'Evaluation Settings')]");
	public By tenderStatus_closed = By
			.xpath("//span[@class='ng-binding ng-isolate-scope' and contains(text(),'Closed')]");
	public By evaluationTenderStatus_Completd = By.xpath("//span[text()='Completed  ']");
	public By evaluationTenderStage_completd = By.xpath("//button[@data-original-title='Completed']");

	public By bidopeningcommentTabBy = By.xpath("//a[text()='Bid Opening Comment']");
	public By bidEvaluationCommentTabBy = By.xpath("//a[text()='Bid Evaluation Comment']");
	public By tenderStageApprovalCover2By = By
			.xpath("//*[@data-original-title='Pending for Opening Approval (Cover 2)']");
	public By tenderStageCover1Evaluated = By.xpath("//*[@data-original-title='Cover 1 Evaluated']");
	public By alertPopUp_CTS_AUTO_01 = By.xpath("//strong[text()='CTS test (CTS_AUTO_01) is successfully Approved']");
	public By closeXButton = By.xpath("//button[@ng-click='loadValidationInfoByBidder()']");
	public By alertPopUp_CTS = By.xpath("//strong[text()='CTS test (CTS_AUTO_01) is successfully Approved']");
	public By workFlowTypeApprovalCover2By = By.xpath("//td[text()='Pending for Opening Approval (Cover 2)']");

	public By workFlowTypeFinalApprovalCover2By = By
			.xpath("//td[text()='Pending for Approval for Final Evaluation (Cover 2)']");
	public By selectNoBy = By.xpath("//*[@type='radio' and @value='N']");
	public By savepopup = By.xpath("//button[@ng-click='saveOrUpdateEvaluation()']");
	public By documentId_Evaluation = By.xpath("(//td[@class='ng-binding'])[2]");
	public By workFlowType_Evaluation = By.xpath("(//td[@class='ng-binding'])[4]");
	public By workFlowStatus_Evaluation = By.xpath("//td[text()='Pending']");
	public By EvaluationStatus_Evaluation = By.xpath("(//strong[@class='ng-binding'])[1]");
	public By tenderId_Evaluation = By.xpath("(//strong[@class='ng-binding'])[2]");
	public By noOfLiveBids_Evaluation = By.xpath("(//strong[@class='ng-binding'])[3]");
	public By openAndEvaluateBid_Evaluation = By.xpath("//label[contains(text(),'Open & Evaluate Bid')]");
	public By scheduleOpening_Evaluation = By.xpath("//label[contains(text(),'Schedule Opening')]");
	public By openingApprovalCover1 = By.xpath("//button[@id='evaluation123']");
	public By approve0rReject_AlertMessage = By.xpath("//strong[text()='Please approve or reject CTS test']");
	public By viewDetailsOfBidder_AlertMessage = By.xpath("//strong[contains(text(),'Please View Details of Bidder')]");
	public String editablelinkpendingStatus = "//*[contains(normalize-space(text()),'{0}')]//following-sibling::td//*[normalize-space(text())='Pending']/../following-sibling::button";

	public String approvedStatus = "//*[contains(normalize-space(text()),'{0}')]//following-sibling::td//*[normalize-space(text())='Approved']";

	public String overallCommentAdded = "//*[contains(normalize-space(text()),'{0}')]//following-sibling::td[3]";

	public String attachmentAdded = "//*[contains(normalize-space(text()),'{0}')]//following-sibling::td[4]";

	public String rejectStatus = "//*[contains(normalize-space(text()),'{0}')]//following-sibling::td//*[normalize-space(text())='Rejected']";
	public By alertPopUp_CTSreject = By.xpath("//div[@id='alertMessageModal']//strong[@class='ng-binding']");
	public By bidderStatusRejected = By.xpath("//strong[contains(text(),'Rejected')]");
	public By bidderOverAllComment2 = By.xpath("//table[@id]/tbody/tr[2]/td[4]");
	public By bidderAttachment2 = By.xpath("//table[@id]/tbody/tr[2]/td[5]");

	public By approveAll = By.xpath("//button[@data-original-title='Approve All']");
	public By rejectBtn = By.xpath("//button[@data-original-title='Reject']");
	public By bidDetails1 = By.xpath("//i[@class='fa fa-eye']");

	public By approveAllOkButton = By.xpath("//button[text()='OK']");
	public By approveOrRejectEditableLink1 = By.xpath("(//i[@class='fa fa-pencil'])[1]");
	public By approveOrRejectEditableLink2 = By.xpath("(//i[@class='fa fa-pencil'])[2]");
	public By alertPopUp_TCS_AUTO_01 = By.xpath("//strong[text()='TCS test (TCS_AUTO_01) is successfully Rejected']");
	public By downloadDecryptBtnBy = By.xpath("//button[@data-original-title='Download Decrypted Data']");
	public By downloadDecryptLinkDropdownBy = By.xpath("//ul[@class='dropdown-menu extended logout widauto']//li/a");
	public By initiateNegotiation = By.xpath("//a[contains(text(),'Initiate Negotiation')]");

	public By negotiationBidSubmissionStartDate = By.xpath("//input[@id='negotiation-start-date-input']");
	public By negotiationBidSubmissionEndDate = By.xpath("//input[@id='negotiation-end-date-input']");
	public By tenderID_Negotiation = By.xpath("//strong[@class='ng-binding' and contains(text(),'Tender ID')]");
	public By evaluatorName = By.xpath("//input[@ng-model='evaluatorName']");
	public By bidPartOrCoverNumber = By.xpath("//input[@ng-model='bidPartName']");
	public By subProcessName = By.xpath("//select[@class='table table-striped table-advance']");

	public By bidderCode = By.xpath("//th[text()='Bidder Code']");
	public By bidderCode_CTS = By.xpath("//label[text()='CTS_AUTO_01']");
	public By bidderCode_TCS = By.xpath("//label[text()='TCS_AUTO_01']");
	public By bidderCode_TECH = By.xpath("//label[text()='TECH_AUTO_01']");

	public By bidder_Name = By.xpath("//th[text()='Bidder Name']");
	public By bidder_Name_CTS = By.xpath("//td[text()='CTS  test']");
	public By bidder_Name_TCS = By.xpath("//td[text()='TCS  test']");
	public By bidder_Name_TECH = By.xpath("//td[text()='Tech Mahindra  test']");

	public By company = By.xpath("//th[text()='Company']");
	public By company_CTS = By.xpath("//td[text()='CTS']");
	public By company_TCS = By.xpath("//td[text()='TCS']");
	public By company_TECH = By.xpath("//td[text()='Tech Mahindra']");

	public By biddercomment = By.xpath("//th[text()='Comment']");

	public By status = By.xpath("//th[text()='Last Evaluation Decision']");
	public By status_Rejected = By.xpath("//label[@class='ng-scope' and contains(text(),'Rejected')]");
	public By status_Approved = By.xpath("//label[@class='ng-scope' and contains(text(),'Approved')]");
	public By status_Negotiation = By.xpath("//label[@class='ng-scope' and contains(text(),'Negotiation')]");
	public By tenderStatus = By.xpath("//span[@class='ng-binding ng-isolate-scope']");
	public By cover1Negotiation = By
			.xpath("//button[@class='btn btn-primary btn-circle btn-lg btn-lg-sub2 grnBtn ng-scope ng-isolate-scope']");
	public By negotiation = By.xpath("(//a[@class='dlt_tr'])[3]");
	public By generalInformation_Tab = By.xpath("//a[text()='General Information']");
	public By bidEvaluationComment = By.xpath("//textarea[@ng-model='global.evaluationSettings.evaluationcomment']");
	public By confirmationPopUp = By.xpath(
			"//div[text()='Once confirmed,bidders who are selected as mandatory but did not submit the negotiated bids, will be automatically rejected. Do you wish to continue?']");
	public By confirmationPopUp_YES = By.xpath("//button[@class='btn btn-primary' and contains(text(),'Yes')]");
	// Vamshi
	public By approveOrRejectEditableLink_CTS = By.xpath("//table[@id]//td[text()='CTS']/following-sibling::td/button");
	public By approveOrRejectEditableLink_TCS = By.xpath("//table[@id]//td[text()='TCS']/following-sibling::td/button");
	public By approveOrRejectEditableLink_TechMahindra = By
			.xpath("//table[@id]//td[text()='Tech Mahindra']/following-sibling::td/button");
	public By alertPopUp_CTS_AUTO_01_Reject = By
			.xpath("//strong[text()='CTS test (CTS_AUTO_01) is successfully Rejected']");
	public By alertPopUp_Tech_AUTO_01 = By
			.xpath("//strong[text()='Tech Mahindra test (TECH_AUTO_01) is successfully Negotiated']");
	public By negotiateRadioButton = By.xpath("//input[@type='radio']/following::strong[text()='Negotiate']");
	public By negotiateMandatoryRadioButton = By.xpath("//input[@type='radio']/following::strong[text()='Mandatory']");
	public By alertPopUp_Tech_AUTO_01_Approved = By
			.xpath("//strong[text()='Tech Mahindra test (TECH_AUTO_01) is successfully Approved']");
	public By bidder_Allcheckbox = By.xpath("//input[@ng-click='checkAllBidderForDecryption()']");
	public By notrequired = By.xpath("//label[contains(text(),'Not Required')]");
	public By bidderStatus_Rejected = By.xpath("//strong[@class='ng-binding' and contains(text(),'Rejected')]");
	public By bidderStatus_Approved = By.xpath("//strong[@class='ng-binding' and contains(text(),'Approved')]");
	public By bidderStatus_Negotiated = By.xpath("//strong[@class='ng-binding' and contains(text(),'Negotiated')]");
	public By OverAllComment_CTS = By.xpath("//table[@class='table table-advance table-hover']//tbody//tr[2]//td[4]");
	public By OverAllComment_TCS = By.xpath("//table[@class='table table-advance table-hover']//tbody//tr[3]//td[4]");
	public By OverAllComment_TECH = By.xpath("//table[@class='table table-advance table-hover']//tbody//tr[4]//td[4]");
	public By attachment_CTS = By.xpath("//table[@class='table table-advance table-hover']//tbody//tr[2]//td[5]");
	public By attachment_TCS = By.xpath("//table[@class='table table-advance table-hover']//tbody//tr[3]//td[5]");
	public By attachment_TECH = By.xpath("//table[@class='table table-advance table-hover']//tbody//tr[4]//td[5]");
	public By bidderStatus_Approved_TECH = By
			.xpath("(//strong[@class='ng-binding' and contains(text(),'Approved')])[2]");
	public By bidderStatus_Rejected_TECH = By
			.xpath("(//strong[@class='ng-binding' and contains(text(),'Rejected')])[2]");
	public By previousBy = By.xpath("//a[text()='Prev']");
	public By negotiationTimeOverPopup = By.xpath("//div[contains(text(),'Negotiated Bid can be placed between')]");
	public By postTenderProcessBy = By.xpath("//*[contains(normalize-space(text()),'Post Tender Process')]");

	public By SN_stage = By.xpath("//button[@id='clrcookie']");
	public By createSanctionNote = By.xpath("//button[@data-original-title='Create Sanction Note']");
	public By sanctionReferenceNumber = By.xpath("//input[@id='sanctionRef']");
	public By sanctionRefNo_Submit = By.xpath("//button[@ng-click='saveSanctionRef()']");
	public By supplierSelectionCheckBox = By.xpath("//input[@ng-click='selectAllQuotation(checkAllBidder)']");
	public By plusIcon_CTS = By.xpath("//a[contains(text(),'CTS')]");
	public By plusIcon_TCS = By.xpath("//a[contains(text(),'TCS')]");

	public By recommendationTab = By.xpath("//a[contains(text(),'Recommendation')]");
	public By recommendationComment = By.xpath("//body[@id='tinymce']");
	public By submit = By.xpath("//button[@ng-click='submitSanction()']");
	public By actionDropdown = By.xpath("//button[@id='menu1']");
	public By view = By.xpath("//a[@ng-click='viewModal(sanction)']");
	public By viewDetails_TCS = By.xpath("//div[contains(text(),'TCS')]");
	public By viewDetails_CTS = By.xpath("//div[contains(text(),'CTS')]");
	public By viewDetails_close = By.xpath("(//button[contains(text(),'Close')])[5]");
	public By approvalDetails = By.xpath("//a[@ng-click='showApprovalDetails(sanction)']");
	public By noRecordsFound = By.xpath("//strong[text()='No Records Found']");
	public By approvalDetails_close = By.xpath("(//button[contains(text(),'Close')])[8]");
	public By issuePO = By.xpath("//button[contains(text(),'Issue PO')]");
	public By documentNumber = By.xpath("//form[@name='approvalForm']/table//tr/td[2]");
	public By typeAnyKeyword = By.xpath("//input[@ng-model='obj.snSearchText']");
	public By downloadSanctionReport = By.xpath("//a[@ng-click='downloadSnReportModal(sanction.sanctionId)']");
	public By saveSanction = By.xpath("//button[@ng-click='saveSanction(false)']");
	public By downloadReoprtFormat = By.xpath("//select[@ng-model='downLoadReportSupportingData.type']");
	public By goButton = By.xpath("//button[@ng-click='generateReport()']");
	public By downloadSanctionReportClose = By.xpath("(//button[contains(text(),'x')])[7]");
	public By username = By.xpath("//th[contains(text(),'User Name')]");
	public By approval_Type = By.xpath("//th[contains(text(),'Approval Type')]");
	public By approvalDetailsStatus = By.xpath("(//th[contains(text(),'Status')])[5]");
	public By recallButton = By.xpath("//a[@ng-click='openRecallModal(sanction.sanctionId)']");
	public By recallReasonComment = By.xpath("//textarea[@ng-model='recallObj.comment']");
	public By recallReasonSubmit = By.xpath("//button[@ng-click='recallFn()']");
	public By cancel_User1 = By.xpath("(//button[@ng-click='cancelUser(row)'])[1]");
	public By typeAnyKeyword1 = By.xpath("//input[@ng-model='tenderFilter']");
	public By sanctionNoteEvaluationCommentTab = By.xpath("//a[contains(text(),'Comment')]");
	public By sanctionNoteEvaluationApprove = By.xpath("//button[@data-original-title='Approve']");
	public By approveConfirm = By.xpath("//button[contains(text(),' Confirm')]");
	public By edit = By.xpath("//a[@ng-click='editSanctionNote(sanction)']");
	public By completedRFQId = By.xpath("//table[@id='myTablebyrTl00']/tbody/tr/td[2]");
	public By noRecords = By.xpath("//span[contains(@style,'position: absolute;right:')]/b");
	public By purchaseOrder = By.xpath("//span[contains(text(),'Purchase Order (PO)')]");
	public By poListing = By.xpath("//a[contains(text(),'PO Listing')]");
	public By createPO = By.xpath("//button[@data-original-title='Create PO']");
	public By selectSource = By.xpath("//select[@ng-model='selectedSource']");
	public By POstage = By.xpath("//button[@id='bidPublish']");
	public By sanctionTemplateGroupAlert = By.xpath(
			"//div[contains(text(),'Sanction template group does not exist.Please create sanction template group!')]");
	public By predefine = By.xpath("//input[@id='appPre'][@value='P']");
	public By issuePOTemplateAlert = By.xpath(
			"//div[contains(text(),'Sanction template not mapping with any PO template! Do you want to move to PO mapping?')]");
	public By sanctionTemplate = By.xpath("//span[contains(text(),'Sanction Template')]");
	public By templateGroupCreation = By.xpath("//a[contains(text(),'Template Group Creation')]");
	public By evaluationstage = By.xpath("//button[@id='evaluation123']");
	public By dopMenuLinkBy = By.xpath("//*[@class='fa fa-user']");

	public By createDopLinkBy = By.xpath("//*[contains(normalize-space(text()),'Create DOP')]");

	public By selectModuleBy = By.xpath("//*[@ng-model='dop.moduleId']");

	public By selectProcurementCategoryBy = By.xpath("//*[@ng-model='dop.procCatId']");

	public By addOrg_HierarchyBy = By.xpath("//*[contains(@data-target,'organisationHierarchy')]/i");

	public By addBtn_HierarchyBy = By
			.xpath("//*[contains(@ng-click,'setTempOrgHierachy')][contains(normalize-space(text()),'Add')]");

	public By dopValueFromBy = By.xpath("//*[@ng-model='dop.minValue']");

	public By dopValueToBy = By.xpath("//*[@ng-model='dop.maxValue']");

	public By dopNameBy = By.xpath("//*[@ng-model='dop.dopName']");

	public By DopSucessMsgBy = By.xpath("//*[contains(text(),'DOP has been saved successfully')]");

	public By dop_ListLinkBy = By.xpath("//*[contains(normalize-space(text()),'DOP List')]");

	public By addUserBy = By.xpath("//*[contains(@ng-click,'addUser')]");

	public By deleteDopBy = By.xpath("//*[contains(@ng-click,'removeChildRow')]");

	public By addUserToTableBy = By.xpath("//*[contains(@ng-click,'addUser2Table')]");

	public By approvalTypeDropdownBy = By.xpath("//*[@ng-model='user.approvalType']");

	public By saveUserBy = By.xpath("//*[@ng-click='saveUser()']");

	public By activeTabLinkBy = By.xpath("//*[contains(normalize-space(text()),'Activate')]");

	public By confirmBtn = By.xpath("//*[@data-bb-handler='confirm']");

	public By dopListStatusActiveBy = By
			.xpath("//*[@id='myTablebyrTl00']/tbody/tr/td[position()=9 and text()='Active']");

	public By doplistPageColumnNames = By.xpath("//*[@id='myTablebyrTl00']/thead/tr/th");

	public By deActiveTabLinkBy = By.xpath("//*[contains(normalize-space(text()),'De-Activate')]");

	public By dopListStatusInActiveBy = By
			.xpath("//*[@id='myTablebyrTl00']/tbody/tr/td[position()=9 and text()='Inactive']");

	public By dopListEditBy = By.xpath("//*[contains(normalize-space(text()),'Edit')]");

	public By dopListPreviewBy = By.xpath("//li/a[contains(normalize-space(text()),'DOP Preview')]");

	public By dopPreviewOkBy = By.xpath("//*[contains(text(),' Ok')]");

	public By dopPreviewVerifySanctionModuleBy = By.xpath(
			"//table/tbody/tr/td[label[text()='Module'] ]/following-sibling::td/label[contains(normalize-space(text()),'Sanction')]");

	public By dopClonelinkBy = By.xpath("//*[contains(normalize-space(text()),'Clone DOP')]");

	public By dopListSactionRowTextBy = By.xpath("//table/tbody/tr[1]/td[position()=3 and text()='Default_cat']");

	public By activeStateActionMenuBy = By.xpath(
			"(//*[@id='myTablebyrTl00']/tbody/tr/td[position()=9 and text()='Active']/following-sibling::td//button)[1]");

	public By draftStatActionMenuBy = By.xpath(
			"(//*[@id='myTablebyrTl00']/tbody/tr/td[position()=9 and text()='Draft']/following-sibling::td//button)[1]");
	public By sanctionNoteStage = By.xpath("//button[@id='clrcookie']");

	public By sanctionReferenceNo = By.xpath("//input[@id='sanctionRef']");
	public By sanctionSubmit = By.xpath("//button[@ng-click='saveSanctionRef()']");
	public By itemAllotment1 = By.xpath("(//input[@ng-model='x.info.sanctionFlag'])[1]");
	public By itemAllotmentSelect = By.xpath("(//div[@ng-click='toggleCollapse($index)']/h5)[1]");
	public By itemAllotmentRow = By
			.xpath("(//div[text()='BOQ (Mandatory)']/following-sibling::div/table/tbody/tr[1]/td[1]/input)[1]");

	public By recommendationOverAllComment = By.xpath("//body[@id='tinymce']");
	public By recommendationOverAllComment_frame = By.xpath("//iframe[@id='txtArea_ifr']");
	public By recommendationOverAllCommentButton = By.xpath("//body[@id='tinymce']");

	public By closeButtonApproval = By.xpath("//button[@ng-click='closeApprovalModal()'][contains(text(),'Close')]");
	public By cancelUser = By.xpath("//button[@ng-click='cancelUser(row)']");
	public By pendingForApproval = By.xpath(
			"//button[@data-original-title='Pending for approval'][@class='btn btn-primary btn-circle btn-lg btn-lg-sub2 grnBtn']");

	public By acceptOrReject = By.xpath(
			"//button[@class='btn btn-primary btn-circle btn-lg btn-lg-sub2 yellowBtn grnBtn'][@data-original-title='Accepted/Rejected']");
	public By coordinatorFlag_2 = By.xpath("//table[@id='approver']/tbody/tr[2]/td[5]//input");
	public By itemSelectionAlert = By
			.xpath("//li[contains(text(),'Please select quation item template for organization')]");
	public By suppliercheckboxCTS = By.xpath("(//input[@ng-model='x.info.sanctionFlag'])[1]");
	public By addAttachment = By.xpath("//button[@ng-click='addAttachment()']");
	public By uploadAttachment = By.xpath("//input[@clari-file='data']");
	public By recall = By.xpath("//a[@ng-click='openRecallModal(sanction.sanctionId)']");
	public By recallReason = By.xpath("//textarea[@name='recallReason']");
	public By submitRecall = By.xpath("//button[contains(text(),'Submit')][@data-dismiss='modal']");
	public By draftStage = By.xpath(
			"//button[@class='btn btn-primary btn-circle btn-lg btn-lg-sub2 grnBtn'][@data-original-title='Draft']");
	public By reviewButton = By.xpath("//i[@class='fa fa-arrow-left']");
	public By confirm = By.xpath("//button[@data-bb-handler='confirm']");
	public By rejectedSupplier = By.xpath("//a[text()='Rejected and Not-participated supplier']");
	public By orgName = By.xpath("//div[@id='rejectedSupplier']//table/tbody/tr/td[1]");
	public By wipApprover = By.xpath("//table[@id='myTable']/tbody/tr/td[7]");
	public By supplierStanding = By.xpath("//table[@id='myTable']/tbody/tr/td[7]");

	// public By coordinator = By.xpath("//input[@type='radio' and
	// @ng-value='rad_999']");
	public By SearchIdInSanction = By.xpath("//input[@ng-model='obj.snSearchText']");

	public By ALLsupplierSelectionCheckBox = By.xpath("(//input[@type='checkbox'])[1]");
	public By canceluser = By.xpath("//button[@class='btn btn-default btn-sm']/i");

	public By termsAndConditioncheckBox_TCS = By
			.xpath("((//a[contains(text(),'TCS')])[1]/../../..//div[text()='Terms and Conditions']/..//input)[1]");
	public By boqCheckbox_TCS = By
			.xpath("((//a[contains(text(),'TCS')])[1]/../../..//div[text()='BOQ (Mandatory)']/..//input)[1]");

	public By EditTagtext_supplierQuotedQuantity1_TCS = By
			.xpath("(//input[@placeholder='Supplier Quoted Quantity'])[1]/../span");

	public By EditTagtext_supplierQuotedQuantity2_TCS = By
			.xpath("(//input[@placeholder='Supplier Quoted Quantity'])[2]/../span");

	public By EditTagtext_supplierQuotedQuantity3_TCS = By
			.xpath("(//input[@placeholder='Supplier Quoted Quantity'])[3]/../span");

	public By termsAndConditioncheckBox_CTS = By
			.xpath("((//a[contains(text(),'CTS')])[1]/../../..//div[text()='Terms and Conditions']/..//input)[1]");
	public By boqCheckbox_CTS = By
			.xpath("((//a[contains(text(),'CTS')])[1]/../../..//div[text()='BOQ (Mandatory)']/..//input)[1]");

	public By EditTagtextsupplierQuotedQuantity1_CTS = By
			.xpath("(//input[@placeholder='Supplier Quoted Quantity'])[4]/../span");

	public By EditTagtextsupplierQuotedQuantity2_CTS = By
			.xpath("(//input[@placeholder='Supplier Quoted Quantity'])[5]/../span");

	public By EditTagtextsupplierQuotedQuantity3_CTS = By
			.xpath("(//input[@placeholder='Supplier Quoted Quantity'])[6]/../span");
	public By deleteUser = By.xpath("//button[@ng-click='cancelUser(row)']");

	public By sanctionDetails = By.xpath("//a[normalize-space(text())='Details']");
	public By approvesanction = By.xpath("//button[@data-original-title='Approve']");
	public By reviewsanction = By.xpath("//button[@data-original-title='Review']/i");
	public By commenttab = By.xpath("//a[normalize-space(text())='Comment']");
	public By summarytab = By.xpath("//a[normalize-space(text())='Summary']");
	public By confirmsanction = By.xpath("//button[normalize-space(text())='Confirm']");
	public By cancelsanction = By.xpath("//button[normalize-space(text())='Cancel']");
	public By mousehoverreview = By.xpath("//button[@data-original-title='Approve']/../../a[4]");

	public By btnTenderPO = By.xpath("//button[normalize-space(text())='PO']");
	public By btnIssuePO = By.xpath("//button[normalize-space(text())='Issue PO']");
	public By SelectTemplateGroup = By.xpath("//select/option[text()='PO Template Group V1.13']");
	public By SelectBidderPOCTS = By.xpath("//select/option[text()='CTS']");
	public By SelectBidderPOTCS = By.xpath("//select/option[text()='TCS']");
	public By BOQAllItemChecklist = By.xpath("(//p[text()='BOQ (Mandatory)']/../..//input)[1]");
	public By btnAddfinalItems = By.xpath("(//button[@title='Add'])[1]");
	public By addItemQantity1 = By.xpath("(//input[@type='textbox'])[1]");
	public By addItemQantity2 = By.xpath("(//input[@type='textbox'])[2]");
	public By addItemQantity3 = By.xpath("(//input[@type='textbox'])[3]");
	public By btnProceedToCreatePO = By.xpath("//button[@data-original-title='Proceed to Create PO']");
	public By COnfirmationYESforScantionInFuture = By
			.xpath("//span[text()='Do you want to use the sanction in future?']/../../../..//button[text()='Yes']");
	public By POReferenceNo = By.xpath("//label[text()='PO Reference No:']/../input");
	public By BtnCreatePO = By.xpath("//button[text()='Create PO']");
	public By POExpiryDate = By.xpath("//input[@id='po_header_temp.po_expiry_date.0']");

	public By POAmendmentflagOption(String val) {

		return By.xpath("//select[@name='po_amendment_flag']/option[text()='" + val + "']");
	}

	public By POAmendmentflag = By.xpath("//select[@name='po_amendment_flag']");
	public By PODetailsAttachmentTab = By.xpath("//a[text()='Attachments']");
	public By addattachments = By.xpath("(//button[@data-original-title='Add']/i)[3]");
	public By addlabelsattachment = By.xpath("//input[@name='label']");
	public By addnamesattachment = By.xpath("//input[@name='file_name']");
	public By addfileattachment = By.xpath("//input[@type='file']");
	public By POBidderDetails = By.xpath("//a[text()='Bidder Details']");
	public By POItemDetails = By.xpath("//a[text()='Item Details']");
	public By VLegacyCodeItem1 = By.xpath("(//input[@name='v_legacy_code'])[1]");
	public By VLegacyCodeItem2 = By.xpath("(//input[@name='v_legacy_code'])[2]");
	public By VLegacyCodeItem3 = By.xpath("(//input[@name='v_legacy_code'])[3]");
	public By POTermsandCond = By.xpath("//a[text()='Terms & Conditions']");
	public By POclauseNo = By.xpath("//input[@name='clause_no']");
	public By POclauseName = By.xpath("//input[@name='clause_name']");
	public By POclauseDetails = By.xpath("//input[@name='clause_details']");
	public By POSave = By.xpath("//button[@data-original-title='Save']/i");
	public By POSUccessMsg = By.xpath("//button[text()='OK']");

	public By ApprovalNotReqd = By.xpath("(//input[@id='appNo'])[2]");
	public By ApprovalNotReqd_ReleasePO = By.xpath("(//input[@id='appNo'])[1]");
	public By ApprovalNotReqd_CancelPO = By.xpath("(//input[@id='appNo'])[1]");
	public By SubmitApproval = By.xpath("(//button[normalize-space(text())='Submit'])[2]");

	// venkatesh
	public By poListingLinkBy = By.xpath("//a[contains(normalize-space(text()),'PO Listing')]");

	public By poListPageRowBy = By.xpath("//*[@id='usrmntbLst']/tbody/tr[2]");

	public By acceptPOLinkBy = By.xpath("//*[@id='usrmntbLst']/tbody/tr/td/div/ul/li/a/i[@class='fa fa-check']");

	public By poDeatailsTabBy = By.xpath("//*[contains(text(),'PO Details')]");

	public By aacceptPOCommentTabBy = By
			.xpath("//*[@aria-controls='profile' and contains(normalize-space(text()),'Comment')]");

	public By poObjCommentBy = By.xpath("//*[@ng-model='poObj.comment']");

	public By poAcceptBtnBy = By.xpath("//*[@ng-click='acceptPo()']/i");

	public By acceptPurchaseOrderConfirmPopYesBtnBy = By.xpath("//button[@data-bb-handler='confirm']");

	public By acceptPurchaseOrderConfirmPopOKBtnBy = By.xpath("//*[@data-bb-handler='ok']");

	public By poStatusAcceptedBy = By.xpath("//*[@id='usrmntbLst']/tbody/tr/td[normalize-space(text())='Accepted']");

	// pooja
	public By supplierQuotedQuantity1_TCS = By
			.xpath("(//a[contains(text(),'TCS')])[1]/../../..//input[@id='qi_boq.supquotequant.0']");
	public By supplierQuotedQuantity2_TCS = By
			.xpath("(//a[contains(text(),'TCS')])[1]/../../..//input[@id='qi_boq.supquotequant.1']");
	public By supplierQuotedQuantity3_TCS = By
			.xpath("(//a[contains(text(),'TCS')])[1]/../../..//input[@id='qi_boq.supquotequant.2']");
	public By supplierQuotedQuantity1_CTS = By
			.xpath("(//a[contains(text(),'CTS')])[1]/../../..//input[@id='qi_boq.supquotequant.0']");
	public By supplierQuotedQuantity2_CTS = By
			.xpath("(//a[contains(text(),'CTS')])[1]/../../..//input[@id='qi_boq.supquotequant.1']");
	public By supplierQuotedQuantity3_CTS = By
			.xpath("(//a[contains(text(),'CTS')])[1]/../../..//input[@id='qi_boq.supquotequant.2']");

	public By dashboard = By.xpath("//i[@class='fa fa-laptop']");
	public By purchase_Order = By.xpath("//i[@class='fa fa-pencil-square-o title_icon']");
	public By createPOLink = By.xpath(
			"//a[@ng-click='redirectToItemVendorSelectionPage(sn.sanctionId, selectedSource, sn.sanctionGroupId)']");
	public By selectTemplateGroup = By.xpath("//select[@ng-model='selectedTemplateGroupId']");
	public By selectBidder = By.xpath("//select[@ng-model='selectedVendorForSnObj.selectedVendorForSn']");
	public By bidderOrganisationName = By.xpath("//th[contains(text(),'Bidder Organization Name')]");
	public By vendorName = By.xpath("//th[contains(text(),'Vendor Name')]");
	public By createPOButton = By.xpath("//button[@data-original-title='Create PO']");
	public By columnVisibility = By.xpath("//button[contains(@data-original-title,'Column Visibility')]");
	public By myPO = By.xpath("//a[text()='My PO']");
	public By purchaseOrderNavigtion = By.xpath("//span[text()='Purchase Order (PO)']");
	public By poListingNavigation = By.xpath("//a[contains(text(),'PO Listing')]");
	public By poSNo = By.xpath("(//table[@id='usrmntbLst']/tbody/tr/th[text()='S.No'])[1]");
	public By poNoRefNo = By.xpath("(//th[contains(text(),'PO No (Ref No)')])[1]");
	public By prRfqSn = By
			.xpath("(//th[contains(text(),'PR') or contains(text(),'RFQ') or contains(text(),'SN No')])[1]");
	public By poType = By.xpath("(//th[contains(text(),'PO Type')])[1]");
	public By poCategory = By.xpath("(//th[contains(text(),'PO Category')])[1]");
	public By items = By.xpath("(//th[contains(text(),'Item(s)')])[1]");
	public By vendorOrSupplierName = By.xpath("(//th[contains(text(),'Vendor/Supplier Name')])[1]");
	public By poValue = By.xpath("(//th[contains(text(),'PO Value')])[1]");
	public By creationDate = By.xpath("(//th[contains(text(),'Creation Date')])[1]");
	public By validity = By.xpath("(//th[contains(text(),'Validity')])[1]");
	public By source = By.xpath("(//th[contains(text(),'Source')])[1]");
	public By catalogue = By.xpath("(//th[contains(text(),'Used in Catalogue')])[1]");
	public By status1 = By.xpath("(//th[contains(text(),'Status')])[1]");
	public By action1 = By.xpath("(//th[contains(text(),'Action')])[1]");
	public By purchaseOrderNavigtionIcon = By.xpath("//i[@class='fa fa-cart-arrow-down']");
	public By editPO = By.xpath("//a[text()=' Edit PO']");
	public By listDropdown = By.xpath("(//ul[@class='dropdown-menu extended logout big_actv'])[1]");
	public By cancelledPO = By.xpath("//a[text()='Cancelled PO']");
	public By action27 = By.xpath("(//button[@id='menu1'])[27]");
	public By poNoRefNo1 = By.xpath("//table[@id='usrmntbLst']/tbody/tr[2]/td[2]");
	public By poNoRefNo2 = By.xpath("(//table[@id='usrmntbLst']/tbody/tr[2]/td[2])[2]");
	public By poNoRefNo3 = By.xpath("(//table[@id='usrmntbLst']/tbody/tr[2]/td[2])[3]");
	public By othersPO = By.xpath("//a[contains(text(),'Other')]");
	public By releasePODropDown = By.xpath("//span[contains(text(),'Release PO')]");
	public By releasePOButton = By.xpath("//button[contains(text(),'Release PO')]");

	public By POclauseNo(int i) {
		return By.xpath("(//input[@name='clause_no'])[" + i + "]");
	}

	public By POclauseName(int i) {
		return By.xpath("(//input[@name='clause_name'])[" + i + "]");
	}

	public By POclauseDetails(int i) {
		return By.xpath("(//input[@name='clause_details'])[" + i + "]");
	}

	public By poNum(String num) {
		return By.xpath("//span[text()='(" + num + ")']");
	}

	public By downloadbtndateschedule = By.xpath("//button[@data-original-title='Download']");
	public By downloadbtntermsandcond = By.xpath("(//button[@data-original-title='Download'])[2]");
	public By downloadbtntechnical = By.xpath("(//button[@data-original-title='Download'])[3]");
	public By downloadbtnboqsummary = By.xpath("(//button[@data-original-title='Download'])[4]");
	// public By uploadbtn = By.xpath("//a[@data-original-title='Upload']");
	public By uploadbtn = By.xpath("//input[@upload-event='uploadTabData']");
	public By Okbtn = By.xpath("//button[text()='OK']");
	public By uploadbtntermsandcond = By.xpath("(//input[@upload-event='uploadTabData'])[2]");
	public By uploadbtntechnical = By.xpath("(//input[@upload-event='uploadTabData'])[3]");
	public By uploadbtnboqsummary = By.xpath("(//input[@upload-event='uploadTabData'])[4]");
	public By biddingcomp = By.xpath("//span[text()='TCS']/../../td/span/label/span");
	public By addbtnvendor = By.xpath("//button[@id='addVendor']");
	public By UploadExcelSheet = By.xpath("//a[@data-original-title='Upload Tender Document']/input");

	public By previewtendertype(String option) {
		return By.xpath("//label[contains(text(),'" + option + "')]");

	}

	public By sectionsdownloadinExcel = By.xpath("(//button[@data-toggle='dropdown'])[2]");
	public By DownloadExcelFormat = By.xpath("//button[@data-original-title='Download Template Format']");

	public By excelBidding = By.xpath("(//button[@class='btn btn-default dropdown-toggle'])[1]");
	public By biddownloadinExcel = By.xpath("(//button[@class='btn btn-primary'])[3]");
	public By bidSubmissionUpload = By.xpath(".//input[@id='excel-bidding-upload']");
	public By valBidStartDate = By.xpath("//*[@id='myTable']/tbody/tr/td[6]");
	public By valBidDueDate = By.xpath("//*[@id='myTable']/tbody/tr/td[7]");
	public By selectallBidder = By.xpath("//*[@id='vendorLst']/tbody/tr[1]/th[1]/span/label/span");
	public By cancelPO = By.xpath("//a[@ng-click='cancelPo(data)']");
	public By cancellationReason = By.xpath("//textarea[@name='approvalComment']");
	public By Userdefined_po1 = By.xpath("(//input[@id='appYes'][@value='U'])[1]");
	public By pendingForCancellationApproval = By.xpath("//td[contains(text(),'Pending For Cancellation Approval')]");
	public By viewPO = By.xpath("//li[@ng-click='viewPoPreview(data)']");
	public By poDetails = By.xpath("//h3[contains(text(),'PO Detail')]");
	public By poNo = By.xpath("//b[contains(text(),'PO No')]");
	public By poRefNo = By.xpath("//b[contains(text(),'PO Ref No')]");
	public By dashboardIcon1 = By.xpath("//i[@class='fa fa-laptop']");
	public By statusFilter = By.xpath("//select[@ng-model='poStatusFilter']");
	public By editPOLink = By.xpath("//li[@ng-click='viewPo(data.epspoId,data.poGroupId)']");
	public By mandatoryFieldAlert = By.xpath("//div[contains(text(),'All Mandatory fields are required')]");
	public By OtherPOBy = By
			.xpath("//*[@active='active']/child::ul/li/a[contains(text(), 'My PO')]/../following-sibling::li/a");

	public By PredifinedApprovalTypeBy = By
			.xpath("//*[@id='approvalModal']//child::div//form/child::div//input[@id='appPre'][@value='P']");

	public By commentFieldBy = By.xpath("//*[@name='approvalComment']");

	public By poSaveSuucessMsgPopUpBy = By.xpath("//*[contains(text(),'PO saved successfully.')]");

	public By poDocumentNoBy = By
			.xpath("(//form/table/tbody/tr/td[contains(text(),'System Po Number')]/following-sibling::td)[1]");

	public By poAcceptMsgPopUpBy = By.xpath("//*[text()='Purchase order successfully accepted.']");

	public By closeBtnBy = By.xpath("//div[contains(@id,'tenderApprovalModal')]//div[@class='modal-footer']/button[@ng-click='closeApprovalModal()']");
	public By closeBtnBy_PO = By.xpath("//div[contains(@id,'approvalModal')]//div[@class='modal-footer']/button[@ng-click='closeApprovalModal()']");

	public By editPOLinkBy = By.xpath("//li[@ng-click='viewPo(data.epspoId,data.poGroupId)']");

	public By poNoFieldBy = By.xpath("//*[@placeholder='PO No' and @disabled='disabled']");

	public By poCreatorNameFieldBy = By.xpath("//*[@placeholder='PO Creator Name' and @disabled='disabled']");

	public By uiTypeFieldBy = By.xpath("//*[@id='po_vendor_temp.uid_type.0']");

	public By unitPriceFieldBy = By.xpath("//*[@placeholder='Unit Price/Rate']");

	public By bidderDetailTabBy = By.xpath("//a[text()='Bidder Details']");
	public By Userdefined_po = By.xpath("(//input[@id='appYes'][@value='U'])[2]");
	public By approvalType_Sequential = By.xpath("//input[@id='dvDes'][@value='S']");
	public By addButton = By.xpath("//button[@ng-click='addRowForApproverAppend()']");
	public By approvalConfirmPopUp = By.xpath("//div[contains(text(),'Status updated successfully!')]");
	public By confirmOk = By.xpath("//button[contains(text(),'OK')]");
	public By poStatusFilter = By.xpath("//*[@ng-model='poStatusFilter']");
	public By poCategoryBy = By.xpath("//*[@ng-model='poCategoryFilter']");
	public By poSource = By.xpath("//*[@ng-model='filter.poSourceFilter']");
	public By draftStateStatus = By.xpath("//*[@id='usrmntbLst']/tbody/tr/td[contains(text(),'Draft')]");
	public By AcceptedStateStatus = By.xpath("//*[@id='usrmntbLst']/tbody/tr/td[contains(text(),'Accepted')]");
	public By PendingForAcceptanceStateStatus = By
			.xpath("//*[@id='usrmntbLst']/tbody/tr/td[contains(normalize-space(text()),'Pending for Acceptance')]");
	public By PendingForApprovalStateStatus = By
			.xpath("//*[@id='usrmntbLst']/tbody/tr/td[contains(normalize-space(text()),'Pending For Approval')]");
	public By cancelledPoSucessesPopUp = By.xpath("//div[contains(text(),'PO cancelled successfully!')]");
	public By cancelledPoTab = By.xpath("//a[contains(text(),'Cancelled PO')]");
	public By PendingForCancellationApprovalBy = By
			.xpath("//tbody/tr[2]/td[contains(text(),'Pending For Cancellation Approval')]");

	public By cancelPurchaseOrderTabBy = By.xpath("//*[contains(text(),'Cancel Purchase Order')]");

	public By PurchaseOrderTabBy = By.xpath("//*[@aria-controls='contact' and text()='Purchase Order']");

	public By PoSearchBy = By.xpath("//input[@ng-model='obj.poSearchText']");
	public By CancelledStatusBy = By.xpath("//*[@id='contact']/div/table/tbody/tr/td[contains(text(),'CANCELLED')]");
	public By selectStatus = By.xpath("//select[@ng-model='poStatusFilter']");
	public By repeatOrder = By.xpath("//a[text()='Repeat Order']");
	public By refText = By
			.xpath("//div[contains(text(),'Repeat Order has been successfully completed with Purchase Order Number')]");
	public By alertCancel = By.xpath("//div[text()='Please provide cancellation Reason first.']");
	public By poNoRefNo1Span = By.xpath("//table[@id='usrmntbLst']/tbody/tr[2]/td[2]/span");
	public By cancellationComment = By.xpath("//table[@id='usrmntbLst']/tbody/tr[2]/td[7]");
	public By searchsactionIdinPOPOButton = By.xpath("//input[@ng-model='searchFilter']");
	public By ViewPO = By.xpath("//a[text()='View PO']");
	public By View_PO_ref_NUm = By.xpath("//b[text()='PO Ref No']/../../..//span");
	public By View_PO_NUm = By.xpath("//b[text()='PO No']/../../..//span");
	public By View_PO_Value = By.xpath("//b[text()='PO Value']/../../..//span");
	public By View_PO_Amendment_flag = By.xpath("//b[text()='PO Amendment flag']/../../..//span");
	public By View_PO_Start_Date = By.xpath("(//b[text()='PO start date']/../../..//label)[2]");
	public By View_PO_Expiry_Date = By.xpath("(//b[text()='PO Expiry date']/../../..//label)[2]");
	public By View_Sanction_Note_Num = By.xpath("(//b[text()='Sanction Note No']/../../..//label)[2]");
	public By View_PO_Quotation_Code = By.xpath("(//b[text()='Quotation Reference Code']/../../..//label)[2]");
	public By View_PO_Supplier_Organization_Name = By
			.xpath("(//b[text()='Supplier Organization Name']/../../..//label)[2]");
	public By View_PO_CloseBtn = By.xpath("//button[@id='btn-po-print']/../button[text()='Close']");

	public By EditPO = By.xpath("//a[text()=' Edit PO']");
	public By Cancelbtn = By.xpath("//button[@data-original-title='Cancel']");
	public By PODeailspage = By.xpath("//i[@class='fa fa-cubes icon_document_alt']");
	public By POListingpage = By.xpath("//i[@class='fa fa-pencil-square-o title_icon']");

	public By btnReleasePO = By.xpath("//button[text()='Release PO']");

	public By ApprovalPreDefined = By.xpath("(//input[@id='appPre'])[2]");
	public By UserDefined = By.xpath("(//input[@id='appYes'])[2]");

	public By SearchPO = By.xpath("//input[@ng-model='poFilter']");

	public By AmendPo = By.xpath("//a[@data-target='#amendModal']");
	public By AmendPoComment = By.xpath("//textarea[@ng-model='amendComment']");
	public By AmendPoSubmitbtn = By.xpath("(//button[text()='Submit'])[2]");
	public By AmendSuccessmsg = By.xpath("//div[@class='bootbox-body']");
	public By AmendMsgOKbutton = By.xpath("//button[text()='OK']");

	public By ReleasePO = By.xpath("//span[text()='Release PO']");
	public By btnReleasePo = By.xpath("//button[text()='Release PO']");
	public By approvnotreq = By.xpath("(//input[@id='appNo'])[1]");
	public By subapproval = By.xpath("(//button[normalize-space(text())='Submit'])[4]");

	public By POCancel = By.xpath("//a[@ng-click='cancelPo(data)']");
	public By PoCancelMsg = By.xpath("//textarea[@name='approvalComment']");
	public By Cancelsubmitbtn = By.xpath("//button[@ng-click='submit()']");
	public By CancelledPOTab = By.xpath("//a[text()='Cancelled PO']");

	public By Recall = By.xpath("//a[@data-target='#recallModal']");
	public By RecallReason = By.xpath("//textarea[@name='recallReason']");
	public By RecallSubmit = By.xpath("//button[@ng-click='recallFn()']");
	public By recallPo = By.xpath("//a[@ng-click='openRecallModal(data.epspoId)']");
	public By recallPoCloseButton = By.xpath("//button[contains(text(),'x')]");
	public By rejectPOLinkBy = By.xpath("//*[@id='usrmntbLst']/tbody/tr/td/div/ul/li/a/i[@class='fa fa-ban']");
	public By poRejectBtnBy = By.xpath("//*[@ng-click='rejectPo()']/i");
	public By poStatusRejectedBy = By.xpath("//*[@id='usrmntbLst']/tbody/tr/td[normalize-space(text())='Rejected']");
	public By btnSendforapproval = By.xpath("//button[@data-original-title='Send For Approval']");
	public By approvalType_Parallel = By.xpath("//input[@id='dvEnb'][@value='P']");
	public By minimumApproval = By.xpath("//input[@id='sequanTxt']");
	public By user3 = By.xpath("//table[@id='approver']/tbody/tr[3]/td[2]/input");
	public By coordinator3 = By.xpath("(//input[@ng-click='setCoordinator($index)'])[3]");
	public By cancelledPoSucessesPopUpApprover = By
			.xpath("//div[contains(text(),'PO Cancellation WF sent successfully!')]");
	public By cancelPurchaseOrder = By.xpath("//a[contains(text(),'Cancel Purchase Order')]");
	public By podetails = By.xpath("//div[contains(text(),'PO Details')]");
	public By Review = By.xpath("//i[@class='fa fa-arrow-left']");
	public By SelectSource = By.xpath("//select/option[text()='--Select Source--']");
	public By SelectRQF = By
			.xpath("//select/option[text()='--Select Source--']/following-sibling::option[text()='RFQ']");
	public By SelectPR = By.xpath("//select/option[text()='--Select Source--']/following-sibling::option[text()='PR']");
	public By btnApprove = By.xpath("//button[@data-original-title='Approve']");
	public By btnReview = By.xpath("(//button[@data-original-title='Approve']/../following-sibling::a)[1]");
	public By btnAbranch = By.xpath("(//button[@data-original-title='Approve']/../following-sibling::a)[2]");
	public By btndownload = By.xpath("(//button[@data-original-title='Download'])[1]");
	public By headbtns = By.xpath("//div[@class='marg_neg_round pull-right']");
	public By BranchClosebtn = By.xpath("//button[normalize-space(text())='Submit']/../button[text()='Close']");
	public By PrintPreview = By.xpath("(//a[text()='Print Preview'])[1]");
	public By MYPO = By.xpath("//a[text()='My PO']");
	public By LoadingBy = By.xpath("//div[@id='loading']");

	public By SummaryTab = By.xpath("//a[text()='Summary']");
	public By View_PO_Num = By.xpath("(//b[text()='PO No']/../../..//span)[1]");
	public By View_PO_VAlue = By.xpath("(//b[text()='PO Value']/../../..//span)[1]");
	public By View_PO_Amendment_Flag = By.xpath("(//b[text()='PO Amendment flag']/../../..//span)[1]");
	public By View_PO_ref_NUM = By.xpath("(//b[text()='PO Ref No']/../../..//span)[1]");
	// Prasad
	public By SNo = By.xpath("//table[@id='usrmntbLst']/tbody/tr[1]/th[1]");
	public By PRRFQSNNo = By.xpath("//table[@id='usrmntbLst']/tbody/tr[1]/th[2]");
	public By PONo = By.xpath("//table[@id='usrmntbLst']/tbody/tr[1]/th[3]");
	public By POCreationDate = By.xpath("//table[@id='usrmntbLst']/tbody/tr[1]/th[5]");
	public By CancelledPOValue = By.xpath("//table[@id='usrmntbLst']/tbody/tr[1]/th[6]");
	public By CancelledAction = By.xpath("//table[@id='usrmntbLst']/tbody/tr[1]/th[10]");
	public By cancellationDate = By.xpath("//table[@id='usrmntbLst']/tbody/tr[1]/th[9]");
	public By usedInCatalogue = By.xpath("//table[@id='usrmntbLst']/tbody/tr[1]/th[8]");
	public By viewPoPreview = By.xpath("//*[@ng-click='viewPoPreview(data)']");
	public By viewPoForprint = By.xpath("//*[@ng-click='viewPoForprint(data)']");
	public By ApprovalHistory = By.xpath("//*[@ng-click='loadApproversComments(data.epspoId,data.poStatus)']");
	public By poTypeFilter = By.xpath("//*[@ng-model='poTypeFilter']");
	public By usedCatalogueFilter = By.xpath("//*[@ng-model='usedCatalogueFilter']");
	public By fullyUsed = By.xpath("//select[@ng-model='usedCatalogueFilter']/option[@label='Fully Used']");
	public By notUsed = By.xpath("//select[@ng-model='usedCatalogueFilter']/option[@label='Not Used']");
	public By partiallyUsed = By.xpath("//select[@ng-model='usedCatalogueFilter']/option[@label='Partially Used']");
	public By approvalTypeDropdownBy1 = By.xpath("(//*[@ng-model='user.approvalType'])[2]");
	public By approvalTypeDropdownBy2 = By.xpath("(//*[@ng-model='user.approvalType'])[3]");
	public By coordinatorFlagParallel = By
			.xpath("//table[@class='table table-striped table-advance table-hover']/tbody/tr[1]/td[3]//input");
	public By minApprover1Parallel = By
			.xpath("//table[@class='table table-striped table-advance table-hover']/tbody/tr[1]/td[4]//input");
	public By minApprover1Parallel2 = By
			.xpath("//table[@class='table table-striped table-advance table-hover']/tbody/tr[2]/td[4]//input");
	public By minApprover1Parallel3 = By
			.xpath("//table[@class='table table-striped table-advance table-hover']/tbody/tr[3]/td[4]//input");

	public By PoApproveDetails(int i) {
		return By.xpath("(//table[@id='myTable']//tr[@class='ng-scope']/td)[" + i + "]");
	}

	public By sendForApprovalClose = By.xpath("(//button[@ng-click='closeApprovalModal()'])[4]");
	public By preview = By.xpath("(//*[@id='usrmntbLst']/tbody/tr/td/div/ul/li/a/i[@class='fa fa-eye'])[1]");
	public By printPreview = By.xpath("(//*[@id='usrmntbLst']/tbody/tr/td/div/ul/li/a/i[@class='fa fa-eye'])[2]");
	public By poExpiryDateAlert = By
			.xpath("//div[contains(text(),'PO Expiry Date should be greater than or equal to current date!')]");
	public By PreDefinedApproversListBy = By.xpath("//*[@id='menusndApprovl']/div/table/tbody/tr");

	public By PoCancelleddateValueBy = By.xpath("(//table[@id='usrmntbLst'])[3]//tbody/tr/td[9]");
	public By predefined = By.xpath("//input[@id='appPre']");
	public By documentNumber1 = By.xpath("//form[@name='approvalForm']/table//tr/td[2]/text()");
	public By POApprovalAlertForExpiredPO = By.xpath(
			"//div[contains(text(),'PO validity is expired. Hence PO can not be approved. Please send back the PO to the initiator')]");
	public By settings = By.xpath("//span[contains(text(),'Settings')]");
	public By configurationManagement = By.xpath("//a[contains(text(),'Configuration Management')]");
	public By purchaseOrderManagement = By.xpath("//a[contains(text(),'Purchase Order Management')]");
	public By defaultPOType = By.xpath("//strong[contains(text(),'Default PO Type')]");
	public By defaultPOTypeDropdown = By.xpath("//select[@ng-model='defaultPoType']");
	public By rateContractDropdown = By.xpath("(//select[@ng-change='selectPoChange()'])[1]");
	public By blanketDropdown = By.xpath("(//select[@ng-change='selectPoChange()'])[2]");
	public By plannedDropdown = By.xpath("(//select[@ng-change='selectPoChange()'])[3]");
	public By standardDropdown = By.xpath("(//select[@ng-change='selectPoChange()'])[4]");
	public By configurationManagementSave = By.xpath("(//a[@data-original-title='Save'])[8]");
	public By purchaseOrderConfigurationSucessesMessage = By
			.xpath("//div[text()='Purchase Order configuration has been successfully updated']");
	public By po_Type = By.xpath("//select[@name='po_type']");
	public By selectPOType = By.xpath("//select[@ng-model='poTypeFilter']");
	public By bidder = By.xpath("//table[@id='usrmntbLst']/tbody/tr[2]/td[7]");
	public By poStatusCancelled = By.xpath("//*[@id='usrmntbLst']/tbody/tr/td[text()='Cancelled ']");
	// public By details1 = By.xpath("(//a[contains(text(),'Details')])[2]");
	public By comment1 = By.xpath("//a[text()='Comment']");
	public By poDetails1 = By.xpath("//div[text()='PO Details']");
	public By negotiatedStatus = By.xpath("//label[contains(text(),'Negotiated')]");

	public By POTemplateGroup = By.xpath("//select[@ng-model='selectedTemplateGroupId']");
	public By Snipper = By.xpath("//div[@style='display: block;']/..//div[@class='spinner']");
	public By coordinator = By
			.xpath("//input[@type='radio' and @ng-click='radioCheckUncheck(row, $event)' and not(@checked='checked')]");
	public By approverType2_p = By.xpath("(//select[@id='filterTest'])[2]");

	public By drpdwn(String id) {
		return By.xpath("(//td[text()='" + id + "']/following-sibling::td)[6]//button[@id='menu1']");
	}

	public By VIEW(String id) {
		return By.xpath("(//td[text()='" + id + "']/following-sibling::td)[6]//a[@ng-click='viewModal(sanction)']");
	}

	public By dwnloadSanctionReport(String id) {
		return By.xpath("(//td[text()='" + id
				+ "']/following-sibling::td)[6]//a[@ng-click='downloadSnReportModal(sanction.sanctionId)']");
	}

	public By btnRecall(String id) {
		return By.xpath("(//td[text()='" + id
				+ "']/following-sibling::td)[6]//a[@ng-click='openRecallModal(sanction.sanctionId)']");
	}

	public By approvdetailsvalidation(String id) {
		return By.xpath(
				"(//td[text()='" + id + "']/following-sibling::td)[6]//a[@ng-click='showApprovalDetails(sanction)']");
	}

	public By sendForApprovalNotRequired = By
			.xpath("//div[contains(@id,'tenderApprovalModal')]//form[contains(@class,'ng-valid')]//input[@id='appNo']/parent::label/input");
	public By sendForApprovalNotRequired_SN = By
			.xpath("//form[contains(@class,'ng-valid')]//input[@id='appNo']/parent::label/input");
	public By DOPstatus = By.xpath("//td[text()='pooja']/following-sibling::td[2]");

	public By AcceptedorRejected(String id) {
		return By.xpath("(//td[text()='" + id
				+ "']/following-sibling::td)[5]//button[@data-original-title='Accepted/Rejected']");
	}

	public By ApprovalPendingSN(String id) {
		return By.xpath("(//td[text()='" + id
				+ "']/following-sibling::td)[5]//button[@data-original-title='Pending for approval']");
	}

	public By DraftStatus(String id) {
		return By.xpath("(//td[text()='" + id + "']/following-sibling::td)[5]//button[@data-original-title='Draft']");
	}
	// precondition locators for SN
	// venkatesh

	public By TenderClauseField(int i) {
		return By.xpath("(//input[@placeholder='Clause'])[" + i + "]");
	}

	public By TenderClauseDetailsField(int i) {
		return By.xpath("(//textarea[@name='details'])[" + i + "]");
	}

	public By TenderTechnicalClauseField(int i) {
		return By.xpath("(//*[starts-with(@id, 'rh_techcompl.clause')])[" + i + "]");
	}

	public By TenderTechnicalDetailsField(int i) {
		return By.xpath("(//*[contains(@class, 'rh_techcompl.details')])[" + i + "]");
	}

	public By boqOptionalSupQuoteQuantBy = By.xpath("//*[@id='qi_boqoptional.supquotequant.0']");

	public By bidderSpecAttachmentBy = By.xpath("//*[text()='Bidder Specific Attachment']");

	public By bidderSpecAttachmentUploadBy = By.xpath(
			"//button[@class='btn btn-primary dropdown-toggle' and @data-btn-row='BidPart1']/following-sibling::ul/li/a");

	public By tenderAttachmentBy = By.xpath("//*[text()='Tender Attachment']");

	public By attachmentLabelFieldBy = By.xpath("//*[@id='authorsList']/tbody/tr[2]/td[1]/input");

	public By boqSuppQuoteQtyBy = By.xpath("//*[starts-with(@class,'form-control qi_boq.supquotequant')]");

	public By LiveTenderRecordsBy = By.xpath("(//*[@id='Live']//child::table//tbody/tr)[1]");

	public By technicalTabFileuploadEleBy = By.xpath("//*[@id='qh_techcompl-attach_file-0-download-icon']");

	public By termsAndConditionsClauseBy = By.xpath("//*[@id='_qh_termsnconditons']//table/thead/tr/th[2]");

	public By boqOptionalAddOptionalBy = By.xpath("//button[@data-target='#addOptionalItemModal_qi_boqoptional']");

	public By SpinnerHolderBy = By.xpath("//div[@id='spinnerholder']");

	public By disabledDecryptBidderDataBtnBy = By
			.xpath("//button[@ng-click='decryptBidderData()'][@disabled='disabled']");

	public By SpinnerHolderContainerIdTextBy = By
			.xpath("//*[@id='container']//div[@class='spinner-text ng-binding' and text()='Please Wait...']");

	public By excelBiddingFieldEleBy = By.xpath("//*[contains(text(),'Excel Bidding')]");

	public String editpencilIconEleBy = "//*[contains(normalize-space(text()),'{0}')]//following-sibling::td/button[contains(@ng-click,'editBidderStatus')]";

	public By itemCodeElement(int i) {
		return By.xpath("(//*[text()='Item Code'])[" + i + "]");
	}

	public By documentChargeFieldEleBy = By.xpath("//strong[contains(text(),'Packing & Documentation Charge ')]");

	public By body_overallcomment = By.xpath("//body[@id='tinymce']");
	public By Biddername = By.xpath("(//h5[@class='panel-title'])[1]/a");
	public By l1RankEleBy = By
			.xpath("//*[@id='approvedSupplier']/div//table/tbody/tr[1]/td/span[contains(text(),'L 1')]");

	public By ApproversupplierTabBy = By.xpath("//*[@aria-controls='approvedSupplier' or text()='Approved Supplier']");

	public By rejectedsupplierTabBy = By
			.xpath("//*[@aria-controls='rejectedSupplier' or text()='Rejected and Not-participated supplier']");

	public By rejectedsupplierBy = By.xpath("//*[@id='rejectedSupplier']/div/div/div/table/tbody/tr");

	public By sanctionNoteSummaryTabBy = By.xpath("//a[text()='Summary']");

	public By sanctionNotePrintViewTabBy = By.xpath("//a[text()='Print Preview']");

	public By sanctionAcceptedStageEleBy = By
			.xpath("//*[@class='btn btn-primary btn-circle btn-lg btn-lg-sub2 yellowBtn grnBtn']");

	public By SummaryTabCtsBy = By.xpath(
			"//span[contains(text(),'Bidder Wise List')]/../following-sibling::div/child::div//a[contains(text(),'CTS')]");

	public By SummaryTabTcsBy = By.xpath(
			"//span[contains(text(),'Bidder Wise List')]/../following-sibling::div/child::div//a[contains(text(),'TCS')]");
	public By approvalDetailsValidation = By
			.xpath("//table[@id]/tbody/tr[3][contains(@ng-repeat,'approval.approvalDetails')]");
	public By approvalDetailsWindow = By.xpath("//h3[text()='Approval Details']");
	public By sendForApproval_Close = By.xpath("(//button[@ng-click='closeApprovalModal()'])[2]");

	public By itemQuantity1 = By.xpath("(//input[@name='item_qty'])[1]");
	public By itemQuantity2 = By.xpath("(//input[@name='item_qty'])[2]");
	public By itemQuantity3 = By.xpath("(//input[@name='item_qty'])[3]");

	public By OPOSNO = By.xpath("(//table[@id='usrmntbLst']/tbody/tr[1]/th[text()='S.No'])[2]");
	public By OpoNoRefNo = By.xpath("(//th[contains(text(),'PO No (Ref No)')])[2]");
	public By OprRfqSn = By
			.xpath("(//th[contains(text(),'PR') or contains(text(),'RFQ') or contains(text(),'SN No')])[2]");
	public By OpoType = By.xpath("(//th[contains(text(),'PO Type')])[2]");
	public By OpoCategory = By.xpath("(//th[contains(text(),'PO Category')])[2]");
	public By Oitems = By.xpath("(//th[contains(text(),'Item(s)')])[2]");
	public By OvendorOrSupplierName = By.xpath("(//th[contains(text(),'Vendor/Supplier Name')])[2]");
	public By OpoValue = By.xpath("(//th[contains(text(),'PO Value')])[2]");
	public By OcreationDate = By.xpath("(//th[contains(text(),'Creation Date')])[2]");
	public By Ovalidity = By.xpath("(//th[contains(text(),'Validity')])[2]");
	public By Osource = By.xpath("(//th[contains(text(),'Source')])[2]");
	public By Ocatalogue = By.xpath("(//th[contains(text(),'Used in Catalogue')])[2]");
	public By Ostatus = By.xpath("(//th[contains(text(),'Status')])[2]");
	public By Oaction = By.xpath("(//th[contains(text(),'Action')])[2]");

	public By DOPstatusPO(String DOP) {
		return By.xpath("//td[text()='" + DOP + "']/following-sibling::td[2]");
	}

	public By selectDopStatusBy = By.xpath("//*[@ng-model='dop.status']");

	public By headerInformation = By.xpath("(//span[@class='ng-binding'])[2]");
	public By shippingInformation = By.xpath("(//span[@class='ng-binding'])[3]");

	public By Warningmsg = By.xpath("//div[@class='bootbox-body']");
	public By Acceptwarinigmsg = By.xpath("//button[@data-bb-handler='ok']");
	public By cancelledPOAction = By.xpath(
			"//li[contains(@class,'ng-isolate-scope active')]/..//following-sibling::div//*[@id='usrmntbLst']/tbody/tr[2]/td[10]/div/button");

	public By cancelledPOActionDropdownElementsBy = By.xpath(
			"//li[contains(@class,'ng-isolate-scope active')]/..//following-sibling::div//*[@id='usrmntbLst']/tbody/tr[2]/td[10]/div/button/following-sibling::ul");

	public By otherPoActionMenuBy = By.xpath(
			"//li[contains(@class,'ng-isolate-scope active')]/..//following-sibling::div//*[@id='usrmntbLst']/tbody/tr[2]/td[14]/div/button");

	public By otherPoActionMenuElementsBy = By.xpath(
			"//li[contains(@class,'ng-isolate-scope active')]/..//following-sibling::div//*[@id='usrmntbLst']/tbody/tr[2]/td[14]/div/button/following-sibling::ul");
	public By addTermsAndCondition = By
			.xpath("(//button[@ng-click='addRowForDynamicTabularTemplate(templateShortName)'])[2]");
	public By SummaryTabBidderDetailsBy = By.xpath("//div[contains(text(),'Bidder Details')]");

	public By SummaryTabItemDetailsBy = By.xpath("//div[contains(text(),'Item Details')]");

	public By SummaryTabTermsAndCondDetailsBy = By.xpath("//div[contains(text(),'Terms and Conditions')]");

	public By itemcodeQty(int no) {
		return By.xpath("//*[@id='preview']/div/div[4]/table/tbody/tr[" + no + "]/td[4]");
	}

	public By termsClauseNames(int no) {
		return By.xpath("//*[@id='preview']/div/div[5]/table/tbody/tr[" + no + "]/td[2]");
	}

	public By ApprovalCommentHistoryBtnBy = By.xpath("//*[@ng-show='approvalCollapse']/child::i");

	public By userNameColumnSeqBy = By.xpath("//*[@id='reloadMe']/table/tbody/tr/td[position()=1]/span");

	public By userNameStatusSeqBy = By.xpath("//*[@id='reloadMe']/table/tbody/tr/td[4]");

	public By CommentColumnSeqBy = By.xpath("//*[@id='reloadMe']/table/tbody/tr/td[5]");
	public By POStatus = By
			.xpath("//*[@id='usrmntbLst']/tbody/tr/td[contains(normalize-space(text()),'Pending For Approval')]");
	public By repeatordernotexpired = By.xpath(
			"//span[contains(@time-countdown,'data.poExpiryDt') and (contains(text(),'days'))]/..//following-sibling::td//button[@data-toggle='dropdown']");
	public By btnrepeatorder = By.xpath(
			"(//span[contains(@time-countdown,'data.poExpiryDt') and (contains(text(),'days'))]/..//following-sibling::td//button[@data-toggle='dropdown'])[1]/following-sibling::ul//li//a[text()='Repeat Order']");
	public By btnBranch = By.xpath("(//button[@data-original-title='Approve']/../following-sibling::a)[2]/button");

	public By BranchUSer1Btn(String username) {
		return By.xpath("//span[text()='" + username + "']/../following-sibling::td//i");
	}

	public By BranchUser1Remarks = By.xpath("//textarea[@ng-model='user.comment']");
	public By BranchBtnSubmit = By.xpath("//button[@id='btn-prevOk']");
	public By BranchSuccessMsg = By.xpath("//div[@class='bootbox-body']");
	public By BranchSuccesMsgClose = By.xpath("//button[text()='OK']");
	public By cancellationReasonAlert = By.xpath("//div[contains(text(),'Please provide cancellation Reason first')]");
	public By sendForApproval1 = By.xpath("//button[@ng-click='sendForApproval(approvalObj.workflowType)']");
	public By POCancelSuccesMsg = By.xpath("//div[contains(text(),'PO Cancellation WF sent successfully!')]");
	public By notRequiredClose = By.xpath("//button[@ng-click='submit()']");
	public By confirmDOPMSG = By.xpath("//button[text()='OK']");
	public By confirmDOP = By.xpath("//button[@data-bb-handler='confirm']");

	public By approvuserdefin = By.xpath("(//input[@name='chkApproval'])[2]");
	public By sequentialCancelorder = By.xpath("(//input[@name='sequanLabrd'])[1]");
	public By adduserapprove = By.xpath("//button[@id='addApprovertc']");
	public By approverList1 = By.xpath("//input[@list='approvaerList']");
	public By approverList2 = By.xpath("(//input[@list='approvaerList'])[2]");
	public By SendForAptovalbtn = By.xpath("//button[@id='dyantc']");
	public By branchUser = By.xpath("//h3[contains(text(),'Branch User')]");
	public By branchUpload = By.xpath("//input[@upload-event='branchUploadEvent']");
	public By sendBackButton = By
			.xpath("//button[@class='btn btn-icon btn-primary btn-circle btn-lg' or @data-original-title='Send Back']");
	public By sendBackSucessMsg = By.xpath("//div[contains(text(),'Send Back successfully completed.')]");
	public By branchSectionApproverComment = By.xpath("//strong[contains(text(),'Branch Section Approver Comment')]");
	public By sendBackToApproverStatus = By.xpath("//td[contains(text(),'Send Back To Approver')]");
	public By notRequired = By.xpath("//div[@class='radio-group workflowType']//label/input[@id='appNo']");
	public By itemQuantity01 = By.xpath("//input[@id='po_temp_v1.item_qty.0']");
	public By itemQuantity02 = By.xpath("//input[@id='po_temp_v1.item_qty.1']");
	public By itemQuantityalertMsgPopUp = By.xpath("//div[text()='Quantity not more than main quantity!']");
	public By removeBranchUser = By.xpath("//button[@class='btn btn-default btn-sm']");
	public By removeBranchUserSuccessfulMessage = By
			.xpath("//div[contains(text(),'Assigned user successfully removed.')]");
	public By minApproverRequired = By.xpath("//input[@id='sequanTxt']");
	public By parallelCoordinatorFlag = By.xpath("//table[@id='approver']/tbody/tr[2]//input[@name='coordinator']");
	public By minApproverAlert = By
			.xpath("//div[text()='Please enter minimum 2 approver for parallel type approval.']");
	public By approver1Comment = By.xpath("//*[@id='reloadMe']/table/tbody/tr[2]/td[position()=5]");
	public By POapproverComment = By.xpath("//*[@id='reloadMe']/table/tbody/tr[4]/td[position()=5]");
	public By approvalStatusPending = By.xpath("//option[text()='Pending']");
	public By approvalStatusCompleted = By.xpath("//option[text()='Completed']");
	public By StatusDraft = By.xpath("//td[normalize-space(text())='Draft']");
	public By StatusRejectedByapprover = By.xpath("//a[text()='(Rejected by approver)']");
	public By PrintPreviewClose = By.xpath("//div[@id='poPrintPrevModal']//button[text()='Close']");
	public By ItemQtyPopUpValidationBy = By.xpath("//*[text()='Quantity not more than main quantity!']");

	public By reverseBackApproverBy = By.xpath(" //*[contains(text(),' Reverse back to approver')]");
	public By coordinatorFlagParallel_vamshi = By
			.xpath("//table[@class='table table-striped table-advance table-hover']/tbody/tr[3]/td[3]//input");

	// ****************************Auction
	// locators********************************
	public By btnParticipate = By.xpath("//a[normalize-space(text())='Participate']");
	public By btnAuction = By.xpath("//span[text()='Auction']");
	public By tabPARTICiPATE = By.xpath("//a[normalize-space(text())='PARTICIPATE']");
	public By tabLIVE = By.xpath("//a[normalize-space(text())='LIVE']");

	public By BidSubmission = By.xpath("(//input[@type='text'])[1]");
	public By MarketPrice = By.xpath("//div[contains(text(),'Market Price')]/..");
	public By StartPrice = By.xpath("//div[contains(text(),'Start Price')]/..");
	public By Currency = By.xpath("//div[contains(text(),'Currency')]/..");
	public By Quantity = By.xpath("//div[contains(text(),'Quantity')]/..");
	public By MyLastBid = By.xpath("//div[contains(text(),'My Last Bid')]/..");
	public By Decrement = By.xpath("//div[contains(text(),'Decrement')]/..");
	public By auction = By.xpath("//span[contains(text(),'Auction')]");
	public By home = By.xpath("//a[contains(text(),'Home')]");
	public By postAuction = By.xpath("//span[contains(text(),'Post Auction')]");
	public By action_Dropdown = By.xpath("//div[@id='appId']//table//tbody//tr[1]//td[10]");
	public By releaseLink = By.xpath("//a[contains(text(),'Release')]");
	public By releasedAuction = By.xpath("//a[contains(text(),'RELEASED AUCTION')]");
	public By auctionSummaryReport = By.xpath("//a[contains(text(),'Auction Summary Report (PDF)')]");
	public By releaseConfirmYes = By.xpath("//button[contains(text(),'Yes')]");
	public By releaseConfirmSucessesMsgOk = By.xpath("//button[contains(text(),'OK')]");
	public By filter = By.xpath("//label[contains(text(),'FILTER')]");
	public By auctionSearch = By.xpath("//input[@id='generalSearch']");
	public By intiateAuctionMenuLinkBy = By.xpath("//*[contains(text(),'Initiate Auction')]");

	public By popupInitiateAuctonBtnBy = By
			.xpath("//*[@id='initiateAuction']//button[contains(text(),'Initiate auction')]");

	public By AuctionTypeSelectBy = By.xpath("//*[@name='auctionTypeId0']");

	public By AuctionTypeBiddingSelectBy = By.xpath("//*[@name='auctionBiddingTypeId0']");

	public By templateFieldSelectBy = By.xpath("//*[@name='templateField0']");

	public By itemCodeSelectBy = By.xpath("//*[@name='itemCode0']");

	public By itemCodeDescSelectBy = By.xpath("//*[@name='itemDescription0']");

	public By uomSelectBy = By.xpath("//*[@name='uom0']");

	public By QuantitySelectBy = By.xpath("//*[@name='qty0']");

	public By startBidPriceSelectBy = By.xpath("//*[@name='startBidPrice0']");

	public By viewEventBy = By.xpath("//button[text()='View Event']");

	public By setUpAction(int no) {
		return By.xpath("(//span[text()='Draft']/../following-sibling::td/child::button[text()='SET UP AUCTION '])["
				+ no + "]");
	}

	public By reserverevePriceFiledBy = By.xpath("//input[@id='reservePrice']");

	public By changePriceFiledBy = By.xpath("//input[@name='changePrice']");

	public By priceMultipleFiledBy = By.xpath("//select[@name='priceMultiple']");

	public By observersAddFiledBy = By.xpath("//*[@ng-click='openObserverModal();']/span/i[@class='la la-plus']");

	public By ObserversBy = By.xpath("//*[contains(text(),'TEST_AUTOMATION_SUPER_ADMIN')]");

	public By selectObserverAddBy = By.xpath("//*[@ng-click='selectObservers();']");

	public By saveScheduleBy = By.xpath("//*[contains(text(),'SAVE AND SCHEDULE')]");

	public By AuctionSavedSucessMsgBy = By.xpath("//*[text()='Auction has been saved successfully!']");

	public By publishEventBtnBy = By.xpath("//*[@id='publishEventId']");

	public By confirmationPublishEventYesBy = By.xpath(
			"//*[text()='Are you sure you want to publish the event?']/../following-sibling::div/child::button[text()='Yes']");

	public By sucessMsgForPublishEventByBy = By.xpath("//*[text()='Event has been published successfully!']");

	public By eventstartDate = By.xpath(
			"//span[text()='Draft']/..//preceding-sibling::td/child::div/child::input[@placeholder='Select start date & time']");

	public By eventEndDate = By.xpath(
			"//span[text()='Draft']/..//preceding-sibling::td/child::div/child::input[@placeholder='Select end date & time']");

	public By saveScheduleActivateBtnBy = By.xpath("//*[normalize-space(text())='SAVE SCHEDULE & ACTIVATE']");

	public By auctionactivatedSucessmsgBy = By
			.xpath("//*[normalize-space(text())='All Auctions have been activated successfully!']");

	public By AcivateStatusBy = By.xpath("//*[@id='scheduleListId']/tr/td[8]/span[text()='Active']");

	public By startDateTime = By.xpath(
			"//*[@id='scheduleListId']/tr/td[8]/span[text()='Active']/../preceding-sibling::td/input[@placeholder='Select start date & time']");

	public By endDateTime = By.xpath(
			"//*[@id='scheduleListId']/tr/td[8]/span[text()='Active']/../preceding-sibling::td/input[@placeholder='Select end date & time']");

	public By userpicBy = By.xpath("//*[@id='m_header_topbar']//span[@class='m-topbar__userpic']");

	public By LogoutBy = By.xpath("//*[@id='m_header_topbar']//ul/li/a[contains(text(),'Logout')]");

	public By yesForLogoutBy = By.xpath(
			"//*[text()='Are you sure you want to logout?']/../following-sibling::div/child::button[text()='Yes']");

	public By saveEventDetailsBy = By
			.xpath("//*[contains(text(),'SAVE AND SCHEDULE')]/following-sibling::button[contains(text(),'SAVE')]");

	public By backToEpsBy = By.xpath("//*[@id='m_ver_menu']//span[contains(text(),'Back to EPS')]");

	public By ConfirmYesForBackToEpsBy = By.xpath(
			"//*[text()='Are you sure you want to back to EPS?']/../following-sibling::div/child::button[text()='Yes']");

	public By EventMsgAlreadyExistBy = By.xpath("//*[text()='Event already exist!']");

	public By auctionRuleFiledBy = By.xpath("//select[@ng-model='auctionRule']");

	public By selectDraftCheckBox(int no) {
		return By.xpath("(//span[text()='Draft']/../preceding-sibling::td/child::input[@type='checkbox'])[" + no + "]");
	}

	public By ClickonStartDateField = By.xpath("//div[@id='m_datetimepicker_1_1']");
	public By todayStartdate = By.xpath("(//td[@class='day today active'])[1]");

	public By Currency(String price) {
		return By.xpath(" (//div[contains(text(),'Decrement')]/../../div[text()='" + price
				+ "']/../../preceding-sibling::div)[1]//div[text()='Currency:']/..");
	}

	public By BtnBackToEPS = By.xpath("(//span[normalize-space(text())='Back to EPS'])[2]");
	public By ConfirmBackYes = By.xpath("//button[text()='Yes']");

	public By Quantity(String price) {
		return By.xpath(" (//div[contains(text(),'Decrement')]/../../div[text()='" + price
				+ "']/../../preceding-sibling::div)[1]//div[text()='Quantity:']/..");
	}

	public By MyLastBid(String price) {
		return By.xpath("//div[contains(text(),'Decrement')]/../../div[text()='" + price
				+ "']/../../../following-sibling::div//span/input");
	}

	public By Decrement(String price) {
		return By.xpath("//div[contains(text(),'Decrement')]/../../div[text()='" + price + "']");
	}

	public By Submit(String price) {
		return By.xpath("//div[contains(text(),'Decrement')]/../../div[text()='" + price
				+ "']/../../../following-sibling::div//button[text()='Submit']");
	}

	public By SubsVal(String price) {
		return By.xpath("(//div[contains(text(),'Decrement')]/../../div[text()='" + price
				+ "']/../../../following-sibling::div//a)[2]");
	}

	public By startDate(String date) {
		return By.xpath("//div[text()='Start Time:']/../../div[text()='" + date + "']");
	}

	public By ActiveAuction(String date) {
		return By.xpath("//div[text()='Start Time:']/../../div[text()='" + date
				+ "']/following-sibling::div/button[text()='Accept']");

	}

	public By BidSubmission(String price) {
		return By.xpath("//div[contains(text(),'Decrement')]/../../div[text()='" + price
				+ "']/../../../following-sibling::div//span/input");
	}

	public By MarketPrice(String price) {
		return By.xpath("//div[contains(text(),'Decrement')]/../../div[text()='" + price + "']/following-sibling::div");
	}

	public By details(String price) {
		return By.xpath("(//div[contains(text(),'Decrement')]/../../div[text()='" + price
				+ "']/../../../following-sibling::div//button[contains(text(),'Detail')])");
	}

	public By price1 = By.xpath("//tbody[@id='auctionItemListId']//tr[1]//td[5]//input");
	public By price2 = By.xpath("//tbody[@id='auctionItemListId']//tr[2]//td[5]//input");
	public By price3 = By.xpath("//tbody[@id='auctionItemListId']//tr[3]//td[5]//input");
	public By calculateTotal = By.xpath("//button[@id='calculateBidValue']");
	public By submitBidValue = By.xpath("//button[@id='submitBidValue']");
	public By reservePriceValidationBy = By.xpath("//*[@ng-show='isReverseAuctionReservePriceValidation']");

	public By startPriceFiledBy = By.xpath("//*[@id='startPriceId']");

	public By user1auctionBy = By.xpath("//*[@id='auctionUserCheck_1']");

	public By user2auctionBy = By.xpath("//*[@id='auctionUserCheck_0']");
	public By viewButton = By.xpath("//button[contains(text(),'View')]");
	public By termsAndConditions = By
			.xpath("//div[@id='defaultTextAttchment']//span[text()='General Terms and Conditions']");
	public By closeTNC = By.xpath("//button[@id='closeDefaultTextViewModal']");

	public By actionMenuintiateAuctionlinkBy(String id) {
		return By.xpath("//td[contains(@title,'" + id
				+ "')]//parent::tr//td[7]//a[normalize-space(text())='Initiate auction']");
	}

	public By BidPrice(String price) {
		return By.xpath("(//div[contains(text(),'Decrement')]/../../div[text()='" + price
				+ "']/../../../following-sibling::div//span/input)[2]");
	}

	public By customizedTermsCondtionRadio = By.xpath("//input[@id='materialChecked2']");
	public By file_Name = By.xpath("//input[@id='fileName']");
	public By browseFile = By.xpath("//input[@id='file2']");
	public By uploadFile = By.xpath("//button[contains(text(),'Upload')]");
	public By uploadFileConfirmOk = By.xpath("//button[contains(text(),'OK')]");
	public By auctionListForController = By.xpath("//span[contains(text(),'Auction List for Controller')]");
	public By auctionList = By.xpath("//h3[contains(text(),'AUCTION LIST')]");
	public By observer = By.xpath("//a[contains(text(),'Observe')]");

	public By startPrice(String price) {
		return By.xpath("(//div[contains(text(),'Decrement')]/../../div[text()='" + price + "']/../div)[1]");
	}

	public By AuctionListForController = By.xpath(" //span[normalize-space(text())='Auction List for Controller']");

	public By StopAuction(String price) {
		return By.xpath("//div[contains(text(),'Decrement')]/../../div[text()='" + price
				+ "']/../../../following-sibling::div[2]//a[normalize-space(text())='Stop']");
	}

	public By Confirmationmessagepopup = By.xpath("//h2[@id='swal2-title']");

	public By YesToStop = By.xpath("//button[text()='Yes']");

	public By SuccessConfirmation = By.xpath("//div[@id='swal2-content']");

	public By OKMessagebtn = By.xpath("//button[text()='OK']");

	public By AuctionListPage = By.xpath("//*[normalize-space(text())='AUCTION LIST']");

	public By Actionbtn(String price) {
		return By.xpath("//div[contains(text(),'Decrement')]/../../div[text()='" + price
				+ "']/../../../following-sibling::div[2]//a[@data-toggle='dropdown']");
	}

	public By ActionDrpdwn = By.xpath("//a[@data-toggle='dropdown']");

	public By TenderRefNum(String refnum) {
		return By.xpath("//span[text()='" + refnum + "']");
	}

	public By HArdStoptext(String refNum) {
		return By.xpath("//span[text()='" + refNum + "']/..//following-sibling::td[7]/span");
	}

	public By ReleaseTab = By.xpath("//a[normalize-space(text())='RELEASED AUCTION']");

	public By Relaunch = By.xpath("//a[normalize-space(text())='Relaunch']");

	public By RemarksForRelaunch = By.xpath("//textarea[@id='relaunchRemarks']");

	public By RelaunchBtn = By.xpath("//button[normalize-space(text())='Relaunch']");

	public By Startminutes(String i) {
		return By
				.xpath("//html/body/div[3]/div[1]/table/tbody/tr/td/span[contains(@class,'minute') and contains(text(),'"
						+ i + "')]");
	}

	public By Endminutes(String i) {
		return By
				.xpath("//html/body/div[4]/div[1]/table/tbody/tr/td/span[contains(@class,'minute') and contains(text(),'"
						+ i + "')]");
	}

	public By BidTrailReport = By.xpath("//a[normalize-space(text())='Bid Trail Report (PDF)']");

	public By RejectedBidTrailReport = By.xpath("//a[normalize-space(text())='Rejected Bid Trail Report (PDF)']");

	public By lead(String price) {
		return By.xpath(
				"(//div[contains(text(),'Decrement')]/../../div[text()='" + price + "']/following-sibling::div)[2]");
	}

	public By rank(String Decrement) {
		return By.xpath("//div[text()='" + Decrement + "']/../div[text()='Rank: 1']");
	}

	public By observe = By.xpath("//a[contains(text(),'Observe')]");

	public By SNstage(String Id) {
		return By.xpath("//td[text()='" + Id + "']/../td//button[@id='clrcookie']");
	}

	public By pauseTimeFieldBy = By.xpath("//*[@id='pauseTime']");

	public By resumeTimeFieldBy = By.xpath("//*[@id='resumeTime']");

	public By extendedTimeFieldBy = By.xpath("//*[@id='extendedDate']");

	public By eventPagedropDownBy = By.xpath("//*[@data-toggle='dropdown']");

	public By eventPagedropDownField = By.xpath("(//*[@data-toggle='dropdown'])[1]");

	public By saveAndActivateFieldBy = By.xpath("//a[contains(text(),'Save & Activate')]");

	public By startPriceValidationBy = By.xpath("//span[@id='startPriceValidationRA']");

	public By auctionactivatedSuccessmsgBy = By
			.xpath("//*[normalize-space(text())='Auction has been activated successfully!']");

	public By DutchRuleMarketPrice(String price) {
		return By.xpath("//div[contains(text(),'Price Change')]/../../div[text()='" + price
				+ "']//following-sibling::div/child::div[contains(text(),'Market Price')]");
	}

	public By DutchRuleStartPrice(String price) {
		return By.xpath("//div[contains(text(),'Price Change')]/../../div[text()='" + price
				+ "']/../../preceding-sibling::div//child::div[contains(text(),'Start Price:')]");
	}

	public By DutchRuleSubmitBidPrice(String price) {
		return By.xpath("//div[contains(text(),'Price Change')]/../../div[text()='" + price
				+ "']/../../../following-sibling::div//button[text()='Submit']");
	}

	public By DutchRuleNewBidPrice(String price) {
		return By.xpath("//div[contains(text(),'Price Change')]/../../div[text()='" + price
				+ "']/../../../following-sibling::div//child::span/input[starts-with(@id,'newBid')]");
	}

	public By DutchAcivateStatusBy = By.xpath("//*[@id='scheduleListId']/tr/td[9]/span[text()='Active']");

	public By DutchstartDateTime = By.xpath(
			"//*[@id='scheduleListId']/tr/td[9]/span[text()='Active']/../preceding-sibling::td/input[@placeholder='Select start date & time']");

	public By DutchendDateTime = By.xpath(
			"//*[@id='scheduleListId']/tr/td[9]/span[text()='Active']/../preceding-sibling::td/input[@placeholder='Select end date & time']");

	public By Starthour(String i) {
		return By.xpath("((//span[contains(@class,'hour') and starts-with(text(),'" + i + "')]))[1]");
	}

	public By Endhour(String i) {
		return By.xpath("((//span[contains(@class,'hour') and starts-with(text(),'" + i + "')]))[2]");
	}

	public By ClickonEndDateField = By.xpath("//div[@id='m_datetimepicker_2_1']");
	public By todayEnddate = By.xpath("(//td[@class='day today active'])[2]");

	public By StartPrice(String price) {
		return By.xpath("(//div[contains(text(),'Decrement')]/../../div[text()='" + price
				+ "']/../../preceding-sibling::div)[1]//div[text()='Start Price:']/..");
	}

	public By approveASN = By.xpath("//a[contains(text(),'Approve ASN')]");
	public By actionDropdownASN = By.xpath("(//button[contains(@data-toggle,'dropdown')])[1]");
	public By approveOrReject = By.xpath("(//a[contains(text(),'Approve/Reject')])[1]");
	public By selectPONo = By.xpath("//strong[contains(text(),'Select PO Number:')]");

	public By approveOrHold = By.xpath("//button[@data-original-title='Approve / Hold']");
	public By approverCommentSection = By.xpath("//textarea[@ng-model='objData.comment']");
	public By attachFile = By.xpath("//input[@class='ng-isolate-scope']");
	public By approveButton = By.xpath("//button[contains(text(),'Approve')]");
	public By supplierInformation = By.xpath("//a[contains(text(),'Supplier Information')]");
	public By superLegancyCode = By.xpath("//strong[contains(text(),'Supplier Legacy Code')]");
	public By shipmentInformation = By.xpath("//a[contains(text(),'Shipment Information')]");
	public By bolNumber = By.xpath("//strong[contains(text(),'BOL Number')]");
	public By whatTheyAreShippingToUs = By.xpath("//a[contains(text(),'What They are Shipping to Us')]");
	public By boxNumber = By.xpath("//label[contains(text(),'Box Number :')]");
	public By ASNDeliveryChallan = By.xpath("//a[contains(text(),'ASN / Delivery Challan Checklist')]");
	public By buyerAcceptance = By.xpath("//select[@name='buyer_acceptance']");
	public By invoice = By.xpath("//a[contains(text(),'Invoice')]");
	public By poNumber = By.xpath("//label[contains(text(),'PO Number')]");
	public By ASNApproverSave = By.xpath("//button[@data-original-title='Save']");
	public By ASN = By.xpath("//span[text()='ASN']");

	public By ASNList = By
			.xpath("//span[text()='ASN']/../following-sibling::ul//a[normalize-space(text())='ASN List']");

	public By CreateASN = By
			.xpath("//span[text()='ASN']/../following-sibling::ul//a[normalize-space(text())='Create ASN']");

	public By SelectPONum = By.xpath("//select[@ng-model='asn.singleSelectPoId']");

	public By ASNDate = By.xpath("//input[@id='0.asn_date.0']");

	public By ASNReference = By.xpath("//input[@id='0.asn_reference_no.0']");

	public By ASNCarrier = By.xpath("//input[@id='0.carrier.0']");

	public By ASNShippingMethod = By.xpath("//input[@id='0.shipping_method.0']");

	public By ASNShippertracking = By.xpath("//input[@id='0.shipper_tracking.0']");

	public By ASNEstimatedDeliveryTime = By.xpath("//input[@id='0.estimated_delivery_time.0']");

	public By ASNAdditionalNote = By.xpath("//input[@id='0.additional_note.0']");

	public By ASNShipmentnumber = By.xpath("//input[@id='0.shipment_number.0']");

	public By ChkbxInspectionReq = By.xpath("//input[@id='exampleCheck1']");

	public By ASNInspectionDetails = By.xpath("//textarea[@id='exampleFormControlTextarea1']");

	public By ASNSave = By.xpath("//button[@data-original-title='Save']");

	public By TabMyInformation = By.xpath("//a[normalize-space(text())='My Information']");

	public By TabShipmentInformation = By.xpath("//a[normalize-space(text())='Shipment Information']");

	public By TabWhatIamShipping = By.xpath("//a[normalize-space(text())='What I am Shipping']");

	public By TabDeliveryChallanChecklist = By.xpath("//a[normalize-space(text())='ASN / Delivery Challan Checklist']");

	public By TabInvoice = By.xpath("//a[normalize-space(text())='Invoice']");

	public By purchaserAccount = By.xpath("//input[@id='1.budget_head.0']");

	public By SupplierState = By.xpath("//input[@id='1.state.0']");

	public By SupplierZip = By.xpath("//input[@id='1.zip.0']");

	public By SupplierContact = By.xpath("//input[@id='1.contact_no.0']");

	public By SupplierOrgName = By.xpath("//input[@id='1.vendor_company_name.0']");

	public By SelctWareHouse = By.xpath("//strong[text()='Select Warehouse: ']/../following-sibling::select");

	public By BolNumber = By.xpath("//input[@id='2.bol_no.0']");

	public By DeliverToContactName = By.xpath("//input[@id='2.contact_name.0']");

	public By PalletsandBoxes = By.xpath("//label[normalize-space(text())='Pallets & Boxes']");

	public By BoxesOnly = By.xpath("//label[normalize-space(text())='Boxes Only']");

	public By AddPallets = By.xpath("//label[normalize-space(text())='Add Pallet']");

	public By AddBoxe = By.xpath("//label[normalize-space(text())='Add Box']");

	public By PalletDescription1 = By.xpath("(//textarea[@id='exampleFormControlTextarea1'])[1]");

	public By PalletDescription2 = By.xpath("(//textarea[@id='exampleFormControlTextarea1'])[2]");

	public By PalletDescription3 = By.xpath("(//textarea[@id='exampleFormControlTextarea1'])[3]");

	public By Boxe1 = By.xpath("(//input[@placeholder='Enter Box Number'])[1]");

	public By Boxe2 = By.xpath("(//input[@placeholder='Enter Box Number'])[2]");

	public By Boxe3 = By.xpath("(//input[@placeholder='Enter Box Number'])[3]");

	public By ItemDescription1 = By.xpath("(//i[@class='fa fa-list tooltipClass'])[1]");

	public By ItemDescription2 = By.xpath("(//i[@class='fa fa-list tooltipClass'])[2]");

	public By ItemDescription3 = By.xpath("(//i[@class='fa fa-list tooltipClass'])[3]");

	public By radiobtnItemDescription1 = By.xpath("(//tr[@class='ng-scope']//input)[1]");

	public By radiobtnItemDescription2 = By.xpath("(//tr[@class='ng-scope']//input)[2]");

	public By radiobtnItemDescription3 = By.xpath("(//tr[@class='ng-scope']//input)[3]");

	public By BtnOk = By.xpath("//button[normalize-space(text())='Ok']");

	public By ASNQuantity1 = By.xpath("(//input[@placeholder='ASN Quantity'])[1]");

	public By ASNQuantity2 = By.xpath("(//input[@placeholder='ASN Quantity'])[2]");

	public By ASNQuantity3 = By.xpath("(//input[@placeholder='ASN Quantity'])[3]");

	public By Weight1 = By.xpath("(//input[@placeholder='Weight'])[1]");

	public By Weight2 = By.xpath("(//input[@placeholder='Weight'])[2]");

	public By Weight3 = By.xpath("(//input[@placeholder='Weight'])[3]");

	public By UOMWeight1 = By.xpath("(//select[@id='3.uom_weight.14'])[1]");

	public By UOMWeight2 = By.xpath("(//select[@id='3.uom_weight.14'])[2]");

	public By UOMWeight3 = By.xpath("(//select[@id='3.uom_weight.14'])[3]");

	public By UploadDocument = By.xpath("//input[@title='Select file' and @id ='4_upload_path_0']");

	public By DocumentName = By.xpath("//input[@placeholder='Document Name']");

	public By InvoiceType = By.xpath("//select[@id='5.invoice_type.0']");

	public By SellerInvoiceNum = By.xpath("//input[@placeholder='Seller Invoice No']");

	public By InvoiceDate = By.xpath("//input[@id='5.invoice_date.0']");

	public By Freight = By.xpath("//input[@placeholder='Freight']");

	public By Tax = By.xpath("//input[@placeholder='Tax']");

	public By InvoiceAttachment = By.xpath("//input[@id='5_invoice_attachment_0']");

	public By BankAccountNumber = By.xpath("//input[@placeholder='Bank Account Number']");

	public By BillingAddress = By.xpath("//textarea[@name='billing_address']");

	public By cashDiscount = By.xpath("//input[@ng-model='row.cashDiscount']");

	public By cashPercentage = By.xpath("//input[@ng-model='row.cashPercentage']");

	public By NumberOfDays = By.xpath("//input[@ng-model='row.numberOfDays']");

	public By btnSendforApproval = By.xpath("//button[@data-original-title='Send for approval']");

	public By BtnYes = By.xpath("//button[@data-bb-handler='confirm']");
	public By GRN = By.xpath("//span[text()='GRN']");

	public By approvedASN = By.xpath("//a[contains(text(),'Approved ASN')]");
	public By TCS = By.xpath("//strong[contains(text(),'TCS')]");
	public By enterVendorName = By.xpath("//input[@placeholder='Enter Vendor Name']");
	public By enterShipmentNumber = By.xpath("//input[@placeholder='Enter shipment number']");

	public By SearchPOINASN = By.xpath("//input[@ng-model='searchString']");

	public By Status(String ponum) {
		return By.xpath("(//a[normalize-space(text())='" + ponum
				+ "'])[1]/../../following-sibling::td[8]//button[@class='btn btn-primary btn-circle btn-lg btn-lg-sub2 grnBtn']");
	}

	public By DeleteBox = By
			.xpath("//label[normalize-space(text())='Add Box']/../../following-sibling::div[2]//button/i");

	public By BoXDeleteConfirm = By.xpath("//button[@data-bb-handler='confirm']");

	public By ASNListInBidderLogin = By.xpath("//a[contains(text(),'ASN List')]");

	public By ASNListPage = By.xpath("//li[text()='ASN List']");

	public By ASNNo = By.xpath("//strong[text()='ASN No.']/../following-sibling::div//input");

	public By draftStatus = By.xpath(
			"//button[@data-original-title='Draft'][@class='btn btn-primary btn-circle btn-lg btn-lg-sub2 grnBtn']");

	public By records = By.xpath("//div[text()='Showing ']/b");

	public By GRNMenuLinkBy = By.xpath("//*[@id='mCSB_1_container']/li/a/span[text()='GRN']");

	public By ApprovedASNLinkBy = By.xpath("//a[contains(text(),'Approved ASN')]");

	public By vendorNameFieldBy = By.xpath("//*[@placeholder='Enter Vendor Name']");

	public By entershipmentFieldBy = By.xpath("//*[@placeholder='Enter shipment number']");

	public By MaterialDetailsBy = By.xpath("//*[contains(normalize-space(text()),'Material Details')]");

	public By SaveGrnDetailsBy = By.xpath("//*[@data-original-title='Save']");

	public By GrnSubmitBy = By.xpath("//*[@data-original-title='Submit']");

	public By GrnSubmitConfirmPopUpMsgBy = By
			.xpath("//*[text()='Do you really want to submit? Once submit you can not reverse it back. ']");

	public By GrnSubmitConfirmYesBy = By.xpath(
			"//*[text()='Do you really want to submit? Once submit you can not reverse it back. ']/../following-sibling::div/button[@data-bb-handler='confirm']");

	public By GrnSubmitConfirmNoBy = By.xpath(
			"//*[text()='Do you really want to submit? Once submit you can not reverse it back. ']/../following-sibling::div/button[@data-bb-handler='cancel']");

	public By GrnListMenuLinkBy = By.xpath("//a[contains(text(),'GRN List')]");

	public By GrnListDraftEleBy = By.xpath("//*[@id='myTab']/li[contains(@class,'ng-scope active')]/a");

	public By AsnListShipmentThBy = By.xpath("//th[contains(text(),'Shipment No.')]");

	public By EnterRejectedQtyBy(int no) {
		return By.xpath("(//*[@name='grn_rejected_quantity'])[" + no + "]");
	}

	public By EnterExcessQtyBy(int no) {
		return By.xpath("(//*[@name='excess_qty'])[" + no + "]");
	}

	public By EnterShortQtyBy(int no) {
		return By.xpath("(//*[@name='short_qty'])[" + no + "]");
	}

	public By EnterDamageQtyBy(int no) {
		return By.xpath("(//*[@name='damage_qty'])[" + no + "]");
	}

	public By AcceptedQtyBy(int no) {
		return By.xpath("(//*[contains(text(),'Accepted Quantity:')]/../../../following-sibling::div//b)[" + no + "]");
	}

	public By grnIDBy = By.xpath("//*[@id='bodyPrint']//table/tbody/tr[1]/th[text()='GRN ID']");

	public By GrnPreviewCloseBtn = By.xpath("//*[contains(@ng-click,'GRN_MODAL')  and text()='Close']");

	public By AsnPreviewCloseBtn = By.xpath("//*[contains(@ng-click,'ASN_MODAL')  and text()='Close']");

	public By asnNoBy = By.xpath(
			"//*[@id='asnPreviewModal']//table/tbody/tr[1]/td[2]/asn-preview-field/div/label[1]/b[text()='ASN No. :']");

	public By GrnCancelAlertMsgBy = By.xpath("//*[text()='Do you really want to cancel?']");

	public By GrnCancelConfirmBtnBy = By.xpath(
			"//*[text()='Do you really want to cancel?']/../following-sibling::div[@class='modal-footer']/button[contains(text(),'Confirm')]");

	public By GrnCreatedTabBy = By.xpath("//*[@class='row']//child::ul[@id='myTab']/li/a[contains(text(),'Created')]");

	public By GrnCreatedTabRecBy = By.xpath(
			"//*[@class='row']//child::ul/li/a[contains(text(),'Created')]/../..//following-sibling::div/child::div[@class='tab-pane fade ng-scope active in']//table/tbody/tr[1]");

	public By GrnCancelTabBy = By.xpath("//*[@class='row']//child::ul[@id='myTab']/li/a[contains(text(),'Cancel')]");

	public By GrnCancelTabRecBy = By.xpath(
			"//*[@class='row']//child::ul/li/a[contains(text(),'Cancel')]/../..//following-sibling::div/child::div[@class='tab-pane fade ng-scope active in']//table/tbody/tr[1]");

	public By GrnDraftRecBy = By.xpath(
			"//*[@class='row']//child::ul/li/a[contains(text(),'Draft')]/../..//following-sibling::div/child::div[@class='tab-pane fade ng-scope active in']//table/tbody/tr[1]");
	public By createASNButton = By.xpath("//button[contains(@data-original-title,'Create ASN')]");

	public By viewASN = By.xpath("//a[text()='View']");

	public By previewASN = By.xpath("//h3[text()='Preview ASN']");

	public By ASNNo(String ASNno) {
		return By.xpath("//b[text()='ASN No. :']/../following-sibling::label/span/span[text()='" + ASNno + "']");
	}

	public By asnInformation = By.xpath("//div[normalize-space(text())='ASN Information']");

	public By myInformation = By.xpath("//div[normalize-space(text())='My Information']");

	public By shipmentInfo = By.xpath("//div[normalize-space(text())='Shipment Information']");

	public By shipping = By.xpath("//div[normalize-space(text())='What I am Shipping']");
	public By deliveryChallan = By.xpath("//div[normalize-space(text())='ASN / Delivery Challan Checklist']");

	public By invoiceCheck = By.xpath("//div[normalize-space(text())='Invoice']");

	public By preparedStatus = By
			.xpath("//button[contains(@class,'btn-lg btn-lg-sub2 grnBtn doneBtn')][@data-original-title='Prepared']");

	public By StatusPendingforInspection(String ponum) {
		return By.xpath("(//a[normalize-space(text())='" + ponum
				+ "'])[1]/../../following-sibling::td[8]//button[@class='btn btn-primary btn-circle btn-lg btn-lg-sub2 grnBtn doneBtn']");
	}

	public By Inspection = By.xpath("//a[normalize-space(text())='Inspection']");
	public By Pending = By.xpath("//a[normalize-space(text())='Pending']");

	public By InspectionActionBtn(String ASNNum) {
		return By.xpath("//td[text()='" + ASNNum + "']/../td[9]//button");
	}

	public By Claim(String ASNNum) {
		return By.xpath("//td[text()='" + ASNNum + "']/../td[9]//button/following-sibling::ul//a[text()='Claim']");
	}

	public By ViewASN(String ASNNum) {
		return By.xpath("//td[text()='" + ASNNum + "']/../td[9]//button/following-sibling::ul//a[text()='View Asn']");
	}

	public By claimAssigned(String ASNNum) {
		return By.xpath("//td[text()='" + ASNNum + "']/../td[6]/span[text()='Assigned']");
	}

	public By ApproveOrReview(String ASNNum) {
		return By.xpath(
				"//td[text()='" + ASNNum + "']/../td[9]//button/following-sibling::ul//a[text()='Approve/Review']");
	}

	public By ActionBtnBidder(String ponum) {
		return By.xpath("(//a[normalize-space(text())='" + ponum + "'])[1]/../../following-sibling::td[9]//button");
	}

	public By EditASN(String ponum) {
		return By.xpath("(//a[normalize-space(text())='" + ponum
				+ "'])[1]/../../following-sibling::td[9]//ul//a[text()='Edit']");
	}

	public By POItemRemaining = By.xpath("(//b[text()='Remaining PO Quantity : ']/..)[1]");
	public By ASNInformation = By.xpath("//b[contains(text(),'ASN Information')]");

	public By DeleteASN(String ponum) {
		return By.xpath("(//a[normalize-space(text())='" + ponum
				+ "'])[1]/../../following-sibling::td[9]//ul//a[normalize-space(text())='Delete']");
	}

	public By BidderWarehouse(String ponum) {
		return By.xpath("(//a[normalize-space(text())='" + ponum + "'])[1]/../../following-sibling::td[6]");
	}

	public By ApproverWarehouse(String asnnum) {
		return By.xpath("//td[text()='" + asnnum + "']/../td[7]");
	}

	public By approveOrReject(String ASNno) {
		return By.xpath("//td[text()='" + ASNno
				+ "']/../td//button[@id='menu1']/following-sibling::ul/li/a[text()='Approve/Reject']");
	}

	public By ASNStatus(String currentStatus) {
		return By.xpath("//button[contains(@class,'btn-lg btn-lg-sub2 grnBtn')][contains(@data-original-title,'"
				+ currentStatus + "')]");
	}

	public By buyerAcceptence = By.xpath("//select[@id='4.buyer_acceptance.0']");

	public By revert = By.xpath("//button[contains(text(),'Revert')]");

	public By actionDropDowns(String asnNo, String dropDowns) {
		return By.xpath("//td[text()='" + asnNo
				+ "']/../td//button[@id='menu1']/following-sibling::ul/li/a[contains(text(),'" + dropDowns + "')]");
	}

	public By ASNNum(String ponum) {
		return By.xpath("(//a[normalize-space(text())='" + ponum + "'])[1]/../../following-sibling::td[1]");
	}

	public By hold = By.xpath("//button[contains(text(),'Hold')]");

	public By inspectionButton = By.xpath("//button[@data-original-title='Send for Inspection']");

	public By requestForInspectionButton = By.xpath("//button[contains(text(),'Request for Inspection')]");

	public By inspectionASNDocNo(String asnNo) {
		return By.xpath("//tbody[@id='tbody1']/tr/td[text()='" + asnNo + "']");
	}

	public By approveOrReview(String ASNno) {
		return By.xpath("//td[text()='" + ASNno
				+ "']/../td//button[@id='menu1']/following-sibling::ul/li/a[text()='Approve/Review']");
	}

	public By actionBtn(String ASNno) {
		return By.xpath("//td[text()='" + ASNno + "']/../td//button[@id='menu1']");
	}

	public By poValueCV = By.xpath("(//label[@class='bigcheck ng-binding'])[3]");
	public By netASNValueCV = By.xpath("(//label[@class='bigcheck ng-binding'])[4]");
	public By closeFilter = By.xpath("//button[@id='closeFilter' and contains(text(),'Close')]");
	public By selectAllBidFieldBy = By.xpath("//*[@id='allBid']");

	public By hardStopTimeFieldBy = By.xpath("//*[@id='hardStopTime']");

	public By ViewShipmentDetailsPage(String ShipTrackno) {
		return By.xpath("(//td[text()='" + ShipTrackno + "']/../td[10]//a[text()='View Shipment Details'])[1]");
	}

	public By CancelGRN(String ShipTrackno) {
		return By.xpath("(//td[text()='" + ShipTrackno + "']/../td[10]//a[text()='Cancel GRN'])[1]");
	}

	public By TabCreated = By.xpath("(//a[normalize-space(text())='Created'])[1]");

	public By TabCancel = By.xpath("(//a[normalize-space(text())='Cancel'])[1]");

	public By ASNPONum = By.xpath("(//b[text()='PO Number :'])[1]/../following-sibling::label//a");
	public By ASNVal = By.xpath("((//b[text()='ASN No. :'])[1]/../following-sibling::label//span)[2]");
	public By ASN_reference = By.xpath("((//b[text()='Reference :'])[1]/../following-sibling::label//span)[2]");
	public By ASN_Shippingmethod = By
			.xpath("((//b[text()='Shipping Method :'])[1]/../following-sibling::label//span)[2]");
	public By ASN_carrier = By.xpath("((//b[text()='Carrier :'])[1]/../following-sibling::label//span)[2]");
	public By ASNAdditionalNoteVal = By
			.xpath("((//b[text()='Additional Note :'])[1]/../following-sibling::label//span)[2]");
	public By ASN_Shipment_Num = By.xpath("(//b[text()='Shipment Number :'])[1]/../following-sibling::label//b");
	public By SellerORGName = By.xpath("((//b[text()='Supplier Org Name :'])[1]/../following-sibling::label//span)[2]");
	public By budgetHead = By
			.xpath("((//b[text()='Purchaser Account ID/Budget Head :'])[1]/../following-sibling::label//span)[2]");
	public By SupplierAddress = By
			.xpath("((//b[text()='Supplier Address :'])[1]/../following-sibling::label//span)[2]");
	public By Suppliercountry = By
			.xpath("((//b[text()='Supplier Country :'])[1]/../following-sibling::label//span)[2]");
	public By Supplierzip = By.xpath("((//b[text()='Supplier zip :'])[1]/../following-sibling::label//span)[2]");
	public By Suppiercontact = By.xpath("((//b[text()='Supplier Contact :'])[1]/../following-sibling::label//span)[2]");
	public By SellerInvoiceNumber = By
			.xpath("((//b[text()='Seller Invoice No :'])[1]/../following-sibling::label//span)[2]");
	public By Billingaddress = By.xpath("((//b[text()='Billing Address :'])[1]/../following-sibling::label//span)[2]");
	public By DiscountbeforNoofDAYS = By
			.xpath("((//b[text()='Discount before no of Days :'])[1]/../following-sibling::label//span)[2]");
	public By InvoiceTypeVal = By.xpath("((//b[text()='Invoice Type :'])[1]/../following-sibling::label//span)[2]");
	public By acceptedQtyAlert = By.xpath("//li[contains(text(),'can not be less than')]");

	public By invalidQuantity(int invalidQtyRow) {
		return By.xpath("(//label[text()='Invalid quantity']/.)[" + invalidQtyRow + "]");
	}

	public By asnQtyFieldvalue(int asnQtyValueRow) {
		return By.xpath("(//b[text()='ASN Quantity: ']/..)[" + asnQtyValueRow + "]");
	}

	public By grnQtyShouldBeEditable(int ItemCodeRow) {
		return By.xpath(
				"//*[contains(text(),'Material Details')]/../..//following-sibling::div//child::div//*[contains(@class,'ng-touched')]");
	}

	public By grnDataSavedMsgBy = By.xpath("//*[contains(text(),'All Data Saved')]");

	public By RejectedQtyvalue(String ItemCode) {
		return By.xpath("//span[contains(text(),'" + ItemCode
				+ "')]/../../../../../following-sibling::div//b[contains(text(),'Rejected Quantity : ')]/..");
	}

	public By ExcessQtyvalue(String ItemCode) {
		return By.xpath("//span[contains(text(),'" + ItemCode
				+ "')]/../../../../../following-sibling::div//b[contains(text(),'Excess Quantity: ')]/..");
	}

	public By DamageQtyvalue(String ItemCode) {
		return By.xpath("//span[contains(text(),'" + ItemCode
				+ "')]/../../../../../following-sibling::div//b[contains(text(),'Damage Quantity: ')]/..");
	}

	public By ShortQtyvalue(String ItemCode) {
		return By.xpath("//span[contains(text(),'" + ItemCode
				+ "')]/../../../../../following-sibling::div//b[contains(text(),'Short Quantity: ')]/..");
	}

	public By ASNQuantity = By.xpath("//label/b[text()='ASN Quantity: ']/parent::label");
	public By shortQtyPopUp = By.xpath("//h4[text()='Confirm']");
	public By popUpText = By.xpath("//div[contains(text(),'You have added excess quantity already')]");

	public By yesOrNoButton(String yesOrNo) {
		return By.xpath("//button[@data-bb-handler][contains(text(),'" + yesOrNo + "')]");
	}

	public By draft = By.xpath("//a[contains(text(),'Draft')]");
	public By viewGRN = By.xpath("//a[text()='View GRN']");
	public By viewShipmentDetails = By.xpath("//a[text()='View Shipment Details']");
	public By cancelGRN = By.xpath("//a[text()='Cancel GRN']");
	public By previewGRN = By.xpath("//h3[text()='Preview GRN']");
	public By previewAll_close1 = By.xpath("(//button[@class='btn btn-primary succes_msgsnd' and text()='Close'])[2]");

	public By ViewlGRN(String ShipTrackno) {
		return By.xpath("(//td[text()='" + ShipTrackno + "']/../td[10]//a[text()='View GRN'])[1]");
	}

	public By ItemCodeUi = By.xpath("(//b[text()='Item Code: ']//../span)[1]");
	public By ItemDescriptionUi = By.xpath("(//b[text()='Item Description: ']//../span)[1]");
	public By UOMUi = By.xpath("(//b[text()='UOM (Qty): ']//../span)[1]");
	public By POQuantityUi = By.xpath("(//b[text()='PO Quantity: ']//..)[1]");
	public By ASNQuantityUi = By.xpath("(//b[text()='ASN Quantity: ']//..)[1]");
	public By RejectedQuantityUi = By.xpath("(//b[text()='Rejected Quantity : ']//..)[1]");
	public By ExcessQuantityUi = By.xpath("(//b[text()='Excess Quantity: ']//..)[1]");
	public By ShortQuantityUi = By.xpath("(//b[text()='Short Quantity: ']//..)[1]");
	public By DamageQuantity = By.xpath("(//b[text()='Damage Quantity: ']//..)[1]");
	public By AcceptedQuantityUi = By
			.xpath("(//b[text()='Accepted Quantity : ']//../../../following-sibling::div//b)[1]");
	public By GrnIDBy = By.xpath("(//table/tbody/tr[1]/th[text()='GRN ID']/../td/span)[1]");
	// InVoice

	public By InVoiceMenuLinkBy = By.xpath("//*[@id='mCSB_1_container']/li/a/span[text()='Invoice']");

	public By InVoiceProcessingLinkBy = By.xpath("//a[contains(text(),'Invoice Processing')]");

	public By invoiceNestedTableRowsBy = By.xpath("(//*[@class='nestedTableLevel1']//div[@class='panel-heading'])[1]");

	public By serchInvoiceOrPoNumBy = By.xpath("//input[@placeholder='Enter PO Number / invoice number']");

	public By InvoiceSellerHeaderBy = By.xpath(
			"//*[@class='panel-heading']//a[contains(normalize-space(text()),'Invoice Number / Seller Invoice Number :')]");

	public By GRNHeaderBy = By.xpath("//*[@class='panel-heading']//a[contains(normalize-space(text()),'GRN ID :')]");

	public By GRNHeaderMinusIconBy = By.xpath("//*[contains(@ng-click,'clickOnGRN')][@class='fa fa-minus-circle']");

	public By shippingTrackingData(String ShipTrackno) {
		return By.xpath("//*[contains(text(),'" + ShipTrackno + "')]");
	}

	public By Invoice = By.xpath("//span[contains(text(),'Invoice')]");
	public By GRListToRaiseInvoice = By.xpath("//a[contains(text(),'GRN List To Raise Invoice')]");
	public By enterPONumber = By.xpath("//input[@placeholder='Enter PO Number / invoice number']");
	public By poNumberplusIcon = By.xpath("//a[contains(@ng-click,'clickOnPo')]");
	public By view_GRN = By.xpath("//a[contains(text(),'View GRN')]");
	public By GRNId = By.xpath("//a[contains(@ng-click,'clickOnGRN')]");

	public By itemDetailsTab = By.xpath("//a[contains(text(),'Item Details')]");
	public By invoiceDate = By.xpath("//input[@id='.invoice_date.']");
	public By btnPayment = By.xpath("//span[text()='Payment']");
	public By btnInvoiceListPayment = By.xpath("//a[normalize-space(text())='Invoice List For Payment']");

	public By chkboxinvoice(String invoice) {
		return By.xpath("//td[text()='" + invoice + "']/preceding-sibling::td/input");
	}

	public By HomeInvoicepage = By.xpath("//li[normalize-space(text())='Invoice List For Payment']");

	public By invoiceActiondrpdwn(String invoice) {
		return By.xpath("//td[text()='" + invoice + "']/..//button[@data-toggle='dropdown']");

	}

	public By ViewInvoiceASN(String invoice) {
		return By.xpath("(//td[text()='" + invoice + "']/..//a)[1]");
	}

	public By ViewInvoiceGRN(String invoice) {
		return By.xpath("(//td[text()='" + invoice + "']/..//a)[2]");
	}

	public By ViewInvoicePO(String invoice) {
		return By.xpath("(//td[text()='" + invoice + "']/..//a)[3]");
	}

	public By GRNidUi = By.xpath("//th[text()='GRN ID']/following-sibling::td[1]");
	public By SupplierCode = By.xpath("//b[text()='Supplier Code']/../../following-sibling::td//span");

	public By SupplierorgName = By.xpath("//b[text()='Supplier Organization Name']/../../following-sibling::td//span");
	public By Address = By.xpath("//b[text()='Address']/../../following-sibling::td//span");
	public By Country = By.xpath("//b[text()='Country']/../../following-sibling::td//span");

	public By SellerInvoiceapprove(String sellerInvoiceNo) {
		return By.xpath("//*[contains(text(),'" + sellerInvoiceNo + "')]/..//span/i[@title='Approve Invoice']");
	}

	public By poValueNumberBy = By.xpath("//*[@placeholder='PO Number']");

	public By approvedAmountBy = By.xpath("//*[@name='approved_amount']");

	public By penaltyDeductionAmountBy = By.xpath("//*[@name='penalty_deduction']");

	public By inVoiceAcceptBy = By.xpath("//*[@ng-click='acceptInvoice()']");

	public By sendBackToSupplierBy = By.xpath("//*[@ng-click='sendBackToSupplier()']");

	public By inVoiceErrorValidationBy = By
			.xpath("//*[@class='error-msg-list']//b[text()='Invoice']/../../following-sibling::li");

	public By inVoiceErrorMsgOkBy = By.xpath("//button[contains(text(),'OK')]");

	public By inVoiceHeaderBarBy = By.xpath("//*[@class='panel-heading']//a[contains(@ng-click,'clickOnInvoice')]");

	public By viewGrnBy = By.xpath("//*[@ng-click='viewGrnData(grnObj)']");

	public By poQuantityValue(String ItemCode) {
		return By.xpath("//span[contains(text(),'" + ItemCode
				+ "')]/../../../../../following-sibling::div//b[contains(text(),'PO Quantity: ')]/..");
	}

	public By AsnQuantityValue(String ItemCode) {
		return By.xpath("//span[contains(text(),'" + ItemCode
				+ "')]/../../../../../following-sibling::div//b[contains(text(),'ASN Quantity: ')]/..");
	}

	public By viewASN_InoviceNumber = By.xpath("//span[@ng-click='viewAsn(invObj)']");
	public By view_PO = By.xpath("//span[contains(@ng-click,'poViewModal')]");
	public By invoice_Date = By.xpath("//*[contains(text(),'Invoice Date :')]/../..");
	public By dueDate = By.xpath("//*[contains(text(),'Due Date :')]/../..");
	public By invoiceAmount = By.xpath("//*[contains(text(),'Invoice Amount :')]/../..");
	public By paidAmount = By.xpath("(//*[contains(text(),'Paid Amount :')]/../..)[2]");
	public By overDueAmount = By.xpath("//*[contains(text(),'Due/Overdue Amt')]/../..");
	public By invoiceStatus = By.xpath("//*[contains(text(),'Invoice Status :')]/../..");

	public By AcceptedGrnQuantity(String ItemCode) {
		return By.xpath("//span[contains(text(),'" + ItemCode
				+ "')]/../../../../../following-sibling::div//b[contains(text(),'Accepted Quantity : ')]/../../../following-sibling::div//b");
	}

	public By ClosePoDetails = By.xpath("(//button[text()='Close'])[3]");
	public By disputeInvoiceList = By.xpath("//a[contains(text(),'Disputed Invoice List')]");
	public By disputedInvoicePaymentListTitle = By
			.xpath("//h3//span[contains(text(),'Disputed Invoice Payment List')]");
	public By poNumberHeader = By.xpath("//h4/a[contains(@ng-click,'clickOnPo')]");
	public By alterInvoiceIcon = By.xpath("//span[contains(@ng-click,'alterInvoiceAction')]/i");
	public By alterDisputedInvoice = By.xpath("//h3/span[text()='Alter/Modify Disputed Invoice']");

	public By itemCodeList(int i) {
		return By.xpath(
				"(//div[@role='rowgroup']/following-sibling::div//div[@ui-grid-row='row']/div/div/span)[" + i + "]");
	}

	public By acceptedQuantity(int i) {
		return By.xpath(
				"(//div[@class='ui-grid-cell ng-scope ui-grid-coluiGrid-000A text-right']/div[@title])[" + i + "]");
	}

	public By invoiceQuantityToBeRaised(int i) {
		return By.xpath("(//input[@ng-model='row.entity.invoiceQuantity'])[" + i + "]");
	}

	public By grnActionBtn(String trackingNumber) {
		return By.xpath("//td[text()='" + trackingNumber + "']/..//button[@id='menu1']");
	}

	public By disputedStatusBy = By.xpath("//*[text()='Disputed']");

	public By InvoiceNuUI(String InvoiceNum) {
		return By.xpath("//td[@class='ng-binding ng-scope' and text()='" + InvoiceNum + "']");
	}

	public By SupplierUI = By.xpath("//td[@class='ng-binding ng-scope' and text()='TCS']");

	public By InvoiceDateUI(String InvoiceDate) {
		return By.xpath("//td[@class='ng-binding ng-scope' and text()='" + InvoiceDate + "']");
	}

	public By InvoiceAmtUI(String InvoiceAmtUI) {
		return By.xpath("//span[@class='ng-binding ng-scope' and text()='" + InvoiceAmtUI + "']");
	}

	public By BalanceInvoiceAmtUI(String InvoiceAmtUI) {
		return By.xpath("//td[@class='ng-binding ng-scope' and text()='" + InvoiceAmtUI + "']");
	}

	public By InvoiceStatusUI(String Status) {
		return By.xpath("//td[@class='ng-binding ng-scope' and text()='" + Status + "']");
	}

	public By btnPay = By.xpath("//button[text()='Pay']");

	public By AmtToBePaid = By.xpath("//input[@ng-model='paymentItem.paidAmount']");

	public By Balance = By.xpath("//input[@ng-model='paymentItem.paidAmount']/../following-sibling::td[1]");

	public By Total = By.xpath("//button[text()='Total']/../following-sibling::div[1]/button");

	public By BeneficiaryName = By
			.xpath("//div[normalize-space(text())='Beneficiary Name']/following-sibling::div/input");

	public By BeneficiaryAccountNumber = By
			.xpath("//div[normalize-space(text())='Beneficiary Account No']/following-sibling::div/input");

	public By IFSCCode = By.xpath("//div[normalize-space(text())='IFSC Code']/following-sibling::div/input");

	public By TransactionNumber = By
			.xpath("//div[normalize-space(text())='Enter Transaction Number']/following-sibling::div/input");

	public By Amount = By.xpath("//div[normalize-space(text())='Amount']/following-sibling::div/input");

	public By radiobtnCheque = By.xpath("//span[text()='Cheque']/preceding-sibling::input");

	public By radiobtnChallan = By.xpath("//span[text()='Challan']/preceding-sibling::input");

	public By chqAccount = By.xpath("//div[normalize-space(text())='Account']/following-sibling::div/input");

	public By ChqNumber = By.xpath("//div[normalize-space(text())='Cheque No']/following-sibling::div/input");

	public By ChqDate = By.xpath("//div[normalize-space(text())='Date']/following-sibling::div//input");

	public By PayeeNameOnCheque = By
			.xpath("//div[normalize-space(text())='Payee Name On Cheque']/following-sibling::div/input");

	public By VoucherNumber = By.xpath("//div[normalize-space(text())='Voucher Number']/following-sibling::div/input");

	public By ProcessedBy = By.xpath("//div[normalize-space(text())='Processed By']/following-sibling::div/input");

	public By Submit = By.xpath("//button[normalize-space(text())='Submit']");

	public By ShowList(String InvoiceAmtUI) {
		return By.xpath("//span[@class='ng-binding ng-scope' and text()='" + InvoiceAmtUI
				+ "']/../following-sibling::td//span//i");
	}

	public By PaidAmt(String InvoiceAmtUI) {
		return By.xpath("(//span[@class='ng-binding ng-scope' and text()='" + InvoiceAmtUI
				+ "']/../following-sibling::td//span)[1]");
	}

	public By btnOk = By.xpath("(//button[normalize-space(text())='Ok'])[1]");

	public By itemCodeActionBtn(int indexNo) {
		return By.xpath("(//button[@class='btn btn-primary dropdown-toggle'])[" + indexNo + "]");
	}

	public By itemCodeViewPO = By.xpath("//a[contains(@ng-click,'grid.appScope.poViewModal')]");
	public By PODetail = By.xpath("//h3[text()='PO Detail']");
	public By closePOView = By.xpath("//div[@id='poView']//button[text()='Close']");

	//
	public By closePreviewASN = By.xpath("(//*[contains(@ng-click,'ASN_MODAL')  and text()='Close'])[1]");
	public By closePreviewGRN = By.xpath("(//*[contains(@ng-click,'GRN_MODAL')  and text()='Close'])[2]");

	public By itemCodeViewASN(int i) {
		return By.xpath("(//a[contains(@ng-click,'grid.appScope.asnPreviewFn')])[" + i + "]");
	}

	public By itemCodeViewGRN(int i) {
		return By.xpath("(//a[contains(@ng-click,'grid.appScope.viewGrn')])[" + i + "]");
	}

	public By StatusFilter = By.xpath("//select[@ng-model='statusObj.statusFilter']");
	public By raiseInovice = By.xpath("//span[contains(@ng-click,'raiseInvoiceAction')]");
	public By disputedInvoiceTotalAmt = By.xpath("//h2[contains(@ng-if,'data.totalInvoiceAmount')]/b");
	//public By showList = By.xpath("//a[@data-original-title='Show  List']/i");
	public By penalityDeduction = By
			.xpath("//label[contains(text(),'Penalty Deduction')]/following-sibling::div//input");
	public By approvedAmount = By.xpath("//label[contains(text(),'Approved Amount')]/following-sibling::div//input");
	public By invoiceValidationMessage = By.xpath("//b[text()='Invoice']/../../following-sibling::li");
	public By BidderRegistration = By.xpath("//a[text()='Bidder Registration']");

	public By FreshRegistration = By.xpath("//input[@value='Fresh']");

	public By btnContinue = By.xpath("//button[@id='save']");

	public By SelectCountry = By.xpath("//select[@name='country']");

	public By Pannum = By.xpath("//input[@name='pan']");

	public By Companyname = By.xpath("//input[@name='companyname']");

	public By BuisnessType = By.xpath("//select[@name='naturecompany']");

	public By OfficeAddress = By.xpath("//textarea[@ng-model='vendor.officeaddress']");

	public By City = By.xpath("//input[@name='city']");

	public By State = By.xpath("//select[@name='state']");

	public By Postalcode = By.xpath("//input[@name='postalcode']");

	public By Phoneno = By.xpath("//input[@name='phoneno']");

	public By Faxno = By.xpath("//input[@name='faxno']");

	public By Title = By.xpath("//select[@name='salutation']");

	public By FirstName = By.xpath("//input[@name='firstname']");

	public By LastName = By.xpath("//input[@name='lastname']");

	public By MobileNum = By.xpath("//input[@name='otpMobile']");

	public By Email = By.xpath("//input[@name='otpEmail']");

	public By createRFQFromIndentBy = By.xpath("//a[contains(@ng-click,'indentListForAssignee')]");

	public By IndenttableDataRowsBy = By.xpath("//tbody/tr[1][contains(@ng-repeat,'indent in filterAllTenderData')]");

	public By selectIndentBy = By.xpath("//*[@ng-model='assignmentF.type']");

	public By viewIndentBy = By.xpath("//*[contains(@ng-click,'previewIndentTemplate')][@name='viewWork']");

	public By createRfqDropDownBy = By.xpath("//*[contains(@ng-click,'previewIndentTemplate')][@name='createRFQ']");

	public By revertBy = By.xpath("//*[contains(@ng-click,'setIndentIdForReviewed')]");

	public By systemIntendNoBy = By.xpath("//strong[@class='ng-binding'][text()='System Indent No']");

	public By indentpreviewOkBy = By.xpath("//*[@id='btn-prevOk']/child::span[text()='Ok']");

	public By printBy = By.xpath("//*[@id='myModalprev']//child::button[@id='btn-prev'][contains(text(),'Print')]");

	public By reasonForRevertBy = By.xpath("//*[@id='reviededBack']//textarea[@name='reviewedReason']");

	public By indentRevertSubmitBtnBy = By.xpath("//*[@ng-click='reviewed(reviewed.reviewedReason)']");

	public By sucessMsgIndertRevertedBy = By.xpath("//*[text()='Indent has been reverted successfully.']");

	public By reversalHistoryBy = By.xpath("//*[contains(@ng-click,'fetchAssignmentHistory')]");

	public By revertedStatusBy = By.xpath("//*[@id='assignmentList']//td[text()='Reverted  Back']");

	public By reversalHistoryViewPopUpBy = By.xpath("//*[@id='reversalHistViewModal']//h3");

	public By reversalHistoryViewPopUpOkBtnBy = By
			.xpath("//*[@id='reversalHistViewModal']//h3/../following-sibling::div/button[contains(text(),'Ok')]");

	public By indentAssignmentLinkBy = By.xpath("//a[contains(text(),'Indent Assignment')]");
	public By zeroValueItem = By.xpath("//a[contains(@ng-click,'openZeroValueModal')]");
	public By declarationOfFreeItem = By.xpath("//h3[contains(text(),'Declaration of free Items')]");
	public By whyExtra = By.xpath("//th[contains(text(),'Why Extra')]");
	public By quantity = By.xpath("//th/span[contains(text(),'Quantity')]");
	public By addZeroValueItem = By.xpath("//button[@ng-click='saveZeroValueItem()']");
	public By whyExtraTextBox = By.xpath("//input[@ng-model='obj.extraReason']");
	public By quantityTextBox = By.xpath("//input[@ng-model='obj.excessQty']");
	public By addErrorMessage = By.xpath("//div[contains(text(),'Extra Reason is mandatory')]");

	public By excessQuantity(int i) {
		return By.xpath(
				"(//div[@class='ui-grid-cell ng-scope ui-grid-coluiGrid-000C text-right']/div[@title])[" + i + "]");
	}

	public By createFRQBtnBy = By.xpath("//*[@id='myModalprev']//*[contains(text(),'Create RFQ')]");

	public By selectTemplateGroupBy = By.xpath("//*[@ng-model='templategroupId']");

	public By createRFQFromIndentSubmitBtn = By
			.xpath("//*[contains(@ng-click,'createRFQFromIndent')][text()='Submit']");

	public By EmailOTP = By.xpath("//input[@name='validOtpEmail']");

	public By OtpValidity = By.xpath("//div[@ng-show='showOtpValidStatus']");

	public By savevendor = By.xpath("//a[@data-original-title='Save']");

	public By ValidateSubmit = By.xpath("//button[@ng-click='verifyOtp()']");

	public By btnOK = By.xpath("//button[text()='OK']");

	public By TabItemCategory = By.xpath("//a[normalize-space(text())='Item Category']");

	public By TabOrganization = By.xpath("//a[normalize-space(text())='Organization']");

	public By btnGetOTP = By.xpath("//button[@ng-click='getOtp()']");
	public By mobileNoField = By.xpath("//label[contains(text(),'Mobile number is invalid!')]");
	public By showList = By.xpath("//span[@class='ng-binding ng-scope']/../following-sibling::td//span//i");
public By Accommodationandfoodserviceactivities = By.xpath("//span[text()='Accommodation and food service activities']/preceding-sibling::span//span");
	
	public By btnSubmit = By.xpath("//a[@data-original-title='Submit']/button");
	
	public By UserName = By.xpath("//input[@name='userid']");
	
	public By AcceptDisclaimer = By.xpath("//a[text()='Disclaimer']/../preceding-sibling::span");
	
	public By MasterOrgList = By.xpath("//h3[text()='Master Organization List']/../following-sibling::div/input");
	
	public By addorg = By.xpath("//a[@name='addToOrg']");
	
	public By usercodeunavailable =  By.xpath("//li[text()='Usercode not available']");
	
	public By SubmitUserName = By.xpath("(//button[text()='Submit'])[2]");
	
	public By CharLimit = By.xpath("//input[@name='userid']/../preceding-sibling::label[2]");
	public By OTPValidity = By.xpath("//div[contains(text(),'Resend button will be active in 15 sec(s)')]");
	public By continueWithTRN = By.xpath("//input[@value='UAN']");
	public By TRNNumber = By.xpath("//input[@name='verifyUan']");
	public By verifiedEmailAddress = By.xpath("//input[@name='verifyEmail']");
	public By verifiedMobileNumber = By.xpath("//input[@name='verifyMobile']");
	public By bidderRegsuccessMesg = By.xpath("//div[contains(text(),'Bidder Registration Data Is Submitted Successfully.')]");
	public By editTemplate = By.xpath("//button[contains(@ng-click,'editOrgTemplate')]");
	public By GSTIN = By.xpath("//select[@ng-model='field.value']");
	public By GSTINUpload = By.xpath("//input[@type='file']");
	
	public By OrganizationItemCategory = By.xpath("//a[text()='Organization Item Category']");
	public By OrgDatasuccessMesg = By.xpath("//div[contains(text(),'Organization Data Saved Successfully')]");
	public By saveOrgTabData = By.xpath("//button[contains(@ng-click,'saveOrgTabData')]");
	public By closeOrgDetail = By.xpath("//button[@ng-click='closeEditModal()']");
	public By actionPONum(String PoNum) {
		return By.xpath("//span[text()='"+PoNum+"']/../..//button[@class='btn btn-primary dropdown-toggle' and @data-toggle='dropdown']");
		}
	public By viewAcceptedQuantity(int index) {
		return By.xpath("(//b[text()='Accepted Quantity : ']//../../../following-sibling::div//b)["+index+"]");
		}
	public By MarketPriceVal = By.xpath("(//div[contains(text(),'Decrement')]/../../div)[3]");
	
	//Indent locator
	public By IndentIcon = By.xpath("//span[contains(text(),'Indent')]//parent::a//i[@class='fa fa-indent']");
	public By IndentCreation = By.xpath("//a[normalize-space()='Indent Creation']");
	public By Loader_Indent = By.xpath("//div[@class='spinner']");
	public By Lbl_IndentCreation = By.xpath("//h3[normalize-space()='Indent Creation']");
	public By Indent_TG_select = By.xpath("//select[@name='templateGroup']");
	public By Indent_TG_View = By.xpath("//button[normalize-space()='View']");

	//Indent General information page
	public By IndentRefNo= By.xpath("//input[@id='indent_general_info.indent_ref_no.0']");
	public By IndentCategory = By.xpath("//select[@id='indent_general_info.indent_category.0']");
	public By IndentCurrency = By.xpath("//select[@id='indent_general_info.indent_currency.0']");
	public By EstimatedPrice_Indent = By.xpath("//input[@id='indent_general_info.estimat_price.0']");
	public By ProcMode_Indent = By.xpath("//select[@id='indent_general_info.sugg_mode_of_proc.0']");
	public By GenDesc_Indent = By.xpath("//textarea[@id='indent_general_info.indent_desc.0']");
	public By GenInfo_Remarks_Indent = By.xpath("//textarea[@id='indent_general_info.Remarks.0']");
	public By GenInfo_NormalEmergency_Indent = By.xpath("//select[@id='indent_general_info.Normal_Emergency.0']");

	public By Savebtn_Indent = By.xpath("//button[@data-original-title='Save']");
	public By Submitbtn_Indent = By.xpath("//button[@ng-click=\"isIndentApprovable('submit')\"]");
	
	//Indent Details tab for TG1
	public By IndentDetailsTab = By.xpath("//a[@id='rdcis_indnt_dtls_final_mm']");
	public By TypeOfService_TG1IndentDetailsTab = By.xpath("//select[@id='rdcis_indnt_dtls_final_mm.Type_of_Service_Work.0']");
	public By Capital_TG1IndentDetailsTab = By.xpath("//select[@id='rdcis_indnt_dtls_final_mm.Nature_of_Indent.0']");
	public By ModeOfDespatch_TG1IndentDetailsTab = By.xpath("//select[@id='rdcis_indnt_dtls_final_mm.Mode_of_Dispatch.0']");
	public By PlaceOfDelivery_TG1IndentDetailsTab = By.xpath("//select[@id='rdcis_indnt_dtls_final_mm.Place_of_Delivery.0']");
	public By DeiveryPeriod_TG1IndentDetailsTab = By.xpath("//select[@id='rdcis_indnt_dtls_final_mm.Contrct_dlivry_priod.0']");
	public By NoOfYear_TG1IndentDetailsTab = By.xpath("//input[@id='rdcis_indnt_dtls_final_mm.dys_Month_Year.0']");
	public By BasisOfPriceEstimation_TG1IndentDetailsTab = By.xpath("//select[@id='rdcis_indnt_dtls_final_mm.Basis_f_Prce_Estimtn.0']");
	public By PreBidMeeting_TG1IndentDetailsTab = By.xpath("//select[@id='rdcis_indnt_dtls_final_mm.Pre_Bid_Meeting.0']");
	//Indent Other Information tab for TG1
	public By TG1OtherinformationTab = By.xpath("//a[@id='rdcis_other_info_final']");
	public By WorkofContract_TG1OtherinformationTab = By.xpath("//select[@id='rdcis_other_info_final.Works_Contract.0']");
	public By PartySite_TG1OtherinformationTab = By.xpath("//select[@id='rdcis_other_info_final.Partys_Site.0']");
	//Indent BOM Item tab for TG1
	public By TG1IndentBOMItemTab = By.xpath("//a[@id='rdcis_bom_supply_final']");
	public By AddNonSORItemBtn_TG1BOMItemTab = By.xpath("//a[@data-target='#myModalalRfqNonSorItem-rdcis_bom_supply_final']//button[@type='button']");
	public By ItemCode_TG1BOMItemTab = By.xpath("//input[@id='rdcis_bom_supply_final.item_code.0']");
	public By ItemName_TG1BOMItemTab = By.xpath("//input[@id='rdcis_bom_supply_final.item_name.0']");
	public By UOM_TG1BOMItemTab = By.xpath("//select[@id='rdcis_bom_supply_final.uom.0']");
	public By Qty_TG1BOMItemTab = By.xpath("//input[@id='rdcis_bom_supply_final.item_qty.0']");
	public By UnitRate_TG1BOMItemTab = By.xpath("//input[@id='rdcis_bom_supply_final.sor_rate.0']");
	public By GSTPercent_TG1BOMItemTab = By.xpath("//input[@id='rdcis_bom_supply_final.gst.0']");
	//Indent BOM Services Tab for TG1
	public By TG1IndentBOMServicesTab = By.xpath("//a[@id='rdcis_bom_servce_cc_final']");
	public By AddNonSORItemBtn_TG1BOMServicesTab = By.xpath("//a[@data-target='#myModalalRfqNonSorItem-rdcis_bom_servce_cc_final']//button[@type='button']");
	public By ItemCode_TG1BOMServicesTab = By.xpath("//input[@id='rdcis_bom_servce_cc_final.item_code.0']");
	public By ItemName_TG1BOMServicesTab = By.xpath("//input[@id='rdcis_bom_servce_cc_final.item_name.0']");
	public By UOM_TG1BOMServicesTab = By.xpath("//select[@id='rdcis_bom_servce_cc_final.uom.0']");
	public By Qty_TG1BOMServicesTab = By.xpath("//input[@id='rdcis_bom_servce_cc_final.item_qty.0']");
	public By UnitRate_TG1BOMServicesTab = By.xpath("//input[@id='rdcis_bom_servce_cc_final.sor_rate.0']");
	public By GSTPercent_TG1BOMServicesTab = By.xpath("//input[@id='rdcis_bom_servce_cc_final.gst.0']");

	public By NextTabLink_Indent = By.xpath("//a[normalize-space()='Next']");
	//Indent Estimation sheet tab for TG1
	public By TG1IndentEstimationSheetTab = By.xpath("//a[@id='indent_estmtn_sheet_final']");
	//Indent Technical Specification tab for TG1
	public By TG1IndentTechnicalSpecificationTab = By.xpath("//a[@id='rdcis_cmpl_tech_spe_final']");
	public By Addbtn_TG1TechnicalSpecificationTab = By.xpath("//button[@class='btn btn-primary btn-circle btn-lg btn-lg-sub ng-scope']");
	public By ClauseNo_TG1TechnicalSpecificationTab = By.xpath("//input[@id='rdcis_cmpl_tech_spe_final.Clause_subcla_No.0']");
	public By ClauseHeaderTitle_TG1TechnicalSpecificationTab = By.xpath("//input[@id='rdcis_cmpl_tech_spe_final.Clause_Header_Title.0']");
	//Indent Annexures tab for TG1
	public By TG1IndentAnnexuresTab = By.xpath("//a[@id='indent_attachment']");
	public By Addbtn_TG1AnnexuresTab = By.xpath("//button[@class='btn btn-primary btn-circle btn-lg btn-lg-sub pull-right margadj']");
	public By AddAtachmentLbl_TG1AnnexuresTab = By.xpath("//div[contains(@class,'modal fade in')]//h3[normalize-space()='Add Attachment']");
	public By AnnexuresType_TG1AnnexuresTab = By.xpath("//div[contains(@class,'modal fade in')]//select[@id='indent_attachment.label.0']");
	public By InpuFilename_TG1AnnexuresTab = By.xpath("//div[contains(@class,'modal fade in')]//input[@id='indent_attachment.file_name.0']");
	public By AttachFile_TG1AnnexuresTab = By.xpath("//input[contains(@ng-click,'aaaa()')]");
	public By OKBtn_TG1AnnexuresTab = By.xpath("//button[@id='add-authorRow']");
	
	//Indent workflow page
	public By Lbl_SendforApproval_Indent = By.xpath("//div[contains(@class,'modalsnd-main fade in')]//h3[@class='modal-title'][normalize-space()='Send For Approval']");
	public By SystemIndentNo = By.xpath("//div[contains(@class,'modalsnd-main fade in')]//form[@name='approvalForm']/table/tbody/tr/td[2]");
	public By UserDefinedWFchkbox_Indent = By.xpath("//div[contains(@class,'modalsnd-main fade in')]//input[@id='appYes']");
	public By NotReqdWFchkbox_Indent = By.xpath("//div[contains(@class,'modalsnd-main fade in')]//input[@id='appNo']");
	public By CompleteIndentbtn = By.xpath("//button[normalize-space()='Complete Indent']");
	public By NoOfIndentRowinApproval = By.xpath("//div[contains(@class,'modalsnd-main fade in')]//div[contains(@id,'homesndApprovl')]//tr[contains(@ng-repeat,'row in approval')]");
	public By cancelUser1_Indent = By.xpath("//table[@id='approver']/tbody/tr[1]/td[7]/button[@ng-click='cancelUser(row)']");
	public By userAdd_Indent = By.xpath("//table[@id='approver']/thead/tr[1]/th[7]/div/button[@id='addApprovertc']");
	public By user1_Indent = By.xpath("//table[@id='approver']/tbody/tr[1]/td[2]/input");
	public By approverType1_Indent = By.xpath("(//div[contains(@class,'modalsnd-main fade in')]//select[@id='filterTest'])[1]");
	public By CommentsArea_Indent = By.xpath("//div[contains(@class,'modalsnd-main fade in')]//textarea[@name='approvalComment']");
	public By Btn_SendforApproval_Indent = By.xpath("//div[@id='myModalss']//button[@id='dyantc']");

	public By IndentListLink = By.xpath("//a[normalize-space()='Indent List']");
	public By Lbl_IndentList = By.xpath("//h3[@class='page-header box_shadow']");
	public By IndentRowResult (String indentno) {return By.xpath("//table[contains(@id,'myTablebyrTl00')]//tr[@class='ng-scope']//td[contains(@title,'"+indentno+"')]");}
	public By IndentStatus_ListPage = By.xpath("//table[contains(@id,'myTablebyrTl00')]//tr[@class='ng-scope']//td[4]");
	public By Txt_TypeanyKeyword_Indent = By.xpath("//input[@placeholder='Type any keyword']");
	public By ActionBtn_Listpage_Indent = By.xpath("//button[@id='menu1']");
	public By DropdownmenuListpage_Indent = By.xpath("//ul[contains(@class,'dropdown-menu extended logout big_actv')]");
	public By MarkForRFQBtn_Indent = By.xpath("//a[normalize-space()='Mark For RFQ']");
	public By ConfBox_Indent = By.xpath("//div[@class='bootbox modal fade bootbox-confirm in']//div[@class='modal-content']");
	public By Confbtn_Indent = By.xpath("//button[normalize-space()='Confirm']");
	public By Alertbox_Indent = By.xpath("//div[@class='bootbox modal fade bootbox-alert in']//div[@class='modal-content']");
	public By Okbtn_Indent = By.xpath("//button[normalize-space()='OK']");
	
	public By IndentAssignmentlnk_Indent = By.xpath("//a[normalize-space()='Indent Assignment']");
	public By IndentAssignmentListLbl_Indent = By.xpath("//span[normalize-space()='Indent Assignment List']");
	public By DropdownmenuAssignmentListpage_Indent = By.xpath("//div[contains(@class,'open')]//ul[contains(@class,'dropdown-menu extended logout big_actv')]");
	public By ClaimLnkAssignmentListpage_Indent = By.xpath("//a[normalize-space()='Claim']");
	public By AssignUserLnkAssignmentListpage_Indent = By.xpath("//a[normalize-space()='Assign User']");
	public By AssignUsermodal_Indent = By.xpath("//div[@id='assignUserModal' and contains(@class,'fade in')]");
	public By TypeanyKeyword_AssignUsermodal_Indent = By.xpath("//input[@ng-model='userFilter']");
	public By AddUser_AssignUsermodal_Indent = By.xpath("//a[@name='add2Group']");
	public By Remarks_AssignUsermodal_Indent = By.xpath("//div[@id='assignUserModal' and contains(@class,'fade in')]//textarea[@ng-model='user.comment']");
	public By Submitbtn_AssignUsermodal_Indent = By.xpath("//div[@id='assignUserModal']//button[@id='btn-prevOk']");
	
	public By RevertToICLnkAssignmentListpage_Indent = By.xpath("//a[normalize-space()='Revert back to IC']");
	public By RevertToICbox_Indent = By.xpath("//div[@id='reviededBackICModal']//div[@class='modal-content']");
	public By ReasonofRevertTxtbox_Indent = By.xpath("//textarea[contains(@ng-model,'reviewedIndent.reviewedReason')]");
	public By Revertbox_Submitbtn_Indent = By.xpath("//button[contains(@ng-click,'reviewedIndent(reviewedIndent.reviewedReason)')]");
	public By CreateRFQfromIndentLbl_Indent = By.xpath("//span[normalize-space()='Create RFQ From Indent']");
	public By IndentStatus_AssignmentListPage = By.xpath("//table[contains(@id,'myTablebyrTl00')]//tr[@class='ng-scope']//td[7]");
	public By Txt_TypeanyKeyword_RFQfromIndent = By.xpath("//input[@ng-model='assignmentFilter']");
	public By CreateRFQLnk_RFQfromIndent = By.xpath("//a[@name='createRFQ']");
	public By IndentPreviewPage_RFQfromIndent = By.xpath("//div[@id='myModalprev' and contains(@class,'fade in')]");
	public By CreateRFQbtn_IndentPreview = By.xpath("//div[@id='myModalprev']//button[@id='btn-create']");
	public By TGselectionmodal_RFQfromIndent = By.xpath("//div[@id='tenderGroupsModal' and contains(@class,'fade in')]");
	public By SelectTG_RFQfromIndent = By.xpath("//select[@ng-model='templategroupId']");
	public By Submitbtn_RFQfromIndent = By.xpath("//button[@ng-click='createRFQFromIndent(indentIdForRFQCreation,templategroupId);']");
	
//Publish tender with RFQ TG1 from indent TG1
	//Terms and Conditions tab
	public By TermsandConditionstabLnk_Tender_TG1 = By.xpath("//a[contains(text(),'Terms and Conditions')]");
	public By AddBtnTermsandConditionstab_Tender_TG1 = By.xpath("//div[@id='rdcis_rfq_terms_cnditions']//button[@class='btn btn-primary btn-circle btn-lg btn-lg-sub ng-scope']");
	public By ClauseTxtTermsandConditionstab_Tender_TG1 = By.xpath("//input[@id='rdcis_rfq_terms_cnditions.clause_no.0']");
	public By ClauseHeaderTxtTermsandConditionstab_Tender_TG1 = By.xpath("//input[@id='rdcis_rfq_terms_cnditions.Header.0']");
	//General Requirement Equipment details
	public By GeneralReqEquiptabLnk_Tender_TG1 = By.xpath("//a[contains(text(),'General Requirement Equiptment Details')]");
	//General information Clauses
	public By GeneralInfoClausestabLnk_Tender_TG1 = By.xpath("//a[contains(text(),'General Information Clauses')]");
	//Attachments tab
	public By AttachmentstabLnk_Tender_TG1 = By.xpath("//a[normalize-space()='Attachments']");
	public By AttachmentsRowLbl_Tender_TG1 = By.xpath("//div[@id='rfqattachment']//tbody//tr[contains(@ng-if,'tenderTemplateTabs')]");
	//Required Attachment tab
	public By ReqAttachmenttabLnk_Tender_TG1 = By.xpath("//a[normalize-space()='Required Attachment']");
	public By AddBtnReqAttachmenttab_Tender_TG1 = By.xpath("//div[@id='requiredbidderattachment']//a[@data-target='#myModaladdrqr']//button");
	public By AttachmentModal_ReqAttachmenttab_Tender_TG1 = By.xpath("//div[@id='myModaladdrqr' and contains(@class,'fade in')]");
	public By SupportingdocTxt_ReqAttachmenttab_Tender_TG1 = By.xpath("//input[@id='requiredbidderattachment.supporting_doc.']");
	public By Okbtn_ReqAttachmenttab_Tender_TG1 = By.xpath("//button[@id='add-author3']");
	public By NextLnk_Tender_TG1 = By.xpath("//a[normalize-space()='Next']");
	//Pre-bid Discussion tab
	public By PreBidDiscussiontabLnk_Tender_TG1 = By.xpath("//a[contains(text(),'Pre-bid Discussion')]");
	//Payment tab
	public By PaymentabLnk_Tender_TG1 = By.xpath("//a[normalize-space()='Payment']");
	public By PaymentType_Paymentab_Tender_TG1 = By.xpath("//select[@id='rfqpayment.payment_type.0']");
	public By PaymentCurrency_Paymentab_Tender_TG1 = By.xpath("//select[@id='rfqpayment.payment_currency.0']");
	public By PaymentAmount_Paymentab_Tender_TG1 = By.xpath("//input[@id='rfqpayment.amount.0']");
	public By OfflineNEFT_Paymentab_Tender_TG1 = By.xpath("//input[@value='NEFT']");
	public By AddPayment_Paymentab_Tender_TG1 = By.xpath("//button[normalize-space()='Add Payment']");
	public By PaymentRow_Paymentab_Tender_TG1 = By.xpath("//div[@id='rfqpayment']//tr[contains(@class,'ng-scope')]");
	
	//Project Details tab
	public By ProjectDetailstabLnk_Tender_TG1 = By.xpath("//a[contains(text(),'Project Details')]");
	//Technical tab
	public By TechnicaltabLnk_Tender_TG1 = By.xpath("//a[contains(text(),'Technical')]");
	public By ClauseTxt_Technicaltab_Tender_TG1 = By.xpath("//input[@id='rdcis_rfq_cmpl_tech_speci.Clause_subcla_No.0']");
	//RFQ Item tab
	public By RFQItemtabLnk_Tender_TG1 = By.xpath("//a[contains(text(),'RFQ Item')]");
	public By ItemCodeTxt_RFQItemtab_Tender_TG1 = By.xpath("//input[@id='rdcis_rfq_bom_supply.item_code.0']");
	//BOQ Mandatory tab
	public By BOQMandatorytabLnk_Tender_TG1 = By.xpath("//a[contains(text(),'BOQ (Mandatory)')]");
	public By ItemCodeTxt_BOQMandatorytab_Tender_TG1 = By.xpath("//input[@id='rdcis_rfq_bom_services.item_code.0']");
	
//Bid submission for tender with RFQ TG1 from indent TG1
	//Terms and Conditions tab
	public By TermsandConditionstabLnk_BidSubmission_TG1 = By.xpath("//a[@id='rdcis_quotatn_trms_cnditn']");
	public By ClauseTxtTermsandConditionstab_BidSubmission_TG1 = By.xpath("//input[@id='rdcis_quotatn_trms_cnditn.clause_no.0']");
	public By RemarksTxtTermsandConditionstab_BidSubmission_TG1 = By.xpath("//textarea[@id='rdcis_quotatn_trms_cnditn.Remarks.0']");
	//Technical Compliance tab
	public By TechnicalCompliancetabLnk_BidSubmission_TG1 = By.xpath("//a[@id='rdcis_quotaton_tech_speci']");
	public By ClauseTxtTechnicalCompliancetab_BidSubmission_TG1 = By.xpath("//input[@id='rdcis_quotaton_tech_speci.Clause_subcla_No.0']");
	public By RemarksTxtTechnicalCompliancetab_BidSubmission_TG1 = By.xpath("//textarea[@id='rdcis_quotaton_tech_speci.Remarks.0']");
	//Attachments tab
	public By AttachmentstabLnk_BidSubmission_TG1 = By.xpath("//a[@id='qout_attachment']");
	public By Attachment_subtabLnk_BidSubmission_TG1 = By.xpath("//a[normalize-space()='Attachment']");
	public By TenderAttachment_subtabLnk_BidSubmission_TG1 = By.xpath("//a[normalize-space()='Tender Attachment']");
	public By ActionbtnBidderspecificAttachment_BidSubmission_TG1 = By.xpath("//td[@class='ng-scope']//button[@type='button']");
	public By UploadBidderspecificAttachment_BidSubmission_TG1 = By.xpath("//input[@id='bidderInput_0']");
	//Technical Tab
	public By TechnicaltabLnk_BidSubmission_TG1 = By.xpath("//a[@id='tech_quotaton_bom_service']");
	//Specifications and Technical Requirements Compliance tab
	public By TechnicalReqComptabLnk_BidSubmission_TG1 = By.xpath("//a[@id='tech_quotaton_bom_supply']");
	//Commercial Parameters Compliance tab
	public By CommercialComptabLnk_BidSubmission_TG1 = By.xpath("//a[@id='rdcis_quotatn_estimtn_sht']");
	//General Requirement Equipment Details tab
	public By GeneralReqEqiptabLnk_BidSubmission_TG1 = By.xpath("//a[@id='ordr_placement_details']");
	public By Address_GeneralReqEqiptab_BidSubmission_TG1 = By.xpath("//textarea[@id='ordr_placement_details.name_address.0']");
	//Other Clauses Tab
	public By OtherClausestabLnk_BidSubmission_TG1 = By.xpath("//a[@id='gem_msme_certifcte_detail']");
	public By RegisteredGEM_OtherClausestab_BidSubmission_TG1 = By.xpath("//select[@id='gem_msme_certifcte_detail.Registered_in_GeM.0']");
	public By VendorCode_OtherClausestab_BidSubmission_TG1 = By.xpath("//input[@id='gem_msme_certifcte_detail.GeM_vendor_code.0']");
	public By Startupbidder_OtherClausestab_BidSubmission_TG1 = By.xpath("//select[@id='gem_msme_certifcte_detail.start_up_bidder.0']");
	public By GovtOrg_OtherClausestab_BidSubmission_TG1 = By.xpath("//select[@id='gem_msme_certifcte_detail.govt_PSU.0']");
	public By RepresentingFirm_OtherClausestab_BidSubmission_TG1 = By.xpath("//select[@id='gem_msme_certifcte_detail.Section_six.0']");
	public By RepresentingFirmDetail_OtherClausestab_BidSubmission_TG1 = By.xpath("//textarea[@id='gem_msme_certifcte_detail.Section_six_details.0']");
	public By RepresentingFirmRelationship_OtherClausestab_BidSubmission_TG1 = By.xpath("//select[@id='gem_msme_certifcte_detail.representing_firm.0']");
	public By RepresentingFirmRelationshipDetail_OtherClausestab_BidSubmission_TG1 = By.xpath("//textarea[@id='gem_msme_certifcte_detail.representing_firm_de.0']");
	public By MSME_OtherClausestab_BidSubmission_TG1 = By.xpath("//select[@id='gem_msme_certifcte_detail.r_u_msme_bidder.0']");
	public By UAN_MSME_OtherClausestab_BidSubmission_TG1 = By.xpath("//input[@id='gem_msme_certifcte_detail.uan_number.0']");
	public By EMNo_MSME_OtherClausestab_BidSubmission_TG1 = By.xpath("//input[@id='gem_msme_certifcte_detail.em_no.0']");
	public By Type_MSME_OtherClausestab_BidSubmission_TG1 = By.xpath("//select[@id='gem_msme_certifcte_detail.Type_of_Enterprise.0']");
	public By ActivityType_MSME_OtherClausestab_BidSubmission_TG1 = By.xpath("//select[@id='gem_msme_certifcte_detail.Type_of_Activity.0']");
	public By OrgType_MSME_OtherClausestab_BidSubmission_TG1 = By.xpath("//select[@id='gem_msme_certifcte_detail.Type_of_Organization.0']");
	public By WomenOwned_MSME_OtherClausestab_BidSubmission_TG1 = By.xpath("//select[@id='gem_msme_certifcte_detail.WOMEN_Entrepreneur.0']");
	public By Gender_MSME_OtherClausestab_BidSubmission_TG1 = By.xpath("//select[@id='gem_msme_certifcte_detail.Gender.0']");
	public By PhyHand_MSME_OtherClausestab_BidSubmission_TG1 = By.xpath("//select[@id='gem_msme_certifcte_detail.Physicaly_Handicaped.0']");
	public By SocialCat_MSME_OtherClausestab_BidSubmission_TG1 = By.xpath("//select[@id='gem_msme_certifcte_detail.Social_Category.0']");
	public By LocationPlant_MSME_OtherClausestab_BidSubmission_TG1 = By.xpath("//input[@id='gem_msme_certifcte_detail.Location_of_Plant.0']");
	public By FirmType_MSME_OtherClausestab_BidSubmission_TG1 = By.xpath("//select[@id='gem_msme_certifcte_detail.Type_of_Your_Firm.0']");
	public By AnyotherFirmType_MSME_OtherClausestab_BidSubmission_TG1 = By.xpath("//input[@id='gem_msme_certifcte_detail.tender_item_any_othe.0']");
	public By ScopeOfProc_MSME_OtherClausestab_BidSubmission_TG1 = By.xpath("//select[@id='gem_msme_certifcte_detail.Scope_Job_Procuremnt.0']");
	public By Certification_MSME_OtherClausestab_BidSubmission_TG1 = By.xpath("//select[@id='gem_msme_certifcte_detail.Firm_MSE_Certficatin.0']");
	//Payment tab
	public By PaymenttabLnk_BidSubmission_TG1 = By.xpath("//a[@id='quotation_payment']");
	public By Paymentbtn_Paymenttab_BidSubmission_TG1 = By.xpath("//button[@name='payment']");
	public By PaymentType_Paymenttab_BidSubmission_TG1 = By.xpath("//input[@name='paymentType']");
	public By BankNametxt_Paymenttab_BidSubmission_TG1 = By.xpath("//div[@id='paySection']//input[@name='bankName']");
	public By BranchNametxt_Paymenttab_BidSubmission_TG1 = By.xpath("//div[@id='paySection']//input[@name='branchName']");
	public By InstruNotxt_Paymenttab_BidSubmission_TG1 = By.xpath("//div[@id='paySection']//input[@name='instrumentNo']");
	public By InstruDatetxt_Paymenttab_BidSubmission_TG1 = By.xpath("//div[@id='paySection']//input[@name='instrumentDate']");
	public By Commenttxt_Paymenttab_BidSubmission_TG1 = By.xpath("//div[@id='paySection']//input[@name='comment']");
	public By InstruExpiryDate_Paymenttab_BidSubmission_TG1 = By.xpath("//div[@id='paySection']//input[@name='expiryDate']");
	public By Uploadfile_Paymenttab_BidSubmission_TG1 = By.xpath("//input[@file-model='fileModel.offlinePaymentFile']");
	public By SavePaymentBtn_Paymenttab_BidSubmission_TG1 = By.xpath("//button[normalize-space()='Save Payment']");
	//RFQ Item tab
	public By RFQItemtabLnk_BidSubmission_TG1 = By.xpath("//a[@id='rdcis_quotaton_bom_servce']");
	public By UnitRateTxt_RFQItemtab_BidSubmission_TG1 = By.xpath("//input[@id='rdcis_quotaton_bom_servce.Unit_Rate.0']");
	public By CGSTTxt_RFQItemtab_BidSubmission_TG1 = By.xpath("//input[@id='rdcis_quotaton_bom_servce.cgst_per.0']");
	public By SGSTTxt_RFQItemtab_BidSubmission_TG1 = By.xpath("//input[@id='rdcis_quotaton_bom_servce.sgst_per.0']");
	//BOQ Mandatory tab
	public By BOQMandatorytabLnk_BidSubmission_TG1 = By.xpath("//a[@id='rdcis_quotaton_bom_supply']");
	public By UnitRateTxt_BOQMandatorytab_BidSubmission_TG1 = By.xpath("//input[@id='rdcis_quotaton_bom_supply.Unit_Rate.0']");
	public By CGSTTxt_BOQMandatorytab_BidSubmission_TG1 = By.xpath("//input[@id='rdcis_quotaton_bom_supply.cgst_per.0']");
	public By SGSTTxt_BOQMandatorytab_BidSubmission_TG1 = By.xpath("//input[@id='rdcis_quotaton_bom_supply.sgst_per.0']");
	//Preview All page
	public By GeneralInfo_PreviwAllPage_TG1 = By.xpath("//div[@id='myModalprev_bid' and contains(@class,'fade in')]//h2[normalize-space()='General Information']");
	public By Terms_Conditions_PreviwAllPage_TG1 = By.xpath("(//div[@id='myModalprev_bid' and contains(@class,'fade in')]//h2[normalize-space()='Terms and Conditions'])[1]");
	public By Technical_ComplianceTable_PreviwAllPage_TG1 = By.xpath("(//div[@id='myModalprev_bid' and contains(@class,'fade in')]//h2[normalize-space()='Technical Compliance Table'])[1]");
	public By Tender_Attachment_PreviwAllPage_TG1 = By.xpath("//div[@id='myModalprev_bid' and contains(@class,'fade in')]//h2[normalize-space()='Tender Attachment']");
	public By BidderSpecAttachment_PreviwAllPage_TG1 = By.xpath("//div[@id='myModalprev_bid' and contains(@class,'fade in')]//h2[normalize-space()='Bidder Specific Attachment']");
	public By Technical_PreviwAllPage_TG1 = By.xpath("(//div[@id='myModalprev_bid' and contains(@class,'fade in')]//h2[normalize-space()='Technical'])[1]");
	public By SpecTechReq_PreviwAllPage_TG1 = By.xpath("(//div[@id='myModalprev_bid' and contains(@class,'fade in')]//h2[contains(text(),'Specifications and Technical Requirements Complian')])[1]");
	public By CommercialParam_PreviwAllPage_TG1 = By.xpath("//div[@id='myModalprev_bid' and contains(@class,'fade in')]//h2[normalize-space()='Commercial Parameters Compliance']");
	public By GeneralReq_PreviwAllPage_TG1 = By.xpath("//div[@id='myModalprev_bid' and contains(@class,'fade in')]//h2[normalize-space()='General Requirement Equiptment Details']");
	public By OtherClauses_PreviwAllPage_TG1 = By.xpath("//div[@id='myModalprev_bid' and contains(@class,'fade in')]//h2[normalize-space()='Other Clauses']");
	public By PaymentDetails_PreviwAllPage_TG1 = By.xpath("//div[@id='myModalprev_bid' and contains(@class,'fade in')]//h2[normalize-space()='Payment Details']");
	public By RFQItem_PreviwAllPage_TG1 = By.xpath("(//div[@id='myModalprev_bid' and contains(@class,'fade in')]//h2[normalize-space()='RFQ Item'])[1]");
	public By BOQMan_PreviwAllPage_TG1 = By.xpath("(//div[@id='myModalprev_bid' and contains(@class,'fade in')]//h2[normalize-space()='BOQ (Mandatory)'])[1]");
	//Tender Preview bid list page
	public By PreviewAll_TenderPreviewBidListPage_TG1 = By.xpath("//div[@id='myModalprev' and contains(@class,'fade in')]");
	public By GeneralInfo_TenderPreviewBidListPage_TG1 = By.xpath("//div[@id='myModalprev' and contains(@class,'fade in')]//div[normalize-space()='General Information']");
	public By Terms_Conditions_TenderPreviewBidListPage_TG1 = By.xpath("//div[@id='myModalprev' and contains(@class,'fade in')]//div[normalize-space()='Terms and Conditions']");
	public By GeneralReq_TenderPreviewBidListPage_TG1 = By.xpath("//div[@id='myModalprev' and contains(@class,'fade in')]//div[normalize-space()='General Requirement Equiptment Details']");
	public By GeneralInfoClauses_TenderPreviewBidListPage_TG1 = By.xpath("//div[@id='myModalprev' and contains(@class,'fade in')]//div[normalize-space()='General Information Clauses']");
	public By Attachments_TenderPreviewBidListPage_TG1 = By.xpath("//div[@id='myModalprev' and contains(@class,'fade in')]//div[normalize-space()='Attachments']");
	public By ReqAttachment_TenderPreviewBidListPage_TG1 = By.xpath("//div[normalize-space()='Required Attachment']");
	public By Payment_TenderPreviewBidListPage_TG1 = By.xpath("//div[@id='myModalprev' and contains(@class,'fade in')]//div[normalize-space()='Payment']");
	public By RFQItem_TenderPreviewBidListPage_TG1 = By.xpath("//div[@id='myModalprev' and contains(@class,'fade in')]//div[normalize-space()='RFQ Item']");
	public By BOQMandatory_TenderPreviewBidListPage_TG1 = By.xpath("//div[@id='myModalprev' and contains(@class,'fade in')]//div[normalize-space()='BOQ (Mandatory)']");
	public By DateSchedule_TenderPreviewBidListPage_TG1 = By.xpath("//div[@id='myModalprev' and contains(@class,'fade in')]//div[normalize-space()='Date Schedule']");
	//Bid Preview bid list page
	public By PreviewAll_BidPreviewBidListPage_TG1 = By.xpath("//div[@id='myModalprev_bid' and contains(@class,'fade in')]");
	//Bid details in evaluation for cover1 for TG1
	public By TG1_generalInformation_bidDetails = By.xpath("//a[text()='General Information']");
	public By TG1_TermsAndConditions_bidDetails = By.xpath("//a[text()='Terms and Conditions']");
	public By TG1_TechnicalComp_bidDetails = By.xpath("//a[text()='Technical Compliance Table']");
	public By TG1_Attachments_bidDetails = By.xpath("//a[text()='Attachments']");
	public By TG1_Technical_bidDetails = By.xpath("//a[text()='Technical']");
	public By TG1_SpecificationAndTechnicalReq_bidDetails = By.xpath("//a[text()='Specifications and Technical Requirements Compliance']");
	public By TG1_OtherClauses_bidDetails = By.xpath("//a[text()='Other Clauses']");
	//Bid details in evaluation for cover2 for TG1
	public By TG1_CommercialParam_bidDetails = By.xpath("//a[text()='Commercial Parameters Compliance']");
	public By TG1_GeneralReqEquip_bidDetails = By.xpath("//a[text()='General Requirement Equiptment Details']");
	public By TG1_Payment_bidDetails = By.xpath("//a[text()='Payment']");
	public By TG1_RFQItem_bidDetails = By.xpath("//a[text()='RFQ Item']");
	public By TG1_BOQMandatory_bidDetails = By.xpath("//a[text()='BOQ (Mandatory)']");
	
	//Indent Approver
	public By Lbl_workflowinbox = By.xpath("//h3[normalize-space()='Workflow Inbox (Pending & complete list)']");
	public By IndentRowResult_Approver (String indentno) { return By.xpath("//table[contains(@id,'myTable')]//tr[@class='ng-scope']//td[contains(text(),'"+indentno+"')]");}
	public By Actionbtn_IndentApprover = By.xpath("//button[@id='menu1']");
	public By Detailbtn_IndentApprover = By.xpath("//a[normalize-space()='Details']");
	public By LblAppCmnt_IndentApprover = By.xpath("//strong[normalize-space()='Approver Comment']");
	public By AppComments_Indent = By.xpath("//textarea[contains(@ng-model,'overallComment')]");
	public By ApproveBtn_Indent = By.xpath("//button[@id='approve']");
	public By SendBaackBtn_Indent = By.xpath("//button[@id='review']");
	public By CloseWFBtn_Indent = By.xpath("//button[normalize-space()='Close Workflow']");
	public By ConfirmYESBtn_Indent = By.xpath("//button[@class='btn btn-primary'][normalize-space()='Yes']");
	public By SuccessMsg_IndentApproval = By.xpath("//div[@class='bootbox modal fade bootbox-alert in']//div[@class='modal-body']");
	public By OKBtn_IndentApproval = By.xpath("//button[normalize-space()='OK']");
	public By NoBtn_IndentApproval = By.xpath("//button[@data-bb-handler='no']");

	//Publish tender with RFQ TG3
	//Terms and Conditions tab
	public By TermsandConditionstabLnk_Tender_TG3 = By.xpath("//a[contains(text(),'Terms and Conditions')]");
	public By AddBtnTermsandConditionstab_Tender_TG3 = By.xpath("//div[@id='rh_terms']//button[@class='btn btn-primary btn-circle btn-lg btn-lg-sub ng-scope']");
	public By ClauseNoTxtTermsandConditionstab_Tender_TG3 = By.xpath("//input[@id='rh_terms.Clause_No.0']");
	public By ClauseTxtTermsandConditionstab_Tender_TG3 = By.xpath("//textarea[@id='rh_terms.RH_terms_Clause.0']");
	public By DetailsTxtTermsandConditionstab_Tender_TG3 = By.xpath("//textarea[@id='rh_terms.RH_terms_Details.0']");
	//Commercial Terms and Conditions tab
	public By CommercialTermsandConditionstabLnk_Tender_TG3 = By.xpath("//a[contains(text(),'Commercial Terms & Conditions')]");
	public By AddBtnCommercialTermsandConditionstab_Tender_TG3 = By.xpath("//div[@id='rh_man_terms']//button[@class='btn btn-primary btn-circle btn-lg btn-lg-sub ng-scope']");
	public By ClauseNoTxtCommercialTermsandConditionstab_Tender_TG3 = By.xpath("//input[@id='rh_man_terms.Clause_No.0']");
	public By ClauseTxtCommercialTermsandConditionstab_Tender_TG3 = By.xpath("//textarea[@id='rh_man_terms.RH_terms_Clause.0']");
	public By DetailsTxtCommercialTermsandConditionstab_Tender_TG3 = By.xpath("//textarea[@id='rh_man_terms.RH_terms_Details.0']");
	//Attachments tab
	public By AttachmentstabLnk_Tender_TG3 = By.xpath("//a[normalize-space()='Attachments']");
	public By AddBtnAttachmenttab_Tender_TG3 = By.xpath("//div[@id='rfqattachment']//button[contains(@ng-click,'openAttachmentModal')]");
	public By AddAttachmentLbl_Attachments_Tender_TG3 = By.xpath("//div[@id='myModaladd' and contains(@class,'fade in')]//h3[normalize-space()='Add Attachment']");
	public By LblTxtAttachmenttab_Tender_TG3 = By.xpath("//div[@id='myModaladd' and contains(@class,'fade in')]//input[@id='rfqattachment.label.0']");
	public By FileNameinputAttachmenttab_Tender_TG3 = By.xpath("//div[@id='myModaladd' and contains(@class,'fade in')]//input[@type='file']");
	public By OKBtnAttachmenttab_Tender_TG3 = By.xpath("//button[@id='add-authorRow']");
	//Price Format tab
	public By PriceFormattabLnk_Tender_TG3 = By.xpath("//a[contains(text(),'Price Format')]");
	public By AddBtnPriceFormattab_Tender_TG3 = By.xpath("//div[@id='ri_mro']//button[contains(@ng-click,'addNonSORItem')]");
	public By PRnoTxtPriceFormattab_Tender_TG3 = By.xpath("//input[@id='ri_mro.pr_no.0']");
	public By PRDatePriceFormattab_Tender_TG3 = By.xpath("//input[@id='ri_mro.PR_Date.0']");
	public By PRLineitemPriceFormattab_Tender_TG3 = By.xpath("//input[@id='ri_mro.PR_Line_Itemno.0']");
	public By EPSMatCodePriceFormattab_Tender_TG3 = By.xpath("//input[@id='ri_mro.item_code.0']");
	public By SAPMatCodePriceFormattab_Tender_TG3 = By.xpath("//input[@id='ri_mro.SAP_Matl_Code.0']");
	public By ShortDescPriceFormattab_Tender_TG3 = By.xpath("//input[@id='ri_mro.Short_desc.0']");
	public By QtyPriceFormattab_Tender_TG3 = By.xpath("//input[@id='ri_mro.item_qty.0']");
	public By UOMPriceFormattab_Tender_TG3 = By.xpath("//select[@id='ri_mro.uom.0']");
	public By PRValPriceFormattab_Tender_TG3 = By.xpath("//input[@id='ri_mro.sor_rate.0']");
	public By DeliveryDatePriceFormattab_Tender_TG3 = By.xpath("//input[@id='ri_mro.del_date.0']");
	public By OrganizationPriceFormattab_Tender_TG3 = By.xpath("//input[@id='ri_mro.Purchase_org.0']");
	public By CompanyNamePriceFormattab_Tender_TG3 = By.xpath("//input[@id='ri_mro.company_name.0']");
	public By PlantCodePriceFormattab_Tender_TG3 = By.xpath("//input[@id='ri_mro.Plant_Code.0']");
	public By PlantNamePriceFormattab_Tender_TG3 = By.xpath("//input[@id='ri_mro.Plant_Name.0']");
	public By PurchaseGroupPriceFormattab_Tender_TG3 = By.xpath("//input[@id='ri_mro.Purchase_group.0']");
	public By UserEmailPriceFormattab_Tender_TG3 = By.xpath("//input[@id='ri_mro.user_emilid.0']");
	//Pre-bid discussion tab
	public By PreBidDiscussiontabLnk_Tender_TG3 = By.xpath("//a[contains(text(),'Pre-bid Discussion')]");
	public By Type_PreBidDiscussiontab_Tender_TG3 = By.xpath("//select[@id='prebiddiscussion.discussion_type.0']");
//Bid Submission for RFQ TG3
	//Date schedule tab
	public By DateScheduletabLnk_BidSubmission_TG3 = By.xpath("//a[@id='qout_attachment']");
	public By AttachmentSubtabLnk_BidSubmission_TG3 = By.xpath("//a[normalize-space()='Attachment']");
	public By AddAttachmentBtn_BidSubmission_TG3 = By.xpath("//div[contains(@id,'attchmentTab')]//button[@id='addAttach']");
	public By LabelTxt_AttachmentSubtab_BidSubmission_TG3 = By.xpath("//div[contains(@id,'attchmentTab')]//input[@ng-model='row.label']");
	public By Actionbtn_AttachmentSubtab_BidSubmission_TG3 = By.xpath("//div[contains(@id,'attchmentTab')]//button[@class='btn btn-primary dropdown-toggle']");
	public By FileInput_AttachmentSubtab_BidSubmission_TG3 = By.xpath("//div[contains(@id,'attchmentTab')]//input[@id='attachment_0']");
	public By TenderattachmentSubtabLnk_BidSubmission_TG3 = By.xpath("//a[normalize-space()='Tender Attachment']");
	//Commercial Terms and Conditions tab
	public By CommercialTermsConditionstabLnk_BidSubmission_TG3 = By.xpath("//a[@id='qh_term']");
	public By ClauseTxt_CommercialTermsConditionstab_BidSubmission_TG3 = By.xpath("//textarea[@id='qh_term.RH_terms_Clause.0']");
	//Terms and conditions tab
	public By TermsConditionstabLnk_BidSubmission_TG3 = By.xpath("//a[@id='qh_man_terms']");
	public By ClauseTxt_TermsConditionstab_BidSubmission_TG3 = By.xpath("//textarea[@id='qh_man_terms.RH_terms_Clause.0']");
	//General Information Clauses tab
	public By GeneralinfoClausestabLnk_BidSubmission_TG3 = By.xpath("//a[@id='unpriced_mro_domestic']");
	public By Mandatoryitemlbl_GeneralinfoClausestab_BidSubmission_TG3 = By.xpath("//div[@id='_unpriced_mro_domestic']//h3[contains(text(),'Mandatory Item')]");
	public By SupplirOfferedTxt_GeneralinfoClausestab_BidSubmission_TG3 = By.xpath("//input[@id='unpriced_mro_domestic.Supplier_offer_make.0']");
	public By HSNCodeTxt_GeneralinfoClausestab_BidSubmission_TG3 = By.xpath("//input[@id='unpriced_mro_domestic.hsn_sac.0']");
	public By OfferValidityTxt_GeneralinfoClausestab_BidSubmission_TG3 = By.xpath("//input[@id='unpriced_mro_domestic.offervalidity.0']");
	public By OfferDeliverPeriodTxt_GeneralinfoClausestab_BidSubmission_TG3 = By.xpath("//input[@id='unpriced_mro_domestic.del_period.0']");
	
	public By Nexttab_BidSubmission_TG3 = By.xpath("//a[@class='bx-next']");
	//Price Format tab
	public By PriceFormattabLnk_BidSubmission_TG3 = By.xpath("//a[@id='qi_mro']");
	public By Mandatoryitemlbl_PriceFormattab_BidSubmission_TG3 = By.xpath("//div[@id='_qi_mro']//h3[contains(text(),'Mandatory Item')]");
	public By QuotedPriceTxt_PriceFormattab_BidSubmission_TG3 = By.xpath("//input[@id='qi_mro.qtd_price.0']");
	public By OverallDiscountTxt_PriceFormattab_BidSubmission_TG3 = By.xpath("//input[@id='qi_mro.Disc_percentage.0']");
	public By GSTPercentageTxt_PriceFormattab_BidSubmission_TG3 = By.xpath("//input[@id='qi_mro.gst_perc.0']");
	//Price summary tab
	public By PriceSummarytabLnk_BidSubmission_TG3 = By.xpath("//a[@id='mro_price_summary']");
	public By IncotermsSelect_PriceSummarytab_BidSubmission_TG3 = By.xpath("//select[@id='mro_price_summary.Incoterms.0']");
	//Preview All page
	public By QuotationAttachment_PreviwAllPage_TG3 = By.xpath("//div[@id='myModalprev_bid' and contains(@class,'fade in')]//h2[normalize-space()='Quotation Attachment']");
	public By TenderAttachment_PreviwAllPage_TG3 = By.xpath("//div[@id='myModalprev_bid' and contains(@class,'fade in')]//h2[normalize-space()='Tender Attachment']");
	public By CommercialTermsCond_PreviwAllPage_TG3 = By.xpath("(//div[@id='myModalprev_bid' and contains(@class,'fade in')]//h2[normalize-space()='Commercial Terms & Conditions'])[1]");
	public By TermsCond_PreviwAllPage_TG3 = By.xpath("(//div[@id='myModalprev_bid' and contains(@class,'fade in')]//h2[normalize-space()='Terms and Conditions'])[1]");
	public By GeneralInfoClauses_PreviwAllPage_TG3 = By.xpath("(//div[@id='myModalprev_bid' and contains(@class,'fade in')]//h2[normalize-space()='General Information Clauses'])[1]");
	public By PriceFormat_PreviwAllPage_TG3 = By.xpath("(//div[@id='myModalprev_bid' and contains(@class,'fade in')]//h2[normalize-space()='Price Format'])[1]");
	public By PriceSummary_PreviwAllPage_TG3 = By.xpath("(//div[@id='myModalprev_bid' and contains(@class,'fade in')]//h2[normalize-space()='Price Summary'])[1]");
	// Tender Preview bid list page
	public By PreviewAll_TenderPreviewBidListPage_TG3 = By.xpath("//div[@id='myModalprev' and contains(@class,'fade in')]");
	public By GeneralInfo_TenderPreviewBidListPage_TG3 = By.xpath("//div[@id='myModalprev' and contains(@class,'fade in')]//div[normalize-space()='General Information']");
	public By Terms_Conditions_TenderPreviewBidListPage_TG3 = By.xpath("//div[@id='myModalprev' and contains(@class,'fade in')]//div[normalize-space()='Terms and Conditions']");
	public By CommercialTermsCond_TenderPreviewBidListPage_TG3 = By.xpath("//div[@id='myModalprev' and contains(@class,'fade in')]//div[normalize-space()='Commercial Terms & Conditions']");
	public By Attachments_TenderPreviewBidListPage_TG3 = By.xpath("//div[@id='myModalprev' and contains(@class,'fade in')]//div[normalize-space()='Attachments']");
	public By PriceFormat_TenderPreviewBidListPage_TG3 = By.xpath("//div[@id='myModalprev' and contains(@class,'fade in')]//div[normalize-space()='Price Format']");
	public By DateSchedule_TenderPreviewBidListPage_TG3 = By.xpath("//div[@id='myModalprev' and contains(@class,'fade in')]//div[normalize-space()='Date Schedule']");
	public By PrebidDiscussion_TenderPreviewBidListPage_TG3 = By.xpath("//div[@id='myModalprev' and contains(@class,'fade in')]//div[normalize-space()='Pre-bid Discussion']");
	//Bid details in evaluation for cover1 for TG3
	public By TG3_generalInformation_bidDetails = By.xpath("//a[text()='General Information']");
	public By TG3_DateSchedule_bidDetails = By.xpath("//a[text()='Date Schedule']");
	public By TG3_GeneralInfoClauses_bidDetails = By.xpath("//a[text()='General Information Clauses']");
	//Bid details in evaluation for cover2 for TG3
	public By TG3_CommercialTermsAndCond_bidDetails = By.xpath("//a[text()='Commercial Terms & Conditions']");
	public By TG3_TermsAndCond_bidDetails = By.xpath("//a[text()='Terms and Conditions']");
	public By TG3_PriceFormat_bidDetails = By.xpath("//a[text()='Price Format']");
	public By TG3_PriceSummary_bidDetails = By.xpath("//a[text()='Price Summary']");
	public By Savebtn_BidDetailspage_Evaluation= By.xpath("//button[@ng-click='saveOrUpdateEvaluation()']");
	public By Savebtn_BidDetailspage_TCUser_Evaluation= By.xpath("//button[@ng-click='saveInitiatorComments()']");
	public By AlertMsgmodal_BidDetailspage_Evaluation= By.xpath("//div[@id='alertMessageModal' and contains(@class,' in')]");
	public By ClosebtnAlertMsgmodal_BidDetailspage_Evaluation= By.xpath("//div[@id='alertMessageModal' and contains(@class,' in')]//button[normalize-space()='Close']");
	
	
	
	
}




