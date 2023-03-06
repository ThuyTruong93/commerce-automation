package katalon.model

public class Subscription {
	Number id
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

	public Subscription(Number id, Number createdAt, Number updatedAt, boolean archived, String recurlySubscriptionId, String recurlySubscriptionUuid, boolean unpaid, String status, Number stripeCustomerId, Number testOpsStripeCustomerId, Number enterprisePlanId, String recurlyInvoiceNumber, Number nextBillingDate, Number canceledAt) {
		this.id = id
		this.createdAt = createdAt
		this.updatedAt = updatedAt
		this.archived = archived
		this.recurlySubscriptionId = recurlySubscriptionId
		this.recurlySubscriptionUuid = recurlySubscriptionUuid
		this.unpaid = unpaid
		this.status = status
		this.stripeCustomerId = stripeCustomerId
		this.testOpsStripeCustomerId = testOpsStripeCustomerId
		this.enterprisePlanId = enterprisePlanId
		this.recurlyInvoiceNumber = recurlyInvoiceNumber
		this.nextBillingDate = nextBillingDate
		this.canceledAt = canceledAt
	}
}

