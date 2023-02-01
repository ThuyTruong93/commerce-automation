package katalon.testops.services
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import internal.GlobalVariable
import katalon.fw.lib.BaseService
import katalon.model.Account

public class LoginService extends BaseService<LoginService>{
	static String loginUrl = GlobalVariable.myApi + "/v1/auth/login";

	public LoginService login (String email, String password) {
		Account account = new Account(email: email, password: password)
		initRequestObject()
				.setUrl(loginUrl)
				.setJsonContentTypeHeader()
				.setPayLoad(parseObjectToString(account))
				.sendPostRequest()

		return this
	}

	public LoginService verifyEmail (String expected) {
		String actual = parseResponseBodyToJsonObject().data.user.email
		WS.verifyEqual(actual, expected)
		return this
	}

	public LoginService verifyFirstName (String expected) {
		String actual = parseResponseBodyToJsonObject().data.user.firstName
		WS.verifyEqual(actual, expected)
		return this
	}

	public LoginService getToken () {
		String token = parseResponseBodyToJsonObject().data.jwt
		GlobalVariable.encodedToken = token
		return this
	}
}
