package katalon.my.account
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class ChangePasswordPopup extends BasePage<ChangePasswordPopup> {
	
	public ChangePasswordPopup verifyUIChangePasswordPopup() {
		WebUI.verifyElementPresent(txt('Change Password'), 5)
		WebUI.verifyElementPresent(txt('CURRENT PASSWORD *'), 5)
		WebUI.verifyElementPresent(txt('NEW PASSWORD *'), 5)
		WebUI.verifyElementPresent(txt('CONFIRM PASSWORD *'), 5)
		WebUI.verifyElementPresent(btn('Forgot your password?'), 5)
		WebUI.verifyElementPresent(btn('Cancel'), 5)
		WebUI.verifyElementPresent(btn('Change Password'), 5)
		return this
	}
	
	public ChangePasswordPopup inputCurrentPassword(String value) {
		WebUI.sendKeys(txt('CURRENT PASSWORD *'), value)
		WebUI.setEncryptedText(byName('password'), value)
		return this
	}

	public ChangePasswordPopup inputNewPassword(String value) {
		WebUI.sendKeys(txt('NEW PASSWORD *'), value)
		WebUI.setEncryptedText(byName('newPassword'), value)
		return this
	}

	public ChangePasswordPopup inputConfirmPassword(String value) {
		WebUI.sendKeys(txt('CONFIRM PASSWORD *'), value)
		WebUI.setEncryptedText(byName('confirmPassword'), value)
		return this
	}

	public ChangePasswordPopup clickChangePasswordBtn () {
		WebUI.click(btn('Change Password'))
		return this
	}

	public ChangePasswordPopup clickForgotPassword () {
		WebUI.click(link('Forgot your password?'))
		return this
	}
	
	public ChangePasswordPopup clickCancelBtn () {
		WebUI.click(btn('Cancel'))
		return this
	}

	public ChangePasswordPopup verifySuccessfullyChangePassword () {
		def successfullMessage = WebUI.getText(xpath("(//*[@id = 'notistack-snackbar'])"))
		println(successfullMessage)
		WebUI.verifyEqual(successfullMessage, "Password has been updated.")
		return this
	}

	public ChangePasswordPopup verifyEmptyChangePasswordFailedEmptyThreeField () {
		String currentPassText = WebUI.getText(xpath("(//*[text()='CURRENT PASSWORD *'])/following::div"))
		println(currentPassText)
		String newPassText = WebUI.getText(xpath("(//*[text()='NEW PASSWORD *'])/following::div"))
		println(newPassText)
		String confirmPassText = WebUI.getText(xpath("(//*[text()='CONFIRM PASSWORD *'])/following::div"))
		println(confirmPassText)
		println(currentPassText != null)
		if((confirmPassText.length() > 0) && (currentPassText.length() > 0) && (newPassText.length() > 0)) {
			WebUI.verifyEqual(currentPassText,"Please fill in CURRENT PASSWORD")
			WebUI.verifyEqual(newPassText,"Please fill in NEW PASSWORD")
			WebUI.verifyEqual(confirmPassText, "Please fill in CONFIRM PASSWORD")
			return this
		}
		else if((currentPassText.length() > 0) && (confirmPassText.length() == 0 ) && (newPassText.length() == 0 )) {
			WebUI.verifyMatch(currentPassText,"Please fill in CURRENT PASSWORD", false)
			return this
		}
		else if((newPassText.length() > 0) && (confirmPassText.length() == 0 ) && (currentPassText.length() == 0 )) {
			WebUI.verifyMatch(newPassText,"Please fill in NEW PASSWORD", false)
			return this
		}
		else if((confirmPassText.length() > 0) && (newPassText.length() == 0 ) && (currentPassText.length() == 0 )) {
			WebUI.verifyMatch(confirmPassText, "Please fill in CONFIRM PASSWORD", false)
			return this
		}
		else
			return this
	}

	public ChangePasswordPopup verifyEmptyChangePasswordFailedEmptyCurrentPasswordField () {
		String currentPassText = WebUI.getText(text('CURRENT PASSWORD '))
		println(currentPassText)
		WebUI.verifyMatch(currentPassText,"Please fill in CURRENT PASSWORD", false)
		return this
	}

	public ChangePasswordPopup verifyEmptyChangePasswordFailedEmptyNewPasswordField () {
		String newPassText = WebUI.getText(text('NEW PASSWORD '))
		println(newPassText)
		WebUI.verifyMatch(newPassText,"Please fill in NEW PASSWORD", false)
		return this
	}

	public ChangePasswordPopup verifyEmptyChangePasswordFailedEmptyConfirmPasswordField () {
		String confirmPassText = WebUI.getText(text('CONFIRM PASSWORD '))
		println(confirmPassText)
		WebUI.verifyMatch(confirmPassText, "Please fill in CONFIRM PASSWORD", false)
		return this
	}

	public ChangePasswordPopup verifyChangePasswordFailedIncorrectPassword () {
		String getError = WebUI.getText(xpath("(//*[text()='CURRENT PASSWORD *'])/following::div"))
		println(getError)
		WebUI.verifyMatch(getError,"Incorrect password.", false)
		return this
	}

	public ChangePasswordPopup verifyChangePasswordFailedPasswordDoNotMatch () {
		String getError = WebUI.getText(xpath("(//*[text()='CONFIRM PASSWORD *'])/following::div"))
		println(getError)
		WebUI.verifyMatch(getError,"Passwords do not match", false)
		return this
	}

	public ChangePasswordPopup verifyChangePasswordFailedWrongRulesPassword () {
		String getError = WebUI.getText(xpath("(//*[text()='NEW PASSWORD *'])/following::div"))
		println(getError)
		WebUI.verifyMatch(getError, "Password must be a minimum of 8 characters, maximum of 255 characters, contain at least 1 upper case, 1 lower case, 1 special character, 1 number and must not start or end with a space", false)
		return this
	}

	public ChangePasswordPopup verifyChangePasswordFailedInputMoreThan255Chars () {
		String getError = WebUI.getText(xpath("(//*[text()='NEW PASSWORD *'])/following::div"))
		println(getError)
		WebUI.verifyMatch(getError,"Please enter no more than 255 characters", false)
		return this
	}
}