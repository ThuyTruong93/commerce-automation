import katalon.common.MySignInPage
import katalon.fw.lib.Page
import katalon.my.account.ProfilePage
import katalon.my.account.ChangePasswordPopup

'User sign in to administration page'
Page.nav(MySignInPage).enterCredential(user_name,password).clickSignIn().verifySuccessfullySignIn()

'Go to Profile page and click Change Password hyperlink'
Page.nav(ProfilePage).navProfilePage().clickChangePasswordLink()

'User starts inputing general information [current password, new password, confirm password] on Change Password pop up'
Page.nav(ChangePasswordPopup)
					.inputCurrentPassword(current_password)
					.inputNewPassword(new_password)
					.inputConfirmPassword(confirm_password)
					.clickChangePasswordBtn()
					
'Verify change password successfully'
Page.nav(ChangePasswordPopup).verifySuccessfullyChangePassword().sleepALittleTime()

'User sign in to administration page'
Page.nav(MySignInPage).enterCredential(user_name,new_password).clickSignIn().verifySuccessfullySignIn()