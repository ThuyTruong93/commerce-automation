package katalon.testops.testexecution

import org.openqa.selenium.Keys

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class TestRunListPage extends BasePage<TestRunListPage> {
	private TestObject filterBoxObj = id('search-input')
	private def testRunNameObj = { int rowIndex -> return xpath("//tr[${rowIndex}]//td[1]") }
	private def testRunReleaseObj = { int rowIndex -> return xpath("//tr[${rowIndex}]//td[2]") }
	private def testRunScriptRepoObj = { int rowIndex -> return xpath("//tr[${rowIndex}]//td[3]") }
	private def testRunTestEnvObj = { int rowIndex -> return xpath("//tr[${rowIndex}]//td[4]") }
	private def testRunLastRunObj = { int rowIndex -> return xpath("//tr[${rowIndex}]//td[5]") }
	private def testRunNextRunObj = { int rowIndex -> return xpath("//tr[${rowIndex}]//td[6]") }

	public TestRunListPage clickTestRunCalendarTab() {
		WebUI.click(title('Test Run Calendar'))
		return this
	}

	public TestRunListPage clickTestRunListTab() {
		WebUI.click(title('Test Run List'))
		return this
	}

	public TestRunListPage clickApplicationRepositoryTab() {
		WebUI.click(title('Application Repository'))
		return this
	}

	public TestRunListPage clickScheduleTestRun() {
		WebUI.click(btn('Schedule Test Run'))
		return this
	}

	public TestRunListPage clickScheduleSampleTestRun() {
		WebUI.click(btn('Schedule Sample Test Run'))
		return this
	}

	public TestRunListPage filterByName(String name) {
		WebUI.sendKeys(filterBoxObj, name)
		WebUI.sendKeys(filterBoxObj, Keys.chord(Keys.ENTER))
		return this
	}

	public TestRunListPage verifyTestRunName(int rowIndex, String expectedValue) {
		WebUI.verifyElementText(testRunNameObj(rowIndex), expectedValue)
		return this
	}

	public TestRunListPage verifyTestRunRelease(int rowIndex, String expectedValue) {
		WebUI.verifyElementText(testRunReleaseObj(rowIndex), expectedValue)
		return this
	}

	public TestRunListPage verifyTestRunScriptRepo(int rowIndex, String expectedValue) {
		WebUI.verifyElementText(testRunScriptRepoObj(rowIndex), expectedValue)
		return this
	}


	public TestRunListPage verifyTestRunTestEnv(int rowIndex, String expectedValue) {
		WebUI.verifyElementText(testRunTestEnvObj(rowIndex), expectedValue)
		return this
	}

	public TestRunListPage verifyTestRunLastRun(int rowIndex, String expectedValue) {
		WebUI.verifyElementText(testRunLastRunObj(rowIndex), expectedValue)
		return this
	}

	public TestRunListPage verifyTestRunNextRun(int rowIndex, String expectedValue) {
		WebUI.verifyElementText(testRunNextRunObj(rowIndex), expectedValue)
		return this
	}

	public TestRunListPage clickTestRunName(int rowIndex) {
		WebUI.click(testRunNameObj(rowIndex))
		return this
	}
}
