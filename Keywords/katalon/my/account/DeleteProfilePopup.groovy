package katalon.my.account

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class DeleteProfilePopup  extends BasePage<DeleteProfilePopup>{

	public DeleteProfilePopup verifyUIDeleteAccountPopup() {
		WebUI.verifyElementPresent(text("Delete Katalon Account"), 5)
		def textLineOne = WebUI.getText(xpath("(//h2[text()='Delete Katalon Account']//following::p)[1]"))
		def textLineTwo = WebUI.getText(xpath("(//h2[text()='Delete Katalon Account']//following::p)[2]"))
		WebUI.verifyEqual(textLineOne,"By clicking DELETE ACCOUNT, you agree to remove your Katalon Account. After removal, you will no longer be able to login to Katalon Studio and Katalon Forum using this account.")
		WebUI.verifyEqual(textLineTwo,"If you are using Katalon Platform and wish to remove the data or cancel the subscription associated with this account, please log in to Katalon Platform and manually delete/cancel them.")
		WebUI.verifyElementPresent(text("Could you tell us your reasons for leaving Katalon?"), 5)
		WebUI.verifyElementPresent(xpath("//textarea[@name='reason']"), 5)
		WebUI.verifyElementPresent(text("Enter your password to confirm the deletion"), 5)
		WebUI.verifyElementPresent(xpath("//input[@name='password']"), 5)
		WebUI.verifyElementPresent(btn("Cancel"), 5)
		WebUI.verifyElementPresent(btn("Delete Account"), 5)
		return this
	}
	
	public DeleteProfilePopup clickCancelBtn() {
		WebUI.click(btn("Cancel"))
		return this
	}
	
	public DeleteProfilePopup clickDeleteAccountBtn() {
		WebUI.click(btn("Delete Account"))
		return this
	}
}
