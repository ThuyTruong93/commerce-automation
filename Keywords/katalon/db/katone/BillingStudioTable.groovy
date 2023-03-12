package katalon.db.katone

import katalon.fw.db.PostgreSql
import katalon.fw.lib.BaseTable
import katalon.fw.lib.Page

public class BillingStudioTable extends BaseTable<BillingStudioTable> {

	private BillingStudioTable() {
		dbName = "katone"
		table = "billing.studio_subscriptions"
	}
	
	public BillingStudioTable selectStudioSubscription(Number accountId) {
		def selectScript = "select * from billing.studio_subscriptions ats2 where ats2.accountid  = $accountId and ats2.status = 'ACTIVE';"

		def values = execute(selectScript)
		println "value: $values"
		return this
	}
}
