
import com.kms.katalon.util.CryptoUtil

import internal.GlobalVariable
import katalon.fw.lib.Page
import katalon.services.SubscriptionService
import katalon.testops.services.LoginService

String decryptedPwd =  CryptoUtil.decode(CryptoUtil.getDefault(password))

'User login to get token '
Page.nav(LoginService).login(email, decryptedPwd).verifyStatusCode(200).getToken()

Page.nav(SubscriptionService).initRequestObject()
							.createNewSubscription(accountId, planId, number)
							