import java.awt.print.Pageable

import katalon.common.MySignInPage
import katalon.common.WelcomePage
import katalon.common.AdminHeaderBar
import katalon.my.account.ProfilePage
import katalon.fw.lib.Page

'1. User signs in to administration page'
Page.nav(MySignInPage).enterCredential(username, password).clickSignIn().verifySuccessfullySignIn()

'2. User selects the account'
Page.nav(WelcomePage).selectAccount(accountId)

'3. Click View Profile button in the Profile dropdwon'
Page.nav(AdminHeaderBar).clickAvatar().clickViewProfile().sleepALittleTime()

'4. Verify after clicking View Profile will navigate to Profile page'
Page.nav(ProfilePage).verifyProfilePagePresent().back()

'5. Click Switch Account in the Profile dropdown'
Page.nav(AdminHeaderBar).clickAvatar().clickSwitchAccount().sleepALittleTime()

'6. Verify after clicking Switch Account will navigate to Welcome page'
Page.nav(WelcomePage).verifyWelcomePagePresent().back()

'7. Click Notification Setting in the Profile dropdown'
Page.nav(AdminHeaderBar).clickAvatar().clickNotificationSettings().sleepALittleTime().verifyNotificationSettingPresent()



