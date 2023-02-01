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
import katalon.testops.services.TestCaseService

'PRE-CONDITON: Assign new tag to Test Case'
BodySearch searchTCBody = new BodySearch('TestCase', 
								[new ConditionSearch('ExecutionStatistics.startTime', '>=', '2012-09-16T10:45:20+07:00'), 
									new ConditionSearch('Project.id', '=', GlobalVariable.platformGenaralProjectId)])

Object testCaseInfo = Page.nav(SearchService)
							.search(searchTCBody)
							.parseResponseBodyToJsonObject()
									
testCaseId = testCaseInfo.content[0].id.toString()

Object addTageBody = Page.nav(TestCaseService).generateTagBody()
addTagResponseBody = Page.nav(TestCaseService)
							.addTag(testCaseId ,addTageBody)
							.parseResponseBodyToJsonObject()
							
'Login to TO as admin account'
Page.nav(SignInPage).enterCredential(GlobalVariable.owner_mail, GlobalVariable.owner_pass).clickSignIn()

'Filter Test Case by tag on Report page'
Page.nav(DashboardPage).selectProject(GlobalVariable.platformGeneralProject)
Page.nav(HeaderBar).clickReports()
Page.nav(ReportPage).navigateTestCases()
Page.nav(TestCasesPage).clickTag().inputTag(addTageBody.name).clickUpdateBtn()

'Verify the test case has added tag is shown in the result'
Page.nav(TestCasesPage).verifyTestCaseIsVisible(testCaseInfo.content[0].name)
