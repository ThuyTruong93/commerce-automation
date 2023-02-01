import katalon.common.HeaderBar
import katalon.fw.lib.Page
import katalon.testops.services.LoginService
import katalon.testops.visualtesting.VisualBaselineCollectionPage
import katalon.testops.visualtesting.VisualTestRunPage

'1. Login to TO as admin account'
Page.nav(LoginService).login(internal.GlobalVariable.vst_pro_email, 'K@talon2021').getToken().verifyStatusCode(200)

'Step 1: access Visual baseline collection'
Page.nav(VisualTestRunPage).accessDetailsVSTProject('143841', '158146')

'2. With the Visual Testing Standard, navigate to the Module Visual Testing > Visual Baseline Collections > choose an ID of a VBC.'
Page.nav(HeaderBar).clickVisualTesting()

Page.nav(VisualTestRunPage)
	.selectVisualBaselineCollectionsTab()
	.selectLatestBaselineCollection()

'3. Change Pixel Sensitivity and button Save Changes is enabled; button Upload Images is disabled.'
'Verify that TO displays the sign in the next of button Upload Images.'

Page.nav(VisualBaselineCollectionPage)
.verifyChangePixelSaveChangesEnables().verifyUploadImagesDisableAndDisplaySign()



