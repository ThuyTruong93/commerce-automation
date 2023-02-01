import katalon.common.MySignInPage
import katalon.common.WelcomePage
import katalon.fw.lib.Page
import katalon.my.account.AccountHomePage
import katalon.my.licensemanagement.AssignLicensePage
import katalon.my.licensemanagement.LicenseManagementPage
import katalon.my.licensemanagement.RemoveUserDialog
import katalon.utility.CommonUtility

List<String> emails = CommonUtility.convertString2ListString(emailList, ',')

'User signs in to administration page'
Page.nav(MySignInPage).enterCredential(username, password).clickSignIn().verifySuccessfullySignIn()

'User selects the account'
Page.nav(WelcomePage).selectAccount(accountId)

'User click Manage My License via Quick Action to go to License Management page'
Page.nav(AccountHomePage).clickManageMyLicenses().sleepALittleTime()

'User selects a license tab and click Assign License button'
Page.nav(LicenseManagementPage).selectTab(licenseName).clickAssignLicense().sleepALittleTime()

'User selects users to assign license'
Page.nav(AssignLicensePage).selectUsers(emails)
					.clickAssignLicenseBtn()
					.verifySuccessNotification(emails.size)
					.clickClose()
					.sleepALittleTime()
					
'Verify emails of licensed user shown on License Management page/Licensed Users board'
Page.nav(LicenseManagementPage).verifyEmailsInLicensedUser(emails)

'User remove selected users'
for(String email:emails) {
	Page.nav(LicenseManagementPage).clickRemoveUser(email)
	Page.nav(RemoveUserDialog).clickRemove()
	Page.nav(LicenseManagementPage).verifyNotificationRemoveUserSuccessfully(email)
}

