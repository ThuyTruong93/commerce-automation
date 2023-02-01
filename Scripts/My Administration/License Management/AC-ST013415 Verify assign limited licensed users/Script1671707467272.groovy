import katalon.common.MySignInPage
import katalon.common.WelcomePage
import katalon.fw.lib.Page
import katalon.my.account.AccountHomePage
import katalon.my.licensemanagement.AssignLicensePage
import katalon.my.licensemanagement.LicenseManagementPage

List<String> emailList = emails.split(',')

'User signs in to administration page'
Page.nav(MySignInPage).enterCredential().clickSignIn().verifySuccessfullySignIn()

'User selects the account'
Page.nav(WelcomePage).selectAccount(accountId)

'User click Manage My License via Quick Action to go to License Management page'
Page.nav(AccountHomePage).clickManageMyLicenses()

'User selects a license tab and click Assign License button'
Page.nav(LicenseManagementPage).selectTab(licenseName).clickAssignLicense()

'User selects users to assign license'
Page.nav(AssignLicensePage).selectUsers(emailList)
					.clickAssignLicenseBtn()
					.verifyNotificationExceedQuota()
					.sleepALittleTime()
					