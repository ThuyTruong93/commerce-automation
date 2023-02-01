package katalon.testops.configurations
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class CustomFieldPage extends BasePage<CustomFieldPage> {

	public CustomFieldPage clickCreateBtn() {
		WebUI.click(id("create-new-custom-field"))
		return this
	}

	public CustomFieldPage clickEditIcon(String key) {
		WebUI.click(xpath("(//p[text()='${key}']/ancestor::div[contains(@class, 'custom-field-detail__container-btn')]//button)[1]"))
		return this
	}

	public CustomFieldPage clickDeleteIcon(String key) {
		WebUI.click(xpath("(//p[text()='${key}']/ancestor::div[contains(@class, 'custom-field-detail__container-btn')]//button)[2]"))
		return this
	}

	public CustomFieldPage clickDeleteButton() {
		WebUI.click(title("Delete"))
		return this
	}


	public CustomFieldPage getCustomFieldTable(String label) {
		WebUI.getText(text(label))

		return this
	}

	public CustomFieldPage verifyKeyIsVisible(String key) {
		WebUI.verifyElementVisible(text(key))
		return this
	}

	public CustomFieldPage verifyDisplayNameIsVisible(String displayName) {
		WebUI.verifyElementVisible(text(displayName))
		return this
	}

	public CustomFieldPage verifyValuesIsVisible(String values) {
		WebUI.verifyElementVisible(text(values))
		return this
	}

	public CustomFieldPage verifyCustomFieldIsNotDisplay(String key) {
		WS.verifyEqual(verifyElementNotVisible(text(key)), true)
		return this
	}
}
