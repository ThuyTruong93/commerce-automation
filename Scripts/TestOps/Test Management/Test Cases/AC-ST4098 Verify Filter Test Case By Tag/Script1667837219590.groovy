import internal.GlobalVariable
import katalon.common.HeaderBar
import katalon.common.SignInPage
import katalon.fw.lib.Page
import katalon.model.BodySearch
import katalon.model.ConditionSearch
import katalon.services.SearchService
import katalon.testops.dashboard.DashboardPage
import katalon.testops.services.TestCaseService
import katalon.testops.testmanagement.MultiSearchPopup
import katalon.testops.testmanagement.TestCasesPage

'PRE-CONDITON: Assign new tag to Test Case'
BodySearch searchTCBody = new BodySearch('TestCase', 
								[new ConditionSearch('name', '=', GlobalVariable.currentTestCaseName), 
									new ConditionSearch('Project.id', '=', GlobalVariable.platformGenaralProjectId)])

testCaseId = Page.nav(SearchService).search(searchTCBody)
									.parseResponseBodyToJsonObject()
									.content[0].id.toString()

Object addTageBody = Page.nav(TestCaseService).generateTagBody()
addTagResponseBody = Page.nav(TestCaseService)
						.addTag(testCaseId ,addTageBody)
						.parseResponseBodyToJsonObject()

'Login to TO as admin account'
Page.nav(SignInPage).enterCredential(GlobalVariable.owner_mail, GlobalVariable.owner_pass).clickSignIn()

'Filter by tag on Tag Management'
Page.nav(DashboardPage).selectProject(GlobalVariable.platformGeneralProject)
Page.nav(HeaderBar).clickTests()
Page.nav(TestCasesPage).openMultiSearchPopup()
Page.nav(MultiSearchPopup).inputTag(addTageBody.name).clickApplyAndSearch()

'VERIFY: The test case has added CF is shown in the result'
Page.nav(TestCasesPage).verifyTestCaseIsVisible(GlobalVariable.currentTestCaseName).verifyTheTotalOfTestCase('1 - 1 of 1 test case')


