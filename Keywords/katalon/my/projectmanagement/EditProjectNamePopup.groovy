package katalon.my.projectmanagement

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage



public class EditProjectNamePopup extends BasePage<EditProjectNamePopup> {

	public EditProjectNamePopup inputProjectName(String name) {
		WebUI.clearText(xpath("//input[contains(@id,'edit-project-name-popup-input-field')]"))
		WebUI.sendKeys(xpath("//input[contains(@id,'edit-project-name-popup-input-field')]"), name)
		return this
	}

	public EditProjectNamePopup clickSave() {
		WebUI.click(text("Save"))
		return this
	}
}
