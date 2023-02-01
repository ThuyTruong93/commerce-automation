package katalon.common

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import katalon.fw.lib.BasePage


public class AdminSideBar extends BasePage<AdminSideBar>  {

	public AdminSideBar clickProductUtilization() {
		WebUI.click(genItem("Product Utilization"))
		return this
	}

	public AdminSideBar clickLicenseManagement() {
		WebUI.click(genItem("License Management"))
		return this
	}

	public AdminSideBar clickUserManagement () {
		WebUI.click(genItem("User Management"))
		return this
	}

	public AdminSideBar clickPaymentMethod () {
		WebUI.click(genItem("Payment Method"))
		return this
	}

	public AdminSideBar clickSubscriptionManagement () {
		WebUI.click(genItem("Subscription Management"))
		return this
	}

	public AdminSideBar clickOrganizationManagement () {
		WebUI.click(genItem("Organization Management"))
		return this
	}

	public AdminSideBar clickSupportManagement () {
		WebUI.click(genItem("Support Management"))
		return this
	}

	public AdminSideBar clickTeamManagement() {
		WebUI.click(genItem("Project Management"))
		return this
	}

	public AdminSideBar clickProjectManagement() {
		WebUI.click(genItem("Project Management"))
		return this
	}

	public AdminSideBar clickAccountSettings() {
		WebUI.click(genItem("Account Settings"))
		return this
	}

	private TestObject genItem(String itemName) {
		return xpath("//a[text()='$itemName']");
	}
}
