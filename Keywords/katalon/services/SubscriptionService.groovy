package katalon.services

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import katalon.fw.lib.BaseService
import katalon.model.Subscription

public class SubscriptionService extends BaseService<SubscriptionService> {
	static String subscriptionUrl = "$GlobalVariable.myApi$GlobalVariable.version/subscriptions/checkout-testops-platform"
	static String cancelSubscriptionUrl = "$GlobalVariable.myApi$GlobalVariable.version/subscriptions/cancel"
	static String reactivateSubscriptionUrl = "$GlobalVariable.myApi$GlobalVariable.version/subscriptions/reactivate"
	static String markPaidInvoiceSubscriptionUrl = "$GlobalVariable.recurlyUrl"+"invoices/"
	static String terminateSubscriptionUrl = "$GlobalVariable.recurlyUrl"+"subscriptions/uuid-"
	static String listActiveSubscriptionUrl = "$GlobalVariable.recurlyUrl"+"accounts/code-organization-"
	static String subscriptionRecurlyUrl = "$GlobalVariable.recurlyUrl"+"subscriptions"
	static String subscriptionUpdateRecurlyUrl = "$GlobalVariable.recurlyUrl"+"subscriptions/uuid-"

	//	Number accountId;
	//	String planId;
	//	Number number;

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

	//respond array list subscription
    def uuidArray = []

	public SubscriptionService listActiveSubscription(Number accountId) {
		initRequestObject()
				.setUrl(listActiveSubscriptionUrl+accountId+"/subscriptions?state=active")
				.setBasicAuthorizationHeader("$GlobalVariable.apiKeyRecurly", "")
				.setJsonContentTypeHeader()
				.setAcceptHeader(GlobalVariable.acceptNameRecurly)
				.sendGetRequest()

		def lengthListActiveBody = parseResponseBodyToJsonObject().data.size()
		for (int i = 0;i<lengthListActiveBody;i++) {
			this.uuidArray << (parseResponseBodyToJsonObject().data[i].uuid)
		}
		println "uuidArray: $uuidArray"

		return this
	}

