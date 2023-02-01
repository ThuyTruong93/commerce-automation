package katalon.common

import com.kms.katalon.util.CryptoUtil

import internal.GlobalVariable
import katalon.fw.db.PostgreSql
import katalon.fw.lib.Page
import katalon.model.Account
import katalon.testops.services.LoginService

public class SeedingData {
	public dataSetUp() {
		Page.nav(PostgreSql).openConnection().executeUpdate("update  public.accounts set name = 'QEAUTO Account Name_hl' where owneremail='hong.le@katalon.com' and name ='QEAUTO Account Name_updated'")
		Page.nav(PostgreSql).closeConnection()
	}

	public static void setUpKatOneToken(String email, String encodedPwd) {
		Account account = new Account(email: email, password: CryptoUtil.decode(CryptoUtil.getDefault(encodedPwd)))

		Page.nav(LoginService)
			.initRequestObject()
			.setUrl("${GlobalVariable.myApi}/v1/auth/login")
			.setJsonContentTypeHeader()
			.setPayLoad(account)
			.sendPostRequest()
			.verifyStatusCode(200)
			.getToken()
	}
}
