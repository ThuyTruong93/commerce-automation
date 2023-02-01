import com.kms.katalon.core.util.KeywordUtil

import internal.GlobalVariable
import katalon.common.HomePage
import katalon.common.SignInPage
import katalon.fw.lib.Page
import katalon.testops.testexecution.NewUIScheduleTestRunPopUp
import katalon.testops.testexecution.TestRunCalendarPage
import katalon.testops.testexecution.TestRunListPage
import katalon.testops.testexecution.TestRunsPage
import katalon.utility.DateTimeUtility

String testRunName = "TS-CircleCI-" + new DateTimeUtility().getCurrentDateTime()

'User navigates to TestOps Page'
Page.nav(SignInPage).enterCredential().clickSignIn()
Page.nav(HomePage).waitUntilPageDisplayed()

'User navigates to Test Run Calendar page'
Page.nav(TestRunCalendarPage).navigateTo(GlobalVariable.cloudianTeamId, GlobalVariable.cloudBasedExecProjectId)

'User click Schedule Test Run button'
Page.nav(TestRunCalendarPage).clickScheduleTestRun()

if (GlobalVariable.isNewUIScheduleEnabled) {
	'User starts inputting the information on the Schedule test run popup'
	Page.nav(NewUIScheduleTestRunPopUp)
		.inputTestRunName(testRunName)
		.clickScriptRepo()
		.selectScriptRepo(scriptRepo)
		.clickTestSuiteObjectName()
		.selectTestSuite(testSuitePath)
		.clickTSEnvDropdown()
		.clickMoreOptions()
		.clickCircleCIRadio()
		.clickCircleCIEnvDropdown()
		.selectCircleCIEnv(circlCIEnv)
		.clickRunWithDropdown()
		.selectRunWith(browser)
		.clickProfile()
		.selectProfile(profile)
		.clickBackMoreOptions()
	    .clickRepeatEnable()
		.clickRun()
} else {
	KeywordUtil.markFailedAndStop('This test does not support old UI of the Schedule Test Run modal, please test it manually')
}
				  
'User filters test run by name'
Page.nav(TestRunListPage).filterByName(testRunName)

'Verify all test run information of the first row on the list is correct'
Page.nav(TestRunListPage)
	.verifyTestRunName(1, testRunName)
	.verifyTestRunRelease(1, '')
	.verifyTestRunScriptRepo(1, scriptRepo)
	.verifyTestRunTestEnv(1, circlCIEnv)
	.verifyTestRunLastRun(1, 'a few secs ago')
	.verifyTestRunNextRun(1, '')

'User clicks the test run name of the first row to go to the test run details page'
Page.nav(TestRunListPage).clickTestRunName(1)
	
'Wait until the the test run is completed then verify the test run job information'
Page.nav(TestRunsPage)
	.waitUntilTestRunCompleted(1, 60 * 20)
	.verifyTestRunName(1, testRunName)
	.verifyTestSuiteName(1, testSuitePath.split('/').last())
	.verifyProfile(1, profile)
	.verifyTotal(1, total)
	.verifyNumberOfPassed(1, numberOfPassed)
	.verifyNumberOfFailed(1, numberOfFailed)
	.verifyNumberOfError(1, numberOfError)
	.verifyNumberOfIncomplete(1, numberOfIncomplete)
	.verifyNumberOfSkipped(1, numberOfSkipped)
	.verifyExecutedBy(1, GlobalVariable.ownerAbbrName)
	
	