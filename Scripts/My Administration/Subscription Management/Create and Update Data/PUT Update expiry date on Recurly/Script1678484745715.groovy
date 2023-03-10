import com.kms.katalon.util.CryptoUtil

import internal.GlobalVariable
import katalon.fw.lib.Page
import katalon.services.SubscriptionService
import katalon.testops.services.LoginService

accountId = accountId.toString().toLong()

Page.nav(SubscriptionService).getListActiveSubscription(accountId)
							 .updateNextBillingDateList(next_bill_date)