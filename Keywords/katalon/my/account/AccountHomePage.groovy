
package katalon.my.account

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import katalon.fw.lib.BasePage

public class AccountHomePage extends BasePage<AccountHomePage>  {

	public AccountHomePage verifyAccountName (String expected) {
		WebUI.verifyElementText(title(expected), expected)
		return this
	}

	public AccountHomePage verifyNoOfMemberOnThisAccount (String expected) {
		String actual = WebUI.getText(xpath("//*[text()='Member on this Account']/child::*"))
		WebUI.verifyEqual(actual, expected)
		return this
	}

	public AccountHomePage verifyNoOfOrganizationOnThisAccount (String expected) {
		String actual = WebUI.getText(xpath("//*[text()='Organization on this Account']/child::*"))
		WebUI.verifyEqual(actual, expected)
		return this
	}

	public AccountHomePage verifyAccountRole (String expected) {
		String actual = WebUI.getText(xpath("//*[@title]/following-sibling::*"))
		WebUI.verifyEqual(actual, expected)
		return this
	}

	public AccountHomePage clickGetTheAccountID() {
		WebUI.click(id("admin.home.link.get.the.account.id"))
		return this
	}

	public AccountHomePage clickManageMyLicenses() {
		WebUI.click(id("admin.home.link.manage.my.licenses"))
		return this
	}

	public AccountHomePage clickInviteUsersToAccount() {
		WebUI.click(id("admin.home.link.invite.users.to.account"))
		return this
	}

	public AccountHomePage navigate2LicenseManagementPage(String orgId) {
		WebUI.navigateToUrl(GlobalVariable.myUrl+"organization/$orgId/admin/license_keys/per_user_kse")
		return this
	}

	public AccountHomePage verifyAccountHomepagePresent () {
		WebUI.verifyElementPresent(xpath("//img[@alt='avatar']"), 5)
		WebUI.verifyElementPresent(text('Member on this Account'), 5)
		WebUI.verifyElementPresent(text('Organization on this Account'), 5)
		WebUI.verifyElementPresent(text('My Katalon Products'), 5)
		WebUI.verifyElementPresent(text('Quick Actions'), 5)
		WebUI.verifyElementPresent(text('Featured Documentation'), 5)
		return this
	}

	public AccountHomePage checkHaveKSEperUserProduct () {
		WebUI.verifyElementPresent(text('Studio Enterprise (per-User)'), 5)
		return this
	}

	public AccountHomePage clickAvatar() {
		WebUI.click(id("user_setting"))
		return this
	}

	public AccountHomePage clickProfile() {
		WebUI.click(text("View Profile"))
		return this
	}
}


