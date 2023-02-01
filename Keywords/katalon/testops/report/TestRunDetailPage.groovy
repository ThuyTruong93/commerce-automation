package katalon.testops.report

import org.openqa.selenium.Keys

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword

import internal.GlobalVariable
import katalon.fw.lib.BasePage

public class TestRunDetailPage extends BasePage<TestRunDetailPage>{
	private def sessionPassed = { int rowIndex -> return xpath("//div[contains(@class, 'card') and contains(., 'Sessions')]//tr[${rowIndex}]//td[1]/span[@title='Success']") }
	private def sessionScriptRepo = { int rowIndex -> return xpath("//div[contains(@class, 'card') and contains(., 'Sessions')]//tr[${rowIndex}]//td[3]//a") }
	private def sessionTestEnv = { int rowIndex -> return xpath("//div[contains(@class, 'card') and contains(., 'Sessions')]//tr[${rowIndex}]//td[4]//a") }
	private def sessionId = { int rowIndex -> return xpath("//div[contains(@class, 'card') and contains(., 'Sessions')]//tr[${rowIndex}]//td[2]//a") }

	public TestRunDetailPage clickTagsInputField() {
		WebUI.click(id("tagEntity"))
		return this
	}

	// Custom Fields
	public TestRunDetailPage clickAddNewCustomField() {
		scrollToAndClick(text('Add new'))
		return this
	}

	public TestRunDetailPage inputCustomFieldDisplayName(String displayName) {
		WebUI.click(txt('Display Name'))
		WebUI.sendKeys(txt('Display Name'), displayName)
		return this
	}

	public TestRunDetailPage selectCustomFieldDisplayName(String displayName) {
		inputCustomFieldDisplayName(displayName)
		WebUI.click(option(displayName))
		return this
	}

	public TestRunDetailPage selectCustomFieldDisplayValue(String value) {
		scrollToAndSendKeys(txt('Value'), value)
		WebUI.click(xpath("(//*[contains(text(),'${value.substring(1,13)}')]/ancestor::li[@role='option'])[1]"))
		return this
	}

	public TestRunDetailPage clickAssignCustomField() {
		WebUI.click(title('Add'))
		return this
	}

	public TestRunDetailPage clickCancelAddCustomField() {
		WebUI.click(title('Cancel'))
		return this
	}

	public TestRunDetailPage verifyCustomFieldDisplay(def key, def value, int position = 1) {
		WebUI.verifyElementAttributeValue(xpath("//span[contains(@class, 'custom-field')][${position}]//div[contains(@class, '_definition')]"), 'title', "${key}", GlobalVariable.smallSleepTime)
		WebUI.verifyElementAttributeValue(xpath("//span[contains(@class, 'custom-field')][${position}]//div[contains(@class, '_option')]"), 'title', "${value}",  GlobalVariable.smallSleepTime)
		return this
	}

	public TestRunDetailPage clickUnassignCustomField(String value) {
		WebUI.click(svg(value))
	}

	public TestRunDetailPage verifyDisplayNameNotPresent(String displayName) {
		WebUI.verifyElementNotPresent(xpath("(//*[@title='${displayName}']/ancestor::li[@role='option'])[1]"), GlobalVariable.smallSleepTime)
		return this
	}

	public TestRunDetailPage verifyCustomFieldValueFieldIsDisabled() {
		WebUI.verifyElementHasAttribute(txt('Value'), 'disabled', GlobalVariable.smallSleepTime)
		return this
	}

	public TestRunDetailPage verifyAssignCustomFieldButtonIsDisabled() {
		WebUI.verifyElementHasAttribute(title('Add'), 'disabled', GlobalVariable.smallSleepTime)
		return this
	}

	public TestRunDetailPage verifyCustomFieldInfoIsEmpty() {
		WebUI.verifyElementNotPresent(xpath("(//span[contains(@class, 'custom-field')])[1]"), GlobalVariable.smallSleepTime)
		return this
	}

	//TAGS
	public TestRunDetailPage createNewTag(String nameOfTag) {
		WebUI.sendKeys(id("tagEntity"), nameOfTag)
		return this
	}

	public TestRunDetailPage searchExistingTagAndAssign(String nameOfTag) {
		WebUI.setText(id("tagEntity"), nameOfTag)
		scrollToAndClick(title(nameOfTag))
		return this
	}

	public TestRunDetailPage verifyCreateNewTag(String nameOfTag) {
		WebUI.verifyElementVisible(title(nameOfTag + ' (new tag)'))
		return this
	}

	public TestRunDetailPage assignTag(String nameOfTag) {
		WebUI.sendKeys(id("tagEntity"), Keys.chord(Keys.ENTER))
		return this
	}

	public TestRunDetailPage unassignTag(String nameOfTag) {
		WebUI.click(xpath("(.//*[normalize-space(text()) and normalize-space(.)='${nameOfTag}'])[1]/following::*[name()='svg'][1]"))
		return this
	}

	public TestRunDetailPage verifyTagIsVisible(String nameOfTag) {
		WebUI.verifyElementText(text(nameOfTag), nameOfTag)
		return this
	}

	public TestRunDetailPage verifyTagNotPresent(String tag) {
		WebUI.verifyElementNotPresent(xpath("(//*[@title='${tag}']/ancestor::li[@role='option'])[1]"), GlobalVariable.smallSleepTime)
		return this
	}

	public TestRunDetailPage verifySessionPassed(int rowIndex) {
		WebUI.verifyElementVisible(sessionPassed(rowIndex))
		return this
	}

	public TestRunDetailPage verifySessionScriptRepo(int rowIndex, String expecteValue) {
		WebUI.verifyElementVisible(sessionScriptRepo(rowIndex))
		WebUI.verifyElementText(sessionScriptRepo(rowIndex), expecteValue)
		return this
	}

	public TestRunDetailPage verifySessionTestEnv(int rowIndex, String expecteValue) {
		WebUI.verifyElementVisible(sessionTestEnv(rowIndex))
		WebUI.verifyElementText(sessionTestEnv(rowIndex), expecteValue)
		return this
	}

	public TestRunDetailPage clickSessionId(int rowIndex) {
		WebUI.click(sessionId(rowIndex))
		return this
	}
}
