import katalon.common.HeaderBar
import katalon.fw.lib.Page
import katalon.testops.services.LoginService
import katalon.testops.visualtesting.VisualTestRunPage

'1. Login to TO as admin account'
Page.nav(LoginService).login(internal.GlobalVariable.vst_pro_email, 'K@talon2021').getToken().verifyStatusCode(200)

'2. Verify User can access to pro org'
Page.nav(VisualTestRunPage)
	.accessDetailsVSTProject('143841', '158146')

Page.nav(HeaderBar).clickVisualTesting()

Page.nav(VisualTestRunPage)
	.isVSTProlabelDisplay(true)

'3. Verify User can access to VST standard org'
Page.nav(VisualTestRunPage)
	.accessDetailsVSTProject('261532', '373205')

Page.nav(HeaderBar).clickVisualTesting()
		
Page.nav(VisualTestRunPage)
	.isVSTProlabelDisplay(false)
	
	
	