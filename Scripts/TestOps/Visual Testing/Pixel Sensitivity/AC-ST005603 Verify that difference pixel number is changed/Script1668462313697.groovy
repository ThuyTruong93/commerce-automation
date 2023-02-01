import katalon.common.HeaderBar
import katalon.fw.lib.Page
import katalon.testops.services.LoginService
import katalon.testops.visualtesting.VisualTestRunDetail
import katalon.testops.visualtesting.VisualTestRunPage

'1. Login to TO as admin account'

// enhance later with specific test data: run vst test with mismatch  
// current using the old test run 
def vstID = [423, 1004, 996, 948 ]
Page.nav(LoginService)
	.login(internal.GlobalVariable.vst_pro_email, 'K@talon2021')
	.getToken()
	.verifyStatusCode(200)

'2. With the Visual Testing Pro, navigate to the Module Visual Testing > Visual Test Runs > choose an ID of a VTR > choose a Mismatch Checkpoint.'	
Page.nav(VisualTestRunPage)
	.accessDetailsVSTProject('143841', '158146')

Page.nav(HeaderBar)
	.clickVisualTesting()

Page.nav(VisualTestRunPage)
	.accessDetailsTestRunById(1004)
	
Page.nav(VisualTestRunDetail)
	.clickToImageWithStatus("MISMATCH")
	.selectComparisonMethod("Pixel") // Pixel, Layout, Text Content
	.changePixelSensitivity(3) // 0, 1 ,2 ,3 
	.isDiffCountDecreased()