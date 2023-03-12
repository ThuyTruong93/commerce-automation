import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import katalon.fw.db.PostgreSql
import katalon.fw.lib.Page
import katalon.utility.DateTimeUtility


class BaseTest {
	/**
	 * Executes before every test case starts.
	 * @param testCaseContext related information of the executed test case.
	 */
	boolean checkPass(TestCaseContext testCaseContext) {
		return testCaseContext.getTestCaseStatus().equalsIgnoreCase("PASSED")
	}

	@BeforeTestSuite
	def setTSInfo(TestSuiteContext testSuiteContext) {
		GlobalVariable.tsStartTime = new DateTimeUtility().getCurrentDateTime('dd/MM/yyyy HH:mm:ss')
		GlobalVariable.tsName = testSuiteContext.getTestSuiteId()
	}

	@BeforeTestCase
	def setTCInfo(TestCaseContext testCaseContext) {
		GlobalVariable.tcStartTime = new DateTimeUtility().getCurrentDateTime('dd/MM/yyyy HH:mm:ss')
		GlobalVariable.tcName = testCaseContext.getTestCaseId()
	}

	@BeforeTestCase
	def openBrowser(TestCaseContext testCaseContext) {
		if (!GlobalVariable.isAPIRunning){
			WebUI.openBrowser(null)
			WebUI.maximizeWindow()
		}
	}

	@AfterTestCase
	def closeBrowser(TestCaseContext testCaseContext) {
		if (!GlobalVariable.isAPIRunning) {
			if (!checkPass(testCaseContext)) {
				WebUI.takeScreenshot()
			}
			WebUI.closeBrowser()
		}
	}

	@AfterTestCase
	def closeConnection() {
		Page.nav(PostgreSql).closeConnection();
	}
}