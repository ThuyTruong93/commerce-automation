package katalon.my.productutilization

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage


public class TestCloudUtilizationPage extends BasePage<TestCloudUtilizationPage>{

	public TestCloudUtilizationPage clickKatalonTestCloudTab() {
		WebUI.click(text("TestCloud"))
		return this
	}

	public TestCloudUtilizationPage verifyUITestCloudUtilizationPage() {
		WebUI.verifyElementPresent(text("Usage Dashboard"), 5)
		WebUI.verifyElementPresent(text("Parallel Sessions"), 5)
		WebUI.verifyElementPresent(xpath("//*[name()='canvas']"), 5)
		return this
	}
}
