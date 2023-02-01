package katalon.my.account

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable
import katalon.common.WelcomePage
import katalon.fw.lib.BasePage
import katalon.my.usermanagement.InviteUsersPopup

public class CreateNewAccountPopUp extends BasePage<CreateNewAccountPopUp> {

	public CreateNewAccountPopUp inputAccountName(String value) {
		WebUI.sendKeys(txt('Account Name'), value)
		return this
	}

	public CreateNewAccountPopUp inputOrganizationtName(String value) {
		WebUI.sendKeys(txt('Organization Name'), value)
		return this
	}

	public CreateNewAccountPopUp inputProjectName(String value) {
		WebUI.sendKeys(txt('Project Name'), value)
		return this
	}


	public CreateNewAccountPopUp inputUser2Invite2Organization(String value) {
		WebUI.sendKeys(txt('Invite User to Organization (Optional)'), value)
		return this
	}

	public CreateNewAccountPopUp clickCreate() {
		WebUI.click(btn('Create'))
		return this
	}

	public WelcomePage clickCancel() {
		WebUI.click(btn('Cancel'))
		return this
	}

	public CreateNewAccountPopUp verifyCreateNewAccountPopUpPresent() {
		WebUI.verifyElementPresent(text('Create New Account'), 5)
		WebUI.verifyElementPresent(btn('Cancel'), 5)
		WebUI.verifyElementPresent(btn('Create'), 5)
		WebUI.verifyElementPresent(id('create.account.form.account_name.text_field'), 5)
		WebUI.verifyElementPresent(id('create.account.form.organization_name.text_field'), 5)
		WebUI.verifyElementPresent(id('create.account.form.project_name.text_field'), 5)
		WebUI.verifyElementPresent(id('create.account.form.emails.text_field'), 5)
		return this
	}

	public CreateNewAccountPopUp inputInviteUserEmail(List<String> value) {
		WebUI.sendKeys(txt('Invite User to Organization (Optional)'), value.join(' ')+' ')
		return this
	}

	public CreateNewAccountPopUp verifyErrorMessageOver5UsersInvited() {
		def errorMessage = WebUI.getText(xpath("(//*[@id = 'create.account.form.emails.invitations.error'])"))
		println(errorMessage)
		WebUI.verifyEqual(errorMessage, "You’ve exceeded the user quota. Please upgrade your plan to invite more members.")
		return this
	}

	public CreateNewAccountPopUp clickClosePopup() {
		WebUI.click(xpath("(//*[(text()='Create New Account')])/following::div/child::button"))
		return this
	}
}
