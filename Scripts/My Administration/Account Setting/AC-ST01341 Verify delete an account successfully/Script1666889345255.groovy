import katalon.common.MySignInPage
import katalon.common.WelcomePage
import katalon.fw.lib.Page
import katalon.my.account.AccountHomePage
import katalon.my.account.AccountSettingPage
import katalon.my.account.CreateNewAccountPopUp
import katalon.my.account.DeleteAccountPopUp


'1. User signs in to administration page'
Page.nav(MySignInPage).enterCredential().clickSignIn().verifySuccessfullySignIn()

'2. User lands on Welcome page > click + button to create a new account'
Page.nav(WelcomePage).clickCreateANewAccount()

'3. User starts inputing general information on Create new account pop up'
Page.nav(CreateNewAccountPopUp)
						.inputAccountName(account_name)
						.inputOrganizationtName(account_name)
						.inputProjectName(account_name)
						.clickCreate().sleepALittleTime()

'4. User goes to Account Homepage > click <Get the Account ID> link'
Page.nav(AccountHomePage).clickGetTheAccountID()

'5. User goes to Account Setting page > click Delete button'
accountId = Page.nav(AccountSettingPage).getAccountId()
Page.nav(AccountSettingPage).clickDeleteAccount()

'6. User inputs correct Account Name on Delete Account popup > go to Welcome page'
Page.nav(DeleteAccountPopUp).inputAccountName().clickDeleteThisAccount().sleepALittleTime()

'7. Verify the Account is deleted succesfully'
Page.nav(WelcomePage).verifyAccountIsDeleted(accountId)