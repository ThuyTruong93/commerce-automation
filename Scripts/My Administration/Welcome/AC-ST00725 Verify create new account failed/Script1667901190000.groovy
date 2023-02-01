import katalon.common.AdminHeaderBar
import katalon.common.MySignInPage
import katalon.common.WelcomePage
import katalon.fw.lib.Page
import katalon.my.account.CreateNewAccountPopUp
import katalon.my.usermanagement.InviteUsersPopup
import katalon.my.usermanagement.UserManagementPage

'1. User signs in to administration page'
Page.nav(MySignInPage).enterCredential(username, password).clickSignIn().verifySuccessfullySignIn()

'2. Click Create new account button'
Page.nav(WelcomePage).clickCreateANewAccount()
	
'3. Try Create new account and invite more than 5 users and Verify message warning do not invite more than 5 users when create new account'
Page.nav(CreateNewAccountPopUp)
									.inputAccountName(accountName)
									.inputOrganizationtName(orgName)
									.inputProjectName(projectName)
									.inputInviteUserEmail(emailList)
									.clickCreate()
									.verifyErrorMessageOver5UsersInvited()
									.clickClosePopup()