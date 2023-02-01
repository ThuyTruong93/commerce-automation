package katalon.services

import internal.GlobalVariable
import katalon.fw.lib.BaseService

public class MyAccountService extends BaseService<MyAccountService> {
	static String accountUrl = GlobalVariable.myApi+"v1/accounts/"
	public String accountId


	public MyAccountService createNewAccount(String accountName) {
		setUrl(accountUrl)
				.setBearerAuthorizationHeader()
				.setJsonContentTypeHeader()
				.setPayLoad('{"name":"'+accountName+'"}')
				.sendPostRequest()
				.verifyStatusCode(200)
		this.accountId = parseResponseBodyToJsonObject().data.id
		println this.accountId
		return this
	}
	
	public MyAccountService setUrlWithAccountId(String accountId = this.accountId) {
		setUrl(accountUrl + accountId)
		return this
	}
}
