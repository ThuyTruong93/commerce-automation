package katalon.services

import internal.GlobalVariable
import katalon.fw.lib.BaseService
import katalon.model.Subscription

public class SubscriptionService extends BaseService<SubscriptionService> {
	static String subscriptionUrl = GlobalVariable.myApi+"v1/subscriptions/checkout-testops-platform"
	static String cancelSubscriptionUrl = GlobalVariable.myApi+"v1/subscriptions/cancel"
	static String reactivateSubscriptionUrl = GlobalVariable.myApi+"v1/subscriptions/reactivate"

	String accountId;
	String planId;
	String number;

	//respond subscription
	Number id;
	Number createdAt;
	Number updatedAt;
	boolean archived
	String recurlySubscriptionId
	String recurlySubscriptionUuid;
	boolean unpaid
	String status
	Number stripeCustomerId
	Number testOpsStripeCustomerId
	Number enterprisePlanId
	String recurlyInvoiceNumber
	Number nextBillingDate
	Number canceledAt

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
		this.id = parseResponseBodyToJsonObject().data[0].id
		this.createdAt = parseResponseBodyToJsonObject().data[0].createdAt
		this.updatedAt = parseResponseBodyToJsonObject().data[0].updatedAt
		this.archived = parseResponseBodyToJsonObject().data[0].archived
		this.recurlySubscriptionId = parseResponseBodyToJsonObject().data[0].recurlySubscriptionId
		this.recurlySubscriptionUuid = parseResponseBodyToJsonObject().data[0].recurlySubscriptionUuid
		this.unpaid = parseResponseBodyToJsonObject().data[0].unpaid
		this.status = parseResponseBodyToJsonObject().data[0].status
		this.stripeCustomerId = parseResponseBodyToJsonObject().data[0].stripeCustomerId
		this.testOpsStripeCustomerId = parseResponseBodyToJsonObject().data[0].testOpsStripeCustomerId
		this.enterprisePlanId = parseResponseBodyToJsonObject().data[0].enterprisePlanId
		this.recurlyInvoiceNumber = parseResponseBodyToJsonObject().data[0].recurlyInvoiceNumber
		this.nextBillingDate = parseResponseBodyToJsonObject().data[0].nextBillingDate
		this.canceledAt = parseResponseBodyToJsonObject().data[0].canceledAt
		println "Id: " + this.id
		println this.createdAt
		println this.updatedAt
		println this.archived
		println this.recurlySubscriptionId
		println this.recurlySubscriptionUuid
		println this.unpaid
		println this.status
		println this.stripeCustomerId
		println this.testOpsStripeCustomerId
		println this.enterprisePlanId
		println this.recurlyInvoiceNumber
		println this.nextBillingDate
		println this.canceledAt
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

