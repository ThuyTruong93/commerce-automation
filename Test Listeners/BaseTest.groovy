import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import groovy.json.JsonOutput
import internal.GlobalVariable
import katalon.fw.lib.Page
import katalon.testops.services.CustomFieldDefinitionService


class BaseTest {
	/**
	 * Executes before every test case starts.
	 * @param testCaseContext related information of the executed test case.
	 */
	boolean checkPass(TestCaseContext testCaseContext) {
		return testCaseContext.getTestCaseStatus().equalsIgnoreCase("PASSED")
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
}