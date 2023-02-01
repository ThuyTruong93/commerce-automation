import katalon.common.AdminHeaderBar
import katalon.common.MySignInPage
import katalon.common.WelcomePage
import katalon.fw.lib.Page
import katalon.my.account.CreateNewAccountPopUp
import katalon.my.usermanagement.InviteUsersPopup
import katalon.my.usermanagement.UserManagementPage

'1. User signs in to administration page'
Page.nav(MySignInPage).enterCredential(username, password).clickSignIn().verifySuccessfullySignIn()

switch(selectedAccount.toString())
{
	case 'NonKS_5users':
	'Cases verify button Invite Users disable when users over 5 users'
	'2. User selects the account'
	Page.nav(WelcomePage).selectAccount(accountId)

	'3.Click Settings on Header Navigator > Go to User Management'
	Page.nav(AdminHeaderBar).clickSettingIcon().clickUserManagement()

	'4. Verify when users over 5 users (inclunding Pending Invitation) cannot click Invite Users button and show tooltips'
	Page.nav(UserManagementPage)
							.verifyDisableInviteUsersButton()
							.verifyContentAfterHoverDisableInviteUsersButton()
	break
							
	case 'NonKS_4users':
	'Cases verify show message error when users over 5 users with Org do not have KS product'
	'2. User selects the account'
	Page.nav(WelcomePage).selectAccount(accountId)

	'3.Click Settings on Header Navigator > Go to User Management'
	Page.nav(AdminHeaderBar).clickSettingIcon().clickUserManagement()

	'4. Go to Invite Users Popup'
	Page.nav(UserManagementPage).clickInviteUsersButton()
	
	'5. Verify message when users over 5 users on Invite Users to Organization'
	Page.nav(InviteUsersPopup)
								.inputUserEmail(emailList)
								.clickSendInvitationButton()
								.verifyErrorMessageOver5UsersInvited()
	break
								
	case 'KS_4users':
	'Cases verify show message error when users over 5 users with Org have KS product on button Next'
	'2. User selects the account'
	Page.nav(WelcomePage).selectAccount(accountId)
							
	'3.Click Settings on Header Navigator > Go to User Management'
	Page.nav(AdminHeaderBar).clickSettingIcon().clickUserManagement()
							
	'4. Go to Invite Users Popup'
	Page.nav(UserManagementPage).clickInviteUsersButton()
								
	'5. Verify message when users over 5 users on Invite Users to Organization'
	Page.nav(InviteUsersPopup)
								.inputUserEmail(emailList)
								.clickNextButton()
								.verifyErrorMessageOver5UsersInvited()
	break
	
}