package katalon.my.account

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import katalon.fw.lib.BasePage

public class ProfilePage extends BasePage<ProfilePage>{

	public ProfilePage navProfilePage () {
		WebUI.navigateToUrl(GlobalVariable.myUrl+"profile")
		return this
	}

	public ProfilePage clickChangePasswordLink () {
		WebUI.click(xpath("//*[text()='Change Password']/parent::*"))
		return this
	}

	public ProfilePage verifyProfilePagePresent () {
		WebUI.verifyEqual(WebUI.getUrl(), GlobalVariable.myUrl+"profile")
		WebUI.verifyElementPresent(xpath("//p[text()='Change Password']"), 5)
		WebUI.verifyElementPresent(xpath("//*[text()='Delete Profile']"), 5)
		WebUI.verifyElementPresent(xpath("//*[text()='Log Out']"), 5)
		WebUI.verifyElementPresent(text("Name"), 5)
		WebUI.verifyElementPresent(text("Email"), 5)
		WebUI.verifyElementPresent(text("Password"), 5)
		WebUI.verifyElementPresent(text("Job Title"), 5)
		WebUI.verifyElementPresent(text("Registered Date"), 5)
		return this
	}
	
	public ProfilePage clickDeleteProfile () {
		WebUI.click(text("Delete Profile"))
		return this
	}

	public ProfilePage clickGoToWelcomePage () {
		WebUI.click(id("profile.go_to_welcome"))
		return this
	}
}
