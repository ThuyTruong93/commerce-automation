import katalon.common.HeaderBar
import katalon.common.SignInPage
import katalon.fw.lib.Page
import katalon.testops.services.LoginService
import katalon.testops.visualtesting.VisualTestRunPage

'Pre-condition: login with PRO user'
def email = internal.GlobalVariable.vst_pro_email
def password = internal.GlobalVariable.vst_pro_password

'Pre-condition: Login to pro user'
Page.nav(LoginService)
					.login(internal.GlobalVariable.vst_pro_email, 'K@talon2021')
					.getToken()
					.verifyStatusCode(200)
					
'Step1: access vst baseline collection '
Page.nav(VisualTestRunPage)
	.accessDetailsVSTProject('143841', '158146')

'Step2: Select default comparison method in properties'
Page.nav(HeaderBar).clickVisualTesting()
Page.nav(VisualTestRunPage)
	.selectVisualBaselineCollectionsTab()
	.selectLatestBaselineCollection()
	.selectDefaultComparisonMethod()
	.clickSaveChange()

'Result: dialog display save success' // in progress
