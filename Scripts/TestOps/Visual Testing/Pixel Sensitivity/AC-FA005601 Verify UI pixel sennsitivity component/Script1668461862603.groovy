import katalon.common.HeaderBar
import katalon.fw.lib.Page
import katalon.testops.services.LoginService
import katalon.testops.visualtesting.VisualTestRunPage

def email = internal.GlobalVariable.vst_pro_email
def password = internal.GlobalVariable.vst_pro_password

'Pre-condition: Login to pro user'
Page.nav(LoginService)
					.login(internal.GlobalVariable.vst_pro_email, 'K@talon2021')
					.getToken()
					.verifyStatusCode(200)
					
'2. Navigate to project visual testing with pro'
Page.nav(VisualTestRunPage)
	.accessDetailsVSTProject('143841', '158146')
'3. Select Visual testing tab'
Page.nav(HeaderBar).clickVisualTesting()
	
'4. Select latest Visual baseline Collections'
Page.nav(VisualTestRunPage)
	.selectVisualBaselineCollectionsTab()
	.selectLatestBaselineCollection()
	.verifyPixelSensitivityComponent()