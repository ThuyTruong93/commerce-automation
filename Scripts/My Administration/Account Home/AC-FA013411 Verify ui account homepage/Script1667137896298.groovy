import katalon.common.MySignInPage
import katalon.common.WelcomePage
import katalon.my.account.AccountHomePage
import katalon.fw.lib.Page

'1. User signs in to administration page'
Page.nav(MySignInPage).enterCredential(username, password).clickSignIn().verifySuccessfullySignIn()

'2. User selects the account'
Page.nav(WelcomePage).selectAccount(accountId)

'3. Verify Account Homepage present'
Page.nav(AccountHomePage).verifyAccountHomepagePresent()

'3. Verify avatar of Account on Account Homepage present'
Page.nav(AccountHomePage).verifyAccountHomepagePresent()
