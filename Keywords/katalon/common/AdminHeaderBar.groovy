package katalon.common

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import katalon.fw.lib.BasePage


public class AdminHeaderBar extends BasePage<AdminHeaderBar>  {

	public AdminHeaderBar clickAvatar() {
		WebUI.click(css('.MuiAvatar-img'))
		return this
	}

	public AdminHeaderBar clickViewProfile() {
		WebUI.click(text('View Profile'))
		return this
	}

	public AdminHeaderBar clickNotificationSettings() {
		WebUI.click(text('Notification Settings'))
		return this
	}

	public AdminHeaderBar clickDocumentation() {
		WebUI.click(text('Documentation'))
		return this
	}

	public AdminHeaderBar clickCommunity() {
		WebUI.click(text('Community'))
		return this
	}

	public AdminHeaderBar clickSubmitATicket() {
		WebUI.click(text('Submit A Ticket'))
		return this
	}

	public AdminHeaderBar clickSwitchAccount() {
		WebUI.click(text('Switch Account'))
		return this
	}

	public AdminHeaderBar verifyNotificationSettingPresent() {
		WebUI.verifyEqual(WebUI.getUrl(), GlobalVariable.testOpsUrl+"user/settings?")
		return this
	}

	public AdminHeaderBar clickSettingIcon() {
		WebUI.click(xpath("(//header//button[1])[2]"))
		return this
	}

	public AdminHeaderBar clickProductUtilization() {
		WebUI.click(genDropdownItem("Product Utilization"))
		return this
	}

	public AdminHeaderBar clickLicenseManagement() {
		WebUI.click(genDropdownItem("License Management"))
		return this
	}

	public AdminHeaderBar clickUserManagement () {
		WebUI.click(genDropdownItem("User Management"))
		return this
	}

	public AdminHeaderBar clickPaymentMethod () {
		WebUI.click(genDropdownItem("Payment Method"))
		return this
	}

	public AdminHeaderBar clickSubscriptionManagement () {
		WebUI.click(genDropdownItem("Subscription Management"))
		return this
	}

	public AdminHeaderBar clickOrganizationManagement () {
		WebUI.click(genDropdownItem("Organization Management"))
		return this
	}

	public AdminHeaderBar clickSupportManagement () {
		WebUI.click(genDropdownItem("Support Management"))
		return this
	}

	public AdminHeaderBar clickTeamManagement() {
		WebUI.click(genDropdownItem("Project Management"))
		return this
	}

	public AdminHeaderBar clickProjectManagement() {
		WebUI.click(genDropdownItem("Project Management"))
		return this
	}

	public AdminHeaderBar clickTestOpsHomepage() {
		WebUI.click(genDropdownItem("TestOps Homepage"))
		return this
	}
	
	private TestObject genDropdownItem(String itemName) {
		return xpath("//span[text()='$itemName']");
	}
}
