package katalon.services

import internal.GlobalVariable
import katalon.fw.lib.BaseService

public class SubscriptionService extends BaseService<SubscriptionService> {
	static String subscriptionUrl = GlobalVariable.myApi+"v1/subscriptions/checkout-testops-platform"
	public String recurlySubscriptionUuid

	String accountId;
	String planId;
	String number;


	public SubscriptionService createNewSubscription(Number accountId,String planId,Number number) {
		def body = [
			[ "organizationId": accountId,"planId": planId, "number": number]
		]
		setUrl(subscriptionUrl)
				.setBearerAuthorizationHeader()
				.setJsonContentTypeHeader()
				.setPayLoad(parseObjectToString(body))
				.sendPostRequest()
				.verifyStatusCode(200)
		return this
	}
}