	public SubscriptionService createNewSubscription(Number accountId, String planId, Number quantity) {
		def body = [
			[ "organizationId": accountId,"planId": planId, "number": quantity]
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

	public SubscriptionService createListNewSubscription(Number accountId, String planId, Number quantity) {
		def dem =  planId.count(',')  + 1
		println dem
		for (int i = 0; i < dem; i++) {
			def planIdArray = planId.split(",").collect { it.trim() }
			def quantityArray = quantity.split(",").collect { it.trim() }
			println planIdArray
			println quantityArray
		
		def body = [
			[ "organizationId": accountId,"planId": planIdArray[i], "number": quantityArray[i]]
		]
		initRequestObject()
				.setUrl(subscriptionUrl)
				.setBearerAuthorizationHeader()
				.setJsonContentTypeHeader()
				.setPayLoad(parseObjectToString(body))
				.sendPostRequest()
				.verifyStatusCode(200)
		}
		return this	
	}


	public SubscriptionService markPaidInvoiceSubscriptionByInvoiceNumber(String invoiceNumber) {
		initRequestObject()
				.setUrl(markPaidInvoiceSubscriptionUrl+"number-$invoiceNumber"+"/mark_successful")
				.setBasicAuthorizationHeader("$GlobalVariable.apiKeyRecurly", "")
				.setJsonContentTypeHeader()
				.setContentLengthHeader('0')
				.setAcceptHeader(GlobalVariable.acceptNameRecurly)
				.sendPutRequest()
				.verifyStatusCode(200)
		return this
	}

	public SubscriptionService markPaidInvoiceSubscriptionByInvoiceId(String invoiceId) {
		initRequestObject()
				.setUrl(markPaidInvoiceSubscriptionUrl+"$invoiceId"+"/mark_successful")
				.setBasicAuthorizationHeader("$GlobalVariable.apiKeyRecurly", "")
				.setJsonContentTypeHeader()
				.setContentLengthHeader('0')
				.setAcceptHeader(GlobalVariable.acceptNameRecurly)
				.sendPutRequest()
				.verifyStatusCode(200)
		return this
	}

	public SubscriptionService upgradeSubscription(Number accountId,String planId,Number quantity, String recurlySubscriptionUuid) {
		def quantityUpgrade = quantity + 1
		def body = [
			[ "organizationId": accountId,"planId": planId, "number": quantityUpgrade, "recurlySubscriptionUuid": recurlySubscriptionUuid]
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

	public SubscriptionService cancelSubscription(Object body) {
		initRequestObject()
				.setUrl(cancelSubscriptionUrl)
				.setBearerAuthorizationHeader()
				.setJsonContentTypeHeader()
				.setPayLoad(parseObjectToString(body))
				.sendPutRequest()
				.verifyStatusCode(200)

		return this
	}

	public SubscriptionService reactivateSubscription(Object body) {
		initRequestObject()
				.setUrl(reactivateSubscriptionUrl)
				.setBearerAuthorizationHeader()
				.setJsonContentTypeHeader()
				.setPayLoad(parseObjectToString(body))
				.sendPutRequest()
				.verifyStatusCode(200)

		return this
	}
	public SubscriptionService terminateSubscription(String recurlySubscriptionUuid) {
		initRequestObject()
				.setUrl(terminateSubscriptionUrl+recurlySubscriptionUuid+"?refund=none&charge=true")
				.setBasicAuthorizationHeader("$GlobalVariable.apiKeyRecurly", "")
				.setJsonContentTypeHeader()
				.setAcceptHeader(GlobalVariable.acceptNameRecurly)
				.sendDeleteRequest()
				.verifyStatusCode(200)
		return this
	}

	public SubscriptionService terminateListSubscription() {

		for (int i = 0 ; i< uuidArray.size(); i++) {
			def uuid = uuidArray[i]
			println "uuid = $uuid"

			initRequestObject()
					.setUrl(terminateSubscriptionUrl+uuid+"?refund=none&charge=true")
					.setBasicAuthorizationHeader("$GlobalVariable.apiKeyRecurly", "")
					.setJsonContentTypeHeader()
					.setAcceptHeader(GlobalVariable.acceptNameRecurly)
					.sendDeleteRequest()
		}
		WebUI.delay(5)
		return this
	}

	public updateSubscriptionJson(Object result) {
		this.id = result.id
		this.createdAt = result.createdAt
		this.updatedAt = result.updatedAt
		this.archived = result.archived
		this.recurlySubscriptionId = result.recurlySubscriptionId
		this.recurlySubscriptionUuid = result.recurlySubscriptionUuid
		this.unpaid = result.unpaid
		this.status = result.status
		this.stripeCustomerId = result.stripeCustomerId
		this.testOpsStripeCustomerId = result.testOpsStripeCustomerId
		this.enterprisePlanId = result.enterprisePlanId
		this.recurlyInvoiceNumber = result.recurlyInvoiceNumber
		this.nextBillingDate = result.nextBillingDate
		this.canceledAt = result.canceledAt
	}

	//Subscription on Recurly

	public SubscriptionService createNewSubscriptionRecurly(Number accountId, String planId, Number quantity) {
		def body = ["plan_code": planId, "currency": "USD","account": ["code": "organization-$accountId"], "collection_method": "manual",
			"quantity": quantity]

		initRequestObject()
				.setUrl(subscriptionRecurlyUrl)
				.setBasicAuthorizationHeader("$GlobalVariable.apiKeyRecurly", "")
				.setJsonContentTypeHeader()
				.setAcceptHeader(GlobalVariable.acceptNameRecurly)
				.setPayLoad(parseObjectToString(body))
				.sendPostRequest()
				.verifyStatusCode(201)

		return this
	}

	public SubscriptionService updateNextBillingDate(String nextBillingDate, String recurlySubscriptionUuid) {
		def body = ["next_bill_date": nextBillingDate]
		
		initRequestObject()
				.setUrl(subscriptionUpdateRecurlyUrl+recurlySubscriptionUuid)
				.setBasicAuthorizationHeader("$GlobalVariable.apiKeyRecurly", "")
				.setJsonContentTypeHeader()
				.setAcceptHeader(GlobalVariable.acceptNameRecurly)
				.setPayLoad(parseObjectToString(body))
				.sendPutRequest()
				.verifyStatusCode(200)

		return this
	}
	
	public SubscriptionService updateNextBillingDateList(String nextBillingDate) {
		def body = ["next_bill_date": nextBillingDate]
		
		//handle case array uuid
		for (int i = 0;i< uuidArray.size();i++) {
				def recurlySubscriptionUuid = uuidArray[i]
				println "$recurlySubscriptionUuid"

		initRequestObject()
				.setUrl(subscriptionUpdateRecurlyUrl+recurlySubscriptionUuid)
				.setBasicAuthorizationHeader("$GlobalVariable.apiKeyRecurly", "")
				.setJsonContentTypeHeader()
				.setAcceptHeader(GlobalVariable.acceptNameRecurly)
				.setPayLoad(parseObjectToString(body))
				.sendPutRequest()
				.verifyStatusCode(200)
		}

		return this
	}		

}
