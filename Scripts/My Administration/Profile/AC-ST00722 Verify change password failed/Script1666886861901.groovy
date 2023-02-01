import katalon.common.MySignInPage
import katalon.fw.lib.Page
import katalon.my.account.ChangePasswordPopup
import katalon.my.account.ProfilePage

'1. User sign in to administration page'
Page.nav(MySignInPage).enterCredential(user_name, password).clickSignIn().verifySuccessfullySignIn()
'2. Go to Profile page and click Change Password hyperlink'
Page.nav(ProfilePage).navProfilePage().clickChangePasswordLink()
'3.User starts inputing general information [current password, new password, confirm password] on Change Password pop up'
Page.nav(ChangePasswordPopup)
					.inputCurrentPassword(current_password)
					.inputNewPassword(new_password)
					.inputConfirmPassword(confirm_password)
					.clickChangePasswordBtn()
switch(status_expectedResult.toString())
{
	case 'FAILED_ETP':
	'4. Verify change failed empty all three field'
	Page.nav(ChangePasswordPopup).verifyEmptyChangePasswordFailedEmptyThreeField()
	break
	
	case 'FAILED_ECP':
	'5. Verify change failed empty current password field'
	//Page.nav(ChangePasswordPopup).verifyEmptyChangePasswordFailedEmptyCurrentPasswordField()
	Page.nav(ChangePasswordPopup).verifyEmptyChangePasswordFailedEmptyThreeField()
	break
	
	case 'FAILED_ENP':
	'6. Verify change failed empty new password field'
	Page.nav(ChangePasswordPopup).verifyEmptyChangePasswordFailedEmptyNewPasswordField()
	break
	
	case 'FAILED_ECO':
	'7. Verify change failed empty confirm password field'
	Page.nav(ChangePasswordPopup).verifyEmptyChangePasswordFailedEmptyConfirmPasswordField()
	break
	
	case 'FAILED_ICP':
	'8. Verify change failed incorrect password field'
	Page.nav(ChangePasswordPopup).verifyChangePasswordFailedIncorrectPassword()
	break
	
	case 'FAILED_DNM':
	'9. Verify change failed password do not match'
	Page.nav(ChangePasswordPopup).verifyChangePasswordFailedPasswordDoNotMatch()
	break
	
	case 'FAILED_PMB':
	'10. Verify change failed not contain at least 1 upper case, 1 lower case, 1 number, 1 character'
	Page.nav(ChangePasswordPopup).verifyChangePasswordFailedWrongRulesPassword()
	break
	
	case 'FAILED_NMM':
	'11. Verify change failed password input more than 255 characters'
	Page.nav(ChangePasswordPopup).verifyChangePasswordFailedInputMoreThan255Chars()
	break
}