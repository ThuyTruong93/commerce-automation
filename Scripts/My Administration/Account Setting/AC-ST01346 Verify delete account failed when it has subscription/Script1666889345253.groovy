import katalon.common.MySignInPage
import katalon.common.WelcomePage
import katalon.fw.lib.Page
import katalon.my.account.AccountHomePage
import katalon.my.account.AccountSettingPage
import katalon.my.account.DeleteAccountPopUp


'1. User signs in to administration page'
Page.nav(MySignInPage).enterCredential(username, password).clickSignIn().verifySuccessfullySignIn()

'2. User selects an account'
Page.nav(WelcomePage).selectAccount(accountId)

'3. User goes to Account Homepage > click <Get the Account ID> link'
Page.nav(AccountHomePage).clickGetTheAccountID()

'3. User goes to Account Setting page > click Delete button'
Page.nav(AccountSettingPage).clickDeleteAccount()

'4. User inputs correct Account Name on Delete Account popup > go to Welcome page'
Page.nav(DeleteAccountPopUp).inputAccountName().clickDeleteThisAccount().sleepALittleTime()

'5. Verify the Account cannot be deleted because account has subscriptions'
Page.nav(AccountSettingPage).verifyMessageCannotDeleteAccount()
