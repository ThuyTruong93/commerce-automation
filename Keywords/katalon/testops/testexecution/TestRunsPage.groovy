package katalon.testops.testexecution

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class TestRunsPage extends BasePage<TestRunsPage> {
	private def loading = { int rowIndex -> return xpath("//tr[${rowIndex}]//td[2]//*[@title='Analyzing']")}
	private def incompleteStatus = { int rowIndex -> return xpath("//tr[${rowIndex}]//td[2]//*[@title='Incomplete']")}
	private def importingStatus = { int rowIndex -> return xpath("//tr[${rowIndex}]//td[2]//*[@title='Importing']")}
	private def passedStatus = { int rowIndex -> return xpath("//tr[${rowIndex}]//td[2]//span[@title='Passed']") }
	private def failedStatus = { int rowIndex -> return xpath("//tr[${rowIndex}]//td[2]//span[@title='Failed']") }
	private def completedStatus = { int rowIndex -> return xpath("//tr[${rowIndex}]//td[2]//span[@title='Failed' or @title='Passed']") }
	private def testRunName = { int rowIndex -> return xpath("//tr[${rowIndex}]//td[4]//div[contains(@class, 'label')]//div[@title]") }
	private def testSuite = { int rowIndex -> return xpath("//tr[${rowIndex}]//td[4]//div[contains(@class, 'text-word-wrap')]") }
	private def testSuitePassed = { int rowIndex -> return xpath("//tr[${rowIndex}]//td[4]//*[@title='Passed']") }
	private def tsc = { int rowIndex, String tscPath, String tscName -> return xpath("//tr[${rowIndex}]//td[4]//div[@title='${tscPath}']//span[@title['${tscName}']]") }
	private def androidIcon = { int rowIndex -> return css("tbody tr:nth-child(${rowIndex}) g#surface1") }
	private def iOSIcon = { int rowIndex -> return css("tbody tr:nth-child(${rowIndex}) g#surface1") }
	private def profile = { int rowIndex -> return xpath("//tr[${rowIndex}]//td[5]//span[@title]") }	
	private def total = { int rowIndex -> return xpath("//tr[${rowIndex}]//td[7]//span") }
	private def numberOfPassed = { int rowIndex -> return xpath("//tr[${rowIndex}]//td[8]//span") }
	private def numberOfFailed = { int rowIndex -> return xpath("//tr[${rowIndex}]//td[9]//span") }
	private def numberOfError = { int rowIndex -> return xpath("//tr[${rowIndex}]//td[10]//span") }
	private def numberOfIncomplete = { int rowIndex -> return xpath("//tr[${rowIndex}]//td[11]//span") }
	private def numberOfSkipped = { int rowIndex -> return xpath("//tr[${rowIndex}]//td[12]//span") }
	private def executedBy = { int rowIndex -> return xpath("//tr[${rowIndex}]//td[14]//span[contains(@style, 'nowrap')]") }
	private def testRunId = { int rowIndex -> return xpath("//tr[${rowIndex}]//td[3]//a") }
	private TestObject toolTip = xpath("//div[@role='tooltip']")
	
	public TestRunsPage waitUntilTestRunCompleted(int rowIndex, int timeOut) {
		WebUI.waitForElementPresent(completedStatus(rowIndex), timeOut)
		return this
	}
	
	public TestRunsPage waitUntilTestRunIncomplete(int rowIndex, int timeOut) {
		WebUI.waitForElementPresent(incompleteStatus(rowIndex), timeOut)
		return this
	}
	
	public TestRunsPage verifyTestRunName(int rowIndex, String expectedValue) {
		WebUI.verifyElementText(testRunName(rowIndex), expectedValue)
		return this
	}
	
	public TestRunsPage verifyTestSuiteName(int rowIndex, String expectedValue) {
		WebUI.verifyElementText(testSuite(rowIndex), expectedValue)
		return this
	}
	
	public TestRunsPage verifyTSCName(int rowIndex, String tscPath) {
		String tscName = tscPath.split('/').last()
		WebUI.verifyElementVisible(tsc(rowIndex, tscPath, tscName))
		WebUI.verifyElementText(tsc(rowIndex, tscPath, tscName), tscName)
		return this
	}
	
	public TestRunsPage verifyOSIsAndroid(int rowIndex) {
		WebUI.verifyElementVisible(androidIcon(rowIndex))
		return this
	}
	
	public TestRunsPage verifyAndroidVersion(int rowIndex, String expectedValue) {
		WebUI.mouseOver(androidIcon(rowIndex))
		WebUI.verifyElementText(toolTip, expectedValue)
		return this
	}
	
	public TestRunsPage verifyOSIsIOS(int rowIndex) {
		WebUI.verifyElementVisible(iOSIcon(rowIndex))
		return this
	}
	
	public TestRunsPage verifyIOSVersion(int rowIndex, String expectedValue) {
		WebUI.mouseOver(iOSIcon(rowIndex))
		WebUI.verifyElementText(toolTip, expectedValue)
		return this
	}
	
	public TestRunsPage verifyProfile(int rowIndex, String expectedValue) {
		WebUI.verifyElementText(profile(rowIndex), expectedValue)
		return this
	}
	
	public TestRunsPage verifyTotal(int rowIndex, String expectedValue) {
		WebUI.verifyElementText(total(rowIndex), expectedValue)
		return this
	}
	
	public TestRunsPage verifyNumberOfPassed(int rowIndex, String expectedValue) {
		WebUI.verifyElementText(numberOfPassed(rowIndex), expectedValue)
		return this
	}
	
	public TestRunsPage verifyNumberOfFailed(int rowIndex, String expectedValue) {
		WebUI.verifyElementText(numberOfFailed(rowIndex), expectedValue)
		return this
	}
	
	public TestRunsPage verifyNumberOfError(int rowIndex, String expectedValue) {
		WebUI.verifyElementText(numberOfError(rowIndex), expectedValue)
		return this
	}
	
	public TestRunsPage verifyNumberOfIncomplete(int rowIndex, String expectedValue) {
		WebUI.verifyElementText(numberOfIncomplete(rowIndex), expectedValue)
		return this
	}
	
	public TestRunsPage verifyNumberOfSkipped(int rowIndex, String expectedValue) {
		WebUI.verifyElementText(numberOfSkipped(rowIndex), expectedValue)
		return this
	}
	
	public TestRunsPage verifyExecutedBy(int rowIndex, String expectedValue) {
		WebUI.verifyElementText(executedBy(rowIndex), expectedValue)
		return this
	}
	
	public TestRunsPage clickTestRunId(int rowIndex) {
		WebUI.click(testRunId(rowIndex))
		return this
	}
}
