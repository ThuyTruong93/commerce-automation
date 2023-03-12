import com.kms.katalon.util.CryptoUtil

import katalon.fw.lib.Page
import katalon.services.SubscriptionService
import katalon.testops.services.LoginService
import katalon.db.katone.BillingStudioTable

accountId = accountId.toString().toLong()
quantity = quantity.toString().toLong()

String decryptedPwd =  CryptoUtil.decode(CryptoUtil.getDefault(password))

'User login to get token '
Page.nav(LoginService).login(email, decryptedPwd).verifyStatusCode(200).getToken()

Page.nav(SubscriptionService).getListActiveSubscription(accountId)
							 .terminateListSubscription()
							 .createNewSubscription(accountId, planId, quantity)					 
							 
Page.nav(BillingStudioTable).selectStudioSubscription(accountId)
										