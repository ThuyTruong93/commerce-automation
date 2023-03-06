import com.kms.katalon.util.CryptoUtil

import internal.GlobalVariable
import katalon.fw.lib.Page
import katalon.services.SubscriptionService
import katalon.testops.services.LoginService


def subscriptionBody = Page.nav(SubscriptionService).createNewSubscriptionRecurly(accountId, planId, quantity).parseResponseBodyToJsonObject()
		println "subscription body: $subscriptionBody"	
		println subscriptionBody.uuid		
						 
Page.nav(SubscriptionService).updateNextBillingDate(nextBillingDate, subscriptionBody.uuid)
							 .markPaidInvoiceSubscriptionByInvoiceId(subscriptionBody.active_invoice_id)