import internal.GlobalVariable
import katalon.common.HeaderBar
import katalon.common.SignInPage
import katalon.fw.lib.Page
import katalon.model.BodySearch
import katalon.model.ConditionSearch
import katalon.services.SearchService
import katalon.testops.dashboard.DashboardPage
import katalon.testops.services.CustomFieldDefinitionService
import katalon.testops.services.TestCaseService
import katalon.testops.testmanagement.MultiSearchPopup
import katalon.testops.testmanagement.TestCasesPage

'PRE-CONDITON: Create new custom field'
BodySearch searchTCBody = new BodySearch('TestCase',
	[new ConditionSearch('name', '=', GlobalVariable.currentTestCaseName),
		new ConditionSearch('Project.id', '=', GlobalVariable.platformGenaralProjectId)])

testCaseId = Page.nav(SearchService).search(searchTCBody)
									.parseResponseBodyToJsonObject()
									.content[0].id.toString()
									
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
Page.nav(SignInPage)
		.enterCredential(GlobalVariable.owner_mail, GlobalVariable.owner_pass)
		.clickSignIn()

'Filter by CF on Tag Management'
Page.nav(DashboardPage).selectProject(GlobalVariable.platformGeneralProject)
Page.nav(HeaderBar).clickTests()
Page.nav(TestCasesPage).openMultiSearchPopup()

Page.nav(MultiSearchPopup)
		.clickAddNewCustomField()
		.inputCustomFieldDisplayName(createCFData.displayName)
		.inputCustomFieldDisplayValue(createCFData.customFieldOptions[0].value)
		.clickAddCustomField()
		.clickApplyAndSearch()

'VERIFY: The test case has added CF is shown in the result'
Page.nav(TestCasesPage)
		.verifyTestCaseIsVisible(GlobalVariable.currentTestCaseName)
		.verifyTheTotalOfTestCase('1 - 1 of 1 test case')


