package katalon.db.katone

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import katalon.fw.lib.BaseTable

public class BillingStudioTable extends BaseTable<BillingStudioTable> {

	private BillingStudioTable() {
		dbName = "katone"
		table = "billing.studio_subscriptions ats2 inner join billing.recurly_subscriptions x on ats2.recurlysubscriptionid =x.id"				
	}
	
	String accountid
	String expirydate
	String feature
	String recurlysubscriptionid
	String status
	
	public BillingStudioTable selectStudioSubscription(Number accountid) {
//		def selectScript = "select *\
//			from billing.studio_subscriptions ats2 inner join billing.recurly_subscriptions x\
//			on ats2.recurlysubscriptionid =x.id where accountid  = $accountid order by ats2.recurlysubscriptionid desc;"
//		def value = quickExecute(selectScript)
//		return value
		
		def queryCondition = "accountid = $accountid order by recurlysubscriptionid desc"
		def accountidDB = executeSelect(queryCondition,"accountid")
		def expirydateDB = executeSelect(queryCondition,"expirydate")
		def featureDB = executeSelect(queryCondition,"feature")
		def recurlysubscriptionidDB = executeSelect(queryCondition,"recurlysubscriptionid")
		def statusDB = executeSelect(queryCondition,"status")
		def dataQuery = [accountidDB, expirydateDB, featureDB, recurlysubscriptionidDB, statusDB]
		println "$dbName"
		println "$table"
		println "value: $dataQuery"
		
		this.accountid = accountidDB		
		this.expirydate = expirydateDB
		this.feature = featureDB
		this.recurlysubscriptionid = recurlysubscriptionidDB
		this.status = statusDB

		return this
	}	

}
