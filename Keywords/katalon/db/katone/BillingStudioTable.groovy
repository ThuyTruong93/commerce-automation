package katalon.db.katone

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import katalon.fw.lib.BaseTable

public class BillingStudioTable extends BaseTable<BillingStudioTable> {

	private BillingStudioTable() {
		dbName = "katone"
		table = "billing.studio_subscriptions"				
	}
	
	String accountid
	String expirydate
	String feature
	String recurlysubscriptionid
	String status

	public void setAccountId(String accountid) {
		this.accountid = accountid
	}

	public void setExpirydate(String expirydate) {
		this.expirydate = expirydate
	}

	public void setFeature(String feature) {
		this.feature = feature
	}

	public void setRecurlysubscriptionid(String recurlysubscriptionid) {
		this.recurlysubscriptionid = recurlysubscriptionid
	}

	public void setStatus(String status) {
		this.status = status
	}

	public getAccountId() {
		return this.accountid
	}

	public getExpirydate() {
		return this.expirydate
	}

	public getFeature() {
		return this.feature
	}

	public getRecurlysubscriptionid() {
		return this.recurlysubscriptionid
	}

	public getStatus() {
		return this.status
	}
	
	public BillingStudioTable selectStudioSubscription(Number accountId) {
//		def selectScript = "select *\
//				from billing.studio_subscriptions ats2 where ats2.accountid  = $accountId;"
		//def values = quickExecute(selectScript)
		
		def accountidDB = executeSelect("accountid = $accountId order by recurlysubscriptionid desc","accountid")
		def expirydateDB = executeSelect("accountid = $accountId order by recurlysubscriptionid desc","expirydate")
		def featureDB = executeSelect("accountid = $accountId order by recurlysubscriptionid desc","feature")
		def recurlysubscriptionidDB = executeSelect("accountid = $accountId order by recurlysubscriptionid desc","recurlysubscriptionid")
		def statusDB = executeSelect("accountid = $accountId order by recurlysubscriptionid desc","status")
		def dataQuery = [accountidDB, expirydateDB, featureDB, recurlysubscriptionidDB, statusDB]
		println "$dbName"
		println "$table"
		println "value: $dataQuery"

		// create a new BillingStudioTable object and set its properties
		BillingStudioTable billingStudioTable = new BillingStudioTable()
		billingStudioTable.setAccountId(accountidDB.toString())
		billingStudioTable.setExpirydate(expirydateDB.toString())
		billingStudioTable.setFeature(featureDB.toString())
		billingStudioTable.setRecurlysubscriptionid(recurlysubscriptionidDB.toString())
		billingStudioTable.setStatus(statusDB.toString())

		return billingStudioTable
	}	

}
