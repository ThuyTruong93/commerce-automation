import java.util.stream.Collectors

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import internal.GlobalVariable
import katalon.common.HeaderBar
import katalon.common.SignInPage
import katalon.fw.lib.Page
import katalon.model.BodySearch
import katalon.model.BodySearchProjectScope
import katalon.model.ConditionSearch
import katalon.services.SearchService
import katalon.testops.dashboard.DashboardPage
import katalon.testops.report.ReportPage
import katalon.testops.report.TestRunsPage
import katalon.testops.services.CustomFieldDefinitionService
import katalon.testops.services.ExecutionsService


'PRE-CONDITON: Assign new custom field to Test Run'								
BodySearch searchTestRunData = new BodySearch('Execution',
								[new ConditionSearch('Project.id', '=', GlobalVariable.platformGenaralProjectId),
								 new ConditionSearch('TestSuite.name', '=', 'Gryffindor_Regression_Test')])
Object testRun = Page.nav(SearchService)
						.search(searchTestRunData)
						.parseResponseBodyToJsonObject()									
testRunId = testRun.content[0].id.toString()

Object customFieldData = Page.nav(CustomFieldDefinitionService).generateData()
Object customField = Page.nav(CustomFieldDefinitionService)
									.create(customFieldData)
									.parseResponseBodyToJsonObject()		
customFieldId = customField.id

Page.nav(ExecutionsService)
	.assignCustomField(testRunId,
						[["id": customField.customFieldOptions[0].id, "definitionId": customFieldId]])
							
'Login to TO as admin account'
Page.nav(SignInPage)
		.enterCredential(GlobalVariable.owner_mail, GlobalVariable.owner_pass)
		.clickSignIn()

'Filter Test run by Custom Field on Report page'
Page.nav(DashboardPage).selectProject(GlobalVariable.platformGeneralProject)
Page.nav(HeaderBar).clickReports()
Page.nav(ReportPage).navigateTestRuns()
Page.nav(TestRunsPage).navigateTestRuns()
					.clickCustomField()
					.clickAddNewCustomField()
					.inputCustomFieldDisplayName(customFieldData.displayName)
					.inputCustomFieldDisplayValue(customFieldData.customFieldOptions[0].value)
					.clickAddCustomField()
					.clickUpdateBtn()

'VERIFY: The test run has added tag is shown in the result'
def searchResponseBody = Page.nav(SearchService)
							 .search(new BodySearchProjectScope("Execution",
									[new ConditionSearch("Project.id", "=", GlobalVariable.platformGenaralProjectId)],
									[new ConditionSearch(String.format("${customFieldData.key}.opt_id" ),
														 "=", customField.customFieldOptions[0].id)]))
							 .parseResponseBodyToJsonObject()

List<String> actualResult = searchResponseBody.content.stream()
												.map{r -> r.id.toString()}
												.collect(Collectors.toList())

WS.verifyEqual(actualResult, [testRunId])

