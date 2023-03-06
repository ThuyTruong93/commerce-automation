import com.kms.katalon.util.CryptoUtil

import katalon.fw.lib.Page
import katalon.services.SubscriptionService
import katalon.testops.services.LoginService

String decryptedPwd =  CryptoUtil.decode(CryptoUtil.getDefault(password))

'User login to get token '
Page.nav(LoginService).login(email, decryptedPwd).verifyStatusCode(200).getToken()

Page.nav(SubscriptionService).listActiveSubscription(accountId)
							 .terminateListSubscription()						 
							 
def subscriptionBody = Page.nav(SubscriptionService).createNewSubscription(accountId, planId, quantity).parseResponseBodyToJsonObject().data[0]

//Page.nav(SubscriptionService).markPaidInvoiceSubscriptionByInvoiceNumber(subscriptionBody.recurlyInvoiceNumber)