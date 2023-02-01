import katalon.common.MySignInPage
import katalon.common.WelcomePage
import katalon.common.AdminHeaderBar
import katalon.my.usermanagement.UserManagementPage
import katalon.fw.lib.Page


'1. User signs in to administration page'
Page.nav(MySignInPage).enterCredential(username, password).clickSignIn().verifySuccessfullySignIn()

'2. User selects the account'
Page.nav(WelcomePage).selectAccount(accountId)

'3.Click Settings on Header Navigator > Go to User Management'
Page.nav(AdminHeaderBar).clickSettingIcon().clickUserManagement()

'4. Verify show 3 tabs: "Active Users", "Pending Invitation" and "Removed Users"'
Page.nav(UserManagementPage)
							.verifyUserManagementPresent()
							.clickActiveUsers()
							.verifyDetailActiveUserTabPresent()
							.clickPendingInvitation()
							.verifyDetailPendingInvitationTabPresent()
							.clickRemovedUsers()
							.verifyDetailRemovedUsersTabPresent()