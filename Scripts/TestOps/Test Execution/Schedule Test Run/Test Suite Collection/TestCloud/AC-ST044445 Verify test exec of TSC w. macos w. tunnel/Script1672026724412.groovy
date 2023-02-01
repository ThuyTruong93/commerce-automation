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

String testRunName = "TSC-macOS-private-" + new DateTimeUtility().getCurrentDateTime()
String scriptRepo = 'cloudian-automation-1'
String tscPath = 'Test Suites/TSC/TSC with 6 passed WA TS with tunnel'
String profile = 'Staging'

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
		.clickTSCTestCloudEnv(1)
		.selectPlatform('MacOS')
		.selectBrowser('Chrome')
		.selectVersion('103')
		.clickTSCTestCloudEnv(2)
		.selectPlatform('MacOS')
		.selectBrowser('Chrome (headless)')
		.selectVersion('103')
		.clickTSCTestCloudEnv(3)
		.selectPlatform('MacOS')
		.selectBrowser('Edge Chromium')
		.selectVersion('103')
		.clickTSCTestCloudEnv(4)
		.selectPlatform('MacOS')
		.selectBrowser('Firefox')
		.selectVersion('102')
		.clickTSCTestCloudEnv(5)
		.selectPlatform('MacOS')
		.selectBrowser('Firefox (headless)')
		.selectVersion('102')
		.clickTSCTestCloudEnv(6)
		.selectPlatform('MacOS')
		.selectBrowser('Safari')
		.selectVersion('16')
		.clickProfile()
		.selectProfile(profile)
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

String expectedEnv = """TestCloud MacOS Chrome 103
TestCloud MacOS Chrome Headless 103
TestCloud MacOS Edge Chromium 103
TestCloud MacOS Firefox 102
TestCloud MacOS Firefox Headless 102
TestCloud MacOS Safari 16"""

'Verify all test run information of the first row on the list is correct'
Page.nav(TestRunListPage)
	.verifyTestRunName(1, testRunName)
	.verifyTestRunRelease(1, '')
	.verifyTestRunScriptRepo(1, scriptRepo)
	.verifyTestRunTestEnv(1, expectedEnv)
	.verifyTestRunLastRun(1, 'a few secs ago')
	.verifyTestRunNextRun(1, '')

'User clicks the test run name of the first row to go to the test run details page'
Page.nav(TestRunListPage).clickTestRunName(1)
	
'Wait until the the test run is completed then verify the test run job information'
Page.nav(TestRunsPage)
	.waitUntilTestRunCompleted(1, 60 * 30)
	.verifyTestRunName(1, testRunName)
	.verifyTSCName(1, tscPath)
	.verifyProfile(1, profile)
	.verifyTotal(1, 6)
	.verifyNumberOfPassed(1, 6)
	.verifyNumberOfFailed(1, 0)
	.verifyNumberOfError(1, 0)
	.verifyNumberOfIncomplete(1, 0)
	.verifyNumberOfSkipped(1, 0)
	.verifyExecutedBy(1, GlobalVariable.ownerAbbrName)
	
							  