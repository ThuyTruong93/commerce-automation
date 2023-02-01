import com.kms.katalon.core.annotation.TearDown

import katalon.common.MySignInPage
import katalon.common.WelcomePage
import katalon.fw.lib.Page
import katalon.my.account.AccountHomePage
import katalon.my.account.AccountSettingPage
import katalon.my.account.CreateNewAccountPopUp
import katalon.my.account.DeleteAccountPopUp

'1. User signs in to administration page'
Page.nav(MySignInPage).enterCredential().clickSignIn().verifySuccessfullySignIn()

'2. User lands on Welcome page > click + button to create a new account'
Page.nav(WelcomePage).clickCreateANewAccount()

'3. User starts inputing general information [account name, organization name, project name, invite user] on Create new account pop up'
Page.nav(CreateNewAccountPopUp)
						.inputAccountName(account_name)
						.inputOrganizationtName(organization_name)
						.inputProjectName(project_name)
						.inputUser2Invite2Organization(invite_user)
						.clickCreate().sleepSomeTime()

'4. Verify the new created account display corrent information'
Page.nav(AccountHomePage).verifyAccountName(account_name)
					     .verifyNoOfMemberOnThisAccount(no_of_member)
						 .verifyNoOfOrganizationOnThisAccount(no_of_organization)
						 .verifyAccountRole(account_role)

@TearDown
def deleteAccount() {
	Page.nav(AccountHomePage).clickGetTheAccountID()
	String accountId = Page.nav(AccountSettingPage).getAccountId()
	Page.nav(AccountSettingPage).clickDeleteAccount()
	Page.nav(DeleteAccountPopUp).inputAccountName().clickDeleteThisAccount().sleepALittleTime()
}						