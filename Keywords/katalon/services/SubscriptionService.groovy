package katalon.services
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import groovy.json.JsonSlurper
import internal.GlobalVariable
import katalon.fw.lib.BaseService
import katalon.utility.CommonUtility

public class SubscriptionService extends BaseService<SubscriptionService> {
	static String subscriptionUrl = "$GlobalVariable.myApi$GlobalVariable.version/subscriptions/checkout-testops-platform"
	static String cancelSubscriptionUrl = "$GlobalVariable.myApi$GlobalVariable.version/subscriptions/cancel"
	static String reactivateSubscriptionUrl = "$GlobalVariable.myApi$GlobalVariable.version/subscriptions/reactivate"
	static String markPaidInvoiceSubscriptionUrl = "$GlobalVariable.recurlyUrl"+"invoices/"
	static String terminateSubscriptionUrl = "$GlobalVariable.recurlyUrl"+"subscriptions/uuid-"
	static String listActiveSubscriptionUrl = "$GlobalVariable.recurlyUrl"+"accounts/code-organization-"
	static String subscriptionRecurlyUrl = "$GlobalVariable.recurlyUrl"+"subscriptions"
	static String subscriptionUpdateRecurlyUrl = "$GlobalVariable.recurlyUrl"+"subscriptions/uuid-"
	static String updateQuotaOrgLevelUrl = "$GlobalVariable.adminApiUrl/public/quota"

	//	Number accountId;
	//	String planId;
	//	Number number;

	//response subscription
	Number id;
	Number createdAt
	Number updatedAt
	boolean archived
	String recurlySubscriptionId
	String recurlySubscriptionUuid
	boolean unpaid
	String status
	Number stripeCustomerId
	Number testOpsStripeCustomerId
	Number enterprisePlanId
	String recurlyInvoiceNumber
	Number nextBillingDate
	Number canceledAt

	//respond array list subscription
	List<String> uuidArray = []

