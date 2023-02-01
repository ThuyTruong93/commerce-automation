package katalon.testops.visualtesting

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import katalon.fw.lib.BasePage
import katalon.testops.services.LoginService
import katalon.fw.lib.Page

public class VisualTestRunPage extends BasePage<VisualTestRunPage> {
	def LoginService loginAPI = new LoginService()
	def email = internal.GlobalVariable.vst_pro_email
	def password = internal.GlobalVariable.vst_pro_password
	def pageElement = [ TITLE_BASELINECOLLECTIONTAB: 'Visual Baseline Collections',
		TITLE_VISUALTESTRUNTAB: 'Visual Test Runs',
		TITLE_VISUALTESTING: 'Visual Testing',
		LABEL_VISUALTESTING_PRO: '//span[contains(@class,"badge-pro")]'
		]
		
	public VisualTestRunPage accessDetailsVSTProject(String teamId, String projectId) {
		String token = internal.GlobalVariable.encodedToken
		String vstUrl = internal.GlobalVariable.testOpsUrl + 'team/' + teamId + '/project/' + projectId + '?katone_access_token=' + token
													   
		WebUI.navigateToUrl(vstUrl)
		WebUI.delay(5)
		return this
	}
	
	public VisualTestRunDetail accessDetailsTestRunById(int id) {   
		String detailsTestRun = WebUI.getUrl();
		WebUI.navigateToUrl(detailsTestRun + "/${id}")
		return Page.nav(VisualTestRunDetail)
	}


	public VisualTestRunPage verifyDisableDropDown() {
		TestObject element = id('react-select-2')
		String c = WebUI.getAttribute(element, 'class')
		WebUI.verifyEqual(c.contains('react-select--is-disabled'), true)
		return this
	}

	public VisualTestRunPage navOrganization(String urlOrganization) {
		WebUI.navigateToUrl(urlOrganization)
		return this
	}

	public VisualTestRunPage selectTab(String xpathTab) {
		WebUI.click(xpath(xpathTab))
		return this
	}

	public VisualTestRunPage selectVerticalEllipsis(String xpathEllipsis) {
		WebUI.click(xpath(xpathEllipsis))
		return this
	}

	public VisualTestRunPage selectTestOpsModule(String nameModule) {
		WebUI.click(title(nameModule))
		return this
	}

	public VisualTestRunPage selectVisualTestingModule() {
		WebUI.click(title(pageElement['TITLE_VISUALTESTING']))
		return this
	}

	public VisualTestRunPage selectVisualRunsTab() {
		WebUI.click(title(pageElement['TITLE_VISUALTESTRUNTAB']))
		return this
	}

	public VisualTestRunPage selectVisualTestRun(String stringCSS) {
		WebUI.click(css(stringCSS))
		return this
	}

	public VisualBaselineCollectionPage selectVisualBaselineCollectionsTab() {
		click(title(pageElement['TITLE_BASELINECOLLECTIONTAB']))
		return Page.nav(VisualBaselineCollectionPage)
	}

	public VisualTestRunPage selectThumbnailImage(String cssThumnailImage) {
		WebUI.click(css(cssThumnailImage))
		return this
	}
	
	public VisualTestRunPage clickMismatchImage() {
		click(xpath(pageElement['xpath_mismatch_thumbnails']))
		WebUI.delay(20)
		return this
	}
	
	public VisualTestRunPage isVSTProlabelDisplay(boolean expectedResult) {
		def actualResult = WebUI.verifyElementPresent(xpath(pageElement['LABEL_VISUALTESTING_PRO']),0, FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.verifyEqual(actualResult, expectedResult)
		return this
	}
}