import katalon.common.AdminHeaderBar
import katalon.common.MySignInPage
import katalon.common.WelcomePage
import katalon.fw.lib.Page
import katalon.my.supportmanagement.SupportManagementPage

'User sign in to administration page'
Page.nav(MySignInPage).enterCredential().clickSignIn().verifySuccessfullySignIn()

'User selects the account'
Page.nav(WelcomePage).selectAccount(accountId)

'Click Settings on Header Navigator > Go to Support Management'
Page.nav(AdminHeaderBar).clickSettingIcon().clickSupportManagement()

'Verify UI Support Management'
Page.nav(SupportManagementPage).verifyUISupportManagement()
