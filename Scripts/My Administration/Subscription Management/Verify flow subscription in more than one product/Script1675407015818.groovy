import com.kms.katalon.util.CryptoUtil

import katalon.fw.lib.Page
import katalon.services.SubscriptionService
import katalon.testops.services.LoginService

accountId = accountId.toString().toLong()

String decryptedPwd =  CryptoUtil.decode(CryptoUtil.getDefault(password))

'User login to get token '
Page.nav(LoginService).login(email, decryptedPwd).verifyStatusCode(200).getToken()

Page.nav(SubscriptionService).listActiveSubscription(accountId)
							 .terminateListSubscription()						 
							 
Page.nav(SubscriptionService).createListNewSubscription(accountId, planId, quantity)

//Page.nav(SubscriptionService).markPaidInvoiceSubscriptionByInvoiceNumber(subscriptionBody.recurlyInvoiceNumber)
//
//def subscriptionBodyUpgrade = Page.nav(SubscriptionService)
//									.upgradeSubscription(accountId, planId, quantity, subscriptionBody.recurlySubscriptionUuid)
//									.parseResponseBodyToJsonObject().data[0]
//									
//Page.nav(SubscriptionService).markPaidInvoiceSubscriptionByInvoiceNumber(subscriptionBodyUpgrade.recurlyInvoiceNumber)
//							.cancelSubscription(subscriptionBodyUpgrade)
//							.reactivateSubscription(subscriptionBodyUpgrade)