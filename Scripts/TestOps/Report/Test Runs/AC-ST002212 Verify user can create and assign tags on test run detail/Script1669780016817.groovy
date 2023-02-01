import groovy.json.JsonSlurper
import internal.GlobalVariable
import katalon.common.HeaderBar
import katalon.common.HomePage
import katalon.common.SignInPage
import katalon.fw.lib.Page
import katalon.testops.report.TestRunDetailPage
import katalon.testops.report.TestRunsPage


'User navigates to TestOps Page'
Page.nav(SignInPage).enterCredential()
					.doCheckRemberMe()
					.clickSignIn()

'At Home page, user select Project'
Page.nav(HomePage).selectProject(GlobalVariable.platformGeneralProject)

'User click on Reports module'
Page.nav(HeaderBar).clickReports()

'User click on Test Runs tab'
Page.nav(TestRunsPage).navigateTestRuns()

'Navigate to test run detail page'
Page.nav(TestRunsPage).filterTestSuite(testSuite)
					  .clickOnTestRun(testRun)

'User click on Tag input field'
Page.nav(TestRunDetailPage).clickTagsInputField()

JsonSlurper slurper = new JsonSlurper()
listTags = slurper.parseText(listTags)
for(def tag: listTags) {
	tag = tag + System.currentTimeMillis()
	'Input new Tag and Verify user can create new Tag'
	Page.nav(TestRunDetailPage).createNewTag(tag).verifyCreateNewTag(tag)
	'Verify user can Assign new tags on Test Run detail'
	Page.nav(TestRunDetailPage).assignTag(tag).verifyTagIsVisible(tag)
}