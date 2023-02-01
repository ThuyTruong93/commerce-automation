import katalon.common.MySignInPage
import katalon.common.WelcomePage
import katalon.fw.lib.Page
import katalon.my.account.ChangePasswordPopup
import katalon.my.account.DeleteProfilePopup
import katalon.my.account.ProfilePage

'User sign in to administration page'
Page.nav(MySignInPage).enterCredential().clickSignIn().verifySuccessfullySignIn()

'Go to Profile page and verify the page'
Page.nav(ProfilePage).navProfilePage()
									  .verifyProfilePagePresent()
									  .clickChangePasswordLink()

'Verify UI Change Password Popup'
Page.nav(ChangePasswordPopup).verifyUIChangePasswordPopup().clickCancelBtn()

'Open Delete profile popup'
Page.nav(ProfilePage).clickDeleteProfile()

'Verify UI Delete profile Popup'
Page.nav(DeleteProfilePopup).verifyUIDeleteAccountPopup().clickCancelBtn()

'Go to Profile page and click Go to Welcome Page'
Page.nav(ProfilePage).clickGoToWelcomePage().sleepALittleTime()

'Verify Welcome page present'
Page.nav(WelcomePage).verifyWelcomePagePresent()


