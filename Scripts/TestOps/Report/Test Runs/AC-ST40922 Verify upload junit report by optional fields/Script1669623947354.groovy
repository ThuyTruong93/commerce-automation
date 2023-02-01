import java.util.stream.Collectors

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import internal.GlobalVariable
import katalon.common.HeaderBar
import katalon.common.SignInPage
import katalon.fw.lib.Page
import katalon.model.BodySearch
import katalon.model.ConditionSearch
import katalon.services.SearchService
import katalon.testops.dashboard.DashboardPage
import katalon.testops.report.ReportPage
import katalon.testops.report.UploadReportPage
import katalon.testops.services.CustomFieldDefinitionService 

'PRE-CONDITON: Get total test run before run test'
int before = Page.nav(SearchService)
				.search(new BodySearch('Execution', 
						[new ConditionSearch('Project.id', '=', GlobalVariable.platformGenaralProjectId)]))
				.parseResponseBodyToJsonObject().totalElements

'PRE-CONDITON: Create new custom field'
Object customFieldData = Page.nav(CustomFieldDefinitionService).generateData()
Object customField = Page.nav(CustomFieldDefinitionService)
							.create(customFieldData)
							.parseResponseBodyToJsonObject()
customFieldId = customField.id.toString()

'Admin login to TO success'
Page.nav(SignInPage).enterCredential(GlobalVariable.owner_mail, GlobalVariable.owner_pass).clickSignIn()

'Select the project and navigate to Report page'
Page.nav(DashboardPage).selectProject(GlobalVariable.platformGeneralProject)

Page.nav(HeaderBar).clickReports()

'Navigate Upload report page'
Page.nav(ReportPage).navigateUploadReport()

'Upload JUnit report report'
Page.nav(UploadReportPage).uploadJUnitReport()

'Add Custom Field'
Page.nav(UploadReportPage)
	.clickAddNewCustomFieldJUnit()
	.inputCustomFieldDisplayName(customFieldData.key)
	.inputCustomFieldDisplayValue(customFieldData.customFieldOptions[0].value)
	.clickAddCustomField()

'Add Tag and upload report'
Page.nav(UploadReportPage).inputTagJUnit(customFieldData.key).clickImportJUnitBtn()

'Verify upload report successfully with optional fields'
Page.nav(UploadReportPage).verifyUploadReportSuccess()

'Verify the total of test run is counted'
int after = Page.nav(SearchService)
.search(new BodySearch('Execution',
		[new ConditionSearch('Project.id', '=', GlobalVariable.platformGenaralProjectId)]))
.parseResponseBodyToJsonObject().totalElements

WS.verifyGreaterThan(after, before)

'Verify show exactly test run when filter with optional fields'
String tagId = Page.nav(SearchService)
					.searchTagInfo()
					.parseResponseBodyToJsonObject()
					.content.find{ tag -> tag.name == customFieldData.key }.id

def searchResponseBody = Page.nav(SearchService)
	.search(new BodySearch('Execution', 
			[new ConditionSearch('Project.id', '=', GlobalVariable.platformGenaralProjectId), 
				new ConditionSearch('Tag.id', '=', tagId)], 
			[new ConditionSearch(String.format("${customFieldData.key}.opt_id" ), '=', customField.customFieldOptions[0].id)]))
	.parseResponseBodyToJsonObject()

List<String> actualResult = searchResponseBody.content.stream()
				   .map{r -> r.id.toString()}
				   .collect(Collectors.toList())

WS.verifyEqual(actualResult.size(), 1)

