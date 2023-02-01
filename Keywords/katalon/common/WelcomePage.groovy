package katalon.common

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import katalon.fw.lib.BasePage

public class WelcomePage extends BasePage<WelcomePage> {

	public WelcomePage clickCreateANewAccount () {
		WebUI.click(svg('Create A New Account'))
		return this
	}

	public WelcomePage selectAccount (String accountId) {
		scrollToAndClick(xpath("//a[contains(@href,'accountId=$accountId')]"))
		return this
	}

	public WelcomePage verifyAccountIsDeleted(String accountId) {
		WebUI.verifyElementNotPresent(xpath("//a[contains(@href,'accountId=$accountId')]"), 10)
		return this
	}

	public WelcomePage verifyWelcomePagePresent() {
		WebUI.verifyElementPresent(svg('Welcome to Katalon'), 5)
		WebUI.verifyElementPresent(text("Welcome to Katalon"), 5)
		WebUI.verifyElementPresent(svg('Create A New Account'), 5)
		return this
	}
}

