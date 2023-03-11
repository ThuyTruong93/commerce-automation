import katalon.fw.lib.Page
import katalon.services.SubscriptionService

accountId = accountId.toString().toLong()
quantity = quantity.toString().toLong()

Page.nav(SubscriptionService).getListActiveSubscription(accountId)
							 .terminateListSubscription()

Page.nav(SubscriptionService).createNewSubscriptionRecurly(accountId, planId, quantity)

						 
//Page.nav(SubscriptionService).updateNextBillingDate(next_bill_date, subscriptionBody.uuid)
//							 .markPaidInvoiceSubscriptionByInvoiceId(subscriptionBody.active_invoice_id)