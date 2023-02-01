package katalon.common

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import katalon.fw.lib.BasePage

public class MySignInPage extends BasePage<MySignInPage> {

	public MySignInPage enterCredential () {
		WebUI.navigateToUrl(GlobalVariable.myUrl)
		WebUI.waitForElementPresent(byName('email'), 60)
		WebUI.sendKeys(byName('email'), GlobalVariable.owner_mail)
		WebUI.setEncryptedText(byName('password'), GlobalVariable.owner_pass)
		return this
	}


	public MySignInPage enterCredential(String user_name, String password) {
		WebUI.navigateToUrl(GlobalVariable.myUrl)
		WebUI.sendKeys(byName('email'), user_name)
		WebUI.setEncryptedText(byName('password'), password)
		return this
	}

	public MySignInPage clickSignIn () {
		WebUI.click(btn('Sign In'))
		return this
	}

	public MySignInPage verifySuccessfullySignIn () {
		WebUI.verifyTextPresent('Welcome to Katalon', false)
		WebUI.verifyTextPresent('Select your Account to get started', false)
		return this
	}

	public MySignInPage doCheckRemberMe () {
		WebUI.click(byName('rememberMe'))
		return this
	}
}