	public SubscriptionService getListActiveSubscription(Number accountId) {
		initRequestObject()
				.setUrl(listActiveSubscriptionUrl+accountId+"/subscriptions?state=active")
				.setBasicAuthorizationHeader("$GlobalVariable.apiKeyRecurly", "")
				.setJsonContentTypeHeader()
				.setAcceptHeader(GlobalVariable.acceptNameRecurly)
				.sendGetRequest()

		def lengthListActiveBody = parseResponseBodyToJsonObject().data.size()
		println "lengthListActiveBody: $lengthListActiveBody"
		for (int i = 0;i < lengthListActiveBody; i++) {
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

		reponseSubscriptionUIAPIJson(parseResponseBodyToJsonObject())

		return this
	}

	public SubscriptionService createListNewSubscriptionByUIAPI(Number accountId, String planId, String quantity) {
		def dem =  planId.count(',')+1
		println dem
		for (int i = 0; i < dem; i++) {
			if(dem != 0) {
				def planIdArray = planId.split(",").collect { it.trim() }
				//def quantityArray = quantity.split(",").collect { it.trim().toString().toLong() }.findAll { it != null }
				def quantityArray = CommonUtility.convertString2ListString (quantity, ",")

				println accountId
				println planIdArray[i]
				println quantityArray[i]

				def body = [
					[ "organizationId": accountId,"planId": planIdArray[i], "number": quantityArray[i]]
				]
				initRequestObject()
						//.sleepMiddleTime()
						.setUrl(subscriptionUrl)
						.setBearerAuthorizationHeader()
						.setJsonContentTypeHeader()
						.setPayLoad(parseObjectToString(body))
						.sendPostRequest()
						.verifyStatusCode(200)

				markPaidInvoiceSubscriptionByInvoiceNumber(parseResponseBodyToJsonObject().data.recurlyInvoiceNumber)

			}
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
		uuidArray.clear()
		return this
	}

	public reponseSubscriptionUIAPIJson(Object result) {
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

		def body = []

		switch(getSubscriptionName(planId).toString())
		{
			case 'kre_devops':
			case 'kre_node-locked':
			case 'kre_floating':
			case 'kse_node-locked':
			case 'kse_floating':
			case 'kse_per-user':
				body = ["plan_code": planId, "currency": "USD","account": ["code": "organization-$accountId"], "collection_method": "manual",
					"quantity": quantity]
				postCreateNewSubscriptionRecurly(body)
				break

			case 'testops_platform':
				body = ["plan_code": planId, "currency": "USD","account": ["code": "organization-$accountId"], "collection_method": "manual",
					"quantity": 1,"add_ons": [
						["code": "testops_platform_test_results","quantity": quantity]
					]]
				println "body in switch cases: $body"
				postCreateNewSubscriptionRecurly(body)
				break

			case 'testcloud_session_web':
				body = ["plan_code": planId, "currency": "USD","account": ["code": "organization-$accountId"], "collection_method": "manual",
					"quantity": 1,"add_ons": [
						["code": "testcloud_web_sessions","quantity": quantity]
					]]
				println "body in switch cases: $body"
				postCreateNewSubscriptionRecurly(body)
				break

			case 'visual_testing_pro':
				body = ["plan_code": planId, "currency": "USD","account": ["code": "organization-$accountId"], "collection_method": "manual",
					"quantity": 1,"add_ons": [
						["code": "visual_testing_checkpoints","quantity": quantity]
					]]
				println "body in switch cases: $body"
				postCreateNewSubscriptionRecurly(body)
				break
		}
		return this
	}

	public postCreateNewSubscriptionRecurly(Object body){
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

	public SubscriptionService updateNextBillingDate(String next_bill_date, String recurlySubscriptionUuid) {
		def body = ["next_bill_date": next_bill_date]

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

	public SubscriptionService updateNextBillingDateList(String next_bill_date) {
		def body = ["next_bill_date": next_bill_date]

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

	public SubscriptionService verifyCreateSubscriptionSuccessfully(Number accountId, Object dataQuery) {
		def jsonString = parseObjectToString(dataQuery)
		def jsonSlurper = new JsonSlurper()
		def data = jsonSlurper.parseText(jsonString)
		println "data after parse: $data"

		def accountidDB = data.accountid
		def expirydateDB = data.expirydate
		def featureDB = data.feature
		def recurlysubscriptionidDB = data.recurlysubscriptionid
		def statusDB = data.status

		def arrayQuery = [
			accountidDB,
			expirydateDB,
			featureDB,
			recurlysubscriptionidDB,
			statusDB
		]

		println "data accountid after parse: $accountidDB"
		WebUI.verifyEqual(arrayQuery, [
			accountId,
			nextBillingDate,
			"KSE_PER_USER",
			recurlySubscriptionId,
			status
		])
	}

	//	public SubscriptionService updateQuotaOldProduct(String feature, Number quota, String expiryDate, Number machineQuotaFactor) {
	//		def body = [ "feature": "$feature", "quota": quota, "expiry_date": "$expiryDate",
	//					"machine_quota_factor": machineQuotaFactor]
	//
	//		initRequestObject()
	//				.setUrl(subscriptionUpdateRecurlyUrl+recurlySubscriptionUuid)
	//				.setBasicAuthorizationHeader("$GlobalVariable.apiKeyRecurly", "")
	//				.setJsonContentTypeHeader()
	//				.setAcceptHeader(GlobalVariable.acceptNameRecurly)
	//				.setPayLoad(parseObjectToString(body))
	//				.sendPutRequest()
	//				.verifyStatusCode(200)
	//
	//		return this
	//	}

	public SubscriptionService setFeatureParam(String feature) {
		List<TestObjectProperty> parameters = new ArrayList<>()
		parameters.add(new TestObjectProperty('feature', ConditionType.EQUALS, feature))
		setParam(parameters)
		return this
	}

	public getSubscriptionName(String planId) {
		def lastIndex = planId.lastIndexOf('_')
		def result = planId.substring(0, lastIndex)
		println result
		return result
	}

	public sleepALittleTime() {
		WebUI.delay(GlobalVariable.smallSleepTime)
		return this
	}

	public sleepMiddleTime() {
		WebUI.delay(10)
		return this
	}

}