import katalon.common.HeaderBar
import katalon.common.SignInPage
import katalon.fw.lib.Page
import katalon.testops.dashboard.DashboardPage
import katalon.testops.services.TestCaseService
import katalon.testops.testmanagement.MultiSearchPopup
import katalon.testops.testmanagement.TestCasesPage

'PRE-CONDITON: Assign new tag to Test Case'
def addTageBody = Page.nav(TestCaseService).generateTagBody()
def addTagResponseBody = Page.nav(TestCaseService).addTag(testCaseId ,addTageBody).parseResponseBodyToJsonObject()

'Login to TO as admin account'
Page.nav(SignInPage).enterCredential(internal.GlobalVariable.owner_mail, internal.GlobalVariable.owner_pass).clickSignIn()

'Filter by tag on Tag Management'
Page.nav(DashboardPage).selectProject('First Project')
Page.nav(HeaderBar).clickTests()
Page.nav(TestCasesPage).openMultiSearchPopup()
Page.nav(MultiSearchPopup).inputTag(addTageBody.name).clickApplyAndSearch()

'VERIFY: The test case has added CF is shown in the result'
Page.nav(TestCasesPage).verifyTestCaseIsVisible(testCaseName).verifyTheTotalOfTestCase('1 - 1 of 1 test case')


