package external.services
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.recurly.v3.Client
import com.recurly.v3.Pager
import com.recurly.v3.QueryParams
import com.recurly.v3.resources.Invoice
import com.recurly.v3.resources.Plan
import com.recurly.v3.resources.Subscription

import internal.GlobalVariable
import katalon.enums.Subscriptions
import katalon.fw.lib.BaseService
import katalon.model.WSRequest


public class RecurlyService extends BaseService<RecurlyService> {

	public static final Client client  = new Client(GlobalVariable.apiKeyRecurly)

	public RecurlyService createPreviewSubscriptionChange(String subscriptionId,Subscriptions sub, Number quantity) {
		String url = "$GlobalVariable.recurlyUrl"+"subscriptions/uuid-${subscriptionId}/change/preview"
		String token = "${GlobalVariable.apiKeyRecurly}:".bytes.encodeBase64().toString()

		Map<String, String> params = ['subscription_id': "uuid-${subscriptionId}"]
		Map<String, String> headers = ['Authorization': "Basic ${token}", 'Accept': 'application/vnd.recurly.v2021-02-25']
		def body = ["plan_code": sub.getCode(), "quantity": quantity]
		initRequestObject().create(new WSRequest().with {
			it.url = url
			it.params = params
			it.headers = headers
			it.payload = body
			it
		})
		return this
	}

	public BigDecimal getPlanPrice(Subscriptions sub){
		Plan plan = client.getPlan("code-${sub.getCode()}")
		BigDecimal number = plan.getCurrencies().get(0).getUnitAmount()
		return number
	}

	public Pager<Subscription> getAccountSubscriptions(String accountId) {
		QueryParams params = new QueryParams()
		params.setState("active")
		Pager<Subscription> subscriptions = client.listAccountSubscriptions("code-organization-${accountId}", params)
		return subscriptions
	}

	public RecurlyService terminateSubscription(String subscriptionId) {
		QueryParams params = new QueryParams()
		params.setRefund("none")
		client.terminateSubscription(subscriptionId, params);
		return this
	}

	public RecurlyService terminateAllAccountSubscriptions(String accountId) {
		Pager<Subscription> subscriptions = getAccountSubscriptions(accountId)
		QueryParams params = new QueryParams()
		params.setRefund("none")
		for (Subscription subscription : subscriptions) {
			client.terminateSubscription(subscription.getId(), params);
		}
		return this
	}

	public RecurlyService markInvoiceSuccessfulSubscription(String invoiceId) {
		client.markInvoiceSuccessful("number-$invoiceId");
		return this
	}

	public RecurlyService cancelSubscription(String subscriptionId) {
		client.cancelSubscription(subscriptionId);
		return this
	}

	public RecurlyService reactivateSubscription(String subscriptionId) {
		client.cancelSubscription(subscriptionId);
		return this
	}

	public String getStateInvoice(String invoiceId) {
		Invoice invoice = client.getInvoice("number-${invoiceId}");
		String state = invoice.getState()
		return state
	}

	public String getInvoiceNumber(String invoiceId) {
		Invoice invoice = client.getInvoice("number-${invoiceId}");
		return invoice.getNumber()
	}

	public RecurlyService verifyStateInvoice(String invoiceId, String expectedResult) {
		WebUI.delay(10)
		String state = getStateInvoice(invoiceId)
		WebUI.verifyEqual(state, expectedResult)
		return this
	}

	public RecurlyService waitUntilChangeStateInvoice(String invoiceId,String actualStatus,String expectedStatus) {
		def count = 0
		while ((count < 5) && (actualStatus != expectedStatus)) {
			WebUI.delay(GlobalVariable.smallSleepTime)
			actualStatus = getStateInvoice(invoiceId)
			count++
		}
		return this
	}

	public RecurlyService waitUntilChangeStateListInvoice(String accountId,List<String> invoiceId,String expectedStatus) {
		def count = 0
		List<String> numberInvoices = getListInvoiceNumbers(accountId)
		String actualStatus = "pending"
		while (count < 5 && actualStatus != expectedStatus) {
			WebUI.delay(GlobalVariable.smallSleepTime)
			actualStatus = getStateInvoice(invoiceId[count])
			count++
		}
		return this
	}

	public Pager<Invoice> getListAccountInvoices(String accountId) {
		QueryParams params = new QueryParams()
		params.setOrder("desc")
		Pager<Invoice> invoices = client.listAccountInvoices("code-organization-${accountId}", params)
		return invoices
	}

	public List<String> getListInvoiceNumbers(String accountId) {
		Pager<Invoice> invoices = getListAccountInvoices(accountId)
		List<String> numbersInvoiceId = []
		for (Invoice invoice : invoices) {
			String invoiceNumber = invoice.getNumber()
			numbersInvoiceId.addAll(invoiceNumber)
		}
		return numbersInvoiceId
	}

	public RecurlyService verifyTotalNumbersOfInvoice(List<String> numbersInvoiceId, Number expectedResult) {
		Number count = numbersInvoiceId.size()
		WebUI.verifyEqual(count, expectedResult)
		return this
	}
}