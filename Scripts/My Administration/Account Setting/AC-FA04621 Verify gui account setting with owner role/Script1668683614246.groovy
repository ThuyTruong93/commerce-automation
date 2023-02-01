import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.common.MySignInPage
import katalon.common.WelcomePage
import katalon.my.account.AccountHomePage
import katalon.fw.lib.Page
import katalon.my.account.AccountSettingPage
import katalon.my.account.CreateNewAccountPopUp
import katalon.my.account.DeleteAccountPopUp
import internal.GlobalVariable

'User signs in to administration page'
Page.nav(MySignInPage).enterCredential(username, password)
					  .clickSignIn()
					  .verifySuccessfullySignIn()

'User lands on Welcome page > click + button to create a new account'
Page.nav(WelcomePage).clickCreateANewAccount()

'User starts inputing general information on Create new account pop up'
Page.nav(CreateNewAccountPopUp)
						.inputAccountName(account_name)
						.inputOrganizationtName(account_name)
						.inputProjectName(account_name)
						.clickCreate().sleepSomeTime()
						
'User goes to Account Homepage > click <Get the Account ID> link'
Page.nav(AccountHomePage).verifyAccountRole(owner)
						 .clickGetTheAccountID()

'Verify account avatar, account name, "EditName" button are presented'
Page.nav(AccountSettingPage).verifyAccountAvatarPresent()
							.verifyEditNameButtonPresent()
							.verifyAccountNamePresent(account_name)

'Verify "Delete Account" button is presented'
Page.nav(AccountSettingPage).verifyDeleteButtonPresent()

'User clicks "Edit Name" button'
Page.nav(AccountSettingPage).clickEditNameButton()

'Verify "Edit Name" popup is presented'
Page.nav(AccountSettingPage).verifyEditNamePopupPresent()
							
'User clicks Cancel button'
Page.nav(AccountSettingPage).clickCancel()
							
'User goes to Account Setting page > click Delete button'
Page.nav(AccountSettingPage).clickDeleteAccount()

'Verify Delete popup is presented'
Page.nav(AccountSettingPage).verifyDeletePopupPresent()

'User inputs correct Account Name on Delete Account popup > go to Welcome page'
Page.nav(DeleteAccountPopUp).inputAccountName().clickDeleteThisAccount().sleepALittleTime()
