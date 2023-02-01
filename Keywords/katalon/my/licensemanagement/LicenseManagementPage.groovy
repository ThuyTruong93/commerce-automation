package katalon.my.licensemanagement

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class LicenseManagementPage extends BasePage<LicenseManagementPage> {

	public LicenseManagementPage selectTab(String licenseName) {
		String activeTab = WebUI.getUrl().split('/').last()
		println activeTab

		if(activeTab.contains(licenseName)) return this

		switch(licenseName.toLowerCase()) {
			case 'per_user_kse':
				click(text('Katalon Studio Enterprise (per-User)'))
				break;
			case 'kse':
				click(text('Katalon Studio Enterprise (Node-locked)'))
				break;
			case 'unlimited_kse':
				click(text('Katalon Studio Enterprise (Floating)'))
				break;
			case 'engine':
				click(text('Katalon Runtime Engine (Node-locked)'))
				break;
			case 'unlimited_engine':
				click(text('Katalon Runtime Engine (Floating)'))
				break;
			default:
				println 'Error: Wrong license name'
		}

		return this
	}

	public LicenseManagementPage clickAssignLicense() {
		click(title('Assign License'))
		return this
	}

	public LicenseManagementPage clickRemoveUser(String email) {
		click(xpath("//div[text()='$email']/parent::td/following-sibling::*//button"))
		return this
	}

	public LicenseManagementPage verifyEmailsInLicensedUser(List<String> emails) {
		emails.each{item -> WebUI.verifyElementVisible(xpath("//td/div[text()='$item']"))}
		return this
	}

	public LicenseManagementPage verifyNotificationRemoveUserSuccessfully(String email) {
		if(WebUI.verifyElementVisible(css(".toast-success .toast-message"), FailureHandling.CONTINUE_ON_FAILURE)) {
			String actualMsg = WebUI.getText(css(".toast-success .toast-message"), FailureHandling.CONTINUE_ON_FAILURE)
			String expectedMsg = "User $email was removed"
			WebUI.verifyEqual(actualMsg, expectedMsg, FailureHandling.CONTINUE_ON_FAILURE)
		}
		return this
	}
}
