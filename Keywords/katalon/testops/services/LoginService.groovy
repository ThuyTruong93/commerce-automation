package katalon.testops.services
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.util.CryptoUtil

import internal.GlobalVariable
import katalon.fw.lib.BaseService
import katalon.model.Account

public class LoginService extends BaseService<LoginService>{
	static String loginUrl = GlobalVariable.myApi + "/v1/auth/login";

	public LoginService login(String email, String password) {
		initRequestObject().create(new Account(email: email, password: password), loginUrl)
		return this
	}

	public LoginService loginWithEncryptedPwd(String email, String encrypedPwd) {
		initRequestObject().create(new Account(email: email, password: CryptoUtil.decode(CryptoUtil.getDefault(encrypedPwd))), loginUrl)
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
