package katalon.testops.testmanagement

import org.openqa.selenium.Keys

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import katalon.fw.lib.BasePage
import katalon.testops.report.TestRunDetailPage

public class TestCaseDetailPage extends BasePage<TestCaseDetailPage> {
	public TestCaseDetailPage clickTagsInputField() {
		WebUI.click(id("tagEntity"))
		return this
	}

	public TestCaseDetailPage createNewTag(String nameOfTag) {
		WebUI.sendKeys(id("tagEntity"), nameOfTag)
		return this
	}

	public TestCaseDetailPage verifyCreateNewTag(String nameOfTag) {
		println "Error msg $nameOfTag"
		WebUI.verifyElementVisible(title(nameOfTag + ' (new tag)'))
		return this
	}

	public TestCaseDetailPage assignTag(String nameOfTag) {
		WebUI.sendKeys(id("tagEntity"), Keys.chord(Keys.ENTER))
		return this
	}

	public TestCaseDetailPage verifyTagIsVisible(String nameOfTag) {
		WebUI.verifyElementText(text(nameOfTag), nameOfTag)
		return this
	}

	public TestCaseDetailPage unassignTag(String nameOfTag) {
		WebUI.click(xpath("(.//*[normalize-space(text()) and normalize-space(.)='${nameOfTag}'])[1]/following::*[name()='svg'][1]"))
		return this
	}

	public TestCaseDetailPage verifyTagIsVisibleOnDropdown(String nameOfTag) {
		WebUI.click(id("tagEntity"))
		WebUI.sendKeys(id("tagEntity"), nameOfTag)
		WebUI.verifyElementVisible(title(nameOfTag))
		return this
	}

	public TestCaseDetailPage searchExistingTag(String nameOfTag) {
		WebUI.click(title(nameOfTag))
	}

	public TestCaseDetailPage verifyErrorMessage(String errorMessage) {
		String msg = WebUI.getText(cls("toast-message"))
		println "Error msg $msg"
		WebUI.verifyEqual(msg.contains(errorMessage), true)
		return this
	}

	public TestCaseDetailPage clickEditTestcase() {
		WebUI.click(title('Edit'))
		return this
	}

	public TestCaseDetailPage verifyCustomFieldDisplay(def key, def value, int position = 1) {
		WebUI.verifyElementAttributeValue(xpath("//span[contains(@class, 'custom-field')][${position}]//div[contains(@class, '_definition')]"), 'title', "${key}", GlobalVariable.smallSleepTime)
		WebUI.verifyElementAttributeValue(xpath("//span[contains(@class, 'custom-field')][${position}]//div[contains(@class, '_option')]"), 'title', "${value}",  GlobalVariable.smallSleepTime)
		return this
	}

	public TestCaseDetailPage verifyCustomFieldInfoIsEmpty() {
		WebUI.verifyElementNotPresent(xpath("(//span[contains(@class, 'custom-field')])[1]"), GlobalVariable.smallSleepTime)
		return this
	}

	public TestCaseDetailPage verifyCustomFieldDoesNotDisplay(def displayName, def value) {
		WebUI.verifyElementNotPresent(text(displayName), GlobalVariable.smallSleepTime)
		WebUI.verifyElementNotPresent(text(value), GlobalVariable.smallSleepTime)
		return this
	}

	// Edit Test Case Form

	public TestCaseDetailPage clickSaveUpdateTestCase() {
		WebUI.click(btn('Save'))
		return this
	}

	public TestCaseDetailPage clickAddNewCustomField() {
		scrollToAndClick(text('Add new'))
		return this
	}

	public TestCaseDetailPage inputCustomFieldDisplayName(String displayName) {
		WebUI.click(txt('Display Name'))
		WebUI.sendKeys(txt('Display Name'), displayName)
		return this
	}

	public TestCaseDetailPage selectCustomFieldDisplayName(String displayName) {
		inputCustomFieldDisplayName(displayName)
		WebUI.click(option(displayName))
		return this
	}

	public TestCaseDetailPage selectCustomFieldDisplayValue(String value) {
		scrollToAndSendKeys(txt('Value'), value)
		WebUI.click(option(value))
		return this
	}

	public TestCaseDetailPage clickAssignCustomField() {
		WebUI.click(title('Add'))
		return this
	}

	public TestCaseDetailPage clickCancelAddCustomField() {
		WebUI.click(title('Cancel'))
		return this
	}

	public TestCaseDetailPage clickUnassignCustomField(String value) {
		WebUI.click(xpath("(.//*[normalize-space(text()) and normalize-space(.)='${value}'])[1]/following::*[name()='svg'][1]"))
		return this
	}

	public TestCaseDetailPage verifyDisplayNameNotPresent(String displayName) {
		WebUI.verifyElementNotPresent(xpath("(//*[@title='${displayName}']/ancestor::li[@role='option'])[1]"), GlobalVariable.smallSleepTime)
		return this
	}

	public TestCaseDetailPage verifyCustomFieldValueFieldIsDisabled() {
		WebUI.verifyElementHasAttribute(txt('Value'), 'disabled', GlobalVariable.smallSleepTime)
		return this
	}

	public TestCaseDetailPage verifyAssignCustomFieldButtonIsDisabled() {
		WebUI.verifyElementHasAttribute(title('Add'), 'disabled', GlobalVariable.smallSleepTime)
		return this
	}
}
