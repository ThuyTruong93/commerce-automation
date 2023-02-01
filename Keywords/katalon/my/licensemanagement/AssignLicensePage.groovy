package katalon.my.licensemanagement

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class AssignLicensePage extends BasePage<AssignLicensePage>{

	public AssignLicensePage selectUsers(List<String> emails) {
		for(String email : emails) {
			click(xpath("//*[contains(text(),'$email')]/ancestor::li"))
		}
		return this
	}

	public AssignLicensePage clickAssignLicenseBtn() {
		click(btn("Assign License"))
		return this
	}

	public AssignLicensePage clickClose() {
		if(WebUI.verifyElementPresent(css("h2 svg[data-testid*='HighlightOffIcon']"), 2, FailureHandling.OPTIONAL )) {
			click(css("h2 svg[data-testid*='HighlightOffIcon']"))
		}
		return this
	}

	public AssignLicensePage verifySuccessNotification(int noOfUser) {
		WebUI.waitForElementVisible(css("#notistack-snackbar"), 10)
		String actualMsg = WebUI.getText(css("#notistack-snackbar"), FailureHandling.CONTINUE_ON_FAILURE )
		String expectedMsg = noOfUser == 1 ? "1 user successfully assigned the license." : "$noOfUser users successfully assigned the license."
		WebUI.verifyEqual(actualMsg, expectedMsg, FailureHandling.CONTINUE_ON_FAILURE)
		return this
	}
	
	public AssignLicensePage verifyNotificationExceedQuota() {
		WebUI.verifyElementText(css("#notistack-snackbar"), "The number of granted users cannot exceed the license quota.")
		return this
	}
}
