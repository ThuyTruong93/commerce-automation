package katalon.testops.report

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class TestCasesPage extends BasePage<TestCasesPage>{

	public TestCasesPage clickCustomField() {
		WebUI.click(text('Custom Fields'))
		return this
	}

	public TestCasesPage clickTag() {
		WebUI.click(text('Tags'))
		return this
	}

	public TestCasesPage inputTag(String tag) {
		scrollToAndClick(id('tagEntity'))
		WebUI.sendKeys(id('tagEntity'), tag)
		WebUI.click(option(tag))
		return this
	}


	public TestCasesPage clickAddNewCustomField() {
		WebUI.click(xpath("//*[contains(text(),'Add new')]"))
		return this
	}

	public TestCasesPage inputCustomFieldDisplayName(String displayName) {
		WebUI.click(txt('Display Name'))
		WebUI.sendKeys(txt('Display Name'), displayName)
		WebUI.click(option(displayName))
		return this
	}

	public TestCasesPage inputCustomFieldDisplayValue(String value) {
		scrollToAndSendKeys(txt('Value'), value)
		WebUI.click(option(value))
		return this
	}

	public TestCasesPage clickAddCustomField() {
		WebUI.click(title('Add'))
		return this
	}

	public TestCasesPage clickUpdateBtn() {
		WebUI.click(xpath('//button[@type="submit"]'))
		return this
	}

	public TestCasesPage verifyTestCaseIsVisible(String testcase) {
		WebUI.verifyElementVisible(text(testcase))
		return this
	}
}
