import external.services.RecurlyService
import internal.GlobalVariable
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.services.SubscriptionService
import katalon.testops.services.LoginService


'Data Setup - Get user and account'
Credential user = Page.nav(Credential)
	.getCredentials()
	.inAccount("Free Platform")
	.withRole('Owner')
	.getFirst()
	
'Clean data'
Page.nav(RecurlyService).terminateAllAccountSubscriptions(user.accountId)

'User login to get token'
Page.nav(LoginService).loginWithEncryptedPwd(user.email, user.pwd).verifyStatusCode(200).getToken()

if(!token) {
	token = GlobalVariable.encodedToken
}

if(!accountId) {
	accountId = user.accountId.toFloat()
}

def body = [[ "organizationId": accountId,"planId": planId, "number": quantity.intValue()]]

'Verify create new subscription status'
Page.nav(SubscriptionService).initRequestObject()
				.setUrl("$GlobalVariable.myApi$GlobalVariable.version/subscriptions/checkout-testops-platform")
				.setPayLoad(body)
				.setJsonContentTypeHeader()
				.setBearerAuthorizationHeader(token)
				.sendPostRequest()
				.verifyStatusCode(status.intValue())