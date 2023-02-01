import katalon.common.MySignInPage
import katalon.common.WelcomePage
import katalon.fw.lib.Page
import katalon.my.account.AccountHomePage
import katalon.my.account.AccountSettingPage

'1. User signs in to administration page'
Page.nav(MySignInPage).enterCredential(username, password).clickSignIn().verifySuccessfullySignIn()

'2. User selects the account'
Page.nav(WelcomePage).selectAccount(accountId)

'3. User goes to Account Homepage > click <Get the Account ID> link'
Page.nav(AccountHomePage).clickGetTheAccountID()

'4. Verify only Account Owner can see Delete Account button, hide that button for other roles'
Page.nav(AccountSettingPage).verifyOnlyOwnerCanViewDeleteAccount(role)