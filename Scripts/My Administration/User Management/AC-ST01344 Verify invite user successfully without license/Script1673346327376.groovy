import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.util.CryptoUtil

import internal.GlobalVariable
import katalon.common.AdminSideBar
import katalon.common.MySignInPage
import katalon.common.WelcomePage
import katalon.fw.lib.Page
import katalon.my.account.AccountHomePage
import katalon.my.account.CreateNewAccountPopUp
import katalon.my.usermanagement.InviteUsersPopup
import katalon.my.usermanagement.UserManagementPage
import katalon.services.MyAccountService
import katalon.testops.services.LoginService
import katalon.utility.CommonUtility

'User signs in to administration page'
Page.nav(MySignInPage).enterCredential(username, password).clickSignIn().verifySuccessfullySignIn()

'User lands on Welcome page > click + button to create a new account'
Page.nav(WelcomePage).clickCreateANewAccount()

'User starts inputing general information [account name, organization name, project name, invite user] on Create new account pop up'
Page.nav(CreateNewAccountPopUp)
						.inputAccountName(accountName)
						.inputOrganizationtName('Org '+accountName)
						.inputProjectName('Project '+accountName)
						.clickCreate()
						.sleepALittleTime()

String accountId = WebUI.getUrl().toString().tokenize('=').last()	
						
'User click Invite Users To Account in Quick Action'
Page.nav(AccountHomePage).clickInviteUsersToAccount()

'User clicks Invite button'
Page.nav(UserManagementPage).clickInviteUsersButton()

'User inputs valid emails'
List<String> emailList = CommonUtility.convertString2ListString(emails, ',')
Page.nav(InviteUsersPopup).inputUserEmail(emailList)
						.clickSendInvitationButton()

'Verify emails are shown on Pending Invitation tab with invitation link'
Page.nav(UserManagementPage).verifyIsAtPendingInvitationPage()
							.verifyEmailsExist(emailList)
							
'User goes to Account Settings page to clear test data'
Page.nav(AdminSideBar).clickAccountSettings()

'Clear up data'
String decryptedPwd =  CryptoUtil.decode(CryptoUtil.getDefault(GlobalVariable.owner_pass))
Page.nav(LoginService).login(GlobalVariable.owner_mail, decryptedPwd).verifyStatusCode(200).getToken()
Page.nav(MyAccountService).initRequestObject()
					.setBearerAuthorizationHeader()
					.setJsonContentTypeHeader()
					.setUrlWithAccountId(accountId)
					.sendDeleteRequest()
					.verifyStatusCode(200)
