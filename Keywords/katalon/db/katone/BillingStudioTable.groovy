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
		def selectScript = "select * from billing.studio_subscriptions ats2 where ats2.accountid  = $accountId;"

		//def values = executeSelect("accountid = $accountId","accountid")
		println "$dbName"
		println "$table"
		//executeSelect(dbName,table,"accountid = $accountId","accountid")
		
		def value = execute(selectScript)
		println "value: $value"
		return this
	}
}
