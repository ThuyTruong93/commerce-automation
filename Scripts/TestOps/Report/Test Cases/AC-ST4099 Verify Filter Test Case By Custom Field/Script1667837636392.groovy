import internal.GlobalVariable
import katalon.common.HeaderBar
import katalon.common.SignInPage
import katalon.fw.lib.Page
import katalon.model.BodySearch
import katalon.model.ConditionSearch
import katalon.services.SearchService
import katalon.testops.dashboard.DashboardPage
import katalon.testops.report.ReportPage
import katalon.testops.report.TestCasesPage
import katalon.testops.services.CustomFieldDefinitionService
import katalon.testops.services.TestCaseService

'PRE-CONDITON: Create new custom field'
BodySearch searchTCBody = new BodySearch('TestCase', 
								[new ConditionSearch('ExecutionStatistics.startTime', '>=', '2012-09-16T10:45:20+07:00'), 
									new ConditionSearch('Project.id', '=', GlobalVariable.platformGenaralProjectId)])

Object testCaseInfo = Page.nav(SearchService)
							.search(searchTCBody)
							.parseResponseBodyToJsonObject()
									
testCaseId = testCaseInfo.content[0].id.toString()
									
Object createCFData = Page.nav(CustomFieldDefinitionService).generateData()

Object createCFResponseBody = Page.nav(CustomFieldDefinitionService)
									.create(createCFData)
									.parseResponseBodyToJsonObject()

'PRE-CONDITION: Assgin CF to test case'
customFieldId = createCFResponseBody.id
Page.nav(TestCaseService).update(["id": testCaseId,
								  "customFieldOptions": [[ "id": createCFResponseBody.customFieldOptions[0].id,
															"definitionId": customFieldId]]])

'Login to TO as admin account'
Page.nav(SignInPage).enterCredential(GlobalVariable.owner_mail, GlobalVariable.owner_pass).clickSignIn()

'Filter test case by Custom Field on Report page'
Page.nav(DashboardPage).selectProject(GlobalVariable.platformGeneralProject)
Page.nav(HeaderBar).clickReports()
Page.nav(ReportPage).navigateTestCases()

Page.nav(TestCasesPage).clickCustomField()
						.clickAddNewCustomField()
						.inputCustomFieldDisplayName(createCFData.displayName)
						.inputCustomFieldDisplayValue(createCFData.customFieldOptions[0].value)
						.clickAddCustomField()
						.clickUpdateBtn()

'VERIFY: The test case has added CF is shown in the result'
Page.nav(TestCasesPage).verifyTestCaseIsVisible(testCaseInfo.content[0].name)


