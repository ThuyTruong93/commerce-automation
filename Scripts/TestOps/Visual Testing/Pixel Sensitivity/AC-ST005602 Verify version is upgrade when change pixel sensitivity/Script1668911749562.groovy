import katalon.common.HeaderBar
import katalon.fw.lib.Page
import katalon.testops.services.LoginService
import katalon.testops.visualtesting.VisualTestRunPage as VisualTestRunPage
import katalon.testops.visualtesting.VisualBaselineCollectionPage as VisualBaselineCollectionPage

String visible = 'disable'


// need account for free user for org
Page.nav(LoginService).login(internal.GlobalVariable.vst_pro_email, 'K@talon2021').getToken().verifyStatusCode(200)

'Pre-condition: Login to Free user'

'Step 1: access Visual baseline collection'
Page.nav(VisualTestRunPage).accessDetailsVSTProject('143841', '158146')

'Step 2: open Baseline Collection'
Page.nav(HeaderBar).clickVisualTesting()

Page.nav(VisualTestRunPage)
	.selectVisualBaselineCollectionsTab()
	.selectLatestBaselineCollection()
	
'Get current version'	
def	currentVersion = Page.nav(VisualBaselineCollectionPage).getCurrentVersion() as int

'Select randome Pixel sensitivity'
Page.nav(VisualBaselineCollectionPage)
	.randomSelectPixelSensitive()
	.clickSaveChange()

'Get Latest version'	
def latestVersion = Page.nav(VisualBaselineCollectionPage).getCurrentVersion() as int

assert latestVersion > currentVersion


