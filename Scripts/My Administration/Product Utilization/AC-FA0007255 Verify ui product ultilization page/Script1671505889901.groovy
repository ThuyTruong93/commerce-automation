import katalon.common.AdminHeaderBar
import katalon.common.MySignInPage
import katalon.common.WelcomePage
import katalon.fw.lib.Page
import katalon.my.productutilization.KatalonPlatformUtilizationPage
import katalon.my.productutilization.KatalonStudioUtilizationPage
import katalon.my.productutilization.TestCloudUtilizationPage

'User sign in to administration page'
Page.nav(MySignInPage).enterCredential().clickSignIn().verifySuccessfullySignIn()

'User selects the account'
Page.nav(WelcomePage).selectAccount(accountId)

'Click Settings on Header Navigator > Go to Product Utilization page'
Page.nav(AdminHeaderBar).clickSettingIcon().clickProductUtilization()

'Verify UI Katalon Studio page'
Page.nav(KatalonStudioUtilizationPage).clickKatalonStudioTab().verifyUIKatalonStudioUtilizationPage()

'Verify UI Katalon Platform page'
Page.nav(KatalonPlatformUtilizationPage).clickKatalonPlatformTab().verifyUIKatalonPlatformUtilizationPage()

'Verify UI TestCloud page'
Page.nav(TestCloudUtilizationPage).clickKatalonTestCloudTab().verifyUITestCloudUtilizationPage()