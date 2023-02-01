import com.kms.katalon.core.util.KeywordUtil

import internal.GlobalVariable
import katalon.common.HeaderBar
import katalon.common.HomePage
import katalon.common.SignInPage
import katalon.fw.lib.Page
import katalon.testops.testexecution.NewUIScheduleTestRunPopUp
import katalon.testops.testexecution.TestRunCalendarPage
import katalon.testops.testexecution.TestRunsPage
import katalon.testops.testexecution.TestRunListPage
import katalon.utility.DateTimeUtility

String testRunName = "TSC-linux-default-private-" + new DateTimeUtility().getCurrentDateTime()
String scriptRepo = 'cloudian-automation-1'
String tscPath = 'Test Suites/TSC/TSC with 4 passed WA TS with tunnel for linux (default)'
String profile = 'default'

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
		.clickTSCTab()
		.clickTSCObjectName()
		.selectTSC(tscPath)
		.clickTSCConfigLink()
		.clickTestCloudTunnelEnable()
		.clickBackMoreOptions()
		.clickRepeatEnable()
		.clickAdvancedSettings()
		.clickExecutionMode()
		.selectExecutionMode('Parallel')
		.clickBackAdvancedSettings()
		.clickRun()
} else {
	KeywordUtil.markFailedAndStop("This test script does not support the old UI of the Schedule Test Run, so please test manually")
}
				
							  		  
'User filters test run by name'
Page.nav(TestRunListPage).filterByName(testRunName)

String expectedEnv = """TestCloud Linux (Default)
TestCloud Linux (Default)
TestCloud Linux (Default)
TestCloud Linux (Default)"""

'Verify all test run information of the first row on the list is correct'
Page.nav(TestRunListPage).verifyTestRunName(1, testRunName)
	.verifyTestRunRelease(1, '')
	.verifyTestRunScriptRepo(1, scriptRepo)
	.verifyTestRunTestEnv(1, expectedEnv)
	.verifyTestRunLastRun(1, 'a few secs ago')
	.verifyTestRunNextRun(1, '')

'User clicks the test run name of the first row to go to the test run details page'
Page.nav(TestRunListPage).clickTestRunName(1)
	  
'Wait until the the test run is completed then verify the test run job information'
Page.nav(TestRunsPage).waitUntilTestRunCompleted(1, 60 * 30)
	.verifyTestRunName(1, testRunName)
	.verifyTSCName(1, tscPath)
	.verifyProfile(1, profile)
	.verifyTotal(1, 4)
	.verifyNumberOfPassed(1, 4)
	.verifyNumberOfFailed(1, 0)
	.verifyNumberOfError(1, 0)
	.verifyNumberOfIncomplete(1, 0)
	.verifyNumberOfSkipped(1, 0)
	.verifyExecutedBy(1, GlobalVariable.ownerAbbrName)
							  
							  