package katalon.my.usermanagement

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage
import katalon.my.account.CreateNewAccountPopUp
import katalon.my.projectmanagement.EditProjectNamePopup



public class InviteUsersPopup extends BasePage<InviteUsersPopup> {

	public InviteUsersPopup inputUserEmail(List<String> value) {
		WebUI.sendKeys(txt('Insert email'), value.join(' ')+' ')
		return this
	}

	public InviteUsersPopup inputOneEmail(String email) {
		WebUI.sendKeys(txt('Insert email'), email+' ')
		return this
	}

	public InviteUsersPopup verifyContentAfterHoverDisableInviteUsersButton() {
		def hoverText = WebUI.getAttribute(xpath("(//button[(text()='Invite Users')])/parent::span"), "aria-label")
		println(hoverText)
		WebUI.verifyEqual(hoverText, "You’ve exceeded the user quota.")
		return this
	}

	public InviteUsersPopup clickSendInvitationButton() {
		WebUI.click(btn("Send Invitation"))
		return this
	}

	public InviteUsersPopup clickNextButton() {
		WebUI.click(btn("Next"))
		return this
	}

	public InviteUsersPopup clickConfirmButton() {
		WebUI.click(btn("Confirm"))
		return this
	}

	public InviteUsersPopup clearInputEmail() {
		WebUI.clearText(xpath("(//*[(text()='Insert email')])/following::div/child::input"))
		return this
	}
	
	public InviteUsersPopup selectLicense(String licenseName) {
		WebUI.click(xpath("//input[@value='$licenseName']"))
		return this
	}

	public InviteUsersPopup verifyErrorMessageOver5UsersInvited() {
		def errorMessage = WebUI.getText(xpath("(//*[@id = 'notistack-snackbar'])"))
		println(errorMessage)
		WebUI.verifyEqual(errorMessage, "You’ve exceeded the user quota. Please upgrade your plan to invite more members.")
		return this
	}
}