		this.id = parseResponseBodyToJsonObject().data[0].id
		this.createdAt = parseResponseBodyToJsonObject().data[0].createdAt
		this.updatedAt = parseResponseBodyToJsonObject().data[0].updatedAt
		this.archived = parseResponseBodyToJsonObject().data[0].archived
		this.recurlySubscriptionId = parseResponseBodyToJsonObject().data[0].recurlySubscriptionId
		this.recurlySubscriptionUuid = parseResponseBodyToJsonObject().data[0].recurlySubscriptionUuid
		this.unpaid = parseResponseBodyToJsonObject().data[0].unpaid
		this.status = parseResponseBodyToJsonObject().data[0].status
		this.stripeCustomerId = parseResponseBodyToJsonObject().data[0].stripeCustomerId
		this.testOpsStripeCustomerId = parseResponseBodyToJsonObject().data[0].testOpsStripeCustomerId
		this.enterprisePlanId = parseResponseBodyToJsonObject().data[0].enterprisePlanId
		this.recurlyInvoiceNumber = parseResponseBodyToJsonObject().data[0].recurlyInvoiceNumber
		this.nextBillingDate = parseResponseBodyToJsonObject().data[0].nextBillingDate
		this.canceledAt = parseResponseBodyToJsonObject().data[0].canceledAt
		return this
	}

	public SubscriptionService cancelSubscription() {
		Subscription body = new Subscription(this.id, this.createdAt, this.updatedAt, this.archived, this.recurlySubscriptionId,
			 this.recurlySubscriptionUuid, this.unpaid, this.status, this.stripeCustomerId, this.testOpsStripeCustomerId,
			  this.enterprisePlanId, this.recurlyInvoiceNumber, this.nextBillingDate, this.canceledAt)

		initRequestObject()
				.setUrl(cancelSubscriptionUrl)
				.setBearerAuthorizationHeader()
				.setJsonContentTypeHeader()
				.setPayLoad(parseObjectToString(body))
				.sendPutRequest()
				.verifyStatusCode(200)

		this.id = parseResponseBodyToJsonObject().data.id
		this.createdAt = parseResponseBodyToJsonObject().data.createdAt
		this.updatedAt = parseResponseBodyToJsonObject().data.updatedAt
		this.archived = parseResponseBodyToJsonObject().data.archived
		this.recurlySubscriptionId = parseResponseBodyToJsonObject().data.recurlySubscriptionId
		this.recurlySubscriptionUuid = parseResponseBodyToJsonObject().data.recurlySubscriptionUuid
		this.unpaid = parseResponseBodyToJsonObject().data.unpaid
		this.status = parseResponseBodyToJsonObject().data.status
		this.stripeCustomerId = parseResponseBodyToJsonObject().data.stripeCustomerId
		this.testOpsStripeCustomerId = parseResponseBodyToJsonObject().data.testOpsStripeCustomerId
		this.enterprisePlanId = parseResponseBodyToJsonObject().data.enterprisePlanId
		this.recurlyInvoiceNumber = parseResponseBodyToJsonObject().data.recurlyInvoiceNumber
		this.nextBillingDate = parseResponseBodyToJsonObject().data.nextBillingDate
		this.canceledAt = parseResponseBodyToJsonObject().data.canceledAt
		return this
	}
	
	public SubscriptionService reactivateSubscription() {
		Subscription body = new Subscription(this.id, this.createdAt, this.updatedAt, this.archived, this.recurlySubscriptionId,
			 this.recurlySubscriptionUuid, this.unpaid, this.status, this.stripeCustomerId, this.testOpsStripeCustomerId,
			  this.enterprisePlanId, this.recurlyInvoiceNumber, this.nextBillingDate, this.canceledAt)

		initRequestObject()
				.setUrl(reactivateSubscriptionUrl)
				.setBearerAuthorizationHeader()
				.setJsonContentTypeHeader()
				.setPayLoad(parseObjectToString(body))
				.sendPutRequest()
				.verifyStatusCode(200)

		this.id = parseResponseBodyToJsonObject().data.id
		this.createdAt = parseResponseBodyToJsonObject().data.createdAt
		this.updatedAt = parseResponseBodyToJsonObject().data.updatedAt
		this.archived = parseResponseBodyToJsonObject().data.archived
		this.recurlySubscriptionId = parseResponseBodyToJsonObject().data.recurlySubscriptionId
		this.recurlySubscriptionUuid = parseResponseBodyToJsonObject().data.recurlySubscriptionUuid
		this.unpaid = parseResponseBodyToJsonObject().data.unpaid
		this.status = parseResponseBodyToJsonObject().data.status
		this.stripeCustomerId = parseResponseBodyToJsonObject().data.stripeCustomerId
		this.testOpsStripeCustomerId = parseResponseBodyToJsonObject().data.testOpsStripeCustomerId
		this.enterprisePlanId = parseResponseBodyToJsonObject().data.enterprisePlanId
		this.recurlyInvoiceNumber = parseResponseBodyToJsonObject().data.recurlyInvoiceNumber
		this.nextBillingDate = parseResponseBodyToJsonObject().data.nextBillingDate
		this.canceledAt = parseResponseBodyToJsonObject().data.canceledAt
		return this
	}
}
