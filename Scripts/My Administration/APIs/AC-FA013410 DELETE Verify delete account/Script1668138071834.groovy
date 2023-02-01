import com.kms.katalon.util.CryptoUtil

import internal.GlobalVariable
import katalon.fw.lib.Page
import katalon.services.MyAccountService
import katalon.testops.services.LoginService

String decryptedPwd =  CryptoUtil.decode(CryptoUtil.getDefault(GlobalVariable.owner_pass))

'User login to get token '
Page.nav(LoginService).login(GlobalVariable.owner_mail, decryptedPwd).verifyStatusCode(200).getToken()

'User delete account by account Id'
Page.nav(MyAccountService).initRequestObject()
					.createNewAccount(accountName)	
					.setUrlWithAccountId()
					.sendDeleteRequest()
					.verifyStatusCode(200)
					
					
					
				