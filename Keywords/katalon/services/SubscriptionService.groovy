package katalon.services

import internal.GlobalVariable
import katalon.fw.lib.BaseService

public class SubscriptionService extends BaseService<SubscriptionService> {
	static String subscriptionUrl = GlobalVariable.myApi+"v1/subscriptions/checkout-testops-platform"

	String accountId;
	String planId;
	String number;
	String recurlySubscriptionUuid;


	public SubscriptionService createNewSubscription(Number accountId,String planId,Number number) {
		def body = [
			[ "organizationId": accountId,"planId": planId, "number": number]
		]
		initRequestObject()
				.setUrl(subscriptionUrl)
				.setBearerAuthorizationHeader()
				.setJsonContentTypeHeader()
				.setPayLoad(parseObjectToString(body))
				.sendPostRequest()
				.verifyStatusCode(200)
		this.recurlySubscriptionUuid = parseResponseBodyToJsonObject().data[0].recurlySubscriptionUuid
		println this.recurlySubscriptionUuid
		return this
	}

	public SubscriptionService upgradeSubscription(Number accountId,String planId,Number number) {
		def numberupgrade = number + 1
		def body = [
			[ "organizationId": accountId,"planId": planId, "number": numberupgrade, "recurlySubscriptionUuid": this.recurlySubscriptionUuid]
		]

		initRequestObject()
				.setUrl(subscriptionUrl)
				.setBearerAuthorizationHeader()
				.setJsonContentTypeHeader()
				.setPayLoad(parseObjectToString(body))
				.sendPostRequest()
				.verifyStatusCode(200)
		return this
	}
}
