package katalon.testops.report
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import katalon.fw.lib.BasePage
import katalon.testops.testmanagement.TestCaseDetailPage

public class TestResultPage extends BasePage<TestResultPage>{
	public TestResultPage clickOnTestResultTab() {
		WebUI.click(text('Test Results'))
		return this
	}

	public TestResultPage clickOnTestResultDetail(String testResultName, int position = 1) {
		scrollToAndClick(xpath("(//tr[.//*[text() = '${testResultName}']])[${position}]//a[.//@data-trackid='click-test-result-details']"))
		return this
	}

	// Custom Fields
	public TestResultPage clickAddNewCustomField() {
		scrollToAndClick(text('Add new'))
		return this
	}

	public TestResultPage inputCustomFieldDisplayName(String displayName) {
		WebUI.click(txt('Display Name'))
		WebUI.sendKeys(txt('Display Name'), displayName)
		return this
	}

	public TestResultPage selectCustomFieldDisplayName(String displayName) {
		inputCustomFieldDisplayName(displayName)
		WebUI.click(option(displayName))
		return this
	}

	public TestResultPage selectCustomFieldDisplayValue(String value) {
		def text = value.substring(1, 13)
		scrollToAndSendKeys(txt('Value'), value)
		WebUI.click(xpath("(//*[contains(text(),'${text}')]/ancestor::li[@role='option'])[1]"))
		return this
	}

	public TestResultPage clickAssignCustomField() {
		WebUI.click(title('Add'))
		return this
	}

	public TestResultPage clickCancelAddCustomField() {
		WebUI.click(title('Cancel'))
		return this
	}

	public TestResultPage verifyCustomFieldDisplay(def key, def value) {
		WebUI.verifyElementAttributeValue(title(key), 'title', key, GlobalVariable.smallSleepTime)
		WebUI.verifyElementAttributeValue(title(value), 'title', value, GlobalVariable.smallSleepTime)
		return this
	}

	public TestResultPage verifyCustomFieldDoesNotDisplay(def displayName, def value) {
		WebUI.verifyElementNotPresent(text(displayName), GlobalVariable.smallSleepTime)
		WebUI.verifyElementNotPresent(text(value), GlobalVariable.smallSleepTime)
		return this
	}

	public TestResultPage clickUnassignCustomField(String value) {
		WebUI.click(xpath("(.//*[normalize-space(text()) and normalize-space(.)='${value}'])[1]/following::*[name()='svg'][1]"))
		return this
	}

	public TestResultPage verifyDisplayNameNotPresent(String displayName) {
		WebUI.verifyElementNotPresent(xpath("(//*[@title='$displayName']/ancestor::li[@role='option'])[1]"), GlobalVariable.smallSleepTime)
		return this
	}

	public TestResultPage verifyCustomFieldValueFieldIsDisabled() {
		WebUI.verifyElementHasAttribute(txt('Value'), 'disabled', GlobalVariable.smallSleepTime)
		return this
	}

	public TestResultPage verifyAssignCustomFieldButtonIsDisabled() {
		WebUI.verifyElementHasAttribute(title('Add'), 'disabled', GlobalVariable.smallSleepTime)
		return this
	}

	public TestResultPage verifyCustomFieldInfoIsEmpty() {
		WebUI.verifyElementNotPresent(xpath("(//span[contains(@class, 'custom-field')])[1]"), GlobalVariable.smallSleepTime)
		return this
	}
}
