import katalon.common.MySignInPage
import katalon.common.WelcomePage
import katalon.fw.lib.Page
import katalon.my.account.AccountHomePage
import katalon.my.usermanagement.InviteUsersPopup
import katalon.my.usermanagement.UserManagementPage
import katalon.utility.CommonUtility

'User signs in to administration page'
Page.nav(MySignInPage).enterCredential(username, password).clickSignIn().verifySuccessfullySignIn()

'User selects the account'
Page.nav(WelcomePage).selectAccount(accountId)

'User click Invite Users To Account in Quick Action'
Page.nav(AccountHomePage).clickInviteUsersToAccount()

'User clicks Invite button'
Page.nav(UserManagementPage).clickInviteUsersButton()

'User inputs valid emails'
List<String> emailList = CommonUtility.convertString2ListString(emails, ',')
Page.nav(InviteUsersPopup).inputUserEmail(emailList)
							.clickNextButton()
							.selectLicense(licenseName)
							.clickConfirmButton()

'Verify emails are shown on Pending Invitation tab with invitation link'
Page.nav(UserManagementPage).verifyIsAtPendingInvitationPage()
							.verifyEmailsExist(emailList)
