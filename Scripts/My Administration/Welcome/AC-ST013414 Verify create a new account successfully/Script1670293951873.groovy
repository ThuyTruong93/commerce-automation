import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.util.CryptoUtil

import internal.GlobalVariable
import katalon.common.MySignInPage
import katalon.common.WelcomePage
import katalon.fw.lib.Page
import katalon.my.account.AccountHomePage
import katalon.my.account.CreateNewAccountPopUp
import katalon.services.MyAccountService
import katalon.testops.services.LoginService

'User signs in to administration page'
Page.nav(MySignInPage).enterCredential().clickSignIn().verifySuccessfullySignIn()

'User lands on Welcome page > click + button to create a new account'
Page.nav(WelcomePage).clickCreateANewAccount()

'User starts inputing general information [account name, organization name, project name, invite user] on Create new account pop up'
Page.nav(CreateNewAccountPopUp)
						.inputAccountName(account_name)
						.inputOrganizationtName(organization_name)
						.inputProjectName(project_name)
						.inputUser2Invite2Organization(invite_user)
						.clickCreate()
						.sleepALittleTime()


'Verify the new created account display corrent information'
Page.nav(AccountHomePage).verifyAccountName(account_name)
					     .verifyNoOfMemberOnThisAccount(no_of_member)
						 .verifyNoOfOrganizationOnThisAccount(no_of_organization)
						 .verifyAccountRole(account_role)
						 
'Clear up data'
String accountId = WebUI.getUrl().toString().tokenize('=').last()
String decryptedPwd =  CryptoUtil.decode(CryptoUtil.getDefault(GlobalVariable.owner_pass))
Page.nav(LoginService).login(GlobalVariable.owner_mail, decryptedPwd).verifyStatusCode(200).getToken()
Page.nav(MyAccountService).initRequestObject()
					 .setBearerAuthorizationHeader()
					 .setJsonContentTypeHeader()
					 .setUrlWithAccountId(accountId)
					 .sendDeleteRequest()
					 .verifyStatusCode(200)
