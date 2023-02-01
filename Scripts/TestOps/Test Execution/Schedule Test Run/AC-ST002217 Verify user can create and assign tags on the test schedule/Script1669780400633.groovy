import groovy.json.JsonSlurper
import internal.GlobalVariable
import katalon.common.HeaderBar
import katalon.common.HomePage
import katalon.common.SignInPage
import katalon.fw.lib.Page
import katalon.testops.report.TestRunDetailPage
import katalon.testops.report.TestRunsPage
import katalon.testops.testexecution.NewUIScheduleTestRunPopUp
import katalon.testops.testexecution.TestRunCalendarPage

'User navigates to TestOps Page'
Page.nav(SignInPage).enterCredential()
					.doCheckRemberMe()
					.clickSignIn()

'At Home page, user select Project'
Page.nav(HomePage).selectProject(GlobalVariable.platformGeneralProject)

'User click on Execution module'
Page.nav(HeaderBar).clickExecutions()

'User click on Schedule Test Run button'
Page.nav(TestRunCalendarPage).clickScheduleTestRun()

'User click on Advanced settings hyperlink'
Page.nav(NewUIScheduleTestRunPopUp).clickAdvancedSettings()

'User click on Tag input field'
Page.nav(NewUIScheduleTestRunPopUp).clickTagInputField()

JsonSlurper slurper = new JsonSlurper()
listTags = slurper.parseText(listTags)
for(def tag: listTags) {
	tag = tag + System.currentTimeMillis()
	'Input new Tag and Verify user can create new Tag'
	Page.nav(NewUIScheduleTestRunPopUp).createNewTag(tag).verifyCreateNewTag(tag)
	'Verify user can Assign new tags on Test Schedule'
	Page.nav(NewUIScheduleTestRunPopUp).assignTag(tag).verifyTagIsVisible(tag)
}