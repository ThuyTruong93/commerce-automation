import katalon.common.MySignInPage
import katalon.common.WelcomePage
import katalon.common.AdminHeaderBar
import katalon.my.account.AccountHomePage
import katalon.my.paymentmethod.PaymentMethodPage
import katalon.fw.lib.Page

'User signs in to administration page'
Page.nav(MySignInPage).enterCredential(username, password).clickSignIn().verifySuccessfullySignIn()

'User selects the account'
Page.nav(WelcomePage).selectAccount(accountId)

'Click Settings on Header Navigator > Go to Payment Method'
Page.nav(AdminHeaderBar).clickSettingIcon().clickPaymentMethod().sleepSomeTime()

'Add new payment method and verify element'
Page.nav(PaymentMethodPage).inputCardNumber(card_number).sleepSomeTime()
//							.inputExpiration(expiration)
//							.inputCVC(cvc)
//							.inputCardHolder(card_holder)
//							.clickSavePayment()
//							.verifyAppearElementAfterAddPayment()
//							.clickDeletePaymentMethod().sleepALittleTime()
//							.clickUpdateBillingInformation()
//							.verifyAppearElementAfterClickUpdateBillingInfor()
//							.clickCancelBillingInformation()