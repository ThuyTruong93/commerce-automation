import com.kms.katalon.util.CryptoUtil

import katalon.fw.lib.Page
import katalon.services.SubscriptionService
import katalon.testops.services.LoginService

accountId = accountId.toString().toLong()
quantity = quantity.toString().toLong()

String decryptedPwd =  CryptoUtil.decode(CryptoUtil.getDefault(password))

'User login to get token '
Page.nav(LoginService).login(email, decryptedPwd).verifyStatusCode(200).getToken()

Page.nav(SubscriptionService).getListActiveSubscription(accountId)
							 .terminateListSubscription()						 
							 
def subscriptionBody = Page.nav(SubscriptionService).createNewSubscription(accountId, planId, quantity).parseResponseBodyToJsonObject().data[0]

Page.nav(SubscriptionService).markPaidInvoiceSubscriptionByInvoiceNumber(subscriptionBody.recurlyInvoiceNumber)

def subscriptionBodyUpgrade = Page.nav(SubscriptionService)
									.upgradeSubscription(accountId, planId, quantity, subscriptionBody.recurlySubscriptionUuid)
									.parseResponseBodyToJsonObject().data[0]
									
Page.nav(SubscriptionService).markPaidInvoiceSubscriptionByInvoiceNumber(subscriptionBodyUpgrade.recurlyInvoiceNumber)
							.cancelSubscription(subscriptionBodyUpgrade)
							.reactivateSubscription(subscriptionBodyUpgrade)