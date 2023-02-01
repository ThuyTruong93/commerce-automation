package katalon.testops.report

import org.openqa.selenium.Keys

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class TestRunsPage extends BasePage<TestRunsPage>{

	public TestRunsPage navigateTestRuns() {
		WebUI.click(xpath('//div[@class="page-tab"]//a[contains(text(), "Test Runs")]'))
		return this
	}

	public TestRunsPage clickTestRunId(String id) {
		WebUI.click(text(id))
		return this
	}

	// TAG
	public TestRunsPage clickTag() {
		WebUI.click(text('Tags'))
		return this
	}

	public TestRunsPage inputTag(String tag) {
		WebUI.sendKeys(id('tagEntity'), tag)
		WebUI.click(option(tag))
		return this
	}


	// CUSTOM FIELD
	public TestRunsPage clickCustomField() {
		WebUI.click(text('Custom Fields'))
		return this
	}

	public TestRunsPage clickAddNewCustomField() {
		WebUI.click(xpath("//*[contains(text(),'Add new')]"))
		return this
	}

	public TestRunsPage inputCustomFieldDisplayName(String displayName) {
		WebUI.click(txt('Display Name'))
		WebUI.sendKeys(txt('Display Name'), displayName)
		WebUI.click(option(displayName))
		return this
	}

	public TestRunsPage inputCustomFieldDisplayValue(String value) {
		scrollToAndSendKeys(txt('Value'), value)
		WebUI.click(option(value))
		return this
	}

	public TestRunsPage clickAddCustomField() {
		WebUI.click(title('Add'))
		return this
	}

	public TestRunsPage clickUpdateBtn() {
		WebUI.click(xpath('//button[@type="submit"]'))
		return this
	}

	public TestRunsPage verifyTestCaseIsVisible(String testcase) {
		WebUI.verifyElementVisible(title(testcase))
		return this
	}

	public TestRunsPage filterTestSuite(String testSuite) {
		scrollToAndSendKeys(id('search-input'), "TestSuite.name:${testSuite}")
		WebUI.sendKeys(id('search-input'), Keys.chord(Keys.ENTER))
		WebUI.delay(1) // Delay 1s for filter loading
		return this
	}

	public TestRunsPage filterByExecutor(String executor) {

	}

	public TestRunsPage filterByStatus(String status) {
		return this
	}

	public TestRunsPage filterByOperatingSystem(String os) {
		return this
	}

	public TestRunsPage filterByBrowser(String browser) {
		return this
	}

	public TestRunsPage clickOnTestRun(String testRunName, int position = 1) {
		scrollToAndClick(xpath("(//tr[.//*[@title = '${testRunName}']])[${position}]//a[.//@data-trackid='click-test-run-details']"))
		return this
	}

	public TestRunsPage verifySearchStringIsVisible() {
		WebUI.verifyElementVisible(id('search-input'))
		return this
	}

	public TestRunsPage verifyTimeRangeClickable() {
		WebUI.verifyElementClickable(id('startTime'))
		return this
	}

	public TestRunsPage verifyReleaseFilterClickable() {
		WebUI.verifyElementClickable(id('ReleaseBuild'))
		return this
	}

	public TestRunsPage verifyProfileFilterClickable() {
		WebUI.verifyElementClickable(id('ExecutionTestResult.profile'))
		return this
	}

	public TestRunsPage verifyCustomFieldFilterClickable() {
		WebUI.verifyElementClickable(id('CustomField'))
		return this
	}

	public TestRunsPage verifyTagFilterClickable() {
		WebUI.verifyElementClickable(id('Tag.id'))
		return this
	}

	public TestRunsPage verifyAddMoreButtonClickable() {
		WebUI.verifyElementClickable(xpath('//button[contains(@class, "add-more-btn")]'))
		return this
	}

	public TestRunsPage verifyChartIsVisible() {
		WebUI.verifyElementVisible(cls('recharts-responsive-container'))
		return this
	}

	public TestRunsPage verifyBoardIsVisible() {
		WebUI.verifyElementVisible(cls('card-body'))
		return this
	}


}
