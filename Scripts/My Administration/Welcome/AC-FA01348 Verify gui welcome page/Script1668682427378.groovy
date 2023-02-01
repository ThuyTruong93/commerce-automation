import katalon.common.MySignInPage
import katalon.common.WelcomePage
import katalon.my.account.CreateNewAccountPopUp
import katalon.fw.lib.Page

'1. User signs in to administration page'
Page.nav(MySignInPage).enterCredential().clickSignIn().verifySuccessfullySignIn()

'2. Verify welcome page present'
Page.nav(WelcomePage).verifyWelcomePagePresent().clickCreateANewAccount()

'3. Verify create new account popup present'
Page.nav(CreateNewAccountPopUp).verifyCreateNewAccountPopUpPresent()
