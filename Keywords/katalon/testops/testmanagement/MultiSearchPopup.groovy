package katalon.testops.testmanagement

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class MultiSearchPopup  extends BasePage<MultiSearchPopup>{

	public MultiSearchPopup clickAddNewCustomField() {
		WebUI.click(xpath("//*[contains(text(),'Add new')]"))
		return this
	}

	public MultiSearchPopup inputCustomFieldDisplayName(String displayName) {
		WebUI.click(txt('Display Name'))
		WebUI.sendKeys(txt('Display Name'), displayName)
		WebUI.click(option(displayName))
		return this
	}

	public MultiSearchPopup inputCustomFieldDisplayValue(String value) {
		scrollToAndSendKeys(txt('Value'), value)
		WebUI.click(option(value))
		return this
	}

	public MultiSearchPopup clickAddCustomField() {
		WebUI.click(title('Add'))
		return this
	}

	public MultiSearchPopup clickApplyAndSearch() {
		WebUI.click(xpath('//button[@type="submit"]'))
		return this
	}

	public MultiSearchPopup inputTag(String tag) {
		clearTextAndSendKeys(id('tagEntity'), tag)
		WebUI.click(option(tag))
		return this
	}
}
